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
fun WifiScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "WiFi",
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

            // ── What is WiFi ─────────────────────────────────────────────
            item {
                SectionCard(title = "What is WiFi?") {
                    BodyText("WiFi is a family of wireless networking protocols based on the IEEE 802.11 standards. It defines how devices communicate over radio frequencies (typically 2.4 GHz and 5 GHz; WiFi 6E adds 6 GHz).")
                    BodyText("WiFi operates at Layer 1 (physical — radio) and Layer 2 (data link — MAC frames) of the OSI model. From Layer 3 upward it behaves like Ethernet: it delivers IP packets between devices on the same network.")
                    CodeBlock(
                        "Standard   Year  Max speed    Frequency\n" +
                        "802.11b    1999   11 Mbps     2.4 GHz\n" +
                        "802.11g    2003   54 Mbps     2.4 GHz\n" +
                        "802.11n    2009  600 Mbps     2.4/5 GHz  (WiFi 4)\n" +
                        "802.11ac   2013  3.5 Gbps     5 GHz      (WiFi 5)\n" +
                        "802.11ax   2021  9.6 Gbps     2.4/5/6 GHz (WiFi 6/6E)\n" +
                        "802.11be   2024  46 Gbps      2.4/5/6 GHz (WiFi 7)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Beacon Frames — Passive Scanning ────────────────────────
            item {
                SectionCard(title = "Network Discovery — Beacon Frames") {
                    BodyText("Access Points (APs) continuously broadcast Beacon frames to advertise their presence. By default, a Beacon is sent approximately every 100 milliseconds (the Target Beacon Transmission Time, TBTT).")
                    BodyText("A Beacon frame contains:")
                    BodyText("  • SSID — the network name (e.g. 'HomeNetwork')")
                    BodyText("  • BSSID — the AP's MAC address")
                    BodyText("  • Supported data rates")
                    BodyText("  • Channel information")
                    BodyText("  • RSN/WPA information element — security capabilities (encryption type, authentication)")
                    BodyText("  • Timestamp for synchronisation")
                    BodyText("Passive scanning: a device simply listens on each channel for Beacon frames. This is how your phone's WiFi list is populated — no packets are sent until you actually connect.")
                    BodyText("Beacon frames are 802.11 management frames, sent at the lowest supported data rate to ensure all devices can receive them.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Active Scanning ──────────────────────────────────────────
            item {
                SectionCard(title = "Active Scanning — Probe Request / Response") {
                    BodyText("A device can also actively scan by sending Probe Request frames and listening for Probe Response frames from APs.")
                    CodeBlock(
                        "Device                          AP\n" +
                        "  |                              |\n" +
                        "  |--- Probe Request (SSID='') ->|  Wildcard: any AP respond\n" +
                        "  |<-- Probe Response -----------|  AP replies with its info\n" +
                        "  |                              |\n" +
                        "  |--- Probe Request ('MyNet') ->|  Target specific SSID\n" +
                        "  |<-- Probe Response -----------|  Only MyNet APs reply"
                    )
                    BodyText("Active scanning finds networks faster than passive scanning (no need to wait up to 100ms per channel for a Beacon). Devices typically use both: active scan first, then passive for channels that didn't respond.")
                    BodyText("Privacy note: when probing with specific SSIDs (networks you've connected to before), your device reveals the names of those networks to anyone listening nearby. Modern devices use randomised MAC addresses and wildcard probes to mitigate this.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Hidden Networks ──────────────────────────────────────────
            item {
                SectionCard(title = "Hidden Networks") {
                    BodyText("An AP can be configured to suppress its SSID in Beacon frames. The Beacon is still broadcast (the AP does not become invisible), but the SSID field is left empty or zeroed.")
                    BodyText("To connect to a hidden network you must type the SSID manually. Devices that know a hidden SSID send directed Probe Requests with that SSID, and the AP responds.")
                    BodyText("Hidden SSIDs provide very little security: the SSID is visible in plain text in Probe Request and Probe Response frames whenever any client connects. Tools like Wireshark or airodump-ng reveal hidden SSIDs trivially. Hiding a network is security through obscurity, not real security.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Encryption Algorithms ────────────────────────────────────
            item {
                SectionCard(title = "Encryption Algorithms / Protocols") {
                    BodyText("WEP — Wired Equivalent Privacy (1997, broken):")
                    BodyText("  • RC4 stream cipher with a 40-bit or 104-bit key")
                    BodyText("  • Critically flawed: short, reused IVs (24-bit) allow statistical attacks")
                    BodyText("  • Can be cracked in minutes with tools like aircrack-ng. Never use WEP.")
                    BodyText("WPA — Wi-Fi Protected Access (2003, deprecated):")
                    BodyText("  • Introduced TKIP (Temporal Key Integrity Protocol) as a stopgap")
                    BodyText("  • Still uses RC4 but with per-packet key mixing and a sequence counter to prevent IV reuse")
                    BodyText("  • Better than WEP but still vulnerable to several attacks. Deprecated.")
                    BodyText("WPA2 — Wi-Fi Protected Access 2 (2004, current standard):")
                    BodyText("  • Uses CCMP: AES in Counter Mode with CBC-MAC for both encryption and integrity")
                    BodyText("  • AES-128 block cipher — cryptographically strong")
                    BodyText("  • Two modes: Personal (PSK — pre-shared key) and Enterprise (802.1X/RADIUS)")
                    BodyText("  • Vulnerable to offline dictionary attacks against the 4-way handshake if a weak password is used")
                    BodyText("WPA3 (2018, latest standard):")
                    BodyText("  • Replaces PSK 4-way handshake with SAE (Simultaneous Authentication of Equals), also called Dragonfly")
                    BodyText("  • SAE is a password-authenticated key exchange: even if the password is weak, capturing the handshake does not allow offline brute-forcing")
                    BodyText("  • Forward secrecy: each session uses fresh keys; past sessions cannot be decrypted even if the password is later compromised")
                    BodyText("  • WPA3-Enterprise requires 192-bit cryptographic strength (CNSA suite)")
                    CodeBlock(
                        "Protocol  Cipher        Auth handshake  Status\n" +
                        "WEP       RC4 (40/104b)  None (shared)   Broken — avoid\n" +
                        "WPA       RC4 + TKIP     4-way (PSK)     Deprecated\n" +
                        "WPA2      AES-CCMP       4-way (PSK)     Current standard\n" +
                        "WPA3      AES-CCMP/GCMP  SAE (Dragonfly) Latest"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── WPA2 4-Way Handshake ─────────────────────────────────────
            item {
                SectionCard(title = "WPA2 4-Way Handshake") {
                    BodyText("After a client associates with a WPA2 AP, both sides derive a session key through the 4-way handshake. This proves they both know the PSK without transmitting it.")
                    BodyText("Key material:")
                    BodyText("  • PMK (Pairwise Master Key) = PBKDF2(passphrase, SSID, 4096 iterations) — derived independently by both sides from the password")
                    BodyText("  • PTK (Pairwise Transient Key) = KDF(PMK + ANonce + SNonce + MAC addresses) — the actual encryption key for the session")
                    CodeBlock(
                        "Client                          AP\n" +
                        "  |                              |\n" +
                        "  |<-- ANonce (random) ----------|  AP sends its nonce\n" +
                        "  |                              |\n" +
                        "  |  Client derives PTK          |\n" +
                        "  |  from PMK+ANonce+SNonce+MACs |\n" +
                        "  |                              |\n" +
                        "  |--- SNonce + MIC ------------>|  Client sends its nonce\n" +
                        "  |                              |  + Message Integrity Code\n" +
                        "  |  AP derives PTK, verifies MIC|\n" +
                        "  |                              |\n" +
                        "  |<-- GTK (group key) + MIC ----|  AP sends group key\n" +
                        "  |--- ACK ----------------------|  Client confirms\n" +
                        "  |  (encrypted data now begins) |"
                    )
                    BodyText("The 4 messages are captured by an attacker during the handshake. With the handshake captured, a dictionary attack can test passwords offline: try each candidate password, compute the PMK and PTK, and check if the computed MIC matches the captured one.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── WPA Enterprise / 802.1X ──────────────────────────────────
            item {
                SectionCard(title = "WPA-Enterprise / 802.1X / RADIUS") {
                    BodyText("WPA-Personal (PSK) uses the same password for all users. WPA-Enterprise uses 802.1X to authenticate each user individually against a central server.")
                    BodyText("Components:")
                    BodyText("  • Supplicant — the client device (laptop, phone)")
                    BodyText("  • Authenticator — the AP (acts as a relay)")
                    BodyText("  • Authentication Server — typically a RADIUS server (e.g. FreeRADIUS, Microsoft NPS)")
                    BodyText("Flow:")
                    CodeBlock(
                        "Client ←→ AP (EAP over 802.11) ←→ RADIUS server\n" +
                        "\n" +
                        "1. Client associates with AP\n" +
                        "2. AP blocks all traffic except EAP (IEEE 802.1X port blocking)\n" +
                        "3. Client and RADIUS server run an EAP exchange\n" +
                        "   (EAP-TLS: mutual certificate auth\n" +
                        "    EAP-PEAP: server cert + MSCHAPv2 user/pass inside TLS tunnel\n" +
                        "    EAP-TTLS: similar to PEAP)\n" +
                        "4. RADIUS sends Accept/Reject to AP\n" +
                        "5. On Accept, AP derives PMK from the EAP session key\n" +
                        "6. 4-way handshake runs as usual to derive PTK"
                    )
                    BodyText("Benefits of Enterprise mode: each user has individual credentials; compromising one user's password does not affect others; credentials can be revoked centrally; audit trail per user. Standard for corporate and university networks.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Promiscuous Mode ──────────────────────────────────────────
            item {
                SectionCard(title = "Promiscuous Mode") {
                    BodyText("Normal NIC operation: the hardware filter accepts only three kinds of frames:")
                    BodyText("  1. Frames addressed to the NIC's own MAC address")
                    BodyText("  2. Broadcast frames (FF:FF:FF:FF:FF:FF)")
                    BodyText("  3. Multicast frames for groups the NIC has joined")
                    BodyText("All other frames are silently dropped in hardware before the OS ever sees them.")
                    BodyText("Promiscuous mode: the hardware filter is disabled. The NIC passes every frame it physically receives up to the OS, regardless of the destination MAC. Software (Wireshark, tcpdump) can then inspect all traffic.")
                    BodyText("Limitation on switched networks: a switch only sends a frame to the port where the destination MAC lives. In promiscuous mode you still only see your own unicast traffic plus broadcasts and multicasts — not traffic flowing between two other hosts. To capture all switch traffic you need a mirror port (SPAN port) configured on the switch.")
                    BodyText("On WiFi — promiscuous mode vs monitor mode:")
                    BodyText("  • Promiscuous mode on WiFi: receive all 802.11 data frames within your associated BSS (your own network). Useful but limited.")
                    BodyText("  • Monitor mode (RFMON): the card leaves associated mode entirely and becomes a passive radio receiver. It captures all raw 802.11 frames on the current channel — data, management (beacons, probe requests, auth), and control frames — from any SSID on the channel. This is what airodump-ng, Wireshark, and tcpdump use for full wireless packet capture.")
                    BodyText("Do all adapters support it?")
                    BodyText("  • Wired Ethernet: supported by virtually all modern NICs.")
                    BodyText("  • WiFi monitor mode: chipset- and driver-dependent. Widely supported: Atheros, Ralink/MediaTek, many Intel chipsets (iwlwifi). Often not supported or limited: many Broadcom and Realtek consumer adapters. Verify compatibility before buying a WiFi adapter for security research.")
                    CodeBlock(
                        "# Wired promiscuous mode (Linux)\n" +
                        "ip link set eth0 promisc on\n" +
                        "\n" +
                        "# WiFi monitor mode (Linux)\n" +
                        "ip link set wlan0 down\n" +
                        "iw dev wlan0 set type monitor\n" +
                        "ip link set wlan0 up\n" +
                        "\n" +
                        "# Programmatic (Linux): opening AF_PACKET / SOCK_RAW\n" +
                        "# automatically enables promiscuous mode on the interface.\n" +
                        "\n" +
                        "# Windows: WinPcap / Npcap (used by Wireshark) handles it\n" +
                        "# transparently when opening a capture device."
                    )
                    BodyText("Use cases: packet capture and protocol analysis (Wireshark, tcpdump), network troubleshooting, security auditing, intrusion detection systems (IDS/IPS), WiFi auditing tools (airodump-ng, kismet).")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
