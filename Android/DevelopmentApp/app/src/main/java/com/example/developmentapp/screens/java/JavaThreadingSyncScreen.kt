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
fun JavaThreadingSyncScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Java — Threading & Sync",
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
                SectionCard(title = "Threads vs. Virtual Threads") {
                    BodyText(
                        "Java has two kinds of threads:\n\n" +
                        "Platform threads (traditional): each one is backed 1:1 by a real OS thread. " +
                        "The OS schedules them. They are expensive — roughly 1 MB of stack per thread. " +
                        "Creating thousands is practical; millions are not.\n\n" +
                        "Virtual threads (Java 21+, Project Loom): NOT OS threads. They are lightweight " +
                        "objects managed by the JVM. A small pool of OS 'carrier' threads runs them. " +
                        "When a virtual thread blocks (I/O, sleep, lock), the JVM unmounts it from its " +
                        "carrier and parks it; the carrier is immediately reused by another virtual thread. " +
                        "Millions of virtual threads are practical. There is no separate 'fiber' API — " +
                        "virtual threads ARE the fiber equivalent in Java.\n\n" +
                        "Before Java 21 there were no fibers in standard Java."
                    )
                    CodeBlock(
                        "// Platform thread — wraps a real OS thread:\n" +
                        "Thread platform = new Thread(() -> System.out.println(\"platform\"));\n" +
                        "platform.start();\n\n" +
                        "// Virtual thread (Java 21+) — NOT an OS thread:\n" +
                        "Thread virtual = Thread.ofVirtual().start(() -> System.out.println(\"virtual\"));\n\n" +
                        "// The carrier model at runtime:\n" +
                        "//  virtual thread 1  ─┐\n" +
                        "//  virtual thread 2  ─┼─► carrier (OS thread 1) ─► CPU\n" +
                        "//  virtual thread 3  ─┘  (only one mounted at a time)\n" +
                        "//\n" +
                        "// When vt1 calls Thread.sleep() or reads from a socket:\n" +
                        "//   JVM unmounts vt1, mounts vt2 on the same carrier immediately.\n" +
                        "//   OS thread is never blocked waiting — it always has useful work.\n\n" +
                        "// isVirtual() tells you which kind:\n" +
                        "System.out.println(platform.isVirtual()); // false\n" +
                        "System.out.println(virtual.isVirtual());  // true"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Starting a Thread") {
                    BodyText(
                        "There are three common patterns for creating a platform thread, and an " +
                        "additional fluent API for virtual threads. You must call start() — calling " +
                        "run() directly just executes the code on the current thread without creating " +
                        "a new one."
                    )
                    CodeBlock(
                        "// 1. Extend Thread (less common — ties the task to Thread):\n" +
                        "class MyThread extends Thread {\n" +
                        "    @Override public void run() { System.out.println(\"MyThread\"); }\n" +
                        "}\n" +
                        "new MyThread().start();\n\n" +
                        "// 2. Pass a Runnable (preferred — separates task from thread):\n" +
                        "Thread t2 = new Thread(new Runnable() {\n" +
                        "    @Override public void run() { System.out.println(\"Runnable\"); }\n" +
                        "});\n" +
                        "t2.start();\n\n" +
                        "// 3. Lambda (shortest):\n" +
                        "Thread t3 = new Thread(() -> System.out.println(\"lambda\"));\n" +
                        "t3.start();\n\n" +
                        "// Named thread via builder (Java 21+):\n" +
                        "Thread named = Thread.ofPlatform().name(\"worker\").start(() -> work());\n\n" +
                        "// Virtual thread (Java 21+):\n" +
                        "Thread vt = Thread.ofVirtual().name(\"vworker\").start(() -> work());\n\n" +
                        "// WRONG — run() runs on current thread, does NOT create a new thread:\n" +
                        "// t3.run();  // no new thread! just a plain method call"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Join vs. Detach — and Daemon Threads") {
                    BodyText(
                        "Join: thread.join() blocks the calling thread until the target thread finishes. " +
                        "Equivalent to pthread_join. Throws InterruptedException (checked).\n\n" +
                        "Detach: Java has no explicit detach() call. Instead:\n\n" +
                        "  Daemon threads — set thread.setDaemon(true) BEFORE calling start(). A daemon " +
                        "thread is automatically killed when all non-daemon threads have finished. The JVM " +
                        "does not wait for daemon threads to complete before exiting. Good for background " +
                        "services (logging, housekeeping).\n\n" +
                        "  Non-daemon (default) — keeps the JVM alive until its run() completes. If you " +
                        "start a non-daemon thread and never join it, it still runs to completion; the " +
                        "JVM just stays alive waiting for it."
                    )
                    CodeBlock(
                        "// Join — wait for thread to finish:\n" +
                        "Thread worker = new Thread(() -> {\n" +
                        "    Thread.sleep(500);\n" +
                        "    System.out.println(\"worker done\");\n" +
                        "});\n" +
                        "worker.start();\n" +
                        "worker.join();                 // blocks here until worker finishes\n" +
                        "System.out.println(\"main continues after join\");\n\n" +
                        "// Join with timeout (returns even if thread is still running):\n" +
                        "worker.join(1000);             // wait at most 1 second\n" +
                        "if (worker.isAlive()) System.out.println(\"still running\");\n\n" +
                        "// Daemon thread — killed when main exits:\n" +
                        "Thread daemon = new Thread(() -> {\n" +
                        "    while (true) { Thread.sleep(100); doHousekeeping(); }\n" +
                        "});\n" +
                        "daemon.setDaemon(true);   // MUST be called before start()!\n" +
                        "daemon.start();\n" +
                        "// When main() returns, the JVM exits and kills this daemon automatically.\n" +
                        "// If setDaemon() is called after start(): IllegalThreadStateException."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Zombie Threads") {
                    BodyText(
                        "Java does not have zombie threads.\n\n" +
                        "In C with pthreads, a thread that has finished but has not been joined is in " +
                        "a 'zombie' state — it holds system resources until joined or detached.\n\n" +
                        "In Java, once a thread's run() method returns, the Thread object moves to " +
                        "TERMINATED state and is eligible for garbage collection. No resources are " +
                        "held. You do not need to join a thread in order to free anything.\n\n" +
                        "If you never join a non-daemon thread, the only consequence is that the JVM " +
                        "stays alive until that thread naturally finishes its run() — there is no " +
                        "resource leak."
                    )
                    CodeBlock(
                        "// Thread lifecycle states:\n" +
                        "//   NEW → RUNNABLE → (BLOCKED / WAITING / TIMED_WAITING) → TERMINATED\n\n" +
                        "Thread t = new Thread(() -> System.out.println(\"done\"));\n" +
                        "System.out.println(t.getState()); // NEW\n" +
                        "t.start();\n" +
                        "t.join();\n" +
                        "System.out.println(t.getState()); // TERMINATED\n\n" +
                        "// TERMINATED thread is just a Java object — GC will collect it.\n" +
                        "// No join needed to avoid a 'zombie' — Java has no such concept.\n\n" +
                        "// Unlike pthreads where:\n" +
                        "//   pthread_join() is mandatory to reclaim kernel resources, OR\n" +
                        "//   pthread_detach() must be called — otherwise zombie thread lingers."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Getting a Return Value — Callable & Future") {
                    BodyText(
                        "Runnable.run() returns void. To get a value back from a thread, use " +
                        "Callable<T>, which is like Runnable but returns T and can throw checked " +
                        "exceptions from call().\n\n" +
                        "Submit a Callable to an ExecutorService; it returns a Future<T>. Call " +
                        "future.get() to block until the result is ready. If the Callable threw an " +
                        "exception, get() wraps it in ExecutionException — unwrap with getCause()."
                    )
                    CodeBlock(
                        "import java.util.concurrent.*;\n\n" +
                        "ExecutorService exec = Executors.newSingleThreadExecutor();\n\n" +
                        "// Submit a Callable<Integer>:\n" +
                        "Future<Integer> future = exec.submit(() -> {\n" +
                        "    Thread.sleep(300);\n" +
                        "    return 42;           // return value\n" +
                        "});\n\n" +
                        "// Do other work while the task runs...\n\n" +
                        "int result = future.get();          // blocks until ready, returns 42\n" +
                        "System.out.println(result);         // 42\n\n" +
                        "// With timeout:\n" +
                        "int r2 = future.get(1, TimeUnit.SECONDS);  // throws TimeoutException if slow\n\n" +
                        "// If the Callable threw:\n" +
                        "try {\n" +
                        "    future.get();\n" +
                        "} catch (ExecutionException e) {\n" +
                        "    Throwable cause = e.getCause();  // the original exception from call()\n" +
                        "    System.err.println(\"Task failed: \" + cause.getMessage());\n" +
                        "}\n\n" +
                        "exec.shutdown();"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Uncaught Exceptions in Threads") {
                    BodyText(
                        "If a thread's run() method throws an unchecked exception that is not caught " +
                        "inside run(), the JVM calls the thread's UncaughtExceptionHandler. The default " +
                        "handler prints the thread name and stack trace to System.err, then the thread " +
                        "terminates.\n\n" +
                        "You can install a custom handler per thread or globally. When using Future, " +
                        "exceptions from the task are captured and rethrown as ExecutionException when " +
                        "you call future.get() — so you always get to see them."
                    )
                    CodeBlock(
                        "// Per-thread handler:\n" +
                        "Thread t = new Thread(() -> { throw new RuntimeException(\"boom\"); });\n" +
                        "t.setUncaughtExceptionHandler((thread, ex) -> {\n" +
                        "    System.err.println(thread.getName() + \" crashed: \" + ex.getMessage());\n" +
                        "});\n" +
                        "t.start();\n\n" +
                        "// Global default handler (used when no per-thread handler is set):\n" +
                        "Thread.setDefaultUncaughtExceptionHandler((thread, ex) -> {\n" +
                        "    logger.error(\"Unhandled in \" + thread.getName(), ex);\n" +
                        "});\n\n" +
                        "// With Future — exception is captured and rethrown on get():\n" +
                        "Future<?> f = executor.submit(() -> {\n" +
                        "    throw new RuntimeException(\"task exploded\");\n" +
                        "});\n" +
                        "try {\n" +
                        "    f.get();                          // blocks, then rethrows\n" +
                        "} catch (ExecutionException e) {\n" +
                        "    System.err.println(e.getCause()); // RuntimeException: task exploded\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "synchronized — Intrinsic Lock (Mutex)") {
                    BodyText(
                        "Every Java object has a built-in monitor lock (intrinsic lock). The " +
                        "synchronized keyword acquires it. Only one thread at a time can hold a given " +
                        "object's lock — others block until it is released.\n\n" +
                        "The lock is reentrant: if the thread that already holds the lock tries to " +
                        "acquire it again (e.g. from a synchronized method calling another synchronized " +
                        "method on the same object), it succeeds instead of deadlocking.\n\n" +
                        "What can you synchronize on? Any non-null object reference — the object itself " +
                        "is not special, only its identity matters. Common choices:\n\n" +
                        "• this — synchronized method or synchronized(this) block. Simple, but external " +
                        "code can also lock on your object and interfere.\n\n" +
                        "• private final Object lock = new Object() — a dedicated, hidden lock. " +
                        "Preferred: nobody outside the class can acquire it, so you fully control who " +
                        "competes for it. The lock object itself does nothing — it is just a token.\n\n" +
                        "• ClassName.class — the Class object. Used by static synchronized methods. " +
                        "One instance shared across all threads for that class.\n\n" +
                        "• Any other object you own — e.g. a List or Map field. Two synchronized " +
                        "blocks that use the same object instance are mutually exclusive; blocks that " +
                        "use different objects are not.\n\n" +
                        "What NOT to synchronize on:\n" +
                        "• String literals — they are interned and shared across the JVM. " +
                        "synchronized(\"lock\") may accidentally contend with completely unrelated code.\n" +
                        "• Boxed Integer / Long from small values — Integer.valueOf(n) for small n " +
                        "returns cached instances, so two unrelated classes can end up locking the " +
                        "same object without knowing it.\n" +
                        "• null — throws NullPointerException immediately."
                    )
                    CodeBlock(
                        "// 1. synchronized method — locks 'this':\n" +
                        "class Counter {\n" +
                        "    private int count = 0;\n" +
                        "    public synchronized void increment() { count++; }\n" +
                        "    public synchronized int  get()       { return count; }\n" +
                        "}\n\n" +
                        "// 2. synchronized block on 'this' — identical effect, finer scope:\n" +
                        "public void increment() {\n" +
                        "    doSomeUnsynchronizedWork();\n" +
                        "    synchronized (this) { count++; }   // only this line is locked\n" +
                        "}\n\n" +
                        "// 3. Private dedicated lock object — preferred:\n" +
                        "class Counter2 {\n" +
                        "    private int count = 0;\n" +
                        "    private final Object lock = new Object();\n\n" +
                        "    public void increment() {\n" +
                        "        synchronized (lock) { count++; }\n" +
                        "    }\n" +
                        "}\n\n" +
                        "// 4. Two independent locks — separate concerns don't block each other:\n" +
                        "class Store {\n" +
                        "    private final Object inventoryLock = new Object();\n" +
                        "    private final Object orderLock     = new Object();\n\n" +
                        "    public void updateInventory() { synchronized (inventoryLock) { ... } }\n" +
                        "    public void placeOrder()      { synchronized (orderLock)     { ... } }\n" +
                        "    // updateInventory and placeOrder can now run truly in parallel\n" +
                        "}\n\n" +
                        "// 5. Static synchronized — locks Counter.class, not an instance:\n" +
                        "class IdGen {\n" +
                        "    private static int next = 0;\n" +
                        "    public static synchronized int nextId() { return next++; }\n" +
                        "    // equivalent: synchronized (IdGen.class) { return next++; }\n" +
                        "}\n\n" +
                        "// BAD — String literal (interned, shared globally):\n" +
                        "synchronized (\"myLock\") { ... }  // dangerous\n\n" +
                        "// BAD — cached Integer (Integer.valueOf(42) is the same object everywhere):\n" +
                        "Integer key = 42;\n" +
                        "synchronized (key) { ... }  // dangerous\n\n" +
                        "// Reentrant — same thread re-enters its own lock without deadlock:\n" +
                        "synchronized void outer() { inner(); }\n" +
                        "synchronized void inner() { /* ... */ }  // same 'this' lock — OK"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "volatile — Visibility and Ordering") {
                    BodyText(
                        "The problem volatile solves: by default the JVM and CPU are free to cache " +
                        "variables in registers or CPU caches. A write on thread A may not be visible " +
                        "to thread B for an indefinite time — thread B just keeps reading its stale " +
                        "cached copy. The JIT compiler may also hoist a variable read out of a loop " +
                        "entirely, making the loop never see an update written by another thread.\n\n" +
                        "Marking a field volatile gives two guarantees:\n" +
                        "• Visibility: every write is flushed to main memory immediately; every read " +
                        "fetches from main memory, never a cached copy. Thread B always sees what " +
                        "thread A last wrote.\n" +
                        "• Ordering (happens-before): a volatile write happens-before any subsequent " +
                        "volatile read of the same field. Reads and writes to volatile fields are not " +
                        "reordered relative to each other by the compiler or CPU.\n\n" +
                        "What volatile does NOT guarantee: atomicity of compound operations. count++ is " +
                        "read-modify-write — three steps. Even on a volatile int, two threads doing " +
                        "count++ simultaneously can both read the same old value and both write the same " +
                        "result, losing one increment. Use AtomicInteger for that.\n\n" +
                        "The classic safe pattern: a stop-flag written by one thread and polled by another."
                    )
                    CodeBlock(
                        "class Worker implements Runnable {\n" +
                        "    private volatile boolean running = true;\n\n" +
                        "    @Override\n" +
                        "    public void run() {\n" +
                        "        while (running) {    // always reads fresh value — no stale cache\n" +
                        "            doWork();\n" +
                        "        }\n" +
                        "    }\n\n" +
                        "    public void stop() { running = false; }  // visible to run() immediately\n" +
                        "}\n\n" +
                        "// Without volatile — BROKEN:\n" +
                        "// The JIT can hoist 'running' into a register once and never re-read it.\n" +
                        "// The while(running) loop may spin forever even after stop() is called.\n\n" +
                        "// volatile does NOT fix compound operations:\n" +
                        "private volatile int count = 0;\n" +
                        "count++;  // still a race: read-increment-write is NOT atomic\n" +
                        "          // use AtomicInteger.incrementAndGet() instead\n\n" +
                        "// volatile reference — the reference switch is visible, not object internals:\n" +
                        "private volatile Config config;\n" +
                        "// Writing config = new Config(...) makes the new reference visible atomically.\n" +
                        "// But fields inside the new Config must be safely published separately."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "ReentrantLock — Explicit Mutex") {
                    BodyText(
                        "java.util.concurrent.locks.ReentrantLock provides the same mutual exclusion " +
                        "as synchronized but with extra capabilities: timed lock attempts, " +
                        "interruptible locking, and optional fairness (FIFO ordering).\n\n" +
                        "Always release the lock in a finally block — if the code between lock() and " +
                        "unlock() throws, you must still release the lock or you will deadlock every " +
                        "other thread that needs it."
                    )
                    CodeBlock(
                        "import java.util.concurrent.locks.*;\n\n" +
                        "class SafeCounter {\n" +
                        "    private final ReentrantLock lock = new ReentrantLock();\n" +
                        "    private int count = 0;\n\n" +
                        "    public void increment() {\n" +
                        "        lock.lock();           // acquires; blocks if held by another thread\n" +
                        "        try {\n" +
                        "            count++;\n" +
                        "        } finally {\n" +
                        "            lock.unlock();     // ALWAYS release, even if count++ threw\n" +
                        "        }\n" +
                        "    }\n" +
                        "}\n\n" +
                        "// Non-blocking attempt:\n" +
                        "if (lock.tryLock()) {\n" +
                        "    try { doWork(); } finally { lock.unlock(); }\n" +
                        "} else {\n" +
                        "    System.out.println(\"Lock busy — skipping\");\n" +
                        "}\n\n" +
                        "// Timed attempt:\n" +
                        "if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {\n" +
                        "    try { doWork(); } finally { lock.unlock(); }\n" +
                        "} else {\n" +
                        "    System.out.println(\"Timed out waiting for lock\");\n" +
                        "}\n\n" +
                        "// Fair lock — threads acquire in arrival order (lower throughput):\n" +
                        "ReentrantLock fairLock = new ReentrantLock(true);"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Semaphore, Condition, Latches") {
                    BodyText(
                        "Semaphore — counting semaphore. acquire() takes a permit (blocks if zero), " +
                        "release() returns one. Use to limit concurrent access to a resource (e.g. " +
                        "a connection pool of size N).\n\n" +
                        "Condition — condition variable. Must be created from a ReentrantLock. " +
                        "await() releases the lock and waits; signal() wakes one waiter; signalAll() " +
                        "wakes all. The Java equivalent of C++ std::condition_variable.\n\n" +
                        "CountDownLatch — one-shot event. Initialized with count N. countDown() " +
                        "decrements; await() blocks until count reaches zero. Not resettable.\n\n" +
                        "CyclicBarrier — reusable barrier. N threads each call await(); all are " +
                        "released together when the last one arrives."
                    )
                    CodeBlock(
                        "// Semaphore — limit to 3 concurrent database connections:\n" +
                        "Semaphore pool = new Semaphore(3);\n" +
                        "pool.acquire();       // blocks if all 3 permits are taken\n" +
                        "try { useDatabase(); } finally { pool.release(); }\n\n" +
                        "// Condition variable — bounded queue producer/consumer:\n" +
                        "ReentrantLock lock    = new ReentrantLock();\n" +
                        "Condition      notFull = lock.newCondition();\n" +
                        "Condition      notEmpty = lock.newCondition();\n\n" +
                        "void produce(Object item) throws InterruptedException {\n" +
                        "    lock.lock();\n" +
                        "    try {\n" +
                        "        while (queue.size() == MAX) notFull.await();  // releases lock\n" +
                        "        queue.add(item);\n" +
                        "        notEmpty.signal();\n" +
                        "    } finally { lock.unlock(); }\n" +
                        "}\n\n" +
                        "// CountDownLatch — start-gun: all workers wait until ready signal:\n" +
                        "CountDownLatch ready = new CountDownLatch(1);\n" +
                        "for (int i = 0; i < 10; i++) {\n" +
                        "    new Thread(() -> {\n" +
                        "        ready.await();   // all 10 threads block here\n" +
                        "        doRace();\n" +
                        "    }).start();\n" +
                        "}\n" +
                        "ready.countDown();       // releases all 10 at once\n\n" +
                        "// CyclicBarrier — synchronize 4 threads at a checkpoint:\n" +
                        "CyclicBarrier barrier = new CyclicBarrier(4);\n" +
                        "// Each thread calls barrier.await() — all wait until all 4 have arrived."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Atomic Variables") {
                    BodyText(
                        "java.util.concurrent.atomic provides lock-free thread-safe operations using " +
                        "CPU-level Compare-And-Swap (CAS) instructions. No lock is acquired; " +
                        "contention causes a retry loop rather than a block.\n\n" +
                        "AtomicInteger, AtomicLong, AtomicBoolean — for primitives.\n" +
                        "AtomicReference<T> — for object references.\n" +
                        "LongAdder — optimized for high-throughput counters: internally striped " +
                        "across cells to reduce CAS contention. Better than AtomicLong when many " +
                        "threads increment simultaneously."
                    )
                    CodeBlock(
                        "import java.util.concurrent.atomic.*;\n\n" +
                        "AtomicInteger counter = new AtomicInteger(0);\n" +
                        "counter.incrementAndGet();      // atomic ++, returns new value\n" +
                        "counter.getAndIncrement();      // atomic ++, returns old value\n" +
                        "counter.addAndGet(5);           // atomic += 5, returns new value\n" +
                        "int val = counter.get();        // plain read (always consistent)\n\n" +
                        "// compareAndSet — CAS: only writes if current value matches expected:\n" +
                        "AtomicInteger ai = new AtomicInteger(10);\n" +
                        "boolean swapped = ai.compareAndSet(10, 20);  // true: 10→20\n" +
                        "boolean failed  = ai.compareAndSet(10, 30);  // false: value is 20 now\n\n" +
                        "// CAS retry loop (optimistic locking pattern):\n" +
                        "AtomicInteger shared = new AtomicInteger(0);\n" +
                        "int oldVal, newVal;\n" +
                        "do {\n" +
                        "    oldVal = shared.get();\n" +
                        "    newVal = transform(oldVal);\n" +
                        "} while (!shared.compareAndSet(oldVal, newVal));  // retry if raced\n\n" +
                        "// LongAdder — best for pure counting under high contention:\n" +
                        "LongAdder hits = new LongAdder();\n" +
                        "hits.increment();\n" +
                        "long total = hits.sum();        // read the accumulated total"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "ReadWriteLock") {
                    BodyText(
                        "ReentrantReadWriteLock allows multiple threads to read concurrently, but " +
                        "only one thread to write at a time. Any active reader blocks writers; any " +
                        "active writer blocks everyone.\n\n" +
                        "Use it when reads are frequent and writes are rare — it gives much better " +
                        "throughput than a plain mutex for read-heavy workloads.\n\n" +
                        "StampedLock (Java 8+) adds an optimistic read mode: read without acquiring " +
                        "the lock at all, then validate before using the result. If validation fails " +
                        "(a write happened), fall back to a regular read lock."
                    )
                    CodeBlock(
                        "import java.util.concurrent.locks.*;\n\n" +
                        "class Cache {\n" +
                        "    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();\n" +
                        "    private final Map<String, String> map = new HashMap<>();\n\n" +
                        "    public String get(String key) {\n" +
                        "        rwl.readLock().lock();         // many threads can hold this\n" +
                        "        try   { return map.get(key); }\n" +
                        "        finally { rwl.readLock().unlock(); }\n" +
                        "    }\n\n" +
                        "    public void put(String key, String val) {\n" +
                        "        rwl.writeLock().lock();        // exclusive — blocks all readers\n" +
                        "        try   { map.put(key, val); }\n" +
                        "        finally { rwl.writeLock().unlock(); }\n" +
                        "    }\n" +
                        "}\n\n" +
                        "// StampedLock — optimistic read (no lock acquired):\n" +
                        "StampedLock sl = new StampedLock();\n" +
                        "double x, y;\n\n" +
                        "long stamp = sl.tryOptimisticRead();  // no lock taken\n" +
                        "x = this.x;\n" +
                        "y = this.y;\n" +
                        "if (!sl.validate(stamp)) {            // check if a write happened\n" +
                        "    stamp = sl.readLock();            // fall back to real read lock\n" +
                        "    try { x = this.x; y = this.y; } finally { sl.unlockRead(stamp); }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Thread Pools — ExecutorService") {
                    BodyText(
                        "Creating a new Thread for every task is expensive. A thread pool maintains " +
                        "a set of pre-created threads and queues tasks to them. The Executors factory " +
                        "class provides the common configurations.\n\n" +
                        "Always shut down the executor when you are done; otherwise its threads keep " +
                        "the JVM alive. shutdown() stops accepting new tasks but lets queued tasks " +
                        "finish. shutdownNow() tries to interrupt running tasks immediately."
                    )
                    CodeBlock(
                        "import java.util.concurrent.*;\n\n" +
                        "// Fixed pool — exactly 4 threads, excess tasks queue:\n" +
                        "ExecutorService fixed = Executors.newFixedThreadPool(4);\n\n" +
                        "// Cached pool — grows as needed, idle threads expire after 60s:\n" +
                        "ExecutorService cached = Executors.newCachedThreadPool();\n\n" +
                        "// Single-thread executor — all tasks run serially:\n" +
                        "ExecutorService single = Executors.newSingleThreadExecutor();\n\n" +
                        "// Virtual thread per task (Java 21+) — unlimited, cheap:\n" +
                        "ExecutorService vExec = Executors.newVirtualThreadPerTaskExecutor();\n\n" +
                        "// Submit tasks:\n" +
                        "for (int i = 0; i < 10; i++) {\n" +
                        "    final int id = i;\n" +
                        "    fixed.submit(() -> System.out.println(\"task \" + id));\n" +
                        "}\n\n" +
                        "// Graceful shutdown — drain queued tasks, then stop:\n" +
                        "fixed.shutdown();\n" +
                        "fixed.awaitTermination(10, TimeUnit.SECONDS);\n\n" +
                        "// ScheduledExecutorService — run at fixed rate:\n" +
                        "ScheduledExecutorService sched = Executors.newScheduledThreadPool(2);\n" +
                        "sched.scheduleAtFixedRate(() -> poll(), 0, 1, TimeUnit.SECONDS);\n\n" +
                        "// ForkJoinPool.commonPool() — used by parallel streams:\n" +
                        "List.of(1,2,3).parallelStream().forEach(System.out::println);"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Thread ID") {
                    BodyText(
                        "Every Thread has a JVM-assigned ID, readable via threadId() (Java 19+) or " +
                        "getId() (deprecated in Java 19). This is a sequential long, unique within " +
                        "the JVM's lifetime.\n\n" +
                        "For platform threads: this is NOT the OS thread ID. The OS assigns its own " +
                        "native thread ID. You cannot read the OS thread ID from Java without JNI or " +
                        "JVM tooling (jstack, JVM TI agent). The Java ID and the OS ID are unrelated " +
                        "numbers.\n\n" +
                        "For virtual threads: the threadId() is still a JVM-assigned long, but " +
                        "virtual threads have NO corresponding OS thread ID at all — they are not OS " +
                        "threads. The carrier platform thread that currently runs the virtual thread " +
                        "does have an OS thread ID, but the virtual thread can be moved to a " +
                        "different carrier between blocking points."
                    )
                    CodeBlock(
                        "Thread t = Thread.ofPlatform().name(\"worker\").start(() -> {\n" +
                        "    Thread me = Thread.currentThread();\n" +
                        "    System.out.println(\"name:      \" + me.getName());\n" +
                        "    System.out.println(\"Java ID:   \" + me.threadId());\n" +
                        "    System.out.println(\"isVirtual: \" + me.isVirtual());\n" +
                        "    // OS thread ID: not directly available in Java.\n" +
                        "    // Use jstack, JVM TI, or ProcessHandle from external tooling.\n" +
                        "});\n\n" +
                        "Thread vt = Thread.ofVirtual().name(\"vworker\").start(() -> {\n" +
                        "    Thread me = Thread.currentThread();\n" +
                        "    System.out.println(\"name:      \" + me.getName());\n" +
                        "    System.out.println(\"Java ID:   \" + me.threadId());  // JVM long, not OS\n" +
                        "    System.out.println(\"isVirtual: \" + me.isVirtual()); // true\n" +
                        "    // No OS thread ID — virtual threads are not OS threads.\n" +
                        "    // The carrier thread ID changes whenever this thread is parked/resumed.\n" +
                        "});\n\n" +
                        "// Read ID from outside:\n" +
                        "System.out.println(t.getName());    // \"worker\"\n" +
                        "System.out.println(t.threadId());   // e.g. 21 (JVM-assigned)\n" +
                        "System.out.println(t.getState());   // RUNNABLE, WAITING, TERMINATED, ..."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Thread-Local Storage — ThreadLocal and ScopedValue") {
                    BodyText(
                        "ThreadLocal<T> gives each thread its own independent value — the classic TLS " +
                        "mechanism. Reads and writes are per-thread; no synchronization needed.\n\n" +
                        "In thread pools always call remove() when the task finishes — pooled threads " +
                        "are reused, and a forgotten value will 'bleed' into the next task.\n\n" +
                        "For virtual threads, ThreadLocal technically works, but is discouraged: " +
                        "millions of virtual threads each holding a ThreadLocal value multiplies " +
                        "memory usage significantly.\n\n" +
                        "ScopedValue<T> (Java 21+ preview, stable in Java 23+) is the modern " +
                        "replacement for virtual threads. It is immutable within a scope, requires no " +
                        "remove(), and is inherited automatically by child scopes."
                    )
                    CodeBlock(
                        "// ThreadLocal — per-thread value:\n" +
                        "ThreadLocal<SimpleDateFormat> dateFormat = ThreadLocal.withInitial(\n" +
                        "    () -> new SimpleDateFormat(\"yyyy-MM-dd\")\n" +
                        ");\n\n" +
                        "// Each thread gets its own SimpleDateFormat (not thread-safe otherwise):\n" +
                        "String s = dateFormat.get().format(new Date());\n\n" +
                        "// In a thread pool — ALWAYS clean up:\n" +
                        "ExecutorService exec = Executors.newFixedThreadPool(4);\n" +
                        "exec.submit(() -> {\n" +
                        "    dateFormat.set(new SimpleDateFormat(\"dd/MM/yyyy\"));\n" +
                        "    try {\n" +
                        "        doWork();\n" +
                        "    } finally {\n" +
                        "        dateFormat.remove();   // prevent bleed-over to next task\n" +
                        "    }\n" +
                        "});\n\n" +
                        "// InheritableThreadLocal — child inherits parent's value at start:\n" +
                        "InheritableThreadLocal<String> userId = new InheritableThreadLocal<>();\n" +
                        "userId.set(\"alice\");\n" +
                        "new Thread(() -> System.out.println(userId.get())).start(); // prints alice\n\n" +
                        "// ScopedValue (Java 21+) — preferred for virtual threads:\n" +
                        "static final ScopedValue<String> USER = ScopedValue.newInstance();\n\n" +
                        "ScopedValue.where(USER, \"alice\").run(() -> {\n" +
                        "    System.out.println(USER.get()); // alice — bound for this scope only\n" +
                        "    // Immutable: cannot call USER.set() — no setter exists.\n" +
                        "    // Automatically unbound when the run() lambda returns.\n" +
                        "});\n" +
                        "// USER.get() here would throw NoSuchElementException — out of scope."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Sleeping") {
                    BodyText(
                        "Thread.sleep(ms) pauses the current thread for at least the given number of " +
                        "milliseconds. It throws InterruptedException (a checked exception) — you " +
                        "must handle it.\n\n" +
                        "If a thread is sleeping and another thread calls interrupt() on it, the sleep " +
                        "throws InterruptedException immediately. The interrupted flag is cleared by the " +
                        "throw. In a loop, always re-set the flag with Thread.currentThread().interrupt() " +
                        "if you catch it, so the caller knows the thread was interrupted."
                    )
                    CodeBlock(
                        "// Basic sleep:\n" +
                        "Thread.sleep(1000);             // sleep ~1 second; throws InterruptedException\n\n" +
                        "// TimeUnit variant (same behavior, more readable):\n" +
                        "TimeUnit.SECONDS.sleep(1);\n" +
                        "TimeUnit.MILLISECONDS.sleep(250);\n\n" +
                        "// Must handle InterruptedException (checked):\n" +
                        "try {\n" +
                        "    Thread.sleep(500);\n" +
                        "} catch (InterruptedException e) {\n" +
                        "    // Option 1: re-set the interrupted flag and continue:\n" +
                        "    Thread.currentThread().interrupt();\n" +
                        "    // Option 2: stop the loop / propagate:\n" +
                        "    return;\n" +
                        "}\n\n" +
                        "// Loop pattern — check interrupted flag each iteration:\n" +
                        "while (!Thread.currentThread().isInterrupted()) {\n" +
                        "    doWork();\n" +
                        "    try {\n" +
                        "        Thread.sleep(100);\n" +
                        "    } catch (InterruptedException e) {\n" +
                        "        Thread.currentThread().interrupt();  // re-set flag\n" +
                        "        break;                               // exit cleanly\n" +
                        "    }\n" +
                        "}\n\n" +
                        "// sleep(0) yields the CPU voluntarily — rarely needed:\n" +
                        "Thread.sleep(0);"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
