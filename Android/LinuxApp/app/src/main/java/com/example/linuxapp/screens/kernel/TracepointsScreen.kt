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
fun TracepointsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Tracepoints",
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
                SectionCard(title = "What Are Tracepoints?") {
                    BodyText("A tracepoint is a STATIC hook that kernel developers place at an interesting spot in the source code, such as \"a process was forked\", \"a block request was issued\" or \"a packet was received\". It is compiled into the kernel and looks like an ordinary function call:")
                    CodeBlock(
                        """/* kernel/fork.c (simplified) */
trace_sched_process_fork(current, p);"""
                    )
                    BodyText("When nobody is attached, the call costs almost nothing. It sits behind a static key (jump label): the hook is a NOP instruction that the kernel patches into a jump only when a probe is registered.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("When a probe IS attached, every registered probe function is called with the tracepoint's typed arguments (real C pointers such as struct task_struct *).")
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """                 tracepoints              kprobes
Placement        static, in source        dynamic, any instruction
Stability        fairly stable names      breaks when functions are
                 + typed arguments        renamed or inlined
Overhead (off)   ~0 (NOP)                 0 (nothing inserted)
Overhead (on)    direct function call     breakpoint / ftrace trampoline
Arguments        typed, documented        raw registers
Coverage         only where devs put them almost anywhere"""
                    )
                    BodyText("Users of tracepoints: ftrace (tracefs), perf, eBPF / bpftrace, LTTng, and kernel modules that register their own probe functions.")
                }
            }
            item {
                SectionCard(title = "Where Do Tracepoints Live?") {
                    BodyText("Definitions: include/trace/events/*.h (sched.h, signal.h, task.h, block.h, net.h, syscalls.h ...), grouped by subsystem (TRACE_SYSTEM).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Run-time list (tracefs):")
                    CodeBlock(
                        """# ls /sys/kernel/tracing/events/
block  irq  kmem  net  oom  sched  signal  syscalls  task ...

# ls /sys/kernel/tracing/events/sched/ | grep process
sched_process_exec  sched_process_exit  sched_process_fork
sched_process_free  sched_process_wait  ...

# cat /sys/kernel/tracing/events/sched/sched_process_fork/format
    field:char parent_comm[16]; ...
    field:pid_t parent_pid; ...
    field:char child_comm[16]; ...
    field:pid_t child_pid; ..."""
                    )
                    BodyText("Using them from user space, without writing a module:")
                    CodeBlock(
                        """# ftrace
cd /sys/kernel/tracing
echo 1 > events/sched/sched_process_fork/enable
echo 1 > events/sched/sched_process_exit/enable
cat trace_pipe
echo 0 > events/sched/enable

# perf
perf list 'sched:*'
perf record -e sched:sched_process_exec -a -- sleep 10

# bpftrace
bpftrace -l 'tracepoint:sched:*'
bpftrace -e 'tracepoint:sched:sched_process_exec
             { printf("%d exec %s\n", pid, str(args->filename)); }'"""
                    )
                }
            }
            item {
                SectionCard(title = "Registering a Probe from a Module") {
                    BodyText("A probe is a function whose first parameter is void *data (your private pointer), followed by the tracepoint's TP_PROTO arguments:")
                    CodeBlock(
                        """/* include/trace/events/sched.h */
TRACE_EVENT(sched_process_fork,
    TP_PROTO(struct task_struct *parent, struct task_struct *child),
    ...

/* your probe: */
static void my_fork_probe(void *data,
                          struct task_struct *parent,
                          struct task_struct *child);"""
                    )
                    BodyText("Method 1: exported tracepoints. If the tracepoint is exported to modules with EXPORT_TRACEPOINT_SYMBOL_GPL(name), include its header and use the generated helpers:")
                    CodeBlock(
                        """#include <trace/events/foo.h>

ret = register_trace_foo(my_probe, my_data);    /* 0 on success */
...
unregister_trace_foo(my_probe, my_data);"""
                    )
                    BodyText("Method 2: non-exported tracepoints. Most core tracepoints (sched_*, signal_*, task_*) are NOT exported, so register_trace_sched_process_fork() would fail to link in a module. Instead, look the tracepoint up by name and use the generic API, which is exported (GPL):")
                    CodeBlock(
                        """#include <linux/tracepoint.h>

void for_each_kernel_tracepoint(
        void (*fct)(struct tracepoint *tp, void *priv), void *priv);

int tracepoint_probe_register(struct tracepoint *tp,
                              void *probe, void *data);
int tracepoint_probe_unregister(struct tracepoint *tp,
                                void *probe, void *data);"""
                    )
                    BodyText("for_each_kernel_tracepoint() iterates over the built-in kernel tracepoints (not the ones defined in modules). Compare tp->name to find the one you want.")
                }
            }
            item {
                SectionCard(title = "Deregistering Safely") {
                    BodyText("Unregistering removes your probe from the tracepoint's probe array, but another CPU may be running your probe at that exact moment. Tracepoint probe arrays are protected by RCU-style mechanisms (readers call the probes without taking any lock), so you must wait before your code disappears:")
                    CodeBlock(
                        """static void __exit my_exit(void)
{
    tracepoint_probe_unregister(tp, my_probe, NULL);
    /* ... unregister all other probes ... */

    tracepoint_synchronize_unregister();   /* REQUIRED */
    /* now no CPU can still be inside my_probe, so it is
       safe to free 'data' and let the module unload */
}"""
                    )
                    BodyText("tracepoint_synchronize_unregister() is a static inline in <linux/tracepoint.h>. It waits for the grace period(s) that the tracepoint machinery uses. Call it once, after ALL unregister calls.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Also: register and unregister with the SAME (probe, data) pair, because that pair identifies the registration. Registering the same pair twice returns -EEXIST.")
                }
            }
            item {
                SectionCard(title = "Tracepoints for Process / Thread Start and End") {
                    BodyText("Yes, the kernel has tracepoints for every stage of a task's life:")
                    CodeBlock(
                        """Event                 Tracepoint             Probe args (after void *data)
──────────────────────────────────────────────────────────────────────
task created          task:task_newtask      task, u64 clone_flags
fork / clone done     sched:sched_process_fork  parent, child
exec() new program    sched:sched_process_exec  p, pid_t old_pid,
                                                struct linux_binprm *bprm
task exits            sched:sched_process_exit  p, bool group_dead *
task struct freed     sched:sched_process_free  p
signal sent           signal:signal_generate  sig, info, task,
                                              group, result
signal delivered      signal:signal_deliver   sig, info, ka
OOM killer victim     oom:mark_victim         (OOM kills)"""
                    )
                    BodyText("* group_dead was added to sched_process_exit in 2025, through the tip sched/core tree (probably Linux 6.16, but check your kernel). On older kernels the probe is just (void *data, struct task_struct *p). Always check include/trace/events/sched.h for the kernel you build against.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Process vs. thread: in Linux both are tasks created by clone(), and sched_process_fork fires for both. Tell them apart with:")
                    CodeBlock(
                        """child->pid == child->tgid   → new PROCESS (thread-group leader)
child->pid != child->tgid   → new THREAD inside process child->tgid
(task_newtask: clone_flags & CLONE_THREAD tells the same thing)"""
                    )
                    BodyText("Process vs. thread on exit: sched_process_exit fires for every exiting thread. group_dead == true means this was the last live thread, so the whole process is ending.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("\"Killed\": no single tracepoint means \"killed\". Combine these:")
                    BodyText("• signal_generate with sig == SIGKILL (or SIGTERM ...) shows WHO sent it (current) and to WHOM (task).")
                    BodyText("• In sched_process_exit, p->exit_code holds the wait-status: (exit_code & 0x7f) is the terminating signal number (0 for a normal exit), and (exit_code >> 8) & 0xff is the exit status.")
                    BodyText("• oom:mark_victim fires when the OOM killer selects a process.")
                }
            }
            item {
                SectionCard(title = "Example — Process/Thread Lifecycle Monitor Module") {
                    CodeBlock(
                        """// SPDX-License-Identifier: GPL-2.0
#include <linux/module.h>
#include <linux/tracepoint.h>
#include <linux/sched.h>
#include <linux/binfmts.h>     /* struct linux_binprm */
#include <linux/signal.h>

/* ---------- probes ---------- */

static void probe_fork(void *data, struct task_struct *parent,
                       struct task_struct *child)
{
    if (child->pid == child->tgid)
        pr_info("lifemon: NEW PROCESS pid=%d parent=%d (%s)\n",
                child->pid, parent->pid, parent->comm);
    else
        pr_info("lifemon: NEW THREAD tid=%d in process %d\n",
                child->pid, child->tgid);
    /* child->comm is still the parent's name until exec() */
}

static void probe_exec(void *data, struct task_struct *p,
                       pid_t old_pid, struct linux_binprm *bprm)
{
    pr_info("lifemon: EXEC pid=%d file=%s\n", p->pid, bprm->filename);
}

/* Linux >= ~6.16 signature; older kernels: drop 'group_dead' */
static void probe_exit(void *data, struct task_struct *p,
                       bool group_dead)
{
    int sig = p->exit_code & 0x7f;

    if (!group_dead) {
        pr_info("lifemon: THREAD EXIT tid=%d\n", p->pid);
        return;
    }
    if (sig)
        pr_info("lifemon: PROCESS %d (%s) KILLED by signal %d\n",
                p->tgid, p->comm, sig);
    else
        pr_info("lifemon: PROCESS %d (%s) exited, status %d\n",
                p->tgid, p->comm, (p->exit_code >> 8) & 0xff);
}

static void probe_signal(void *data, int sig, struct kernel_siginfo *info,
                         struct task_struct *task, int group, int result)
{
    if (sig == SIGKILL)
        pr_info("lifemon: SIGKILL %d (%s) -> %d (%s)\n",
                current->pid, current->comm, task->pid, task->comm);
}

/* ---------- lookup + register ---------- */

struct tp_hook {
    const char        *name;
    void              *probe;
    struct tracepoint *tp;
    bool               registered;
};

static struct tp_hook hooks[] = {
    { .name = "sched_process_fork", .probe = probe_fork   },
    { .name = "sched_process_exec", .probe = probe_exec   },
    { .name = "sched_process_exit", .probe = probe_exit   },
    { .name = "signal_generate",    .probe = probe_signal },
};

static void find_tp(struct tracepoint *tp, void *priv)
{
    int i;

    for (i = 0; i < ARRAY_SIZE(hooks); i++)
        if (!strcmp(tp->name, hooks[i].name))
            hooks[i].tp = tp;
}

static void unregister_all(void)
{
    int i;

    for (i = 0; i < ARRAY_SIZE(hooks); i++)
        if (hooks[i].registered)
            tracepoint_probe_unregister(hooks[i].tp,
                                        hooks[i].probe, NULL);
    tracepoint_synchronize_unregister();
}

static int __init lifemon_init(void)
{
    int i, ret;

    for_each_kernel_tracepoint(find_tp, NULL);

    for (i = 0; i < ARRAY_SIZE(hooks); i++) {
        if (!hooks[i].tp) {
            pr_err("lifemon: tracepoint %s not found\n", hooks[i].name);
            ret = -ENOENT;
            goto fail;
        }
        ret = tracepoint_probe_register(hooks[i].tp,
                                        hooks[i].probe, NULL);
        if (ret)
            goto fail;
        hooks[i].registered = true;
    }
    pr_info("lifemon: loaded\n");
    return 0;
fail:
    unregister_all();
    return ret;
}

static void __exit lifemon_exit(void)
{
    unregister_all();
    pr_info("lifemon: unloaded\n");
}

module_init(lifemon_init);
module_exit(lifemon_exit);
MODULE_LICENSE("GPL");   /* the tracepoint API is EXPORT_SYMBOL_GPL */"""
                    )
                    BodyText("Try it:")
                    CodeBlock(
                        """sudo insmod lifemon.ko
sleep 100 &  kill -9 %1
sudo dmesg | grep lifemon
lifemon: NEW PROCESS pid=5123 parent=4870 (bash)
lifemon: EXEC pid=5123 file=/usr/bin/sleep
lifemon: SIGKILL 4870 (bash) -> 5123 (sleep)
lifemon: PROCESS 5123 (sleep) KILLED by signal 9
sudo rmmod lifemon"""
                    )
                    BodyText("The probe signature must match TP_PROTO exactly. The register API takes a void *, so the compiler can't check it, and a mismatch reads garbage arguments or crashes.")
                }
            }
            item {
                SectionCard(title = "Defining Your Own Tracepoint in a Module") {
                    BodyText("Modules can add their own tracepoints, which then appear in tracefs, perf and bpftrace like built-in ones. Step 1 is a trace header (my_trace.h):")
                    CodeBlock(
                        """#undef TRACE_SYSTEM
#define TRACE_SYSTEM mymod

#if !defined(_MY_TRACE_H) || defined(TRACE_HEADER_MULTI_READ)
#define _MY_TRACE_H

#include <linux/tracepoint.h>

TRACE_EVENT(mymod_packet,
    TP_PROTO(int id, unsigned int len),       /* C prototype      */
    TP_ARGS(id, len),                         /* argument names   */
    TP_STRUCT__entry(                         /* ring-buffer record */
        __field(int,          id)
        __field(unsigned int, len)
    ),
    TP_fast_assign(                           /* fill the record  */
        __entry->id  = id;
        __entry->len = len;
    ),
    TP_printk("id=%d len=%u", __entry->id, __entry->len)
);

#endif /* _MY_TRACE_H */

/* must be outside the include guard: */
#undef TRACE_INCLUDE_PATH
#define TRACE_INCLUDE_PATH .
#undef TRACE_INCLUDE_FILE
#define TRACE_INCLUDE_FILE my_trace
#include <trace/define_trace.h>"""
                    )
                    BodyText("Step 2: in exactly ONE .c file, define CREATE_TRACE_POINTS before the include. That generates the tracepoint's code and data. Other files include the header without it.")
                    CodeBlock(
                        """/* mymod.c */
#define CREATE_TRACE_POINTS
#include "my_trace.h"

static void handle_packet(int id, unsigned int len)
{
    trace_mymod_packet(id, len);   /* ~free when disabled */
    ...
}

/* optional: let OTHER modules register_trace_mymod_packet() */
EXPORT_TRACEPOINT_SYMBOL_GPL(mymod_packet);"""
                    )
                    CodeBlock(
                        """# Makefile — so define_trace.h finds "./my_trace.h"
obj-m += mymod.o
CFLAGS_mymod.o := -I${'$'}(src)"""
                    )
                    BodyText("Step 3: use it.")
                    CodeBlock(
                        """echo 1 > /sys/kernel/tracing/events/mymod/mymod_packet/enable
cat /sys/kernel/tracing/trace_pipe
  <idle>-0  [002] ..s1.  1234.5678: mymod_packet: id=7 len=1500

bpftrace -e 'tracepoint:mymod:mymod_packet { @bytes = sum(args->len); }'"""
                    )
                    BodyText("Guarding expensive argument preparation: if computing an argument is costly, wrap it in if (trace_mymod_packet_enabled()) { ... } so the work is done only when someone is listening.")
                }
            }
            item {
                SectionCard(title = "Caveats") {
                    BodyText("• Probes run in the context of the code that hit the tracepoint, which is often atomic (preemption disabled, IRQs off, or holding the runqueue lock). Never sleep, never take a mutex, and keep probes short. Use GFP_ATOMIC, or better, don't allocate at all.")
                    BodyText("• Tracepoints are fairly stable but NOT a guaranteed ABI. Arguments do change between kernel versions (for example the 2025 group_dead addition to sched_process_exit), so check the header for every kernel you support.")
                    BodyText("• The registration API is GPL-only, so your module must be MODULE_LICENSE(\"GPL\").")
                    BodyText("• Probes on hot tracepoints (sched_switch, irq, net) run millions of times per second, so they add measurable overhead.")
                    BodyText("• For process events from user space without a module, alternatives are eBPF (tracepoint programs), the netlink proc connector (PROC_EVENT_FORK / EXEC / EXIT), and the audit subsystem.")
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
