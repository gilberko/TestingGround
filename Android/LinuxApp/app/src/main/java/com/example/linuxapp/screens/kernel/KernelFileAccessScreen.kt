package com.example.linuxapp.screens.kernel

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KernelFileAccessScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "File Access in Kernel",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color(0xFF00FF41)
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
            contentPadding = PaddingValues(
                start = 16.dp, end = 16.dp,
                top = innerPadding.calculateTopPadding() + 8.dp,
                bottom = 8.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                SectionCard("Can Kernel Code Open Files?") {
                    BodyText("YES. Kernel code can open, read, write, and close regular files using filp_open() and filp_close().")
                    BodyText("However, this is generally discouraged for kernel-to-user communication. Prefer:")
                    CodeBlock("""
procfs   — /proc/mymodule  (status / text output)
sysfs    — /sys/...        (tunable parameters)
debugfs  — /sys/kernel/debug/... (debug data)
Netlink  — kernel-push + request-response
                    """.trimIndent())
                    BodyText("Legitimate uses for filp_open in production code:")
                    CodeBlock("""
- Loading firmware from /lib/firmware (most common)
- Writing kernel logs to a file (rare)
- Reading a config file at module init time
- Accessing a special device file from kernel
                    """.trimIndent())
                    BodyText("IMPORTANT: File I/O must happen in process context — inside a kthread or workqueue. Never call filp_open() from interrupt context, softirq, or atomic context. The kernel may sleep while waiting for I/O.")
                }
            }
            item {
                SectionCard("The File API") {
                    BodyText("Include <linux/fs.h>. The main functions:")
                    CodeBlock("""
/* Open — returns struct file* or ERR_PTR on error */
struct file *f = filp_open(path, flags, mode);
if (IS_ERR(f)) {
    pr_err("open failed: %ld\n", PTR_ERR(f));
    return PTR_ERR(f);
}

/* Read — kernel_read handles address-space
   switching internally (no set_fs needed) */
loff_t pos = 0;
char buf[128];
ssize_t n = kernel_read(f, buf, sizeof(buf), &pos);

/* Write */
loff_t wpos = 0;
kernel_write(f, "hello\n", 6, &wpos);

/* Close */
filp_close(f, NULL);
                    """.trimIndent())
                    BodyText("kernel_read() and kernel_write() are the modern API (Linux 4.14+). They replaced vfs_read()/vfs_write() with set_fs(KERNEL_DS) which was removed in Linux 5.10. On modern kernels always use kernel_read/kernel_write.")
                    BodyText("Flags for filp_open are the same as user-space open(): O_RDONLY, O_RDWR, O_WRONLY, O_CREAT, O_APPEND, etc. Mode (e.g. 0644) is used only when creating a file.")
                }
            }
            item {
                SectionCard("Blocking I/O Only") {
                    BodyText("Kernel file I/O is SYNCHRONOUS and BLOCKING. There is no kernel-side select(), epoll(), or io_uring available to modules.")
                    CodeBlock("""
/* io_uring — user space only, not available to modules */
/* epoll    — user space syscall, not callable from kernel */
/* select   — user space syscall, not callable from kernel */
                    """.trimIndent())
                    BodyText("For concurrent or non-blocking file I/O from a module, use a dedicated kthread per file:")
                    CodeBlock("""
static int file_reader(void *path)
{
    struct file *f = filp_open(path, O_RDONLY, 0);
    if (IS_ERR(f)) return PTR_ERR(f);

    char buf[256];
    loff_t pos = 0;
    while (!kthread_should_stop()) {
        ssize_t n = kernel_read(f, buf,
                                sizeof(buf), &pos);
        if (n <= 0) break;
        /* process buf[0..n-1] */
    }
    filp_close(f, NULL);
    return 0;
}
                    """.trimIndent())
                    BodyText("For asynchronous file I/O within the VFS internals (kiocb/AIO), that is used by filesystem drivers themselves — not a practical API for ordinary kernel modules. Stick to blocking kernel_read/kernel_write in a kthread.")
                }
            }
            item {
                SectionCard("Full Example: Read a File from Kernel") {
                    CodeBlock("""
#include <linux/module.h>
#include <linux/fs.h>

static int __init mymod_init(void)
{
    struct file *f;
    char buf[128] = {};
    loff_t pos = 0;
    ssize_t n;

    f = filp_open("/etc/hostname", O_RDONLY, 0);
    if (IS_ERR(f)) {
        pr_err("filp_open failed: %ld\n", PTR_ERR(f));
        return PTR_ERR(f);
    }

    n = kernel_read(f, buf, sizeof(buf) - 1, &pos);
    if (n > 0) {
        buf[n] = '\0';
        pr_info("hostname: %s\n", buf);
    }

    filp_close(f, NULL);
    return 0;
}

static void __exit mymod_exit(void) {}

module_init(mymod_init);
module_exit(mymod_exit);
MODULE_LICENSE("GPL");
                    """.trimIndent())
                }
            }
            item {
                SectionCard("Opening a File for Userspace (fd_install)") {
                    BodyText("YES — kernel code can open a file and install the file descriptor directly into a user process's fd table, handing a usable fd back to user space.")
                    BodyText("This is exactly how the socket(), open(), and pipe() syscalls work internally. The mechanism:")
                    CodeBlock("""
/* Step 1: get an unused fd slot in current process.
   Must be called in process context (ioctl handler,
   syscall handler — NOT from a kthread). */
int fd = get_unused_fd_flags(O_CLOEXEC);
if (fd < 0) return fd;   /* -EMFILE if table full */

/* Step 2: open the file */
struct file *f = filp_open("/var/log/mylog",
                            O_RDONLY, 0);
if (IS_ERR(f)) {
    put_unused_fd(fd);   /* release the reserved slot */
    return PTR_ERR(f);
}

/* Step 3: install — transfers ownership to fd table.
   After this, f is owned by the fd table.
   Do NOT filp_close() it yourself. */
fd_install(fd, f);

/* Step 4: return fd to user space.
   e.g. as the return value of ioctl(). */
return fd;
                    """.trimIndent())
                    BodyText("MUST run in the target process's context — i.e., inside an ioctl/syscall handler for that process. If you call this from a kthread, 'current' is the kthread, not the user process, and the fd goes into the wrong fd table.")
                }
            }
            item {
                SectionCard("The Process Closes It Normally") {
                    BodyText("Once fd_install() is called, the user process owns the fd. It can use it with all normal file operations:")
                    CodeBlock("""
/* user space — after receiving fd from ioctl */
int fd = ioctl(dev_fd, IOCTL_OPEN_LOG, 0);

/* normal file operations work */
char buf[256];
read(fd, buf, sizeof(buf));
lseek(fd, 0, SEEK_SET);
select(fd + 1, &fds, NULL, NULL, &tv);
int fd2 = dup(fd);

/* close() decrements refcount.
   When refcount reaches 0, VFS calls filp_close()
   which calls file_operations.release() */
close(fd);
                    """.trimIndent())
                    BodyText("Full ioctl handler example that opens a file and hands it to user space:")
                    CodeBlock("""
static long my_ioctl(struct file *filp,
                     unsigned int cmd,
                     unsigned long arg)
{
    if (cmd != IOCTL_OPEN_LOG)
        return -EINVAL;

    int fd = get_unused_fd_flags(O_CLOEXEC);
    if (fd < 0) return fd;

    struct file *f = filp_open("/var/log/kern.log",
                               O_RDONLY, 0);
    if (IS_ERR(f)) {
        put_unused_fd(fd);
        return PTR_ERR(f);
    }

    fd_install(fd, f);
    return fd;    /* ioctl() return value = the fd */
}
                    """.trimIndent())
                    BodyText("The user process receives the fd as the return value of ioctl(), then uses read(), close(), etc. normally.")
                }
            }
            item {
                SectionCard("Comparison Table") {
                    CodeBlock("""
Method     | filp_open  | procfs  | sysfs   | debugfs | Netlink
-----------|------------|---------|---------|---------|--------
Direction  | K reads /  | K->U    | K<->U   | K<->U   | both
           | writes FS  | text    | text    | binary  |
Blocking   | yes        | yes     | yes     | yes     | yes
fd_install | yes        | no*     | no*     | no*     | no
Complexity | low API    | medium  | low     | low     | high
Typical    | firmware   | status  | params  | debug   | events
use        | logging    | /proc   | /sys    | /debug  | config

* procfs/sysfs/debugfs fds are obtained by user
  open()ing the path — kernel does not push them.
                    """.trimIndent())
                }
            }
        }
    }
}
