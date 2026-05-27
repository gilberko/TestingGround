package com.example.developmentapp.screens.webdev

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
fun NodeJsTypeScriptScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Node.js and TypeScript",
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
                SectionCard(title = "Node.js — What Is It?") {
                    BodyText(
                        "Node.js is a runtime that lets JavaScript run outside the browser — " +
                        "on the server, command line, desktop apps, and IoT devices.\n\n" +
                        "Built on V8 — Google's open-source JavaScript engine (the same one " +
                        "inside Chrome). V8 compiles JavaScript directly to machine code (JIT).\n\n" +
                        "Created by Ryan Dahl in 2009. The key insight: instead of a thread-per-" +
                        "request model (like classic Apache/PHP), use a single-threaded event " +
                        "loop with non-blocking I/O — ideal for I/O-heavy workloads like web servers.\n\n" +
                        "How to run:\n" +
                        "  node app.js — run a file\n" +
                        "  node — interactive REPL\n" +
                        "  npx ts-node app.ts — run TypeScript directly"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The Event Loop") {
                    BodyText(
                        "Node.js is single-threaded for JavaScript execution but handles " +
                        "concurrency through the event loop and libuv (a C library).\n\n" +
                        "When a file read or network request is made, Node passes it to the " +
                        "OS (via libuv), continues executing other code, and when the OS " +
                        "signals completion, Node queues the callback.\n\n" +
                        "The event loop picks up callbacks from the queue and executes them one " +
                        "at a time on the single JS thread — no race conditions in JS code, " +
                        "no mutexes needed for most use cases.\n\n" +
                        "This is why Node can handle thousands of concurrent connections with " +
                        "one thread — it never blocks waiting for I/O.\n\n" +
                        "Long CPU-intensive operations DO block the event loop since they run " +
                        "on the single thread — use Worker Threads for those."
                    )
                    CodeBlock(
                        "// Non-blocking: Node starts reading, continues to next line\n" +
                        "const fs = require('fs');\n\n" +
                        "fs.readFile('data.txt', 'utf8', (err, data) => {\n" +
                        "    if (err) throw err;\n" +
                        "    console.log('File contents:', data);\n" +
                        "});\n\n" +
                        "console.log('This runs BEFORE file is done reading');\n\n" +
                        "// With promises (preferred modern style)\n" +
                        "const fs2 = require('fs').promises;\n" +
                        "async function readFile() {\n" +
                        "    const data = await fs2.readFile('data.txt', 'utf8');\n" +
                        "    console.log(data);\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Node.js Core API") {
                    BodyText(
                        "Node.js has a rich standard library (no browser dependencies):\n\n" +
                        "fs — file system:\n" +
                        "  fs.readFile, fs.writeFile, fs.appendFile, fs.unlink\n" +
                        "  fs.createReadStream — streaming large files\n\n" +
                        "http / https — create HTTP servers:\n" +
                        "  http.createServer((req, res) => ...)\n\n" +
                        "path — file path manipulation:\n" +
                        "  path.join(), path.resolve(), path.basename(), path.extname()\n\n" +
                        "os — operating system info:\n" +
                        "  os.hostname(), os.cpus(), os.totalmem(), os.platform()\n\n" +
                        "events — EventEmitter pattern:\n" +
                        "  emitter.on('event', handler)\n" +
                        "  emitter.emit('event', data)\n\n" +
                        "process — the Node.js process itself:\n" +
                        "  process.env.PORT — environment variable\n" +
                        "  process.argv — command-line arguments\n" +
                        "  process.exit(0) — exit\n\n" +
                        "Buffer — raw binary data (for file I/O, network)"
                    )
                    CodeBlock(
                        "// Minimal HTTP server\n" +
                        "const http = require('http');\n\n" +
                        "const server = http.createServer((req, res) => {\n" +
                        "    res.writeHead(200, { 'Content-Type': 'application/json' });\n" +
                        "    res.end(JSON.stringify({ status: 'ok' }));\n" +
                        "});\n\n" +
                        "server.listen(3000, () => {\n" +
                        "    console.log('Server running on port 3000');\n" +
                        "});\n\n" +
                        "// Environment variable\n" +
                        "const PORT = process.env.PORT || 3000;\n" +
                        "const DB   = process.env.DATABASE_URL;"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "State and Parameters in Node.js") {
                    BodyText(
                        "Unlike PHP (which is stateless per-request), a Node.js server process " +
                        "runs continuously. Variables declared at the module level persist for " +
                        "the life of the server process and are shared across all requests — " +
                        "this is useful for connection pools, caches, and counters, but " +
                        "requires care to avoid sharing per-user state accidentally.\n\n" +
                        "Request parameters:\n" +
                        "URL path params — /users/:id — available as req.params.id (Express)\n" +
                        "Query string — /search?q=cats — req.query.q (Express)\n" +
                        "Request body — POST JSON/form — req.body (parsed by middleware)\n" +
                        "Headers — req.headers['authorization']\n\n" +
                        "Environment variables (process.env) are the standard way to inject " +
                        "config (ports, API keys, DB URLs) without hardcoding."
                    )
                    CodeBlock(
                        "// Express.js example (most popular Node framework)\n" +
                        "const express = require('express');\n" +
                        "const app = express();\n\n" +
                        "app.use(express.json()); // parse JSON body\n\n" +
                        "// Module-level (shared across requests)\n" +
                        "const db = new Database(process.env.DB_URL);\n\n" +
                        "// Path param: GET /users/42\n" +
                        "app.get('/users/:id', async (req, res) => {\n" +
                        "    const user = await db.findUser(req.params.id);\n" +
                        "    if (!user) return res.status(404).json({ error: 'Not found' });\n" +
                        "    res.json(user);\n" +
                        "});\n\n" +
                        "// Request body: POST /users\n" +
                        "app.post('/users', async (req, res) => {\n" +
                        "    const { name, email } = req.body;\n" +
                        "    const user = await db.createUser(name, email);\n" +
                        "    res.status(201).json(user);\n" +
                        "});\n\n" +
                        "app.listen(process.env.PORT || 3000);"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "npm and Packages") {
                    BodyText(
                        "npm (Node Package Manager) is the world's largest software registry. " +
                        "It comes bundled with Node.js.\n\n" +
                        "package.json — describes the project: name, version, scripts, dependencies.\n" +
                        "  npm init — creates package.json\n" +
                        "  npm install — installs all listed dependencies to node_modules/\n" +
                        "  npm install express — adds a package\n" +
                        "  npm install --save-dev jest — dev-only dependency\n\n" +
                        "Scripts in package.json:\n" +
                        "  \"start\": \"node app.js\"\n" +
                        "  \"dev\": \"nodemon app.js\" — auto-restart on file change\n" +
                        "  \"test\": \"jest\"\n\n" +
                        "Popular packages:\n" +
                        "  express — web framework\n" +
                        "  axios — HTTP client\n" +
                        "  dotenv — load .env file into process.env\n" +
                        "  prisma — ORM for database access\n" +
                        "  jest / vitest — testing"
                    )
                    CodeBlock(
                        "// package.json\n" +
                        "{\n" +
                        "  \"name\": \"my-api\",\n" +
                        "  \"version\": \"1.0.0\",\n" +
                        "  \"scripts\": {\n" +
                        "    \"start\": \"node dist/app.js\",\n" +
                        "    \"dev\":   \"nodemon src/app.ts\",\n" +
                        "    \"build\": \"tsc\"\n" +
                        "  },\n" +
                        "  \"dependencies\": { \"express\": \"^4.18\" },\n" +
                        "  \"devDependencies\": { \"typescript\": \"^5.0\" }\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "TypeScript — What Is It?") {
                    BodyText(
                        "TypeScript (TS) is a strongly-typed superset of JavaScript created by " +
                        "Microsoft in 2012 (Anders Hejlsberg, who also designed C#). " +
                        "It compiles (transpiles) to plain JavaScript that runs anywhere JS runs.\n\n" +
                        "TypeScript adds to JavaScript:\n" +
                        "• Static type annotations — caught at compile time, not runtime\n" +
                        "• Interfaces and type aliases\n" +
                        "• Generics\n" +
                        "• Enums\n" +
                        "• Access modifiers (public, private, protected)\n" +
                        "• Decorators (Angular, NestJS)\n" +
                        "• Better IDE autocomplete and refactoring\n\n" +
                        "All valid JavaScript is valid TypeScript — you can adopt it gradually.\n\n" +
                        "How to run:\n" +
                        "  tsc file.ts — compile to JavaScript, then node file.js\n" +
                        "  ts-node file.ts — compile and run in one step (dev only)\n" +
                        "  tsx — faster alternative to ts-node"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "TypeScript Type System") {
                    BodyText(
                        "Primitive types: string, number, boolean, null, undefined, symbol, bigint\n\n" +
                        "Arrays: number[] or Array<number>\n\n" +
                        "Tuple: [string, number] — fixed-length array with known types\n\n" +
                        "Union: string | null — value can be one of several types\n\n" +
                        "Type inference — TS infers the type from the initial value; no annotation needed.\n\n" +
                        "Interface vs type alias — both define shapes, but interfaces can be " +
                        "extended and merged; type aliases can represent unions and tuples.\n\n" +
                        "Generics <T> — write reusable code that works with any type.\n\n" +
                        "as const — narrows literal types.\n\n" +
                        "readonly — prevents reassignment of a property or array element.\n\n" +
                        "strictNullChecks — when enabled, null/undefined are not assignable to " +
                        "other types unless explicitly included in a union. This eliminates " +
                        "entire classes of runtime NPEs."
                    )
                    CodeBlock(
                        "// Primitive annotations\n" +
                        "let name:  string  = 'Alice';\n" +
                        "let age:   number  = 30;\n" +
                        "let admin: boolean = true;\n\n" +
                        "// Interface\n" +
                        "interface User {\n" +
                        "    readonly id: number;\n" +
                        "    name: string;\n" +
                        "    email: string;\n" +
                        "    role?: 'admin' | 'user';  // optional, union literal\n" +
                        "}\n\n" +
                        "// Generic function\n" +
                        "function first<T>(arr: T[]): T | undefined {\n" +
                        "    return arr[0];\n" +
                        "}\n\n" +
                        "// Type alias for union\n" +
                        "type Result<T> = { ok: true; data: T } | { ok: false; error: string };\n\n" +
                        "// strictNullChecks: must handle null\n" +
                        "function greet(name: string | null) {\n" +
                        "    if (name === null) return 'Hello, guest';\n" +
                        "    return `Hello, \${name}`;\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "TypeScript in Practice") {
                    BodyText(
                        "tsconfig.json — configures the TypeScript compiler:\n" +
                        "  target: 'ES2022' — output JS version\n" +
                        "  module: 'CommonJS' — for Node.js\n" +
                        "  strict: true — enables all strict checks including strictNullChecks\n" +
                        "  outDir: './dist' — where compiled JS goes\n\n" +
                        "NestJS — a TypeScript-first Node.js framework inspired by Angular. " +
                        "Uses decorators heavily (@Controller, @Get, @Injectable).\n\n" +
                        "Where TypeScript runs:\n" +
                        "  Frontend: compiled by webpack/Vite, runs in browser\n" +
                        "  Backend: compiled by tsc, runs in Node.js\n\n" +
                        "State: same as regular Node.js — module-level variables persist " +
                        "across requests; request state lives in the request handler scope.\n\n" +
                        "TypeScript does NOT change runtime behavior — it only catches errors " +
                        "at build time. The compiled JS is plain JavaScript."
                    )
                    CodeBlock(
                        "// tsconfig.json\n" +
                        "{\n" +
                        "  \"compilerOptions\": {\n" +
                        "    \"target\": \"ES2022\",\n" +
                        "    \"module\": \"CommonJS\",\n" +
                        "    \"strict\": true,\n" +
                        "    \"outDir\": \"./dist\",\n" +
                        "    \"rootDir\": \"./src\"\n" +
                        "  }\n" +
                        "}\n\n" +
                        "// Express + TypeScript endpoint\n" +
                        "import express, { Request, Response } from 'express';\n\n" +
                        "interface CreateUserBody { name: string; email: string; }\n\n" +
                        "app.post('/users', (req: Request<{},{},CreateUserBody>, res: Response) => {\n" +
                        "    const { name, email } = req.body; // fully typed\n" +
                        "    res.status(201).json({ name, email });\n" +
                        "});"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
