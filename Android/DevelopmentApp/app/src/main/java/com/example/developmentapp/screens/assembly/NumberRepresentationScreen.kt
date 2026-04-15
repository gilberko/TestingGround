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
fun NumberRepresentationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Number Representation", color = Color(0xFF00FF41), fontFamily = FontFamily.Monospace, fontSize = 16.sp)
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
                SectionCard("Unsigned Integers — Plain Binary") {
                    BodyText("An unsigned integer stores a non-negative value. Every bit contributes its place value (1, 2, 4, 8 …). For an N-bit register the range is 0 to 2^N - 1.")
                    CodeBlock(
                        "8-bit unsigned range:  0 to 255\n" +
                        "16-bit:                0 to 65,535\n" +
                        "32-bit:                0 to 4,294,967,295\n" +
                        "64-bit:                0 to 18,446,744,073,709,551,615\n\n" +
                        "Examples (8-bit):\n" +
                        "  0   = 0000 0000\n" +
                        "  1   = 0000 0001\n" +
                        "  127 = 0111 1111\n" +
                        "  128 = 1000 0000\n" +
                        "  255 = 1111 1111"
                    )
                }
            }

            item {
                SectionCard("Signed Integers — Two's Complement") {
                    BodyText(
                        "x86 uses two's complement to represent signed integers. The most significant bit (MSB) is the sign bit: " +
                        "0 = positive (or zero), 1 = negative. The range for N bits is −2^(N−1) to +2^(N−1)−1."
                    )
                    BodyText("To negate a two's complement number: invert all bits, then add 1.")
                    CodeBlock(
                        "8-bit signed range:   -128 to +127\n" +
                        "16-bit:              -32,768 to +32,767\n" +
                        "32-bit:        -2,147,483,648 to +2,147,483,647\n" +
                        "64-bit: -9,223,372,036,854,775,808 to +9,223,372,036,854,775,807"
                    )
                }
            }

            item {
                SectionCard("8-bit Examples — Positive, Zero, Negative") {
                    CodeBlock(
                        "Value  Binary       Hex   Notes\n" +
                        "+127   0111 1111    0x7F  largest positive 8-bit\n" +
                        "+5     0000 0101    0x05\n" +
                        "+1     0000 0001    0x01\n" +
                        " 0     0000 0000    0x00\n" +
                        "-1     1111 1111    0xFF  (invert 0x01 → 0xFE, +1 → 0xFF)\n" +
                        "-5     1111 1011    0xFB\n" +
                        "-127   1000 0001    0x81\n" +
                        "-128   1000 0000    0x80  most negative 8-bit (no positive twin!)"
                    )
                    BodyText(
                        "Notice: -128 in 8-bit two's complement has no positive counterpart — attempting to negate it gives -128 again (overflow). " +
                        "This is why INT_MIN behaves strangely in C."
                    )
                }
            }

            item {
                SectionCard("32-bit and 64-bit Examples") {
                    CodeBlock(
                        "32-bit register (EAX):\n" +
                        "  +1        = 0x00000001\n" +
                        "   0        = 0x00000000\n" +
                        "  -1        = 0xFFFFFFFF\n" +
                        "  -2        = 0xFFFFFFFE\n" +
                        "  INT_MAX   = 0x7FFFFFFF  (+2,147,483,647)\n" +
                        "  INT_MIN   = 0x80000000  (-2,147,483,648)\n" +
                        "  UINT_MAX  = 0xFFFFFFFF  (4,294,967,295 — if unsigned)\n\n" +
                        "64-bit register (RAX):\n" +
                        "  +1        = 0x0000000000000001\n" +
                        "  -1        = 0xFFFFFFFFFFFFFFFF\n" +
                        "  LLONG_MAX = 0x7FFFFFFFFFFFFFFF\n" +
                        "  LLONG_MIN = 0x8000000000000000"
                    )
                    BodyText(
                        "The CPU does not store a 'signed' or 'unsigned' tag with the bits — the same bit pattern is interpreted differently " +
                        "by signed vs unsigned instructions. For example: 0xFFFFFFFF is -1 for IDIV/IMUL/SAR/JL, but 4,294,967,295 for DIV/MUL/SHR/JB."
                    )
                }
            }

            item {
                SectionCard("Two's Complement — How Negation Works") {
                    BodyText("To compute -N: flip all bits (ones' complement), then add 1. The NEG instruction does this in one step.")
                    CodeBlock(
                        "; Negate +5 in 8 bits:\n" +
                        "  +5     = 0000 0101\n" +
                        "  invert = 1111 1010  (ones' complement = 0xFA)\n" +
                        "  + 1    = 1111 1011  (0xFB = -5 in two's complement)\n\n" +
                        "; Verify: +5 + (-5) = 0\n" +
                        "  0000 0101\n" +
                        "+ 1111 1011\n" +
                        "= 0000 0000  (plus carry out = 1, which is discarded)\n\n" +
                        "; NEG instruction:\n" +
                        "MOV AL, 5\n" +
                        "NEG AL       ; AL = -5 = 0xFB"
                    )
                }
            }

            item {
                SectionCard("Same Bits, Different Meaning") {
                    BodyText(
                        "The x86 ADD/SUB/AND/OR/XOR instructions work identically for signed and unsigned values " +
                        "— the bit operations are the same. Only the interpretation of the result (and which flags/jumps you use) differs."
                    )
                    CodeBlock(
                        "; Bit pattern 0xFF in AL:\n" +
                        ";   As unsigned: 255\n" +
                        ";   As signed:   -1\n\n" +
                        "; Adding 0xFF + 0x01:\n" +
                        "MOV AL, 0xFF\n" +
                        "ADD AL, 1\n" +
                        "; Result: AL = 0x00, CF=1, ZF=1, OF=0\n" +
                        ";   Unsigned: 255 + 1 = 256 — wrapped to 0, CF indicates overflow\n" +
                        ";   Signed:   -1  + 1 =   0 — correct, no signed overflow (OF=0)\n\n" +
                        "; Use JB/JA for unsigned comparisons (check CF)\n" +
                        "; Use JL/JG for signed  comparisons (check SF/OF)"
                    )
                }
            }

            item {
                SectionCard("Floating-Point — IEEE 754 (Brief Overview)") {
                    BodyText("Floating-point values are NOT stored as two's complement. They use the IEEE 754 format with three fields:")
                    CodeBlock(
                        "32-bit float (single precision):\n" +
                        "  [S][  Exponent 8 bits  ][     Mantissa 23 bits     ]\n" +
                        "  S=sign, exponent biased by 127, mantissa = 1.fraction\n\n" +
                        "64-bit double (double precision):\n" +
                        "  [S][ Exponent 11 bits ][        Mantissa 52 bits       ]\n" +
                        "  Exponent biased by 1023\n\n" +
                        "Special values:\n" +
                        "  +0  = 0 00000000 00000000000000000000000\n" +
                        "  +∞  = 0 11111111 00000000000000000000000\n" +
                        "  NaN = x 11111111 (any non-zero mantissa)\n\n" +
                        "x86 floating-point uses XMM registers and SSE2 instructions:\n" +
                        "  MOVSS  XMM0, [f]     ; load 32-bit float\n" +
                        "  ADDSS  XMM0, XMM1    ; add two floats\n" +
                        "  ADDSD  XMM0, XMM1    ; add two doubles"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
