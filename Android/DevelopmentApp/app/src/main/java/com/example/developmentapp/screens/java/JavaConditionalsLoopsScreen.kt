package com.example.developmentapp.screens.java

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
fun JavaConditionalsLoopsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Java — Conditionals and Loops",
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
        LazyColumn(modifier = Modifier.padding(innerPadding).padding(horizontal = 16.dp)) {
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "if / else if / else") {
                    BodyText(
                        "The if statement evaluates a boolean expression and executes a block if it is " +
                        "true. else if adds additional conditions. else catches everything that did not " +
                        "match. Braces are optional for single-statement bodies, but omitting them is a " +
                        "common source of bugs — prefer to always include them."
                    )
                    CodeBlock(
                        "int score = 75;\n\n" +
                        "if (score >= 90) {\n" +
                        "    System.out.println(\"A\");\n" +
                        "} else if (score >= 80) {\n" +
                        "    System.out.println(\"B\");\n" +
                        "} else if (score >= 70) {\n" +
                        "    System.out.println(\"C\");  // prints this\n" +
                        "} else {\n" +
                        "    System.out.println(\"F\");\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "switch / case / default") {
                    BodyText(
                        "switch tests a single value against multiple cases. You can switch on int, " +
                        "char, String, or enum types. break is required at the end of each case to " +
                        "stop execution from falling through into the next case."
                    )
                    BodyText(
                        "Fall-through: if you omit break, Java continues executing the code in the " +
                        "following case without re-checking its value. This is sometimes intentional " +
                        "(multiple cases sharing the same action) but is a common source of bugs. " +
                        "default runs when no case matches — it is like the else branch."
                    )
                    CodeBlock(
                        "int day = 3;\n\n" +
                        "switch (day) {\n" +
                        "    case 1:\n" +
                        "        System.out.println(\"Monday\");\n" +
                        "        break;  // required — stops here\n" +
                        "    case 2:\n" +
                        "        System.out.println(\"Tuesday\");\n" +
                        "        break;\n" +
                        "    case 3:\n" +
                        "        System.out.println(\"Wednesday\");  // prints this\n" +
                        "        break;\n" +
                        "    default:\n" +
                        "        System.out.println(\"Other\");\n" +
                        "}\n\n" +
                        "// Intentional fall-through — cases 1 and 2 share code:\n" +
                        "switch (day) {\n" +
                        "    case 1:\n" +
                        "    case 2:\n" +
                        "        System.out.println(\"Mon or Tue\");\n" +
                        "        break;\n" +
                        "    default:\n" +
                        "        System.out.println(\"Other\");\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "while Loop") {
                    BodyText(
                        "The while loop checks its condition before each iteration. If the condition is " +
                        "false at the start, the body never executes. Use it when you don't know in " +
                        "advance how many iterations are needed."
                    )
                    CodeBlock(
                        "int i = 0;\n" +
                        "while (i < 5) {\n" +
                        "    System.out.println(i);  // prints 0 1 2 3 4\n" +
                        "    i++;\n" +
                        "}\n\n" +
                        "// Infinite loop (needs a break inside):\n" +
                        "while (true) {\n" +
                        "    // do work...\n" +
                        "    break;  // exit when done\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "do-while Loop") {
                    BodyText(
                        "The do-while loop executes its body first, then checks the condition. This " +
                        "guarantees the body runs at least once, regardless of the condition. Note the " +
                        "semicolon after the closing parenthesis — it is required."
                    )
                    CodeBlock(
                        "int i = 0;\n" +
                        "do {\n" +
                        "    System.out.println(i);  // prints 0 1 2 3 4\n" +
                        "    i++;\n" +
                        "} while (i < 5);  // <-- semicolon required\n\n" +
                        "// Body always runs at least once:\n" +
                        "int x = 100;\n" +
                        "do {\n" +
                        "    System.out.println(\"ran once\");  // prints even though x > 5\n" +
                        "} while (x < 5);"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "for Loop") {
                    BodyText(
                        "The for loop bundles initialisation, condition, and update into one line. " +
                        "All three parts are optional (leaving them blank gives an infinite loop). " +
                        "You can initialise and update multiple variables by separating them with commas."
                    )
                    CodeBlock(
                        "// Basic for loop\n" +
                        "for (int i = 0; i < 5; i++) {\n" +
                        "    System.out.println(i);  // 0 1 2 3 4\n" +
                        "}\n\n" +
                        "// Multiple init and update variables:\n" +
                        "for (int i = 0, j = 10; i < j; i++, j--) {\n" +
                        "    System.out.println(i + \" \" + j);\n" +
                        "    // prints: 0 10 / 1 9 / 2 8 / 3 7 / 4 6\n" +
                        "}\n\n" +
                        "// Infinite for loop:\n" +
                        "for (;;) {\n" +
                        "    break;\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "break and continue") {
                    BodyText(
                        "break immediately exits the innermost enclosing loop (or switch). Execution " +
                        "continues with the statement after the loop."
                    )
                    BodyText(
                        "continue skips the remainder of the current loop iteration and jumps back to " +
                        "the loop's condition check (or update expression, for a for loop)."
                    )
                    BodyText(
                        "Both work the same way in while, do-while, and for loops."
                    )
                    CodeBlock(
                        "// break — stop when we find 3\n" +
                        "for (int i = 0; i < 10; i++) {\n" +
                        "    if (i == 3) break;\n" +
                        "    System.out.print(i + \" \");  // prints: 0 1 2\n" +
                        "}\n\n" +
                        "// continue — skip even numbers\n" +
                        "for (int i = 0; i < 6; i++) {\n" +
                        "    if (i % 2 == 0) continue;\n" +
                        "    System.out.print(i + \" \");  // prints: 1 3 5\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Labels for Nested Loops") {
                    BodyText(
                        "Java supports labels — a name followed by a colon placed before a loop. You " +
                        "can then use break labelName or continue labelName to target an outer loop " +
                        "instead of the innermost one. This is Java's structured alternative to goto " +
                        "for loop control."
                    )
                    BodyText(
                        "break labelName exits the labeled loop entirely. continue labelName skips the " +
                        "rest of the current inner iteration and moves to the next iteration of the " +
                        "labeled (outer) loop."
                    )
                    CodeBlock(
                        "outer:\n" +
                        "for (int i = 0; i < 3; i++) {\n" +
                        "    for (int j = 0; j < 3; j++) {\n" +
                        "        if (j == 1) continue outer;  // skip to next i\n" +
                        "        System.out.println(i + \",\" + j);\n" +
                        "    }\n" +
                        "}\n" +
                        "// Output: 0,0 / 1,0 / 2,0  (j==1 always skips to next outer iteration)\n\n" +
                        "outer:\n" +
                        "for (int i = 0; i < 3; i++) {\n" +
                        "    for (int j = 0; j < 3; j++) {\n" +
                        "        if (i == 1 && j == 1) break outer;  // exit both loops\n" +
                        "        System.out.println(i + \",\" + j);\n" +
                        "    }\n" +
                        "}\n" +
                        "// Output: 0,0 / 0,1 / 0,2 / 1,0  (stops when i=1, j=1)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Enhanced for Loop (for-each)") {
                    BodyText(
                        "The enhanced for loop (for-each) iterates over all elements of an array or " +
                        "any object implementing Iterable<T> (such as ArrayList, LinkedList, HashSet). " +
                        "It is cleaner than a traditional index-based loop when you only need to read " +
                        "each element and don't need the index."
                    )
                    CodeBlock(
                        "// Array\n" +
                        "int[] numbers = {10, 20, 30, 40};\n" +
                        "for (int n : numbers) {\n" +
                        "    System.out.println(n);  // 10 20 30 40\n" +
                        "}\n\n" +
                        "// ArrayList\n" +
                        "import java.util.ArrayList;\n" +
                        "ArrayList<String> names = new ArrayList<>();\n" +
                        "names.add(\"Alice\");\n" +
                        "names.add(\"Bob\");\n" +
                        "names.add(\"Carol\");\n\n" +
                        "for (String name : names) {\n" +
                        "    System.out.println(name);  // Alice Bob Carol\n" +
                        "}"
                    )
                    BodyText(
                        "The variable (n, name) is a copy of each element — modifying it does not " +
                        "change the original array or collection. You also cannot add or remove " +
                        "elements from the collection during a for-each loop (that throws " +
                        "ConcurrentModificationException). Use an Iterator or a traditional index " +
                        "loop if you need to modify the collection while iterating."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
