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
fun FSMFAltitudeScreen(navController: NavController) {
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
            text = "ALTITUDE",
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

        SectionHeader("WHAT IS ALTITUDE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Altitude is a numeric string stored in the registry that determines a minifilter's position in the filter stack.\n\nHigher altitude = closer to the I/O Manager:\n• Pre-op callbacks: called first (highest altitude sees I/O earliest)\n• Post-op callbacks: called last (highest altitude processes results last, on the way back up)\n\nThe altitude is stored as a REG_SZ under:\nHKLM\\SYSTEM\\CurrentControlSet\\Services\\\n  <DriverName>\\Instances\\<InstanceName>\n    Altitude = \"328000\"")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ALTITUDE RANGES (MICROSOFT-DEFINED)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Microsoft defines ranges by product category. A product must stay within its assigned range:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "420000-429999  FSFilter Top\n" +
            "400000-409999  FSFilter Activity Monitor\n" +
            "360000-389999  FSFilter Undelete\n" +
            "340000-349999  FSFilter Anti-Virus\n" +
            "320000-329999  FSFilter Replication\n" +
            "300000-309999  FSFilter Continuous Backup\n" +
            "280000-289999  FSFilter Content Screener\n" +
            "260000-269999  FSFilter Quota Management\n" +
            "240000-249999  FSFilter System Recovery\n" +
            "220000-229999  FSFilter Cluster File System\n" +
            "200000-209999  FSFilter HSM\n" +
            "180000-189999  FSFilter Imaging\n" +
            "170000-174999  FSFilter Compression\n" +
            "140000-149999  FSFilter Encryption\n" +
            "130000-139999  FSFilter Virtualization\n" +
            "120000-129999  FSFilter Physical Quota Mgmt\n" +
            "100000-109999  FSFilter Open File\n" +
            " 80000- 89999  FSFilter Security Enhancer\n" +
            " 60000- 69999  FSFilter Copy Protection\n" +
            " 40000- 49999  FSFilter Bottom\n" +
            " 20000- 29999  FSFilter System"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("REGISTERING WITH MICROSOFT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Companies must submit a request to Microsoft (via the Windows Hardware Dev Center) to receive an assigned altitude within the appropriate range for their product type.\n\nThis prevents altitude collisions between products from different vendors.\n\nDuring development: use any value within the appropriate range as a test altitude (e.g. 325000 for an anti-virus prototype). Do not use test altitudes in shipping products.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("USING ALTITUDE IN FLT... CALLS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("FltCreateFile is the recommended API for issuing I/O from within a minifilter. It takes the calling minifilter's PFLT_INSTANCE and re-enters the filter stack below that instance's altitude.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "FltCreateFile(\n" +
            "    gFilterHandle,\n" +
            "    Instance,       // caller's instance\n" +
            "    &hFile,\n" +
            "    GENERIC_READ,\n" +
            "    &objAttr,\n" +
            "    &ioStatus,\n" +
            "    NULL, FILE_ATTRIBUTE_NORMAL,\n" +
            "    FILE_SHARE_READ,\n" +
            "    FILE_OPEN,\n" +
            "    FILE_SYNCHRONOUS_IO_NONALERT,\n" +
            "    NULL, 0,\n" +
            "    IO_IGNORE_SHARE_ACCESS_CHECK,\n" +
            "    NULL);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Result: filters at or above your altitude are NOT called — no recursion into your own pre-op.\n\nFltCreateFileEx2 offers even more control: target a specific instance, attach Extra Create Parameters (ECPs) to mark the I/O as self-initiated.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ISSUING I/O THROUGH ALL LAYERS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Can you issue I/O that goes through every filter, including those above you?\n\nYes — use ZwCreateFile (or IoCreateFile). It goes to the top of the filter stack, so every minifilter including yours will see it.\n\nRisk: your own pre-op fires again → potential infinite recursion.\n\nGuard against recursion by:\n• Setting a thread-local flag (e.g. KeGetCurrentThread() → TLS slot) and checking it in pre-op\n• Attaching an ECP to the IRP and checking for it in pre-op\n\nRecommendation: always prefer FltCreateFile unless you explicitly need upper-filter processing. ZwCreateFile from a minifilter is a footgun — use it only with a recursion guard in place.")

        Spacer(modifier = Modifier.height(32.dp))
        HackerButton("< BACK") { navController.popBackStack() }
    }
}
