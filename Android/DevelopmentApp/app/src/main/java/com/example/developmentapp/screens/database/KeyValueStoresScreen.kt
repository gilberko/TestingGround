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
fun KeyValueStoresScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Key-Value Stores",
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
                SectionCard(title = "What Is a Key-Value Store?") {
                    BodyText(
                        "A key-value store is the simplest database model: you store a value under a unique key " +
                        "and retrieve it by that key. The database treats values as opaque blobs — it doesn't " +
                        "know or care what's inside the value (a string, a JSON object, a serialised object, " +
                        "an image — anything goes).\n\n" +
                        "The API is minimal by design:"
                    )
                    CodeBlock(
                        "SET  key  value   // store\n" +
                        "GET  key          // retrieve\n" +
                        "DEL  key          // delete"
                    )
                    BodyText(
                        "This simplicity is what makes key-value stores extremely fast — there is no query " +
                        "planning, no schema parsing, no JOIN execution. Just a hash lookup. Production systems " +
                        "like Redis can handle millions of operations per second on a single node."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Aren't These Just Hash Maps?") {
                    BodyText(
                        "Conceptually yes — a key-value store is like a very large, persistent, distributed hash " +
                        "map. But production KV stores solve several hard problems that a simple HashMap in " +
                        "memory does not:\n\n" +
                        "• Data size — your dataset may be 100 GB or 10 TB; it doesn't fit in one process's " +
                        "heap. A KV store distributes data across many machines.\n\n" +
                        "• Persistence — when the process restarts, a HashMap loses everything. A KV store " +
                        "writes data to disk so it survives crashes and reboots.\n\n" +
                        "• Concurrent access — thousands of clients connect simultaneously. The KV store handles " +
                        "locking, connection pooling, and serialisation.\n\n" +
                        "• TTL (Time To Live) — a built-in expiry mechanism. Set a key to expire in 60 seconds " +
                        "and it's automatically deleted. A HashMap has no such concept.\n\n" +
                        "• Replication & failover — data is copied to backup nodes. If the primary fails, a " +
                        "replica takes over. A single HashMap is a single point of failure.\n\n" +
                        "• Network access — any service, in any language, on any machine can use the KV store " +
                        "over the network. A HashMap is process-local."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "How Key-Value Stores Are Implemented") {
                    BodyText(
                        "In-memory stores (Redis, Memcached):\n" +
                        "The primary data structure is a hash table in RAM. For Redis, the hash table maps " +
                        "string keys to one of many value types (string, list, set, hash, sorted set). " +
                        "Persistence is added on top:\n" +
                        "• RDB snapshot — forks the process and writes the full dataset to disk every N seconds\n" +
                        "• AOF (Append-Only File) — every write command is logged to disk before executing; " +
                        "on restart, the log is replayed to rebuild state\n\n" +
                        "Disk-based stores (RocksDB, LevelDB):\n" +
                        "Use an LSM-tree (Log-Structured Merge-tree). Writes always go to an in-memory buffer " +
                        "(memtable) and are flushed to disk as immutable sorted files (SSTables). Reads check " +
                        "the memtable first, then SSTables. Background compaction merges SSTables. This design " +
                        "makes sequential disk writes extremely fast.\n\n" +
                        "Distribution — sharding:\n" +
                        "To spread data across nodes, consistent hashing maps each key to a node. A hash ring " +
                        "assigns responsibility for key ranges to nodes. When you add or remove a node, only the " +
                        "keys at the boundary of that node's range need to move — not a complete reshuffle."
                    )
                    CodeBlock(
                        "// Consistent hashing concept\n" +
                        "node = hash(key) % numberOfNodes\n\n" +
                        "// Problem: if numberOfNodes changes, almost every key remaps!\n" +
                        "// Consistent hashing solves this: only key/numberOfNodes fraction remaps"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Challenges") {
                    BodyText(
                        "Hot keys — a single key that is read or written by a large fraction of all requests " +
                        "creates a bottleneck on one node. Solutions: local in-process caching, key spreading " +
                        "(store N copies under key+suffix and pick randomly), or read replicas.\n\n" +
                        "Large values — storing a 100 MB blob in Redis blocks other operations during the " +
                        "serialisation. Split large values into chunks or store large binary data in an object " +
                        "store (S3) and only keep the URL in the KV store.\n\n" +
                        "No range queries — a hash map gives O(1) lookup by exact key, but cannot efficiently " +
                        "answer 'give me all keys from user:1000 to user:2000'. If you need range queries, use " +
                        "Redis sorted sets (ZRANGEBYSCORE) or a different database type.\n\n" +
                        "Eviction — when memory is full, the KV store must evict some keys. Redis supports " +
                        "several eviction policies:\n" +
                        "• noeviction — reject writes when full (safe but your app must handle errors)\n" +
                        "• allkeys-lru — evict the least recently used key from all keys\n" +
                        "• volatile-lru — evict the least recently used key that has a TTL set\n" +
                        "• allkeys-random — evict a random key (useful for uniform access patterns)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Redundancy & Replication") {
                    BodyText(
                        "Primary-replica replication (Redis Sentinel):\n" +
                        "One primary node accepts writes. One or more replica nodes receive a copy of every write " +
                        "asynchronously. Reads can go to replicas. If the primary fails, Sentinel promotes a " +
                        "replica to primary automatically.\n\n" +
                        "Tradeoff — asynchronous vs synchronous replication:\n" +
                        "• Async (default in Redis): primary acknowledges the write immediately, then replicates " +
                        "in the background. Extremely fast, but if the primary crashes before replication " +
                        "completes, that write is lost.\n" +
                        "• Sync (WAIT command in Redis): primary waits until at least N replicas confirm the " +
                        "write. Safer, but adds latency.\n\n" +
                        "Cluster mode (Redis Cluster):\n" +
                        "Data is automatically partitioned across 16,384 hash slots distributed across multiple " +
                        "primary nodes. Each primary has one or more replicas. If a primary fails, its replica " +
                        "is promoted. This provides both horizontal scalability and high availability without a " +
                        "single point of failure."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Transactions") {
                    BodyText(
                        "Most key-value stores have limited or no transaction support — this is part of how they " +
                        "achieve their speed.\n\n" +
                        "Redis — MULTI/EXEC (optimistic transactions):"
                    )
                    CodeBlock(
                        "MULTI          // start queuing commands\n" +
                        "INCR counter\n" +
                        "SET last_seen \"2024-01-15\"\n" +
                        "EXEC           // execute all queued commands atomically\n\n" +
                        "// WATCH — optimistic locking\n" +
                        "WATCH balance\n" +
                        "MULTI\n" +
                        "DECRBY balance 100\n" +
                        "EXEC\n" +
                        "// If 'balance' changed since WATCH, EXEC returns nil (transaction aborted)\n" +
                        "// The application must retry"
                    )
                    BodyText(
                        "Important: Redis MULTI/EXEC guarantees atomicity (all commands run or none run) but " +
                        "does NOT rollback on a logic error. If one command fails (e.g. INCR on a non-integer " +
                        "value), the other commands still execute. This is different from SQL ROLLBACK.\n\n" +
                        "DynamoDB — supports ACID transactions across up to 100 items via TransactWriteItems " +
                        "and TransactGetItems. More expensive than single-item operations but provides true ACID.\n\n" +
                        "Memcached — no transaction support at all. Pure cache."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Deduplication") {
                    BodyText(
                        "Key-value stores don't have built-in deduplication — they store exactly what you give " +
                        "them. But the application layer can implement it using the KV store itself.\n\n" +
                        "Content-addressed storage pattern:"
                    )
                    CodeBlock(
                        "// Instead of: SET user:42:avatar <binary>\n" +
                        "// Hash the content:\n" +
                        "hash = sha256(imageBytes)  // e.g. 'a3f2c8...'\n\n" +
                        "// Check if already stored\n" +
                        "if NOT EXISTS blobs:a3f2c8:\n" +
                        "    SET blobs:a3f2c8 <imageBytes>\n\n" +
                        "// Store only the reference\n" +
                        "SET user:42:avatar \"blobs:a3f2c8\""
                    )
                    BodyText(
                        "This is exactly how Git stores objects, how S3 optionally deduplicates, and how " +
                        "backup systems reduce storage. The tradeoff: every write requires hashing the content " +
                        "(CPU cost) and checking existence (one extra GET). For large blobs with high duplication, " +
                        "the storage savings are substantial."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Common Key-Value Stores") {
                    BodyText(
                        "Redis\n" +
                        "In-memory with optional persistence. Rich value types: strings, lists, sets, sorted " +
                        "sets, hashes, streams. Pub/sub messaging. Lua scripting. The default choice for " +
                        "caching, rate limiting, leaderboards, session storage, and real-time features. " +
                        "Single-threaded event loop — no data races, extremely predictable latency.\n\n" +
                        "Memcached\n" +
                        "Pure in-memory cache, no persistence, no replication built-in. Multi-threaded, so " +
                        "can use all CPU cores. Simpler than Redis — only string keys and string values. " +
                        "Good choice when you want the absolute simplest cache with no other features.\n\n" +
                        "DynamoDB (AWS)\n" +
                        "Fully managed, serverless, auto-scaling. Stores items identified by a partition key " +
                        "(and optional sort key). Supports ACID transactions. Handles any scale automatically " +
                        "without tuning. Used at massive scale inside Amazon. Good for AWS-native stacks that " +
                        "need a managed, globally distributed KV/document store.\n\n" +
                        "etcd\n" +
                        "Distributed key-value store focused on strong consistency via the Raft consensus " +
                        "algorithm. All writes go through leader election. Designed for storing small amounts " +
                        "of critical configuration data reliably. Kubernetes uses etcd as its backing store " +
                        "for all cluster state (pod definitions, secrets, config maps).\n\n" +
                        "RocksDB\n" +
                        "Embedded KV store (a library, not a server). Based on LevelDB. Uses an LSM-tree for " +
                        "very high write throughput. Not accessed over the network — lives inside your process. " +
                        "Used as the storage engine inside other databases (Cassandra, TiKV, CockroachDB).\n\n" +
                        "Cassandra\n" +
                        "Wide-column store — closer to a distributed KV store where the value itself is a " +
                        "sorted map of columns. Optimised for very high write throughput across many data " +
                        "centres. Eventual consistency by default. Used at Netflix, Instagram, and Apple at " +
                        "massive scale."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "When to Use Key-Value Stores") {
                    BodyText(
                        "Excellent fit:\n" +
                        "• Caching — store expensive DB query results, rendered HTML, or API responses " +
                        "(SET key value EX 300 — expire in 5 minutes)\n" +
                        "• Session storage — each user session gets a key; expire it when the session ends\n" +
                        "• Rate limiting — INCR a counter per user per minute; reject if over the limit\n" +
                        "• Leaderboards — Redis sorted sets order users by score automatically\n" +
                        "• Pub/sub messaging — Redis channels for real-time notifications\n" +
                        "• Distributed locks — SET key 'locked' NX EX 30 (set if not exists, expire in 30s)\n" +
                        "• Configuration and feature flags — etcd stores cluster-wide config with strong consistency\n\n" +
                        "Not a good fit:\n" +
                        "• Complex queries across multiple keys or range scans on unordered data\n" +
                        "• Strong relational integrity (foreign keys, constraints, JOINs)\n" +
                        "• Data that is too large to fit in RAM and you need fast arbitrary queries"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "API Examples (Redis)") {
                    BodyText("Basic string operations:")
                    CodeBlock(
                        "SET name \"Alice\"          // store string\n" +
                        "GET name                   // -> \"Alice\"\n" +
                        "DEL name                   // delete\n" +
                        "EXISTS name                // -> 0 (gone)\n\n" +
                        "SET visits 0\n" +
                        "INCR visits                // atomic increment -> 1\n" +
                        "INCRBY visits 5            // add 5 -> 6\n\n" +
                        "SET session:abc123 \"user:42\" EX 1800  // expire in 30 min\n" +
                        "TTL session:abc123          // -> seconds remaining"
                    )
                    BodyText("List (queue / stack):")
                    CodeBlock(
                        "LPUSH tasks \"job1\" \"job2\"   // push to left\n" +
                        "RPOP tasks                  // pop from right (FIFO queue)\n" +
                        "LRANGE tasks 0 -1           // all elements"
                    )
                    BodyText("Hash (store an object's fields):")
                    CodeBlock(
                        "HSET user:1 name \"Alice\" age 30 city \"TLV\"\n" +
                        "HGET user:1 name            // -> \"Alice\"\n" +
                        "HGETALL user:1              // all fields and values\n" +
                        "HINCRBY user:1 age 1        // increment a hash field"
                    )
                    BodyText("Sorted set (leaderboard):")
                    CodeBlock(
                        "ZADD leaderboard 1500 \"alice\"\n" +
                        "ZADD leaderboard 2300 \"bob\"\n" +
                        "ZADD leaderboard 1800 \"carol\"\n\n" +
                        "ZRANGE leaderboard 0 -1 WITHSCORES    // lowest to highest\n" +
                        "ZREVRANGE leaderboard 0 2 WITHSCORES  // top 3 (highest first)\n" +
                        "ZRANK leaderboard \"alice\"              // rank (0-indexed)"
                    )
                    BodyText("Transaction:")
                    CodeBlock(
                        "MULTI\n" +
                        "DECRBY account:1 200\n" +
                        "INCRBY account:2 200\n" +
                        "EXEC"
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
