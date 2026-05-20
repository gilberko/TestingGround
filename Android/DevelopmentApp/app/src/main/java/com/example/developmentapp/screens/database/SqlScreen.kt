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
fun SqlScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "SQL (Structured Query Language)",
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
                SectionCard(title = "What Is SQL?") {
                    BodyText(
                        "SQL (Structured Query Language) is a declarative language for managing relational databases. " +
                        "You describe WHAT data you want; the database figures out HOW to retrieve it.\n\n" +
                        "Origins: E.F. Codd's relational model (1970) → IBM's SEQUEL language (1974) → ANSI SQL " +
                        "standard (1986). Every major relational database speaks some dialect of SQL, making skills " +
                        "highly transferable.\n\n" +
                        "SQL is split into sub-languages:\n" +
                        "• DML (Data Manipulation Language) — SELECT, INSERT, UPDATE, DELETE\n" +
                        "• DDL (Data Definition Language) — CREATE, ALTER, DROP\n" +
                        "• DCL (Data Control Language) — GRANT, REVOKE\n" +
                        "• TCL (Transaction Control Language) — BEGIN, COMMIT, ROLLBACK"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Tables, Rows, and Columns") {
                    BodyText(
                        "A relational database organises data into tables. Each table represents one type of entity.\n\n" +
                        "• Table — a grid of rows and columns (like a spreadsheet, but with strict types)\n" +
                        "• Row (record) — one instance of the entity\n" +
                        "• Column (field) — one attribute shared by all rows\n" +
                        "• Primary key — a column (or combination) that uniquely identifies each row\n" +
                        "• Foreign key — a column that references the primary key of another table, creating a link\n\n" +
                        "Relationship types:\n" +
                        "• 1:1 — one user has one profile\n" +
                        "• 1:many — one user has many orders\n" +
                        "• many:many — many students enrol in many courses (bridged by an enrolment table)"
                    )
                    CodeBlock(
                        "users table:          orders table:\n" +
                        "id | name | email     id | user_id | total\n" +
                        "---+------+------     ---+---------+------\n" +
                        " 1 | Alice| a@ex.com   1 |    1    | 99.00\n" +
                        " 2 | Bob  | b@ex.com   2 |    1    | 45.00\n" +
                        "                       3 |    2    | 12.00"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "When to Use SQL") {
                    BodyText(
                        "SQL databases shine when:\n" +
                        "• Your data has a fixed, well-known structure that won't change often\n" +
                        "• You need complex queries joining multiple tables\n" +
                        "• You need full ACID guarantees (banking, e-commerce, medical records)\n" +
                        "• Your team is comfortable with the mature tooling (ORMs, migration tools, BI dashboards)\n\n" +
                        "Consider alternatives when:\n" +
                        "• The data schema changes constantly (document DB may fit better)\n" +
                        "• You need extreme horizontal write throughput across many machines (Cassandra, DynamoDB)\n" +
                        "• Data is naturally a graph with deep relationship traversal (graph DB)\n" +
                        "• You need sub-millisecond latency caching (Redis)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "SELECT and Filtering") {
                    BodyText("Retrieve data from one table:")
                    CodeBlock(
                        "-- All columns\n" +
                        "SELECT * FROM users;\n\n" +
                        "-- Specific columns\n" +
                        "SELECT name, email FROM users;\n\n" +
                        "-- Filter with WHERE\n" +
                        "SELECT * FROM users WHERE age > 18;\n\n" +
                        "-- Multiple conditions\n" +
                        "SELECT * FROM orders WHERE total > 50 AND status = 'shipped';\n\n" +
                        "-- Pattern match\n" +
                        "SELECT * FROM users WHERE email LIKE '%@gmail.com';\n\n" +
                        "-- Set membership\n" +
                        "SELECT * FROM users WHERE country IN ('US', 'CA', 'GB');\n\n" +
                        "-- Range\n" +
                        "SELECT * FROM orders WHERE total BETWEEN 10 AND 100;\n\n" +
                        "-- Sort and limit\n" +
                        "SELECT * FROM products ORDER BY price DESC LIMIT 10;"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "JOINs — Querying Multiple Tables") {
                    BodyText(
                        "JOINs combine rows from two tables based on a related column.\n\n" +
                        "INNER JOIN — only rows that have a match in both tables:"
                    )
                    CodeBlock(
                        "SELECT users.name, orders.total\n" +
                        "FROM users\n" +
                        "INNER JOIN orders ON users.id = orders.user_id;"
                    )
                    BodyText("LEFT JOIN — all rows from the left table, plus matching rows from the right (NULL if no match):")
                    CodeBlock(
                        "SELECT users.name, orders.total\n" +
                        "FROM users\n" +
                        "LEFT JOIN orders ON users.id = orders.user_id;\n" +
                        "-- Users with no orders appear with total = NULL"
                    )
                    BodyText("Multiple JOINs — chaining three or more tables:")
                    CodeBlock(
                        "SELECT u.name, o.id, p.name AS product\n" +
                        "FROM users u\n" +
                        "JOIN orders o ON u.id = o.user_id\n" +
                        "JOIN order_items oi ON o.id = oi.order_id\n" +
                        "JOIN products p ON oi.product_id = p.id;"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "UNION — Combining Result Sets") {
                    BodyText(
                        "UNION stacks the results of two SELECT queries vertically. Both queries must return the " +
                        "same number of columns with compatible types.\n\n" +
                        "• UNION — removes duplicate rows\n" +
                        "• UNION ALL — keeps all rows including duplicates (faster)"
                    )
                    CodeBlock(
                        "-- All email addresses from two different tables\n" +
                        "SELECT email FROM customers\n" +
                        "UNION\n" +
                        "SELECT email FROM newsletter_subscribers;\n\n" +
                        "-- Keep duplicates (faster, use when you know there are none)\n" +
                        "SELECT city FROM warehouses\n" +
                        "UNION ALL\n" +
                        "SELECT city FROM stores;"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "INSERT, UPDATE, DELETE") {
                    BodyText("Adding rows:")
                    CodeBlock(
                        "INSERT INTO users (name, email, age)\n" +
                        "VALUES ('Alice', 'alice@example.com', 30);\n\n" +
                        "-- Insert multiple rows at once\n" +
                        "INSERT INTO products (name, price) VALUES\n" +
                        "  ('Widget', 9.99),\n" +
                        "  ('Gadget', 24.99);"
                    )
                    BodyText("Modifying rows:")
                    CodeBlock(
                        "UPDATE users SET age = 31 WHERE id = 1;\n\n" +
                        "-- Update multiple columns\n" +
                        "UPDATE orders SET status = 'shipped', shipped_at = NOW()\n" +
                        "WHERE id = 42;"
                    )
                    BodyText("Deleting rows:")
                    CodeBlock(
                        "DELETE FROM users WHERE id = 5;\n\n" +
                        "-- Delete based on a condition\n" +
                        "DELETE FROM sessions WHERE expires_at < NOW();"
                    )
                    BodyText(
                        "WARNING: UPDATE and DELETE without a WHERE clause affect ALL rows in the table. " +
                        "Always double-check your WHERE condition before running in production."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "DDL: CREATE TABLE, DROP TABLE, DROP DATABASE") {
                    BodyText("Creating tables:")
                    CodeBlock(
                        "CREATE TABLE products (\n" +
                        "  id          INT           PRIMARY KEY AUTO_INCREMENT,\n" +
                        "  name        VARCHAR(200)  NOT NULL,\n" +
                        "  price       DECIMAL(10,2) NOT NULL,\n" +
                        "  stock       INT           DEFAULT 0,\n" +
                        "  created_at  TIMESTAMP     DEFAULT CURRENT_TIMESTAMP\n" +
                        ");"
                    )
                    BodyText("Adding a column to an existing table:")
                    CodeBlock("ALTER TABLE products ADD COLUMN description TEXT;")
                    BodyText("Removing a table (irreversible — all data is deleted):")
                    CodeBlock("DROP TABLE old_logs;")
                    BodyText("Removing a database and all its tables:")
                    CodeBlock("DROP DATABASE my_old_database;")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Field Types") {
                    BodyText(
                        "Choosing the right type saves storage and prevents invalid data:\n\n" +
                        "Integer types:\n" +
                        "• TINYINT (1 byte, 0–255 unsigned) — flags, small counts\n" +
                        "• INT / INTEGER (4 bytes, up to ~2 billion) — most IDs and counts\n" +
                        "• BIGINT (8 bytes, up to ~9 quintillion) — IDs in very large tables\n\n" +
                        "Text types:\n" +
                        "• CHAR(n) — fixed-length string, always n bytes (fast for fixed codes like 'US')\n" +
                        "• VARCHAR(n) — variable-length up to n characters (most general use)\n" +
                        "• TEXT — unlimited length string (article bodies, HTML)\n\n" +
                        "Numeric types:\n" +
                        "• FLOAT / DOUBLE — approximate floating point (physics, ML weights)\n" +
                        "• DECIMAL(p, s) — exact decimal with p total digits, s after the decimal point " +
                        "(always use for money — FLOAT loses precision)\n\n" +
                        "Other types:\n" +
                        "• BOOLEAN — true/false (stored as 1/0 in MySQL)\n" +
                        "• DATE — 'YYYY-MM-DD'\n" +
                        "• DATETIME / TIMESTAMP — date + time; TIMESTAMP auto-adjusts for time zones in some DBs\n" +
                        "• BLOB / BYTEA — raw binary data (images, files — better to store in S3 and keep a URL)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Indexing in SQL") {
                    BodyText("Create a basic index to speed up queries on a column:")
                    CodeBlock(
                        "CREATE INDEX idx_users_email ON users(email);\n\n" +
                        "-- Unique index: also enforces no duplicate values\n" +
                        "CREATE UNIQUE INDEX idx_users_email_unique ON users(email);\n\n" +
                        "-- Composite index: useful when you filter on both columns together\n" +
                        "CREATE INDEX idx_orders_user_status ON orders(user_id, status);\n\n" +
                        "-- Remove an index\n" +
                        "DROP INDEX idx_users_email ON users;  -- MySQL syntax\n" +
                        "DROP INDEX idx_users_email;            -- PostgreSQL syntax\n\n" +
                        "-- Check if a query uses an index\n" +
                        "EXPLAIN SELECT * FROM users WHERE email = 'alice@example.com';"
                    )
                    BodyText(
                        "Composite index note: an index on (user_id, status) helps queries that filter on " +
                        "user_id alone, or on both user_id AND status — but NOT on status alone (the left-most " +
                        "prefix rule)."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Transactions in SQL") {
                    BodyText("Basic transaction:")
                    CodeBlock(
                        "BEGIN;  -- or: START TRANSACTION;\n\n" +
                        "UPDATE accounts SET balance = balance - 200 WHERE id = 1;\n" +
                        "UPDATE accounts SET balance = balance + 200 WHERE id = 2;\n\n" +
                        "COMMIT;  -- make it permanent\n" +
                        "-- or: ROLLBACK;  -- undo everything since BEGIN"
                    )
                    BodyText("Savepoints — partial rollback within a transaction:")
                    CodeBlock(
                        "BEGIN;\n" +
                        "INSERT INTO orders (...) VALUES (...);\n" +
                        "SAVEPOINT after_order;\n\n" +
                        "INSERT INTO payments (...) VALUES (...);\n" +
                        "-- Something went wrong with payment:\n" +
                        "ROLLBACK TO after_order;  -- undo payment, keep order\n\n" +
                        "-- Try a different payment method...\n" +
                        "COMMIT;"
                    )
                    BodyText(
                        "Isolation levels (from weakest to strongest):\n" +
                        "• READ UNCOMMITTED — can read uncommitted changes from other transactions (dirty reads)\n" +
                        "• READ COMMITTED — only sees committed data; default in PostgreSQL and Oracle\n" +
                        "• REPEATABLE READ — same row read twice in a transaction always returns the same value; " +
                        "default in MySQL InnoDB\n" +
                        "• SERIALIZABLE — transactions appear to run one at a time; safest, slowest"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "WARNING: SQL Injection") {
                    BodyText(
                        "SQL injection is one of the most common and devastating web vulnerabilities. It occurs when " +
                        "user-supplied input is concatenated directly into a SQL string.\n\n" +
                        "NEVER do this:"
                    )
                    CodeBlock(
                        "// Java — DANGEROUS\n" +
                        "String query = \"SELECT * FROM users WHERE email = '\" + userInput + \"'\";\n\n" +
                        "// If userInput is:  ' OR '1'='1\n" +
                        "// Query becomes: SELECT * FROM users WHERE email = '' OR '1'='1'\n" +
                        "// This returns ALL users — authentication bypassed!"
                    )
                    BodyText("ALWAYS use parameterized queries / prepared statements:")
                    CodeBlock(
                        "// Java — SAFE\n" +
                        "PreparedStatement stmt = conn.prepareStatement(\n" +
                        "    \"SELECT * FROM users WHERE email = ?\");\n" +
                        "stmt.setString(1, userInput);  // driver escapes the value safely\n" +
                        "ResultSet rs = stmt.executeQuery();\n\n" +
                        "// Python (sqlite3) — SAFE\n" +
                        "cursor.execute(\"SELECT * FROM users WHERE email = ?\", (user_input,))"
                    )
                    BodyText(
                        "The parameter placeholder (? or $1) tells the database driver to treat the value as data, " +
                        "never as SQL code. No amount of clever user input can escape this. Additionally, use the " +
                        "principle of least privilege — your app's DB user should only have the permissions it " +
                        "actually needs (SELECT, INSERT) — not DROP or DELETE on every table."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Common SQL Implementations") {
                    BodyText(
                        "MySQL / MariaDB\n" +
                        "Best for: web applications, starter projects, shared hosting\n" +
                        "Why: easy to set up, huge community, good performance for read-heavy workloads, widely " +
                        "supported by hosting providers. MariaDB is an open-source fork with extra features.\n\n" +
                        "PostgreSQL\n" +
                        "Best for: projects that need advanced features or strict SQL compliance\n" +
                        "Why: supports JSON columns, full-text search, window functions, custom types, and powerful " +
                        "extensions (PostGIS for geospatial data, pg_vector for AI embeddings). Often the default " +
                        "choice for new serious projects.\n\n" +
                        "SQLite\n" +
                        "Best for: embedded use — mobile apps (Android/iOS), desktop apps, tests\n" +
                        "Why: a single .db file, no server process needed, zero configuration. Android's Room " +
                        "library wraps SQLite. Not suitable for high-concurrency multi-user servers.\n\n" +
                        "Microsoft SQL Server\n" +
                        "Best for: Windows-centric enterprises, .NET applications\n" +
                        "Why: deep integration with Azure, Active Directory, and Visual Studio. T-SQL dialect has " +
                        "powerful stored procedure and reporting features.\n\n" +
                        "Oracle Database\n" +
                        "Best for: very large enterprises with heavy transactional workloads\n" +
                        "Why: extremely mature, battle-tested, best-in-class optimizer and partitioning. Very " +
                        "expensive licensing — usually chosen when you inherit an existing Oracle deployment or have " +
                        "specific Oracle-only features in mind."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
