package com.example.linuxapp.screens.kernel

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoryAccessWholeScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Memory Access: The Whole Picture",
                        color = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontSize = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item {
                SectionCard(title = "malloc() in User Space") {
                    BodyText("When a program calls malloc(), the C library manages a heap — a region of virtual address space reserved for dynamic allocation. For small allocations, libc carves memory from an existing pool without any syscall at all.")
                    CodeBlock("""void *p = malloc(64);   // may not involve the kernel at all""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("When the heap runs out, libc asks the kernel to extend it:")
                    CodeBlock("""brk() / sbrk()  — moves the heap break pointer upward
                 (traditional heap expansion)

mmap(MAP_ANONYMOUS) — maps a new anonymous region
                      (used for large allocations, >128 KB typically)""")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Critical insight: the kernel's response to brk() or mmap() does NOT allocate any physical RAM. It only records that a virtual address range is now valid for this process. Physical memory is allocated lazily — only when actually accessed.")
                }
            }
            item {
                SectionCard(title = "Virtual Memory & Page Tables") {
                    BodyText("Every process lives in its own virtual address space — a private 48-bit (on x86-64) range of addresses that map to physical RAM through page tables.")
                    CodeBlock("""Virtual Address (64-bit)
  ┌─────────┬─────────┬─────────┬─────────┬──────────┐
  │ PML4    │  PDPT   │   PD    │   PT    │  Offset  │
  │ 9 bits  │ 9 bits  │ 9 bits  │ 9 bits  │ 12 bits  │
  └─────────┴─────────┴─────────┴─────────┴──────────┘
        4-level page table walk → physical page frame""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The CR3 register holds the physical address of the top-level page table (PML4). On a context switch the kernel reloads CR3 — instantly giving each process its own isolated view of memory.")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("When brk()/mmap() returns, the page table entries for the new range are either absent (not present bit = 0) or point to a shared zero page. No physical frame is allocated yet.")
                }
            }
            item {
                SectionCard(title = "The First Access — Page Fault") {
                    BodyText("When the CPU tries to access a virtual address with no valid page table entry, the MMU (Memory Management Unit) cannot translate it. It raises a page fault exception (#PF, interrupt vector 14).")
                    CodeBlock("""CPU: load instruction at virtual address 0x7fff...
MMU: page table entry is not present → raise #PF
CPU: saves register state, switches to kernel mode
     jumps to page fault handler""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The kernel's page fault handler (do_page_fault → handle_mm_fault) checks:")
                    CodeBlock("""1. Is this address in a valid VMA (vm_area_struct)?
   No  → segfault (SIGSEGV) — invalid access
   Yes → continue

2. What kind of fault?
   Anonymous mapping  → allocate a new zero page
   File-backed mmap   → read the page from the file
   Copy-on-write      → copy the shared page privately
   Swapped out        → read the page back from swap""")
                }
            }
            item {
                SectionCard(title = "Physical Page Allocation — The Buddy Allocator") {
                    BodyText("For an anonymous fault, the kernel allocates a physical page frame using the buddy allocator — Linux's physical memory manager.")
                    CodeBlock("""alloc_page(GFP_USER)
  → buddy allocator: maintains free lists of 2^n page blocks
    (order-0 = 1 page, order-1 = 2 pages, ... order-11 = 2048)
  → finds a free block, splits if needed
  → returns a struct page * representing the physical frame""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("After allocation:")
                    CodeBlock("""1. Kernel zero-fills the page (security: don't leak kernel data)
2. Updates the page table entry:
     physical frame number + Present + User + Writable bits
3. TLB doesn't know yet — it will pick it up on next access
4. Returns from the fault handler with iret
5. CPU retries the faulting instruction — now succeeds""")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("The entire fault, allocation, and return is transparent. The program never knows a page fault occurred — it simply continues executing.")
                }
            }
            item {
                SectionCard(title = "The SLUB Allocator — Kernel's kmalloc") {
                    BodyText("The buddy allocator works in pages (4 KB). For smaller kernel allocations (struct task_struct, socket buffers, etc.) the kernel uses the SLUB allocator on top of buddy.")
                    CodeBlock("""kmalloc(256, GFP_KERNEL)
  → SLUB: looks in per-CPU cache (slab) for a free 256-byte slot
  → If cache empty: pulls a new page from buddy, carves it up
  → Returns pointer immediately

Advantages:
  - Avoids buddy allocator overhead for small objects
  - Per-CPU caches eliminate lock contention
  - Objects of the same type reuse the same slabs
    (better cache locality, less fragmentation)""")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("User-space malloc works similarly — libc has its own slab-like allocator on top of mmap/brk, calling the kernel only when its pool is empty.")
                }
            }
            item {
                SectionCard(title = "Accessing Memory Later — TLB & CPU Cache") {
                    BodyText("After the page is mapped, subsequent accesses are fast — no kernel involvement needed:")
                    CodeBlock("""Virtual address → TLB lookup (Translation Lookaside Buffer)
  Hit:  physical address returned in ~1 cycle
  Miss: hardware page table walk (4 memory reads on x86-64)
        result cached in TLB for future accesses""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The physical address then goes to the CPU cache hierarchy:")
                    CodeBlock("""L1 cache:  ~4 cycles   (per-core, ~32 KB)
L2 cache:  ~12 cycles  (per-core, ~256 KB)
L3 cache:  ~40 cycles  (shared, ~8-64 MB)
RAM:       ~100+ cycles (DRAM access)""")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Context switches invalidate (flush) TLB entries for the outgoing process (unless PCID is used — Process Context ID, which lets TLB entries be tagged by process to survive context switches).")
                }
            }
            item {
                SectionCard(title = "Paging Out — When RAM Is Scarce") {
                    BodyText("When the system runs low on physical memory, the kernel's kswapd daemon wakes up and reclaims pages using the LRU (Least Recently Used) algorithm:")
                    CodeBlock("""kswapd wakes (triggered by low watermark threshold)
  → Scans active/inactive LRU lists for candidate pages
  → For dirty pages: writes them to swap (partition or file)
  → For clean file-backed pages: simply drops them
    (can be reloaded from the file later)
  → Clears the Present bit in the PTE
  → Stores swap location in the PTE (special encoding)
  → Frees the physical page frame back to buddy allocator""")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("The process that owns the page is not notified. Its page table entry now says 'not present' but encodes where on swap the data lives. The process continues running — until it accesses that address again.")
                }
            }
            item {
                SectionCard(title = "Page Fault on a Swapped-Out Page") {
                    BodyText("When the process accesses a swapped-out address, the MMU raises a page fault again. The handler detects this is a swap fault:")
                    CodeBlock("""handle_mm_fault()
  → PTE present bit = 0, but swap info encoded in PTE
  → This is a swap fault, not a segfault

  1. Allocate a new physical page frame (buddy)
  2. Issue I/O to read the page from swap device
     (this is real disk I/O — goes through block layer)
  3. Process is put to sleep (wait_queue) during I/O
  4. Drive completes, IRQ fires, page is filled
  5. PTE updated with new physical frame
  6. Process woken up, retries the instruction""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("This is called a major page fault (vs a minor page fault which needs no I/O). Major faults are expensive — the process is fully suspended until the disk responds. This is why excessive swap usage makes systems feel slow.")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("The full chain — malloc() → virtual mapping → page fault → buddy allocator → TLB → cache → kswapd → swap fault — is one of the most sophisticated mechanisms in the Linux kernel, all hidden behind a pointer dereference.")
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
