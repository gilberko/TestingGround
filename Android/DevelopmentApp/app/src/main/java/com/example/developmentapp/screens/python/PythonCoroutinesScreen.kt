package com.example.developmentapp.screens.python

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun PythonCoroutinesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Python — Coroutines",
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
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            item { Spacer(Modifier.height(12.dp)) }

            // ── Event Loop Model ──────────────────────────────────────────────
            item {
                SectionCard(title = "The Event Loop Model") {
                    BodyText(
                        "Python's asyncio library is built on an event loop — a scheduler that manages a " +
                        "collection of tasks. The loop runs each task until it either finishes or voluntarily " +
                        "yields control by hitting an await expression."
                    )
                    BodyText(
                        "Coroutines are cooperative, not preemptive. Unlike OS threads, the event loop does " +
                        "not interrupt a coroutine mid-execution. A coroutine runs uninterrupted until it " +
                        "explicitly yields with await. This makes coroutines very lightweight — no locking " +
                        "needed for shared data accessed only between awaits, since only one coroutine runs " +
                        "at a time (within a single thread)."
                    )
                    BodyText(
                        "The event loop is ideal for I/O-bound concurrency: while one coroutine is waiting " +
                        "for a network response or file read, other coroutines can make progress. It is not " +
                        "suitable for CPU-bound work — for that, use multiprocessing."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── async def and coroutine objects ───────────────────────────────
            item {
                SectionCard(title = "async def and Coroutine Objects") {
                    BodyText(
                        "Prefixing a function definition with async makes it a coroutine function. Calling " +
                        "it does NOT execute the body — it returns a coroutine object."
                    )
                    CodeBlock(
                        """
                        import asyncio

                        async def greet(name):
                            print(f"Hello, {name}!")

                        coro = greet("Alice")   # returns a coroutine object — body NOT run yet
                        print(type(coro))       # <class 'coroutine'>

                        # To actually run it, give it to an event loop:
                        asyncio.run(greet("Alice"))   # Hello, Alice!
                        """.trimIndent()
                    )
                    BodyText(
                        "This two-step model (define → create object → schedule) is fundamental. It lets " +
                        "the event loop decide when to run each coroutine."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── asyncio.run ───────────────────────────────────────────────────
            item {
                SectionCard(title = "asyncio.run()") {
                    BodyText(
                        "asyncio.run(coro) is the standard entry point for async programs. It: " +
                        "(1) creates a new event loop, " +
                        "(2) runs the given coroutine to completion, " +
                        "(3) closes the loop and cleans up. " +
                        "Call it once from synchronous code — it should not be called from inside a " +
                        "running event loop."
                    )
                    CodeBlock(
                        """
                        import asyncio

                        async def main():
                            print("start")
                            await asyncio.sleep(1)
                            print("done after 1 second")

                        asyncio.run(main())   # creates loop, runs main(), closes loop
                        """.trimIndent()
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── await ─────────────────────────────────────────────────────────
            item {
                SectionCard(title = "await") {
                    BodyText(
                        "await suspends the current coroutine and hands control back to the event loop. " +
                        "The event loop can then run other ready tasks. When the awaited operation " +
                        "completes, the event loop reschedules the suspended coroutine and it resumes " +
                        "from exactly where it paused."
                    )
                    BodyText(
                        "await can only appear inside an async def function. The expression to the right " +
                        "of await must be an awaitable — a coroutine, a Task, or an object with __await__."
                    )
                    CodeBlock(
                        """
                        async def fetch_data():
                            print("fetching...")
                            await asyncio.sleep(2)    # yield control for 2 seconds
                            print("data ready")
                            return 42

                        async def main():
                            result = await fetch_data()   # suspend until fetch_data finishes
                            print(f"result: {result}")    # result: 42
                        """.trimIndent()
                    )
                    BodyText(
                        "Without await, fetch_data() would block the loop for the entire 2 seconds, " +
                        "preventing any other task from running."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── asyncio.sleep ─────────────────────────────────────────────────
            item {
                SectionCard(title = "asyncio.sleep()") {
                    BodyText(
                        "asyncio.sleep(seconds) is the async equivalent of time.sleep(). It yields control " +
                        "to the event loop for the specified duration, then reschedules the current coroutine."
                    )
                    CodeBlock(
                        """
                        import asyncio
                        import time

                        async def correct():
                            await asyncio.sleep(1)    # yields — other tasks can run during this second

                        async def wrong():
                            time.sleep(1)             # BLOCKS the entire thread for 1 second
                                                      # No other coroutine can run — defeats the purpose
                        """.trimIndent()
                    )
                    BodyText(
                        "Never use time.sleep() inside async code. It blocks the OS thread that runs the " +
                        "event loop, starving every other coroutine for the full sleep duration. Always " +
                        "use await asyncio.sleep() instead. The same applies to any blocking I/O call — " +
                        "use async-aware libraries (aiohttp, aiofiles, asyncpg, etc.) rather than their " +
                        "synchronous counterparts."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── asyncio.create_task ───────────────────────────────────────────
            item {
                SectionCard(title = "asyncio.create_task()") {
                    BodyText(
                        "asyncio.create_task(coro) wraps a coroutine in a Task and schedules it to run " +
                        "concurrently on the event loop. The task starts running as soon as the current " +
                        "coroutine reaches an await point."
                    )
                    CodeBlock(
                        """
                        async def worker(name, delay):
                            print(f"{name} starting")
                            await asyncio.sleep(delay)
                            print(f"{name} done")
                            return name

                        async def main():
                            task_a = asyncio.create_task(worker("A", 2))
                            task_b = asyncio.create_task(worker("B", 1))

                            # Both tasks are now scheduled. They will interleave at await points.
                            result_a = await task_a   # wait for A to finish
                            result_b = await task_b   # B likely already finished
                            print(result_a, result_b)

                        # Output order:
                        # A starting
                        # B starting
                        # B done        (after ~1 s)
                        # A done        (after ~2 s)
                        # A B
                        """.trimIndent()
                    )
                    BodyText(
                        "Without create_task(), awaiting worker('A', 2) then worker('B', 1) would run " +
                        "them sequentially — total 3 seconds. With create_task(), they run concurrently " +
                        "and finish in ~2 seconds total."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── asyncio.gather ────────────────────────────────────────────────
            item {
                SectionCard(title = "asyncio.gather()") {
                    BodyText(
                        "asyncio.gather(*coroutines_or_tasks) runs multiple awaitables concurrently and " +
                        "returns a list of their results in the same order as the arguments, regardless of " +
                        "completion order."
                    )
                    CodeBlock(
                        """
                        async def fetch(url, delay):
                            await asyncio.sleep(delay)   # simulate network latency
                            return f"data from {url}"

                        async def main():
                            results = await asyncio.gather(
                                fetch("api/users",    1),
                                fetch("api/products", 2),
                                fetch("api/orders",   1),
                            )
                            # All three run concurrently — done in ~2 s total, not 4 s
                            for r in results:
                                print(r)
                            # data from api/users
                            # data from api/products
                            # data from api/orders
                        """.trimIndent()
                    )
                    BodyText(
                        "gather() is a convenient shorthand: it internally creates tasks for each argument " +
                        "and awaits them all. If any coroutine raises an exception, gather() propagates it " +
                        "by default (pass return_exceptions=True to collect exceptions as return values " +
                        "instead of raising)."
                    )
                    BodyText(
                        "Summary — when to use what:\n" +
                        "  asyncio.run()         — entry point from sync code\n" +
                        "  await coro            — run one coroutine, wait for result\n" +
                        "  create_task()         — fire-and-forget, check result later\n" +
                        "  asyncio.gather()      — run N coroutines, collect all results"
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
