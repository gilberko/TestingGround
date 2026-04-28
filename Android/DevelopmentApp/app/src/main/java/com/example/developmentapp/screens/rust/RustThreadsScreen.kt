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
fun RustThreadsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Rust — Threads",
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
                SectionCard(title = "Spawning a Thread") {
                    BodyText(
                        "std::thread::spawn takes a closure and runs it on a new OS thread. It " +
                        "returns a JoinHandle<T>, where T is the return type of the closure. " +
                        "The spawned thread begins running immediately in parallel."
                    )
                    CodeBlock(
                        "use std::thread;\n\n" +
                        "let handle = thread::spawn(|| {\n" +
                        "    println!(\"running on a new thread\");\n" +
                        "});\n\n" +
                        "// JoinHandle<()> — closure returns ()"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Joining — Waiting for a Thread") {
                    BodyText(
                        "handle.join() blocks the current thread until the spawned thread " +
                        "finishes. It returns Result<T, Box<dyn Any + Send>>: Ok(T) if the thread " +
                        "completed normally, or Err if the thread panicked."
                    )
                    CodeBlock(
                        "let handle = thread::spawn(|| {\n" +
                        "    println!(\"thread running\");\n" +
                        "});\n\n" +
                        "handle.join().unwrap();   // block until the thread finishes"
                    )
                    BodyText(
                        "If you do not call join(), the JoinHandle is dropped when it goes out " +
                        "of scope and the thread continues running detached. Dropping a " +
                        "JoinHandle does not kill the thread."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Detached Threads") {
                    BodyText(
                        "Rust has no explicit .detach() method like C++. To detach a thread, " +
                        "simply drop the JoinHandle — either by ignoring the return value of " +
                        "spawn, or by letting the handle variable go out of scope."
                    )
                    CodeBlock(
                        "// Detached: handle not bound — dropped immediately\n" +
                        "thread::spawn(|| {\n" +
                        "    println!(\"I run detached\");\n" +
                        "});\n\n" +
                        "// OR: bind then drop explicitly\n" +
                        "let handle = thread::spawn(|| println!(\"also detached\"));\n" +
                        "drop(handle);"
                    )
                    BodyText(
                        "Important: if the main thread (fn main()) returns, the entire process " +
                        "exits and all detached threads are killed immediately — even if they " +
                        "have not finished. If you need detached threads to complete, keep the " +
                        "main thread alive (e.g. with a join on another handle)."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "unwrap() Explained") {
                    BodyText(
                        "join() returns a Result<T, E>. unwrap() is a method on both Result and " +
                        "Option that extracts the success value or panics if there is none."
                    )
                    BodyText(
                        "For Result: unwrap() returns the Ok(T) value if the result is Ok, and " +
                        "panics with the Err value if it is Err. For Option: it returns the " +
                        "Some(T) value, and panics if the value is None."
                    )
                    CodeBlock(
                        "let r: Result<i32, &str> = Ok(42);\n" +
                        "let val = r.unwrap();   // val = 42\n\n" +
                        "let r: Result<i32, &str> = Err(\"oops\");\n" +
                        "r.unwrap();   // panics: called unwrap() on Err(\"oops\")"
                    )
                    BodyText(
                        "With handle.join().unwrap(): if the spawned thread panicked, join() " +
                        "returns Err and unwrap() re-panics in the calling thread — propagating " +
                        "the failure visibly. This is fine in examples and small programs. " +
                        "Production code should match on the Result instead."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Moving Data into Threads") {
                    BodyText(
                        "The closure passed to thread::spawn must satisfy 'static + Send. " +
                        "'static means it cannot hold non-owning references that might be " +
                        "invalidated. Send means it is safe to transfer to another thread."
                    )
                    BodyText(
                        "The practical solution is a move closure: move transfers ownership of " +
                        "all captured variables into the closure, satisfying 'static because " +
                        "the closure now owns the data and does not borrow from the stack."
                    )
                    CodeBlock(
                        "let data = vec![1, 2, 3];\n\n" +
                        "let handle = thread::spawn(move || {\n" +
                        "    println!(\"{:?}\", data);   // data is owned by the thread\n" +
                        "});\n\n" +
                        "// println!(\"{:?}\", data);  // compile error: data was moved\n" +
                        "handle.join().unwrap();"
                    )
                    BodyText(
                        "If you need to share data between threads without moving it, use Arc " +
                        "(atomic reference counting) to share ownership, and Mutex or RwLock " +
                        "for safe interior mutability."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Complete Example") {
                    BodyText(
                        "Putting it all together: spawn a thread, move data into it, do work, " +
                        "then join and unwrap the return value."
                    )
                    CodeBlock(
                        "use std::thread;\n\n" +
                        "fn main() {\n" +
                        "    let numbers = vec![10, 20, 30];\n\n" +
                        "    let handle = thread::spawn(move || {\n" +
                        "        let sum: i32 = numbers.iter().sum();\n" +
                        "        println!(\"Sum: {}\", sum);   // Sum: 60\n" +
                        "        sum   // return value — JoinHandle<i32>\n" +
                        "    });\n\n" +
                        "    let result = handle.join().unwrap();\n" +
                        "    println!(\"Thread returned: {}\", result);   // 60\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
