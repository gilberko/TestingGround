package com.example.windowsapp

import androidx.compose.foundation.background
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
fun BewareRegistryScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "BEWARE THE REGISTRY",
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
            "The Windows registry is the central configuration database. It is also one of the most powerful persistence and injection mechanisms on the platform — both for legitimate tools and for malware.\n\n" +
            "Several well-known registry locations allow an attacker (or a developer writing security tools) to: attach debuggers to arbitrary processes, inject DLLs into every process, intercept all network calls, run code at login, or install a kernel driver that starts automatically.\n\n" +
            "Understanding these locations is essential for security engineers, malware analysts, and anyone writing defensive software."
        )
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("A. IMAGE FILE EXECUTION OPTIONS (IFEO)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "When Windows is about to launch an executable, it checks IFEO for that image name. If a Debugger value is present, Windows runs the debugger instead, passing the original executable as an argument.\n\n" +
            "Legitimate use: Windows Error Reporting uses this to attach vsjitdebugger.exe on crash. Kernel engineers use it to attach WinDbg to a process at launch.\n\n" +
            "Abuse: any executable can be silently redirected to a malicious binary, or to a no-op like cmd.exe to prevent it from running (used to disable AV).\n\n" +
            "GlobalFlag value in the same key enables heap debugging aids (page heap, stack traces, etc.) — used by application verifier."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
HKLM\SOFTWARE\Microsoft\Windows NT\CurrentVersion\
  Image File Execution Options\notepad.exe
    Debugger = REG_SZ "C:\Tools\windbg.exe"

// When user launches notepad.exe, Windows runs:
//   windbg.exe notepad.exe
// instead of notepad.exe directly

// To disable an executable (redirect to no-op):
    Debugger = REG_SZ "C:\Windows\System32\cmd.exe /c"
        """.trimIndent())
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("B. APPINIT_DLLS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "When user32.dll initialises in any process (which includes virtually all GUI applications), it reads AppInit_DLLs and calls LoadLibrary on each DLL listed there. This injects the DLL into every process that loads user32.dll.\n\n" +
            "Historically used by accessibility tools, input method editors, and unfortunately malware.\n\n" +
            "Since Windows 8 with Secure Boot enabled: AppInit_DLLs is silently ignored. The RequireSignedAppInit_DLLs value also enforces code signing. On older systems or with Secure Boot disabled, this mechanism is fully active.\n\n" +
            "A broken DLL in AppInit_DLLs can destabilise the entire system since it is loaded into every process."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
HKLM\SOFTWARE\Microsoft\Windows NT\CurrentVersion\Windows
    AppInit_DLLs         = REG_SZ    "C:\inject\hook.dll"
    LoadAppInit_DLLs     = REG_DWORD 1
    RequireSignedAppInit_DLLs = REG_DWORD 0 // bypass sig check

// hook.dll is loaded into every process that loads user32.dll
// This is most GUI processes on the system
        """.trimIndent())
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("C. LAYERED SERVICE PROVIDER (LSP)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "LSP is a real mechanism — part of the Winsock Service Provider Interface (SPI). A DLL registered as an LSP is loaded into every process that calls WSAStartup, inserting itself into the Winsock provider chain.\n\n" +
            "From inside an LSP, you can intercept every socket call: connect, send, recv, WSASendTo, WSARecvFrom, and so on — across all Winsock applications on the machine.\n\n" +
            "Legitimate uses: parental controls, corporate proxy enforcement, traffic shaping, DPI.\n\n" +
            "Deprecated in Windows 8 / Server 2012 — Microsoft recommends WFP (Windows Filtering Platform) instead. However, LSPs still load on current Windows versions if installed.\n\n" +
            "A corrupt LSP will break all network connectivity for every application. Recovery: netsh winsock reset (removes the entire Winsock catalog and resets to default)."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
// LSP registry location (managed by Winsock, not hand-edited)
HKLM\SYSTEM\CurrentControlSet\Services\WinSock2\Parameters\
  Protocol_Catalog9\Catalog_Entries\...

// Install an LSP programmatically (requires admin)
WSCInstallProvider(&providerGuid, L"C:\\lsp\\myhook.dll",
                   &protocolInfo, 1, &err);

// Remove an LSP (safer than deleting registry directly)
WSCDeinstallProvider(&providerGuid, &err);

// Emergency recovery (run as admin)
// netsh winsock reset
        """.trimIndent())
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("D. RUN AT LOGIN")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The Run and RunOnce keys cause the Explorer shell to launch executables at user login. HKLM entries run for all users; HKCU entries run only for the current user.\n\n" +
            "RunOnce entries are deleted after execution — useful for one-time setup.\n\n" +
            "More powerful: the Winlogon keys execute before the user desktop appears, with SYSTEM-level timing. Appending to Userinit or replacing Shell with a malicious binary gives deep persistence that runs before any user-mode security tools start."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
// Run for all users on every login
HKLM\SOFTWARE\Microsoft\Windows\CurrentVersion\Run
    MyApp = REG_SZ "C:\myapp\myapp.exe"

// Run for current user only
HKCU\SOFTWARE\Microsoft\Windows\CurrentVersion\Run
    MyApp = REG_SZ "C:\myapp\myapp.exe"

// Run once then delete the value
HKLM\SOFTWARE\Microsoft\Windows\CurrentVersion\RunOnce
    SetupStep = REG_SZ "C:\setup\step2.exe"

// Winlogon hooks (more powerful — before desktop appears)
HKLM\SOFTWARE\Microsoft\Windows NT\CurrentVersion\Winlogon
    Userinit = REG_SZ
      "C:\Windows\system32\userinit.exe,C:\evil\persist.exe,"
    Shell    = REG_SZ "C:\evil\fakeshell.exe"
        """.trimIndent())
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("E. DEFINING A SERVICE OR DRIVER")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The Service Control Manager reads HKLM\\SYSTEM\\CurrentControlSet\\Services at boot. Creating a subkey there with the right values registers a new service or kernel driver — no installer required.\n\n" +
            "Type=1 is a kernel-mode driver. Type=16 is a Win32 service. Start=2 means auto-start on every boot.\n\n" +
            "A reboot is needed for new entries unless NtLoadDriver is called directly (which requires SeLoadDriverPrivilege — an admin privilege). This is precisely how malware installs a persistent kernel driver with a single registry key creation."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
HKLM\SYSTEM\CurrentControlSet\Services\MyKernelDriver
    Type        = REG_DWORD 1     // 1=kernel driver, 16=Win32 svc
    Start       = REG_DWORD 2     // 2=auto, 3=demand, 4=disabled
    ErrorControl= REG_DWORD 1     // 1=normal (log, continue boot)
    ImagePath   = REG_SZ "\??\C:\drivers\mydrv.sys"
    DisplayName = REG_SZ "My Kernel Driver"

// After reboot: SCM loads mydrv.sys automatically
// Without reboot: call NtLoadDriver with the registry path
//   (requires SeLoadDriverPrivilege)
        """.trimIndent())
        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
