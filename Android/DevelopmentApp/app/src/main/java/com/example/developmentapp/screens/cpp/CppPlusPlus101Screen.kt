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
fun CppPlusPlus101Screen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "C++ 101",
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

            // ── class vs struct ───────────────────────────────────────────────
            item {
                SectionCard(title = "class vs struct") {
                    BodyText(
                        "In C++, class and struct are almost identical. The only difference is the " +
                        "default access specifier: struct members are public by default, class members " +
                        "are private by default. Convention: use struct for plain data holders, class " +
                        "for types with invariants and encapsulated logic."
                    )
                    CodeBlock(
                        "class MyClass {\n" +
                        "    int secret;      // private by default\n" +
                        "public:\n" +
                        "    int visible;     // public after 'public:'\n" +
                        "};\n\n" +
                        "struct MyStruct {\n" +
                        "    int visible;     // public by default\n" +
                        "private:\n" +
                        "    int secret;      // private after 'private:'\n" +
                        "};"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Access Specifiers ─────────────────────────────────────────────
            item {
                SectionCard(title = "public, private, protected") {
                    BodyText(
                        "Access specifiers control who can access members of a class:"
                    )
                    BodyText(
                        "  public — accessible by anyone: code inside the class, derived classes, " +
                        "and external code. This is the interface of the class.\n\n" +
                        "  private — accessible only by code inside the class itself (its own member " +
                        "functions and friends). Derived classes cannot access private members.\n\n" +
                        "  protected — accessible inside the class and inside derived classes, but not " +
                        "by external code. Used when a base class wants to share internals with " +
                        "subclasses without exposing them publicly."
                    )
                    CodeBlock(
                        "class Animal {\n" +
                        "public:\n" +
                        "    void breathe() { /* anyone can call this */ }\n" +
                        "protected:\n" +
                        "    int heartRate;   // subclasses can read/write this\n" +
                        "private:\n" +
                        "    int dnaSequence; // only Animal's own methods can touch this\n" +
                        "};\n\n" +
                        "class Dog : public Animal {\n" +
                        "    void bark() {\n" +
                        "        heartRate = 80;    // OK — protected\n" +
                        "        // dnaSequence = 0; // ERROR — private\n" +
                        "    }\n" +
                        "};"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Member Variables and Functions ────────────────────────────────
            item {
                SectionCard(title = "Member Variables and Member Functions") {
                    BodyText(
                        "A member function (method) has implicit access to all member variables of the " +
                        "same object via the hidden this pointer. Use . to call methods and access " +
                        "members on an object, -> through a pointer."
                    )
                    CodeBlock(
                        "class Counter {\n" +
                        "    int count = 0;          // member variable (C++11 default init)\n" +
                        "public:\n" +
                        "    void increment() { count++; }   // member function\n" +
                        "    int  get() const { return count; } // const — won't modify 'this'\n" +
                        "};\n\n" +
                        "Counter c;          // object on the stack\n" +
                        "c.increment();      // call via .\n" +
                        "c.increment();\n" +
                        "int n = c.get();    // n == 2\n\n" +
                        "Counter *cp = &c;\n" +
                        "cp->increment();    // call via ->"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Constructors ──────────────────────────────────────────────────
            item {
                SectionCard(title = "Constructors and the Default Constructor") {
                    BodyText(
                        "A constructor is a special member function that initializes an object. It has " +
                        "the same name as the class and no return type. It runs automatically when the " +
                        "object is created."
                    )
                    BodyText(
                        "The default constructor takes no arguments. If you define no constructors at " +
                        "all, the compiler generates one automatically (it zero-initializes POD members " +
                        "and default-constructs object members). If you define any constructor, the " +
                        "compiler-generated default constructor is suppressed — you must write it " +
                        "explicitly if you still want it."
                    )
                    CodeBlock(
                        "class Box {\n" +
                        "    int width, height;\n" +
                        "public:\n" +
                        "    Box() : width(1), height(1) {}          // default constructor\n" +
                        "    Box(int w, int h) : width(w), height(h) {} // parameterized\n" +
                        "    int area() const { return width * height; }\n" +
                        "};\n\n" +
                        "Box b1;          // calls default constructor — 1x1\n" +
                        "Box b2(3, 4);    // calls Box(int, int) — 3x4\n" +
                        "Box b3 = {5, 6}; // also calls Box(int, int) — C++11 brace-init"
                    )
                    BodyText(
                        "Use the member initializer list (: member(value)) rather than assignment inside " +
                        "the body — it is more efficient (constructs directly rather than " +
                        "default-constructing then assigning) and required for const members and references."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Copy Constructor ──────────────────────────────────────────────
            item {
                SectionCard(title = "Copy Constructor") {
                    BodyText(
                        "The copy constructor initializes a new object as a copy of an existing one. " +
                        "It is called when an object is passed by value, returned by value (sometimes), " +
                        "or explicitly copy-constructed."
                    )
                    BodyText(
                        "If you do not define one, the compiler generates a default copy constructor that " +
                        "performs a memberwise copy — it copies each member variable using that member's " +
                        "own copy constructor. For plain types (int, double) this is a bitwise copy. " +
                        "This is a shallow copy — if a member is a raw pointer, only the pointer is " +
                        "copied, not the data it points to."
                    )
                    CodeBlock(
                        "class Buffer {\n" +
                        "    int *data;\n" +
                        "    int  size;\n" +
                        "public:\n" +
                        "    Buffer(int n) : size(n), data(new int[n]) {}\n\n" +
                        "    // Custom copy constructor — deep copy\n" +
                        "    Buffer(const Buffer &other) : size(other.size), data(new int[other.size]) {\n" +
                        "        std::copy(other.data, other.data + size, data);\n" +
                        "    }\n" +
                        "};\n\n" +
                        "Buffer a(10);\n" +
                        "Buffer b = a;   // calls copy constructor — b gets its own array"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Move Constructor ──────────────────────────────────────────────
            item {
                SectionCard(title = "Move Constructor (C++11)") {
                    BodyText(
                        "A move constructor transfers ownership of resources from a temporary (rvalue) " +
                        "object rather than copying them. After the move, the source object is left in " +
                        "a valid but unspecified state. This avoids expensive deep copies when an object " +
                        "is about to be destroyed anyway."
                    )
                    CodeBlock(
                        "class Buffer {\n" +
                        "    int *data;\n" +
                        "    int  size;\n" +
                        "public:\n" +
                        "    // Move constructor — steals the pointer\n" +
                        "    Buffer(Buffer &&other) noexcept\n" +
                        "        : data(other.data), size(other.size) {\n" +
                        "        other.data = nullptr;  // prevent double-free in destructor\n" +
                        "        other.size = 0;\n" +
                        "    }\n" +
                        "};\n\n" +
                        "Buffer makeBuffer() { return Buffer(100); }\n" +
                        "Buffer b = makeBuffer();  // move constructor called (no deep copy)"
                    )
                    BodyText(
                        "The compiler generates a default move constructor if you have not declared a " +
                        "copy constructor, copy assignment, move assignment, or destructor. Once you " +
                        "define any of those, you must explicitly = default the move constructor or " +
                        "write it yourself (this is the Rule of Five — see below)."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Destructor ────────────────────────────────────────────────────
            item {
                SectionCard(title = "Destructor") {
                    BodyText(
                        "The destructor is called automatically when an object goes out of scope (stack " +
                        "objects) or when delete is called (heap objects). It is the right place to " +
                        "release owned resources: free memory, close file handles, unlock mutexes."
                    )
                    CodeBlock(
                        "class Buffer {\n" +
                        "    int *data;\n" +
                        "    int  size;\n" +
                        "public:\n" +
                        "    Buffer(int n) : size(n), data(new int[n]) {}\n" +
                        "    ~Buffer() { delete[] data; }  // destructor — prefixed with ~\n" +
                        "};\n\n" +
                        "{\n" +
                        "    Buffer b(100);   // data allocated\n" +
                        "}                    // b goes out of scope — ~Buffer() runs, data freed\n\n" +
                        "// Virtual destructor is essential for base classes used polymorphically:\n" +
                        "class Base { public: virtual ~Base() {} };\n" +
                        "// Without virtual ~Base(), delete on a Base* pointing to a Derived\n" +
                        "// will NOT call Derived's destructor — resource leak / UB."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Function Overloading ──────────────────────────────────────────
            item {
                SectionCard(title = "Function Overloading") {
                    BodyText(
                        "C++ allows multiple functions with the same name as long as their parameter lists " +
                        "differ (in number or type). The compiler selects the right one at compile time " +
                        "based on the argument types — this is called overload resolution. Return type " +
                        "alone is not enough to distinguish overloads."
                    )
                    CodeBlock(
                        "int    add(int a, int b)    { return a + b; }\n" +
                        "double add(double a, double b) { return a + b; }\n" +
                        "int    add(int a, int b, int c) { return a + b + c; }\n\n" +
                        "add(1, 2);        // calls add(int, int)\n" +
                        "add(1.0, 2.0);    // calls add(double, double)\n" +
                        "add(1, 2, 3);     // calls add(int, int, int)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Operator Overloading ──────────────────────────────────────────
            item {
                SectionCard(title = "Operator Overloading") {
                    BodyText(
                        "C++ lets classes define custom behaviour for built-in operators like +, -, ==, " +
                        "<, [], (), <<, and =. This makes user-defined types feel like built-in types."
                    )
                    CodeBlock(
                        "class Vec2 {\n" +
                        "public:\n" +
                        "    float x, y;\n" +
                        "    Vec2(float x, float y) : x(x), y(y) {}\n\n" +
                        "    // Binary + as member function\n" +
                        "    Vec2 operator+(const Vec2 &other) const {\n" +
                        "        return Vec2(x + other.x, y + other.y);\n" +
                        "    }\n\n" +
                        "    // Copy assignment\n" +
                        "    Vec2 &operator=(const Vec2 &other) {\n" +
                        "        x = other.x;  y = other.y;\n" +
                        "        return *this;  // return self-reference for chaining: a = b = c\n" +
                        "    }\n\n" +
                        "    // Equality\n" +
                        "    bool operator==(const Vec2 &other) const {\n" +
                        "        return x == other.x && y == other.y;\n" +
                        "    }\n" +
                        "};\n\n" +
                        "Vec2 a(1, 2), b(3, 4);\n" +
                        "Vec2 c = a + b;   // calls operator+"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Rule of 3 and Rule of 5 ───────────────────────────────────────
            item {
                SectionCard(title = "Rule of 3 and Rule of 5") {
                    BodyText(
                        "If your class needs a custom destructor to release resources, it almost certainly " +
                        "also needs a custom copy constructor and copy assignment operator — otherwise the " +
                        "compiler's defaults will perform a shallow copy and you will have two objects " +
                        "pointing to the same resource, causing a double-free when they are destroyed."
                    )
                    BodyText(
                        "Rule of 3 (C++03): if you define any of destructor, copy constructor, or copy " +
                        "assignment operator, you should define all three."
                    )
                    BodyText(
                        "Rule of 5 (C++11): with move semantics added, if you define any of the five " +
                        "special member functions — destructor, copy constructor, copy assignment, move " +
                        "constructor, move assignment — you should define or explicitly default/delete all five."
                    )
                    CodeBlock(
                        "class Buffer {\n" +
                        "    int *data; int size;\n" +
                        "public:\n" +
                        "    Buffer(int n) : size(n), data(new int[n]) {}\n" +
                        "    ~Buffer()                          { delete[] data; }\n" +
                        "    Buffer(const Buffer &o)            : size(o.size), data(new int[o.size])\n" +
                        "                                         { std::copy(o.data, o.data+size, data); }\n" +
                        "    Buffer &operator=(const Buffer &o) { Buffer tmp(o); std::swap(*this, tmp); return *this; }\n" +
                        "    Buffer(Buffer &&o) noexcept        : size(o.size), data(o.data)\n" +
                        "                                         { o.data=nullptr; o.size=0; }\n" +
                        "    Buffer &operator=(Buffer &&o) noexcept {\n" +
                        "        std::swap(data, o.data); std::swap(size, o.size); return *this;\n" +
                        "    }\n" +
                        "};"
                    )
                    BodyText(
                        "Rule of 0: the best design is to avoid manual resource management altogether. " +
                        "Use smart pointers (unique_ptr, shared_ptr) and standard containers — they " +
                        "already implement the Rule of 5 correctly, so your class needs no special " +
                        "members at all."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── friend ────────────────────────────────────────────────────────
            item {
                SectionCard(title = "friend") {
                    BodyText(
                        "A class can grant another class or function access to its private and protected " +
                        "members by declaring it a friend. Friendship is explicit, non-transitive (friend " +
                        "of a friend is not a friend), and not inherited."
                    )
                    CodeBlock(
                        "class BankAccount {\n" +
                        "    double balance = 0;\n" +
                        "public:\n" +
                        "    // Grant Auditor access to private members\n" +
                        "    friend class Auditor;\n\n" +
                        "    // Grant a free function access\n" +
                        "    friend std::ostream &operator<<(std::ostream &os, const BankAccount &a);\n" +
                        "};\n\n" +
                        "class Auditor {\n" +
                        "public:\n" +
                        "    void inspect(const BankAccount &a) {\n" +
                        "        std::cout << a.balance;  // OK — Auditor is a friend\n" +
                        "    }\n" +
                        "};\n\n" +
                        "// Overloading << for output (needs private access)\n" +
                        "std::ostream &operator<<(std::ostream &os, const BankAccount &a) {\n" +
                        "    return os << \"Balance: \" << a.balance;\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── new and delete ────────────────────────────────────────────────
            item {
                SectionCard(title = "new and delete") {
                    BodyText(
                        "C++ uses new and delete for heap allocation instead of malloc/free. new allocates " +
                        "memory and calls the constructor; delete calls the destructor then frees memory. " +
                        "Never mix them with malloc/free."
                    )
                    CodeBlock(
                        "// Allocate a single object\n" +
                        "Box *b = new Box(3, 4);   // allocate + call Box(int, int)\n" +
                        "delete b;                  // call ~Box() + free memory\n\n" +
                        "// Allocate an array of objects\n" +
                        "Box *boxes = new Box[10];  // allocate 10 Box objects (default ctor each)\n" +
                        "delete[] boxes;            // call ~Box() for all 10, then free — MUST use []\n\n" +
                        "// WRONG — mismatched new[]/delete crashes or corrupts memory\n" +
                        "// delete boxes;           // only destroys first element, corrupts allocator\n\n" +
                        "// WRONG — mixing new and free\n" +
                        "// free(b);                // free doesn't call ~Box()\n\n" +
                        "// Prefer smart pointers — they call delete automatically:\n" +
                        "auto sp = std::make_unique<Box>(3, 4);  // freed when sp goes out of scope"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Placement new ─────────────────────────────────────────────────
            item {
                SectionCard(title = "Placement new") {
                    BodyText(
                        "Placement new constructs an object at a pre-existing memory address — it calls " +
                        "the constructor without allocating any memory. The syntax is new (ptr) Type(args). " +
                        "This separates allocation from construction."
                    )
                    CodeBlock(
                        "#include <new>   // for placement new\n\n" +
                        "// Pre-allocated buffer (e.g. a memory pool, shared memory, stack arena)\n" +
                        "alignas(Box) char buf[sizeof(Box)];\n\n" +
                        "// Construct a Box in that buffer — no heap allocation\n" +
                        "Box *b = new (buf) Box(3, 4);\n\n" +
                        "// When done: call destructor MANUALLY — do NOT use delete\n" +
                        "// (delete would also try to free the buffer, which we own)\n" +
                        "b->~Box();"
                    )
                    BodyText(
                        "Use cases: memory pools (allocate a large block once, construct objects into it " +
                        "on demand — avoids per-object allocator overhead), shared memory IPC (the " +
                        "shared region is already mapped; placement new constructs objects in it), " +
                        "and implementing standard library containers like std::vector (which allocates " +
                        "raw memory and constructs elements in place)."
                    )
                    BodyText(
                        "Rules summary:\n" +
                        "  new      ↔ delete\n" +
                        "  new[]    ↔ delete[]   (never delete without [])\n" +
                        "  malloc   ↔ free\n" +
                        "  placement new ↔ manual destructor call (no delete)\n" +
                        "  Never mix allocators across these boundaries."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
