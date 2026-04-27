package com.example.developmentapp.screens.stl

import androidx.compose.foundation.layout.Spacer
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
fun StlAtomicMemoryBarriersScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "C/C++ — Atomic and Memory Barriers",
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
        LazyColumn(modifier = Modifier.padding(innerPadding).padding(horizontal = 16.dp)) {
            item { Spacer(Modifier.height(16.dp)) }

            item {
                SectionCard(title = "The Problem: Shared Data Across Threads") {
                    BodyText("Without synchronisation, both the compiler and the CPU are free to reorder or cache memory accesses. Two threads reading and writing the same variable without any protection can see stale or torn values — even on simple types like int.")
                    BodyText("The three sources of danger:")
                    BodyText("1. Compiler reordering — the compiler may move stores and loads past each other if it sees no dependency.")
                    BodyText("2. CPU out-of-order execution — modern CPUs execute instructions out of program order and buffer stores before they reach shared cache.")
                    BodyText("3. Non-atomic read-modify-write — even a simple counter++ is not atomic: it compiles to a load, an add, and a store. Two threads can interleave these steps.")
                    CodeBlock(
                        "// Racy — NOT safe\n" +
                        "int counter = 0;\n" +
                        "\n" +
                        "// Thread A           Thread B\n" +
                        "// counter++           counter++\n" +
                        "// (load,add,store)    (load,add,store)\n" +
                        "// Both may load 0, both store 1 → final value is 1, not 2"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "C11 stdatomic.h") {
                    BodyText("Atomic operations for C were standardised in C11 (ISO/IEC 9899:2011). Include <stdatomic.h>.")
                    BodyText("The _Atomic type qualifier makes any object atomic. Convenience typedefs are provided:")
                    BodyText("  atomic_bool, atomic_char, atomic_int, atomic_long, atomic_llong, atomic_size_t, …")
                    BodyText("Each is equivalent to _Atomic followed by the underlying type. Example: atomic_int is _Atomic int.")
                    CodeBlock(
                        "#include <stdatomic.h>\n" +
                        "\n" +
                        "atomic_int counter = 0;\n" +
                        "\n" +
                        "// Store and load — default order is memory_order_seq_cst\n" +
                        "atomic_store(&counter, 1);\n" +
                        "int v = atomic_load(&counter);\n" +
                        "\n" +
                        "// Explicit order variants\n" +
                        "atomic_store_explicit(&counter, 1, memory_order_release);\n" +
                        "int v2 = atomic_load_explicit(&counter, memory_order_acquire);\n" +
                        "\n" +
                        "// Read-modify-write\n" +
                        "atomic_fetch_add(&counter, 1);   // atomic counter++\n" +
                        "atomic_fetch_sub(&counter, 1);   // atomic counter--\n" +
                        "\n" +
                        "// Compare-and-swap\n" +
                        "int expected = 0;\n" +
                        "bool ok = atomic_compare_exchange_strong(&counter, &expected, 42);\n" +
                        "// if counter == expected, stores 42 and returns true\n" +
                        "// otherwise loads current value into expected and returns false"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "C11 Memory Orders") {
                    BodyText("The memory_order enum controls how atomic operations are ordered relative to surrounding non-atomic accesses:")
                    BodyText("memory_order_relaxed — the operation is atomic (no torn reads/writes) but carries no ordering guarantee with respect to other memory operations. Useful only when all you need is atomicity (e.g., a statistics counter).")
                    BodyText("memory_order_acquire — used on a load. All subsequent reads and writes in this thread cannot be moved before this load. Pairs with a release store in another thread.")
                    BodyText("memory_order_release — used on a store. All preceding reads and writes in this thread cannot be moved after this store. Pairs with an acquire load in another thread.")
                    BodyText("memory_order_acq_rel — combines acquire + release. Used on read-modify-write operations (fetch_add, compare_exchange, …).")
                    BodyText("memory_order_seq_cst — full sequential consistency. All seq_cst operations across all threads appear in a single total order agreed upon by every thread. This is the default when no order is specified.")
                    CodeBlock(
                        "// Producer thread\n" +
                        "data = 42;                                       // ordinary write\n" +
                        "atomic_store_explicit(&ready, 1,\n" +
                        "                      memory_order_release);     // release: data write visible before this\n" +
                        "\n" +
                        "// Consumer thread\n" +
                        "while (!atomic_load_explicit(&ready,\n" +
                        "                             memory_order_acquire)); // acquire: see data after this\n" +
                        "use(data);   // guaranteed to see 42"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "C11 atomic_thread_fence") {
                    BodyText("A fence is a standalone barrier not tied to a specific atomic variable. It orders all atomic and non-atomic accesses on one side of the fence relative to the other.")
                    BodyText("A release fence prevents all preceding stores from being moved after it. An acquire fence prevents all subsequent loads from being moved before it. A seq_cst fence acts as a full barrier.")
                    CodeBlock(
                        "atomic_thread_fence(memory_order_release); // release fence\n" +
                        "atomic_thread_fence(memory_order_acquire); // acquire fence\n" +
                        "atomic_thread_fence(memory_order_seq_cst); // full barrier (MFENCE on x86)\n" +
                        "\n" +
                        "// Example: fence-based release/acquire without explicit atomic store/load\n" +
                        "// Producer:\n" +
                        "data = 42;\n" +
                        "atomic_thread_fence(memory_order_release);\n" +
                        "atomic_store_explicit(&ready, 1, memory_order_relaxed);\n" +
                        "\n" +
                        "// Consumer:\n" +
                        "while (!atomic_load_explicit(&ready, memory_order_relaxed));\n" +
                        "atomic_thread_fence(memory_order_acquire);\n" +
                        "use(data); // safe"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "C++ <atomic> (C++11)") {
                    BodyText("C++11 introduced <atomic>. The template std::atomic<T> works for any trivially copyable type T. The default memory order for all operations is std::memory_order_seq_cst.")
                    CodeBlock(
                        "#include <atomic>\n" +
                        "\n" +
                        "std::atomic<int> counter{0};\n" +
                        "\n" +
                        "// Store and load\n" +
                        "counter.store(1);                                    // seq_cst\n" +
                        "counter.store(1, std::memory_order_release);\n" +
                        "int v  = counter.load();                             // seq_cst\n" +
                        "int v2 = counter.load(std::memory_order_acquire);\n" +
                        "\n" +
                        "// Read-modify-write\n" +
                        "counter.fetch_add(1);                                // atomic ++\n" +
                        "counter.fetch_sub(1);                                // atomic --\n" +
                        "counter++;   counter--;   ++counter;                 // also available\n" +
                        "\n" +
                        "// Compare-and-swap (CAS)\n" +
                        "int expected = 0;\n" +
                        "bool ok = counter.compare_exchange_strong(expected, 42);\n" +
                        "\n" +
                        "// Standalone fence\n" +
                        "std::atomic_thread_fence(std::memory_order_seq_cst);"
                    )
                    BodyText("Memory order names mirror C11 but live in the std:: namespace: std::memory_order_relaxed, std::memory_order_acquire, std::memory_order_release, std::memory_order_acq_rel, std::memory_order_seq_cst.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Why volatile Does Not Help") {
                    BodyText("A common misconception is that declaring a shared variable volatile is enough. It is not.")
                    BodyText("What volatile does: tells the compiler not to keep the variable in a register between accesses — every read goes to memory and every write goes to memory immediately. This is enough for memory-mapped hardware registers.")
                    BodyText("What volatile does NOT do:")
                    BodyText("1. Does not prevent the compiler from reordering volatile accesses relative to other (non-volatile) accesses around them.")
                    BodyText("2. Does not prevent the CPU from reordering through its store buffer or out-of-order execution engine.")
                    BodyText("3. Does not make any operation atomic — volatile int counter++; is still three separate instructions (load, add, store).")
                    CodeBlock(
                        "// WRONG — volatile does not fix the race\n" +
                        "volatile int counter = 0;\n" +
                        "// Thread A: counter++   (load, add, store — still 3 steps)\n" +
                        "// Thread B: counter++   (same — data race, UB)\n" +
                        "\n" +
                        "// CORRECT — use std::atomic\n" +
                        "std::atomic<int> counter{0};\n" +
                        "// Thread A: counter.fetch_add(1);  // single atomic RMW\n" +
                        "// Thread B: counter.fetch_add(1);  // safe"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "x86 Assembly Equivalents") {
                    BodyText("x86 uses a Total Store Order (TSO) memory model:")
                    BodyText("  • Stores are not reordered with other stores.")
                    BodyText("  • Loads are not reordered with other loads.")
                    BodyText("  • Stores are not reordered with earlier loads.")
                    BodyText("  • BUT: a load may be reordered before a prior store (the only TSO weakness).")
                    BodyText("Consequence: acquire loads and release stores are essentially free — the CPU already enforces those orderings. Only seq_cst stores need an explicit fence.")
                    CodeBlock(
                        "; relaxed store / load — plain MOV\n" +
                        "mov [mem], eax     ; store\n" +
                        "mov eax, [mem]     ; load\n" +
                        "\n" +
                        "; release store (same as relaxed on x86 TSO — MOV is enough)\n" +
                        "mov [mem], eax\n" +
                        "\n" +
                        "; acquire load (same as relaxed on x86 TSO — MOV is enough)\n" +
                        "mov eax, [mem]\n" +
                        "\n" +
                        "; seq_cst store — MOV + MFENCE\n" +
                        "mov [mem], eax\n" +
                        "mfence\n" +
                        "\n" +
                        "; Alternative: XCHG has an implied LOCK prefix (always atomic)\n" +
                        "xchg [mem], eax\n" +
                        "\n" +
                        "; Fences\n" +
                        "mfence   ; seq_cst fence — full store+load barrier\n" +
                        "lfence   ; acquire fence — serialises loads (rarely needed on TSO)\n" +
                        "sfence   ; release fence — serialises stores (rarely needed on TSO)"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Compiler Barriers") {
                    BodyText("A compiler barrier prevents the compiler from reordering memory accesses across that point. It generates no CPU instructions — the CPU can still reorder. Only atomic operations and CPU fences stop CPU reordering.")
                    BodyText("GCC / Clang user-space compiler barrier:")
                    CodeBlock(
                        "__asm__ volatile(\"\" ::: \"memory\");\n" +
                        "// The empty inline asm with the \"memory\" clobber tells the compiler\n" +
                        "// to assume all memory may have changed at this point,\n" +
                        "// preventing any reordering of accesses across this line."
                    )
                    BodyText("Linux kernel barrier() macro — defined in include/linux/compiler.h:")
                    CodeBlock(
                        "#define barrier() __asm__ __volatile__(\"\":::\"memory\")\n" +
                        "\n" +
                        "// Usage in kernel code:\n" +
                        "barrier();   // compiler barrier — no CPU fence generated\n" +
                        "\n" +
                        "// For CPU barriers in the kernel, use the smp_* macros instead:\n" +
                        "smp_mb();    // full memory barrier (maps to MFENCE on x86)\n" +
                        "smp_rmb();   // read  barrier (LFENCE on x86)\n" +
                        "smp_wmb();   // write barrier (SFENCE on x86)\n" +
                        "smp_store_release(&x, val); // release store\n" +
                        "smp_load_acquire(&x);       // acquire load"
                    )
                    BodyText("Summary: use compiler barriers (barrier() / __asm__ volatile) only when you need to stop compiler reordering without emitting any CPU instruction — for example, around memory-mapped I/O that is already protected by hardware ordering. For synchronisation between CPU threads always use atomic operations or explicit CPU fences.")
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
