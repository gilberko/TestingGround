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
fun KotlinThreadingSyncScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Kotlin — Threading & Sync",
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
                SectionCard(title = "Platform Threads") {
                    BodyText(
                        "Kotlin runs on the JVM and uses the same Thread class as Java. Each platform " +
                        "thread maps 1:1 to an OS thread. They are heavyweight — roughly 1 MB of stack " +
                        "each — so thousands are practical but millions are not.\n\n" +
                        "The kotlin.concurrent package adds a thread { } builder function as a " +
                        "shorter alternative to the Java-style Thread { }.start() pattern."
                    )
                    CodeBlock(
                        "import kotlin.concurrent.thread\n\n" +
                        "// Java-style:\n" +
                        "val t = Thread { println(\"Hello from thread\") }\n" +
                        "t.start()\n" +
                        "t.join()   // wait for it to finish\n\n" +
                        "// Kotlin thread { } builder:\n" +
                        "val t2 = thread {              // starts immediately\n" +
                        "    println(\"Kotlin thread\")\n" +
                        "}\n\n" +
                        "// Daemon thread — killed when main thread exits:\n" +
                        "thread(isDaemon = true) {\n" +
                        "    while (true) { Thread.sleep(1000); doHousekeeping() }\n" +
                        "}\n\n" +
                        "// Named thread:\n" +
                        "thread(name = \"worker\") {\n" +
                        "    println(Thread.currentThread().name)  // worker\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Coroutines — What They Are") {
                    BodyText(
                        "Coroutines are Kotlin's primary concurrency mechanism from the " +
                        "kotlinx.coroutines library. They are lightweight — NOT OS threads.\n\n" +
                        "The JVM runs coroutines on a small pool of real threads called carrier " +
                        "threads. When a coroutine suspends (waiting for I/O, a delay, a network " +
                        "response), the carrier thread is released and immediately picks up another " +
                        "coroutine. No OS context switch, no stack parking — just a cheap state save.\n\n" +
                        "You can create hundreds of thousands of coroutines without memory issues. " +
                        "This makes coroutines ideal for I/O-bound work on Android (network calls, " +
                        "database queries) without blocking the UI thread."
                    )
                    CodeBlock(
                        "// Add to build.gradle.kts:\n" +
                        "// implementation(\"org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0\")\n\n" +
                        "import kotlinx.coroutines.*\n\n" +
                        "fun main() = runBlocking {   // bridges blocking main() to coroutines\n" +
                        "    launch {\n" +
                        "        delay(1000)          // suspends — does NOT block the thread\n" +
                        "        println(\"Coroutine 1\")\n" +
                        "    }\n" +
                        "    launch {\n" +
                        "        delay(500)\n" +
                        "        println(\"Coroutine 2\")\n" +
                        "    }\n" +
                        "    println(\"Main continues immediately\")\n" +
                        "}\n" +
                        "// Output:\n" +
                        "// Main continues immediately\n" +
                        "// Coroutine 2  (after 500ms)\n" +
                        "// Coroutine 1  (after 1000ms)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "suspend Functions") {
                    BodyText(
                        "A function marked suspend can be paused mid-execution and resumed later " +
                        "without blocking the carrier thread. The compiler transforms a suspend " +
                        "function into a state machine under the hood.\n\n" +
                        "Rules:\n" +
                        "• A suspend function can only be called from another suspend function or " +
                        "from inside a coroutine\n" +
                        "• Calling a suspend function does not automatically switch threads — use " +
                        "withContext(Dispatcher) to move work to a different thread pool\n" +
                        "• Regular (non-suspend) code cannot call suspend functions directly"
                    )
                    CodeBlock(
                        "// Declaring a suspend function:\n" +
                        "suspend fun fetchUser(id: Int): User {\n" +
                        "    delay(100)                        // suspend: wait without blocking\n" +
                        "    return api.getUser(id)            // may also be suspend\n" +
                        "}\n\n" +
                        "// withContext — switch to a different thread pool:\n" +
                        "suspend fun loadFromDisk(): String {\n" +
                        "    return withContext(Dispatchers.IO) {  // runs on IO thread pool\n" +
                        "        File(\"data.txt\").readText()       // blocking I/O — safe here\n" +
                        "    }  // resumes on the original dispatcher\n" +
                        "}\n\n" +
                        "// Can only call from a coroutine or suspend function:\n" +
                        "fun regularFunction() {\n" +
                        "    // fetchUser(1)   // COMPILE ERROR — not in a coroutine\n" +
                        "    CoroutineScope(Dispatchers.Main).launch {\n" +
                        "        val user = fetchUser(1)   // OK — inside a coroutine\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Coroutine Builders") {
                    BodyText(
                        "Coroutine builders are how you start coroutines:\n\n" +
                        "launch { } — fire and forget. Returns a Job. Use when you don't need a " +
                        "result back. The coroutine runs concurrently.\n\n" +
                        "async { } — returns a Deferred<T>. Call await() on it to suspend until the " +
                        "result is ready. Use for concurrent work that produces a value.\n\n" +
                        "runBlocking { } — blocks the current thread until all coroutines inside " +
                        "complete. Used for bridging regular code to coroutine code (e.g. in main() " +
                        "or tests). Never use in Android UI code — it will freeze the UI thread."
                    )
                    CodeBlock(
                        "// launch — fire and forget:\n" +
                        "val job: Job = CoroutineScope(Dispatchers.IO).launch {\n" +
                        "    saveData()\n" +
                        "}\n" +
                        "job.join()    // suspend until it finishes\n" +
                        "job.cancel()  // cancel if no longer needed\n\n" +
                        "// async/await — get a result:\n" +
                        "val deferred: Deferred<String> = CoroutineScope(Dispatchers.IO).async {\n" +
                        "    fetchDataFromNetwork()  // returns String\n" +
                        "}\n" +
                        "val result: String = deferred.await()  // suspends until ready\n\n" +
                        "// Two concurrent async operations:\n" +
                        "val d1 = async { fetchUser(1) }\n" +
                        "val d2 = async { fetchUser(2) }\n" +
                        "val (u1, u2) = Pair(d1.await(), d2.await())  // both run in parallel\n\n" +
                        "// runBlocking — for main() or tests only:\n" +
                        "fun main() = runBlocking {\n" +
                        "    val user = fetchUser(42)\n" +
                        "    println(user)\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Dispatchers") {
                    BodyText(
                        "A Dispatcher controls which thread (or thread pool) a coroutine runs on:\n\n" +
                        "Dispatchers.Main — the Android UI thread. Use for updating the UI. Only " +
                        "available in Android; any UI operation must happen here.\n\n" +
                        "Dispatchers.IO — optimised for blocking I/O. Backed by a shared thread " +
                        "pool that grows as needed. Use for network calls, file I/O, database.\n\n" +
                        "Dispatchers.Default — CPU-bound work. Backed by a thread pool with threads " +
                        "equal to the number of CPU cores. Use for parsing, sorting, computation.\n\n" +
                        "Dispatchers.Unconfined — starts on the caller's thread, resumes on whatever " +
                        "thread the suspension resumed on. Rarely used."
                    )
                    CodeBlock(
                        "// Android ViewModel pattern:\n" +
                        "class MyViewModel : ViewModel() {\n" +
                        "    fun loadData() {\n" +
                        "        viewModelScope.launch {          // starts on Main\n" +
                        "            val data = withContext(Dispatchers.IO) {\n" +
                        "                repository.fetchFromNetwork()  // IO thread\n" +
                        "            }\n" +
                        "            uiState.value = data         // back on Main\n" +
                        "        }\n" +
                        "    }\n" +
                        "}\n\n" +
                        "// Explicitly specify dispatcher:\n" +
                        "CoroutineScope(Dispatchers.IO).launch {\n" +
                        "    val raw = File(\"big.csv\").readText()          // IO\n" +
                        "    val parsed = withContext(Dispatchers.Default) {\n" +
                        "        parseCsv(raw)                              // CPU\n" +
                        "    }\n" +
                        "    withContext(Dispatchers.Main) {\n" +
                        "        updateUI(parsed)                           // UI\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Coroutines vs Threads") {
                    BodyText(
                        "The fundamental difference is cost. A platform thread costs ~1 MB of stack " +
                        "and an OS context switch on every block. A coroutine costs a few hundred " +
                        "bytes and never involves the OS when it suspends.\n\n" +
                        "Coroutines do not replace threads — they run ON threads. They are a way to " +
                        "multiplex a lot of concurrent work onto a small number of OS threads, " +
                        "avoiding the overhead of creating and switching between OS threads."
                    )
                    CodeBlock(
                        "// This works fine — 100,000 coroutines is trivial:\n" +
                        "fun main() = runBlocking {\n" +
                        "    val jobs = (1..100_000).map {\n" +
                        "        launch {\n" +
                        "            delay(1000)\n" +
                        "            print(\".\")\n" +
                        "        }\n" +
                        "    }\n" +
                        "    jobs.forEach { it.join() }\n" +
                        "}\n\n" +
                        "// This would crash — 100,000 platform threads use ~100 GB of RAM:\n" +
                        "// repeat(100_000) { Thread { Thread.sleep(1000) }.start() }\n\n" +
                        "// Thread.sleep() blocks the OS thread:\n" +
                        "Thread.sleep(1000)   // carrier thread parked — no other coroutine can run\n\n" +
                        "// delay() suspends the coroutine — carrier thread is freed:\n" +
                        "delay(1000)          // carrier thread runs other coroutines while waiting"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "No Zombie Threads — Join and Cancel") {
                    BodyText(
                        "Kotlin inherits Java's threading model — there are no zombie threads. When " +
                        "a thread's run block finishes, the Thread object moves to TERMINATED and " +
                        "is eligible for GC. No join is required to free resources.\n\n" +
                        "For coroutines, the equivalent concepts are:\n" +
                        "• job.join() — suspend until the coroutine completes\n" +
                        "• job.cancel() — request cancellation; the coroutine should cooperate by " +
                        "checking isActive or calling any suspend function (which checks cancellation)\n" +
                        "• Daemon threads: thread(isDaemon = true) — killed when all non-daemon " +
                        "threads exit\n\n" +
                        "Structured concurrency: coroutines launched inside a CoroutineScope are " +
                        "automatically cancelled when the scope is cancelled — no need to track every " +
                        "job manually. Android's viewModelScope and lifecycleScope leverage this."
                    )
                    CodeBlock(
                        "// Thread join:\n" +
                        "val t = thread { Thread.sleep(500); println(\"done\") }\n" +
                        "t.join()   // wait; no resources leak if you skip this\n\n" +
                        "// Coroutine join and cancel:\n" +
                        "val job = CoroutineScope(Dispatchers.IO).launch {\n" +
                        "    repeat(10) {\n" +
                        "        if (!isActive) return@launch  // cooperate with cancellation\n" +
                        "        delay(100)\n" +
                        "        println(\"step \$it\")\n" +
                        "    }\n" +
                        "}\n" +
                        "delay(350)\n" +
                        "job.cancel()   // requests cancellation\n" +
                        "job.join()     // wait for coroutine to fully stop\n\n" +
                        "// Structured concurrency — scope cancels all children:\n" +
                        "class MyViewModel : ViewModel() {\n" +
                        "    fun start() {\n" +
                        "        viewModelScope.launch { doWork() }  // auto-cancelled on clear()\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Synchronization") {
                    BodyText(
                        "For platform threads, Kotlin supports the same Java synchronization " +
                        "mechanisms:\n\n" +
                        "@Synchronized annotation on a function — equivalent to Java's synchronized " +
                        "keyword on a method; locks on 'this'.\n\n" +
                        "synchronized(lock) { } block — locks on any object. Same rules as Java: " +
                        "any non-null object can be the lock; use a private final Object to " +
                        "avoid external interference.\n\n" +
                        "For coroutines, synchronized and @Synchronized are NOT safe — they block " +
                        "the carrier thread, defeating the point of coroutines. Use Mutex from " +
                        "kotlinx.coroutines instead: mutex.withLock { } suspends the coroutine until " +
                        "the lock is available, leaving the carrier thread free for other work."
                    )
                    CodeBlock(
                        "import kotlinx.coroutines.sync.Mutex\n" +
                        "import kotlinx.coroutines.sync.withLock\n\n" +
                        "// @Synchronized — for platform threads:\n" +
                        "class Counter {\n" +
                        "    private var count = 0\n" +
                        "    @Synchronized fun increment() { count++ }\n" +
                        "    @Synchronized fun get() = count\n" +
                        "}\n\n" +
                        "// synchronized block — finer scope:\n" +
                        "class SafeMap {\n" +
                        "    private val lock = Any()\n" +
                        "    private val map = HashMap<String, Int>()\n" +
                        "    fun put(k: String, v: Int) = synchronized(lock) { map[k] = v }\n" +
                        "    fun get(k: String) = synchronized(lock) { map[k] }\n" +
                        "}\n\n" +
                        "// Mutex — for coroutines (suspends, not blocks):\n" +
                        "class CoroutineCounter {\n" +
                        "    private val mutex = Mutex()\n" +
                        "    private var count = 0\n\n" +
                        "    suspend fun increment() {\n" +
                        "        mutex.withLock { count++ }   // suspends if locked, never blocks\n" +
                        "    }\n" +
                        "    fun get() = count\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
