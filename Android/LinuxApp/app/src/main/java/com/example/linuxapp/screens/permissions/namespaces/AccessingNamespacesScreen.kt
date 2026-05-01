package com.example.linuxapp.screens.permissions.namespaces

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
fun AccessingNamespacesScreen(onBack: () -> Unit) {
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Accessing Namespaces From The Host",
                        color = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontSize = 14.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item {
                SectionCard(title = "/proc/<pid>/ns/") {
                    BodyText("Every process exposes its namespaces as symlinks under /proc/<pid>/ns/. Each symlink points to a namespace inode in the format type:[inode-number].")
                    CodeBlock("""ls -la /proc/<pid>/ns/
# lrwxrwxrwx cgroup -> cgroup:[4026531835]
# lrwxrwxrwx ipc    -> ipc:[4026531839]
# lrwxrwxrwx mnt    -> mnt:[4026531840]
# lrwxrwxrwx net    -> net:[4026531992]
# lrwxrwxrwx pid    -> pid:[4026531836]
# lrwxrwxrwx pid_for_children -> pid:[4026531836]
# lrwxrwxrwx time   -> time:[4026531834]
# lrwxrwxrwx time_for_children -> time:[4026531834]
# lrwxrwxrwx user   -> user:[4026531837]
# lrwxrwxrwx uts    -> uts:[4026531838]""")
                    BodyText("If two processes share the same inode number for a given namespace type, they are in the same namespace.")
                    BodyText("Keeping an open file descriptor to /proc/<pid>/ns/X prevents the namespace from being destroyed even after all its processes have exited. This is how setns() and nsenter work.")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "clone / clone3 Namespace Flags") {
                    BodyText("Pass CLONE_NEW* flags to clone() or clone3() to create a process in a new namespace. The child enters the new namespace; the parent stays in the old one.")
                    CodeBlock("""// All CLONE_NEW* flags:
CLONE_NEWCGROUP  // new cgroup namespace
CLONE_NEWIPC     // new IPC namespace
CLONE_NEWNET     // new network namespace
CLONE_NEWNS      // new mount namespace
CLONE_NEWPID     // new PID namespace
CLONE_NEWTIME    // new time namespace (Linux 5.6+)
CLONE_NEWUSER    // new user namespace
CLONE_NEWUTS     // new UTS namespace

// Example with clone3:
struct clone_args args = {
    .flags       = CLONE_NEWPID | CLONE_NEWNET | CLONE_NEWNS,
    .exit_signal = SIGCHLD,
};
pid_t pid = syscall(SYS_clone3, &args, sizeof(args));""")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "setns — Attach to an Existing Namespace") {
                    BodyText("setns() attaches the calling thread to an existing namespace referenced by an open file descriptor. This is how a host process can 'enter' a running container's namespace.")
                    CodeBlock("""#include <sched.h>
int setns(int fd, int nstype);

// fd: open FD to /proc/<pid>/ns/<type>
// nstype: CLONE_NEW* constant to verify the type, or 0 to skip check
// Returns 0 on success, -1 on error
// Requires CAP_SYS_ADMIN (or a user namespace with appropriate rights)

// Example: enter a container's network namespace:
int fd = open("/proc/12345/ns/net", O_RDONLY | O_CLOEXEC);
setns(fd, CLONE_NEWNET);
close(fd);
// This thread is now operating inside process 12345's network namespace""")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "unshare — New Namespace for the Calling Process") {
                    BodyText("unshare() creates new namespaces and places the calling process into them — without forking a new process. The process detaches from its current shared namespaces.")
                    CodeBlock("""#include <sched.h>
int unshare(int flags);

// flags: CLONE_NEW* constants — same as clone/clone3
// Unlike clone(), does NOT create a new process.

// Example: give the current process its own private network namespace:
unshare(CLONE_NEWNET | CLONE_NEWNS);
// From here, mount() and socket() operate in isolated namespaces

// Command-line equivalent:
// unshare --net --mount -- bash""")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "nsenter — Enter a Namespace (Command)") {
                    BodyText("nsenter is a command-line tool that calls setns() to enter one or more namespaces of a running process, then exec's a specified command. This is effectively what 'docker exec' does internally.")
                    CodeBlock("""# Enter a specific namespace:
nsenter -t <pid> -n          # net namespace only
nsenter -t <pid> -m          # mount namespace only
nsenter -t <pid> -p          # pid namespace only

# Enter multiple at once and run bash:
nsenter -t <pid> -n -m -p -- bash

# Enter ALL namespaces of the target process:
nsenter -t <pid> --all -- bash

# Flags: -n=net  -m=mount  -p=pid  -u=uts  -i=ipc
#        -U=user -C=cgroup -T=time""")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "lsns — List Namespaces (Command)") {
                    BodyText("lsns reads /proc to enumerate all namespaces visible to the current user and displays them in a table.")
                    CodeBlock("""lsns            # list all namespace types
lsns -t net     # list only network namespaces
lsns -t pid     # list only PID namespaces
# Types: net pid mnt uts ipc user time cgroup

# Output columns:
# NS        - namespace inode number
# TYPE      - namespace type
# NPROCS    - number of processes in this namespace
# PID       - representative process PID
# USER      - owning user
# COMMAND   - command of the representative process""")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "pidfd_open — Process File Descriptor") {
                    BodyText("pidfd_open() was added in Linux 5.3 (September 2019) — relatively recent. It returns a file descriptor that uniquely refers to a specific process, solving the PID reuse race that exists when using raw PID numbers.")
                    CodeBlock("""#include <sys/syscall.h>

// Open a pidfd for a running process:
int pidfd = syscall(SYS_pidfd_open, pid, 0);
// flags: currently must be 0

// Advantages over raw PIDs:
// 1. Immune to PID reuse: the FD stays valid for the exact
//    process, even if that PID is recycled for another process.
// 2. Can poll()/epoll() on it: becomes readable when process exits.
// 3. Passable via SCM_RIGHTS between processes.
// 4. clone3() + CLONE_PIDFD: get a pidfd for the child atomically.

// Race-free signal delivery:
syscall(SYS_pidfd_send_signal, pidfd, SIGTERM, NULL, 0);

// To get a namespace FD via pidfd:
// /proc/self/fd/<pidfd>/ns/<type>  ->  namespace symlink
// Then open that path and pass to setns().""")
                    BodyText("pidfd_open is not a namespace API itself — it is a process handle API. But it is commonly paired with namespace operations to avoid PID reuse races when targeting a specific container process.")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
