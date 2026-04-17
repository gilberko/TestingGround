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
fun CppConstAutoMutableScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "C/C++ — const, auto, mutable",
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
                SectionCard("const Basics") {
                    BodyText("const marks a variable whose value cannot change after initialization. The compiler enforces this — any attempt to modify it is a compile error. It also enables optimizations (the value can be treated as a compile-time constant in some contexts).")
                    CodeBlock("""
const int MAX = 100;
// MAX = 200;   // compile error

const double PI = 3.14159;

// const must be initialized at declaration
// const int x;  // compile error — uninitialized const
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("const With Pointers — Two Very Different Forms") {
                    BodyText("The position of const relative to * determines what is const. Read declarations right-to-left to decode them.")
                    BodyText("const int *p  — \"p is a pointer to const int\": you cannot change *p (the value pointed to), but you can change p itself (redirect it to a different int).")
                    BodyText("int * const p  — \"p is a const pointer to int\": you cannot change p (redirect it), but you can change *p (the value at that address).")
                    BodyText("const int * const p  — both the pointer and the value are const.")
                    CodeBlock("""
int a = 1, b = 2;

// Pointer to const int — cannot modify value, CAN redirect pointer
const int *p1 = &a;
// *p1 = 99;     // compile error — value is const
p1 = &b;         // OK — pointer itself is not const

// Const pointer to int — CAN modify value, cannot redirect pointer
int * const p2 = &a;
*p2 = 99;        // OK — modifying the int
// p2 = &b;      // compile error — pointer is const

// Both const
const int * const p3 = &a;
// *p3 = 99;     // compile error
// p3 = &b;      // compile error

// Common function parameter pattern: read-only access to data
void process(const int *data, size_t len) {
    // data[0] = 0;  // compile error — data is const
}
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("const Member Functions") {
                    BodyText("A member function declared const promises not to modify any member variables of the object (this is const-qualified). You can only call const member functions on a const object. Non-const functions cannot be called on a const object.")
                    CodeBlock("""
class Counter {
    int value;
public:
    Counter(int v) : value(v) {}

    int get() const { return value; }   // can call on const Counter
    void increment() { value++; }       // cannot call on const Counter
};

const Counter c(5);
printf("%d\n", c.get());     // OK — const function
// c.increment();             // compile error — non-const function on const object

Counter d(5);
d.get();        // OK
d.increment();  // OK
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("mutable") {
                    BodyText("mutable allows a member variable to be modified even when the enclosing object is const. It is the escape hatch for \"implementation details that don't affect logical constness\" — caches, access counters, lazy-evaluated fields, and mutexes.")
                    BodyText("The object's observable state (its logical value) is unchanged; only the internal bookkeeping is updated. Use sparingly — overusing mutable weakens const guarantees.")
                    CodeBlock("""
class ExpensiveCalc {
    int base;
    mutable int cachedResult = -1;   // mutable — can change even in const functions
    mutable bool dirty = true;

public:
    ExpensiveCalc(int b) : base(b) {}

    int compute() const {
        if (dirty) {
            cachedResult = base * base * base;  // OK — mutable
            dirty = false;
        }
        return cachedResult;
    }
};

const ExpensiveCalc ec(5);
printf("%d\n", ec.compute());   // first call computes and caches
printf("%d\n", ec.compute());   // second call uses cache

// Mutex inside a const method — also needs mutable
class ThreadSafe {
    mutable std::mutex mtx;
    int data = 0;
public:
    int read() const {
        std::lock_guard<std::mutex> lock(mtx);  // OK — mtx is mutable
        return data;
    }
};
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("auto") {
                    BodyText("auto tells the compiler to deduce the type from the initializer. It drops top-level const and references — so auto copies by default. Use it when the type is obvious from context or when the full type name would be verbose.")
                    CodeBlock("""
auto x = 42;           // int
auto d = 3.14;         // double
auto s = std::string("hi");  // std::string

// Avoids long iterator types
std::map<std::string, std::vector<int>> m;
auto it = m.begin();   // beats: std::map<std::string,std::vector<int>>::iterator

// auto drops const and reference from the source
const int ci = 5;
auto a = ci;    // a is int (not const int) — copy
a = 99;         // OK

// auto with explicit modifiers
const auto ca = ci;  // ca is const int
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("auto& and const auto&") {
                    BodyText("auto& deduces a reference type — no copy is made and modifications affect the original. Use in range-based for loops or when you intentionally want to modify through the deduced variable.")
                    BodyText("const auto& is the most common choice for read-only access: no copy, no accidental mutation, and it can bind to temporaries (lifetime extension). Use it in range-based for loops when you don't need to modify the element.")
                    CodeBlock("""
std::vector<std::string> names = {"Alice", "Bob", "Charlie"};

// auto — copies each string (expensive)
for (auto name : names) { ... }

// auto& — reference, can modify the original
for (auto &name : names) { name += "!"; }

// const auto& — read-only reference, no copy
for (const auto &name : names) { printf("%s\n", name.c_str()); }

// auto& in normal variable context
int x = 10;
auto &ref = x;   // ref is int&
ref = 20;        // x is now 20

// const auto& extends lifetime of a temporary
const auto &r = std::string("temp");  // string lives until r goes out of scope

// In function templates — const auto& accepts both lvalues and rvalues
auto printAny = [](const auto &val) { std::cout << val << "\n"; };
printAny(42);
printAny("hello");
printAny(3.14);
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
