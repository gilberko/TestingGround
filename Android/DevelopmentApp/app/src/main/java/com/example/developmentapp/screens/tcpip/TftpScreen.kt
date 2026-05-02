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
fun TftpScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "TFTP",
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

            // ── What is TFTP ─────────────────────────────────────────────
            item {
                SectionCard(title = "What is TFTP?") {
                    BodyText("TFTP — Trivial File Transfer Protocol — is an extremely simple file transfer protocol defined in RFC 1350 (1992). As its name implies, it is trivial: it provides only the ability to read or write a file. Nothing else.")
                    BodyText("TFTP has no authentication, no directory listing, no user login, and no encryption. Its simplicity makes it easy to implement in very small environments — bootloaders, ROMs, and embedded firmware.")
                    BodyText("TFTP runs over UDP (not TCP) on port 69.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── UDP and Port 69 ──────────────────────────────────────────
            item {
                SectionCard(title = "UDP and Port 69") {
                    BodyText("TFTP uses UDP for two reasons:")
                    BodyText("  1. Simplicity: UDP requires no connection setup, no state machine. A minimal TFTP implementation fits in a few hundred lines of C — or in a bootloader ROM.")
                    BodyText("  2. Pre-boot environments: PXE booting happens before an OS is loaded. The network stack available is minimal; UDP is far easier to implement than TCP.")
                    BodyText("Since UDP provides no reliability, TFTP implements its own simple reliability: a lockstep (stop-and-wait) acknowledgment protocol. The server listens on UDP port 69 for initial requests. After the first packet, both sides switch to a random ephemeral port for the transfer.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Lockstep Protocol ────────────────────────────────────────
            item {
                SectionCard(title = "Lockstep (Stop-and-Wait) Protocol") {
                    BodyText("TFTP sends one block at a time and waits for an acknowledgment before sending the next. This is called a lockstep or stop-and-wait protocol.")
                    CodeBlock(
                        "Client                      Server\n" +
                        "  |                            |\n" +
                        "  |--- RRQ 'file.bin' -------->|  Read request\n" +
                        "  |<-- DATA block #1 (512 B) --|  Server sends first block\n" +
                        "  |--- ACK #1 ---------------->|  Client acknowledges\n" +
                        "  |<-- DATA block #2 (512 B) --|  Server sends next block\n" +
                        "  |--- ACK #2 ---------------->|\n" +
                        "  |<-- DATA block #3 (< 512 B)-|  Last block (< 512 bytes)\n" +
                        "  |--- ACK #3 ---------------->|  Transfer complete"
                    )
                    BodyText("The default block size is 512 bytes (RFC 1350). A block smaller than 512 bytes signals the end of the file. RFC 2347 introduced option negotiation allowing larger block sizes (e.g. 1468 bytes for Ethernet, avoiding fragmentation) for better performance.")
                    BodyText("If a packet is lost, the sender retransmits after a timeout. There is no sliding window — only one unacknowledged block at a time — making TFTP slow on high-latency links.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Packet Types ─────────────────────────────────────────────
            item {
                SectionCard(title = "Packet Types") {
                    CodeBlock(
                        "Opcode  Type    Description\n" +
                        "  1     RRQ     Read Request  — client wants to download a file\n" +
                        "  2     WRQ     Write Request — client wants to upload a file\n" +
                        "  3     DATA    Data block    — 2-byte block number + up to 512 bytes\n" +
                        "  4     ACK     Acknowledge   — 2-byte block number\n" +
                        "  5     ERROR   Error         — error code + message string"
                    )
                    BodyText("RRQ and WRQ packets contain the filename and transfer mode (netascii or octet). Octet (binary) mode is standard for modern use.")
                    CodeBlock(
                        "RRQ packet:\n" +
                        "  [0x00 0x01] [filename\\0] [mode\\0]\n" +
                        "  e.g.: 00 01 'pxelinux.0' 00 'octet' 00\n" +
                        "\n" +
                        "DATA packet:\n" +
                        "  [0x00 0x03] [block# high] [block# low] [data bytes...]"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Use Cases ────────────────────────────────────────────────
            item {
                SectionCard(title = "Use Cases") {
                    BodyText("Despite its limitations, TFTP is widely used in specific scenarios where simplicity matters more than features:")
                    BodyText("PXE booting:")
                    BodyText("  • A machine with no OS boots from the network. DHCP tells it the TFTP server address and boot filename. The BIOS/UEFI downloads the bootloader (e.g. pxelinux.0) via TFTP and executes it.")
                    BodyText("Network device configuration:")
                    BodyText("  • Cisco routers and switches use TFTP to save and restore running configurations and IOS images. Example: 'copy running-config tftp'")
                    BodyText("Firmware flashing:")
                    BodyText("  • Embedded devices (routers, IP cameras) often include a minimal TFTP server in their recovery mode for reflashing corrupted firmware.")
                    BodyText("IP phones:")
                    BodyText("  • VoIP phones commonly download their configuration files from a TFTP server at boot time.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── TFTP vs FTP ──────────────────────────────────────────────
            item {
                SectionCard(title = "TFTP vs FTP") {
                    CodeBlock(
                        "Feature          TFTP              FTP\n" +
                        "Transport        UDP               TCP\n" +
                        "Port             69                21 (control), 20/random (data)\n" +
                        "Authentication   None              Username + password\n" +
                        "Directory list   No                Yes (LIST command)\n" +
                        "Operations       Read, Write only  Full: list, delete, mkdir...\n" +
                        "Reliability      Stop-and-wait     TCP (built-in)\n" +
                        "Performance      Slow (lockstep)   Better (TCP windowing)\n" +
                        "Encryption       None              None (FTP); TLS (FTPS/SFTP)\n" +
                        "Implementation   Tiny (~500 LOC)   Complex\n" +
                        "Use case         Boot, embedded    General file transfer"
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
