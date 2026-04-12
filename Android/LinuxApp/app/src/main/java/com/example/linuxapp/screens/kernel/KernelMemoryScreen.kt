package com.example.linuxapp.screens.kernel

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KernelMemoryScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Memory Management",
                        color = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(
                start = 16.dp, end = 16.dp,
                top = innerPadding.calculateTopPadding() + 8.dp,
                bottom = 24.dp
            )
        ) {

            // ── Section 1: mmap() and sbrk() ─────────────────────────────────
            item {
                SectionCard(title = "mmap() and sbrk()") {
                    BodyText("User-space programs need virtual memory for heap growth, anonymous buffers, and file mappings. Two fundamental kernel interfaces make this possible.")
                    Spacer(Modifier.height(8.dp))

                    BodyText("sbrk() / brk()")
                    BodyText(
                        "brk(addr) sets the program break — the end of the heap segment. " +
                        "sbrk(n) is a convenience wrapper that increments it by n bytes and returns the old break.\n\n" +
                        "glibc malloc() calls sbrk() for small allocations. The kernel does not allocate physical pages immediately; it only expands the anonymous VMA that covers the heap region. Physical pages are faulted in on first access."
                    )
                    CodeBlock(
                        "/* direct use (rarely done outside libc) */\n" +
                        "void *old_break = sbrk(0);   /* query current break */\n" +
                        "sbrk(4096);                  /* grow heap by 4 KB   */\n" +
                        "brk(old_break);              /* shrink back         */"
                    )
                    BodyText("Limitations: the heap is a single contiguous region. You cannot selectively free a block in the middle, and you cannot munmap() a sbrk-extended region.")
                    Spacer(Modifier.height(8.dp))

                    BodyText("mmap() — anonymous")
                    BodyText(
                        "mmap() with MAP_ANONYMOUS|MAP_PRIVATE creates a new, independent VMA anywhere in the address space. " +
                        "glibc uses it for allocations ≥ MMAP_THRESHOLD (~128 KB). Each mapping can be munmap()ped independently."
                    )
                    CodeBlock(
                        "void *p = mmap(\n" +
                        "    NULL,                          /* kernel chooses address  */\n" +
                        "    size,\n" +
                        "    PROT_READ | PROT_WRITE,\n" +
                        "    MAP_PRIVATE | MAP_ANONYMOUS,\n" +
                        "    -1, 0                          /* no file descriptor      */\n" +
                        ");\n" +
                        "if (p == MAP_FAILED) { /* error */ }\n" +
                        "/* ... use p ... */\n" +
                        "munmap(p, size);"
                    )
                    BodyText("mmap() — file-backed")
                    BodyText(
                        "When a valid fd is passed, the VMA is backed by the file's page cache. Reads/writes go directly to/from cached pages — no explicit read()/write() needed. " +
                        "MAP_SHARED flushes changes back to the file; MAP_PRIVATE gives a copy-on-write snapshot."
                    )
                    CodeBlock(
                        "int fd = open(\"data.bin\", O_RDWR);\n" +
                        "char *map = mmap(NULL, size, PROT_READ|PROT_WRITE,\n" +
                        "                 MAP_SHARED, fd, 0);\n" +
                        "map[0] = 'X';   /* writes directly into page cache */\n" +
                        "msync(map, size, MS_SYNC);   /* flush to disk if needed */\n" +
                        "munmap(map, size);\n" +
                        "close(fd);"
                    )
                    BodyText("sbrk vs mmap — quick comparison:")
                    CodeBlock(
                        "                  sbrk/brk          mmap (anon)\n" +
                        "Layout            contiguous heap    independent VMA\n" +
                        "Free selectively  no                 yes (munmap)\n" +
                        "Typical use       small allocations  large buffers\n" +
                        "Kernel action     extend heap VMA    create new VMA\n" +
                        "Physical pages    lazy (on fault)    lazy (on fault)"
                    )
                }
            }

            // ── Section 2: Driver mmap Implementation ─────────────────────────
            item {
                SectionCard(title = "Driver mmap() Implementation") {
                    BodyText(
                        "A kernel driver can implement the .mmap file_operation to let a user-space process map kernel/hardware memory directly — no copy_to_user() needed. " +
                        "This is the zero-copy path used by frame buffers, GPU drivers, network adapters, and UIO drivers."
                    )
                    Spacer(Modifier.height(8.dp))

                    BodyText("Which drivers implement .mmap")
                    BodyText(
                        "Any char device that needs to share a memory region with user space:\n" +
                        "• Hardware MMIO registers (PCI BARs) — map physical address of device registers\n" +
                        "• DMA buffers — map a contiguous kernel-allocated buffer\n" +
                        "• Frame buffers (/dev/fb0) — map video memory\n" +
                        "• GPU drivers (DRM/KMS) — map command rings, VRAM\n" +
                        "• UIO (User-space I/O) drivers\n" +
                        "• Shared-memory helpers (CMEM, ion allocator)"
                    )
                    Spacer(Modifier.height(8.dp))

                    BodyText("The mmap file_operation")
                    BodyText(
                        "The kernel calls .mmap(filp, vma) after it has already created a vm_area_struct (VMA) for the requested range. " +
                        "The driver's job is to populate that VMA — either by calling remap_pfn_range() (for physical addresses, e.g. MMIO) or vm_insert_page() (for individual kernel pages)."
                    )
                    CodeBlock(
                        "/* file_operations entry */\n" +
                        "static const struct file_operations mydev_fops = {\n" +
                        "    .owner = THIS_MODULE,\n" +
                        "    .open  = mydev_open,\n" +
                        "    .mmap  = mydev_mmap,   /* <-- our hook */\n" +
                        "};"
                    )
                    Spacer(Modifier.height(4.dp))

                    BodyText("Example: map a single kernel page to user space")
                    BodyText(
                        "The driver allocates one page at module init with get_zeroed_page(), then maps it into the user VMA on each mmap() call. " +
                        "Both kernel and user space share the same physical page — writes from either side are instantly visible to the other."
                    )
                    CodeBlock(
                        "static unsigned long buf_page;   /* kernel virtual addr of page */\n\n" +
                        "static int __init mydev_init(void)\n" +
                        "{\n" +
                        "    buf_page = get_zeroed_page(GFP_KERNEL);\n" +
                        "    if (!buf_page) return -ENOMEM;\n" +
                        "    /* ... register char device ... */\n" +
                        "    return 0;\n" +
                        "}\n\n" +
                        "static int mydev_mmap(struct file *filp,\n" +
                        "                      struct vm_area_struct *vma)\n" +
                        "{\n" +
                        "    unsigned long size = vma->vm_end - vma->vm_start;\n" +
                        "    if (size > PAGE_SIZE)\n" +
                        "        return -EINVAL;\n\n" +
                        "    /* mark as I/O memory: no swap, no core dump */\n" +
                        "    vm_flags_set(vma, VM_IO | VM_DONTEXPAND | VM_DONTDUMP);\n\n" +
                        "    /* insert the physical page into the user VMA */\n" +
                        "    return vm_insert_page(\n" +
                        "        vma,\n" +
                        "        vma->vm_start,\n" +
                        "        virt_to_page(buf_page)\n" +
                        "    );\n" +
                        "}\n\n" +
                        "static void __exit mydev_exit(void)\n" +
                        "{\n" +
                        "    free_page(buf_page);\n" +
                        "    /* ... unregister char device ... */\n" +
                        "}"
                    )
                    BodyText("User-space side:")
                    CodeBlock(
                        "int fd = open(\"/dev/mydev\", O_RDWR);\n" +
                        "char *shared = mmap(NULL, PAGE_SIZE,\n" +
                        "                    PROT_READ | PROT_WRITE,\n" +
                        "                    MAP_SHARED, fd, 0);\n" +
                        "/* now shared[] and buf_page[] are the same physical page */\n" +
                        "shared[0] = 42;   /* kernel can read buf_page[0] == 42 */"
                    )
                    Spacer(Modifier.height(4.dp))

                    BodyText("Mapping MMIO (hardware registers)")
                    BodyText(
                        "For MMIO regions, use remap_pfn_range() with the physical address shifted right by PAGE_SHIFT:"
                    )
                    CodeBlock(
                        "static int mydev_mmap(struct file *filp,\n" +
                        "                      struct vm_area_struct *vma)\n" +
                        "{\n" +
                        "    unsigned long phys = MMIO_BASE_PHYS;  /* e.g. PCI BAR */\n" +
                        "    vma->vm_page_prot =\n" +
                        "        pgprot_noncached(vma->vm_page_prot);\n" +
                        "    return remap_pfn_range(\n" +
                        "        vma,\n" +
                        "        vma->vm_start,\n" +
                        "        phys >> PAGE_SHIFT,\n" +
                        "        vma->vm_end - vma->vm_start,\n" +
                        "        vma->vm_page_prot\n" +
                        "    );\n" +
                        "}"
                    )
                }
            }

            // ── Section 3: vmalloc vs kmalloc ─────────────────────────────────
            item {
                SectionCard(title = "vmalloc vs kmalloc") {
                    BodyText(
                        "The kernel has two main general-purpose allocators. Choosing the wrong one causes either wasted memory, allocation failures, or broken DMA."
                    )
                    Spacer(Modifier.height(8.dp))

                    BodyText("kmalloc(size, flags)")
                    BodyText(
                        "Returns memory that is both physically and virtually contiguous. Backed by the SLUB allocator (slab caches). " +
                        "Very fast — typically sub-microsecond. Maximum practical size is around 4–8 MB (order-11 pages). " +
                        "Safe in interrupt context with GFP_ATOMIC. Required whenever hardware DMA must access the buffer (DMA engines need a single physical range)."
                    )
                    CodeBlock(
                        "/* allocate 256 bytes, can sleep */\n" +
                        "void *buf = kmalloc(256, GFP_KERNEL);\n\n" +
                        "/* allocate in interrupt context (no sleep) */\n" +
                        "void *buf = kmalloc(64, GFP_ATOMIC);\n\n" +
                        "/* zero-filled variant */\n" +
                        "void *buf = kzalloc(256, GFP_KERNEL);\n\n" +
                        "kfree(buf);"
                    )
                    Spacer(Modifier.height(4.dp))

                    BodyText("vmalloc(size)")
                    BodyText(
                        "Returns memory that is virtually contiguous but not necessarily physically contiguous — each page may be at a different physical address. " +
                        "Can allocate large regions (hundreds of megabytes) by stitching together physically scattered pages. " +
                        "Slower because it must set up new kernel page-table entries. Cannot be used for DMA. " +
                        "Typical uses: large driver buffers, dynamically loaded module text, large temporary arrays."
                    )
                    CodeBlock(
                        "void *buf = vmalloc(16 * 1024 * 1024);  /* 16 MB */\n\n" +
                        "/* zero-filled */\n" +
                        "void *buf = vzalloc(16 * 1024 * 1024);\n\n" +
                        "vfree(buf);"
                    )
                    Spacer(Modifier.height(4.dp))

                    BodyText("kvmalloc — best of both")
                    BodyText(
                        "kvmalloc(size, flags) first tries kmalloc; if the allocation fails (e.g. too large or memory pressure), it falls back to vmalloc. " +
                        "Use when you want physically contiguous memory if possible but can tolerate virtually-contiguous fallback."
                    )
                    CodeBlock(
                        "void *buf = kvmalloc(size, GFP_KERNEL);\n" +
                        "kvfree(buf);   /* works for both kmalloc and vmalloc results */"
                    )
                    Spacer(Modifier.height(4.dp))

                    BodyText("Comparison table:")
                    CodeBlock(
                        "                    kmalloc          vmalloc\n" +
                        "Physical contiguous yes              no\n" +
                        "Virtual contiguous  yes              yes\n" +
                        "DMA safe            yes              no\n" +
                        "Max size            ~4–8 MB          hundreds of MB\n" +
                        "Interrupt context   yes (GFP_ATOMIC) no\n" +
                        "Speed               very fast        slower\n" +
                        "Page table setup    no               yes\n" +
                        "Free function       kfree()          vfree()"
                    )
                }
            }

            // ── Section 4: Page Fault Handling Flow ───────────────────────────
            item {
                SectionCard(title = "Page Fault Handling Flow") {
                    BodyText(
                        "When a process accesses a virtual address that has no valid page-table entry, the CPU raises a #PF (page fault) exception. " +
                        "The kernel resolves it by locating the responsible VMA, allocating a physical page, and updating the page tables — all without the process knowing anything happened."
                    )
                    Spacer(Modifier.height(8.dp))

                    BodyText("Step 1 — CPU raises #PF")
                    BodyText(
                        "The CPU saves the faulting address in CR2, pushes an error code onto the kernel stack, and jumps to the page-fault handler entry point (exc_page_fault on x86-64)."
                    )
                    Spacer(Modifier.height(4.dp))

                    BodyText("Step 2 — do_page_fault / handle_mm_fault")
                    BodyText(
                        "exc_page_fault() reads CR2, determines if the fault came from user space, and calls handle_mm_fault(mm, vma, address, flags). " +
                        "mm is current->mm — the memory descriptor of the faulting process."
                    )
                    Spacer(Modifier.height(4.dp))

                    BodyText("Step 3 — VMA lookup")
                    BodyText(
                        "The kernel searches mm->mm_mt (a maple tree, replacing the older red-black tree in Linux 6.1) for the VMA that covers the faulting address. " +
                        "If no VMA covers that address, the access is invalid → SIGSEGV is sent to the process."
                    )
                    CodeBlock(
                        "/* simplified kernel view */\n" +
                        "struct vm_area_struct *vma =\n" +
                        "    find_vma(mm, fault_address);\n" +
                        "if (!vma || vma->vm_start > fault_address)\n" +
                        "    return send_sigsegv(...);"
                    )
                    Spacer(Modifier.height(4.dp))

                    BodyText("Step 4 — page-table walk and PTE allocation")
                    BodyText(
                        "handle_pte_fault() walks the 4-level page tables rooted at mm->pgd (PGD → P4D → PUD → PMD → PTE). " +
                        "If a PTE is absent (not-present bit clear), a new physical page is allocated."
                    )
                    CodeBlock(
                        "/* mm->pgd is the root (loaded into CR3 on context switch) */\n" +
                        "pgd_t *pgd = pgd_offset(mm, address);\n" +
                        "p4d_t *p4d = p4d_alloc(mm, pgd, address);\n" +
                        "pud_t *pud = pud_alloc(mm, p4d, address);\n" +
                        "pmd_t *pmd = pmd_alloc(mm, pud, address);\n" +
                        "pte_t *pte = pte_alloc_map(mm, pmd, address);"
                    )
                    Spacer(Modifier.height(4.dp))

                    BodyText("Step 5 — page allocation and PTE update")
                    BodyText(
                        "For an anonymous VMA (heap, stack, MAP_ANONYMOUS), the kernel allocates a zero-filled page from the buddy allocator, " +
                        "then installs it in the page table:"
                    )
                    CodeBlock(
                        "struct page *page =\n" +
                        "    alloc_page(GFP_HIGHUSER_MOVABLE | __GFP_ZERO);\n\n" +
                        "pte_t entry = mk_pte(page, vma->vm_page_prot);\n" +
                        "entry = pte_sw_mkyoung(entry);\n" +
                        "if (vma->vm_flags & VM_WRITE)\n" +
                        "    entry = pte_mkwrite(pte_mkdirty(entry));\n\n" +
                        "set_pte_at(mm, address, pte, entry);\n" +
                        "/* TLB flush happens on return to user space */"
                    )
                    Spacer(Modifier.height(4.dp))

                    BodyText("Role of mm_struct and task_struct")
                    BodyText(
                        "task_struct is the kernel's per-process descriptor. Its mm field points to mm_struct, which holds everything the kernel needs to manage a process's virtual address space:"
                    )
                    CodeBlock(
                        "struct task_struct {\n" +
                        "    struct mm_struct *mm;        /* user-space memory      */\n" +
                        "    struct mm_struct *active_mm; /* for kernel threads     */\n" +
                        "    /* ... */\n" +
                        "};\n\n" +
                        "struct mm_struct {\n" +
                        "    pgd_t            *pgd;       /* root page table (→CR3) */\n" +
                        "    struct maple_tree mm_mt;     /* ordered set of VMAs    */\n" +
                        "    unsigned long    start_brk;  /* heap start             */\n" +
                        "    unsigned long    brk;        /* heap end (program brk) */\n" +
                        "    unsigned long    start_stack;\n" +
                        "    atomic_t         mm_users;   /* threads sharing this mm*/\n" +
                        "    /* ... */\n" +
                        "};"
                    )
                    BodyText(
                        "On a context switch, the kernel loads mm->pgd into CR3, which instantly makes the new process's entire virtual address space visible to the MMU."
                    )
                    Spacer(Modifier.height(4.dp))

                    BodyText("Step 6 — return and instruction retry")
                    BodyText(
                        "After set_pte_at(), the fault handler returns. The CPU executes iret, which returns to user space and automatically retries the faulting instruction. " +
                        "This time the PTE is present, the TLB is primed, and the access succeeds — the process never sees any of this."
                    )
                    Spacer(Modifier.height(4.dp))

                    BodyText("Major vs minor faults")
                    CodeBlock(
                        "Minor fault  page was already in memory\n" +
                        "             (page cache, zero page, COW)\n" +
                        "             → no disk I/O, fast\n\n" +
                        "Major fault  page must be read from disk\n" +
                        "             (swapped-out page, file-backed\n" +
                        "             page not yet in cache)\n" +
                        "             → process sleeps in\n" +
                        "               wait_on_page_locked()\n" +
                        "               until I/O completes"
                    )
                    BodyText(
                        "You can see per-process fault counts in /proc/<pid>/stat fields majflt and minflt, or with the time command's 'major/minor page faults' output."
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
