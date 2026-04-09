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
fun BtfScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "eBPF — BTF & CO-RE",
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
                SectionCard(title = "What is BTF (BPF Type Format)") {
                    BodyText("BTF (BPF Type Format) is a compact binary format that describes kernel and BPF program type information — structs, unions, enums, typedefs, and function signatures. It was inspired by DWARF (the debug-info format used by compilers) but is orders of magnitude smaller.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("BTF is embedded in two places:")
                    CodeBlock(
                        """/sys/kernel/btf/vmlinux   — the running kernel's full type info
                            |                           (all structs, enums, typedefs)
                            |
                            |BPF ELF object (.bpf.o)  — type info for the BPF program itself
                            |                           (what structs it accesses, arg types)""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Introduced in Linux 4.18. Used by the verifier (to check field accesses), libbpf (to relocate field offsets at load time), and bpftool (to pretty-print maps and programs).")
                }
            }
            item {
                SectionCard(title = "Why BTF is Needed") {
                    BodyText("Without BTF, an eBPF program that reads a kernel struct field — say task_struct->pid — must be compiled against the exact kernel headers of the target machine.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The problem: different kernel versions add, remove, or reorder struct fields. A field at offset 48 on kernel 5.15 might be at offset 56 on kernel 6.1. If the BPF program hardcodes the offset at compile time, it reads garbage — or crashes — on a different kernel version.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("BTF solves this by encoding field names and types in both the kernel and the BPF object. At load time, the runtime can look up the real offset by field name, not hardcoded byte position.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Before BTF: one BPF binary per kernel version. With BTF + CO-RE: one binary runs everywhere.")
                }
            }
            item {
                SectionCard(title = "CO-RE — Compile Once, Run Everywhere") {
                    BodyText("CO-RE is the principle enabled by BTF: compile your eBPF program once with Clang against a vmlinux.h snapshot, and libbpf will make it run correctly on any kernel version that has BTF support.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The three ingredients of CO-RE:")
                    CodeBlock(
                        """1. vmlinux.h
                            |   A single header file containing all kernel type definitions,
                            |   generated from the running kernel's BTF. Replace all #include
                            |   <linux/*.h> with just: #include "vmlinux.h"
                            |
                            |2. BPF_CORE_READ() macro
                            |   Use this instead of direct -> dereference. It emits BTF
                            |   relocation records so libbpf can patch the offset at load time.
                            |
                            |3. libbpf at load time
                            |   Reads relocation records from the BPF object, looks up the
                            |   field in the running kernel's BTF, patches the instruction.""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Generate vmlinux.h:")
                    CodeBlock("bpftool btf dump file /sys/kernel/btf/vmlinux format c > vmlinux.h")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("With CO-RE, the same compiled binary works across kernel versions 5.x and 6.x without recompilation — as long as the accessed fields still exist.")
                }
            }
            item {
                SectionCard(title = "How CO-RE Relocations Work") {
                    BodyText("When you write BPF_CORE_READ(task, pid), Clang does not emit a hardcoded byte offset. Instead it emits a BTF relocation record in the BPF ELF object: \"I want field 'pid' of type 'task_struct'\".")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("At load time, libbpf:")
                    CodeBlock(
                        """1. Reads the BPF object's BTF relocation records.
                            |
                            |2. Opens /sys/kernel/btf/vmlinux to get the running
                            |   kernel's type info.
                            |
                            |3. Finds 'task_struct' in the kernel BTF.
                            |
                            |4. Looks up the byte offset of 'pid' in that struct
                            |   for THIS specific kernel version.
                            |
                            |5. Patches the BPF instruction with the real offset
                            |   before loading into the kernel.""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("If a field no longer exists in the running kernel, libbpf can be told to use a default value (zero) rather than failing — useful for optional fields introduced in newer kernels.")
                }
            }
            item {
                SectionCard(title = "Key CO-RE Macros") {
                    BodyText("All CO-RE macros are defined in <bpf/bpf_core_read.h> (included via bpf_helpers.h):")
                    CodeBlock(
                        """// Read a field — emits relocation record
                            |pid_t p = BPF_CORE_READ(task, pid);
                            |
                            |// Read into an existing variable
                            |pid_t p;
                            |BPF_CORE_READ_INTO(&p, task, pid);
                            |
                            |// Read nested field
                            |u64 addr = BPF_CORE_READ(task, mm, mmap_base);
                            |
                            |// Check if a field exists on the running kernel
                            |// Returns 1 if present, 0 if absent
                            |if (bpf_core_field_exists(struct task_struct, exit_code_status)) {
                            |    // field exists on this kernel
                            |}
                            |
                            |// Get the size of a field on the running kernel
                            |__u32 sz = bpf_core_field_size(struct task_struct, pid);""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Never use direct -> dereference in a CO-RE program — it will hardcode the compile-time offset and break on other kernels. Always use BPF_CORE_READ.")
                }
            }
            item {
                SectionCard(title = "Inspecting BTF with bpftool") {
                    BodyText("bpftool can dump BTF in human-readable form — useful for understanding what type info is available and for debugging CO-RE issues:")
                    CodeBlock(
                        """# Generate vmlinux.h from the running kernel
                            |bpftool btf dump file /sys/kernel/btf/vmlinux format c \
                            |    > vmlinux.h
                            |
                            |# Dump all types in raw text form
                            |bpftool btf dump file /sys/kernel/btf/vmlinux format raw \
                            |    | grep -A5 "task_struct"
                            |
                            |# Show BTF info for a loaded BPF program
                            |sudo bpftool prog show          # find the prog id
                            |sudo bpftool btf dump id <id>  # dump its BTF
                            |
                            |# Show BTF for a pinned BPF map
                            |sudo bpftool map show
                            |sudo bpftool btf dump id <map_btf_id>
                            |
                            |# Check if the kernel has BTF at all
                            |ls /sys/kernel/btf/vmlinux     # must exist
                            |# If missing: kernel was built without CONFIG_DEBUG_INFO_BTF=y""".trimMargin()
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
