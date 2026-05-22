package com.example.developmentapp.screens.kotlin

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
fun KotlinBasicSyntaxScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Kotlin — Basic Syntax",
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
                SectionCard(title = "Comments") {
                    BodyText(
                        "Kotlin supports three comment styles:\n\n" +
                        "// single-line — from // to end of line. Most common for short notes.\n\n" +
                        "/* block */ — can span multiple lines. Used for temporarily disabling code " +
                        "or longer inline explanations.\n\n" +
                        "/** KDoc */ — documentation comment. Processed by the Dokka tool to generate " +
                        "HTML documentation. Supports @param, @return, @throws tags similar to Javadoc."
                    )
                    CodeBlock(
                        "// This is a single-line comment\n\n" +
                        "/* This is a\n" +
                        "   multi-line comment */\n\n" +
                        "/**\n" +
                        " * Adds two integers.\n" +
                        " * @param a the first operand\n" +
                        " * @param b the second operand\n" +
                        " * @return the sum\n" +
                        " */\n" +
                        "fun add(a: Int, b: Int): Int = a + b"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Arithmetic and Operators") {
                    BodyText(
                        "Arithmetic: + - * / % (modulo). Integer division truncates toward zero.\n\n" +
                        "Comparison: == (structural equality), != , < , > , <= , >= . In Kotlin == " +
                        "calls equals() — it is NOT reference equality. Use === for reference equality.\n\n" +
                        "Logical: && (and), || (or), ! (not). Short-circuit evaluated.\n\n" +
                        "String concatenation: + joins strings. But prefer string templates (\"\$var\") " +
                        "for readability."
                    )
                    CodeBlock(
                        "val a = 10\n" +
                        "val b = 3\n" +
                        "println(a + b)   // 13\n" +
                        "println(a - b)   // 7\n" +
                        "println(a * b)   // 30\n" +
                        "println(a / b)   // 3   (integer division)\n" +
                        "println(a % b)   // 1   (remainder)\n\n" +
                        "// Comparison (== is structural equality):\n" +
                        "val s1 = \"hello\"\n" +
                        "val s2 = \"hello\"\n" +
                        "println(s1 == s2)   // true  (content equal)\n" +
                        "println(s1 === s2)  // may be true or false (reference equality)\n\n" +
                        "// Logical:\n" +
                        "val x = true\n" +
                        "val y = false\n" +
                        "println(x && y)   // false\n" +
                        "println(x || y)   // true\n" +
                        "println(!x)       // false\n\n" +
                        "// Compound assignment operators:\n" +
                        "var n = 5\n" +
                        "n += 3    // n = 8\n" +
                        "n -= 1    // n = 7\n" +
                        "n *= 2    // n = 14"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "if as an Expression") {
                    BodyText(
                        "In Kotlin, if is an expression — it produces a value. You can assign the " +
                        "result of an if directly to a variable. When used as an expression, the else " +
                        "branch is mandatory (otherwise the value would be undefined).\n\n" +
                        "This replaces the ternary operator (condition ? a : b) that exists in Java and " +
                        "C++ — Kotlin has no ternary operator because if already serves that role."
                    )
                    CodeBlock(
                        "val a = 10\n" +
                        "val b = 20\n\n" +
                        "// if as a statement (traditional style):\n" +
                        "if (a > b) println(\"a wins\") else println(\"b wins\")\n\n" +
                        "// if as an expression (assigns the result):\n" +
                        "val max = if (a > b) a else b\n" +
                        "println(max)  // 20\n\n" +
                        "// Multi-line branches — last expression in the block is the value:\n" +
                        "val description = if (a > 0) {\n" +
                        "    println(\"a is positive\")\n" +
                        "    \"positive\"\n" +
                        "} else {\n" +
                        "    \"non-positive\"\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "when Expression") {
                    BodyText(
                        "when replaces Java's switch statement — but is much more powerful and is also " +
                        "an expression that returns a value.\n\n" +
                        "• No fall-through between branches (unlike Java switch)\n" +
                        "• Branches can match: a value, a range, a type, or any Boolean expression\n" +
                        "• When used as an expression, else is required unless all cases are covered " +
                        "(e.g. when matching a sealed class or enum exhaustively)"
                    )
                    CodeBlock(
                        "// when as a statement:\n" +
                        "val x = 3\n" +
                        "when (x) {\n" +
                        "    1    -> println(\"one\")\n" +
                        "    2, 3 -> println(\"two or three\")  // multiple values\n" +
                        "    in 4..10 -> println(\"four to ten\")  // range\n" +
                        "    else -> println(\"other\")\n" +
                        "}\n\n" +
                        "// when as an expression:\n" +
                        "val label = when (x) {\n" +
                        "    1    -> \"one\"\n" +
                        "    2, 3 -> \"two or three\"\n" +
                        "    else -> \"other\"\n" +
                        "}\n\n" +
                        "// when without an argument — acts as a chain of if-else:\n" +
                        "val score = 75\n" +
                        "val grade = when {\n" +
                        "    score >= 90 -> \"A\"\n" +
                        "    score >= 80 -> \"B\"\n" +
                        "    score >= 70 -> \"C\"\n" +
                        "    else        -> \"F\"\n" +
                        "}\n\n" +
                        "// when with type check (is):\n" +
                        "fun describe(obj: Any) = when (obj) {\n" +
                        "    is String -> \"String of length \${obj.length}\"  // smart cast\n" +
                        "    is Int    -> \"Integer: \$obj\"\n" +
                        "    else      -> \"Unknown\"\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Ranges and Loops") {
                    BodyText(
                        "Ranges — Kotlin has a built-in range syntax that makes loops and conditions cleaner:\n" +
                        "• 1..10 — inclusive range from 1 to 10\n" +
                        "• 1 until 10 — exclusive: 1 to 9\n" +
                        "• 10 downTo 1 — counts down\n" +
                        "• step N — changes the increment\n\n" +
                        "for loop — iterates over any Iterable (ranges, collections, arrays). There is " +
                        "no traditional C-style for(;;) loop in Kotlin.\n\n" +
                        "while and do-while — same semantics as Java/C++."
                    )
                    CodeBlock(
                        "// Range in for loop:\n" +
                        "for (i in 1..5)       print(\"\$i \")   // 1 2 3 4 5\n" +
                        "for (i in 1 until 5)  print(\"\$i \")   // 1 2 3 4\n" +
                        "for (i in 5 downTo 1) print(\"\$i \")   // 5 4 3 2 1\n" +
                        "for (i in 0..10 step 2) print(\"\$i \") // 0 2 4 6 8 10\n\n" +
                        "// Iterating a collection:\n" +
                        "val names = listOf(\"Alice\", \"Bob\", \"Carol\")\n" +
                        "for (name in names) println(name)\n\n" +
                        "// with index:\n" +
                        "for ((index, name) in names.withIndex()) {\n" +
                        "    println(\"\$index: \$name\")\n" +
                        "}\n\n" +
                        "// while:\n" +
                        "var n = 0\n" +
                        "while (n < 5) { print(\"\$n \"); n++ }\n\n" +
                        "// do-while:\n" +
                        "do { print(\"\$n \"); n-- } while (n > 0)\n\n" +
                        "// Range in if / when:\n" +
                        "val score = 75\n" +
                        "if (score in 70..79) println(\"Grade C\")"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Unit — Not void") {
                    BodyText(
                        "Kotlin has no void keyword. Functions that do not return a meaningful value " +
                        "return Unit — an actual type and singleton object with exactly one value " +
                        "(also called Unit). The return type Unit can be omitted from the declaration; " +
                        "the compiler adds it implicitly.\n\n" +
                        "Unit is equivalent to Java's void but it is a real value, which means functions " +
                        "returning Unit can be used in generic contexts (e.g. Callable<Unit>) without " +
                        "special handling.\n\n" +
                        "Nothing is a separate type for functions that never return — either because " +
                        "they always throw an exception or loop forever. It is a subtype of every type, " +
                        "so the compiler knows code after a Nothing-returning call is unreachable."
                    )
                    CodeBlock(
                        "// Unit return — explicit and implicit are identical:\n" +
                        "fun greet(): Unit { println(\"Hello\") }\n" +
                        "fun greet2()       { println(\"Hello\") }  // same thing\n\n" +
                        "// Unit is a real value:\n" +
                        "val result: Unit = greet()  // OK — Unit is assignable\n\n" +
                        "// Nothing — function never returns:\n" +
                        "fun fail(msg: String): Nothing {\n" +
                        "    throw IllegalStateException(msg)   // always throws\n" +
                        "}\n\n" +
                        "fun infiniteLoop(): Nothing {\n" +
                        "    while (true) { }   // never returns\n" +
                        "}\n\n" +
                        "// Compiler knows code after fail() is unreachable:\n" +
                        "val name: String = getName() ?: fail(\"Name is required\")\n" +
                        "println(name.length)   // compiler knows name is non-null here"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Semicolons") {
                    BodyText(
                        "Semicolons are optional in Kotlin. A newline ends a statement. You almost " +
                        "never need semicolons in Kotlin code — the style guide recommends omitting them.\n\n" +
                        "The only place semicolons are still used is to separate multiple statements on " +
                        "a single line, though that style is discouraged."
                    )
                    CodeBlock(
                        "// No semicolons needed:\n" +
                        "val a = 1\n" +
                        "val b = 2\n" +
                        "println(a + b)\n\n" +
                        "// Semicolons are allowed but not idiomatic:\n" +
                        "val x = 10; val y = 20; println(x + y)  // valid but bad style\n\n" +
                        "// Compare to Java where semicolons are mandatory:\n" +
                        "// int a = 1;   ← required in Java\n" +
                        "// System.out.println(a);   ← required in Java"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
