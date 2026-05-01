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
fun PidNamespaceScreen(onBack: () -> Unit) {
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "PID Namespace",
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
                SectionCard(title = "What PID Namespace Does") {
                    BodyText("A PID namespace isolates the process ID number space. Processes inside a namespace can only see (and signal) other processes in the same namespace or its descendants — the host's other processes are invisible to them.")
                    BodyText("The first process created in a new PID namespace always gets PID 1. It acts as init for that namespace: if PID 1 exits, the kernel sends SIGKILL to all remaining processes in the namespace.")
                    BodyText("PID namespaces nest: a container inside a container creates a child PID namespace. A process in a child namespace is visible from parent namespaces with a different PID number, but cannot see back up into the parent.")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "Why pid_ns_for_children, Not the Task's Own PID NS?") {
                    BodyText("nsproxy->pid_ns_for_children is the PID namespace that NEW CHILDREN of this task will be placed into when created with CLONE_NEWPID.")
                    BodyText("When you call clone(CLONE_NEWPID), the kernel creates a new PID namespace. The child starts in that new namespace with PID 1. The parent, however, remains in its original namespace — its own PID does not change.")
                    BodyText("So the parent's pid_ns_for_children now points to the new namespace (children it creates next will go there too), but the parent's own PID namespace is unchanged.")
                    BodyText("To find a task's own PID namespace from kernel code: task_active_pid_ns(task). From userspace: read the symlink /proc/<pid>/ns/pid — the inode number identifies the namespace.")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "One Process, Multiple PIDs") {
                    BodyText("Yes — a single process can have a different PID number in each namespace level it belongs to. For example, a container's init process might be PID 1 inside the container, but PID 28456 on the host.")
                    BodyText("The kernel tracks all these mappings in struct pid, which contains a flexible array of upid entries — one per namespace level:")
                    CodeBlock("""struct pid {
    refcount_t   count;
    unsigned int level;    // how many namespace levels this pid spans
    // ...
    struct upid numbers[]; // flexible array — one entry per level
};

struct upid {
    int nr;                    // the PID number in this namespace
    struct pid_namespace *ns;  // which namespace this entry is for
};
// numbers[0]         = innermost namespace (deepest / most nested)
// numbers[level - 1] = root (host) namespace""")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "Where Is the Mapping Stored?") {
                    BodyText("task_struct holds a pointer to the struct pid object. struct pid is NOT embedded inside task_struct — it is a separate heap-allocated object, because multiple tasks in a thread group share the same struct pid for their TGID.")
                    CodeBlock("""// In task_struct:
struct pid *thread_pid;  // points to this thread's struct pid

// Get the PID number in a specific namespace from kernel code:
pid_t nr = pid_nr_ns(task->thread_pid, target_ns);

// task_active_pid_ns(task) returns the task's own PID namespace.

// From userspace — /proc/<pid>/status shows all namespace PIDs:
// NSpid: 28456  1
// (host PID first, then progressively deeper namespace PIDs)""")
                    BodyText("The same /proc/<pid>/ns/pid symlink can be opened as a file descriptor to pin the namespace alive even after all its processes exit.")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
