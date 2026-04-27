package com.example.developmentapp.screens.stl

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
fun StlMemoryStringsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "C/C++ — Memory and Strings",
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
        LazyColumn(modifier = Modifier.padding(innerPadding).padding(horizontal = 16.dp)) {
            item { Spacer(Modifier.height(16.dp)) }

            // ── C memory functions ────────────────────────────────────────────
            item {
                SectionCard(title = "C Memory Functions — <string.h> / <cstring>") {
                    BodyText("These four functions operate on raw bytes — they know nothing about types or null terminators. All require #include <string.h> in C or <cstring> in C++.")
                    BodyText("memset(ptr, c, n) — fills n bytes starting at ptr with the byte value c. Commonly used to zero a buffer. c is interpreted as unsigned char.")
                    BodyText("memcpy(dst, src, n) — copies exactly n bytes from src to dst. The regions must NOT overlap; behaviour is undefined if they do. Typically the fastest copy because the compiler/library can use SIMD without guarding for overlap.")
                    BodyText("memmove(dst, src, n) — copies exactly n bytes from src to dst. Safe when src and dst overlap — it detects the overlap direction and copies accordingly (backward when dst > src and regions overlap). Slightly slower than memcpy in some implementations.")
                    BodyText("Rule of thumb: use memcpy when you are certain the regions are disjoint (e.g., copying between two separate buffers). Use memmove when moving data within the same buffer or when overlap is possible.")
                    BodyText("memcmp(ptr1, ptr2, n) — compares n bytes lexicographically. Returns 0 if equal, a negative value if ptr1 < ptr2, a positive value if ptr1 > ptr2. Does not stop at '\\0'.")
                    CodeBlock(
                        "#include <string.h>\n" +
                        "\n" +
                        "char buf[64];\n" +
                        "memset(buf, 0, sizeof(buf));        // zero the whole buffer\n" +
                        "memset(buf, 0xFF, 4);               // fill first 4 bytes with 0xFF\n" +
                        "\n" +
                        "char src[] = \"Hello\";\n" +
                        "char dst[16];\n" +
                        "memcpy(dst, src, 6);                // copies 'H','e','l','l','o','\\0'\n" +
                        "\n" +
                        "// Shift array elements left by 1 — regions overlap → memmove\n" +
                        "int arr[] = {1, 2, 3, 4, 5};\n" +
                        "memmove(arr, arr + 1, 4 * sizeof(int)); // {2,3,4,5,5}\n" +
                        "\n" +
                        "int r = memcmp(\"abc\", \"abd\", 3);   // negative ('c' < 'd')\n" +
                        "int z = memcmp(\"abc\", \"abc\", 3);   // 0 (equal)"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── C string functions ────────────────────────────────────────────
            item {
                SectionCard(title = "C String Functions — <string.h> / <cstring>") {
                    BodyText("C strings are null-terminated byte arrays. These functions treat '\\0' as the end of the string.")
                    BodyText("strlen(s) — returns the number of characters before the first '\\0'. Does NOT count the null terminator. \"hello\" → 5.")
                    BodyText("strcpy(dst, src) / strncpy(dst, src, n) — copy src into dst. strcpy is unsafe if dst is too small. strncpy copies at most n characters; if src is shorter, it pads dst with '\\0' up to n bytes; if src is longer, dst may NOT be null-terminated — add dst[n-1]='\\0' manually.")
                    BodyText("strcat(dst, src) / strncat(dst, src, n) — appends src to the end of dst. dst must have enough space for both strings plus '\\0'. strncat appends at most n characters from src and always null-terminates dst.")
                    BodyText("strcmp(s1, s2) / strncmp(s1, s2, n) — compare strings lexicographically (by ASCII value). Returns 0 if equal, negative if s1 < s2, positive if s1 > s2.")
                    BodyText("strstr(haystack, needle) — returns a pointer to the first occurrence of the substring needle inside haystack, or NULL if not found.")
                    BodyText("strchr(s, c) / strrchr(s, c) — find first / last occurrence of character c in s. Return a pointer into the string, or NULL.")
                    CodeBlock(
                        "#include <string.h>\n" +
                        "\n" +
                        "size_t n = strlen(\"hello\");           // 5 (no '\\0')\n" +
                        "\n" +
                        "char buf[32] = \"Hello\";\n" +
                        "strncat(buf, \", world\", sizeof(buf) - strlen(buf) - 1);\n" +
                        "// buf == \"Hello, world\"\n" +
                        "\n" +
                        "int cmp = strcmp(\"apple\", \"banana\");  // negative\n" +
                        "\n" +
                        "const char* found = strstr(\"Hello, world\", \"world\"); // points to 'w'\n" +
                        "if (found) printf(\"%s\\n\", found);    // \"world\"\n" +
                        "\n" +
                        "const char* p = strchr(\"Hello\", 'l'); // points to first 'l'\n" +
                        "const char* q = strrchr(\"Hello\", 'l'); // points to second 'l'"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── tolower / toupper in C ────────────────────────────────────────
            item {
                SectionCard(title = "C Character Classification — <ctype.h> / <cctype>") {
                    BodyText("These functions operate on a single character, passed as int (the value must be in range [0, 255] or EOF). Passing a plain char can cause UB if char is signed and the value is negative — cast to unsigned char first.")
                    CodeBlock(
                        "#include <ctype.h>\n" +
                        "\n" +
                        "int tolower('A');   // 'a'\n" +
                        "int toupper('a');   // 'A'\n" +
                        "int tolower('5');   // '5' (unchanged — not a letter)\n" +
                        "\n" +
                        "// Classification predicates\n" +
                        "isalpha('A')   // true — letter\n" +
                        "isdigit('7')   // true — decimal digit\n" +
                        "isalnum('A')   // true — letter or digit\n" +
                        "isspace(' ')   // true — whitespace (space, tab, newline, …)\n" +
                        "isupper('A')   // true\n" +
                        "islower('a')   // true\n" +
                        "isprint('@')   // true — printable character\n" +
                        "\n" +
                        "// Safe way to convert a string in-place (C)\n" +
                        "char s[] = \"Hello World\";\n" +
                        "for (int i = 0; s[i]; i++)\n" +
                        "    s[i] = (char)tolower((unsigned char)s[i]);"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── std::string ───────────────────────────────────────────────────
            item {
                SectionCard(title = "std::string — Size and the Null Terminator") {
                    BodyText("std::string (in <string>) manages a heap-allocated character sequence. It always maintains an internal null terminator, but size() and length() do NOT count it — they return only the number of visible characters.")
                    BodyText("size() and length() are identical; both return the character count excluding '\\0'. empty() returns true when size() == 0.")
                    CodeBlock(
                        "#include <string>\n" +
                        "\n" +
                        "std::string s = \"hello\";\n" +
                        "s.size();     // 5  — does NOT include '\\0'\n" +
                        "s.length();   // 5  — same as size()\n" +
                        "s.empty();    // false\n" +
                        "\n" +
                        "// The internal buffer does have a '\\0' at position 5:\n" +
                        "s[5];          // '\\0' (valid but not included in size())\n" +
                        "s.c_str()[5];  // '\\0' (guaranteed)\n" +
                        "\n" +
                        "std::string empty_str;\n" +
                        "empty_str.size();   // 0\n" +
                        "empty_str.empty();  // true"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "std::string — Substring, find, rfind, npos") {
                    BodyText("substr(pos, len) — returns a new string that is a copy of len characters starting at position pos. If len is omitted (or std::string::npos), the rest of the string from pos is returned.")
                    BodyText("find(str, pos=0) — searches forward from position pos and returns the index of the first occurrence of str (or char). Returns std::string::npos if not found.")
                    BodyText("rfind(str, pos=npos) — searches backward from position pos and returns the index of the last occurrence. Returns std::string::npos if not found.")
                    BodyText("std::string::npos is a static constant of value std::string::size_type(-1) — effectively the largest possible size_t value. It is used both as the 'not found' sentinel and as a parameter meaning 'to the end'.")
                    CodeBlock(
                        "std::string s = \"Hello, world!\";\n" +
                        "\n" +
                        "// substr\n" +
                        "s.substr(7, 5);   // \"world\"  (pos=7, len=5)\n" +
                        "s.substr(7);      // \"world!\" (to end)\n" +
                        "\n" +
                        "// find — forward search\n" +
                        "size_t pos = s.find(\"world\");  // 7\n" +
                        "size_t pos2 = s.find('o');     // 4  (first 'o')\n" +
                        "size_t pos3 = s.find(\"xyz\");   // std::string::npos\n" +
                        "\n" +
                        "if (pos3 == std::string::npos) {\n" +
                        "    // not found\n" +
                        "}\n" +
                        "\n" +
                        "// rfind — backward search\n" +
                        "size_t last_o = s.rfind('o');  // 8  (the 'o' in 'world')\n" +
                        "\n" +
                        "// Common pattern: extract extension\n" +
                        "std::string filename = \"photo.tar.gz\";\n" +
                        "size_t dot = filename.rfind('.');\n" +
                        "if (dot != std::string::npos)\n" +
                        "    std::string ext = filename.substr(dot); // \".gz\""
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "std::string — Operators and c_str()") {
                    BodyText("operator+ — concatenates two strings and returns a new string. Does not modify the originals.")
                    BodyText("operator+= — appends to the string in place (more efficient than + for building strings).")
                    BodyText("operator[] — accesses a character by index with no bounds checking. Out-of-range access is undefined behaviour. Use at(i) for checked access (throws std::out_of_range).")
                    BodyText("c_str() — returns a const char* pointing to the internal null-terminated buffer. Valid only as long as the string is not modified. Use this to pass a std::string to a C API expecting const char*.")
                    CodeBlock(
                        "std::string a = \"Hello\";\n" +
                        "std::string b = \", world\";\n" +
                        "\n" +
                        "// operator+\n" +
                        "std::string c = a + b;          // \"Hello, world\" (new string)\n" +
                        "std::string d = a + \"!!\";       // \"Hello!!\"\n" +
                        "\n" +
                        "// operator+= (preferred for building)\n" +
                        "a += b;                          // a == \"Hello, world\"\n" +
                        "a += '!';                        // a == \"Hello, world!\"\n" +
                        "\n" +
                        "// operator[]\n" +
                        "char ch = a[0];   // 'H'  (no bounds check)\n" +
                        "a[0] = 'h';       // \"hello, world!\"\n" +
                        "\n" +
                        "// at() — throws if out of range\n" +
                        "char safe = a.at(0);             // 'h'\n" +
                        "// a.at(999);                    // throws std::out_of_range\n" +
                        "\n" +
                        "// c_str() — pass to C API\n" +
                        "std::string path = \"/tmp/file.txt\";\n" +
                        "FILE* f = fopen(path.c_str(), \"r\");  // safe\n" +
                        "// path may not be modified while f is open and c_str() is used"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "std::tolower / std::toupper and std::transform") {
                    BodyText("std::tolower and std::toupper in <cctype> are the same functions as the C versions — they work on a single int (unsigned char value). There is also a locale-aware overload in <locale> that takes a std::locale parameter.")
                    BodyText("To convert an entire std::string to lowercase or uppercase, use std::transform from <algorithm> with a lambda. Using the raw function pointer directly is problematic because of the overloaded locale version — a lambda avoids the ambiguity.")
                    CodeBlock(
                        "#include <algorithm>\n" +
                        "#include <cctype>\n" +
                        "#include <string>\n" +
                        "\n" +
                        "std::string s = \"Hello, World!\";\n" +
                        "\n" +
                        "// Convert to lowercase in-place\n" +
                        "std::transform(s.begin(), s.end(), s.begin(),\n" +
                        "    [](unsigned char c) { return std::tolower(c); });\n" +
                        "// s == \"hello, world!\"\n" +
                        "\n" +
                        "// Convert to uppercase into a new string\n" +
                        "std::string upper;\n" +
                        "upper.resize(s.size());\n" +
                        "std::transform(s.begin(), s.end(), upper.begin(),\n" +
                        "    [](unsigned char c) { return std::toupper(c); });\n" +
                        "// upper == \"HELLO, WORLD!\"\n" +
                        "\n" +
                        "// Why unsigned char? — plain char may be signed;\n" +
                        "// passing a negative value to tolower/toupper is UB.\n" +
                        "// Casting to unsigned char first is the correct approach."
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard(title = "std::wstring") {
                    BodyText("std::wstring is the wide-character counterpart to std::string. It stores wchar_t values instead of char. The width of wchar_t is platform-defined: 2 bytes on Windows (UTF-16), 4 bytes on Linux/macOS (UTF-32).")
                    BodyText("All the same methods exist: size(), length(), substr(), find(), rfind(), npos, operator+, operator[], operator+=, at(). The wide equivalent of c_str() returns const wchar_t*.")
                    BodyText("Wide string literals use the L prefix. Wide character functions are prefixed with w: wcslen, wcscpy, wcscat, wcscmp, wcsstr, etc. (in <wchar.h> / <cwchar>).")
                    CodeBlock(
                        "#include <string>\n" +
                        "#include <cwchar>\n" +
                        "\n" +
                        "std::wstring ws = L\"Héllo\";\n" +
                        "ws.size();     // 5 — number of wchar_t units, NOT bytes\n" +
                        "ws.length();   // 5\n" +
                        "\n" +
                        "ws.find(L'é');              // 1\n" +
                        "ws.substr(1, 3);             // L\"éll\"\n" +
                        "\n" +
                        "// c_str() returns const wchar_t*\n" +
                        "const wchar_t* raw = ws.c_str();\n" +
                        "wcslen(raw);               // 5\n" +
                        "\n" +
                        "// Windows API uses wstring extensively\n" +
                        "// CreateFileW(ws.c_str(), ...);\n" +
                        "\n" +
                        "// Convert between string and wstring requires a codec\n" +
                        "// (MultiByteToWideChar on Windows, or std::wstring_convert — deprecated C++17)"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
