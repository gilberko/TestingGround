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
