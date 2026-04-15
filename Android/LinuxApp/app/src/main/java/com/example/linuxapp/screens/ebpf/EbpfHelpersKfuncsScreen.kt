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
fun EbpfHelpersKfuncsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "BPF Helpers & kfuncs",
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
                SectionCard(title = "BPF Helpers — The Classic Extension Point") {
                    BodyText(
                        "BPF helpers are kernel functions that eBPF programs can call. They are " +
                        "identified by a stable 32-bit numeric ID in the bytecode (enum bpf_func_id), " +
                        "making them part of the stable Linux UAPI — once added, a helper is never " +
                        "removed or changed in an incompatible way."
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Where they live in the kernel:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "include/uapi/linux/bpf.h    — enum bpf_func_id (the numeric IDs)\n" +
                        "kernel/bpf/helpers.c        — core helpers (maps, ringbuf, etc.)\n" +
                        "kernel/trace/bpf_trace.c    — tracing helpers (probe_read, etc.)\n" +
                        "net/core/filter.c           — networking helpers (sk_storage, etc.)"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("How the verifier handles helpers:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "When the verifier sees a BPF_CALL instruction with a helper ID, it:\n" +
                        "1. Looks up the bpf_func_proto for that ID in the program type's\n" +
                        "   get_func_proto() callback.\n" +
                        "2. Checks the calling program type is allowed to use this helper.\n" +
                        "3. Validates each argument type matches the proto (ARG_PTR_TO_MAP,\n" +
                        "   ARG_ANYTHING, ARG_PTR_TO_MEM, etc.).\n" +
                        "4. Records the return type for subsequent instructions."
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Common helpers:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "bpf_map_lookup_elem()        — look up a map entry\n" +
                        "bpf_map_update_elem()        — insert/update a map entry\n" +
                        "bpf_get_current_pid_tgid()   — PID/TGID of current task\n" +
                        "bpf_probe_read_kernel()      — safe kernel memory read\n" +
                        "bpf_ringbuf_submit()         — submit a ring buffer entry\n" +
                        "bpf_ktime_get_ns()           — monotonic nanosecond timestamp\n" +
                        "bpf_perf_event_output()      — send data to perf ring buffer"
                    )
                }
            }

            item {
                SectionCard(title = "kfuncs — The Modern Extension Point") {
                    BodyText(
                        "kfuncs (kernel functions for BPF) are regular kernel functions exported " +
                        "to eBPF programs by registering their BTF IDs. Available since kernel 5.13, " +
                        "widely adopted from 5.15 onwards."
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Key differences from helpers:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "• Identified by BTF name, not a numeric ID.\n" +
                        "  → No stable UAPI guarantee; kfuncs can change between kernel versions.\n" +
                        "\n" +
                        "• Full BTF type checking on all arguments and return values.\n" +
                        "  → No hand-written bpf_func_proto needed; types come from BTF.\n" +
                        "\n" +
                        "• Can return and accept kptrs (owning/non-owning references).\n" +
                        "  → Enables safe access to typed kernel objects from BPF.\n" +
                        "\n" +
                        "• Can live in a LOADABLE KERNEL MODULE.\n" +
                        "  → Helpers can only exist in the kernel proper; kfuncs can be\n" +
                        "     added without touching core kernel code."
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Examples from the kernel:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "bpf_task_acquire()      — acquire owning ref to task_struct\n" +
                        "bpf_task_release()      — release owning ref to task_struct\n" +
                        "bpf_sk_storage_get()    — per-socket BPF storage\n" +
                        "bpf_list_push_front()   — push to a BPF-managed linked list\n" +
                        "bpf_rbtree_add()        — insert into a BPF-managed red-black tree\n" +
                        "bpf_cpumask_create()    — allocate a BPF cpumask object"
                    )
                }
            }

            item {
                SectionCard(title = "Helpers vs kfuncs — When to Use Which") {
                    BodyText("Comparison at a glance:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "                    BPF Helpers          kfuncs\n" +
                        "Stability           Stable UAPI          No stability guarantee\n" +
                        "                    (never removed)      (can change per release)\n" +
                        "\n" +
                        "Where defined       Kernel only          Kernel OR loadable module\n" +
                        "\n" +
                        "Arg type checking   Hard-coded protos    Full BTF type checking\n" +
                        "\n" +
                        "Can return kptr?    No                   Yes (with KF_ACQUIRE)\n" +
                        "Can accept kptr?    No                   Yes (with KF_TRUSTED_ARGS)\n" +
                        "\n" +
                        "Portability         High (stable IDs)    Lower (BTF name lookup)"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Rule of thumb:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "Use BPF helpers when you need portability across kernel versions\n" +
                        "and are working with basic maps, perf output, or probing.\n" +
                        "\n" +
                        "Use kfuncs when:\n" +
                        "  • Working with typed kernel objects (task, sk, cgroup, etc.)\n" +
                        "  • Using BPF data structures (lists, rbtrees, kptrs)\n" +
                        "  • Building custom kernel extensions in a loadable module\n" +
                        "  • You need operations not yet available as a stable helper"
                    )
                }
            }

            item {
                SectionCard(title = "Can I Write New BPF Helpers?") {
                    BodyText(
                        "Yes, technically — but it requires patching the core kernel and is " +
                        "rarely done today. New kernel-BPF interfaces use kfuncs instead."
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Steps to add a new helper (kernel patch):")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "1. Add entry to enum bpf_func_id in:\n" +
                        "     include/uapi/linux/bpf.h\n" +
                        "\n" +
                        "2. Write the implementation using the BPF_CALL_N macro:\n" +
                        "     BPF_CALL_2(bpf_my_helper, u64, val, u64, flags) { ... }\n" +
                        "\n" +
                        "3. Define a bpf_func_proto describing arg and return types:\n" +
                        "     const struct bpf_func_proto bpf_my_helper_proto = {\n" +
                        "         .func      = bpf_my_helper,\n" +
                        "         .gpl_only  = false,\n" +
                        "         .ret_type  = RET_INTEGER,\n" +
                        "         .arg1_type = ARG_ANYTHING,\n" +
                        "         .arg2_type = ARG_ANYTHING,\n" +
                        "     };\n" +
                        "\n" +
                        "4. Register it in the relevant program type's get_func_proto():\n" +
                        "     case BPF_FUNC_my_helper: return &bpf_my_helper_proto;\n" +
                        "\n" +
                        "5. Mirror the declaration in tools/include/uapi/linux/bpf.h\n" +
                        "   for userspace (bpftool, libbpf)."
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText(
                        "This requires a kernel upstream patch with review from the BPF " +
                        "maintainers. Because of the UAPI stability requirement, the bar is " +
                        "high. For private or module-based extensions, always use kfuncs."
                    )
                }
            }

            item {
                SectionCard(title = "Can I Write New kfuncs? (Including in a Module?)") {
                    BodyText(
                        "Yes — and this is the recommended approach for extending BPF with " +
                        "custom kernel interfaces. kfuncs can live in a loadable kernel module, " +
                        "meaning you don't need to touch the core kernel at all."
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Steps to implement a kfunc in a module:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "1. Write the function and annotate with __bpf_kfunc:\n" +
                        "     __bpf_kfunc int bpf_my_fn(int a, int b) { return a + b; }\n" +
                        "   (__bpf_kfunc = noinline + notrace + visibility attributes)\n" +
                        "\n" +
                        "2. Declare a BTF set of kfunc IDs:\n" +
                        "     BTF_SET8_START(my_kfunc_ids)\n" +
                        "     BTF_ID_FLAGS(func, bpf_my_fn)\n" +
                        "     BTF_SET8_END(my_kfunc_ids)\n" +
                        "\n" +
                        "3. Create a btf_kfunc_id_set:\n" +
                        "     static const struct btf_kfunc_id_set my_kfunc_set = {\n" +
                        "         .owner = THIS_MODULE,\n" +
                        "         .set   = &my_kfunc_ids,\n" +
                        "     };\n" +
                        "\n" +
                        "4. Register in module_init:\n" +
                        "     register_btf_kfunc_id_set(BPF_PROG_TYPE_TRACING, &my_kfunc_set);"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText(
                        "Once the module is loaded, BPF programs compiled against the " +
                        "correct BTF can call the kfunc by name. When the module is unloaded, " +
                        "the kfunc is automatically unregistered."
                    )
                }
            }

            item {
                SectionCard(title = "Implementing a kfunc — Full Example") {
                    BodyText("Complete loadable module exposing a simple kfunc to BPF tracing programs:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "/* my_kfunc_module.c */\n" +
                        "#include <linux/module.h>\n" +
                        "#include <linux/bpf.h>\n" +
                        "#include <linux/btf.h>\n" +
                        "#include <linux/btf_ids.h>\n" +
                        "\n" +
                        "__bpf_kfunc int bpf_my_add(int a, int b)\n" +
                        "{\n" +
                        "    return a + b;\n" +
                        "}\n" +
                        "\n" +
                        "BTF_SET8_START(my_kfunc_ids)\n" +
                        "BTF_ID_FLAGS(func, bpf_my_add)\n" +
                        "BTF_SET8_END(my_kfunc_ids)\n" +
                        "\n" +
                        "static const struct btf_kfunc_id_set my_kfunc_set = {\n" +
                        "    .owner = THIS_MODULE,\n" +
                        "    .set   = &my_kfunc_ids,\n" +
                        "};\n" +
                        "\n" +
                        "static int __init my_kfunc_init(void)\n" +
                        "{\n" +
                        "    return register_btf_kfunc_id_set(BPF_PROG_TYPE_TRACING,\n" +
                        "                                     &my_kfunc_set);\n" +
                        "}\n" +
                        "module_init(my_kfunc_init);\n" +
                        "MODULE_LICENSE(\"GPL\");"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("BPF program side — calling the kfunc:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "/* Declare the kfunc with __ksym so libbpf resolves it by BTF name */\n" +
                        "extern int bpf_my_add(int a, int b) __ksym;\n" +
                        "\n" +
                        "SEC(\"tp/syscalls/sys_enter_read\")\n" +
                        "int handle_read(void *ctx)\n" +
                        "{\n" +
                        "    int result = bpf_my_add(3, 4);  /* calls the kfunc */\n" +
                        "    bpf_printk(\"result = %d\\n\", result);\n" +
                        "    return 0;\n" +
                        "}"
                    )
                }
            }

            item {
                SectionCard(title = "Implementing a BPF Helper — Full Example") {
                    BodyText("Kernel-side implementation (requires kernel patch):")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "/* kernel/bpf/helpers.c */\n" +
                        "\n" +
                        "BPF_CALL_2(bpf_my_helper, u64, val, struct bpf_map *, map)\n" +
                        "{\n" +
                        "    /* Example: double the value */\n" +
                        "    return val * 2;\n" +
                        "}\n" +
                        "\n" +
                        "const struct bpf_func_proto bpf_my_helper_proto = {\n" +
                        "    .func      = bpf_my_helper,\n" +
                        "    .gpl_only  = false,\n" +
                        "    .ret_type  = RET_INTEGER,\n" +
                        "    .arg1_type = ARG_ANYTHING,\n" +
                        "    .arg2_type = ARG_CONST_MAP_PTR,\n" +
                        "};\n" +
                        "\n" +
                        "/* Wire it in get_func_proto for, e.g., tracing programs:\n" +
                        "   (in kernel/trace/bpf_trace.c)\n" +
                        "   case BPF_FUNC_my_helper:\n" +
                        "       return &bpf_my_helper_proto;"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("BPF program side:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "SEC(\"kprobe/vfs_read\")\n" +
                        "int my_probe(struct pt_regs *ctx)\n" +
                        "{\n" +
                        "    u64 x = 21;\n" +
                        "    u64 result = bpf_my_helper(x, &my_map);\n" +
                        "    /* result == 42 */\n" +
                        "    return 0;\n" +
                        "}"
                    )
                }
            }

            item {
                SectionCard(title = "KF_ Flags — Controlling When kfuncs Can Be Called") {
                    BodyText(
                        "KF_ flags are OR'd into the second argument of BTF_ID_FLAGS(). " +
                        "They tell the verifier what the kfunc does with its arguments and " +
                        "return value, and which program types or contexts are allowed to call it."
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Ownership and reference flags:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "KF_ACQUIRE       — returns an owning kptr; verifier ensures\n" +
                        "                   the caller stores or releases the returned ptr.\n" +
                        "KF_RELEASE       — consumes (releases) an owning kptr argument;\n" +
                        "                   the argument register is invalidated after the call.\n" +
                        "KF_RET_NULL      — return value may be NULL; caller must check\n" +
                        "                   before dereferencing (verifier enforces this).\n" +
                        "KF_TRUSTED_ARGS  — all pointer arguments must be 'trusted'\n" +
                        "                   (acquired via KF_ACQUIRE, not raw pointers)."
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Context and permission flags:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "KF_SLEEPABLE     — kfunc may sleep (e.g. kmalloc with GFP_KERNEL).\n" +
                        "                   Only callable from sleepable BPF programs\n" +
                        "                   (loaded with BPF_F_SLEEPABLE flag).\n" +
                        "                   Cannot be called from interrupt context.\n" +
                        "\n" +
                        "KF_DESTRUCTIVE   — dangerous/destructive operation.\n" +
                        "                   Requires CAP_SYS_BOOT in addition to CAP_BPF.\n" +
                        "                   Example: bpf_cpumask_destroy()."
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Example registration with flags:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "BTF_SET8_START(task_kfunc_ids)\n" +
                        "BTF_ID_FLAGS(func, bpf_task_acquire,  KF_ACQUIRE | KF_TRUSTED_ARGS)\n" +
                        "BTF_ID_FLAGS(func, bpf_task_release,  KF_RELEASE)\n" +
                        "BTF_ID_FLAGS(func, bpf_task_from_pid, KF_ACQUIRE | KF_RET_NULL)\n" +
                        "BTF_ID_FLAGS(func, bpf_my_sleepy_fn,  KF_SLEEPABLE | KF_TRUSTED_ARGS)\n" +
                        "BTF_SET8_END(task_kfunc_ids)"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Sleepable BPF programs — what they are:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "Sleepable programs run in a context where sleeping is safe\n" +
                        "(not in hard IRQ or NMI context). Supported types include:\n" +
                        "  • BPF_PROG_TYPE_LSM       (with sleepable hooks)\n" +
                        "  • BPF_PROG_TYPE_TRACING   (fentry/fexit on sleepable functions)\n" +
                        "  • BPF_PROG_TYPE_ITER\n" +
                        "\n" +
                        "Load with BPF_F_SLEEPABLE flag:\n" +
                        "  bpf_prog_load(..., .prog_flags = BPF_F_SLEEPABLE, ...)\n" +
                        "\n" +
                        "libbpf skeleton usage:\n" +
                        "  SEC(\"fentry.s/vfs_read\")   /* .s suffix = sleepable */\n" +
                        "  int BPF_PROG(my_fentry, ...) { ... }"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
