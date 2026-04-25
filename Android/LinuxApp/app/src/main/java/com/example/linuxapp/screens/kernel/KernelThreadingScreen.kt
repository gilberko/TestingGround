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
fun KernelThreadingScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Kernel Threading",
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
                SectionCard(title = "kthreads vs Workqueues") {
                    BodyText(
                        "There are two main ways to run code asynchronously in the kernel: dedicated kernel " +
                        "threads (kthreads) and workqueues."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "kthreads are full kernel threads with their own stack and scheduling slot. They are " +
                        "persistent and long-lived — ideal for continuous background loops (polling a device, " +
                        "draining a queue, monitoring a condition indefinitely)."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Workqueues use a shared pool of worker kernel threads. You submit discrete work items " +
                        "that are picked up and executed. They are lighter weight and better suited for " +
                        "occasional, one-shot tasks triggered by events (e.g., the bottom half of an interrupt)."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "kthread API") {
                    BodyText(
                        "kthread_run() creates and immediately starts a kernel thread. It returns a " +
                        "task_struct pointer — always check IS_ERR() before using it."
                    )
                    CodeBlock(
                        """#include <linux/kthread.h>

static int my_thread_fn(void *data) {
    while (!kthread_should_stop()) {
        /* process context: CAN sleep, call schedule(),
           msleep(), wait_event(), mutex_lock(), etc. */
        msleep(100);
    }
    return 0;
}

/* Start: */
struct task_struct *task =
    kthread_run(my_thread_fn, NULL, "my_kthread");
if (IS_ERR(task))
    return PTR_ERR(task);

/* Stop (sets the stop flag, waits for the thread to return): */
kthread_stop(task);

/* Park / unpark — pause and resume without full stop: */
kthread_park(task);     /* signal thread to suspend */
kthread_parkme();       /* thread calls this to actually suspend */
kthread_unpark(task);   /* resume the thread */"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Because a kthread runs in process context, it can call any sleeping function — " +
                        "including mutex_lock(), kmalloc(GFP_KERNEL), and wait_event()."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "Workqueue API") {
                    CodeBlock(
                        """#include <linux/workqueue.h>

/* Static declaration: */
DECLARE_WORK(my_work, my_work_fn);

/* Dynamic init: */
struct work_struct my_work;
INIT_WORK(&my_work, my_work_fn);

schedule_work(&my_work);        /* queue on system_wq */
flush_work(&my_work);           /* wait for completion */
cancel_work_sync(&my_work);     /* cancel and wait */

/* Delayed work — fires after a timeout: */
DECLARE_DELAYED_WORK(my_dwork, my_work_fn);
schedule_delayed_work(&my_dwork, msecs_to_jiffies(200));
cancel_delayed_work_sync(&my_dwork);"""
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "Spinlocks") {
                    BodyText(
                        "Spinlocks are the most fundamental kernel synchronization primitive. They busy-wait " +
                        "(spin) until the lock is free — never sleeping. They can be used in any context, " +
                        "including interrupt handlers."
                    )
                    CodeBlock(
                        """#include <linux/spinlock.h>

DEFINE_SPINLOCK(my_lock);   /* static init */
/* or: spin_lock_init(&my_lock); for dynamic init */

spin_lock(&my_lock);
    /* critical section — preemption disabled */
spin_unlock(&my_lock);

/* When the lock is also accessed from interrupt handlers,
   save/restore IRQ state to prevent deadlock: */
unsigned long flags;
spin_lock_irqsave(&my_lock, flags);
    /* safe from both process and interrupt context */
spin_unlock_irqrestore(&my_lock, flags);"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Rule: never call any sleeping function while holding a spinlock — no mutex_lock(), " +
                        "no kmalloc(GFP_KERNEL), no copy_from_user()."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "Mutexes") {
                    BodyText(
                        "Kernel mutexes are sleeping locks. If the lock is not available, the calling thread " +
                        "sleeps (is put on a wait queue) rather than spinning. This makes them suitable for " +
                        "critical sections that may need to sleep or take a long time, but they can only be " +
                        "used in process context."
                    )
                    CodeBlock(
                        """#include <linux/mutex.h>

DEFINE_MUTEX(my_mutex);     /* static init */
/* or: mutex_init(&my_mutex); for dynamic init */

mutex_lock(&my_mutex);      /* blocks (sleeps) if not available */
    /* critical section */
mutex_unlock(&my_mutex);

/* Non-blocking trylock: returns 1=acquired, 0=failed */
if (mutex_trylock(&my_mutex)) {
    /* got it */
    mutex_unlock(&my_mutex);
}"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Process context only — never use in interrupt handlers or while holding a spinlock.")
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "Semaphores") {
                    BodyText(
                        "A counting semaphore tracks a count of available resources. down() decrements the " +
                        "count; if it reaches zero the caller sleeps until another thread calls up(). " +
                        "A binary semaphore (count=1) acts like a mutex."
                    )
                    CodeBlock(
                        """#include <linux/semaphore.h>

struct semaphore my_sem;
sema_init(&my_sem, 1);   /* count = 1 (binary semaphore) */

down(&my_sem);           /* sleep until count > 0, then decrement */
    /* critical section */
up(&my_sem);             /* increment count, wake a waiter */

/* Interruptible variant — returns -EINTR if a signal arrives: */
if (down_interruptible(&my_sem))
    return -ERESTARTSYS;"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Prefer down_interruptible() in driver code so that a signal (e.g., Ctrl+C) can " +
                        "unblock a waiting process rather than leaving it stuck."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "Wait Queues") {
                    BodyText(
                        "Wait queues are the kernel's equivalent of condition variables. A thread can sleep " +
                        "on a wait queue until some condition becomes true; another thread (or interrupt " +
                        "handler) wakes the sleeper after making the condition true."
                    )
                    CodeBlock(
                        """#include <linux/wait.h>

DECLARE_WAIT_QUEUE_HEAD(my_wq);
/* or: init_waitqueue_head(&my_wq); */

/* --- Waiter side --- */
/* Sleeps until condition is true (re-checks after each wake): */
wait_event(my_wq, condition);

/* Interruptible — returns -ERESTARTSYS if signal arrives: */
if (wait_event_interruptible(my_wq, condition))
    return -ERESTARTSYS;

/* --- Waker side (e.g., IRQ handler or producer) --- */
condition = true;            /* set condition BEFORE waking */
wake_up(&my_wq);             /* wake all uninterruptible waiters */
wake_up_interruptible(&my_wq); /* wake interruptible waiters */"""
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "Atomic Variables") {
                    BodyText(
                        "atomic_t (32-bit) and atomic64_t (64-bit) provide read-modify-write operations " +
                        "that are guaranteed atomic on all architectures — no locks needed for simple " +
                        "counters and flags."
                    )
                    CodeBlock(
                        """#include <linux/atomic.h>

atomic_t my_counter = ATOMIC_INIT(0);

atomic_set(&my_counter, 5);
int val = atomic_read(&my_counter);

atomic_inc(&my_counter);
atomic_dec(&my_counter);
atomic_add(3, &my_counter);
atomic_sub(2, &my_counter);

/* Returns true if result is zero (useful for ref-counting): */
if (atomic_dec_and_test(&my_counter))
    cleanup();

/* Compare-and-exchange — returns the old value: */
int old = atomic_cmpxchg(&my_counter, expected, new_val);"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Use atomic variables for lockless reference counters, statistics, and simple " +
                        "flags. They do not replace mutexes when you need to protect compound state " +
                        "that spans multiple variables."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "RCU — Read-Copy-Update") {
                    BodyText(
                        "RCU stands for Read-Copy-Update. It is a synchronization mechanism optimized for " +
                        "workloads with many concurrent readers and infrequent writers. Readers proceed " +
                        "without acquiring any lock — they never block or spin. Only writers need to " +
                        "coordinate."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "RCU handles the reader/writer relationship only. If there is more than one writer, " +
                        "the writers must synchronize with each other using a separate lock — a spinlock is " +
                        "the common choice."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Reader side:")
                    BodyText(
                        "A reader marks its reader critical section with rcu_read_lock() before accessing " +
                        "RCU-protected data and rcu_read_unlock() after. The reader must not sleep inside " +
                        "this section. Inside it, use rcu_dereference(ptr) to load the protected pointer — " +
                        "this includes a memory barrier that prevents the compiler and CPU from speculating " +
                        "past the load."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Writer side (the copy-update pattern):")
                    BodyText("1. Load the old pointer: rcu_dereference_protected(ptr, condition) — used by the writer because it already holds a lock; lighter than rcu_dereference, and tells lockdep the access is protected.")
                    BodyText("2. Allocate a new copy and apply the desired changes.")
                    BodyText("3. Publish: rcu_assign_pointer(ptr, new_val) — stores the new pointer with a write barrier so all preceding writes are visible to readers before the pointer swap is observed.")
                    BodyText("4. Wait for the Grace Period: all readers that still hold a reference to the old pointer must finish. Use synchronize_rcu() to block until this is guaranteed, then free the old data. Or use call_rcu() to schedule an asynchronous callback.")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText(
                        "Important: after step 3, new readers immediately see the new pointer — " +
                        "rcu_assign_pointer() publishes it atomically with a write barrier. " +
                        "synchronize_rcu() / call_rcu() is not about making the new value visible; " +
                        "it is only about waiting for readers that had already loaded the old pointer " +
                        "before the swap to exit their rcu_read_lock() section, so the old data " +
                        "can then be freed safely."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Grace Period — how it works:")
                    BodyText(
                        "Classic (non-preemptible) RCU: rcu_read_lock() disables preemption. A CPU " +
                        "that context-switches, goes idle, or returns to user space has necessarily left " +
                        "any reader critical section. The kernel tracks a quiescent state per CPU; once " +
                        "every CPU has observed at least one quiescent state, the Grace Period is over and " +
                        "it is safe to free the old data."
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText(
                        "PREEMPT_RCU (preemptible kernels): rcu_read_lock() does NOT disable preemption; " +
                        "it increments a per-CPU nesting counter instead. To end the Grace Period, the " +
                        "kernel must confirm every CPU's counter reached zero. Tasks that were preempted " +
                        "while inside a reader critical section are placed on a per-CPU list — " +
                        "synchronize_rcu() also waits for all of those tasks to exit before declaring the " +
                        "Grace Period complete."
                    )
                    CodeBlock(
                        """/* RCU-protected pointer (annotate with __rcu) */
struct my_data __rcu *my_ptr;

/* ---- Reader ---- */
rcu_read_lock();
struct my_data *d = rcu_dereference(my_ptr);
use(d->field);      /* must NOT sleep here */
rcu_read_unlock();

/* ---- Writer (sole writer — no extra lock needed) ---- */
struct my_data *old =
    rcu_dereference_protected(my_ptr, 1 /* sole writer */);
struct my_data *new = kmalloc(sizeof(*new), GFP_KERNEL);
*new = *old;                    /* copy */
new->field = new_value;         /* modify */
rcu_assign_pointer(my_ptr, new);/* publish with write barrier */
synchronize_rcu();              /* wait: Grace Period complete */
kfree(old);                     /* safe to free old data now */

/* ---- Multiple writers: guard with a spinlock ---- */
spin_lock(&my_writer_lock);
old = rcu_dereference_protected(my_ptr,
          lockdep_is_held(&my_writer_lock));
/* ... same copy-modify-assign steps ... */
spin_unlock(&my_writer_lock);
synchronize_rcu();
kfree(old);

/* ---- Async free with call_rcu ---- */
/* old_data->rcu_head is struct rcu_head embedded in the struct */
call_rcu(&old_data->rcu_head, my_free_fn); /* returns immediately */
/* my_free_fn is called after the Grace Period */"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("API summary:")
                    BodyText("rcu_read_lock() — enter reader critical section; disables preemption in classic RCU, increments per-CPU counter in PREEMPT_RCU")
                    BodyText("rcu_read_unlock() — exit reader critical section; symmetric with rcu_read_lock()")
                    BodyText("rcu_dereference(p) — safe pointer load for readers; memory barrier prevents compiler/CPU reordering")
                    BodyText("rcu_dereference_protected(p, cond) — safe pointer load for writers; cond is typically lockdep_is_held(&lock) or 1 for a sole writer")
                    BodyText("rcu_assign_pointer(p, v) — publish new pointer with a write barrier")
                    BodyText("synchronize_rcu() — block until the Grace Period completes; after this, the old pointer is safe to free")
                    BodyText("call_rcu(&head, fn) — non-blocking: fn(&head) is called after the Grace Period; use for deferred free in interrupt or performance-sensitive paths")
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "Preemption Control") {
                    BodyText(
                        "Disabling preemption protects per-CPU data structures and ensures a critical " +
                        "sequence is not interrupted by the scheduler. It does NOT protect from IRQ " +
                        "handlers — for that you must also disable interrupts."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "preempt_disable() increments an internal per-CPU counter (preempt_count). " +
                        "As long as preempt_count > 0, the scheduler will not preempt the current task. " +
                        "preempt_enable() decrements the counter; if it reaches 0 and TIF_NEED_RESCHED " +
                        "is set, it triggers an immediate reschedule."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Multiple preempt_disable() calls nest safely — the counter simply increments. " +
                        "Each call must be matched by exactly one preempt_enable(). The scheduler only " +
                        "regains control when the count drops back to 0."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Variants and helpers:")
                    BodyText(
                        "preempt_enable_no_resched() — decrements the count but skips the reschedule " +
                        "check. Useful in hot paths where you want to defer the reschedule to a later safe point."
                    )
                    BodyText(
                        "preempt_check_resched() — explicitly checks TIF_NEED_RESCHED and reschedules " +
                        "if needed. Typically called after preempt_enable_no_resched() at the deferred safe point."
                    )
                    BodyText(
                        "preemptible() — returns true if preempt_count == 0 (preemption currently enabled). " +
                        "Useful for assertions."
                    )
                    BodyText(
                        "preempt_count() — returns the raw preempt_count value. The upper bits also encode " +
                        "hardirq and softirq nesting depth, not just the preempt_disable nesting level."
                    )
                    BodyText(
                        "might_sleep() — a debug assertion: warns (or BUGs) if preemption is disabled, " +
                        "catching callers that invoke a potentially-sleeping function from atomic context."
                    )
                    CodeBlock(
                        """#include <linux/preempt.h>

/* Basic pattern — protect a per-CPU sequence */
preempt_disable();
    /* scheduler will not preempt here */
    my_percpu_var = compute_value();
preempt_enable();       /* reschedule if TIF_NEED_RESCHED is set */

/* Nested calls — count goes 0 → 1 → 2 → 1 → 0 */
preempt_disable();          /* count = 1 */
    preempt_disable();      /* count = 2 */
        /* critical work */
    preempt_enable();       /* count = 1 — no reschedule yet */
preempt_enable();           /* count = 0 — reschedule now if needed */

/* Hot-path pattern: defer the reschedule */
preempt_disable();
    fast_percpu_update();
preempt_enable_no_resched();    /* count = 0, but no reschedule yet */
/* ... other cheap work ... */
preempt_check_resched();        /* reschedule here if TIF_NEED_RESCHED */

/* Assertion helpers */
WARN_ON(!preemptible());    /* warn if preemption expected to be on */
might_sleep();              /* BUG/WARN if currently in atomic context */"""
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "Disabling Interrupts (Local IRQ Control)") {
                    BodyText(
                        "local_irq_disable() and local_irq_enable() disable and re-enable hardware " +
                        "interrupts on the current CPU only. Other CPUs are entirely unaffected. They " +
                        "do not acquire any lock — they only control whether this CPU responds to hardware " +
                        "interrupt signals."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Use when: data is shared between process context and an IRQ handler, but accessed " +
                        "on a single CPU only — so no spinlock is needed, but you must prevent the IRQ " +
                        "handler from running on this CPU while you access the data."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Problem with bare local_irq_disable/enable: if interrupts were already disabled " +
                        "when you call local_irq_disable(), a subsequent local_irq_enable() incorrectly " +
                        "re-enables them — corrupting the caller's expected state."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "local_irq_save(flags) / local_irq_restore(flags): saves the current CPU flags " +
                        "register (including the IF interrupt-enable bit) before disabling, then restores " +
                        "exactly the original state. Safe even if interrupts were already disabled. " +
                        "Always prefer save/restore over bare disable/enable."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "spin_lock_irqsave(&lock, flags) / spin_unlock_irqrestore(&lock, flags): combines " +
                        "spinlock acquisition with IRQ disable. Use this when a spinlock-protected data " +
                        "structure is also accessed from an IRQ handler. Without the irqsave variant, the " +
                        "IRQ handler could fire on the same CPU holding the lock and deadlock — it spins " +
                        "waiting for a lock that can never be released while it's running."
                    )
                    CodeBlock(
                        """#include <linux/irqflags.h>
#include <linux/spinlock.h>

/* ---- Pattern A: local_irq_save / restore ---- */
unsigned long flags;

local_irq_save(flags);      /* save EFLAGS, disable IRQs on this CPU */
    /* IRQ handler cannot fire on this CPU here */
    percpu_data->count++;
local_irq_restore(flags);   /* restore original EFLAGS exactly */

/* AVOID: bare disable/enable — breaks if caller had IRQs off */
/* local_irq_disable(); */
/* local_irq_enable();  */  /* may incorrectly re-enable IRQs */

/* ---- Pattern B: spin_lock_irqsave ---- */
/* Use when the same lock is taken inside an IRQ handler */
DEFINE_SPINLOCK(my_lock);

spin_lock_irqsave(&my_lock, flags);
    /* safe from both process and interrupt context on any CPU */
    shared_data->value = new_value;
spin_unlock_irqrestore(&my_lock, flags);

/* Why irqsave: without it, the IRQ handler could fire on this
   CPU, try to spin_lock(&my_lock), and deadlock — because this
   CPU holds the lock and cannot release it while the IRQ runs. */"""
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
