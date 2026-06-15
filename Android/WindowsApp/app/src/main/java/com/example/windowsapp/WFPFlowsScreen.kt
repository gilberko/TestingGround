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
fun WFPFlowsScreen(navController: NavController) {
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
            text = "WFP FLOWS",
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

        SectionHeader("WHAT IS A FLOW")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A flow is a WFP abstraction for a network connection or data stream. WFP tracks flows at FLOW_ESTABLISHED layers — these are the layers that fire once when a connection is accepted:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "FWPM_LAYER_ALE_FLOW_ESTABLISHED_V4\n" +
            "FWPM_LAYER_ALE_FLOW_ESTABLISHED_V6"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Each flow is identified by a unique 64-bit flowId, available in classifyFn via inMetaValues->flowHandle. Flows exist at the transport layer and above (TCP connections, UDP pseudo-flows). At the IP packet layer, there are no WFP flows.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ATTACHING DATA TO A FLOW")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("You can attach a per-flow context (a UINT64 — typically a pointer to a driver-allocated structure) to any flow your callout sees. This lets you carry state across multiple classifyFn calls for the same connection.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// In classifyFn at FLOW_ESTABLISHED layer:\n" +
            "UINT64 flowId = inMetaValues->flowHandle;\n\n" +
            "MY_FLOW_CONTEXT* ctx = ExAllocatePool2(\n" +
            "    POOL_FLAG_NON_PAGED, sizeof(*ctx), 'wfpF');\n" +
            "// ... populate ctx ...\n\n" +
            "FwpsFlowAssociateContext(\n" +
            "    flowId,\n" +
            "    FWPS_LAYER_ALE_FLOW_ESTABLISHED_V4,\n" +
            "    gCalloutId,\n" +
            "    (UINT64)ctx);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The context is then available as the flowContext parameter in subsequent classifyFn calls at stream or datagram layers for the same connection, and in flowDeleteNotifyFn when the flow ends.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("FLOW LIFECYCLE AND FREEING DATA")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("When a flow is torn down (connection closed, timed out, or aborted), WFP calls your flowDeleteNotifyFn:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "void NTAPI MyFlowDeleteFn(\n" +
            "  UINT16 layerId,\n" +
            "  UINT32 calloutId,\n" +
            "  UINT64 flowContext\n" +
            ") {\n" +
            "    MY_FLOW_CONTEXT* ctx = (MY_FLOW_CONTEXT*)flowContext;\n" +
            "    // free resources\n" +
            "    ExFreePoolWithTag(ctx, 'wfpF');\n" +
            "}"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("You must free all per-flow allocations in flowDeleteNotifyFn. Do not access the flow or its context after this callback returns — the flow object is destroyed.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("FORCING FLOW RELEASE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("To force-terminate an active connection (e.g., to block a flow that was already established), call FwpsFlowAbort. This causes the TCP stack to send a RST and tears down the connection:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "FwpsFlowAbort(flowId);\n" +
            "// triggers RST, then flowDeleteNotifyFn fires"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DRIVER UNLOAD")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("FwpsCalloutUnregisterById returns STATUS_DEVICE_BUSY if any flows still have contexts attached to your callout. You must clean up all flow contexts before unregistering.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Recommended pattern:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// 1. Stop accepting new flows (remove your filters first)\n" +
            "FwpmFilterDeleteById(engineHandle, filterId);\n\n" +
            "// 2. Remove context from all active flows\n" +
            "//    (iterate your tracked flow list)\n" +
            "LIST_FOR_EACH(flow) {\n" +
            "    FwpsFlowRemoveContext(\n" +
            "        flow->flowId,\n" +
            "        FWPS_LAYER_ALE_FLOW_ESTABLISHED_V4,\n" +
            "        gCalloutId);\n" +
            "}\n\n" +
            "// 3. Wait for flowDeleteNotifyFn to fire for each\n" +
            "//    (use a counter + KeWaitForSingleObject)\n\n" +
            "// 4. Now safe to unregister\n" +
            "FwpsCalloutUnregisterById(gCalloutId);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Calling FwpsFlowRemoveContext detaches the context and triggers flowDeleteNotifyFn synchronously or asynchronously depending on the layer. Using an atomic counter and a KEVENT is the standard way to wait until all flow cleanup callbacks have completed.")

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
