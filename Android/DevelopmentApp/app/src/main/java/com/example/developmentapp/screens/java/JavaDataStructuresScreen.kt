package com.example.developmentapp.screens.java

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
fun JavaDataStructuresScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Java — Data Structures",
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
                SectionCard(title = "Iterator — How It Works") {
                    BodyText(
                        "java.util.Iterator<E> is the interface all iterable collections use. Key points:\n\n" +
                        "iterator() — returns an Iterator positioned BEFORE the first element. Nothing " +
                        "has been read yet; you must call next() to get the first element.\n\n" +
                        "hasNext() — returns true if there is another element. Does NOT advance.\n\n" +
                        "next() — advances the iterator AND returns the element it just passed over.\n\n" +
                        "remove() — removes the element last returned by next(). This is the only safe " +
                        "way to delete during iteration; calling collection.remove() while iterating " +
                        "throws ConcurrentModificationException.\n\n" +
                        "There is NO peek() or current() on Iterator. Save the result of next() in a " +
                        "variable if you need it more than once.\n\n" +
                        "for-each (for T item : collection) is syntactic sugar — the compiler desugars " +
                        "it to iterator() + hasNext() + next() automatically."
                    )
                    CodeBlock(
                        "import java.util.Iterator;\n\n" +
                        "List<String> list = new ArrayList<>(List.of(\"A\", \"B\", \"C\"));\n" +
                        "Iterator<String> it = list.iterator();\n" +
                        "// it is positioned BEFORE \"A\" — no element has been read yet\n\n" +
                        "it.hasNext();           // true  — does NOT advance\n" +
                        "String first = it.next(); // advances past \"A\", returns \"A\"\n" +
                        "String second = it.next();// advances past \"B\", returns \"B\"\n" +
                        "it.remove();            // removes \"B\" — the last element returned by next()\n" +
                        "// list is now [\"A\", \"C\"]\n\n" +
                        "// No peek() — save next() result if you need it twice:\n" +
                        "String item = it.next();\n" +
                        "if (item.equals(\"C\")) System.out.println(item); // reuse saved value\n\n" +
                        "// for-each compiles to:\n" +
                        "// Iterator<String> _it = list.iterator();\n" +
                        "// while (_it.hasNext()) { String s = _it.next(); ... }"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Array") {
                    BodyText(
                        "Built into the language — no import. Fixed size; cannot grow or shrink. " +
                        "Elements are contiguous in memory. Works with any type including primitives " +
                        "(no boxing overhead unlike ArrayList).\n\n" +
                        "Time: O(1) get/set by index. O(n) insert or delete (must shift elements). " +
                        "O(n) linear search. O(n log n) sort via Arrays.sort().\n" +
                        "Space: O(n)."
                    )
                    CodeBlock(
                        "import java.util.Arrays;\n\n" +
                        "// Declare and allocate\n" +
                        "int[]    arr   = new int[5];         // [0, 0, 0, 0, 0]  (default 0)\n" +
                        "String[] words = new String[3];      // [null, null, null]\n\n" +
                        "// Declare with initializer\n" +
                        "int[] primes = {2, 3, 5, 7, 11};\n\n" +
                        "// Access and mutate\n" +
                        "primes[0] = 99;\n" +
                        "int len = primes.length;  // field, not method — always fixed at creation\n\n" +
                        "// Iteration\n" +
                        "for (int x : primes) System.out.println(x);\n\n" +
                        "// 2D array\n" +
                        "int[][] grid = new int[3][4];  // 3 rows, 4 columns\n" +
                        "grid[1][2] = 42;\n\n" +
                        "// Utility (java.util.Arrays)\n" +
                        "Arrays.sort(primes);                        // in-place sort  O(n log n)\n" +
                        "int pos = Arrays.binarySearch(primes, 5);  // O(log n) after sort\n" +
                        "System.out.println(Arrays.toString(primes));// [2, 3, 5, 7, 99]\n" +
                        "int[] copy = Arrays.copyOf(primes, primes.length);\n\n" +
                        "// ── Iterator ─────────────────────────────────────────────\n" +
                        "// Arrays are NOT Iterable — no arr.iterator() method exists.\n" +
                        "// for-each on an array compiles to a plain index loop:\n" +
                        "for (int x : primes) { }  // → for (int i=0; i<primes.length; i++)\n\n" +
                        "// Stream-based iteration (java.util.Arrays.stream):\n" +
                        "Arrays.stream(primes).forEach(System.out::println);\n" +
                        "int[] evens = Arrays.stream(primes).filter(x -> x % 2 == 0).toArray();"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "String") {
                    BodyText(
                        "In java.lang — imported automatically. Immutable: every operation that " +
                        "\"changes\" a String actually creates a new object. Backed by a char array " +
                        "internally (UTF-16 encoding). String literals are interned — the JVM reuses " +
                        "the same object for equal literals. Use StringBuilder for repeated " +
                        "concatenation in a loop to avoid O(n^2) string creation.\n\n" +
                        "Time: O(1) charAt. O(n) length, contains, indexOf, substring (creates copy).\n" +
                        "Space: O(n) per String object."
                    )
                    CodeBlock(
                        "String s = \"Hello, World!\";\n\n" +
                        "// Common API\n" +
                        "s.length()                 // 13\n" +
                        "s.charAt(0)                // 'H'\n" +
                        "s.substring(7)             // \"World!\"\n" +
                        "s.substring(7, 12)         // \"World\"  (end index exclusive)\n" +
                        "s.contains(\"World\")     // true\n" +
                        "s.startsWith(\"Hello\")   // true\n" +
                        "s.indexOf('o')             // 4 (first occurrence)\n" +
                        "s.lastIndexOf('o')         // 8\n" +
                        "s.toUpperCase()            // \"HELLO, WORLD!\"\n" +
                        "s.trim()                   // strips leading/trailing whitespace\n" +
                        "s.replace('l', 'r')        // \"Herro, Worrd!\"\n" +
                        "s.split(\", \")            // [\"Hello\", \"World!\"]\n" +
                        "String.valueOf(42)         // \"42\"  (int to String)\n" +
                        "Integer.parseInt(\"42\")   // 42   (String to int)\n\n" +
                        "// Always use equals(), not == (== compares references)\n" +
                        "\"hello\".equals(\"hello\")           // true\n" +
                        "\"hello\".equalsIgnoreCase(\"HELLO\") // true\n\n" +
                        "// Efficient mutation — StringBuilder\n" +
                        "StringBuilder sb = new StringBuilder();\n" +
                        "for (int i = 0; i < 5; i++) sb.append(i);\n" +
                        "String result = sb.toString();  // \"01234\"\n" +
                        "sb.reverse();                   // in-place reverse\n" +
                        "sb.insert(0, \"[\").append(\"]\"); // wrap in brackets\n\n" +
                        "// ── Iterator ─────────────────────────────────────────────\n" +
                        "// String is NOT Iterable — no s.iterator() method exists.\n" +
                        "// Option 1: index loop with charAt\n" +
                        "for (int i = 0; i < s.length(); i++) { char c = s.charAt(i); }\n\n" +
                        "// Option 2: toCharArray() — creates a char[] copy\n" +
                        "for (char c : s.toCharArray()) System.out.print(c);\n\n" +
                        "// Option 3: chars() IntStream (Java 8)\n" +
                        "s.chars().forEach(c -> System.out.print((char) c));"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "ArrayList") {
                    BodyText(
                        "import java.util.ArrayList — dynamic array. Backed by a plain array that " +
                        "doubles in capacity when full. The most commonly used collection in Java. " +
                        "Only holds objects — primitives are autoboxed (int to Integer).\n\n" +
                        "Time: O(1) amortized add at end. O(n) add/remove in the middle (elements " +
                        "shift). O(1) get/set by index. O(n) contains/indexOf (linear scan).\n" +
                        "Space: O(n). Capacity may be up to 2x the element count after a resize."
                    )
                    CodeBlock(
                        "import java.util.ArrayList;\n" +
                        "import java.util.List;\n\n" +
                        "List<String> list = new ArrayList<>();  // prefer interface type on left\n\n" +
                        "// Add\n" +
                        "list.add(\"Alice\");      // append at end      O(1) amortized\n" +
                        "list.add(0, \"Zara\");    // insert at index 0  O(n)  shifts elements\n\n" +
                        "// Access\n" +
                        "String s = list.get(0);  // O(1)  random access\n" +
                        "list.set(0, \"Bob\");      // replace at index   O(1)\n\n" +
                        "// Remove\n" +
                        "list.remove(0);          // by index  O(n)  shifts elements\n" +
                        "list.remove(\"Alice\");   // by value  O(n)  scans then shifts\n\n" +
                        "// Info\n" +
                        "list.size();\n" +
                        "list.isEmpty();\n" +
                        "list.contains(\"Alice\"); // O(n) linear scan\n\n" +
                        "// Iterate\n" +
                        "for (String item : list) System.out.println(item);\n\n" +
                        "// Construct from existing elements (Java 9+)\n" +
                        "List<String> immutable = List.of(\"a\", \"b\", \"c\");\n" +
                        "List<String> mutable   = new ArrayList<>(immutable); // copy into mutable\n\n" +
                        "// ── Iterator ─────────────────────────────────────────────\n" +
                        "// ArrayList implements Iterable<E> — supports for-each and Iterator\n" +
                        "Iterator<String> it = list.iterator();\n" +
                        "while (it.hasNext()) {\n" +
                        "    String elem = it.next();\n" +
                        "    if (elem.equals(\"Alice\")) it.remove(); // safe; list.remove() would throw\n" +
                        "}\n\n" +
                        "// ListIterator — bidirectional, also supports add/set during iteration\n" +
                        "ListIterator<String> lit = list.listIterator(list.size()); // start at end\n" +
                        "while (lit.hasPrevious()) System.out.println(lit.previous()); // reverse"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "LinkedList") {
                    BodyText(
                        "import java.util.LinkedList — doubly-linked list. Each node stores its value " +
                        "and pointers to the previous and next nodes. Also implements Deque, so it can " +
                        "be used as a queue or stack. Random access requires traversal from head or " +
                        "tail — avoid get(index) in loops.\n\n" +
                        "Time: O(1) add/remove at head or tail. O(n) access by index. " +
                        "O(n) contains/indexOf.\n" +
                        "Space: O(n) but higher constant than ArrayList — each node allocates object " +
                        "overhead plus two pointers."
                    )
                    CodeBlock(
                        "import java.util.LinkedList;\n\n" +
                        "LinkedList<Integer> ll = new LinkedList<>();\n\n" +
                        "// Add at head or tail — O(1)\n" +
                        "ll.addFirst(10);   // [10]\n" +
                        "ll.addLast(20);    // [10, 20]\n" +
                        "ll.add(30);        // [10, 20, 30]  same as addLast\n\n" +
                        "// Remove at head or tail — O(1)\n" +
                        "ll.removeFirst();  // returns 10, list is [20, 30]\n" +
                        "ll.removeLast();   // returns 30, list is [20]\n\n" +
                        "// Peek without removing — O(1)\n" +
                        "ll.peekFirst();    // head element (null if empty)\n" +
                        "ll.peekLast();     // tail element\n\n" +
                        "// Random access — O(n) traversal; avoid in loops\n" +
                        "ll.get(0);         // slow — traverses from head\n\n" +
                        "// As a queue: offer enqueues at tail, poll dequeues from head\n" +
                        "ll.offer(5);\n" +
                        "ll.poll();\n\n" +
                        "// ── Iterator ─────────────────────────────────────────────\n" +
                        "// LinkedList implements Iterable<E> — head → tail by default\n" +
                        "for (Integer item : ll) System.out.println(item);\n\n" +
                        "// descendingIterator() — tail → head\n" +
                        "Iterator<Integer> desc = ll.descendingIterator();\n" +
                        "while (desc.hasNext()) System.out.println(desc.next());\n\n" +
                        "// ListIterator — bidirectional O(1) node traversal\n" +
                        "ListIterator<Integer> lit = ll.listIterator();\n" +
                        "while (lit.hasNext()) lit.next();         // scan forward\n" +
                        "while (lit.hasPrevious()) lit.previous(); // then scan back"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "HashSet") {
                    BodyText(
                        "import java.util.HashSet — hash table backed set. No duplicates; unordered " +
                        "(iteration order is not guaranteed). Elements must implement equals() and " +
                        "hashCode() — Java's String, Integer, etc. already do.\n\n" +
                        "Time: O(1) average add/remove/contains. O(n) worst case (hash collision " +
                        "degenerate). Space: O(n).\n\n" +
                        "Best used for fast membership testing and deduplication."
                    )
                    CodeBlock(
                        "import java.util.HashSet;\n" +
                        "import java.util.Set;\n\n" +
                        "Set<String> set = new HashSet<>();\n\n" +
                        "set.add(\"Alice\");   // true  (added)\n" +
                        "set.add(\"Bob\");     // true\n" +
                        "set.add(\"Alice\");   // false (duplicate — silently ignored)\n\n" +
                        "set.contains(\"Bob\");   // true   O(1) avg\n" +
                        "set.remove(\"Bob\");     // true   O(1) avg\n" +
                        "set.size();             // 1\n\n" +
                        "// Use case: deduplicate an array\n" +
                        "int[] nums = {1, 2, 2, 3, 3, 3};\n" +
                        "Set<Integer> seen = new HashSet<>();\n" +
                        "for (int n : nums) seen.add(n);\n" +
                        "System.out.println(seen);  // [1, 2, 3]  (order not guaranteed)\n\n" +
                        "// Set operations\n" +
                        "Set<String> a = new HashSet<>(Set.of(\"x\", \"y\", \"z\"));\n" +
                        "Set<String> b = new HashSet<>(Set.of(\"y\", \"z\", \"w\"));\n" +
                        "a.retainAll(b);  // intersection: a is now {y, z}\n" +
                        "a.addAll(b);     // union\n" +
                        "a.removeAll(b);  // difference\n\n" +
                        "// ── Iterator ─────────────────────────────────────────────\n" +
                        "// HashSet implements Iterable<E> — iteration order is arbitrary\n" +
                        "for (String s : set) System.out.println(s);  // order not guaranteed\n\n" +
                        "// Safe removal during iteration via iterator:\n" +
                        "Iterator<String> it = set.iterator();\n" +
                        "while (it.hasNext()) {\n" +
                        "    if (it.next().startsWith(\"A\")) it.remove(); // safe\n" +
                        "}\n" +
                        "// Need ordered iteration? Use LinkedHashSet (insertion order)\n" +
                        "// or TreeSet (sorted order) instead of HashSet"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "HashMap") {
                    BodyText(
                        "import java.util.HashMap — hash table of key-to-value pairs. Keys are unique; " +
                        "values can repeat. Unordered. Keys must implement equals() + hashCode(). " +
                        "Allows one null key and multiple null values.\n\n" +
                        "Time: O(1) average get/put/remove/containsKey. Space: O(n).\n\n" +
                        "The most commonly used map in Java."
                    )
                    CodeBlock(
                        "import java.util.HashMap;\n" +
                        "import java.util.Map;\n\n" +
                        "Map<String, Integer> scores = new HashMap<>();\n\n" +
                        "// Put\n" +
                        "scores.put(\"Alice\", 95);\n" +
                        "scores.put(\"Bob\",   87);\n" +
                        "scores.put(\"Alice\", 99);  // overwrites — key must be unique\n\n" +
                        "// Get\n" +
                        "scores.get(\"Alice\");             // 99\n" +
                        "scores.get(\"Zara\");              // null — key not present\n" +
                        "scores.getOrDefault(\"Zara\", 0);  // 0 — safe default\n\n" +
                        "// Check\n" +
                        "scores.containsKey(\"Bob\");   // true\n" +
                        "scores.containsValue(87);     // true  (O(n) — scans all values)\n\n" +
                        "// Remove\n" +
                        "scores.remove(\"Bob\");         // removes entry\n\n" +
                        "// Iterate over all entries\n" +
                        "for (Map.Entry<String, Integer> e : scores.entrySet()) {\n" +
                        "    System.out.println(e.getKey() + \" -> \" + e.getValue());\n" +
                        "}\n\n" +
                        "// Useful Java 8+ helpers\n" +
                        "scores.putIfAbsent(\"Carol\", 70);          // only puts if key missing\n" +
                        "scores.merge(\"Alice\", 1, Integer::sum);   // Alice's score += 1\n\n" +
                        "// ── Iterator ─────────────────────────────────────────────\n" +
                        "// HashMap is NOT directly Iterable. Use three live views:\n" +
                        "// entrySet() — iterate key + value pairs (most common)\n" +
                        "for (Map.Entry<String, Integer> e : scores.entrySet()) {\n" +
                        "    System.out.println(e.getKey() + \" = \" + e.getValue());\n" +
                        "    e.setValue(e.getValue() + 1); // can mutate value in place\n" +
                        "}\n\n" +
                        "// keySet() — keys only;  values() — values only\n" +
                        "for (String key : scores.keySet())   System.out.println(key);\n" +
                        "for (int val  : scores.values())     System.out.println(val);\n\n" +
                        "// Safe removal via entrySet iterator:\n" +
                        "Iterator<Map.Entry<String,Integer>> it = scores.entrySet().iterator();\n" +
                        "while (it.hasNext()) {\n" +
                        "    if (it.next().getValue() < 50) it.remove(); // remove low scores\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "TreeSet") {
                    BodyText(
                        "import java.util.TreeSet — red-black tree backed set. Elements are always " +
                        "kept in sorted order (natural order via Comparable, or a custom Comparator " +
                        "passed at construction). No duplicates. Extra navigation methods let you find " +
                        "the nearest element, get a range, etc.\n\n" +
                        "Time: O(log n) add/remove/contains/first/last/floor/ceiling. Space: O(n)."
                    )
                    CodeBlock(
                        "import java.util.TreeSet;\n\n" +
                        "TreeSet<Integer> ts = new TreeSet<>();\n" +
                        "ts.add(5);\n" +
                        "ts.add(2);\n" +
                        "ts.add(8);\n" +
                        "ts.add(2);  // duplicate — silently ignored\n" +
                        "System.out.println(ts);  // [2, 5, 8] — always sorted\n\n" +
                        "ts.first();   // 2   (smallest element)\n" +
                        "ts.last();    // 8   (largest element)\n" +
                        "ts.floor(6);  // 5   (largest element <= 6)\n" +
                        "ts.ceiling(6);// 8   (smallest element >= 6)\n\n" +
                        "// Range views — live (backed by the original set)\n" +
                        "ts.headSet(6);                  // [2, 5]     (strictly < 6)\n" +
                        "ts.tailSet(5);                  // [5, 8]     (>= 5)\n" +
                        "ts.subSet(2, true, 8, false);   // [2, 5]     (2 inclusive, 8 exclusive)\n\n" +
                        "// Custom comparator — descending order\n" +
                        "TreeSet<String> rev = new TreeSet<>(Comparator.reverseOrder());\n" +
                        "rev.add(\"banana\"); rev.add(\"apple\"); rev.add(\"cherry\");\n" +
                        "System.out.println(rev);  // [cherry, banana, apple]\n\n" +
                        "// ── Iterator ─────────────────────────────────────────────\n" +
                        "// TreeSet implements Iterable<E> — iterates in sorted order (ascending)\n" +
                        "for (Integer n : ts) System.out.println(n);  // 2, 5, 8\n\n" +
                        "// descendingIterator() — reverse sorted order\n" +
                        "Iterator<Integer> desc = ts.descendingIterator();\n" +
                        "while (desc.hasNext()) System.out.println(desc.next()); // 8, 5, 2\n\n" +
                        "// Range view results are also Iterable:\n" +
                        "for (Integer n : ts.subSet(2, true, 8, false)) System.out.println(n); // 2, 5"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "TreeMap") {
                    BodyText(
                        "import java.util.TreeMap — red-black tree backed map. Keys are always in " +
                        "sorted order. Like TreeSet but with key-to-value pairs. Useful when you need " +
                        "both fast lookup and sorted key access — range queries, nearest-key lookups, " +
                        "ordered iteration.\n\n" +
                        "Time: O(log n) get/put/remove/firstKey/lastKey/floorKey/ceilingKey. Space: O(n)."
                    )
                    CodeBlock(
                        "import java.util.TreeMap;\n\n" +
                        "TreeMap<String, Integer> tm = new TreeMap<>();\n" +
                        "tm.put(\"banana\", 2);\n" +
                        "tm.put(\"apple\",  5);\n" +
                        "tm.put(\"cherry\", 1);\n" +
                        "System.out.println(tm);  // {apple=5, banana=2, cherry=1} — sorted keys\n\n" +
                        "tm.firstKey();   // \"apple\"\n" +
                        "tm.lastKey();    // \"cherry\"\n\n" +
                        "// Nearest-key navigation\n" +
                        "tm.floorKey(\"blueberry\");    // \"banana\"  (largest key <= \"blueberry\")\n" +
                        "tm.ceilingKey(\"blueberry\");  // \"cherry\"  (smallest key >= \"blueberry\")\n\n" +
                        "// Range views\n" +
                        "tm.headMap(\"cherry\");  // {apple=5, banana=2}   (keys strictly < \"cherry\")\n" +
                        "tm.tailMap(\"banana\");  // {banana=2, cherry=1}  (keys >= \"banana\")\n\n" +
                        "// Get first/last entry (key + value)\n" +
                        "tm.firstEntry();   // apple=5\n" +
                        "tm.lastEntry();    // cherry=1\n\n" +
                        "// ── Iterator ─────────────────────────────────────────────\n" +
                        "// TreeMap is NOT directly Iterable. Views iterate in key-sorted order:\n" +
                        "for (Map.Entry<String, Integer> e : tm.entrySet()) {\n" +
                        "    System.out.println(e.getKey() + \" = \" + e.getValue());\n" +
                        "}  // apple=5, banana=2, cherry=1  (sorted keys)\n\n" +
                        "for (String key : tm.keySet()) System.out.println(key);   // sorted\n\n" +
                        "// Reverse key order:\n" +
                        "for (String key : tm.descendingKeySet()) System.out.println(key);\n" +
                        "// cherry, banana, apple"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Queue") {
                    BodyText(
                        "import java.util.Queue — an interface, not a class. FIFO: first in, first out. " +
                        "offer() enqueues at the tail; poll() dequeues from the head; peek() looks at " +
                        "the head without removing it. These null-returning methods are preferred over " +
                        "the exception-throwing versions (add/remove/element).\n\n" +
                        "Typically backed by ArrayDeque in modern code. LinkedList also implements " +
                        "Queue but has higher memory overhead and is slower in practice."
                    )
                    CodeBlock(
                        "import java.util.Queue;\n" +
                        "import java.util.ArrayDeque;\n\n" +
                        "Queue<String> queue = new ArrayDeque<>();\n\n" +
                        "// Enqueue at the tail — O(1) amortized\n" +
                        "queue.offer(\"first\");\n" +
                        "queue.offer(\"second\");\n" +
                        "queue.offer(\"third\");\n\n" +
                        "// Peek at head without removing — O(1)\n" +
                        "queue.peek();   // \"first\"  (null if empty)\n\n" +
                        "// Dequeue from head — O(1) amortized\n" +
                        "queue.poll();   // \"first\"  (null if empty)\n" +
                        "queue.poll();   // \"second\"\n\n" +
                        "queue.size();    // 1\n" +
                        "queue.isEmpty(); // false\n\n" +
                        "// BFS skeleton:\n" +
                        "Queue<Integer> bfsQueue = new ArrayDeque<>();\n" +
                        "bfsQueue.offer(startNode);\n" +
                        "while (!bfsQueue.isEmpty()) {\n" +
                        "    int node = bfsQueue.poll();\n" +
                        "    // process node, offer unvisited neighbors\n" +
                        "}\n\n" +
                        "// ── Iterator ─────────────────────────────────────────────\n" +
                        "// Queue implements Iterable<E> — but for-each does NOT remove elements!\n" +
                        "for (String item : queue) System.out.println(item); // queue unchanged\n" +
                        "System.out.println(queue.size()); // still has all elements\n\n" +
                        "// To drain in FIFO order, use poll():\n" +
                        "while (!queue.isEmpty()) System.out.println(queue.poll()); // removes each"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "PriorityQueue") {
                    BodyText(
                        "import java.util.PriorityQueue — binary heap. By default a min-heap: poll() " +
                        "always returns the smallest element. Provide a Comparator at construction to " +
                        "reverse the order (max-heap) or sort by any key. Does NOT maintain sorted " +
                        "order for iteration — only the head is guaranteed to be the minimum.\n\n" +
                        "Time: O(log n) offer/poll. O(1) peek. O(n) contains or remove by value.\n" +
                        "Space: O(n)."
                    )
                    CodeBlock(
                        "import java.util.PriorityQueue;\n\n" +
                        "// Min-heap (default) — smallest element at head\n" +
                        "PriorityQueue<Integer> minHeap = new PriorityQueue<>();\n" +
                        "minHeap.offer(5);\n" +
                        "minHeap.offer(1);\n" +
                        "minHeap.offer(3);\n" +
                        "minHeap.peek();  // 1 — smallest at top, O(1)\n" +
                        "minHeap.poll();  // 1 — removes smallest, O(log n)\n" +
                        "minHeap.poll();  // 3\n" +
                        "minHeap.poll();  // 5\n\n" +
                        "// Max-heap — reverseOrder comparator\n" +
                        "PriorityQueue<Integer> maxHeap =\n" +
                        "    new PriorityQueue<>(Comparator.reverseOrder());\n" +
                        "maxHeap.offer(5); maxHeap.offer(1); maxHeap.offer(3);\n" +
                        "maxHeap.poll();  // 5 — largest first\n\n" +
                        "// Custom key — shortest string first\n" +
                        "PriorityQueue<String> byLen =\n" +
                        "    new PriorityQueue<>(Comparator.comparingInt(String::length));\n" +
                        "byLen.offer(\"banana\"); byLen.offer(\"fig\"); byLen.offer(\"apple\");\n" +
                        "byLen.poll();  // \"fig\" (shortest — 3 chars)\n\n" +
                        "// ── Iterator ─────────────────────────────────────────────\n" +
                        "// WARNING: PriorityQueue iterator does NOT traverse in priority order!\n" +
                        "// It traverses the internal heap array in an unspecified order.\n" +
                        "PriorityQueue<Integer> pq = new PriorityQueue<>();\n" +
                        "pq.offer(5); pq.offer(1); pq.offer(3);\n\n" +
                        "// WRONG — for-each is NOT 1, 3, 5:\n" +
                        "for (int n : pq) System.out.print(n + \" \"); // e.g. 1 5 3 (heap array order)\n\n" +
                        "// CORRECT — drain with poll() for guaranteed priority order:\n" +
                        "while (!pq.isEmpty()) System.out.print(pq.poll() + \" \"); // 1 3 5"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "ArrayDeque") {
                    BodyText(
                        "import java.util.ArrayDeque — double-ended queue backed by a resizable " +
                        "circular array. Supports O(1) amortized add/remove at both head and tail. " +
                        "More efficient than LinkedList (no node allocation per element) and preferred " +
                        "over the legacy Stack class.\n\n" +
                        "Use it as a stack (push/pop — LIFO) or as a queue (offer/poll — FIFO). " +
                        "Does not allow null elements.\n\n" +
                        "Time: O(1) amortized for all head/tail operations. Space: O(n)."
                    )
                    CodeBlock(
                        "import java.util.ArrayDeque;\n" +
                        "import java.util.Deque;\n\n" +
                        "// ── As a stack (LIFO) ────────────────────────────────────\n" +
                        "Deque<Integer> stack = new ArrayDeque<>();\n" +
                        "stack.push(1);   // addFirst: [1]\n" +
                        "stack.push(2);   // addFirst: [2, 1]\n" +
                        "stack.push(3);   // addFirst: [3, 2, 1]\n" +
                        "stack.peek();    // 3 — top of stack, no remove  O(1)\n" +
                        "stack.pop();     // 3 — removeFirst              O(1)\n" +
                        "stack.pop();     // 2\n\n" +
                        "// ── As a queue (FIFO) ────────────────────────────────────\n" +
                        "Deque<String> queue = new ArrayDeque<>();\n" +
                        "queue.offer(\"a\");  // addLast\n" +
                        "queue.offer(\"b\");\n" +
                        "queue.offer(\"c\");\n" +
                        "queue.poll();       // \"a\" — removeFirst (FIFO)\n\n" +
                        "// ── Direct head/tail access ──────────────────────────────\n" +
                        "queue.offerFirst(\"z\");  // insert at head\n" +
                        "queue.peekFirst();       // \"z\"\n" +
                        "queue.peekLast();        // \"c\"\n" +
                        "queue.pollLast();        // \"c\" — remove from tail\n\n" +
                        "// ── Iterator ─────────────────────────────────────────────\n" +
                        "// ArrayDeque implements Iterable<E> — iterates head → tail\n" +
                        "for (Integer n : stack) System.out.println(n); // head to tail order\n\n" +
                        "// descendingIterator() — tail → head\n" +
                        "Iterator<Integer> desc = stack.descendingIterator();\n" +
                        "while (desc.hasNext()) System.out.println(desc.next());"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
