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
fun PythonLibrariesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Python — Libraries & Imports", color = Color(0xFF00FF41), fontFamily = FontFamily.Monospace, fontSize = 16.sp)
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
                SectionCard("Installing a Library with pip") {
                    BodyText(
                        "pip is Python's package manager. It downloads and installs libraries from PyPI (the Python Package Index). " +
                        "Run pip commands in your terminal, not inside a Python script."
                    )
                    CodeBlock(
                        "# Install a package:\n" +
                        "pip install requests\n" +
                        "pip install numpy pandas matplotlib\n\n" +
                        "# Install a specific version:\n" +
                        "pip install requests==2.31.0\n\n" +
                        "# Upgrade to the latest version:\n" +
                        "pip install --upgrade requests\n\n" +
                        "# List installed packages:\n" +
                        "pip list\n\n" +
                        "# Show info about a package:\n" +
                        "pip show requests\n\n" +
                        "# Uninstall:\n" +
                        "pip uninstall requests\n\n" +
                        "# Save dependencies to a file (for reproducibility):\n" +
                        "pip freeze > requirements.txt\n\n" +
                        "# Install from a requirements file:\n" +
                        "pip install -r requirements.txt"
                    )
                }
            }

            item {
                SectionCard("Virtual Environments") {
                    BodyText(
                        "A virtual environment is an isolated Python installation for a single project. " +
                        "It prevents library version conflicts between projects. Always use one — it's a best practice."
                    )
                    CodeBlock(
                        "# Create a virtual environment named 'venv':\n" +
                        "python -m venv venv\n\n" +
                        "# Activate (Windows):\n" +
                        "venv\\Scripts\\activate\n\n" +
                        "# Activate (macOS/Linux):\n" +
                        "source venv/bin/activate\n\n" +
                        "# You'll see (venv) in your prompt when active.\n" +
                        "# Now pip install goes into this env only.\n\n" +
                        "# Deactivate:\n" +
                        "deactivate\n\n" +
                        "# The venv folder should NOT be committed to git.\n" +
                        "# Add it to .gitignore:\n" +
                        "# venv/"
                    )
                }
            }

            item {
                SectionCard("Importing Modules") {
                    BodyText(
                        "Use 'import' to bring a module into your script. You can import the whole module, " +
                        "specific names from it, or give it a shorter alias."
                    )
                    CodeBlock(
                        "# Import the whole module — access via module.name:\n" +
                        "import math\n" +
                        "math.sqrt(16)       # 4.0\n" +
                        "math.pi             # 3.14159...\n" +
                        "math.floor(3.9)     # 3\n\n" +
                        "# Import specific names — use directly:\n" +
                        "from math import sqrt, pi, ceil\n" +
                        "sqrt(25)            # 5.0  (no 'math.' prefix)\n\n" +
                        "# Import everything (avoid — pollutes namespace):\n" +
                        "# from math import *\n\n" +
                        "# Import with alias:\n" +
                        "import numpy as np\n" +
                        "import pandas as pd\n" +
                        "arr = np.array([1, 2, 3])\n\n" +
                        "# Import multiple items from one module:\n" +
                        "from os.path import join, exists, dirname"
                    )
                }
            }

            item {
                SectionCard("Using Standard Library Modules") {
                    BodyText(
                        "Python ships with a rich standard library — no pip needed for these."
                    )
                    CodeBlock(
                        "import os\n" +
                        "os.getcwd()                    # current working directory\n" +
                        "os.listdir('.')                # list files in current dir\n" +
                        "os.path.join('dir', 'file.txt')# safe path join\n" +
                        "os.path.exists('file.txt')     # check if file exists\n\n" +
                        "import sys\n" +
                        "sys.argv           # command-line arguments\n" +
                        "sys.exit(0)        # exit the program\n" +
                        "sys.version        # Python version string\n\n" +
                        "import random\n" +
                        "random.randint(1, 6)           # random int between 1 and 6\n" +
                        "random.choice(['a','b','c'])   # random item from list\n" +
                        "random.shuffle(my_list)        # shuffle list in place\n\n" +
                        "import datetime\n" +
                        "today = datetime.date.today()  # today's date\n" +
                        "now   = datetime.datetime.now()# current date and time\n\n" +
                        "import json\n" +
                        "json.dumps({'key': 'value'})   # dict → JSON string\n" +
                        "json.loads('{\"key\": \"value\"}') # JSON string → dict"
                    )
                }
            }

            item {
                SectionCard("Creating Your Own Module") {
                    BodyText(
                        "Any .py file is a module. Put reusable functions or classes in a file, " +
                        "then import it from another script in the same directory."
                    )
                    CodeBlock(
                        "# --- mymath.py ---\n" +
                        "def add(a, b):\n" +
                        "    return a + b\n\n" +
                        "def square(x):\n" +
                        "    return x * x\n\n" +
                        "PI = 3.14159\n\n" +
                        "# --- main.py (same directory) ---\n" +
                        "import mymath\n" +
                        "print(mymath.add(2, 3))     # 5\n" +
                        "print(mymath.PI)            # 3.14159\n\n" +
                        "# Or import specific names:\n" +
                        "from mymath import square\n" +
                        "print(square(4))            # 16"
                    )
                }
            }

            item {
                SectionCard("if __name__ == \"__main__\"") {
                    BodyText(
                        "When Python runs a file directly (e.g. 'python script.py'), it sets the special variable " +
                        "__name__ to the string '__main__'. When the same file is imported by another script, " +
                        "__name__ is set to the module's name instead. " +
                        "This lets you put code that should only run when the file is the entry point — " +
                        "not when it is imported as a library."
                    )
                    CodeBlock(
                        "# mymodule.py\n\n" +
                        "def greet(name):\n" +
                        "    print(f'Hello, {name}!')\n\n" +
                        "if __name__ == '__main__':\n" +
                        "    # This block runs ONLY when you execute:\n" +
                        "    #   python mymodule.py\n" +
                        "    # It does NOT run when another script does:\n" +
                        "    #   import mymodule\n" +
                        "    greet('World')"
                    )
                    BodyText(
                        "A common and clean pattern is to define a main() function that contains the script's " +
                        "top-level logic, then call it inside the guard. This makes the code easier to test, " +
                        "read, and reuse — the function can be called explicitly from tests or other scripts."
                    )
                    CodeBlock(
                        "# script.py\n\n" +
                        "def process(data):\n" +
                        "    return [x * 2 for x in data]\n\n" +
                        "def main():\n" +
                        "    data = [1, 2, 3, 4, 5]\n" +
                        "    result = process(data)\n" +
                        "    print('Result:', result)\n\n" +
                        "if __name__ == '__main__':\n" +
                        "    main()   # only runs when this file is the entry point\n\n" +
                        "# --- another_script.py ---\n" +
                        "import script              # imports fine — main() not called\n" +
                        "out = script.process([10, 20])  # reuse process() directly"
                    )
                }
            }

            item {
                SectionCard("Packages — Folders of Modules") {
                    BodyText(
                        "A package is a directory containing an __init__.py file and multiple module files. " +
                        "It lets you organize code into a namespace hierarchy."
                    )
                    CodeBlock(
                        "# Directory structure:\n" +
                        "# mypackage/\n" +
                        "#   __init__.py      (makes it a package — can be empty)\n" +
                        "#   geometry.py\n" +
                        "#   strings.py\n\n" +
                        "# --- mypackage/geometry.py ---\n" +
                        "def area_circle(r):\n" +
                        "    return 3.14159 * r * r\n\n" +
                        "# --- main.py ---\n" +
                        "import mypackage.geometry\n" +
                        "mypackage.geometry.area_circle(5)\n\n" +
                        "# Or:\n" +
                        "from mypackage.geometry import area_circle\n" +
                        "area_circle(5)\n\n" +
                        "# __init__.py can re-export for convenience:\n" +
                        "# mypackage/__init__.py:\n" +
                        "#   from .geometry import area_circle\n" +
                        "# Then:\n" +
                        "from mypackage import area_circle"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
