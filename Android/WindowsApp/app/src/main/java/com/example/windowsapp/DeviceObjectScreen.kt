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
fun DeviceObjectScreen(navController: NavController) {
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
            text = "DEVICE_OBJECT",
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
            "_DEVICE_OBJECT represents a logical or physical device in the system. " +
            "Drivers create device objects to expose their functionality to the I/O subsystem " +
            "and to participate in a device stack."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // CREATION
        SectionHeader("CREATION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Device objects are created by calling IoCreateDevice() (or IoCreateDeviceSecure " +
            "for named devices with security). The driver calls this from DriverEntry for " +
            "non-PnP drivers, or from its AddDevice callback for PnP drivers."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "NTSTATUS IoCreateDevice(\n" +
            "    PDRIVER_OBJECT  DriverObject,\n" +
            "    ULONG           DeviceExtensionSize,\n" +
            "    PUNICODE_STRING DeviceName,       // optional\n" +
            "    DEVICE_TYPE     DeviceType,\n" +
            "    ULONG           DeviceCharacteristics,\n" +
            "    BOOLEAN         Exclusive,\n" +
            "    PDEVICE_OBJECT  *DeviceObject     // output\n" +
            ");"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // REFERENCE COUNTING
        SectionHeader("REFERENCE COUNTING")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Yes. DEVICE_OBJECT is reference-counted by the Object Manager. The I/O Manager " +
            "holds references while IRPs are in flight against the device. If you store a " +
            "pointer to a DEVICE_OBJECT you did not create, take your own reference with " +
            "ObReferenceObject and release it with ObDereferenceObject when done."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // POINTER TO DRIVER
        SectionHeader("POINTER TO DRIVER_OBJECT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Yes. The DriverObject field is a back-pointer to the DRIVER_OBJECT that owns " +
            "this device object. Every DEVICE_OBJECT belongs to exactly one DRIVER_OBJECT."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // DEVICE EXTENSION
        SectionHeader("DEVICE EXTENSION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The DeviceExtension field points to a contiguous block of non-paged pool memory " +
            "allocated by the I/O Manager at IoCreateDevice time. Its size is the " +
            "DeviceExtensionSize parameter you passed in.\n\n" +
            "The layout of this memory is entirely driver-defined — the kernel treats it as " +
            "opaque. This is where drivers store per-device context: spinlocks, event objects, " +
            "state flags, DMA adapters, interface registration strings, worker thread handles, " +
            "and pointers to related objects.\n\n" +
            "Typically you define a struct and cast:\n"
        )
        CodeBlock(
            "typedef struct _DEVICE_EXTENSION {\n" +
            "    PDEVICE_OBJECT  Self;\n" +
            "    PDEVICE_OBJECT  LowerDevice;\n" +
            "    KSPIN_LOCK      Lock;\n" +
            "    BOOLEAN         Removing;\n" +
            "} DEVICE_EXTENSION, *PDEVICE_EXTENSION;\n\n" +
            "PDEVICE_EXTENSION ext =\n" +
            "    (PDEVICE_EXTENSION)DeviceObject->DeviceExtension;"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // OTHER KEY FIELDS
        SectionHeader("OTHER KEY FIELDS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "DeviceType — FILE_DEVICE_xxx constant (e.g. FILE_DEVICE_UNKNOWN, " +
            "FILE_DEVICE_NETWORK)\n\n" +
            "Flags — bitmask controlling device behavior (see DEVICE FLAGS below)\n\n" +
            "StackSize — number of IO_STACK_LOCATION slots in IRPs sent to this device. " +
            "When attaching on top of another device, set StackSize to lower->StackSize + 1.\n\n" +
            "AttachedDevice — the device object attached directly above this one in the stack\n\n" +
            "NextDevice — next entry in the owning DRIVER_OBJECT's device list\n\n" +
            "Characteristics — FILE_DEVICE_SECURE_OPEN and similar flags\n\n" +
            "AlignmentRequirement — required buffer alignment for DMA transfers"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // DEVICE FLAGS
        SectionHeader("DEVICE FLAGS IN DETAIL")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The Flags field is a bitmask. Key flags and their exact values:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "#define DO_BUFFERED_IO          0x00000004\n" +
            "#define DO_DIRECT_IO            0x00000010\n" +
            "#define DO_DEVICE_INITIALIZING  0x00000080\n" +
            "#define DO_POWER_PAGABLE        0x00002000\n" +
            "#define DO_POWER_INRUSH         0x00004000"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "DO_BUFFERED_IO\n" +
            "For IRP_MJ_READ and IRP_MJ_WRITE, the I/O Manager copies the user's buffer " +
            "to/from a kernel system buffer (AssociatedIrp.SystemBuffer) in non-paged pool. " +
            "Safe at any IRQL. Best for small transfers where copy overhead is acceptable.\n\n" +
            "DO_DIRECT_IO\n" +
            "For IRP_MJ_READ and IRP_MJ_WRITE, the I/O Manager locks the user's buffer " +
            "in physical memory and stores an MDL in Irp->MdlAddress. The driver calls " +
            "MmGetSystemAddressForMdlSafe to access the locked pages with no copy on " +
            "completion. Best for large transfers (DMA, high-throughput devices).\n\n" +
            "If neither flag is set, the driver receives the raw user-mode VA in " +
            "AssociatedIrp.UserBuffer — only valid at PASSIVE_LEVEL in the caller's " +
            "process context. Rarely used.\n\n" +
            "DO_DEVICE_INITIALIZING\n" +
            "Set automatically by IoCreateDevice. While this flag is set, the device cannot " +
            "be opened — CreateFile against it returns an error. The driver MUST clear it " +
            "before returning from DriverEntry (legacy) or AddDevice (PnP):"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("DeviceObject->Flags &= ~DO_DEVICE_INITIALIZING;  // required!")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Forgetting to clear this flag leaves the device permanently inaccessible.\n\n" +
            "DO_POWER_PAGABLE\n" +
            "Tells the power manager that this device's IRP_MJ_POWER dispatch routine can " +
            "be paged. Set this for most devices — the power manager ensures the routine is " +
            "resident before calling it.\n\n" +
            "Do NOT set it if your device is in the paging I/O path (disk drivers, storage " +
            "filters above the page file). If the routine gets paged out during a sleep " +
            "transition before the page file is accessible, the result is a deadlock or BSOD.\n\n" +
            "DO_POWER_INRUSH\n" +
            "Indicates the device draws a large inrush current surge when powered on — " +
            "typically capacitor-heavy hardware. The power manager serializes power-up of " +
            "inrush devices: only one powers on at a time, preventing simultaneous surges " +
            "from tripping a circuit breaker.\n\n" +
            "DO_POWER_INRUSH and DO_POWER_PAGABLE are mutually exclusive."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
