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
fun X86EnvironmentScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "x86 & x86-64 Environment",
                        color      = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontSize   = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector        = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint               = Color.White
                        )
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
            // 1 — GPRs 32-bit
            item {
                SectionCard("General-Purpose Registers (32-bit / x86)") {
                    BodyText("The eight 32-bit general-purpose registers in classic x86:")
                    CodeBlock(
                        "EAX — accumulator  (return value, arithmetic)\n" +
                        "ECX — counter      (loops, shift counts)\n" +
                        "EDX — data         (I/O, high half of MUL/DIV result)\n" +
                        "EBX — base         (callee-saved, general use)\n" +
                        "ESP — stack pointer (top of stack; avoid clobbering)\n" +
                        "EBP — base pointer  (base of current stack frame)\n" +
                        "ESI — source index  (string/memory source)\n" +
                        "EDI — dest index    (string/memory destination)"
                    )
                    BodyText(
                        "Each 32-bit register has 16-bit (AX, CX, …) and 8-bit sub-register aliases. " +
                        "For EAX: AX is the low 16 bits, AH is bits 15–8, AL is bits 7–0."
                    )
                    CodeBlock(
                        "EAX (32-bit): [AH | AL] = AX (16-bit low half)\n" +
                        "              AH = bits 15–8,  AL = bits 7–0"
                    )
                }
            }

            // 2 — GPRs 64-bit
            item {
                SectionCard("General-Purpose Registers (64-bit / x86-64)") {
                    BodyText(
                        "x86-64 extends all eight registers to 64-bit (RAX, RCX, RDX, RBX, RSP, RBP, RSI, RDI) " +
                        "and adds eight new ones: R8 through R15."
                    )
                    CodeBlock(
                        "64-bit: RAX RCX RDX RBX RSP RBP RSI RDI R8–R15\n" +
                        "32-bit: EAX ECX EDX EBX ESP EBP ESI EDI R8D–R15D\n" +
                        "16-bit: AX  CX  DX  BX  SP  BP  SI  DI  R8W–R15W\n" +
                        " 8-bit: AL  CL  DL  BL  SPL BPL SIL DIL R8B–R15B"
                    )
                    BodyText(
                        "Writing to the 32-bit alias (e.g. EAX) automatically zero-extends the result into the full 64-bit register (RAX). " +
                        "Writing to the 16-bit alias (AX) does NOT zero-extend — the upper 48 bits are unchanged."
                    )
                }
            }

            // 3 — IP / SP / BP
            item {
                SectionCard("Instruction Pointer, Stack Pointer & Base Pointer") {
                    BodyText("EIP / RIP — Instruction Pointer")
                    BodyText(
                        "Always points to the next instruction to execute. You cannot write to it directly; " +
                        "it is updated by JMP, CALL, RET, and interrupt-return instructions."
                    )
                    BodyText("ESP / RSP — Stack Pointer")
                    BodyText(
                        "Points to the current top of the stack. The stack grows downward (toward lower addresses). " +
                        "PUSH: RSP -= 8, then write. POP: read, then RSP += 8."
                    )
                    BodyText("EBP / RBP — Base Pointer")
                    BodyText(
                        "Anchors the current stack frame. Local variables are at negative offsets from RBP " +
                        "(e.g. [RBP-4]); function arguments passed on the stack are at positive offsets (e.g. [RBP+16])."
                    )
                }
            }

            // 4 — Segment Registers
            item {
                SectionCard("Segment Registers") {
                    BodyText("x86 has six segment registers: CS, DS, SS, ES, FS, GS.")
                    BodyText(
                        "In 32-bit protected mode, each holds a selector into the GDT/LDT, which describes the segment's " +
                        "base address, limit, and access rights. The CPU adds the segment base to every memory access."
                    )
                    BodyText(
                        "In 64-bit long mode, the hardware forces the bases of CS, DS, SS, and ES to zero — " +
                        "the flat memory model. These selectors are still present but their base is always 0, making " +
                        "virtual addresses equal to linear addresses."
                    )
                    BodyText(
                        "FS and GS remain fully functional in 64-bit mode. Operating systems use them for " +
                        "Thread-Local Storage (TLS). The base address is set via model-specific registers (MSRs):"
                    )
                    CodeBlock(
                        "MSR IA32_FS_BASE (0xC0000100) — sets FS base\n" +
                        "MSR IA32_GS_BASE (0xC0000101) — sets GS base\n\n" +
                        "Linux kernel: FS base → user-space TLS (glibc's TCB at fs:0)\n" +
                        "Windows:      GS base → user-space TEB (Thread Environment Block)\n" +
                        "Linux kernel: GS base → per-CPU data area (swapped on entry/exit)"
                    )
                }
            }

            // 5 — Control Registers
            item {
                SectionCard("Control Registers") {
                    BodyText("Control registers govern processor operating modes and memory management:")
                    CodeBlock(
                        "CR0 — system control flags\n" +
                        "      Bit 0  (PE): Protected Mode Enable\n" +
                        "      Bit 31 (PG): Paging Enable\n\n" +
                        "CR2 — Page Fault Linear Address\n" +
                        "      Holds the virtual address that caused the last #PF exception\n\n" +
                        "CR3 — Page Table Base Register\n" +
                        "      Physical address of the current process's PGD\n" +
                        "      The MMU starts every page table walk here\n" +
                        "      Loading a new value into CR3 switches address spaces\n\n" +
                        "CR4 — feature flags\n" +
                        "      PAE  — Physical Address Extension (36-bit phys addresses)\n" +
                        "      PSE  — Page Size Extension (4 MB pages in 32-bit mode)\n" +
                        "      SMEP — Supervisor Mode Execution Prevention\n" +
                        "      SMAP — Supervisor Mode Access Prevention"
                    )
                    BodyText(
                        "On every context switch the OS writes the new process's PGD physical address into CR3, " +
                        "which flushes the TLB and gives the new process its own virtual address space."
                    )
                }
            }

            // 6 — Stack Frame
            item {
                SectionCard("Stack Frame Layout") {
                    BodyText(
                        "When a function is called, a stack frame is set up to hold the return address, " +
                        "saved registers, and local variables. The stack grows downward."
                    )
                    CodeBlock(
                        "Higher addresses\n" +
                        "┌──────────────────┐\n" +
                        "│  (caller frame)  │\n" +
                        "│  ...             │\n" +
                        "│  return address  │  ← pushed by CALL instruction\n" +
                        "│  saved RBP       │  ← RBP points here after prologue\n" +
                        "│  local var 1     │\n" +
                        "│  local var 2     │\n" +
                        "│  ...             │  ← RSP points here after SUB RSP,N\n" +
                        "└──────────────────┘\n" +
                        "Lower addresses"
                    )
                    BodyText(
                        "Locals are accessed as [RBP - offset], and stack-passed arguments are at [RBP + 16] and above " +
                        "(RBP+8 is the return address, RBP+0 is the saved old RBP)."
                    )
                }
            }

            // 7 — Ring 0 / Ring 3
            item {
                SectionCard("Privilege Rings — Ring 0 and Ring 3") {
                    BodyText(
                        "x86 defines four privilege rings (0–3). Only rings 0 and 3 are used by modern OSes."
                    )
                    BodyText(
                        "Ring 0 (Kernel Mode): full hardware access. Can execute privileged instructions: " +
                        "IN/OUT (port I/O), HLT, LGDT, LIDT, MOV CRn, WRMSR/RDMSR, etc. " +
                        "Can access all physical memory. The OS kernel runs here."
                    )
                    BodyText(
                        "Ring 3 (User Mode): restricted. Privileged instructions cause a #GP (General Protection) fault. " +
                        "Memory accesses are bounded by the page table permissions. User applications run here."
                    )
                    BodyText("Transitioning from Ring 3 to Ring 0:")
                    CodeBlock(
                        "syscall   — 64-bit fast system call entry\n" +
                        "           saves RIP/RFLAGS to RCX/R11 via MSRs,\n" +
                        "           jumps to kernel entry point in LSTAR MSR\n\n" +
                        "sysenter  — 32-bit fast entry (Intel, deprecated in 64-bit)\n" +
                        "int 0x80  — legacy 32-bit software interrupt"
                    )
                    BodyText("Returning from Ring 0 to Ring 3:")
                    CodeBlock(
                        "sysret    — fast return from syscall (restores RIP/RFLAGS from RCX/R11)\n" +
                        "iret      — interrupt return; restores CS, RIP, RFLAGS, SS, RSP from stack"
                    )
                    BodyText(
                        "The Current Privilege Level (CPL) is stored in bits 1–0 of the CS register. " +
                        "0 = kernel, 3 = user."
                    )
                }
            }

            // 8 — EFLAGS / RFLAGS
            item {
                SectionCard("EFLAGS / RFLAGS") {
                    BodyText(
                        "The flags register is updated by most arithmetic and logical instructions. " +
                        "Conditional jump instructions test one or more flags to decide whether to branch."
                    )
                    CodeBlock(
                        "CF — Carry Flag\n" +
                        "     Set when an unsigned operation produces a carry/borrow\n\n" +
                        "ZF — Zero Flag\n" +
                        "     Set when the result is zero\n" +
                        "     Checked by: JE/JZ (ZF=1), JNE/JNZ (ZF=0)\n\n" +
                        "SF — Sign Flag\n" +
                        "     Set when the result's most significant bit is 1 (negative)\n\n" +
                        "OF — Overflow Flag\n" +
                        "     Set when a signed operation overflows\n\n" +
                        "PF — Parity Flag\n" +
                        "     Set when the low byte of the result has an even number of 1-bits"
                    )
                    BodyText("Selected conditional jump flag requirements:")
                    CodeBlock(
                        "JE  / JZ    → ZF = 1\n" +
                        "JNE / JNZ   → ZF = 0\n" +
                        "JG  / JNLE  → ZF = 0 AND SF = OF   (signed greater)\n" +
                        "JGE / JNL   → SF = OF               (signed ≥)\n" +
                        "JL  / JNGE  → SF ≠ OF               (signed less)\n" +
                        "JLE / JNG   → ZF = 1 OR SF ≠ OF     (signed ≤)\n" +
                        "JA  / JNBE  → CF = 0 AND ZF = 0     (unsigned above)\n" +
                        "JB  / JNAE  → CF = 1                 (unsigned below)"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
