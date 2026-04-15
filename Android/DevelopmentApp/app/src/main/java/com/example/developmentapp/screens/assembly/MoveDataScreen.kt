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
fun MoveDataScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Move Data", color = Color(0xFF00FF41), fontFamily = FontFamily.Monospace, fontSize = 16.sp)
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
                SectionCard("MOV — Basic Move") {
                    BodyText("MOV copies a value from source to destination. It never reads the source through the destination — it's a direct copy. The flags register is NOT modified by MOV.")
                    CodeBlock(
                        "MOV RAX, RBX          ; reg → reg\n" +
                        "MOV RAX, 42           ; immediate → reg\n" +
                        "MOV RAX, [RBX]        ; memory → reg  (load)\n" +
                        "MOV [RBX], RAX        ; reg → memory  (store)\n" +
                        "MOV [RBX+RCX*4], RAX  ; scaled index addressing\n" +
                        "MOV QWORD PTR [RSP], 0 ; immediate → memory (explicit size)"
                    )
                    BodyText("You cannot MOV directly from memory to memory — you must go through a register. Memory-to-memory is done with MOVS (see below).")
                }
            }

            item {
                SectionCard("MOVZX and MOVSX — Zero/Sign Extension") {
                    BodyText("When loading a smaller value into a larger register, you must decide how to fill the upper bits.")
                    CodeBlock(
                        "MOVZX RAX, BYTE PTR [RBX]   ; zero-extend  8-bit → 64-bit\n" +
                        "MOVZX EAX, AX               ; zero-extend 16-bit → 32-bit\n" +
                        "MOVZX EAX, BL               ; zero-extend  8-bit → 32-bit\n\n" +
                        "MOVSX RAX, DWORD PTR [RBX]  ; sign-extend 32-bit → 64-bit  (= MOVSXD)\n" +
                        "MOVSX EAX, AL               ; sign-extend  8-bit → 32-bit\n" +
                        "MOVSX RAX, AX               ; sign-extend 16-bit → 64-bit"
                    )
                    BodyText("Rule: writing to a 32-bit register (e.g. EAX) always zero-extends into the 64-bit register (RAX). So MOVZX EAX, ... is often redundant with MOV EAX, .... MOVSX is needed when the source is signed.")
                }
            }

            item {
                SectionCard("MOVS — String/Memory Move") {
                    BodyText(
                        "MOVS moves one element from [RSI] to [RDI], then advances both pointers. " +
                        "The direction (increment or decrement) is controlled by the Direction Flag (DF). " +
                        "CLD clears DF (auto-increment); STD sets DF (auto-decrement)."
                    )
                    CodeBlock(
                        "MOVSB   ; move 1 byte:  [RDI] = [RSI], RSI±=1, RDI±=1\n" +
                        "MOVSW   ; move 2 bytes: [RDI] = [RSI], RSI±=2, RDI±=2\n" +
                        "MOVSD   ; move 4 bytes: [RDI] = [RSI], RSI±=4, RDI±=4\n" +
                        "MOVSQ   ; move 8 bytes: [RDI] = [RSI], RSI±=8, RDI±=8\n\n" +
                        "; Copy 64 bytes (8 qwords):\n" +
                        "cld                   ; DF=0 (forward)\n" +
                        "lea rsi, [src]\n" +
                        "lea rdi, [dst]\n" +
                        "mov rcx, 8\n" +
                        "rep movsq             ; repeat 8 times"
                    )
                    BodyText("REP MOVSQ is a highly optimized bulk copy on modern CPUs — the microcode can use internal wide datapaths much wider than 8 bytes per iteration.")
                }
            }

            item {
                SectionCard("LEA — Load Effective Address") {
                    BodyText(
                        "LEA computes the address expression inside [] and stores the result in the destination register — " +
                        "without actually accessing memory. It is a flexible arithmetic instruction disguised as a memory op."
                    )
                    CodeBlock(
                        "LEA RAX, [RBX + 8]         ; RAX = RBX + 8\n" +
                        "LEA RAX, [RBX + RCX*4]     ; RAX = RBX + RCX*4\n" +
                        "LEA RAX, [RBX + RCX*4 + 16]; RAX = RBX + RCX*4 + 16\n" +
                        "LEA RAX, [RIP + label]      ; RAX = address of 'label' (PIC)\n\n" +
                        "; Common uses:\n" +
                        "LEA RDX, [RAX + RAX*2]     ; RDX = RAX * 3  (no MUL needed)\n" +
                        "LEA RAX, [RAX + RAX*4]     ; RAX = RAX * 5\n" +
                        "; LEA does NOT modify flags (unlike ADD)"
                    )
                    BodyText("LEA is often preferred over ADD/IMUL for small-constant multiplication because it completes in 1 cycle on most ports and doesn't touch flags.")
                }
            }

            item {
                SectionCard("XCHG — Exchange") {
                    BodyText("XCHG swaps the values of two operands atomically. When a memory operand is used, XCHG has an implicit LOCK prefix — no explicit LOCK needed.")
                    CodeBlock(
                        "XCHG RAX, RBX         ; swap two registers\n" +
                        "XCHG [mutex], EAX     ; atomic swap with memory (implicit LOCK)\n" +
                        "                      ; classic spinlock acquire:\n" +
                        "mov  eax, 1\n" +
                        "xchg [lock_var], eax  ; atomically set lock_var=1, get old value\n" +
                        "test eax, eax\n" +
                        "jnz  spin             ; if old value was 1 → already locked"
                    )
                    BodyText("XCHG RAX, RAX is a 2-byte NOP (opcode 0x90 with REX prefix). XCHG EAX, EAX is the classic 1-byte NOP (0x90).")
                }
            }

            item {
                SectionCard("IN and OUT — Port I/O") {
                    BodyText(
                        "IN reads a byte/word/dword from a hardware I/O port into AL/AX/EAX. " +
                        "OUT writes AL/AX/EAX to a port. These instructions require ring 0 (or IOPL=3, or permission in the TSS IOPB)."
                    )
                    CodeBlock(
                        "IN  AL,  0x60      ; read keyboard data port (byte)\n" +
                        "IN  AX,  DX        ; read 16-bit port number in DX\n" +
                        "IN  EAX, DX        ; read 32-bit port\n\n" +
                        "OUT 0x20, AL       ; write to PIC1 command port\n" +
                        "OUT DX,   AL       ; write to port in DX\n\n" +
                        "; INS / OUTS — string port I/O (uses [RDI]/[RSI] + RCX):\n" +
                        "REP INSB           ; read RCX bytes from port DX → [RDI]\n" +
                        "REP OUTSB          ; write RCX bytes from [RSI] → port DX"
                    )
                    BodyText("On modern PCs most devices are memory-mapped (MMIO) rather than port-mapped, but ports are still used for legacy devices like the PIC, PIT, and keyboard controller.")
                }
            }

            item {
                SectionCard("LOCK Prefix — Atomic Memory Operations") {
                    BodyText(
                        "The LOCK prefix makes the following instruction a bus-locked read-modify-write, " +
                        "ensuring atomicity across multiple CPU cores. It can only be used with memory operands."
                    )
                    CodeBlock(
                        "LOCK ADD  [counter], 1      ; atomic increment\n" +
                        "LOCK SUB  [counter], 1      ; atomic decrement\n" +
                        "LOCK AND  [flags], 0xFE     ; atomic bit clear\n" +
                        "LOCK OR   [flags], 0x01     ; atomic bit set\n" +
                        "LOCK XCHG [var], EAX        ; (implicit — LOCK already implied)\n\n" +
                        "; Instructions that support LOCK:\n" +
                        "; ADD, ADC, AND, BTC, BTR, BTS, CMPXCHG, CMPXCHG8B/16B,\n" +
                        "; DEC, INC, NEG, NOT, OR, SBB, SUB, XOR, XADD, XCHG"
                    )
                    BodyText("LOCK issues a cache coherency protocol message (MESI) to stall other cores for the duration. Overuse causes severe contention on heavily shared data.")
                }
            }

            item {
                SectionCard("CMPXCHG — Compare and Exchange") {
                    BodyText(
                        "CMPXCHG is the foundation of lock-free programming. It compares the destination with AL/AX/EAX/RAX (the accumulator). " +
                        "If equal, it writes the source register to the destination. Otherwise, it loads the current destination into the accumulator."
                    )
                    CodeBlock(
                        "; CMPXCHG dst, src\n" +
                        ";   if (dst == AL/AX/EAX/RAX) { dst = src; ZF=1 }\n" +
                        ";   else                       { AL/.../RAX = dst; ZF=0 }\n\n" +
                        "; Atomic compare-and-swap (CAS) — the building block of\n" +
                        "; mutexes, lock-free queues, reference counting:\n" +
                        "mov  rax, [expected]         ; load expected value\n" +
                        "mov  rbx, [new_value]        ; new value to store\n" +
                        "lock cmpxchg [shared], rbx   ; if [shared]==rax → store rbx, ZF=1\n" +
                        "jne  retry                   ; ZF=0 means someone changed it\n\n" +
                        "; C equivalent:\n" +
                        "; __atomic_compare_exchange_n(&shared, &expected, new, ...);"
                    )
                    BodyText("CMPXCHG8B and CMPXCHG16B extend this to 64-bit and 128-bit comparisons, useful for updating two related pointers atomically (ABA-safe pointer updates).")
                }
            }

            item {
                SectionCard("XADD — Exchange and Add") {
                    BodyText(
                        "XADD swaps src and dst, then stores their sum in dst. Combined with LOCK, " +
                        "it provides an atomic fetch-and-add — the value before adding is left in src."
                    )
                    CodeBlock(
                        "; XADD dst, src\n" +
                        ";   temp = dst + src\n" +
                        ";   src  = dst  (old dst value)\n" +
                        ";   dst  = temp\n\n" +
                        "; Atomic increment, returns old value in ECX:\n" +
                        "mov  ecx, 1\n" +
                        "lock xadd [counter], ecx    ; ecx = old counter, counter += 1\n\n" +
                        "; Used internally by glibc's atomic increment functions and\n" +
                        "; the Linux kernel's atomic_add_return()."
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
