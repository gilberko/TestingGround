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
fun TracingAndDebuggingScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "TRACING AND\nDEBUGGING",
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

        SectionHeader("EVENT TRACING FOR WINDOWS (ETW)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Yes — user-mode and kernel-mode code can both define and post brand new custom events, not just consume events from existing providers.\n\n" +
            "A component first calls EventRegister to register its own provider GUID with the ETW subsystem. From then on it calls EventWrite / EventWriteEx to log custom events under that provider.\n\n" +
            "There are two authoring styles. Manifest-based: you author an XML manifest describing your events, run it through the Message Compiler (mc.exe), which generates the headers and macros you call from code. TraceLogging: a newer, simpler style where events are self-describing at the call site — no manifest needed.\n\n" +
            "WPP tracing (below) is really a third style layered on top of this same underlying engine."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
REGGUID EventRegister(&MyProviderGuid,
                       NULL, NULL,
                       &RegHandle);

EventWrite(RegHandle,
           &MyEventDescriptor,
           EventDataCount,
           EventData);
        """.trimIndent())
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WINDBG AND KDNET")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "WinDbg debugs both kernel mode and user mode — the same tool attaches to a live user process, opens a user-mode crash dump, or connects to a kernel debug target (physical machine, VM, or dump file).\n\n" +
            "KDNET lets you debug a kernel over the network instead of a serial/USB cable. On the target machine, kdnet.exe (shipped with the WDK/SDK, or the WinDbg Store app) patches the boot configuration via bcdedit to enable a network debug transport on a chosen NIC and port, and prints a connection key.\n\n" +
            "The NIC must be on Microsoft's supported list (Intel, Broadcom, Realtek, Atheros, Emulex, Mellanox, Cisco — see VerifiedNicList.xml). For a physical PCI/PCIe NIC you also supply busparams (bus/device/function, found in Device Manager). BitLocker or Secure Boot sometimes needs to be temporarily suspended before bcdedit will apply the change.\n\n" +
            "Once configured and rebooted, WinDbg on the host connects with: windbg -k net:port=<port>,key=<key>"
        )
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SYSINTERNALS SUITE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "DebugView captures debug output from both worlds: OutputDebugString calls from user-mode processes, and DbgPrint / DbgPrintEx / KdPrint calls from kernel-mode drivers — all in one live scrolling window.\n\n" +
            "IRQL fact (corrected from the common assumption): DbgPrint / DbgPrintEx are documented as callable up to DIRQL, not merely up to DISPATCH_LEVEL. The caveats are that Unicode format specifiers (%S, %ls, %wZ) require PASSIVE_LEVEL, and calling at very high IRQL risks a deadlock because the debugger's own output path can involve inter-processor interrupts.\n\n" +
            "Process Monitor and Process Explorer are both part of the Sysinternals suite — Process Monitor logs file/registry/process/network activity in real time; Process Explorer is an enhanced Task Manager replacement showing handles, DLLs, and process trees.\n\n" +
            "NotMyFault is also a Sysinternals tool — it is built specifically to intentionally crash, hang, or leak memory on a system, producing a BSOD or memory dump on demand for testing crash-analysis workflows."
        )
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WPP SOFTWARE TRACING")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "WPP (Windows software trace Preprocessor) is a set of macros and specially formatted comments — DoTraceMessage calls — that a build-time preprocessor scans to auto-generate trace instrumentation. It runs on the same underlying engine as ETW: the generated code ultimately calls WmiTraceMessage / WmiTraceMessageVa.\n\n" +
            "Verified: yes, WPP tracing can be used at high/elevated IRQL. Microsoft's documentation states WmiTraceMessage / DoTraceMessage can be called at any IRQL and runs at the IRQL of the caller.\n\n" +
            "The one caveat is delivery, not safety: if a trace call happens above DISPATCH_LEVEL at a moment when the trace buffers are full, the kernel cannot allocate a new buffer at that IRQL, so the message is silently dropped rather than causing a fault. The call itself remains safe to make."
        )
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("XPERF / WINDOWS PERFORMANCE TOOLKIT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "xperf records system-wide ETW traces — the NT Kernel Logger plus other providers — and analyzes them to find bottlenecks spanning both user-mode and kernel-mode activity: CPU sampling, DPCs, ISRs, context switches, disk I/O, and more.\n\n" +
            "It's part of the Windows Performance Toolkit (WPT). The UI side has largely been superseded by Windows Performance Recorder (WPR) and Windows Performance Analyzer (WPA), but xperf.exe itself still exists and is scriptable for automated trace capture."
        )
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("VISUAL STUDIO DEBUGGER")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "From the IDE, the Visual Studio debugger can attach to an already-running process, launch/run a process under the debugger from a fresh start, and — via msvsmon.exe (the Remote Tools for Visual Studio / Remote Debugging Monitor) — debug a process running on a separate machine or VM.\n\n" +
            "msvsmon runs on the remote target and relays debug events back to the local Visual Studio instance. The version of msvsmon on the remote machine must match the version of Visual Studio doing the debugging."
        )
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DRIVER VERIFIER")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Driver Verifier (verifier.exe) is a Windows-included stress-testing tool for kernel-mode drivers. Enabling it for a driver turns on checks such as: pool tracking (catches leaks, double-frees, and use of the wrong pool type), forced IRQL checking (raises pressure to catch code that touches pageable memory above PASSIVE/APC level), forced completion of pending IRPs, low-resources simulation (randomly fails pool allocations to exercise error paths), deadlock detection, and DMA verification.\n\n" +
            "It's commonly the first thing to enable when a driver causes intermittent, hard-to-reproduce crashes."
        )
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("HLK / HCK — HARDWARE CERTIFICATION TESTING")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The Windows Hardware Lab Kit (HLK) — HCK (Hardware Certification Kit) is the older, pre-Windows-10 name for effectively the same system — uses a controller/client architecture.\n\n" +
            "An HLK Controller machine runs HLK Studio, which schedules tests, collects results, and packages the final submission. One or more separate HLK Client machines connect to the controller; the actual driver or device under test runs on the client, executing the assigned battery of tests and reporting results back to the controller.\n\n" +
            "This is how hardware and drivers get certified/signed for Windows — the same controller can drive many client machines through the same test pass."
        )
        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
