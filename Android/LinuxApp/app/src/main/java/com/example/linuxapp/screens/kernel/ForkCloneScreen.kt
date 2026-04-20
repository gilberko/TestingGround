package com.example.linuxapp.screens.kernel

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForkCloneScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "fork() and clone()",
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
                SectionCard(title = "fork() — Creating a New Process") {
                    BodyText("fork() creates a new process that is an exact copy of the calling process at the moment of the call. After fork() returns, two independent processes are running the same code from the same point.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Return value:")
                    CodeBlock(
                        """pid_t pid = fork();

if (pid < 0) {
    perror("fork failed");   // error — no child created
} else if (pid == 0) {
    // ── CHILD process ──
    // fork() returns 0 to the child.
    printf("I am the child, my PID = %d\n", getpid());
    exit(0);
} else {
    // ── PARENT process ──
    // fork() returns the child's PID to the parent.
    printf("I am the parent, child PID = %d\n", pid);
    wait(NULL);   // reap child to avoid zombie
}"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("After fork(), both processes are identical: same memory layout, open file descriptors, signal handlers, and CPU state. They diverge only because fork() returns a different value to each, and from that point they are fully independent — changes in one do not affect the other.")
                }
            }
            item {
                SectionCard(title = "clone() — The Underlying Syscall") {
                    BodyText("fork() is implemented as a thin wrapper around the clone() syscall. clone() is the real mechanism; it accepts a flags argument that controls precisely what the new task shares with its creator.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Process vs thread at the kernel level:")
                    CodeBlock(
                        """// New independent process — shares nothing.
// This is what fork() does internally.
clone(0);

// New thread — shares everything with the parent.
// This is what pthread_create() / glibc does internally.
clone(CLONE_VM       // share address space (mm_struct)
    | CLONE_FILES    // share file descriptor table
    | CLONE_SIGHAND  // share signal handlers
    | CLONE_THREAD   // same thread group (same TGID)
    | CLONE_SETTLS   // set TLS descriptor for new thread
    | CLONE_PARENT_SETTID
    | CLONE_CHILD_CLEARTID);"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Key CLONE_* flags:")
                    CodeBlock(
                        """Flag               Meaning
────────────────────────────────────────────────────────
CLONE_VM           Share mm_struct (same virtual address space)
CLONE_FILES        Share files_struct (file descriptor table)
CLONE_SIGHAND      Share sighand_struct (signal handlers)
CLONE_THREAD       Place in same thread group (share TGID)
CLONE_FS           Share fs_struct (cwd, root, umask)
CLONE_SETTLS       Set TLS base register for new task
CLONE_NEWPID       New PID namespace
CLONE_NEWNET       New network namespace
CLONE_NEWNS        New mount namespace
CLONE_NEWUTS       New UTS namespace (hostname)
CLONE_NEWIPC       New IPC namespace
CLONE_NEWUSER      New user namespace (UID/GID mapping)
CLONE_NEWCGROUP    New cgroup namespace
CLONE_NEWTIME      New time namespace"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The rule: if a CLONE_* flag for a resource is set, the new task shares that resource's kernel struct with the parent. If absent, the kernel duplicates (or creates fresh) that struct for the child.")
                }
            }
            item {
                SectionCard(title = "Copy-on-Write Memory (COW)") {
                    BodyText("When fork() is called, no physical memory pages are copied. The kernel takes an efficient shortcut: copy-on-write (COW).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("What the kernel actually does at fork() time:")
                    CodeBlock(
                        """Duplicated (new structs allocated):
  mm_struct          — new struct with same layout as parent
  VMA tree           — all vm_area_struct entries copied
  Page tables        — all page table entries copied

Shared (NOT duplicated — only refcount incremented):
  Physical page frames — parent and child PTEs point to
                         the same RAM pages

Key modification:
  All writable PTEs in BOTH parent and child are marked
  read-only by the kernel during the fork() path."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("What happens on the first write after fork():")
                    CodeBlock(
                        """1. A process writes to a page that was writable before fork().
2. CPU raises a page fault — the PTE is now read-only.
3. Kernel page fault handler calls do_wp_page().
4. do_wp_page() checks: VM_WRITE is set in the VMA but
   the PTE is read-only → this is a COW fault.
5. A new physical page frame is allocated.
6. The original page's content is copied into the new frame.
7. Only THIS process's PTE is updated to point to the new
   frame and marked writable again.
8. The other process's PTE still points to the original
   frame (its refcount is decremented by 1).
9. The faulting instruction is retried — succeeds.

Result: each process has its own independent copy of only
the pages it actually writes to."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("This makes fork() extremely cheap — even a 1 GB process forks in microseconds because no memory is physically copied upfront. Pages are duplicated lazily, only on demand.")
                }
            }
            item {
                SectionCard(title = "fork() + exec() Pattern") {
                    BodyText("The canonical way to launch a new program in Unix/Linux is the fork-exec pattern. The parent forks a child; the child immediately replaces itself with a new program via execve().")
                    CodeBlock(
                        """pid_t pid = fork();
if (pid == 0) {
    // Child: replace this process image with a new program.
    // execve() loads the new binary, sets up a fresh stack,
    // heap, and text segment — the old address space is gone.
    execve("/usr/bin/ls",
           (char *[]){"/usr/bin/ls", "-l", NULL},
           environ);

    // execve() only returns on error:
    perror("execve");
    exit(1);
}
// Parent continues its own execution.
waitpid(pid, NULL, 0);"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The COW pages allocated during fork() are immediately discarded when the child calls execve() — the new program gets a completely fresh address space. No physical page is ever duplicated in the fork+exec flow; COW makes the copy truly zero-cost.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("This is also how shells work: every command you type causes the shell to fork() itself, then the child exec()s the target binary while the parent shell waits with waitpid().")
                }
            }
            item {
                SectionCard(title = "Namespaces — Isolation at the Kernel Level") {
                    BodyText("Every process has a struct nsproxy inside its task_struct. nsproxy holds pointers to namespace objects — one per namespace type. Namespaces isolate a process's view of global kernel resources, giving it a private instance of that resource.")
                    CodeBlock(
                        """struct nsproxy {
    struct uts_namespace    *uts_ns;  /* hostname, domainname */
    struct ipc_namespace    *ipc_ns;  /* SysV IPC, POSIX mqueues */
    struct mnt_namespace    *mnt_ns;  /* mount tree */
    struct pid_namespace    *pid_ns_for_children;
    struct net              *net_ns;  /* full network stack */
    struct time_namespace   *time_ns; /* clock offsets */
    struct cgroup_namespace *cgroup_ns;
    /* user namespace lives in struct cred, not nsproxy */
};"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The 8 namespace types:")
                    CodeBlock(
                        """Namespace  clone() flag      What it isolates
──────────────────────────────────────────────────────────────────
PID        CLONE_NEWPID      PID number space; first process sees PID 1
Network    CLONE_NEWNET      Own network stack: interfaces, routing,
                             iptables rules, sockets
Mount      CLONE_NEWNS       Own filesystem mount tree
UTS        CLONE_NEWUTS      Own hostname and domainname
IPC        CLONE_NEWIPC      Own SysV IPC objects + POSIX mqueues
User       CLONE_NEWUSER     Own UID/GID mapping; unprivileged process
                             can appear as uid 0 inside the namespace
Cgroup     CLONE_NEWCGROUP   Own view of the cgroup hierarchy
Time       CLONE_NEWTIME     Own monotonic and boottime clock offsets"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Namespaces are the kernel foundation of containers. Docker and podman create containers by calling clone() with multiple CLONE_NEW* flags, then using cgroups for resource limits (CPU, memory, I/O). The container process thinks it is alone on the system — it sees its own PID 1, its own network stack, its own filesystem root.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Creating a new PID namespace from the shell:")
                    CodeBlock(
                        """# unshare(1) calls the unshare() syscall to detach the
# calling process from one or more namespaces.
sudo unshare --pid --fork --mount-proc /bin/bash

# Inside the new shell:
echo $$      # prints 1  ← you are PID 1 in this namespace
ps aux       # only sees processes in this namespace"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Calling clone() with CLONE_NEWPID in C:")
                    CodeBlock(
                        """#define _GNU_SOURCE
#include <sched.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

#define STACK_SIZE (1024 * 1024)

static int child_fn(void *arg) {
    printf("PID inside namespace: %d\n", getpid()); // 1
    return 0;
}

int main(void) {
    char *stack = malloc(STACK_SIZE);
    // clone() with CLONE_NEWPID creates a new PID namespace.
    // The child's getpid() returns 1 inside that namespace.
    pid_t pid = clone(child_fn,
                      stack + STACK_SIZE,  // stack grows down
                      CLONE_NEWPID | SIGCHLD,
                      NULL);
    printf("Child real PID (parent view): %d\n", pid);
    waitpid(pid, NULL, 0);
    free(stack);
    return 0;
}"""
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
