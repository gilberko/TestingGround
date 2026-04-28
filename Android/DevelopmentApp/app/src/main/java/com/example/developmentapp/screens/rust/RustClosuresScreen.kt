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
fun RustClosuresScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Rust — Closures",
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
                SectionCard(title = "What Is a Closure") {
                    BodyText(
                        "A closure is an anonymous function that can capture variables from its " +
                        "surrounding scope. Closures are written with pipes around the parameter " +
                        "list: |params| expression. Types are usually inferred."
                    )
                    CodeBlock(
                        "let add = |a, b| a + b;\n" +
                        "println!(\"{}\", add(2, 3));   // 5\n\n" +
                        "// Multi-line closure with a block body:\n" +
                        "let f = |x| {\n" +
                        "    let y = x * 2;\n" +
                        "    y + 1\n" +
                        "};\n" +
                        "println!(\"{}\", f(4));   // 9"
                    )
                    BodyText(
                        "Unlike a regular function, a closure has access to the variables in the " +
                        "scope where it is defined. How it accesses them — by borrow, mutable " +
                        "borrow, or move — depends on what the closure body does with them."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Capturing by Immutable Borrow") {
                    BodyText(
                        "If the closure only reads a captured variable, Rust borrows it " +
                        "immutably. The original variable remains accessible after the closure " +
                        "is called because the borrow is shared and the closure does not take " +
                        "ownership."
                    )
                    CodeBlock(
                        "let s = String::from(\"hello\");\n" +
                        "let f = || println!(\"{}\", s);   // borrows s immutably\n" +
                        "f();         // hello\n" +
                        "f();         // hello — can call multiple times\n" +
                        "println!(\"{}\", s);   // s still accessible — borrow is shared"
                    )
                    BodyText(
                        "This is the most common capture mode. The closure and the outer code " +
                        "can both use s at the same time as long as no one needs to mutate it."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Mutable Capture") {
                    BodyText(
                        "If the closure modifies a captured variable, Rust takes a mutable " +
                        "borrow of it. Two things are required: the captured variable must be " +
                        "declared mut, and the closure variable itself must also be mut (because " +
                        "calling it changes the closure's internal state)."
                    )
                    CodeBlock(
                        "let mut s = String::from(\"hello\");\n" +
                        "let mut f = || { s.push_str(\" world\"); };\n" +
                        "// println!(\"{}\", s);  // compile error: s is mutably borrowed by f\n" +
                        "f();                     // mutates s\n" +
                        "// f's mutable borrow of s ends here (last use of f)\n" +
                        "println!(\"{}\", s);   // hello world — s is accessible again"
                    )
                    BodyText(
                        "While the closure f exists and holds a mutable borrow of s, you cannot " +
                        "read or write s directly. The borrow ends at the last use of f, after " +
                        "which s is accessible again."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Move Closures") {
                    BodyText(
                        "The move keyword forces the closure to take ownership of every captured " +
                        "variable rather than borrowing. After the closure is created, the " +
                        "original variable is no longer accessible — it was moved into the closure."
                    )
                    CodeBlock(
                        "let s = String::from(\"hello\");\n" +
                        "let f = move || println!(\"{}\", s);   // s is moved into f\n" +
                        "// println!(\"{}\", s);  // compile error: s was moved\n" +
                        "f();   // hello\n" +
                        "f();   // hello — closure owns s, can call multiple times"
                    )
                    BodyText(
                        "Move closures are essential when passing a closure to a new thread, " +
                        "because the thread must own all its data — it cannot hold a borrow " +
                        "that might outlive the caller's stack frame."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Fn Trait Family") {
                    BodyText(
                        "Closures implement one or more of three traits that describe how they " +
                        "may be called:"
                    )
                    BodyText(
                        "Fn — borrows captured variables immutably. Can be called any number of " +
                        "times and from multiple places simultaneously."
                    )
                    BodyText(
                        "FnMut — borrows captured variables mutably. Can be called any number of " +
                        "times, but only one call can be active at once (exclusive borrow)."
                    )
                    BodyText(
                        "FnOnce — takes ownership of captured variables. Can only be called once " +
                        "because calling it consumes the captured values."
                    )
                    BodyText(
                        "The hierarchy is: every Fn is also FnMut, and every FnMut is also " +
                        "FnOnce. Function signatures use these as trait bounds:"
                    )
                    CodeBlock(
                        "fn apply<F: Fn(i32) -> i32>(f: F, x: i32) -> i32 {\n" +
                        "    f(x)   // immutable borrow — can call multiple times\n" +
                        "}\n\n" +
                        "fn apply_mut<F: FnMut(i32) -> i32>(mut f: F, x: i32) -> i32 {\n" +
                        "    f(x)   // mutable borrow — needs mut binding\n" +
                        "}\n\n" +
                        "fn apply_once<F: FnOnce(i32) -> i32>(f: F, x: i32) -> i32 {\n" +
                        "    f(x)   // consumes f — can only be called once\n" +
                        "}"
                    )
                    BodyText(
                        "When writing a function that accepts a closure, prefer Fn for maximum " +
                        "flexibility. Use FnMut if you need mutation, FnOnce if you need " +
                        "ownership transfer."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
