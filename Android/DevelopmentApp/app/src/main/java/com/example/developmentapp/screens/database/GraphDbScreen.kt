package com.example.developmentapp.screens.database

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
fun GraphDbScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Graph Databases",
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
                SectionCard(title = "What Is a Graph Database?") {
                    BodyText(
                        "A graph database models data as a network of nodes (entities) connected by edges " +
                        "(relationships). It is designed for data where the connections between things are just " +
                        "as important — or more important — than the things themselves.\n\n" +
                        "In a relational database, relationships are represented as foreign keys and resolved " +
                        "with JOINs. JOINs become expensive as the depth of traversal grows. A graph database " +
                        "stores the relationships directly as first-class data structures, making deep traversal " +
                        "orders of magnitude faster.\n\n" +
                        "Ideal use cases:\n" +
                        "• Social networks — who knows whom, friend-of-friend recommendations\n" +
                        "• Recommendation engines — 'users who bought X also bought Y'\n" +
                        "• Fraud detection — unusual patterns of connections between accounts\n" +
                        "• Knowledge graphs — facts and their relationships (Wikipedia's structured data)\n" +
                        "• Dependency graphs — which services / packages depend on which others\n" +
                        "• Network topology — routers, switches, and links in a data centre"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Nodes") {
                    BodyText(
                        "A node represents an entity — a person, a product, a city, an account. Nodes are the " +
                        "'things' in your graph.\n\n" +
                        "Labels (types):\n" +
                        "A node can have one or more labels, which classify what kind of entity it is. Multiple " +
                        "labels per node are fully supported — a node can be both a :Person and an :Employee " +
                        "simultaneously. Labels are used to group nodes and to filter queries efficiently.\n\n" +
                        "Properties:\n" +
                        "A node can have any number of key-value properties. Properties hold the actual data " +
                        "about the entity."
                    )
                    CodeBlock(
                        "// Neo4j Cypher — creating a node with two labels and three properties\n" +
                        "CREATE (alice:Person:Employee {\n" +
                        "  name:       \"Alice\",\n" +
                        "  age:        30,\n" +
                        "  department: \"Engineering\"\n" +
                        "})\n\n" +
                        "// A node with a single label\n" +
                        "CREATE (p:Product { name: \"Widget\", price: 9.99, stock: 100 })"
                    )
                    BodyText(
                        "Internally, Neo4j stores each node with a list of its labels, a pointer to its " +
                        "properties, and pointers to its relationships. This structure is what makes traversal " +
                        "fast — no table scans, no joins, just pointer chasing."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Edges (Relationships)") {
                    BodyText(
                        "An edge represents a relationship between two nodes. Edges are also called " +
                        "relationships in graph DB terminology.\n\n" +
                        "Type — each relationship has exactly one type (e.g. KNOWS, PURCHASED, LIVES_IN). " +
                        "Unlike nodes, a relationship cannot have multiple types. The type is the primary way " +
                        "you categorise and filter relationships.\n\n" +
                        "Direction — relationships are directional. They have a start node and an end node. " +
                        "The direction is part of the data model (ALICE -[:FOLLOWS]-> BOB means Alice follows " +
                        "Bob, not the other way around).\n\n" +
                        "Properties — like nodes, relationships can have key-value properties.\n\n" +
                        "Examples:"
                    )
                    CodeBlock(
                        "// Alice KNOWS Bob, with a 'since' property\n" +
                        "MATCH (alice:Person {name:\"Alice\"}), (bob:Person {name:\"Bob\"})\n" +
                        "CREATE (alice)-[:KNOWS {since: 2020}]->(bob)\n\n" +
                        "// A purchase relationship with amount and date\n" +
                        "MATCH (u:User {id:1}), (p:Product {id:42})\n" +
                        "CREATE (u)-[:PURCHASED {amount: 1, date: \"2024-01-15\"}]->(p)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Directionality & Bidirectional Queries") {
                    BodyText(
                        "Relationships are stored with a direction, but you can query them in any direction or " +
                        "ignore direction altogether.\n\n" +
                        "Directed query — Alice's outgoing FOLLOWS:"
                    )
                    CodeBlock(
                        "// Who does Alice follow?\n" +
                        "MATCH (alice:Person {name:\"Alice\"})-[:FOLLOWS]->(other)\n" +
                        "RETURN other.name"
                    )
                    BodyText("Reverse direction — who follows Alice?")
                    CodeBlock(
                        "MATCH (other)-[:FOLLOWS]->(alice:Person {name:\"Alice\"})\n" +
                        "RETURN other.name"
                    )
                    BodyText("Undirected — anyone connected to Alice by a FOLLOWS relationship, in either direction:")
                    CodeBlock(
                        "MATCH (alice:Person {name:\"Alice\"})-[:FOLLOWS]-(other)\n" +
                        "RETURN other.name\n" +
                        "// Note: no arrow, so both directions match"
                    )
                    BodyText(
                        "Bidirectional relationships are often modelled as two directed edges:\n" +
                        "Alice -[:FRIENDS_WITH]-> Bob AND Bob -[:FRIENDS_WITH]-> Alice\n\n" +
                        "Alternatively, query without direction (as above) and treat the relationship as mutual. " +
                        "The choice depends on your use case — if direction has semantic meaning (FOLLOWS is " +
                        "inherently directional), store it directed. If not (MARRIED_TO), you might store one " +
                        "edge and query undirected."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Querying Paths") {
                    BodyText(
                        "One of the most powerful features of graph databases is traversing paths — finding " +
                        "nodes connected through a chain of relationships, including variable-length chains.\n\n" +
                        "Fixed-length path (exactly 2 hops — friend of a friend):"
                    )
                    CodeBlock(
                        "MATCH (alice:Person {name:\"Alice\"})-[:KNOWS*2]->(fof)\n" +
                        "RETURN fof.name"
                    )
                    BodyText("Variable-length path (1 to 3 hops):")
                    CodeBlock(
                        "MATCH (alice:Person {name:\"Alice\"})-[:KNOWS*1..3]->(other)\n" +
                        "RETURN DISTINCT other.name"
                    )
                    BodyText("Shortest path between two nodes:")
                    CodeBlock(
                        "MATCH p = shortestPath(\n" +
                        "  (alice:Person {name:\"Alice\"})-[*]-(bob:Person {name:\"Bob\"})\n" +
                        ")\n" +
                        "RETURN p"
                    )
                    BodyText("Path with specific edge types — at most 3 hops via KNOWS or WORKS_WITH:")
                    CodeBlock(
                        "MATCH (start:Person {name:\"Alice\"})\n" +
                        "      -[:KNOWS|WORKS_WITH*1..3]-\n" +
                        "      (target:Person)\n" +
                        "WHERE target.department = \"Sales\"\n" +
                        "RETURN DISTINCT target.name"
                    )
                    BodyText(
                        "These path queries would require recursive CTEs or multiple self-joins in SQL — complex, " +
                        "slow, and hard to read. In a graph database they are first-class operations."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Cypher API Examples (Neo4j)") {
                    BodyText("Cypher is Neo4j's declarative query language. Its syntax is visual — nodes are () and edges are [].")
                    CodeBlock(
                        "// Create a node\n" +
                        "CREATE (n:Person {name: \"Alice\", age: 30})\n\n" +
                        "// Find all people Alice knows\n" +
                        "MATCH (a:Person {name:\"Alice\"})-[:KNOWS]->(b)\n" +
                        "RETURN b.name, b.age\n\n" +
                        "// Find people who have NOT met Alice\n" +
                        "MATCH (n:Person)\n" +
                        "WHERE NOT (n)-[:KNOWS]-(:Person {name:\"Alice\"})\n" +
                        "  AND n.name <> \"Alice\"\n" +
                        "RETURN n.name\n\n" +
                        "// Create a relationship\n" +
                        "MATCH (a:Person {name:\"Alice\"}), (b:Person {name:\"Bob\"})\n" +
                        "CREATE (a)-[:LIKES {since: 2023}]->(b)\n\n" +
                        "// Update a property\n" +
                        "MATCH (n:Person {name:\"Alice\"})\n" +
                        "SET n.age = 31\n\n" +
                        "// Delete a node and all its relationships\n" +
                        "MATCH (n:Person {name:\"Alice\"})\n" +
                        "DETACH DELETE n\n\n" +
                        "// Count friends of friends\n" +
                        "MATCH (alice:Person {name:\"Alice\"})-[:KNOWS]->(friend)-[:KNOWS]->(fof)\n" +
                        "WHERE fof <> alice\n" +
                        "RETURN fof.name, COUNT(*) AS mutualFriends\n" +
                        "ORDER BY mutualFriends DESC"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Common Graph Databases") {
                    BodyText(
                        "Neo4j\n" +
                        "The most widely used graph database. Uses the Cypher query language. Native graph " +
                        "storage (every node and relationship is stored with direct pointers). Strong ACID " +
                        "support. Community edition is free; Enterprise adds clustering and security. " +
                        "Best choice for most graph use cases.\n\n" +
                        "Amazon Neptune\n" +
                        "Managed graph database on AWS. Supports two graph models: Property Graph (via " +
                        "Gremlin query language) and RDF triples (via SPARQL, used in knowledge graphs and " +
                        "semantic web). No infrastructure to manage. Good for AWS-native stacks.\n\n" +
                        "ArangoDB\n" +
                        "Multi-model database: graph, document, and key-value in one system. Uses AQL " +
                        "(ArangoDB Query Language). Useful when you need graph capabilities but also a lot of " +
                        "document-style access in the same system.\n\n" +
                        "JanusGraph\n" +
                        "Open-source, horizontally scalable graph database. Plugs into different storage " +
                        "backends (Cassandra, HBase, BerkeleyDB) and indexing backends (Elasticsearch). " +
                        "Uses Gremlin. Suited for very large graphs that don't fit on one machine.\n\n" +
                        "Redis Graph (RedisGraph / FalkorDB)\n" +
                        "In-memory graph database built as a Redis module. Cypher-like query language. " +
                        "Extremely fast for graphs that fit in RAM. Good for real-time recommendation and fraud " +
                        "detection where sub-millisecond response is needed."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "When to Use Graph Databases") {
                    BodyText(
                        "Use a graph database when:\n" +
                        "• You need to traverse relationships deeply and frequently (3+ hops)\n" +
                        "• The relationships themselves carry meaning and data (typed edges with properties)\n" +
                        "• Your queries look like 'find all X connected to Y via Z within N steps'\n" +
                        "• The relationship pattern is the insight — fraud detection, anomaly detection\n\n" +
                        "Concrete examples:\n" +
                        "• LinkedIn's 'degree of connection' — are you 1st, 2nd, or 3rd degree with this person?\n" +
                        "• Netflix recommendations — users who watched A also watched B via WATCHED edges\n" +
                        "• Supply chain analysis — which suppliers are within 3 tiers of a disrupted component?\n" +
                        "• IT dependency mapping — if server X fails, which services are affected (transitively)?\n\n" +
                        "Avoid graph databases when:\n" +
                        "• Your data is mostly tabular with few meaningful connections\n" +
                        "• You need massive write throughput (graph DBs optimise for traversal, not bulk writes)\n" +
                        "• Your team doesn't have graph DB expertise and a SQL model works fine"
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
