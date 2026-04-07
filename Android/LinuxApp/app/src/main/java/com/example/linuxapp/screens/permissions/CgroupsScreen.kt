package com.example.linuxapp.screens.permissions

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
fun CgroupsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "cgroups — Control Groups",
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
                SectionCard(title = "What Are cgroups?") {
                    BodyText("Control Groups (cgroups) are a Linux kernel feature that organizes processes into hierarchical groups and applies resource limits, accounting, and control to each group. Introduced in Linux 2.6.24 (2008) by Google engineers Paul Menage and Rohit Seth.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("cgroups give the kernel the ability to:")
                    CodeBlock("""Limit     — set a maximum on CPU, memory, I/O, etc.
Account   — measure how much of a resource a group uses
Isolate   — prevent groups from seeing each other's resources
Prioritize — give one group more CPU/IO weight than another
Freeze    — suspend all processes in a group at once
Checkpoint — save and restore group state""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("cgroups are the foundation of containers. Docker, Podman, and Kubernetes all use cgroups to enforce CPU and memory limits per container.")
                }
            }
            item {
                SectionCard(title = "cgroups v1 vs v2") {
                    BodyText("There are two generations. Most modern systems use v2 (unified hierarchy), but v1 may still exist alongside it.")
                    CodeBlock("""cgroups v1 (Linux 2.6.24, 2008)
  Multiple separate hierarchies, one per controller.
  Each controller (cpu, memory, blkio, ...) mounts separately.
  Mounted at:  /sys/fs/cgroup/cpu/
               /sys/fs/cgroup/memory/
               /sys/fs/cgroup/blkio/
  Drawback: hard to coordinate across controllers;
            a process can be in different hierarchies.

cgroups v2 (Linux 4.5, 2016 — default since ~5.x)
  Single unified hierarchy for all controllers.
  Mounted at: /sys/fs/cgroup/
  Cleaner delegation model for containers.
  Better support for rootless containers.
  Recommended for all new deployments.

# Check which version is active on your system
mount | grep cgroup
cat /proc/filesystems | grep cgroup
stat /sys/fs/cgroup   # if type is cgroup2, v2 is active""")
                }
            }
            item {
                SectionCard(title = "The cgroup Filesystem Interface") {
                    BodyText("cgroups are entirely managed through a virtual filesystem. Creating a directory = creating a cgroup. Writing to files = adjusting limits or adding processes.")
                    CodeBlock("""# cgroups v2 is mounted at /sys/fs/cgroup/
# Each subdirectory is a cgroup.

ls /sys/fs/cgroup/
# cgroup.controllers   cgroup.procs   memory.max
# cpu.max              io.max         ...

# Create a new cgroup (just mkdir)
mkdir /sys/fs/cgroup/myapp

# The kernel auto-populates control files inside:
ls /sys/fs/cgroup/myapp/
# cgroup.procs  cgroup.subtree_control
# cpu.max       memory.max  memory.current  io.max ...""")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Every cgroup directory contains a standard set of files. Writing to them configures the group; reading them shows current state or usage.")
                }
            }
            item {
                SectionCard(title = "Adding Processes to a cgroup") {
                    BodyText("Write a PID to cgroup.procs to move a process (and all its threads) into the cgroup. A process can only be in one cgroup at a time.")
                    CodeBlock("""# Move a running process into the cgroup
echo 1234 > /sys/fs/cgroup/myapp/cgroup.procs

# Move the current shell session into the cgroup
echo ${'$'}${'$'} > /sys/fs/cgroup/myapp/cgroup.procs

# Launch a new process directly into a cgroup
# (using systemd-run or cgexec)
systemd-run --scope -p MemoryMax=256M myserver
cgexec -g memory:myapp myserver   # cgroup-tools package

# View which PIDs are in a cgroup
cat /sys/fs/cgroup/myapp/cgroup.procs

# New child processes inherit their parent's cgroup
# automatically — no extra setup needed.""")
                }
            }
            item {
                SectionCard(title = "CPU Control") {
                    BodyText("The cpu controller limits and weights CPU time allocation.")
                    CodeBlock("""# Enable the cpu controller in the parent cgroup
echo "+cpu" > /sys/fs/cgroup/cgroup.subtree_control

# cpu.max — hard limit: quota/period (in microseconds)
# Format: <quota> <period>
# "max" quota means no limit.

# Allow myapp to use at most 50% of one CPU
echo "50000 100000" > /sys/fs/cgroup/myapp/cpu.max
#       50ms    100ms = 50% of one core per 100ms window

# No limit (default)
echo "max 100000"   > /sys/fs/cgroup/myapp/cpu.max

# cpu.weight — relative scheduling weight (default 100)
# Higher weight → more CPU time when contention exists.
echo 200 > /sys/fs/cgroup/myapp/cpu.weight   # double weight
echo  50 > /sys/fs/cgroup/myapp/cpu.weight   # half weight

# Read current CPU usage (in microseconds)
cat /sys/fs/cgroup/myapp/cpu.stat""")
                }
            }
            item {
                SectionCard(title = "Memory Control") {
                    BodyText("The memory controller limits RAM and swap usage per cgroup. When a cgroup exceeds its memory.max limit, the OOM killer fires and kills a process inside that cgroup.")
                    CodeBlock("""# Enable memory controller
echo "+memory" > /sys/fs/cgroup/cgroup.subtree_control

# memory.max — hard limit on physical memory
echo "256M"  > /sys/fs/cgroup/myapp/memory.max
echo "512M"  > /sys/fs/cgroup/myapp/memory.max
echo "1G"    > /sys/fs/cgroup/myapp/memory.max
echo "max"   > /sys/fs/cgroup/myapp/memory.max   # no limit

# memory.high — soft limit: process is throttled (slowed
# down) when it exceeds this, but not killed
echo "200M"  > /sys/fs/cgroup/myapp/memory.high

# memory.swap.max — limit swap usage
echo "0"     > /sys/fs/cgroup/myapp/memory.swap.max  # no swap

# Read current memory usage
cat /sys/fs/cgroup/myapp/memory.current   # current RSS
cat /sys/fs/cgroup/myapp/memory.stat      # detailed stats""")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("When memory.max is hit: the kernel's OOM killer selects a victim inside the cgroup and sends SIGKILL. The event is logged in dmesg as \"oom-kill:constraint=CONSTRAINT_MEMCG\".")
                }
            }
            item {
                SectionCard(title = "I/O Control") {
                    BodyText("The io controller limits block device throughput and IOPS per cgroup. Device numbers can be found in /proc/partitions or using lsblk.")
                    CodeBlock("""# Enable io controller
echo "+io" > /sys/fs/cgroup/cgroup.subtree_control

# io.max — hard limits on a specific device (MAJ:MIN)
# Format: MAJ:MIN rbps=N wbps=N riops=N wiops=N

# Limit reads to 10 MB/s, writes to 5 MB/s on /dev/sda (8:0)
echo "8:0 rbps=10485760 wbps=5242880" \
    > /sys/fs/cgroup/myapp/io.max

# Limit IOPS
echo "8:0 riops=1000 wiops=500" \
    > /sys/fs/cgroup/myapp/io.max

# Remove limit on a device
echo "8:0 rbps=max wbps=max riops=max wiops=max" \
    > /sys/fs/cgroup/myapp/io.max

# io.weight — relative I/O weight (default 100)
echo "default 200" > /sys/fs/cgroup/myapp/io.weight

# Read I/O statistics
cat /sys/fs/cgroup/myapp/io.stat""")
                }
            }
            item {
                SectionCard(title = "PID Limit") {
                    BodyText("The pids controller limits how many processes/threads a cgroup can have. Prevents fork bombs from exhausting the system's PID space.")
                    CodeBlock("""# Enable pids controller
echo "+pids" > /sys/fs/cgroup/cgroup.subtree_control

# Set max PIDs for the group
echo 100 > /sys/fs/cgroup/myapp/pids.max

# Read current count
cat /sys/fs/cgroup/myapp/pids.current

# When the limit is hit, fork()/clone() returns EAGAIN
# (resource temporarily unavailable) — the process cannot
# create more children.""")
                }
            }
            item {
                SectionCard(title = "Removing a cgroup") {
                    BodyText("A cgroup can only be removed when it has no processes and no child cgroups. The kernel removes the control files automatically when rmdir succeeds.")
                    CodeBlock("""# First move all processes out (or kill them)
cat /sys/fs/cgroup/myapp/cgroup.procs
# move remaining PIDs to root cgroup
echo 1234 > /sys/fs/cgroup/cgroup.procs

# Then remove the (now empty) cgroup directory
rmdir /sys/fs/cgroup/myapp

# If there are still processes, rmdir fails with EBUSY.""")
                }
            }
            item {
                SectionCard(title = "systemd and cgroups") {
                    BodyText("systemd is the primary cgroup manager on modern Linux. Every systemd unit (service, scope, slice) is automatically placed in its own cgroup under /sys/fs/cgroup/system.slice/ or user.slice/.")
                    CodeBlock("""# View the cgroup tree
systemd-cgls

# Set resource limits on a service (in .service file)
[Service]
CPUQuota=50%
MemoryMax=256M
IOReadBandwidthMax=/dev/sda 10M
TasksMax=50

# Or on the fly with systemctl set-property
systemctl set-property myservice.service MemoryMax=256M
systemctl set-property myservice.service CPUQuota=50%

# View resource accounting for a service
systemctl status myservice.service   # shows cgroup path
systemd-cgtop                        # live cgroup resource view""")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Containers (Docker/Podman): when you run \"docker run --memory=256m --cpus=0.5\", Docker writes exactly these values into the container's cgroup files under /sys/fs/cgroup/. The container process sees only its cgroup limits, enforced by the kernel.")
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
