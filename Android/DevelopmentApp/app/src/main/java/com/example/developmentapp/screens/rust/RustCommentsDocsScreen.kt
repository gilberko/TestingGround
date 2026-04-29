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
fun RustCommentsDocsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Rust — Comments and Documentation",
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
                SectionCard(title = "// — Single-Line Comments") {
                    BodyText(
                        "A double slash // starts a comment that runs to the end of the line. " +
                        "Everything after // on that line is ignored by the compiler. This is the " +
                        "most common comment style in Rust."
                    )
                    CodeBlock(
                        "// This entire line is a comment.\n\n" +
                        "let x = 5; // inline comment — explains the line\n\n" +
                        "// Multi-line thoughts are written as\n" +
                        "// consecutive single-line comments.\n" +
                        "let y = x + 1;"
                    )
                    BodyText(
                        "Single-line comments are idiomatic for almost everything. The // style " +
                        "is easy to toggle in any editor and is what rustfmt and most Rust code " +
                        "uses in practice."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "/* */ — Block Comments") {
                    BodyText(
                        "A block comment starts with /* and ends with */. Everything between those " +
                        "delimiters is ignored, including newlines. Block comments can appear " +
                        "anywhere in the source — even in the middle of an expression."
                    )
                    CodeBlock(
                        "/* This is a block comment.\n" +
                        "   It can span multiple lines. */\n\n" +
                        "let z = /* inline block comment */ 42;\n\n" +
                        "/*\n" +
                        " * Some codebases use the leading-asterisk style\n" +
                        " * for multi-line blocks — purely a convention.\n" +
                        " */"
                    )
                    BodyText(
                        "Block comments are less common in idiomatic Rust than in C or Java, but " +
                        "they are occasionally useful for temporarily commenting out a large region " +
                        "of code."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Nested Block Comments") {
                    BodyText(
                        "Rust block comments can be nested. Each /* must be paired with its own " +
                        "matching */. This is a deliberate difference from C and C++, where block " +
                        "comments cannot nest — the first */ encountered closes the comment, which " +
                        "makes it impossible to comment out code that already contains /* */ blocks."
                    )
                    CodeBlock(
                        "/* outer comment\n" +
                        "    /* inner comment — valid in Rust, error in C */\n" +
                        "   still inside the outer comment\n" +
                        "*/"
                    )
                    BodyText(
                        "Because of nesting support, you can safely wrap any region of Rust code " +
                        "in /* ... */ to disable it, even if that region already contains block " +
                        "comments. The compiler tracks the nesting depth and only closes the outer " +
                        "comment when the matching */ is found."
                    )
                    CodeBlock(
                        "/*\n" +
                        "fn old_function() {\n" +
                        "    let x = /* some calculation */ 5 + 3;\n" +
                        "    // ...\n" +
                        "}\n" +
                        "*/ // <-- closes the outer /*, not the inner one"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "/// — Outer Doc Comments") {
                    BodyText(
                        "A triple-slash /// is an outer doc comment. It documents the item " +
                        "(function, struct, enum, constant, etc.) that immediately follows it. " +
                        "The compiler collects these comments and rustdoc turns them into HTML " +
                        "documentation."
                    )
                    BodyText(
                        "Doc comment content is parsed as Markdown. You can use bold, code " +
                        "spans, headings, and lists. The most important conventional sections are " +
                        "# Examples, # Panics, # Errors, and # Safety."
                    )
                    CodeBlock(
                        "/// Adds two integers and returns the result.\n" +
                        "///\n" +
                        "/// # Examples\n" +
                        "///\n" +
                        "/// ```\n" +
                        "/// let sum = add(2, 3);\n" +
                        "/// assert_eq!(sum, 5);\n" +
                        "/// ```\n" +
                        "pub fn add(a: i32, b: i32) -> i32 {\n" +
                        "    a + b\n" +
                        "}"
                    )
                    BodyText(
                        "Code blocks inside doc comments (wrapped in ```) are also compiled and " +
                        "run as tests when you execute cargo test. This keeps documentation " +
                        "examples from going stale."
                    )
                    CodeBlock(
                        "/// Divides `a` by `b`.\n" +
                        "///\n" +
                        "/// # Panics\n" +
                        "///\n" +
                        "/// Panics if `b` is zero.\n" +
                        "///\n" +
                        "/// # Examples\n" +
                        "///\n" +
                        "/// ```\n" +
                        "/// assert_eq!(divide(10, 2), 5);\n" +
                        "/// ```\n" +
                        "pub fn divide(a: i32, b: i32) -> i32 {\n" +
                        "    if b == 0 { panic!(\"division by zero\"); }\n" +
                        "    a / b\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "//! — Inner Doc Comments") {
                    BodyText(
                        "A //! comment documents the item that contains it, rather than the item " +
                        "that follows it. It is used at the top of a file or inside a mod block to " +
                        "describe the module itself. Think of it as the module's own introduction."
                    )
                    CodeBlock(
                        "// src/math.rs\n\n" +
                        "//! Utility functions for integer arithmetic.\n" +
                        "//!\n" +
                        "//! All functions in this module operate on `i32` values\n" +
                        "//! and panic on overflow in debug builds.\n\n" +
                        "/// Adds two numbers.\n" +
                        "pub fn add(a: i32, b: i32) -> i32 { a + b }"
                    )
                    BodyText(
                        "At the top of lib.rs (the crate root), //! comments become the crate's " +
                        "top-level documentation — the first page a reader sees in the generated " +
                        "HTML."
                    )
                    CodeBlock(
                        "// src/lib.rs\n\n" +
                        "//! # My Math Crate\n" +
                        "//!\n" +
                        "//! A small library of arithmetic utilities.\n" +
                        "//!\n" +
                        "//! ## Quick Start\n" +
                        "//!\n" +
                        "//! ```\n" +
                        "//! use my_math::add;\n" +
                        "//! assert_eq!(add(1, 2), 3);\n" +
                        "//! ```\n\n" +
                        "pub mod math;"
                    )
                    BodyText(
                        "You can also use the block form /*! ... */ as an inner doc comment, but " +
                        "//! is the idiomatic style."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "cargo doc — Generating Documentation") {
                    BodyText(
                        "The cargo doc command reads all /// and //! comments in your crate (and " +
                        "its dependencies) and generates a self-contained HTML website."
                    )
                    CodeBlock(
                        "cargo doc           # build docs into target/doc/\n" +
                        "cargo doc --open    # build and open in the browser\n" +
                        "cargo doc --no-deps # skip dependency docs (faster)"
                    )
                    BodyText(
                        "Only pub items are included in the generated docs by default. Private " +
                        "helpers are excluded. Adding --document-private-items includes them, " +
                        "which is useful when writing internal documentation for your team."
                    )
                    CodeBlock(
                        "cargo doc --document-private-items --open"
                    )
                    BodyText(
                        "The standard library at doc.rust-lang.org is itself generated by rustdoc " +
                        "from the same /// comments you write in your own code — the toolchain is " +
                        "the same."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
