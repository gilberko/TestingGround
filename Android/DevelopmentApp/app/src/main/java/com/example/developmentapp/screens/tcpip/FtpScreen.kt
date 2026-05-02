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
fun FtpScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "FTP",
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

            // ── What is FTP ──────────────────────────────────────────────
            item {
                SectionCard(title = "What is FTP?") {
                    BodyText("FTP — File Transfer Protocol — is an application-layer protocol for transferring files between a client and a server over TCP. It is defined in RFC 959 (1985).")
                    BodyText("FTP is unique in that it uses two separate TCP connections:")
                    BodyText("  • Control connection (port 21): carries commands and responses as ASCII text, remains open for the duration of the session")
                    BodyText("  • Data connection: opened on demand for each file transfer or directory listing, then closed")
                    BodyText("The way the data connection is established depends on the mode: Active or Passive.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Active Mode ──────────────────────────────────────────────
            item {
                SectionCard(title = "Active Mode") {
                    BodyText("In Active mode, the client tells the server which port to connect back to, and the server initiates the data connection.")
                    CodeBlock(
                        "1. Client connects to server port 21 (control)\n" +
                        "2. Client sends: PORT 192,168,1,10,200,50\n" +
                        "   (= client IP 192.168.1.10, port 200*256+50 = 51250)\n" +
                        "3. Server opens data connection FROM its port 20\n" +
                        "   TO the client's IP:51250\n" +
                        "4. File transfer happens\n" +
                        "5. Server closes the data connection"
                    )
                    BodyText("Problem with Active mode: the server initiates an inbound TCP connection to the client. If the client is behind NAT or a firewall, inbound connections from port 20 are typically blocked. Active mode often fails for clients on home networks or behind corporate firewalls.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Passive Mode ─────────────────────────────────────────────
            item {
                SectionCard(title = "Passive Mode") {
                    BodyText("In Passive mode, the client asks the server to open a port, and then the client initiates the data connection. All connections are outgoing from the client's side.")
                    CodeBlock(
                        "1. Client connects to server port 21 (control)\n" +
                        "2. Client sends: PASV\n" +
                        "3. Server opens a random high port (e.g. 50000)\n" +
                        "   and replies: 227 Entering Passive Mode\n" +
                        "   (192,168,1,1,195,80)  → port 195*256+80 = 50000\n" +
                        "4. Client connects TO server IP:50000 (data connection)\n" +
                        "5. File transfer happens\n" +
                        "6. Server closes the data connection"
                    )
                    BodyText("Passive mode is firewall-friendly for the client because the client makes only outgoing connections — the same pattern as a regular web browser. This is why virtually all modern FTP clients (and browsers) default to Passive mode.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Firewall Considerations ──────────────────────────────────
            item {
                SectionCard(title = "Firewall Considerations") {
                    BodyText("Active mode:")
                    BodyText("  • Client firewall must allow inbound TCP connections from server port 20")
                    BodyText("  • NAT makes this even harder: the server connects to the client's public IP, but the NAT device doesn't know which internal host to forward it to")
                    BodyText("  • Usually requires a firewall rule or ALG (Application Layer Gateway) in the NAT router")
                    BodyText("Passive mode:")
                    BodyText("  • Client firewall needs only outbound rules — same as HTTP")
                    BodyText("  • Server firewall must allow inbound connections on its passive port range (e.g. 50000-51000)")
                    BodyText("  • Easier to configure on the server side (one port range) than on thousands of client firewalls")
                    BodyText("Passive mode is simpler and far more common. If you run an FTP server, configure a fixed passive port range and open that range in your server's firewall.")
                    CodeBlock(
                        "vsftpd (Linux FTP server) passive range config:\n" +
                        "  pasv_min_port=50000\n" +
                        "  pasv_max_port=50100\n" +
                        "  pasv_address=<server public IP>"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Common Commands ──────────────────────────────────────────
            item {
                SectionCard(title = "Common FTP Commands") {
                    CodeBlock(
                        "USER <name>     Send username\n" +
                        "PASS <password> Send password\n" +
                        "PWD             Print working directory\n" +
                        "CWD <dir>       Change working directory\n" +
                        "LIST            List directory contents (uses data conn)\n" +
                        "RETR <file>     Download a file\n" +
                        "STOR <file>     Upload a file\n" +
                        "DELE <file>     Delete a file\n" +
                        "MKD <dir>       Create directory\n" +
                        "RMD <dir>       Remove directory\n" +
                        "PORT <h,h,h,h,p,p>  Active mode: tell server where to connect\n" +
                        "PASV            Request passive mode\n" +
                        "TYPE I          Binary mode (transfer as-is)\n" +
                        "TYPE A          ASCII mode (line-ending conversion)\n" +
                        "QUIT            End session"
                    )
                    BodyText("Example session snippet:")
                    CodeBlock(
                        "C: USER alice\n" +
                        "S: 331 Password required\n" +
                        "C: PASS secret\n" +
                        "S: 230 Login successful\n" +
                        "C: PASV\n" +
                        "S: 227 Entering Passive Mode (10,0,0,1,195,80)\n" +
                        "C: RETR report.pdf\n" +
                        "S: 150 Opening data connection\n" +
                        "   [file data transferred on port 50000]\n" +
                        "S: 226 Transfer complete"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Security ─────────────────────────────────────────────────
            item {
                SectionCard(title = "Security — FTP is Plaintext") {
                    BodyText("Plain FTP transmits everything — username, password, and file data — as unencrypted text. Anyone on the network can intercept credentials with a packet sniffer. FTP should not be used on untrusted networks.")
                    BodyText("Secure alternatives:")
                    BodyText("FTPS (FTP over SSL/TLS):")
                    BodyText("  • FTP + TLS encryption, same RFC 959 protocol")
                    BodyText("  • Explicit FTPS: starts as plain FTP, client sends AUTH TLS to upgrade")
                    BodyText("  • Implicit FTPS: TLS from the start (port 990)")
                    BodyText("  • Same Active/Passive complexity still applies")
                    BodyText("SFTP (SSH File Transfer Protocol):")
                    BodyText("  • Completely different protocol — not FTP over SSH")
                    BodyText("  • Runs over SSH (port 22), fully encrypted")
                    BodyText("  • Single connection (no separate data channel)")
                    BodyText("  • Firewall-friendly: only port 22 needed")
                    BodyText("  • Strongly preferred over FTP/FTPS for new deployments")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
