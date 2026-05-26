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
fun KotlinEnumsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Kotlin — Enums",
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
                SectionCard(title = "Enum Basics") {
                    BodyText(
                        "An enum class declares a fixed set of named constants. Each constant is a " +
                        "singleton instance of the enum class. There is no new keyword and you cannot " +
                        "create instances outside the enum declaration. Enums in Kotlin are classes, " +
                        "so they can have properties, methods, and implement interfaces."
                    )
                    CodeBlock(
                        "enum class Direction { NORTH, SOUTH, EAST, WEST }\n" +
                        "\n" +
                        "val dir: Direction = Direction.NORTH\n" +
                        "println(dir)          // NORTH\n" +
                        "println(dir.name)     // \"NORTH\"  (the declared constant name)\n" +
                        "println(dir.ordinal)  // 0        (0-based position in declaration)\n" +
                        "\n" +
                        "// Compare\n" +
                        "if (dir == Direction.NORTH) println(\"heading north\")"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Enum with Properties") {
                    BodyText(
                        "Each enum constant can carry data by giving the enum class a constructor. " +
                        "Constants call the constructor when they are declared. The properties are " +
                        "typically val (immutable), since enum constants are singletons."
                    )
                    CodeBlock(
                        "enum class Planet(val mass: Double, val radius: Double) {\n" +
                        "    MERCURY(3.303e+23, 2.4397e6),\n" +
                        "    VENUS  (4.869e+24, 6.0518e6),\n" +
                        "    EARTH  (5.976e+24, 6.37814e6),\n" +
                        "    MARS   (6.421e+23, 3.3972e6);\n" +
                        "    // note: semicolon separates constants from the rest of the body\n" +
                        "\n" +
                        "    val surfaceGravity: Double\n" +
                        "        get() = G * mass / (radius * radius)\n" +
                        "    companion object { const val G = 6.67300E-11 }\n" +
                        "}\n" +
                        "\n" +
                        "println(Planet.EARTH.surfaceGravity)  // ~9.80"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Enum with Methods") {
                    BodyText(
                        "An enum can define regular methods shared by all entries, or abstract " +
                        "methods that each entry must override individually. Abstract methods let " +
                        "each constant carry different behavior without a when expression."
                    )
                    CodeBlock(
                        "enum class Operation {\n" +
                        "    PLUS {\n" +
                        "        override fun apply(x: Double, y: Double) = x + y\n" +
                        "    },\n" +
                        "    MINUS {\n" +
                        "        override fun apply(x: Double, y: Double) = x - y\n" +
                        "    },\n" +
                        "    TIMES {\n" +
                        "        override fun apply(x: Double, y: Double) = x * y\n" +
                        "    };\n" +
                        "\n" +
                        "    abstract fun apply(x: Double, y: Double): Double\n" +
                        "\n" +
                        "    fun describe() = \"\${name}: apply(2, 3) = \${apply(2.0, 3.0)}\"\n" +
                        "}\n" +
                        "\n" +
                        "println(Operation.PLUS.describe())   // PLUS: apply(2, 3) = 5.0"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Built-in Members") {
                    BodyText(
                        "Every enum class automatically gets:\n\n" +
                        "• .name — the constant's declared name as a String.\n" +
                        "• .ordinal — its 0-based position in the declaration order.\n" +
                        "• entries — (Kotlin 1.9+) an immutable EnumEntries<T> list of all constants. " +
                        "Prefer this over the older values() function which returns a new array on " +
                        "every call.\n" +
                        "• enumValueOf<T>(name) — returns the constant with the given name or throws " +
                        "IllegalArgumentException.\n" +
                        "• enumValues<T>() — returns an Array<T> of all constants (legacy, prefer entries)."
                    )
                    CodeBlock(
                        "enum class Color { RED, GREEN, BLUE }\n" +
                        "\n" +
                        "// entries (Kotlin 1.9+)\n" +
                        "for (c in Color.entries) println(\"\${c.ordinal}: \${c.name}\")\n" +
                        "// 0: RED\n" +
                        "// 1: GREEN\n" +
                        "// 2: BLUE\n" +
                        "\n" +
                        "// By name\n" +
                        "val c: Color = enumValueOf<Color>(\"GREEN\")   // Color.GREEN\n" +
                        "val safe: Color? = Color.entries.find { it.name == \"PURPLE\" }  // null"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "when with Enums") {
                    BodyText(
                        "A when expression over an enum type is exhaustive: the compiler requires " +
                        "handling every entry (or an else branch). If you add a new constant later, " +
                        "every when without an else branch becomes a compile error, which helps you " +
                        "find all affected code paths. Use when as an expression to return a value."
                    )
                    CodeBlock(
                        "enum class Status { PENDING, ACTIVE, SUSPENDED, CLOSED }\n" +
                        "\n" +
                        "// when as a statement\n" +
                        "fun handleStatus(s: Status) = when (s) {\n" +
                        "    Status.PENDING   -> enqueueMail()\n" +
                        "    Status.ACTIVE    -> enableAccess()\n" +
                        "    Status.SUSPENDED -> showWarning()\n" +
                        "    Status.CLOSED    -> archiveAccount()\n" +
                        "    // No else needed — all cases covered\n" +
                        "}\n" +
                        "\n" +
                        "// when as an expression returning a value\n" +
                        "val label: String = when (s) {\n" +
                        "    Status.PENDING   -> \"Awaiting activation\"\n" +
                        "    Status.ACTIVE    -> \"Account active\"\n" +
                        "    Status.SUSPENDED -> \"Account suspended\"\n" +
                        "    Status.CLOSED    -> \"Account closed\"\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Companion Object & Interfaces") {
                    BodyText(
                        "An enum can implement one or more interfaces, and can have a companion " +
                        "object for factory methods or constants. This is useful for safe name " +
                        "lookup that returns null instead of throwing."
                    )
                    CodeBlock(
                        "interface Displayable { fun displayName(): String }\n" +
                        "\n" +
                        "enum class Priority : Displayable {\n" +
                        "    LOW, MEDIUM, HIGH, CRITICAL;\n" +
                        "\n" +
                        "    override fun displayName() = name.lowercase().replaceFirstChar { it.uppercase() }\n" +
                        "\n" +
                        "    companion object {\n" +
                        "        fun fromString(s: String): Priority? =\n" +
                        "            entries.find { it.name.equals(s, ignoreCase = true) }\n" +
                        "    }\n" +
                        "}\n" +
                        "\n" +
                        "println(Priority.HIGH.displayName())         // High\n" +
                        "println(Priority.fromString(\"medium\"))       // MEDIUM\n" +
                        "println(Priority.fromString(\"unknown\"))      // null"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Enum vs Sealed Class") {
                    BodyText(
                        "Both provide exhaustive when expressions, but they serve different purposes.\n\n" +
                        "Enum class:\n" +
                        "• All constants are instances of the same type with the same set of properties.\n" +
                        "• Instances are always singletons — no per-instance variable data.\n" +
                        "• Good for: directions, days of the week, status codes, fixed option sets.\n\n" +
                        "Sealed class:\n" +
                        "• Each subclass can have its own distinct properties and types.\n" +
                        "• Instances can carry different data (Success has T, Error has message).\n" +
                        "• Good for: Result types, UI states, events with payloads.\n\n" +
                        "Rule of thumb: if every variant looks the same structurally, use enum. " +
                        "If variants carry different data shapes, use sealed."
                    )
                    CodeBlock(
                        "// Enum — all share the same structure, only the value differs\n" +
                        "enum class HttpMethod { GET, POST, PUT, DELETE, PATCH }\n" +
                        "\n" +
                        "// Sealed — each subtype has its own payload\n" +
                        "sealed class NetworkResult\n" +
                        "data class HttpSuccess(val body: String, val code: Int) : NetworkResult()\n" +
                        "data class HttpError(val code: Int, val reason: String)  : NetworkResult()\n" +
                        "object NetworkTimeout                                    : NetworkResult()"
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
