package com.example.developmentapp.screens.stl

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
fun StlAlgorithmsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "C/C++ — Algorithms",
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
            item { Spacer(Modifier.height(16.dp)) }

            // ── C: qsort ─────────────────────────────────────────────────────
            item {
                SectionCard(title = "C Sorting — qsort") {
                    BodyText("qsort is declared in <stdlib.h> / <cstdlib>. Despite the name, the C standard does not mandate quicksort — implementations are free to use any algorithm. Modern standard libraries typically use introsort (a hybrid of quicksort, heapsort, and insertion sort) to guarantee O(n log n) worst-case performance.")
                    BodyText("Signature: qsort(base, nitems, size, comparator)")
                    BodyText("  base — pointer to the start of the array.")
                    BodyText("  nitems — number of elements.")
                    BodyText("  size — size in bytes of each element (use sizeof).")
                    BodyText("  comparator — function taking two const void* pointers; must return a negative int if the first element is less than the second, zero if equal, positive if greater.")
                    BodyText("Complexity: O(n log n) average and worst case (in practice with introsort). Original quicksort is O(n²) worst case with a bad pivot — the name is historical.")
                    CodeBlock(
                        "#include <stdlib.h>\n" +
                        "#include <stdio.h>\n" +
                        "\n" +
                        "int cmp_int(const void* a, const void* b) {\n" +
                        "    int ia = *(const int*)a;\n" +
                        "    int ib = *(const int*)b;\n" +
                        "    return (ia > ib) - (ia < ib); // avoids overflow from ia-ib\n" +
                        "}\n" +
                        "\n" +
                        "int arr[] = {5, 2, 8, 1, 9, 3};\n" +
                        "qsort(arr, 6, sizeof(int), cmp_int);\n" +
                        "// arr == {1, 2, 3, 5, 8, 9}\n" +
                        "\n" +
                        "// Sort strings\n" +
                        "int cmp_str(const void* a, const void* b) {\n" +
                        "    return strcmp(*(const char**)a, *(const char**)b);\n" +
                        "}\n" +
                        "const char* words[] = {\"banana\", \"apple\", \"cherry\"};\n" +
                        "qsort(words, 3, sizeof(char*), cmp_str);\n" +
                        "// words == {\"apple\", \"banana\", \"cherry\"}"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── C: bsearch ───────────────────────────────────────────────────
            item {
                SectionCard(title = "C Binary Search — bsearch") {
                    BodyText("bsearch is declared in <stdlib.h> / <cstdlib>. It performs a binary search on a sorted array and returns a pointer to a matching element, or NULL if not found.")
                    BodyText("IMPORTANT: the array must already be sorted by the same comparator you pass to bsearch, otherwise the behaviour is undefined. Always sort with qsort first if the array is not already ordered.")
                    BodyText("Complexity: O(log n). The comparator signature is identical to qsort — the key is passed as the first argument.")
                    CodeBlock(
                        "#include <stdlib.h>\n" +
                        "\n" +
                        "int arr[] = {1, 2, 3, 5, 8, 9}; // must be sorted!\n" +
                        "\n" +
                        "int key = 5;\n" +
                        "int* found = (int*)bsearch(&key, arr, 6, sizeof(int), cmp_int);\n" +
                        "\n" +
                        "if (found) {\n" +
                        "    printf(\"Found %d at index %td\\n\", *found, found - arr); // index 3\n" +
                        "} else {\n" +
                        "    printf(\"Not found\\n\");\n" +
                        "}\n" +
                        "\n" +
                        "// If there are duplicates, bsearch may return a pointer\n" +
                        "// to ANY matching element — not necessarily the first one."
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── std::sort ────────────────────────────────────────────────────
            item {
                SectionCard(title = "C++ std::sort") {
                    BodyText("std::sort is in <algorithm>. Since C++11 the standard guarantees O(n log n) worst-case complexity. All major implementations use introsort: quicksort for the average case, heap sort when recursion depth exceeds a threshold (to avoid quicksort's O(n²) worst case), and insertion sort for small partitions.")
                    BodyText("It sorts in ascending order by default. Supply a custom comparator to sort in descending order or by a specific field.")
                    BodyText("std::stable_sort preserves the relative order of equal elements; it is O(n log² n) worst case (O(n log n) if extra memory is available).")
                    CodeBlock(
                        "#include <algorithm>\n" +
                        "#include <vector>\n" +
                        "#include <functional>\n" +
                        "\n" +
                        "std::vector<int> v = {5, 2, 8, 1, 9, 3};\n" +
                        "\n" +
                        "// Ascending (default)\n" +
                        "std::sort(v.begin(), v.end());\n" +
                        "// v == {1, 2, 3, 5, 8, 9}\n" +
                        "\n" +
                        "// Descending\n" +
                        "std::sort(v.begin(), v.end(), std::greater<int>());\n" +
                        "// v == {9, 8, 5, 3, 2, 1}\n" +
                        "\n" +
                        "// Custom comparator — sort by string length\n" +
                        "std::vector<std::string> words = {\"banana\", \"kiwi\", \"apple\"};\n" +
                        "std::sort(words.begin(), words.end(),\n" +
                        "    [](const std::string& a, const std::string& b) {\n" +
                        "        return a.size() < b.size();\n" +
                        "    });\n" +
                        "// words == {\"kiwi\", \"apple\", \"banana\"}\n" +
                        "\n" +
                        "// Sort a raw array\n" +
                        "int arr[] = {5, 2, 8, 1};\n" +
                        "std::sort(arr, arr + 4);"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── std::transform ───────────────────────────────────────────────
            item {
                SectionCard(title = "std::transform") {
                    BodyText("std::transform applies a function to each element of an input range and writes the results to an output range. The output range can be the same as the input range (in-place transformation). There are two forms:")
                    BodyText("Unary — applies f(element) to each element of one input range.")
                    BodyText("Binary — applies f(element1, element2) to paired elements of two input ranges.")
                    CodeBlock(
                        "#include <algorithm>\n" +
                        "#include <vector>\n" +
                        "#include <numeric>\n" +
                        "\n" +
                        "std::vector<int> v = {1, 2, 3, 4, 5};\n" +
                        "\n" +
                        "// Unary: square each element in-place\n" +
                        "std::transform(v.begin(), v.end(), v.begin(),\n" +
                        "    [](int x) { return x * x; });\n" +
                        "// v == {1, 4, 9, 16, 25}\n" +
                        "\n" +
                        "// Unary: write results into a different vector\n" +
                        "std::vector<int> out(v.size());\n" +
                        "std::transform(v.begin(), v.end(), out.begin(),\n" +
                        "    [](int x) { return x + 100; });\n" +
                        "// out == {101, 104, 109, 116, 125}\n" +
                        "\n" +
                        "// Binary: element-wise addition of two vectors\n" +
                        "std::vector<int> a = {1, 2, 3};\n" +
                        "std::vector<int> b = {10, 20, 30};\n" +
                        "std::vector<int> sum(3);\n" +
                        "std::transform(a.begin(), a.end(), b.begin(), sum.begin(),\n" +
                        "    [](int x, int y) { return x + y; });\n" +
                        "// sum == {11, 22, 33}"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── remove_if ────────────────────────────────────────────────────
            item {
                SectionCard(title = "std::remove_if and the Erase-Remove Idiom") {
                    BodyText("std::remove_if does NOT erase elements from the container. It rearranges elements: those not matching the predicate are moved to the front; those matching the predicate are left in an unspecified state at the end. It returns an iterator pointing to the new logical end of the kept elements.")
                    BodyText("To actually shrink the container, follow remove_if with a call to erase(). This combination is called the erase-remove idiom.")
                    CodeBlock(
                        "#include <algorithm>\n" +
                        "#include <vector>\n" +
                        "\n" +
                        "std::vector<int> v = {1, 2, 3, 4, 5, 6, 7, 8};\n" +
                        "\n" +
                        "// Remove all even numbers\n" +
                        "auto new_end = std::remove_if(v.begin(), v.end(),\n" +
                        "    [](int x) { return x % 2 == 0; });\n" +
                        "// v is now {1, 3, 5, 7, ?, ?, ?, ?} — size() still 8!\n" +
                        "// new_end points past the last kept element\n" +
                        "\n" +
                        "v.erase(new_end, v.end()); // actually remove the tail\n" +
                        "// v == {1, 3, 5, 7}, v.size() == 4\n" +
                        "\n" +
                        "// One-liner (C++20 adds std::erase_if which does both):\n" +
                        "v.erase(std::remove_if(v.begin(), v.end(),\n" +
                        "    [](int x) { return x > 4; }), v.end());\n" +
                        "\n" +
                        "// C++20 shorthand\n" +
                        "// std::erase_if(v, [](int x){ return x > 4; });"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── binary search family ─────────────────────────────────────────
            item {
                SectionCard(title = "std::binary_search, lower_bound, upper_bound") {
                    BodyText("All three require a sorted range. They run in O(log n) for random-access iterators.")
                    BodyText("std::binary_search(first, last, value) — returns true if value exists in the range, false otherwise. Use this when you only need to know if an element is present.")
                    BodyText("std::lower_bound(first, last, value) — returns an iterator to the first element that is NOT LESS THAN value (i.e., >= value). If all elements are less, returns last.")
                    BodyText("std::upper_bound(first, last, value) — returns an iterator to the first element that is GREATER THAN value (> value). If no element is greater, returns last.")
                    BodyText("Together, lower_bound and upper_bound define the half-open range [lower, upper) of all elements equal to value — useful for counting duplicates or finding insertion points.")
                    CodeBlock(
                        "#include <algorithm>\n" +
                        "#include <vector>\n" +
                        "\n" +
                        "std::vector<int> v = {1, 2, 2, 3, 3, 3, 5, 8}; // sorted!\n" +
                        "\n" +
                        "// binary_search — does 3 exist?\n" +
                        "bool found = std::binary_search(v.begin(), v.end(), 3); // true\n" +
                        "\n" +
                        "// lower_bound — first element >= 3\n" +
                        "auto lo = std::lower_bound(v.begin(), v.end(), 3);\n" +
                        "// *lo == 3, index == 3\n" +
                        "\n" +
                        "// upper_bound — first element > 3\n" +
                        "auto hi = std::upper_bound(v.begin(), v.end(), 3);\n" +
                        "// *hi == 5, index == 6\n" +
                        "\n" +
                        "// Count occurrences of 3\n" +
                        "int count = (int)(hi - lo);  // 3\n" +
                        "\n" +
                        "// Insertion point to keep sorted order\n" +
                        "auto ins = std::lower_bound(v.begin(), v.end(), 4);\n" +
                        "v.insert(ins, 4); // {1,2,2,3,3,3,4,5,8}\n" +
                        "\n" +
                        "// Custom comparator works too:\n" +
                        "std::lower_bound(v.begin(), v.end(), 4, std::less<int>());"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── nth_element ──────────────────────────────────────────────────
            item {
                SectionCard(title = "std::nth_element") {
                    BodyText("std::nth_element partially sorts a range so that the element at the nth position is the same element that would be there if the range were fully sorted. Elements before nth are all <= *nth; elements after nth are all >= *nth. Their relative order is NOT specified.")
                    BodyText("Complexity: O(n) average (uses introselect). Much faster than fully sorting when you only need the k-th smallest/largest element — such as the median, a percentile, or a top-K selection.")
                    CodeBlock(
                        "#include <algorithm>\n" +
                        "#include <vector>\n" +
                        "\n" +
                        "std::vector<int> v = {7, 2, 9, 1, 5, 4, 8, 3, 6};\n" +
                        "\n" +
                        "// Find the median (element at index n/2 in sorted order)\n" +
                        "auto mid = v.begin() + v.size() / 2;\n" +
                        "std::nth_element(v.begin(), mid, v.end());\n" +
                        "int median = *mid;   // 5 (the 5th smallest of 9 elements)\n" +
                        "\n" +
                        "// After nth_element:\n" +
                        "// Elements before mid are all <= 5\n" +
                        "// Elements after  mid are all >= 5\n" +
                        "// But they are NOT fully sorted\n" +
                        "\n" +
                        "// Top-3 smallest (partial sort)\n" +
                        "std::vector<int> data = {7, 2, 9, 1, 5, 4};\n" +
                        "std::nth_element(data.begin(), data.begin() + 3, data.end());\n" +
                        "// data[0..2] are the 3 smallest values (in any order)\n" +
                        "// data[3..] are the 3 largest values (in any order)"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── iota / reduce / inner_product ────────────────────────────────
            item {
                SectionCard(title = "std::iota, std::reduce, std::inner_product") {
                    BodyText("These three are in <numeric>, not <algorithm>.")
                    BodyText("std::iota(first, last, value) — fills the range with sequentially increasing values starting from value. The name comes from the APL language. O(n).")
                    BodyText("std::reduce(first, last, init, op) — combines all elements using op, starting with init. Similar to std::accumulate but may execute in any order (allowing parallel execution). The default op is addition. O(n). Requires C++17. With std::execution::par it can run in parallel on C++17.")
                    BodyText("std::inner_product(first1, last1, first2, init) — computes the dot product: starts with init, then adds first1[i] * first2[i] for each pair. Custom binary ops can replace both + and *. O(n). Available since C++98.")
                    CodeBlock(
                        "#include <numeric>\n" +
                        "#include <vector>\n" +
                        "\n" +
                        "// std::iota — fill with 0, 1, 2, 3, 4\n" +
                        "std::vector<int> v(5);\n" +
                        "std::iota(v.begin(), v.end(), 0);    // {0,1,2,3,4}\n" +
                        "std::iota(v.begin(), v.end(), 10);   // {10,11,12,13,14}\n" +
                        "\n" +
                        "// std::reduce (C++17) — sum all elements\n" +
                        "std::vector<int> data = {1, 2, 3, 4, 5};\n" +
                        "int sum = std::reduce(data.begin(), data.end(), 0);     // 15\n" +
                        "int product = std::reduce(data.begin(), data.end(), 1,\n" +
                        "    std::multiplies<int>());                            // 120\n" +
                        "\n" +
                        "// std::accumulate (C++98, ordered alternative to reduce)\n" +
                        "// int sum2 = std::accumulate(data.begin(), data.end(), 0);\n" +
                        "\n" +
                        "// std::inner_product — dot product\n" +
                        "std::vector<int> a = {1, 2, 3};\n" +
                        "std::vector<int> b = {4, 5, 6};\n" +
                        "int dot = std::inner_product(\n" +
                        "    a.begin(), a.end(), b.begin(), 0);\n" +
                        "// 1*4 + 2*5 + 3*6 = 4 + 10 + 18 = 32\n" +
                        "\n" +
                        "// Custom ops: sum of absolute differences |a[i]-b[i]|\n" +
                        "int sad = std::inner_product(\n" +
                        "    a.begin(), a.end(), b.begin(), 0,\n" +
                        "    std::plus<int>(),\n" +
                        "    [](int x, int y) { return std::abs(x - y); });\n" +
                        "// |1-4|+|2-5|+|3-6| = 3+3+3 = 9"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
