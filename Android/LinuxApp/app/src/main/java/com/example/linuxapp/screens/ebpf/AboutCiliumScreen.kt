package com.example.linuxapp.screens.ebpf

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
fun AboutCiliumScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "About Cilium",
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
                SectionCard(title = "What Is Cilium") {
                    BodyText("Cilium is an open-source project that provides networking, security, and observability for containerised and cloud-native workloads. It is primarily used as a CNI (Container Network Interface) plugin for Kubernetes clusters.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Key facts:")
                    CodeBlock(
                        "Website:   cilium.io\n" +
                        "Source:    github.com/cilium/cilium\n" +
                        "License:   Apache 2.0 (NOT GPL)\n" +
                        "Governance: CNCF graduated project\n" +
                        "Founded:   Thomas Graf & Joe Stringer at Isovalent\n" +
                        "           (Isovalent acquired by Cisco, 2023)\n" +
                        "Language:  Go (control plane) + C (eBPF dataplane)"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("The name comes from biology: cilia are tiny hair-like structures that line surfaces and help move things along — an analogy for how Cilium manages network traffic flow in a cluster.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "The eBPF Connection") {
                    BodyText("Cilium replaces the entire kernel networking dataplane (traditionally iptables + conntrack) with eBPF programs compiled at startup and attached to kernel hooks:")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock(
                        "XDP (eXpress Data Path)\n" +
                        "  -> Attached to NIC drivers\n" +
                        "  -> Fast packet drop/redirect for DDoS mitigation\n" +
                        "     and early load-balancer decisions\n" +
                        "  -> Runs BEFORE sk_buff allocation (~10ns latency)\n" +
                        "\n" +
                        "TC (Traffic Control) BPF\n" +
                        "  -> Attached to ingress + egress of each veth pair\n" +
                        "  -> L3/L4 network policy enforcement\n" +
                        "  -> NAT, DNAT, load balancing for Kubernetes Services\n" +
                        "  -> Packet marking for WireGuard/IPsec encryption\n" +
                        "\n" +
                        "Socket-level BPF (SK_MSG / SK_SKB)\n" +
                        "  -> Intercepts socket send/recv without leaving the host\n" +
                        "  -> Transparent L7 proxy (sidecar-less service mesh)\n" +
                        "  -> Sockmap for accelerated local socket forwarding\n" +
                        "\n" +
                        "BPF Maps\n" +
                        "  -> Connection tracking (replaces nf_conntrack)\n" +
                        "  -> Service endpoint tables\n" +
                        "  -> Policy tables (IP + port allow/deny lists)\n" +
                        "  -> LPM trie for CIDR lookups"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Why eBPF Instead of iptables") {
                    BodyText("Traditional Kubernetes uses kube-proxy with iptables rules to implement service load balancing. This approach has fundamental scalability problems:")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock(
                        "iptables  ->  O(n) rule traversal per packet\n" +
                        "              For 10,000 services: ~10k rules checked\n" +
                        "              Rule insertion: O(n) rewrite of entire ruleset\n" +
                        "              conntrack table: memory + CPU for every flow\n" +
                        "\n" +
                        "Cilium BPF -> O(1) hash map lookup per packet\n" +
                        "              Atomic map updates (no ruleset rewrite)\n" +
                        "              conntrack in BPF maps (kernel-controlled)\n" +
                        "              No iptables involved at all"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("At 10,000+ services, iptables-based kube-proxy consumes measurable CPU on every packet. Cilium's BPF hash lookup is constant time regardless of service count. Benchmarks show 5-10x reduction in latency and CPU at scale.")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "What Cilium Provides") {
                    BodyText("Networking:")
                    CodeBlock(
                        "- Pod-to-pod routing (direct or overlay: VXLAN/Geneve)\n" +
                        "- kube-proxy replacement (BPF-based service load balancing)\n" +
                        "- BGP Control Plane: advertise pod CIDRs to routers\n" +
                        "- LB-IPAM: allocate external IPs for LoadBalancer services\n" +
                        "- Cluster Mesh: cross-cluster service discovery & LB"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Security:")
                    CodeBlock(
                        "- L3/L4 network policy (IP + port)\n" +
                        "- L7 network policy (HTTP path, gRPC method, Kafka topic)\n" +
                        "- CiliumNetworkPolicy CRD — richer than K8s NetworkPolicy\n" +
                        "- Identity-based policy (pod labels, not just IPs)\n" +
                        "- Transparent encryption: WireGuard or IPsec between nodes"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Observability (via Hubble):")
                    CodeBlock(
                        "- L3/L4/L7 flow visibility for every pod\n" +
                        "- No sampling — exact flow records via eBPF ring buffers\n" +
                        "- hubble observe CLI + Hubble UI web dashboard\n" +
                        "- Prometheus metrics export\n" +
                        "- Service map / network topology view"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Hubble: Observability Layer") {
                    BodyText("Hubble is Cilium's built-in observability component. It uses eBPF ring buffer maps to capture every L3/L4/L7 network flow passing through the cluster.")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock(
                        "# Install Hubble CLI\n" +
                        "HUBBLE_VERSION=\$(curl -s https://raw.githubusercontent.com/\\\n" +
                        "  cilium/hubble/master/stable.txt)\n" +
                        "\n" +
                        "# Enable Hubble in Cilium\n" +
                        "helm upgrade cilium cilium/cilium \\\n" +
                        "  --set hubble.relay.enabled=true \\\n" +
                        "  --set hubble.ui.enabled=true\n" +
                        "\n" +
                        "# Watch all flows in real time\n" +
                        "hubble observe --follow\n" +
                        "\n" +
                        "# Only show dropped packets\n" +
                        "hubble observe --verdict DROPPED\n" +
                        "\n" +
                        "# Filter by namespace\n" +
                        "hubble observe -n kube-system --follow\n" +
                        "\n" +
                        "# Show L7 HTTP flows\n" +
                        "hubble observe --protocol http --follow"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Hubble UI (port-forward to access):")
                    CodeBlock("kubectl port-forward -n kube-system svc/hubble-ui 12000:80\n# Open http://localhost:12000 in a browser")
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Installation & Basic Usage") {
                    BodyText("Install the Cilium CLI (Linux/macOS):")
                    CodeBlock(
                        "CILIUM_CLI_VERSION=\$(curl -s https://raw.githubusercontent.com/\\\n" +
                        "  cilium/cilium-cli/main/stable.txt)\n" +
                        "curl -L --remote-name-all https://github.com/cilium/\\\n" +
                        "  cilium-cli/releases/download/\${CILIUM_CLI_VERSION}/\\\n" +
                        "  cilium-linux-amd64.tar.gz\n" +
                        "sudo tar xzvf cilium-linux-amd64.tar.gz -C /usr/local/bin"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Install into a Kubernetes cluster using Helm:")
                    CodeBlock(
                        "helm repo add cilium https://helm.cilium.io/\n" +
                        "helm repo update\n" +
                        "\n" +
                        "# Basic install\n" +
                        "helm install cilium cilium/cilium \\\n" +
                        "  --version 1.15.6 \\\n" +
                        "  --namespace kube-system\n" +
                        "\n" +
                        "# Replace kube-proxy entirely\n" +
                        "helm install cilium cilium/cilium \\\n" +
                        "  --version 1.15.6 \\\n" +
                        "  --namespace kube-system \\\n" +
                        "  --set kubeProxyReplacement=true"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Check health and run the connectivity test suite:")
                    CodeBlock(
                        "# Show status of all Cilium pods\n" +
                        "cilium status\n" +
                        "\n" +
                        "# Run the full connectivity test (deploys test pods)\n" +
                        "cilium connectivity test\n" +
                        "\n" +
                        "# Show Cilium version and config\n" +
                        "cilium version\n" +
                        "cilium config view"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
            item {
                SectionCard(title = "Open Source and Licensing") {
                    BodyText("Cilium is licensed under Apache 2.0 — a permissive open-source license. This means:")
                    Spacer(Modifier.height(8.dp))
                    CodeBlock(
                        "Apache 2.0 (Cilium Go control plane + CRDs):\n" +
                        "  - Use, copy, modify, distribute freely\n" +
                        "  - No copyleft requirement — your code can stay private\n" +
                        "  - Explicit patent grant from all contributors\n" +
                        "  - Must preserve copyright notices"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("The eBPF programs compiled into the kernel are dual-licensed:")
                    CodeBlock(
                        "GPL-2.0 OR BSD-2-Clause (Cilium eBPF C programs):\n" +
                        "  - Must be GPL for kernel loading (GPL helpers needed)\n" +
                        "  - BSD-2-Clause gives extra permissive option\n" +
                        "  - The dual license is a common pattern for BPF programs\n" +
                        "    that need both kernel access and commercial use"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Governance and support:")
                    CodeBlock(
                        "CNCF:      Graduated project (highest maturity level)\n" +
                        "           Same tier as Kubernetes, Prometheus, Envoy\n" +
                        "Community: github.com/cilium/cilium, Slack cilium.io/slack\n" +
                        "Support:   Isovalent (Cisco) offers enterprise support\n" +
                        "           Open-source community support on GitHub/Slack"
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}
