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
fun IrpScreen(navController: NavController) {
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
            text = "IRP",
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

        // WHAT IS IT
        SectionHeader("WHAT IS IT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "_IRP (I/O Request Packet) is the fundamental communication unit of the Windows " +
            "I/O subsystem. Every I/O operation — read, write, IOCTL, PnP, power management " +
            "— is represented as an IRP that flows down a device stack and back up on completion."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // WHO CREATES IT
        SectionHeader("WHO CREATES IT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The I/O Manager creates IRPs in response to system calls: NtReadFile, NtWriteFile, " +
            "NtDeviceIoControlFile, and others. Drivers can also create IRPs themselves:\n\n" +
            "  IoAllocateIrp               — raw allocation\n" +
            "  IoBuildSynchronousFsdRequest — for synchronous I/O to a lower driver\n" +
            "  IoBuildAsynchronousFsdRequest — for async I/O to a lower driver\n" +
            "  IoBuildDeviceIoControlRequest — to send an IOCTL to a lower driver"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // THE DEVICE STACK
        SectionHeader("THE DEVICE STACK")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Windows uses a layered driver model. Multiple DEVICE_OBJECTs are chained on " +
            "top of one another via IoAttachDeviceToDeviceStack. The resulting structure is " +
            "called the device stack.\n\n" +
            "When an IRP is sent to a device object, it enters at the top of its stack and " +
            "travels downward. Each driver in the stack gets to inspect or modify the IRP, " +
            "then either complete it or pass it to the next lower driver."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "  [Filter Driver A]   <-- IRP arrives here first\n" +
            "  [Filter Driver B]\n" +
            "  [Bus/Function Driver] <-- completes the IRP\n" +
            "       (PDO)"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // IRP STACK LOCATIONS
        SectionHeader("IRP STACK LOCATIONS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Before sending an IRP, the I/O Manager pre-allocates an array of " +
            "IO_STACK_LOCATION structures inside the IRP — one slot for each device object " +
            "in the target stack. The count comes from the StackSize field of the topmost " +
            "device object.\n\n" +
            "Each driver accesses its own slot via IoGetCurrentIrpStackLocation(Irp). " +
            "The slot contains:\n\n" +
            "  MajorFunction / MinorFunction — the operation type\n" +
            "  Parameters union — operation-specific args (e.g. Read.Length, Read.ByteOffset)\n" +
            "  CompletionRoutine — callback registered by an upper driver\n" +
            "  Context — opaque pointer passed to the completion routine\n" +
            "  DeviceObject — the device this slot belongs to\n" +
            "  FileObject — the file object for the request"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // COMPLETING AN IRP
        SectionHeader("COMPLETING AN IRP")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "When a driver has finished processing an IRP, it fills in Irp->IoStatus.Status " +
            "and Irp->IoStatus.Information, then calls IoCompleteRequest()."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Irp->IoStatus.Status      = STATUS_SUCCESS;\n" +
            "Irp->IoStatus.Information = bytesTransferred;\n" +
            "IoCompleteRequest(Irp, IO_NO_INCREMENT);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "IoCompleteRequest walks back up the stack, invoking the CompletionRoutine in " +
            "each IO_STACK_LOCATION that has one registered. After all completion routines " +
            "run, the IRP is freed and the original caller is signaled."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // PASSING DOWN: WITH COMPLETION ROUTINE
        SectionHeader("PASSING DOWN: WITH COMPLETION ROUTINE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "When you want to be notified after a lower driver completes the IRP, copy your " +
            "stack slot's parameters to the next slot, register a completion routine, then " +
            "call the lower driver."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "IoCopyCurrentIrpStackLocationToNext(Irp);\n" +
            "IoSetCompletionRoutine(\n" +
            "    Irp,\n" +
            "    MyCompletionRoutine,  // NTSTATUS (*)(DEVICE_OBJECT*, IRP*, void*)\n" +
            "    context,              // passed to your routine\n" +
            "    TRUE,   // invoke on success\n" +
            "    TRUE,   // invoke on error\n" +
            "    TRUE    // invoke on cancel\n" +
            ");\n" +
            "return IoCallDriver(lowerDevice, Irp);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "IoCopyCurrentIrpStackLocationToNext copies the current slot to the next lower " +
            "slot in the IRP so the lower driver receives the correct parameters. Your " +
            "completion routine is called on the way back up."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // PASSING DOWN: WITHOUT COMPLETION ROUTINE
        SectionHeader("PASSING DOWN: WITHOUT COMPLETION ROUTINE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "When you have nothing to do on completion — just forwarding the IRP as-is — " +
            "use the skip variant instead. This avoids the copy entirely."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "IoSkipCurrentIrpStackLocation(Irp);\n" +
            "return IoCallDriver(lowerDevice, Irp);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "IoSkipCurrentIrpStackLocation simply decrements the IRP's current stack " +
            "position pointer. The next driver then \"sees\" and reuses your slot — " +
            "you are effectively giving it up. No copy overhead."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // COPY vs SKIP
        SectionHeader("COPY vs SKIP")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Use COPY when you register a completion routine. Your slot must remain valid " +
            "at the stack position that will be restored when the IRP completes back up " +
            "to you — the copy ensures the lower driver uses its own slot for its parameters.\n\n" +
            "Use SKIP when just forwarding with no completion routine. Your slot is " +
            "\"consumed\" by the lower driver, saving the copy cost.\n\n" +
            "NEVER call IoSkipCurrentIrpStackLocation and then set a completion routine. " +
            "The skip moves the pointer down, so IoSetCompletionRoutine would write into " +
            "the slot belonging to the driver below you, corrupting its state."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // KEY IRP FIELDS
        SectionHeader("KEY IRP FIELDS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "IoStatus.Status      — final NTSTATUS code\n" +
            "IoStatus.Information — bytes transferred, or other operation-specific value\n\n" +
            "MdlAddress           — MDL describing the user buffer (for DO_DIRECT_IO)\n" +
            "AssociatedIrp.SystemBuffer — kernel buffer (for DO_BUFFERED_IO)\n\n" +
            "Flags                — IRP_BUFFERED_IO, IRP_INPUT_OPERATION, IRP_DEALLOCATE_BUFFER\n" +
            "PendingReturned      — set if a driver returned STATUS_PENDING for this IRP\n" +
            "Cancel               — set TRUE when IRP cancellation is requested\n" +
            "CancelRoutine        — driver-registered routine called if IRP is cancelled"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // CANCEL ROUTINE
        SectionHeader("CANCEL ROUTINE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "A driver that holds a pending IRP (e.g. in a queue) must register a cancel " +
            "routine so the IRP can be aborted if the application closes its handle or " +
            "calls CancelIo. Without one, the I/O cannot be cancelled at all.\n\n" +
            "The cancel spin lock — a global kernel spin lock — serializes access to the " +
            "cancel routine pointer inside every IRP. It must be held when setting a cancel " +
            "routine to prevent a race where the IRP is cancelled on another CPU in the " +
            "instant between your decision to queue it and the moment the routine is written:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "KIRQL oldIrql;\n" +
            "IoAcquireCancelSpinLock(&oldIrql);\n" +
            "IoSetCancelRoutine(Irp, MyCancelRoutine); // register under lock\n" +
            "// enqueue the IRP here, still inside the lock\n" +
            "InsertTailList(&deviceExtension->PendingQueue, &Irp->Tail.Overlay.ListEntry);\n" +
            "IoReleaseCancelSpinLock(oldIrql);\n" +
            "IoMarkIrpPending(Irp);\n" +
            "return STATUS_PENDING;"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "When completing an IRP that has a cancel routine, you must disable the routine " +
            "first by clearing it atomically under the lock. IoSetCancelRoutine returns the " +
            "OLD pointer — its value tells you whether you own the IRP:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "IoAcquireCancelSpinLock(&oldIrql);\n" +
            "PDRIVER_CANCEL old = IoSetCancelRoutine(Irp, NULL); // clear under lock\n" +
            "IoReleaseCancelSpinLock(oldIrql);\n\n" +
            "if (old == NULL) {\n" +
            "    // NULL return means the cancel routine is currently running on\n" +
            "    // another CPU — it will complete the IRP. Do NOT touch it.\n" +
            "    return;\n" +
            "}\n\n" +
            "// We cleared the routine — we own the IRP.\n" +
            "// Check if it was already marked for cancellation.\n" +
            "if (Irp->Cancel) {\n" +
            "    Irp->IoStatus.Status      = STATUS_CANCELLED;\n" +
            "    Irp->IoStatus.Information = 0;\n" +
            "} else {\n" +
            "    Irp->IoStatus.Status      = STATUS_SUCCESS;\n" +
            "    Irp->IoStatus.Information = bytesTransferred;\n" +
            "}\n" +
            "IoCompleteRequest(Irp, IO_NO_INCREMENT);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Irp->Cancel is set to TRUE by the I/O Manager before it calls the cancel " +
            "routine, or when the IRP was cancelled but the routine hadn't run yet. Always " +
            "check it after you clear the routine — even if you got here first, the caller " +
            "may have already cancelled the request.\n\n" +
            "The cancel routine is called by the I/O Manager with the cancel spin lock " +
            "already held. It MUST release the lock before completing or returning:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "VOID MyCancelRoutine(\n" +
            "    PDEVICE_OBJECT DeviceObject,\n" +
            "    PIRP           Irp)\n" +
            "{\n" +
            "    // Cancel spin lock is held on entry — release it first.\n" +
            "    IoReleaseCancelSpinLock(Irp->CancelIrql);\n\n" +
            "    // Remove IRP from your driver's pending queue.\n" +
            "    RemoveEntryList(&Irp->Tail.Overlay.ListEntry);\n\n" +
            "    Irp->IoStatus.Status      = STATUS_CANCELLED;\n" +
            "    Irp->IoStatus.Information = 0;\n" +
            "    IoCompleteRequest(Irp, IO_NO_INCREMENT);\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // IRP LIFETIME
        SectionHeader("IRP LIFETIME")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "IRPs are NOT kernel objects — they have no Object Manager reference count. " +
            "ObReferenceObject / ObDereferenceObject do not apply.\n\n" +
            "The I/O Manager manages IRP lifetime through the stack location mechanism:\n\n" +
            "  • An IRP created by the I/O Manager is freed automatically by " +
            "IoCompleteRequest after all completion routines have run and none returned " +
            "STATUS_MORE_PROCESSING_REQUIRED.\n\n" +
            "  • An IRP allocated by a driver via IoAllocateIrp must be freed explicitly " +
            "with IoFreeIrp if it is never sent via IoCallDriver.\n\n" +
            "To hold an IRP across an asynchronous operation from a completion routine, " +
            "return STATUS_MORE_PROCESSING_REQUIRED. This halts the upward traversal and " +
            "prevents the IRP from being freed. The driver must later restart completion " +
            "by calling IoCompleteRequest a second time, or free it with IoFreeIrp.\n\n" +
            "To hold an IRP at the dispatch level without a completion routine, call " +
            "IoMarkIrpPending and return STATUS_PENDING from the dispatch function. The IRP " +
            "stays alive until some thread, DPC, or work item eventually calls " +
            "IoCompleteRequest on it."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
