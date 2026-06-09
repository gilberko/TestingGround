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
fun EbpfPersistencyScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "eBPF — Persistency",
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
                SectionCard(title = "eBPF Object Lifetime") {
                    BodyText("Every eBPF program and map is a kernel object managed by reference counting. When you call the bpf() syscall to create a program or map, the kernel returns a file descriptor (fd). That fd holds one reference to the object.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("As long as at least one reference exists the object stays alive. When the last reference drops to zero the kernel garbage-collects the object. This means:")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock(
                        "// fd=3 → ref_count=1\n" +
                        "int fd = bpf(BPF_MAP_CREATE, &attr, sizeof(attr));\n" +
                        "\n" +
                        "// dup → ref_count=2\n" +
                        "int fd2 = dup(fd);\n" +
                        "\n" +
                        "// close one → ref_count=1 (object still alive)\n" +
                        "close(fd);\n" +
                        "\n" +
                        "// close last → ref_count=0 → map freed\n" +
                        "close(fd2);"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "What Happens When the Loader Exits") {
                    BodyText("When a process exits, the OS closes all its file descriptors. Each close drops the ref count on the associated eBPF object. If no other process or kernel mechanism holds a reference, the program and maps are freed.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Attachments created via perf_event_open (the mechanism used for kprobes, uprobes, and tracepoints in libbpf) also die when the associated perf event fd is closed. So a kprobe program disappears along with its loader unless you take action to prevent it.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Summary — what survives vs what dies when the loader exits:")
                    CodeBlock(
                        "DIES with loader (unless pinned):\n" +
                        "  kprobe / kretprobe attachments\n" +
                        "  tracepoint attachments\n" +
                        "  uprobe / uretprobe attachments\n" +
                        "  fentry / fexit attachments (bpf_link fd closed)\n" +
                        "  LSM program attachments (bpf_link fd closed)\n" +
                        "  BPF maps (fd closed)\n" +
                        "\n" +
                        "SURVIVES (kernel holds its own reference):\n" +
                        "  XDP attached via ip link set dev eth0 xdp\n" +
                        "  TC filter added via tc filter add ... bpf\n" +
                        "  Any bpf_link or map pinned to /sys/fs/bpf/"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "BPF FS Pinning") {
                    BodyText("/sys/fs/bpf/ is a pseudo-filesystem of type 'bpf' that is mounted at boot on modern systems. Pinning an eBPF object to this filesystem creates a special file that holds an extra reference to the object. The object remains alive even after all process fds are closed.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Pinning uses BPF_OBJ_PIN; retrieving uses BPF_OBJ_GET:")
                    CodeBlock(
                        "// Pin a map fd to the BPF FS\n" +
                        "union bpf_attr pin_attr = {\n" +
                        "    .pathname = (uint64_t)\"/sys/fs/bpf/my_map\",\n" +
                        "    .bpf_fd   = map_fd,\n" +
                        "};\n" +
                        "bpf(BPF_OBJ_PIN, &pin_attr, sizeof(pin_attr));\n" +
                        "close(map_fd);  // map still alive — BPF FS holds ref\n" +
                        "\n" +
                        "// Later, in a second process:\n" +
                        "union bpf_attr get_attr = {\n" +
                        "    .pathname = (uint64_t)\"/sys/fs/bpf/my_map\",\n" +
                        "};\n" +
                        "int fd2 = bpf(BPF_OBJ_GET, &get_attr, sizeof(get_attr));\n" +
                        "// fd2 is a new fd to the same map\n" +
                        "\n" +
                        "// Remove the pin (map freed when fd2 also closed):\n" +
                        "unlink(\"/sys/fs/bpf/my_map\");"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Pinning with libbpf") {
                    BodyText("libbpf provides high-level pinning APIs that wrap the raw syscall:")
                    CodeBlock(
                        "#include <bpf/libbpf.h>\n" +
                        "\n" +
                        "// Pin a single map\n" +
                        "bpf_map__pin(map, \"/sys/fs/bpf/my_map\");\n" +
                        "\n" +
                        "// Pin a single program\n" +
                        "bpf_program__pin(prog, \"/sys/fs/bpf/my_prog\");\n" +
                        "\n" +
                        "// Pin all maps in an object to a directory\n" +
                        "bpf_object__pin_maps(obj, \"/sys/fs/bpf/myapp/\");\n" +
                        "\n" +
                        "// Pin all programs in an object\n" +
                        "bpf_object__pin_programs(obj, \"/sys/fs/bpf/myapp/\");\n" +
                        "\n" +
                        "// --- In a second process: open pinned map ---\n" +
                        "int map_fd = bpf_obj_get(\"/sys/fs/bpf/my_map\");\n" +
                        "if (map_fd < 0) { perror(\"bpf_obj_get\"); exit(1); }\n" +
                        "// Now use map_fd as a normal BPF map fd"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("The directory passed to bpf_object__pin_maps must already exist. Each map is pinned as /dir/map_name using the map's name from the BPF source.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Pinning with bpftool") {
                    BodyText("bpftool provides one-command pinning without writing any C code:")
                    CodeBlock(
                        "# Pin a loaded map by its runtime ID\n" +
                        "bpftool map pin id 42 /sys/fs/bpf/my_map\n" +
                        "\n" +
                        "# Pin a loaded program by ID\n" +
                        "bpftool prog pin id 17 /sys/fs/bpf/my_prog\n" +
                        "\n" +
                        "# Load an object file AND pin in one step\n" +
                        "bpftool prog load prog.o /sys/fs/bpf/my_prog\n" +
                        "\n" +
                        "# List everything pinned on the BPF FS\n" +
                        "ls -la /sys/fs/bpf/\n" +
                        "\n" +
                        "# Show info about a pinned object\n" +
                        "bpftool prog show pinned /sys/fs/bpf/my_prog\n" +
                        "bpftool map  show pinned /sys/fs/bpf/my_map\n" +
                        "\n" +
                        "# Remove a pin (object freed once all other refs drop)\n" +
                        "rm /sys/fs/bpf/my_map"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Persistent Attachments (No Pinning Needed)") {
                    BodyText("Two program types are attached through kernel-owned mechanisms that survive loader exit without any pinning:")
                    Spacer(Modifier.height(8.dp))
                    BodyText("XDP — attached via iproute2 or bpftool net. The kernel stores the reference in the net_device structure:")
                    CodeBlock(
                        "# Attach (survives loader exit)\n" +
                        "ip link set dev eth0 xdp obj prog.o sec xdp\n" +
                        "\n" +
                        "# Verify it is still attached after loader dies\n" +
                        "ip link show dev eth0   # shows 'xdp' flag\n" +
                        "bpftool net list\n" +
                        "\n" +
                        "# Detach\n" +
                        "ip link set dev eth0 xdp off"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("TC (Traffic Control) — attached via tc. The qdisc/filter holds the reference:")
                    CodeBlock(
                        "# Attach ingress TC program\n" +
                        "tc qdisc add dev eth0 clsact\n" +
                        "tc filter add dev eth0 ingress bpf \\\n" +
                        "    direct-action obj prog.o sec ingress\n" +
                        "\n" +
                        "# Verify\n" +
                        "tc filter show dev eth0 ingress\n" +
                        "\n" +
                        "# Detach\n" +
                        "tc filter del dev eth0 ingress"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "BPF Links") {
                    BodyText("A bpf_link is a kernel object that represents a program attachment (e.g. a kprobe binding or an LSM hook). Like programs and maps, links are reference-counted. Pinning a link keeps the attachment alive across loader restarts.")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock(
                        "// Attach a kprobe via bpf_link (libbpf)\n" +
                        "struct bpf_link *link =\n" +
                        "    bpf_program__attach_kprobe(prog, false, \"do_unlinkat\");\n" +
                        "\n" +
                        "// Pin the link — attachment stays alive after exit\n" +
                        "bpf_link__pin(link, \"/sys/fs/bpf/my_kprobe_link\");\n" +
                        "bpf_link__destroy(link);  // close our fd; link still alive\n" +
                        "\n" +
                        "// --- Next time the agent restarts: ---\n" +
                        "struct bpf_link *link2 =\n" +
                        "    bpf_link__open(\"/sys/fs/bpf/my_kprobe_link\");\n" +
                        "// Can now disconnect: bpf_link__detach(link2);\n" +
                        "// Or update: bpf_link__update_program(link2, new_prog);"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Pinning the link is the recommended approach for LSM, fentry/fexit, and kprobe programs that need to survive loader restarts. It is more explicit than XDP/TC persistent attachment because you control cleanup by deleting the pin file.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Sharing Maps Between Programs") {
                    BodyText("A pinned map can be opened by any number of processes simultaneously. Both processes read and write the same underlying kernel data structure. This is the standard pattern for:")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock(
                        "// Typical telemetry agent pattern:\n" +
                        "//\n" +
                        "// 1. First run: create and pin the map\n" +
                        "bpf_map__pin(counters_map, \"/sys/fs/bpf/counters\");\n" +
                        "\n" +
                        "// 2. Agent restarts: open pinned map, skip creation\n" +
                        "int fd = bpf_obj_get(\"/sys/fs/bpf/counters\");\n" +
                        "// All previously accumulated counts are still there!\n" +
                        "\n" +
                        "// 3. A separate reader process can open it too\n" +
                        "int reader_fd = bpf_obj_get(\"/sys/fs/bpf/counters\");\n" +
                        "bpf_map_lookup_elem(reader_fd, &key, &value);"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Use cases: counters that must not reset on agent crash, config maps updated by a control plane while the data-plane BPF program keeps running, flow tables shared between multiple BPF programs.")
                }
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}
