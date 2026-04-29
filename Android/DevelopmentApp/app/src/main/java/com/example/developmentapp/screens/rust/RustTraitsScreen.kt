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
fun RustTraitsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Rust — Traits and Generic Functions",
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
                SectionCard(title = "What Is a Trait?") {
                    BodyText(
                        "A trait defines a set of method signatures that a type must implement. " +
                        "Traits are Rust's primary abstraction mechanism and are analogous to " +
                        "interfaces in Java/C# or type classes in Haskell. Any type can implement " +
                        "any trait, including types you didn't write."
                    )
                    BodyText(
                        "Unlike Go (where interfaces are satisfied implicitly), Rust requires an " +
                        "explicit impl Trait for Type declaration. The compiler then verifies " +
                        "that all required methods are implemented."
                    )
                    CodeBlock(
                        "trait Speak {\n" +
                        "    fn speak(&self);\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Implementing a Trait") {
                    BodyText(
                        "Use impl TraitName for TypeName { ... } to implement a trait for a " +
                        "type. The block must provide bodies for all methods the trait declares " +
                        "that have no default implementation."
                    )
                    CodeBlock(
                        "struct Dog { name: String }\n" +
                        "struct Cat { name: String }\n\n" +
                        "impl Speak for Dog {\n" +
                        "    fn speak(&self) {\n" +
                        "        println!(\"{} says: Woof!\", self.name);\n" +
                        "    }\n" +
                        "}\n\n" +
                        "impl Speak for Cat {\n" +
                        "    fn speak(&self) {\n" +
                        "        println!(\"{} says: Meow!\", self.name);\n" +
                        "    }\n" +
                        "}\n\n" +
                        "let d = Dog { name: String::from(\"Rex\") };\n" +
                        "let c = Cat { name: String::from(\"Whiskers\") };\n" +
                        "d.speak();   // Rex says: Woof!\n" +
                        "c.speak();   // Whiskers says: Meow!"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Default Method Implementations") {
                    BodyText(
                        "A trait can provide a default body for any of its methods. Types that " +
                        "implement the trait inherit the default and may override it if needed. " +
                        "Only methods with no default must always be implemented."
                    )
                    CodeBlock(
                        "trait Greet {\n" +
                        "    fn name(&self) -> &str;            // no default — must implement\n\n" +
                        "    fn greet(&self) {                  // has a default\n" +
                        "        println!(\"Hello, I am {}\", self.name());\n" +
                        "    }\n" +
                        "}\n\n" +
                        "struct Person { name: String }\n\n" +
                        "impl Greet for Person {\n" +
                        "    fn name(&self) -> &str { &self.name }\n" +
                        "    // greet() is not overridden — the default runs\n" +
                        "}\n\n" +
                        "let p = Person { name: String::from(\"Alice\") };\n" +
                        "p.greet();   // Hello, I am Alice"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Trait Bounds — impl Trait Syntax") {
                    BodyText(
                        "To write a function that accepts any type implementing a trait, use " +
                        "impl TraitName in the parameter position. The compiler generates a " +
                        "separate function for each concrete type — this is static dispatch " +
                        "(monomorphisation), with no runtime overhead."
                    )
                    CodeBlock(
                        "fn make_speak(x: impl Speak) {\n" +
                        "    x.speak();\n" +
                        "}\n\n" +
                        "make_speak(Dog { name: String::from(\"Rex\") });\n" +
                        "make_speak(Cat { name: String::from(\"Whiskers\") });"
                    )
                    BodyText(
                        "impl Trait in parameter position is syntactic sugar for the generic " +
                        "form. It is concise and readable for straightforward single-parameter cases."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Trait Bounds — Generic Syntax") {
                    BodyText(
                        "The explicit generic form makes the type parameter visible. T: Speak " +
                        "is a trait bound — T must implement Speak. The two forms are equivalent " +
                        "for a single parameter; the generic form is required when the same type " +
                        "must appear more than once or in the return type."
                    )
                    CodeBlock(
                        "fn make_speak<T: Speak>(x: T) {\n" +
                        "    x.speak();\n" +
                        "}\n\n" +
                        "// Generic form needed when two params must be the same type:\n" +
                        "fn make_both_speak<T: Speak>(x: T, y: T) {\n" +
                        "    x.speak();\n" +
                        "    y.speak();\n" +
                        "}\n\n" +
                        "// Multiple bounds with +:\n" +
                        "fn speak_and_display<T: Speak + std::fmt::Display>(x: T) {\n" +
                        "    x.speak();\n" +
                        "    println!(\"{x}\");\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The where Clause") {
                    BodyText(
                        "When a function has several generic parameters or each parameter has " +
                        "multiple bounds, the where clause moves the bounds below the signature " +
                        "for readability. The behaviour is identical to inline bounds — it is " +
                        "purely a style choice."
                    )
                    CodeBlock(
                        "// Inline bounds — gets crowded with multiple constraints\n" +
                        "fn compare<T: Speak + std::fmt::Display>(x: T, y: T) { }\n\n" +
                        "// where clause — cleaner\n" +
                        "fn compare<T>(x: T, y: T)\n" +
                        "where\n" +
                        "    T: Speak + std::fmt::Display,\n" +
                        "{\n" +
                        "    x.speak();\n" +
                        "    println!(\"{x}\");\n" +
                        "}\n\n" +
                        "// Multiple generics — where really shines\n" +
                        "fn transfer<S, D>(src: &mut S, dst: &mut D)\n" +
                        "where\n" +
                        "    S: std::io::Read,\n" +
                        "    D: std::io::Write,\n" +
                        "{\n" +
                        "    // ...\n" +
                        "}"
                    )
                    BodyText(
                        "Prefer inline bounds for simple cases (one param, one bound). " +
                        "Switch to where when bounds grow complex enough to hurt readability."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Generic Functions") {
                    BodyText(
                        "A generic function is parameterized over one or more types. Rust resolves " +
                        "generics at compile time — a process called monomorphization — so there is " +
                        "no runtime overhead."
                    )
                    CodeBlock(
                        "fn run<T>(x: T) {\n" +
                        "    // x can be any type — we just accept it\n" +
                        "    // (we can't do much without a trait bound)\n" +
                        "}\n\n" +
                        "run(42);\n" +
                        "run(\"hello\");\n" +
                        "run(vec![1, 2, 3]);"
                    )
                    BodyText(
                        "Without a trait bound, T is completely opaque — you can pass it around but " +
                        "not inspect or print it. Add bounds to unlock capabilities:"
                    )
                    CodeBlock(
                        "use std::fmt::Display;\n\n" +
                        "fn print_it<T: Display>(x: T) {\n" +
                        "    println!(\"{}\", x);\n" +
                        "}\n\n" +
                        "print_it(42);       // prints: 42\n" +
                        "print_it(\"world\"); // prints: world"
                    )
                    BodyText(
                        "Multiple bounds use +; multiple parameters each get their own angle-bracket " +
                        "entry. For complex signatures prefer the where clause (covered below)."
                    )
                    CodeBlock(
                        "fn compare_and_print<T: PartialOrd + Display>(a: T, b: T) {\n" +
                        "    if a > b { println!(\"{} is bigger\", a); }\n" +
                        "    else     { println!(\"{} is bigger\", b); }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Bounds in Generic Functions — Type and Lifetime") {
                    BodyText(
                        "A type parameter can carry two kinds of bounds: trait bounds (what the type " +
                        "must implement) and lifetime bounds (how long references inside the type " +
                        "must live). Both can be written inline or moved to a where clause."
                    )
                    BodyText("Type bounds — inline syntax:")
                    CodeBlock(
                        "use std::fmt::{Display, Clone};\n\n" +
                        "fn print_twice<T: Display + Clone>(x: T) {\n" +
                        "    let copy = x.clone();\n" +
                        "    println!(\"{}\", x);\n" +
                        "    println!(\"{}\", copy);\n" +
                        "}"
                    )
                    BodyText("Same function using a where clause — identical behaviour, better readability for multiple bounds:")
                    CodeBlock(
                        "fn print_twice<T>(x: T)\n" +
                        "where\n" +
                        "    T: Display + Clone,\n" +
                        "{\n" +
                        "    let copy = x.clone();\n" +
                        "    println!(\"{}\", x);\n" +
                        "    println!(\"{}\", copy);\n" +
                        "}"
                    )
                    BodyText(
                        "'a is a lifetime parameter — a compile-time label you introduce in " +
                        "angle brackets, just like a type parameter T. The name is arbitrary " +
                        "(by convention: short lowercase letters like 'a, 'b, 'r). It does not " +
                        "create a variable or allocate memory; the compiler uses it purely to " +
                        "check that references don't outlive the data they point to."
                    )
                    BodyText(
                        "You can also use 'static directly as a concrete lifetime bound — the " +
                        "strictest possible requirement. T: 'static means T must not contain any " +
                        "references shorter than the entire program."
                    )
                    BodyText(
                        "Calling a function with a lifetime parameter — you never write 'a " +
                        "explicitly at the call site. The compiler infers it from the reference " +
                        "you actually pass. 'a is bounded by the owner's lifetime, not the " +
                        "reference variable itself."
                    )
                    BodyText("Three calls — three inferred lifetimes:")
                    CodeBlock(
                        "fn announce<'a, T: Display + 'a>(msg: &'a T) {\n" +
                        "    println!(\"{}\", msg);\n" +
                        "}\n\n" +
                        "fn example() {\n" +
                        "    let x: i32 = 42;\n" +
                        "    announce(&x);       // 'a = lifetime of x (local to example)\n\n" +
                        "    let s = String::from(\"hello\");\n" +
                        "    announce(&s);       // 'a = lifetime of s (also local)\n\n" +
                        "    announce(\"world\"); // 'a = 'static — literal lives in the binary\n" +
                        "}"
                    )
                    BodyText(
                        "For &x: 'a is bounded by when x goes out of scope (end of example). " +
                        "For \"world\": the bytes are in the binary so 'a = 'static. In both " +
                        "cases the compiler infers this silently."
                    )
                    BodyText(
                        "What 'a actually tracks: the region for which the borrow is valid, " +
                        "constrained by the owner's lifetime. For &x where x: i32, 'a cannot " +
                        "exceed x's scope. The reference variable (a pointer on the stack) is " +
                        "not what 'a measures — 'a measures how long you are allowed to use " +
                        "that pointer safely."
                    )
                    BodyText(
                        "Consequence: a T: 'static bound accepts owned values and 'static " +
                        "references, but rejects a local borrow:"
                    )
                    CodeBlock(
                        "fn store_forever<T: 'static>(x: T) { /* ... */ }\n\n" +
                        "fn example() {\n" +
                        "    let n: i32 = 5;\n" +
                        "    store_forever(n);                  // ✓  i32 copied in\n\n" +
                        "    store_forever(String::from(\"hi\")); // ✓  String moved in\n\n" +
                        "    store_forever(\"hello\");            // ✓  &'static str\n\n" +
                        "    let s = String::from(\"local\");\n" +
                        "    let sl: &str = &s;                 // 'a = scope of s (local)\n" +
                        "    // store_forever(sl);              // ✗  not 'static\n" +
                        "}"
                    )
                    BodyText(
                        "Lifetime bounds. T: 'a means any references stored inside T must be valid " +
                        "for at least lifetime 'a. This is required when a generic type holds a " +
                        "reference and you need to prove the reference won't dangle."
                    )
                    BodyText("Lifetime bound — inline:")
                    CodeBlock(
                        "fn announce<'a, T: Display + 'a>(msg: &'a T) {\n" +
                        "    println!(\"{}\", msg);\n" +
                        "}"
                    )
                    BodyText("Using 'static as a concrete lifetime bound:")
                    CodeBlock(
                        "// 'static bound — T must own all its data (no temporary borrows)\n" +
                        "fn store_forever<T: 'static>(x: T) {\n" +
                        "    // safe to store x indefinitely — nothing inside will dangle\n" +
                        "}\n\n" +
                        "store_forever(42i32);               // ✓  i32 — no references\n" +
                        "store_forever(String::from(\"hi\")); // ✓  String — owns its data\n" +
                        "// store_forever(&local_var);        // ✗  &'a str — temporary borrow"
                    )
                    BodyText("Combining type and lifetime bounds in a where clause:")
                    CodeBlock(
                        "fn longest_with_note<'a, T>(\n" +
                        "    x:    &'a str,\n" +
                        "    y:    &'a str,\n" +
                        "    note: T,\n" +
                        ") -> &'a str\n" +
                        "where\n" +
                        "    T: Display,\n" +
                        "{\n" +
                        "    println!(\"Note: {}\", note);\n" +
                        "    if x.len() > y.len() { x } else { y }\n" +
                        "}"
                    )
                    BodyText(
                        "Rule of thumb: use inline bounds for simple, one-line signatures; " +
                        "switch to where as soon as bounds make the parameter list hard to read."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
