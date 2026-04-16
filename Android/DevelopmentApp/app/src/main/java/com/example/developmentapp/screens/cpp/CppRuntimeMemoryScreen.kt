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
fun CppRuntimeMemoryScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "C/C++ — C Runtime And Memory",
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

            // ── Memory Segments ───────────────────────────────────────────────
            item {
                SectionCard(title = "Memory Segments") {
                    BodyText(
                        "A compiled program's memory is divided into distinct segments, each serving " +
                        "a different purpose. Understanding them helps explain where variables, code, " +
                        "and strings live — and why writing to the wrong region crashes your program."
                    )
                    BodyText(
                        ".text — the code segment:\n" +
                        "Contains the compiled machine instructions. Marked read-only and executable " +
                        "by the OS (W^X — write XOR execute). Attempting to write to .text causes a " +
                        "segfault. All function bodies live here."
                    )
                    BodyText(
                        ".data — initialized data:\n" +
                        "Global and static variables that have a non-zero initial value. The actual " +
                        "bytes are stored in the executable file and loaded into memory at startup."
                    )
                    BodyText(
                        ".bss — Block Started by Symbol (uninitialized data):\n" +
                        "Global and static variables that are uninitialized or explicitly zero-initialized. " +
                        "The executable stores only the size, not the bytes — the OS maps zero pages on " +
                        "load. This makes executables smaller: a global array of 1 MB of zeros takes " +
                        "zero bytes on disk but 1 MB at runtime."
                    )
                    BodyText(
                        "Heap:\n" +
                        "Dynamically allocated memory (malloc/new). Starts at the end of BSS and grows " +
                        "upward toward high addresses. Managed by the allocator (glibc uses brk/mmap on " +
                        "Linux; Windows uses HeapAlloc/VirtualAlloc). The programmer controls lifetimes — " +
                        "memory persists until freed."
                    )
                    BodyText(
                        "Stack:\n" +
                        "Local variables, function parameters, return addresses, and saved registers. " +
                        "Grows downward from a high address. Each function call pushes a new frame; " +
                        "returning pops it. Stack frames are automatically reclaimed — no explicit free " +
                        "needed. Size is limited (typically 1–8 MB per thread)."
                    )
                    CodeBlock(
                        "int  global_init   = 42;       // .data  (initialized)\n" +
                        "int  global_uninit;             // .bss   (zero-initialized)\n" +
                        "static int file_scope = 7;     // .data  (initialized static)\n" +
                        "const char *msg = \"hello\";     // msg ptr in .data/.bss;\n" +
                        "                               // \"hello\" bytes in .rodata\n\n" +
                        "void foo() {\n" +
                        "    int local = 1;             // stack\n" +
                        "    int *p = malloc(64);       // p on stack, 64 bytes on heap\n" +
                        "    static int persist = 0;   // .bss (zero-init static local)\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Constant Strings and .rodata ──────────────────────────────────
            item {
                SectionCard(title = "Where Do Constant Strings Live? (.rodata)") {
                    BodyText(
                        "String literals like \"hello\" are not on the stack, heap, .data, or .bss. " +
                        "They live in a dedicated read-only data section:"
                    )
                    BodyText(
                        "  • Linux/ELF: .rodata (read-only data)\n" +
                        "  • Windows/PE: .rdata\n" +
                        "  • Some minimal embedded targets: merged into .text\n\n" +
                        "The OS maps this section with read-only permissions. Any attempt to write " +
                        "through a string literal pointer causes a SIGSEGV (or Access Violation on " +
                        "Windows). This is why the C standard declares string literals as const char* " +
                        "— they cannot be modified."
                    )
                    CodeBlock(
                        "const char *s = \"hello\";    // s is a pointer on the stack;\n" +
                        "                            // the bytes 'h','e','l','l','o','\\0'\n" +
                        "                            // live in .rodata\n\n" +
                        "s[0] = 'H';   // UNDEFINED BEHAVIOUR — likely SIGSEGV\n\n" +
                        "// To get a modifiable copy, use a char array:\n" +
                        "char buf[] = \"hello\";   // copies bytes from .rodata onto the stack\n" +
                        "buf[0] = 'H';            // safe — buf lives on the stack"
                    )
                    BodyText(
                        "The compiler may also merge identical string literals into a single copy in " +
                        ".rodata, saving space. Two pointers to the same literal may or may not be " +
                        "equal — do not rely on pointer equality to compare strings."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── The C Runtime ─────────────────────────────────────────────────
            item {
                SectionCard(title = "The C Runtime (CRT) — Before and After main()") {
                    BodyText(
                        "Your program does not start executing at main(). The OS transfers control to " +
                        "a small startup stub provided by the C Runtime library — _start on Linux, " +
                        "mainCRTStartup (or WinMainCRTStartup) on Windows. This stub runs before and " +
                        "after main() to set up and tear down the execution environment."
                    )
                    BodyText("Before main() the CRT startup code:")
                    BodyText(
                        "  1. Sets up the stack pointer and frame pointer.\n" +
                        "  2. Initializes the heap allocator.\n" +
                        "  3. Populates argc, argv, and envp from the OS.\n" +
                        "  4. Initializes thread-local storage.\n" +
                        "  5. Runs all C++ global constructors (see next section).\n" +
                        "  6. Calls main()."
                    )
                    BodyText("After main() returns (or exit() is called) the CRT:")
                    BodyText(
                        "  1. Calls all functions registered with atexit() — in LIFO order.\n" +
                        "  2. Runs all C++ global destructors (registered via __cxa_atexit).\n" +
                        "  3. Flushes and closes all open stdio streams.\n" +
                        "  4. Returns the exit code to the OS."
                    )
                    BodyText(
                        "Note: calling _exit() or _Exit() bypasses the atexit list and destructor " +
                        "calls — it exits immediately. Useful in a child process after fork() to " +
                        "avoid double-flushing shared stdio buffers."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Global Object Construction and Destruction ────────────────────
            item {
                SectionCard(title = "Global Object Construction and Destruction") {
                    BodyText(
                        "For every global C++ object the compiler generates a small initialization " +
                        "function. These functions are placed in a special list stored in the " +
                        "executable (.init_array section on Linux/ELF; the CRT\$XCU segment on " +
                        "Windows MSVC). The CRT startup code iterates this list before main() and " +
                        "calls every function in it."
                    )
                    BodyText("Each generated init function does exactly two things:")
                    BodyText(
                        "  1. Calls the object's constructor (constructing it in-place in its " +
                        "     statically allocated storage).\n" +
                        "  2. Registers a destructor wrapper with __cxa_atexit() so the object " +
                        "     will be destroyed after main() returns."
                    )
                    BodyText(
                        "The compiler effectively generates code like this for GlobalFoo g_foo(42):"
                    )
                    CodeBlock(
                        "// Destructor wrapper — stored as a callback\n" +
                        "static void destroy_g_foo(void *obj) {\n" +
                        "    static_cast<Foo *>(obj)->~Foo();\n" +
                        "}\n\n" +
                        "// Init function — stored in .init_array\n" +
                        "static void init_g_foo() {\n" +
                        "    new (&g_foo) Foo(42);         // construct in-place\n" +
                        "    __cxa_atexit(destroy_g_foo,   // destructor wrapper\n" +
                        "                 &g_foo,           // the specific object\n" +
                        "                 &__dso_handle);   // DSO handle (for shared libs)\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── atexit vs __cxa_atexit ────────────────────────────────────────
            item {
                SectionCard(title = "atexit() and __cxa_atexit()") {
                    BodyText(
                        "atexit() is the C standard function (declared in <stdlib.h>) for registering " +
                        "cleanup callbacks. It takes a plain function pointer with no arguments and " +
                        "no return value. The C standard requires support for at least 32 registered " +
                        "functions; glibc supports many more."
                    )
                    CodeBlock(
                        "#include <stdlib.h>\n\n" +
                        "void cleanup(void) {\n" +
                        "    printf(\"cleanup called\\n\");\n" +
                        "}\n\n" +
                        "int main() {\n" +
                        "    atexit(cleanup);   // called after main() returns\n" +
                        "    return 0;\n" +
                        "}"
                    )
                    BodyText(
                        "__cxa_atexit() is a C++ ABI extension (Itanium C++ ABI, used by GCC and " +
                        "Clang on Linux/macOS). It extends atexit() by also storing a pointer to " +
                        "the specific object, enabling the registered function to call the destructor " +
                        "on exactly that instance. This is what compilers actually emit for global " +
                        "C++ objects — not plain atexit()."
                    )
                    CodeBlock(
                        "// Signature of __cxa_atexit:\n" +
                        "int __cxa_atexit(\n" +
                        "    void (*destructor)(void *),  // function to call\n" +
                        "    void *obj,                   // passed to destructor\n" +
                        "    void *dso_handle             // which shared lib registered this\n" +
                        ");"
                    )
                    BodyText(
                        "Registered callbacks (both atexit and __cxa_atexit) are called in LIFO " +
                        "(last-in, first-out) order after main() returns — the last global object " +
                        "constructed is the first destroyed. This mirrors the stack discipline for " +
                        "local variables.\n\n" +
                        "On MSVC the mechanism is equivalent in effect but uses CRT-internal tables " +
                        "rather than __cxa_atexit. The observable behavior — constructors before " +
                        "main, destructors in LIFO order after main — is the same on all platforms."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Static Initialization Order Fiasco ───────────────────────────
            item {
                SectionCard(title = "Static Initialization Order Fiasco") {
                    BodyText(
                        "The order in which global objects across different translation units are " +
                        "constructed is unspecified. If global object A's constructor depends on " +
                        "global object B, and they live in different .cpp files, B may not have " +
                        "been constructed yet when A's constructor runs — leading to a bug or crash " +
                        "that is very hard to reproduce."
                    )
                    CodeBlock(
                        "// file_a.cpp\n" +
                        "extern Logger g_logger;      // defined in file_b.cpp\n" +
                        "Database g_db(g_logger);     // may run BEFORE g_logger is constructed!\n\n" +
                        "// file_b.cpp\n" +
                        "Logger g_logger;             // may run AFTER g_db's constructor"
                    )
                    BodyText(
                        "The standard solution is the Meyers' Singleton: wrap each global object in " +
                        "a function that returns a static local. Function-local statics are guaranteed " +
                        "to be initialized the first time the function is called (and thread-safely " +
                        "in C++11). Since A's constructor calls B's accessor function, B is " +
                        "guaranteed to be initialized first."
                    )
                    CodeBlock(
                        "// file_b.cpp\n" +
                        "Logger& get_logger() {\n" +
                        "    static Logger instance;   // constructed on first call, thread-safe\n" +
                        "    return instance;\n" +
                        "}\n\n" +
                        "// file_a.cpp\n" +
                        "Database& get_db() {\n" +
                        "    static Database instance(get_logger());  // logger guaranteed alive\n" +
                        "    return instance;\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
