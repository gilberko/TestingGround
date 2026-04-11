package com.example.linuxapp.screens.usermode

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
fun UserModeInlineAssemblyScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Inline Assembly",
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
                SectionCard(title = "What Is Inline Assembly") {
                    BodyText("GCC and G++ allow embedding raw CPU instructions directly inside C or C++ source using the asm keyword (also written __asm__ for strict ANSI mode). The compiler integrates the hand-written instructions into the surrounding code without a separate .s file.")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Common reasons to use inline assembly:")
                    BodyText("  \u2022 Access CPU instructions with no C equivalent (CPUID, RDTSC, XCHG, memory fences)")
                    BodyText("  \u2022 Read or write CPU registers directly (RSP, RFLAGS, CR3 in kernel)")
                    BodyText("  \u2022 Implement lock-free primitives or ultra-tight loops where the compiler's output is suboptimal")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("No special compiler flags are needed. A normal gcc -O2 -o prog prog.c compile works fine.")
                }
            }
            item {
                SectionCard(title = "Basic vs Extended Asm") {
                    BodyText("Basic asm — just an instruction string, no operands:")
                    CodeBlock("asm (\"nop\");              // do nothing\nasm (\"cli\");              // disable interrupts (kernel only)")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Extended asm — the full form, lets you connect C variables to registers or memory. Always use extended asm inside functions so GCC knows what the block reads and writes:")
                    CodeBlock("asm [volatile] (\n    \"assembly instructions\"   // template string\n    : output operands          // optional\n    : input operands           // optional\n    : clobber list             // optional\n);")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Multiple instructions in one block: separate them with \\n\\t (newline + tab) to produce clean assembly output.")
                    CodeBlock("asm (\"mov $1, %%eax\\n\\t\"\n     \"add $2, %%eax\\n\\t\"\n     \"mov %%eax, %0\"\n     : \"=r\"(result)\n     : /* no inputs */\n     : \"eax\");")
                }
            }
            item {
                SectionCard(title = "Operand Constraints") {
                    BodyText("Constraints tell GCC where to put each operand — in a register, in memory, or as an immediate. Common constraints:")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("  \"r\"  — any general-purpose register (GCC picks)")
                    BodyText("  \"m\"  — memory location (variable's address)")
                    BodyText("  \"i\"  — compile-time integer constant")
                    BodyText("  \"g\"  — register, memory, or immediate (GCC picks best)")
                    BodyText("  \"a\"  — EAX / RAX specifically")
                    BodyText("  \"b\"  — EBX / RBX specifically")
                    BodyText("  \"c\"  — ECX / RCX specifically")
                    BodyText("  \"d\"  — EDX / RDX specifically")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Modifier prefixes on output constraints:")
                    BodyText("  \"=r\"  — write-only output (GCC allocates a register)")
                    BodyText("  \"+r\"  — read/write: GCC reads it in, you write it back")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Referencing operands in the template:")
                    BodyText("  %0, %1, %2 … — by position (0 = first output, then inputs)")
                    BodyText("  %[name]     — by name (cleaner for long blocks)")
                    CodeBlock("// Named operands example\nasm (\"imul %[b], %[a]\"\n     : [a] \"+r\"(x)\n     : [b] \"r\"(y));\n// x = x * y")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Inside the template, a literal % must be written as %% because a single % introduces an operand reference.")
                }
            }
            item {
                SectionCard(title = "Moving Data: C Variable \u2194 Register") {
                    BodyText("The most common pattern — load a C variable into a register, operate on it, store the result back:")
                    CodeBlock("int x = 10, result;\nasm (\"mov %1, %%eax\\n\\t\"   // load x into EAX\n     \"add $1,  %%eax\\n\\t\"   // EAX = EAX + 1\n     \"mov %%eax, %0\"        // store EAX into result\n     : \"=r\"(result)          // output: result\n     : \"r\"(x)                // input:  x\n     : \"eax\");               // clobber: we used EAX")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Using named operands and the +r (read/write) constraint — simpler when output == input:")
                    CodeBlock("int val = 5;\nasm (\"add %[inc], %[v]\"\n     : [v]   \"+r\"(val)\n     : [inc] \"i\"(3));\n// val is now 8, no separate output needed")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Pointer / memory operand — operate on a value in memory directly:")
                    CodeBlock("int arr[4] = {1,2,3,4};\nasm (\"movl $99, %0\"\n     : \"=m\"(arr[2]));   // arr[2] = 99 via memory operand")
                }
            }
            item {
                SectionCard(title = "Reading CPU Registers") {
                    BodyText("There is no C syntax to read a hardware register directly; you must route through an asm block with an output operand.")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Read RSP (stack pointer):")
                    CodeBlock("#include <stdint.h>\nuint64_t sp;\nasm (\"mov %%rsp, %0\" : \"=r\"(sp));\n// sp now holds the current stack pointer value")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Read RFLAGS (status/condition flags):")
                    CodeBlock("uint64_t flags;\nasm (\"pushfq\\n\\t\"\n     \"pop %0\"\n     : \"=r\"(flags));\n// bit 9 = interrupt enable flag (IF)")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("RDTSC — read the Time Stamp Counter (cycle count since reset). Returns 64-bit value split across EDX:EAX:")
                    CodeBlock("uint32_t lo, hi;\nasm volatile (\"rdtsc\" : \"=a\"(lo), \"=d\"(hi));\nuint64_t tsc = ((uint64_t)hi << 32) | lo;\n// Use for micro-benchmarking (not wall time)")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("CPUID — query CPU features. Leaf in EAX, results in EAX/EBX/ECX/EDX:")
                    CodeBlock("uint32_t eax, ebx, ecx, edx;\nuint32_t leaf = 0;  // leaf 0: max leaf + vendor string\nasm volatile (\"cpuid\"\n     : \"=a\"(eax), \"=b\"(ebx), \"=c\"(ecx), \"=d\"(edx)\n     : \"a\"(leaf)\n     : \"memory\");")
                }
            }
            item {
                SectionCard(title = "The Clobber List") {
                    BodyText("The clobber list tells GCC which registers or memory the asm block modifies without those items appearing as explicit output operands. GCC will save and restore them (or avoid using them as temporaries) around the block.")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Register clobbers — list by name:")
                    CodeBlock("asm (\"xchg %%eax, %%ebx\"\n     : /* no outputs */\n     : /* no inputs  */\n     : \"eax\", \"ebx\");   // both registers modified")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("\"cc\" — the asm modifies the CPU condition codes (FLAGS / EFLAGS / RFLAGS). Include this whenever your asm executes instructions that set flags (add, sub, cmp, test, etc.).")
                    CodeBlock("asm (\"add %1, %0\"\n     : \"+r\"(a)\n     : \"r\"(b)\n     : \"cc\");   // add sets flags")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("\"memory\" — the asm reads or writes memory that GCC cannot see through the explicit operands. This acts as a full compiler memory barrier: GCC flushes all cached variable values before the block and reloads them after. Use whenever the asm dereferences pointers or performs stores not listed as operands.")
                    CodeBlock("// Write 0 to the address in ptr\nasm volatile (\"movl $0, (%0)\"\n     : /* no outputs */\n     : \"r\"(ptr)\n     : \"memory\");   // GCC: assume memory changed")
                }
            }
            item {
                SectionCard(title = "asm volatile") {
                    BodyText("By default GCC may move, duplicate, or delete an asm block if it decides the block has no effect on program output (dead-code elimination, common-subexpression elimination, loop hoisting).")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("asm volatile prevents all of that. Use volatile when the block has an observable side effect that GCC cannot see:")
                    BodyText("  \u2022 RDTSC / RDTSCP — timing; moving it changes the measured interval")
                    BodyText("  \u2022 Memory fences — must not be reordered with surrounding loads/stores")
                    BodyText("  \u2022 I/O port access (in/out instructions in kernel drivers)")
                    BodyText("  \u2022 Any block whose side effect is NOT captured by an output operand")
                    CodeBlock("// Safe: volatile prevents removal / hoisting\nuint64_t t1, t2;\nasm volatile (\"rdtsc\" : \"=A\"(t1));\ndo_work();\nasm volatile (\"rdtsc\" : \"=A\"(t2));\nuint64_t cycles = t2 - t1;")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("If an asm block has outputs and volatile is omitted, GCC is allowed to remove it if the outputs are never used. When in doubt, add volatile.")
                }
            }
            item {
                SectionCard(title = "Memory Barriers") {
                    BodyText("Both the compiler and the CPU can reorder memory operations for performance. Barriers prevent this.")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Compiler-only barrier — stops GCC from reordering, but does NOT emit a CPU fence instruction:")
                    CodeBlock("asm volatile (\"\" ::: \"memory\");\n// GCC: treat as if all memory was read and written here\n// CPU: still free to reorder its own load/store pipeline")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Full CPU + compiler barrier (x86 MFENCE — serializes all loads and stores):")
                    CodeBlock("asm volatile (\"mfence\" ::: \"memory\");\n// Both the compiler and the CPU are fenced")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Store barrier only (SFENCE — all prior stores globally visible before stores after):")
                    CodeBlock("asm volatile (\"sfence\" ::: \"memory\");")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Load barrier only (LFENCE — all prior loads complete before loads after):")
                    CodeBlock("asm volatile (\"lfence\" ::: \"memory\");")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Note: on x86/x86-64 the memory model is already strongly ordered for regular stores; MFENCE is mainly needed with non-temporal (streaming) stores or when interacting with device memory. On ARM/PowerPC weaker memory models, barriers are needed far more often.")
                }
            }
            item {
                SectionCard(title = "Compilation") {
                    BodyText("Inline assembly is compiled exactly like any other C or C++ code — no special flags required:")
                    CodeBlock("gcc  -O2 -o prog  prog.c\ng++  -O2 -o prog  prog.cpp")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Inspect the generated assembly output with -S (stops before assembling, writes a .s file):")
                    CodeBlock("gcc -S -O2 -o prog.s prog.c\ncat prog.s   # look for your asm blocks inline")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Tips:")
                    BodyText("  \u2022 -m32 compiles for x86 (32-bit); default on a 64-bit system is x86-64")
                    BodyText("  \u2022 Use -Wall — the compiler will warn if an output operand is unused")
                    BodyText("  \u2022 If a constraint is wrong, GCC emits an error like \"impossible constraint\" or \"operand does not satisfy constraint\" — read it carefully")
                    BodyText("  \u2022 For kernel code, inline assembly lives in headers like arch/x86/include/asm/; the style is identical but uses kernel types (u32, u64) and kernel-specific constraints")
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
