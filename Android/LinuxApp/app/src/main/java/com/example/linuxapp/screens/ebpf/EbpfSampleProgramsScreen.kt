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
fun EbpfSampleProgramsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "eBPF — Sample Programs",
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
                SectionCard(title = "XDP: Drop Packets from a Specific IP") {
                    BodyText("XDP programs run at the earliest possible point in the receive path — inside the NIC driver, before the sk_buff is allocated. This makes them extremely fast (tens of millions of packets per second).")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Every pointer access must be bounds-checked against data_end or the verifier will reject the program:")
                    CodeBlock(
                        "#include <linux/bpf.h>\n" +
                        "#include <bpf/bpf_helpers.h>\n" +
                        "#include <linux/if_ether.h>   // struct ethhdr\n" +
                        "#include <linux/ip.h>          // struct iphdr\n" +
                        "#include <arpa/inet.h>         // htons, htonl\n" +
                        "\n" +
                        "SEC(\"xdp\")\n" +
                        "int xdp_drop_ip(struct xdp_md *ctx)\n" +
                        "{\n" +
                        "    void *data     = (void *)(long)ctx->data;\n" +
                        "    void *data_end = (void *)(long)ctx->data_end;\n" +
                        "\n" +
                        "    // Parse Ethernet header\n" +
                        "    struct ethhdr *eth = data;\n" +
                        "    if ((void *)(eth + 1) > data_end)\n" +
                        "        return XDP_PASS;\n" +
                        "\n" +
                        "    // Only handle IPv4\n" +
                        "    if (eth->h_proto != __constant_htons(ETH_P_IP))\n" +
                        "        return XDP_PASS;\n" +
                        "\n" +
                        "    // Parse IP header\n" +
                        "    struct iphdr *iph = (void *)(eth + 1);\n" +
                        "    if ((void *)(iph + 1) > data_end)\n" +
                        "        return XDP_PASS;\n" +
                        "\n" +
                        "    // Drop packets from 198.51.100.1\n" +
                        "    if (iph->saddr == __constant_htonl(0xC6336401))\n" +
                        "        return XDP_DROP;\n" +
                        "\n" +
                        "    return XDP_PASS;\n" +
                        "}\n" +
                        "\n" +
                        "char LICENSE[] SEC(\"license\") = \"GPL\";"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Load and attach:")
                    CodeBlock(
                        "# Compile\n" +
                        "clang -O2 -target bpf -c xdp_drop.c -o xdp_drop.o\n" +
                        "\n" +
                        "# Attach (survives loader exit — kernel holds ref)\n" +
                        "ip link set dev eth0 xdp obj xdp_drop.o sec xdp\n" +
                        "\n" +
                        "# Verify\n" +
                        "ip link show dev eth0\n" +
                        "\n" +
                        "# Detach\n" +
                        "ip link set dev eth0 xdp off"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "XDP: Count Packets Per IP Protocol") {
                    BodyText("Use a BPF hash map to count packets broken down by IP protocol number (TCP=6, UDP=17, ICMP=1, etc.):")
                    CodeBlock(
                        "struct {\n" +
                        "    __uint(type,       BPF_MAP_TYPE_HASH);\n" +
                        "    __uint(max_entries, 256);\n" +
                        "    __type(key,        __u8);   // IP protocol\n" +
                        "    __type(value,      __u64);  // packet count\n" +
                        "} proto_count SEC(\".maps\");\n" +
                        "\n" +
                        "SEC(\"xdp\")\n" +
                        "int xdp_count(struct xdp_md *ctx)\n" +
                        "{\n" +
                        "    void *data     = (void *)(long)ctx->data;\n" +
                        "    void *data_end = (void *)(long)ctx->data_end;\n" +
                        "\n" +
                        "    struct ethhdr *eth = data;\n" +
                        "    if ((void *)(eth + 1) > data_end) return XDP_PASS;\n" +
                        "    if (eth->h_proto != __constant_htons(ETH_P_IP))\n" +
                        "        return XDP_PASS;\n" +
                        "\n" +
                        "    struct iphdr *iph = (void *)(eth + 1);\n" +
                        "    if ((void *)(iph + 1) > data_end) return XDP_PASS;\n" +
                        "\n" +
                        "    __u8  proto = iph->protocol;\n" +
                        "    __u64 *cnt  = bpf_map_lookup_elem(&proto_count, &proto);\n" +
                        "    if (cnt)\n" +
                        "        __sync_fetch_and_add(cnt, 1);\n" +
                        "    else {\n" +
                        "        __u64 one = 1;\n" +
                        "        bpf_map_update_elem(&proto_count, &proto,\n" +
                        "                            &one, BPF_ANY);\n" +
                        "    }\n" +
                        "    return XDP_PASS;\n" +
                        "}"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Read the map from user space with bpftool:")
                    CodeBlock(
                        "bpftool map list            # find the map ID\n" +
                        "bpftool map dump id <id>    # print all key/value pairs"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Monitor execve: Log All Process Starts") {
                    BodyText("Attach to the sys_enter_execve tracepoint to capture every process launch. The tracepoint args struct is automatically typed by BTF so no manual casting is needed.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("BPF program (execve_monitor.bpf.c):")
                    CodeBlock(
                        "#include <vmlinux.h>\n" +
                        "#include <bpf/bpf_helpers.h>\n" +
                        "#include <bpf/bpf_tracing.h>\n" +
                        "\n" +
                        "struct event {\n" +
                        "    __u32 pid;\n" +
                        "    char  comm[16];\n" +
                        "    char  filename[256];\n" +
                        "};\n" +
                        "\n" +
                        "struct {\n" +
                        "    __uint(type,       BPF_MAP_TYPE_RINGBUF);\n" +
                        "    __uint(max_entries, 1 << 20);\n" +
                        "} rb SEC(\".maps\");\n" +
                        "\n" +
                        "SEC(\"tracepoint/syscalls/sys_enter_execve\")\n" +
                        "int trace_execve(struct trace_event_raw_sys_enter *ctx)\n" +
                        "{\n" +
                        "    struct event *e =\n" +
                        "        bpf_ringbuf_reserve(&rb, sizeof(*e), 0);\n" +
                        "    if (!e) return 0;\n" +
                        "\n" +
                        "    e->pid = bpf_get_current_pid_tgid() >> 32;\n" +
                        "    bpf_get_current_comm(e->comm, sizeof(e->comm));\n" +
                        "\n" +
                        "    // ctx->args[0] is the filename (const char __user *)\n" +
                        "    const char *fname = (const char *)ctx->args[0];\n" +
                        "    bpf_probe_read_user_str(e->filename,\n" +
                        "                           sizeof(e->filename), fname);\n" +
                        "\n" +
                        "    bpf_ringbuf_submit(e, 0);\n" +
                        "    return 0;\n" +
                        "}\n" +
                        "\n" +
                        "char LICENSE[] SEC(\"license\") = \"GPL\";"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("User-space loader reads events from the ring buffer and prints them:")
                    CodeBlock(
                        "// After open_and_load + attach:\n" +
                        "struct ring_buffer *rb =\n" +
                        "    ring_buffer__new(bpf_map__fd(skel->maps.rb),\n" +
                        "                     handle_event, NULL, NULL);\n" +
                        "while (true)\n" +
                        "    ring_buffer__poll(rb, 100 /* ms timeout */);\n" +
                        "\n" +
                        "// handle_event callback:\n" +
                        "static int handle_event(void *ctx, void *data, size_t sz) {\n" +
                        "    struct event *e = data;\n" +
                        "    printf(\"[%u] %s exec: %s\\n\",\n" +
                        "           e->pid, e->comm, e->filename);\n" +
                        "    return 0;\n" +
                        "}"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Monitor File Access: Log All Opens") {
                    BodyText("Attach to sys_enter_openat to capture every file open syscall with filename, process name, and flags:")
                    CodeBlock(
                        "struct open_event {\n" +
                        "    __u32 pid;\n" +
                        "    __u32 flags;\n" +
                        "    char  comm[16];\n" +
                        "    char  filename[256];\n" +
                        "};\n" +
                        "\n" +
                        "struct {\n" +
                        "    __uint(type,       BPF_MAP_TYPE_RINGBUF);\n" +
                        "    __uint(max_entries, 1 << 22);\n" +
                        "} rb SEC(\".maps\");\n" +
                        "\n" +
                        "SEC(\"tracepoint/syscalls/sys_enter_openat\")\n" +
                        "int trace_openat(struct trace_event_raw_sys_enter *ctx)\n" +
                        "{\n" +
                        "    // Filter: only log root process opens\n" +
                        "    // (remove this check to log everything)\n" +
                        "    if (bpf_get_current_uid_gid() != 0)\n" +
                        "        return 0;\n" +
                        "\n" +
                        "    struct open_event *e =\n" +
                        "        bpf_ringbuf_reserve(&rb, sizeof(*e), 0);\n" +
                        "    if (!e) return 0;\n" +
                        "\n" +
                        "    e->pid   = bpf_get_current_pid_tgid() >> 32;\n" +
                        "    e->flags = (__u32)ctx->args[2]; // O_RDONLY etc.\n" +
                        "    bpf_get_current_comm(e->comm, sizeof(e->comm));\n" +
                        "\n" +
                        "    // args[1] = filename (const char __user *)\n" +
                        "    bpf_probe_read_user_str(e->filename,\n" +
                        "                           sizeof(e->filename),\n" +
                        "                           (const char *)ctx->args[1]);\n" +
                        "\n" +
                        "    bpf_ringbuf_submit(e, 0);\n" +
                        "    return 0;\n" +
                        "}"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("For security use-cases consider attaching to the LSM file_open hook instead — it fires after permission checks, so you only see files the process is actually allowed to open.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Monitor Signals: Trace Signal Delivery") {
                    BodyText("The signal:signal_generate tracepoint fires whenever the kernel generates a signal. It captures sender, target PID, signal number, and the delivery result.")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock(
                        "struct sig_event {\n" +
                        "    __u32 sender_pid;\n" +
                        "    __u32 target_pid;\n" +
                        "    __s32 sig;\n" +
                        "    char  sender_comm[16];\n" +
                        "};\n" +
                        "\n" +
                        "struct {\n" +
                        "    __uint(type,       BPF_MAP_TYPE_RINGBUF);\n" +
                        "    __uint(max_entries, 1 << 20);\n" +
                        "} rb SEC(\".maps\");\n" +
                        "\n" +
                        "// trace_event_raw_signal_generate fields:\n" +
                        "//   sig    — signal number\n" +
                        "//   pid    — target PID\n" +
                        "//   group  — 1 = sent to whole thread group\n" +
                        "//   result — 0 = delivered, IGNORED, BLOCKED, OVERFLOW\n" +
                        "\n" +
                        "SEC(\"tracepoint/signal/signal_generate\")\n" +
                        "int trace_signal(\n" +
                        "    struct trace_event_raw_signal_generate *ctx)\n" +
                        "{\n" +
                        "    struct sig_event *e =\n" +
                        "        bpf_ringbuf_reserve(&rb, sizeof(*e), 0);\n" +
                        "    if (!e) return 0;\n" +
                        "\n" +
                        "    e->sender_pid = bpf_get_current_pid_tgid() >> 32;\n" +
                        "    e->target_pid = ctx->pid;\n" +
                        "    e->sig        = ctx->sig;\n" +
                        "    bpf_get_current_comm(e->sender_comm,\n" +
                        "                         sizeof(e->sender_comm));\n" +
                        "\n" +
                        "    bpf_ringbuf_submit(e, 0);\n" +
                        "    return 0;\n" +
                        "}"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("A quick bpftrace one-liner for the same result:")
                    CodeBlock(
                        "sudo bpftrace -e '\n" +
                        "  tracepoint:signal:signal_generate {\n" +
                        "    printf(\"%s (pid %d) -> pid %d  sig %d\\n\",\n" +
                        "           comm, pid, args->pid, args->sig);\n" +
                        "  }'"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Common use: catch which process sends SIGKILL (sig=9) to detect forceful process termination, or trace SIGHUP (sig=1) to audit daemon reload triggers.")
                }
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}
