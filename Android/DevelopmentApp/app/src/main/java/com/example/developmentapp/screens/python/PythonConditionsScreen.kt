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
fun PythonConditionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Python — Conditions & If-Else", color = Color(0xFF00FF41), fontFamily = FontFamily.Monospace, fontSize = 16.sp)
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
                SectionCard("Boolean Values") {
                    BodyText(
                        "Python has two boolean literals: True and False. Any object can be evaluated in a boolean context — " +
                        "values considered false include 0, 0.0, empty strings, empty collections, and None. Everything else is truthy."
                    )
                    CodeBlock(
                        "t = True\n" +
                        "f = False\n\n" +
                        "# bool() converts any value to True or False:\n" +
                        "bool(0)       # False\n" +
                        "bool(42)      # True\n" +
                        "bool('')      # False\n" +
                        "bool('hi')    # True\n" +
                        "bool([])      # False  (empty list)\n" +
                        "bool([1,2])   # True\n" +
                        "bool(None)    # False\n\n" +
                        "# bool is a subclass of int:\n" +
                        "True  == 1    # True\n" +
                        "False == 0    # True\n" +
                        "True  + True  # 2"
                    )
                }
            }

            item {
                SectionCard("Comparison Operators") {
                    BodyText(
                        "Comparison operators work on numbers, strings, and any type that implements __eq__ / __lt__ etc. " +
                        "They always return a bool."
                    )
                    CodeBlock(
                        "x = 10\n" +
                        "y = 20\n\n" +
                        "x == y     # False  — equal\n" +
                        "x != y     # True   — not equal\n" +
                        "x >  y     # False  — greater than\n" +
                        "x >= y     # False  — greater than or equal\n" +
                        "x <  y     # True   — less than\n" +
                        "x <= y     # True   — less than or equal\n\n" +
                        "# Chained comparisons (unique to Python):\n" +
                        "0 < x < 100   # True — reads naturally, no 'and' needed\n" +
                        "1 <= x <= 10  # True\n\n" +
                        "# Comparing variables:\n" +
                        "a = 5\n" +
                        "b = 5\n" +
                        "a == b     # True\n" +
                        "a is b     # True for small ints (CPython caches them)\n" +
                        "           # but don't rely on 'is' for value equality!"
                    )
                }
            }

            item {
                SectionCard("String Comparisons") {
                    BodyText(
                        "Strings are compared lexicographically (character by character by Unicode code point). " +
                        "Use == to check equality; use < / > for alphabetical ordering. " +
                        "Never use 'is' to compare string values — it checks object identity, not content."
                    )
                    CodeBlock(
                        "s1 = 'apple'\n" +
                        "s2 = 'banana'\n\n" +
                        "s1 == s2    # False\n" +
                        "s1 != s2    # True\n" +
                        "s1 <  s2    # True  ('a' < 'b' in Unicode)\n" +
                        "s1 >  s2    # False\n\n" +
                        "'abc' == 'abc'   # True\n" +
                        "'ABC' == 'abc'   # False — case-sensitive!\n" +
                        "'ABC' <  'abc'   # True  (uppercase letters have lower code points)\n\n" +
                        "# Case-insensitive comparison:\n" +
                        "'Hello'.lower() == 'hello'.lower()   # True\n\n" +
                        "# 'is' checks identity — avoid for strings:\n" +
                        "a = input('enter: ')   # user types 'hi'\n" +
                        "a is 'hi'    # False (different object) — BUG!\n" +
                        "a == 'hi'    # True  — correct way"
                    )
                }
            }

            item {
                SectionCard("Logical Operators — and, or, not") {
                    BodyText(
                        "Logical operators combine boolean expressions. Python uses the words 'and', 'or', 'not' — not &&, ||, !. " +
                        "They short-circuit: 'and' stops at the first False, 'or' stops at the first True."
                    )
                    CodeBlock(
                        "# and — True only if both sides are True:\n" +
                        "True  and True    # True\n" +
                        "True  and False   # False\n" +
                        "False and True    # False (right side never evaluated)\n\n" +
                        "# or — True if at least one side is True:\n" +
                        "True  or False    # True  (right side never evaluated)\n" +
                        "False or False    # False\n" +
                        "False or True     # True\n\n" +
                        "# not — inverts the boolean:\n" +
                        "not True          # False\n" +
                        "not False         # True\n" +
                        "not 0             # True  (0 is falsy)\n\n" +
                        "# Combining multiple conditions:\n" +
                        "x = 15\n" +
                        "if x > 10 and x < 20:      # both must be True\n" +
                        "    print('between 10-20')\n\n" +
                        "if x < 5 or x > 10:        # at least one True\n" +
                        "    print('out of 5-10')\n\n" +
                        "# Short-circuit for defaults:\n" +
                        "name = user_input or 'Guest'   # use 'Guest' if input is empty"
                    )
                }
            }

            item {
                SectionCard("If / Elif / Else") {
                    BodyText(
                        "The if statement evaluates a condition and runs the indented block if True. " +
                        "'elif' (else-if) checks additional conditions in order. 'else' runs if none matched."
                    )
                    CodeBlock(
                        "score = 75\n\n" +
                        "if score >= 90:\n" +
                        "    grade = 'A'\n" +
                        "elif score >= 80:\n" +
                        "    grade = 'B'\n" +
                        "elif score >= 70:\n" +
                        "    grade = 'C'\n" +
                        "else:\n" +
                        "    grade = 'F'\n\n" +
                        "print(grade)   # 'C'\n\n" +
                        "# Nested if:\n" +
                        "x = 10\n" +
                        "if x > 0:\n" +
                        "    if x % 2 == 0:\n" +
                        "        print('positive and even')\n" +
                        "    else:\n" +
                        "        print('positive and odd')\n\n" +
                        "# One-liner ternary expression:\n" +
                        "result = 'even' if x % 2 == 0 else 'odd'\n" +
                        "label  = 'admin' if is_admin else 'user'\n\n" +
                        "# Checking membership in an if:\n" +
                        "colors = ['red', 'green', 'blue']\n" +
                        "if 'red' in colors:\n" +
                        "    print('found red')"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
