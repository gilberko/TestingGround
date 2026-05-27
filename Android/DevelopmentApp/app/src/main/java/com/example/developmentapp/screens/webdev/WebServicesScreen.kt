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
fun WebServicesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Web Services",
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
                SectionCard(title = "What Are Web Services?") {
                    BodyText(
                        "Web services are software systems designed for machine-to-machine " +
                        "communication over a network using standardized formats and protocols. " +
                        "Unlike a website (designed for humans in browsers), a web service " +
                        "exposes an API that other programs consume.\n\n" +
                        "Key properties of web services:\n" +
                        "• Interoperability — a Python client can call a Java server\n" +
                        "• Platform-independent — any OS, any language, via HTTP\n" +
                        "• Standardized format — XML or JSON for data exchange\n" +
                        "• Discoverable — contracts (WSDL, OpenAPI) describe the interface\n\n" +
                        "Main styles: SOAP, REST, GraphQL, gRPC, WebSockets."
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "SOAP") {
                    BodyText(
                        "SOAP (Simple Object Access Protocol) is the oldest and most formal " +
                        "web service standard. Used heavily in enterprise, banking, and healthcare " +
                        "systems, often in legacy integrations.\n\n" +
                        "Key characteristics:\n" +
                        "• Messages are XML envelopes — strict, verbose format\n" +
                        "• Contract defined by WSDL (Web Services Description Language) — " +
                        "  a machine-readable XML file describing operations and types\n" +
                        "• Operates over HTTP, HTTPS, SMTP, or JMS\n" +
                        "• Supports WS-Security, WS-ReliableMessaging — enterprise features\n" +
                        "• Strongly typed — the WSDL defines exact input/output types\n\n" +
                        "Downsides: verbose XML, complex tooling, harder to debug than REST."
                    )
                    CodeBlock(
                        "<!-- SOAP request to a weather service -->\n" +
                        "<soap:Envelope\n" +
                        "  xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                        "  <soap:Header/>\n" +
                        "  <soap:Body>\n" +
                        "    <GetWeather xmlns=\"http://example.com/weather\">\n" +
                        "      <City>London</City>\n" +
                        "    </GetWeather>\n" +
                        "  </soap:Body>\n" +
                        "</soap:Envelope>\n\n" +
                        "<!-- SOAP response -->\n" +
                        "<soap:Envelope ...>\n" +
                        "  <soap:Body>\n" +
                        "    <GetWeatherResponse>\n" +
                        "      <Temperature>15</Temperature>\n" +
                        "      <Condition>Cloudy</Condition>\n" +
                        "    </GetWeatherResponse>\n" +
                        "  </soap:Body>\n" +
                        "</soap:Envelope>"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "REST") {
                    BodyText(
                        "REST (Representational State Transfer) is the dominant API style today. " +
                        "Lightweight, uses plain HTTP, data in JSON (usually).\n\n" +
                        "Resources are identified by URLs; HTTP verbs (GET, POST, PUT, DELETE) " +
                        "describe the action. Stateless — each request carries all context.\n\n" +
                        "See the REST API screen for full details."
                    )
                    CodeBlock(
                        "// REST: GET a user\n" +
                        "GET /api/users/42\n" +
                        "Accept: application/json\n\n" +
                        "// Response\n" +
                        "HTTP/1.1 200 OK\n" +
                        "Content-Type: application/json\n" +
                        "{ \"id\": 42, \"name\": \"Alice\", \"email\": \"alice@example.com\" }"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "GraphQL") {
                    BodyText(
                        "GraphQL was developed by Facebook (Meta) in 2012, open-sourced in 2015. " +
                        "It is a query language for APIs, not just a protocol.\n\n" +
                        "Key difference from REST:\n" +
                        "• Single endpoint — always POST /graphql\n" +
                        "• Client specifies EXACTLY what fields it wants — no over-fetching " +
                        "  (getting more data than needed) or under-fetching (needing multiple requests)\n" +
                        "• Strongly typed schema — server defines types; clients and tools use it\n" +
                        "• Resolvers — server functions that fetch each field\n\n" +
                        "Three operation types:\n" +
                        "  query — read data\n" +
                        "  mutation — write/modify data\n" +
                        "  subscription — real-time updates via WebSocket\n\n" +
                        "Great for mobile apps (bandwidth-constrained) and complex data graphs."
                    )
                    CodeBlock(
                        "// GraphQL query: get user name and their posts' titles only\n" +
                        "query {\n" +
                        "  user(id: 42) {\n" +
                        "    name\n" +
                        "    posts {\n" +
                        "      title\n" +
                        "    }\n" +
                        "  }\n" +
                        "}\n\n" +
                        "// Response — exactly what was asked for, nothing more\n" +
                        "{\n" +
                        "  \"data\": {\n" +
                        "    \"user\": {\n" +
                        "      \"name\": \"Alice\",\n" +
                        "      \"posts\": [{\"title\": \"Hello World\"}, {\"title\": \"GraphQL Tips\"}]\n" +
                        "    }\n" +
                        "  }\n" +
                        "}\n\n" +
                        "// Mutation: create a post\n" +
                        "mutation {\n" +
                        "  createPost(title: \"New Post\", userId: 42) {\n" +
                        "    id\n" +
                        "    title\n" +
                        "  }\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "gRPC") {
                    BodyText(
                        "gRPC (Google Remote Procedure Call) is an open-source RPC framework " +
                        "developed by Google, released in 2016. It is designed for high-performance " +
                        "inter-service communication.\n\n" +
                        "Key features:\n" +
                        "• Protocol Buffers (protobuf) — binary serialization format. " +
                        "  Much smaller and faster than JSON. Strongly typed.\n" +
                        "• HTTP/2 — multiplexed streams, bidirectional streaming, header compression\n" +
                        "• Code generation — from .proto file, generate client/server stubs " +
                        "  in any supported language (Go, Java, Python, Node, C++...)\n" +
                        "• 4 service types: unary, server streaming, client streaming, " +
                        "  bidirectional streaming\n\n" +
                        "Best for: internal microservice communication where performance matters."
                    )
                    CodeBlock(
                        "// .proto file defines the service contract\n" +
                        "syntax = \"proto3\";\n\n" +
                        "service UserService {\n" +
                        "    rpc GetUser (UserRequest) returns (UserResponse);\n" +
                        "    rpc ListUsers (ListRequest) returns (stream UserResponse);\n" +
                        "}\n\n" +
                        "message UserRequest  { int32 id = 1; }\n" +
                        "message UserResponse {\n" +
                        "    int32  id    = 1;\n" +
                        "    string name  = 2;\n" +
                        "    string email = 3;\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "WebSockets") {
                    BodyText(
                        "HTTP is request-response — the client asks, the server answers, " +
                        "and the connection closes. WebSockets establish a persistent, " +
                        "full-duplex (bidirectional) connection over a single TCP connection.\n\n" +
                        "Handshake: starts as HTTP; client sends Upgrade: websocket header; " +
                        "server responds 101 Switching Protocols.\n\n" +
                        "After upgrade: either side can send messages at any time — " +
                        "server can push data without waiting for the client to ask.\n\n" +
                        "Use cases: live chat, collaborative editing, live sports scores, " +
                        "stock tickers, multiplayer games, real-time dashboards.\n\n" +
                        "Protocol: ws:// (unencrypted) or wss:// (encrypted over TLS)."
                    )
                    CodeBlock(
                        "// Browser (client)\n" +
                        "const ws = new WebSocket('wss://chat.example.com/ws');\n\n" +
                        "ws.onopen    = () => ws.send('Hello server!');\n" +
                        "ws.onmessage = (event) => console.log('Got:', event.data);\n" +
                        "ws.onclose   = () => console.log('Disconnected');\n\n" +
                        "// Server push without client request\n" +
                        "ws.send(JSON.stringify({ type: 'chat', text: 'Hi from server' }));\n\n" +
                        "// Node.js server (ws library)\n" +
                        "const { WebSocketServer } = require('ws');\n" +
                        "const wss = new WebSocketServer({ port: 8080 });\n\n" +
                        "wss.on('connection', (client) => {\n" +
                        "    client.on('message', (msg) => {\n" +
                        "        // Broadcast to all clients\n" +
                        "        wss.clients.forEach(c => c.send(msg));\n" +
                        "    });\n" +
                        "});"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "When to Use What") {
                    BodyText(
                        "REST — the safe default for public APIs, CRUD operations, and any " +
                        "situation where simplicity and wide tooling support matter. " +
                        "Clients: any HTTP client in any language.\n\n" +
                        "GraphQL — when the client needs flexible queries; mobile apps where " +
                        "bandwidth is precious; APIs with complex, deeply nested data relationships " +
                        "(social networks, content platforms).\n\n" +
                        "gRPC — internal service-to-service communication (microservices) where " +
                        "performance is critical; streaming use cases; when you want a strict " +
                        "contract with generated clients.\n\n" +
                        "SOAP — only when integrating with existing enterprise/legacy systems " +
                        "that require it (banks, insurance, government systems).\n\n" +
                        "WebSockets — real-time bidirectional communication: chat, live feeds, " +
                        "collaborative tools, gaming. Use alongside REST (REST for CRUD, " +
                        "WebSocket for live updates)."
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
