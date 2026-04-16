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
fun CppKeywordsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "C/C++ — Keywords",
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

            // ── volatile ──────────────────────────────────────────────────────
            item {
                SectionCard(title = "volatile") {
                    BodyText(
                        "The compiler is allowed to optimize aggressively: it may cache a variable's " +
                        "value in a CPU register and never reload it from memory, reorder reads and " +
                        "writes for performance, or eliminate reads it considers redundant. This is " +
                        "correct when the compiler controls all accesses to the variable — but breaks " +
                        "down when something outside the compiler's view can change the value."
                    )
                    BodyText(
                        "volatile tells the compiler: every access to this variable must go directly " +
                        "to memory — do not cache in a register, do not reorder, do not optimize away. " +
                        "Typical use cases:"
                    )
                    CodeBlock(
                        "// 1. Memory-mapped hardware register\n" +
                        "//    The register changes due to hardware, not our code.\n" +
                        "volatile uint32_t *UART_STATUS = (volatile uint32_t *)0x40001000;\n" +
                        "while (!(*UART_STATUS & 0x01)) { }  // must re-read each iteration\n\n" +
                        "// 2. Variable modified by a signal handler\n" +
                        "volatile sig_atomic_t stop_flag = 0;\n" +
                        "void handler(int sig) { stop_flag = 1; }\n" +
                        "while (!stop_flag) { do_work(); }   // must re-read each loop\n\n" +
                        "// 3. Variable shared between a thread and an ISR\n" +
                        "volatile int irq_count = 0;  // incremented by interrupt routine"
                    )
                    BodyText(
                        "Important: volatile does NOT provide atomicity or memory ordering for " +
                        "multithreading between CPU cores. On modern multi-core systems, two threads " +
                        "reading and writing a volatile variable can still race. For thread-safe " +
                        "shared state, use C11 _Atomic or C++ std::atomic instead."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── static ────────────────────────────────────────────────────────
            item {
                SectionCard(title = "static") {
                    BodyText(
                        "static has five different meanings in C/C++ depending on where it appears. " +
                        "The common thread: it either limits visibility to the current file or extends " +
                        "the lifetime of a variable to the whole program."
                    )
                    BodyText("1. Global static variable — file-scope, internal linkage:")
                    BodyText(
                        "Without static, a global variable has external linkage — other translation " +
                        "units can see and use it via extern. Adding static restricts it to the current " +
                        ".c/.cpp file only. The linker will not export it and other files cannot access it."
                    )
                    CodeBlock(
                        "// file_a.c\n" +
                        "int    shared = 0;         // external linkage — visible everywhere\n" +
                        "static int private_count;  // internal linkage — only visible in this file\n\n" +
                        "// file_b.c\n" +
                        "extern int shared;         // OK — resolves to file_a.c's shared\n" +
                        "extern int private_count;  // linker error — not exported"
                    )
                    BodyText("2. Global static function — file-scope, internal linkage:")
                    BodyText(
                        "Same idea applied to functions. static before a function definition makes it " +
                        "invisible outside the translation unit. Useful to prevent name collisions and " +
                        "to signal that a helper is an internal implementation detail."
                    )
                    CodeBlock(
                        "static void helper(void) { ... }  // only callable within this file\n" +
                        "void public_api(void) { helper(); } // exported, calls internal helper"
                    )
                    BodyText("3. Static local variable — function-scope, program lifetime:")
                    BodyText(
                        "A local variable declared static is created once and lives for the entire " +
                        "program run. It is initialized only on the first call to the function:\n" +
                        "- Plain types (int, etc.): zero-initialized before the first call.\n" +
                        "- C++ objects: the constructor runs the first time execution reaches " +
                        "  the declaration — not at program start.\n\n" +
                        "C++11 magic statics: the standard guarantees that initialization of a " +
                        "function-local static is thread-safe. If two threads race to call the " +
                        "function for the first time simultaneously, exactly one thread runs the " +
                        "initializer and the other waits. This is the foundation of the Meyers' " +
                        "Singleton pattern. Before C++11 this was a data race."
                    )
                    CodeBlock(
                        "int call_count() {\n" +
                        "    static int count = 0;   // initialized once, retains value between calls\n" +
                        "    return ++count;\n" +
                        "}\n" +
                        "// call_count() returns 1, 2, 3, ... across calls\n\n" +
                        "// C++ object — CTOR runs on first call (thread-safe in C++11)\n" +
                        "Logger& get_logger() {\n" +
                        "    static Logger instance;   // Meyers' Singleton\n" +
                        "    return instance;\n" +
                        "}"
                    )
                    BodyText("4 & 5. Static class member variable and static member function:")
                    BodyText(
                        "A static member variable belongs to the class itself, not to any instance — " +
                        "all objects share the same copy. It must be declared inside the class and " +
                        "defined (storage allocated) in exactly one .cpp file:\n" +
                        "- Plain types: initialized during the static initialization phase (before main).\n" +
                        "- C++ objects: the constructor runs during static initialization, in the " +
                        "  order that definitions appear across translation units (the Static " +
                        "  Initialization Order Fiasco — prefer function-local statics instead).\n" +
                        "- No automatic thread-safety lock for class-level statics (unlike function-" +
                        "  local statics). The programmer must protect them if needed.\n\n" +
                        "A static member function has no 'this' pointer and can only access static " +
                        "members or its own parameters. It can be called on the class name directly."
                    )
                    CodeBlock(
                        "// header: Counter.h\n" +
                        "class Counter {\n" +
                        "public:\n" +
                        "    static int  count;    // declaration only — no storage here\n" +
                        "    static void reset();  // static member function\n" +
                        "    void        increment() { count++; }\n" +
                        "};\n\n" +
                        "// source: Counter.cpp — exactly one definition\n" +
                        "int Counter::count = 0;   // storage + initialization\n\n" +
                        "void Counter::reset() {\n" +
                        "    count = 0;   // no 'this' — only static members accessible\n" +
                        "}\n\n" +
                        "// Usage\n" +
                        "Counter a, b;\n" +
                        "a.increment();          // count == 1\n" +
                        "b.increment();          // count == 2  (shared!)\n" +
                        "Counter::reset();       // count == 0  (call on class name)\n" +
                        "printf(\"%d\\n\", Counter::count);"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── extern ────────────────────────────────────────────────────────
            item {
                SectionCard(title = "extern") {
                    BodyText(
                        "extern is a declaration without a definition — it tells the compiler that a " +
                        "variable or function exists, and that its storage is allocated elsewhere (in " +
                        "another translation unit). The linker resolves the reference at link time. " +
                        "No storage is allocated by an extern declaration."
                    )
                    BodyText("Sharing a global variable across multiple files:")
                    CodeBlock(
                        "// globals.h — shared header\n" +
                        "extern int g_count;        // declaration: \"g_count exists somewhere\"\n" +
                        "extern void init_globals(); // function declarations are extern by default\n\n" +
                        "// globals.c — exactly one .c file provides the definition\n" +
                        "#include \"globals.h\"\n" +
                        "int g_count = 0;           // definition: storage allocated here\n\n" +
                        "void init_globals() { g_count = 100; }\n\n" +
                        "// other.c\n" +
                        "#include \"globals.h\"\n" +
                        "g_count++;                 // fine — linker resolves to globals.c"
                    )
                    BodyText(
                        "A common mistake: defining (not just declaring) a variable in a header and " +
                        "including it in multiple .c files. This causes a 'multiple definition' " +
                        "linker error because each translation unit allocates storage. The fix: " +
                        "declare with extern in the header; define in exactly one .c file."
                    )
                    BodyText("extern \"C\" in C++ — C linkage for mixed C/C++ projects:")
                    BodyText(
                        "C++ compilers mangle function names to encode parameter types into the symbol " +
                        "name (enabling overloading). extern \"C\" disables name mangling for the " +
                        "wrapped declarations, making them link with C object files and C-callable " +
                        "shared libraries."
                    )
                    CodeBlock(
                        "// Calling a C library from C++\n" +
                        "extern \"C\" {\n" +
                        "    int  c_lib_open(const char *path);    // C linkage\n" +
                        "    void c_lib_close(int handle);\n" +
                        "}\n\n" +
                        "// Exporting a C-callable API from a C++ shared library\n" +
                        "extern \"C\" void plugin_entry(void) {\n" +
                        "    // C++ code here, but symbol name is unmangled\n" +
                        "}\n\n" +
                        "// Headers designed for both C and C++:\n" +
                        "#ifdef __cplusplus\n" +
                        "extern \"C\" {\n" +
                        "#endif\n\n" +
                        "    void my_api_function(int x);\n\n" +
                        "#ifdef __cplusplus\n" +
                        "}\n" +
                        "#endif"
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
