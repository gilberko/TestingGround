package com.example.developmentapp.screens.kotlin

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
fun KotlinCollectionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Kotlin — Collections",
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
                SectionCard(title = "Immutable vs Mutable") {
                    BodyText(
                        "Kotlin distinguishes between read-only and mutable collections at the type " +
                        "level. This is a key design decision:\n\n" +
                        "Read-only (List, Map, Set) — the interface provides only read operations. " +
                        "It does not mean the underlying data is frozen — if the same object is also " +
                        "referenced as a MutableList somewhere, it can still be changed there. But " +
                        "through the read-only reference, you cannot mutate it.\n\n" +
                        "Mutable (MutableList, MutableMap, MutableSet) — adds write operations " +
                        "(add, remove, put, clear, etc.).\n\n" +
                        "Prefer read-only types in function signatures and class properties — pass " +
                        "mutable collections only when callers genuinely need to write to them."
                    )
                    CodeBlock(
                        "// Read-only:\n" +
                        "val readOnly: List<Int> = listOf(1, 2, 3)\n" +
                        "// readOnly.add(4)   // COMPILE ERROR — List has no add()\n\n" +
                        "// Mutable:\n" +
                        "val mutable: MutableList<Int> = mutableListOf(1, 2, 3)\n" +
                        "mutable.add(4)       // OK\n" +
                        "mutable.removeAt(0)  // OK\n\n" +
                        "// Convert between the two:\n" +
                        "val ro: List<Int>        = mutable.toList()          // defensive copy\n" +
                        "val mu: MutableList<Int> = readOnly.toMutableList()  // new mutable copy\n\n" +
                        "// Same for Map and Set:\n" +
                        "val roMap: Map<String, Int>        = mapOf(\"a\" to 1)\n" +
                        "val muMap: MutableMap<String, Int> = mutableMapOf(\"a\" to 1)\n" +
                        "val roSet: Set<Int>        = setOf(1, 2, 3)\n" +
                        "val muSet: MutableSet<Int> = mutableSetOf(1, 2, 3)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "List") {
                    BodyText(
                        "List is an ordered collection with indexed access. Elements can repeat. " +
                        "Under the hood, listOf returns an unmodifiable view of an ArrayList. " +
                        "mutableListOf returns a real ArrayList."
                    )
                    CodeBlock(
                        "val fruits = listOf(\"apple\", \"banana\", \"cherry\")\n" +
                        "println(fruits[0])          // apple\n" +
                        "println(fruits.size)        // 3\n" +
                        "println(fruits.contains(\"banana\"))  // true\n" +
                        "println(fruits.indexOf(\"cherry\"))   // 2\n" +
                        "println(fruits.first())     // apple\n" +
                        "println(fruits.last())      // cherry\n\n" +
                        "val mutable = mutableListOf(\"a\", \"b\", \"c\")\n" +
                        "mutable.add(\"d\")\n" +
                        "mutable.add(1, \"x\")       // insert at index 1\n" +
                        "mutable.removeAt(0)        // remove first element\n" +
                        "mutable.set(0, \"z\")        // replace at index 0\n" +
                        "mutable[0] = \"z\"           // same as set()\n\n" +
                        "// Iteration:\n" +
                        "for (fruit in fruits) println(fruit)\n" +
                        "fruits.forEachIndexed { i, v -> println(\"\$i: \$v\") }"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Map") {
                    BodyText(
                        "Map stores key-value pairs. Keys are unique; values may repeat. " +
                        "mapOf creates a read-only LinkedHashMap (preserves insertion order). " +
                        "map[key] returns V? — null if the key is absent."
                    )
                    CodeBlock(
                        "val scores = mapOf(\"Alice\" to 95, \"Bob\" to 82, \"Carol\" to 90)\n\n" +
                        "println(scores[\"Alice\"])              // 95\n" +
                        "println(scores[\"Dave\"])               // null — not found\n" +
                        "println(scores.getOrDefault(\"Dave\", 0)) // 0\n" +
                        "println(scores.size)                  // 3\n" +
                        "println(scores.containsKey(\"Bob\"))    // true\n" +
                        "println(scores.containsValue(82))     // true\n\n" +
                        "// Iterate:\n" +
                        "for ((name, score) in scores) {\n" +
                        "    println(\"\$name: \$score\")\n" +
                        "}\n" +
                        "scores.forEach { (k, v) -> println(\"\$k scored \$v\") }\n\n" +
                        "// Mutable map:\n" +
                        "val mu = mutableMapOf(\"a\" to 1)\n" +
                        "mu[\"b\"] = 2          // add or overwrite\n" +
                        "mu.put(\"c\", 3)\n" +
                        "mu.remove(\"a\")\n" +
                        "mu.getOrPut(\"d\") { 4 }  // add 4 only if \"d\" is absent"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Set") {
                    BodyText(
                        "Set is an unordered collection with no duplicate elements. setOf creates a " +
                        "read-only LinkedHashSet (preserves insertion order). Use it when you need " +
                        "fast membership testing or want to eliminate duplicates."
                    )
                    CodeBlock(
                        "val colors = setOf(\"red\", \"green\", \"blue\", \"red\")  // \"red\" ignored\n" +
                        "println(colors)                // [red, green, blue]\n" +
                        "println(colors.size)           // 3\n" +
                        "println(colors.contains(\"red\"))  // true\n\n" +
                        "// Set operations:\n" +
                        "val a = setOf(1, 2, 3, 4)\n" +
                        "val b = setOf(3, 4, 5, 6)\n" +
                        "println(a union b)      // [1, 2, 3, 4, 5, 6]\n" +
                        "println(a intersect b)  // [3, 4]\n" +
                        "println(a subtract b)   // [1, 2]\n\n" +
                        "// Mutable set:\n" +
                        "val mu = mutableSetOf(\"a\", \"b\")\n" +
                        "mu.add(\"c\")\n" +
                        "mu.add(\"a\")   // duplicate — ignored, returns false\n" +
                        "mu.remove(\"b\")"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Common Operations") {
                    BodyText(
                        "Kotlin's collection API is rich. These are the most commonly used operations — " +
                        "all return new collections, they do not mutate the original."
                    )
                    CodeBlock(
                        "val nums = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)\n\n" +
                        "// filter — keep elements matching predicate:\n" +
                        "nums.filter { it % 2 == 0 }           // [2, 4, 6, 8, 10]\n\n" +
                        "// map — transform each element:\n" +
                        "nums.map { it * it }                  // [1, 4, 9, 16, ...]\n\n" +
                        "// flatMap — map then flatten:\n" +
                        "listOf(1, 2, 3).flatMap { listOf(it, it * 10) } // [1,10, 2,20, 3,30]\n\n" +
                        "// any / all / none:\n" +
                        "nums.any  { it > 9 }   // true\n" +
                        "nums.all  { it > 0 }   // true\n" +
                        "nums.none { it > 10 }  // true\n\n" +
                        "// first / last / find (returns null if not found):\n" +
                        "nums.first { it > 5 }      // 6\n" +
                        "nums.find  { it > 100 }    // null\n\n" +
                        "// sorted / sortedBy:\n" +
                        "listOf(3, 1, 4, 1, 5).sorted()            // [1, 1, 3, 4, 5]\n" +
                        "listOf(\"banana\", \"apple\").sortedBy { it } // [apple, banana]\n\n" +
                        "// groupBy — partition into a Map:\n" +
                        "nums.groupBy { if (it % 2 == 0) \"even\" else \"odd\" }\n" +
                        "// {even=[2,4,6,8,10], odd=[1,3,5,7,9]}\n\n" +
                        "// take / drop:\n" +
                        "nums.take(3)    // [1, 2, 3]\n" +
                        "nums.drop(7)    // [8, 9, 10]"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Iterators") {
                    BodyText(
                        "Every Kotlin collection implements Iterable<T>, which provides an iterator() " +
                        "method returning Iterator<T>. The for loop calls iterator() implicitly — " +
                        "you rarely need to use Iterator directly.\n\n" +
                        "Iterator<T> has two methods:\n" +
                        "• hasNext() — returns true if more elements remain\n" +
                        "• next() — returns the next element; throws NoSuchElementException if empty\n\n" +
                        "MutableIterator<T> adds remove() to delete the current element during traversal."
                    )
                    CodeBlock(
                        "// for loop (preferred — uses iterator under the hood):\n" +
                        "val list = listOf(\"a\", \"b\", \"c\")\n" +
                        "for (item in list) println(item)\n\n" +
                        "// Manual iterator:\n" +
                        "val it = list.iterator()\n" +
                        "while (it.hasNext()) {\n" +
                        "    println(it.next())\n" +
                        "}\n\n" +
                        "// Removing during iteration (MutableIterator):\n" +
                        "val mu = mutableListOf(1, 2, 3, 4, 5)\n" +
                        "val mit = mu.iterator()\n" +
                        "while (mit.hasNext()) {\n" +
                        "    if (mit.next() % 2 == 0) mit.remove()   // safe removal\n" +
                        "}\n" +
                        "println(mu)   // [1, 3, 5]\n\n" +
                        "// Ranges are also iterable:\n" +
                        "for (i in 1..5) print(\"\$i \")       // 1 2 3 4 5\n" +
                        "for (i in 10 downTo 1 step 3) print(\"\$i \")  // 10 7 4 1"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Sequences — Lazy Evaluation") {
                    BodyText(
                        "Regular collection operations (filter, map, etc.) are eager — each step " +
                        "creates a new intermediate list. For long chains on large collections this " +
                        "creates unnecessary allocations.\n\n" +
                        "Sequences are lazy — operations are chained and executed only when a terminal " +
                        "operation (toList, first, sum, count, etc.) is called. Each element passes " +
                        "through the whole chain before the next element is processed.\n\n" +
                        "Use sequences for:\n" +
                        "• Chains of 3+ operations on large collections\n" +
                        "• Infinite or very large streams\n" +
                        "• Early termination (first, take) that would otherwise process the whole list"
                    )
                    CodeBlock(
                        "// Eager — creates 3 intermediate lists:\n" +
                        "val result1 = (1..1_000_000)\n" +
                        "    .filter { it % 2 == 0 }\n" +
                        "    .map    { it * it }\n" +
                        "    .take(5)\n" +
                        "    .toList()   // allocates full filtered + mapped lists first\n\n" +
                        "// Lazy — only processes elements until 5 results are found:\n" +
                        "val result2 = (1..1_000_000).asSequence()\n" +
                        "    .filter { it % 2 == 0 }\n" +
                        "    .map    { it * it }\n" +
                        "    .take(5)\n" +
                        "    .toList()   // stops after finding 5 elements\n\n" +
                        "println(result2)  // [4, 16, 36, 64, 100]\n\n" +
                        "// Generate an infinite sequence:\n" +
                        "val fibs = generateSequence(Pair(0, 1)) { Pair(it.second, it.first + it.second) }\n" +
                        "    .map { it.first }\n" +
                        "    .take(10)\n" +
                        "    .toList()\n" +
                        "println(fibs)  // [0, 1, 1, 2, 3, 5, 8, 13, 21, 34]"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
