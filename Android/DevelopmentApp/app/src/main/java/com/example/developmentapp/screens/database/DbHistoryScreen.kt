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
fun DbHistoryScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "DB History & Common Terms",
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
                SectionCard(title = "A Brief History") {
                    BodyText(
                        "In 1970, E.F. Codd at IBM published his landmark paper describing the relational model — " +
                        "data organised into tables (relations) with rows and columns, and a query language based on " +
                        "set theory. IBM prototyped this as System R in the mid-1970s, and Oracle shipped the first " +
                        "commercial relational DB in 1979.\n\n" +
                        "The 1980s–90s solidified the SQL standard (ANSI 1986) and gave us PostgreSQL, MySQL, and " +
                        "SQLite. These relational systems dominated for decades.\n\n" +
                        "In the 2000s, the web-scale era arrived. Companies like Google, Amazon, and Facebook needed " +
                        "to store billions of records across thousands of servers — far beyond what a single relational " +
                        "DB could handle. This drove the NoSQL movement: a family of databases that trade some SQL " +
                        "guarantees for horizontal scalability and flexible data models. NoSQL covers document stores " +
                        "(MongoDB), key-value stores (Redis), column-family stores (Cassandra), and graph databases " +
                        "(Neo4j)."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "What Is a Database?") {
                    BodyText(
                        "A database is a persistent, organised store of data managed by a Database Management System " +
                        "(DBMS). The key word is persistent — data survives process restarts and crashes.\n\n" +
                        "Why not just use files?\n" +
                        "• Structure and query: a DB lets you ask 'find all orders over \$100' without reading every byte\n" +
                        "• Concurrent access: multiple clients read and write safely without corrupting each other\n" +
                        "• ACID guarantees: partial writes (due to crashes) are rolled back automatically\n" +
                        "• Crash recovery: the DB replays a write-ahead log to restore a consistent state after a crash\n\n" +
                        "Example: an e-commerce site stores users, products, orders, and payments in a DB so that " +
                        "10,000 simultaneous shoppers can all read and place orders safely."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Transactions") {
                    BodyText(
                        "A transaction is a group of database operations (reads, inserts, updates, deletes) that are " +
                        "treated as a single indivisible unit. The golden rule: ALL operations succeed, or NONE of them " +
                        "take effect — there is no partial state.\n\n" +
                        "Classic example — bank transfer of \$200 from Alice to Bob:"
                    )
                    CodeBlock(
                        "BEGIN TRANSACTION;\n" +
                        "  UPDATE accounts SET balance = balance - 200 WHERE name = 'Alice';\n" +
                        "  UPDATE accounts SET balance = balance + 200 WHERE name = 'Bob';\n" +
                        "COMMIT;"
                    )
                    BodyText(
                        "If the server crashes after debiting Alice but before crediting Bob, the transaction is " +
                        "automatically rolled back. Alice's money is restored; the DB stays consistent. Without " +
                        "transactions, \$200 would simply vanish."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "ACID Principles") {
                    BodyText(
                        "ACID is the set of properties that guarantee reliable transaction processing:\n\n" +
                        "Atomicity — the transaction is all-or-nothing. If any step fails, the whole transaction is " +
                        "rolled back as if it never happened.\n\n" +
                        "Consistency — the database always moves from one valid state to another. Any invariants you " +
                        "define (e.g. 'balance must never be negative') are preserved by the transaction system.\n\n" +
                        "Isolation — concurrent transactions don't see each other's intermediate (partial) state. It " +
                        "looks as if they ran one after the other, even though they run in parallel. Different " +
                        "isolation levels (READ COMMITTED, REPEATABLE READ, SERIALIZABLE) offer different tradeoffs " +
                        "between safety and performance.\n\n" +
                        "Durability — once a transaction is committed, its changes survive crashes. The DB writes a " +
                        "log to disk before confirming the commit.\n\n" +
                        "Do all databases support ACID?\n" +
                        "Traditional SQL databases (PostgreSQL, MySQL, Oracle) provide full ACID. Many NoSQL databases " +
                        "deliberately weaken some guarantees to gain speed or availability. For example, Cassandra " +
                        "uses 'eventual consistency' — after a write, all replicas will eventually agree, but a read " +
                        "immediately after a write might return stale data. This is a deliberate tradeoff: you get " +
                        "extremely high write throughput, but you must design your application to tolerate temporary " +
                        "inconsistency."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Indexing") {
                    BodyText(
                        "An index is a separate data structure (typically a B-tree or hash table) that the database " +
                        "maintains alongside your data. It maps column values to the physical location of matching " +
                        "rows, so the DB can find them without scanning the entire table.\n\n" +
                        "Without an index on 'email':"
                    )
                    CodeBlock("SELECT * FROM users WHERE email = 'alice@example.com';\n-- Must scan ALL rows (full table scan)")
                    BodyText("With an index on 'email':")
                    CodeBlock("-- B-tree lookup: O(log n) instead of O(n)\n-- Millions of rows → milliseconds instead of seconds")
                    BodyText(
                        "Types of indexes:\n" +
                        "• Primary index — built automatically on the primary key\n" +
                        "• Secondary index — on any other column\n" +
                        "• Composite index — spans multiple columns, e.g. (last_name, first_name)\n" +
                        "• Covering index — includes all columns the query needs; no row lookup required\n\n" +
                        "The cost:\n" +
                        "• Extra disk space (often 10–30% of table size per index)\n" +
                        "• Every INSERT, UPDATE, or DELETE must also update all relevant indexes\n" +
                        "• Rule of thumb: index columns you frequently filter, sort, or join on; don't over-index " +
                        "write-heavy tables."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Why Transactions Are Necessary") {
                    BodyText(
                        "Imagine two concurrent users each trying to buy the last concert ticket:\n\n" +
                        "Without transactions:"
                    )
                    CodeBlock(
                        "Thread A: SELECT stock FROM tickets WHERE id=1;  -- returns 1\n" +
                        "Thread B: SELECT stock FROM tickets WHERE id=1;  -- returns 1\n" +
                        "Thread A: UPDATE tickets SET stock = 0 WHERE id=1;\n" +
                        "Thread B: UPDATE tickets SET stock = 0 WHERE id=1;\n" +
                        "-- Both threads sold a ticket that didn't exist!"
                    )
                    BodyText("With transactions and proper isolation (SELECT FOR UPDATE):")
                    CodeBlock(
                        "Thread A: BEGIN;\n" +
                        "  SELECT stock FROM tickets WHERE id=1 FOR UPDATE; -- locks row, returns 1\n" +
                        "  UPDATE tickets SET stock = 0 WHERE id=1;\n" +
                        "  COMMIT;\n\n" +
                        "Thread B: BEGIN;\n" +
                        "  SELECT stock FROM tickets WHERE id=1 FOR UPDATE; -- waits for A to finish\n" +
                        "  -- now sees stock = 0, knows it cannot sell\n" +
                        "  ROLLBACK;"
                    )
                    BodyText(
                        "The 'all succeed or none' guarantee is what keeps the database in a meaningful state. " +
                        "Partial writes — where some operations succeed and some don't — are one of the most " +
                        "dangerous failure modes in software."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "In-Memory Databases & Persistence") {
                    BodyText(
                        "An in-memory database keeps its entire dataset in RAM rather than on disk. Reading and " +
                        "writing RAM is ~100× faster than even an SSD, so in-memory DBs can handle millions of " +
                        "operations per second. Redis and Memcached are the most widely used examples.\n\n" +
                        "The catch: RAM is volatile. When the process dies, data is gone — unless you add " +
                        "persistence.\n\n" +
                        "Redis persistence options:\n" +
                        "• RDB snapshots — Redis forks itself and writes the entire dataset to a .rdb file " +
                        "periodically (e.g. every 5 minutes). Fast restarts, but you can lose up to 5 minutes of data.\n" +
                        "• AOF (Append-Only File) — every write command is appended to a log file. On restart, Redis " +
                        "replays the log. Much safer (can configure to fsync every second), but slower and the log " +
                        "file grows large.\n" +
                        "• Both together — use RDB for fast restarts, AOF for durability.\n\n" +
                        "Memcached has no persistence at all — it is a pure cache. Use it when losing data on restart " +
                        "is acceptable (you can rebuild the cache from your primary DB)."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Distributed Databases & Redundancy") {
                    BodyText(
                        "When data grows beyond what one server can hold or serve, you distribute it across multiple " +
                        "nodes. Two core techniques:\n\n" +
                        "Replication — keep copies of the same data on multiple nodes (primary + replicas). If the " +
                        "primary fails, a replica takes over (failover). Reads can be spread across replicas. " +
                        "Challenge: keeping all copies in sync.\n\n" +
                        "Sharding (partitioning) — split the data so each node holds a different slice. E.g. users " +
                        "A–M on node 1, N–Z on node 2. Writes and reads for a user go to only one shard. " +
                        "Challenge: cross-shard queries and rebalancing when you add nodes.\n\n" +
                        "The distributed transaction problem:\n" +
                        "If a transaction touches data on multiple nodes, how do you ensure atomicity? Some brief " +
                        "approaches:\n" +
                        "• 2-Phase Commit (2PC) — a coordinator asks all nodes to 'prepare', then 'commit'. Safe " +
                        "but slow; coordinator failure can leave nodes stuck.\n" +
                        "• Paxos / Raft — consensus algorithms where nodes vote to agree on the order of operations. " +
                        "Used in etcd, CockroachDB, and Google Spanner. Slower than a single-node write but much " +
                        "safer than 2PC.\n\n" +
                        "Many NoSQL databases sidestep distributed transactions entirely by routing related data to " +
                        "the same shard (e.g. all of a user's data lives together), so most transactions stay local."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Deduplication") {
                    BodyText(
                        "Deduplication is an optimisation that eliminates storing the same piece of data more than " +
                        "once. The classic technique is content-addressed storage: hash the content and use the hash " +
                        "as the storage key. If two pieces of data produce the same hash, only one physical copy is " +
                        "kept.\n\n" +
                        "Example — backup system:"
                    )
                    CodeBlock(
                        "File block data → SHA-256 hash\n" +
                        "hash = 'a3f2...'  → already stored? Skip. : Write once.\n" +
                        "Next backup: same block → same hash → no duplicate write"
                    )
                    BodyText(
                        "Where it's used:\n" +
                        "• Backup systems (Veeam, Veritas) — deduplicate at the block level; a 100 GB backup with " +
                        "95% unchanged data might store only 5 GB of new blocks\n" +
                        "• Object storage (AWS S3, Azure Blob) — optional dedup for identical objects\n" +
                        "• Git — every file, tree, and commit is stored by its SHA-1/SHA-256 hash\n\n" +
                        "The tradeoff: every write requires hashing the data (CPU cost) and checking whether that " +
                        "hash already exists (lookup cost). For data with high duplication, the storage savings far " +
                        "outweigh this overhead."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Schemas") {
                    BodyText(
                        "A schema is the blueprint that defines the structure of data: what fields exist, what type " +
                        "each field holds, and what constraints apply.\n\n" +
                        "SQL schema example:"
                    )
                    CodeBlock(
                        "CREATE TABLE users (\n" +
                        "  id       INT          PRIMARY KEY,\n" +
                        "  email    VARCHAR(255) NOT NULL UNIQUE,\n" +
                        "  age      INT          CHECK (age >= 0),\n" +
                        "  country  CHAR(2)\n" +
                        ");"
                    )
                    BodyText(
                        "The database enforces this schema on every row. A missing required column or a wrong type is " +
                        "rejected immediately.\n\n" +
                        "Schema-less (document DB) example:\n" +
                        "MongoDB collections accept any document shape. One user document might have 'age', another " +
                        "might not. This flexibility is powerful but shifts responsibility to the application — the " +
                        "DB won't tell you if you forget a field.\n\n" +
                        "Schema evolution — the challenge:\n" +
                        "When your schema changes (e.g. you rename a column or add a NOT NULL field), you must " +
                        "migrate existing data. In SQL this is a careful ALTER TABLE operation, sometimes requiring " +
                        "downtime or careful backfill strategies. In document DBs you update documents lazily or " +
                        "write code that handles both old and new shapes during a transition period."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
