package com.example.linuxapp.screens.permissions

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
fun IoUringScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "io_uring",
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
                SectionCard(title = "What is io_uring?") {
                    BodyText(
                        "io_uring is a high-performance asynchronous I/O interface added to the Linux " +
                        "kernel in version 5.1 (2019), designed by Jens Axboe. The goal: eliminate the " +
                        "per-operation system call overhead that makes traditional async I/O (aio, epoll) " +
                        "expensive at high I/O rates."
                    )
                    BodyText(
                        "The core idea: user and kernel share two lock-free ring buffers in the same " +
                        "physical memory pages. The user writes I/O requests directly into memory; the " +
                        "kernel reads them and writes results back — no data copying, often no syscalls."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            item {
                SectionCard(title = "User Mode, Kernel Mode, or Both?") {
                    BodyText(
                        "io_uring is a user-mode API backed by kernel infrastructure:"
                    )
                    CodeBlock(
                        "User space:\n" +
                        "  - calls io_uring_setup() once to create the ring\n" +
                        "  - mmaps the ring buffers into its address space\n" +
                        "  - writes SQEs and reads CQEs directly (no syscall)\n" +
                        "  - optionally calls io_uring_enter() to wait for completion\n\n" +
                        "Kernel space:\n" +
                        "  - the io_uring subsystem drains the SQ asynchronously\n" +
                        "  - dispatches I/O to block/net/file layers\n" +
                        "  - writes results into the CQ\n\n" +
                        "No kernel module needed. No daemon. Pure kernel + user API."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            item {
                SectionCard(title = "The Ring Buffers") {
                    BodyText(
                        "Two ring buffers are shared between user space and kernel via mmap:"
                    )
                    CodeBlock(
                        "Submission Queue (SQ)\n" +
                        "  User produces →  SQE (Submission Queue Entry)\n" +
                        "  Fields per SQE:\n" +
                        "    opcode    — what to do (IORING_OP_READ, _WRITE,\n" +
                        "                _ACCEPT, _CONNECT, _FSYNC, ...)\n" +
                        "    fd        — file descriptor\n" +
                        "    addr      — user buffer address\n" +
                        "    len       — buffer length\n" +
                        "    off       — file offset\n" +
                        "    user_data — opaque tag echoed back in CQE\n\n" +
                        "Completion Queue (CQ)\n" +
                        "  Kernel produces → CQE (Completion Queue Entry)\n" +
                        "  Fields per CQE:\n" +
                        "    user_data — matches the SQE tag\n" +
                        "    res       — result (bytes read/written, or -errno)\n" +
                        "    flags     — optional metadata"
                    )
                    BodyText(
                        "Both rings are mapped with mmap into user space. Head/tail pointers are " +
                        "atomic variables in the shared memory — no kernel call needed to push or pop."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            item {
                SectionCard(title = "How It Works — Step by Step") {
                    CodeBlock(
                        "// 1. Create the ring (one-time syscall)\n" +
                        "struct io_uring_params params = {};\n" +
                        "int ring_fd = io_uring_setup(256, &params);\n\n" +
                        "// 2. mmap submission and completion rings\n" +
                        "//    (liburing does this for you)\n" +
                        "void *sq_ptr = mmap(..., ring_fd, IORING_OFF_SQ_RING);\n" +
                        "void *cq_ptr = mmap(..., ring_fd, IORING_OFF_CQ_RING);\n" +
                        "struct io_uring_sqe *sqes =\n" +
                        "    mmap(..., ring_fd, IORING_OFF_SQES);\n\n" +
                        "// 3. Fill a Submission Queue Entry\n" +
                        "struct io_uring_sqe *sqe = &sqes[sq_tail & mask];\n" +
                        "sqe->opcode    = IORING_OP_READ;\n" +
                        "sqe->fd        = fd;\n" +
                        "sqe->addr      = (uint64_t)buffer;\n" +
                        "sqe->len       = sizeof(buffer);\n" +
                        "sqe->off       = 0;\n" +
                        "sqe->user_data = my_tag;\n\n" +
                        "// 4. Submit (optional syscall; not needed with SQPOLL)\n" +
                        "io_uring_enter(ring_fd, 1, 1, IORING_ENTER_GETEVENTS);\n\n" +
                        "// 5. Read Completion Queue Entry\n" +
                        "struct io_uring_cqe *cqe = &cq_ring[cq_head & mask];\n" +
                        "ssize_t result = cqe->res;  // bytes read, or -errno"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            item {
                SectionCard(title = "SQPOLL Mode") {
                    BodyText(
                        "With the IORING_SETUP_SQPOLL flag, the kernel spawns a dedicated kernel thread " +
                        "that continuously polls the SQ. User space never needs to call io_uring_enter() " +
                        "for I/O submission — zero syscalls per operation."
                    )
                    CodeBlock(
                        "params.flags = IORING_SETUP_SQPOLL;\n" +
                        "params.sq_thread_idle = 2000; // sleep after 2s idle (ms)\n" +
                        "int ring_fd = io_uring_setup(256, &params);"
                    )
                    BodyText(
                        "Trade-off: the kernel thread burns CPU even when idle (up to sq_thread_idle ms). " +
                        "Ideal for storage engines and network servers with near-continuous I/O. " +
                        "Requires CAP_SYS_NICE or a privileged process."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            item {
                SectionCard(title = "No Daemons Involved") {
                    BodyText(
                        "io_uring creates no background daemons. The SQPOLL thread is a kernel thread " +
                        "(visible as [io_uring-sq] in ps), not a user-space daemon — it lives and dies " +
                        "with the ring file descriptor."
                    )
                    BodyText(
                        "io_uring can fully replace both epoll (event notification) and POSIX AIO " +
                        "(async file I/O) in a single, unified API. Many programs use it alongside " +
                        "or instead of these older mechanisms."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            item {
                SectionCard(title = "Use Cases") {
                    CodeBlock(
                        "High-performance file I/O\n" +
                        "  — backup tools, log writers, copy engines\n\n" +
                        "Database storage engines\n" +
                        "  — RocksDB, ScyllaDB, PostgreSQL (experimental),\n" +
                        "    FoundationDB use io_uring for WAL writes\n\n" +
                        "Network servers\n" +
                        "  — nginx patches, Tokio (Rust async runtime),\n" +
                        "    liburing-based HTTP servers\n\n" +
                        "Event loops\n" +
                        "  — libuv (Node.js) and libev have io_uring backends\n\n" +
                        "io_uring shines when:\n" +
                        "  - you have many concurrent I/O operations\n" +
                        "  - syscall overhead is measurable in your profile\n" +
                        "  - you want to batch network + file I/O in one loop"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            item {
                SectionCard(title = "liburing — The Friendly Wrapper") {
                    BodyText(
                        "liburing wraps the raw syscalls and ring management into a clean C API. " +
                        "Link with -luring."
                    )
                    CodeBlock(
                        "#include <liburing.h>\n\n" +
                        "struct io_uring ring;\n" +
                        "io_uring_queue_init(256, &ring, 0);  // create ring\n\n" +
                        "// Submit a read\n" +
                        "struct io_uring_sqe *sqe = io_uring_get_sqe(&ring);\n" +
                        "io_uring_prep_read(sqe, fd, buf, len, offset);\n" +
                        "io_uring_sqe_set_data(sqe, my_tag);\n" +
                        "io_uring_submit(&ring);\n\n" +
                        "// Wait for completion\n" +
                        "struct io_uring_cqe *cqe;\n" +
                        "io_uring_wait_cqe(&ring, &cqe);\n" +
                        "ssize_t result = cqe->res;\n" +
                        "io_uring_cqe_seen(&ring, cqe);  // advance CQ head\n\n" +
                        "io_uring_queue_exit(&ring);      // cleanup"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
