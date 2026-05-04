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
fun PlugAndPlayScreen(navController: NavController) {
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
            text = "PLUG AND PLAY",
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

        // OVERVIEW
        SectionHeader("OVERVIEW")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Windows PnP (Plug and Play) is the subsystem that automatically detects " +
            "hardware, finds drivers, and builds a layered device stack — all without " +
            "rebooting or manual configuration.\n\n" +
            "Key roles:\n" +
            "  PnP Manager   — kernel component (part of ntoskrnl); orchestrates enumeration, " +
            "driver loading, resource assignment, and device stack construction\n" +
            "  Bus driver     — owns a bus (USB, PCI, ACPI...) and creates child PDOs when " +
            "devices appear on that bus\n" +
            "  Function driver — the main driver for a device; creates the FDO and handles I/O\n" +
            "  Filter drivers — optional; sit above or below the FDO and intercept IRPs"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // USB DEVICE INSERTION FLOW
        SectionHeader("USB DEVICE INSERTION FLOW")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "When you insert a USB disk drive the following sequence occurs:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "1. USB hub detects port state change (interrupt from hub hardware)\n" +
            "   usbhub.sys wakes up, resets the port, reads USB descriptors:\n" +
            "     - Device Descriptor  (VID/PID, device class, max packet size)\n" +
            "     - Configuration Descriptor\n\n" +
            "2. usbhub.sys creates a PDO for the new device and reports it\n" +
            "   to the PnP Manager via IRP_MN_BUS_RELATIONS (BusRelations query).\n\n" +
            "3. PnP Manager sends IRP_MN_QUERY_ID (BusQueryDeviceID) to the PDO.\n" +
            "   usbhub.sys returns: USB\\VID_0781&PID_5583\n" +
            "   (also compatible IDs like USB\\Class_08 for mass storage class)\n\n" +
            "4. PnP Manager searches the INF store for a matching driver.\n" +
            "   usbstor.inf matches on Class_08 (USB Mass Storage).\n\n" +
            "5. usbstor.sys is loaded. Its AddDevice routine is called with the PDO.\n" +
            "   AddDevice creates the FDO and calls IoAttachDeviceToDeviceStack(FDO, PDO).\n\n" +
            "6. PnP Manager sends IRP_MN_START_DEVICE to the FDO (top of stack).\n" +
            "   usbstor.sys starts talking to the device via USB bulk transfers.\n\n" +
            "7. usbstor.sys enumerates the device's logical units (LUNs) and\n" +
            "   creates a child PDO for each disk.\n\n" +
            "8. PnP Manager repeats the process for the child PDO:\n" +
            "   disk.sys is loaded, creates another FDO. partmgr.sys is loaded\n" +
            "   as an upper filter. Volume manager layers on top."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // DRIVER STACK DIAGRAM
        SectionHeader("DRIVER STACK (USB DISK)")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "  [ volmgr.sys / volsnap.sys ]  ← volume filters\n" +
            "  [ partmgr.sys              ]  ← partition manager (upper filter)\n" +
            "  [ disk.sys FDO             ]  ← function driver for disk LUN\n" +
            "  ────────── child PDO ──────────\n" +
            "  [ usbstor.sys FDO          ]  ← USB storage class driver\n" +
            "  ──────────── PDO ──────────────\n" +
            "  [ usbhub.sys               ]  ← USB hub bus driver (owns the PDO)\n" +
            "  [ usbxhci.sys / usbehci.sys]  ← host controller driver\n" +
            "  ─────── hardware ───────────────\n" +
            "         USB bus"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // PDO VS FDO
        SectionHeader("PDO VS FDO")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "PDO (Physical Device Object):\n" +
            "  • Created by the bus driver when a device appears on the bus\n" +
            "  • Represents the physical slot/connection; always at the bottom of the stack\n" +
            "  • The bus driver owns and manages it; handles bus-specific IRPs\n" +
            "  • Exists as long as the device is present (even after remove, until all " +
            "    handles are closed)\n\n" +
            "FDO (Functional Device Object):\n" +
            "  • Created by the function driver in its AddDevice callback\n" +
            "  • Attached above the PDO via IoAttachDeviceToDeviceStack\n" +
            "  • Handles the device's actual I/O — reads, writes, IOCTLs\n" +
            "  • There is exactly one FDO per PDO (one function driver)\n\n" +
            "Filter device objects (FiDO) are ordinary DEVICE_OBJECTs created by filter " +
            "drivers — they are upper or lower filters, not PDO or FDO."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // UPPER AND LOWER FILTERS
        SectionHeader("UPPER AND LOWER FILTERS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Upper filter: attached above the FDO. It sees IRPs before the FDO does. " +
            "Used for adding policy, logging, or modifying requests on the way down and " +
            "results on the way up (e.g., encryption, compression drivers).\n\n" +
            "Lower filter: attached above the PDO but below the FDO. It sees IRPs after " +
            "the FDO passes them down toward the bus. Used for hardware quirk workarounds " +
            "or adding bus-level features transparently.\n\n" +
            "Stack order (top to bottom):\n"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "  Upper filter(s)   ← IRP arrives here first on the way down\n" +
            "  FDO               ← function driver\n" +
            "  Lower filter(s)   ← between FDO and PDO\n" +
            "  PDO               ← bus driver; bottom of stack"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // INF FILE
        SectionHeader("INF FILE AND HOW YOUR DRIVER IS USED")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "A .inf file tells Windows Setup how to install a driver. The critical fields " +
            "for PnP matching are the hardware IDs in the [Models] section.\n\n" +
            "If you supply a hardware ID that matches the device, Windows selects your " +
            "driver as the function driver. Your AddDevice callback is called to create " +
            "the FDO — you become the function driver for that device.\n\n" +
            "If you want to be a filter instead, do NOT claim the hardware ID. Instead " +
            "add your service name to UpperFilters or LowerFilters via AddReg in the INF."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "; Minimal INF skeleton for a function driver\n" +
            "[Version]\n" +
            "Signature = \"\$WINDOWS NT\$\"\n" +
            "Class     = DiskDrive\n" +
            "ClassGuid  = {4D36E967-E325-11CE-BFC1-08002BE10318}\n" +
            "Provider   = %Manufacturer%\n" +
            "DriverVer  = 01/01/2024,1.0.0.0\n\n" +
            "[Manufacturer]\n" +
            "%Manufacturer% = Standard,NTamd64\n\n" +
            "[Standard.NTamd64]\n" +
            "; HardwareID=ServiceInstallSection\n" +
            "USB\\VID_0781&PID_5583 = MyDriver_Install\n\n" +
            "[MyDriver_Install.NT]\n" +
            "CopyFiles = MyDriver_CopyFiles\n\n" +
            "[MyDriver_Install.NT.Services]\n" +
            "AddService = MyDriver, 0x00000002, MyDriver_ServiceInstall\n\n" +
            "[MyDriver_ServiceInstall]\n" +
            "ServiceType    = 1   ; SERVICE_KERNEL_DRIVER\n" +
            "StartType      = 3   ; SERVICE_DEMAND_START\n" +
            "ErrorControl   = 1\n" +
            "ServiceBinary  = %12%\\mydriver.sys"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "For a filter driver, the INF uses AddReg to append to the UpperFilters or " +
            "LowerFilters value of the device class key — no HardwareID match needed."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // REGISTERING FILTERS IN THE REGISTRY
        SectionHeader("REGISTERING FILTERS IN THE REGISTRY")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Filter drivers are registered by service name (the name from AddService in " +
            "the INF). Two scopes exist:\n\n" +
            "Class-wide filter — applies to every device of that class:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "HKLM\\SYSTEM\\CurrentControlSet\\Control\\Class\\{ClassGUID}\\\n" +
            "  UpperFilters  REG_MULTI_SZ  MyFilterService\n" +
            "  LowerFilters  REG_MULTI_SZ  AnotherFilterService"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Device-specific filter — applies to one device instance only:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "HKLM\\SYSTEM\\CurrentControlSet\\Enum\\USB\\VID_0781&PID_5583\\<InstanceId>\\\n" +
            "  Device Parameters\\\n" +
            "    UpperFilters  REG_MULTI_SZ  MyFilterService"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The PnP Manager reads these values when building the device stack. For " +
            "multiple filters in REG_MULTI_SZ, they are stacked in list order (top to " +
            "bottom for UpperFilters, top to bottom for LowerFilters).\n\n" +
            "Note: writing to class filter keys requires SYSTEM privileges and typically " +
            "needs INF installation or SetupDiSetDeviceRegistryProperty — do not write " +
            "the registry directly from production code."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // REMOVE LOCK
        SectionHeader("REMOVE LOCK")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The Remove Lock (IO_REMOVE_LOCK) prevents the device stack from being torn " +
            "down while I/O is still in progress. Without it, IRP_MN_REMOVE_DEVICE could " +
            "free the device extension while a dispatch routine is still using it.\n\n" +
            "API:\n" +
            "  IoInitializeRemoveLock — call once in AddDevice; sets up the lock\n" +
            "  IoAcquireRemoveLock    — call at the top of every dispatch routine;\n" +
            "                           returns STATUS_DELETE_PENDING if removal is in progress\n" +
            "  IoReleaseRemoveLock    — call before completing each IRP\n" +
            "  IoReleaseRemoveLockAndWait — call in IRP_MN_REMOVE_DEVICE handler;\n" +
            "                              blocks until all outstanding acquires are released"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// AddDevice\n" +
            "IO_REMOVE_LOCK RemoveLock;\n" +
            "IoInitializeRemoveLock(&RemoveLock, 'kcoL', 0, 0);\n\n" +
            "// Every dispatch routine\n" +
            "NTSTATUS MyDispatch(PDEVICE_OBJECT DevObj, PIRP Irp) {\n" +
            "    NTSTATUS status = IoAcquireRemoveLock(&RemoveLock, Irp);\n" +
            "    if (!NT_SUCCESS(status)) {\n" +
            "        Irp->IoStatus.Status = status;\n" +
            "        IoCompleteRequest(Irp, IO_NO_INCREMENT);\n" +
            "        return status;\n" +
            "    }\n" +
            "    // ... handle the IRP ...\n" +
            "    IoReleaseRemoveLock(&RemoveLock, Irp);\n" +
            "    return status;\n" +
            "}\n\n" +
            "// IRP_MN_REMOVE_DEVICE handler\n" +
            "case IRP_MN_REMOVE_DEVICE:\n" +
            "    IoReleaseRemoveLockAndWait(&RemoveLock, Irp);  // waits for all I/O\n" +
            "    // now safe to delete device object and free extension"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // IRP_MJ_PNP MINOR CODES
        SectionHeader("IRP_MJ_PNP MINOR CODES")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "IRP_MN_START_DEVICE\n" +
            "  Resources assigned; start hardware; FDO must pass down first\n" +
            "  (IoCallDriver to PDO), then start after PDO completes\n\n" +
            "IRP_MN_QUERY_REMOVE_DEVICE\n" +
            "  Can this device be removed? Return STATUS_SUCCESS to allow,\n" +
            "  STATUS_UNSUCCESSFUL to veto (e.g., file is open)\n\n" +
            "IRP_MN_CANCEL_REMOVE_DEVICE\n" +
            "  A veto occurred somewhere in the stack; undo any prepare-for-remove state\n\n" +
            "IRP_MN_STOP_DEVICE\n" +
            "  Orderly resource release for rebalancing; hardware may still be present;\n" +
            "  queue or fail new I/O; will be followed by START or REMOVE\n\n" +
            "IRP_MN_REMOVE_DEVICE\n" +
            "  Device is being removed; free all resources; call\n" +
            "  IoReleaseRemoveLockAndWait; detach FDO from stack; delete device object\n\n" +
            "IRP_MN_SURPRISE_REMOVAL\n" +
            "  Unexpected removal — hardware already gone; see below\n\n" +
            "IRP_MN_QUERY_CAPABILITIES\n" +
            "  Query device capabilities (removable, wake, D-state mapping)\n\n" +
            "IRP_MN_QUERY_ID\n" +
            "  Query hardware ID, compatible IDs, instance ID, device ID\n\n" +
            "IRP_MN_QUERY_DEVICE_RELATIONS\n" +
            "  Bus driver returns array of child PDOs (BusRelations),\n" +
            "  or a single target device object (TargetDeviceRelation)"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // SURPRISE REMOVAL
        SectionHeader("SURPRISE REMOVAL")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Surprise removal happens when a device is physically disconnected without the " +
            "orderly query-remove → stop → remove sequence (e.g., yanking a USB cable).\n\n" +
            "The PnP Manager sends IRP_MN_SURPRISE_REMOVAL first, then (after all handles " +
            "on the device are closed) IRP_MN_REMOVE_DEVICE.\n\n" +
            "Difference from orderly remove:\n" +
            "  • In orderly remove, IRP_MN_STOP_DEVICE is sent first and hardware is still " +
            "accessible until REMOVE_DEVICE.\n" +
            "  • In surprise removal, hardware is already gone when SURPRISE_REMOVAL arrives. " +
            "Any attempt to access hardware will fail.\n\n" +
            "What to do in IRP_MN_SURPRISE_REMOVAL:\n" +
            "  1. Stop accepting new I/O requests (set a flag; new IRPs fail immediately)\n" +
            "  2. Cancel all pending IRPs or complete them with STATUS_DELETE_PENDING\n" +
            "  3. Do NOT detach or delete the device object yet — that happens in the\n" +
            "     subsequent IRP_MN_REMOVE_DEVICE\n" +
            "  4. Complete the IRP_MN_SURPRISE_REMOVAL IRP with STATUS_SUCCESS\n\n" +
            "The device object (and your device extension memory) must remain valid from " +
            "SURPRISE_REMOVAL until REMOVE_DEVICE is processed and all outstanding " +
            "handles are closed. The Remove Lock pattern ensures safe cleanup."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
