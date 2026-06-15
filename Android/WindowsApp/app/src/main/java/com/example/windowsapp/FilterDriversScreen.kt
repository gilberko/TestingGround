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
fun FilterDriversScreen(navController: NavController) {
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
            text = "FILTER DRIVERS",
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

        SectionHeader("WHAT ARE FILTER DRIVERS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "A filter driver is a kernel driver that inserts itself into the device stack. " +
            "The device stack is the chain of DEVICE_OBJECT nodes that handles I/O for a device: " +
            "top is the topmost filter, middle is the function driver (FDO), bottom is the physical " +
            "device object (PDO) created by the bus driver.\n\n" +
            "Every IRP dispatched to the stack enters at the top and travels downward through each " +
            "driver in order. Upper filters sit above the FDO; lower filters sit between the PDO and FDO.\n\n" +
            "Common uses: antivirus, encryption, logging, policy enforcement, device emulation."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("THREE CHOICES FOR EACH IRP")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "When an IRP arrives at your filter driver dispatch routine, you have three options:\n\n" +
            "1. COMPLETE THE IRP — call IoCompleteRequest(Irp, IO_NO_INCREMENT) and return a status. " +
            "The IRP travels back up the stack via completion routines. Use this to block or synthesize a response.\n\n" +
            "2. PEND THE IRP — call IoMarkIrpPending(Irp), return STATUS_PENDING, and complete the IRP " +
            "later (e.g., from a DPC or worker thread). You own the IRP until you call IoCompleteRequest.\n\n" +
            "3. PASS DOWN — forward the IRP to the next-lower device in the stack via IoCallDriver. " +
            "This is the most common action for a pass-through filter."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("KNOWING THE NEXT DEVICE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "In your AddDevice routine, you call IoAttachDeviceToDeviceStack. " +
            "This attaches your filter device object above the existing top of the stack and " +
            "returns a pointer to the device that was previously at the top — " +
            "that is your lowerDevice. Store it in your device extension. " +
            "IoCallDriver(lowerDevice, Irp) sends the IRP to that device, and from there it " +
            "continues downward through the remaining drivers."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// In AddDevice:\n" +
            "PDEVICE_OBJECT filterDO;\n" +
            "IoCreateDevice(DriverObject, sizeof(MY_DEVICE_EXT),\n" +
            "               NULL, FILE_DEVICE_UNKNOWN,\n" +
            "               FILE_DEVICE_SECURE_OPEN, FALSE, &filterDO);\n\n" +
            "PDEVICE_OBJECT lowerDevice =\n" +
            "    IoAttachDeviceToDeviceStack(filterDO, PhysicalDeviceObject);\n\n" +
            "// Store lowerDevice in device extension:\n" +
            "MY_DEVICE_EXT* ext = filterDO->DeviceExtension;\n" +
            "ext->LowerDevice = lowerDevice;"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("PASSING AN IRP DOWN (NO MODIFICATION)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "IoSkipCurrentIrpStackLocation advances the stack pointer so the next driver " +
            "reuses the current IO_STACK_LOCATION instead of getting a blank one. " +
            "This is the fast pass-through path — no allocation, no copy."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "NTSTATUS MyDispatch(PDEVICE_OBJECT DevObj, PIRP Irp) {\n" +
            "    MY_DEVICE_EXT* ext = DevObj->DeviceExtension;\n" +
            "    IoSkipCurrentIrpStackLocation(Irp);\n" +
            "    return IoCallDriver(ext->LowerDevice, Irp);\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("PASSING DOWN WITH A COMPLETION ROUTINE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "If you need to inspect or modify the IRP after the lower drivers have processed it " +
            "(i.e., on the way back up), you register a completion routine before calling IoCallDriver.\n\n" +
            "IoCopyCurrentIrpStackLocationToNext copies the current stack location to the next " +
            "(unlike IoSkipCurrentIrpStackLocation which just reuses it). This lets you set " +
            "parameters for the next driver independently.\n\n" +
            "IoSetCompletionRoutine registers a callback that fires when the IRP completes. " +
            "The three TRUE flags mean: invoke on success, on error, and on cancel."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "NTSTATUS MyReadDispatch(PDEVICE_OBJECT DevObj, PIRP Irp) {\n" +
            "    MY_DEVICE_EXT* ext = DevObj->DeviceExtension;\n\n" +
            "    IoCopyCurrentIrpStackLocationToNext(Irp);\n" +
            "    IoSetCompletionRoutine(\n" +
            "        Irp,\n" +
            "        MyReadCompletion,  // completion routine\n" +
            "        NULL,              // context\n" +
            "        TRUE,              // invoke on success\n" +
            "        TRUE,              // invoke on error\n" +
            "        TRUE);             // invoke on cancel\n\n" +
            "    return IoCallDriver(ext->LowerDevice, Irp);\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("THE COMPLETION ROUTINE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The completion routine is called at DISPATCH_LEVEL or lower as the IRP travels " +
            "back up the stack after all lower drivers have finished.\n\n" +
            "It must return one of two values:\n" +
            "  STATUS_CONTINUE_COMPLETION — the IRP continues up the stack normally.\n" +
            "  STATUS_MORE_PROCESSING_REQUIRED — the IRP is held; you take ownership and " +
            "must call IoCompleteRequest yourself later (e.g., after doing async work).\n\n" +
            "You can inspect Irp->IoStatus.Status to see what the lower drivers returned. " +
            "You can modify the IRP's data or status before returning."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "NTSTATUS MyReadCompletion(\n" +
            "    PDEVICE_OBJECT DevObj,\n" +
            "    PIRP Irp,\n" +
            "    PVOID Context)\n" +
            "{\n" +
            "    if (Irp->PendingReturned)\n" +
            "        IoMarkIrpPending(Irp);\n\n" +
            "    // Inspect result from lower driver:\n" +
            "    NTSTATUS status = Irp->IoStatus.Status;\n\n" +
            "    // ... optional: modify buffer, log, etc. ...\n\n" +
            "    return STATUS_CONTINUE_COMPLETION;\n" +
            "    // or STATUS_MORE_PROCESSING_REQUIRED to hold the IRP\n" +
            "}"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Note: if the lower driver returned STATUS_PENDING and you call " +
            "IoMarkIrpPending(Irp) in the completion routine, you must propagate that " +
            "pending status upward — this is done by checking Irp->PendingReturned."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
