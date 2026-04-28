package com.example.developmentapp.screens.go

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
fun GoGettingStartedScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Go — Getting Started",
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
                SectionCard(title = "Hello, World!") {
                    BodyText("The simplest Go program:")
                    CodeBlock(
                        "package main\n\nimport \"fmt\"\n\nfunc main() {\n" +
                        "    fmt.Println(\"Hello, World!\")\n}"
                    )
                    BodyText(
                        "Every Go source file starts with a package declaration, followed by any " +
                        "imports, and then the code. The entry point of an executable program is " +
                        "always func main() inside package main."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The package Keyword") {
                    BodyText(
                        "Every Go file begins with a package declaration. Packages are Go's unit of " +
                        "code organisation — similar to namespaces in other languages. All .go files " +
                        "inside the same directory must share the same package name."
                    )
                    CodeBlock("package main      // executable program\npackage mathutil  // library package")
                    BodyText(
                        "The name main is special: it marks an executable program. The Go toolchain " +
                        "requires that the main package contains a func main() — that is the entry " +
                        "point. Any other package name (e.g. package mathutil) defines a reusable " +
                        "library package that can be imported by other code but cannot be run directly."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The main Function") {
                    BodyText(
                        "func main() is the entry point of an executable Go program. It belongs to " +
                        "package main, takes no parameters, and returns nothing. Program execution " +
                        "starts here. To exit with a specific code, call os.Exit(code)."
                    )
                    CodeBlock(
                        "func main() {\n    // execution starts here\n    fmt.Println(\"Running\")\n" +
                        "    // program exits when main returns (exit code 0)\n}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Comments") {
                    BodyText(
                        "Go uses the same comment syntax as C. Single-line comments start with // " +
                        "and run to the end of the line. Multi-line comments are enclosed between " +
                        "/* and */."
                    )
                    CodeBlock(
                        "// This is a single-line comment\n\n" +
                        "/*\n" +
                        "   This is a\n" +
                        "   multi-line comment\n" +
                        "*/"
                    )
                    BodyText(
                        "In practice, // comments are used for almost everything — inline notes, " +
                        "function documentation, and temporarily disabling code. /* */ comments " +
                        "are mainly used for large block-outs or the package doc comment at the " +
                        "top of a file."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Import Directives") {
                    BodyText(
                        "The import keyword brings in packages by their import path. There are two " +
                        "forms — single import and grouped import:"
                    )
                    CodeBlock(
                        "// Single import\nimport \"fmt\"\n\n" +
                        "// Grouped import — preferred when importing more than one package\nimport (\n" +
                        "    \"fmt\"\n    \"os\"\n    \"strings\"\n    \"math/rand\"\n)"
                    )
                    BodyText(
                        "The import path is a string that identifies the package. Standard library " +
                        "packages use short paths like \"fmt\", \"os\", \"strings\". Third-party packages " +
                        "use a full module path like \"github.com/user/repo\"."
                    )
                    BodyText(
                        "Go enforces that every imported package is actually used — if you import a " +
                        "package but never call anything from it, the compiler refuses to compile. " +
                        "This is a deliberate design choice to keep code clean and avoid dead imports."
                    )
                    CodeBlock(
                        "import \"fmt\"   // OK if you use fmt.Something below\n" +
                        "import \"os\"    // compile ERROR if os is never used"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Printing Output") {
                    BodyText(
                        "The fmt package (short for format) provides formatted I/O. You must import " +
                        "\"fmt\" to use it. The package prefix fmt. is always required — Go has no " +
                        "\"using namespace\" equivalent."
                    )
                    CodeBlock(
                        "import \"fmt\"\n\n" +
                        "fmt.Println(\"Hello\")           // prints + newline\n" +
                        "fmt.Println(\"x =\", 42)         // multiple args, space-separated\n" +
                        "fmt.Printf(\"x = %d\\n\", 42)    // formatted (like C printf)\n" +
                        "fmt.Printf(\"%v %T\\n\", x, x)   // %v = default format, %T = type name\n" +
                        "s := fmt.Sprintf(\"val=%d\", 7)  // returns formatted string, no print"
                    )
                    BodyText(
                        "fmt.Println adds a newline automatically. fmt.Printf does not — you must " +
                        "include \\n in the format string. fmt.Sprintf works like Printf but returns " +
                        "the result as a string instead of printing it."
                    )
                    BodyText(
                        "Common format verbs: %d (integer), %f (float), %s (string), %v (any value " +
                        "in default format), %T (type of the value), %p (pointer address), %t (bool)."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
