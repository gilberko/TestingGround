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
fun MountNamespaceScreen(onBack: () -> Unit) {
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Mount Namespace",
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
                SectionCard(title = "What Mount Namespace Does") {
                    BodyText("A mount namespace isolates the filesystem mount table. Each namespace has its own independent view of which filesystems are mounted where.")
                    BodyText("A mount or umount operation inside one namespace does not affect any other namespace. This is the mechanism by which containers each get their own private root filesystem.")
                    BodyText("Note: CLONE_NEWNS is the oldest namespace flag — it predates the 'mount namespace' name. NS literally meant 'namespace' when only one type existed.")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "Mounting Inside a Namespace") {
                    BodyText("You can enter a process's mount namespace from the host and perform mounts that are visible only there:")
                    CodeBlock("""# Mount something visible only inside a container:
nsenter -t <pid> -m -- mount /dev/sdb1 /mnt/data
# The host and all other containers are unaffected

# Or with setns() from C:
int fd = open("/proc/<pid>/ns/mnt", O_RDONLY);
setns(fd, CLONE_NEWNS);
// now mount() calls operate in that namespace""")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "OverlayFS: Layered Filesystems for Containers") {
                    BodyText("OverlayFS (overlayfs) is a union filesystem that merges two directory trees into one unified view. It is the default storage driver used by Docker and containerd.")
                    BodyText("lower — read-only base layer(s). These are the container image layers, shared across many containers.")
                    BodyText("upper — per-container writable layer. All writes land here (copy-on-write: the first write to a file copies it from lower to upper first).")
                    BodyText("work — an internal scratch directory on the same filesystem as upper, used by the kernel for atomic operations.")
                    BodyText("merged — the unified view shown to the container: reads come from upper if present, otherwise from lower.")
                    CodeBlock("""mount -t overlay overlay \\
  -o lowerdir=/image/layer2:/image/layer1,\\
     upperdir=/container/rw,\\
     workdir=/container/work \\
  /container/rootfs

# Multiple lower layers (colon-separated, rightmost = bottom):
# layer1 is the base OS, layer2 is the app layer
# All containers share the same lower layers (read-only, cached)
# Each container gets its own upper (isolated writes)""")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "Bind Mounts and Volume Mounts") {
                    BodyText("A bind mount makes a directory (or file) from one location appear at another path — inside the namespace's mount tree:")
                    CodeBlock("""mount --bind /host/data /container/rootfs/data
# /host/data is now visible as /data inside the container

# Docker: -v /host/dir:/container/dir uses bind mounts internally""")
                    BodyText("The container sees the host directory at the container path, while the host still sees it at the original path. Both paths refer to the same underlying inode.")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "Mount Propagation") {
                    BodyText("The kernel controls whether mount events propagate between namespaces via mount propagation types:")
                    BodyText("MS_PRIVATE — mounts and unmounts do not propagate across the boundary. This is the default for containers.")
                    BodyText("MS_SHARED — mount events propagate to all peers in the same peer group (bidirectional).")
                    BodyText("MS_SLAVE — receives propagation from its master peer group, but does not send propagation back.")
                    BodyText("MS_UNBINDABLE — like MS_PRIVATE, but also cannot be bind-mounted elsewhere.")
                    CodeBlock("""// Set propagation from C:
mount(NULL, "/", NULL, MS_REC | MS_PRIVATE, NULL);
// Make the whole mount tree private (typical container setup)""")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
