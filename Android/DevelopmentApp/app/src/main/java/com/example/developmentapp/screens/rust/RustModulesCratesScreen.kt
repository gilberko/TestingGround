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
fun RustModulesCratesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Rust — Modules and Crates",
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
                SectionCard(title = "Crates vs Modules") {
                    BodyText(
                        "A crate is Rust's compilation unit — the smallest piece of code the " +
                        "compiler processes at once. There are two kinds: a binary crate has a " +
                        "fn main() entry point (its root file is src/main.rs), and a library " +
                        "crate has no main() (its root file is src/lib.rs). A Cargo package can " +
                        "contain both."
                    )
                    BodyText(
                        "A module is an organisational unit within a crate. Modules let you " +
                        "split code into namespaced groups, control visibility with pub, and " +
                        "avoid name collisions. They do not change what gets compiled — that is " +
                        "still determined at the crate level."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "main.rs and lib.rs") {
                    BodyText(
                        "src/main.rs is the binary crate root. It must contain fn main(). " +
                        "Cargo compiles it into an executable."
                    )
                    BodyText(
                        "src/lib.rs is the library crate root. It has no main(). Cargo compiles " +
                        "it into a library (.rlib by default) that can be used by the binary or " +
                        "by other packages."
                    )
                    BodyText(
                        "In the same Cargo package you can have both. The binary in main.rs can " +
                        "call functions from lib.rs as if it were an external dependency, using " +
                        "the package name as the crate name."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Calling lib.rs Functions from main.rs") {
                    BodyText(
                        "Cargo.toml defines the package name. That name becomes the crate name " +
                        "you use in main.rs to reference the library. If Cargo.toml has " +
                        "name = \"my_app\", then in main.rs you write use my_app::greet; or " +
                        "call my_app::greet() directly."
                    )
                    CodeBlock(
                        "// src/lib.rs\n" +
                        "pub fn greet(name: &str) {\n" +
                        "    println!(\"Hello, {}!\", name);\n" +
                        "}"
                    )
                    CodeBlock(
                        "// src/main.rs\n" +
                        "use my_app::greet;\n\n" +
                        "fn main() {\n" +
                        "    greet(\"world\");  // Hello, world!\n" +
                        "}"
                    )
                    BodyText(
                        "The function in lib.rs must be marked pub. Without pub it is private " +
                        "to the library crate and the binary cannot see it."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The pub Keyword") {
                    BodyText(
                        "By default, everything in Rust is private to its module. pub makes an " +
                        "item accessible from outside. You can apply pub to functions, structs, " +
                        "enums, modules, and individual struct fields."
                    )
                    CodeBlock(
                        "pub fn visible_fn() { }      // callable from outside the module\n" +
                        "fn private_fn() { }           // only usable inside this module\n\n" +
                        "pub struct Config {\n" +
                        "    pub name: String,         // field is public\n" +
                        "    timeout: u32,             // field is private\n" +
                        "}\n\n" +
                        "pub mod utils { }             // module itself is public"
                    )
                    BodyText(
                        "For a struct to be fully constructable from outside its module, both " +
                        "the struct and all the fields you need to set must be pub. Alternatively " +
                        "you can provide a public constructor function."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Cargo.toml Structure") {
                    BodyText(
                        "Cargo.toml is the manifest file for a Rust package. It tells Cargo the " +
                        "package metadata and which external crates to download."
                    )
                    CodeBlock(
                        "[package]\n" +
                        "name    = \"my_app\"   # becomes the crate name\n" +
                        "version = \"0.1.0\"\n" +
                        "edition = \"2021\"\n\n" +
                        "[dependencies]\n" +
                        "rand = \"0.8\"         # fetched from crates.io"
                    )
                    BodyText(
                        "crates.io is the official Rust package registry. When you add a " +
                        "dependency, cargo build downloads and compiles it automatically. You can " +
                        "browse crates at crates.io and paste the version line directly into " +
                        "[dependencies]."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Modules from Files") {
                    BodyText(
                        "Writing mod bla; in a source file tells the compiler to look for " +
                        "the module's content in either bla.rs or bla/mod.rs. The code is " +
                        "included as if it were written inline under a mod bla { } block."
                    )
                    BodyText(
                        "In lib.rs: mod bla; includes bla.rs but keeps it internal — callers of " +
                        "the library cannot access it. pub mod bla; includes it AND re-exports it, " +
                        "so external callers can use bla's public items. use bla::some_fn; brings " +
                        "some_fn into scope so you can call it without the full path."
                    )
                    CodeBlock(
                        "// src/bla.rs\n" +
                        "pub fn some_fn() {\n" +
                        "    println!(\"called some_fn from bla\");\n" +
                        "}"
                    )
                    CodeBlock(
                        "// src/lib.rs\n" +
                        "pub mod bla;           // include bla.rs and export it\n" +
                        "use bla::some_fn;      // bring into scope within lib.rs\n\n" +
                        "pub fn run() {\n" +
                        "    some_fn();         // no need to write bla::some_fn()\n" +
                        "}"
                    )
                    CodeBlock(
                        "// src/main.rs\n" +
                        "use my_app::bla::some_fn;\n\n" +
                        "fn main() {\n" +
                        "    some_fn();         // works because bla is pub mod in lib.rs\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Compiling") {
                    BodyText(
                        "cargo build compiles the entire package — both the library and the " +
                        "binary if both exist. The output goes into target/debug/ by default."
                    )
                    BodyText(
                        "cargo run builds and immediately runs the binary crate. If there is no " +
                        "binary crate (lib-only package), cargo run has nothing to run."
                    )
                    BodyText(
                        "cargo build --lib builds only the library crate. Libraries produce an " +
                        ".rlib file (Rust's own format, used when linking Rust-to-Rust). If you " +
                        "declare crate-type = [\"cdylib\"] in Cargo.toml you get a .so (Linux), " +
                        ".dll (Windows), or .dylib (macOS) for use from other languages."
                    )
                    CodeBlock(
                        "cargo build           # debug build (fast compile, no optimisations)\n" +
                        "cargo build --release # release build (slower compile, full optimisations)\n" +
                        "cargo run             # build + run binary\n" +
                        "cargo build --lib     # build only the library crate"
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
