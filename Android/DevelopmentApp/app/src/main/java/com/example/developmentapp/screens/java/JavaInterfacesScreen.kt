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
fun JavaInterfacesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Java — Interfaces",
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
                SectionCard(title = "What Is an Interface?") {
                    BodyText(
                        "An interface is a named contract — a set of method signatures that a class " +
                        "promises to implement. It defines behavior (\"what the class can do\") without " +
                        "specifying implementation (\"how it does it\"). Think of it as a pure abstract " +
                        "class in C++ where every method is abstract and there is no instance state. " +
                        "Java 8 added default methods with bodies, but the core idea remains: an " +
                        "interface is a promise, not an implementation."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Defining an Interface") {
                    BodyText(
                        "Use the interface keyword. Methods inside are implicitly public and abstract — " +
                        "no body, no access modifier needed. Fields are implicitly public static final " +
                        "(constants). No constructor — interfaces cannot be instantiated directly."
                    )
                    CodeBlock(
                        "interface Drawable {\n" +
                        "    void draw();        // implicitly public abstract\n" +
                        "    double area();      // implicitly public abstract\n" +
                        "}\n\n" +
                        "interface Resizable {\n" +
                        "    void resize(double factor);\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "implements") {
                    BodyText(
                        "A class commits to an interface with implements. The compiler enforces that " +
                        "every interface method is given a body (or the class must be declared abstract). " +
                        "Use @Override on each implementation — same rules as overriding a parent method."
                    )
                    CodeBlock(
                        "class Circle implements Drawable {\n" +
                        "    private double radius;\n" +
                        "    Circle(double r) { this.radius = r; }\n\n" +
                        "    @Override\n" +
                        "    public void draw() {\n" +
                        "        System.out.println(\"Circle r=\" + radius);\n" +
                        "    }\n" +
                        "    @Override\n" +
                        "    public double area() {\n" +
                        "        return Math.PI * radius * radius;\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Multiple Interfaces") {
                    BodyText(
                        "A class can implement any number of interfaces — list them comma-separated " +
                        "after implements. This is how Java compensates for the lack of multiple class " +
                        "inheritance. A class can also extend one parent AND implement interfaces at " +
                        "the same time. All interface methods must still be implemented."
                    )
                    CodeBlock(
                        "interface Drawable   { void draw(); }\n" +
                        "interface Resizable  { void resize(double f); }\n" +
                        "interface Saveable   { void save(); }\n\n" +
                        "class Icon extends Widget implements Drawable, Resizable, Saveable {\n" +
                        "    @Override public void draw()            { ... }\n" +
                        "    @Override public void resize(double f)  { ... }\n" +
                        "    @Override public void save()            { ... }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Interface as Type / Passing as Parameter") {
                    BodyText(
                        "An interface name is a valid type. You can store any implementing object by " +
                        "the interface type, and pass it to any method that accepts that interface. " +
                        "The actual implementation called at runtime depends on the real object type — " +
                        "the same polymorphism mechanism as with class inheritance."
                    )
                    CodeBlock(
                        "Drawable d = new Circle(5.0);   // Circle implements Drawable\n" +
                        "d.draw();                       // calls Circle.draw()\n\n" +
                        "// Method that works with ANY Drawable:\n" +
                        "void render(Drawable shape) {\n" +
                        "    shape.draw();\n" +
                        "    System.out.println(\"area: \" + shape.area());\n" +
                        "}\n\n" +
                        "render(new Circle(3.0));\n" +
                        "render(new Rectangle(4, 6));    // also implements Drawable"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Default Methods (Java 8+)") {
                    BodyText(
                        "An interface can provide a default method body using the default keyword. " +
                        "Implementing classes inherit the default and can optionally override it. " +
                        "This allows adding new methods to an interface without forcing every existing " +
                        "implementation to be updated — a major improvement for library evolution."
                    )
                    CodeBlock(
                        "interface Drawable {\n" +
                        "    void draw();\n" +
                        "    double area();\n\n" +
                        "    default void describe() {              // has a body\n" +
                        "        System.out.println(\"area: \" + area());\n" +
                        "    }\n" +
                        "}\n\n" +
                        "class Circle implements Drawable {\n" +
                        "    @Override public void draw()   { ... }\n" +
                        "    @Override public double area() { return Math.PI * r * r; }\n" +
                        "    // describe() is inherited from the interface — no override needed\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Constants in Interfaces") {
                    BodyText(
                        "Fields in an interface are implicitly public static final. They are " +
                        "compile-time constants shared by the whole program, not per-instance state. " +
                        "No instance fields are allowed in interfaces."
                    )
                    CodeBlock(
                        "interface Config {\n" +
                        "    int MAX_SIZE    = 100;   // implicitly public static final\n" +
                        "    String VERSION  = \"1.0\";\n" +
                        "}\n\n" +
                        "// Access without an instance:\n" +
                        "System.out.println(Config.MAX_SIZE);   // 100\n" +
                        "System.out.println(Config.VERSION);    // 1.0"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Common Java Interfaces") {
                    BodyText(
                        "Comparable<T>   — compareTo(T o): enables natural ordering; implemented by " +
                        "String, Integer, etc.; used by Collections.sort()\n\n" +
                        "Iterable<T>     — iterator(): enables for-each loop; implemented by all " +
                        "standard collections (ArrayList, HashSet, etc.)\n\n" +
                        "Runnable        — run(): a task with no return value; passed to Thread or " +
                        "ExecutorService\n\n" +
                        "Callable<T>     — call(): a task that returns a value and can throw checked " +
                        "exceptions; used with ExecutorService.submit()\n\n" +
                        "AutoCloseable   — close(): enables try-with-resources; compiler inserts " +
                        "close() call automatically when the block exits\n\n" +
                        "Serializable    — marker interface (no methods); signals that objects of the " +
                        "class can be serialized to a byte stream\n\n" +
                        "Cloneable       — marker interface; signals that Object.clone() may be called"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
