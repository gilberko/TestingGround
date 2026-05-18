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
fun JavaGenericsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Java — Generics",
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
                SectionCard(title = "What Are Generics?") {
                    BodyText(
                        "Before generics (pre-Java 5), collections stored Object — you had to cast " +
                        "manually and the compiler could not catch type errors. Generics let you " +
                        "parameterize a class with a type, so the compiler enforces correctness and " +
                        "casts are inserted automatically. The angle-bracket syntax <T> introduces a " +
                        "type parameter — a placeholder filled in at the call site."
                    )
                    CodeBlock(
                        "// Without generics — no safety\n" +
                        "Box box = new Box();\n" +
                        "box.set(\"hello\");\n" +
                        "String s = (String) box.get();  // cast required; ClassCastException at runtime if wrong type\n\n" +
                        "// With generics — compiler-checked\n" +
                        "class Box<T> {\n" +
                        "    private T value;\n" +
                        "    public void set(T value) { this.value = value; }\n" +
                        "    public T    get()        { return value; }\n" +
                        "}\n\n" +
                        "Box<String>  strBox = new Box<>();\n" +
                        "strBox.set(\"hello\");\n" +
                        "String s = strBox.get();   // no cast needed\n\n" +
                        "Box<Integer> intBox = new Box<>();\n" +
                        "intBox.set(42);\n" +
                        "// intBox.set(\"hello\");  // compile error — wrong type"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Multiple Type Parameters") {
                    BodyText(
                        "A generic class can have any number of type parameters, separated by commas. " +
                        "Java uses this extensively in its standard library. By convention: T (type), " +
                        "E (element), K (key), V (value), N (number), R (return/result). The names are " +
                        "just hints — the compiler treats them identically."
                    )
                    CodeBlock(
                        "class Pair<K, V> {\n" +
                        "    private K key;\n" +
                        "    private V value;\n" +
                        "    public Pair(K key, V value) { this.key = key; this.value = value; }\n" +
                        "    public K getKey()   { return key; }\n" +
                        "    public V getValue() { return value; }\n" +
                        "}\n\n" +
                        "Pair<String, Integer> entry = new Pair<>(\"score\", 100);\n" +
                        "System.out.println(entry.getKey());    // \"score\"\n" +
                        "System.out.println(entry.getValue());  // 100\n\n" +
                        "// HashMap uses <K, V> exactly this way\n" +
                        "HashMap<String, Integer> scores = new HashMap<>();\n" +
                        "scores.put(\"Alice\", 95);"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Generic Methods") {
                    BodyText(
                        "A method can introduce its own type parameter, independent of the class. The " +
                        "type parameter goes before the return type. The compiler infers it from the " +
                        "argument — you rarely need to specify it explicitly. This is common for static " +
                        "utility methods where no generic class is needed."
                    )
                    CodeBlock(
                        "public static <T> T identity(T x) { return x; }\n\n" +
                        "String  s = identity(\"hello\");  // T inferred as String\n" +
                        "Integer n = identity(42);         // T inferred as Integer\n\n" +
                        "// Swap two elements in an array\n" +
                        "public static <T> void swap(T[] arr, int i, int j) {\n" +
                        "    T temp = arr[i];\n" +
                        "    arr[i] = arr[j];\n" +
                        "    arr[j] = temp;\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "C++ Templates — Code Duplication at Compile Time") {
                    BodyText(
                        "In C++, template<typename T> is a code-generation mechanism. The compiler " +
                        "produces a separate compiled copy for every type you instantiate it with. " +
                        "Box<int> and Box<std::string> become truly independent classes with no shared " +
                        "code.\n\n" +
                        "Advantages: each copy can be fully optimized for its type; you can use " +
                        "sizeof(T), pointer arithmetic on T, and template specialization to provide " +
                        "a completely different implementation for a specific type.\n\n" +
                        "Disadvantages: compile times grow with the number of instantiations, and the " +
                        "binary contains duplicate code per type (code bloat). Each specialization is " +
                        "a genuinely different type — Box<int> and Box<string> share nothing at runtime."
                    )
                    CodeBlock(
                        "template<typename T>\n" +
                        "class Box {\n" +
                        "    T value;\n" +
                        "public:\n" +
                        "    void set(T v) { value = v; }\n" +
                        "    T    get()    { return value; }\n" +
                        "};\n\n" +
                        "Box<int>    intBox;  // compiler generates a Box<int> class\n" +
                        "Box<string> strBox;  // compiler generates a separate Box<string> class\n" +
                        "// These are genuinely different types; no shared implementation"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Java Type Erasure — One Class for All Types") {
                    BodyText(
                        "Java takes the opposite approach. The compiler uses the type parameter for " +
                        "compile-time checking only, then erases it — replacing every T with Object " +
                        "(or the upper bound if one is declared) in the generated bytecode. There is " +
                        "exactly one compiled .class file for Box, regardless of how many types you " +
                        "use it with. Box<String> and Box<Integer> are the same class at runtime.\n\n" +
                        "This has consequences: you cannot use instanceof Box<String> (the <String> is " +
                        "unknown at runtime), you cannot write new T(), and you cannot get T.class. The " +
                        "type information for generic parameters simply does not exist in the JVM."
                    )
                    CodeBlock(
                        "// What you write:\n" +
                        "class Box<T> {\n" +
                        "    private T value;\n" +
                        "    public void set(T v) { value = v; }\n" +
                        "    public T    get()    { return value; }\n" +
                        "}\n\n" +
                        "// What the compiler produces (after erasure):\n" +
                        "class Box {\n" +
                        "    private Object value;          // T → Object\n" +
                        "    public void set(Object v)    { value = v; }\n" +
                        "    public Object get()          { return value; }\n" +
                        "}\n\n" +
                        "// At the call site the compiler inserts the cast:\n" +
                        "//   Box<String> b = ...; String s = b.get();\n" +
                        "// becomes: String s = (String) b.get();\n\n" +
                        "// These are the same class — provable at runtime:\n" +
                        "Box<String>  strBox = new Box<>();\n" +
                        "Box<Integer> intBox = new Box<>();\n" +
                        "System.out.println(strBox.getClass() == intBox.getClass()); // true\n\n" +
                        "// Won't compile — type info is erased:\n" +
                        "// if (strBox instanceof Box<String>) { }  // compile error"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Bounds — Constraining What T Can Be") {
                    BodyText(
                        "With no bounds, erased T is Object, so only Object methods (equals, toString, " +
                        "hashCode, ...) are available on T inside the generic class or method. A bound " +
                        "(extends) tells the compiler: \"T is guaranteed to implement this interface or " +
                        "extend this class.\" The erased type becomes the bound instead of Object, so the " +
                        "compiler can verify that the methods you call on T actually exist.\n\n" +
                        "Multiple bounds use &. The type erases to the first bound; subsequent bounds are " +
                        "verified at compile time only."
                    )
                    CodeBlock(
                        "// T must implement Comparable — erases to Comparable, not Object\n" +
                        "public static <T extends Comparable<T>> T max(T a, T b) {\n" +
                        "    return a.compareTo(b) >= 0 ? a : b;  // valid — Comparable has compareTo()\n" +
                        "}\n\n" +
                        "System.out.println(max(3, 7));               // 7\n" +
                        "System.out.println(max(\"apple\", \"mango\")); // \"mango\"\n" +
                        "// max(new Object(), new Object());          // compile error\n\n" +
                        "// Multiple bounds: erases to first bound (Serializable)\n" +
                        "public static <T extends Serializable & Comparable<T>> void store(T item) { ... }\n\n" +
                        "// Number bound: T.intValue(), T.doubleValue() etc. become available\n" +
                        "public static <T extends Number> double sum(List<T> list) {\n" +
                        "    double total = 0;\n" +
                        "    for (T item : list) total += item.doubleValue();\n" +
                        "    return total;\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "How the JVM Knows Which Method to Call") {
                    BodyText(
                        "At runtime the JVM has no generic type information. What makes it work is two " +
                        "things combined: the compiler's compile-time verification and the JVM's normal " +
                        "virtual dispatch — the same polymorphism used everywhere in Java.\n\n" +
                        "When you write <T extends Animal> and call t.speak(), the compiler erases T to " +
                        "Animal and emits an invokevirtual Animal.speak() instruction. At runtime the JVM " +
                        "performs its standard virtual method lookup — it checks the actual object's type " +
                        "(which might be Dog) and dispatches to Dog.speak(). The JVM does not need to know " +
                        "T was Dog at compile time; it just follows the vtable of whatever object is there."
                    )
                    CodeBlock(
                        "class Animal { public void speak() { System.out.println(\"...\"); } }\n" +
                        "class Dog extends Animal { @Override public void speak() { System.out.println(\"Woof\"); } }\n" +
                        "class Cat extends Animal { @Override public void speak() { System.out.println(\"Meow\"); } }\n\n" +
                        "// Generic method — T erases to Animal\n" +
                        "public static <T extends Animal> void makeSpeak(T animal) {\n" +
                        "    animal.speak();  // compiled as: invokevirtual Animal.speak()\n" +
                        "}\n\n" +
                        "makeSpeak(new Dog());  // JVM dispatch → Dog.speak() → \"Woof\"\n" +
                        "makeSpeak(new Cat());  // JVM dispatch → Cat.speak() → \"Meow\"\n\n" +
                        "// Without a bound, T erases to Object — only Object methods available:\n" +
                        "public static <T> void example(T obj) {\n" +
                        "    // obj.speak();    // compile error — Object has no speak()\n" +
                        "    obj.toString();    // fine — Object has toString()\n" +
                        "}"
                    )
                    BodyText(
                        "So yes — the compiler only checks that the type you are trying to use has the " +
                        "method (via the bound). The JVM does not need to know the generic type at " +
                        "runtime; it relies entirely on virtual dispatch, which already knows the actual " +
                        "object's type through its vtable. One compiled class, zero runtime overhead."
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
