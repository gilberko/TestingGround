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
fun EbpfHistoryScreen(onBack: () -> Unit) {
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "eBPF History",
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
                SectionCard(title = "Classic BPF (1992)") {
                    BodyText("BPF stands for Berkeley Packet Filter. It was invented in 1992 by Steven McCanne and Van Jacobson at Lawrence Berkeley National Laboratory. The problem they were solving: capturing network packets efficiently for tools like tcpdump.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Before BPF, packet capture was done by copying every packet from the kernel to user space, then filtering them there. This was enormously wasteful — a program watching for ICMP packets would receive and throw away every TCP and UDP packet too.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("BPF's insight: run a small filter program inside the kernel, before the copy. Only packets that match the filter get copied to user space. The filter runs in a tiny virtual machine (VM) — a safe, sandboxed interpreter inside the kernel.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("The classic BPF VM was deliberately minimal:\n• 2 registers (accumulator A, index register X)\n• 32-bit words only\n• A small scratch memory area\n• A fixed instruction set for packet field access and comparisons\n• No loops allowed — programs always terminate")
                    Spacer(Modifier.height(8.dp))
                    BodyText("When you run tcpdump with a filter like \"tcp port 80\", the filter expression is compiled to BPF bytecode and loaded into the kernel. The kernel runs the bytecode against each arriving packet to decide whether to hand it to tcpdump.")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock("# tcpdump compiles this filter to BPF bytecode:\ntcpdump -i eth0 'tcp port 80'\n\n# To see the generated BPF instructions:\ntcpdump -i eth0 -d 'tcp port 80'")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Why BPF Needed Extending") {
                    BodyText("Classic BPF served packet filtering well for 20 years, but by the early 2010s its limitations were becoming painful:")
                    Spacer(Modifier.height(8.dp))
                    BodyText("• 32-bit only — modern CPUs are 64-bit and can do much more per instruction\n• Only 2 registers — a severe constraint for complex programs\n• No persistent state — the VM had no way to store data between packets or share data with user space\n• Packet filtering only — the VM had no way to interact with the kernel beyond reading packet bytes\n• No JIT on many architectures — the interpreter was slow compared to native code")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Several kernel subsystems had built their own ad-hoc in-kernel programmability (seccomp, kernel probes, etc.) but they were all separate, each with their own safety model and limitations. There was no unified approach.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "eBPF Is Born (Linux 3.18, 2014)") {
                    BodyText("Alexei Starovoitov at Facebook and Daniel Borkmann at Red Hat designed extended BPF (eBPF) and merged it into Linux 3.18 in late 2014. The 'e' stands for extended, but the scope of the extension was enormous — it was essentially a new system.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Key improvements over classic BPF:")
                    Spacer(Modifier.height(8.dp))
                    BodyText("• 11 registers (64-bit) instead of 2 — enough for real programs\n• A proper register-based ISA similar to x86-64\n• BPF maps — persistent key-value stores in the kernel, accessible from both eBPF programs and user space\n• A rich set of helper functions — safe, approved ways to call kernel functionality\n• A powerful in-kernel verifier — static analysis to prove programs are safe before they run\n• JIT compilation on x86-64, ARM64, and other architectures — near-native speed\n• Attach points beyond packet filtering — kprobes, tracepoints, cgroup hooks, and more")
                    Spacer(Modifier.height(8.dp))
                    BodyText("The verifier is what makes eBPF unique. Before any eBPF program runs, the kernel's verifier performs a full static analysis, checking every possible execution path. Programs that could crash the kernel, access invalid memory, or loop forever are rejected before they ever execute.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Beyond Networking") {
                    BodyText("Early eBPF was still network-focused, but the combination of the safe verifier, fast JIT, and flexible attach points quickly attracted attention from other parts of the kernel community.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Brendan Gregg at Netflix (and later Intel) became a major evangelist for using eBPF for performance analysis and observability. He built tools that could trace kernel functions, measure latency distributions, and profile CPU usage with near-zero overhead — things that previously required modifying kernel source code or using slow, intrusive tools.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("The tooling ecosystem exploded:\n• bcc (BPF Compiler Collection) — Python/Lua front end for writing eBPF programs\n• bpftrace — a high-level tracing language (like awk but for the kernel)\n• libbpf — a low-level C library for loading and managing eBPF programs\n• BTF (BPF Type Format) — allows eBPF programs to be portable across kernel versions")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Security tools like Falco (container runtime security) adopted eBPF to monitor system calls without the overhead of traditional kernel modules.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "eBPF Today") {
                    BodyText("eBPF has become infrastructure-critical technology. Major companies have built entire products on it:")
                    Spacer(Modifier.height(8.dp))
                    BodyText("• Cilium — Kubernetes networking and security layer built entirely on eBPF (used by Google, AWS, and many others)\n• Falco — cloud-native runtime security using eBPF syscall tracing\n• Pixie — Kubernetes observability with automatic eBPF-based profiling\n• Katran — Facebook/Meta's load balancer, handling billions of connections per day with XDP eBPF\n• Cloudflare's DDoS mitigation — drops malicious packets at line rate with XDP")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Microsoft has begun an eBPF for Windows project, bringing the same programmability model to Windows. The Linux Foundation created the eBPF Foundation to oversee the ecosystem.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("eBPF is no longer just about filtering packets. It is a general-purpose kernel extension platform — a way to safely run custom code in the kernel without writing kernel modules, waiting for patches to be accepted, or rebooting the system.")
                }
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}
