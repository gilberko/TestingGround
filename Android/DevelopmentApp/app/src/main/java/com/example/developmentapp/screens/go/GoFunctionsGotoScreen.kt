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
fun GoFunctionsGotoScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Go — Functions and Goto 101",
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
                SectionCard(title = "Functions") {
                    BodyText(
                        "Functions are declared with the func keyword. Parameters are written as " +
                        "name type pairs separated by commas. The return type follows the parameter " +
                        "list. A function with no return type returns nothing (equivalent to void in C)."
                    )
                    CodeBlock(
                        "func greet() {\n" +
                        "    fmt.Println(\"Hello\")    // no return value\n" +
                        "}\n\n" +
                        "func add(a int, b int) int {\n" +
                        "    return a + b\n" +
                        "}\n\n" +
                        "// Parameters of the same type can share a single type annotation\n" +
                        "func multiply(a, b int) int {\n" +
                        "    return a * b\n" +
                        "}"
                    )
                    BodyText("Calling functions:")
                    CodeBlock(
                        "greet()              // no parameters, no return value\n" +
                        "result := add(3, 4)  // pass parameters, capture the return value"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Return Values") {
                    BodyText("A single return value:")
                    CodeBlock(
                        "func double(n int) int {\n" +
                        "    return n * 2\n" +
                        "}\n" +
                        "x := double(5)   // x == 10"
                    )
                    BodyText(
                        "Go functions can return more than one value. Multiple return values are " +
                        "wrapped in parentheses. A common Go idiom is to return a result together " +
                        "with an error value."
                    )
                    CodeBlock(
                        "func divide(a, b float64) (float64, error) {\n" +
                        "    if b == 0 {\n" +
                        "        return 0, fmt.Errorf(\"division by zero\")\n" +
                        "    }\n" +
                        "    return a / b, nil\n" +
                        "}\n\n" +
                        "result, err := divide(10, 3)\n" +
                        "if err != nil {\n" +
                        "    fmt.Println(\"Error:\", err)\n" +
                        "} else {\n" +
                        "    fmt.Println(result)\n" +
                        "}"
                    )
                    BodyText("Returning multiple values with a single return statement:")
                    CodeBlock(
                        "func swap(a, b int) (int, int) {\n" +
                        "    return b, a\n" +
                        "}\n" +
                        "x, y := swap(1, 2)   // x == 2, y == 1"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Named Return Values") {
                    BodyText(
                        "Return values can be given names in the function signature. Named return " +
                        "values act like variables that are pre-declared inside the function body " +
                        "with their zero values. A bare return (naked return) returns whatever " +
                        "those named variables currently hold."
                    )
                    CodeBlock(
                        "func minMax(arr []int) (min, max int) {\n" +
                        "    min, max = arr[0], arr[0]\n" +
                        "    for _, v := range arr {\n" +
                        "        if v < min { min = v }\n" +
                        "        if v > max { max = v }\n" +
                        "    }\n" +
                        "    return   // returns the current values of min and max\n" +
                        "}\n\n" +
                        "lo, hi := minMax([]int{3, 1, 4, 1, 5})"
                    )
                    BodyText(
                        "Named returns are useful in short functions. In longer functions they " +
                        "can hurt readability — use explicit return values instead."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Discarding Return Values with _") {
                    BodyText(
                        "The blank identifier _ can stand in for any return value you do not " +
                        "want to use. Assigning to _ discards the value — no variable is created " +
                        "and no unused-variable compile error is triggered."
                    )
                    CodeBlock(
                        "val, _ := divide(10, 3)   // ignore the error\n" +
                        "_, hi  := minMax(data)    // ignore the minimum, keep the maximum"
                    )
                    BodyText(
                        "Go requires that every declared variable is used. _ is the escape hatch " +
                        "when a function returns more values than you currently need."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "goto and Labels") {
                    BodyText(
                        "A label is a name followed by a colon placed before any statement. " +
                        "goto label unconditionally jumps to that label. Both must be in the " +
                        "same function. You cannot jump forward over a variable declaration " +
                        "or into a block — the compiler will reject it."
                    )
                    CodeBlock(
                        "func countUp() {\n" +
                        "    i := 0\n" +
                        "loop:\n" +
                        "    if i < 3 {\n" +
                        "        fmt.Println(i)\n" +
                        "        i++\n" +
                        "        goto loop   // jumps back to the label\n" +
                        "    }\n" +
                        "    fmt.Println(\"done\")\n" +
                        "}"
                    )
                    BodyText(
                        "goto is rarely used in Go. The more idiomatic alternative for breaking " +
                        "out of nested loops is a labeled break — you attach a label to the outer " +
                        "loop and use break label to exit it directly."
                    )
                    CodeBlock(
                        "outer:\n" +
                        "    for i := 0; i < 3; i++ {\n" +
                        "        for j := 0; j < 3; j++ {\n" +
                        "            if i == 1 && j == 1 {\n" +
                        "                break outer   // exits the outer loop\n" +
                        "            }\n" +
                        "            fmt.Println(i, j)\n" +
                        "        }\n" +
                        "    }"
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
