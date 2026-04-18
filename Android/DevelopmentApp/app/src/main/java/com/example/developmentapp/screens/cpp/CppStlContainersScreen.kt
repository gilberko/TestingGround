package com.example.developmentapp.screens.cpp

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
fun CppStlContainersScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "STL Containers",
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
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            item { Spacer(Modifier.height(12.dp)) }

            // ── std::vector ───────────────────────────────────────────────────
            item {
                SectionCard(title = "std::vector — Dynamic Array") {
                    BodyText(
                        "std::vector is a contiguous dynamic array — think of it as a C array that can " +
                        "grow. Elements are stored in a single block of heap memory, so random access " +
                        "by index is O(1) and cache performance is excellent."
                    )
                    CodeBlock(
                        "#include <vector>\n\n" +
                        "std::vector<int> v = {1, 2, 3};\n" +
                        "v.push_back(4);       // add to end\n" +
                        "int x = v[2];         // O(1) random access — x == 3\n" +
                        "v.erase(v.begin());   // remove first element — O(n) shift\n" +
                        "std::cout << v.size();     // number of elements currently stored\n" +
                        "std::cout << v.capacity(); // allocated slots (>= size)"
                    )
                    BodyText(
                        "Geometric growth and amortized O(1) push_back:"
                    )
                    BodyText(
                        "When push_back would exceed capacity, vector allocates a new, larger block, " +
                        "copies all elements, then inserts the new one. This single operation costs O(n). " +
                        "However, implementations grow capacity geometrically — typically doubling it " +
                        "(GCC and MSVC both double; LLVM uses 1.5x). The C++ standard does not mandate " +
                        "a specific factor, only that push_back has amortized O(1) complexity."
                    )
                    BodyText(
                        "Why amortized O(1)? If we start with capacity 1 and double on each overflow, " +
                        "inserting n elements triggers reallocations of size 1, 2, 4, 8 … up to n. " +
                        "Total copy work = 1 + 2 + 4 + … + n = 2n - 1 = O(n). Spread across n " +
                        "insertions that is O(1) per insertion on average. Any single push_back may " +
                        "still take O(n) in the worst case — amortized O(1) describes the average " +
                        "over a sequence of operations, not a per-call guarantee."
                    )
                    BodyText(
                        "Amortized O(1) vs worst-case O(1): amortized means the average cost over " +
                        "many operations is bounded by O(1), even though individual operations may " +
                        "occasionally spike. Hash-table lookup has average O(1) but worst-case O(n). " +
                        "vector push_back has amortized O(1) but worst-case O(n). Real-time systems " +
                        "that cannot tolerate spikes should pre-allocate with .reserve()."
                    )
                    CodeBlock(
                        "std::vector<int> v;\n" +
                        "v.reserve(1000);    // pre-allocate 1000 slots — no reallocation until full\n" +
                        "// Now push_back up to 1000 elements with zero reallocation cost\n\n" +
                        "v.shrink_to_fit();  // hint to release excess capacity (non-binding)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── std::list ─────────────────────────────────────────────────────
            item {
                SectionCard(title = "std::list — Doubly Linked List") {
                    BodyText(
                        "std::list stores elements as individually heap-allocated nodes, each holding " +
                        "a value and two pointers (prev/next). There is no contiguous memory block."
                    )
                    CodeBlock(
                        "#include <list>\n\n" +
                        "std::list<int> lst = {10, 20, 30};\n" +
                        "lst.push_front(5);       // O(1) — prepend\n" +
                        "lst.push_back(40);       // O(1) — append\n\n" +
                        "auto it = lst.begin();\n" +
                        "std::advance(it, 2);     // O(n) — no random access\n" +
                        "lst.insert(it, 99);      // O(1) once you have the iterator\n" +
                        "lst.erase(it);           // O(1) — splices out the node"
                    )
                    BodyText(
                        "Key properties:\n" +
                        "  O(1) insert and erase anywhere given an iterator — no shifting needed.\n" +
                        "  O(n) to reach an arbitrary element — no operator[] or random access.\n" +
                        "  Insertions and erasures never invalidate other iterators (unlike vector).\n" +
                        "  Poor cache locality — each node is a separate heap allocation, so " +
                        "traversal causes many cache misses.\n\n" +
                        "Use std::list when: you need stable iterators under frequent mid-sequence " +
                        "insertions/erasures. For most other cases, vector is faster despite its " +
                        "O(n) shift cost, because its cache friendliness dominates in practice."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── std::set / std::unordered_set ────────────────────────────────
            item {
                SectionCard(title = "std::set and std::unordered_set") {
                    BodyText(
                        "Both store unique values with no duplicates. They differ in ordering guarantees " +
                        "and performance."
                    )
                    BodyText(
                        "std::set is implemented as a Red-Black tree — a self-balancing binary search " +
                        "tree. Elements are always kept in sorted order. Insert, lookup (count/find), " +
                        "and erase are all O(log n)."
                    )
                    CodeBlock(
                        "#include <set>\n\n" +
                        "std::set<int> s = {5, 3, 8, 1};\n" +
                        "s.insert(4);\n" +
                        "s.insert(3);              // duplicate — silently ignored\n" +
                        "bool found = s.count(8);  // O(log n) — 1 if present, 0 if not\n\n" +
                        "for (int x : s)           // always iterates in sorted order: 1 3 4 5 8\n" +
                        "    std::cout << x << ' ';"
                    )
                    BodyText(
                        "std::unordered_set is implemented as a hash table. Insert, lookup, and erase " +
                        "are O(1) on average. Elements are stored in no particular order — iteration " +
                        "order is unspecified. Worst case is O(n) when many elements hash to the same " +
                        "bucket (hash collision storm), which is rare in practice."
                    )
                    CodeBlock(
                        "#include <unordered_set>\n\n" +
                        "std::unordered_set<int> us = {5, 3, 8, 1};\n" +
                        "us.insert(4);              // O(1) average\n" +
                        "bool found = us.count(8);  // O(1) average\n\n" +
                        "// Iteration order is arbitrary — do NOT rely on it\n" +
                        "for (int x : us)\n" +
                        "    std::cout << x << ' ';  // might print: 1 8 3 5 4"
                    )
                    BodyText(
                        "std::multiset allows duplicate elements; std::unordered_multiset is the " +
                        "hash-based equivalent. Use count(k) to find how many times k appears."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── std::map / std::unordered_map ────────────────────────────────
            item {
                SectionCard(title = "std::map and std::unordered_map") {
                    BodyText(
                        "Both map a unique key to a value. Again, the ordered variant uses a " +
                        "Red-Black tree; the unordered variant uses a hash table."
                    )
                    BodyText(
                        "std::map — Red-Black tree, keys always sorted, O(log n) for all operations. " +
                        "Iteration visits entries in ascending key order."
                    )
                    CodeBlock(
                        "#include <map>\n\n" +
                        "std::map<std::string, int> scores;\n" +
                        "scores[\"Alice\"] = 95;\n" +
                        "scores[\"Bob\"]   = 87;\n" +
                        "scores[\"Carol\"] = 91;\n\n" +
                        "// Iterates in key order: Alice, Bob, Carol\n" +
                        "for (auto &[name, score] : scores)\n" +
                        "    std::cout << name << \" = \" << score << '\\n';\n\n" +
                        "// Safe lookup — won't insert a default value\n" +
                        "auto it = scores.find(\"Dave\");\n" +
                        "if (it != scores.end())\n" +
                        "    std::cout << it->second;\n\n" +
                        "// C++20: cleaner\n" +
                        "if (scores.contains(\"Dave\"))\n" +
                        "    std::cout << scores.at(\"Dave\");"
                    )
                    BodyText(
                        "Operator[] gotcha: scores[\"Dave\"] will default-construct an int (== 0) and " +
                        "insert it if 'Dave' is not already present. This silently grows the map. " +
                        "Use .find() or .contains() + .at() when you only want to read."
                    )
                    BodyText(
                        "std::unordered_map — hash table, O(1) average, no ordering. Same [] gotcha applies."
                    )
                    CodeBlock(
                        "#include <unordered_map>\n\n" +
                        "std::unordered_map<std::string, int> freq;\n" +
                        "for (const auto &word : words)\n" +
                        "    freq[word]++;   // inserts with 0 on first encounter, then increments\n\n" +
                        "// O(1) average lookup\n" +
                        "std::cout << freq[\"hello\"];"
                    )
                    BodyText(
                        "std::multimap and std::unordered_multimap allow duplicate keys. " +
                        "They do not support operator[] — use insert() and equal_range()."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Comparison table ──────────────────────────────────────────────
            item {
                SectionCard(title = "Quick Comparison") {
                    CodeBlock(
                        "Container            | Ordered | Structure       | Insert/Lookup/Erase\n" +
                        "---------------------|---------|-----------------|--------------------\n" +
                        "vector               |  —      | contiguous array| O(1) amortized end\n" +
                        "                     |         |                 | O(n) mid / front\n" +
                        "list                 |  —      | doubly linked   | O(1) with iterator\n" +
                        "set / map            |  yes    | Red-Black tree  | O(log n)\n" +
                        "unordered_set / map  |  no     | hash table      | O(1) average\n" +
                        "multiset / multimap  |  yes    | Red-Black tree  | O(log n)\n" +
                        "unordered_multi...   |  no     | hash table      | O(1) average"
                    )
                    BodyText(
                        "Rule of thumb:\n" +
                        "  Need fast random access + iteration? → vector\n" +
                        "  Need stable iterators + O(1) splice? → list\n" +
                        "  Need uniqueness + sorted order? → set\n" +
                        "  Need uniqueness + maximum speed, order irrelevant? → unordered_set\n" +
                        "  Need key-value + sorted keys? → map\n" +
                        "  Need key-value + maximum speed? → unordered_map"
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
