package com.example.developmentapp.screens.python

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
fun PythonArithmeticScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Python — Arithmetic", color = Color(0xFF00FF41), fontFamily = FontFamily.Monospace, fontSize = 16.sp)
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
                SectionCard("Basic Arithmetic Operators") {
                    BodyText(
                        "Python supports all standard arithmetic operators. Note that / always returns a float — " +
                        "use // for integer (floor) division. ** is the exponentiation operator (no 'pow' keyword needed)."
                    )
                    CodeBlock(
                        "a = 10\n" +
                        "b = 3\n\n" +
                        "a + b      # 13   — addition\n" +
                        "a - b      # 7    — subtraction\n" +
                        "a * b      # 30   — multiplication\n" +
                        "a / b      # 3.333...  — true division (always float!)\n" +
                        "a // b     # 3    — floor division (rounds toward -infinity)\n" +
                        "a ** b     # 1000 — exponentiation (10^3)\n\n" +
                        "# Floor division with negatives:\n" +
                        "-7 // 2    # -4  (floors toward -infinity, not toward 0)\n" +
                        " 7 // 2    #  3\n\n" +
                        "# Mixing int and float:\n" +
                        "3 + 1.5    # 4.5  (result is float)\n" +
                        "10 / 2     # 5.0  (always float, even if evenly divisible)"
                    )
                }
            }

            item {
                SectionCard("Remainder — Modulo %") {
                    BodyText(
                        "The % operator returns the remainder after division. " +
                        "Unlike C, Python's modulo result always has the same sign as the divisor."
                    )
                    CodeBlock(
                        "10 % 3     # 1    — 10 = 3*3 + 1\n" +
                        "15 % 4     # 3    — 15 = 4*3 + 3\n" +
                        "10 % 2     # 0    — even number\n\n" +
                        "# Common uses:\n" +
                        "n % 2 == 0           # check if n is even\n" +
                        "n % 2 != 0           # check if n is odd\n" +
                        "index = (index + 1) % length  # wrap around a circular buffer\n\n" +
                        "# Sign follows divisor (differs from C):\n" +
                        "-7 % 3     # 2   (in C: -1)\n" +
                        " 7 % -3    # -2  (in C:  1)\n\n" +
                        "# divmod() returns quotient and remainder at once:\n" +
                        "q, r = divmod(17, 5)   # q=3, r=2"
                    )
                }
            }

            item {
                SectionCard("Operator Precedence") {
                    BodyText(
                        "Python follows standard mathematical precedence (PEMDAS). " +
                        "When in doubt, use parentheses — they make intent clear and override any precedence rules."
                    )
                    CodeBlock(
                        "# Highest to lowest precedence (for arithmetic):\n" +
                        "# ** (exponentiation)  — right-associative\n" +
                        "# *, /, //, %\n" +
                        "# +, -\n\n" +
                        "2 + 3 * 4      # 14  — * before +\n" +
                        "(2 + 3) * 4    # 20  — parentheses first\n" +
                        "2 ** 3 ** 2    # 512 — right-assoc: 2 ** (3**2) = 2**9\n" +
                        "(2 ** 3) ** 2  # 64\n\n" +
                        "10 - 3 - 2     # 5   — left-assoc: (10-3)-2\n" +
                        "4 * 3 / 2      # 6.0 — left-assoc: (4*3)/2\n\n" +
                        "# When mixing: always use parentheses for clarity:\n" +
                        "result = (a + b) * (c - d) / 2"
                    )
                }
            }

            item {
                SectionCard("Augmented Assignment") {
                    BodyText(
                        "Augmented assignment operators combine an operation with assignment. " +
                        "They modify the variable in place and are equivalent to the expanded form."
                    )
                    CodeBlock(
                        "x = 10\n\n" +
                        "x += 5     # x = x + 5  → 15\n" +
                        "x -= 3     # x = x - 3  → 12\n" +
                        "x *= 2     # x = x * 2  → 24\n" +
                        "x /= 4     # x = x / 4  → 6.0  (becomes float!)\n" +
                        "x //= 2    # x = x // 2 → 3.0  (floor div)\n" +
                        "x %= 2     # x = x % 2  → 1.0\n" +
                        "x **= 3    # x = x ** 3 → 1.0\n\n" +
                        "# Also works with strings and lists:\n" +
                        "s = 'hello'\n" +
                        "s += ' world'   # 'hello world'\n\n" +
                        "lst = [1, 2]\n" +
                        "lst += [3, 4]   # [1, 2, 3, 4]  (extends in place)"
                    )
                }
            }

            item {
                SectionCard("Bitwise Operators") {
                    BodyText(
                        "Python supports the same bitwise operators as C: shift left, shift right, AND, OR, XOR, and NOT. " +
                        "They operate on integers at the bit level. Python ints have arbitrary precision, so there is no overflow."
                    )
                    CodeBlock(
                        "a = 0b1100    # 12 in binary\n" +
                        "b = 0b1010    # 10 in binary\n\n" +
                        "a & b         # 0b1000 = 8   — bitwise AND\n" +
                        "a | b         # 0b1110 = 14  — bitwise OR\n" +
                        "a ^ b         # 0b0110 = 6   — bitwise XOR\n" +
                        "~a            # -13          — bitwise NOT (two's complement)\n\n" +
                        "# Shift operators:\n" +
                        "1 << 4        # 16   — shift left 4 bits (multiply by 2^4)\n" +
                        "256 >> 3      # 32   — shift right 3 bits (divide by 2^3)\n\n" +
                        "# Common patterns:\n" +
                        "x = 42\n" +
                        "x & 1            # 0 if even, 1 if odd\n" +
                        "x & 0xFF         # mask lower 8 bits\n" +
                        "(x >> 4) & 0xF   # extract bits 4-7\n" +
                        "x | (1 << 3)     # set bit 3\n" +
                        "x & ~(1 << 3)    # clear bit 3\n" +
                        "x ^ (1 << 3)     # toggle bit 3"
                    )
                }
            }

            item {
                SectionCard("int vs float, Conversions, Utilities") {
                    BodyText(
                        "Mixing int and float produces a float. Use int() to truncate toward zero, float() to convert to float. " +
                        "round() rounds to even (banker's rounding) by default."
                    )
                    CodeBlock(
                        "# int + float = float:\n" +
                        "3 + 1.0        # 4.0\n" +
                        "type(3 + 1.0)  # <class 'float'>\n\n" +
                        "# Conversions:\n" +
                        "int(3.9)       # 3   — truncates (does NOT round)\n" +
                        "int(-3.9)      # -3  — truncates toward zero\n" +
                        "int('42')      # 42  — parses a string\n" +
                        "float(10)      # 10.0\n" +
                        "float('3.14')  # 3.14\n\n" +
                        "# Rounding:\n" +
                        "round(3.5)     # 4  — rounds to even\n" +
                        "round(2.5)     # 2  — rounds to even (banker's rounding)\n" +
                        "round(3.14159, 2)  # 3.14 — round to 2 decimal places\n\n" +
                        "# Absolute value:\n" +
                        "abs(-42)       # 42\n" +
                        "abs(-3.14)     # 3.14\n\n" +
                        "# Min / max:\n" +
                        "min(3, 1, 4, 1, 5)   # 1\n" +
                        "max(3, 1, 4, 1, 5)   # 5\n" +
                        "sum([1, 2, 3, 4])     # 10"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
