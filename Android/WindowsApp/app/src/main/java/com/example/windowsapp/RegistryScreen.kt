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
fun RegistryScreen(navController: NavController) {
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
            text = "THE WINDOWS REGISTRY",
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

        SectionHeader("WHAT IS THE REGISTRY?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The Windows Registry is a centralized, hierarchical database that Windows and installed applications use to store configuration data. It replaced the proliferation of .ini files used in Windows 3.x — instead of every app managing its own config file, all settings live in one structured store.\n\n" +
            "The registry is organized as a tree of keys, subkeys, and values. Each value has a name, a type (DWORD, REG_SZ, REG_BINARY, REG_MULTI_SZ, etc.), and data. Think of keys as folders and values as files inside them.\n\n" +
            "The registry stores: hardware configuration, installed software settings, user preferences, service and driver configuration, file associations, security policies, and much more."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("THE SIX ROOT KEYS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "HKEY_LOCAL_MACHINE (HKLM)\n" +
            "  Machine-wide settings — hardware, drivers, services, installed software. Requires admin to write.\n\n" +
            "HKEY_CURRENT_USER (HKCU)\n" +
            "  Settings for the currently logged-in user — per-user preferences, user-specific app config. Alias: maps to HKEY_USERS\\<current-user-SID>.\n\n" +
            "HKEY_CLASSES_ROOT (HKCR)\n" +
            "  File associations and COM class registrations. Not a real hive — it is a merged view of HKLM\\SOFTWARE\\Classes and HKCU\\SOFTWARE\\Classes (user values override machine values).\n\n" +
            "HKEY_USERS (HKU)\n" +
            "  Contains one subkey per loaded user profile (by SID), plus .DEFAULT for the default profile. Each subkey is that user's NTUSER.DAT hive.\n\n" +
            "HKEY_CURRENT_CONFIG (HKCC)\n" +
            "  Alias for HKLM\\SYSTEM\\CurrentControlSet\\Hardware Profiles\\Current — hardware profile info for the current boot.\n\n" +
            "HKEY_PERFORMANCE_DATA (HKPD)\n" +
            "  Not stored on disk. A virtual key that exposes performance counter data via the registry API (RegQueryValueEx). Not visible in regedit."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("HKEY_LOCAL_MACHINE STRUCTURE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "HKLM contains five primary subkeys:\n\n" +
            "BCD00000000\n" +
            "  The Boot Configuration Database — stores boot entries (OSes, boot options). Normally accessed via bcdedit.exe, not regedit directly. Corresponds to \\EFI\\Microsoft\\Boot\\BCD on the EFI partition.\n\n" +
            "HARDWARE\n" +
            "  Volatile (not stored on disk). Built fresh each boot by the kernel and HAL. Contains detected hardware information: device map, ACPI tables, resource lists.\n\n" +
            "SAM\n" +
            "  Security Account Manager — local user accounts and groups, password hashes (stored as NTLM hashes). Access-controlled; even admins cannot read it directly without SYSTEM privileges or a tool like PwDump.\n\n" +
            "SECURITY\n" +
            "  Local security policy, audit policy, privilege assignments. Readable only by the SYSTEM account.\n\n" +
            "SOFTWARE\n" +
            "  Installed applications, component registrations, COM classes (under Classes subkey), Windows features. Writable by admins.\n\n" +
            "SYSTEM\n" +
            "  System configuration — services, drivers, boot settings, control sets. This is where driver and service configuration lives. Most critical for boot behavior."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("CONTROL SETS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Under HKLM\\SYSTEM you will find subkeys named ControlSet001, ControlSet002, and CurrentControlSet.\n\n" +
            "Each ControlSetXXX is a complete snapshot of the system configuration used during a specific boot: which services and drivers load, in what order, with what parameters.\n\n" +
            "CurrentControlSet is not a real key — it is a registry symlink that points to whichever ControlSetXXX is currently active. Changes made at runtime (e.g., sc start) go into CurrentControlSet and are reflected in the active ControlSet.\n\n" +
            "The SYSTEM\\Select subkey controls which ControlSet is used:\n\n" +
            "  Current (DWORD): the ControlSet currently in use (1 or 2)\n" +
            "  Default (DWORD): the ControlSet to use on the next normal boot\n" +
            "  LastKnownGood (DWORD): the ControlSet from the last boot where a user successfully logged on\n" +
            "  Failed (DWORD): the ControlSet that caused a failed boot (set by the system on crash)\n\n" +
            "Windows maintains at most two active control sets to keep disk usage minimal. When a new config is committed, the old one may be overwritten."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("BSOD AND LAST KNOWN GOOD")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "When Windows successfully boots AND a user successfully logs on interactively, the system commits the current configuration as the LastKnownGood control set. Specifically, it updates Select\\LastKnownGood to match Select\\Current.\n\n" +
            "If the machine BSODs repeatedly or fails to boot, the boot menu offers \"Last Known Good Configuration.\" Selecting this causes the boot loader (winload.efi) to load Select\\LastKnownGood instead of Select\\Current.\n\n" +
            "What this actually does:\n" +
            "  - Changes SYSTEM\\Select\\Current to the LastKnownGood value (e.g., 1 → 2)\n" +
            "  - The kernel then loads CurrentControlSet pointing at that older ControlSet\n" +
            "  - Services and drivers from the previous working config are used\n\n" +
            "Important caveats:\n" +
            "  - Only driver and service configuration is reverted — not installed software, user data, or application settings\n" +
            "  - If the bad driver was installed and the machine booted+logged-in successfully even once, LastKnownGood already includes the bad driver\n" +
            "  - Safe Mode is often more useful for diagnosing driver issues because it loads a minimal driver set regardless of ControlSet"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("HOW TO CHANGE THE ACTIVE CONTROL SET")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Option 1 — regedit (manual, dangerous):\n" +
            "  Navigate to HKLM\\SYSTEM\\Select. Change the Current DWORD to 1 or 2 to point to ControlSet001 or ControlSet002. This takes effect on the next boot.\n\n" +
            "Option 2 — Windows Recovery Environment:\n" +
            "  At boot, press F8 (or hold Shift on some systems) → \"Last Known Good Configuration\". The boot loader automatically changes Select\\Current.\n\n" +
            "Option 3 — bcdedit:\n" +
            "  bcdedit does not directly change ControlSet selection, but it modifies boot entries in the BCD store. For safe boot: bcdedit /set {current} safeboot minimal.\n\n" +
            "In practice:\n" +
            "  Manually editing Select\\Current in regedit is risky and rarely needed. The preferred recovery path is the Windows Recovery Environment or reverting a specific driver via Device Manager → Properties → Driver → Roll Back Driver."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHAT IS UNDER CONTROL?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "HKLM\\SYSTEM\\CurrentControlSet\\Control contains system-wide configuration that is not driver/service specific. Key subkeys:\n\n" +
            "ComputerName\n" +
            "  The machine's hostname (ComputerName value) and ActiveComputerName (the name actually in use this boot).\n\n" +
            "hivelist\n" +
            "  Lists all currently loaded hives and their file paths. Used internally by the kernel to track loaded hive files.\n\n" +
            "Session Manager\n" +
            "  BootExecute: programs run before the Win32 subsystem starts (e.g., autochk.exe for disk check).\n" +
            "  PendingFileRenameOperations: deferred file renames/deletes applied at next boot (used by installers).\n" +
            "  KnownDLLs: DLLs pre-loaded into the system process and shared as Section objects (avoids per-process reloading).\n\n" +
            "ServiceGroupOrder\n" +
            "  Defines the order in which service groups load. Drivers specify a Group value under their Services key; the SCM uses this list to sequence loading.\n\n" +
            "Class\n" +
            "  Device class GUIDs — one subkey per device class (e.g., {4D36E96B-E325-11CE-BFC1-08002BE10318} for keyboards). Contains UpperFilters and LowerFilters for class-wide filter drivers (see next section).\n\n" +
            "Lsa\n" +
            "  Local Security Authority configuration — authentication packages, security packages, RunAsPPL for lsass protection, notification packages."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHAT IS UNDER SERVICES?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "HKLM\\SYSTEM\\CurrentControlSet\\Services contains one subkey per installed service or kernel driver. Each subkey has these key values:\n\n" +
            "Start (DWORD): when to load\n" +
            "  0 = Boot (loaded by the boot loader, before kernel init)\n" +
            "  1 = System (loaded during kernel initialization, Phase 1)\n" +
            "  2 = Automatic (loaded by the SCM at startup)\n" +
            "  3 = Manual (loaded on demand)\n" +
            "  4 = Disabled\n\n" +
            "Type (DWORD): what kind\n" +
            "  1  = Kernel driver\n" +
            "  2  = File system driver\n" +
            "  4  = Adapter\n" +
            "  16 = Win32 own process (svchost or standalone)\n" +
            "  32 = Win32 shared process (shared svchost)\n\n" +
            "ImagePath (REG_EXPAND_SZ): path to the binary or driver file\n" +
            "ObjectName (REG_SZ): account the service runs as (LocalSystem, LocalService, NetworkService, or a domain account)\n" +
            "ErrorControl (DWORD): 0=ignore, 1=normal (log), 2=severe (switch to LastKnownGood), 3=critical (BSOD)\n\n" +
            "Example entry for the TCP/IP driver:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "HKLM\\SYSTEM\\CurrentControlSet\\Services\\Tcpip\n" +
            "  Start       = 1       (System — loads during kernel init)\n" +
            "  Type        = 1       (Kernel driver)\n" +
            "  ImagePath   = \"\\SystemRoot\\System32\\drivers\\tcpip.sys\"\n" +
            "  ObjectName  = \"LocalSystem\"\n" +
            "  ErrorControl= 1       (Normal)\n" +
            "  Group       = \"PNP_TDI\"  (load order group)\n" +
            "\n" +
            "  Parameters\\\n" +
            "    DataBasePath = \"%SystemRoot%\\System32\\drivers\\etc\"\n" +
            "    EnableICMPRedirect = 1\n" +
            "    TcpMaxDataRetransmissions = 5\n" +
            "    ...\n" +
            "\n" +
            "  Linkage\\\n" +
            "    Bind = {NIC adapter GUIDs}\n" +
            "    Export = \\Device\\Tcpip_{...}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("UPPER FILTERS AND LOWER FILTERS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "When the Plug and Play (PnP) manager builds the device stack for a device, it checks two locations for filter drivers to add:\n\n" +
            "1. Class-wide filters (apply to all devices of a class):\n" +
            "   HKLM\\SYSTEM\\CurrentControlSet\\Control\\Class\\{GUID}\n" +
            "   UpperFilters (REG_MULTI_SZ): list of service names to attach above the FDO\n" +
            "   LowerFilters (REG_MULTI_SZ): list of service names to attach below the FDO\n\n" +
            "2. Device-specific filters (apply to one device only):\n" +
            "   HKLM\\SYSTEM\\CurrentControlSet\\Enum\\<bus>\\<device>\\<instance>\n" +
            "   UpperFilters / LowerFilters (REG_MULTI_SZ): same format\n\n" +
            "Example: the disk device class GUID is {4D36E967-E325-11CE-BFC1-08002BE10318}. If you add a service name to the UpperFilters value there, that driver will be inserted into the device stack above every disk's FDO on the system.\n\n" +
            "This is how legacy AV and DLP products insert their filter drivers — they add themselves to the appropriate class's UpperFilters or LowerFilters during installation. The PnP manager reads these values when the device is enumerated and calls the filter driver's AddDevice to build the stack.\n\n" +
            "Class-wide filters affect every device of that class. Device-specific filters override or supplement for one device. The PnP manager merges both lists when building the stack: class lower filters → device lower filters → FDO → device upper filters → class upper filters."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHERE IS THE REGISTRY STORED?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The registry is backed by files called hive files:\n\n" +
            "  HKLM\\SYSTEM    → %SystemRoot%\\System32\\config\\SYSTEM\n" +
            "  HKLM\\SOFTWARE  → %SystemRoot%\\System32\\config\\SOFTWARE\n" +
            "  HKLM\\SAM       → %SystemRoot%\\System32\\config\\SAM\n" +
            "  HKLM\\SECURITY  → %SystemRoot%\\System32\\config\\SECURITY\n" +
            "  HKLM\\HARDWARE  → volatile, no file (rebuilt each boot)\n" +
            "  HKCU / HKU\\SID → %UserProfile%\\NTUSER.DAT\n" +
            "  HKCU\\Software\\Classes → %LocalAppData%\\Microsoft\\Windows\\UsrClass.dat\n\n" +
            "Each hive file has a corresponding transaction log:\n" +
            "  SYSTEM.LOG1 and SYSTEM.LOG2\n" +
            "  SOFTWARE.LOG1 and SOFTWARE.LOG2\n" +
            "  etc.\n\n" +
            "The LOG files are used for transactional journaling. When the kernel flushes a hive to disk, it first writes to a log file (so changes are atomic — either fully written or not written at all). This protects against corruption if the machine loses power mid-write."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHAT IS A HIVE?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "A hive is a self-contained registry subtree stored as a single file. The internal structure:\n\n" +
            "File format:\n" +
            "  - Starts with a 4096-byte base block (signature: \"regf\")\n" +
            "  - Followed by bins: 4096-byte (or larger) blocks of registry data\n" +
            "  - Each bin contains cells: variable-sized allocation units\n" +
            "  - Cells store: key nodes, value nodes, security descriptors, key names, value data\n\n" +
            "Loading:\n" +
            "  The kernel loads hives via NtLoadKey (or internally during boot). The hive file is mapped into kernel address space using a Section object (memory-mapped file) — the same mechanism used for executable images.\n\n" +
            "Cell allocation:\n" +
            "  The hive allocator manages cells within bins, similar to a heap. Free cells are tracked in a free list. When space runs out, the allocator grows the hive by adding new bins.\n\n" +
            "Security:\n" +
            "  Each key has an associated security descriptor stored as a security cell in the hive. The Object Manager enforces access checks against this descriptor when a process opens a registry key."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("BOOT AND REGISTRY AVAILABILITY")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The registry is needed to know which drivers to load — but the drivers are stored in files. This sounds like a bootstrapping problem. Here is how Windows actually solves it:\n\n" +
            "Phase 0 — Boot Loader (winload.efi):\n" +
            "  Before the Windows kernel starts, winload.efi runs. It has its own built-in minimal file system driver (NTFS and FAT) baked into the boot loader itself. winload.efi reads the SYSTEM hive directly from disk and uses it to determine which kernel and boot-start drivers (Start=0) to load into memory before handing control to the kernel.\n\n" +
            "Phase 1 — Early Kernel Init:\n" +
            "  The NT kernel starts with the SYSTEM hive already loaded (passed from winload.efi). The Configuration Manager (CmpInitializeRegistry) initializes the hive in memory and creates the CurrentControlSet symlink. The kernel can now use the registry.\n\n" +
            "Phase 2 — System-start Drivers (Start=1):\n" +
            "  The IoLoadDriver subsystem reads Services entries from the now-available registry and loads system-start drivers (including filesystem drivers like NTFS). Once NTFS is loaded, the system can open real files normally.\n\n" +
            "Phase 3 — Service Control Manager:\n" +
            "  The SCM starts as a user-mode process and loads auto-start (Start=2) services and drivers. At this point the full file system and network stack are available.\n\n" +
            "User hive loading:\n" +
            "  NTUSER.DAT is loaded by winlogon.exe / userinit.exe when a user logs on, using NtLoadKey. It is unloaded on logoff (with a delay to allow profile cleanup).\n\n" +
            "So there is no circular dependency: the boot loader breaks the chicken-and-egg problem by having its own filesystem code independent of the kernel driver stack."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DOES REGISTRY ACCESS TRIGGER FILE I/O?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "For reads: almost never.\n\n" +
            "Each hive file is memory-mapped by the kernel using a Section object (the same mechanism used for executables). When a registry key or value is read, the Configuration Manager directly reads from the in-memory image of the hive — no file I/O occurs. It is a pure memory access.\n\n" +
            "For writes: deferred and batched.\n\n" +
            "When a registry value is written (via RegSetValueEx or NtSetValueKey), the change is made to the in-memory hive image. The change is not immediately written to disk. The kernel periodically flushes dirty hive pages to disk — typically every few seconds or when the system is idle.\n\n" +
            "Flush mechanism:\n" +
            "  The flush uses a two-phase commit with the .LOG files to ensure atomicity:\n" +
            "  1. Write all changed cells to the .LOG file\n" +
            "  2. Mark the log as valid\n" +
            "  3. Write the changed cells to the actual hive file\n" +
            "  4. Mark the hive as clean\n" +
            "  If the machine crashes between steps 1 and 3, the log is replayed on next boot.\n\n" +
            "What filesystem minifilters see:\n" +
            "  Minifilters DO see IRP_MJ_READ and IRP_MJ_WRITE operations on hive files — but only when the kernel flushes dirty pages to disk. Normal registry reads via RegQueryValueEx do not produce any IRP to the filesystem layer at all. The hive is in memory; no file read IRP is generated for registry read operations.\n\n" +
            "This is why registry-intensive workloads do not generate proportional disk I/O — most operations are purely in-memory."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
