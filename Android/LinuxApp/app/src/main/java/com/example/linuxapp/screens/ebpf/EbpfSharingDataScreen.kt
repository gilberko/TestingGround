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
fun EbpfSharingDataScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "eBPF — Sharing Data",
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
                SectionCard(title = "What Are eBPF Maps?") {
                    BodyText(
                        "eBPF maps are kernel data structures that serve as the primary mechanism for " +
                        "sharing data in the eBPF ecosystem. They can be shared between:"
                    )
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "• Multiple eBPF programs running in the kernel\n" +
                        "• eBPF programs and user-space applications\n" +
                        "• Different invocations of the same eBPF program"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText(
                        "Unlike local eBPF stack variables (which vanish when the program exits), maps " +
                        "live in kernel memory and persist across program invocations. They are accessed " +
                        "from user space via file descriptors and a set of bpf() syscall commands."
                    )
                }
            }

            item {
                SectionCard(title = "Declaring and Accessing Maps") {
                    BodyText("In eBPF C source (using libbpf's BTF-based map declaration):")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "struct {\n" +
                        "    __uint(type,        BPF_MAP_TYPE_HASH);\n" +
                        "    __uint(max_entries, 1024);\n" +
                        "    __type(key,         __u32);   // e.g. PID\n" +
                        "    __type(value,       __u64);   // e.g. byte count\n" +
                        "} my_map SEC(\".maps\");"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Kernel-side access (inside the eBPF program):")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "__u32 key = bpf_get_current_pid_tgid() >> 32;\n" +
                        "__u64 *val = bpf_map_lookup_elem(&my_map, &key);\n" +
                        "if (val)\n" +
                        "    (*val)++;\n" +
                        "else {\n" +
                        "    __u64 init = 1;\n" +
                        "    bpf_map_update_elem(&my_map, &key, &init, BPF_ANY);\n" +
                        "}\n" +
                        "bpf_map_delete_elem(&my_map, &key); // remove an entry"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("User-space access (libbpf):")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "// After loading the BPF skeleton:\n" +
                        "int fd = bpf_map__fd(skel->maps.my_map);\n" +
                        "\n" +
                        "__u32 key = target_pid;\n" +
                        "__u64 value;\n" +
                        "bpf_map_lookup_elem(fd, &key, &value);\n" +
                        "printf(\"PID %u: %llu bytes\\n\", key, value);\n" +
                        "\n" +
                        "bpf_map_update_elem(fd, &key, &new_val, BPF_ANY);\n" +
                        "bpf_map_delete_elem(fd, &key);"
                    )
                }
            }

            item {
                SectionCard(title = "Sharing Between Multiple eBPF Programs") {
                    BodyText(
                        "By default, a map's lifetime is tied to the file descriptor that owns it. " +
                        "To share a map between separate eBPF programs — possibly loaded independently " +
                        "— pin it to the BPF virtual filesystem:"
                    )
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "# Mount bpffs (usually already mounted at boot):\nmount -t bpf none /sys/fs/bpf\n\n// Program A — create and pin the map:\nint fd = bpf_map__fd(skel->maps.my_map);\nbpf_obj_pin(fd, \"/sys/fs/bpf/my_shared_map\");\n\n// Program B — open the already-pinned map:\nint fd = bpf_obj_get(\"/sys/fs/bpf/my_shared_map\");\n// Now use fd with bpf_map_lookup_elem / update / delete"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText(
                        "The map stays alive as long as at least one file descriptor or filesystem pin " +
                        "holds a reference. This enables persistent state that survives the restart of " +
                        "the user-space agent that loaded the eBPF program."
                    )
                }
            }

            item {
                SectionCard(title = "Map Types") {
                    BodyText("BPF_MAP_TYPE_HASH")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "• Generic hash table with arbitrary key/value types\n" +
                        "• O(1) average lookup; dynamic insertion up to max_entries\n" +
                        "• Entries that do not exist return NULL on lookup\n" +
                        "• Use for: per-connection state, per-PID counters, IP flow tracking"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("BPF_MAP_TYPE_ARRAY")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "• Fixed-size array; key is a u32 index (0 .. max_entries-1)\n" +
                        "• All slots pre-allocated and zero-initialized at map creation\n" +
                        "• Lookup always succeeds (no NULL return for valid index)\n" +
                        "• Use for: global counters, per-CPU statistics, config tables"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("BPF_MAP_TYPE_PERCPU_HASH / BPF_MAP_TYPE_PERCPU_ARRAY")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "• Each CPU maintains its own copy of every value\n" +
                        "• eBPF program reads/writes its CPU-local copy — no locking needed\n" +
                        "• User space reads all per-CPU values as an array and aggregates them\n" +
                        "• Use for: high-frequency counters where lock contention would be a problem"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("BPF_MAP_TYPE_RINGBUF")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "• Lock-free ring buffer; preferred for streaming events to user space\n" +
                        "• bpf_ringbuf_reserve() — reserve a slot (zero-copy)\n" +
                        "• bpf_ringbuf_submit() — publish the slot to user space\n" +
                        "• bpf_ringbuf_output() — copy data and submit in one call\n" +
                        "• User space polls with ring_buffer__poll() (libbpf)\n" +
                        "• More efficient than PERF_EVENT_ARRAY; introduced in Linux 5.8"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("BPF_MAP_TYPE_PERF_EVENT_ARRAY")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "• Older mechanism for streaming events to user space via perf\n" +
                        "• One entry per CPU; user space reads via perf_buffer__poll()\n" +
                        "• Still widely used; superseded by RINGBUF for new code"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("BPF_MAP_TYPE_LRU_HASH")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "• Hash table with Least-Recently-Used eviction when full\n" +
                        "• No need to manually delete old entries\n" +
                        "• Use for: connection tracking, DNS caches, flow tables"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("BPF_MAP_TYPE_PROG_ARRAY")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "• Array of eBPF program file descriptors\n" +
                        "• Used for tail calls: bpf_tail_call(ctx, &prog_array, index)\n" +
                        "• Tail call replaces the current program — no stack growth\n" +
                        "• Use for: dispatch tables, chaining multiple eBPF programs"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("BPF_MAP_TYPE_STACK_TRACE")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "• Stores kernel or user-space stack traces\n" +
                        "• Key: stack ID returned by bpf_get_stackid()\n" +
                        "• Value: array of return addresses forming the call stack\n" +
                        "• Use for: profiling, tracing which code paths trigger an event"
                    )
                }
            }

            item {
                SectionCard(title = "Persistence and Lifecycle") {
                    BodyText(
                        "Maps are reference-counted kernel objects. Their lifetime rules:"
                    )
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "Created  — by bpf(BPF_MAP_CREATE, ...) or via skeleton load\n" +
                        "Alive    — as long as at least one fd OR bpffs pin holds a ref\n" +
                        "Destroyed — when the last fd is closed AND all pins are removed\n" +
                        "\n" +
                        "Typical pattern for stateful agents:\n" +
                        "  1. Load BPF program + maps on startup\n" +
                        "  2. Pin maps to /sys/fs/bpf/\n" +
                        "  3. Agent can restart; map data survives in the kernel\n" +
                        "  4. New agent instance opens pinned map and resumes"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText(
                        "This is particularly useful for connection tracking, rate limiting, and " +
                        "security policy enforcement — state accumulated over time is not lost " +
                        "when the user-space controller restarts."
                    )
                }
            }

            item {
                SectionCard(title = "Concurrency Considerations") {
                    BodyText(
                        "eBPF programs run concurrently on multiple CPUs. Maps are not inherently " +
                        "atomic for multi-field updates. Options:"
                    )
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "Per-CPU maps (PERCPU_HASH / PERCPU_ARRAY):\n" +
                        "  Each CPU writes to its own slot — no contention at all\n" +
                        "  User space aggregates all per-CPU values\n" +
                        "  Best for: counters, histograms\n" +
                        "\n" +
                        "bpf_spin_lock (embedded in the value struct):\n" +
                        "  struct my_value {\n" +
                        "      struct bpf_spin_lock lock;\n" +
                        "      __u64 counter;\n" +
                        "      __u64 bytes;\n" +
                        "  };\n" +
                        "  bpf_spin_lock(&val->lock);\n" +
                        "  val->counter++; val->bytes += size;\n" +
                        "  bpf_spin_unlock(&val->lock);\n" +
                        "  Requires BTF-annotated map value types\n" +
                        "\n" +
                        "Ring buffer:\n" +
                        "  Lock-free by design; safe for multi-producer single-consumer"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
