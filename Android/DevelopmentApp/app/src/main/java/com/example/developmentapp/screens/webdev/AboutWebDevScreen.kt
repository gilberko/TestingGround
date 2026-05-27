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
fun AboutWebDevScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "About Web Development",
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
                SectionCard(title = "Static vs Dynamic Websites") {
                    BodyText(
                        "A static website serves the same pre-built HTML files to every visitor. " +
                        "The server simply hands the files to the browser — no code runs to generate " +
                        "them. Simple portfolios and documentation sites are often static.\n\n" +
                        "A dynamic website generates content on the fly. There are two ways:\n\n" +
                        "• Server-side rendering — the server runs code (PHP, Python, Node.js...) " +
                        "and sends back a freshly generated HTML page for each request. " +
                        "The browser receives finished HTML.\n\n" +
                        "• Client-side rendering — the server sends a minimal HTML shell plus " +
                        "JavaScript. The browser runs the JavaScript to fetch data and build the " +
                        "page. Used heavily by React, Vue, Angular apps."
                    )
                    CodeBlock(
                        "// Static: file is the same for everyone\n" +
                        "index.html  →  browser receives as-is\n\n" +
                        "// Server-side dynamic: PHP generates the page\n" +
                        "GET /profile/42  →  PHP reads DB, builds HTML  →  browser renders\n\n" +
                        "// Client-side dynamic: JS fetches and renders\n" +
                        "GET /app  →  empty HTML + React JS\n" +
                        "JS runs  →  fetch(\"/api/user/42\")  →  renders UI in browser"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Client-Side Technologies") {
                    BodyText(
                        "Client-side code runs inside the user's browser. The browser downloads " +
                        "these files and executes them locally — no round-trip to the server " +
                        "for each interaction.\n\n" +
                        "HTML (HyperText Markup Language) — defines the structure and content " +
                        "of the page: headings, paragraphs, links, forms, images.\n\n" +
                        "CSS (Cascading Style Sheets) — controls the visual presentation: " +
                        "colors, fonts, layout, animations, responsive breakpoints.\n\n" +
                        "JavaScript — adds behavior and interactivity: responds to clicks, " +
                        "validates forms, fetches data from APIs, updates the page without reload. " +
                        "Frameworks like React, Vue, and Angular build on JavaScript to create " +
                        "complex single-page applications."
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Server-Side Technologies") {
                    BodyText(
                        "Server-side code runs on the web server — the browser never sees it. " +
                        "It can access databases, file systems, environment variables, and other " +
                        "services that must not be exposed to the public.\n\n" +
                        "PHP — oldest and most widespread server-side language. Powers WordPress " +
                        "and roughly a third of all websites. Embedded directly into HTML.\n\n" +
                        "ASP / ASP.NET — Microsoft's server-side framework. Classic ASP used VBScript; " +
                        "ASP.NET uses C# or VB.NET with strong typing and high performance.\n\n" +
                        "Python — used via frameworks: Django (batteries-included, ORM, admin panel) " +
                        "and Flask (minimal, use what you need). Also FastAPI for async APIs.\n\n" +
                        "Node.js — JavaScript on the server. Non-blocking event loop. Popular with " +
                        "Express.js. Lets teams use JavaScript end-to-end.\n\n" +
                        "Ruby / Ruby on Rails — elegant syntax; Rails pioneered \"convention over " +
                        "configuration\" and is known for rapid development.\n\n" +
                        "TypeScript — compiles to JavaScript. Used on the server via Node.js with " +
                        "frameworks like NestJS. Adds strong typing to Node development."
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Frontend vs Backend") {
                    BodyText(
                        "Frontend (\"client side\") — everything the user sees and interacts with " +
                        "in the browser. HTML structure, CSS styling, JavaScript behavior, " +
                        "animations, forms. Frontend developers focus on user experience, " +
                        "accessibility, and performance in the browser.\n\n" +
                        "Backend (\"server side\") — the logic running on the server: processing " +
                        "requests, querying databases, authenticating users, applying business rules, " +
                        "sending emails. The browser never directly sees backend code.\n\n" +
                        "Full-Stack — a developer who works on both frontend and backend.\n\n" +
                        "The two sides communicate via HTTP: the frontend sends requests (often " +
                        "JSON via the Fetch API), the backend processes them and returns responses " +
                        "(HTML, JSON, files)."
                    )
                    CodeBlock(
                        "Browser (Frontend)              Server (Backend)\n" +
                        "─────────────────               ─────────────────\n" +
                        "HTML / CSS / JS                 PHP / Python / Node.js\n" +
                        "React / Vue / Angular           Express / Django / Laravel\n" +
                        "Fetch API / Axios               Database queries (SQL/NoSQL)\n" +
                        "LocalStorage / Cookies          File system / Auth / Email\n\n" +
                        "       ←── HTTP request ─────→\n" +
                        "       ←── JSON response ─────"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The Request-Response Cycle") {
                    BodyText(
                        "Every web interaction follows the same basic cycle:\n\n" +
                        "1. User types a URL or clicks a link in the browser\n" +
                        "2. Browser resolves the domain via DNS to an IP address\n" +
                        "3. Browser opens a TCP connection (and TLS handshake for HTTPS)\n" +
                        "4. Browser sends an HTTP request (GET, POST, etc.)\n" +
                        "5. Web server receives the request\n" +
                        "6. Server runs the backend script (PHP, Python, etc.) if dynamic\n" +
                        "7. Script may query a database and compose the response\n" +
                        "8. Server sends back an HTTP response with status code + body\n" +
                        "9. Browser parses HTML, fetches CSS/JS/images, and renders the page\n" +
                        "10. JavaScript runs and may make additional API calls (AJAX)"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Common Browsers") {
                    BodyText(
                        "Each browser has its own rendering engine (parses HTML/CSS) and " +
                        "JavaScript engine (executes JS). They all follow W3C/WHATWG standards " +
                        "but differ in performance, features, and privacy defaults.\n\n" +
                        "Chrome — Google; Blink rendering engine; V8 JavaScript engine. " +
                        "Most popular worldwide (~65% market share). Used in Electron apps.\n\n" +
                        "Firefox — Mozilla; Gecko engine; SpiderMonkey JS. Privacy-focused; " +
                        "strong developer tools.\n\n" +
                        "Safari — Apple; WebKit engine; JavaScriptCore. Default on macOS/iOS. " +
                        "iOS forces all third-party browsers to use WebKit too.\n\n" +
                        "Edge — Microsoft; switched from EdgeHTML to Chromium/Blink in 2020. " +
                        "Default on Windows; integrates with Microsoft 365.\n\n" +
                        "Opera — Presto engine historically; now Chromium/Blink. Known for " +
                        "built-in VPN and data-saving mode."
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Common Web Servers") {
                    BodyText(
                        "A web server listens for incoming HTTP requests and serves responses. " +
                        "It may serve static files directly, or hand off to an application " +
                        "server (PHP-FPM, Gunicorn, Node process) for dynamic content.\n\n" +
                        "Apache HTTP Server (httpd) — the most historically common server. " +
                        "Flexible .htaccess per-directory configuration. Module-based architecture.\n\n" +
                        "Nginx (\"engine-x\") — event-driven, highly efficient for concurrent " +
                        "connections. Widely used as a reverse proxy and load balancer in front " +
                        "of application servers.\n\n" +
                        "IIS (Internet Information Services) — Microsoft; Windows Server only. " +
                        "Tight integration with ASP.NET, Active Directory, and Windows auth.\n\n" +
                        "Tomcat — Apache; runs Java Servlet / JSP applications. Often paired " +
                        "with Nginx as a reverse proxy in front.\n\n" +
                        "LiteSpeed — commercial; drop-in Apache replacement with better " +
                        "performance; popular with cPanel hosting."
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
