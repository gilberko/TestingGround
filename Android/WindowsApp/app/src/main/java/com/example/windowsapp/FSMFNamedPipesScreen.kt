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
fun FSMFNamedPipesScreen(navController: NavController) {
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
            text = "NAMED PIPES",
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

        SectionHeader("NAMED PIPES AS A FILE SYSTEM")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Named pipes are implemented by npfs.sys — the Named Pipe File System driver.\n\nnpfs.sys registers itself as a file system with the I/O Manager, just like NTFS or FAT. It has its own device object (\\ Device\\NamedPipe) and responds to the same set of IRP_MJ_* major function codes.\n\nThe Filter Manager also attaches to npfs.sys volumes when it initializes, so minifilters have the opportunity to intercept named pipe operations.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DO MINIFILTERS SEE NAMED PIPE OPERATIONS?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Yes — by default.\n\nWhen the Filter Manager starts up and detects the NPFS volume, it calls the InstanceSetupCallback of every registered minifilter and offers attachment.\n\nThe callback receives VolumeFilesystemType == FLT_FSTYPE_NPFS.\n\nIf the minifilter returns STATUS_SUCCESS, it will intercept all named pipe operations:\n• IRP_MJ_CREATE — pipe open / connect\n• IRP_MJ_READ — read from pipe\n• IRP_MJ_WRITE — write to pipe\n• IRP_MJ_QUERY_INFORMATION\n• IRP_MJ_CLEANUP, IRP_MJ_CLOSE")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SELECTIVE ATTACHMENT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Most minifilters need to decide whether to attach to NPFS. Attach to monitor IPC; skip to reduce overhead.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "NTSTATUS InstanceSetupCallback(\n" +
            "    PCFLT_RELATED_OBJECTS FltObjects,\n" +
            "    FLT_INSTANCE_SETUP_FLAGS Flags,\n" +
            "    DEVICE_TYPE VolumeDeviceType,\n" +
            "    FLT_FILESYSTEM_TYPE VolumeFilesystemType)\n" +
            "{\n" +
            "    // Opt in to named pipe monitoring:\n" +
            "    if (VolumeFilesystemType == FLT_FSTYPE_NPFS)\n" +
            "        return STATUS_SUCCESS;\n\n" +
            "    // Opt out (ignore named pipes):\n" +
            "    // return STATUS_FLT_DO_NOT_ATTACH;\n\n" +
            "    return STATUS_SUCCESS; // attach to rest\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("PRACTICAL IMPLICATIONS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Security products that monitor IPC (e.g. detecting C2 communication over named pipes) must explicitly handle FLT_FSTYPE_NPFS and opt in.\n\nMost file-activity audit tools filter out NPFS because:\n• Named pipe reads/writes are not disk I/O — logging them inflates audit volume\n• Recursion risk: the filter itself may use a named pipe for user-kernel communication, which would re-trigger its own callbacks\n\nAlso applies to mailslots:\n• Implemented by msfs.sys (Mailslot File System)\n• FLT_FSTYPE_MSFS in InstanceSetupCallback\n• Same pattern: opt in or out explicitly\n\nOther non-disk file systems the Filter Manager attaches to:\n• FLT_FSTYPE_NTFS — standard NTFS\n• FLT_FSTYPE_FAT — FAT / exFAT\n• FLT_FSTYPE_CDFS — CD-ROM\n• FLT_FSTYPE_UDF — DVD\n• FLT_FSTYPE_LANMAN — SMB redirector")

        Spacer(modifier = Modifier.height(32.dp))
        HackerButton("< BACK") { navController.popBackStack() }
    }
}
