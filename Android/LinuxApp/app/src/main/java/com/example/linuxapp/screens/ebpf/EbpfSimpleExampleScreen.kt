package com.example.linuxapp.screens.ebpf

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
import com.example.linuxapp.screens.kernel.BodyText
import com.example.linuxapp.screens.kernel.CodeBlock
import com.example.linuxapp.screens.kernel.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EbpfSimpleExampleScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "eBPF — A Simple Example",
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
                SectionCard(title = "What This Example Does") {
                    BodyText("We will write a small eBPF program that attaches to the sched_process_exec tracepoint. This tracepoint fires every time a process calls execve() — that is, every time a new program is launched. The BPF program reads the new process name, PID, and parent PID, then prints them as a debug message.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The output appears in the kernel's trace buffer and can be read from userspace with a single cat command — no custom reader needed.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Two files to create:")
                    CodeBlock("""trace_execve.bpf.c   — the eBPF program (runs in kernel)
trace_execve.c       — the userspace loader (loads & pins it)
Makefile             — build instructions""")
                }
            }
            item {
                SectionCard(title = "File 1: trace_execve.bpf.c (the BPF program)") {
                    BodyText("This is the code that runs inside the kernel. It must be compiled to BPF bytecode with clang.")
                    CodeBlock("""// trace_execve.bpf.c
#include "vmlinux.h"              // kernel type definitions (BTF)
#include <bpf/bpf_helpers.h>     // bpf_printk, BPF_CORE_READ, ...
#include <bpf/bpf_tracing.h>     // SEC() macro, BPF_PROG

char LICENSE[] SEC("license") = "GPL";  // required for some helpers

// The tracepoint sched/sched_process_exec fires right after
// a successful execve — the new binary has been loaded.
// The kernel provides a context struct with process info.
SEC("tracepoint/sched/sched_process_exec")
int handle_exec(struct trace_event_raw_sched_process_exec *ctx)
{
    // Read the new process name (from the tracepoint args)
    // The filename field points into kernel memory — use
    // BPF_CORE_READ to safely read it.
    char filename[64];
    unsigned fname_off = ctx->__data_loc_filename & 0xFFFF;
    bpf_probe_read_str(filename, sizeof(filename),
                       (void *)ctx + fname_off);

    // Get PID and parent PID of the new process
    __u64 pid_tgid = bpf_get_current_pid_tgid();
    __u32 pid  = (__u32)(pid_tgid >> 32);   // TGID = user PID
    __u32 tid  = (__u32)(pid_tgid);

    // Get the process name of whoever called execve
    char caller[16];
    bpf_get_current_comm(caller, sizeof(caller));

    // bpf_printk writes to /sys/kernel/debug/tracing/trace_pipe
    bpf_printk("execve: pid=%u caller=%s -> new=%s\\n",
               pid, caller, filename);

    return 0;
}""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Key helpers used:")
                    CodeBlock("""bpf_get_current_pid_tgid()
  Returns (tgid << 32 | tid). In user-space terms, tgid
  is the PID shown by ps. Shift right by 32 to get it.

bpf_get_current_comm(buf, size)
  Copies the current task's comm (name, max 16 chars)
  into buf. This is the process that called execve().

bpf_probe_read_str(dst, size, src)
  Safely copies a string from kernel memory into the
  BPF stack. Required because direct pointer dereference
  is not allowed in BPF.

bpf_printk(fmt, ...)
  Writes a formatted string to the trace ring buffer.
  Output readable at:
    /sys/kernel/debug/tracing/trace_pipe""")
                }
            }
            item {
                SectionCard(title = "File 2: trace_execve.c (the userspace loader)") {
                    BodyText("This program loads the BPF object, attaches it to the tracepoint, and keeps it alive until you press Ctrl-C. It uses the libbpf skeleton generated from the BPF object.")
                    CodeBlock("""// trace_execve.c
#include <stdio.h>
#include <signal.h>
#include <unistd.h>
#include "trace_execve.skel.h"   // auto-generated by bpftool

static volatile int running = 1;

static void handle_sigint(int sig)
{
    running = 0;
}

int main(void)
{
    struct trace_execve_bpf *skel;
    int err;

    // Open + verify + load the BPF object into the kernel
    skel = trace_execve_bpf__open_and_load();
    if (!skel) {
        fprintf(stderr, "Failed to open/load BPF skeleton\\n");
        return 1;
    }

    // Attach: connect the BPF program to the tracepoint
    err = trace_execve_bpf__attach(skel);
    if (err) {
        fprintf(stderr, "Failed to attach BPF program\\n");
        goto cleanup;
    }

    signal(SIGINT, handle_sigint);

    printf("Tracing execve()... press Ctrl-C to stop.\\n");
    printf("Run in another terminal:\\n");
    printf("  sudo cat /sys/kernel/debug/tracing/trace_pipe\\n\\n");

    // The BPF program runs automatically on every execve.
    // We just keep the loader alive so the attachment persists.
    while (running)
        sleep(1);

    printf("\\nDetaching...\\n");

cleanup:
    // Detaches and unloads the BPF program
    trace_execve_bpf__destroy(skel);
    return err < 0 ? 1 : 0;
}""")
                }
            }
            item {
                SectionCard(title = "File 3: Makefile") {
                    BodyText("The build has two stages: (1) compile the BPF program to bytecode and generate the skeleton header, (2) compile the userspace loader.")
                    CodeBlock("""# Makefile
CLANG   ?= clang
CC      ?= gcc
ARCH    := $(shell uname -m | \
               sed 's/x86_64/x86/' | sed 's/aarch64/arm64/')

# BPF compilation flags
BPF_CFLAGS := -g -O2 -target bpf \
              -D__TARGET_ARCH_$(ARCH) \
              -I/usr/include/$(shell uname -m)-linux-gnu

.PHONY: all clean

all: trace_execve

# Step 1: compile BPF C → BPF ELF object
trace_execve.bpf.o: trace_execve.bpf.c
	$(CLANG) $(BPF_CFLAGS) -c $< -o $@

# Step 2: generate libbpf skeleton header from the BPF object
trace_execve.skel.h: trace_execve.bpf.o
	bpftool gen skeleton $< > $@

# Step 3: compile the userspace loader, link against libbpf
trace_execve: trace_execve.c trace_execve.skel.h
	$(CC) -g -O2 $< -o $@ -lbpf -lelf -lz

clean:
	rm -f trace_execve trace_execve.bpf.o trace_execve.skel.h""")
                }
            }
            item {
                SectionCard(title = "Prerequisites — What to Install") {
                    BodyText("Before building, install the required packages:")
                    CodeBlock("""# Ubuntu / Debian
sudo apt install -y clang llvm libbpf-dev \
    linux-headers-$(uname -r) bpftool \
    libelf-dev zlib1g-dev

# Fedora / RHEL
sudo dnf install -y clang llvm libbpf-devel \
    kernel-devel bpftool elfutils-libelf-devel

# Arch Linux
sudo pacman -S clang llvm libbpf linux-headers bpf""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("You also need vmlinux.h — the single-header file containing all kernel type definitions generated from BTF (BPF Type Format). Generate it from the running kernel:")
                    CodeBlock("""bpftool btf dump file /sys/kernel/btf/vmlinux format c \
    > vmlinux.h

# This file goes in the same directory as trace_execve.bpf.c.
# It must match your running kernel version.""")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Check that BTF and the tracepoint exist on your kernel:")
                    CodeBlock("""ls /sys/kernel/btf/vmlinux           # BTF must exist
ls /sys/kernel/debug/tracing/events/sched/sched_process_exec/
# If the events directory is missing, mount debugfs first:
sudo mount -t debugfs none /sys/kernel/debug""")
                }
            }
            item {
                SectionCard(title = "How to Build") {
                    BodyText("Put all three files (trace_execve.bpf.c, trace_execve.c, Makefile) plus the generated vmlinux.h in the same directory, then:")
                    CodeBlock("""# Generate vmlinux.h (once per kernel version)
bpftool btf dump file /sys/kernel/btf/vmlinux format c \
    > vmlinux.h

# Build everything
make

# Expected output:
# clang -g -O2 -target bpf ... -c trace_execve.bpf.c -o trace_execve.bpf.o
# bpftool gen skeleton trace_execve.bpf.o > trace_execve.skel.h
# gcc -g -O2 trace_execve.c -o trace_execve -lbpf -lelf -lz

ls -la
# trace_execve          ← the loader binary
# trace_execve.bpf.o    ← BPF ELF object
# trace_execve.skel.h   ← auto-generated skeleton
# vmlinux.h             ← kernel type headers""")
                }
            }
            item {
                SectionCard(title = "How to Load and Run") {
                    BodyText("Loading a BPF program requires root (or CAP_BPF + CAP_PERFMON).")
                    CodeBlock("""# Terminal 1 — run the loader (keeps BPF attached)
sudo ./trace_execve
# Output:
# Tracing execve()... press Ctrl-C to stop.
# Run in another terminal:
#   sudo cat /sys/kernel/debug/tracing/trace_pipe

# Terminal 2 — watch the debug output
sudo cat /sys/kernel/debug/tracing/trace_pipe

# Terminal 3 — trigger some execs to see output
ls
pwd
echo hello

# You should see lines like:
#  trace_execve-12345 [000] .... execve: pid=12345 caller=bash -> new=/usr/bin/ls
#  trace_execve-12346 [001] .... execve: pid=12346 caller=bash -> new=/usr/bin/pwd""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The BPF program stays loaded as long as the loader process is alive. When the loader exits (or you press Ctrl-C), the BPF program is automatically detached and unloaded.")
                }
            }
            item {
                SectionCard(title = "How to Unload") {
                    BodyText("Because we used the libbpf skeleton with auto-attach, the BPF program is tied to the loader process's lifetime.")
                    CodeBlock("""# Stop the loader with Ctrl-C in Terminal 1
^C
# Detaching...

# The BPF program is now gone.
# Verify: trace_pipe no longer shows execve events.

# Alternative: kill the loader from another terminal
sudo kill $(pgrep trace_execve)

# To check what BPF programs are currently loaded
sudo bpftool prog list
# When trace_execve is stopped, its entry disappears.""")
                }
            }
            item {
                SectionCard(title = "Viewing the Debug Output") {
                    BodyText("bpf_printk() writes to the kernel's ftrace ring buffer. There are two ways to read it:")
                    CodeBlock("""# Method 1: trace_pipe (streaming, blocks until data)
sudo cat /sys/kernel/debug/tracing/trace_pipe
# Stays open and prints new events as they arrive.
# Ctrl-C to stop reading (does NOT stop the BPF program).

# Method 2: trace (snapshot, shows buffered events)
sudo cat /sys/kernel/debug/tracing/trace
# Shows current buffer contents. Does not block.
# Clear the buffer:
sudo sh -c 'echo > /sys/kernel/debug/tracing/trace'

# Output format:
# <comm>-<pid> [<cpu>] <flags> <timestamp>: <message>
#
# bash-1234 [000] d... 12345.678901: bpf_trace_printk:
#     execve: pid=1234 caller=bash -> new=/usr/bin/ls""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("If tracing/trace_pipe is empty, check that tracing is enabled:")
                    CodeBlock("""cat /sys/kernel/debug/tracing/tracing_on
# Should be 1. If 0, enable it:
echo 1 > /sys/kernel/debug/tracing/tracing_on

# Also check bpf_trace is not filtered out:
cat /sys/kernel/debug/tracing/set_event
# To enable all events including bpf:
echo > /sys/kernel/debug/tracing/set_event""")
                }
            }
            item {
                SectionCard(title = "Alternative: bpftrace One-liner") {
                    BodyText("For quick exploration, bpftrace lets you write the same program as a one-liner — no C files, no Makefile, no skeleton. Install bpftrace and run:")
                    CodeBlock("""sudo apt install bpftrace   # or dnf/pacman

# Trace every execve — print PID, calling process, new binary
sudo bpftrace -e '
tracepoint:sched:sched_process_exec {
    printf("pid=%d caller=%s file=%s\\n",
           pid, comm, str(args->filename));
}'

# Example output:
# pid=7842 caller=bash file=/usr/bin/ls
# pid=7843 caller=bash file=/usr/bin/cat
# pid=7844 caller=bash file=/usr/bin/grep

# Ctrl-C to stop. The program is unloaded automatically.

# Filter: only show processes launched by bash
sudo bpftrace -e '
tracepoint:sched:sched_process_exec
/comm == "bash"/ {
    printf("bash launched: pid=%d file=%s\\n",
           pid, str(args->filename));
}'""")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("bpftrace is great for one-off investigations. The libbpf skeleton approach (shown above) is the right choice when you need a persistent daemon, maps, or more complex logic.")
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
