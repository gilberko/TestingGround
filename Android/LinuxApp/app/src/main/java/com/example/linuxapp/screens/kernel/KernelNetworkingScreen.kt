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
fun KernelNetworkingScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Kernel Networking",
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
                SectionCard("Can Kernel Code Use Sockets?") {
                    BodyText("YES. The kernel provides a socket API for use in kernel modules and drivers. This is the ksocket API, implemented in net/socket.c.")
                    BodyText("Use cases where kernel sockets are appropriate:")
                    CodeBlock("""
- VPN/tunnel drivers that need to send encrypted
  packets on behalf of user space
- NFS client — kernel mounts talk to NFS servers
- Kernel-level telemetry / C2 agents (rare but valid)
- Cluster filesystems (DRBD, Lustre, Ceph)
                    """.trimIndent())
                    BodyText("Two key structs:")
                    CodeBlock("""
struct socket  /* socket layer (VFS side)     */
struct sock    /* TCP/IP protocol layer        */

/* sock_create_kern gives you a struct socket*.
   The struct sock is inside: sock->sk            */
                    """.trimIndent())
                }
            }
            item {
                SectionCard("The ksocket API") {
                    BodyText("Include <net/sock.h> and <linux/net.h>. All functions return 0 on success or a negative errno.")
                    CodeBlock("""
#include <linux/net.h>
#include <net/sock.h>
#include <linux/in.h>

/* Create a kernel socket */
struct socket *sock;
int err = sock_create_kern(&init_net,
                           AF_INET,
                           SOCK_STREAM,
                           IPPROTO_TCP,
                           &sock);
if (err < 0) { /* handle error */ }

/* Connect (TCP) */
struct sockaddr_in addr = {
    .sin_family      = AF_INET,
    .sin_port        = htons(8080),
    .sin_addr.s_addr = in_aton("192.168.1.1"),
};
kernel_connect(sock,
               (struct sockaddr*)&addr,
               sizeof(addr), 0);

/* Send data */
char data[] = "GET / HTTP/1.0\r\n\r\n";
struct kvec  iov  = { data, strlen(data) };
struct msghdr msgh = {};
kernel_sendmsg(sock, &msgh, &iov, 1, strlen(data));

/* Receive data */
char buf[1024];
iov.iov_base = buf; iov.iov_len = sizeof(buf);
int n = kernel_recvmsg(sock, &msgh, &iov, 1,
                       sizeof(buf), 0);

/* Destroy the socket */
sock_release(sock);
                    """.trimIndent())
                    BodyText("sock_create_kern() takes the network namespace as first argument. &init_net is the initial (default) network namespace. Use sock_net(sk) inside protocol callbacks to get the namespace of an existing socket.")
                }
            }
            item {
                SectionCard("Blocking vs Non-Blocking I/O") {
                    BodyText("By default, kernel_sendmsg and kernel_recvmsg are BLOCKING — they sleep until data is available or sent, just like user-space blocking sockets.")
                    BodyText("To make a single call non-blocking, pass MSG_DONTWAIT in the flags argument. It returns -EAGAIN immediately if the operation would block:")
                    CodeBlock("""
int n = kernel_recvmsg(sock, &msgh, &iov, 1,
                       sizeof(buf), MSG_DONTWAIT);
if (n == -EAGAIN) {
    /* no data available right now */
}
                    """.trimIndent())
                    BodyText("To make the socket permanently non-blocking (all calls use EAGAIN semantics), set O_NONBLOCK on the socket's file flags:")
                    CodeBlock("""
sock->file->f_flags |= O_NONBLOCK;
                    """.trimIndent())
                    BodyText("Note: sock->file is only valid if the socket has an associated file (it does by default after sock_create_kern). Non-blocking sockets are best combined with a polling kthread — see next section.")
                }
            }
            item {
                SectionCard("Async Options — No select/poll in Kernel") {
                    BodyText("Kernel code has NO equivalent of select(), poll(), or epoll(). Those are user-space syscalls. Kernel code cannot call them.")
                    BodyText("The correct async pattern for kernel sockets is a dedicated kthread that blocks on kernel_recvmsg():")
                    CodeBlock("""
/* The kthread blocks in kernel_recvmsg.
   When kthread_stop() is called, sock_release()
   unblocks the recv by closing the socket. */

static int recv_thread(void *data)
{
    struct socket *sock = data;
    char buf[512];
    struct kvec iov;
    struct msghdr msg = {};

    while (!kthread_should_stop()) {
        iov.iov_base = buf;
        iov.iov_len  = sizeof(buf);
        int n = kernel_recvmsg(sock, &msg,
                               &iov, 1,
                               sizeof(buf), 0);
        if (n <= 0) break;   /* closed or error */
        /* process data in buf[0..n-1] */
        pr_info("recv %d bytes\n", n);
    }
    return 0;
}
                    """.trimIndent())
                    BodyText("To stop the thread cleanly: call sock_release(sock) first (unblocks the recv), then kthread_stop(thread). Do NOT call kthread_stop() while the thread is sleeping in kernel_recvmsg() without unblocking it first — it will hang.")
                    BodyText("Alternative (less common): use MSG_DONTWAIT + schedule_timeout() loop, but this wastes CPU and delays response. The blocking kthread pattern is preferred.")
                }
            }
            item {
                SectionCard("Full Example: TCP Client kthread") {
                    CodeBlock("""
#include <linux/module.h>
#include <linux/kthread.h>
#include <linux/net.h>
#include <net/sock.h>
#include <linux/in.h>

static struct socket     *g_sock;
static struct task_struct *g_thread;

static int net_thread(void *unused)
{
    char buf[256];
    struct kvec iov  = { buf, sizeof(buf) };
    struct msghdr msg = {};

    while (!kthread_should_stop()) {
        int n = kernel_recvmsg(g_sock, &msg,
                               &iov, 1,
                               sizeof(buf), 0);
        if (n <= 0) break;
        pr_info("recv: %.*s\n", n, buf);
        iov.iov_base = buf;  /* reset for next recv */
        iov.iov_len  = sizeof(buf);
    }
    return 0;
}

static int __init mymod_init(void)
{
    struct sockaddr_in addr = {
        .sin_family      = AF_INET,
        .sin_port        = htons(9000),
        .sin_addr.s_addr = in_aton("127.0.0.1"),
    };

    int err = sock_create_kern(&init_net, AF_INET,
                               SOCK_STREAM,
                               IPPROTO_TCP, &g_sock);
    if (err) return err;

    err = kernel_connect(g_sock,
                         (struct sockaddr*)&addr,
                         sizeof(addr), 0);
    if (err) { sock_release(g_sock); return err; }

    g_thread = kthread_run(net_thread, NULL, "mynet");
    if (IS_ERR(g_thread)) {
        sock_release(g_sock);
        return PTR_ERR(g_thread);
    }
    return 0;
}

static void __exit mymod_exit(void)
{
    sock_release(g_sock);      /* unblocks kernel_recvmsg */
    kthread_stop(g_thread);    /* waits for thread to exit */
}

module_init(mymod_init);
module_exit(mymod_exit);
MODULE_LICENSE("GPL");
                    """.trimIndent())
                }
            }
            item {
                SectionCard("UDP Example") {
                    BodyText("UDP uses SOCK_DGRAM. No connect() needed for sending — set msg_name to the destination address in each sendmsg call:")
                    CodeBlock("""
struct socket *udp;
sock_create_kern(&init_net, AF_INET,
                 SOCK_DGRAM, IPPROTO_UDP, &udp);

char data[] = "ping";
struct kvec iov = { data, sizeof(data) - 1 };

struct sockaddr_in dst = {
    .sin_family      = AF_INET,
    .sin_port        = htons(9999),
    .sin_addr.s_addr = in_aton("127.0.0.1"),
};
struct msghdr msg = {
    .msg_name    = &dst,
    .msg_namelen = sizeof(dst),
};
kernel_sendmsg(udp, &msg, &iov, 1,
               iov.iov_len);

/* receive reply — msg_name gets filled with sender */
char reply[256];
iov.iov_base = reply; iov.iov_len = sizeof(reply);
msg.msg_name    = &dst;
msg.msg_namelen = sizeof(dst);
kernel_recvmsg(udp, &msg, &iov, 1,
               sizeof(reply), 0);

sock_release(udp);
                    """.trimIndent())
                }
            }
        }
    }
}
