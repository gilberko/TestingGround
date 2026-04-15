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
fun PythonLoopsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Python — Loops", color = Color(0xFF00FF41), fontFamily = FontFamily.Monospace, fontSize = 16.sp)
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
                SectionCard("while Loop") {
                    BodyText(
                        "The while loop repeats as long as its condition is True. " +
                        "WARNING: If the condition never becomes False and there is no break, the loop runs forever — " +
                        "an infinite loop that will hang your program."
                    )
                    CodeBlock(
                        "count = 0\n" +
                        "while count < 5:\n" +
                        "    print(count)    # 0 1 2 3 4\n" +
                        "    count += 1      # must update condition or loop is infinite!\n\n" +
                        "# Reading until a sentinel value:\n" +
                        "line = input('> ')\n" +
                        "while line != 'quit':\n" +
                        "    print('you said:', line)\n" +
                        "    line = input('> ')\n\n" +
                        "# DANGER — infinite loop (no exit condition):\n" +
                        "# while True:\n" +
                        "#     print('forever')  # never stops!"
                    )
                }
            }

            item {
                SectionCard("for Loop") {
                    BodyText(
                        "The for loop iterates over any iterable object — a range, list, string, dict, set, etc. " +
                        "Python's for is closer to C's foreach than its for(;;) — there is no init/increment/test syntax."
                    )
                    CodeBlock(
                        "# Iterating over a range:\n" +
                        "for i in range(5):         # 0, 1, 2, 3, 4\n" +
                        "    print(i)\n\n" +
                        "for i in range(2, 8):      # 2, 3, 4, 5, 6, 7\n" +
                        "    print(i)\n\n" +
                        "for i in range(0, 10, 2):  # 0, 2, 4, 6, 8 (step=2)\n" +
                        "    print(i)\n\n" +
                        "for i in range(10, 0, -1): # 10, 9, 8 ... 1 (count down)\n" +
                        "    print(i)\n\n" +
                        "# Iterating over a list:\n" +
                        "fruits = ['apple', 'banana', 'cherry']\n" +
                        "for fruit in fruits:\n" +
                        "    print(fruit)\n\n" +
                        "# Iterating over a string (character by character):\n" +
                        "for ch in 'hello':\n" +
                        "    print(ch)   # h e l l o"
                    )
                }
            }

            item {
                SectionCard("Iterating Containers") {
                    BodyText(
                        "Any Python container (list, tuple, dict, set) is iterable. " +
                        "Use enumerate() to get both the index and the value. " +
                        "Use .items() on a dict to get key-value pairs."
                    )
                    CodeBlock(
                        "# List — value only:\n" +
                        "items = [10, 20, 30]\n" +
                        "for item in items:\n" +
                        "    print(item)\n\n" +
                        "# List with index using enumerate():\n" +
                        "for i, item in enumerate(items):\n" +
                        "    print(i, item)   # 0 10 / 1 20 / 2 30\n\n" +
                        "# Dict — keys by default:\n" +
                        "d = {'a': 1, 'b': 2, 'c': 3}\n" +
                        "for key in d:\n" +
                        "    print(key, d[key])\n\n" +
                        "# Dict — key-value pairs with .items():\n" +
                        "for key, value in d.items():\n" +
                        "    print(key, '->', value)\n\n" +
                        "# Set — unordered, no index:\n" +
                        "s = {1, 2, 3, 4}\n" +
                        "for x in s:\n" +
                        "    print(x)   # order not guaranteed\n\n" +
                        "# Tuple unpacking inside a loop:\n" +
                        "pairs = [(1, 'one'), (2, 'two'), (3, 'three')]\n" +
                        "for num, name in pairs:\n" +
                        "    print(num, name)"
                    )
                }
            }

            item {
                SectionCard("break and continue") {
                    BodyText(
                        "Python's break and continue behave exactly like C's — break exits the innermost loop immediately, " +
                        "continue skips the rest of the current iteration and moves to the next. " +
                        "In nested loops, they only affect the innermost loop."
                    )
                    CodeBlock(
                        "# break — exit the loop early:\n" +
                        "for i in range(10):\n" +
                        "    if i == 5:\n" +
                        "        break       # stop at 5\n" +
                        "    print(i)        # prints 0 1 2 3 4\n\n" +
                        "# continue — skip current iteration:\n" +
                        "for i in range(10):\n" +
                        "    if i % 2 == 0:\n" +
                        "        continue    # skip even numbers\n" +
                        "    print(i)        # prints 1 3 5 7 9\n\n" +
                        "# Nested loops — break/continue only affect innermost:\n" +
                        "for i in range(3):\n" +
                        "    for j in range(3):\n" +
                        "        if j == 1:\n" +
                        "            break   # exits inner loop only\n" +
                        "    print(i)        # outer loop still runs: 0 1 2\n\n" +
                        "# break in while:\n" +
                        "while True:\n" +
                        "    cmd = input('> ')\n" +
                        "    if cmd == 'quit':\n" +
                        "        break       # safe exit from an otherwise infinite loop"
                    )
                }
            }

            item {
                SectionCard("else on Loops") {
                    BodyText(
                        "Python loops can have an 'else' clause — it runs only if the loop completed normally " +
                        "(i.e. was NOT terminated by break). This is useful for search patterns."
                    )
                    CodeBlock(
                        "# else runs because no break was hit:\n" +
                        "for i in range(5):\n" +
                        "    print(i)\n" +
                        "else:\n" +
                        "    print('loop finished normally')   # prints\n\n" +
                        "# else does NOT run when break is hit:\n" +
                        "for i in range(5):\n" +
                        "    if i == 3:\n" +
                        "        break\n" +
                        "else:\n" +
                        "    print('not printed — loop was broken')\n\n" +
                        "# Classic use — search with for/else:\n" +
                        "target = 7\n" +
                        "numbers = [1, 3, 5, 9, 11]\n" +
                        "for n in numbers:\n" +
                        "    if n == target:\n" +
                        "        print('found!')\n" +
                        "        break\n" +
                        "else:\n" +
                        "    print('not found')   # prints because 7 is not in list"
                    )
                }
            }

            item {
                SectionCard("List Comprehensions") {
                    BodyText(
                        "A list comprehension is a compact way to build a list using a loop (and optionally a condition) in a single expression. " +
                        "It is faster than an equivalent for loop with append()."
                    )
                    CodeBlock(
                        "# Basic form: [expression for item in iterable]\n" +
                        "squares = [x * x for x in range(6)]\n" +
                        "# [0, 1, 4, 9, 16, 25]\n\n" +
                        "# With a filter condition:\n" +
                        "evens = [x for x in range(10) if x % 2 == 0]\n" +
                        "# [0, 2, 4, 6, 8]\n\n" +
                        "# String transformation:\n" +
                        "words = ['hello', 'world', 'python']\n" +
                        "upper = [w.upper() for w in words]\n" +
                        "# ['HELLO', 'WORLD', 'PYTHON']\n\n" +
                        "# Nested loop in comprehension:\n" +
                        "pairs = [(x, y) for x in range(3) for y in range(3)]\n" +
                        "# [(0,0),(0,1),(0,2),(1,0),(1,1),(1,2),(2,0),(2,1),(2,2)]\n\n" +
                        "# Dict comprehension:\n" +
                        "d = {k: v for k, v in zip('abc', [1, 2, 3])}\n" +
                        "# {'a': 1, 'b': 2, 'c': 3}"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
