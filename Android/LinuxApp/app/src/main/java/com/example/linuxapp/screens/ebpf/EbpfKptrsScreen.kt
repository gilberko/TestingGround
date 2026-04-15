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
fun EbpfKptrsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "eBPF — kptrs",
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
                SectionCard(title = "What Are kptrs?") {
                    BodyText(
                        "A kptr (kernel pointer) is a pointer to a kernel object stored inside " +
                        "a BPF map value. Introduced in kernel 5.19, kptrs let eBPF programs " +
                        "safely hold references to kernel objects (task_struct, sk, cgroup, etc.) " +
                        "across program invocations."
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText(
                        "Before kptrs, eBPF programs could only store scalar integers in maps. " +
                        "Storing a raw pointer was prohibited because the verifier had no way to " +
                        "track the pointer's lifetime — it could become a dangling pointer between " +
                        "invocations, leading to use-after-free bugs."
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText(
                        "A kptr field in a map struct tells the verifier: 'this slot holds a " +
                        "pointer to a specific BTF-typed kernel object; enforce ownership rules " +
                        "so there are no leaks and no use-after-free.'"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Two kinds of kptrs:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "__kptr      — non-owning reference (borrowed pointer)\n" +
                        "              the map does NOT hold a refcount on the object\n" +
                        "\n" +
                        "__kptr_ref  — owning reference (reference-counted pointer)\n" +
                        "              the map holds a refcount; must be explicitly released"
                    )
                }
            }

            item {
                SectionCard(title = "Declaring a kptr in a Map — task_struct Example") {
                    BodyText(
                        "kptr fields are declared inside the map's value struct using the " +
                        "__kptr or __kptr_ref BTF type-tag annotation. The map must use a " +
                        "BTF-typed value (struct), not a plain scalar."
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Non-owning kptr storing a task_struct pointer:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "/* BPF program side (uses vmlinux.h or explicit forward decl) */\n" +
                        "\n" +
                        "struct task_kptr_val {\n" +
                        "    struct task_struct __kptr *task;  /* borrowed ref */\n" +
                        "};\n" +
                        "\n" +
                        "struct {\n" +
                        "    __uint(type, BPF_MAP_TYPE_HASH);\n" +
                        "    __uint(max_entries, 1024);\n" +
                        "    __type(key,   u32);\n" +
                        "    __type(value, struct task_kptr_val);\n" +
                        "} my_map SEC(\".maps\");\n" +
                        "\n" +
                        "SEC(\"tp/sched/sched_switch\")\n" +
                        "int handle_sched_switch(struct sched_switch_args *ctx)\n" +
                        "{\n" +
                        "    u32 pid = bpf_get_current_pid_tgid() >> 32;\n" +
                        "    struct task_struct *task = bpf_get_current_task_btf();\n" +
                        "    struct task_kptr_val val = { .task = task };\n" +
                        "    bpf_map_update_elem(&my_map, &pid, &val, BPF_ANY);\n" +
                        "    return 0;\n" +
                        "}"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText(
                        "The __kptr annotation is a BTF type tag. Tools like pahole and bpftool " +
                        "record it in the BTF metadata so the verifier understands the field's " +
                        "ownership semantics at program load time."
                    )
                }
            }

            item {
                SectionCard(title = "Owning vs Non-Owning kptrs") {
                    BodyText("Non-owning kptr  (__kptr):")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "• The map does NOT increment the object's reference count.\n" +
                        "• The pointer is only valid while the object is kept alive by\n" +
                        "  some other mechanism (e.g. RCU read lock, held reference).\n" +
                        "• Used for short-lived associations where you trust the object\n" +
                        "  won't disappear while the map entry exists.\n" +
                        "• Example: storing the current task pointer during a kprobe."
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Owning kptr  (__kptr_ref):")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "• The map DOES hold a reference; the kernel object's refcount\n" +
                        "  is incremented when the pointer is stored.\n" +
                        "• The object is guaranteed alive as long as the map entry exists.\n" +
                        "• Must be explicitly released before or when the map entry is\n" +
                        "  deleted — the kernel calls the type's release kfunc automatically\n" +
                        "  on map entry deletion.\n" +
                        "• Example: bpf_task_acquire() returns an owning task ptr."
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText(
                        "The verifier tracks ownership across all control flow paths. If you " +
                        "acquire an owning kptr in one branch but fail to store or release it " +
                        "in another, the program is rejected at load time."
                    )
                }
            }

            item {
                SectionCard(title = "The Verifier's Role — No Leaks, No Use-After-Free") {
                    BodyText("The BPF verifier actively enforces three invariants for kptrs:")
                    Spacer(Modifier.height(8.dp))
                    BodyText("1. Leak prevention:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "Every owning kptr acquired during a program invocation must be\n" +
                        "either stored into a map field  OR  released before the program\n" +
                        "exits. If any execution path ends without doing one of those two\n" +
                        "things, the verifier rejects the program at load time.\n" +
                        "\n" +
                        "Error example:\n" +
                        "  struct task_struct *t = bpf_task_acquire(task);\n" +
                        "  /* forgot to store or release t — verifier rejects */"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("2. Use-after-free prevention:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "Once a kptr is released (via bpf_task_release, bpf_kptr_xchg,\n" +
                        "or map deletion), the verifier marks that register as invalid.\n" +
                        "Any subsequent dereference of that register causes the verifier\n" +
                        "to reject the program.\n" +
                        "\n" +
                        "bpf_task_release(t);\n" +
                        "printk(\"%s\", t->comm);  /* rejected — t is invalid after release */"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("3. Type safety:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "The verifier knows the BTF type of every kptr. You cannot cast\n" +
                        "a task_struct kptr to a sock kptr or any other type.\n" +
                        "Type mismatches are rejected at load time, not at runtime."
                    )
                }
            }

            item {
                SectionCard(title = "Do kptrs Require BTF?") {
                    BodyText(
                        "Yes — BTF is mandatory for kptrs. There is no way to use kptrs " +
                        "without it."
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Requirements:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "Kernel config:\n" +
                        "  CONFIG_DEBUG_INFO_BTF=y   # kernel exports BTF for all types\n" +
                        "\n" +
                        "BPF program:\n" +
                        "  • Use vmlinux.h (generated by bpftool btf dump) for kernel types,\n" +
                        "    or include explicit struct forward declarations with BTF tags.\n" +
                        "  • Compile with clang -g (emits BTF for the BPF object).\n" +
                        "\n" +
                        "Why BTF is needed:\n" +
                        "  The verifier must know the exact type of the kptr field to:\n" +
                        "  • Find the correct acquire/release kfuncs for the type\n" +
                        "  • Enforce that only compatible types are stored\n" +
                        "  • Automatically call the release kfunc on map entry deletion"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Verify BTF availability:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "bpftool btf dump file /sys/kernel/btf/vmlinux | grep task_struct\n" +
                        "# Should show the full task_struct type definition"
                    )
                }
            }

            item {
                SectionCard(title = "bpf_kptr_xchg — Atomic Exchange") {
                    BodyText(
                        "bpf_kptr_xchg() is the primary way to read and replace a kptr stored " +
                        "in a map. It performs an atomic compare-and-swap: it stores the new " +
                        "pointer into the map slot and returns the old pointer."
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Signature:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "void *bpf_kptr_xchg(void *map_val, void *ptr);\n" +
                        "\n" +
                        "  map_val — pointer to the kptr field inside a map value\n" +
                        "  ptr     — new pointer to store (or NULL to clear)\n" +
                        "  returns — the old pointer that was in the slot"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText(
                        "This is the ONLY way to safely read-and-replace a kptr in a map. " +
                        "Direct assignment (val->task = new_task) is rejected by the verifier " +
                        "because it would leak the old pointer."
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Example — swap an owning task kptr:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "struct task_kptr_val {\n" +
                        "    struct task_struct __kptr_ref *task;  /* owning ref */\n" +
                        "};\n" +
                        "\n" +
                        "SEC(\"kprobe/do_sys_open\")\n" +
                        "int track_opener(struct pt_regs *ctx)\n" +
                        "{\n" +
                        "    u32 key = 0;\n" +
                        "    struct task_kptr_val *val;\n" +
                        "    struct task_struct *new_task, *old_task;\n" +
                        "\n" +
                        "    val = bpf_map_lookup_elem(&my_map, &key);\n" +
                        "    if (!val)\n" +
                        "        return 0;\n" +
                        "\n" +
                        "    /* Acquire an owning reference to current task */\n" +
                        "    new_task = bpf_task_acquire(bpf_get_current_task_btf());\n" +
                        "    if (!new_task)\n" +
                        "        return 0;\n" +
                        "\n" +
                        "    /* Atomically store new, get back old */\n" +
                        "    old_task = bpf_kptr_xchg(&val->task, new_task);\n" +
                        "\n" +
                        "    /* Release the old owning reference */\n" +
                        "    if (old_task)\n" +
                        "        bpf_task_release(old_task);\n" +
                        "\n" +
                        "    return 0;\n" +
                        "}"
                    )
                }
            }

            item {
                SectionCard(title = "__kptr and __kptr_ref Annotations") {
                    BodyText(
                        "Yes — you are remembering correctly. These are BTF type-tag annotations " +
                        "placed on pointer fields in map value structs. They are part of the " +
                        "kernel's BPF CO-RE (Compile Once, Run Everywhere) infrastructure."
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("The two annotations:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "__kptr      — non-owning (borrowed) reference\n" +
                        "              map field does NOT hold a refcount\n" +
                        "              pointer may become invalid if object is freed elsewhere\n" +
                        "\n" +
                        "__kptr_ref  — owning (reference-counted) reference\n" +
                        "              map field DOES hold a refcount\n" +
                        "              object stays alive as long as map entry exists\n" +
                        "              must be released (via kfunc) before map entry is deleted"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Example showing both in the same struct:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "struct my_val {\n" +
                        "    /* borrowed: valid only during RCU read-side critical section */\n" +
                        "    struct task_struct __kptr     *borrowed_task;\n" +
                        "\n" +
                        "    /* owned: refcount held, object guaranteed alive */\n" +
                        "    struct task_struct __kptr_ref *owned_task;\n" +
                        "};"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("What these annotations actually are:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "Under the hood, these expand to:\n" +
                        "  __attribute__((btf_type_tag(\"kptr\")))\n" +
                        "  __attribute__((btf_type_tag(\"kptr_ref\")))\n" +
                        "\n" +
                        "The compiler emits these as BTF annotations in the object file.\n" +
                        "The BPF verifier reads them at program load time to determine\n" +
                        "ownership semantics for each map field."
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
