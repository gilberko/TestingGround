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
fun AsmLabelsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Assembly — Labels",
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
                SectionCard("What Is a Label") {
                    BodyText("A label is a symbolic name that refers to an address — either a location in code (before an instruction) or a location in data. The assembler resolves every label to its numeric address at assembly time. Labels have no runtime cost; they disappear after assembly.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Syntax: write the name followed by a colon. It can be on its own line or immediately before an instruction:")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "my_func:                ; label on its own line\n" +
                        "    push rbp\n" +
                        "    mov rbp, rsp\n\n" +
                        "loop_top: mov ecx, 0   ; label on same line as instruction"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Naming Rules") {
                    BodyText("In NASM (and most x86 assemblers):")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "Allowed characters : letters (A-Z, a-z), digits (0-9), underscore (_)\n" +
                        "First character    : must be a letter or underscore (not a digit)\n" +
                        "Case              : NASM is case-sensitive (myFunc != myfunc)\n" +
                        "No spaces allowed  : use_underscores instead"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("Examples of valid labels:")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "_start\n" +
                        "my_function\n" +
                        "loop_top\n" +
                        "skip_neg\n" +
                        "DONE\n" +
                        "errorHandler"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Does a Label Have to Start with '_'?") {
                    BodyText("No — the underscore prefix is convention, not a rule. You can name a label anything valid.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("'_start' is special for a different reason: the ELF linker looks for '_start' as the default program entry point (analogous to 'main()' in C). This is a linker convention, not an assembler rule. You could override it with '-e myfunc' when linking.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Common conventions:")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "_start       — program entry point (linker default)\n" +
                        "my_func      — function names, lowercase_with_underscores\n" +
                        "loop_top     — loop targets, descriptive\n" +
                        ".done        — local labels (see below)\n" +
                        "EXIT_CODE    — constants defined with equ"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("There is no enforcement of any naming style — pick whatever is clear to you.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Local Labels (NASM)") {
                    BodyText("In NASM, a label starting with a dot (.) is local to the nearest preceding non-local label. This lets you reuse common names like '.loop' or '.done' in multiple functions without collision.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "print_msg:\n" +
                        "    mov rcx, 5\n" +
                        ".loop:                 ; full name: print_msg.loop\n" +
                        "    ; ... do something ...\n" +
                        "    dec rcx\n" +
                        "    jnz .loop          ; jumps to print_msg.loop\n" +
                        "    ret\n\n" +
                        "clear_buf:\n" +
                        "    mov rcx, 64\n" +
                        ".loop:                 ; full name: clear_buf.loop\n" +
                        "    mov byte [rdi + rcx - 1], 0\n" +
                        "    dec rcx\n" +
                        "    jnz .loop          ; jumps to clear_buf.loop\n" +
                        "    ret"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("The two '.loop' labels do not conflict because they belong to different parent labels.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("Using Labels — Jumps, Calls, and Data") {
                    BodyText("Labels are used in three main ways:")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "; 1. Conditional / unconditional jumps\n" +
                        "    cmp rax, 0\n" +
                        "    je  .is_zero        ; jump if equal\n" +
                        "    jmp .continue\n" +
                        ".is_zero:\n" +
                        "    ; handle zero case\n" +
                        ".continue:\n\n" +
                        "; 2. Function calls\n" +
                        "    call my_function    ; push return addr, jump to my_function\n" +
                        "my_function:\n" +
                        "    push rbp\n" +
                        "    ; ...\n" +
                        "    pop rbp\n" +
                        "    ret\n\n" +
                        "; 3. Data address\n" +
                        "section .data\n" +
                        "    msg db \"Hi\", 10, 0\n" +
                        "section .text\n" +
                        "    mov rsi, msg       ; load address of 'msg'"
                    )
                    Spacer(Modifier.height(6.dp))
                    BodyText("Labels exported to the linker (for use across object files or from C) must be declared 'global':")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock(
                        "global _start       ; make _start visible to linker\n" +
                        "global my_function  ; callable from C as 'extern void my_function();'"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
