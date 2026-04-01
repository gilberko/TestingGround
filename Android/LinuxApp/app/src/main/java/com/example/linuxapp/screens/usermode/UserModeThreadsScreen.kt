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
fun UserModeThreadsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Threads",
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
                SectionCard(title = "What Is a Thread?") {
                    BodyText("A thread is a unit of execution within a process. All threads in a process share the same address space, file descriptors, and global variables — but each has its own stack and CPU register state.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("POSIX threads (pthreads) is the standard threading API on Linux. Compile with: gcc prog.c -lpthread")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Under the hood, pthread_create() calls the clone() system call with flags that share the address space with the parent — the same mechanism the kernel uses internally for threads.")
                }
            }
            item {
                SectionCard(title = "Creating a Thread") {
                    CodeBlock(
                        """#include <pthread.h>
#include <stdio.h>

/* Thread function — must have this signature: */
void *worker(void *arg)
{
    int id = *(int *)arg;
    printf("Thread %d running\n", id);
    /* Return value is the thread's exit status */
    return (void *)(long)id;
}

int main(void)
{
    pthread_t tid;
    int arg = 42;

    /* pthread_create(thread, attr, start_fn, arg) */
    int rc = pthread_create(&tid,   /* output: thread handle */
                            NULL,   /* attributes (NULL = default) */
                            worker, /* function to run */
                            &arg);  /* argument passed to worker */
    if (rc != 0) {
        perror("pthread_create");
        return 1;
    }
    printf("Thread created with id %lu\n", tid);
    /* ... main continues concurrently with worker ... */
    return 0;
}"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Thread attributes (pthread_attr_t) let you customize the new thread:")
                    CodeBlock(
                        """pthread_attr_t attr;
pthread_attr_init(&attr);

/* Set stack size (default is usually 8 MB): */
pthread_attr_setstacksize(&attr, 2 * 1024 * 1024);  /* 2 MB */

/* Set detach state (see Detached Threads section): */
pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_DETACHED);

pthread_create(&tid, &attr, worker, &arg);
pthread_attr_destroy(&attr);  /* free attr after create */"""
                    )
                }
            }
            item {
                SectionCard(title = "Waiting for a Thread (pthread_join)") {
                    BodyText("pthread_join() blocks the calling thread until the target thread finishes. It also retrieves the thread's return value and releases its resources.")
                    CodeBlock(
                        """void *retval;
int rc = pthread_join(tid, &retval);
/* Blocks until thread 'tid' exits.
   retval receives whatever the thread returned
   (or the value passed to pthread_exit). */

if (rc != 0) {
    perror("pthread_join");
} else {
    long result = (long)retval;
    printf("Thread returned: %ld\n", result);
}"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Joining multiple threads — join each one in sequence:")
                    CodeBlock(
                        """#define NUM_THREADS 4
pthread_t tids[NUM_THREADS];
int args[NUM_THREADS];

for (int i = 0; i < NUM_THREADS; i++) {
    args[i] = i;
    pthread_create(&tids[i], NULL, worker, &args[i]);
}

/* Wait for all threads to finish: */
for (int i = 0; i < NUM_THREADS; i++) {
    pthread_join(tids[i], NULL);  /* NULL = discard return value */
}
printf("All threads done\n");"""
                    )
                }
            }
            item {
                SectionCard(title = "Zombie Threads") {
                    BodyText("A zombie thread (also called a joinable thread that has exited but not been joined) is a thread that has finished executing but whose resources have not yet been reclaimed.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("This is directly analogous to a zombie process:")
                    CodeBlock(
                        """Zombie process: a process that has exited but whose parent
  has not yet called wait() to collect its exit status.
  Appears as 'Z' in ps output.

Zombie thread: a joinable thread that has exited but no
  thread has called pthread_join() on it yet.
  The thread's stack, thread-local storage, and internal
  bookkeeping remain allocated until joined."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("What resources are held by a zombie thread:")
                    CodeBlock(
                        """- Stack memory (typically 8 MB by default)
- Thread descriptor (pthread internal state)
- TLS (thread-local storage) blocks
- Signal mask and other per-thread state

If you create many threads without joining them, you can
exhaust virtual address space or hit the system thread limit
even though the threads are no longer running."""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Rule: every joinable thread must be either joined or detached before the program exits. Forgetting to join is a resource leak.")
                }
            }
            item {
                SectionCard(title = "Detached Threads") {
                    BodyText("A detached thread releases its resources automatically when it exits — you do not (and cannot) call pthread_join() on it. Use this for fire-and-forget threads where you don't need the return value.")
                    CodeBlock(
                        """/* Method 1: detach at creation via attribute */
pthread_attr_t attr;
pthread_attr_init(&attr);
pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_DETACHED);
pthread_create(&tid, &attr, worker, NULL);
pthread_attr_destroy(&attr);
/* tid is now detached — do not call pthread_join on it */

/* Method 2: detach an already-created thread */
pthread_t tid;
pthread_create(&tid, NULL, worker, NULL);
pthread_detach(tid);
/* After this, the thread manages its own cleanup */"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Warning: if main() exits (or calls exit()) while detached threads are still running, they are killed immediately. Use pthread_exit() instead of return in main() to let detached threads finish.")
                }
            }
            item {
                SectionCard(title = "Proper Thread Cleanup") {
                    BodyText("A thread exits when its start function returns, or when it calls pthread_exit() explicitly:")
                    CodeBlock(
                        """void *worker(void *arg)
{
    /* Normal exit — return value is the thread's status: */
    return (void *)0;

    /* Or explicit exit from anywhere in the call stack: */
    pthread_exit((void *)42);
}"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Cleanup handlers — run automatically when a thread exits or is cancelled:")
                    CodeBlock(
                        """void cleanup(void *arg)
{
    printf("Cleanup: releasing %s\n", (char *)arg);
    /* free resources, close fds, unlock mutexes, etc. */
}

void *worker(void *arg)
{
    char *buf = malloc(256);

    /* Register cleanup handler (LIFO stack): */
    pthread_cleanup_push(cleanup, "buffer");

    /* ... do work ... */
    /* If we return early or pthread_exit is called,
       cleanup() is invoked automatically. */

    /* Pop and optionally execute (1 = execute, 0 = just pop): */
    pthread_cleanup_pop(1);
    return NULL;
}"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Complete cleanup pattern summary:")
                    CodeBlock(
                        """Joinable thread  → must call pthread_join()
                   → returns resources + exit status

Detached thread  → call pthread_detach() or use attribute
                   → resources freed automatically on exit
                   → cannot be joined

pthread_exit()   → exit current thread (not the process)
                   → cleanup handlers run

pthread_cancel() → request another thread to cancel
                   → thread exits at next cancellation point
                   → cleanup handlers still run"""
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
