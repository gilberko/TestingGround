package com.example.developmentapp.screens.assembly

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
import com.example.developmentapp.screens.BodyText
import com.example.developmentapp.screens.CodeBlock
import com.example.developmentapp.screens.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SynchronizationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Synchronization",
                        color      = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontSize   = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector        = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint               = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        LazyColumn(
            modifier       = Modifier.fillMaxSize().padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // 1 — The LOCK Prefix
            item {
                SectionCard("The LOCK Prefix") {
                    BodyText(
                        "LOCK is a 1-byte instruction prefix (0xF0) that makes a read-modify-write on memory " +
                        "appear atomic to every other core in the system — no other core's load or store to that " +
                        "address can be interleaved with it."
                    )
                    CodeBlock(
                        "0xF0   ; LOCK prefix byte, placed before the opcode\n\n" +
                        "lock inc dword [counter]     ; atomic increment\n" +
                        "lock cmpxchg [ptr], eax       ; atomic compare-and-swap"
                    )
                    BodyText(
                        "LOCK is only legal on instructions that read-modify-write a memory destination, and " +
                        "only that fixed list:"
                    )
                    CodeBlock(
                        "ADD  ADC  AND  BTC  BTR  BTS\n" +
                        "CMPXCHG  CMPXCHG8B  CMPXCHG16B\n" +
                        "DEC  INC  NEG  NOT  OR  SBB  SUB  XOR\n" +
                        "XADD  XCHG"
                    )
                    BodyText(
                        "Applying LOCK to anything else — or to a register-only destination — is illegal and " +
                        "raises #UD (invalid opcode) on the CPU. One quirk: XCHG with a memory operand is " +
                        "*always* atomic, even with no LOCK byte present — the architecture guarantees it " +
                        "implicitly for historical reasons."
                    )
                }
            }

            // 2 — What LOCK Achieves
            item {
                SectionCard("What LOCK Achieves") {
                    BodyText(
                        "The guarantee is atomicity: the read, the modify, and the write of a LOCKed instruction " +
                        "happen as one indivisible step from every other core's point of view. Two cores both " +
                        "executing 'lock inc [counter]' can never lose an update to a race."
                    )
                    BodyText(
                        "How it's implemented: older CPUs asserted a literal LOCK# signal on the system bus, " +
                        "freezing all other bus traffic — a full bus lock. Modern CPUs instead use a cache lock: " +
                        "the cache line is grabbed in Exclusive state under the MESI coherence protocol so no other " +
                        "core's cache can hold a copy while the operation completes. A full bus lock is still used " +
                        "as a fallback when the access is misaligned and spans two cache lines, or when it targets " +
                        "uncached/MMIO memory."
                    )
                    BodyText(
                        "A side effect that matters in practice: on x86, every LOCKed instruction is also a full " +
                        "memory barrier — it orders all loads/stores before it against all loads/stores after it. " +
                        "This is why 'lock xchg' or 'lock add [x], 0' are commonly used in real-world code as a " +
                        "cheap full-barrier substitute for an explicit MFENCE — on many microarchitectures it's " +
                        "actually faster."
                    )
                }
            }

            // 3 — Why Reordering Happens
            item {
                SectionCard("Why Reordering Happens") {
                    BodyText(
                        "Code rarely executes in the exact order it was written, for two independent reasons:"
                    )
                    BodyText(
                        "1. The compiler reorders, caches in registers, or eliminates memory accesses, as long as " +
                        "the result is indistinguishable from the original order — but only from a single thread's " +
                        "own point of view (as-if-serial semantics)."
                    )
                    BodyText(
                        "2. The CPU itself reorders memory operations at runtime: stores sit in a per-core store " +
                        "buffer before becoming globally visible, execution is out-of-order, and the CPU " +
                        "speculatively executes past branches and loads."
                    )
                    BodyText(
                        "Neither source breaks single-threaded correctness, but both can break multi-threaded " +
                        "code that relies on one thread observing another's writes in the order they were issued. " +
                        "Classic example:"
                    )
                    CodeBlock(
                        "Thread A:                  Thread B:\n" +
                        "  mov [data], 1               .wait:\n" +
                        "  mov [ready], 1                 cmp [ready], 0\n" +
                        "                                  je .wait\n" +
                        "                               mov eax, [data]   ; expects 1\n\n" +
                        "; Without ordering guarantees, B's load of [data] is not guaranteed\n" +
                        "; to see A's write — A's two stores, or B's load of [ready] versus\n" +
                        "; its later load of [data], can be reordered relative to each other."
                    )
                }
            }

            // 4 — The Four Types of Memory Barriers
            item {
                SectionCard("The Four Types of Memory Barriers") {
                    BodyText(
                        "Reordering is classified by which pair of operation kinds can swap order. A barrier of a " +
                        "given type forbids that specific swap across the barrier point:"
                    )
                    CodeBlock(
                        "Barrier type   Forbids reordering of\n" +
                        "────────────────────────────────────────────────────────\n" +
                        "LoadLoad       a load, then a later load\n" +
                        "StoreStore     a store, then a later store\n" +
                        "LoadStore      a load, then a later store\n" +
                        "StoreLoad      a store, then a later load   (the expensive one)"
                    )
                    BodyText(
                        "x86 hardware follows a model called Total Store Order (TSO), which already preserves " +
                        "LoadLoad, StoreStore, and LoadStore ordering for free — the CPU never reorders those. " +
                        "StoreLoad is the one reordering x86 hardware actually performs (because of the store " +
                        "buffer), and it's exactly what MFENCE, and LOCKed instructions, exist to prevent."
                    )
                }
            }

            // 5 — Compiler Barriers vs Hardware Barriers
            item {
                SectionCard("Compiler Barriers vs Hardware Barriers") {
                    BodyText(
                        "A compiler barrier is a compile-time-only directive. GCC/Clang's idiom is " +
                        "asm volatile(\"\" ::: \"memory\") — it emits zero machine instructions. Its only effect " +
                        "is telling the compiler: 'don't reorder, cache in a register, or eliminate any memory " +
                        "access across this line.' It has no runtime existence and no effect on what the CPU " +
                        "does once the binary is running."
                    )
                    BodyText(
                        "A hardware barrier (a CPU fence instruction, or a LOCKed instruction) is a real " +
                        "instruction the processor executes, constraining how its memory operations become " +
                        "visible to other cores at runtime."
                    )
                    BodyText(
                        "The two are orthogonal and you frequently need both: a compiler barrier alone does " +
                        "nothing for cross-core visibility — the CPU can still reorder at runtime. A hardware " +
                        "fence alone doesn't help if the compiler already reordered the surrounding accesses " +
                        "before the fence instruction was even emitted. In hand-written assembly the compiler " +
                        "barrier issue disappears — there's no compiler — but it's the reason intrinsics like " +
                        "_mm_mfence() in C are paired with a memory clobber in their definition."
                    )
                }
            }

            // 6 — x86 Fence Instructions
            item {
                SectionCard("x86 Fence Instructions: LFENCE / SFENCE / MFENCE") {
                    BodyText(
                        "Yes — all three are real x86 instructions (introduced with SSE/SSE2), each a true " +
                        "hardware barrier:"
                    )
                    CodeBlock(
                        "sfence   ; orders stores only\n" +
                        "lfence   ; orders loads only (see note below)\n" +
                        "mfence   ; orders both loads and stores (full fence)"
                    )
                    BodyText(
                        "MFENCE — the one you reach for by default. It guarantees every load/store before it is " +
                        "globally visible before any load/store after it executes. It's the explicit way to stop " +
                        "the one reordering x86 hardware allows: StoreLoad."
                    )
                    BodyText(
                        "SFENCE — orders stores relative to other stores. Ordinary stores are already ordered on " +
                        "x86, so SFENCE mainly matters for non-temporal stores (MOVNTI, MOVNTQ, MOVNTDQ) that " +
                        "deliberately bypass the normal cache/store-buffer ordering, and for write-combining " +
                        "memory regions (e.g. framebuffers)."
                    )
                    BodyText(
                        "LFENCE — architecturally described as ordering loads relative to other loads. Since x86 " +
                        "hardware never reorders loads with loads anyway, that guarantee is close to free in " +
                        "practice. LFENCE's real modern job is different: it's a speculation barrier — it stalls " +
                        "execution until every instruction before it has retired, so nothing after it can execute " +
                        "speculatively past that point. That's why LFENCE is the standard mitigation for Spectre-" +
                        "style speculative-execution side channels. (Historical note: on some older AMD CPUs, " +
                        "LFENCE was only ordering, not dispatch-serializing, unless an MSR bit was set — a real " +
                        "concern for early Spectre patches.)"
                    )
                }
            }

            // 7 — Fences vs Barriers
            item {
                SectionCard("Fences vs Barriers — Same Thing?") {
                    BodyText(
                        "Yes. 'Fence' and 'barrier' name the same concept; which word you see just depends on " +
                        "whose vocabulary you're reading."
                    )
                    CodeBlock(
                        "Concept             Linux kernel    x86 instruction   C++11\n" +
                        "──────────────────────────────────────────────────────────────\n" +
                        "full barrier         mb()            mfence            atomic_thread_fence(seq_cst)\n" +
                        "read barrier          rmb()           lfence            (memory_order_acquire fence)\n" +
                        "write barrier         wmb()           sfence            (memory_order_release fence)\n" +
                        "SMP-only full barrier smp_mb()        mfence            —"
                    )
                    BodyText(
                        "Intel and AMD chose 'fence' for the x86 instruction mnemonics themselves. The Linux " +
                        "kernel and most academic CS literature say 'memory barrier.' C++11 standardized the term " +
                        "as 'fence' (std::atomic_thread_fence). All of them describe the identical hardware idea: " +
                        "a point past which certain memory-operation reorderings are forbidden."
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
