package com.example.developmentapp.screens.tcpip

import androidx.compose.foundation.layout.Spacer
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
import com.example.developmentapp.screens.BodyText
import com.example.developmentapp.screens.CodeBlock
import com.example.developmentapp.screens.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IpScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "IP",
                        color      = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontSize   = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector        = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint               = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            item { Spacer(Modifier.height(12.dp)) }

            // ── What is IP ───────────────────────────────────────────────
            item {
                SectionCard(title = "What is IP?") {
                    BodyText("IP — Internet Protocol — is the Layer 3 (Network) protocol that gives every device a logical address and handles routing packets across networks. It provides best-effort, connectionless delivery: packets may be lost, reordered, or duplicated.")
                    BodyText("An IP packet is carried inside an Ethernet frame's payload field. The Ethernet frame delivers it hop-by-hop between adjacent nodes (using MAC addresses), while IP carries the end-to-end destination address across many hops.")
                    CodeBlock("""
[ Ethernet Frame                                 ]
  [ Ethernet Header | IP Packet              | FCS ]
                      [ IP Header | TCP/UDP/... ]
                    """.trimIndent())
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── IPv4 vs IPv6 ─────────────────────────────────────────────
            item {
                SectionCard(title = "IPv4 vs IPv6") {
                    BodyText("IPv4 (1981): 32-bit addresses → ~4.3 billion unique addresses. Written as four decimal octets: 192.168.1.100. The internet ran out of IPv4 space around 2011; NAT (Network Address Translation) has extended its life.")
                    BodyText("IPv6 (1998): 128-bit addresses → 3.4 × 10³⁸ addresses. Written as eight groups of four hex digits: 2001:0db8:85a3::8a2e:0370:7334. Consecutive all-zero groups collapse to '::'.")
                    BodyText("Dual-stack: most modern systems run both IPv4 and IPv6 simultaneously. Tunneling (e.g. 6in4) encapsulates IPv6 packets inside IPv4 when native IPv6 is unavailable.")
                    BodyText("Key IPv6 improvements over IPv4: no header checksum (handled by Layer 2/4), no fragmentation at routers (done by the source), built-in IPSec support, stateless address autoconfiguration (SLAAC).")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── IPv4 Header ──────────────────────────────────────────────
            item {
                SectionCard(title = "IPv4 Header") {
                    BodyText("The IPv4 header is at least 20 bytes (without options):")
                    CodeBlock("""
 0                   1                   2                   3
 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|Version|  IHL  |DSCP/ECN (8b)  |        Total Length (16b)     |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|         Identification (16b)  |Flags(3b)|  Fragment Offset(13b)|
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|  TTL (8b)     |  Protocol (8b)|       Header Checksum (16b)   |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                       Source IP Address (32b)                 |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                    Destination IP Address (32b)               |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                    Options (0–40 bytes, if IHL > 5)           |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
                    """.trimIndent())
                    BodyText("Version (4b): always 4 for IPv4.")
                    BodyText("IHL — Internet Header Length (4b): header length in 32-bit words. Minimum 5 (=20 bytes).")
                    BodyText("DSCP/ECN (8b): quality-of-service markings. DSCP = traffic class; ECN = explicit congestion notification.")
                    BodyText("Total Length (16b): entire IP packet size (header + payload), max 65535 bytes.")
                    BodyText("ID / Flags / Fragment Offset: used when a packet is too large for a link's MTU and must be fragmented. The 'Don't Fragment' (DF) flag prevents fragmentation.")
                    BodyText("TTL — Time To Live (8b): decremented by 1 at each router hop. Packet dropped (ICMP Time Exceeded sent back) when TTL reaches 0. Prevents routing loops from lasting forever.")
                    BodyText("Protocol (8b): identifies the payload type. 6 = TCP, 17 = UDP, 1 = ICMP, 89 = OSPF.")
                    BodyText("Header Checksum (16b): one's complement checksum of the header only. Must be recalculated at every hop (TTL changes).")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── IPv6 Header ──────────────────────────────────────────────
            item {
                SectionCard(title = "IPv6 Header") {
                    BodyText("IPv6 has a fixed 40-byte header — simpler than IPv4, no checksum, no fragmentation fields (moved to extension headers):")
                    CodeBlock("""
 0                   1                   2                   3
 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|Version| Traffic Class (8b) |         Flow Label (20b)         |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|         Payload Length (16b)  | Next Header(8b)| Hop Limit(8b)|
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                                                               |
+                    Source Address (128 bits)                  +
|                                                               |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                                                               |
+                 Destination Address (128 bits)                +
|                                                               |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
                    """.trimIndent())
                    BodyText("Version (4b): always 6.")
                    BodyText("Traffic Class (8b): similar to IPv4 DSCP/ECN.")
                    BodyText("Flow Label (20b): identifies a traffic flow for QoS; routers can use it to keep packets on the same path.")
                    BodyText("Payload Length (16b): size of everything after the 40-byte header.")
                    BodyText("Next Header (8b): identifies the payload type or the first extension header (same values as IPv4 Protocol field).")
                    BodyText("Hop Limit (8b): equivalent to IPv4 TTL.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Routing ──────────────────────────────────────────────────
            item {
                SectionCard(title = "Routing") {
                    BodyText("Routers forward IP packets hop-by-hop. Each router has a routing table — a list of network prefixes and the next-hop address or outgoing interface for each.")
                    BodyText("Longest prefix match: when multiple routes match a destination, the most specific (longest) prefix wins. A /28 route beats a /24 route for the same destination.")
                    BodyText("Default route (0.0.0.0/0): matches any destination not covered by a more specific route — traffic goes to the 'default gateway' (usually your router/ISP).")
                    BodyText("Routing protocols: OSPF and BGP are used between routers to exchange and update routing tables automatically. BGP is the protocol that glues together the entire internet.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Subnets & Subnet Mask ────────────────────────────────────
            item {
                SectionCard(title = "Subnets & Subnet Mask") {
                    BodyText("A subnet divides an IP address space into smaller networks. The subnet mask (or CIDR prefix length) identifies which bits are the network portion and which are the host portion.")
                    CodeBlock("""
Address:  192.168.1.100
Mask:     255.255.255.0  =  /24

Network:  192.168.1.0    (first 24 bits fixed)
Hosts:    192.168.1.1  to  192.168.1.254
Broadcast: 192.168.1.255

# /24 = 256 addresses, 254 usable hosts
# /25 = 128 addresses, 126 usable hosts
# /30 =   4 addresses,   2 usable hosts (point-to-point links)
                    """.trimIndent())
                    BodyText("Hosts on the same subnet can communicate directly (via Ethernet/ARP). To reach a different subnet, traffic must go through a router.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Loopback / Localhost ──────────────────────────────────────
            item {
                SectionCard(title = "Loopback — Localhost Addresses") {
                    BodyText("The loopback interface (lo) is a virtual network interface that exists entirely within the operating system. Traffic sent to it never touches any network hardware.")
                    BodyText("IPv4: the entire 127.0.0.0/8 block is reserved for loopback. 127.0.0.1 is the canonical address. The hostname 'localhost' is conventionally mapped to 127.0.0.1.")
                    BodyText("IPv6: ::1 — a single address (all 128 bits zero except the last). Written in full: 0000:0000:0000:0000:0000:0000:0000:0001, collapsed to ::1. On dual-stack systems 'localhost' resolves to ::1 as well.")
                    CodeBlock(
                        "ping 127.0.0.1       # IPv4 loopback\n" +
                        "ping ::1             # IPv6 loopback\n" +
                        "ping localhost       # resolves to one or both on dual-stack"
                    )
                    BodyText("Common use: connecting to a local web server, database, or any service on the same machine without going through a real network interface. The loopback interface is always available even with no network hardware present.")
                    BodyText("Any address in 127.0.0.0/8 works (127.0.0.1–127.255.255.254). Multiple services on the same machine sometimes bind to different 127.x.x.x addresses to avoid port conflicts.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── IPv4 Address Classes ──────────────────────────────────────
            item {
                SectionCard(title = "IPv4 Address Classes") {
                    BodyText("Before CIDR (Classless Inter-Domain Routing), IPv4 addresses were divided into classes based on the leading bits of the first octet. Classful addressing is largely historical but still relevant for understanding address ranges and private addresses.")
                    CodeBlock(
                        "Class  First bits  Range                      Default  Hosts/net\n" +
                        "  A       0...     1.0.0.0–126.255.255.255    /8       ~16.7 M\n" +
                        "  B       10..     128.0.0.0–191.255.255.255  /16      ~65,534\n" +
                        "  C       110.     192.0.0.0–223.255.255.255  /24       254\n" +
                        "  D       1110     224.0.0.0–239.255.255.255  multicast (no mask)\n" +
                        "  E       1111     240.0.0.0–255.255.255.255  reserved/experimental"
                    )
                    BodyText("Class A: 8 network bits, 24 host bits. 0.x.x.x and 127.x.x.x are reserved, leaving networks 1–126 (126 usable Class A network blocks).")
                    BodyText("Class B: 16 network bits, 16 host bits. Medium-to-large organisations.")
                    BodyText("Class C: 24 network bits, 8 host bits. Small networks — the most numerous class.")
                    BodyText("Class D (224–239): IP multicast addresses. No host/network split — the entire address identifies a multicast group.")
                    BodyText("Class E (240–255): reserved for experimental use. Not used in practice.")
                    BodyText("Special values within any subnet:")
                    BodyText("  • All host bits = 0 (e.g. 192.168.1.0 in a /24): the network address — identifies the subnet itself. Not assignable to any host.")
                    BodyText("  • All host bits = 1 (e.g. 192.168.1.255 in a /24): the directed broadcast address — a packet sent here is delivered to every host in that subnet.")
                    BodyText("CIDR supersedes classful addressing — modern routing uses explicit prefix lengths (/8, /16, /24, /27…) regardless of class. But the Class A/B/C ranges still define where the private address blocks live.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Private Addresses (RFC 1918) ──────────────────────────────
            item {
                SectionCard(title = "Private Addresses — RFC 1918") {
                    BodyText("RFC 1918 defines three address blocks reserved for private networks. They are not routable on the public internet — ISPs drop packets sourced from or destined to these ranges at their borders.")
                    CodeBlock(
                        "Range                          CIDR   Class  Addresses\n" +
                        "10.0.0.0–10.255.255.255         /8      A    ~16.7 million\n" +
                        "172.16.0.0–172.31.255.255       /12     B    ~1 million\n" +
                        "192.168.0.0–192.168.255.255     /16     C    65,536"
                    )
                    BodyText("10.0.0.0/8: the Class A private block. Used by large enterprises, cloud VPCs (AWS, Azure default VNets), and carrier-grade NAT. 10.0.0.1 is a common default gateway — your guess was exactly right.")
                    BodyText("172.16.0.0/12: covers 172.16.x.x through 172.31.x.x. Less common in home networks but used by Docker (172.17.0.0/16 for the default bridge) and some corporate environments.")
                    BodyText("192.168.0.0/16: the most familiar private range. Home routers typically use 192.168.0.1 or 192.168.1.1 as the default gateway with the LAN on a /24 subnet. Your guess of 192.168.0.1 was exactly right.")
                    BodyText("NAT (Network Address Translation): since private addresses are not routable on the internet, a router performing NAT rewrites the source IP of outgoing packets to its own public IP and maintains a translation table to reverse-map incoming replies. This lets many private-addressed devices share a single public IP.")
                    BodyText("APIPA — Automatic Private IP Addressing (169.254.0.0/16): not RFC 1918 but related. When DHCP fails, the OS auto-assigns a 169.254.x.x link-local address so local communication still works. You will see this on a misconfigured or DHCP-unreachable machine.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Broadcast in IP ───────────────────────────────────────────
            item {
                SectionCard(title = "Broadcast in IP") {
                    BodyText("IP defines two types of broadcast address:")
                    BodyText("Limited broadcast — 255.255.255.255: delivered to all hosts on the local subnet only. Routers never forward it. Used by DHCP Discover (a client with no IP must broadcast to find a server) and similar protocols that need to reach the local network before an address is known.")
                    BodyText("Directed broadcast — all host bits set to 1 for a specific subnet (e.g. 192.168.1.255 for 192.168.1.0/24): addresses all hosts in that subnet. A router can forward a directed broadcast, but most block it by default. Historically exploited in Smurf DDoS attacks — sending a spoofed ICMP echo to a directed broadcast caused all hosts in the subnet to reply to the victim.")
                    CodeBlock(
                        "Subnet: 192.168.1.0/24\n" +
                        "  Network address:    192.168.1.0   (host bits all 0 — not a host)\n" +
                        "  Directed broadcast: 192.168.1.255 (host bits all 1)\n" +
                        "  Limited broadcast:  255.255.255.255 (always stays local)"
                    )
                    BodyText("IPv6 has NO broadcast: the concept is entirely replaced by multicast. All-nodes multicast (FF02::1) reaches every IPv6 host on the link; all-routers multicast (FF02::2) reaches every router — more targeted than a blanket broadcast.")
                    BodyText("Sending a broadcast from a socket requires explicitly enabling it: setsockopt(sockfd, SOL_SOCKET, SO_BROADCAST, &on, sizeof(on)). Without this flag the OS rejects sends to broadcast addresses.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Multicast in IP ───────────────────────────────────────────
            item {
                SectionCard(title = "Multicast in IP") {
                    BodyText("IP multicast lets one sender reach a group of receivers efficiently — the network delivers one copy of a packet and replicates only where paths diverge, rather than sending a separate unicast copy to each receiver.")
                    BodyText("IPv4 multicast uses Class D addresses: 224.0.0.0–239.255.255.255. Well-known groups:")
                    BodyText("  • 224.0.0.1   — all hosts on the subnet")
                    BodyText("  • 224.0.0.2   — all routers on the subnet")
                    BodyText("  • 224.0.0.5   — all OSPF routers")
                    BodyText("  • 224.0.0.251 — mDNS (Bonjour / Avahi)")
                    BodyText("Joining a group — IGMP: when a host wants to receive traffic for a multicast group it sends an IGMP Membership Report to the local router. The router records which groups each subnet is interested in and forwards matching multicast traffic there. IGMPv3 adds Source-Specific Multicast (SSM) — subscribe to a specific sender only. IPv6 uses MLD (Multicast Listener Discovery, part of ICMPv6) instead of IGMP.")
                    BodyText("Does IP multicast rely on Ethernet multicast? YES. When an IPv4 multicast packet is sent over Ethernet the destination MAC is derived from the group address:")
                    CodeBlock(
                        "IPv4 multicast → Ethernet multicast MAC:\n" +
                        "  Prefix: 01:00:5E:xx:xx:xx\n" +
                        "  Lower 23 bits of the IP group → lower 23 bits of the MAC\n" +
                        "\n" +
                        "  Example: 224.0.0.251 (mDNS)\n" +
                        "    lower 23 bits of .0.0.251 → 00:00:FB\n" +
                        "    Ethernet MAC: 01:00:5E:00:00:FB\n" +
                        "\n" +
                        "IPv6 multicast → 33:33:xx:xx:xx:xx  (last 32 bits of IPv6 addr)"
                    )
                    BodyText("Caveat: only 23 of the 28 group-address bits map to the MAC, so 32 different IP multicast groups share one Ethernet MAC. A NIC may pass up frames for groups it didn't join; the IP stack filters them out.")
                    BodyText("Application API (POSIX / C):")
                    CodeBlock(
                        "struct ip_mreq mreq;\n" +
                        "mreq.imr_multiaddr.s_addr = inet_addr(\"224.0.0.251\");\n" +
                        "mreq.imr_interface.s_addr = INADDR_ANY;\n" +
                        "\n" +
                        "// Join\n" +
                        "setsockopt(fd, IPPROTO_IP, IP_ADD_MEMBERSHIP,  &mreq, sizeof(mreq));\n" +
                        "// Leave\n" +
                        "setsockopt(fd, IPPROTO_IP, IP_DROP_MEMBERSHIP, &mreq, sizeof(mreq));"
                    )
                    BodyText("Calling IP_ADD_MEMBERSHIP also triggers the kernel to send an IGMP report and configure the NIC's Ethernet multicast MAC filter automatically.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── ARP ──────────────────────────────────────────────────────
            item {
                SectionCard(title = "ARP — Address Resolution Protocol") {
                    BodyText("Before sending an Ethernet frame to another host on the same subnet, the sender needs the target's MAC address. ARP resolves IP → MAC.")
                    BodyText("How ARP works:")
                    BodyText("  1. ARP Request (broadcast): \"Who has 192.168.1.5? Tell 192.168.1.1\" — sent to FF:FF:FF:FF:FF:FF so every host on the segment sees it.")
                    BodyText("  2. ARP Reply (unicast): the host with that IP responds: \"192.168.1.5 is at aa:bb:cc:dd:ee:ff\".")
                    BodyText("  3. The sender stores the result in its ARP cache (typically 20–60 seconds) to avoid repeating the lookup.")
                    BodyText("Gratuitous ARP: a host announces its own IP→MAC mapping at startup or after an IP change, updating other hosts' caches.")
                    BodyText("IPv6 replaces ARP with Neighbor Discovery Protocol (NDP), which uses ICMPv6 messages.")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
