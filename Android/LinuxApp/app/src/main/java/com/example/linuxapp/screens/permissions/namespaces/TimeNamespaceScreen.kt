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
fun TimeNamespaceScreen(onBack: () -> Unit) {
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Time Namespace",
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
                SectionCard(title = "What Time Namespace Does") {
                    BodyText("Added in Linux 5.6 (March 2020). A time namespace isolates CLOCK_BOOTTIME and CLOCK_MONOTONIC by applying a per-namespace offset to readings of those clocks.")
                    BodyText("Each namespace can present a different virtual boot time. The primary use case is container checkpoint/restore with CRIU: when a container is migrated to a different host, the host will have a different real boot time. Without a time namespace, the container's monotonic timestamps would be wrong. With a time namespace, an offset is applied to keep them consistent.")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "What It Does NOT Isolate") {
                    BodyText("CLOCK_REALTIME (the wall clock) is NOT isolated by a time namespace. All namespaces share the same wall clock — you cannot give a container a fake date/time this way.")
                    BodyText("Only the two monotonic clocks (CLOCK_BOOTTIME and CLOCK_MONOTONIC) get per-namespace offsets. These clocks measure time elapsed since boot, not absolute calendar time.")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "struct time_namespace") {
                    CodeBlock("""// nsproxy has TWO time namespace pointers (mirroring PID ns):
struct time_namespace *time_ns;               // this task's time ns
struct time_namespace *time_ns_for_children;  // for new children

struct time_namespace {
    struct timens_offsets offsets; // clock offsets for this ns
    struct user_namespace *user_ns;
    // reference count, etc.
};

struct timens_offsets {
    struct timespec64 monotonic; // offset added to CLOCK_MONOTONIC
    struct timespec64 boottime;  // offset added to CLOCK_BOOTTIME
};

// Accessed via:
// task_struct -> nsproxy -> time_ns -> offsets""")
                    BodyText("Like pid_ns_for_children, time_ns_for_children is for new children. The split exists because a process that creates a new time namespace does so before exec(), so the parent needs to set offsets and then exec the child into the new namespace.")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "Usage") {
                    CodeBlock("""# Create a new time namespace:
unshare --time -- bash

# Before exec, write offsets to /proc/self/timens_offsets:
# Format: "<clock-id> <seconds> <nanoseconds>"
# clock-id: monotonic or boottime
echo "monotonic 100 0" > /proc/self/timens_offsets
# CLOCK_MONOTONIC in this namespace now reads 100 seconds ahead

echo "boottime -3600 0" > /proc/self/timens_offsets
# CLOCK_BOOTTIME offset: pretend the system booted 1 hour later""")
                    BodyText("timens_offsets can only be written before the first process in the namespace calls exec(). After that, the offsets are frozen.")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
