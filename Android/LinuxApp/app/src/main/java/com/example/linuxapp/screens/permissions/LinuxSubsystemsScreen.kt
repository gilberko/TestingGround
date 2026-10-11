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
fun LinuxSubsystemsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Linux Subsystems",
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
                SectionCard(title = "The Kernel as a Set of Subsystems") {
                    BodyText("Linux is a monolithic kernel: everything runs in one address space at the highest privilege level. Inside, the code is organized into SUBSYSTEMS. Each one owns one area of responsibility, has its own maintainers (see the MAINTAINERS file), its own directory in the source tree, and its own internal API that other subsystems call.")
                    CodeBlock(
                        """  User space:  bash   nginx   docker   firefox   systemd
 ─────────────────── system call interface ──────────────────
 ┌──────────┬──────────┬──────────┬──────────┬──────────────┐
 │ Process  │ Memory   │   VFS    │ Network  │   IPC        │
 │ mgmt +   │ mgmt     │ + file-  │ stack    │ (pipes, shm, │
 │ scheduler│ (mm/)    │ systems  │ (net/)   │  futex, ...) │
 ├──────────┴────┬─────┴────┬─────┴──────────┴──────────────┤
 │ Security(LSM) │ Namespaces + cgroups │ Time │ Tracing   │
 ├───────────────┴──────────┬───────────┴──────┴───────────┤
 │   Block layer            │  Device model + drivers       │
 │   (block/)               │  (drivers/: USB, PCI, input,  │
 │                          │   DRM, ALSA, net, ...)        │
 ├──────────────────────────┴───────────────────────────────┤
 │  Arch code (arch/x86, arm64): IRQs, MMU, context switch,  │
 │  boot, syscall entry                                      │
 └──────────────────────────────────────────────────────────┘
                        Hardware"""
                    )
                    BodyText("Subsystems are not isolated. A single read() call passes through the syscall layer, VFS, a filesystem, the page cache (mm), the block layer, a driver, the interrupt subsystem and the scheduler. The examples at the bottom of this screen trace such paths.")
                }
            }
            item {
                SectionCard(title = "1. Process Management & Scheduler") {
                    BodyText("Source: kernel/ (fork.c, exit.c, signal.c), kernel/sched/")
                    BodyText("Provides:")
                    BodyText("• Creating and destroying tasks: fork/clone/exec/exit/wait. Every process or thread is a struct task_struct.")
                    BodyText("• Scheduling: choosing which task runs on which CPU (EEVDF fair class (formerly CFS), real-time SCHED_FIFO/RR, SCHED_DEADLINE, idle), load balancing, CPU affinity, priorities and nice.")
                    BodyText("• Signals, wait queues (sleep and wake-up), kernel threads, and workqueues for deferred work.")
                    CodeBlock("task_struct  current  schedule()  wake_up_process()\nkthread_run()  send_sig()  sched_setaffinity()")
                }
            }
            item {
                SectionCard(title = "2. Memory Management") {
                    BodyText("Source: mm/, plus arch/*/mm for page tables")
                    BodyText("Provides:")
                    BodyText("• Virtual memory: per-process address spaces (mm_struct, VMAs), page tables, demand paging, copy-on-write after fork, mmap().")
                    BodyText("• Physical memory: the buddy page allocator, the slab allocators (kmalloc, kmem_cache), vmalloc, NUMA placement, huge pages.")
                    BodyText("• Page cache: file data cached in RAM, shared by read(), write() and mmap().")
                    BodyText("• Reclaim and swap: kswapd, LRU lists, writeback of dirty pages, and the OOM killer when memory runs out.")
                    CodeBlock("kmalloc()  alloc_pages()  vmalloc()  mm_struct\nvm_area_struct  handle_mm_fault()  out_of_memory()")
                }
            }
            item {
                SectionCard(title = "3. VFS & Filesystems") {
                    BodyText("Source: fs/ (VFS core) and fs/ext4, fs/btrfs, fs/xfs, fs/proc, fs/overlayfs ...")
                    BodyText("Provides:")
                    BodyText("• One uniform API (open/read/write/close/stat/mmap) over every filesystem, built on superblock, inode, dentry and file objects.")
                    BodyText("• Path lookup, the dentry and inode caches, mounts and mount namespaces, permission checks, and file locking.")
                    BodyText("• Concrete filesystems: on-disk (ext4, XFS, Btrfs), network (NFS, CIFS), pseudo (procfs, sysfs, tmpfs, debugfs, tracefs), and stacking (overlayfs, which Docker uses).")
                    BodyText("• \"Everything is a file\": devices, pipes and sockets are also reached through struct file and file_operations.")
                    CodeBlock("struct file_operations  inode  dentry  super_block\nvfs_read()  filename_lookup()  register_filesystem()")
                }
            }
            item {
                SectionCard(title = "4. Block Layer") {
                    BodyText("Source: block/, plus drivers/nvme, drivers/scsi, drivers/md (RAID, device-mapper)")
                    BodyText("Provides:")
                    BodyText("• A generic path from filesystems down to storage drivers: struct bio (an I/O request) is merged into struct request and sent to the device's hardware queues (blk-mq).")
                    BodyText("• I/O schedulers (none, mq-deadline, bfq, kyber), request merging, plugging, and per-cgroup I/O throttling.")
                    BodyText("• Stacking drivers: device-mapper (LVM, dm-crypt / LUKS), md RAID, loop devices.")
                    CodeBlock("submit_bio()  struct bio  blk_mq_ops  gendisk\n/sys/block/nvme0n1/queue/scheduler")
                }
            }
            item {
                SectionCard(title = "5. Networking Stack") {
                    BodyText("Source: net/ (core, ipv4, ipv6, netfilter, unix, packet ...), drivers/net")
                    BodyText("Provides:")
                    BodyText("• The socket API (BSD sockets) for TCP, UDP, raw, Unix-domain, netlink and packet sockets.")
                    BodyText("• Protocol implementations: Ethernet, ARP, IPv4/IPv6, routing (FIB), TCP congestion control, UDP, kTLS.")
                    BodyText("• Netfilter / nftables (firewall, NAT, conntrack), traffic control (qdiscs), bridges, VLANs, veth pairs, tunnels, WireGuard.")
                    BodyText("• Fast paths: NAPI polling, GRO/GSO, XDP and eBPF hooks. Every packet is a struct sk_buff.")
                    CodeBlock("struct sk_buff  struct sock  struct net_device\nnetif_receive_skb()  dev_queue_xmit()  nf_register_net_hook()")
                }
            }
            item {
                SectionCard(title = "6. Device Drivers & the Driver Model") {
                    BodyText("Source: drivers/ (the largest part of the kernel), drivers/base for the core model")
                    BodyText("Provides:")
                    BodyText("• The bus / device / driver model: buses (PCI, USB, platform, I2C, SPI) match devices with drivers and call probe() and remove().")
                    BodyText("• sysfs (/sys) shows every device, and uevents tell udev in user space to create /dev nodes and load firmware.")
                    BodyText("• Device classes: character devices, block devices, network devices, plus frameworks such as input, DRM, ALSA, V4L2 and IIO.")
                    BodyText("• Shared helpers: DMA mapping, firmware loading, device tree / ACPI description, regulators and clocks.")
                    CodeBlock("struct device  struct device_driver  pci_register_driver()\nmodule_usb_driver()  probe()/remove()  devm_*()")
                }
            }
            item {
                SectionCard(title = "7. Inter-Process Communication (IPC)") {
                    BodyText("Source: ipc/, kernel/futex/, fs/pipe.c, net/unix/")
                    BodyText("Provides:")
                    BodyText("• Pipes and FIFOs, Unix-domain sockets (which can also pass file descriptors).")
                    BodyText("• System V and POSIX message queues, semaphores and shared memory.")
                    BodyText("• Futexes: the kernel half of every user-space mutex and condition variable (pthread, std::mutex).")
                    BodyText("• Signals, eventfd, signalfd, and (on Android) Binder.")
                    CodeBlock("pipe2()  futex()  shmget()/shm_open()  mq_open()\neventfd()  socketpair(AF_UNIX)")
                }
            }
            item {
                SectionCard(title = "8. Security") {
                    BodyText("Source: security/, kernel/capability.c, kernel/seccomp.c")
                    BodyText("Provides:")
                    BodyText("• Classic DAC: UIDs and GIDs, file permission bits, ACLs.")
                    BodyText("• Capabilities: root's power split into units (CAP_NET_ADMIN, CAP_SYS_ADMIN ...).")
                    BodyText("• LSM hooks with SELinux, AppArmor, Smack, Landlock, BPF-LSM and Yama plugged into them. These are mandatory access-control checks on files, sockets, processes and more.")
                    BodyText("• seccomp (syscall filtering), integrity (IMA/EVM), keyrings, lockdown, and the kernel crypto API (crypto/) used by dm-crypt, IPsec, WireGuard and kTLS.")
                    CodeBlock("capable(CAP_X)  security_file_open()  LSM_HOOK\nprctl(PR_SET_SECCOMP)  crypto_alloc_skcipher()")
                }
            }
            item {
                SectionCard(title = "9. Namespaces & cgroups (Containers)") {
                    BodyText("Source: kernel/nsproxy.c, kernel/cgroup/, plus per-subsystem pieces")
                    BodyText("Provides:")
                    BodyText("• Namespaces, which control what a process can SEE: mount, PID, network, UTS, IPC, user, cgroup and time namespaces.")
                    BodyText("• cgroups v2, which control how much a process can USE: cpu, memory, io and pids controllers, cpusets, and the freezer.")
                    BodyText("• These two are what Docker, Kubernetes, systemd services and Android apps are built on. There is no \"container\" object in the kernel.")
                    CodeBlock("clone(CLONE_NEWPID|CLONE_NEWNET...)  unshare()  setns()\n/sys/fs/cgroup/<grp>/memory.max  cpu.max  pids.max")
                }
            }
            item {
                SectionCard(title = "10. Time, Timers, Interrupts & Deferred Work") {
                    BodyText("Source: kernel/time/, kernel/irq/, kernel/softirq.c, kernel/workqueue.c")
                    BodyText("Provides:")
                    BodyText("• Clocks (CLOCK_REALTIME, CLOCK_MONOTONIC), clocksources (TSC, arch timer), the scheduler tick, tickless (NO_HZ) idle, and NTP adjustment.")
                    BodyText("• Timers: timer_list (jiffies-based), hrtimers (nanosecond precision), and the timerfd, nanosleep and POSIX-timer syscalls.")
                    BodyText("• Interrupt handling: IRQ descriptors, request_irq(), threaded IRQs, affinity, and MSI/MSI-X.")
                    BodyText("• Deferred work: softirqs, tasklets, workqueues.")
                    CodeBlock("ktime_get()  hrtimer_start()  mod_timer()\nrequest_threaded_irq()  queue_work()  raise_softirq()")
                }
            }
            item {
                SectionCard(title = "11. Power Management") {
                    BodyText("Source: kernel/power/, drivers/cpufreq, drivers/cpuidle, drivers/base/power")
                    BodyText("Provides:")
                    BodyText("• System sleep: suspend-to-RAM, hibernate, and the driver suspend() / resume() callbacks.")
                    BodyText("• Runtime PM, which powers individual idle devices down while the system runs.")
                    BodyText("• CPU frequency scaling (cpufreq governors: schedutil, performance, powersave), CPU idle states (C-states), and thermal management.")
                    CodeBlock("dev_pm_ops  pm_runtime_get_sync()  /sys/power/state\n/sys/devices/system/cpu/cpu0/cpufreq/scaling_governor")
                }
            }
            item {
                SectionCard(title = "12. Device-Class Frameworks: Input, Sound, Graphics, USB") {
                    BodyText("These are driver frameworks (under drivers/ and sound/). Each gives user space one standard interface, whatever the hardware vendor:")
                    BodyText("• Input (drivers/input): keyboards, mice, touchscreens and game controllers all report events through /dev/input/eventN (evdev). HID parsing turns USB and Bluetooth reports into key codes.")
                    BodyText("• Sound / ALSA (sound/): PCM playback and capture, mixers and MIDI through /dev/snd/*. PipeWire and PulseAudio sit on top of it.")
                    BodyText("• Graphics / DRM-KMS (drivers/gpu/drm): mode setting (resolution, outputs), GPU memory management (GEM, dma-buf), command submission and page flips through /dev/dri/card0 and renderD128.")
                    BodyText("• Video4Linux2 (drivers/media): cameras, TV tuners and hardware video decoders through /dev/videoN.")
                    BodyText("• USB (drivers/usb): host controller drivers (xHCI), enumeration, the hub driver, and class drivers (HID, mass storage, CDC). It also supports gadget mode, where Linux acts as the USB device.")
                }
            }
            item {
                SectionCard(title = "13. Tracing & Observability") {
                    BodyText("Source: kernel/trace/, kernel/bpf/, kernel/events/ (perf)")
                    BodyText("Provides:")
                    BodyText("• ftrace (function tracer, tracefs), tracepoints, kprobes and uprobes.")
                    BodyText("• perf events (hardware counters, sampling), and the eBPF virtual machine and verifier that run safe programs on any of those hooks.")
                    BodyText("• /proc and /sys statistics, printk / dmesg, and audit.")
                    CodeBlock("/sys/kernel/tracing  perf_event_open()  bpf()\ntrace_*() tracepoints  register_kprobe()")
                }
            }
            item {
                SectionCard(title = "Example 1 — cat file.txt") {
                    BodyText("You type cat file.txt in a terminal. What each subsystem contributes:")
                    BodyText("• Process mgmt: bash calls fork() to create a child (copy-on-write mm), the child calls execve(\"/usr/bin/cat\"), and bash wait()s for the exit status.")
                    BodyText("• Memory mgmt: execve maps the ELF binary and libc. Page faults load their pages from the page cache, and the stack and heap are created.")
                    BodyText("• VFS + ext4: open(\"file.txt\") does path lookup through the dentry cache, ext4 finds the inode, and a permission check runs.")
                    BodyText("• Security: DAC permission bits, plus the LSM hook (SELinux/AppArmor) security_file_open().")
                    BodyText("• Memory mgmt (page cache): read() is served from the page cache if the file is cached. Otherwise...")
                    BodyText("• Block layer + NVMe driver: ext4 builds a bio, blk-mq sends it to the NVMe queue, and the DMA brings the data into page-cache pages.")
                    BodyText("• Interrupts + scheduler: cat sleeps on a wait queue. The NVMe completion interrupt wakes it and the scheduler runs it again.")
                    BodyText("• TTY/console driver: write(1, ...) goes to the pseudo-terminal (pty), and the terminal emulator reads the other side.")
                }
            }
            item {
                SectionCard(title = "Example 2 — nginx Accepts an HTTPS Connection") {
                    BodyText("• Network driver + interrupts: the NIC DMAs the TCP SYN into memory and raises an IRQ. NAPI polls the packet in softirq context as an sk_buff.")
                    BodyText("• Networking stack: Ethernet → IP (routing lookup) → netfilter (firewall / conntrack) → TCP completes the 3-way handshake and puts the connection on the listen socket's accept queue.")
                    BodyText("• Process mgmt / scheduler: the nginx worker blocked in epoll_wait() is woken. With SO_REUSEPORT the kernel spreads connections across workers.")
                    BodyText("• VFS: accept4() returns a new file descriptor, so a socket is just a struct file.")
                    BodyText("• Memory mgmt: socket buffers come from the slab allocator, and memory cgroup accounting charges them to nginx's cgroup.")
                    BodyText("• Security / crypto: TLS is usually done in user space (OpenSSL). With kTLS, the kernel crypto API encrypts records so sendfile() can send files directly from the page cache.")
                    BodyText("• VFS + page cache: sendfile() of static files avoids copying data into user space.")
                }
            }
            item {
                SectionCard(title = "Example 3 — Plugging In a USB Keyboard") {
                    BodyText("• USB subsystem: the xHCI host controller detects the port change, and the hub driver resets the port, assigns an address and reads the device descriptors (enumeration).")
                    BodyText("• Driver model: a struct usb_device / usb_interface is registered on the USB bus, the bus matches it with usbhid, and probe() runs. sysfs entries appear under /sys/bus/usb/devices.")
                    BodyText("• Input subsystem: usbhid + hid-generic parse the HID report descriptor and register an input_dev, which creates /dev/input/eventN.")
                    BodyText("• uevent → udev (user space): creates the /dev node and applies rules (keyboard layout, permissions), and systemd-logind gives access to the seat.")
                    BodyText("• Interrupts / timers: every few ms the xHCI controller completes the interrupt transfer and the IRQ handler hands the key report to HID. The input layer adds autorepeat with a timer.")
                    BodyText("• Power management: USB runtime PM can suspend the keyboard when it is idle, and remote wakeup can wake the system from suspend on a keypress.")
                }
            }
            item {
                SectionCard(title = "Example 4 — docker run nginx") {
                    BodyText("• Namespaces: runc calls clone() / unshare() with new PID, mount, network, UTS, IPC (and optionally user) namespaces, so the container sees itself as PID 1 with its own hostname.")
                    BodyText("• cgroups: a new cgroup gets memory.max, cpu.max and pids.max limits, and the container's processes are moved into it.")
                    BodyText("• VFS / overlayfs: the image layers (read-only) and a writable upper layer are combined with overlayfs. pivot_root() switches to it, and /proc and /sys are mounted again inside.")
                    BodyText("• Networking: a veth pair (one end in the container's network namespace, the other on the docker0 bridge), plus netfilter/nftables NAT rules for -p port publishing.")
                    BodyText("• Security: capabilities are reduced, a default seccomp profile blocks dangerous syscalls, and an AppArmor/SELinux profile is applied.")
                    BodyText("• Process mgmt: execve() of the nginx binary inside the new namespaces. The same scheduler schedules it like any other task, within its cgroup's CPU share.")
                }
            }
            item {
                SectionCard(title = "Example 5 — Playing a Video in a Browser") {
                    BodyText("• Networking: the video arrives in TCP or QUIC (UDP) streams.")
                    BodyText("• Memory mgmt: large buffers, shared memory between the browser's processes (memfd, mmap), and dma-buf to share frames with the GPU without copying.")
                    BodyText("• DRM/KMS + GPU driver: hardware video decode (through VA-API / V4L2 on some SoCs), compositing, and page flips synchronized to vblank.")
                    BodyText("• ALSA: PipeWire sends the audio stream to the sound card's PCM device, and period interrupts keep the buffer filled.")
                    BodyText("• Scheduler + time: hrtimers and the audio clock pace frame presentation. The browser's media threads may run at higher priority (rtkit).")
                    BodyText("• Security / IPC: the browser's sandboxed renderer processes use seccomp-bpf, namespaces and Unix-socket IPC to talk to the GPU and network processes.")
                    BodyText("• Power management: cpufreq raises CPU clocks under load, and the GPU and display use runtime PM between frames.")
                }
            }
            item {
                SectionCard(title = "Example 6 — The OOM Killer Kills a Process") {
                    BodyText("• Memory mgmt: an allocation fails even after reclaim (kswapd, direct reclaim, swap). out_of_memory() runs and chooses a victim by oom_score (memory size, adjusted by oom_score_adj).")
                    BodyText("• cgroups: if the limit hit was a cgroup's memory.max, only that cgroup's tasks are candidates (a memcg OOM), and memory.events counts it.")
                    BodyText("• Process mgmt / signals: the victim gets SIGKILL, and the OOM reaper frees its memory early, even before the task finishes exiting.")
                    BodyText("• Tracing: the oom:mark_victim tracepoint and a detailed dmesg report (\"Out of memory: Killed process 1234 (java)\").")
                    BodyText("• User space: systemd-oomd or earlyoom may act earlier, based on PSI (pressure stall information) that the scheduler and mm export through /proc/pressure.")
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
