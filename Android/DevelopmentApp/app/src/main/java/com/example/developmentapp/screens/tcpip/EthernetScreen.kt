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
fun EthernetScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Ethernet",
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

            // ── What is Ethernet ─────────────────────────────────────────
            item {
                SectionCard(title = "What is Ethernet?") {
                    BodyText("Ethernet is the dominant Layer 2 (Data Link) technology for local area networks (LANs). It defines how devices on the same network segment communicate over a physical medium — originally coaxial cable, now twisted-pair copper (Cat5e/Cat6) or fiber optic.")
                    BodyText("Ethernet operates at the hardware level using MAC (Media Access Control) addresses — 48-bit identifiers burned into every network interface card (NIC). MAC addresses are used for delivery within a single network segment; IP addresses handle routing across segments.")
                    BodyText("Modern Ethernet runs at 100 Mbps, 1 Gbps, 10 Gbps, or faster. It uses point-to-point full-duplex links via switches — each device has a dedicated connection to the switch, eliminating most collisions.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Ethernet Frame Format ────────────────────────────────────
            item {
                SectionCard(title = "Ethernet Frame Format") {
                    BodyText("An Ethernet frame wraps the data being sent. The format (IEEE 802.3):")
                    CodeBlock("""
 0        7        8       14       20      22              22+n    22+n+4
 +---------+--------+--------+--------+-------//------+--------+
 |Preamble |  SFD   |Dst MAC |Src MAC |EtherType/Len |Payload |  FCS   |
 | 7 bytes | 1 byte | 6 bytes| 6 bytes|   2 bytes    |46-1500B| 4 bytes|
 +---------+--------+--------+--------+-------//------+--------+
                    """.trimIndent())
                    BodyText("Preamble (7 bytes): alternating 1/0 bits (0xAA...) for clock synchronisation.")
                    BodyText("SFD — Start Frame Delimiter (1 byte, 0xAB): marks the start of the actual frame.")
                    BodyText("Dst MAC / Src MAC (6 bytes each): hardware addresses. Dst FF:FF:FF:FF:FF:FF = broadcast.")
                    BodyText("EtherType / Length (2 bytes): values ≥ 0x0600 are EtherType (e.g. 0x0800 = IPv4, 0x86DD = IPv6, 0x0806 = ARP). Values < 0x0600 indicate the payload length (older 802.3 format).")
                    BodyText("Payload (46–1500 bytes): the data being carried (e.g. an IP packet). Minimum 46 bytes — shorter payloads are padded.")
                    BodyText("FCS — Frame Check Sequence (4 bytes): CRC-32 checksum computed over the frame; receiver recalculates and discards the frame if it doesn't match.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Frame Size Limits ────────────────────────────────────────
            item {
                SectionCard(title = "Frame Size Limits") {
                    BodyText("Minimum frame size: 64 bytes (header + payload + FCS). If the payload is smaller than 46 bytes, it is padded with zeros. Frames shorter than 64 bytes are called 'runts' and are discarded — they are likely collision fragments.")
                    BodyText("Maximum frame size (MTU): standard Ethernet MTU is 1500 bytes for the payload, making the maximum frame 1518 bytes (or 1522 with an 802.1Q VLAN tag).")
                    BodyText("Jumbo frames: some networks configure MTU up to 9000 bytes, reducing CPU overhead for large transfers. Both endpoints and all switches must support jumbo frames.")
                    BodyText("If an IP packet is larger than the Ethernet MTU, IP fragmentation splits it into multiple frames.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Frames Can Be Lost ───────────────────────────────────────
            item {
                SectionCard(title = "Frames Can Be Lost") {
                    BodyText("Ethernet provides best-effort delivery — there is no acknowledgement at Layer 2. A frame can be silently discarded for several reasons:")
                    BodyText("  • CRC error: the FCS checksum doesn't match (corrupted in transit). The frame is dropped with no notification.")
                    BodyText("  • Buffer overflow: a switch's incoming buffer fills up during congestion. New frames are dropped (tail-drop or RED policy).")
                    BodyText("  • Collision (legacy half-duplex): two stations transmitted simultaneously, corrupting both frames.")
                    BodyText("  • Cable or hardware fault: physical layer issues cause intermittent frame loss.")
                    BodyText("Higher-level protocols (TCP) are responsible for detecting and recovering from lost frames. UDP and raw IP do not recover — they simply lose the data.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── CSMA/CD ──────────────────────────────────────────────────
            item {
                SectionCard(title = "Collision Detection (CSMA/CD)") {
                    BodyText("CSMA/CD — Carrier Sense Multiple Access with Collision Detection — is the original Ethernet access mechanism for shared half-duplex media (hubs, coax).")
                    BodyText("How it works:")
                    BodyText("  1. Carrier Sense: before transmitting, a station listens to check if the medium is idle.")
                    BodyText("  2. Multiple Access: multiple stations share the same medium.")
                    BodyText("  3. Collision Detect: if two stations start transmitting at almost the same time, a collision is detected (voltage level exceeds normal).")
                    BodyText("  4. Jam signal: both stations send a 32-bit jam signal to ensure all nodes detect the collision.")
                    BodyText("  5. Exponential backoff: each station waits a random multiple of the slot time before retrying. After each collision the backoff window doubles (up to 10 doublings), then gives up after 16 attempts.")
                    BodyText("CSMA/CD is largely obsolete today. Full-duplex switches give each device a dedicated point-to-point link — there is no shared medium and no collisions. CSMA/CD is disabled on full-duplex links.")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
