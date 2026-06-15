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
fun WdfKmdfScreen(navController: NavController) {
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
            text = "WDF / KMDF",
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

        SectionHeader("WHAT IS WDF")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Windows Driver Foundation (WDF) is a framework that sits on top of WDM (Windows Driver Model) and provides an object model, automatic IRP routing, a complete PnP/power state machine, and object lifetime management. WDF has two sub-frameworks: KMDF (Kernel-Mode Driver Framework) for kernel drivers, and UMDF (User-Mode Driver Framework) for user-mode drivers. Both share the same design philosophy and very similar APIs.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("WDF frees driver authors from manually implementing the dozens of IRP major and minor function handlers that every compliant WDM driver must handle correctly. Under WDF, the driver only implements the callbacks that are relevant to its hardware.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHEN DID KMDF BEGIN")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("KMDF 1.0 shipped with Windows Vista (2006) as part of the Windows Driver Kit. It was back-ported to Windows XP SP2 and Windows Server 2003 SP1 as a redistributable package, so drivers written with KMDF could run on older systems without requiring a new OS. The current version is KMDF 1.33, shipped with Windows 11.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The KMDF runtime lives in Wdf01000.sys — a kernel driver that loads automatically when any KMDF driver is present on the system. Driver writers link against wdfdriverentry.lib and wdfLcoInstaller01009.dll at build time; the actual implementation is in the runtime .sys, which is serviced independently via Windows Update.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("THE PROBLEM WITH LEGACY WDM DRIVERS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A correct WDM driver must manually handle every IRP major function code the OS might send. For a typical device driver that is at minimum: IRP_MJ_CREATE, IRP_MJ_CLOSE, IRP_MJ_CLEANUP, IRP_MJ_READ, IRP_MJ_WRITE, IRP_MJ_DEVICE_CONTROL, IRP_MJ_PNP (with ~15 minor codes), and IRP_MJ_POWER. Missing or incorrectly implementing any of these causes non-compliance that only surfaces under edge conditions — safe removal, sleep/wake transitions, Driver Verifier stress runs.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// WDM DriverEntry — boilerplate before any real work\n" +
            "DriverObject->MajorFunction[IRP_MJ_CREATE]         = DispatchCreate;\n" +
            "DriverObject->MajorFunction[IRP_MJ_CLOSE]          = DispatchClose;\n" +
            "DriverObject->MajorFunction[IRP_MJ_CLEANUP]        = DispatchCleanup;\n" +
            "DriverObject->MajorFunction[IRP_MJ_READ]           = DispatchRead;\n" +
            "DriverObject->MajorFunction[IRP_MJ_WRITE]          = DispatchWrite;\n" +
            "DriverObject->MajorFunction[IRP_MJ_DEVICE_CONTROL] = DispatchIoctl;\n" +
            "DriverObject->MajorFunction[IRP_MJ_PNP]            = DispatchPnp;\n" +
            "DriverObject->MajorFunction[IRP_MJ_POWER]          = DispatchPower;\n" +
            "DriverObject->DriverUnload                         = DriverUnload;\n" +
            "// ...and inside DispatchPnp you switch on MinorFunction:\n" +
            "// IRP_MN_START_DEVICE, IRP_MN_STOP_DEVICE,\n" +
            "// IRP_MN_REMOVE_DEVICE, IRP_MN_SURPRISE_REMOVAL,\n" +
            "// IRP_MN_QUERY_CAPABILITIES, IRP_MN_QUERY_PNP_DEVICE_STATE...\n\n" +
            "// KMDF DriverEntry — same result, one call\n" +
            "WDF_DRIVER_CONFIG_INIT(&config, EvtDriverDeviceAdd);\n" +
            "return WdfDriverCreate(drv, reg,\n" +
            "                       WDF_NO_OBJECT_ATTRIBUTES,\n" +
            "                       &config, WDF_NO_HANDLE);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("KEY KMDF OBJECTS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Every KMDF entity is a typed handle. Objects have parent-child relationships: when a parent is deleted, all children are deleted automatically. This eliminates most manual cleanup code.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "WDFDRIVER     — the driver itself; created in DriverEntry\n" +
            "WDFDEVICE     — a device object; created in EvtDriverDeviceAdd\n" +
            "WDFQUEUE      — I/O queue; routes requests by type to callbacks\n" +
            "WDFREQUEST    — wraps an IRP; driver never touches the IRP directly\n" +
            "WDFMEMORY     — safe memory handle; freed automatically with parent\n" +
            "WDFTIMER      — kernel timer with automatic synchronization\n" +
            "WDFWORKITEM   — deferred work at PASSIVE_LEVEL\n" +
            "WDFINTERRUPT  — interrupt with built-in spin lock and DPC\n" +
            "WDFSPINLOCK   — spin lock object; no manual KeInitializeSpinLock\n" +
            "WDFCOLLECTION — dynamic array of KMDF objects"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DRIVER ENTRY AND DEVICE ADD")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("DriverEntry creates the WDFDRIVER and registers EvtDriverDeviceAdd. KMDF calls EvtDriverDeviceAdd once per device instance the OS enumerates (equivalent to IRP_MN_START_DEVICE arriving at DriverEntry in WDM). The driver creates its WDFDEVICE there, then sets up queues and resources.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "NTSTATUS DriverEntry(PDRIVER_OBJECT DriverObject,\n" +
            "                     PUNICODE_STRING RegistryPath)\n" +
            "{\n" +
            "    WDF_DRIVER_CONFIG config;\n" +
            "    WDF_DRIVER_CONFIG_INIT(&config, EvtDriverDeviceAdd);\n" +
            "    return WdfDriverCreate(DriverObject, RegistryPath,\n" +
            "                           WDF_NO_OBJECT_ATTRIBUTES,\n" +
            "                           &config, WDF_NO_HANDLE);\n" +
            "}\n\n" +
            "NTSTATUS EvtDriverDeviceAdd(WDFDRIVER       Driver,\n" +
            "                            PWDFDEVICE_INIT DeviceInit)\n" +
            "{\n" +
            "    // Optional: set device type, I/O buffering method\n" +
            "    WdfDeviceInitSetIoType(DeviceInit, WdfDeviceIoBuffered);\n\n" +
            "    WDFDEVICE device;\n" +
            "    NTSTATUS status = WdfDeviceCreate(&DeviceInit,\n" +
            "                                      WDF_NO_OBJECT_ATTRIBUTES,\n" +
            "                                      &device);\n" +
            "    if (!NT_SUCCESS(status)) return status;\n\n" +
            "    // Create symbolic link, I/O queues, interrupts...\n" +
            "    return MyCreateQueue(device);\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("I/O QUEUES AND REQUESTS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A WDFQUEUE intercepts IRPs before they reach the driver and dispatches them to typed callbacks. Three dispatch modes: Sequential (one request at a time — next is not delivered until the current completes), Parallel (all requests dispatched immediately), Manual (driver calls WdfIoQueueRetrieveNextRequest itself). The default queue catches all requests not handled by a more specific queue.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "NTSTATUS MyCreateQueue(WDFDEVICE device)\n" +
            "{\n" +
            "    WDF_IO_QUEUE_CONFIG qConfig;\n" +
            "    WDF_IO_QUEUE_CONFIG_INIT_DEFAULT_QUEUE(\n" +
            "        &qConfig, WdfIoQueueDispatchSequential);\n" +
            "    qConfig.EvtIoDeviceControl = EvtIoDeviceControl;\n" +
            "    qConfig.EvtIoRead          = EvtIoRead;\n" +
            "    qConfig.EvtIoWrite         = EvtIoWrite;\n\n" +
            "    WDFQUEUE queue;\n" +
            "    return WdfIoQueueCreate(device, &qConfig,\n" +
            "                            WDF_NO_OBJECT_ATTRIBUTES, &queue);\n" +
            "}\n\n" +
            "VOID EvtIoDeviceControl(WDFQUEUE   Queue,\n" +
            "                        WDFREQUEST Request,\n" +
            "                        size_t     OutputBufferLength,\n" +
            "                        size_t     InputBufferLength,\n" +
            "                        ULONG      IoControlCode)\n" +
            "{\n" +
            "    NTSTATUS status = STATUS_INVALID_DEVICE_REQUEST;\n" +
            "    ULONG_PTR info  = 0;\n\n" +
            "    if (IoControlCode == IOCTL_MY_COMMAND) {\n" +
            "        PVOID   buf;  size_t len;\n" +
            "        WdfRequestRetrieveInputBuffer(Request, 4, &buf, &len);\n" +
            "        // process buf...\n" +
            "        status = STATUS_SUCCESS;\n" +
            "        info   = 0;\n" +
            "    }\n" +
            "    WdfRequestCompleteWithInformation(Request, status, info);\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("PNP AND POWER — WHAT KMDF HANDLES FOR YOU")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The KMDF runtime runs the full PnP state machine and the full power policy state machine. The driver only fills in hardware-specific transition callbacks — everything else is default-handled correctly.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Register hardware callbacks on the device\n" +
            "WDF_PNPPOWER_EVENT_CALLBACKS pnpCbs;\n" +
            "WDF_PNPPOWER_EVENT_CALLBACKS_INIT(&pnpCbs);\n" +
            "pnpCbs.EvtDevicePrepareHardware  = EvtDevicePrepareHardware;\n" +
            "pnpCbs.EvtDeviceReleaseHardware  = EvtDeviceReleaseHardware;\n" +
            "pnpCbs.EvtDeviceD0Entry          = EvtDeviceD0Entry;\n" +
            "pnpCbs.EvtDeviceD0Exit           = EvtDeviceD0Exit;\n" +
            "WdfDeviceInitSetPnpPowerEventCallbacks(DeviceInit, &pnpCbs);\n\n" +
            "// EvtDevicePrepareHardware: map BARs, allocate DMA\n" +
            "// EvtDeviceReleaseHardware: unmap, free DMA\n" +
            "// EvtDeviceD0Entry: power on hardware, enable interrupts\n" +
            "// EvtDeviceD0Exit: disable interrupts, power down hardware\n\n" +
            "// IRP_MJ_PNP minor codes, IRP_MJ_POWER, IRP_MN_REMOVE_DEVICE,\n" +
            "// IRP_MN_SURPRISE_REMOVAL, etc. are all handled automatically\n" +
            "// by the framework — the driver never sees them."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("BUILDING")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Select the \"Kernel Mode Driver (KMDF)\" project template in Visual Studio with the WDK installed. The project automatically links against wdfdriverentry.lib and wdfLcoInstaller01009.dll. The output is a .sys file — a standard PE image with subsystem NATIVE, exactly like a WDM driver. Test signing setup and sc create / sc start deployment are identical to WDM. For PnP devices, ship an INF and deploy with pnputil /add-driver.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Minimal .vcxproj additions (handled by template):\n" +
            "//   $(KMDFVersion) = 1.33\n" +
            "//   AdditionalDependencies: wdfdriverentry.lib\n" +
            "//   INF AddReg: KmdfService, KmdfLibraryVersion = $(KMDFVersion)\n\n" +
            "// Load non-PnP driver the same way as WDM:\n" +
            "sc create MyKmdfDriver type= kernel binPath= C:\\MyDriver.sys\n" +
            "sc start  MyKmdfDriver\n" +
            "sc stop   MyKmdfDriver\n" +
            "sc delete MyKmdfDriver"
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
