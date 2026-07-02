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
fun FunctionCallsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Function Calls",
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
            // 1 — CALL
            item {
                SectionCard("CALL Instruction") {
                    BodyText(
                        "CALL transfers control to a function. Before jumping, it pushes the return address " +
                        "(the address of the instruction immediately after CALL) onto the stack."
                    )
                    CodeBlock(
                        "CALL target\n" +
                        "; equivalent to:\n" +
                        "  PUSH  RIP_next   ; push address of next instruction\n" +
                        "  JMP   target\n\n" +
                        "; Encoding:\n" +
                        "  E8 xx xx xx xx  ; near CALL, 5 bytes, rel32 offset\n" +
                        "  FF /2           ; indirect CALL via register (2 bytes): CALL RAX"
                    )
                    BodyText(
                        "The near CALL offset is relative to the instruction after CALL (i.e., RIP + 5). " +
                        "Indirect calls use a register or memory operand as the target address — essential for " +
                        "virtual function dispatch and function pointer calls."
                    )
                }
            }

            // 2 — Prologue
            item {
                SectionCard("Function Prologue") {
                    BodyText(
                        "The standard prologue saves the caller's base pointer and establishes a new stack frame. " +
                        "The ABI requires RSP to be 16-byte aligned before a CALL, so N is always a multiple of 16."
                    )
                    CodeBlock(
                        "PUSH RBP           ; save caller's RBP on stack   (RSP -= 8)\n" +
                        "MOV  RBP, RSP      ; RBP = current stack top\n" +
                        "SUB  RSP, N        ; allocate N bytes for locals   (RSP -= N)\n\n" +
                        "; After prologue:\n" +
                        "; [RBP + 0]  = saved old RBP\n" +
                        "; [RBP + 8]  = return address\n" +
                        "; [RBP + 16] = first stack argument (if any)\n" +
                        "; [RBP - 4]  = first local variable (example, 4-byte int)"
                    )
                    BodyText(
                        "Optimized builds (e.g. -O2) often omit the frame pointer (RBP) entirely, freeing it as a " +
                        "general-purpose register. Stack unwinding then relies on DWARF unwind tables instead."
                    )
                }
            }

            // 3 — Epilogue
            item {
                SectionCard("Function Epilogue") {
                    BodyText("The epilogue tears down the stack frame and returns to the caller.")
                    CodeBlock(
                        "; Using LEAVE (one instruction shorthand):\n" +
                        "LEAVE              ; MOV RSP, RBP  then  POP RBP\n" +
                        "RET                ; pop return address into RIP\n\n" +
                        "; Explicit equivalent:\n" +
                        "MOV  RSP, RBP      ; deallocate locals\n" +
                        "POP  RBP           ; restore caller's RBP   (RSP += 8)\n" +
                        "RET                ; pop return address, jump to it  (RSP += 8)"
                    )
                }
            }

            // 4 — RET
            item {
                SectionCard("RET Instruction") {
                    BodyText(
                        "RET pops the 8-byte return address from the top of the stack into RIP and continues " +
                        "execution there. RSP is incremented by 8."
                    )
                    CodeBlock(
                        "RET       ; near return — pop 8-byte return addr, jump to it\n" +
                        "RET N     ; near return + pop N additional bytes from stack\n" +
                        "          ; used in callee-cleanup conventions (Windows __stdcall)\n" +
                        "          ; example: RET 8 pops return addr + 8 bytes of arguments"
                    )
                    BodyText(
                        "On x86-64 Linux (System V ABI) the caller always cleans the stack, so plain RET is the norm. " +
                        "On 32-bit Windows stdcall the callee uses RET N."
                    )
                }
            }

            // 5 — LEAVE
            item {
                SectionCard("LEAVE Instruction") {
                    BodyText(
                        "LEAVE is a single-byte instruction (opcode 0xC9) that collapses the current stack frame " +
                        "back to the caller's — the exact reverse of the prologue's MOV RBP, RSP / SUB RSP, N."
                    )
                    CodeBlock(
                        "LEAVE   ; equivalent to:\n" +
                        "        ;   MOV RSP, RBP    ; discard locals — RSP jumps back up to RBP\n" +
                        "        ;   POP RBP         ; restore caller's saved RBP   (RSP += 8)"
                    )
                    BodyText(
                        "LEAVE does not touch the return address and does not return — RET is still a separate, " +
                        "required instruction immediately after it."
                    )
                    BodyText(
                        "RSP vs RBP: RSP always points at the current top of stack and moves on every PUSH/POP/CALL/" +
                        "RET/SUB RSP,N — it can wobble throughout the function body. RBP is set once at entry " +
                        "(MOV RBP, RSP) and held fixed for the rest of the function, giving a stable reference point: " +
                        "locals at negative offsets ([RBP-4]), saved old RBP at [RBP+0], return address at [RBP+8], " +
                        "further arguments above that — regardless of how RSP moves later on."
                    )
                    BodyText(
                        "You don't need LEAVE — it's shorthand for MOV RSP, RBP + POP RBP, and compilers emit either " +
                        "one; the manual two-instruction form is functionally identical. LEAVE is smaller (1 byte vs. " +
                        "~3-4 bytes) and on modern CPUs is a single fast micro-op, so there's no meaningful " +
                        "performance difference. When the frame pointer is omitted entirely (-fomit-frame-pointer, " +
                        "see Function Prologue above), there's no saved RBP to restore, so the epilogue skips LEAVE " +
                        "altogether and just does ADD RSP, N before RET."
                    )
                }
            }

            // 6 — Stack Frame Diagram
            item {
                SectionCard("Stack Frame Diagram") {
                    CodeBlock(
                        "State just before CALL:     State after prologue:\n\n" +
                        "Higher addresses            Higher addresses\n" +
                        "┌────────────────┐          ┌────────────────┐\n" +
                        "│ caller frame   │          │ caller frame   │\n" +
                        "│ ...            │          │ ...            │\n" +
                        "│ arg 3 (cdecl)  │          │ arg 3 (cdecl)  │\n" +
                        "│ arg 2 (cdecl)  │          │ arg 2 (cdecl)  │\n" +
                        "│ arg 1 (cdecl)  │          │ arg 1 (cdecl)  │\n" +
                        "RSP→│ (return addr)  │          │ return addr    │\n" +
                        "└────────────────┘          │ saved old RBP  │ ←RBP\n" +
                        "                            │ local var 1    │\n" +
                        "                            │ local var 2    │\n" +
                        "                       RSP→ │ ...            │\n" +
                        "                            └────────────────┘\n" +
                        "Lower addresses             Lower addresses"
                    )
                }
            }

            // 6 — Security Note
            item {
                SectionCard("Security — Return Address Tampering") {
                    BodyText(
                        "The return address sits at a predictable offset from RBP in every stack frame. " +
                        "A classic stack buffer overflow writes past a local buffer and overwrites the return address " +
                        "with an attacker-controlled value — redirecting execution to arbitrary code."
                    )
                    CodeBlock(
                        "; Layout of a vulnerable function:\n" +
                        "; [RBP - 64] = char buf[64]     ← overflowable buffer\n" +
                        "; [RBP + 0]  = saved old RBP\n" +
                        "; [RBP + 8]  = return address   ← attacker overwrites this"
                    )
                    BodyText("This is the foundation of stack smashing, return-to-libc, and ROP (Return-Oriented Programming) attacks.")
                    BodyText("Modern mitigations:")
                    BodyText(
                        "  • Stack canary — a random value placed between locals and return address; " +
                        "checked at function exit. Linux: -fstack-protector. Windows: GS cookie."
                    )
                    BodyText("  • NX / DEP — marks the stack non-executable so shellcode injected into it cannot run.")
                    BodyText("  • ASLR — randomizes base addresses so the attacker cannot predict target addresses.")
                    BodyText("  • CFG / CET — Intel Control-Flow Enforcement Technology uses a shadow stack (in hardware) to verify that RET targets the address pushed by CALL.")
                }
            }

            // 7 — Calling Conventions / Parameters
            item {
                SectionCard("Calling Conventions — Parameters") {
                    BodyText("How arguments are passed depends on the calling convention:")
                    CodeBlock(
                        "Convention          Args (order)              Caller cleans stack?\n" +
                        "─────────────────────────────────────────────────────────────────\n" +
                        "32-bit cdecl        right-to-left on stack    Yes (caller: ADD ESP,N)\n" +
                        "32-bit stdcall      right-to-left on stack    No  (callee: RET N)\n" +
                        "64-bit System V     RDI RSI RDX RCX R8 R9,   Yes\n" +
                        " (Linux / macOS)    then stack (right-to-left)\n" +
                        "64-bit Windows x64  RCX RDX R8 R9,           Yes\n" +
                        "                    then stack (right-to-left)\n" +
                        "                    + 32-byte shadow space"
                    )
                    BodyText(
                        "Shadow space (Windows x64): the caller must reserve 32 bytes (4 × 8) on the stack above the " +
                        "return address before every CALL, even if the callee takes fewer than 4 arguments. " +
                        "This gives the callee space to spill its register arguments for debugging."
                    )
                    BodyText(
                        "Floating-point and SIMD arguments use XMM0–XMM7 (System V) or XMM0–XMM3 (Windows x64)."
                    )
                }
            }

            // 9 — Return Values
            item {
                SectionCard("Return Values") {
                    BodyText("The return value register(s) depend on the value's type and size:")
                    CodeBlock(
                        "Return type        Register(s)\n" +
                        "────────────────────────────────────────────────────────\n" +
                        "8/16-bit integer   AL / AX  (zero-extended into RAX)\n" +
                        "32-bit integer     EAX      (zero-extended into RAX)\n" +
                        "64-bit integer     RAX\n" +
                        "128-bit integer    RDX:RAX  (high 64 in RDX, low 64 in RAX)\n" +
                        "float / double     XMM0     (System V and Windows x64)\n" +
                        "Small struct       RAX:RDX  (System V — fits in 2 regs)\n" +
                        "Large struct       Caller allocates space, passes pointer\n" +
                        "                   in RDI (System V) or RCX (Windows)"
                    )
                    BodyText(
                        "On Windows x64, a struct larger than 8 bytes is always returned via a hidden pointer " +
                        "passed as the first argument in RCX, shifting all other arguments by one register."
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
