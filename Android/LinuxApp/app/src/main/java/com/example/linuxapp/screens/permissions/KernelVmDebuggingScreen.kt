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
fun KernelVmDebuggingScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Kernel VM Debugging",
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
                SectionCard(title = "Why Debug the Kernel in a VM?") {
                    BodyText(
                        "Kernel bugs can crash or corrupt the entire system. Debugging on a VM offers:"
                    )
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "• Safety — crashes only affect the guest VM, not your host\n" +
                        "• Control — pause, snapshot, and replay execution at will\n" +
                        "• Convenience — no need for dedicated test hardware or serial cables\n" +
                        "• Speed — modern CPUs run VMs with near-native performance (KVM)"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText(
                        "QEMU (with KVM acceleration on Linux) is the standard tool. It has a built-in " +
                        "GDB stub that lets you attach a debugger to the guest kernel from the host."
                    )
                }
            }

            item {
                SectionCard(title = "Preparing the Kernel Image") {
                    BodyText("Compile the kernel with the following configuration options:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "CONFIG_DEBUG_INFO=y          # embed DWARF debug symbols in vmlinux\n" +
                        "CONFIG_DEBUG_INFO_DWARF5=y   # use DWARF5 format (better GDB support)\n" +
                        "CONFIG_RANDOMIZE_BASE=n      # disable KASLR — fixed kernel load address\n" +
                        "CONFIG_GDB_SCRIPTS=y         # install kernel-aware GDB helper scripts\n" +
                        "CONFIG_FRAME_POINTER=y       # keep frame pointers for stack traces"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText(
                        "Why disable KASLR? KASLR (Kernel Address Space Layout Randomization) places the " +
                        "kernel at a random base address each boot. With KASLR enabled, breakpoint addresses " +
                        "change every run and symbol resolution breaks. Disable it for debugging sessions. " +
                        "You can also disable it at boot time without recompiling:"
                    )
                    Spacer(Modifier.height(4.dp))
                    CodeBlock("# Add to the kernel command line:\nnokaslr")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Build artifacts you need:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "vmlinux   — uncompressed kernel ELF with full debug symbols\n" +
                        "            (used by GDB for symbol lookups)\n" +
                        "bzImage   — compressed bootable kernel image\n" +
                        "            (loaded by QEMU)"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Build commands:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "make menuconfig      # configure the options above\n" +
                        "make -j\$(nproc)     # build the kernel\n" +
                        "# output: arch/x86/boot/bzImage  and  vmlinux"
                    )
                }
            }

            item {
                SectionCard(title = "Packing a Root Filesystem") {
                    BodyText(
                        "QEMU needs a root filesystem to boot into. The simplest approach is a minimal " +
                        "initramfs (initial RAM disk) containing BusyBox:"
                    )
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "# Build a BusyBox-based initramfs:\nmkdir -p initramfs/{bin,sbin,dev,proc,sys}\ncp busybox initramfs/bin/\nln -s /bin/busybox initramfs/bin/sh\n\n# Include your kernel modules (.ko files):\nmake modules_install INSTALL_MOD_PATH=initramfs\n\n# Create init script:\ncat > initramfs/init << 'EOF'\n#!/bin/sh\nmount -t proc none /proc\nmount -t sysfs none /sys\nexec /bin/sh\nEOF\nchmod +x initramfs/init\n\n# Pack into cpio.gz:\ncd initramfs\nfind . | cpio -o -H newc | gzip > ../initramfs.cpio.gz"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText(
                        "Alternatively, use buildroot to generate a full root filesystem, or " +
                        "debootstrap for a minimal Debian environment. For kernel module debugging, " +
                        "ensure the .ko files are built against the same kernel source tree."
                    )
                }
            }

            item {
                SectionCard(title = "Running QEMU") {
                    BodyText("Launch QEMU with the GDB stub enabled:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "qemu-system-x86_64 \\\n" +
                        "  -kernel arch/x86/boot/bzImage \\\n" +
                        "  -initrd initramfs.cpio.gz \\\n" +
                        "  -append \"console=ttyS0 nokaslr\" \\\n" +
                        "  -nographic \\\n" +
                        "  -m 512M \\\n" +
                        "  -s \\\n" +
                        "  -S"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Key flags:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "-s          shorthand for -gdb tcp::1234\n" +
                        "            starts a GDB server on localhost port 1234\n" +
                        "\n" +
                        "-S          freeze the CPU at startup — waits for GDB to connect\n" +
                        "            before executing a single instruction\n" +
                        "\n" +
                        "-nographic  redirect serial output to the terminal (console=ttyS0)\n" +
                        "\n" +
                        "-enable-kvm add this flag (if available) for hardware acceleration"
                    )
                }
            }

            item {
                SectionCard(title = "Connecting GDB to the Kernel") {
                    BodyText("On the host, in a separate terminal, start GDB with vmlinux:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "gdb vmlinux\n" +
                        "\n" +
                        "(gdb) target remote :1234       # connect to QEMU gdbserver\n" +
                        "(gdb) lx-symbols                # load kernel module symbols\n" +
                        "                                # (requires CONFIG_GDB_SCRIPTS=y)\n" +
                        "(gdb) break start_kernel        # breakpoint at kernel entry\n" +
                        "(gdb) continue\n" +
                        "\n" +
                        "# After hitting the breakpoint:\n" +
                        "(gdb) bt                        # print backtrace\n" +
                        "(gdb) info registers            # show register values\n" +
                        "(gdb) break sys_openat          # break on a syscall\n" +
                        "(gdb) break my_module_init      # break in a loaded module\n" +
                        "(gdb) print task->comm          # inspect kernel data structures\n" +
                        "(gdb) x/16xb 0xffff888000000000 # examine memory"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText(
                        "The GDB helper scripts (vmlinux-gdb.py) loaded by lx-symbols provide " +
                        "kernel-aware commands:"
                    )
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "lx-ps        — list kernel processes (like ps)\n" +
                        "lx-dmesg     — print the kernel ring buffer\n" +
                        "lx-lsmod     — list loaded kernel modules\n" +
                        "lx-symbols   — reload module symbols after insmod\n" +
                        "lx-cpus      — list CPUs and their current tasks"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText(
                        "To enable the GDB scripts, add this to ~/.gdbinit:"
                    )
                    Spacer(Modifier.height(4.dp))
                    CodeBlock("add-auto-load-safe-path /path/to/linux/scripts/gdb/vmlinux-gdb.py")
                }
            }

            item {
                SectionCard(title = "KDB — Kernel Debugger") {
                    BodyText(
                        "KDB is a built-in kernel debugger that runs inside the kernel itself — no " +
                        "separate host connection needed. Useful when GDB is impractical."
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Enable in kernel config:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "CONFIG_KGDB=y           # enable the KGDB/KDB subsystem\n" +
                        "CONFIG_KGDB_KDB=y       # enable the KDB front-end\n" +
                        "CONFIG_KDB_KEYBOARD=y   # enter KDB via keyboard (SysRq)"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Entering KDB at runtime:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "# From the running system:\necho g > /proc/sysrq-trigger\n\n# Or press: Alt + SysRq + G on the keyboard\n# KDB also activates automatically on an oops or panic"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Common KDB commands:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "bt              — backtrace of current thread\n" +
                        "bt <pid>        — backtrace of a specific process\n" +
                        "ps              — list processes\n" +
                        "md <addr>       — memory display (hex dump)\n" +
                        "mm <addr> <val> — memory modify\n" +
                        "bp <addr/sym>   — set breakpoint\n" +
                        "bc <N>          — clear breakpoint N\n" +
                        "go              — continue execution\n" +
                        "ss              — single step\n" +
                        "rd              — display registers\n" +
                        "lsmod           — list loaded modules"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText(
                        "KGDB mode: you can also use KDB as a KGDB backend, connecting a second " +
                        "GDB instance from a remote host over a serial line or network."
                    )
                }
            }

            item {
                SectionCard(title = "Other Debugging Techniques") {
                    BodyText("printk / pr_debug:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "• Simplest technique — add printk() calls to kernel code\n" +
                        "• Output appears in dmesg / /var/log/kern.log\n" +
                        "• Use log levels: KERN_ERR, KERN_WARNING, KERN_INFO, KERN_DEBUG\n" +
                        "• dynamic_debug: enable/disable pr_debug() at runtime without recompile\n" +
                        "  echo 'file my_driver.c +p' > /sys/kernel/debug/dynamic_debug/control"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("strace — system call tracer (user mode):")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "strace ./my_program          # trace syscalls from the start\n" +
                        "strace -p <PID>              # attach to a running process\n" +
                        "strace -e openat,read ./prog # filter to specific syscalls\n" +
                        "• Shows syscall args, return values, and timing\n" +
                        "• Invaluable for understanding what user programs actually do"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("ftrace — kernel function tracer:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "# Controlled via /sys/kernel/debug/tracing/\n" +
                        "echo function > current_tracer      # trace all function calls\n" +
                        "echo function_graph > current_tracer # show call graph with timing\n" +
                        "echo 1 > tracing_on\n" +
                        "cat trace\n" +
                        "\n" +
                        "Tracers:\n" +
                        "  function       — every function entry\n" +
                        "  function_graph — entry + exit with indented call graph\n" +
                        "  irqsoff        — find where IRQs are disabled the longest\n" +
                        "  sched_switch   — show context switches\n" +
                        "\n" +
                        "No recompile needed — works on any kernel with CONFIG_FTRACE=y"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("perf — performance counters and tracing:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "perf stat ./program          # CPU performance counters\n" +
                        "perf record -g ./program     # record call graph\n" +
                        "perf report                  # interactive flamegraph-style view\n" +
                        "perf trace ./program         # like strace but lower overhead"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("DTrace-style tracing:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "DTrace originated on Solaris. Linux equivalents:\n" +
                        "\n" +
                        "bpftrace   — high-level one-liner tracing with eBPF\n" +
                        "  bpftrace -e 'kprobe:sys_read { printf(\"%d\\n\", pid); }'\n" +
                        "\n" +
                        "SystemTap  — scripted kernel instrumentation (older approach)\n" +
                        "  stap -e 'probe kernel.function(\"vfs_read\") { println(execname()) }'"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
