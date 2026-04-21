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
fun ArpScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "ARP",
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
            modifier = Modifier.padding(innerPadding).padding(horizontal = 16.dp)
        ) {
            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("What is ARP?") {
                    BodyText("ARP — Address Resolution Protocol — solves a fundamental problem in local networking: you know a device's IP address, but to actually send it an Ethernet frame you need its MAC address.")
                    BodyText("ARP operates at the boundary between Layer 2 (Ethernet/MAC) and Layer 3 (IP). Before any IP packet can leave your machine as an Ethernet frame, the kernel must know the destination MAC. ARP is how it finds out.")
                    BodyText("ARP is only used within the same subnet. If the destination is on a different subnet, the packet is sent to the default gateway and ARP is used to resolve the gateway's MAC instead.")
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("How ARP Works") {
                    BodyText("Step-by-step example: host A (192.168.1.2) wants to send a packet to host B (192.168.1.5) but has no entry for it in the ARP cache.")
                    BodyText("1. A broadcasts an ARP Request to the Ethernet broadcast address FF:FF:FF:FF:FF:FF. The payload says: \"Who has 192.168.1.5? Tell 192.168.1.2\".")
                    BodyText("2. Every device on the segment receives the broadcast. Only the device with IP 192.168.1.5 (host B) replies.")
                    BodyText("3. B sends a unicast ARP Reply directly to A: \"192.168.1.5 is at AA:BB:CC:DD:EE:FF\".")
                    BodyText("4. A stores the mapping in its ARP cache and uses the MAC to build the Ethernet frame.")
                    CodeBlock("""
# ARP Request (broadcast)
Sender IP:  192.168.1.2   Sender MAC: 11:22:33:44:55:66
Target IP:  192.168.1.5   Target MAC: 00:00:00:00:00:00 (unknown)
Dst Ethernet: FF:FF:FF:FF:FF:FF

# ARP Reply (unicast back to requester)
Sender IP:  192.168.1.5   Sender MAC: AA:BB:CC:DD:EE:FF
Target IP:  192.168.1.2   Target MAC: 11:22:33:44:55:66
Dst Ethernet: 11:22:33:44:55:66""".trimIndent())
                    BodyText("View the ARP cache with:")
                    CodeBlock("""
# Windows / Linux
arp -a

# Linux (iproute2)
ip neigh show

# Example output
192.168.1.1   dev eth0  lladdr aa:bb:cc:dd:ee:ff  REACHABLE
192.168.1.5   dev eth0  lladdr aa:bb:cc:dd:ee:00  STALE""".trimIndent())
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("ARP Cache and Gratuitous ARP") {
                    BodyText("The ARP cache holds IP→MAC mappings for a short time (typically 1–20 minutes depending on OS). Caching avoids a broadcast for every single packet to the same host.")
                    BodyText("Cache entries age out automatically. An entry moves from REACHABLE to STALE, and may be probed before being removed. If the host is still there, the entry is refreshed.")
                    BodyText("Gratuitous ARP: a host broadcasts an ARP Reply for its own IP address, unprompted. Uses:")
                    BodyText("• Boot / IP assignment — announce presence, update other hosts' caches")
                    BodyText("• IP change — flush old entries across the network")
                    BodyText("• Failover — a new interface takes over a virtual IP and claims ownership")
                    CodeBlock("""
# Gratuitous ARP: both sender and target IP are the host's own IP
Sender IP:  192.168.1.10  Sender MAC: AA:BB:CC:DD:EE:FF
Target IP:  192.168.1.10  Target MAC: FF:FF:FF:FF:FF:FF (broadcast)""".trimIndent())
                    BodyText("ARP spoofing / poisoning: an attacker sends forged ARP Replies to associate their MAC with another host's IP. Traffic meant for the victim is redirected to the attacker (man-in-the-middle). Defences include dynamic ARP inspection (DAI) on managed switches and static ARP entries for critical hosts.")
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("ARP Limitations") {
                    BodyText("• Subnet-local only — ARP broadcasts do not cross routers. Cross-subnet packets go to the gateway; the gateway is what ARP resolves.")
                    BodyText("• No authentication — any host can claim any IP→MAC mapping. This is why spoofing is trivial on unprotected networks.")
                    BodyText("• IPv4 only — IPv6 does not use ARP. It uses NDP (Neighbor Discovery Protocol, part of ICMPv6) which provides the same resolution via multicast rather than broadcast, and includes address autoconfiguration.")
                    BodyText("• Proxy ARP — a router can respond to ARP requests on behalf of hosts on another network, making them appear local. Useful in some VPN and bridging scenarios, but adds complexity.")
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
