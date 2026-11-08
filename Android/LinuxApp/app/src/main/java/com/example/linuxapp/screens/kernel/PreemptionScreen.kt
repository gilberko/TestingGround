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
fun PreemptionScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Preemption",
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
                SectionCard(title = "1. What Is Preemption?") {
                    BodyText("PREEMPTION = the kernel forcibly takes the CPU away from the running task (without the task asking) and gives it to another task. The opposite is a VOLUNTARY switch, where the task itself blocks (waits for I/O, a mutex, sleep()...).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Two kinds:")
                    BodyText("  - USER preemption: the task was running user-space code (or is returning to it). This has ALWAYS been possible in Linux, in every configuration.\n  - KERNEL preemption: the task was in the middle of KERNEL code (e.g. inside a system call) and is switched out right there. This is only possible if the kernel is built/booted with a preemptible model (CONFIG_PREEMPT, PREEMPT_RT, or preempt=full).")
                }
            }
            item {
                SectionCard(title = "2. How Does Preemption Occur?") {
                    BodyText("Preemption is a two-step process: first someone MARKS that a reschedule is needed, then at a safe point the kernel ACTS on that mark.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Step 1 - setting TIF_NEED_RESCHED (the 'please reschedule' flag of the running task):")
                    BodyText("  - Timer tick: the periodic scheduler tick (scheduler_tick(), renamed sched_tick() in 6.10) calls the class's task_tick(). For CFS/EEVDF, if the task used up its slice, resched_curr() sets the flag.\n  - Wakeup: try_to_wake_up() makes a task runnable; wakeup_preempt() (formerly check_preempt_curr()) checks if the woken task should run before the current one (higher RT priority, earlier EEVDF deadline) and sets the flag - possibly on another CPU via an IPI.\n  - Priority / affinity changes, sched_setscheduler(), migrations, etc.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Step 2 - checking the flag at preemption points:")
                    BodyText("  - Return to USER mode from a syscall, interrupt or exception (exit_to_user_mode_loop()): if the flag is set, call schedule(). Always active.\n  - Return from an INTERRUPT to KERNEL code: if preempt_count == 0 and flag set, call preempt_schedule_irq(). Only on preemptible kernels.\n  - preempt_enable(): when the count drops to 0 and the flag is set, call preempt_schedule(). Only on preemptible kernels.\n  - cond_resched() / might_resched() in long kernel loops: explicit voluntary preemption points (the main mechanism in PREEMPT_VOLUNTARY / NONE kernels).")
                    CodeBlock(
                        """timer IRQ ---> sched_tick() --> task_tick_fair()
                                     |  slice used up
                                     v
                              resched_curr(rq)  -> set TIF_NEED_RESCHED
                                     |
IRQ exit:  returning to user?  ---yes--> schedule()
           returning to kernel? --> preempt_count==0 && PREEMPT?
                                         |yes
                                         v
                                 preempt_schedule_irq() -> __schedule()"""
                    )
                }
            }
            item {
                SectionCard(title = "3. What Is schedule()? Is It a System Call?") {
                    BodyText("schedule() (kernel/sched/core.c) is the core kernel FUNCTION that picks the next task to run and switches to it. It is NOT a system call - user space cannot call it directly. It runs inside the kernel, on the CPU whose task is being switched out.")
                    CodeBlock(
                        """schedule()
  -> __schedule(SM_NONE)
       local_irq_disable(); rq_lock(rq);
       prev = rq->curr;
       if task is blocking: deactivate_task(prev)  /* remove from runqueue */
       next = pick_next_task(rq, prev)  /* stop > deadline > RT > fair > idle */
       clear_tsk_need_resched(prev);
       if (prev != next)
           context_switch(rq, prev, next)
              -> switch_mm_irqs_off()   /* new address space (CR3)   */
              -> switch_to(prev, next)  /* new kernel stack + regs   */"""
                    )
                    BodyText("Who calls it:")
                    BodyText("  - Blocking primitives (VOLUNTARY): mutex_lock(), wait_event(), msleep(), down(), read() waiting for data... they set the task state to TASK_INTERRUPTIBLE/UNINTERRUPTIBLE and call schedule().\n  - The return-to-user path when TIF_NEED_RESCHED is set.\n  - preempt_schedule() / preempt_schedule_irq() (INVOLUNTARY, in-kernel preemption).\n  - cond_resched().")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Related system calls: sched_yield() (do_sched_yield() -> schedule(): gives up the CPU voluntarily), nanosleep(), sched_setscheduler(), sched_setattr(), sched_setaffinity(). They call into the scheduler, but schedule() itself is an internal function.")
                }
            }
            item {
                SectionCard(title = "4. Preemption Models - Can I Have No Preemption?") {
                    BodyText("Yes. The amount of KERNEL preemption is a build / boot choice (user preemption always exists):")
                    CodeBlock(
                        """CONFIG_PREEMPT_NONE       "Server". Kernel code is never preempted;
                          switches only when it blocks or returns
                          to user. Best throughput, worst latency.
CONFIG_PREEMPT_VOLUNTARY  "Desktop". NONE + extra might_resched()
                          points sprinkled in long code paths.
CONFIG_PREEMPT            "Low-latency desktop". Kernel code may be
                          preempted anywhere preempt_count == 0.
CONFIG_PREEMPT_LAZY       (6.13+) like PREEMPT for RT tasks, but
                          normal tasks are preempted lazily (at the
                          next tick / return to user).
CONFIG_PREEMPT_RT         "Real-time". Mainline since 6.12. Almost
                          everything becomes preemptible (see below).
CONFIG_PREEMPT_DYNAMIC    Choose at boot: preempt=none | voluntary |
                          full | lazy  (most distros ship this)"""
                    )
                    BodyText("Check what you're running:")
                    CodeBlock(
                        """uname -v                       # e.g. "#1 SMP PREEMPT_DYNAMIC ..."
grep PREEMPT /boot/config-${'$'}(uname -r)
cat /sys/kernel/debug/sched/preempt
#   none voluntary (full) lazy     <- current model in parentheses
echo none > /sys/kernel/debug/sched/preempt   # switch at runtime (root)"""
                    )
                    BodyText("Note: with preempt=none you still get user preemption and interrupts. 'No preemption' only means kernel code runs until it blocks, calls cond_resched(), or returns to user mode.")
                }
            }
            item {
                SectionCard(title = "5. Preemption vs Real-Time") {
                    BodyText("They are related but not the same thing:")
                    BodyText("  - PREEMPTION is a MECHANISM: the ability to switch tasks at (almost) any point.\n  - REAL-TIME is a GOAL: a bounded, predictable worst-case latency from an event (e.g. an interrupt) to the high-priority task that handles it. 'Real-time' does not mean 'fast', it means 'deterministic'.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Even CONFIG_PREEMPT has long non-preemptible regions: spinlock holders, softirq processing, IRQ handlers. PREEMPT_RT shrinks them by:")
                    BodyText("  - Turning spinlock_t and rwlock_t into SLEEPING locks (based on rt_mutex) with PRIORITY INHERITANCE. Only raw_spinlock_t still really spins with preemption disabled.\n  - Forcing threaded interrupts: almost all IRQ handlers run in kernel threads (irq/NN-name) that can be prioritized and preempted.\n  - Running softirqs in task context.\n  - Making local_irq_disable() regions rare and short.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Combine with SCHED_FIFO / SCHED_RR / SCHED_DEADLINE policies, isolcpus / nohz_full, and locked memory (mlockall) for real-time applications.")
                }
            }
            item {
                SectionCard(title = "6. Can I Prevent Preemption?") {
                    BodyText("Inside the KERNEL - yes, explicitly:")
                    CodeBlock(
                        """preempt_disable();          /* preempt_count++ */
/* this task stays on THIS CPU and is not switched out
   (interrupts can still arrive!) */
v = this_cpu_read(my_counter);
this_cpu_write(my_counter, v + 1);
preempt_enable();           /* preempt_count--, if 0 && need_resched
                               -> preempt_schedule() right here */

cpu = get_cpu();            /* = preempt_disable() + smp_processor_id() */
use_per_cpu_data(cpu);
put_cpu();                  /* = preempt_enable() */

preempt_enable_no_resched();/* rare: don't check need_resched now */"""
                    )
                    BodyText("Other things that also disable preemption implicitly: spin_lock*(), local_irq_disable() (no interrupt -> no tick -> no preemption), local_bh_disable(), rcu_read_lock() on non-preemptible RCU, running in hardirq/softirq/NMI context.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Rules: keep these regions SHORT, and never sleep inside (no mutex_lock, no GFP_KERNEL allocation, no copy_from_user that may fault). Violations print 'BUG: scheduling while atomic' or 'BUG: sleeping function called from invalid context' (with CONFIG_DEBUG_ATOMIC_SLEEP).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("From USER space - no. A process cannot disable preemption. It can only make it unlikely: SCHED_FIFO at high priority (only higher-priority RT tasks and interrupts can preempt it), pinning to an isolated CPU (isolcpus=, nohz_full=, IRQ affinity away from that CPU).")
                }
            }
            item {
                SectionCard(title = "7. Spinlocks: Isn't Preemption While Holding One Dangerous?") {
                    BodyText("Yes - that is exactly why spin_lock() DISABLES preemption before taking the lock:")
                    CodeBlock(
                        """static inline void spin_lock(spinlock_t *lock)
{
    preempt_disable();          /* 1. no preemption on this CPU */
    spin_acquire(...);          /* lockdep annotation           */
    arch_spin_lock(&lock->raw); /* 2. spin until we own it      */
}
spin_unlock():  arch_spin_unlock(); preempt_enable();"""
                    )
                    BodyText("What would go wrong if the holder were preempted:")
                    BodyText("  - Wasted CPU: the holder is switched out; every other task wanting the lock spins uselessly for a whole time slice or more.\n  - Deadlock on the same CPU: the preempting task tries to take the same lock and spins forever - the holder can never run again on this CPU to release it (classic on uniprocessor).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Interrupts are a separate danger: preempt_disable() does NOT block interrupts. If an IRQ handler on the same CPU takes the same lock, the CPU deadlocks against itself. Hence the variants:")
                    CodeBlock(
                        """spin_lock(&l)                  preemption off
spin_lock_bh(&l)               + softirqs off on this CPU
spin_lock_irq(&l)              + hard IRQs off (assumes they were on)
spin_lock_irqsave(&l, flags)   + hard IRQs off, previous state saved
raw_spin_lock(&l)              always a real spinning lock (even RT)"""
                    )
                    BodyText("On PREEMPT_RT, spin_lock() becomes a sleeping rt_mutex and does NOT disable preemption - the problems above are solved by priority inheritance instead (and code that truly must spin uses raw_spinlock_t). On a uniprocessor non-preemptible kernel spinlocks compile to almost nothing, because nobody else could hold them.")
                }
            }
            item {
                SectionCard(title = "8. The Per-CPU preempt_count") {
                    BodyText("Yes - there is a counter that says 'do not preempt now': preempt_count. On x86 it is a per-CPU variable (pcpu_hot.preempt_count); on most other architectures it lives in the task's thread_info. It is not one number but several packed counters:")
                    CodeBlock(
                        """ bit:  31        23..20    19..16      15..8       7..0
      +--------+---------+----------+-----------+-----------+
      |NEED_   |   NMI   | HARDIRQ  |  SOFTIRQ  |  PREEMPT  |
      |RESCHED |  count  |  count   |   count   |   count   |
      +--------+---------+----------+-----------+-----------+
       (x86:     nmi_enter  irq_enter  local_bh_   preempt_disable
       folded,              /irq_exit  disable +2  spin_lock ...
       inverted)                       serving +1"""
                    )
                    BodyText("Who modifies which field:")
                    BodyText("  - PREEMPT (bits 0-7): preempt_disable()/enable(), spin_lock()/unlock(), get_cpu(), kmap_atomic(), rcu_read_lock() (non-preemptible RCU).\n  - SOFTIRQ (bits 8-15): local_bh_disable() adds 2*SOFTIRQ_OFFSET; while actually SERVING softirqs the kernel adds SOFTIRQ_OFFSET. That's how in_softirq() and in_serving_softirq() differ.\n  - HARDIRQ (bits 16-19): incremented by irq_enter() on interrupt entry, decremented by irq_exit().\n  - NMI (bits 20-23): nmi_enter()/nmi_exit().\n  - NEED_RESCHED (bit 31, x86 only): TIF_NEED_RESCHED folded in INVERTED, so 'preempt_count == 0' means both 'preemptible' and 'reschedule wanted' in one compare - preempt_enable() is a single decrement-and-test.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Helper macros (include/linux/preempt.h):")
                    CodeBlock(
                        """in_nmi()             NMI field != 0
in_hardirq()         HARDIRQ field != 0       (old name: in_irq())
in_serving_softirq() currently running a softirq
in_softirq()         serving softirq OR bh disabled
in_interrupt()       NMI | HARDIRQ | SOFTIRQ fields != 0
in_task()            none of the above (process context)
in_atomic()          preempt_count() != 0
preemptible()        preempt_count()==0 && !irqs_disabled()"""
                    )
                    BodyText("What does NOT touch preempt_count:")
                    BodyText("  - atomic_t / atomic64_t operations (atomic_inc, atomic_cmpxchg...) and this_cpu_* ops: they are single atomic instructions - there is nothing to preempt in the middle.\n  - local_irq_disable() / local_irq_save(): they only clear the CPU's interrupt flag. preemptible() checks irqs_disabled() separately.\n  - mutex_lock(): sleeping locks allow preemption while held.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Caveat: on kernels without CONFIG_PREEMPT_COUNT (PREEMPT_NONE without debug options), preempt_disable() is just a compiler barrier and the PREEMPT field is not maintained, so in_atomic() cannot detect a held spinlock. That's why the 'might sleep' checks need CONFIG_DEBUG_ATOMIC_SLEEP (which selects PREEMPT_COUNT).")
                }
            }
            item {
                SectionCard(title = "9. Quick Reference") {
                    CodeBlock(
                        """MARK        set_tsk_need_resched(), resched_curr(rq)
CHECK       need_resched(), tif_need_resched()
SWITCH      schedule(), __schedule(), context_switch()
INVOLUNTARY preempt_schedule(), preempt_schedule_irq()
VOLUNTARY   cond_resched(), might_resched(), sched_yield()
DISABLE     preempt_disable()/preempt_enable(), get_cpu()/put_cpu()
            migrate_disable() (stay on CPU, still preemptible)
QUERY       preempt_count(), in_atomic(), preemptible(),
            in_interrupt(), in_task()
DEBUG       CONFIG_DEBUG_ATOMIC_SLEEP, CONFIG_DEBUG_PREEMPT,
            ftrace 'preemptoff' / 'irqsoff' / 'wakeup_rt' tracers,
            cyclictest (rt-tests) for latency measurement"""
                    )
                }
            }
        }
    }
}
