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
fun KotlinVariableTypesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Kotlin — Variable Types",
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
                SectionCard(title = "var vs val") {
                    BodyText(
                        "Kotlin has two variable declaration keywords:\n\n" +
                        "val — read-only (like Java final). Once assigned, it cannot be reassigned. " +
                        "This is Kotlin's preferred default — use val whenever the value does not change.\n\n" +
                        "var — mutable. Can be reassigned after declaration.\n\n" +
                        "val does not mean deeply immutable — if the value is an object, its internal " +
                        "state can still change. val only prevents reassigning the variable itself to " +
                        "a different object, exactly like Java's final."
                    )
                    CodeBlock(
                        "val name = \"Alice\"       // read-only\n" +
                        "name = \"Bob\"             // COMPILE ERROR — val cannot be reassigned\n\n" +
                        "var age = 30             // mutable\n" +
                        "age = 31                 // OK\n\n" +
                        "val list = mutableListOf(1, 2, 3)\n" +
                        "list.add(4)              // OK — the list itself is mutable\n" +
                        "list = mutableListOf()   // COMPILE ERROR — the reference is val"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Data Types") {
                    BodyText(
                        "Kotlin's built-in types — all are proper objects, no primitive types in the " +
                        "language. The JVM compiler optimises them to Java primitives (int, long, etc.) " +
                        "automatically wherever possible:\n\n" +
                        "Int     — 32-bit integer  (-2^31 to 2^31-1)\n" +
                        "Long    — 64-bit integer  (suffix L: 100L)\n" +
                        "Short   — 16-bit integer\n" +
                        "Byte    — 8-bit integer\n" +
                        "Float   — 32-bit floating point  (suffix f: 3.14f)\n" +
                        "Double  — 64-bit floating point  (default for decimals)\n" +
                        "Boolean — true / false\n" +
                        "Char    — single Unicode character in single quotes: 'A'\n\n" +
                        "Unlike Java, Kotlin does not allow implicit widening. You must call explicit " +
                        "conversion functions like .toInt(), .toLong(), .toDouble()."
                    )
                    CodeBlock(
                        "val i: Int     = 42\n" +
                        "val l: Long    = 100L\n" +
                        "val f: Float   = 3.14f\n" +
                        "val d: Double  = 3.14       // default decimal type\n" +
                        "val b: Boolean = true\n" +
                        "val c: Char    = 'K'\n\n" +
                        "// No implicit widening — must convert explicitly:\n" +
                        "val x: Int = 5\n" +
                        "val y: Long = x.toLong()    // OK\n" +
                        "// val z: Long = x          // COMPILE ERROR in Kotlin (unlike Java)\n\n" +
                        "// Numeric literals with underscores for readability:\n" +
                        "val million = 1_000_000\n" +
                        "val hex     = 0xFF_EC_D8_12\n" +
                        "val binary  = 0b0001_0101"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Type Inference") {
                    BodyText(
                        "Kotlin can infer the type of a variable from its initialiser. You do not " +
                        "have to write the type explicitly when it is obvious from context. Both " +
                        "forms are identical — the explicit type is just documentation.\n\n" +
                        "You must write the type explicitly when:\n" +
                        "• Declaring without an initialiser: val x: Int (assigned later)\n" +
                        "• The inferred type is too general and you want a more specific one"
                    )
                    CodeBlock(
                        "val name = \"Alice\"         // inferred: String\n" +
                        "val age  = 30              // inferred: Int\n" +
                        "val pi   = 3.14            // inferred: Double\n\n" +
                        "// Explicit types — same result:\n" +
                        "val name: String = \"Alice\"\n" +
                        "val age:  Int    = 30\n\n" +
                        "// Must be explicit when no initialiser:\n" +
                        "val result: Int\n" +
                        "result = computeValue()    // assigned later\n\n" +
                        "// Widen the inferred type:\n" +
                        "val n: Number = 42         // inferred would be Int, but Number is requested"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Nullable Types and Null Safety") {
                    BodyText(
                        "By default, Kotlin variables cannot hold null. To allow null you add ? to " +
                        "the type: String? means 'String or null'. The compiler enforces null checks — " +
                        "you cannot call a method on a nullable type without handling the null case.\n\n" +
                        "Safe call operator ?. — calls the method only if the receiver is non-null; " +
                        "returns null if it is null. Chain as many calls as needed.\n\n" +
                        "Elvis operator ?: — provides a default when the left side is null. The name " +
                        "comes from the sideways Elvis face: ?:\n\n" +
                        "Non-null assertion !! — tells the compiler 'I know this is not null'. Throws " +
                        "NullPointerException at runtime if it actually is null. Avoid in production " +
                        "code; use only when you are certain and have no better option.\n\n" +
                        "let — runs a lambda only when the value is non-null, with the non-null value " +
                        "available as 'it' inside the lambda."
                    )
                    CodeBlock(
                        "var s: String  = \"hello\"   // non-nullable — cannot be null\n" +
                        "var n: String? = null      // nullable — can be null\n\n" +
                        "// s = null   // COMPILE ERROR\n" +
                        "// n.length  // COMPILE ERROR — might be null, must check first\n\n" +
                        "// Safe call ?.  — returns null instead of crashing:\n" +
                        "val len: Int? = n?.length  // null if n is null\n\n" +
                        "// Elvis operator ?:  — default value:\n" +
                        "val len2: Int = n?.length ?: 0   // 0 if n is null\n\n" +
                        "// Non-null assertion !! — use sparingly:\n" +
                        "val len3: Int = n!!.length       // throws NPE if n is null\n\n" +
                        "// let — safe block:\n" +
                        "n?.let {\n" +
                        "    println(it.uppercase())   // only runs when n != null\n" +
                        "}\n\n" +
                        "// Smart cast after null check:\n" +
                        "if (n != null) {\n" +
                        "    println(n.length)  // compiler knows n is non-null here\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Arrays") {
                    BodyText(
                        "Array<T> is the generic array type. For primitive types there are specialised " +
                        "arrays — IntArray, LongArray, DoubleArray, FloatArray, BooleanArray, CharArray, " +
                        "ByteArray, ShortArray — that map directly to Java primitive arrays and avoid " +
                        "boxing overhead.\n\n" +
                        "Arrays are fixed in size after creation. Use MutableList for a resizable " +
                        "array-like structure (see Collections)."
                    )
                    CodeBlock(
                        "// Generic array:\n" +
                        "val names: Array<String> = arrayOf(\"Alice\", \"Bob\", \"Carol\")\n" +
                        "val empty: Array<Int>    = emptyArray()\n\n" +
                        "// Primitive arrays (no boxing):\n" +
                        "val nums: IntArray = intArrayOf(1, 2, 3, 4, 5)\n" +
                        "val zeros = IntArray(10)          // 10 zeros\n" +
                        "val squares = IntArray(5) { it * it }  // [0, 1, 4, 9, 16]\n\n" +
                        "// Access and modify:\n" +
                        "println(nums[0])       // 1 — zero-based index\n" +
                        "nums[0] = 99\n" +
                        "println(nums.size)     // 5\n\n" +
                        "// Iterate:\n" +
                        "for (n in nums) println(n)\n" +
                        "nums.forEach { println(it) }\n\n" +
                        "// Multi-dimensional (array of arrays):\n" +
                        "val matrix = Array(3) { IntArray(3) }  // 3x3 grid of zeros"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Strings") {
                    BodyText(
                        "String is immutable in Kotlin (same as Java). There is no separate char[].\n\n" +
                        "String templates — embed values directly in a string using \$ for a variable " +
                        "or \${...} for an expression. Much cleaner than Java's string concatenation.\n\n" +
                        "Raw strings — delimited by triple quotes. Preserve all whitespace and newlines " +
                        "as-is. No need to escape backslashes. trimIndent() removes common leading " +
                        "whitespace."
                    )
                    CodeBlock(
                        "val name = \"Alice\"\n" +
                        "val age  = 30\n\n" +
                        "// String template with variable:\n" +
                        "println(\"Hello, \$name!\")              // Hello, Alice!\n\n" +
                        "// String template with expression:\n" +
                        "println(\"Age next year: \${age + 1}\")  // Age next year: 31\n\n" +
                        "// Common operations:\n" +
                        "val s = \"Hello, World!\"\n" +
                        "println(s.length)          // 13\n" +
                        "println(s.uppercase())     // HELLO, WORLD!\n" +
                        "println(s.substring(0, 5)) // Hello\n" +
                        "println(s.contains(\"World\")) // true\n" +
                        "println(s.replace(\"World\", \"Kotlin\")) // Hello, Kotlin!\n" +
                        "println(s.split(\", \"))     // [Hello, World!]\n\n" +
                        "// Raw string (triple-quoted):\n" +
                        "val poem = " + "\"\"\"" + "\n" +
                        "    Roses are red\n" +
                        "    Violets are blue\n" +
                        "    " + "\"\"\"" + ".trimIndent()"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Enums") {
                    BodyText(
                        "Enums in Kotlin are declared with enum class. Each constant is an instance " +
                        "of the enum class. You can add properties and methods to enum classes.\n\n" +
                        "values() returns all constants as an Array. valueOf(name) returns the constant " +
                        "with that name. entries (Kotlin 1.9+) is a more efficient alternative to values()."
                    )
                    CodeBlock(
                        "// Basic enum:\n" +
                        "enum class Direction { NORTH, SOUTH, EAST, WEST }\n\n" +
                        "val dir = Direction.NORTH\n" +
                        "println(dir)           // NORTH\n" +
                        "println(dir.name)      // NORTH (the constant's name as String)\n" +
                        "println(dir.ordinal)   // 0 (zero-based position)\n\n" +
                        "// Enum with properties:\n" +
                        "enum class Planet(val mass: Double, val radius: Double) {\n" +
                        "    EARTH(5.97e24, 6.371e6),\n" +
                        "    MARS (6.39e23, 3.389e6);\n\n" +
                        "    fun surfaceGravity() = 6.674e-11 * mass / (radius * radius)\n" +
                        "}\n\n" +
                        "println(Planet.EARTH.surfaceGravity())  // ~9.8 m/s^2\n\n" +
                        "// Iterating all values:\n" +
                        "for (d in Direction.values()) println(d)\n\n" +
                        "// Using in a when expression:\n" +
                        "val msg = when (dir) {\n" +
                        "    Direction.NORTH -> \"Going up\"\n" +
                        "    Direction.SOUTH -> \"Going down\"\n" +
                        "    else            -> \"Going sideways\"\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
