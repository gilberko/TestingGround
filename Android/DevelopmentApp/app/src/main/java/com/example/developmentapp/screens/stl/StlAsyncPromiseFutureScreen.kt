package com.example.developmentapp.screens.stl

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
fun StlAsyncPromiseFutureScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Async, Promise and Future",
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
            modifier = Modifier.padding(innerPadding).padding(horizontal = 16.dp)
        ) {
            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("std::future — Moving Results Between Threads") {
                    BodyText("std::future<T> is a handle to a value that will be produced asynchronously — typically by another thread. It represents the eventual result of a computation, including the possibility that the computation threw an exception.")
                    BodyText("Header: <future>")
                    BodyText("get() — blocks the calling thread until the result is ready, then returns it. If the async operation threw an exception, get() rethrows it. Can only be called once; after that the future is invalid.")
                    BodyText("wait() — blocks until the result is ready but does not return it. Useful when you want to synchronise without immediately consuming the value.")
                    BodyText("wait_for(duration) — waits up to the specified duration, then returns a std::future_status:")
                    BodyText("  • future_status::ready   — result is available")
                    BodyText("  • future_status::timeout — duration elapsed, result not ready yet")
                    BodyText("  • future_status::deferred — the task is lazily deferred (see std::async below); no thread is running")
                    BodyText("wait_for lets you poll without committing to an indefinite block:")
                    CodeBlock("""
#include <future>
#include <chrono>
#include <iostream>

// assume: std::future<int> f = ...

while (true) {
    auto status = f.wait_for(std::chrono::milliseconds(100));
    if (status == std::future_status::ready) {
        std::cout << "Result: " << f.get() << "\n";
        break;
    } else if (status == std::future_status::timeout) {
        std::cout << "Still waiting...\n";
        // do other work here
    }
}""".trimIndent())
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Exceptions in std::future") {
                    BodyText("If the asynchronous operation throws an exception, the exception is captured and stored in the future's shared state. It is not immediately propagated.")
                    BodyText("When get() is called, the stored exception is rethrown in the calling thread. This means the exception safely crosses the thread boundary.")
                    CodeBlock("""
#include <future>
#include <stdexcept>
#include <iostream>

auto f = std::async(std::launch::async, []() -> int {
    throw std::runtime_error("something went wrong");
    return 42;
});

try {
    int value = f.get();   // rethrows the stored exception
} catch (const std::exception& e) {
    std::cerr << "Caught: " << e.what() << "\n";
    // "Caught: something went wrong"
}""".trimIndent())
                    BodyText("If you never call get() and the future is destroyed, the stored exception is silently discarded. Always call get() (or check via wait_for) to avoid silently losing errors.")
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("std::async — Launching Async Work") {
                    BodyText("std::async is the simplest way to run a callable asynchronously. It returns a std::future holding the callable's return value (or exception).")
                    BodyText("You control how the work is scheduled with a launch policy:")
                    BodyText("std::launch::async — a new thread is spawned immediately; the callable starts running concurrently with the caller.")
                    BodyText("std::launch::deferred — the callable is not run until get() or wait() is called on the returned future, and it runs in that calling thread. No new thread is ever created. This is essentially lazy evaluation.")
                    BodyText("Default (no policy) — the implementation picks either async or deferred. Avoid the default if you care about when or where the code runs; the behaviour is not portable.")
                    CodeBlock("""
#include <future>
#include <iostream>
#include <thread>

int compute(int x) { return x * x; }

int main() {
    // Runs on a new thread immediately
    auto f1 = std::async(std::launch::async, compute, 10);

    // Runs in this thread when f2.get() is called — no new thread
    auto f2 = std::async(std::launch::deferred, compute, 20);

    std::cout << "f1 = " << f1.get() << "\n";  // 100  (may already be done)
    std::cout << "f2 = " << f2.get() << "\n";  // 400  (runs here, now)
}""".trimIndent())
                    BodyText("Important: if launch::async is used, the destructor of the returned future blocks until the thread finishes. Store the future in a variable — if the return value is discarded immediately, the destructor runs at the end of that statement and blocks right there, making it synchronous.")
                    CodeBlock("""
// WRONG — blocks immediately because the temporary is destroyed
std::async(std::launch::async, some_task);

// RIGHT — future outlives the launch site
auto f = std::async(std::launch::async, some_task);
// ... do other work ...
f.get();""".trimIndent())
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("std::promise — Manually Setting a Future's Value") {
                    BodyText("std::promise<T> is the write side of the future/promise pair. It lets you manually inject a value (or exception) into a future from anywhere — not just from a callable's return statement.")
                    BodyText("Typical pattern: one thread holds the promise, another holds the future. The future-holder blocks on get(); the promise-holder calls set_value() when the result is ready.")
                    BodyText("get_future() — called on the promise once to obtain the associated std::future.")
                    BodyText("set_value(v) — stores the value in the shared state and unblocks any thread waiting on the future.")
                    BodyText("set_exception(ptr) — stores an exception. Use std::make_exception_ptr to wrap an exception object.")
                    BodyText("Use promise when the result comes from a callback, an event, or manual coordination rather than a plain function return:")
                    CodeBlock("""
#include <future>
#include <thread>
#include <iostream>

int main() {
    std::promise<int> prom;
    std::future<int>  fut = prom.get_future();

    // Producer thread — sets the value later
    std::thread producer([&prom]() {
        int result = 42;  // ... do some work ...
        prom.set_value(result);
    });

    // Consumer — blocks here until set_value is called
    std::cout << "Result: " << fut.get() << "\n";  // 42

    producer.join();
}""".trimIndent())
                    BodyText("Storing an exception via promise:")
                    CodeBlock("""
std::promise<int> prom;
auto fut = prom.get_future();

std::thread t([&prom]() {
    try {
        // ... work that may throw ...
        throw std::runtime_error("oops");
    } catch (...) {
        prom.set_exception(std::current_exception());
    }
});

try {
    int v = fut.get();  // rethrows "oops"
} catch (const std::exception& e) {
    std::cerr << e.what() << "\n";
}
t.join();""".trimIndent())
                    BodyText("If the promise is destroyed before set_value or set_exception is called, the future's get() will throw std::future_error with broken_promise.")
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
