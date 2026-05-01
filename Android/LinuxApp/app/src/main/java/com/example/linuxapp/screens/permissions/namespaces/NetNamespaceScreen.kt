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
fun NetNamespaceScreen(onBack: () -> Unit) {
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Net Namespace",
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
                SectionCard(title = "What Network Namespace Does") {
                    BodyText("A network namespace isolates the entire network stack. Each namespace has its own independent set of network resources with no sharing between them.")
                    BodyText("Two containers can both bind to port 80 without conflict — they each have their own port number space inside separate network namespaces.")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "Per-Namespace Resources") {
                    BodyText("Network interfaces — lo, eth0, and all other interfaces are per-namespace. An interface belongs to exactly one namespace at a time.")
                    BodyText("Routing tables — each namespace has its own routing table (ip route shows per-namespace routes).")
                    BodyText("iptables / nftables rules — firewall rules are completely isolated per namespace.")
                    BodyText("Sockets and port bindings — a listen on port 8080 in one namespace does not conflict with the same port in another.")
                    BodyText("/proc/net/* — all network statistics and state files are per-namespace.")
                    BodyText("Connection tracking — each namespace tracks its own established connections independently.")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "struct net — The Namespace Root Object") {
                    CodeBlock("""// The root kernel struct for per-namespace networking:
struct net {
    refcount_t       passive;        // reference count
    struct list_head dev_base_head;  // network devices in this ns
    struct netns_ipv4 ipv4;          // IPv4 state (routes, etc.)
    struct netns_ipv6 ipv6;          // IPv6 state
    // ... socket tables, conntrack, etc.
};

// Accessed via:
// task_struct -> nsproxy -> net_ns -> struct net""")
                    BodyText("When a process creates a socket, the kernel associates it with the process's net_ns. All subsequent operations on that socket are scoped to that namespace.")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "Typical Container Networking Setup") {
                    BodyText("Container runtimes typically connect a container to the host with a virtual ethernet pair (veth):")
                    BodyText("1. Host creates a veth pair: veth0 (host side) and veth1 (container side).")
                    BodyText("2. veth1 is moved into the container's network namespace: ip link set veth1 netns <pid>")
                    BodyText("3. Inside the container, veth1 is renamed to eth0 and assigned an IP address.")
                    BodyText("4. On the host, veth0 is attached to a bridge (e.g., docker0) or routing is configured to forward traffic between namespaces.")
                    BodyText("The loopback interface (lo) must be explicitly brought up inside the container namespace — it is not automatically active in a new namespace.")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
