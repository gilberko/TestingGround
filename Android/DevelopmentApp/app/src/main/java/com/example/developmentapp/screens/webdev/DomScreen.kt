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
fun DomScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "DOM — Document Object Model",
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
                SectionCard(title = "What Is the DOM?") {
                    BodyText(
                        "The Document Object Model (DOM) is the browser's in-memory, " +
                        "object-oriented representation of an HTML (or XML) page. When the browser " +
                        "parses your HTML, it builds a tree of objects — one object per element, " +
                        "text node, comment, and attribute.\n\n" +
                        "The DOM is a W3C/WHATWG standard interface, not a JavaScript-specific " +
                        "thing. It is also accessible from Java via JAXP in server-side XML " +
                        "processing. But in web development, JavaScript is by far the primary " +
                        "language used to read and modify it."
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "DOM Tree Structure") {
                    BodyText(
                        "The DOM is a tree of nodes. The root is the Document node. Below it are " +
                        "the html element, then head and body, and so on recursively.\n\n" +
                        "Node types:\n" +
                        "• Element node — represents an HTML tag (div, p, a, img...)\n" +
                        "• Text node — the text content inside an element\n" +
                        "• Attribute node — an attribute on an element (id, class, href...)\n" +
                        "• Comment node — <!-- HTML comments -->"
                    )
                    CodeBlock(
                        "<!-- HTML source -->\n" +
                        "<html>\n" +
                        "  <body>\n" +
                        "    <h1 id=\"title\">Hello</h1>\n" +
                        "    <p class=\"intro\">World</p>\n" +
                        "  </body>\n" +
                        "</html>\n\n" +
                        "// DOM tree:\n" +
                        "Document\n" +
                        "  └── html (Element)\n" +
                        "        └── body (Element)\n" +
                        "              ├── h1 id=\"title\" (Element)\n" +
                        "              │     └── \"Hello\" (Text)\n" +
                        "              └── p class=\"intro\" (Element)\n" +
                        "                    └── \"World\" (Text)"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Why the DOM Matters") {
                    BodyText(
                        "The DOM is why web pages feel interactive. Without the DOM:\n\n" +
                        "• You could not change the text of an element after page load\n" +
                        "• You could not show or hide elements in response to clicks\n" +
                        "• You could not add list items without reloading the page\n" +
                        "• Single-page applications (React, Vue) would be impossible\n\n" +
                        "When JavaScript modifies a DOM node, the browser immediately updates " +
                        "what the user sees — no HTTP request, no reload. This is what makes " +
                        "the web feel like a native application."
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "JavaScript and the DOM") {
                    BodyText(
                        "JavaScript accesses the DOM through the global document object. Common " +
                        "methods for finding elements:\n\n" +
                        "document.getElementById(id) — returns the one element with that id\n" +
                        "document.querySelector(css) — first element matching a CSS selector\n" +
                        "document.querySelectorAll(css) — NodeList of all matches\n\n" +
                        "Once you have an element, you can read or change it:\n" +
                        ".innerHTML — get/set the HTML inside an element (allows tags)\n" +
                        ".textContent — get/set plain text (no tag parsing, safer)\n" +
                        ".style.color — inline CSS property\n" +
                        ".classList.add/remove/toggle — CSS classes\n" +
                        ".setAttribute(name, value) — any attribute\n\n" +
                        "Adding new elements:\n" +
                        "document.createElement(tag) — creates a new node\n" +
                        "parent.appendChild(child) — inserts it into the tree"
                    )
                    CodeBlock(
                        "// Find elements\n" +
                        "const title = document.getElementById('title');\n" +
                        "const btn   = document.querySelector('.submit-btn');\n" +
                        "const items = document.querySelectorAll('li');\n\n" +
                        "// Read / change content\n" +
                        "console.log(title.textContent);      // \"Hello\"\n" +
                        "title.textContent = 'Hi there!';\n" +
                        "title.style.color = 'red';\n" +
                        "title.classList.add('highlight');\n\n" +
                        "// Create and append\n" +
                        "const li = document.createElement('li');\n" +
                        "li.textContent = 'New item';\n" +
                        "document.getElementById('list').appendChild(li);\n\n" +
                        "// Listen for events\n" +
                        "btn.addEventListener('click', function(event) {\n" +
                        "    event.preventDefault();\n" +
                        "    console.log('clicked!');\n" +
                        "});"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "DOM Manipulation Examples") {
                    BodyText(
                        "A few practical patterns you will see constantly:"
                    )
                    CodeBlock(
                        "// Toggle visibility\n" +
                        "const panel = document.getElementById('info');\n" +
                        "panel.style.display = (panel.style.display === 'none') ? 'block' : 'none';\n\n" +
                        "// Form validation — read input value\n" +
                        "const email = document.getElementById('email').value;\n" +
                        "if (!email.includes('@')) {\n" +
                        "    document.getElementById('error').textContent = 'Invalid email';\n" +
                        "}\n\n" +
                        "// Build a list from data\n" +
                        "const names = ['Alice', 'Bob', 'Carol'];\n" +
                        "const ul = document.getElementById('people');\n" +
                        "names.forEach(name => {\n" +
                        "    const li = document.createElement('li');\n" +
                        "    li.textContent = name;\n" +
                        "    ul.appendChild(li);\n" +
                        "});\n\n" +
                        "// Remove an element\n" +
                        "const old = document.getElementById('old-banner');\n" +
                        "old.parentNode.removeChild(old);  // or: old.remove();"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Can Server-Side Scripts Access the DOM?") {
                    BodyText(
                        "No — not directly. Server-side languages like PHP, Python, Ruby, and " +
                        "ASP.NET run on the server, before the browser has loaded the page. They " +
                        "do not have access to the live DOM in the browser.\n\n" +
                        "What server-side code does instead:\n\n" +
                        "• Generates the initial HTML string that becomes the source of the DOM " +
                        "when the browser parses it. For example, a PHP script builds the HTML " +
                        "markup with the correct data already embedded.\n\n" +
                        "• Provides a REST/GraphQL API that JavaScript (running in the browser) " +
                        "calls to fetch or submit data, then updates the DOM itself.\n\n" +
                        "Exception — headless browsers:\n" +
                        "Tools like Puppeteer (Node.js) or Selenium control a real headless " +
                        "browser (Chromium) from the server. They load the page, execute its " +
                        "JavaScript, and CAN interact with the live DOM. This is used for " +
                        "web scraping, automated testing, and server-side screenshot/PDF generation."
                    )
                    CodeBlock(
                        "// PHP: generates HTML — this becomes the DOM source\n" +
                        "<?php\n" +
                        "  \$user = fetchUser(42);\n" +
                        "  echo \"<h1>\" . htmlspecialchars(\$user['name']) . \"</h1>\";\n" +
                        "?>\n\n" +
                        "// Puppeteer (Node.js on server): real DOM access\n" +
                        "const browser = await puppeteer.launch();\n" +
                        "const page    = await browser.newPage();\n" +
                        "await page.goto('https://example.com');\n" +
                        "const title   = await page.\$eval('h1', el => el.textContent);\n" +
                        "console.log(title);  // 'Example Domain'"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Virtual DOM") {
                    BodyText(
                        "Directly manipulating the real DOM is slow when many nodes change at once, " +
                        "because the browser must re-layout and repaint.\n\n" +
                        "React and Vue introduced the Virtual DOM: a lightweight JavaScript " +
                        "object tree that mirrors the real DOM. When state changes:\n\n" +
                        "1. A new Virtual DOM tree is created for the new state\n" +
                        "2. The framework diffs (compares) old and new Virtual DOM trees\n" +
                        "3. Only the real DOM nodes that actually changed are updated\n\n" +
                        "This batching and diffing minimizes expensive real DOM mutations, " +
                        "making large dynamic UIs much faster."
                    )
                    CodeBlock(
                        "// React example: you don't touch the DOM directly\n" +
                        "function Counter() {\n" +
                        "    const [count, setCount] = React.useState(0);\n" +
                        "    return (\n" +
                        "        <div>\n" +
                        "            <p>Count: {count}</p>\n" +
                        "            <button onClick={() => setCount(count + 1)}>+</button>\n" +
                        "        </div>\n" +
                        "    );\n" +
                        "}\n" +
                        "// React diffs the Virtual DOM and updates only the <p> text node"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
