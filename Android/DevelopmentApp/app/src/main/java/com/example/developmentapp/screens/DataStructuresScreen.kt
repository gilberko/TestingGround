package com.example.developmentapp.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataStructuresScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Data Structures",
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
            modifier       = Modifier.fillMaxSize().padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // 1 — Linked List
            item {
                SectionCard("Linked List") {
                    BodyText(
                        "A linked list is a sequence of nodes where each node stores a value and a pointer to the next node. " +
                        "Unlike arrays, nodes are scattered in memory — the list is navigated by following pointers."
                    )
                    CodeBlock(
                        "struct Node {\n" +
                        "    int value;\n" +
                        "    Node* next;\n" +
                        "};\n\n" +
                        "// Insert at head — O(1)\n" +
                        "void push_front(Node*& head, int val) {\n" +
                        "    Node* n = new Node{val, head};\n" +
                        "    head = n;\n" +
                        "}\n\n" +
                        "// Search — O(n)\n" +
                        "Node* find(Node* head, int val) {\n" +
                        "    for (Node* cur = head; cur; cur = cur->next)\n" +
                        "        if (cur->value == val) return cur;\n" +
                        "    return nullptr;\n" +
                        "}"
                    )
                    BodyText(
                        "A doubly-linked list adds a 'prev' pointer so you can traverse in both directions. " +
                        "Insert/delete at a known node is O(1); search is always O(n)."
                    )
                }
            }

            // 2 — Hash Map
            item {
                SectionCard("Hash Map") {
                    BodyText(
                        "A hash map stores key-value pairs. A hash function maps each key to a bucket index. " +
                        "Collisions (two keys hashing to the same bucket) are resolved by chaining (each bucket holds a linked list) " +
                        "or open addressing (probe for the next empty slot)."
                    )
                    BodyText(
                        "The load factor (entries / buckets) determines when to resize. When it grows too large the map " +
                        "rehashes all entries into a larger array. Average O(1) insert, lookup, and delete; O(n) worst-case on hash collision."
                    )
                    CodeBlock(
                        "#include <unordered_map>\n\n" +
                        "std::unordered_map<std::string, int> scores;\n" +
                        "scores[\"Alice\"] = 95;\n" +
                        "scores[\"Bob\"]   = 87;\n\n" +
                        "// Lookup — average O(1)\n" +
                        "int s = scores[\"Alice\"];  // 95\n\n" +
                        "// Check existence\n" +
                        "if (scores.count(\"Carol\") == 0) { /* not found */ }"
                    )
                }
            }

            // 3 — Heap
            item {
                SectionCard("Heap") {
                    BodyText(
                        "A heap is a complete binary tree stored compactly in an array. In a min-heap every parent " +
                        "is smaller than its children; in a max-heap every parent is larger."
                    )
                    CodeBlock(
                        "// Array layout (1-indexed for simplicity):\n" +
                        "// parent of node i  → i / 2\n" +
                        "// left child of i   → 2 * i\n" +
                        "// right child of i  → 2 * i + 1\n\n" +
                        "// Insert: append, then sift_up — O(log n)\n" +
                        "// Remove min/max: swap root with last, pop, sift_down — O(log n)\n" +
                        "// Peek min/max: heap[1] — O(1)"
                    )
                    BodyText(
                        "The standard priority queue is heap-backed. Building a heap from an unsorted array is O(n) " +
                        "via the heapify algorithm (sift-down from the last non-leaf upward)."
                    )
                    CodeBlock(
                        "#include <queue>\n\n" +
                        "std::priority_queue<int> maxHeap;  // max-heap by default\n" +
                        "std::priority_queue<int, std::vector<int>,\n" +
                        "                   std::greater<int>> minHeap;"
                    )
                }
            }

            // 4 — Queue
            item {
                SectionCard("Queue") {
                    BodyText(
                        "A queue is a FIFO (first-in, first-out) structure. Elements are enqueued at the tail " +
                        "and dequeued from the head. Common uses: BFS, task scheduling, producer-consumer buffers."
                    )
                    CodeBlock(
                        "#include <queue>\n\n" +
                        "std::queue<int> q;\n" +
                        "q.push(1);   // enqueue — O(1)\n" +
                        "q.push(2);\n" +
                        "q.push(3);\n\n" +
                        "int front = q.front();  // 1 — O(1)\n" +
                        "q.pop();                // dequeue — O(1)"
                    )
                    BodyText("Internally backed by a deque or linked list. A ring buffer (circular buffer) is the most cache-friendly implementation for fixed-capacity queues.")
                }
            }

            // 5 — Stack
            item {
                SectionCard("Stack") {
                    BodyText(
                        "A stack is a LIFO (last-in, first-out) structure. Both push and pop operate on the top. " +
                        "The CPU's call stack is the quintessential example: each function call pushes a frame, each return pops it."
                    )
                    CodeBlock(
                        "#include <stack>\n\n" +
                        "std::stack<int> s;\n" +
                        "s.push(10);  // push — O(1)\n" +
                        "s.push(20);\n" +
                        "s.push(30);\n\n" +
                        "int top = s.top();  // 30 — O(1)\n" +
                        "s.pop();            // removes 30 — O(1)"
                    )
                    BodyText("Other uses: expression parsing, undo history, depth-first search, backtracking algorithms.")
                }
            }

            // 6 — Circular Buffer
            item {
                SectionCard("Circular Buffer") {
                    BodyText(
                        "A circular buffer (ring buffer) is a fixed-size array used as a queue. Two indices — " +
                        "a read pointer and a write pointer — wrap around using modulo arithmetic."
                    )
                    CodeBlock(
                        "  [ 0 ][ 1 ][ 2 ][ 3 ][ 4 ]  size = 5\n" +
                        "         ↑                ↑\n" +
                        "       read             write\n\n" +
                        "// Empty : read_ptr == write_ptr\n" +
                        "// Full  : (write_ptr + 1) % size == read_ptr\n\n" +
                        "void enqueue(int val) {\n" +
                        "    buf[write_ptr] = val;\n" +
                        "    write_ptr = (write_ptr + 1) % size;\n" +
                        "}\n" +
                        "int dequeue() {\n" +
                        "    int val = buf[read_ptr];\n" +
                        "    read_ptr = (read_ptr + 1) % size;\n" +
                        "    return val;\n" +
                        "}"
                    )
                    BodyText(
                        "Because one slot is kept empty to distinguish full from empty, a buffer of size N holds at most N-1 elements. " +
                        "All operations are O(1) with no dynamic allocation — ideal for real-time and kernel use."
                    )
                }
            }

            // 7 — LRU Cache
            item {
                SectionCard("LRU Cache (Least Recently Used)") {
                    BodyText(
                        "An LRU cache is size-limited and evicts the least recently used item when full. " +
                        "It combines two structures:"
                    )
                    BodyText("  • A doubly-linked list ordered by access time (most recent at front, least recent at back).")
                    BodyText("  • A hash map from key to the node in the list, enabling O(1) lookup.")
                    CodeBlock(
                        "On access(key):\n" +
                        "  1. Look up node in hash map — O(1)\n" +
                        "  2. Move node to front of list  — O(1)\n" +
                        "  3. Return value\n\n" +
                        "On insert(key, value) when cache is full:\n" +
                        "  1. Remove node at back of list — O(1)  (LRU eviction)\n" +
                        "  2. Remove its key from hash map — O(1)\n" +
                        "  3. Insert new node at front, add to map — O(1)\n\n" +
                        "// Internal members:\n" +
                        "std::list<std::pair<int,int>> order;  // {key, value}\n" +
                        "std::unordered_map<int, decltype(order)::iterator> map;"
                    )
                    BodyText(
                        "The node of the accessed item is moved to the front so the list always reflects access order. " +
                        "When eviction is needed the node at the back is the least recently accessed."
                    )
                }
            }

            // 8 — Reference Counting
            item {
                SectionCard("Reference Counting — shared_ptr / unique_ptr") {
                    BodyText(
                        "Reference counting tracks how many owners a heap object has. When the count reaches zero, " +
                        "the object is automatically destroyed. C++ smart pointers implement this:"
                    )
                    CodeBlock(
                        "// unique_ptr — sole owner, no copies\n" +
                        "#include <memory>\n\n" +
                        "auto p = std::make_unique<int>(42);\n" +
                        "auto q = std::move(p);  // ownership transferred; p is now null\n\n" +
                        "// shared_ptr — shared ownership via atomic ref count\n" +
                        "auto a = std::make_shared<int>(10);\n" +
                        "auto b = a;  // ref count: 2\n" +
                        "{\n" +
                        "    auto c = a;  // ref count: 3\n" +
                        "}            // c destroyed — ref count: 2\n" +
                        "// a and b go out of scope — ref count: 0 → freed"
                    )
                    BodyText(
                        "unique_ptr has zero overhead compared to a raw pointer. shared_ptr carries a control block " +
                        "with the reference count and a weak count, updated atomically for thread safety."
                    )
                }
            }

            // 9 — Circular References & weak_ptr
            item {
                SectionCard("Circular References & weak_ptr") {
                    BodyText(
                        "When two objects hold shared_ptrs to each other, their reference counts never reach zero — " +
                        "even after all external references are gone. This is a memory leak."
                    )
                    CodeBlock(
                        "struct B;  // forward declaration\n\n" +
                        "struct A {\n" +
                        "    std::shared_ptr<B> b_ptr;\n" +
                        "};\n" +
                        "struct B {\n" +
                        "    std::shared_ptr<A> a_ptr;  // ← circular!\n" +
                        "};\n\n" +
                        "auto a = std::make_shared<A>();\n" +
                        "auto b = std::make_shared<B>();\n" +
                        "a->b_ptr = b;  // A holds B\n" +
                        "b->a_ptr = a;  // B holds A  ← cycle\n" +
                        "// When a and b go out of scope:\n" +
                        "// A's count = 1 (held by B), B's count = 1 (held by A)\n" +
                        "// Neither is ever freed. Memory leak."
                    )
                    BodyText(
                        "weak_ptr breaks the cycle. It observes a shared object without incrementing the reference count. " +
                        "To use the object you must lock() the weak_ptr, which returns a shared_ptr (or null if the object is gone)."
                    )
                    CodeBlock(
                        "struct B {\n" +
                        "    std::weak_ptr<A> a_ptr;  // weak — does not own A\n" +
                        "};\n\n" +
                        "// Now when a and b go out of scope:\n" +
                        "// A's shared count = 1 (held by outer a), B's = 1 (held by outer b)\n" +
                        "// a destroyed → A's count = 0 → A freed\n" +
                        "// b destroyed → B's count = 0 → B freed  ✓\n\n" +
                        "// Accessing via weak_ptr:\n" +
                        "if (auto locked = b->a_ptr.lock()) {\n" +
                        "    locked->doSomething();  // safe\n" +
                        "}"
                    )
                }
            }

            // 10 — Hierarchy Lookups / Page Tables
            item {
                SectionCard("Hierarchy Lookups — Memory Page Tables") {
                    BodyText(
                        "When a process accesses a virtual address, the CPU's MMU (Memory Management Unit) must translate " +
                        "it to a physical address. Modern x86-64 systems use a 4-level page table hierarchy."
                    )
                    BodyText(
                        "The CR3 control register holds the physical base address of the current process's Page Global Directory (PGD). " +
                        "Each level is an array of 512 entries (9 bits of the virtual address index into each level)."
                    )
                    CodeBlock(
                        "Virtual address (48-bit canonical form):\n\n" +
                        "Bits 47–39 → PGD index  (Page Global Directory)\n" +
                        "Bits 38–30 → PUD index  (Page Upper Directory)\n" +
                        "Bits 29–21 → PMD index  (Page Middle Directory)\n" +
                        "Bits 20–12 → PTE index  (Page Table Entry)\n" +
                        "Bits 11–0  → page offset (4 KB page)\n\n" +
                        "CR3 → PGD[idx] → PUD[idx] → PMD[idx] → PTE[idx]\n" +
                        "                                         ↓\n" +
                        "                              physical frame address\n" +
                        "                              + page offset\n" +
                        "                              = physical address"
                    )
                    BodyText(
                        "This 4-level walk happens in hardware on a TLB (Translation Lookaside Buffer) miss. " +
                        "The TLB caches recent translations to avoid repeated walks. On a context switch, " +
                        "the OS loads a new value into CR3, switching to the new process's page table — " +
                        "this is how each process gets its own private virtual address space."
                    )
                    BodyText(
                        "The same hierarchy lookup principle applies in software too: a filesystem's directory tree, " +
                        "a URL path, or a packet routing table all resolve a hierarchical address by walking an indexed tree."
                    )
                }
            }

            // 11 — Balanced Binary Trees
            item { Spacer(Modifier.height(8.dp)) }
            item {
                SectionCard("Balanced Binary Trees") {
                    BodyText(
                        "A Binary Search Tree (BST) keeps all values in sorted order: for any node, every value in its " +
                        "left subtree is smaller and every value in its right subtree is larger. This gives O(log N) " +
                        "insert and lookup — but only if the tree remains balanced. A degenerate BST (e.g. inserting " +
                        "already-sorted data) degrades into a linked list with O(N) operations. Balanced BSTs enforce " +
                        "a height invariant so the tree height stays O(log N) regardless of insertion order."
                    )
                    BodyText("2-3 Trees")
                    BodyText(
                        "A 2-3 tree is a balanced search tree where each internal node has either 2 children (and 1 key) " +
                        "or 3 children (and 2 keys). All leaves are at the same depth. Insertions may temporarily create " +
                        "a 4-node, which is split upward, propagating the balance toward the root. This guarantees height " +
                        "O(log N)."
                    )
                    CodeBlock(
                        "2-node:  [20]         3-node:  [10 | 30]\n" +
                        "        /    \\                /    |    \\\n" +
                        "      <20   >20            <10  10-30  >30\n\n" +
                        "Insert 25 into [20]:\n" +
                        "  → [20 | 25]  (becomes 3-node)\n" +
                        "Insert 30:\n" +
                        "  → [20 | 25 | 30]  (temporary 4-node, split)\n" +
                        "  → parent gets 25 pushed up; children become [20] and [30]"
                    )
                    BodyText("AVL Trees")
                    BodyText(
                        "An AVL tree is a self-balancing BST where each node stores a balance factor = " +
                        "height(left subtree) − height(right subtree). This must remain in {−1, 0, 1}. " +
                        "Whenever an insertion or deletion violates this, one or two rotations restore balance. " +
                        "AVL trees are very strictly balanced — heights of left and right subtrees never differ " +
                        "by more than 1 — making them faster for lookups than Red-Black trees, but requiring more " +
                        "rotations during updates."
                    )
                    CodeBlock(
                        "// Single right rotation (LL case):\n" +
                        "//   Before:      C (bf=+2)     After:    B\n" +
                        "//               /                       / \\\n" +
                        "//              B (bf=+1)               A   C\n" +
                        "//             /\n" +
                        "//            A\n" +
                        "\n" +
                        "// Double rotation (LR case): rotate B left, then C right"
                    )
                    BodyText("Red-Black Trees")
                    BodyText(
                        "A Red-Black tree is a BST where every node is colored red or black, with five invariants: " +
                        "(1) every node is red or black; (2) the root is black; (3) leaves (nil sentinels) are black; " +
                        "(4) a red node's children are both black (no two consecutive reds); " +
                        "(5) all simple paths from any node down to a leaf contain the same number of black nodes. " +
                        "These rules guarantee the tree height is at most 2·log₂(N+1). Red-Black trees are less strictly " +
                        "balanced than AVL but require fewer rotations on insert/delete — making them faster for " +
                        "write-heavy workloads. They are used in C++ std::map, Java TreeMap, and Linux's scheduler."
                    )
                    CodeBlock(
                        "// Complexity summary (all three are O(log N)):\n" +
                        "//\n" +
                        "//   Structure       Lookup     Insert     Delete\n" +
                        "//   ─────────────────────────────────────────────\n" +
                        "//   2-3 Tree        O(log N)   O(log N)   O(log N)\n" +
                        "//   AVL Tree        O(log N)   O(log N)   O(log N)\n" +
                        "//   Red-Black Tree  O(log N)   O(log N)   O(log N)\n" +
                        "//\n" +
                        "//   AVL:        stricter balance, faster lookups\n" +
                        "//   Red-Black:  looser balance, faster inserts/deletes\n" +
                        "//   2-3 Tree:   conceptually simple; B-trees generalise it"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
