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
fun VpnScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "TCP/IP — VPN",
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

            // ── What Is a VPN ─────────────────────────────────────────────
            item {
                SectionCard(title = "What Is a VPN?") {
                    BodyText("VPN — Virtual Private Network — creates an encrypted tunnel between two endpoints over an untrusted network (typically the internet). From the perspective of the devices inside the VPN, they appear to be on the same private network even though they are physically far apart.")
                    BodyText("Key goals:")
                    BodyText("  • Confidentiality: traffic is encrypted; eavesdroppers see ciphertext")
                    BodyText("  • Authentication: both endpoints verify each other's identity")
                    BodyText("  • Integrity: packets cannot be tampered with in transit")
                    BodyText("  • Private addressing: devices get private IP addresses and communicate as if directly connected")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── How VPN Works ─────────────────────────────────────────────
            item {
                SectionCard(title = "How VPN Works — Tunneling") {
                    BodyText("Tunneling is the core mechanism. The original (inner) packet is wrapped inside a new (outer) packet:")
                    CodeBlock(
                        """
[ Outer IP Header ] [ VPN Header ] [ Encrypted Inner Packet ]
  ^^^^^^^^^^^^^^^^^^^               ^^^^^^^^^^^^^^^^^^^^^^^^^
  routed over internet              original traffic, hidden
                        """.trimIndent()
                    )
                    BodyText("Step by step:")
                    BodyText("  1. Client sends a packet to a private destination (e.g. 10.0.1.5)")
                    BodyText("  2. VPN client software intercepts the packet before it leaves the host")
                    BodyText("  3. The packet is encrypted and encapsulated in a new packet addressed to the VPN server's public IP")
                    BodyText("  4. The outer packet is routed over the internet normally")
                    BodyText("  5. VPN server receives, decrypts, strips the outer header, and forwards the inner packet to the private destination")
                    BodyText("  6. Return traffic follows the reverse path")
                    BodyText("The client is assigned a virtual IP address from the VPN server's private subnet. To other machines on that subnet, the client looks like a local host.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Tunnel vs Transport Mode ───────────────────────────────────
            item {
                SectionCard(title = "Tunnel Mode vs Transport Mode") {
                    BodyText("These two modes apply specifically to IPSec (but the concept generalizes):")
                    BodyText("Tunnel Mode — the entire original IP packet (header + payload) is encrypted and wrapped in a new IP packet. The inner IP addresses are hidden. This is the mode used by VPN gateways.")
                    CodeBlock(
                        """
Tunnel Mode:
[ New IP Hdr | IPSec Hdr | Orig IP Hdr | TCP | Data ]
               ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
               all encrypted / protected
                        """.trimIndent()
                    )
                    BodyText("Transport Mode — only the payload (TCP/UDP + data) is encrypted; the original IP header remains in plaintext. The outer IP addresses are visible. Used for direct host-to-host IPSec (e.g. a server protecting its own traffic).")
                    CodeBlock(
                        """
Transport Mode:
[ IP Hdr | IPSec Hdr | TCP | Data ]
            ^^^^^^^^^^^^^^^^^^^
            encrypted / protected
                        """.trimIndent()
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── VPN with IPSec ─────────────────────────────────────────────
            item {
                SectionCard(title = "VPN with IPSec") {
                    BodyText("IPSec is the most widely deployed VPN technology for enterprise use. A typical IKEv2/IPSec VPN connection proceeds in two phases:")
                    BodyText("Phase 1 — IKE_SA_INIT:")
                    BodyText("  • Client and server exchange Diffie-Hellman public keys")
                    BodyText("  • Both derive a shared secret without transmitting it")
                    BodyText("  • A secure channel (IKE SA) is established; all further IKE messages are encrypted")
                    BodyText("Phase 2 — IKE_AUTH + CREATE_CHILD_SA:")
                    BodyText("  • Peers authenticate (certificates or pre-shared key)")
                    BodyText("  • They negotiate the IPSec SA: cipher (AES-GCM), integrity (HMAC-SHA2), lifetime")
                    BodyText("  • Two unidirectional SAs are created (one per direction)")
                    BodyText("  • Traffic protection begins using ESP (Encapsulating Security Payload)")
                    CodeBlock(
                        """
Packet flow (client → server):
  App data
    → encapsulate in ESP (encrypt with AES, add ICV)
    → add new IP header (client public IP → VPN server IP)
    → send over internet
  VPN server receives:
    → look up SA by SPI in ESP header
    → decrypt + verify ICV
    → strip outer IP + ESP headers
    → forward inner packet to private network
                        """.trimIndent()
                    )
                    BodyText("ESP uses protocol number 50. IKE uses UDP port 500 (or 4500 when NAT-Traversal is active, which wraps ESP in UDP).")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Common VPN Protocols ───────────────────────────────────────
            item {
                SectionCard(title = "Common VPN Protocols") {
                    BodyText("IKEv2/IPSec — the enterprise standard. Fast reconnect (MOBIKE extension handles IP changes, great for mobile). Built into Android, iOS, Windows, macOS. Uses ESP for data encryption.")
                    BodyText("OpenVPN — TLS-based VPN. Runs over TCP or UDP (typically UDP 1194). Very flexible and open-source. Widely supported but requires a client app. Slightly more overhead than IKEv2.")
                    BodyText("WireGuard — modern, minimal protocol. Uses state-of-the-art cryptography (ChaCha20, Curve25519, BLAKE2). Very small codebase (~4000 lines vs ~400,000 for OpenVPN). Fast and simple to configure. Built into the Linux kernel since 5.6. Increasingly popular.")
                    BodyText("L2TP/IPSec — Layer 2 Tunneling Protocol wrapped in IPSec. Legacy. L2TP provides the tunnel structure; IPSec provides encryption. Being phased out in favor of IKEv2.")
                    BodyText("SSL VPN — uses TLS (like OpenVPN) or can be browser-based (HTTPS portal). Useful when UDP is blocked by firewalls.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Use Cases ─────────────────────────────────────────────────
            item {
                SectionCard(title = "Use Cases") {
                    BodyText("Remote Access VPN — an employee working from home connects to the corporate VPN server. Their device gets a corporate IP and can access internal resources (file servers, databases, intranet) as if sitting in the office.")
                    BodyText("Site-to-Site VPN — two office locations each have a VPN gateway (router/firewall). The gateways establish a permanent tunnel. All traffic between offices flows through the tunnel transparently — individual hosts don't need VPN clients.")
                    BodyText("Split Tunneling — only traffic destined for private subnets goes through the VPN tunnel; internet-bound traffic exits directly from the client. Reduces VPN server load and improves performance for browsing.")
                    BodyText("Full Tunnel — all traffic, including internet browsing, is routed through the VPN server. Useful when corporate policy requires all traffic to pass through a firewall/proxy.")
                    BodyText("Privacy/Anonymization — consumer VPN services: the client tunnels all traffic to a VPN provider's server, hiding the client's real IP from websites and ISPs. The VPN provider's IP appears as the source.")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
