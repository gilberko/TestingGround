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
fun CppSyntaxScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "C/C++ — Syntax",
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

            // ── Expressions and Statements ────────────────────────────────────
            item {
                SectionCard(title = "Expressions and Statements") {
                    BodyText(
                        "An expression is any combination of values, variables, operators, and function " +
                        "calls that evaluates to a value. A statement is an instruction that the program " +
                        "executes; most statements in C/C++ end with a semicolon."
                    )
                    CodeBlock(
                        "x + 1          // expression — evaluates to x+1, produces a value\n" +
                        "x = 5          // expression — assignment also produces a value (5)\n" +
                        "x = 5;         // statement — the expression becomes a complete instruction\n" +
                        "printf(\"hi\");  // statement — function call as a statement\n" +
                        ";              // empty statement — legal, does nothing"
                    )
                    BodyText(
                        "Blocks (compound statements) wrap multiple statements in braces {} and are " +
                        "treated as a single statement. Variables declared inside a block are local to it."
                    )
                    BodyText(
                        "Assignment (=) is an expression, not just a statement. The expression (a = b) " +
                        "copies b into a and evaluates to the assigned value. This enables chaining: " +
                        "a = b = c = 0 assigns 0 to all three (right to left), and patterns like " +
                        "while ((ch = getchar()) != '\\n') which reads and tests in one step. In C++, " +
                        "classes can define custom behaviour for operator= via operator overloading " +
                        "(covered in C++ 101)."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Variables and Basic Types ─────────────────────────────────────
            item {
                SectionCard(title = "Variables and Basic Types") {
                    BodyText(
                        "C has a small set of fundamental types. Size guarantees are loose — use " +
                        "<stdint.h> types (int32_t, uint8_t, etc.) when exact sizes matter."
                    )
                    CodeBlock(
                        "int    x = 42;          // signed integer, typically 32-bit\n" +
                        "unsigned int u = 3000;  // unsigned integer, same size as int\n" +
                        "char   c = 'A';         // single byte, may be signed or unsigned\n" +
                        "unsigned char uc = 255; // explicitly unsigned, range 0..255\n" +
                        "bool   flag = true;     // C99 _Bool; C++ has native bool\n" +
                        "                        // requires <stdbool.h> in C\n" +
                        "float  f = 3.14f;       // 32-bit IEEE 754\n" +
                        "double d = 3.14159;     // 64-bit IEEE 754\n\n" +
                        "// Declaration without initialization (value is indeterminate)\n" +
                        "int uninitialized;      // reading this is undefined behaviour"
                    )
                    BodyText(
                        "Variables have a type, a name, a storage duration (automatic/static/dynamic), " +
                        "and a scope (block, file, function). Local variables inside a function and " +
                        "function parameters both live on the stack. When execution leaves their " +
                        "enclosing {} block, the stack unwinds and those variables cease to exist. " +
                        "For parameters, this happens when the function returns — they are gone the " +
                        "moment the callee's frame is popped. Never return a pointer to a local " +
                        "variable: by the time the caller dereferences it, the stack frame has been " +
                        "reused and the memory contains garbage."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Variable Sizes ────────────────────────────────────────────────
            item {
                SectionCard(title = "Variable Sizes") {
                    BodyText(
                        "sizeof is a compile-time operator (not a function call) that returns the size " +
                        "of a type or variable in bytes. Its result type is size_t. Exception: in C99, " +
                        "sizeof on a Variable-Length Array (VLA) is evaluated at runtime because the " +
                        "size is not known until then."
                    )
                    CodeBlock(
                        "// sizeof on types and variables\n" +
                        "printf(\"%zu\\n\", sizeof(int));       // 4 on both 32-bit and 64-bit\n" +
                        "printf(\"%zu\\n\", sizeof(void *));    // 4 on 32-bit, 8 on 64-bit\n" +
                        "int x = 0;\n" +
                        "printf(\"%zu\\n\", sizeof(x));         // also works on a variable\n" +
                        "printf(\"%zu\\n\", sizeof x);          // parentheses optional for variables\n\n" +
                        "// Typical sizes on modern platforms (bytes):\n" +
                        "//                  32-bit   64-bit\n" +
                        "// char               1        1\n" +
                        "// short              2        2\n" +
                        "// int                4        4   <- stays 4 on 64-bit!\n" +
                        "// unsigned int       4        4\n" +
                        "// long  (Windows)    4        4   <- LLP64 model\n" +
                        "// long  (Linux)      4        8   <- LP64 model\n" +
                        "// long long          8        8\n" +
                        "// float              4        4\n" +
                        "// double             8        8\n" +
                        "// void *             4        8   <- pointer size changes\n" +
                        "// size_t             4        8   <- same as void* on modern ABIs"
                    )
                    BodyText(
                        "A common misconception: int does NOT become 8 bytes on 64-bit systems. It " +
                        "stays 4 bytes on all modern platforms. What changes is the pointer size " +
                        "(void *, int *, function pointers) and, on Linux, long.\n\n" +
                        "size_t: the result type of sizeof. On every modern ABI it equals the pointer " +
                        "size (4 or 8 bytes), because it must be able to represent the size of the " +
                        "largest possible object. The C standard does not formally equate them, but " +
                        "in practice they are always the same.\n\n" +
                        "Use <stdint.h> types when exact widths matter:"
                    )
                    CodeBlock(
                        "#include <stdint.h>\n\n" +
                        "int32_t  a;   // exactly 32-bit signed\n" +
                        "uint64_t b;   // exactly 64-bit unsigned\n" +
                        "intptr_t c;   // signed integer wide enough to hold a pointer\n" +
                        "uintptr_t d;  // unsigned version — safe pointer-to-integer cast\n" +
                        "size_t   n;   // unsigned, same width as void* on modern platforms"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── typedef ───────────────────────────────────────────────────────
            item {
                SectionCard(title = "typedef") {
                    BodyText(
                        "typedef creates an alias for an existing type. It does not create a new type — " +
                        "it just gives the existing type an additional name."
                    )
                    CodeBlock(
                        "typedef unsigned int  uint;     // 'uint' is now an alias for 'unsigned int'\n" +
                        "typedef char *        cstr_t;   // pointer-to-char alias\n" +
                        "typedef int (*fn_t)(int, int);  // alias for a function-pointer type\n\n" +
                        "uint x = 42;    // same as: unsigned int x = 42;\n" +
                        "cstr_t s = \"hi\"; // same as: char *s = \"hi\";"
                    )
                    BodyText(
                        "Most common use: giving struct and enum types a shorter name. In old C (pre-C99), " +
                        "using a struct type required repeating the struct keyword everywhere. typedef " +
                        "eliminates that:"
                    )
                    CodeBlock(
                        "// Without typedef — must write 'struct Point' everywhere\n" +
                        "struct Point { int x; int y; };\n" +
                        "struct Point p = {1, 2};  // verbose\n\n" +
                        "// With typedef — can write just 'Point'\n" +
                        "typedef struct Point { int x; int y; } Point;\n" +
                        "Point p = {1, 2};         // clean\n\n" +
                        "// Anonymous struct + typedef (common in C headers)\n" +
                        "typedef struct { float r; float g; float b; } Color;"
                    )
                    BodyText(
                        "In C++, struct and class tags are automatically available as type names — typedef " +
                        "is not needed. C++ also provides the using syntax which is often preferred for " +
                        "its cleaner readability, especially with templates:\n\n" +
                        "  using uint = unsigned int;           // C++ equivalent of typedef\n" +
                        "  using Matrix = vector<vector<int>>;  // easier to read than typedef"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Pointer Types ─────────────────────────────────────────────────
            item {
                SectionCard(title = "Pointer Types") {
                    BodyText(
                        "A pointer holds the memory address of another object. The type tells the compiler " +
                        "the size and interpretation of the value at that address."
                    )
                    CodeBlock(
                        "int  *p;        // pointer to int\n" +
                        "char *s;        // pointer to char (the basis of C strings)\n" +
                        "void *vp;       // pointer to void — generic pointer\n\n" +
                        "// void * is special:\n" +
                        "//   • any pointer type can be implicitly converted to void * in C\n" +
                        "//   • cannot be dereferenced directly (no size to read)\n" +
                        "//   • must be cast to a concrete type before use\n" +
                        "//   • used extensively for type-erased APIs (malloc, memcpy, qsort)\n\n" +
                        "void *mem = malloc(100);   // returns void *\n" +
                        "int  *arr = (int *)mem;    // cast to usable type"
                    )
                    BodyText(
                        "Pointer arithmetic: adding 1 to an int * advances by sizeof(int) bytes (e.g. 4), " +
                        "not by 1 byte. Adding 1 to a char * advances by 1 byte. void * arithmetic is " +
                        "illegal in standard C (no element size known)."
                    )
                    BodyText("NULL and nullptr:")
                    BodyText(
                        "NULL is a macro representing a null pointer — one that points to nothing. In C " +
                        "it expands to (void*)0; in C++ it is typically just 0 (an integer constant). " +
                        "Dereferencing a null pointer is undefined behaviour and crashes on all modern " +
                        "operating systems because address 0 is deliberately unmapped (the zero page).\n\n" +
                        "In modern C++ (C++11+), prefer nullptr. It is a keyword of type std::nullptr_t, " +
                        "not an integer. This matters for overload resolution: if you have both f(int*) " +
                        "and f(int), calling f(nullptr) picks f(int*) as expected. Calling f(NULL) picks " +
                        "f(int) because NULL is just 0 — a silent, hard-to-spot bug."
                    )
                    CodeBlock(
                        "int *p = NULL;      // C style — points to nothing\n" +
                        "int *q = nullptr;   // C++11 style — preferred\n\n" +
                        "if (p == nullptr)   // always check before dereferencing\n" +
                        "    return;\n" +
                        "*p = 42;            // safe now (if p is not null)"
                    )
                    BodyText("Function pointers:")
                    BodyText(
                        "A pointer can point to a function, not just data. The pointer type encodes the " +
                        "full function signature: return type and parameter types. Function pointers are " +
                        "used for callbacks, dispatch tables, and plugin-style architectures."
                    )
                    CodeBlock(
                        "int add(int a, int b) { return a + b; }\n" +
                        "int sub(int a, int b) { return a - b; }\n\n" +
                        "// Declaration: return_type (*name)(param_types)\n" +
                        "int (*fp)(int, int) = add;   // assign — no & needed for functions\n" +
                        "int result = fp(3, 4);        // call — same as add(3, 4)\n\n" +
                        "// typedef / using make the type readable\n" +
                        "typedef int (*BinaryOp)(int, int);       // C style\n" +
                        "using  BinaryOp = int (*)(int, int);     // C++11 style\n\n" +
                        "BinaryOp ops[] = { add, sub };           // dispatch table\n" +
                        "ops[0](10, 3);  // 13\n" +
                        "ops[1](10, 3);  // 7\n\n" +
                        "// Passing a function pointer as a callback\n" +
                        "void apply(int *arr, int n, int (*fn)(int)) {\n" +
                        "    for (int i = 0; i < n; i++) arr[i] = fn(arr[i]);\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Functions ─────────────────────────────────────────────────────
            item {
                SectionCard(title = "Functions") {
                    BodyText(
                        "A function groups reusable code under a name. It has a return type, a name, " +
                        "a parameter list, and a body. A declaration (prototype) tells the compiler the " +
                        "signature; a definition provides the body."
                    )
                    CodeBlock(
                        "// Declaration (prototype) — in a header or before use\n" +
                        "int add(int a, int b);\n\n" +
                        "// Definition\n" +
                        "int add(int a, int b) {\n" +
                        "    return a + b;\n" +
                        "}\n\n" +
                        "// void return type — function produces no value\n" +
                        "void print_hello(void) {\n" +
                        "    printf(\"Hello\\n\");\n" +
                        "    return;    // optional — bare return exits the function early\n" +
                        "}\n\n" +
                        "// A function with a non-void return type MUST return a value\n" +
                        "// on all code paths; omitting it is undefined behaviour"
                    )
                    BodyText(
                        "Parameters are passed by value — the function receives copies. To modify the " +
                        "caller's variable, pass a pointer. C++ adds pass-by-reference (int &ref)."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── main() ────────────────────────────────────────────────────────
            item {
                SectionCard(title = "The main() Function") {
                    BodyText(
                        "main() is the program entry point — the first function called after the C " +
                        "runtime (CRT) initializes. It must return int. The return value is the process " +
                        "exit code: 0 means success, non-zero means failure (the exact value is " +
                        "available to the shell or parent process)."
                    )
                    CodeBlock(
                        "// No command-line arguments\n" +
                        "int main(void) {\n" +
                        "    return 0;   // EXIT_SUCCESS\n" +
                        "}\n\n" +
                        "// With command-line arguments\n" +
                        "int main(int argc, char *argv[]) {\n" +
                        "    // argc: number of arguments (including program name)\n" +
                        "    // argv: array of C strings; argv[0] is the program name\n" +
                        "    for (int i = 0; i < argc; i++)\n" +
                        "        printf(\"%s\\n\", argv[i]);\n" +
                        "    return 0;\n" +
                        "}\n\n" +
                        "// Note: void main() is non-standard and should not be used"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Operators ─────────────────────────────────────────────────────
            item {
                SectionCard(title = "Arithmetic, Bitwise, and Boolean Operators") {
                    BodyText("Arithmetic operators work on numeric types:")
                    CodeBlock(
                        "a + b   // addition\n" +
                        "a - b   // subtraction\n" +
                        "a * b   // multiplication\n" +
                        "a / b   // division (integer division truncates toward zero)\n" +
                        "a % b   // modulo (remainder); result sign matches dividend in C99+"
                    )
                    BodyText("Bitwise operators work on the individual bits of integers:")
                    CodeBlock(
                        "a & b   // bitwise AND\n" +
                        "a | b   // bitwise OR\n" +
                        "a ^ b   // bitwise XOR  (NOT exponentiation — C has no ** operator)\n" +
                        "~a      // bitwise NOT (one's complement)\n" +
                        "a << n  // left shift  — multiply by 2^n (fast)\n" +
                        "a >> n  // right shift — for unsigned: divide by 2^n\n" +
                        "        //              for signed: implementation-defined (usually arithmetic)"
                    )
                    BodyText("Boolean (logical) operators return 0 or 1 and short-circuit:")
                    CodeBlock(
                        "a && b  // logical AND — b not evaluated if a is false\n" +
                        "a || b  // logical OR  — b not evaluated if a is true\n" +
                        "!a      // logical NOT"
                    )
                    BodyText(
                        "Operator precedence (high to low, partial list): unary (!,~,*,&), " +
                        "* / %, + -, << >>, < > <= >=, == !=, &, ^, |, &&, ||, ?:, = += -= …  " +
                        "When in doubt, use parentheses — they cost nothing and prevent bugs."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Address-of and Dereference ────────────────────────────────────
            item {
                SectionCard(title = "Address-of (&) and Dereference (*)") {
                    BodyText(
                        "& as a unary prefix operator yields the address of its operand (lvalue required). " +
                        "* as a unary prefix operator dereferences a pointer — reads or writes the value " +
                        "at the address stored in the pointer."
                    )
                    CodeBlock(
                        "int x = 10;\n" +
                        "int *p = &x;   // p holds the address of x\n" +
                        "*p = 20;       // write through pointer — x is now 20\n" +
                        "printf(\"%d\\n\", *p);  // read through pointer — prints 20\n\n" +
                        "// Passing by pointer to modify caller's variable\n" +
                        "void increment(int *n) { (*n)++; }\n" +
                        "increment(&x);   // x is now 21"
                    )
                    BodyText(
                        "Note: & also appears as the bitwise AND operator, and * also appears as the " +
                        "multiplication operator. Context (unary vs binary) distinguishes them. The " +
                        "compiler always parses them correctly; just be aware when reading code."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Other Operators ───────────────────────────────────────────────
            item {
                SectionCard(title = "Other Operators: Ternary, Comma, Parentheses") {
                    BodyText("Ternary (conditional) operator — a compact if-else expression:")
                    CodeBlock(
                        "int abs_val = (x >= 0) ? x : -x;\n" +
                        "// Equivalent to:\n" +
                        "// int abs_val; if (x >= 0) abs_val = x; else abs_val = -x;"
                    )
                    BodyText(
                        "Comma operator — evaluates both operands left to right; the value of the entire " +
                        "expression is the value of the right operand. Rarely used outside of for-loop " +
                        "init/increment clauses:"
                    )
                    CodeBlock(
                        "int a = (x = 3, x + 1);  // x assigned 3, then a = 4\n\n" +
                        "// Common in for loops:\n" +
                        "for (int i = 0, j = 10; i < j; i++, j--) { ... }\n" +
                        "// The i++, j-- uses comma to perform two increments"
                    )
                    BodyText(
                        "Parentheses control evaluation order. C does not guarantee left-to-right " +
                        "evaluation of function arguments — the order is unspecified. Parentheses " +
                        "override operator precedence but do not impose an evaluation order on " +
                        "subexpressions at the same precedence level."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── goto and Labels ───────────────────────────────────────────────
            item {
                SectionCard(title = "goto and Labels") {
                    BodyText(
                        "goto jumps unconditionally to a labeled statement within the same function. " +
                        "Labels are identifiers followed by a colon. goto cannot jump into a different " +
                        "function, and in C++ it cannot jump over a variable initialization."
                    )
                    CodeBlock(
                        "void process(int *data, int n) {\n" +
                        "    FILE *f = fopen(\"out.txt\", \"w\");\n" +
                        "    if (!f) goto cleanup;\n\n" +
                        "    int *buf = malloc(n * sizeof(int));\n" +
                        "    if (!buf) goto close_file;\n\n" +
                        "    // ... do work ...\n\n" +
                        "    free(buf);\n" +
                        "close_file:\n" +
                        "    fclose(f);\n" +
                        "cleanup:\n" +
                        "    return;\n" +
                        "}"
                    )
                    BodyText(
                        "goto is generally avoided in modern C++ (use RAII/destructors for cleanup), but " +
                        "it is idiomatic in C for the error-cleanup pattern shown above — it avoids deeply " +
                        "nested if-else chains. The Linux kernel uses this pattern extensively."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Inline Assembly ───────────────────────────────────────────────
            item {
                SectionCard(title = "Inline Assembly") {
                    BodyText(
                        "C and C++ allow embedding assembly instructions directly in source code. This is " +
                        "used for low-level hardware access, performance-critical routines, or operations " +
                        "with no C equivalent (e.g. reading CPU registers, atomic instructions)."
                    )
                    BodyText("Windows — MSVC __asm (x86 32-bit only):")
                    CodeBlock(
                        "// MSVC inline assembly — x86 only, not available in 64-bit builds\n" +
                        "int add_asm(int a, int b) {\n" +
                        "    __asm {\n" +
                        "        mov eax, a\n" +
                        "        add eax, b\n" +
                        "        // return value is in eax by convention\n" +
                        "    }\n" +
                        "}\n\n" +
                        "// In 64-bit MSVC, use intrinsics instead:\n" +
                        "#include <intrin.h>\n" +
                        "unsigned __int64 tsc = __rdtsc();  // read Time Stamp Counter"
                    )
                    BodyText("Linux — GCC/Clang extended asm (AT&T syntax):")
                    CodeBlock(
                        "// Extended asm: asm volatile(\"instructions\" : outputs : inputs : clobbers)\n" +
                        "int add_asm(int a, int b) {\n" +
                        "    int result;\n" +
                        "    asm volatile(\n" +
                        "        \"addl %2, %1\\n\"   // addl src, dst  (AT&T: src before dst)\n" +
                        "        \"movl %1, %0\\n\"\n" +
                        "        : \"=r\"(result)     // output: result in any register\n" +
                        "        : \"r\"(a), \"r\"(b)  // inputs: a and b in registers\n" +
                        "        :                  // no additional clobbered registers\n" +
                        "    );\n" +
                        "    return result;\n" +
                        "}\n\n" +
                        "// Read the x86-64 Time Stamp Counter\n" +
                        "uint64_t read_tsc(void) {\n" +
                        "    uint32_t lo, hi;\n" +
                        "    asm volatile(\"rdtsc\" : \"=a\"(lo), \"=d\"(hi));\n" +
                        "    return ((uint64_t)hi << 32) | lo;\n" +
                        "}"
                    )
                    BodyText(
                        "volatile prevents the compiler from removing or reordering the asm block as an " +
                        "optimization. The clobber list tells the compiler which registers and memory are " +
                        "modified by the asm, so it can save/restore them correctly. Use \"memory\" in the " +
                        "clobber list if the asm reads or writes memory not described by the operands."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Declaration and Definition ────────────────────────────────
            item {
                SectionCard(title = "Declaration and Definition") {
                    BodyText(
                        "A declaration tells the compiler that a name exists and describes its type " +
                        "or signature, without providing a body or storage. A definition provides " +
                        "the actual implementation or memory allocation. A name can be declared many " +
                        "times but defined only once."
                    )
                    CodeBlock(
                        "// Function declaration (prototype) — no body\nvoid processData(int count, float* buffer);\n\n" +
                        "// Function definition — body provided\nvoid processData(int count, float* buffer) {\n" +
                        "    // implementation\n}"
                    )
                    BodyText(
                        "Why declarations matter: in C/C++, a name must be declared before it is used. " +
                        "Placing a function prototype at the top of a file (or in a header) lets code " +
                        "below call it even when the definition is in a different .cpp file. The linker " +
                        "connects the call to the definition at link time."
                    )
                    BodyText(
                        "When a declaration appears above the code that uses it — so the lines below " +
                        "can see the name — it is commonly called a forward declaration. It declares " +
                        "the name \"forward\" for everything that follows. Examples for functions, " +
                        "structs, and classes:"
                    )
                    CodeBlock(
                        "// Forward declaration of a function\nvoid render(int frame);\n\n" +
                        "int main() {\n    render(0);   // OK — prototype seen above\n}\n\n" +
                        "void render(int frame) { /* ... */ }  // definition can appear below"
                    )
                    CodeBlock(
                        "// Forward declaration of a struct\nstruct Node;\n\n" +
                        "struct Node* ptr;    // OK: pointer — pointer size is always known\n" +
                        "// struct Node obj;  // ERROR: size unknown, cannot allocate\n\n" +
                        "struct Node {        // definition — now size is known\n    int value;\n    struct Node* next;\n};"
                    )
                    CodeBlock(
                        "// Forward declaration of a class\nclass Engine;\n\n" +
                        "Engine* e;           // OK: pointer to incomplete type\n" +
                        "// Engine obj;       // ERROR: full definition needed\n" +
                        "// e->start();       // ERROR: members not visible without definition\n\n" +
                        "class Engine {       // definition\npublic:\n    void start();\n};"
                    )
                    BodyText(
                        "Forward declarations are especially useful for breaking circular header " +
                        "dependencies: if A.h and B.h each need to reference the other's type, " +
                        "a forward declaration lets one header mention the type without including " +
                        "the other header."
                    )
                    BodyText(
                        "The \"must appear above the use\" rule: at file scope, declarations or " +
                        "definitions must appear textually before the first use. If a function " +
                        "definition appears below the call site in the same file, the compiler will " +
                        "not find it unless a prototype appears first."
                    )
                    BodyText(
                        "Exception — class member functions: this rule is relaxed inside a class body. " +
                        "The C++ standard specifies that member function bodies are late-parsed: the " +
                        "compiler processes the entire class definition first, then goes back to parse " +
                        "the function bodies. This means a member function can freely call other " +
                        "member functions or reference member variables declared anywhere in the same " +
                        "class, even if they appear textually after the function:"
                    )
                    CodeBlock(
                        "class Foo {\n    void a() {\n        b();       // OK — b() is declared below\n" +
                        "        x = 10;    // OK — x is declared below\n    }\n    void b() {}\n    int x;\n};"
                    )
                    BodyText(
                        "Out-of-line definitions (void Foo::a() { ... } written outside the class) " +
                        "also see all members, because they appear after the complete class definition. " +
                        "This late-parsing rule applies only to member function bodies — default " +
                        "argument values and base-class specifiers are evaluated at the point they appear."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
