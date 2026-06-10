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
fun UserModeMemAlloc2Screen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Memory Allocation 2",
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
                SectionCard(title = "NUMA Overview") {
                    BodyText("NUMA — Non-Uniform Memory Access. In single-socket systems (UMA), all CPU cores share one memory controller and one memory bus, so every core competes for the same bandwidth and they all experience the same latency.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("In multi-socket servers, each socket forms a NUMA node: a group of CPU cores plus a dedicated DRAM bank attached through that socket's own memory controller. The key difference is that each node has an independent path to its local memory.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Local access (CPU to its node's DRAM) — fast: ~80 ns latency, full memory bandwidth, no cross-socket traffic. Cores within the same node compete only for their local bus, not with cores on other nodes.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Remote access (CPU to another node's DRAM) — slow: ~150–200 ns latency, reduced bandwidth. The request must traverse an inter-socket interconnect — AMD Infinity Fabric or Intel UPI (Ultra Path Interconnect). On systems with 4+ sockets, a hop may traverse two interconnects (2-hop NUMA), increasing latency further.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Each node's distance to every other node is published by the firmware. The local node is always distance 10; remote nodes typically show 20–40 or more.")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "Discovering NUMA Topology") {
                    BodyText("numactl --hardware — shows nodes, which CPUs belong to each, total and free memory per node, and the full distance matrix.")
                    CodeBlock("""
$ numactl --hardware
available: 2 nodes (0-1)
node 0 cpus: 0 1 2 3 4 5 6 7
node 0 size: 32168 MB
node 1 cpus: 8 9 10 11 12 13 14 15
node 1 size: 32167 MB
node distances:
node   0   1
  0:  10  21
  1:  21  10
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("lscpu — lists each CPU and its NUMA node under 'NUMA node0 CPU(s)' etc.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("/sys/devices/system/node/ — one directory per node. node0/cpulist shows CPU ranges; node0/meminfo shows memory stats; numastat shows allocation hit/miss counters.")
                    CodeBlock("""
cat /sys/devices/system/node/node0/cpulist   # e.g. 0-7
cat /sys/devices/system/node/node0/meminfo   # MemTotal, MemFree, etc.
cat /sys/devices/system/node/node0/numastat  # numa_hit, numa_miss, ...
                    """.trimIndent())
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "NUMA API (libnuma and Syscalls)") {
                    BodyText("libnuma provides the high-level NUMA API. Include <numa.h> and link with -lnuma.")
                    CodeBlock("""
#include <numa.h>

if (numa_available() == -1) { /* NUMA not supported */ }

int nodes = numa_max_node() + 1;       // number of NUMA nodes
int node  = numa_node_of_cpu(sched_getcpu()); // node for current CPU

// Allocate on a specific node
void *p = numa_alloc_onnode(size, 0);  // force node 0
void *q = numa_alloc_local(size);      // current CPU's node
void *r = numa_alloc_interleaved(size);// round-robin across all nodes
numa_free(p, size);                    // must pass size to numa_free

// Pin the process/thread to a node
numa_run_on_node(0);      // only run on CPUs of node 0
numa_set_preferred(0);    // prefer node 0 for future allocations
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Syscall level — mbind() sets the NUMA policy for an existing virtual memory range; set_mempolicy() sets the default policy for all future allocations in the process.")
                    CodeBlock("""
#include <numaif.h>
// Bind a specific mmap'd region strictly to node 0
unsigned long nodemask = 1UL << 0;    // bitmask: bit N = node N
mbind(addr, len, MPOL_BIND,
      &nodemask, /*maxnode=*/2, 0);

// Set process-wide interleave policy
set_mempolicy(MPOL_INTERLEAVE, &nodemask, 2);
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("migrate_pages(pid, maxnode, old_nodes, new_nodes) — move all pages of a process from one set of nodes to another. move_pages(pid, count, pages[], nodes[], status[], flags) — fine-grained per-page migration.")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "First Access and Page Faults") {
                    BodyText("mmap() and malloc() only reserve virtual address space — they do NOT allocate physical memory at the time of the call. Physical pages are assigned lazily on first access.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("When a thread first reads or writes a virtual address that has no physical page backing it, a page fault fires. The kernel page fault handler:")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("1. Selects a physical page from the NUMA node determined by the current NUMA policy for that address range.")
                    BodyText("2. Zeros the page (security requirement: never hand user space a page with another process's data).")
                    BodyText("3. Updates the page table to map the virtual address to the physical page.")
                    BodyText("4. Returns to user space, which retries the faulting instruction.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Default policy (MPOL_LOCAL / 'first-touch'): allocate on the NUMA node of the CPU that caused the fault. This means whichever thread first touches a page determines its physical NUMA placement for the life of that page.")
                    CodeBlock("""
// Common NUMA performance trap:
// Single init thread touches all pages -> all land on node 0
memset(large_array, 0, SIZE);   // init on CPU 0 = node 0

// Worker threads on node 1 now pay remote-access penalty
// Fix: parallelize init to match final access pattern
#pragma omp parallel for
for (int i = 0; i < N; i++)
    large_array[i] = 0;   // each thread faults its own portion
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("MAP_POPULATE — pass this flag to mmap() to fault in all pages immediately during the mmap() call, on the calling thread's node. Eliminates lazy-fault overhead at the cost of upfront time.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("mlock(addr, len) / mlockall(MCL_CURRENT) — also triggers page faults and pins the pages in RAM (no swapping), while also forcing NUMA placement on the calling CPU's node.")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "NUMA Policies") {
                    BodyText("MPOL_DEFAULT — inherit the parent process's policy. If at process top level, fall back to MPOL_LOCAL.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("MPOL_LOCAL (Linux 3.8+) — always allocate on the node of the CPU currently executing the allocation, regardless of any set_mempolicy() default. The most performance-friendly for NUMA-aware code.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("MPOL_PREFERRED — try a preferred node first; fall back to any node if the preferred node has no free memory. Non-strict, avoids OOM from node exhaustion.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("MPOL_BIND — strict: only allocate from the specified node set. If those nodes are full, allocation fails (ENOMEM) rather than going remote. Use when remote access is unacceptable.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("MPOL_INTERLEAVE — pages are distributed round-robin across the specified node set. Spreads memory bandwidth across nodes. Good for shared data structures accessed by threads on all nodes, bad for per-thread private data.")
                    CodeBlock("""
// numactl wrapper (no code changes required):
numactl --membind=0 --cpunodebind=0 ./myapp   // strict node 0
numactl --interleave=all ./myapp              // interleave all nodes
numactl --preferred=1 ./myapp                 // prefer node 1
                    """.trimIndent())
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "memfd_create — Anonymous Memory Files") {
                    BodyText("memfd_create() creates an anonymous file that lives entirely in memory (backed by tmpfs). It returns a normal file descriptor that can be used with read(), write(), ftruncate(), mmap(), and sendmsg().")
                    CodeBlock("""
#include <sys/memfd.h>
#include <sys/mman.h>
#include <unistd.h>

// Create an anonymous memory file
int fd = memfd_create("shared_buf", MFD_CLOEXEC);
if (fd == -1) { perror("memfd_create"); }

// Set size (file starts at 0 bytes)
ftruncate(fd, 4096);

// Map it into address space
void *p = mmap(NULL, 4096,
               PROT_READ | PROT_WRITE,
               MAP_SHARED, fd, 0);
// Now read/write p — changes visible through any other mmap of fd
munmap(p, 4096);
close(fd);
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The name argument is purely for debugging — it appears as 'memfd:name' in /proc/self/fd/ and in core dumps. It has no effect on functionality.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Flags: MFD_CLOEXEC — close fd on exec (recommended); MFD_ALLOW_SEALING — enable sealing operations; MFD_HUGETLB | MFD_HUGE_2MB — use 2 MB huge pages.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Available since Linux 3.17. Include <sys/memfd.h> (or define __NR_memfd_create and call syscall() on older glibc).")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "memfd_create: IPC and Sealing") {
                    BodyText("IPC between unrelated processes — the fd can be sent to another process via a Unix domain socket using SCM_RIGHTS ancillary data. The receiving process gets its own file descriptor for the same memory object. No filesystem path or shared name is ever needed.")
                    CodeBlock("""
// Sender: create memfd and send fd over Unix socket
int fd = memfd_create("ipc_buf", MFD_CLOEXEC | MFD_ALLOW_SEALING);
ftruncate(fd, size);
// ... fill with data ...
// send fd via sendmsg() with cmsg level=SOL_SOCKET type=SCM_RIGHTS

// Receiver: receives fd via recvmsg()
// mmap(NULL, size, PROT_READ|PROT_WRITE, MAP_SHARED, received_fd, 0)
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Sealing (requires MFD_ALLOW_SEALING) — add immutable constraints to the file. Once a seal is set it cannot be removed. Useful for handing read-only data to untrusted code.")
                    CodeBlock("""
#include <linux/memfd.h>
// After writing data, seal against further writes:
fcntl(fd, F_ADD_SEALS, F_SEAL_WRITE | F_SEAL_GROW | F_SEAL_SHRINK);
// Now no process can modify size or content of this memfd
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Available seals: F_SEAL_SEAL (no more seals can be added), F_SEAL_SHRINK (cannot reduce size), F_SEAL_GROW (cannot increase size), F_SEAL_WRITE (no writes), F_SEAL_FUTURE_WRITE (no new writable mappings).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Comparison with other shared memory mechanisms:")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("shm_open(name, ...) — POSIX standard, creates a named entry under /dev/shm, accessible by name across processes, but requires cleanup (shm_unlink).")
                    BodyText("mmap(MAP_ANONYMOUS|MAP_SHARED) — only works between a parent and its forked children, not unrelated processes.")
                    BodyText("shmget() (SysV) — legacy integer-key API, still works but not recommended for new code.")
                    BodyText("memfd_create — most flexible: no filesystem path, supports sealing, works between unrelated processes via fd passing.")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "Memory Protection — PROT Flags") {
                    BodyText("Every virtual memory page has its own independent set of protection bits stored in its Page Table Entry (PTE). The MMU enforces these permissions in hardware on every single memory access — there is no software check. A violation instantly raises SIGSEGV (segmentation fault).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The four PROT flags (from <sys/mman.h>):")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("PROT_READ — the page may be read. Accessing a page without this flag = SIGSEGV.")
                    BodyText("PROT_WRITE — the page may be written. Writing without this flag = SIGSEGV.")
                    BodyText("PROT_EXEC — the page may be executed as machine code. On x86-64 this clears the NX (No-eXecute) bit in the PTE; on ARM64 it clears the XN (eXecute Never) bit. Without it, the CPU refuses to fetch instructions from the page even if the bytes are valid code.")
                    BodyText("PROT_NONE — no access whatsoever. Any read, write, or execute attempt = SIGSEGV.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Common combinations and where you see them:")
                    CodeBlock("""
PROT_READ | PROT_WRITE   // heap, stack, .data, .bss segments
PROT_READ | PROT_EXEC    // .text (code) segment
PROT_READ                // .rodata (string literals, const globals)
PROT_NONE                // guard pages (see below)
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Guard pages — a PROT_NONE page placed immediately below a thread stack. If the stack grows too large and overflows into that page, the hardware raises a clean SIGSEGV instead of silently corrupting the next allocation. The kernel inserts a guard page automatically for the main stack and for pthread-created thread stacks.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("W^X (Write XOR Execute) — a hardened security policy: a page must never be both writable and executable at the same time. This defeats code-injection attacks: an attacker can write shellcode into a writable page but cannot execute it; if they later flip it to executable, it is no longer writable. Modern kernels enforce this for anonymous mappings: requesting PROT_WRITE | PROT_EXEC on anonymous memory returns EPERM on SELinux/grsecurity-hardened systems.")
                    CodeBlock("""
// JIT compiler pattern: write code first, then flip to executable
void *buf = mmap(NULL, page_size,
                 PROT_READ | PROT_WRITE,   // writable for writing bytecode
                 MAP_ANONYMOUS | MAP_PRIVATE, -1, 0);
// ... write machine instructions into buf ...
mprotect(buf, page_size, PROT_READ | PROT_EXEC); // now executable, no longer writable
// ... call the JIT'd code ...
munmap(buf, page_size);
                    """.trimIndent())
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "mprotect() and Page Alignment") {
                    BodyText("mprotect() changes the protection flags on a range of pages in the calling process.")
                    CodeBlock("""
#include <sys/mman.h>
int mprotect(void *addr, size_t len, int prot);
// Returns 0 on success, -1 on error (sets errno)
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("addr MUST be page-aligned. It must be an exact multiple of the system page size. The kernel does NOT round it for you — passing a non-aligned address returns EINVAL immediately.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("len does not need to be page-aligned. The kernel automatically rounds it up to the next page boundary. So mprotect(addr, 1, PROT_READ) protects one full page.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Finding the page size — three equivalent ways:")
                    CodeBlock("""
#include <unistd.h>
#include <sys/auxv.h>

long ps1 = sysconf(_SC_PAGESIZE); // POSIX preferred, returns long
int  ps2 = getpagesize();         // legacy POSIX, returns int
long ps3 = getauxval(AT_PAGESZ);  // fastest: reads auxv, no syscall
// All three return 4096 on x86-64 standard Linux
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Computing a page-aligned base for an arbitrary pointer — since page_size is always a power of 2, masking off the low bits gives the page base:")
                    CodeBlock("""
size_t page_size = sysconf(_SC_PAGESIZE);  // e.g. 4096

// Page-align addr downward (floor to page boundary):
void *page_base = (void *)((uintptr_t)addr & ~(page_size - 1));

// Example: addr = 0x7fff'1234'5678
// page_size - 1 = 0xFFF
// ~(page_size-1) = 0xFFFF'FFFF'FFFF'F000
// result         = 0x7fff'1234'5000  (4 KB page base)
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Full example — guard page below a manually-allocated stack:")
                    CodeBlock("""
size_t ps = sysconf(_SC_PAGESIZE);
size_t stack_size = 64 * ps;

// Allocate stack + 1 guard page below it
void *mem = mmap(NULL, stack_size + ps,
                 PROT_READ | PROT_WRITE,
                 MAP_ANONYMOUS | MAP_PRIVATE, -1, 0);

// First page becomes the guard (no access)
mprotect(mem, ps, PROT_NONE);

void *stack_top = (char *)mem + stack_size + ps;
// stack grows down from stack_top; guard page at bottom
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Common errno values from mprotect: EINVAL — addr not page-aligned, or invalid prot combination; EACCES — trying to add PROT_WRITE to a read-only file mapping (fd was opened O_RDONLY); EPERM — W^X policy violation on hardened kernel.")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "/proc/self/maps — The Process Memory Map") {
                    BodyText("/proc/self/maps is a kernel-maintained text file that lists every VMA (Virtual Memory Area) in the current process, one region per line. Reading it gives a complete picture of what is mapped, where, with what permissions, and backed by what file.")
                    CodeBlock("""
$ cat /proc/self/maps
55a3c4200000-55a3c4201000 r--p 00000000 08:01 1234567  /bin/cat
55a3c4201000-55a3c4204000 r-xp 00001000 08:01 1234567  /bin/cat
55a3c4204000-55a3c4205000 r--p 00004000 08:01 1234567  /bin/cat
55a3c4206000-55a3c4207000 rw-p 00005000 08:01 1234567  /bin/cat
7f9a12000000-7f9a12200000 rw-p 00000000 00:00 0        [heap]
7f9a14c00000-7f9a14dc0000 r--p 00000000 08:01 9876543  /lib/x86_64-linux-gnu/libc.so.6
7f9a14dc0000-7f9a14f48000 r-xp 001c0000 08:01 9876543  /lib/x86_64-linux-gnu/libc.so.6
7ffff7d00000-7ffff7d01000 r-xp 00000000 00:00 0        [vdso]
7ffffffde000-7ffffffff000 rw-p 00000000 00:00 0        [stack]
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Column breakdown:")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("start-end — virtual address range in hex.")
                    BodyText("perms — 4-character field: r/- (read), w/- (write), x/- (execute), p/s (p = private copy-on-write, s = shared mapping).")
                    BodyText("offset — byte offset into the backing file where this region starts (0 for anonymous mappings).")
                    BodyText("dev — major:minor device number of the backing file (00:00 for anonymous).")
                    BodyText("inode — inode number of the backing file (0 for anonymous).")
                    BodyText("pathname — absolute path of the backing file, or special labels: [heap], [stack], [vdso], [vsyscall], [stack:TID] for thread stacks, or blank for anonymous private mappings.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Reading /proc/self/maps programmatically:")
                    CodeBlock("""
FILE *maps = fopen("/proc/self/maps", "r");
char line[256];
while (fgets(line, sizeof(line), maps)) {
    unsigned long start, end;
    char perms[5], path[128] = "";
    unsigned long offset;
    unsigned int dev_major, dev_minor;
    unsigned long inode;
    sscanf(line, "%lx-%lx %4s %lx %x:%x %lu %127s",
           &start, &end, perms, &offset,
           &dev_major, &dev_minor, &inode, path);
    printf("%lx-%lx  %s  %s\n", start, end, perms, path);
}
fclose(maps);
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("/proc/self/smaps — extended version of maps. Each region is followed by detailed statistics: RSS (physical pages currently in RAM), PSS (proportional share for shared mappings), Private_Dirty (modified pages not shared), Swap, AnonHugePages (transparent huge pages), and kernel flags like THPeligible.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("/proc/self/smaps_rollup — a single aggregate block summing all smaps fields across every region. Fast way to get total RSS and PSS for the entire process without parsing every line.")
                    CodeBlock("""
$ cat /proc/self/smaps_rollup
00000000-00000000 ---p 00000000 00:00 0                  [rollup]
Rss:               12288 kB
Pss:                8192 kB
Pss_Dirty:          4096 kB
Private_Clean:      4096 kB
Private_Dirty:      4096 kB
Shared_Clean:       4096 kB
Swap:                  0 kB
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("pmap(1) — a shell command that reads /proc/pid/maps and formats it readably. pmap -x pid adds the smaps detail columns (RSS, dirty, etc.). Useful for quick inspection without writing code.")
                    CodeBlock("""
pmap $$           # current shell's memory map
pmap -x 1234      # extended detail for PID 1234
                    """.trimIndent())
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
