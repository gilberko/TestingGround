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
fun BasicGraphAlgorithmsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Algorithms — Basic Graphs",
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

            // ── Graphs ─────────────────────────────────────────────────────
            item {
                SectionCard(title = "Graphs") {
                    BodyText("A graph is a data structure consisting of vertices (nodes) connected by edges. Graphs model networks, dependencies, maps, social connections, and many other real-world relationships.")
                    BodyText("Key properties:")
                    BodyText("  • Directed vs undirected: in a directed graph (digraph) each edge has a direction (A → B). In an undirected graph edges go both ways (A — B).")
                    BodyText("  • Weighted vs unweighted: edges can carry a numeric weight (e.g. distance, cost). Algorithms differ for weighted graphs.")
                    BodyText("  • Cyclic vs acyclic: a cycle is a path that starts and ends at the same vertex. Acyclic directed graphs are called DAGs (Directed Acyclic Graphs).")
                    BodyText("Adjacency list — the most common representation. For each vertex, store a list of its neighbors:")
                    CodeBlock(
                        """
# Undirected graph with 5 vertices
graph = {
    0: [1, 2],
    1: [0, 3],
    2: [0, 4],
    3: [1],
    4: [2]
}
# Visual:
#   0 -- 1 -- 3
#   |
#   2 -- 4
                        """.trimIndent()
                    )
                    BodyText("Adjacency list uses O(V + E) space (V vertices, E edges). Adjacency matrix uses O(V²) — efficient for dense graphs, wasteful for sparse ones.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── BFS ────────────────────────────────────────────────────────
            item {
                SectionCard(title = "BFS — Breadth-First Search") {
                    BodyText("BFS explores a graph level by level. It visits all vertices at distance 1 from the start before visiting vertices at distance 2, then distance 3, and so on. It uses a queue (FIFO).")
                    BodyText("Key property: BFS finds the shortest path (fewest edges) from the start vertex to every reachable vertex in an unweighted graph.")
                    CodeBlock(
                        """
BFS(graph, start):
  visited = { start }
  queue   = [ start ]

  while queue is not empty:
    node = queue.dequeue()       // FIFO: take from front
    process(node)                // e.g. print, record distance

    for neighbor in graph[node]:
      if neighbor not in visited:
        visited.add(neighbor)
        queue.enqueue(neighbor)  // add to back
                        """.trimIndent()
                    )
                    BodyText("Why mark visited before enqueuing (not after dequeuing)? To prevent the same vertex from being added to the queue multiple times — especially important in graphs with many edges.")
                    BodyText("Complexity: O(V + E) time, O(V) space for the visited set and queue.")
                    BodyText("BFS finds the shortest path — trace back through a 'parent' map recorded during traversal:")
                    CodeBlock(
                        """
BFS_shortest_path(graph, start, goal):
  parent  = { start: None }
  queue   = [ start ]

  while queue:
    node = queue.dequeue()
    if node == goal:
      break
    for neighbor in graph[node]:
      if neighbor not in parent:
        parent[neighbor] = node
        queue.enqueue(neighbor)

  // Reconstruct path by walking parent map back
  path = []
  node = goal
  while node is not None:
    path.prepend(node)
    node = parent[node]
  return path
                        """.trimIndent()
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── DFS ────────────────────────────────────────────────────────
            item {
                SectionCard(title = "DFS — Depth-First Search") {
                    BodyText("DFS explores as far as possible along one branch before backtracking. It can be implemented recursively (using the call stack implicitly) or iteratively (using an explicit stack).")
                    BodyText("Recursive DFS:")
                    CodeBlock(
                        """
DFS(graph, node, visited):
  visited.add(node)
  process(node)                    // e.g. print

  for neighbor in graph[node]:
    if neighbor not in visited:
      DFS(graph, neighbor, visited)

// Call with:
visited = set()
DFS(graph, start, visited)
                        """.trimIndent()
                    )
                    BodyText("The recursion depth equals the longest path explored. For very deep graphs this can cause a stack overflow — use the iterative version instead.")
                    BodyText("Iterative DFS (explicit stack):")
                    CodeBlock(
                        """
DFS_iterative(graph, start):
  visited = set()
  stack   = [ start ]

  while stack is not empty:
    node = stack.pop()           // LIFO: take from top

    if node not in visited:
      visited.add(node)
      process(node)

      for neighbor in graph[node]:
        if neighbor not in visited:
          stack.push(neighbor)
                        """.trimIndent()
                    )
                    BodyText("Note: in the iterative version, visited is checked after popping (not before pushing) because the same vertex may be pushed multiple times before it is visited. Both approaches are correct.")
                    BodyText("Complexity: O(V + E) time, O(V) space.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── BFS vs DFS ─────────────────────────────────────────────────
            item {
                SectionCard(title = "BFS vs DFS") {
                    BodyText("BFS:")
                    BodyText("  + Guarantees shortest path (unweighted graph)")
                    BodyText("  + Good for finding vertices close to the start")
                    BodyText("  - Higher memory: the queue may hold an entire 'frontier' level at once (O(V) in the worst case for wide graphs)")
                    BodyText("DFS:")
                    BodyText("  + Lower memory for narrow/deep graphs (O(depth) for the call stack)")
                    BodyText("  + Simpler recursive implementation")
                    BodyText("  - Does NOT guarantee shortest path")
                    BodyText("  - Can get 'stuck' going deep if goal is near the start")
                    CodeBlock(
                        """
Graph:
  Start -- A -- B -- C -- Goal
        \_ D _/

BFS order: Start, A, D, B, C, Goal  (finds Start→A→B→C→Goal, 4 edges)
DFS order: Start, A, B, C, Goal     (may find same path, but not guaranteed)
                        """.trimIndent()
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Use Cases ─────────────────────────────────────────────────
            item {
                SectionCard(title = "Use Cases") {
                    BodyText("BFS use cases:")
                    BodyText("  • Shortest path in unweighted graphs (GPS navigation at street level, network routing)")
                    BodyText("  • Level-order traversal of trees")
                    BodyText("  • Finding all vertices within N hops (social network: friends-of-friends)")
                    BodyText("  • Web crawling (process all pages at depth 1 before depth 2)")
                    BodyText("  • Bipartite graph detection")
                    BodyText("DFS use cases:")
                    BodyText("  • Cycle detection (has the DFS revisited a vertex?)")
                    BodyText("  • Topological sort of a DAG (e.g. build systems, task scheduling)")
                    BodyText("  • Finding connected components")
                    BodyText("  • Maze solving (explore one path fully before backtracking)")
                    BodyText("  • Strongly connected components (Tarjan's / Kosaraju's algorithm)")
                    BodyText("  • Generating all permutations / combinations (backtracking search)")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
