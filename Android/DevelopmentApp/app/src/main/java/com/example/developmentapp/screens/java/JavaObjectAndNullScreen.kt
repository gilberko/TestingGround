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
fun JavaObjectAndNullScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Java — Object and Null",
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
                SectionCard(title = "Everything Inherits from Object") {
                    BodyText(
                        "In Java, every class implicitly extends java.lang.Object if it does not " +
                        "explicitly extend another class. Object is the root of the entire class " +
                        "hierarchy — every array, every enum, and every class you write is an " +
                        "Object at the top of the chain.\n\n" +
                        "This means you can assign any object to a variable of type Object, and " +
                        "every object responds to the methods defined on Object."
                    )
                    CodeBlock(
                        "class Dog extends Animal { ... }  // Dog -> Animal -> Object\n" +
                        "class Cat { ... }                 // Cat -> Object\n\n" +
                        "Object o = new Dog();   // valid — Dog IS-A Object\n" +
                        "Object s = \"hello\";    // String IS-A Object\n" +
                        "Object[] arr = new Dog[5]; // arrays are Objects too"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Key Methods on Object") {
                    BodyText(
                        "Every Java object inherits these methods from Object:\n\n" +
                        "equals(Object o) — tests logical equality. Default: same reference (==). " +
                        "Override to compare by content.\n\n" +
                        "hashCode() — returns an int used by hash-based collections (HashMap, " +
                        "HashSet). Contract: if a.equals(b) then a.hashCode() == b.hashCode(). " +
                        "Always override both equals and hashCode together.\n\n" +
                        "toString() — returns a human-readable string. Default: ClassName@hexAddress. " +
                        "Override to give meaningful output.\n\n" +
                        "getClass() — returns the runtime Class<?> object. Cannot be overridden.\n\n" +
                        "clone() — produces a shallow copy. Must implement Cloneable and call " +
                        "super.clone(); rarely used today — prefer copy constructors.\n\n" +
                        "wait() / notify() / notifyAll() — thread coordination via the object's " +
                        "intrinsic lock. Must be called inside a synchronized block.\n\n" +
                        "finalize() — called by GC before collection. Deprecated since Java 9; " +
                        "never rely on it."
                    )
                    CodeBlock(
                        "public class Point {\n" +
                        "    int x, y;\n" +
                        "    Point(int x, int y) { this.x = x; this.y = y; }\n\n" +
                        "    @Override\n" +
                        "    public boolean equals(Object o) {\n" +
                        "        if (this == o) return true;\n" +
                        "        if (!(o instanceof Point)) return false;\n" +
                        "        Point p = (Point) o;\n" +
                        "        return x == p.x && y == p.y;\n" +
                        "    }\n\n" +
                        "    @Override\n" +
                        "    public int hashCode() {\n" +
                        "        return Objects.hash(x, y);\n" +
                        "    }\n\n" +
                        "    @Override\n" +
                        "    public String toString() {\n" +
                        "        return \"Point(\" + x + \", \" + y + \")\";\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "What Is null?") {
                    BodyText(
                        "null is a special literal in Java that means \"this reference points to no " +
                        "object\". It is not an object itself and has no type, but it can be assigned " +
                        "to any reference type variable.\n\n" +
                        "Primitive types (int, double, boolean, char, etc.) cannot be null — they " +
                        "always hold a value. Only reference types (objects, arrays, interfaces, " +
                        "enums) can be null."
                    )
                    CodeBlock(
                        "String s = null;      // valid — String is a reference type\n" +
                        "int n = null;         // compile error — int is primitive\n\n" +
                        "String[] arr = null;  // valid — arrays are reference types\n" +
                        "List<String> list = null;  // valid\n\n" +
                        "System.out.println(s == null);  // true\n" +
                        "System.out.println(s);          // prints: null"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "NullPointerException") {
                    BodyText(
                        "A NullPointerException (NPE) is thrown at runtime when you try to use a " +
                        "null reference as if it were a real object. Common triggers:\n\n" +
                        "• Calling a method on a null reference\n" +
                        "• Accessing a field on a null reference\n" +
                        "• Getting the length or element of a null array\n" +
                        "• Throwing null as a Throwable\n" +
                        "• Unboxing a null Integer/Boolean/etc. to a primitive\n\n" +
                        "Since Java 14 the JVM prints \"helpful NPE messages\" pinpointing exactly " +
                        "which variable was null — enabled by default from Java 17+."
                    )
                    CodeBlock(
                        "String s = null;\n" +
                        "int len = s.length();   // NullPointerException!\n\n" +
                        "int[] arr = null;\n" +
                        "int n = arr.length;     // NullPointerException!\n\n" +
                        "Integer boxed = null;\n" +
                        "int primitive = boxed;  // NullPointerException (unboxing)\n\n" +
                        "// Java 14+ helpful message:\n" +
                        "// Cannot invoke \"String.length()\" because \"s\" is null"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "How You Can Get null") {
                    BodyText(
                        "null can enter your code from several sources:\n\n" +
                        "• A method explicitly returns null — common for \"not found\" results\n" +
                        "• An uninitialized instance field — class fields default to null\n" +
                        "• Map.get(key) when the key is absent — returns null, not an exception\n" +
                        "• External APIs and libraries that return null for optional results\n" +
                        "• Deserializing JSON/XML where a field was absent or explicitly null"
                    )
                    CodeBlock(
                        "// Method returning null for 'not found'\n" +
                        "public User findUser(int id) {\n" +
                        "    return database.get(id);  // null if absent\n" +
                        "}\n\n" +
                        "// Uninitialized field\n" +
                        "class Order {\n" +
                        "    String trackingNumber;  // null until set\n" +
                        "}\n\n" +
                        "// Map.get returns null on miss\n" +
                        "Map<String,String> map = new HashMap<>();\n" +
                        "String v = map.get(\"missing\");  // v is null\n" +
                        "int len = v.length();           // NPE!"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Defensive Null Checks") {
                    BodyText(
                        "The straightforward approach is to check for null before using a reference:\n\n" +
                        "Objects.requireNonNull(obj) throws NullPointerException with a clear message " +
                        "if obj is null — good for validating method arguments at the entry point " +
                        "rather than letting the NPE propagate deep into the call stack."
                    )
                    CodeBlock(
                        "// Simple null check\n" +
                        "String name = user.getName();\n" +
                        "if (name != null) {\n" +
                        "    System.out.println(name.toUpperCase());\n" +
                        "}\n\n" +
                        "// Ternary fallback\n" +
                        "String display = (name != null) ? name : \"Anonymous\";\n\n" +
                        "// Objects.requireNonNull — validate early\n" +
                        "public void save(User user) {\n" +
                        "    Objects.requireNonNull(user, \"user must not be null\");\n" +
                        "    // safe to use user below\n" +
                        "}\n\n" +
                        "// String.valueOf is null-safe (prints \"null\")\n" +
                        "System.out.println(String.valueOf(name));"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Optional<T> — Java 8+") {
                    BodyText(
                        "Optional<T> is a container that explicitly models the possibility of absence. " +
                        "Instead of returning null to signal \"no result\", a method returns " +
                        "Optional<T>. The caller is then forced (by the API) to handle the empty case.\n\n" +
                        "Creating an Optional:\n" +
                        "  Optional.of(value)          — value must not be null\n" +
                        "  Optional.ofNullable(value)  — value may be null\n" +
                        "  Optional.empty()            — explicitly empty\n\n" +
                        "Consuming an Optional:\n" +
                        "  isPresent() / isEmpty()     — check presence\n" +
                        "  get()                       — get value (throws if empty — avoid)\n" +
                        "  orElse(default)             — value or default\n" +
                        "  orElseGet(() -> compute())  — value or lazy-computed default\n" +
                        "  orElseThrow()               — value or NoSuchElementException\n" +
                        "  map(fn)                     — transform if present\n" +
                        "  ifPresent(action)           — run action if present\n\n" +
                        "Best used for return types of methods where absence is a normal outcome. " +
                        "Avoid Optional for fields or method parameters — it adds overhead without benefit."
                    )
                    CodeBlock(
                        "// Method that might not find a result\n" +
                        "public Optional<User> findById(int id) {\n" +
                        "    User u = db.get(id);\n" +
                        "    return Optional.ofNullable(u);\n" +
                        "}\n\n" +
                        "// Caller handles absence cleanly\n" +
                        "Optional<User> result = findById(42);\n\n" +
                        "result.ifPresent(u -> System.out.println(u.getName()));\n\n" +
                        "String name = result\n" +
                        "    .map(User::getName)\n" +
                        "    .orElse(\"Unknown\");\n\n" +
                        "User u = result.orElseThrow(() ->\n" +
                        "    new NotFoundException(\"User 42 not found\"));"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Annotations and Static Analysis") {
                    BodyText(
                        "@Nullable and @NonNull (or @NotNull) are annotations used to document and " +
                        "statically enforce nullability. They are not enforced by the Java compiler " +
                        "itself, but IDE tools and static analyzers use them to flag potential NPEs " +
                        "at development time.\n\n" +
                        "Common annotation sources:\n" +
                        "• JSR-305 (javax.annotation) — findbugs/spotbugs; widely used\n" +
                        "• JetBrains annotations (org.jetbrains.annotations) — used in IntelliJ projects\n" +
                        "• Android Jetpack (androidx.annotation) — used in Android development\n\n" +
                        "NullAway (from Uber) is a compile-time checker that treats @NonNull as " +
                        "a contract and fails the build when a nullable value flows into a @NonNull " +
                        "site.\n\n" +
                        "Kotlin solved this at the language level with built-in nullable types " +
                        "(String? vs String), making null handling mandatory at compile time — " +
                        "no annotations or Optional needed."
                    )
                    CodeBlock(
                        "import org.jetbrains.annotations.NotNull;\n" +
                        "import org.jetbrains.annotations.Nullable;\n\n" +
                        "public class UserService {\n\n" +
                        "    // caller guaranteed non-null return\n" +
                        "    @NotNull\n" +
                        "    public User getUser(int id) { ... }\n\n" +
                        "    // return may be null\n" +
                        "    @Nullable\n" +
                        "    public String getNickname(int id) { ... }\n\n" +
                        "    // parameter must not be null\n" +
                        "    public void save(@NotNull User user) { ... }\n" +
                        "}\n\n" +
                        "// IDE / NullAway warns:\n" +
                        "String nick = getNickname(42);\n" +
                        "nick.toLowerCase();  // warning: nick may be null"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
