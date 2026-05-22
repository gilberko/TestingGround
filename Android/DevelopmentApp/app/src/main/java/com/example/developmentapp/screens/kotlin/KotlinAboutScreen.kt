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
fun KotlinAboutScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Kotlin — About",
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
                SectionCard(title = "History") {
                    BodyText(
                        "Kotlin was created by JetBrains — the company behind IntelliJ IDEA. Development " +
                        "started in 2010, the language was open-sourced in 2012 under the Apache 2 license, " +
                        "and version 1.0 was officially released in February 2016.\n\n" +
                        "The name comes from Kotlin Island near St. Petersburg, Russia — the same naming " +
                        "convention used by Java (named after Java Island in Indonesia).\n\n" +
                        "JetBrains designed Kotlin to be:\n" +
                        "• Concise — much less boilerplate than Java\n" +
                        "• Safe — null safety built into the type system\n" +
                        "• Interoperable — 100% compatible with existing Java code and libraries\n" +
                        "• Tool-friendly — excellent IDE support from day one\n\n" +
                        "In May 2017, Google announced first-class support for Kotlin on Android. Since " +
                        "2019 Kotlin has been Google's preferred language for Android development."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Kotlin and Android") {
                    BodyText(
                        "Kotlin is now the dominant language for Android development. Google's own " +
                        "Android Jetpack libraries, and especially Jetpack Compose (the modern UI toolkit), " +
                        "are Kotlin-first — they are designed around Kotlin idioms and may not work as well " +
                        "or at all from Java.\n\n" +
                        "Key Android-specific Kotlin features:\n" +
                        "• Coroutines — Android's recommended way to write asynchronous code (network " +
                        "calls, database access) without blocking the UI thread\n" +
                        "• Extension functions — add methods to existing Android SDK classes without " +
                        "subclassing\n" +
                        "• Null safety — eliminates the NullPointerExceptions that plague Java Android code\n" +
                        "• Data classes — replace verbose Java POJOs used for model objects\n\n" +
                        "All new Android SDK documentation and code samples from Google are written in Kotlin."
                    )
                    CodeBlock(
                        "// A Jetpack Compose UI written in Kotlin:\n" +
                        "@Composable\n" +
                        "fun Greeting(name: String) {\n" +
                        "    Text(text = \"Hello, \$name!\")\n" +
                        "}\n\n" +
                        "// A coroutine for a network call (ViewModel):\n" +
                        "viewModelScope.launch {\n" +
                        "    val data = withContext(Dispatchers.IO) { api.fetchData() }\n" +
                        "    uiState.value = data\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Interoperability with Java") {
                    BodyText(
                        "Kotlin compiles to the same JVM bytecode as Java. Kotlin and Java files can " +
                        "coexist in the same project — you can call Java code from Kotlin and Kotlin code " +
                        "from Java, in the same build.\n\n" +
                        "Calling Java from Kotlin is seamless. Most Java types work directly. The one " +
                        "nuance is null safety: Java types have no nullability information, so Kotlin " +
                        "treats them as 'platform types' (written as String!) — you can use them either " +
                        "as String or String?, but you take responsibility for null checks.\n\n" +
                        "Calling Kotlin from Java works, but Kotlin features that have no Java equivalent " +
                        "require special handling:\n" +
                        "• Top-level functions — accessible as static methods on a generated class named " +
                        "after the file (e.g. MyFileKt.myFunction())\n" +
                        "• Extension functions — accessible as static methods with the receiver as the " +
                        "first argument\n" +
                        "• Default parameters — Java sees overloads only if @JvmOverloads is used\n" +
                        "• Properties — exposed as getXxx() / setXxx() methods in Java"
                    )
                    CodeBlock(
                        "// Kotlin calling Java (seamless):\n" +
                        "val list = java.util.ArrayList<String>()  // Java class, no wrapper needed\n" +
                        "list.add(\"hello\")                         // Java method call\n\n" +
                        "// Platform type — Java returns String (no nullability info):\n" +
                        "val s: String = System.getProperty(\"user.name\")   // treated as non-null\n" +
                        "val s2: String? = System.getProperty(\"missing\")   // or as nullable\n\n" +
                        "// Kotlin annotations for Java callers:\n" +
                        "class MyClass {\n" +
                        "    @JvmField   val PI = 3.14          // expose as field, not getPI()\n" +
                        "    @JvmStatic  fun create() = MyClass() // expose as static method\n" +
                        "    @JvmOverloads fun greet(name: String = \"World\") = println(\"Hello \$name\")\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Recommended IDEs") {
                    BodyText(
                        "IntelliJ IDEA (JetBrains) — the best Kotlin IDE for general PC development. " +
                        "JetBrains built Kotlin, so IntelliJ has the deepest integration: refactoring, " +
                        "smart completion, coroutine debugging, and the Kotlin REPL. The Community " +
                        "Edition is free and supports Kotlin fully.\n\n" +
                        "Android Studio (Google) — built on IntelliJ, this is the standard IDE for " +
                        "Android app development. It adds Android-specific tools: layout editor, " +
                        "ADB integration, profiler, emulator, and Gradle integration tuned for Android. " +
                        "Free and open source. Use this for any Android project.\n\n" +
                        "Visual Studio Code — lighter weight than IntelliJ. Install the 'Kotlin' " +
                        "extension (by fwcd) for syntax highlighting, basic completion, and run support " +
                        "via kotlinc. Good for learning or small scripts, but lacks the deep refactoring " +
                        "and debugging of IntelliJ. Requires a separate JDK installation.\n\n" +
                        "Online — play.kotlinlang.org is the official Kotlin playground. No installation " +
                        "required, useful for quick experiments."
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
