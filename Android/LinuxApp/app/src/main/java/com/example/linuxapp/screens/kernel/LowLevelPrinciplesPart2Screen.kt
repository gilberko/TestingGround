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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
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
                        "Kernel Mode Synchronization",
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
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("How the writer works:")
                    BodyText("seqlock_t embeds both a seqcount_t (the sequence counter) and a spinlock_t (guards writers against each other). write_seqlock() acquires the spinlock first — so only one writer can be active at a time — then increments the counter from even to odd (signalling \"write in progress\"). write_sequnlock() increments it back from odd to even (\"write done\") and releases the spinlock. The spinlock prevents two writers from incrementing simultaneously, keeping the even/odd invariant intact.")
                    CodeBlock(
                        """write_seqlock(&sl):
  spin_lock(&sl->lock);       /* one writer at a time */
  sl->seqcount.sequence++;    /* even → odd ("writing…") */
  smp_wmb();                  /* barrier: writes visible before data */

write_sequnlock(&sl):
  smp_wmb();                  /* barrier: data visible before counter */
  sl->seqcount.sequence++;    /* odd → even ("done") */
  spin_unlock(&sl->lock);"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("How the reader works:")
                    BodyText("read_seqbegin() spins until the sequence counter is even (no write in progress), adds a read memory barrier, and returns the counter value. While spinning it calls cpu_relax() — on x86 this emits the PAUSE instruction (see \"How Spinlocks Work Internally\" for details). read_seqretry() adds another read barrier then checks whether the counter changed. If a write completed during the read the counter moved (e.g. 4→5→6, so 6≠4). If a write is still in progress it is odd (5≠4). Either case returns true → retry.")
                    CodeBlock(
                        """read_seqbegin(&sl):
  do {
      seq = READ_ONCE(sl->seqcount.sequence);
      if (seq & 1) cpu_relax(); /* odd: write in progress, spin */
  } while (seq & 1);
  smp_rmb();   /* barrier before reading the protected data */
  return seq;

read_seqretry(&sl, seq):
  smp_rmb();   /* barrier after reading the protected data */
  return sl->seqcount.sequence != seq; /* changed or odd → retry */"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("The kernel uses seqlocks for things like the wall clock (jiffies_64) — millions of reads per second, very rare writes.")
                }
            }
            item {
                SectionCard(title = "Read-Write Semaphore (rw_semaphore)") {
                    BodyText("A read-write semaphore is the sleeping-lock counterpart to rwlock_t. Multiple readers can hold it simultaneously; a writer gets exclusive access. Because it can sleep, it is usable only in process context — never in atomic or interrupt context.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("API:")
                    CodeBlock(
                        """#include <linux/rwsem.h>

/* Static init: */
DECLARE_RWSEM(my_rwsem);

/* Dynamic init: */
struct rw_semaphore my_rwsem;
init_rwsem(&my_rwsem);

/* Multiple readers can hold simultaneously
   (sleeps if a writer currently holds it): */
down_read(&my_rwsem);
/* ... read shared data ... */
up_read(&my_rwsem);

/* Writer gets exclusive access
   (sleeps until all current readers/writers finish): */
down_write(&my_rwsem);
/* ... modify shared data ... */
up_write(&my_rwsem);

/* Interruptible variants — return -EINTR on signal: */
if (down_read_interruptible(&my_rwsem))
    return -EINTR;
if (down_write_killable(&my_rwsem))
    return -EINTR;

/* Downgrade a write lock to a read lock (no unlock needed): */
downgrade_write(&my_rwsem);"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("rw_semaphore vs rwlock_t — both allow concurrent reads and exclusive writes, but the locking strategy differs:")
                    CodeBlock(
                        """                  rwlock_t        rw_semaphore
Type              Spinlock        Semaphore (sleeping lock)
Waiting           Busy-spin       Sleep (put on wait queue)
Atomic/IRQ ctx    YES             NO (may sleep)
Process ctx       YES             YES
IRQ-safe variant  _irqsave        none — never use in IRQ context"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Use rwlock_t when readers or writers may run in interrupt context, or when the critical section is very short. Use rw_semaphore when the critical section may sleep or take a long time — for example when copying data to/from user space.")
                }
            }
            item {
                SectionCard(title = "RCU (Read-Copy-Update)") {
                    BodyText("RCU is a lock-free synchronization mechanism for read-mostly data structures. Read-side critical sections are essentially free — no locks, no atomics, no cache bouncing.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The principle:")
                    CodeBlock(
                        """Readers:
  - Call rcu_read_lock() / rcu_read_unlock() (no actual locking:
    on non-preemptible kernels it just disables preemption; on
    PREEMPT_RCU kernels it increments a per-task nesting counter)
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
            item {
                SectionCard(title = "RCU Grace Period") {
                    BodyText("A grace period is the time that must pass after a writer publishes a new pointer before the old data can be freed safely. It ends once every RCU read-side critical section that was already running when the grace period started has finished.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Readers that start after the publish don't matter. rcu_dereference() can only give them the new pointer, so the grace period never waits for them. That is why a grace period always finishes, even if new readers keep arriving.")
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """time ───────────────────────────────────────────────▶

CPU0  [reader A ── sees OLD ──]
CPU1        [reader B ── sees OLD ────────]
CPU2                 [reader C ─ sees NEW ─]   (doesn't matter)
                ▲                         ▲
writer:  rcu_assign_pointer(new)          │
                │◀──── grace period ─────▶│
                                          kfree(old) is now safe"""
                    )
                    BodyText("Quiescent state (QS): a point where a CPU is known not to be inside an RCU reader. Examples are a context switch, running in user mode, and the idle loop. Once every CPU has passed through a QS after the grace period started, no pre-existing reader can still be running.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("On PREEMPT_RCU kernels a reader can be preempted, so a context switch alone isn't enough. A task preempted inside rcu_read_lock() is put on its rcu_node's blocked-tasks list, and the grace period also waits for that list to drain.")
                }
            }
            item {
                SectionCard(title = "Async Reclamation — call_rcu()") {
                    BodyText("synchronize_rcu() blocks the caller (it sleeps) until a grace period has passed. call_rcu() is the asynchronous version: it queues a callback and returns immediately.")
                    CodeBlock(
                        """void call_rcu(struct rcu_head *head, rcu_callback_t func);
/* typedef void (*rcu_callback_t)(struct rcu_head *head); */"""
                    )
                    BodyText("After a grace period, the kernel calls func(head). Embed a struct rcu_head in your object and use container_of() to get back to the object:")
                    CodeBlock(
                        """struct mydata {
    int value;
    struct rcu_head rcu;
};

static void mydata_free_rcu(struct rcu_head *head)
{
    struct mydata *p = container_of(head, struct mydata, rcu);
    /* extra cleanup can go here (put refs, update stats ...) */
    kfree(p);
}

/* Writer (may hold a spinlock / be in atomic context): */
spin_lock(&my_lock);
old_p = rcu_replace_pointer(global_ptr, new_p,
                            lockdep_is_held(&my_lock));
spin_unlock(&my_lock);
call_rcu(&old_p->rcu, mydata_free_rcu);   /* returns at once */

/* Module exit: */
static void __exit my_exit(void)
{
    /* ... unpublish everything ... */
    rcu_barrier();   /* wait for ALL queued callbacks to run */
}"""
                    )
                    BodyText("Rules for call_rcu():")
                    BodyText("• The callback runs in softirq context (RCU_SOFTIRQ), or in an rcuo kthread when callbacks are offloaded (rcu_nocbs=). Either way it must NOT sleep.")
                    BodyText("• Don't touch the rcu_head again until the callback has run. Each object can be queued only once per grace period.")
                    BodyText("• rcu_barrier() before unloading a module is required. Without it, a queued callback could run after the module's code is gone, and the kernel crashes. synchronize_rcu() is NOT enough here, because it waits for readers, not for callbacks.")
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """synchronize_rcu()  blocks/sleeps   process ctx only   simplest
call_rcu(h, fn)    async           any ctx            custom cleanup
kfree_rcu(p, rcu)  async           any ctx            plain kfree,
                                                      no callback needed"""
                    )
                }
            }
            item {
                SectionCard(title = "Is the Grace Period Per-Structure? (No)") {
                    BodyText("Classic RCU has ONE grace period for the whole system. It doesn't matter how many RCU-protected structures you have (a routing table, a list of devices, your module's config pointer). synchronize_rcu() and call_rcu() all wait for the same thing: every pre-existing RCU reader on every CPU, no matter which data it reads.")
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """rcu_read_lock();
p = rcu_dereference(table_A);   /* reader of structure A   */
... long-running work ...
rcu_read_unlock();

/* Meanwhile, on another CPU: */
old_b = rcu_replace_pointer(table_B, new_b, ...);
synchronize_rcu();   /* ALSO waits for the reader of A above! */
kfree(old_b);"""
                    )
                    BodyText("Consequences:")
                    BodyText("• A slow reader anywhere delays reclamation everywhere. That is why classic RCU readers must be short and must not sleep.")
                    BodyText("• It's cheap: the kernel tracks a single grace period for all users, instead of per-structure reader counts.")
                    BodyText("• Since Linux 4.20 the old flavors (rcu_read_lock_bh() and rcu_read_lock_sched(), with their synchronize_rcu_bh/_sched) are consolidated. They all share the same grace period, and synchronize_rcu() waits for all of them.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("If you need an independent grace period, or readers that sleep, use SRCU (see below).")
                }
            }
            item {
                SectionCard(title = "How the Kernel Knows a Grace Period Ended (Tree RCU)") {
                    BodyText("Modern kernels use Tree RCU. Asking every CPU directly wouldn't scale to thousands of CPUs, so CPUs report into a tree of rcu_node structures:")
                    CodeBlock(
                        """                 ┌──────────────┐
                 │ root rcu_node│  qsmask: 0b11  (2 children)
                 └──────┬───────┘
           ┌────────────┴────────────┐
   ┌───────┴──────┐          ┌───────┴──────┐
   │ leaf rcu_node│          │ leaf rcu_node│   qsmask: one bit
   │ qsmask 0b1111│          │ qsmask 0b1111│   per CPU
   └─┬───┬───┬───┬┘          └─┬───┬───┬───┬┘
    CPU0 1   2   3            CPU4 5   6   7"""
                    )
                    BodyText("1. The grace-period kthread (rcu_preempt or rcu_sched, running rcu_gp_kthread()) starts a new grace period. It increments the GP sequence number and sets a qsmask bit for every online CPU in the leaf nodes.")
                    BodyText("2. Each CPU notices the new grace period (from its scheduling-clock tick or softirq), and at its next quiescent state (context switch, user mode, idle) it reports a QS by clearing its bit in its leaf node.")
                    BodyText("3. When a leaf's mask reaches 0, and its blocked-tasks list is empty on PREEMPT_RCU, the leaf clears its own bit in its parent. This continues up the tree under the per-node locks.")
                    BodyText("4. When the root's qsmask is 0, the grace period is over. The GP kthread records that it completed, and callbacks queued before it started become ready to run. They are invoked from RCU_SOFTIRQ, an rcuc kthread, or an rcuo kthread.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Idle and nohz_full CPUs: RCU doesn't wake a sleeping CPU. Through dyntick-idle tracking (a per-CPU counter updated on entering and leaving idle or nohz user mode), the GP kthread can see that the CPU is in an extended quiescent state and reports the QS on its behalf.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("A normal grace period takes milliseconds. synchronize_rcu_expedited() forces one through quickly by sending IPIs to CPUs. It is faster, but it disturbs every CPU, so use it sparingly.")
                }
            }
            item {
                SectionCard(title = "SRCU — Sleepable RCU with Its Own Domain") {
                    BodyText("Yes: SRCU (Sleepable RCU) lets you define your own domain. A domain is a struct srcu_struct. Every read lock, read unlock, synchronize and call_srcu takes that domain as an argument, and a grace period in one domain waits ONLY for readers of that same domain.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Two benefits:")
                    BodyText("• Readers may SLEEP (block on a mutex, allocate with GFP_KERNEL, do I/O). This is not allowed in classic RCU.")
                    BodyText("• Isolation: a slow or sleeping reader in your domain doesn't delay classic RCU or any other SRCU domain, and other subsystems' readers don't delay you.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("API (<linux/srcu.h>):")
                    CodeBlock(
                        """/* Define a domain */
DEFINE_SRCU(name);              /* global, usable from other files */
DEFINE_STATIC_SRCU(name);       /* static to this file             */
int  init_srcu_struct(struct srcu_struct *ssp);    /* dynamic */
void cleanup_srcu_struct(struct srcu_struct *ssp); /* must call */

/* Readers */
int  srcu_read_lock(struct srcu_struct *ssp);           /* returns idx */
void srcu_read_unlock(struct srcu_struct *ssp, int idx);/* pass it back */
p = srcu_dereference(ptr, ssp);

/* Updaters */
void synchronize_srcu(struct srcu_struct *ssp);          /* sleeps */
void synchronize_srcu_expedited(struct srcu_struct *ssp);
void call_srcu(struct srcu_struct *ssp, struct rcu_head *head,
               rcu_callback_t func);                      /* async  */
void srcu_barrier(struct srcu_struct *ssp);  /* wait for call_srcu cbs */"""
                    )
                    BodyText("The index returned by srcu_read_lock() says which of the domain's two per-CPU counter sets the reader incremented. srcu_read_unlock() must receive the same index, which is how SRCU tracks readers without per-structure locking.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Example: a config that readers use while sleeping:")
                    CodeBlock(
                        """#include <linux/srcu.h>
#include <linux/delay.h>

struct my_cfg {
    int  timeout_ms;
    char name[32];
    struct rcu_head rcu;
};

DEFINE_STATIC_SRCU(cfg_srcu);              /* our private domain */
static struct my_cfg __rcu *cur_cfg;
static DEFINE_MUTEX(cfg_update_lock);

/* Reader: may sleep inside the critical section */
static void use_cfg(void)
{
    struct my_cfg *c;
    int idx;

    idx = srcu_read_lock(&cfg_srcu);
    c = srcu_dereference(cur_cfg, &cfg_srcu);
    if (c) {
        msleep(c->timeout_ms);     /* OK in SRCU, ILLEGAL in RCU */
        pr_info("cfg %s\n", c->name);
    }
    srcu_read_unlock(&cfg_srcu, idx);
}

/* Writer */
static int set_cfg(int timeout, const char *name)
{
    struct my_cfg *new, *old;

    new = kzalloc(sizeof(*new), GFP_KERNEL);
    if (!new)
        return -ENOMEM;
    new->timeout_ms = timeout;
    strscpy(new->name, name, sizeof(new->name));

    mutex_lock(&cfg_update_lock);
    old = rcu_replace_pointer(cur_cfg, new,
                              lockdep_is_held(&cfg_update_lock));
    mutex_unlock(&cfg_update_lock);

    synchronize_srcu(&cfg_srcu);  /* waits ONLY for cfg_srcu readers */
    kfree(old);
    return 0;
}

static void __exit my_exit(void)
{
    struct my_cfg *c = rcu_replace_pointer(cur_cfg, NULL, true);

    synchronize_srcu(&cfg_srcu);
    kfree(c);
    srcu_barrier(&cfg_srcu);   /* if call_srcu() was ever used */
    /* cleanup_srcu_struct() is only needed for init_srcu_struct() */
}"""
                    )
                    BodyText("Costs: srcu_read_lock() and srcu_read_unlock() do real work (per-CPU counter increments plus a memory barrier), so they are slower than rcu_read_lock(). Grace periods are also usually longer. Use SRCU only when you need sleeping readers or an isolated domain.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Real users: KVM (memslots, kvm->srcu), SRCU notifier chains, fsnotify, and the device-mapper table swap.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Related: Tasks RCU (synchronize_rcu_tasks / call_rcu_tasks) is another separate domain. Its quiescent state is a voluntary context switch, and it is used by tracing and BPF trampolines to free code that a task might be executing.")
                }
            }
            item {
                SectionCard(title = "RCU Hash Table with Reference Counting") {
                    BodyText("Yes, there is a macro that both selects the bucket AND walks it in an RCU-safe way. <linux/hashtable.h> provides:")
                    CodeBlock(
                        """DEFINE_HASHTABLE(name, bits);          /* 2^bits buckets of hlist */
hash_add_rcu(table, &obj->node, key);  /* publish into a bucket   */
hash_del_rcu(&obj->node);              /* unlink (readers safe)   */

hash_for_each_possible_rcu(table, obj, member, key)
/* = hlist_for_each_entry_rcu(obj,
         &table[hash_min(key, HASH_BITS(table))], member)
   picks the ONE bucket for key and walks it with
   rcu_dereference() on every ->next pointer             */"""
                    )
                    BodyText("Note: the macro visits every entry in that bucket (hash collisions included), so you still compare obj->key == key yourself.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("RCU keeps the memory valid during the walk. A reference count lets the caller keep using the object AFTER rcu_read_unlock():")
                    CodeBlock(
                        """#include <linux/hashtable.h>
#include <linux/refcount.h>

struct conn {
    u32               key;
    refcount_t        ref;
    struct hlist_node node;
    struct rcu_head   rcu;
    /* ... payload ... */
};

static DEFINE_HASHTABLE(conn_table, 8);    /* 256 buckets */
static DEFINE_SPINLOCK(conn_lock);         /* serializes writers */

static void conn_put(struct conn *c)
{
    if (refcount_dec_and_test(&c->ref))
        kfree_rcu(c, rcu);   /* readers may still be walking it */
}

/* Lookup: lock-free, returns a referenced object or NULL */
static struct conn *conn_lookup(u32 key)
{
    struct conn *c;

    rcu_read_lock();
    hash_for_each_possible_rcu(conn_table, c, node, key) {
        if (c->key == key && refcount_inc_not_zero(&c->ref)) {
            rcu_read_unlock();
            return c;            /* caller must conn_put(c) */
        }
    }
    rcu_read_unlock();
    return NULL;
}

/* Insert: table holds one reference */
static void conn_add(struct conn *c)
{
    refcount_set(&c->ref, 1);
    spin_lock(&conn_lock);
    hash_add_rcu(conn_table, &c->node, c->key);
    spin_unlock(&conn_lock);
}

/* Remove: unlink, then drop the table's reference */
static void conn_remove(struct conn *c)
{
    spin_lock(&conn_lock);
    hash_del_rcu(&c->node);
    spin_unlock(&conn_lock);
    conn_put(c);
}

/* Usage */
struct conn *c = conn_lookup(42);
if (c) {
    do_something_that_may_sleep(c);   /* safe: we hold a ref */
    conn_put(c);
}"""
                    )
                    BodyText("Why refcount_inc_not_zero() and not refcount_inc()? A reader can find an object that another CPU has just unlinked and whose count has already reached 0 (it is waiting for kfree_rcu). RCU guarantees the memory is still there, but the object is dead. inc_not_zero fails in that case, so the reader treats it as \"not found\" instead of reviving a freed object.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Lower-level building blocks: hlist_add_head_rcu, hlist_del_rcu, hlist_for_each_entry_rcu (for your own bucket arrays), and list_for_each_entry_rcu for plain lists. kref_get_unless_zero() is the kref equivalent of refcount_inc_not_zero().")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("For large or growing tables, use rhashtable (<linux/rhashtable.h>). It resizes automatically in the background, and rhashtable_lookup_fast() / rhashtable_lookup() are RCU-aware lookups. The networking stack uses it heavily.")
                }
            }
            item {
                SectionCard(title = "Quick Reference — Which Primitive to Use?") {
                    PrimitiveEntry(
                        name = "mutex",
                        atomic = "NO",
                        useWhen = "Process context; long critical sections; need an interruptible or killable wait."
                    )
                    PrimitiveEntry(
                        name = "spinlock",
                        atomic = "YES",
                        useWhen = "Short critical sections in any context, including IRQ handlers."
                    )
                    PrimitiveEntry(
                        name = "rwlock_t",
                        atomic = "YES",
                        useWhen = "Many concurrent readers in IRQ or atomic context; rare writes; short sections."
                    )
                    PrimitiveEntry(
                        name = "seqlock",
                        atomic = "YES",
                        useWhen = "Read-mostly simple values (counters, timestamps). Writers never block readers; readers retry if a write overlapped."
                    )
                    PrimitiveEntry(
                        name = "rw_semaphore",
                        atomic = "NO",
                        useWhen = "Many concurrent readers in process context; longer sections that may sleep."
                    )
                    PrimitiveEntry(
                        name = "RCU",
                        atomic = "Readers: YES. Writers: synchronize_rcu() NO (sleeps); call_rcu() / kfree_rcu() YES.",
                        useWhen = "Read-mostly pointer-based structures (lists, hash tables, config pointers); extreme read scalability; lock-free reads. Readers must not sleep."
                    )
                    PrimitiveEntry(
                        name = "SRCU",
                        atomic = "Readers: NO (they may sleep). call_srcu() YES; synchronize_srcu() NO.",
                        useWhen = "Like RCU, but readers need to sleep or you need a grace-period domain isolated from the rest of the kernel.",
                        last = true
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun PrimitiveEntry(name: String, atomic: String, useWhen: String, last: Boolean = false) {
    Text(
        text = name,
        color = Color(0xFF00FF41),
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp
    )
    Spacer(modifier = Modifier.height(2.dp))
    BodyText("Atomic/IRQ: $atomic")
    BodyText("Use When: $useWhen")
    if (!last) {
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            color = Color(0xFF00FF41).copy(alpha = 0.2f)
        )
    }
}
