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
fun PythonInputOutputScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Python — Input & Output", color = Color(0xFF00FF41), fontFamily = FontFamily.Monospace, fontSize = 16.sp)
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
                SectionCard("Printing to the Screen") {
                    BodyText(
                        "print() writes to standard output (the terminal). It automatically adds a newline at the end. " +
                        "f-strings are the recommended way to embed variables and expressions into output."
                    )
                    CodeBlock(
                        "print('Hello, World!')         # Hello, World!\n" +
                        "print(42)                      # 42\n" +
                        "print(3.14)                    # 3.14\n\n" +
                        "# f-strings (Python 3.6+) — embed values directly:\n" +
                        "name = 'Alice'\n" +
                        "age  = 30\n" +
                        "print(f'Name: {name}, Age: {age}')  # Name: Alice, Age: 30\n" +
                        "print(f'2 + 2 = {2 + 2}')           # 2 + 2 = 4\n\n" +
                        "# sep= changes the separator between arguments:\n" +
                        "print('a', 'b', 'c')           # a b c  (space by default)\n" +
                        "print('a', 'b', 'c', sep=', ') # a, b, c\n" +
                        "print('a', 'b', 'c', sep='')   # abc\n\n" +
                        "# end= changes what's printed at the end (default: newline):\n" +
                        "print('loading', end='')\n" +
                        "print('...')                   # loading...\n\n" +
                        "# Unpack a list into print:\n" +
                        "items = [1, 2, 3]\n" +
                        "print(*items)                  # 1 2 3"
                    )
                }
            }

            item {
                SectionCard("Reading User Input") {
                    BodyText(
                        "input() reads a line of text from the user. It always returns a string — " +
                        "convert it with int() or float() if you need a number."
                    )
                    CodeBlock(
                        "# Basic input — returns a string:\n" +
                        "name = input('Enter your name: ')\n" +
                        "print(f'Hello, {name}!')\n\n" +
                        "# Reading a number — must convert:\n" +
                        "age = int(input('Enter your age: '))     # int\n" +
                        "price = float(input('Enter price: '))    # float\n\n" +
                        "# Safe input with error handling:\n" +
                        "while True:\n" +
                        "    try:\n" +
                        "        n = int(input('Enter a number: '))\n" +
                        "        break           # valid input — exit loop\n" +
                        "    except ValueError:\n" +
                        "        print('That is not a number, try again.')\n\n" +
                        "# Reading multiple values on one line:\n" +
                        "parts = input('x y: ').split()    # e.g. '3 5' → ['3', '5']\n" +
                        "x, y = int(parts[0]), int(parts[1])"
                    )
                }
            }

            item {
                SectionCard("Opening Files") {
                    BodyText(
                        "open() opens a file and returns a file object. Always use the 'with' statement — " +
                        "it automatically closes the file when the block exits, even if an exception occurs."
                    )
                    CodeBlock(
                        "# Modes:\n" +
                        "#  'r'  — read text (default)\n" +
                        "#  'w'  — write text (creates file, overwrites if exists)\n" +
                        "#  'a'  — append text (creates file, adds to end if exists)\n" +
                        "#  'x'  — create new file (fails if already exists)\n" +
                        "#  'rb' — read binary\n" +
                        "#  'wb' — write binary\n\n" +
                        "# With statement — always prefer this:\n" +
                        "with open('data.txt', 'r') as f:\n" +
                        "    content = f.read()\n" +
                        "# File is automatically closed here\n\n" +
                        "# Without 'with' — you must close manually:\n" +
                        "f = open('data.txt', 'r')\n" +
                        "content = f.read()\n" +
                        "f.close()    # easy to forget — use 'with' instead\n\n" +
                        "# Encoding — specify for text files:\n" +
                        "with open('data.txt', 'r', encoding='utf-8') as f:\n" +
                        "    content = f.read()"
                    )
                }
            }

            item {
                SectionCard("Reading Files") {
                    BodyText(
                        "There are several ways to read file content. For large files, iterate line by line " +
                        "instead of reading everything into memory at once."
                    )
                    CodeBlock(
                        "# read() — entire file as one string:\n" +
                        "with open('data.txt') as f:\n" +
                        "    content = f.read()\n" +
                        "    print(content)\n\n" +
                        "# readline() — one line at a time:\n" +
                        "with open('data.txt') as f:\n" +
                        "    first  = f.readline()   # '...\\n'\n" +
                        "    second = f.readline()\n\n" +
                        "# readlines() — all lines as a list:\n" +
                        "with open('data.txt') as f:\n" +
                        "    lines = f.readlines()   # ['line1\\n', 'line2\\n', ...]\n\n" +
                        "# Best practice — iterate line by line (memory efficient):\n" +
                        "with open('data.txt') as f:\n" +
                        "    for line in f:\n" +
                        "        print(line.strip())  # strip() removes trailing newline\n\n" +
                        "# Strip the newline when processing lines:\n" +
                        "with open('data.txt') as f:\n" +
                        "    lines = [line.rstrip('\\n') for line in f]"
                    )
                }
            }

            item {
                SectionCard("Writing Files") {
                    BodyText(
                        "Use mode 'w' to create or overwrite a file, 'a' to append. " +
                        "write() does not add a newline automatically — include '\\n' yourself."
                    )
                    CodeBlock(
                        "# Write — creates or overwrites:\n" +
                        "with open('output.txt', 'w') as f:\n" +
                        "    f.write('Hello, World!\\n')   # must include \\n manually\n" +
                        "    f.write('Second line\\n')\n\n" +
                        "# writelines() — write a list of strings:\n" +
                        "lines = ['one\\n', 'two\\n', 'three\\n']\n" +
                        "with open('output.txt', 'w') as f:\n" +
                        "    f.writelines(lines)\n\n" +
                        "# Append — adds to end without erasing:\n" +
                        "with open('log.txt', 'a') as f:\n" +
                        "    f.write('New log entry\\n')\n\n" +
                        "# Convenient pattern — write many lines at once:\n" +
                        "data = [f'item {i}' for i in range(5)]\n" +
                        "with open('output.txt', 'w') as f:\n" +
                        "    f.write('\\n'.join(data) + '\\n')"
                    )
                }
            }

            item {
                SectionCard("Error Handling for Files") {
                    BodyText(
                        "File operations can fail — the file might not exist, you might lack permissions, or the disk could be full. " +
                        "Wrap file code in try/except to handle errors gracefully."
                    )
                    CodeBlock(
                        "# FileNotFoundError — file does not exist:\n" +
                        "try:\n" +
                        "    with open('missing.txt') as f:\n" +
                        "        data = f.read()\n" +
                        "except FileNotFoundError:\n" +
                        "    print('File not found!')\n\n" +
                        "# PermissionError — no read/write access:\n" +
                        "try:\n" +
                        "    with open('/etc/shadow') as f:\n" +
                        "        data = f.read()\n" +
                        "except PermissionError:\n" +
                        "    print('Access denied!')\n\n" +
                        "# Catch any IO error:\n" +
                        "try:\n" +
                        "    with open('data.txt') as f:\n" +
                        "        data = f.read()\n" +
                        "except OSError as e:\n" +
                        "    print(f'Error: {e}')\n\n" +
                        "# Check before opening (less robust than try/except):\n" +
                        "import os\n" +
                        "if os.path.exists('data.txt'):\n" +
                        "    with open('data.txt') as f:\n" +
                        "        data = f.read()"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
