package com.example.developmentapp.screens.go

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
fun GoStandardLibraryScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Go — Standard Library",
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
            item { Spacer(Modifier.height(16.dp)) }

            // ── fmt ──────────────────────────────────────────────────────────
            item {
                SectionCard(title = "fmt") {
                    BodyText("The fmt package handles formatted I/O. Key functions:")
                    BodyText("fmt.Println(args...) — prints space-separated values with a newline.")
                    BodyText("fmt.Printf(format, args...) — formatted print to stdout. Common verbs: %d (int), %s (string), %v (default), %+v (struct with field names), %#v (Go syntax), %T (type), %p (pointer), %f (float), %e (scientific), %b (binary), %x (hex).")
                    BodyText("fmt.Sprintf(format, args...) — returns a formatted string instead of printing.")
                    BodyText("fmt.Fprintf(w, format, args...) — writes to any io.Writer.")
                    BodyText("fmt.Errorf(format, args...) — creates a formatted error; use %w to wrap another error.")
                    BodyText("fmt.Scan / fmt.Scanf / fmt.Scanln — read from stdin.")
                    BodyText("Stringer interface: if a type implements String() string, fmt uses it automatically when printing with %v or %s.")
                    CodeBlock(
                        "fmt.Println(\"Hello\", 42)             // Hello 42\n" +
                        "fmt.Printf(\"%s is %d years old\\n\", \"Alice\", 30)\n" +
                        "s := fmt.Sprintf(\"pi = %.2f\", 3.14159) // \"pi = 3.14\"\n" +
                        "fmt.Fprintf(os.Stderr, \"error: %v\\n\", err)\n" +
                        "err := fmt.Errorf(\"open failed: %w\", originalErr)\n" +
                        "\n" +
                        "// Stringer\n" +
                        "type Point struct{ X, Y int }\n" +
                        "func (p Point) String() string { return fmt.Sprintf(\"(%d,%d)\", p.X, p.Y) }\n" +
                        "fmt.Println(Point{3, 4})  // (3,4)"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── strings ──────────────────────────────────────────────────────
            item {
                SectionCard(title = "strings") {
                    BodyText("The strings package provides functions for working with UTF-8 strings.")
                    CodeBlock(
                        "import \"strings\"\n" +
                        "\n" +
                        "strings.Contains(\"seafood\", \"foo\")    // true\n" +
                        "strings.HasPrefix(\"hello\", \"he\")       // true\n" +
                        "strings.HasSuffix(\"hello\", \"lo\")       // true\n" +
                        "strings.Index(\"hello\", \"ll\")           // 2  (-1 if not found)\n" +
                        "strings.Count(\"cheese\", \"e\")           // 3\n" +
                        "strings.Split(\"a,b,c\", \",\")            // [\"a\" \"b\" \"c\"]\n" +
                        "strings.Join([]string{\"a\",\"b\"}, \"-\")  // \"a-b\"\n" +
                        "strings.TrimSpace(\"  hi  \")             // \"hi\"\n" +
                        "strings.Trim(\"--hi--\", \"-\")            // \"hi\"\n" +
                        "strings.ToLower(\"HELLO\")                // \"hello\"\n" +
                        "strings.ToUpper(\"hello\")                // \"HELLO\"\n" +
                        "strings.Replace(\"oink oink\", \"oink\", \"moo\", 1) // \"moo oink\"\n" +
                        "strings.ReplaceAll(\"oink oink\", \"oink\", \"moo\")  // \"moo moo\"\n" +
                        "strings.Fields(\"  foo bar  \")           // [\"foo\" \"bar\"]\n" +
                        "\n" +
                        "// strings.Builder — efficient concatenation (avoids allocating many strings)\n" +
                        "var b strings.Builder\n" +
                        "for i := 0; i < 5; i++ {\n" +
                        "    fmt.Fprintf(&b, \"%d\", i)\n" +
                        "}\n" +
                        "result := b.String() // \"01234\""
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── errors ───────────────────────────────────────────────────────
            item {
                SectionCard(title = "errors") {
                    BodyText("The errors package provides basic error utilities. See also the Error Handling screen for a full explanation.")
                    CodeBlock(
                        "import \"errors\"\n" +
                        "\n" +
                        "// Create a simple error\n" +
                        "err := errors.New(\"something went wrong\")\n" +
                        "\n" +
                        "// Check if any error in the chain equals a sentinel\n" +
                        "errors.Is(err, ErrNotFound)   // walks unwrap chain\n" +
                        "\n" +
                        "// Extract a concrete error type from the chain\n" +
                        "var ve *ValidationError\n" +
                        "errors.As(err, &ve)           // assigns first match to ve\n" +
                        "\n" +
                        "// Unwrap — get the next error in the chain\n" +
                        "errors.Unwrap(wrappedErr)     // nil if no wrapped error\n" +
                        "\n" +
                        "// Wrapping errors with fmt.Errorf and %w (Go 1.13+)\n" +
                        "wrapped := fmt.Errorf(\"open config: %w\", err)\n" +
                        "errors.Is(wrapped, err) // true — Is walks the chain"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── strconv ──────────────────────────────────────────────────────
            item {
                SectionCard(title = "strconv") {
                    BodyText("The strconv package converts between strings and basic types.")
                    CodeBlock(
                        "import \"strconv\"\n" +
                        "\n" +
                        "// String → int\n" +
                        "n, err := strconv.Atoi(\"42\")   // n=42, err=nil\n" +
                        "n, err  = strconv.Atoi(\"abc\")  // n=0, err=*NumError\n" +
                        "\n" +
                        "// int → string\n" +
                        "s := strconv.Itoa(42)           // \"42\"\n" +
                        "\n" +
                        "// ParseInt(s, base, bitSize) — more control\n" +
                        "v, err := strconv.ParseInt(\"FF\", 16, 64)   // 255\n" +
                        "v, err  = strconv.ParseInt(\"-42\", 10, 32)  // -42\n" +
                        "\n" +
                        "// ParseFloat\n" +
                        "f, err := strconv.ParseFloat(\"3.14\", 64)   // 3.14\n" +
                        "\n" +
                        "// ParseBool\n" +
                        "b, err := strconv.ParseBool(\"true\")  // true\n" +
                        "// accepts: 1, t, T, TRUE, true, True, 0, f, F, FALSE, false, False\n" +
                        "\n" +
                        "// Format (int/float → string)\n" +
                        "strconv.FormatInt(255, 16)             // \"ff\"\n" +
                        "strconv.FormatFloat(3.14, 'f', 2, 64) // \"3.14\""
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── bytes ────────────────────────────────────────────────────────
            item {
                SectionCard(title = "bytes") {
                    BodyText("The bytes package mirrors the strings package but operates on []byte. Use it when working with binary data or building byte slices efficiently.")
                    CodeBlock(
                        "import \"bytes\"\n" +
                        "\n" +
                        "bytes.Contains([]byte(\"seafood\"), []byte(\"foo\")) // true\n" +
                        "bytes.Split([]byte(\"a,b,c\"), []byte(\",\"))        // [[]byte]\n" +
                        "bytes.Join(parts, []byte(\"-\"))\n" +
                        "bytes.TrimSpace([]byte(\"  hi  \"))                 // []byte(\"hi\")\n" +
                        "bytes.ToLower([]byte(\"HELLO\"))                    // []byte(\"hello\")\n" +
                        "\n" +
                        "// bytes.Buffer — efficient byte-slice builder\n" +
                        "var buf bytes.Buffer\n" +
                        "buf.WriteString(\"Hello\")\n" +
                        "buf.WriteByte(' ')\n" +
                        "buf.Write([]byte(\"world\"))\n" +
                        "result := buf.String() // \"Hello world\"\n" +
                        "raw    := buf.Bytes()  // []byte"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── unicode ──────────────────────────────────────────────────────
            item {
                SectionCard(title = "unicode and unicode/utf8") {
                    BodyText("Go strings are sequences of bytes (UTF-8 encoded). A rune is Go's name for a Unicode code point (int32). The unicode and unicode/utf8 packages help work with them correctly.")
                    BodyText("unicode — functions that classify and convert individual rune values.")
                    BodyText("unicode/utf8 — functions that work with UTF-8 encoded byte sequences.")
                    CodeBlock(
                        "import (\n" +
                        "    \"unicode\"\n" +
                        "    \"unicode/utf8\"\n" +
                        ")\n" +
                        "\n" +
                        "// unicode — classify runes\n" +
                        "unicode.IsLetter('A')   // true\n" +
                        "unicode.IsDigit('5')    // true\n" +
                        "unicode.IsSpace('\\t')   // true\n" +
                        "unicode.IsUpper('A')    // true\n" +
                        "unicode.ToLower('A')    // 'a'\n" +
                        "unicode.ToUpper('a')    // 'A'\n" +
                        "\n" +
                        "// unicode/utf8 — work with UTF-8 bytes\n" +
                        "s := \"héllo\"\n" +
                        "utf8.RuneCountInString(s)       // 5 (not len(s) which is 6 bytes)\n" +
                        "utf8.ValidString(s)             // true\n" +
                        "\n" +
                        "r, size := utf8.DecodeRuneInString(s) // r='h', size=1\n" +
                        "utf8.RuneLen('é')                      // 2 (bytes needed)\n" +
                        "\n" +
                        "// Iterating runes safely\n" +
                        "for i, r := range s {\n" +
                        "    fmt.Printf(\"%d: %c\\n\", i, r)\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── sort ─────────────────────────────────────────────────────────
            item {
                SectionCard(title = "sort") {
                    BodyText("The sort package provides sorting for built-in types and any custom type via sort.Interface.")
                    CodeBlock(
                        "import \"sort\"\n" +
                        "\n" +
                        "// Convenience functions for built-in types\n" +
                        "ints    := []int{3, 1, 4, 1, 5}\n" +
                        "strs    := []string{\"banana\", \"apple\", \"cherry\"}\n" +
                        "floats  := []float64{3.14, 1.41, 2.71}\n" +
                        "\n" +
                        "sort.Ints(ints)         // [1 1 3 4 5]\n" +
                        "sort.Strings(strs)      // [apple banana cherry]\n" +
                        "sort.Float64s(floats)   // [1.41 2.71 3.14]\n" +
                        "\n" +
                        "// sort.Slice — sort any slice with a custom less function\n" +
                        "people := []struct{ Name string; Age int }{\n" +
                        "    {\"Alice\", 30}, {\"Bob\", 25}, {\"Carol\", 35},\n" +
                        "}\n" +
                        "sort.Slice(people, func(i, j int) bool {\n" +
                        "    return people[i].Age < people[j].Age\n" +
                        "})\n" +
                        "\n" +
                        "// sort.Interface — implement for custom types\n" +
                        "type ByLength []string\n" +
                        "func (b ByLength) Len() int           { return len(b) }\n" +
                        "func (b ByLength) Less(i, j int) bool { return len(b[i]) < len(b[j]) }\n" +
                        "func (b ByLength) Swap(i, j int)      { b[i], b[j] = b[j], b[i] }\n" +
                        "sort.Sort(ByLength(strs))\n" +
                        "\n" +
                        "// Binary search\n" +
                        "idx := sort.SearchInts(ints, 4) // index of first element >= 4"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── container/list ───────────────────────────────────────────────
            item {
                SectionCard(title = "container/list") {
                    BodyText("container/list implements a doubly linked list. Elements can be inserted or removed in O(1) given an element pointer, unlike slices which require shifting.")
                    BodyText("Each element is a *list.Element with a Value field of type any. The list tracks its Front and Back elements.")
                    CodeBlock(
                        "import \"container/list\"\n" +
                        "\n" +
                        "l := list.New()\n" +
                        "\n" +
                        "// Insert\n" +
                        "e1 := l.PushBack(\"first\")\n" +
                        "e2 := l.PushBack(\"second\")\n" +
                        "e3 := l.PushFront(\"zero\")\n" +
                        "\n" +
                        "// Remove O(1)\n" +
                        "l.Remove(e2)\n" +
                        "\n" +
                        "// Insert before/after\n" +
                        "l.InsertAfter(\"inserted\", e1)\n" +
                        "\n" +
                        "// Iterate\n" +
                        "for e := l.Front(); e != nil; e = e.Next() {\n" +
                        "    fmt.Println(e.Value)\n" +
                        "}\n" +
                        "fmt.Println(\"length:\", l.Len())"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── container/heap ───────────────────────────────────────────────
            item {
                SectionCard(title = "container/heap") {
                    BodyText("container/heap provides heap operations for any type implementing heap.Interface. heap.Interface extends sort.Interface with Push and Pop methods.")
                    BodyText("The heap is a min-heap by default — the Less function in sort.Interface determines ordering. For a max-heap, reverse the Less comparison.")
                    CodeBlock(
                        "import \"container/heap\"\n" +
                        "\n" +
                        "// Min-heap of ints\n" +
                        "type IntHeap []int\n" +
                        "func (h IntHeap) Len() int           { return len(h) }\n" +
                        "func (h IntHeap) Less(i, j int) bool { return h[i] < h[j] } // min-heap\n" +
                        "func (h IntHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }\n" +
                        "func (h *IntHeap) Push(x any)        { *h = append(*h, x.(int)) }\n" +
                        "func (h *IntHeap) Pop() any {\n" +
                        "    old := *h; n := len(old)\n" +
                        "    x := old[n-1]; *h = old[:n-1]; return x\n" +
                        "}\n" +
                        "\n" +
                        "h := &IntHeap{5, 3, 8, 1}\n" +
                        "heap.Init(h)                 // heapify\n" +
                        "heap.Push(h, 4)\n" +
                        "min := heap.Pop(h).(int)     // 1"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── os ───────────────────────────────────────────────────────────
            item {
                SectionCard(title = "os") {
                    BodyText("The os package provides a platform-independent interface to operating system functionality.")
                    BodyText("Files:")
                    CodeBlock(
                        "import \"os\"\n" +
                        "\n" +
                        "// Open for reading\n" +
                        "f, err := os.Open(\"file.txt\")        // read-only\n" +
                        "defer f.Close()\n" +
                        "\n" +
                        "// Create / truncate for writing\n" +
                        "f, err  = os.Create(\"out.txt\")\n" +
                        "\n" +
                        "// Full control: os.OpenFile(name, flags, perm)\n" +
                        "f, err  = os.OpenFile(\"log.txt\",\n" +
                        "    os.O_APPEND|os.O_CREATE|os.O_WRONLY, 0644)\n" +
                        "\n" +
                        "// Read and write directly\n" +
                        "buf := make([]byte, 1024)\n" +
                        "n, err := f.Read(buf)\n" +
                        "f.Write([]byte(\"data\"))\n" +
                        "\n" +
                        "// Stat, remove, mkdir\n" +
                        "info, err := os.Stat(\"file.txt\")   // info.Size(), info.IsDir(), …\n" +
                        "os.Remove(\"file.txt\")\n" +
                        "os.Mkdir(\"dir\", 0755)\n" +
                        "os.MkdirAll(\"a/b/c\", 0755)"
                    )
                    BodyText("Process and environment:")
                    CodeBlock(
                        "os.Args               // []string — command-line arguments (Args[0] is program)\n" +
                        "os.Getenv(\"HOME\")     // get environment variable\n" +
                        "os.Setenv(\"KEY\", \"v\") // set environment variable\n" +
                        "os.Exit(1)            // exit with code (defers do NOT run)\n" +
                        "\n" +
                        "// Standard streams\n" +
                        "os.Stdin   // *os.File\n" +
                        "os.Stdout  // *os.File\n" +
                        "os.Stderr  // *os.File"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── io / bufio ───────────────────────────────────────────────────
            item {
                SectionCard(title = "io and bufio") {
                    BodyText("io defines the core Reader and Writer interfaces used throughout the standard library. bufio wraps them with buffering for efficiency.")
                    CodeBlock(
                        "import (\n" +
                        "    \"io\"\n" +
                        "    \"bufio\"\n" +
                        ")\n" +
                        "\n" +
                        "// io.Reader / io.Writer — the two fundamental interfaces\n" +
                        "// Read(p []byte) (n int, err error)\n" +
                        "// Write(p []byte) (n int, err error)\n" +
                        "\n" +
                        "// io.Copy — copy all bytes from reader to writer\n" +
                        "n, err := io.Copy(dst, src)\n" +
                        "\n" +
                        "// io.ReadAll — read everything from a reader into []byte\n" +
                        "data, err := io.ReadAll(r)\n" +
                        "\n" +
                        "// io.TeeReader — read src; simultaneously write to w\n" +
                        "tr := io.TeeReader(src, w)\n" +
                        "\n" +
                        "// io.LimitReader — read at most n bytes\n" +
                        "lr := io.LimitReader(r, 1024)\n" +
                        "\n" +
                        "// bufio.NewReader / bufio.NewWriter — add buffering\n" +
                        "br := bufio.NewReader(f)    // 4096-byte buffer by default\n" +
                        "bw := bufio.NewWriter(f)\n" +
                        "bw.Flush()                  // must flush to write buffered data\n" +
                        "\n" +
                        "// bufio.Scanner — read line by line\n" +
                        "scanner := bufio.NewScanner(f)\n" +
                        "for scanner.Scan() {\n" +
                        "    line := scanner.Text()\n" +
                        "    fmt.Println(line)\n" +
                        "}\n" +
                        "if err := scanner.Err(); err != nil { /* handle */ }"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── net / net/http ───────────────────────────────────────────────
            item {
                SectionCard(title = "net and net/http") {
                    BodyText("net provides low-level networking (TCP, UDP, DNS). net/http builds a complete HTTP client and server on top of it.")
                    CodeBlock(
                        "import (\n" +
                        "    \"net\"\n" +
                        "    \"net/http\"\n" +
                        "    \"net/url\"\n" +
                        ")\n" +
                        "\n" +
                        "// Low-level TCP connection\n" +
                        "conn, err := net.Dial(\"tcp\", \"example.com:80\")\n" +
                        "defer conn.Close()\n" +
                        "\n" +
                        "// HTTP client — simple requests\n" +
                        "resp, err := http.Get(\"https://api.example.com/data\")\n" +
                        "defer resp.Body.Close()\n" +
                        "body, err := io.ReadAll(resp.Body)\n" +
                        "\n" +
                        "resp, err  = http.Post(url, \"application/json\", bytes.NewReader(data))\n" +
                        "\n" +
                        "// Custom request\n" +
                        "req, err := http.NewRequest(\"DELETE\", url, nil)\n" +
                        "req.Header.Set(\"Authorization\", \"Bearer token\")\n" +
                        "client := &http.Client{Timeout: 10 * time.Second}\n" +
                        "resp, err  = client.Do(req)\n" +
                        "\n" +
                        "// HTTP server\n" +
                        "http.HandleFunc(\"/hello\", func(w http.ResponseWriter, r *http.Request) {\n" +
                        "    fmt.Fprintln(w, \"Hello!\")\n" +
                        "})\n" +
                        "http.ListenAndServe(\":8080\", nil)\n" +
                        "\n" +
                        "// url.Parse — parse and build URLs\n" +
                        "u, err := url.Parse(\"https://example.com/path?k=v\")\n" +
                        "fmt.Println(u.Host, u.Path, u.RawQuery)\n" +
                        "\n" +
                        "// url.Values — encode query parameters\n" +
                        "params := url.Values{\"q\": {\"Go\"}, \"page\": {\"1\"}}\n" +
                        "fmt.Println(params.Encode()) // q=Go&page=1"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── encoding/json ────────────────────────────────────────────────
            item {
                SectionCard(title = "encoding/json") {
                    BodyText("encoding/json marshals Go values to JSON and unmarshals JSON into Go values. Struct field tags control JSON key names.")
                    BodyText("Exported (capital-letter) fields are included. Fields tagged with omitempty are omitted when zero/nil. Use - to always exclude a field.")
                    CodeBlock(
                        "import \"encoding/json\"\n" +
                        "\n" +
                        "type User struct {\n" +
                        "    Name  string `json:\"name\"`\n" +
                        "    Age   int    `json:\"age,omitempty\"`\n" +
                        "    Token string `json:\"-\"`            // never serialised\n" +
                        "}\n" +
                        "\n" +
                        "// Marshal — struct → []byte\n" +
                        "data, err := json.Marshal(User{Name: \"Alice\", Age: 30})\n" +
                        "// {\"name\":\"Alice\",\"age\":30}\n" +
                        "\n" +
                        "// Unmarshal — []byte → struct\n" +
                        "var u User\n" +
                        "err = json.Unmarshal(data, &u)\n" +
                        "\n" +
                        "// Streaming encode/decode (preferred for large data / HTTP)\n" +
                        "enc := json.NewEncoder(w)   // writes to io.Writer\n" +
                        "enc.Encode(u)\n" +
                        "dec := json.NewDecoder(r)   // reads from io.Reader\n" +
                        "dec.Decode(&u)"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── encoding/xml + encoding/base64 ───────────────────────────────
            item {
                SectionCard(title = "encoding/xml and encoding/base64") {
                    BodyText("encoding/xml marshals/unmarshals XML using struct tags. encoding/base64 encodes and decodes base64.")
                    CodeBlock(
                        "import (\n" +
                        "    \"encoding/xml\"\n" +
                        "    \"encoding/base64\"\n" +
                        ")\n" +
                        "\n" +
                        "// XML\n" +
                        "type Person struct {\n" +
                        "    XMLName xml.Name `xml:\"person\"`\n" +
                        "    Name    string   `xml:\"name\"`\n" +
                        "    Age     int      `xml:\"age,attr\"` // as attribute\n" +
                        "}\n" +
                        "data, err := xml.Marshal(Person{Name: \"Alice\", Age: 30})\n" +
                        "// <person age=\"30\"><name>Alice</name></person>\n" +
                        "var p Person\n" +
                        "err = xml.Unmarshal(data, &p)\n" +
                        "\n" +
                        "// base64 — standard encoding (A-Z a-z 0-9 + /)\n" +
                        "encoded := base64.StdEncoding.EncodeToString([]byte(\"Hello!\"))\n" +
                        "// \"SGVsbG8h\"\n" +
                        "decoded, err := base64.StdEncoding.DecodeString(encoded)\n" +
                        "\n" +
                        "// URL-safe encoding (replaces + with - and / with _)\n" +
                        "encoded = base64.URLEncoding.EncodeToString([]byte(\"Hello!\"))"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── sync ─────────────────────────────────────────────────────────
            item {
                SectionCard(title = "sync") {
                    BodyText("The sync package provides mutual-exclusion locks, wait groups, and other synchronisation primitives. See the Goroutines and Sync screen for full coverage with examples.")
                    BodyText("Quick reference:")
                    BodyText("sync.Mutex — Lock/Unlock; use defer Unlock for safety.")
                    BodyText("sync.RWMutex — RLock/RUnlock for concurrent readers; Lock/Unlock for exclusive writers.")
                    BodyText("sync.WaitGroup — Add before launching goroutine; Done (via defer) when finished; Wait blocks until counter reaches zero.")
                    BodyText("sync.Once — Do(f) calls f exactly once across all goroutines; used for lazy initialisation.")
                    BodyText("sync.Map — concurrent-safe map with Load, Store, Delete, Range; no generics.")
                    BodyText("sync.Cond — condition variable; Wait, Signal, Broadcast.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── time ─────────────────────────────────────────────────────────
            item {
                SectionCard(title = "time") {
                    BodyText("The time package handles times, durations, timers, and tickers. Duration is stored internally as nanoseconds (int64).")
                    CodeBlock(
                        "import \"time\"\n" +
                        "\n" +
                        "now := time.Now()              // time.Time (local)\n" +
                        "utc := now.UTC()               // convert to UTC\n" +
                        "\n" +
                        "// Duration constants and arithmetic\n" +
                        "d := 2*time.Hour + 30*time.Minute\n" +
                        "time.Sleep(500 * time.Millisecond)\n" +
                        "\n" +
                        "// Since / Until\n" +
                        "elapsed := time.Since(start)   // duration since start\n" +
                        "remaining := time.Until(end)   // duration until end\n" +
                        "\n" +
                        "// Format — uses the reference time Mon Jan 2 15:04:05 MST 2006\n" +
                        "now.Format(\"2006-01-02 15:04:05\")\n" +
                        "now.Format(time.RFC3339)        // 2009-11-10T23:00:00Z\n" +
                        "\n" +
                        "// Parse\n" +
                        "t, err := time.Parse(\"2006-01-02\", \"2024-03-15\")\n" +
                        "\n" +
                        "// Timer — fires once after duration\n" +
                        "timer := time.NewTimer(1 * time.Second)\n" +
                        "<-timer.C  // block until timer fires\n" +
                        "\n" +
                        "// time.After — shorthand, returns a channel\n" +
                        "select {\n" +
                        "case <-time.After(2 * time.Second):\n" +
                        "    fmt.Println(\"timed out\")\n" +
                        "case result := <-ch:\n" +
                        "    fmt.Println(result)\n" +
                        "}\n" +
                        "\n" +
                        "// Ticker — fires repeatedly\n" +
                        "ticker := time.NewTicker(1 * time.Second)\n" +
                        "defer ticker.Stop()\n" +
                        "for t := range ticker.C {\n" +
                        "    fmt.Println(\"tick:\", t)\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── log / testing / runtime ──────────────────────────────────────
            item {
                SectionCard(title = "log, testing, runtime") {
                    BodyText("log — structured logging to stderr (or a custom writer). log.Fatal calls os.Exit(1) after logging.")
                    CodeBlock(
                        "import \"log\"\n" +
                        "\n" +
                        "log.Println(\"server started\")\n" +
                        "log.Printf(\"listening on :%d\", port)\n" +
                        "log.Fatal(\"critical error\")   // logs then os.Exit(1)\n" +
                        "log.SetFlags(log.Ldate | log.Ltime | log.Lshortfile)\n" +
                        "log.SetPrefix(\"[myapp] \")\n" +
                        "\n" +
                        "// log/slog — structured logging (Go 1.21)\n" +
                        "slog.Info(\"user logged in\", \"user\", \"alice\", \"ip\", \"1.2.3.4\")"
                    )
                    BodyText("testing — unit and benchmark tests. Test functions must be named TestXxx and live in _test.go files.")
                    CodeBlock(
                        "// file: math_test.go\n" +
                        "import \"testing\"\n" +
                        "\n" +
                        "func TestAdd(t *testing.T) {\n" +
                        "    if got := Add(2, 3); got != 5 {\n" +
                        "        t.Errorf(\"Add(2,3) = %d, want 5\", got)\n" +
                        "    }\n" +
                        "}\n" +
                        "\n" +
                        "// Subtests\n" +
                        "func TestMul(t *testing.T) {\n" +
                        "    t.Run(\"positive\", func(t *testing.T) { /* … */ })\n" +
                        "    t.Run(\"negative\", func(t *testing.T) { /* … */ })\n" +
                        "}\n" +
                        "\n" +
                        "// Benchmark\n" +
                        "func BenchmarkAdd(b *testing.B) {\n" +
                        "    for i := 0; i < b.N; i++ { Add(2, 3) }\n" +
                        "}\n" +
                        "// go test -bench=. -benchmem"
                    )
                    BodyText("runtime — inspect and control the Go runtime.")
                    CodeBlock(
                        "import (\n" +
                        "    \"runtime\"\n" +
                        "    \"runtime/pprof\"\n" +
                        ")\n" +
                        "\n" +
                        "runtime.GOMAXPROCS(4)          // use 4 OS threads\n" +
                        "n := runtime.NumCPU()          // logical CPUs available\n" +
                        "g := runtime.NumGoroutine()    // currently running goroutines\n" +
                        "runtime.GC()                   // trigger a GC cycle\n" +
                        "\n" +
                        "// runtime/pprof — CPU and memory profiling\n" +
                        "f, _ := os.Create(\"cpu.prof\")\n" +
                        "pprof.StartCPUProfile(f)\n" +
                        "// … run workload …\n" +
                        "pprof.StopCPUProfile()\n" +
                        "\n" +
                        "mf, _ := os.Create(\"mem.prof\")\n" +
                        "pprof.WriteHeapProfile(mf)\n" +
                        "// go tool pprof cpu.prof"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── sync/atomic ──────────────────────────────────────────────────
            item {
                SectionCard(title = "sync/atomic") {
                    BodyText("sync/atomic provides low-level atomic memory operations without locks. There are two APIs.")
                    BodyText("Function API (all Go versions) — operates on primitive pointers (*int32, *int64, *uint32, *uint64, *uintptr, *unsafe.Pointer):")
                    CodeBlock(
                        "import \"sync/atomic\"\n" +
                        "\n" +
                        "var counter int64\n" +
                        "\n" +
                        "atomic.StoreInt64(&counter, 42)        // store\n" +
                        "v := atomic.LoadInt64(&counter)        // load\n" +
                        "\n" +
                        "atomic.AddInt64(&counter, 1)           // increment (returns new value)\n" +
                        "atomic.AddInt64(&counter, -1)          // decrement\n" +
                        "\n" +
                        "old := atomic.SwapInt64(&counter, 100) // atomic exchange; returns old value\n" +
                        "\n" +
                        "// Compare-And-Swap (CAS): if *addr == old, set *addr = new, return true\n" +
                        "swapped := atomic.CompareAndSwapInt64(&counter, 100, 200)"
                    )
                    BodyText("Typed struct API (Go 1.19+, preferred) — no raw pointers needed:")
                    CodeBlock(
                        "var c atomic.Int64   // zero value is ready to use\n" +
                        "\n" +
                        "c.Store(42)\n" +
                        "v := c.Load()\n" +
                        "c.Add(1)              // increment\n" +
                        "c.Add(-1)             // decrement\n" +
                        "old := c.Swap(100)    // atomic exchange\n" +
                        "ok  := c.CompareAndSwap(100, 200) // CAS\n" +
                        "\n" +
                        "// Other typed atomics:\n" +
                        "var flag   atomic.Bool\n" +
                        "var count  atomic.Uint64\n" +
                        "var ptr    atomic.Pointer[MyStruct]  // generic pointer\n" +
                        "\n" +
                        "// atomic.Value — store any comparable type atomically\n" +
                        "var val atomic.Value\n" +
                        "val.Store(config{Port: 8080})\n" +
                        "cfg := val.Load().(config)"
                    )
                    BodyText("When to use: use sync/atomic only for simple counters, flags, and lock-free structures where the raw primitive is required. For higher-level coordination (waiting, signalling, complex invariants) use sync.Mutex, sync.WaitGroup, or channels.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            // ── crypto / reflect ─────────────────────────────────────────────
            item {
                SectionCard(title = "crypto and reflect") {
                    BodyText("crypto/rand — cryptographically secure random bytes. Use this (not math/rand) whenever randomness is security-sensitive: tokens, keys, nonces.")
                    CodeBlock(
                        "import (\n" +
                        "    \"crypto/rand\"\n" +
                        "    \"math/big\"\n" +
                        ")\n" +
                        "\n" +
                        "// Fill a byte slice with random bytes\n" +
                        "token := make([]byte, 32)\n" +
                        "_, err := rand.Read(token)  // always fills the slice\n" +
                        "\n" +
                        "// Random big.Int in [0, max)\n" +
                        "n, err := rand.Int(rand.Reader, big.NewInt(100))"
                    )
                    BodyText("reflect — runtime reflection. Inspect types and values without knowing them at compile time. Commonly used for serialisation libraries, ORMs, and test utilities.")
                    CodeBlock(
                        "import \"reflect\"\n" +
                        "\n" +
                        "x := 42\n" +
                        "t := reflect.TypeOf(x)   // reflect.Type — \"int\"\n" +
                        "v := reflect.ValueOf(x)  // reflect.Value — holds 42\n" +
                        "fmt.Println(t.Kind())    // reflect.Int\n" +
                        "\n" +
                        "// Inspect struct fields and tags\n" +
                        "type User struct {\n" +
                        "    Name string `json:\"name\"`\n" +
                        "}\n" +
                        "ut := reflect.TypeOf(User{})\n" +
                        "for i := 0; i < ut.NumField(); i++ {\n" +
                        "    f := ut.Field(i)\n" +
                        "    fmt.Println(f.Name, f.Tag.Get(\"json\"))\n" +
                        "}\n" +
                        "\n" +
                        "// Set a value via reflection (must be addressable)\n" +
                        "p := &x\n" +
                        "reflect.ValueOf(p).Elem().SetInt(99)\n" +
                        "fmt.Println(x) // 99"
                    )
                    BodyText("Checking whether an interface holds a nil value — the problem. An interface value has two internal slots: a dynamic type and a dynamic value. The interface itself is nil only when both slots are empty. When you wrap a typed nil (e.g. a nil *MyError) inside an interface, the type slot is filled, so == nil returns false even though the concrete value is nil:")
                    CodeBlock(
                        "var p *MyError = nil\n" +
                        "var i error = p          // i holds type=*MyError, value=nil\n" +
                        "fmt.Println(p == nil)    // true\n" +
                        "fmt.Println(i == nil)    // false — the interface is NOT nil!\n" +
                        "\n" +
                        "// This catches many people off guard when a function returns\n" +
                        "// a typed nil wrapped in an interface (e.g. error)."
                    )
                    BodyText("Using reflect to check the concrete value. reflect.ValueOf(i) unwraps the interface and gives you the underlying value. Calling IsNil() on it tells you whether that concrete value is nil. IsNil() panics on non-nilable kinds (int, struct, etc.), so always check the Kind first:")
                    CodeBlock(
                        "import \"reflect\"\n" +
                        "\n" +
                        "func isNil(i interface{}) bool {\n" +
                        "    if i == nil {\n" +
                        "        return true  // both slots are empty — truly nil\n" +
                        "    }\n" +
                        "    v := reflect.ValueOf(i)\n" +
                        "    switch v.Kind() {\n" +
                        "    case reflect.Ptr, reflect.Slice, reflect.Map,\n" +
                        "         reflect.Chan, reflect.Func, reflect.Interface:\n" +
                        "        return v.IsNil()  // concrete value is nil\n" +
                        "    }\n" +
                        "    return false  // non-nilable kind (int, struct, ...)\n" +
                        "}\n" +
                        "\n" +
                        "var p *MyError = nil\n" +
                        "var i error = p\n" +
                        "fmt.Println(isNil(i))  // true"
                    )
                    BodyText("The real fix is to avoid returning typed nils in the first place — return bare nil from functions that return an interface type. The reflect approach is a last resort when you receive an interface from code you cannot control.")
                    BodyText("Caution: reflection bypasses compile-time type safety and is significantly slower than direct code. Use it only when the type is genuinely unknown at compile time.")
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
