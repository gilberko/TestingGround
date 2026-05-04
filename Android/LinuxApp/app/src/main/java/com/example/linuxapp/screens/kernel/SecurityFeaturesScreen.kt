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
fun SecurityFeaturesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Kernel Security Features",
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
                SectionCard(title = "KASLR — Kernel Address Space Layout Randomization") {
                    BodyText("KASLR randomises the virtual address at which the kernel is loaded at every boot. Without it, the kernel text always begins at a fixed address (e.g. 0xffffffff81000000 on x86-64), making it trivial for an attacker to hard-code kernel addresses in an exploit. With KASLR, a random offset is added to the kernel text, data, and BSS regions at boot time.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("KASLR and /proc/kallsyms:")
                    CodeBlock(
                        """/proc/kallsyms lists every kernel symbol with its runtime
address — the KASLR-adjusted value, not the link-time address.
kptr_restrict controls visibility:

  kptr_restrict = 0   all users see real addresses
  kptr_restrict = 1   only root sees real addresses;
                      non-privileged users see 0x0
  kptr_restrict = 2   all users (including root) see 0x0
                      (strongest — used in hardened distros)

# Read current setting:
cat /proc/sys/kernel/kptr_restrict

# To read real addresses as root (temporarily):
echo 0 | sudo tee /proc/sys/kernel/kptr_restrict
sudo cat /proc/kallsyms | grep " T startup_64"
# The address shown is the KASLR-shifted load point."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("How to check if KASLR is compiled in and active:")
                    CodeBlock(
                        """# Kernel config flag:
grep CONFIG_RANDOMIZE_BASE /boot/config-$(uname -r)
# CONFIG_RANDOMIZE_BASE=y  → compiled in

# Confirm it fired at last boot:
dmesg | grep -i kaslr
# [    0.000000] KASLR enabled

# Physical memory mapping can also be randomised separately:
grep CONFIG_RANDOMIZE_MEMORY /boot/config-$(uname -r)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("How to disable (for debugging):")
                    CodeBlock(
                        """# Add to the kernel command line (GRUB or bootloader):
nokaslr

# Verify after reboot:
dmesg | grep -i kaslr
# [    0.000000] KASLR disabled

# With nokaslr, /proc/kallsyms addresses match vmlinux exactly."""
                    )
                }
            }
            item {
                SectionCard(title = "FGKASLR — Fine-Grained KASLR") {
                    BodyText("While standard KASLR shifts the entire kernel image by one random offset, FGKASLR goes further: it randomises the order of individual kernel functions within the text section at boot. Even if an attacker leaks the address of one function, the positions of all other functions are independently randomised and cannot be predicted.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("How it works:")
                    CodeBlock(
                        """Normal KASLR:
  [func_A][func_B][func_C]  →  loaded at base + random_offset
  All functions shift by the same amount. One leak → all addresses known.

FGKASLR:
  Boot-time randomiser shuffles function order:
  [func_C][func_A][func_B]  →  each function at a different random address
  One leak reveals only that one function's address."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("How to check:")
                    CodeBlock(
                        """grep CONFIG_FG_KASLR /boot/config-$(uname -r)
# CONFIG_FG_KASLR=y  → enabled (available mainline 5.16+)

# FGKASLR requires KASLR to be active; nokaslr disables both."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("How to disable:")
                    CodeBlock(
                        """# Disable only FGKASLR, keep base KASLR:
nofgkaslr

# Disable both:
nokaslr

# Note: not all distro kernels ship with CONFIG_FG_KASLR=y yet."""
                    )
                }
            }
            item {
                SectionCard(title = "SMEP — Supervisor Mode Execution Prevention") {
                    BodyText("SMEP is an x86 hardware feature (Intel IvyBridge 2012+, AMD Excavator 2015+). When enabled, the CPU raises a #PF fault if kernel-mode code (ring 0) attempts to execute from a page that has the USER bit set in its page-table entry. This blocks the classic exploit technique of placing shellcode in user space and jumping to it from a kernel vulnerability.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Mechanism — SMEP is controlled by bit 20 of the CR4 register:")
                    CodeBlock(
                        """CR4 bit 20 = 1  →  SMEP active
CR4 bit 20 = 0  →  SMEP inactive

The kernel sets this bit during early boot if the CPU supports it.
An attacker who can execute arbitrary kernel code can clear CR4[20]
with a single instruction — which is why SMEP is best combined with
other mitigations (SMAP, CFI) that prevent arbitrary kernel execution."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("How to check:")
                    CodeBlock(
                        """# CPU support:
grep smep /proc/cpuinfo          # appears in 'flags' line

# Kernel compiled with SMEP support:
grep CONFIG_X86_SMEP /boot/config-$(uname -r)
# CONFIG_X86_SMEP=y

# The kernel enables SMEP automatically at boot if the CPU has it;
# there is no separate runtime sysctl — check dmesg:
dmesg | grep -i smep"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("How to disable (debug/testing only):")
                    CodeBlock(
                        """# Kernel boot parameter:
nosmep

# Never disable on a production system — restores the shellcode-in-
# userspace attack vector."""
                    )
                }
            }
            item {
                SectionCard(title = "SMAP — Supervisor Mode Access Prevention") {
                    BodyText("SMAP is an x86 hardware feature (Intel Broadwell 2014+, AMD Zen 2017+). When enabled, any kernel-mode (ring 0) load or store that targets a user-space page raises a #PF fault — unless the kernel has explicitly opened an access window using the stac/clac instructions. This prevents exploits where an attacker passes a crafted user-space pointer to the kernel and tricks it into dereferencing it.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Mechanism — SMAP uses CR4 bit 21 and the EFLAGS AC flag:")
                    CodeBlock(
                        """CR4 bit 21 = 1  →  SMAP active

The EFLAGS.AC flag acts as a controlled gate:
  AC = 0  →  user-space accesses from ring 0 are BLOCKED
  AC = 1  →  user-space accesses from ring 0 are allowed

stac   # set AC — opens the window (before copy_from/to_user)
clac   # clear AC — closes the window (after the copy)

The kernel wraps every legitimate user-space access in stac/clac.
An attacker cannot set AC because it requires ring 0 privilege — and
SMEP/CFI prevent getting there with user-space shellcode."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("How to check:")
                    CodeBlock(
                        """# CPU support:
grep smap /proc/cpuinfo          # appears in 'flags' line

# Kernel compiled with SMAP support:
grep CONFIG_X86_SMAP /boot/config-$(uname -r)
# CONFIG_X86_SMAP=y

# Enabled automatically at boot if CPU supports it.
dmesg | grep -i smap"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("How to disable:")
                    CodeBlock(
                        """nosmap   # kernel boot parameter

# Disabling SMAP re-enables the NULL-deref and user-ptr dereference
# kernel exploit classes. Only do this on an isolated test VM."""
                    )
                }
            }
            item {
                SectionCard(title = "PAN — Privileged Access Never (ARM64)") {
                    BodyText("PAN is the ARM64 equivalent of SMAP. Introduced in ARMv8.1, it prevents kernel code running at EL1 from reading or writing EL0 (user-space) memory directly. Legitimate user-space accesses go through explicit UAO (User Access Override) instructions, mirroring how x86 uses stac/clac.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Hardware vs. software PAN:")
                    CodeBlock(
                        """Hardware PAN (ARMv8.1+):
  Controlled by PAN bit in PSTATE.
  PAN = 1  →  EL1 cannot access EL0 pages (fault)
  PAN = 0  →  EL1 can access EL0 pages

  uao (User Access Override) instruction clears PAN temporarily
  for explicit user-space copy operations, then it is restored.

Software PAN (CONFIG_ARM64_SW_TTBR0_PAN):
  For pre-ARMv8.1 cores that lack the hardware bit.
  The kernel unmaps the user-space page table (TTBR0_EL1 points to
  an empty reserved table) while running in the kernel; it restores
  the real user page tables only during explicit user-space accesses.
  Slightly higher overhead than hardware PAN."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("How to check:")
                    CodeBlock(
                        """# Hardware PAN support visible in CPU features:
grep -E 'pan|Features' /proc/cpuinfo

# Kernel config:
grep CONFIG_ARM64_PAN /boot/config-$(uname -r)
# CONFIG_ARM64_PAN=y

grep CONFIG_ARM64_SW_TTBR0_PAN /boot/config-$(uname -r)
# CONFIG_ARM64_SW_TTBR0_PAN=y  (software emulation for older SoCs)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("How to disable:")
                    CodeBlock(
                        """# Kernel boot parameter:
arm64.nopan

# On systems with only software PAN, it can also be disabled via:
# CONFIG_ARM64_SW_TTBR0_PAN=n at build time."""
                    )
                }
            }
            item {
                SectionCard(title = "Stack Canaries") {
                    BodyText("Stack canaries are a compiler-level mitigation against stack buffer overflows. The compiler inserts a random value — the canary — between a function's local variables and the saved return address. On function return, the canary is checked: if it has been overwritten, the program calls a stack-smash handler (__stack_chk_fail), which in the kernel triggers an immediate panic.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("How canaries work in generated code:")
                    CodeBlock(
                        """Function prologue:
  mov  rax, QWORD PTR fs:0x28   # load canary from TLS slot
  mov  QWORD PTR [rbp-0x8], rax # store it between locals and ret addr

Function epilogue:
  mov  rdx, QWORD PTR [rbp-0x8] # reload canary from stack
  xor  rdx, QWORD PTR fs:0x28   # XOR with original
  jne  __stack_chk_fail          # mismatch → smash detected

In the kernel, __stack_chk_guard is a per-CPU variable populated with
a random value during early boot."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Kernel config options:")
                    CodeBlock(
                        """CONFIG_STACKPROTECTOR:
  Uses -fstack-protector.
  Protects functions that contain local arrays ≥ 8 bytes.

CONFIG_STACKPROTECTOR_STRONG (recommended):
  Uses -fstack-protector-strong.
  Broader: also protects functions with local address-taken
  variables, local arrays of any size, or structs containing arrays.

# Check which is enabled:
grep -E 'STACKPROTECTOR' /boot/config-$(uname -r)
# CONFIG_STACKPROTECTOR_STRONG=y"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Limitations — canaries only stop linear overwrites:")
                    CodeBlock(
                        """A linear buffer overflow must overwrite the canary before
reaching the return address → detected.

But if an attacker has an arbitrary-write primitive they can:
  - Overwrite the return address without touching the canary.
  - Leak the canary value first, then include it in the overflow payload.

Canaries are compile-time only — there is no runtime sysctl to
enable or disable them on a live kernel."""
                    )
                }
            }
            item {
                SectionCard(title = "CFI — Control Flow Integrity") {
                    BodyText("CFI prevents an attacker from hijacking indirect calls (function pointers) or return addresses to redirect kernel execution to arbitrary code. Two complementary halves cover the two directions of control flow:")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Forward-edge CFI — CONFIG_CFI_CLANG:")
                    CodeBlock(
                        """Protects indirect calls (call *rax, blr x8, etc.).

How it works (Clang):
  At compile time, Clang groups all functions by their exact type
  signature and builds a jump table for each type.
  Every indirect call is replaced with a bounds check:
    if (target not in valid_table_for_this_type) → kernel panic

Requires:
  CONFIG_CFI_CLANG=y
  CONFIG_LTO_CLANG=y   (Link-Time Optimisation — Clang must see all
                         translation units to build the type tables)

Check:
  grep CONFIG_CFI_CLANG /boot/config-$(uname -r)
  grep CONFIG_LTO_CLANG /boot/config-$(uname -r)

Who ships it:
  Android kernels (GKI) since 5.13.
  Mainline x86 uses IBT instead (see below).
  Mainline ARM64 has CONFIG_CFI_CLANG available since 6.1."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Backward-edge CFI — shadow call stack and IBT:")
                    CodeBlock(
                        """Shadow Call Stack (CONFIG_SHADOW_CALL_STACK) — ARM64:
  Return addresses are saved in a separate "shadow stack" in the
  x18 register's backing storage, inaccessible to normal loads/stores.
  On function return, the shadow-stack return address is used instead
  of the one on the regular stack — a corrupted stack return address
  is ignored.

  grep CONFIG_SHADOW_CALL_STACK /boot/config-$(uname -r)

IBT — Indirect Branch Tracking (CONFIG_X86_KERNEL_IBT) — x86:
  Part of Intel CET (Control-flow Enforcement Technology).
  Every valid indirect-call target must begin with an ENDBR64
  instruction. The CPU raises a fault if a branch lands elsewhere.
  Paired with shadow stacks at the hardware level.

  grep CONFIG_X86_KERNEL_IBT /boot/config-$(uname -r)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("CFI is compile-time only — there is no runtime switch. Disabling requires a kernel rebuild without the relevant config flags.")
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
