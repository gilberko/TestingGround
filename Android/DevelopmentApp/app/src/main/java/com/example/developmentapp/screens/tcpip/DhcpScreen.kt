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
fun DhcpScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "DHCP",
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
                SectionCard("What is DHCP?") {
                    BodyText("DHCP — Dynamic Host Configuration Protocol — automatically hands out IP addresses and network configuration to devices when they join a network.")
                    BodyText("Without DHCP, every device would need a manually configured static IP address, subnet mask, default gateway, and DNS server. On a network with hundreds of devices, or on any network where devices come and go (phones connecting to Wi-Fi), manual configuration doesn't scale.")
                    BodyText("DHCP uses UDP: the client sends from port 68, the server listens on port 67. The initial exchange uses broadcast because the client has no IP yet.")
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("The DORA Process") {
                    BodyText("A new client goes through four steps — Discover, Offer, Request, Acknowledge — to obtain an address:")
                    BodyText("1. Discover — The client has no IP. It broadcasts a DHCPDISCOVER to 255.255.255.255, source IP 0.0.0.0. All DHCP servers on the segment receive it.")
                    BodyText("2. Offer — One or more servers respond with a DHCPOFFER: a proposed IP address, subnet mask, gateway, DNS servers, and lease duration.")
                    BodyText("3. Request — The client broadcasts a DHCPREQUEST accepting one of the offers (it's still a broadcast so other servers know their offer was declined).")
                    BodyText("4. Acknowledge — The chosen server sends a DHCPACK confirming the assignment. The client configures its interface and is ready to communicate.")
                    CodeBlock("""
1. DISCOVER  src=0.0.0.0:68      dst=255.255.255.255:67
             "I need an address"

2. OFFER     src=192.168.1.1:67  dst=255.255.255.255:68
             "Take 192.168.1.42, lease 24h"

3. REQUEST   src=0.0.0.0:68      dst=255.255.255.255:67
             "I accept 192.168.1.42 from server 192.168.1.1"

4. ACK       src=192.168.1.1:67  dst=255.255.255.255:68
             "Confirmed. Config attached."

Client configures:
  IP:      192.168.1.42/24
  Gateway: 192.168.1.1
  DNS:     8.8.8.8, 8.8.4.4""".trimIndent())
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("What is a Lease?") {
                    BodyText("A lease is a time-limited assignment. The DHCP server records which client (identified by MAC address) holds which IP and when that grant expires.")
                    BodyText("Why leases matter: if a device leaves the network without releasing its address (e.g. laptop lid closes, phone walks away), the server would never know. Without expiry, addresses would be permanently consumed even by gone devices, eventually exhausting the pool. Leases ensure IPs are automatically reclaimed.")
                    BodyText("Renewal timeline:")
                    BodyText("• T1 (50% of lease time) — client sends a unicast DHCPREQUEST directly to its server to renew.")
                    BodyText("• T2 (87.5% of lease time) — if T1 renewal failed, client broadcasts a DHCPREQUEST to any server.")
                    BodyText("• Expiry — if both fail, the client loses its address and must restart the full DORA process.")
                    CodeBlock("""
# Windows — shows lease obtained/expires
ipconfig /all
  Lease Obtained. . . : Monday, April 21, 2026 9:00:00 AM
  Lease Expires . . . : Tuesday, April 22, 2026 9:00:00 AM

# Linux
ip addr show eth0
# or check the DHCP client log / lease file:
cat /var/lib/dhclient/dhclient.leases""".trimIndent())
                    BodyText("Lease duration trade-offs:")
                    BodyText("• Short lease (minutes/hours) — IPs are reclaimed quickly from departed devices; more broadcast traffic from renewals.")
                    BodyText("• Long lease (days) — fewer broadcasts, more stable assignments; slow to reclaim from absent devices.")
                    BodyText("Home routers typically default to 24 hours. Corporate networks with high turnover (conference rooms, guest Wi-Fi) often use shorter leases.")
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("DHCP Options") {
                    BodyText("DHCP is not just about IP addresses. The protocol carries a rich set of options that let the server configure clients completely:")
                    CodeBlock("""
Option 1  — Subnet mask        (e.g. 255.255.255.0)
Option 3  — Default gateway    (e.g. 192.168.1.1)
Option 6  — DNS servers        (e.g. 8.8.8.8, 1.1.1.1)
Option 15 — Domain name        (e.g. corp.example.com)
Option 42 — NTP server         (for time synchronisation)
Option 51 — Lease time         (in seconds)
Option 121 — Classless static routes""".trimIndent())
                    BodyText("DHCP relay agents: DHCP uses broadcast, which routers normally do not forward. A relay agent (running on the router or a dedicated host) intercepts DHCP broadcasts and forwards them as unicast to a central DHCP server, allowing one server to serve multiple subnets and VLANs.")
                    CodeBlock("""
# Simplified relay flow
Client --broadcast--> Relay Agent (router) --unicast--> DHCP Server
DHCP Server --unicast--> Relay Agent --broadcast--> Client""".trimIndent())
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
