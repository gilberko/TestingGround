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
fun EbpfSleepableScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Sleepable eBPF Programs",
                        color = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontSize = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
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
                SectionCard(title = "What Are Sleepable eBPF Programs") {
                    BodyText(
                        "Before Linux 5.10, ALL eBPF programs ran as if in atomic context — no sleeping, " +
                        "no page faults, no blocking locks. This was a hard constraint enforced by the verifier."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Linux 5.10 (December 2020), authored by Alexei Starovoitov, introduced the " +
                        "BPF_F_SLEEPABLE flag. Programs loaded with this flag are permitted to block — " +
                        "meaning the kernel can sleep the thread running the eBPF program if a page fault " +
                        "or blocking lock acquisition is needed."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Why it was needed:")
                    CodeBlock(
                        """Some helpers — like bpf_copy_from_user() — must read from user-space memory.
                            |User-space pages can be swapped out. Reading a swapped page causes a page fault,
                            |and the kernel must sleep to bring the page back in from disk.
                            |
                            |Without BPF_F_SLEEPABLE, helpers that may trigger a page fault are completely
                            |forbidden by the verifier. With it, they become available.""".trimMargin()
                    )
                }
            }
            item {
                SectionCard(title = "The .s Notation (SEC Macro)") {
                    BodyText(
                        "libbpf uses a naming convention in the SEC() macro to signal that a program " +
                        "should be loaded as sleepable. The .s suffix in the section name is the key:"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """// Non-sleepable — runs in standard atomic context
                            |SEC("lsm/file_open")
                            |int BPF_PROG(check_file_open, struct file *file) {
                            |    return 0;
                            |}
                            |
                            |// Sleepable — allowed to block
                            |SEC("lsm.s/file_open")
                            |int BPF_PROG(check_file_open_sleep, struct file *file) {
                            |    // May now call bpf_copy_from_user(), bpf_find_vma(), etc.
                            |    return 0;
                            |}""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "When libbpf parses the .s suffix, it sets BPF_F_SLEEPABLE in the flags field of " +
                        "the bpf() syscall's BPF_PROG_LOAD attributes. The kernel verifier then applies " +
                        "sleepable-context validation rules."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("SEC prefixes that accept the .s sleepable suffix (added progressively):")
                    CodeBlock(
                        """lsm.s/      — BPF LSM hooks (introduced with sleepable in Linux 5.10)
                            |fentry.s/   — function entry probes
                            |fexit.s/    — function exit probes
                            |iter.s/     — BPF iterator programs
                            |uprobe.s/   — user-space probes
                            |kprobe.s/   — kernel probes (added later)""".trimMargin()
                    )
                }
            }
            item {
                SectionCard(title = "Verifier Checks at Load Time") {
                    BodyText(
                        "The verifier performs two static checks when a program is loaded with " +
                        "BPF_F_SLEEPABLE. Both happen at load time — not at runtime."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """Check 1 — Program type supports sleepable execution
                            |  The program type must have the BPF_F_SLEEPABLE capability registered.
                            |  XDP, socket filter, and TC classifier (in most configs) do not support it.
                            |  Attempting to load them as sleepable returns EINVAL.
                            |
                            |Check 2 — Attach point is not in atomic context
                            |  Even for a supported type (e.g. lsm), some specific hooks are called
                            |  from interrupt or atomic context. Attaching a sleepable program to such
                            |  a hook also fails at load time with EINVAL.
                            |  Example: an LSM hook called from a hardirq handler cannot host
                            |  a sleepable program, even though lsm.s/ is valid in general.""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "These are conservative, static refusals. The verifier does not track whether " +
                        "the program will actually sleep at runtime — only whether sleeping is permitted " +
                        "at all for that program type and attach point."
                    )
                }
            }
            item {
                SectionCard(title = "XDP Cannot Sleep — The Fast-Path Reason") {
                    BodyText(
                        "XDP programs run in the NAPI softirq context. This is atomic context: no sleeping, " +
                        "no page faults, no blocking lock acquisitions are possible."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Why XDP is designed this way:")
                    CodeBlock(
                        """XDP's entire value proposition is line-rate packet processing:
                            |  20–40 million packets per second (20–40M pps) on commodity hardware.
                            |
                            |A single sleep event would stall the NAPI polling loop for that CPU,
                            |causing packet drops that completely defeat the purpose of using XDP.
                            |The no-sleep constraint is not a limitation — it is the feature.""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "The verifier enforces this based on the program type's registered flags. Attempting " +
                        "to load an XDP program with BPF_F_SLEEPABLE causes the verifier to reject it with EINVAL."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Same restriction applies to:")
                    CodeBlock(
                        """- Socket filters (SO_ATTACH_FILTER / SO_ATTACH_BPF)
                            |- TC classifiers (cls_bpf) in most configurations
                            |
                            |These also run in or close to softirq context where sleeping is forbidden.
                            |The verifier checks the program type's capability flags and rejects
                            |BPF_F_SLEEPABLE for any type that does not support it.""".trimMargin()
                    )
                }
            }
            item {
                SectionCard(title = "Sleepable BPF Helpers") {
                    BodyText(
                        "Only sleepable programs can call helpers marked internally as may_sleep. " +
                        "The verifier enforces this: calling a sleepable-only helper from a non-sleepable " +
                        "program fails verification at load time."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Key helpers that require a sleepable program context:")
                    CodeBlock(
                        """bpf_copy_from_user(void *dst, u32 size, const void *user_ptr)
                            |  Copies from user space. May trigger a page fault if the user page is
                            |  swapped out, requiring the kernel to sleep to fetch it from disk.
                            |
                            |bpf_copy_from_user_task(void *dst, u32 size,
                            |                        const void *user_ptr,
                            |                        struct task_struct *tsk, u64 flags)
                            |  Same, but reads from another task's address space. (Linux 5.15)
                            |
                            |bpf_find_vma(struct task_struct *task, u64 addr,
                            |             void *callback_fn, void *callback_ctx, u64 flags)
                            |  Walks VMAs to find the one covering 'addr'. Acquires mmap_read_lock,
                            |  a read semaphore that can sleep.
                            |
                            |bpf_task_storage_get() / bpf_inode_storage_get()
                            |  With BPF_LOCAL_STORAGE_GET_F_CREATE in sleepable context: may allocate
                            |  with GFP_KERNEL, which can sleep.
                            |
                            |kfuncs marked KF_SLEEPABLE  (covered in the next section)""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Note: helpers like bpf_ringbuf_reserve() and bpf_ringbuf_submit() work in both " +
                        "sleepable and non-sleepable contexts — they use non-blocking spin logic internally."
                    )
                }
            }
            item {
                SectionCard(title = "KF_SLEEPABLE kfuncs") {
                    BodyText(
                        "A kfunc annotated with KF_SLEEPABLE can only be called from a program loaded with " +
                        "BPF_F_SLEEPABLE. The verifier enforces all combinations at load time:"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """KF_SLEEPABLE kfunc  +  non-sleepable program  →  verifier rejects (EINVAL)
                            |KF_SLEEPABLE kfunc  +  sleepable program     →  allowed
                            |Non-sleepable kfunc +  sleepable program     →  allowed (stricter → looser is fine)
                            |Non-sleepable kfunc +  non-sleepable program →  allowed""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Defining a KF_SLEEPABLE kfunc in a kernel module:")
                    CodeBlock(
                        """__bpf_kfunc int my_sleepable_kfunc(struct task_struct *task)
                            |{
                            |    // May sleep (e.g. acquires a mutex, does GFP_KERNEL allocation)
                            |    return 0;
                            |}
                            |
                            |BTF_KFUNCS_START(my_kfunc_set)
                            |BTF_ID_FLAGS(func, my_sleepable_kfunc, KF_SLEEPABLE | KF_TRUSTED_ARGS)
                            |BTF_KFUNCS_END(my_kfunc_set)""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Calling it from eBPF — the .s suffix is mandatory:")
                    CodeBlock(
                        """SEC("lsm.s/file_open")    // .s required — BPF_F_SLEEPABLE must be set
                            |int BPF_PROG(my_lsm_prog, struct file *file)
                            |{
                            |    struct task_struct *task = bpf_get_current_task_btf();
                            |    // Verifier allows this call only because BPF_F_SLEEPABLE is set
                            |    my_sleepable_kfunc(task);
                            |    return 0;
                            |}""".trimMargin()
                    )
                }
            }
            item {
                SectionCard(title = "Does the Verifier Guarantee Sleep Terminates?") {
                    BodyText(
                        "No. The verifier only determines whether sleeping is permitted for a given program " +
                        "type and attach point. It does not — and cannot — verify that a sleep will " +
                        "eventually end."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Why this is fundamentally impossible:")
                    CodeBlock(
                        """Guaranteeing that an arbitrary sleep terminates is equivalent to solving
                            |the halting problem — a provably undecidable question in computer science.
                            |No static analysis can determine in general whether a blocking wait will
                            |ever be satisfied.""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Responsibility falls on the author of each kfunc or helper to ensure bounded " +
                        "blocking: interruptible waits, hard timeouts, or other mechanisms that prevent " +
                        "indefinite blocking."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("What happens if a sleepable eBPF program blocks indefinitely:")
                    CodeBlock(
                        """The kernel thread running the eBPF program enters D state (uninterruptible
                            |or interruptible sleep, depending on the wait type). This is identical to
                            |any kernel thread stuck waiting for a resource that never arrives.
                            |The system does not crash, but that CPU thread is effectively hung until
                            |the blocking condition resolves or the system reboots.
                            |
                            |In practice: well-written sleepable helpers and kfuncs use interruptible
                            |waits (TASK_INTERRUPTIBLE) or bounded timeouts so the program cannot hang
                            |indefinitely.""".trimMargin()
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
