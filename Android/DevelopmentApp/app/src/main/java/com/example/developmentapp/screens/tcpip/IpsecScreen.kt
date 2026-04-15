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
fun IpsecScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "IPSec",
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

            // ── What is IPSec ────────────────────────────────────────────
            item {
                SectionCard(title = "What is IPSec?") {
                    BodyText("IPSec — IP Security — is a suite of protocols that adds authentication and encryption at Layer 3. Unlike TLS (which secures a single TCP connection at Layer 4+), IPSec secures all IP traffic between two endpoints, regardless of which application or protocol is being used.")
                    BodyText("IPSec is defined by a collection of RFCs and consists of three main components:")
                    BodyText("  • AH (Authentication Header): integrity + authentication, no encryption")
                    BodyText("  • ESP (Encapsulating Security Payload): encryption + optional integrity")
                    BodyText("  • IKE (Internet Key Exchange): key negotiation and SA management")
                    BodyText("IPSec is the foundation of most VPN solutions (site-to-site corporate VPNs, remote-access VPNs on mobile devices).")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Two Modes ────────────────────────────────────────────────
            item {
                SectionCard(title = "Transport Mode vs Tunnel Mode") {
                    BodyText("Transport Mode: IPSec header is inserted between the original IP header and the payload. The original IP addresses are visible. Used for host-to-host communication (e.g. a client directly connecting to a server).")
                    CodeBlock("""
Transport Mode:
  [ Original IP Header | IPSec Header | TCP/UDP | Data ]
                          ^^^^^^^^^^^^ AH or ESP
                    """.trimIndent())
                    BodyText("Tunnel Mode: the entire original IP packet (header + payload) is encapsulated inside a new IP packet with new source/destination addresses. The inner addresses are hidden. Used by VPN gateways.")
                    CodeBlock("""
Tunnel Mode:
  [ New IP Header | IPSec Header | Original IP Header | TCP/UDP | Data ]
                    ^^^^^^^^^^^^ AH or ESP
                    Outer: gateway IPs
                                          Inner: real client/server IPs
                    """.trimIndent())
                    BodyText("VPN gateways use Tunnel Mode: traffic between two offices is wrapped in an IPSec tunnel between the two gateway IP addresses. The private IP addresses inside are hidden from the internet.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── AH ───────────────────────────────────────────────────────
            item {
                SectionCard(title = "AH — Authentication Header (Protocol 51)") {
                    BodyText("AH provides data integrity and origin authentication for IP packets. It does NOT encrypt the data — the payload is visible in plaintext.")
                    BodyText("AH computes an HMAC (Hash-based MAC) over the entire packet including most IP header fields. The receiver recomputes the HMAC and verifies it. This detects any tampering.")
                    BodyText("Limitation: AH authenticates the IP header, which means it does not work through NAT (NAT rewrites IP addresses and ports, breaking the HMAC). In practice, ESP with authentication is preferred over AH.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── ESP ───────────────────────────────────────────────────────
            item {
                SectionCard(title = "ESP — Encapsulating Security Payload (Protocol 50)") {
                    BodyText("ESP is the most commonly used IPSec protocol. It provides:")
                    BodyText("  • Confidentiality (encryption): the payload is encrypted with a symmetric cipher (AES-CBC, AES-GCM, ChaCha20).")
                    BodyText("  • Optional integrity & authentication: an HMAC or AEAD tag covers the ESP header + ciphertext.")
                    BodyText("  • Anti-replay protection: a sequence number in the ESP header prevents attackers from re-sending captured packets.")
                    CodeBlock("""
ESP Packet layout:
  SPI (32b) | Seq# (32b) | IV (variable)
  | Encrypted payload + Padding + Pad Length + Next Header |
  | Integrity Check Value (ICV, variable) |
                    """.trimIndent())
                    BodyText("SPI — Security Parameters Index: identifies which Security Association (SA) to use for decryption.")
                    BodyText("ESP works through NAT (NAT-T: NAT Traversal): ESP packets are wrapped in UDP port 4500 to allow NAT devices to translate them.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── IKE ───────────────────────────────────────────────────────
            item {
                SectionCard(title = "IKE — Internet Key Exchange") {
                    BodyText("IKE (IKEv2 is the current version) is the control-plane protocol that negotiates and manages Security Associations before any encrypted traffic flows.")
                    BodyText("IKE Phase 1 (IKE_SA_INIT): establishes a secure channel between the two peers using Diffie-Hellman key exchange. After this, all further IKE messages are encrypted.")
                    BodyText("IKE Phase 2 (IKE_AUTH + CREATE_CHILD_SA): authenticates the peers (using certificates or a pre-shared key), and negotiates the actual IPSec SAs (which cipher suite, which traffic to protect).")
                    BodyText("IKE uses UDP port 500 (or 4500 when NAT-T is active).")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Security Association ─────────────────────────────────────
            item {
                SectionCard(title = "Security Association (SA)") {
                    BodyText("An SA is a one-way agreement between two peers that defines how to protect traffic in one direction:")
                    BodyText("  • Which protocol (AH or ESP)")
                    BodyText("  • Which encryption/authentication algorithm and key")
                    BodyText("  • The SPI (Security Parameters Index) — a 32-bit identifier included in every packet so the receiver can look up the correct SA")
                    BodyText("Full bidirectional communication requires two SAs (one for each direction). IKE negotiates them in pairs.")
                    BodyText("SAs have a lifetime (time- or byte-based). IKE renegotiates keys before expiry (Perfect Forward Secrecy: new Diffie-Hellman exchange means compromising one session's key doesn't compromise past or future sessions).")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Common Uses ───────────────────────────────────────────────
            item {
                SectionCard(title = "Common Uses") {
                    BodyText("Site-to-site VPN: two office routers establish an IPSec tunnel over the internet. All traffic between the offices is encrypted transparently — the hosts don't need to know about IPSec.")
                    BodyText("Remote access VPN: an employee's laptop connects to the corporate network. On Android/iOS/Windows, IKEv2/IPSec is a built-in VPN option. L2TP/IPSec wraps the L2TP tunnel inside IPSec for legacy clients.")
                    BodyText("Host-to-host: individual servers communicating with mandatory encryption (e.g. storage networks, inter-datacenter links).")
                    BodyText("IPSec vs TLS: IPSec secures at the IP layer (all traffic, transparent to applications). TLS secures individual application connections (HTTP, SMTP, etc.). They are complementary — IPSec for infrastructure, TLS for applications.")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
