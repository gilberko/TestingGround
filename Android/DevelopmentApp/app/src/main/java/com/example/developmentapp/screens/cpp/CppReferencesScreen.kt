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
fun CppReferencesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "C/C++ — References & rvalue Refs",
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
                SectionCard("Pass by Value vs Pass by Reference") {
                    BodyText("Pass by value copies the argument — the function works on its own local copy and changes are invisible to the caller. Pass by reference passes an alias to the original — modifications are visible to the caller, and no copy is made.")
                    CodeBlock("""
// By value — caller's 'x' is unchanged
void incrementByValue(int x) { x++; }

// By reference — caller's variable IS modified
void incrementByRef(int &x) { x++; }

int n = 5;
incrementByValue(n);  // n is still 5
incrementByRef(n);    // n is now 6

// Prefer const ref for large objects you don't need to modify
void print(const std::string &s) { ... }  // no copy of the string
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Reference Variables") {
                    BodyText("A reference is an alias — another name for an existing object. It must be initialized when declared (no unbound references) and cannot be reseated to point to a different object later. Unlike a pointer, there is no null reference.")
                    CodeBlock("""
int b = 10;
int &a = b;    // 'a' is an alias for 'b'

a = 20;        // same as b = 20
printf("%d\n", b);   // prints 20

// References cannot be reseated
int c = 99;
a = c;         // this COPIES c into b (via a), not a reseat
               // a still refers to b

// No null references — unlike pointers
int *p = nullptr;   // valid
int &r = ???;       // no such thing as a null reference
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("const References") {
                    BodyText("A const reference can bind to a temporary (rvalue) as well as to a named variable. The compiler extends the temporary's lifetime to match the reference's scope. This is the standard way to accept read-only arguments cheaply.")
                    CodeBlock("""
// Binds to a temporary — lifetime extended to match 'r'
const int &r = 5 + 3;   // OK — r is 8 for the scope

// Non-const reference cannot bind to a temporary
int &bad = 5 + 3;        // compile error

// Common in function parameters — accepts both lvalues and rvalues
void log(const std::string &msg) { puts(msg.c_str()); }

log("hello");                        // temporary string — OK
std::string s = "world"; log(s);     // named variable — OK
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("What Is an rvalue?") {
                    BodyText("An lvalue is an expression that has an identity and a persistent address — you can take its address with & and it can appear on the left side of =.")
                    BodyText("An rvalue is an expression that has no persistent identity — it's a temporary. The name comes from the right-hand side of assignment: rvalues can only appear on the right side of = because you cannot assign to them. You cannot write (a + b) = x or 5 = x.")
                    BodyText("Every expression in C++ is either an lvalue (or lvalue-category) or an rvalue (or rvalue-category). Named variables are lvalues. Literals, arithmetic results, and function return values (of non-reference types) are rvalues.")
                    CodeBlock("""
int x = 5;      // x is an lvalue; 5 is an rvalue
x = 10;         // OK — lvalue on left
// 5 = x;       // compile error — rvalue on left

int a = 2, b = 3;
// (a + b) = 0; // compile error — a+b is a temporary rvalue

// Function returning by value — result is an rvalue
int getValue() { return 42; }
// getValue() = 10;  // compile error
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("rvalue References  (T&&)") {
                    BodyText("An rvalue reference (T&&) can only bind to an rvalue (temporary). It was introduced in C++11 specifically to enable move semantics — allowing functions to detect when an argument is a temporary and \"steal\" its resources instead of copying them.")
                    CodeBlock("""
int &&r = 5;      // r binds to the temporary 5
r = 6;            // OK — you can modify through the reference

int x = 10;
// int &&bad = x; // compile error — x is an lvalue

// Function overloads — compiler picks the rvalue version for temporaries
void process(const std::string &s) { /* copy path */ }
void process(std::string &&s)      { /* move path — s is a temp */ }

process("hello");          // picks rvalue overload — no copy
std::string s = "world";
process(s);                // picks lvalue (const&) overload — copy
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Move Semantics — Stealing Resources") {
                    BodyText("Copying a large object (like a string or vector with heap allocation) duplicates all its data. When the source is a temporary that is about to be destroyed anyway, copying is wasteful — we can instead steal the internal pointer and leave the source in an empty but valid state.")
                    BodyText("After a move, the source object is in a valid but unspecified state. Its destructor will run but should do nothing harmful (e.g., the pointer is set to nullptr so delete nullptr is a no-op).")
                    CodeBlock("""
struct Buffer {
    int *data;
    size_t size;

    Buffer(size_t n) : data(new int[n]), size(n) {}

    // Copy constructor — allocates and duplicates
    Buffer(const Buffer &other) : data(new int[other.size]), size(other.size) {
        std::copy(other.data, other.data + size, data);
    }

    // Move constructor — steal pointer, leave source empty
    Buffer(Buffer &&other) noexcept
        : data(other.data), size(other.size) {
        other.data = nullptr;   // source destructor will delete nullptr — safe
        other.size = 0;
    }

    ~Buffer() { delete[] data; }  // delete nullptr is a no-op
};
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Move Constructor and Move operator=") {
                    BodyText("Define both the move constructor and move assignment operator to fully support move semantics. Mark them noexcept — the standard library uses noexcept to decide whether to move or copy in containers like std::vector.")
                    CodeBlock("""
class MyString {
    char *buf;
    size_t len;
public:
    // Move constructor
    MyString(MyString &&other) noexcept
        : buf(other.buf), len(other.len) {
        other.buf = nullptr;
        other.len = 0;
    }

    // Move assignment operator
    MyString& operator=(MyString &&other) noexcept {
        if (this != &other) {
            delete[] buf;          // free current resources
            buf = other.buf;       // steal
            len = other.len;
            other.buf = nullptr;   // leave source safe to destruct
            other.len = 0;
        }
        return *this;
    }

    ~MyString() { delete[] buf; }
};
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("std::move") {
                    BodyText("Unnamed temporaries move automatically — the compiler sees they are rvalues. But named variables are lvalues, so passing them to a function normally triggers a copy even if you know you are done with them.")
                    BodyText("std::move is a cast — it tells the compiler \"treat this lvalue as an rvalue.\" It does not actually move anything; it just enables the move overload to be selected. After std::move, the source is in a valid but unspecified state — do not use it without re-assigning.")
                    CodeBlock("""
std::vector<std::string> vec;

std::string s = "hello";

// Without std::move — copies s into the vector
vec.push_back(s);   // s is still valid and unchanged

// With std::move — transfers s's buffer into the vector
vec.push_back(std::move(s));
// s is now empty (valid but unspecified) — don't use s after this

// Unnamed temporary — moves automatically, no std::move needed
vec.push_back(std::string("world"));

// Moving a unique_ptr — transfer ownership
std::unique_ptr<int> p1 = std::make_unique<int>(42);
std::unique_ptr<int> p2 = std::move(p1);  // p1 is now null
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
