package com.example.developmentapp.screens.python

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
fun PythonBuiltInVariablesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Python — Built-In Variables",
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
                SectionCard("Dunder Variables — Double Underscore") {
                    BodyText("Python names a set of built-in variables surrounded by double underscores — __like_this__. The community calls them \"dunders\" (Double UNDERscore). They are set automatically by the interpreter when a module is loaded. You read them; you rarely write them. Modules, functions, and classes all expose a standard set.")
                    BodyText("They are not magic — they are just ordinary variables that Python populates for you. You can inspect them with dir() or simply print them.")
                    CodeBlock("""
# See all names in the current module
print(dir())
# ['__builtins__', '__doc__', '__loader__',
#  '__name__', '__package__', '__spec__', ...]
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("__debug__") {
                    BodyText("Always True at runtime. Set to False only when Python is run with the -O (optimize) flag, which also strips all assert statements from bytecode. The main use is guarding expensive checks that should vanish in production.")
                    CodeBlock("""
if __debug__:
    print("Running in debug mode — asserts are active")

# assert is silently removed with: python -O script.py
assert x > 0, "x must be positive"

# Run normally:   python script.py   → __debug__ is True
# Run optimized:  python -O script.py → __debug__ is False,
#                                       assert lines never execute
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("__name__") {
                    BodyText("Every module has a __name__. When a file is run directly (e.g. python main.py), __name__ is the string \"__main__\". When the same file is imported by another module, __name__ is the module's own dotted name (e.g. \"utils\" or \"mypackage.utils\").")
                    CodeBlock("""
# Inside any .py file:
print(__name__)
# → "__main__"   if you ran this file directly
# → "utils"      if another file did: import utils
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("__doc__") {
                    BodyText("The module's docstring — the first bare string literal in the file (not assigned to anything). Functions and classes have their own __doc__ too, taken from their own first string literal.")
                    CodeBlock(
                        "\"\"\"This module handles user authentication.\"\"\"\n" +
                        "\n" +
                        "print(__doc__)\n" +
                        "# \"This module handles user authentication.\"\n" +
                        "\n" +
                        "def greet(name):\n" +
                        "    \"\"\"Return a greeting for the given name.\"\"\"\n" +
                        "    return f\"Hello, {name}\"\n" +
                        "\n" +
                        "print(greet.__doc__)\n" +
                        "# \"Return a greeting for the given name.\"\n" +
                        "\n" +
                        "class Dog:\n" +
                        "    \"\"\"Represents a dog.\"\"\"\n" +
                        "    pass\n" +
                        "\n" +
                        "print(Dog.__doc__)   # \"Represents a dog.\""
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("__package__") {
                    BodyText("The name of the package the module belongs to. For a top-level script, __package__ is None or an empty string. For a module inside a package it is the dotted package name. Used internally by the import system for relative imports.")
                    CodeBlock("""
# Inside myapp/utils.py
print(__package__)   # "myapp"

# Inside myapp/sub/helpers.py
print(__package__)   # "myapp.sub"

# Top-level script (python main.py)
print(__package__)   # None  or  ""
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("__file__") {
                    BodyText("The path to the source file. Can be relative or absolute depending on how Python was invoked. Not set for built-in C extension modules. Most useful for locating resources (config files, assets) relative to the running script.")
                    CodeBlock("""
print(__file__)
# e.g.  /home/user/project/main.py
#   or  main.py   (relative, if invoked that way)

# Locate a file next to the current script
import pathlib
here   = pathlib.Path(__file__).parent
config = here / "config.toml"
print(config.exists())
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("_ in Interactive Mode") {
                    BodyText("In the Python REPL (interactive shell), the single underscore _ is automatically set to the result of the last evaluated expression. This is only available interactively — it is not set in scripts. Handy for reusing a result without retyping the expression.")
                    CodeBlock("""
>>> 2 + 2
4
>>> _
4
>>> "hello".upper()
'HELLO'
>>> _
'HELLO'
>>> _ + "!"
'HELLO!'
>>> [1, 2, 3]
[1, 2, 3]
>>> sum(_)
6
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("if __name__ == \"__main__\"") {
                    BodyText("Because __name__ equals \"__main__\" only when the file is run directly, you can guard code inside this check so it only executes on direct runs — not when the file is imported as a library.")
                    BodyText("The idiomatic pattern is to define a main() function and call it only under the guard. This keeps the module importable without side-effects.")
                    CodeBlock("""
# --- simple_script.py ---

def do_work():
    print("Working...")

def main():
    print("Script started")
    do_work()

if __name__ == "__main__":
    main()
                    """.trimIndent())
                    BodyText("A file that can be both a standalone script and an importable library:")
                    CodeBlock("""
# --- math_utils.py ---

def add(a, b):
    return a + b

def multiply(a, b):
    return a * b

def main():
    print(add(3, 4))       # 7
    print(multiply(3, 4))  # 12

if __name__ == "__main__":
    main()

# Used as a script:  python math_utils.py
#   → main() runs, prints 7 and 12

# Used as a library: import math_utils
#   → add() and multiply() are available
#   → main() is NOT called automatically
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
