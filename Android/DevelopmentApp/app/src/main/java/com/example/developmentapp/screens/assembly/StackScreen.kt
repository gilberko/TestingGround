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
fun StackScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Stack", color = Color(0xFF00FF41), fontFamily = FontFamily.Monospace, fontSize = 16.sp)
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
            modifier       = Modifier.fillMaxSize().padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            item {
                SectionCard("RSP and RBP — The Stack Registers") {
                    BodyText("RSP (Stack Pointer) always points to the current top of the stack — the last value pushed. The x86 stack grows downward toward lower addresses.")
                    CodeBlock(
                        "PUSH operand:\n" +
                        "  RSP -= 8         ; allocate space\n" +
                        "  [RSP] = operand  ; write value\n\n" +
                        "POP dest:\n" +
                        "  dest = [RSP]     ; read value\n" +
                        "  RSP += 8         ; deallocate space"
                    )
                    BodyText("RBP (Base Pointer) anchors the current stack frame. It does not move during the function body, so locals and arguments can always be accessed via fixed offsets from RBP regardless of how many PUSHes happen inside the function.")
                    CodeBlock(
                        "RBP + 0   → saved old RBP\n" +
                        "RBP + 8   → return address\n" +
                        "RBP + 16  → first stack argument (if any)\n" +
                        "RBP - 4   → first local variable (4-byte)\n" +
                        "RBP - 8   → second local variable (4-byte)"
                    )
                }
            }

            item {
                SectionCard("PUSH Variants") {
                    CodeBlock(
                        "PUSH r64          ; push 64-bit register   (RSP -= 8)\n" +
                        "PUSH r/m64        ; push 64-bit memory operand\n" +
                        "PUSH imm8         ; push sign-extended 8-bit immediate\n" +
                        "PUSH imm32        ; push sign-extended 32-bit immediate\n\n" +
                        "; Special pushes:\n" +
                        "PUSHF  / PUSHFD   ; push 16/32-bit EFLAGS  (RSP -= 2/4)\n" +
                        "PUSHFQ            ; push 64-bit RFLAGS     (RSP -= 8)\n\n" +
                        "; 32-bit only (invalid in 64-bit mode):\n" +
                        "PUSHA  / PUSHAD   ; push all 8 GPRs (AX..DI / EAX..EDI)"
                    )
                    BodyText("In 64-bit mode only 64-bit (or 16-bit with prefix) PUSH is available. There is no 32-bit PUSH in 64-bit mode. PUSHA/PUSHAD are invalid in 64-bit mode.")
                }
            }

            item {
                SectionCard("POP Variants") {
                    CodeBlock(
                        "POP r64           ; pop into 64-bit register  (RSP += 8)\n" +
                        "POP r/m64         ; pop into 64-bit memory operand\n\n" +
                        "; Special pops:\n" +
                        "POPF  / POPFD     ; pop 16/32-bit flags from stack into EFLAGS\n" +
                        "POPFQ             ; pop 64-bit RFLAGS\n\n" +
                        "; 32-bit only:\n" +
                        "POPA  / POPAD     ; pop all 8 GPRs (reverse of PUSHA)\n\n" +
                        "; Useful pattern — adjust RSP without POP:\n" +
                        "ADD RSP, 8        ; discard top 8 bytes (skip a value)"
                    )
                }
            }

            item {
                SectionCard("Stack Alignment") {
                    BodyText(
                        "The 64-bit System V ABI (Linux/macOS) and Windows x64 ABI both require RSP to be " +
                        "16-byte aligned at the point of a CALL instruction. This means RSP must be 8-byte aligned " +
                        "just before CALL (the CALL itself pushes 8 bytes, making it 16-byte aligned inside the callee's prologue)."
                    )
                    CodeBlock(
                        "; Checking alignment:\n" +
                        "AND RSP, -16      ; round RSP down to 16-byte boundary\n\n" +
                        "; Why it matters:\n" +
                        "; SSE/AVX instructions (MOVAPS, MOVDQA) fault on\n" +
                        "; unaligned memory access if the stack is misaligned.\n" +
                        "; The compiler inserts alignment fixes in prologues."
                    )
                }
            }

            item {
                SectionCard("Function Calls, Prologue & Epilogue (Recap)") {
                    BodyText("Every function call involves three phases:")
                    CodeBlock(
                        "; ─── Caller: before CALL ───────────────────────────────\n" +
                        "; Place arguments (registers or stack per ABI)\n" +
                        "CALL  target       ; push RIP+5, jump to target\n\n" +
                        "; ─── Callee: prologue ──────────────────────────────────\n" +
                        "PUSH  RBP          ; save caller's frame pointer\n" +
                        "MOV   RBP, RSP     ; establish new frame pointer\n" +
                        "SUB   RSP, N       ; allocate N bytes for locals (N % 16 == 0)\n\n" +
                        "; ─── Callee: epilogue ──────────────────────────────────\n" +
                        "LEAVE              ; MOV RSP,RBP  +  POP RBP\n" +
                        "RET                ; pop return address → RIP"
                    )
                    BodyText(
                        "Callee-saved registers (must be preserved across a call): " +
                        "RBX, RBP, R12–R15 (System V); RBX, RBP, RDI, RSI, R12–R15 (Windows x64). " +
                        "All other GPRs are caller-saved (may be clobbered by the callee)."
                    )
                }
            }

            item {
                SectionCard("Stack Frames and the Call Stack") {
                    BodyText("Every active function owns one stack frame. Frames are linked through the chain of saved RBP values, forming the call stack that debuggers display.")
                    CodeBlock(
                        "Higher addresses\n" +
                        "┌───────────────────┐\n" +
                        "│  main() frame     │  ← outermost frame\n" +
                        "│  saved RBP = 0    │\n" +
                        "│  return addr      │\n" +
                        "├───────────────────┤\n" +
                        "│  foo() frame      │\n" +
                        "│  saved RBP ──────────→ main's RBP\n" +
                        "│  return addr      │\n" +
                        "├───────────────────┤\n" +
                        "│  bar() frame      │  ← current function\n" +
                        "│  saved RBP ──────────→ foo's RBP\n" +
                        "│  return addr      │\n" +
                        "│  locals ...       │\n" +
                        "RSP→└───────────────────┘\n" +
                        "Lower addresses"
                    )
                    BodyText("Stack unwinding (for exceptions or backtraces) walks this linked list of saved RBPs. Optimized builds (-O2) may omit RBP and use DWARF CFI (Call Frame Information) tables instead.")
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
