package com.example.developmentapp.screens.assembly

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
import com.example.developmentapp.screens.BodyText
import com.example.developmentapp.screens.CodeBlock
import com.example.developmentapp.screens.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SyscallIntScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Syscall & Int",
                        color      = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontSize   = 16.sp
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
            modifier       = Modifier.fillMaxSize().padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            item {
                SectionCard("Software Interrupts — INT N") {
                    BodyText(
                        "The INT N instruction generates a software interrupt with vector number N. " +
                        "The CPU uses the Interrupt Descriptor Table (IDT) to find the handler."
                    )
                    BodyText("What happens when INT N executes:")
                    CodeBlock(
                        "1. CPU looks up IDT[N] — finds gate descriptor (handler address + DPL)\n" +
                        "2. Checks CPL ≤ DPL (privilege check — prevents user from calling ring 0 gates)\n" +
                        "3. If ring change needed: loads SS:RSP from TSS (Task State Segment)\n" +
                        "4. Pushes onto new stack:  SS, RSP, RFLAGS, CS, RIP  (in that order)\n" +
                        "5. Clears IF (disables hardware interrupts) if interrupt gate\n" +
                        "6. Loads CS:RIP from the gate descriptor → jumps to handler\n" +
                        "7. CPL = 0 (ring 0)"
                    )
                    BodyText(
                        "INT 0x80 was the classic Linux 32-bit system call gate. " +
                        "INT 3 (0xCC) is the breakpoint exception used by debuggers."
                    )
                }
            }

            item {
                SectionCard("SYSCALL — Fast 64-bit System Call Entry") {
                    BodyText(
                        "SYSCALL is the preferred method for system calls in 64-bit mode. " +
                        "It is faster than INT because it avoids the IDT lookup and stack manipulation."
                    )
                    BodyText("What SYSCALL does:")
                    CodeBlock(
                        "1. Saves RIP  → RCX   (return address)\n" +
                        "2. Saves RFLAGS → R11 (flags to restore on return)\n" +
                        "3. Masks RFLAGS with IA32_FMASK MSR (clears IF and others)\n" +
                        "4. Loads new CS from IA32_STAR MSR (bits 47:32)\n" +
                        "5. Loads new RIP from IA32_LSTAR MSR (kernel entry point)\n" +
                        "6. CPL = 0  (but does NOT switch stack — kernel does that itself)\n" +
                        "7. Kernel saves RCX/R11 and switches to kernel stack"
                    )
                    BodyText("Note: SYSCALL does not push anything to the stack — RCX and R11 are the implicit save registers, which is why they are caller-saved across a syscall boundary.")
                }
            }

            item {
                SectionCard("SYSENTER — Fast 32-bit System Call Entry (Intel)") {
                    BodyText(
                        "SYSENTER is the 32-bit equivalent of SYSCALL (Intel, deprecated in 64-bit mode). " +
                        "AMD's 32-bit equivalent is SYSCALL in legacy mode."
                    )
                    CodeBlock(
                        "MSRs used by SYSENTER:\n" +
                        "  IA32_SYSENTER_CS  — target CS (and SS = CS+8)\n" +
                        "  IA32_SYSENTER_EIP — kernel entry point\n" +
                        "  IA32_SYSENTER_ESP — kernel stack pointer\n\n" +
                        "SYSENTER:\n" +
                        "  Loads CS, EIP, SS, ESP from MSRs\n" +
                        "  CPL = 0\n" +
                        "  Does NOT save EIP/ESP — caller must save them first"
                    )
                    BodyText("Because SYSENTER doesn't save the return address, user space (via the vDSO in Linux) saves EIP and ESP before calling it.")
                }
            }

            item {
                SectionCard("Returning to User Space") {
                    BodyText("There are three main ways to return from kernel to user mode:")
                    CodeBlock(
                        "IRET / IRETQ\n" +
                        "  Used to return from INT-based entry (and hardware interrupts/exceptions)\n" +
                        "  Pops: RIP, CS, RFLAGS, RSP, SS  (in 64-bit mode)\n" +
                        "  Restores CPL from the CS selector popped\n\n" +
                        "SYSRET\n" +
                        "  Used to return from SYSCALL-based entry\n" +
                        "  Restores RIP ← RCX,  RFLAGS ← R11\n" +
                        "  Loads user CS/SS from IA32_STAR MSR (bits 63:48)\n" +
                        "  CPL = 3\n" +
                        "  Fast — no memory pop\n\n" +
                        "SYSEXIT\n" +
                        "  Used to return from SYSENTER-based entry\n" +
                        "  Restores EIP ← EDX,  ESP ← ECX\n" +
                        "  CPL = 3"
                    )
                }
            }

            item {
                SectionCard("Linux System Calls — Example") {
                    BodyText("In 64-bit Linux (System V ABI):")
                    CodeBlock(
                        "Syscall number → RAX\n" +
                        "Arguments     → RDI, RSI, RDX, R10, R8, R9\n" +
                        "                (note R10 instead of RCX — RCX is clobbered by SYSCALL)\n" +
                        "Return value  ← RAX  (negative = -errno on error)\n\n" +
                        "; Example: write(1, buf, len)  — syscall number 1\n" +
                        "mov rax, 1          ; __NR_write\n" +
                        "mov rdi, 1          ; fd = stdout\n" +
                        "lea rsi, [buf]      ; buffer pointer\n" +
                        "mov rdx, 13         ; length\n" +
                        "syscall\n" +
                        "; return value in RAX (bytes written, or -errno)\n\n" +
                        "; Example: exit(0)  — syscall number 60\n" +
                        "mov rax, 60         ; __NR_exit\n" +
                        "xor rdi, rdi        ; exit code 0\n" +
                        "syscall"
                    )
                    BodyText("The kernel entry point (pointed to by IA32_LSTAR) is entry_SYSCALL_64 in arch/x86/entry/entry_64.S. It switches to the kernel stack, saves all registers, then calls do_syscall_64() which indexes into sys_call_table[].")
                }
            }

            item {
                SectionCard("IDT — Interrupt Descriptor Table (Linux & Windows)") {
                    BodyText(
                        "The IDT is a 256-entry table of gate descriptors loaded into the CPU via the LIDT instruction. " +
                        "Each entry handles one interrupt or exception vector."
                    )
                    CodeBlock(
                        "IDT entry types:\n" +
                        "  Interrupt Gate — clears IF on entry (hardware IRQs, most exceptions)\n" +
                        "  Trap Gate      — preserves IF (software debug traps, INT 3)\n" +
                        "  Task Gate      — 32-bit only, not used in 64-bit\n\n" +
                        "Notable vectors:\n" +
                        "  0   #DE  Divide Error\n" +
                        "  1   #DB  Debug Exception\n" +
                        "  3   #BP  Breakpoint (INT 3)\n" +
                        "  6   #UD  Invalid Opcode\n" +
                        "  8   #DF  Double Fault\n" +
                        "  13  #GP  General Protection Fault\n" +
                        "  14  #PF  Page Fault\n" +
                        "  32–255  External hardware IRQs and software vectors"
                    )
                    BodyText(
                        "Linux: The IDT is set up in arch/x86/kernel/idt.c. Vectors 0–31 are CPU exceptions; " +
                        "32–127 are hardware IRQs; 128 (0x80) was the legacy Linux syscall gate; 0x80–0xFF are used for IPIs and local APIC."
                    )
                    BodyText(
                        "Windows: The IDT is also a 256-entry table. Windows uses KiInterruptDispatch and related stubs as handlers. " +
                        "Hardware IRQs go through the HAL (Hardware Abstraction Layer)."
                    )
                }
            }

            item {
                SectionCard("sys_call_table — Linux Syscall Dispatch") {
                    BodyText(
                        "Linux dispatches system calls through a function pointer table called sys_call_table. " +
                        "The syscall number in RAX is used as an index."
                    )
                    CodeBlock(
                        "// Simplified (arch/x86/entry/syscall_64.c):\n" +
                        "asmlinkage const sys_call_ptr_t sys_call_table[] = {\n" +
                        "    [0]  = sys_read,\n" +
                        "    [1]  = sys_write,\n" +
                        "    [2]  = sys_open,\n" +
                        "    ...\n" +
                        "    [60] = sys_exit,\n" +
                        "};\n\n" +
                        "// In do_syscall_64():\n" +
                        "regs->ax = sys_call_table[nr](regs);"
                    )
                    BodyText(
                        "Rootkits and security tools sometimes hook sys_call_table by overwriting entries " +
                        "(write-protected since kernel 5.3 via CR0.WP manipulation or direct page table patching). " +
                        "Modern kernels use kprobes and eBPF for safe tracing instead."
                    )
                }
            }

            item {
                SectionCard("SSDT — System Service Descriptor Table (Windows)") {
                    BodyText(
                        "Windows dispatches system calls through the SSDT (System Service Descriptor Table), " +
                        "the equivalent of Linux's sys_call_table."
                    )
                    CodeBlock(
                        "// Simplified SSDT structure (ntoskrnl.exe):\n" +
                        "typedef struct _SYSTEM_SERVICE_TABLE {\n" +
                        "    PULONG_PTR ServiceTable;   // array of function pointers\n" +
                        "    PULONG     CounterTable;   // call count per service (debug builds)\n" +
                        "    ULONG      ServiceLimit;   // number of entries\n" +
                        "    PUCHAR     ArgumentTable;  // argument byte counts\n" +
                        "} SYSTEM_SERVICE_TABLE;"
                    )
                    BodyText("Windows system call flow (64-bit):")
                    CodeBlock(
                        "User space (ntdll.dll):\n" +
                        "  mov eax, <syscall_number>   ; e.g. 0x0025 = NtWriteFile\n" +
                        "  syscall                     ; enter kernel\n\n" +
                        "Kernel (ntoskrnl.exe):\n" +
                        "  KiSystemCall64              ; entry stub, saves registers\n" +
                        "  KiSystemServiceUser         ; validates and dispatches\n" +
                        "  SSDT[syscall_number]()      ; calls e.g. NtWriteFile\n" +
                        "  sysret                      ; return to user"
                    )
                    BodyText(
                        "There are two SSDTs: the main one (KeServiceDescriptorTable, Win32k.sys functions) " +
                        "and the shadow SSDT (KeServiceDescriptorTableShadow) which also includes GUI services. " +
                        "SSDT hooking is a classic antivirus and rootkit technique; PatchGuard (KPP) on 64-bit Windows " +
                        "actively detects and blue-screens any modification."
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
