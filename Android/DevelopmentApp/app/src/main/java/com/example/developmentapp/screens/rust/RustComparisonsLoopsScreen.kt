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
fun RustComparisonsLoopsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Rust — Comparisons and Loops",
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
                SectionCard(title = "Comparison Operators") {
                    BodyText(
                        "Rust has the same comparison operators as C, Java, and Go. All of them return " +
                        "a bool:"
                    )
                    CodeBlock(
                        "let a = 5;\n" +
                        "println!(\"{}\", a <  10);  // true  — less than\n" +
                        "println!(\"{}\", a <= 5);   // true  — less than or equal\n" +
                        "println!(\"{}\", a == 5);   // true  — equal\n" +
                        "println!(\"{}\", a != 3);   // true  — not equal\n" +
                        "println!(\"{}\", a >  4);   // true  — greater than\n" +
                        "println!(\"{}\", a >= 5);   // true  — greater than or equal"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Logical Operators") {
                    BodyText(
                        "&& (and), || (or), ! (not). Both && and || short-circuit: && stops evaluating " +
                        "at the first false; || stops at the first true. Note: Rust does not have and, " +
                        "or, or not keywords like Python does."
                    )
                    CodeBlock(
                        "let x = 5;\n" +
                        "let flag = true;\n\n" +
                        "if x > 0 && x < 10 {\n" +
                        "    println!(\"single digit positive\");\n" +
                        "}\n" +
                        "if x < 0 || x > 100 {\n" +
                        "    println!(\"out of normal range\");\n" +
                        "}\n" +
                        "if !flag {\n" +
                        "    println!(\"flag is false\");\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The loop Statement") {
                    BodyText(
                        "loop runs a block infinitely until a break statement is reached. It is Rust's " +
                        "equivalent of while(true). Unlike most languages, loop can return a value — " +
                        "the value after break is returned from the loop expression:"
                    )
                    CodeBlock(
                        "// Basic infinite loop with break\n" +
                        "let mut count = 0;\n" +
                        "loop {\n" +
                        "    count += 1;\n" +
                        "    if count == 5 { break; }\n" +
                        "}\n\n" +
                        "// loop as an expression — returns a value\n" +
                        "let result = loop {\n" +
                        "    count += 1;\n" +
                        "    if count == 10 { break count * 2; }\n" +
                        "};\n" +
                        "println!(\"{}\", result);  // 20"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The while Loop") {
                    BodyText(
                        "while evaluates a condition before each iteration and loops as long as it is " +
                        "true. No parentheses are needed around the condition — they would be a warning."
                    )
                    CodeBlock(
                        "let mut n = 0;\n" +
                        "while n < 5 {\n" +
                        "    println!(\"{}\", n);\n" +
                        "    n += 1;\n" +
                        "}  // prints 0, 1, 2, 3, 4"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The for Loop and Ranges") {
                    BodyText(
                        "for iterates over any iterator — ranges, arrays, vectors, and more. The loop " +
                        "variable (i in the example below) is declared by the for statement itself. " +
                        "You do not need a separate let i before the loop."
                    )
                    CodeBlock(
                        "for i in 0..5 {\n" +
                        "    println!(\"{}\", i);  // 0, 1, 2, 3, 4\n" +
                        "}"
                    )
                    BodyText(
                        "0..5 is a half-open range: it includes 0, 1, 2, 3, 4 and excludes 5. " +
                        "0..=5 is an inclusive range: it includes 0 through 5."
                    )
                    CodeBlock(
                        "for i in 0..=5 {\n" +
                        "    print!(\"{} \", i);  // 0 1 2 3 4 5\n" +
                        "}\n\n" +
                        "// Iterate in reverse\n" +
                        "for i in (0..5).rev() {\n" +
                        "    print!(\"{} \", i);  // 4 3 2 1 0\n" +
                        "}"
                    )
                    BodyText(
                        "Ranges are just values that implement the Iterator trait. You can also " +
                        "iterate over arrays, slices, and collections the same way: for item in &vec { ... }."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "break and continue") {
                    BodyText(
                        "break exits the innermost loop immediately. continue skips the rest of the " +
                        "current iteration and jumps to the next one."
                    )
                    BodyText(
                        "Labeled loops allow break and continue to target an outer loop. A label is an " +
                        "identifier prefixed with a tick (') placed before the loop keyword:"
                    )
                    CodeBlock(
                        "'outer: for i in 0..3 {\n" +
                        "    for j in 0..3 {\n" +
                        "        if i == 1 && j == 1 {\n" +
                        "            break 'outer;  // exits the outer for loop entirely\n" +
                        "        }\n" +
                        "        println!(\"{} {}\", i, j);\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
