package com.example.linuxapp.screens.ebpf

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.linuxapp.screens.kernel.BodyText
import com.example.linuxapp.screens.kernel.CodeBlock
import com.example.linuxapp.screens.kernel.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EbpfProgramTypesScreen(onBack: () -> Unit) {
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Types of eBPF Programs",
                        color = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFF00FF41)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = innerPadding.calculateTopPadding() + 8.dp,
                bottom = innerPadding.calculateBottomPadding() + 8.dp
            )
        ) {
            item {
                SectionCard(title = "Tracing — kprobes / kretprobes") {
                    BodyText("kprobes let you attach an eBPF program to the entry of any kernel function. kretprobes attach to the return. They are dynamic — you don't need to recompile the kernel. Any exported (non-inlined) kernel function is a valid target.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Use cases: trace which processes open files, measure time spent in a syscall, log TCP connection establishment, audit privilege escalations.")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock("// Attach to tcp_connect entry:\nSEC(\"kprobe/tcp_connect\")\nint BPF_KPROBE(trace_tcp_connect, struct sock *sk)\n{\n    __u32 pid = bpf_get_current_pid_tgid() >> 32;\n    bpf_printk(\"pid %d called tcp_connect\\n\", pid);\n    return 0;\n}")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Limitation: kprobes attach to function names which can change across kernel versions. If a function is renamed or inlined, the probe breaks. For stable interfaces, prefer tracepoints.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Tracing — Tracepoints") {
                    BodyText("Tracepoints are static instrumentation points built into the kernel source. Kernel developers explicitly mark locations as tracepoints, so they are part of the stable ABI — they don't change across kernel versions the way function names can.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Tracepoints are organized as subsystem:event_name. You can see all available tracepoints:")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock("ls /sys/kernel/debug/tracing/events/\n# syscalls/  sched/  net/  block/  irq/ ...\n\ncat /sys/kernel/debug/tracing/events/syscalls/\\\nsys_enter_openat/format\n# Shows the fields available in the tracepoint context")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Example: trace every openat() syscall to log which files are being opened:")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock("SEC(\"tracepoint/syscalls/sys_enter_openat\")\nint trace_openat(struct trace_event_raw_sys_enter *ctx)\n{\n    char fname[256];\n    bpf_probe_read_user_str(fname, sizeof(fname),\n        (void *)ctx->args[1]);\n    bpf_printk(\"openat: %s\\n\", fname);\n    return 0;\n}")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Other important tracepoints:\n• sched:sched_switch — every context switch (CPU scheduling)\n• sched:sched_process_fork — every fork()\n• net:net_dev_xmit — packet transmission\n• block:block_rq_issue — block device I/O requests")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Tracing — uprobes & USDT") {
                    BodyText("uprobes are the user-space equivalent of kprobes — they attach to any function in any user-space binary, including running processes. You can probe functions in nginx, Python, databases, or your own application without modifying them.")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock("# Attach to the readline() function in bash:\nbpftrace -e 'uprobe:/bin/bash:readline\n{ printf(\"command: %s\\n\", str(retval)); }'")
                    Spacer(Modifier.height(8.dp))
                    BodyText("USDT (User Statically-Defined Tracepoints) are static tracepoints embedded in user-space applications — the user-space equivalent of kernel tracepoints. Many runtimes ship with USDT probes built in:")
                    Spacer(Modifier.height(8.dp))
                    BodyText("• CPython: python:function__entry, python:function__return\n• Node.js: node:http__server__request, node:gc__start\n• PostgreSQL: postgresql:query__start, postgresql:query__done\n• MySQL: mysql:query__start, mysql:query__done\n• JVM (via agent): method entry/exit, GC events")
                    Spacer(Modifier.height(8.dp))
                    BodyText("USDT probes are more stable than uprobes (function names can change; probe names are part of the ABI) and carry structured arguments rather than just raw registers.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Networking — XDP (eXpress Data Path)") {
                    BodyText("XDP is the fastest eBPF networking hook. It runs at the earliest possible point: inside the NIC driver, immediately after a packet is received — before the kernel allocates an sk_buff structure. This makes it dramatically cheaper than socket-level filtering.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("An XDP program receives a raw packet buffer and returns one of these actions:")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock("XDP_DROP    // drop the packet (cheapest DDoS mitigation)\nXDP_PASS    // pass to normal kernel networking stack\nXDP_TX      // retransmit out the same interface\nXDP_REDIRECT // redirect to another interface or CPU queue\nXDP_ABORTED  // drop + trigger tracepoint (error/debug)")
                    Spacer(Modifier.height(8.dp))
                    BodyText("XDP modes:\n• Native XDP — runs in the NIC driver itself; requires driver support but is the fastest\n• Generic XDP — runs at a higher layer (after sk_buff allocation); works on any driver but loses most of the speed advantage\n• Offloaded XDP — runs on the NIC hardware itself; only a few smart NICs support this")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Use cases: DDoS mitigation at line rate (Cloudflare drops hundreds of Gbps of attack traffic with XDP), load balancing (Facebook Katran), fast packet forwarding, traffic steering to specific CPU queues.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Benchmarks show XDP can process 20–40+ million packets per second per core, versus 1–2 million for userspace packet processing via AF_PACKET.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Networking — TC (Traffic Control)") {
                    BodyText("TC eBPF programs attach to the kernel's Traffic Control layer — after sk_buff allocation, at the point where tc (the Linux traffic control tool) would apply queueing disciplines and filters. TC programs can inspect and modify packet contents, including headers.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("TC programs can attach to both ingress and egress paths (unlike XDP which is ingress-only by default). They can:\n• Modify IP headers and TCP/UDP ports (NAT)\n• Mark packets for QoS scheduling\n• Redirect packets between interfaces\n• Drop malicious traffic that passed XDP")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock("# Attach a TC eBPF program to eth0 ingress:\ntc qdisc add dev eth0 clsact\ntc filter add dev eth0 ingress bpf obj prog.o \\\n    sec tc/ingress direct-action")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Cilium uses TC eBPF extensively — it implements container-to-container networking, network policy enforcement, and load balancing entirely through TC (and XDP) programs, bypassing iptables entirely for much higher performance.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Networking — Other Types") {
                    BodyText("Socket filter (BPF_PROG_TYPE_SOCKET_FILTER) — the original BPF use case. Attaches to a socket (via SO_ATTACH_BPF) and filters packets delivered to that socket. Still used by tcpdump and Wireshark.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("cgroup socket programs — attach to a cgroup and apply policy to all sockets created by processes in that cgroup. Used to implement per-container network policy, connect-time load balancing, and bandwidth limiting.")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock("// cgroup/connect4: redirect TCP connections\n// to a different IP at connect() time\nSEC(\"cgroup/connect4\")\nint redirect_connect(struct bpf_sock_addr *ctx)\n{\n    if (ctx->user_port == bpf_htons(80)) {\n        ctx->user_ip4 = bpf_htonl(BACKEND_IP);\n        ctx->user_port = bpf_htons(8080);\n    }\n    return 1; // allow\n}")
                    Spacer(Modifier.height(8.dp))
                    BodyText("sk_msg programs — intercept data as it is sent through a socket pair (sockmap), allowing in-kernel message inspection or redirection without copying data to user space. Used for zero-copy service mesh proxying.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Security — LSM Hooks") {
                    BodyText("LSM (Linux Security Module) is the kernel's security hook framework. Traditional LSMs like SELinux and AppArmor implement mandatory access control by hooking into ~240 security check points throughout the kernel (file open, socket connect, exec, etc.).")
                    Spacer(Modifier.height(8.dp))
                    BodyText("BPF LSM (KRSI — Kernel Runtime Security Instrumentation), added in Linux 5.7, allows eBPF programs to attach to any LSM hook. This brings the power of kernel security policy enforcement to eBPF: you can write custom security policies in C, load them at runtime without rebooting, and update them without touching SELinux policy files.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("An eBPF LSM program attaches to hooks like:")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock("lsm/file_open         // before a file is opened\nlsm/bprm_check_security // before exec()\nlsm/socket_connect    // before TCP/UDP connect()\nlsm/task_kill         // before sending a signal\nlsm/sb_mount          // before mounting a filesystem")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Example: block any process named \"malware\" from opening files in /etc:")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock("SEC(\"lsm/file_open\")\nint BPF_PROG(restrict_file_open, struct file *file)\n{\n    char comm[16];\n    bpf_get_current_comm(comm, sizeof(comm));\n    if (__builtin_memcmp(comm, \"malware\", 7) == 0)\n        return -EPERM; // deny\n    return 0; // allow\n}")
                    Spacer(Modifier.height(8.dp))
                    BodyText("BPF LSM programs stack with existing LSMs — SELinux still runs, and eBPF LSM can add additional restrictions on top. This makes it possible to deploy runtime security policies without replacing the existing security infrastructure.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Falco (container security) uses this mechanism to detect suspicious behavior inside containers at kernel speed, without the overhead of traditional ptrace-based monitoring.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Program Types at a Glance") {
                    CodeBlock(
                        "Type              | Attach point        | Use case\n" +
                        "------------------|---------------------|--------------------\n" +
                        "kprobe/kretprobe  | Any kernel func     | Dynamic tracing\n" +
                        "tracepoint        | Static kernel hooks | Stable tracing\n" +
                        "uprobe/uretprobe  | User-space funcs    | App tracing\n" +
                        "USDT              | User-space probes   | Runtime tracing\n" +
                        "perf_event        | HW/SW perf counters | Profiling/sampling\n" +
                        "XDP               | NIC driver (RX)     | Fast packet proc\n" +
                        "TC                | Traffic control     | Packet modify/NAT\n" +
                        "socket_filter     | Socket recv         | Packet filtering\n" +
                        "cgroup/sock*      | cgroup sockets      | Per-container net\n" +
                        "sk_msg            | Socket send path    | Zero-copy proxy\n" +
                        "lsm/*             | LSM security hooks  | Runtime security\n" +
                        "sched_ext         | CPU scheduler       | Custom scheduling"
                    )
                }
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}
