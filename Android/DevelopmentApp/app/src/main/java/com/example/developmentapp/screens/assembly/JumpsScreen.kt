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
fun JumpsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Jumps",
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
            // 1 — Unconditional JMP
            item {
                SectionCard("Unconditional JMP") {
                    BodyText("JMP transfers control to a target address unconditionally. Three encoding forms exist:")
                    CodeBlock(
                        "JMP short rel8      ; EB xx\n" +
                        "                    ; 2 bytes total\n" +
                        "                    ; range: -128 to +127 bytes from next instruction\n\n" +
                        "JMP near  rel32     ; E9 xx xx xx xx\n" +
                        "                    ; 5 bytes total\n" +
                        "                    ; range: ±2 GB from next instruction (64-bit mode)\n\n" +
                        "JMP FAR  ptr16:32   ; EA + 6-byte far pointer\n" +
                        "                    ; changes CS selector and EIP\n" +
                        "                    ; rarely used in 64-bit protected mode"
                    )
                    BodyText(
                        "In 64-bit mode, all code shares a single flat address space with CS base = 0. " +
                        "Far jumps that change CS are therefore only meaningful for switching privilege levels or " +
                        "compatibility modes, and are essentially absent from normal user/kernel code."
                    )
                    BodyText("Indirect jumps use a register or memory operand as the target:")
                    CodeBlock(
                        "JMP RAX            ; jump to address in RAX  (2 bytes)\n" +
                        "JMP [RBX]          ; jump to address stored at [RBX]\n" +
                        "JMP [RIP+offset]   ; position-independent indirect jump (jump table)"
                    )
                }
            }

            // 2 — Conditional Jumps
            item {
                SectionCard("Conditional Jumps") {
                    BodyText(
                        "Conditional jumps test the EFLAGS/RFLAGS register and branch only if the condition is met. " +
                        "They come in two encoding sizes — the assembler chooses automatically based on offset:"
                    )
                    CodeBlock(
                        "Short form:  7x xx          (2 bytes, ±127 bytes)\n" +
                        "Near form:   0F 8x xx xx xx (6 bytes, ±2 GB)\n"
                    )
                    BodyText("Common conditional jumps and their flag conditions:")
                    CodeBlock(
                        "Mnemonic    Alias   Condition\n" +
                        "JE          JZ      ZF = 1           (equal / zero)\n" +
                        "JNE         JNZ     ZF = 0           (not equal / not zero)\n" +
                        "JG          JNLE    ZF=0 AND SF=OF   (signed greater)\n" +
                        "JGE         JNL     SF = OF          (signed greater-or-equal)\n" +
                        "JL          JNGE    SF ≠ OF          (signed less)\n" +
                        "JLE         JNG     ZF=1 OR SF≠OF    (signed less-or-equal)\n" +
                        "JA          JNBE    CF=0 AND ZF=0    (unsigned above)\n" +
                        "JAE         JNB     CF = 0           (unsigned above-or-equal)\n" +
                        "JB          JNAE    CF = 1           (unsigned below)\n" +
                        "JBE         JNA     CF=1 OR ZF=1     (unsigned below-or-equal)\n" +
                        "JS                  SF = 1           (negative / sign set)\n" +
                        "JNS                 SF = 0           (non-negative)\n" +
                        "JO                  OF = 1           (overflow)\n" +
                        "JNO                 OF = 0           (no overflow)"
                    )
                }
            }

            // 3 — Jump Mechanics
            item {
                SectionCard("Jump Mechanics — How the Offset Works") {
                    BodyText(
                        "The rel8 and rel32 operands in a jump instruction are signed offsets relative to the " +
                        "address of the instruction immediately after the jump — i.e., to RIP after the jump has been fetched."
                    )
                    CodeBlock(
                        "new RIP = (address of jump instruction + size of jump) + signed offset\n\n" +
                        "Example:\n" +
                        "  Address 0x1000: JE short 0x10   ; EB 10  (2 bytes)\n" +
                        "  If ZF=1:\n" +
                        "    next RIP = (0x1000 + 2) + 0x10 = 0x1012\n" +
                        "  If ZF=0:\n" +
                        "    next RIP = 0x1002  (fall through)"
                    )
                    BodyText(
                        "A negative offset jumps backward. Example: a loop body ends with " +
                        "JNZ -N which jumps back to the loop header until the counter reaches zero."
                    )
                }
            }

            // 4 — Assembler / Compiler Optimisation
            item {
                SectionCard("Assembler & Compiler Optimisation") {
                    BodyText(
                        "Assemblers perform a two-pass layout: on the first pass every branch gets a short placeholder; " +
                        "on the second pass, if the target is out of short range, the instruction is promoted to the near form " +
                        "and surrounding offsets are recalculated."
                    )
                    BodyText(
                        "Compilers exploit branch prediction heuristics. A taken branch stalls the pipeline on older " +
                        "architectures. To place the hot (frequent) path as the fall-through:"
                    )
                    CodeBlock(
                        "// C:\n" +
                        "if (unlikely(error)) { handle_error(); }\n" +
                        "normal_path();\n\n" +
                        "// Compiler emits:\n" +
                        "    TEST EAX, EAX\n" +
                        "    JNZ  .error          ; branch taken only on error (rare)\n" +
                        "    ; hot path falls through here\n" +
                        "    CALL normal_path\n" +
                        "    ...\n" +
                        ".error:\n" +
                        "    CALL handle_error"
                    )
                    BodyText(
                        "Modern CPUs use dynamic branch predictors (BTB, TAGE) that learn the actual taken/not-taken " +
                        "history, so the compiler hint matters most for cold code or first-run performance."
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
