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
fun JavaClasses2Screen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Java — Classes: Part 2",
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
                SectionCard(title = "Inheritance — extends") {
                    BodyText(
                        "\"class Dog extends Animal\" means Dog IS-A Animal. Dog inherits all " +
                        "non-private fields and methods from Animal. Anywhere an Animal is expected, " +
                        "a Dog can be used. The class being extended is called the superclass (or " +
                        "parent); the extending class is the subclass (or child)."
                    )
                    CodeBlock(
                        "class Animal {\n" +
                        "    String name;\n" +
                        "    void eat() { System.out.println(name + \" is eating\"); }\n" +
                        "}\n\n" +
                        "class Dog extends Animal {      // Dog IS-A Animal\n" +
                        "    void bark() { System.out.println(\"Woof!\"); }\n" +
                        "}\n\n" +
                        "Dog d = new Dog();\n" +
                        "d.name = \"Rex\";   // inherited field — no redeclaration needed\n" +
                        "d.eat();           // inherited method\n" +
                        "d.bark();          // Dog's own method"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The super Keyword") {
                    BodyText(
                        "super.method() calls the parent class's version of a method. super(args) in " +
                        "a constructor delegates to the parent constructor and must be the very first " +
                        "statement — the compiler enforces this. If you do not write super(...) yourself, " +
                        "the compiler inserts a zero-argument super() call automatically; if the parent " +
                        "has no no-arg constructor, you must call super(...) explicitly."
                    )
                    CodeBlock(
                        "class Animal {\n" +
                        "    String name;\n" +
                        "    Animal(String name) { this.name = name; }\n" +
                        "    void speak() { System.out.println(name + \" makes a sound\"); }\n" +
                        "}\n\n" +
                        "class Dog extends Animal {\n" +
                        "    String breed;\n" +
                        "    Dog(String name, String breed) {\n" +
                        "        super(name);          // must be first — calls Animal(name)\n" +
                        "        this.breed = breed;\n" +
                        "    }\n" +
                        "    @Override\n" +
                        "    void speak() {\n" +
                        "        super.speak();        // calls Animal.speak() first\n" +
                        "        System.out.println(name + \" also barks!\");\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "@Override") {
                    BodyText(
                        "@Override is an annotation placed above a method that replaces a parent's " +
                        "implementation. It tells the compiler: this method must match a method " +
                        "signature in some ancestor. If no matching method exists anywhere up the " +
                        "hierarchy, the compiler reports an error. Always use @Override when overriding " +
                        "— it documents intent and catches typos before runtime."
                    )
                    CodeBlock(
                        "class Animal {\n" +
                        "    void speak() { System.out.println(\"...\"); }\n" +
                        "}\n\n" +
                        "class Dog extends Animal {\n" +
                        "    @Override\n" +
                        "    void speak() { System.out.println(\"Woof!\"); }  // OK\n\n" +
                        "    @Override\n" +
                        "    void speakk() { ... }  // COMPILE ERROR — no 'speakk' in Animal\n" +
                        "                           // catches the typo immediately\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Overloading vs Overriding") {
                    BodyText(
                        "Overloading: same method name, different parameter list. The compiler picks " +
                        "the right version at compile time based on the argument types (static dispatch). " +
                        "Return type alone does not distinguish overloads — that is a compile error.\n\n" +
                        "Overriding: same method name AND same parameter list in a subclass. The " +
                        "runtime picks the right version based on the actual object type (dynamic " +
                        "dispatch). This is what @Override annotates."
                    )
                    CodeBlock(
                        "// OVERLOADING — resolved at compile time:\n" +
                        "class Calc {\n" +
                        "    int    add(int a, int b)       { return a + b; }\n" +
                        "    double add(double a, double b) { return a + b; }\n" +
                        "    // double add(int a, int b)    ← COMPILE ERROR: same params\n" +
                        "}\n\n" +
                        "// OVERRIDING — resolved at runtime:\n" +
                        "class Animal { void speak() { System.out.println(\"...\"); } }\n" +
                        "class Cat extends Animal {\n" +
                        "    @Override\n" +
                        "    void speak() { System.out.println(\"Meow\"); }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "No Multiple Inheritance") {
                    BodyText(
                        "Java does not allow a class to extend more than one class. " +
                        "\"class C extends A, B\" is a compile error. This avoids the diamond problem: " +
                        "if both A and B define a method X, which version does C inherit? Java resolves " +
                        "this by forbidding it entirely. A class can only have one parent in its " +
                        "inheritance chain. Multiple interfaces (see the Interfaces screen) address the " +
                        "need for multiple contracts without this ambiguity."
                    )
                    CodeBlock(
                        "class A { void hello() { System.out.println(\"A\"); } }\n" +
                        "class B { void hello() { System.out.println(\"B\"); } }\n\n" +
                        "// class C extends A, B { }   ← COMPILE ERROR\n" +
                        "//   Which hello() does C get? Java refuses to decide.\n\n" +
                        "// Only single inheritance is allowed:\n" +
                        "class C extends A { }   // OK — C gets A's hello()"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Polymorphism — All Methods Are Virtual") {
                    BodyText(
                        "Because Dog extends Animal, you can store a Dog in an Animal reference. " +
                        "When you call a.speak(), the JVM looks at the actual runtime object (a Dog), " +
                        "not the reference type (Animal), and calls Dog's speak(). This runtime " +
                        "dispatch is called polymorphism.\n\n" +
                        "In Java, ALL non-static, non-private, non-final instance methods are virtual " +
                        "by default. Unlike C++, you do not need any keyword — every overridable " +
                        "method automatically participates in dynamic dispatch."
                    )
                    CodeBlock(
                        "class Animal { void speak() { System.out.println(\"...\"); } }\n" +
                        "class Dog extends Animal {\n" +
                        "    @Override void speak() { System.out.println(\"Woof!\"); }\n" +
                        "}\n" +
                        "class Cat extends Animal {\n" +
                        "    @Override void speak() { System.out.println(\"Meow\"); }\n" +
                        "}\n\n" +
                        "Animal a = new Dog();   // Dog IS-A Animal → valid\n" +
                        "a.speak();             // → \"Woof!\"  (runtime type = Dog)\n\n" +
                        "Animal[] animals = { new Dog(), new Cat() };\n" +
                        "for (Animal x : animals) {\n" +
                        "    x.speak();         // each calls its own implementation\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "final for Classes and Methods") {
                    BodyText(
                        "Marking a class final prevents any subclass from extending it. java.lang.String " +
                        "and all primitive wrapper classes (Integer, Double, etc.) are final — you " +
                        "cannot extend them.\n\n" +
                        "Marking a method final in a class prevents any subclass from overriding it. " +
                        "The compiler enforces both — attempting to extend a final class or override a " +
                        "final method is a compile error."
                    )
                    CodeBlock(
                        "final class Immutable {       // no class may extend Immutable\n" +
                        "    // ...\n" +
                        "}\n" +
                        "// class Sub extends Immutable {}  ← COMPILE ERROR\n\n" +
                        "class Base {\n" +
                        "    final void locked() { System.out.println(\"locked\"); }\n" +
                        "}\n" +
                        "class Child extends Base {\n" +
                        "    @Override\n" +
                        "    void locked() { ... }   // COMPILE ERROR — method is final\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "final References Are Not const") {
                    BodyText(
                        "final on a reference variable means you cannot rebind the variable to a " +
                        "different object. It does NOT mean the object itself is immutable. You can " +
                        "still call any method on it and change its fields — final only locks the " +
                        "reference, not the object.\n\n" +
                        "This is fundamentally different from C++'s const, which prevents mutation " +
                        "through the reference. Java has no const qualifier. If you need a truly " +
                        "immutable object, the class itself must be designed to be immutable " +
                        "(private final fields, no setters, defensive copies)."
                    )
                    CodeBlock(
                        "final Animal a = new Dog(\"Rex\");\n\n" +
                        "a.setName(\"Buddy\");   // OK — object's internal state is changed\n" +
                        "a.speak();             // OK — any method can be called\n\n" +
                        "a = new Cat();         // COMPILE ERROR — can't rebind a final reference\n\n" +
                        "// C++ contrast (not Java):\n" +
                        "// const Animal* a = new Dog();  // can't call non-const methods either\n" +
                        "// Java final has no such restriction — only rebinding is forbidden."
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Abstract Classes") {
                    BodyText(
                        "Mark a class abstract when it should never be instantiated on its own — " +
                        "only its concrete subclasses make sense. This is Java's equivalent of a C++ " +
                        "class with at least one pure-virtual method.\n\n" +
                        "Rules:\n" +
                        "• A method declared abstract has no body; every concrete subclass must override it.\n" +
                        "• If a class has any abstract method, the class itself must be abstract.\n" +
                        "• An abstract class can also contain fully implemented methods — only the ones " +
                        "that subclasses must override need the abstract keyword.\n" +
                        "• A subclass that doesn't implement all abstract methods must itself be abstract.\n\n" +
                        "C++ equivalent: virtual void draw() = 0; is a pure virtual method — same idea, " +
                        "different syntax. In C++ you don't have to mark the class separately; in Java " +
                        "the class keyword must carry abstract."
                    )
                    CodeBlock(
                        "abstract class Shape {\n" +
                        "    // Abstract method — no body, must be overridden\n" +
                        "    abstract double area();\n\n" +
                        "    // Concrete method — inherited as-is, no override required\n" +
                        "    void describe() {\n" +
                        "        System.out.println(\"Area: \" + area());\n" +
                        "    }\n" +
                        "}\n\n" +
                        "class Circle extends Shape {\n" +
                        "    double radius;\n" +
                        "    Circle(double r) { radius = r; }\n\n" +
                        "    @Override\n" +
                        "    double area() { return Math.PI * radius * radius; }\n" +
                        "}\n\n" +
                        "// Shape s = new Shape();  // COMPILE ERROR — abstract class\n" +
                        "Shape s = new Circle(5);  // OK — concrete subclass\n" +
                        "s.describe();             // prints \"Area: 78.53...\"\n\n" +
                        "// C++ pure virtual equivalent:\n" +
                        "// virtual double area() = 0;"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
