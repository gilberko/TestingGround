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
fun FSMFRawDiskScreen(navController: NavController) {
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
            text = "WHAT IS THE RAW DISK?",
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

        SectionHeader("WHAT IS RAW DISK ACCESS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("\"Raw\" disk access means opening the disk or volume device directly — bypassing the file system layer entirely. There is no NTFS, no FAT; you read and write raw sectors.\n\nUser mode examples:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Raw physical disk (all partitions)\n" +
            "HANDLE h = CreateFile(\n" +
            "    L\"\\\\\\\\.\\\\PhysicalDrive0\",\n" +
            "    GENERIC_READ,\n" +
            "    FILE_SHARE_READ | FILE_SHARE_WRITE,\n" +
            "    NULL, OPEN_EXISTING, 0, NULL);\n\n" +
            "// Raw volume (C: partition, no NTFS)\n" +
            "HANDLE h2 = CreateFile(\n" +
            "    L\"\\\\\\\\.\\\\C:\",\n" +
            "    GENERIC_READ,\n" +
            "    FILE_SHARE_READ | FILE_SHARE_WRITE,\n" +
            "    NULL, OPEN_EXISTING, 0, NULL);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("THE DEVICE STACK")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Normal file access goes through the file system device. Raw disk access bypasses it:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Normal file I/O:\n" +
            "  App → I/O Manager\n" +
            "    → fltmgr.sys (minifilter callbacks)\n" +
            "    → NTFS device\n" +
            "    → Volume device\n" +
            "    → disk.sys\n\n" +
            "Raw disk I/O:\n" +
            "  App → I/O Manager\n" +
            "    → Volume device  ← skips NTFS!\n" +
            "    → disk.sys\n" +
            "    → Storage controller"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The file system (NTFS) is a separate device attached to the volume. Raw disk I/O skips the FS device entirely — the IRP goes directly to the volume device object.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DOES A MINIFILTER SEE RAW DISK I/O?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("No — for the common case.\n\nThe Filter Manager attaches minifilter instances to the file system's device objects. Raw disk I/O that bypasses the FS device does not go through the FS device → the minifilter is not called.\n\n\\\\.\\PhysicalDrive0: travels through the storage stack only — minifilters never see it.\n\n\\\\.\\C: (raw volume): also does not go through the NTFS device, so minifilters do not see it.\n\nException: on some Windows versions, the volume device may have fltmgr attached at a lower level, giving limited visibility — but this behavior is not documented or reliable.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("PRACTICAL IMPLICATIONS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Raw disk I/O is intentionally used to bypass minifilters:\n\n• Disk forensics tools read raw sectors to recover deleted files\n• Partition managers and disk editors use raw access\n• Some malware writes to raw disk to hide data from AV minifilters\n\nTo monitor raw disk access you need a different driver type:\n\n• Storage filter driver (class filter) — stacks above disk.sys in the storage stack\n• Volume filter driver — stacks above the volume device\n\nMinifilters are the right tool only for file-system-level I/O. If you need to catch all low-level disk activity, combine a minifilter (for FS-level I/O) with a storage filter (for raw disk I/O).")

        Spacer(modifier = Modifier.height(32.dp))
        HackerButton("BACK") { navController.popBackStack() }
    }
}
