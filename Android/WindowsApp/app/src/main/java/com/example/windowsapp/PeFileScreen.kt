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
fun PeFileScreen(navController: NavController) {
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
            text = "PE FILE STRUCTURE AND LOADING",
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

        // PE FILE LAYOUT
        SectionHeader("PE FILE LAYOUT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "PE (Portable Executable) is the file format for .exe, .dll, .sys, and .ocx " +
            "files on Windows. It is derived from the Unix COFF format."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "┌─────────────────────────────┐  ← file offset 0\n" +
            "│  DOS Header  (64 bytes)     │  MZ signature + e_lfanew\n" +
            "│  DOS Stub    (~64 bytes)    │  \"This program cannot be run...\"\n" +
            "├─────────────────────────────┤  ← e_lfanew\n" +
            "│  PE Signature  (4 bytes)    │  \"PE\\0\\0\" = 0x00004550\n" +
            "│  File Header   (20 bytes)   │  IMAGE_FILE_HEADER\n" +
            "│  Optional Header            │  IMAGE_OPTIONAL_HEADER32/64\n" +
            "├─────────────────────────────┤\n" +
            "│  Section Table              │  array of IMAGE_SECTION_HEADER\n" +
            "├─────────────────────────────┤\n" +
            "│  .text  section             │  executable code\n" +
            "│  .rdata section             │  read-only data, imports, exports\n" +
            "│  .data  section             │  initialized read-write data\n" +
            "│  .reloc section             │  base relocation table\n" +
            "│  ... other sections ...     │\n" +
            "└─────────────────────────────┘"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // DOS HEADER
        SectionHeader("DOS HEADER AND PE SIGNATURE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "IMAGE_DOS_HEADER (the MZ header) is always the first 64 bytes of any PE file."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "typedef struct _IMAGE_DOS_HEADER {\n" +
            "    WORD e_magic;    // 0x5A4D = 'MZ' (Mark Zbikowski)\n" +
            "    ...              // fields used only for the DOS stub\n" +
            "    LONG e_lfanew;   // file offset of the PE signature\n" +
            "} IMAGE_DOS_HEADER;\n\n" +
            "// At offset e_lfanew:\n" +
            "DWORD Signature;     // 0x00004550 = 'PE\\0\\0'"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The DOS stub is a tiny real-mode program. When the file is run in DOS it " +
            "prints \"This program cannot be run in DOS mode\" and exits. Windows ignores it."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // FILE HEADER
        SectionHeader("FILE HEADER (IMAGE_FILE_HEADER)")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "typedef struct _IMAGE_FILE_HEADER {\n" +
            "    WORD  Machine;              // 0x8664 = AMD64  0x014C = x86\n" +
            "                               // 0xAA64 = ARM64\n" +
            "    WORD  NumberOfSections;\n" +
            "    DWORD TimeDateStamp;        // Unix timestamp of link time\n" +
            "    DWORD PointerToSymbolTable; // 0 for PE images (debug only)\n" +
            "    DWORD NumberOfSymbols;\n" +
            "    WORD  SizeOfOptionalHeader; // must match before parsing optional hdr\n" +
            "    WORD  Characteristics;\n" +
            "                               // 0x0002 IMAGE_FILE_EXECUTABLE_IMAGE\n" +
            "                               // 0x2000 IMAGE_FILE_DLL\n" +
            "                               // 0x0020 IMAGE_FILE_LARGE_ADDRESS_AWARE\n" +
            "} IMAGE_FILE_HEADER;"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // OPTIONAL HEADER
        SectionHeader("OPTIONAL HEADER")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Despite the name, the Optional Header is always present in PE images. It " +
            "differs between 32-bit (PE32) and 64-bit (PE32+) files."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Magic:\n" +
            "  0x010B  PE32   (32-bit, IMAGE_OPTIONAL_HEADER32)\n" +
            "  0x020B  PE32+  (64-bit, IMAGE_OPTIONAL_HEADER64)\n\n" +
            "Key fields:\n" +
            "  ImageBase           — preferred VA to load the image\n" +
            "                        EXE default: 0x0000000140000000 (x64)\n" +
            "                        DLL default: 0x0000000180000000 (x64)\n" +
            "  SizeOfImage         — total virtual size; must be page-aligned\n" +
            "  SizeOfHeaders       — combined size of headers; aligned to FileAlignment\n" +
            "  AddressOfEntryPoint — RVA of first instruction (main / DllMain / DriverEntry)\n" +
            "  SectionAlignment    — VA alignment of sections in memory (usually 0x1000)\n" +
            "  FileAlignment       — alignment of sections on disk (usually 0x200 or 0x1000)\n" +
            "  DataDirectory[16]   — array of { VirtualAddress, Size } for import table,\n" +
            "                        export table, relocations, TLS, resources, etc."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // COMMON SECTIONS
        SectionHeader("COMMON SECTIONS")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            ".text    RX   — compiled machine code\n" +
            ".rdata   R    — constants, string literals, imports, exports,\n" +
            "                exception handling tables\n" +
            ".data    RW   — initialized global/static variables\n" +
            ".bss     RW   — uninitialized data; zero-filled at load;\n" +
            "                no raw data in the file (SizeOfRawData = 0)\n" +
            ".reloc   R    — base relocation table (can be absent if ASLR stripped)\n" +
            ".rsrc    R    — resources: icons, dialogs, version info, manifests\n" +
            ".tls     RW   — thread-local storage initializers\n" +
            ".debug   R    — debug info (often stripped; separate .pdb file instead)\n" +
            ".edata   R    — export directory (often merged into .rdata)\n" +
            ".idata   RW   — import directory + IAT (often merged into .rdata/.data)"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // RVA
        SectionHeader("RVA (RELATIVE VIRTUAL ADDRESS)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "An RVA is an offset from the image's base address in virtual memory:\n\n" +
            "  VA (Virtual Address) = ImageBase + RVA\n\n" +
            "RVAs appear everywhere in the PE format: entry point, export table address, " +
            "import table address, section VirtualAddress, etc.\n\n" +
            "On disk the mapping is different because FileAlignment (disk) and " +
            "SectionAlignment (memory) can differ. To convert a file offset to an RVA:\n" +
            "  1. Find which section contains the file offset (PointerToRawData)\n" +
            "  2. delta = file_offset − section.PointerToRawData\n" +
            "  3. RVA = section.VirtualAddress + delta"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // EAT
        SectionHeader("EXPORT ADDRESS TABLE (EAT)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The EAT describes functions this module exports. It is pointed to by " +
            "DataDirectory[IMAGE_DIRECTORY_ENTRY_EXPORT]."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "typedef struct _IMAGE_EXPORT_DIRECTORY {\n" +
            "    DWORD  Name;                    // RVA of module name string\n" +
            "    DWORD  Base;                    // ordinal base (subtract to get index)\n" +
            "    DWORD  NumberOfFunctions;       // entries in AddressOfFunctions\n" +
            "    DWORD  NumberOfNames;           // entries in AddressOfNames\n" +
            "    DWORD  AddressOfFunctions;      // RVA → array of function RVAs\n" +
            "    DWORD  AddressOfNames;          // RVA → array of name string RVAs\n" +
            "    DWORD  AddressOfNameOrdinals;   // RVA → array of WORD ordinal indices\n" +
            "} IMAGE_EXPORT_DIRECTORY;"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Lookup by name (e.g., GetProcAddress):\n" +
            "  1. Binary search AddressOfNames for the target string\n" +
            "  2. Let i = found index → AddressOfNameOrdinals[i] = ordinal index k\n" +
            "  3. Function RVA = AddressOfFunctions[k]\n\n" +
            "Lookup by ordinal:\n" +
            "  k = ordinal − Base\n" +
            "  Function RVA = AddressOfFunctions[k]\n\n" +
            "Forwarder: if the function RVA points inside the export section itself " +
            "(between DataDirectory VirtualAddress and VirtualAddress+Size), it is a " +
            "string like \"NTDLL.RtlAllocateHeap\". The loader resolves it by loading that " +
            "DLL and looking up the named export."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // IAT
        SectionHeader("IMPORT ADDRESS TABLE (IAT)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The IAT describes functions this module imports from other DLLs. " +
            "DataDirectory[IMAGE_DIRECTORY_ENTRY_IMPORT] points to an array of " +
            "IMAGE_IMPORT_DESCRIPTORs (one per imported DLL), terminated by a zero entry."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "typedef struct _IMAGE_IMPORT_DESCRIPTOR {\n" +
            "    DWORD OriginalFirstThunk; // RVA → INT (Import Name Table)\n" +
            "                             // original names/ordinals; NEVER patched\n" +
            "    DWORD TimeDateStamp;\n" +
            "    DWORD ForwarderChain;\n" +
            "    DWORD Name;              // RVA → DLL name string (e.g. \"kernel32.dll\")\n" +
            "    DWORD FirstThunk;        // RVA → IAT; PATCHED by loader with addresses\n" +
            "} IMAGE_IMPORT_DESCRIPTOR;\n\n" +
            "Each IAT slot is an IMAGE_THUNK_DATA (pointer-sized):\n" +
            "  High bit = 1  → import by ordinal; low bits = ordinal number\n" +
            "  High bit = 0  → RVA to IMAGE_IMPORT_BY_NAME { WORD Hint; CHAR Name[]; }"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // PE LOADING STEPS
        SectionHeader("PE LOADING STEPS")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "1. Map the image\n" +
            "   ntdll loader calls NtMapViewOfSection. The PE is mapped into virtual\n" +
            "   memory: headers at ImageBase, each section at ImageBase + VirtualAddress.\n\n" +
            "2. Apply base relocations (if needed)\n" +
            "   If the image loaded at a different base than ImageBase (ASLR or conflict),\n" +
            "   the loader reads the .reloc section and applies delta to every absolute\n" +
            "   address listed:\n" +
            "     delta = actual_base - preferred_ImageBase\n" +
            "   Each IMAGE_BASE_RELOCATION block covers a 4KB page and lists the offsets\n" +
            "   within that page that contain absolute addresses to patch.\n\n" +
            "3. Resolve imports\n" +
            "   For each IMAGE_IMPORT_DESCRIPTOR:\n" +
            "     a. LoadLibrary the named DLL (recursive — may trigger more loading)\n" +
            "     b. For each slot in OriginalFirstThunk, look up the function in that\n" +
            "        DLL's EAT (by name or ordinal)\n" +
            "     c. Write the resolved absolute VA into the corresponding FirstThunk slot\n" +
            "   After this step, the IAT (FirstThunk array) contains real function addresses.\n\n" +
            "4. Initialize TLS\n" +
            "   If the image has a TLS directory, allocate TLS slots and copy initializers.\n\n" +
            "5. Call DllMain / entry point\n" +
            "   For DLLs: DllMain(DLL_PROCESS_ATTACH), in dependency order.\n" +
            "   For EXEs: transfer control to AddressOfEntryPoint."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // IAT PATCHING SUMMARY
        SectionHeader("IAT PATCHING SUMMARY")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "YES — the IAT is modified in memory. This is a key design point."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Before load (on disk / before imports resolved):\n" +
            "  FirstThunk[i]          = RVA to IMAGE_IMPORT_BY_NAME\n" +
            "  OriginalFirstThunk[i]  = RVA to IMAGE_IMPORT_BY_NAME  ← same\n\n" +
            "After load (imports resolved):\n" +
            "  FirstThunk[i]          = 0x00007FFF12345678  ← absolute VA of function\n" +
            "  OriginalFirstThunk[i]  = RVA (unchanged)     ← still the original name/ordinal\n\n" +
            "Call site in .text:\n" +
            "  call [rip + offset_to_iat_slot]   ; indirect through FirstThunk entry"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "IAT hooking exploits this: overwrite a FirstThunk entry to point to your " +
            "own function instead. The .text code path is unchanged — the hook is " +
            "transparent to the calling code. Security tools and detours libraries use " +
            "this technique. Windows places the IAT pages as readable/writable during " +
            "load, but after load they are often marked read-only by the loader."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
