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
fun MemoryAndPagingScreen(navController: NavController) {
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
            text = "MEMORY AND PAGING",
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

        SectionHeader("VIRTUAL ADDRESS SPACE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Each process gets its own virtual address space (128 TB on 64-bit Windows). Addresses in this space are virtual — they are translated to physical RAM addresses by the CPU's Memory Management Unit (MMU) using multi-level page tables.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The OS tracks the state of each virtual address range using two structures: VAD (Virtual Address Descriptor) trees, and page table entries (PTEs).")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("MEM_RESERVE vs MEM_COMMIT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("VirtualAlloc / ZwAllocateVirtualMemory work in two stages:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "MEM_RESERVE\n" +
            "  Claims a range of virtual address space.\n" +
            "  Creates a VAD entry in the process VAD tree.\n" +
            "  No physical memory allocated, no page table entries.\n" +
            "  Other VirtualAlloc calls won't use this range.\n" +
            "  Accessing it causes ACCESS_VIOLATION.\n\n" +
            "MEM_COMMIT\n" +
            "  Allocates physical backing (RAM or pagefile quota).\n" +
            "  Creates page table entries marked demand-zero.\n" +
            "  Actual physical frame is NOT allocated yet --\n" +
            "  it's allocated lazily on the first access (demand paging).\n" +
            "  Pages are zeroed before being handed to the process.\n\n" +
            "MEM_RESERVE | MEM_COMMIT\n" +
            "  Reserve + commit in one call. Most common usage.\n\n" +
            "MEM_DECOMMIT\n" +
            "  Free physical backing, return to reserved state.\n\n" +
            "MEM_RELEASE\n" +
            "  Release reservation entirely. RegionSize must be 0."
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Other VirtualAlloc flags:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "MEM_RESET       // mark pages as discardable; contents\n" +
            "                //   may be lost if RAM is needed\n" +
            "MEM_RESET_UNDO  // undo a MEM_RESET\n" +
            "MEM_LARGE_PAGES // use 2 MB pages (x64); requires\n" +
            "                //   SeLockMemoryPrivilege; reduces TLB pressure\n" +
            "MEM_TOP_DOWN    // allocate from highest available address\n" +
            "MEM_WRITE_WATCH // track which pages are written (GC use)"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHAT HAPPENS ON FIRST ACCESS (DEMAND PAGING)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("When you first access a committed but not-yet-touched page, the CPU cannot find a valid translation — the PTE's Present bit is 0. The CPU raises exception #PF (page fault) and transfers control to the page fault handler (MmAccessFault in ntoskrnl.exe).")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Page fault handler decision tree:\n\n" +
            "Address not in any VAD\n" +
            "  -> STATUS_ACCESS_VIOLATION (segfault)\n\n" +
            "Address in VAD but region is only Reserved (not Committed)\n" +
            "  -> STATUS_ACCESS_VIOLATION\n\n" +
            "PTE is demand-zero (committed, never accessed)\n" +
            "  -> Allocate a physical frame from the free list\n" +
            "  -> Zero the frame\n" +
            "  -> Update PTE: set Present=1, frame number\n" +
            "  -> Return; CPU retries the faulting instruction\n\n" +
            "PTE has transition bits (page in working set trim list)\n" +
            "  -> Rescue page from standby/modified list\n" +
            "  -> Update PTE and TLB\n\n" +
            "PTE has pagefile location bits (page was paged out)\n" +
            "  -> Issue paging I/O to read page from pagefile\n" +
            "  -> Suspend thread until I/O completes\n" +
            "  -> Resume: update PTE, instruction retries"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHAT HAPPENS WHEN MEMORY IS PAGED OUT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("When RAM is scarce, the Memory Manager's working set trimmer removes pages from processes' working sets:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "1. MMU PTE is modified: Present bit cleared,\n" +
            "   pagefile slot index encoded in the PTE bits.\n" +
            "2. Page content is written to the pagefile\n" +
            "   (if dirty/modified) or simply discarded (if clean).\n" +
            "3. Physical frame returned to the free/standby list.\n\n" +
            "On next access:\n" +
            "4. CPU takes #PF (Present=0).\n" +
            "5. MmAccessFault reads PTE, finds pagefile bits.\n" +
            "6. Allocates a new physical frame.\n" +
            "7. Issues paging I/O to read data back from pagefile.\n" +
            "8. Suspends the faulting thread (can't busy-wait).\n" +
            "9. I/O completes; PTE updated to new frame; thread wakes."
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("From the application's perspective, paging is completely transparent — the instruction that caused the fault is retried and succeeds. The only observable effect is latency (disk I/O instead of RAM access).")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WRITE-PROTECTION PAGE FAULT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A page fault is not only triggered by a missing page — it also fires when the CPU's protection bits prevent the access. If you write to a read-only page, the CPU raises #PF with the write flag set in the error code. The page fault handler then checks what kind of region this is:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Write to a read-only page:\n\n" +
            "Case 1: Copy-on-Write page (COW)\n" +
            "  Example: DLL code page shared among processes.\n" +
            "  Handler: allocate a private frame, copy page content,\n" +
            "  update PTE to point to private frame, set writable.\n" +
            "  Result: write SUCCEEDS; change is private to this process.\n\n" +
            "Case 2: Truly read-only page (no COW flag)\n" +
            "  Example: PAGE_READONLY allocation, .text section.\n" +
            "  Handler: generates EXCEPTION_ACCESS_VIOLATION.\n" +
            "  User-mode: unhandled -> process crash.\n" +
            "  Kernel-mode: bugcheck (IRQL_NOT_LESS_OR_EQUAL or\n" +
            "               PAGE_FAULT_IN_NONPAGED_AREA).\n\n" +
            "Case 3: PAGE_GUARD page\n" +
            "  First access: STATUS_GUARD_PAGE_VIOLATION exception.\n" +
            "  Guard bit is cleared after firing.\n" +
            "  Used for stack growth detection and watchpoints."
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("So yes — write-protection violations do go through the same page fault handler (MmAccessFault). The handler inspects the error code to determine whether this is a missing-page fault or a protection fault, and decides whether to satisfy it (COW) or propagate an exception.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("HOW THE HANDLER DISTINGUISHES STATES")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "PTE state encoding (simplified x64):\n\n" +
            "All zeros\n" +
            "  -> Walk VAD tree to determine region type.\n" +
            "  -> VAD absent: ACCESS_VIOLATION\n" +
            "  -> VAD Reserved (not committed): ACCESS_VIOLATION\n" +
            "  -> VAD Committed, demand-zero: allocate zero page\n\n" +
            "Present=0, Transition bit set\n" +
            "  -> Page is on standby/modified list, not in pagefile.\n" +
            "  -> Rescue from list (no I/O needed).\n\n" +
            "Present=0, Pagefile bits set\n" +
            "  -> Page was written to pagefile.\n" +
            "  -> Issue paging I/O.\n\n" +
            "Present=1, Protection mismatch\n" +
            "  -> COW: make private copy\n" +
            "  -> No COW: access violation"
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
