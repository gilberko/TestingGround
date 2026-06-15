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
fun WerFaultScreen(navController: NavController) {
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
            text = "WERFAULT AND CRASH DUMPS",
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

        SectionHeader("WHAT IS WERFAULT?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "WerFault.exe (Windows Error Reporting Fault) is the Windows crash handler. When a process encounters an unhandled exception, the Windows Error Reporting (WER) infrastructure eventually spawns WerFault.exe to:\n\n" +
            "  - Collect information about the crash (exception type, call stack, module list)\n" +
            "  - Generate a minidump file capturing the process's memory state at the time of the crash\n" +
            "  - Optionally report the crash to Microsoft's WER service for analysis\n" +
            "  - Display the \"[AppName] has stopped working\" dialog to the user\n\n" +
            "WerFault.exe is a legitimate Windows system binary located at %SystemRoot%\\System32\\WerFault.exe. It runs with the privileges of the crashed process's user.\n\n" +
            "The WER service (wersvc) runs as a background service and acts as the coordinator — it receives crash notifications from the kernel and decides whether to spawn WerFault, queue the report, or silently discard it."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHAT HAPPENS WHEN A PROCESS CRASHES?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The crash flow has two distinct phases — in-process and out-of-process:\n\n" +
            "Phase 1 — In-process exception handling:\n" +
            "  1. The process encounters an unhandled exception (e.g., null pointer dereference, divide by zero, stack overflow)\n" +
            "  2. The CPU generates a hardware exception; control transfers to the kernel\n" +
            "  3. The kernel dispatches the exception to user mode via KiUserExceptionDispatcher in ntdll.dll\n" +
            "  4. The SEH chain is walked — each registered exception handler is tried\n" +
            "  5. No handler claims the exception → the OS calls the UnhandledExceptionFilter (UEF)\n" +
            "  6. The default UEF in ntdll calls WerpReportFault, which makes an RPC call to the WER service\n\n" +
            "Phase 2 — Out-of-process crash handling:\n" +
            "  7. The WER service (wersvc) receives the crash report via RPC\n" +
            "  8. WerSvc calls NtRaiseHardError internally and suspends the crashed process's threads\n" +
            "  9. WerSvc spawns WerFault.exe, passing it the crashed process's PID as a command-line argument\n" +
            "  10. WerFault opens the suspended process, generates the minidump, shows the dialog\n" +
            "  11. When done, WerFault signals WerSvc; WerSvc terminates the crashed process\n\n" +
            "Why separate process?\n" +
            "  The crashed process may have a corrupted heap, stack, or other state. WerFault runs in a separate, clean process so it can safely read the crashed process's memory without itself being affected by the corruption."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("THE CRASH HANDLER CHAIN")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The full call chain from exception to WerFault:\n\n" +
            "  Hardware exception\n" +
            "  → KiUserExceptionDispatcher (ntdll.dll) — dispatches to SEH frames\n" +
            "  → No handler found\n" +
            "  → UnhandledExceptionFilter (ntdll.dll)\n" +
            "     → Checks for attached debugger (if debugger attached, second-chance exception is delivered to debugger instead)\n" +
            "     → Calls WerpReportFault (wer.dll)\n" +
            "  → WerpReportFault:\n" +
            "     → RPC call to WER service (wersvc, running as LocalSystem)\n" +
            "  → WerSvc:\n" +
            "     → Suspends crashed process threads\n" +
            "     → Creates WerFault.exe process\n" +
            "     → WerFault.exe opens target process, generates dump, shows dialog\n" +
            "  → WerSvc terminates crashed process after WerFault completes\n\n" +
            "Application can customize behavior via:\n" +
            "  SetUnhandledExceptionFilter() — replace the default UEF\n" +
            "  WerRegisterMemoryBlock() — mark additional memory regions for inclusion in the dump\n" +
            "  WerSetFlags(WER_FAULT_REPORTING_NO_UI) — suppress the dialog for services"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("HOW WERFAULT PRODUCES A MEMORY DUMP")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "WerFault generates the dump by calling MiniDumpWriteDump from dbghelp.dll. The steps:\n\n" +
            "  1. OpenProcess(PROCESS_ALL_ACCESS, FALSE, crashedPid)\n" +
            "     → Opens the suspended crashed process\n" +
            "  2. Creates a .dmp file in %LocalAppData%\\CrashDumps\\\n" +
            "     (or %ProgramData%\\Microsoft\\Windows\\WER\\ReportQueue for service crashes)\n" +
            "  3. Calls MiniDumpWriteDump(hProcess, pid, hDumpFile, dumpType, pExceptionInfo, NULL, NULL)\n" +
            "     → Reads process memory, thread state, module list\n" +
            "     → Writes the structured minidump file\n\n" +
            "For kernel-assisted dumps, WerFault may also use NtSystemDebugControl with SystemDebugObjectInformationEx to obtain kernel-side information about the crashed thread.\n\n" +
            "MINIDUMP_TYPE flags (from dbghelp.h):\n\n" +
            "  MiniDumpNormal (0x0)\n" +
            "    Minimal — thread stacks, module list, exception record. Small file.\n" +
            "  MiniDumpWithFullMemory (0x2)\n" +
            "    All readable pages from the process. Can be gigabytes for large processes.\n" +
            "  MiniDumpWithHandleData (0x4)\n" +
            "    Includes open handle table\n" +
            "  MiniDumpWithUnloadedModules (0x20)\n" +
            "    Recently unloaded DLL information\n" +
            "  MiniDumpWithIndirectlyReferencedMemory (0x40)\n" +
            "    Memory pointed to by local variables and registers"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("MINIDUMP FORMAT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "A minidump file (.dmp) is a structured binary format defined in dbghelp.h. Despite the name, it can be very large. The structure:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "MINIDUMP_HEADER\n" +
            "  Signature: 'MDMP' (0x504D444D)\n" +
            "  Version, NumberOfStreams, StreamDirectoryRva\n" +
            "  CheckSum, TimeDateStamp, Flags\n" +
            "\n" +
            "MINIDUMP_DIRECTORY[NumberOfStreams]\n" +
            "  Each entry: StreamType + Location (Rva + DataSize)\n" +
            "\n" +
            "Stream types:\n" +
            "  ThreadListStream (3)\n" +
            "    One MINIDUMP_THREAD per thread:\n" +
            "    ThreadId, SuspendCount, PriorityClass,\n" +
            "    Stack (start+size), ThreadContext (full CONTEXT record)\n" +
            "\n" +
            "  ModuleListStream (4)\n" +
            "    One MINIDUMP_MODULE per loaded DLL/EXE:\n" +
            "    BaseOfImage, SizeOfImage, CheckSum,\n" +
            "    ModuleName, VersionInfo, CvRecord (debug info path)\n" +
            "\n" +
            "  MemoryListStream (5)\n" +
            "    List of MINIDUMP_MEMORY_DESCRIPTOR ranges:\n" +
            "    StartOfMemoryRange + MemoryRange (Rva + DataSize)\n" +
            "    Contains the actual memory bytes for captured ranges\n" +
            "\n" +
            "  ExceptionStream (6)\n" +
            "    MINIDUMP_EXCEPTION_STREAM:\n" +
            "    ThreadId (which thread crashed)\n" +
            "    ExceptionRecord (code, address, parameters)\n" +
            "    ThreadContext (registers AT TIME OF CRASH — this is\n" +
            "    what debuggers use to reconstruct the crash point)"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "\"Mini\" does not mean small. MiniDumpWithFullMemory captures every readable page in the process and produces a file as large as the process's working set — gigabytes for browsers, servers, or large applications.\n\n" +
            "The exception stream's ThreadContext contains the CPU register state at the exact moment of the crash: RIP (instruction pointer), RSP (stack pointer), general-purpose registers, and flags. This is what allows a debugger to reconstruct the precise crash location."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("CAN WERFAULT TARGET LSASS?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "WerFault generates dumps by calling OpenProcess(PROCESS_ALL_ACCESS) on the target process and then MiniDumpWriteDump. There is nothing inherent to WerFault that restricts it to only its own crashed process — the same code path can be aimed at any process.\n\n" +
            "Historically (pre-PPL):\n" +
            "  Any admin-level process — including WerFault invoked manually, or any code that calls MiniDumpWriteDump — could target lsass.exe. The only requirement was an admin account.\n\n" +
            "With PPL (RunAsPPL = 1):\n" +
            "  LSASS runs as PPL-Lsa. WerFault.exe is not a Protected Process. When WerFault calls OpenProcess(PROCESS_ALL_ACCESS) against lsass, the kernel checks protection levels:\n" +
            "  WerFault protection: 0 (unprotected)\n" +
            "  LSASS protection: PPL-Lsa\n" +
            "  Result: OpenProcess returns STATUS_ACCESS_DENIED\n" +
            "  MiniDumpWriteDump fails immediately — it cannot open the process.\n\n" +
            "This is the design: PPL ensures that even the crash dump infrastructure cannot read LSASS memory, because the crash dump process is not itself protected at the required level."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("COMSVCS.DLL MINIDUMP LOLBAS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "One of the most well-known LSASS dumping techniques uses comsvcs.dll, a legitimate Windows COM server DLL. It happens to export a function named MiniDump (ordinal 110) that is essentially a thin wrapper around MiniDumpWriteDump.\n\n" +
            "This technique is called LOLBAS — Living Off the Land Binaries and Scripts — because it uses a signed Windows binary to perform the malicious action, avoiding the need to drop a third-party tool like mimikatz."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "# Get LSASS PID\n" +
            "${'$'}lsassPid = (Get-Process lsass).Id\n" +
            "\n" +
            "# Dump using comsvcs.dll MiniDump export (ordinal 110)\n" +
            "# Must run from a cmd.exe started with 'runas' or elevated PS\n" +
            "rundll32.exe C:\\Windows\\System32\\comsvcs.dll,MiniDump `\n" +
            "    ${'$'}lsassPid C:\\Windows\\Temp\\lsass.dmp full\n" +
            "\n" +
            "# Why 'full'? It passes MiniDumpWithFullMemory to MiniDumpWriteDump\n" +
            "# The resulting lsass.dmp can be analyzed offline with mimikatz:\n" +
            "# sekurlsa::minidump lsass.dmp\n" +
            "# sekurlsa::logonpasswords"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Why does comsvcs.dll have a MiniDump export?\n" +
            "  comsvcs.dll is a COM+ Services support DLL. It historically exposed this function for crash reporting of COM+ server applications. It was never intended as a security feature — it just happened to be there.\n\n" +
            "Blocked by:\n" +
            "  PPL (RunAsPPL = 1): rundll32 is not PPL-protected, so OpenProcess on lsass fails\n" +
            "  ASR rule: \"Block credential stealing from LSASS\"\n" +
            "  EDR detection: rundll32 + comsvcs.dll + lsass PID is a well-known IoC"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("PROTECTIONS AGAINST LSASS DUMP")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Multiple complementary layers protect LSASS against memory dump attacks:\n\n" +
            "PPL (RunAsPPL = 1):\n" +
            "  Blocks OpenProcess from any non-PPL process. Defeats all user-mode dumping techniques including WerFault, comsvcs.dll MiniDump, ProcDump, and Task Manager.\n\n" +
            "Credential Guard (VBS):\n" +
            "  Even if memory is somehow read, the actual NTLM hashes and Kerberos keys are in VTL1. What an attacker reads from LSASS memory are ciphertexts and handles, not usable credentials.\n\n" +
            "HVCI (Hypervisor-Protected Code Integrity):\n" +
            "  Prevents unsigned kernel drivers from loading. This removes the BYOVD (Bring Your Own Vulnerable Driver) attack path that bypasses PPL at kernel level.\n\n" +
            "Windows Defender / EDR detection:\n" +
            "  ObRegisterCallbacks in the kernel allow security drivers to intercept all OpenProcess calls. Any call targeting lsass.exe with sensitive access flags (PROCESS_VM_READ, PROCESS_ALL_ACCESS) is detected and can be blocked.\n\n" +
            "ASR (Attack Surface Reduction) rules:\n" +
            "  Microsoft Defender includes an ASR rule specifically to block credential stealing from LSASS. Can block comsvcs.dll MiniDump, ProcDump, and similar techniques by policy.\n\n" +
            "WerFault exclusion:\n" +
            "  Microsoft explicitly configured WerFault so it does NOT automatically get PPL elevation when targeting lsass. This was intentional — WerFault itself would otherwise become an attack vector."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
