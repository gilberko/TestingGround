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
fun KotlinPackagesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Kotlin — Packages",
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
                SectionCard(title = "Package Declaration") {
                    BodyText(
                        "A package declaration goes at the very top of a Kotlin source file, before " +
                        "any imports. All declarations in the file (classes, functions, properties) " +
                        "belong to that package.\n\n" +
                        "Unlike Java, Kotlin does NOT require the file to be in a directory that " +
                        "matches the package name — the package is purely a logical grouping. That " +
                        "said, the Kotlin style guide and Android conventions recommend keeping the " +
                        "directory structure aligned with the package hierarchy.\n\n" +
                        "If no package declaration is present, the file belongs to the default package."
                    )
                    CodeBlock(
                        "// At the top of MyClass.kt:\n" +
                        "package com.example.myapp.util\n\n" +
                        "class MyHelper {         // fully qualified: com.example.myapp.util.MyHelper\n" +
                        "    fun doSomething() { }\n" +
                        "}\n\n" +
                        "fun topLevelHelper() { }  // also in com.example.myapp.util\n\n" +
                        "// From another file — use the fully qualified name or import:\n" +
                        "val h = com.example.myapp.util.MyHelper()"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Imports") {
                    BodyText(
                        "import brings a declaration into scope so you can use its short name without " +
                        "the full package prefix.\n\n" +
                        "Single import — import the specific class or function you need.\n" +
                        "Wildcard import — import kotlin.math.* imports everything from the package. " +
                        "The Kotlin style guide generally prefers explicit single imports over wildcards.\n\n" +
                        "Aliased import — use as to rename a declaration on import. Useful when two " +
                        "packages export the same name and you need both."
                    )
                    CodeBlock(
                        "import kotlin.math.sqrt        // single import\n" +
                        "import kotlin.math.PI\n" +
                        "import kotlin.math.*           // wildcard — imports everything\n\n" +
                        "// Aliased import — rename to avoid name collision:\n" +
                        "import java.util.Date as JavaDate\n" +
                        "import org.joda.time.DateTime as JodaDate\n\n" +
                        "fun circleArea(r: Double) = PI * r * r\n" +
                        "fun hypotenuse(a: Double, b: Double) = sqrt(a * a + b * b)\n\n" +
                        "val d1 = JavaDate()\n" +
                        "val d2 = JodaDate.now()"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Top-Level Declarations") {
                    BodyText(
                        "In Java, every function and variable must live inside a class. Kotlin removes " +
                        "this restriction — functions, properties, and even type aliases can be declared " +
                        "directly in a file, outside any class. These are called top-level declarations.\n\n" +
                        "Under the hood, the Kotlin compiler wraps top-level declarations in a synthetic " +
                        "class named after the file (e.g. Utils.kt → UtilsKt). Java code can call them " +
                        "as UtilsKt.myFunction(). Annotate with @JvmName to customise the generated " +
                        "class name, or @file:JvmName(\"Utils\") at the top of the file."
                    )
                    CodeBlock(
                        "// Utils.kt\n" +
                        "package com.example.util\n\n" +
                        "// Top-level function — no class wrapper needed:\n" +
                        "fun formatName(first: String, last: String) = \"\$last, \$first\"\n\n" +
                        "// Top-level property (constant):\n" +
                        "const val MAX_RETRIES = 3\n\n" +
                        "// From another Kotlin file — import and use directly:\n" +
                        "import com.example.util.formatName\n" +
                        "import com.example.util.MAX_RETRIES\n\n" +
                        "println(formatName(\"Alice\", \"Smith\"))  // Smith, Alice\n" +
                        "println(MAX_RETRIES)                   // 3\n\n" +
                        "// From Java — accessed as UtilsKt.formatName(...):\n" +
                        "// String s = UtilsKt.formatName(\"Alice\", \"Smith\");"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "internal Visibility") {
                    BodyText(
                        "The internal modifier makes a declaration visible within the same compilation " +
                        "module — typically a single Gradle subproject or Maven module.\n\n" +
                        "This is Kotlin's replacement for Java's package-private (no-modifier) " +
                        "visibility. Java's package-private restricts to the same package directory; " +
                        "Kotlin's internal restricts to the same module, which is often more aligned " +
                        "with real-world code organisation.\n\n" +
                        "internal is enforced by the compiler — another module that depends on yours " +
                        "cannot access internal declarations. This makes it safe for library authors " +
                        "to expose implementation details within a module without leaking them to " +
                        "downstream consumers."
                    )
                    CodeBlock(
                        "// In module A:\n" +
                        "internal class DatabaseHelper { }           // not visible outside module A\n" +
                        "internal fun parseConfig(): Config { ... }  // same\n\n" +
                        "class UserRepository {\n" +
                        "    internal fun connect() { }  // usable within module A only\n" +
                        "    public  fun getUser(): User { ... }  // visible to all\n" +
                        "}\n\n" +
                        "// In module B (depends on A):\n" +
                        "val repo = UserRepository()\n" +
                        "repo.getUser()   // OK — public\n" +
                        "// repo.connect() // COMPILE ERROR — internal to module A"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Standard Library Packages") {
                    BodyText(
                        "The kotlin.* package is automatically imported into every Kotlin file — you " +
                        "never need to import Int, String, List, println, etc.\n\n" +
                        "Other commonly used standard library packages (need explicit import or " +
                        "wildcard):\n\n" +
                        "kotlin.collections — List, Map, Set, MutableList, filter, map, forEach, etc.\n" +
                        "kotlin.io — File extensions: readText, readLines, forEachLine, writeText\n" +
                        "kotlin.text — String extensions: trim, split, toInt, format, Regex\n" +
                        "kotlin.math — math functions: sqrt, abs, pow, sin, cos, floor, ceil\n" +
                        "kotlin.concurrent — thread { } builder, Timer extensions\n" +
                        "kotlinx.coroutines — coroutines (separate dependency, not in stdlib)"
                    )
                    CodeBlock(
                        "// Always available — no import:\n" +
                        "val list = listOf(1, 2, 3)    // kotlin.collections\n" +
                        "println(\"hello\".uppercase())   // kotlin.text extension\n" +
                        "val t = Thread { }             // java.lang.Thread (auto-imported)\n\n" +
                        "// kotlin.math — needs import:\n" +
                        "import kotlin.math.sqrt\n" +
                        "import kotlin.math.PI\n" +
                        "val r = sqrt(16.0)   // 4.0\n\n" +
                        "// kotlin.io — File extensions:\n" +
                        "import java.io.File\n" +
                        "val text = File(\"notes.txt\").readText()   // kotlin.io extension on File\n\n" +
                        "// kotlinx.coroutines — separate Gradle dependency:\n" +
                        "// implementation(\"org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0\")\n" +
                        "import kotlinx.coroutines.*"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
