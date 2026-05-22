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
fun KotlinHelloWorldScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Kotlin — Hello, World!",
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
                SectionCard(title = "The Program") {
                    BodyText(
                        "A complete Kotlin Hello World program — saved in a file called Hello.kt:"
                    )
                    CodeBlock(
                        "fun main() {\n" +
                        "    println(\"Hello, World!\")\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Line by Line") {
                    BodyText(
                        "fun main()\n" +
                        "fun is the keyword that declares a function. main is the entry point — the " +
                        "JVM looks for a top-level function named main when the program starts. In " +
                        "Kotlin, main does not have to be inside a class (unlike Java where every " +
                        "method must belong to a class).\n\n" +
                        "The empty parentheses () mean main takes no parameters. You can optionally " +
                        "write fun main(args: Array<String>) to receive command-line arguments.\n\n" +
                        "println(\"Hello, World!\")\n" +
                        "println is a standard library function that prints its argument followed by a " +
                        "newline. It is equivalent to Java's System.out.println() but written without " +
                        "the System.out prefix. The string literal is enclosed in double quotes.\n\n" +
                        "Curly braces { } delimit the function body. Statements inside do not require " +
                        "a semicolon at the end — newlines are enough."
                    )
                    CodeBlock(
                        "// Java equivalent for comparison:\n" +
                        "public class Hello {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        System.out.println(\"Hello, World!\");\n" +
                        "    }\n" +
                        "}\n\n" +
                        "// Kotlin — no class required, no semicolons:\n" +
                        "fun main() {\n" +
                        "    println(\"Hello, World!\")\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "How to Build") {
                    BodyText(
                        "From the command line using the Kotlin compiler (kotlinc):\n\n" +
                        "  kotlinc Hello.kt -include-runtime -d hello.jar\n\n" +
                        "• kotlinc — the Kotlin compiler\n" +
                        "• Hello.kt — the source file\n" +
                        "• -include-runtime — bundles the Kotlin runtime into the JAR so it can run " +
                        "without Kotlin on the classpath\n" +
                        "• -d hello.jar — output file name\n\n" +
                        "With Gradle (standard for Android and multi-module projects), add the Kotlin " +
                        "plugin to build.gradle.kts:\n\n" +
                        "  plugins { kotlin(\"jvm\") version \"2.0.0\" }\n\n" +
                        "Then: ./gradlew build\n\n" +
                        "In Android Studio or IntelliJ IDEA, press Shift+F10 (Run) or click the green " +
                        "triangle — the IDE handles compilation automatically."
                    )
                    CodeBlock(
                        "# Install kotlinc via SDKMAN (Linux/Mac):\n" +
                        "sdk install kotlin\n\n" +
                        "# Compile:\n" +
                        "kotlinc Hello.kt -include-runtime -d hello.jar\n\n" +
                        "# Gradle build.gradle.kts snippet:\n" +
                        "plugins {\n" +
                        "    kotlin(\"jvm\") version \"2.0.0\"\n" +
                        "    application\n" +
                        "}\n" +
                        "application {\n" +
                        "    mainClass.set(\"HelloKt\")  // top-level main compiles to HelloKt class\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "How to Run") {
                    BodyText(
                        "Run the compiled JAR with the standard java command:\n\n" +
                        "  java -jar hello.jar\n\n" +
                        "Alternatively, if you have kotlinc installed, run the source file directly as " +
                        "a script (no explicit compilation step):\n\n" +
                        "  kotlin Hello.kt      (or: kotlinc -script Hello.kts for .kts scripts)\n\n" +
                        "When the file is compiled to a JAR, Kotlin's compiler wraps the top-level " +
                        "main() in a class called HelloKt (file name + Kt suffix). That is the class " +
                        "the JVM looks for when you run the JAR.\n\n" +
                        "In Android Studio, press the Run button or Shift+F10. The IDE deploys the " +
                        "app to a connected device or emulator and launches it."
                    )
                    CodeBlock(
                        "# Run the compiled JAR:\n" +
                        "java -jar hello.jar\n" +
                        "# Output: Hello, World!\n\n" +
                        "# Run as a script directly:\n" +
                        "kotlin Hello.kt\n" +
                        "# Output: Hello, World!\n\n" +
                        "# What the compiler generates behind the scenes:\n" +
                        "// Hello.kt  →  compiled to class HelloKt\n" +
                        "// public final class HelloKt {\n" +
                        "//     public static final void main(String[] args) { ... }\n" +
                        "// }"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
