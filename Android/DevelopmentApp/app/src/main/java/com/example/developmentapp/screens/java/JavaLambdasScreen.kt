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
fun JavaLambdasScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Java — Lambdas",
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
                SectionCard(title = "What Is a Lambda?") {
                    BodyText(
                        "A lambda is an anonymous function — a function with no name, written inline " +
                        "where it is used. Before Java 8, the only way to pass behavior was through an " +
                        "anonymous class, which required a lot of boilerplate. A lambda is a concise " +
                        "shorthand for implementing a single-method interface inline."
                    )
                    CodeBlock(
                        "// Before Java 8 — anonymous class (verbose)\n" +
                        "Runnable r1 = new Runnable() {\n" +
                        "    @Override\n" +
                        "    public void run() {\n" +
                        "        System.out.println(\"Hello from thread\");\n" +
                        "    }\n" +
                        "};\n\n" +
                        "// Java 8+ — lambda (concise, same result)\n" +
                        "Runnable r2 = () -> System.out.println(\"Hello from thread\");\n\n" +
                        "// Both do exactly the same thing.\n" +
                        "// A lambda is not a new concept — it is shorthand for a one-method object."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Lambda Syntax") {
                    BodyText(
                        "The syntax is: (parameters) -> body. The body is either a single expression " +
                        "(no braces, value is implicitly returned) or a block with braces (explicit " +
                        "return required). Parentheses around the parameter list are required for zero " +
                        "or two or more params, but optional for exactly one parameter."
                    )
                    CodeBlock(
                        "// No parameters\n" +
                        "Runnable r = () -> System.out.println(\"Hello\");\n\n" +
                        "// Single parameter — parens optional\n" +
                        "Transformer double1 = x -> x * 2;\n" +
                        "Transformer double2 = (x) -> x * 2;  // same thing\n\n" +
                        "// Multiple parameters — parens required\n" +
                        "Comparator<String> cmp = (a, b) -> a.compareTo(b);\n\n" +
                        "// Block body — braces + explicit return\n" +
                        "Transformer abs = x -> {\n" +
                        "    if (x < 0) return -x;\n" +
                        "    return x;\n" +
                        "};\n\n" +
                        "// Types can be declared explicitly (usually inferred by compiler)\n" +
                        "Transformer triple = (int x) -> x * 3;"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "@FunctionalInterface — Giving a Lambda a Type") {
                    BodyText(
                        "A lambda has no type of its own. It must be assigned to a functional interface — " +
                        "an interface with exactly one abstract method (SAM: Single Abstract Method). The " +
                        "lambda implements that one method. The @FunctionalInterface annotation is optional " +
                        "but tells the compiler to enforce the one-abstract-method rule and emit a clear " +
                        "error if someone accidentally adds a second abstract method to the interface.\n\n" +
                        "Runnable, Comparator, and Callable are all @FunctionalInterfaces — that is why " +
                        "lambdas work with them directly."
                    )
                    CodeBlock(
                        "@FunctionalInterface\n" +
                        "interface Transformer {\n" +
                        "    int transform(int x);  // the one abstract method\n" +
                        "    // Adding a second abstract method here would be a compile error\n" +
                        "}\n\n" +
                        "// Each lambda implements Transformer.transform(int)\n" +
                        "Transformer doubler  = x -> x * 2;\n" +
                        "Transformer negater  = x -> -x;\n" +
                        "Transformer absolute = x -> x < 0 ? -x : x;\n\n" +
                        "System.out.println(doubler.transform(5));   // 10\n" +
                        "System.out.println(negater.transform(5));   // -5\n" +
                        "System.out.println(absolute.transform(-7)); // 7\n\n" +
                        "// Runnable is a built-in @FunctionalInterface:\n" +
                        "Runnable greet = () -> System.out.println(\"Hi!\");\n" +
                        "greet.run();  // Hi!"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Does the Method Name Matter?") {
                    BodyText(
                        "The method name in a @FunctionalInterface matters at the call site — you invoke " +
                        "the lambda through that name. It does NOT matter when writing the lambda itself. " +
                        "The compiler knows which method to implement because there is exactly one abstract " +
                        "method. Two completely different interfaces with different method names will accept " +
                        "identical lambda syntax; what the name controls is only how you call the result."
                    )
                    CodeBlock(
                        "@FunctionalInterface interface Transformer { int transform(int x); }\n" +
                        "@FunctionalInterface interface Mapper      { int map(int x); }\n\n" +
                        "// Both accept the exact same lambda body — method name is irrelevant here\n" +
                        "Transformer t = x -> x * 2;\n" +
                        "Mapper      m = x -> x * 2;\n\n" +
                        "t.transform(5);  // 10 — invoked as \"transform\"\n" +
                        "m.map(5);        // 10 — invoked as \"map\"\n" +
                        "// The lambda bodies are identical; only the invocation name differs\n\n" +
                        "// The compiler infers the method purely from context:\n" +
                        "// When you write: Transformer t = x -> x * 2;\n" +
                        "// the compiler sees: Transformer has one abstract method transform(int)\n" +
                        "// so the lambda implements transform(int) — no name needed in the lambda"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Storing in Variables") {
                    BodyText(
                        "You store a lambda by declaring a variable of the functional interface type. " +
                        "The variable holds a reference to the lambda object on the heap. You can have " +
                        "multiple variables of the same interface type, each holding a different lambda — " +
                        "different implementations of the same one-method contract."
                    )
                    CodeBlock(
                        "@FunctionalInterface\n" +
                        "interface Greeter { String greet(String name); }\n\n" +
                        "Greeter formal = name -> \"Good day, \" + name;\n" +
                        "Greeter casual = name -> \"Hey \" + name + \"!\";\n" +
                        "Greeter shout  = name -> name.toUpperCase() + \"!!!\";\n\n" +
                        "System.out.println(formal.greet(\"Alice\")); // Good day, Alice\n" +
                        "System.out.println(casual.greet(\"Alice\")); // Hey Alice!\n" +
                        "System.out.println(shout.greet(\"Alice\"));  // ALICE!!!\n\n" +
                        "// Lambdas can go in arrays or lists too:\n" +
                        "Greeter[] greeters = { formal, casual, shout };\n" +
                        "for (Greeter g : greeters) System.out.println(g.greet(\"Bob\"));"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Passing as Parameters") {
                    BodyText(
                        "A method declares a functional interface parameter. The caller passes a lambda. " +
                        "This is the foundation of Java functional-style programming — behavior can be " +
                        "passed around like data. The sort() method and stream operations use exactly " +
                        "this mechanism internally."
                    )
                    CodeBlock(
                        "static void printTransformed(int value, Transformer t) {\n" +
                        "    System.out.println(t.transform(value));\n" +
                        "}\n\n" +
                        "// Pass different lambdas for different behavior:\n" +
                        "printTransformed(5, x -> x * 2);    // 10\n" +
                        "printTransformed(5, x -> x + 100);  // 105\n" +
                        "printTransformed(5, x -> x * x);    // 25\n\n" +
                        "// Real-world example — sorting with a Comparator lambda:\n" +
                        "List<String> names = new ArrayList<>(List.of(\"Charlie\", \"Alice\", \"Bob\"));\n" +
                        "Collections.sort(names, (a, b) -> a.compareTo(b));\n" +
                        "System.out.println(names);  // [Alice, Bob, Charlie]\n\n" +
                        "// Sort by string length:\n" +
                        "Collections.sort(names, (a, b) -> a.length() - b.length());"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Built-in Functional Interfaces") {
                    BodyText(
                        "You rarely need to define your own @FunctionalInterface. Java ships a set of " +
                        "these covering the common patterns. The java.util.function package has the generic " +
                        "ones; Runnable is in java.lang; Callable in java.util.concurrent; Comparator in java.util:\n\n" +
                        "Function<T,R>     — takes T, returns R          apply(T t)\n" +
                        "Consumer<T>       — takes T, returns nothing     accept(T t)\n" +
                        "Supplier<T>       — takes nothing, returns T     get()\n" +
                        "Predicate<T>      — takes T, returns boolean     test(T t)\n" +
                        "BiFunction<T,U,R> — takes T+U, returns R        apply(T t, U u)\n" +
                        "Runnable          — takes nothing, returns void  run()         [java.lang]\n" +
                        "Callable<V>       — takes nothing, returns V     call()        [j.u.concurrent]\n" +
                        "Comparator<T>     — takes two T, returns int     compare(T,T)  [java.util]"
                    )
                    CodeBlock(
                        "import java.util.function.*;\n\n" +
                        "Function<String, Integer> length = s -> s.length();\n" +
                        "Consumer<String>          print  = s -> System.out.println(s);\n" +
                        "Supplier<String>          hello  = () -> \"Hello!\";\n" +
                        "Predicate<Integer>        isEven = n -> n % 2 == 0;\n" +
                        "BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;\n\n" +
                        "System.out.println(length.apply(\"hello\")); // 5\n" +
                        "print.accept(\"world\");                      // world\n" +
                        "System.out.println(hello.get());             // Hello!\n" +
                        "System.out.println(isEven.test(4));          // true\n" +
                        "System.out.println(add.apply(3, 7));         // 10\n\n" +
                        "// Runnable — void run() — no input, no output [java.lang, auto-imported]\n" +
                        "Runnable task = () -> System.out.println(\"Running!\");\n" +
                        "new Thread(task).start();  // most common use\n" +
                        "task.run();                // or call directly\n\n" +
                        "// Callable<V> — like Supplier but can throw a checked exception\n" +
                        "import java.util.concurrent.*;\n" +
                        "Callable<String> fetch = () -> \"data from network\";\n" +
                        "ExecutorService ex = Executors.newSingleThreadExecutor();\n" +
                        "Future<String> f = ex.submit(fetch);\n" +
                        "System.out.println(f.get());  // \"data from network\"\n\n" +
                        "// Comparator<T> — used everywhere sorting is needed\n" +
                        "Comparator<String> byLen = (a, b) -> a.length() - b.length();\n" +
                        "List<String> words = new ArrayList<>(List.of(\"banana\", \"fig\", \"apple\"));\n" +
                        "words.sort(byLen);\n" +
                        "System.out.println(words);  // [fig, apple, banana]\n\n" +
                        "Comparator<String> byLen2 = Comparator.comparingInt(String::length);\n" +
                        "Comparator<String> rev    = byLen2.reversed();"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Generic Functional Interfaces") {
                    BodyText(
                        "A lambda itself cannot have a type parameter — there is no syntax for a free-" +
                        "standing generic lambda. However, the functional interface can be generic, and " +
                        "you instantiate it with a specific type. The lambda then implements that specific " +
                        "version. This is exactly how Function<T,R>, Predicate<T>, and all the built-in " +
                        "interfaces work — they are generic interfaces instantiated at the use site."
                    )
                    CodeBlock(
                        "@FunctionalInterface\n" +
                        "interface Box<T> { T wrap(T value); }\n\n" +
                        "// Instantiate with a specific type — lambda sees T as that type\n" +
                        "Box<String>  strBox = x -> x.toUpperCase(); // x is String\n" +
                        "Box<Integer> intBox = x -> x * 2;           // x is Integer\n\n" +
                        "System.out.println(strBox.wrap(\"hello\")); // HELLO\n" +
                        "System.out.println(intBox.wrap(5));         // 10\n\n" +
                        "// Built-in interfaces are exactly this pattern:\n" +
                        "Function<String, Integer> len   = s -> s.length(); // T=String, R=Integer\n" +
                        "Function<Integer, Boolean> even = n -> n % 2 == 0; // T=Integer, R=Boolean\n\n" +
                        "// A method can take a generic functional interface:\n" +
                        "static <T> T applyTwice(Box<T> box, T value) {\n" +
                        "    return box.wrap(box.wrap(value));\n" +
                        "}\n" +
                        "System.out.println(applyTwice(strBox, \"hi\"));  // HIHI\n" +
                        "System.out.println(applyTwice(intBox, 3));      // 12  (3*2=6, 6*2=12)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Closures — Captured Variables Must Be Effectively Final") {
                    BodyText(
                        "A lambda can read variables from its enclosing scope — this is called a closure. " +
                        "However, those variables must be effectively final: either declared final, or " +
                        "never reassigned after being initialized. You do not need to write the final " +
                        "keyword; the compiler checks this automatically.\n\n" +
                        "Why the restriction? A lambda is a heap object that outlives the stack frame " +
                        "that created it. Java captures a snapshot of the variable (its value or " +
                        "reference) into the lambda object at creation time. If you were allowed to " +
                        "reassign the variable afterward, the lambda's snapshot would silently diverge " +
                        "from it — a hard-to-find bug. The effectively-final rule prevents this entirely."
                    )
                    CodeBlock(
                        "@FunctionalInterface interface Transformer { int transform(int x); }\n\n" +
                        "int multiplier = 3;  // never reassigned → effectively final → OK\n" +
                        "Transformer t = x -> x * multiplier;  // captures the value 3\n" +
                        "System.out.println(t.transform(5));   // 15\n\n" +
                        "// ── What breaks the rule ──────────────────────────────────\n" +
                        "int counter = 0;\n" +
                        "counter = 1;  // reassignment — counter is NO LONGER effectively final\n" +
                        "// Runnable r = () -> System.out.println(counter);  // COMPILE ERROR\n\n" +
                        "// ── Tricky case: for-loop variable ────────────────────────\n" +
                        "for (int i = 0; i < 3; i++) {\n" +
                        "    // Runnable r = () -> System.out.println(i);  // COMPILE ERROR\n" +
                        "    // i is modified each iteration — NOT effectively final\n" +
                        "    final int copy = i;  // copy is fixed for this iteration → OK\n" +
                        "    Runnable r = () -> System.out.println(copy);\n" +
                        "    r.run();  // prints 0, then 1, then 2\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "What Is Stored in the Closure?") {
                    BodyText(
                        "The closure stores a snapshot of the captured variable at the moment the lambda " +
                        "is created:\n\n" +
                        "Primitive (int, double, etc.): the value itself is copied into the lambda " +
                        "object. The lambda gets its own copy of the value.\n\n" +
                        "Object reference (String, List, any class): the reference value (the memory " +
                        "address) is copied into the lambda object. The lambda cannot rebind the " +
                        "reference — it cannot make the variable point to a different object. But it " +
                        "CAN mutate the object that the reference points to, because both the lambda " +
                        "and the outer code hold pointers to the same heap object."
                    )
                    CodeBlock(
                        "// Primitive: value is copied into the lambda\n" +
                        "int base = 10;\n" +
                        "Supplier<Integer> getBase = () -> base;  // lambda stores the value 10\n" +
                        "System.out.println(getBase.get());  // 10\n\n" +
                        "// Object reference: the address is copied — mutation is visible outside\n" +
                        "List<String> names = new ArrayList<>();\n" +
                        "Runnable adder = () -> names.add(\"Alice\");  // lambda stores the reference\n" +
                        "adder.run();\n" +
                        "System.out.println(names);  // [Alice] — mutation visible outside lambda\n\n" +
                        "// Cannot rebind the reference from inside the lambda:\n" +
                        "// Runnable bad = () -> { names = new ArrayList<>(); };  // COMPILE ERROR\n" +
                        "// (rebinding = reassignment = breaks effectively-final rule)\n\n" +
                        "// String is immutable — concat creates a new object, does not mutate:\n" +
                        "String prefix = \"Hello\";\n" +
                        "Function<String, String> greet = name -> prefix + \", \" + name;\n" +
                        "System.out.println(greet.apply(\"Bob\"));  // Hello, Bob"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Returning a Lambda — GC and Local Variables") {
                    BodyText(
                        "Yes — you can safely return a lambda that captures a local variable. A local " +
                        "variable lives on the stack and is normally gone when the method returns. But " +
                        "the lambda is a heap object. When it captures a local variable, the captured " +
                        "value (or reference) becomes a field inside the lambda object on the heap. " +
                        "The stack slot disappears, but the lambda object — and everything it " +
                        "references — stays alive as long as something holds a reference to the lambda.\n\n" +
                        "In the case of Animal a = new Dog(), the Dog is on the heap. The lambda " +
                        "captures the reference to the Dog and stores it as a field in the lambda " +
                        "object. After the method returns, the stack slot a is gone, but the lambda " +
                        "still points to the Dog — so the GC sees the Dog as reachable and will NOT " +
                        "collect it. This is the fundamental contract of Java closures."
                    )
                    CodeBlock(
                        "@FunctionalInterface interface Describer { String describe(); }\n\n" +
                        "static class Animal { String name; Animal(String n) { name = n; } }\n" +
                        "static class Dog extends Animal  { Dog(String n) { super(n); } }\n\n" +
                        "static Describer makeDescriber() {\n" +
                        "    Animal a = new Dog(\"Rex\");  // 'a' is a local stack variable\n" +
                        "    return () -> \"Animal: \" + a.name;\n" +
                        "    // The lambda is a heap object with 'a' captured as a field.\n" +
                        "    // When makeDescriber() returns, the stack frame vanishes — 'a' is gone.\n" +
                        "    // But the lambda object on the heap still holds the Dog's reference.\n" +
                        "    // GC sees: (lambda) → Dog — Dog is reachable, NOT collected.\n" +
                        "}\n\n" +
                        "Describer d = makeDescriber();       // caller holds the lambda\n" +
                        "System.out.println(d.describe());   // Animal: Rex — Dog still alive!\n\n" +
                        "d = null;  // nothing holds the lambda anymore\n" +
                        "// GC can now collect both the lambda object AND the Dog"
                    )
                    BodyText(
                        "This is why Java closures are safe to return: the captured data becomes part " +
                        "of the lambda object's lifetime on the heap, not the stack frame's lifetime."
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
