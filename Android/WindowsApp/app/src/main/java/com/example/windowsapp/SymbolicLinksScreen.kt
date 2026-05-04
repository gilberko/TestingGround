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
fun SymbolicLinksScreen(navController: NavController) {
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
            text = "SYMBOLIC LINKS",
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

        SectionHeader("DEVICE NAMES VS DOS DEVICE NAMES")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "When you call IoCreateDevice with a name, the device object is placed in the " +
            "NT object namespace under \\Device\\, for example \\Device\\MyDevice. " +
            "This namespace is not directly accessible from user mode — Win32 APIs have no way " +
            "to open \\Device\\MyDevice directly.\n\n" +
            "The bridge is a symbolic link in the DOS device namespace. " +
            "The DOS device namespace is rooted at \\DosDevices\\ (also written \\??\\, " +
            "which is the per-session alias). A symbolic link entry here points at the " +
            "actual device object in \\Device\\.\n\n" +
            "Win32's \\\\.\\  prefix (in code: L\"\\\\\\\\.\\\\...\") maps directly to \\??\\  " +
            "in the native namespace. So \\\\.\\MyDevice opens \\??\\MyDevice " +
            "which resolves to \\Device\\MyDevice.\n\n" +
            "You can inspect the namespace with WinObj (Sysinternals) or the " +
            "!object WinDbg command."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("CREATING A SYMBOLIC LINK")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Call IoCreateSymbolicLink in DriverEntry after IoCreateDevice succeeds. " +
            "Both arguments are UNICODE_STRING pointers — the symbolic link name " +
            "(in \\DosDevices\\) and the device name (in \\Device\\)."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "UNICODE_STRING devName =\n" +
            "    RTL_CONSTANT_STRING(L\"\\\\Device\\\\MyDevice\");\n" +
            "UNICODE_STRING symLink =\n" +
            "    RTL_CONSTANT_STRING(L\"\\\\DosDevices\\\\MyDevice\");\n\n" +
            "// Create the device:\n" +
            "IoCreateDevice(DriverObject, sizeof(MY_EXT), &devName,\n" +
            "               FILE_DEVICE_UNKNOWN, 0, FALSE, &gDeviceObject);\n\n" +
            "// Create the symbolic link:\n" +
            "NTSTATUS status = IoCreateSymbolicLink(&symLink, &devName);\n" +
            "if (!NT_SUCCESS(status)) {\n" +
            "    IoDeleteDevice(gDeviceObject);\n" +
            "    return status;\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DELETING THE SYMBOLIC LINK")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Always call IoDeleteSymbolicLink in DriverUnload (and in your error path in " +
            "DriverEntry if something fails after the link was created). " +
            "If you forget, the link persists in the namespace even after your driver " +
            "unloads — subsequent CreateFile calls will fail or resolve to a deleted device, " +
            "and you cannot reload the driver without a reboot (or manually removing the link)."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "VOID DriverUnload(PDRIVER_OBJECT DriverObject) {\n" +
            "    UNICODE_STRING symLink =\n" +
            "        RTL_CONSTANT_STRING(L\"\\\\DosDevices\\\\MyDevice\");\n\n" +
            "    IoDeleteSymbolicLink(&symLink);  // remove the link first\n" +
            "    IoDeleteDevice(gDeviceObject);   // then delete the device\n" +
            "}"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Order matters: delete the symbolic link before the device object. " +
            "If you reverse the order, the link briefly points at a freed device."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("OPENING FROM USER MODE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "From a Win32 application, use CreateFile with the \\\\.\\  prefix followed by " +
            "the name you used in \\DosDevices\\. This generates an IRP_MJ_CREATE which " +
            "travels down to your driver. On success, you have a HANDLE that can be used " +
            "with ReadFile, WriteFile, DeviceIoControl, and CloseHandle."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// User mode (Win32 C/C++):\n" +
            "HANDLE hDevice = CreateFile(\n" +
            "    L\"\\\\\\\\.\\\\MyDevice\",        // \\\\.\\MyDevice\n" +
            "    GENERIC_READ | GENERIC_WRITE,\n" +
            "    0,                             // no sharing\n" +
            "    NULL,                          // default security\n" +
            "    OPEN_EXISTING,\n" +
            "    0,\n" +
            "    NULL);\n\n" +
            "if (hDevice == INVALID_HANDLE_VALUE) {\n" +
            "    // GetLastError() for details\n" +
            "}\n\n" +
            "// Send an IOCTL:\n" +
            "DeviceIoControl(hDevice, MY_IOCTL_CODE,\n" +
            "                inBuf, inSize, outBuf, outSize,\n" +
            "                &bytesReturned, NULL);\n\n" +
            "CloseHandle(hDevice);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("NAMESPACE QUICK REFERENCE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "\\Device\\MyDevice       — NT kernel namespace (not accessible from user mode)\n" +
            "\\DosDevices\\MyDevice   — DOS device namespace (kernel spelling)\n" +
            "\\??\\MyDevice           — same as above (shorthand alias)\n" +
            "\\\\.\\MyDevice           — Win32 spelling that maps to \\??\\MyDevice\n\n" +
            "The \\DosDevices\\ and \\??\\  directories are per-session in modern Windows. " +
            "For system-level drivers (session 0), they point to the same global location."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
