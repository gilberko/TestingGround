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
fun DLLsScreen(navController: NavController) {
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
            text = "DLLs",
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

        SectionHeader("WHAT IS A DLL?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A Dynamic Link Library (DLL) is a PE file (same format as .exe) that contains code, data, and resources that can be shared across multiple processes. DLLs export functions and variables; processes import them at load time (implicit linking) or on demand at runtime (explicit linking via LoadLibrary / GetProcAddress).")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The key purpose of a DLL over a static library: code deduplication in RAM. If 100 processes use kernel32.dll, there is only one copy of kernel32.dll's code pages in physical RAM — not 100.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("LOADING A DLL")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("When a process calls LoadLibrary (or the loader implicitly loads a DLL at process startup), the loader:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "1. Checks PEB.Ldr lists (InLoadOrderModuleList,\n" +
            "   InMemoryOrderModuleList) to see if the DLL is\n" +
            "   already loaded in this process.\n" +
            "   -> If yes: increment reference count, return handle.\n\n" +
            "2. Opens the DLL file and checks the kernel's Section\n" +
            "   object cache. If another process already created\n" +
            "   a Section for this file, the same Section is reused.\n\n" +
            "3. Maps the Section into the process's address space\n" +
            "   (MapViewOfSection internally). This creates virtual\n" +
            "   address mappings pointing to the shared physical pages.\n\n" +
            "4. Applies relocations if the DLL could not load at its\n" +
            "   preferred base address. Affected pages get COW copies.\n\n" +
            "5. Resolves imports (fixes up the IAT).\n\n" +
            "6. Calls DllMain(DLL_PROCESS_ATTACH)."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("HOW WINDOWS KNOWS A DLL IS ALREADY LOADED")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Two separate tracking mechanisms work at two levels:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Per-process level (user mode):\n" +
            "  PEB.Ldr->InLoadOrderModuleList -- a doubly-linked list\n" +
            "  of LDR_DATA_TABLE_ENTRY structures, one per loaded DLL.\n" +
            "  LoadLibrary checks this list first. If found:\n" +
            "  increment LdrEntry->ReferenceCount, return hModule.\n\n" +
            "System-wide level (kernel):\n" +
            "  The file object's Section (file mapping) is cached.\n" +
            "  All processes mapping the same DLL file share the same\n" +
            "  Section object, which means they share the same physical\n" +
            "  pages for the DLL's code and read-only data sections."
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("ASLR note: Windows ASLR for system DLLs is per-boot, not per-process. At each boot, ntdll.dll, kernel32.dll, etc. are randomized to a new base address — but all processes on that boot use the same base. This allows full physical page sharing without relocation (no COW copies needed for code pages).")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SHARING PHYSICAL PAGES")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("When process A and process B both load kernel32.dll, the code pages of kernel32.dll are shared in physical RAM. Both processes' page tables map different virtual addresses to the same physical frames.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Process A virtual space:\n" +
            "  0x00007FF800000000 -> physical frame 0x1A2B3\n\n" +
            "Process B virtual space:\n" +
            "  0x00007FF800000000 -> physical frame 0x1A2B3\n" +
            "                        (SAME physical frame)\n\n" +
            "RAM:\n" +
            "  Frame 0x1A2B3: [kernel32.dll code page]\n" +
            "                  -- one copy, shared by both"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The DLL is NOT loaded from scratch for each process. Only a new virtual address mapping is created (cheap). The physical pages are already resident if any other process loaded the DLL recently.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("MODIFICATION AND COPY-ON-WRITE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("DLL pages are mapped as copy-on-write (COW). If a process writes to a DLL page (e.g., patching the IAT, hooking a function, or writing to the DLL's global data section), the write is NOT blocked — it succeeds, but through the COW mechanism:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "1. Process attempts to write to a DLL page.\n" +
            "2. CPU raises #PF (write to a read-only/COW page).\n" +
            "3. Page fault handler (MmAccessFault) detects COW flag.\n" +
            "4. Allocates a new private physical frame.\n" +
            "5. Copies the original shared page into the private frame.\n" +
            "6. Updates the faulting process's PTE to point to\n" +
            "   the private frame (now writable).\n" +
            "7. Write proceeds -- succeeds for this process only.\n\n" +
            "Other processes:\n" +
            "   Still mapped to the original shared frame.\n" +
            "   They see NO change."
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("So: is modification always blocked? No — COW-mapped writes always succeed. The modification is simply isolated to the writing process. Other processes continue to see the original unmodified DLL code.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The only DLL pages that are truly shared and writable (visible to all) are those in explicitly shared sections — a DLL can declare a section with the SHARED attribute in its .def file or with #pragma data_seg. These pages are not COW and writes are visible to all processes that loaded the DLL. This is rarely used.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Verifying page type with VirtualQuery:\n" +
            "MEMORY_BASIC_INFORMATION mbi;\n" +
            "VirtualQuery(address, &mbi, sizeof(mbi));\n\n" +
            "// For a normal DLL code page:\n" +
            "// mbi.Type    == MEM_IMAGE\n" +
            "// mbi.Protect == PAGE_EXECUTE_WRITECOPY (before first write)\n" +
            "//             or PAGE_EXECUTE_READ_WRITE (after COW triggered)"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("FreeLibrary AND REFERENCE COUNTING")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("LoadLibrary increments a per-process reference count in the LDR_DATA_TABLE_ENTRY. FreeLibrary decrements it. When the count reaches zero, the loader calls DllMain(DLL_PROCESS_DETACH), unmaps the Section view, and removes the entry from PEB.Ldr.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The Section object itself (in the kernel) has its own reference count. When all processes unmap it AND no file handles remain, the Section is released and the physical pages can be reclaimed. Until then, the pages remain in the standby list and will be quickly re-mapped if any process reloads the DLL.")

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
