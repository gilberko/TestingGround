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
fun KotlinFunctionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Kotlin — Functions",
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
                SectionCard(title = "Named Parameters") {
                    BodyText(
                        "Kotlin lets you call a function by naming its parameters. Named arguments " +
                        "can be supplied in any order. This makes calls with many parameters " +
                        "self-documenting and eliminates the need for overloads that reorder args.\n\n" +
                        "Positional and named arguments can be mixed, but positional arguments must " +
                        "come before named ones."
                    )
                    CodeBlock(
                        "fun drawRect(x: Int, y: Int, width: Int, height: Int, fill: Boolean) { }\n" +
                        "\n" +
                        "// Positional — hard to read\n" +
                        "drawRect(0, 0, 200, 100, true)\n" +
                        "\n" +
                        "// Named — self-documenting\n" +
                        "drawRect(x = 0, y = 0, width = 200, height = 100, fill = true)\n" +
                        "\n" +
                        "// Any order when all named\n" +
                        "drawRect(fill = true, height = 100, width = 200, x = 0, y = 0)\n" +
                        "\n" +
                        "// Mix: first positional, rest named\n" +
                        "drawRect(0, 0, width = 200, height = 100, fill = true)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Default Parameter Values") {
                    BodyText(
                        "Parameters can have default values. Callers can omit those parameters and " +
                        "the default is used. This replaces Java overloads in most cases.\n\n" +
                        "Java callers cannot use defaults automatically. Annotate the function with " +
                        "@JvmOverloads to have the compiler generate overloaded versions for Java."
                    )
                    CodeBlock(
                        "fun greet(name: String = \"World\", greeting: String = \"Hello\") {\n" +
                        "    println(\"\$greeting, \$name!\")\n" +
                        "}\n" +
                        "\n" +
                        "greet()                         // Hello, World!\n" +
                        "greet(\"Gil\")                    // Hello, Gil!\n" +
                        "greet(greeting = \"Hi\")          // Hi, World!\n" +
                        "greet(\"Gil\", \"Hey\")             // Hey, Gil!\n" +
                        "\n" +
                        "// @JvmOverloads for Java interop\n" +
                        "@JvmOverloads\n" +
                        "fun connect(host: String, port: Int = 8080, tls: Boolean = false) { }"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "vararg — Variadic Parameters") {
                    BodyText(
                        "A parameter marked vararg accepts zero or more arguments of that type. " +
                        "Inside the function the parameter is an Array<T> (or the primitive array " +
                        "for Int, Long, etc.). A function can have only one vararg parameter; it is " +
                        "usually the last one.\n\n" +
                        "To pass an existing array to a vararg function, prefix it with the spread " +
                        "operator *. This unpacks the array into individual arguments."
                    )
                    CodeBlock(
                        "fun sum(vararg nums: Int): Int = nums.sum()\n" +
                        "\n" +
                        "sum(1, 2, 3)           // 6\n" +
                        "sum()                   // 0  — zero args is valid\n" +
                        "\n" +
                        "val numbers = intArrayOf(4, 5, 6)\n" +
                        "sum(*numbers)           // spread operator — 15\n" +
                        "\n" +
                        "// Mix spread and extra args\n" +
                        "fun tag(vararg classes: String) = classes.joinToString(\" \")\n" +
                        "val base = arrayOf(\"btn\", \"primary\")\n" +
                        "tag(*base, \"large\")     // \"btn primary large\""
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Pass by Value or Reference?") {
                    BodyText(
                        "Kotlin (like Java) uses pass-by-value, but what is passed for object " +
                        "types is the reference (memory address), not a copy of the object. " +
                        "This means:\n\n" +
                        "• For primitive types (Int, Boolean, etc.) — a copy of the value is " +
                        "passed. Modifying it inside the function has no effect on the caller.\n" +
                        "• For objects — the reference is copied. You can mutate the object's " +
                        "contents through the reference, and the caller sees those mutations. " +
                        "However, you cannot make the caller's variable point to a different object."
                    )
                    CodeBlock(
                        "// Primitive — caller's variable unchanged\n" +
                        "fun doubleIt(n: Int) { /* n * 2 affects only local copy */ }\n" +
                        "var x = 5\n" +
                        "doubleIt(x)\n" +
                        "println(x)   // still 5\n" +
                        "\n" +
                        "// Object — can mutate contents\n" +
                        "data class Counter(var count: Int)\n" +
                        "fun increment(c: Counter) { c.count++ }   // mutates the object\n" +
                        "\n" +
                        "val counter = Counter(0)\n" +
                        "increment(counter)\n" +
                        "println(counter.count)   // 1\n" +
                        "\n" +
                        "// But reassignment inside the function has no effect on caller\n" +
                        "fun replace(c: Counter) { c = Counter(99) }   // compile error: val param"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Returning Multiple Values") {
                    BodyText(
                        "Kotlin has no built-in tuple syntax, but returning multiple values is " +
                        "idiomatic via Pair, Triple, or a custom data class. Prefer a data class " +
                        "when the return values have meaning — names make the code self-documenting.\n\n" +
                        "Destructuring declarations unpack the returned value into separate variables."
                    )
                    CodeBlock(
                        "// Pair and Triple (quick, but names are component1/2/3)\n" +
                        "fun minMax(list: List<Int>): Pair<Int, Int> =\n" +
                        "    list.min() to list.max()\n" +
                        "\n" +
                        "val (min, max) = minMax(listOf(3, 1, 4, 1, 5))\n" +
                        "println(\"min=\$min, max=\$max\")   // min=1, max=5\n" +
                        "\n" +
                        "// Prefer a data class for readability\n" +
                        "data class ParseResult(val value: Int, val remaining: String)\n" +
                        "\n" +
                        "fun parseInt(s: String): ParseResult {\n" +
                        "    val digits = s.takeWhile { it.isDigit() }\n" +
                        "    return ParseResult(digits.toInt(), s.drop(digits.length))\n" +
                        "}\n" +
                        "\n" +
                        "val (num, rest) = parseInt(\"42abc\")\n" +
                        "println(\"num=\$num, rest=\$rest\")  // num=42, rest=abc"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "No Checked Exceptions") {
                    BodyText(
                        "Kotlin has no checked exceptions. You never declare which exceptions a " +
                        "function may throw, and callers are not forced to catch them. This was a " +
                        "deliberate decision to avoid the verbosity and exception-swallowing patterns " +
                        "that checked exceptions produce in Java.\n\n" +
                        "For Java interoperability, annotate a Kotlin function with " +
                        "@Throws(ExceptionClass::class) so the Java compiler sees a checked throws " +
                        "declaration and enforces handling on the Java side."
                    )
                    CodeBlock(
                        "import java.io.IOException\n" +
                        "\n" +
                        "// Kotlin — no throws declaration needed\n" +
                        "fun readFile(path: String): String = java.io.File(path).readText()\n" +
                        "\n" +
                        "// Java interop — forces Java callers to handle IOException\n" +
                        "@Throws(IOException::class)\n" +
                        "fun readFileSafe(path: String): String = java.io.File(path).readText()\n" +
                        "\n" +
                        "// Catching exceptions in Kotlin is try/catch like Java,\n" +
                        "// but try is an expression that can return a value\n" +
                        "val text: String? = try { readFile(\"data.txt\") } catch (e: IOException) { null }"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Local Variables — Stack vs Heap") {
                    BodyText(
                        "On the JVM, each method call creates a stack frame holding the method's " +
                        "local variables and parameters. When the function returns, the frame is " +
                        "discarded and those locals are gone.\n\n" +
                        "• Primitive types (Int, Boolean, Double, etc.) are stored directly in the " +
                        "stack frame — no heap allocation.\n" +
                        "• Object types are allocated on the heap; the stack frame holds the " +
                        "reference (a pointer). When the frame is discarded, the reference goes away " +
                        "and the heap object becomes eligible for garbage collection.\n\n" +
                        "Exception: variables captured by a lambda or coroutine are \"lifted\" to a " +
                        "heap-allocated wrapper object so they outlive the original stack frame."
                    )
                    CodeBlock(
                        "fun example() {\n" +
                        "    val n: Int = 42          // stored directly on the stack frame\n" +
                        "    val s: String = \"hello\"  // reference on stack, object on heap\n" +
                        "    // when example() returns, n and s go away;\n" +
                        "    // \"hello\" may be GC'd if no other reference exists\n" +
                        "}\n" +
                        "\n" +
                        "// Captured variable — lifted to heap so the lambda can access it\n" +
                        "fun makeCounter(): () -> Int {\n" +
                        "    var count = 0             // lifted to Ref<Int> on the heap\n" +
                        "    return { count++ }        // lambda holds a reference to Ref<Int>\n" +
                        "}\n" +
                        "val counter = makeCounter()\n" +
                        "println(counter())   // 0\n" +
                        "println(counter())   // 1   — survives after makeCounter() returned"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Extension Functions") {
                    BodyText(
                        "An extension function adds a new function to an existing type without " +
                        "modifying the class or using inheritance. Inside the function, this refers " +
                        "to the receiver object.\n\n" +
                        "Extension functions are resolved statically at compile time based on the " +
                        "declared type of the variable, not the runtime type. They cannot access " +
                        "private or protected members."
                    )
                    CodeBlock(
                        "fun String.isPalindrome(): Boolean = this == this.reversed()\n" +
                        "\n" +
                        "println(\"racecar\".isPalindrome())   // true\n" +
                        "println(\"hello\".isPalindrome())     // false\n" +
                        "\n" +
                        "// Extension on a nullable type\n" +
                        "fun String?.orEmpty(): String = this ?: \"\"\n" +
                        "\n" +
                        "// Extensions on your own classes — common for utility methods\n" +
                        "fun List<Int>.median(): Double {\n" +
                        "    val sorted = this.sorted()\n" +
                        "    return if (sorted.size % 2 == 0)\n" +
                        "        (sorted[sorted.size / 2 - 1] + sorted[sorted.size / 2]) / 2.0\n" +
                        "    else\n" +
                        "        sorted[sorted.size / 2].toDouble()\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Infix Functions") {
                    BodyText(
                        "A function marked infix can be called without a dot or parentheses. " +
                        "Requirements: it must be a member function or extension function, and it " +
                        "must have exactly one parameter. Infix notation is used throughout the " +
                        "standard library (to, until, downTo, step, and, or) and is popular in " +
                        "DSLs and test assertion frameworks."
                    )
                    CodeBlock(
                        "infix fun Int.multiplyBy(factor: Int) = this * factor\n" +
                        "\n" +
                        "println(3 multiplyBy 4)   // 12  — infix call\n" +
                        "println(3.multiplyBy(4))  // also valid — regular call\n" +
                        "\n" +
                        "// Standard library examples\n" +
                        "val pair = \"key\" to \"value\"   // Pair<String, String>\n" +
                        "val range = 1 until 10         // IntRange 1..9\n" +
                        "val step  = 0 until 20 step 2  // 0, 2, 4 ... 18\n" +
                        "\n" +
                        "// Test DSL style\n" +
                        "infix fun <T> T.shouldEqual(expected: T) {\n" +
                        "    if (this != expected) throw AssertionError(\"Expected \$expected but got \$this\")\n" +
                        "}\n" +
                        "42 shouldEqual 42"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Companion Object — Static Members") {
                    BodyText(
                        "Kotlin has no static keyword. Class-level members live in a companion " +
                        "object. There is at most one companion per class. A companion object is " +
                        "initialized when the enclosing class is first loaded by the JVM classloader " +
                        "— the same semantics as Java static initializers.\n\n" +
                        "Annotate a companion member with @JvmStatic to make it a true JVM static " +
                        "method or field so Java callers can access it without the Companion qualifier."
                    )
                    CodeBlock(
                        "class ApiClient private constructor(val baseUrl: String) {\n" +
                        "\n" +
                        "    companion object {\n" +
                        "        const val DEFAULT_TIMEOUT = 30_000L   // compile-time constant\n" +
                        "        private var instance: ApiClient? = null\n" +
                        "\n" +
                        "        @JvmStatic\n" +
                        "        fun getInstance(url: String): ApiClient {\n" +
                        "            return instance ?: ApiClient(url).also { instance = it }\n" +
                        "        }\n" +
                        "    }\n" +
                        "}\n" +
                        "\n" +
                        "// Kotlin call — companion is accessed via the class name\n" +
                        "val client = ApiClient.getInstance(\"https://api.example.com\")\n" +
                        "println(ApiClient.DEFAULT_TIMEOUT)   // 30000\n" +
                        "\n" +
                        "// Java call — @JvmStatic makes it ApiClient.getInstance(...) not\n" +
                        "// ApiClient.Companion.getInstance(...)"
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
