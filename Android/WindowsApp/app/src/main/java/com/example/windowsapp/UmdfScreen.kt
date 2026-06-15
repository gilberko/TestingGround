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
fun UmdfScreen(navController: NavController) {
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
            text = "WDF / UMDF",
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

        SectionHeader("WHAT IS UMDF")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("UMDF (User-Mode Driver Framework) is the user-mode half of WDF. A UMDF driver is a DLL loaded into WUDFHost.exe (Windows User-mode Driver Framework Host) — a regular Win32 process running in ring 3. Each device instance gets its own isolated WUDFHost.exe process. The driver uses nearly the same Wdf-prefixed API as KMDF, but runs entirely in user mode with no kernel privileges.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHEN WAS IT ADDED")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("UMDF 1.0 — Windows Vista (2007). Used a COM-based API (IDriverEntry, IWDFDriver, IWDFDevice interfaces) that was completely different from KMDF. Writing a UMDF 1.x driver felt nothing like writing a KMDF driver.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("UMDF 2.0 — Windows 8.1 (2013). A complete redesign that unified the API with KMDF: same WDF_DRIVER_CONFIG, same WdfDriverCreate, same WdfIoQueueCreate, same WDFREQUEST. A UMDF 2.x driver and a KMDF driver look nearly identical in source. UMDF 1.x is considered obsolete; all new UMDF development uses 2.x.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("HOW UMDF DRIVERS RUN")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The kernel side has a reflector — WUDFRd.sys — that sits in the device stack and intercepts I/O requests. It forwards them via local IPC (ALPC) to WUDFHost.exe where the driver DLL processes them. Completed requests travel back the same way. The reflector is transparent to applications: DeviceIoControl, ReadFile, WriteFile all work as normal.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Application calls ReadFile()\n" +
            "  → I/O Manager (kernel)\n" +
            "    → WUDFRd.sys reflector (kernel, device stack)\n" +
            "      → ALPC message to WUDFHost.exe\n" +
            "        → UMDF driver DLL  ← your code runs here\n" +
            "          → WdfRequestCompleteWithInformation()\n" +
            "        → ALPC reply back to reflector\n" +
            "      → I/O Manager completes the IRP\n" +
            "  → ReadFile returns to application"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("If WUDFHost.exe crashes, only that device becomes unavailable. The system does not BSOD. WUDFHost can be configured to restart automatically on failure via the driver's INF, making UMDF drivers significantly more resilient than kernel drivers.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHAT CAN YOU WRITE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("UMDF is well-suited for any device that does not require direct hardware access from kernel space:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "USB devices      — function driver via WinUSB lower filter\n" +
            "HID devices      — Human Interface Devices (keyboards, mice,\n" +
            "                   gamepads, sensors exposed as HID)\n" +
            "Sensors          — using Windows Sensor class extension\n" +
            "Serial / COM     — RS-232, USB-to-serial adapters\n" +
            "GPIO / I2C / SPI — simple peripheral buses\n" +
            "Custom hardware  — any device where you don't need DMA or\n" +
            "                   direct interrupt handling in kernel mode\n\n" +
            "NOT suitable for:\n" +
            "  NICs            — require NDIS / kernel packet access\n" +
            "  Storage         — require kernel DMA, filter stack access\n" +
            "  Graphics        — WDDM is kernel-mode\n" +
            "  Audio           — PortCls / AVStream are kernel-mode"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHAT YOU CANNOT DO")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Running in user mode imposes real constraints compared to KMDF:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Direct DMA        — no WdfDmaEnabler; reflector manages\n" +
            "                    memory mapping on your behalf\n" +
            "ISR in user mode  — interrupts are delivered via reflector\n" +
            "                    as a callback at PASSIVE_LEVEL equivalent;\n" +
            "                    no DIRQL, no hardware register access\n" +
            "Kernel APIs       — cannot call ExAllocatePool2, KeWaitFor*,\n" +
            "                    IoAllocateIrp, or any DDI\n" +
            "Kernel memory     — no access to kernel address space\n" +
            "Elevated IRQL     — always at PASSIVE_LEVEL equivalent;\n" +
            "                    spin locks, DPCs not available"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("BENEFITS OVER KMDF")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Crashes don't BSOD   — WUDFHost restarts; system stays up\n" +
            "User-mode debugging  — attach WinDbg or VS to WUDFHost.exe\n" +
            "                       like any user-mode process\n" +
            "C++ STL freely       — std::vector, std::string, exceptions\n" +
            "CRT functions        — malloc, printf, file I/O via Win32\n" +
            "No IRQL concerns     — no non-paged pool requirements\n" +
            "Faster iteration     — rebuild DLL; no test-signing ceremony\n" +
            "Process isolation    — one device crash = one WUDFHost crash"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DRIVER STRUCTURE EXAMPLE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A minimal UMDF 2.x driver looks identical to a KMDF driver. The only differences are in the project template (outputs a DLL) and the INF file.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// DriverEntry — identical to KMDF\n" +
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
            "    WdfDeviceInitSetIoType(DeviceInit, WdfDeviceIoBuffered);\n\n" +
            "    WDFDEVICE device;\n" +
            "    NTSTATUS status = WdfDeviceCreate(&DeviceInit,\n" +
            "                                      WDF_NO_OBJECT_ATTRIBUTES,\n" +
            "                                      &device);\n" +
            "    if (!NT_SUCCESS(status)) return status;\n\n" +
            "    WDF_IO_QUEUE_CONFIG qConfig;\n" +
            "    WDF_IO_QUEUE_CONFIG_INIT_DEFAULT_QUEUE(\n" +
            "        &qConfig, WdfIoQueueDispatchSequential);\n" +
            "    qConfig.EvtIoRead  = EvtIoRead;\n" +
            "    qConfig.EvtIoWrite = EvtIoWrite;\n\n" +
            "    WDFQUEUE queue;\n" +
            "    return WdfIoQueueCreate(device, &qConfig,\n" +
            "                            WDF_NO_OBJECT_ATTRIBUTES, &queue);\n" +
            "}\n\n" +
            "VOID EvtIoRead(WDFQUEUE Queue, WDFREQUEST Request,\n" +
            "               size_t Length)\n" +
            "{\n" +
            "    WDFMEMORY mem;\n" +
            "    WdfRequestRetrieveOutputMemory(Request, &mem);\n" +
            "    // WdfMemoryCopyFromBuffer, or fill via WdfMemoryGetBuffer\n" +
            "    WdfRequestCompleteWithInformation(Request,\n" +
            "                                      STATUS_SUCCESS, Length);\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("BUILDING AND INSTALLING")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Select the \"User Mode Driver (UMDF V2)\" project template in Visual Studio + WDK. The output is a .dll, not a .sys. The project links against umdfstub.lib and the WDF co-installer handles WUDFHost setup.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// INF file additions required for UMDF:\n" +
            "[MyDriver_Install.NT]\n" +
            "CopyFiles = MyDriver_CopyFiles\n\n" +
            "[MyDriver_Install.NT.Services]\n" +
            "AddService = WUDFRd, 0x000001fa, WUDFRD_ServiceInstall\n\n" +
            "[MyDriver_Install.NT.Wdf]\n" +
            "UmdfService        = MyDriver, MyDriver_Install\n" +
            "UmdfServiceOrder   = MyDriver\n\n" +
            "[MyDriver_Install]\n" +
            "UmdfLibraryVersion = \$UMDFVERSION\$\n" +
            "ServiceBinary      = %12%\\UMDF\\MyDriver.dll\n\n" +
            "// Deploy:\n" +
            "pnputil /add-driver MyDriver.inf /install"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DEBUGGING")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Because UMDF drivers run inside WUDFHost.exe, you debug them like any user-mode process. No kernel debugger needed for most development work.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Option 1: WinDbg user-mode attach\n" +
            "windbg -p <PID of WUDFHost.exe>\n\n" +
            "// Find the right WUDFHost.exe PID:\n" +
            "tasklist | findstr WUDFHost\n\n" +
            "// Option 2: Visual Studio\n" +
            "// Debug → Attach to Process → WUDFHost.exe\n" +
            "// Set breakpoints normally; inspect locals, heap, STL\n\n" +
            "// Option 3: enable WDF verifier for UMDF\n" +
            "WdfVerifier.exe (ships with WDK Tools)\n" +
            "// Enables host process break-on-error, verbose tracing\n\n" +
            "// WPP tracing works the same as KMDF:\n" +
            "// TraceMessage() → captured with tracelog / TraceView"
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
