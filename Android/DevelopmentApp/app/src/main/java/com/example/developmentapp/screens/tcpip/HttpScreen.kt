package com.example.developmentapp.screens.tcpip

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
fun HttpScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "HTTP",
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

            // ── What is HTTP ─────────────────────────────────────────────
            item {
                SectionCard(title = "What is HTTP?") {
                    BodyText("HTTP — HyperText Transfer Protocol — is an application-layer request-response protocol for transferring hypermedia documents and data. It is the foundation of the World Wide Web.")
                    BodyText("HTTP is stateless: each request is independent. State (sessions, logins) is maintained externally via cookies, tokens, or session IDs.")
                    BodyText("HTTP originally ran over TCP. HTTP/3 runs over QUIC (UDP-based).")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── HTTP Versions ────────────────────────────────────────────
            item {
                SectionCard(title = "HTTP Versions") {
                    CodeBlock(
                        "HTTP/1.0  One request per TCP connection; no keep-alive\n" +
                        "HTTP/1.1  Persistent connections (keep-alive); pipelining\n" +
                        "          (rarely used); text-based; head-of-line blocking\n" +
                        "HTTP/2    Binary framing; multiplexed streams over one TCP\n" +
                        "          connection; header compression (HPACK); server push\n" +
                        "          Still has TCP head-of-line blocking\n" +
                        "HTTP/3    Runs over QUIC (UDP); no head-of-line blocking;\n" +
                        "          TLS 1.3 mandatory; faster connection setup"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Request Methods ──────────────────────────────────────────
            item {
                SectionCard(title = "Request Methods") {
                    CodeBlock(
                        "GET     Retrieve a resource; no body; safe + idempotent\n" +
                        "POST    Submit data; creates/triggers; NOT idempotent\n" +
                        "PUT     Replace a resource entirely; idempotent\n" +
                        "PATCH   Partially update a resource; NOT idempotent\n" +
                        "DELETE  Remove a resource; idempotent\n" +
                        "HEAD    Like GET but returns headers only (no body)\n" +
                        "OPTIONS Ask the server which methods are allowed (used by CORS)"
                    )
                    BodyText("Safe means the method has no side effects (GET, HEAD). Idempotent means repeating the request N times has the same effect as doing it once (GET, PUT, DELETE).")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── URL and Query Parameters ─────────────────────────────────
            item {
                SectionCard(title = "URL and Query Parameters") {
                    BodyText("A URL has the structure:")
                    CodeBlock(
                        "scheme://host:port/path?query#fragment\n" +
                        "\n" +
                        "https://api.example.com:443/users/42?sort=name&page=2#section"
                    )
                    BodyText("Query string parameters come after '?' and are separated by '&':")
                    CodeBlock(
                        "?key=value&key2=value2\n" +
                        "?search=hello+world&lang=en\n" +
                        "?q=foo%20bar     (space encoded as %20)"
                    )
                    BodyText("Special characters in query strings must be percent-encoded: space → %20 (or +), & → %26, = → %3D, etc. This is called URL encoding (application/x-www-form-urlencoded).")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Request / Response Structure ─────────────────────────────
            item {
                SectionCard(title = "Request / Response Structure") {
                    BodyText("HTTP/1.1 Request:")
                    CodeBlock(
                        "GET /users/42 HTTP/1.1\n" +
                        "Host: api.example.com\n" +
                        "Accept: application/json\n" +
                        "Authorization: Bearer eyJhbGci...\n" +
                        "\n" +
                        "(blank line separates headers from body)\n" +
                        "(GET has no body)"
                    )
                    BodyText("HTTP/1.1 Response:")
                    CodeBlock(
                        "HTTP/1.1 200 OK\n" +
                        "Content-Type: application/json\n" +
                        "Content-Length: 58\n" +
                        "\n" +
                        "{\"id\": 42, \"name\": \"Alice\"}"
                    )
                    BodyText("Common status codes:")
                    CodeBlock(
                        "200 OK           Request succeeded\n" +
                        "201 Created      Resource created (POST/PUT)\n" +
                        "301/302          Redirect (permanent/temporary)\n" +
                        "400 Bad Request  Malformed request\n" +
                        "401 Unauthorized Missing/invalid credentials\n" +
                        "403 Forbidden    Authenticated but not allowed\n" +
                        "404 Not Found    Resource does not exist\n" +
                        "500 Internal     Server error"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Body Encoding ────────────────────────────────────────────
            item {
                SectionCard(title = "Body Encoding (Content-Type)") {
                    BodyText("The Content-Type header tells the receiver how to parse the body:")
                    CodeBlock(
                        "application/x-www-form-urlencoded\n" +
                        "  key1=value1&key2=hello+world\n" +
                        "  (same encoding as URL query strings)\n" +
                        "  Used by HTML <form> submissions\n" +
                        "\n" +
                        "application/json\n" +
                        "  {\"key\": \"value\", \"count\": 3}\n" +
                        "  Most common for REST APIs\n" +
                        "\n" +
                        "multipart/form-data\n" +
                        "  Used for file uploads; each part has its own headers\n" +
                        "  Boundary string separates parts\n" +
                        "\n" +
                        "text/plain, text/html, application/xml, ..."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Compression ──────────────────────────────────────────────
            item {
                SectionCard(title = "Compression — gzip (No Base64 Needed)") {
                    BodyText("HTTP bodies can be compressed to save bandwidth. The client advertises what it accepts:")
                    CodeBlock("Accept-Encoding: gzip, deflate, br")
                    BodyText("If the server compresses the response, it signals this:")
                    CodeBlock(
                        "Content-Encoding: gzip\n" +
                        "\n" +
                        "[raw gzip-compressed bytes follow in the body]"
                    )
                    BodyText("Important: HTTP is a binary-safe protocol. The body can contain any byte value, not just printable ASCII. Therefore, compressed bytes are sent directly — no Base64 encoding is needed or used.")
                    BodyText("Adding Base64 would actually be counterproductive: Base64 increases data size by ~33%, undoing much of the compression benefit. Base64 exists only for text-only transports (like embedding binary in JSON or email — SMTP originally carried only ASCII text).")
                    BodyText("Common compression algorithms over HTTP:")
                    CodeBlock(
                        "gzip    DEFLATE + gzip wrapper; widely supported\n" +
                        "deflate Raw DEFLATE; less common\n" +
                        "br      Brotli; better compression than gzip; HTTP/2+\n" +
                        "zstd    Zstandard; fastest; modern support"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Base64 in HTTP ───────────────────────────────────────────
            item {
                SectionCard(title = "Base64 in HTTP — When It IS Used") {
                    BodyText("Base64 converts arbitrary binary bytes into printable ASCII characters (A-Z, a-z, 0-9, +, /). It is used in HTTP only when binary data must appear inside a text-only context:")
                    BodyText("1. HTTP Basic Authentication:")
                    CodeBlock(
                        "Authorization: Basic dXNlcjpwYXNzd29yZA==\n" +
                        "  (Base64 of 'user:password')\n" +
                        "  The header value must be ASCII text, so the\n" +
                        "  colon-separated credentials are Base64-encoded"
                    )
                    BodyText("2. Data URIs (embedding images in HTML/CSS):")
                    CodeBlock(
                        "<img src=\"data:image/png;base64,iVBORw0KGgo...\">\n" +
                        "  Binary image bytes embedded as Base64 text\n" +
                        "  inside an HTML attribute (which must be text)"
                    )
                    BodyText("3. Embedding binary in JSON (since JSON is text):")
                    CodeBlock(
                        "{\"file\": \"SGVsbG8gV29ybGQ=\"}\n" +
                        "  JSON has no binary type, so binary data\n" +
                        "  (e.g. a file, a signature) is Base64-encoded"
                    )
                    BodyText("Summary: Base64 is a text-transport workaround. HTTP bodies are already binary-safe, so compressed or binary response bodies never need Base64.")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
