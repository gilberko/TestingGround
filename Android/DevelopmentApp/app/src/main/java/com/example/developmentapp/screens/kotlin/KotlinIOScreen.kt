package com.example.developmentapp.screens.kotlin

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
fun KotlinIOScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Kotlin — I/O",
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
                SectionCard(title = "Reading Files") {
                    BodyText(
                        "Kotlin's kotlin.io package adds extension functions directly on java.io.File, " +
                        "making file reading much more concise than Java.\n\n" +
                        "readText() — reads the entire file into a String. Simple but loads the whole " +
                        "file into memory.\n\n" +
                        "readLines() — reads all lines into a List<String>. Also loads everything.\n\n" +
                        "forEachLine { } — reads and processes one line at a time without loading " +
                        "the whole file. Best for large files.\n\n" +
                        "bufferedReader().use { } — full control with a BufferedReader; use { } " +
                        "ensures close() is called even if an exception is thrown."
                    )
                    CodeBlock(
                        "import java.io.File\n\n" +
                        "// Read entire file as a String:\n" +
                        "val text = File(\"notes.txt\").readText()\n" +
                        "println(text)\n\n" +
                        "// Read all lines into a list:\n" +
                        "val lines: List<String> = File(\"data.txt\").readLines()\n" +
                        "lines.forEach { println(it) }\n\n" +
                        "// Process line by line (memory-efficient for large files):\n" +
                        "File(\"large.log\").forEachLine { line ->\n" +
                        "    if (line.contains(\"ERROR\")) println(line)\n" +
                        "}\n\n" +
                        "// Full control with BufferedReader:\n" +
                        "File(\"config.txt\").bufferedReader().use { reader ->\n" +
                        "    var line = reader.readLine()\n" +
                        "    while (line != null) {\n" +
                        "        println(line)\n" +
                        "        line = reader.readLine()\n" +
                        "    }\n" +
                        "}  // reader.close() called automatically\n\n" +
                        "// Specify charset:\n" +
                        "File(\"utf16.txt\").readText(Charsets.UTF_16)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Writing Files") {
                    BodyText(
                        "Kotlin's file writing extensions are equally concise:\n\n" +
                        "writeText() — writes a String to the file, replacing any existing content.\n\n" +
                        "appendText() — appends a String to the end of the file.\n\n" +
                        "bufferedWriter().use { } — for building output line by line or when you " +
                        "need to write many small strings efficiently.\n\n" +
                        "printWriter().use { } — provides println() / print() methods on the writer, " +
                        "which is convenient for formatted text output."
                    )
                    CodeBlock(
                        "import java.io.File\n\n" +
                        "// Write (overwrites existing content):\n" +
                        "File(\"output.txt\").writeText(\"Hello, Kotlin!\")\n\n" +
                        "// Append:\n" +
                        "File(\"log.txt\").appendText(\"New log entry\\n\")\n\n" +
                        "// Buffered writer:\n" +
                        "File(\"report.txt\").bufferedWriter().use { writer ->\n" +
                        "    writer.write(\"Line 1\")\n" +
                        "    writer.newLine()\n" +
                        "    writer.write(\"Line 2\")\n" +
                        "}  // flushed and closed automatically\n\n" +
                        "// PrintWriter — convenient println/print:\n" +
                        "File(\"out.txt\").printWriter().use { out ->\n" +
                        "    out.println(\"Header\")\n" +
                        "    for (i in 1..5) out.println(\"Row \$i\")\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Standard I/O") {
                    BodyText(
                        "println() and print() write to standard output. readLine() reads one line " +
                        "from standard input and returns String? — null when stdin is closed (EOF)."
                    )
                    CodeBlock(
                        "// Output:\n" +
                        "println(\"Hello, World!\")   // with newline\n" +
                        "print(\"Enter name: \")      // no newline\n\n" +
                        "// Read a line (returns String? — null on EOF):\n" +
                        "val input: String? = readLine()\n" +
                        "val name = readLine() ?: \"Guest\"  // default if null\n\n" +
                        "// Read and parse a number:\n" +
                        "val n = readLine()?.toIntOrNull() ?: 0\n\n" +
                        "// Read multiple values from one line:\n" +
                        "val (a, b) = readLine()!!.split(\" \").map { it.toInt() }\n\n" +
                        "// Read all lines until EOF:\n" +
                        "val lines = generateSequence(::readLine).toList()"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "TCP Sockets") {
                    BodyText(
                        "Kotlin uses the same java.net.Socket and java.net.ServerSocket as Java. " +
                        "The Kotlin use { } extension ensures sockets are closed even on exceptions, " +
                        "replacing the Java try-with-resources pattern."
                    )
                    CodeBlock(
                        "import java.net.Socket\n" +
                        "import java.net.ServerSocket\n\n" +
                        "// TCP client:\n" +
                        "Socket(\"example.com\", 80).use { socket ->\n" +
                        "    val writer = socket.getOutputStream().bufferedWriter()\n" +
                        "    val reader = socket.getInputStream().bufferedReader()\n\n" +
                        "    writer.write(\"GET / HTTP/1.0\\r\\nHost: example.com\\r\\n\\r\\n\")\n" +
                        "    writer.flush()\n\n" +
                        "    val response = reader.readText()\n" +
                        "    println(response)\n" +
                        "}  // socket.close() called automatically\n\n" +
                        "// TCP server:\n" +
                        "ServerSocket(9000).use { server ->\n" +
                        "    println(\"Listening on port 9000\")\n" +
                        "    while (true) {\n" +
                        "        server.accept().use { client ->\n" +
                        "            val msg = client.getInputStream().bufferedReader().readLine()\n" +
                        "            println(\"Received: \$msg\")\n" +
                        "            client.getOutputStream().write(\"Echo: \$msg\\n\".toByteArray())\n" +
                        "        }\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "HTTP — HttpURLConnection") {
                    BodyText(
                        "For simple HTTP requests without a third-party library, Kotlin can use " +
                        "java.net.HttpURLConnection. It is verbose but has no dependencies.\n\n" +
                        "Note: on Android, network calls must run on a background thread or in a " +
                        "coroutine with Dispatchers.IO — calling from the main thread throws " +
                        "NetworkOnMainThreadException."
                    )
                    CodeBlock(
                        "import java.net.HttpURLConnection\n" +
                        "import java.net.URL\n\n" +
                        "fun get(urlString: String): String {\n" +
                        "    val url = URL(urlString)\n" +
                        "    val conn = url.openConnection() as HttpURLConnection\n" +
                        "    return try {\n" +
                        "        conn.requestMethod = \"GET\"\n" +
                        "        conn.connectTimeout = 5_000\n" +
                        "        conn.readTimeout    = 5_000\n" +
                        "        conn.connect()\n\n" +
                        "        if (conn.responseCode == 200) {\n" +
                        "            conn.inputStream.bufferedReader().readText()\n" +
                        "        } else {\n" +
                        "            error(\"HTTP \${conn.responseCode}\")\n" +
                        "        }\n" +
                        "    } finally {\n" +
                        "        conn.disconnect()\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "HTTP — OkHttp and Ktor (Recommended)") {
                    BodyText(
                        "For real Android or server projects, use a proper HTTP library:\n\n" +
                        "OkHttp (Square) — the most widely used HTTP client in Android. Synchronous " +
                        "and asynchronous calls, interceptors, connection pooling, automatic retries. " +
                        "Dependency: com.squareup.okhttp3:okhttp\n\n" +
                        "Ktor Client (JetBrains) — a coroutine-native HTTP client. Use suspend " +
                        "functions directly in your coroutine code. Supports multiple engines " +
                        "(OkHttp on Android, CIO on server). Dependency: io.ktor:ktor-client-android"
                    )
                    CodeBlock(
                        "// OkHttp — synchronous (run on IO thread or in coroutine):\n" +
                        "import okhttp3.OkHttpClient\n" +
                        "import okhttp3.Request\n\n" +
                        "val client = OkHttpClient()\n\n" +
                        "val request = Request.Builder()\n" +
                        "    .url(\"https://api.example.com/data\")\n" +
                        "    .build()\n\n" +
                        "client.newCall(request).execute().use { response ->\n" +
                        "    if (!response.isSuccessful) error(\"HTTP \${response.code}\")\n" +
                        "    println(response.body?.string())\n" +
                        "}\n\n" +
                        "// Ktor Client — coroutine-native (suspend function):\n" +
                        "import io.ktor.client.*\n" +
                        "import io.ktor.client.request.*\n\n" +
                        "val httpClient = HttpClient()\n\n" +
                        "suspend fun fetchData(): String {\n" +
                        "    return httpClient.get(\"https://api.example.com/data\")\n" +
                        "        .body()\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
