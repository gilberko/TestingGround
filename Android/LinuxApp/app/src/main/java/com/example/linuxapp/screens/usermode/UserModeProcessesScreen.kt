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
fun UserModeProcessesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Processes",
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
                SectionCard(title = "What Is a Process?") {
                    BodyText("A process is an instance of a running program. Every process has:")
                    CodeBlock(
                        """- Its own virtual address space (code, heap, stack, mmap regions)
- A unique PID (Process ID)
- Its own file descriptor table
- Its own signal handlers and pending signal set
- A parent process (except PID 1 — init/systemd)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Process vs thread: threads share the same address space within a process. Processes are isolated — one process cannot directly read another's memory. This isolation is enforced by the kernel's virtual memory system (each process has its own page table).")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("The shell creates a new process for every command you run. You can see all running processes with ps aux.")
                }
            }
            item {
                SectionCard(title = "fork() — Creating a New Process") {
                    BodyText("fork() creates a copy of the calling process. After the call, two processes exist: the original parent and the new child. Both resume execution from the line immediately after fork().")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Return value — this is the key to understanding fork():")
                    CodeBlock(
                        """fork() returns a different value in each process:

  In the PARENT: returns the child's PID (a positive integer)
  In the CHILD:  returns 0
  On error:      returns -1 (child is not created)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("This is how you branch on which process you are in:")
                    CodeBlock(
                        """#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

int main(void)
{
    pid_t pid = fork();

    if (pid < 0) {
        /* Error: fork failed */
        perror("fork");
        return 1;
    } else if (pid == 0) {
        /* We are in the CHILD process */
        printf("Child: my PID = %d, parent PID = %d\n",
               getpid(), getppid());
    } else {
        /* We are in the PARENT process; pid = child's PID */
        printf("Parent: my PID = %d, child PID = %d\n",
               getpid(), pid);
    }
    return 0;
}"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The child is a nearly identical copy of the parent at the moment of fork(): same code, same heap contents, same open file descriptors. Changes made after fork() are independent in each process (copy-on-write).")
                }
            }
            item {
                SectionCard(title = "wait() and waitpid()") {
                    BodyText("When a child process exits, it becomes a zombie until the parent collects its exit status. The parent must call wait() or waitpid() to prevent zombie accumulation.")
                    CodeBlock(
                        """#include <sys/wait.h>

/* wait for ANY child to finish: */
int status;
pid_t child_pid = wait(&status);

/* wait for a SPECIFIC child: */
pid_t child_pid = waitpid(pid,    /* PID to wait for */
                          &status, /* exit status output */
                          0);      /* options (0 = blocking) """
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Inspecting the exit status with macros:")
                    CodeBlock(
                        """if (WIFEXITED(status)) {
    /* Child called exit() or returned from main() */
    int code = WEXITSTATUS(status);   /* exit code (0-255) */
    printf("Exited with code %d\n", code);
}
if (WIFSIGNALED(status)) {
    /* Child was killed by a signal */
    int sig = WTERMSIG(status);
    printf("Killed by signal %d\n", sig);
}"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("waitpid() with WNOHANG returns immediately (0) if no child has exited yet — useful for polling without blocking.")
                }
            }
            item {
                SectionCard(title = "execve() — Replace the Process Image") {
                    BodyText("execve() replaces the current process's program with a new one. The PID stays the same, but the code, heap, stack, and data are all replaced.")
                    CodeBlock(
                        """#include <unistd.h>

int execve(const char *pathname,  /* full path to executable */
           char *const argv[],    /* argument list (argv[0] = program name) */
           char *const envp[]);   /* environment variables */

/* Returns -1 on error. On success it NEVER returns —
   the current program is gone, replaced by the new one. */"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Example — run /bin/ls with arguments:")
                    CodeBlock(
                        """char *args[] = { "ls", "-la", "/tmp", NULL };  /* must be NULL-terminated */
char *env[]  = { NULL };                       /* empty environment */

execve("/bin/ls", args, env);

/* If we reach here, execve failed: */
perror("execve");"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Open file descriptors survive across execve() unless marked with O_CLOEXEC. Signal handlers are reset to defaults. Memory mappings are replaced.")
                }
            }
            item {
                SectionCard(title = "exec() Family") {
                    BodyText("The exec() family are convenience wrappers around execve(). The naming convention encodes what they accept:")
                    CodeBlock(
                        """l  = arguments passed as a list  (varargs, NULL-terminated)
v  = arguments passed as a vector (char *argv[])
p  = search PATH for the executable (no need for full path)
e  = caller supplies the environment (envp[] parameter)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("All variants:")
                    CodeBlock(
                        """/* List args, explicit path: */
execl("/bin/ls", "ls", "-la", NULL);

/* List args, search PATH: */
execlp("ls", "ls", "-la", NULL);

/* List args, explicit path, custom environment: */
execle("/bin/ls", "ls", "-la", NULL, envp);

/* Array args, explicit path: */
char *args[] = {"ls", "-la", NULL};
execv("/bin/ls", args);

/* Array args, search PATH: */
execvp("ls", args);

/* Array args, search PATH, custom environment: */
execvpe("ls", args, envp);"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("All of these are thin wrappers — they ultimately call execve(). The p variants search PATH so you don't need the full path. The e variants let you control exactly what environment the new program sees.")
                }
            }
            item {
                SectionCard(title = "fork + exec — The Classic Pattern") {
                    BodyText("The standard Unix way to run a new program is to fork() first, then exec() in the child. This lets the parent continue running while the child runs the new program.")
                    CodeBlock(
                        """#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main(void)
{
    pid_t pid = fork();

    if (pid < 0) {
        perror("fork");
        return 1;
    }

    if (pid == 0) {
        /* --- CHILD ---
           Replace ourselves with "ls -la /tmp" */
        char *args[] = { "ls", "-la", "/tmp", NULL };
        execvp("ls", args);

        /* execvp only returns on failure: */
        perror("execvp");
        _exit(1);   /* use _exit in child, not exit(),
                       to avoid flushing parent's stdio buffers */
    }

    /* --- PARENT ---
       Wait for the child to finish. */
    int status;
    waitpid(pid, &status, 0);

    if (WIFEXITED(status))
        printf("Child exited with code %d\n", WEXITSTATUS(status));

    return 0;
}"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Why fork + exec instead of just exec? Because fork() lets the parent set up the child's environment before exec() runs — redirect stdin/stdout, close file descriptors, set the working directory, etc. This is how your shell runs every command you type.")
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
