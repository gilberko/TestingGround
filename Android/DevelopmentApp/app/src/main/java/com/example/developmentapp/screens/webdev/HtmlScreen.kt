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
fun HtmlScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "HTML",
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
                SectionCard(title = "HTML4 — The Foundation (1997)") {
                    BodyText(
                        "HTML4.01 (1999) was the dominant standard for most of the 2000s. It defined " +
                        "the core concept of tags (elements) and attributes.\n\n" +
                        "Key ideas that remain true today:\n" +
                        "• Tags are wrapped in angle brackets: <p>, <a>, <div>\n" +
                        "• Most tags have an opening <tag> and a closing </tag>\n" +
                        "• Attributes go inside the opening tag: <a href=\"url\">\n" +
                        "• Block elements (div, p, h1-h6, table) start on a new line\n" +
                        "• Inline elements (span, a, strong, em) flow with text\n\n" +
                        "HTML4 also included many now-deprecated presentational tags: " +
                        "<font> for color and size, <center> for centering, <b> for bold " +
                        "with no semantic meaning. CSS replaced these entirely."
                    )
                    CodeBlock(
                        "<!-- HTML4 example -->\n" +
                        "<table border=\"1\">\n" +
                        "  <tr><td><font color=\"red\">Old style</font></td></tr>\n" +
                        "</table>\n\n" +
                        "<center><b>Avoid these in modern HTML</b></center>"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "XHTML — Stricter Rules (2000)") {
                    BodyText(
                        "XHTML (Extensible HTML) reformulated HTML as XML. It imposed strict rules:\n\n" +
                        "• All tags must be lowercase: <p> not <P>\n" +
                        "• All tags must be closed: <br /> not <br>\n" +
                        "• Attribute values must be quoted: href=\"url\" not href=url\n" +
                        "• No overlapping tags (well-formed XML)\n\n" +
                        "XHTML 1.0 was declared a W3C Recommendation in 2000 and was the " +
                        "recommended standard through most of the 2000s. XHTML 2.0 was " +
                        "abandoned in 2009 when the WHATWG and W3C converged on HTML5, " +
                        "which dropped the strict XML requirement but kept most of the " +
                        "good practices (lowercase tags, quoted attrs) as conventions."
                    )
                    CodeBlock(
                        "<!-- XHTML: self-closing void elements, lowercase, quoted -->\n" +
                        "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\n" +
                        "  \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
                        "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                        "  <head><title>Page</title></head>\n" +
                        "  <body>\n" +
                        "    <p>Text with a line break<br /></p>\n" +
                        "    <img src=\"pic.jpg\" alt=\"desc\" />\n" +
                        "  </body>\n" +
                        "</html>"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "HTML5 — The Modern Standard (2014)") {
                    BodyText(
                        "HTML5 (W3C Recommendation 2014, actively updated as a \"Living Standard\") " +
                        "made major improvements:\n\n" +
                        "Semantic elements — convey meaning beyond visual style:\n" +
                        "<header>, <nav>, <main>, <article>, <section>, <aside>, <footer>\n" +
                        "<figure>, <figcaption>, <time>, <mark>, <details>, <summary>\n\n" +
                        "Native multimedia — no plugins required:\n" +
                        "<video src=\"movie.mp4\" controls>\n" +
                        "<audio src=\"song.mp3\" controls>\n" +
                        "<canvas> — 2D/3D drawing surface (JavaScript API)\n\n" +
                        "Better forms — new input types with built-in validation:\n" +
                        "type=\"email\", type=\"url\", type=\"number\", type=\"date\",\n" +
                        "type=\"range\", type=\"color\", type=\"search\", required attribute\n\n" +
                        "APIs moved to JavaScript: localStorage, sessionStorage, " +
                        "Geolocation, WebSocket, Web Workers, History API, Fetch API\n\n" +
                        "Dropped: frames, <font>, <center>, <big>, <strike>, <applet>"
                    )
                    CodeBlock(
                        "<!-- HTML5 semantic layout -->\n" +
                        "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "  <meta charset=\"UTF-8\">\n" +
                        "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                        "  <title>My Page</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "  <header><nav>...</nav></header>\n" +
                        "  <main>\n" +
                        "    <article>\n" +
                        "      <h1>Title</h1>\n" +
                        "      <p>Content</p>\n" +
                        "    </article>\n" +
                        "  </main>\n" +
                        "  <footer>...</footer>\n" +
                        "</body>\n" +
                        "</html>"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Core HTML Structure") {
                    BodyText(
                        "<!DOCTYPE html> — must be the very first line. Tells the browser " +
                        "to use standards mode (not quirks mode).\n\n" +
                        "<html lang=\"en\"> — root element; lang attribute helps screen readers " +
                        "and search engines.\n\n" +
                        "<head> — metadata: title, charset, viewport, CSS links, meta description. " +
                        "Nothing here is visible to the user.\n\n" +
                        "<meta charset=\"UTF-8\"> — essential; supports all Unicode characters.\n\n" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"> " +
                        "— essential for mobile-responsive design.\n\n" +
                        "<body> — all visible content goes here.\n\n" +
                        "Script placement: <script> tags at the end of <body> (or use " +
                        "defer/async attributes in <head>) to avoid blocking page rendering."
                    )
                    CodeBlock(
                        "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "  <meta charset=\"UTF-8\">\n" +
                        "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "  <meta name=\"description\" content=\"My web page\">\n" +
                        "  <title>Page Title</title>\n" +
                        "  <link rel=\"stylesheet\" href=\"styles.css\">\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "  <h1>Hello</h1>\n" +
                        "  <script src=\"app.js\" defer></script>\n" +
                        "</body>\n" +
                        "</html>"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Key Tags and Attributes") {
                    BodyText(
                        "Text and structure: h1-h6 (headings), p (paragraph), br (line break), " +
                        "hr (horizontal rule), blockquote, pre (preformatted), code\n\n" +
                        "Inline formatting: strong (bold/important), em (italic/emphasis), " +
                        "span (generic inline container), small, sub, sup\n\n" +
                        "Links and media: a href (hyperlink), img src alt (image, void element), " +
                        "video src controls, audio src controls\n\n" +
                        "Lists: ul (unordered), ol (ordered), li (list item), dl/dt/dd (definition list)\n\n" +
                        "Tables: table, thead, tbody, tfoot, tr (row), th (header cell), td (data cell)\n\n" +
                        "Layout containers: div (block), span (inline)\n\n" +
                        "Global attributes on any element:\n" +
                        "id — unique identifier (used by CSS and JS)\n" +
                        "class — one or more CSS classes\n" +
                        "style — inline CSS (avoid for maintainability)\n" +
                        "data-* — custom data attributes readable by JavaScript"
                    )
                    CodeBlock(
                        "<h1>Main Heading</h1>\n" +
                        "<p>Paragraph with <strong>bold</strong> and <em>italic</em>.</p>\n\n" +
                        "<a href=\"https://example.com\" target=\"_blank\" rel=\"noopener\">Link</a>\n" +
                        "<img src=\"photo.jpg\" alt=\"A descriptive caption\" width=\"300\">\n\n" +
                        "<ul>\n" +
                        "  <li>Item one</li>\n" +
                        "  <li>Item two</li>\n" +
                        "</ul>\n\n" +
                        "<div id=\"hero\" class=\"banner featured\" data-theme=\"dark\">\n" +
                        "  <span>Inside div</span>\n" +
                        "</div>"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "State in HTML — None") {
                    BodyText(
                        "HTML itself is stateless — it is just markup. Once rendered, it has no " +
                        "memory of previous page loads or user actions.\n\n" +
                        "To persist data across pages or sessions, you use other mechanisms:\n\n" +
                        "Cookies — small key-value pairs stored in the browser; sent to the server " +
                        "with every HTTP request; set by server (Set-Cookie header) or by JS " +
                        "(document.cookie). Expire at a set date.\n\n" +
                        "localStorage — JS-only key-value store; persists until explicitly cleared; " +
                        "not sent to server; ~5 MB limit; same-origin only.\n\n" +
                        "sessionStorage — same as localStorage but cleared when the tab closes.\n\n" +
                        "URL query parameters — /search?q=cats&page=2 — readable by server and JS.\n\n" +
                        "Hidden form fields — <input type=\"hidden\" name=\"token\" value=\"abc\"> " +
                        "— submitted with the form but invisible to user."
                    )
                    CodeBlock(
                        "// localStorage\n" +
                        "localStorage.setItem('theme', 'dark');\n" +
                        "const theme = localStorage.getItem('theme');  // 'dark'\n" +
                        "localStorage.removeItem('theme');\n\n" +
                        "// sessionStorage\n" +
                        "sessionStorage.setItem('step', '2');\n\n" +
                        "// Hidden form field\n" +
                        "<form method=\"POST\" action=\"/checkout\">\n" +
                        "  <input type=\"hidden\" name=\"csrf_token\" value=\"xyz\">\n" +
                        "  <button type=\"submit\">Pay</button>\n" +
                        "</form>"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Forms and User Input") {
                    BodyText(
                        "<form> — groups inputs for submission. Key attributes:\n" +
                        "  action=\"/url\" — where to send the data (defaults to current URL)\n" +
                        "  method=\"POST\" (or GET) — HTTP method\n\n" +
                        "<input> — void element; type attribute controls behavior:\n" +
                        "  text, password, email, number, tel, url, search, date, time,\n" +
                        "  checkbox, radio, file, range, color, hidden, submit, reset\n\n" +
                        "<textarea> — multi-line text\n" +
                        "<select> + <option> — dropdown\n" +
                        "<label for=\"id\"> — associates label with input (improves accessibility)\n" +
                        "required, minlength, maxlength, min, max, pattern — HTML5 validation attrs\n" +
                        "placeholder — hint text inside empty input"
                    )
                    CodeBlock(
                        "<form method=\"POST\" action=\"/register\">\n\n" +
                        "  <label for=\"name\">Name</label>\n" +
                        "  <input type=\"text\" id=\"name\" name=\"name\"\n" +
                        "         required placeholder=\"Your name\">\n\n" +
                        "  <label for=\"email\">Email</label>\n" +
                        "  <input type=\"email\" id=\"email\" name=\"email\" required>\n\n" +
                        "  <label for=\"age\">Age</label>\n" +
                        "  <input type=\"number\" id=\"age\" name=\"age\" min=\"1\" max=\"120\">\n\n" +
                        "  <label for=\"country\">Country</label>\n" +
                        "  <select id=\"country\" name=\"country\">\n" +
                        "    <option value=\"us\">United States</option>\n" +
                        "    <option value=\"uk\">United Kingdom</option>\n" +
                        "  </select>\n\n" +
                        "  <button type=\"submit\">Register</button>\n" +
                        "</form>"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "HTML5 APIs (via JavaScript)") {
                    BodyText(
                        "HTML5 introduced APIs that JavaScript accesses through standardized browser " +
                        "objects. HTML declares the element; JS provides the functionality.\n\n" +
                        "Canvas 2D — draw shapes, images, and animations via JavaScript:\n" +
                        "  const ctx = canvas.getContext('2d');\n\n" +
                        "Geolocation — get user's position (requires permission):\n" +
                        "  navigator.geolocation.getCurrentPosition(cb)\n\n" +
                        "Web Storage — localStorage / sessionStorage (see State section)\n\n" +
                        "WebSockets — persistent bidirectional connection:\n" +
                        "  const ws = new WebSocket('wss://example.com');\n\n" +
                        "Fetch API — HTTP requests from JavaScript:\n" +
                        "  fetch('/api/data').then(r => r.json()).then(data => ...)\n\n" +
                        "History API — push states to the URL bar without reloading:\n" +
                        "  history.pushState({}, '', '/new-path');\n\n" +
                        "Web Workers — run JavaScript in a background thread:\n" +
                        "  const worker = new Worker('worker.js');"
                    )
                    CodeBlock(
                        "// Canvas: draw a filled circle\n" +
                        "<canvas id=\"c\" width=\"200\" height=\"200\"></canvas>\n\n" +
                        "<script>\n" +
                        "  const ctx = document.getElementById('c').getContext('2d');\n" +
                        "  ctx.fillStyle = 'lime';\n" +
                        "  ctx.beginPath();\n" +
                        "  ctx.arc(100, 100, 50, 0, Math.PI * 2);\n" +
                        "  ctx.fill();\n" +
                        "</script>\n\n" +
                        "// Fetch\n" +
                        "fetch('/api/users')\n" +
                        "  .then(res => res.json())\n" +
                        "  .then(users => console.log(users));"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
