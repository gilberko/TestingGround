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
fun MemoryAllocationScreen(navController: NavController) {
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

        SectionHeader("POOL ALLOCATION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Kernel pool is the kernel's heap — a set of pre-allocated memory regions managed by the memory manager. The modern API is ExAllocatePool2 (Windows 10 1803+, replaces the deprecated ExAllocatePoolWithTag).")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "PVOID ExAllocatePool2(\n" +
            "    POOL_FLAGS Flags,\n" +
            "    SIZE_T     NumberOfBytes,\n" +
            "    ULONG      Tag);        // 4-char tag, e.g. 'yDrM'\n\n" +
            "// Always returns zeroed memory (unlike old API)\n" +
            "// Returns NULL on failure (never throws)\n\n" +
            "ExFreePoolWithTag(Ptr, Tag); // free; Tag must match"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Pool types (POOL_FLAGS):")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "POOL_FLAG_NON_PAGED           // always resident in RAM;\n" +
            "                              //   safe at any IRQL\n" +
            "POOL_FLAG_PAGED               // can be paged out;\n" +
            "                              //   only safe at PASSIVE/APC_LEVEL\n" +
            "POOL_FLAG_NON_PAGED_EXECUTE   // non-paged + executable\n" +
            "                              //   (for dynamically generated code)\n" +
            "POOL_FLAG_SESSION_NON_PAGED   // non-paged, per-session\n" +
            "                              //   (win32k.sys, Terminal Services)\n" +
            "POOL_FLAG_SESSION_PAGED       // paged, per-session\n" +
            "POOL_FLAG_UNINITIALIZED       // skip zeroing (faster, use carefully)"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Non-paged pool is a finite and precious resource. Allocate only what you need, free it promptly, and use paged pool whenever your code runs at PASSIVE_LEVEL (which is most of the time in dispatch routines and worker threads).")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("LOOK-ASIDE LISTS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("For drivers that frequently allocate and free fixed-size objects, look-aside lists provide a per-CPU free list that avoids returning blocks to the global pool on every free. This significantly reduces contention and improves performance.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "LOOKASIDE_LIST_EX lal;\n\n" +
            "ExInitializeLookasideListEx(\n" +
            "    &lal,\n" +
            "    NULL, NULL,             // optional alloc/free callbacks\n" +
            "    NonPagedPool,           // or PagedPool\n" +
            "    0,                      // flags\n" +
            "    sizeof(MY_OBJECT),\n" +
            "    'ylAM',                 // tag\n" +
            "    0);                     // depth (0 = OS default)\n\n" +
            "MY_OBJECT* obj = ExAllocateFromLookasideListEx(&lal);\n" +
            "ExFreeToLookasideListEx(&lal, obj);\n\n" +
            "ExDeleteLookasideListEx(&lal); // cleanup on driver unload"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("CONTIGUOUS AND NON-CACHED MEMORY")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("For DMA devices that require physically contiguous memory (not scattered across RAM pages):")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Physically contiguous non-paged memory:\n" +
            "PHYSICAL_ADDRESS highAddr = { .QuadPart = 0xFFFFFFFF };\n" +
            "PVOID buf = MmAllocateContiguousMemory(Size, highAddr);\n" +
            "MmFreeContiguousMemory(buf);\n\n" +
            "// Memory-mapped I/O (non-cached, non-paged):\n" +
            "PVOID buf = MmAllocateNonCachedMemory(Size);\n" +
            "MmFreeNonCachedMemory(buf, Size);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ZwAllocateVirtualMemory")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("ZwAllocateVirtualMemory allocates virtual address space in any process (or the current process). It is the kernel equivalent of VirtualAlloc and works at page granularity.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "PVOID  base = NULL;       // NULL = OS picks address\n" +
            "SIZE_T size = 0x1000;     // 1 page; rounds UP to page size\n\n" +
            "NTSTATUS status = ZwAllocateVirtualMemory(\n" +
            "    NtCurrentProcess(),   // or any process handle\n" +
            "    &base,                // in/out: requested/actual base\n" +
            "    0,                    // ZeroBits (almost always 0)\n" +
            "    &size,                // in/out: requested/actual size\n" +
            "    MEM_RESERVE | MEM_COMMIT,  // allocType\n" +
            "    PAGE_READWRITE);      // protection"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Does it work in full pages? Yes — address is rounded DOWN to allocation granularity (64 KB for new reservations), and RegionSize is rounded UP to page size (4 KB). You always commit/reserve whole pages even if you request 1 byte.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("AllocationType flags:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "MEM_RESERVE              // reserve address space only;\n" +
            "                         //   no RAM or pagefile backing\n" +
            "MEM_COMMIT               // commit (allocate backing);\n" +
            "                         //   region must already be reserved\n" +
            "MEM_RESERVE | MEM_COMMIT // reserve + commit in one call\n" +
            "MEM_RESET                // mark pages as discardable\n" +
            "MEM_TOP_DOWN             // allocate from high addresses"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Protection flags for VirtualMemory (PAGE_* constants):")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "PAGE_READWRITE           // read + write\n" +
            "PAGE_READONLY            // read only\n" +
            "PAGE_EXECUTE_READ        // read + execute\n" +
            "PAGE_EXECUTE_READWRITE   // read + write + execute\n" +
            "PAGE_NOACCESS            // no access at all\n" +
            "PAGE_GUARD               // guard page (fires exception on first access)\n" +
            "PAGE_NOCACHE             // disable CPU cache for this region"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ZwFreeVirtualMemory")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Releases or decommits virtual memory previously allocated with ZwAllocateVirtualMemory.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "SIZE_T size = 0; // must be 0 for MEM_RELEASE\n\n" +
            "// Fully release (decommit + unreserve):\n" +
            "ZwFreeVirtualMemory(\n" +
            "    NtCurrentProcess(), &base, &size, MEM_RELEASE);\n\n" +
            "// Decommit only (keep reserved, free backing):\n" +
            "SIZE_T decommitSize = 0x1000;\n" +
            "ZwFreeVirtualMemory(\n" +
            "    NtCurrentProcess(), &base, &decommitSize, MEM_DECOMMIT);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ZwProtectVirtualMemory")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Changes the protection on a region of committed pages. You can, for example, allocate a buffer as PAGE_READWRITE, write code into it, then change it to PAGE_EXECUTE_READ before executing it (W^X pattern).")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "ULONG oldProtect;\n" +
            "SIZE_T protSize = 0x1000;\n\n" +
            "ZwProtectVirtualMemory(\n" +
            "    NtCurrentProcess(),\n" +
            "    &base,            // rounded down to page boundary\n" +
            "    &protSize,        // rounded up to page size\n" +
            "    PAGE_EXECUTE_READ,\n" +
            "    &oldProtect);     // receives previous protection"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Yes — you can change page permissions at any time on committed pages. This is how JIT compilers generate executable code, and it's also how runtime packers work. Note: on systems with CET (Control-flow Enforcement Technology), making previously-writable pages executable may be restricted.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ZwQueryVirtualMemory")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Queries the state and protection of a virtual address range. The main use is MemoryBasicInformation, which returns MEMORY_BASIC_INFORMATION:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "MEMORY_BASIC_INFORMATION mbi;\n" +
            "SIZE_T returnLen;\n\n" +
            "ZwQueryVirtualMemory(\n" +
            "    NtCurrentProcess(),\n" +
            "    QueryAddress,\n" +
            "    MemoryBasicInformation,\n" +
            "    &mbi,\n" +
            "    sizeof(mbi),\n" +
            "    &returnLen);\n\n" +
            "// mbi fields:\n" +
            "// .BaseAddress    -- start of this region\n" +
            "// .RegionSize     -- size of this contiguous region\n" +
            "// .State          -- MEM_COMMIT, MEM_RESERVE, MEM_FREE\n" +
            "// .Protect        -- current page protection (PAGE_*)\n" +
            "// .Type           -- MEM_PRIVATE, MEM_MAPPED, MEM_IMAGE"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("MEM_PRIVATE: process-private allocation. MEM_MAPPED: memory-mapped file. MEM_IMAGE: mapped from a PE image (DLL or EXE). By walking consecutive addresses with ZwQueryVirtualMemory you can enumerate the entire virtual address space of any accessible process.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHAT IS NUMA")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("NUMA (Non-Uniform Memory Access): multi-socket machines give each CPU socket its own local RAM bank. Accessing remote RAM (another socket's bank) costs more latency and bandwidth — typically 20–50% higher latency on a 2-socket server.\n\nThe Windows kernel is NUMA-aware and uses this topology when managing pool memory. Regular pool allocations (ExAllocatePool2) are automatically NUMA-local: the memory manager prefers to satisfy an allocation from the NUMA node the calling CPU belongs to. You get good locality for free as long as your worker thread stays on the right node.\n\nFor physically contiguous memory (DMA buffers), explicit node targeting is available via MmAllocateContiguousNodeMemory. Use KeSetSystemGroupAffinityThread to keep a thread bound to a specific node's CPUs.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("NUMA KERNEL APIs")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("KeQueryHighestNodeNumber: returns the highest NUMA node index (zero-based). Add 1 for the total count.\n\nKeGetCurrentNodeNumber: returns the NUMA node of the CPU currently executing the calling thread. Use this to allocate from the same node as your thread.\n\nKeQueryGroupAffinity: given a NUMA node number, returns a GROUP_AFFINITY mask of the CPUs on that node.\n\nKeSetSystemGroupAffinityThread: bind a kernel thread to a group/CPU mask. Pass the affinity from KeQueryGroupAffinity to pin a thread to a specific node.\n\nMmAllocateContiguousNodeMemory (Windows 8+): allocate physically contiguous non-paged memory from a specific NUMA node — the kernel equivalent of VirtualAllocExNuma, primarily for DMA buffers. Pass MM_ANY_NODE_OK as the node to skip NUMA preference. Free with MmFreeContiguousMemory.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "ULONG nodeCount = KeQueryHighestNodeNumber() + 1;\n" +
            "ULONG currentNode = KeGetCurrentNodeNumber();\n\n" +
            "// Allocate a 2 MB DMA buffer from the current node\n" +
            "PHYSICAL_ADDRESS low  = { 0 };\n" +
            "PHYSICAL_ADDRESS high = { .QuadPart = 0xFFFFFFFF };\n" +
            "PHYSICAL_ADDRESS zero = { 0 };\n\n" +
            "PVOID dmaBuf = MmAllocateContiguousNodeMemory(\n" +
            "    2 * 1024 * 1024,\n" +
            "    low, high, zero,\n" +
            "    PAGE_READWRITE,\n" +
            "    currentNode);  // or MM_ANY_NODE_OK\n\n" +
            "// ... use dmaBuf for DMA transfers ...\n\n" +
            "MmFreeContiguousMemory(dmaBuf);\n\n" +
            "// Bind a worker thread to the same node's CPUs\n" +
            "GROUP_AFFINITY aff;\n" +
            "KeQueryGroupAffinity(currentNode, &aff);\n" +
            "KeSetSystemGroupAffinityThread(\n" +
            "    KeGetCurrentThread(), &aff, NULL);"
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
