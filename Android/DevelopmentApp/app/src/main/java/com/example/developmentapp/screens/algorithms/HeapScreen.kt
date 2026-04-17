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
fun HeapScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Heap",
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

            item {
                SectionCard("What Is a Heap?") {
                    BodyText("A heap is a complete binary tree stored as an array, satisfying the heap property. In a max-heap, every node's value is ≥ its children's values, so the maximum is always at the root. In a min-heap, every node's value is ≤ its children's, so the minimum is at the root.")
                    BodyText("\"Complete binary tree\" means all levels are fully filled except possibly the last, which is filled left-to-right. This shape makes array storage efficient: for a node at index i (0-based), its left child is at 2i+1, right child at 2i+2, and parent at (i-1)/2.")
                    CodeBlock("""
// Max-heap stored as array:
//
//         16
//        /  \
//       14   10
//      / \  / \
//     8   7 9  3
//    / \
//   2   4
//
// Array: [16, 14, 10, 8, 7, 9, 3, 2, 4]
// Index:   0   1   2  3  4  5  6  7  8
//
// Node at index i:
//   left  child = 2*i + 1
//   right child = 2*i + 2
//   parent      = (i - 1) / 2
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Peek — O(1)") {
                    BodyText("The maximum (max-heap) or minimum (min-heap) is always at index 0. Reading it is O(1) — no traversal needed.")
                    CodeBlock("""
function peek(heap):
    if heap is empty: error
    return heap[0]
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Insert — O(log n)") {
                    BodyText("Append the new element at the end (maintaining the complete-tree shape), then sift it up (also called bubble-up or heapify-up): repeatedly swap it with its parent while it violates the heap property. The number of swaps is bounded by the tree height — O(log n).")
                    CodeBlock("""
function insert(heap, value):
    heap.append(value)           // place at end — complete tree maintained
    i = heap.size - 1
    siftUp(heap, i)

function siftUp(heap, i):
    while i > 0:
        parent = (i - 1) / 2
        if heap[i] > heap[parent]:    // max-heap: child > parent → swap
            swap(heap[i], heap[parent])
            i = parent
        else:
            break                     // heap property satisfied

// Example — insert 15 into [16, 14, 10, 8, 7, 9, 3]
// Step 1: append  → [16, 14, 10, 8, 7, 9, 3, 15]
//   index of 15 = 7, parent = (7-1)/2 = 3 → heap[3] = 8
//   15 > 8 → swap → [16, 14, 10, 15, 7, 9, 3, 8]
// Step 2: i = 3, parent = (3-1)/2 = 1 → heap[1] = 14
//   15 > 14 → swap → [16, 15, 10, 14, 7, 9, 3, 8]
// Step 3: i = 1, parent = 0 → heap[0] = 16
//   15 < 16 → stop
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Extract Max/Min — O(log n)") {
                    BodyText("Remove the root (the max/min). To maintain the complete-tree shape, move the last element to the root position, then sift it down (heapify-down): repeatedly swap it with the larger (max-heap) of its two children until the heap property is restored.")
                    CodeBlock("""
function extractMax(heap):
    if heap is empty: error
    max = heap[0]
    heap[0] = heap[heap.size - 1]  // last element goes to root
    heap.removeLast()
    siftDown(heap, 0)
    return max

function siftDown(heap, i):
    n = heap.size
    while true:
        largest = i
        left    = 2*i + 1
        right   = 2*i + 2

        if left < n and heap[left] > heap[largest]:
            largest = left
        if right < n and heap[right] > heap[largest]:
            largest = right

        if largest == i:
            break              // heap property satisfied

        swap(heap[i], heap[largest])
        i = largest

// Example — extract 16 from [16, 15, 10, 14, 7, 9, 3, 8]
// Step 1: save 16, move last (8) to root → [8, 15, 10, 14, 7, 9, 3]
// Step 2: siftDown(0)
//   children: 15 (left), 10 (right) → largest = 15
//   8 < 15 → swap → [15, 8, 10, 14, 7, 9, 3]
// Step 3: siftDown(1)
//   children: 14 (left), 7 (right) → largest = 14
//   8 < 14 → swap → [15, 14, 10, 8, 7, 9, 3]
// Step 4: siftDown(3)
//   children: none within bounds → stop
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Delete Arbitrary Element — O(log n)") {
                    BodyText("To remove an element at a known index i: replace it with the last element, remove the last, then either sift up or sift down depending on whether the replacement is larger or smaller than its parent.")
                    CodeBlock("""
function delete(heap, i):
    heap[i] = heap[heap.size - 1]
    heap.removeLast()
    if i >= heap.size: return       // was the last element

    parent = (i - 1) / 2
    if i > 0 and heap[i] > heap[parent]:
        siftUp(heap, i)             // replacement is larger — move up
    else:
        siftDown(heap, i)           // replacement is smaller — move down
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Build Heap — O(n)") {
                    BodyText("Given an unordered array, you can build a valid heap in O(n) — faster than inserting n elements one-by-one (which is O(n log n)). Start from the last non-leaf node and sift each node down. Leaves are already valid heaps.")
                    CodeBlock("""
function buildHeap(array):
    n = array.size
    // Last non-leaf is at index n/2 - 1
    for i from (n/2 - 1) down to 0:
        siftDown(array, i)
    // Total work is O(n) — most siftDowns are short

// Complexity summary:
//   peek          O(1)
//   insert        O(log n)
//   extractMax    O(log n)
//   delete(i)     O(log n)
//   buildHeap     O(n)
//
// Used in: priority queues, heap sort (O(n log n)),
//          Dijkstra's algorithm, Prim's algorithm
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
