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
fun DriversAndObjectsScreen(navController: NavController) {
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
            text = "WORKING WITH DRIVERS\nAND OBJECTS",
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

        // 1. LOADING AND UNLOADING A DRIVER
        SectionHeader("LOADING AND UNLOADING A DRIVER")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Two ways to load/unload a driver programmatically:\n\n" +
            "1) The SCM path (what sc.exe wraps): CreateService with " +
            "SERVICE_KERNEL_DRIVER, then StartService / ControlService(SERVICE_CONTROL_STOP). " +
            "This is the standard, supported route for installable drivers.\n\n" +
            "2) The native path: ZwLoadDriver / ZwUnloadDriver (callable from kernel mode — " +
            "see NT... VS ZW... for the general Nt/Zw rule) and their user-mode counterparts " +
            "NtLoadDriver / NtUnloadDriver. Both take a single UNICODE_STRING: the path to the " +
            "driver's registry Services key, not a file path."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "UNICODE_STRING regPath;\n" +
            "RtlInitUnicodeString(&regPath,\n" +
            "    L\"\\\\Registry\\\\Machine\\\\System\\\\CurrentControlSet\\\\Services\\\\MyDriver\");\n\n" +
            "NTSTATUS status = ZwLoadDriver(&regPath);\n" +
            "// ... later ...\n" +
            "status = ZwUnloadDriver(&regPath);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "From user mode, NtLoadDriver additionally requires SE_LOAD_DRIVER_NAME " +
            "privilege to be enabled on the calling thread's token via AdjustTokenPrivileges " +
            "— by default only Administrators hold SeLoadDriverPrivilege.\n\n" +
            "Either path ultimately fails to unload if DriverUnload is NULL on the target " +
            "DRIVER_OBJECT (see DRIVER_OBJECT — DRIVER UNLOAD): the driver stays loaded until reboot."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 2. MINIFILTERS - LOAD AND UNLOAD
        SectionHeader("MINIFILTERS — LOAD AND UNLOAD")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Minifilters are not loaded/unloaded with ZwLoadDriver/ZwUnloadDriver directly — " +
            "they go through the Filter Manager instead. Three equivalent fronts for the " +
            "same operation:\n\n" +
            "  fltmc load <name>  /  fltmc unload <name>   — command-line tool\n" +
            "  FilterLoad(name)   /  FilterUnload(name)     — user-mode API (fltlib.h)\n" +
            "  FltLoadFilter(name) / FltUnloadFilter(name)  — kernel-callable equivalents\n\n" +
            "FilterLoad still loads the underlying service via the SCM, then attaches it to " +
            "the Filter Manager port. Both load and unload require SeLoadDriverPrivilege."
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Important gotcha: a minifilter that does not register a FilterUnloadCallback " +
            "(PFLT_FILTER_UNLOAD_CALLBACK, set via FLT_REGISTRATION) cannot be unloaded at " +
            "all — FltUnloadFilter/FilterUnload will fail. The callback is responsible for " +
            "closing any communication ports and calling FltUnregisterFilter."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 3. OBJECT NAMES AND HIERARCHY
        SectionHeader("LOOKING UP OBJECTS BY NAME")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Yes — and yes, there is a hierarchy. Names live in a tree of Directory objects " +
            "rooted at \\ (see OBJECTS OVERVIEW — NAMED OBJECT DIRECTORIES for the full list: " +
            "\\Driver, \\Device, \\FileSystem, etc.). A driver object's full name looks like " +
            "\\Driver\\MyDriver; a device object's looks like \\Device\\MyDevice. Browse the " +
            "live tree with Sysinternals WinObj or WinDbg's \"!object \\\".\n\n" +
            "How you resolve a name to a pointer depends on the type:\n\n" +
            "  Device objects (documented): IoGetDeviceObjectPointer — see CREATING AND " +
            "SENDING IRPs, already covers this.\n\n" +
            "  Any named object type (undocumented but very widely used): " +
            "ObReferenceObjectByName. OBJECTS OVERVIEW already shows this for " +
            "*IoDeviceObjectType. The driver-object case is the same call with a different " +
            "type tag and path:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "UNICODE_STRING name =\n" +
            "    RTL_CONSTANT_STRING(L\"\\\\Driver\\\\MyDriver\");\n" +
            "PDRIVER_OBJECT drvObj = NULL;\n\n" +
            "NTSTATUS status = ObReferenceObjectByName(\n" +
            "    &name,\n" +
            "    OBJ_CASE_INSENSITIVE,\n" +
            "    NULL,\n" +
            "    0,\n" +
            "    *IoDriverObjectType,   // must match the object's real type\n" +
            "    KernelMode,\n" +
            "    NULL,\n" +
            "    (PVOID*)&drvObj);\n\n" +
            "// Wrong type tag here returns STATUS_OBJECT_TYPE_MISMATCH.\n" +
            "if (NT_SUCCESS(status)) {\n" +
            "    // use drvObj\n" +
            "    ObDereferenceObject(drvObj);\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 4. REFERENCE COUNTING RECAP
        SectionHeader("DO YOU NEED TO REFERENCE / DEREFERENCE IT?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Yes. Every object the Object Manager hands you this way is reference-counted " +
            "(PointerCount/HandleCount in OBJECT_HEADER). The lookup call itself already " +
            "takes a reference on your behalf — you must release it with exactly one " +
            "ObDereferenceObject when you're done. Full detail on the counter and the " +
            "pairing rule is in OBJECTS OVERVIEW — ObReferenceObject FAMILY; this is just " +
            "the reminder that it applies here too."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 5. MmGetSystemRoutineAddress
        SectionHeader("FINDING A FUNCTION'S ADDRESS — MmGetSystemRoutineAddress")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Yes, this one is real and documented (wdm.h, exported from ntoskrnl.exe):\n\n" +
            "  PVOID MmGetSystemRoutineAddress(PUNICODE_STRING SystemRoutineName);\n\n" +
            "Must be called at PASSIVE_LEVEL. It only resolves routines exported by " +
            "ntoskrnl.exe or hal.dll — it cannot find routines internal to those images " +
            "(not exported) or routines belonging to any other driver. Returns NULL if the " +
            "name isn't found.\n\n" +
            "When it's used: to call a kernel/HAL routine that only exists on some Windows " +
            "versions, without creating a hard load-time dependency. If you instead just " +
            "import the function normally and link against it, your driver fails to load " +
            "entirely on any system missing that export. Resolving it at runtime lets you " +
            "check for NULL and fall back gracefully."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "UNICODE_STRING fnName =\n" +
            "    RTL_CONSTANT_STRING(L\"ExSomeNewerRoutine\");\n" +
            "PVOID fn = MmGetSystemRoutineAddress(&fnName);\n" +
            "if (fn != NULL) {\n" +
            "    // safe to call on this OS version\n" +
            "} else {\n" +
            "    // fall back — not available here\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 6. PARSING ANOTHER DRIVER'S EXPORT TABLE
        SectionHeader("PARSING ANOTHER DRIVER'S EXPORT TABLE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Yes, and this is a different technique from MmGetSystemRoutineAddress because " +
            "it works on any module's base address, not just ntoskrnl.exe/hal.dll. Given a " +
            "base address, walk its own PE headers:\n\n" +
            "  1. RtlImageDirectoryEntryToData(base, TRUE, IMAGE_DIRECTORY_ENTRY_EXPORT, " +
            "&size) → IMAGE_EXPORT_DIRECTORY\n" +
            "  2. Search AddressOfNames for the target name (RVA array of name strings)\n" +
            "  3. Use the matching index into AddressOfNameOrdinals to get the ordinal\n" +
            "  4. Use that ordinal to index AddressOfFunctions → function RVA\n" +
            "  5. Function address = base + RVA\n\n" +
            "Caveat: this only ever reveals exported symbols. Internal/static functions " +
            "never appear in AddressOfNames at all, so neither this technique nor " +
            "MmGetSystemRoutineAddress can find them — that requires pattern/signature " +
            "scanning, which is out of scope here."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 7. CAN A DRIVER LOAD A DLL?
        SectionHeader("CAN A DRIVER LOAD/UNLOAD A \"DLL\"?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Not the way a user-mode program does. LoadLibrary is a user-mode-only " +
            "mechanism tied to a process's PEB and loader data structures — there's no " +
            "kernel-mode equivalent you can call.\n\n" +
            "What actually happens when one kernel image depends on another is ordinary " +
            "static PE linking: your driver imports functions from another kernel image, " +
            "and the system's own internal loader resolves that dependency automatically " +
            "before your DriverEntry runs, and tears it down when no longer needed. " +
            "Internally this goes through MmLoadSystemImage / MmUnloadSystemImage — real " +
            "functions, but NOT exported from ntoskrnl.exe, so third-party code cannot " +
            "call them directly; only the system loader itself uses them. HAL.DLL is the " +
            "canonical real example of a .dll-extension image loaded this way at boot.\n\n" +
            "Note for awareness only: manually invoking this internal loading path at " +
            "runtime to map an arbitrary image (rather than relying on the static-link " +
            "dependency mechanism) is a known historical technique from rootkit research. " +
            "It requires resolving an unexported, version-dependent function via signature " +
            "scanning, is fragile across Windows builds, and is blocked on modern x64 " +
            "Windows by Driver Signature Enforcement. No working implementation is given here."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 8. CREATING A LOADABLE KERNEL LIBRARY
        SectionHeader("CREATING A LOADABLE KERNEL \"LIBRARY\"")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Two different things get called a \"kernel DLL\", and they're not the same:\n\n" +
            "1) WDK's \"Kernel-Mode Driver — Library\" project type produces a .lib. This is " +
            "compile-time only — the object code is merged directly into whatever driver " +
            "links it. It is never itself a runtime-loaded image, so there's nothing to " +
            "load or unload.\n\n" +
            "2) The practical way to share kernel code at runtime between separately-built " +
            "drivers: write it as an ordinary driver (.sys) that exports functions via a " +
            ".def file, and ship a .lib import library for consumers to link against at " +
            "build time. The standard driver-dependency loading mechanism above (#7) then " +
            "loads/unloads it automatically alongside whatever depends on it — you don't " +
            "call any load/unload API yourself for this case.\n\n" +
            "True dynamically-loaded .dll-extension kernel images (like HAL.DLL) are an " +
            "OS-internal boot mechanism, not something third-party drivers realistically " +
            "create."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 9. DRIVEROBJECT BASE ADDRESS
        SectionHeader("DOES DRIVER_OBJECT HAVE THE BASE ADDRESS?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Yes — already covered in DRIVER_OBJECT — KEY FIELDS: DriverStart (base VA) " +
            "and DriverSize. DriverStart is exactly the base address the export-table walk " +
            "above needs — pass it straight into RtlImageDirectoryEntryToData to parse a " +
            "driver's own exports once you have its DRIVER_OBJECT."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 10. FINDING A DRIVER'S BASE ADDRESS GENERALLY
        SectionHeader("FINDING A DRIVER'S BASE ADDRESS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "If you already have its DRIVER_OBJECT (e.g. via ObReferenceObjectByName " +
            "above), just read DriverStart directly — simplest and safest.\n\n" +
            "If you don't have the object yet, the practical route is " +
            "ZwQuerySystemInformation(SystemModuleInformation, ...), which fills an " +
            "RTL_PROCESS_MODULES buffer — an array of RTL_PROCESS_MODULE_INFORMATION, each " +
            "with ImageBase, ImageSize, and FullPathName. Loop the array and match by name.\n\n" +
            "The more fragile, classic alternative is walking PsLoadedModuleList directly " +
            "(the same list DriverSection points into, below) — it's unexported and " +
            "officially unsupported, and it's also the structure rootkits unlink an entry " +
            "from to hide a loaded driver (DKOM). Prefer ZwQuerySystemInformation."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 11. DRIVERSECTION FIELD
        SectionHeader("THE DriverSection FIELD")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "DRIVER_OBJECT::DriverSection points to an LDR_DATA_TABLE_ENTRY — the loader's " +
            "own bookkeeping record for the loaded image (base address, image size, full " +
            "and base DLL name, load-order links). It lives in the doubly-linked list " +
            "rooted at PsLoadedModuleList, alongside every other loaded driver's entry.\n\n" +
            "Despite the name, it is NOT a live Section object. \"DriverSection\" is " +
            "historically confusing terminology, not a pointer to a SECTION/CONTROL_AREA. " +
            "It's purely the loader's module-list entry — a different kind of structure " +
            "from the Section objects described next."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 12. SECTION OBJECTS
        SectionHeader("WHAT ARE SECTION OBJECTS?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "A Section object is the kernel's memory-mapped-file abstraction — created with " +
            "NtCreateSection (user mode) / MmCreateSection (kernel mode). Two kinds:\n\n" +
            "  Pagefile-backed — anonymous shared memory between processes (the kernel " +
            "equivalent of CreateFileMapping with INVALID_HANDLE_VALUE).\n" +
            "  File-backed — reflects the contents of a real file on disk.\n\n" +
            "Internally it's built from a SECTION object plus a CONTROL_AREA (nonpaged — " +
            "holds the I/O-relevant state) and one or more SEGMENT/SUBSECTION structures. " +
            "Only one CONTROL_AREA exists per file per mapping type, shared by every process " +
            "that maps it — which is exactly the mechanism behind PAGING I/O's \"one copy of " +
            "each file page in RAM, shared across mappers\" (see PAGING I/O — THE VIRTUAL " +
            "MEMORY AND FILE CACHE CONNECTION for that side of the story).\n\n" +
            "To be clear, since the names invite confusion: a Section object is this " +
            "memory-mapping object; DriverSection (above) is a pointer to an unrelated " +
            "loader bookkeeping structure (LDR_DATA_TABLE_ENTRY) that merely has \"Section\" " +
            "in its name."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
