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
fun UserModeSignalsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Signal Handlers",
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
                SectionCard(title = "What are Signals?") {
                    BodyText(
                        "Signals are asynchronous notifications delivered to a process by the kernel, another process, " +
                        "or the process itself. They interrupt normal execution and invoke a registered handler function."
                    )
                    BodyText(
                        "Each signal has a default action:\n" +
                        "  • Terminate — kill the process (e.g. SIGTERM, SIGINT)\n" +
                        "  • Core dump — terminate + write core (e.g. SIGSEGV, SIGABRT)\n" +
                        "  • Ignore — silently discard (e.g. SIGCHLD default in some contexts)\n" +
                        "  • Stop — suspend the process (SIGSTOP, SIGTSTP)\n" +
                        "  • Continue — resume a stopped process (SIGCONT)"
                    )
                }
            }

            item {
                SectionCard(title = "Registering a Handler — sigaction") {
                    BodyText(
                        "sigaction() is the preferred API. Unlike the older signal(), it is portable, does not " +
                        "auto-reset the handler to SIG_DFL after delivery, and provides fine-grained control."
                    )
                    CodeBlock(
                        "#include <signal.h>\n" +
                        "\n" +
                        "void my_handler(int signum) {\n" +
                        "    /* minimal work — only async-signal-safe functions! */\n" +
                        "}\n" +
                        "\n" +
                        "struct sigaction sa;\n" +
                        "sa.sa_handler = my_handler;\n" +
                        "sigemptyset(&sa.sa_mask);   /* no extra signals blocked during handler */\n" +
                        "sa.sa_flags = SA_RESTART;   /* auto-restart interrupted syscalls */\n" +
                        "sigaction(SIGINT, &sa, NULL);"
                    )
                    BodyText(
                        "sa_flags options:\n" +
                        "  SA_RESTART  — restart syscalls interrupted by this signal\n" +
                        "  SA_NOCLDWAIT — don't create zombie children (with SIGCHLD)\n" +
                        "  SA_SIGINFO  — use sa_sigaction instead of sa_handler for extra info\n" +
                        "  SA_ONESHOT  — reset to SIG_DFL after first delivery"
                    )
                    BodyText("To ignore a signal:")
                    CodeBlock(
                        "struct sigaction sa = { .sa_handler = SIG_IGN };\n" +
                        "sigaction(SIGPIPE, &sa, NULL);"
                    )
                }
            }

            item {
                SectionCard(title = "Common Signals") {
                    CodeBlock(
                        "SIGHUP  ( 1) — terminal closed / daemon config reload\n" +
                        "SIGINT  ( 2) — Ctrl+C\n" +
                        "SIGQUIT ( 3) — Ctrl+\\  (terminates with core dump)\n" +
                        "SIGKILL ( 9) — force kill  *** CANNOT be caught/blocked/ignored ***\n" +
                        "SIGSEGV (11) — segmentation fault\n" +
                        "SIGPIPE (13) — write to a broken pipe\n" +
                        "SIGALRM (14) — alarm() timer expired\n" +
                        "SIGTERM (15) — graceful termination request\n" +
                        "SIGCHLD (17) — child process changed state\n" +
                        "SIGSTOP (19) — stop process  *** CANNOT be caught/blocked/ignored ***\n" +
                        "SIGTSTP (20) — Ctrl+Z (can be caught, unlike SIGSTOP)\n" +
                        "SIGUSR1 (10) — user-defined\n" +
                        "SIGUSR2 (12) — user-defined"
                    )
                }
            }

            item {
                SectionCard(title = "Signals That Cannot Be Caught") {
                    BodyText(
                        "SIGKILL (9) and SIGSTOP (19) are handled exclusively by the kernel. No process can install " +
                        "a handler, block, or ignore them — sigaction() will return EINVAL if you try."
                    )
                    BodyText(
                        "SIGKILL immediately terminates the process. SIGSTOP immediately suspends it. " +
                        "These are the guarantees that allow administrators and the OOM killer to always regain control."
                    )
                }
            }

            item {
                SectionCard(title = "Signal Masks") {
                    BodyText(
                        "Block signals temporarily to protect critical sections from being interrupted:"
                    )
                    CodeBlock(
                        "sigset_t mask, old_mask;\n" +
                        "sigemptyset(&mask);\n" +
                        "sigaddset(&mask, SIGINT);\n" +
                        "sigaddset(&mask, SIGTERM);\n" +
                        "sigprocmask(SIG_BLOCK, &mask, &old_mask);  /* block */\n" +
                        "\n" +
                        "/* critical section — signals are pending, not delivered */\n" +
                        "\n" +
                        "sigprocmask(SIG_SETMASK, &old_mask, NULL); /* restore */"
                    )
                    BodyText(
                        "sigprocmask flags:\n" +
                        "  SIG_BLOCK   — add mask to current blocked set\n" +
                        "  SIG_UNBLOCK — remove mask from blocked set\n" +
                        "  SIG_SETMASK — replace blocked set entirely"
                    )
                }
            }

            item {
                SectionCard(title = "Async-Signal-Safe Rules") {
                    BodyText(
                        "A signal handler can be called at any point — even in the middle of malloc() or printf(). " +
                        "Only async-signal-safe functions are safe to call inside a handler:"
                    )
                    CodeBlock(
                        "/* SAFE inside a handler: */\n" +
                        "write(), read(), _exit(), kill(), getpid(),\n" +
                        "sigaction(), signal(), open(), close()\n" +
                        "\n" +
                        "/* UNSAFE — may deadlock or corrupt state: */\n" +
                        "printf(), malloc(), free(),\n" +
                        "pthread_mutex_lock(), any stdio function"
                    )
                    BodyText(
                        "Best practice: set a flag in the handler and do the real work in the main loop:"
                    )
                    CodeBlock(
                        "volatile sig_atomic_t g_shutdown = 0;\n" +
                        "\n" +
                        "void handler(int sig) { g_shutdown = 1; }\n" +
                        "\n" +
                        "/* In main: */\n" +
                        "while (!g_shutdown) {\n" +
                        "    /* do work */\n" +
                        "}"
                    )
                }
            }

            item {
                SectionCard(title = "Sending Signals") {
                    CodeBlock(
                        "#include <signal.h>\n" +
                        "\n" +
                        "kill(pid, SIGTERM);     /* send to a specific process */\n" +
                        "kill(0,   SIGTERM);     /* send to all in same process group */\n" +
                        "raise(SIGUSR1);         /* send to self */\n" +
                        "killpg(pgid, SIGINT);   /* send to an entire process group */"
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
