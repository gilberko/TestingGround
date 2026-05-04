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
fun CommunicatingWithDeviceDriversScreen(navController: NavController) {
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
            text = "COMMUNICATING WITH\nDEVICE DRIVERS",
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
            "User-mode applications communicate with kernel drivers using the standard Win32 " +
            "file API — the same CreateFile, ReadFile, WriteFile, and CloseHandle functions " +
            "used for files. The Windows I/O Manager intercepts these calls and translates " +
            "them into IRPs (I/O Request Packets) dispatched to the driver."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("OPENING A HANDLE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Use CreateFile with a device path. The driver must have created a device object " +
            "and a symbolic link for this to resolve."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "HANDLE hDevice = CreateFile(\n" +
            "    L\"\\\\\\\\.\\\\MyDevice\",  // user-mode device path\n" +
            "    GENERIC_READ | GENERIC_WRITE,\n" +
            "    0,               // no sharing\n" +
            "    nullptr,\n" +
            "    OPEN_EXISTING,\n" +
            "    FILE_ATTRIBUTE_NORMAL,\n" +
            "    nullptr);\n\n" +
            "if (hDevice == INVALID_HANDLE_VALUE) {\n" +
            "    // GetLastError() for details\n" +
            "}\n" +
            "// Use handle...\n" +
            "CloseHandle(hDevice);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Opening the handle sends IRP_MJ_CREATE to the driver's dispatch routine. " +
            "CloseHandle sends IRP_MJ_CLEANUP and later IRP_MJ_CLOSE."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DOS DEVICE NAMES")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The path \"\\\\.\\MyDevice\" is how user mode refers to devices. Windows maps " +
            "\"\\\\.\\\" to the \"\\??\\\" object namespace (also written \"\\DosDevices\\\"). " +
            "The driver creates this mapping in DriverEntry using IoCreateSymbolicLink. " +
            "The actual DEVICE_OBJECT lives at a kernel namespace path."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// In DriverEntry:\n" +
            "UNICODE_STRING devName, symLink;\n" +
            "RtlInitUnicodeString(&devName,\n" +
            "                     L\"\\\\Device\\\\MyDevice\");\n" +
            "RtlInitUnicodeString(&symLink,\n" +
            "                     L\"\\\\DosDevices\\\\MyDevice\");\n\n" +
            "IoCreateDevice(DriverObject, 0, &devName,\n" +
            "               FILE_DEVICE_UNKNOWN, 0, FALSE,\n" +
            "               &g_DeviceObject);\n" +
            "IoCreateSymbolicLink(&symLink, &devName);\n\n" +
            "// In DriverUnload:\n" +
            "IoDeleteSymbolicLink(&symLink);\n" +
            "IoDeleteDevice(g_DeviceObject);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SENDING AN IOCTL")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "DeviceIoControl sends a control code to the driver along with optional input " +
            "and output buffers."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "DWORD bytesReturned = 0;\n" +
            "MyInputStruct  input  = { /* ... */ };\n" +
            "MyOutputStruct output = {};\n\n" +
            "DeviceIoControl(\n" +
            "    hDevice,\n" +
            "    IOCTL_MY_COMMAND,   // control code\n" +
            "    &input,  sizeof(input),\n" +
            "    &output, sizeof(output),\n" +
            "    &bytesReturned,\n" +
            "    nullptr);           // nullptr = synchronous"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "This triggers IRP_MJ_DEVICE_CONTROL in the driver. The driver reads the IOCTL " +
            "code from IoGetCurrentIrpStackLocation(Irp)->Parameters.DeviceIoControl.IoControlCode."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("CTL_CODE MACRO")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "IOCTL codes are 32-bit values composed of four fields: DeviceType, Function " +
            "number, Method, and Access. Defined in a shared header used by both driver and " +
            "user-mode code."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "#define IOCTL_MY_COMMAND \\\n" +
            "    CTL_CODE(FILE_DEVICE_UNKNOWN, 0x800, \\\n" +
            "             METHOD_BUFFERED, FILE_ANY_ACCESS)\n\n" +
            "// Method controls buffer transfer:\n" +
            "// METHOD_BUFFERED   (0) - I/O mgr copies to/from kernel pool\n" +
            "// METHOD_IN_DIRECT  (1) - input buffered; output locked via MDL\n" +
            "// METHOD_OUT_DIRECT (2) - input buffered; output MDL for reading\n" +
            "// METHOD_NEITHER    (3) - raw user ptrs (driver must probe/lock)"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("IRP TRANSLATION TABLE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Every Win32 I/O call generates an IRP. The driver registers handlers in " +
            "DriverObject->MajorFunction[]."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Win32 API            IRP Major Function\n" +
            "-------------------------------------------\n" +
            "CreateFile       ->  IRP_MJ_CREATE\n" +
            "ReadFile         ->  IRP_MJ_READ\n" +
            "WriteFile        ->  IRP_MJ_WRITE\n" +
            "DeviceIoControl  ->  IRP_MJ_DEVICE_CONTROL\n" +
            "CloseHandle      ->  IRP_MJ_CLEANUP\n" +
            "                     IRP_MJ_CLOSE  (later)"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("CLEANUP VS CLOSE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Two distinct IRPs fire when a handle is closed. IRP_MJ_CLEANUP fires when the " +
            "last user-mode handle to the file object is closed (CloseHandle). IRP_MJ_CLOSE " +
            "fires later, when the kernel's object manager drops its reference to the file " +
            "object — this may happen significantly after CLEANUP if kernel components still " +
            "hold references. Always free per-open resources in IRP_MJ_CLEANUP, not " +
            "IRP_MJ_CLOSE, so they are released promptly."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
