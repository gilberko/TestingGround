package com.example.developmentapp.screens.algorithms

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
fun SortingAndLookupScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Algorithms — Sorting & Lookup",
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

            // ── Selection Sort ─────────────────────────────────────────────
            item {
                SectionCard(title = "Selection Sort") {
                    BodyText(
                        "Selection sort is a greedy algorithm. On each pass it scans the entire unsorted portion " +
                        "of the array, selects the minimum element (or maximum, depending on sort direction), " +
                        "and swaps it into its final position at the front of the unsorted region."
                    )
                    BodyText(
                        "It is 'greedy' because it always makes the globally optimal choice available at that step — " +
                        "it never looks back, never changes its mind. After k passes, the first k positions " +
                        "contain the k smallest elements in sorted order."
                    )
                    CodeBlock(
                        "SelectionSort(arr, n):\n" +
                        "  for i = 0 to n-2:\n" +
                        "    minIdx = i\n" +
                        "    for j = i+1 to n-1:\n" +
                        "      if arr[j] < arr[minIdx]:\n" +
                        "        minIdx = j\n" +
                        "    swap(arr[i], arr[minIdx])\n\n" +
                        "Example:  [5, 3, 8, 1, 4]\n" +
                        "Pass 1 → find min=1 (idx 3), swap → [1, 3, 8, 5, 4]\n" +
                        "Pass 2 → find min=3 (idx 1), swap → [1, 3, 8, 5, 4]\n" +
                        "Pass 3 → find min=4 (idx 4), swap → [1, 3, 4, 5, 8]\n" +
                        "Pass 4 → find min=5 (idx 3), swap → [1, 3, 4, 5, 8]"
                    )
                    BodyText("Time: O(n²) always (inner scan never short-circuits). Space: O(1) in-place.")
                    BodyText(
                        "Selection sort makes at most n−1 swaps — useful when swap cost is high (e.g., large objects " +
                        "on disk). Otherwise, insertion sort usually outperforms it on partially sorted data."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Bubble Sort ────────────────────────────────────────────────
            item {
                SectionCard(title = "Bubble Sort") {
                    BodyText(
                        "Bubble sort works by repeatedly comparing adjacent elements and swapping them if they are " +
                        "in the wrong order. After each full pass, the largest unsorted element has 'bubbled' to its " +
                        "correct position at the end of the unsorted region."
                    )
                    CodeBlock(
                        "BubbleSort(arr, n):\n" +
                        "  for i = 0 to n-2:\n" +
                        "    swapped = false\n" +
                        "    for j = 0 to n-2-i:\n" +
                        "      if arr[j] > arr[j+1]:\n" +
                        "        swap(arr[j], arr[j+1])\n" +
                        "        swapped = true\n" +
                        "    if not swapped: break   // already sorted\n\n" +
                        "Example:  [5, 3, 8, 1]\n" +
                        "Pass 1:  5↔3 → [3,5,8,1], 8↔1 → [3,5,1,8]\n" +
                        "Pass 2:  5↔1 → [3,1,5,8]\n" +
                        "Pass 3:  3↔1 → [1,3,5,8]  ✓"
                    )
                    BodyText(
                        "The early-exit flag (swapped) makes bubble sort O(n) on an already-sorted array — " +
                        "the first pass finds no swaps and stops immediately."
                    )
                    BodyText("Time: O(n²) worst/average, O(n) best. Space: O(1) in-place.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Quick Sort ─────────────────────────────────────────────────
            item {
                SectionCard(title = "Quick Sort") {
                    BodyText(
                        "Quick sort is a divide-and-conquer algorithm. It selects a pivot element, then partitions " +
                        "the array so that every element less than the pivot ends up on the left, and every element " +
                        "greater ends up on the right. It then recursively sorts each half."
                    )
                    BodyText(
                        "The partition step is the key operation. One classic approach (Lomuto partition) works " +
                        "by scanning left-to-right and maintaining a boundary between 'small' and 'large' elements:"
                    )
                    CodeBlock(
                        "Partition(arr, low, high):       // pivot = arr[high]\n" +
                        "  pivot = arr[high]\n" +
                        "  i = low - 1                   // boundary of 'small' region\n" +
                        "  for j = low to high-1:\n" +
                        "    if arr[j] <= pivot:\n" +
                        "      i++\n" +
                        "      swap(arr[i], arr[j])\n" +
                        "  swap(arr[i+1], arr[high])     // place pivot in final position\n" +
                        "  return i+1                    // pivot index\n\n" +
                        "QuickSort(arr, low, high):\n" +
                        "  if low < high:\n" +
                        "    p = Partition(arr, low, high)\n" +
                        "    QuickSort(arr, low,  p-1)\n" +
                        "    QuickSort(arr, p+1, high)\n\n" +
                        "Example: [3, 6, 8, 10, 1, 2, 1], pivot=1\n" +
                        "After partition: [1, 1, 8, 10, 6, 2, 3]  (1 at index 1)\n" +
                        "Recurse on [1] and [8, 10, 6, 2, 3]"
                    )
                    BodyText(
                        "Average time: O(n log n) — each partition splits the array roughly in half, " +
                        "giving log n levels of recursion, each doing O(n) work. " +
                        "Worst case: O(n²) — happens when the pivot is always the smallest or largest " +
                        "element (e.g., already-sorted input with a bad pivot choice). Space: O(log n) stack."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Quick Sort — Pivot Selection ───────────────────────────────
            item {
                SectionCard(title = "Quick Sort — Choosing a Pivot") {
                    BodyText(
                        "The pivot choice is critical. A good pivot splits the array roughly in half; a bad pivot " +
                        "creates one large subarray and one tiny one, degrading to O(n²)."
                    )
                    BodyText("Common strategies:")
                    BodyText(
                        "  • First or last element — simplest to implement, but degrades badly on already-sorted " +
                        "or reverse-sorted input (a common real-world case)."
                    )
                    BodyText(
                        "  • Random element — pick a random index as pivot. Expected O(n log n) for any input. " +
                        "An adversary cannot construct a worst-case input because the pivot is unpredictable."
                    )
                    BodyText(
                        "  • Median-of-three — compare the first, middle, and last elements; use their median " +
                        "as the pivot. Avoids the sorted-input worst case and adds negligible overhead. " +
                        "This is what most production implementations (e.g., C++ std::sort) use."
                    )
                    CodeBlock(
                        "MedianOfThree(arr, low, high):\n" +
                        "  mid = (low + high) / 2\n" +
                        "  // sort arr[low], arr[mid], arr[high] in place\n" +
                        "  if arr[low] > arr[mid]:  swap(arr[low], arr[mid])\n" +
                        "  if arr[low] > arr[high]: swap(arr[low], arr[high])\n" +
                        "  if arr[mid] > arr[high]: swap(arr[mid], arr[high])\n" +
                        "  // arr[mid] is now the median — use as pivot\n" +
                        "  swap(arr[mid], arr[high-1])   // move pivot to end\n" +
                        "  return arr[high-1]"
                    )
                    BodyText(
                        "  • Median-of-medians — a deterministic algorithm that guarantees a pivot within the " +
                        "middle 30–70% of the array, ensuring O(n log n) worst case. The overhead is higher " +
                        "than random pivot in practice, so it is rarely used outside of theory."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Heap Sort ──────────────────────────────────────────────────
            item {
                SectionCard(title = "Heap Sort") {
                    BodyText(
                        "Heap sort uses a max-heap data structure to sort in-place. A max-heap is a complete " +
                        "binary tree where every parent node is greater than or equal to its children. " +
                        "When stored in an array, the parent of index i is at (i-1)/2, and its children " +
                        "are at 2i+1 (left) and 2i+2 (right)."
                    )
                    BodyText("The algorithm has two phases:")
                    BodyText(
                        "  1. Build phase — convert the unsorted array into a max-heap. We call Heapify on " +
                        "every non-leaf node from bottom to top. This takes O(n) time."
                    )
                    BodyText(
                        "  2. Extract phase — repeatedly swap the root (maximum element) with the last " +
                        "element of the heap, shrink the heap by one, and restore the heap property by " +
                        "calling Heapify on the new root. Repeat until the heap is empty. This takes O(n log n)."
                    )
                    CodeBlock(
                        "Heapify(arr, n, i):               // sift arr[i] down\n" +
                        "  largest = i\n" +
                        "  left  = 2*i + 1\n" +
                        "  right = 2*i + 2\n" +
                        "  if left  < n and arr[left]  > arr[largest]: largest = left\n" +
                        "  if right < n and arr[right] > arr[largest]: largest = right\n" +
                        "  if largest != i:\n" +
                        "    swap(arr[i], arr[largest])\n" +
                        "    Heapify(arr, n, largest)      // fix the subtree\n\n" +
                        "HeapSort(arr, n):\n" +
                        "  // Phase 1: build max-heap (start from last non-leaf)\n" +
                        "  for i = n/2 - 1 down to 0:\n" +
                        "    Heapify(arr, n, i)\n\n" +
                        "  // Phase 2: extract max one by one\n" +
                        "  for i = n-1 down to 1:\n" +
                        "    swap(arr[0], arr[i])          // move current max to end\n" +
                        "    Heapify(arr, i, 0)            // restore heap on reduced size"
                    )
                    BodyText(
                        "Time: O(n log n) guaranteed — no bad pivots, no worst case. Space: O(1) in-place. " +
                        "Downside: poor cache performance (heap accesses jump around in memory), so " +
                        "quick sort with median-of-three is usually faster in practice on typical hardware."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Binary Search ──────────────────────────────────────────────
            item {
                SectionCard(title = "Binary Search") {
                    BodyText(
                        "Binary search finds a target value in a sorted array in O(log n) time. The key insight: " +
                        "because the array is sorted, comparing the target against the middle element " +
                        "immediately eliminates half the remaining candidates."
                    )
                    CodeBlock(
                        "BinarySearch(arr, target, low, high):\n" +
                        "  while low <= high:\n" +
                        "    mid = (low + high) / 2\n" +
                        "    if arr[mid] == target:\n" +
                        "      return mid               // found\n" +
                        "    else if arr[mid] < target:\n" +
                        "      low = mid + 1            // search right half\n" +
                        "    else:\n" +
                        "      high = mid - 1           // search left half\n" +
                        "  return -1                    // not found\n\n" +
                        "Example: arr = [1, 3, 5, 7, 9, 11], target = 7\n" +
                        "  Step 1: mid=2 → arr[2]=5 < 7, search right  → low=3\n" +
                        "  Step 2: mid=4 → arr[4]=9 > 7, search left   → high=3\n" +
                        "  Step 3: mid=3 → arr[3]=7 == 7, found at idx 3  ✓\n\n" +
                        "Time: O(log n).  Array of 1 billion elements: ≤ 30 comparisons."
                    )
                    BodyText(
                        "Binary search requires the array to be sorted. If you only search once, the O(n log n) " +
                        "sort cost isn't worth it — just do a linear scan. Binary search pays off when you " +
                        "perform many searches on the same sorted collection."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Lion in the Desert ─────────────────────────────────────────
            item {
                SectionCard(title = "Binary Search — \"Lion in the Desert\"") {
                    BodyText(
                        "Imagine a lion is hiding somewhere in a 1 km desert. You want to find it. " +
                        "One approach: start at one end and walk meter by meter until you find it. " +
                        "Worst case: 1,000 steps. That is linear search."
                    )
                    BodyText(
                        "A smarter approach: go to the exact middle of the desert (500 m). Check — is the " +
                        "lion here? If not, the lion is either in the left half (0–499 m) or the right half " +
                        "(501–1,000 m). You now know which half to ignore. Go to the middle of the " +
                        "remaining half and repeat."
                    )
                    CodeBlock(
                        "Iteration 1: search [0, 1000], check middle 500\n" +
                        "Iteration 2: search [0, 500]  or [500, 1000]\n" +
                        "Iteration 3: search region of 250 m\n" +
                        "Iteration 4: search region of 125 m\n" +
                        "...\n" +
                        "After 10 steps: search region of ~1 m   (2^10 = 1024)\n\n" +
                        "For 1,000 locations:  linear = up to 1,000 steps\n" +
                        "                     binary  = up to 10 steps  (log₂ 1000 ≈ 10)"
                    )
                    BodyText(
                        "Each check halves the remaining search space. After k steps, at most n / 2^k " +
                        "candidates remain. You are done when that reaches 1, i.e., when 2^k = n, " +
                        "so k = log₂(n). This is exactly what binary search does on a sorted array: " +
                        "compare the target against the middle element, then continue in the half " +
                        "that must contain it."
                    )
                    BodyText(
                        "The analogy generalizes: any problem where you can test a midpoint and confidently " +
                        "discard half the remaining possibilities is amenable to a binary-search-style " +
                        "approach. Examples: finding a broken commit with git bisect, locating a fault " +
                        "in a sorted error log, or finding a threshold in monotonic data."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
