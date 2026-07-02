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
fun LinuxOnOtherOsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Linux on \"Other\" OSs",
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
                SectionCard(title = "ChromeOS — Linux Under the Hood") {
                    BodyText("ChromeOS is not just \"Linux-like\" — it IS Linux. The foundation is a real Linux kernel (Google tracks an LTS upstream kernel and applies their own patches). On top of that sits a Gentoo-derived userspace. The Chrome browser shell, Android apps, and Linux containers all run on this common Linux base.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The security model:")
                    CodeBlock(
                        """Verified Boot     — each boot checks cryptographic signatures of
                    ChromeOS partitions; tampering is detected at boot
Read-only rootfs  — the ChromeOS system partition is mounted
                    read-only; malware cannot persist across reboot
Sandboxing        — processes run in Linux namespaces + seccomp
                    filters + SELinux/AppArmor enforcing
dm-verity         — block-level integrity verification of the OS
                    partition (similar to Android)"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("In developer mode, you can get a root shell directly on ChromeOS. But doing so breaks Verified Boot and is intended only for developers — not for day-to-day use.")
                }
            }
            item {
                SectionCard(title = "Crostini — Linux Development on ChromeOS") {
                    BodyText("Crostini is the official name for the \"Linux development environment\" feature in ChromeOS (Settings > Advanced > Developers > Linux development environment). It gives you a terminal with a full Debian GNU/Linux userspace.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The architecture has three layers:")
                    CodeBlock(
                        """ChromeOS host kernel (Google's Linux kernel)
  └── crosvm — Google's KVM-based VMM (VM monitor)
               Analogous to QEMU but purpose-built for ChromeOS
        └── Termina VM — a lightweight VM image
                         Based on a minimal Gentoo rootfs
                         Has its own Linux kernel (may differ from host)
              └── LXC container (default: penguin)
                    Runs Debian GNU/Linux (bookworm)
                    Full apt, gcc, Python, Go, Rust, gdb…
                    This is what you see in the Terminal app"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("You can run GUI Linux apps too — they appear alongside Chrome windows via the Wayland protocol bridged through sommelier (a Wayland proxy that connects to the ChromeOS compositor). VS Code, IntelliJ, and even Vim run this way.")
                }
            }
            item {
                SectionCard(title = "What Can You Develop in Crostini?") {
                    BodyText("User mode development: YES")
                    BodyText("Full Linux user-mode development works perfectly inside the container. Compile C/C++ with gcc/clang, debug with gdb, use Python/Go/Rust/Node, run web servers, connect to databases — everything a normal Linux dev machine offers. This is what Crostini is explicitly designed for.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Kernel modules: NO")
                    BodyText("You are inside an LXC container inside a VM. You cannot load kernel modules into the ChromeOS host kernel. Inside the Termina VM you could in principle build a module against the VM's kernel, but Termina's kernel source and headers are not publicly exposed in standard ChromeOS builds. Practically: kernel module development is not supported in Crostini.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("eBPF: LIMITED")
                    CodeBlock(
                        """Modern ChromeOS kernels (kernel 5.10+) have eBPF support enabled.
The constraint is capabilities inside the LXC container:
  CAP_BPF and CAP_PERFMON are needed for most eBPF programs.

What may work:
  - bpftrace one-liners for tracing (with appropriate caps)
  - Basic BCC tools if the container has the necessary privileges
  - CO-RE / BTF programs if BTF is exposed in the VM kernel

What is likely blocked or restricted:
  - XDP (attaches to network drivers — not accessible from container)
  - tc egress/ingress hooks
  - LSM eBPF programs
  - kprobe programs may need additional privileges

Results vary by ChromeOS version, device model (ARM vs x86),
and whether the container is given extended capabilities."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Security impact on the host: NONE")
                    BodyText("Crostini's VM boundary (crosvm + KVM hardware virtualisation) isolates the container from the ChromeOS host OS. Even if malicious code runs inside the Debian container and breaks out of the LXC container, it is still inside the Termina VM. Breaking out of the VM would require a hypervisor exploit. ChromeOS Verified Boot is completely unaffected — Crostini state lives in a separate encrypted virtual disk image.")
                }
            }
            item {
                SectionCard(title = "Windows Subsystem for Linux (WSL)") {
                    BodyText("WSL lets you run Linux programs natively on Windows without a traditional virtual machine visible to the user. There are two very different implementations:")
                    CodeBlock(
                        """WSL1 (2016):
  A compatibility layer inside the Windows NT kernel.
  No real Linux kernel — Linux system calls are translated
  to NT equivalents at runtime (like Wine but in reverse).
  Advantages:   fast file access to Windows NTFS drives
  Disadvantages: limited compatibility; no eBPF; no raw
                  sockets; no FUSE; some syscalls missing

WSL2 (2019, default since Windows 10 2004):
  A real Linux kernel running in a lightweight Hyper-V VM.
  Microsoft maintains their own fork of the upstream kernel:
    github.com/microsoft/WSL2-Linux-Kernel
  Full Linux syscall compatibility.
  File I/O to the Linux virtual disk is fast; cross-OS
  file access (/mnt/c) is slower due to the VM boundary.
  Recommended for all serious development."""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("WSL2 distributions (Ubuntu, Debian, Fedora, Arch, etc.) are installed from the Microsoft Store or via wsl --install. Each distro gets its own userspace filesystem image (ext4 virtual disk) but shares the same WSL2 Linux kernel.")
                }
            }
            item {
                SectionCard(title = "What Can You Develop in WSL2?") {
                    BodyText("User mode development: YES")
                    BodyText("Full Linux user-mode development. gcc, clang, gdb, strace, make, cmake, Python, Go, Rust, Node.js, Docker (via systemd or wsl2 backends) — all work. The /proc and /sys filesystems are present and functional. You can open X11/Wayland GUI apps via WSLg (Windows Subsystem for Linux GUI, built into WSL2 since 2021).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Kernel modules: very limited by default, possible with a custom kernel")
                    CodeBlock(
                        """The default Microsoft WSL2 kernel has CONFIG_MODULES=y (module
support compiled in), but Microsoft does not ship kernel headers
for module builds alongside the kernel image.

To load your own .ko module:
  1. Build a custom WSL2 kernel from Microsoft's source tree:
     git clone https://github.com/microsoft/WSL2-Linux-Kernel
     cp Microsoft/config-wsl .config
     make olddefconfig
     # Enable whatever you need (e.g. your custom module)
     make -j$(nproc)
  2. Point WSL2 at your kernel image in %USERPROFILE%\.wslconfig:
     [wsl2]
     kernel=C:\\path\\to\\bzImage
  3. wsl --shutdown && restart WSL2

Your modules load into the WSL2 VM kernel — NOT into Windows.
You cannot write Windows kernel drivers via WSL at all;
use the Windows Driver Kit (WDK) natively for that."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("eBPF: partially yes, improving with each WSL2 kernel release")
                    CodeBlock(
                        """WSL2 kernel 5.15+ has eBPF enabled. What works:
  - bpftrace and BCC tools — tracing programs work well
  - CO-RE / BTF-based programs — BTF is exposed, CO-RE works
  - kprobes and uprobes — function tracing works
  - XDP on the virtual NIC inside the VM — works
  - tc (traffic control) hooks — works inside the VM
  - LSM eBPF — works if compiled with CONFIG_BPF_LSM=y

Limitations:
  - You are tracing the WSL2 VM kernel, not the Windows kernel
  - Hardware events (perf_event on physical PMU) may be
    limited depending on Hyper-V's virtualisation of PMU
  - Some program types may need CAP_BPF / CAP_PERFMON
    (run as root inside WSL or grant capabilities)

Microsoft ships periodic kernel updates; eBPF support
has been expanding steadily since WSL2 kernel 5.10."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Security impact on Windows: NONE under normal operation")
                    BodyText("WSL2 runs inside a Hyper-V utility VM (lighter-weight than a full Hyper-V VM but the same isolation model). The Linux environment is separated from the Windows host by the hypervisor. Malicious Linux code cannot reach the Windows kernel or Windows user processes without a hypervisor-level exploit. The WSL2 virtual disk is an encrypted .vhdx file stored in the Windows user profile.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The key distinction:")
                    CodeBlock(
                        """In Crostini and WSL2, your kernel modules and eBPF programs
target the Linux VM kernel, not the host OS kernel.
This is both a limitation and a safety feature:
  - You cannot break the host OS with a buggy module
  - But you are also not actually running on bare metal Linux
  - For true bare-metal Linux kernel development, run Linux
    natively or in a dedicated VM (QEMU/KVM, VirtualBox, VMware)"""
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
