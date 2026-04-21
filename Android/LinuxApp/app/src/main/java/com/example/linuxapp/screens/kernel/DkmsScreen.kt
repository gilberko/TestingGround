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
fun DkmsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "DKMS",
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
                SectionCard(title = "The Deployment Problem") {
                    BodyText("When you build a loadable kernel module (.ko file), it is compiled against a specific version of the kernel. The module is tightly bound to that version's ABI — internal symbols, function signatures, struct layouts.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("When the customer's kernel updates, the module breaks. The kernel's module loader checks a 'vermagic' string and symbol CRCs at load time; a mismatch causes insmod or modprobe to refuse the module with an error.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The problem: the customer's machine is not under your control. Distros like Ubuntu and RHEL push kernel updates automatically. What happens to your module when the kernel changes under it?")
                }
            }
            item {
                SectionCard(title = "Option 1 — Ship Many Pre-Built Versions") {
                    BodyText("One approach is to maintain a separate compiled .ko for every kernel version you want to support. When the customer updates, they install the matching binary.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("In practice this explodes quickly: kernel version × CPU architecture × distro variant. You end up maintaining hundreds of binaries, all needing to be tested and kept in sync with source changes. Not sustainable for a long-lived product.")
                }
            }
            item {
                SectionCard(title = "Option 2 — Upstream to the Mainline Kernel") {
                    BodyText("If the module provides something of general usefulness, it can be submitted to the upstream Linux kernel. Once merged, it lives inside the kernel tree and is maintained by the community — no compatibility gap ever.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The catch: the process is slow. A patch must be reviewed by maintainers, pass multiple rounds of feedback, be accepted into a subsystem tree, hit a merge window, and finally land in a stable release. This commonly takes 6–18 months before the code is available in a distro kernel.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Also not viable if the module is proprietary, product-specific, or subject to a short delivery deadline.")
                }
            }
            item {
                SectionCard(title = "Option 3 — Version Lock") {
                    BodyText("A simpler approach: declare that the module only supports specific kernel versions. The installer checks and refuses to proceed on unsupported kernels.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("This is acceptable in embedded or appliance contexts where you fully control the OS image and can prevent kernel updates. For general-purpose deployments on customer machines it is a poor experience — a routine OS update silently breaks the product.")
                }
            }
            item {
                SectionCard(title = "DKMS — Dynamic Kernel Module Support") {
                    BodyText("DKMS stands for Dynamic Kernel Module Support. The core idea: instead of shipping a compiled .ko, ship the module source code together with a small descriptor file (dkms.conf). The DKMS framework on the customer machine compiles the module against whatever kernel headers are currently installed.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("When a new kernel is installed, DKMS automatically rebuilds and installs the module for that kernel version. The source lives in /usr/src/<name>-<version>/ and is registered with the DKMS database.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("A minimal dkms.conf looks like:")
                    CodeBlock(
                        """PACKAGE_NAME="mymodule"
PACKAGE_VERSION="1.0"
BUILT_MODULE_NAME[0]="mymodule"
DEST_MODULE_LOCATION[0]="/kernel/drivers/misc"
AUTOINSTALL="yes""""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("AUTOINSTALL=\"yes\" tells DKMS to rebuild this module automatically whenever a new kernel is installed.")
                }
            }
            item {
                SectionCard(title = "Secure Boot & Module Signing") {
                    BodyText("Secure Boot is a UEFI feature that prevents unsigned or untrusted code from running. The firmware holds a database of trusted certificates (the Secure Boot DB). The bootloader, kernel, and kernel modules must all be signed by a key whose certificate is in that database.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("When Secure Boot is active, the kernel enforces module signing at load time. insmod will reject any .ko that is not signed by a trusted key, even if the module is otherwise valid.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("This conflicts with DKMS: the module is built fresh on the customer machine, so there is no pre-existing signature from the vendor.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The solution is MOK — Machine Owner Key. MOK is a separate certificate database (MokList) managed by the shim bootloader and the mokutil tool. The machine owner can enrol their own certificates into MokList; the kernel trusts modules signed by any key whose certificate is in MokList.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Workflow to create and enrol a signing key:")
                    CodeBlock(
                        """# Generate a key pair
openssl req -new -x509 -newkey rsa:2048 \
  -keyout mok.key -out mok.crt \
  -days 3650 -subj "/CN=DKMS Signing/"

# Enrol the public cert (user confirms on next reboot)
mokutil --import mok.crt

# After reboot and MOK enrolment, sign a module manually
/usr/src/linux-headers-$(uname -r)/scripts/sign-file \
  sha256 mok.key mok.crt mymodule.ko"""
                    )
                }
            }
            item {
                SectionCard(title = "Configuring DKMS to Build and Sign") {
                    BodyText("DKMS can be configured to sign the newly built module automatically after each build, so the customer never has to sign manually.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("On modern distros (e.g. Ubuntu 22.04+), configure /etc/dkms/framework.conf:")
                    CodeBlock("""sign_tool="/etc/dkms/sign_helper.sh"""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("sign_helper.sh receives the path to the freshly built .ko as \$1 and calls sign-file with the stored MOK private key and certificate.")
                    CodeBlock(
                        """#!/bin/sh
/usr/src/linux-headers-$(uname -r)/scripts/sign-file \
  sha256 /var/lib/dkms/mok.key /var/lib/dkms/mok.crt "$1""""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("How does DKMS know the kernel was updated? The package manager (apt/dnf) installs matching linux-headers-<version> packages whenever a new kernel arrives. DKMS registers a postinst hook with the package manager; when those headers land, the hook triggers 'dkms autoinstall', which rebuilds and signs all registered modules for the new kernel version.")
                }
            }
            item {
                SectionCard(title = "What If the Build Fails?") {
                    BodyText("If the module source is incompatible with the new kernel headers — for example a kernel API changed — the dkms autoinstall step will fail during compilation.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The result: the module simply does not exist for the new kernel. The machine boots normally, but the module is not loaded. The old .ko for the previous kernel version is not used — it would not match the new kernel anyway.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The build log is saved to:")
                    CodeBlock("/var/lib/dkms/<name>/<version>/build/make.log")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Running 'dkms status' will show the module as 'broken' for the new kernel version.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("There is no universal pop-up notification. The failure typically surfaces in the package manager's postinst output and in system logs. Operators who need proactive alerting must add their own — for example a systemd service or timer that periodically checks 'dkms status' and sends an alert if a module is broken.")
                }
            }
            item {
                SectionCard(title = "eBPF & CO-RE — An Alternative") {
                    BodyText("CO-RE stands for Compile Once — Run Everywhere. It is a feature of the eBPF ecosystem that solves the same kernel-version compatibility problem without requiring DKMS, source shipping, or module signing.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("An eBPF program compiled with CO-RE (using libbpf and BTF — BPF Type Format — type information) embeds metadata about the kernel structs it accesses. At load time, libbpf reads the running kernel's BTF and patches the program's field offsets to match the actual kernel layout.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The result: a single pre-compiled .bpf.o binary runs correctly across a wide range of kernel versions, with no recompilation on the customer machine. There is also no Secure Boot signing requirement — eBPF programs are not kernel modules and are not subject to module signing enforcement.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Trade-off: eBPF cannot replace all LKM use cases. You cannot implement a new filesystem, a network device driver, or other deep kernel extensions in eBPF. But for tracing, observability, security policy enforcement (via LSM hooks), and network filtering, CO-RE eBPF is the modern preferred alternative to shipping a kernel module.")
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
