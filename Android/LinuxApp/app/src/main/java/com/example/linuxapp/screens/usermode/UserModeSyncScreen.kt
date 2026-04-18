package com.example.linuxapp.screens.usermode

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.linuxapp.screens.kernel.BodyText
import com.example.linuxapp.screens.kernel.CodeBlock
import com.example.linuxapp.screens.kernel.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserModeSyncScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Synchronization",
                        color = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontSize = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item {
                SectionCard(title = "Overview") {
                    BodyText("POSIX provides several synchronization primitives for threads and processes. All are available via the pthreads library.")
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """Primitive       Scope              Sleeping?
Mutex           threads (+ procs) Yes
Semaphore       threads + procs   Yes
Condition Var   threads           Yes (with mutex)
RW Lock         threads           Yes
Spinlock        threads           No (busy-wait)"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Link all pthreads programs with: gcc prog.c -lpthread")
                }
            }
            item {
                SectionCard(title = "Mutex (pthread_mutex_t)") {
                    BodyText("A mutex ensures only one thread executes a critical section at a time. Other threads block until the mutex is unlocked.")
                    CodeBlock(
                        """#include <pthread.h>

/* Static initialization: */
pthread_mutex_t mtx = PTHREAD_MUTEX_INITIALIZER;

/* Dynamic initialization (needed for non-default attrs): */
pthread_mutex_t mtx;
pthread_mutex_init(&mtx, NULL);

/* Lock — blocks until available: */
pthread_mutex_lock(&mtx);
/* ... critical section ... */
pthread_mutex_unlock(&mtx);

/* Non-blocking attempt: */
int rc = pthread_mutex_trylock(&mtx);
if (rc == 0) {
    /* acquired */
    pthread_mutex_unlock(&mtx);
} else {
    /* EBUSY — another thread holds it */
}

/* Lock with timeout: */
struct timespec ts;
clock_gettime(CLOCK_REALTIME, &ts);
ts.tv_sec += 2;  /* 2 second timeout */
rc = pthread_mutex_timedlock(&mtx, &ts);
/* rc == ETIMEDOUT if expired */

/* Destroy when done: */
pthread_mutex_destroy(&mtx);"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("A mutex must be unlocked by the same thread that locked it. For counting semantics (release from a different thread) use a semaphore.")
                }
            }
            item {
                SectionCard(title = "Futex — How pthread_mutex Works Internally") {
                    BodyText("futex stands for Fast Userspace muTEX. It is a Linux syscall that enables user-space locking primitives to avoid the kernel in the common (uncontended) case.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The key insight: most of the time a lock is NOT contended. futex exploits this by splitting locking into a fast path and a slow path:")
                    Spacer(modifier = Modifier.height(4.dp))
                    CodeBlock(
                        """Fast path (no contention):
  Thread does atomic CAS on an integer in userspace.
  If it succeeds → lock acquired. No syscall at all.

Slow path (lock is already taken):
  Atomic CAS fails → thread calls futex(FUTEX_WAIT).
  Kernel checks the value again (atomically) and
  puts the thread to sleep if still contended.

Unlock path (waiters exist):
  Owner calls futex(FUTEX_WAKE) → kernel wakes one
  (or all) sleeping threads."""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("The futex word is a plain int shared between threads. Its value encodes lock state — e.g., 0 = unlocked, 1 = locked (no waiters), 2 = locked (waiters sleeping in kernel).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("When there is no contention, the fast path avoids the syscall entirely. A syscall is expensive: the CPU must switch from user mode to kernel mode and back — saving/restoring registers, switching stacks, and going through the kernel entry point. Eliminating that overhead in the common case is exactly what makes futex-based locking fast.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Does pthread_mutex use futex? Yes — always. glibc's NPTL implementation uses futex as the foundation for every pthread_mutex type:")
                    Spacer(modifier = Modifier.height(4.dp))
                    CodeBlock(
                        """PTHREAD_MUTEX_DEFAULT/NORMAL  — futex, fast path
PTHREAD_MUTEX_ERRORCHECK      — futex + ownership check
PTHREAD_MUTEX_RECURSIVE       — futex + lock count
Robust mutex variants         — futex + robust-list
Priority-inheritance (PI)     — futex with kernel PI

PTHREAD_MUTEX_ADAPTIVE_NP     — GNU extension:
  spins ~100x first, then falls back to futex.
  (glibc.pthread.mutex_spin_count tunable)"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("There is no pthread_mutex type in modern Linux/glibc that issues a syscall on every lock — the futex fast path is universal. The old \"syscall every time\" approach no longer exists.")
                }
            }
            item {
                SectionCard(title = "Semaphore (sem_t)") {
                    BodyText("A semaphore is a generalized counter. Multiple threads can \"hold\" the semaphore simultaneously up to the initial count. Unlike a mutex, it can be posted (released) from a different thread — or even a different process.")
                    CodeBlock(
                        """#include <semaphore.h>

/* Unnamed semaphore (for threads in same process): */
sem_t sem;
sem_init(&sem,
         0,    /* 0 = shared between threads only */
         1);   /* initial value (1 = binary semaphore) */

/* Wait (P operation) — decrements; blocks if value is 0: */
sem_wait(&sem);

/* Post (V operation) — increments; unblocks one waiter: */
sem_post(&sem);

/* Non-blocking wait: */
int rc = sem_trywait(&sem);  /* EAGAIN if value == 0 */

/* Wait with timeout: */
struct timespec ts;
clock_gettime(CLOCK_REALTIME, &ts);
ts.tv_sec += 5;
sem_timedwait(&sem, &ts);    /* ETIMEDOUT on expiry */

/* Read current value: */
int val;
sem_getvalue(&sem, &val);

sem_destroy(&sem);"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Named semaphores — shared across unrelated processes:")
                    CodeBlock(
                        """/* Process A — create or open: */
sem_t *s = sem_open("/my_semaphore",
                    O_CREAT,   /* create if not exists */
                    0644,      /* permissions */
                    1);        /* initial value */

sem_wait(s);
/* ... critical section ... */
sem_post(s);

sem_close(s);          /* close this process's handle */
sem_unlink("/my_semaphore");  /* delete from the system */"""
                    )
                }
            }
            item {
                SectionCard(title = "Condition Variable (pthread_cond_t)") {
                    BodyText("A condition variable lets a thread sleep until some condition becomes true. It must always be paired with a mutex.")
                    CodeBlock(
                        """#include <pthread.h>

pthread_mutex_t mtx  = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t  cond = PTHREAD_COND_INITIALIZER;
int ready = 0;

/* --- Consumer thread: waits for data --- */
pthread_mutex_lock(&mtx);
while (!ready)                        /* always loop — spurious wakeups */
    pthread_cond_wait(&cond, &mtx);   /* atomically: unlock + sleep */
/* condition is true here; mutex is re-locked */
consume_data();
pthread_mutex_unlock(&mtx);

/* --- Producer thread: signals the consumer --- */
pthread_mutex_lock(&mtx);
produce_data();
ready = 1;
pthread_cond_signal(&cond);    /* wake ONE waiting thread */
/* or: pthread_cond_broadcast(&cond);  wake ALL waiting threads */
pthread_mutex_unlock(&mtx);"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Always check the condition in a while loop, not an if — POSIX allows spurious wakeups (a thread can wake up without a signal).")
                }
            }
            item {
                SectionCard(title = "Read-Write Lock (pthread_rwlock_t)") {
                    BodyText("Allows many threads to read simultaneously, but gives writers exclusive access. Ideal for read-heavy data structures.")
                    CodeBlock(
                        """#include <pthread.h>

pthread_rwlock_t rwlock = PTHREAD_RWLOCK_INITIALIZER;

/* Multiple threads can hold rdlock at the same time: */
pthread_rwlock_rdlock(&rwlock);
/* ... read shared data ... */
pthread_rwlock_unlock(&rwlock);

/* Writer gets exclusive access (waits for all readers): */
pthread_rwlock_wrlock(&rwlock);
/* ... modify shared data ... */
pthread_rwlock_unlock(&rwlock);

/* Non-blocking variants: */
pthread_rwlock_tryrdlock(&rwlock);   /* EBUSY if write-locked */
pthread_rwlock_trywrlock(&rwlock);   /* EBUSY if any lock held */

pthread_rwlock_destroy(&rwlock);"""
                    )
                }
            }
            item {
                SectionCard(title = "Spinlock (pthread_spinlock_t)") {
                    BodyText("A user-space spinlock busy-waits without sleeping. Useful only for extremely short critical sections where the overhead of a mutex's syscall would dominate. Rarely the right choice — a mutex is almost always better.")
                    CodeBlock(
                        """#include <pthread.h>

pthread_spinlock_t spinlock;
pthread_spin_init(&spinlock, PTHREAD_PROCESS_PRIVATE);
/* PTHREAD_PROCESS_PRIVATE — only within this process
   PTHREAD_PROCESS_SHARED  — shared between processes */

pthread_spin_lock(&spinlock);
/* ... very short critical section ... */
pthread_spin_unlock(&spinlock);

pthread_spin_trylock(&spinlock);  /* non-blocking */
pthread_spin_destroy(&spinlock);"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Warning: if a thread holding a spinlock is preempted, all other threads waste CPU time spinning. Use mutexes unless you have measured that this is a bottleneck.")
                }
            }
            item {
                SectionCard(title = "Atomic Operations") {
                    BodyText("For simple counters and flags shared between threads, C11 atomics can replace a mutex entirely:")
                    CodeBlock(
                        """#include <stdatomic.h>

/* Atomic counter — no mutex needed: */
atomic_int counter = ATOMIC_VAR_INIT(0);

/* Thread-safe increment: */
atomic_fetch_add(&counter, 1);

/* Thread-safe read: */
int val = atomic_load(&counter);

/* Thread-safe write: */
atomic_store(&counter, 0);

/* Compare-and-swap: */
int expected = 5;
atomic_compare_exchange_strong(&counter, &expected, 10);
/* Sets counter to 10 only if it was 5; writes actual
   value into expected if the swap fails. */"""
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
