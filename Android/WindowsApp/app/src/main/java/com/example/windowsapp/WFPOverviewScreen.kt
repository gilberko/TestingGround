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
fun WFPOverviewScreen(navController: NavController) {
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
            text = "WFP OVERVIEW",
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

        SectionHeader("HISTORY")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Before Vista, drivers intercepted network traffic by hooking TDI (Transport Driver Interface) or NDIS. These hooks were fragile, undocumented, and caused frequent incompatibility between security products.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Windows Filtering Platform (WFP) was introduced in Vista as a stable, documented replacement. It provides a kernel API (fwpkclnt.sys) and a user-mode management API (fwpuclnt.dll) for building firewall and inspection drivers without hooking.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("LAYERS AND SHIMS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A shim is a WFP component that sits at a specific point in the network stack and exposes data to callout drivers. Each shim corresponds to one or more layers.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Each layer is identified by a GUID and represents a distinct interception point. Examples:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "FWPM_LAYER_INBOUND_IPPACKET_V4   // raw inbound IPv4\n" +
            "FWPM_LAYER_OUTBOUND_IPPACKET_V4  // raw outbound IPv4\n" +
            "FWPM_LAYER_INBOUND_TRANSPORT_V4  // inbound TCP/UDP header\n" +
            "FWPM_LAYER_OUTBOUND_TRANSPORT_V4 // outbound TCP/UDP header\n" +
            "FWPM_LAYER_STREAM_V4             // TCP stream data\n" +
            "FWPM_LAYER_ALE_AUTH_CONNECT_V4   // outbound connect()\n" +
            "FWPM_LAYER_ALE_FLOW_ESTABLISHED_V4 // connection established"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("At each layer, specific fields are exposed (IP addresses, ports, protocol, process ID at ALE layers, etc.). The available fields differ between layers.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("FILTERS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A filter is a rule added to a layer via FwpmFilterAdd. It has match conditions (e.g., remote port == 443) and an action. If conditions match, the action is taken.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Filter actions:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "FWP_ACTION_PERMIT                // allow the packet\n" +
            "FWP_ACTION_BLOCK                 // drop the packet\n" +
            "FWP_ACTION_CALLOUT_TERMINATING   // defer to callout (final)\n" +
            "FWP_ACTION_CALLOUT_INSPECTION    // defer to callout (non-final)\n" +
            "FWP_ACTION_CALLOUT_UNKNOWN       // callout decides type"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Filters with no callout reference act as simple rules. Filters referencing a callout GUID invoke the callout's classifyFn for the final decision.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("CALLOUTS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A callout is a kernel driver that registers callback functions at a layer. Registration is a two-step process:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Step 1: register callbacks with the kernel\n" +
            "FwpsCalloutRegister(deviceObject, &callout, &calloutId);\n\n" +
            "// Step 2: add callout to the WFP policy store\n" +
            "FwpmCalloutAdd(engineHandle, &callout0, NULL, NULL);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Each callout registers three callback functions:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "classifyFn        // called to make a filtering decision\n" +
            "notifyFn          // called when a referencing filter is added/deleted\n" +
            "flowDeleteNotifyFn // called when a tracked flow is torn down"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("TERMINATING VS NON-TERMINATING")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A terminating callout can set the final verdict for a packet. Its classifyFn sets actionType to FWP_ACTION_PERMIT or FWP_ACTION_BLOCK in the FWPS_CLASSIFY_OUT struct.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A non-terminating (inspection) callout can only observe the packet. It returns FWP_ACTION_CONTINUE, meaning 'I have no opinion — let other filters decide.' It cannot block or permit on its own.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The distinction matters for sublayer arbitration: a terminating BLOCK from one sublayer can be overridden only by a higher-weight sublayer's PERMIT.")

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
