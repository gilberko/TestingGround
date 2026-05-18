package com.example.developmentapp.screens.java

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
fun JavaBasicSyntaxScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Java — Basic Syntax",
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
                SectionCard(title = "Expressions and Their Values") {
                    BodyText(
                        "An expression is any piece of code that evaluates to a value. Literals are " +
                        "expressions: 5, 3.14, true, 'A', \"hello\". Compound forms combine them: 3 + 4 " +
                        "evaluates to 7, x * 2 evaluates to twice x, isReady && hasData evaluates to a " +
                        "boolean. A method call that returns a value is also an expression. A statement, " +
                        "by contrast, is a complete instruction — a declaration, assignment, loop, etc."
                    )
                    CodeBlock(
                        "5              // integer literal expression — value is 5\n" +
                        "3 + 4          // arithmetic expression — value is 7\n" +
                        "x * 2          // value depends on x\n" +
                        "true && false  // logical expression — value is false\n" +
                        "Math.max(3, 7) // method-call expression — value is 7"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Variables — What Are They?") {
                    BodyText(
                        "A variable is a named storage location that holds a value of a fixed type. " +
                        "Every variable has three things: a type, a name, and a value. The type " +
                        "determines which values are legal and how much memory is reserved. " +
                        "We are currently covering primitive types (int, double, boolean, char, etc.). " +
                        "Variables that hold objects are discussed in Classes 101."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Declaring a Variable") {
                    BodyText(
                        "Syntax: type name; or type name = initialValue;\n" +
                        "A declaration without initialization reserves the storage but leaves the " +
                        "variable without a usable value. You can initialize it later with an assignment."
                    )
                    CodeBlock(
                        "int age;               // declared, not yet initialized\n" +
                        "int score = 0;         // declared and initialized to 0\n" +
                        "double price = 9.99;\n" +
                        "boolean isActive = true;"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Where Are Variables Stored?") {
                    BodyText(
                        "Local variables (declared inside a method) live on the call stack. Each " +
                        "method call gets a stack frame that holds its own local variables and " +
                        "parameters. When the method returns, the frame is popped and those locals " +
                        "cease to exist.\n\n" +
                        "Primitive values are stored directly inside the stack frame — no indirection. " +
                        "Variables that hold objects store a reference (a pointer to the heap object), " +
                        "but that is covered in Classes 101 when we discuss new."
                    )
                    CodeBlock(
                        "Call stack (simplified):\n" +
                        "┌─────────────────────┐\n" +
                        "│  main()             │  ← bottom frame\n" +
                        "│    int x = 5        │\n" +
                        "├─────────────────────┤\n" +
                        "│  add(3, 4)          │  ← top frame while add() runs\n" +
                        "│    int a = 3        │\n" +
                        "│    int b = 4        │\n" +
                        "│    int result = 7   │\n" +
                        "└─────────────────────┘  ← frame gone when add() returns"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Initializing and Changing a Variable's Value") {
                    BodyText(
                        "A local variable must be initialized before it is read — the compiler enforces " +
                        "this with a compile error. Assign with = and reassign any number of times. " +
                        "Compound assignment operators (+=, -=, *=, /=) combine an operation with " +
                        "assignment in one step."
                    )
                    CodeBlock(
                        "int x;\n" +
                        "x = 5;          // initialized after declaration\n" +
                        "x = x + 1;      // changed to 6\n" +
                        "x += 2;         // compound assignment — now 8\n\n" +
                        "int y;\n" +
                        "System.out.println(y);  // COMPILE ERROR — y has no value yet"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The Semicolon ;") {
                    BodyText(
                        "Every statement in Java ends with a semicolon. Common statements that need " +
                        "one: variable declarations, assignments, and method calls.\n\n" +
                        "A semicolon is NOT written after if/for/while headers (those are followed by " +
                        "a block {}), after a closing brace, or after a class or method declaration."
                    )
                    CodeBlock(
                        "int count = 0;               // declaration — needs ;\n" +
                        "count = count + 1;           // assignment — needs ;\n" +
                        "System.out.println(count);   // method call — needs ;\n\n" +
                        "if (count > 0) {             // no ; here\n" +
                        "    System.out.println(\"positive\");  // ; inside the block\n" +
                        "}                            // no ; after }"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Comments") {
                    BodyText(
                        "// starts a single-line comment — everything to the right on that line is " +
                        "ignored by the compiler.\n\n" +
                        "/* ... */ delimits a block comment that can span multiple lines.\n\n" +
                        "/** ... */ is a Javadoc comment — documentation tools such as javadoc extract " +
                        "these to generate HTML API documentation."
                    )
                    CodeBlock(
                        "// This is a single-line comment\n\n" +
                        "int x = 5;  // inline comment after code\n\n" +
                        "/*\n" +
                        " * Multi-line comment.\n" +
                        " * Useful for longer explanations.\n" +
                        " */\n\n" +
                        "/** Javadoc comment — extracted by documentation tools.\n" +
                        " *  @param n the number to double\n" +
                        " *  @return n multiplied by 2\n" +
                        " */\n" +
                        "int doubleIt(int n) { return n * 2; }"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Assignment as an Expression") {
                    BodyText(
                        "In Java, the assignment operator = is itself an expression — its value is " +
                        "the value being assigned. This makes it valid to chain assignments:\n\n" +
                        "    c = a = 10;\n\n" +
                        "Chains evaluate right-to-left: first a = 10 is evaluated (value = 10), then " +
                        "c is assigned that same value. Both a and c end up holding 10.\n\n" +
                        "This pattern is less common in modern code since it can reduce readability, " +
                        "but it is valid and occasionally useful — for instance, assigning and testing " +
                        "a value inside an if condition in one step."
                    )
                    CodeBlock(
                        "int a, c;\n" +
                        "c = a = 10;   // a = 10, then c = 10  →  both are 10\n\n" +
                        "// Assign and test in one expression\n" +
                        "int len;\n" +
                        "if ((len = text.length()) > 0) {\n" +
                        "    System.out.println(\"Length: \" + len);\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The final Keyword") {
                    BodyText(
                        "final on a local variable or field means it can only be assigned once. After " +
                        "the first assignment, any attempt to change the value is a compile error."
                    )
                    CodeBlock(
                        "final int MAX = 100;\n" +
                        "MAX = 200;   // COMPILE ERROR — cannot reassign a final variable\n\n" +
                        "final double PI = 3.14159;\n" +
                        "PI += 1;     // COMPILE ERROR"
                    )
                    BodyText(
                        "When final is applied to a reference variable (a variable that holds an " +
                        "object), only the reference is locked — you cannot point it at a different " +
                        "object. The object itself remains fully mutable: you can still call any " +
                        "method on it and change its fields."
                    )
                    CodeBlock(
                        "final Dog d = new Dog(\"Rex\");\n\n" +
                        "d.setName(\"Buddy\");  // OK — object's internal state changed\n" +
                        "d.bark();             // OK — any method can be called\n\n" +
                        "d = new Dog(\"Max\");  // COMPILE ERROR — can't rebind a final reference"
                    )
                    BodyText(
                        "final also applies to method parameters. Inside the method body you cannot " +
                        "reassign a final parameter to point to something else, but you can still " +
                        "mutate the object it points to."
                    )
                    CodeBlock(
                        "void greet(final String name) {\n" +
                        "    name = \"Bob\";  // COMPILE ERROR — can't reassign final parameter\n" +
                        "}\n\n" +
                        "void process(final Dog dog) {\n" +
                        "    dog.setName(\"Buddy\");  // OK — mutates the object\n" +
                        "    dog = new Dog();       // COMPILE ERROR — can't rebind\n" +
                        "}"
                    )
                    BodyText(
                        "A static final field is effectively a compile-time constant for primitive " +
                        "types and String: the compiler may inline its value directly. This is the " +
                        "idiomatic Java way to declare named constants.\n\n" +
                        "There is no const in Java. Unlike C++, Java provides no way to declare that " +
                        "a reference prevents mutation of the object it points to. Making an object " +
                        "truly immutable requires designing the class itself to be immutable: private " +
                        "final fields, no setters, and defensive copies in constructors."
                    )
                    CodeBlock(
                        "public static final int MAX_CONNECTIONS = 100;  // constant\n" +
                        "public static final String APP_NAME = \"MyApp\";\n\n" +
                        "// C++ const prevents mutation through the reference:\n" +
                        "// const Dog* d = new Dog();  d->setName(\"x\");  // error in C++\n" +
                        "// Java has no such mechanism — final locks only the reference binding."
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
