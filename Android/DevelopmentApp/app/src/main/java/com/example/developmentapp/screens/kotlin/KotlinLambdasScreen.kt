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
fun KotlinLambdasScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Kotlin — Lambdas",
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
                SectionCard(title = "Lambda Syntax") {
                    BodyText(
                        "A lambda is an anonymous function literal — a block of code you can store " +
                        "in a variable, pass to a function, or return from a function.\n\n" +
                        "Syntax: { parameters -> body }. The last expression in the body is the " +
                        "return value — no return keyword needed inside a lambda.\n\n" +
                        "The full type can always be written as (ParamType, ...) -> ReturnType."
                    )
                    CodeBlock(
                        "// Lambda stored in a variable:\n" +
                        "val add: (Int, Int) -> Int = { a, b -> a + b }\n" +
                        "println(add(3, 4))    // 7\n\n" +
                        "// Type inferred from the lambda body:\n" +
                        "val square = { x: Int -> x * x }   // type: (Int) -> Int\n" +
                        "println(square(5))    // 25\n\n" +
                        "// Lambda with no parameters:\n" +
                        "val greet: () -> String = { \"Hello!\" }\n" +
                        "println(greet())      // Hello!\n\n" +
                        "// Multi-statement lambda — last expression is the result:\n" +
                        "val process = { x: Int ->\n" +
                        "    val doubled = x * 2\n" +
                        "    doubled + 1         // returned value\n" +
                        "}\n" +
                        "println(process(5))   // 11"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Function Types") {
                    BodyText(
                        "In Kotlin, functions are first-class citizens. A function type describes the " +
                        "signature of a function: its parameter types and return type.\n\n" +
                        "(Int, Int) -> Int    — takes two Ints, returns Int\n" +
                        "() -> Unit           — takes nothing, returns nothing\n" +
                        "(String) -> Boolean  — takes a String, returns Boolean\n\n" +
                        "A nullable function type — ((Int) -> Int)? — means the variable may hold " +
                        "null instead of a function. Invoke it with ?. to guard against null."
                    )
                    CodeBlock(
                        "// Declare a variable with a function type:\n" +
                        "var transform: (Int) -> Int = { it * 2 }\n" +
                        "println(transform(5))   // 10\n\n" +
                        "transform = { it + 100 }   // reassign a different function\n" +
                        "println(transform(5))   // 105\n\n" +
                        "// Function type as a parameter:\n" +
                        "fun applyTwice(x: Int, f: (Int) -> Int): Int = f(f(x))\n" +
                        "println(applyTwice(3, { it * 2 }))   // 12  (3 → 6 → 12)\n\n" +
                        "// Nullable function type:\n" +
                        "var action: (() -> Unit)? = null\n" +
                        "action?.invoke()   // does nothing — action is null\n" +
                        "action = { println(\"Running!\") }\n" +
                        "action?.invoke()   // prints: Running!"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "it — Implicit Single Parameter") {
                    BodyText(
                        "When a lambda has exactly one parameter, you can omit the parameter " +
                        "declaration and the -> arrow entirely. The single parameter is automatically " +
                        "available as 'it'. This makes short lambdas much more concise.\n\n" +
                        "Use 'it' for short, simple lambdas. For longer or nested lambdas, name the " +
                        "parameter explicitly to improve readability."
                    )
                    CodeBlock(
                        "val numbers = listOf(1, 2, 3, 4, 5)\n\n" +
                        "// Explicit parameter:\n" +
                        "val doubled = numbers.map { n -> n * 2 }\n\n" +
                        "// Using 'it' — identical result, shorter:\n" +
                        "val doubled2 = numbers.map { it * 2 }\n\n" +
                        "// Chained calls with 'it':\n" +
                        "val result = numbers\n" +
                        "    .filter { it % 2 == 0 }   // [2, 4]\n" +
                        "    .map    { it * it }        // [4, 16]\n" +
                        "    .sum()                     // 20\n\n" +
                        "// Named parameter for clarity in nested lambdas:\n" +
                        "val matrix = listOf(listOf(1, 2), listOf(3, 4))\n" +
                        "matrix.forEach { row ->\n" +
                        "    row.forEach { cell -> print(\"\$cell \") }  // 'it' would be ambiguous\n" +
                        "    println()\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Trailing Lambda Convention") {
                    BodyText(
                        "If the last (or only) parameter of a function is a function type, the lambda " +
                        "can be moved outside the parentheses. If the lambda is the only argument, the " +
                        "parentheses can be omitted entirely.\n\n" +
                        "This convention is heavily used in Kotlin APIs — it makes DSL-style code " +
                        "readable and is why Jetpack Compose's UI blocks look like natural language."
                    )
                    CodeBlock(
                        "fun repeat(times: Int, action: () -> Unit) {\n" +
                        "    for (i in 0 until times) action()\n" +
                        "}\n\n" +
                        "// Traditional call:\n" +
                        "repeat(3, { println(\"Hello\") })\n\n" +
                        "// Trailing lambda — moved outside parens:\n" +
                        "repeat(3) { println(\"Hello\") }\n\n" +
                        "// Standard library examples (all use trailing lambda):\n" +
                        "listOf(1, 2, 3).forEach { println(it) }\n" +
                        "listOf(1, 2, 3).filter { it > 1 }\n" +
                        "listOf(1, 2, 3).map    { it * 2 }\n\n" +
                        "// Jetpack Compose — trailing lambda creates UI tree:\n" +
                        "Column {\n" +
                        "    Text(\"Title\")\n" +
                        "    Button(onClick = { /* handle click */ }) {\n" +
                        "        Text(\"Click me\")\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Higher-Order Functions") {
                    BodyText(
                        "A higher-order function is a function that takes another function as a " +
                        "parameter, or returns a function. Kotlin's standard library is built on " +
                        "higher-order functions — map, filter, forEach, reduce, fold, and many others " +
                        "all accept lambdas.\n\n" +
                        "Functions can also return functions — the return type is a function type."
                    )
                    CodeBlock(
                        "// Higher-order function — takes a transform function:\n" +
                        "fun <T, R> transform(list: List<T>, f: (T) -> R): List<R> =\n" +
                        "    list.map(f)\n\n" +
                        "val names = listOf(\"alice\", \"bob\")\n" +
                        "val upper = transform(names) { it.uppercase() }\n" +
                        "println(upper)   // [ALICE, BOB]\n\n" +
                        "// Standard library higher-order functions:\n" +
                        "val nums = listOf(1, 2, 3, 4, 5, 6)\n" +
                        "val evens  = nums.filter { it % 2 == 0 }    // [2, 4, 6]\n" +
                        "val squares = nums.map { it * it }           // [1, 4, 9, 16, 25, 36]\n" +
                        "val sum     = nums.reduce { acc, x -> acc + x }  // 21\n" +
                        "val total   = nums.fold(100) { acc, x -> acc + x } // 121\n\n" +
                        "// Returning a function:\n" +
                        "fun multiplier(factor: Int): (Int) -> Int = { it * factor }\n" +
                        "val triple = multiplier(3)\n" +
                        "println(triple(7))   // 21"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Closures") {
                    BodyText(
                        "A closure is a lambda that captures variables from the surrounding scope. " +
                        "The lambda 'closes over' those variables — they remain accessible inside the " +
                        "lambda even after the outer function returns.\n\n" +
                        "A key difference from Java: Kotlin lambdas can capture and MODIFY var " +
                        "variables from the enclosing scope. In Java, captured variables must be " +
                        "effectively final."
                    )
                    CodeBlock(
                        "// Closure captures a val:\n" +
                        "fun makeGreeter(greeting: String): () -> String {\n" +
                        "    return { \"\$greeting, World!\" }   // captures 'greeting'\n" +
                        "}\n" +
                        "val hello = makeGreeter(\"Hello\")\n" +
                        "val hola  = makeGreeter(\"Hola\")\n" +
                        "println(hello())   // Hello, World!\n" +
                        "println(hola())    // Hola, World!\n\n" +
                        "// Kotlin: closure can capture AND modify a var:\n" +
                        "var counter = 0\n" +
                        "val increment = { counter++ }\n" +
                        "increment()\n" +
                        "increment()\n" +
                        "println(counter)   // 2 — the captured var was modified\n\n" +
                        "// Java contrast (won't compile in Java):\n" +
                        "// int count = 0;\n" +
                        "// Runnable r = () -> count++; // ERROR: must be effectively final"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Function References") {
                    BodyText(
                        "Instead of writing a lambda that just calls an existing function, you can " +
                        "reference the function directly using the :: operator. The result is a " +
                        "function reference that can be used anywhere a matching function type is expected.\n\n" +
                        "::functionName         — top-level function reference\n" +
                        "obj::methodName        — bound method reference (captures the receiver)\n" +
                        "ClassName::methodName  — unbound reference (receiver is the first parameter)\n" +
                        "ClassName::new         — constructor reference (not needed in Kotlin usually)"
                    )
                    CodeBlock(
                        "fun isEven(n: Int): Boolean = n % 2 == 0\n\n" +
                        "// Lambda calling isEven:\n" +
                        "val nums = listOf(1, 2, 3, 4, 5)\n" +
                        "nums.filter { isEven(it) }   // verbose\n\n" +
                        "// Function reference — cleaner:\n" +
                        "nums.filter(::isEven)\n\n" +
                        "// Bound method reference:\n" +
                        "val words = listOf(\"hello\", \"world\", \"kotlin\")\n" +
                        "words.map(String::uppercase)   // unbound — String is receiver\n\n" +
                        "val prefix = \"ID:\"\n" +
                        "words.map(prefix::plus)   // bound — prefix is captured\n\n" +
                        "// Constructor reference:\n" +
                        "val strings = listOf(1, 2, 3)\n" +
                        "strings.map(::String)      // [\"1\", \"2\", \"3\"]\n\n" +
                        "// Passing to a higher-order function:\n" +
                        "fun logError(msg: String) = System.err.println(\"ERROR: \$msg\")\n" +
                        "listOf(\"disk full\", \"timeout\").forEach(::logError)"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
