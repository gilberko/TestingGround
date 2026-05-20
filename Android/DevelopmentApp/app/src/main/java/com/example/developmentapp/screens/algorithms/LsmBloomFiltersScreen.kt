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
fun LsmBloomFiltersScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "LSM Trees & Bloom Filters",
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
                SectionCard("The Problem With Large Databases") {
                    BodyText("A production database storing billions of records cannot fit in RAM. The obvious in-memory structures — a hash map or a balanced BST — break down at scale for a simple reason: they scatter reads and writes across arbitrary memory addresses. On disk, that means random I/O: the drive head must physically seek to a different location for each operation.")
                    BodyText("Hard drives perform sequential I/O roughly 100× faster than random I/O. Even SSDs, where the gap is smaller, are still significantly faster when writing sequentially. A database that minimises random disk access and maximises sequential access can achieve dramatically higher write throughput — this is the core design goal of the LSM tree.")
                    CodeBlock("""
// Random I/O: each key maps to an arbitrary disk offset
write("alice", data)  // seek to offset 0x3A7F2000
write("bob",   data)  // seek to offset 0x00C81000  <- far away
write("carol", data)  // seek to offset 0xFF12A000  <- far away again

// HDD throughput (approximate):
// Sequential write:  ~150 MB/s
// Random write (4K): ~0.5–2 MB/s  ← ~100x slower
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("The LSM Tree Approach") {
                    BodyText("The Log-Structured Merge-tree (LSM tree) inverts the usual write strategy. Instead of finding the correct location on disk and updating it in place, every write is always appended — first to memory, then to disk as a new sequential chunk. Nothing is ever modified in place.")
                    BodyText("The structure has two tiers: a mutable in-memory buffer called the MemTable, and a set of immutable, sorted, on-disk files called SSTables (Sorted String Tables). Writes are fast because they always hit memory first. Reads are more complex because a key may be in the MemTable or in any of several SSTables on disk.")
                    BodyText("Used by: LevelDB, RocksDB, Apache Cassandra, Apache HBase, and Google Bigtable.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("MemTable — Writing to Memory") {
                    BodyText("The MemTable is an in-memory sorted data structure — typically a red-black tree or a skip list — that accepts all incoming writes. Because it stays sorted, it supports efficient lookups and produces sorted output when flushed. Deletions are recorded as tombstone markers, not actual removals.")
                    BodyText("Every write is also appended to a Write-Ahead Log (WAL) on disk before being applied to the MemTable. The WAL is a plain sequential append — extremely fast. If the process crashes before the MemTable is flushed, the WAL is replayed on restart to recover unflushed writes.")
                    BodyText("Once the MemTable reaches a size threshold (e.g. 64 MB), it is frozen. A fresh MemTable accepts new writes immediately while the frozen one is flushed to disk as a new SSTable. The frozen MemTable and its WAL are discarded after the flush completes.")
                    CodeBlock("""
// MemTable operations (pseudocode)
memTable.put("alice", "data_v2")   // insert or update
memTable.put("bob",   "data_v1")
memTable.delete("carol")           // tombstone: ("carol", DELETED)

// WAL written first (sequential append to disk):
// [PUT alice data_v2]
// [PUT bob   data_v1]
// [DEL carol        ]

// When MemTable hits size threshold:
//   → freeze it, flush to new SSTable on disk
//   → new writes go to a fresh MemTable immediately
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("SSTable — Flushing to Disk") {
                    BodyText("When the MemTable is flushed, it produces an SSTable — a Sorted String Table. The data is written sequentially in ascending key order. Because the MemTable was already a sorted structure, this is a single linear scan — no random writes at all.")
                    BodyText("Once written, an SSTable is immutable. It is never modified. This makes SSTables safe to read concurrently without locking, and means writes never conflict with reads on disk.")
                    BodyText("Each SSTable also includes a sparse index (a list of key-to-byte-offset pairs, one per block) and a serialised Bloom filter — both stored in the file and loaded into memory when the SSTable is opened.")
                    CodeBlock("""
// SSTable file layout (simplified)
// ┌──────────────────────────────────────┐
// │  Data blocks (sorted by key)         │
// │  [alice → data_v2]                   │
// │  [bob   → data_v1]                   │
// │  [carol → TOMBSTONE]                 │
// │  [dave  → data_v3]                   │
// │  ...                                 │
// ├──────────────────────────────────────┤
// │  Sparse index block                  │
// │  [alice → byte offset 0]             │
// │  [dave  → byte offset 512]           │
// ├──────────────────────────────────────┤
// │  Bloom filter (serialised bit array) │
// └──────────────────────────────────────┘

// SSTables are numbered by age (oldest → newest):
//   sstable-001.sst
//   sstable-002.sst
//   sstable-003.sst  ← most recent writes
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Reading — How It Works") {
                    BodyText("A read checks locations from newest to oldest. First the MemTable (most recent writes), then SSTables from newest to oldest. The first match wins. Newer writes override older ones by definition.")
                    BodyText("If a tombstone is found first, the key is treated as deleted. If a live value is found in any SSTable, it is returned immediately — no need to check older SSTables. If the key is absent, all SSTables must be checked — this is the read amplification problem: one logical read can turn into many physical disk reads. Bloom filters (next section) and compaction (section 7) are the primary tools for reducing this cost.")
                    CodeBlock("""
function get(key):
    // 1. MemTable — in memory, fast
    if memTable.contains(key):
        v = memTable.get(key)
        return DELETED if v is tombstone else v

    // 2. SSTables, newest first
    for sstable in sstables.newestFirst():
        if sstable.bloomFilter.mightContain(key):
            v = sstable.read(key)      // disk read
            if v is tombstone: return DELETED
            if v != null:      return v

    return NOT_FOUND

// Worst case (absent key): check every SSTable
// Bloom filter eliminates most unnecessary disk reads
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Bloom Filters — Fast 'Definitely Not Here' Check") {
                    BodyText("A Bloom filter is a probabilistic data structure that answers one question cheaply: 'Is this key definitely NOT in this SSTable?' It uses a bit array of size m and k independent hash functions. It has no false negatives — if a key is present, the filter always reports it. It can have false positives — an absent key may be reported as 'possibly present', triggering an unnecessary disk read. With good parameters, the false positive rate is typically ~1%.")
                    BodyText("To add a key: compute all k hash values, each giving an index in [0, m-1]. Set the bit at each of those k positions to 1. To check a key: compute the same k hash values and inspect those k bit positions. If any bit is 0, the key is definitely absent. If all k bits are 1, the key is probably present.")
                    CodeBlock("""
// Bloom filter: m=16 bits, k=3 hash functions
// Bit positions: 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15

// Initial (all zeros):
// [0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0,  0,  0,  0,  0]

// ADD "alice" → hash positions: 1, 5, 13
// [0, 1, 0, 0, 0, 1, 0, 0, 0, 0,  0,  0,  0,  1,  0,  0]
//     ^           ^                              ^

// ADD "bob" → hash positions: 4, 7, 11
// [0, 1, 0, 0, 1, 1, 0, 1, 0, 0,  0,  1,  0,  1,  0,  0]
//              ^     ^      ^            ^

// CHECK "carol" → hash positions: 2, 9, 14
//   bit[2] = 0  ← ZERO found
//   → "carol" DEFINITELY NOT in this SSTable ✓
//   No disk read needed.

// CHECK "alice" → hash positions: 1, 5, 13
//   bit[1]=1, bit[5]=1, bit[13]=1 → all set
//   → "alice" MIGHT be present → proceed to disk read
                    """.trimIndent())
                    BodyText("Bits can never be unset. Once a bit is set to 1 by any key, it stays 1 forever — which is why false positives are possible (another key may have set those exact positions). Bloom filters do not support deletions; that is handled by tombstones in the MemTable.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Compaction — Keeping SSTables Under Control") {
                    BodyText("Over time the number of SSTables grows. More SSTables means slower reads (more files to check) and wasted space (old versions of updated keys and tombstones accumulate). Compaction is the background process that merges SSTables together, resolving duplicates and dropping tombstones.")
                    BodyText("During compaction, two or more SSTables are read simultaneously via a k-way merge and written out as a single new sorted SSTable. Because all inputs are already sorted, the merge is linear — no random access. When the same key appears in multiple inputs, only the newest version is kept. Tombstones are discarded once it is safe (no older SSTable could still hold that key).")
                    BodyText("Two common strategies: size-tiered compaction (merge SSTables of similar size; simple; Cassandra's default; good for write-heavy workloads) and level-based compaction (SSTables organised into levels L0–Ln, each level 10× larger than the previous; LevelDB/RocksDB default; better read performance and space efficiency).")
                    CodeBlock("""
// Compaction: merge SSTable-001 + SSTable-002 → SSTable-004
//
// SSTable-001 (older):  alice→v1,  carol→v1,  dave→v1
// SSTable-002 (newer):  alice→v2,  bob→v1,    carol→TOMBSTONE
//
// k-way merge result:
//   "alice":  both present → keep newest (v2)
//   "bob":    002 only → keep (v1)
//   "carol":  newest is TOMBSTONE → drop entirely
//   "dave":   001 only → keep (v1)
//
// SSTable-004 (result):  alice→v2,  bob→v1,  dave→v1
//
// SSTable-001 and SSTable-002 deleted.
// Space reclaimed. Read path is shorter.
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Other Optimizations") {
                    BodyText("Sparse Index (Fence Index): Storing an index entry for every key would use too much memory. Instead, each SSTable keeps one (key, byte-offset) entry per block (e.g. every 4 KB). To look up a key, binary-search the sparse index to find the nearest block start, then scan forward within that block. For 10 million keys at 100 bytes each (~1 GB), a full index costs ~200 MB; a sparse index costs ~5 MB — 40× smaller.")
                    BodyText("Block Compression: Data blocks are compressed before writing (LZ4 or Snappy are common in RocksDB). Sorted data compresses extremely well — adjacent keys share prefixes, values are often similar — yielding 3–5× compression ratios with minimal CPU overhead. This reduces both disk footprint and I/O bandwidth.")
                    BodyText("Key-Value Separation (WiscKey / RocksDB Titan): When values are large (images, documents), compaction copies them repeatedly and wastes I/O. With key-value separation, only keys and value pointers are kept in the LSM tree; actual values are stored in a separate append-only log. Compaction only touches keys — dramatically reducing write amplification for large values.")
                    BodyText("Level-Based Compaction in Detail: In LevelDB/RocksDB, L0 accepts freshly flushed SSTables which may have overlapping key ranges. L1 through Ln maintain non-overlapping key ranges within each level. When a level exceeds its size budget, one SSTable is merged into the next level. Because L1+ have no key overlaps, a read checks at most one SSTable per level — bounding read amplification to O(number of levels), typically 5–7 disk reads in the worst case.")
                    CodeBlock("""
// Sparse index memory example
// 10M keys × 100 bytes avg = ~1 GB data on disk
// Full index (every key):    10M × 20 bytes = 200 MB in RAM
// Sparse index (1/4KB block): ~250K × 20 bytes = 5 MB in RAM
//                                              ← 40x smaller

// Level-based compaction (RocksDB defaults):
// L0:  ≤ 4 SSTables  (overlapping keys, freshly flushed)
// L1:  ≤ 256 MB       (non-overlapping key ranges)
// L2:  ≤ 2.56 GB      (10× L1)
// L3:  ≤ 25.6 GB      (10× L2)
// L4:  ≤ 256 GB       (10× L3)
//
// Read: MemTable + at most 1 SSTable per level = ~5-7 reads
// Write amplification: ~10-30× (bytes written multiple times)
// Space amplification: ~1.1×   (much better than size-tiered)
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
