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
fun SmtpScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "SMTP",
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

            // ── What is SMTP ─────────────────────────────────────────────
            item {
                SectionCard(title = "What is SMTP?") {
                    BodyText("SMTP — Simple Mail Transfer Protocol — is a text-based application-layer protocol for sending email between servers and from clients to servers. It is defined in RFC 5321.")
                    BodyText("SMTP is a push protocol: the sender initiates a connection to the recipient's server and pushes the message. To retrieve mail from a server, different protocols are used: IMAP (RFC 3501) or POP3 (RFC 1939).")
                    BodyText("SMTP uses TCP for reliable delivery. The conversation is human-readable ASCII text — the server responds with 3-digit status codes.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Ports ────────────────────────────────────────────────────
            item {
                SectionCard(title = "Ports") {
                    CodeBlock(
                        "Port 25  — SMTP relay (MTA to MTA, server-to-server)\n" +
                        "           Often blocked by ISPs on residential connections\n" +
                        "           to prevent spam from compromised machines\n" +
                        "\n" +
                        "Port 587 — Submission (mail client to server)\n" +
                        "           Requires AUTH; typically uses STARTTLS to\n" +
                        "           upgrade to TLS before sending credentials\n" +
                        "\n" +
                        "Port 465 — SMTPS (implicit TLS from the start)\n" +
                        "           TLS wraps the connection before any SMTP dialog\n" +
                        "           Historically deprecated, but widely re-adopted"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Key Commands ─────────────────────────────────────────────
            item {
                SectionCard(title = "Key Commands") {
                    CodeBlock(
                        "EHLO <domain>          Greet server (Extended HELO); server\n" +
                        "                       replies with supported extensions\n" +
                        "HELO <domain>          Old greeting (no extensions)\n" +
                        "MAIL FROM:<addr>       Start a mail transaction; sets envelope sender\n" +
                        "RCPT TO:<addr>         Add a recipient (repeat for multiple)\n" +
                        "DATA                   Begin message body; end with a lone dot (.)\n" +
                        "QUIT                   Close the connection gracefully\n" +
                        "RSET                   Abort current transaction, reset state\n" +
                        "NOOP                   No-op; used to keep the connection alive\n" +
                        "VRFY <address>         Ask server if address is valid (often disabled)\n" +
                        "AUTH <mechanism>       Authenticate (LOGIN, PLAIN, CRAM-MD5, etc.)\n" +
                        "STARTTLS               Upgrade plaintext connection to TLS"
                    )
                    BodyText("Server response codes:")
                    CodeBlock(
                        "2xx  Positive (250 OK, 220 Service ready)\n" +
                        "3xx  Intermediate (354 Start mail input)\n" +
                        "4xx  Transient failure — try again later\n" +
                        "5xx  Permanent failure (550 No such user)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Session Flow ─────────────────────────────────────────────
            item {
                SectionCard(title = "Session Flow") {
                    BodyText("A typical SMTP submission session follows this order:")
                    BodyText("  1. TCP connect to server on port 587 (or 465)")
                    BodyText("  2. Server sends greeting banner (220)")
                    BodyText("  3. Client sends EHLO; server lists capabilities")
                    BodyText("  4. Client sends STARTTLS (port 587) → TLS handshake")
                    BodyText("  5. Client sends AUTH → provides credentials")
                    BodyText("  6. Client sends MAIL FROM, RCPT TO, DATA")
                    BodyText("  7. Client sends message headers + body, ends with a lone '.'")
                    BodyText("  8. Client sends QUIT")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── STARTTLS vs SMTPS ────────────────────────────────────────
            item {
                SectionCard(title = "STARTTLS vs SMTPS") {
                    BodyText("STARTTLS (opportunistic/explicit TLS):")
                    BodyText("  • Connection starts in plaintext on port 587 (or 25)")
                    BodyText("  • Client issues STARTTLS command to upgrade the connection")
                    BodyText("  • TLS handshake runs; all subsequent SMTP is encrypted")
                    BodyText("  • Downgrade attack risk: a MITM could strip STARTTLS from the server's EHLO response, forcing plaintext (SMTP STS / MTA-STS helps mitigate this)")
                    BodyText("SMTPS (implicit TLS):")
                    BodyText("  • TLS is established immediately on connect (port 465)")
                    BodyText("  • No plaintext phase at all")
                    BodyText("  • Simpler and safer — no opportunity for STARTTLS stripping")
                    BodyText("Modern recommendation: prefer port 465 (SMTPS) for mail submission.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Example Session ──────────────────────────────────────────
            item {
                SectionCard(title = "Example SMTP Session") {
                    BodyText("(Lines starting with S: are server responses, C: are client commands)")
                    CodeBlock(
                        "S: 220 mail.example.com ESMTP ready\n" +
                        "C: EHLO client.example.com\n" +
                        "S: 250-mail.example.com\n" +
                        "S: 250-STARTTLS\n" +
                        "S: 250-AUTH LOGIN PLAIN\n" +
                        "S: 250 SIZE 52428800\n" +
                        "C: STARTTLS\n" +
                        "S: 220 Go ahead\n" +
                        "   [TLS handshake]\n" +
                        "C: EHLO client.example.com\n" +
                        "S: 250-mail.example.com\n" +
                        "S: 250 AUTH LOGIN PLAIN\n" +
                        "C: AUTH PLAIN AHVzZXIAcGFzc3dvcmQ=\n" +
                        "S: 235 Authentication successful\n" +
                        "C: MAIL FROM:<alice@example.com>\n" +
                        "S: 250 OK\n" +
                        "C: RCPT TO:<bob@other.com>\n" +
                        "S: 250 OK\n" +
                        "C: DATA\n" +
                        "S: 354 End data with <CR><LF>.<CR><LF>\n" +
                        "C: From: alice@example.com\n" +
                        "C: To: bob@other.com\n" +
                        "C: Subject: Hello\n" +
                        "C: \n" +
                        "C: Hi Bob!\n" +
                        "C: .\n" +
                        "S: 250 OK: queued as 12345\n" +
                        "C: QUIT\n" +
                        "S: 221 Bye"
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
