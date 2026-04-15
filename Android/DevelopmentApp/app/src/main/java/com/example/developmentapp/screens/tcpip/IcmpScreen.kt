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
fun IcmpScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "ICMP",
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

            // ── What is ICMP ─────────────────────────────────────────────
            item {
                SectionCard(title = "What is ICMP?") {
                    BodyText("ICMP — Internet Control Message Protocol — is a Layer 3 helper protocol that IP nodes (hosts and routers) use to send error messages and operational information. It is not used for transporting application data.")
                    BodyText("ICMP messages travel inside IP packets (Protocol field = 1 for ICMPv4, 58 for ICMPv6). Every ICMP message has a Type (8b), Code (8b), Checksum (16b), and a variable-length body.")
                    CodeBlock("""
[ Ethernet Frame                                ]
  [ IP Header | ICMP Message                   ]
                [ Type | Code | Checksum | Data ]
                    """.trimIndent())
                    BodyText("ICMP is a diagnostic and signalling layer — it tells you when something went wrong, but does nothing to fix it. Higher-level protocols are responsible for recovery.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Common ICMP Types ────────────────────────────────────────
            item {
                SectionCard(title = "Common ICMP Types") {
                    BodyText("Type 0 — Echo Reply: response to an Echo Request (ping reply).")
                    BodyText("Type 3 — Destination Unreachable: a router or the destination host cannot deliver the packet. The Code field gives the reason:")
                    BodyText("  Code 0: Network unreachable (no route in routing table)")
                    BodyText("  Code 1: Host unreachable (on the network but not responding)")
                    BodyText("  Code 3: Port unreachable (host is up, no process listening on that port — common with UDP)")
                    BodyText("  Code 4: Fragmentation needed but DF bit set (used for Path MTU Discovery)")
                    BodyText("Type 5 — Redirect: a router tells a host to use a better route for future packets to a destination.")
                    BodyText("Type 8 — Echo Request: sent by ping to ask a host if it is reachable.")
                    BodyText("Type 11 — Time Exceeded:")
                    BodyText("  Code 0: TTL expired in transit (used by traceroute)")
                    BodyText("  Code 1: Fragment reassembly timeout")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── ping ─────────────────────────────────────────────────────
            item {
                SectionCard(title = "ping") {
                    BodyText("ping sends ICMP Echo Request packets to a destination and waits for Echo Reply responses. It measures round-trip time (RTT) and reports packet loss.")
                    CodeBlock("""
$ ping 8.8.8.8
PING 8.8.8.8 (8.8.8.8): 56 data bytes
64 bytes from 8.8.8.8: icmp_seq=0 ttl=116 time=14.2 ms
64 bytes from 8.8.8.8: icmp_seq=1 ttl=116 time=13.8 ms
64 bytes from 8.8.8.8: icmp_seq=2 ttl=116 time=14.1 ms

--- 8.8.8.8 ping statistics ---
3 packets transmitted, 3 received, 0% packet loss
round-trip min/avg/max = 13.8/14.0/14.2 ms
                    """.trimIndent())
                    BodyText("If no reply is received, ping reports 'Request timeout'. This can mean the host is down, a firewall is blocking ICMP, or routing is broken.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── traceroute ───────────────────────────────────────────────
            item {
                SectionCard(title = "traceroute") {
                    BodyText("traceroute (tracert on Windows) reveals the path packets take across the network by exploiting the TTL field. It sends a series of probes with increasing TTL values:")
                    BodyText("  TTL=1: first router decrements to 0, drops the packet, sends back ICMP Time Exceeded (Type 11, Code 0). traceroute records the router's IP and RTT.")
                    BodyText("  TTL=2: second router responds similarly.")
                    BodyText("  TTL=n: eventually the probe reaches the destination and gets an ICMP Echo Reply (or Port Unreachable for UDP probes).")
                    CodeBlock("""
$ traceroute google.com
 1  192.168.1.1        1.2 ms    (home router)
 2  10.0.0.1           8.4 ms    (ISP gateway)
 3  72.14.196.100     12.1 ms
 4  142.250.56.196    13.5 ms
 5  lax17s55-in-f14   14.2 ms   (google.com)
                    """.trimIndent())
                    BodyText("'* * *' lines mean that router dropped the probe without sending ICMP back (filtered by a firewall), or the probe timed out.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── ICMPv6 ───────────────────────────────────────────────────
            item {
                SectionCard(title = "ICMPv6") {
                    BodyText("ICMPv6 serves the same role as ICMPv4 but is extended with features that were separate protocols in IPv4:")
                    BodyText("Neighbor Discovery Protocol (NDP): replaces ARP. Uses ICMPv6 Neighbor Solicitation (Type 135) and Neighbor Advertisement (Type 136) to resolve IPv6 addresses to MAC addresses.")
                    BodyText("Router Discovery: hosts use Router Solicitation (Type 133) to find routers; routers reply with Router Advertisement (Type 134) announcing the network prefix, so hosts can configure their own IPv6 address without DHCP (SLAAC).")
                    BodyText("Path MTU Discovery: uses ICMPv6 'Packet Too Big' (Type 2) messages so the source can learn the minimum MTU along a path and avoid fragmentation.")
                    BodyText("ping6 / ping -6 sends ICMPv6 Echo Request (Type 128) and receives Echo Reply (Type 129).")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
