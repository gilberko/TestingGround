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
                        text       = "Rust — Lifetimes, Ownership and Borrowing",
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
