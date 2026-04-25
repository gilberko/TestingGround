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
fun CppExpressionTypesCastingScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Expression Types and Casting",
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
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Literal Expression Types") {
                    BodyText("Every literal in C/C++ has a specific type determined by its syntax. The compiler uses this type for arithmetic, overload resolution, and implicit conversions.")
                    CodeBlock("""
// Integer literals
40          // int
40L         // long
40LL        // long long
40U         // unsigned int
40UL        // unsigned long
40ULL       // unsigned long long

// Floating-point literals
40.0        // double  (default)
40.0f       // float
40.0L       // long double

// Character literals
'c'         // char  (treated as int in C, char in C++)
L'c'        // wchar_t

// String literals
"abcd"      // const char*   (null-terminated)
L"abcd"     // const wchar_t*
u8"abcd"    // const char8_t*  (C++20, UTF-8)
u"abcd"     // const char16_t* (C++11, UTF-16)
U"abcd"     // const char32_t* (C++11, UTF-32)

// Boolean
true        // bool
false       // bool

// Null pointer (C++11)
nullptr     // std::nullptr_t  (converts to any pointer or bool)
NULL        // implementation-defined integer constant (old style)
                    """.trimIndent())
                    BodyText("Hexadecimal (0xFF), octal (077), and binary (0b1010, C++14) integer literals follow the same suffix rules for type selection.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("C-Style Casts") {
                    BodyText("The C cast syntax (T)expr applies the first conversion that compiles, silently combining static, const, and reinterpret conversions with no safety checks.")
                    CodeBlock("""
double d = 3.14;
int    n = (int)d;           // truncates to 3

const int* cp = &n;
int* p = (int*)cp;           // silently strips const — dangerous!

void* vp = &n;
int*  ip = (int*)vp;         // reinterpret void*

// Can even cast between unrelated pointer types — no warning
float* fp = (float*)&n;      // undefined behaviour if you dereference
                    """.trimIndent())
                    BodyText("C-style casts are still valid C++ but should be avoided — they are hard to search for in code and hide what kind of conversion is happening. Prefer the named C++ casts.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("static_cast") {
                    BodyText("static_cast<T>(expr) is checked at compile time. It handles well-defined conversions only: numeric conversions, pointer up/downcasts in a class hierarchy, and void* conversions.")
                    CodeBlock("""
// Numeric conversions
double d   = 3.99;
int    n   = static_cast<int>(d);    // 3  (truncates, no UB)

int    i   = 65;
char   c   = static_cast<char>(i);   // 'A'

// Upcast (always safe)
Derived* der = new Derived();
Base*    bas = static_cast<Base*>(der);

// Downcast (no runtime check — you must know the type is correct)
Derived* back = static_cast<Derived*>(bas);  // UB if wrong type

// void* round-trip
void* vp  = static_cast<void*>(&n);
int*  ip  = static_cast<int*>(vp);   // safe: same type as original
                    """.trimIndent())
                    BodyText("static_cast cannot remove const/volatile, and cannot cast between unrelated pointer types. These limitations are features — they prevent accidents that a C-style cast would silently allow.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("const_cast — and Why Modifying const Is Undefined Behaviour") {
                    BodyText("const_cast<T>(expr) is the only C++ cast that can add or remove the const and volatile qualifiers. Every other cast preserves them.")
                    CodeBlock("""
void print(char* s) { printf("%s\n", s); }

const char* msg = "hello";
print(const_cast<char*>(msg));   // removes const to call legacy API
                    """.trimIndent())
                    BodyText("Removing const is safe only when the underlying object was not originally declared const — for example, a non-const object was passed through a const reference and you know you need to modify it.")
                    CodeBlock("""
// SAFE — original object is non-const
int x = 42;
const int* cp = &x;
*const_cast<int*>(cp) = 99;    // OK: x was non-const to begin with

// UNDEFINED BEHAVIOUR — original object IS const
const int y = 42;
const int* cy = &y;
*const_cast<int*>(cy) = 99;    // UB: modifying a truly-const object
                    """.trimIndent())
                    BodyText("Why is the second case UB? The compiler sees that y is const and may fold its value directly into the machine code, store it in read-only memory, or cache it in a register at the point of declaration and never re-read it from the address. Modifying the storage through const_cast breaks those assumptions silently — the program may appear to work in debug builds but produce wrong results in optimised builds.")
                    BodyText("In short: const_cast does not make a const object mutable. It only lets you call an API that forgot to mark its parameter const, when you are certain the object will not actually be written to.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("dynamic_cast") {
                    BodyText("dynamic_cast<T>(expr) performs a runtime type check using RTTI (Run-Time Type Information). It requires the source type to have at least one virtual function (i.e., be polymorphic).")
                    BodyText("If the cast fails: returns nullptr for pointer targets, throws std::bad_cast for reference targets.")
                    CodeBlock("""
struct Animal  { virtual ~Animal() {} };
struct Dog     : Animal { void bark() {} };
struct Cat     : Animal { void meow() {} };

Animal* a = new Dog();

// Safe downcast — runtime check
Dog* d = dynamic_cast<Dog*>(a);   // succeeds, d != nullptr
Cat* c = dynamic_cast<Cat*>(a);   // fails,    c == nullptr

if (d) d->bark();

// Reference version — throws on failure
try {
    Dog& ref = dynamic_cast<Dog&>(*a);   // OK
    Cat& bad = dynamic_cast<Cat&>(*a);   // throws std::bad_cast
} catch (const std::bad_cast& e) {
    // handle
}
                    """.trimIndent())
                    BodyText("dynamic_cast is slower than static_cast (RTTI walk at runtime). Use it when you genuinely do not know the concrete type at compile time and need safe downcasting.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("reinterpret_cast") {
                    BodyText("reinterpret_cast<T>(expr) reinterprets the raw bit pattern of a value as a completely different type. No conversion takes place — only the type label changes. It is the most dangerous cast and should be used rarely.")
                    CodeBlock("""
// Inspect the bit representation of a float
float  f   = 3.14f;
uint32_t bits = *reinterpret_cast<uint32_t*>(&f);
printf("0x%08X\n", bits);   // 0x4048F5C3

// Hardware register access (embedded / driver code)
volatile uint32_t* reg =
    reinterpret_cast<volatile uint32_t*>(0xDEAD0000);
*reg = 0x1;

// Convert between function pointer and void* (implementation-defined)
void (*fn)() = &someFunc;
void* ptr = reinterpret_cast<void*>(fn);
                    """.trimIndent())
                    BodyText("The common legitimate uses are: type punning for serialisation/bit inspection (better with memcpy in strict-aliasing-compliant code), hardware register mapping in driver/embedded code, and certain low-level data structure tricks. Anywhere else, a safer cast or a design change is preferable.")
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
