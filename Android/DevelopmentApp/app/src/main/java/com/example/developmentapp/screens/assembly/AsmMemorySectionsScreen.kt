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
fun AsmMemorySectionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Assembly — Memory Sections",
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
                SectionCard("Overview — ELF Sections") {
                    BodyText("An ELF binary is divided into sections. The linker merges sections into loadable segments. The most important sections:")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        ".text   — machine code (read-only, executable)\n" +
                        ".data   — initialized global/static variables (read-write)\n" +
                        ".bss    — zero-initialized globals (no bytes in file; OS zeroes on load)\n" +
                        ".rodata — read-only constants and string literals"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("In NASM you declare them with 'section' directives:")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "section .data\n" +
                        "    msg db \"Hello, world!\", 0\n\n" +
                        "section .bss\n" +
                        "    buf resb 64        ; reserve 64 bytes, zero-filled\n\n" +
                        "section .text\n" +
                        "    global _start\n" +
                        "_start:\n" +
                        "    mov rax, 1         ; syscall: write\n" +
                        "    mov rdi, 1         ; fd: stdout\n" +
                        "    mov rsi, msg       ; pointer to string\n" +
                        "    mov rdx, 13        ; length\n" +
                        "    syscall"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Data Definition Directives") {
                    BodyText("NASM provides directives to define data of various sizes in .data (or .rodata):")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "db  — Define Byte        (1 byte)\n" +
                        "dw  — Define Word        (2 bytes)\n" +
                        "dd  — Define Doubleword  (4 bytes)\n" +
                        "dq  — Define Quadword    (8 bytes)\n" +
                        "dt  — Define Ten Bytes   (10 bytes, for x87 extended float)\n" +
                        "do  — Define Octoword    (16 bytes, for SSE/AVX)"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("Examples:")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "section .data\n" +
                        "    msg      db \"Hello\", 0          ; null-terminated string\n" +
                        "    newline  db 10                   ; ASCII newline (0x0A)\n" +
                        "    flag     db 1                    ; single byte = 1\n" +
                        "    count    dw 500                  ; 16-bit integer\n" +
                        "    value    dd 42                   ; 32-bit integer\n" +
                        "    bignum   dq 0x1234567890ABCDEF   ; 64-bit integer\n" +
                        "    pi       dd 3.14159              ; 32-bit float"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("For .bss (uninitialized), use 'res' variants: resb, resw, resd, resq. These only reserve space — no data is placed in the file.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "section .bss\n" +
                        "    input_buf  resb 256    ; reserve 256 bytes\n" +
                        "    counters   resq 8      ; reserve 8 quadwords = 64 bytes"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Labels Are User-Defined Names") {
                    BodyText("'msg' is NOT a keyword or a fixed name. It is a label — a symbolic name you choose to mark an address. You could call it 'greeting', 'str_hello', 'buf', or anything else. The assembler replaces every occurrence of the label with its numeric address during assembly.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Valid label names: letters, digits, underscores, dots (for local labels). Must start with a letter or underscore.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "section .data\n" +
                        "    greeting   db \"Hello\", 0     ; label is 'greeting'\n" +
                        "    error_msg  db \"Error!\", 10, 0 ; label is 'error_msg'\n\n" +
                        "section .text\n" +
                        "    global _start\n" +
                        "_start:\n" +
                        "    mov rsi, greeting   ; works exactly the same as 'mov rsi, msg'"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Referencing Data — Address vs Value") {
                    BodyText("There is an important distinction between loading an address and loading a value:")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "mov rsi, msg       ; rsi = ADDRESS of msg  (pointer)\n" +
                        "mov al,  [msg]     ; al  = BYTE VALUE at address msg\n" +
                        "lea rsi, [msg]     ; rsi = ADDRESS of msg  (same as first line)"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("'mov rsi, msg' and 'lea rsi, [msg]' both load the address. In NASM, 'msg' without brackets is already an immediate address value. The 'lea' form is sometimes preferred for clarity or for address arithmetic:")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "lea rsi, [msg + 5]   ; address of the 6th byte in msg"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("Square brackets [ ] mean 'dereference' — go to that address and read/write the memory there.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("String Termination & Multiple Values") {
                    BodyText("'db' can take a comma-separated list of bytes. A string followed by ', 0' appends a null terminator (C-style):")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "; These three are equivalent:\n" +
                        "msg db \"Hello\", 0\n" +
                        "msg db 'H','e','l','l','o', 0\n" +
                        "msg db 72, 101, 108, 108, 111, 0   ; ASCII codes"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("You can also mix strings and control codes:")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "msg db \"Hello, world!\", 10, 0   ; newline + null terminator"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("To get the length of a string at assembly time, use the '$' trick:")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "section .data\n" +
                        "    msg    db \"Hello, world!\", 10\n" +
                        "    msglen equ $ - msg    ; $ is current address; msglen = 14"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
