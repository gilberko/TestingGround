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
fun KotlinGenericsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Kotlin — Generics",
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
                SectionCard(title = "Generic Classes") {
                    BodyText(
                        "A generic class is parameterised by one or more type parameters, written in " +
                        "angle brackets after the class name. The type parameter is a placeholder " +
                        "that the caller fills in at use-site.\n\n" +
                        "Multiple type parameters are separated by commas: class Pair<A, B>.\n\n" +
                        "By default, a type parameter T can be Any? — it accepts any type including " +
                        "nullable. Add an upper bound to restrict it."
                    )
                    CodeBlock(
                        "// Generic container:\n" +
                        "class Box<T>(val value: T) {\n" +
                        "    fun get(): T = value\n" +
                        "    override fun toString() = \"Box(\$value)\"\n" +
                        "}\n\n" +
                        "val intBox    = Box(42)          // Box<Int>  — type inferred\n" +
                        "val strBox    = Box(\"hello\")     // Box<String>\n" +
                        "println(intBox.get())   // 42\n\n" +
                        "// Multiple type parameters:\n" +
                        "class Pair<A, B>(val first: A, val second: B)\n\n" +
                        "val p = Pair(\"Alice\", 30)   // Pair<String, Int>\n" +
                        "println(\"\${p.first}: \${p.second}\")  // Alice: 30"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Generic Functions") {
                    BodyText(
                        "A generic function declares its type parameter(s) before the function name. " +
                        "The type is usually inferred from the arguments — you rarely need to specify " +
                        "it explicitly."
                    )
                    CodeBlock(
                        "// Generic identity function:\n" +
                        "fun <T> identity(x: T): T = x\n\n" +
                        "println(identity(42))        // 42  — T inferred as Int\n" +
                        "println(identity(\"hello\"))   // hello — T inferred as String\n\n" +
                        "// Generic swap:\n" +
                        "fun <T> swap(pair: Pair<T, T>): Pair<T, T> = Pair(pair.second, pair.first)\n\n" +
                        "// Explicit type argument (rarely needed):\n" +
                        "val result = identity<String>(\"explicit\")\n\n" +
                        "// Generic extension function:\n" +
                        "fun <T> List<T>.secondOrNull(): T? = if (size >= 2) this[1] else null\n\n" +
                        "println(listOf(10, 20, 30).secondOrNull())  // 20\n" +
                        "println(listOf(10).secondOrNull())           // null"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Type Bounds") {
                    BodyText(
                        "An upper bound restricts which types can be substituted for T. Use " +
                        "<T : UpperBound>. Inside the function or class body, you can then call any " +
                        "method of UpperBound on values of type T.\n\n" +
                        "The default upper bound is Any? (anything, including nullables). Use " +
                        "<T : Any> to exclude nullable types. Multiple bounds require a where clause."
                    )
                    CodeBlock(
                        "// T must implement Comparable<T> — enables comparison operators:\n" +
                        "fun <T : Comparable<T>> max(a: T, b: T): T = if (a >= b) a else b\n\n" +
                        "println(max(3, 7))          // 7\n" +
                        "println(max(\"apple\", \"zebra\")) // zebra\n\n" +
                        "// Exclude nullable types:\n" +
                        "fun <T : Any> printNonNull(value: T) = println(value)\n" +
                        "// printNonNull(null)  // COMPILE ERROR — null not allowed\n\n" +
                        "// Multiple bounds — where clause:\n" +
                        "fun <T> copyWhenBoth(src: T): T\n" +
                        "        where T : Cloneable, T : Comparable<T> {\n" +
                        "    return src\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Type Erasure") {
                    BodyText(
                        "Like Java, Kotlin generics are implemented with type erasure on the JVM. " +
                        "The type parameter T is removed at runtime — List<Int> and List<String> are " +
                        "both just List at the bytecode level. This means:\n\n" +
                        "• You cannot check is List<Int> at runtime — you only know is List<*>\n" +
                        "• You cannot create an array of a generic type: Array<T>() does not work " +
                        "(use arrayOfNulls or reified, see next section)\n\n" +
                        "The * wildcard (star projection) is Kotlin's equivalent of Java's ? — it " +
                        "means 'a List of some type I don't know'."
                    )
                    CodeBlock(
                        "// Type erasure — both are just List at runtime:\n" +
                        "val ints:    List<Int>    = listOf(1, 2, 3)\n" +
                        "val strings: List<String> = listOf(\"a\", \"b\")\n\n" +
                        "// Can only check the raw type, not the type argument:\n" +
                        "println(ints is List<*>)     // true  — star projection, always works\n" +
                        "// println(ints is List<Int>) // WARNING: unchecked cast at runtime\n\n" +
                        "// Safe cast with star projection:\n" +
                        "fun printList(obj: Any) {\n" +
                        "    if (obj is List<*>) {    // OK — erased type check\n" +
                        "        obj.forEach { println(it) }\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Variance — out (Covariant)") {
                    BodyText(
                        "By default, generic classes are invariant: List<Dog> is not a subtype of " +
                        "List<Animal> even though Dog IS-A Animal. Variance annotations change this.\n\n" +
                        "out T (covariant) — the class only produces T, never consumes it. Declared " +
                        "on the class. Allows List<Dog> to be used where List<Animal> is expected.\n\n" +
                        "This mirrors Java's ? extends T wildcard but is declared once on the class " +
                        "(declaration-site variance) rather than at every call site."
                    )
                    CodeBlock(
                        "// Without variance — invariant:\n" +
                        "// val animals: List<Animal> = listOf<Dog>()  // COMPILE ERROR\n\n" +
                        "// Kotlin's built-in List<T> is declared as interface List<out T>:\n" +
                        "// So List<Dog> IS a subtype of List<Animal>:\n" +
                        "open class Animal\n" +
                        "class Dog : Animal()\n\n" +
                        "val dogs: List<Dog> = listOf(Dog(), Dog())\n" +
                        "val animals: List<Animal> = dogs   // OK — List is covariant\n\n" +
                        "// Declaring your own covariant class:\n" +
                        "class Producer<out T>(private val value: T) {\n" +
                        "    fun get(): T = value   // OK — produces T\n" +
                        "    // fun set(v: T) { }   // COMPILE ERROR — out position only\n" +
                        "}\n\n" +
                        "val dogProducer: Producer<Dog>    = Producer(Dog())\n" +
                        "val anyProducer: Producer<Animal> = dogProducer  // OK"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Variance — in (Contravariant)") {
                    BodyText(
                        "in T (contravariant) — the class only consumes T, never produces it. " +
                        "Allows a Comparator<Animal> to be used where a Comparator<Dog> is expected — " +
                        "if you can compare any Animal, you can certainly compare Dogs.\n\n" +
                        "This mirrors Java's ? super T wildcard."
                    )
                    CodeBlock(
                        "// Kotlin's Comparable<T> is contravariant: interface Comparable<in T>\n\n" +
                        "// Declaring your own contravariant class:\n" +
                        "class Sink<in T> {\n" +
                        "    fun consume(value: T) { println(\"Got: \$value\") }\n" +
                        "    // fun get(): T { }  // COMPILE ERROR — in position only\n" +
                        "}\n\n" +
                        "val animalSink: Sink<Animal> = Sink()\n" +
                        "val dogSink: Sink<Dog> = animalSink  // OK — contravariant\n" +
                        "dogSink.consume(Dog())               // works\n\n" +
                        "// Use-site variance (equivalent to Java wildcards) is also available:\n" +
                        "fun copy(from: List<out Animal>, to: MutableList<in Animal>) {\n" +
                        "    for (animal in from) to.add(animal)\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Reified Type Parameters") {
                    BodyText(
                        "Normally, type parameters are erased at runtime — you cannot write T::class " +
                        "inside a generic function. The reified keyword, used with inline functions, " +
                        "preserves the type information at the call site by inlining the function body. " +
                        "This lets you:\n\n" +
                        "• Check the actual type with is T at runtime\n" +
                        "• Get the Class<T> or KClass<T> without a separate class parameter\n" +
                        "• Use T in reflection or deserialization\n\n" +
                        "Reified only works with inline functions — the compiler replaces each call " +
                        "site with the actual function body, substituting the concrete type."
                    )
                    CodeBlock(
                        "// Without reified — must pass Class<T> manually:\n" +
                        "fun <T> isInstance(value: Any, clazz: Class<T>) = clazz.isInstance(value)\n" +
                        "println(isInstance(\"hello\", String::class.java))  // true\n\n" +
                        "// With reified — T is the actual type at the call site:\n" +
                        "inline fun <reified T> isInstance(value: Any): Boolean = value is T\n\n" +
                        "println(isInstance<String>(\"hello\"))  // true\n" +
                        "println(isInstance<Int>(\"hello\"))     // false\n\n" +
                        "// Get KClass of T without passing it:\n" +
                        "inline fun <reified T> typeNameOf(): String = T::class.simpleName ?: \"?\"\n" +
                        "println(typeNameOf<List<Int>>())  // List\n\n" +
                        "// Common real-world use — Gson / Moshi deserialization:\n" +
                        "inline fun <reified T> Gson.fromJson(json: String): T =\n" +
                        "    fromJson(json, T::class.java)"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
