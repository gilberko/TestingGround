package com.example.developmentapp.screens.python

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
fun PythonSyntaxScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Python — Syntax", color = Color(0xFF00FF41), fontFamily = FontFamily.Monospace, fontSize = 16.sp)
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        LazyColumn(
            modifier       = Modifier.fillMaxSize().padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            item {
                SectionCard("General Syntax") {
                    BodyText(
                        "Python uses indentation (spaces or tabs — never mixed) to delimit code blocks. " +
                        "There are no curly braces, semicolons are optional (and rarely used), and there is no main() required."
                    )
                    CodeBlock(
                        "# This is a comment\n\n" +
                        "x = 10           # assignment — no type declaration needed\n" +
                        "y = x + 5        # expression\n" +
                        "print(y)         # function call — no semicolon needed\n\n" +
                        "# Multiple statements on one line (avoid in real code):\n" +
                        "a = 1; b = 2; c = 3"
                    )
                    BodyText("Python is dynamically typed — the type of a variable is determined at runtime and can change. There is no need to declare a variable before using it.")
                }
            }

            item {
                SectionCard("Indentation and Code Blocks") {
                    BodyText(
                        "Indentation is the syntax. Every block that follows a colon (:) must be indented consistently. " +
                        "The standard is 4 spaces per level."
                    )
                    CodeBlock(
                        "if x > 0:          # colon starts a block\n" +
                        "    print('pos')   # 4 spaces — inside the if\n" +
                        "    y = x * 2      # still inside\n" +
                        "else:\n" +
                        "    print('neg')   # inside the else\n" +
                        "print('done')      # back at top level\n\n" +
                        "for i in range(3):\n" +
                        "    for j in range(3):   # nested — 8 spaces\n" +
                        "        print(i, j)\n\n" +
                        "def my_func():\n" +
                        "    # function body is indented\n" +
                        "    return 42\n\n" +
                        "class MyClass:\n" +
                        "    def method(self):    # method body indented twice\n" +
                        "        pass             # 'pass' is a no-op placeholder"
                    )
                }
            }

            item {
                SectionCard("Strings") {
                    BodyText("Strings are immutable sequences of Unicode characters. Several literal forms exist:")
                    CodeBlock(
                        "s1 = 'hello'                  # single quotes\n" +
                        "s2 = \"world\"                  # double quotes (identical)\n" +
                        "s3 = '''multi\nline'''         # triple single-quotes\n" +
                        "s4 = \"\"\"also\nmultiline\"\"\"    # triple double-quotes\n\n" +
                        "# f-string (formatted string literal — Python 3.6+):\n" +
                        "name = 'Alice'\n" +
                        "age  = 30\n" +
                        "msg  = f'Name: {name}, Age: {age}'  # 'Name: Alice, Age: 30'\n" +
                        "expr = f'2 + 2 = {2 + 2}'           # 'expression: 4'\n\n" +
                        "# Raw string (backslashes not escaped):\n" +
                        "path = r'C:\\Users\\Alice'   # no need to double-backslash\n\n" +
                        "# Byte string:\n" +
                        "b = b'\\x00\\xFF'"
                    )
                }
            }

            item {
                SectionCard("Numbers") {
                    BodyText("Python has three built-in numeric types:")
                    CodeBlock(
                        "# int — arbitrary precision signed integer (no overflow!)\n" +
                        "a = 42\n" +
                        "b = -100\n" +
                        "c = 10 ** 100        # googol — works fine\n" +
                        "d = 0xFF             # hex literal (255)\n" +
                        "e = 0b1010           # binary literal (10)\n" +
                        "f = 0o17             # octal literal (15)\n\n" +
                        "# float — 64-bit IEEE 754 double precision\n" +
                        "pi = 3.14159\n" +
                        "sci = 1.5e10         # 1.5 × 10^10\n\n" +
                        "# complex\n" +
                        "z = 3 + 4j           # real=3, imaginary=4\n\n" +
                        "# Python has NO unsigned integer type.\n" +
                        "# int is always signed and grows arbitrarily large.\n" +
                        "# For fixed-width: use ctypes.c_uint32, numpy.uint32, etc."
                    )
                }
            }

            item {
                SectionCard("Boolean") {
                    BodyText("bool is a subclass of int. True == 1 and False == 0 in arithmetic contexts.")
                    CodeBlock(
                        "t = True\n" +
                        "f = False\n" +
                        "print(True + True)   # 2 (bool is int!)\n\n" +
                        "# Truthiness — these are all falsy:\n" +
                        "# False, 0, 0.0, '', [], {}, set(), None\n" +
                        "# Everything else is truthy.\n\n" +
                        "if []:               # empty list → falsy\n" +
                        "    print('never')\n\n" +
                        "# Boolean operators:\n" +
                        "x = True and False   # False\n" +
                        "y = True or  False   # True\n" +
                        "z = not True         # False"
                    )
                }
            }

            item {
                SectionCard("Collections — List, Tuple, Dict, Set") {
                    CodeBlock(
                        "# List — ordered, mutable, allows duplicates\n" +
                        "lst = [1, 'hello', 3.14, True]\n" +
                        "lst.append(42)           # add to end\n" +
                        "lst[0] = 99              # update by index\n\n" +
                        "# Tuple — ordered, IMMUTABLE, allows duplicates\n" +
                        "tup = (1, 2, 3)\n" +
                        "tup2 = 1, 2, 3           # parentheses optional\n\n" +
                        "# Dict — hash map, key→value, ordered (Python 3.7+)\n" +
                        "d = {'name': 'Alice', 'age': 30}\n" +
                        "d['city'] = 'NY'         # add/update key\n" +
                        "val = d.get('age', 0)    # safe lookup with default\n\n" +
                        "# Set — unordered, unique elements\n" +
                        "s = {1, 2, 3, 2, 1}      # {1, 2, 3}\n" +
                        "s.add(4)\n" +
                        "s2 = set()               # empty set (NOT {} — that's a dict!)\n\n" +
                        "# frozenset — immutable set\n" +
                        "fs = frozenset([1, 2, 3])"
                    )
                }
            }

            item {
                SectionCard("None, Bytes, and Other Types") {
                    CodeBlock(
                        "# None — Python's null / absence of value\n" +
                        "x = None\n" +
                        "if x is None:        # use 'is', not '=='\n" +
                        "    print('nothing')\n\n" +
                        "# bytes — immutable byte sequence\n" +
                        "b = b'hello'         # b'hello'\n" +
                        "b2 = bytes([72, 101, 108, 108, 111])\n\n" +
                        "# bytearray — mutable byte sequence\n" +
                        "ba = bytearray(b'hello')\n" +
                        "ba[0] = 72\n\n" +
                        "# Type checking:\n" +
                        "type(42)             # <class 'int'>\n" +
                        "isinstance(42, int)  # True\n" +
                        "isinstance(42, (int, float))  # True"
                    )
                }
            }

            item {
                SectionCard("Common Expressions and Operators") {
                    CodeBlock(
                        "# Arithmetic:\n" +
                        "10 / 3       # 3.333... (true division — always float)\n" +
                        "10 // 3      # 3         (floor division)\n" +
                        "10 % 3       # 1         (modulo)\n" +
                        "2 ** 8       # 256        (exponentiation)\n\n" +
                        "# Comparison (return bool):\n" +
                        "x == y;  x != y;  x < y;  x <= y;  x > y;  x >= y\n\n" +
                        "# Chained comparisons (unique to Python):\n" +
                        "0 < x < 10   # True if 0 < x AND x < 10\n\n" +
                        "# Identity and membership:\n" +
                        "x is None;   x is not None\n" +
                        "'key' in d;  5 not in lst\n\n" +
                        "# Ternary expression:\n" +
                        "result = 'yes' if condition else 'no'\n\n" +
                        "# Walrus operator := (Python 3.8+) — assign inside expression:\n" +
                        "while chunk := f.read(8192):\n" +
                        "    process(chunk)"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
