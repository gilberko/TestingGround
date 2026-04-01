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
fun LowLevelPrinciplesPart2Screen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Low Level Principles Pt. 2",
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
                SectionCard(title = "Synchronization Overview") {
                    BodyText("The kernel runs on multiple CPUs simultaneously and can be preempted or interrupted at any time. Without synchronization, concurrent access to shared data causes races, corruption, and crashes.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Choosing the right primitive depends on two questions:")
                    CodeBlock(
                        """1. What context will the code run in?
   Process context → can sleep → use mutex
   Interrupt / atomic context → cannot sleep → use spinlock

2. How long is the critical section?
   Very short (a few instructions) → spinlock OK
   Long (may allocate, do I/O) → must use mutex"""
                    )
                }
            }
            item {
                SectionCard(title = "Mutex") {
                    BodyText("A mutex (mutual exclusion lock) is a sleeping lock. If the mutex is already held, the caller sleeps until it becomes available. This makes mutexes usable only in process context.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("When you CAN use a mutex:")
                    CodeBlock(
                        """✓ System call handlers
✓ Kernel threads (kthread_run)
✓ Workqueue workers
✓ Module init / exit
✓ Any place where sleeping is safe (not in_interrupt())

✗ Interrupt handlers (hardirq / softirq)
✗ While holding a spinlock
✗ While preemption is disabled"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Mutex API:")
                    CodeBlock(
                        """#include <linux/mutex.h>

/* Static initialization: */
DEFINE_MUTEX(my_mutex);

/* Dynamic initialization: */
struct mutex my_mutex;
mutex_init(&my_mutex);

/* Lock — sleeps if not available: */
mutex_lock(&my_mutex);

/* Try to lock — returns 1 if acquired, 0 if busy (non-blocking): */
if (mutex_trylock(&my_mutex)) {
    /* got the lock */
    mutex_unlock(&my_mutex);
}

/* Lock, but return -EINTR if a signal arrives: */
if (mutex_lock_interruptible(&my_mutex))
    return -EINTR;

/* Unlock: */
mutex_unlock(&my_mutex);

/* Destroy (call in cleanup when done): */
mutex_destroy(&my_mutex);"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("A mutex must always be unlocked by the same task that locked it. It cannot be used to pass ownership between tasks (use a semaphore for that).")
                }
            }
            item {
                SectionCard(title = "Spinlock") {
                    BodyText("A spinlock is a busy-wait lock. Instead of sleeping, the waiting CPU loops (spins) checking the lock repeatedly. Because it never sleeps, it is safe in any context — including interrupt handlers.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("When to use a spinlock:")
                    CodeBlock(
                        """✓ Interrupt handlers
✓ Softirq / tasklet
✓ When the critical section is very short (< a few µs)
✓ When you need to protect data accessed from both
  process context AND interrupt context
✗ Do not use if the critical section may sleep
✗ Do not hold a spinlock for a long time — other CPUs
  waste cycles spinning"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Spinlock API:")
                    CodeBlock(
                        """#include <linux/spinlock.h>

/* Static initialization: */
DEFINE_SPINLOCK(my_lock);

/* Dynamic initialization: */
spinlock_t my_lock;
spin_lock_init(&my_lock);

/* Basic lock/unlock (disables preemption on local CPU): */
spin_lock(&my_lock);
/* ... critical section ... */
spin_unlock(&my_lock);

/* If accessed from interrupt context — MUST use irqsave
   to disable interrupts on local CPU too: */
unsigned long flags;
spin_lock_irqsave(&my_lock, flags);
/* ... critical section ... */
spin_unlock_irqrestore(&my_lock, flags);

/* Non-blocking attempt: */
if (spin_trylock(&my_lock)) {
    /* got the lock */
    spin_unlock(&my_lock);
}"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Rule: if any interrupt handler accesses the same data as your process-context code, always use spin_lock_irqsave — otherwise a deadlock is possible if an interrupt fires while the lock is held.")
                }
            }
            item {
                SectionCard(title = "How Spinlocks Work Internally") {
                    BodyText("On a uniprocessor kernel (CONFIG_SMP not set), a spinlock degenerates to just disabling preemption — there is no other CPU to compete, so no actual spinning is needed.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("On SMP (multi-core), Linux uses queued spinlocks (qspinlock since ~4.2):")
                    CodeBlock(
                        """Classic spinlock (simplified concept):
  typedef struct { volatile int locked; } spinlock_t;

  spin_lock:
    while (atomic_cmpxchg(&lock->locked, 0, 1) != 0)
        cpu_relax();  /* hint: yield the pipeline */

  spin_unlock:
    WRITE_ONCE(lock->locked, 0);

Problems with classic spinlock on many-core systems:
  - All waiters watch the same cache line → massive
    cache bouncing (thundering herd on unlock)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Queued spinlock (qspinlock) — solves thundering herd:")
                    CodeBlock(
                        """Each contending CPU claims a position in a virtual queue
(using a per-CPU MCS node). It spins on its OWN node
rather than the global lock word.

On unlock, only the next CPU in the queue is woken.
Result: one cache-line ping-pong per handoff instead of N.

Implemented in arch/x86/include/asm/qspinlock.h using
LOCK CMPXCHG instructions for atomic operations."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("cpu_relax() is critical inside spin loops:")
                    CodeBlock(
                        """On x86: emits the PAUSE instruction.
  - Hints to the CPU that this is a spin-wait loop.
  - Reduces power consumption.
  - Prevents the CPU's pipeline from speculating
    too far ahead on the loop.
  - On hyperthreaded CPUs, yields execution resources
    to the sibling hardware thread."""
                    )
                }
            }
            item {
                SectionCard(title = "Read-Write Spinlock") {
                    BodyText("When data is frequently read but rarely written, a read-write spinlock allows multiple concurrent readers while giving writers exclusive access:")
                    CodeBlock(
                        """#include <linux/spinlock.h>

rwlock_t my_rwlock = __RW_LOCK_UNLOCKED(my_rwlock);

/* Multiple readers can hold this simultaneously: */
read_lock(&my_rwlock);
/* ... read shared data ... */
read_unlock(&my_rwlock);

/* Writer gets exclusive access — waits for all readers: */
write_lock(&my_rwlock);
/* ... modify shared data ... */
write_unlock(&my_rwlock);

/* IRQ-safe variants (same rule as spinlock): */
unsigned long flags;
read_lock_irqsave(&my_rwlock, flags);
read_unlock_irqrestore(&my_rwlock, flags);
write_lock_irqsave(&my_rwlock, flags);
write_unlock_irqrestore(&my_rwlock, flags);"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Caveat: if writes are frequent, readers can starve writers. For read-heavy workloads with rare writes, consider RCU instead.")
                }
            }
            item {
                SectionCard(title = "Seqlock") {
                    BodyText("A seqlock is optimized for data that is read very frequently but written rarely. Writers are never blocked. Readers detect if a write occurred and retry:")
                    CodeBlock(
                        """#include <linux/seqlock.h>

seqlock_t my_seqlock = SEQLOCK_UNLOCKED;

/* Writer (never blocks — always proceeds immediately): */
write_seqlock(&my_seqlock);
/* ... update shared data ... */
write_sequnlock(&my_seqlock);

/* Reader (retries if a write occurred during read): */
unsigned int seq;
do {
    seq = read_seqbegin(&my_seqlock);
    /* ... copy shared data into local vars ... */
} while (read_seqretry(&my_seqlock, seq));
/* Use the local copy — not the shared data directly */"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("The kernel uses seqlocks for things like the wall clock (jiffies_64) — millions of reads per second, very rare writes.")
                }
            }
            item {
                SectionCard(title = "RCU (Read-Copy-Update)") {
                    BodyText("RCU is a lock-free synchronization mechanism for read-mostly data structures. Read-side critical sections are essentially free — no locks, no atomics, no cache bouncing.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The principle:")
                    CodeBlock(
                        """Readers:
  - Call rcu_read_lock() / rcu_read_unlock() (no actual locking,
    just disables preemption)
  - Access data via rcu_dereference() which includes a
    read-side data dependency barrier
  - Never see a partially-updated structure

Writers:
  - Make a COPY of the data structure
  - Update the copy
  - Atomically publish the new pointer (rcu_assign_pointer)
  - Wait for all existing readers to finish (grace period)
  - Free the old copy"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("RCU API:")
                    CodeBlock(
                        """#include <linux/rcupdate.h>

struct mydata {
    int value;
    struct rcu_head rcu;  /* for kfree_rcu */
};

/* Global pointer protected by RCU: */
struct mydata __rcu *global_ptr;

/* Reader: */
rcu_read_lock();
struct mydata *p = rcu_dereference(global_ptr);
if (p)
    printk(KERN_INFO "value=%d\n", p->value);
rcu_read_unlock();
/* Do NOT use p after rcu_read_unlock! */

/* Writer: */
struct mydata *new_p = kmalloc(sizeof(*new_p), GFP_KERNEL);
new_p->value = 42;
struct mydata *old_p = rcu_replace_pointer(
    global_ptr, new_p, lockdep_is_held(&my_lock));

/* Wait for all pre-existing readers to finish, then free: */
synchronize_rcu();
kfree(old_p);

/* Or use kfree_rcu to free automatically after grace period: */
kfree_rcu(old_p, rcu);"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("RCU is used extensively in the kernel for routing tables, process lists, module lists, and network protocol data structures.")
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
