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
fun PythonStringsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Python — Strings", color = Color(0xFF00FF41), fontFamily = FontFamily.Monospace, fontSize = 16.sp)
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
                SectionCard("String Basics and Length") {
                    BodyText(
                        "Strings are immutable sequences of Unicode characters. There is no separate 'char' type — " +
                        "a single character is just a string of length 1. Use len() to get the number of characters."
                    )
                    CodeBlock(
                        "s = 'hello'          # single quotes\n" +
                        "s = \"hello\"          # double quotes — identical\n" +
                        "s = \"\"\"multi\nline\"\"\" # triple quotes for multi-line strings\n\n" +
                        "# Raw strings — backslashes are literal (useful for paths/regex):\n" +
                        "path = r'C:\\Users\\Alice\\file.txt'\n\n" +
                        "# Length:\n" +
                        "len('hello')       # 5\n" +
                        "len('')            # 0\n" +
                        "len('hello world') # 11\n\n" +
                        "# Strings are Unicode — emojis are one character:\n" +
                        "len('A')           # 1"
                    )
                }
            }

            item {
                SectionCard("Checking if a String is Empty") {
                    BodyText(
                        "An empty string is falsy in Python. There are several idiomatic ways to check — " +
                        "the most Pythonic is 'if not s:' or 'if s:'."
                    )
                    CodeBlock(
                        "s = ''\n\n" +
                        "# Most Pythonic — relies on truthiness:\n" +
                        "if not s:\n" +
                        "    print('empty')\n\n" +
                        "if s:\n" +
                        "    print('not empty')\n\n" +
                        "# Explicit checks (also valid):\n" +
                        "if s == '':\n" +
                        "    print('empty')\n\n" +
                        "if len(s) == 0:\n" +
                        "    print('empty')\n\n" +
                        "# Watch out for whitespace-only strings:\n" +
                        "s2 = '   '\n" +
                        "if not s2:               # False — not empty, contains spaces\n" +
                        "    print('empty')       # does NOT print\n" +
                        "if not s2.strip():       # True — strip() removes whitespace first\n" +
                        "    print('blank')"
                    )
                }
            }

            item {
                SectionCard("Concatenation") {
                    BodyText(
                        "Strings can be joined with +, formatted with f-strings, or assembled from a list with join(). " +
                        "For building many strings in a loop, join() is much faster than repeated + operations."
                    )
                    CodeBlock(
                        "# + operator:\n" +
                        "greeting = 'Hello' + ', ' + 'World!'  # 'Hello, World!'\n\n" +
                        "# f-string (recommended for formatting):\n" +
                        "name = 'Alice'\n" +
                        "age  = 30\n" +
                        "msg  = f'Name: {name}, Age: {age}'   # 'Name: Alice, Age: 30'\n" +
                        "calc = f'Sum: {1 + 2 + 3}'           # 'Sum: 6'\n\n" +
                        "# str.join() — fast, clean, idiomatic:\n" +
                        "parts = ['one', 'two', 'three']\n" +
                        "', '.join(parts)   # 'one, two, three'\n" +
                        "'-'.join(parts)    # 'one-two-three'\n" +
                        "''.join(parts)     # 'onetwothree'\n\n" +
                        "# Building in a loop — use join, not +=:\n" +
                        "words = ['a', 'b', 'c', 'd']\n" +
                        "result = ' '.join(words)   # efficient\n\n" +
                        "# * repeats a string:\n" +
                        "'ha' * 3           # 'hahaha'"
                    )
                }
            }

            item {
                SectionCard("Substrings — Slicing") {
                    BodyText(
                        "Python slicing extracts a substring using [start:stop:step]. " +
                        "The start is inclusive, stop is exclusive. Negative indices count from the end."
                    )
                    CodeBlock(
                        "s = 'Hello, World!'\n\n" +
                        "# s[start:stop]  — characters from start up to (not including) stop:\n" +
                        "s[0:5]    # 'Hello'\n" +
                        "s[7:12]   # 'World'\n\n" +
                        "# Omitting start or stop:\n" +
                        "s[:5]     # 'Hello'        (from beginning)\n" +
                        "s[7:]     # 'World!'       (to end)\n" +
                        "s[:]      # 'Hello, World!' (full copy)\n\n" +
                        "# Negative indices (count from end):\n" +
                        "s[-1]     # '!'   (last char)\n" +
                        "s[-6:]    # 'orld!'  — last 6 chars\n" +
                        "s[:-1]    # 'Hello, World' — all but last\n\n" +
                        "# Step:\n" +
                        "s[::2]    # 'Hlo ol!'  — every 2nd character\n" +
                        "s[::-1]   # '!dlroW ,olleH' — reverse the string"
                    )
                }
            }

            item {
                SectionCard("Accessing Characters") {
                    BodyText(
                        "Access individual characters with s[i]. Strings are immutable — you cannot assign to s[i]. " +
                        "Iterate character by character with a for loop."
                    )
                    CodeBlock(
                        "s = 'Python'\n\n" +
                        "s[0]    # 'P'   (first character)\n" +
                        "s[1]    # 'y'\n" +
                        "s[-1]   # 'n'   (last character)\n" +
                        "s[-2]   # 'o'\n\n" +
                        "# Attempting to assign raises TypeError:\n" +
                        "# s[0] = 'p'   # ERROR — strings are immutable!\n\n" +
                        "# To 'change' a string, build a new one:\n" +
                        "new_s = 'p' + s[1:]    # 'python'\n\n" +
                        "# Iterating characters:\n" +
                        "for ch in 'hello':\n" +
                        "    print(ch)   # h  e  l  l  o\n\n" +
                        "# With index:\n" +
                        "for i, ch in enumerate('hello'):\n" +
                        "    print(i, ch)   # 0 h / 1 e / ..."
                    )
                }
            }

            item {
                SectionCard("Useful String Methods") {
                    BodyText(
                        "Strings have many built-in methods. All return new strings — the original is never modified."
                    )
                    CodeBlock(
                        "s = '  Hello, World!  '\n\n" +
                        "# Case:\n" +
                        "s.upper()          # '  HELLO, WORLD!  '\n" +
                        "s.lower()          # '  hello, world!  '\n\n" +
                        "# Whitespace:\n" +
                        "s.strip()          # 'Hello, World!'      — remove leading/trailing spaces\n" +
                        "s.lstrip()         # 'Hello, World!  '    — left only\n" +
                        "s.rstrip()         # '  Hello, World!'    — right only\n\n" +
                        "# Searching:\n" +
                        "s2 = 'Hello, World!'\n" +
                        "'World' in s2      # True  — membership test (preferred)\n" +
                        "s2.find('World')   # 7     — index of first match (-1 if not found)\n" +
                        "s2.index('World')  # 7     — same but raises ValueError if not found\n\n" +
                        "# Starts / ends:\n" +
                        "s2.startswith('Hello')   # True\n" +
                        "s2.endswith('!')         # True\n\n" +
                        "# Split and replace:\n" +
                        "'a,b,c'.split(',')        # ['a', 'b', 'c']\n" +
                        "'hello world'.split()     # ['hello', 'world'] (splits on whitespace)\n" +
                        "'hello'.replace('l', 'r') # 'herro'\n\n" +
                        "# Check content:\n" +
                        "'123'.isdigit()    # True\n" +
                        "'abc'.isalpha()    # True\n" +
                        "'abc123'.isalnum() # True"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
