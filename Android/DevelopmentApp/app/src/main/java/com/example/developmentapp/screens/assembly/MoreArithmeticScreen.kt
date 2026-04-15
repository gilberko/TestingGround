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
fun MoreArithmeticScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("More Arithmetic", color = Color(0xFF00FF41), fontFamily = FontFamily.Monospace, fontSize = 16.sp)
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
                SectionCard("SHL / SAL — Shift Left") {
                    BodyText(
                        "SHL (Shift Logical Left) and SAL (Shift Arithmetic Left) are identical — x86 has one opcode for both. " +
                        "Bits shift left by N positions; zeros fill from the right. The last bit shifted out goes into CF."
                    )
                    CodeBlock(
                        "SHL EAX, 1       ; EAX = EAX * 2  (multiply by 2^1)\n" +
                        "SHL EAX, 3       ; EAX = EAX * 8  (multiply by 2^3)\n" +
                        "SHL RAX, CL      ; shift count from CL register (0–63)\n" +
                        "SHL EAX, 1       ; also sets OF if sign bit changed\n\n" +
                        "; 8-bit example:\n" +
                        "; AL = 0000 0011 (3)\n" +
                        "; SHL AL, 2\n" +
                        "; AL = 0000 1100 (12)   CF = 0"
                    )
                    BodyText("Overflow with SHL: CF is set to the last bit shifted out. OF is defined only for 1-bit shifts: OF=1 if the sign bit changed (signed overflow). For shifts > 1, OF is undefined.")
                }
            }

            item {
                SectionCard("SHR — Shift Right Logical") {
                    BodyText(
                        "SHR shifts bits right by N positions and fills the vacated high bits with zeros, regardless of sign. " +
                        "This is an unsigned right shift — equivalent to dividing by 2^N for unsigned values."
                    )
                    CodeBlock(
                        "SHR EAX, 1       ; EAX = EAX / 2  (unsigned)\n" +
                        "SHR EAX, 4       ; EAX = EAX / 16 (unsigned)\n\n" +
                        "; 8-bit example (signed negative):\n" +
                        "; AL = 1000 0100 (-124 signed, 132 unsigned)\n" +
                        "; SHR AL, 1\n" +
                        "; AL = 0100 0010 (66)   — sign bit replaced with 0\n" +
                        "; CF = 0 (last shifted-out bit)\n\n" +
                        "; For signed division use SAR instead!"
                    )
                }
            }

            item {
                SectionCard("SAR — Shift Right Arithmetic") {
                    BodyText(
                        "SAR shifts bits right by N positions and fills the vacated high bits with copies of the original sign bit. " +
                        "This preserves the sign — equivalent to signed integer division by 2^N (rounds toward negative infinity)."
                    )
                    CodeBlock(
                        "SAR EAX, 1       ; EAX = EAX / 2  (signed, rounds toward -∞)\n" +
                        "SAR RAX, 3       ; RAX = RAX / 8  (signed)\n\n" +
                        "; 8-bit example (negative number):\n" +
                        "; AL = 1000 0100 (-124 signed)\n" +
                        "; SAR AL, 1\n" +
                        "; AL = 1100 0010 (-62)  — sign bit copied left\n" +
                        "; CF = 0\n\n" +
                        "; 8-bit example (positive number):\n" +
                        "; AL = 0100 1000 (72)\n" +
                        "; SAR AL, 2\n" +
                        "; AL = 0001 0010 (18)   — zeros fill from left"
                    )
                    BodyText("SAR by 1 is faster than IDIV by 2 and is commonly emitted by compilers for dividing signed integers by powers of 2. Note: SAR rounds toward −∞, while C integer division truncates toward zero — for negative values these differ by 1.")
                }
            }

            item {
                SectionCard("ROL / ROR — Rotate") {
                    BodyText("Rotate instructions shift bits in a circle — bits that fall off one end reappear at the other. CF gets the last rotated bit.")
                    CodeBlock(
                        "ROL EAX, 1       ; rotate left  1 bit: MSB → LSB, MSB → CF\n" +
                        "ROR EAX, 1       ; rotate right 1 bit: LSB → MSB, LSB → CF\n" +
                        "ROL EAX, 8       ; rotate left  8 bits (byte swap within 32-bit)\n\n" +
                        "; 8-bit example:\n" +
                        "; AL = 1000 0001\n" +
                        "; ROL AL, 1\n" +
                        "; AL = 0000 0011   CF = 1 (the old MSB)"
                    )
                }
            }

            item {
                SectionCard("RCL / RCR — Rotate Through Carry") {
                    BodyText("RCL and RCR rotate through the carry flag — CF becomes part of the rotation (effectively a 9-bit or 65-bit rotate for 8-bit or 64-bit operands).")
                    CodeBlock(
                        "RCL EAX, 1       ; 33-bit rotate left:  CF → bit0, old MSB → CF\n" +
                        "RCR EAX, 1       ; 33-bit rotate right: CF → MSB,  old bit0 → CF\n\n" +
                        "; Useful for multi-precision shifts:\n" +
                        "; Shift a 128-bit value in [RDX:RAX] left by 1:\n" +
                        "SHL RAX, 1       ; shifts RAX left, old MSB → CF\n" +
                        "RCL RDX, 1       ; RDX left, CF (old RAX MSB) enters RDX bit 0"
                    )
                }
            }

            item {
                SectionCard("Sign Extension") {
                    BodyText(
                        "Sign extension copies the sign bit of a smaller value into all upper bits of a larger container, " +
                        "preserving the signed value. Zero extension fills upper bits with zeros (for unsigned values)."
                    )
                    CodeBlock(
                        "; 8-bit → 16-bit:  CBW  (sign-extend AL into AX)\n" +
                        "; 16-bit → 32-bit: CWDE (sign-extend AX into EAX)\n" +
                        "; 32-bit → 64-bit: CDQE (sign-extend EAX into RAX)\n\n" +
                        "; 16-bit → 32-bit: CWD  (sign-extend AX into DX:AX)\n" +
                        "; 32-bit → 64-bit: CDQ  (sign-extend EAX into EDX:EAX)\n" +
                        "; 64-bit → 128:    CQO  (sign-extend RAX into RDX:RAX)\n\n" +
                        "; Example:\n" +
                        "; AL  = 1111 1110  (-2 as 8-bit signed)\n" +
                        "; CBW\n" +
                        "; AX  = 1111 1111 1111 1110  (-2 as 16-bit)\n\n" +
                        "; MOVSX also sign-extends:\n" +
                        "MOVSX RAX, BYTE PTR [rbx]   ; 8-bit → 64-bit, sign extended"
                    )
                    BodyText("CDQ / CQO are required before IDIV to prepare the double-width dividend EDX:EAX / RDX:RAX.")
                }
            }

            item {
                SectionCard("SHLD / SHRD — Double Precision Shift") {
                    BodyText("SHLD and SHRD shift two registers together, pulling bits from one into the other. Used for shifting values wider than 64 bits.")
                    CodeBlock(
                        "; SHLD dst, src, count\n" +
                        ";   dst <<= count, bits shifted out replaced by high bits of src\n\n" +
                        "; SHRD dst, src, count\n" +
                        ";   dst >>= count, bits shifted out replaced by low bits of src\n\n" +
                        "; Shift a 128-bit value RDX:RAX left by 3:\n" +
                        "SHLD RDX, RAX, 3   ; RDX gets 3 high bits from RAX\n" +
                        "SHL  RAX, 3        ; RAX shifted (low bits become 0)"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
