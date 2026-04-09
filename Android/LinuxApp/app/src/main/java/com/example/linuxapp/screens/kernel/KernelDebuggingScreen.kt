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
fun KernelDebuggingScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Kernel Debugging & Tracing",
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
                SectionCard(title = "dmesg — Kernel Ring Buffer") {
                    BodyText("dmesg reads the kernel ring buffer — a fixed-size circular buffer in which the kernel logs printk() messages. It is the primary tool for diagnosing driver and kernel issues.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Essential dmesg commands:")
                    CodeBlock(
                        """dmesg                  # print entire ring buffer
dmesg | tail -30       # last 30 lines
dmesg -w               # follow in real time (like tail -f)
dmesg -T               # human-readable timestamps
dmesg -H               # human-readable with colours (systemd)
dmesg -c               # print then CLEAR the buffer (root)

# Filter by log level:
dmesg -l err           # errors only
dmesg -l warn,err      # warnings and errors
dmesg -l crit,alert,emerg  # critical and above

# Filter by facility:
dmesg -f kern          # kernel messages
dmesg -f daemon        # daemon messages"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Persistent kernel log locations:")
                    CodeBlock(
                        """/dev/kmsg              # structured kernel log (multiple readers)
/proc/kmsg             # raw ring buffer (one reader only)

# systemd — survives reboot:
journalctl -k          # kernel messages this boot
journalctl -k -b -1    # kernel messages from PREVIOUS boot
journalctl -k -b -1 -p err  # previous boot, errors only

# syslog-based systems:
/var/log/kern.log      # kernel log (Debian/Ubuntu)
/var/log/messages      # combined log (RHEL/CentOS)"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Tip: after loading or unloading a module, always check dmesg immediately — any init/exit printk output or error backtraces appear here.")
                }
            }
            item {
                SectionCard(title = "strace — System Call Tracer") {
                    BodyText("strace intercepts and logs every system call made by a user-space process. It is invaluable for understanding what a program is doing, debugging permission errors, and tracing file/network activity.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Basic usage:")
                    CodeBlock(
                        """# Trace a new process from start:
strace ./myprogram arg1 arg2

# Attach to an already-running process by PID:
strace -p 1234

# Detach with Ctrl+C; the traced process continues."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Useful options:")
                    CodeBlock(
                        """-e trace=open,read,write   # trace only these syscalls
-e trace=file              # all file-related syscalls
-e trace=network           # all network syscalls
-e trace=signal            # signal-related syscalls

-o strace.log              # write output to file
-f                         # follow child processes (fork)
-ff -o strace              # separate file per pid: strace.1234
-t                         # add wall-clock timestamps
-T                         # show time spent in each syscall
-s 256                     # show up to 256 bytes of strings
                           # (default is 32 — often truncates)
-c                         # summary: count calls, time, errors"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Reading strace output — each line shows: syscall(args) = return_value:")
                    CodeBlock(
                        """openat(AT_FDCWD, "data.txt", O_RDONLY) = 3
# Opened data.txt, got fd=3

read(3, "hello world\n", 4096)   = 12
# Read 12 bytes from fd=3 into buffer

write(1, "hello world\n", 12)    = 12
# Wrote 12 bytes to stdout (fd=1)

close(3)                         = 0
# Closed fd=3

mmap(NULL, 4096, PROT_READ|PROT_WRITE,
     MAP_PRIVATE|MAP_ANONYMOUS, -1, 0) = 0x7f8a00000000
# Anonymous mmap succeeded

--- SIGSEGV {si_signo=SIGSEGV, si_code=SEGV_MAPERR,
             si_addr=0x10} ---
# Segfault at address 0x10 (likely NULL dereference)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Tracing your kernel module from user space — strace the program that uses your /dev device:")
                    CodeBlock(
                        """strace -e trace=openat,read,write,ioctl,close \
       ./myapp /dev/mydevice

# You will see exactly which fops your driver is called with."""
                    )
                }
            }
            item {
                SectionCard(title = "Kernel Oops") {
                    BodyText("A kernel oops is the kernel detecting an internal error from which it may be able to recover — for example, a NULL pointer dereference in a kernel module. The faulty process (or module) is typically killed, but the system continues running.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("What an oops looks like in dmesg:")
                    CodeBlock(
                        """BUG: kernel NULL pointer dereference, address: 0000000000000010
#PF: supervisor read access in kernel mode
#PF: error_code(0x0000) - not-present page
PGD 0 P4D 0
Oops: 0000 [#1] SMP PTI
CPU: 2 PID: 1234 Comm: myprogram Tainted: G  OE
Hardware name: QEMU Standard PC
RIP: 0010:my_driver_read+0x2f/0x80 [mymodule]
RSP: 0018:ffffb4e1c09dfc00 EFLAGS: 00010246

RAX: 0000000000000000 RBX: ffff9e8c45b78000 ...
...
Call Trace:
 <TASK>
 vfs_read+0xa5/0x1b0
 ksys_read+0x5c/0xd0
 do_syscall_64+0x5c/0x90
 entry_SYSCALL_64_after_hwframe+0x6e/0xd8
 </TASK>"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("How to read an oops:")
                    CodeBlock(
                        """RIP: 0010:my_driver_read+0x2f/0x80 [mymodule]
  ↑ The instruction pointer at crash time.
  "my_driver_read" is the function, "+0x2f" is the offset.

Call Trace: shows the call stack at crash time (read bottom-up).

Tainted: G OE
  G = out-of-tree module loaded
  O = out-of-tree (externally built) module
  E = unsigned module"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Decode the exact crash line using addr2line or objdump:")
                    CodeBlock(
                        """# Find offset 0x2f in your module:
objdump -d mymodule.ko | grep -A5 "<my_driver_read>"

# With debug info (-g in Makefile EXTRA_CFLAGS):
addr2line -e mymodule.ko 0x2f"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("CONFIG_PANIC_ON_OOPS=y (kernel config) turns every oops into a full kernel panic instead of trying to continue.")
                }
            }
            item {
                SectionCard(title = "Kernel Panic") {
                    BodyText("A kernel panic is an unrecoverable fatal error. The kernel halts (and usually reboots) because it cannot safely continue. Unlike an oops, a panic means the system is completely stopped.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Common causes:")
                    CodeBlock(
                        """- NULL dereference in interrupt context (can't kill a process)
- Stack overflow in kernel thread
- Double free or heap corruption
- BUG() / BUG_ON() macro triggered
- Explicit panic("reason") call in driver/kernel
- Hardware errors (uncorrectable ECC, MCE)
- Failing to mount the root filesystem at boot"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The panic console output:")
                    CodeBlock(
                        """Kernel panic - not syncing: Attempted to kill init! exitcode=0x00000200

or:

Kernel panic - not syncing: Fatal exception in interrupt

Followed by:
  - Register dump (same format as oops)
  - Full stack trace
  - Module list
  - "---[ end Kernel panic ]---"

Then either halts, or reboots after panic_timeout seconds."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Controlling panic behavior:")
                    CodeBlock(
                        """# Reboot automatically after 10 seconds:
echo 10 | sudo tee /proc/sys/kernel/panic

# Permanent (add to /etc/sysctl.conf):
kernel.panic = 10

# Panic on oops:
kernel.panic_on_oops = 1"""
                    )
                }
            }
            item {
                SectionCard(title = "Crash Dumps — Finding the Memory Dump") {
                    BodyText("When the kernel panics, it can save a memory dump (vmcore) for later analysis. This requires the kdump service to be configured before the crash.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("How kdump works:")
                    CodeBlock(
                        """1. The main kernel reserves a small amount of RAM at boot
   (crashkernel=256M in kernel boot params).

2. On panic, the main kernel is replaced by a small
   "crash kernel" (loaded via kexec) that runs from the
   reserved memory.

3. The crash kernel saves the previous kernel's memory
   (the vmcore dump) to disk, then reboots normally.

4. You then analyze the vmcore offline."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Setting up kdump (Ubuntu/Debian):")
                    CodeBlock(
                        """sudo apt install linux-crashdump kdump-tools

# Add to kernel boot parameters (e.g. in /etc/default/grub):
GRUB_CMDLINE_LINUX="crashkernel=256M"
sudo update-grub
sudo reboot

# Verify kdump is active:
kdump-config show
systemctl status kdump"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Finding the crash dump after a panic:")
                    CodeBlock(
                        """/var/crash/                   # Ubuntu/Debian default dump dir
/var/crash/<timestamp>/vmcore  # the raw memory dump

# RHEL/CentOS/Fedora:
/var/crash/<timestamp>/vmcore

# Check what is inside:
ls -lh /var/crash/

# The dump can be several gigabytes (size of RAM)."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Analyzing a vmcore with the crash utility:")
                    CodeBlock(
                        """sudo apt install crash

# Open the dump (needs matching vmlinux with debug symbols):
crash /usr/lib/debug/boot/vmlinux-$(uname -r) \
      /var/crash/<timestamp>/vmcore

# Inside the crash shell:
crash> bt          # backtrace at time of crash
crash> log         # kernel message buffer (dmesg at crash)
crash> ps          # process list at crash time
crash> vm          # virtual memory info
crash> mod         # loaded modules at crash time
crash> dis my_driver_read  # disassemble function
crash> quit"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("If kdump was not configured — what to check after a reboot:")
                    CodeBlock(
                        """# Previous boot kernel messages (systemd):
journalctl -k -b -1

# Some systems write last kernel messages here:
/var/log/kern.log       # Debian/Ubuntu (if rsyslog is running)
/var/log/messages       # RHEL/CentOS

# If the panic happened too fast for logs to flush to disk,
# the only record may be a photo of the screen (console output).
# On VMs, check the hypervisor's serial console log."""
                    )
                }
            }
            item {
                SectionCard(title = "kprobes — Dynamic Kernel Instrumentation") {
                    BodyText("kprobes let you place a breakpoint-like probe at virtually any kernel instruction at runtime — no reboot, no kernel recompile, no source modification. Introduced in Linux 2.6.9.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Three probe types:")
                    CodeBlock(
                        """kprobe     — fires before the probed instruction executes
                            |             (function entry or any address)
                            |
                            |kretprobe  — fires when the probed function returns
                            |             (has access to the return value)
                            |
                            |jprobe     — deprecated since Linux 5.3, removed in 5.15""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("How kprobes work (x86-64):")
                    CodeBlock(
                        """1. At registration, kprobes replaces the first byte of
                            |   the target instruction with int3 (0xCC).
                            |
                            |2. When the CPU hits 0xCC it raises a #BP exception.
                            |   The kprobes exception handler runs your pre_handler.
                            |
                            |3. The original instruction is single-stepped in a
                            |   scratch area (or emulated in software).
                            |
                            |4. Your post_handler runs (optional), then execution
                            |   resumes normally.
                            |
                            |Overhead: ~100-300ns per probe hit.""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Use cases: debugging production kernels without recompiling, performance analysis, security auditing, tracing specific code paths. Today, eBPF programs use kprobes as their backend — SEC(\"kprobe/...\") is built on the same mechanism.")
                }
            }
            item {
                SectionCard(title = "struct pt_regs — Register State at Probe Point") {
                    BodyText("When your kprobe handler is called, it receives a struct pt_regs pointer containing the CPU register state at the moment the probe fired. The struct layout is architecture-specific.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("On x86-64, the calling convention maps function arguments to registers as follows:")
                    CodeBlock(
                        """// x86-64 — relevant fields in struct pt_regs
                            |struct pt_regs {
                            |    unsigned long di;  // arg 1 (RDI)
                            |    unsigned long si;  // arg 2 (RSI)
                            |    unsigned long dx;  // arg 3 (RDX)
                            |    unsigned long cx;  // arg 4 (RCX)
                            |    unsigned long r8;  // arg 5
                            |    unsigned long r9;  // arg 6
                            |    unsigned long ax;  // return value (RAX)
                            |                       // also syscall number on entry
                            |    unsigned long ip;  // instruction pointer (RIP)
                            |    unsigned long sp;  // stack pointer (RSP)
                            |    // ... flags, cs, ss, orig_ax, r10-r15 ...
                            |};""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("For portable code (works across architectures), use the PT_REGS macros instead of accessing fields directly:")
                    CodeBlock(
                        """PT_REGS_PARM1(regs)   // arg 1
                            |PT_REGS_PARM2(regs)   // arg 2
                            |PT_REGS_PARM3(regs)   // arg 3
                            |PT_REGS_PARM4(regs)   // arg 4
                            |PT_REGS_PARM5(regs)   // arg 5
                            |PT_REGS_RC(regs)      // return value (use in kretprobe)
                            |PT_REGS_IP(regs)      // instruction pointer""".trimMargin()
                    )
                }
            }
            item {
                SectionCard(title = "Small kprobe Module Example") {
                    BodyText("This LKM probes do_sys_openat2 (the kernel function backing openat()) and logs the file descriptor argument via pr_info:")
                    CodeBlock(
                        """#include <linux/kernel.h>
                            |#include <linux/module.h>
                            |#include <linux/kprobes.h>
                            |
                            |// Fires just before do_sys_openat2 executes
                            |static int handler_pre(struct kprobe *p,
                            |                       struct pt_regs *regs)
                            |{
                            |    // On x86-64, arg1 (dfd) is in regs->di
                            |    pr_info("kprobe: openat called, dfd=%ld\n",
                            |            (long)regs->di);
                            |    return 0;
                            |}
                            |
                            |static struct kprobe kp = {
                            |    .symbol_name = "do_sys_openat2",
                            |    .pre_handler = handler_pre,
                            |};
                            |
                            |static int __init kprobe_init(void)
                            |{
                            |    int ret = register_kprobe(&kp);
                            |    if (ret < 0) {
                            |        pr_err("register_kprobe failed: %d\n", ret);
                            |        return ret;
                            |    }
                            |    pr_info("kprobe registered at %p\n", kp.addr);
                            |    return 0;
                            |}
                            |
                            |static void __exit kprobe_exit(void)
                            |{
                            |    unregister_kprobe(&kp);
                            |    pr_info("kprobe unregistered\n");
                            |}
                            |
                            |module_init(kprobe_init);
                            |module_exit(kprobe_exit);
                            |MODULE_LICENSE("GPL");""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Build and test:")
                    CodeBlock(
                        """make          # standard LKM Makefile
                            |sudo insmod my_kprobe.ko
                            |dmesg | tail  # see "kprobe registered at 0x..."
                            |
                            |# Trigger: open any file
                            |cat /etc/hostname
                            |
                            |dmesg | tail  # see "kprobe: openat called, dfd=-100"
                            |             # (-100 = AT_FDCWD, current directory)
                            |
                            |sudo rmmod my_kprobe""".trimMargin()
                    )
                }
            }
            item {
                SectionCard(title = "kprobes — Limits and Blacklist") {
                    BodyText("Not all kernel functions can be probed:")
                    CodeBlock(
                        """# View the kprobes blacklist (functions that cannot be probed)
                            |cat /sys/kernel/debug/kprobes/blacklist
                            |
                            |# Functions on the blacklist include:
                            |# - kprobes infrastructure itself (would cause recursion)
                            |# - Functions marked __kprobes or nokprobe_inline
                            |# - Some interrupt/exception handlers""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Other constraints:")
                    CodeBlock(
                        """- Cannot probe notrace functions (marked with notrace attribute)
                            |
                            |- Overhead matters: avoid probing very hot paths such as
                            |  schedule() or __do_fault in production — thousands of
                            |  calls per second will add measurable latency.
                            |
                            |- kprobes handlers run with interrupts disabled (on x86)
                            |  — no sleeping, no blocking calls.
                            |
                            |- View all currently registered kprobes:
                            |  cat /sys/kernel/debug/kprobes/list""".trimMargin()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("In practice, you rarely write raw kprobe LKMs today. eBPF with SEC(\"kprobe/...\") provides the same mechanism with safety guarantees, no reboot, dynamic load/unload, and access to BPF maps and helpers. Use raw kprobes only when you need capabilities eBPF cannot provide.")
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
