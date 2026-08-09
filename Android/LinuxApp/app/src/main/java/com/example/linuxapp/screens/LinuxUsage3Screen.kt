package com.example.linuxapp.screens

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
fun LinuxUsage3Screen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Linux Usage 3",
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
                SectionCard(title = "systemctl — Managing Services") {
                    BodyText(
                        "systemd manages services (and other resources) as units. A service unit describes " +
                        "how to start, stop, and supervise one daemon, and is typically defined in a " +
                        "/etc/systemd/system/*.service or /usr/lib/systemd/system/*.service file. " +
                        "systemctl is the command-line tool for controlling those units."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Starting, stopping, and checking a service:")
                    CodeBlock(
                        """systemctl status <service>    # show state + recent log lines
systemctl start <service>     # start it now
systemctl stop <service>      # stop it now
systemctl restart <service>   # stop then start
systemctl reload <service>    # re-read config w/o restarting
                               #   (only if the service supports it)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "\"start\"/\"stop\" only affect the current boot. \"enable\"/\"disable\" control whether " +
                        "the service is started automatically at boot (they create/remove a symlink into a " +
                        "target's .wants/ directory) — enabling does not start it right now, and starting it " +
                        "does not make it survive a reboot. Combine both with --now."
                    )
                    CodeBlock(
                        """systemctl enable <service>       # start on future boots
systemctl disable <service>      # stop starting on boot
systemctl enable --now <service> # enable AND start immediately
systemctl disable --now <service># disable AND stop immediately"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Querying state (useful in scripts — these are silent, exit-code only):")
                    CodeBlock(
                        """systemctl is-active <service>    # "active" / "inactive" / "failed"
systemctl is-enabled <service>   # "enabled" / "disabled" / "static"
systemctl is-failed <service>    # true if the unit is in a failed state"""
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "systemctl — Inspecting the System") {
                    BodyText("Listing units and unit files:")
                    CodeBlock(
                        """systemctl list-units                 # active units, this boot
systemctl list-units --all           # include inactive/dead units too
systemctl list-units --failed        # only units that failed to start
systemctl list-unit-files            # every installed unit + enabled state
systemctl --type=service             # only service units"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "daemon-reload tells systemd to re-read unit files from disk. Run it after editing or " +
                        "adding a .service file — without it, systemctl keeps using the old, cached definition " +
                        "even though the file on disk has changed."
                    )
                    CodeBlock("systemctl daemon-reload")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "mask goes further than disable: it symlinks the unit file to /dev/null, so the " +
                        "service cannot be started even manually or as a dependency of another unit — useful " +
                        "for services you want to make completely unstartable, not just off by default."
                    )
                    CodeBlock(
                        """systemctl mask <service>      # make it impossible to start
systemctl unmask <service>    # undo mask"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Targets are systemd's replacement for SysV runlevels — a target is a unit that groups " +
                        "other units together (e.g. graphical.target pulls in multi-user.target plus the " +
                        "display manager). isolate switches to a target, stopping units not required by it."
                    )
                    CodeBlock(
                        """systemctl get-default                 # show the default boot target
systemctl set-default multi-user.target  # boot to text mode, no GUI
systemctl isolate rescue.target        # switch to single-user/rescue mode now"""
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "journalctl — Reading Logs") {
                    BodyText(
                        "systemd-journald collects log messages from the kernel, early boot, stdout/stderr of " +
                        "every service, and syslog into a structured binary journal (typically stored under " +
                        "/var/log/journal/). journalctl is the tool for querying it — it replaces (or " +
                        "supplements) tailing plain-text files like /var/log/syslog."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Basic usage:")
                    CodeBlock(
                        """journalctl                # every log entry, oldest first, pipe into a pager
journalctl -u <service>   # only entries from one unit (e.g. -u sshd)
journalctl -f              # follow — like `tail -f`, blocks and streams new entries
journalctl -k               # kernel messages only, this boot (dmesg equivalent)
journalctl -b               # only entries from the current boot
journalctl -b -1            # only entries from the previous boot
journalctl -e                # jump straight to the end of the output
journalctl -n 50            # show only the last 50 lines"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Flags combine naturally, e.g. following one service's live log:")
                    CodeBlock("journalctl -u nginx -f")
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "journalctl — Filtering and Maintenance") {
                    BodyText("Filtering by priority (syslog levels, most to least severe):")
                    CodeBlock(
                        """journalctl -p err            # err and everything more severe
                              #   (emerg, alert, crit, err)
journalctl -p warning        # warning and more severe
journalctl -u sshd -p err -b # combine: errors from sshd, this boot only"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Filtering by time — --since/--until accept natural-language expressions:")
                    CodeBlock(
                        """journalctl --since "1 hour ago"
journalctl --since "2026-08-09 09:00:00" --until "2026-08-09 10:00:00"
journalctl --since yesterday
journalctl -u nginx --since today --until now"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "The journal grows over time and is capped (by default a fraction of the filesystem " +
                        "it lives on). Check usage and trim it manually if needed:"
                    )
                    CodeBlock(
                        """journalctl --disk-usage           # how much space the journal is using
journalctl --vacuum-time=2weeks   # delete entries older than 2 weeks
journalctl --vacuum-size=500M     # shrink the journal down to 500MB"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Whether logs survive a reboot depends on Storage= in /etc/systemd/journald.conf: " +
                        "\"volatile\" keeps the journal only in /run (RAM, wiped on reboot); \"persistent\" " +
                        "keeps it under /var/log/journal (survives reboots); \"auto\" (the common default) " +
                        "uses /var/log/journal if that directory already exists, otherwise falls back to " +
                        "volatile."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "ip addr — Managing Addresses") {
                    BodyText(
                        "ip, part of iproute2, is the modern replacement for the older ifconfig/route/arp " +
                        "tools. ip addr (or the short form ip a) shows and manages IP addresses assigned to " +
                        "network interfaces."
                    )
                    CodeBlock(
                        """ip addr show          # all interfaces (ip a)
ip addr show eth0    # just one interface (ip a show eth0)
ip -4 addr show       # IPv4 addresses only
ip -6 addr show       # IPv6 addresses only"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Reading one line of output:")
                    CodeBlock(
                        """2: eth0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 ...
    inet 192.168.1.20/24 brd 192.168.1.255 scope global eth0

<UP,LOWER_UP>  — admin state UP and a carrier signal (cable plugged in)
inet .../24    — IPv4 address + CIDR prefix length (subnet mask)
scope global   — routable beyond this host (vs "scope link" = local-only)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Adding/removing an address is a live, in-memory change — it does not persist across " +
                        "reboots or interface restarts (for that, use a persistent tool like nmcli, below)."
                    )
                    CodeBlock(
                        """ip addr add 192.168.1.50/24 dev eth0
ip addr del 192.168.1.50/24 dev eth0"""
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "ip link — Managing Interfaces") {
                    BodyText(
                        "ip link (ip l) operates one layer down from ip addr — it manages the network " +
                        "interface (device) itself, rather than the addresses assigned to it."
                    )
                    CodeBlock(
                        """ip link show          # list all interfaces + their state/flags
ip link show eth0    # just one interface"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Bringing an interface up or down (admin state, independent of cabling):")
                    CodeBlock(
                        """sudo ip link set eth0 up
sudo ip link set eth0 down"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Other common changes — MTU and MAC address (interface must be down to change the MAC):")
                    CodeBlock(
                        """sudo ip link set eth0 mtu 9000
sudo ip link set eth0 down
sudo ip link set eth0 address 02:11:22:33:44:55
sudo ip link set eth0 up"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Renaming an interface (also requires it to be down first):")
                    CodeBlock(
                        """sudo ip link set eth0 down
sudo ip link set eth0 name lan0
sudo ip link set lan0 up"""
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "ip route — Managing Routes") {
                    BodyText(
                        "ip route (ip r) shows and edits the kernel's routing table — which decides, for any " +
                        "destination IP, which interface and next-hop gateway a packet goes out through."
                    )
                    CodeBlock("ip route show   # equivalent: ip r")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Reading a typical routing table:")
                    CodeBlock(
                        """default via 192.168.1.1 dev eth0 proto dhcp metric 100
192.168.1.0/24 dev eth0 proto kernel scope link src 192.168.1.20

default ...      — the default route (used when no more specific route matches)
via 192.168.1.1  — next-hop gateway
dev eth0         — outgoing interface
metric 100       — priority; lower metric wins when routes overlap
192.168.1.0/24 ... scope link — directly-connected subnet, no gateway needed"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Adding/removing routes (again, live and non-persistent):")
                    CodeBlock(
                        """sudo ip route add 10.0.0.0/24 via 192.168.1.254 dev eth0
sudo ip route add default via 192.168.1.1 dev eth0   # set default gateway
sudo ip route del 10.0.0.0/24"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "ip route get answers \"which route would be used to reach this destination\" without " +
                        "actually sending anything — useful for debugging routing/connectivity issues."
                    )
                    CodeBlock("ip route get 8.8.8.8")
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "nmcli — NetworkManager CLI") {
                    BodyText(
                        "NetworkManager is the service most desktop and many server distros use to manage " +
                        "networking, and nmcli is its command-line client. Where ip makes live, in-memory " +
                        "changes that vanish on reboot or interface restart, nmcli manages persistent " +
                        "\"connection profiles\" (saved configs) that NetworkManager reapplies automatically."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Checking overall status:")
                    CodeBlock(
                        """nmcli general status     # NetworkManager state, connectivity, WiFi/WWAN radio
nmcli device status      # every network device + its connection state
nmcli connection show    # every saved connection profile (nmcli con show)
nmcli connection show --active   # only profiles currently in use"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Wi-Fi:")
                    CodeBlock(
                        """nmcli device wifi list
nmcli device wifi connect "MySSID" password "mypassword""""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Bringing a saved profile up or down:")
                    CodeBlock(
                        """nmcli connection up "MySSID"
nmcli connection down "MySSID""""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Creating a persistent, static-IP wired profile:")
                    CodeBlock(
                        """nmcli connection add type ethernet con-name "static-lan" ifname eth0 \\
    ipv4.method manual ipv4.addresses 192.168.1.50/24 \\
    ipv4.gateway 192.168.1.1 ipv4.dns 8.8.8.8
nmcli connection up "static-lan""""
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
