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
fun RustUnsafeScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Rust — Unsafe",
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
                SectionCard(title = "Why Unsafe Exists") {
                    BodyText(
                        "Rust's safety guarantees come from the borrow checker and type system. " +
                        "But some programs — OS kernels, device drivers, FFI with C libraries, " +
                        "hardware register access — need operations the compiler cannot prove are " +
                        "safe. Rust does not ban these; it quarantines them."
                    )
                    BodyText(
                        "unsafe is an explicit opt-out from the subset of checks Rust cannot " +
                        "automate. The rest of the language — ownership, types, borrowing — still " +
                        "applies. You are simply taking personal responsibility for the parts the " +
                        "compiler can't verify."
                    )
                    BodyText("What unsafe actually unlocks:")
                    BodyText("  • Dereferencing raw pointers (*const T / *mut T)")
                    BodyText("  • Calling unsafe functions")
                    BodyText("  • Accessing or mutating static mut globals")
                    BodyText("  • Implementing unsafe traits")
                    BodyText("  • Accessing fields of union types")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "static mut — Mutable Global Variables") {
                    BodyText(
                        "A static mut variable is a global that can be written to. Because any " +
                        "thread could read or write it at any time, the compiler cannot guarantee " +
                        "this is safe — so every access must be inside an unsafe {} block."
                    )
                    CodeBlock(
                        "static mut REQUEST_COUNT: u64 = 0;\n\n" +
                        "fn record_request() {\n" +
                        "    unsafe {\n" +
                        "        REQUEST_COUNT += 1;  // compiler enforces unsafe here\n" +
                        "    }\n" +
                        "}\n\n" +
                        "fn get_count() -> u64 {\n" +
                        "    unsafe { REQUEST_COUNT }  // reading is also unsafe\n" +
                        "}"
                    )
                    BodyText(
                        "Use static mut only when you can guarantee exclusive access — typically " +
                        "single-threaded code, hardware initialization, or C FFI. For multi-threaded " +
                        "shared state, use std::sync::atomic::AtomicU64 or Mutex<T> instead — they " +
                        "are safe and correct under concurrency."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Raw Pointers — *const T and *mut T") {
                    BodyText(
                        "Rust normally works with references (&T, &mut T) that are tracked by the " +
                        "borrow checker. Raw pointers (*const T, *mut T) are the low-level " +
                        "alternative: they are not checked by the borrow checker, carry no lifetime, " +
                        "and can be null or dangling."
                    )
                    BodyText(
                        "Creating a raw pointer is safe — it is just an address. Dereferencing " +
                        "one (reading or writing through it) is unsafe, because the compiler " +
                        "cannot verify the pointer is valid."
                    )
                    CodeBlock(
                        "let x: i32 = 42;\n\n" +
                        "// Creating raw pointers — safe\n" +
                        "let ptr_const: *const i32 = &x as *const i32;\n" +
                        "let ptr_mut:   *mut i32   = &x as *const i32 as *mut i32;\n\n" +
                        "// Dereferencing — must be inside unsafe\n" +
                        "unsafe {\n" +
                        "    println!(\"value via raw ptr: {}\", *ptr_const);\n" +
                        "}"
                    )
                    BodyText(
                        "Unlike references, raw pointers can be null (std::ptr::null()) and you " +
                        "can have both *const and *mut to the same location simultaneously — no " +
                        "borrow rules apply. This is exactly why dereferencing requires unsafe."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Unsafe Functions — Defining and Calling") {
                    BodyText(
                        "Prefix fn with unsafe fn to declare a function whose preconditions the " +
                        "compiler cannot verify. The function body runs in an unsafe context " +
                        "implicitly. Callers must uphold any preconditions manually."
                    )
                    BodyText(
                        "Can you call an unsafe function from a regular (safe) function? Yes — but " +
                        "you must wrap the call in an unsafe {} block. The safe function itself " +
                        "stays safe to call from anywhere; only the specific block is unsafe."
                    )
                    CodeBlock(
                        "// Defining an unsafe function\n" +
                        "unsafe fn dangerous(ptr: *const i32) -> i32 {\n" +
                        "    *ptr   // dereference — only safe if ptr is valid\n" +
                        "}\n\n" +
                        "// Calling it from a safe function — need an unsafe block\n" +
                        "fn safe_wrapper(x: i32) -> i32 {\n" +
                        "    unsafe {\n" +
                        "        dangerous(&x as *const i32)  // &x is always valid\n" +
                        "    }\n" +
                        "}\n\n" +
                        "fn main() {\n" +
                        "    println!(\"{}\", safe_wrapper(99));  // safe call site\n" +
                        "}"
                    )
                    BodyText(
                        "safe_wrapper is a safe function — anyone can call it without unsafe. " +
                        "The unsafe {} block is a local region where you've provided the required " +
                        "guarantee. Only unsafe fn declarations require their callers to be in an " +
                        "unsafe context."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Does unsafe{} Propagate?") {
                    BodyText(
                        "No. An unsafe {} block inside a safe function does not make the function " +
                        "itself unsafe. The block is a scoped region, not a property of the " +
                        "enclosing function."
                    )
                    CodeBlock(
                        "fn normal_function() {    // safe — no unsafe fn prefix\n" +
                        "    // ... safe code ...\n" +
                        "    unsafe {\n" +
                        "        // unsafe block — localized here only\n" +
                        "    }\n" +
                        "    // ... safe code continues ...\n" +
                        "}\n\n" +
                        "// normal_function() is still safe to call — no unsafe required:\n" +
                        "normal_function();        // fine from anywhere"
                    )
                    BodyText(
                        "The only thing that makes a function unsafe to call is declaring it " +
                        "unsafe fn. An internal unsafe {} block is your responsibility locally; " +
                        "it does not change the function's public safety contract. This design " +
                        "keeps unsafe code visible and contained — you can grep for \"unsafe\" to " +
                        "find every place safety is manually managed."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Pointer Arithmetic — It's Typed Like C") {
                    BodyText(
                        "Raw pointer arithmetic in Rust is typed, just like C. Advancing a " +
                        "*const i32 by 1 moves forward by 4 bytes (the size of i32), not 1 byte. " +
                        "Advancing a *const u8 by 1 moves 1 byte. The unit is always sizeof(T)."
                    )
                    BodyText(
                        "Use .add(n) to move forward by n elements, .sub(n) to move backward. " +
                        "Both require unsafe."
                    )
                    CodeBlock(
                        "let arr: [i32; 4] = [10, 20, 30, 40];\n" +
                        "let ptr: *const i32 = arr.as_ptr();  // points to arr[0]\n\n" +
                        "unsafe {\n" +
                        "    println!(\"{}\", *ptr);         // 10 — arr[0]\n" +
                        "    println!(\"{}\", *ptr.add(1));  // 20 — arr[1]  (+4 bytes)\n" +
                        "    println!(\"{}\", *ptr.add(2));  // 30 — arr[2]  (+8 bytes)\n" +
                        "    println!(\"{}\", *ptr.add(3));  // 40 — arr[3]  (+12 bytes)\n" +
                        "}"
                    )
                    BodyText(
                        ".add(1) on *const i32 advances by 1 × 4 = 4 bytes — exactly like ptr + 1 " +
                        "in C when ptr is int*. Going out of bounds is undefined behavior (same as " +
                        "C), so always stay within the allocation."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
