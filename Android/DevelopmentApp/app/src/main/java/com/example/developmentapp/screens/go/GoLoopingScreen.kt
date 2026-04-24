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
fun GoLoopingScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Go — Looping",
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
                SectionCard(title = "The for Loop") {
                    BodyText(
                        "Go has exactly one looping keyword: for. There is no while loop and no " +
                        "do-while loop — for handles all three cases."
                    )
                    BodyText(
                        "Full (three-part) form: for init; condition; post { body }. " +
                        "The init statement runs once before the first iteration. The condition is " +
                        "evaluated before each iteration — if false, the loop exits. The post " +
                        "statement runs after each iteration body."
                    )
                    CodeBlock(
                        "for i := 0; i < 5; i++ {\n" +
                        "    fmt.Println(i)   // prints 0, 1, 2, 3, 4\n" +
                        "}\n" +
                        "// i is not accessible here — declared inside the for init"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Omitting Parts of the for Loop") {
                    BodyText("Any of the three parts can be omitted.")
                    CodeBlock(
                        "// Omit init — use a variable declared before the loop\n" +
                        "i := 0\n" +
                        "for ; i < 5; i++ {\n" +
                        "    fmt.Println(i)\n" +
                        "}\n\n" +
                        "// Omit init AND semicolons — works like a while loop\n" +
                        "i = 0\n" +
                        "for i < 5 {\n" +
                        "    fmt.Println(i)\n" +
                        "    i++\n" +
                        "}\n\n" +
                        "// Omit post — increment manually inside body\n" +
                        "for i := 0; i < 5; {\n" +
                        "    fmt.Println(i)\n" +
                        "    i++\n" +
                        "}"
                    )
                    BodyText(
                        "The condition-only form (for i < 5) is Go's equivalent of a while loop. " +
                        "It is the most common way to loop until a condition is met when you do not " +
                        "need the init or post parts."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Infinite Loop") {
                    BodyText(
                        "Omit all three parts to get an infinite loop. The loop runs forever " +
                        "until a break or return statement exits it."
                    )
                    CodeBlock(
                        "for {\n" +
                        "    input := readInput()\n" +
                        "    if input == \"quit\" {\n" +
                        "        break\n" +
                        "    }\n" +
                        "    process(input)\n" +
                        "}"
                    )
                    BodyText(
                        "Infinite loops are common in Go for server loops, background workers, " +
                        "and event processing loops — anywhere you need to keep running until an " +
                        "explicit exit condition is triggered."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "break and continue") {
                    BodyText(
                        "break exits the innermost loop immediately — no further iterations run."
                    )
                    BodyText(
                        "continue skips the rest of the current iteration body and jumps to the " +
                        "post statement (or to the next condition check if there is no post)."
                    )
                    CodeBlock(
                        "for i := 0; i < 10; i++ {\n" +
                        "    if i == 3 {\n" +
                        "        continue        // skip printing 3\n" +
                        "    }\n" +
                        "    if i == 7 {\n" +
                        "        break           // stop the loop at 7\n" +
                        "    }\n" +
                        "    fmt.Println(i)      // prints 0 1 2 4 5 6\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
