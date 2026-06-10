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
fun LinuxPackageManagersScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Package Managers",
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
                SectionCard(title = "What Is a Package Manager?") {
                    BodyText("A package manager is a tool that automates the lifecycle of software on your system: finding, downloading, installing, upgrading, and removing programs and libraries. The core problem it solves is dependency management — most software depends on shared libraries or other tools, and tracking all of those manually is impractical.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("A package is a self-contained archive that contains:")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Compiled binary files and/or scripts to install into the system (e.g., /usr/bin/gcc, /usr/lib/libz.so).")
                    BodyText("Metadata: name, version, architecture (x86_64, aarch64, noarch), description, list of dependencies, checksums, digital signature.")
                    BodyText("Optional pre/post-install scripts that run before or after file installation (e.g., creating a system user, running ldconfig, enabling a systemd service).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Repositories (repos) are remote servers (or mirrors) that host thousands of packages as an indexed archive. The package manager downloads and caches an index of available packages, then fetches and verifies only the packages you request.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The key advantage over downloading binaries manually: when you install package A, the package manager automatically installs everything A needs, at the correct versions, without conflicts.")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "The Two Main Package Formats") {
                    BodyText("All major Linux distributions use one of two binary package formats. These formats are incompatible — you cannot install an RPM on a Debian system or a .deb on Fedora.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("RPM format (.rpm files) — created by Red Hat. Used by:")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Fedora — community distro, very up-to-date packages, sponsored by Red Hat. Uses dnf.")
                    BodyText("RHEL (Red Hat Enterprise Linux) — enterprise, stable, long support cycles, subscription-based. Uses dnf.")
                    BodyText("CentOS Stream — upstream development platform for RHEL. Uses dnf.")
                    BodyText("Rocky Linux / AlmaLinux — free RHEL-compatible community rebuilds. Uses dnf.")
                    BodyText("openSUSE / SUSE Linux Enterprise — RPM-based but uses zypper, not dnf.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("DEB format (.deb files) — created by Debian. Used by:")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Debian — the original, community-run, very stable. Uses apt.")
                    BodyText("Ubuntu — most popular desktop/server Linux, based on Debian. Uses apt.")
                    BodyText("Linux Mint — desktop-friendly Ubuntu derivative. Uses apt.")
                    BodyText("Raspberry Pi OS (Raspbian) — Debian for the Raspberry Pi. Uses apt.")
                    BodyText("Pop!_OS, Kali Linux, elementary OS, Parrot OS — all Ubuntu/Debian derivatives. Use apt.")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "RPM Files") {
                    BodyText("RPM stands for both the package format (.rpm files) and the low-level command-line tool (rpm). An .rpm file is a CPIO archive wrapped in RPM headers containing metadata and optionally a PGP/GPG digital signature for verification.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The rpm command — low-level tool, does NOT resolve dependencies:")
                    CodeBlock("""
# Install a local .rpm file:
rpm -i package-1.0.x86_64.rpm
# (fails if dependencies are missing — lists what's needed)

# Erase (uninstall) a package:
rpm -e package-name

# Query: is this package installed?
rpm -q package-name          # prints version or "not installed"

# List all files installed by a package:
rpm -ql package-name

# Which installed package owns this file?
rpm -qf /usr/bin/gcc

# Show package info (description, version, deps):
rpm -qi package-name

# Verify package integrity against original checksums:
rpm -V package-name
# Output: S=size changed, M=mode changed, 5=MD5 mismatch, etc.

# List all installed packages:
rpm -qa
rpm -qa | grep python         # filter by name
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Critical limitation: if you try to install an .rpm whose dependencies are not yet installed, rpm will fail and list the missing packages. It will NOT fetch them for you. That is the job of dnf or zypper. Think of rpm as the low-level installer and dnf as the high-level package manager that calls rpm after resolving dependencies.")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "dnf — Fedora and RHEL Family") {
                    BodyText("DNF (Dandified YUM) is the high-level package manager for RPM-based distributions. It replaced YUM (Yellowdog Updater Modified) in Fedora 22 (2015) and RHEL 8 (2019). On current Fedora, RHEL 8+, CentOS Stream 8+, Rocky Linux, and AlmaLinux, dnf is the primary package tool.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Note: openSUSE/SUSE is also RPM-based but uses zypper (covered in the next section) — not dnf.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Essential dnf commands:")
                    CodeBlock("""
sudo dnf install gcc            # install a package (+ resolve deps)
sudo dnf remove gcc             # uninstall
sudo dnf upgrade                # upgrade all installed packages
sudo dnf upgrade gcc            # upgrade one specific package
dnf search "text editor"        # search by name and description
dnf info vim                    # show version, size, description, deps
dnf list --installed            # list all installed packages
dnf list --available            # list all packages in enabled repos
dnf repolist                    # show enabled repositories
dnf history                     # list all past transactions
sudo dnf history undo 5         # undo transaction #5
sudo dnf autoremove             # remove unused dependency packages
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Repositories — defined as .repo files in /etc/yum.repos.d/. Each repo file specifies a URL, GPG key for package verification, and whether it is enabled.")
                    CodeBlock("""
# Example: add the RPM Fusion repo (provides multimedia codecs,
# proprietary GPU drivers that Fedora can't ship):
sudo dnf install \
  https://download1.rpmfusion.org/free/fedora/rpmfusion-free-release-$(rpm -E %fedora).noarch.rpm
sudo dnf install ffmpeg vlc     # now available from RPM Fusion
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("dnf groups — install a curated set of packages at once: 'dnf group install \"Development Tools\"' installs gcc, make, gdb, and dozens of other dev packages. 'dnf group list' shows available groups.")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "apt — Debian and Ubuntu Family") {
                    BodyText("APT (Advanced Package Tool) is the high-level package manager for DEB-based distributions. apt is the modern user-friendly command. apt-get is the older equivalent that still works and is preferred in scripts (more stable output format). Both call dpkg (the low-level DEB tool) under the hood.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Essential apt commands:")
                    CodeBlock("""
sudo apt update                 # ALWAYS run this first: refreshes
                                # the local package index from repos
sudo apt install gcc            # install a package
sudo apt remove gcc             # uninstall (keeps config files)
sudo apt purge gcc              # uninstall + remove config files
sudo apt upgrade                # upgrade all packages (safe)
sudo apt full-upgrade           # upgrade + allow removing packages
apt search "text editor"        # search name/description
apt show vim                    # version, size, description, deps
apt list --installed            # list installed packages
sudo apt autoremove             # remove unused auto-installed deps
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("dpkg — the low-level DEB tool (equivalent of rpm):")
                    CodeBlock("""
sudo dpkg -i package_1.0_amd64.deb  # install local .deb file
dpkg -l '*python*'                   # list installed matching pattern
dpkg -L vim                          # list files installed by package
dpkg -S /usr/bin/gcc                 # which package owns this file
dpkg --get-selections                # list all installed packages
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Repositories — defined in /etc/apt/sources.list and /etc/apt/sources.list.d/*.list. Each line specifies a URL, distribution codename (e.g. 'noble' for Ubuntu 24.04), and component (main/universe/restricted/multiverse).")
                    CodeBlock("""
# Add a third-party repo (e.g. VSCode):
wget -qO- https://packages.microsoft.com/keys/microsoft.asc \
    | sudo gpg --dearmor -o /usr/share/keyrings/microsoft.gpg
echo "deb [arch=amd64 signed-by=/usr/share/keyrings/microsoft.gpg] \
  https://packages.microsoft.com/repos/code stable main" \
    | sudo tee /etc/apt/sources.list.d/vscode.list
sudo apt update && sudo apt install code
                    """.trimIndent())
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "Other Package Managers") {
                    BodyText("pacman — Arch Linux, Manjaro, EndeavourOS. Uses its own .pkg.tar.zst format. Arch is a rolling-release distro — packages are always the very latest upstream version.")
                    CodeBlock("""
sudo pacman -S gcc              # install
sudo pacman -R gcc              # remove
sudo pacman -Syu                # sync + upgrade everything
pacman -Ss "text editor"        # search
pacman -Ql vim                  # list files of installed package
pacman -Qo /usr/bin/gcc         # which package owns this file
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("AUR (Arch User Repository) — community-maintained PKGBUILDs (build scripts). Not official packages; built from source on your machine. Use an AUR helper like yay or paru: 'yay -S package-name' installs from both official repos and AUR.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("zypper — openSUSE and SUSE Linux Enterprise. RPM-based (calls rpm internally) but uses a completely different dependency solver than dnf.")
                    CodeBlock("""
sudo zypper install gcc         # install
sudo zypper remove gcc          # remove
sudo zypper refresh             # refresh repos (like apt update)
sudo zypper update              # upgrade all
zypper search gcc               # search
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("emerge — Gentoo. Source-based: every package is compiled from source on your machine using flags you configure. USE flags let you include/exclude features at compile time (e.g., USE=\"alsa -pulseaudio\" installs ALSA support but not PulseAudio).")
                    CodeBlock("""
sudo emerge --ask sys-devel/gcc  # compile and install gcc
sudo emerge --depclean           # remove unneeded deps
sudo emerge --sync               # sync Portage tree (like apt update)
                    """.trimIndent())
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "Universal Package Formats") {
                    BodyText("These formats work across distributions — they bundle all dependencies inside the package itself, avoiding conflicts with the system's native package manager.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("snap (Canonical/Ubuntu) — sandboxed packages that each carry their own runtime. Managed by the snapd daemon (always running). Auto-updates silently in the background.")
                    CodeBlock("""
snap install vlc                # install from Snap Store
snap remove vlc
snap list                       # list installed snaps
snap refresh                    # update all snaps
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Controversy: snapd always runs as a background service; packages start slightly slower than native; Canonical controls the Snap Store; forced auto-updates have broken systems. Not popular outside Ubuntu.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("flatpak (freedesktop.org) — similar sandbox model; popular on GNOME and KDE desktops across all distros. Flathub (flathub.org) is the main community repo. No always-on daemon required.")
                    CodeBlock("""
flatpak remote-add --if-not-exists flathub \
    https://dl.flathub.org/repo/flathub.flatpakrepo
flatpak install flathub org.videolan.VLC
flatpak run org.videolan.VLC
flatpak remove org.videolan.VLC
flatpak update                  # update all flatpaks
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("AppImage — a single self-contained executable file. No installation, no root, no daemon. Download, chmod +x, run. No auto-updates. Useful for distributing GUI apps that work on any Linux distro without packaging for each one.")
                    CodeBlock("""
chmod +x MyApp-1.0-x86_64.AppImage
./MyApp-1.0-x86_64.AppImage    # runs directly, no install
# To integrate with desktop (creates .desktop launcher):
./MyApp-1.0-x86_64.AppImage --appimage-integrate
                    """.trimIndent())
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SectionCard(title = "Quick Reference Table") {
                    BodyText("Package manager commands by distro family:")
                    CodeBlock("""
Task                 | dnf (Fedora/RHEL) | apt (Ubuntu/Debian)
---------------------|-------------------|--------------------
Refresh repo index   | (auto on install) | apt update
Install package      | dnf install pkg   | apt install pkg
Remove package       | dnf remove pkg    | apt remove pkg
Upgrade all          | dnf upgrade       | apt upgrade
Search               | dnf search kw     | apt search kw
Show package info    | dnf info pkg      | apt show pkg
List installed       | dnf list --inst.. | apt list --installed
Who owns file        | rpm -qf /path     | dpkg -S /path
List pkg files       | rpm -ql pkg       | dpkg -L pkg
Install local file   | rpm -i file.rpm   | dpkg -i file.deb
Transaction history  | dnf history       | (no equivalent)
Unused dep cleanup   | dnf autoremove    | apt autoremove
                    """.trimIndent())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Distro → format → high-level tool → low-level tool:")
                    CodeBlock("""
Fedora / RHEL / Rocky     →  RPM (.rpm)         →  dnf    →  rpm
openSUSE / SUSE           →  RPM (.rpm)         →  zypper →  rpm
Debian / Ubuntu / Mint    →  DEB (.deb)         →  apt    →  dpkg
Arch / Manjaro            →  .pkg.tar.zst       →  pacman →  pacman
Gentoo                    →  source (ebuilds)   →  emerge →  emerge
                    """.trimIndent())
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
