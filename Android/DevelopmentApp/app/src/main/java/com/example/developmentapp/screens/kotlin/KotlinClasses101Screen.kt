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
fun KotlinClasses101Screen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Kotlin — Classes 101",
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
                SectionCard(title = "Defining a Class") {
                    BodyText(
                        "A class is declared with the class keyword. To create an instance, call the " +
                        "class name like a function — there is no new keyword in Kotlin.\n\n" +
                        "The simplest class has no body. Add a body in curly braces for members."
                    )
                    CodeBlock(
                        "// Minimal class:\n" +
                        "class Dog\n\n" +
                        "// Class with a body:\n" +
                        "class Cat {\n" +
                        "    var name = \"Whiskers\"\n" +
                        "    fun meow() = println(\"Meow!\")\n" +
                        "}\n\n" +
                        "// Creating instances — no 'new' keyword:\n" +
                        "val d = Dog()\n" +
                        "val c = Cat()\n" +
                        "c.meow()          // Meow!\n" +
                        "println(c.name)   // Whiskers"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Primary Constructor") {
                    BodyText(
                        "The primary constructor is declared directly in the class header, after the " +
                        "class name. If you put val or var in front of a parameter, Kotlin automatically " +
                        "creates a property with that name — no separate field declaration needed.\n\n" +
                        "Parameters without val/var are just constructor arguments, not persistent " +
                        "properties — they can be used in init blocks but are not accessible later."
                    )
                    CodeBlock(
                        "// Primary constructor — val/var parameters become properties:\n" +
                        "class Dog(val name: String, var age: Int)\n\n" +
                        "val d = Dog(\"Rex\", 3)\n" +
                        "println(d.name)   // Rex\n" +
                        "println(d.age)    // 3\n" +
                        "d.age = 4         // OK — var is mutable\n" +
                        "// d.name = \"Max\" // COMPILE ERROR — val is read-only\n\n" +
                        "// Constructor argument without val/var — not a property:\n" +
                        "class Circle(radius: Double) {\n" +
                        "    val area = Math.PI * radius * radius  // used in init only\n" +
                        "    // radius is not accessible outside init/area\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "init Block") {
                    BodyText(
                        "The init block runs during construction, right after the primary constructor " +
                        "parameters are set. It can contain validation logic or computed initialisation. " +
                        "A class can have multiple init blocks — they run in order of appearance.\n\n" +
                        "init has access to the primary constructor parameters regardless of whether " +
                        "they have val/var."
                    )
                    CodeBlock(
                        "class Person(val name: String, val age: Int) {\n" +
                        "    init {\n" +
                        "        require(name.isNotBlank()) { \"Name must not be blank\" }\n" +
                        "        require(age >= 0)          { \"Age must be non-negative\" }\n" +
                        "        println(\"Created: \$name, age \$age\")\n" +
                        "    }\n" +
                        "}\n\n" +
                        "val p = Person(\"Alice\", 30)   // prints: Created: Alice, age 30\n" +
                        "// Person(\"\", 25)             // throws IllegalArgumentException"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Secondary Constructors") {
                    BodyText(
                        "Secondary constructors use the constructor keyword inside the class body. " +
                        "They must delegate to the primary constructor using this(...) as the first " +
                        "action — the primary constructor's init block always runs.\n\n" +
                        "In practice, secondary constructors are rarely needed in Kotlin because " +
                        "primary constructor parameters can have default values."
                    )
                    CodeBlock(
                        "class Rectangle(val width: Double, val height: Double) {\n" +
                        "    // Secondary constructor for squares:\n" +
                        "    constructor(side: Double) : this(side, side)\n\n" +
                        "    val area get() = width * height\n" +
                        "}\n\n" +
                        "val r = Rectangle(4.0, 3.0)\n" +
                        "val sq = Rectangle(5.0)      // calls secondary constructor\n" +
                        "println(sq.width)   // 5.0\n" +
                        "println(sq.area)    // 25.0\n\n" +
                        "// Default values are usually cleaner than secondary constructors:\n" +
                        "class Circle(val radius: Double = 1.0)\n" +
                        "val unit = Circle()        // radius = 1.0\n" +
                        "val big  = Circle(10.0)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Properties") {
                    BodyText(
                        "A property (declared with val or var in the class body) is a field with " +
                        "auto-generated getter and setter. You access it directly by name — Kotlin " +
                        "calls the getter/setter transparently.\n\n" +
                        "Custom getter — define a get() block to compute the value on every access.\n" +
                        "Custom setter — define a set(value) block to run code on every assignment.\n\n" +
                        "The backing field is accessed inside a getter/setter as 'field'. Without a " +
                        "backing field, the property is purely computed."
                    )
                    CodeBlock(
                        "class Temperature(private var celsius: Double) {\n\n" +
                        "    // Computed property — no backing field:\n" +
                        "    val fahrenheit: Double\n" +
                        "        get() = celsius * 9.0 / 5.0 + 32.0\n\n" +
                        "    // Property with custom setter that validates:\n" +
                        "    var kelvin: Double\n" +
                        "        get() = celsius + 273.15\n" +
                        "        set(value) {\n" +
                        "            require(value >= 0) { \"Kelvin cannot be negative\" }\n" +
                        "            celsius = value - 273.15\n" +
                        "        }\n" +
                        "}\n\n" +
                        "val t = Temperature(100.0)\n" +
                        "println(t.fahrenheit)  // 212.0\n" +
                        "println(t.kelvin)      // 373.15\n" +
                        "t.kelvin = 300.0       // calls the custom setter"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Member Methods") {
                    BodyText(
                        "Methods are declared with fun inside the class body. Parameters are written " +
                        "as name: Type. The return type comes after a colon at the end of the " +
                        "signature. If a function returns Unit (nothing), the return type can be omitted.\n\n" +
                        "Single-expression functions — if the body is a single expression, you can " +
                        "use = instead of braces. The return type is often inferred.\n\n" +
                        "Inside a method, this refers to the current instance."
                    )
                    CodeBlock(
                        "class Calculator(val base: Int) {\n\n" +
                        "    // Regular method:\n" +
                        "    fun add(x: Int, y: Int): Int {\n" +
                        "        return x + y\n" +
                        "    }\n\n" +
                        "    // Single-expression shorthand:\n" +
                        "    fun addToBase(x: Int): Int = base + x\n\n" +
                        "    // Default parameter:\n" +
                        "    fun multiply(x: Int, factor: Int = 2): Int = x * factor\n\n" +
                        "    // No return value (Unit):\n" +
                        "    fun printBase() = println(\"Base is \$base\")\n" +
                        "}\n\n" +
                        "val calc = Calculator(10)\n" +
                        "println(calc.add(3, 4))        // 7\n" +
                        "println(calc.addToBase(5))     // 15\n" +
                        "println(calc.multiply(3))      // 6\n" +
                        "println(calc.multiply(3, 10))  // 30\n" +
                        "calc.printBase()               // Base is 10"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Access Modifiers") {
                    BodyText(
                        "Kotlin has four visibility modifiers:\n\n" +
                        "public (default) — visible everywhere. If you omit a modifier, the " +
                        "declaration is public. This is the opposite of Java where package-private is " +
                        "the default.\n\n" +
                        "private — visible only within the same class or file (for top-level " +
                        "declarations).\n\n" +
                        "protected — visible within the class and its subclasses. Not available for " +
                        "top-level declarations.\n\n" +
                        "internal — visible within the same compilation module. This is Kotlin's " +
                        "replacement for Java's package-private: everything within the same Gradle " +
                        "module or Maven module can see it, but not callers from other modules."
                    )
                    CodeBlock(
                        "class Account {\n" +
                        "    public  val id: Int = 1         // visible everywhere (default)\n" +
                        "    private var balance: Double = 0.0 // visible only in Account\n" +
                        "    internal val bankCode = \"ACME\"  // visible within the module\n\n" +
                        "    private fun validateAmount(a: Double) = a > 0\n\n" +
                        "    fun deposit(amount: Double) {\n" +
                        "        if (validateAmount(amount)) balance += amount\n" +
                        "    }\n\n" +
                        "    fun getBalance() = balance\n" +
                        "}\n\n" +
                        "val acc = Account()\n" +
                        "acc.deposit(100.0)\n" +
                        "println(acc.getBalance())   // 100.0\n" +
                        "// acc.balance             // COMPILE ERROR — private"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "No Copy Constructor or Destructor") {
                    BodyText(
                        "Kotlin has no copy constructor and no destructor.\n\n" +
                        "No copy constructor — Kotlin relies on the GC for memory; there is no manual " +
                        "copying needed for memory safety. For data transfer objects, use data class " +
                        "which provides a generated copy() method that creates a shallow copy with " +
                        "optional field overrides.\n\n" +
                        "No destructor — there is no ~MyClass() or finalize() that reliably runs when " +
                        "an object goes out of scope. For deterministic resource cleanup (files, " +
                        "sockets, database connections), implement Closeable or AutoCloseable and use " +
                        "the use { } extension function, which is Kotlin's equivalent of Java's " +
                        "try-with-resources."
                    )
                    CodeBlock(
                        "// data class — auto-generated copy():\n" +
                        "data class Point(val x: Int, val y: Int)\n\n" +
                        "val p1 = Point(3, 4)\n" +
                        "val p2 = p1.copy()           // exact copy\n" +
                        "val p3 = p1.copy(x = 10)     // same y=4, but x=10\n" +
                        "println(p1)  // Point(x=3, y=4)   — free toString()\n" +
                        "println(p3)  // Point(x=10, y=4)\n\n" +
                        "// Resource cleanup with use { }:\n" +
                        "java.io.FileReader(\"file.txt\").use { reader ->\n" +
                        "    val content = reader.readText()\n" +
                        "    println(content)\n" +
                        "}  // reader.close() called automatically, even if an exception is thrown"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
