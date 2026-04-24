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
fun GoAnonFuncsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Go — Anonymous Functions",
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
                SectionCard(title = "Function Literals") {
                    BodyText("In Go, a function literal (also called an anonymous function or unnamed function) is defined with the func keyword but without a name. It is an expression — you can assign it to a variable, pass it as an argument, or return it from another function. This is Go's equivalent of a lambda.")
                    CodeBlock("""
add := func(a, b int) int {
    return a + b
}
result := add(3, 4)  // 7

// Immediately invoked (IIFE)
func(msg string) {
    fmt.Println(msg)
}("hello")
                    """.trimIndent())
                    BodyText("The variable add holds the function value. Calling add(3, 4) works exactly like calling a named function. The IIFE form defines and calls the function in one expression — useful for scoping temporary logic.")
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Closures — Referencing Outer Variables") {
                    BodyText("A function literal can reference variables from the scope where it was defined. This makes it a closure. Crucially, it captures the variable itself — not a copy of the value at the time of creation. When the closure is called, it reads the variable's current value.")
                    CodeBlock("""
x := 10
f := func() {
    fmt.Println(x)
}
x = 20
f()  // prints 20, not 10
                    """.trimIndent())
                    BodyText("The closure and the surrounding code share the same variable. The closure can also write to x, and the change is visible outside. Both sides are looking at the same memory location, not independent copies.")
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The For-Loop Closure Bug (pre-Go 1.22)") {
                    BodyText("Before Go 1.22, a single loop variable was reused across all iterations. Any closure created inside the loop captured that one shared variable. By the time the closures ran, the loop had already finished and the variable held its final value — so every closure saw the same thing.")
                    CodeBlock("""
// BAD — pre-Go 1.22 behaviour
funcs := make([]func(), 3)
for i := 0; i < 3; i++ {
    funcs[i] = func() {
        fmt.Println(i)  // i is shared across all iterations!
    }
}
for _, f := range funcs {
    f()  // prints: 3, 3, 3
}
                    """.trimIndent())
                    BodyText("All three closures reference the same i. After the loop ends, i == 3 (the value that failed the condition i < 3), so all three closures print 3.")
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The i := i Fix") {
                    BodyText("The standard pre-1.22 fix is to shadow the loop variable at the top of the loop body. The line i := i declares a brand-new variable local to that iteration, initialised with the current value of the outer i. The closure then captures this inner i, which never changes.")
                    CodeBlock("""
funcs := make([]func(), 3)
for i := 0; i < 3; i++ {
    i := i  // new i, scoped to this iteration only
    funcs[i] = func() {
        fmt.Println(i)
    }
}
for _, f := range funcs {
    f()  // prints: 0, 1, 2
}
                    """.trimIndent())
                    BodyText("Each iteration creates its own independent i. The outer loop variable still increments normally (the post-step i++ acts on the outer i), but inside the loop body the inner i is in scope and captures the iteration's value.")
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Fixed in Go 1.22") {
                    BodyText("Go 1.22 (released February 2024) changed the loop semantics: each for loop iteration now creates its own copy of the loop variable. The bug no longer exists when your go.mod declares go 1.22 or later.")
                    CodeBlock("""
// go.mod: go 1.22
funcs := make([]func(), 3)
for i := 0; i < 3; i++ {
    funcs[i] = func() {
        fmt.Println(i)  // each iteration has its own i
    }
}
for _, f := range funcs {
    f()  // prints: 0, 1, 2  (correct without any workaround)
}
                    """.trimIndent())
                    BodyText("This fix applies to all for loop variables, including range variables. The i := i trick is still valid and harmless in 1.22+ code — it just creates a redundant shadow. If your project targets an older Go version, the old behaviour still applies and i := i remains the safe workaround.")
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
