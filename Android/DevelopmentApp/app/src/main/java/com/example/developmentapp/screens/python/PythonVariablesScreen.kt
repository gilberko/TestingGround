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
fun PythonVariablesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Python — Variables", color = Color(0xFF00FF41), fontFamily = FontFamily.Monospace, fontSize = 16.sp)
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
                SectionCard("Variables — No Declaration Needed") {
                    BodyText(
                        "In Python, a variable is created the moment you assign to it. There is no declaration keyword like 'int' or 'var'. " +
                        "The type is attached to the object, not the variable name — the name is just a label that points to an object."
                    )
                    CodeBlock(
                        "x = 42           # x points to an int object\n" +
                        "x = 'hello'      # x now points to a str object (legal!)\n" +
                        "x = [1, 2, 3]   # x now points to a list object\n\n" +
                        "# Multiple assignment:\n" +
                        "a = b = c = 0\n\n" +
                        "# Tuple unpacking:\n" +
                        "x, y, z = 1, 2, 3\n" +
                        "first, *rest = [1, 2, 3, 4]  # first=1, rest=[2,3,4]"
                    )
                }
            }

            item {
                SectionCard("Numeric Types") {
                    CodeBlock(
                        "# int — signed, arbitrary precision (no overflow in Python)\n" +
                        "i = 42\n" +
                        "big = 10 ** 100     # googol — works fine\n\n" +
                        "# float — 64-bit IEEE 754 double (approx 15–17 significant digits)\n" +
                        "f = 3.14\n" +
                        "f2 = 1.5e-10\n\n" +
                        "# complex — pair of floats\n" +
                        "z = 2 + 3j\n" +
                        "z.real, z.imag      # 2.0, 3.0\n\n" +
                        "# No unsigned integer in core Python!\n" +
                        "# Use ctypes for C-compatible fixed-width types:\n" +
                        "import ctypes\n" +
                        "u = ctypes.c_uint32(4294967295)  # 32-bit unsigned\n\n" +
                        "# Or numpy for array-oriented fixed-width numerics:\n" +
                        "import numpy as np\n" +
                        "arr = np.array([1, 2, 3], dtype=np.uint8)"
                    )
                }
            }

            item {
                SectionCard("Strings and Characters") {
                    BodyText("Python has no separate character type. A 'character' is just a string of length 1.")
                    CodeBlock(
                        "s = 'hello'\n" +
                        "c = s[0]            # 'h' — a str of length 1\n\n" +
                        "# Strings are immutable — you cannot change a character:\n" +
                        "# s[0] = 'H'       # TypeError!\n" +
                        "s2 = 'H' + s[1:]   # must create a new string\n\n" +
                        "# Useful string methods:\n" +
                        "s.upper()           # 'HELLO'\n" +
                        "s.split(',')        # list of parts\n" +
                        "','.join(['a','b']) # 'a,b'\n" +
                        "s.strip()           # remove whitespace\n" +
                        "len(s)              # 5"
                    )
                }
            }

            item {
                SectionCard("List, Tuple, and Array") {
                    CodeBlock(
                        "# list — ordered, mutable, heterogeneous, O(1) append\n" +
                        "lst = [1, 2, 3]\n" +
                        "lst.append(4)       # [1, 2, 3, 4]\n" +
                        "lst.insert(0, 0)    # [0, 1, 2, 3, 4]\n" +
                        "lst.pop()           # removes and returns last: 4\n" +
                        "lst[1:3]            # slice: [1, 2]\n\n" +
                        "# tuple — ordered, IMMUTABLE, slightly faster than list\n" +
                        "tup = (1, 2, 3)\n" +
                        "# tup[0] = 9       # TypeError — cannot mutate\n\n" +
                        "# For C-style fixed-type arrays use array module:\n" +
                        "import array\n" +
                        "a = array.array('i', [1, 2, 3])  # 'i' = signed int\n\n" +
                        "# Or numpy for numerical arrays:\n" +
                        "import numpy as np\n" +
                        "v = np.zeros(100, dtype=np.float64)"
                    )
                }
            }

            item {
                SectionCard("Dict (Hash Map) and Set") {
                    CodeBlock(
                        "# dict — hash map, key → value\n" +
                        "# Keys must be hashable (immutable): str, int, tuple, etc.\n" +
                        "d = {'a': 1, 'b': 2}\n" +
                        "d['c'] = 3              # insert / update\n" +
                        "d.get('x', 0)           # safe get with default\n" +
                        "del d['a']              # remove key\n" +
                        "'b' in d                # True — O(1) lookup\n" +
                        "d.keys(), d.values(), d.items()\n\n" +
                        "# set — unordered, unique, backed by hash table\n" +
                        "s = {1, 2, 3}\n" +
                        "s.add(4)\n" +
                        "s.discard(99)           # remove if present, no error\n" +
                        "s & {2, 3, 5}           # intersection: {2, 3}\n" +
                        "s | {4, 5}              # union\n" +
                        "s - {1}                 # difference"
                    )
                }
            }

            item {
                SectionCard("Sorted Containers") {
                    BodyText("Python's built-in dict is hash-based (unordered by hash, insertion-ordered since 3.7). For key-sorted access use external libraries or built-in alternatives.")
                    CodeBlock(
                        "# bisect — binary search on a sorted list:\n" +
                        "import bisect\n" +
                        "sl = [1, 3, 5, 7]\n" +
                        "bisect.insort(sl, 4)    # [1, 3, 4, 5, 7]\n\n" +
                        "# heapq — min-heap on a plain list:\n" +
                        "import heapq\n" +
                        "h = []\n" +
                        "heapq.heappush(h, 3)\n" +
                        "heapq.heappush(h, 1)\n" +
                        "heapq.heappop(h)        # 1 (smallest)\n\n" +
                        "# sortedcontainers library (pip install):\n" +
                        "from sortedcontainers import SortedList, SortedDict\n" +
                        "sl2 = SortedList([3, 1, 4, 1, 5])\n" +
                        "sl2.add(2)              # stays sorted\n" +
                        "sd = SortedDict({'b': 2, 'a': 1})\n" +
                        "# sd.keys() iterates in alphabetical order"
                    )
                }
            }

            item {
                SectionCard("Scope — Local, Global, and Nonlocal") {
                    BodyText(
                        "Python uses LEGB scope resolution: Local → Enclosing → Global → Built-in. " +
                        "A name is looked up in each scope from innermost to outermost."
                    )
                    CodeBlock(
                        "x = 'global'\n\n" +
                        "def outer():\n" +
                        "    x = 'enclosing'\n\n" +
                        "    def inner():\n" +
                        "        x = 'local'    # creates a new local variable\n" +
                        "        print(x)       # 'local'\n" +
                        "    inner()\n" +
                        "    print(x)           # 'enclosing'\n\n" +
                        "outer()\n" +
                        "print(x)               # 'global'\n\n" +
                        "# To modify the global 'x' from inside a function:\n" +
                        "def modify_global():\n" +
                        "    global x\n" +
                        "    x = 'changed'      # modifies the module-level x\n\n" +
                        "# To modify an enclosing (non-global) variable:\n" +
                        "def outer2():\n" +
                        "    count = 0\n" +
                        "    def inner2():\n" +
                        "        nonlocal count\n" +
                        "        count += 1\n" +
                        "    inner2()\n" +
                        "    print(count)       # 1"
                    )
                    BodyText("Convention: module-level 'constants' are UPPER_CASE. Local variables are snake_case. Avoid global variables — they make code harder to reason about.")
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
