package com.example.developmentapp.screens.kotlin

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
fun KotlinClasses2Screen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Kotlin — Classes: Part 2",
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
                SectionCard(title = "Classes Are final by Default") {
                    BodyText(
                        "In Java, classes and methods are open to inheritance by default. In Kotlin " +
                        "it is the opposite: all classes and methods are final by default. You must " +
                        "explicitly mark a class or method open to allow it to be extended or overridden.\n\n" +
                        "This is a deliberate design decision: accidental inheritance is a common " +
                        "source of bugs. By making everything final unless explicitly opened, Kotlin " +
                        "encourages more intentional API design.\n\n" +
                        "The same rule applies to methods — only methods marked open can be overridden."
                    )
                    CodeBlock(
                        "class Closed { }               // final by default\n" +
                        "// class Sub : Closed() { }    // COMPILE ERROR — Closed is final\n\n" +
                        "open class Base {              // can be extended\n" +
                        "    open fun greet() = println(\"Hello from Base\")\n" +
                        "    fun locked() = println(\"Cannot override\")  // final\n" +
                        "}\n\n" +
                        "class Child : Base() {\n" +
                        "    override fun greet() = println(\"Hello from Child\")  // OK\n" +
                        "    // override fun locked() { }  // COMPILE ERROR — locked is final\n" +
                        "}\n\n" +
                        "// By default, Child.greet is also final after override.\n" +
                        "// To allow further overriding:\n" +
                        "open class GrandChild : Base() {\n" +
                        "    override open fun greet() = println(\"GrandChild\")  // open again\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Inheritance") {
                    BodyText(
                        "A class inherits from another using the colon : syntax after the class name, " +
                        "followed by the parent class with its constructor call. The parent constructor " +
                        "must be called explicitly — Kotlin does not auto-call a no-arg constructor.\n\n" +
                        "Inside a method, super.methodName() calls the parent's implementation.\n\n" +
                        "Kotlin supports single inheritance for classes (like Java), but a class can " +
                        "implement multiple interfaces."
                    )
                    CodeBlock(
                        "open class Animal(val name: String) {\n" +
                        "    open fun speak() = println(\"\$name makes a sound\")\n" +
                        "}\n\n" +
                        "class Dog(name: String, val breed: String) : Animal(name) {\n" +
                        "    override fun speak() {\n" +
                        "        super.speak()                  // calls Animal.speak()\n" +
                        "        println(\"\$name barks!\")       // adds Dog behaviour\n" +
                        "    }\n" +
                        "}\n\n" +
                        "class Cat(name: String) : Animal(name) {\n" +
                        "    override fun speak() = println(\"\$name says Meow\")\n" +
                        "}\n\n" +
                        "val animals: List<Animal> = listOf(Dog(\"Rex\", \"Labrador\"), Cat(\"Whiskers\"))\n" +
                        "for (a in animals) a.speak()   // polymorphic dispatch\n" +
                        "// Rex makes a sound\n" +
                        "// Rex barks!\n" +
                        "// Whiskers says Meow"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "override Keyword") {
                    BodyText(
                        "The override keyword is mandatory in Kotlin — unlike Java where @Override is " +
                        "just a recommended annotation. This serves as both documentation and " +
                        "protection: the compiler verifies that a matching open method exists in a " +
                        "superclass or interface. A typo in the method name is caught immediately.\n\n" +
                        "After overriding, the method becomes final by default. Mark it open again " +
                        "if you want further subclasses to be able to override it.\n\n" +
                        "Properties can also be overridden with override val/var."
                    )
                    CodeBlock(
                        "open class Shape {\n" +
                        "    open val name: String = \"Shape\"\n" +
                        "    open fun area(): Double = 0.0\n" +
                        "}\n\n" +
                        "class Circle(val radius: Double) : Shape() {\n" +
                        "    override val name: String = \"Circle\"   // override property\n" +
                        "    override fun area(): Double = Math.PI * radius * radius\n" +
                        "}\n\n" +
                        "// override fun arae() { }  // COMPILE ERROR — typo caught at compile time\n\n" +
                        "// Prevent further overriding with 'final override':\n" +
                        "open class Rectangle(val w: Double, val h: Double) : Shape() {\n" +
                        "    final override fun area() = w * h  // no subclass can override area()\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Abstract Classes") {
                    BodyText(
                        "An abstract class cannot be instantiated directly — only concrete subclasses " +
                        "that implement all abstract members can be instantiated.\n\n" +
                        "Rules in Kotlin:\n" +
                        "• Declare a class abstract with the abstract keyword\n" +
                        "• An abstract method has no body — every concrete subclass must override it\n" +
                        "• If a class has any abstract method, the class must be abstract\n" +
                        "• abstract implies open — you do NOT need to write both abstract open\n" +
                        "• An abstract class can contain fully implemented methods — only the ones " +
                        "that subclasses must provide need abstract\n\n" +
                        "C++ equivalent: virtual double area() = 0; (pure virtual method)."
                    )
                    CodeBlock(
                        "abstract class Shape(val color: String) {\n" +
                        "    // Abstract — no body, must be overridden:\n" +
                        "    abstract fun area(): Double\n" +
                        "    abstract val name: String\n\n" +
                        "    // Concrete — inherited as-is:\n" +
                        "    fun describe() = println(\"\$name (\$color): area = \${area()}\")\n" +
                        "}\n\n" +
                        "class Circle(val radius: Double, color: String) : Shape(color) {\n" +
                        "    override val name = \"Circle\"\n" +
                        "    override fun area() = Math.PI * radius * radius\n" +
                        "}\n\n" +
                        "class Square(val side: Double, color: String) : Shape(color) {\n" +
                        "    override val name = \"Square\"\n" +
                        "    override fun area() = side * side\n" +
                        "}\n\n" +
                        "// val s = Shape(\"red\")  // COMPILE ERROR — abstract class\n" +
                        "val c = Circle(5.0, \"red\")\n" +
                        "c.describe()  // Circle (red): area = 78.53..."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Interfaces") {
                    BodyText(
                        "Interfaces in Kotlin are similar to Java 8+ interfaces — they can have " +
                        "abstract method declarations AND default method implementations. Unlike " +
                        "abstract classes, interfaces cannot store state (no backing fields for " +
                        "properties, only computed/abstract ones).\n\n" +
                        "A class can implement multiple interfaces (resolves the diamond problem " +
                        "that prevents multiple class inheritance).\n\n" +
                        "If two interfaces declare the same method, the implementing class must " +
                        "override it and optionally call super<Interface>.method() to pick one."
                    )
                    CodeBlock(
                        "interface Drawable {\n" +
                        "    fun draw()                         // abstract — must be implemented\n" +
                        "    fun describe() = println(\"I am drawable\")  // default implementation\n" +
                        "}\n\n" +
                        "interface Resizable {\n" +
                        "    fun resize(factor: Double)\n" +
                        "}\n\n" +
                        "// Implement multiple interfaces:\n" +
                        "class Icon(var size: Double) : Drawable, Resizable {\n" +
                        "    override fun draw()                { println(\"Drawing icon size \$size\") }\n" +
                        "    override fun resize(factor: Double) { size *= factor }\n" +
                        "}\n\n" +
                        "val icon = Icon(32.0)\n" +
                        "icon.draw()        // Drawing icon size 32.0\n" +
                        "icon.describe()    // I am drawable — default from interface\n" +
                        "icon.resize(2.0)\n" +
                        "println(icon.size) // 64.0"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Data Classes") {
                    BodyText(
                        "A data class is a class whose main purpose is to hold data. Prefix with data " +
                        "and Kotlin auto-generates:\n\n" +
                        "• equals() / hashCode() — based on primary constructor properties\n" +
                        "• toString() — returns \"ClassName(prop1=val1, prop2=val2, ...)\"\n" +
                        "• copy() — creates a shallow copy with optional property overrides\n" +
                        "• componentN() functions — for destructuring declarations\n\n" +
                        "Requirements: primary constructor must have at least one val or var; cannot " +
                        "be abstract, open, sealed, or inner."
                    )
                    CodeBlock(
                        "data class User(val name: String, val age: Int, val email: String)\n\n" +
                        "val u1 = User(\"Alice\", 30, \"alice@example.com\")\n" +
                        "val u2 = User(\"Alice\", 30, \"alice@example.com\")\n\n" +
                        "println(u1)          // User(name=Alice, age=30, email=alice@example.com)\n" +
                        "println(u1 == u2)    // true — structural equality\n" +
                        "println(u1 === u2)   // false — different instances\n\n" +
                        "// copy() — create modified copy:\n" +
                        "val u3 = u1.copy(age = 31)         // same name/email, new age\n" +
                        "val u4 = u1.copy(name = \"Bob\", email = \"bob@example.com\")\n\n" +
                        "// Destructuring:\n" +
                        "val (name, age, email) = u1\n" +
                        "println(\"\$name is \$age years old\")\n\n" +
                        "// In a for loop:\n" +
                        "val users = listOf(u1, u3, u4)\n" +
                        "for ((n, a) in users) println(\"\$n: \$a\")"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "sealed Classes") {
                    BodyText(
                        "A sealed class restricts its class hierarchy: all direct subclasses must be " +
                        "declared in the same file (Kotlin 1.5+: same package and module). The " +
                        "compiler knows all possible subtypes at compile time.\n\n" +
                        "This enables exhaustive when expressions — if you cover all subtypes, no " +
                        "else branch is needed. If a new subclass is added, every when without else " +
                        "will produce a compile error, forcing you to handle the new case.\n\n" +
                        "Sealed classes are the idiomatic way to model a fixed set of states " +
                        "(Result, NetworkState, UiEvent, etc.)."
                    )
                    CodeBlock(
                        "sealed class Result<out T> {\n" +
                        "    data class Success<T>(val data: T) : Result<T>()\n" +
                        "    data class Error(val message: String) : Result<Nothing>()\n" +
                        "    object Loading : Result<Nothing>()\n" +
                        "}\n\n" +
                        "fun handleResult(result: Result<String>) {\n" +
                        "    when (result) {\n" +
                        "        is Result.Success -> println(\"Got: \${result.data}\")\n" +
                        "        is Result.Error   -> println(\"Error: \${result.message}\")\n" +
                        "        Result.Loading    -> println(\"Loading...\")\n" +
                        "        // No 'else' needed — compiler knows all cases are covered\n" +
                        "    }\n" +
                        "}\n\n" +
                        "handleResult(Result.Success(\"Hello\"))\n" +
                        "handleResult(Result.Error(\"Network timeout\"))\n" +
                        "handleResult(Result.Loading)"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
