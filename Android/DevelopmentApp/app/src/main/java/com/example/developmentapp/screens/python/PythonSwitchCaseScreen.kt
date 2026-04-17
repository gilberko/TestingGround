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
fun PythonSwitchCaseScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Python — Switch / Match",
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
            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Python Has No 'switch' Keyword") {
                    BodyText("Python has never had a C-style 'switch' statement. For years the idiomatic alternatives were an if/elif chain or a dict dispatch. Python 3.10 (released October 2021) introduced 'match/case' — Structural Pattern Matching — which is more powerful than a traditional switch.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("match / case — Basic Syntax (Python 3.10+)") {
                    BodyText("The 'match' statement compares a subject against a sequence of patterns. The first matching 'case' block runs. 'case _:' is the wildcard / default.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "def handle_command(command):\n" +
                        "    match command:\n" +
                        "        case \"quit\":\n" +
                        "            print(\"Quitting...\")\n" +
                        "        case \"help\":\n" +
                        "            print(\"Available commands: quit, help, start\")\n" +
                        "        case \"start\":\n" +
                        "            print(\"Starting...\")\n" +
                        "        case _:              # default / wildcard\n" +
                        "            print(f\"Unknown command: {command}\")\n\n" +
                        "handle_command(\"help\")   # prints: Available commands: ..."
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("Unlike C switch, there is no fall-through. Each case is independent. No 'break' needed.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Matching Literals and OR Patterns") {
                    BodyText("Match against numbers, strings, booleans, or None. Use '|' to match multiple values in one case:")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "def http_status(code):\n" +
                        "    match code:\n" +
                        "        case 200:\n" +
                        "            return \"OK\"\n" +
                        "        case 400 | 401 | 403:\n" +
                        "            return \"Client Error\"\n" +
                        "        case 404:\n" +
                        "            return \"Not Found\"\n" +
                        "        case 500 | 502 | 503:\n" +
                        "            return \"Server Error\"\n" +
                        "        case _:\n" +
                        "            return \"Unknown\""
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Guard Conditions (if)") {
                    BodyText("Add an 'if' guard to a case to impose an extra condition. The pattern must match AND the guard must be true:")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "def classify(n):\n" +
                        "    match n:\n" +
                        "        case 0:\n" +
                        "            return \"zero\"\n" +
                        "        case x if x > 0:\n" +
                        "            return f\"{x} is positive\"\n" +
                        "        case x if x < 0:\n" +
                        "            return f\"{x} is negative\"\n\n" +
                        "# 'x' is a capture variable — it binds to the value of n"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Matching Sequences and Tuples") {
                    BodyText("Patterns can destructure sequences. The case pattern mirrors the structure you expect:")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "def describe_point(point):\n" +
                        "    match point:\n" +
                        "        case (0, 0):\n" +
                        "            return \"Origin\"\n" +
                        "        case (x, 0):\n" +
                        "            return f\"On x-axis at {x}\"\n" +
                        "        case (0, y):\n" +
                        "            return f\"On y-axis at {y}\"\n" +
                        "        case (x, y):\n" +
                        "            return f\"Point at ({x}, {y})\"\n\n" +
                        "describe_point((3, 0))   # \"On x-axis at 3\"\n\n" +
                        "# List patterns with * for the rest:\n" +
                        "def first_and_rest(lst):\n" +
                        "    match lst:\n" +
                        "        case []:\n" +
                        "            return \"empty\"\n" +
                        "        case [first, *rest]:\n" +
                        "            return f\"head={first}, tail={rest}\""
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Matching Objects (Class Patterns)") {
                    BodyText("Match against instances of a class and destructure their attributes:")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "from dataclasses import dataclass\n\n" +
                        "@dataclass\n" +
                        "class Point:\n" +
                        "    x: float\n" +
                        "    y: float\n\n" +
                        "def classify_point(p):\n" +
                        "    match p:\n" +
                        "        case Point(x=0, y=0):\n" +
                        "            return \"Origin\"\n" +
                        "        case Point(x=0, y=y):\n" +
                        "            return f\"Y-axis at y={y}\"\n" +
                        "        case Point(x=x, y=0):\n" +
                        "            return f\"X-axis at x={x}\"\n" +
                        "        case Point(x=x, y=y):\n" +
                        "            return f\"({x}, {y})\"\n\n" +
                        "classify_point(Point(0, 5))   # \"Y-axis at y=5\""
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Pre-3.10: Dict Dispatch & if/elif") {
                    BodyText("Before match/case, the two idiomatic approaches were:")
                    Spacer(Modifier.height(6.dp))
                    BodyText("1. if/elif chain (always works, verbose for many cases):")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "def handle(cmd):\n" +
                        "    if cmd == \"quit\":\n" +
                        "        do_quit()\n" +
                        "    elif cmd == \"help\":\n" +
                        "        do_help()\n" +
                        "    elif cmd == \"start\":\n" +
                        "        do_start()\n" +
                        "    else:\n" +
                        "        print(\"unknown\")"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("2. Dict dispatch (fast O(1) lookup, good for many cases):")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "def do_unknown(cmd):\n" +
                        "    print(f\"Unknown: {cmd}\")\n\n" +
                        "dispatch = {\n" +
                        "    \"quit\":  do_quit,\n" +
                        "    \"help\":  do_help,\n" +
                        "    \"start\": do_start,\n" +
                        "}\n\n" +
                        "def handle(cmd):\n" +
                        "    fn = dispatch.get(cmd, lambda: do_unknown(cmd))\n" +
                        "    fn()"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("Dict dispatch is efficient but can't do structural destructuring. For Python 3.10+ code, prefer match/case when the logic involves patterns or structure.")
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
