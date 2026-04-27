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
fun RustAboutScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Rust — About Rust",
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
                        "Rust was created by Graydon Hoare as a personal project around 2006. Mozilla " +
                        "Research adopted it in 2009 and sponsored its development. Rust 1.0 — the first " +
                        "stable release — shipped in May 2015. The Rust Foundation was formed in 2021 to " +
                        "provide independent stewardship of the language."
                    )
                    BodyText(
                        "The name comes from a group of fungi (rust fungi) — fitting for a language that " +
                        "was designed to be resilient and to prevent things from \"rotting\" in memory. " +
                        "Rust is a systems programming language intended to replace C and C++ in memory-" +
                        "sensitive work — operating systems, embedded firmware, game engines, browsers, " +
                        "and network services."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Why Rust Is Considered Safer") {
                    BodyText(
                        "Rust's safety guarantees come from a set of rules enforced at compile time — " +
                        "not at runtime. There is no safety tax paid when the program runs."
                    )
                    BodyText(
                        "Ownership: every value in Rust has exactly one owner. When the owner goes out " +
                        "of scope, the value is automatically freed. No manual free(), no garbage " +
                        "collector needed."
                    )
                    BodyText(
                        "Borrow checker: Rust tracks every reference at compile time and ensures that " +
                        "references never outlive the data they point to. Dangling pointers are " +
                        "impossible in safe Rust — the code simply won't compile."
                    )
                    BodyText(
                        "No null pointers: Rust has no null. Absent values are represented as " +
                        "Option<T> — either Some(value) or None. You cannot accidentally dereference " +
                        "a null pointer."
                    )
                    BodyText(
                        "No data races: the borrow checker enforces that either many threads may read " +
                        "a value OR one thread may write it — never both simultaneously. Data races are " +
                        "a compile error."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Compiled Language") {
                    BodyText(
                        "Rust is compiled to native machine code using the LLVM compiler backend — the " +
                        "same backend used by Clang/C++. The Rust compiler is rustc. The official build " +
                        "tool and package manager is cargo."
                    )
                    CodeBlock(
                        "cargo new hello_world   # create a new project\n" +
                        "cargo build             # compile (produces target/debug/hello_world)\n" +
                        "cargo run               # compile + run in one step\n" +
                        "cargo build --release   # optimised release binary"
                    )
                    BodyText(
                        "There is no interpreter and no virtual machine. The output is a native binary " +
                        "that runs directly on the hardware. Performance is comparable to C and C++. " +
                        "Rust programs have no garbage collector and no runtime overhead."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Memory Without a Garbage Collector") {
                    BodyText(
                        "Most safe languages (Java, Go, Python, C#) use a garbage collector — a runtime " +
                        "process that periodically scans memory and frees objects that are no longer " +
                        "reachable. This causes unpredictable pauses (stop-the-world GC) and adds " +
                        "runtime overhead."
                    )
                    BodyText(
                        "Rust achieves memory safety without a GC. Instead, the ownership system " +
                        "guarantees that every allocation is freed exactly once, automatically, the " +
                        "moment the owner goes out of scope (RAII — Resource Acquisition Is " +
                        "Initialization). Deallocation is deterministic and happens at a known point " +
                        "in the code."
                    )
                    BodyText(
                        "Lifetimes let the compiler track how long references are valid and reject code " +
                        "where a reference could outlive its data — all at compile time, with no runtime " +
                        "bookkeeping. The result is memory-safe code that runs as fast as hand-written C."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
