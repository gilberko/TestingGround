package com.example.linuxapp.screens.usermode

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.linuxapp.screens.kernel.BodyText
import com.example.linuxapp.screens.kernel.CodeBlock
import com.example.linuxapp.screens.kernel.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserModeFilesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Files",
                        color = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontSize = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item {
                SectionCard(title = "Two Levels of File I/O") {
                    BodyText("Linux file I/O has two distinct layers that programmers use:")
                    CodeBlock(
                        """C Standard Library (libc) — high level, buffered
  FILE *f = fopen("a.txt", "r");
  fread / fwrite / fprintf / fscanf / fgets / fputs
  fclose

  Operates on FILE* (a struct holding a buffer + fd).
  I/O is buffered — data accumulates in a userspace
  buffer and is sent to the kernel in larger batches.

POSIX / System Call API — low level, unbuffered
  int fd = open("a.txt", O_RDONLY);
  read / write / lseek / close / fsync

  Operates on int file descriptors.
  Every call goes directly to the kernel.
  No userspace buffering."""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Internally, every FILE* wraps an int file descriptor. When the libc buffer is full (or explicitly flushed), it calls the write() system call to hand data to the kernel.")
                }
            }
            item {
                SectionCard(title = "fopen and fclose → open / close") {
                    BodyText("fopen() opens a file and returns a FILE*. Underneath it calls the open() system call (syscall number 2 on x86_64) to obtain a file descriptor, then allocates a FILE struct with an I/O buffer.")
                    CodeBlock(
                        """/* C API: */
FILE *f = fopen("data.txt", "r");
/*            mode strings:
   "r"  — read only           → open(path, O_RDONLY)
   "w"  — write, truncate     → open(path, O_WRONLY|O_CREAT|O_TRUNC, 0666)
   "a"  — append              → open(path, O_WRONLY|O_CREAT|O_APPEND, 0666)
   "r+" — read + write        → open(path, O_RDWR)
   "w+" — read + write trunc  → open(path, O_RDWR|O_CREAT|O_TRUNC, 0666)
   "a+" — read + append       → open(path, O_RDWR|O_CREAT|O_APPEND, 0666) */

/* Equivalent direct syscall: */
int fd = open("data.txt", O_RDONLY);"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("fclose() flushes the buffer (triggers a write() syscall if there is buffered data), then calls close() to release the file descriptor:")
                    CodeBlock(
                        """fclose(f);
/* Internally: fflush(f) → write(fd, buf, n)  [if dirty]
               close(fd)                               */

/* Equivalent direct syscall: */
close(fd);"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Get the underlying file descriptor from a FILE*:")
                    CodeBlock("int fd = fileno(f);")
                }
            }
            item {
                SectionCard(title = "fprintf → write") {
                    BodyText("fprintf() formats a string into libc's internal FILE buffer. The write() system call is NOT invoked on every fprintf() call — only when the buffer fills up, when fflush() is called, or when fclose() is called.")
                    CodeBlock(
                        """FILE *f = fopen("log.txt", "w");

fprintf(f, "Count: %d\n", 42);
/* Data goes into libc's buffer (typically 4–8 KB).
   No system call yet. */

fprintf(f, "Name: %s\n", "hello");
/* Still in buffer. */

fflush(f);
/* NOW libc calls: write(fd, buffer, n)
   Everything buffered so far is sent to the kernel. */

fclose(f);
/* Flushes remaining buffer, then calls close(fd). */"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Buffering modes — controlled by setvbuf():")
                    CodeBlock(
                        """_IOFBF  — fully buffered (default for files)
          write() called only when buffer is full

_IOLBF  — line buffered (default for stdout when terminal)
          write() called on each newline

_IONBF  — unbuffered (default for stderr)
          write() called on every fprintf/fputc

/* Set 16 KB buffer: */
setvbuf(f, NULL, _IOFBF, 16384);

/* Disable buffering: */
setvbuf(f, NULL, _IONBF, 0);"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Equivalent direct syscall (no buffering):")
                    CodeBlock(
                        """char buf[64];
int n = snprintf(buf, sizeof(buf), "Count: %d\n", 42);
write(fd, buf, n);   /* goes to kernel immediately */"""
                    )
                }
            }
            item {
                SectionCard(title = "fscanf → read") {
                    BodyText("fscanf() reads from the FILE buffer. When the buffer is empty, libc calls the read() system call to refill it from the kernel, then fscanf parses the formatted data from the in-memory buffer.")
                    CodeBlock(
                        """FILE *f = fopen("data.txt", "r");
int count;
char name[64];

fscanf(f, "%d %s", &count, name);
/* If the buffer is empty, libc calls:
     read(fd, internal_buf, BUFSIZ)
   to fill it, then scans formatted data from the buffer.
   On subsequent fscanf calls, data is read from the
   in-memory buffer until it's exhausted — then another
   read() syscall refills it. */

fclose(f);"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Equivalent direct syscall approach (reading a line):")
                    CodeBlock(
                        """char buf[256];
ssize_t n = read(fd, buf, sizeof(buf) - 1);
buf[n] = '\0';
sscanf(buf, "%d %s", &count, name);"""
                    )
                }
            }
            item {
                SectionCard(title = "fread / fwrite → read / write") {
                    BodyText("fread() and fwrite() handle binary data (no formatting). They also go through the libc buffer:")
                    CodeBlock(
                        """/* Write binary data: */
int values[4] = {1, 2, 3, 4};
fwrite(values,        /* pointer to data */
       sizeof(int),   /* size of one element */
       4,             /* number of elements */
       f);            /* FILE* */
/* Buffered — write() syscall fires when buffer fills or fflush */

/* Read binary data: */
int result[4];
size_t n = fread(result, sizeof(int), 4, f);
/* Calls read() syscall to fill buffer if needed,
   then copies 4 ints out of the buffer. */"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Equivalent direct syscalls (unbuffered):")
                    CodeBlock(
                        """write(fd, values, sizeof(values));  /* 16 bytes, one syscall */
read(fd, result, sizeof(result));   /* 16 bytes, one syscall */"""
                    )
                }
            }
            item {
                SectionCard(title = "System Call Numbers (x86_64)") {
                    BodyText("These are the underlying syscalls that libc calls when performing file I/O. You can look them up in /usr/include/asm/unistd_64.h:")
                    CodeBlock(
                        """Syscall name     Number   Description
read             0        Read from file descriptor
write            1        Write to file descriptor
open             2        Open file (legacy, 2-arg path)
close            3        Close file descriptor
lseek            8        Move file position
fstat            5        Get file metadata
openat           257      Open relative to directory fd
                          (modern replacement for open)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("See exactly which syscalls your program makes with strace:")
                    CodeBlock(
                        """strace -e trace=openat,read,write,close ./myprogram

# Example output:
# openat(AT_FDCWD, "data.txt", O_RDONLY) = 3
# read(3, "hello world\n", 4096)         = 12
# write(1, "hello world\n", 12)          = 12
# close(3)                               = 0"""
                    )
                }
            }
            item {
                SectionCard(title = "Direct POSIX API (No C Buffering)") {
                    BodyText("You can bypass libc buffering entirely and call the POSIX syscall wrappers directly:")
                    CodeBlock(
                        """#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>

/* Open */
int fd = open("data.txt", O_RDWR | O_CREAT | O_TRUNC, 0644);

/* Write */
const char *msg = "Hello\n";
write(fd, msg, strlen(msg));

/* Move position */
lseek(fd, 0, SEEK_SET);   /* rewind to start */
lseek(fd, 0, SEEK_END);   /* jump to end */
lseek(fd, -10, SEEK_CUR); /* back 10 bytes from current */

/* Read */
char buf[64];
ssize_t n = read(fd, buf, sizeof(buf) - 1);
buf[n] = '\0';

/* Flush OS page cache to disk (fsync = fdatasync + metadata): */
fsync(fd);
fdatasync(fd);   /* only data, faster */

/* Get file info */
struct stat st;
fstat(fd, &st);
printf("Size: %lld\n", (long long)st.st_size);

close(fd);"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("When to use each layer:")
                    CodeBlock(
                        """FILE* / libc   — text files, formatted I/O, config files,
                 log files (buffering improves performance)

POSIX / fd     — network sockets, pipes, device files,
                 performance-critical binary I/O, when
                 you need exact control over when data
                 is sent to the kernel"""
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
