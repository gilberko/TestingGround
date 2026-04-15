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
fun SslTlsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "SSL/TLS",
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

            // ── What is TLS ──────────────────────────────────────────────
            item {
                SectionCard(title = "What is TLS?") {
                    BodyText("TLS — Transport Layer Security — is the cryptographic protocol that secures most internet communication today. It sits between TCP (or QUIC) and the application protocol (HTTP, SMTP, etc.), providing three guarantees:")
                    BodyText("  • Confidentiality: data is encrypted — only the two endpoints can read it.")
                    BodyText("  • Integrity: any tampering with the data in transit is detected and the connection is rejected.")
                    BodyText("  • Authentication: the server's identity is verified via a certificate. Optionally, the client is also authenticated (mutual TLS / mTLS).")
                    BodyText("SSL (Secure Sockets Layer) was TLS's predecessor, developed by Netscape in the 1990s. SSL 2.0 and 3.0 are broken and deprecated. Modern software uses TLS 1.2 or TLS 1.3. 'SSL' is often used informally to mean TLS.")
                    CodeBlock("""
Protocol stack for HTTPS:
  Application: HTTP
  Security:    TLS 1.3
  Transport:   TCP
  Network:     IP
  Link:        Ethernet
                    """.trimIndent())
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── TLS Handshake ────────────────────────────────────────────
            item {
                SectionCard(title = "TLS 1.3 Handshake") {
                    BodyText("TLS 1.3 (RFC 8446) reduced the handshake to 1 round-trip (1-RTT), down from 2 in TLS 1.2. Session resumption can be 0-RTT.")
                    CodeBlock("""
Client                              Server
  |                                   |
  |--- ClientHello ------------------>|
  |    (supported ciphers,            |
  |     key_share: ECDH public key,   |
  |     TLS version)                  |
  |                                   |
  |<-- ServerHello -------------------|
  |    (chosen cipher,                |
  |     key_share: server ECDH key,   |
  |     Certificate,                  |
  |     CertificateVerify,            |
  |     Finished)                     |
  |                                   |
  |  Both sides derive session keys   |
  |  from the ECDH shared secret      |
  |                                   |
  |--- Finished (encrypted) --------->|
  |                                   |
  |  Handshake complete — 1 RTT       |
  |  Application data flows encrypted |
                    """.trimIndent())
                    BodyText("0-RTT resumption: on a resumed session, the client can send application data with the very first message (using a pre-shared ticket from the previous session). Slightly weaker security (no forward secrecy for 0-RTT data) — used carefully.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Certificates & PKI ───────────────────────────────────────
            item {
                SectionCard(title = "Certificates & PKI") {
                    BodyText("The server proves its identity by presenting an X.509 digital certificate. The certificate contains:")
                    BodyText("  • The server's domain name (Common Name or Subject Alternative Names)")
                    BodyText("  • The server's public key")
                    BodyText("  • A digital signature from a Certificate Authority (CA)")
                    BodyText("  • Validity period (not before / not after)")
                    BodyText("Chain of trust: the CA's signature is verified against an intermediate CA's certificate, which is verified against a root CA certificate. Root CA certificates are pre-installed in your OS and browsers (the trust store). If any signature in the chain is invalid, the browser shows a certificate warning.")
                    BodyText("Certificate Authorities: DigiCert, Let's Encrypt (free, automated), Sectigo, etc. Let's Encrypt has made HTTPS free and easy, dramatically increasing adoption.")
                    BodyText("Mutual TLS (mTLS): the client also presents a certificate. Used in service-to-service authentication (microservices, API access), where both parties need to prove identity.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Cipher Suites ────────────────────────────────────────────
            item {
                SectionCard(title = "Cipher Suites") {
                    BodyText("A cipher suite is a named combination of algorithms used for the different phases of TLS. TLS 1.3 reduced the list to just five modern suites:")
                    CodeBlock("""
TLS_AES_128_GCM_SHA256
TLS_AES_256_GCM_SHA384
TLS_CHACHA20_POLY1305_SHA256
TLS_AES_128_CCM_SHA256
TLS_AES_128_CCM_8_SHA256

Format: TLS _ [AEAD cipher] _ [hash for HKDF]
                    """.trimIndent())
                    BodyText("AEAD (Authenticated Encryption with Associated Data): AES-GCM and ChaCha20-Poly1305 both encrypt AND authenticate in a single pass — integrity is implicit, no separate HMAC needed.")
                    BodyText("Key exchange in TLS 1.3 is always (EC)DHE — providing Perfect Forward Secrecy. Even if the server's private key is later compromised, past sessions cannot be decrypted.")
                    BodyText("TLS 1.2 used RSA key exchange (no forward secrecy) in many deployments. This is now considered obsolete.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── TLS Record Protocol ──────────────────────────────────────
            item {
                SectionCard(title = "TLS Record Protocol") {
                    BodyText("Once the handshake is complete, data is exchanged using TLS records. Each record:")
                    BodyText("  • Is at most 16 KB")
                    BodyText("  • Has a content type (handshake, application data, alert)")
                    BodyText("  • Is independently encrypted and authenticated with the session key")
                    BodyText("  • Contains an implicit sequence number to prevent reordering or replay attacks")
                    CodeBlock("""
TLS Record:
  Content Type (1b)
  Legacy Version (2b)   — always 0x0303 in TLS 1.3
  Length (2b)
  Encrypted Data (AEAD ciphertext + auth tag)
                    """.trimIndent())
                    BodyText("Alerts: TLS has an alert mechanism for signalling errors (bad certificate, decode error, etc.) and for closing the connection gracefully (close_notify alert).")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── HTTPS and other uses ─────────────────────────────────────
            item {
                SectionCard(title = "HTTPS and Other Uses") {
                    BodyText("HTTPS = HTTP over TLS. The browser connects to TCP port 443, performs a TLS handshake, then sends HTTP requests over the encrypted channel. The padlock icon in the browser address bar shows TLS is active.")
                    BodyText("TLS is used to secure many other protocols:")
                    BodyText("  • SMTPS / STARTTLS: email submission and relay")
                    BodyText("  • IMAPS / POP3S: email retrieval")
                    BodyText("  • FTPS: file transfer (distinct from SFTP, which uses SSH)")
                    BodyText("  • LDAPS: directory services")
                    BodyText("  • MQTT over TLS: IoT messaging")
                    BodyText("  • gRPC: uses HTTP/2 over TLS")
                    BodyText("Certificate pinning: an application embeds the expected certificate or public key hash and rejects any other certificate — prevents attacks even if a rogue CA is trusted by the OS. Common in mobile apps.")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
