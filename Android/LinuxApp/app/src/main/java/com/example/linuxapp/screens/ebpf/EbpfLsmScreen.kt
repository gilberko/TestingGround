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
fun EbpfLsmScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "eBPF LSM (KRSI)",
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
                SectionCard(title = "What Is eBPF LSM (KRSI)") {
                    BodyText("KRSI — Kernel Runtime Security Instrumentation — was introduced in Linux 5.7. It lets you attach eBPF programs to any of the ~400 Linux Security Module (LSM) hooks at runtime, without rebooting or recompiling the kernel.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("LSM hooks are call sites the kernel inserts at security-sensitive operations (process exec, file open, network connect, signal delivery, mount, etc.). Traditionally only in-kernel LSMs like SELinux and AppArmor could attach to them. With eBPF LSM, a BPF program can too.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("eBPF LSM stacks on top of existing LSMs. All LSMs in the stack are called for each operation. If any returns a denial, the operation is blocked. The order is: capabilities → SELinux/AppArmor → eBPF LSM (last in stack).")
                    Spacer(Modifier.height(8.dp))
                    BodyText("A program returns 0 to allow and a negative errno (typically -EPERM) to deny.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Enabling eBPF LSM") {
                    BodyText("Kernel config requirements:")
                    CodeBlock(
                        "CONFIG_BPF_LSM=y\n" +
                        "CONFIG_LSM=\"...,bpf\"   # 'bpf' must appear in this list"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Check if eBPF LSM is active on a running system:")
                    CodeBlock(
                        "cat /sys/kernel/security/lsm\n" +
                        "# Should contain 'bpf' in the output\n" +
                        "# Example: lockdown,capability,yama,apparmor,bpf"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("If 'bpf' is missing, add it via the kernel boot parameter:")
                    CodeBlock(
                        "# Edit /etc/default/grub:\n" +
                        "GRUB_CMDLINE_LINUX=\"lsm=lockdown,capability,yama,apparmor,bpf\"\n" +
                        "\n" +
                        "# Then regenerate grub config and reboot:\n" +
                        "sudo update-grub\n" +
                        "sudo reboot"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Loading an eBPF LSM program requires CAP_MAC_ADMIN in addition to CAP_BPF. Most modern distributions (Ubuntu 22.04+, Fedora 35+) ship with eBPF LSM enabled by default.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "How It Works") {
                    BodyText("Use the SEC(\"lsm/hook_name\") annotation and the BPF_PROG() macro from libbpf for clean argument access:")
                    CodeBlock(
                        "#include <vmlinux.h>\n" +
                        "#include <bpf/bpf_helpers.h>\n" +
                        "#include <bpf/bpf_tracing.h>\n" +
                        "#include <bpf/bpf_core_read.h>\n" +
                        "\n" +
                        "// BPF_PROG(fn_name, arg1_type arg1, ...) unpacks\n" +
                        "// the hook arguments from the BPF context struct.\n" +
                        "SEC(\"lsm/file_open\")\n" +
                        "int BPF_PROG(my_file_open, struct file *file)\n" +
                        "{\n" +
                        "    // return 0  -> allow\n" +
                        "    // return -EPERM -> deny\n" +
                        "    return 0;\n" +
                        "}\n" +
                        "\n" +
                        "char LICENSE[] SEC(\"license\") = \"GPL\";"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("The BPF_PROG macro is required because LSM hook args are passed in a struct context, not as plain arguments. Without BPF_PROG you would have to extract them manually from ctx->args[].")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Load and attach with libbpf skeleton (generated by bpftool gen skeleton):")
                    CodeBlock(
                        "struct myprog_bpf *skel = myprog_bpf__open_and_load();\n" +
                        "myprog_bpf__attach(skel);  // attaches all SEC(\"lsm/...\") progs\n" +
                        "// ... keep running ...\n" +
                        "myprog_bpf__destroy(skel); // detach + unload on exit"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Common LSM Hooks") {
                    BodyText("A selection of the most useful hooks:")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock(
                        "bprm_check_security  // execve() — struct linux_binprm *bprm\n" +
                        "                     // bprm->filename = path being executed\n" +
                        "\n" +
                        "file_open            // any file open — struct file *file\n" +
                        "                     // file->f_path.dentry->d_name.name\n" +
                        "\n" +
                        "task_kill            // signal delivery\n" +
                        "                     // struct task_struct *p, int sig\n" +
                        "\n" +
                        "socket_connect       // outgoing TCP/UDP connect\n" +
                        "                     // struct socket *sock\n" +
                        "                     // struct sockaddr *address, int addrlen\n" +
                        "\n" +
                        "socket_bind          // bind() syscall\n" +
                        "                     // struct socket *sock\n" +
                        "                     // struct sockaddr *address\n" +
                        "\n" +
                        "inode_unlink         // file deletion\n" +
                        "                     // struct inode *dir\n" +
                        "                     // struct dentry *dentry\n" +
                        "\n" +
                        "sb_mount             // mount syscall\n" +
                        "                     // const char *dev_name\n" +
                        "                     // const struct path *path\n" +
                        "\n" +
                        "task_alloc           // new task creation (fork)\n" +
                        "cred_prepare         // privilege change"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("List all available LSM hooks on the running kernel:")
                    CodeBlock(
                        "bpftool btf dump file /sys/kernel/btf/vmlinux \\\n" +
                        "  format raw | grep lsm_hooks\n" +
                        "# Or search for DEFINE_LSM in kernel source"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Example: Block Execution of a Binary") {
                    BodyText("Deny execve of /usr/bin/wget — prevent the tool from running entirely:")
                    CodeBlock(
                        "SEC(\"lsm/bprm_check_security\")\n" +
                        "int BPF_PROG(block_wget, struct linux_binprm *bprm)\n" +
                        "{\n" +
                        "    const char blocked[] = \"/usr/bin/wget\";\n" +
                        "    char fname[256] = {};\n" +
                        "\n" +
                        "    bpf_probe_read_kernel_str(fname, sizeof(fname),\n" +
                        "                              bprm->filename);\n" +
                        "\n" +
                        "    if (bpf_strncmp(fname, sizeof(blocked) - 1, blocked) == 0)\n" +
                        "        return -EPERM;\n" +
                        "\n" +
                        "    return 0;\n" +
                        "}"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("When -EPERM is returned from bprm_check_security the execve syscall fails with 'Operation not permitted'. The return value from the hook becomes the syscall error code.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Example: Log File Opens to Ring Buffer") {
                    BodyText("Capture the filename and process name for every file open and send to user space via ring buffer:")
                    CodeBlock(
                        "struct event {\n" +
                        "    char comm[16];\n" +
                        "    char filename[256];\n" +
                        "};\n" +
                        "\n" +
                        "struct {\n" +
                        "    __uint(type, BPF_MAP_TYPE_RINGBUF);\n" +
                        "    __uint(max_entries, 1 << 20);\n" +
                        "} rb SEC(\".maps\");\n" +
                        "\n" +
                        "SEC(\"lsm/file_open\")\n" +
                        "int BPF_PROG(log_file_open, struct file *file)\n" +
                        "{\n" +
                        "    struct event *e = bpf_ringbuf_reserve(&rb,\n" +
                        "                                          sizeof(*e), 0);\n" +
                        "    if (!e) return 0;\n" +
                        "\n" +
                        "    bpf_get_current_comm(e->comm, sizeof(e->comm));\n" +
                        "    bpf_probe_read_kernel_str(\n" +
                        "        e->filename, sizeof(e->filename),\n" +
                        "        BPF_CORE_READ(file, f_path.dentry, d_name.name));\n" +
                        "\n" +
                        "    bpf_ringbuf_submit(e, 0);\n" +
                        "    return 0;  // allow — this hook only observes\n" +
                        "}"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Example: Block Signal to a Protected Process") {
                    BodyText("Prevent SIGKILL (signal 9) from reaching a process named 'myguard'. This protects a watchdog process from being killed by root:")
                    CodeBlock(
                        "SEC(\"lsm/task_kill\")\n" +
                        "int BPF_PROG(protect_guard,\n" +
                        "             struct task_struct *p,\n" +
                        "             struct kernel_siginfo *info,\n" +
                        "             int sig, const struct cred *cred)\n" +
                        "{\n" +
                        "    char target_comm[16] = {};\n" +
                        "    BPF_CORE_READ_STR_INTO(&target_comm, p, comm);\n" +
                        "\n" +
                        "    if (sig == 9 &&\n" +
                        "        bpf_strncmp(target_comm, 7, \"myguard\") == 0)\n" +
                        "        return -EPERM;\n" +
                        "\n" +
                        "    return 0;\n" +
                        "}"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Note: even root (uid=0) is subject to LSM hook denials from eBPF LSM, as long as the BPF program itself was loaded with CAP_MAC_ADMIN. This is a stronger protection than setuid or capabilities alone.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Example: Block Outgoing Connection by IP") {
                    BodyText("Deny outgoing TCP/UDP connections to a specific IP address and port:")
                    CodeBlock(
                        "#include <linux/in.h>   // AF_INET, struct sockaddr_in\n" +
                        "\n" +
                        "SEC(\"lsm/socket_connect\")\n" +
                        "int BPF_PROG(block_connect,\n" +
                        "             struct socket *sock,\n" +
                        "             struct sockaddr *address,\n" +
                        "             int addrlen)\n" +
                        "{\n" +
                        "    if (address->sa_family != AF_INET)\n" +
                        "        return 0;\n" +
                        "\n" +
                        "    struct sockaddr_in *addr4 =\n" +
                        "        (struct sockaddr_in *)address;\n" +
                        "\n" +
                        "    // Block connections to 1.2.3.4 port 4444\n" +
                        "    __be32 blocked_ip   = bpf_htonl(0x01020304);\n" +
                        "    __be16 blocked_port = bpf_htons(4444);\n" +
                        "\n" +
                        "    if (addr4->sin_addr.s_addr == blocked_ip &&\n" +
                        "        addr4->sin_port       == blocked_port)\n" +
                        "        return -EPERM;\n" +
                        "\n" +
                        "    return 0;\n" +
                        "}"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("For production use, store the blocked IPs in a BPF_MAP_TYPE_LPM_TRIE or BPF_MAP_TYPE_HASH map that the user-space control plane can update without reloading the BPF program.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Example: Block File Deletion") {
                    BodyText("Prevent deletion of files in a sensitive directory, e.g. /etc/:")
                    CodeBlock(
                        "SEC(\"lsm/inode_unlink\")\n" +
                        "int BPF_PROG(protect_etc,\n" +
                        "             struct inode *dir,\n" +
                        "             struct dentry *dentry)\n" +
                        "{\n" +
                        "    // Read the full path via dentry walk is complex in BPF;\n" +
                        "    // a simpler approach: check the parent directory inode number\n" +
                        "    // and compare against the known inode of /etc.\n" +
                        "    // Or use the directory's i_ino if you know it.\n" +
                        "\n" +
                        "    char dir_name[64] = {};\n" +
                        "    const char *name = BPF_CORE_READ(\n" +
                        "        dentry, d_parent, d_name.name);\n" +
                        "    bpf_probe_read_kernel_str(dir_name,\n" +
                        "                             sizeof(dir_name), name);\n" +
                        "\n" +
                        "    // Block unlink inside /etc\n" +
                        "    if (bpf_strncmp(dir_name, 3, \"etc\") == 0)\n" +
                        "        return -EPERM;\n" +
                        "\n" +
                        "    return 0;\n" +
                        "}"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Sleepable eBPF LSM") {
                    BodyText("By default, eBPF LSM programs run in atomic context — they cannot call sleeping helpers. Sleepable programs (BPF_F_SLEEPABLE flag, SEC(\"lsm.s/...\")) relax this restriction.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Sleepable hooks unlock additional helpers:")
                    CodeBlock(
                        "bpf_copy_from_user(dst, size, user_ptr)\n" +
                        "    // Read from user-space memory safely\n" +
                        "\n" +
                        "bpf_find_vma(task, addr, callback, callback_ctx, flags)\n" +
                        "    // Walk process VMAs (needs mmap_read_lock)\n" +
                        "\n" +
                        "bpf_task_storage_get(&map, task, NULL,\n" +
                        "                     BPF_LOCAL_STORAGE_GET_F_CREATE)\n" +
                        "    // Get or create per-task local storage"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Use the .s suffix to mark a hook as sleepable:")
                    CodeBlock(
                        "// Normal (non-sleepable):\n" +
                        "SEC(\"lsm/bprm_check_security\")\n" +
                        "\n" +
                        "// Sleepable — can use bpf_copy_from_user etc.:\n" +
                        "SEC(\"lsm.s/bprm_check_security\")"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Not all LSM hooks support sleepable programs. The verifier rejects the load with EINVAL if the hook runs in an atomic context (e.g. NMI or softirq). Check kernel source to confirm a specific hook is safe for sleepable use.")
                }
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}
