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
fun JavaIOScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Java — I/O",
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
                SectionCard(title = "Overview: java.io vs java.nio") {
                    BodyText(
                        "Java has two I/O systems. Both work for files AND network (sockets)."
                    )
                    BodyText(
                        "java.io (Java 1.0) — stream-based. InputStream/OutputStream handle raw bytes; " +
                        "Reader/Writer handle characters. The API is simple but every operation blocks " +
                        "the calling thread. There is no non-blocking or async mode."
                    )
                    BodyText(
                        "java.nio (Java 1.4) — channel + buffer based. Channels replace streams; " +
                        "ByteBuffers replace byte arrays. Network channels support non-blocking mode. " +
                        "A Selector multiplexes many channels on a single thread (like C select/epoll). " +
                        "NIO.2 (Java 7) added the Files utility class and truly asynchronous channels " +
                        "(AsynchronousFileChannel, AsynchronousSocketChannel) that notify via " +
                        "CompletionHandler callbacks or Future."
                    )
                }
            }

            item {
                SectionCard(title = "Old I/O: Reading a File (java.io)") {
                    BodyText(
                        "FileInputStream gives raw bytes. Wrap it in InputStreamReader to decode a " +
                        "charset, then in BufferedReader for efficient line-by-line reading:"
                    )
                    CodeBlock(
                        "// Explicit charset — recommended for portability\n" +
                        "try (BufferedReader br = new BufferedReader(\n" +
                        "        new InputStreamReader(\n" +
                        "            new FileInputStream(\"data.txt\"),\n" +
                        "            StandardCharsets.UTF_8))) {\n" +
                        "    String line;\n" +
                        "    while ((line = br.readLine()) != null) {\n" +
                        "        System.out.println(line);\n" +
                        "    }\n" +
                        "}"
                    )
                    BodyText(
                        "FileReader is a shortcut but uses the platform default charset — avoid it if " +
                        "portability matters. All read calls are blocking: readLine() blocks until a " +
                        "full line is available or EOF is reached."
                    )
                    CodeBlock(
                        "// Shorter (Java 8+) — streams all lines lazily\n" +
                        "try (BufferedReader br = new BufferedReader(new FileReader(\"data.txt\"))) {\n" +
                        "    br.lines().forEach(System.out::println);\n" +
                        "}"
                    )
                }
            }

            item {
                SectionCard(title = "Old I/O: Writing a File (java.io)") {
                    BodyText(
                        "FileOutputStream gives a raw byte sink. Wrap it in OutputStreamWriter for " +
                        "charset encoding, then in BufferedWriter for efficiency:"
                    )
                    CodeBlock(
                        "// Write (overwrite or create)\n" +
                        "try (BufferedWriter bw = new BufferedWriter(\n" +
                        "        new OutputStreamWriter(\n" +
                        "            new FileOutputStream(\"out.txt\"),\n" +
                        "            StandardCharsets.UTF_8))) {\n" +
                        "    bw.write(\"Hello, World!\");\n" +
                        "    bw.newLine();\n" +
                        "    bw.write(\"Second line\");\n" +
                        "}\n" +
                        "\n" +
                        "// Append mode: pass true as second arg to FileOutputStream/FileWriter\n" +
                        "try (BufferedWriter bw = new BufferedWriter(\n" +
                        "        new FileWriter(\"out.txt\", true))) {\n" +
                        "    bw.write(\"Appended line\");\n" +
                        "    bw.newLine();\n" +
                        "}"
                    )
                    BodyText(
                        "write() calls are blocking. BufferedWriter batches writes internally and flushes " +
                        "on close (try-with-resources guarantees the close). PrintWriter adds printf/println " +
                        "conveniences but swallows IOExceptions — avoid it when error handling matters."
                    )
                }
            }

            item {
                SectionCard(title = "New I/O: Files Utility Class (NIO.2, Java 7+)") {
                    BodyText(
                        "java.nio.file.Files provides high-level static helpers that eliminate most " +
                        "boilerplate. This is the simplest choice for ordinary file I/O:"
                    )
                    CodeBlock(
                        "Path path = Path.of(\"data.txt\");\n" +
                        "\n" +
                        "// Read everything at once\n" +
                        "byte[]       bytes = Files.readAllBytes(path);\n" +
                        "List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);\n" +
                        "\n" +
                        "// Lazy line streaming — good for large files (close the stream!)\n" +
                        "try (Stream<String> s = Files.lines(path, StandardCharsets.UTF_8)) {\n" +
                        "    s.forEach(System.out::println);\n" +
                        "}\n" +
                        "\n" +
                        "// Write bytes (overwrites by default)\n" +
                        "Files.write(Path.of(\"out.txt\"),\n" +
                        "            \"Hello\\n\".getBytes(StandardCharsets.UTF_8));\n" +
                        "\n" +
                        "// Write lines\n" +
                        "Files.write(Path.of(\"out.txt\"),\n" +
                        "            List.of(\"line1\", \"line2\"),\n" +
                        "            StandardCharsets.UTF_8);\n" +
                        "\n" +
                        "// Append\n" +
                        "Files.write(Path.of(\"out.txt\"), List.of(\"more\"),\n" +
                        "            StandardCharsets.UTF_8, StandardOpenOption.APPEND);"
                    )
                    BodyText(
                        "All Files methods are blocking. readAllBytes/readAllLines load the entire file " +
                        "into memory — prefer Files.lines() (lazy Stream) for files too large to fit in RAM."
                    )
                }
            }

            item {
                SectionCard(title = "New I/O: Channels and Buffers") {
                    BodyText(
                        "FileChannel is NIO's lower-level file handle. It reads/writes ByteBuffers instead " +
                        "of byte arrays. Buffer lifecycle: fill with put()/read() → flip() to switch to " +
                        "read mode (limit=position, position=0) → drain with get()/write() → clear() to reset:"
                    )
                    CodeBlock(
                        "// Reading\n" +
                        "try (FileChannel fc = FileChannel.open(\n" +
                        "        Path.of(\"data.txt\"), StandardOpenOption.READ)) {\n" +
                        "    ByteBuffer buf = ByteBuffer.allocate(4096);\n" +
                        "    while (fc.read(buf) != -1) {\n" +
                        "        buf.flip();\n" +
                        "        while (buf.hasRemaining())\n" +
                        "            System.out.print((char) buf.get());\n" +
                        "        buf.clear();\n" +
                        "    }\n" +
                        "}\n" +
                        "\n" +
                        "// Writing\n" +
                        "try (FileChannel fc = FileChannel.open(\n" +
                        "        Path.of(\"out.txt\"),\n" +
                        "        StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {\n" +
                        "    ByteBuffer buf =\n" +
                        "        ByteBuffer.wrap(\"Hello, NIO!\".getBytes(StandardCharsets.UTF_8));\n" +
                        "    while (buf.hasRemaining()) fc.write(buf);\n" +
                        "}"
                    )
                    BodyText(
                        "FileChannel is always blocking regardless of configureBlocking(false) — the OS " +
                        "does not support non-blocking for regular files. Non-blocking mode only makes " +
                        "sense for network channels (SocketChannel, ServerSocketChannel)."
                    )
                }
            }

            item {
                SectionCard(title = "Non-Blocking I/O and Selector (like select/epoll)") {
                    BodyText(
                        "SocketChannel and ServerSocketChannel support configureBlocking(false). A Selector " +
                        "multiplexes many non-blocking channels on one thread — equivalent to C's select(), " +
                        "poll(), or epoll_wait(). You register channels with interest operations: OP_READ, " +
                        "OP_WRITE, OP_CONNECT, OP_ACCEPT."
                    )
                    CodeBlock(
                        "Selector selector = Selector.open();\n" +
                        "\n" +
                        "ServerSocketChannel server = ServerSocketChannel.open();\n" +
                        "server.bind(new InetSocketAddress(8080));\n" +
                        "server.configureBlocking(false);\n" +
                        "server.register(selector, SelectionKey.OP_ACCEPT);\n" +
                        "\n" +
                        "while (true) {\n" +
                        "    selector.select();          // blocks until at least one channel is ready\n" +
                        "    // selector.select(timeout) — like select() with timeout\n" +
                        "    // selector.selectNow()     — non-blocking poll, returns 0 if nothing ready\n" +
                        "\n" +
                        "    Iterator<SelectionKey> it = selector.selectedKeys().iterator();\n" +
                        "    while (it.hasNext()) {\n" +
                        "        SelectionKey key = it.next();\n" +
                        "        it.remove();   // must remove manually\n" +
                        "\n" +
                        "        if (key.isAcceptable()) {\n" +
                        "            SocketChannel client = server.accept();\n" +
                        "            client.configureBlocking(false);\n" +
                        "            client.register(selector, SelectionKey.OP_READ);\n" +
                        "        } else if (key.isReadable()) {\n" +
                        "            SocketChannel ch = (SocketChannel) key.channel();\n" +
                        "            ByteBuffer buf = ByteBuffer.allocate(256);\n" +
                        "            int n = ch.read(buf);\n" +
                        "            if (n == -1) key.cancel();  // peer closed\n" +
                        "            else { /* process buf */ }\n" +
                        "        }\n" +
                        "    }\n" +
                        "}"
                    )
                    BodyText(
                        "This pattern — one thread serving many connections — is how Java-based servers " +
                        "like Netty and Vert.x work internally. The read() on a non-blocking channel " +
                        "returns 0 immediately if no data is available (instead of blocking), so the " +
                        "Selector is essential to avoid busy-polling."
                    )
                }
            }

            item {
                SectionCard(title = "Async I/O: NIO.2 (Java 7+)") {
                    BodyText(
                        "AsynchronousFileChannel, AsynchronousSocketChannel, and " +
                        "AsynchronousServerSocketChannel initiate I/O and return immediately. The OS " +
                        "performs the operation in the background and notifies you via a CompletionHandler " +
                        "callback or a Future:"
                    )
                    CodeBlock(
                        "// Async file read — CompletionHandler callback\n" +
                        "AsynchronousFileChannel afc = AsynchronousFileChannel.open(\n" +
                        "        Path.of(\"data.txt\"), StandardOpenOption.READ);\n" +
                        "ByteBuffer buf = ByteBuffer.allocate(1024);\n" +
                        "\n" +
                        "afc.read(buf, /*filePosition=*/0, buf,\n" +
                        "    new CompletionHandler<Integer, ByteBuffer>() {\n" +
                        "        @Override\n" +
                        "        public void completed(Integer bytesRead, ByteBuffer attachment) {\n" +
                        "            attachment.flip();\n" +
                        "            System.out.println(\"Read \" + bytesRead + \" bytes\");\n" +
                        "        }\n" +
                        "        @Override\n" +
                        "        public void failed(Throwable exc, ByteBuffer attachment) {\n" +
                        "            exc.printStackTrace();\n" +
                        "        }\n" +
                        "    });\n" +
                        "\n" +
                        "// Async file read — Future (calling get() blocks until done)\n" +
                        "Future<Integer> f = afc.read(buf, 0);\n" +
                        "int n = f.get();                         // blocks\n" +
                        "int n = f.get(2, TimeUnit.SECONDS);      // with timeout"
                    )
                    BodyText(
                        "CompletionHandler callbacks run on a JVM-managed thread pool — your original " +
                        "thread is free to do other work. Future.get() re-introduces blocking, so it is " +
                        "mainly useful for simple one-shot reads where full async plumbing is overkill. " +
                        "AsynchronousSocketChannel follows the same pattern for network I/O."
                    )
                }
            }

            item {
                SectionCard(title = "Summary Table") {
                    BodyText("All five I/O approaches compared:")
                    CodeBlock(
                        "API                    | Model          | File | Net  | Blocking?\n" +
                        "───────────────────────┼────────────────┼──────┼──────┼──────────────\n" +
                        "java.io streams        | Stream         | Yes  | Yes* | Always\n" +
                        "java.nio FileChannel   | Channel/Buffer | Yes  |  —   | Always\n" +
                        "java.nio SocketChannel | Channel/Buffer |  —   | Yes  | Configurable\n" +
                        "java.nio Selector      | Multiplexer    |  —   | Yes  | Blocks-til-rdy\n" +
                        "NIO.2 Async channels   | Callback/Future| Yes  | Yes  | Never\n" +
                        "\n" +
                        "* java.io works with network via Socket/ServerSocket (blocking only)"
                    )
                    BodyText(
                        "Quick guide: use Files (NIO.2) for simple file work; use Selector + " +
                        "SocketChannel for high-concurrency servers; use Async channels when you need " +
                        "true non-blocking file I/O or async network with callbacks."
                    )
                }
            }

            item {
                SectionCard(title = "C++ Comparison") {
                    BodyText(
                        "java.io maps closely to what C and C++ programmers already know:"
                    )
                    CodeBlock(
                        "java.io streams       ≈  C FILE* / C++ std::ifstream / std::ofstream\n" +
                        "                         Synchronous, stream-based, always blocking\n" +
                        "\n" +
                        "java.nio non-blocking  ≈  O_NONBLOCK flag on a Linux fd\n" +
                        "  + Selector              + select() / poll() / epoll_wait()\n" +
                        "                         One thread multiplexes many sockets\n" +
                        "\n" +
                        "NIO.2 Async channels  ≈  io_uring on Linux\n" +
                        "                      ≈  IOCP (I/O Completion Ports) on Windows\n" +
                        "                         OS signals completion; caller never blocked"
                    )
                    BodyText(
                        "Key Java advantage: the same AsynchronousSocketChannel API works on all " +
                        "platforms. The JVM selects the best OS primitive internally (epoll on Linux, " +
                        "kqueue on macOS, IOCP on Windows). In C/C++ you must write platform-specific " +
                        "code or use a library like libuv or Boost.Asio."
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
