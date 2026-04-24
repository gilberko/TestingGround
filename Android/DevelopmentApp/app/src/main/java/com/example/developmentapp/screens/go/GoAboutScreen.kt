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
fun GoAboutScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Go — About Go",
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
                SectionCard(title = "History and Origins") {
                    BodyText(
                        "Go (also called Golang) was created at Google by Rob Pike, Ken Thompson, and " +
                        "Robert Griesemer. It was publicly announced in November 2009 and reached its " +
                        "first stable release (Go 1.0) in March 2012."
                    )
                    BodyText(
                        "The language was designed to solve real pain points encountered in large-scale " +
                        "software development at Google — particularly the slow compilation times and " +
                        "complexity of large C++ codebases. The design goals were: simplicity (small, " +
                        "readable syntax), fast compilation, built-in concurrency, and a strong standard " +
                        "library. Go intentionally leaves out features that add complexity — no " +
                        "inheritance, no method overloading, no exceptions, no generics until Go 1.18."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Compiled Language") {
                    BodyText(
                        "Go is a compiled language. The go build command compiles your source code into " +
                        "a native binary for the target platform."
                    )
                    CodeBlock("go build main.go        # produces ./main (or main.exe on Windows)\ngo run main.go          # compile + run in one step (no permanent binary)")
                    BodyText(
                        "By default, Go produces a statically linked executable — all dependencies " +
                        "(including the Go runtime) are bundled into a single binary. No runtime " +
                        "installation is required on the target machine; you just copy the binary and " +
                        "run it. This makes deployment very simple compared to languages that require " +
                        "a separate interpreter or virtual machine."
                    )
                    BodyText(
                        "Go is known for extremely fast compile times, even for large codebases. This " +
                        "was a primary design goal — the authors wanted the edit-compile-test cycle to " +
                        "feel nearly instantaneous."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Memory Management — Garbage Collector") {
                    BodyText(
                        "Go has a built-in garbage collector (GC). The runtime automatically tracks " +
                        "heap allocations and reclaims memory that is no longer reachable. You do not " +
                        "need to call malloc/free or manage memory manually."
                    )
                    BodyText(
                        "Go does have pointers — you can take the address of a value with & and " +
                        "dereference with *:"
                    )
                    CodeBlock(
                        "x := 42\np := &x      // p is *int, holds the address of x\n" +
                        "*p = 100     // dereference: sets x to 100\nfmt.Println(x)  // 100"
                    )
                    BodyText(
                        "However, Go does not allow pointer arithmetic. You cannot do p++, p + 1, or " +
                        "index into memory via a pointer as you would in C. This eliminates an entire " +
                        "class of memory-safety bugs (buffer overflows, dangling pointer reads, etc.)."
                    )
                    BodyText(
                        "Pointer arithmetic is only possible via the unsafe package, which is " +
                        "deliberately named unsafe to signal that you are leaving Go's safety guarantees. " +
                        "It is rarely needed in normal Go code."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Goroutines — Lightweight Concurrency") {
                    BodyText(
                        "Goroutines are Go's built-in unit of concurrent execution. You launch one by " +
                        "putting the go keyword before a function call:"
                    )
                    CodeBlock(
                        "go fetchData()          // runs fetchData() concurrently\ngo func() {\n" +
                        "    fmt.Println(\"I run concurrently\")\n}()                     // anonymous goroutine"
                    )
                    BodyText(
                        "Goroutines are not OS threads. The Go runtime uses an M:N scheduler — it " +
                        "multiplexes many goroutines onto a small pool of OS threads (one per CPU core " +
                        "by default). A new goroutine starts with a small stack of just 2–8 KB that " +
                        "grows and shrinks dynamically as needed. This makes it practical to run " +
                        "hundreds of thousands of goroutines simultaneously, whereas OS threads " +
                        "typically cost hundreds of kilobytes each."
                    )
                    BodyText(
                        "Context switching between goroutines is handled by the Go runtime rather than " +
                        "the OS kernel, making it much cheaper. You can think of a goroutine as a very " +
                        "lightweight work item — far more efficient than a thread."
                    )
                    BodyText(
                        "Goroutines communicate via channels (chan T) rather than shared memory. " +
                        "Go's philosophy: \"Do not communicate by sharing memory; instead, share memory " +
                        "by communicating.\" Channels are typed conduits that goroutines can send values " +
                        "into and receive values from, making coordination safe and explicit."
                    )
                    CodeBlock(
                        "ch := make(chan int)       // create a channel of int\ngo func() {\n" +
                        "    ch <- 42               // send a value into the channel\n}()\nval := <-ch" +
                        "                 // receive the value (blocks until ready)\nfmt.Println(val)  // 42"
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
