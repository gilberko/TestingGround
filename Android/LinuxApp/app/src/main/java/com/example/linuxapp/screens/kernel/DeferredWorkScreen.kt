package com.example.linuxapp.screens.kernel

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeferredWorkScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Deferred Work",
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
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = innerPadding.calculateTopPadding() + 8.dp,
                bottom = 8.dp
            )
        ) {

            item {
                SectionCard(title = "Why Defer Work?") {
                    BodyText(
                        "Interrupt handlers run in interrupt context and must finish in microseconds. They cannot sleep, " +
                        "cannot acquire regular mutexes, and block the same IRQ line on all CPUs while executing."
                    )
                    BodyText(
                        "Often an interrupt just signals that something happened, but the actual processing takes more " +
                        "time or needs to block. Deferred mechanisms let you finish the slow part later, in a more " +
                        "permissive context, after the interrupt has been acknowledged."
                    )
                }
            }

            item {
                SectionCard(title = "Softirqs") {
                    BodyText(
                        "The lowest-level deferred mechanism. Softirqs are statically defined at compile time — you " +
                        "cannot add new ones without patching the kernel. The fixed set includes: HI, TIMER, NET_TX, " +
                        "NET_RX, BLOCK, IRQ_POLL, TASKLET, SCHED, HRTIMER, RCU."
                    )
                    BodyText(
                        "Context: softirq context (a form of interrupt context).\n" +
                        "  • Cannot sleep\n" +
                        "  • No user-space access\n" +
                        "  • May run concurrently on multiple CPUs — must use spinlocks for shared data\n" +
                        "  • Processed when an interrupt handler returns, or by the ksoftirqd kernel thread"
                    )
                    CodeBlock(
                        "/* Rarely used directly in drivers — shown for completeness */\n" +
                        "open_softirq(MY_SOFTIRQ, my_softirq_handler); /* register */\n" +
                        "raise_softirq(MY_SOFTIRQ);                    /* schedule */"
                    )
                }
            }

            item {
                SectionCard(title = "Tasklets") {
                    BodyText(
                        "Tasklets are built on top of softirqs (TASKLET_SOFTIRQ / HI_SOFTIRQ) but can be defined " +
                        "dynamically by any driver. They are the traditional choice for simple deferred work in older drivers."
                    )
                    BodyText(
                        "Context: softirq context.\n" +
                        "  • Cannot sleep\n" +
                        "  • Atomic context — no mutexes, no blocking\n" +
                        "  • Serialized: only one instance of a given tasklet runs at a time\n" +
                        "  • Always runs on the CPU that scheduled it\n" +
                        "  • Note: tasklets are deprecated since Linux 5.17; prefer workqueues in new code"
                    )
                    CodeBlock(
                        "#include <linux/interrupt.h>\n" +
                        "\n" +
                        "void my_tasklet_fn(struct tasklet_struct *t) {\n" +
                        "    /* do work — no sleeping */\n" +
                        "}\n" +
                        "\n" +
                        "struct tasklet_struct my_tasklet;\n" +
                        "\n" +
                        "/* Static init: */\n" +
                        "DECLARE_TASKLET(my_tasklet, my_tasklet_fn);\n" +
                        "\n" +
                        "/* Dynamic init: */\n" +
                        "tasklet_setup(&my_tasklet, my_tasklet_fn);\n" +
                        "\n" +
                        "/* Schedule from IRQ handler: */\n" +
                        "tasklet_schedule(&my_tasklet);\n" +
                        "\n" +
                        "/* Cleanup (waits for running instance): */\n" +
                        "tasklet_kill(&my_tasklet);"
                    )
                }
            }

            item {
                SectionCard(title = "Workqueues") {
                    BodyText(
                        "The modern, recommended mechanism for deferred work. Work items run inside kernel threads " +
                        "(worker threads), which means process context — the full power of the kernel is available."
                    )
                    BodyText(
                        "Context: process context (kernel thread).\n" +
                        "  • CAN sleep — msleep(), wait_event(), mutex_lock() all valid\n" +
                        "  • Not in interrupt context — no atomic constraints\n" +
                        "  • Use the shared system_wq for most cases; create a custom queue only when you need " +
                        "    ordering guarantees or a dedicated thread"
                    )
                    CodeBlock(
                        "#include <linux/workqueue.h>\n" +
                        "\n" +
                        "struct work_struct my_work;\n" +
                        "\n" +
                        "void my_work_fn(struct work_struct *w) {\n" +
                        "    /* can sleep here */\n" +
                        "}\n" +
                        "\n" +
                        "INIT_WORK(&my_work, my_work_fn);\n" +
                        "schedule_work(&my_work);       /* queue on system_wq */\n" +
                        "flush_work(&my_work);          /* wait for completion */\n" +
                        "cancel_work_sync(&my_work);    /* cancel + wait */"
                    )
                    BodyText("Delayed workqueue — run after a timer expires:")
                    CodeBlock(
                        "struct delayed_work my_dwork;\n" +
                        "INIT_DELAYED_WORK(&my_dwork, my_work_fn);\n" +
                        "schedule_delayed_work(&my_dwork, msecs_to_jiffies(100));\n" +
                        "cancel_delayed_work_sync(&my_dwork);"
                    )
                    BodyText("Custom workqueue (for dedicated threads or strict ordering):")
                    CodeBlock(
                        "struct workqueue_struct *wq =\n" +
                        "    alloc_workqueue(\"my_wq\", WQ_UNBOUND, 0);\n" +
                        "queue_work(wq, &my_work);\n" +
                        "/* ... */\n" +
                        "destroy_workqueue(wq);"
                    )
                }
            }

            item {
                SectionCard(title = "Kernel Threads") {
                    BodyText(
                        "Full kernel threads for continuous background work — polling a device, draining a queue, " +
                        "or any task that runs indefinitely rather than being scheduled once."
                    )
                    BodyText(
                        "Context: process context.\n" +
                        "  • CAN sleep — full blocking I/O, waits, and all synchronization primitives available\n" +
                        "  • kthread_should_stop() must be checked periodically to allow clean shutdown"
                    )
                    CodeBlock(
                        "#include <linux/kthread.h>\n" +
                        "\n" +
                        "static int my_thread_fn(void *data) {\n" +
                        "    while (!kthread_should_stop()) {\n" +
                        "        /* do work */\n" +
                        "        msleep(100);\n" +
                        "    }\n" +
                        "    return 0;\n" +
                        "}\n" +
                        "\n" +
                        "/* Start (returns task_struct* or ERR_PTR): */\n" +
                        "struct task_struct *task =\n" +
                        "    kthread_run(my_thread_fn, NULL, \"my_kthread\");\n" +
                        "\n" +
                        "/* Stop (sets stop flag, waits for return): */\n" +
                        "kthread_stop(task);"
                    )
                }
            }

            item {
                SectionCard(title = "Comparison") {
                    CodeBlock(
                        "Mechanism   Context     Sleep?  Atomic?  Notes\n" +
                        "──────────────────────────────────────────────────────\n" +
                        "softirq     interrupt   No      Yes      Fixed set; concurrent on SMP\n" +
                        "tasklet     interrupt   No      Yes      Deprecated; serialized per tasklet\n" +
                        "workqueue   process     Yes     No       Preferred for new drivers\n" +
                        "kthread     process     Yes     No       For continuous background tasks"
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
