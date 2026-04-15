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
fun TcpScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "TCP",
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

            // ── What is TCP ──────────────────────────────────────────────
            item {
                SectionCard(title = "What is TCP?") {
                    BodyText("TCP — Transmission Control Protocol — is a Layer 4 protocol that provides a reliable, ordered, full-duplex byte stream between two endpoints. It sits inside IP packets (Protocol=6).")
                    BodyText("TCP guarantees:")
                    BodyText("  • Reliable delivery: lost segments are detected and retransmitted.")
                    BodyText("  • Ordered delivery: bytes arrive at the application in the order they were sent.")
                    BodyText("  • Flow control: the receiver tells the sender how much buffer space is available.")
                    BodyText("  • Congestion control: TCP backs off when the network is congested.")
                    BodyText("The cost: higher overhead than UDP (20+ byte header, handshake, state machine, retransmits).")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── 3-Way Handshake ──────────────────────────────────────────
            item {
                SectionCard(title = "3-Way Handshake") {
                    BodyText("Before any data can flow, TCP establishes a connection with a 3-way handshake. This synchronises both sides' initial sequence numbers (ISNs).")
                    CodeBlock("""
Client                          Server
  |                               |
  |--- SYN (seq=x) -------------->|   Client picks random ISN x
  |                               |
  |<-- SYN-ACK (seq=y, ack=x+1) --|   Server picks ISN y,
  |                               |   acknowledges client's SYN
  |--- ACK (ack=y+1) ------------>|   Client acknowledges server
  |                               |
  |    (connection established)   |
                    """.trimIndent())
                    BodyText("After the handshake both sides know each other's starting sequence numbers and the connection is ESTABLISHED. Data can now flow in both directions simultaneously.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Graceful Close ───────────────────────────────────────────
            item {
                SectionCard(title = "Graceful Close (FIN handshake)") {
                    BodyText("Either side can initiate a close by sending a FIN segment. TCP supports half-close: one side can stop sending while still receiving.")
                    CodeBlock("""
Client (active closer)          Server (passive closer)
  |                               |
  |--- FIN (seq=u) -------------->|   Client done sending
  |<-- ACK (ack=u+1) ------------|   Server acknowledges
  |                               |
  |  (server can still send data) |
  |                               |
  |<-- FIN (seq=v) ---------------|   Server done sending
  |--- ACK (ack=v+1) ------------>|   Client acknowledges
  |                               |
  |  Client enters TIME_WAIT      |
  |  (waits 2×MSL before closing) |
                    """.trimIndent())
                    BodyText("TIME_WAIT (typically 60–120 s): ensures the final ACK reaches the server. Prevents a new connection on the same port from receiving stale packets from the old connection.")
                    BodyText("RST: an abrupt close. Either side sends RST to immediately terminate the connection (no graceful drain). Used for errors or when an unexpected segment arrives.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── ACK & Sequence Numbers ───────────────────────────────────
            item {
                SectionCard(title = "ACK & Sequence Numbers") {
                    BodyText("TCP treats the data stream as a sequence of bytes, each with a 32-bit sequence number.")
                    BodyText("Sequence Number: the sequence number of the first byte in this segment's payload.")
                    BodyText("Acknowledgement Number: the next sequence number the receiver expects. An ACK of N means 'I have received all bytes up to N-1'. This is a cumulative ACK — one ACK covers all data up to that byte.")
                    CodeBlock("""
Sender sends:    SEQ=1000  LEN=500   (bytes 1000–1499)
Sender sends:    SEQ=1500  LEN=500   (bytes 1500–1999)

Receiver ACKs:   ACK=2000            (received all up to 1999)
                    """.trimIndent())
                    BodyText("Duplicate ACKs: if a segment is lost, the receiver keeps ACKing the last good byte it received. Three duplicate ACKs trigger fast retransmit (see Congestion Control).")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Sliding Window ───────────────────────────────────────────
            item {
                SectionCard(title = "Sliding Window (Flow Control)") {
                    BodyText("The receiver's window (rwnd) — advertised in every ACK — tells the sender how many bytes it can have in flight (sent but not yet acknowledged) at any time.")
                    CodeBlock("""
rwnd = 4000 bytes (receiver has 4000 bytes of buffer free)

Sender can send up to 4000 unacked bytes without waiting:

 [sent & acked] [sent, not acked] [can send now] [cannot send yet]
 |              |<--- rwnd ------>|
                    """.trimIndent())
                    BodyText("As the receiver processes data and frees buffer space, it sends ACKs with a larger window, sliding the window forward. This is flow control: the sender cannot overwhelm the receiver's buffer.")
                    BodyText("If rwnd drops to 0 the sender stops. The sender probes periodically with a 1-byte 'window probe' until the receiver opens the window again.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Congestion Control ───────────────────────────────────────
            item {
                SectionCard(title = "Congestion Control") {
                    BodyText("In addition to the receiver's window (rwnd), TCP maintains a congestion window (cwnd) — the sender's estimate of how much data the network can handle. The actual send limit is min(rwnd, cwnd).")
                    BodyText("Slow Start: cwnd begins at 1 MSS and doubles each RTT until it hits the slow-start threshold (ssthresh) or a loss event.")
                    BodyText("Congestion Avoidance: once cwnd ≥ ssthresh, it grows linearly (+1 MSS per RTT) — probing carefully for more bandwidth.")
                    BodyText("Loss detection:")
                    BodyText("  • Timeout (RTO expired): cwnd → 1 MSS, ssthresh → cwnd/2, restart slow start from scratch. Aggressive reduction — the network may be severely congested.")
                    BodyText("  • 3 duplicate ACKs (fast retransmit): the missing segment is resent immediately. ssthresh → cwnd/2, cwnd → ssthresh (TCP Reno) or cwnd/2 (TCP CUBIC). Only a moderate reduction.")
                    BodyText("So yes — on retransmission, TCP reduces the congestion window. On a timeout it resets to 1 MSS; on 3 dup-ACKs it halves. After recovery, it grows again incrementally (AIMD: Additive Increase Multiplicative Decrease).")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── TCP Header ───────────────────────────────────────────────
            item {
                SectionCard(title = "TCP Header") {
                    BodyText("The TCP header is at least 20 bytes (without options):")
                    CodeBlock("""
 0                   1                   2                   3
 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|       Source Port (16b)       |    Destination Port (16b)     |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                       Sequence Number (32b)                   |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                    Acknowledgment Number (32b)                |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|DataOffset(4b)|Rsvd(3b)|N|C|E|U|A|P|R|S|F|   Window (16b)    |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|           Checksum (16b)      |      Urgent Pointer (16b)     |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                    Options (0–40 bytes)                       |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
                    """.trimIndent())
                    BodyText("Control flags (the N/C/E/U/A/P/R/S/F bits):")
                    BodyText("  SYN — synchronise sequence numbers (handshake)")
                    BodyText("  ACK — acknowledgment number field is valid")
                    BodyText("  FIN — no more data from sender (graceful close)")
                    BodyText("  RST — reset the connection (abort)")
                    BodyText("  PSH — push data to the application immediately (don't buffer)")
                    BodyText("  URG — urgent pointer field is valid (rarely used)")
                    BodyText("  ECE/CWR — Explicit Congestion Notification flags")
                    BodyText("Data Offset (4b): header length in 32-bit words (minimum 5 = 20 bytes).")
                    BodyText("Window (16b): receiver's advertised window size (rwnd). Scaled by the TCP Window Scale option for high-bandwidth links.")
                    BodyText("Common options: Maximum Segment Size (MSS), Window Scale, SACK (Selective ACK), Timestamps.")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
