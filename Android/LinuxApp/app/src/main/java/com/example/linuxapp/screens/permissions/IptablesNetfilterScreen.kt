package com.example.linuxapp.screens.permissions

import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.linuxapp.screens.kernel.BodyText
import com.example.linuxapp.screens.kernel.CodeBlock
import com.example.linuxapp.screens.kernel.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IptablesNetfilterScreen(onBack: () -> Unit) {
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "iptables & netfilter",
                        color = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 12.dp)
        ) {
            item { Spacer(Modifier.height(8.dp)) }

            // Section 1: What Is Netfilter?
            item {
                SectionCard(title = "What Is Netfilter?") {
                    BodyText(
                        "Netfilter is a framework built into the Linux kernel that provides hooks at " +
                        "well-defined points in the network stack. Code running at these hooks can " +
                        "inspect, modify, drop, or accept every packet that passes through the system."
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText(
                        "Netfilter underpins all of Linux's packet-processing userspace tools:\n" +
                        "• iptables / ip6tables — the classic rule-based firewall front-end\n" +
                        "• nftables — the modern replacement for iptables\n" +
                        "• ipset — efficient set-based matching (IP ranges, port sets)\n" +
                        "• conntrack — connection tracking (stateful firewall)\n" +
                        "• NAT — Network Address Translation"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText(
                        "Kernel modules can also register directly with Netfilter via the kernel API " +
                        "(nf_register_net_hook) without going through iptables at all — giving full " +
                        "programmatic control over packet flow."
                    )
                }
            }

            // Section 2: Managing Rules with iptables
            item {
                SectionCard(title = "Managing Rules with iptables") {
                    BodyText(
                        "iptables organises rules into tables and chains:\n\n" +
                        "Tables:\n" +
                        "  filter  — default table; decides ACCEPT/DROP (chains: INPUT, OUTPUT, FORWARD)\n" +
                        "  nat     — address translation (chains: PREROUTING, OUTPUT, POSTROUTING)\n" +
                        "  mangle  — packet modification (all five chains)\n\n" +
                        "Common targets (what to do with a matching packet):\n" +
                        "  ACCEPT  — let the packet through\n" +
                        "  DROP    — silently discard it\n" +
                        "  REJECT  — discard and send an ICMP error / TCP-RST back to the sender\n" +
                        "  LOG     — log the packet to the kernel log and continue matching"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("Add a rule (append to end of chain):")
                    CodeBlock("iptables -A CHAIN [match options] -j TARGET")
                    BodyText("Insert a rule at a specific position (1 = top):")
                    CodeBlock("iptables -I CHAIN [rule-number] [match options] -j TARGET")
                    BodyText("Delete a rule by specification:")
                    CodeBlock("iptables -D CHAIN [match options] -j TARGET")
                    BodyText("Delete a rule by its line number (see --line-numbers below):")
                    CodeBlock("iptables -D CHAIN rule-number")
                    BodyText("List rules (verbose, with line numbers):")
                    CodeBlock("iptables -L [CHAIN] -v --line-numbers")
                    BodyText("Flush (delete all rules in a chain or all chains):")
                    CodeBlock(
                        "iptables -F CHAIN   # flush one chain\n" +
                        "iptables -F         # flush all chains in the filter table"
                    )
                    BodyText("Persist rules across reboots:")
                    CodeBlock(
                        "iptables-save > /etc/iptables/rules.v4\n" +
                        "iptables-restore < /etc/iptables/rules.v4"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("Example rules:")
                    CodeBlock(
                        "# Block all incoming TCP connections to port 8080\n" +
                        "iptables -A INPUT -p tcp --dport 8080 -j DROP\n\n" +
                        "# Block all outgoing TCP connections to port 443\n" +
                        "iptables -A OUTPUT -p tcp --dport 443 -j DROP\n\n" +
                        "# Allow already-established or related traffic (stateful)\n" +
                        "iptables -A INPUT -m state --state ESTABLISHED,RELATED -j ACCEPT\n\n" +
                        "# Delete the first rule above by its specification\n" +
                        "iptables -D INPUT -p tcp --dport 8080 -j DROP"
                    )
                }
            }

            // Section 3: Netfilter Kernel API
            item {
                SectionCard(title = "Netfilter Kernel API") {
                    BodyText(
                        "A loadable kernel module can register its own hook function directly with " +
                        "Netfilter — without using iptables. This gives the module full control: " +
                        "it can inspect every packet, modify headers, or block traffic based on " +
                        "arbitrary logic."
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("Required headers:")
                    CodeBlock(
                        "#include <linux/netfilter.h>\n" +
                        "#include <linux/netfilter_ipv4.h>\n" +
                        "#include <linux/ip.h>\n" +
                        "#include <linux/tcp.h>"
                    )
                    BodyText("Register / unregister a single hook:")
                    CodeBlock(
                        "// Returns 0 on success, negative errno on failure\n" +
                        "int nf_register_net_hook(struct net *net,\n" +
                        "                         struct nf_hook_ops *ops);\n\n" +
                        "void nf_unregister_net_hook(struct net *net,\n" +
                        "                            struct nf_hook_ops *ops);"
                    )
                    BodyText("Register / unregister multiple hooks at once:")
                    CodeBlock(
                        "int  nf_register_net_hooks(struct net *net,\n" +
                        "                           struct nf_hook_ops *reg,\n" +
                        "                           unsigned int n);\n\n" +
                        "void nf_unregister_net_hooks(struct net *net,\n" +
                        "                             struct nf_hook_ops *reg,\n" +
                        "                             unsigned int n);"
                    )
                    BodyText("Pass &init_net as the net argument for the default network namespace.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("The nf_hook_ops struct describes a single hook registration:")
                    CodeBlock(
                        "struct nf_hook_ops {\n" +
                        "    nf_hookfn         *hook;     // your callback function\n" +
                        "    struct net_device *dev;      // optional: restrict to one interface\n" +
                        "    void              *priv;     // private data passed to the callback\n" +
                        "    u_int8_t           pf;       // protocol family:\n" +
                        "                                 //   NFPROTO_IPV4, NFPROTO_IPV6\n" +
                        "    unsigned int       hooknum;  // hook point (see next section)\n" +
                        "    int                priority; // order among hooks at the same point\n" +
                        "                                 //   NF_IP_PRI_DEFAULT (0) is typical\n" +
                        "};"
                    )
                    BodyText("Hook callback signature:")
                    CodeBlock(
                        "unsigned int my_hook(void *priv,\n" +
                        "                     struct sk_buff *skb,\n" +
                        "                     const struct nf_hook_state *state);"
                    )
                    BodyText(
                        "skb (socket buffer) is the packet. Read IP/TCP headers with ip_hdr(skb) " +
                        "and tcp_hdr(skb). Return one of the verdict values described below."
                    )
                }
            }

            // Section 4: Hook Points
            item {
                SectionCard(title = "Hook Points") {
                    BodyText(
                        "For IPv4 (pf = NFPROTO_IPV4) there are five hook points. Each fires at a " +
                        "different stage of the packet's journey through the kernel:"
                    )
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "NF_INET_PRE_ROUTING\n" +
                        "  Fires as soon as an incoming packet arrives, before the routing\n" +
                        "  decision is made. Used for DNAT (destination NAT).\n\n" +
                        "NF_INET_LOCAL_IN\n" +
                        "  After routing, for packets destined for a process on this host.\n" +
                        "  This is where INPUT chain rules in iptables fire.\n\n" +
                        "NF_INET_FORWARD\n" +
                        "  For packets being routed through this host to another destination.\n" +
                        "  This is where FORWARD chain rules fire.\n\n" +
                        "NF_INET_LOCAL_OUT\n" +
                        "  For locally generated outgoing packets, before routing.\n" +
                        "  This is where OUTPUT chain rules fire.\n\n" +
                        "NF_INET_POST_ROUTING\n" +
                        "  For all outgoing packets (forwarded or local), after routing,\n" +
                        "  just before they leave the host. Used for SNAT / masquerade."
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("Packet flow diagram (simplified):")
                    CodeBlock(
                        "Incoming packet\n" +
                        "  └─► PRE_ROUTING\n" +
                        "        ├─► (destined for this host) LOCAL_IN ──► process\n" +
                        "        └─► (to be forwarded)        FORWARD\n" +
                        "                                         └─► POST_ROUTING ──► wire\n\n" +
                        "Outgoing packet (from local process)\n" +
                        "  └─► LOCAL_OUT ──► POST_ROUTING ──► wire"
                    )
                }
            }

            // Section 5: Packet Verdicts
            item {
                SectionCard(title = "Packet Verdicts") {
                    BodyText(
                        "The hook callback returns an unsigned int verdict that tells the kernel " +
                        "what to do next with the packet:"
                    )
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "NF_ACCEPT\n" +
                        "  Let the packet continue through the normal network stack.\n\n" +
                        "NF_DROP\n" +
                        "  Silently discard the packet. No ICMP or RST is sent to the sender.\n\n" +
                        "NF_STOLEN\n" +
                        "  The hook takes ownership of the skb. The kernel stops processing it.\n" +
                        "  The module is responsible for freeing the sk_buff.\n\n" +
                        "NF_QUEUE\n" +
                        "  Queue the packet to userspace via nfnetlink_queue for further\n" +
                        "  processing or verdict by a userspace daemon.\n\n" +
                        "NF_REPEAT\n" +
                        "  Call this same hook again on the same packet."
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText(
                        "The most commonly used verdicts in a simple firewall module are " +
                        "NF_ACCEPT (pass) and NF_DROP (block)."
                    )
                }
            }

            // Section 6: Example — Block by Port in a Kernel Module
            item {
                SectionCard(title = "Example: Block by Port in a Kernel Module") {
                    BodyText(
                        "A complete loadable kernel module that:\n" +
                        "• Blocks all incoming TCP connections to port 8080\n" +
                        "• Blocks all outgoing TCP connections to port 443\n\n" +
                        "Both hooks are registered and unregistered together using the " +
                        "nf_register_net_hooks / nf_unregister_net_hooks variants."
                    )
                    CodeBlock(
                        "#include <linux/module.h>\n" +
                        "#include <linux/netfilter.h>\n" +
                        "#include <linux/netfilter_ipv4.h>\n" +
                        "#include <linux/ip.h>\n" +
                        "#include <linux/tcp.h>\n\n" +
                        "static struct nf_hook_ops nf_ops[2];\n\n" +
                        "/* Block incoming TCP to port 8080 */\n" +
                        "static unsigned int block_input(void *priv,\n" +
                        "                                struct sk_buff *skb,\n" +
                        "                                const struct nf_hook_state *state)\n" +
                        "{\n" +
                        "    struct iphdr  *iph;\n" +
                        "    struct tcphdr *tcph;\n\n" +
                        "    if (!skb) return NF_ACCEPT;\n\n" +
                        "    iph = ip_hdr(skb);\n" +
                        "    if (iph->protocol != IPPROTO_TCP) return NF_ACCEPT;\n\n" +
                        "    tcph = tcp_hdr(skb);\n" +
                        "    if (ntohs(tcph->dest) == 8080) {\n" +
                        "        pr_info(\"netfilter: dropping incoming TCP:8080\\n\");\n" +
                        "        return NF_DROP;\n" +
                        "    }\n" +
                        "    return NF_ACCEPT;\n" +
                        "}\n\n" +
                        "/* Block outgoing TCP to port 443 */\n" +
                        "static unsigned int block_output(void *priv,\n" +
                        "                                 struct sk_buff *skb,\n" +
                        "                                 const struct nf_hook_state *state)\n" +
                        "{\n" +
                        "    struct iphdr  *iph;\n" +
                        "    struct tcphdr *tcph;\n\n" +
                        "    if (!skb) return NF_ACCEPT;\n\n" +
                        "    iph = ip_hdr(skb);\n" +
                        "    if (iph->protocol != IPPROTO_TCP) return NF_ACCEPT;\n\n" +
                        "    tcph = tcp_hdr(skb);\n" +
                        "    if (ntohs(tcph->dest) == 443) {\n" +
                        "        pr_info(\"netfilter: dropping outgoing TCP:443\\n\");\n" +
                        "        return NF_DROP;\n" +
                        "    }\n" +
                        "    return NF_ACCEPT;\n" +
                        "}\n\n" +
                        "static int __init nf_example_init(void)\n" +
                        "{\n" +
                        "    nf_ops[0].hook     = block_input;\n" +
                        "    nf_ops[0].pf       = NFPROTO_IPV4;\n" +
                        "    nf_ops[0].hooknum  = NF_INET_LOCAL_IN;\n" +
                        "    nf_ops[0].priority = NF_IP_PRI_DEFAULT;\n\n" +
                        "    nf_ops[1].hook     = block_output;\n" +
                        "    nf_ops[1].pf       = NFPROTO_IPV4;\n" +
                        "    nf_ops[1].hooknum  = NF_INET_LOCAL_OUT;\n" +
                        "    nf_ops[1].priority = NF_IP_PRI_DEFAULT;\n\n" +
                        "    return nf_register_net_hooks(&init_net, nf_ops,\n" +
                        "                                 ARRAY_SIZE(nf_ops));\n" +
                        "}\n\n" +
                        "static void __exit nf_example_exit(void)\n" +
                        "{\n" +
                        "    nf_unregister_net_hooks(&init_net, nf_ops,\n" +
                        "                            ARRAY_SIZE(nf_ops));\n" +
                        "}\n\n" +
                        "module_init(nf_example_init);\n" +
                        "module_exit(nf_example_exit);\n" +
                        "MODULE_LICENSE(\"GPL\");"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText(
                        "NF_INET_LOCAL_IN fires for packets arriving at this host — " +
                        "suitable for blocking incoming connections (tcph->dest is the destination port). " +
                        "NF_INET_LOCAL_OUT fires for packets generated by this host — " +
                        "suitable for blocking outgoing connections (tcph->dest is the remote port)."
                    )
                }
            }

            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}
