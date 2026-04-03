package com.example.linuxapp.screens.kernel

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NetDeviceScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Net Device",
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
                SectionCard(title = "What is a Net Device") {
                    BodyText("A network device driver exposes a network interface (like eth0 or lo) to the kernel's networking stack. The driver is responsible for transmitting and receiving sk_buff packets; the network stack handles TCP/IP above that.")
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """Application: send()/recv()
    ↓
Socket layer (TCP/UDP)
    ↓
IP layer (routing, fragmentation)
    ↓
net_device  ← your driver lives here
    ↓
Hardware (Ethernet, WiFi, virtual…)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The interface appears in ip link / ifconfig. The driver registers with alloc_netdev + register_netdev, and the kernel calls your ndo_* ops to open it, send packets, and query stats.")
                }
            }
            item {
                SectionCard(title = "Key Structures") {
                    BodyText("struct net_device_ops is the ops table you must fill in:")
                    CodeBlock(
                        """struct net_device_ops {
    /* Called when 'ip link set ethX up' */
    int  (*ndo_open)(struct net_device *dev);

    /* Called when 'ip link set ethX down' */
    int  (*ndo_stop)(struct net_device *dev);

    /* Called by the stack to send a packet.
       You own the skb until you call dev_kfree_skb(). */
    netdev_tx_t (*ndo_start_xmit)(struct sk_buff *skb,
                                   struct net_device *dev);

    /* Fill in rtnl_link_stats64 with TX/RX counters */
    void (*ndo_get_stats64)(struct net_device *dev,
                             struct rtnl_link_stats64 *stats);
    ...
};"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("For RX (receiving), call netif_rx(skb) to hand a packet up to the network stack. For high-throughput drivers, use NAPI (poll-based) instead of per-packet netif_rx.")
                }
            }
            item {
                SectionCard(title = "Loopback-style Driver") {
                    BodyText("A complete minimal virtual net device. Every packet sent to it is immediately received back — a software loopback.")
                    CodeBlock(
                        """#include <linux/netdevice.h>
#include <linux/etherdevice.h>
#include <linux/module.h>

struct mynet_priv {
    struct net_device_stats stats;
};

static netdev_tx_t mynet_xmit(struct sk_buff *skb,
                               struct net_device *dev)
{
    struct mynet_priv *priv = netdev_priv(dev);

    /* Update TX stats */
    priv->stats.tx_packets++;
    priv->stats.tx_bytes += skb->len;

    /* Loop the packet back: give it to our own RX path.
       skb->dev must point to the receiving interface. */
    skb->dev      = dev;
    skb->protocol = eth_type_trans(skb, dev);
    skb->ip_summed = CHECKSUM_UNNECESSARY;

    /* Hand the packet up to the network stack as an RX */
    netif_rx(skb);

    priv->stats.rx_packets++;
    priv->stats.rx_bytes += skb->len;
    return NETDEV_TX_OK;
}

static int mynet_open(struct net_device *dev)
{
    /* Allow the stack to call ndo_start_xmit */
    netif_start_queue(dev);
    return 0;
}

static int mynet_stop(struct net_device *dev)
{
    netif_stop_queue(dev);
    return 0;
}

static void mynet_get_stats64(struct net_device *dev,
                               struct rtnl_link_stats64 *s)
{
    struct mynet_priv *priv = netdev_priv(dev);
    s->tx_packets = priv->stats.tx_packets;
    s->tx_bytes   = priv->stats.tx_bytes;
    s->rx_packets = priv->stats.rx_packets;
    s->rx_bytes   = priv->stats.rx_bytes;
}

static const struct net_device_ops mynet_ops = {
    .ndo_open        = mynet_open,
    .ndo_stop        = mynet_stop,
    .ndo_start_xmit  = mynet_xmit,
    .ndo_get_stats64 = mynet_get_stats64,
};

static struct net_device *g_dev;

static void mynet_setup(struct net_device *dev)
{
    /* Use Ethernet frame format */
    ether_setup(dev);
    dev->netdev_ops = &mynet_ops;
    dev->flags     |= IFF_NOARP;   /* no ARP needed */
    dev->features  |= NETIF_F_HW_CSUM;

    /* Assign a random MAC address */
    eth_hw_addr_random(dev);
}

static int __init mynet_init(void)
{
    /* alloc_netdev(priv_size, name, name_assign_type, setup) */
    g_dev = alloc_netdev(sizeof(struct mynet_priv),
                         "mynet%d",
                         NET_NAME_ENUM,
                         mynet_setup);
    if (!g_dev) return -ENOMEM;

    int err = register_netdev(g_dev);
    if (err) { free_netdev(g_dev); return err; }

    pr_info("mynet: registered as %s\n", g_dev->name);
    return 0;
}

static void __exit mynet_exit(void)
{
    unregister_netdev(g_dev);
    free_netdev(g_dev);
    pr_info("mynet: removed\n");
}

module_init(mynet_init);
module_exit(mynet_exit);
MODULE_LICENSE("GPL");"""
                    )
                }
            }
            item {
                SectionCard(title = "Using the Driver") {
                    BodyText("After loading the module, the interface appears and can be configured like any other network device:")
                    CodeBlock(
                        """insmod mynet.ko

# See the interface
ip link show mynet0
# 3: mynet0: <BROADCAST,NOARP> mtu 1500 ...

# Bring it up and assign an IP
ip link set mynet0 up
ip addr add 10.0.99.1/24 dev mynet0

# Ping ourselves through the driver
ping -c 3 10.0.99.1

# Watch RX/TX counters climb
ip -s link show mynet0

# Remove
ip link set mynet0 down
rmmod mynet"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("For a real hardware driver, replace mynet_xmit with DMA to the NIC's TX ring, and call netif_rx() (or the NAPI poll function) from the interrupt handler when packets arrive.")
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
