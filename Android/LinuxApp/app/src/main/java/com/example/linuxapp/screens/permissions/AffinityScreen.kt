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
fun AffinityScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("CPU Affinity", color = Color(0xFF00FF41), fontFamily = FontFamily.Monospace) },
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
                SectionCard(title = "What Is CPU Affinity") {
                    BodyText("CPU affinity defines which CPU(s) a thread or process is allowed to run on. By default the kernel scheduler is free to migrate tasks between CPUs at any time to balance load.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("When affinity is set, the scheduler will only schedule the task on the allowed CPU set. The task is said to be \"pinned\" to those CPUs.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Affinity is represented as a bitmask — one bit per logical CPU. A task with mask 0b0011 may run on CPU 0 or CPU 1 only.")
                }
            }
            item {
                SectionCard(title = "Benefits") {
                    BodyText("Pinning a task to specific CPUs can provide several advantages:")
                    Spacer(Modifier.height(6.dp))
                    BodyText("• Predictable latency — the task always wakes on the same core, no migration delay")
                    BodyText("• Avoids migration cost — migrating a task flushes its CPU pipeline state and TLB entries")
                    BodyText("• NUMA locality — on multi-socket systems, pinning keeps a task close to its memory node, reducing memory access latency")
                    BodyText("• Cache warmth — the task's working set stays hot in L1/L2, improving throughput for cache-sensitive workloads (network packet processing, real-time audio, etc.)")
                }
            }
            item {
                SectionCard(title = "Cache Effects") {
                    BodyText("Cache behavior is one of the main reasons to use affinity — but it cuts both ways.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("When pinned to a lightly-loaded core:")
                    BodyText("• L1/L2 cache lines remain warm between task wakeups")
                    BodyText("• L3 (shared) cache stays populated with the task's data")
                    BodyText("• Instruction cache (icache) stays hot — branch predictor history is preserved")
                    Spacer(Modifier.height(6.dp))
                    BodyText("When pinned to a busy or wrong core:")
                    BodyText("• Cache thrashing — other tasks evict your lines while your task is sleeping")
                    BodyText("• NUMA penalty — if pinned to a CPU on socket 0 but memory was allocated on socket 1, every access crosses the interconnect (~2× latency)")
                    BodyText("• Thermal throttling — a hot core reduces its clock frequency; a nearby idle core might be faster")
                }
            }
            item {
                SectionCard(title = "When Affinity Is NOT Good") {
                    BodyText("Affinity is not always beneficial and can hurt performance or reliability:")
                    Spacer(Modifier.height(6.dp))
                    BodyText("• Over-constraining on small systems — pinning all threads to 1–2 cores prevents the scheduler from spreading load across the machine")
                    BodyText("• Blocks load balancing — the kernel's load balancer cannot move a pinned task to a less loaded CPU, leading to imbalanced queues")
                    BodyText("• Thermal throttling risk — a single hot core throttles; the task could run faster on a cooler sibling")
                    BodyText("• Not helpful for CPU-unbound tasks — tasks that spend most time in I/O or sleep see no benefit from pinning")
                    BodyText("• Can cause starvation — if all high-priority tasks are pinned to the same CPU, lower-priority tasks on that CPU may starve")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Rule of thumb: measure first. Use affinity only when profiling shows migration or cache misses are a real bottleneck.")
                }
            }
            item {
                SectionCard(title = "User Mode: Setting Affinity") {
                    BodyText("Three main ways to set affinity from user space:")
                    Spacer(Modifier.height(8.dp))
                    BodyText("1. sched_setaffinity() syscall — set affinity for any process (requires CAP_SYS_NICE for other processes):")
                    CodeBlock(
                        """
#include <sched.h>

cpu_set_t mask;
CPU_ZERO(&mask);
CPU_SET(0, &mask);  // allow CPU 0
CPU_SET(1, &mask);  // allow CPU 1

// pid=0 means the calling thread
sched_setaffinity(0, sizeof(mask), &mask);
                        """.trimIndent()
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("2. taskset command — launch or adjust a running process:")
                    CodeBlock(
                        """
# Launch ./prog pinned to CPUs 0 and 1
taskset -c 0,1 ./prog

# Change affinity of running process (PID 1234)
taskset -cp 2,3 1234

# Hex mask form: 0x3 = CPUs 0 and 1
taskset 0x3 ./prog
                        """.trimIndent()
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("3. pthread_setaffinity_np() — set affinity per-thread in a multi-threaded program:")
                    CodeBlock(
                        """
#include <pthread.h>
#include <sched.h>

cpu_set_t cpuset;
CPU_ZERO(&cpuset);
CPU_SET(2, &cpuset);  // pin this thread to CPU 2

pthread_t tid = pthread_self();
pthread_setaffinity_np(tid, sizeof(cpuset), &cpuset);
                        """.trimIndent()
                    )
                }
            }
            item {
                SectionCard(title = "Kernel Threads: Setting Affinity") {
                    BodyText("Kernel threads (kthreads) have their own affinity API. The approach depends on whether the thread has started yet.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("kthread_bind() — pin a kthread to a CPU before it is woken for the first time:")
                    CodeBlock(
                        """
#include <linux/kthread.h>

struct task_struct *t;
t = kthread_create(my_fn, NULL, "my_kthread");
if (!IS_ERR(t)) {
    kthread_bind(t, 2);   // bind to CPU 2
    wake_up_process(t);
}
                        """.trimIndent()
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("set_cpus_allowed_ptr() — change the allowed CPU mask for an already-running kthread:")
                    CodeBlock(
                        """
#include <linux/cpumask.h>
#include <linux/sched.h>

cpumask_var_t mask;
alloc_cpumask_var(&mask, GFP_KERNEL);
cpumask_set_cpu(0, mask);
cpumask_set_cpu(1, mask);

set_cpus_allowed_ptr(task, mask);
free_cpumask_var(mask);
                        """.trimIndent()
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("Common use: workqueue \"bound\" workers are pinned per-CPU by the workqueue subsystem itself, ensuring a worker always handles the queue of its assigned CPU.")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
