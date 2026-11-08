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
fun RcuGracePeriodScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "RCU Grace Period",
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
                SectionCard(title = "1. RCU in One Minute") {
                    BodyText("RCU (Read-Copy-Update) is a synchronization mechanism for READ-MOSTLY data. Readers take no lock, write no shared memory and never block, so they scale perfectly across CPUs. Writers pay the price instead.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The writer never modifies an object that readers may be looking at. It:")
                    BodyText("  1. COPIES the old object (or allocates a new one),\n  2. UPDATES the copy privately,\n  3. PUBLISHES it with one atomic pointer store (rcu_assign_pointer),\n  4. WAITS for a grace period,\n  5. FREES the old object.")
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """time ----------------------------------------------------------->

gp ---> [ old v1 ]          gp ---> [ new v2 ]
          ^                           ^
reader A: |--- sees v1 ---|           |
reader B:     |------ sees v1 ------| |
reader C:                    |--- sees v2 ---|
                 ^
          rcu_assign_pointer(gp, v2)
                 |<----- grace period ----->|
                                            kfree(v1)  <- safe now:
                                            A and B are done,
                                            C never saw v1"""
                    )
                    BodyText("Reader side API:")
                    CodeBlock(
                        """rcu_read_lock();                 /* start read-side critical section */
p = rcu_dereference(gp);         /* load pointer (+ dependency ordering) */
if (p)
    do_something(p->a, p->b);    /* p cannot be freed under us */
rcu_read_unlock();               /* end of critical section */"""
                    )
                    BodyText("Writer side (writers still serialize among themselves with a normal lock):")
                    CodeBlock(
                        """spin_lock(&my_lock);
old = rcu_dereference_protected(gp, lockdep_is_held(&my_lock));
new = kmalloc(sizeof(*new), GFP_KERNEL);
*new = *old;                     /* copy   */
new->a = 42;                     /* update */
rcu_assign_pointer(gp, new);     /* publish (has release barrier) */
spin_unlock(&my_lock);

/* ... now wait for a grace period, then free 'old' ... */"""
                    )
                }
            }
            item {
                SectionCard(title = "2. What a Grace Period Guarantees") {
                    BodyText("A GRACE PERIOD is a span of time that ends only after every CPU/task that was inside an RCU read-side critical section when the grace period STARTED has left it.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The guarantee in one sentence: once the grace period ends, no reader can still hold a reference to the OLD version, because every reader that could have seen it has finished, and every reader that started later can only see the NEW pointer.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("What it does NOT guarantee:")
                    BodyText("  - It does not stop new readers; they simply see the new version.\n  - It does not wait for readers that start after the grace period began (it doesn't need to).\n  - It does not order writers against each other; you still need a lock or atomic ops for that.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The waiting can be done in two ways: SYNCHRONOUSLY (block until the grace period ends) or ASYNCHRONOUSLY (register a callback that runs after it ends).")
                }
            }
            item {
                SectionCard(title = "3. Waiting Synchronously") {
                    BodyText("synchronize_rcu() blocks the caller until a full grace period has elapsed. Afterwards the old object can be freed directly.")
                    CodeBlock(
                        """void update_config(struct config *new)
{
    struct config *old;

    mutex_lock(&cfg_mutex);
    old = rcu_dereference_protected(cur_cfg,
                                    lockdep_is_held(&cfg_mutex));
    rcu_assign_pointer(cur_cfg, new);
    mutex_unlock(&cfg_mutex);

    synchronize_rcu();   /* SLEEPS until all pre-existing readers finish */
    kfree(old);          /* nobody can reference 'old' anymore */
}"""
                    )
                    BodyText("Rules and variants:")
                    BodyText("  - synchronize_rcu() SLEEPS: only from process context, never from IRQ/softirq, never under a spinlock, and never inside rcu_read_lock() (that would deadlock - you would wait for yourself).\n  - Latency is typically milliseconds (several scheduler ticks).\n  - synchronize_rcu_expedited(): much faster (microseconds) because it IPIs all CPUs to force quiescent states - but it disturbs every CPU; avoid it in hot paths and on real-time systems.\n  - synchronize_srcu(&my_srcu): the sleepable-RCU (SRCU) flavour, for readers that are allowed to sleep.\n  - Polled API: cookie = get_state_synchronize_rcu(); ... later poll_state_synchronize_rcu(cookie) returns true if a grace period has already elapsed, or cond_synchronize_rcu(cookie) waits only if needed.")
                }
            }
            item {
                SectionCard(title = "4. Waiting Asynchronously") {
                    BodyText("call_rcu() does not block. It queues a callback on the CURRENT CPU and returns immediately; the callback runs some time after a grace period ends. Embed a struct rcu_head in your object:")
                    CodeBlock(
                        """struct config {
    int a, b;
    struct rcu_head rcu;          /* storage for the callback */
};

static void config_free_rcu(struct rcu_head *head)
{
    struct config *c = container_of(head, struct config, rcu);
    kfree(c);
}

void update_config(struct config *new)
{
    struct config *old;

    spin_lock(&cfg_lock);                 /* OK - call_rcu never sleeps */
    old = rcu_dereference_protected(cur_cfg,
                                    lockdep_is_held(&cfg_lock));
    rcu_assign_pointer(cur_cfg, new);
    spin_unlock(&cfg_lock);

    call_rcu(&old->rcu, config_free_rcu); /* returns immediately */
}"""
                    )
                    BodyText("If the callback only frees memory, use the shortcut:")
                    CodeBlock(
                        """kfree_rcu(old, rcu);     /* 'rcu' = name of the rcu_head field     */
kvfree_rcu(old);         /* single-arg form: may sleep, no rcu_head */"""
                    )
                    BodyText("Important properties:")
                    BodyText("  - call_rcu() may be called from any context, including IRQ and under spinlocks.\n  - The callback runs in SOFTIRQ context (RCU_SOFTIRQ) or in a per-CPU rcuc/rcuo kthread - it must NOT sleep.\n  - Callbacks are batched: one grace period can retire thousands of callbacks, which is why call_rcu() is far cheaper than many synchronize_rcu() calls.\n  - Module unload: call rcu_barrier() in module_exit. It waits until all ALREADY-QUEUED callbacks have RUN - otherwise a callback could jump into the freed module text. (synchronize_rcu() is not enough: it waits for readers, not for callbacks.)")
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """                 synchronize_rcu()      call_rcu()/kfree_rcu()
Blocks caller?   yes (sleeps)           no
Context          process only           any (IRQ, spinlock ...)
Free happens     in caller, after wait  in callback (softirq/kthread)
Throughput       low (1 GP per call)    high (batched)
Unload issue     none                   need rcu_barrier()"""
                    )
                }
            }
            item {
                SectionCard(title = "5. Under the Hood: Quiescent States") {
                    BodyText("The key idea: the kernel does NOT track individual readers in the classic (non-preemptible) RCU. Instead it watches for QUIESCENT STATES - points at which a CPU is guaranteed NOT to be inside a read-side critical section.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("In a non-preemptible kernel, rcu_read_lock() is essentially preempt_disable(), so a reader can never be switched out. Therefore, if a CPU performs any of the following, all readers that were running on it must have finished:")
                    BodyText("  - a context switch (schedule()),\n  - running the idle loop,\n  - running in user mode,\n  - being in an 'extended quiescent state' (idle with the tick stopped, or nohz_full user mode).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("So: a grace period ends when EVERY CPU has passed through at least one quiescent state since the grace period started.")
                    CodeBlock(
                        """          GP starts                                  GP ends
              |                                          |
CPU0  ==R==|--ctx switch (QS)----------------------------|
CPU1  =====R=====|--user mode (QS)-----------------------|
CPU2  -----idle (QS)-------------------------------------|
CPU3  ===R=========R=========|--ctx switch (QS)---------->|
                                     last CPU reports ---^

R = reader in rcu_read_lock() section"""
                    )
                }
            }
            item {
                SectionCard(title = "6. Under the Hood: How the Kernel Knows the GP Is Over") {
                    BodyText("Modern Linux uses TREE RCU (kernel/rcu/tree.c). The bookkeeping is a hierarchy so that thousands of CPUs don't fight over one lock:")
                    CodeBlock(
                        """struct rcu_state                  (one, global)
   |  gp_seq   = current grace-period number
   |  gp_kthread = "rcu_preempt" / "rcu_sched" kthread
   v
struct rcu_node  ROOT              qsmask = which children still owe a QS
   /           \
rcu_node      rcu_node   (leaves)  qsmask = one bit per CPU still owing a QS
 /   |   \      /  \               blkd_tasks = preempted readers (PREEMPT_RCU)
rcu_data  rcu_data ...             (PER-CPU)
  gp_seq          - which GP this CPU has seen
  cpu_no_qs       - "I have not passed a QS yet in this GP"
  cblist          - this CPU's queued call_rcu() callbacks"""
                    )
                    BodyText("The life of one grace period:")
                    BodyText("  1. START: the RCU grace-period kthread (rcu_gp_kthread) increments rcu_state.gp_seq and sets, in every leaf rcu_node, a qsmask bit for each online CPU.\n  2. NOTICE: each CPU notices the new gp_seq (from its scheduler tick, rcu_sched_clock_irq(), or in RCU_SOFTIRQ) and sets its own rcu_data.cpu_no_qs = true.\n  3. QUIESCENT STATE: on the next context switch the scheduler calls rcu_note_context_switch() -> rcu_qs(), which clears cpu_no_qs. The tick also detects 'interrupted from user mode / idle' and counts that as a QS.\n  4. REPORT: the CPU (from RCU_SOFTIRQ) calls rcu_report_qs_rdp(), which clears its bit in the leaf's qsmask under the leaf lock. When a leaf's qsmask becomes 0, the leaf clears ITS bit in the parent - and so on up the tree.\n  5. END: when the ROOT's qsmask is 0 (and no preempted readers are blocking it), the GP kthread runs rcu_gp_cleanup(): it advances gp_seq, marking the grace period complete.\n  6. CALLBACKS: each CPU's segmented callback list (rcu_segcblist: DONE | WAIT | NEXT_READY | NEXT) is tagged with gp_seq numbers. Callbacks whose GP has completed move to DONE and are invoked by rcu_core() in RCU_SOFTIRQ (or by rcuo kthreads if the CPU is in rcu_nocbs=). synchronize_rcu() itself is just 'call_rcu(wakeme_after_rcu) + wait_for_completion()'.")
                }
            }
            item {
                SectionCard(title = "7. Idle CPUs, Slow CPUs and Stalls") {
                    BodyText("Idle / tickless CPUs: a CPU sleeping in idle with its tick stopped cannot report anything. So each CPU maintains a per-CPU counter (historically 'dynticks', now part of struct context_tracking, the 'RCU watching' state) that is incremented on every entry to and exit from an extended quiescent state (idle, nohz_full user mode).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The GP kthread periodically runs force-quiescent-state scanning (rcu_gp_fqs(), every few jiffies). For each CPU still owing a QS it snapshots that counter: if the CPU is currently in an extended QS, or the counter changed since the GP started, the CPU has passed a QS and the kthread reports it ON THE CPU'S BEHALF - the idle CPU is never woken up.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("CPUs that stay busy in the kernel without scheduling: RCU escalates - sets need_resched / rcu_urgent_qs so the next cond_resched() reports a QS, then resched_cpu(), and eventually sends IPIs.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("If a CPU still hasn't reported after CONFIG_RCU_CPU_STALL_TIMEOUT (21 seconds by default), you get the famous warning: 'rcu: INFO: rcu_preempt detected stalls on CPUs/tasks' with a backtrace of the offender - usually a loop with preemption or IRQs disabled.")
                }
            }
            item {
                SectionCard(title = "8. Are There Per-CPU Counters of Readers?") {
                    BodyText("It depends on the RCU flavour - this is the most common misconception about RCU:")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("A) Non-preemptible Tree RCU (CONFIG_PREEMPT_NONE / VOLUNTARY): NO reader counter at all. rcu_read_lock() compiles to preempt_disable() (or nothing on non-debug builds). Readers are invisible; the kernel infers 'no readers left' purely from quiescent states. That is why the read side is free.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("B) Preemptible RCU (CONFIG_PREEMPT_RCU, used by PREEMPT / PREEMPT_RT / most desktop kernels): readers CAN be preempted, so a context switch is no longer proof. The counter is PER-TASK, not per-CPU: current->rcu_read_lock_nesting is incremented by rcu_read_lock() and decremented by rcu_read_unlock(). If a task is preempted while nesting > 0, rcu_note_context_switch() queues the task on its leaf rcu_node's blkd_tasks list. The grace period cannot end until that list's entries for the current GP drain; the reader removes itself in rcu_read_unlock_special() when it exits the critical section. (Priority boosting, CONFIG_RCU_BOOST, can raise such blocked readers' priority.)")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("C) SRCU (Sleepable RCU): YES, real per-CPU counters. Each srcu_struct has per-CPU srcu_data with a pair of lock/unlock counters (srcu_lock_count[2] / srcu_unlock_count[2] in many kernel versions; renamed srcu_ctrs[] in recent ones). srcu_read_lock() increments the lock counter of the current index and returns the index; srcu_read_unlock(idx) increments the matching unlock counter. To end a grace period the updater flips the active index and waits until, summed over all CPUs, locks == unlocks for the OLD index.")
                    CodeBlock(
                        """idx = srcu_read_lock(&my_srcu);     /* this_cpu_inc(lock[idx])   */
p = srcu_dereference(gp, &my_srcu);
msleep(10);                          /* sleeping is allowed here! */
srcu_read_unlock(&my_srcu, idx);     /* this_cpu_inc(unlock[idx]) */

/* updater */
rcu_assign_pointer(gp, new);
synchronize_srcu(&my_srcu);          /* flip idx, wait sum(lock)==sum(unlock) */
kfree(old);"""
                    )
                    BodyText("Summary: classic RCU = no counters (quiescent states only); preemptible RCU = per-task nesting counter + blocked-task lists; SRCU = per-CPU lock/unlock counter pairs.")
                }
            }
            item {
                SectionCard(title = "9. Quick Reference") {
                    CodeBlock(
                        """READERS
rcu_read_lock() / rcu_read_unlock()
rcu_dereference(p)                    read an RCU-protected pointer
rcu_read_lock_bh() / _sched()         legacy flavours (now one GP)
srcu_read_lock(&s) / srcu_read_unlock(&s, idx)

UPDATERS - publish
rcu_assign_pointer(p, v)              publish with release ordering
rcu_replace_pointer(p, v, cond)       swap and return old
RCU_INIT_POINTER(p, NULL)             no barrier (NULL / unpublished)
rcu_dereference_protected(p, cond)    read while holding update lock

UPDATERS - wait synchronously
synchronize_rcu()                     sleeps ~ms
synchronize_rcu_expedited()           fast, IPIs all CPUs
synchronize_srcu(&s)
get_state_synchronize_rcu() / poll_state_synchronize_rcu(c)
cond_synchronize_rcu(c)

UPDATERS - wait asynchronously
call_rcu(&obj->rcu, cb)               cb runs after GP (softirq)
kfree_rcu(obj, rcu) / kvfree_rcu(obj)
call_srcu(&s, &obj->rcu, cb)
rcu_barrier() / srcu_barrier(&s)      wait for queued callbacks

LISTS
list_add_rcu / list_del_rcu / list_for_each_entry_rcu
hlist_add_head_rcu / hlist_for_each_entry_rcu

DEBUG
CONFIG_PROVE_RCU, rcu_read_lock_held(), lockdep splats
/sys/kernel/debug/rcu/*, rcutorture, stall warnings"""
                    )
                }
            }
        }
    }
}
