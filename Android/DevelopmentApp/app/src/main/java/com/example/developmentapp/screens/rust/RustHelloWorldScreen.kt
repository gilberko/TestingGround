package com.example.developmentapp.screens.rust

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
fun RustHelloWorldScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Rust — Hello, World!",
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
                SectionCard(title = "Hello, World!") {
                    CodeBlock(
                        "fn main() {\n" +
                        "    println!(\"Hello, World!\");\n" +
                        "}"
                    )
                    BodyText(
                        "This is a complete, compilable Rust program. Save it as main.rs and run it with " +
                        "cargo run (inside a cargo project) or rustc main.rs followed by ./main. " +
                        "It prints Hello, World! to the terminal."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The fn Keyword") {
                    BodyText(
                        "fn declares a function. The basic syntax is:"
                    )
                    CodeBlock(
                        "fn function_name(parameters) {\n" +
                        "    // body\n" +
                        "}"
                    )
                    BodyText(
                        "All executable Rust code lives inside functions. The convention is snake_case " +
                        "for function names (e.g. my_function, not myFunction)."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The main Function") {
                    BodyText(
                        "main is the entry point — the first function the operating system calls when " +
                        "your program starts. Every executable Rust program must have exactly one main."
                    )
                    BodyText(
                        "main takes no parameters and by default returns nothing. You can optionally " +
                        "make it return a Result for error propagation, but that is an advanced pattern."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "println! — A Macro, Not a Function") {
                    BodyText(
                        "The exclamation mark ! is the key detail: println! is a macro, not a regular " +
                        "function. Macros in Rust are invoked with ! after the name. They generate code " +
                        "at compile time and can accept a variable number of arguments with different " +
                        "types — something ordinary functions cannot do without generics."
                    )
                    CodeBlock(
                        "println!(\"Hello!\");          // print with newline\n" +
                        "print!(\"No newline here\");   // print without newline\n" +
                        "eprintln!(\"Error message\");  // print to stderr"
                    )
                    BodyText(
                        "If you write println without the !, the compiler will look for a function named " +
                        "println — which does not exist — and report an error."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "String Formatting") {
                    BodyText(
                        "{} is a placeholder. println! replaces each {} with the next argument in order:"
                    )
                    CodeBlock(
                        "let name = \"Alice\";\n" +
                        "let age  = 30;\n" +
                        "println!(\"Name: {}, Age: {}\", name, age);\n" +
                        "// prints: Name: Alice, Age: 30"
                    )
                    BodyText(
                        "Since Rust 1.58 you can capture variables directly by name inside {}:"
                    )
                    CodeBlock(
                        "println!(\"Name: {name}, Age: {age}\");\n" +
                        "// same output, cleaner syntax"
                    )
                    BodyText(
                        "{:?} is the debug format — works for most types and prints a developer-friendly " +
                        "representation. format! builds a String without printing it:"
                    )
                    CodeBlock(
                        "let s = format!(\"Hello, {}!\", name);\n" +
                        "// s is a String: \"Hello, Alice!\""
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
