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
fun FSMFHistoryScreen(navController: NavController) {
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
            text = "HISTORY AND OVERVIEW",
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

        SectionHeader("LEGACY FILE SYSTEM FILTER DRIVERS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Before minifilters, developers wrote legacy FS filter drivers. They attached to the file system's device stack using IoAttachDeviceToDeviceStack, then forwarded IRPs manually.\n\nProblems with this approach:\n• Ordering — first driver to attach became the outermost; no standard mechanism to control position\n• No safe unload — unloading while operations were in-flight could crash\n• Complex IRP handling — every IRP type required boilerplate forwarding code\n• Re-entrant I/O deadlocks — issuing I/O from within the filter could re-enter your own code\n• Interaction bugs — two legacy filters from different vendors could interfere unpredictably")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("THE FILTER MANAGER (fltmgr.sys)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Introduced in Windows XP SP2 / Windows Server 2003.\n\nfltmgr.sys is itself a legacy FS filter driver — it attaches to the file system device stack at a well-defined position. All minifilters register with and run under the Filter Manager; they never interact with the device stack directly.\n\nThe Filter Manager:\n• Converts raw IRPs into FLT_CALLBACK_DATA structs before calling minifilters\n• Dispatches pre-op callbacks to minifilters in altitude order (highest first)\n• Dispatches post-op callbacks in reverse altitude order (lowest first)\n• Handles safe unload by draining in-flight operations\n• Provides a rich context and communication API")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHY MINIFILTERS ARE BETTER")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Ordering via altitude: Microsoft assigns altitude ranges per product category. No guesswork, no attachment-order hacks.\n\nSafe unload: FltUnregisterFilter drains all in-flight callbacks before returning — no crash-on-unload.\n\nPass-through is trivial: return FLT_PREOP_SUCCESS_NO_CALLBACK. No IRP forwarding boilerplate.\n\nRich context API: allocate and attach typed context structures per volume, per instance, per file object, per stream, per transaction.\n\nFlt... APIs for internal I/O: use FltCreateFile instead of ZwCreateFile — it re-enters the stack below your altitude, preventing recursion.\n\nCommunication ports: FltCreateCommunicationPort / FltConnectCommunicationPort for user-kernel channel without IOCTL setup.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("INSTANCES")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A minifilter instance is one attachment of the minifilter to one volume.\n\nWhen a volume mounts, the Filter Manager calls the InstanceSetupCallback of every registered minifilter and offers it an instance on that volume. The minifilter can accept (return STATUS_SUCCESS) or decline (return STATUS_FLT_DO_NOT_ATTACH).\n\nOne minifilter binary → multiple simultaneous instances (one per mounted volume).\n\nInstance context (FLT_INSTANCE_CONTEXT): per-volume state that the minifilter allocates and owns. Retrieved from any callback via FltGetInstanceContext.\n\nExample decision in InstanceSetupCallback:\n• Attach only to NTFS volumes\n• Skip network redirector volumes\n• Skip named pipe / mailslot volumes")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "NTSTATUS InstanceSetupCallback(\n" +
            "    PCFLT_RELATED_OBJECTS FltObjects,\n" +
            "    FLT_INSTANCE_SETUP_FLAGS Flags,\n" +
            "    DEVICE_TYPE VolumeDeviceType,\n" +
            "    FLT_FILESYSTEM_TYPE VolumeFilesystemType)\n" +
            "{\n" +
            "    if (VolumeFilesystemType != FLT_FSTYPE_NTFS)\n" +
            "        return STATUS_FLT_DO_NOT_ATTACH;\n" +
            "    return STATUS_SUCCESS;\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(32.dp))
        HackerButton("< BACK") { navController.popBackStack() }
    }
}
