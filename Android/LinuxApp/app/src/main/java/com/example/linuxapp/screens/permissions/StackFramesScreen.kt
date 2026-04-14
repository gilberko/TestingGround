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
fun StackFramesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Stacks & Stack Frames",
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
                SectionCard(title = "What Is a Stack?") {
                    BodyText(
                        "The stack is a LIFO (Last-In, First-Out) region of memory used for function calls. " +
                        "Every thread has its own stack. It stores:"
                    )
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "• Local variables\n" +
                        "• Return addresses (where to resume after a call)\n" +
                        "• Saved register values\n" +
                        "• Function arguments (on some architectures/calling conventions)"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText(
                        "The stack grows downward — toward lower addresses — as more data is pushed. " +
                        "The stack pointer always points to the top (lowest address) of the currently used stack."
                    )
                }
            }

            item {
                SectionCard(title = "x86 Stack Frame Layout (32-bit)") {
                    BodyText(
                        "Key registers on x86 (32-bit):"
                    )
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "ESP  — Stack Pointer: points to the top of the stack\n" +
                        "EBP  — Base Pointer / Frame Pointer: anchors the current frame\n" +
                        "EIP  — Instruction Pointer: address of the next instruction to execute"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Memory layout of a stack frame (high to low address):")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "  High address\n" +
                        "  ┌──────────────────────────┐\n" +
                        "  │  function arguments       │  ← pushed by caller before CALL\n" +
                        "  ├──────────────────────────┤\n" +
                        "  │  return address (EIP)     │  ← pushed by CALL instruction\n" +
                        "  ├──────────────────────────┤\n" +
                        "  │  saved EBP (prev frame)   │  ← pushed by callee prologue\n" +
                        "  ├──────────────────────────┤  ← EBP points here\n" +
                        "  │  local variables          │\n" +
                        "  │  ...                      │\n" +
                        "  └──────────────────────────┘  ← ESP points here\n" +
                        "  Low address (top of stack)"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Function prologue (sets up a new frame):")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "push  ebp          ; save caller's frame pointer\n" +
                        "mov   ebp, esp     ; set our frame pointer\n" +
                        "sub   esp, N       ; allocate N bytes for locals"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Function epilogue (tears down the frame):")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "mov   esp, ebp     ; restore stack pointer\n" +
                        "pop   ebp          ; restore caller's frame pointer\n" +
                        "ret                ; pop return address into EIP"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText(
                        "Because EBP stays fixed for the duration of the function, the debugger can " +
                        "walk the chain of saved EBP values to reconstruct a call backtrace."
                    )
                }
            }

            item {
                SectionCard(title = "x86_64 Stack Frame Layout (64-bit)") {
                    BodyText(
                        "The 64-bit counterparts of the key registers:"
                    )
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "RSP  — 64-bit Stack Pointer (extends ESP)\n" +
                        "RBP  — 64-bit Base/Frame Pointer (extends EBP)\n" +
                        "RIP  — 64-bit Instruction Pointer (extends EIP)"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText(
                        "The System V AMD64 ABI (used on Linux) passes the first six integer/pointer " +
                        "arguments in registers, avoiding stack pushes for most calls:"
                    )
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "Arg 1 → RDI\n" +
                        "Arg 2 → RSI\n" +
                        "Arg 3 → RDX\n" +
                        "Arg 4 → RCX\n" +
                        "Arg 5 → R8\n" +
                        "Arg 6 → R9\n" +
                        "Arg 7+ → pushed on stack"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText(
                        "Red Zone: in user mode, leaf functions (those that make no further calls) may use " +
                        "the 128 bytes below RSP as scratch space without adjusting RSP. Signal handlers " +
                        "must not clobber it. The kernel does not observe the red zone — it is a user-mode-only concept."
                    )
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "  ┌──────────────────────────┐\n" +
                        "  │  (return addr, saved RBP, │\n" +
                        "  │   locals, etc.)           │\n" +
                        "  └──────────────────────────┘  ← RSP\n" +
                        "  │  red zone (128 bytes)     │  ← below RSP, usable in leaf fns\n" +
                        "  └──────────────────────────┘"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText(
                        "Some compilers omit the frame pointer (RBP) as an optimization to free up an extra " +
                        "register. This makes debugging harder; use -fno-omit-frame-pointer to keep it."
                    )
                }
            }

            item {
                SectionCard(title = "ARM64 (AArch64) — Brief Overview") {
                    BodyText(
                        "ARM64 uses a different register set and ABI (AAPCS64). There is no hardware CALL/RET " +
                        "that auto-pushes to a stack; everything is convention-based."
                    )
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "x29 (FP) — Frame Pointer: convention-based, analogous to RBP\n" +
                        "x30 (LR) — Link Register: holds the return address after a BL/BLR\n" +
                        "SP       — Stack Pointer: must stay 16-byte aligned"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText(
                        "Arguments are passed in registers x0–x7 (first 8 integer args). " +
                        "The callee saves x29 and x30 to the stack to allow nested calls. " +
                        "Because the return address lives in a register rather than being auto-pushed, " +
                        "ARM64 frames look slightly different but serve the same purpose."
                    )
                }
            }

            item {
                SectionCard(title = "Where Does the Stack Live?") {
                    BodyText("User mode:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "• Lives in the process's virtual address space, near the top (high addresses)\n" +
                        "• Each thread gets its own stack\n" +
                        "• Default size: ~8 MB (controlled by ulimit -s or RLIMIT_STACK)\n" +
                        "• A guard page below the stack causes a segfault on overflow"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Kernel mode (per-thread kernel stack):")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "• Every kernel thread / user thread has a dedicated kernel stack\n" +
                        "• Size defined by THREAD_SIZE: typically 8 KB (32-bit) or 16 KB (x86_64)\n" +
                        "• Allocated in kernel memory (not in the process's user space)\n" +
                        "• Switching to kernel mode (syscall/interrupt) switches to this stack"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Interrupt handling (x86_64):")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "• x86_64 has separate per-CPU interrupt stacks (irq_stack_union)\n" +
                        "• Prevents deep interrupt nesting from overflowing the kernel thread stack\n" +
                        "• IST (Interrupt Stack Table) entries in the TSS point to special stacks\n" +
                        "  for NMI, double fault, machine check — used even if RSP is invalid"
                    )
                }
            }

            item {
                SectionCard(title = "Stack in Kernel Mode") {
                    BodyText(
                        "The kernel stack is deliberately small. This has important consequences:"
                    )
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "• Avoid large local arrays or deeply nested calls in kernel code\n" +
                        "• Recursion in the kernel is dangerous — it can silently overflow\n" +
                        "• Large data belongs in heap memory (kmalloc / vmalloc), not on the stack\n" +
                        "• Stack overflow in the kernel corrupts adjacent memory or causes an oops/panic\n" +
                        "• CONFIG_VMAP_STACK (since Linux 4.9) maps the kernel stack with a virtual\n" +
                        "  guard page, turning overflow into a deterministic fault rather than corruption"
                    )
                }
            }

            item {
                SectionCard(title = "Interrupts & Context Saving: pt_regs") {
                    BodyText(
                        "When a hardware interrupt or exception fires, the CPU must save enough state to " +
                        "resume the interrupted code later. Here is what happens on x86_64:"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Step 1 — CPU hardware pushes the interrupt frame automatically:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "  If coming from user mode (ring 3 → ring 0):\n" +
                        "    CPU loads kernel RSP from TSS.RSP0\n" +
                        "    Pushes (onto kernel stack, in this order):\n" +
                        "      SS      — user stack segment\n" +
                        "      RSP     — user stack pointer\n" +
                        "      RFLAGS  — flags register\n" +
                        "      CS      — user code segment\n" +
                        "      RIP     — user instruction pointer (where to resume)\n" +
                        "\n" +
                        "  If already in kernel mode (ring 0):\n" +
                        "    No stack switch; RSP/SS are NOT pushed\n" +
                        "    CPU pushes: RFLAGS, CS, RIP (and error code for some exceptions)"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText(
                        "Step 2 — The kernel's entry assembly saves the remaining general-purpose " +
                        "registers to complete struct pt_regs on the kernel stack:"
                    )
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "struct pt_regs (x86_64, simplified, high to low on stack):\n" +
                        "  r15, r14, r13, r12\n" +
                        "  rbp, rbx\n" +
                        "  r11, r10, r9, r8\n" +
                        "  rax, rcx, rdx, rsi, rdi\n" +
                        "  orig_rax    ← syscall number (reuses rax slot)\n" +
                        "  --- CPU-pushed interrupt frame below ---\n" +
                        "  rip, cs, eflags, rsp, ss"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText(
                        "pt_regs sits at the base of the kernel stack for that thread. Kernel code " +
                        "(interrupt handlers, syscall handlers) receives a pointer to it. The struct is " +
                        "also used by ptrace to inspect/modify a traced process's registers."
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Returning from an interrupt:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "IRET instruction:\n" +
                        "  Pops RIP, CS, RFLAGS from the stack\n" +
                        "  If returning to user mode: also pops RSP and SS\n" +
                        "  → execution resumes at the interrupted instruction"
                    )
                }
            }

            item {
                SectionCard(title = "TSS — Task State Segment") {
                    BodyText(
                        "The Task State Segment (TSS) is an x86/x86_64 hardware structure. Despite " +
                        "its name, Linux does not use it for task switching — instead, Linux keeps " +
                        "one TSS per CPU (not one per task)."
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Why the TSS is needed — the privilege-level stack switch:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "When the CPU transitions from ring 3 (user) → ring 0 (kernel):\n" +
                        "  1. CPU reads TSS.RSP0 — the kernel stack pointer for ring 0\n" +
                        "  2. CPU switches RSP to TSS.RSP0\n" +
                        "  3. CPU pushes the interrupt frame (SS, RSP, RFLAGS, CS, RIP)\n" +
                        "\n" +
                        "Without the TSS, the CPU would not know which stack to use for\n" +
                        "the kernel — and using the user stack would be a security disaster."
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Linux updates the TSS on every context switch:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "• On each context switch, Linux writes the new thread's\n" +
                        "  kernel stack top into TSS.RSP0\n" +
                        "• This ensures that on the next user→kernel transition the\n" +
                        "  CPU switches to the correct thread's kernel stack"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Other fields in the TSS:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "IST1–IST7  — alternate stack pointers for special exceptions:\n" +
                        "             NMI, double fault, machine check use dedicated\n" +
                        "             per-CPU stacks via these IST entries\n" +
                        "IOPB       — I/O Permission Bitmap: controls which I/O ports\n" +
                        "             user-mode code can access directly"
                    )
                }
            }

            item {
                SectionCard(title = "Stack Size Limits & Overflow") {
                    BodyText("User-mode stack:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "Default size: 8192 KB = 8 MB  (on most Linux distributions)\n" +
                        "Check:   ulimit -s\n" +
                        "Change:  ulimit -s unlimited  (or set RLIMIT_STACK via setrlimit)\n" +
                        "Guard page below the stack → SIGSEGV on overflow"
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Kernel-mode stack:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock(
                        "Size: THREAD_SIZE\n" +
                        "  → 8 KB on 32-bit x86\n" +
                        "  → 16 KB on x86_64 (2 pages)\n" +
                        "\n" +
                        "CONFIG_VMAP_STACK=y  — virtual guard page (Linux 4.9+)\n" +
                        "  Overflow → immediate stack fault, not silent corruption\n" +
                        "\n" +
                        "Without CONFIG_VMAP_STACK, overflow silently corrupts\n" +
                        "adjacent kernel memory — very hard to diagnose."
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText(
                        "Rule of thumb for kernel code: keep stack frames small. " +
                        "Allocate large buffers with kmalloc() or on the heap, not as local arrays."
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
