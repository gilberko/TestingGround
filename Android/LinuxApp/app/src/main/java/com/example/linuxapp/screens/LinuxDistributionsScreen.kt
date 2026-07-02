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
fun LinuxDistributionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Linux Distributions",
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
                SectionCard(title = "What Is a Linux Distribution?") {
                    BodyText("The Linux kernel alone is just a kernel — it cannot run programs on its own. A Linux distribution (\"distro\") bundles the kernel with everything needed to make a usable operating system:")
                    CodeBlock(
                        """Linux kernel        — process management, drivers, memory
GNU userspace tools  — bash, ls, cp, grep, gcc, glibc, ...
Init system          — systemd (most modern distros) or SysV init
Package manager      — installs, updates, and removes software
Desktop environment  — GNOME, KDE Plasma, XFCE, etc. (optional)
Configuration        — default settings, branding, support policies"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Each distro makes different trade-offs: stability vs. freshness, ease-of-use vs. control, free vs. commercial support. Those choices determine who the distro is really for.")
                }
            }
            item {
                SectionCard(title = "The Family Tree") {
                    BodyText("Most distros are derived from one of a handful of ancestors. Deriving means starting with the ancestor's source packages, patches, and package format and then building on top:")
                    CodeBlock(
                        """Slackware (1993) ─────────────────── independent
Debian (1993) ─────────────────────── independent
  └── Ubuntu (Canonical, 2004)
        ├── Linux Mint (2006)
        ├── Kubuntu (KDE edition)
        ├── Lubuntu, Xubuntu, Ubuntu MATE
        └── Pop!_OS (System76)
Red Hat Linux → RHEL (commercial, 2002)
  ├── Fedora (community upstream of RHEL, 2003)
  └── CentOS (2004) → CentOS Stream (2020)
        └── AlmaLinux / Rocky Linux (RHEL clones)
SUSE Linux Enterprise (SLE) ────────── independent
  ├── openSUSE Leap   (stable, tracks SLE)
  └── openSUSE Tumbleweed (rolling release)
Arch Linux (2002) ─────────────────── independent, rolling
Gentoo (2002) ─────────────────────── independent, source-based"""
                    )
                }
            }
            item {
                SectionCard(title = "Debian") {
                    BodyText("Founded: 1993. One of the oldest surviving distros and the ancestor of the largest distro family. Debian is run by a volunteer community and governed by the Debian Social Contract — a pledge to remain free and open.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Three branches:")
                    CodeBlock(
                        """Stable   — released every ~2 years; rock-solid, older packages
Testing  — packages promoted from Unstable; used to build next Stable
Unstable — (codename: Sid) bleeding-edge; packages arrive here first"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Known for: the largest free software package archive (~60,000 packages), strict free-software policy (non-free repo is separate), and being the upstream source for Ubuntu and dozens of other distros.")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Target: servers, experienced users, anyone who values stability over new features. Debian Stable is common on servers because it rarely breaks.")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Package manager: apt (high-level) + dpkg (low-level). Format: .deb")
                }
            }
            item {
                SectionCard(title = "Ubuntu") {
                    BodyText("Founded: 2004 by Canonical Ltd., built on top of Debian. Ubuntu made Linux accessible to desktop users and became the most widely installed Linux on both personal computers and cloud servers (AWS, Azure, GCP all offer Ubuntu by default).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Release cadence:")
                    CodeBlock(
                        """LTS (Long-Term Support) — every 2 years (even years: 20.04, 22.04, 24.04)
                          5-year standard support, 10-year with ESM subscription
Interim releases        — every 6 months (e.g. 23.10); 9 months support
                          Used for testing new packages before the next LTS"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Canonical introduced Snap packages alongside the traditional .deb system. Snap packages are self-contained with bundled dependencies and update automatically — useful for ISVs but controversial because they are larger and slower to start than native .deb packages.")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Target: desktop users, developers, cloud VMs. Ubuntu is the de-facto standard for Linux cloud deployments.")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Package manager: apt + dpkg (.deb) plus Snap. Format: .deb / .snap")
                }
            }
            item {
                SectionCard(title = "Linux Mint") {
                    BodyText("Founded: 2006, derived from Ubuntu (and also offers a direct Debian edition). Linux Mint has become one of the most popular Linux desktops precisely because it targets users switching from Windows.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Desktop editions:")
                    CodeBlock(
                        """Cinnamon — flagship; traditional taskbar/start-menu layout
MATE      — lightweight, based on the old GNOME 2 desktop
XFCE      — very lightweight, good for older hardware"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Key choices: Mint deliberately ships without Snap by default (the snap daemon is blocked in its APT configuration). It includes multimedia codecs and proprietary drivers out of the box. The Update Manager is conservative — it labels updates by risk level and defaults to only applying safe ones.")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Target: Windows switchers, beginners, anyone who wants a desktop that \"just works\" without configuration.")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Package manager: apt + dpkg. Format: .deb")
                }
            }
            item {
                SectionCard(title = "Kubuntu") {
                    BodyText("Founded: 2005, an official Ubuntu flavour maintained by Blue Systems. Kubuntu is Ubuntu with the KDE Plasma desktop environment instead of GNOME — everything else (package repos, LTS cadence, update infrastructure) is identical to Ubuntu.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("KDE Plasma is highly customisable — window layout, themes, keyboard shortcuts, and workspace behaviour can all be tuned far beyond what GNOME allows by default. Users who want a feature-rich, Windows-like layout often prefer KDE.")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Target: users who prefer KDE Plasma; power users who want a highly configurable desktop on a stable Ubuntu base.")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Package manager: apt + dpkg. Format: .deb")
                }
            }
            item {
                SectionCard(title = "Fedora") {
                    BodyText("Founded: 2003, sponsored by Red Hat. Fedora is the cutting-edge community distro that feeds new features into RHEL. If a technology first appears in Fedora, expect it in RHEL 2–3 years later.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Fedora ships the absolute latest: newest kernel, newest GNOME release, newest Wayland compositor changes, newest systemd features. It is where major open-source projects often land first. This also means it can occasionally be less stable than Debian or Ubuntu LTS.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Editions:")
                    CodeBlock(
                        """Fedora Workstation — GNOME desktop, target: developers
Fedora Server       — headless server edition
Fedora CoreOS       — container-optimised, immutable
Fedora Spins        — community editions with other DEs (KDE, XFCE, …)"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Target: developers who want the latest open-source stack; Linux enthusiasts; upstream contributors who need to work on bleeding-edge code.")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Package manager: dnf (dnf5 from Fedora 41). Format: .rpm")
                }
            }
            item {
                SectionCard(title = "Red Hat Enterprise Linux (RHEL)") {
                    BodyText("RHEL is Red Hat's commercial Linux product. It is subscription-based — you pay for it and receive enterprise support, certified configurations, and a 10-year support lifecycle. RHEL takes features that have been proven in Fedora and stabilises them for long-term production use.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Why enterprises choose RHEL:")
                    CodeBlock(
                        """10-year lifecycle per major version (e.g. RHEL 9: 2022–2032)
Certified for SAP HANA, Oracle DB, Microsoft SQL Server
SELinux enforcing by default (mandatory access control)
Red Hat CVE team backports security fixes to older kernel versions
FIPS 140-2/3 validated cryptography builds available
Subscriptions include access to Red Hat's support engineers"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("RHEL pins to a specific upstream kernel version for the entire major version lifetime (e.g. RHEL 9 is based on kernel 5.14.x). Security fixes are backported rather than upgrading the kernel version, which provides stability but means RHEL ships older kernel APIs.")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Target: enterprise data centres, regulated industries, anywhere that requires a certified, commercially supported Linux.")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Package manager: dnf (yum in RHEL ≤7). Format: .rpm")
                }
            }
            item {
                SectionCard(title = "AlmaLinux and Rocky Linux (RHEL Clones)") {
                    BodyText("The original CentOS was a free, community-rebuilt binary clone of RHEL — same packages, different branding, no subscription needed. In December 2020 Red Hat announced that CentOS 8 would reach end-of-life in December 2021 (two years early) and shift to CentOS Stream, which is a rolling preview of the next RHEL minor release rather than a stable clone.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Two projects quickly filled the void:")
                    CodeBlock(
                        """AlmaLinux — started by CloudLinux; 1:1 RHEL binary compatible;
             governed by the AlmaLinux OS Foundation (non-profit)
Rocky Linux — started by Gregory Kurtzer (CentOS co-founder);
              also 1:1 RHEL binary compatible; community-driven"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Both aim to be drop-in replacements for CentOS 8 and track RHEL minor releases closely. Target: organisations that need RHEL-compatible software and certifications without paying for a Red Hat subscription.")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Package manager: dnf. Format: .rpm")
                }
            }
            item {
                SectionCard(title = "openSUSE") {
                    BodyText("openSUSE is the community counterpart to SUSE Linux Enterprise (SLE), a German company's commercial Linux. openSUSE comes in two very different flavours:")
                    CodeBlock(
                        """openSUSE Leap       — stable release tracking SLE; new version ~yearly;
                      conservative package versions; good for servers/workstations
openSUSE Tumbleweed — rolling release; packages updated continuously;
                      closer to Arch in freshness; good for enthusiasts/devs"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("openSUSE is well known for YaST (Yet another Setup Tool) — a comprehensive graphical and ncurses-based administration tool covering partitioning, network, firewall, users, and software in one place. Strong in European enterprise.")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Note: openSUSE uses .rpm packages but its RPM dialect and package names differ from the Red Hat family — you cannot mix Fedora/RHEL .rpm files with openSUSE without rebuilding.")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Target: Leap — servers, workstations, enterprise users. Tumbleweed — developers, enthusiasts who want rolling updates with good QA.")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Package manager: zypper (dnf-style high-level tool). Format: .rpm")
                }
            }
            item {
                SectionCard(title = "Slackware") {
                    BodyText("Founded: 1993 by Patrick Volkerding — the oldest surviving Linux distribution. Slackware is deliberately minimalist and conservative, staying as close to vanilla upstream software as possible and making very few changes to how things are configured.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Its most distinctive feature: no automatic dependency resolution. When you install a Slackware package, the package manager does not pull in dependencies — you manage them yourself. This is a design choice, not a limitation.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Other notable traits:")
                    CodeBlock(
                        """Used SysV init scripts for decades (not systemd until very recently)
Package format: simple .tgz / .txz archives (tar + gzip or xz)
No package conflicts to manage; no dependency databases to corrupt
Default shell: bash; default editor choice left to the user
Philosophy: UNIX simplicity; understand what you're installing"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Target: advanced users who want to understand Linux deeply, who prefer manual control over automation, and who value the UNIX tradition of each tool doing one thing well.")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Package manager: pkgtool (installpkg / removepkg / upgradepkg). Format: .tgz / .txz")
                }
            }
            item {
                SectionCard(title = "Arch Linux") {
                    BodyText("Founded: 2002, independent, rolling release. Arch is built on the KISS principle (Keep It Simple, Stupid). You start with a minimal base system and build up exactly the OS you want — no graphical installer, no preselected desktop, no opinion on what you should run.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("What Arch is famous for:")
                    CodeBlock(
                        """Rolling release — always up-to-date; no major upgrade cycles
pacman          — fast, simple binary package manager
AUR (Arch User Repository) — community-maintained build scripts
                             for nearly any software not in the
                             official repos; PKGBUILD format
The Arch Wiki   — widely considered the best Linux documentation
                  on the internet, useful even for non-Arch users"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Because Arch ships packages very close to upstream with minimal patching, problems in upstream software appear on Arch first. A breaking change in a library will hit Arch users days before it reaches Ubuntu LTS users (who may not see it for years).")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Target: enthusiasts and experienced users who want full control and the latest software. Often used by developers and Linux hackers as their daily driver. Manjaro is a popular beginner-friendly derivative of Arch.")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Package manager: pacman. AUR helpers: yay, paru. Format: .pkg.tar.zst")
                }
            }
            item {
                SectionCard(title = "What Is RPM?") {
                    BodyText("RPM stands for \"RPM Package Manager\" — a recursive acronym (originally \"Red Hat Package Manager\"). It is a binary package format and the low-level package database used by the entire Red Hat/SUSE ecosystem.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("An .rpm file contains:")
                    CodeBlock(
                        """Compiled binaries and data files
Metadata: package name, version, release, architecture, description
Dependency list: what other packages must be installed first
Pre/post-install scripts: run during installation or removal
File ownership and permissions for every installed file"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The rpm tool itself is the low-level layer. High-level tools (dnf, zypper, yum) sit on top of rpm and handle dependency resolution — downloading and installing all required dependencies automatically:")
                    CodeBlock(
                        """/* Low-level rpm commands: */
rpm -ivh package.rpm      # install, verbose, hash progress bar
rpm -Uvh package.rpm      # upgrade (installs if not yet present)
rpm -e package-name       # erase (remove) an installed package
rpm -qa                   # query: list all installed packages
rpm -qi package-name      # query: show info about a package
rpm -ql package-name      # query: list files the package installed
rpm -qf /usr/bin/ls       # query: which package owns this file

/* rpm does not resolve dependencies — use dnf for that: */
dnf install package-name  # installs package + all dependencies
dnf update                # update all packages
dnf remove package-name   # remove package and unneeded deps"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Important: .rpm files from Fedora/RHEL are NOT interchangeable with openSUSE .rpm files even though both use the RPM format — package names, library versions, and patch sets differ between the families.")
                }
            }
            item {
                SectionCard(title = "Package Manager Quick Reference") {
                    CodeBlock(
                        """Distro Family           Tool      Format       Notes
────────────────────────────────────────────────────────────────
Debian / Ubuntu / Mint  apt+dpkg  .deb         apt resolves deps;
                                               dpkg is the low level
Fedora                  dnf       .rpm         dnf5 from Fedora 41+
RHEL / Alma / Rocky     dnf       .rpm         yum is the legacy name;
                         (yum≤8)               same .rpm format
openSUSE                zypper    .rpm         SUSE RPM ≠ Red Hat RPM
                                               YaST wraps zypper
Slackware               pkgtool   .tgz/.txz    No dep resolution;
                                               intentional design
Arch Linux              pacman    .pkg.tar.zst AUR adds community
                        +AUR                   source packages (PKGBUILD)"""
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
