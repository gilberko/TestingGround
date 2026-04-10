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
fun BootProcessScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Boot Process",
                        color = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontSize = 18.sp
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
                .padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            item {
                SectionCard(title = "1. BIOS / UEFI") {
                    BodyText("When you power on a computer, the CPU immediately executes firmware stored in non-volatile memory on the motherboard — either the legacy BIOS (Basic Input/Output System) or the modern UEFI (Unified Extensible Firmware Interface).")
                    Spacer(Modifier.height(6.dp))
                    BodyText("The firmware runs POST (Power-On Self Test) to verify that RAM, CPU, and essential hardware are functional. It then scans configured boot devices (NVMe, SSD, USB, network) and hands off control to the bootloader found on the first bootable device.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("UEFI differences from BIOS:")
                    BodyText("• Reads from an EFI System Partition (ESP, FAT32, typically /boot/efi)")
                    BodyText("• Supports Secure Boot — only signed bootloaders and kernels can run")
                    BodyText("• Can boot from disks larger than 2 TB (GPT partition table)")
                    BodyText("• Has a built-in shell and network stack")
                }
            }
            item {
                SectionCard(title = "2. Bootloader (GRUB2)") {
                    BodyText("GRUB2 (GRand Unified Bootloader 2) is the standard bootloader on most Linux distributions. After BIOS/UEFI hands off control, GRUB2 takes over.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("What GRUB2 does:")
                    BodyText("• Displays the boot menu (kernel version selection, recovery mode, etc.)")
                    BodyText("• Reads its configuration from /boot/grub/grub.cfg")
                    BodyText("• Loads the kernel image (vmlinuz) into RAM")
                    BodyText("• Loads the initial RAM filesystem (initramfs/initrd) into RAM")
                    BodyText("• Passes kernel command-line parameters (e.g. root=/dev/sda1, quiet, splash)")
                    BodyText("• Transfers control to the kernel entry point")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock("""# Example kernel command line passed by GRUB2:
linux /boot/vmlinuz-6.8.0 root=/dev/nvme0n1p2 ro quiet splash
initrd /boot/initramfs-6.8.0.img""")
                    BodyText("On UEFI systems, GRUB2 itself is a .efi executable stored in the ESP.")
                }
            }
            item {
                SectionCard(title = "3. initramfs — Initial RAM Filesystem") {
                    BodyText("initramfs (or initrd — initial RAM disk) is a compressed archive (cpio + gzip/zstd) that the bootloader loads into RAM. The kernel unpacks it into a temporary in-memory root filesystem.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Why it exists: The kernel needs to mount the real root filesystem (e.g. ext4 on an encrypted LVM on a NVMe device), but it may not have the necessary drivers compiled in. initramfs contains just enough userspace tools and kernel modules to:")
                    BodyText("• Load storage/filesystem/RAID/LVM/encryption drivers")
                    BodyText("• Assemble RAID arrays or unlock LUKS-encrypted partitions")
                    BodyText("• Mount the real root filesystem")
                    BodyText("• Call switch_root to pivot to the real root and hand off to init")
                    Spacer(Modifier.height(6.dp))
                    BodyText("The kernel mounts initramfs as / first. Once the real root is mounted, the kernel replaces the temporary / via switch_root (or pivot_root on older setups). The initramfs is then discarded from RAM.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock("""# Inspect initramfs contents:
lsinitramfs /boot/initramfs-6.8.0.img   # Debian/Ubuntu
lsinitrd /boot/initramfs-6.8.0.img      # Fedora/RHEL

# Rebuild initramfs after adding modules:
update-initramfs -u    # Debian/Ubuntu
dracut --force         # Fedora/RHEL""")
                }
            }
            item {
                SectionCard(title = "4. Kernel Initialization") {
                    BodyText("Once the kernel image (vmlinuz) is mapped into memory by GRUB2, the kernel's own startup code runs:")
                    Spacer(Modifier.height(6.dp))
                    BodyText("• Decompresses itself (vmlinuz is a compressed self-extracting archive)")
                    BodyText("• Sets up the CPU: GDT, IDT, paging, long mode (64-bit)")
                    BodyText("• Initializes core subsystems: memory allocator (buddy + SLUB), scheduler, IRQ handlers, timers")
                    BodyText("• Probes and registers hardware via device drivers")
                    BodyText("• Mounts initramfs as the initial root filesystem")
                    BodyText("• Runs /init inside initramfs (a shell script or systemd stub)")
                    BodyText("• After real root is mounted: calls switch_root to pivot to /dev/sdXN")
                    BodyText("• Executes /sbin/init (or /lib/systemd/systemd) as PID 1")
                    Spacer(Modifier.height(6.dp))
                    BodyText("You can observe kernel boot messages with:")
                    CodeBlock("""dmesg | less
journalctl -b         # current boot
journalctl -b -1      # previous boot""")
                }
            }
            item {
                SectionCard(title = "5. Init Process — systemd (PID 1)") {
                    BodyText("The very first userspace process the kernel starts is PID 1 — the init process. On all major modern Linux distributions, this is systemd.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Historical context:")
                    BodyText("• SysV init — original UNIX init, sequential runlevels (0–6), slow serial startup")
                    BodyText("• Upstart — Ubuntu's event-driven init (replaced by systemd ~2015)")
                    BodyText("• systemd — adopted by all major distros; parallel service startup, declarative unit files")
                    Spacer(Modifier.height(6.dp))
                    BodyText("systemd manages units (services, sockets, mounts, timers, targets). It starts services in parallel where dependencies allow, dramatically reducing boot time.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Boot targets (analogous to SysV runlevels):")
                    CodeBlock("""emergency.target     # minimal shell, no filesystem mounts
rescue.target        # single-user maintenance mode
multi-user.target    # full multi-user, no GUI (runlevel 3)
graphical.target     # multi-user + display manager (runlevel 5)""")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock("""# Check boot performance:
systemd-analyze
systemd-analyze blame        # which services took longest
systemd-analyze critical-chain""")
                }
            }
            item {
                SectionCard(title = "6. Graphical Target — Display Manager") {
                    BodyText("When the default target is graphical.target, systemd starts a display manager service as part of reaching that target.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Common display managers:")
                    BodyText("• GDM (GNOME Display Manager) — default on GNOME/Fedora/Ubuntu")
                    BodyText("• SDDM (Simple Desktop Display Manager) — default on KDE Plasma")
                    BodyText("• LightDM — lightweight, used by some Ubuntu flavors and Xfce")
                    Spacer(Modifier.height(6.dp))
                    BodyText("The display manager:")
                    BodyText("• Starts the display server (Wayland compositor or X11 Xorg server)")
                    BodyText("• Presents a graphical login screen")
                    BodyText("• Handles PAM authentication")
                    BodyText("• On successful login, launches the user session")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock("""# Check which display manager is active:
systemctl status display-manager

# Switch display manager:
sudo systemctl disable gdm && sudo systemctl enable sddm""")
                }
            }
            item {
                SectionCard(title = "7. Login → Desktop Environment") {
                    BodyText("After the user authenticates with the display manager, a desktop session is launched:")
                    Spacer(Modifier.height(6.dp))
                    BodyText("On GNOME:")
                    BodyText("• GDM starts a Wayland session (or X11 fallback)")
                    BodyText("• gnome-session starts GNOME Shell, the compositor (Mutter), and GNOME services (Files, Settings, etc.)")
                    Spacer(Modifier.height(6.dp))
                    BodyText("On KDE Plasma:")
                    BodyText("• SDDM starts a Wayland session (KWin/Wayland) or X11 session")
                    BodyText("• plasmashell starts the Plasma desktop, KWin (window manager + compositor), and KDE services (KRunner, Dolphin, etc.)")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Summary — complete boot sequence:")
                    CodeBlock("""Power On
  → UEFI/BIOS (POST, find boot device)
  → GRUB2 (load vmlinuz + initramfs, show menu)
  → Kernel init (decompress, hardware setup)
  → initramfs /init (load drivers, mount real root)
  → switch_root → real /
  → /sbin/init = systemd (PID 1)
  → systemd targets: basic → multi-user → graphical
  → Display Manager (GDM / SDDM)
  → Login → gnome-session / plasmashell
  → Desktop ready""")
                }
            }
            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}
