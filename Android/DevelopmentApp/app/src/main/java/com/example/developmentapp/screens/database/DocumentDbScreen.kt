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
fun DocumentDbScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "DocumentDB / MongoDB",
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
                SectionCard(title = "What Is JSON?") {
                    BodyText(
                        "JSON (JavaScript Object Notation) is a human-readable text format for representing " +
                        "structured data. It was originally derived from JavaScript object syntax but is now " +
                        "language-independent and universally supported.\n\n" +
                        "JSON supports these value types:\n" +
                        "• String: \"hello\"\n" +
                        "• Number: 42 or 3.14\n" +
                        "• Boolean: true / false\n" +
                        "• Null: null\n" +
                        "• Array: [1, 2, 3]\n" +
                        "• Object: {\"key\": \"value\"}\n" +
                        "• Objects and arrays can be nested to any depth\n\n" +
                        "Example — a user record in JSON:"
                    )
                    CodeBlock(
                        "{\n" +
                        "  \"id\": 1,\n" +
                        "  \"name\": \"Alice\",\n" +
                        "  \"email\": \"alice@example.com\",\n" +
                        "  \"age\": 30,\n" +
                        "  \"address\": {\n" +
                        "    \"city\": \"Tel Aviv\",\n" +
                        "    \"country\": \"IL\"\n" +
                        "  },\n" +
                        "  \"tags\": [\"admin\", \"beta-tester\"]\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Why Document Databases?") {
                    BodyText(
                        "In the real world, data often shares many common fields but also has unique extras that " +
                        "vary per record. Consider user profiles: most users have name, email, and password, but " +
                        "some have a company field, others have a date-of-birth, others have social links.\n\n" +
                        "In SQL, you must declare every column upfront. Missing values become NULLs, and adding a " +
                        "new optional field requires altering the table — which can be slow and disruptive on large " +
                        "tables.\n\n" +
                        "A document database stores each record as a self-contained JSON document. Each document " +
                        "can have its own set of fields. There's no ALTER TABLE, no NULLs for missing optional " +
                        "fields — a field simply doesn't appear in the document if it has no value.\n\n" +
                        "MongoDB is the most widely used document database. Internally it uses BSON (Binary JSON) " +
                        "which extends JSON with additional types like Date, ObjectId, and 128-bit integers."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Terminology: Database → Collection → Document") {
                    BodyText(
                        "MongoDB's hierarchy maps to SQL's hierarchy:\n\n" +
                        "  MongoDB           SQL equivalent\n" +
                        "  ─────────────     ─────────────────\n" +
                        "  Database          Database\n" +
                        "  Collection        Table\n" +
                        "  Document          Row / Record\n" +
                        "  Field             Column\n" +
                        "  _id (ObjectId)    Primary Key\n\n" +
                        "Key difference: documents in the same collection do NOT need to have the same fields. " +
                        "One document in the 'users' collection might have 5 fields; another might have 20. MongoDB " +
                        "doesn't complain — it stores whatever shape of document you give it.\n\n" +
                        "Every document automatically gets a unique _id field (a 12-byte ObjectId by default). " +
                        "ObjectIds encode the timestamp, machine ID, and a sequence number, so they are globally " +
                        "unique without a central counter."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Creating / Deleting Databases & Collections") {
                    BodyText(
                        "MongoDB creates databases and collections lazily — they spring into existence the moment " +
                        "you first insert a document. You don't need an explicit CREATE DATABASE or CREATE TABLE.\n\n" +
                        "Switch to (or create) a database:"
                    )
                    CodeBlock("use myapp")
                    BodyText("Explicitly create a collection (optional — usually not needed):")
                    CodeBlock(
                        "db.createCollection(\"users\")\n\n" +
                        "// With options (e.g. capped collection — fixed-size ring buffer)\n" +
                        "db.createCollection(\"logs\", { capped: true, size: 10485760 })"
                    )
                    BodyText("List collections in the current database:")
                    CodeBlock("db.getCollectionNames()")
                    BodyText("Drop (delete) a collection and all its documents:")
                    CodeBlock("db.users.drop()")
                    BodyText("Drop the entire current database:")
                    CodeBlock("db.dropDatabase()")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Adding, Updating, and Removing Documents") {
                    BodyText("Insert a single document:")
                    CodeBlock(
                        "db.users.insertOne({\n" +
                        "  name: \"Alice\",\n" +
                        "  email: \"alice@example.com\",\n" +
                        "  age: 30\n" +
                        "})"
                    )
                    BodyText("Insert multiple documents at once:")
                    CodeBlock(
                        "db.users.insertMany([\n" +
                        "  { name: \"Bob\",   email: \"bob@example.com\" },\n" +
                        "  { name: \"Carol\", email: \"carol@example.com\", age: 25 }\n" +
                        "])"
                    )
                    BodyText("Update — modify specific fields of matching documents:")
                    CodeBlock(
                        "// Update one document\n" +
                        "db.users.updateOne(\n" +
                        "  { email: \"alice@example.com\" },   // filter\n" +
                        "  { \$set: { age: 31, city: \"Haifa\" } }  // changes\n" +
                        ")\n\n" +
                        "// Update all matching documents\n" +
                        "db.users.updateMany(\n" +
                        "  { country: \"IL\" },\n" +
                        "  { \$set: { currency: \"ILS\" } }\n" +
                        ")\n\n" +
                        "// Increment a field\n" +
                        "db.products.updateOne({ _id: id }, { \$inc: { stock: -1 } })\n\n" +
                        "// Completely replace a document (only _id is preserved)\n" +
                        "db.users.replaceOne({ _id: id }, { name: \"Alice\", email: \"new@example.com\" })"
                    )
                    BodyText("Delete documents:")
                    CodeBlock(
                        "db.users.deleteOne({ email: \"alice@example.com\" })\n" +
                        "db.users.deleteMany({ status: \"inactive\" })"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Querying Documents") {
                    BodyText("Basic find — returns a cursor (iterable result set):")
                    CodeBlock(
                        "// All documents\n" +
                        "db.users.find()\n\n" +
                        "// With a filter\n" +
                        "db.users.find({ age: { \$gt: 18 } })\n\n" +
                        "// Return only the first match\n" +
                        "db.users.findOne({ email: \"alice@example.com\" })"
                    )
                    BodyText("Comparison operators:")
                    CodeBlock(
                        "\$eq   equal            { age: { \$eq: 30 } }\n" +
                        "\$ne   not equal         { status: { \$ne: \"banned\" } }\n" +
                        "\$gt   greater than      { price: { \$gt: 100 } }\n" +
                        "\$gte  greater or equal  { score: { \$gte: 90 } }\n" +
                        "\$lt   less than         { age: { \$lt: 18 } }\n" +
                        "\$in   in a list         { country: { \$in: [\"IL\",\"US\"] } }"
                    )
                    BodyText("Logical operators:")
                    CodeBlock(
                        "// AND (implicit — just include both conditions)\n" +
                        "db.users.find({ age: { \$gt: 18 }, country: \"IL\" })\n\n" +
                        "// Explicit \$and\n" +
                        "db.users.find({ \$and: [ { age: { \$gt: 18 } }, { age: { \$lt: 65 } } ] })\n\n" +
                        "// OR\n" +
                        "db.users.find({ \$or: [ { country: \"IL\" }, { country: \"US\" } ] })\n\n" +
                        "// Sorting, skip, limit\n" +
                        "db.products.find().sort({ price: -1 }).skip(20).limit(10)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Projection: Full Document or Specific Fields") {
                    BodyText(
                        "The second argument to find() is a projection — it controls which fields are returned. " +
                        "You can return the full document (omit projection) or only specific fields.\n\n" +
                        "Return only specific fields (1 = include, 0 = exclude):"
                    )
                    CodeBlock(
                        "// Return only name and email, suppress _id\n" +
                        "db.users.find({}, { name: 1, email: 1, _id: 0 })\n\n" +
                        "// Return everything EXCEPT the password field\n" +
                        "db.users.find({}, { password: 0 })\n\n" +
                        "// Return full document (default — no projection)\n" +
                        "db.users.find({ country: \"IL\" })"
                    )
                    BodyText(
                        "Both full and partial projections are fully supported. Use partial projections when you " +
                        "don't need all fields — it reduces network transfer and memory usage significantly on " +
                        "documents with many fields."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Null vs. Missing Fields") {
                    BodyText(
                        "MongoDB distinguishes three states for a field:\n" +
                        "(a) Field exists with a real value — e.g. { nickname: \"Ace\" }\n" +
                        "(b) Field exists but is explicitly null — e.g. { nickname: null }\n" +
                        "(c) Field is completely absent from the document\n\n" +
                        "When querying for a field that some documents don't have, the absent field does NOT " +
                        "appear in the result — there is no 'NULL' placeholder in the output. But the query " +
                        "filter behaviour is important to understand:\n\n" +
                        "{ field: null } matches BOTH (b) and (c):"
                    )
                    CodeBlock(
                        "// Matches users where nickname is null OR nickname doesn't exist at all\n" +
                        "db.users.find({ nickname: null })"
                    )
                    BodyText("To distinguish absent vs explicitly null, use \$exists:")
                    CodeBlock(
                        "// (c) only — field is completely absent\n" +
                        "db.users.find({ nickname: { \$exists: false } })\n\n" +
                        "// (a) and (b) — field is present (any value, including null)\n" +
                        "db.users.find({ nickname: { \$exists: true } })\n\n" +
                        "// (b) only — field exists but is explicitly null\n" +
                        "db.users.find({ nickname: { \$exists: true, \$eq: null } })\n\n" +
                        "// (a) only — field exists with a real (non-null) value\n" +
                        "db.users.find({ nickname: { \$exists: true, \$ne: null } })"
                    )
                    BodyText(
                        "Practical example: you store user profiles where 'company' is optional. To find all " +
                        "freelancers (no company field set at all): db.users.find({ company: { \$exists: false } }).\n" +
                        "To find users who explicitly cleared their company: db.users.find({ company: { \$exists: true, \$eq: null } })."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Indexing in MongoDB") {
                    BodyText(
                        "Indexes in MongoDB work the same way as in SQL: they speed up queries at the cost of " +
                        "extra storage and slower writes. Without an index, MongoDB does a collection scan — " +
                        "reading every document. With an index, it jumps directly to matching documents.\n\n" +
                        "You do NOT need to declare field types before creating an index — MongoDB infers types."
                    )
                    CodeBlock(
                        "// Single-field ascending index\n" +
                        "db.users.createIndex({ email: 1 })\n\n" +
                        "// Single-field descending index (useful for sorting newest-first)\n" +
                        "db.orders.createIndex({ created_at: -1 })\n\n" +
                        "// Unique index (also enforces no duplicate values)\n" +
                        "db.users.createIndex({ email: 1 }, { unique: true })\n\n" +
                        "// Compound index (good for queries filtering on both fields)\n" +
                        "db.orders.createIndex({ user_id: 1, status: 1 })\n\n" +
                        "// TTL index: automatically delete documents after N seconds\n" +
                        "db.sessions.createIndex({ created_at: 1 }, { expireAfterSeconds: 3600 })\n\n" +
                        "// List all indexes on a collection\n" +
                        "db.users.getIndexes()\n\n" +
                        "// Drop an index\n" +
                        "db.users.dropIndex(\"email_1\")"
                    )
                    BodyText(
                        "Use db.collection.explain(\"executionStats\").find({...}) to check whether a query uses an " +
                        "index (look for 'IXSCAN' in the plan, not 'COLLSCAN')."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Transactions in MongoDB") {
                    BodyText(
                        "Single-document operations in MongoDB are always atomic — updateOne with \$set is applied " +
                        "fully or not at all. No transaction needed for single-document changes.\n\n" +
                        "Multi-document transactions are supported since MongoDB 4.0. They require a session and " +
                        "work only on replica sets or sharded clusters (not standalone servers)."
                    )
                    CodeBlock(
                        "// Node.js / MongoDB driver example\n" +
                        "const session = client.startSession();\n" +
                        "session.startTransaction();\n\n" +
                        "try {\n" +
                        "  await db.accounts.updateOne(\n" +
                        "    { _id: fromId },\n" +
                        "    { \$inc: { balance: -200 } },\n" +
                        "    { session }\n" +
                        "  );\n" +
                        "  await db.accounts.updateOne(\n" +
                        "    { _id: toId },\n" +
                        "    { \$inc: { balance: +200 } },\n" +
                        "    { session }\n" +
                        "  );\n" +
                        "  await session.commitTransaction();\n" +
                        "} catch (err) {\n" +
                        "  await session.abortTransaction();  // rolls back both updates\n" +
                        "  throw err;\n" +
                        "} finally {\n" +
                        "  session.endSession();\n" +
                        "}"
                    )
                    BodyText(
                        "Note: MongoDB transactions are more expensive than single-document operations. Design your " +
                        "data model to embed related data in one document when possible, and use transactions only " +
                        "when you truly need to atomically update multiple collections."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Schema and Field Types") {
                    BodyText(
                        "MongoDB is schema-flexible by default. Any document shape is accepted — you don't need to " +
                        "declare fields or their types in advance. Field types are inferred from the BSON values " +
                        "you insert: string, int32, double, boolean, date, array, object, ObjectId, etc.\n\n" +
                        "Optional schema validation (MongoDB 3.6+):\n" +
                        "You can attach a JSON Schema validator to a collection. MongoDB then validates documents " +
                        "on insert and update and rejects those that don't match."
                    )
                    CodeBlock(
                        "db.createCollection(\"users\", {\n" +
                        "  validator: {\n" +
                        "    \$jsonSchema: {\n" +
                        "      bsonType: \"object\",\n" +
                        "      required: [\"name\", \"email\"],\n" +
                        "      properties: {\n" +
                        "        name:  { bsonType: \"string\" },\n" +
                        "        email: { bsonType: \"string\", pattern: \".+@.+\" },\n" +
                        "        age:   { bsonType: \"int\", minimum: 0 }\n" +
                        "      }\n" +
                        "    }\n" +
                        "  }\n" +
                        "})"
                    )
                    BodyText(
                        "With no validator, a document missing 'email' is accepted. With the validator above, it " +
                        "is rejected with an error. This lets you enforce a schema where it matters while still " +
                        "allowing optional fields freely."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "When to Use MongoDB") {
                    BodyText(
                        "Good fit:\n" +
                        "• Data with many optional or varying fields (user profiles, product catalogues with " +
                        "different attribute sets per category)\n" +
                        "• Rapidly evolving schema during early development — no migrations needed\n" +
                        "• Content management systems, event logs, IoT sensor data\n" +
                        "• When your data is naturally hierarchical and you want to embed related data in one " +
                        "document to avoid joins\n\n" +
                        "Consider SQL instead when:\n" +
                        "• Data is highly relational with many cross-references (orders with line items with " +
                        "products with suppliers — deep join chains)\n" +
                        "• Strict schema enforcement and data integrity are non-negotiable (financial records)\n" +
                        "• Your team already has strong SQL skills and the data is naturally tabular"
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
