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
fun KernelPatchingScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Patching The Kernel",
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
                SectionCard(title = "Vanilla vs Distribution Kernel") {
                    BodyText("There are two fundamentally different things that both get called \"the Linux kernel\":")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The vanilla (upstream) kernel is the official tree maintained by Linus Torvalds at git.kernel.org. It is pure upstream code with no distribution-specific additions. When you see release announcements like \"Linux 6.12 released\", that refers to this tree.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The distribution kernel is what your distro actually ships. It starts from a specific upstream release but then adds:")
                    CodeBlock(
                        """1. A heavily customised .config
   - Enables many more drivers than vanilla defaults
     (a distro must boot on millions of different machines)
   - Enables distro-specific features:
     Ubuntu: livepatch (live kernel patching), PREEMPT_DYNAMIC
     RHEL: no Rust modules, specific FIPS settings
   - May disable features deemed unnecessary or risky

2. Out-of-tree patches stacked on top of the base version:
   - Security backports not yet in upstream stable
   - Hardware enablement for newer CPUs/GPUs
   - Bug fixes from upstream that haven't been released yet
   - Occasionally features unique to the distro

3. A distro-specific version string:
   Ubuntu 24.04: 6.8.0-47-generic
   Fedora 41:    6.11.4-301.fc41.x86_64
   Vanilla:      6.8.12  (a completely separate tree)"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("You can see exactly what your distro's kernel is based on: cat /proc/version shows the upstream base, and the distro usually publishes its patch series separately (Ubuntu's kernel patches are on Launchpad, RHEL's in CentOS Stream).")
                }
            }
            item {
                SectionCard(title = "Cloning Vanilla and Booting It") {
                    BodyText("Yes — you can clone Linus's tree, make any change you want, build it, and boot it on your machine. The steps:")
                    CodeBlock(
                        """# 1. Clone the upstream source (this is a large repo ~4GB)
git clone https://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git
cd linux

# 2. Use your distro's config as a starting point
#    (your hardware already boots with this config)
cp /boot/config-$(uname -r) .config
make olddefconfig
# olddefconfig sets any new Kconfig options to their default values
# so you are not asked hundreds of questions interactively

# 3. (Optional) Trim the config to speed up builds:
make localmodconfig
# This disables modules for hardware NOT currently loaded —
# useful for a dev machine, risky on hardware you rarely use

# 4. Make your change, then build
# Edit whatever source files you need, then:
make -j$(nproc)          # build kernel image and modules

# 5. Install modules and kernel
sudo make modules_install # installs .ko files to /lib/modules/<version>/
sudo make install         # copies vmlinuz + System.map to /boot
                          # and runs update-grub on Debian/Ubuntu

# 6. Reboot and select the new entry in GRUB"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("After reboot, uname -r will show the vanilla version string (e.g. 6.12.0) rather than your distro's string. The kernel is fully functional — it just lacks your distro's specific patches.")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Practical note: if your machine uses a very new GPU or Wi-Fi chip that the distro enabled via an out-of-tree patch, that hardware may not work on vanilla until the patch reaches upstream. Seeding from your distro's .config reduces this risk.")
                }
            }
            item {
                SectionCard(title = "Submitting a Patch Upstream") {
                    BodyText("The Linux kernel does NOT use GitHub pull requests. The kernel predates GitHub by over a decade and uses a mailing-list + patch-email workflow. This is a genuine workflow used by thousands of kernel developers every day.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The steps:")
                    CodeBlock(
                        """# 1. Make your fix in a clean commit
git add -p              # stage changes interactively
git commit -s           # -s adds Signed-off-by: Your Name <email>
                        # Signed-off-by is REQUIRED — it certifies
                        # you authored/have rights to the code (DCO)

# 2. Generate a patch file
git format-patch HEAD~1
# Produces: 0001-short-description-of-your-fix.patch

# 3. Check your patch follows kernel coding style
./scripts/checkpatch.pl 0001-your-fix.patch

# 4. Find who to send it to
./scripts/get_maintainer.pl 0001-your-fix.patch
# Prints the maintainer email(s) and relevant mailing list(s)
# e.g. net-dev@vger.kernel.org for networking fixes
#      linux-mm@kvack.org for memory management

# 5. Send the patch by email
git send-email \
    --to=maintainer@example.com \
    --cc=linux-kernel@vger.kernel.org \
    0001-your-fix.patch"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("What happens after you send it:")
                    CodeBlock(
                        """- Maintainer and community review on the mailing list
- They may reply with: Reviewed-by / Acked-by (approval tags)
  or ask for changes → you send v2, v3, ...
- The subsystem maintainer pulls accepted patches into their
  subsystem tree (e.g. net-next, mm, fs/ext4)
- Near the end of Linus's release cycle (rc stage), he opens
  a ~2-week merge window
- Subsystem maintainers send pull requests TO Linus during
  the merge window — he pulls entire subsystem trees
- The merge window closes; rc1 is tagged; bug-fix-only phase
  until release (~9–10 weeks total per cycle)"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("There is no GitHub involved at any step. Linus does have a mirror on GitHub but it is read-only. All real development happens on the mailing lists.")
                }
            }
            item {
                SectionCard(title = "How Long Until Your Patch Reaches a Distro?") {
                    BodyText("The timeline from \"patch accepted by maintainer\" to \"running on a user's machine\" varies enormously depending on the distro:")
                    CodeBlock(
                        """Upstream release cycle:
  Merge window    ~2 weeks  (new features accepted)
  RC phase        ~8 weeks  (bug fixes only, rc1…rc8)
  Release         tagged; new stable branch opened
  ─────────────────────────────────────────────────
  Each kernel release: roughly 9–10 weeks total

Rolling distros (Arch, openSUSE Tumbleweed):
  Days to 1–2 weeks after upstream release
  They track the latest kernel closely

Ubuntu LTS (e.g. 22.04, 24.04):
  Bug/security fixes: weeks–months via SRU process
    (Stable Release Update — must pass 7-day verification)
  New kernel version: next LTS cycle (2 years later)
  Security CVE backports: fast-tracked, often days

RHEL / CentOS Stream:
  RHEL 9 is pinned to kernel 5.14.x for its entire lifetime
  Features from a newer kernel: may never arrive in RHEL 9
    at all — they would appear in RHEL 10 (years later)
  Security fixes ARE backported to the pinned version"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("The bottom line: if your patch fixes a real bug and is accepted upstream, it will reach rolling-release users within weeks. It may take 6–18 months to reach Ubuntu LTS users via an SRU, and potentially years (or never) for the same kernel version to reach RHEL users — though RHEL will backport the fix itself to their older base kernel.")
                }
            }
            item {
                SectionCard(title = "Building a Distro-Style Kernel with Your Patch") {
                    BodyText("If you want to run a kernel that has your change AND all of the distro's patches and config (the safest approach for real-world use on your machine), build from the distro's own kernel source tree:")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Ubuntu / Debian:")
                    CodeBlock(
                        """# Method 1: get the distro source via apt
apt-get source linux-image-$(uname -r)
cd linux-*/

# Method 2: clone Ubuntu's kernel git directly
# (includes all Ubuntu patches as commits you can inspect)
git clone https://git.launchpad.net/~ubuntu-kernel/ubuntu/+source/linux
cd linux

# Apply your patch on top of the distro tree
git apply /path/to/my-fix.patch
# or: git cherry-pick <commit-hash-from-vanilla-tree>

# Build as Debian packages — preserves distro's .config and patches
# and produces clean .deb files you can install/remove like any package
make -j$(nproc) bindeb-pkg

# Install
sudo dpkg -i ../linux-image-*.deb ../linux-headers-*.deb"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Fedora / RHEL:")
                    CodeBlock(
                        """# Download the source RPM for the running kernel
dnf download --source kernel

# Install it (expands source + spec to ~/rpmbuild/)
rpm -ivh kernel-*.src.rpm

# Copy your patch into the sources directory
cp /path/to/my-fix.patch ~/rpmbuild/SOURCES/

# Edit the spec file to apply your patch:
# Add near the top:  Patch9999: my-fix.patch
# Add in %prep:      %patch9999 -p1
vim ~/rpmbuild/SPECS/kernel.spec

# Build a kernel RPM (takes a long time — full kernel build)
rpmbuild -bb ~/rpmbuild/SPECS/kernel.spec

# Install the resulting RPM
sudo dnf install ~/rpmbuild/RPMS/x86_64/kernel-*.rpm"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("The result is a properly packaged kernel that integrates with your distro's boot loader and package database. It can be removed cleanly with the normal package manager — no manual /boot cleanup needed.")
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
