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
fun CppOperatorOverloadingScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "C/C++ — Operator Overloading",
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

            // ── Arithmetic Operators ──────────────────────────────────────────
            item {
                SectionCard(title = "Arithmetic Operators (+, -, *, /)") {
                    BodyText(
                        "Operator overloading lets you define what built-in operators mean for your " +
                        "types. You write a function named operator+ (or operator-, etc.) and the " +
                        "compiler calls it wherever you write a + b with your type."
                    )
                    BodyText(
                        "Two forms exist: member functions and free (non-member) functions. For " +
                        "symmetric binary operators (+ - * /), free functions are preferred — they " +
                        "allow implicit conversions on both operands, not just the right-hand side."
                    )
                    BodyText(
                        "Best practice: implement the compound assignment operators (+=, -=) as " +
                        "members, then implement the plain binary operators as free functions that " +
                        "call them. This avoids duplicating the arithmetic logic."
                    )
                    CodeBlock(
                        "class Vec2 {\n" +
                        "public:\n" +
                        "    float x, y;\n" +
                        "    Vec2(float x, float y) : x(x), y(y) {}\n\n" +
                        "    Vec2& operator+=(const Vec2& rhs) {\n" +
                        "        x += rhs.x; y += rhs.y; return *this;\n" +
                        "    }\n" +
                        "    Vec2& operator-=(const Vec2& rhs) {\n" +
                        "        x -= rhs.x; y -= rhs.y; return *this;\n" +
                        "    }\n" +
                        "    Vec2& operator*=(float s) { x *= s; y *= s; return *this; }\n" +
                        "    Vec2& operator/=(float s) { x /= s; y /= s; return *this; }\n" +
                        "};\n\n" +
                        "// Free functions: first arg taken by value so we can modify it\n" +
                        "inline Vec2 operator+(Vec2 lhs, const Vec2& rhs) { return lhs += rhs; }\n" +
                        "inline Vec2 operator-(Vec2 lhs, const Vec2& rhs) { return lhs -= rhs; }\n" +
                        "inline Vec2 operator*(Vec2 v,   float s)          { return v  *= s; }\n" +
                        "inline Vec2 operator/(Vec2 v,   float s)          { return v  /= s; }\n\n" +
                        "Vec2 a(1, 2), b(3, 4);\n" +
                        "Vec2 c = a + b;      // calls operator+(Vec2, const Vec2&)\n" +
                        "Vec2 d = c * 2.0f;  // calls operator*(Vec2, float)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Assignment Operator ───────────────────────────────────────────
            item {
                SectionCard(title = "Assignment Operator (=)") {
                    BodyText(
                        "operator= must be a member function (not a free function). It is called when " +
                        "you write a = b where both are already-constructed objects of the same type. " +
                        "It must handle self-assignment (a = a) safely and return *this by reference " +
                        "to allow chaining (a = b = c)."
                    )
                    CodeBlock(
                        "class Buffer {\n" +
                        "    char *data;\n" +
                        "    size_t size;\n" +
                        "public:\n" +
                        "    Buffer& operator=(const Buffer& rhs) {\n" +
                        "        if (this == &rhs) return *this;  // guard against self-assignment\n" +
                        "        delete[] data;\n" +
                        "        size = rhs.size;\n" +
                        "        data = new char[size];\n" +
                        "        memcpy(data, rhs.data, size);\n" +
                        "        return *this;                    // enable chaining\n" +
                        "    }\n" +
                        "};"
                    )
                    BodyText(
                        "The compiler generates a copy-assignment operator automatically if you don't " +
                        "declare one, but only if the class doesn't manage raw resources. If you " +
                        "declare a destructor or copy constructor, you almost certainly need to " +
                        "declare operator= as well — this is the Rule of Three (Rule of Five in " +
                        "C++11 with move semantics)."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Dereference and Arrow ─────────────────────────────────────────
            item {
                SectionCard(title = "Dereference and Arrow (*, ->)") {
                    BodyText(
                        "operator* (dereference) and operator-> (member access through pointer) are " +
                        "used to build smart-pointer-like types that wrap a pointer but add ownership " +
                        "or access semantics. operator-> is unusual: the compiler calls it repeatedly " +
                        "until a raw pointer is returned, then performs the member access on that pointer."
                    )
                    CodeBlock(
                        "template<typename T>\n" +
                        "class Box {\n" +
                        "    T *ptr;\n" +
                        "public:\n" +
                        "    explicit Box(T *p) : ptr(p) {}\n" +
                        "    ~Box() { delete ptr; }\n\n" +
                        "    T&  operator*()  const { return *ptr; }  // dereference\n" +
                        "    T*  operator->() const { return ptr;  }  // arrow — returns raw ptr\n" +
                        "};\n\n" +
                        "Box<std::string> b(new std::string(\"hello\"));\n\n" +
                        "std::cout << *b          << \"\\n\";  // operator* -> \"hello\"\n" +
                        "std::cout << b->size()   << \"\\n\";  // operator-> then ->size()\n" +
                        "// The compiler expands b->size() as:\n" +
                        "// (b.operator->())->size()  which is ptr->size()"
                    )
                    BodyText(
                        "This is exactly how std::unique_ptr and std::shared_ptr are implemented. " +
                        "Their operator* and operator-> simply forward to the underlying raw pointer."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Increment and Decrement ───────────────────────────────────────
            item {
                SectionCard(title = "Increment and Decrement (++, --)") {
                    BodyText(
                        "There are two forms of ++ and --: prefix (++a, --a) and postfix (a++, a--). " +
                        "They differ in what they return and in performance. The compiler distinguishes " +
                        "them by a dummy int parameter in the postfix version."
                    )
                    BodyText(
                        "Prefix: modify and return a reference to self (no copy made).\n" +
                        "Postfix: save the old value, modify self, return the old value (requires a copy).\n\n" +
                        "When in doubt, prefer prefix increment — it is never slower, and for iterators " +
                        "and complex types it can be significantly faster than postfix."
                    )
                    CodeBlock(
                        "struct Counter {\n" +
                        "    int val = 0;\n\n" +
                        "    // Prefix ++c: modify, return reference to self\n" +
                        "    Counter& operator++()    { ++val; return *this; }\n\n" +
                        "    // Postfix c++: save old, modify, return old value (copy)\n" +
                        "    Counter  operator++(int) {\n" +
                        "        Counter old = *this;   // copy\n" +
                        "        ++val;\n" +
                        "        return old;            // return pre-increment copy\n" +
                        "    }\n\n" +
                        "    Counter& operator--()    { --val; return *this; }\n" +
                        "    Counter  operator--(int) { Counter old = *this; --val; return old; }\n" +
                        "};\n\n" +
                        "Counter c;\n" +
                        "Counter a = c++;   // a.val=0, c.val=1  — postfix returns OLD value\n" +
                        "Counter b = ++c;   // b.val=2, c.val=2  — prefix  returns NEW value"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Function Call Operator ────────────────────────────────────────
            item {
                SectionCard(title = "Function Call Operator () — Functors") {
                    BodyText(
                        "operator() lets an object be called with the function-call syntax obj(args). " +
                        "An object with operator() is called a functor or function object. Unlike a " +
                        "plain function pointer, a functor can carry state in its member variables — " +
                        "making it a closure before lambdas existed."
                    )
                    BodyText(
                        "C++ lambdas are syntactic sugar for anonymous structs with operator() — " +
                        "every lambda the compiler sees is transformed into a unique class with a " +
                        "generated operator() and captured variables as member fields."
                    )
                    CodeBlock(
                        "struct Multiplier {\n" +
                        "    int factor;\n" +
                        "    explicit Multiplier(int f) : factor(f) {}\n" +
                        "    int operator()(int x) const { return x * factor; }\n" +
                        "};\n\n" +
                        "Multiplier triple(3);\n" +
                        "std::cout << triple(5)  << \"\\n\";  // 15\n" +
                        "std::cout << triple(10) << \"\\n\";  // 30\n\n" +
                        "// Works with STL algorithms that accept a callable:\n" +
                        "std::vector<int> v = {1, 2, 3, 4};\n" +
                        "std::transform(v.begin(), v.end(), v.begin(), Multiplier(2));\n" +
                        "// v is now {2, 4, 6, 8}\n\n" +
                        "// Equivalent lambda (compiler generates similar code internally):\n" +
                        "int factor = 2;\n" +
                        "auto lam = [factor](int x) { return x * factor; };\n" +
                        "std::cout << lam(5) << \"\\n\";  // 10"
                    )
                    BodyText(
                        "operator() can take any number and types of arguments and can be overloaded. " +
                        "It is commonly used for comparators, hash functions, and callbacks stored in " +
                        "std::function."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── operator new and operator delete ──────────────────────────────
            item {
                SectionCard(title = "operator new and operator delete") {
                    BodyText(
                        "Overloading operator new and operator delete lets a class control exactly how " +
                        "its memory is allocated and freed — useful for memory pools, arenas, or custom " +
                        "allocators that avoid the general-purpose heap."
                    )
                    BodyText(
                        "These operators are implicitly static. There is no 'this' pointer because the " +
                        "object does not exist yet when operator new runs — it is called to allocate " +
                        "the raw memory before the constructor. You do not need to (and cannot) write " +
                        "'static' explicitly; it is implied."
                    )
                    CodeBlock(
                        "class PoolObj {\n" +
                        "public:\n" +
                        "    // Called to allocate raw memory — before the constructor\n" +
                        "    static void* operator new(std::size_t size) {\n" +
                        "        void *p = pool_alloc(size);\n" +
                        "        if (!p) throw std::bad_alloc();\n" +
                        "        return p;\n" +
                        "    }\n\n" +
                        "    // Called after the destructor to free raw memory\n" +
                        "    static void operator delete(void *p, std::size_t size) noexcept {\n" +
                        "        pool_free(p, size);\n" +
                        "    }\n\n" +
                        "    // Array forms (new[] / delete[])\n" +
                        "    static void* operator new[](std::size_t size) {\n" +
                        "        return pool_alloc(size);\n" +
                        "    }\n" +
                        "    static void  operator delete[](void *p) noexcept {\n" +
                        "        pool_free(p, 0);\n" +
                        "    }\n" +
                        "};\n\n" +
                        "// new calls operator new for memory, then the constructor\n" +
                        "PoolObj *o = new PoolObj();\n\n" +
                        "// delete calls the destructor, then operator delete for memory\n" +
                        "delete o;"
                    )
                    BodyText(
                        "Sequence for 'new PoolObj()':\n" +
                        "  1. PoolObj::operator new(sizeof(PoolObj)) — allocate raw bytes\n" +
                        "  2. PoolObj::PoolObj() — run the constructor on that memory\n\n" +
                        "Sequence for 'delete o':\n" +
                        "  1. PoolObj::~PoolObj() — run the destructor\n" +
                        "  2. PoolObj::operator delete(o, sizeof(PoolObj)) — free the memory"
                    )
                    BodyText(
                        "You can also overload global operator new and operator delete (outside any " +
                        "class). This replaces the allocator for all heap allocations in the program — " +
                        "used for memory profilers, sanitizers, and custom global allocators."
                    )
                    CodeBlock(
                        "// Global replacement — affects all new/delete in the program\n" +
                        "void* operator new(std::size_t size) {\n" +
                        "    log_alloc(size);\n" +
                        "    void *p = std::malloc(size);\n" +
                        "    if (!p) throw std::bad_alloc();\n" +
                        "    return p;\n" +
                        "}\n" +
                        "void operator delete(void *p) noexcept {\n" +
                        "    log_free(p);\n" +
                        "    std::free(p);\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
