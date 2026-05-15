package com.example.linuxapp.screens.usermode

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
fun UserModeTunScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "TUN Device",
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
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = innerPadding.calculateTopPadding() + 8.dp,
                bottom = 8.dp
            )
        ) {

            item {
                SectionCard(title = "What is TUN?") {
                    BodyText(
                        "TUN stands for network TUNnel. It is a virtual network interface that operates at Layer 3 (IP level). " +
                        "Unlike a physical NIC, a TUN device has no hardware behind it — it is backed by a file descriptor " +
                        "in user space."
                    )
                    BodyText(
                        "The kernel routes outgoing IP packets destined for the TUN interface to your process via the fd. " +
                        "Your process reads those packets, does whatever it wants (e.g. encrypt them for a VPN), and sends them " +
                        "over the real network. In the other direction, your process injects packets by writing to the fd — the " +
                        "kernel delivers them to the IP stack as if they arrived from the network."
                    )
                    BodyText(
                        "TUN vs TAP:\n" +
                        "  • TUN — Layer 3, sees raw IP packets\n" +
                        "  • TAP — Layer 2, sees raw Ethernet frames (useful for bridging)"
                    )
                }
            }

            item {
                SectionCard(title = "Use Cases") {
                    BodyText(
                        "• VPN: read outgoing IP packets → encrypt → forward to VPN server; decrypt inbound packets → inject back\n" +
                        "• Custom tunneling protocols (IPIP, GRE-like, WireGuard-style)\n" +
                        "• Packet inspection and filtering in user space\n" +
                        "• Network simulators and test harnesses"
                    )
                }
            }

            item {
                SectionCard(title = "Creating a TUN Device") {
                    BodyText("Open /dev/net/tun, then call ioctl(TUNSETIFF) with a struct ifreq:")
                    CodeBlock(
                        "#include <fcntl.h>\n" +
                        "#include <sys/ioctl.h>\n" +
                        "#include <linux/if.h>\n" +
                        "#include <linux/if_tun.h>\n" +
                        "\n" +
                        "/* dev: desired name e.g. \"tun0\", or \"\" for kernel auto-assign */\n" +
                        "int tun_alloc(char *dev) {\n" +
                        "    int fd = open(\"/dev/net/tun\", O_RDWR);\n" +
                        "    if (fd < 0) { perror(\"open\"); return fd; }\n" +
                        "\n" +
                        "    struct ifreq ifr = {};\n" +
                        "    ifr.ifr_flags = IFF_TUN | IFF_NO_PI;\n" +
                        "    if (*dev) strncpy(ifr.ifr_name, dev, IFNAMSIZ);\n" +
                        "\n" +
                        "    if (ioctl(fd, TUNSETIFF, &ifr) < 0) {\n" +
                        "        perror(\"TUNSETIFF\"); close(fd); return -1;\n" +
                        "    }\n" +
                        "    strcpy(dev, ifr.ifr_name); /* actual name written back */\n" +
                        "    return fd;\n" +
                        "}"
                    )
                    BodyText(
                        "Key flags for ifr_flags:\n" +
                        "  • IFF_TUN   — Layer 3 / IP packets\n" +
                        "  • IFF_TAP   — Layer 2 / Ethernet frames\n" +
                        "  • IFF_NO_PI — omit the 4-byte struct tun_pi prefix on each packet (almost always desired)"
                    )
                    BodyText("After creating the device, bring it up from the shell (or via SIOCSIFFLAGS ioctl):")
                    CodeBlock(
                        "ip link set tun0 up\n" +
                        "ip addr add 10.0.0.1/24 dev tun0\n" +
                        "ip route add 10.0.0.0/24 dev tun0"
                    )
                }
            }

            item {
                SectionCard(title = "Closing / Deleting a TUN Device") {
                    BodyText("By default, the interface is destroyed automatically when the fd is closed:")
                    CodeBlock("close(fd);  /* interface disappears */")
                    BodyText("To make the interface persistent (survives fd close):")
                    CodeBlock("ioctl(fd, TUNSETPERSIST, 1);")
                    BodyText("To delete a persistent interface later:")
                    CodeBlock(
                        "ioctl(fd, TUNSETPERSIST, 0);\n" +
                        "close(fd);\n" +
                        "/* or from the shell: */\n" +
                        "ip link delete tun0"
                    )
                }
            }

            item {
                SectionCard(title = "Reading Packets (outgoing IP traffic)") {
                    BodyText(
                        "Each read() returns exactly one IP packet. With IFF_NO_PI set, the buffer starts directly " +
                        "with the IP header."
                    )
                    CodeBlock(
                        "uint8_t buf[65536];\n" +
                        "ssize_t n = read(fd, buf, sizeof(buf));\n" +
                        "if (n < 0) { perror(\"read\"); }\n" +
                        "\n" +
                        "/* Determine IP version from first nibble */\n" +
                        "uint8_t ip_version = (buf[0] >> 4);  /* 4 or 6 */"
                    )
                    BodyText(
                        "read() blocks until a packet arrives. For non-blocking I/O, open with O_NONBLOCK " +
                        "and use poll() or epoll() to wait for POLLIN."
                    )
                    CodeBlock(
                        "int fd = open(\"/dev/net/tun\", O_RDWR | O_NONBLOCK);\n" +
                        "/* ... TUNSETIFF ... */\n" +
                        "\n" +
                        "struct pollfd pfd = { .fd = fd, .events = POLLIN };\n" +
                        "poll(&pfd, 1, -1);  /* block until packet ready */\n" +
                        "ssize_t n = read(fd, buf, sizeof(buf));"
                    )
                }
            }

            item {
                SectionCard(title = "Writing / Injecting Packets (incoming traffic)") {
                    BodyText(
                        "Each write() injects exactly one IP packet into the kernel's IP stack. " +
                        "The buffer must contain a complete, valid IP packet."
                    )
                    CodeBlock(
                        "/* packet_buf: pointer to a valid IP packet */\n" +
                        "ssize_t n = write(fd, packet_buf, packet_len);\n" +
                        "if (n < 0) { perror(\"write\"); }"
                    )
                    BodyText(
                        "The kernel processes the injected packet exactly as if it arrived from the network — " +
                        "it goes through the normal IP routing, netfilter hooks, and socket delivery path."
                    )
                }
            }

            item {
                SectionCard(title = "Control Commands (ioctl)") {
                    BodyText("Common ioctl requests on the TUN file descriptor:")
                    BodyText(
                        "  TUNSETIFF      — create/attach interface (see above)\n" +
                        "  TUNSETPERSIST  — 1 = keep alive after close, 0 = destroy on close\n" +
                        "  TUNSETOWNER    — set owning UID\n" +
                        "  TUNSETGROUP    — set owning GID\n" +
                        "  TUNGETIFF      — read back the struct ifreq for the current interface\n" +
                        "  TUNGETFEATURES — query supported feature flags"
                    )
                    CodeBlock(
                        "/* Make persistent and set owner */\n" +
                        "ioctl(fd, TUNSETPERSIST, 1);\n" +
                        "ioctl(fd, TUNSETOWNER, (long)getuid());\n" +
                        "ioctl(fd, TUNSETGROUP, (long)getgid());"
                    )
                    BodyText(
                        "SIOCGIFINDEX gives you the interface index. This ioctl goes on a regular socket fd, " +
                        "not the TUN fd:"
                    )
                    CodeBlock(
                        "int sock = socket(AF_INET, SOCK_DGRAM, 0);\n" +
                        "struct ifreq ifr2 = {};\n" +
                        "strncpy(ifr2.ifr_name, \"tun0\", IFNAMSIZ);\n" +
                        "ioctl(sock, SIOCGIFINDEX, &ifr2);\n" +
                        "printf(\"ifindex = %d\\n\", ifr2.ifr_ifindex);\n" +
                        "close(sock);"
                    )
                }
            }

            item {
                SectionCard(title = "Setting IP Address and Netmask") {
                    BodyText(
                        "After creating the TUN device, you need to assign it an IP address and netmask. " +
                        "All three methods below use a regular AF_INET SOCK_DGRAM socket — not the TUN fd."
                    )
                    BodyText("Command line:")
                    CodeBlock(
                        "ip addr add 10.0.0.1/24 dev tun0\n" +
                        "ip addr del 10.0.0.1/24 dev tun0   # remove\n" +
                        "ip addr show dev tun0              # verify"
                    )
                    BodyText("Via ioctl (SIOCSIFADDR + SIOCSIFNETMASK):")
                    CodeBlock(
                        "#include <sys/ioctl.h>\n" +
                        "#include <net/if.h>\n" +
                        "#include <arpa/inet.h>\n\n" +
                        "int sock = socket(AF_INET, SOCK_DGRAM, 0);\n\n" +
                        "struct ifreq ifr = {};\n" +
                        "strncpy(ifr.ifr_name, \"tun0\", IFNAMSIZ);\n\n" +
                        "struct sockaddr_in *sin = (struct sockaddr_in *)&ifr.ifr_addr;\n" +
                        "sin->sin_family = AF_INET;\n\n" +
                        "/* Set IP address */\n" +
                        "inet_pton(AF_INET, \"10.0.0.1\", &sin->sin_addr);\n" +
                        "ioctl(sock, SIOCSIFADDR, &ifr);\n\n" +
                        "/* Set netmask */\n" +
                        "inet_pton(AF_INET, \"255.255.255.0\", &sin->sin_addr);\n" +
                        "ioctl(sock, SIOCSIFNETMASK, &ifr);\n\n" +
                        "close(sock);"
                    )
                    BodyText("Via netlink (RTM_NEWADDR):")
                    CodeBlock(
                        "#include <linux/rtnetlink.h>\n" +
                        "#include <linux/if_addr.h>\n\n" +
                        "/* Open a NETLINK_ROUTE socket */\n" +
                        "int nl = socket(AF_NETLINK, SOCK_RAW, NETLINK_ROUTE);\n\n" +
                        "/* Build and send a message with these fields: */\n" +
                        "struct nlmsghdr nlh;      /* type = RTM_NEWADDR             */\n" +
                        "                          /* flags = NLM_F_REQUEST|NLM_F_CREATE|NLM_F_ACK */\n" +
                        "struct ifaddrmsg ifa;     /* ifa_family    = AF_INET        */\n" +
                        "                          /* ifa_prefixlen = 24             */\n" +
                        "                          /* ifa_index     = if_nametoindex(\"tun0\") */\n" +
                        "/* Append RTA_LOCAL attribute: the 10.0.0.1 in_addr */\n" +
                        "/* Send with sendto(nl, ..., (struct sockaddr *)&nl_addr, ...) */\n" +
                        "/* Read ACK: recvfrom -> nlmsgerr.error == 0 means success */"
                    )
                }
            }

            item {
                SectionCard(title = "Bringing the Interface Up and Down") {
                    BodyText(
                        "An interface must be brought UP before the kernel will route packets through it " +
                        "or deliver them to the TUN fd. Again, these ioctls and netlink messages use a " +
                        "regular socket, not the TUN fd."
                    )
                    BodyText("Command line:")
                    CodeBlock(
                        "ip link set tun0 up\n" +
                        "ip link set tun0 down\n" +
                        "ip link show tun0   # verify flags: <UP,POINTOPOINT,..."
                    )
                    BodyText("Via ioctl (SIOCGIFFLAGS + SIOCSIFFLAGS):")
                    CodeBlock(
                        "int sock = socket(AF_INET, SOCK_DGRAM, 0);\n\n" +
                        "struct ifreq ifr = {};\n" +
                        "strncpy(ifr.ifr_name, \"tun0\", IFNAMSIZ);\n\n" +
                        "/* Read current flags */\n" +
                        "ioctl(sock, SIOCGIFFLAGS, &ifr);\n\n" +
                        "/* Bring up: set IFF_UP and IFF_RUNNING */\n" +
                        "ifr.ifr_flags |= (IFF_UP | IFF_RUNNING);\n" +
                        "ioctl(sock, SIOCSIFFLAGS, &ifr);\n\n" +
                        "/* Bring down: clear IFF_UP */\n" +
                        "/* ifr.ifr_flags &= ~IFF_UP; */\n" +
                        "/* ioctl(sock, SIOCSIFFLAGS, &ifr); */\n\n" +
                        "close(sock);"
                    )
                    BodyText("Via netlink (RTM_NEWLINK):")
                    CodeBlock(
                        "/* Build and send RTM_NEWLINK with: */\n" +
                        "struct nlmsghdr nlh;     /* type = RTM_NEWLINK              */\n" +
                        "                         /* flags = NLM_F_REQUEST|NLM_F_ACK */\n" +
                        "struct ifinfomsg ifi;    /* ifi_index  = if_nametoindex(\"tun0\") */\n" +
                        "                         /* ifi_flags  = IFF_UP   (bring up) */\n" +
                        "                         /* ifi_flags  = 0        (bring down) */\n" +
                        "                         /* ifi_change = IFF_UP   (bitmask of */\n" +
                        "                         /*              fields to change)     */\n" +
                        "/* No extra attributes needed for a simple up/down toggle */"
                    )
                    BodyText(
                        "The ifi_change field is a bitmask that tells the kernel which flag bits to " +
                        "actually modify. Setting ifi_change = IFF_UP means: only touch the UP bit, " +
                        "leave all other flags (PROMISC, MULTICAST, etc.) unchanged."
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
