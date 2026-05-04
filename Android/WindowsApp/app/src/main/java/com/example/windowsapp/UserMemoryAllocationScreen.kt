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
fun UserMemoryAllocationScreen(navController: NavController) {
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
            text = "MEMORY ALLOCATION",
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

        SectionHeader("BEHIND new AND malloc")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("C++ new and C malloc do not go directly to the OS. They call the CRT allocator, which calls HeapAlloc on the process default heap. HeapAlloc then carves out a sub-region from memory it previously obtained via VirtualAlloc.\n\nGetProcessHeap() returns the handle to the default process heap — the same heap that new/malloc use.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// These are equivalent at the OS level:\n" +
            "int* p1 = new int(42);           // C++\n" +
            "int* p2 = (int*)malloc(sizeof(int)); // C\n" +
            "int* p3 = (int*)HeapAlloc(       // Win32\n" +
            "    GetProcessHeap(), 0, sizeof(int));"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("HEAPALLOC / HEAPFREE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("HeapCreate: create a private heap (isolated from the default heap — useful for leak detection or per-component isolation).\n\nHeapAlloc: allocate a block. HEAP_ZERO_MEMORY zeroes it before returning.\n\nHeapFree: free a block. HEAP_VALIDATE_PARAMETERS flag available in debug builds.\n\nHeapReAlloc: resize a block in-place if possible; moves it otherwise.\n\nHeapDestroy: destroy the entire heap in one call — frees all blocks at once.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Create a private heap (growable)\n" +
            "HANDLE hHeap = HeapCreate(0, 0, 0);\n\n" +
            "// Allocate 1 KB, zeroed\n" +
            "void* p = HeapAlloc(hHeap,\n" +
            "    HEAP_ZERO_MEMORY, 1024);\n\n" +
            "// Resize to 2 KB\n" +
            "p = HeapReAlloc(hHeap, 0, p, 2048);\n\n" +
            "// Free one block\n" +
            "HeapFree(hHeap, 0, p);\n\n" +
            "// Destroy heap — frees everything\n" +
            "HeapDestroy(hHeap);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("VIRTUALALLOC / VIRTUALFREE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("VirtualAlloc works at page granularity (4 KB pages, 64 KB allocation granularity). It is lower-level than the heap and suitable for large buffers, executable memory, or when you need precise page-level control.\n\nflAllocationType:\n• MEM_RESERVE — reserve address space without mapping physical memory\n• MEM_COMMIT — map pages and make them accessible\n• MEM_RESERVE | MEM_COMMIT — do both at once\n\nflProtect: PAGE_READWRITE, PAGE_READONLY, PAGE_EXECUTE_READ, PAGE_NOACCESS, PAGE_GUARD, etc.\n\nVirtualFree:\n• MEM_DECOMMIT — release physical pages but keep the reservation\n• MEM_RELEASE — release the entire reservation (dwSize must be 0)")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Reserve 10 MB of address space\n" +
            "void* base = VirtualAlloc(\n" +
            "    NULL,\n" +
            "    10 * 1024 * 1024,\n" +
            "    MEM_RESERVE,\n" +
            "    PAGE_READWRITE);\n\n" +
            "// Commit the first 4 KB\n" +
            "void* p = VirtualAlloc(\n" +
            "    base, 4096,\n" +
            "    MEM_COMMIT, PAGE_READWRITE);\n\n" +
            "// ... use the committed pages ...\n\n" +
            "// Decommit (keep reservation)\n" +
            "VirtualFree(base, 4096, MEM_DECOMMIT);\n\n" +
            "// Release the entire reservation\n" +
            "VirtualFree(base, 0, MEM_RELEASE);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("VIRTUALQUERY / VIRTUALQUERYEX")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Query the state of a region in the virtual address space. Useful for memory mapping, introspection, and anti-cheat tools.\n\nVirtualQuery: queries the calling process.\nVirtualQueryEx: cross-process query — requires an open handle to the target process with PROCESS_QUERY_INFORMATION.\n\nFills MEMORY_BASIC_INFORMATION:\n• BaseAddress / RegionSize — contiguous region with the same attributes\n• State: MEM_FREE, MEM_RESERVE, MEM_COMMIT\n• Protect: current page protection flags\n• Type: MEM_IMAGE, MEM_MAPPED, MEM_PRIVATE\n\nWalk the entire address space by repeatedly calling VirtualQuery and advancing by mbi.RegionSize:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "MEMORY_BASIC_INFORMATION mbi;\n" +
            "PVOID addr = NULL;\n" +
            "while (VirtualQuery(addr, &mbi,\n" +
            "                    sizeof(mbi))) {\n" +
            "    // inspect mbi.State, mbi.Type, etc.\n" +
            "    addr = (PBYTE)mbi.BaseAddress\n" +
            "         + mbi.RegionSize;\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ALLOCATION HIERARCHY")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("From highest-level to lowest:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "new / malloc / calloc / realloc\n" +
            "    └─ HeapAlloc (CRT or private heap)\n" +
            "           └─ VirtualAlloc\n" +
            "                  └─ NtAllocateVirtualMemory\n" +
            "                         └─ kernel VAD/PTE"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("HeapAlloc is efficient for small, frequent allocations — it sub-allocates from large VirtualAlloc regions and maintains free-lists.\n\nVirtualAlloc is appropriate for:\n• Large buffers (>= ~1 MB)\n• Executable memory (PAGE_EXECUTE_READ)\n• Guard pages and custom memory regions\n• When you need MEM_RESERVE without committing")

        Spacer(modifier = Modifier.height(32.dp))
        HackerButton("< BACK") { navController.popBackStack() }
    }
}
