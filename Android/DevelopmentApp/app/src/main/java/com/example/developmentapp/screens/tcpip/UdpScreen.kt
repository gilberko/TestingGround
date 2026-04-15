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
fun UdpScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "UDP",
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

            // ── What is UDP ──────────────────────────────────────────────
            item {
                SectionCard(title = "What is UDP?") {
                    BodyText("UDP — User Datagram Protocol — is a Layer 4 (Transport) protocol that provides a simple, connectionless message delivery service. It adds port numbers and a checksum on top of IP, but nothing more.")
                    BodyText("UDP characteristics:")
                    BodyText("  • Connectionless: no handshake. You simply send a datagram and it goes.")
                    BodyText("  • Unreliable: datagrams may be lost, duplicated, or arrive out of order. UDP does nothing about it.")
                    BodyText("  • No retransmission: lost packets stay lost unless the application handles recovery itself.")
                    BodyText("  • Low overhead: the header is only 8 bytes. Great when speed matters more than reliability.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── UDP inside IP ────────────────────────────────────────────
            item {
                SectionCard(title = "UDP inside IP") {
                    BodyText("UDP sits inside the IP payload. The IP Protocol field is set to 17 (0x11) to identify the payload as UDP.")
                    CodeBlock("""
[ Ethernet Frame                                       ]
  [ IP Header (Protocol=17) | UDP Header | Data   | FCS]
                    """.trimIndent())
                    BodyText("Each UDP datagram is independent — there is no connection state. The kernel routes each incoming datagram to the correct socket based on the destination port number.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── UDP Header ───────────────────────────────────────────────
            item {
                SectionCard(title = "UDP Header") {
                    BodyText("The UDP header is just 8 bytes — the smallest possible transport-layer header:")
                    CodeBlock("""
 0                   1                   2                   3
 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|       Source Port (16b)       |    Destination Port (16b)     |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|          Length (16b)         |         Checksum (16b)        |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                          Data ...                             |
                    """.trimIndent())
                    BodyText("Source Port (16b): the sender's port. Optional — set to 0 if not needed (e.g. DHCP client before it has an IP).")
                    BodyText("Destination Port (16b): identifies the receiving application on the target host.")
                    BodyText("Length (16b): total length of the UDP header + data, in bytes. Minimum 8 (header only).")
                    BodyText("Checksum (16b): covers a pseudo-header (src/dst IP, protocol, UDP length) plus the UDP header and data. Optional in IPv4, mandatory in IPv6.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Port Numbers ─────────────────────────────────────────────
            item {
                SectionCard(title = "Port Numbers") {
                    BodyText("Port numbers are 16-bit values (0–65535) that identify specific applications or services on a host. Combined with an IP address they form a socket address.")
                    BodyText("Well-known ports (0–1023): reserved for standard services. Requires root/admin to bind.")
                    CodeBlock("""
  53   DNS
  67   DHCP server
  68   DHCP client
 123   NTP (time sync)
 161   SNMP
 514   Syslog
                    """.trimIndent())
                    BodyText("Registered ports (1024–49151): assigned to applications by IANA (e.g. 3306 MySQL, 5353 mDNS).")
                    BodyText("Ephemeral ports (49152–65535): assigned automatically by the OS to the client side of a connection. A browser makes requests from an ephemeral source port; the server replies to it.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Use Cases ────────────────────────────────────────────────
            item {
                SectionCard(title = "When to Use UDP") {
                    BodyText("UDP is best when latency matters more than guaranteed delivery, or when the application handles reliability itself:")
                    BodyText("  • DNS: query/response fits in one datagram; if lost, just retry.")
                    BodyText("  • DHCP: can't use TCP before you have an IP address.")
                    BodyText("  • Video streaming / VoIP: a retransmitted frame arrives too late to be useful; better to skip it.")
                    BodyText("  • Online gaming: game state updates are sent so frequently that a lost packet is superseded by the next one.")
                    BodyText("  • NTP (time sync): one small datagram per query.")
                    BodyText("  • Multicast / broadcast: TCP is point-to-point only; UDP supports one-to-many delivery.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── QUIC ─────────────────────────────────────────────────────
            item {
                SectionCard(title = "QUIC — UDP with Superpowers") {
                    BodyText("QUIC is a modern transport protocol developed by Google, now standardised as RFC 9000. It runs on top of UDP (port 443) and is the foundation of HTTP/3.")
                    BodyText("Why build on UDP? TCP is implemented in the OS kernel — updating it across all devices takes years. By running on UDP in userspace, QUIC can evolve quickly.")
                    BodyText("What QUIC adds on top of UDP:")
                    BodyText("  • Reliability & ordering: QUIC implements its own acknowledgements, retransmission, and flow control — per stream.")
                    BodyText("  • Multiplexed streams: multiple independent byte streams in one connection. Unlike TCP, a lost packet in stream A doesn't block stream B (no head-of-line blocking).")
                    BodyText("  • Integrated TLS 1.3: encryption is built in from the start — the handshake combines connection setup and key exchange in 1 RTT (0-RTT for resumed sessions).")
                    BodyText("  • Connection migration: a QUIC connection is identified by a Connection ID, not a 4-tuple. Switching from Wi-Fi to mobile data keeps the connection alive.")
                    CodeBlock("""
HTTP/1.1 and HTTP/2:  TCP → TLS → HTTP
HTTP/3:               UDP → QUIC (with TLS 1.3) → HTTP/3
                    """.trimIndent())
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
