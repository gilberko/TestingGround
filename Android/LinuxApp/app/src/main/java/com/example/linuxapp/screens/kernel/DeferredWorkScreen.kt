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
                        "Interrupt Handling & Deferred Work",
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
                SectionCard(title = "Registering an Interrupt Handler") {
                    BodyText(
                        "A driver registers an interrupt handler with request_irq(). This tells the kernel " +
                        "which function to call when the hardware raises the specified IRQ line."
                    )
                    CodeBlock(
                        "#include <linux/interrupt.h>\n" +
                        "\n" +
                        "/* Signature of the handler callback: */\n" +
                        "irqreturn_t my_handler(int irq, void *dev_id);\n" +
                        "\n" +
                        "/* Register (e.g., in probe()): */\n" +
                        "int err = request_irq(\n" +
                        "    irq,           /* IRQ line number (from platform data,\n" +
                        "                      device tree, or pci_irq_vector()) */\n" +
                        "    my_handler,    /* callback */\n" +
                        "    IRQF_SHARED,   /* flags — see below */\n" +
                        "    \"my_device\",   /* name shown in /proc/interrupts */\n" +
                        "    &mydev         /* dev_id — identifies this device;\n" +
                        "                      for shared IRQs, must be unique */\n" +
                        ");\n" +
                        "if (err)\n" +
                        "    return err;\n" +
                        "\n" +
                        "/* Unregister (e.g., in remove()): */\n" +
                        "free_irq(irq, &mydev);  /* dev_id must match request_irq */"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Common flags:")
                    CodeBlock(
                        "IRQF_SHARED          — share the IRQ line with other devices\n" +
                        "IRQF_TRIGGER_RISING  — trigger on rising edge\n" +
                        "IRQF_TRIGGER_FALLING — trigger on falling edge\n" +
                        "IRQF_TRIGGER_HIGH    — trigger while line is high (level)\n" +
                        "IRQF_TRIGGER_LOW     — trigger while line is low (level)"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "free_irq() must always be called before the driver unloads or the device is " +
                        "removed, to prevent stale handler pointers from being called after the driver " +
                        "memory is freed."
                    )
                }
            }

            item {
                SectionCard(title = "What To Do In The IRQ Handler") {
                    BodyText(
                        "The handler runs in interrupt context. This means: no sleeping, no blocking, " +
                        "no mutexes, no memory allocation with GFP_KERNEL. It must finish in microseconds. " +
                        "The handler must return IRQ_HANDLED if it serviced the interrupt, or IRQ_NONE if " +
                        "the interrupt was not from this device (important for shared IRQ lines)."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Do in the handler:")
                    CodeBlock(
                        "• Acknowledge the interrupt to the hardware — write to a status register\n" +
                        "  to clear the IRQ line, otherwise the interrupt fires again immediately\n" +
                        "• Read the minimum necessary data from hardware registers (a status byte,\n" +
                        "  a single value from a FIFO)\n" +
                        "• Save that data to a shared buffer or flag\n" +
                        "• Schedule deferred work to process the data\n" +
                        "• Return IRQ_HANDLED"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Defer to a workqueue or tasklet:")
                    CodeBlock(
                        "• Anything taking more than a few microseconds\n" +
                        "• I2C / SPI register reads (they can sleep)\n" +
                        "• Memory allocation with GFP_KERNEL\n" +
                        "• Copying data to user space (copy_to_user)\n" +
                        "• Processing a full packet or data buffer"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Pattern — split IRQ handler and deferred processing:")
                    CodeBlock(
                        "struct work_struct process_work;\n" +
                        "u8 saved_status;\n" +
                        "\n" +
                        "irqreturn_t my_handler(int irq, void *dev_id) {\n" +
                        "    /* 1. Acknowledge + read minimal data */\n" +
                        "    saved_status = readb(dev->base + STATUS_REG);\n" +
                        "    writeb(ACK, dev->base + STATUS_REG);\n" +
                        "\n" +
                        "    /* 2. Schedule slow processing */\n" +
                        "    schedule_work(&process_work);\n" +
                        "    return IRQ_HANDLED;\n" +
                        "}\n" +
                        "\n" +
                        "void process_work_fn(struct work_struct *w) {\n" +
                        "    /* process context: can sleep, allocate, etc. */\n" +
                        "    handle_status(saved_status);\n" +
                        "}"
                    )
                }
            }

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
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("__do_softirq() — budget and limits:")
                    BodyText(
                        "When an IRQ handler returns, the kernel calls __do_softirq() inline on the same CPU " +
                        "to drain pending softirqs. To prevent softirq processing from starving user-space " +
                        "processes, __do_softirq() enforces two hard limits:"
                    )
                    CodeBlock(
                        "#define MAX_SOFTIRQ_RESTART  10               /* max loop iterations   */\n" +
                        "#define MAX_SOFTIRQ_TIME     msecs_to_jiffies(2)  /* ~2 ms time budget */\n" +
                        "\n" +
                        "/* __do_softirq() loop (simplified): */\n" +
                        "restart = 0;\n" +
                        "end = jiffies + MAX_SOFTIRQ_TIME;\n" +
                        "do {\n" +
                        "    pending = local_softirq_pending();\n" +
                        "    handle_softirqs(pending);\n" +
                        "    restart++;\n" +
                        "} while (local_softirq_pending() &&\n" +
                        "         restart < MAX_SOFTIRQ_RESTART &&\n" +
                        "         !need_resched() &&\n" +
                        "         time_before(jiffies, end));\n" +
                        "\n" +
                        "if (local_softirq_pending())\n" +
                        "    wakeup_softirqd();  /* hand off remaining work to ksoftirqd */"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "When the restart count hits 10, the 2 ms budget expires, or the scheduler flags a " +
                        "higher-priority task (need_resched()), __do_softirq() stops inline and wakes ksoftirqd " +
                        "to handle the remaining pending softirqs. This prevents softirq storms (e.g. a network " +
                        "flood) from monopolizing the CPU indefinitely."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("ksoftirqd — the softirq kernel thread:")
                    BodyText(
                        "ksoftirqd/N is a per-CPU kernel thread — one dedicated thread per CPU, not a shared " +
                        "pool. Each thread is pinned to its own CPU and named ksoftirqd/0, ksoftirqd/1, etc."
                    )
                    CodeBlock(
                        "/* ksoftirqd thread function (simplified): */\n" +
                        "static int ksoftirqd_fn(void *data)\n" +
                        "{\n" +
                        "    while (!kthread_should_stop()) {\n" +
                        "        if (!local_softirq_pending()) {\n" +
                        "            schedule();   /* nothing pending — sleep */\n" +
                        "            continue;\n" +
                        "        }\n" +
                        "        /* Call the same function used in the inline path: */\n" +
                        "        __do_softirq();\n" +
                        "    }\n" +
                        "    return 0;\n" +
                        "}"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        "Scheduling class:  SCHED_NORMAL (competes with normal user processes)\n" +
                        "Context:           process context — the thread itself can be preempted\n" +
                        "                   and scheduled out between runs\n" +
                        "Handlers:          still run in softirq context inside __do_softirq();\n" +
                        "                   cannot sleep during handler execution\n" +
                        "CPU binding:       pinned — ksoftirqd/0 only runs on CPU 0\n" +
                        "\n" +
                        "Wake-up triggers:\n" +
                        "  • __do_softirq() hits restart/time limit → wakeup_softirqd()\n" +
                        "  • raise_softirq() called from process context (not in interrupt)\n" +
                        "  • Softirq pending when returning from IRQ with need_resched set"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "The design goal: softirqs do high-throughput low-latency work inline after IRQs. " +
                        "ksoftirqd is a safety valve — it ensures that a burst of softirq activity cannot " +
                        "monopolize the CPU indefinitely, since ksoftirqd can be preempted by user-space " +
                        "processes running at the same or higher scheduling priority."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Interrupt state during softirq and tasklet execution:")
                    BodyText(
                        "IRQ handlers run with local interrupts disabled — the CPU automatically masks " +
                        "interrupt delivery when it enters the handler. No other hardware IRQ can preempt it."
                    )
                    BodyText(
                        "Softirqs and tasklets run with interrupts re-enabled. They are still in atomic " +
                        "context (preemption disabled, cannot sleep), but hardware interrupts CAN fire " +
                        "and will preempt the softirq/tasklet mid-execution."
                    )
                    BodyText(
                        "Consequence: if data is shared between an IRQ handler and a softirq/tasklet, " +
                        "the softirq/tasklet must use spin_lock_irqsave(). A plain spin_lock() only " +
                        "disables preemption — it does not prevent the IRQ handler from firing on the " +
                        "same CPU and deadlocking on the already-held lock."
                    )
                    BodyText(
                        "Workqueues differ: they run in full process context with both interrupts and " +
                        "preemption enabled, and may run on any CPU."
                    )
                    CodeBlock(
                        "/* Data shared between IRQ handler and a tasklet */\n" +
                        "DEFINE_SPINLOCK(shared_lock);\n" +
                        "\n" +
                        "/* IRQ handler — interrupts already disabled by the CPU */\n" +
                        "irqreturn_t my_irq_handler(int irq, void *dev_id) {\n" +
                        "    spin_lock(&shared_lock);     /* plain lock OK — IRQs already off */\n" +
                        "    shared_data->rx_count++;\n" +
                        "    spin_unlock(&shared_lock);\n" +
                        "    tasklet_schedule(&my_tasklet);\n" +
                        "    return IRQ_HANDLED;\n" +
                        "}\n" +
                        "\n" +
                        "/* Tasklet — runs with interrupts ENABLED */\n" +
                        "void my_tasklet_fn(struct tasklet_struct *t) {\n" +
                        "    unsigned long flags;\n" +
                        "    /* IRQ handler can fire here — must use irqsave */\n" +
                        "    spin_lock_irqsave(&shared_lock, flags);\n" +
                        "    process(shared_data->rx_count);\n" +
                        "    spin_unlock_irqrestore(&shared_lock, flags);\n" +
                        "}"
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
