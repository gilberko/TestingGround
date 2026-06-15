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
fun InterruptHandlingScreen(navController: NavController) {
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
            text = "INTERRUPT HANDLING",
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

        SectionHeader("WHAT HAPPENS WHEN AN INTERRUPT FIRES")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "When a hardware device raises an interrupt line, the CPU completes its " +
            "current instruction and then the following happens atomically in hardware:\n\n" +
            "1. The CPU looks up the interrupt vector in the IDT (Interrupt Descriptor Table).\n" +
            "2. If the interrupt arrived while in user mode, the CPU switches to the kernel " +
            "   stack automatically.\n" +
            "3. The CPU saves a trap frame onto the kernel stack — a snapshot of all " +
            "   registers at the moment of interruption.\n" +
            "4. The CPU jumps to the IDT handler, which raises IRQL to the device's DIRQL " +
            "   and eventually calls your ISR."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("THE TRAP FRAME (_KTRAP_FRAME)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The trap frame is the kernel struct (_KTRAP_FRAME) pushed onto the kernel " +
            "stack by the CPU and the interrupt stub. It contains the complete execution " +
            "state of the interrupted code:\n\n" +
            "  General-purpose registers: RAX, RBX, RCX, RDX, RSI, RDI, RBP, RSP, R8–R15\n" +
            "  Instruction pointer: RIP (where the thread will resume)\n" +
            "  Flags: RFLAGS\n" +
            "  Segment registers: CS, DS, ES, FS, GS, SS\n" +
            "  Optional error code (for exceptions like page faults)\n\n" +
            "When the interrupt is fully handled and IRQL drops back, the kernel restores " +
            "the trap frame to resume the thread exactly where it left off — " +
            "the thread has no awareness that it was interrupted."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHAT HAPPENS TO THE RUNNING THREAD")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The interrupted thread is suspended mid-execution. Its entire CPU state is " +
            "captured in the trap frame on its kernel stack. The thread is not context-switched " +
            "out (it stays the 'current thread' on that CPU); instead, the CPU simply starts " +
            "executing the ISR in the thread's place.\n\n" +
            "After the ISR returns, IRQL drops. If the IRQL drop triggers DPC processing, " +
            "DPCs run first. Eventually IRQL returns to the pre-interrupt level and the " +
            "thread resumes from its trap frame as if nothing happened."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ISR REGISTRATION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Call IoConnectInterrupt to register your ISR with the kernel. " +
            "The kernel creates a KINTERRUPT object, wires up the IDT, and allocates " +
            "an interrupt spin lock (if you pass NULL for the SpinLock parameter)."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "PKINTERRUPT gInterruptObject;\n\n" +
            "IoConnectInterrupt(\n" +
            "    &gInterruptObject,  // out: KINTERRUPT object\n" +
            "    MyISR,              // your ISR function\n" +
            "    context,            // passed to MyISR\n" +
            "    NULL,               // spin lock (NULL = kernel allocates one)\n" +
            "    vector,             // interrupt vector (from PnP resources)\n" +
            "    irql,               // device IRQL (from PnP resources)\n" +
            "    irql,               // SynchronizeIrql (same as device IRQL)\n" +
            "    LevelSensitive,     // or Latched\n" +
            "    FALSE,              // shared interrupt?\n" +
            "    affinity,           // CPU affinity mask\n" +
            "    FALSE);             // save FPU state?"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "In DriverUnload (or device removal), call IoDisconnectInterrupt(gInterruptObject) " +
            "to deregister the ISR. After this call returns, your ISR will never be called again."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ARE INTERRUPTS DISABLED DURING THE ISR?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Partially — and automatically. When IRQL is raised to the device's DIRQL, " +
            "the local APIC on that CPU masks all interrupt lines at the same or lower DIRQL. " +
            "This means no interrupt at the same or lower priority can preempt the ISR on " +
            "that CPU.\n\n" +
            "Higher DIRQL interrupts (from a higher-priority device) CAN still preempt the ISR.\n\n" +
            "Your ISR does NOT need to call STI or CLI. The kernel's IRQL mechanism " +
            "handles masking entirely. Manually toggling the interrupt flag in an ISR is " +
            "dangerous and incorrect."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("THE INTERRUPT SPIN LOCK")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Before calling your ISR, the kernel acquires the KINTERRUPT's spin lock " +
            "and raises the CPU to SynchronizeIrql (usually equal to the device IRQL). " +
            "This prevents:\n\n" +
            "  — The same ISR running simultaneously on two CPUs (for the same device).\n" +
            "  — Other code that holds the same spin lock from running concurrently.\n\n" +
            "The spin lock is released when your ISR returns.\n\n" +
            "Two different KINTERRUPT objects can share the same spin lock if they are " +
            "connected with the same SpinLock parameter in IoConnectInterrupt. This is used " +
            "for shared interrupt lines (legacy PCI INTx sharing) where multiple devices " +
            "use the same IRQ — all ISRs on that line run sequentially, protected by the " +
            "shared spin lock."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ISR MUST FINISH QUICKLY")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "While your ISR runs, all lower-IRQL interrupts on that CPU are masked and the " +
            "scheduler cannot preempt. A slow ISR causes:\n\n" +
            "  — Increased interrupt latency for other devices.\n" +
            "  — Starved DPC processing.\n" +
            "  — System jitter and missed real-time deadlines.\n\n" +
            "The ISR should do the absolute minimum: acknowledge the device (clear the " +
            "interrupt pending flag in hardware so it stops asserting the line), copy a " +
            "small amount of raw data from hardware registers into the device extension, " +
            "and queue a DPC to do the rest."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("QUEUEING A DPC FROM THE ISR")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "KeInsertQueueDpc adds a KDPC object to the per-CPU DPC queue. " +
            "After all ISRs for the current IRQL have returned and the IRQL drops to " +
            "DISPATCH_LEVEL, the kernel drains the DPC queue — calling each DPC routine " +
            "in order."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Initialize once (e.g., in AddDevice or DriverEntry):\n" +
            "KDPC gDpc;\n" +
            "KeInitializeDpc(&gDpc, MyDpcRoutine, context);\n\n" +
            "// ISR — runs at DIRQL, interrupt spin lock held:\n" +
            "BOOLEAN MyISR(PKINTERRUPT Interrupt, PVOID Context) {\n" +
            "    MY_EXT* ext = (MY_EXT*)Context;\n\n" +
            "    // 1. Read device status to acknowledge the interrupt:\n" +
            "    ext->LastStatus = READ_PORT_UCHAR(ext->StatusPort);\n\n" +
            "    // 2. Queue DPC to finish processing:\n" +
            "    KeInsertQueueDpc(&gDpc, NULL, NULL);\n\n" +
            "    return TRUE;  // TRUE = we handled this interrupt\n" +
            "               // FALSE = not ours (shared interrupt)\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("THE DPC ROUTINE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The DPC runs at DISPATCH_LEVEL on one of the CPUs (not necessarily the one " +
            "that received the interrupt). It can perform substantial work: processing data " +
            "from the device extension, signaling events, completing IRPs.\n\n" +
            "If the DPC needs to access device registers that the ISR also reads (i.e., " +
            "shared data), it must acquire the interrupt spin lock to synchronize with the ISR. " +
            "This temporarily raises the DPC to DIRQL while the lock is held."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "VOID MyDpcRoutine(\n" +
            "    PKDPC Dpc,\n" +
            "    PVOID Context,\n" +
            "    PVOID SysArg1,\n" +
            "    PVOID SysArg2)\n" +
            "{\n" +
            "    MY_EXT* ext = (MY_EXT*)Context;\n\n" +
            "    // If we need to sync with the ISR:\n" +
            "    KIRQL oldIrql;\n" +
            "    KeAcquireInterruptSpinLock(gInterruptObject, &oldIrql);\n" +
            "    UCHAR status = ext->LastStatus;  // read ISR-written data\n" +
            "    KeReleaseInterruptSpinLock(gInterruptObject, oldIrql);\n\n" +
            "    // Process the data (at DISPATCH_LEVEL):\n" +
            "    // - Complete pending IRPs\n" +
            "    // - Signal events\n" +
            "    // - Queue work items for PASSIVE_LEVEL work\n" +
            "    if (ext->PendingIrp) {\n" +
            "        ext->PendingIrp->IoStatus.Status = STATUS_SUCCESS;\n" +
            "        ext->PendingIrp->IoStatus.Information = 1;\n" +
            "        IoCompleteRequest(ext->PendingIrp, IO_DISK_INCREMENT);\n" +
            "        ext->PendingIrp = NULL;\n" +
            "    }\n" +
            "}"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The DPC cannot call APIs that sleep (KeWaitForSingleObject, ZwXxx, etc.) because " +
            "it runs at DISPATCH_LEVEL. For work that requires PASSIVE_LEVEL, queue a work item " +
            "from the DPC using IoQueueWorkItem."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
