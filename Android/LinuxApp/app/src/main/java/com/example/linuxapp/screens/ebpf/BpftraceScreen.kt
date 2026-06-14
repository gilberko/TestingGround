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
fun BpftraceScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "bpftrace",
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
                SectionCard(title = "What Is bpftrace") {
                    BodyText("bpftrace is a high-level tracing language built on top of Linux eBPF. It lets you write powerful one-liners and short scripts to trace kernel and user-space events — similar to DTrace or awk for dynamic tracing.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Under the hood bpftrace compiles your script to eBPF bytecode, loads it into the kernel, and collects the output. It reads BTF type information so it can automatically decode struct fields without manual type declarations.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Requirements: Linux 4.9+ (some features need 5.x), root or CAP_BPF + CAP_PERFMON, debugfs mounted at /sys/kernel/debug.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Installation") {
                    BodyText("The easiest way is the distribution package:")
                    CodeBlock(
                        "apt install bpftrace           # Debian/Ubuntu\n" +
                        "dnf install bpftrace           # Fedora/RHEL\n" +
                        "pacman -S bpftrace             # Arch"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Or build from source (requires cmake, libbpf-dev, libelf-dev):")
                    CodeBlock(
                        "git clone https://github.com/bpftrace/bpftrace\n" +
                        "cd bpftrace && mkdir build && cd build\n" +
                        "cmake -DCMAKE_BUILD_TYPE=Release ..\n" +
                        "make -j\$(nproc) && sudo make install"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Verify the install and list available probes:")
                    CodeBlock(
                        "sudo bpftrace --version\n" +
                        "sudo bpftrace -l 'tracepoint:syscalls:*'   # list probes\n" +
                        "sudo bpftrace -l 'kprobe:do_*'             # kernel functions"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Probe Types") {
                    BodyText("A bpftrace program is a set of probe:action pairs. The syntax is: probe_type:target { action }. Multiple probes can share the same action block.")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock(
                        "kprobe:function_name           # kernel function entry\n" +
                        "kretprobe:function_name        # kernel function return\n" +
                        "tracepoint:category:name       # static kernel tracepoint\n" +
                        "uprobe:/path/to/bin:func       # user-space function entry\n" +
                        "uretprobe:/path/to/bin:func    # user-space function return\n" +
                        "software:event:count           # software perf event\n" +
                        "hardware:cache-misses:1000     # PMU hardware counter\n" +
                        "profile:hz:99                  # CPU sample every 1/99 sec\n" +
                        "interval:s:5                   # timer: fire every 5 seconds\n" +
                        "BEGIN { ... }                  # runs once at startup\n" +
                        "END   { ... }                  # runs once at exit"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Tracepoints (tracepoint:cat:name) are preferred over kprobes when available because their arguments are stable across kernel versions. kprobes can attach to any non-blacklisted kernel function but argument layouts may change.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Built-In Variables") {
                    BodyText("These variables are available inside any action block without declaration:")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock(
                        "comm       // current process name (string, 16 chars max)\n" +
                        "pid        // process ID of the current thread\n" +
                        "tid        // thread ID (same as pid for single-threaded)\n" +
                        "uid / gid  // real user ID / group ID\n" +
                        "cpu        // CPU number where probe fired\n" +
                        "nsecs      // nanoseconds since system boot\n" +
                        "elapsed    // nanoseconds since bpftrace started\n" +
                        "retval     // return value (kretprobe or uretprobe only)\n" +
                        "args       // BTF-typed struct of tracepoint args\n" +
                        "arg0..arg9 // raw kprobe arguments as u64\n" +
                        "curtask    // pointer to current struct task_struct\n" +
                        "func       // name of the probed function\n" +
                        "probe      // full probe name string\n" +
                        "\$1, \$2... // positional script arguments"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("For tracepoints, args is automatically typed from BTF so you can write args->filename instead of manually casting. For kprobes, use arg0, arg1, ... (u64) and cast manually if needed.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Script positional arguments are passed on the command line:")
                    CodeBlock(
                        "sudo bpftrace myscript.bt 1234 mystring\n" +
                        "# Inside script: \$1 == 1234, \$2 == \"mystring\""
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Functions & Maps") {
                    BodyText("Output and utility functions:")
                    CodeBlock(
                        "printf(fmt, args...)   // formatted print to stdout\n" +
                        "str(ptr)               // read C string from kernel/user ptr\n" +
                        "str(ptr, len)          // read up to len bytes\n" +
                        "ksym(addr)             // resolve kernel symbol name\n" +
                        "usym(addr)             // resolve user-space symbol name\n" +
                        "join(array, sep)       // join char* array (e.g. argv)\n" +
                        "cat(file)              // print contents of a file\n" +
                        "exit()                 // terminate bpftrace cleanly"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Map aggregation functions (results stored in BPF maps, printed at END or on Ctrl-C):")
                    CodeBlock(
                        "count()               // increment a counter\n" +
                        "sum(n)                // accumulate a sum\n" +
                        "avg(n)                // compute running average\n" +
                        "min(n) / max(n)       // track minimum / maximum\n" +
                        "stats(n)              // count + avg + total\n" +
                        "hist(n)               // power-of-2 histogram\n" +
                        "lhist(n, lo, hi, step)// linear histogram"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Maps are global variables that persist across probe firings. Keys are optional:")
                    CodeBlock(
                        "@total = count()             // scalar counter\n" +
                        "@by_proc[comm] = count()     // counter keyed by process name\n" +
                        "@lat[pid, tid] = nsecs       // two-key map\n" +
                        "@hist = hist(retval)         // histogram (no key)\n" +
                        "delete(@lat[pid, tid])       // remove one entry\n" +
                        "clear(@by_proc)              // remove all entries\n" +
                        "print(@by_proc, 10)          // print top-10 entries"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Aggregation functions can only appear on the right-hand side of a map (@) assignment — never a local variable (\$). The = in @hist = hist(retval) does not replace the stored value; it accumulates one sample per probe firing. Multiple maps accumulate independently in the same script.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "One-Liner Examples") {
                    BodyText("Count syscalls per process name (Ctrl-C to print results):")
                    CodeBlock("sudo bpftrace -e 'tracepoint:syscalls:sys_enter_* { @[comm] = count(); }'")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Log every execve call with the binary name:")
                    CodeBlock(
                        "sudo bpftrace -e '\n" +
                        "  tracepoint:syscalls:sys_enter_execve {\n" +
                        "    printf(\"%s -> %s\\n\", comm, str(args->filename));\n" +
                        "  }'"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Trace file opens via a kernel function (kprobe; arg1 = filename ptr):")
                    CodeBlock(
                        "sudo bpftrace -e '\n" +
                        "  kprobe:do_sys_openat2 {\n" +
                        "    printf(\"%s opens %s\\n\", comm, str(arg1));\n" +
                        "  }'"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Sample kernel stacks at 99 Hz — raw data for CPU flamegraphs:")
                    CodeBlock("sudo bpftrace -e 'profile:hz:99 { @[kstack] = count(); }'")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Measure vfs_read latency histogram per process:")
                    CodeBlock(
                        "sudo bpftrace -e '\n" +
                        "  kprobe:vfs_read            { @s[tid] = nsecs; }\n" +
                        "  kretprobe:vfs_read /@s[tid]/ {\n" +
                        "    @ns[comm] = hist(nsecs - @s[tid]);\n" +
                        "    delete(@s[tid]);\n" +
                        "  }'"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Who is sending signals, and to which PID?")
                    CodeBlock(
                        "sudo bpftrace -e '\n" +
                        "  tracepoint:signal:signal_generate {\n" +
                        "    printf(\"%s -> pid %d  sig %d\\n\",\n" +
                        "           comm, args->pid, args->sig);\n" +
                        "  }'"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Print new TCP connect attempts:")
                    CodeBlock(
                        "sudo bpftrace -e '\n" +
                        "  kprobe:tcp_connect {\n" +
                        "    printf(\"%s (pid %d) TCP connect\\n\", comm, pid);\n" +
                        "  }'"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Script Files (.bt)") {
                    BodyText("For multi-probe programs save to a .bt file and run with: sudo bpftrace script.bt")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Example — count forks per process and print on exit:")
                    CodeBlock(
                        "#!/usr/bin/env bpftrace\n" +
                        "\n" +
                        "BEGIN {\n" +
                        "    printf(\"Tracing forks... Ctrl-C to stop.\\n\");\n" +
                        "}\n" +
                        "\n" +
                        "tracepoint:sched:sched_process_fork {\n" +
                        "    @forks[comm] = count();\n" +
                        "}\n" +
                        "\n" +
                        "END {\n" +
                        "    printf(\"\\nFork counts per process:\\n\");\n" +
                        "    print(@forks);\n" +
                        "}"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Predicates (/condition/) filter probe firing before entering the BPF program — more efficient than an if() inside the action:")
                    CodeBlock(
                        "// Only trace reads for a specific PID (passed as \$1)\n" +
                        "kprobe:vfs_read /pid == \$1/ {\n" +
                        "    @reads[comm] = count();\n" +
                        "}\n" +
                        "\n" +
                        "// Run: sudo bpftrace trace_pid.bt 1234"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Interval probes are useful for printing periodic summaries:")
                    CodeBlock(
                        "tracepoint:syscalls:sys_enter_read { @reads[comm] = count(); }\n" +
                        "\n" +
                        "interval:s:5 {\n" +
                        "    printf(\"=== reads per process (last 5s) ===\\n\");\n" +
                        "    print(@reads);\n" +
                        "    clear(@reads);\n" +
                        "}"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}
