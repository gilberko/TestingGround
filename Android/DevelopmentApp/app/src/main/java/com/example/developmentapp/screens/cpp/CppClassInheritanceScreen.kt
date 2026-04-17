package com.example.developmentapp.screens.cpp

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
fun CppClassInheritanceScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "C/C++ — Class Inheritance",
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
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Basic Inheritance") {
                    BodyText("A derived class inherits members (fields and methods) from its base class. All non-private members of the base are accessible in the derived class.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "class Animal {\n" +
                        "public:\n" +
                        "    std::string name;\n" +
                        "    void breathe() { std::cout << \"breathing\\n\"; }\n" +
                        "};\n\n" +
                        "class Dog : public Animal {  // Dog IS-A Animal\n" +
                        "public:\n" +
                        "    void bark() { std::cout << name << \" says woof\\n\"; }\n" +
                        "};\n\n" +
                        "Dog d;\n" +
                        "d.name = \"Rex\";\n" +
                        "d.breathe();  // inherited from Animal\n" +
                        "d.bark();     // Dog's own method"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Public vs Private vs Protected Inheritance") {
                    BodyText("The access specifier controls how inherited members are exposed:")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "class B : public A   // IS-A: public->public, protected->protected\n" +
                        "class B : protected A // public->protected, protected->protected\n" +
                        "class B : private A  // implemented-in-terms-of: all become private"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("Public inheritance expresses 'is-a': a Dog IS an Animal. Private inheritance expresses 'implemented-in-terms-of': the derived class uses the base class implementation but does not expose the relationship. Almost all real-world code uses public inheritance.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Note: with structs, the default inheritance is public; with classes it is private.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Pointer Compatibility — Base* Points to Derived") {
                    BodyText("Because Derived IS-A Base, a Base pointer (or reference) can point to a Derived object. This is the foundation of polymorphism.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "Animal* pa = new Dog();   // valid: Dog is an Animal\n" +
                        "pa->breathe();            // calls Animal::breathe\n\n" +
                        "// Through a Base*, you can only call Base's interface\n" +
                        "// (unless the method is virtual — see next section)"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("The Base* stores the address of the Base subobject inside the Derived object. For single inheritance this is the same as the object's start address. For multiple inheritance it may differ (see Multiple Inheritance section).")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Virtual Functions & the Vtable") {
                    BodyText("Declare a method 'virtual' in the base class to enable runtime dispatch. Each class with virtual functions gets a hidden vtable (array of function pointers) and each object gets a hidden vptr pointing to its class's vtable.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "class Shape {\n" +
                        "public:\n" +
                        "    virtual double area() { return 0; }\n" +
                        "    void describe() { std::cout << \"I am a shape\\n\"; }\n" +
                        "};\n\n" +
                        "class Circle : public Shape {\n" +
                        "public:\n" +
                        "    double r;\n" +
                        "    double area() override { return 3.14159 * r * r; }\n" +
                        "};\n\n" +
                        "Shape* s = new Circle{/* r=5 */};\n\n" +
                        "s->area();      // VIRTUAL  -> Circle::area()  (runtime dispatch)\n" +
                        "s->describe();  // NON-VIRTUAL -> Shape::describe() (compile-time)"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("For a non-virtual call, the compiler looks at the static type of the pointer (Shape*) and calls Shape::describe directly. For a virtual call, at runtime it dereferences the vptr, looks up area() in the vtable, and calls Circle::area.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Memory layout of a Circle object (simplified):")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "[ vptr ] -> Circle's vtable: [ Circle::area, ... ]\n" +
                        "[ r    ]"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Polymorphism") {
                    BodyText("Polymorphism: the same interface (Base pointer/reference) behaves differently depending on the actual runtime type. Classic example:")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "class Shape {\n" +
                        "public:\n" +
                        "    virtual double area() = 0;  // pure virtual\n" +
                        "};\n" +
                        "class Circle : public Shape {\n" +
                        "    double r;\n" +
                        "public:\n" +
                        "    Circle(double r) : r(r) {}\n" +
                        "    double area() override { return 3.14159 * r * r; }\n" +
                        "};\n" +
                        "class Rect : public Shape {\n" +
                        "    double w, h;\n" +
                        "public:\n" +
                        "    Rect(double w, double h) : w(w), h(h) {}\n" +
                        "    double area() override { return w * h; }\n" +
                        "};\n\n" +
                        "std::vector<Shape*> shapes = {\n" +
                        "    new Circle(5), new Rect(3, 4), new Circle(2)\n" +
                        "};\n" +
                        "for (auto* s : shapes)\n" +
                        "    std::cout << s->area() << \"\\n\";  // correct type each time"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("The 'override' Keyword") {
                    BodyText("'override' tells the compiler: this method must be overriding a virtual method in a base class. If the signature doesn't match any base virtual, it is a compile error — catching typos and signature mismatches at compile time.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "class Base {\n" +
                        "    virtual void foo(int x);\n" +
                        "};\n\n" +
                        "class Derived : public Base {\n" +
                        "    void foo(int x) override;    // OK\n" +
                        "    void foo(float x) override;  // ERROR: no virtual foo(float) in Base\n" +
                        "    void fooo(int x) override;   // ERROR: typo, no virtual fooo\n" +
                        "};"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("Always use 'override' when you intend to override. It is free — no runtime cost.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Pure Virtual Functions & Abstract Classes") {
                    BodyText("A pure virtual function has no implementation in the base class. The derived class MUST provide one. Any class with at least one pure virtual function is abstract and cannot be instantiated directly.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "class IShape {              // abstract interface\n" +
                        "public:\n" +
                        "    virtual double area()  = 0;   // pure virtual\n" +
                        "    virtual double perimeter() = 0;\n" +
                        "    virtual ~IShape() = default;\n" +
                        "};\n\n" +
                        "// IShape s;  // ERROR: cannot instantiate abstract class\n\n" +
                        "class Square : public IShape {\n" +
                        "    double side;\n" +
                        "public:\n" +
                        "    Square(double s) : side(s) {}\n" +
                        "    double area()      override { return side * side; }\n" +
                        "    double perimeter() override { return 4 * side; }\n" +
                        "};\n\n" +
                        "IShape* s = new Square(5);   // OK: Square is concrete\n" +
                        "std::cout << s->area();      // 25"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("Abstract classes are the C++ equivalent of interfaces.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Virtual Destructor") {
                    BodyText("If you delete a derived object through a Base pointer and the destructor is not virtual, only ~Base() runs — ~Derived() is skipped. This is undefined behavior and causes resource leaks.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "class Base {\n" +
                        "public:\n" +
                        "    ~Base() { std::cout << \"~Base\\n\"; }  // NOT virtual!\n" +
                        "};\n" +
                        "class Derived : public Base {\n" +
                        "    int* data;\n" +
                        "public:\n" +
                        "    Derived() : data(new int[100]) {}\n" +
                        "    ~Derived() { delete[] data; std::cout << \"~Derived\\n\"; }\n" +
                        "};\n\n" +
                        "Base* pb = new Derived();\n" +
                        "delete pb;   // only calls ~Base() -- memory leak! data is never freed"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("Fix: always make the destructor virtual when a class has virtual functions or is intended to be subclassed:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "class Base {\n" +
                        "public:\n" +
                        "    virtual ~Base() { std::cout << \"~Base\\n\"; }\n" +
                        "};\n" +
                        "// Now: delete pb calls ~Derived() then ~Base() — correct"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Multiple Inheritance & Memory Layout") {
                    BodyText("A class can inherit from multiple bases. The object layout places the first base's subobject first, then the second base's, then the derived class's own fields.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "class A { public: int x; };\n" +
                        "class B { public: int y; };\n\n" +
                        "class C : public A, public B {\n" +
                        "public:\n" +
                        "    int z;\n" +
                        "};\n\n" +
                        "// Memory layout of C object:\n" +
                        "// [ A::x ] [ B::y ] [ C::z ]\n" +
                        "//  ^-- A subobject  ^-- B subobject\n\n" +
                        "C c;\n" +
                        "A* pa = &c;   // pa points to start of c (A subobject)\n" +
                        "B* pb = &c;   // pb is ADJUSTED to point to B subobject inside c\n\n" +
                        "// pa and pb may have different numeric addresses!\n" +
                        "std::cout << (void*)pa << \" \" << (void*)pb;  // often different"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("The compiler automatically adjusts pointer values when converting C* to B*. This is why you should always use the C++ cast operators (static_cast, dynamic_cast) rather than C-style casts for pointer conversions involving inheritance.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("The Diamond Problem & Virtual Inheritance") {
                    BodyText("If B and C both inherit from A, and D inherits from both B and C, then D contains two copies of A — one from B and one from C. This causes ambiguity.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "//      A\n" +
                        "//     / \\\n" +
                        "//    B   C       <- B and C each have their own copy of A\n" +
                        "//     \\ /\n" +
                        "//      D         <- D has two copies of A\n\n" +
                        "class A { public: int val; };\n" +
                        "class B : public A {};\n" +
                        "class C : public A {};\n" +
                        "class D : public B, public C {};\n\n" +
                        "D d;\n" +
                        "// d.val = 1;       // ERROR: ambiguous — B::A::val or C::A::val?\n" +
                        "d.B::val = 1;     // OK: explicit disambiguation\n" +
                        "d.C::val = 2;     // OK: different copy"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("Solution: virtual inheritance. B and C declare their inheritance from A as virtual. Then D contains only one shared A subobject.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "class A { public: int val; };\n" +
                        "class B : virtual public A {};  // virtual!\n" +
                        "class C : virtual public A {};  // virtual!\n" +
                        "class D : public B, public C {};\n\n" +
                        "D d;\n" +
                        "d.val = 42;   // OK: only one A subobject, no ambiguity\n\n" +
                        "// Memory layout with virtual inheritance:\n" +
                        "// D contains: [vptr_B][B fields][vptr_C][C fields][D fields][A fields]\n" +
                        "// A is at the end; B and C use a vbase pointer/offset to locate it"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("Virtual inheritance adds overhead: each virtually-inherited base is located via a pointer/offset stored in the vtable (a 'vbase offset'). The most-derived class (D) is responsible for constructing the shared A subobject.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Function Hiding (Not the Same as Overriding)") {
                    BodyText("When Derived defines a function with the same name as a Base function but a different signature, the Base version is hidden — not overridden. All Base overloads with that name become inaccessible directly.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "class Base {\n" +
                        "public:\n" +
                        "    void f(int x) { std::cout << \"Base::f(int) \" << x << \"\\n\"; }\n" +
                        "};\n\n" +
                        "class Derived : public Base {\n" +
                        "public:\n" +
                        "    void f(void* p) { std::cout << \"Derived::f(void*)\\n\"; }\n" +
                        "    // Base::f(int) is now HIDDEN in Derived's scope\n" +
                        "};\n\n" +
                        "Derived d;\n" +
                        "d.f(42);       // calls Derived::f(void*) — int 42 converted to void*!\n" +
                        "               // Base::f(int) is hidden, not selected\n" +
                        "// d.f(42) with an int does NOT call Base::f(int) — surprising!\n\n" +
                        "// Fix: bring Base::f back into scope\n" +
                        "class DerivedFixed : public Base {\n" +
                        "public:\n" +
                        "    using Base::f;    // unhide Base::f(int)\n" +
                        "    void f(void* p) { std::cout << \"DerivedFixed::f(void*)\\n\"; }\n" +
                        "};\n\n" +
                        "DerivedFixed df;\n" +
                        "df.f(42);      // now correctly calls Base::f(int)\n" +
                        "df.f(nullptr); // calls DerivedFixed::f(void*)"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("Overriding requires the same signature + virtual. Hiding happens whenever the name matches but the signature differs (or the base method is non-virtual). This is a common source of subtle bugs — use 'override' to catch mistakes.")
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
