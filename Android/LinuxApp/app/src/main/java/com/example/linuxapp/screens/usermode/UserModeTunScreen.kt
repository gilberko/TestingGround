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

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
