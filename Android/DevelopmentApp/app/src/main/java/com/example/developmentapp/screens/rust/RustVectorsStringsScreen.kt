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
fun RustVectorsStringsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Rust — Vectors and Strings",
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
                SectionCard(title = "Vec<T> — The Growable Array") {
                    BodyText(
                        "Vec<T> is Rust's growable, heap-allocated array — equivalent to " +
                        "std::vector<T> in C++. It owns its elements. When a Vec goes out of " +
                        "scope, all its elements are dropped automatically."
                    )
                    CodeBlock(
                        "// Create empty, then add elements:\n" +
                        "let mut v: Vec<i32> = Vec::new();\n" +
                        "v.push(10);\n" +
                        "v.push(20);\n" +
                        "v.push(30);\n\n" +
                        "// Or use the vec! macro for an initial list:\n" +
                        "let mut nums = vec![1, 2, 3, 4, 5];"
                    )
                    BodyText(
                        "Vec manages its own capacity internally. When it runs out of space, " +
                        "it allocates a larger buffer and moves all elements — similar to C++ " +
                        "vector's amortised O(1) push_back."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Accessing and Modifying a Vec") {
                    BodyText(
                        "Index with [] (panics on out-of-bounds) or use .get() for a safe " +
                        "Option<&T> that returns None instead of panicking. Elements are " +
                        "zero-indexed."
                    )
                    CodeBlock(
                        "let mut v = vec![10, 20, 30];\n\n" +
                        "// Access:\n" +
                        "println!(\"{}\", v[0]);           // 10\n" +
                        "println!(\"{:?}\", v.get(1));     // Some(20)\n" +
                        "println!(\"{:?}\", v.get(99));    // None — no panic\n\n" +
                        "// Number of elements:\n" +
                        "println!(\"{}\", v.len());        // 3\n\n" +
                        "// Modify an element:\n" +
                        "v[1] = 99;\n" +
                        "println!(\"{:?}\", v);            // [10, 99, 30]\n\n" +
                        "// Remove and return the last element:\n" +
                        "let last = v.pop();              // Some(30); v is [10, 99]\n\n" +
                        "// Remove by index (remaining elements shift left):\n" +
                        "v.remove(0);                     // removes 10; v is [99]\n\n" +
                        "// Clear all elements:\n" +
                        "v.clear();                       // v is now empty"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Iterating Over a Vec") {
                    BodyText(
                        "Three iteration styles depending on what you need — read, mutate, " +
                        "or consume the elements."
                    )
                    CodeBlock(
                        "let v = vec![1, 2, 3];\n\n" +
                        "// Read-only: borrows each element\n" +
                        "for x in &v {\n" +
                        "    println!(\"{x}\");\n" +
                        "}  // v is still usable after this\n\n" +
                        "// Mutable: borrows each element mutably\n" +
                        "let mut v = vec![1, 2, 3];\n" +
                        "for x in &mut v {\n" +
                        "    *x *= 2;  // dereference to assign\n" +
                        "}\n" +
                        "println!(\"{:?}\", v);  // [2, 4, 6]\n\n" +
                        "// Consuming: moves each element out of the Vec\n" +
                        "for x in v {           // v is moved — cannot use v after this\n" +
                        "    println!(\"{x}\");\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "String Literal — A Prelude to Strings in Rust") {
                    BodyText(
                        "In Rust you can write let s = \"some string\"; — we haven't told Rust " +
                        "what type s is, but \"some string\" is a string literal. String literals " +
                        "do not live on the heap."
                    )
                    BodyText(
                        "Instead, the literal is baked directly into the compiled binary in a " +
                        "static, read-only memory section. It will be there for the entire " +
                        "lifetime of the program."
                    )
                    CodeBlock(
                        "let s = \"some string\";\n" +
                        "// s is &str — a slice (pointer + length)\n" +
                        "// \"some string\" lives in read-only binary memory"
                    )
                    BodyText(
                        "s is of type &str — a string slice. A slice is just a reference: a " +
                        "pointer and a length. It does not own the data it points to; it simply " +
                        "borrows a view of it. This is why you can freely copy &str values — " +
                        "you're just copying the pointer and length, never the underlying bytes."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "String — The Owned String Type") {
                    BodyText(
                        "String is Rust's growable, heap-allocated, UTF-8 encoded string type. " +
                        "It owns its data. &str is a string slice — a borrowed, read-only view " +
                        "into UTF-8 data that can point into a String or into a string literal " +
                        "baked into the binary."
                    )
                    CodeBlock(
                        "// Creating a String:\n" +
                        "let s1 = String::from(\"hello\");\n" +
                        "let s2 = \"world\".to_string();    // same result\n" +
                        "let s3 = String::new();           // empty string\n\n" +
                        "// Appending:\n" +
                        "let mut s = String::from(\"hello\");\n" +
                        "s.push(' ');            // append a single char\n" +
                        "s.push_str(\"world\");  // append a &str\n" +
                        "println!(\"{s}\");       // hello world\n\n" +
                        "// Concatenation with + (s1 is moved; s2 is borrowed):\n" +
                        "let s1 = String::from(\"hello \");\n" +
                        "let s2 = String::from(\"world\");\n" +
                        "let s3 = s1 + &s2;\n" +
                        "println!(\"{s3}\");  // hello world\n\n" +
                        "// format! avoids moves — preferred for multi-string joins:\n" +
                        "let result = format!(\"{s2} there\");"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "String Slices (&str) — A View Into String Data") {
                    BodyText(
                        "A string slice &str is a (pointer, length) pair that references a " +
                        "range of UTF-8 bytes inside some existing String or string literal. " +
                        "It is a view — it does not own the data. Slicing a String gives you " +
                        "a &str that refers to a substring of that String, without copying."
                    )
                    CodeBlock(
                        "let s = String::from(\"hello world\");\n\n" +
                        "let hello = &s[0..5];    // &str pointing to \"hello\" inside s\n" +
                        "let world = &s[6..11];   // &str pointing to \"world\" inside s\n" +
                        "println!(\"{hello}\");    // hello\n" +
                        "println!(\"{world}\");    // world\n\n" +
                        "// Shorthand forms:\n" +
                        "// &s[..5]  same as &s[0..5]\n" +
                        "// &s[6..]  from index 6 to the end\n" +
                        "// &s[..]   slice of the entire string"
                    )
                    BodyText(
                        "A function that takes &str accepts both string literals and references " +
                        "to String values — Rust auto-derefs &String to &str:"
                    )
                    CodeBlock(
                        "fn first_word(s: &str) -> &str {\n" +
                        "    match s.find(' ') {\n" +
                        "        Some(i) => &s[..i],\n" +
                        "        None    => s,\n" +
                        "    }\n" +
                        "}\n\n" +
                        "let sentence = String::from(\"hello world\");\n" +
                        "let word = first_word(&sentence);  // &String coerces to &str\n" +
                        "println!(\"{word}\");  // hello\n\n" +
                        "let word2 = first_word(\"rust is cool\");  // literal &str\n" +
                        "println!(\"{word2}\");  // rust"
                    )
                    BodyText(
                        "Important: slice indices must fall on UTF-8 character boundaries. " +
                        "Slicing in the middle of a multi-byte character (e.g. an emoji or " +
                        "accented letter) panics at runtime."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "String vs &str — When to Use Which") {
                    BodyText(
                        "Use String when you need to own the data, grow it, or return it from " +
                        "a function without a lifetime attached to an input."
                    )
                    BodyText(
                        "Use &str for function parameters when you only need to read — it " +
                        "accepts both string literals and &my_string without requiring ownership, " +
                        "and avoids unnecessary allocations."
                    )
                    BodyText(
                        "String is essentially a Vec<u8> that is guaranteed to hold valid UTF-8. " +
                        "The same mutation operations apply: push(char), push_str(&str), pop(), " +
                        "remove(byte_index), insert(byte_index, char), len(), is_empty(), " +
                        "clear(). Searching and inspection: contains(), starts_with(), " +
                        "ends_with(), find(), trim(), to_lowercase(), to_uppercase(), split()."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
