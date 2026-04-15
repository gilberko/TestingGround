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
fun ComparisonsLoopsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Comparisons & Loops", color = Color(0xFF00FF41), fontFamily = FontFamily.Monospace, fontSize = 16.sp)
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
                SectionCard("The Flags Register") {
                    BodyText("Most arithmetic and logical instructions update the flags register (EFLAGS / RFLAGS). Key flags:")
                    CodeBlock(
                        "CF  Carry Flag     — unsigned overflow/borrow; set by shifts, ADD, SUB\n" +
                        "ZF  Zero Flag      — result is zero\n" +
                        "SF  Sign Flag      — result MSB = 1 (negative in two's complement)\n" +
                        "OF  Overflow Flag  — signed overflow (result doesn't fit as signed)\n" +
                        "PF  Parity Flag    — low byte has even number of 1-bits\n" +
                        "AF  Adjust Flag    — carry out of bit 3 (BCD arithmetic)\n" +
                        "DF  Direction Flag — controls MOVS/CMPS/SCAS direction\n" +
                        "IF  Interrupt Flag — enables/disables maskable hardware interrupts\n" +
                        "TF  Trap Flag      — single-step mode (debuggers)"
                    )
                    BodyText("Yes, OF (Overflow Flag) is a full flag. It is set when a signed operation produces a result that cannot be represented in the destination size.")
                }
            }

            item {
                SectionCard("CMP — Compare Without Storing") {
                    BodyText(
                        "CMP performs a subtraction (dst - src) and updates flags, but discards the result. " +
                        "The destination register is unchanged. Flags are set exactly as SUB would set them."
                    )
                    CodeBlock(
                        "CMP RAX, RBX      ; flags = RAX - RBX  (RAX unchanged)\n" +
                        "CMP EAX, 0        ; flags = EAX - 0\n" +
                        "CMP [mem], RDX    ; flags based on [mem] - RDX\n\n" +
                        "; After CMP, use a conditional jump:\n" +
                        "CMP RAX, RBX\n" +
                        "JE  .equal        ; jump if RAX == RBX  (ZF=1)\n" +
                        "JL  .less         ; jump if RAX < RBX   (signed)\n" +
                        "JB  .below        ; jump if RAX < RBX   (unsigned)"
                    )
                }
            }

            item {
                SectionCard("TEST — Bitwise AND Without Storing") {
                    BodyText(
                        "TEST performs a bitwise AND and updates ZF, SF, PF — and clears CF and OF. " +
                        "The result is discarded. It is the standard way to check whether a register is zero or whether a specific bit is set."
                    )
                    CodeBlock(
                        "TEST RAX, RAX     ; ZF=1 if RAX==0 (cheapest zero-check)\n" +
                        "JZ   .is_zero\n\n" +
                        "TEST EAX, 1       ; ZF=0 if bit 0 is set (odd/even check)\n" +
                        "JNZ  .is_odd\n\n" +
                        "TEST AL, 0x80     ; check sign bit of AL\n" +
                        "JS   .negative    ; SF=1 if bit 7 set\n\n" +
                        "; TEST is preferred over CMP reg,0 because it is\n" +
                        "; one byte shorter (no immediate) and equally fast."
                    )
                }
            }

            item {
                SectionCard("SUB and XOR — Do They Always Change Flags?") {
                    BodyText(
                        "Yes — SUB, XOR, AND, OR, ADD and most arithmetic/logical instructions always update the flags. " +
                        "There is no variant that suppresses flag writes."
                    )
                    BodyText("If you need to preserve the flags across an operation:")
                    CodeBlock(
                        "; Option 1: save and restore with PUSHF/POPF\n" +
                        "PUSHFQ             ; push RFLAGS onto stack\n" +
                        "ADD RAX, 1         ; this changes flags\n" +
                        "POPFQ              ; restore original flags\n\n" +
                        "; Option 2: save flags to a register via LAHF/SAHF\n" +
                        "LAHF               ; AH = low 8 bits of EFLAGS (SF,ZF,AF,PF,CF)\n" +
                        "ADD RAX, 1\n" +
                        "SAHF               ; restore SF,ZF,AF,PF,CF from AH\n\n" +
                        "; Note: LAHF/SAHF only cover the low 8 flag bits (not OF)."
                    )
                    BodyText("MOV, LEA, XCHG (reg-reg) and a few others are notable exceptions that do NOT touch flags.")
                }
            }

            item {
                SectionCard("Flag Management Instructions") {
                    CodeBlock(
                        "; Carry flag:\n" +
                        "STC               ; Set Carry   (CF=1)\n" +
                        "CLC               ; Clear Carry (CF=0)\n" +
                        "CMC               ; Complement Carry (CF = !CF)\n\n" +
                        "; Direction flag (controls MOVS/CMPS/SCAS/LODS/STOS):\n" +
                        "CLD               ; Clear Direction (DF=0 → addresses increment)\n" +
                        "STD               ; Set Direction   (DF=1 → addresses decrement)\n\n" +
                        "; Interrupt flag (ring 0 only):\n" +
                        "STI               ; Set Interrupt enable (IF=1)\n" +
                        "CLI               ; Clear Interrupt enable (IF=0)\n\n" +
                        "; Full flags save/restore:\n" +
                        "PUSHFQ            ; push RFLAGS\n" +
                        "POPFQ             ; pop RFLAGS\n" +
                        "LAHF              ; AH = SF|ZF|0|AF|0|PF|1|CF\n" +
                        "SAHF              ; restore those bits from AH"
                    )
                }
            }

            item {
                SectionCard("SETcc — Set Byte on Condition") {
                    BodyText("SETcc instructions write 1 or 0 into an 8-bit register based on a flag condition. Useful for branchless code.")
                    CodeBlock(
                        "SETE   AL         ; AL = 1 if ZF=1  (equal)\n" +
                        "SETNE  AL         ; AL = 1 if ZF=0  (not equal)\n" +
                        "SETG   AL         ; AL = 1 if ZF=0 AND SF=OF (signed >)\n" +
                        "SETL   AL         ; AL = 1 if SF≠OF           (signed <)\n" +
                        "SETA   AL         ; AL = 1 if CF=0 AND ZF=0   (unsigned >)\n" +
                        "SETB   AL         ; AL = 1 if CF=1             (unsigned <)\n\n" +
                        "; Branchless absolute value:\n" +
                        "MOV  RBX, RAX\n" +
                        "NEG  RAX\n" +
                        "CMOVL RAX, RBX    ; if original was ≥0, keep original"
                    )
                }
            }

            item {
                SectionCard("CMOVcc — Conditional Move") {
                    BodyText("CMOVcc conditionally moves a value based on flags — no branch, no pipeline stall.")
                    CodeBlock(
                        "CMOVE  RAX, RBX   ; if ZF=1 → RAX = RBX\n" +
                        "CMOVNE RAX, RBX   ; if ZF=0 → RAX = RBX\n" +
                        "CMOVG  RAX, RBX   ; if signed >  → RAX = RBX\n" +
                        "CMOVL  RAX, RBX   ; if signed <  → RAX = RBX\n\n" +
                        "; Example: max(a, b) without branch:\n" +
                        "CMP  RAX, RBX\n" +
                        "CMOVL RAX, RBX    ; if RAX < RBX → RAX = RBX"
                    )
                }
            }

            item {
                SectionCard("Conditional Jumps — Quick Reference") {
                    CodeBlock(
                        "JE  / JZ    ZF=1            equal / zero\n" +
                        "JNE / JNZ   ZF=0            not equal\n" +
                        "JG  / JNLE  ZF=0,SF=OF      signed greater\n" +
                        "JGE / JNL   SF=OF           signed greater-or-equal\n" +
                        "JL  / JNGE  SF≠OF           signed less\n" +
                        "JLE / JNG   ZF=1 or SF≠OF   signed less-or-equal\n" +
                        "JA  / JNBE  CF=0,ZF=0       unsigned above\n" +
                        "JAE / JNB   CF=0             unsigned above-or-equal\n" +
                        "JB  / JNAE  CF=1             unsigned below\n" +
                        "JBE / JNA   CF=1 or ZF=1     unsigned below-or-equal\n" +
                        "JS          SF=1             negative (sign set)\n" +
                        "JNS         SF=0             non-negative\n" +
                        "JO          OF=1             overflow\n" +
                        "JNO         OF=0             no overflow\n" +
                        "JC          CF=1             carry\n" +
                        "JNC         CF=0             no carry\n" +
                        "JP  / JPE   PF=1             parity even\n" +
                        "JNP / JPO   PF=0             parity odd"
                    )
                }
            }

            item {
                SectionCard("Writing Loops — Manual Counter") {
                    BodyText("The most flexible loop is a manual counter in any register combined with a conditional jump.")
                    CodeBlock(
                        "; Sum array of 10 integers in RAX:\n" +
                        "    xor  rax, rax        ; sum = 0\n" +
                        "    xor  rcx, rcx        ; i = 0\n" +
                        ".loop:\n" +
                        "    add  rax, [arr + rcx*8]  ; sum += arr[i]\n" +
                        "    inc  rcx             ; i++\n" +
                        "    cmp  rcx, 10\n" +
                        "    jl   .loop           ; if i < 10 → repeat\n\n" +
                        "; Equivalent do-while (tests at bottom — one less jump):\n" +
                        "    xor  rax, rax\n" +
                        "    mov  rcx, 9\n" +
                        ".loop:\n" +
                        "    add  rax, [arr + rcx*8]\n" +
                        "    dec  rcx\n" +
                        "    jns  .loop           ; loop while RCX ≥ 0 (SF=0)"
                    )
                }
            }

            item {
                SectionCard("Writing Loops — LOOP Instruction") {
                    BodyText(
                        "The LOOP instruction decrements RCX (or ECX/CX) and jumps to the target if RCX ≠ 0. " +
                        "It is compact but on modern CPUs it is slower than DEC+JNZ because it is not as well predicted."
                    )
                    CodeBlock(
                        "; LOOP  rel8      — decrement RCX, jump if RCX ≠ 0\n" +
                        "; LOOPE rel8      — decrement RCX, jump if RCX ≠ 0 AND ZF=1\n" +
                        "; LOOPNE rel8     — decrement RCX, jump if RCX ≠ 0 AND ZF=0\n\n" +
                        "; Example — copy 8 bytes:\n" +
                        "    mov  rcx, 8\n" +
                        ".copy:\n" +
                        "    mov  al, [rsi]\n" +
                        "    mov  [rdi], al\n" +
                        "    inc  rsi\n" +
                        "    inc  rdi\n" +
                        "    loop .copy        ; RCX -= 1; jump if RCX ≠ 0\n\n" +
                        "; LOOPNE example — scan for first non-zero byte:\n" +
                        "    mov  rcx, 256\n" +
                        ".scan:\n" +
                        "    mov  al, [rsi]\n" +
                        "    inc  rsi\n" +
                        "    test al, al\n" +
                        "    loopne .scan      ; repeat if RCX≠0 AND ZF=0 (byte was zero)\n" +
                        "    ; when done: if ZF=1 found non-zero, if RCX=0 all were zero"
                    )
                    BodyText("For performance-critical loops, prefer DEC RCX + JNZ or a test-based conditional jump over the LOOP instruction.")
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
