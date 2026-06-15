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
fun LsassScreen(navController: NavController) {
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
            text = "LSASS",
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

        SectionHeader("WHAT IS LSASS?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "LSASS — Local Security Authority Subsystem Service — is one of the most critical processes in Windows. It runs as lsass.exe under the SYSTEM account and starts very early in the boot process (before user login).\n\n" +
            "LSASS hosts the Local Security Authority (LSA) server and multiple authentication packages. It is responsible for:\n\n" +
            "  - User logon and logoff: validates credentials when a user logs in\n" +
            "  - Authentication protocols: implements Kerberos, NTLM, and other auth packages as in-process DLLs\n" +
            "  - Security policy: enforces local security policy (password requirements, account lockout, audit settings)\n" +
            "  - Audit log: writes security audit events to the Security event log\n" +
            "  - Credential caching: holds credentials in memory so logged-in users do not need to re-authenticate for every network resource access\n" +
            "  - Token creation: creates access tokens for logged-in users that are used for all subsequent access checks\n\n" +
            "If LSASS crashes, Windows immediately BSODs (Stop: 0xC000021A — Fatal System Error) because authentication and security are non-negotiable services."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHAT DOES LSASS STORE?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "LSASS keeps a large amount of sensitive credential material in its process memory:\n\n" +
            "NTLM hashes:\n" +
            "  For each interactively logged-in user, LSASS holds the NTLM hash (MD4 of the Unicode password). Used for NTLM challenge-response authentication to network resources.\n\n" +
            "Kerberos tickets and session keys:\n" +
            "  The Ticket-Granting Ticket (TGT) and service tickets for each logged-in user. Session keys used to encrypt/decrypt Kerberos traffic.\n\n" +
            "WDigest plaintext credentials:\n" +
            "  Historically, WDigest authentication required plaintext passwords in memory. Windows 8.1+ disables this by default (UseLogonCredential = 0 in the registry). Attackers have been known to re-enable it remotely and then wait for a user to re-login.\n\n" +
            "LSA secrets:\n" +
            "  Encrypted secrets stored in the registry (HKLM\\SECURITY\\Policy\\Secrets) and decrypted into LSASS memory. Contains: service account passwords (for services running as domain accounts), cached domain credentials for offline logon.\n\n" +
            "DPAPI master keys:\n" +
            "  The Data Protection API (DPAPI) protects user data (browser passwords, WiFi keys, certificates). LSASS holds DPAPI master keys in memory for decryption.\n\n" +
            "SAM database copy:\n" +
            "  LSASS has access to local account hashes from the SAM database for authenticating local users."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("IS LSASS A PROTECTED PROCESS?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Since Windows 8.1, LSASS can run as a Protected Process Light (PPL). This is not enabled by default on all editions — it requires explicit configuration or Secure Boot + Credential Guard.\n\n" +
            "To check or enable PPL for LSASS:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Registry path:\n" +
            "HKLM\\SYSTEM\\CurrentControlSet\\Control\\Lsa\n" +
            "\n" +
            "Value: RunAsPPL (DWORD)\n" +
            "  0 = not protected (default on many systems)\n" +
            "  1 = run as Protected Process Light\n" +
            "  2 = run as Protected Process (full, not just Light)\n" +
            "\n" +
            "Enable via PowerShell (requires admin + reboot):\n" +
            "Set-ItemProperty -Path 'HKLM:\\SYSTEM\\CurrentControlSet\\Control\\Lsa' `\n" +
            "    -Name RunAsPPL -Value 1\n" +
            "\n" +
            "Verify lsass protection level (task manager or:\n" +
            "Get-Process lsass | Select-Object -Property *"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "When RunAsPPL = 1, lsass runs with protection level PsProtectedTypeProtectedLight and signer PsProtectedSignerLsa (signer level 6 on modern Windows).\n\n" +
            "A process can only open another process with sensitive access rights (PROCESS_VM_READ, PROCESS_VM_WRITE, PROCESS_ALL_ACCESS) if the opener's protection level is greater than or equal to the target's. An unprotected admin process has protection level 0 — it cannot open a PPL process for memory reading.\n\n" +
            "Even a local administrator account is blocked. This was a deliberate design choice — being an admin no longer automatically grants the ability to dump LSASS memory."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("PROTECTED PROCESS LIGHT (PPL) IN DETAIL")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "PPL is a Windows kernel mechanism that constrains what other processes can do to a protected process.\n\n" +
            "The protection is stored in the EPROCESS kernel structure. When OpenProcess is called, the kernel checks the caller's protection level against the target's. Specifically:\n\n" +
            "  if (callerProtectionLevel < targetProtectionLevel)\n" +
            "      return STATUS_ACCESS_DENIED\n\n" +
            "Protected Process hierarchy (from lowest to highest):\n" +
            "  Unprotected (0)\n" +
            "  PPL - various signers (e.g., PsProtectedSignerAntimalware)\n" +
            "  PP  - Protected Process (full, e.g., PsProtectedSignerWinSystem)\n\n" +
            "LSASS as PPL-Lsa:\n" +
            "  An antimalware PPL process can typically be opened by other antimalware PPL processes but NOT by an unprotected process (even if running as SYSTEM).\n\n" +
            "Kernel-mode bypass:\n" +
            "  A kernel-mode driver running at ring 0 can read any process memory regardless of PPL — it can directly manipulate EPROCESS structures. This is why HVCI (Hypervisor Protected Code Integrity) + Secure Boot matters: it prevents unsigned kernel drivers from loading, removing the kernel-mode bypass option for attackers.\n\n" +
            "Without HVCI, a driver that is not WHQL-signed but loaded via a vulnerability can simply read LSASS memory directly from kernel mode, bypassing PPL entirely."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("CREDENTIAL GUARD")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Credential Guard is a Virtualization-Based Security (VBS) feature that takes LSASS protection to the next level.\n\n" +
            "How it works:\n" +
            "  Windows runs two virtual trust levels (VTLs) using the hypervisor:\n" +
            "  - VTL0: the normal OS (kernel, drivers, user-mode processes)\n" +
            "  - VTL1: the Secure World (isolated by the hypervisor)\n\n" +
            "  A companion process, lsaiso.exe (LSA Isolated), runs in VTL1. The sensitive credential material (NTLM hashes, Kerberos keys) is stored in VTL1 memory.\n\n" +
            "  When a VTL0 component needs a credential (e.g., to authenticate to a server), it requests it from lsaiso.exe via a hypervisor call. lsaiso.exe performs the cryptographic operation in VTL1 and returns only the result — the actual hash or key never crosses to VTL0.\n\n" +
            "What this means for attackers:\n" +
            "  Even a fully compromised VTL0 kernel — a rootkit running at ring 0 — cannot read the credential material. VTL0 has no access to VTL1 memory; the hypervisor enforces this at the hardware level (SLAT — Second Level Address Translation).\n\n" +
            "Requirements:\n" +
            "  UEFI Secure Boot, VT-x/AMD-V virtualization, IOMMU, Windows 10/11 Enterprise or Education. Enabled by default on Secured-Core PCs."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("CAN OTHER PROCESSES READ LSASS MEMORY?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "It depends on which protections are active:\n\n" +
            "Scenario 1 — No PPL, no Credential Guard:\n" +
            "  Any process running as admin (or SYSTEM) can call:\n" +
            "  OpenProcess(PROCESS_VM_READ | PROCESS_QUERY_INFORMATION, FALSE, lsassPid)\n" +
            "  → succeeds → ReadProcessMemory → dumps all credentials\n" +
            "  This is exactly how mimikatz sekurlsa::logonpasswords works.\n\n" +
            "Scenario 2 — PPL enabled, no Credential Guard:\n" +
            "  OpenProcess with PROCESS_VM_READ against lsass returns ACCESS_DENIED for unprotected callers.\n" +
            "  However, a kernel-mode driver can still read LSASS memory directly from ring 0.\n" +
            "  Attackers use vulnerable signed drivers (BYOVD — Bring Your Own Vulnerable Driver) to get kernel execution and read LSASS memory despite PPL.\n\n" +
            "Scenario 3 — PPL + Credential Guard:\n" +
            "  PPL blocks user-mode attacks. Credential Guard means the valuable credential material (hashes, Kerberos keys) is in VTL1 and is not present in LSASS's VTL0 memory at all.\n" +
            "  Even a kernel-mode attacker in VTL0 cannot retrieve the credentials — reading LSASS memory gives you only the ciphertext or stubs, not the actual hashes.\n" +
            "  This is the gold standard protection for credential security."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("IS IT SAFE? SECURITY IMPLICATIONS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Dumping LSASS memory is the single most common post-exploitation technique in Windows environments. Once an attacker has admin on one machine, dumping LSASS often yields credentials to pivot laterally across the entire network.\n\n" +
            "Why it matters:\n" +
            "  NTLM hashes can be used directly for Pass the Hash — authenticating to network resources without knowing the plaintext password.\n" +
            "  Kerberos TGTs can be used for Pass the Ticket — forging service ticket requests using a stolen TGT.\n" +
            "  If WDigest is enabled, plaintext passwords can be extracted directly.\n\n" +
            "Famous tools/techniques:\n" +
            "  mimikatz (sekurlsa::logonpasswords) — the classic credential dumper\n" +
            "  comsvcs.dll MiniDump LOLBAS — dumps lsass without third-party tools\n" +
            "  ProcDump -ma lsass.exe — legitimate tool abused to create a dump file\n" +
            "  Task Manager → right-click lsass → Create dump file (blocked by PPL)\n\n" +
            "EDR response:\n" +
            "  Endpoint Detection and Response tools hook OpenProcess and ReadProcessMemory in user mode (via DLL injection or kernel callbacks like ObRegisterCallbacks). Any process that opens lsass with PROCESS_VM_READ generates an alert. This is one of the most-monitored events in enterprise security."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DEFENSES SUMMARY")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Defense                          | What it stops\n" +
            "─────────────────────────────────────────────────────────────────\n" +
            "RunAsPPL = 1                     | Blocks user-mode OpenProcess\n" +
            "                                 | (requires Secure Boot to fully\n" +
            "                                 |  prevent PPL bit manipulation)\n" +
            "─────────────────────────────────────────────────────────────────\n" +
            "Credential Guard (VBS/VTL1)      | Blocks VTL0 kernel-mode reads;\n" +
            "                                 | credentials never in VTL0 memory\n" +
            "─────────────────────────────────────────────────────────────────\n" +
            "HVCI                             | Blocks unsigned/vulnerable drivers\n" +
            "                                 | (removes BYOVD kernel bypass)\n" +
            "─────────────────────────────────────────────────────────────────\n" +
            "ASR Rule: Block credential       | Blocks known credential-dumping\n" +
            "stealing from LSASS              | patterns at the OS level\n" +
            "─────────────────────────────────────────────────────────────────\n" +
            "ETW-TI / ObRegisterCallbacks     | EDR detection: alert on any\n" +
            "                                 | PROCESS_VM_READ targeting lsass\n" +
            "─────────────────────────────────────────────────────────────────\n" +
            "Protected Users security group   | No NTLM authentication;\n" +
            "                                 | no credential delegation;\n" +
            "                                 | no unconstrained Kerberos\n" +
            "─────────────────────────────────────────────────────────────────\n" +
            "WDigest UseLogonCredential = 0   | Prevents plaintext passwords\n" +
            "                                 | from being cached in LSASS\n" +
            "─────────────────────────────────────────────────────────────────\n" +
            "Security audit events 4625/4776  | Detect brute-force + NTLM auth\n" +
            "                                 | anomalies after credential theft"
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
