package com.example.linuxapp.screens.permissions

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
fun NatVpnScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "NAT & VPN",
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
                SectionCard(title = "What Is NAT & MASQUERADE?") {
                    BodyText(
                        "NAT (Network Address Translation) rewrites IP headers as packets pass through the kernel. " +
                        "Two main forms:\n" +
                        "  • SNAT (Source NAT) — rewrites the source IP/port on outbound packets\n" +
                        "  • DNAT (Dest NAT) — rewrites the destination IP/port on inbound packets\n\n" +
                        "MASQUERADE is a special case of SNAT where the replacement source IP is taken " +
                        "automatically from the outgoing interface's current IP address. This is ideal for a " +
                        "VPN server whose public IP may change — you don't need to hard-code it.\n\n" +
                        "Both SNAT and MASQUERADE fire at the POSTROUTING hook (after routing has decided " +
                        "which interface to use). MASQUERADE requires the nat table and connection tracking."
                    )
                    BodyText("IP forwarding must be enabled to forward packets between interfaces:")
                    CodeBlock(
                        "# Enable at runtime\n" +
                        "sysctl -w net.ipv4.ip_forward=1\n\n" +
                        "# Make permanent (add to /etc/sysctl.conf)\n" +
                        "net.ipv4.ip_forward = 1"
                    )
                }
            }

            item {
                SectionCard(title = "conntrack — Connection Tracking") {
                    BodyText(
                        "The nf_conntrack kernel module maintains a table of every active connection. " +
                        "Each entry stores both the original tuple and the NAT-translated reply tuple, " +
                        "allowing the kernel to reverse the translation automatically on return packets.\n\n" +
                        "Connection states:\n" +
                        "  NEW         — first packet of a new connection\n" +
                        "  ESTABLISHED — reply seen; connection is bidirectional\n" +
                        "  RELATED     — a new flow related to an existing one (e.g., FTP data channel)\n" +
                        "  INVALID     — packet doesn't match any known connection"
                    )
                    BodyText(
                        "When MASQUERADE rewrites a packet leaving eth0:\n" +
                        "  original:  src=10.0.0.1:54321 → dst=8.8.8.8:443\n" +
                        "  reply:     src=8.8.8.8:443   → dst=203.0.113.1:PORT_A\n\n" +
                        "conntrack stores both tuples. When 8.8.8.8 replies to 203.0.113.1:PORT_A, " +
                        "conntrack matches the reply tuple at PREROUTING and rewrites the destination " +
                        "back to 10.0.0.1:54321 — no explicit rule needed."
                    )
                    BodyText("View the connection tracking table:")
                    CodeBlock(
                        "conntrack -L\n" +
                        "# or\n" +
                        "cat /proc/net/nf_conntrack\n\n" +
                        "# Example entry (TCP, established with NAT):\n" +
                        "ipv4 2 tcp 6 86395 ESTABLISHED\n" +
                        "  src=10.0.0.1 dst=8.8.8.8 sport=54321 dport=443\n" +
                        "  src=8.8.8.8  dst=203.0.113.1 sport=443 dport=PORT_A\n" +
                        "  [ASSURED] mark=0"
                    )
                }
            }

            item {
                SectionCard(title = "VPN Design: TUN + Encrypted Tunnel") {
                    BodyText(
                        "A software VPN connects two networks over an untrusted channel using:\n" +
                        "  1. A TUN device on each end — a virtual L3 interface with a private IP\n" +
                        "  2. A user-space process that reads/writes the TUN file descriptor\n" +
                        "  3. An encrypted transport (UDP, TLS, QUIC, or TCP) over the real network"
                    )
                    CodeBlock(
                        "Client side                        Server side\n" +
                        "──────────────────────────────     ────────────────────────────\n" +
                        "App                                Internet destination\n" +
                        " │ normal IP packet                 │\n" +
                        " ▼                                 ▼\n" +
                        "kernel IP stack                    kernel IP stack\n" +
                        " │ routed via tun0                  │ injected via tun0\n" +
                        " ▼                                 ▲\n" +
                        "tun0 fd ──► VPN process            VPN process ◄── tun0 fd\n" +
                        "             │ encrypt                │ decrypt\n" +
                        "             ▼                       │\n" +
                        "           UDP/TLS/QUIC/TCP ─────────┘\n" +
                        "           (real network interface)"
                    )
                    BodyText(
                        "Addresses used in this explanation:\n" +
                        "  Client real IP:  192.168.1.10  (gateway 192.168.1.1, real iface: eth0)\n" +
                        "  Client VPN IP:   10.0.0.1      (tun0)\n" +
                        "  Server real IP:  203.0.113.1   (real iface: eth0)\n" +
                        "  Server VPN IP:   10.0.0.2      (tun0)\n" +
                        "  VPN UDP port:    1194"
                    )
                }
            }

            item {
                SectionCard(title = "Client Side — TUN Process & Routing") {
                    BodyText(
                        "The VPN process on the client:\n" +
                        "  1. Opens /dev/net/tun and creates tun0 (IFF_TUN | IFF_NO_PI)\n" +
                        "  2. Sets tun0's IP to 10.0.0.1/24 and brings it up\n" +
                        "  3. Adjusts routing so all traffic flows through tun0\n" +
                        "  4. Reads outgoing IP packets from tun0, encrypts, sends via UDP to server\n" +
                        "  5. Receives UDP from server, decrypts, writes inner packet to tun0"
                    )
                    BodyText(
                        "Routing loop prevention — critical step:\n\n" +
                        "If the default route points at tun0, the UDP packets the VPN process sends to " +
                        "203.0.113.1 would also be routed via tun0, causing an infinite loop. " +
                        "The fix: add a host route for the VPN server's real IP via the real gateway " +
                        "BEFORE changing the default route."
                    )
                    CodeBlock(
                        "# Step 1: pin VPN server traffic to the real interface\n" +
                        "ip route add 203.0.113.1/32 via 192.168.1.1 dev eth0\n\n" +
                        "# Step 2: redirect all other traffic through the tunnel\n" +
                        "ip route del default\n" +
                        "ip route add default dev tun0\n\n" +
                        "# The VPN process's UDP socket to 203.0.113.1\n" +
                        "# hits the /32 host route -> goes via eth0, not tun0.\n" +
                        "# All other traffic hits 'default dev tun0'."
                    )
                    BodyText(
                        "The VPN process main loop (simplified):\n" +
                        "  while true:\n" +
                        "    poll(tun_fd, udp_fd)\n" +
                        "    if tun_fd readable:  read IP pkt -> encrypt -> sendto(udp_fd, server)\n" +
                        "    if udp_fd readable:  recvfrom(udp_fd) -> decrypt -> write(tun_fd, pkt)"
                    )
                }
            }

            item {
                SectionCard(title = "Server Side — Routing, NAT & conntrack") {
                    BodyText(
                        "The VPN process on the server:\n" +
                        "  1. Creates tun0 at 10.0.0.2/24 and brings it up\n" +
                        "  2. Binds a UDP socket to 0.0.0.0:1194 on the real interface\n" +
                        "  3. Receives encrypted UDP from client; decrypts inner IP packet\n" +
                        "  4. Writes the inner packet (src=10.0.0.1) to tun0 fd\n" +
                        "  5. The kernel routes it out eth0 toward the internet\n" +
                        "  6. MASQUERADE rewrites src 10.0.0.1:PORT -> 203.0.113.1:PORT_A\n" +
                        "  7. conntrack records the mapping\n\n" +
                        "On the return path:\n" +
                        "  8. Reply arrives at 203.0.113.1:PORT_A\n" +
                        "  9. conntrack at PREROUTING un-NATs dst to 10.0.0.1:PORT\n" +
                        " 10. Kernel routes 10.0.0.0/24 via tun0\n" +
                        " 11. Server VPN process reads from tun0, encrypts, sends UDP back to client"
                    )
                    BodyText(
                        "Why the 10.0.0.0/24 route exists on the server:\n" +
                        "When you add 10.0.0.2/24 to tun0, the kernel automatically creates a " +
                        "connected route: 10.0.0.0/24 dev tun0. This is what makes conntrack's " +
                        "un-NATed packets (dst=10.0.0.1) get delivered to tun0 — and therefore " +
                        "to the VPN process reading the fd."
                    )
                }
            }

            item {
                SectionCard(title = "Packet Journey: End-to-End Example") {
                    BodyText("Tracing a TCP connection from the client app to 8.8.8.8:443 and back:")
                    CodeBlock(
                        "── SYN: client app → 8.8.8.8:443 ──────────────────────────────\n\n" +
                        " 1. App sends TCP SYN\n" +
                        "    [src=10.0.0.1:54321  dst=8.8.8.8:443]\n\n" +
                        " 2. Default route: dev tun0\n" +
                        "    Packet arrives at tun0 fd (readable by VPN process)\n\n" +
                        " 3. VPN client reads SYN, encrypts, sends UDP:\n" +
                        "    [src=192.168.1.10:PORT_C  dst=203.0.113.1:1194]\n" +
                        "    -> exits via eth0 (host route for 203.0.113.1)\n\n" +
                        " 4. VPN server receives UDP on :1194, decrypts inner packet\n\n" +
                        " 5. Writes [src=10.0.0.1:54321  dst=8.8.8.8:443] to server tun0\n\n" +
                        " 6. Kernel forwards via eth0; MASQUERADE at POSTROUTING fires:\n" +
                        "    src 10.0.0.1:54321 -> 203.0.113.1:PORT_A\n\n" +
                        " 7. conntrack records:\n" +
                        "    orig:  10.0.0.1:54321 -> 8.8.8.8:443\n" +
                        "    reply: 8.8.8.8:443 -> 203.0.113.1:PORT_A\n\n" +
                        " 8. SYN leaves server eth0 -> 8.8.8.8:443\n\n" +
                        "── SYN-ACK: 8.8.8.8:443 → client app ──────────────────────────\n\n" +
                        " 9. 8.8.8.8 sends SYN-ACK:\n" +
                        "    [src=8.8.8.8:443  dst=203.0.113.1:PORT_A]\n\n" +
                        "10. conntrack at PREROUTING matches reply tuple, un-NATs:\n" +
                        "    dst 203.0.113.1:PORT_A -> 10.0.0.1:54321\n\n" +
                        "11. Kernel routes 10.0.0.0/24 via tun0\n" +
                        "    -> SYN-ACK lands at server tun0 fd\n\n" +
                        "12. VPN server reads [src=8.8.8.8:443  dst=10.0.0.1:54321],\n" +
                        "    encrypts, sends UDP to 192.168.1.10:PORT_C\n\n" +
                        "13. VPN client receives UDP, decrypts, writes inner packet to tun0:\n" +
                        "    [src=8.8.8.8:443  dst=10.0.0.1:54321]\n\n" +
                        "14. Kernel delivers SYN-ACK to client app's socket"
                    )
                }
            }

            item {
                SectionCard(title = "Command-Line Setup — Client") {
                    BodyText("The VPN process creates tun0 via ioctl (see TUN Device screen), then:")
                    CodeBlock(
                        "# Bring up tun0 and assign the VPN IP\n" +
                        "ip link set tun0 up\n" +
                        "ip addr add 10.0.0.1/24 dev tun0\n\n" +
                        "# Pin VPN server to the real gateway FIRST (loop prevention)\n" +
                        "ip route add 203.0.113.1/32 via 192.168.1.1 dev eth0\n\n" +
                        "# Redirect all other traffic through the tunnel\n" +
                        "ip route del default\n" +
                        "ip route add default dev tun0\n\n" +
                        "# Teardown: restore real default route, remove host route\n" +
                        "ip route del default dev tun0\n" +
                        "ip route add default via 192.168.1.1\n" +
                        "ip route del 203.0.113.1/32"
                    )
                }
            }

            item {
                SectionCard(title = "Command-Line Setup — Server") {
                    CodeBlock(
                        "# Enable IP forwarding\n" +
                        "sysctl -w net.ipv4.ip_forward=1\n\n" +
                        "# Configure tun0\n" +
                        "ip link set tun0 up\n" +
                        "ip addr add 10.0.0.2/24 dev tun0\n" +
                        "# Kernel auto-creates: 10.0.0.0/24 dev tun0\n\n" +
                        "# MASQUERADE: packets forwarded out eth0 get src-NATed\n" +
                        "iptables -t nat -A POSTROUTING -o eth0 -j MASQUERADE\n\n" +
                        "# Allow forwarding: tun0 -> internet\n" +
                        "iptables -A FORWARD -i tun0 -o eth0 -j ACCEPT\n\n" +
                        "# Allow forwarding: return packets (conntrack-matched)\n" +
                        "iptables -A FORWARD -i eth0 -o tun0 \\\n" +
                        "    -m state --state ESTABLISHED,RELATED -j ACCEPT"
                    )
                    BodyText(
                        "The FORWARD rules are needed because the kernel's default FORWARD policy is " +
                        "often DROP. Without them, forwarded packets are blocked even though NAT is set up."
                    )
                }
            }

            item {
                SectionCard(title = "ioctl APIs: Interface Config & Routes") {
                    BodyText(
                        "All of these ioctls go on a regular AF_INET SOCK_DGRAM socket — NOT the TUN fd. " +
                        "The TUN fd is only for reading/writing packets."
                    )
                    BodyText("Set IP address (SIOCSIFADDR) and netmask (SIOCSIFNETMASK):")
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
                        "/* Bring up (read-modify-write IFF_UP) */\n" +
                        "ioctl(sock, SIOCGIFFLAGS, &ifr);\n" +
                        "ifr.ifr_flags |= IFF_UP | IFF_RUNNING;\n" +
                        "ioctl(sock, SIOCSIFFLAGS, &ifr);\n\n" +
                        "close(sock);"
                    )
                    BodyText("Add a route (SIOCADDRT) using struct rtentry:")
                    CodeBlock(
                        "#include <net/route.h>\n\n" +
                        "int sock = socket(AF_INET, SOCK_DGRAM, 0);\n\n" +
                        "struct rtentry rt = {};\n" +
                        "struct sockaddr_in *dst  = (struct sockaddr_in *)&rt.rt_dst;\n" +
                        "struct sockaddr_in *gw   = (struct sockaddr_in *)&rt.rt_gateway;\n" +
                        "struct sockaddr_in *mask = (struct sockaddr_in *)&rt.rt_genmask;\n\n" +
                        "/* Add route: 0.0.0.0/0 dev tun0 (no gateway) */\n" +
                        "dst->sin_family = gw->sin_family = mask->sin_family = AF_INET;\n" +
                        "inet_pton(AF_INET, \"0.0.0.0\", &dst->sin_addr);\n" +
                        "inet_pton(AF_INET, \"0.0.0.0\", &mask->sin_addr);\n" +
                        "rt.rt_flags = RTF_UP;\n" +
                        "rt.rt_dev   = \"tun0\";        /* output device */\n" +
                        "rt.rt_metric = 0;\n\n" +
                        "ioctl(sock, SIOCADDRT, &rt); /* SIOCDELRT to remove */\n" +
                        "close(sock);"
                    )
                    BodyText(
                        "SIOCADDRT / SIOCDELRT are the classic ioctl route API. They are deprecated " +
                        "in favour of netlink (RTM_NEWROUTE) but still work on all kernels."
                    )
                }
            }

            item {
                SectionCard(title = "Netlink APIs: Addresses, Routes & NAT Rules") {
                    BodyText(
                        "Netlink (AF_NETLINK) is the modern kernel interface for network configuration. " +
                        "NETLINK_ROUTE handles addresses, routes, and link state. " +
                        "NETLINK_NETFILTER handles firewall/NAT rules (nfnetlink)."
                    )
                    BodyText("Key message types for NETLINK_ROUTE:")
                    CodeBlock(
                        "RTM_NEWADDR  — add IP address to interface (like SIOCSIFADDR)\n" +
                        "RTM_DELADDR  — remove IP address\n" +
                        "RTM_NEWLINK  — modify link state (bring up/down, rename, ...)\n" +
                        "RTM_NEWROUTE — add a route (like SIOCADDRT)\n" +
                        "RTM_DELROUTE — remove a route"
                    )
                    BodyText("Minimal structure for RTM_NEWADDR (add 10.0.0.1/24 to tun0):")
                    CodeBlock(
                        "#include <linux/rtnetlink.h>\n" +
                        "#include <linux/if_addr.h>\n\n" +
                        "/* Message layout in the send buffer: */\n" +
                        "struct nlmsghdr  nlh;      /* type=RTM_NEWADDR, flags=NLM_F_CREATE|ACK */\n" +
                        "struct ifaddrmsg ifa;      /* ifa_family=AF_INET, ifa_prefixlen=24,\n" +
                        "                              ifa_index=if_nametoindex(\"tun0\") */\n" +
                        "struct rtattr    rta;      /* rta_type=IFA_LOCAL, followed by in_addr */\n" +
                        "struct in_addr   addr;     /* 10.0.0.1 */\n\n" +
                        "/* Send via sendto() on AF_NETLINK/NETLINK_ROUTE socket */\n" +
                        "/* Read back ACK with recvfrom() and check nlmsgerr.error */\n\n" +
                        "/* To bring tun0 up (RTM_NEWLINK): */\n" +
                        "struct ifinfomsg ifi;      /* ifi_index=if_nametoindex(\"tun0\"),\n" +
                        "                              ifi_flags=IFF_UP,\n" +
                        "                              ifi_change=IFF_UP (mask of changed bits) */"
                    )
                    BodyText(
                        "NAT/MASQUERADE rules have NO ioctl path. They live entirely in the " +
                        "netfilter subsystem and are configured via NETLINK_NETFILTER (nfnetlink). " +
                        "Three practical options:\n\n" +
                        "  libiptc (legacy)\n" +
                        "    Internal iptables C library. Deprecated but widely available.\n" +
                        "    iptc_init / iptc_insert_entry / iptc_commit\n\n" +
                        "  libnftables (modern, recommended)\n" +
                        "    Clean API over nftables. Link with -lnftables.\n\n" +
                        "  Shell out to iptables/nft\n" +
                        "    Simplest: system(\"iptables -t nat -A POSTROUTING ...\")"
                    )
                    BodyText("libnftables: set up MASQUERADE with a few lines:")
                    CodeBlock(
                        "#include <nftables/libnftables.h>\n\n" +
                        "struct nft_ctx *ctx = nft_ctx_new(NFT_CTX_DEFAULT);\n\n" +
                        "/* Create table + chain + MASQUERADE rule in one batch */\n" +
                        "nft_run_cmd_from_buffer(ctx,\n" +
                        "    \"add table ip vpn_nat\\n\"\n" +
                        "    \"add chain ip vpn_nat postrouting \"\n" +
                        "        \"{ type nat hook postrouting priority 100; }\\n\"\n" +
                        "    \"add rule  ip vpn_nat postrouting \"\n" +
                        "        \"oifname \\\"eth0\\\" masquerade\"\n" +
                        ");\n\n" +
                        "nft_ctx_free(ctx);"
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
