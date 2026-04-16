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
fun MoreGraphAlgorithmsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Algorithms — More Graphs",
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

            // ── Max Flow ───────────────────────────────────────────────────
            item {
                SectionCard(title = "Max Flow Problem") {
                    BodyText(
                        "Given a directed graph where each edge has a capacity (a maximum amount of flow it can carry), " +
                        "the Max Flow problem asks: what is the maximum amount of flow that can be sent from a source " +
                        "vertex s to a sink vertex t?"
                    )
                    BodyText(
                        "Two constraints apply: (1) the flow on any edge cannot exceed its capacity; " +
                        "(2) flow conservation — at every vertex except s and t, the total flow in equals the total flow out."
                    )
                    CodeBlock(
                        "Example network (capacity on each edge):\n\n" +
                        "    s --10--> A --10--> t\n" +
                        "    |                  ^\n" +
                        "    10                 |\n" +
                        "    v                  |\n" +
                        "    B ------10-------> t\n\n" +
                        "Max flow from s to t = 20\n" +
                        "(10 units via s→A→t, 10 units via s→B→t)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Ford-Fulkerson ─────────────────────────────────────────────
            item {
                SectionCard(title = "Ford-Fulkerson Algorithm") {
                    BodyText(
                        "Ford-Fulkerson finds max flow by repeatedly locating an augmenting path — a path from s to t " +
                        "in the residual graph that still has available capacity — and pushing as much flow along it as " +
                        "possible. It stops when no augmenting path exists."
                    )
                    BodyText(
                        "The residual graph tracks remaining capacity. When flow f is sent along edge u→v, the residual " +
                        "graph has: forward edge u→v with remaining capacity (cap − f), and backward edge v→u with " +
                        "capacity f. The backward edge allows 'undoing' flow in later iterations."
                    )
                    CodeBlock(
                        "FordFulkerson(graph, s, t):\n" +
                        "  max_flow = 0\n" +
                        "  build residual graph from graph\n\n" +
                        "  while augmenting path P exists in residual (DFS/BFS):\n" +
                        "    bottleneck = min capacity along P\n" +
                        "    for each edge (u,v) in P:\n" +
                        "      residual[u][v] -= bottleneck   // reduce forward\n" +
                        "      residual[v][u] += bottleneck   // increase backward\n" +
                        "    max_flow += bottleneck\n\n" +
                        "  return max_flow\n\n" +
                        "Time: O(E × max_flow) with DFS (depends on flow value)\n" +
                        "      O(V × E²)  with BFS — Edmonds-Karp variant"
                    )
                    BodyText(
                        "Ford-Fulkerson with DFS can be slow if the max flow is large (the algorithm may augment 1 unit " +
                        "at a time). The Edmonds-Karp variant fixes this by always using BFS to find the shortest " +
                        "augmenting path, giving a polynomial guarantee of O(VE²)."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Max-Flow Min-Cut ───────────────────────────────────────────
            item {
                SectionCard(title = "Max-Flow Min-Cut Theorem") {
                    BodyText(
                        "A cut is a partition of the vertices into two sets S (containing s) and T (containing t). " +
                        "The capacity of a cut is the sum of capacities of all edges going from S to T " +
                        "(edges in the reverse direction do not count)."
                    )
                    BodyText(
                        "The Max-Flow Min-Cut Theorem states: the maximum flow from s to t equals the minimum " +
                        "cut capacity over all s-t cuts. This is a fundamental duality result — it proves that " +
                        "Ford-Fulkerson terminates at the optimal solution."
                    )
                    CodeBlock(
                        "Network:  s --10--> A --10--> t\n" +
                        "          |                   ^\n" +
                        "          10                  |\n" +
                        "          v                   |\n" +
                        "          B -------10-------> t\n\n" +
                        "Cut {s} | {A, B, t}:\n" +
                        "  edges crossing: s→A (10) + s→B (10) = 20\n\n" +
                        "Cut {s, A, B} | {t}:\n" +
                        "  edges crossing: A→t (10) + B→t (10) = 20\n\n" +
                        "Min cut = 20 = Max flow  ✓"
                    )
                    BodyText(
                        "Applications: network bandwidth bottleneck analysis, bipartite matching (maximum matching " +
                        "equals minimum vertex cover by König's theorem, which is a special case of max-flow min-cut), " +
                        "project selection, image segmentation in computer vision."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Dijkstra ───────────────────────────────────────────────────
            item {
                SectionCard(title = "Dijkstra's Algorithm") {
                    BodyText(
                        "Dijkstra's algorithm finds the shortest path from a single source vertex to all other vertices " +
                        "in a graph with non-negative edge weights. It is a greedy algorithm: at each step it processes " +
                        "the unvisited vertex with the smallest known tentative distance."
                    )
                    CodeBlock(
                        "Dijkstra(graph, source):\n" +
                        "  dist[source] = 0\n" +
                        "  dist[v]      = ∞  for all other v\n" +
                        "  pq = min-heap containing (0, source)\n\n" +
                        "  while pq is not empty:\n" +
                        "    (d, u) = pq.pop_min()         // closest unvisited vertex\n\n" +
                        "    if d > dist[u]: continue      // stale entry, skip\n\n" +
                        "    for each neighbor v of u with edge weight w:\n" +
                        "      if dist[u] + w < dist[v]:\n" +
                        "        dist[v] = dist[u] + w     // relax edge\n" +
                        "        pq.push((dist[v], v))\n\n" +
                        "  return dist[]"
                    )
                    BodyText(
                        "The key insight: once a vertex is popped from the min-heap, its shortest distance is finalized. " +
                        "This is only correct when all edge weights are non-negative — if a negative edge existed, " +
                        "a later path could be shorter."
                    )
                    CodeBlock(
                        "Example graph (undirected, weighted):\n\n" +
                        "  A --1-- B --2-- D\n" +
                        "  |              |\n" +
                        "  4              1\n" +
                        "  |              |\n" +
                        "  C -----3------ D\n\n" +
                        "Dijkstra from A:\n" +
                        "  dist[A]=0, dist[B]=1, dist[C]=4, dist[D]=3\n" +
                        "  Shortest path A→D: A→B→D (weight 3)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Dijkstra complexity & limits ───────────────────────────────
            item {
                SectionCard(title = "Dijkstra — Complexity & Limitations") {
                    BodyText("Time complexity:")
                    BodyText("  • O((V + E) log V) with a binary min-heap (standard implementation)")
                    BodyText("  • O(E + V log V) with a Fibonacci heap (asymptotically optimal)")
                    BodyText("  • O(V²) with a simple array scan (efficient for dense graphs)")
                    BodyText("Limitations:")
                    BodyText(
                        "Dijkstra does NOT work with negative edge weights. If a negative edge exists, " +
                        "the greedy assumption breaks — a vertex popped from the heap may later be reachable " +
                        "via a cheaper path through a negative edge. Use Bellman-Ford (O(VE)) for graphs " +
                        "with negative weights."
                    )
                    BodyText("Real-world applications:")
                    BodyText("  • GPS/navigation systems (road networks have non-negative distances)")
                    BodyText("  • Network routing protocols — OSPF (Open Shortest Path First) runs Dijkstra")
                    BodyText("  • Pathfinding in games (A* is Dijkstra with a heuristic added)")
                    BodyText("  • Dependency resolution (e.g. package managers computing minimum-cost install order)")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
