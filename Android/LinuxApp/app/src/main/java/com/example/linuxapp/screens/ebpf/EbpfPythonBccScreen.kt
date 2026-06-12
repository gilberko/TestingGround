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
fun EbpfPythonBccScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "eBPF — Python and BCC",
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
                SectionCard(title = "What Is BCC?") {
                    BodyText("BCC (BPF Compiler Collection) is a toolkit for creating efficient kernel tracing and manipulation programs. Its key feature is Python (and Lua) bindings that let you write a single script containing both the eBPF C code and the user-space logic.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("BCC embeds LLVM/Clang and compiles the eBPF C code at runtime from a Python string. This means:")
                    CodeBlock("""Pros:
  - Single Python script — no Makefile, no pre-compilation step
  - Easy to prototype and iterate quickly
  - Rich built-in tracing tools: execsnoop, opensnoop,
    tcptop, biosnoop, and 100+ others in /usr/share/bcc/tools/
  - BPF_HASH, BPF_ARRAY, BPF_PERF_OUTPUT macros simplify maps

Cons:
  - Requires LLVM + Clang on the target machine (~200 MB)
  - Slow startup (LLVM compiles the C string on every run)
  - Not CO-RE — compiled against the running kernel only
  - Not suitable for production daemons or resource-constrained
    environments (use libbpf + clang ahead-of-time for those)""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("BCC is ideal for observability scripts, one-off investigations, and learning eBPF without the full C toolchain setup.")
                }
            }
            item {
                SectionCard(title = "Installation") {
                    BodyText("Install BCC from your distribution's package manager for the best compatibility. The PyPI package requires system LLVM to already be installed.")
                    CodeBlock("""# Ubuntu / Debian (recommended)
sudo apt install bpfcc-tools python3-bpfcc \
    linux-headers-$(uname -r)

# Fedora / RHEL / Rocky
sudo dnf install bcc bcc-tools python3-bcc \
    kernel-devel

# Arch Linux
sudo pacman -S bcc bcc-tools python-bcc

# From PyPI (requires system LLVM already installed):
pip install bcc

# Verify installation:
python3 -c "from bcc import BPF; print('BCC OK')"

# Run a built-in BCC tool to confirm everything works:
sudo /usr/share/bcc/tools/execsnoop""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("BCC tools require root privileges (or CAP_BPF + CAP_PERFMON + CAP_SYS_ADMIN). Always run BCC scripts with sudo.")
                }
            }
            item {
                SectionCard(title = "Basic Structure") {
                    BodyText("A BCC Python script has three parts: the eBPF C code as a string, setup/attach calls in Python, and an output loop. Here is the minimal hello-world pattern:")
                    CodeBlock("""#!/usr/bin/env python3
from bcc import BPF

# 1. eBPF C code embedded as a Python string
bpf_source = r'''
#include <uapi/linux/ptrace.h>

// This function is called on every sys_clone (fork)
int hello(struct pt_regs *ctx) {
    bpf_trace_printk("Hello from PID %d\\n",
                     bpf_get_current_pid_tgid() >> 32);
    return 0;
}
'''

# 2. Compile and load the eBPF program
b = BPF(text=bpf_source)

# 3. Attach to a kernel event
b.attach_kprobe(event=b.get_syscall_fnname("clone"),
                fn_name="hello")

# 4. Print output (blocks until Ctrl-C)
print("Tracing fork()... Ctrl-C to stop")
try:
    b.trace_print()
except KeyboardInterrupt:
    pass""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("b.get_syscall_fnname(\"clone\") returns the correct platform-specific syscall name (e.g., __x64_sys_clone on x86-64, __arm64_sys_clone on ARM64) so the script is portable across architectures.")
                }
            }
            item {
                SectionCard(title = "Example: Tracing execve Calls") {
                    BodyText("This example prints the name of every new program launched on the system, including the PID and executable path.")
                    CodeBlock("""#!/usr/bin/env python3
from bcc import BPF

bpf_source = r'''
#include <uapi/linux/ptrace.h>
#include <linux/sched.h>

// Struct to carry event data from eBPF to Python
struct data_t {
    u32  pid;
    char comm[TASK_COMM_LEN];   // calling process name (16 chars)
    char fname[64];             // new executable filename
};

// Perf event output map — streams events to user space
BPF_PERF_OUTPUT(events);

// Tracepoint fires after every successful execve
TRACEPOINT_PROBE(sched, sched_process_exec)
{
    struct data_t data = {};

    data.pid = bpf_get_current_pid_tgid() >> 32;
    bpf_get_current_comm(data.comm, sizeof(data.comm));

    // args->filename is the path of the new executable
    bpf_probe_read_str(data.fname, sizeof(data.fname),
                       args->filename);

    events.perf_submit(args, &data, sizeof(data));
    return 0;
}
'''

b = BPF(text=bpf_source)
# Note: TRACEPOINT_PROBE auto-attaches; no attach_* call needed

# Python callback — called for every event
def handle_event(cpu, data, size):
    event = b["events"].event(data)
    print(f"PID={event.pid:6}  "
          f"CALLER={event.comm.decode():16}  "
          f"EXEC={event.fname.decode()}")

# Open the perf buffer
b["events"].open_perf_buffer(handle_event)

print("Tracing execve... Ctrl-C to stop")
try:
    while True:
        b.perf_buffer_poll()
except KeyboardInterrupt:
    pass""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("BPF_PERF_OUTPUT + open_perf_buffer() is the standard BCC pattern for streaming structured data from the eBPF program to Python. perf_buffer_poll() drains available events and calls your callback for each one.")
                }
            }
            item {
                SectionCard(title = "Example: Counting Events with a Map") {
                    BodyText("Maps persist across invocations. This example counts how many times each process ID calls open():")
                    CodeBlock("""#!/usr/bin/env python3
import ctypes
import time
from bcc import BPF

bpf_source = r'''
// BPF_HASH declares a hash map: key=u32 (pid), value=u64 (count)
BPF_HASH(counts, u32, u64);

int count_open(struct pt_regs *ctx)
{
    u32 pid = bpf_get_current_pid_tgid() >> 32;
    u64 zero = 0, *count;

    // Look up the existing count, or initialise to zero
    count = counts.lookup_or_try_init(&pid, &zero);
    if (count)
        (*count)++;   // atomic increment

    return 0;
}
'''

b = BPF(text=bpf_source)
b.attach_kprobe(event=b.get_syscall_fnname("open"),
                fn_name="count_open")

print("Counting open() calls... sleeping 5s")
time.sleep(5)

# Read the map from Python — b["counts"] is dict-like
print(f"\n{'PID':>8}  {'COUNT':>8}")
print("-" * 20)
for k, v in sorted(b["counts"].items(),
                   key=lambda kv: kv[1].value,
                   reverse=True)[:10]:
    print(f"{k.value:>8}  {v.value:>8}")

# Reset the map for the next collection interval
b["counts"].clear()""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("BCC map access from Python: b[\"map_name\"] returns a map object. .items() returns a list of (key_ctypes, value_ctypes) pairs. .clear() empties the map. The key and value types are automatically converted to ctypes structures matching the C declaration.")
                }
            }
            item {
                SectionCard(title = "Reading Data Methods") {
                    BodyText("BCC provides four ways to receive data from an eBPF program:")
                    CodeBlock("""# 1. trace_print() — simplest, uses bpf_trace_printk() in C
#    Blocking loop that reads /sys/kernel/debug/tracing/trace_pipe
b.trace_print()
# or single line:
b.trace_fields()   # returns (task, pid, cpu, flags, ts, msg)

# 2. open_perf_buffer() + perf_buffer_poll()
#    Best for structured events; C uses BPF_PERF_OUTPUT
def on_event(cpu, data, size):
    e = b["events"].event(data)
    print(e.pid, e.comm)

b["events"].open_perf_buffer(on_event)
while True:
    b.perf_buffer_poll(timeout=100)   # 100ms timeout

# 3. open_ring_buffer() + ring_buffer_poll()
#    Newer, lower overhead than perf buffer (kernel 5.8+)
#    C uses BPF_RINGBUF_OUTPUT
def on_ring_event(ctx, data, size):
    e = b["rb"].event(data)
    print(e.pid)

b["rb"].open_ring_buffer(on_ring_event)
while True:
    b.ring_buffer_poll()

# 4. Direct map access — for hash/array maps
#    Poll the map contents at intervals from Python
counts = b["counts"]
for key, val in counts.items():
    print(key.value, val.value)

# Aggregation helpers on map objects:
b["latency"].print_log2_hist("usecs")   # histogram
b["counts"].sum()                       # sum all values""")
                }
            }
            item {
                SectionCard(title = "Attaching and Detaching") {
                    BodyText("BCC provides attach methods for every major eBPF hook point:")
                    CodeBlock("""# kprobe / kretprobe
b.attach_kprobe(event="vfs_open", fn_name="trace_open")
b.attach_kretprobe(event="vfs_open", fn_name="trace_open_ret")
b.detach_kprobe("vfs_open")
b.detach_kretprobe("vfs_open")

# Tracepoint — format is "category:name"
b.attach_tracepoint(tp="sched:sched_process_exec",
                    fn_name="trace_exec")
b.detach_tracepoint("sched:sched_process_exec")
# Note: TRACEPOINT_PROBE() in C auto-attaches — no attach call needed

# USDT (user-space static tracepoints)
b.enable_probe(probe="http__request", fn_name="trace_request")
# Must provide the binary path:
u = USDT(path="/usr/bin/node")
u.enable_probe(probe="http__server__request", fn_name="do_trace")
b = BPF(text=bpf_src, usdt_contexts=[u])

# XDP
fn = b.load_func("xdp_filter", BPF.XDP)
b.attach_xdp(dev="eth0", fn=fn, flags=0)
# Flags: 0=generic, XDP_FLAGS_DRV_MODE=native, XDP_FLAGS_HW_MODE=offload
b.remove_xdp(dev="eth0", flags=0)   # explicit detach required for XDP

# Perf event (hardware/software counters)
b.attach_perf_event(ev_type=PerfType.SOFTWARE,
                    ev_config=PerfSWConfig.CPU_CLOCK,
                    fn_name="sample_stack", sample_freq=99)""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Lifecycle: kprobe, kretprobe, and tracepoint attachments are automatically detached when the BPF object (b) is garbage-collected or the script exits. XDP is kernel-persistent and must be explicitly removed with remove_xdp() even after the script exits.")
                }
            }
            item {
                SectionCard(title = "BCC vs libbpf") {
                    BodyText("Choose the right tool for your use case:")
                    CodeBlock("""Feature             BCC (Python)        libbpf (C, ahead-of-time)
------------------  ------------------  -------------------------
Compilation         Runtime (LLVM)      Ahead-of-time (clang)
LLVM on target      Required            Not needed
Startup time        Slow (~1-3s)        Fast (milliseconds)
Ease of use         Easy (Python)       More complex (C)
Language            Python / Lua        C (user-space loader)
CO-RE portability   No (same kernel)    Yes (cross-kernel)
Production daemons  Not recommended     Recommended
Observability tools Excellent           Good
Learning eBPF       Great starting pt.  Better for production
Map access          Dict-like Python    bpf_map_lookup_elem()
Output              trace_print/perf    Ring buffer / perf buf
Dependencies        python3-bpfcc+LLVM  libbpf-dev only
Main users          sysadmins, SREs     Cilium, Falco, Katran""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Practical recommendation: use BCC to explore and prototype. Once the program is working and you need to ship it in production or on machines without LLVM, rewrite the eBPF C part with a libbpf/skeleton user-space loader compiled ahead-of-time.")
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
