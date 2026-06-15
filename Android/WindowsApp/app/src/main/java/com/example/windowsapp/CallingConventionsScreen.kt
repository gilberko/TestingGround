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
fun CallingConventionsScreen(navController: NavController) {
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
            text = "CALLING CONVENTIONS",
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

        SectionHeader("WHY CALLING CONVENTIONS MATTER")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A calling convention is a contract between a function's caller and callee that specifies:\n• Where parameters are passed (registers vs. stack)\n• The order parameters are pushed\n• Who cleans up the stack after the call (caller or callee)\n• Where the return value is placed\n• Which registers each side must preserve\n\nMismatched conventions cause crashes: the wrong amount of stack is cleaned, registers are clobbered, or arguments land at the wrong offsets.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("X86 — __cdecl")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Params:   stack, right-to-left\n" +
            "Return:   EAX (int/ptr)\n" +
            "          EDX:EAX (64-bit return)\n" +
            "Cleanup:  CALLER\n" +
            "Varargs:  YES (caller knows arg count)\n" +
            "Mangling: _functionName"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The default for C programs compiled with MSVC or GCC on x86. Because the caller cleans the stack, varargs (printf-style) functions work naturally — the caller pushed the args so only the caller knows how many there are.\n\nSlight code-size cost: every call site emits a stack-cleanup instruction (add esp, N).")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("X86 — __stdcall")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Params:   stack, right-to-left\n" +
            "Return:   EAX\n" +
            "Cleanup:  CALLEE (RET N)\n" +
            "Varargs:  NO\n" +
            "Mangling: _functionName@ByteCount"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The default for Win32 API functions. WINAPI is #defined as __stdcall.\n\nBecause the callee emits a single RET N instruction, the cleanup code exists once per function rather than at every call site — smaller total code size.\n\nVariadic functions cannot use __stdcall because the callee doesn't know how many bytes the caller pushed.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("X86 — __fastcall")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Params:   ECX=arg1, EDX=arg2,\n" +
            "          rest on stack (right-to-left)\n" +
            "Return:   EAX\n" +
            "Cleanup:  CALLEE\n" +
            "Varargs:  NO\n" +
            "Mangling: @functionName@ByteCount"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Passes the first two integer/pointer arguments in ECX and EDX to avoid stack traffic for small functions.\n\nUsed internally by the Windows kernel for many routines. Not suitable for COM interfaces or APIs that cross DLL boundaries if the DLL was compiled with a different convention.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("X86 — __thiscall")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Params:   ECX=this pointer,\n" +
            "          rest on stack (right-to-left)\n" +
            "Return:   EAX\n" +
            "Cleanup:  CALLEE (unless varargs → CALLER)\n" +
            "Varargs:  special case only\n" +
            "Mangling: C++ decorated name"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The implicit convention for C++ non-static member functions in MSVC. The this pointer arrives in ECX so it doesn't consume a stack slot.\n\nFor vararg member functions (rare), the convention falls back to caller-cleanup because the callee can't know how many args were pushed.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("X86 — __vectorcall")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Params:   XMM0-XMM5 for float/SIMD,\n" +
            "          ECX/EDX for first two integers,\n" +
            "          rest on stack\n" +
            "Return:   XMM0 (float), EAX (int)\n" +
            "Cleanup:  CALLEE\n" +
            "Mangling: @@functionName@ByteCount"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("MSVC extension for SIMD-heavy code. Passes up to six floating-point or vector (SSE/AVX) arguments in XMM/YMM registers. Significant performance gain when calling math or graphics routines that take many floats.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("X86-64 — MICROSOFT x64 ABI (Windows)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("On 64-bit Windows there is only ONE calling convention. All modifiers (__cdecl, __stdcall, etc.) are silently ignored by the compiler.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Integer/pointer params:\n" +
            "  arg1=RCX  arg2=RDX\n" +
            "  arg3=R8   arg4=R9\n" +
            "  arg5+ on stack (right-to-left)\n\n" +
            "Float/double params:\n" +
            "  arg1=XMM0  arg2=XMM1\n" +
            "  arg3=XMM2  arg4=XMM3\n" +
            "  arg5+ on stack\n\n" +
            "Return: RAX (int/ptr), XMM0 (float)\n\n" +
            "Cleanup: CALLER\n\n" +
            "Shadow space: caller allocates 32 bytes\n" +
            "  (4 x 8) BEFORE the call, always,\n" +
            "  even if fewer than 4 args passed\n\n" +
            "Stack alignment: 16 bytes at CALL"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Volatile (caller-save) registers:\nRAX RCX RDX R8 R9 R10 R11 XMM0-XMM5\n\nNon-volatile (callee-save) registers:\nRBX RBP RDI RSI RSP R12 R13 R14 R15 XMM6-XMM15\n\nThe callee may use the 32-byte shadow space to spill its register parameters to the stack for debugging — it is always there, but the callee is not required to use it.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("X86-64 — SYSTEM V AMD64 ABI (Linux / macOS)")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Integer/pointer params:\n" +
            "  RDI RSI RDX RCX R8 R9\n" +
            "  (6 registers, not 4)\n" +
            "  arg7+ on stack\n\n" +
            "Float/double params:\n" +
            "  XMM0-XMM7 (8 registers)\n\n" +
            "Return: RAX (or RDX:RAX for 128-bit)\n\n" +
            "Cleanup: CALLER\n\n" +
            "Shadow space: NONE\n\n" +
            "Stack alignment: 16 bytes at CALL"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Key difference from Microsoft x64: parameters go in different registers (RDI/RSI/RDX/RCX vs RCX/RDX/R8/R9), more registers are used (6 vs 4 for integers, 8 vs 4 for floats), and there is no shadow space. Windows and Linux x64 code cannot call each other's functions directly without a thunk.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ARM32 — AAPCS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The ARM Architecture Procedure Call Standard (AAPCS) for 32-bit ARM (ARMv7 / Thumb):")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Integer params:\n" +
            "  r0 r1 r2 r3 (first 4)\n" +
            "  arg5+ on stack\n\n" +
            "Float params (VFP):\n" +
            "  s0-s15 / d0-d7\n\n" +
            "Return: r0 (or r0:r1 for 64-bit)\n\n" +
            "Cleanup: CALLER\n\n" +
            "Stack alignment: 8 bytes at CALL\n\n" +
            "Callee-save: r4-r11, r13(sp), r14(lr)\n" +
            "Caller-save: r0-r3, r12(ip)"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("r14 (lr) is the link register — holds the return address. The callee saves it to the stack on entry if it calls other functions, then restores it for the return (POP {pc} or BX LR).")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ARM64 — AAPCS64 (AArch64)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The 64-bit ARM calling convention used on ARMv8+ (including Apple Silicon, Windows on ARM, Android ARM64):")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Integer params:\n" +
            "  x0-x7 (first 8)\n" +
            "  arg9+ on stack\n\n" +
            "Float/SIMD params:\n" +
            "  v0-v7 (first 8)\n\n" +
            "Return: x0 (or x0:x1 for 128-bit)\n\n" +
            "Cleanup: CALLER\n\n" +
            "Stack alignment: 16 bytes at CALL\n\n" +
            "Callee-save: x19-x28, x29(fp), x30(lr)\n" +
            "             v8-v15 (low 64 bits only)\n" +
            "Caller-save: x0-x17, v0-v7, v16-v31"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("x29 is the frame pointer (fp) and x30 is the link register (lr). ARM64 is significantly cleaner than ARM32: 8 argument registers (vs 4), 128-bit SIMD in v registers, and a fixed 16-byte stack alignment requirement.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DEFAULT WINDOWS API CONVENTION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("32-bit (x86): __stdcall\n  WINAPI is #defined as __stdcall\n  WIN32 functions use \"_FuncName@N\" mangling\n  Callee cleans the stack\n\n64-bit (x64): Microsoft x64 ABI\n  All __cdecl/__stdcall modifiers are ignored\n  One universal convention — no annotation needed\n  RCX/RDX/R8/R9 + 32-byte shadow space\n\nWindows on ARM64: AAPCS64 with minor Microsoft extensions\n  x0-x7 for integer args (Microsoft follows the standard)\n\nImplication: a DLL compiled for Win32 (__stdcall) cannot be called directly from a 64-bit process — it must be in a 32-bit host process or accessed via COM/out-of-process activation.")

        Spacer(modifier = Modifier.height(32.dp))
        HackerButton("BACK") { navController.popBackStack() }
    }
}
