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
fun ProtectedPplScreen(navController: NavController) {
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
            text = "PROTECTED AND PPL",
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

        SectionHeader("WHAT ARE PROTECTED PROCESSES?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Protected Processes were introduced in Windows Vista for DRM. Processes like audiodg.exe (audio DRM) and media foundation players needed protection from debuggers and memory scanners — even when run by administrators. The OS was modified so that the kernel refuses sensitive OpenProcess handles to Protected processes regardless of the caller's privilege level.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHAT IS PPL (PROTECTED PROCESS LIGHT)?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("PPL (Protected Process Light) was introduced in Windows 8.1 as a more flexible protection tier. It applies the same access restriction model but with a lighter signing requirement and is used more broadly: lsass.exe (credential storage), MsMpEng.exe (Windows Defender), and other security-critical processes can run as PPL.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("THE PROTECTION STRUCTURE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Each process has a Protection field in its EPROCESS kernel structure. This is a PS_PROTECTION bitfield combining a Type and a Signer level:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "typedef struct _PS_PROTECTION {\n" +
            "    UCHAR Type   : 3;  // PsProtectedType*\n" +
            "    UCHAR Audit  : 1;\n" +
            "    UCHAR Signer : 4;  // PsProtectedSigner*\n" +
            "} PS_PROTECTION;\n\n" +
            "PsProtectedType values:\n" +
            "  PsProtectedTypeNone           = 0  (unprotected)\n" +
            "  PsProtectedTypeProtectedLight = 1  (PPL)\n" +
            "  PsProtectedTypeProtected      = 2  (full Protected)\n\n" +
            "PsProtectedSigner levels (higher = more privileged):\n" +
            "  PsProtectedSignerNone         = 0\n" +
            "  PsProtectedSignerAuthenticode = 1\n" +
            "  PsProtectedSignerCodeGen      = 2\n" +
            "  PsProtectedSignerAntimalware  = 3\n" +
            "  PsProtectedSignerLsa          = 4\n" +
            "  PsProtectedSignerWindows      = 5\n" +
            "  PsProtectedSignerWinTcb       = 6\n" +
            "  PsProtectedSignerWinSystem    = 7"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ACCESS RESTRICTIONS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A process at a lower protection level (or unprotected) cannot perform the following operations on a Protected or PPL process — even when running as SYSTEM or Administrator:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "OpenProcess with sensitive access rights:\n" +
            "  PROCESS_VM_READ, PROCESS_VM_WRITE\n" +
            "  PROCESS_ALL_ACCESS, PROCESS_TERMINATE\n" +
            "  PROCESS_DUP_HANDLE, PROCESS_CREATE_THREAD\n" +
            "  → returns ACCESS_DENIED\n\n" +
            "OpenThread with THREAD_ALL_ACCESS\n" +
            "  → returns ACCESS_DENIED\n\n" +
            "ReadProcessMemory / WriteProcessMemory\n" +
            "  → fails (requires PROCESS_VM_READ/WRITE)\n\n" +
            "CreateRemoteThread\n" +
            "  → ACCESS_DENIED\n\n" +
            "DebugActiveProcess\n" +
            "  → ACCESS_DENIED\n\n" +
            "This check is enforced in the KERNEL on every\n" +
            "object open — not just a user-mode policy."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHAT PROTECTED/PPL PROCESSES CAN DO")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Protection affects what others can do TO the process, not what the process itself can do. Protected and PPL processes use normal Win32/NT APIs without restriction. The protection rules for opening another process are based on the comparison of both processes' protection levels:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Opening rules (caller → target):\n\n" +
            "  Protected (Type=2) can open PPL (Type=1)\n" +
            "    with sensitive access rights\n\n" +
            "  PPL cannot open Protected with sensitive rights\n\n" +
            "  Same Type, higher Signer can open lower Signer\n" +
            "    (e.g. Lsa-PPL can open Antimalware-PPL)\n\n" +
            "  Unprotected process cannot open any PP or PPL\n" +
            "    with sensitive access rights\n\n" +
            "  SYSTEM privilege does NOT bypass these checks"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("MEMORY SHARING")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Processes at equal or higher protection can share memory via named file mappings (CreateFileMapping / MapViewOfFile). A lower-protection process cannot map or read shared memory belonging to a higher-protected process. However, standard IPC mechanisms like named pipes, sockets, and LPC ports are allowed between processes of different protection levels since those go through kernel-managed objects rather than direct memory access.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("PROTECTED vs PPL")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "                  Protected Process   PPL\n" +
            "Type value        2                   1\n" +
            "Introduced        Windows Vista       Windows 8.1\n" +
            "Typical use       DRM, critical OS    lsass, AV, services\n" +
            "Admin bypass      Impossible          Impossible\n" +
            "                  (kernel enforced)   (kernel enforced)\n" +
            "Can open PPL?     Yes                 No\n" +
            "Can open Prot?    Same/higher only    No"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ENABLING PPL FOR LSASS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("lsass.exe stores NTLM hashes, Kerberos tickets, and other credentials in memory. Without PPL, an admin can dump its memory using tools like Mimikatz. Enabling RunAsPPL makes lsass.exe run as PPL-Lsa, blocking direct memory access even from administrators:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Registry:\n" +
            "  HKLM\\SYSTEM\\CurrentControlSet\\Control\\Lsa\n" +
            "    RunAsPPL = DWORD 1\n\n" +
            "Effect:\n" +
            "  lsass.exe runs as PPL (Type=1, Signer=Lsa=4)\n" +
            "  Blocks: Mimikatz sekurlsa::logonpasswords\n" +
            "  Blocks: comsvcs.dll MiniDump LOLBAS technique\n" +
            "  Blocks: any OpenProcess with VM_READ on lsass\n\n" +
            "Note: requires Secure Boot to be secure;\n" +
            "  without it the registry value can be changed\n" +
            "  offline and PPL bypassed."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("HOW ELAM RELATES TO PPL")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("ELAM drivers can grant PPL status to specific services by embedding identity metadata in a resource section named \"MICROSOFTELAM\" in the ELAM driver PE. When SCM starts a service whose binary hash matches an entry in this resource, the kernel automatically applies the specified PPL level. This allows AV vendors to ensure their processes run as PPL without requiring per-machine registry changes.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Chain of trust:\n\n" +
            "  Microsoft issues ELAM signing certificate\n" +
            "  └─ AV vendor signs their ELAM driver\n" +
            "      └─ ELAM driver PE contains\n" +
            "          MICROSOFTELAM resource:\n" +
            "            hash of AV process binary\n" +
            "            + desired Type + Signer level\n" +
            "          └─ At boot, SCM starts the AV process\n" +
            "              kernel matches the hash\n" +
            "              process runs as PPL automatically\n\n" +
            "Example:\n" +
            "  WdBoot.sys (Defender ELAM) certifies\n" +
            "  MsMpEng.exe as PPL-Antimalware\n" +
            "  (Type=1, Signer=3)"
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
