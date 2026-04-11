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
fun CallingConventionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Calling Conventions",
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
                SectionCard(title = "What Is a Calling Convention") {
                    BodyText("A calling convention is the contract between a function caller and a function callee. It specifies:")
                    BodyText("  \u2022 Which registers (or stack slots) hold the function arguments")
                    BodyText("  \u2022 Where the return value is placed")
                    BodyText("  \u2022 Who builds and tears down the stack frame (sets up EBP/RBP)")
                    BodyText("  \u2022 Which registers the callee must preserve (callee-saved) vs which it may freely destroy (caller-saved / scratch)")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("This contract is defined by the platform ABI (Application Binary Interface). The ABI covers the whole binary interface — data layout, alignment, name mangling, dynamic linking — but the calling convention is its most commonly encountered part.")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Mismatching conventions between caller and callee causes stack corruption, wrong argument values, or a crash on return. The compiler enforces the ABI automatically; you only need to care when writing inline assembly, writing a foreign-function interface, or mixing compilers.")
                }
            }
            item {
                SectionCard(title = "x86 (32-bit) \u2014 User Mode: cdecl") {
                    BodyText("cdecl (C declaration) is the default calling convention for C and C++ on 32-bit Linux (and most Unix-like systems).")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Argument passing — pushed on the stack right-to-left (last argument pushed first) before the call instruction:")
                    CodeBlock("// C:  int add(int a, int b, int c);\n// call: add(1, 2, 3)\npush  3          ; push c first\npush  2          ; push b\npush  1          ; push a   <- top of stack at call\ncall  add\nadd   esp, 12    ; caller cleans 3 x 4 = 12 bytes")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Return value: EAX (32-bit integer/pointer). 64-bit return value: EDX:EAX (EDX = high 32 bits).")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Stack cleanup: the CALLER adds to ESP after the call to remove the pushed arguments (\"caller cleans up\"). This allows variadic functions (printf) because the callee does not need to know argument count.")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Stack frame — standard prologue and epilogue emitted by the callee:")
                    CodeBlock("; Prologue (callee sets up frame)\npush  ebp          ; save caller's base pointer\nmov   ebp, esp     ; set our base pointer\nsub   esp, N       ; allocate N bytes for local vars\n\n; ... function body, locals at [ebp-4], [ebp-8] ...\n; ... arguments at [ebp+8], [ebp+12], [ebp+16] ...\n\n; Epilogue (callee tears down frame)\nmov   esp, ebp     ; release locals\npop   ebp          ; restore caller's base pointer\nret                ; pop return address, jump to caller")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Callee-saved registers (must be restored before ret): EBX, ESI, EDI, EBP.")
                    BodyText("Caller-saved / scratch (callee may clobber freely): EAX, ECX, EDX.")
                }
            }
            item {
                SectionCard(title = "x86 (32-bit) \u2014 Other Conventions") {
                    BodyText("stdcall — the callee cleans the stack (ret N instruction pops N argument bytes). Used by the Win32 API. Declared with __stdcall or __attribute__((stdcall)). Not used on Linux for normal code.")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("fastcall — passes the first two integer arguments in ECX and EDX (right-to-left), rest on the stack; callee cleans. Declared with __fastcall. Mainly a MSVC/Windows concept. GCC supports it but it is rarely used on Linux.")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("regparm(n) — GCC extension: pass first n integer arguments in EAX, EDX, ECX. The Linux kernel itself uses __attribute__((regparm(3))) on some internal functions for speed, but normal userspace code uses cdecl.")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Bottom line: on 32-bit Linux, cdecl is universal for public C APIs. You only encounter other conventions when interfacing with Windows libraries or reading legacy kernel code.")
                }
            }
            item {
                SectionCard(title = "x86-64 \u2014 User Mode: System V AMD64 ABI") {
                    BodyText("The System V AMD64 ABI is the standard for 64-bit Linux (and macOS). It passes most arguments in registers, making function calls significantly faster than the 32-bit stack-only approach.")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Integer and pointer arguments (in order): RDI, RSI, RDX, RCX, R8, R9. The 7th and beyond go on the stack (pushed right-to-left, caller cleans).")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Floating-point arguments: XMM0 through XMM7 (first 8 doubles/floats). Additional float args go on the stack.")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Return value: RAX for integer/pointer; XMM0 for float/double. A 128-bit return uses RDX:RAX.")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Stack alignment: RSP must be 16-byte aligned immediately before the call instruction. The call itself pushes the 8-byte return address, so RSP is 16-byte aligned at function entry (i.e., RSP % 16 == 8 just after the call, which means the callee's prologue push rbp makes it 16-byte aligned again).")
                    Spacer(modifier = Modifier.height(6.dp))
                    CodeBlock("// C:  long add3(long a, long b, long c);\n// call: add3(10, 20, 30)\nmov  rdi, 10    ; arg 1\nmov  rsi, 20    ; arg 2\nmov  rdx, 30    ; arg 3\ncall add3\n; return value in RAX\n; no stack cleanup needed (all args in registers)")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Standard prologue / epilogue:")
                    CodeBlock("; Prologue\npush  rbp\nmov   rbp, rsp\nsub   rsp, N      ; allocate locals (N multiple of 16)\n\n; Locals at [rbp-8], [rbp-16], ...\n; Args 1-6 already in RDI/RSI/RDX/RCX/R8/R9\n; Stack args (7th+) at [rbp+16], [rbp+24], ...\n\n; Epilogue\nleave             ; = mov rsp,rbp + pop rbp\nret")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Callee-saved (must restore): RBX, RBP, R12, R13, R14, R15.")
                    BodyText("Caller-saved / scratch (may clobber): RAX, RCX, RDX, RSI, RDI, R8, R9, R10, R11, XMM0-XMM15.")
                }
            }
            item {
                SectionCard(title = "x86-64 \u2014 Windows x64 ABI (contrast)") {
                    BodyText("Windows uses a different 64-bit ABI. The first four integer arguments go in RCX, RDX, R8, R9 (not RDI/RSI). Fifth and beyond go on the stack.")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Shadow space: the caller always allocates 32 bytes of \"shadow space\" (home space) on the stack for the first four register arguments, even if they are never spilled there. This is mandatory and the callee may use that space freely.")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Callee-saved: RBX, RBP, RDI, RSI, R12–R15, XMM6–XMM15 (note: on Windows, RDI and RSI are callee-saved; on Linux they are scratch).")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Implication: a Linux .so compiled for System V AMD64 is binary-incompatible with Windows at the function-call level. Wine, WSL, and cross-compilation toolchains exist precisely to bridge this gap.")
                }
            }
            item {
                SectionCard(title = "Kernel Mode \u2014 Internal Function Calls") {
                    BodyText("Linux kernel code compiled for x86-64 uses the same System V AMD64 ABI for internal function calls between kernel subsystems.")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("That means: RDI, RSI, RDX, RCX, R8, R9 for the first six arguments; RAX for return value; RBX/RBP/R12-R15 are callee-saved. There is no separate \"kernel calling convention\" for regular function calls.")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Where the kernel differs from userspace:")
                    BodyText("  \u2022 The kernel stack is only 4 KB or 8 KB — deep recursion or large stack frames cause a stack overflow (kernel oops or silent corruption). Avoid alloca and large local arrays.")
                    BodyText("  \u2022 Interrupt handlers and softirqs run on a separate per-CPU stack (irq_stack, softirq_stack). The switch happens automatically in the interrupt entry code.")
                    BodyText("  \u2022 Some older 32-bit kernel paths used regparm(3) (first 3 args in EAX/EDX/ECX) for performance. 64-bit kernels dropped this because the SysV ABI already passes 6 args in registers.")
                }
            }
            item {
                SectionCard(title = "System Calls \u2014 x86 (32-bit): int 0x80") {
                    BodyText("On 32-bit Linux, user space enters the kernel with the int 0x80 software interrupt. This is not a regular function call — a dedicated register protocol is used:")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("  EAX — syscall number (from <asm/unistd_32.h>)")
                    BodyText("  EBX — argument 1")
                    BodyText("  ECX — argument 2")
                    BodyText("  EDX — argument 3")
                    BodyText("  ESI — argument 4")
                    BodyText("  EDI — argument 5")
                    BodyText("  EBP — argument 6 (rarely used)")
                    BodyText("  EAX — return value (negative = -errno on error)")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("The kernel saves all user registers on entry (SAVE_ALL macro) and restores them on return, so the user's callee-saved registers are untouched.")
                    CodeBlock("; write(1, \"hi\\n\", 3) via int 0x80\n; __NR_write = 4\nmov  eax, 4          ; syscall: write\nmov  ebx, 1          ; fd = stdout\nmov  ecx, msg        ; buf = address of string\nmov  edx, 3          ; count = 3 bytes\nint  0x80            ; enter kernel\n; return value (bytes written or -errno) in EAX")
                }
            }
            item {
                SectionCard(title = "System Calls \u2014 x86-64: syscall instruction") {
                    BodyText("On 64-bit Linux the syscall instruction replaces int 0x80. It is faster (no IDT lookup, direct MSR-based dispatch) and uses a different register layout:")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("  RAX — syscall number (from <asm/unistd_64.h>)")
                    BodyText("  RDI — argument 1")
                    BodyText("  RSI — argument 2")
                    BodyText("  RDX — argument 3")
                    BodyText("  R10 — argument 4  \u2190 NOT RCX!")
                    BodyText("  R8  — argument 5")
                    BodyText("  R9  — argument 6")
                    BodyText("  RAX — return value (negative = -errno on error)")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Why R10 instead of RCX for arg 4? The syscall instruction itself clobbers RCX (saves the user-space return address there) and R11 (saves RFLAGS). So the kernel cannot pass arg 4 in RCX — it uses R10 instead.")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("The kernel preserves RBX, RBP, R12–R15 (callee-saved per SysV ABI). RAX, RCX, R11 are modified by the syscall mechanism itself.")
                    CodeBlock("; write(1, buf, 6) via syscall\n; __NR_write = 1  (different from 32-bit!)\nmov  rax, 1          ; syscall: write\nmov  rdi, 1          ; fd = stdout\nlea  rsi, [buf]      ; buf pointer\nmov  rdx, 6          ; count\nsyscall              ; enter kernel\n; bytes written (or -errno) in RAX")
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("VDSO: glibc's clock_gettime, gettimeofday, etc. are mapped into every process's address space by the kernel and execute without actually entering the kernel (no syscall instruction), for very low overhead time queries.")
                }
            }
            item {
                SectionCard(title = "Quick Reference Table") {
                    BodyText("Argument registers across all four conventions:")
                    Spacer(modifier = Modifier.height(4.dp))
                    CodeBlock(
                        "Arg  | x86 cdecl  | x86-64 SysV | x86 int 0x80 | x86-64 syscall\n" +
                        "-----+------------+-------------+--------------+---------------\n" +
                        " 1   | stack      | RDI         | EBX          | RDI\n" +
                        " 2   | stack      | RSI         | ECX          | RSI\n" +
                        " 3   | stack      | RDX         | EDX          | RDX\n" +
                        " 4   | stack      | RCX         | ESI          | R10  (*)\n" +
                        " 5   | stack      | R8          | EDI          | R8\n" +
                        " 6   | stack      | R9          | EBP          | R9\n" +
                        " 7+  | stack      | stack       | n/a (max 6)  | n/a (max 6)\n" +
                        "-----+------------+-------------+--------------+---------------\n" +
                        "Ret  | EAX        | RAX         | EAX          | RAX\n" +
                        "Clnp | Caller     | Caller      | kernel       | kernel\n" +
                        "\n(*) syscall clobbers RCX with return addr, so arg4 uses R10"
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    BodyText("Callee-saved registers:")
                    CodeBlock(
                        "x86 cdecl    : EBX, ESI, EDI, EBP\n" +
                        "x86-64 SysV  : RBX, RBP, R12, R13, R14, R15\n" +
                        "x86-64 Win64 : RBX, RBP, RDI, RSI, R12-R15, XMM6-XMM15"
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
