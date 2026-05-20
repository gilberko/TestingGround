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
fun WideColumnDbScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Wide-Column Databases",
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
                SectionCard("What Is a Wide-Column Database?") {
                    BodyText("A wide-column database stores data as rows, each identified by a unique row key. Each row can have a large, flexible set of columns — different rows can have entirely different columns, and new columns can be added to any row at any time without altering a schema. There are no NULLs for missing columns: if a column is absent for a row, it simply does not exist.")
                    BodyText("Columns within a row are sorted by column name (or a composite column key). This sorted ordering is a first-class feature: it enables fast range scans across columns within a single row.")
                    BodyText("Important distinction: wide-column databases are NOT the same as columnar stores (Parquet, Apache ORC, Vertica, Redshift). Columnar stores physically group all values of one column together across all rows — optimised for full-column analytics. Wide-column stores group all columns of one row together — optimised for row-level access and point lookups. Conceptually: rowKey -> (columnName -> value).")
                    CodeBlock("""
// SQL: fixed schema — every row has the same columns
// users: | id | name  | email       | age |
//        | 1  | Alice | a@mail.com  | 30  |
//        | 2  | Bob   | b@mail.com  | NULL|  ← NULL for missing

// Wide-column: each row has its own set of columns
// users:
//   row "user:1" → { name:"Alice", email:"a@mail.com",
//                    age:"30", city:"TLV" }
//   row "user:2" → { name:"Bob", phone:"555-1234" }
//                    ↑ no email, no age — they just don't exist
//   row "user:3" → { name:"Carol", email:"c@mail.com",
//                    company:"Acme", title:"CTO" }
//                    ↑ completely different columns
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("The Sensor Example — A Perfect Fit") {
                    BodyText("Consider an IoT system collecting temperature readings. Each sensor produces one reading per second — 86,400 per day. A wide-column database is the canonical choice for this.")
                    BodyText("Each sensor is a row, identified by its sensor ID as the row key. Each timestamp becomes a column name, and the temperature is the column value. Because columns are sorted, querying 'all readings for sensor_42 between 10:00 and 11:00' is a contiguous sorted range scan on a single row — no secondary index, no JOIN needed.")
                    BodyText("Offline sensors simply have no columns for those timestamps — no NULL, no placeholder, no wasted storage. Adding new sensor types with extra metadata columns (battery_level, firmware) requires no schema change — just start writing those column names.")
                    CodeBlock("""
// Row key = sensor ID
// Column name = ISO timestamp (sorted chronologically)
// Value = temperature reading

// Row "sensor_42":
// ┌────────────┬─────────────────────┬─────────────────────┐
// │ sensor_42  │ 2024-01-15T10:00:00 │ 2024-01-15T10:00:01 │
// │            │ 21.4                │ 21.5                │
// └────────────┴─────────────────────┴─────────────────────┘

// Row "sensor_17":
// ┌────────────┬─────────────────────┬─────────────────────┐
// │ sensor_17  │ 2024-01-15T10:00:00 │ 2024-01-15T10:00:02 │
// │            │ 18.9                │ 19.1                │
// └────────────┴─────────────────────┴─────────────────────┘
// Note: sensor_17 has no reading at 10:00:01
// → that column simply does not exist

// Range scan: sensor_42 readings from 10:00 to 11:00
// → single row + sorted column scan → very fast
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Column Families") {
                    BodyText("In databases like HBase and Bigtable, columns are grouped into column families. A column family is a predefined logical grouping declared when the table is created. All columns in the same family are stored together physically on disk. Families designed around access patterns give the best I/O efficiency.")
                    BodyText("Every column name has the form family:qualifier. For example, data:temperature, data:humidity, and meta:firmware_version — two qualifiers in the 'data' family and one in 'meta'. You can add any qualifier to an existing family at any time, but adding a new family requires an administrative table change.")
                    BodyText("The storage benefit: reading only the 'data' family opens only the data family's files on disk — the 'meta' family's storage is never touched. Cassandra's equivalent is the composite primary key: the partition key determines the row, and clustering columns define the sorted column dimension within that partition.")
                    CodeBlock("""
// HBase: create table with two column families
create 'sensors',
  { NAME => 'data', VERSIONS => 1, COMPRESSION => 'SNAPPY' },
  { NAME => 'meta', VERSIONS => 1 }

// Column name format: family:qualifier
//   'data:temperature'   → family=data, qualifier=temperature
//   'data:humidity'      → family=data, qualifier=humidity
//   'meta:firmware'      → family=meta, qualifier=firmware
//   'meta:location'      → family=meta, qualifier=location

// Physical storage:
//   data/ HFiles → all data:* columns for all rows
//   meta/ HFiles → all meta:* columns for all rows
//
// Reading only data:* → only data HFiles opened
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Commercial Wide-Column Databases") {
                    BodyText("Google Bigtable (2006): The original wide-column database, described in Google's landmark 2006 paper. Powers Google Search indexing, Maps, and Gmail. Rows are stored sorted by row key across a distributed cluster of tablet servers. The design directly inspired HBase and Cassandra. Available as a managed cloud service: Google Cloud Bigtable.")
                    BodyText("Apache HBase: The open-source Bigtable implementation, built on top of HDFS (Hadoop's distributed file system). The standard wide-column store in the Hadoop ecosystem. Used at scale at Facebook (messaging), Twitter, and Alibaba. Provides strong consistency guarantees via ZooKeeper coordination.")
                    BodyText("Apache Cassandra: Originally developed at Facebook. Combines Bigtable's wide-column data model with Amazon Dynamo's leaderless replication and tunable consistency. Designed for extreme write throughput and multi-datacenter geographic distribution. Uses CQL (Cassandra Query Language), which closely resembles SQL.")
                    BodyText("ScyllaDB: A drop-in C++ reimplementation of Cassandra. Uses the identical CQL API and data model but is engineered for modern hardware — NUMA-aware, fully async I/O using the Seastar framework. Achieves significantly higher throughput per node than Cassandra.")
                    BodyText("Azure Cosmos DB: Microsoft's globally distributed multi-model database. Supports a Cassandra-compatible wire protocol, meaning existing Cassandra drivers and CQL queries work against Cosmos DB without code changes. Offers five tunable consistency levels from strong to eventual.")
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Adding Records and Columns — HBase Java API") {
                    BodyText("The core write operation is a Put. You supply the row key, then call addColumn(family, qualifier, value) for each column to write. Multiple columns can be added in a single Put. Because HBase has no fixed column schema beyond families, you can introduce any new qualifier at any time — just include it in the next Put.")
                    CodeBlock("""
Configuration conf = HBaseConfiguration.create();
conf.set("hbase.zookeeper.quorum", "zk-host:2181");
Connection conn  = ConnectionFactory.createConnection(conf);
Table table = conn.getTable(TableName.valueOf("sensors"));

// Write row "sensor_42" with two data columns
Put put = new Put(Bytes.toBytes("sensor_42"));
put.addColumn(
    Bytes.toBytes("data"),           // column family
    Bytes.toBytes("temperature"),    // qualifier
    Bytes.toBytes("21.4")            // value
);
put.addColumn(
    Bytes.toBytes("data"),
    Bytes.toBytes("humidity"),
    Bytes.toBytes("55.2")
);
table.put(put);

// Add a new column qualifier — no schema change needed
Put addMeta = new Put(Bytes.toBytes("sensor_42"));
addMeta.addColumn(
    Bytes.toBytes("meta"),
    Bytes.toBytes("firmware"),
    Bytes.toBytes("v2.1.3")
);
table.put(addMeta);

table.close();
conn.close();
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Reading and Enumerating Columns — HBase Java API") {
                    BodyText("A Get retrieves a single row by key. You can request the whole row, a specific family, or a specific qualifier. The Result contains Cell objects — each cell is one column value. A Scan iterates a range of rows using a ResultScanner; you set start/stop row keys, add column family or qualifier filters, and optionally set a time range.")
                    CodeBlock("""
// Get a single row — all column families
Get get = new Get(Bytes.toBytes("sensor_42"));
Result result = table.get(get);

// Enumerate all cells
for (Cell cell : result.listCells()) {
    String family    = Bytes.toString(CellUtil.cloneFamily(cell));
    String qualifier = Bytes.toString(CellUtil.cloneQualifier(cell));
    String value     = Bytes.toString(CellUtil.cloneValue(cell));
    long   ts        = cell.getTimestamp();
    System.out.println(family + ":" + qualifier
                       + " = " + value + " @ " + ts);
}
// Output:
//   data:humidity    = 55.2   @ 1705312800000
//   data:temperature = 21.4   @ 1705312800000
//   meta:firmware    = v2.1.3 @ 1705312900000

// Scan rows "sensor_10" through "sensor_19"
Scan scan = new Scan();
scan.withStartRow(Bytes.toBytes("sensor_10"));
scan.withStopRow(Bytes.toBytes("sensor_20")); // exclusive
scan.addFamily(Bytes.toBytes("data"));        // only data:*
ResultScanner scanner = table.getScanner(scan);
for (Result r : scanner) {
    String rowKey = Bytes.toString(r.getRow());
    // process r.listCells() as above
}
scanner.close();
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Querying Columns — Cassandra CQL") {
                    BodyText("Cassandra uses CQL (Cassandra Query Language), whose syntax closely resembles SQL. The composite primary key is the key design concept: a partition key that determines which node stores the data, and optional clustering columns that are sorted within the partition.")
                    BodyText("For the sensor example: sensor_id is the partition key (all readings for one sensor land on the same node), and reading_time is the clustering column (readings are stored sorted by time). Range queries on reading_time are fast single-partition sorted scans.")
                    CodeBlock("""
-- Create table: sensor_id = partition key,
--               reading_time = clustering column (sorted)
CREATE TABLE sensor_readings (
    sensor_id    TEXT,
    reading_time TIMESTAMP,
    temperature  DOUBLE,
    humidity     DOUBLE,
    PRIMARY KEY (sensor_id, reading_time)
) WITH CLUSTERING ORDER BY (reading_time ASC);

-- Insert readings
INSERT INTO sensor_readings
    (sensor_id, reading_time, temperature, humidity)
VALUES ('sensor_42', '2024-01-15 10:00:00', 21.4, 55.2);

INSERT INTO sensor_readings
    (sensor_id, reading_time, temperature, humidity)
VALUES ('sensor_42', '2024-01-15 10:00:01', 21.5, 55.0);

-- Range query: sensor_42 readings in a 1-hour window
-- Fast: single partition + sorted range scan
SELECT reading_time, temperature
FROM   sensor_readings
WHERE  sensor_id    = 'sensor_42'
  AND  reading_time >= '2024-01-15 10:00:00'
  AND  reading_time <  '2024-01-15 11:00:00';

-- Most recent reading
SELECT temperature, humidity
FROM   sensor_readings
WHERE  sensor_id = 'sensor_42'
ORDER BY reading_time DESC
LIMIT 1;
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("When to Use Wide-Column Databases") {
                    BodyText("Good fit: time-series data (sensor readings, metrics, financial tick data, event logs — timestamps as column names is the canonical pattern); sparse data where different entities naturally have different attributes and NULLs in a fixed schema would waste significant space; high write throughput at scale (Cassandra and HBase handle millions of writes per second across a distributed cluster); workloads with a clear primary access pattern of 'get all data for entity X, optionally filtered by column range'.")
                    BodyText("Avoid when: you need complex JOINs across many row keys — there is no JOIN capability, related data from multiple row keys must be assembled in application code; you need ad-hoc aggregations (SUM, AVG, GROUP BY) across many rows — this requires a full scan and a columnar analytics store (BigQuery, Redshift) will be far more efficient; your data model is highly relational with many cross-references (use SQL or a graph database); your team needs full SQL semantics — even CQL has strict restrictions on WHERE clauses (filters must align with the primary key structure).")
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
