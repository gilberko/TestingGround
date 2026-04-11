package com.example.linuxapp.screens.permissions

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.example.linuxapp.screens.kernel.BodyText
import com.example.linuxapp.screens.kernel.CodeBlock
import com.example.linuxapp.screens.kernel.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProcessSchedulingScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Process Scheduling", color = Color(0xFF00FF41), fontFamily = FontFamily.Monospace) },
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
                .fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = innerPadding.calculateTopPadding() + 8.dp,
                bottom = 24.dp
            )
        ) {
            item {
                SectionCard(title = "Scheduling Classes Overview") {
                    BodyText("Linux uses a modular, class-based scheduler. Each scheduling class implements a set of function pointers (the sched_class interface) covering: task selection, enqueue/dequeue, preemption checks, and more.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("On every scheduling event (timer tick, wakeup, yield), the kernel walks the class list from highest to lowest priority and picks the first class that has a runnable task.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("A task belongs to exactly one scheduling class at a time. Moving a task between classes requires a privileged syscall (sched_setscheduler / sched_setattr).")
                }
            }
            item {
                SectionCard(title = "Class Priority Order") {
                    BodyText("From highest to lowest priority:")
                    Spacer(Modifier.height(6.dp))
                    BodyText("1. STOP — not a user-visible class; used internally for CPU migration and watchdog threads. Always runs first.")
                    BodyText("2. DEADLINE (SCHED_DEADLINE) — earliest-deadline-first; preempts everything below it when its deadline is imminent.")
                    BodyText("3. RT (SCHED_FIFO / SCHED_RR) — real-time tasks; always preempt CFS tasks.")
                    BodyText("4. FAIR (SCHED_NORMAL / SCHED_BATCH) — the Completely Fair Scheduler; default for almost all user processes.")
                    BodyText("5. IDLE (SCHED_IDLE) — runs only when no other class has a runnable task.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Tasks compete only within their class. An RT task at priority 1 still beats any CFS task, regardless of nice value.")
                }
            }
            item {
                SectionCard(title = "SCHED_NORMAL — CFS") {
                    BodyText("SCHED_NORMAL is the default policy for all user processes. It is implemented by the Completely Fair Scheduler (CFS).")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Key concepts:")
                    BodyText("• vruntime — each task accumulates virtual runtime proportional to actual CPU time, weighted by its nice-based priority. Lower vruntime = has run less = should run next.")
                    BodyText("• Red-black tree — runnable tasks are ordered by vruntime in an rb-tree. The scheduler always picks the leftmost (smallest vruntime) node.")
                    BodyText("• Fairness — over any time window, each task gets CPU time proportional to its weight. A task at nice −5 gets roughly 3× more CPU than a task at nice +5.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("SCHED_BATCH is a variant of SCHED_NORMAL that assumes non-interactive batch work and slightly reduces scheduling frequency.")
                }
            }
            item {
                SectionCard(title = "Real-Time Classes") {
                    BodyText("Linux provides two real-time scheduling policies, both using static priorities 1–99 (99 = highest).")
                    Spacer(Modifier.height(8.dp))
                    BodyText("SCHED_FIFO:")
                    BodyText("• A FIFO task runs until it voluntarily sleeps, blocks, or is preempted by a higher-priority RT task.")
                    BodyText("• No time slicing within the same priority — the first task to become runnable at a given priority runs until done.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("SCHED_RR (Round Robin):")
                    BodyText("• Same as SCHED_FIFO but with a time quantum. When the quantum expires, the task goes to the back of its priority queue.")
                    BodyText("• Tasks at the same RR priority share the CPU in round-robin fashion.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("RT throttling:")
                    BodyText("• To prevent RT tasks from starving CFS tasks, the kernel limits RT tasks to rt_runtime_us out of every rt_period_us (default: 950 ms / 1 s = 95%).")
                    CodeBlock(
                        """
# View/change RT bandwidth limits
cat /proc/sys/kernel/sched_rt_period_us   # 1000000 (1 s)
cat /proc/sys/kernel/sched_rt_runtime_us  # 950000 (0.95 s)
                        """.trimIndent()
                    )
                }
            }
            item {
                SectionCard(title = "SCHED_DEADLINE") {
                    BodyText("SCHED_DEADLINE implements the Earliest Deadline First (EDF) algorithm. It is designed for periodic real-time tasks with strict timing guarantees.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Three parameters must be specified per task:")
                    BodyText("• runtime — how much CPU time the task needs each period (budget)")
                    BodyText("• period — the length of each repetition cycle")
                    BodyText("• deadline — when within the period the budget must be consumed")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Setting SCHED_DEADLINE via sched_setattr():")
                    CodeBlock(
                        """
#include <linux/sched/types.h>
#include <sys/syscall.h>

struct sched_attr attr = {
    .size       = sizeof(attr),
    .sched_policy   = SCHED_DEADLINE,
    .sched_runtime  =  5000000,  //  5 ms
    .sched_deadline = 10000000,  // 10 ms
    .sched_period   = 10000000,  // 10 ms
};
syscall(SYS_sched_setattr, 0, &attr, 0);
                        """.trimIndent()
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("SCHED_DEADLINE tasks preempt RT tasks when their deadline is imminent. The kernel performs admission control — if the total deadline bandwidth would exceed 100%, it rejects the request.")
                }
            }
            item {
                SectionCard(title = "SCHED_IDLE") {
                    BodyText("SCHED_IDLE is the lowest-priority scheduling class. A task with this policy only runs when every other CPU runqueue is empty.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("It is conceptually similar to nice +19 within CFS, but is an entirely separate class — it will never preempt any CFS task, even one at nice +19.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Typical uses: truly background maintenance work (index rebuilding, disk defrag helpers, telemetry) where the task should never affect interactive or normal workloads.")
                    CodeBlock(
                        """
# Launch a process with SCHED_IDLE policy
chrt --idle 0 ./background_task
                        """.trimIndent()
                    )
                }
            }
            item {
                SectionCard(title = "Nice Values") {
                    BodyText("Nice values tune the CFS weight of SCHED_NORMAL (and SCHED_BATCH) tasks. They have no effect on RT, DEADLINE, or IDLE classes.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Range: −20 (most favorable, highest weight) to +19 (least favorable, lowest weight). Default is 0.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("How it works internally:")
                    BodyText("• Each nice level maps to a weight in a fixed table (nice 0 → weight 1024).")
                    BodyText("• Each step of 1 nice level changes the weight by roughly 10%.")
                    BodyText("• A task at nice −5 gets approximately 3× more CPU than a task at nice +5.")
                    BodyText("• CFS uses these weights to scale vruntime accumulation — a higher-weight task's vruntime grows slower, so it gets picked more often.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Important: nice values do NOT change the scheduling class. A task at nice −20 is still a CFS task and will be preempted by any RT task.")
                }
            }
            item {
                SectionCard(title = "Changing Priority (User Mode)") {
                    BodyText("There are several ways to set or change a task's nice value or scheduling class from user space.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("nice — launch a process with a non-default nice value:")
                    CodeBlock(
                        """
nice -n 10 ./my_program   # launch at nice +10
nice -n -5 ./my_program   # launch at nice -5 (requires root)
                        """.trimIndent()
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("renice — adjust the nice value of a running process:")
                    CodeBlock(
                        """
renice -n 15 -p 1234      # set PID 1234 to nice +15
renice -n -10 -p 1234     # set nice -10 (requires root)
renice -n 5 -u myuser     # adjust all processes of a user
                        """.trimIndent()
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("setpriority() syscall — change nice value from within a program:")
                    CodeBlock(
                        """
#include <sys/resource.h>

// Set nice value of the calling process to +5
setpriority(PRIO_PROCESS, 0, 5);
                        """.trimIndent()
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("chrt — change the scheduling class of a running or new process (requires CAP_SYS_NICE for RT/DL):")
                    CodeBlock(
                        """
# Set PID 1234 to SCHED_FIFO priority 50
chrt -f -p 50 1234

# Set PID 1234 to SCHED_RR priority 10
chrt -r -p 10 1234

# Launch a program as SCHED_NORMAL (remove RT)
chrt -o -p 0 1234

# Query scheduling class of PID 1234
chrt -p 1234
                        """.trimIndent()
                    )
                }
            }
            item {
                SectionCard(title = "Kernel Thread Scheduling") {
                    BodyText("Kernel threads (kthreads) are created with kthread_create() or kthread_run() and default to SCHED_NORMAL with nice 0, just like ordinary user processes.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Elevating a kthread to real-time priority:")
                    CodeBlock(
                        """
#include <linux/sched.h>

struct sched_param param = { .sched_priority = 50 };
sched_setscheduler(task, SCHED_FIFO, &param);
                        """.trimIndent()
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("Common patterns in the kernel:")
                    BodyText("• kswapd — SCHED_NORMAL; elevated transiently under memory pressure")
                    BodyText("• migration/N — SCHED_FIFO priority 99; moves tasks between CPU runqueues")
                    BodyText("• watchdog/N — SCHED_FIFO priority 99; detects hung CPUs")
                    BodyText("• workqueue bound workers — inherit the scheduling class of their creator by default; can be configured per-workqueue")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Interrupt handlers (hardirq / softirq) are not scheduled tasks — they run in interrupt context, preempting whatever was running, and have no scheduling class or nice value.")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
