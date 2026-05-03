package com.example.windowsapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.windowsapp.ui.theme.HackerGreen

@Composable
fun SystemServicesScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "SYSTEM SERVICES",
            color = HackerGreen,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "─".repeat(28),
            color = HackerGreen,
            fontFamily = FontFamily.Monospace,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // OVERVIEW
        SectionHeader("OVERVIEW")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "A system service (system call) is the mechanism by which user-mode code requests " +
            "a kernel-mode operation — file I/O, memory allocation, process creation, etc. " +
            "It is conceptually identical to the Linux syscall: user code triggers a CPU " +
            "instruction that causes a privilege transition into the kernel, which performs " +
            "the work and returns the result.\n\n" +
            "On Windows, user-mode applications call documented Win32 APIs " +
            "(CreateFile, ReadFile, VirtualAlloc...) which are thin wrappers in kernel32.dll " +
            "and ntdll.dll. ntdll contains the actual system call stubs that cross the " +
            "kernel boundary."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // RING 0 AND RING 3
        SectionHeader("RING 0 AND RING 3")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "x86 / x64 CPUs define four privilege levels (rings 0–3):"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Ring 0  — kernel mode  — full hardware access, privileged instructions,\n" +
            "                         all memory readable/writable\n" +
            "Ring 1  — (unused by Windows)\n" +
            "Ring 2  — (unused by Windows)\n" +
            "Ring 3  — user mode    — restricted instructions, limited memory access,\n" +
            "                         cannot touch I/O ports or MSRs directly"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Windows uses only ring 0 (kernel: ntoskrnl, drivers) and ring 3 (all user " +
            "processes). Rings 1 and 2 are never used.\n\n" +
            "With hardware virtualization (Intel VT-x / AMD-V), a hypervisor runs at " +
            "VMX root mode — sometimes called \"ring -1\" — which has authority over " +
            "ring 0 itself. Windows Hyper-V and VBS use this for Credential Guard and HVCI."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // THE TRANSITION
        SectionHeader("THE SYSCALL INSTRUCTION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "When user mode (ring 3) needs to call into the kernel (ring 0), it executes " +
            "a special CPU instruction that atomically switches privilege level, loads the " +
            "kernel stack pointer, and jumps to a fixed kernel entry point.\n\n" +
            "Windows uses different instructions depending on architecture:\n\n" +
            "  x64 (64-bit Windows):   syscall  — AMD64 fast system call\n" +
            "  x86 (32-bit Windows,\n" +
            "       Pentium Pro+):      sysenter — Intel fast system call\n" +
            "  x86 (very old Windows): int 0x2E — software interrupt (legacy, slow)\n\n" +
            "The kernel entry points are:\n" +
            "  x64: KiSystemCall64  (address stored in LSTAR MSR)\n" +
            "  x86: KiFastCallEntry (address stored in SYSENTER_EIP_MSR)\n\n" +
            "These MSRs are written by the kernel at boot. When syscall/sysenter fires, " +
            "the CPU reads the MSR and jumps there — no memory lookup, no interrupt table."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // THE SERVICE INDEX
        SectionHeader("THE SERVICE INDEX")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Before executing syscall, the ntdll stub loads the service number into the " +
            "EAX register. This is an index, NOT an address. The kernel uses it to look up " +
            "the actual function pointer in the SSDT.\n\n" +
            "On x64 Windows, the 32-bit index is structured as:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "EAX bits [11: 0]  — index within the service table (up to 4096 entries)\n" +
            "EAX bits [13:12]  — which service table:\n" +
            "                    0 = Nt* table  (KeServiceDescriptorTable)\n" +
            "                    1 = Win32K GUI table (KeServiceDescriptorTableShadow)\n" +
            "                        used for NtGdi*, NtUser* calls from GUI threads"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Windows intentionally does not document or stabilize service numbers. They " +
            "change between Windows versions and sometimes between cumulative updates. " +
            "User-mode code always goes through ntdll, which contains the current numbers " +
            "compiled in at build time — never hardcode a service number."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // THE SSDT
        SectionHeader("THE SSDT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "SSDT (System Service Descriptor Table) is the informal name for the kernel " +
            "symbol KeServiceDescriptorTable. It is a small structure of type " +
            "KSERVICE_TABLE_DESCRIPTOR:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "typedef struct _KSERVICE_TABLE_DESCRIPTOR {\n" +
            "    PULONG_PTR ServiceTableBase;     // pointer to the function table\n" +
            "    PULONG     ServiceCounterTableBase; // call counters (NULL in release)\n" +
            "    ULONG_PTR  NumberOfServices;     // number of entries in the table\n" +
            "    PUCHAR     ParamTableBase;       // argument sizes (x86 only)\n" +
            "} KSERVICE_TABLE_DESCRIPTOR;"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "ServiceTableBase on x64 does not store absolute function addresses. Instead " +
            "each entry is a 32-bit signed offset from the table base address. The kernel " +
            "computes: handler = ServiceTableBase + (entry >> 4). This indirection makes " +
            "naive patching harder.\n\n" +
            "The main table (index 0) holds the Nt* kernel functions — NtReadFile, " +
            "NtCreateProcess, NtAllocateVirtualMemory, etc. Windows 11 x64 has roughly " +
            "450–500 entries in this table.\n\n" +
            "The shadow table (index 1, KeServiceDescriptorTableShadow) extends this with " +
            "the Win32K subsystem calls used by GUI threads: NtGdiXxx, NtUserXxx. It is " +
            "only active for threads that have called into win32k.sys."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // EXAMPLE
        SectionHeader("EXAMPLE: NtReadFile")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Tracing a ReadFile call from user mode to kernel:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "; ntdll!NtReadFile stub (x64 Windows)\n" +
            "NtReadFile:\n" +
            "    mov r10, rcx          ; syscall clobbers rcx, save it in r10\n" +
            "    mov eax, 6            ; service number for NtReadFile\n" +
            "                         ; (exact value varies by Windows version)\n" +
            "    syscall               ; ring 3 → ring 0, jump to KiSystemCall64\n" +
            "    ret"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Once in KiSystemCall64:\n\n" +
            "  1. CPU has already switched to ring 0 and loaded the kernel stack (RSP " +
            "from SYSCALL_STACK_POINTER area).\n" +
            "  2. Kernel saves all registers, builds a KTRAP_FRAME on the kernel stack.\n" +
            "  3. EAX (=6) is used: table index = EAX & 0xFFF, table selector = EAX >> 12.\n" +
            "  4. ServiceTableBase[6] is decoded to get the address of nt!NtReadFile.\n" +
            "  5. Arguments are loaded (rcx/r10, rdx, r8, r9, and stack args).\n" +
            "  6. nt!NtReadFile is called. It validates arguments with ProbeForRead/Write, " +
            "builds an IRP, sends it down the device stack, and eventually returns.\n" +
            "  7. KiSystemCallExit restores registers, executes sysret, returning to ring 3."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // PATCHGUARD
        SectionHeader("IS THE SSDT GUARDED?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "It depends on the Windows edition and bitness.\n\n" +
            "32-bit Windows (x86) — NO protection:\n" +
            "SSDT hooking (patching ServiceTableBase entries to redirect calls) was a " +
            "standard technique for security software and rootkits on 32-bit Windows. " +
            "The OS does not enforce the integrity of the table.\n\n" +
            "64-bit Windows Vista and later — PatchGuard (KPP):\n" +
            "Kernel Patch Protection runs encrypted, obfuscated verification routines at " +
            "randomized intervals in randomized contexts. It checks the integrity of critical " +
            "kernel structures including:\n" +
            "  • SSDT (KeServiceDescriptorTable / Shadow)\n" +
            "  • IDT (Interrupt Descriptor Table)\n" +
            "  • GDT (Global Descriptor Table)\n" +
            "  • MSRs (LSTAR, SYSCALL_MASK)\n" +
            "  • Certain kernel code sections\n\n" +
            "If any of these are modified, PatchGuard triggers an immediate system crash:\n" +
            "  Bug Check 0x109: CRITICAL_STRUCTURE_CORRUPTION\n\n" +
            "PatchGuard is x64-only. Its randomized timing makes it practically impossible " +
            "to patch the SSDT safely without first disabling PatchGuard itself.\n\n" +
            "Windows 10+ with HVCI (Hypervisor-Protected Code Integrity):\n" +
            "The hypervisor (VTL 1) enforces kernel code integrity at the hardware level " +
            "using second-level address translation (SLAT / EPT). Even ring-0 code cannot " +
            "write to kernel read-only pages. This makes SSDT modification impossible even " +
            "for code running in the kernel — the write simply generates a fault."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
