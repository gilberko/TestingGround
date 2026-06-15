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
fun NtZwScreen(navController: NavController) {
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
            text = "NT... VS ZW...",
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

        SectionHeader("OVERVIEW")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Both prefixes refer to native API functions. For every syscall there is an " +
            "NtXxx function (the actual implementation) and a ZwXxx function (a kernel-mode " +
            "wrapper). Both are exported from ntoskrnl.exe."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("IN USER MODE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "In ntdll.dll both NtXxx and ZwXxx are identical stubs that execute the syscall " +
            "instruction. There is no difference between them from user mode. The kernel sets " +
            "PreviousMode to UserMode on syscall entry regardless of which name was used."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("IN KERNEL MODE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "This is the key difference. ZwXxx is a thin wrapper that:\n" +
            "  1. Sets the calling thread's PreviousMode field to KernelMode\n" +
            "  2. Dispatches through the SSDT\n" +
            "  3. Restores PreviousMode on return\n\n" +
            "NtXxx is the actual implementation. Calling it directly from kernel mode " +
            "preserves whatever PreviousMode already is — which is UserMode for threads " +
            "that originated from user-mode requests."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("PREVIOUSMODE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "PreviousMode lives in the KTHREAD structure. It has two values:\n" +
            "  UserMode   (0) — the call originated from user mode\n" +
            "  KernelMode (1) — the call originated from kernel mode\n\n" +
            "NtXxx reads PreviousMode to decide whether to validate input buffers via " +
            "ProbeForRead and ProbeForWrite."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("BUFFER PROBING")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "UserMode: the kernel calls ProbeForRead/ProbeForWrite on every buffer, catching " +
            "invalid user addresses before they cause a fault.\n\n" +
            "KernelMode: probing is skipped — kernel addresses are trusted.\n\n" +
            "The danger: if a driver calls NtXxx directly, PreviousMode is still UserMode, so " +
            "the kernel probes the driver's kernel-mode buffer pointers as if they were user " +
            "addresses. This causes STATUS_ACCESS_VIOLATION or a BSOD because kernel " +
            "addresses fall outside the user-mode address range that the probe checks."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("EXAMPLE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Correct vs dangerous usage from a kernel-mode driver:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Correct: ZwReadFile sets PreviousMode = KernelMode\n" +
            "// Probing is skipped; kernel buffer pointers are accepted\n" +
            "status = ZwReadFile(hFile, NULL, NULL, NULL,\n" +
            "                    &ioStatus, kernelBuffer,\n" +
            "                    bufferSize, &byteOffset, NULL);\n\n" +
            "// Dangerous: NtReadFile does NOT change PreviousMode\n" +
            "// PreviousMode is still UserMode from the originating request\n" +
            "// ProbeForWrite(kernelBuffer) will fail -> crash\n" +
            "status = NtReadFile(hFile, NULL, NULL, NULL,\n" +
            "                    &ioStatus, kernelBuffer,\n" +
            "                    bufferSize, &byteOffset, NULL);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("HANDLE SEMANTICS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Handles created via the Zw path carry KernelMode access. They bypass some " +
            "security checks — for example, ZwOpenFile called from kernel mode can open " +
            "files ignoring certain ACL checks that NtOpenFile from user mode would enforce. " +
            "The kernel trusts that a kernel-mode caller has already performed appropriate " +
            "authorization decisions."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("THE RULE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Always use ZwXxx from kernel-mode driver code. NtXxx is the implementation, " +
            "not the intended kernel-mode interface. ZwXxx exists precisely to give drivers " +
            "a safe, PreviousMode-correct path through the system call layer."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
