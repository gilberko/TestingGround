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
fun PhpScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "PHP",
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
                SectionCard(title = "What Is PHP?") {
                    BodyText(
                        "PHP (PHP: Hypertext Preprocessor — a recursive acronym) is a widely-used " +
                        "open-source server-side scripting language designed specifically for web " +
                        "development. PHP code runs on the web server; the browser only receives " +
                        "the generated HTML output.\n\n" +
                        "Key characteristics:\n" +
                        "• Dynamically typed — no need to declare variable types\n" +
                        "• Interpreted — no separate compile step\n" +
                        "• Embedded in HTML — mix PHP and HTML in .php files\n" +
                        "• Huge ecosystem — powers WordPress, Drupal, Laravel, Symfony\n" +
                        "• Powers ~78% of server-side websites (including WordPress)"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "History") {
                    BodyText(
                        "PHP/FI (Personal Home Page / Forms Interpreter) — 1994, created by " +
                        "Rasmus Lerdorf as a set of Perl scripts to track visits to his resume.\n\n" +
                        "PHP 3 — 1997, rewritten by Zeev Suraski and Andi Gutmans; first version " +
                        "to look like the PHP we know today.\n\n" +
                        "PHP 4 — 2000, introduced the Zend Engine scripting engine for better " +
                        "performance.\n\n" +
                        "PHP 5 — 2004, major OOP improvements: real classes, interfaces, exceptions, " +
                        "PDO for database access.\n\n" +
                        "PHP 7 — 2015, massive performance improvement (up to 2x over PHP 5); " +
                        "scalar type declarations, return types, null coalescing (??).\n\n" +
                        "PHP 8 — 2020+, JIT compiler, named arguments, match expression, " +
                        "union types, fibers (cooperative multitasking)."
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Basic Syntax") {
                    BodyText(
                        "PHP code lives between <?php and ?> tags, embedded in HTML. " +
                        "If a file is pure PHP, omit the closing ?> to avoid accidental whitespace.\n\n" +
                        "Statements end with semicolons. Comments: // (single-line), " +
                        "# (single-line), /* ... */ (block).\n\n" +
                        "echo outputs text. print works similarly but returns 1.\n\n" +
                        "Variables: all variable names start with a dollar sign, no declaration " +
                        "needed. PHP is case-sensitive for variables but NOT for function names."
                    )
                    CodeBlock(
                        "<!-- PHP embedded in HTML -->\n" +
                        "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<body>\n" +
                        "  <?php\n" +
                        "    // Single-line comment\n" +
                        "    /* Multi-line comment */\n\n" +
                        "    \$name    = 'Alice';\n" +
                        "    \$age     = 30;\n" +
                        "    \$active  = true;\n\n" +
                        "    echo '<h1>Hello, ' . \$name . '!</h1>';\n" +
                        "    echo \"You are \$age years old.\";  // double quotes interpolate\n" +
                        "  ?>\n" +
                        "</body>\n" +
                        "</html>"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Variables, Types, and State") {
                    BodyText(
                        "PHP types: string, integer, float, boolean, array, null, object, resource.\n" +
                        "Type juggling — PHP automatically converts types in comparisons. " +
                        "Use === (strict equality) to avoid surprises.\n\n" +
                        "Useful null-checking functions:\n" +
                        "  isset(\$var) — true if var exists and is not null\n" +
                        "  empty(\$var) — true if var is null, false, 0, '', [], '0'\n" +
                        "  is_null(\$var) — true if var is null\n\n" +
                        "State is per-request: PHP starts fresh for each HTTP request. " +
                        "Variables set during one request do not exist in the next.\n\n" +
                        "Arrays in PHP are ordered maps — they can use integer or string keys:\n" +
                        "  \$arr = [1, 2, 3];         — integer-keyed\n" +
                        "  \$map = ['key' => 'value']; — string-keyed (associative)"
                    )
                    CodeBlock(
                        "<?php\n" +
                        "\$num  = 42;\n" +
                        "\$pi   = 3.14;\n" +
                        "\$text = 'hello';\n" +
                        "\$flag = false;\n" +
                        "\$data = null;\n\n" +
                        "// Type juggling\n" +
                        "var_dump(0 == 'a');   // true  (loose)\n" +
                        "var_dump(0 === 'a');  // false (strict)\n\n" +
                        "// Arrays\n" +
                        "\$fruits = ['apple', 'banana', 'cherry'];\n" +
                        "echo \$fruits[0];  // apple\n" +
                        "\$fruits[] = 'date';  // append\n\n" +
                        "\$person = [\n" +
                        "    'name' => 'Bob',\n" +
                        "    'age'  => 25,\n" +
                        "];\n" +
                        "echo \$person['name'];  // Bob"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Passing Parameters — GET and POST") {
                    BodyText(
                        "HTTP GET — parameters in the URL query string:\n" +
                        "  URL: /search?q=cats&page=2\n" +
                        "  PHP: \$_GET['q'] gives 'cats'\n\n" +
                        "HTTP POST — parameters in the request body (form submission or API):\n" +
                        "  PHP: \$_POST['email'] gives the submitted value\n\n" +
                        "\$_REQUEST — combines \$_GET, \$_POST, and \$_COOKIE. Convenient but " +
                        "less explicit — prefer \$_GET or \$_POST directly.\n\n" +
                        "Security: ALWAYS sanitize and validate input. Never trust user data.\n" +
                        "htmlspecialchars(\$input) converts < > & \" ' to HTML entities, " +
                        "preventing XSS (Cross-Site Scripting) attacks.\n" +
                        "Use parameterized queries (PDO) to prevent SQL injection."
                    )
                    CodeBlock(
                        "<?php\n" +
                        "// GET: /profile?id=42\n" +
                        "\$id = isset(\$_GET['id']) ? (int)\$_GET['id'] : 0;\n" +
                        "echo \"Profile: \$id\";\n\n" +
                        "// POST: handle form submission\n" +
                        "if (\$_SERVER['REQUEST_METHOD'] === 'POST') {\n" +
                        "    \$email = htmlspecialchars(trim(\$_POST['email'] ?? ''));\n" +
                        "    \$pass  = \$_POST['password'] ?? '';\n\n" +
                        "    if (empty(\$email) || empty(\$pass)) {\n" +
                        "        \$error = 'All fields required';\n" +
                        "    } else {\n" +
                        "        // process...\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Sessions and Cookies") {
                    BodyText(
                        "Since PHP is stateless per-request, sessions and cookies are used to " +
                        "persist data across multiple requests from the same user.\n\n" +
                        "Sessions — server stores data; browser gets only a session ID cookie.\n" +
                        "  session_start() — must be called before any output\n" +
                        "  \$_SESSION['key'] = value — store data\n" +
                        "  session_destroy() — log out\n\n" +
                        "Cookies — data stored in the browser; sent to server on each request.\n" +
                        "  setcookie(name, value, expires, path, domain, secure, httponly)\n" +
                        "  \$_COOKIE['name'] — read a cookie\n\n" +
                        "For login state: store user ID in a session (not the password!). " +
                        "Mark the session cookie as HttpOnly + Secure + SameSite=Lax."
                    )
                    CodeBlock(
                        "<?php\n" +
                        "session_start();\n\n" +
                        "// Login: store user ID\n" +
                        "\$_SESSION['user_id'] = 42;\n" +
                        "\$_SESSION['role']    = 'admin';\n\n" +
                        "// Check authentication on protected pages\n" +
                        "if (!isset(\$_SESSION['user_id'])) {\n" +
                        "    header('Location: /login');\n" +
                        "    exit;\n" +
                        "}\n\n" +
                        "// Logout\n" +
                        "session_destroy();\n\n" +
                        "// Cookie (expires in 30 days)\n" +
                        "setcookie('theme', 'dark', time() + 30*24*3600, '/', '', true, true);\n" +
                        "\$theme = \$_COOKIE['theme'] ?? 'light';"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Common API") {
                    BodyText(
                        "Strings: strlen(), strtolower(), strtoupper(), trim(), str_replace(), " +
                        "strpos(), substr(), explode(), implode(), sprintf(), nl2br()\n\n" +
                        "Arrays: count(), array_push(), array_pop(), array_merge(), array_keys(), " +
                        "array_values(), in_array(), array_search(), sort(), usort(), array_map(), " +
                        "array_filter(), array_reduce()\n\n" +
                        "File I/O: file_get_contents(), file_put_contents(), fopen/fclose/fread/fwrite, " +
                        "file_exists(), is_file(), is_dir(), glob()\n\n" +
                        "JSON: json_encode(\$data), json_decode(\$json, true) — decode to array\n\n" +
                        "HTTP: header('Location: /page') — redirect; header('Content-Type: application/json')\n\n" +
                        "PDO (database):\n" +
                        "  \$pdo = new PDO('mysql:host=...;dbname=...', user, pass);\n" +
                        "  \$stmt = \$pdo->prepare('SELECT * FROM users WHERE id = ?');\n" +
                        "  \$stmt->execute([\$id]);\n" +
                        "  \$row = \$stmt->fetch(PDO::FETCH_ASSOC);\n\n" +
                        "Date/Time: date('Y-m-d H:i:s'), time(), strtotime()"
                    )
                    CodeBlock(
                        "<?php\n" +
                        "// JSON API endpoint\n" +
                        "header('Content-Type: application/json');\n\n" +
                        "\$data = ['status' => 'ok', 'users' => [['id'=>1,'name'=>'Alice']]];\n" +
                        "echo json_encode(\$data);\n\n" +
                        "// PDO parameterized query (safe from SQL injection)\n" +
                        "\$stmt = \$pdo->prepare('SELECT id, name FROM users WHERE email = ?');\n" +
                        "\$stmt->execute([\$email]);\n" +
                        "\$user = \$stmt->fetch(PDO::FETCH_ASSOC);\n" +
                        "// \$user is false if not found, array otherwise"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Example — Form Handler") {
                    BodyText(
                        "A typical PHP pattern: the same file renders the form (GET) and processes " +
                        "the submission (POST). On success, redirect to avoid double-submit on refresh."
                    )
                    CodeBlock(
                        "<?php\n" +
                        "session_start();\n" +
                        "\$error = '';\n\n" +
                        "if (\$_SERVER['REQUEST_METHOD'] === 'POST') {\n" +
                        "    \$name  = trim(\$_POST['name'] ?? '');\n" +
                        "    \$email = trim(\$_POST['email'] ?? '');\n\n" +
                        "    if (!\$name || !filter_var(\$email, FILTER_VALIDATE_EMAIL)) {\n" +
                        "        \$error = 'Invalid input';\n" +
                        "    } else {\n" +
                        "        \$stmt = \$pdo->prepare(\n" +
                        "            'INSERT INTO contacts (name, email) VALUES (?, ?)');\n" +
                        "        \$stmt->execute([\$name, \$email]);\n" +
                        "        header('Location: /thank-you');\n" +
                        "        exit;\n" +
                        "    }\n" +
                        "}\n" +
                        "?>\n\n" +
                        "<!DOCTYPE html><html><body>\n" +
                        "<?php if (\$error): ?>\n" +
                        "  <p style=\"color:red\"><?= htmlspecialchars(\$error) ?></p>\n" +
                        "<?php endif; ?>\n" +
                        "<form method=\"POST\">\n" +
                        "  <input type=\"text\"  name=\"name\"  required placeholder=\"Name\">\n" +
                        "  <input type=\"email\" name=\"email\" required placeholder=\"Email\">\n" +
                        "  <button type=\"submit\">Send</button>\n" +
                        "</form>\n" +
                        "</body></html>"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
