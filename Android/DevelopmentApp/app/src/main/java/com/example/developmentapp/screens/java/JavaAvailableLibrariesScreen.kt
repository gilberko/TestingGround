package com.example.developmentapp.screens.java

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
fun JavaAvailableLibrariesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Java — Available Libraries",
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
                SectionCard(title = "Sockets (TCP)") {
                    BodyText(
                        "java.net.Socket (client) and java.net.ServerSocket (server) are the standard " +
                        "TCP socket classes — no import beyond java.net needed. The server calls " +
                        "accept() which blocks until a client connects and returns a Socket. Both sides " +
                        "then use getInputStream() / getOutputStream() for bidirectional byte streams. " +
                        "Wrap with BufferedReader / PrintWriter for text, or DataInputStream for binary."
                    )
                    CodeBlock(
                        "// --- Server ---\n" +
                        "ServerSocket server = new ServerSocket(8080);\n" +
                        "Socket client = server.accept();          // blocks\n" +
                        "BufferedReader in  = new BufferedReader(\n" +
                        "    new InputStreamReader(client.getInputStream()));\n" +
                        "PrintWriter    out = new PrintWriter(client.getOutputStream(), true);\n" +
                        "String line = in.readLine();\n" +
                        "out.println(\"Echo: \" + line);\n\n" +
                        "// --- Client ---\n" +
                        "Socket sock = new Socket(\"localhost\", 8080);\n" +
                        "PrintWriter out2 = new PrintWriter(sock.getOutputStream(), true);\n" +
                        "out2.println(\"Hello\");"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "HTTP / HTTPS Networking") {
                    BodyText(
                        "Java 11+ ships java.net.http.HttpClient — a modern, fluent API supporting " +
                        "HTTP/1.1 and HTTP/2, both synchronous and async. HTTPS is handled automatically: " +
                        "just use an https:// URL and the JVM negotiates TLS.\n\n" +
                        "Legacy alternative: java.net.HttpURLConnection (Java 1.1+) — still works but " +
                        "verbose and awkward. Avoid for new code."
                    )
                    CodeBlock(
                        "// Java 11+ HttpClient\n" +
                        "HttpClient client = HttpClient.newHttpClient();\n\n" +
                        "HttpRequest request = HttpRequest.newBuilder()\n" +
                        "    .uri(URI.create(\"https://api.example.com/data\"))\n" +
                        "    .header(\"Accept\", \"application/json\")\n" +
                        "    .GET()\n" +
                        "    .build();\n\n" +
                        "// Synchronous\n" +
                        "HttpResponse<String> response =\n" +
                        "    client.send(request, HttpResponse.BodyHandlers.ofString());\n" +
                        "System.out.println(response.statusCode());  // 200\n" +
                        "System.out.println(response.body());\n\n" +
                        "// Async\n" +
                        "client.sendAsync(request, HttpResponse.BodyHandlers.ofString())\n" +
                        "      .thenApply(HttpResponse::body)\n" +
                        "      .thenAccept(System.out::println);"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "UDP Networking") {
                    BodyText(
                        "java.net.DatagramSocket sends and receives DatagramPackets — connectionless, " +
                        "no guarantee of delivery or order. Each packet carries its destination address " +
                        "and port. DatagramSocket can be bound to a fixed local port for receiving, or " +
                        "left unbound (OS picks a port) for send-only."
                    )
                    CodeBlock(
                        "// Send a UDP packet\n" +
                        "DatagramSocket socket = new DatagramSocket();\n" +
                        "byte[] data = \"Hello UDP\".getBytes();\n" +
                        "InetAddress addr = InetAddress.getByName(\"192.168.1.10\");\n" +
                        "DatagramPacket packet = new DatagramPacket(data, data.length, addr, 9090);\n" +
                        "socket.send(packet);\n\n" +
                        "// Receive\n" +
                        "DatagramSocket server = new DatagramSocket(9090);\n" +
                        "byte[] buf = new byte[1024];\n" +
                        "DatagramPacket incoming = new DatagramPacket(buf, buf.length);\n" +
                        "server.receive(incoming);   // blocks\n" +
                        "String msg = new String(incoming.getData(), 0, incoming.getLength());"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "SQL Databases (JDBC)") {
                    BodyText(
                        "java.sql.* is the standard Java Database Connectivity API. The same code works " +
                        "with any relational database — you just swap the driver JAR and connection URL.\n\n" +
                        "The JDK does NOT include drivers for MySQL, PostgreSQL, etc. You add the vendor's " +
                        "driver JAR to the classpath (e.g., mysql-connector-j-8.x.jar). SQLite has the " +
                        "sqlite-jdbc driver. Always use PreparedStatement, not plain Statement, to prevent " +
                        "SQL injection."
                    )
                    CodeBlock(
                        "// Requires: mysql-connector-j-8.x.jar on classpath\n" +
                        "String url  = \"jdbc:mysql://localhost:3306/mydb\";\n" +
                        "String user = \"root\", pass = \"secret\";\n\n" +
                        "try (Connection conn = DriverManager.getConnection(url, user, pass)) {\n\n" +
                        "    // Query\n" +
                        "    PreparedStatement ps = conn.prepareStatement(\n" +
                        "        \"SELECT name, age FROM users WHERE age > ?\");\n" +
                        "    ps.setInt(1, 25);\n" +
                        "    ResultSet rs = ps.executeQuery();\n" +
                        "    while (rs.next()) {\n" +
                        "        System.out.println(rs.getString(\"name\") +\n" +
                        "                           \" \" + rs.getInt(\"age\"));\n" +
                        "    }\n\n" +
                        "    // Insert\n" +
                        "    PreparedStatement ins = conn.prepareStatement(\n" +
                        "        \"INSERT INTO users (name, age) VALUES (?, ?)\");\n" +
                        "    ins.setString(1, \"Alice\");\n" +
                        "    ins.setInt(2, 30);\n" +
                        "    ins.executeUpdate();\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Graph Algorithms") {
                    BodyText(
                        "Java has no built-in graph library. The standard third-party choice is JGraphT " +
                        "(org.jgrapht:jgrapht-core on Maven/Gradle). It provides:\n" +
                        "• Graph types: directed, undirected, weighted, multigraph\n" +
                        "• Algorithms: BFS, DFS, Dijkstra shortest path, Bellman-Ford, Kruskal/Prim MST, " +
                        "topological sort, cycle detection, max flow (Edmonds-Karp)\n\n" +
                        "For simple tasks (BFS/DFS) you can easily build your own with HashMap<Node, List<Node>>."
                    )
                    CodeBlock(
                        "// JGraphT example (add jgrapht-core to build.gradle)\n" +
                        "import org.jgrapht.Graph;\n" +
                        "import org.jgrapht.graph.*;\n" +
                        "import org.jgrapht.alg.shortestpath.DijkstraShortestPath;\n\n" +
                        "Graph<String, DefaultWeightedEdge> g =\n" +
                        "    new SimpleWeightedGraph<>(DefaultWeightedEdge.class);\n" +
                        "g.addVertex(\"A\"); g.addVertex(\"B\"); g.addVertex(\"C\");\n" +
                        "g.setEdgeWeight(g.addEdge(\"A\", \"B\"), 4.0);\n" +
                        "g.setEdgeWeight(g.addEdge(\"B\", \"C\"), 2.0);\n" +
                        "g.setEdgeWeight(g.addEdge(\"A\", \"C\"), 7.0);\n\n" +
                        "var path = new DijkstraShortestPath<>(g).getPath(\"A\", \"C\");\n" +
                        "System.out.println(path.getVertexList()); // [A, B, C]\n" +
                        "System.out.println(path.getWeight());     // 6.0"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Sorting") {
                    BodyText(
                        "No extra library needed. java.util.Arrays and java.util.Collections cover all " +
                        "standard sorting:\n" +
                        "• Arrays.sort(arr) — primitive arrays use dual-pivot quicksort (O(n log n))\n" +
                        "• Arrays.sort(objArr) / Collections.sort(list) — objects use Timsort (stable)\n" +
                        "• Both accept a Comparator for custom ordering\n" +
                        "• Java 8+: list.sort(Comparator) and Comparator.comparing() chains"
                    )
                    CodeBlock(
                        "int[] nums = {5, 2, 8, 1};\n" +
                        "Arrays.sort(nums);           // [1, 2, 5, 8]\n\n" +
                        "List<String> names = Arrays.asList(\"Charlie\", \"Alice\", \"Bob\");\n" +
                        "Collections.sort(names);     // [Alice, Bob, Charlie]\n\n" +
                        "// Custom comparator — sort by string length, then alphabetically\n" +
                        "names.sort(Comparator.comparingInt(String::length)\n" +
                        "                    .thenComparing(Comparator.naturalOrder()));\n\n" +
                        "// Sort objects\n" +
                        "List<Person> people = ...;\n" +
                        "people.sort(Comparator.comparing(Person::getAge).reversed());"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Audio") {
                    BodyText(
                        "javax.sound.sampled is the built-in audio API. It supports WAV, AU, and AIFF " +
                        "out of the box. Key classes:\n" +
                        "• AudioSystem — factory for getting lines and reading audio files\n" +
                        "• Clip — preload entire audio file into memory, then play/loop\n" +
                        "• SourceDataLine — stream audio in chunks (for large files or generated audio)\n\n" +
                        "MP3 is NOT supported natively — you need a third-party library such as JLayer " +
                        "(javazoom) or TarsosDSP. AAC/OGG also require external decoders."
                    )
                    CodeBlock(
                        "// Play a WAV file with Clip\n" +
                        "import javax.sound.sampled.*;\n\n" +
                        "File audioFile = new File(\"sound.wav\");\n" +
                        "AudioInputStream ais = AudioSystem.getAudioInputStream(audioFile);\n" +
                        "Clip clip = AudioSystem.getClip();\n" +
                        "clip.open(ais);\n" +
                        "clip.start();          // non-blocking — playback on a daemon thread\n\n" +
                        "// Loop forever\n" +
                        "clip.loop(Clip.LOOP_CONTINUOUSLY);\n\n" +
                        "// Stop\n" +
                        "clip.stop();\n" +
                        "clip.close();"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Compression / Decompression") {
                    BodyText(
                        "java.util.zip is built in — no extra dependency needed:\n" +
                        "• GZIPOutputStream / GZIPInputStream — gzip format (.gz)\n" +
                        "• DeflaterOutputStream / InflaterInputStream — raw DEFLATE\n" +
                        "• ZipOutputStream / ZipInputStream — zip archives (.zip)\n" +
                        "• ZipFile — random-access reading of zip archives\n\n" +
                        "Wrap around any OutputStream/InputStream — it just sits in the stream chain."
                    )
                    CodeBlock(
                        "// Compress to gzip\n" +
                        "try (GZIPOutputStream gz = new GZIPOutputStream(\n" +
                        "        new FileOutputStream(\"output.gz\"))) {\n" +
                        "    gz.write(\"Hello, compressed world!\".getBytes());\n" +
                        "}\n\n" +
                        "// Decompress from gzip\n" +
                        "try (GZIPInputStream gz = new GZIPInputStream(\n" +
                        "        new FileInputStream(\"output.gz\"))) {\n" +
                        "    System.out.println(new String(gz.readAllBytes()));\n" +
                        "}\n\n" +
                        "// Create a zip archive\n" +
                        "try (ZipOutputStream zos = new ZipOutputStream(\n" +
                        "        new FileOutputStream(\"archive.zip\"))) {\n" +
                        "    zos.putNextEntry(new ZipEntry(\"hello.txt\"));\n" +
                        "    zos.write(\"Hello!\".getBytes());\n" +
                        "    zos.closeEntry();\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "DNS") {
                    BodyText(
                        "java.net.InetAddress handles DNS resolution — no extra library needed:\n" +
                        "• getByName(host) — forward lookup: hostname → IP\n" +
                        "• getAllByName(host) — all IP addresses for a hostname (A + AAAA records)\n" +
                        "• getLocalHost() — local machine's address\n" +
                        "• Reverse lookup: create InetAddress from raw IP bytes, then call getHostName()\n\n" +
                        "All calls are blocking. For async DNS or advanced record types (MX, TXT, SRV), " +
                        "use JNDI with the DNS provider or a third-party library like dnsjava."
                    )
                    CodeBlock(
                        "// Forward lookup\n" +
                        "InetAddress addr = InetAddress.getByName(\"www.google.com\");\n" +
                        "System.out.println(addr.getHostAddress()); // e.g. \"142.250.74.68\"\n\n" +
                        "// All addresses\n" +
                        "InetAddress[] all = InetAddress.getAllByName(\"www.google.com\");\n" +
                        "for (InetAddress a : all) System.out.println(a.getHostAddress());\n\n" +
                        "// Reverse lookup (PTR record)\n" +
                        "InetAddress reverse = InetAddress.getByName(\"8.8.8.8\");\n" +
                        "System.out.println(reverse.getHostName()); // \"dns.google\"\n\n" +
                        "// Check reachability\n" +
                        "boolean reachable = addr.isReachable(3000);  // 3 second timeout"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "OS Interaction") {
                    BodyText(
                        "Java can launch, wait on, and kill external processes:\n" +
                        "• ProcessBuilder (preferred) — configure command, environment, working directory, " +
                        "then call start() to get a Process\n" +
                        "• Runtime.exec(cmd) — older API, less control, harder to use correctly\n\n" +
                        "Read stdout/stderr via process.getInputStream() / process.getErrorStream(). " +
                        "Always read both streams (possibly in separate threads) to avoid blocking if " +
                        "the child process fills a pipe buffer."
                    )
                    CodeBlock(
                        "// Run 'ls -l' and capture output\n" +
                        "ProcessBuilder pb = new ProcessBuilder(\"ls\", \"-l\");\n" +
                        "pb.directory(new File(\"/tmp\"));\n" +
                        "pb.redirectErrorStream(true);  // merge stderr into stdout\n\n" +
                        "Process proc = pb.start();\n\n" +
                        "// Read stdout\n" +
                        "BufferedReader reader = new BufferedReader(\n" +
                        "    new InputStreamReader(proc.getInputStream()));\n" +
                        "reader.lines().forEach(System.out::println);\n\n" +
                        "int exitCode = proc.waitFor();  // blocks until done\n" +
                        "System.out.println(\"Exit: \" + exitCode);\n\n" +
                        "// Kill a process\n" +
                        "proc.destroy();          // SIGTERM\n" +
                        "proc.destroyForcibly(); // SIGKILL (if destroy() not enough)"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
