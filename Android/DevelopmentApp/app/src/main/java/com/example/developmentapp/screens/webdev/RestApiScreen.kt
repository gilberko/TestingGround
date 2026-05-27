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
fun RestApiScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "REST API",
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
                SectionCard(title = "What Is REST?") {
                    BodyText(
                        "REST (Representational State Transfer) is an architectural style, not a " +
                        "protocol. It was defined by Roy Fielding in his 2000 doctoral dissertation.\n\n" +
                        "REST describes 6 constraints that, when followed together, produce a " +
                        "scalable, maintainable, stateless architecture:\n\n" +
                        "1. Client-Server — UI and data storage are separated; they evolve independently\n" +
                        "2. Stateless — each request contains all context needed; server stores no " +
                        "   client session state between requests\n" +
                        "3. Cacheable — responses must define themselves as cacheable or not\n" +
                        "4. Uniform Interface — consistent, self-descriptive messages; " +
                        "   resources identified by URI\n" +
                        "5. Layered System — client cannot tell if it's talking to the real " +
                        "   server or a proxy/load balancer in between\n" +
                        "6. Code on Demand (optional) — server can send executable code to client\n\n" +
                        "An API that follows REST principles is called RESTful."
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Resources and URLs") {
                    BodyText(
                        "The core concept of REST: everything is a resource, and each resource " +
                        "has a unique URL (Uniform Resource Locator).\n\n" +
                        "URL design rules:\n" +
                        "• Use nouns, not verbs — the HTTP method IS the verb\n" +
                        "• Use plural resource names — /users not /user\n" +
                        "• Nest for relationships — /users/42/orders (orders belonging to user 42)\n" +
                        "• Use lowercase with hyphens — /product-categories not /ProductCategories\n" +
                        "• Never include actions in the URL:\n" +
                        "  Bad: /getUser?id=42, /deleteOrder/5, /createPost\n" +
                        "  Good: GET /users/42, DELETE /orders/5, POST /posts"
                    )
                    CodeBlock(
                        "// Resource URL examples\n" +
                        "GET    /users           → list all users\n" +
                        "GET    /users/42        → get user 42\n" +
                        "POST   /users           → create a new user\n" +
                        "PUT    /users/42        → fully replace user 42\n" +
                        "PATCH  /users/42        → partially update user 42\n" +
                        "DELETE /users/42        → delete user 42\n\n" +
                        "// Nested resources\n" +
                        "GET    /users/42/orders → orders for user 42\n" +
                        "GET    /users/42/orders/7 → order 7 of user 42\n\n" +
                        "// Query params for filtering/sorting (not IDs)\n" +
                        "GET    /products?category=electronics&sort=price&order=asc"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "HTTP Methods") {
                    BodyText(
                        "GET — read a resource. Safe (no side effects) and idempotent " +
                        "(same result every time). Never use GET to modify data.\n\n" +
                        "POST — create a new resource or trigger an action. Not idempotent — " +
                        "calling twice creates two records.\n\n" +
                        "PUT — fully replace a resource. Idempotent — same result if called " +
                        "multiple times. Must send the entire resource body.\n\n" +
                        "PATCH — partial update. Only send the fields that changed. " +
                        "Idempotent when designed properly.\n\n" +
                        "DELETE — remove a resource. Idempotent — deleting twice has the " +
                        "same end state (resource is gone).\n\n" +
                        "OPTIONS — used by browsers for CORS preflight checks. Server returns " +
                        "allowed methods and headers.\n\n" +
                        "HEAD — like GET but server returns headers only, no body. " +
                        "Useful to check if a resource exists or get its size."
                    )
                    CodeBlock(
                        "// POST: create — returns 201 Created with the new resource\n" +
                        "POST /users\n" +
                        "Content-Type: application/json\n" +
                        "{ \"name\": \"Alice\", \"email\": \"alice@example.com\" }\n\n" +
                        "→ 201 Created\n" +
                        "   Location: /users/42\n" +
                        "   { \"id\": 42, \"name\": \"Alice\", \"email\": \"alice@example.com\" }\n\n" +
                        "// PATCH: partial update — only changed fields\n" +
                        "PATCH /users/42\n" +
                        "{ \"email\": \"newemail@example.com\" }\n\n" +
                        "→ 200 OK\n" +
                        "   { \"id\": 42, \"name\": \"Alice\", \"email\": \"newemail@example.com\" }"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "HTTP Status Codes") {
                    BodyText(
                        "Status codes tell the client what happened. Always use the right code.\n\n" +
                        "2xx — Success:\n" +
                        "  200 OK — general success\n" +
                        "  201 Created — resource created (include Location header)\n" +
                        "  204 No Content — success with no body (DELETE, some PUTs)\n\n" +
                        "3xx — Redirection:\n" +
                        "  301 Moved Permanently — URL changed forever\n" +
                        "  304 Not Modified — cached version is still valid\n\n" +
                        "4xx — Client Error:\n" +
                        "  400 Bad Request — malformed request / invalid input\n" +
                        "  401 Unauthorized — not authenticated (must log in)\n" +
                        "  403 Forbidden — authenticated but not allowed\n" +
                        "  404 Not Found — resource doesn't exist\n" +
                        "  409 Conflict — state conflict (e.g. duplicate email)\n" +
                        "  422 Unprocessable Entity — validation errors\n" +
                        "  429 Too Many Requests — rate limit exceeded\n\n" +
                        "5xx — Server Error:\n" +
                        "  500 Internal Server Error — unhandled exception\n" +
                        "  502 Bad Gateway — upstream service error\n" +
                        "  503 Service Unavailable — server overloaded or down"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Request and Response Format") {
                    BodyText(
                        "JSON is the de facto standard format for REST APIs today.\n\n" +
                        "Content-Type: application/json — tells the server the body is JSON\n" +
                        "Accept: application/json — tells the server what format you want back\n\n" +
                        "Error responses should have a consistent structure so clients can " +
                        "handle them generically.\n\n" +
                        "Always include a Content-Type header. Always parse and return valid JSON."
                    )
                    CodeBlock(
                        "// GET /users/42 — successful response\n" +
                        "HTTP/1.1 200 OK\n" +
                        "Content-Type: application/json\n\n" +
                        "{\n" +
                        "  \"id\": 42,\n" +
                        "  \"name\": \"Alice\",\n" +
                        "  \"email\": \"alice@example.com\",\n" +
                        "  \"createdAt\": \"2024-01-15T10:30:00Z\"\n" +
                        "}\n\n" +
                        "// Error response — consistent structure\n" +
                        "HTTP/1.1 422 Unprocessable Entity\n" +
                        "Content-Type: application/json\n\n" +
                        "{\n" +
                        "  \"error\": \"validation_failed\",\n" +
                        "  \"message\": \"Invalid input\",\n" +
                        "  \"details\": [\n" +
                        "    { \"field\": \"email\", \"message\": \"Invalid email format\" },\n" +
                        "    { \"field\": \"name\",  \"message\": \"Name is required\" }\n" +
                        "  ]\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Authentication") {
                    BodyText(
                        "REST is stateless — authentication info must be in every request, " +
                        "not in server-side session storage.\n\n" +
                        "API Keys — simplest; a secret string in the header. Used for server-to-" +
                        "server and developer API access.\n\n" +
                        "JWT (JSON Web Token) — self-contained token with header.payload.signature, " +
                        "base64url encoded. Server signs it with a secret; anyone with the public " +
                        "key can verify it without a database lookup. Stateless. " +
                        "Sent in the Authorization: Bearer <token> header.\n\n" +
                        "OAuth 2.0 — delegated authorization. A user grants your app access to " +
                        "their Google/GitHub account without sharing their password. " +
                        "Issues access tokens (often JWTs). Used by social login ('Login with Google').\n\n" +
                        "Always use HTTPS — tokens in plain HTTP can be intercepted."
                    )
                    CodeBlock(
                        "// API key in header\n" +
                        "GET /api/data\n" +
                        "X-API-Key: sk-abc123...\n\n" +
                        "// JWT Bearer token\n" +
                        "GET /api/profile\n" +
                        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0MiJ9.xyz\n\n" +
                        "// JWT structure: header.payload.signature\n" +
                        "// Decoded payload:\n" +
                        "{\n" +
                        "  \"sub\": \"42\",         // subject (user id)\n" +
                        "  \"name\": \"Alice\",\n" +
                        "  \"role\": \"admin\",\n" +
                        "  \"exp\": 1735689600   // expiry (Unix timestamp)\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Pagination and Filtering") {
                    BodyText(
                        "Never return thousands of records in one response — use pagination.\n\n" +
                        "Offset pagination — simple; skip N records:\n" +
                        "  GET /users?page=3&limit=20\n" +
                        "  Downside: inconsistent if records are inserted/deleted mid-session.\n\n" +
                        "Cursor-based pagination — more robust for large datasets:\n" +
                        "  GET /users?after=user_xyz&limit=20\n" +
                        "  Server returns a nextCursor; client uses it for the next page.\n\n" +
                        "Filtering and sorting:\n" +
                        "  GET /products?category=books&minPrice=10&maxPrice=50&sort=-createdAt\n" +
                        "  (- prefix means descending)\n\n" +
                        "Return pagination metadata in the response:"
                    )
                    CodeBlock(
                        "// GET /users?page=2&limit=20\n" +
                        "{\n" +
                        "  \"data\": [ { \"id\": 21, \"name\": \"...\" }, ... ],\n" +
                        "  \"pagination\": {\n" +
                        "    \"page\":       2,\n" +
                        "    \"limit\":      20,\n" +
                        "    \"total\":      145,\n" +
                        "    \"totalPages\": 8\n" +
                        "  }\n" +
                        "}\n\n" +
                        "// Or in Link header (RFC 5988)\n" +
                        "Link: </users?page=3&limit=20>; rel=\"next\",\n" +
                        "      </users?page=1&limit=20>; rel=\"prev\""
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "REST Best Practices") {
                    BodyText(
                        "Version your API — /v1/users — so you can evolve without breaking " +
                        "existing clients.\n\n" +
                        "Use consistent error objects — clients can handle all errors the same way.\n\n" +
                        "Idempotency keys — for POST requests where duplicate delivery is possible " +
                        "(e.g. payment): client sends a unique Idempotency-Key header; server " +
                        "deduplicates by returning the same result for repeated keys.\n\n" +
                        "Use HTTPS — always. No exceptions for production APIs.\n\n" +
                        "Rate limiting — protect against abuse; return 429 with Retry-After header.\n\n" +
                        "Document your API — OpenAPI / Swagger spec lets you generate client " +
                        "SDKs, interactive docs (Swagger UI), and test collections.\n\n" +
                        "HATEOAS (optional) — Hypermedia As The Engine Of Application State — " +
                        "include links in responses so clients can discover available actions. " +
                        "Rarely implemented fully in practice."
                    )
                    CodeBlock(
                        "// Versioned API with OpenAPI\n" +
                        "BASE URL: https://api.example.com/v1\n\n" +
                        "// Idempotency key (payment)\n" +
                        "POST /v1/payments\n" +
                        "Idempotency-Key: 550e8400-e29b-41d4-a716-446655440000\n" +
                        "{ \"amount\": 5000, \"currency\": \"USD\" }\n\n" +
                        "// Rate limit response\n" +
                        "HTTP/1.1 429 Too Many Requests\n" +
                        "Retry-After: 60\n" +
                        "X-RateLimit-Limit: 100\n" +
                        "X-RateLimit-Remaining: 0\n\n" +
                        "// HATEOAS links in response\n" +
                        "{\n" +
                        "  \"id\": 42,\n" +
                        "  \"name\": \"Alice\",\n" +
                        "  \"_links\": {\n" +
                        "    \"self\":   { \"href\": \"/v1/users/42\" },\n" +
                        "    \"orders\": { \"href\": \"/v1/users/42/orders\" }\n" +
                        "  }\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
