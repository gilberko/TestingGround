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
fun GoMapsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Go — Maps",
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
                SectionCard(title = "What Is a Map") {
                    BodyText("A map is an unordered collection of key-value pairs — like a dictionary or hash map in other languages. Each key maps to exactly one value.")
                    BodyText("Key type must be comparable (supports ==): booleans, numbers, strings, pointers, arrays, structs whose fields are all comparable.")
                    BodyText("The following cannot be keys: slices, maps, and functions — these types are not comparable in Go.")
                    BodyText("Value type can be anything, including another map, a slice, or a struct.")
                    CodeBlock("""
var scores map[string]int    // keys: string, values: int
var nested map[string][]int  // values can be slices too
                    """.trimIndent())
                }
            }

            item {
                SectionCard(title = "Declaring and Creating Maps") {
                    BodyText("There are four common ways to create a map:")
                    CodeBlock("""
// 1. var declaration — nil map
//    Reading returns zero values; writing panics
var m map[string]int

// 2. Empty map literal
m := map[string]int{}

// 3. make — most common
m := make(map[string]int)

// 4. make with capacity hint (optimisation, not a hard limit)
m := make(map[string]int, 100)

// 5. Map literal with initial values
m := map[string]int{
    "alice": 90,
    "bob":   85,
}
                    """.trimIndent())
                    BodyText("A nil map is not the same as an empty map. You can read from a nil map (it returns zero values), but writing to a nil map causes a runtime panic. Always initialise with make or a literal before inserting.")
                    BodyText("Literal {} vs make — is there a difference?")
                    BodyText("For basic empty creation they are functionally identical. Both call the same runtime function under the hood and produce a fully initialised, empty map ready for immediate use. The compiler treats them the same way.")
                    CodeBlock("""
m1 := map[string]int{}        // empty literal
m2 := make(map[string]int)    // make, no hint

// m1 and m2 are equivalent — both are empty, initialised maps
m1["a"] = 1   // fine
m2["a"] = 1   // fine
                    """.trimIndent())
                    BodyText("The practical differences are:\n\n• Capacity hint — only make accepts a second argument: make(map[string]int, 100). This pre-allocates hash buckets for roughly 100 entries, reducing rehashing if you know the approximate size up front. The literal syntax has no equivalent.\n\n• Inline values — only the literal syntax lets you provide initial key-value pairs at creation time: map[string]int{\"a\": 1, \"b\": 2}. make always creates an empty map.\n\n• Style — make is preferred when you are creating an empty map programmatically (especially with a hint). A literal is preferred when you have known starting values or want an explicit empty map inline.")
                }
            }

            item {
                SectionCard(title = "Inserting, Updating, and Deleting") {
                    BodyText("Insert and update use the same syntax — if the key already exists, the value is overwritten:")
                    CodeBlock("""
m := make(map[string]int)

m["alice"] = 90     // insert
m["alice"] = 95     // update (overwrites 90)

delete(m, "alice")  // remove key "alice"
delete(m, "nobody") // no-op if key does not exist — safe
                    """.trimIndent())
                }
            }

            item {
                SectionCard(title = "Looking Up Values") {
                    BodyText("Single-value lookup returns the zero value for the value type when the key is absent. You cannot tell the difference between a missing key and a key whose stored value happens to be zero:")
                    CodeBlock("""
m := map[string]int{"alice": 0}

val := m["alice"]   // 0 — but is it stored or missing?
val  = m["nobody"]  // also 0 — indistinguishable!
                    """.trimIndent())
                    BodyText("Always prefer the two-value form when you need to know whether a key exists:")
                    CodeBlock("""
val, ok := m["alice"]
if ok {
    fmt.Println("found:", val)
} else {
    fmt.Println("key not present")
}
                    """.trimIndent())
                    BodyText("ok is a bool. true means the key was present; false means it was not. This pattern is idiomatic Go and mirrors the channel receive syntax (val, ok := <-ch).")
                }
            }

            item {
                SectionCard(title = "Length") {
                    BodyText("len(m) returns the number of key-value pairs currently in the map. It works the same as len() on slices and strings.")
                    CodeBlock("""
m := map[string]int{"a": 1, "b": 2, "c": 3}
fmt.Println(len(m)) // 3

delete(m, "a")
fmt.Println(len(m)) // 2
                    """.trimIndent())
                    BodyText("There is no cap() for maps — maps grow automatically and there is no fixed capacity to query.")
                }
            }

            item {
                SectionCard(title = "Iterating with range") {
                    BodyText("Use a range loop to iterate over all key-value pairs. The order is not guaranteed and is deliberately randomised on every run — do not rely on it.")
                    CodeBlock("""
m := map[string]int{"alice": 90, "bob": 85, "carol": 92}

// All keys and values
for k, v := range m {
    fmt.Println(k, v)
}

// Keys only
for k := range m {
    fmt.Println(k)
}

// Values only
for _, v := range m {
    fmt.Println(v)
}
                    """.trimIndent())
                    BodyText("If you need sorted output, collect the keys into a slice and sort it first:")
                    CodeBlock("""
import "sort"

keys := make([]string, 0, len(m))
for k := range m { keys = append(keys, k) }
sort.Strings(keys)
for _, k := range keys {
    fmt.Println(k, m[k])
}
                    """.trimIndent())
                }
            }

            item {
                SectionCard(title = "Thread Safety and Internals") {
                    BodyText("Built-in maps are NOT thread-safe. Concurrent reads from multiple goroutines are fine. But any concurrent access where at least one goroutine is writing causes a runtime panic (detectable with go run -race).")
                    CodeBlock("""
// UNSAFE — concurrent write + read
go func() { m["key"] = 1 }()
go func() { _ = m["key"] }()   // data race!
                    """.trimIndent())
                    BodyText("For concurrent access, either protect the map with a sync.Mutex, or use sync.Map (covered in the Goroutines and Sync screen).")
                    CodeBlock("""
var mu sync.Mutex
mu.Lock()
m["key"] = 1
mu.Unlock()
                    """.trimIndent())
                    BodyText("Internally, Go's map is a hash map using open addressing with buckets. It is unordered. Go deliberately randomises iteration order to prevent code from accidentally depending on hash order, which could change across Go versions.")
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
