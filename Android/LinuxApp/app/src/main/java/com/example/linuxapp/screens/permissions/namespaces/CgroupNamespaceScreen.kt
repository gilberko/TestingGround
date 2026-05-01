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
fun CgroupNamespaceScreen(onBack: () -> Unit) {
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Cgroup Namespace",
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
                SectionCard(title = "What Cgroup Namespace Does") {
                    BodyText("A cgroup namespace isolates the view of the cgroup hierarchy. Inside a cgroup namespace, the container's own cgroup appears as the root '/' of the hierarchy — processes cannot see the host cgroup paths above them.")
                    BodyText("/proc/<pid>/cgroup shows paths relative to the cgroup namespace root, not the global root. This prevents information leakage and gives containers a consistent view of their own resource limits.")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "Effect on /proc") {
                    CodeBlock("""# On the host, a container process shows the full path:
cat /proc/<container-pid>/cgroup
# 0::/system.slice/docker-abc123.scope/

# From inside the container (cgroup namespace active):
cat /proc/self/cgroup
# 0::/
# The container's cgroup appears as the root — the host
# hierarchy above it is hidden.""")
                    BodyText("This also prevents a container from learning what other containers or services are running on the host by inspecting cgroup names.")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "struct cgroup_namespace") {
                    CodeBlock("""struct cgroup_namespace {
    struct ns_common      ns;
    struct user_namespace *user_ns;
    struct ucounts        *ucounts;
    struct css_set        *root_cset; // the cgroup that appears as "/"
};

// Accessed via:
// task_struct -> nsproxy -> cgroup_ns -> root_cset""")
                    BodyText("root_cset is the css_set (cgroup subsystem state set) that becomes the virtual root '/' inside the namespace. It corresponds to the actual cgroup the namespace was created from.")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "Interaction with cgroupfs") {
                    BodyText("When cgroupfs is mounted inside the container (e.g., /sys/fs/cgroup), it shows the namespace-relative hierarchy — paths are relative to the cgroup namespace root.")
                    BodyText("Resource limits set by the host (CPU, memory) still apply to the container — the cgroup namespace only changes the VIEW, not the enforcement.")
                    CodeBlock("""# Create a new cgroup namespace:
unshare --cgroup -- bash

# Mount cgroupfs inside to see the namespace-relative view:
mount -t cgroup2 cgroup2 /sys/fs/cgroup
ls /sys/fs/cgroup  # shows the namespace root as /""")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
