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
fun JavaClasses101Screen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Java — Classes 101",
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
                SectionCard(title = "What Is a Class?") {
                    BodyText(
                        "A class is a blueprint that defines what an object looks like (its data) and " +
                        "what it can do (its behavior). Member variables (also called fields or instance " +
                        "variables) hold the data. Methods define the behavior.\n\n" +
                        "When you create an object from a class — an instance — that object gets its " +
                        "own private copy of every instance variable. Two Dog objects each have their " +
                        "own name and age; changing one does not affect the other."
                    )
                    CodeBlock(
                        "class Dog {\n" +
                        "    String name;   // each Dog has its own name\n" +
                        "    int    age;    // each Dog has its own age\n\n" +
                        "    void bark() {\n" +
                        "        System.out.println(name + \" says: Woof!\");\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The this Keyword — The Hidden Argument") {
                    BodyText(
                        "When you call rex.bark(), the JVM secretly passes rex as a hidden first " +
                        "argument to bark. Inside the method, that hidden argument is available as " +
                        "this — a reference to the current instance the method was called on. Other " +
                        "languages call this convention a \"thiscall\".\n\n" +
                        "Through this, a method can read or write any field of the current instance " +
                        "and call any other instance method on it. When there is no name ambiguity, " +
                        "Java resolves field names automatically and this. is optional. It becomes " +
                        "required when a parameter or local variable shadows a field."
                    )
                    CodeBlock(
                        "class Dog {\n" +
                        "    String name;\n" +
                        "    int    age;\n\n" +
                        "    // this.name required — parameter 'name' shadows the field\n" +
                        "    void setName(String name) {\n" +
                        "        this.name = name;  // this.name = field, name = parameter\n" +
                        "    }\n\n" +
                        "    // no ambiguity — 'age' resolves to the field automatically\n" +
                        "    void birthday() {\n" +
                        "        age++;             // same as this.age++\n" +
                        "    }\n\n" +
                        "    void describe() {\n" +
                        "        System.out.println(this.name + \" is \" + this.age);\n" +
                        "        this.birthday();   // calling another instance method via this\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Methods — Parameters and Return Values") {
                    BodyText(
                        "A method declares a return type before its name. void means it returns " +
                        "nothing. If the return type is not void, every code path must end with a " +
                        "return value; statement — the compiler enforces this.\n\n" +
                        "A void method may use a bare return; to exit early but does not have to — " +
                        "execution simply falls off the end of the method body."
                    )
                    CodeBlock(
                        "int add(int a, int b) {\n" +
                        "    return a + b;      // must return int — all paths do\n" +
                        "}\n\n" +
                        "double divide(double a, double b) {\n" +
                        "    if (b == 0) return 0;   // early return for error case\n" +
                        "    return a / b;\n" +
                        "}\n\n" +
                        "void greet(String who) {\n" +
                        "    if (who == null) return;  // early exit, no value needed\n" +
                        "    System.out.println(\"Hello, \" + who);\n" +
                        "    // implicit return here — void method, no keyword required\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Variable Scope") {
                    BodyText(
                        "A variable exists only within the block {} it was declared in. Method " +
                        "parameters are scoped to the entire method body. A variable declared inside " +
                        "an if or for block vanishes after that block closes. Instance variables " +
                        "(fields) are accessible from any method in the class."
                    )
                    CodeBlock(
                        "void example(int param) {       // param: scoped to whole method\n" +
                        "    int x = 10;                 // x: scoped to this method\n\n" +
                        "    if (x > 5) {\n" +
                        "        int y = 20;             // y: scoped to this if-block only\n" +
                        "        System.out.println(y);  // OK\n" +
                        "    }\n\n" +
                        "    // System.out.println(y);   // COMPILE ERROR — y out of scope\n" +
                        "    System.out.println(x);      // fine — x still in scope\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Method Overloading") {
                    BodyText(
                        "Java allows multiple methods with the same name as long as their parameter " +
                        "lists differ — different count or different types. The compiler picks the " +
                        "right version at compile time based on the arguments you pass. This is called " +
                        "static dispatch. The return type alone is not enough to distinguish overloads."
                    )
                    CodeBlock(
                        "void print(int n)           { System.out.println(\"int: \" + n); }\n" +
                        "void print(String s)        { System.out.println(\"str: \" + s); }\n" +
                        "void print(int n, String s) { System.out.println(n + \" \" + s); }\n\n" +
                        "print(5);          // → print(int)\n" +
                        "print(\"hi\");       // → print(String)\n" +
                        "print(3, \"dogs\");  // → print(int, String)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Access Modifiers — public, private, protected, default") {
                    BodyText(
                        "Java has four levels of visibility for fields and methods:\n\n" +
                        "public    — accessible from anywhere.\n" +
                        "protected — same class, same package, and subclasses (even in other packages).\n" +
                        "default   — no keyword written; same class and same package only.\n" +
                        "private   — same class only. The strongest encapsulation.\n\n" +
                        "Is there a friend keyword like C++?\n" +
                        "No. Java has no friend keyword — you cannot grant one specific foreign class " +
                        "access to your private members. The closest equivalent is default (package-" +
                        "private) access: any class in the same package can reach each other's default " +
                        "members. Inner/nested classes are another option — an inner class can access " +
                        "its outer class's private members."
                    )
                    CodeBlock(
                        "// package: animals\n" +
                        "public class Dog {\n" +
                        "    public    String name;       // anyone can access\n" +
                        "    protected int    age;        // same package + subclasses\n" +
                        "              int    weight;     // package-private\n" +
                        "    private   String secretFood; // Dog methods only\n\n" +
                        "    public    void bark()  { System.out.println(name + \": Woof!\"); }\n" +
                        "    protected void fetch() { System.out.println(\"fetching...\"); }\n" +
                        "              void sleep() { System.out.println(\"zzz\"); }\n" +
                        "    private   void scratch(){ System.out.println(\"scratch\"); }\n" +
                        "}\n\n" +
                        "// package: zoo  (different package, not a subclass)\n" +
                        "public class ZooKeeper {\n" +
                        "    public void care(Dog dog) {\n" +
                        "        System.out.println(dog.name);  // OK — public\n" +
                        "        dog.bark();                    // OK — public\n\n" +
                        "        // dog.age = 5;               // ERROR — protected\n" +
                        "        // dog.weight = 30;           // ERROR — package-private\n" +
                        "        // dog.secretFood = \"bone\";  // ERROR — private\n" +
                        "        // dog.fetch();               // ERROR — protected\n" +
                        "        // dog.sleep();               // ERROR — package-private\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Static Members — Per Class, Not Per Instance") {
                    BodyText(
                        "The static keyword means the member belongs to the class itself, not to any " +
                        "individual instance.\n\n" +
                        "A static field has exactly one copy shared by all objects of that class.\n\n" +
                        "A static method is not called on an instance — it is called on the class " +
                        "(Counter.getTotal()). Because there is no instance involved, the JVM does not " +
                        "pass a hidden this argument. This means a static method cannot access instance " +
                        "variables or call instance methods — it simply has no this to work with.\n\n" +
                        "Static fields are initialized when the JVM first loads the class — before any " +
                        "instance is ever created. Static initializer blocks (static { ... }) run at " +
                        "that same moment."
                    )
                    CodeBlock(
                        "class Counter {\n" +
                        "    static int totalCreated = 0;  // one copy, shared by all instances\n" +
                        "    int id;                        // each instance has its own id\n\n" +
                        "    Counter() {\n" +
                        "        totalCreated++;            // static field — accessible anywhere\n" +
                        "        id = totalCreated;\n" +
                        "    }\n\n" +
                        "    static int getTotal() {\n" +
                        "        return totalCreated;       // OK — static accessing static\n" +
                        "        // return id;              // COMPILE ERROR — id is an instance\n" +
                        "                                   // field; there is no 'this' here\n" +
                        "    }\n" +
                        "}\n\n" +
                        "Counter a = new Counter();               // totalCreated=1, a.id=1\n" +
                        "Counter b = new Counter();               // totalCreated=2, b.id=2\n" +
                        "System.out.println(Counter.getTotal());  // 2 — called on the class"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The main Method — A Static Entry Point") {
                    BodyText(
                        "The JVM starts your program by calling main. Its signature must be:\n\n" +
                        "    public static void main(String[] args)\n\n" +
                        "Why static? The JVM needs to call main before any instance of your class " +
                        "exists. If it were an instance method, the JVM would have to create an object " +
                        "first — but which constructor? With what arguments? Making it static solves " +
                        "this cleanly: the JVM calls ClassName.main(args) directly on the class, no " +
                        "instance needed.\n\n" +
                        "Why public? So the JVM, which runs outside your package, can reach it.\n\n" +
                        "Return type void — main returns nothing to the JVM. To signal an exit code " +
                        "to the OS, call System.exit(code).\n\n" +
                        "String[] args — command-line arguments as strings. Always an array; empty " +
                        "(not null) if no arguments were passed."
                    )
                    CodeBlock(
                        "public class MyApp {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        // java MyApp hello world  →  args = [\"hello\", \"world\"]\n" +
                        "        // no arguments passed      →  args = []  (empty, never null)\n\n" +
                        "        System.out.println(\"Arguments: \" + args.length);\n" +
                        "        for (String arg : args) {\n" +
                        "            System.out.println(arg);\n" +
                        "        }\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Constructors") {
                    BodyText(
                        "A constructor is a special method that runs automatically when new creates " +
                        "an instance. It has the same name as the class and no return type — not even " +
                        "void. Like regular methods, constructors can be overloaded. One constructor " +
                        "can delegate to another using this(args), which must be the first statement.\n\n" +
                        "Default constructor rule: if you declare no constructors at all, the compiler " +
                        "provides a no-arg constructor for free. The moment you declare any constructor, " +
                        "the compiler stops providing the default — add it explicitly if you still need it.\n\n" +
                        "Copy constructors: Java has no built-in copy constructor and no automatic " +
                        "default copy constructor (unlike C++). Write one manually if you need it. " +
                        "Java code more commonly uses clone() or copy factory methods.\n\n" +
                        "Destructors: Java has no destructors (unlike C++ or C#). Memory is reclaimed " +
                        "by the garbage collector automatically — you never call delete. For resources " +
                        "needing explicit cleanup (files, sockets), implement AutoCloseable and use " +
                        "try-with-resources; the JVM calls close() when the block exits. The old " +
                        "finalize() method was deprecated in Java 9 and removed in Java 18 — do not use it."
                    )
                    CodeBlock(
                        "class Dog {\n" +
                        "    String name;\n" +
                        "    int    age;\n\n" +
                        "    // Primary constructor\n" +
                        "    Dog(String name, int age) {\n" +
                        "        this.name = name;\n" +
                        "        this.age  = age;\n" +
                        "    }\n\n" +
                        "    // Overloaded — delegates to primary\n" +
                        "    Dog(String name) {\n" +
                        "        this(name, 0);   // must be first statement\n" +
                        "    }\n\n" +
                        "    // No-arg — added explicitly (we defined other constructors)\n" +
                        "    Dog() {\n" +
                        "        this(\"Unknown\", 0);\n" +
                        "    }\n\n" +
                        "    // Manual copy constructor\n" +
                        "    Dog(Dog other) {\n" +
                        "        this(other.name, other.age);\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Creating an Instance with new") {
                    BodyText(
                        "new ClassName(args) allocates a fresh object on the heap, calls the matching " +
                        "constructor, and returns a reference to it. The variable on the left holds " +
                        "that reference — a pointer to the heap object, not the object itself.\n\n" +
                        "Two variables can hold references to the same object. Assigning one variable " +
                        "to another copies the reference, not the object."
                    )
                    CodeBlock(
                        "Dog rex   = new Dog(\"Rex\", 3);   // calls Dog(String, int)\n" +
                        "Dog puppy = new Dog(\"Buddy\");    // calls Dog(String)\n" +
                        "Dog anon  = new Dog();            // calls Dog()\n" +
                        "Dog copy  = new Dog(rex);         // manual copy constructor\n\n" +
                        "rex.bark();    // calls bark() on the rex instance\n" +
                        "puppy.bark();  // calls bark() on the puppy instance — different output\n\n" +
                        "Dog alias = rex;    // alias and rex point to the SAME object\n" +
                        "alias.age = 4;      // rex.age is now 4 too"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
