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
fun GoDataTypesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Go — Data Types and Basic Variables",
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
                SectionCard(title = "Built-in Types") {
                    BodyText("Boolean:")
                    CodeBlock("bool                       // true or false (1 byte)")
                    BodyText("String:")
                    CodeBlock("string                     // immutable UTF-8 byte sequence")
                    BodyText(
                        "A string's len() returns the number of bytes, not the number of characters. " +
                        "Use the unicode/utf8 package or range over the string to work with code points."
                    )
                    BodyText("Platform-sized integers (size depends on the target CPU):")
                    CodeBlock(
                        "int                        // 4 bytes on 32-bit, 8 bytes on 64-bit\n" +
                        "uint                       // same sizes, unsigned"
                    )
                    BodyText("Fixed-size integers:")
                    CodeBlock(
                        "int8   / uint8             // 1 byte  (-128..127  /  0..255)\n" +
                        "int16  / uint16            // 2 bytes\n" +
                        "int32  / uint32            // 4 bytes\n" +
                        "int64  / uint64            // 8 bytes"
                    )
                    BodyText("Aliases:")
                    CodeBlock(
                        "byte                       // alias for uint8  (raw bytes, ASCII)\n" +
                        "rune                       // alias for int32  (Unicode code point)"
                    )
                    BodyText("Pointer-sized unsigned integer:")
                    CodeBlock(
                        "uintptr                    // 4 bytes on 32-bit, 8 bytes on 64-bit\n" +
                        "                           // large enough to hold any pointer value"
                    )
                    BodyText(
                        "uintptr is used when interfacing with the unsafe package or with C via cgo. " +
                        "It is an integer, not a pointer — the GC does not follow it, so the object " +
                        "it points to may be collected. Use unsafe.Pointer for safe GC-visible " +
                        "pointer operations."
                    )
                    BodyText("Floating-point:")
                    CodeBlock(
                        "float32                    // 4 bytes (IEEE 754 single precision)\n" +
                        "float64                    // 8 bytes (IEEE 754 double precision)\n" +
                        "                           // float64 is the default for float literals"
                    )
                    BodyText("Complex numbers:")
                    CodeBlock(
                        "complex64                  // two float32 (real + imaginary parts)\n" +
                        "complex128                 // two float64\n" +
                        "                           // complex128 is the default for complex literals"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Declaring Variables with var") {
                    BodyText(
                        "The var keyword declares one or more variables. It can be used at package " +
                        "level (outside any function) or inside a function."
                    )
                    CodeBlock(
                        "var x int              // single variable, zero value (0)\n" +
                        "var x, y int           // multiple variables of the same type — allowed\n" +
                        "var x, y = 1, 2        // multiple with initialiser, types inferred\n" +
                        "var s string = \"hi\"   // with explicit type and initialiser\n\n" +
                        "// Block syntax — useful for grouping related declarations\n" +
                        "var (\n" +
                        "    name string\n" +
                        "    age  int\n" +
                        "    active bool = true\n" +
                        ")"
                    )
                    BodyText(
                        "When you declare with var but provide no initialiser, the variable receives " +
                        "the zero value for its type (see the Zero Values section below)."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Short Variable Declaration :=") {
                    BodyText(
                        "The := operator declares and initialises a variable in one step. It is only " +
                        "valid inside a function — you cannot use := at package level."
                    )
                    CodeBlock(
                        "x := 42              // type inferred as int\n" +
                        "s := \"hello\"        // type inferred as string\n" +
                        "f := 3.14            // type inferred as float64\n" +
                        "b := true            // type inferred as bool\n" +
                        "a, b := 1, \"two\"   // multiple variables at once"
                    )
                    BodyText(
                        "Can I specify the type explicitly with :=? No. The type is always inferred " +
                        "from the right-hand side. If you need a specific type, use var:"
                    )
                    CodeBlock(
                        "var x float32 = 3.14     // explicit float32\n" +
                        "// x := float32(3.14)    // also works: type conversion on the right side"
                    )
                    BodyText("Default type inference rules for untyped literals:")
                    CodeBlock(
                        "42          --> int\n" +
                        "3.14        --> float64\n" +
                        "3+4i        --> complex128\n" +
                        "'A'         --> rune  (int32)\n" +
                        "\"hello\"     --> string\n" +
                        "true/false  --> bool"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Default (Zero) Values") {
                    BodyText(
                        "A variable declared without an explicit initialiser is automatically set to " +
                        "its zero value. There is no \"uninitialised\" memory in Go."
                    )
                    CodeBlock(
                        "bool               --> false\n" +
                        "string             --> \"\"  (empty string)\n" +
                        "int, uint, float32, float64, ... --> 0\n" +
                        "complex64, complex128 --> 0+0i\n" +
                        "pointer, slice, map, channel, function --> nil"
                    )
                    CodeBlock(
                        "var n int      // n == 0\n" +
                        "var s string   // s == \"\"\n" +
                        "var ok bool    // ok == false"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Type Conversion") {
                    BodyText(
                        "Go has no implicit type casting. Every type conversion must be written " +
                        "explicitly using the syntax TypeName(expression)."
                    )
                    CodeBlock(
                        "var i int = 42\nf := float64(i)    // int  --> float64\nu := uint(f)       // float64 --> uint\n\n" +
                        "var x float32 = 1.5\ny := float64(x)    // float32 --> float64\n\n" +
                        "// String <-> numeric conversions require the strconv package\nimport \"strconv\"\n" +
                        "s := strconv.Itoa(42)      // int to string: \"42\"\nn, _ := strconv.Atoi(\"42\") // string to int: 42"
                    )
                    BodyText(
                        "Converting between numeric types that differ in size or sign (e.g. int64 to " +
                        "int32) may truncate the value — Go does not warn you, so take care."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Constants") {
                    BodyText(
                        "Constants are declared with the const keyword. They must use = — you cannot " +
                        "use := with a constant (constants are not variables)."
                    )
                    CodeBlock(
                        "const Pi = 3.14159             // untyped constant\n" +
                        "const MaxSize int = 1024       // typed constant\n\n" +
                        "// Block syntax\nconst (\n" +
                        "    StatusOK       = 200\n" +
                        "    StatusNotFound = 404\n" +
                        ")"
                    )
                    BodyText(
                        "Go distinguishes typed and untyped constants. An untyped constant like Pi has " +
                        "no fixed type — it carries a high-precision value and adapts to the context " +
                        "where it is used. This means you can use Pi in a float32 expression or a " +
                        "float64 expression without any explicit conversion, unlike a typed float64 " +
                        "constant which would require conversion when used as float32."
                    )
                    CodeBlock(
                        "const Pi = 3.14159             // untyped — adapts to context\nvar a float32 = Pi  // OK\nvar b float64 = Pi  // OK\n\n" +
                        "const TypedPi float64 = 3.14159\nvar c float32 = TypedPi          // compile ERROR: need explicit float32(TypedPi)"
                    )
                    BodyText(
                        "iota is a special constant generator available inside const blocks — it " +
                        "starts at 0 and increments by 1 for each constant in the block, making it " +
                        "convenient for defining enumerations."
                    )
                    CodeBlock(
                        "const (\n    Sunday = iota   // 0\n    Monday          // 1\n" +
                        "    Tuesday         // 2\n    // ...\n)"
                    )
                }
            }
            item {
                SectionCard(title = "Assignment vs Declaration") {
                    BodyText(
                        "The = operator reassigns an existing variable. The := operator declares " +
                        "a brand new variable in the current scope and assigns to it in one step."
                    )
                    CodeBlock(
                        "x := 10    // declares x in this scope (must not already exist here)\n" +
                        "x = 20     // reassigns x (x must already be declared)"
                    )
                    BodyText(
                        "Every pair of curly braces {} creates a new scope. When you write " +
                        "i := something inside a block, Go always creates a brand new variable " +
                        "local to that block — even if a variable with the same name already " +
                        "exists in an outer scope. The inner variable shadows the outer one " +
                        "while the block runs; once the block exits, the inner variable is gone " +
                        "and the outer one is completely unchanged."
                    )
                    CodeBlock(
                        "i := 1\n" +
                        "if true {\n" +
                        "    i := 99         // new 'i' — shadows the outer i\n" +
                        "    fmt.Println(i)  // prints 99\n" +
                        "}\n" +
                        "fmt.Println(i)      // prints 1 — outer i was never touched"
                    )
                    BodyText(
                        "This is a common Go pitfall. If you intended to assign to the outer i, " +
                        "use = inside the block instead of :=."
                    )
                    BodyText(
                        "In Go, assignments are statements — they do not return a value. Unlike " +
                        "C, you cannot chain assignments or use an assignment as an expression. " +
                        "Writing z := t := 10 is a compile error. You also cannot write " +
                        "if i = someFunc() — use Go's init-statement syntax instead: " +
                        "if x := someFunc(); x > 0 { ... }."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
