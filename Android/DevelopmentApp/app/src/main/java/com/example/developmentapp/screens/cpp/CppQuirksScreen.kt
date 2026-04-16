package com.example.developmentapp.screens.cpp

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
fun CppQuirksScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "C/C++ — Quirks",
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

            // ── setjmp and longjmp ────────────────────────────────────────────
            item {
                SectionCard(title = "setjmp and longjmp") {
                    BodyText(
                        "setjmp and longjmp provide a form of non-local goto — the ability to jump from " +
                        "deep inside a call stack back to a previously marked point, without returning " +
                        "through each intermediate function. They are declared in <setjmp.h>."
                    )
                    BodyText(
                        "setjmp(env) saves the current execution context (stack pointer, instruction " +
                        "pointer, callee-saved registers) into a jmp_buf variable. On the first call it " +
                        "returns 0. Later, longjmp(env, value) restores that saved context, making the " +
                        "CPU appear to return from setjmp again — but this time returning value (which " +
                        "must be non-zero; if you pass 0 it is treated as 1)."
                    )
                    CodeBlock(
                        "#include <setjmp.h>\n" +
                        "#include <stdio.h>\n\n" +
                        "static jmp_buf error_exit;\n\n" +
                        "void deep_function(int x) {\n" +
                        "    if (x < 0) {\n" +
                        "        longjmp(error_exit, 1);  // jump back to setjmp call site\n" +
                        "    }\n" +
                        "    printf(\"processing %d\\n\", x);\n" +
                        "}\n\n" +
                        "int main(void) {\n" +
                        "    if (setjmp(error_exit) != 0) {\n" +
                        "        // We get here when longjmp fires\n" +
                        "        printf(\"error: negative input\\n\");\n" +
                        "        return 1;\n" +
                        "    }\n" +
                        "    deep_function(5);   // prints: processing 5\n" +
                        "    deep_function(-1);  // triggers longjmp, skips the line below\n" +
                        "    deep_function(3);   // never reached\n" +
                        "    return 0;\n" +
                        "}"
                    )
                    BodyText(
                        "What longjmp does NOT do: it unwinds the stack pointer but does not call any " +
                        "destructors or cleanup functions for frames it skips. In C this matters if you " +
                        "have open file handles or allocated memory — those will leak unless cleaned up " +
                        "manually before longjmp or via the setjmp landing site."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── setjmp / longjmp and C++ ──────────────────────────────────────
            item {
                SectionCard(title = "setjmp / longjmp in C++") {
                    BodyText(
                        "In C++, using longjmp to unwind through stack frames that contain objects with " +
                        "destructors is undefined behaviour. When a C++ function exits normally, the " +
                        "compiler inserts destructor calls for all local objects. longjmp bypasses those " +
                        "calls entirely — objects are abandoned without being destroyed."
                    )
                    CodeBlock(
                        "// DANGEROUS in C++\n" +
                        "void bad_cpp() {\n" +
                        "    std::vector<int> v = {1, 2, 3};  // has a destructor\n" +
                        "    longjmp(env, 1);   // destructor of v NEVER runs — memory leak\n" +
                        "}\n\n" +
                        "// Use C++ exceptions instead:\n" +
                        "void good_cpp() {\n" +
                        "    std::vector<int> v = {1, 2, 3};\n" +
                        "    throw std::runtime_error(\"error\");  // v is destroyed properly\n" +
                        "}"
                    )
                    BodyText(
                        "Practical use today: setjmp/longjmp appears in C codebases for error propagation " +
                        "without the overhead of errno-checking every return value (e.g. libjpeg's error " +
                        "handler, some scripting language interpreters). In C++ code, prefer exceptions. " +
                        "In C code with no objects to destroy, setjmp/longjmp is safe and still used."
                    )
                    BodyText(
                        "One more subtlety: local variables in the function that called setjmp may have " +
                        "indeterminate values after longjmp if those variables were modified between the " +
                        "setjmp call and the longjmp, unless they are declared volatile."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
