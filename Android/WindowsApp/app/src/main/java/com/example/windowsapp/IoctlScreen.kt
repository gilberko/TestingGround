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
fun IoctlScreen(navController: NavController) {
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
            text = "IOCTL",
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

        // WHAT IS AN IOCTL
        SectionHeader("WHAT IS AN IOCTL")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "An IOCTL (I/O Control Code) is a custom control operation sent to a driver via " +
            "DeviceIoControl in user mode. It lets applications request driver-specific " +
            "operations that don't fit the standard read/write model — querying device " +
            "status, changing configuration, triggering hardware actions, etc.\n\n" +
            "The driver handles it in its IRP_MJ_DEVICE_CONTROL dispatch routine."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // DEFINING AN IOCTL
        SectionHeader("DEFINING AN IOCTL — CTL_CODE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "IOCTL codes are 32-bit values built with the CTL_CODE macro:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "#define CTL_CODE(DeviceType, Function, Method, Access) (\\\n" +
            "    ((DeviceType) << 16) | ((Access) << 14) |           \\\n" +
            "    ((Function)   <<  2) |  (Method)         )\n\n" +
            "// Example: custom IOCTL for a private device\n" +
            "#define IOCTL_MY_DO_WORK  CTL_CODE(\n" +
            "    FILE_DEVICE_UNKNOWN,  // DeviceType (0x22)\n" +
            "    0x800,                // Function: 0x000-0x7FF = Microsoft reserved\n" +
            "                         //           0x800-0xFFF = vendor defined\n" +
            "    METHOD_BUFFERED,      // how data is transferred\n" +
            "    FILE_ANY_ACCESS       // required handle access\n" +
            ")"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Bit layout of the 32-bit code:\n" +
            "  [31:16] DeviceType  — FILE_DEVICE_xxx or custom 0x8000+\n" +
            "  [15:14] Access      — FILE_ANY_ACCESS, FILE_READ_DATA, FILE_WRITE_DATA\n" +
            "  [13: 2] Function    — operation number within this device type\n" +
            "  [ 1: 0] Method      — transfer method (see below)"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // METHOD_BUFFERED
        SectionHeader("METHOD_BUFFERED")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The simplest method. The I/O Manager handles all copying between user and kernel " +
            "space automatically.\n\n" +
            "On IRP creation, the I/O Manager allocates a single SystemBuffer from " +
            "non-paged pool, sized to max(InputBufferLength, OutputBufferLength), and copies " +
            "the user's input data into it. The driver reads input from and writes output to " +
            "this same buffer. On IRP completion, the I/O Manager copies SystemBuffer back " +
            "to the user's output buffer."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "PIO_STACK_LOCATION stack = IoGetCurrentIrpStackLocation(Irp);\n\n" +
            "// Access the shared kernel buffer\n" +
            "PVOID  buf     = Irp->AssociatedIrp.SystemBuffer;\n" +
            "ULONG  inLen   = stack->Parameters.DeviceIoControl.InputBufferLength;\n" +
            "ULONG  outLen  = stack->Parameters.DeviceIoControl.OutputBufferLength;\n\n" +
            "// Read input (first inLen bytes of buf)\n" +
            "MY_INPUT_STRUCT* in = (MY_INPUT_STRUCT*)buf;\n\n" +
            "// Write output to the same buffer\n" +
            "MY_OUTPUT_STRUCT* out = (MY_OUTPUT_STRUCT*)buf;\n" +
            "out->result = ...;\n\n" +
            "Irp->IoStatus.Information = sizeof(MY_OUTPUT_STRUCT);\n" +
            "Irp->IoStatus.Status = STATUS_SUCCESS;\n" +
            "IoCompleteRequest(Irp, IO_NO_INCREMENT);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "SystemBuffer is guaranteed to be in non-paged pool, so it is safe to access " +
            "at any IRQL including DISPATCH_LEVEL. Read all input data BEFORE writing " +
            "output — they share the same buffer.\n\n" +
            "Irp->IoStatus.Information must be set to the number of bytes of valid output " +
            "written to SystemBuffer; the I/O Manager copies exactly that many bytes to the " +
            "user's output buffer."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // METHOD_IN_DIRECT / METHOD_OUT_DIRECT
        SectionHeader("METHOD_IN_DIRECT / METHOD_OUT_DIRECT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Both methods avoid copying large output buffers by locking the user's output " +
            "buffer in place with an MDL instead of allocating a system buffer for it.\n\n" +
            "Input: still copied into AssociatedIrp.SystemBuffer (non-paged, safe).\n" +
            "Output: Irp->MdlAddress — an MDL describing the user's output buffer, locked " +
            "in physical memory by the I/O Manager.\n\n" +
            "The difference:\n" +
            "  METHOD_IN_DIRECT  — output MDL is verified for WRITE access\n" +
            "                      (driver writes results directly to user's buffer)\n" +
            "  METHOD_OUT_DIRECT — output MDL is verified for READ access\n" +
            "                      (used when driver reads data FROM the output buffer,\n" +
            "                      e.g. for DMA where the output buffer is a DMA target)"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Input (same as METHOD_BUFFERED)\n" +
            "MY_INPUT_STRUCT* in =\n" +
            "    (MY_INPUT_STRUCT*)Irp->AssociatedIrp.SystemBuffer;\n\n" +
            "// Output — map the locked user pages into kernel address space\n" +
            "PVOID outBuf = MmGetSystemAddressForMdlSafe(\n" +
            "    Irp->MdlAddress, NormalPagePriority);\n" +
            "ULONG outLen =\n" +
            "    MmGetMdlByteCount(Irp->MdlAddress);\n\n" +
            "// Write directly to outBuf (it maps to user's locked physical pages)\n" +
            "RtlCopyMemory(outBuf, &result, sizeof(result));\n\n" +
            "Irp->IoStatus.Information = sizeof(result);\n" +
            "Irp->IoStatus.Status = STATUS_SUCCESS;\n" +
            "IoCompleteRequest(Irp, IO_NO_INCREMENT);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "outBuf is a kernel virtual address mapping the same physical pages as the " +
            "user's buffer. Writing to it writes directly to user memory, with no copy " +
            "on completion. Suitable for large data transfers."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // METHOD_NEITHER
        SectionHeader("METHOD_NEITHER")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The I/O Manager does nothing — no system buffer, no MDL. The driver receives " +
            "the raw user-mode virtual addresses directly:\n\n" +
            "  Input:  stack->Parameters.DeviceIoControl.Type3InputBuffer\n" +
            "  Output: Irp->UserBuffer\n\n" +
            "These are user-mode virtual addresses and are ONLY valid while the requesting " +
            "process is the current process (i.e. at PASSIVE_LEVEL in the dispatch routine, " +
            "in the context of the caller's thread). You CANNOT save these pointers for use " +
            "in a DPC, work item, or any other deferred context."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "PVOID userIn  = stack->Parameters.DeviceIoControl.Type3InputBuffer;\n" +
            "PVOID userOut = Irp->UserBuffer;\n" +
            "ULONG inLen   = stack->Parameters.DeviceIoControl.InputBufferLength;\n" +
            "ULONG outLen  = stack->Parameters.DeviceIoControl.OutputBufferLength;\n\n" +
            "__try {\n" +
            "    // Verify the caller actually has access to these addresses.\n" +
            "    ProbeForRead (userIn,  inLen,  sizeof(UCHAR));\n" +
            "    ProbeForWrite(userOut, outLen, sizeof(UCHAR));\n\n" +
            "    // Safe to access within this try block.\n" +
            "    RtlCopyMemory(&localCopy, userIn, inLen);\n" +
            "    // ... process and write output ...\n" +
            "    RtlCopyMemory(userOut, &result, sizeof(result));\n\n" +
            "} __except(EXCEPTION_EXECUTE_HANDLER) {\n" +
            "    status = GetExceptionCode();\n" +
            "}"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Problems with METHOD_NEITHER:\n\n" +
            "  • User memory can be paged out between the probe and the access — the " +
            "try/except catches the resulting exception but you lose the operation.\n\n" +
            "  • A malicious caller can change the memory contents between your probe " +
            "and your actual read (time-of-check to time-of-use / TOCTOU attack). Always " +
            "copy input into a kernel buffer first, then operate on the copy.\n\n" +
            "  • Cannot be used at any IRQL above PASSIVE_LEVEL — paged memory access " +
            "at DISPATCH_LEVEL causes an immediate BSOD.\n\n" +
            "Prefer METHOD_BUFFERED for simplicity and safety. Use METHOD_IN_DIRECT or " +
            "METHOD_OUT_DIRECT only when transfer size makes copying prohibitive. Avoid " +
            "METHOD_NEITHER unless you have a compelling reason and understand the risks."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
