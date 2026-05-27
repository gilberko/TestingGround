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
fun JavascriptScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "JavaScript",
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
                SectionCard(title = "What Is JavaScript?") {
                    BodyText(
                        "JavaScript (JS) is a lightweight, interpreted, event-driven programming " +
                        "language. It was created by Brendan Eich at Netscape in 1995 in just " +
                        "10 days — initially called Mocha, then LiveScript, then JavaScript. " +
                        "The name is a marketing decision; it is NOT related to Java.\n\n" +
                        "Standardized as ECMAScript (ES) by ECMA International. Major versions:\n" +
                        "ES5 (2009) — strict mode, Array methods\n" +
                        "ES6/ES2015 — let/const, arrow functions, classes, promises, modules\n" +
                        "ES2017 — async/await\n" +
                        "ES2024 — ongoing annual releases\n\n" +
                        "JavaScript runs in the browser (client-side) and on the server via " +
                        "Node.js. It is the only language that runs natively in all browsers."
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "When Does It Run?") {
                    BodyText(
                        "JavaScript is event-driven — there is no main() that runs top to bottom " +
                        "once and exits. Instead, code runs in response to events.\n\n" +
                        "Inline scripts (no attributes) — block HTML parsing and run immediately " +
                        "as the browser encounters the <script> tag. Avoid for large scripts.\n\n" +
                        "defer — script downloads in parallel, runs after HTML is fully parsed. " +
                        "Safe to access all DOM elements. Scripts run in order.\n\n" +
                        "async — script downloads in parallel, runs as soon as it downloads " +
                        "(may interrupt parsing). Order not guaranteed.\n\n" +
                        "After load, JS responds to events: user clicks, key presses, timers, " +
                        "network responses, page visibility changes."
                    )
                    CodeBlock(
                        "<!-- defer: safe default for most scripts -->\n" +
                        "<script src=\"app.js\" defer></script>\n\n" +
                        "<!-- async: for independent scripts like analytics -->\n" +
                        "<script src=\"analytics.js\" async></script>\n\n" +
                        "// Event-driven execution:\n" +
                        "document.getElementById('btn').addEventListener('click', handleClick);\n" +
                        "setTimeout(() => console.log('after 2s'), 2000);\n" +
                        "fetch('/api').then(r => r.json()).then(data => render(data));"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Variables and State") {
                    BodyText(
                        "var — function-scoped, hoisted to top of function, can be redeclared. " +
                        "Avoid in modern code.\n\n" +
                        "let — block-scoped, not hoisted into the temporal dead zone, cannot be " +
                        "redeclared in same scope. Use for mutable variables.\n\n" +
                        "const — block-scoped, must be initialized, cannot be reassigned. " +
                        "Does NOT mean immutable — object properties can still change. " +
                        "Use by default; switch to let only when you need to reassign.\n\n" +
                        "Types are dynamic — a variable can hold any type. Types: string, number " +
                        "(always 64-bit float), boolean, null, undefined, symbol, bigint, object.\n\n" +
                        "State lives in JavaScript memory for the lifetime of the current page. " +
                        "Refreshing the page destroys all variables."
                    )
                    CodeBlock(
                        "const name  = 'Alice';          // string\n" +
                        "let   count = 0;               // number\n" +
                        "let   flag  = true;            // boolean\n\n" +
                        "count = count + 1;             // reassign: ok (let)\n" +
                        "// name = 'Bob';               // error: const\n\n" +
                        "const user = { id: 1, role: 'admin' };\n" +
                        "user.role = 'user';            // ok — object property changes\n" +
                        "// user = {};                  // error — cannot reassign const\n\n" +
                        "console.log(typeof name);      // 'string'\n" +
                        "console.log(typeof count);     // 'number'\n" +
                        "console.log(typeof null);      // 'object' (historical bug)"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Persistence — Storing Data") {
                    BodyText(
                        "Variables live only while the page is open. To persist data:\n\n" +
                        "localStorage — key-value strings; survives page close and refresh; " +
                        "cleared only by JS or user action; ~5 MB; same-origin only.\n\n" +
                        "sessionStorage — same API but cleared when the tab/window closes.\n\n" +
                        "Cookies — small text; sent to server with every request; useful for " +
                        "auth tokens. Set via document.cookie (clunky) or server Set-Cookie header.\n\n" +
                        "IndexedDB — full client-side database; stores objects, blobs; async; " +
                        "larger capacity; used by service workers for offline-first apps.\n\n" +
                        "Always serialize objects to JSON before storing in localStorage."
                    )
                    CodeBlock(
                        "// localStorage — store and retrieve\n" +
                        "localStorage.setItem('token', 'eyJ...');\n" +
                        "const token = localStorage.getItem('token');\n" +
                        "localStorage.removeItem('token');\n\n" +
                        "// Store objects (must serialize)\n" +
                        "const prefs = { theme: 'dark', lang: 'en' };\n" +
                        "localStorage.setItem('prefs', JSON.stringify(prefs));\n" +
                        "const loaded = JSON.parse(localStorage.getItem('prefs'));\n\n" +
                        "// sessionStorage — same API, clears on tab close\n" +
                        "sessionStorage.setItem('step', '3');"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Common API") {
                    BodyText(
                        "Console:\n" +
                        "  console.log(), console.error(), console.warn(), console.table()\n\n" +
                        "Timers:\n" +
                        "  setTimeout(fn, ms) — run once after delay\n" +
                        "  setInterval(fn, ms) — run repeatedly; clearInterval(id) to stop\n\n" +
                        "Network:\n" +
                        "  fetch(url, options) — returns Promise<Response>\n" +
                        "  response.json() — parse body as JSON\n" +
                        "  response.text() — parse body as string\n\n" +
                        "Math and Date:\n" +
                        "  Math.floor/ceil/round/random/abs/max/min/sqrt/pow\n" +
                        "  new Date() — current date/time; .getFullYear()/.getMonth()/.getDate()\n\n" +
                        "Strings: .length, .toUpperCase(), .trim(), .split(), .includes(), .startsWith(),\n" +
                        "  .replace(), .slice(), .substring(), template literals: `Hello \${name}`\n\n" +
                        "Arrays: .push(), .pop(), .shift(), .unshift(), .map(), .filter(), " +
                        ".reduce(), .find(), .some(), .every(), .sort(), .flat(), .forEach()"
                    )
                    CodeBlock(
                        "// Fetch with async/await\n" +
                        "async function loadUser(id) {\n" +
                        "    const res  = await fetch(`/api/users/\${id}`);\n" +
                        "    if (!res.ok) throw new Error('Not found');\n" +
                        "    const user = await res.json();\n" +
                        "    return user;\n" +
                        "}\n\n" +
                        "// Array methods\n" +
                        "const nums = [1, 2, 3, 4, 5];\n" +
                        "const evens = nums.filter(n => n % 2 === 0);  // [2, 4]\n" +
                        "const doubled = nums.map(n => n * 2);          // [2,4,6,8,10]\n" +
                        "const sum = nums.reduce((acc, n) => acc + n, 0); // 15"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "DOM Interaction and Events") {
                    BodyText(
                        "JavaScript's main job in the browser is reading and modifying the DOM " +
                        "in response to events. (See the DOM screen for full details.)\n\n" +
                        "addEventListener(event, handler) — attaches an event listener.\n\n" +
                        "Common events: click, dblclick, mouseenter, mouseleave, keydown, keyup, " +
                        "input, change, submit, focus, blur, scroll, resize, load, DOMContentLoaded\n\n" +
                        "The event object (e) passed to handlers contains:\n" +
                        "  e.target — the element that triggered the event\n" +
                        "  e.currentTarget — the element the listener is attached to\n" +
                        "  e.preventDefault() — cancel default browser behavior (e.g. form submit)\n" +
                        "  e.stopPropagation() — stop event bubbling up the DOM tree\n" +
                        "  e.key / e.keyCode — which key was pressed\n" +
                        "  e.clientX / e.clientY — mouse coordinates"
                    )
                    CodeBlock(
                        "document.getElementById('form').addEventListener('submit', (e) => {\n" +
                        "    e.preventDefault();  // stop browser from reloading\n" +
                        "    const name = document.getElementById('name').value.trim();\n" +
                        "    if (!name) {\n" +
                        "        document.getElementById('error').textContent = 'Name required';\n" +
                        "        return;\n" +
                        "    }\n" +
                        "    console.log('Submitting:', name);\n" +
                        "});\n\n" +
                        "document.addEventListener('keydown', (e) => {\n" +
                        "    if (e.key === 'Escape') closeModal();\n" +
                        "});"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "User Input") {
                    BodyText(
                        "Reading input values:\n" +
                        "  input.value — current text/number in a text/number input\n" +
                        "  input.checked — boolean for checkboxes and radio buttons\n" +
                        "  select.value — currently selected option value\n" +
                        "  textarea.value — multi-line text\n\n" +
                        "Input events:\n" +
                        "  'input' — fires on every keystroke (real-time)\n" +
                        "  'change' — fires when the element loses focus after a change\n" +
                        "  'submit' — fires on <form> when submitted\n\n" +
                        "Always trim and validate input before using it."
                    )
                    CodeBlock(
                        "// Real-time character counter\n" +
                        "const textarea = document.getElementById('bio');\n" +
                        "const counter  = document.getElementById('count');\n\n" +
                        "textarea.addEventListener('input', () => {\n" +
                        "    const remaining = 200 - textarea.value.length;\n" +
                        "    counter.textContent = `\${remaining} characters left`;\n" +
                        "    counter.style.color = remaining < 20 ? 'red' : 'white';\n" +
                        "});\n\n" +
                        "// Reading a checkbox\n" +
                        "const agreed = document.getElementById('terms').checked;\n" +
                        "if (!agreed) alert('Please accept the terms');"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "ES6+ Features") {
                    BodyText(
                        "Arrow functions — shorter syntax; do not have their own 'this':\n" +
                        "  const add = (a, b) => a + b;\n\n" +
                        "Template literals — embed expressions in strings:\n" +
                        "  `Hello \${name}, you are \${age} years old`\n\n" +
                        "Destructuring — extract values from arrays/objects:\n" +
                        "  const { id, name } = user;\n" +
                        "  const [first, ...rest] = arr;\n\n" +
                        "Spread / rest:\n" +
                        "  const merged = { ...defaults, ...overrides };\n" +
                        "  function sum(...nums) { return nums.reduce((a,b) => a+b, 0); }\n\n" +
                        "Classes — syntactic sugar over prototypes\n\n" +
                        "Promises + async/await — handle async operations without callback hell\n\n" +
                        "Modules — import/export for splitting code across files\n\n" +
                        "Optional chaining (?.) and nullish coalescing (??) — ES2020\n" +
                        "  const city = user?.address?.city ?? 'Unknown';"
                    )
                    CodeBlock(
                        "// Async/await with error handling\n" +
                        "async function getData() {\n" +
                        "    try {\n" +
                        "        const res  = await fetch('/api/items');\n" +
                        "        const data = await res.json();\n" +
                        "        return data;\n" +
                        "    } catch (err) {\n" +
                        "        console.error('Failed:', err);\n" +
                        "        return [];\n" +
                        "    }\n" +
                        "}\n\n" +
                        "// Destructuring + spread\n" +
                        "const { name, age, ...rest } = { name:'Alice', age:30, city:'NY' };\n" +
                        "const copy = { ...rest, verified: true };\n\n" +
                        "// Optional chaining\n" +
                        "const zip = user?.address?.zip ?? 'N/A';"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
