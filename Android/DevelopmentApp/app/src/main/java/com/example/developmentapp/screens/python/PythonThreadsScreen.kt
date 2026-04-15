package com.example.developmentapp.screens.python

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
fun PythonThreadsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Python — Threads",
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

            // ── Creating & Starting Threads ──────────────────────────────
            item {
                SectionCard(title = "Creating & Starting Threads") {
                    BodyText("Use threading.Thread to run a function concurrently. Call .start() to launch it, and .join() to wait for it to finish.")
                    CodeBlock("""
import threading

def worker(name):
    print(f"Thread {name} starting")
    # ... do work ...
    print(f"Thread {name} done")

t1 = threading.Thread(target=worker, args=("A",))
t2 = threading.Thread(target=worker, args=("B",))

t1.start()
t2.start()

t1.join()   # wait for t1 to finish
t2.join()   # wait for t2 to finish
print("All threads done")
                    """.trimIndent())
                    BodyText("Daemon threads: if daemon=True the thread is killed automatically when the main thread exits. Non-daemon threads keep the process alive until they complete.")
                    CodeBlock("""
t = threading.Thread(target=worker, args=("bg",), daemon=True)
t.start()
# process will exit even if t is still running
                    """.trimIndent())
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Waiting for Threads ───────────────────────────────────────
            item {
                SectionCard(title = "Waiting for Threads") {
                    BodyText(".join() blocks until the thread finishes. Pass a timeout (seconds) to avoid blocking forever; check .is_alive() to see if it timed out.")
                    CodeBlock("""
t = threading.Thread(target=long_task)
t.start()

t.join(timeout=5.0)    # wait at most 5 seconds

if t.is_alive():
    print("Thread still running after timeout")
else:
    print("Thread finished in time")
                    """.trimIndent())
                    BodyText("You can join multiple threads sequentially. The order of join() calls does not affect which thread finishes first — it only controls which one you wait on next.")
                    CodeBlock("""
threads = [threading.Thread(target=worker, args=(i,))
           for i in range(4)]

for t in threads:
    t.start()

for t in threads:
    t.join()   # wait for each in turn
                    """.trimIndent())
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Zombie Threads? ───────────────────────────────────────────
            item {
                SectionCard(title = "Zombie Threads?") {
                    BodyText("In Linux, a zombie process is one that has exited but whose exit status has not yet been collected by the parent via wait(). Python threads have no equivalent.")
                    BodyText("When a Python thread finishes, the threading module cleans it up automatically. There is no 'zombie' state — the thread object simply becomes inactive (.is_alive() returns False) and its resources are reclaimed by the garbage collector once no references remain.")
                    BodyText("The only thing to watch for:")
                    BodyText("  • Non-daemon threads keep the process alive. If you forget to join() them and the main thread ends, Python will wait for all non-daemon threads before exiting — it will not exit with dangling threads.")
                    BodyText("  • Daemon threads are killed abruptly (no cleanup) when the main thread exits. Do not use daemon threads for tasks that must finish cleanly.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Lock (Mutex) ──────────────────────────────────────────────
            item {
                SectionCard(title = "Lock (Mutex)") {
                    BodyText("threading.Lock() is Python's mutex. Only one thread can hold it at a time. Use the 'with' context manager to ensure the lock is always released.")
                    CodeBlock("""
import threading

counter = 0
lock = threading.Lock()

def increment():
    global counter
    for _ in range(100_000):
        with lock:           # acquire → body → release
            counter += 1

threads = [threading.Thread(target=increment) for _ in range(4)]
for t in threads: t.start()
for t in threads: t.join()
print(counter)  # always 400000
                    """.trimIndent())
                    BodyText("Manual acquire/release (less preferred — forgetting release causes deadlock):")
                    CodeBlock("""
lock.acquire()
try:
    counter += 1
finally:
    lock.release()
                    """.trimIndent())
                    BodyText("RLock (re-entrant lock): the same thread can acquire it multiple times without deadlocking. Useful when a locked function calls another function that also needs the same lock.")
                    CodeBlock("""
rlock = threading.RLock()

def outer():
    with rlock:
        inner()   # safe — same thread can re-acquire

def inner():
    with rlock:
        print("inside inner")
                    """.trimIndent())
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Event ─────────────────────────────────────────────────────
            item {
                SectionCard(title = "Event") {
                    BodyText("threading.Event is a simple flag one thread can set to signal another. The waiting thread blocks on .wait() until the flag is set.")
                    BodyText("Key methods: .set() — raise the flag | .clear() — lower it | .wait(timeout) — block until set | .is_set() — check without blocking")
                    CodeBlock("""
import threading, time

ready = threading.Event()

def producer():
    time.sleep(2)
    print("Data ready, signalling...")
    ready.set()

def consumer():
    print("Waiting for data...")
    ready.wait()       # blocks here
    print("Got the signal, processing!")

t1 = threading.Thread(target=producer)
t2 = threading.Thread(target=consumer)
t1.start(); t2.start()
t1.join();  t2.join()
                    """.trimIndent())
                    BodyText("Events are one-shot by default. Call .clear() to reuse the event for the next cycle.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Condition Variable ────────────────────────────────────────
            item {
                SectionCard(title = "Condition Variable") {
                    BodyText("Yes, Python has threading.Condition. It combines a Lock with the ability to wait for a specific condition, then be notified when it may have changed. Classic use: producer/consumer queue.")
                    BodyText("Key methods: .wait() — release lock and block | .notify() — wake one waiter | .notify_all() — wake all waiters")
                    CodeBlock("""
import threading, collections

queue  = collections.deque()
cond   = threading.Condition()

def producer():
    for i in range(5):
        with cond:
            queue.append(i)
            print(f"Produced {i}")
            cond.notify()        # wake consumer

def consumer():
    consumed = 0
    while consumed < 5:
        with cond:
            while not queue:    # re-check after wakeup
                cond.wait()     # releases lock, waits
            item = queue.popleft()
        print(f"Consumed {item}")
        consumed += 1

t1 = threading.Thread(target=producer)
t2 = threading.Thread(target=consumer)
t1.start(); t2.start()
t1.join();  t2.join()
                    """.trimIndent())
                    BodyText("Always re-check the condition in a while loop after wait() returns — another thread may have consumed the item before you re-acquire the lock (spurious wakeup protection).")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Semaphore ─────────────────────────────────────────────────
            item {
                SectionCard(title = "Semaphore") {
                    BodyText("threading.Semaphore(n) allows up to n threads to hold it simultaneously. Useful for limiting concurrent access to a resource (e.g., max 3 threads hitting a database at once).")
                    CodeBlock("""
import threading, time

sem = threading.Semaphore(3)   # at most 3 at a time

def worker(i):
    with sem:
        print(f"Worker {i} running")
        time.sleep(1)

threads = [threading.Thread(target=worker, args=(i,))
           for i in range(8)]
for t in threads: t.start()
for t in threads: t.join()
                    """.trimIndent())
                    BodyText("BoundedSemaphore raises ValueError if .release() is called more times than .acquire(), preventing bugs where the counter accidentally exceeds its initial value.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── GIL Note ──────────────────────────────────────────────────
            item {
                SectionCard(title = "The GIL") {
                    BodyText("CPython has a Global Interpreter Lock (GIL) — a mutex that ensures only one thread executes Python bytecode at a time. This means:")
                    BodyText("  • Threads are great for I/O-bound work (network, disk, sleep). While one thread waits on I/O, the GIL is released and others can run.")
                    BodyText("  • Threads do NOT speed up CPU-bound work (number crunching). All threads compete for the single GIL slot.")
                    BodyText("For CPU parallelism, use multiprocessing (separate processes, no shared GIL) or libraries that release the GIL internally (NumPy, Cython).")
                    CodeBlock("""
# I/O-bound: threads help (GIL released during I/O)
import threading, urllib.request

def fetch(url):
    urllib.request.urlopen(url).read()

# CPU-bound: use multiprocessing instead
from multiprocessing import Process

def crunch(n):
    sum(range(n))

p = Process(target=crunch, args=(10_000_000,))
p.start(); p.join()
                    """.trimIndent())
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
