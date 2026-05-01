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
fun UtsNamespaceScreen(onBack: () -> Unit) {
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "UTS Namespace",
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
                SectionCard(title = "What UTS Namespace Does") {
                    BodyText("UTS stands for Unix Time Sharing — a historical UNIX name for the struct that holds system identity fields.")
                    BodyText("A UTS namespace isolates two system identifiers: the hostname (nodename) and the NIS domain name. Changing either inside a UTS namespace has no effect on the host or other namespaces.")
                    BodyText("This lets each container report its own hostname (e.g., web-server-1) independently of the host machine's name.")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "struct uts_namespace") {
                    CodeBlock("""struct uts_namespace {
    struct new_utsname name;
    // reference counting, user_ns, etc.
};

struct new_utsname {
    char sysname[65];    // always "Linux"
    char nodename[65];   // the hostname — what 'uname -n' returns
    char release[65];    // kernel version string
    char version[65];    // kernel build timestamp
    char machine[65];    // architecture: x86_64, aarch64, etc.
    char domainname[65]; // NIS domain — what 'uname --domainname' returns
};

// Accessed via:
// task_struct -> nsproxy -> uts_ns -> name.nodename""")
                    BodyText("The sysname, release, version, and machine fields are NOT isolated — they always reflect the running kernel. Only nodename and domainname are per-namespace.")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "Usage") {
                    CodeBlock("""# Create a new UTS namespace and set a custom hostname:
unshare --uts bash
hostname mycontainer
hostname   # -> mycontainer

# On the host (in a separate terminal):
hostname   # -> unchanged, still the host's name

# From C, using sethostname() inside the new namespace:
unshare(CLONE_NEWUTS);
sethostname("mycontainer", 11);""")
                    BodyText("Container runtimes set the hostname by calling sethostname() after entering the new UTS namespace, before starting the container's init process.")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
