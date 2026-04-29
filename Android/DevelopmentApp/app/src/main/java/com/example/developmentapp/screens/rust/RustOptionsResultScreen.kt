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
fun RustOptionsResultScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Rust — Options, Result, and Error Handling",
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
                SectionCard(title = "panic! — Unrecoverable Errors") {
                    BodyText(
                        "When Rust encounters something that should never happen, it calls panic!. " +
                        "The thread unwinds, the error message is printed, and the program exits. " +
                        "Use it for programming errors (broken invariants), not expected failure cases."
                    )
                    CodeBlock(
                        "fn divide(a: i32, b: i32) -> i32 {\n" +
                        "    if b == 0 { panic!(\"division by zero!\"); }\n" +
                        "    a / b\n" +
                        "}"
                    )
                    BodyText(
                        "Indexing a Vec out of bounds, calling unwrap on None, and integer overflow " +
                        "in debug builds all trigger panic!. In release builds, overflow wraps silently."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Option<T> — Representing Absence") {
                    BodyText(
                        "Option<T> replaces null. A value is either Some(T) (present) or None " +
                        "(absent). The compiler forces you to handle both cases — no null-pointer surprises."
                    )
                    CodeBlock(
                        "fn find_first_even(nums: &[i32]) -> Option<i32> {\n" +
                        "    for &n in nums {\n" +
                        "        if n % 2 == 0 { return Some(n); }\n" +
                        "    }\n" +
                        "    None\n" +
                        "}\n\n" +
                        "match find_first_even(&[1, 3, 4, 7]) {\n" +
                        "    Some(n) => println!(\"Found: {}\", n),\n" +
                        "    None    => println!(\"None found\"),\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Result<T, E> — Success or Failure") {
                    BodyText(
                        "Result<T, E> is used when an operation can fail in an expected way. It is " +
                        "either Ok(T) (success with a value) or Err(E) (failure with an error). " +
                        "Functions that can fail return Result instead of panicking."
                    )
                    CodeBlock(
                        "use std::fs;\n\n" +
                        "fn read_file(path: &str) -> Result<String, std::io::Error> {\n" +
                        "    fs::read_to_string(path)\n" +
                        "}\n\n" +
                        "match read_file(\"data.txt\") {\n" +
                        "    Ok(contents) => println!(\"{}\", contents),\n" +
                        "    Err(e)       => println!(\"Error: {}\", e),\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "unwrap — Extract or Panic") {
                    BodyText(
                        "Both Option and Result have .unwrap(). It extracts the inner value if " +
                        "present/ok, or panics if the value is None/Err. Use it in tests or when " +
                        "you are certain the value exists; avoid it in production paths."
                    )
                    CodeBlock(
                        "let x: Option<i32> = Some(5);\n" +
                        "let v = x.unwrap();     // v == 5\n\n" +
                        "let y: Option<i32> = None;\n" +
                        "let _ = y.unwrap();     // PANIC: called unwrap on None"
                    )
                    BodyText(
                        ".expect(\"message\") works like unwrap but lets you provide a custom " +
                        "message that appears in the panic output, making debugging easier."
                    )
                    CodeBlock(
                        "let val = some_option.expect(\"value must be present at this point\");"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Variations of unwrap") {
                    BodyText(
                        "unwrap_or(default) — returns the inner value or a provided default.\n" +
                        "unwrap_or_default() — returns the inner value or the type's Default value.\n" +
                        "unwrap_or_else(|| expr) — computes a fallback lazily (useful when the " +
                        "fallback is expensive or has side effects)."
                    )
                    CodeBlock(
                        "let a: Option<i32> = None;\n\n" +
                        "let v1 = a.unwrap_or(0);              // 0\n" +
                        "let v2 = a.unwrap_or_default();       // 0  (i32::default() == 0)\n" +
                        "let v3 = a.unwrap_or_else(|| 2 + 2);  // 4\n\n" +
                        "// Same variants work on Result:\n" +
                        "let r: Result<i32, &str> = Err(\"oops\");\n" +
                        "let v4 = r.unwrap_or(-1);             // -1"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Custom Error Enums") {
                    BodyText(
                        "For larger programs you define your own error type — usually an enum — " +
                        "so callers can distinguish between error kinds without parsing strings."
                    )
                    CodeBlock(
                        "#[derive(Debug)]\n" +
                        "enum AppError {\n" +
                        "    NotFound(String),\n" +
                        "    ParseError(String),\n" +
                        "}\n\n" +
                        "fn load(id: u32) -> Result<String, AppError> {\n" +
                        "    if id == 0 {\n" +
                        "        return Err(AppError::NotFound(\"id 0 is reserved\".into()));\n" +
                        "    }\n" +
                        "    Ok(format!(\"item_{}\", id))\n" +
                        "}\n\n" +
                        "match load(0) {\n" +
                        "    Ok(data)                      => println!(\"Got: {}\", data),\n" +
                        "    Err(AppError::NotFound(msg))  => println!(\"Not found: {}\", msg),\n" +
                        "    Err(AppError::ParseError(msg))=> println!(\"Parse error: {}\", msg),\n" +
                        "}"
                    )
                    BodyText(
                        "Deriving Debug lets you print the error with {:?}. For library code, " +
                        "implementing the std::error::Error trait lets downstream code treat your " +
                        "error alongside other error types uniformly."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
