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
fun UserModeGlibcScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "glibc",
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
                SectionCard(title = "What is glibc?") {
                    BodyText("glibc (GNU C Library) is the reference implementation of the C standard library for Linux. It is the layer between user-space programs and the Linux kernel — it wraps system calls, implements standard C functions, and provides POSIX APIs.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("glibc ships on virtually every major Linux distribution: Ubuntu, Debian, Fedora, RHEL, Arch, openSUSE. The shared library is typically at /lib/x86_64-linux-gnu/libc.so.6 (or the equivalent path for the architecture and distro).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("It also ships the dynamic linker/loader: ld-linux-x86-64.so.2 (64-bit x86) or ld-linux.so.2 (32-bit). The linker is the first code that runs when an ELF executable is launched — it loads all required shared libraries, resolves symbols, and then jumps to the program's entry point.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Notable alternatives:")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("musl — lightweight, single-file, used in Alpine Linux and musl-based embedded distros. Prioritizes correctness and simplicity over performance. Not ABI-compatible with glibc.")
                    BodyText("uClibc-ng — designed for embedded systems with tight memory budgets.")
                    BodyText("dietlibc — extremely minimal, for static linking in tiny environments.")
                    BodyText("Bionic — Android's C library, derived from BSD, bundled with the Android OS.")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "What glibc Provides") {
                    BodyText("Standard C library — the full ISO C99/C11/C17 specification: stdio.h, stdlib.h, string.h, math.h, time.h, etc.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("POSIX.1-2017 — file I/O, sockets, pthreads, signals, pipes, mmap, and hundreds of other POSIX functions. Pthreads are implemented by the NPTL (Native POSIX Thread Library), which is part of glibc.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("GNU extensions (enabled with _GNU_SOURCE) — asprintf(), getline(), memmem(), pthread_setname_np(), and many others not in POSIX.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Syscall wrappers — for most Linux syscalls, glibc provides a wrapper function that handles argument marshaling, errno setting, and the actual syscall instruction. You rarely need to call syscall() directly.")
                    CodeBlock("""
// glibc's read() internally does something like:
ssize_t read(int fd, void *buf, size_t count) {
    ssize_t ret = syscall(SYS_read, fd, buf, count);
    if (ret < 0) { errno = -ret; return -1; }
    return ret;
}
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("vDSO (virtual Dynamic Shared Object) — for very frequently called functions like gettimeofday(), clock_gettime(), time(), and getpid(), the kernel maps a small shared library directly into every process's address space. These functions execute entirely in user mode without a syscall trap, reducing overhead from ~100 ns to ~5 ns.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("NSS (Name Service Switch) — pluggable resolver for users, groups, hostnames. DNS resolver. Locale and internationalization (iconv character set conversion, LC_* environment variables).")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "Detecting glibc Version at Runtime (Shell)") {
                    BodyText("There are several ways to determine which glibc version is installed on a running system:")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("ldd --version — prints the glibc version used by ldd itself (which is linked against the system glibc).")
                    CodeBlock("""
$ ldd --version
ldd (Ubuntu GLIBC 2.35-0ubuntu3) 2.35
Copyright (C) 2022 Free Software Foundation, Inc.
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Run libc.so directly — the libc shared library accepts --version as an argument and prints its own version string.")
                    CodeBlock("""
$ /lib/x86_64-linux-gnu/libc.so.6 --version
GNU C Library (Ubuntu GLIBC 2.35-0ubuntu3) stable release version 2.35.
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("ldd on the binary — shows which version of libc.so is linked into a specific executable.")
                    CodeBlock("""
$ ldd /bin/ls
    linux-vdso.so.1 (0x00007ffd...)
    libselinux.so.1 => /lib/x86_64-linux-gnu/libselinux.so.1
    libc.so.6 => /lib/x86_64-linux-gnu/libc.so.6 (0x00007f...)
    /lib64/ld-linux-x86-64.so.2
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("strings on the library — searches for the version string embedded in the shared library binary.")
                    CodeBlock("""
$ strings /lib/x86_64-linux-gnu/libc.so.6 | grep "GNU C Library"
GNU C Library (Ubuntu GLIBC 2.35-0ubuntu3) stable release version 2.35.
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The library path varies by architecture and distro. Common paths: /lib/x86_64-linux-gnu/libc.so.6 (Debian/Ubuntu 64-bit), /lib/aarch64-linux-gnu/libc.so.6 (ARM64), /lib64/libc.so.6 (RHEL/Fedora), /usr/lib/libc.so.6 (some configurations).")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "Detecting glibc Version Programmatically") {
                    BodyText("From C code, glibc provides dedicated functions for querying its own version at runtime:")
                    CodeBlock("""
#include <gnu/libc-version.h>
#include <stdio.h>

int main(void) {
    // Runtime version string, e.g. "2.35"
    printf("glibc version: %s\n", gnu_get_libc_version());
    // Release type, typically "stable"
    printf("glibc release: %s\n", gnu_get_libc_release());
    return 0;
}
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("confstr() with _CS_GNU_LIBC_VERSION — POSIX-style interface that writes the version string into a buffer.")
                    CodeBlock("""
#include <unistd.h>
char buf[32];
confstr(_CS_GNU_LIBC_VERSION, buf, sizeof(buf));
// buf now contains e.g. "glibc 2.35"
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Compile-time macros (defined in <features.h>, included transitively by almost all glibc headers):")
                    CodeBlock("""
#include <features.h>
// __GLIBC__       = major version, e.g. 2
// __GLIBC_MINOR__ = minor version, e.g. 35
#if __GLIBC__ == 2 && __GLIBC_MINOR__ >= 34
    // Use features added in glibc 2.34
#endif
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Feature-test macros — control which APIs are visible. Define before including any headers:")
                    CodeBlock("""
#define _GNU_SOURCE        // enable all GNU extensions
#define _POSIX_C_SOURCE 200809L  // POSIX.1-2008
#define _XOPEN_SOURCE 700       // XSI/POSIX extended
                    """.trimIndent())
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "Reading glibc Version from an ELF Binary") {
                    BodyText("When a C program is compiled and linked against glibc, the resulting ELF binary records exactly which glibc symbols it uses, tagged with the glibc version that introduced them. This lets you determine the minimum required glibc version without running the binary.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("readelf -V mybinary — dumps the .gnu.version_r section (Version Needs: which symbol versions the binary requires from each library) and .gnu.version_d (Version Definitions: symbols this binary exports).")
                    CodeBlock("""
$ readelf -V /bin/ls
Version needs section '.gnu.version_r':
  0x0000: Version: 1  File: libselinux.so.1  Cnt: 1
    0x0010:   Name: GLIBC_2.17  Flags: none  Version: 5
  0x0020: Version: 1  File: libc.so.6  Cnt: 3
    0x0030:   Name: GLIBC_2.17  Flags: none  Version: 4
    0x0040:   Name: GLIBC_2.3.4  Flags: none  Version: 3
    0x0050:   Name: GLIBC_2.33  Flags: none  Version: 2
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The highest GLIBC_X.Y entry under libc.so.6 is the minimum glibc version required on the target system. In the example above: GLIBC_2.33 — so this binary needs glibc >= 2.33.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Other useful commands:")
                    CodeBlock("""
# List required shared libraries
$ readelf -d mybinary | grep NEEDED
 0x0001 (NEEDED) Shared library: [libc.so.6]

# Same with objdump
$ objdump -p mybinary | grep NEEDED
  NEEDED    libc.so.6

# Show versioned symbol names (@@GLIBC_X.Y tags)
$ nm -D mybinary | grep GLIBC
         U printf@@GLIBC_2.2.5
         U malloc@@GLIBC_2.2.5
         U clock_gettime@@GLIBC_2.17
                    """.trimIndent())
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "Symbol Versioning") {
                    BodyText("glibc maintains backward compatibility through ELF symbol versioning. A single libc.so.6 can contain multiple implementations of the same function under different version tags, allowing old and new binaries to coexist on the same system.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("In nm -D output, symbols appear with version tags appended:")
                    CodeBlock("""
memcpy@@GLIBC_2.14   # @@ = default (current) version
memcpy@GLIBC_2.2.5  # @  = older compatibility version

memmove@@GLIBC_2.2.5
printf@@GLIBC_2.2.5
getaddrinfo@@GLIBC_2.2.5
clock_gettime@@GLIBC_2.17
pthread_create@@GLIBC_2.34
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("A binary compiled today gets memcpy@@GLIBC_2.14 (the AVX-optimized version added in 2.14). An old binary compiled before glibc 2.14 references memcpy@GLIBC_2.2.5, and the dynamic linker resolves it to the old implementation — both live in the same .so file.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("This is why a binary compiled on Ubuntu 22.04 (glibc 2.35) will typically not run on Ubuntu 18.04 (glibc 2.27) — the newer binary references symbols introduced in 2.28+, which simply don't exist in the older library.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("To maximize portability, compile on the oldest supported target distro or use tools like crosstool-ng or docker containers pinned to old glibc versions. -static links the entire glibc into the binary, eliminating the runtime dependency but increasing binary size.")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "musl vs glibc Compatibility") {
                    BodyText("musl and glibc are ABI-incompatible. A binary compiled and linked against glibc will not run on a musl system, and vice versa — the dynamic linker path itself is different.")
                    CodeBlock("""
# glibc binary interpreter (in ELF PT_INTERP):
/lib64/ld-linux-x86-64.so.2

# musl binary interpreter:
/lib/ld-musl-x86_64.so.1
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("When you run a glibc-linked binary on Alpine Linux (musl), it fails with 'No such file or directory' because /lib64/ld-linux-x86-64.so.2 doesn't exist. The fix is to recompile on Alpine, or use a statically linked binary.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("musl intentionally omits several glibc-specific functions: gnu_get_libc_version(), mallinfo(), obstack, error_t-based error handling, and many _GNU_SOURCE extensions. Code that depends on these must be ported.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Static linking with glibc (-static) produces a self-contained binary that includes the entire libc. It runs on any Linux with the same architecture, regardless of which libc the system has. Trade-offs: larger binary, no security patches from distro libc updates, and some features (NSS, dynamic module loading) are disabled or broken in statically-linked glibc.")
                    CodeBlock("""
# Compile fully static (no shared library dependency):
gcc -static -o myapp myapp.c
file myapp
# myapp: ELF 64-bit LSB executable, statically linked
ldd myapp
# not a dynamic executable
                    """.trimIndent())
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
