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
fun EbpfSecurityScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "eBPF — Security Possibilities",
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
                SectionCard(title = "eBPF as a Security Platform") {
                    BodyText("eBPF can observe and enforce security policy at kernel speed — without modifying kernel source, loading kernel modules, or rebooting. It runs inside the kernel with verifier guarantees (no crashes, no infinite loops) and can be loaded and unloaded at runtime.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Three main mechanisms for security:")
                    CodeBlock(
                        """1. BPF LSM (Linux 5.7)
                            |   Attach BPF programs to LSM hooks to enforce MAC policy.
                            |   Can DENY operations by returning a non-zero error code.
                            |
                            |2. fentry / fexit (Linux 5.5)
                            |   Efficient BTF-based probes at kernel function entry/exit.
                            |   fexit also receives the function's return value.
                            |   Used for auditing, monitoring, and detection.
                            |
                            |3. kprobe + bpf_override_return
                            |   Override the return value of an allowlisted kernel function.
                            |   Used for fault injection and limited enforcement.""".trimMargin()
                    )
                }
            }
            item {
                SectionCard(title = "BPF LSM / KRSI (Linux 5.7)") {
                    BodyText("BPF programs can attach to any of the ~400 LSM (Linux Security Module) hooks — the same hooks used by SELinux and AppArmor. An attached BPF program can ALLOW or DENY the operation by returning 0 (allow) or a negative errno (deny, e.g. -EPERM).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("BPF LSM stacks on top of other LSMs — ALL must allow the operation for it to proceed. Requires CONFIG_BPF_LSM=y and \"bpf\" in the lsm= kernel boot parameter.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Example — deny exec of a specific process name:")
                    CodeBlock(
                        """SEC("lsm/bprm_check_security")
                            |int BPF_PROG(check_exec, struct linux_binprm *bprm) {
                            |    char comm[16];
                            |    bpf_get_current_comm(comm, sizeof(comm));
                            |
                            |    // Deny if the calling process is named "malware"
                            |    if (__builtin_memcmp(comm, "malware", 7) == 0)
                            |        return -EPERM;
                            |
                            |    return 0; // allow
                            |}""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Other useful LSM hooks:")
                    CodeBlock(
                        """lsm/socket_connect     — block outbound network connections
                            |lsm/file_open          — control file access
                            |lsm/task_kill          — intercept signals
                            |lsm/bpf                — control BPF program loading itself
                            |lsm/inode_rename       — intercept file renames""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("LSM programs must be loaded with BPF_F_SLEEPABLE for hooks that run in task context (most of them). They require CAP_BPF.")
                }
            }
            item {
                SectionCard(title = "fentry / fexit (Linux 5.5)") {
                    BodyText("fentry and fexit are BTF-based function probes — more efficient than kprobes because they use direct call patching instead of breakpoint traps (int3). fentry fires at function entry, fexit fires after return and additionally receives the return value.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Performance comparison:")
                    CodeBlock(
                        """kprobe:        ~100-300ns overhead (int3 trap + single-step)
                            |fentry/fexit:  ~5-10ns overhead (direct call patching)""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("fentry/fexit are CO-RE compatible — they use BTF to resolve argument types, so you get named, typed arguments instead of raw pt_regs fields.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Example — trace TCP connections with fexit (includes return value):")
                    CodeBlock(
                        """// fexit fires AFTER tcp_connect returns.
                            |// The last argument 'ret' is the function's return value.
                            |SEC("fexit/tcp_connect")
                            |int BPF_PROG(trace_tcp_connect, struct sock *sk, int ret) {
                            |    if (ret == 0) {
                            |        // Connection succeeded — log destination IP
                            |        __u32 daddr = BPF_CORE_READ(sk,
                            |            __sk_common.skc_daddr);
                            |        bpf_printk("TCP connect to %x\n", daddr);
                            |    }
                            |    return 0;
                            |}
                            |
                            |// fentry example — fires at function entry
                            |SEC("fentry/vfs_read")
                            |int BPF_PROG(trace_vfs_read, struct file *file,
                            |             char *buf, size_t count, loff_t *pos) {
                            |    bpf_printk("vfs_read: count=%zu\n", count);
                            |    return 0;
                            |}""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("fentry/fexit cannot deny the operation (they run after the call setup or after return). Use BPF LSM for enforcement; use fentry/fexit for auditing and detection.")
                }
            }
            item {
                SectionCard(title = "bpf_override_return — Error Injection") {
                    BodyText("bpf_override_return() lets a kprobe BPF program override the return value of the probed function — effectively short-circuiting the call and returning a custom error code. This is primarily used for fault injection testing.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Requirements:")
                    CodeBlock(
                        """- CONFIG_BPF_KPROBE_OVERRIDE=y must be set in kernel config
                            |
                            |- The target function must be annotated with
                            |  ALLOW_ERROR_INJECTION() in the kernel source.
                            |  Only a limited set of functions are allowlisted
                            |  (e.g. __sys_recvmsg, __sys_sendmsg, various
                            |  security_* hooks, io_uring paths).
                            |
                            |- Must be used from a kprobe (not kretprobe, not fentry).""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Example — force EPERM on recvmsg:")
                    CodeBlock(
                        """SEC("kprobe/__sys_recvmsg")
                            |int deny_recv(struct pt_regs *ctx) {
                            |    // Override the return value — the real function
                            |    // body is skipped entirely.
                            |    bpf_override_return(ctx, -EPERM);
                            |    return 0;
                            |}""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Important limitation: bpf_override_return only works on the small set of allowlisted functions. It is NOT a general-purpose call-blocking mechanism. For blocking arbitrary kernel operations, use BPF LSM hooks instead.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Primary use case: chaos engineering and fault injection — simulate ENOMEM, EPERM, EIO, etc. to test application resilience without requiring kernel modifications.")
                }
            }
            item {
                SectionCard(title = "Syscall Monitoring with Tracepoints") {
                    BodyText("The syscalls/sys_enter_* and sys_exit_* tracepoints let you audit every syscall in real time with zero kernel modification. Each syscall has a corresponding tracepoint that provides the arguments in a typed struct.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Combined with ring buffers and pid/uid filtering, this is the foundation of runtime security monitoring (and what tools like Falco use internally):")
                    CodeBlock(
                        """SEC("tracepoint/syscalls/sys_enter_openat")
                            |int trace_openat(struct trace_event_raw_sys_enter *ctx) {
                            |    char fname[256];
                            |    // ctx->args[0] = dfd, ctx->args[1] = filename ptr
                            |    bpf_probe_read_user_str(fname, sizeof(fname),
                            |                            (void *)ctx->args[1]);
                            |    __u64 pid_tgid = bpf_get_current_pid_tgid();
                            |    __u32 pid = (__u32)(pid_tgid >> 32);
                            |    bpf_printk("openat: pid=%u file=%s\n", pid, fname);
                            |    return 0;
                            |}""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("For production use, replace bpf_printk with a BPF ring buffer (BPF_MAP_TYPE_RINGBUF) to efficiently stream events to userspace without going through the trace pipe.")
                }
            }
            item {
                SectionCard(title = "Real-World Security Tools") {
                    BodyText("All major Linux security tools have adopted eBPF. They run entirely in kernel space — no kernel modules, no reboot required:")
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """Falco (CNCF)
                            |  Syscall + Kubernetes audit rules engine.
                            |  Uses kprobes/tracepoints. Detects anomalous behavior
                            |  (unexpected file access, privilege escalation, etc.)
                            |  Very popular in container security.
                            |
                            |Tetragon (Cilium project)
                            |  fentry/fexit + BPF LSM. Can enforce policy with
                            |  bpf_send_signal() to kill offending processes in real time.
                            |  Deep Kubernetes integration via Hubble.
                            |
                            |Cilium / Hubble
                            |  XDP + TC for network policy enforcement (L3/L4/L7).
                            |  Hubble provides network flow visibility and security
                            |  monitoring for Kubernetes clusters.
                            |
                            |Tracee (Aqua Security)
                            |  Tracepoint-based runtime security for containers.
                            |  Detects CVE-level exploit patterns from kernel event streams.
                            |
                            |KubeArmor
                            |  BPF LSM-based policy enforcement for containers and VMs.
                            |  Whitelisting approach — define what is allowed, block rest.""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The common pattern: BPF programs collect kernel events into ring buffers, userspace daemons consume and evaluate them against security policy, and enforcement is either done in-kernel (BPF LSM / bpf_send_signal) or via userspace response.")
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
