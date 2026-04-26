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
fun GoNewMakeScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Go — new and make",
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
                SectionCard(title = "new — Allocate and Zero Any Type") {
                    BodyText("new(T) is a built-in function that works for any type T. It does exactly three things:")
                    BodyText("1. Allocates memory for a new T on the heap.\n2. Zero-initialises every byte of that memory (0 for numbers, false for bool, \"\" for string, nil for pointers/maps/slices/channels).\n3. Returns *T — a pointer to the zeroed value.")
                    CodeBlock("""
// new with a plain struct
type Point struct { X, Y int }

p := new(Point)       // p is *Point
fmt.Println(*p)       // {0 0}  — zero-initialised

// new with a primitive
n := new(int)         // n is *int
fmt.Println(*n)       // 0
*n = 42
fmt.Println(*n)       // 42
                    """.trimIndent())
                    BodyText("new never calls any constructor or initialiser — it always produces a zero value. There is no delete or free; the garbage collector reclaims the memory when no references remain.")
                }
            }

            item {
                SectionCard(title = "When new Falls Short — Types with Internal State") {
                    BodyText("For a plain struct, a zero value is often immediately usable. But some Go types hide internal bookkeeping that must be properly set up before the value can be used. The three examples are map, slice, and channel.")
                    BodyText("A map is not just a struct with a few fields — the runtime must create hash buckets, set up a header, and initialise growth-related counters. A zeroed map is a nil map. You can read from it (you get zero values back), but writing to it panics:")
                    CodeBlock("""
pm := new(map[string]int)   // *map[string]int, the map itself is nil
(*pm)["key"] = 1            // PANIC: assignment to entry in nil map
                    """.trimIndent())
                    BodyText("The same applies to channels: new(chan int) gives a pointer to a nil channel — sends and receives on a nil channel block forever. And for slices: new([]int) gives a pointer to a nil slice, which while technically usable, is an awkward way to create one.")
                    BodyText("For these three types, use make instead.")
                }
            }

            item {
                SectionCard(title = "make — Initialise map, slice, or channel") {
                    BodyText("make is a built-in that works exclusively for three types: map, slice, and channel. Unlike new, make initialises the internal runtime state and returns the value itself — not a pointer.")
                    CodeBlock("""
// map
m := make(map[string]int)       // ready-to-use empty map
m := make(map[string]int, 100)  // pre-allocate ~100 buckets

// slice  (len, cap)
s := make([]int, 5)             // len=5, cap=5, all zeros
s := make([]int, 0, 100)        // len=0, cap=100, pre-allocated

// channel
ch  := make(chan int)            // unbuffered
bch := make(chan int, 10)        // buffered, capacity 10
                    """.trimIndent())
                    BodyText("make returns the value itself (map, []T, chan T), not a pointer. You rarely need a pointer to a map or channel because those types are already reference types internally — passing them by value copies the reference, not the data.")
                    BodyText("Summary of differences:\n\nnew(T)    → works for any T, returns *T, always zeroed\nmake(T)   → only map/slice/chan, returns T, initialises internals")
                }
            }

            item {
                SectionCard(title = "Can You Use make for Your Own Types?") {
                    BodyText("No. make is a language built-in that is hardwired to exactly three types: map, slice, and channel. You cannot extend it, overload it, or apply it to your own struct. Attempting to do so is a compile error:")
                    CodeBlock("""
type MyQueue struct { items []int }

q := make(MyQueue)   // compile error: cannot make type MyQueue
                    """.trimIndent())
                    BodyText("The idiomatic Go alternative is a constructor function. By convention these are named New<TypeName> and return a pointer to a fully initialised value:")
                    CodeBlock("""
type MyQueue struct {
    items []int
    mu    sync.Mutex
}

func NewMyQueue(initialCap int) *MyQueue {
    return &MyQueue{
        items: make([]int, 0, initialCap),
    }
}

// Usage
q := NewMyQueue(64)
                    """.trimIndent())
                    BodyText("This is exactly how the standard library works: ring.New(), heap constructors, sync.NewCond(), etc. The constructor function is the Go equivalent of a parameterised constructor in other languages.")
                    BodyText("If a zero value of your struct is already usable without any initialisation, you do not even need a constructor — callers can just write var q MyQueue or q := MyQueue{} and use it immediately. Designing types so their zero value is useful is considered good Go style (sync.Mutex is a famous example — the zero value is an unlocked mutex).")
                }
            }

            item {
                SectionCard(title = "Quick Reference") {
                    CodeBlock("""
// new — any type, returns pointer to zero value
p := new(int)             // *int, value is 0
p := new(MyStruct)        // *MyStruct, all fields zeroed

// make — map / slice / channel only, returns the value
m  := make(map[K]V)       // map[K]V, internal state ready
m  := make(map[K]V, hint) // same + capacity pre-allocated
s  := make([]T, len)      // []T of length len
s  := make([]T, len, cap) // []T, separate len and cap
ch := make(chan T)         // unbuffered channel
ch := make(chan T, n)      // buffered channel, capacity n

// Your own types — constructor function
obj := NewMyType(args)    // returns *MyType (by convention)
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
