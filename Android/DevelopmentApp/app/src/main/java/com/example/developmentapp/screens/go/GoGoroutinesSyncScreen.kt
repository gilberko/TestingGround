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
fun GoGoroutinesSyncScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Go — Goroutines and Sync",
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
                SectionCard(title = "What Is a Goroutine") {
                    BodyText("A goroutine is a lightweight, user-space thread managed entirely by the Go runtime — it is not an OS thread.")
                    BodyText("The Go scheduler uses M:N scheduling: it multiplexes many goroutines (M) onto a smaller pool of OS threads (N). Creating a goroutine is very cheap — each one starts with a 2–8 KB stack that grows and shrinks dynamically (up to 1 GB by default). Spawning thousands of goroutines at once is perfectly normal.")
                    BodyText("Comparison:\n• OS thread — heavy, ~1–8 MB stack, kernel involvement on every context switch\n• Goroutine — light, ~4 KB initial stack, switched entirely in user space by the Go runtime")
                    BodyText("A goroutine is not quite a work-item either — it runs concurrently for as long as it needs, not just once. Think of it as a very cheap concurrent function call.")
                }
            }

            item {
                SectionCard(title = "Launching a Goroutine — the go Keyword") {
                    BodyText("Prefix any function call with go to run it as a goroutine. Arguments are evaluated immediately at the go statement; the function body runs concurrently.")
                    CodeBlock("""
// Named function
go someFunc(arg1, arg2)

// Anonymous function — very common
go func(x int) {
    fmt.Println("goroutine got:", x)
}(42)   // 42 is evaluated now and passed in
                    """.trimIndent())
                    BodyText("No handle or ID is returned. There is no goroutine object to hold on to. If main() returns, all running goroutines are killed immediately — you must explicitly wait for them if their work matters.")
                    BodyText("Can the function have any signature? Yes — any function with any parameters works. The only restriction is that return values from a goroutine are discarded (you use channels to get results back).")
                }
            }

            item {
                SectionCard(title = "sync.WaitGroup — Waiting for Goroutines") {
                    BodyText("WaitGroup is the standard way to wait for a known number of goroutines to finish. It works like a counter: Add() increments it, Done() decrements it, and Wait() blocks until it reaches zero.")
                    CodeBlock("""
var wg sync.WaitGroup

for i := 0; i < 3; i++ {
    wg.Add(1)              // increment before launching
    go func(n int) {
        defer wg.Done()    // decrement when this goroutine exits
        fmt.Println("worker", n)
    }(i)
}

wg.Wait()  // blocks here until all three Done() calls have run
fmt.Println("all workers done")
                    """.trimIndent())
                    BodyText("Always call Add() before the go statement. Calling it from inside the goroutine creates a race condition where Wait() might return before Add() is called.")
                }
            }

            item {
                SectionCard(title = "sync.Mutex — Exclusive Lock") {
                    BodyText("A Mutex (mutual exclusion lock) lets only one goroutine at a time access a critical section. All others block on Lock() until the holder calls Unlock().")
                    CodeBlock("""
var (
    mu  sync.Mutex
    cnt int
)

func increment() {
    mu.Lock()
    defer mu.Unlock()   // always unlock, even if the function panics
    cnt++
}

// safe to call from multiple goroutines concurrently
go increment()
go increment()
                    """.trimIndent())
                    BodyText("A Mutex has zero value that is ready to use — no constructor needed. Never copy a Mutex after first use (pass by pointer or embed in a struct).")
                }
            }

            item {
                SectionCard(title = "sync.RWMutex — Reader-Writer Lock") {
                    BodyText("RWMutex allows multiple concurrent readers OR one exclusive writer — never both at the same time. Use it when reads are much more frequent than writes, as it lets readers proceed in parallel.")
                    CodeBlock("""
var (
    rw    sync.RWMutex
    cache map[string]string
)

// Reader — can run concurrently with other readers
func get(key string) string {
    rw.RLock()
    defer rw.RUnlock()
    return cache[key]
}

// Writer — exclusive; blocks until all readers release
func set(key, val string) {
    rw.Lock()
    defer rw.Unlock()
    cache[key] = val
}
                    """.trimIndent())
                    BodyText("RLock/RUnlock for readers; Lock/Unlock for writers. A writer blocks until all current readers have called RUnlock. New readers block while a writer is waiting — this prevents writer starvation.")
                }
            }

            item {
                SectionCard(title = "sync.Once — Execute Exactly Once") {
                    BodyText("sync.Once guarantees that a function is executed at most once, regardless of how many goroutines call Do() concurrently. It is the standard pattern for lazy, thread-safe initialisation.")
                    CodeBlock("""
var (
    once     sync.Once
    instance *MyService
)

func GetService() *MyService {
    once.Do(func() {
        // This runs exactly once, even under concurrent calls
        instance = &MyService{connect()}
    })
    return instance
}
                    """.trimIndent())
                    BodyText("All goroutines that call Do() while the function is still running will block and wait. After the first call completes, subsequent calls return immediately without executing the function again.")
                }
            }

            item {
                SectionCard(title = "sync.Map — Concurrent-Safe Map") {
                    BodyText("sync.Map is a map built for concurrent use — no external locking required. It is optimised for two specific patterns: many reads with infrequent writes, or writes that each happen to a different key only once.")
                    CodeBlock("""
var sm sync.Map

sm.Store("key", 42)                // insert / update

val, ok := sm.Load("key")         // lookup
if ok { fmt.Println(val) }

sm.Delete("key")                  // remove

// Iterate over all entries
sm.Range(func(k, v any) bool {
    fmt.Println(k, v)
    return true   // return false to stop early
})
                    """.trimIndent())
                    BodyText("Values are typed as any (interface{}), so you must type-assert on load: val.(int). There are no generics. For general-purpose concurrent access, a regular map protected by sync.Mutex is often simpler and equally fast.")
                }
            }

            item {
                SectionCard(title = "sync.Cond — Condition Variable") {
                    BodyText("sync.Cond lets goroutines wait efficiently for a shared condition to become true, without busy-looping. It is always associated with a Mutex.")
                    BodyText("You must hold the associated lock when calling Wait, Signal, or Broadcast.")
                    CodeBlock("""
mu   := &sync.Mutex{}
cond := sync.NewCond(mu)
ready := false

// Goroutine that waits for a condition
go func() {
    mu.Lock()
    for !ready {
        // Wait atomically: unlocks mu, suspends goroutine,
        // relocks mu when woken. Always loop — spurious wakeups exist.
        cond.Wait()
    }
    fmt.Println("condition met!")
    mu.Unlock()
}()

// Goroutine that signals the condition
go func() {
    mu.Lock()
    ready = true
    mu.Unlock()
    cond.Signal()    // wake one waiting goroutine
    // cond.Broadcast() // wake ALL waiting goroutines
}()
                    """.trimIndent())
                    BodyText("Always check the condition in a for loop, not an if — Wait can return spuriously (woken without Signal/Broadcast). Signal wakes one waiter; Broadcast wakes all of them.")
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
