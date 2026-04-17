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
fun CppTemplatesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "C/C++ — Templates",
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
                SectionCard("Why Templates?") {
                    BodyText("Templates let you write code once and have the compiler generate a type-specific copy (an instantiation) for each concrete type you use. Without templates, you would need separate functions for int, double, std::string, etc.")
                    BodyText("Important: the compiler needs the full template definition at the point of instantiation (the .cpp file that uses it). This means template implementations must live in header files (.hpp), not in separate .cpp files. If you define a template in a .cpp and try to use it from another .cpp, you will get linker errors.")
                    CodeBlock("""
// swap.hpp — entire definition in the header
template<typename T>
void mySwap(T &a, T &b) {
    T tmp = a;
    a = b;
    b = tmp;
}

// main.cpp — compiler generates mySwap<int> and mySwap<double> here
#include "swap.hpp"
int x = 1, y = 2;
mySwap(x, y);           // instantiation for int

double a = 1.5, b = 2.5;
mySwap(a, b);           // instantiation for double
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Function Templates") {
                    BodyText("Use template<typename T> or template<class T> — both are identical (class here does not mean 'class type only'). The compiler can usually deduce T from the arguments, but you can also specify it explicitly.")
                    CodeBlock("""
// typename and class are interchangeable in this context
template<typename T>
T maxOf(T a, T b) { return (a > b) ? a : b; }

template<class C>
C minOf(C a, C b) { return (a < b) ? a : b; }

// Deduced — compiler figures out T = int
int big = maxOf(3, 7);

// Explicit — useful when types don't match or can't be deduced
double d = maxOf<double>(3, 7.5);  // without <double>, ambiguous

// Multiple type parameters
template<typename A, typename B>
void printPair(A first, B second) {
    std::cout << first << ", " << second << "\n";
}

printPair(42, "hello");   // A=int, B=const char*
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Class Templates") {
                    BodyText("A class can be parameterised by type, allowing generic containers and wrappers. All member function definitions written outside the class body must repeat the template<typename T> prefix.")
                    CodeBlock("""
template<typename T>
class Stack {
    std::vector<T> data;
public:
    void push(const T &val) { data.push_back(val); }
    void pop()              { data.pop_back(); }
    T&   top()              { return data.back(); }
    bool empty() const      { return data.empty(); }
};

// Usage — T is deduced from make argument (C++17 CTAD) or explicit
Stack<int>         intStack;
Stack<std::string> strStack;

intStack.push(1);
intStack.push(2);
printf("%d\n", intStack.top());  // 2

// Member defined outside the class
template<typename T>
void Stack<T>::push(const T &val) { data.push_back(val); }
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Multiple Template Parameters & Non-type Parameters") {
                    BodyText("A template can have any number of type parameters. It can also have non-type parameters — compile-time constant values like integers. std::array uses this to encode its size at compile time.")
                    CodeBlock("""
// Two type parameters
template<typename K, typename V>
struct Pair {
    K key;
    V value;
};

Pair<std::string, int> score = {"Alice", 95};

// Non-type parameter — N is a compile-time integer
template<typename T, int N>
struct FixedArray {
    T data[N];
    int size() const { return N; }
};

FixedArray<float, 4> vec4;   // stack-allocated, size baked in at compile time

// std::array from the standard library does exactly this
std::array<int, 5> arr = {1, 2, 3, 4, 5};
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Template Specialization") {
                    BodyText("Full specialization provides a completely different implementation for one specific type. Partial specialization (only for class templates) provides an implementation for a family of types, e.g., all pointer types.")
                    CodeBlock("""
// Primary template
template<typename T>
struct Serialize {
    static std::string to_str(T val) { return std::to_string(val); }
};

// Full specialization for bool
template<>
struct Serialize<bool> {
    static std::string to_str(bool val) { return val ? "true" : "false"; }
};

Serialize<int>::to_str(42);     // uses primary — "42"
Serialize<bool>::to_str(true);  // uses specialization — "true"

// Partial specialization — matches any pointer type
template<typename T>
struct Serialize<T*> {
    static std::string to_str(T *ptr) {
        char buf[32];
        snprintf(buf, sizeof(buf), "%p", (void*)ptr);
        return buf;
    }
};
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("SFINAE — Substitution Failure Is Not An Error") {
                    BodyText("When the compiler tries to substitute a type into a template and the substitution makes the template ill-formed (e.g., the type lacks a required member), that overload is silently removed from the candidate set rather than causing a compile error. This is SFINAE — Substitution Failure Is Not An Error.")
                    BodyText("std::enable_if<condition, Type> evaluates to Type when condition is true, and is undefined (substitution failure) when condition is false. This lets you conditionally enable or disable template overloads at compile time.")
                    CodeBlock("""
#include <type_traits>

// Only enabled for integral types (int, long, char, ...)
template<typename T>
typename std::enable_if<std::is_integral<T>::value, T>::type
addOne(T x) { return x + 1; }

// Only enabled for floating-point types
template<typename T>
typename std::enable_if<std::is_floating_point<T>::value, T>::type
addOne(T x) { return x + 1.0; }

addOne(5);     // picks integral version
addOne(3.14);  // picks floating-point version
// addOne("hi"); // compile error — no valid overload

// C++14 shorthand
template<typename T,
         typename = std::enable_if_t<std::is_integral_v<T>>>
T square(T x) { return x * x; }

// C++20 — concepts replace SFINAE with cleaner syntax
template<std::integral T>
T addOneModern(T x) { return x + 1; }
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
