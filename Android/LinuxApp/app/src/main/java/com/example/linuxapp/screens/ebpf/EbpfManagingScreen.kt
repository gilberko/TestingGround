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
fun EbpfManagingScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "eBPF — Managing & Monitoring",
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
                SectionCard(title = "Listing Loaded Programs") {
                    BodyText("bpftool is the primary tool for inspecting the live eBPF state of a kernel. It requires root or CAP_BPF + CAP_PERFMON.")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock(
                        "# List all loaded programs\n" +
                        "bpftool prog list\n" +
                        "bpftool prog show         # same, more verbose\n" +
                        "\n" +
                        "# Show a specific program by its runtime ID\n" +
                        "bpftool prog show id 42\n" +
                        "\n" +
                        "# Find by program name (set from BPF source function name)\n" +
                        "bpftool prog show name xdp_drop_ip"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Sample output of bpftool prog list:")
                    CodeBlock(
                        "17: xdp  name xdp_drop_ip  tag a94e0b1b244d6b98\n" +
                        "    loaded_at 2024-01-15T10:23:41  uid 0\n" +
                        "    xlated 176B  jited 112B  memlock 4096B  map_ids 3\n" +
                        "    btf_id 42  gpl_compatible\n" +
                        "    pids ip(1234)"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Disassemble the BPF bytecode or JIT-compiled native code:")
                    CodeBlock(
                        "bpftool prog dump xlated id 17        # BPF bytecode\n" +
                        "bpftool prog dump xlated id 17 visual # graphviz CFG\n" +
                        "bpftool prog dump jited  id 17        # native machine code"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Listing Loaded Maps") {
                    CodeBlock(
                        "# List all maps\n" +
                        "bpftool map list\n" +
                        "\n" +
                        "# Show details for one map\n" +
                        "bpftool map show id 10\n" +
                        "\n" +
                        "# Dump ALL key/value pairs in a map\n" +
                        "bpftool map dump id 10\n" +
                        "\n" +
                        "# Look up a specific key\n" +
                        "bpftool map lookup id 10 key 0x06 0x00 0x00 0x00\n" +
                        "\n" +
                        "# Update a value from the command line\n" +
                        "bpftool map update id 10 key 0x06 0x00 0x00 0x00 \\\n" +
                        "                          value 0x00 0x00 0x00 0x00\n" +
                        "\n" +
                        "# Delete an entry\n" +
                        "bpftool map delete id 10 key 0x06 0x00 0x00 0x00"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Sample output of bpftool map list:")
                    CodeBlock(
                        "3: hash  name proto_count  flags 0x0\n" +
                        "   key 1B  value 8B  max_entries 256  memlock 4096B\n" +
                        "   btf_id 42\n" +
                        "   pids ip(1234)"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Pinned Objects") {
                    BodyText("/sys/fs/bpf/ holds pinned programs, maps, and bpf_links. Each entry is a file that keeps the object alive.")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock(
                        "# List everything pinned\n" +
                        "ls -la /sys/fs/bpf/\n" +
                        "\n" +
                        "# Show info for a pinned program or map\n" +
                        "bpftool prog show pinned /sys/fs/bpf/my_prog\n" +
                        "bpftool map  show pinned /sys/fs/bpf/my_map\n" +
                        "\n" +
                        "# Dump pinned map contents\n" +
                        "bpftool map dump pinned /sys/fs/bpf/my_map\n" +
                        "\n" +
                        "# Remove a pin (deletes the file; object freed\n" +
                        "# when all other fds / refs also drop to zero)\n" +
                        "rm /sys/fs/bpf/my_prog\n" +
                        "rm /sys/fs/bpf/my_map"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("bpf_links are also pinnable. They appear as regular files in /sys/fs/bpf/ and hold the program attachment alive:")
                    CodeBlock(
                        "bpftool link list             # list all bpf_links\n" +
                        "bpftool link show id 5        # details + attached prog\n" +
                        "bpftool link pin id 5 /sys/fs/bpf/my_link\n" +
                        "bpftool link detach id 5      # detach without freeing"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Network / XDP Inspection") {
                    BodyText("Find out which interfaces have eBPF programs attached at the network layer:")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock(
                        "# ip link shows 'xdp' or 'xdpgeneric' flag\n" +
                        "ip link show dev eth0\n" +
                        "# Output example:\n" +
                        "# 2: eth0: <...> xdp\n" +
                        "#     link/ether ... brd ...\n" +
                        "#     prog/xdp id 17 name xdp_drop_ip\n" +
                        "\n" +
                        "# List XDP, TC, and flow_dissector programs on all interfaces\n" +
                        "bpftool net list\n" +
                        "\n" +
                        "# Same but only for a specific interface\n" +
                        "bpftool net list dev eth0\n" +
                        "# Example output:\n" +
                        "# eth0(2):\n" +
                        "#   xdp: id 17 name xdp_drop_ip\n" +
                        "\n" +
                        "# TC BPF filters\n" +
                        "tc filter show dev eth0 ingress\n" +
                        "tc filter show dev eth0 egress"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("XDP mode — native vs generic vs offloaded:")
                    CodeBlock(
                        "# Native XDP (fastest — driver support required)\n" +
                        "ip link set dev eth0 xdp      obj prog.o\n" +
                        "\n" +
                        "# Generic XDP (slower — fallback, any driver)\n" +
                        "ip link set dev eth0 xdpgeneric obj prog.o\n" +
                        "\n" +
                        "# Hardware offload (NIC executes BPF directly)\n" +
                        "ip link set dev eth0 xdpoffload obj prog.o\n" +
                        "\n" +
                        "# Check which mode is active\n" +
                        "bpftool net list dev eth0"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "GPL and Metadata") {
                    BodyText("bpftool prog show reports whether a program is GPL-compatible. This matters because GPL-only helpers (like bpf_probe_read_kernel) are available only to programs with a GPL-compatible license set via SEC(\"license\") = \"GPL\".")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock(
                        "bpftool prog show id 17\n" +
                        "# ...gpl_compatible...   <- present if LICENSE=GPL\n" +
                        "# Absent for non-GPL programs\n" +
                        "\n" +
                        "# The 'pids' field shows which process holds an fd\n" +
                        "# to this program right now (bpftool reads /proc/<pid>/fd)\n" +
                        "# Example:  pids my_agent(5678)\n" +
                        "\n" +
                        "# btf_id links the program to its BTF type info\n" +
                        "bpftool btf list             # list all BTF objects\n" +
                        "bpftool btf show id 42       # which files/programs use it\n" +
                        "bpftool btf dump id 42 format c  # reconstruct C header"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Probe which eBPF features the current kernel supports:")
                    CodeBlock(
                        "bpftool feature probe\n" +
                        "# Reports: program types, map types, helper availability,\n" +
                        "# large instruction limit, bounded loops, global data, etc.\n" +
                        "\n" +
                        "# Probe a specific program type\n" +
                        "bpftool feature probe prog_type xdp\n" +
                        "bpftool feature probe map_type ringbuf"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Inspecting via /proc and /sys") {
                    BodyText("Several kernel interfaces expose eBPF state without bpftool:")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock(
                        "# kprobes installed by eBPF programs appear here\n" +
                        "cat /sys/kernel/debug/tracing/kprobe_events\n" +
                        "\n" +
                        "# All active tracepoints enabled by BPF programs\n" +
                        "cat /sys/kernel/debug/tracing/events/enable\n" +
                        "\n" +
                        "# JIT compilation stats (enabled BPF JIT)\n" +
                        "cat /proc/sys/net/core/bpf_jit_enable     # 0/1/2\n" +
                        "cat /proc/sys/net/core/bpf_jit_harden     # 0/1/2\n" +
                        "cat /proc/sys/net/core/bpf_jit_kallsyms   # 0/1\n" +
                        "\n" +
                        "# BPF JIT kallsyms: loaded BPF programs appear in\n" +
                        "# /proc/kallsyms as bpf_prog_<tag>_<name>\n" +
                        "grep bpf_prog /proc/kallsyms\n" +
                        "\n" +
                        "# Memory used by all BPF maps and programs\n" +
                        "grep bpf /proc/meminfo"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("The /proc/kallsyms trick is useful for correlating a BPF program tag (shown by bpftool) with a symbol name for use with perf or addr2line when debugging JIT-compiled BPF code.")
                }
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}
