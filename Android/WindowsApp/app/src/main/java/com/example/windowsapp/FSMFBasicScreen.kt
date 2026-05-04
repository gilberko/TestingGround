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
fun FSMFBasicScreen(navController: NavController) {
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
            text = "BASIC MINIFILTER",
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

        SectionHeader("INF FILE AND SERVICE REGISTRATION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A minifilter is installed as a kernel-mode driver service. The INF file sets required registry values under:\n\nHKLM\\SYSTEM\\CurrentControlSet\\Services\\\n  MyFilter\\\n    Type = 2       (SERVICE_FILE_SYSTEM_DRIVER)\n    Start = 3      (SERVICE_DEMAND_START)\n    Instances\\\n      MyInstance\\\n        Altitude = \"328000\"\n        Flags    = 0\n\nFlags = 0 means the minifilter attaches automatically to matching volumes. Flags = 1 disables automatic attachment (manual only).")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("FILTER REGISTRATION STRUCTURE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Declare the operations array and the FLT_REGISTRATION struct. IRP_MJ_OPERATION_END terminates the array.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "const FLT_OPERATION_REGISTRATION Callbacks[] = {\n" +
            "    { IRP_MJ_CREATE,\n" +
            "      0,\n" +
            "      PreCreate, PostCreate },\n" +
            "    { IRP_MJ_WRITE,\n" +
            "      0,\n" +
            "      PreWrite, NULL },\n" +
            "    { IRP_MJ_READ,\n" +
            "      0,\n" +
            "      NULL, PostRead },\n" +
            "    { IRP_MJ_OPERATION_END }\n" +
            "};\n\n" +
            "const FLT_REGISTRATION FilterReg = {\n" +
            "    sizeof(FLT_REGISTRATION),\n" +
            "    FLT_REGISTRATION_VERSION,\n" +
            "    0,              // flags\n" +
            "    NULL,           // context registrations\n" +
            "    Callbacks,\n" +
            "    UnloadCallback,\n" +
            "    InstanceSetupCallback,\n" +
            "    NULL,           // InstanceQueryTeardown\n" +
            "    NULL,           // InstanceTeardownStart\n" +
            "    NULL,           // InstanceTeardownComplete\n" +
            "    NULL, NULL      // GenerateFileName callbacks\n" +
            "};"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DRIVERENTRY")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Register the filter with the Filter Manager, then start filtering. If FltStartFiltering fails, unregister immediately.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "PFLT_FILTER gFilterHandle;\n\n" +
            "NTSTATUS DriverEntry(\n" +
            "    PDRIVER_OBJECT DriverObject,\n" +
            "    PUNICODE_STRING RegistryPath)\n" +
            "{\n" +
            "    NTSTATUS status;\n\n" +
            "    status = FltRegisterFilter(\n" +
            "        DriverObject,\n" +
            "        &FilterReg,\n" +
            "        &gFilterHandle);\n\n" +
            "    if (NT_SUCCESS(status)) {\n" +
            "        status = FltStartFiltering(\n" +
            "            gFilterHandle);\n" +
            "        if (!NT_SUCCESS(status))\n" +
            "            FltUnregisterFilter(\n" +
            "                gFilterHandle);\n" +
            "    }\n" +
            "    return status;\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("UNLOAD CALLBACK")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("FltUnregisterFilter blocks until all in-flight callbacks have returned — guaranteed safe teardown.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "NTSTATUS UnloadCallback(\n" +
            "    FLT_FILTER_UNLOAD_FLAGS Flags)\n" +
            "{\n" +
            "    FltUnregisterFilter(gFilterHandle);\n" +
            "    return STATUS_SUCCESS;\n" +
            "}"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("If Flags contains FLTFL_FILTER_UNLOAD_MANDATORY, the filter must unload even if it would prefer not to. If the flag is absent, you can return STATUS_FLT_DO_NOT_DETACH to refuse the unload (e.g. while a scan is in progress).")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("INSTANCE SETUP CALLBACK")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Called by the Filter Manager each time a volume mounts. Return STATUS_FLT_DO_NOT_ATTACH to decline attachment to that volume.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "NTSTATUS InstanceSetupCallback(\n" +
            "    PCFLT_RELATED_OBJECTS FltObjects,\n" +
            "    FLT_INSTANCE_SETUP_FLAGS Flags,\n" +
            "    DEVICE_TYPE VolumeDeviceType,\n" +
            "    FLT_FILESYSTEM_TYPE VolumeFilesystemType)\n" +
            "{\n" +
            "    // Attach only to NTFS volumes\n" +
            "    if (VolumeFilesystemType != FLT_FSTYPE_NTFS)\n" +
            "        return STATUS_FLT_DO_NOT_ATTACH;\n\n" +
            "    return STATUS_SUCCESS;\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(32.dp))
        HackerButton("< BACK") { navController.popBackStack() }
    }
}
