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
fun UserModeFanotifyScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "fanotify / inotify",
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
                SectionCard(title = "fsnotify — The Shared Kernel Framework") {
                    BodyText("Your intuition is correct: both inotify and fanotify share the same internal kernel plumbing, called fsnotify.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("fsnotify is not a userspace API — it is an internal VFS-level notification framework introduced in Linux 2.6.36. When the VFS performs a file operation (open, read, write, create, delete, rename, chmod…), it calls into fsnotify, which dispatches the event to every registered backend. inotify and fanotify are two separate backends that plug into this framework.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The relationship:")
                    CodeBlock(
                        """VFS (open/read/write/create/…)
      │
      ▼
  fsnotify  ◄── internal kernel framework
   ├── inotify backend  →  userspace fd (struct inotify_event)
   └── fanotify backend →  userspace fd (struct fanotify_event_metadata)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Because both sit on top of fsnotify, they both receive VFS-level hooks. The difference lies in what each API exposes to userspace — inotify is simpler and lighter, fanotify is more powerful and can block access.")
                }
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "inotify — Lightweight File/Directory Watcher") {
                    BodyText("inotify was introduced in Linux 2.6.13 (2005) to replace the older, clunkier dnotify mechanism. It lets userspace programs watch files or directories for a set of events and be notified via a file descriptor — no polling required.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Common event flags:")
                    CodeBlock(
                        """IN_CREATE      file/dir created inside watched dir
IN_DELETE      file/dir deleted from watched dir
IN_MODIFY      file content modified
IN_CLOSE_WRITE file closed after being opened for writing
IN_MOVED_FROM  file moved out of watched dir
IN_MOVED_TO    file moved into watched dir
IN_ATTRIB      metadata changed (permissions, timestamps)
IN_DELETE_SELF watched file/dir itself deleted"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("API flow:")
                    CodeBlock(
                        """int fd = inotify_init1(IN_CLOEXEC);
int wd = inotify_add_watch(fd, "/path", mask);  // returns watch descriptor
// read() delivers struct inotify_event entries
inotify_rm_watch(fd, wd);
close(fd);"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Limitations: inotify is path-based, not mount-wide. It cannot block or deny file access — it only observes. If a watched directory is deleted and recreated, the watch must be re-registered. It does not tell you which process triggered the event.")
                }
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "fanotify — Powerful Filesystem Notifier") {
                    BodyText("fanotify (File Access Notify) was introduced in Linux 2.6.36 (2010). It is significantly more capable than inotify and is the foundation of modern Linux security tools, AV scanners, and file integrity monitors.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Two classes of events:")
                    CodeBlock(
                        """Notification events (informational only — like inotify):
  FAN_OPEN, FAN_CLOSE, FAN_MODIFY, FAN_ACCESS

Permission events (block the accessing process until answered):
  FAN_OPEN_PERM    — block on open()
  FAN_ACCESS_PERM  — block on read()"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Key advantages over inotify:")
                    CodeBlock(
                        """• Watch entire mount points or filesystems, not just individual paths
• Permission events let you allow or deny the triggering access
• Delivers an open fd to the accessed file (not just a path string)
  → immune to TOCTOU races; you inspect the actual file being opened
• The event tells you the PID of the accessing process"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("fanotify requires CAP_SYS_ADMIN (or CAP_DAC_READ_SEARCH for some read-only modes). It is used by tools like ClamAV on-access scanning, OSSEC, and systemd's file monitoring.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("fanotify_init() flags select the event class:")
                    CodeBlock(
                        """FAN_CLASS_NOTIF    notification events only (no permission events)
FAN_CLASS_CONTENT  permission events on file content access
FAN_CLASS_PRE_CONTENT permission events before content is available"""
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "inotify vs fanotify — Key Differences") {
                    CodeBlock(
                        """Feature                    inotify       fanotify
─────────────────────────────────────────────────────
Kernel since               2.6.13        2.6.36
Underlying framework       fsnotify      fsnotify
Watch scope                Path/dir      Mount or filesystem
Can block/deny access      No            Yes (permission events)
Fd to accessed file        No            Yes
Knows accessing PID        No            Yes
Root required              No            Yes (CAP_SYS_ADMIN)
Mount-wide watching        No            Yes
Overhead                   Low           Moderate (esp. permission)
Typical use                App watchers  Security/AV agents"""
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "fanotify — Notification Mode (print events)") {
                    BodyText("This example watches the entire /tmp mount for open/close/modify events and prints the path of each accessed file. No blocking — FAN_CLASS_NOTIF is informational only.")
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """#include <fcntl.h>
#include <stdio.h>
#include <sys/fanotify.h>
#include <unistd.h>

int main(void) {
    // FAN_CLASS_NOTIF: notification events only (no permission events)
    int fan_fd = fanotify_init(FAN_CLOEXEC | FAN_CLASS_NOTIF,
                               O_RDONLY | O_LARGEFILE);

    // Mark the entire /tmp mount for open/close/modify events
    fanotify_mark(fan_fd,
                  FAN_MARK_ADD | FAN_MARK_MOUNT,
                  FAN_OPEN | FAN_CLOSE | FAN_MODIFY,
                  AT_FDCWD, "/tmp");

    char buf[4096];
    while (1) {
        ssize_t len = read(fan_fd, buf, sizeof(buf));
        struct fanotify_event_metadata *meta =
            (struct fanotify_event_metadata *)buf;

        while (FAN_EVENT_OK(meta, len)) {
            // Resolve the open fd to a path via /proc/self/fd/
            char path[256] = {};
            char fdpath[64];
            snprintf(fdpath, sizeof(fdpath),
                     "/proc/self/fd/%d", meta->fd);
            readlink(fdpath, path, sizeof(path) - 1);

            printf("pid=%-6d mask=0x%08llx path=%s\n",
                   meta->pid, (unsigned long long)meta->mask, path);

            // Must close the per-event fd
            close(meta->fd);
            meta = FAN_EVENT_NEXT(meta, len);
        }
    }
    close(fan_fd);
    return 0;
}"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Compile and run as root: gcc -o fan_notify fan_notify.c && sudo ./fan_notify")
                }
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "fanotify — Permission Mode (allow / deny)") {
                    BodyText("Permission events let you intercept file access and decide whether to allow or deny it. The accessing process is blocked in the kernel until your daemon writes a FAN_ALLOW or FAN_DENY response. This example blocks access to any file named secret.txt inside /tmp/protected.")
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """#include <fcntl.h>
#include <stdio.h>
#include <string.h>
#include <sys/fanotify.h>
#include <unistd.h>

int main(void) {
    // FAN_CLASS_CONTENT: enables permission events
    int fan_fd = fanotify_init(FAN_CLOEXEC | FAN_CLASS_CONTENT,
                               O_RDONLY | O_LARGEFILE);

    // Watch the mount containing /tmp/protected for open permission events
    fanotify_mark(fan_fd,
                  FAN_MARK_ADD | FAN_MARK_MOUNT,
                  FAN_OPEN_PERM,
                  AT_FDCWD, "/tmp/protected");

    char buf[4096];
    while (1) {
        ssize_t len = read(fan_fd, buf, sizeof(buf));
        struct fanotify_event_metadata *meta =
            (struct fanotify_event_metadata *)buf;

        while (FAN_EVENT_OK(meta, len)) {
            // Resolve accessed file path
            char path[256] = {};
            char fdpath[64];
            snprintf(fdpath, sizeof(fdpath),
                     "/proc/self/fd/%d", meta->fd);
            readlink(fdpath, path, sizeof(path) - 1);

            struct fanotify_response resp;
            resp.fd = meta->fd;

            if (strstr(path, "secret.txt")) {
                printf("DENY  pid=%d path=%s\n", meta->pid, path);
                resp.response = FAN_DENY;
            } else {
                printf("ALLOW pid=%d path=%s\n", meta->pid, path);
                resp.response = FAN_ALLOW;
            }

            // Write response — unblocks the accessing process
            write(fan_fd, &resp, sizeof(resp));
            close(meta->fd);
            meta = FAN_EVENT_NEXT(meta, len);
        }
    }
    close(fan_fd);
    return 0;
}"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Run as root. While the daemon is running, any process trying to open secret.txt will receive EACCES. All other files are allowed through normally.")
                }
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "inotify — Watch a Directory for Events") {
                    BodyText("This example watches /tmp/watch_dir and prints the event type and filename for every create, delete, modify, or move event. inotify does not require root.")
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """#include <stdio.h>
#include <sys/inotify.h>
#include <unistd.h>

int main(void) {
    int fd = inotify_init1(IN_CLOEXEC);

    // Add a watch on the target directory
    int wd = inotify_add_watch(fd, "/tmp/watch_dir",
                               IN_CREATE    |
                               IN_DELETE    |
                               IN_MODIFY    |
                               IN_CLOSE_WRITE |
                               IN_MOVED_FROM |
                               IN_MOVED_TO);

    // inotify events are read as a stream of variable-length records
    char buf[4096] __attribute__((aligned(8)));
    while (1) {
        ssize_t len = read(fd, buf, sizeof(buf));
        char *ptr = buf;
        while (ptr < buf + len) {
            struct inotify_event *ev = (struct inotify_event *)ptr;

            const char *type =
                (ev->mask & IN_CREATE)     ? "CREATE"      :
                (ev->mask & IN_DELETE)     ? "DELETE"      :
                (ev->mask & IN_MODIFY)     ? "MODIFY"      :
                (ev->mask & IN_CLOSE_WRITE)? "CLOSE_WRITE" :
                (ev->mask & IN_MOVED_FROM) ? "MOVED_FROM"  :
                (ev->mask & IN_MOVED_TO)   ? "MOVED_TO"    : "OTHER";

            // ev->name is set only when watching a directory
            printf("[inotify] %-12s %s\n",
                   type, ev->len ? ev->name : "(watched path itself)");

            // Each record is sizeof(inotify_event) + ev->len (name field)
            ptr += sizeof(*ev) + ev->len;
        }
    }

    inotify_rm_watch(fd, wd);
    close(fd);
    return 0;
}"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Compile: gcc -o inotify_watch inotify_watch.c && ./inotify_watch")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Test it: in another terminal run touch /tmp/watch_dir/hello.txt — the watcher will print CREATE then CLOSE_WRITE.")
                }
            }
        }
    }
}
