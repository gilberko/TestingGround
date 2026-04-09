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
fun EbpfAdvancedScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "eBPF — A Bit More Advanced",
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
                SectionCard(title = "Tail Calls") {
                    BodyText("An eBPF program can jump to another eBPF program using bpf_tail_call(). Unlike a regular function call, the callee completely replaces the caller's stack frame — the caller never returns. This is analogous to a C tail call or execve() for processes.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Tail calls are used to:")
                    CodeBlock(
                        """- Chain programs together to implement complex logic
                            |- Split a program across the 1M instruction limit
                            |- Implement dispatch tables (index → handler program)
                            |- Implement state machines across multiple BPF programs""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("How it works — the prog array map:")
                    CodeBlock(
                        """// Define a prog array map — values are BPF program FDs
                            |struct {
                            |    __uint(type, BPF_MAP_TYPE_PROG_ARRAY);
                            |    __uint(max_entries, 8);
                            |    __uint(key_size, sizeof(__u32));
                            |    __uint(value_size, sizeof(__u32));
                            |} prog_array SEC(".maps");
                            |
                            |SEC("xdp")
                            |int dispatcher(struct xdp_md *ctx) {
                            |    __u32 key = 0;
                            |    // Jump to program at index 0 in prog_array.
                            |    // If the jump succeeds, this function never returns.
                            |    bpf_tail_call(ctx, &prog_array, key);
                            |    // Only reached if tail call fails (key not found):
                            |    return XDP_PASS;
                            |}""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Limits and constraints:")
                    CodeBlock(
                        """- Max chain depth: 33 tail calls (hard verifier limit)
                            |- No cycles: cannot tail-call back to a previous program
                            |- Callee must be the same program type (e.g. both XDP)
                            |- The stack is NOT inherited — callee starts fresh
                            |- Maps can be shared between caller and callee
                            |- Programs are loaded separately and inserted into the
                            |  prog array from userspace""".trimMargin()
                    )
                }
            }
            item {
                SectionCard(title = "Loops in eBPF") {
                    BodyText("The eBPF verifier must prove that every possible execution path terminates. This means loops require special care — an unbounded loop will be rejected at load time.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Approach 1 — Bounded loops (Linux 5.3+):")
                    CodeBlock(
                        """// The verifier can prove this terminates because
                            |// the bound (100) is a compile-time constant.
                            |for (int i = 0; i < 100; i++) {
                            |    // do work
                            |}
                            |
                            |// Also works with an array length:
                            |#define MAX 64
                            |char buf[MAX];
                            |for (int i = 0; i < MAX; i++) {
                            |    buf[i] = 0;
                            |}
                            |
                            |// NOT OK — verifier cannot prove termination:
                            |for (int i = 0; i < n; i++) { // n is runtime value
                            |    // rejected unless verifier can bound 'n'
                            |}""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Approach 2 — #pragma unroll (always works):")
                    CodeBlock(
                        """// Force Clang to unroll the loop at compile time.
                            |// The verifier sees only straight-line code — no loop.
                            |// Downside: increases instruction count significantly.
                            |#pragma unroll
                            |for (int i = 0; i < 16; i++) {
                            |    process(buf[i]);
                            |}""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Approach 3 — bpf_loop() helper (Linux 5.17+):")
                    CodeBlock(
                        """// bpf_loop(nr_loops, callback, ctx, flags)
                            |// The helper itself guarantees termination — the verifier
                            |// does not need to analyze the callback's loop body.
                            |// Max iterations: 1 << 23 (~8 million).
                            |
                            |static int my_cb(__u32 index, void *ctx) {
                            |    // 'index' goes from 0 to nr_loops-1
                            |    // return 0 to continue, 1 to stop early
                            |    do_work(index);
                            |    return 0;
                            |}
                            |
                            |// Call from your BPF program:
                            |bpf_loop(100, my_cb, NULL, 0);""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("bpf_loop() is the preferred approach when the iteration count is a runtime value, as it decouples termination proof from the verifier's analysis of the callback body.")
                }
            }
            item {
                SectionCard(title = "Is Sleep Allowed in eBPF?") {
                    BodyText("For most eBPF program types: NO. Programs attached to kprobes, tracepoints, XDP, TC, and socket filters run in interrupt context or softirq context — the scheduler cannot preempt them, so sleeping is forbidden. Any attempt to sleep (or call a sleeping helper) will be rejected by the verifier.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Sleepable programs (Linux 5.10+):")
                    CodeBlock(
                        """A subset of program types can be loaded with the
                            |BPF_F_SLEEPABLE flag. These run in task (process) context
                            |and are allowed to sleep.
                            |
                            |Supported sleepable types:
                            |  - BPF LSM hooks (lsm/*)
                            |  - fentry/fexit on sleepable kernel functions
                            |  - BPF iterator programs
                            |  - uprobe programs
                            |
                            |Sleepable helpers available only to these programs:
                            |  bpf_copy_from_user()   — may take a page fault (sleeps)
                            |  bpf_send_signal()      — sends a signal to current task
                            |  bpf_ringbuf_reserve()  — may block if ring is full
                            |
                            |Requirements:
                            |  - CAP_BPF capability
                            |  - The kernel hook site must itself be sleepable
                            |    (i.e. not called from interrupt context)""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Mark a program sleepable in libbpf:")
                    CodeBlock(
                        """struct bpf_program *prog = ...;
                            |bpf_program__set_flags(prog, BPF_F_SLEEPABLE);""".trimMargin()
                    )
                }
            }
            item {
                SectionCard(title = "bpf_timer — Deferred / Delayed Work") {
                    BodyText("Linux 5.15 added bpf_timer — a per-map timer that fires a BPF callback function asynchronously after a delay. This allows BPF programs to defer work without sleeping in the probe handler.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The timer value is stored in a BPF map. The callback runs in task context (can sleep if needed). The timer can be one-shot or repeating.")
                    CodeBlock(
                        """// Map holding the timer
                            |struct {
                            |    __uint(type, BPF_MAP_TYPE_ARRAY);
                            |    __type(key, int);
                            |    __type(value, struct bpf_timer);
                            |} timers SEC(".maps");
                            |
                            |// The callback — runs later, asynchronously
                            |static int timer_cb(void *map, int *key,
                            |                    struct bpf_timer *timer) {
                            |    // do deferred work here
                            |    // can call sleeping helpers
                            |    return 0;
                            |}
                            |
                            |// In your BPF probe — set up the timer
                            |SEC("kprobe/some_function")
                            |int my_probe(struct pt_regs *ctx) {
                            |    int key = 0;
                            |    struct bpf_timer *t = bpf_map_lookup_elem(&timers, &key);
                            |    if (!t) return 0;
                            |
                            |    bpf_timer_init(t, &timers, CLOCK_MONOTONIC);
                            |    bpf_timer_set_callback(t, timer_cb);
                            |    // Fire after 1 second (nanoseconds)
                            |    bpf_timer_start(t, 1000000000ULL, 0);
                            |    return 0;
                            |}""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("To make the timer repeat: call bpf_timer_start() again from within the callback. The timer is automatically cancelled when its map entry is deleted.")
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
