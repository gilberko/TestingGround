package com.example.developmentapp.screens.rust

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
fun RustTuplesArraysSlicesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Rust — Tuples, Arrays and Slices",
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
                SectionCard(title = "Tuples") {
                    BodyText(
                        "A tuple groups a fixed number of values that can have different types. " +
                        "The type is written as (T1, T2, ...). Instances are created with " +
                        "parentheses and commas. Elements are accessed by zero-based index using " +
                        "dot notation: .0, .1, and so on."
                    )
                    CodeBlock(
                        "let point = (1.0_f64, 2.5_f64);\n" +
                        "println!(\"{} {}\", point.0, point.1);   // 1 2.5\n\n" +
                        "let mixed = (42, \"hello\", true);\n" +
                        "println!(\"{} {} {}\", mixed.0, mixed.1, mixed.2);"
                    )
                    BodyText(
                        "Tuples are useful for returning multiple values from a function " +
                        "without defining a dedicated struct. The unit type () is an empty " +
                        "tuple with no fields — it is what Rust functions return when they " +
                        "have no declared return type."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Destructuring a Tuple") {
                    BodyText(
                        "A tuple can be decomposed (destructured) into individual variables in " +
                        "a single let binding. Use _ to ignore elements you don't need."
                    )
                    CodeBlock(
                        "let (x, y) = (10, 20);\n" +
                        "println!(\"{x} {y}\");        // 10 20\n\n" +
                        "let (a, _, c) = (1, 2, 3);  // ignore the middle element\n" +
                        "println!(\"{a} {c}\");        // 1 3"
                    )
                    BodyText("Destructuring is especially handy with functions that return a tuple:")
                    CodeBlock(
                        "fn min_max(data: &[i32]) -> (i32, i32) {\n" +
                        "    let min = *data.iter().min().unwrap();\n" +
                        "    let max = *data.iter().max().unwrap();\n" +
                        "    (min, max)\n" +
                        "}\n\n" +
                        "let (lo, hi) = min_max(&[3, 1, 4, 1, 5]);\n" +
                        "println!(\"{lo} {hi}\");  // 1 5"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Arrays") {
                    BodyText(
                        "An array is a fixed-length collection of elements all of the same type. " +
                        "The type is [T; N] where N is the number of elements — the size is part " +
                        "of the type and cannot change at runtime. Arrays live on the stack."
                    )
                    CodeBlock(
                        "let nums = [1, 2, 3, 4, 5];        // [i32; 5], inferred\n" +
                        "let zeros: [i32; 5] = [0; 5];      // repeat syntax: five zeros\n\n" +
                        "// Number of elements:\n" +
                        "println!(\"{}\", nums.len());         // 5\n\n" +
                        "// Access by zero-based index:\n" +
                        "println!(\"{}\", nums[0]);            // 1\n" +
                        "println!(\"{}\", nums[4]);            // 5\n" +
                        "// nums[5]  — panic at runtime: index out of bounds\n\n" +
                        "// Mutate elements (array variable must be mut):\n" +
                        "let mut arr = [10, 20, 30];\n" +
                        "arr[1] = 99;\n" +
                        "println!(\"{:?}\", arr);              // [10, 99, 30]\n\n" +
                        "// Iterate:\n" +
                        "for n in nums {\n" +
                        "    println!(\"{n}\");\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Slices — Non-Mutable (&[T])") {
                    BodyText(
                        "A slice is a view into a contiguous sequence of elements — it does not " +
                        "own the data, it borrows it. A shared slice &[T] is a (pointer, length) " +
                        "pair. It lets you read the elements but not modify them. The array or " +
                        "Vec it points to continues to exist independently."
                    )
                    CodeBlock(
                        "let arr = [1, 2, 3, 4, 5];\n\n" +
                        "let slice = &arr[1..4];      // view of indices 1,2,3 → [2, 3, 4]\n" +
                        "println!(\"{}\", slice.len()); // 3\n" +
                        "println!(\"{}\", slice[0]);    // 2\n\n" +
                        "let whole: &[i32] = &arr[..]; // slice of the whole array\n\n" +
                        "// Slices are the idiomatic parameter type for read-only access:\n" +
                        "fn sum(s: &[i32]) -> i32 {\n" +
                        "    s.iter().sum()\n" +
                        "}\n" +
                        "println!(\"{}\", sum(&arr));       // 15\n" +
                        "println!(\"{}\", sum(&arr[1..4])); // 9"
                    )
                    BodyText(
                        "Using &[T] as a function parameter is preferred over &[T; N] because it " +
                        "works with any length — you are not restricted to a specific array size."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Slices — Mutable (&mut [T])") {
                    BodyText(
                        "&mut [T] is a mutable slice — a view that allows modifying the " +
                        "elements of the underlying array through the slice. What it does NOT " +
                        "allow is growing or shrinking the collection: you can change what is " +
                        "already there, but you cannot add new elements. The standard borrow " +
                        "rules apply: while a mutable slice is alive, you cannot hold any other " +
                        "reference to the array."
                    )
                    CodeBlock(
                        "let mut arr = [1, 2, 3, 4, 5];\n\n" +
                        "{\n" +
                        "    let slice_mut = &mut arr[1..4];  // mutable view of indices 1,2,3\n" +
                        "    slice_mut[0] = 99;               // changes arr[1]\n" +
                        "}   // slice_mut ends here\n\n" +
                        "println!(\"{:?}\", arr);  // [1, 99, 3, 4, 5]\n\n" +
                        "// Cannot add elements — slice has fixed length:\n" +
                        "// slice_mut.push(6);  // compile error: no push on &mut [T]\n\n" +
                        "// Useful for in-place sorting of a portion:\n" +
                        "let mut data = [5, 3, 1, 4, 2];\n" +
                        "data[1..4].sort();\n" +
                        "println!(\"{:?}\", data);  // [5, 1, 3, 4, 2]"
                    )
                    BodyText(
                        "Mutable slices let you pass part of an array to a function that needs " +
                        "to modify elements — without giving it access to the whole array."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
