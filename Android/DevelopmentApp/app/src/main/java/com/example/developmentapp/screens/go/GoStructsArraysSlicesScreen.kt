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
fun GoStructsArraysSlicesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Go — Structs, Arrays and Slices",
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
                SectionCard(title = "Structs") {
                    BodyText("A struct groups related fields under one named type. Fields are accessed with dot notation. Structs are value types — assigning one struct to another copies all fields.")
                    CodeBlock("""
type Point struct {
    X int
    Y int
}

// Positional initialisation — order must match declaration
p1 := Point{10, 20}

// Named fields — order does not matter
// Any omitted field gets its zero value
p2 := Point{Y: 5}        // X is 0
p3 := Point{Y: 99, X: 3} // order is reversed, still valid

fmt.Println(p1.X, p2.Y)  // 10  5
                    """.trimIndent())
                    BodyText("Named-field initialisation is preferred. It is explicit, order-independent, and safe if the struct gains new fields later — positional initialisation would break at compile time if the field count changes.")
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Arrays — Fixed Size") {
                    BodyText("An array in Go has a fixed size that is part of its type — [3]int and [4]int are two distinct types. Arrays are value types; assigning or passing one copies the entire array.")
                    CodeBlock("""
var a [3]int             // [0 0 0] — zero initialised
b := [3]int{1, 2, 3}    // explicit size with values
c := [...]int{1, 2, 3}  // compiler infers size from values
d := [5]int{10, 20}     // partial init — rest are zero: [10 20 0 0 0]

fmt.Println(len(b))  // 3
fmt.Println(b[0])    // 1
fmt.Println(b[2])    // 3
                    """.trimIndent())
                    BodyText("len() returns the number of elements. The size is fixed at compile time and cannot change at runtime.")
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Iterating Over Arrays — for and range") {
                    BodyText("Use a classic for loop with an index, or use range which yields both the index and a copy of the value at each position.")
                    CodeBlock("""
nums := [4]int{10, 20, 30, 40}

// Classic index loop
for i := 0; i < len(nums); i++ {
    fmt.Println(nums[i])
}

// range — i is the index, v is a copy of the value
for i, v := range nums {
    fmt.Println(i, v)
}

// Discard the index with _
for _, v := range nums {
    fmt.Println(v)
}

// Index only (value is implicitly discarded)
for i := range nums {
    fmt.Println(i)
}
                    """.trimIndent())
                    BodyText("Because range yields a copy of the value, modifying v inside the loop does not change the array. To modify elements, use the index: nums[i] = newValue.")
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Slices — Dynamic Arrays") {
                    BodyText("A slice is a three-field descriptor: a pointer to an underlying array, a length (len), and a capacity (cap). It behaves like a dynamic array, similar to std::vector in C++. Unlike arrays, slices are reference-like — multiple slices can point into the same backing memory.")
                    BodyText("Three common ways to create a slice:")
                    CodeBlock("""
// 1. Slice literal — creates its own backing array
s1 := []int{1, 2, 3}

// 2. make([]T, len) or make([]T, len, cap)
s2 := make([]int, 3)      // [0 0 0], cap = 3
s3 := make([]int, 3, 10)  // [0 0 0], cap = 10

// 3. Slice of an existing array (see next section)

fmt.Println(len(s1), cap(s1))  // 3  3
fmt.Println(len(s3), cap(s3))  // 3  10
                    """.trimIndent())
                    BodyText("len is the number of elements currently accessible. cap is how many elements fit in the backing array before a reallocation is needed. range works on slices exactly as it does on arrays.")
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Slices as Views Into Arrays") {
                    BodyText("The syntax arr[low:high] creates a slice pointing into arr, covering indices low through high-1. The capacity extends from low to the end of the array. Because the slice shares memory with the array, changes through the slice affect the array and vice versa.")
                    CodeBlock("""
arr := [4]int{1, 2, 3, 4}
s := arr[1:3]  // s = [2 3], len=2, cap=3
               // cap = 4 - 1 = 3 (from index 1 to end of arr)
                    """.trimIndent())
                    BodyText("Mutation: if you change an element through the slice, the underlying array also changes.")
                    CodeBlock("""
s[0] = 5  // s = [5 3]
          // arr = [1 5 3 4]
                    """.trimIndent())
                    BodyText("Append within capacity: since len=2 and cap=3 there is one free slot. The new element lands at arr[3], replacing 4.")
                    CodeBlock("""
arr := [4]int{1, 2, 3, 4}
s := arr[1:3]        // s = [2 3], len=2, cap=3

s = append(s, 100)   // 100 goes into arr[3]
                     // arr = [1 2 3 100]
                     // s = [2 3 100], len=3, cap=3
                    """.trimIndent())
                    BodyText("Append beyond capacity: once len == cap, Go allocates a new, larger backing array, copies the data, and the original array is no longer affected.")
                    CodeBlock("""
s = append(s, 200)   // cap was full (len=3, cap=3)
                     // Go allocates new backing array
                     // arr stays [1 2 3 100], unchanged
                     // s now points to new memory
                    """.trimIndent())
                    BodyText("You can limit the initial capacity using three-index notation arr[low:high:max], where cap = max - low. This forces a new allocation on the very first append.")
                    CodeBlock("""
arr := [4]int{1, 2, 3, 4}
s2 := arr[1:3:3]  // len=2, cap=2 (3-1=2)
                  // first append immediately allocates new memory
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Passing Arrays, Slices and Structs to Functions") {
                    BodyText("Go passes everything by value. The key difference lies in what 'the value' contains:\n\n• Array — the entire array is copied. Modifications inside the function do not affect the caller. Pass a pointer (*[4]int) to modify in place.\n\n• Slice — the three-field header (pointer, len, cap) is copied, but the pointer still references the same backing array. Modifying elements inside the function does affect the caller. However, append inside the function updates only the function's local header — the caller's len and cap are unchanged.\n\n• Struct — copied like an array. Pass a pointer (*MyStruct) to mutate the caller's original.")
                    CodeBlock("""
func doubleFirst(s []int) {
    s[0] *= 2          // affects caller's backing array
    s = append(s, 99)  // only changes local header — caller unchanged
}

func move(p *Point) {
    p.X += 1           // modifies caller's struct via pointer
}

func sumArray(a [3]int) int {
    return a[0] + a[1] + a[2]  // a is a full copy
}
                    """.trimIndent())
                    BodyText("Returning a slice or struct is straightforward — just return the value. For large structs, returning a pointer avoids copying. Go's escape analysis keeps the object alive even after the function returns.")
                    CodeBlock("""
func makeSlice() []int {
    return []int{1, 2, 3}
}

func makePoint() *Point {
    p := Point{X: 1, Y: 2}
    return &p  // safe — escape analysis moves p to the heap
}
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
