package com.example.linuxapp.screens.usermode

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
import com.example.linuxapp.screens.kernel.BodyText
import com.example.linuxapp.screens.kernel.CodeBlock
import com.example.linuxapp.screens.kernel.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserModeCommunicatingWithKernelScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Communicating With Kernel",
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
                SectionCard("Opening a Device") {
                    BodyText("User space accesses kernel drivers through device files under /dev. Open one with the standard open() syscall:")
                    CodeBlock("""
int fd = open("/dev/mydevice", O_RDWR);
if (fd < 0) {
    perror("open");   /* check errno */
    return -1;
}
                    """.trimIndent())
                    BodyText("Common flags:")
                    CodeBlock("""
O_RDONLY   /* read only */
O_WRONLY   /* write only */
O_RDWR     /* read + write */
O_NONBLOCK /* non-blocking I/O (driver must support) */
                    """.trimIndent())
                    BodyText("On the kernel side, open() triggers file_operations.open(). The driver can reject the open, allocate private state, and store it in filp->private_data.")
                }
            }
            item {
                SectionCard("Closing a Device") {
                    BodyText("Always close the file descriptor when done. This decrements the kernel refcount and triggers file_operations.release().")
                    CodeBlock("close(fd);")
                    BodyText("If you forget to close, the driver's release() is not called until the process exits. This can prevent the driver from freeing hardware resources or resetting state.")
                }
            }
            item {
                SectionCard("Sending IOCTLs") {
                    BodyText("ioctl() sends a command code and optional argument to the driver. It is the standard way to perform device-specific control operations that don't fit read/write semantics.")
                    CodeBlock("int ret = ioctl(fd, IOCTL_CMD, arg);")
                    BodyText("Commands are defined using macros from <linux/ioctl.h> (shared between kernel and user headers):")
                    CodeBlock("""
/* _IO(type, nr)          — no data transfer */
/* _IOR(type, nr, size)   — kernel → user */
/* _IOW(type, nr, size)   — user → kernel */
/* _IOWR(type, nr, size)  — bidirectional */

#define MY_MAGIC   'k'
#define IOCTL_RESET    _IO (MY_MAGIC, 0)
#define IOCTL_SET_VAL  _IOW(MY_MAGIC, 1, int)
#define IOCTL_GET_VAL  _IOR(MY_MAGIC, 2, int)
                    """.trimIndent())
                    BodyText("User space example:")
                    CodeBlock("""
int fd = open("/dev/mydevice", O_RDWR);

ioctl(fd, IOCTL_RESET);          /* no arg */

int val = 42;
ioctl(fd, IOCTL_SET_VAL, &val);  /* write to driver */

ioctl(fd, IOCTL_GET_VAL, &val);  /* read from driver */
printf("got %d\n", val);

close(fd);
                    """.trimIndent())
                    BodyText("On the kernel side, the driver handles this in file_operations.unlocked_ioctl(). The 'type' byte distinguishes your driver's commands from others and the 'nr' byte is the command number within that driver.")
                }
            }
            item {
                SectionCard("Netlink: Request / Response") {
                    BodyText("Netlink is a socket-based IPC mechanism between user space and the kernel (and between user processes). It supports both request/response and asynchronous notifications.")
                    BodyText("For request/response: open an AF_NETLINK socket, bind it with your own PID, build an nlmsghdr message with NLM_F_REQUEST, sendmsg() to the kernel (nl_pid = 0), then recvmsg() to get the reply.")
                    CodeBlock("""
#include <linux/netlink.h>
#define NETLINK_USER 31   /* custom protocol number */

int fd = socket(AF_NETLINK, SOCK_RAW, NETLINK_USER);

struct sockaddr_nl src = {
    .nl_family = AF_NETLINK,
    .nl_pid    = getpid()
};
bind(fd, (struct sockaddr*)&src, sizeof(src));

/* build request */
char buf[NLMSG_SPACE(256)] = {};
struct nlmsghdr *nlh = (struct nlmsghdr*)buf;
nlh->nlmsg_len   = NLMSG_SPACE(256);
nlh->nlmsg_type  = 0;
nlh->nlmsg_flags = NLM_F_REQUEST;
nlh->nlmsg_pid   = getpid();
memcpy(NLMSG_DATA(nlh), "hello kernel", 12);

/* send to kernel (nl_pid = 0) */
struct sockaddr_nl dst = { .nl_family = AF_NETLINK };
struct iovec  iov = { buf, nlh->nlmsg_len };
struct msghdr msg = { &dst, sizeof(dst), &iov, 1, NULL, 0, 0 };
sendmsg(fd, &msg, 0);

/* receive reply */
memset(buf, 0, sizeof(buf));
iov.iov_base = buf; iov.iov_len = sizeof(buf);
recvmsg(fd, &msg, 0);
printf("reply: %s\n", (char*)NLMSG_DATA(nlh));

close(fd);
                    """.trimIndent())
                    BodyText("The kernel side registers a callback with netlink_kernel_create(). When it receives the message it calls nlmsg_unicast() to send the reply back to the sender's nl_pid.")
                }
            }
            item {
                SectionCard("Netlink: Async Notifications") {
                    BodyText("YES — Netlink supports async kernel-to-user push via multicast groups. The kernel calls netlink_broadcast() to send to all listeners on a group. User space subscribes by binding with nl_groups set, or using setsockopt(NETLINK_ADD_MEMBERSHIP).")
                    BodyText("Example: monitor network interface up/down events using NETLINK_ROUTE:")
                    CodeBlock("""
#include <linux/rtnetlink.h>

int fd = socket(AF_NETLINK, SOCK_RAW, NETLINK_ROUTE);

struct sockaddr_nl addr = {
    .nl_family = AF_NETLINK,
    .nl_groups = RTMGRP_LINK   /* interface events */
};
bind(fd, (struct sockaddr*)&addr, sizeof(addr));

char buf[4096];
for (;;) {
    int n = recv(fd, buf, sizeof(buf), 0);
    struct nlmsghdr *nlh = (struct nlmsghdr*)buf;
    for (; NLMSG_OK(nlh, n); nlh = NLMSG_NEXT(nlh, n)) {
        if (nlh->nlmsg_type == NLMSG_DONE) break;
        if (nlh->nlmsg_type == RTM_NEWLINK)
            printf("interface up/changed\n");
        if (nlh->nlmsg_type == RTM_DELLINK)
            printf("interface removed\n");
    }
}
close(fd);
                    """.trimIndent())
                    BodyText("For custom kernel modules, the kernel calls netlink_broadcast(nl_sock, skb, 0, group, GFP_KERNEL) to push to all listeners on that group number. Listeners join via setsockopt(fd, SOL_NETLINK, NETLINK_ADD_MEMBERSHIP, &group, sizeof(group)).")
                }
            }
            item {
                SectionCard("Comparison: open/ioctl vs Netlink") {
                    CodeBlock("""
Feature         | open/ioctl         | Netlink
----------------|--------------------|-----------------
Direction       | bidirectional      | bidirectional
Structured data | your struct        | nlmsghdr + attrs
Async notif.    | no (poll on fd)    | yes (multicast)
Multicast       | no                 | yes (groups)
Complexity      | low                | medium
Typical use     | device control     | routing, events
                | hardware I/O       | network config
Requires /dev   | yes                | no
                    """.trimIndent())
                    BodyText("Rule of thumb: use open/ioctl when interacting with a device driver that already exposes a /dev node. Use Netlink when you need structured messages, multicast notifications, or communication without a physical device (e.g. custom kernel module exporting status or receiving commands).")
                }
            }
        }
    }
}
