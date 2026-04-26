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
fun GoChannelsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Go — Channels",
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

            item {
                SectionCard(title = "What Is a Channel") {
                    BodyText("A channel is a typed conduit for passing values between goroutines. It is Go's primary mechanism for communication and synchronisation.")
                    BodyText("Go's philosophy: \"Do not communicate by sharing memory; share memory by communicating.\" Instead of protecting a shared variable with a mutex, pass ownership of the data through a channel.")
                    BodyText("Unbuffered channel — a send blocks until another goroutine is ready to receive, and a receive blocks until another goroutine sends. Both sides must be present at the same moment — they rendezvous.")
                    BodyText("Buffered channel — a send blocks only when the buffer is full; a receive blocks only when the buffer is empty. The channel acts as a queue between the two sides.")
                }
            }

            item {
                SectionCard(title = "Creating Channels") {
                    BodyText("Use make to create a channel. The type parameter specifies what values the channel carries.")
                    CodeBlock("""
ch  := make(chan int)      // unbuffered channel of int
bch := make(chan int, 10)  // buffered channel, capacity 10
sch := make(chan string)   // channel of string
pch := make(chan *MyStruct)// channel of pointer

var nilCh chan int          // nil channel — declared but not created
// Sending to or receiving from a nil channel blocks forever
                    """.trimIndent())
                    BodyText("Close a channel to signal that no more values will be sent:")
                    CodeBlock("""
close(ch)
// After close, receivers drain remaining buffered values,
// then get zero value + ok=false on every subsequent receive.
// Sending to a closed channel panics.
                    """.trimIndent())
                }
            }

            item {
                SectionCard(title = "Sending and Receiving") {
                    BodyText("The <- operator is used for both sending and receiving. Its position relative to the channel name determines the direction.")
                    CodeBlock("""
ch <- 42          // send 42 into ch  (blocks if unbuffered or buffer full)
v  := <-ch        // receive from ch  (blocks until a value arrives)

// Two-value receive — detect whether channel is closed
v, ok := <-ch
// ok == false means channel is closed and fully drained

// Range over a channel — loop exits when channel is closed
for v := range ch {
    fmt.Println(v)
}
                    """.trimIndent())
                    BodyText("Channels are safe to use from multiple goroutines simultaneously — that is their whole purpose.")
                }
            }

            item {
                SectionCard(title = "Buffered Channels") {
                    BodyText("A buffered channel decouples sender and receiver. The sender can keep going as long as there is buffer space; the receiver can drain at its own pace.")
                    CodeBlock("""
jobs := make(chan int, 5)  // buffer holds up to 5 items

// Producer — fills the buffer then closes
go func() {
    for j := 1; j <= 5; j++ {
        jobs <- j          // doesn't block until buffer is full
    }
    close(jobs)
}()

// Consumer — drains the buffer, stops when closed
for j := range jobs {
    fmt.Println("processing job", j)
}
                    """.trimIndent())
                    BodyText("After close(jobs) the for-range loop processes any remaining buffered values, then exits. len(ch) gives the number of queued items; cap(ch) gives the buffer capacity.")
                }
            }

            item {
                SectionCard(title = "Directional Channel Types") {
                    BodyText("A channel value is already a reference type — it is internally a pointer to a runtime data structure. You pass a channel by value (copying the chan variable), and both copies point to the same underlying channel. No pointer to a channel is needed.")
                    BodyText("You can restrict what a function is allowed to do with a channel using directional types:")
                    CodeBlock("""
chan<- T   // send-only  — can only write to it
<-chan T   // receive-only — can only read from it
                    """.trimIndent())
                    CodeBlock("""
func producer(out chan<- int) {
    out <- 1
    out <- 2
    close(out)
}

func consumer(in <-chan int) {
    for v := range in {
        fmt.Println(v)
    }
}

func main() {
    ch := make(chan int, 2)
    go producer(ch)  // chan int converts implicitly to chan<- int
    consumer(ch)     // chan int converts implicitly to <-chan int
}
                    """.trimIndent())
                    BodyText("A bidirectional chan T converts implicitly to either directional type. The compiler enforces the restriction — trying to send on a receive-only channel is a compile error.")
                }
            }

            item {
                SectionCard(title = "Goroutine to Main — Communication Pattern") {
                    BodyText("Channels are the idiomatic way for goroutines to report results back to the caller, without shared variables or mutexes.")
                    CodeBlock("""
func fetchData(id int, result chan<- string) {
    // simulate work
    result <- fmt.Sprintf("result from worker %d", id)
}

func main() {
    results := make(chan string, 3)  // buffered so goroutines don't block

    go fetchData(1, results)
    go fetchData(2, results)
    go fetchData(3, results)

    // collect all three results
    for i := 0; i < 3; i++ {
        fmt.Println(<-results)
    }
}
                    """.trimIndent())
                    BodyText("A common done-channel pattern signals completion without carrying a value. chan struct{} uses zero memory per send:")
                    CodeBlock("""
done := make(chan struct{})
go func() {
    doWork()
    close(done)  // signal completion
}()
<-done           // main blocks here until the goroutine closes done
                    """.trimIndent())
                }
            }

            item {
                SectionCard(title = "select — Multiplexing Channels") {
                    BodyText("select lets a goroutine wait on multiple channel operations at once. It blocks until one of the cases is ready, then executes that case. If multiple cases are ready simultaneously, one is chosen at random.")
                    CodeBlock("""
select {
case v := <-ch1:
    fmt.Println("received from ch1:", v)
case ch2 <- 99:
    fmt.Println("sent 99 to ch2")
case <-time.After(1 * time.Second):
    fmt.Println("timed out")
}
                    """.trimIndent())
                    BodyText("Add a default case to make select non-blocking — it executes immediately if no other case is ready:")
                    CodeBlock("""
select {
case v := <-ch:
    fmt.Println("got", v)
default:
    fmt.Println("channel was empty, moving on")
}
                    """.trimIndent())
                    BodyText("select is the foundation for timeouts, cancellation, fan-in merging, and heartbeat patterns in Go programs.")
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
