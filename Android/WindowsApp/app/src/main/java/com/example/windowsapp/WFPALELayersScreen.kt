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
fun WFPALELayersScreen(navController: NavController) {
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
            text = "WFP ALE LAYERS",
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

        SectionHeader("ALE OVERVIEW")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("ALE stands for Application Layer Enforcement. ALE layers sit above the transport layer and operate at socket-operation granularity, not per-packet granularity.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Key properties of ALE layers:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "- One classify call per socket operation (connect/listen/accept)\n" +
            "  not per packet — very efficient for policy decisions\n" +
            "- Process identity is available:\n" +
            "    FWPS_FIELD_*_ALE_APP_ID   // device path of the process image\n" +
            "    FWPS_FIELD_*_ALE_USER_ID  // user SID of the calling process\n" +
            "    inMetaValues->processId   // numeric PID\n" +
            "- Local and remote IP/port are available\n" +
            "- Protocol (TCP/UDP/other) is available"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("ALE layers are the right place to write a host-based firewall: you can allow or block connections per-process, per-user, per-IP, or any combination.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("CONNECT LAYERS (OUTBOUND)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The connect layers fire when an application calls connect() to initiate an outbound connection:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "FWPM_LAYER_ALE_AUTH_CONNECT_V4   // outbound IPv4\n" +
            "FWPM_LAYER_ALE_AUTH_CONNECT_V6   // outbound IPv6"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Fields available at this layer:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "FWPS_FIELD_ALE_AUTH_CONNECT_V4_IP_LOCAL_ADDRESS\n" +
            "FWPS_FIELD_ALE_AUTH_CONNECT_V4_IP_REMOTE_ADDRESS\n" +
            "FWPS_FIELD_ALE_AUTH_CONNECT_V4_IP_LOCAL_PORT\n" +
            "FWPS_FIELD_ALE_AUTH_CONNECT_V4_IP_REMOTE_PORT\n" +
            "FWPS_FIELD_ALE_AUTH_CONNECT_V4_IP_PROTOCOL\n" +
            "FWPS_FIELD_ALE_AUTH_CONNECT_V4_ALE_APP_ID\n" +
            "FWPS_FIELD_ALE_AUTH_CONNECT_V4_ALE_USER_ID"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Block here to prevent a process from making outbound connections. This is effective for application-level egress filtering.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("LISTEN LAYERS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The listen layers fire when an application calls listen() to open a TCP listening port:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "FWPM_LAYER_ALE_AUTH_LISTEN_V4    // TCP listen IPv4\n" +
            "FWPM_LAYER_ALE_AUTH_LISTEN_V6    // TCP listen IPv6"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Block here to prevent applications from opening listening sockets on specific ports or entirely. Useful for preventing unexpected servers from starting. Note: UDP has no listen() call, so UDP binds are covered by RECV_ACCEPT layers.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ACCEPT LAYERS (INBOUND)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The accept layers fire when an inbound connection has completed the TCP handshake and is being handed to the application via accept():")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "FWPM_LAYER_ALE_AUTH_RECV_ACCEPT_V4  // inbound IPv4\n" +
            "FWPM_LAYER_ALE_AUTH_RECV_ACCEPT_V6  // inbound IPv6"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("This fires after the TCP handshake completes but before accept() returns to the application. Blocking here sends a RST and discards the connection without the application ever seeing it.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Also handles inbound UDP datagrams (since UDP has no handshake, each new remote address/port pair triggers a classify at this layer). Useful for inbound IP filtering.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("FIREWALL + DPI PATTERN")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Combining ALE and stream layers enables a full firewall with deep packet inspection (DPI):")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Step 1: ALE_AUTH_CONNECT / ALE_AUTH_RECV_ACCEPT\n" +
            "  -> inspect process identity + IP/port\n" +
            "  -> permit/block at connection time\n\n" +
            "Step 2: ALE_FLOW_ESTABLISHED\n" +
            "  -> attach per-flow context with process info\n" +
            "  -> FwpsFlowAssociateContext(flowId, ...)\n\n" +
            "Step 3: FWPM_LAYER_STREAM_V4 / STREAM_V6\n" +
            "  -> called for each TCP data chunk\n" +
            "  -> flowContext has process info from step 2\n" +
            "  -> inspect payload (FWPS_STREAM_DATA)\n" +
            "  -> FwpsStreamInjectAsync to modify, or block\n\n" +
            "Step 4: FWPM_LAYER_DATAGRAM_DATA_V4\n" +
            "  -> UDP equivalent of stream layer"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The flow context bridges the application identity (learned at ALE) with the payload inspection (done at stream layer). Without the flow context, the stream layer does not know which process owns the data.")

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
