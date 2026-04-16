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
fun ProxyScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "TCP/IP — Proxy",
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

            // ── What Is a Proxy ────────────────────────────────────────────
            item {
                SectionCard(title = "What Is a Proxy?") {
                    BodyText("A proxy server sits between a client and one or more destination servers. Instead of connecting directly, the client sends its requests to the proxy, and the proxy forwards them on behalf of the client.")
                    CodeBlock(
                        """
Without proxy:
  Client ──────────────────────────── Server

With proxy:
  Client ──── Proxy ──── Server
                        """.trimIndent()
                    )
                    BodyText("The proxy can inspect, cache, modify, block, or log requests and responses. From the server's perspective, the client appears to be the proxy's IP address.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Forward Proxy ──────────────────────────────────────────────
            item {
                SectionCard(title = "Forward Proxy") {
                    BodyText("A forward proxy acts on behalf of clients. The client is explicitly configured (or forced by network policy) to send its requests to the proxy instead of directly to the destination.")
                    BodyText("How it works:")
                    BodyText("  1. Client sends HTTP request to the proxy (e.g. GET http://example.com/ HTTP/1.1)")
                    BodyText("  2. Proxy fetches the resource from example.com")
                    BodyText("  3. Proxy returns the response to the client")
                    BodyText("Common uses:")
                    BodyText("  • Caching: proxy stores responses; subsequent requests for the same resource are served from cache, saving bandwidth")
                    BodyText("  • Content filtering: corporate/school networks block certain sites at the proxy")
                    BodyText("  • Anonymization: the destination server sees the proxy's IP, not the client's real IP")
                    BodyText("  • Firewall bypass: clients behind a restrictive firewall route traffic through a proxy in an unrestricted location")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Reverse Proxy ──────────────────────────────────────────────
            item {
                SectionCard(title = "Reverse Proxy") {
                    BodyText("A reverse proxy acts on behalf of servers. It sits in front of one or more backend servers; clients talk to the proxy thinking it is the actual server. The clients don't know about the backends.")
                    CodeBlock(
                        """
Client ──── Reverse Proxy (Nginx) ──── Backend 1
                                   ──── Backend 2
                                   ──── Backend 3
                        """.trimIndent()
                    )
                    BodyText("Common uses:")
                    BodyText("  • Load balancing: distribute incoming requests across multiple backend servers")
                    BodyText("  • SSL/TLS termination: the proxy handles TLS; backends receive plain HTTP, simplifying certificate management")
                    BodyText("  • Caching: proxy caches static content (images, JS, CSS) so backends aren't hit for every request")
                    BodyText("  • Web Application Firewall (WAF): proxy inspects requests and blocks attacks (SQLi, XSS) before they reach the backend")
                    BodyText("  • Compression and optimization: proxy can gzip responses or resize images")
                    BodyText("Popular reverse proxies: Nginx, HAProxy, Envoy, Caddy, AWS ALB.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── HTTP CONNECT ───────────────────────────────────────────────
            item {
                SectionCard(title = "HTTP CONNECT — Tunneling HTTPS Through a Proxy") {
                    BodyText("Plain HTTP requests can be forwarded by a proxy because the proxy reads the Host header and full URL. But HTTPS is encrypted — the proxy can't read it to know where to forward it.")
                    BodyText("Solution: the HTTP CONNECT method. The client asks the proxy to open a raw TCP tunnel to the destination, then does TLS directly through that tunnel.")
                    BodyText("Step by step:")
                    CodeBlock(
                        """
1. Client → Proxy (plaintext):
   CONNECT api.example.com:443 HTTP/1.1
   Host: api.example.com:443

2. Proxy opens a TCP connection to api.example.com:443

3. Proxy → Client (plaintext):
   HTTP/1.1 200 Connection Established

4. Client begins TLS handshake directly with api.example.com
   through the proxy's TCP tunnel.
   The proxy sees only encrypted bytes — it cannot read the content.

5. Normal HTTPS traffic flows through the tunnel.
                        """.trimIndent()
                    )
                    BodyText("The proxy is acting as a dumb pipe for HTTPS — it tunnels bytes without understanding them. This is how browsers use HTTP proxies for HTTPS sites.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── SSL/TLS Inspection ─────────────────────────────────────────
            item {
                SectionCard(title = "SSL/TLS Inspection (Proxy as MITM)") {
                    BodyText("Some forward proxies (in corporate or security environments) perform SSL/TLS inspection — they decrypt, inspect, and re-encrypt HTTPS traffic. This is a controlled man-in-the-middle.")
                    BodyText("How it works:")
                    BodyText("  1. Client connects to the proxy using CONNECT (as above)")
                    BodyText("  2. Instead of passing through blindly, the proxy terminates the TLS connection from the client. It presents a dynamically generated certificate for the target domain, signed by a corporate CA.")
                    BodyText("  3. The corporate CA certificate is pre-installed as a trusted root on all company devices — so the client's browser trusts the forged certificate.")
                    BodyText("  4. The proxy opens a separate TLS connection to the real server and verifies the server's legitimate certificate.")
                    BodyText("  5. The proxy decrypts traffic from the client, inspects it (for malware, DLP, policy violations), then re-encrypts and forwards to the server. Same in reverse.")
                    CodeBlock(
                        """
Client ──[TLS to proxy cert]── Proxy ──[TLS to real cert]── Server
          ^^^^^^^^^^^^^^^^^^^^         ^^^^^^^^^^^^^^^^^^^
          proxy decrypts here          proxy encrypts here
          can inspect content
                        """.trimIndent()
                    )
                    BodyText("Limitations and concerns:")
                    BodyText("  • Certificate pinning: some apps (mobile banking, etc.) pin the server's certificate and reject unknown CAs — SSL inspection breaks these apps")
                    BodyText("  • Privacy: the proxy operator can read all traffic — users must trust the organization")
                    BodyText("  • Legal/compliance: some regulations restrict decrypting certain traffic (e.g. health data)")
                    BodyText("Used by: corporate security gateways, parental controls, some antivirus products.")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
