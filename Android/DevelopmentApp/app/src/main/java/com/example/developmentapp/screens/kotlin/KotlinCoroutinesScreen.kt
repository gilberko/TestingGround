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
fun KotlinCoroutinesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Kotlin — Coroutines",
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
                SectionCard(title = "What Are Coroutines") {
                    BodyText(
                        "A coroutine is a suspendable unit of work. Unlike OS threads, coroutines are " +
                        "managed by the Kotlin runtime, not the OS scheduler, so creating 100,000 of " +
                        "them is practical. When a coroutine reaches a suspend point (e.g. waiting for " +
                        "a network response) it yields its carrier thread to run other coroutines. No " +
                        "thread is blocked. When the awaited work completes the coroutine resumes, " +
                        "possibly on a different thread. This is cooperative multitasking inside the JVM.\n\n" +
                        "Coroutines require the kotlinx.coroutines library " +
                        "(implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android')."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "suspend Functions") {
                    BodyText(
                        "A suspend function can be paused and resumed. It may only be called from " +
                        "another suspend function or from inside a coroutine. The compiler rewrites " +
                        "every suspend function into a state machine that saves and restores local " +
                        "variables across suspension points — no callbacks needed.\n\n" +
                        "withContext switches the coroutine to a different dispatcher for the duration " +
                        "of the block, then switches back."
                    )
                    CodeBlock(
                        "suspend fun fetchUser(id: Int): User {\n" +
                        "    val json = withContext(Dispatchers.IO) {\n" +
                        "        httpClient.get(\"https://api.example.com/users/\$id\")\n" +
                        "    }\n" +
                        "    return parseUser(json)   // runs on original dispatcher\n" +
                        "}\n" +
                        "\n" +
                        "// suspend fun cannot be called from regular (non-suspend) code directly\n" +
                        "// — you need a coroutine builder (see next section)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Coroutine Builders") {
                    BodyText(
                        "Builders are the entry points that bridge regular code into coroutine land.\n\n" +
                        "• launch — fire-and-forget, returns a Job. Exceptions propagate to the scope.\n" +
                        "• async — returns Deferred<T>. Call .await() to get the result. Exceptions " +
                        "are deferred until .await() is called.\n" +
                        "• runBlocking — blocks the current thread until the coroutine completes. " +
                        "Used in main() and tests, not in production Android code.\n" +
                        "• coroutineScope — suspends (does not block) until all child coroutines finish. " +
                        "Throws if any child fails."
                    )
                    CodeBlock(
                        "// launch — no result needed\n" +
                        "val job: Job = scope.launch {\n" +
                        "    println(fetchUser(1))\n" +
                        "}\n" +
                        "\n" +
                        "// async — result needed\n" +
                        "val deferred: Deferred<User> = scope.async { fetchUser(2) }\n" +
                        "val user = deferred.await()   // suspends until ready\n" +
                        "\n" +
                        "// Parallel async — both run concurrently\n" +
                        "suspend fun twoUsers(): Pair<User, User> = coroutineScope {\n" +
                        "    val a = async { fetchUser(1) }\n" +
                        "    val b = async { fetchUser(2) }\n" +
                        "    a.await() to b.await()\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Dispatchers") {
                    BodyText(
                        "A dispatcher decides which thread(s) a coroutine runs on.\n\n" +
                        "• Dispatchers.Main — Android UI thread. Required for updating UI elements.\n" +
                        "• Dispatchers.IO — elastic pool (up to 64 threads). For blocking I/O: " +
                        "network, file system, database.\n" +
                        "• Dispatchers.Default — CPU-count threads. For CPU-intensive work: " +
                        "sorting, parsing, complex computation.\n" +
                        "• Dispatchers.Unconfined — starts on the caller thread, resumes on " +
                        "whatever thread the suspension function uses. Rarely used directly."
                    )
                    CodeBlock(
                        "viewModelScope.launch(Dispatchers.IO) {\n" +
                        "    val data = repository.fetchFromDatabase()   // IO thread\n" +
                        "    withContext(Dispatchers.Main) {\n" +
                        "        uiState.value = data                    // UI thread\n" +
                        "    }\n" +
                        "}\n" +
                        "\n" +
                        "// Default: coroutines inherit the parent's dispatcher unless overridden\n" +
                        "// viewModelScope uses Dispatchers.Main.immediate by default"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Structured Concurrency") {
                    BodyText(
                        "Every coroutine runs inside a CoroutineScope. A scope owns its coroutines: " +
                        "when the scope is cancelled all its children are cancelled automatically. " +
                        "Children must complete before their parent scope completes. This eliminates " +
                        "leaked coroutines.\n\n" +
                        "Jobs form a parent-child hierarchy. A SupervisorJob breaks the propagation: " +
                        "a failing child does not cancel siblings or the parent."
                    )
                    CodeBlock(
                        "val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())\n" +
                        "\n" +
                        "scope.launch { heavyTask1() }   // failure here does not kill scope\n" +
                        "scope.launch { heavyTask2() }   // because SupervisorJob is the parent\n" +
                        "\n" +
                        "// Cancel all work when no longer needed\n" +
                        "scope.cancel()\n" +
                        "\n" +
                        "// Android lifecycle-aware scopes handle this automatically:\n" +
                        "// viewModelScope  — cancelled when ViewModel.onCleared() is called\n" +
                        "// lifecycleScope  — cancelled when the lifecycle owner is destroyed"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Cancellation & Timeouts") {
                    BodyText(
                        "Cancellation is cooperative: the coroutine must check for it. Calls to " +
                        "suspend functions (delay, withContext, etc.) are automatically cancellation " +
                        "points. If your coroutine does CPU-only work in a loop, call " +
                        "ensureActive() or yield() periodically to allow cancellation.\n\n" +
                        "withTimeout throws TimeoutCancellationException if the block does not " +
                        "complete in time. withTimeoutOrNull returns null instead of throwing."
                    )
                    CodeBlock(
                        "val job = scope.launch {\n" +
                        "    repeat(1000) { i ->\n" +
                        "        ensureActive()          // throws CancellationException if cancelled\n" +
                        "        processItem(i)\n" +
                        "    }\n" +
                        "}\n" +
                        "job.cancel()   // cooperative — processing stops at the next ensureActive\n" +
                        "\n" +
                        "// Timeout\n" +
                        "val result = withTimeoutOrNull(5_000) {\n" +
                        "    fetchUser(42)   // null if takes longer than 5 s\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Exception Handling") {
                    BodyText(
                        "launch and async differ in how they surface exceptions.\n\n" +
                        "• launch — an uncaught exception cancels the job and propagates up the " +
                        "scope hierarchy. Install a CoroutineExceptionHandler on the scope to " +
                        "catch it without cancelling the parent.\n" +
                        "• async — exception is stored in the Deferred and rethrown when .await() " +
                        "is called. Wrap await() in try/catch.\n\n" +
                        "CancellationException is special: it is used for normal cancellation and " +
                        "is never passed to a CoroutineExceptionHandler."
                    )
                    CodeBlock(
                        "val handler = CoroutineExceptionHandler { _, ex ->\n" +
                        "    Log.e(\"App\", \"Coroutine failed\", ex)\n" +
                        "}\n" +
                        "val scope = CoroutineScope(Dispatchers.IO + handler)\n" +
                        "\n" +
                        "// async — catch at await site\n" +
                        "val deferred = scope.async { riskyOperation() }\n" +
                        "try {\n" +
                        "    val value = deferred.await()\n" +
                        "} catch (e: Exception) {\n" +
                        "    handleError(e)\n" +
                        "}\n" +
                        "\n" +
                        "// supervisorScope: sibling coroutines survive each other's failures\n" +
                        "supervisorScope {\n" +
                        "    launch { mayFail() }    // failure here does not cancel sibling\n" +
                        "    launch { alsoRuns() }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Flow — Cold Streams") {
                    BodyText(
                        "Flow<T> is Kotlin's asynchronous stream type. It is cold: the producer block " +
                        "does not run until a collector calls collect {}. Each collector gets its own " +
                        "independent execution.\n\n" +
                        "flowOn changes the dispatcher of the upstream (emission side) without " +
                        "affecting the downstream (collection side).\n\n" +
                        "StateFlow and SharedFlow are hot — they emit whether or not anyone is " +
                        "collecting. StateFlow always holds the latest value (like LiveData). " +
                        "SharedFlow replays a configurable number of past emissions."
                    )
                    CodeBlock(
                        "fun countDown(from: Int): Flow<Int> = flow {\n" +
                        "    for (i in from downTo 0) {\n" +
                        "        emit(i)\n" +
                        "        delay(1_000)\n" +
                        "    }\n" +
                        "}\n" +
                        "\n" +
                        "scope.launch {\n" +
                        "    countDown(5)\n" +
                        "        .map { it * 2 }\n" +
                        "        .filter { it > 4 }\n" +
                        "        .flowOn(Dispatchers.Default)\n" +
                        "        .collect { println(it) }\n" +
                        "}\n" +
                        "\n" +
                        "// ViewModel hot state\n" +
                        "private val _uiState = MutableStateFlow(UiState())\n" +
                        "val uiState: StateFlow<UiState> = _uiState.asStateFlow()"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Android Integration") {
                    BodyText(
                        "Android KTX provides two lifecycle-aware scopes that handle cleanup automatically.\n\n" +
                        "• viewModelScope (androidx.lifecycle) — tied to the ViewModel. Cancelled " +
                        "when onCleared() is called. Runs on Dispatchers.Main.immediate by default.\n" +
                        "• lifecycleScope (androidx.lifecycle) — tied to an Activity or Fragment. " +
                        "Cancelled when the lifecycle owner is destroyed.\n\n" +
                        "repeatOnLifecycle suspends and re-launches a block whenever the lifecycle " +
                        "enters a given state (STARTED, RESUMED), and cancels it when it leaves. " +
                        "This is the recommended way to collect Flows from the UI layer."
                    )
                    CodeBlock(
                        "// ViewModel\n" +
                        "class MyViewModel : ViewModel() {\n" +
                        "    fun loadData() {\n" +
                        "        viewModelScope.launch {\n" +
                        "            val result = withContext(Dispatchers.IO) { repo.fetch() }\n" +
                        "            _uiState.value = result\n" +
                        "        }\n" +
                        "    }\n" +
                        "}\n" +
                        "\n" +
                        "// Fragment or Activity — collect safely\n" +
                        "viewLifecycleOwner.lifecycleScope.launch {\n" +
                        "    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {\n" +
                        "        viewModel.uiState.collect { state -> render(state) }\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
