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
fun RustOwnershipBorrowingScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Rust — Lifetimes, Statics, Ownership and Borrowing",
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
                SectionCard(title = "Lifetimes — What Are They?") {
                    BodyText(
                        "A lifetime is the region of code during which a reference is guaranteed " +
                        "to be valid. Rust tracks lifetimes at compile time to ensure no reference " +
                        "ever outlives the data it points to — eliminating dangling pointers without " +
                        "a garbage collector."
                    )
                    BodyText(
                        "Most of the time Rust infers lifetimes automatically. You only write them " +
                        "explicitly when the compiler cannot figure them out on its own — usually " +
                        "in function signatures that return references."
                    )
                    CodeBlock(
                        "// 'a is a lifetime parameter — \"at least as long as 'a\"\n" +
                        "fn longest<'a>(x: &'a str, y: &'a str) -> &'a str {\n" +
                        "    if x.len() > y.len() { x } else { y }\n" +
                        "}\n" +
                        "// The returned reference lives at least as long as\n" +
                        "// the shorter of x and y."
                    )
                    BodyText(
                        "If a is a lifetime variable, writing 'a on a reference type (&'a T) says: " +
                        "\"this reference must not outlive lifetime a.\" It's a constraint, not a " +
                        "storage annotation."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "'static — The Eternal Lifetime") {
                    BodyText(
                        "'static is a special built-in lifetime meaning \"lives for the entire " +
                        "program.\" It has two uses: static variables (globals) and the 'static " +
                        "bound on generic types."
                    )
                    BodyText(
                        "Static variables. Rust allows static globals declared outside any function. " +
                        "They must be outside functions because they need a fixed address in the " +
                        "binary that exists before main runs and after it returns. A local variable " +
                        "lives on the stack and is destroyed when its function exits — making it " +
                        "unusable as a forever-valid reference."
                    )
                    CodeBlock(
                        "static GREETING: &str = \"hello\";  // lives in read-only binary\n\n" +
                        "fn main() {\n" +
                        "    println!(\"{}\", GREETING);\n" +
                        "}"
                    )
                    BodyText(
                        "String literals are 'static. When you write let s: &'static str = \"hello\"; " +
                        "you're annotating that the data s points to must have static lifetime. " +
                        "String literals are embedded in the binary, so this is always true."
                    )
                    CodeBlock(
                        "let s: &'static str = \"hello\";\n" +
                        "// \"hello\" is in the binary — valid for the whole program."
                    )
                    BodyText(
                        "'static as a trait bound. fn run<T: 'static>(x: T) means T must not " +
                        "contain any references shorter than 'static — T must own all its data " +
                        "(or borrow only truly permanent data).\n\n" +
                        "  • i32 satisfies T: 'static — it's a plain value with no references at all.\n" +
                        "  • String satisfies it — it owns its heap bytes; no dangling-reference risk.\n" +
                        "  • &'a str (a temporary borrow) does NOT satisfy it unless 'a == 'static."
                    )
                    CodeBlock(
                        "fn run<T: 'static>(x: T) {\n" +
                        "    // x is safe to store, send to a thread, etc.\n" +
                        "    // because nothing inside it will disappear.\n" +
                        "}\n\n" +
                        "run(42i32);               // ✓  i32 has no references\n" +
                        "run(String::from(\"hi\")); // ✓  String owns its data\n" +
                        "// run(&local_str);        // ✗  &'a str — temporary borrow"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Lifetime Bounds — Variables, Owners, and Data") {
                    BodyText(
                        "Before understanding lifetime bounds, it helps to see that Rust variables " +
                        "fall into three distinct kinds — and lifetime bounds only matter for one of them."
                    )
                    BodyText("Kind 1 — The variable IS the data (i32, f64, bool, char):")
                    BodyText(
                        "These types live entirely on the stack. There is no pointer, no indirection, " +
                        "no reference to anything outside the variable itself. If you have an i32, " +
                        "the value is right there in the variable. Lifetime bounds never restrict these."
                    )
                    BodyText("Kind 2 — The variable OWNS its data (String, Vec<T>, Box<T>):")
                    BodyText(
                        "These types hold a pointer to heap memory, but that memory belongs entirely " +
                        "to the owner. As long as you hold the value, the data is alive and valid. " +
                        "There are no external references embedded in them, so lifetime bounds do not " +
                        "restrict them either."
                    )
                    BodyText("Kind 3 — The variable BORROWS data (&T, &mut T, &str, &[T]):")
                    BodyText(
                        "These are pointers to data that lives somewhere else — owned by another " +
                        "variable. The data's lifetime is determined by whoever owns it, not by the " +
                        "reference. This is the only kind where lifetime bounds actually constrain " +
                        "things: you cannot use the reference after the owner drops, return it after " +
                        "the owner's scope ends, or store it somewhere that outlives it."
                    )
                    BodyText(
                        "Lifetime bounds — T: 'a — apply only to references (Kind 3). " +
                        "They are asking: \"do all the references inside T live at least as long as 'a?\" " +
                        "For Kind 1 and Kind 2 types that contain no external references, the answer " +
                        "is trivially yes — there is nothing to check."
                    )
                    BodyText(
                        "A lifetime bound like T: 'a says all references inside T must be valid " +
                        "for at least 'a. What this means in practice depends on what T actually is."
                    )
                    BodyText("Basic types (i32, bool, f64, char):")
                    BodyText(
                        "The variable IS the data — stored directly on the stack, no pointers " +
                        "to anything else. They contain no references, so they trivially satisfy " +
                        "T: 'a for any 'a, including 'static. A lifetime bound does not restrict " +
                        "them in any way."
                    )
                    BodyText("Owning types (String, Vec<T>, Box<T>):")
                    BodyText(
                        "The variable owns its heap data — as long as the owner is alive, the " +
                        "data is guaranteed valid. No external references, so they also trivially " +
                        "satisfy any lifetime bound."
                    )
                    BodyText("Reference types (&T, &mut T, &str, &[T]):")
                    BodyText(
                        "The variable is a pointer to data owned elsewhere. The lifetime tracks " +
                        "how long you're allowed to use that pointer — bounded by when the owner " +
                        "goes out of scope. This is where lifetime bounds actually constrain things."
                    )
                    BodyText(
                        "Rust guarantees you can never use a dangling reference — but this safety " +
                        "is achieved by restricting what you can do: you cannot store a local " +
                        "reference somewhere that outlives it, return it after the owner drops, or " +
                        "pass it to a function requiring a longer lifetime. Lifetime bounds are " +
                        "how those restrictions are expressed in generic code."
                    )
                    CodeBlock(
                        "// \"bla\" is in the binary's read-only section — never goes away\n" +
                        "let s = \"bla\";               // type: &'static str ('static inferred)\n" +
                        "let s: &'static str = \"bla\"; // explicit annotation — same thing\n\n" +
                        "// s is a (pointer, length) on the stack; the bytes live forever."
                    )
                    BodyText("Now contrast with a slice of a local String:")
                    CodeBlock(
                        "fn func() {\n" +
                        "    let s = String::from(\"bla\");  // s owns the heap bytes\n" +
                        "    let sl: &str = &s[..];         // sl borrows s; lifetime = scope of s\n\n" +
                        "    // sl is valid only while s is alive — i.e., inside func\n" +
                        "}  // s is dropped here — sl would dangle if it could escape"
                    )
                    BodyText(
                        "sl has a lifetime bounded by func's scope — not 'static. The data sl " +
                        "points to is the heap memory owned by s. When s drops, that memory is " +
                        "freed. Your intuition is correct: sl's lifetime is tied to s's scope, " +
                        "and the compiler prevents sl from escaping."
                    )
                    CodeBlock(
                        "fn store_forever<T: 'static>(x: T) { /* ... */ }\n\n" +
                        "fn func() {\n" +
                        "    let s = String::from(\"bla\");\n" +
                        "    let sl: &str = &s[..];     // lifetime = scope of func\n\n" +
                        "    // store_forever(sl);      // ✗ compile error — sl is not 'static\n" +
                        "    store_forever(\"bla\");     // ✓ &'static str — in the binary\n" +
                        "    store_forever(s);          // ✓ String moved in — owns its data\n" +
                        "    store_forever(42i32);      // ✓ i32 — no references at all\n" +
                        "}"
                    )
                    BodyText(
                        "sl cannot be passed to store_forever because its lifetime is local — it " +
                        "does not satisfy T: 'static. Moving s works because ownership transfers " +
                        "with it. The literal \"bla\" is always 'static."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "static mut — Does It Break Safety?") {
                    BodyText(
                        "Rust lets you declare a global variable as both static and mut. The " +
                        "moment you do, a real safety problem appears: any part of the code — " +
                        "including multiple threads — can read or write it at any time. " +
                        "Simultaneous reads and writes are a data race, which is undefined behavior."
                    )
                    BodyText(
                        "Rust's answer is deliberate and simple: any read or write of a static mut " +
                        "variable is only allowed inside an unsafe {} block. The compiler refuses to " +
                        "compile the access otherwise. This is not a workaround — it is Rust " +
                        "acknowledging that it cannot prove the access is safe, so it requires you " +
                        "to promise (via unsafe) that you are managing the hazard yourself."
                    )
                    CodeBlock(
                        "static mut COUNTER: i32 = 0;\n\n" +
                        "fn increment() {\n" +
                        "    unsafe {\n" +
                        "        COUNTER += 1;   // must be in unsafe — compiler enforces this\n" +
                        "    }\n" +
                        "}\n\n" +
                        "fn read_counter() -> i32 {\n" +
                        "    unsafe { COUNTER }  // reading is also unsafe\n" +
                        "}"
                    )
                    BodyText(
                        "Your intuition is correct — static mut does break Rust's normal safety " +
                        "guarantees. It is fine in single-threaded code or with external " +
                        "synchronization (a hardware guarantee, a C mutex, etc.), but the compiler " +
                        "cannot verify that. For safe shared mutable state across threads, prefer " +
                        "Mutex<T>, RwLock<T>, or the std::sync::atomic types — these wrap the " +
                        "unsafe internals so callers remain in safe Rust."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Ownership") {
                    BodyText(
                        "Every value in Rust has exactly one owner — a variable. When that " +
                        "owner goes out of scope, the value is dropped and its memory is freed " +
                        "automatically. There is no garbage collector; the compiler inserts the " +
                        "drop at compile time."
                    )
                    CodeBlock(
                        "{\n" +
                        "    let s = String::from(\"hello\");  // s owns the string\n" +
                        "    // ... use s\n" +
                        "}   // s goes out of scope here — string is dropped automatically"
                    )
                    BodyText(
                        "Ownership is the foundation of Rust's memory safety guarantees. " +
                        "Because each value has exactly one owner, there is never ambiguity " +
                        "about which piece of code is responsible for freeing it."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Stack Types Are Copied, Heap Types Are Moved") {
                    BodyText(
                        "Types that live entirely on the stack — integers, bool, char, f32/f64, " +
                        "fixed-size arrays, and tuples of these — implement the Copy trait. " +
                        "Assignment copies the bits. Both the original and the new variable are " +
                        "independently valid."
                    )
                    BodyText(
                        "Heap-allocated types like String and Vec<T> do NOT implement Copy. " +
                        "Internally, a String is a (pointer, length, capacity) struct on the " +
                        "stack that points to heap data. Simply copying those three words would " +
                        "leave two variables pointing to the same heap memory — a double-free " +
                        "when both go out of scope. Rust prevents this by making assignment " +
                        "a move instead of a copy."
                    )
                    CodeBlock(
                        "let x: i32 = 5;\n" +
                        "let y = x;          // x is Copy — y gets an independent copy\n" +
                        "println!(\"{x}\");    // OK — x is still valid\n\n" +
                        "let s1 = String::from(\"hello\");\n" +
                        "let s2 = s1;        // s1 is moved into s2\n" +
                        "// println!(\"{s1}\");  // compile error: value borrowed here after move\n" +
                        "println!(\"{s2}\");   // OK"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Move Semantics") {
                    BodyText(
                        "When a non-Copy type is assigned to another variable — or passed to a " +
                        "function — ownership is transferred (moved). The original variable is " +
                        "no longer valid. This is similar to C++ move semantics, but in Rust it " +
                        "is the default behaviour for heap types, not something you opt into."
                    )
                    CodeBlock(
                        "let my_str = String::from(\"rust\");\n" +
                        "let other_str = my_str;   // ownership moves to other_str\n" +
                        "// println!(\"{my_str}\");  // compile error: use of moved value\n" +
                        "println!(\"{other_str}\");  // OK"
                    )
                    BodyText("Passing a value to a function also moves it:")
                    CodeBlock(
                        "fn takes_string(s: String) {\n" +
                        "    println!(\"{s}\");\n" +
                        "}   // s is dropped here\n\n" +
                        "let my_str = String::from(\"rust\");\n" +
                        "takes_string(my_str);\n" +
                        "// println!(\"{my_str}\"); // compile error: my_str was moved"
                    )
                    BodyText(
                        "To use a value both before and after a function call without moving it, " +
                        "pass a reference instead — that is what borrowing is for."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Borrowing — Shared References (&T)") {
                    BodyText(
                        "Instead of moving a value, you can lend a reference to it. &T is a " +
                        "shared (immutable) borrow. You can have as many shared borrows as you " +
                        "want simultaneously. The original owner keeps its value and can be used " +
                        "normally — you just cannot mutate it while shared borrows are active."
                    )
                    CodeBlock(
                        "let my_str = String::from(\"rust\");\n" +
                        "let borrow1 = &my_str;\n" +
                        "let borrow2 = &my_str;             // fine — multiple shared borrows allowed\n" +
                        "println!(\"{borrow1} {borrow2}\"); // both valid\n" +
                        "println!(\"{my_str}\");            // owner still valid too"
                    )
                    BodyText(
                        "Shared references let you read data without taking ownership. " +
                        "Functions that only need to read a value should take &T rather than T, " +
                        "so the caller keeps ownership after the call."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Borrowing — Mutable References (&mut T)") {
                    BodyText(
                        "&mut T gives exclusive write access. Three rules apply: " +
                        "(1) the original variable must be declared mut; " +
                        "(2) at most one mutable reference can exist at a time; " +
                        "(3) while a mutable reference is alive, the original variable cannot " +
                        "be accessed at all — no reads, no writes, and no new borrows."
                    )
                    CodeBlock(
                        "let mut my_str = String::from(\"rust\");\n" +
                        "let borrow_mut = &mut my_str;\n" +
                        "borrow_mut.push_str(\" is cool\");\n" +
                        "// println!(\"{my_str}\");    // compile error: already mutably borrowed\n" +
                        "println!(\"{borrow_mut}\");  // \"rust is cool\"\n" +
                        "// borrow_mut is last used above — mutable borrow ends here (NLL)\n" +
                        "println!(\"{my_str}\");      // OK — mutable borrow has ended"
                    )
                    BodyText(
                        "The exclusivity rule prevents data races: if you have a mutable " +
                        "reference, you are guaranteed to be the only one accessing that data."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Non-Lexical Lifetimes (NLL)") {
                    BodyText(
                        "In older Rust, a borrow lasted until the closing } of its enclosing " +
                        "block — its lexical scope. Since Rust 2018 edition, the compiler uses " +
                        "data-flow analysis to find the last point at which a reference is " +
                        "actually used. A borrow ends at its last use, even if more code follows " +
                        "in the same block. This is called Non-Lexical Lifetimes (NLL)."
                    )
                    CodeBlock(
                        "let mut s = String::from(\"hello\");\n\n" +
                        "let r1 = &s;             // shared borrow begins\n" +
                        "let r2 = &s;             // another shared borrow — OK\n" +
                        "println!(\"{r1} {r2}\");  // r1 and r2 last used here — borrows end (NLL)\n\n" +
                        "// r1 and r2 are no longer alive — restrictions lift\n" +
                        "let r3 = &mut s;         // mutable borrow — OK now\n" +
                        "r3.push_str(\", world\");\n" +
                        "println!(\"{r3}\");"
                    )
                    BodyText(
                        "Without NLL, r1 and r2 would have lasted until the end of the block, " +
                        "making the mutable borrow r3 a compile error. NLL makes the borrow " +
                        "checker less conservative in practice."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Borrow Rules Summary") {
                    BodyText("While one or more shared borrows (&T) are alive:")
                    BodyText("  • You cannot mutate the original variable.")
                    BodyText("  • You cannot create a mutable borrow (&mut T).")
                    BodyText("While a mutable borrow (&mut T) is alive:")
                    BodyText("  • You cannot access the original variable at all (no read, no write).")
                    BodyText("  • You cannot create any other borrow — mutable or shared.")
                    BodyText(
                        "\"Alive\" means from the point of creation until the last use (NLL). " +
                        "Once a borrow is no longer used, all restrictions lift immediately — " +
                        "even within the same block. The compiler enforces all of these rules " +
                        "at compile time, with no runtime cost."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
