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
fun CppMoreVariableTypesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "C/C++ — More Variable Types",
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

            // ── Arrays ────────────────────────────────────────────────────────
            item {
                SectionCard(title = "Arrays") {
                    BodyText(
                        "An array is a contiguous block of memory holding a fixed number of elements of " +
                        "the same type. All elements are laid out one after another with no gaps " +
                        "(for basic types — structs may have internal padding, covered below)."
                    )
                    BodyText(
                        "The size must be a compile-time constant in standard C (C99 adds variable-length " +
                        "arrays as an optional feature, but they are best avoided). You can specify the " +
                        "size explicitly or let the compiler infer it from the initializer:"
                    )
                    CodeBlock(
                        "int arr[5];                  // 5 ints, uninitialized\n" +
                        "int arr[5] = {1, 2, 3, 4, 5}; // 5 ints, fully initialized\n" +
                        "int arr[5] = {1, 2};          // remaining 3 are zero-initialized\n" +
                        "int arr[]  = {1, 2, 3, 4};    // size inferred: 4 elements\n" +
                        "int zeros[100] = {0};          // all 100 elements set to 0\n\n" +
                        "// Element access — zero-based index\n" +
                        "arr[0] = 10;\n" +
                        "int x = arr[3];    // fourth element\n\n" +
                        "// Array size at compile time\n" +
                        "int n = sizeof(arr) / sizeof(arr[0]);  // total bytes / element bytes"
                    )
                    BodyText(
                        "Arrays decay to a pointer to their first element when passed to functions — " +
                        "the size information is lost. Always pass the size separately:"
                    )
                    CodeBlock(
                        "void print_array(int *arr, int n) {\n" +
                        "    for (int i = 0; i < n; i++) printf(\"%d \", arr[i]);\n" +
                        "}\n" +
                        "print_array(arr, 4);   // sizeof inside print_array would be wrong"
                    )
                    BodyText("Multi-dimensional arrays are arrays of arrays — still contiguous in memory:")
                    CodeBlock(
                        "int matrix[3][4];           // 3 rows, 4 columns — 12 ints total\n" +
                        "int m[2][3] = {\n" +
                        "    {1, 2, 3},              // row 0\n" +
                        "    {4, 5, 6}               // row 1\n" +
                        "};\n" +
                        "m[1][2] = 9;               // row 1, column 2\n\n" +
                        "// Memory layout (row-major): 1 2 3 4 5 6 — all contiguous"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Structs ───────────────────────────────────────────────────────
            item {
                SectionCard(title = "Structs") {
                    BodyText(
                        "A struct groups variables of different types into a single named unit. The " +
                        "declaration syntax is flexible: you can define the struct and declare variables " +
                        "of it in one step, or separately. You can also omit the struct name (anonymous " +
                        "struct) or the variable list."
                    )
                    CodeBlock(
                        "// Define struct and declare a variable in one statement\n" +
                        "struct Point { int x; int y; } p1, p2;\n\n" +
                        "// Define struct only (no variable yet)\n" +
                        "struct Color { unsigned char r, g, b; };\n" +
                        "struct Color red = {255, 0, 0};\n\n" +
                        "// Anonymous struct — useful with typedef\n" +
                        "typedef struct { float real; float imag; } Complex;\n" +
                        "Complex c = {1.0f, 2.0f};\n\n" +
                        "// Accessing members with . (direct) or -> (through pointer)\n" +
                        "p1.x = 3;\n" +
                        "struct Point *pp = &p1;\n" +
                        "pp->y = 7;   // same as (*pp).y = 7"
                    )
                    BodyText(
                        "In old C (and still required in C89/C90), you must write struct Point every time " +
                        "you use the type — the tag name alone (Point) is not recognized without typedef. " +
                        "In C++ the struct keyword is not needed after the definition; the tag name " +
                        "alone is a valid type name."
                    )
                    BodyText(
                        "Structs are passed by value by default — the entire struct is copied on each " +
                        "call. For large structs this is expensive. C code therefore very commonly passes " +
                        "a pointer to the struct instead:"
                    )
                    CodeBlock(
                        "// Pass by value — entire struct copied (fine for small structs)\n" +
                        "double distance(struct Point a, struct Point b) {\n" +
                        "    int dx = a.x - b.x, dy = a.y - b.y;\n" +
                        "    return sqrt(dx*dx + dy*dy);\n" +
                        "}\n\n" +
                        "// Pass by pointer — no copy, caller's struct can be modified\n" +
                        "void translate(struct Point *p, int dx, int dy) {\n" +
                        "    p->x += dx;\n" +
                        "    p->y += dy;\n" +
                        "}\n\n" +
                        "// Pass by const pointer — no copy, but guarantees no modification\n" +
                        "void print_point(const struct Point *p) {\n" +
                        "    printf(\"(%d, %d)\", p->x, p->y);\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Bitfields ─────────────────────────────────────────────────────
            item {
                SectionCard(title = "Bitfields") {
                    BodyText(
                        "Bitfields let you declare struct members that occupy a specific number of bits " +
                        "instead of a full type's worth of storage. This is used to pack multiple small " +
                        "values into a single word — useful for hardware registers, protocol headers, " +
                        "and compact flags."
                    )
                    CodeBlock(
                        "struct Flags {\n" +
                        "    unsigned int active  : 1;   //  1 bit\n" +
                        "    unsigned int mode    : 3;   //  3 bits (values 0–7)\n" +
                        "    unsigned int priority: 4;   //  4 bits (values 0–15)\n" +
                        "    // total: 8 bits used out of a 32-bit unsigned int\n" +
                        "};\n\n" +
                        "struct Flags f;\n" +
                        "f.active   = 1;\n" +
                        "f.mode     = 5;\n" +
                        "f.priority = 3;"
                    )
                    BodyText(
                        "Grouping rule: bitfields must be grouped by their underlying type. All int " +
                        "bitfields are packed into one int-sized unit; when a different type appears, " +
                        "a new unit begins. Mixing int and unsigned int is allowed (both are 32-bit), " +
                        "but mixing int and short starts a new unit."
                    )
                    CodeBlock(
                        "struct Packed {\n" +
                        "    int a : 10;   // \\\n" +
                        "    int b : 10;   //  > packed into one 32-bit int — uses 32 bits total\n" +
                        "    int c : 12;   // /   (10+10+12 = 32, perfect fit)\n" +
                        "};\n\n" +
                        "struct Wasteful {\n" +
                        "    int  a : 10;   // first int unit — 10 bits used, 22 bits padding\n" +
                        "    char b : 4;    // new unit (different type) — 4 bits used, 4 bits padding\n" +
                        "};  // total: 8 bytes even though only 14 bits carry data"
                    )
                    BodyText(
                        "Partial fill: if you declare int a : 10 alone, the compiler allocates a full " +
                        "32-bit int (the smallest addressable unit for that type), uses 10 bits for a, " +
                        "and leaves 22 bits as padding. You can force the padding to show up explicitly " +
                        "with an unnamed bitfield: int : 22."
                    )
                    BodyText(
                        "Limitations:\n" +
                        "  • You cannot take the address of a bitfield (&f.active is a compile error) — " +
                        "bitfields do not have individual addresses; they share storage with adjacent fields.\n" +
                        "  • Bitfield layout (bit order within a word, across-byte boundaries) is " +
                        "implementation-defined. Do not rely on a specific layout for binary protocols — " +
                        "use explicit shifts and masks instead."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Alignment and Packing ─────────────────────────────────────────
            item {
                SectionCard(title = "Alignment and Packing") {
                    BodyText(
                        "Processors read memory most efficiently when values are aligned — an int at an " +
                        "address divisible by 4, a double at one divisible by 8, etc. To maintain " +
                        "alignment, the compiler inserts padding bytes between struct members and at " +
                        "the end of the struct."
                    )
                    CodeBlock(
                        "struct Padded {\n" +
                        "    char  a;    // 1 byte at offset 0\n" +
                        "    // 3 bytes padding inserted here\n" +
                        "    int   b;    // 4 bytes at offset 4\n" +
                        "    char  c;    // 1 byte at offset 8\n" +
                        "    // 3 bytes padding at end (so struct size is a multiple of 4)\n" +
                        "};  // sizeof = 12, not 6\n\n" +
                        "// Reordering to minimize padding:\n" +
                        "struct Efficient {\n" +
                        "    int   b;    // 4 bytes at offset 0\n" +
                        "    char  a;    // 1 byte at offset 4\n" +
                        "    char  c;    // 1 byte at offset 5\n" +
                        "    // 2 bytes padding at end\n" +
                        "};  // sizeof = 8"
                    )
                    BodyText(
                        "To eliminate padding (for binary I/O or hardware register maps), use packing " +
                        "pragmas or attributes. Accessing unaligned fields through a packed pointer may " +
                        "be slow or trap on some architectures (e.g. ARM without hardware alignment support)."
                    )
                    CodeBlock(
                        "// MSVC\n" +
                        "#pragma pack(push, 1)\n" +
                        "struct WireFormat { char a; int b; char c; };  // sizeof = 6, no padding\n" +
                        "#pragma pack(pop)\n\n" +
                        "// GCC / Clang\n" +
                        "struct WireFormat { char a; int b; char c; } __attribute__((packed));\n\n" +
                        "// C11 standard way to query / control alignment\n" +
                        "_Alignas(16) float vec[4];  // 16-byte aligned (SIMD requirement)\n" +
                        "_Alignof(double);           // alignment requirement of double (usually 8)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Little Endian and Big Endian ──────────────────────────────────
            item {
                SectionCard(title = "Little Endian and Big Endian") {
                    BodyText(
                        "Endianness describes the byte order used to store multi-byte integers in memory. " +
                        "Consider the 32-bit value 0x01020304:"
                    )
                    CodeBlock(
                        "// Little Endian (x86, x86-64, ARM in default mode)\n" +
                        "// Least significant byte stored at the lowest address\n" +
                        "Address:  0x00  0x01  0x02  0x03\n" +
                        "Byte:     0x04  0x03  0x02  0x01\n\n" +
                        "// Big Endian (network byte order, older MIPS, SPARC, PowerPC)\n" +
                        "// Most significant byte stored at the lowest address\n" +
                        "Address:  0x00  0x01  0x02  0x03\n" +
                        "Byte:     0x01  0x02  0x03  0x04"
                    )
                    BodyText(
                        "This matters when:\n" +
                        "  • Reading binary files or network packets on a different-endian machine\n" +
                        "  • Casting between pointer types (e.g. int* to char* to inspect individual bytes)\n" +
                        "  • Serializing/deserializing data structures to disk or network\n\n" +
                        "Network protocols define big-endian (\"network byte order\"). POSIX provides " +
                        "htonl/htons (host to network) and ntohl/ntohs (network to host) for conversion."
                    )
                    CodeBlock(
                        "#include <stdint.h>\n\n" +
                        "// Detect endianness at runtime\n" +
                        "uint32_t val = 1;\n" +
                        "int is_little = *(uint8_t *)&val;  // 1 on little, 0 on big endian\n\n" +
                        "// Manual byte swap (or use __builtin_bswap32 on GCC/Clang)\n" +
                        "uint32_t bswap32(uint32_t x) {\n" +
                        "    return ((x & 0xFF000000) >> 24) |\n" +
                        "           ((x & 0x00FF0000) >>  8) |\n" +
                        "           ((x & 0x0000FF00) <<  8) |\n" +
                        "           ((x & 0x000000FF) << 24);\n" +
                        "}"
                    )
                    BodyText(
                        "Most desktop and server hardware today is little-endian. Many embedded targets " +
                        "(ARM Cortex-M, Cortex-A in default configuration) are also little-endian. If " +
                        "you always stay on one machine and never serialize data to disk or network, " +
                        "endianness is invisible. It only bites you at system boundaries."
                    )
                }
            }
            // ── Enum ──────────────────────────────────────────────────────────
            item {
                SectionCard(title = "Enum") {
                    BodyText(
                        "An enum (enumeration) defines a named integer type with a fixed set of " +
                        "named constants. Internally the constants are just integers — the enum tag " +
                        "is not a strong type in C."
                    )
                    BodyText(
                        "Default numbering starts at 0 and increments by 1. You can set any value " +
                        "explicitly; subsequent constants continue from that value."
                    )
                    CodeBlock(
                        "enum Direction { NORTH, SOUTH, EAST, WEST };  // 0,1,2,3\n\n" +
                        "enum Status {\n" +
                        "    OK        = 200,\n" +
                        "    MOVED     = 301,\n" +
                        "    NOT_FOUND = 404,\n" +
                        "    ERROR     = 500\n" +
                        "};\n\n" +
                        "Direction d = NORTH;\n" +
                        "int val = d;               // implicit conversion to int\n\n" +
                        "// sizeof(enum) == sizeof(underlying type) — int by default (4 bytes)\n" +
                        "printf(\"%zu\\n\", sizeof(Direction));  // typically 4"
                    )
                    BodyText(
                        "C-style enums are not type-safe: you can assign any integer to an enum " +
                        "variable, and two different enum types can be compared directly because " +
                        "both decay to int."
                    )
                    BodyText(
                        "C++ enum class (scoped enum, C++11):\n\n" +
                        "enum class is a strongly-typed enum. Constants must be qualified with the " +
                        "class name (Color::Red), there is no implicit conversion to int, and two " +
                        "different enum classes cannot be compared. You can also specify the " +
                        "underlying integer type explicitly."
                    )
                    CodeBlock(
                        "enum class Color { Red, Green, Blue };           // underlying: int\n" +
                        "enum class Color2 : uint8_t { Red, Green, Blue }; // underlying: uint8_t\n\n" +
                        "Color c = Color::Red;             // must qualify\n" +
                        "// int x = c;                    // compile error — no implicit conversion\n" +
                        "int x = static_cast<int>(c);     // explicit cast required\n\n" +
                        "enum class Dir { N, S, E, W };\n" +
                        "// if (c == Dir::N) {}           // compile error — different types\n\n" +
                        "printf(\"%zu\\n\", sizeof(Color2)); // 1 — sizeof(uint8_t)"
                    )
                    BodyText(
                        "Prefer enum class in new C++ code — it prevents accidental integer " +
                        "arithmetic on enum values and avoids polluting the enclosing namespace " +
                        "with constant names."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Union ─────────────────────────────────────────────────────────
            item {
                SectionCard(title = "Union") {
                    BodyText(
                        "A union is like a struct, except all members share the same block of " +
                        "storage. Every member starts at offset 0. Only one member holds a valid " +
                        "value at any given time — writing to one member and then reading another " +
                        "is type-punning and technically undefined behaviour in C++ (though it is " +
                        "defined in C and widely used in practice)."
                    )
                    BodyText(
                        "Size of a union:\n\n" +
                        "sizeof(union) equals the size of the largest member, rounded up to " +
                        "satisfy the alignment requirement of the most-aligned member. You can " +
                        "absolutely add members of different sizes — that is the entire point. " +
                        "The union reserves enough storage for whichever member is the biggest."
                    )
                    CodeBlock(
                        "union Data {\n" +
                        "    int   i;        // 4 bytes\n" +
                        "    float f;        // 4 bytes\n" +
                        "    char  bytes[4]; // 4 bytes\n" +
                        "};  // sizeof = 4  (all members same size)\n\n" +
                        "union Mixed {\n" +
                        "    char   c;   // 1 byte\n" +
                        "    int    i;   // 4 bytes\n" +
                        "    double d;   // 8 bytes\n" +
                        "};  // sizeof = 8  (largest member wins)\n\n" +
                        "union Mixed m;\n" +
                        "m.d = 3.14;           // write double\n" +
                        "printf(\"%f\\n\", m.d); // read double — valid\n" +
                        "// printf(\"%d\\n\", m.i); // reading m.i here is type-punning"
                    )
                    BodyText(
                        "Common use cases:\n\n" +
                        "  • Type punning — inspect the raw bytes of a float as a uint32_t to " +
                        "examine its IEEE 754 bit pattern. Use memcpy in C++ to be strictly " +
                        "conforming; many compilers support union punning as an extension.\n\n" +
                        "  • Tagged union (discriminated union) — a struct containing a union and " +
                        "an enum tag. The tag records which member is currently active."
                    )
                    CodeBlock(
                        "// Tagged union — common C pattern\n" +
                        "struct Value {\n" +
                        "    enum { INT, FLOAT, STRING } tag;\n" +
                        "    union {\n" +
                        "        int         i;\n" +
                        "        float       f;\n" +
                        "        const char *s;\n" +
                        "    };\n" +
                        "};\n\n" +
                        "struct Value v;\n" +
                        "v.tag = Value::FLOAT;\n" +
                        "v.f   = 3.14f;\n\n" +
                        "if (v.tag == Value::FLOAT) printf(\"%f\\n\", v.f);"
                    )
                    BodyText(
                        "C++ restrictions: if any member has a non-trivial constructor, copy " +
                        "constructor, or destructor, the union's corresponding special member " +
                        "function is deleted. You must manually use placement-new to construct " +
                        "and explicit destructor calls to destroy members. For a type-safe " +
                        "alternative, use std::variant (C++17)."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
