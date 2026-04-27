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
fun RustEnumsStructsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Rust — Enums and Structs",
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
                SectionCard(title = "Enums") {
                    BodyText(
                        "An enum defines a type that can be one of several named variants. " +
                        "Use the enum keyword followed by the variants inside {}. To create a " +
                        "value, write TypeName::VariantName."
                    )
                    CodeBlock(
                        "enum Direction {\n" +
                        "    North,\n" +
                        "    South,\n" +
                        "    East,\n" +
                        "    West,\n" +
                        "}\n\n" +
                        "let dir = Direction::North;"
                    )
                    BodyText(
                        "Enums are not just named constants — each variant is a distinct member " +
                        "of the type, and the compiler ensures you handle all of them wherever " +
                        "the enum is used."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Enums With Data") {
                    BodyText(
                        "Each variant can carry associated data. Different variants can hold " +
                        "different types and amounts of data. A tuple-style variant holds " +
                        "unnamed fields; a struct-style variant holds named fields. This makes " +
                        "Rust enums algebraic data types — far more powerful than C-style enums."
                    )
                    CodeBlock(
                        "enum Shape {\n" +
                        "    Circle(f64),               // tuple variant: holds a radius\n" +
                        "    Rectangle(f64, f64),        // holds width and height\n" +
                        "    Named { label: String },   // struct variant: named field\n" +
                        "}\n\n" +
                        "let c = Shape::Circle(5.0);\n" +
                        "let r = Shape::Rectangle(3.0, 4.0);"
                    )
                    BodyText("Rust's standard library uses this extensively:")
                    CodeBlock(
                        "// Option<T>: a value that is either present or absent\n" +
                        "let maybe: Option<i32> = Some(42);\n" +
                        "let nothing: Option<i32> = None;\n\n" +
                        "// Result<T, E>: either success or an error\n" +
                        "let ok: Result<i32, &str> = Ok(42);\n" +
                        "let err: Result<i32, &str> = Err(\"something went wrong\");"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Matching Enums") {
                    BodyText(
                        "Use match to handle each variant. match is exhaustive — the compiler " +
                        "will reject code that does not cover every possible variant. Data " +
                        "inside variants is destructured in the match arm."
                    )
                    CodeBlock(
                        "fn describe(d: Direction) {\n" +
                        "    match d {\n" +
                        "        Direction::North => println!(\"Going north\"),\n" +
                        "        Direction::South => println!(\"Going south\"),\n" +
                        "        Direction::East  => println!(\"Going east\"),\n" +
                        "        Direction::West  => println!(\"Going west\"),\n" +
                        "    }\n" +
                        "}"
                    )
                    BodyText("Destructuring data variants:")
                    CodeBlock(
                        "match shape {\n" +
                        "    Shape::Circle(r)         => println!(\"Circle, radius {r}\"),\n" +
                        "    Shape::Rectangle(w, h)   => println!(\"Rectangle {w}x{h}\"),\n" +
                        "    Shape::Named { label }   => println!(\"Named: {label}\"),\n" +
                        "}"
                    )
                    BodyText(
                        "Use _ as a catch-all arm to handle any remaining variants without " +
                        "naming them: _ => println!(\"something else\")"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Structs") {
                    BodyText(
                        "A struct groups related named fields under one type. Fields have " +
                        "explicit types. Create an instance by specifying all field names and " +
                        "values. Access fields with dot notation."
                    )
                    CodeBlock(
                        "struct Point {\n" +
                        "    x: f64,\n" +
                        "    y: f64,\n" +
                        "}\n\n" +
                        "struct User {\n" +
                        "    name: String,\n" +
                        "    age:  u32,\n" +
                        "}\n\n" +
                        "let p = Point { x: 1.0, y: 2.5 };\n" +
                        "println!(\"{} {}\", p.x, p.y);\n\n" +
                        "let u = User { name: String::from(\"Alice\"), age: 30 };\n" +
                        "println!(\"{} is {}\", u.name, u.age);"
                    )
                    BodyText(
                        "Structs are value types — assigning one struct to another moves it " +
                        "(if it contains heap data) or copies it (if all fields are Copy). " +
                        "To mutate a struct's fields, the variable holding it must be mut."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "impl — Adding Methods to a Struct") {
                    BodyText(
                        "Use an impl block to attach methods and associated functions to a " +
                        "struct. Methods take &self (read-only), &mut self (mutating), or " +
                        "self (consuming) as their first parameter and are called with dot " +
                        "notation. Associated functions have no self parameter and are called " +
                        "with :: notation — they are used for constructors and utilities."
                    )
                    CodeBlock(
                        "struct Rectangle {\n" +
                        "    width:  f64,\n" +
                        "    height: f64,\n" +
                        "}\n\n" +
                        "impl Rectangle {\n" +
                        "    // Associated function — called with Rectangle::new(...)\n" +
                        "    fn new(width: f64, height: f64) -> Rectangle {\n" +
                        "        Rectangle { width, height }\n" +
                        "    }\n\n" +
                        "    // Method — called with rect.area()\n" +
                        "    fn area(&self) -> f64 {\n" +
                        "        self.width * self.height\n" +
                        "    }\n\n" +
                        "    // Mutating method — called with rect.scale(...)\n" +
                        "    fn scale(&mut self, factor: f64) {\n" +
                        "        self.width  *= factor;\n" +
                        "        self.height *= factor;\n" +
                        "    }\n" +
                        "}\n\n" +
                        "let mut r = Rectangle::new(3.0, 4.0);\n" +
                        "println!(\"{}\", r.area());   // 12\n" +
                        "r.scale(2.0);\n" +
                        "println!(\"{}\", r.area());   // 48"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Methods vs Associated Functions") {
                    BodyText(
                        "A method has self, &self, or &mut self as its first parameter. " +
                        "It operates on a specific instance and is called with dot notation: " +
                        "instance.method(). Rust auto-borrows when calling methods — if you " +
                        "call a &mut self method on a mut variable, Rust takes the mutable " +
                        "reference automatically."
                    )
                    BodyText(
                        "An associated function has no self parameter. It belongs to the type " +
                        "as a whole, not to any instance, and is called with :: notation: " +
                        "Type::function(). Rust has no special constructor keyword; by strong " +
                        "convention, Type::new(...) is the constructor pattern."
                    )
                    CodeBlock(
                        "let r = Rectangle::new(3.0, 4.0);  // associated function\n" +
                        "println!(\"{}\", r.area());          // method on r"
                    )
                    BodyText(
                        "A struct can have multiple impl blocks. This is sometimes used to " +
                        "group methods thematically or to satisfy trait implementations in " +
                        "separate blocks."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
