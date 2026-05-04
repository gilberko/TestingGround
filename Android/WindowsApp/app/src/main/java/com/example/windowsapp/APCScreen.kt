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
fun APCScreen(navController: NavController) {
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
            text = "APC",
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

        SectionHeader("WHAT IS AN APC?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("An Asynchronous Procedure Call (APC) is a function queued to a specific thread that runs in that thread's context. APCs are how the kernel delivers asynchronous notifications and I/O completions to threads without creating new threads. Each thread maintains two APC queues: a kernel APC queue and a user APC queue.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SPECIAL KERNEL APC")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A special kernel APC runs at APC_LEVEL in kernel mode. It preempts the target thread immediately — it does not wait for an alertable wait or any explicit yield point.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Delivery timing: if the target thread is running at PASSIVE_LEVEL on any CPU, the kernel raises IRQL to APC_LEVEL and calls the APC routine. If the thread is at a higher IRQL, the APC is held pending and fires as soon as the thread drops back to PASSIVE_LEVEL.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Special kernel APCs cannot be blocked by KeEnterCriticalRegion (critical regions only block normal kernel APCs). The only way to prevent a special kernel APC from running is to stay at or above APC_LEVEL.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("I/O completion: this is the mechanism behind async I/O. When an I/O operation completes (e.g., a ReadFile with an OVERLAPPED structure finishes), the I/O manager queues a special kernel APC (IopCompleteRequest) to the requesting thread. This APC copies data from the kernel-mode system buffer to the user-mode buffer, signals the OVERLAPPED event, and updates the OVERLAPPED.InternalHigh byte count.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Kernel: queue a special kernel APC\n" +
            "KAPC apc;\n" +
            "KeInitializeApc(\n" +
            "    &apc,\n" +
            "    TargetThread,\n" +
            "    OriginalApcEnvironment,\n" +
            "    KernelRoutine,       // runs at APC_LEVEL (required)\n" +
            "    RundownRoutine,      // called if thread exits before APC runs\n" +
            "    NULL,                // NormalRoutine = NULL -> special kernel APC\n" +
            "    KernelMode,\n" +
            "    NULL);\n\n" +
            "KeInsertQueueApc(&apc, SystemArg1, SystemArg2, IO_NO_INCREMENT);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("NORMAL KERNEL APC")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A normal kernel APC has two parts: a KernelRoutine (runs at APC_LEVEL) and a NormalRoutine (runs at PASSIVE_LEVEL after the KernelRoutine completes).")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Delivery timing: unlike special kernel APCs, normal kernel APCs ARE blocked by KeEnterCriticalRegion. They run when the thread is at PASSIVE_LEVEL and kernel APCs are enabled. If the thread is in a critical region (between KeEnterCriticalRegion and KeLeaveCriticalRegion), normal kernel APCs are deferred until the thread leaves the critical region.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The NormalRoutine runs at PASSIVE_LEVEL, so it can sleep, access paged memory, and do most kernel operations. Normal kernel APCs are less commonly used in driver code compared to special kernel APCs.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Normal kernel APC: NormalRoutine is non-NULL\n" +
            "KeInitializeApc(\n" +
            "    &apc, TargetThread, OriginalApcEnvironment,\n" +
            "    KernelRoutine,     // runs at APC_LEVEL\n" +
            "    RundownRoutine,\n" +
            "    NormalRoutine,     // runs at PASSIVE_LEVEL\n" +
            "    KernelMode,\n" +
            "    NormalContext);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("USER APC")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A user APC runs in user mode at PASSIVE_LEVEL. It is only delivered when the thread enters an alertable wait — a wait that explicitly opts in to APC delivery:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// User-mode alertable wait:\n" +
            "WaitForSingleObjectEx(hEvent, INFINITE, TRUE); // TRUE = alertable\n" +
            "SleepEx(0, TRUE);   // alertable sleep\n" +
            "NtTestAlert();      // flush user APCs immediately\n\n" +
            "// Queue a user APC from user mode:\n" +
            "QueueUserAPC(MyApcRoutine, hThread, dwData);\n\n" +
            "// User APC routine signature:\n" +
            "VOID CALLBACK MyApcRoutine(ULONG_PTR dwParam);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("I/O completion routines (ReadFileEx, WriteFileEx) run as user APCs. The kernel queues the completion to the thread's user APC queue; when the thread enters an alertable wait, the completion routine fires.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("User APCs cannot preempt the thread mid-execution. The thread must cooperate by entering an alertable wait. This makes them safer than special kernel APCs but requires explicit programmer involvement.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("APC AND I/O COMPLETION — REENTRANCY PROBLEM")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("If your code runs inside a special kernel APC's kernel routine (at APC_LEVEL), you must not initiate synchronous I/O that waits for completion. Here is why:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Problem:\n" +
            "1. Your special kernel APC routine runs at APC_LEVEL\n" +
            "2. You issue an I/O request (e.g., ZwReadFile synchronously)\n" +
            "3. The I/O completes; I/O manager queues a special\n" +
            "   kernel APC to this thread to signal completion\n" +
            "4. But we are already AT APC_LEVEL — APC delivery is\n" +
            "   disabled at this IRQL\n" +
            "5. The I/O waits forever for the APC to deliver,\n" +
            "   but the APC can't deliver while we're at APC_LEVEL\n" +
            "6. DEADLOCK → system hang → eventual bugcheck"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The same problem applies to issuing I/O from a spin lock's critical section (at DISPATCH_LEVEL) for the same reason — no APC delivery above PASSIVE_LEVEL.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("PAGED MEMORY IN SPECIAL KERNEL APC")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A special kernel APC's KernelRoutine runs at APC_LEVEL. At APC_LEVEL, APC delivery is disabled. Accessing paged memory at APC_LEVEL is dangerous:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "APC_LEVEL paged-memory problem:\n" +
            "1. Your code (at APC_LEVEL) accesses a paged address\n" +
            "2. The page is not in RAM → CPU raises #PF (page fault)\n" +
            "3. Page fault handler issues paging I/O to read page in\n" +
            "4. Paging I/O completes; signals via a special kernel APC\n" +
            "5. APC delivery is disabled (we're at APC_LEVEL)\n" +
            "6. Paging I/O completion APC cannot fire\n" +
            "7. DEADLOCK → BSOD: IRQL_NOT_LESS_OR_EQUAL or\n" +
            "   PAGE_FAULT_IN_NONPAGED_AREA"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Rule: at APC_LEVEL and above, only access non-paged memory (NonPagedPool, stack variables, locked MDLs). Paged pool is safe only at PASSIVE_LEVEL. The NormalRoutine of a normal kernel APC runs at PASSIVE_LEVEL and can access paged memory freely.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("APC TYPE SUMMARY")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Type            IRQL        Preempts?  Blocked by?      When runs\n" +
            "─────────────────────────────────────────────────────────────────\n" +
            "Special Kernel  APC_LEVEL   Immediate  Nothing (DDI)    PASSIVE_LEVEL\n" +
            "Normal Kernel   APC+PASSIVE Deferred   CriticalRegion   PASSIVE+APCs on\n" +
            "User            PASSIVE     Never      Non-alertable    Alertable wait\n\n" +
            "I/O completion -> special kernel APC (IopCompleteRequest)\n" +
            "ReadFileEx completion routine -> user APC"
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
