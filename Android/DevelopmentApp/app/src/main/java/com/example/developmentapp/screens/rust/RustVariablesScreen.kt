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
fun RustVariablesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Rust — Variables",
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
                SectionCard(title = "The let Keyword") {
                    BodyText(
                        "let declares a variable. The type is usually inferred from the value — you do " +
                        "not need to write it explicitly most of the time:"
                    )
                    CodeBlock(
                        "let x = 5;        // i32 inferred\n" +
                        "let y = 3.14;     // f64 inferred\n" +
                        "let flag = true;  // bool inferred\n" +
                        "let letter = 'A'; // char inferred"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Immutable by Default") {
                    BodyText(
                        "Yes — variables in Rust are immutable by default. Once assigned, you cannot " +
                        "change their value. This is intentional: the compiler uses it to catch " +
                        "accidental mutation and to enable optimisations."
                    )
                    CodeBlock(
                        "let x = 5;\n" +
                        "x = 6;  // ERROR: cannot assign twice to immutable variable `x`"
                    )
                    BodyText(
                        "This is different from C/C++/Java/Go where variables are mutable by default. " +
                        "In Rust you must explicitly opt in to mutability."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The mut Keyword") {
                    BodyText(
                        "Add mut after let to make a variable mutable:"
                    )
                    CodeBlock(
                        "let mut x = 5;\n" +
                        "x = 6;   // OK\n" +
                        "x += 1;  // OK — x is now 7"
                    )
                    BodyText(
                        "mut applies to the binding itself. If you have a mutable reference, that is " +
                        "written &mut T. You cannot make an immutable variable mutable after the fact — " +
                        "you must decide at the declaration."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Integer Types") {
                    BodyText(
                        "Signed integers (can be negative): i8, i16, i32, i64, i128, isize. " +
                        "Unsigned integers (non-negative only): u8, u16, u32, u64, u128, usize. " +
                        "The number in the name is the bit width."
                    )
                    CodeBlock(
                        "// Ranges\n" +
                        "// i8:    -128 .. 127\n" +
                        "// u8:       0 .. 255\n" +
                        "// i32:  -2_147_483_648 .. 2_147_483_647\n" +
                        "// u32:              0 .. 4_294_967_295\n" +
                        "// i64:  -9_223_372_036_854_775_808 .. 9_223_372_036_854_775_807\n" +
                        "// u64:              0 .. 18_446_744_073_709_551_615\n\n" +
                        "let a: i32  = -100;\n" +
                        "let b: u64  = 1_000_000_000;\n" +
                        "let c: i8   = 127;"
                    )
                    BodyText(
                        "The default integer type is i32. isize and usize are pointer-sized — 64 bits " +
                        "on 64-bit systems, 32 bits on 32-bit systems. Use usize for array indices and " +
                        "collection lengths."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Floating-Point Types") {
                    BodyText(
                        "f32 is 32-bit single precision. f64 is 64-bit double precision. The default " +
                        "is f64 — it is more precise and on modern hardware equally fast."
                    )
                    CodeBlock(
                        "let x = 3.14;         // f64 inferred\n" +
                        "let y: f32 = 3.14;    // f32 explicit\n" +
                        "let z: f64 = 2.718_281_828;"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "bool and char") {
                    BodyText(
                        "bool has exactly two values: true and false. char represents a single Unicode " +
                        "scalar value — it is always 4 bytes (32 bits), not 1 byte like in C."
                    )
                    CodeBlock(
                        "let flag: bool = true;\n" +
                        "let other      = false;   // bool inferred\n\n" +
                        "let c: char = 'A';\n" +
                        "let euro    = '€';         // Unicode — works fine\n" +
                        "let newline = '\\n';        // escape sequences also work"
                    )
                    BodyText(
                        "char uses single quotes. String literals use double quotes. They are different " +
                        "types — a char is not the same as a one-character string."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Type Annotations") {
                    BodyText(
                        "Add : Type after the variable name to specify the type explicitly. This is " +
                        "required when the compiler cannot infer the type, and useful for clarity:"
                    )
                    CodeBlock(
                        "let x: i64 = 1_000_000;  // explicit type\n" +
                        "let b: u8  = 200;\n\n" +
                        "// Suffix directly on a numeric literal:\n" +
                        "let n = 255u8;   // same as let n: u8 = 255\n" +
                        "let f = 1.5f32;  // same as let f: f32 = 1.5"
                    )
                    BodyText(
                        "Underscores in numeric literals are ignored by the compiler — they are just " +
                        "visual separators to improve readability (1_000_000 is the same as 1000000)."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Constants") {
                    BodyText(
                        "const declares a compile-time constant. A type annotation is required. " +
                        "Constants can be declared at module scope (outside any function) and are " +
                        "inlined by the compiler wherever they are used:"
                    )
                    CodeBlock(
                        "const MAX_SCORE: u32 = 100_000;\n" +
                        "const PI: f64 = 3.141_592_653_589_793;\n\n" +
                        "fn main() {\n" +
                        "    println!(\"Max score: {}\", MAX_SCORE);\n" +
                        "}"
                    )
                    BodyText(
                        "Unlike let, const is never mutable (there is no const mut). The value must be " +
                        "a constant expression — it cannot be the result of a function call computed at " +
                        "runtime. The naming convention is SCREAMING_SNAKE_CASE."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
