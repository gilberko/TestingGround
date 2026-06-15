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
fun BugChecksAndDriverVerifierScreen(navController: NavController) {
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
            text = "BUGCHECKS AND DRIVER VERIFIER",
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

        SectionHeader("WHAT IS A BUG CHECK?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A bug check (also called a Stop error) is the kernel's response to an unrecoverable error — a situation where continuing would corrupt system state, violate security invariants, or cause data loss. The kernel halts the system, saves a crash dump, and displays the BSOD.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("BSOD (Blue Screen of Death): the full-screen error display shown when a bug check occurs. Since Windows 8 it may appear black or blue depending on the error. On modern Windows it shows a sad-face emoji, a stop code (e.g., IRQL_NOT_LESS_OR_EQUAL), and a QR code.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("To trigger a bug check from a driver:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "KeBugCheckEx(\n" +
            "    BugCheckCode,  // stop code (ULONG)\n" +
            "    Parameter1,    // driver-defined context values\n" +
            "    Parameter2,\n" +
            "    Parameter3,\n" +
            "    Parameter4);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Use KeBugCheckEx only when you have detected a truly unrecoverable situation. For recoverable errors, return a failure NTSTATUS instead.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("COMMON STOP CODES")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "0x0000000A  IRQL_NOT_LESS_OR_EQUAL\n" +
            "            Paged memory accessed above APC_LEVEL,\n" +
            "            or invalid pointer dereference.\n\n" +
            "0x00000050  PAGE_FAULT_IN_NONPAGED_AREA\n" +
            "            Null/dangling pointer in kernel (often\n" +
            "            a freed structure that was still referenced).\n\n" +
            "0x000000D1  DRIVER_IRQL_NOT_LESS_OR_EQUAL\n" +
            "            Driver accessed paged memory at DISPATCH_LEVEL+.\n\n" +
            "0x000000C4  DRIVER_VERIFIER_DETECTED_VIOLATION\n" +
            "            Driver Verifier caught a rule violation.\n\n" +
            "0x00000044  MULTIPLE_IRP_COMPLETE_REQUESTS\n" +
            "            An IRP was completed more than once.\n\n" +
            "0x0000009F  DRIVER_POWER_STATE_FAILURE\n" +
            "            Driver held a power IRP too long.\n\n" +
            "0x000000E1  WORKER_THREAD_RETURNED_AT_BAD_IRQL\n" +
            "            Work item callback raised IRQL without lowering.\n\n" +
            "0x000000EF  CRITICAL_PROCESS_DIED\n" +
            "            A critical system process exited unexpectedly."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DRIVER VERIFIER")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Driver Verifier is a kernel-mode tool that instruments your driver at runtime to catch bugs that would otherwise be silent or intermittent. It makes errors fatal immediately, turning 'random crash later' into 'deterministic crash now with full context.'")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Enable from command line (requires reboot):")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Enable standard checks for your driver:\n" +
            "verifier /standard /driver mydriver.sys\n\n" +
            "// Enable all checks:\n" +
            "verifier /flags 0x209BB /driver mydriver.sys\n\n" +
            "// Disable (reset) after debugging:\n" +
            "verifier /reset\n\n" +
            "// Show current settings:\n" +
            "verifier /querysettings"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Key checks performed by Driver Verifier:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Special Pool\n" +
            "  Each allocation gets its own page, placed adjacent to\n" +
            "  a guard page. Buffer overflows/underflows cause an\n" +
            "  immediate page fault -> 0x50 bugcheck.\n\n" +
            "IRQL Checking\n" +
            "  Verifier forces random page-outs to catch paged\n" +
            "  memory access at elevated IRQL -> 0xD1 bugcheck.\n\n" +
            "Pool Tracking\n" +
            "  All allocations logged. On driver unload, any\n" +
            "  allocation not freed is reported as a leak -> 0xC4.\n\n" +
            "Deadlock Detection\n" +
            "  Monitors lock acquisition order across all drivers.\n" +
            "  Detects ABBA-style deadlocks before they manifest.\n\n" +
            "I/O Verification\n" +
            "  Checks IRP usage: double-completion, bad IRP fields,\n" +
            "  IoCompleteRequest at wrong IRQL, etc.\n\n" +
            "Stack Checking\n" +
            "  Checks kernel stack overflow conditions."
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("When Verifier catches a violation it calls KeBugCheckEx with code 0xC4 (DRIVER_VERIFIER_DETECTED_VIOLATION). The four parameters encode the specific violation type and the driver's address — !analyze -v in WinDbg decodes this automatically.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("BUGCHECK CALLBACKS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("You can register a function to be called during a bug check to save diagnostic state. The callback runs at HIGH_LEVEL with interrupts disabled — you cannot access paged memory, call most kernel APIs, or take locks. You can only fill a pre-allocated buffer.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "KBUGCHECK_CALLBACK_RECORD gRecord;\n" +
            "UCHAR gBugCheckBuffer[512]; // pre-allocated, non-paged\n\n" +
            "VOID MyBugCheckCallback(\n" +
            "    PVOID Buffer, ULONG Length)\n" +
            "{\n" +
            "    // Fill Buffer with diagnostic data\n" +
            "    // Length == sizeof(gBugCheckBuffer)\n" +
            "    // NO paging, NO locks, NO waits\n" +
            "    RtlCopyMemory(Buffer, &gMyState, min(Length, sizeof(gMyState)));\n" +
            "}\n\n" +
            "// Register in DriverEntry:\n" +
            "KeInitializeCallbackRecord(&gRecord);\n" +
            "KeRegisterBugCheckCallback(\n" +
            "    &gRecord,\n" +
            "    MyBugCheckCallback,\n" +
            "    gBugCheckBuffer,\n" +
            "    sizeof(gBugCheckBuffer),\n" +
            "    (PUCHAR)\"MyDriver\"); // component name\n\n" +
            "// Deregister in DriverUnload:\n" +
            "KeDeregisterBugCheckCallback(&gRecord);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("For richer data, use KeRegisterBugCheckReasonCallback with reason KbCallbackSecondaryDumpData. This allows your callback to provide a structured blob that gets embedded in the crash dump:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "KBUGCHECK_REASON_CALLBACK_RECORD gReasonRecord;\n\n" +
            "VOID MyReasonCallback(\n" +
            "    KBUGCHECK_CALLBACK_REASON Reason,\n" +
            "    PKBUGCHECK_REASON_CALLBACK_RECORD Record,\n" +
            "    PVOID ReasonSpecificData,\n" +
            "    ULONG ReasonSpecificLength)\n" +
            "{\n" +
            "    PKBUGCHECK_SECONDARY_DUMP_DATA dump =\n" +
            "        (PKBUGCHECK_SECONDARY_DUMP_DATA)ReasonSpecificData;\n" +
            "    dump->OutBuffer = gDiagBuffer;\n" +
            "    dump->OutBufferLength = sizeof(gDiagBuffer);\n" +
            "}\n\n" +
            "KeInitializeCallbackRecord(&gReasonRecord);\n" +
            "KeRegisterBugCheckReasonCallback(\n" +
            "    &gReasonRecord,\n" +
            "    MyReasonCallback,\n" +
            "    KbCallbackSecondaryDumpData,\n" +
            "    (PUCHAR)\"MyDriver\");"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("READING THE DUMP IN WINDBG")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Open the dump file in WinDbg (File > Open Crash Dump). The dump is typically at %SystemRoot%\\MEMORY.DMP or %SystemRoot%\\Minidump\\.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            ".bugcheck          // show stop code + 4 parameters\n\n" +
            "!analyze -v        // verbose auto-analysis:\n" +
            "                   //   identifies faulting driver,\n" +
            "                   //   decodes 0xC4 Verifier violations,\n" +
            "                   //   shows call stack at crash time\n\n" +
            "kb                 // show call stack at crash\n" +
            "!thread            // show thread state\n" +
            "!irql              // show IRQL at crash time\n\n" +
            "// For KeRegisterBugCheckCallback data:\n" +
            "// Find the callback list:\n" +
            "dt nt!_KBUGCHECK_CALLBACK_RECORD <address>\n" +
            "// .Buffer -> your saved data\n" +
            "// .Length -> byte count\n" +
            "db <Buffer> L<Length>   // dump buffer as bytes\n\n" +
            "// For KbCallbackSecondaryDumpData:\n" +
            "// Secondary data is embedded in the dump's\n" +
            "// secondary streams section; use .dumpdebug\n" +
            "// to enumerate, then db to read the blob."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
