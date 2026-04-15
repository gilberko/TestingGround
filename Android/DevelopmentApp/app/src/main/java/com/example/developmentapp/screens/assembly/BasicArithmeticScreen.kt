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
fun BasicArithmeticScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Basic Arithmetic",
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
            // 1 — Bitwise
            item {
                SectionCard("Bitwise Operations — AND, OR, XOR") {
                    BodyText("AND — bits are 1 only where both operands have 1. Used for masking.")
                    CodeBlock(
                        "AND EAX, 0xFF     ; keep only the low byte of EAX\n" +
                        "AND EAX, EBX      ; EAX = EAX & EBX"
                    )
                    BodyText("OR — bits are 1 where at least one operand has 1. Used for setting bits.")
                    CodeBlock(
                        "OR  EAX, 0x01     ; set bit 0 of EAX\n" +
                        "OR  EAX, EBX      ; EAX = EAX | EBX"
                    )
                    BodyText("XOR — bits are 1 where the operands differ. Used for toggling bits, and the fastest way to zero a register.")
                    CodeBlock(
                        "XOR EAX, EAX      ; EAX = 0  (2 bytes — fastest zero)\n" +
                        "XOR EAX, 0x01     ; toggle bit 0 of EAX\n" +
                        "XOR EAX, EBX      ; EAX = EAX ^ EBX"
                    )
                    BodyText(
                        "AND, OR, and XOR always clear CF and OF. They update ZF (result == 0), " +
                        "SF (result is negative), and PF (parity of low byte)."
                    )
                }
            }

            // 2 — ADD / SUB
            item {
                SectionCard("ADD and SUB") {
                    BodyText("ADD computes dest = dest + src. SUB computes dest = dest - src (via two's complement addition).")
                    CodeBlock(
                        "ADD EAX, ECX      ; EAX += ECX\n" +
                        "ADD RAX, 8        ; RAX += 8   (immediate)\n" +
                        "ADD [RSP], RAX    ; memory += RAX\n\n" +
                        "SUB RBX, RDX      ; RBX -= RDX\n" +
                        "SUB ESP, 16       ; allocate 16 bytes on stack"
                    )
                    BodyText("Flags updated by ADD and SUB:")
                    CodeBlock(
                        "CF — set on unsigned overflow (carry out / borrow)\n" +
                        "OF — set on signed overflow\n" +
                        "ZF — set when result is zero\n" +
                        "SF — set when result is negative (MSB = 1)\n" +
                        "PF — set when low byte has even number of 1-bits"
                    )
                    BodyText(
                        "ADC (Add with Carry) and SBB (Subtract with Borrow) are the multi-precision variants that " +
                        "include the CF from the previous operation."
                    )
                }
            }

            // 3 — MUL / IMUL
            item {
                SectionCard("MUL and IMUL") {
                    BodyText(
                        "MUL performs unsigned multiplication. The implicit destination is always rAX, " +
                        "and the result is double-width to avoid overflow."
                    )
                    CodeBlock(
                        "MUL ECX           ; EDX:EAX = EAX * ECX  (unsigned 32-bit)\n" +
                        "MUL RCX           ; RDX:RAX = RAX * RCX  (unsigned 64-bit)\n" +
                        "                  ; High bits in RDX, low bits in RAX"
                    )
                    BodyText(
                        "IMUL performs signed multiplication. Its one-operand form also uses rDX:rAX. " +
                        "The two-operand and three-operand forms are more convenient and truncate to the register size."
                    )
                    CodeBlock(
                        "IMUL ECX           ; EDX:EAX = EAX * ECX  (signed, 1-op form)\n\n" +
                        "IMUL EAX, ECX      ; EAX = EAX * ECX      (2-op — no EDX involvement)\n" +
                        "IMUL EAX, ECX, 4   ; EAX = ECX * 4        (3-op — immediate scale)\n\n" +
                        "IMUL RAX, RBX      ; RAX = RAX * RBX      (64-bit, 2-op)"
                    )
                }
            }

            // 4 — DIV / IDIV
            item {
                SectionCard("DIV and IDIV") {
                    BodyText(
                        "DIV divides the double-width dividend rDX:rAX by the operand. " +
                        "Quotient → rAX, remainder → rDX. IDIV does the same for signed integers."
                    )
                    CodeBlock(
                        "// Unsigned 32-bit: divide EDX:EAX by ECX\n" +
                        "XOR EDX, EDX      ; clear EDX (high half of dividend)\n" +
                        "MOV EAX, 100      ; low half\n" +
                        "MOV ECX, 7\n" +
                        "DIV ECX           ; EAX = 14 (quotient), EDX = 2 (remainder)\n\n" +
                        "// Signed 32-bit: divide EDX:EAX by ECX\n" +
                        "MOV EAX, -100\n" +
                        "CDQ               ; sign-extend EAX into EDX:EAX\n" +
                        "MOV ECX, 7\n" +
                        "IDIV ECX          ; EAX = -14, EDX = -2\n\n" +
                        "// 64-bit unsigned\n" +
                        "XOR RDX, RDX\n" +
                        "DIV RCX           ; RDX:RAX / RCX"
                    )
                    BodyText(
                        "A divide-by-zero or quotient overflow (result doesn't fit in rAX) triggers a #DE exception. " +
                        "Always prepare rDX before dividing: XOR RDX,RDX for unsigned, CQO/CDQ for signed."
                    )
                }
            }

            // 5 — Operand Sizes
            item {
                SectionCard("Operand Sizes") {
                    BodyText("x86 instructions operate on 8, 16, 32, or 64-bit operands. The size is inferred from the register name or an explicit size prefix.")
                    CodeBlock(
                        "Size     Width   Register examples   Example instruction\n" +
                        "byte     8-bit   AL, CL, R8B         ADD AL,  5\n" +
                        "word    16-bit   AX, CX, R8W         ADD AX,  CX\n" +
                        "dword   32-bit   EAX, ECX, R8D       ADD EAX, ECX\n" +
                        "qword   64-bit   RAX, RCX, R8        ADD RAX, RCX"
                    )
                    BodyText(
                        "Memory operands may need an explicit size hint: BYTE PTR [RSI], WORD PTR [RSI], " +
                        "DWORD PTR [RSI], QWORD PTR [RSI]. Required when the assembler cannot infer the size from context."
                    )
                }
            }

            // 6 — Instruction Byte Sizes
            item {
                SectionCard("Instruction Byte Sizes") {
                    BodyText(
                        "x86 is a variable-length instruction set — instructions range from 1 to 15 bytes. " +
                        "The size depends on: opcode, ModRM byte, SIB byte, displacement, and immediate value."
                    )
                    CodeBlock(
                        "Instruction              Bytes   Notes\n" +
                        "NOP                        1     90h\n" +
                        "ADD EAX, ECX               2     opcode + ModRM\n" +
                        "ADD EAX, 1                 3     opcode + ModRM + imm8\n" +
                        "ADD EAX, 0x00001000        6     opcode + ModRM + imm32\n" +
                        "MOV RAX, 0x1122334455667788 10    REX.W + opcode + imm64\n" +
                        "MOV [RBX+RCX*4+0x10], RAX  8     REX + op + ModRM + SIB + disp8"
                    )
                    BodyText(
                        "The REX prefix (0x4X) is required in 64-bit mode to access 64-bit operand sizes or the extended registers R8–R15. " +
                        "It adds one byte to the encoding."
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
