package com.example.linuxapp.screens.kernel

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NetlinkScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Netlink",
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
                SectionCard(title = "What Is Netlink") {
                    BodyText(
                        "Netlink is a socket-based IPC mechanism for communication between the kernel and user-space " +
                        "processes. It uses the AF_NETLINK address family with SOCK_RAW sockets — not TCP/UDP — and " +
                        "bypasses the normal network stack entirely, going through a dedicated kernel path."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Many core Linux tools use Netlink internally: iproute2 (ip, ss), iptables/nftables, udev " +
                        "(kernel→udev device events), NetworkManager, and others."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Common protocol families (the third argument to socket()):")
                    Spacer(modifier = Modifier.height(6.dp))
                    CodeBlock(
                        "NETLINK_ROUTE          // routing table, interfaces, addresses\n" +
                        "NETLINK_NETFILTER      // iptables/nftables\n" +
                        "NETLINK_KOBJECT_UEVENT // kernel→udev device hotplug events\n" +
                        "NETLINK_AUDIT          // audit subsystem\n" +
                        "NETLINK_GENERIC        // extensible; recommended for custom use\n" +
                        "17–31                  // reserved for custom / out-of-tree use"
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(12.dp))
                SectionCard(title = "Request-Response vs. Async (Bus)") {
                    BodyText(
                        "Netlink supports both communication styles simultaneously — the same socket family can be " +
                        "used for synchronous request-response AND for asynchronous event broadcasting."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Request-response (like ioctl, but richer):")
                    Spacer(modifier = Modifier.height(6.dp))
                    CodeBlock(
                        "User sets NLM_F_REQUEST in nlmsg_flags and sends to kernel.\n" +
                        "Kernel's .input callback fires, processes the request,\n" +
                        "and calls netlink_unicast() back to the sender.\n" +
                        "NLM_F_ACK requests an explicit acknowledgement (NLMSG_ERROR\n" +
                        "with error=0 on success)."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Async / unsolicited push (the bus model):")
                    Spacer(modifier = Modifier.height(6.dp))
                    CodeBlock(
                        "Kernel calls netlink_broadcast() to a multicast group ID.\n" +
                        "All user-space processes subscribed to that group receive\n" +
                        "the message with no prior request.\n\n" +
                        "Example: a routing change fires RTM_NEWROUTE to the\n" +
                        "RTNLGRP_ROUTE group. Every app that called setsockopt\n" +
                        "(NETLINK_ADD_MEMBERSHIP, RTNLGRP_ROUTE) is notified.\n" +
                        "udev receives UEVENT hotplug messages this way."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Messages without NLM_F_REQUEST are treated as notifications. The distinction is " +
                        "purely by convention — the kernel decides whether to reply."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(12.dp))
                SectionCard(title = "User-to-User Communication") {
                    BodyText(
                        "Yes — two user-space processes can exchange Netlink messages directly. Each process binds " +
                        "an AF_NETLINK socket with its own port ID (nl_pid, typically getpid()). Process A then " +
                        "sets dest_addr.nl_pid = B's PID and calls sendmsg(). The kernel routes the packet to B " +
                        "without examining its contents."
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    CodeBlock(
                        "// Process A: bind with own PID\n" +
                        "struct sockaddr_nl my_addr = {\n" +
                        "    .nl_family = AF_NETLINK,\n" +
                        "    .nl_pid    = getpid(),\n" +
                        "};\n" +
                        "bind(fd, (struct sockaddr *)&my_addr, sizeof(my_addr));\n\n" +
                        "// Process B: address to A\n" +
                        "struct sockaddr_nl dest = {\n" +
                        "    .nl_family = AF_NETLINK,\n" +
                        "    .nl_pid    = pid_of_A,   // must know A's PID\n" +
                        "};\n" +
                        "sendmsg(fd, &msg, 0);"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "In practice, UNIX domain sockets (AF_UNIX) are the better choice for pure user↔user IPC — " +
                        "simpler, no PID management needed, and designed for that purpose. Netlink's advantage is " +
                        "specifically kernel↔user communication."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(12.dp))
                SectionCard(title = "Kernel-to-Kernel") {
                    BodyText(
                        "Netlink is not used for kernel-to-kernel communication. Kernel subsystems talk to each " +
                        "other directly through:"
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    CodeBlock(
                        "Direct function calls      // most common — just call the function\n" +
                        "Notifier chains            // blocking_notifier_call_chain(),\n" +
                        "                           //   atomic_notifier_call_chain()\n" +
                        "Work queues / kthreads     // deferred or async processing\n" +
                        "Shared data structures     // protected by spinlocks / mutexes\n" +
                        "Kernel events / uevent     // for communicating state to udev,\n" +
                        "                           //   but that crosses into user space"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "You could technically call netlink_unicast() targeting nl_pid = 0 from one kernel module " +
                        "to another, but there is no receiver registered at pid=0 for arbitrary protocol numbers, " +
                        "and it would be unnecessary overhead — just call the other module's exported function."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(12.dp))
                SectionCard(title = "Core Structures") {
                    BodyText("Every Netlink message starts with struct nlmsghdr:")
                    Spacer(modifier = Modifier.height(6.dp))
                    CodeBlock(
                        "struct nlmsghdr {\n" +
                        "    __u32 nlmsg_len;    // total length including header\n" +
                        "    __u16 nlmsg_type;   // message type (e.g. RTM_NEWROUTE,\n" +
                        "                        //   NLMSG_DONE, NLMSG_ERROR)\n" +
                        "    __u16 nlmsg_flags;  // NLM_F_REQUEST, NLM_F_ACK,\n" +
                        "                        //   NLM_F_DUMP, NLM_F_MULTI ...\n" +
                        "    __u32 nlmsg_seq;    // sequence number (for matching replies)\n" +
                        "    __u32 nlmsg_pid;    // sending port ID (0 = kernel)\n" +
                        "};"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Address structure used for bind() and sendmsg():")
                    Spacer(modifier = Modifier.height(6.dp))
                    CodeBlock(
                        "struct sockaddr_nl {\n" +
                        "    sa_family_t nl_family;  // AF_NETLINK\n" +
                        "    unsigned short nl_pad;  // zero\n" +
                        "    pid_t nl_pid;           // port ID: 0=kernel, getpid()=user\n" +
                        "    __u32 nl_groups;        // multicast group bitmask\n" +
                        "};"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Message navigation macros (from <linux/netlink.h>):")
                    Spacer(modifier = Modifier.height(6.dp))
                    CodeBlock(
                        "NLMSG_DATA(nlh)         // pointer to payload after header\n" +
                        "NLMSG_LENGTH(len)       // header size + len (unaligned)\n" +
                        "NLMSG_SPACE(len)        // header size + len (aligned to 4B)\n" +
                        "NLMSG_NEXT(nlh, rem)    // advance to next message in buffer\n" +
                        "NLMSG_OK(nlh, rem)      // check if message is valid in buffer\n" +
                        "NLMSG_HDRLEN            // sizeof(struct nlmsghdr) aligned"
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(12.dp))
                SectionCard(title = "Kernel API") {
                    BodyText("Creating and destroying a kernel Netlink socket:")
                    Spacer(modifier = Modifier.height(6.dp))
                    CodeBlock(
                        "// Registration — usually in module_init\n" +
                        "struct netlink_kernel_cfg cfg = {\n" +
                        "    .input  = my_recv_callback,  // called on incoming message\n" +
                        "    .groups = 16,                // number of multicast groups\n" +
                        "};\n" +
                        "struct sock *nl_sk =\n" +
                        "    netlink_kernel_create(&init_net, NETLINK_USER, &cfg);\n" +
                        "if (!nl_sk) return -ENOMEM;\n\n" +
                        "// Cleanup — in module_exit\n" +
                        "netlink_kernel_release(nl_sk);"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Sending messages from kernel to user space:")
                    Spacer(modifier = Modifier.height(6.dp))
                    CodeBlock(
                        "// Unicast: to one specific user-space process\n" +
                        "struct sk_buff *skb = nlmsg_new(payload_size, GFP_KERNEL);\n" +
                        "struct nlmsghdr *nlh = nlmsg_put(\n" +
                        "    skb,\n" +
                        "    0,            // portid of sender (0 = kernel)\n" +
                        "    seq,          // sequence number\n" +
                        "    NLMSG_DONE,   // message type\n" +
                        "    payload_size, // payload length\n" +
                        "    0             // flags\n" +
                        ");\n" +
                        "memcpy(nlmsg_data(nlh), my_data, payload_size);\n" +
                        "nlmsg_end(skb, nlh);\n" +
                        "netlink_unicast(nl_sk, skb, target_portid, MSG_DONTWAIT);\n\n" +
                        "// Multicast: to all subscribers of a group\n" +
                        "netlink_broadcast(nl_sk, skb, 0, group_bitmask, GFP_KERNEL);"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Receiving messages in the kernel callback:")
                    Spacer(modifier = Modifier.height(6.dp))
                    CodeBlock(
                        "static void my_recv_callback(struct sk_buff *skb) {\n" +
                        "    struct nlmsghdr *nlh = nlmsg_hdr(skb);\n" +
                        "    u32 sender_pid = NETLINK_CB(skb).portid;\n" +
                        "    void *payload  = nlmsg_data(nlh);\n" +
                        "    int  len       = nlmsg_len(nlh);\n" +
                        "    // process payload, optionally reply via\n" +
                        "    // netlink_unicast() back to sender_pid\n" +
                        "}"
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(12.dp))
                SectionCard(title = "Atomic Context") {
                    BodyText(
                        "Sending Netlink messages from atomic context (interrupt handlers, softirqs, or code " +
                        "holding a spinlock) is not safe and not recommended."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "The two main reasons:"
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    CodeBlock(
                        "1. nlmsg_new(size, GFP_KERNEL) may sleep — forbidden\n" +
                        "   in atomic context. Switching to GFP_ATOMIC avoids\n" +
                        "   sleeping but can fail under memory pressure.\n\n" +
                        "2. netlink_unicast() / netlink_broadcast() can block\n" +
                        "   if the receiver's socket buffer is full. Passing\n" +
                        "   MSG_DONTWAIT avoids blocking, but then the message\n" +
                        "   is silently dropped (returns -EAGAIN) with no retry."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "So even with GFP_ATOMIC + MSG_DONTWAIT, you only get best-effort delivery — messages " +
                        "can disappear without warning when the system is under load. For kernel-originated " +
                        "notifications this is usually unacceptable."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The correct pattern is to defer the send:")
                    Spacer(modifier = Modifier.height(6.dp))
                    CodeBlock(
                        "// In your interrupt / atomic context:\n" +
                        "// 1. Store the data in a lock-free ring buffer or\n" +
                        "//    a work_struct payload.\n" +
                        "// 2. Schedule a work item:\n" +
                        "schedule_work(&my_netlink_work);\n\n" +
                        "// In the work queue handler (process context — safe):\n" +
                        "static void my_netlink_work_fn(struct work_struct *w)\n" +
                        "{\n" +
                        "    struct sk_buff *skb = nlmsg_new(len, GFP_KERNEL);\n" +
                        "    // fill and send normally\n" +
                        "    netlink_unicast(nl_sk, skb, target_pid, 0);\n" +
                        "}"
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(12.dp))
                SectionCard(title = "User Mode API") {
                    BodyText("Opening, binding, and sending to the kernel:")
                    Spacer(modifier = Modifier.height(6.dp))
                    CodeBlock(
                        "#include <sys/socket.h>\n" +
                        "#include <linux/netlink.h>\n\n" +
                        "// Open\n" +
                        "int fd = socket(AF_NETLINK, SOCK_RAW, NETLINK_USER);\n\n" +
                        "// Bind with our port ID\n" +
                        "struct sockaddr_nl src = {\n" +
                        "    .nl_family = AF_NETLINK,\n" +
                        "    .nl_pid    = getpid(),\n" +
                        "    .nl_groups = 0,\n" +
                        "};\n" +
                        "bind(fd, (struct sockaddr *)&src, sizeof(src));\n\n" +
                        "// Destination: kernel\n" +
                        "struct sockaddr_nl dst = {\n" +
                        "    .nl_family = AF_NETLINK,\n" +
                        "    .nl_pid    = 0,   // 0 = kernel\n" +
                        "    .nl_groups = 0,\n" +
                        "};\n\n" +
                        "// Build message\n" +
                        "char buf[NLMSG_SPACE(64)];\n" +
                        "struct nlmsghdr *nlh = (struct nlmsghdr *)buf;\n" +
                        "nlh->nlmsg_len   = NLMSG_SPACE(64);\n" +
                        "nlh->nlmsg_type  = 0;\n" +
                        "nlh->nlmsg_flags = NLM_F_REQUEST;\n" +
                        "nlh->nlmsg_seq   = 1;\n" +
                        "nlh->nlmsg_pid   = getpid();\n" +
                        "strcpy(NLMSG_DATA(nlh), \"Hello kernel!\");\n\n" +
                        "struct iovec  iov = { buf, nlh->nlmsg_len };\n" +
                        "struct msghdr msg = {\n" +
                        "    .msg_name    = &dst,\n" +
                        "    .msg_namelen = sizeof(dst),\n" +
                        "    .msg_iov     = &iov,\n" +
                        "    .msg_iovlen  = 1,\n" +
                        "};\n" +
                        "sendmsg(fd, &msg, 0);\n\n" +
                        "// Receive reply\n" +
                        "memset(buf, 0, sizeof(buf));\n" +
                        "iov.iov_base = buf; iov.iov_len = sizeof(buf);\n" +
                        "recvmsg(fd, &msg, 0);\n" +
                        "printf(\"Reply: %s\\n\", (char *)NLMSG_DATA(nlh));\n\n" +
                        "close(fd);"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Subscribing to multicast groups (async events from kernel):")
                    Spacer(modifier = Modifier.height(6.dp))
                    CodeBlock(
                        "// Set nl_groups in sockaddr_nl before bind, OR use setsockopt\n" +
                        "// after bind (preferred — lets you join/leave at any time):\n" +
                        "int group = RTNLGRP_ROUTE;  // or your custom group ID\n" +
                        "setsockopt(fd, SOL_NETLINK, NETLINK_ADD_MEMBERSHIP,\n" +
                        "           &group, sizeof(group));\n\n" +
                        "// To leave a group:\n" +
                        "setsockopt(fd, SOL_NETLINK, NETLINK_DROP_MEMBERSHIP,\n" +
                        "           &group, sizeof(group));\n\n" +
                        "// Then just recvmsg() in a loop — messages arrive\n" +
                        "// whenever the kernel broadcasts to that group."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(12.dp))
                SectionCard(title = "Full Kernel Module Example") {
                    BodyText(
                        "A minimal LKM that receives a string from user space and replies with a fixed message. " +
                        "Uses protocol number 31 (NETLINK_USER — available for custom use)."
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    CodeBlock(
                        "// netlink_demo.c\n" +
                        "#include <linux/module.h>\n" +
                        "#include <linux/netlink.h>\n" +
                        "#include <linux/skbuff.h>\n" +
                        "#include <net/sock.h>\n\n" +
                        "#define NETLINK_USER 31\n\n" +
                        "static struct sock *nl_sk;\n\n" +
                        "static void nl_recv_msg(struct sk_buff *skb)\n" +
                        "{\n" +
                        "    struct nlmsghdr *nlh = nlmsg_hdr(skb);\n" +
                        "    u32 pid = NETLINK_CB(skb).portid;\n" +
                        "    char *msg = (char *)nlmsg_data(nlh);\n\n" +
                        "    pr_info(\"netlink: from pid %u: %s\\n\", pid, msg);\n\n" +
                        "    // Build reply\n" +
                        "    const char *reply = \"Hello from kernel!\";\n" +
                        "    size_t rlen = strlen(reply) + 1;\n" +
                        "    struct sk_buff *skb_out = nlmsg_new(rlen, GFP_KERNEL);\n" +
                        "    if (!skb_out) return;\n\n" +
                        "    struct nlmsghdr *nlh_out =\n" +
                        "        nlmsg_put(skb_out, 0, nlh->nlmsg_seq,\n" +
                        "                  NLMSG_DONE, rlen, 0);\n" +
                        "    NETLINK_CB(skb_out).dst_group = 0;\n" +
                        "    memcpy(nlmsg_data(nlh_out), reply, rlen);\n" +
                        "    nlmsg_end(skb_out, nlh_out);\n\n" +
                        "    if (nlmsg_unicast(nl_sk, skb_out, pid) < 0)\n" +
                        "        pr_err(\"netlink: unicast failed\\n\");\n" +
                        "}\n\n" +
                        "static int __init nl_init(void)\n" +
                        "{\n" +
                        "    struct netlink_kernel_cfg cfg = { .input = nl_recv_msg };\n" +
                        "    nl_sk = netlink_kernel_create(&init_net, NETLINK_USER, &cfg);\n" +
                        "    return nl_sk ? 0 : -ENOMEM;\n" +
                        "}\n\n" +
                        "static void __exit nl_exit(void)\n" +
                        "{\n" +
                        "    netlink_kernel_release(nl_sk);\n" +
                        "}\n\n" +
                        "module_init(nl_init);\n" +
                        "module_exit(nl_exit);\n" +
                        "MODULE_LICENSE(\"GPL\");"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Makefile:")
                    Spacer(modifier = Modifier.height(6.dp))
                    CodeBlock(
                        "obj-m += netlink_demo.o\n\n" +
                        "all:\n" +
                        "\tmake -C /lib/modules/\$(shell uname -r)/build M=\$(PWD) modules\n" +
                        "clean:\n" +
                        "\tmake -C /lib/modules/\$(shell uname -r)/build M=\$(PWD) clean"
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(12.dp))
                SectionCard(title = "Full User Mode Example") {
                    BodyText(
                        "C program that sends a message to the kernel module above and prints the reply."
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    CodeBlock(
                        "// nl_user.c\n" +
                        "#include <stdio.h>\n" +
                        "#include <string.h>\n" +
                        "#include <unistd.h>\n" +
                        "#include <sys/socket.h>\n" +
                        "#include <linux/netlink.h>\n\n" +
                        "#define NETLINK_USER 31\n" +
                        "#define MSG \"Hello from user space!\"\n\n" +
                        "int main(void)\n" +
                        "{\n" +
                        "    int fd = socket(AF_NETLINK, SOCK_RAW, NETLINK_USER);\n" +
                        "    if (fd < 0) { perror(\"socket\"); return 1; }\n\n" +
                        "    struct sockaddr_nl src = {\n" +
                        "        .nl_family = AF_NETLINK,\n" +
                        "        .nl_pid    = getpid(),\n" +
                        "    };\n" +
                        "    bind(fd, (struct sockaddr *)&src, sizeof(src));\n\n" +
                        "    // Allocate buffer: header + payload\n" +
                        "    size_t mlen = NLMSG_SPACE(sizeof(MSG));\n" +
                        "    char buf[mlen];\n" +
                        "    memset(buf, 0, mlen);\n\n" +
                        "    struct nlmsghdr *nlh = (struct nlmsghdr *)buf;\n" +
                        "    nlh->nlmsg_len   = mlen;\n" +
                        "    nlh->nlmsg_type  = 0;\n" +
                        "    nlh->nlmsg_flags = NLM_F_REQUEST;\n" +
                        "    nlh->nlmsg_seq   = 1;\n" +
                        "    nlh->nlmsg_pid   = getpid();\n" +
                        "    memcpy(NLMSG_DATA(nlh), MSG, sizeof(MSG));\n\n" +
                        "    struct sockaddr_nl dst = {\n" +
                        "        .nl_family = AF_NETLINK,\n" +
                        "        .nl_pid    = 0,\n" +
                        "    };\n" +
                        "    struct iovec  iov = { buf, mlen };\n" +
                        "    struct msghdr msg = {\n" +
                        "        .msg_name    = &dst,\n" +
                        "        .msg_namelen = sizeof(dst),\n" +
                        "        .msg_iov     = &iov,\n" +
                        "        .msg_iovlen  = 1,\n" +
                        "    };\n" +
                        "    sendmsg(fd, &msg, 0);\n" +
                        "    printf(\"Sent: %s\\n\", MSG);\n\n" +
                        "    // Receive reply\n" +
                        "    char rbuf[NLMSG_SPACE(256)];\n" +
                        "    memset(rbuf, 0, sizeof(rbuf));\n" +
                        "    struct iovec riov = { rbuf, sizeof(rbuf) };\n" +
                        "    msg.msg_iov     = &riov;\n" +
                        "    msg.msg_iovlen  = 1;\n" +
                        "    recvmsg(fd, &msg, 0);\n" +
                        "    printf(\"Received: %s\\n\",\n" +
                        "        (char *)NLMSG_DATA((struct nlmsghdr *)rbuf));\n\n" +
                        "    close(fd);\n" +
                        "    return 0;\n" +
                        "}\n\n" +
                        "// Build: gcc -o nl_user nl_user.c\n" +
                        "// Run:   sudo insmod netlink_demo.ko && ./nl_user\n" +
                        "// Output:\n" +
                        "//   Sent: Hello from user space!\n" +
                        "//   Received: Hello from kernel!"
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(12.dp))
                SectionCard(title = "Comparison: Netlink vs Other Kernel↔User IPC") {
                    CodeBlock(
                        "Mechanism        Direction         Async  Mcast  K→U push  Structured\n" +
                        "───────────────────────────────────────────────────────────────────────\n" +
                        "Netlink          Kernel↔User       Yes    Yes    Yes        Yes\n" +
                        "                 User↔User\n" +
                        "ioctl            User→Kernel        No     No     No         No\n" +
                        "                 (req/resp only)\n" +
                        "procfs/sysfs     User→Kernel        No     No     No         No\n" +
                        "                 (read/write files)\n" +
                        "UNIX socket      User↔User         Yes     No     No         No\n" +
                        "                 (no kernel)\n" +
                        "Signals          Kernel→User       Yes     No    Yes         No\n" +
                        "                 User→User\n" +
                        "───────────────────────────────────────────────────────────────────────\n" +
                        "Mcast = multicast (one sender, many receivers)\n" +
                        "K→U push = kernel can initiate without user request"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Use Netlink when you need kernel→user push or multicast events, or when you want to " +
                        "replace a complex ioctl interface with structured, extensible messages. Use UNIX sockets " +
                        "for pure user↔user IPC. Use ioctl for simple, well-bounded kernel requests."
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}
