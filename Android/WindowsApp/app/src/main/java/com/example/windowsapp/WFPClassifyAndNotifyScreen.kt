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
fun WFPClassifyAndNotifyScreen(navController: NavController) {
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
            text = "CLASSIFY AND NOTIFY",
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

        SectionHeader("CLASSIFY FUNCTION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("classifyFn is called by WFP whenever a packet or socket operation matches the conditions of a filter that references your callout. It is responsible for setting the filtering decision.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "void NTAPI MyClassifyFn(\n" +
            "  const FWPS_INCOMING_VALUES0*          inFixedValues,\n" +
            "  const FWPS_INCOMING_METADATA_VALUES0* inMetaValues,\n" +
            "  void*                                 layerData,\n" +
            "  const void*                           classifyContext,\n" +
            "  const FWPS_FILTER2*                   filter,\n" +
            "  UINT64                                flowContext,\n" +
            "  FWPS_CLASSIFY_OUT0*                   classifyOut\n" +
            ");"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Key parameters:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "inFixedValues  // layer fields: IP addresses, ports, protocol\n" +
            "inMetaValues   // metadata: process ID, image path (at ALE)\n" +
            "layerData      // stream data at STREAM layers; NULL elsewhere\n" +
            "flowContext     // UINT64 you attached via FwpsFlowAssociateContext\n" +
            "filter->context // UINT64 you set when adding the filter"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Before setting a verdict, always check that you have the right to write the action:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "if (classifyOut->rights & FWPS_RIGHT_ACTION_WRITE) {\n" +
            "    classifyOut->actionType = FWP_ACTION_PERMIT;\n" +
            "    // or FWP_ACTION_BLOCK, FWP_ACTION_CONTINUE\n" +
            "}"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("FWP_ACTION_CONTINUE means 'I pass' — the callout is non-terminating for this packet. Other filters in the same sublayer continue to be evaluated.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("NOTIFY FUNCTION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("notifyFn is called when a filter that references your callout is added or deleted from the policy store. It is not called per-packet; it is called per-filter lifecycle event.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "NTSTATUS NTAPI MyNotifyFn(\n" +
            "  FWPS_CALLOUT_NOTIFY_TYPE notifyType,\n" +
            "  const GUID*              filterKey,\n" +
            "  FWPS_FILTER2*            filter\n" +
            ");\n\n" +
            "// notifyType values:\n" +
            "// FWPS_CALLOUT_NOTIFY_ADD_FILTER    -- filter added\n" +
            "// FWPS_CALLOUT_NOTIFY_DELETE_FILTER -- filter being deleted"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Use notifyFn to cache per-filter context (filter->context is available here too), or to update internal state when filters change. Return STATUS_SUCCESS in most cases. Returning a failure code on ADD_FILTER will cause the FwpmFilterAdd call to fail, effectively rejecting the filter.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("RECLASSIFICATION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Reclassification (re-authorization) occurs at ALE AUTH layers when WFP needs to re-evaluate a previously classified connection. This happens when WFP policy changes — for example, when a filter is added or deleted that could affect an existing connection.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("At ALE AUTH layers, you can detect reclassification by checking the flags field in the layer's fixed values:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// At FWPM_LAYER_ALE_AUTH_CONNECT_V4:\n" +
            "UINT32 flags = inFixedValues->incomingValues[\n" +
            "    FWPS_FIELD_ALE_AUTH_CONNECT_V4_FLAGS].value.uint32;\n\n" +
            "if (flags & FWP_CONDITION_FLAG_IS_REAUTHORIZE) {\n" +
            "    // this is a reclassification, not a new connection\n" +
            "}"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("What should your callout do during reclassification?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Best practice: recalculate from scratch. Do not assume the previous decision is still valid — the policy change that triggered reclassification may have altered the conditions that led to the original decision.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("If you need to defer the decision asynchronously (e.g., to avoid holding a lock at classify time), use FwpsPendOperation to pend the classify and later call FwpsCompleteOperation to resume it with your verdict.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Async pattern:\n" +
            "FwpsPendOperation(inMetaValues->completionHandle,\n" +
            "                  &myCompletionContext);\n" +
            "classifyOut->actionType = FWP_ACTION_BLOCK; // temporary hold\n\n" +
            "// Later, from a work item or thread:\n" +
            "FwpsCompleteOperation(myCompletionContext, NULL);"
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
