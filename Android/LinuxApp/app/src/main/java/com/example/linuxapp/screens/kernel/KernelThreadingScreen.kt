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

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
