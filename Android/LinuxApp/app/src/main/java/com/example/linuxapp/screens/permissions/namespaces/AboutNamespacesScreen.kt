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
fun AboutNamespacesScreen(onBack: () -> Unit) {
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "About Namespaces",
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
                SectionCard(title = "What Are Namespaces") {
                    BodyText("Linux namespaces are a kernel feature that partitions global system resources so that different sets of processes each see their own isolated instance of those resources.")
                    BodyText("They are the foundational technology behind Linux containers (Docker, Podman, LXC, containerd). A container is essentially a process tree running inside a set of namespaces that give it the illusion of having its own private system.")
                    BodyText("There are 8 namespace types, each wrapping a different class of kernel resource.")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "struct nsproxy in task_struct") {
                    BodyText("Every process (task_struct) carries a pointer to an nsproxy struct that holds references to all its namespaces. Tasks sharing the same namespaces share the same nsproxy (reference counted).")
                    CodeBlock("""// In task_struct:
struct nsproxy *nsproxy;

// struct nsproxy (include/linux/nsproxy.h):
struct nsproxy {
    atomic_t count;                        // ref count — shared across tasks
    struct uts_namespace    *uts_ns;       // hostname/domain
    struct ipc_namespace    *ipc_ns;       // SysV IPC + POSIX mqueues
    struct mnt_namespace    *mnt_ns;       // mount tree
    struct pid_namespace    *pid_ns_for_children; // PID ns for NEW children
    struct net              *net_ns;       // network stack
    struct time_namespace   *time_ns;      // this task's time ns
    struct time_namespace   *time_ns_for_children;
    struct cgroup_namespace *cgroup_ns;    // cgroup hierarchy view
};""")
                    BodyText("Note: pid_ns_for_children is the PID namespace that new children of this task will be born into — NOT the task's own PID namespace. See the PID Namespace screen for details.")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "Namespace Types Overview") {
                    BodyText("PID (CLONE_NEWPID) — isolates process ID numbers. Processes inside only see their peers.")
                    BodyText("Network (CLONE_NEWNET) — isolates the network stack: interfaces, routing tables, iptables rules, ports.")
                    BodyText("Mount (CLONE_NEWNS) — isolates the filesystem mount tree. Each namespace has its own view of what is mounted where.")
                    BodyText("UTS (CLONE_NEWUTS) — isolates hostname and NIS domain name.")
                    BodyText("IPC (CLONE_NEWIPC) — isolates System V IPC objects and POSIX message queues.")
                    BodyText("User (CLONE_NEWUSER) — maps UIDs/GIDs between host and namespace. Enables rootless containers.")
                    BodyText("Time (CLONE_NEWTIME) — isolates CLOCK_BOOTTIME and CLOCK_MONOTONIC offsets. Added in Linux 5.6.")
                    BodyText("Cgroup (CLONE_NEWCGROUP) — isolates the view of the cgroup hierarchy.")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "Creating Namespaces: clone and clone3") {
                    BodyText("Pass CLONE_NEW* flags to clone() or clone3() to place the child into a new namespace. The parent remains in the old namespace.")
                    CodeBlock("""// clone() — older API, widely used:
#define _GNU_SOURCE
#include <sched.h>

pid_t pid = clone(child_fn, stack_top,
                  CLONE_NEWPID | CLONE_NEWNET | SIGCHLD, arg);

// clone3() — added Linux 5.3; struct-based, safer and extensible:
#include <linux/sched.h>
#include <sys/syscall.h>

struct clone_args args = {
    .flags       = CLONE_NEWPID | CLONE_NEWNET,
    .exit_signal = SIGCHLD,
};
pid_t pid = syscall(SYS_clone3, &args, sizeof(args));""")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "clone vs clone3 Differences") {
                    BodyText("clone: passes individual arguments (flags, stack, TLS, etc.) — older ABI, widely supported.")
                    BodyText("clone3: takes a struct clone_args. Benefits: no argument miscount bugs, supports CLONE_PIDFD (atomically get a file descriptor for the child at birth), and the struct is forward-compatible — new fields can be added.")
                    BodyText("fork() is just clone(SIGCHLD) under the hood. It has no way to pass CLONE_NEW* flags, so fork() cannot create new namespaces.")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "Syscalls Respect the Calling Process's Namespace") {
                    BodyText("When a process calls a syscall, the kernel automatically uses its relevant namespace — no special handling is needed per call:")
                    BodyText("socket() — creates the socket inside the process's network namespace (net_ns).")
                    BodyText("mount() — operates on the process's mount namespace (mnt_ns).")
                    BodyText("getpid() — returns the PID as seen from the process's own PID namespace.")
                    BodyText("kill(pid, ...) — looks up the target PID inside the caller's PID namespace.")
                    BodyText("open() / stat() — path resolution walks the process's mount namespace mount tree.")
                    BodyText("This automatic scoping is why a containerized process naturally stays within its namespace boundaries without any extra configuration at the syscall level.")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
