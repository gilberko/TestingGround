package com.example.linuxapp.screens.usermode

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
fun UserModeIpcScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Inter Process Communication",
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
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = innerPadding.calculateTopPadding() + 8.dp,
                bottom = 8.dp
            )
        ) {

            item {
                SectionCard(title = "Overview") {
                    BodyText(
                        "Linux provides two primary IPC mechanisms covered here:\n" +
                        "  • POSIX shared memory — map a named region into multiple processes\n" +
                        "  • UNIX domain sockets — bidirectional message passing over a local socket\n\n" +
                        "For synchronization across processes, Linux offers POSIX named semaphores and " +
                        "pthread primitives with PTHREAD_PROCESS_SHARED — the closest equivalents to " +
                        "Windows named kernel objects (CreateMutex, CreateEvent, CreateSemaphore)."
                    )
                }
            }

            item {
                SectionCard(title = "Shared Memory (POSIX)") {
                    BodyText(
                        "shm_open() creates a named shared memory object (backed by tmpfs under /dev/shm). " +
                        "Both processes mmap the same object to get a shared pointer. Link with -lrt."
                    )
                    CodeBlock(
                        "#include <sys/mman.h>\n" +
                        "#include <fcntl.h>\n" +
                        "\n" +
                        "/* Process A — creator */\n" +
                        "int fd = shm_open(\"/my_shm\", O_CREAT | O_RDWR, 0666);\n" +
                        "ftruncate(fd, sizeof(MyData));\n" +
                        "MyData *p = mmap(NULL, sizeof(MyData),\n" +
                        "    PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);\n" +
                        "close(fd);   /* fd no longer needed after mmap */\n" +
                        "\n" +
                        "/* Process B — consumer */\n" +
                        "int fd = shm_open(\"/my_shm\", O_RDWR, 0666);\n" +
                        "MyData *p = mmap(NULL, sizeof(MyData),\n" +
                        "    PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);\n" +
                        "close(fd);\n" +
                        "\n" +
                        "/* Cleanup (one process) */\n" +
                        "munmap(p, sizeof(MyData));\n" +
                        "shm_unlink(\"/my_shm\"); /* removes name; freed when all unmap */"
                    )
                    BodyText(
                        "Shared memory alone has no synchronization — concurrent access requires a " +
                        "PTHREAD_PROCESS_SHARED mutex placed inside the shared region (see below)."
                    )
                }
            }

            item {
                SectionCard(title = "UNIX Domain Sockets") {
                    BodyText(
                        "AF_UNIX sockets provide full-duplex message passing within the same host. " +
                        "Use SOCK_STREAM for reliable ordered bytes (like TCP) or SOCK_DGRAM for datagrams. " +
                        "A unique capability: you can pass open file descriptors between processes via SCM_RIGHTS."
                    )
                    CodeBlock(
                        "#include <sys/socket.h>\n" +
                        "#include <sys/un.h>\n" +
                        "\n" +
                        "/* Server */\n" +
                        "int srv = socket(AF_UNIX, SOCK_STREAM, 0);\n" +
                        "struct sockaddr_un addr = { .sun_family = AF_UNIX };\n" +
                        "strncpy(addr.sun_path, \"/tmp/my.sock\", sizeof(addr.sun_path));\n" +
                        "unlink(\"/tmp/my.sock\");  /* remove stale socket */\n" +
                        "bind(srv, (struct sockaddr *)&addr, sizeof(addr));\n" +
                        "listen(srv, 5);\n" +
                        "int conn = accept(srv, NULL, NULL);\n" +
                        "char buf[256];\n" +
                        "ssize_t n = read(conn, buf, sizeof(buf));\n" +
                        "\n" +
                        "/* Client */\n" +
                        "int sock = socket(AF_UNIX, SOCK_STREAM, 0);\n" +
                        "connect(sock, (struct sockaddr *)&addr, sizeof(addr));\n" +
                        "write(sock, \"hello\", 5);"
                    )
                    BodyText(
                        "Abstract namespace (Linux-only): avoids filesystem path; automatically cleaned up " +
                        "when all fds are closed. Set sun_path[0] = '\\0' and write the name from sun_path[1]:"
                    )
                    CodeBlock(
                        "struct sockaddr_un addr = { .sun_family = AF_UNIX };\n" +
                        "addr.sun_path[0] = '\\0';\n" +
                        "strncpy(&addr.sun_path[1], \"my_abstract\", sizeof(addr.sun_path) - 2);"
                    )
                }
            }

            item {
                SectionCard(title = "Cross-Process Mutex (≈ Windows CreateMutex)") {
                    BodyText(
                        "Place a pthread_mutex_t inside shared memory and initialize it with " +
                        "PTHREAD_PROCESS_SHARED. Both processes can then lock/unlock it normally."
                    )
                    CodeBlock(
                        "/* Layout in shared memory: */\n" +
                        "typedef struct {\n" +
                        "    pthread_mutex_t mutex;\n" +
                        "    /* shared data fields */\n" +
                        "} SharedRegion;\n" +
                        "\n" +
                        "/* Creator process — call once: */\n" +
                        "pthread_mutexattr_t attr;\n" +
                        "pthread_mutexattr_init(&attr);\n" +
                        "pthread_mutexattr_setpshared(&attr, PTHREAD_PROCESS_SHARED);\n" +
                        "pthread_mutex_init(&shared->mutex, &attr);\n" +
                        "pthread_mutexattr_destroy(&attr);\n" +
                        "\n" +
                        "/* Both processes — use as normal: */\n" +
                        "pthread_mutex_lock(&shared->mutex);\n" +
                        "/* access shared data */\n" +
                        "pthread_mutex_unlock(&shared->mutex);\n" +
                        "\n" +
                        "/* Cleanup: */\n" +
                        "pthread_mutex_destroy(&shared->mutex);"
                    )
                }
            }

            item {
                SectionCard(title = "Cross-Process Event (≈ Windows CreateEvent)") {
                    BodyText(
                        "Linux has no direct equivalent to a Windows Event object, but the same pattern is " +
                        "achieved with a PTHREAD_PROCESS_SHARED condvar + mutex + flag in shared memory."
                    )
                    CodeBlock(
                        "typedef struct {\n" +
                        "    pthread_mutex_t mutex;\n" +
                        "    pthread_cond_t  cond;\n" +
                        "    int             signaled;  /* 0 or 1 */\n" +
                        "} SharedEvent;\n" +
                        "\n" +
                        "/* Init condvar (process-shared): */\n" +
                        "pthread_condattr_t cattr;\n" +
                        "pthread_condattr_init(&cattr);\n" +
                        "pthread_condattr_setpshared(&cattr, PTHREAD_PROCESS_SHARED);\n" +
                        "pthread_cond_init(&ev->cond, &cattr);\n" +
                        "pthread_condattr_destroy(&cattr);\n" +
                        "/* (init mutex with PROCESS_SHARED as above) */\n" +
                        "\n" +
                        "/* Signal — like SetEvent: */\n" +
                        "pthread_mutex_lock(&ev->mutex);\n" +
                        "ev->signaled = 1;\n" +
                        "pthread_cond_broadcast(&ev->cond); /* wake all waiters */\n" +
                        "pthread_mutex_unlock(&ev->mutex);\n" +
                        "\n" +
                        "/* Wait — like WaitForSingleObject: */\n" +
                        "pthread_mutex_lock(&ev->mutex);\n" +
                        "while (!ev->signaled)\n" +
                        "    pthread_cond_wait(&ev->cond, &ev->mutex);\n" +
                        "ev->signaled = 0; /* auto-reset; omit for manual-reset style */\n" +
                        "pthread_mutex_unlock(&ev->mutex);"
                    )
                }
            }

            item {
                SectionCard(title = "Named Semaphores (≈ Windows CreateSemaphore)") {
                    BodyText(
                        "POSIX named semaphores are visible across processes by name (under /dev/shm). " +
                        "No shared memory region is needed — just open by name from each process. Link with -lpthread."
                    )
                    CodeBlock(
                        "#include <semaphore.h>\n" +
                        "\n" +
                        "/* Open or create (initial value = 1): */\n" +
                        "sem_t *sem = sem_open(\"/my_sem\", O_CREAT, 0666, 1);\n" +
                        "\n" +
                        "sem_wait(sem);     /* decrement / acquire — blocks if 0 */\n" +
                        "sem_post(sem);     /* increment / release */\n" +
                        "sem_trywait(sem);  /* non-blocking; returns EAGAIN if 0 */\n" +
                        "\n" +
                        "sem_close(sem);        /* close this process's handle */\n" +
                        "sem_unlink(\"/my_sem\"); /* delete the name */"
                    )
                }
            }

            item {
                SectionCard(title = "Windows → Linux Equivalents") {
                    CodeBlock(
                        "Windows                      Linux\n" +
                        "─────────────────────────────────────────────────────────\n" +
                        "CreateFileMapping /           shm_open() + mmap()\n" +
                        "  MapViewOfFile\n" +
                        "CreateMutex (named)           pthread_mutex_t +\n" +
                        "                                PTHREAD_PROCESS_SHARED in shm\n" +
                        "CreateEvent                   pthread_cond_t +\n" +
                        "                                PTHREAD_PROCESS_SHARED in shm\n" +
                        "CreateSemaphore (named)       sem_open()\n" +
                        "WaitForSingleObject           pthread_mutex_lock /\n" +
                        "                                sem_wait /\n" +
                        "                                pthread_cond_wait\n" +
                        "ReleaseMutex                  pthread_mutex_unlock\n" +
                        "SetEvent                      pthread_cond_signal/broadcast\n" +
                        "ResetEvent                    set flag = 0 under mutex"
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
