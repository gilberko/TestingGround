package com.example.developmentapp.screens.java

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
fun JavaBasicTypesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Java — Basic Types and Operators",
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
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Primitive Types") {
                    BodyText(
                        "Java has eight primitive types. Unlike objects, primitives are stored directly " +
                        "on the stack (or inline in an object) — no heap allocation, no garbage " +
                        "collection overhead."
                    )
                    BodyText(
                        "byte   — 8-bit signed integer.  Range: -128 to 127.\n" +
                        "short  — 16-bit signed integer. Range: -32,768 to 32,767.\n" +
                        "int    — 32-bit signed integer. Range: ~-2.1 billion to ~2.1 billion.\n" +
                        "long   — 64-bit signed integer. Range: ~-9.2 x 10^18 to ~9.2 x 10^18.\n" +
                        "float  — 32-bit IEEE 754 floating point. Suffix: f or F.\n" +
                        "double — 64-bit IEEE 754 floating point. Default for decimal literals.\n" +
                        "char   — 16-bit Unicode (UTF-16 code unit). Range: 0 to 65,535.\n" +
                        "boolean — true or false only."
                    )
                    BodyText(
                        "char is Unicode, not ASCII. Java uses UTF-16 internally, so a char can " +
                        "represent any character in the Unicode Basic Multilingual Plane (code points " +
                        "U+0000 to U+FFFF). Character literals use single quotes. A char is also a " +
                        "numeric value — char c = 'A' is exactly the same as char c = 65."
                    )
                    CodeBlock(
                        "byte   b = 100;\n" +
                        "short  s = 30000;\n" +
                        "int    i = 2_000_000;     // underscores allowed for readability\n" +
                        "long   l = 9_000_000_000L; // L suffix required for long literals\n" +
                        "float  f = 3.14f;          // f suffix required\n" +
                        "double d = 3.14159;         // no suffix needed\n" +
                        "char   c = 'A';             // Unicode: same as char c = 65\n" +
                        "char   u = '\\u0041';        // also 'A' via Unicode escape\n" +
                        "boolean flag = true;"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Arithmetic Operators") {
                    BodyText(
                        "The standard arithmetic operators are +, -, *, / (division), and % (modulo — " +
                        "the remainder after division). Operator precedence: *, /, % are evaluated " +
                        "before + and -. Use parentheses to override."
                    )
                    CodeBlock(
                        "int a = 10, b = 3;\n" +
                        "System.out.println(a + b);   // 13\n" +
                        "System.out.println(a - b);   // 7\n" +
                        "System.out.println(a * b);   // 30\n" +
                        "System.out.println(a / b);   // 3  (integer division — truncates)\n" +
                        "System.out.println(a % b);   // 1  (remainder: 10 = 3*3 + 1)\n\n" +
                        "// Precedence: * before +\n" +
                        "System.out.println(2 + 3 * 4);    // 14, not 20\n" +
                        "System.out.println((2 + 3) * 4);  // 20\n\n" +
                        "// Floating-point division\n" +
                        "System.out.println(10.0 / 3);     // 3.3333..."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Compound Assignment and Increment / Decrement") {
                    BodyText(
                        "Compound assignment operators combine an operation with assignment: +=, -=, " +
                        "*=, /=, %=. They are shorthand — x += 5 means x = x + 5."
                    )
                    BodyText(
                        "++ increments a variable by 1; -- decrements by 1. Both exist in prefix " +
                        "form (++j) and postfix form (j++). As a standalone statement, they behave " +
                        "identically. The difference shows up when used inside a larger expression:"
                    )
                    BodyText(
                        "j++ (postfix): the current value of j is used in the expression first, " +
                        "then j is incremented afterward."
                    )
                    BodyText(
                        "++j (prefix): j is incremented first, then the new value is used in the " +
                        "expression."
                    )
                    CodeBlock(
                        "int j = 5;\n\n" +
                        "int a = j++;  // a = 5, j = 6  (use then increment)\n" +
                        "int b = ++j;  // j = 7, b = 7  (increment then use)\n\n" +
                        "// Standalone — no difference:\n" +
                        "j++;   // j = 8\n" +
                        "++j;   // j = 9\n\n" +
                        "// Compound assignment\n" +
                        "j += 10;  // j = 19\n" +
                        "j -= 4;   // j = 15\n" +
                        "j *= 2;   // j = 30\n" +
                        "j /= 3;   // j = 10\n" +
                        "j %= 3;   // j = 1"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Comparison and Logical Operators") {
                    BodyText(
                        "Comparison operators return a boolean: ==, !=, <, <=, >, >=. Important: " +
                        "== on object references checks whether both variables point to the same " +
                        "object in memory, not whether the objects are equal. For String equality, " +
                        "use .equals()."
                    )
                    BodyText(
                        "Logical operators combine boolean expressions: && (AND), || (OR), ! (NOT). " +
                        "Both && and || short-circuit: if the left side of && is false, the right side " +
                        "is never evaluated (the result is already false). If the left side of || is " +
                        "true, the right side is skipped."
                    )
                    CodeBlock(
                        "int x = 5, y = 10;\n" +
                        "System.out.println(x == 5);          // true\n" +
                        "System.out.println(x != y);          // true\n" +
                        "System.out.println(x < y);           // true\n" +
                        "System.out.println(x >= 5 && y <= 10); // true\n" +
                        "System.out.println(x > 10 || y > 5);  // true\n" +
                        "System.out.println(!(x == y));         // true\n\n" +
                        "// String comparison\n" +
                        "String s1 = \"hello\";\n" +
                        "String s2 = \"hello\";\n" +
                        "System.out.println(s1 == s2);       // may be true (interning) but unreliable\n" +
                        "System.out.println(s1.equals(s2));  // always correct: true"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Can I Use Numbers as Booleans?") {
                    BodyText(
                        "No — Java is strict about types. Unlike C and C++, integers are NOT " +
                        "implicitly convertible to boolean. Writing if (1) or if (x) where x is an " +
                        "int is a compile error. Java requires an explicit boolean expression."
                    )
                    CodeBlock(
                        "int x = 1;\n\n" +
                        "// COMPILE ERROR in Java:\n" +
                        "// if (x) { ... }\n" +
                        "// if (1) { ... }\n\n" +
                        "// Correct — explicit boolean comparison:\n" +
                        "if (x != 0) { System.out.println(\"nonzero\"); }\n" +
                        "if (x == 1) { System.out.println(\"is one\"); }"
                    )
                    BodyText(
                        "This strictness catches a common bug from C where = (assignment) inside if " +
                        "silently compiles. In Java, if (x = 5) is also a compile error because " +
                        "assignment of int doesn't produce a boolean."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Ternary Operator") {
                    BodyText(
                        "The ternary operator is a compact if-else expression: " +
                        "condition ? valueIfTrue : valueIfFalse. It evaluates to one of the two " +
                        "values depending on the condition, and can be used anywhere an expression " +
                        "is expected."
                    )
                    CodeBlock(
                        "int a = 7, b = 3;\n" +
                        "int max = (a > b) ? a : b;       // max = 7\n\n" +
                        "String label = (max > 5) ? \"big\" : \"small\";  // label = \"big\"\n\n" +
                        "// Equivalent if-else:\n" +
                        "int max2;\n" +
                        "if (a > b) { max2 = a; } else { max2 = b; }\n\n" +
                        "// Nested (readable limit — avoid going deeper):\n" +
                        "int sign = (a > 0) ? 1 : (a < 0) ? -1 : 0;"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "How Negative Numbers Are Represented") {
                    BodyText(
                        "Java uses two's complement representation for all integer types (byte, short, " +
                        "int, long). In two's complement, to negate a number: flip all its bits, then " +
                        "add 1."
                    )
                    BodyText(
                        "Example with byte (8 bits):\n" +
                        "+5  =  00000101\n" +
                        "flip = 11111010\n" +
                        "+1  = 11111011  <-- this is -5\n\n" +
                        "Special cases:\n" +
                        "11111111 = -1\n" +
                        "10000000 = -128  (most negative byte value)\n" +
                        "01111111 = +127  (most positive byte value)"
                    )
                    BodyText(
                        "This is why byte ranges from -128 to 127, not -127 to 127. The bit pattern " +
                        "10000000 represents -128 — there is no +128 in a signed 8-bit type. For int " +
                        "(32 bits), -1 is 0xFFFFFFFF and Integer.MIN_VALUE (-2,147,483,648) is 0x80000000."
                    )
                    CodeBlock(
                        "System.out.println(Integer.MAX_VALUE);  // 2147483647  (0x7FFFFFFF)\n" +
                        "System.out.println(Integer.MIN_VALUE);  // -2147483648 (0x80000000)\n" +
                        "System.out.println(Byte.MIN_VALUE);     // -128\n" +
                        "System.out.println(Byte.MAX_VALUE);     // 127\n\n" +
                        "// Integer overflow wraps around:\n" +
                        "int max = Integer.MAX_VALUE;\n" +
                        "System.out.println(max + 1);  // -2147483648 (wraps to MIN_VALUE)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Bitwise Operators") {
                    BodyText(
                        "Bitwise operators work on individual bits of integer values. They operate on " +
                        "int and long (smaller types are promoted to int first)."
                    )
                    BodyText(
                        "&  — bitwise AND: bit is 1 only if both bits are 1.\n" +
                        "|  — bitwise OR:  bit is 1 if either bit is 1.\n" +
                        "^  — bitwise XOR: bit is 1 if the bits differ.\n" +
                        "~  — bitwise NOT: flips every bit (unary).\n" +
                        "<< — left shift:  shifts bits left, fills 0s from the right.\n" +
                        ">> — arithmetic right shift: shifts bits right, fills with the sign bit.\n" +
                        ">>> — logical right shift: shifts bits right, always fills 0s from the left."
                    )
                    CodeBlock(
                        "int a = 0b1010;  // 10\n" +
                        "int b = 0b1100;  // 12\n\n" +
                        "System.out.println(a & b);   // 0b1000 = 8   (AND)\n" +
                        "System.out.println(a | b);   // 0b1110 = 14  (OR)\n" +
                        "System.out.println(a ^ b);   // 0b0110 = 6   (XOR)\n" +
                        "System.out.println(~a);      // -11  (flips all 32 bits of 10)\n\n" +
                        "System.out.println(1 << 3);  // 8    (1 shifted left 3 = 2^3)\n" +
                        "System.out.println(8 >> 1);  // 4    (8 shifted right 1 = 4)\n\n" +
                        "// Right-shifting a negative number:\n" +
                        "System.out.println(-8 >> 1);   // -4  (>> fills with 1s — sign preserved)\n" +
                        "System.out.println(-8 >>> 1);  // 2147483644  (>>> fills with 0s)"
                    )
                    BodyText(
                        "The distinction between >> and >>> matters for negative numbers. With >> " +
                        "(arithmetic shift), the sign bit (1 for negative numbers) is copied into the " +
                        "vacated bits on the left, preserving the sign. So -8 >> 1 = -4. With >>> " +
                        "(logical shift), 0s fill from the left regardless of the sign bit, turning " +
                        "a negative number into a large positive one. Java is unique in having both " +
                        "operators explicitly — C and C++ only have >> and its behaviour on signed " +
                        "negatives is implementation-defined."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
