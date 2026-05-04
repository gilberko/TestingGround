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
fun IrqlScreen(navController: NavController) {
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
            text = "IRQL",
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

        SectionHeader("WHAT IS IRQL")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Interrupt Request Level: a number (0–31 on x86/x64) stored in a CPU-local " +
            "register (CR8 on x64). The CPU masks all interrupts at or below the current " +
            "IRQL. Higher IRQL code always preempts lower. Each processor tracks its own " +
            "IRQL independently — raising IRQL on CPU0 has no effect on CPU1."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("PASSIVE_LEVEL (0)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Normal thread execution. Can take page faults, wait on dispatcher objects " +
            "(events, mutexes, semaphores), call nearly any kernel API, allocate paged or " +
            "non-paged pool. This is where driver dispatch routines run when called from " +
            "user-mode threads."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("APC_LEVEL (1)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The only difference from PASSIVE_LEVEL is that APC interrupts are masked off — " +
            "kernel APCs cannot execute while the CPU is at APC_LEVEL. Like PASSIVE_LEVEL, " +
            "APC_LEVEL still implies a thread context and code can be paged out; page faults " +
            "are still handled normally. Some dispatch routines (e.g. for pageable drivers " +
            "servicing a page-fault read) run at APC_LEVEL."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DISPATCH_LEVEL (2)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The Windows thread scheduler (dispatcher) runs at DISPATCH_LEVEL. While a CPU " +
            "is at DISPATCH_LEVEL, context switches on that CPU are disabled — the scheduler " +
            "cannot run. Rules: no paged memory (page fault → IRQL_NOT_LESS_OR_EQUAL bugcheck), " +
            "no waiting on dispatcher objects, only non-paged pool allocations. Spin locks " +
            "are always acquired at DISPATCH_LEVEL (KeAcquireSpinLock raises IRQL to " +
            "DISPATCH_LEVEL automatically)."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DIRQL (> 2, DEVICE-SPECIFIC)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "ISRs (Interrupt Service Routines) run at the device's assigned IRQL. " +
            "Extremely restricted: no memory allocation, no synchronization primitives, " +
            "must return as fast as possible. An ISR typically just reads hardware registers " +
            "to acknowledge the interrupt and queues a DPC to do the real work."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("PREEMPTION BETWEEN LEVELS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "When a hardware interrupt fires whose IRQL is above the current CPU IRQL, the " +
            "CPU saves state and raises IRQL to the interrupt's level. The ISR runs. On " +
            "return, IRQL drops back and the preempted code resumes. This is how an ISR " +
            "preempts a DPC, which in turn preempts a thread."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DPCs (DEFERRED PROCEDURE CALLS)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Work items queued to run at DISPATCH_LEVEL, typically by an ISR to defer " +
            "non-time-critical processing (e.g. processing a received network packet). Each " +
            "CPU has its own DPC queue. The queue drains when IRQL drops below DISPATCH_LEVEL: " +
            "the system raises to DISPATCH_LEVEL, runs each queued DPC, then lowers IRQL."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "KDPC g_Dpc;\n\n" +
            "VOID DpcRoutine(PKDPC Dpc, PVOID Context,\n" +
            "                PVOID Arg1, PVOID Arg2)\n" +
            "{\n" +
            "    // Runs at DISPATCH_LEVEL\n" +
            "    DbgPrint(\"DPC fired\\n\");\n" +
            "}\n\n" +
            "// In ISR (at DIRQL):\n" +
            "KeInitializeDpc(&g_Dpc, DpcRoutine, nullptr);\n" +
            "KeInsertQueueDpc(&g_Dpc, nullptr, nullptr);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SAME-IRQL PREEMPTION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "On a single CPU: code at DISPATCH_LEVEL cannot be preempted by another DPC " +
            "— the DPC queue only drains after IRQL drops. On a multi-core system, two CPUs " +
            "can simultaneously execute DPCs (or even the same DPC routine if not serialized). " +
            "Shared data accessed from DPCs on different CPUs must be protected by a spin lock."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("THREAD SCHEDULER IS PER-CPU")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Raising IRQL to DISPATCH_LEVEL only disables scheduling on that specific CPU. " +
            "Other CPUs continue scheduling threads normally. Holding DISPATCH_LEVEL is not " +
            "a system-wide freeze — it is purely local to the processor executing the code."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ATOMIC CONTEXT (LINUX ANALOGY)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "In Linux, \"atomic context\" (in_atomic() / in_interrupt()) means: you cannot " +
            "sleep. Windows DISPATCH_LEVEL and above is the equivalent. At DIRQL there are " +
            "no threads — an ISR is a hardware interrupt handler, not a thread. At " +
            "DISPATCH_LEVEL, DPCs run on whatever thread was active when the interrupt fired, " +
            "but you have no thread identity and cannot yield. Sleeping or waiting requires " +
            "PASSIVE_LEVEL, where the scheduler can swap in another thread while this one blocks."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
