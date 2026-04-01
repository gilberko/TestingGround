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
fun LowLevelPrinciplesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Low Level Principles",
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
                SectionCard(title = "Interrupt Context") {
                    BodyText("When a hardware or software interrupt fires, the CPU jumps to the kernel's interrupt handler. You are now in interrupt context — a fundamentally different execution environment:")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Rules in interrupt context:")
                    CodeBlock(
                        """- No sleeping or blocking (no mutex_lock, msleep, wait_event)
- No access to user-space memory (no copy_from_user)
- Very limited stack size (~4 KB on x86_64)
- No process context — current may point to any task
- Must complete quickly (latency-sensitive)
- Can use spin_lock / spin_lock_irqsave
- Can use kmalloc(GFP_ATOMIC) for allocations""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Check if you are in interrupt context at runtime:")
                    CodeBlock(
                        """if (in_interrupt()) {
    /* in interrupt context (hardirq or softirq) */
}
if (in_irq()) {
    /* specifically in a hardirq handler */
}""")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("If you need to do heavy work from an interrupt handler, defer it to a workqueue (process context) or tasklet (softirq context).")
                }
            }
            item {
                SectionCard(title = "Atomic Context") {
                    BodyText("Atomic context is any situation where sleeping is forbidden because the kernel cannot safely schedule another process:")
                    CodeBlock(
                        """Atomic context occurs when:
- A spinlock is held  (spin_lock / spin_lock_irq / etc.)
- Interrupts are disabled  (local_irq_disable)
- Preemption is disabled  (preempt_disable)
- Inside an RCU read-side critical section  (rcu_read_lock)
- Inside any interrupt context (hardirq / softirq)""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("You can check at runtime (debug builds):")
                    CodeBlock(
                        """if (in_atomic()) {
    /* preemption disabled or spinlock held */
}""")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("The kernel's debug option CONFIG_DEBUG_ATOMIC_SLEEP will BUG() if you call a sleeping function while in atomic context — very useful during development.")
                }
            }
            item {
                SectionCard(title = "When to Sleep / When Not To") {
                    BodyText("CAN sleep (process context):")
                    CodeBlock(
                        """- System call handlers
- Kernel threads (kthread_run)
- Workqueue workers
- Module init/exit functions

Safe sleepy operations:
  mutex_lock(&my_mutex)        /* may block */
  wait_event(wq, condition)    /* blocks until true */
  msleep(100)                  /* sleep 100 ms */
  kmalloc(size, GFP_KERNEL)    /* may sleep to reclaim */
  copy_from_user / copy_to_user""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("CANNOT sleep (atomic / interrupt context):")
                    CodeBlock(
                        """- Interrupt handlers (hardirq / softirq)
- While holding a spinlock
- While preemption is disabled

Must use non-sleeping alternatives:
  spin_lock(&my_lock)          /* never sleeps */
  kmalloc(size, GFP_ATOMIC)    /* never sleeps, may fail */
  /* No blocking wait — use flags + tasklets instead */""")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("The fundamental rule: if there is any code path that could call your function from interrupt context, you must use spinlocks and GFP_ATOMIC throughout.")
                }
            }
            item {
                SectionCard(title = "Memory Barriers") {
                    BodyText("Modern CPUs and compilers reorder memory accesses for performance. In concurrent kernel code this can cause subtle bugs. Memory barriers prevent reordering:")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Compiler-only barrier (prevents compiler reordering, no CPU effect):")
                    CodeBlock("barrier();")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("SMP (multi-core) barriers — also affect the CPU:")
                    CodeBlock(
                        """smp_mb()   /* full barrier: no reads OR writes cross this point */
smp_rmb()  /* read barrier: no reads reordered across this */
smp_wmb()  /* write barrier: no writes reordered across this */""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("For simple shared flags, prefer the safer accessor macros:")
                    CodeBlock(
                        """/* Prevents compiler from caching or eliding the access */
int val = READ_ONCE(shared_flag);
WRITE_ONCE(shared_flag, 1);""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Classic example — without barriers, the CPU may execute the stores out of order:")
                    CodeBlock(
                        """/* Producer */
data = 42;
smp_wmb();      /* ensure data is visible before flag */
flag = 1;

/* Consumer */
while (!READ_ONCE(flag));
smp_rmb();      /* ensure flag read before data read */
use(data);      /* guaranteed to see data = 42 */""")
                }
            }
            item {
                SectionCard(title = "Paging") {
                    BodyText("The kernel uses a virtual memory system based on paging. Every address a process (or the kernel) sees is a virtual address translated to a physical address by the MMU.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Address space layout:")
                    CodeBlock(
                        """32-bit x86:
  0x00000000 – 0xBFFFFFFF  →  user space (3 GB)
  0xC0000000 – 0xFFFFFFFF  →  kernel space (1 GB)

64-bit x86_64:
  0x0000000000000000 – 0x00007FFFFFFFFFFF  →  user (128 TB)
  0xFFFF800000000000 – 0xFFFFFFFFFFFFFFFF  →  kernel (128 TB)""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Page table hierarchy (x86_64, 4-level):")
                    CodeBlock(
                        """Virtual address → PGD → PUD → PMD → PTE → physical page

PGD = Page Global Directory   (top level)
PUD = Page Upper Directory
PMD = Page Middle Directory
PTE = Page Table Entry        (points to 4 KB physical frame)""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Page fault: if the PTE is not present, the CPU raises a #PF exception. The kernel's page fault handler decides: valid access (demand page it in) or invalid (SIGSEGV or kernel oops).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Useful kernel macros:")
                    CodeBlock(
                        """PAGE_SIZE           /* typically 4096 bytes */
PAGE_SHIFT          /* log2(PAGE_SIZE) = 12 */
pfn_to_page(pfn)    /* physical frame number → struct page */
page_to_pfn(page)   /* struct page → physical frame number */
virt_to_phys(addr)  /* kernel virtual → physical (direct map) */
phys_to_virt(addr)  /* physical → kernel virtual */""")
                }
            }
            item {
                SectionCard(title = "User Mode → Kernel Mode (System Calls)") {
                    BodyText("A system call is the controlled mechanism by which user space requests kernel services. The CPU hardware enforces the privilege level switch.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("x86 mechanisms over the years:")
                    CodeBlock(
                        """int 0x80        — legacy software interrupt (32-bit Linux)
                  Slow: saves full register state, goes through IDT

sysenter/sysexit — Intel fast system call (32-bit, P6+)
                  Faster: dedicated MSRs, less overhead

syscall/sysret   — AMD64 fast system call (64-bit, used today)
                  Fastest: only saves minimal state, uses MSRs""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("VDSO (Virtual Dynamic Shared Object):")
                    CodeBlock(
                        """The kernel maps a small shared library into every process's
address space at a random address (part of ASLR).

It contains:
  - The optimal syscall entry code for this CPU
  - Fast implementations of clock_gettime, gettimeofday
    (no actual syscall needed — reads kernel data directly)

User code calls glibc → glibc uses VDSO → fast path or syscall""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Kernel side — how syscalls are defined and dispatched:")
                    CodeBlock(
                        """/* Define a syscall in the kernel: */
SYSCALL_DEFINE2(myread, int, fd, char __user *, buf)
{
    /* kernel implementation */
    return count;
}

/* The syscall table maps numbers to handlers: */
sys_call_table[__NR_read] = sys_read;

/* What happens on syscall instruction (x86_64):
   1. CPU switches to ring 0, loads kernel stack
   2. Saves user rip, rsp, rflags
   3. Jumps to entry_SYSCALL_64
   4. Kernel looks up syscall number (rax) in sys_call_table
   5. Calls the handler with args from rdi,rsi,rdx,r10,r8,r9
   6. Returns result in rax, sysret back to user mode */""")
                }
            }
            item {
                SectionCard(title = "printk and dmesg") {
                    BodyText("printk() is the kernel's logging function. It writes messages to the kernel ring buffer, which dmesg reads.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Basic usage:")
                    CodeBlock(
                        """printk(KERN_INFO "Hello from driver, value=%d\n", val);

/* Convenience macros (preferred): */
pr_info("Hello from driver, value=%d\n", val);
pr_err("Something went wrong: %d\n", ret);
pr_warn("Suspicious condition detected\n");
pr_debug("Verbose debug info\n");  /* stripped unless DEBUG defined */""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Log levels (lower number = higher priority):")
                    CodeBlock(
                        """KERN_EMERG   "0"  — system is unusable
KERN_ALERT   "1"  — action must be taken immediately
KERN_CRIT    "2"  — critical conditions
KERN_ERR     "3"  — error conditions
KERN_WARNING "4"  — warning conditions
KERN_NOTICE  "5"  — normal but significant
KERN_INFO    "6"  — informational
KERN_DEBUG   "7"  — debug-level messages""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Reading the kernel log:")
                    CodeBlock(
                        """dmesg               # print the entire ring buffer
dmesg | tail -20    # last 20 lines
dmesg -w            # follow in real time (like tail -f)
dmesg -T            # human-readable timestamps
dmesg -c            # print and clear the buffer
dmesg -l err,warn   # filter by level""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Raw access:")
                    CodeBlock(
                        """/proc/kmsg          — raw kernel log device (one reader only)
/dev/kmsg           — structured log device (multiple readers OK)
journalctl -k       # systemd: show kernel messages""")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Common mistake: forgetting \\n at the end of a printk string. Without it, the message may be buffered and not appear until the next newline is printed — or not at all.")
                }
            }
            item {
                SectionCard(title = "CPU Cache and Cache Lines") {
                    BodyText("Modern CPUs have multiple levels of fast SRAM (cache) between the processor and main DRAM. Accessing data in cache is orders of magnitude faster than going to RAM:")
                    CodeBlock(
                        """L1 cache  — ~32–64 KB per core     — ~4 CPU cycles latency
L2 cache  — ~256 KB–1 MB per core  — ~12 CPU cycles latency
L3 cache  — ~8–64 MB shared        — ~40 CPU cycles latency
DRAM      — gigabytes               — ~200 CPU cycles latency"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Cache Line — the unit of transfer between cache and RAM:")
                    CodeBlock(
                        """A cache line is typically 64 bytes on x86/ARM64.

When you access a single byte, the entire 64-byte cache
line containing it is loaded into cache. This means:

  • Sequential array access is fast — each cache load
    covers 16 ints, so most hits are already in cache.
  • Random pointer chasing is slow — each access may
    miss and pay ~200 cycles to fetch from RAM."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("False Sharing — a subtle SMP performance bug:")
                    CodeBlock(
                        """/* BAD: counter_a and counter_b share a 64-byte cache line.
   Core 0 writes counter_a, Core 1 writes counter_b.
   Each write invalidates the other core's copy of the line,
   causing the line to bounce between cores — even though
   they are writing to DIFFERENT variables. */
struct {
    int counter_a;   /* core 0 uses this */
    int counter_b;   /* core 1 uses this */
} shared;

/* GOOD: put each hot variable on its own cache line */
struct {
    int counter_a;
    int __pad[15];   /* pad to 64 bytes (15 ints × 4 = 60 + 4 = 64) */
} ____cacheline_aligned_in_smp;"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Kernel alignment macros:")
                    CodeBlock(
                        """/* Align a struct to the start of a cache line: */
struct my_hot_data {
    spinlock_t lock;
    int        count;
} ____cacheline_aligned_in_smp;

/* Align a standalone variable: */
static int my_counter ____cacheline_aligned_in_smp;"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Cache Coherency — MESI Protocol:")
                    CodeBlock(
                        """Each cache line is in one of four states per core:
  Modified  — dirty, only in this core's cache; must write back
  Exclusive — clean, only in this core's cache
  Shared    — clean, may exist in multiple cores' caches
  Invalid   — not present in this core's cache

When core A writes to a Shared line, it broadcasts an
invalidation to all other cores — they must re-fetch the
line from memory or from core A's cache on next access.
On many-core systems this coherency traffic is significant."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Prefetching — hint the CPU to load data before you need it:")
                    CodeBlock(
                        """prefetch(ptr);    /* hint: prefetch *ptr for reading */
prefetchw(ptr);   /* hint: prefetch *ptr for writing  */

/* Example: prefetch the next element while processing current */
for (i = 0; i < n - 1; i++) {
    prefetch(&array[i + 1]);
    process(array[i]);
}"""
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
