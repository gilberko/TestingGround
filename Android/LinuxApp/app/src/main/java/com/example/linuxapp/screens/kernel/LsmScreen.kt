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
fun LsmScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "LSM — Linux Security Modules",
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
                SectionCard(title = "What Is an LSM?") {
                    BodyText("The Linux Security Module (LSM) framework is a kernel mechanism that allows security policies to be plugged into the kernel without modifying its core code. It was introduced in Linux 2.6 (2003), designed by the NSA (for SELinux) and Linus Torvalds collaborating on a general-purpose interface.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Without an LSM, Linux enforces only DAC — Discretionary Access Control: file permissions (rwxrwxrwx), ownership, and capabilities. DAC lets any root process do anything. LSMs add MAC — Mandatory Access Control — where a policy enforced by the kernel can restrict even root.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("LSM hooks intercept ~400 security-relevant operations, including:")
                    CodeBlock("""File operations:   open, read, write, execute, mmap
Process control:   fork, exec, ptrace, kill, setuid
Network:           socket create, connect, bind, sendmsg
IPC:               shmget, semctl, msgsnd
Capabilities:      capable() checks before privilege use
Kernel objects:    module load, BPF program load, perf events""")
                }
            }
            item {
                SectionCard(title = "How LSM Hooks Work") {
                    BodyText("At compile time, the kernel inserts calls to security_*() functions at each hook point. At runtime, every registered LSM's hook function is called in sequence:")
                    CodeBlock("""// Inside the kernel (simplified):
int vfs_open(struct path *path, struct file *file)
{
    // DAC check first
    error = may_open(path, ...);

    // LSM hook: every registered LSM runs here
    error = security_file_open(file);
    if (error)
        return error;   // LSM denied it

    // proceed with open
}""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Each LSM hook returns 0 (allow) or a negative errno (deny). If any LSM denies the operation, it is blocked — the most restrictive policy wins. This is called the LSM stacking model (since Linux 4.2+, multiple LSMs can be active simultaneously).")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("With no LSM loaded, security_*() functions are no-ops that always return 0 — only DAC applies. LSMs add a policy layer on top without changing existing kernel logic.")
                }
            }
            item {
                SectionCard(title = "Loading & Initialization") {
                    BodyText("LSMs must be compiled into the kernel — they cannot be loaded as modules at runtime (with the exception of eBPF LSM). This is a deliberate security property: a loaded LSM is trusted as part of the kernel itself.")
                    CodeBlock("""Kernel config options (examples):
  CONFIG_SECURITY_SELINUX=y    — compile SELinux in
  CONFIG_SECURITY_APPARMOR=y   — compile AppArmor in
  CONFIG_SECURITY_SMACK=y      — compile Smack in

Boot parameter to select which LSMs are active:
  lsm=lockdown,capability,selinux
  lsm=apparmor,bpf

Check active LSMs on a running system:
  cat /sys/kernel/security/lsm""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("LSM stacking (Linux 4.2+): multiple LSMs can run together. Some are always present as implicit minor LSMs — capability (POSIX capabilities), lockdown (restrict root from touching integrity), and yama (ptrace scope restrictions).")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("The order in the lsm= boot parameter matters: hooks are called in registration order. The first to deny wins.")
                }
            }
            item {
                SectionCard(title = "SELinux") {
                    BodyText("SELinux (Security-Enhanced Linux) was developed by the NSA and merged in Linux 2.6. It is the default LSM on Red Hat/Fedora/CentOS systems and is baked into Android's security model.")
                    CodeBlock("""Model: Mandatory Access Control using labels (security contexts)
Every object has a label: user:role:type:level
  e.g.  system_u:object_r:httpd_exec_t:s0

Policy rules define allowed transitions:
  allow httpd_t httpd_log_t : file { write append };
  (httpd process may write to httpd log files)""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Denials appear in the kernel log:")
                    CodeBlock("""avc: denied { read } for pid=1234 comm="nginx"
     path="/etc/shadow" dev="sda1" ino=12345
     scontext=system_u:system_r:httpd_t:s0
     tcontext=system_u:object_r:shadow_t:s0
     tclass=file""")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("SELinux can run in enforcing (deny + log), permissive (log only), or disabled mode. Policy files live in /etc/selinux/. Writing policy is complex but highly expressive — it can confine every process to exactly the resources it needs.")
                }
            }
            item {
                SectionCard(title = "AppArmor") {
                    BodyText("AppArmor is the default LSM on Ubuntu, Debian, and openSUSE. It uses path-based profiles rather than labels, making it significantly easier to write and understand than SELinux.")
                    CodeBlock("""# /etc/apparmor.d/usr.sbin.nginx
/usr/sbin/nginx {
    /var/log/nginx/*.log  rw,
    /etc/nginx/**         r,
    /run/nginx.pid        rw,
    network tcp,
    deny /etc/shadow      r,
}""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("AppArmor has a learning mode: run the app normally, AppArmor logs all accesses, then generate a policy from the log. Profiles are stored in /etc/apparmor.d/ and loaded with apparmor_parser.")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Limitation: path-based policies can be bypassed by hard links or bind mounts in some configurations. SELinux's label approach is more robust but harder to manage.")
                }
            }
            item {
                SectionCard(title = "Other LSMs: Smack & TOMOYO") {
                    BodyText("Smack (Simplified Mandatory Access Control Kernel): label-based like SELinux but with much simpler rules. Labels are plain strings; access is allowed only if the subject label can access the object label per a small ruleset. Used in Tizen (Samsung's embedded OS) and some automotive Linux.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("TOMOYO: a path-based LSM from Japan with a learning mode similar to AppArmor. It builds a whitelist of observed behavior during a training phase. Once enforcing, any operation not seen during training is denied. Useful for locking down embedded systems.")
                    CodeBlock("""TOMOYO learning mode:
  1. Run app normally — TOMOYO records all syscall patterns
  2. Switch to enforcing
  3. Any deviation from learned behavior → denied

Smack label example:
  File labeled "secret"
  Process labeled "trusted"
  Rule: trusted can read secret
  Process labeled "untrusted" → denied, no rule exists""")
                }
            }
            item {
                SectionCard(title = "eBPF LSM — The New Frontier (Linux 5.7, 2020)") {
                    BodyText("eBPF LSM was merged in Linux 5.7 (March 2020). It allows BPF programs to attach to LSM hook points — giving you fully programmable security policy without recompiling the kernel, without loading a kernel module, and without rebooting.")
                    CodeBlock("""// BPF LSM program (C, compiled with clang/libbpf)
SEC("lsm/file_open")
int BPF_PROG(restrict_open, struct file *file)
{
    // Deny opening any file in /etc/secret/
    char path[256];
    bpf_d_path(&file->f_path, path, sizeof(path));
    if (starts_with(path, "/etc/secret/"))
        return -EPERM;   // deny
    return 0;            // allow
}""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Key characteristics:")
                    CodeBlock("""BPF_PROG_TYPE_LSM  — program type for LSM hooks
Attach type: BPF_LSM_MAC — must be in lsm= boot list (or bpf)
Loaded at runtime with bpf(BPF_PROG_LOAD, ...)
Can be attached and detached without reboot
Verified by the BPF verifier for safety (no crashes, no loops)""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("eBPF LSM use cases:")
                    CodeBlock("""Custom audit logging:  log only the events you care about
Per-container policy:  different policy per container ID
Runtime policy:        update security rules without rebooting
Observability + enforcement: combine bpf maps with policy
Threat detection:      detect anomalous behavior in real time""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("eBPF LSM can coexist with SELinux or AppArmor (LSM stacking). It is the most flexible option: traditional LSMs define policy at boot; eBPF LSM lets you define and update policy while the system is running. Tools: libbpf, bpftool, BCC, Cilium Tetragon.")
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
