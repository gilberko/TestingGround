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
fun RustStdLibScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Rust — Standard Library",
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
                SectionCard(title = "What Is the Standard Library?") {
                    BodyText(
                        "Rust's standard library (std) is available in every project by default " +
                        "(unless you opt out with #![no_std]). It provides the building blocks you " +
                        "need — collections, I/O, threading, networking, time — without reaching " +
                        "for external crates."
                    )
                    CodeBlock(
                        "use std::collections::HashMap;\n" +
                        "use std::fs;\n" +
                        "use std::io;"
                    )
                    BodyText(
                        "The standard library is organised into modules, each focused on a specific " +
                        "domain. The most commonly used ones are covered in the sections below."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "std::path — Filesystem Paths") {
                    BodyText(
                        "Path is a borrowed path slice (like &str). PathBuf is the owned, growable " +
                        "version (like String). Use them instead of raw strings to stay " +
                        "platform-independent — they handle separators correctly on all operating systems."
                    )
                    CodeBlock(
                        "use std::path::{Path, PathBuf};\n\n" +
                        "let p = Path::new(\"/home/user/file.txt\");\n" +
                        "println!(\"{:?}\", p.extension()); // Some(\"txt\")\n\n" +
                        "let mut buf = PathBuf::from(\"/home/user\");\n" +
                        "buf.push(\"documents\");\n" +
                        "buf.push(\"notes.txt\");\n" +
                        "println!(\"{}\", buf.display()); // /home/user/documents/notes.txt"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "std::fs — Filesystem Operations") {
                    BodyText(
                        "std::fs provides functions for reading, writing, and managing files and directories."
                    )
                    CodeBlock(
                        "use std::fs;\n\n" +
                        "// Read entire file into a String\n" +
                        "let text = fs::read_to_string(\"hello.txt\").unwrap();\n\n" +
                        "// Write a String to a file (creates or overwrites)\n" +
                        "fs::write(\"output.txt\", \"hello world\").unwrap();\n\n" +
                        "// List directory entries\n" +
                        "for entry in fs::read_dir(\".\").unwrap() {\n" +
                        "    let entry = entry.unwrap();\n" +
                        "    println!(\"{:?}\", entry.file_name());\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "std::io — Input and Output") {
                    BodyText(
                        "std::io provides traits (Read, Write) and types for streaming I/O. " +
                        "BufReader wraps any Read to add line-by-line buffering; stdin/stdout give " +
                        "access to the console."
                    )
                    CodeBlock(
                        "use std::io::{self, BufRead, Write};\n\n" +
                        "// Read a line from stdin\n" +
                        "let stdin = io::stdin();\n" +
                        "let mut line = String::new();\n" +
                        "stdin.lock().read_line(&mut line).unwrap();\n" +
                        "println!(\"You typed: {}\", line.trim());\n\n" +
                        "// Flush stdout explicitly\n" +
                        "print!(\"Enter value: \");\n" +
                        "io::stdout().flush().unwrap();"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "std::net — Networking") {
                    BodyText(
                        "std::net offers synchronous (blocking) TCP and UDP sockets. For async " +
                        "networking you would reach for a crate like tokio, but std::net is " +
                        "sufficient for simple server/client programs."
                    )
                    CodeBlock(
                        "use std::net::TcpListener;\n" +
                        "use std::io::{Read, Write};\n\n" +
                        "// Simple echo server (one connection)\n" +
                        "let listener = TcpListener::bind(\"127.0.0.1:8080\").unwrap();\n" +
                        "let (mut stream, addr) = listener.accept().unwrap();\n" +
                        "println!(\"Connection from {}\", addr);\n\n" +
                        "let mut buf = [0u8; 512];\n" +
                        "let n = stream.read(&mut buf).unwrap();\n" +
                        "stream.write_all(&buf[..n]).unwrap(); // echo back"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "std::time — Time and Duration") {
                    BodyText(
                        "Instant measures elapsed time (monotonic, suitable for benchmarks). " +
                        "Duration represents a span of time. SystemTime measures wall-clock time " +
                        "(can go backwards — use for timestamps, not benchmarks)."
                    )
                    CodeBlock(
                        "use std::time::{Duration, Instant};\n\n" +
                        "let start = Instant::now();\n" +
                        "// ... do work ...\n" +
                        "let elapsed: Duration = start.elapsed();\n" +
                        "println!(\"Took {:?}\", elapsed); // e.g. Took 1.234ms\n\n" +
                        "let one_sec = Duration::from_secs(1);\n" +
                        "let half    = Duration::from_millis(500);\n" +
                        "println!(\"{:?}\", one_sec + half); // 1.5s"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "std::collections — HashMap and HashSet") {
                    BodyText(
                        "The two most-used collections beyond Vec. HashMap<K, V> maps keys to " +
                        "values; HashSet<T> stores unique values. Both use hashing and have O(1) " +
                        "average-case lookup and insertion."
                    )
                    CodeBlock(
                        "use std::collections::{HashMap, HashSet};\n\n" +
                        "let mut scores: HashMap<&str, u32> = HashMap::new();\n" +
                        "scores.insert(\"Alice\", 10);\n" +
                        "scores.insert(\"Bob\",   20);\n" +
                        "println!(\"{}\", scores[\"Alice\"]); // 10\n\n" +
                        "let mut seen: HashSet<i32> = HashSet::new();\n" +
                        "seen.insert(1);\n" +
                        "seen.insert(2);\n" +
                        "seen.insert(1); // duplicate ignored\n" +
                        "println!(\"{}\", seen.len()); // 2"
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
