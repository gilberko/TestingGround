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
fun QuicScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "QUIC",
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

            // ── What is QUIC ─────────────────────────────────────────────
            item {
                SectionCard(title = "What is QUIC?") {
                    BodyText("QUIC is a general-purpose transport-layer protocol designed to be faster and more secure than TCP+TLS. It is standardized by the IETF as RFC 9000 (2021) and is the underlying transport for HTTP/3.")
                    BodyText("Key characteristics:")
                    BodyText("  • Runs over UDP (not TCP)")
                    BodyText("  • Supports multiple independent streams in a single connection")
                    BodyText("  • TLS 1.3 encryption is mandatory and built-in")
                    BodyText("  • Reduces connection setup latency (1-RTT, or 0-RTT on resumption)")
                    BodyText("  • Handles connection migration when the client's IP changes")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── History ──────────────────────────────────────────────────
            item {
                SectionCard(title = "History") {
                    BodyText("QUIC was developed at Google starting around 2012 as an experiment to reduce web latency. Google deployed it in Chrome and their servers before it was standardized.")
                    BodyText("Timeline:")
                    BodyText("  • 2012 — Google begins experimenting with QUIC internally")
                    BodyText("  • 2013 — Google deploys QUIC in Chrome and Google servers")
                    BodyText("  • 2015 — Google submits QUIC to the IETF for standardization")
                    BodyText("  • 2018 — HTTP/3 draft adopts QUIC as its transport layer")
                    BodyText("  • 2021 — IETF publishes RFC 9000 (QUIC) and RFC 9114 (HTTP/3)")
                    BodyText("The IETF version of QUIC differs from Google's original implementation. Google's original is now called 'gQUIC'; the standard is simply 'QUIC'.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── UDP Foundation ───────────────────────────────────────────
            item {
                SectionCard(title = "Why UDP?") {
                    BodyText("QUIC deliberately chose UDP as its carrier rather than TCP, for two reasons:")
                    BodyText("1. Deployability: UDP passes through NATs and firewalls without needing OS or middlebox changes. A new TCP-like protocol at Layer 4 would require kernel updates everywhere.")
                    BodyText("2. Control: QUIC implements its own reliability, flow control, and congestion control in user space (inside the QUIC library), so these mechanisms can be updated independently of the OS — unlike TCP which is part of the kernel.")
                    BodyText("QUIC takes raw UDP datagrams and builds a reliable, ordered, multiplexed transport on top of them.")
                    CodeBlock(
                        "IP → UDP → QUIC (reliability + streams + TLS 1.3)\n" +
                        "IP → TCP → TLS  (reliability + encryption, separate layers)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Multiple Streams ─────────────────────────────────────────
            item {
                SectionCard(title = "Multiple Streams (Multiplexing)") {
                    BodyText("A single QUIC connection carries multiple independent streams. Each stream has its own stream ID and delivers data in order within that stream.")
                    BodyText("This solves the Head-of-Line (HoL) blocking problem:")
                    BodyText("  • In TCP, all data shares one byte stream. If one packet is lost, all data behind it is held up — even data for completely unrelated requests.")
                    BodyText("  • In QUIC, each stream is independent. A lost packet only blocks the stream it belongs to. Other streams continue unaffected.")
                    CodeBlock(
                        "QUIC connection\n" +
                        "  Stream 1: HTML page\n" +
                        "  Stream 3: CSS file      ← loss here does NOT block Stream 5\n" +
                        "  Stream 5: JS file\n" +
                        "  Stream 7: image\n" +
                        "\n" +
                        "TCP connection (HTTP/2)\n" +
                        "  Single byte stream: [HTML][CSS][JS][image]\n" +
                        "  A lost packet blocks ALL of the above"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Acknowledgment Mechanism ─────────────────────────────────
            item {
                SectionCard(title = "Acknowledgment Mechanism") {
                    BodyText("QUIC's ACK system is different from TCP's:")
                    BodyText("TCP ACK:")
                    BodyText("  • Cumulative ACK: 'I have received everything up to byte N'")
                    BodyText("  • Retransmitted packets reuse the original sequence number, making it hard to distinguish original from retransmit (retransmission ambiguity)")
                    BodyText("QUIC ACK:")
                    BodyText("  • Each QUIC packet has a unique, monotonically increasing packet number — retransmissions always use a new packet number")
                    BodyText("  • ACK frames can acknowledge arbitrary ranges: 'I received packets 1-5 and 8-10 but not 6-7' (always selective)")
                    BodyText("  • No retransmission ambiguity: the sender can calculate exact RTT from ACKs")
                    BodyText("  • ACKs are at the connection level; ordering and delivery guarantees are per-stream")
                    CodeBlock(
                        "QUIC ACK frame example:\n" +
                        "  Largest Acked: 10\n" +
                        "  ACK Ranges:    [8-10], [1-5]\n" +
                        "  (packets 6 and 7 are missing — sender retransmits them)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Encryption: TLS 1.3 Integrated ──────────────────────────
            item {
                SectionCard(title = "Encryption — TLS 1.3 Integrated") {
                    BodyText("QUIC mandates encryption. TLS 1.3 is not layered on top of QUIC — it is integrated into the QUIC handshake itself.")
                    BodyText("With TCP+TLS, setup requires:")
                    BodyText("  • TCP 3-way handshake: 1 RTT")
                    BodyText("  • TLS 1.3 handshake: 1 RTT")
                    BodyText("  • Total: 2 RTTs before the first byte of application data")
                    BodyText("With QUIC:")
                    BodyText("  • Initial QUIC handshake (crypto + transport): 1 RTT")
                    BodyText("  • 0-RTT resumption: client sends application data in the very first packet (for resumed sessions), 0 RTTs of setup overhead")
                    CodeBlock(
                        "TCP + TLS 1.3                QUIC\n" +
                        "  Client → SYN               Client → Initial (crypto)\n" +
                        "  Server → SYN-ACK            Server → Handshake + cert\n" +
                        "  Client → ACK                Client → Finished\n" +
                        "  Client → ClientHello        Client → [app data]  ← 1 RTT\n" +
                        "  Server → ServerHello+cert\n" +
                        "  Client → Finished\n" +
                        "  Client → [app data]  ← 2 RTTs"
                    )
                    BodyText("QUIC also encrypts its packet headers (beyond the first byte), making it harder for middleboxes to inspect or modify QUIC packets — a deliberate design goal.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── QUIC vs TCP ──────────────────────────────────────────────
            item {
                SectionCard(title = "QUIC vs TCP") {
                    CodeBlock(
                        "Feature              TCP + TLS          QUIC\n" +
                        "Transport            TCP (kernel)       UDP (user space)\n" +
                        "Encryption           TLS (separate)     TLS 1.3 (built-in)\n" +
                        "Connection setup     2+ RTTs            1 RTT (0-RTT resume)\n" +
                        "Streams              No (1 byte stream) Yes (independent)\n" +
                        "HoL blocking         Yes                No (per stream)\n" +
                        "ACK                  Cumulative         Selective ranges\n" +
                        "Retransmit ambiguity Yes                No (new pkt numbers)\n" +
                        "Conn migration       No                 Yes (connection ID)\n" +
                        "Header encryption    No                 Yes\n" +
                        "OS update needed     Yes (kernel TCP)   No (user space lib)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Connection Migration ──────────────────────────────────────
            item {
                SectionCard(title = "Connection Migration") {
                    BodyText("TCP connections are identified by a 4-tuple: (src IP, src port, dst IP, dst port). If any of these change — e.g., a phone switches from WiFi to LTE — the TCP connection breaks and must be re-established.")
                    BodyText("QUIC connections are identified by a Connection ID (CID) chosen by the client. The CID is carried in every QUIC packet. The IP addresses are irrelevant to the QUIC connection identity.")
                    BodyText("When a client's IP changes (WiFi → LTE, roaming), QUIC can continue the same connection seamlessly. The server sees the new IP in the UDP packet but matches it to the existing connection via the CID.")
                    BodyText("This is especially valuable for mobile clients and video calls where network switches would otherwise cause interruptions.")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
