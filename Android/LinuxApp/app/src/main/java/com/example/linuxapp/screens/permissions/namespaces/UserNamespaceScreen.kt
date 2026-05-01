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
fun UserNamespaceScreen(onBack: () -> Unit) {
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "User Namespace",
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
                SectionCard(title = "What User Namespace Does") {
                    BodyText("A user namespace maps UIDs and GIDs between the namespace and its parent. A process that appears as UID 0 (root) inside a user namespace can be a completely unprivileged user on the host.")
                    BodyText("This enables rootless containers: an ordinary user can run a container with full root inside, without needing any host root privileges. Used by rootless Docker and Podman.")
                    BodyText("User namespaces can nest; capabilities granted inside a namespace are bounded by what the parent namespace allows.")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "UID/GID Mapping") {
                    BodyText("The mapping is stored in /proc/<pid>/uid_map and /proc/<pid>/gid_map. Each line defines a range:")
                    CodeBlock("""# Format: inner_start  outer_start  count
cat /proc/<pid>/uid_map
# 0    1000    65536
# Inner UID 0 maps to outer UID 1000.
# Inner UID 1 maps to outer UID 1001. Etc. (65536 IDs total)

# The container runtime writes this after forking the child:
echo "0 1000 65536" > /proc/<child-pid>/uid_map
echo "deny"         > /proc/<child-pid>/setgroups  # required first
echo "0 1000 65536" > /proc/<child-pid>/gid_map""")
                    BodyText("UIDs outside the mapped range appear as 65534 (nobody) inside the namespace. Files on disk owned by unmapped UIDs appear as nobody.")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "Capabilities Inside a User Namespace") {
                    BodyText("A process with UID 0 inside a user namespace holds a full set of capabilities — but only scoped to resources that belong to that namespace.")
                    BodyText("For example, it can bind to port 80 inside the network namespace paired with the user namespace, but cannot affect host network interfaces.")
                    BodyText("This is the core of rootless container security: the container process has 'root' for its own namespaced resources, but is a restricted ordinary user to the host kernel.")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "struct user_namespace") {
                    CodeBlock("""struct user_namespace {
    struct uid_gid_map uid_map;   // UID mapping table
    struct uid_gid_map gid_map;   // GID mapping table
    struct uid_gid_map projid_map;
    struct user_namespace *parent; // parent namespace
    int    level;                  // nesting depth (root ns = 0)
    kuid_t owner;                  // creator's UID in parent ns
    kgid_t group;                  // creator's GID in parent ns
    // capability sets, etc.
};

// Accessed via:
// task_struct -> cred -> user_ns""")
                    BodyText("Unlike other namespaces, user_ns is not in nsproxy — it lives in the task's credentials (struct cred), because it controls who the process IS, not just what resources it sees.")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
