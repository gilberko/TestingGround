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
fun GoMethodsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Go — Methods",
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

            item {
                SectionCard(title = "What Is a Method in Go") {
                    BodyText("Go has no classes and no this keyword. Instead, a method is an ordinary function with an extra receiver parameter that appears before the function name.")
                    CodeBlock("""
                        type A struct { x int }

                        // receiver is named 'a', type is A
                        func (a A) Describe() string {
                            return fmt.Sprintf("x = %d", a.x)
                        }

                        func main() {
                            obj := A{x: 5}
                            fmt.Println(obj.Describe())  // "x = 5"
                        }
                    """.trimIndent())
                    BodyText("The method is defined outside the struct body (Go has no class body), but it is associated with the type and called with the familiar dot notation.")
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Value Receiver vs Pointer Receiver") {
                    BodyText("A value receiver receives a copy of the struct. Changes inside the method do not affect the original. A pointer receiver receives a pointer and can modify the original.")
                    CodeBlock("""
                        type A struct { x int }

                        // value receiver — works on a copy
                        func (a A) GetX() int {
                            return a.x
                        }

                        // pointer receiver — can modify the original
                        func (a *A) SetX(val int) {
                            a.x = val
                        }

                        func main() {
                            obj := A{x: 1}
                            obj.SetX(99)
                            fmt.Println(obj.GetX())  // 99
                        }
                    """.trimIndent())
                    BodyText("Rule of thumb: use a pointer receiver when the method needs to mutate the struct, or when the struct is large and you want to avoid copying. Be consistent — if any method on a type uses a pointer receiver, prefer pointer receivers for all of them.")
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Additional Parameters") {
                    BodyText("Methods are just functions with a receiver — they can take any number of extra parameters and return multiple values, exactly like regular functions.")
                    CodeBlock("""
                        type A struct { x int }

                        func (a A) Add(other A) A {
                            return A{x: a.x + other.x}
                        }

                        func (a *A) Scale(factor int) {
                            a.x *= factor
                        }

                        func main() {
                            p := A{x: 3}
                            q := A{x: 4}
                            r := p.Add(q)    // r.x == 7
                            r.Scale(2)
                            fmt.Println(r.x) // 14
                        }
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The Six Call Variants") {
                    BodyText("Given a value a of type A and a pointer pA of type *A, with one value receiver method and one pointer receiver method:")
                    CodeBlock("""
                        type A struct { x int }

                        func (a A)  mByVal() { fmt.Println("val, x =", a.x) }
                        func (a *A) mByRef() { fmt.Println("ref, x =", a.x) }

                        a  := A{x: 1}
                        pA := &A{x: 2}
                    """.trimIndent())
                    BodyText("All six combinations below are valid:")
                    CodeBlock("""
                        a.mByVal()
                        // Value receiver, direct match. Copies a. Fine.

                        a.mByRef()
                        // Pointer receiver, but a is a variable (addressable).
                        // Go automatically takes the address: equivalent to (&a).mByRef().

                        pA.mByVal()
                        // Value receiver, but pA is a pointer.
                        // Go automatically dereferences: equivalent to (*pA).mByVal(). Copies *pA.

                        pA.mByRef()
                        // Pointer receiver, direct match. No conversion needed.

                        (*pA).mByVal()
                        // Explicit dereference, then value receiver. Same result as pA.mByVal().

                        (*pA).mByRef()
                        // Explicit dereference yields an addressable value.
                        // Go takes its address automatically: equivalent to (&(*pA)).mByRef().
                    """.trimIndent())
                    BodyText("The key rule: Go automatically inserts & (take address) or * (dereference) when the receiver type doesn't match exactly, as long as the expression is addressable. The auto-address case requires the value to be addressable — a plain variable always is, but a map element or function return value is not.")
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "No Arrow Operator") {
                    BodyText("In C and C++ you need -> to call a method or access a field through a pointer, or write the more verbose (*p).field. Go has no -> operator — you always use a plain dot, regardless of whether the left side is a value or a pointer.")
                    CodeBlock("""
                        // C++ style (does not exist in Go):
                        // pA->mByRef()
                        // pA->x

                        // Go — dot works for both values and pointers:
                        pA.mByRef()   // pointer receiver call
                        pA.x          // field access through pointer — Go dereferences automatically
                        a.mByRef()    // Go takes address automatically
                        a.x           // ordinary field access
                    """.trimIndent())
                    BodyText("This keeps the syntax uniform. You never have to decide whether to use . or -> — it is always ..")
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Methods on Any Named Type") {
                    BodyText("Receivers do not have to be structs. You can define methods on any named type declared in the same package — including type aliases for built-in types.")
                    CodeBlock("""
                        type Celsius float64

                        func (c Celsius) ToFahrenheit() float64 {
                            return float64(c)*9/5 + 32
                        }

                        func main() {
                            temp := Celsius(100)
                            fmt.Println(temp.ToFahrenheit())  // 212
                        }
                    """.trimIndent())
                    BodyText("You cannot define methods directly on a built-in type like int or string — you must create a named type first (type MyInt int). The method must be defined in the same package as the type; you cannot add methods to types from other packages.")
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
