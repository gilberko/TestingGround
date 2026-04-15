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
fun PythonFunctionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Python — Functions", color = Color(0xFF00FF41), fontFamily = FontFamily.Monospace, fontSize = 16.sp)
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
                SectionCard("Defining a Function") {
                    CodeBlock(
                        "def greet(name):          # 'def' keyword + name + params\n" +
                        "    \"\"\"Docstring — describes the function.\"\"\"\n" +
                        "    return f'Hello, {name}!'\n\n" +
                        "result = greet('Alice')    # call — result = 'Hello, Alice!'\n\n" +
                        "# Function with no return value returns None implicitly:\n" +
                        "def log(msg):\n" +
                        "    print(msg)             # no return → returns None"
                    )
                }
            }

            item {
                SectionCard("Parameter Types") {
                    CodeBlock(
                        "# Positional parameters:\n" +
                        "def add(a, b):\n" +
                        "    return a + b\n" +
                        "add(3, 4)                  # 7\n\n" +
                        "# Default parameter values:\n" +
                        "def greet(name, greeting='Hello'):\n" +
                        "    return f'{greeting}, {name}!'\n" +
                        "greet('Bob')               # 'Hello, Bob!'\n" +
                        "greet('Bob', 'Hi')         # 'Hi, Bob!'\n\n" +
                        "# Keyword arguments (can pass in any order):\n" +
                        "greet(greeting='Hey', name='Carol')\n\n" +
                        "# Positional-only (/) and keyword-only (*) markers:\n" +
                        "def f(pos_only, /, normal, *, kw_only):\n" +
                        "    pass\n" +
                        "# pos_only must be positional, kw_only must be keyword"
                    )
                }
            }

            item {
                SectionCard("*args and **kwargs — Variable Arguments") {
                    CodeBlock(
                        "# *args — collects extra positional args into a tuple:\n" +
                        "def total(*args):\n" +
                        "    return sum(args)\n" +
                        "total(1, 2, 3, 4)          # 10\n\n" +
                        "# **kwargs — collects extra keyword args into a dict:\n" +
                        "def show(**kwargs):\n" +
                        "    for k, v in kwargs.items():\n" +
                        "        print(f'{k} = {v}')\n" +
                        "show(name='Alice', age=30)\n\n" +
                        "# Combined:\n" +
                        "def everything(a, b, *args, key='default', **kwargs):\n" +
                        "    print(a, b, args, key, kwargs)\n\n" +
                        "# Unpacking when calling:\n" +
                        "nums = [1, 2, 3]\n" +
                        "total(*nums)               # same as total(1, 2, 3)\n" +
                        "opts = {'name': 'X', 'age': 5}\n" +
                        "show(**opts)               # same as show(name='X', age=5)"
                    )
                }
            }

            item {
                SectionCard("Pass by Value or Reference?") {
                    BodyText(
                        "Python uses 'pass by object reference' (sometimes called 'pass by assignment'). " +
                        "The function receives a reference to the same object — NOT a copy. " +
                        "Whether this looks like pass-by-value or pass-by-reference depends on whether the object is mutable."
                    )
                    CodeBlock(
                        "# Immutable objects (int, str, tuple, float) — behave like pass-by-value:\n" +
                        "def double(n):\n" +
                        "    n *= 2         # rebinds local name 'n' — caller unchanged\n" +
                        "    return n\n" +
                        "x = 5\n" +
                        "double(x)          # returns 10\n" +
                        "print(x)           # still 5\n\n" +
                        "# Mutable objects (list, dict, set) — behave like pass-by-reference:\n" +
                        "def append_one(lst):\n" +
                        "    lst.append(1)  # mutates the object — caller sees the change\n" +
                        "items = []\n" +
                        "append_one(items)\n" +
                        "print(items)       # [1]  ← caller's list was modified\n\n" +
                        "# To avoid mutating the caller's list, pass a copy:\n" +
                        "append_one(items[:])    # slice copy\n" +
                        "append_one(list(items)) # explicit copy"
                    )
                }
            }

            item {
                SectionCard("Return Values") {
                    CodeBlock(
                        "# Single return value:\n" +
                        "def square(n):\n" +
                        "    return n * n\n\n" +
                        "# Multiple return values (actually returns a tuple):\n" +
                        "def divmod_manual(a, b):\n" +
                        "    return a // b, a % b    # returns (quotient, remainder)\n\n" +
                        "q, r = divmod_manual(17, 5) # tuple unpacking\n" +
                        "print(q, r)                 # 3 2\n\n" +
                        "# Early return:\n" +
                        "def find(lst, target):\n" +
                        "    for i, v in enumerate(lst):\n" +
                        "        if v == target:\n" +
                        "            return i       # exits immediately\n" +
                        "    return -1              # not found"
                    )
                }
            }

            item {
                SectionCard("Lambda — Anonymous Functions") {
                    BodyText("A lambda is a small anonymous function defined in a single expression. It can take any number of parameters but only one expression.")
                    CodeBlock(
                        "square = lambda x: x * x\n" +
                        "square(5)                   # 25\n\n" +
                        "add = lambda a, b: a + b\n" +
                        "add(3, 4)                   # 7\n\n" +
                        "# Common use — as sort key:\n" +
                        "people = [('Alice', 30), ('Bob', 25), ('Carol', 35)]\n" +
                        "people.sort(key=lambda p: p[1])  # sort by age\n\n" +
                        "# In filter/map:\n" +
                        "evens = list(filter(lambda x: x % 2 == 0, range(10)))\n" +
                        "squares = list(map(lambda x: x**2, range(5)))"
                    )
                }
            }

            item {
                SectionCard("Type Hints") {
                    BodyText("Type hints (Python 3.5+) document expected types. They are not enforced at runtime — use mypy or pyright for static checking.")
                    CodeBlock(
                        "def add(a: int, b: int) -> int:\n" +
                        "    return a + b\n\n" +
                        "def greet(name: str) -> str:\n" +
                        "    return f'Hello, {name}'\n\n" +
                        "from typing import Optional, List, Dict, Tuple\n\n" +
                        "def find(items: List[int], target: int) -> Optional[int]:\n" +
                        "    \"\"\"Returns index or None.\"\"\"\n" +
                        "    try:\n" +
                        "        return items.index(target)\n" +
                        "    except ValueError:\n" +
                        "        return None\n\n" +
                        "# Python 3.10+ union syntax:\n" +
                        "def f(x: int | str) -> int | None:\n" +
                        "    pass"
                    )
                }
            }

            item {
                SectionCard("Nested Functions and Closures") {
                    BodyText("Functions can be defined inside other functions. The inner function captures variables from the enclosing scope (a closure).")
                    CodeBlock(
                        "def make_adder(n):\n" +
                        "    def adder(x):       # inner function\n" +
                        "        return x + n    # captures 'n' from outer scope\n" +
                        "    return adder        # returns the function itself\n\n" +
                        "add5 = make_adder(5)\n" +
                        "add5(3)                 # 8\n" +
                        "add5(10)                # 15\n\n" +
                        "# Modifying an outer variable from inner — use 'nonlocal':\n" +
                        "def counter():\n" +
                        "    count = 0\n" +
                        "    def increment():\n" +
                        "        nonlocal count\n" +
                        "        count += 1\n" +
                        "        return count\n" +
                        "    return increment"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
