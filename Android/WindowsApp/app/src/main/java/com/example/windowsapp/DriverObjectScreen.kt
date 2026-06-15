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
fun DriverObjectScreen(navController: NavController) {
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
            text = "DRIVER_OBJECT",
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
            "_DRIVER_OBJECT is a kernel object representing a loaded driver. " +
            "The I/O Manager creates one when a driver is loaded and passes a pointer to it " +
            "as the first argument of DriverEntry."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // CREATION
        SectionHeader("CREATION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Created when the driver image is loaded — either at boot time (drivers listed " +
            "in the registry with an appropriate Start value: SERVICE_BOOT_START, " +
            "SERVICE_SYSTEM_START, SERVICE_AUTO_START) or dynamically via NtLoadDriver / " +
            "SCM StartService. DriverEntry receives the pointer before any device objects exist."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // REFERENCE COUNTING
        SectionHeader("REFERENCE COUNTING")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Yes. DRIVER_OBJECT is an Executive Object managed by the Object Manager and " +
            "is reference-counted. The I/O Manager holds a reference while the driver is " +
            "loaded. You can acquire your own reference with ObReferenceObject and release " +
            "with ObDereferenceObject."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // KEY FIELDS
        SectionHeader("KEY FIELDS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "DriverStart / DriverSize — base VA and size of the driver image in kernel space\n\n" +
            "DriverName — UNICODE_STRING, e.g. \\Driver\\MyDriver\n\n" +
            "HardwareDatabase — path to the driver's registry Services key\n\n" +
            "DeviceObject — head of the linked list of DEVICE_OBJECTs created by this driver\n\n" +
            "DriverUnload — function pointer for cleanup on unload\n\n" +
            "DriverExtension — pointer to IO_CLIENT_EXTENSION (driver-level context storage)\n\n" +
            "MajorFunction[28] — the dispatch table"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // DISPATCH TABLE
        SectionHeader("DISPATCH TABLE")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("PDRIVER_DISPATCH MajorFunction[IRP_MJ_MAXIMUM_FUNCTION + 1];")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "IRP_MJ_CREATE           (0x00) — CreateFile, open a handle\n" +
            "IRP_MJ_CREATE_NAMED_PIPE(0x01) — named pipe creation (filesystem)\n" +
            "IRP_MJ_CLOSE            (0x02) — CloseHandle, last handle closed\n" +
            "IRP_MJ_READ             (0x03) — ReadFile\n" +
            "IRP_MJ_WRITE            (0x04) — WriteFile\n" +
            "IRP_MJ_QUERY_INFORMATION(0x05) — GetFileInformationByHandle\n" +
            "IRP_MJ_SET_INFORMATION  (0x06) — SetFileInformationByHandle\n" +
            "IRP_MJ_DEVICE_CONTROL   (0x0E) — DeviceIoControl (IOCTLs)\n" +
            "IRP_MJ_INTERNAL_DEVICE_CONTROL (0x0F) — kernel-internal IOCTLs\n" +
            "IRP_MJ_POWER            (0x16) — power management (Sx/Dx transitions)\n" +
            "IRP_MJ_SYSTEM_CONTROL   (0x17) — WMI queries\n" +
            "IRP_MJ_PNP              (0x1B) — Plug and Play (AddDevice, StartDevice, etc.)\n\n" +
            "Unhandled major functions point to IopInvalidDeviceRequest by default, " +
            "which completes the IRP with STATUS_INVALID_DEVICE_REQUEST."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // DRIVER UNLOAD
        SectionHeader("DRIVER UNLOAD")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The DriverUnload field is of type PDRIVER_UNLOAD:\n\n" +
            "  VOID (*DriverUnload)(PDRIVER_OBJECT DriverObject);\n\n" +
            "If this field is NULL, NtUnloadDriver fails and the SCM cannot stop the " +
            "driver — it remains loaded until the next reboot. Always implement DriverUnload " +
            "to perform cleanup: delete device objects (IoDeleteDevice), detach from device " +
            "stacks (IoDetachDevice), deregister callbacks and symbolic links, and free any " +
            "pool allocations."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // DEVICE LIST
        SectionHeader("DEVICE LIST")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The DeviceObject field is the head of a singly-linked list of all DEVICE_OBJECTs " +
            "created by this driver. Each DEVICE_OBJECT has a NextDevice field that points to " +
            "the next entry in the list. The list is maintained by the I/O Manager — " +
            "IoCreateDevice appends to it, IoDeleteDevice removes from it."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ADD DEVICE
        SectionHeader("ADDDEVICE — PNP ENTRY POINT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "AddDevice is the PnP callback the I/O Manager calls when the PnP Manager matches " +
            "a newly detected device to this driver. It is NOT a field directly on DRIVER_OBJECT " +
            "— it lives in the DriverExtension:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// In DriverEntry, register the AddDevice callback:\n" +
            "DriverObject->DriverExtension->AddDevice = MyAddDevice;\n\n" +
            "// Prototype:\n" +
            "NTSTATUS MyAddDevice(\n" +
            "    PDRIVER_OBJECT DriverObject,\n" +
            "    PDEVICE_OBJECT PhysicalDeviceObject  // PDO from bus driver\n" +
            ");"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The PDO (Physical Device Object) passed in is already created by the bus driver " +
            "(PCI, USB hub, ACPI, etc.) — it represents the physical hardware. In AddDevice " +
            "the driver typically:\n\n" +
            "  1. Calls IoCreateDevice to create a Function Device Object (FDO)\n" +
            "  2. Calls IoAttachDeviceToDeviceStack(FDO, PDO) to join the device stack;\n" +
            "     this returns a pointer to the device just below (lower device)\n" +
            "  3. Stores the lower device pointer in the device extension for later use\n" +
            "  4. Propagates StackSize: FDO->StackSize = lowerDevice->StackSize + 1\n" +
            "  5. Clears DO_DEVICE_INITIALIZING on the FDO\n" +
            "  6. Returns STATUS_SUCCESS\n\n" +
            "Non-PnP (legacy) drivers do NOT use AddDevice. They create their devices " +
            "directly in DriverEntry and never receive PnP IRPs."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // FAST IO DISPATCH
        SectionHeader("FAST I/O DISPATCH")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "DRIVER_OBJECT has a FastIoDispatch field pointing to a FAST_IO_DISPATCH structure. " +
            "Fast I/O is an optimization path used almost exclusively by file system drivers " +
            "and file system filter drivers.\n\n" +
            "Normally, every I/O request creates an IRP and routes it through the device stack — " +
            "this carries measurable overhead. Fast I/O bypasses IRP creation entirely: the " +
            "I/O Manager calls the driver's fast I/O routine directly. If the routine can " +
            "satisfy the request from cache without involving the disk (e.g. a cached file " +
            "read), it does so and returns TRUE. If not, it returns FALSE and the I/O Manager " +
            "falls back to the normal IRP path."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "typedef struct _FAST_IO_DISPATCH {\n" +
            "    ULONG SizeOfFastIoDispatch;\n" +
            "    PFAST_IO_CHECK_IF_POSSIBLE  FastIoCheckIfPossible;\n" +
            "    PFAST_IO_READ               FastIoRead;\n" +
            "    PFAST_IO_WRITE              FastIoWrite;\n" +
            "    PFAST_IO_QUERY_BASIC_INFO   FastIoQueryBasicInfo;\n" +
            "    PFAST_IO_QUERY_STANDARD_INFO FastIoQueryStandardInfo;\n" +
            "    PFAST_IO_LOCK               FastIoLock;\n" +
            "    PFAST_IO_UNLOCK_SINGLE      FastIoUnlockSingle;\n" +
            "    PFAST_IO_UNLOCK_ALL         FastIoUnlockAll;\n" +
            "    PFAST_IO_DEVICE_CONTROL     FastIoDeviceControl;\n" +
            "    PFAST_IO_DETACH_DEVICE      FastIoDetachDevice;\n" +
            "    PFAST_IO_QUERY_NETWORK_OPEN_INFO FastIoQueryNetworkOpenInfo;\n" +
            "    PFAST_IO_MDL_READ           MdlRead;\n" +
            "    PFAST_IO_MDL_READ_COMPLETE  MdlReadComplete;\n" +
            "    PFAST_IO_PREPARE_MDL_WRITE  PrepareMdlWrite;\n" +
            "    PFAST_IO_MDL_WRITE_COMPLETE MdlWriteComplete;\n" +
            "    PFAST_IO_READ_COMPRESSED    FastIoReadCompressed;\n" +
            "    PFAST_IO_WRITE_COMPRESSED   FastIoWriteCompressed;\n" +
            "    PFAST_IO_QUERY_OPEN         FastIoQueryOpen;\n" +
            "    // ...\n" +
            "} FAST_IO_DISPATCH;"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "For a regular device driver (keyboard, USB, network adapter), FastIoDispatch " +
            "is set to NULL — fast I/O is irrelevant. It is only meaningful for file system " +
            "drivers (NTFS, FAT, ReFS) and file system filter drivers (legacy filters, " +
            "antivirus file monitors) that interact with the Cache Manager."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
