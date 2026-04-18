package com.example.linuxapp.screens

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
fun LinuxUsage2Screen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Linux Usage 2",
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
                SectionCard(title = "Processes and Threads — PID and TGID") {
                    BodyText(
                        "Every task in the Linux kernel has two key identity fields: pid (the unique ID for " +
                        "this individual task) and tgid (the Thread Group ID, identifying the process as " +
                        "a whole)."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "When you call fork(), the kernel creates a new process with a fresh address space. " +
                        "The child gets its own new pid, and its tgid is set to that same pid — it is the " +
                        "leader of a new thread group containing just itself."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "When you create a thread with pthread_create() (which calls clone(CLONE_THREAD | ...)), " +
                        "the new thread shares the address space of the calling thread. It gets its own unique " +
                        "pid, but its tgid is the same as the group leader (the first thread)."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """getpid()  → returns tgid (the thread group's identity —
             what user space calls the "process ID")
gettid()  → returns the thread's own individual pid

First thread:  pid == tgid  (it is the group leader)
Other threads: pid != tgid  (unique pid, shared tgid)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Example — a multithreaded program:")
                    CodeBlock(
                        """#include <stdio.h>
#include <pthread.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/syscall.h>

void *thread_fn(void *arg) {
    printf("thread: getpid()=%d  gettid()=%d\n",
           getpid(), (int)syscall(SYS_gettid));
    return NULL;
}

int main(void) {
    pthread_t t;
    printf("main:   getpid()=%d  gettid()=%d\n",
           getpid(), (int)syscall(SYS_gettid));
    pthread_create(&t, NULL, thread_fn, NULL);
    pthread_join(t, NULL);
    return 0;
}
/* Output (example):
   main:   getpid()=1234  gettid()=1234
   thread: getpid()=1234  gettid()=1235  */"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Thread group lifecycle:")
                    CodeBlock(
                        """When the group leader (first thread) exits, its task_struct
is kept alive — zombie-like — until ALL other threads in
the group have also exited. Only then is the zombie reaped
by the parent via wait().

exit_group() — calling this from any thread (or receiving
SIGKILL sent to the process) terminates the entire thread
group at once. All threads are killed, not just the caller."""
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "The init Process") {
                    BodyText(
                        "PID 1 is the init process — the first user-space program the kernel launches after " +
                        "boot. The kernel tries /sbin/init, /etc/init, /bin/init, and /bin/sh in order."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "All other user-space processes are descendants of PID 1. When a parent process dies " +
                        "before its children, those children become orphans and are re-parented to PID 1, " +
                        "which then reaps them when they exit."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Historically, init ran SysV-style scripts sequentially from /etc/init.d/. Modern " +
                        "Linux systems use systemd as PID 1. systemd starts services in parallel, uses socket " +
                        "activation (a service is only started when something tries to connect to its socket), " +
                        "and manages dependencies declaratively via unit files."
                    )
                    CodeBlock(
                        """pstree       # show the full process tree rooted at PID 1
systemctl status <service>
journalctl -u <service>"""
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "Desktop Environments: KDE and GNOME") {
                    BodyText(
                        "A desktop environment (DE) provides the full graphical shell: windows, taskbar, " +
                        "file manager, settings panel, and bundled applications. The two most prominent " +
                        "on Linux are KDE Plasma and GNOME."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "KDE Plasma is built with Qt. It is highly customizable — you can rearrange panels, " +
                        "change window decorations, and tweak nearly every visual detail. The workflow feels " +
                        "familiar to Windows users (taskbar at the bottom, system tray). KWin is its window " +
                        "manager and compositor."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "GNOME is built with GTK. It favors a streamlined, minimal design with an Activities " +
                        "overview for launching apps and switching windows. It is the default desktop on " +
                        "Ubuntu, Fedora, and many other major distributions. Mutter is its window manager " +
                        "and Wayland compositor."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Both DEs run on top of a display server — either the legacy X Window System (X11) " +
                        "or the modern Wayland protocol."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "X Window System (X11)") {
                    BodyText(
                        "X11 is a client-server protocol for displaying graphical user interfaces. The display " +
                        "server (Xorg) runs as a daemon and owns the GPU and input devices. Applications are " +
                        "X clients: they connect to Xorg and ask it to create windows, draw graphics, and " +
                        "deliver input events."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "X11 is network-transparent: you can forward a graphical application over SSH and it " +
                        "will display on your local screen."
                    )
                    CodeBlock(
                        """ssh -X user@remote-host
xclock   # GUI app runs on remote, displays locally"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "The DISPLAY environment variable tells X clients which server to connect to. " +
                        ":0 is the first local display, :1 is the second, and so on."
                    )
                    CodeBlock("export DISPLAY=:0")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "X11 is over 40 years old and has a significant security weakness: all X clients on " +
                        "the same display can read each other's keyboard input and inspect each other's " +
                        "windows. There is no isolation between applications."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "Wayland") {
                    BodyText(
                        "Wayland is the modern replacement for X11. Rather than separating the display server " +
                        "and compositor into two processes, Wayland merges them into a single Wayland compositor " +
                        "that handles rendering, input routing, and window management together."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Each client is isolated: a Wayland app cannot read another app's input events or " +
                        "inspect its window contents. This fixes X11's fundamental security weakness."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "KDE's Wayland compositor is KWin (the same binary that handles X11). GNOME's is Mutter. " +
                        "XWayland is a compatibility layer that runs legacy X11 applications inside a Wayland " +
                        "session without modification."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Most major distributions now default to Wayland: Fedora since version 25, Ubuntu " +
                        "since 22.04 (for the GNOME session). The active Wayland display socket is identified " +
                        "by the WAYLAND_DISPLAY environment variable (typically wayland-0)."
                    )
                    CodeBlock(
                        """echo ${"$"}WAYLAND_DISPLAY   # e.g. wayland-0
echo ${"$"}XDG_SESSION_TYPE  # "wayland" or "x11""""
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "startx") {
                    BodyText(
                        "startx is a shell script that launches an X Window System session from a text " +
                        "terminal (TTY). It calls xinit, which starts the Xorg server and then runs whatever " +
                        "is specified in ~/.xinitrc."
                    )
                    CodeBlock(
                        """# ~/.xinitrc example:
exec startplasma-x11    # start KDE Plasma
# or:
exec gnome-session      # start GNOME
# or:
exec i3                 # start the i3 window manager"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "startx is less common now that most systems use a display manager (GDM for GNOME, " +
                        "SDDM for KDE, LightDM for others) that handles graphical login automatically at boot. " +
                        "startx remains useful on minimal or embedded systems that boot to a TTY and only " +
                        "launch a graphical session on demand."
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
