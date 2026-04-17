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
fun CppErrorHandlingScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "C/C++ — Error Handling",
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
                SectionCard("C-Style Error Handling — Return Values") {
                    BodyText("C has no exceptions. Functions signal errors through return values: -1 or a negative number for failure, NULL for pointer returns, or a custom enum. The global errno variable is set by system calls and library functions to indicate the specific error code.")
                    BodyText("perror() prints a human-readable error message to stderr using the current errno. strerror() converts errno to a string you can use yourself.")
                    CodeBlock("""
#include <errno.h>
#include <string.h>

// Return value convention
int result = some_function();
if (result < 0) {
    // handle error
}

FILE *f = fopen("file.txt", "r");
if (!f) {
    perror("fopen");              // prints: fopen: No such file or directory
    fprintf(stderr, "Error: %s\n", strerror(errno));
    return -1;
}

// System calls
int fd = open("file.txt", O_RDONLY);
if (fd == -1) {
    perror("open");
    return -1;
}
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("C++ Exceptions — throw, try, catch") {
                    BodyText("C++ introduces exceptions as a structured way to report and handle errors. throw sends an exception object up the call stack. try marks a block where exceptions may be thrown. catch handles a specific exception type.")
                    BodyText("You can throw and catch any type — int, string, or custom classes. The standard library provides std::exception as a base class with a what() method. Derived types include std::runtime_error, std::logic_error, std::bad_alloc, etc.")
                    CodeBlock("""
#include <stdexcept>

// Throw any type
void riskyFunction(int x) {
    if (x < 0)
        throw std::invalid_argument("x must be non-negative");
    if (x == 0)
        throw std::runtime_error("x cannot be zero");
}

// Try + multiple specific catches
try {
    riskyFunction(-1);
} catch (const std::invalid_argument &e) {
    printf("Invalid arg: %s\n", e.what());
} catch (const std::runtime_error &e) {
    printf("Runtime error: %s\n", e.what());
} catch (const std::exception &e) {
    printf("Some std exception: %s\n", e.what());
}

// Catch anything — use as last resort
try {
    riskyFunction(0);
} catch (...) {
    printf("Unknown exception caught\n");
}
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Stack Unwinding and Destructors") {
                    BodyText("When an exception is thrown and a matching catch is found, the runtime unwinds the call stack — it destroys all local objects between the throw site and the catch, calling their destructors in reverse order of construction. This is what makes RAII exception-safe: file handles, mutexes, and memory are automatically released during unwinding.")
                    CodeBlock("""
struct Guard {
    Guard()  { printf("Guard acquired\n"); }
    ~Guard() { printf("Guard released\n"); }  // called during unwind
};

void inner() {
    Guard g;          // destructor runs when exception unwinds past here
    throw std::runtime_error("oops");
}

void outer() {
    try {
        inner();
    } catch (const std::exception &e) {
        // By the time we reach here, g's destructor has already run
        printf("Caught: %s\n", e.what());
    }
}
// Output:
//   Guard acquired
//   Guard released    ← destructor called during stack unwind
//   Caught: oops
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Exception During Exception — std::terminate()") {
                    BodyText("Only one exception can be \"in flight\" at a time. If a second exception is thrown while the runtime is already propagating a first exception (i.e., during stack unwinding), there is nowhere to route the new exception — C++ calls std::terminate(), which aborts the program.")
                    BodyText("The most common trigger: a destructor throws during stack unwinding. This is why destructors should never throw.")
                    CodeBlock("""
struct Bad {
    ~Bad() {
        throw std::runtime_error("destructor threw!");  // DANGER
    }
};

void danger() {
    Bad b;
    throw std::runtime_error("first exception");
    // ~Bad() runs during unwind and throws a second exception
    // → std::terminate() called → program aborts
}

try {
    danger();
} catch (...) {
    // This catch is NEVER reached
}
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("noexcept — Declaring Functions That Don't Throw") {
                    BodyText("noexcept is a specifier (C++11) that declares a function will not throw exceptions. If a noexcept function does throw, std::terminate() is called — the exception cannot escape. This lets the compiler generate more efficient code (no unwind tables needed for that function).")
                    BodyText("Destructors are implicitly noexcept(true) in C++11 and later. If you declare a destructor that could throw, you must explicitly write noexcept(false) — but this is almost never a good idea.")
                    BodyText("noexcept can take a boolean expression: noexcept(true) means it will not throw; noexcept(false) means it might. This is used in templates to propagate noexcept-ness conditionally.")
                    CodeBlock("""
// Unconditional — this function will never throw
void safeFunction() noexcept {
    // if an exception somehow escapes, std::terminate() is called
}

// Destructor is implicitly noexcept — no annotation needed
struct MyClass {
    ~MyClass() { /* implicitly noexcept */ }
};

// Conditional noexcept in templates
// noexcept only if T's move constructor is noexcept
template<typename T>
void swap(T &a, T &b) noexcept(noexcept(T(std::move(a)))) {
    T tmp = std::move(a);
    a = std::move(b);
    b = std::move(tmp);
}

// Move constructors and move operator= should be noexcept
// so that std::vector can move elements instead of copying
// when it grows its internal buffer
MyClass(MyClass &&other) noexcept { ... }
MyClass& operator=(MyClass &&other) noexcept { ... }

// Query at compile time
static_assert(noexcept(safeFunction()), "must be noexcept");
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
