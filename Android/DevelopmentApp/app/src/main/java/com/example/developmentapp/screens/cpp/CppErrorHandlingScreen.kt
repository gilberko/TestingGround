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

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Exceptions Under the Hood — Exception Tables") {
                    BodyText("When the compiler sees a try/catch block it emits extra read-only data alongside the machine code so the runtime can find the right handler at throw time. On GCC/Clang (including Android NDK, which uses the Itanium C++ ABI), two ELF sections are involved: .eh_frame and .gcc_except_table.")
                    BodyText(".eh_frame holds DWARF call-frame information — one entry per function describing how to restore callee-saved registers and locate the return address. This is what lets the unwinder walk the call stack one frame at a time without any frame pointer.")
                    BodyText(".gcc_except_table holds the LSDA (Language Specific Data Area) for each function that has exception-handling regions. It is divided into three sub-tables:")
                    BodyText("Call Site Table — each row covers a contiguous byte range of the function's machine code (cs_start + cs_len). It records the landing_pad offset (0 = no handler, just run cleanup) and an action index into the Action Table.")
                    BodyText("Action Table — a singly-linked list of nodes. Each node has a type_filter (positive = index into the Type Info table for a specific type; 0 = catch(...) wildcard; negative = exception specification) and a next offset to chain multiple catches for the same try block.")
                    BodyText("Type Info Table — an array of pointers to std::type_info objects. The personality function uses std::type_info::__do_catch() to check whether the thrown type matches a handler type, which correctly handles inheritance.")
                    CodeBlock("""
// Conceptual LSDA layout for:
//   try { ... }
//   catch (const FileError &e) { ... }
//   catch (const IOError   &e) { ... }
//   catch (...)               { ... }

// --- Call Site Table ---
// cs_start  cs_len  landing_pad  action
//   0x00     0x40    pad_A         1     <- try block byte range

// --- Action Table (linked list, 1-based index) ---
// #  type_filter  next
//  1     +2         2   -> check TypeInfo[2] (FileError), then go to #2
//  2     +1         3   -> check TypeInfo[1] (IOError),  then go to #3
//  3      0         0   -> catch(...) wildcard, end of chain

// --- Type Info Table (stored in reverse) ---
// Index 1: &typeid(IOError)
// Index 2: &typeid(FileError)

// At runtime, the personality function __gxx_personality_v0
// walks the action list in order — first matching type wins.
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Exceptions Under the Hood — Two-Phase Unwinding") {
                    BodyText("throw expr compiles into three steps: __cxa_allocate_exception(sizeof(T)) reserves heap memory for the exception object; the object is copy/move-constructed into that memory; then __cxa_throw(ptr, &typeid(T), destructor_fn) stores the type info pointer and calls _Unwind_RaiseException to start the two-phase walk.")
                    BodyText("Phase 1 — SEARCH: _Unwind_RaiseException walks the call stack upward from the throw site. For each frame it calls the personality function __gxx_personality_v0 with UA_SEARCH_PHASE. The personality looks up the current PC in the call site table. If it finds a matching catch (using __do_catch for inheritance-aware comparison) it returns _URC_HANDLER_FOUND and records that frame. If no frame in the entire stack has a handler, std::terminate() is called immediately — the stack is not unwound at all.")
                    BodyText("Phase 2 — CLEANUP: _Unwind_RaiseException walks again from the throw site up to the handler frame found in Phase 1. For each intermediate frame it calls the personality with UA_CLEANUP_PHASE; the personality transfers control to the cleanup landing pad, which runs destructors for that frame's local objects, then resumes unwinding. At the handler frame itself, control transfers to the catch landing pad.")
                    BodyText("If a destructor throws during Phase 2 cleanup, a second exception is raised while the first is still in flight — std::terminate() is called. See the 'Exception During Exception' section above for details.")
                    CodeBlock("""
#include <stdexcept>
#include <cstdio>

struct Lock {
    Lock()  { printf("Lock acquired\n"); }
    ~Lock() { printf("Lock released\n"); }  // Phase 2 runs this
};

struct Buffer {
    Buffer()  { printf("Buffer allocated\n"); }
    ~Buffer() { printf("Buffer freed\n"); }    // Phase 2 runs this
};

void inner() {
    Lock lk;
    Buffer buf;
    throw std::runtime_error("disk full");
    // Phase 1: runtime scans upward, finds no handler here
    // Phase 2: ~Buffer() then ~Lock() called before frame exits
}

void middle() {
    // No try/catch — Phase 1 skips this frame
    // Phase 2 runs any cleanups here (none in this example)
    inner();
}

void outer() {
    try {
        middle();
    } catch (const std::exception &e) {
        // Phase 1 finds handler HERE; Phase 2 unwinds inner+middle first
        printf("Caught: %s\n", e.what());
    }
}

// Output:
//   Lock acquired
//   Buffer allocated
//   Buffer freed      <- Phase 2 destructor (reverse order)
//   Lock released     <- Phase 2 destructor
//   Caught: disk full
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Unhandled Exceptions — No Unwinding") {
                    BodyText("If Phase 1 walks the entire call stack and finds no matching handler, _Unwind_RaiseException returns _URC_END_OF_STACK to __cxa_throw, which calls std::terminate() directly. Phase 2 never runs. No destructors are called.")
                    BodyText("The C++ standard makes this explicitly implementation-defined: 'If no matching handler is found, the program calls std::terminate(). Whether stack unwinding takes place before this call is implementation-defined.' On GCC/Clang (the Android NDK toolchain), the answer is: no unwinding.")
                    BodyText("This is a real trap with RAII-based cleanup that has side effects — network flushes, file syncs, audit logs. If the exception goes uncaught, that cleanup silently disappears. A custom terminate handler installed via std::set_terminate() can run before the process dies, but it runs without any stack unwinding having occurred — destructors still do not run.")
                    CodeBlock("""
struct NetworkFlush {
    ~NetworkFlush() { sendPacket(); }
    // Called if exception is caught.
    // NEVER called if exception propagates uncaught.
};

// Fallback: custom terminate handler runs before the process dies,
// but without stack unwinding — destructors still do not run first.
std::set_terminate([]() {
    sendPacket();     // last resort cleanup
    std::abort();
});
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Debugger Notifications — First-Chance and Second-Chance") {
                    BodyText("When a debugger is attached, exception throws generate two separate notifications. On Windows (WaitForDebugEvent), the OS sends an EXCEPTION_DEBUG_EVENT carrying a dwFirstChance flag. First-chance (dwFirstChance=1) fires the moment throw executes — before Phase 1 even starts — so the debugger can inspect or pass through. Second-chance (dwFirstChance=0) fires when no handler was found and the process is about to terminate: the last opportunity to inspect before it dies.")
                    BodyText("Visual Studio and WinDbg let you configure per-exception-type behavior: break on first-chance, break on second-chance, or ignore. For nuisance exceptions like std::bad_alloc you might only care about second-chance; for access violations you would typically break on first-chance.")
                    BodyText("On Linux/Android, GDB and LLDB expose the same two notification points via ptrace. GDB's 'catch throw' sets a breakpoint on __cxa_throw (first-chance equivalent); 'catch catch' fires when a catch block is entered. Unhandled exceptions surface when std::terminate() triggers SIGABRT.")
                    BodyText("Important debugger side effect: some debuggers (notably GDB) insert a synthetic catch-all frame at the bottom of the stack. If they do, Phase 1 finds this frame as a valid handler, Phase 2 runs, and destructors execute before the debugger's synthetic handler fires. This means with GDB attached your cleanup destructor might run — but without a debugger attached it would not. A classic source of 'works under the debugger, broken in production' bugs.")
                    CodeBlock("""
// Scenario                      Phase 2?  Destructors?  Debugger notified?
// Handler found                   Yes       Yes           1st-chance on throw
// No handler, no debugger         No        No            —
// No handler, debugger attached   Maybe     Maybe*        1st-chance + 2nd-chance
//
// * depends on whether the debugger inserts a synthetic catch-all frame
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
