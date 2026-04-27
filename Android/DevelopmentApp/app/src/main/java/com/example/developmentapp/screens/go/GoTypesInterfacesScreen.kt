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
fun GoTypesInterfacesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Go — Types and Interfaces",
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
            item { Spacer(Modifier.height(16.dp)) }

            item {
                SectionCard(title = "The type Keyword") {
                    BodyText("type declares a new named type. It can be based on any existing type: a built-in, another named type, a struct, a function signature, a slice, a map, etc.")
                    BodyText("The new type is distinct — it has its own identity, its own method set, and is NOT directly interchangeable with the underlying type without an explicit conversion.")
                    CodeBlock(
                        "type Celsius    float64\n" +
                        "type Fahrenheit float64\n" +
                        "type UserID     int\n" +
                        "type StringSlice []string\n" +
                        "\n" +
                        "// Type based on a function signature\n" +
                        "type Predicate func(x int) bool\n" +
                        "\n" +
                        "// Type based on a struct\n" +
                        "type Point struct {\n" +
                        "    X, Y float64\n" +
                        "}\n" +
                        "\n" +
                        "// Type alias (different from a new type — same type, different name)\n" +
                        "type MyInt = int   // alias: MyInt and int are identical"
                    )
                    BodyText("Note: type T = U is a type alias (same type). type T U creates a brand-new named type with U as its underlying type.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Named Types Are NOT Interchangeable") {
                    BodyText("Even though OtherInt has int as its underlying type, you cannot assign an OtherInt to an int variable or pass one where the other is expected. The compiler treats them as different types.")
                    BodyText("An explicit conversion is always required. The conversion is a no-op at runtime (same bit pattern) but the compiler enforces the distinction.")
                    CodeBlock(
                        "type OtherInt int\n" +
                        "\n" +
                        "var y OtherInt = 10\n" +
                        "var x int\n" +
                        "\n" +
                        "x = y          // COMPILE ERROR: cannot use y (variable of type OtherInt) as type int\n" +
                        "x = int(y)     // OK — explicit conversion\n" +
                        "y = OtherInt(x) // OK — back again\n" +
                        "\n" +
                        "// Same applies to function parameters:\n" +
                        "func double(n int) int { return n * 2 }\n" +
                        "\n" +
                        "double(y)       // COMPILE ERROR\n" +
                        "double(int(y))  // OK"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Adding Methods to Named Types") {
                    BodyText("A key reason to declare a named type is to attach methods to it. You can only define methods on types declared in the same package — you cannot add methods directly to built-in types, so you alias them first.")
                    CodeBlock(
                        "type Celsius float64\n" +
                        "type Fahrenheit float64\n" +
                        "\n" +
                        "func (c Celsius) ToFahrenheit() Fahrenheit {\n" +
                        "    return Fahrenheit(c*9/5 + 32)\n" +
                        "}\n" +
                        "\n" +
                        "func (f Fahrenheit) ToCelsius() Celsius {\n" +
                        "    return Celsius((f - 32) * 5 / 9)\n" +
                        "}\n" +
                        "\n" +
                        "boiling := Celsius(100)\n" +
                        "fmt.Println(boiling.ToFahrenheit()) // 212\n" +
                        "\n" +
                        "// The compiler rejects accidental unit mix-ups:\n" +
                        "var f Fahrenheit = boiling // COMPILE ERROR — catches the bug"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Interfaces") {
                    BodyText("An interface type specifies a set of method signatures. Any type that has all the required methods automatically satisfies the interface — there is no implements keyword. This is called implicit satisfaction (also known as structural typing or duck typing).")
                    BodyText("When you define a struct and give it the methods that an interface requires, it satisfies that interface immediately. You do not need to say that it does.")
                    CodeBlock(
                        "type Speaker interface {\n" +
                        "    Speak() string\n" +
                        "}\n" +
                        "\n" +
                        "type Dog struct{ Name string }\n" +
                        "\n" +
                        "// Dog satisfies Speaker just by having this method — nothing else needed\n" +
                        "func (d Dog) Speak() string {\n" +
                        "    return \"Woof! I am \" + d.Name\n" +
                        "}\n" +
                        "\n" +
                        "type Cat struct{ Name string }\n" +
                        "\n" +
                        "func (c Cat) Speak() string {\n" +
                        "    return \"Meow! I am \" + c.Name\n" +
                        "}\n" +
                        "\n" +
                        "// Both Dog and Cat satisfy Speaker without any declaration\n" +
                        "func announce(s Speaker) {\n" +
                        "    fmt.Println(s.Speak())\n" +
                        "}\n" +
                        "\n" +
                        "announce(Dog{Name: \"Rex\"}) // Woof! I am Rex\n" +
                        "announce(Cat{Name: \"Mia\"}) // Meow! I am Mia"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Interface Variables and Type Assertions") {
                    BodyText("A variable of interface type can hold any value that satisfies the interface. Under the hood, an interface value stores two pointers: one to the concrete type descriptor and one to the data.")
                    BodyText("Type assertion v.(ConcreteType) extracts the concrete value. If the assertion is wrong it panics. The safe form — v, ok := s.(ConcreteType) — returns false instead of panicking. A type switch lets you branch on the dynamic type.")
                    CodeBlock(
                        "var s Speaker = Dog{Name: \"Rex\"}\n" +
                        "fmt.Println(s.Speak()) // Woof!\n" +
                        "\n" +
                        "// Type assertion — panics if s is not a Dog\n" +
                        "d := s.(Dog)\n" +
                        "fmt.Println(d.Name) // Rex\n" +
                        "\n" +
                        "// Safe form — no panic\n" +
                        "if d, ok := s.(Dog); ok {\n" +
                        "    fmt.Println(\"it's a Dog:\", d.Name)\n" +
                        "}\n" +
                        "\n" +
                        "// Type switch\n" +
                        "switch v := s.(type) {\n" +
                        "case Dog:\n" +
                        "    fmt.Println(\"Dog:\", v.Name)\n" +
                        "case Cat:\n" +
                        "    fmt.Println(\"Cat:\", v.Name)\n" +
                        "default:\n" +
                        "    fmt.Println(\"unknown animal\")\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Compile-Time Interface Check: var _ Speaker = Dog{}") {
                    BodyText("var _ Speaker = Dog{} is a common Go idiom. It asks the compiler to verify at compile time that Dog satisfies the Speaker interface.")
                    BodyText("The blank identifier _ means the variable is immediately discarded — no storage is allocated and no variable exists at runtime. This line generates no code.")
                    BodyText("If Dog is missing a required method, the compiler reports an error right at this line, rather than later and potentially more confusingly at the point of use. It is used as explicit documentation and early error detection in large codebases.")
                    CodeBlock(
                        "type Speaker interface {\n" +
                        "    Speak() string\n" +
                        "}\n" +
                        "\n" +
                        "// Compile-time assertion — fails if Dog doesn't implement Speak()\n" +
                        "var _ Speaker = Dog{}\n" +
                        "\n" +
                        "// For pointer receivers, use a nil pointer of the type:\n" +
                        "var _ Speaker = (*Dog)(nil)\n" +
                        "\n" +
                        "// If Dog is missing Speak(), error is:\n" +
                        "// cannot use Dog{} (type Dog) as type Speaker:\n" +
                        "//   Dog does not implement Speaker (missing Speak method)"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "The Empty Interface and any") {
                    BodyText("An interface with no methods is satisfied by every type — it can hold a value of any type. Before Go 1.18 this was written as interface{}. Since Go 1.18 the predeclared alias any is preferred and idiomatic.")
                    BodyText("To use the concrete value stored in an any variable, you must perform a type assertion or type switch — the compiler does not know the underlying type, so you cannot call methods on it directly.")
                    CodeBlock(
                        "// any is an alias for interface{} — same thing\n" +
                        "func printAnything(v any) {\n" +
                        "    fmt.Printf(\"(%T) %v\\n\", v, v)\n" +
                        "}\n" +
                        "\n" +
                        "printAnything(42)                // (int) 42\n" +
                        "printAnything(\"hello\")           // (string) hello\n" +
                        "printAnything(Dog{Name: \"Rex\"})  // (main.Dog) {Rex}\n" +
                        "\n" +
                        "// Slices and maps of any\n" +
                        "items := []any{1, \"two\", 3.0, true}\n" +
                        "m     := map[string]any{\"name\": \"Alice\", \"age\": 30}"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
