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
fun FSMFReparsePointScreen(navController: NavController) {
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
            text = "REPARSE POINT",
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

        SectionHeader("WHAT IS A REPARSE POINT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A reparse point is a special NTFS attribute that can be attached to any file or directory. It consists of:\n• A reparse tag — a DWORD that identifies the owner and type of the reparse point\n• A reparse data buffer — opaque bytes whose meaning is defined by the owner\n\nWhen the I/O Manager encounters a file or directory with a reparse point during path traversal (IRP_MJ_CREATE), NTFS reads the attribute and returns STATUS_REPARSE instead of opening the object normally.\n\nThe I/O Manager inspects the reparse tag and data, then decides how to respond — usually by re-issuing the create with a new path.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("REPARSE TAG CATEGORIES")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Microsoft-defined tags (bit 31 set): handled by the OS itself, no driver needed.\nThird-party tags: a driver must be present to handle them; registered with Microsoft to prevent collisions.\n\nCommon tags:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "IO_REPARSE_TAG_SYMLINK\n" +
            "  Symbolic link → redirects to substitute path\n\n" +
            "IO_REPARSE_TAG_MOUNT_POINT\n" +
            "  Junction / volume mount point\n\n" +
            "IO_REPARSE_TAG_CLOUD / _CLOUD_1.._9\n" +
            "  OneDrive, Dropbox placeholder files\n\n" +
            "IO_REPARSE_TAG_DEDUP\n" +
            "  Windows Data Deduplication\n\n" +
            "IO_REPARSE_TAG_DFS\n" +
            "  Distributed File System referral"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("HOW SYMBOLIC LINKS WORK")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Example: C:\\link is a symlink pointing to D:\\target\\file.txt")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "1. App opens C:\\link\n" +
            "2. IRP_MJ_CREATE → NTFS\n" +
            "3. NTFS finds reparse point attribute\n" +
            "   → returns STATUS_REPARSE\n" +
            "4. I/O Manager sees STATUS_REPARSE\n" +
            "   + IO_REPARSE_TAG_SYMLINK\n" +
            "5. Extracts SubstituteName from\n" +
            "   SYMBOLIC_LINK_REPARSE_DATA_BUFFER\n" +
            "   → \"D:\\target\\file.txt\"\n" +
            "6. Re-issues IRP_MJ_CREATE from scratch\n" +
            "   for D:\\target\\file.txt\n" +
            "7. NTFS opens the real file\n" +
            "   → STATUS_SUCCESS"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A minifilter sees the CREATE twice:\n• First: C:\\link — post-op IoStatus.Status == STATUS_REPARSE\n• Second: D:\\target\\file.txt — the actual open")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("REPARSE DATA BUFFER STRUCTURE")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "typedef struct _REPARSE_DATA_BUFFER {\n" +
            "    ULONG  ReparseTag;\n" +
            "    USHORT ReparseDataLength;\n" +
            "    USHORT Reserved;\n" +
            "    union {\n" +
            "        struct { // symlink\n" +
            "            USHORT SubstituteNameOffset;\n" +
            "            USHORT SubstituteNameLength;\n" +
            "            USHORT PrintNameOffset;\n" +
            "            USHORT PrintNameLength;\n" +
            "            ULONG  Flags; // 0=abs, 1=rel\n" +
            "            WCHAR  PathBuffer[1];\n" +
            "        } SymbolicLinkReparseBuffer;\n" +
            "        struct { // junction/mount point\n" +
            "            USHORT SubstituteNameOffset;\n" +
            "            USHORT SubstituteNameLength;\n" +
            "            USHORT PrintNameOffset;\n" +
            "            USHORT PrintNameLength;\n" +
            "            WCHAR  PathBuffer[1];\n" +
            "        } MountPointReparseBuffer;\n" +
            "        struct { // third-party\n" +
            "            UCHAR DataBuffer[1];\n" +
            "        } GenericReparseBuffer;\n" +
            "    };\n" +
            "} REPARSE_DATA_BUFFER;"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("CONTROL CODES")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Reparse points are managed via FSCTL (file system control) codes:\n\n• FSCTL_SET_REPARSE_POINT — attach a reparse point (requires FILE_SPECIAL_ACCESS or admin)\n• FSCTL_GET_REPARSE_POINT — read the reparse data buffer\n• FSCTL_DELETE_REPARSE_POINT — remove the reparse point attribute\n\nUser mode: DeviceIoControl with the FSCTL code\nKernel mode (minifilter): FltFsControlFile or ZwFsControlFile")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("HOW MINIFILTERS INTERACT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Detect a reparse in post-op of IRP_MJ_CREATE:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "FLT_POSTOP_CALLBACK_STATUS PostCreate(\n" +
            "    PFLT_CALLBACK_DATA Data, ...)\n" +
            "{\n" +
            "    if (Data->IoStatus.Status ==\n" +
            "            STATUS_REPARSE) {\n" +
            "        // Data->TagData contains the\n" +
            "        // reparse tag and buffer\n" +
            "        ULONG tag =\n" +
            "            Data->TagData->FileTag;\n" +
            "        // log or act on the reparse\n" +
            "    }\n" +
            "    return FLT_POSTOP_FINISHED_PROCESSING;\n" +
            "}"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Prevent reparse: in pre-op, complete with STATUS_ACCESS_DENIED before NTFS processes the reparse attribute — the re-parse never happens.\n\nCreate a custom reparse point from kernel mode:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "REPARSE_DATA_BUFFER rdb = {0};\n" +
            "rdb.ReparseTag = MY_TAG;\n" +
            "rdb.ReparseDataLength = sizeof(MyData);\n" +
            "// fill GenericReparseBuffer...\n\n" +
            "FltFsControlFile(\n" +
            "    Instance, FileObject,\n" +
            "    FSCTL_SET_REPARSE_POINT,\n" +
            "    &rdb,\n" +
            "    REPARSE_DATA_BUFFER_HEADER_SIZE\n" +
            "        + rdb.ReparseDataLength,\n" +
            "    NULL, 0, NULL);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("USE CASES IN PRACTICE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Cloud storage (OneDrive, Dropbox): files appear present as placeholders tagged with IO_REPARSE_TAG_CLOUD. When opened, the provider's filter driver intercepts the STATUS_REPARSE in post-op and downloads the data before completing the create.\n\nSymbolic links: entirely OS-handled via IO_REPARSE_TAG_SYMLINK — no driver needed.\n\nVolume mount points: mount D:\\ inside C:\\Mounted\\ — NTFS uses a mount-point reparse tag on the directory. The I/O Manager redirects the path to the mounted volume.\n\nDFS (Distributed File System): the DFS client driver handles IO_REPARSE_TAG_DFS, redirecting the client to the correct server transparently.\n\nHSM (Hierarchical Storage Management): files migrated to tape are replaced with reparse-point stubs. A filter driver recalls the data on demand when the stub is opened.")

        Spacer(modifier = Modifier.height(32.dp))
        HackerButton("BACK") { navController.popBackStack() }
    }
}
