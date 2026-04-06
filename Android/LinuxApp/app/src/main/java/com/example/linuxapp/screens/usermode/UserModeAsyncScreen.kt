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
fun UserModeAsyncScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Async Operations",
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
                SectionCard(title = "What Is Async I/O?") {
                    BodyText("By default, I/O calls like read() and write() are blocking — the calling thread stops and waits until the operation completes. For programs that handle many connections or files simultaneously, this is a problem: one slow operation blocks everything.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Async (asynchronous) I/O lets a program start an operation and continue executing while it completes in the background. The program is notified — via a return value, signal, callback, or shared ring buffer — when the result is ready.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Linux has several async I/O mechanisms, added at different points in its history, each with different trade-offs:")
                    CodeBlock("""select() / poll()   — POSIX, always available
epoll()             — Linux 2.5.44 (2002)
POSIX AIO           — glibc userspace threads
io_uring            — Linux 5.1 (May 2019)""")
                }
            }
            item {
                SectionCard(title = "select() and poll() — The Originals") {
                    BodyText("select() and poll() are the oldest async-style APIs, available on all POSIX systems. They don't do async I/O themselves — they watch a set of file descriptors and tell you which ones are ready to read or write without blocking.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("select() usage:")
                    CodeBlock("""fd_set readfds;
FD_ZERO(&readfds);
FD_SET(sockfd, &readfds);

struct timeval timeout = { .tv_sec = 5 };
int ready = select(sockfd + 1, &readfds, NULL, NULL, &timeout);
if (ready > 0 && FD_ISSET(sockfd, &readfds)) {
    read(sockfd, buf, sizeof(buf));  // won't block
}""")
                    BodyText("poll() is similar but uses a struct pollfd array instead of fd_set, removing select()'s limit of 1024 file descriptors:")
                    CodeBlock("""struct pollfd fds[2];
fds[0].fd = sockfd;
fds[0].events = POLLIN;

int ready = poll(fds, 1, 5000);  // 5000ms timeout
if (fds[0].revents & POLLIN) {
    read(sockfd, buf, sizeof(buf));
}""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Limitations: Both scan every fd on every call — O(n) per call. With thousands of connections this is very slow. The kernel must copy fd sets back and forth on every syscall.")
                }
            }
            item {
                SectionCard(title = "epoll() — Scalable Event Notification (Linux 2.5.44, 2002)") {
                    BodyText("epoll was added in Linux 2.5.44 (released 2002) to solve the scalability problem of select/poll. Instead of scanning all fds every time, the kernel maintains a persistent interest list. You register fds once and ask for events efficiently.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The three key calls:")
                    CodeBlock("""// Create an epoll instance (returns an fd)
int epfd = epoll_create1(0);

// Register interest in an fd
struct epoll_event ev;
ev.events = EPOLLIN;        // watch for readability
ev.data.fd = sockfd;
epoll_ctl(epfd, EPOLL_CTL_ADD, sockfd, &ev);

// Wait for events (returns count of ready fds)
struct epoll_event events[MAX_EVENTS];
int n = epoll_wait(epfd, events, MAX_EVENTS, -1);
for (int i = 0; i < n; i++) {
    int fd = events[i].data.fd;
    read(fd, buf, sizeof(buf));
}""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Level-triggered (default): epoll notifies you as long as the fd has unread data. Edge-triggered (EPOLLET): notifies only when state changes (new data arrives). Edge-triggered requires reading until EAGAIN to avoid missing events.")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("epoll scales to tens of thousands of connections with O(1) per-event cost. It is the backbone of high-performance servers (nginx, Node.js, etc.).")
                }
            }
            item {
                SectionCard(title = "POSIX AIO — Asynchronous File I/O") {
                    BodyText("POSIX AIO (aio_*) provides true asynchronous reads and writes — you submit an operation and get notified when it completes, without blocking at all. It targets file I/O specifically.")
                    CodeBlock("""#include <aio.h>

struct aiocb cb = {0};
cb.aio_fildes = fd;
cb.aio_buf    = buffer;
cb.aio_nbytes = sizeof(buffer);
cb.aio_offset = 0;

// Start async read — returns immediately
aio_read(&cb);

// ... do other work ...

// Check for completion
while (aio_error(&cb) == EINPROGRESS) {
    // still in progress
}

ssize_t bytes = aio_return(&cb);  // get result""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Notification options: SIGEV_NONE (poll manually), SIGEV_SIGNAL (deliver a signal when done), SIGEV_THREAD (call a callback in a new thread).")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Limitation: On Linux, POSIX AIO is implemented in glibc using userspace threads — each in-flight request typically consumes a kernel thread. Under heavy load this doesn't scale well and adds overhead.")
                }
            }
            item {
                SectionCard(title = "io_uring — The Modern Way (Linux 5.1, May 2019)") {
                    BodyText("io_uring was merged in Linux 5.1 (May 2019) and is the most powerful async I/O interface Linux has ever had. It was designed by Jens Axboe (the block layer maintainer) to be truly zero-copy and near-zero-syscall.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The core idea: two ring buffers shared between user space and kernel space.")
                    CodeBlock("""Submission Queue (SQ):  user writes requests here
Completion Queue (CQ):  kernel writes results here

Both are memory-mapped — no copying, no syscall needed
to submit or collect most operations.""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Using the liburing wrapper (simplest approach):")
                    CodeBlock("""#include <liburing.h>

struct io_uring ring;
io_uring_queue_init(32, &ring, 0);  // queue depth 32

// Prepare a read
struct io_uring_sqe *sqe = io_uring_get_sqe(&ring);
io_uring_prep_read(sqe, fd, buf, sizeof(buf), 0);
sqe->user_data = 42;  // tag to identify completion

// Submit (one syscall for many ops)
io_uring_submit(&ring);

// ... do other work ...

// Wait for completion
struct io_uring_cqe *cqe;
io_uring_wait_cqe(&ring, &cqe);
int result = cqe->res;         // bytes read, or -errno
io_uring_cqe_seen(&ring, cqe); // mark consumed

io_uring_queue_exit(&ring);""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Advanced features:")
                    CodeBlock("""IORING_SETUP_SQPOLL  — kernel thread polls SQ; zero syscalls
Fixed buffers        — register buffers once, reuse without copying
Registered files     — avoid fd table lookups per operation
Linked requests      — chain ops so B runs only after A completes
Multishot            — one sqe generates multiple cqe results""")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("io_uring supports not just file I/O but also sockets, timers, splice, send/recv, accept, connect, and more — making it a general-purpose async syscall interface.")
                }
            }
            item {
                SectionCard(title = "When to Use Which") {
                    BodyText("Choosing the right API depends on your portability needs, kernel version, and workload:")
                    CodeBlock("""select() / poll()
  Use when: portability to non-Linux systems matters
  Avoid: > a few hundred fds (O(n) scan kills performance)

epoll()
  Use when: handling many concurrent network connections
  The right tool for: HTTP servers, proxies, event loops
  Minimum kernel: 2.5.44 (2002) — always available today

POSIX AIO (aio_*)
  Use when: writing portable async file I/O code
  Avoid on Linux: glibc userspace threads don't scale well
  Better alternative: io_uring for Linux-only targets

io_uring
  Use when: maximum throughput on modern Linux (5.1+)
  Best for: storage-intensive apps, high-frequency trading,
            database engines, anything batching many syscalls
  Requires: Linux 5.1+ for basic use; 5.6+ for full power
  Wrapper:  liburing (recommended over raw syscall)""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("For new Linux-only code that needs high I/O performance, io_uring is the answer. For portable network servers, epoll remains the standard. select/poll are legacy but still useful for simple, low-volume I/O monitoring.")
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
