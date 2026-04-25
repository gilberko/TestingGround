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
fun KernelProcessSchedulingScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Process Scheduling",
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
                SectionCard(title = "The Scheduler") {
                    BodyText(
                        "Every runnable task in Linux lives on a per-CPU runqueue. The scheduler's job is " +
                        "to pick the best task from that runqueue and give it the CPU."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Linux uses a class-based scheduler: each scheduling policy (CFS for normal tasks, " +
                        "SCHED_FIFO / SCHED_RR for real-time, SCHED_DEADLINE for periodic tasks) is " +
                        "implemented by a scheduling class. The classes are ordered by priority; the " +
                        "scheduler walks from highest to lowest and picks the first class that has a " +
                        "runnable task."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "The core scheduler code lives in kernel/sched/core.c. The central function is " +
                        "schedule(), which wraps the internal __schedule(). schedule() is NOT a syscall — " +
                        "it is a regular kernel function called from process context whenever the current " +
                        "task needs to yield the CPU."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "schedule() — What It Does") {
                    BodyText(
                        "When schedule() is called, it performs these steps:"
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("1. Disables preemption and acquires the runqueue lock.")
                    BodyText("2. Dequeues the current task from the runqueue if its state is not TASK_RUNNING (i.e., the task is going to sleep).")
                    BodyText("3. Calls pick_next_task() — walks the scheduling classes and selects the next task to run.")
                    BodyText("4. Calls context_switch() — saves the CPU registers and stack pointer of the outgoing task, loads those of the incoming task, and switches the memory map (mm_struct). Execution now resumes in the incoming task.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "After context_switch() returns (from the perspective of the newly scheduled task), " +
                        "the task continues from the point where it last called schedule() — typically " +
                        "inside a lock or wait function that will now discover the condition it was " +
                        "waiting for."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "schedule() must only be called in process context. It must never be called while " +
                        "holding a spinlock or in interrupt context — doing so will deadlock or corrupt " +
                        "the runqueue."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "What Triggers Scheduling") {
                    BodyText("There are two paths into schedule():")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Voluntary scheduling:")
                    BodyText(
                        "The task itself calls schedule() — directly or indirectly. Every sleeping " +
                        "primitive (mutex_lock, wait_event, msleep, etc.) sets the task state to a " +
                        "sleeping value and calls schedule() internally. The task suspends until woken."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Involuntary scheduling (preemption):")
                    BodyText(
                        "A hardware timer fires periodically. The interrupt handler calls " +
                        "scheduler_tick(), which updates the current task's vruntime and checks whether " +
                        "it has overrun its scheduling quantum. If so, it sets the TIF_NEED_RESCHED flag " +
                        "on the current task's thread_info."
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText(
                        "Crucially, the timer interrupt does NOT call schedule() directly — interrupts " +
                        "run in interrupt context where scheduling is forbidden. Instead, schedule() is " +
                        "invoked at the next safe point after the interrupt returns:"
                    )
                    BodyText("• On return from interrupt to user space: the kernel checks TIF_NEED_RESCHED and calls schedule() before restoring user-space registers.")
                    BodyText("• On kernel preemption points: if CONFIG_PREEMPT is enabled, preemption can happen at any point where preempt_count == 0 (e.g., when a spinlock is released).")
                    BodyText("• Explicit yield: a task can call yield() or cond_resched() to voluntarily give up the CPU if TIF_NEED_RESCHED is set.")
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "Task States and the Runqueue") {
                    BodyText("A task's state field (task_struct.state) controls whether it is on a runqueue:")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("TASK_RUNNING — the task is either actively running on a CPU or waiting on a runqueue to be scheduled next. All runnable tasks are in this state.")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("TASK_INTERRUPTIBLE — the task is sleeping; it will wake up when its wait condition is met OR when a signal is delivered.")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("TASK_UNINTERRUPTIBLE — the task is sleeping; it will only wake up when its wait condition is met. Signals are ignored. Used for waits that must not be interrupted (e.g., waiting for disk I/O or a mutex in the kernel).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "When a task sets its state to TASK_INTERRUPTIBLE or TASK_UNINTERRUPTIBLE and " +
                        "calls schedule(), the scheduler removes it from the runqueue. The task does not " +
                        "consume CPU time. It re-enters the runqueue only when another task or interrupt " +
                        "handler explicitly calls wake_up_process() on it, which sets its state back to " +
                        "TASK_RUNNING."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "Waiting on a Mutex — Step by Step") {
                    BodyText("What happens when Task A calls mutex_lock() on a mutex already held by Task B:")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("1. mutex_lock() checks the mutex owner field. It is set to Task B — the mutex is not free.")
                    BodyText("2. Task A allocates a waiter struct on its stack and adds it to the mutex's internal wait list.")
                    BodyText("3. Task A sets its own state to TASK_UNINTERRUPTIBLE.")
                    BodyText("4. Task A calls schedule() — the scheduler dequeues it from the runqueue. Task A is now suspended.")
                    BodyText("5. The scheduler calls pick_next_task() and switches to the next runnable task (possibly Task B or something else entirely).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("What happens when Task B calls mutex_unlock():")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("6. mutex_unlock() clears the owner field and checks the wait list — it finds Task A's waiter.")
                    BodyText("7. It calls wake_up_process(task_A), which sets Task A's state to TASK_RUNNING and adds it back to a runqueue.")
                    BodyText("8. Depending on Task A's priority, TIF_NEED_RESCHED may be set on the current CPU.")
                    BodyText("9. At the next scheduling point, schedule() is called. pick_next_task() selects Task A.")
                    BodyText("10. Task A resumes inside mutex_lock(), sets itself as the mutex owner, and returns. The caller proceeds past mutex_lock() as if nothing happened.")
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "Example: Mutex Contention") {
                    BodyText(
                        "The following illustrates the scheduler's involvement when two kernel threads " +
                        "contend on a mutex. Comments show what the scheduler does at each step."
                    )
                    CodeBlock(
                        """DEFINE_MUTEX(my_mutex);

/* === Task B — currently holds the mutex === */
mutex_lock(&my_mutex);   /* acquired; Task B is owner */
do_other_work();         /* runs normally */
mutex_unlock(&my_mutex);
/*
 * mutex_unlock():
 *   1. Clears owner field.
 *   2. Finds Task A on wait list.
 *   3. wake_up_process(task_A):
 *        task_A->state = TASK_RUNNING
 *        enqueue task_A onto runqueue
 *        set TIF_NEED_RESCHED if task_A has higher priority
 */

/* === Task A — tries to acquire while B holds it === */
mutex_lock(&my_mutex);
/*
 * mutex_lock() sees owner == Task B:
 *   1. Add self to my_mutex.wait_list.
 *   2. set_current_state(TASK_UNINTERRUPTIBLE)
 *   3. schedule()
 *        → dequeue Task A from runqueue
 *        → pick_next_task() selects another task (e.g., Task B)
 *        → context_switch() — Task A is now sleeping
 *
 * ... time passes, Task B calls mutex_unlock() above ...
 *
 * Eventually schedule() picks Task A again:
 *   → context_switch() back to Task A
 *   → Task A resumes inside mutex_lock()
 *   → sets owner = Task A, removes waiter from list
 *   → mutex_lock() returns — Task A holds the mutex
 */
do_work();               /* runs with the mutex held */
mutex_unlock(&my_mutex);"""
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
