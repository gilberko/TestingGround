package com.example.linuxapp.screens.usermode

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
import com.example.linuxapp.screens.kernel.BodyText
import com.example.linuxapp.screens.kernel.CodeBlock
import com.example.linuxapp.screens.kernel.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserModeMemAllocScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Memory Allocation",
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
                SectionCard(title = "brk and sbrk") {
                    BodyText("The program break is a pointer that marks the end of the process heap — everything below it is valid heap memory; above it is unmapped. brk() and sbrk() move that pointer.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("brk(void *addr) — sets the program break to addr. Returns 0 on success, -1 on error.")
                    CodeBlock("""
#include <unistd.h>
void *heap_start = sbrk(0);   // query current break
brk(heap_start + 4096);       // extend heap by 4 KB
                    """.trimIndent())
                    BodyText("sbrk(intptr_t increment) — moves the break by increment bytes. Returns a pointer to the old break (i.e., the start of the newly allocated region). Passing 0 queries without changing anything.")
                    CodeBlock("""
void *old = sbrk(4096);   // allocate 4 KB, old = start of new region
sbrk(0);                  // query current break without changing it
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Both are Linux syscalls exposed via <unistd.h>. The kernel tracks the program break per-process and maps/unmaps physical pages as it moves.")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "Can You Call brk/sbrk?") {
                    BodyText("Yes — they are real syscalls and calling them from userspace works. However:")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("1. POSIX deprecated them in POSIX.1-2001 and removed them entirely in POSIX.1-2008. Portable code should not use them.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("2. Mixing brk/sbrk with malloc() corrupts the heap. glibc's malloc uses sbrk internally to grow its main arena. If your code also calls sbrk(), you move the break out from under malloc and its internal free-lists become invalid, leading to crashes or silent data corruption.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Safe use: a standalone custom allocator that never calls malloc/free/new/delete. In that case brk/sbrk give you a simple bump-allocator over the process heap.")
                    CodeBlock("""
// Safe: custom allocator with no malloc anywhere
static void *bump_ptr = NULL;
void *my_alloc(size_t n) {
    if (!bump_ptr) bump_ptr = sbrk(0);
    void *p = bump_ptr;
    sbrk(n);
    bump_ptr = sbrk(0);
    return p;
}
                    """.trimIndent())
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "Allocation Limit") {
                    BodyText("The amount of heap you can grow via brk/sbrk is bounded by:")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("RLIMIT_DATA — the soft limit on the data+BSS+heap segment size. Check and set with getrlimit/setrlimit.")
                    CodeBlock("""
#include <sys/resource.h>
struct rlimit rl;
getrlimit(RLIMIT_DATA, &rl);
printf("soft=%lu hard=%lu\n", rl.rlim_cur, rl.rlim_max);
// rl.rlim_cur = RLIM_INFINITY means no limit on most systems
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Virtual address space — on 64-bit Linux the user virtual address space is 128 TB by default. In practice you will exhaust physical RAM or swap long before running out of virtual space.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Overcommit policy — /proc/sys/vm/overcommit_memory controls whether the kernel reserves swap for every allocation (0 = heuristic, 1 = always allow, 2 = never overcommit). With policy 2, brk() can fail even if virtual space is available.")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "Releasing brk/sbrk Memory") {
                    BodyText("To release heap memory, move the program break downward:")
                    CodeBlock("""
void *saved = sbrk(0);     // save current break
sbrk(4096);                // allocate 4 KB
brk(saved);                // release it: move break back
// or equivalently:
sbrk(-4096);               // decrement by 4 KB
                    """.trimIndent())
                    BodyText("When the break is lowered, the kernel unmaps the physical pages for the released region.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Key limitation — no holes. The program break is a single pointer marking the top of the heap. You can only release memory from the top. Example:")
                    CodeBlock("""
void *a = sbrk(4096);   // allocate block A
void *b = sbrk(4096);   // allocate block B (above A)
// To free A, you must first free B:
sbrk(-4096);            // free B
sbrk(-4096);            // now free A
// You cannot skip B and free only A.
                    """.trimIndent())
                    BodyText("This is why general-purpose code uses mmap instead: mmap allows freeing any individual mapping regardless of order.")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "mmap for Memory Allocation") {
                    BodyText("mmap() maps a region of memory (or a file) into the process address space. For anonymous memory allocation, use MAP_ANONYMOUS with fd = -1.")
                    CodeBlock("""
#include <sys/mman.h>
// Anonymous private mapping — typical dynamic allocation
void *p = mmap(NULL, size,
               PROT_READ | PROT_WRITE,
               MAP_ANONYMOUS | MAP_PRIVATE,
               -1, 0);
if (p == MAP_FAILED) { perror("mmap"); }
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("MAP_ANONYMOUS | MAP_PRIVATE — memory is zero-initialized; changes are not visible to other processes; after fork(), each process gets its own copy-on-write copy.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("MAP_ANONYMOUS | MAP_SHARED — changes are visible to all processes that share the mapping; survives fork() with shared state. fd must be -1.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("MAP_HUGETLB — allocate from the huge-page pool (2 MB or 1 GB pages). Reduces TLB pressure for large allocations. Requires huge pages configured on the system.")
                    CodeBlock("""
void *hp = mmap(NULL, 2 * 1024 * 1024,
                PROT_READ | PROT_WRITE,
                MAP_ANONYMOUS | MAP_PRIVATE | MAP_HUGETLB,
                -1, 0);
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("MAP_POPULATE — immediately fault in all pages (no lazy allocation). Useful when you need guaranteed memory and predictable latency from first access.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("MAP_LOCKED — equivalent to mlock(); keeps pages in RAM, prevents swapping.")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "Freeing mmap Memory") {
                    BodyText("munmap(addr, length) releases the mapping and returns physical pages to the kernel immediately.")
                    CodeBlock("""
munmap(p, size);   // addr must match the original mmap() return value
                   // length must match the original size
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Unlike brk, you can munmap any mapping independently — order does not matter, there are no holes. Each mmap region is tracked separately by the kernel.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Other mmap-related operations:")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("mprotect(addr, len, prot) — change protection flags on an existing mapping (e.g., make a region read-only after writing it).")
                    CodeBlock("""
mprotect(p, size, PROT_READ);  // make read-only
                    """.trimIndent())
                    BodyText("mremap(old_addr, old_size, new_size, MREMAP_MAYMOVE) — resize a mapping; kernel may move it to a new address if it cannot extend in place.")
                    CodeBlock("""
void *p2 = mremap(p, old_size, new_size, MREMAP_MAYMOVE);
                    """.trimIndent())
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "How malloc / calloc / free Work") {
                    BodyText("glibc uses ptmalloc2 (a variant of dlmalloc) as its allocator. It uses two different strategies depending on allocation size:")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Small/medium allocations (< MMAP_THRESHOLD, default 128 KB) — managed in the main arena. The arena is a contiguous region grown with sbrk(). Freed blocks go into bins (fast bins, small bins, large bins, unsorted bin) and are reused by future allocations. The kernel does not get these pages back immediately. malloc_trim() can be called to return unused top pages to the OS.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Large allocations (>= MMAP_THRESHOLD) — directly mmap(MAP_ANONYMOUS|MAP_PRIVATE). On free(), munmap() is called immediately, returning pages to the OS right away.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Multi-threaded programs — each thread gets a per-thread arena (itself mmap'd) to avoid lock contention on the main arena. A pool of arenas is maintained; threads that can't get an exclusive arena fall back to the main arena with a lock.")
                    CodeBlock("""
#include <malloc.h>
malloc_trim(0);   // release free pages at top of main arena back to OS
struct mallinfo2 mi = mallinfo2();
printf("in use: %zu bytes\n", mi.uordblks);
                    """.trimIndent())
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "calloc, realloc, posix_memalign") {
                    BodyText("calloc(n, size) — allocates n * size bytes, guaranteed zero-initialized. For large allocations it maps anonymous pages from the OS, which are already zero (the kernel zeroes pages before giving them to user space), so no explicit memset is needed — it is effectively free.")
                    CodeBlock("""
int *arr = calloc(1000, sizeof(int));  // zero-initialized array
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("realloc(ptr, new_size) — resizes an existing allocation. If there is enough space after the current block in the arena, it extends in place (no copy). Otherwise it malloc()s a new block, memcpy()s the old data, and free()s the old block.")
                    CodeBlock("""
char *buf = malloc(64);
buf = realloc(buf, 128);   // may or may not move
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("posix_memalign(&ptr, alignment, size) — returns memory aligned to a power-of-2 boundary. alignment must be a power of 2 and a multiple of sizeof(void*). Useful for SIMD data structures, DMA buffers, and hardware requirements.")
                    CodeBlock("""
void *p;
// 32-byte aligned (for AVX2 SIMD)
posix_memalign(&p, 32, 1024);
free(p);  // freed with standard free()
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("aligned_alloc(alignment, size) — C11 alternative; size must be a multiple of alignment.")
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
