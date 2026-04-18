package com.example.developmentapp.screens.cpp

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
fun CppLambdasThreadingScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Lambdas & STL Threading",
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

            // ── Lambda syntax ─────────────────────────────────────────────────
            item {
                SectionCard(title = "Lambda Syntax") {
                    BodyText(
                        "A lambda is an anonymous function defined inline. It can be stored in a " +
                        "variable, passed to a function, or called immediately."
                    )
                    CodeBlock(
                        "// Full syntax: [capture](params) -> ReturnType { body }\n" +
                        "auto add = [](int a, int b) -> int { return a + b; };\n" +
                        "int result = add(3, 4);   // result == 7\n\n" +
                        "// Return type is usually deduced — can omit ->\n" +
                        "auto square = [](int x) { return x * x; };\n\n" +
                        "// Zero-parameter lambda\n" +
                        "auto greet = []() { std::cout << \"hello\\n\"; };\n" +
                        "greet();\n\n" +
                        "// Call immediately (IIFE — immediately invoked)\n" +
                        "int n = [](int x) { return x * 2; }(21);  // n == 42"
                    )
                    BodyText(
                        "Lambdas are named by assigning them to auto or std::function variables. " +
                        "The type of a lambda is an anonymous compiler-generated struct — only auto " +
                        "can hold it without type erasure overhead."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Closures and capture ──────────────────────────────────────────
            item {
                SectionCard(title = "Closures and Capture") {
                    BodyText(
                        "A closure is a lambda that captures variables from the enclosing scope. " +
                        "The capture list in [] specifies what to capture and how."
                    )
                    CodeBlock(
                        "int base = 10;\n" +
                        "int factor = 3;\n\n" +
                        "// [] — capture nothing (accessing base/factor would be a compile error)\n" +
                        "auto noCapture = []() { return 42; };\n\n" +
                        "// [=] — capture all local variables by value (copy at lambda creation)\n" +
                        "auto byVal = [=]() { return base + factor; };  // reads copies\n\n" +
                        "// [&] — capture all by reference (reads the live variable)\n" +
                        "auto byRef = [&]() { return base + factor; };\n" +
                        "base = 20;\n" +
                        "std::cout << byRef();  // 23 — sees the updated base\n\n" +
                        "// Named captures — mix value and reference per variable\n" +
                        "auto mixed = [base, &factor]() { return base + factor; };\n" +
                        "// base is copied at lambda creation; factor is a reference"
                    )
                    BodyText(
                        "Dangling reference risk: if a lambda captures by reference and outlives the " +
                        "scope of the captured variable (e.g. stored in a callback, passed to a thread), " +
                        "the reference becomes dangling — undefined behavior. Prefer capture by value " +
                        "for lambdas that escape the current scope."
                    )
                    CodeBlock(
                        "// mutable lambda — allows modifying value-captured copies\n" +
                        "int count = 0;\n" +
                        "auto counter = [count]() mutable {\n" +
                        "    count++;            // modifies the lambda's own copy, not the outer 'count'\n" +
                        "    return count;\n" +
                        "};\n" +
                        "counter();  // 1\n" +
                        "counter();  // 2\n" +
                        "// count in the outer scope is still 0"
                    )
                    BodyText(
                        "Init-captures (C++14) let you create a new named variable inside the closure, " +
                        "initialized from any expression — not just copying or referencing an existing " +
                        "variable under its original name. The syntax is [newName = expr] in the capture list."
                    )
                    CodeBlock(
                        "// [y = x] — capture x but call it y inside the lambda\n" +
                        "int x = 10;\n" +
                        "auto f = [y = x]() { return y * 2; };  // y is a copy of x, named y\n" +
                        "x = 99;\n" +
                        "f();  // still 20 — y was initialized at lambda creation\n\n" +
                        "// [my_obj = this] — capture 'this' under a friendlier name\n" +
                        "struct Timer {\n" +
                        "    int ms;\n" +
                        "    auto makeCallback() {\n" +
                        "        return [my_obj = this]() { my_obj->ms = 0; };\n" +
                        "    }\n" +
                        "};\n\n" +
                        "// [copy = *this] — capture a VALUE COPY of the whole object\n" +
                        "// Avoids dangling 'this' when the lambda outlives the object\n" +
                        "struct Widget {\n" +
                        "    int value = 42;\n" +
                        "    auto safeCallback() {\n" +
                        "        return [copy = *this]() { return copy.value; };\n" +
                        "    }\n" +
                        "};\n\n" +
                        "// [ptr = std::move(up)] — move a unique_ptr into the lambda\n" +
                        "// Only works with init-capture; [=] would try to copy (deleted)\n" +
                        "auto up = std::make_unique<int>(7);\n" +
                        "auto g = [ptr = std::move(up)]() { return *ptr; };\n" +
                        "// up is now null; ptr lives inside the lambda's closure"
                    )
                    BodyText(
                        "Init-captures are the only way to move a move-only type (unique_ptr, " +
                        "packaged_task, etc.) into a lambda, since [=] would try to copy it, which " +
                        "is a deleted operation. The captured name (left of =) is a brand-new variable " +
                        "scoped to the closure — it can have a completely different name from the source expression."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── std::function ─────────────────────────────────────────────────
            item {
                SectionCard(title = "std::function") {
                    BodyText(
                        "std::function<Ret(Args...)> is a type-erased wrapper that can hold any " +
                        "callable with a matching signature: lambdas, plain function pointers, " +
                        "and functors (objects with operator()). Use it when you need to store " +
                        "or pass callables at runtime without knowing the concrete type at compile time."
                    )
                    CodeBlock(
                        "#include <functional>\n\n" +
                        "// Accept any callable that takes two ints and returns int\n" +
                        "int apply(std::function<int(int, int)> op, int a, int b) {\n" +
                        "    return op(a, b);\n" +
                        "}\n\n" +
                        "// Pass a lambda\n" +
                        "int r1 = apply([](int a, int b){ return a + b; }, 3, 4);  // 7\n\n" +
                        "// Pass a plain function\n" +
                        "int multiply(int a, int b) { return a * b; }\n" +
                        "int r2 = apply(multiply, 3, 4);  // 12\n\n" +
                        "// Store in a variable\n" +
                        "std::function<void()> task = []{ std::cout << \"done\\n\"; };\n" +
                        "task();"
                    )
                    BodyText(
                        "Performance note: std::function involves a heap allocation and virtual-dispatch " +
                        "overhead for type erasure. For performance-critical code, prefer template " +
                        "parameters (auto or template<typename F>) which let the compiler inline the " +
                        "call. Use std::function when you genuinely need to store heterogeneous " +
                        "callables in a container or across an ABI boundary."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── std::thread ───────────────────────────────────────────────────
            item {
                SectionCard(title = "std::thread") {
                    BodyText(
                        "std::thread launches a new OS thread. The constructor accepts any callable " +
                        "(function pointer, lambda, functor) followed by its arguments. Arguments " +
                        "are copied by default — use std::ref() to pass by reference."
                    )
                    CodeBlock(
                        "#include <thread>\n\n" +
                        "void worker(int id) {\n" +
                        "    std::cout << \"thread \" << id << \" running\\n\";\n" +
                        "}\n\n" +
                        "std::thread t1(worker, 1);      // starts immediately\n" +
                        "std::thread t2([](){ std::cout << \"lambda thread\\n\"; });\n\n" +
                        "// Pass by reference — must use std::ref\n" +
                        "int result = 0;\n" +
                        "std::thread t3([&result](){ result = 42; });  // by ref in lambda\n" +
                        "// OR: std::thread t3(someFunc, std::ref(result));"
                    )
                    BodyText(
                        "join() vs detach():"
                    )
                    BodyText(
                        "join() — the calling thread blocks until the target thread finishes. " +
                        "After join() returns, the thread's resources are released. Use when you " +
                        "need the thread's result or must guarantee it has completed before continuing."
                    )
                    BodyText(
                        "detach() — the thread is released to run independently in the background; " +
                        "the std::thread object no longer refers to it. The caller continues " +
                        "immediately. Danger: if the process exits or resources the detached thread " +
                        "uses are destroyed before it finishes, the behavior is undefined. Prefer " +
                        "join() unless the thread is truly fire-and-forget and outlives everything " +
                        "it touches."
                    )
                    CodeBlock(
                        "t1.join();    // wait for t1 to finish\n" +
                        "t2.detach();  // let t2 run independently — we won't wait for it\n" +
                        "t3.join();\n\n" +
                        "// CRITICAL: if neither join() nor detach() is called before the\n" +
                        "// std::thread object goes out of scope, std::terminate() is called.\n" +
                        "// Always join or detach every thread."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── std::mutex and std::lock_guard ────────────────────────────────
            item {
                SectionCard(title = "std::mutex and std::lock_guard") {
                    BodyText(
                        "std::mutex protects shared data from concurrent access. Only one thread " +
                        "can hold the lock at a time — others block in m.lock() until it is released."
                    )
                    CodeBlock(
                        "#include <mutex>\n\n" +
                        "std::mutex m;\n" +
                        "int counter = 0;\n\n" +
                        "void increment() {\n" +
                        "    m.lock();\n" +
                        "    counter++;       // exclusive access\n" +
                        "    m.unlock();      // must always call — easy to forget on error paths\n" +
                        "}"
                    )
                    BodyText(
                        "std::lock_guard is the RAII wrapper for std::mutex. It locks on construction " +
                        "and unlocks automatically when it goes out of scope — even if an exception " +
                        "is thrown. Prefer lock_guard over manual lock/unlock."
                    )
                    CodeBlock(
                        "void increment() {\n" +
                        "    std::lock_guard<std::mutex> lg(m);  // locks here\n" +
                        "    counter++;                           // exclusive access\n" +
                        "}                                        // unlocks here automatically\n\n" +
                        "// C++17 CTAD — type deduced\n" +
                        "std::lock_guard lg(m);"
                    )
                    BodyText(
                        "std::mutex is non-recursive: if the same thread calls lock() twice without " +
                        "an intervening unlock() it deadlocks. Use std::recursive_mutex if a function " +
                        "that holds the lock may call itself or another function that also locks it."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── std::unique_lock ──────────────────────────────────────────────
            item {
                SectionCard(title = "std::unique_lock") {
                    BodyText(
                        "std::unique_lock is a more flexible RAII lock wrapper than lock_guard. " +
                        "It can be manually unlocked and re-locked, deferred, and it is required " +
                        "by std::condition_variable."
                    )
                    CodeBlock(
                        "std::mutex m;\n\n" +
                        "// Deferred lock — construct without locking yet\n" +
                        "std::unique_lock<std::mutex> ul(m, std::defer_lock);\n" +
                        "// ... do some work ...\n" +
                        "ul.lock();          // lock when ready\n" +
                        "// ... critical section ...\n" +
                        "ul.unlock();        // release early (lock_guard cannot do this)\n" +
                        "// ... non-critical work ...\n" +
                        "ul.lock();          // re-acquire\n" +
                        "// unlocks automatically when ul goes out of scope\n\n" +
                        "// Locking two mutexes without deadlock (C++17)\n" +
                        "std::unique_lock<std::mutex> l1(m1, std::defer_lock);\n" +
                        "std::unique_lock<std::mutex> l2(m2, std::defer_lock);\n" +
                        "std::lock(l1, l2);  // locks both atomically — deadlock-safe"
                    )
                    BodyText(
                        "Rule of thumb: use lock_guard when you only need to lock for the duration " +
                        "of a scope. Use unique_lock when you need early unlock, deferred locking, " +
                        "or a condition variable."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── std::condition_variable ───────────────────────────────────────
            item {
                SectionCard(title = "std::condition_variable") {
                    BodyText(
                        "A condition variable lets one thread wait until another signals that a " +
                        "condition is true. It must be used together with a std::unique_lock and " +
                        "a shared predicate (usually a bool or queue state)."
                    )
                    CodeBlock(
                        "#include <condition_variable>\n\n" +
                        "std::mutex m;\n" +
                        "std::condition_variable cv;\n" +
                        "std::queue<int> q;\n\n" +
                        "// Consumer thread\n" +
                        "void consumer() {\n" +
                        "    std::unique_lock<std::mutex> ul(m);\n" +
                        "    // wait() atomically releases the lock and suspends.\n" +
                        "    // Re-acquires the lock when notified AND predicate is true.\n" +
                        "    // The predicate guards against spurious wakeups.\n" +
                        "    cv.wait(ul, []{ return !q.empty(); });\n" +
                        "    int val = q.front(); q.pop();\n" +
                        "    ul.unlock();\n" +
                        "    process(val);\n" +
                        "}\n\n" +
                        "// Producer thread\n" +
                        "void producer(int val) {\n" +
                        "    {\n" +
                        "        std::lock_guard<std::mutex> lg(m);\n" +
                        "        q.push(val);\n" +
                        "    }                          // release lock before notifying\n" +
                        "    cv.notify_one();           // wake one waiting thread\n" +
                        "    // cv.notify_all();        // wake all waiting threads\n" +
                        "}"
                    )
                    BodyText(
                        "Spurious wakeups: condition variables can wake up without notify being called " +
                        "(OS-level behavior). Always check the predicate in a loop — the two-argument " +
                        "wait(lock, pred) form handles this automatically."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── std::semaphore ────────────────────────────────────────────────
            item {
                SectionCard(title = "std::semaphore (C++20)") {
                    BodyText(
                        "std::semaphore was added in C++20 (header <semaphore>). It is a classical " +
                        "counting semaphore: an integer counter that can be atomically decremented " +
                        "(acquire — blocks if counter is 0) or incremented (release)."
                    )
                    CodeBlock(
                        "#include <semaphore>  // C++20\n\n" +
                        "// counting_semaphore<MaxValue> — MaxValue is the compile-time max\n" +
                        "std::counting_semaphore<3> sem(3);  // start with 3 permits\n\n" +
                        "// Worker threads — each acquires a permit before entering the pool\n" +
                        "void worker() {\n" +
                        "    sem.acquire();      // decrement; blocks if counter == 0\n" +
                        "    doWork();\n" +
                        "    sem.release();      // increment; wakes a blocked thread\n" +
                        "}\n\n" +
                        "// binary_semaphore is a convenient alias for counting_semaphore<1>\n" +
                        "std::binary_semaphore ready(0);   // start at 0 — not signalled\n\n" +
                        "std::thread producer([&]{\n" +
                        "    prepareData();\n" +
                        "    ready.release();    // signal consumer\n" +
                        "});\n\n" +
                        "ready.acquire();        // block until producer signals\n" +
                        "consumeData();\n" +
                        "producer.join();"
                    )
                    BodyText(
                        "Semaphore vs mutex: a mutex must be released by the same thread that locked " +
                        "it. A semaphore has no ownership — any thread can release it. This makes " +
                        "semaphores the right tool for signalling between threads (one thread " +
                        "produces, another consumes) and for limiting concurrency to N threads at " +
                        "a time (resource pool with N slots)."
                    )
                    BodyText(
                        "try_acquire() returns immediately with false if the counter is 0. " +
                        "try_acquire_for(duration) and try_acquire_until(time_point) are timed " +
                        "variants added alongside the C++20 semaphore."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
