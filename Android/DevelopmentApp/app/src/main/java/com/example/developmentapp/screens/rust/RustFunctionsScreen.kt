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
fun RustFunctionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Rust — Functions",
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
                SectionCard(title = "Defining a Function") {
                    BodyText(
                        "Use the fn keyword, followed by the name, parentheses for parameters, and " +
                        "curly braces for the body. The convention is snake_case for function names."
                    )
                    CodeBlock(
                        "fn greet() {\n" +
                        "    println!(\"Hello!\");\n" +
                        "}"
                    )
                    BodyText(
                        "Functions can be defined in any order in the file — Rust does not require a " +
                        "function to be declared before it is called (unlike C)."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Parameters") {
                    BodyText(
                        "Each parameter must have an explicit type annotation. Rust does not infer " +
                        "parameter types — you must always write them. Multiple parameters are " +
                        "separated by commas."
                    )
                    CodeBlock(
                        "fn add(x: i32, y: i32) {\n" +
                        "    println!(\"{}\", x + y);\n" +
                        "}\n\n" +
                        "fn describe(name: &str, age: u32) {\n" +
                        "    println!(\"{} is {} years old\", name, age);\n" +
                        "}"
                    )
                    BodyText(
                        "&str is the type for string slices — the most common way to pass text to a " +
                        "function. (String ownership and borrowing are covered in a later section.)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Declaring a Return Type") {
                    BodyText(
                        "Declare the return type with -> after the parameter list. If you intend to " +
                        "return a value, you must declare the type — you cannot skip it."
                    )
                    CodeBlock(
                        "fn add(x: i32, y: i32) -> i32 {\n" +
                        "    x + y\n" +
                        "}"
                    )
                    BodyText(
                        "A function can only have a single return type. To return multiple values, " +
                        "return a tuple:"
                    )
                    CodeBlock(
                        "fn min_max(a: i32, b: i32) -> (i32, i32) {\n" +
                        "    if a < b { (a, b) } else { (b, a) }\n" +
                        "}\n\n" +
                        "let (lo, hi) = min_max(7, 3);  // lo=3, hi=7"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The Last Expression Is the Return Value") {
                    BodyText(
                        "In Rust, the last expression in a function body — written without a semicolon " +
                        "— is implicitly returned. This is the idiomatic style:"
                    )
                    CodeBlock(
                        "fn square(n: i32) -> i32 {\n" +
                        "    n * n   // no semicolon — this value is returned\n" +
                        "}"
                    )
                    BodyText(
                        "Adding a semicolon turns the expression into a statement, which returns () " +
                        "(the unit type — nothing). If the function is declared to return i32 and the " +
                        "last line ends with a semicolon, the compiler will report a type mismatch error."
                    )
                    CodeBlock(
                        "fn broken(n: i32) -> i32 {\n" +
                        "    n * n;  // WRONG: semicolon makes this return (), not i32\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The return Keyword") {
                    BodyText(
                        "return exits the function early from the middle of the body. It requires a " +
                        "semicolon after it. Using return at the very end of a function is valid, but " +
                        "not idiomatic — the implicit last-expression style is preferred."
                    )
                    CodeBlock(
                        "fn abs_value(n: i32) -> i32 {\n" +
                        "    if n < 0 { return -n; }  // early return\n" +
                        "    n                          // implicit return at end\n" +
                        "}\n\n" +
                        "fn classify(n: i32) -> &'static str {\n" +
                        "    if n < 0  { return \"negative\"; }\n" +
                        "    if n == 0 { return \"zero\"; }\n" +
                        "    \"positive\"  // implicit return\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "No Return Value — The Unit Type") {
                    BodyText(
                        "If a function does not return a meaningful value, its return type is () — " +
                        "called the unit type. Omitting the -> entirely is the same as writing -> ():"
                    )
                    CodeBlock(
                        "fn print_hello() {           // implicit -> ()\n" +
                        "    println!(\"Hello\");\n" +
                        "}\n\n" +
                        "fn print_hi() -> () {        // explicit, same thing\n" +
                        "    println!(\"Hi\");\n" +
                        "}"
                    )
                    BodyText(
                        "Every expression in Rust has a type. A block that ends with a statement " +
                        "(semicolon) produces (). A function whose body produces () and has no -> " +
                        "declaration is said to return unit — it is not void in the C sense, but it " +
                        "behaves the same way in practice."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Calling a Function") {
                    BodyText(
                        "Call a function with its name followed by parentheses and the arguments. " +
                        "The return value can be ignored, stored in a variable, or used inline:"
                    )
                    CodeBlock(
                        "greet();                        // no return value — just call it\n" +
                        "let sum = add(3, 5);            // store the return value\n" +
                        "println!(\"{}\", square(4));     // use the return value directly\n\n" +
                        "let (lo, hi) = min_max(7, 3);   // destructure a tuple return"
                    )
                    BodyText(
                        "Arguments must match the declared parameter types exactly. Rust does not " +
                        "perform implicit numeric conversions — passing an i64 where i32 is expected " +
                        "is a compile error. Unlike Go, Rust has no named arguments; all arguments " +
                        "are positional."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
