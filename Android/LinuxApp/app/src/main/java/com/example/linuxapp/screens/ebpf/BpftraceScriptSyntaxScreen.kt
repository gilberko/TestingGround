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
fun BpftraceScriptSyntaxScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "bpftrace — Script Syntax",
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
                SectionCard(title = "Script Files (.bt)") {
                    BodyText("For anything beyond a one-liner, save your program to a file. The .bt extension is the standard convention for bpftrace scripts. Run it with:")
                    CodeBlock("sudo bpftrace myscript.bt")
                    Spacer(Modifier.height(8.dp))
                    BodyText("You can add a shebang so the file is self-executing:")
                    CodeBlock(
                        "#!/usr/bin/env bpftrace\n" +
                        "\n" +
                        "BEGIN { printf(\"Starting\\n\"); }\n" +
                        "\n" +
                        "tracepoint:syscalls:sys_enter_execve {\n" +
                        "    printf(\"%s\\n\", str(args->filename));\n" +
                        "}\n" +
                        "\n" +
                        "END { printf(\"Done\\n\"); }"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Make it executable and run directly: chmod +x myscript.bt && sudo ./myscript.bt")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Pass arguments on the command line; inside the script they are \$1, \$2, \$3, ...")
                    CodeBlock(
                        "# Run:      sudo bpftrace myscript.bt 1234\n" +
                        "# In script:  if (pid == \$1) { ... }"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Probe Blocks") {
                    BodyText("A bpftrace program is a collection of probe:action pairs. The probe specifies where to attach; the action block is eBPF code that runs each time the probe fires.")
                    CodeBlock(
                        "// Basic form\n" +
                        "kprobe:vfs_read { printf(\"read\\n\"); }\n" +
                        "\n" +
                        "// Two probes sharing one action block\n" +
                        "kprobe:vfs_read, kprobe:vfs_write {\n" +
                        "    printf(\"%s called %s\\n\", comm, func);\n" +
                        "}\n" +
                        "\n" +
                        "// Wildcard: attach to every vfs_* kernel function\n" +
                        "kprobe:vfs_* { @calls[func] = count(); }"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Common probe types: kprobe/kretprobe (kernel function entry/return), tracepoint:cat:name (static kernel hooks), uprobe (user-space function), profile:hz:N (CPU sampling), interval:s:N (timer), BEGIN, END.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Predicates") {
                    BodyText("A predicate is a /condition/ placed between the probe and the action block. If false, the eBPF program is not entered at all — more efficient than an if() check inside the action.")
                    CodeBlock(
                        "// Only trace reads for PID passed as \$1\n" +
                        "kprobe:vfs_read /pid == \$1/ {\n" +
                        "    @reads[comm] = count();\n" +
                        "}\n" +
                        "\n" +
                        "// Only trace root processes\n" +
                        "tracepoint:syscalls:sys_enter_execve /uid == 0/ {\n" +
                        "    printf(\"root exec: %s\\n\", str(args->filename));\n" +
                        "}\n" +
                        "\n" +
                        "// Compound condition\n" +
                        "kprobe:do_sys_openat2 /comm == \"nginx\" && uid != 0/ {\n" +
                        "    printf(\"%s opens %s\\n\", comm, str(arg1));\n" +
                        "}"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "if, else if, else") {
                    BodyText("bpftrace supports standard C-style conditional branching inside action blocks. Use these when you need different behaviour for different cases within the same probe — unlike predicates, which gate the entire block.")
                    CodeBlock(
                        "kprobe:vfs_read /pid == \$1/ {\n" +
                        "    if (uid == 0) {\n" +
                        "        printf(\"root read: %s\\n\", comm);\n" +
                        "    } else if (comm == \"nginx\") {\n" +
                        "        printf(\"nginx read\\n\");\n" +
                        "    } else {\n" +
                        "        printf(\"other: %s uid=%d\\n\", comm, uid);\n" +
                        "    }\n" +
                        "}"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Note: bpftrace does NOT support the C ternary operator (?:) — use if/else instead. Loops are also not supported; bpftrace programs must be bounded and terminate on every path.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Local Variables (\$)") {
                    BodyText("Variables prefixed with \$ are local to the action block. Each time the probe fires, a fresh copy is created. They are NOT shared between probe firings or CPUs — they live only for the duration of one action execution.")
                    CodeBlock(
                        "kprobe:vfs_read     { @start[tid] = nsecs; }\n" +
                        "\n" +
                        "kretprobe:vfs_read /@start[tid]/ {\n" +
                        "    \$delta = nsecs - @start[tid];  // local, per-firing\n" +
                        "    \$us    = \$delta / 1000;        // compute microseconds\n" +
                        "    printf(\"%s took %d us\\n\", comm, \$us);\n" +
                        "    delete(@start[tid]);\n" +
                        "}"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Type is inferred from the assigned value — no declaration keyword. You can store integers, strings (str()), or pointers.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Maps (@)") {
                    BodyText("Variables prefixed with @ are global BPF maps. They persist across all probe firings and all CPUs — a value written in one probe action is visible in every other action and stays alive until the script exits.")
                    CodeBlock(
                        "// Unkeyed scalar\n" +
                        "@total = count();\n" +
                        "\n" +
                        "// Keyed by process name\n" +
                        "@reads[comm] = count();\n" +
                        "\n" +
                        "// Two-key map: record start time per thread\n" +
                        "@start[pid, tid] = nsecs;\n" +
                        "\n" +
                        "// Read a keyed value later (in a different probe)\n" +
                        "\$elapsed = nsecs - @start[pid, tid];\n" +
                        "\n" +
                        "// Remove entries\n" +
                        "delete(@start[pid, tid]);  // one entry\n" +
                        "clear(@start);             // all entries"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Maps can hold plain integer values or the result of aggregation functions (count, sum, hist, etc.). Keys can be integers, strings, or tuples of those.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "printf and nsecs") {
                    BodyText("printf(fmt, args...) prints immediately to stdout every time the probe fires. Use it for event-level logs where you want to see each individual occurrence.")
                    CodeBlock(
                        "tracepoint:syscalls:sys_enter_execve {\n" +
                        "    printf(\"%d %s exec: %s\\n\",\n" +
                        "           pid, comm, str(args->filename));\n" +
                        "}"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("nsecs is a built-in variable: nanoseconds since system boot. It is the standard way to measure elapsed time in bpftrace.")
                    CodeBlock(
                        "kprobe:vfs_write            { @t[tid] = nsecs; }\n" +
                        "kretprobe:vfs_write /@t[tid]/ {\n" +
                        "    \$ns = nsecs - @t[tid];\n" +
                        "    printf(\"%s write took %d ns (%d us)\\n\",\n" +
                        "           comm, \$ns, \$ns / 1000);\n" +
                        "    delete(@t[tid]);\n" +
                        "}"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Other useful built-ins for printf: pid, tid, uid, comm (process name, max 16 chars), cpu, func (probed function name), elapsed (ns since bpftrace started).")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Map Printing") {
                    BodyText("Maps are NOT printed on every probe fire — they accumulate silently. At program exit (Ctrl-C or when exit() is called) bpftrace automatically prints all named maps.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("You can also print a map explicitly at any point in your code:")
                    CodeBlock(
                        "print(@reads);          // print entire map\n" +
                        "print(@reads, 10);      // top-10 entries by value\n" +
                        "print(@reads, 10, 5);   // top-10, hide entries with value < 5"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("A common pattern: print and clear inside an interval block for rolling periodic snapshots, then let the final auto-print show totals at the end.")
                    CodeBlock(
                        "tracepoint:syscalls:sys_enter_read { @reads[comm] = count(); }\n" +
                        "\n" +
                        "interval:s:5 {\n" +
                        "    printf(\"=== top readers (last 5s) ===\\n\");\n" +
                        "    print(@reads, 5);\n" +
                        "    clear(@reads);\n" +
                        "}"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "BEGIN, END, and interval") {
                    BodyText("These special probe types control the script lifecycle:")
                    CodeBlock(
                        "BEGIN { ... }            // once at startup, before probes arm\n" +
                        "END   { ... }            // once at exit, after probes disarm\n" +
                        "                         // auto map-print happens AFTER END\n" +
                        "interval:s:5  { ... }    // timer: fires every 5 seconds\n" +
                        "interval:ms:500 { ... }  // every 500 milliseconds\n" +
                        "interval:hz:1 { ... }    // once per second"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("A complete script skeleton with all three lifecycle blocks:")
                    CodeBlock(
                        "#!/usr/bin/env bpftrace\n" +
                        "\n" +
                        "BEGIN {\n" +
                        "    printf(\"Tracing write() calls. Ctrl-C to stop.\\n\");\n" +
                        "}\n" +
                        "\n" +
                        "tracepoint:syscalls:sys_enter_write {\n" +
                        "    @writes[comm] = count();\n" +
                        "}\n" +
                        "\n" +
                        "interval:s:10 {\n" +
                        "    printf(\"\\n--- writes per process (last 10s) ---\\n\");\n" +
                        "    print(@writes, 5);\n" +
                        "    clear(@writes);\n" +
                        "}\n" +
                        "\n" +
                        "END {\n" +
                        "    printf(\"\\nFinal totals:\\n\");\n" +
                        "}"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("The auto map-print at exit runs AFTER END. Any maps still populated when END finishes will be printed automatically. You can also call print() inside END for custom formatting before that.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Aggregation Functions") {
                    BodyText("Aggregation functions accumulate values into a map across all probe firings. Each firing contributes one sample to the running total rather than overwriting the previous value.")
                    CodeBlock(
                        "@c  = count()                    // +1 each fire\n" +
                        "@s  = sum(args->count)           // accumulate a value\n" +
                        "@mn = min(retval)                // track minimum\n" +
                        "@mx = max(retval)                // track maximum\n" +
                        "@av = avg(nsecs - @t[tid])       // running average\n" +
                        "@st = stats(retval)              // count + avg + total\n" +
                        "@h  = hist(nsecs - @t[tid])      // power-of-2 histogram\n" +
                        "@lh = lhist(retval, 0, 1024, 64) // linear histogram"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Example: track total bytes written per process (write syscall, args->count is the byte count):")
                    CodeBlock(
                        "tracepoint:syscalls:sys_enter_write {\n" +
                        "    @bytes[comm] = sum(args->count);\n" +
                        "}"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Keys can be combined: @lat[comm, pid] = hist(delta) gives one histogram per (process-name, pid) pair.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Aggregation functions must be assigned to a map (@name), never to a local variable (\$name). Writing \$x = hist(val) is a bpftrace syntax error.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("The = in @lat_hist = hist(\$val) does not replace the stored value — it accumulates. Each probe firing adds one sample; bpftrace translates this into a kernel helper call that updates the in-kernel histogram. Think of it as appending to the histogram, not overwriting it. Multiple maps in the same script accumulate independently: @read_lat = hist(delta) and @write_lat = hist(delta) build two separate, unrelated histograms.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "What Are Tracepoints?") {
                    BodyText("Tracepoints are static instrumentation hooks placed directly in the Linux kernel source code by kernel developers using TRACE_EVENT() macros. They mark strategic points: syscall entry/exit, scheduler context switches, network packet events, block I/O, memory allocation, signals, and more.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("When no tool is attached they compile to a near-zero-cost no-op. When enabled via perf, ftrace, eBPF, or bpftrace a registered handler is called each time that code path is hit.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Key advantage over kprobes: tracepoints are stable. Their names and argument layouts are part of the kernel ABI and do not change between kernel versions. kprobes can attach to any kernel function but internal signatures can change without notice.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Tracepoints are grouped into categories:")
                    CodeBlock(
                        "syscalls  // sys_enter_*/sys_exit_* for every syscall\n" +
                        "sched     // sched_switch, sched_wakeup, sched_process_fork\n" +
                        "net       // netif_receive_skb, net_dev_xmit\n" +
                        "tcp       // tcp_retransmit_skb, tcp_send_reset\n" +
                        "block     // block_rq_insert, block_rq_complete\n" +
                        "kmem      // kmalloc, kfree, mm_page_alloc\n" +
                        "xdp       // xdp_exception, mem_connect\n" +
                        "signal    // signal_generate, signal_deliver\n" +
                        "irq       // irq_handler_entry, irq_handler_exit"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Inside a bpftrace action block, args is a BTF-typed struct automatically populated with the tracepoint fields. Write args->filename instead of casting raw integers.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Discovering Tracepoints & Arguments") {
                    BodyText("List available tracepoints from bpftrace:")
                    CodeBlock(
                        "sudo bpftrace -l 'tracepoint:*'           # all (very long list)\n" +
                        "sudo bpftrace -l 'tracepoint:syscalls:*'  # syscall category\n" +
                        "sudo bpftrace -l 'tracepoint:sched:*'\n" +
                        "sudo bpftrace -l 'tracepoint:*openat*'    # wildcard name search"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Show the field names and types for a specific tracepoint (verbose mode):")
                    CodeBlock(
                        "sudo bpftrace -lv tracepoint:syscalls:sys_enter_openat\n" +
                        "\n" +
                        "# Example output:\n" +
                        "# tracepoint:syscalls:sys_enter_openat\n" +
                        "#     int dfd\n" +
                        "#     const char * filename\n" +
                        "#     int flags\n" +
                        "#     umode_t mode"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Alternatively, read the raw format file (available even without bpftrace):")
                    CodeBlock("cat /sys/kernel/debug/tracing/events/syscalls/sys_enter_openat/format")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Access fields in a script via args->fieldname:")
                    CodeBlock(
                        "tracepoint:syscalls:sys_enter_openat {\n" +
                        "    printf(\"%s opens '%s' flags=0x%x\\n\",\n" +
                        "           comm,\n" +
                        "           str(args->filename),\n" +
                        "           args->flags);\n" +
                        "}"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "sys_enter_ and sys_exit_") {
                    BodyText("The tracepoint:syscalls category provides an enter/exit pair for virtually every Linux syscall (requires CONFIG_FTRACE_SYSCALLS=y, enabled in all mainstream distro kernels).")
                    CodeBlock(
                        "sys_enter_<name>  // fires on syscall entry; args = parameters\n" +
                        "sys_exit_<name>   // fires on return;  args->ret = return value"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("For openat: the entry args are dfd, filename, flags, mode. The exit arg is ret (new fd >= 0, or negative errno on error). A value of AT_FDCWD (-100) in dfd means the path is relative to the current working directory — the common case.")
                    CodeBlock(
                        "// Record the filename on entry\n" +
                        "tracepoint:syscalls:sys_enter_openat {\n" +
                        "    @file[tid] = args->filename;\n" +
                        "}\n" +
                        "\n" +
                        "// On exit, check for failure\n" +
                        "tracepoint:syscalls:sys_exit_openat /@file[tid]/ {\n" +
                        "    if (args->ret < 0) {\n" +
                        "        printf(\"FAILED open '%s' ret=%d\\n\",\n" +
                        "               str(@file[tid]), args->ret);\n" +
                        "    }\n" +
                        "    delete(@file[tid]);\n" +
                        "}"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Verify what is available on your kernel:")
                    CodeBlock(
                        "ls /sys/kernel/debug/tracing/events/syscalls/ | head -20\n" +
                        "# or\n" +
                        "sudo bpftrace -l 'tracepoint:syscalls:*' | wc -l"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Histograms — and Can bpftrace Drop Packets?") {
                    BodyText("hist(n) stores values in power-of-2 buckets: [0,1), [1,2), [2,4), [4,8), [8,16), ... Ideal for latencies that span nanoseconds to milliseconds. Printed as an ASCII bar chart at exit.")
                    CodeBlock(
                        "kprobe:vfs_read             { @s[tid] = nsecs; }\n" +
                        "kretprobe:vfs_read /@s[tid]/ {\n" +
                        "    @lat_ns = hist(nsecs - @s[tid]);\n" +
                        "    delete(@s[tid]);\n" +
                        "}\n" +
                        "\n" +
                        "# Typical output at Ctrl-C:\n" +
                        "# @lat_ns:\n" +
                        "# [256, 512)       142 |@@@@@@@@@@@@@@@@@@@@         |\n" +
                        "# [512, 1k)        311 |@@@@@@@@@@@@@@@@@@@@@@@@@@@@@|\n" +
                        "# [1k, 2k)          87 |@@@@@@@@@                    |"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("lhist(n, lo, hi, step) creates linear buckets when you know the range. Example: response sizes 0-1024 bytes in steps of 64:")
                    CodeBlock("@sizes = lhist(args->count, 0, 1024, 64);")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Can bpftrace drop packets (XDP_DROP)?")
                    Spacer(Modifier.height(8.dp))
                    BodyText("No. bpftrace is observability-only. It generates BPF_PROG_TYPE_KPROBE and BPF_PROG_TYPE_TRACEPOINT programs. These program types have no return value that affects kernel behaviour — they can only observe and record.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("XDP_DROP requires BPF_PROG_TYPE_XDP, which bpftrace cannot produce. You can observe XDP-related events (e.g., tracepoint:xdp:xdp_exception) but cannot cause them. For actual packet manipulation you need a full C eBPF program:")
                    CodeBlock(
                        "# Compile and attach an XDP drop program:\n" +
                        "clang -O2 -target bpf -c drop.bpf.c -o drop.bpf.o\n" +
                        "ip link set dev eth0 xdp obj drop.bpf.o sec xdp"
                    )
                }
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}
