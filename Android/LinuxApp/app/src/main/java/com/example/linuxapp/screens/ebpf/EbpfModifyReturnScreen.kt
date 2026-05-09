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
fun EbpfModifyReturnScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Modify Return Value (fmod_ret)",
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
                SectionCard(title = "What Is fmod_ret?") {
                    BodyText("fmod_ret stands for \"function modify return\". It is an eBPF program attach type introduced in Linux 5.7 that can conditionally short-circuit a kernel function and replace its return value.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Program type: BPF_PROG_TYPE_TRACING")
                    BodyText("Attach type: BPF_MODIFY_RETURN")
                    BodyText("SEC macro: SEC(\"fmod_ret/kernel_function_name\")")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Unlike kprobe-based bpf_override_return, fmod_ret uses the BPF trampoline mechanism — the same infrastructure used by fentry and fexit. This means it attaches via inline patching (no int3 exception), giving it overhead of approximately 5–10 ns per call rather than the 100–300 ns of a kprobe.")
                }
            }
            item {
                SectionCard(title = "What Functions Can I Use It On?") {
                    BodyText("fmod_ret is eligible for any function that supports BPF trampolines — the same set as fentry/fexit:")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("• Must have BTF (BPF Type Format) type information available.")
                    BodyText("• Must not be inlined — inlined functions have no single call site.")
                    BodyText("• Must not be in the trampoline blacklist (same as fentry/fexit).")
                    BodyText("• Must not be marked __no_kprobes.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Critically: the target does NOT need ALLOW_ERROR_INJECTION(). This is a major advantage over bpf_override_return, which is limited to a small set of explicitly allowlisted functions.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The practical way to check eligibility is to attempt to load the program. The BPF verifier rejects the load if the function is ineligible, with a clear error message.")
                }
            }
            item {
                SectionCard(title = "How It Decides to Modify or Pass Through") {
                    BodyText("The BPF program runs before the original kernel function. Its return value determines what happens next:")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("• Return 0 → the original function executes normally. Its return value is used by the caller.")
                    BodyText("• Return non-zero → the original function is skipped entirely. The BPF program's return value becomes the function's return value as seen by the caller.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("When multiple fmod_ret programs are attached to the same function, they run in chain order. If any program returns non-zero, the original function is still skipped. Each program in the chain receives the original arguments regardless.")
                }
            }
            item {
                SectionCard(title = "Can I Run the Original and Then Change Its Return Value?") {
                    BodyText("No — not with fmod_ret alone. fmod_ret runs before the original function and can only replace or pass through. It does not see the original function's return value.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("fexit programs run after the original function and receive the return value as an argument — but fexit cannot modify it (read-only).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("To override a return value after execution, use a kretprobe from a kernel module with regs_set_return_value(ri->regs, newval). There is no single eBPF program type that both allows the original to run and then changes its return value.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The right tool by goal:")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("• Observe return value without modifying → fexit")
                    BodyText("• Conditionally skip and replace return value → fmod_ret")
                    BodyText("• Override return value after execution → kretprobe (kernel module)")
                }
            }
            item {
                SectionCard(title = "fmod_ret vs bpf_override_return") {
                    BodyText("Both achieve the goal of replacing a kernel function's return value, but through different mechanisms with different tradeoffs:")
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock("""
                        |Feature              fmod_ret        bpf_override_return
                        |------------------   -----------     -------------------
                        |Program type         BPF_TRACING     kprobe
                        |Needs               (none)          ALLOW_ERROR_INJECTION
                        |  annotation
                        |Mechanism            Trampoline      int3 + single-step
                        |Overhead             ~5-10 ns        ~100-300 ns
                        |Skip original        Yes (non-zero)  Yes (overrides ret)
                        |BTF required         Yes             No
                        |Function scope       Any trampoline- Allowlisted only
                        |                     eligible fn
                    """.trimMargin())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("fmod_ret is the preferred modern approach when you need to conditionally block or replace a function's behaviour. bpf_override_return is older and limited to a small allowlisted set, but does not require BTF.")
                }
            }
            item {
                SectionCard(title = "Can I View Eligible Functions at Runtime?") {
                    BodyText("There is no dedicated fmod_ret eligible-functions list exposed by the kernel. Functions eligible for fentry/fexit are also eligible for fmod_ret, so the same eligibility rules apply.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Practical approach:")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("• Attempt to load the program — the verifier rejects ineligible targets with a clear error.")
                    BodyText("• List currently attached tracing programs:")
                    CodeBlock("""
                        |bpftool prog list
                        |# Shows type, attach point for all loaded BPF programs
                    """.trimMargin())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("• Inspect BTF type info to verify a function is present:")
                    CodeBlock("""
                        |bpftool btf dump file /sys/kernel/btf/vmlinux \
                        |    format raw | grep security_file_open
                    """.trimMargin())
                }
            }
            item {
                SectionCard(title = "Full Example — Selectively Block File Open") {
                    BodyText("Attach fmod_ret to security_file_open. If the opened path is /etc/shadow, return -EPERM and skip the original. All other opens proceed normally.")
                    CodeBlock("""
                        |// block_shadow.bpf.c
                        |#include <vmlinux.h>
                        |#include <bpf/bpf_helpers.h>
                        |#include <bpf/bpf_tracing.h>
                        |#include <bpf/bpf_core_read.h>
                        |
                        |SEC("fmod_ret/security_file_open")
                        |int BPF_PROG(block_shadow_open,
                        |             struct file *file)
                        |{
                        |    char path[32] = {};
                        |    bpf_d_path(&file->f_path,
                        |               path, sizeof(path));
                        |
                        |    // Compare with "/etc/shadow"
                        |    const char target[] = "/etc/shadow";
                        |    for (int i = 0;
                        |         i < sizeof(target) - 1; i++) {
                        |        if (path[i] != target[i])
                        |            return 0; // pass through
                        |    }
                        |    return -1; // -EPERM: skip original
                        |}
                        |
                        |char LICENSE[] SEC("license") = "GPL";
                    """.trimMargin())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Build and load:")
                    CodeBlock("""
                        |clang -O2 -target bpf -g \
                        |    -c block_shadow.bpf.c \
                        |    -o block_shadow.bpf.o
                        |bpftool prog load block_shadow.bpf.o \
                        |    /sys/fs/bpf/block_shadow
                        |
                        |# Unload when done:
                        |rm /sys/fs/bpf/block_shadow
                    """.trimMargin())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("No ALLOW_ERROR_INJECTION annotation is required on security_file_open. This would be rejected by bpf_override_return since security_file_open is not on the error injection allowlist.")
                }
            }
            item {
                SectionCard(title = "Conditional Injection via Maps") {
                    BodyText("Use a BPF map as a runtime on/off switch. Toggle the injection from user space without reloading the program:")
                    CodeBlock("""
                        |// inject_oom.bpf.c
                        |#include <vmlinux.h>
                        |#include <bpf/bpf_helpers.h>
                        |#include <bpf/bpf_tracing.h>
                        |
                        |struct {
                        |    __uint(type, BPF_MAP_TYPE_ARRAY);
                        |    __uint(max_entries, 1);
                        |    __type(key, __u32);
                        |    __type(value, __u64);
                        |} ctrl_map SEC(".maps");
                        |
                        |SEC("fmod_ret/kmem_cache_alloc")
                        |int BPF_PROG(inject_oom, ...)
                        |{
                        |    __u32 key = 0;
                        |    __u64 *enabled =
                        |        bpf_map_lookup_elem(&ctrl_map, &key);
                        |
                        |    if (!enabled || !*enabled)
                        |        return 0;
                        |
                        |    // Only inject for a specific PID
                        |    __u32 pid =
                        |        bpf_get_current_pid_tgid() >> 32;
                        |    if (pid != TARGET_PID)
                        |        return 0;
                        |
                        |    return -12; // -ENOMEM
                        |}
                        |char LICENSE[] SEC("license") = "GPL";
                    """.trimMargin())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Toggle from user space:")
                    CodeBlock("""
                        |# Enable injection
                        |bpftool map update name ctrl_map \
                        |    key 0 0 0 0  value 1 0 0 0 0 0 0 0
                        |
                        |# Disable injection
                        |bpftool map update name ctrl_map \
                        |    key 0 0 0 0  value 0 0 0 0 0 0 0 0
                    """.trimMargin())
                }
            }
            item {
                SectionCard(title = "Implementation — BPF Trampolines") {
                    BodyText("fmod_ret is built on the BPF trampoline mechanism. When you load an fmod_ret program, the kernel generates a small architecture-specific assembly stub (the trampoline) at runtime and installs it by patching the call site of the target function.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The trampoline does:")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("1. Save all caller-saved registers.")
                    BodyText("2. Call the BPF program (JIT-compiled native code).")
                    BodyText("3. Check the return value.")
                    BodyText("4a. If zero → restore registers and call the original function normally.")
                    BodyText("4b. If non-zero → restore registers and return directly to the caller with the BPF return value (skipping the original).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("No int3 exception is involved. The overhead is dominated by the register save/restore and the JIT-compiled BPF program execution — roughly 5–10 ns total.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Platform support: x86-64 (full), ARM64 (full), s390 (partial). Not available on 32-bit x86 or older architectures. Available since Linux 5.7.")
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
