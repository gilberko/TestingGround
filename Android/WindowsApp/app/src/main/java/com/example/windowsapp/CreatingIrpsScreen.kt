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
fun CreatingIrpsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "CREATING AND SENDING IRPs",
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

        SectionHeader("WHY CREATE YOUR OWN IRP")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The I/O Manager normally creates IRPs on behalf of user-mode callers (ReadFile, WriteFile, DeviceIoControl). But a driver can also create IRPs itself and send them directly to any other driver.\n\n" +
            "Common uses: a filter driver communicating with the device it is attached to, a driver sending a control request to a peer driver identified by its symbolic link, or an upper driver initiating I/O without going through user-mode."
        )
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("GETTING THE TARGET DEVICE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "IoGetDeviceObjectPointer opens the named device and returns both a PFILE_OBJECT and a PDEVICE_OBJECT. The file object keeps a reference on the device object alive — it is not safe to use the device object after dereferencing the file object.\n\n" +
            "Use the NT device path (e.g. \\Device\\MyDriver), not the Win32 form (\\\\.\\MyDriver).\n\n" +
            "Call ObDereferenceObject(FileObject) when done with the device. This releases the reference on the device object too."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
UNICODE_STRING devName;
RtlInitUnicodeString(&devName, L"\\Device\\TargetDriver");

PFILE_OBJECT   fileObj;
PDEVICE_OBJECT devObj;
NTSTATUS status = IoGetDeviceObjectPointer(
    &devName, FILE_READ_DATA, &fileObj, &devObj);
if (!NT_SUCCESS(status)) return status;

// ... use devObj to send IRPs ...

ObDereferenceObject(fileObj); // also releases devObj reference
        """.trimIndent())
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ALLOCATING THE IRP")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "IoAllocateIrp allocates memory from NonPagedPool.\n\n" +
            "IRPs must NOT come from PagedPool. An IRP can be touched at DISPATCH_LEVEL — by a DPC that completes it, by an ISR, or by the I/O Manager's completion path. Paged memory is inaccessible at DISPATCH_LEVEL, so any IRP on paged memory would cause a bugcheck.\n\n" +
            "Pass DeviceObject->StackSize as the first argument so the IRP has enough stack locations for the entire driver stack. The second argument (ChargeQuota) is typically FALSE.\n\n" +
            "IRPs have no reference count. Whoever decides to free the IRP calls IoFreeIrp — there is no reference counting mechanism."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
PIRP irp = IoAllocateIrp(devObj->StackSize, FALSE);
if (!irp) {
    ObDereferenceObject(fileObj);
    return STATUS_INSUFFICIENT_RESOURCES;
}
// Set a default status in case the target doesn't fill it
irp->IoStatus.Status = STATUS_NOT_SUPPORTED;
        """.trimIndent())
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SETTING UP THE STACK LOCATION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "IoGetNextIrpStackLocation returns a pointer to the next stack location — the one the target driver will see. Fill in MajorFunction, any relevant Parameters fields, and (for METHOD_BUFFERED) set AssociatedIrp.SystemBuffer to the input buffer."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
PIO_STACK_LOCATION stack = IoGetNextIrpStackLocation(irp);
stack->MajorFunction = IRP_MJ_DEVICE_CONTROL;
stack->Parameters.DeviceIoControl.IoControlCode    = MY_IOCTL_CODE;
stack->Parameters.DeviceIoControl.InputBufferLength  = sizeof(MY_INPUT);
stack->Parameters.DeviceIoControl.OutputBufferLength = sizeof(MY_OUTPUT);

// For METHOD_BUFFERED: kernel allocates a system buffer,
// put input data there
irp->AssociatedIrp.SystemBuffer = &myInputData;
        """.trimIndent())
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("COMPLETION ROUTINE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Set a completion routine with IoSetCompletionRoutine. In the completion routine, signal a KEVENT and return STATUS_MORE_PROCESSING_REQUIRED.\n\n" +
            "STATUS_MORE_PROCESSING_REQUIRED tells the I/O Manager to stop processing completions and not free the IRP. This gives control back to the thread that called IoCallDriver, which then inspects Irp->IoStatus and calls IoFreeIrp itself.\n\n" +
            "If you do NOT return STATUS_MORE_PROCESSING_REQUIRED (and do NOT set a completion routine), the I/O Manager frees the IRP automatically after all completion routines run. In that case you must not touch the IRP after calling IoCallDriver."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
NTSTATUS MyCompletion(PDEVICE_OBJECT DevObj,
                      PIRP Irp, PVOID Context) {
    PKEVENT event = (PKEVENT)Context;
    KeSetEvent(event, IO_NO_INCREMENT, FALSE);
    // Stop I/O Manager from freeing the IRP
    return STATUS_MORE_PROCESSING_REQUIRED;
}

KEVENT completionEvent;
KeInitializeEvent(&completionEvent, NotificationEvent, FALSE);
IoSetCompletionRoutine(irp, MyCompletion, &completionEvent,
                       TRUE, TRUE, TRUE);
        """.trimIndent())
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("CALLING AND WAITING")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "IoCallDriver passes the IRP to the target driver's dispatch routine. It may return STATUS_PENDING if the driver completes it asynchronously.\n\n" +
            "Wait on the event to know when completion is done, then read Irp->IoStatus.Status for the result."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
IoCallDriver(devObj, irp);
KeWaitForSingleObject(&completionEvent, Executive,
                      KernelMode, FALSE, NULL);

status = irp->IoStatus.Status;
        """.trimIndent())
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHO FREES THE IRP")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Because the completion routine returned STATUS_MORE_PROCESSING_REQUIRED, the I/O Manager did not free the IRP. The caller must call IoFreeIrp explicitly after reading the results.\n\n" +
            "Then dereference the file object. This releases the reference on the device object as well."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
IoFreeIrp(irp);
ObDereferenceObject(fileObj); // releases devObj reference too
        """.trimIndent())
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("FULL EXAMPLE")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
NTSTATUS SendIoctlToDriver(VOID) {
    UNICODE_STRING devName;
    PFILE_OBJECT   fileObj;
    PDEVICE_OBJECT devObj;
    KEVENT         event;
    PIRP           irp;
    PIO_STACK_LOCATION stack;
    NTSTATUS       status;
    MY_INPUT       input = { 0 };

    RtlInitUnicodeString(&devName, L"\\Device\\TargetDriver");
    status = IoGetDeviceObjectPointer(&devName, FILE_READ_DATA,
                                      &fileObj, &devObj);
    if (!NT_SUCCESS(status)) return status;

    KeInitializeEvent(&event, NotificationEvent, FALSE);

    irp = IoAllocateIrp(devObj->StackSize, FALSE);
    if (!irp) {
        ObDereferenceObject(fileObj);
        return STATUS_INSUFFICIENT_RESOURCES;
    }

    irp->AssociatedIrp.SystemBuffer = &input;
    irp->IoStatus.Status = STATUS_NOT_SUPPORTED;

    stack = IoGetNextIrpStackLocation(irp);
    stack->MajorFunction = IRP_MJ_DEVICE_CONTROL;
    stack->Parameters.DeviceIoControl.IoControlCode =
        MY_IOCTL_CODE;
    stack->Parameters.DeviceIoControl.InputBufferLength =
        sizeof(MY_INPUT);
    stack->Parameters.DeviceIoControl.OutputBufferLength = 0;

    IoSetCompletionRoutine(irp, MyCompletion, &event,
                           TRUE, TRUE, TRUE);
    IoCallDriver(devObj, irp);
    KeWaitForSingleObject(&event, Executive,
                          KernelMode, FALSE, NULL);

    status = irp->IoStatus.Status;
    IoFreeIrp(irp);
    ObDereferenceObject(fileObj);
    return status;
}
        """.trimIndent())
        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
