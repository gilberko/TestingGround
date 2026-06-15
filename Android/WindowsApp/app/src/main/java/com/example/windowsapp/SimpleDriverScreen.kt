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
fun SimpleDriverScreen(navController: NavController) {
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
            text = "SIMPLE DRIVER",
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

        SectionHeader("OVERVIEW")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The minimal kernel driver: registers a DriverUnload callback, prints a message " +
            "via DbgPrint, and returns STATUS_SUCCESS from DriverEntry. No device object, " +
            "no dispatch routines, no IRPs."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("THE CODE")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "#include <ntddk.h>\n\n" +
            "VOID DriverUnload(PDRIVER_OBJECT DriverObject)\n" +
            "{\n" +
            "    UNREFERENCED_PARAMETER(DriverObject);\n" +
            "    DbgPrint(\"SimpleDriver: Unloaded\\n\");\n" +
            "}\n\n" +
            "NTSTATUS DriverEntry(PDRIVER_OBJECT DriverObject,\n" +
            "                     PUNICODE_STRING RegistryPath)\n" +
            "{\n" +
            "    UNREFERENCED_PARAMETER(RegistryPath);\n" +
            "    DbgPrint(\"SimpleDriver: DriverEntry called\\n\");\n" +
            "    DriverObject->DriverUnload = DriverUnload;\n" +
            "    return STATUS_SUCCESS;\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("LINE BY LINE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "#include <ntddk.h>\n" +
            "The core kernel DDK header. Provides NTSTATUS, PDRIVER_OBJECT, DbgPrint, and " +
            "all other kernel-mode types and functions. No CRT, no Win32 APIs.\n\n" +
            "VOID DriverUnload(PDRIVER_OBJECT DriverObject)\n" +
            "Called by the OS when the driver is stopped (sc stop). Without this registered, " +
            "the driver cannot be unloaded after loading.\n\n" +
            "UNREFERENCED_PARAMETER(...)\n" +
            "Suppresses the \"unused parameter\" compiler warning. Common in kernel code " +
            "where callback signatures are fixed by the OS.\n\n" +
            "DbgPrint(...)\n" +
            "Kernel-mode printf. Output is captured by DebugView or a kernel debugger. " +
            "Not filtered by default — use DbgPrintEx with levels in production.\n\n" +
            "NTSTATUS DriverEntry(PDRIVER_OBJECT, PUNICODE_STRING)\n" +
            "The mandatory driver entry point. Called when the driver loads (sc start). " +
            "Equivalent to main() but runs in ring 0.\n\n" +
            "DriverObject->DriverUnload = DriverUnload\n" +
            "Registers the unload callback. The OS checks this field on sc stop — if NULL, " +
            "the driver is considered non-removable and sc stop will fail.\n\n" +
            "return STATUS_SUCCESS\n" +
            "A non-success NTSTATUS here causes the OS to immediately unload the driver " +
            "and report a load failure to the SCM."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("BUILDING")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Requires the Windows Driver Kit (WDK) installed alongside Visual Studio. " +
            "Create an \"Empty WDM Driver\" project. Build produces a .sys file — a standard " +
            "PE image with subsystem NATIVE (not GUI or Console). Target Debug|x64 for " +
            "development, Release|x64 for production."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("TEST SIGNING")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Windows requires all kernel drivers to be signed. For development, enable test " +
            "signing mode to allow self-signed or test-signed drivers to load."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "bcdedit /set testsigning on   ; enable test mode (reboot required)\n" +
            "                              ; a \"Test Mode\" watermark appears on desktop\n\n" +
            "bcdedit /set testsigning off  ; disable after development (reboot required)"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("LOADING WITH SC")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("All five Service Control Manager commands for the driver lifecycle:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "; Register driver as a kernel service\n" +
            "; Note: spaces after = are required\n" +
            "sc create SimpleDriver type= kernel binPath= C:\\path\\to\\SimpleDriver.sys\n\n" +
            "; Load the driver into the kernel; DriverEntry is called\n" +
            "sc start SimpleDriver\n\n" +
            "; Check current state: RUNNING, STOPPED, etc.\n" +
            "sc query SimpleDriver\n\n" +
            "; Unload the driver; DriverUnload is called\n" +
            "sc stop SimpleDriver\n\n" +
            "; Remove the service entry from the SCM database\n" +
            "sc delete SimpleDriver"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("THE SERVICE CONTROL MANAGER")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The SCM (services.msc) manages both user-mode services and kernel-mode drivers " +
            "as \"services\". The type= kernel parameter tells it this is a driver, not a " +
            "user-mode process.\n\n" +
            "After sc create, the driver appears in Device Manager under \"Non-Plug and Play " +
            "Drivers\" (enable Show Hidden Devices). Unlike a user-mode service that runs " +
            "as a process in ring 3, a kernel driver runs in ring 0 as part of the OS — " +
            "a crash in the driver crashes the entire system."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
