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
fun FSMFMupScreen(navController: NavController) {
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
            text = "MUP FS",
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

        SectionHeader("WHAT IS MUP")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("MUP = Multiple UNC Provider.\n\nMUP (mup.sys) is a kernel driver that handles UNC paths — paths of the form \\\\server\\share\\file.txt.\n\nWhen an application opens a UNC path, the I/O Manager does not know which network provider should handle it. It routes the IRP to MUP, which acts as a dispatcher.\n\nMUP has its own device object and registers as a file system with the I/O Manager. The Filter Manager attaches minifilter instances to the MUP volume just like any other file system.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("HOW MUP WORKS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("1. App opens \\\\server\\share\\file.txt\n2. I/O Manager routes the IRP_MJ_CREATE to the MUP device\n3. MUP queries each registered UNC provider in priority order:\n   • LanmanRedirector (SMB) — handles \\\\server\\share\n   • WebDAVRedirector — handles \\\\webdav-server\n   • DfsClient (Distributed File System)\n4. The provider that claims the path wins\n5. MUP creates a new IRP and re-issues it to the claiming redirector's file system device\n6. The redirector handles the I/O and sends it over the network")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("THE REDIRECTOR AS A FILE SYSTEM")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The network redirector (e.g. rdbss.sys + mrxsmb.sys for SMB) registers as a file system with the I/O Manager and has its own volume device.\n\nThe Filter Manager attaches minifilter instances to redirector volumes, just like it does to local NTFS volumes.\n\nSo a UNC file open traverses two separate filter stack instances:\n• The MUP instance (first)\n• The redirector instance (second, after MUP re-issues the IRP)")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("MINIFILTER SEES I/O TWICE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("When an app opens \\\\server\\share\\file.txt the filter sees it on two different instances:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "App opens \\\\server\\share\\file.txt\n" +
            "  ↓\n" +
            "MUP device (fltmgr attached)\n" +
            "  → [1] Minifilter pre-op (MUP instance)\n" +
            "      ↓\n" +
            "    MUP routes to SMB redirector\n" +
            "    MUP creates a new IRP →\n" +
            "      ↓\n" +
            "    SMB redirector device (fltmgr attached)\n" +
            "      → [2] Minifilter pre-op (SMB instance)\n" +
            "          ↓  (network I/O)\n" +
            "      → [3] Minifilter post-op (SMB instance)\n" +
            "      ↓\n" +
            "  → [4] Minifilter post-op (MUP instance)\n" +
            "  ↓\n" +
            "App receives result"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("IDENTIFYING THE INSTANCE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Use FltObjects->Volume or FltObjects->Instance to distinguish which instance fired the callback.\n\nTag each instance in InstanceSetupCallback using an instance context. Store the file system type in the context so your pre/post ops know which volume they are running on:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "typedef struct _INSTANCE_CONTEXT {\n" +
            "    FLT_FILESYSTEM_TYPE FsType;\n" +
            "} INSTANCE_CONTEXT, *PINSTANCE_CONTEXT;\n\n" +
            "// In InstanceSetupCallback:\n" +
            "PINSTANCE_CONTEXT ctx;\n" +
            "FltAllocateContext(gFilter,\n" +
            "    FLT_INSTANCE_CONTEXT,\n" +
            "    sizeof(INSTANCE_CONTEXT),\n" +
            "    PagedPool, &ctx);\n" +
            "ctx->FsType = VolumeFilesystemType;\n" +
            "FltSetInstanceContext(FltObjects->Instance,\n" +
            "    FLT_SET_CONTEXT_KEEP_IF_EXISTS,\n" +
            "    ctx, NULL);\n" +
            "FltReleaseContext(ctx);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("PRACTICAL IMPLICATIONS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A minifilter that logs file opens will log every UNC open twice — once on the MUP instance, once on the redirector instance.\n\nDeduplication strategies:\n• Skip MUP-instance callbacks: detect FLT_FSTYPE_MUP in InstanceSetupCallback and return STATUS_FLT_DO_NOT_ATTACH — see only the redirector instance\n• Skip redirector callbacks: attach only to MUP — see the path before routing but not the actual data\n• Most security products skip MUP and attach only to the redirector (FLT_FSTYPE_LANMAN etc.) where actual file content flows\n\nNote: the redirector instance is where file data and metadata are accessible. The MUP instance only sees the initial path routing request.")

        Spacer(modifier = Modifier.height(32.dp))
        HackerButton("< BACK") { navController.popBackStack() }
    }
}
