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
fun WhatIsEbpfScreen(onBack: () -> Unit) {
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "What is eBPF",
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
                SectionCard(title = "The eBPF Virtual Machine") {
                    BodyText("eBPF lets you run custom programs inside the Linux kernel without modifying kernel source code and without writing a kernel module. The programs run in a sandboxed virtual machine (VM) inside the kernel.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("The development and execution flow:")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock("1. Write eBPF program in C (restricted subset)\n2. Compile with clang/LLVM → BPF bytecode (.o ELF file)\n3. Load into kernel via bpf() syscall\n4. Kernel verifier checks the program\n5. If safe: JIT-compile to native machine code\n6. Attach to a hook point (kprobe, tracepoint, XDP...)\n7. Program runs every time the hook fires")
                    Spacer(Modifier.height(8.dp))
                    BodyText("The eBPF VM has 11 general-purpose 64-bit registers (r0–r10), a 512-byte stack, and a pointer to the context (the event data, e.g., a packet or a syscall argument). r10 is the read-only frame pointer; r0 holds the return value.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("eBPF programs run in kernel context, meaning they have the speed and visibility of kernel code. But unlike kernel modules, they cannot directly call arbitrary kernel functions or dereference arbitrary pointers — the verifier enforces these boundaries.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "The Verifier") {
                    BodyText("The verifier is the safety heart of eBPF. Before a program runs even once, the kernel performs a complete static analysis of every possible execution path through the program.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("What the verifier checks:")
                    Spacer(Modifier.height(8.dp))
                    BodyText("• Termination — the program must always terminate. No unbounded loops. The verifier tracks back-edges and rejects programs where it cannot prove all loops are bounded.\n• Memory safety — every pointer dereference must be checked. The verifier tracks pointer types and ranges. If you dereference a pointer without first checking it is non-NULL and within bounds, the program is rejected.\n• Register types — the verifier tracks what type of value is in each register (pointer to map, pointer to packet, scalar, etc.) and rejects operations that would violate type safety.\n• Uninitialized reads — reading from a register before writing to it is rejected.\n• Stack bounds — stack accesses must be within the 512-byte limit.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("The verifier traverses the program as a DAG (Directed Acyclic Graph). If the program has branches, it checks all paths. This makes verification time proportional to the number of paths, which is why excessively complex programs can hit verifier limits.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("If the verifier rejects your program, it outputs a log explaining exactly which instruction failed and why — very helpful for debugging.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Program Limitations") {
                    BodyText("eBPF programs are safe but not unlimited. Key constraints:")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Program size:\nOriginally capped at 4,096 instructions. Linux 5.2 (2019) raised this to approximately 1 million instructions for privileged programs (BPF_F_ANY_ALIGNMENT and trusted programs).")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Loops:\nUnbounded loops were rejected until Linux 5.3 (2019), which added support for bounded loops — loops where the verifier can statically prove termination. Truly infinite loops are still rejected.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Stack size:\nThe stack is fixed at 512 bytes. eBPF programs cannot use dynamic stack allocation. For larger data, use BPF maps.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Pointer arithmetic:\nYou cannot perform arbitrary pointer arithmetic. The verifier tracks pointer ranges, and arithmetic that could produce out-of-bounds pointers is rejected. You cannot cast integers to pointers.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Kernel function calls:\nYou cannot call arbitrary kernel functions. You can only call approved BPF helper functions (listed in uapi/linux/bpf.h) and, on newer kernels, a small set of kfuncs (kernel functions explicitly exported for eBPF use).")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Sleeping:\nMost eBPF program types run in non-sleepable contexts (interrupt or RCU-critical sections). They cannot sleep, allocate memory with GFP_KERNEL, or take sleeping locks. Newer sleepable program types (FENTRY with BPF_F_SLEEPABLE) exist for specific attach points.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Privilege:\nLoading most eBPF programs requires CAP_BPF (or CAP_SYS_ADMIN on older kernels). Unprivileged eBPF is disabled by default on most distributions due to past speculative-execution vulnerabilities.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "BPF Maps") {
                    BodyText("BPF maps are the primary data structure in eBPF. They are kernel-resident key-value stores that can be accessed from both eBPF programs (at hook time) and user-space programs (via the bpf() syscall). This is how eBPF programs communicate with the outside world.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Common map types:")
                    Spacer(Modifier.height(8.dp))
                    BodyText("• BPF_MAP_TYPE_HASH — hash table, O(1) average lookup\n• BPF_MAP_TYPE_ARRAY — integer-indexed array, fast for indexed access\n• BPF_MAP_TYPE_RINGBUF — ring buffer for efficiently passing events to user space (preferred over perf events on newer kernels)\n• BPF_MAP_TYPE_PERF_EVENT_ARRAY — sends events to user space via perf\n• BPF_MAP_TYPE_LRU_HASH — hash table that evicts least-recently-used entries\n• BPF_MAP_TYPE_LPM_TRIE — longest-prefix-match trie (useful for routing tables)\n• BPF_MAP_TYPE_PROG_ARRAY — array of eBPF program references for tail calls")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock("// eBPF side: count packets per source IP\nstruct {\n    __uint(type, BPF_MAP_TYPE_HASH);\n    __uint(max_entries, 1024);\n    __type(key, __u32);   // source IP\n    __type(value, __u64); // packet count\n} pkt_count SEC(\".maps\");\n\n// In the eBPF program:\n__u32 src_ip = ...;\n__u64 *count = bpf_map_lookup_elem(&pkt_count, &src_ip);\nif (count)\n    __sync_fetch_and_add(count, 1);")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Maps are identified by file descriptors. They can be pinned to the BPF filesystem (/sys/fs/bpf/) so they persist after the program that created them exits.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Helper Functions") {
                    BodyText("eBPF programs cannot call arbitrary kernel functions. Instead, they call BPF helper functions — a curated set of approved functions that the kernel exposes to eBPF programs. Each helper has a well-defined prototype and is safe to call from eBPF context.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Commonly used helpers:")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock("bpf_map_lookup_elem(map, key)       // look up in a map\nbpf_map_update_elem(map, key, val)  // insert/update map entry\nbpf_map_delete_elem(map, key)       // delete map entry\n\nbpf_probe_read_kernel(dst, size, src) // safely read kernel memory\nbpf_probe_read_user(dst, size, src)   // safely read user memory\n\nbpf_get_current_pid_tgid()  // returns (tgid << 32 | pid)\nbpf_get_current_uid_gid()   // returns (gid << 32 | uid)\nbpf_get_current_comm(buf, sz) // copy current process name\n\nbpf_ktime_get_ns()          // nanosecond timestamp\n\nbpf_perf_event_output(ctx, map, flags, data, size)\n    // send event to user space via perf ring buffer\n\nbpf_ringbuf_output(map, data, size, flags)\n    // send event via BPF ring buffer (newer, preferred)\n\nbpf_trace_printk(fmt, fmt_size, ...) // debug: write to trace_pipe")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Not all helpers are available to all program types. The verifier enforces that programs only call helpers appropriate to their attach context.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Newer kernels also support kfuncs — kernel functions explicitly annotated and exported for eBPF use, allowing more direct access to kernel internals in a controlled way.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "JIT Compilation") {
                    BodyText("After passing the verifier, eBPF bytecode is JIT-compiled to native machine code. This eliminates interpretation overhead and makes eBPF programs run at near-native kernel speed.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("JIT is supported on: x86-64, ARM64, ARM32, MIPS, PowerPC, s390, RISC-V, and others. On architectures without a JIT, programs fall back to the interpreter (slower).")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock("# Check if JIT is enabled:\ncat /proc/sys/net/core/bpf_jit_enable\n# 0 = disabled (interpreter)\n# 1 = enabled (JIT)\n# 2 = enabled + dump JIT code to kernel log\n\n# Enable JIT:\nsysctl -w net.core.bpf_jit_enable=1")
                    Spacer(Modifier.height(8.dp))
                    BodyText("The JIT compiler performs optimizations like constant folding and dead code elimination. For XDP programs doing DDoS mitigation or packet forwarding, JIT-compiled eBPF can process packets at line rate (tens of millions per second) with very low CPU overhead.")
                }
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}
