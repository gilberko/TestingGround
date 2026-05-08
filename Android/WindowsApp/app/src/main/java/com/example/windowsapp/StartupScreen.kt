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
fun StartupScreen(navController: NavController) {
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
            text = "STARTUP",
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

        SectionHeader("FIRMWARE AND POST")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("When power is applied, the CPU starts executing at a fixed reset vector address. UEFI firmware (or legacy BIOS) runs POST (Power-On Self Test), initializes hardware (RAM, PCIe, storage controllers), then locates and loads the Windows Boot Manager from the EFI System Partition (ESP).")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Power on\n" +
            "  └─ CPU reset vector\n" +
            "      └─ UEFI firmware (POST, hardware init)\n" +
            "          └─ Reads EFI System Partition (ESP)\n" +
            "              └─ Launches bootmgfw.efi"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WINDOWS BOOT MANAGER")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("bootmgfw.efi (Windows Boot Manager) reads the Boot Configuration Data (BCD) store — a binary file formatted like a registry hive, located at \\EFI\\Microsoft\\Boot\\BCD on the ESP. BCD contains OS boot entries. Boot Manager presents a menu if multiple entries exist or the timeout is non-zero, then launches the selected boot loader.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "BCD store location:  \\EFI\\Microsoft\\Boot\\BCD\n" +
            "Format: binary registry hive (like SYSTEM hive)\n\n" +
            "Useful tool: bcdedit.exe (view and edit BCD entries)\n" +
            "  bcdedit /enum all       -- list all entries\n" +
            "  bcdedit /set {current} safeboot minimal  -- enable safe mode"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WINDOWS BOOT LOADER (WINLOAD.EFI)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("winload.efi is the Windows Boot Loader, launched by Boot Manager. It performs the critical early loading steps before handing control to the kernel:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "1. Read and verify ntoskrnl.exe and hal.dll\n" +
            "2. Load the SYSTEM hive from\n" +
            "     \\Windows\\System32\\config\\SYSTEM\n" +
            "   (only this hive is available at this stage)\n" +
            "3. Read SYSTEM\\CurrentControlSet\\Services\n" +
            "   to find boot-start drivers (Start = 0)\n" +
            "4. Load ELAM drivers first (special early-launch\n" +
            "   anti-malware drivers, also Start = 0)\n" +
            "5. Load all remaining boot-start drivers\n" +
            "6. Transfer control to ntoskrnl.exe"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("KERNEL INITIALIZATION (NTOSKRNL)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Kernel initialization has two phases:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Phase 0: Minimal bootstrap\n" +
            "  - Interrupts off\n" +
            "  - HAL (Hardware Abstraction Layer) init\n" +
            "  - Basic memory manager bootstrap\n" +
            "  - ELAM driver DriverEntry runs here\n\n" +
            "Phase 1: Full initialization\n" +
            "  - Object manager, security reference monitor\n" +
            "  - I/O manager, PnP manager, Power manager\n" +
            "  - System-start drivers loaded (Start = 1)\n" +
            "  - smss.exe (Session Manager) created as\n" +
            "    first user-mode process"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SESSION MANAGER (SMSS.EXE)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("smss.exe is the first user-mode process. It reads HKLM\\SYSTEM\\CurrentControlSet\\Control\\Session Manager and performs several critical setup steps:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "1. BootExecute programs\n" +
            "   e.g. autochk.exe  (disk consistency check)\n\n" +
            "2. PendingFileRenameOperations\n" +
            "   (file moves/deletes deferred from last session)\n\n" +
            "3. Load additional registry hives:\n" +
            "   SOFTWARE, SAM, SECURITY now become available\n\n" +
            "4. Create pagefile\n\n" +
            "5. Start CSRSS.exe  (Win32 subsystem)\n\n" +
            "6. Start Winlogon.exe  (logon UI and Userinit)"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SERVICE CONTROL MANAGER")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("services.exe (Service Control Manager, SCM) is started by Winlogon. SCM loads all auto-start services and drivers (Start = 2). This is where the vast majority of drivers and services come online — antivirus, network stack services, third-party software, etc.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DRIVER START TYPES")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Every driver/service in the registry has a Start value under HKLM\\SYSTEM\\CurrentControlSet\\Services\\<name>:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Value  Name      When loaded              Who loads\n" +
            "─────────────────────────────────────────────────────\n" +
            "  0    Boot      Before kernel             winload.efi\n" +
            "  1    System    Kernel Phase 1 init       Kernel (IoInitSystem)\n" +
            "  2    Auto      After Session Manager     SCM (services.exe)\n" +
            "  3    Demand    On demand                 SCM / application\n" +
            "  4    Disabled  Never                     —"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("ELAM drivers are Start = 0 (Boot) with a special service type flag that marks them as early-launch. The kernel loads them before any other Start = 0 drivers.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("REGISTRY AVAILABILITY TIMELINE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Different registry hives become available at different stages of boot. Boot-start drivers can only access SYSTEM; later drivers have access to more:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Stage                     Available hives\n" +
            "─────────────────────────────────────────────────\n" +
            "winload.efi               SYSTEM only\n" +
            "Kernel Phase 0            SYSTEM only\n" +
            "Kernel Phase 1            SYSTEM + volatile HARDWARE\n" +
            "                          (HARDWARE built by kernel,\n" +
            "                           not loaded from disk)\n" +
            "smss.exe                  SYSTEM, SOFTWARE, SAM,\n" +
            "                          SECURITY\n" +
            "After user logon          All above + HKCU\n" +
            "                          (NTUSER.DAT loaded by\n" +
            "                           Winlogon)"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The HARDWARE hive is volatile — built fresh by the kernel each boot from hardware detection. It is never stored on disk. The SYSTEM hive is the most important for early boot as it contains all driver configurations and critical OS settings.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SAFE MODE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Safe Mode loads a minimal set of drivers and skips auto-start services. It is useful for diagnosing driver or software issues that prevent normal boot.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "How to trigger:\n" +
            "  Shift + Restart → Troubleshoot → Startup Settings\n" +
            "  Or: bcdedit /set {current} safeboot minimal\n\n" +
            "What loads in Safe Mode:\n" +
            "  Only drivers/services listed under:\n" +
            "  HKLM\\SYSTEM\\CurrentControlSet\\Control\\\n" +
            "    SafeBoot\\Minimal\n\n" +
            "  Auto-start services (Start = 2) are skipped\n" +
            "  unless explicitly listed in SafeBoot\\Minimal.\n\n" +
            "Safe Mode variants:\n" +
            "  Minimal     — no networking\n" +
            "  Network     — includes networking drivers\n" +
            "  AlternateShell — command prompt instead of Explorer"
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
