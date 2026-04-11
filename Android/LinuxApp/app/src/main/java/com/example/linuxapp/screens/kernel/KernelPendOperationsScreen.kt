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
fun KernelPendOperationsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "How To Pend Operations",
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
                SectionCard(title = "Why Pend / Sleep in a Driver?") {
                    BodyText(
                        "A driver often needs to wait for an event — e.g. a char device read() that blocks until data " +
                        "arrives, or a command that sleeps until hardware signals completion. The kernel provides three " +
                        "escalating levels of abstraction for this: manual state manipulation, wait queues, and completions."
                    )
                }
                Spacer(Modifier.height(12.dp))
            }

            item {
                SectionCard(title = "Option 1 — set_current_state + schedule()") {
                    BodyText("The low-level building block. You control the task state directly.")
                    Spacer(Modifier.height(8.dp))

                    BodyText("set_current_state(state) vs __set_current_state(state)")
                    Spacer(Modifier.height(4.dp))
                    BodyText(
                        "set_current_state() calls smp_store_mb() internally — a store + full memory barrier. " +
                        "This ensures the state change is visible to all CPUs before the subsequent condition check. " +
                        "Without the barrier there is a race: another CPU could set data_ready and call wake_up_process " +
                        "(which sees TASK_RUNNING and does nothing) while this CPU then reads data_ready as false and " +
                        "sleeps — hanging forever.\n\n" +
                        "__set_current_state() is a plain assignment with no barrier. It is safe only when ordering " +
                        "is already guaranteed — typically when restoring TASK_RUNNING after breaking out of the loop, " +
                        "where you have already confirmed the condition and no waker race is possible."
                    )
                    Spacer(Modifier.height(8.dp))

                    BodyText("Why use a loop?")
                    Spacer(Modifier.height(4.dp))
                    BodyText(
                        "A TASK_INTERRUPTIBLE task can be woken spuriously by a signal before the condition is true. " +
                        "The loop re-checks the condition after each wake-up; if still false it sets the state again " +
                        "and calls schedule() again. Without the loop, a signal wake-up would cause the driver to " +
                        "proceed with no data — a bug."
                    )
                    Spacer(Modifier.height(8.dp))

                    CodeBlock(
                        """/* ---- sleeping side (e.g. char device read()) ---- */
static struct task_struct *waiting_task;
static int data_ready;

ssize_t my_read(struct file *f, char __user *buf,
                size_t len, loff_t *off)
{
    waiting_task = current;

    for (;;) {
        /* barrier ensures state visible before condition check */
        set_current_state(TASK_INTERRUPTIBLE);
        if (data_ready)
            break;
        schedule();   /* yield CPU; woken by wake_up_process */
        /* loop: re-check — could have been a signal wake-up */
    }
    /* no barrier needed — condition already confirmed */
    __set_current_state(TASK_RUNNING);

    /* copy data to user ... */
    data_ready = 0;
    return copy_to_user(buf, ...) ? -EFAULT : len;
}

/* ---- waking side (e.g. IRQ handler or write()) ---- */
void produce_data(void)
{
    data_ready = 1;
    if (waiting_task)
        wake_up_process(waiting_task); /* sets TASK_RUNNING, enqueues */
}"""
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText(
                        "Limitations: you manage the task pointer yourself, must handle the NULL case, " +
                        "and only one waiter is supported without extra bookkeeping. " +
                        "For anything beyond a toy example, use wait queues instead."
                    )
                }
                Spacer(Modifier.height(12.dp))
            }

            item {
                SectionCard(title = "Option 2 — Wait Queues (recommended for drivers)") {
                    BodyText(
                        "Wait queues automate the set_current_state / schedule loop, handle multiple waiters, " +
                        "and correctly close the race between condition check and sleep. " +
                        "The wait_event* macros re-evaluate the condition expression each time the task is woken."
                    )
                    Spacer(Modifier.height(8.dp))

                    BodyText("Declaration")
                    CodeBlock(
                        """/* Static (global or in a struct): */
DECLARE_WAIT_QUEUE_HEAD(my_wq);

/* Dynamic (runtime init): */
wait_queue_head_t my_wq;
init_waitqueue_head(&my_wq);"""
                    )
                    Spacer(Modifier.height(8.dp))

                    BodyText("Sleeping — wait_event variants")
                    CodeBlock(
                        """/* Uninterruptible — use sparingly, cannot be killed by signals: */
wait_event(my_wq, condition);

/* Interruptible — recommended for most drivers: */
int ret = wait_event_interruptible(my_wq, condition);
if (ret == -ERESTARTSYS)   /* signal interrupted */
    return -EINTR;

/* With timeout (in jiffies): */
long ret = wait_event_interruptible_timeout(
                my_wq, condition, msecs_to_jiffies(500));
/* ret > 0: condition met; ret == 0: timed out; ret < 0: signal */

/* Uninterruptible with timeout: */
long ret = wait_event_timeout(my_wq, condition,
                              msecs_to_jiffies(500));"""
                    )
                    Spacer(Modifier.height(8.dp))

                    BodyText("Waking — wake_up variants")
                    CodeBlock(
                        """/* Wake one or all tasks in TASK_UNINTERRUPTIBLE or TASK_INTERRUPTIBLE: */
wake_up(&my_wq);

/* Wake only TASK_INTERRUPTIBLE tasks: */
wake_up_interruptible(&my_wq);

/* Wake every waiter regardless of state: */
wake_up_all(&my_wq);"""
                    )
                    Spacer(Modifier.height(8.dp))

                    BodyText("Full char device example")
                    CodeBlock(
                        """static DECLARE_WAIT_QUEUE_HEAD(read_wq);
static int data_ready;

/* Called from IRQ handler or write() path: */
void signal_data_ready(void)
{
    data_ready = 1;
    wake_up_interruptible(&read_wq);
}

ssize_t my_read(struct file *f, char __user *buf,
                size_t len, loff_t *off)
{
    int ret;

    ret = wait_event_interruptible(read_wq, data_ready != 0);
    if (ret)            /* -ERESTARTSYS */
        return -EINTR;

    data_ready = 0;
    /* copy to user ... */
    return len;
}"""
                    )
                }
                Spacer(Modifier.height(12.dp))
            }

            item {
                SectionCard(title = "Option 3 — Completions (one-shot events)") {
                    BodyText(
                        "Completions are optimised for \"wait until X has happened exactly once\" — e.g. wait for a " +
                        "DMA transfer to finish, wait for a kernel thread to initialise, or wait for hardware ready. " +
                        "Unlike wait queues there is no condition expression to evaluate; you simply signal done once."
                    )
                    Spacer(Modifier.height(8.dp))

                    BodyText("Declaration")
                    CodeBlock(
                        """/* Static: */
DECLARE_COMPLETION(my_comp);

/* Dynamic: */
struct completion my_comp;
init_completion(&my_comp);

/* On stack (short-lived, e.g. inside a function): */
DECLARE_COMPLETION_ONSTACK(my_comp);"""
                    )
                    Spacer(Modifier.height(8.dp))

                    BodyText("Waiting")
                    CodeBlock(
                        """/* Uninterruptible: */
wait_for_completion(&my_comp);

/* Interruptible (returns -ERESTARTSYS on signal): */
int ret = wait_for_completion_interruptible(&my_comp);

/* With timeout (returns remaining jiffies, or 0 on timeout): */
unsigned long ret = wait_for_completion_timeout(
                        &my_comp, msecs_to_jiffies(1000));

/* Interruptible + timeout: */
long ret = wait_for_completion_interruptible_timeout(
                &my_comp, msecs_to_jiffies(1000));"""
                    )
                    Spacer(Modifier.height(8.dp))

                    BodyText("Signaling")
                    CodeBlock(
                        """/* Wake one waiter (common): */
complete(&my_comp);

/* Wake all waiters and mark permanently done: */
complete_all(&my_comp);"""
                    )
                    Spacer(Modifier.height(8.dp))

                    BodyText("reinit_completion — when and why")
                    Spacer(Modifier.height(4.dp))
                    BodyText(
                        "After a completion fires its internal counter is 0 (\"done\"). " +
                        "To reuse it for the next operation call reinit_completion(&c) to reset it to \"not done\".\n\n" +
                        "Critical ordering rule: call reinit_completion() BEFORE starting the new operation " +
                        "(before you issue the command that will call complete()). " +
                        "If you call it after, there is a race: complete() fires between the new operation starting " +
                        "and your reinit_completion() call — reinit resets the counter after it was already " +
                        "signaled, and the next wait_for_completion() hangs forever.\n\n" +
                        "Never call reinit_completion() while a waiter is already sleeping on it."
                    )
                    Spacer(Modifier.height(8.dp))

                    CodeBlock(
                        """static struct completion dma_done;

/* One-time init: */
init_completion(&dma_done);

void start_dma_transfer(void)
{
    /* Correct reuse pattern — reset BEFORE triggering */
    reinit_completion(&dma_done);
    issue_dma_command();          /* hardware will call complete() on IRQ */
    wait_for_completion(&dma_done);
    /* transfer is finished here */
}

/* Called from DMA IRQ handler: */
void dma_irq_handler(void)
{
    complete(&dma_done);
}"""
                    )
                }
                Spacer(Modifier.height(12.dp))
            }

            item {
                SectionCard(title = "Comparison") {
                    CodeBlock(
                        """Method                        Best for                  Interruptible   Loop
---------------------------------------------------------------------
set_current_state+schedule    Manual/learning           manual          manual
wait_event*()                 General driver sleep/wake yes (variant)   automatic
completions                   One-shot "done" events    yes (variant)   N/A"""
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText(
                        "Rule of thumb: use completions for \"wait for hardware/thread done once\", " +
                        "wait queues for \"wait until a condition becomes true\", and raw " +
                        "set_current_state only when you need precise manual control."
                    )
                }
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}
