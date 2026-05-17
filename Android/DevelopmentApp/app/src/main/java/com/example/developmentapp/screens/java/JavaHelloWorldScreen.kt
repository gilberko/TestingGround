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
fun JavaHelloWorldScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Java — Hello, World!",
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
                    BodyText("Here is the simplest possible Java program:")
                    CodeBlock(
                        "public class HelloWorld {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        System.out.println(\"Hello, World!\");\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Line by Line") {
                    BodyText("public class HelloWorld")
                    BodyText(
                        "Every Java source file must contain a public class, and the class name must " +
                        "exactly match the filename. This file must be saved as HelloWorld.java. " +
                        "Everything in Java lives inside a class — there are no standalone functions " +
                        "outside a class."
                    )
                    BodyText("public static void main(String[] args)")
                    BodyText(
                        "This is the program's entry point — the JVM calls this method to start your " +
                        "program. Each keyword has a specific role:"
                    )
                    BodyText(
                        "public — the method is accessible from anywhere, including from the JVM launcher."
                    )
                    BodyText(
                        "static — the method belongs to the class itself, not to an instance. The JVM " +
                        "can call it without creating a HelloWorld object first."
                    )
                    BodyText("void — the method returns nothing.")
                    BodyText(
                        "String[] args — an array of strings holding any command-line arguments passed " +
                        "when the program is launched. If you run \"java HelloWorld foo bar\", then " +
                        "args[0] is \"foo\" and args[1] is \"bar\"."
                    )
                    BodyText("System.out.println(\"Hello, World!\")")
                    BodyText(
                        "System is a class in java.lang, which is automatically imported in every Java " +
                        "file. out is a static field of System of type PrintStream — it represents " +
                        "standard output (your terminal). println prints the given string followed by " +
                        "a newline. Use print (without \"ln\") to print without a trailing newline."
                    )
                    CodeBlock(
                        "System.out.println(\"With newline\");\n" +
                        "System.out.print(\"No newline \");\n" +
                        "System.out.print(\"same line\\n\");   // manual newline\n" +
                        "System.out.printf(\"Value: %d%n\", 42); // printf-style"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "How to Run") {
                    BodyText("From the command line:")
                    CodeBlock(
                        "# Save the file as HelloWorld.java\n\n" +
                        "# Step 1 — compile: produces HelloWorld.class\n" +
                        "javac HelloWorld.java\n\n" +
                        "# Step 2 — run (no .class extension)\n" +
                        "java HelloWorld\n\n" +
                        "# Output:\n" +
                        "Hello, World!"
                    )
                    BodyText(
                        "The filename must match the class name exactly, including capitalisation. " +
                        "If your class is named HelloWorld, the file must be HelloWorld.java — not " +
                        "helloworld.java or hello_world.java. The compiler will refuse to compile it " +
                        "otherwise."
                    )
                    BodyText(
                        "From IntelliJ IDEA: create a new Java project, create HelloWorld.java inside " +
                        "the src folder, paste the code, then press Shift+F10 (or click the green Run " +
                        "triangle in the gutter). IntelliJ compiles and runs in one step."
                    )
                    BodyText(
                        "Since Java 11, you can also run a single-file program directly without a " +
                        "separate compile step:"
                    )
                    CodeBlock("java HelloWorld.java   # Java 11+ — for single-file programs only")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
