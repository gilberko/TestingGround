package com.example.developmentapp.screens.go

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
fun GoConditionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Go — Conditions",
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
                        "The condition does NOT need parentheses () — idiomatic Go omits them. " +
                        "Curly braces {} ARE mandatory, even for a single-line body."
                    )
                    CodeBlock(
                        "x := 15\n\n" +
                        "if x > 20 {\n" +
                        "    fmt.Println(\"big\")\n" +
                        "} else if x > 10 {\n" +
                        "    fmt.Println(\"medium\")   // prints this\n" +
                        "} else {\n" +
                        "    fmt.Println(\"small\")\n" +
                        "}"
                    )
                    BodyText(
                        "Go also supports an init statement before the condition, separated by a " +
                        "semicolon. The variable declared in the init is scoped to the entire " +
                        "if/else if/else chain — it is not accessible afterwards."
                    )
                    CodeBlock(
                        "if n := compute(); n > 0 {\n" +
                        "    fmt.Println(\"positive:\", n)\n" +
                        "} else {\n" +
                        "    fmt.Println(\"non-positive:\", n)\n" +
                        "}\n" +
                        "// n is not accessible here"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "switch / case / default") {
                    BodyText(
                        "Unlike C, Go's switch does NOT fall through by default. Each case is " +
                        "fully independent — no break statement is needed at the end of a case."
                    )
                    CodeBlock(
                        "day := \"Wed\"\n" +
                        "switch day {\n" +
                        "case \"Mon\":\n" +
                        "    fmt.Println(\"Start of week\")\n" +
                        "case \"Fri\":\n" +
                        "    fmt.Println(\"End of week\")\n" +
                        "case \"Wed\":\n" +
                        "    fmt.Println(\"Midweek\")   // prints this\n" +
                        "default:\n" +
                        "    fmt.Println(\"Other day\")\n" +
                        "}"
                    )
                    BodyText("Multiple values can share a single case by separating them with commas:")
                    CodeBlock(
                        "switch day {\n" +
                        "case \"Sat\", \"Sun\":\n" +
                        "    fmt.Println(\"Weekend\")\n" +
                        "default:\n" +
                        "    fmt.Println(\"Weekday\")\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "switch as an if-else Chain") {
                    BodyText(
                        "A switch with no expression automatically matches true. Each case can " +
                        "then be any boolean condition. This is idiomatic Go for complex " +
                        "if/else if chains — it is cleaner than a long ladder of if statements."
                    )
                    CodeBlock(
                        "i := 12\n" +
                        "switch {\n" +
                        "case i < 0:\n" +
                        "    fmt.Println(\"negative\")\n" +
                        "case i == 0:\n" +
                        "    fmt.Println(\"zero\")\n" +
                        "case i > 5:\n" +
                        "    fmt.Println(\"greater than 5\")   // prints this\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "fallthrough") {
                    BodyText(
                        "To explicitly run the next case's body, use the fallthrough keyword as " +
                        "the last statement in a case. It transfers control to the next case body " +
                        "unconditionally — it does NOT evaluate the next case's condition."
                    )
                    CodeBlock(
                        "n := 2\n" +
                        "switch n {\n" +
                        "case 1:\n" +
                        "    fmt.Println(\"one\")\n" +
                        "case 2:\n" +
                        "    fmt.Println(\"two\")\n" +
                        "    fallthrough           // also runs the case 3 body\n" +
                        "case 3:\n" +
                        "    fmt.Println(\"three\")  // this runs too\n" +
                        "case 4:\n" +
                        "    fmt.Println(\"four\")   // NOT reached\n" +
                        "}\n" +
                        "// Output: \"two\" then \"three\""
                    )
                    BodyText(
                        "fallthrough is rarely needed and can make code harder to follow. The " +
                        "default no-fall-through behaviour is one of Go's deliberate improvements " +
                        "over C-style switch statements."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
