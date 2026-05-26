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
fun KotlinDataClassesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Kotlin — Data Classes",
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
                SectionCard(title = "What Is a Data Class") {
                    BodyText(
                        "A data class is a class whose primary purpose is to hold data. The data " +
                        "keyword tells the compiler to generate boilerplate automatically: equals(), " +
                        "hashCode(), toString(), copy(), and componentN() functions.\n\n" +
                        "Rules: the primary constructor must have at least one parameter, and each " +
                        "must be declared as val or var. Data classes cannot be abstract, open, " +
                        "sealed, or inner."
                    )
                    CodeBlock(
                        "data class User(val name: String, val age: Int)\n" +
                        "\n" +
                        "// That one line generates all of:\n" +
                        "//   equals() / hashCode()  based on name and age\n" +
                        "//   toString()             \"User(name=Gil, age=30)\"\n" +
                        "//   copy()                 shallow copy with optional overrides\n" +
                        "//   component1()           returns name\n" +
                        "//   component2()           returns age"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Auto-generated Methods") {
                    BodyText(
                        "equals() compares all primary constructor properties structurally, not by " +
                        "reference. Two instances with the same property values are equal. " +
                        "hashCode() is consistent with equals() so data class instances work " +
                        "correctly as HashMap keys or in Sets.\n\n" +
                        "toString() produces a readable string listing each property name and value, " +
                        "which is invaluable for logging and debugging."
                    )
                    CodeBlock(
                        "data class Point(val x: Int, val y: Int)\n" +
                        "\n" +
                        "val p1 = Point(3, 4)\n" +
                        "val p2 = Point(3, 4)\n" +
                        "val p3 = Point(0, 0)\n" +
                        "\n" +
                        "println(p1 == p2)       // true  (structural)\n" +
                        "println(p1 === p2)      // false (different objects in memory)\n" +
                        "println(p1 == p3)       // false\n" +
                        "println(p1)             // Point(x=3, y=4)\n" +
                        "\n" +
                        "val set = setOf(p1, p2) // only 1 entry — equal elements collapse\n" +
                        "println(set.size)       // 1"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "copy() — Selective Updates") {
                    BodyText(
                        "copy() creates a new instance with all properties copied from the original. " +
                        "Any property can be overridden by naming it. The original is unchanged, " +
                        "making data classes ideal for immutable update patterns — the standard " +
                        "approach in ViewModel state management with StateFlow."
                    )
                    CodeBlock(
                        "data class Settings(\n" +
                        "    val darkMode: Boolean = false,\n" +
                        "    val fontSize: Int     = 14,\n" +
                        "    val language: String  = \"en\"\n" +
                        ")\n" +
                        "\n" +
                        "val defaults = Settings()\n" +
                        "val large    = defaults.copy(fontSize = 20)\n" +
                        "val darkLarge = large.copy(darkMode = true)\n" +
                        "\n" +
                        "// ViewModel pattern\n" +
                        "data class UiState(val loading: Boolean = false, val items: List<String> = emptyList())\n" +
                        "\n" +
                        "private val _state = MutableStateFlow(UiState())\n" +
                        "_state.value = _state.value.copy(loading = true)   // other fields preserved"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Destructuring") {
                    BodyText(
                        "The compiler generates componentN() functions for each primary constructor " +
                        "property in order. These enable destructuring declarations: you can unpack " +
                        "a data class into individual variables with a single statement. Destructuring " +
                        "also works in for loops and lambda parameters."
                    )
                    CodeBlock(
                        "data class Result(val value: Int, val error: String?)\n" +
                        "\n" +
                        "val res = Result(42, null)\n" +
                        "val (value, error) = res   // component1() and component2()\n" +
                        "println(value)             // 42\n" +
                        "\n" +
                        "// Destructure in a for loop\n" +
                        "val map = mapOf(\"a\" to 1, \"b\" to 2)\n" +
                        "for ((key, v) in map) println(\"\$key=\$v\")   // Map.Entry has component1/2\n" +
                        "\n" +
                        "// Skip a component with _\n" +
                        "val (_, err) = res\n" +
                        "\n" +
                        "// In a lambda\n" +
                        "listOf(Point(1,2), Point(3,4)).forEach { (x, y) -> println(\"\$x,\$y\") }"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Requirements & Restrictions") {
                    BodyText(
                        "Only properties declared in the primary constructor participate in the " +
                        "auto-generated equals(), hashCode(), toString(), and copy(). Properties " +
                        "declared in the class body are excluded — this is a common source of bugs.\n\n" +
                        "Data classes cannot be: abstract (no instantiation), open (no subclassing " +
                        "by default, though they can implement interfaces), sealed, or inner."
                    )
                    CodeBlock(
                        "data class Person(val name: String) {\n" +
                        "    var loginCount: Int = 0   // body property — NOT in equals/hashCode\n" +
                        "}\n" +
                        "\n" +
                        "val p1 = Person(\"Gil\").also { it.loginCount = 5 }\n" +
                        "val p2 = Person(\"Gil\").also { it.loginCount = 0 }\n" +
                        "println(p1 == p2)   // true — loginCount is ignored!\n" +
                        "\n" +
                        "// Data classes CAN implement interfaces\n" +
                        "interface Identifiable { val id: Int }\n" +
                        "data class Item(override val id: Int, val name: String) : Identifiable"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Data Classes vs Regular Classes") {
                    BodyText(
                        "Use a data class when:\n" +
                        "• The class is a value carrier (DTO, API response, UI state, event).\n" +
                        "• You need structural equality.\n" +
                        "• You want copy() for immutable updates.\n\n" +
                        "Use a regular class when:\n" +
                        "• Identity matters — two objects with the same fields are not interchangeable " +
                        "(e.g. database entities tracked by ID).\n" +
                        "• The class has significant behavior rather than just data.\n" +
                        "• You need inheritance hierarchies.\n" +
                        "• Mutable state must be controlled carefully (data classes expose copy())."
                    )
                    CodeBlock(
                        "// Good: data class for a UI event\n" +
                        "data class LoginEvent(val userId: String, val timestamp: Long)\n" +
                        "\n" +
                        "// Avoid: entity with database identity should be a regular class\n" +
                        "// Two User objects with id=1 are the SAME user even if other fields differ\n" +
                        "class User(val id: Int, var name: String) {\n" +
                        "    override fun equals(other: Any?) = other is User && other.id == id\n" +
                        "    override fun hashCode() = id\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Sealed + Generic Data Classes") {
                    BodyText(
                        "A common Kotlin pattern is a sealed class whose subtypes are data classes. " +
                        "This models a fixed set of states while giving each state its own typed " +
                        "payload. The when expression is exhaustive over the sealed hierarchy, so " +
                        "adding a new state forces you to handle it everywhere.\n\n" +
                        "Data classes can also be generic, making them useful as reusable wrappers."
                    )
                    CodeBlock(
                        "// Sealed Result type\n" +
                        "sealed class Result<out T>\n" +
                        "data class Success<T>(val data: T)   : Result<T>()\n" +
                        "data class Error(val message: String): Result<Nothing>()\n" +
                        "object Loading                       : Result<Nothing>()\n" +
                        "\n" +
                        "fun handle(result: Result<User>) = when (result) {\n" +
                        "    is Success -> showUser(result.data)\n" +
                        "    is Error   -> showError(result.message)\n" +
                        "    Loading    -> showSpinner()\n" +
                        "}\n" +
                        "\n" +
                        "// Generic data class\n" +
                        "data class Page<T>(val items: List<T>, val total: Int, val page: Int)"
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
