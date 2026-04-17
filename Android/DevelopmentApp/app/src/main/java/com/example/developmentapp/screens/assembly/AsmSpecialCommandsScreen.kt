package com.example.developmentapp.screens.assembly

import androidx.compose.foundation.layout.Spacer
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
fun AsmSpecialCommandsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Assembly — Special Commands",
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
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("NOP — No Operation") {
                    BodyText("NOP does absolutely nothing. The CPU fetches it, decodes it, and advances the instruction pointer. It takes one clock cycle but has no side effects.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "opcode : 0x90  (exactly 1 byte)\n\n" +
                        "nop            ; does nothing"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("Common uses:")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "1. Alignment padding — compilers insert NOPs to align\n" +
                        "   the next instruction to a cache-line boundary.\n\n" +
                        "2. Code patching — overwrite a jump or call with NOPs\n" +
                        "   to disable it without changing surrounding byte offsets.\n\n" +
                        "3. Timing loops — busy-wait with a known cycle count.\n\n" +
                        "4. NOP Sled (exploit technique) — a sequence of NOPs\n" +
                        "   placed before shellcode. An attacker who cannot predict\n" +
                        "   the exact shellcode address jumps somewhere into the sled\n" +
                        "   and 'slides' forward until the shellcode is reached.\n\n" +
                        "   [NOP][NOP][NOP]...[NOP][shellcode]\n" +
                        "                          ^--- attacker's imprecise jump\n" +
                        "                               lands anywhere in the sled"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("There are also multi-byte NOP encodings (e.g. 0x66 0x90 = 2-byte NOP) that processors decode as NOPs but skip faster due to fewer decode steps.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("INT 3 — Software Breakpoint") {
                    BodyText("INT 3 is the dedicated breakpoint instruction. It is exactly 1 byte (opcode 0xCC), which is what makes it so powerful for debuggers.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "opcode : 0xCC  (exactly 1 byte)\n\n" +
                        "int 3          ; trigger breakpoint exception"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("How debugger breakpoints work:")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "1. Debugger wants a breakpoint at address X.\n" +
                        "2. It reads and saves the original byte at X.\n" +
                        "3. It writes 0xCC at X, replacing the original byte.\n" +
                        "4. When execution reaches X:\n" +
                        "   a. CPU executes 0xCC → raises CPU Exception #3\n" +
                        "      (Breakpoint Exception).\n" +
                        "   b. OS catches the exception, sends SIGTRAP to the\n" +
                        "      process (on Linux).\n" +
                        "   c. The debugger (ptrace-attached) intercepts SIGTRAP\n" +
                        "      and pauses the process.\n" +
                        "   d. Debugger restores the original byte at X.\n" +
                        "5. Developer inspects registers, memory, stack.\n" +
                        "6. Single-step or continue — debugger re-inserts 0xCC\n" +
                        "   if the breakpoint should remain."
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("Because INT 3 is only 1 byte, it can replace ANY single byte in any instruction without overflowing into neighboring instructions. A general INT n is 2 bytes (opcode 0xCD + n), but INT 3 has a special 1-byte encoding precisely for debugger efficiency.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Hardware breakpoints (DR0-DR3 debug registers) are an alternative — they trigger on address access without modifying code, but there are only 4 of them.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "; You can embed INT 3 directly in assembly for ad-hoc debugging:\n" +
                        "my_func:\n" +
                        "    push rbp\n" +
                        "    int 3          ; <-- GDB/LLDB will stop here\n" +
                        "    mov rbp, rsp\n" +
                        "    ; ..."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("INT n — General Software Interrupt") {
                    BodyText("The general form 'INT n' triggers software interrupt number n. The CPU looks up the Interrupt Descriptor Table (IDT) for handler n and calls it (with a privilege transition if needed).")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "int 0x80   ; Linux x86-32 syscall gate (historical)\n" +
                        "           ; EAX = syscall number, result in EAX\n\n" +
                        "int 0x21   ; MS-DOS API (AH = function number)\n\n" +
                        "int 0x10   ; BIOS video services (real mode)"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("On modern x86-64 Linux, INT 0x80 still works (32-bit syscall convention) but SYSCALL is preferred — it is faster because it avoids the full IDT lookup and ring-transition overhead of a hardware interrupt.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "; x86-64 preferred syscall:\n" +
                        "    mov rax, 1       ; write\n" +
                        "    mov rdi, 1       ; stdout\n" +
                        "    mov rsi, msg\n" +
                        "    mov rdx, 13\n" +
                        "    syscall          ; fast syscall — no IDT lookup"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Other Notable Special Instructions") {
                    BodyText("HLT — Halt the processor:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "hlt    ; stops the CPU until the next interrupt\n" +
                        "       ; requires Ring 0 (kernel mode)\n" +
                        "       ; OS idle loops use HLT to save power"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("CPUID — Query processor information:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "; EAX = 'leaf' (info type to request)\n" +
                        "    mov eax, 0\n" +
                        "    cpuid\n" +
                        "    ; EBX:EDX:ECX = vendor string (\"GenuineIntel\" / \"AuthenticAMD\")\n\n" +
                        "    mov eax, 1\n" +
                        "    cpuid\n" +
                        "    ; EDX/ECX bits = feature flags (SSE2, AVX, AES-NI, ...)"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("RDTSC — Read Time-Stamp Counter:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "    rdtsc\n" +
                        "    ; EDX:EAX = 64-bit cycle count since CPU reset\n" +
                        "    ; useful for micro-benchmarks (be aware of CPU frequency scaling)\n" +
                        "    shl rdx, 32\n" +
                        "    or  rax, rdx    ; rax = full 64-bit counter"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("PAUSE — Spin-wait hint:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        ".spin:\n" +
                        "    pause           ; tells CPU this is a spin loop\n" +
                        "    cmp [lock], 0\n" +
                        "    jnz .spin\n" +
                        "    ; PAUSE reduces pipeline mis-speculation and power\n" +
                        "    ; on hyperthreaded cores lets the other thread run"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
