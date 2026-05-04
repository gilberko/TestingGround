package com.example.windowsapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.windowsapp.ui.theme.HackerGreen

@Composable
fun WFPRawSocketsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "WFP AND RAW SOCKETS",
            color = HackerGreen,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "─".repeat(28),
            color = HackerGreen,
            fontFamily = FontFamily.Monospace,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(24.dp))

        SectionHeader("OVERVIEW")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A raw socket (SOCK_RAW) bypasses the TCP and UDP protocol shims in the Windows network stack — it operates directly at the IP or transport layer without a TCP/UDP header being added by the OS.\n\nThis affects which WFP layers fire, which fields are populated, and which are absent. WFP does see raw socket traffic — but not at every layer, and not with all the context it has for normal TCP/UDP flows.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ALE LAYERS AND RAW SOCKETS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("ALE_AUTH_CONNECT_V4:\nFIRES — but ONLY if the application explicitly calls connect() on the raw socket to set a default destination. Many raw socket uses (sendto() with no prior connect()) skip this layer entirely.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("ALE_AUTH_LISTEN_V4:\nNEVER fires. listen() is a TCP-only call. Raw sockets never call listen(), so this layer sees nothing.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("ALE_AUTH_RECV_ACCEPT_V4:\nFIRES for the first inbound packet on a bound raw socket (when the socket was bound with bind()). Does NOT fire for promiscuous-mode raw sockets using SIO_RCVALL without a proper bind.\n\nIn both ALE_AUTH_CONNECT and ALE_AUTH_RECV_ACCEPT, the FWP_CONDITION_FLAG_IS_RAW_ENDPOINT flag IS set in the FLAGS condition, which is how you know it is a raw socket.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("THE SHIM CONCERN — VERIFIED")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Your concern is correct in part. Here is the exact picture:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Layer                       Raw socket?\n" +
            "────────────────────────────────────────\n" +
            "ALE_AUTH_CONNECT            only if connect() called\n" +
            "ALE_AUTH_LISTEN             NEVER\n" +
            "ALE_AUTH_RECV_ACCEPT        only if socket is bound\n" +
            "ALE_FLOW_ESTABLISHED        NEVER (no flow for raw)\n" +
            "STREAM_V4                   NEVER (TCP shim only)\n" +
            "DATAGRAM_DATA_V4            FIRES (raw is datagram)\n" +
            "OUTBOUND_TRANSPORT_V4       FIRES\n" +
            "INBOUND_TRANSPORT_V4        FIRES\n" +
            "OUTBOUND_NETWORK_V4         FIRES\n" +
            "INBOUND_NETWORK_V4          FIRES\n" +
            "ALE_ENDPOINT_CLOSURE_V4     FIRES on socket close"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The STREAM layer is TCP-only by design — it sits inside the TCP shim and operates on reassembled TCP streams. Raw sockets never reach it.\n\nThe ALE_FLOW_ESTABLISHED layer is also unreachable: flow establishment requires a TCP handshake or a UDP first-datagram event; raw sockets have neither.\n\nHowever, OUTBOUND_TRANSPORT, INBOUND_TRANSPORT, OUTBOUND_NETWORK, INBOUND_NETWORK, and DATAGRAM_DATA all DO fire for raw socket traffic. So WFP is not blind — it just doesn't have the higher-level TCP/UDP context.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("MISSING FIELDS IN THE DATA")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Port numbers (IP_LOCAL_PORT, IP_REMOTE_PORT):\nFor raw sockets using protocols that have no port concept (ICMP, custom protocols), these fields are either 0 or repurposed.\n\nFor ICMP specifically, WFP maps the ICMP type and code into the port fields at ALE and transport layers:\n• IP_LOCAL_PORT = ICMP type (e.g. 8 = echo request)\n• IP_REMOTE_PORT = ICMP code (e.g. 0)\n\nFor IPPROTO_IP (raw IP with IP_HDRINCL), the port fields are 0 and meaningless — the protocol in the IP header is whatever the application wrote.\n\nProcess ID and application ID:\nThese ARE available at ALE layers even for raw sockets — you can still identify which process sent the packet.\n\nFlow handle:\nNot available — no flow is created for raw sockets.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("FWP_CONDITION_FLAG_IS_RAW_ENDPOINT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("This is the key flag. It is set in the FLAGS condition whenever the traffic originates from or is received by a raw socket endpoint.\n\nValue: 0x00000400\n\nAvailable at these layers:\n• ALE_AUTH_CONNECT_V4/V6\n• ALE_AUTH_RECV_ACCEPT_V4/V6\n• ALE_FLOW_ESTABLISHED_V4/V6\n• OUTBOUND_TRANSPORT_V4/V6\n• INBOUND_TRANSPORT_V4/V6\n• DATAGRAM_DATA_V4/V6\n• ALE_ENDPOINT_CLOSURE_V4/V6\n\nThe flag is NOT available at the network layers (OUTBOUND/INBOUND_NETWORK_V4/V6) because by that point in the stack the socket context has been detached from the packet.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DETECTING A RAW SOCKET IN CLASSIFYFN")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Check the FLAGS condition in your classifyFn. The field index depends on the layer — each layer has its own FWPS_FIELD_*_FLAGS enum value. Example for ALE_AUTH_CONNECT:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// In classifyFn at ALE_AUTH_CONNECT_V4:\n" +
            "UINT32 flags = inFixedValues->incomingValue[\n" +
            "    FWPS_FIELD_ALE_AUTH_CONNECT_V4_FLAGS\n" +
            "].value.uint32;\n\n" +
            "if (flags & FWP_CONDITION_FLAG_IS_RAW_ENDPOINT) {\n" +
            "    // This connect is from a raw socket\n" +
            "    UINT32 protocol = inFixedValues->incomingValue[\n" +
            "        FWPS_FIELD_ALE_AUTH_CONNECT_V4_IP_PROTOCOL\n" +
            "    ].value.uint8;\n" +
            "    // protocol: 1=ICMP, 6=TCP(raw), 17=UDP(raw)\n" +
            "    // 255=IPPROTO_RAW (raw IP with IP_HDRINCL)\n" +
            "}"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("At OUTBOUND_TRANSPORT, use FWPS_FIELD_OUTBOUND_TRANSPORT_V4_FLAGS. At DATAGRAM_DATA, use FWPS_FIELD_DATAGRAM_DATA_V4_FLAGS. The pattern is identical — only the field index enum changes.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// At OUTBOUND_TRANSPORT_V4:\n" +
            "UINT32 flags = inFixedValues->incomingValue[\n" +
            "    FWPS_FIELD_OUTBOUND_TRANSPORT_V4_FLAGS\n" +
            "].value.uint32;\n\n" +
            "UINT8 proto = inFixedValues->incomingValue[\n" +
            "    FWPS_FIELD_OUTBOUND_TRANSPORT_V4_IP_PROTOCOL\n" +
            "].value.uint8;\n\n" +
            "UINT16 localPort = inFixedValues->incomingValue[\n" +
            "    FWPS_FIELD_OUTBOUND_TRANSPORT_V4_IP_LOCAL_PORT\n" +
            "].value.uint16;\n" +
            "// For ICMP raw sockets: localPort = ICMP type\n" +
            "// For raw IP (IPPROTO_IP): localPort = 0"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("PRACTICAL IMPLICATIONS FOR CALLOUTS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("If your callout blocks traffic based on port numbers, raw sockets can evade those filters — they have no ports. Your port-based rules at OUTBOUND_TRANSPORT simply never match a raw ICMP packet because the port fields are 0 or ICMP type/code.\n\nIf your callout only registers at ALE_AUTH_CONNECT, it misses raw sockets that use sendto() without calling connect() — these never trigger ALE_AUTH_CONNECT.\n\nRecommended strategy for comprehensive raw socket monitoring:\n\n1. Register at OUTBOUND_TRANSPORT and INBOUND_TRANSPORT (these always fire)\n2. Check FWP_CONDITION_FLAG_IS_RAW_ENDPOINT in the FLAGS field\n3. Use the IP_PROTOCOL field to determine what kind of raw socket it is\n4. Do not rely on port fields for raw sockets — use IP_LOCAL_ADDRESS and IP_REMOTE_ADDRESS instead\n5. For raw ICMP: interpret IP_LOCAL_PORT as ICMP type and IP_REMOTE_PORT as ICMP code\n6. Register at ALE_AUTH_CONNECT as well — it fires when connect() is called, giving you process identity and destination address before any packet is sent")

        Spacer(modifier = Modifier.height(32.dp))
        HackerButton("< BACK") { navController.popBackStack() }
    }
}
