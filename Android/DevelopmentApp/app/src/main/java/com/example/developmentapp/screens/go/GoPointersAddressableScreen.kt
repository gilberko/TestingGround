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
fun GoPointersAddressableScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Go — Pointers and Addressable Objects",
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
                SectionCard(title = "Declaring Pointer Types") {
                    BodyText("A pointer holds the memory address of another value. In Go the pointer type is written with a leading * — *int is a pointer to int, *A is a pointer to struct A.")
                    CodeBlock("""
                        var p   *int  // pointer to int;  zero value is nil
                        var pA  *A    // pointer to A;    zero value is nil

                        // nil means "points to nothing" — safe to check
                        if p == nil {
                            // p has not been assigned yet
                        }
                    """.trimIndent())
                    BodyText("Unlike C, an uninitialised pointer is always nil, never a garbage address. Dereferencing nil panics, which is a clear runtime error rather than silent corruption.")
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Allocating with new") {
                    BodyText("new(T) allocates a new T on the heap, zero-initialises it, and returns *T.")
                    CodeBlock("""
                        type A struct { x int }

                        p := new(A)   // p is *A; *p == A{x: 0}
                        fmt.Println(p.x)  // 0  — zero-initialised
                    """.trimIndent())
                    BodyText("There is no free, delete, or dispose call. Go's garbage collector automatically reclaims the memory once no pointers to it remain. You allocate; the runtime cleans up.")
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Zero Values and Initializing Fields") {
                    BodyText("new(T) zero-initialises every field: int fields become 0, string fields become \"\", bool fields become false, pointer fields become nil. There is no way to pass initialiser arguments to new — to set fields, assign them afterwards:")
                    CodeBlock("""
                        p := new(A)
                        p.x = 10
                        fmt.Println(p.x)  // 10
                    """.trimIndent())
                    BodyText("The idiomatic alternative is a composite literal pointer, which lets you initialise fields inline (see next section).")
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Composite Literal as Pointer — &A{x: 10}") {
                    BodyText("You can take the address of a composite literal directly. This looks as if you are taking the address of a temporary object, which would be dangerous in C/C++. In Go it is perfectly valid and idiomatic.")
                    CodeBlock("""
                        p := &A{x: 10}    // p is *A; p.x == 10
                        fmt.Println(p.x)  // 10
                    """.trimIndent())
                    BodyText("Go treats composite literals as addressable. The compiler heap-allocates the literal so its lifetime extends beyond the expression. The two forms below are equivalent:")
                    CodeBlock("""
                        // form 1 — composite literal pointer (idiomatic)
                        p1 := &A{x: 10}

                        // form 2 — declare then take address (same result)
                        a  := A{x: 10}
                        p2 := &a

                        fmt.Println(p1.x, p2.x)  // 10 10
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "What Is (and Isn't) Addressable") {
                    BodyText("The & operator can only be applied to addressable expressions. The Go spec defines these as addressable:\n\n• Variables: &x, &myVar\n• Struct fields: &a.field\n• Array / slice elements: &arr[0], &s[i]\n• Composite literals: &A{}, &[]int{1,2,3}[0]\n• Dereferenced pointers: &(*p)")
                    CodeBlock("""
                        x := 5
                        p := &x         // OK — variable is addressable
                        _ = &(*p)       // OK — deref of pointer is addressable

                        a := A{x: 7}
                        _ = &a.x        // OK — struct field is addressable

                        s := []int{1, 2, 3}
                        _ = &s[0]       // OK — slice element is addressable
                    """.trimIndent())
                    BodyText("These are NOT addressable — the compiler will reject them:")
                    CodeBlock("""
                        m := map[string]int{"a": 1}
                        _ = &m["a"]     // compile error: cannot take address of map element
                        // Why: maps relocate their internal storage on growth;
                        // a pointer to a map entry would dangle after rehashing.

                        _ = &42         // compile error: cannot take address of 42

                        func getVal() int { return 99 }
                        _ = &getVal()   // compile error: cannot take address of getVal()
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Escape Analysis — Returning a Pointer to a Local Variable") {
                    BodyText("In C and C++, returning the address of a local variable is undefined behaviour — the stack frame is destroyed when the function returns, leaving a dangling pointer.")
                    CodeBlock("""
                        // C — DANGEROUS (undefined behaviour)
                        // A* newA() {
                        //     A a = {10};
                        //     return &a;   // stack frame gone on return!
                        // }
                    """.trimIndent())
                    BodyText("In Go this is safe. The compiler performs escape analysis: if it detects that a local variable's address outlives the function, it automatically allocates that variable on the heap instead of the stack. You write the same code as if it were a stack variable — Go figures out the rest.")
                    CodeBlock("""
                        func newA() *A {
                            a := A{x: 42}   // declared like a stack variable
                            return &a        // safe — compiler moves a to the heap
                        }

                        func main() {
                            p := newA()
                            fmt.Println(p.x)  // 42 — valid, a lives on the heap
                        }
                    """.trimIndent())
                    BodyText("To see escape analysis decisions run: go build -gcflags=\"-m\" ./...\nThe compiler will print lines like: \"a escapes to heap\" wherever it promotes a variable.")
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
