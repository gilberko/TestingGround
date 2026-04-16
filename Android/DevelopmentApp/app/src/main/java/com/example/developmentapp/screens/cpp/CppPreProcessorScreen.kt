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
fun CppPreProcessorScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "C/C++ — C Pre-Processor",
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

            // ── What is the Pre-Processor ──────────────────────────────────
            item {
                SectionCard(title = "What Is the C Pre-Processor?") {
                    BodyText(
                        "Before the compiler sees your code, the C Pre-Processor (cpp) makes a first pass over " +
                        "the source file and processes all directives — lines that start with #. The output is " +
                        "a new, expanded C/C++ file with all directives replaced by their results. The compiler " +
                        "then receives this clean file with no # directives remaining."
                    )
                    BodyText(
                        "Directives covered here: #include, #define, #ifdef / #ifndef / #if / #endif / #else / #elif, " +
                        "#undef, variadic macros (__VA_ARGS__), predefined macros (__LINE__, __FILE__, ...), and #pragma."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── #include ───────────────────────────────────────────────────
            item {
                SectionCard(title = "#include") {
                    BodyText(
                        "The #include directive tells the pre-processor to insert the full contents of another " +
                        "file at that point in the source. Two forms exist:"
                    )
                    BodyText("  • <file> — searches the system include paths (standard library headers, compiler paths)")
                    BodyText("  • \"file\" — searches the current directory first, then the system paths")
                    CodeBlock(
                        "#include <stdio.h>       // system header\n" +
                        "#include <string>        // C++ standard library\n" +
                        "#include \"myheader.h\"    // local project header"
                    )
                    BodyText(
                        "Because #include pastes the file verbatim, including the same header twice would redefine " +
                        "everything in it. Include guards (or #pragma once) prevent this — see the #ifdef and " +
                        "#pragma sections below."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Conditional Compilation ────────────────────────────────────
            item {
                SectionCard(title = "Conditional Compilation: #ifdef / #ifndef / #if / #endif") {
                    BodyText(
                        "These directives let you include or exclude blocks of code at compile time, depending on " +
                        "whether a macro is defined or an expression is true."
                    )
                    BodyText("  • #ifdef NAME — true if NAME has been #defined")
                    BodyText("  • #ifndef NAME — true if NAME has NOT been #defined")
                    BodyText("  • #if EXPR — evaluates a constant integer expression")
                    BodyText("  • #elif EXPR — else-if branch")
                    BodyText("  • #else — fallback branch")
                    BodyText("  • #endif — closes the conditional block")
                    CodeBlock(
                        "// Classic include guard — prevents double-inclusion:\n" +
                        "#ifndef MY_HEADER_H\n" +
                        "#define MY_HEADER_H\n" +
                        "\n" +
                        "// ... header contents ...\n" +
                        "\n" +
                        "#endif  // MY_HEADER_H\n" +
                        "\n" +
                        "// Debug-only logging:\n" +
                        "#ifndef NDEBUG\n" +
                        "  #define LOG(msg) fprintf(stderr, \"%s\\n\", msg)\n" +
                        "#else\n" +
                        "  #define LOG(msg)  // expands to nothing in release\n" +
                        "#endif\n" +
                        "\n" +
                        "// Platform-specific code:\n" +
                        "#ifdef _WIN32\n" +
                        "  #include <windows.h>\n" +
                        "#elif defined(__linux__)\n" +
                        "  #include <unistd.h>\n" +
                        "#endif"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── #define constants ──────────────────────────────────────────
            item {
                SectionCard(title = "#define — Constants") {
                    BodyText(
                        "#define performs simple text substitution. Every occurrence of the defined name in the " +
                        "subsequent source is replaced by its replacement text before compilation."
                    )
                    CodeBlock(
                        "#define MAX_SIZE   1024\n" +
                        "#define PI         3.14159265\n" +
                        "#define APP_NAME   \"MyApp\"\n" +
                        "\n" +
                        "// Usage:\n" +
                        "char buf[MAX_SIZE];   // becomes: char buf[1024];\n" +
                        "double area = PI * r * r;"
                    )
                    BodyText(
                        "Unlike const variables, #define names have no type and no scope — they are global to the " +
                        "translation unit from the point of definition. In modern C++ prefer const or constexpr " +
                        "for typed constants, but #define is still common for compile-time flags and compatibility."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── #define macros with parameters ─────────────────────────────
            item {
                SectionCard(title = "#define — Macros with Parameters") {
                    BodyText(
                        "A macro can take parameters that are substituted textually into the replacement. " +
                        "Because substitution is purely textual — not evaluated first — you must wrap both the " +
                        "parameters and the whole macro in parentheses to avoid operator-precedence surprises."
                    )
                    CodeBlock(
                        "#define SQUARE(x)  ((x) * (x))\n" +
                        "\n" +
                        "int a = SQUARE(3);      // → ((3) * (3))    = 9  ✓\n" +
                        "int b = SQUARE(1 + 2);  // → ((1+2) * (1+2)) = 9  ✓\n" +
                        "\n" +
                        "// Without parentheses:\n" +
                        "#define BAD_SQUARE(x)  x * x\n" +
                        "int c = BAD_SQUARE(1 + 2);  // → 1 + 2 * 1 + 2 = 5  ✗"
                    )
                    BodyText(
                        "A macro definition must fit on a single logical line. To span multiple physical lines, " +
                        "end each line (except the last) with a backslash \\. There must be no spaces or tabs " +
                        "after the backslash — only the newline."
                    )
                    CodeBlock(
                        "#define SWAP(a, b, T)  \\\n" +
                        "    do {               \\\n" +
                        "        T tmp = (a);   \\\n" +
                        "        (a) = (b);     \\\n" +
                        "        (b) = tmp;     \\\n" +
                        "    } while (0)\n" +
                        "\n" +
                        "// Usage:\n" +
                        "int x = 3, y = 7;\n" +
                        "SWAP(x, y, int);   // x=7, y=3"
                    )
                    BodyText(
                        "The do { ... } while(0) wrapper is a common idiom for multi-statement macros — it makes " +
                        "the macro behave like a single statement so it works safely after if/else without braces."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── # and ## operators ─────────────────────────────────────────
            item {
                SectionCard(title = "# Stringification and ## Token Pasting") {
                    BodyText(
                        "Two special operators can be used inside macro definitions:"
                    )
                    BodyText(
                        "  • # (stringification) — placed before a parameter, it converts the argument to a " +
                        "string literal (wraps it in double quotes)."
                    )
                    BodyText(
                        "  • ## (token pasting / concatenation) — joins two tokens into one. Used to build " +
                        "identifiers or keywords dynamically."
                    )
                    CodeBlock(
                        "// # — Stringification\n" +
                        "#define STRINGIFY(x)  #x\n" +
                        "\n" +
                        "printf(\"%s\\n\", STRINGIFY(hello));   // prints: hello\n" +
                        "printf(\"%s\\n\", STRINGIFY(1 + 2));   // prints: 1 + 2\n" +
                        "\n" +
                        "// ## — Token pasting\n" +
                        "#define CONCAT(a, b)  a##b\n" +
                        "\n" +
                        "int CONCAT(my, Var) = 42;   // declares: int myVar = 42;\n" +
                        "\n" +
                        "// Combined — useful for debug printing:\n" +
                        "#define PRINT_VAR(v)  printf(#v \" = %d\\n\", v)\n" +
                        "int count = 5;\n" +
                        "PRINT_VAR(count);   // prints: count = 5"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Variadic macros ────────────────────────────────────────────
            item {
                SectionCard(title = "Variadic Macros — ... and __VA_ARGS__") {
                    BodyText(
                        "Since C99 and C++11, macros can accept a variable number of arguments. Use ... as the " +
                        "last parameter in the definition, then __VA_ARGS__ in the replacement to refer to all " +
                        "the extra arguments."
                    )
                    CodeBlock(
                        "#define LOG(fmt, ...)  printf(\"[LOG] \" fmt, __VA_ARGS__)\n" +
                        "\n" +
                        "LOG(\"x=%d y=%d\\n\", x, y);\n" +
                        "// expands to:\n" +
                        "// printf(\"[LOG] \" \"x=%d y=%d\\n\", x, y);\n" +
                        "\n" +
                        "// In C++20 / GCC extension — __VA_OPT__ handles the case\n" +
                        "// where no extra args are passed (avoids trailing comma):\n" +
                        "#define LOG2(fmt, ...)  printf(fmt __VA_OPT__(,) __VA_ARGS__)\n" +
                        "LOG2(\"hello\\n\");       // no extra args — no trailing comma"
                    )
                    BodyText(
                        "Variadic macros are widely used for logging, assertion, and debug helpers where the " +
                        "format string and its arguments need to be forwarded together."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Predefined macros ──────────────────────────────────────────
            item {
                SectionCard(title = "Predefined Macros") {
                    BodyText(
                        "The pre-processor provides several built-in macros that are replaced automatically. " +
                        "These are especially useful for diagnostic and logging code."
                    )
                    BodyText("  • __LINE__ — expands to the current source line number (integer)")
                    BodyText("  • __FILE__ — expands to the current source file name (string literal)")
                    BodyText("  • __DATE__ — compilation date as a string, e.g. \"Apr 16 2026\"")
                    BodyText("  • __TIME__ — compilation time as a string, e.g. \"14:30:00\"")
                    BodyText("  • __func__ — (C99 / C++11) name of the enclosing function (string literal)")
                    CodeBlock(
                        "// Error reporting with location:\n" +
                        "#define ASSERT(cond) \\\n" +
                        "    if (!(cond)) { \\\n" +
                        "        fprintf(stderr, \"Assertion failed: \" #cond \\\n" +
                        "                \" at %s:%d\\n\", __FILE__, __LINE__); \\\n" +
                        "        abort(); \\\n" +
                        "    }\n" +
                        "\n" +
                        "ASSERT(ptr != nullptr);\n" +
                        "// might print:\n" +
                        "// Assertion failed: ptr != nullptr at main.cpp:42\n" +
                        "\n" +
                        "// Logging function name:\n" +
                        "void myFunction() {\n" +
                        "    printf(\"Entering %s\\n\", __func__);\n" +
                        "    // prints: Entering myFunction\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── #undef ─────────────────────────────────────────────────────
            item {
                SectionCard(title = "#undef") {
                    BodyText(
                        "#undef removes a previously defined macro name. After #undef, the name is no longer " +
                        "defined and any subsequent use is treated as an ordinary identifier (or causes a " +
                        "compile error if the name was relied upon)."
                    )
                    BodyText(
                        "Common uses: safely redefining a macro with a new value, and removing a macro that " +
                        "was only needed for a specific section of code."
                    )
                    CodeBlock(
                        "#define BUFFER_SIZE  512\n" +
                        "// ... code using BUFFER_SIZE ...\n" +
                        "\n" +
                        "// Redefine with a larger value:\n" +
                        "#undef  BUFFER_SIZE\n" +
                        "#define BUFFER_SIZE  4096\n" +
                        "\n" +
                        "// Another pattern — only define if not already defined:\n" +
                        "#ifndef MAX_CONNECTIONS\n" +
                        "  #define MAX_CONNECTIONS  100\n" +
                        "#endif\n" +
                        "\n" +
                        "// Or force a known value regardless of prior definition:\n" +
                        "#undef  MAX_CONNECTIONS\n" +
                        "#define MAX_CONNECTIONS  200"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── #pragma ────────────────────────────────────────────────────
            item {
                SectionCard(title = "#pragma") {
                    BodyText(
                        "#pragma passes implementation-defined hints and instructions to the pre-processor or " +
                        "compiler. The meaning of a pragma is specific to the compiler — unknown pragmas are " +
                        "typically ignored (unlike unknown directives, which are errors)."
                    )
                    BodyText("#pragma once — include guard (pre-processor level):")
                    BodyText(
                        "Tells the pre-processor to include this file only once per translation unit, even if " +
                        "#included multiple times. It is simpler than the #ifndef guard pattern and is supported " +
                        "by all major compilers (GCC, Clang, MSVC), though it is technically a non-standard extension."
                    )
                    CodeBlock(
                        "// myheader.h\n" +
                        "#pragma once\n" +
                        "\n" +
                        "// header contents — safe to #include multiple times"
                    )
                    BodyText("#pragma comment (MSVC) — linker hint:")
                    BodyText(
                        "On Windows with MSVC, #pragma comment(lib, ...) instructs the linker to automatically " +
                        "link a specific library without needing to add it to the project settings."
                    )
                    CodeBlock(
                        "// Automatically link winsock library:\n" +
                        "#pragma comment(lib, \"ws2_32.lib\")\n" +
                        "\n" +
                        "// Automatically link a specific runtime:\n" +
                        "#pragma comment(lib, \"msvcrt.lib\")"
                    )
                    BodyText("#pragma warning (MSVC) — suppress specific warnings:")
                    CodeBlock(
                        "// Suppress 'deprecated function' warning (C4996):\n" +
                        "#pragma warning(disable: 4996)\n" +
                        "strcpy(dst, src);   // no warning\n" +
                        "#pragma warning(default: 4996)  // re-enable\n" +
                        "\n" +
                        "// Push/pop warning state (cleaner approach):\n" +
                        "#pragma warning(push)\n" +
                        "#pragma warning(disable: 4996)\n" +
                        "// ... code that triggers 4996 ...\n" +
                        "#pragma warning(pop)"
                    )
                    BodyText(
                        "GCC and Clang have equivalent mechanisms using _Pragma() or __attribute__ directives, " +
                        "though the syntax differs from MSVC pragmas."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
