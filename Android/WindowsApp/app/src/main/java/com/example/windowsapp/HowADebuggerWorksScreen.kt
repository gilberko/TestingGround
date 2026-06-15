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
fun HowADebuggerWorksScreen(navController: NavController) {
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
            text = "HOW A DEBUGGER WORKS",
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

        SectionHeader("OVERVIEW")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "A user-mode debugger attaches to a target process (the debuggee) and receives " +
            "debug events via a polling loop. It can inspect and modify the debuggee's memory, " +
            "registers, and thread state. The OS provides a dedicated debugging API — no " +
            "kernel driver is needed."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ATTACHING TO A PROCESS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Two paths: attach to an existing process with DebugActiveProcess(pid), or launch " +
            "a new process under the debugger with CreateProcess using the DEBUG_PROCESS flag. " +
            "Once attached, the debugger calls WaitForDebugEvent in a loop and " +
            "ContinueDebugEvent to resume the debuggee after handling each event."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Option 1: attach to existing process\n" +
            "DebugActiveProcess(targetPid);\n\n" +
            "// Option 2: launch under debugger\n" +
            "STARTUPINFO si = { sizeof(si) };\n" +
            "PROCESS_INFORMATION pi = {};\n" +
            "CreateProcess(L\"target.exe\", nullptr, nullptr, nullptr,\n" +
            "              FALSE, DEBUG_PROCESS, nullptr, nullptr,\n" +
            "              &si, &pi);\n\n" +
            "// Event loop\n" +
            "DEBUG_EVENT dbgEvent = {};\n" +
            "while (WaitForDebugEvent(&dbgEvent, INFINITE)) {\n" +
            "    // handle event based on dbgEvent.dwDebugEventCode\n" +
            "    ContinueDebugEvent(dbgEvent.dwProcessId,\n" +
            "                       dbgEvent.dwThreadId,\n" +
            "                       DBG_CONTINUE);\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DEBUG EVENTS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "WaitForDebugEvent fills a DEBUG_EVENT struct with the event code and associated " +
            "data. Key event codes:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "CREATE_PROCESS_DEBUG_EVENT  // process created or attached\n" +
            "CREATE_THREAD_DEBUG_EVENT   // new thread created\n" +
            "LOAD_DLL_DEBUG_EVENT        // DLL mapped into process\n" +
            "EXCEPTION_DEBUG_EVENT       // exception in debuggee\n" +
            "OUTPUT_DEBUG_STRING_EVENT   // OutputDebugString called\n" +
            "EXIT_THREAD_DEBUG_EVENT     // thread exited\n" +
            "EXIT_PROCESS_DEBUG_EVENT    // process exited"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The EXCEPTION_DEBUG_EVENT is the heart of all breakpoint and stepping functionality.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("EXCEPTIONS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "When an exception occurs in the debuggee, the OS notifies the attached debugger " +
            "first (first-chance notification). The debugger can handle it by continuing with " +
            "DBG_CONTINUE, or pass it with DBG_EXCEPTION_NOT_HANDLED. If passed, the debuggee's " +
            "own exception handlers (SEH) run. If the exception is still unhandled, the OS " +
            "notifies the debugger again (second-chance — last chance before the process crashes)."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SOFTWARE BREAKPOINTS (INT 3)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Opcode 0xCC is the true single-byte INT 3 instruction. The two-byte form 0xCD 0x03 " +
            "is the generic software interrupt — same effect but costs an extra byte. Debuggers " +
            "always use 0xCC. The debugger patches the target byte in the debuggee's memory, " +
            "and on hit restores it and decrements RIP by 1 (since the CPU already advanced " +
            "past the 0xCC byte)."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Set breakpoint\n" +
            "BYTE saved;\n" +
            "ReadProcessMemory(hProcess, addr, &saved, 1, nullptr);\n" +
            "BYTE int3 = 0xCC;\n" +
            "WriteProcessMemory(hProcess, addr, &int3, 1, nullptr);\n" +
            "FlushInstructionCache(hProcess, addr, 1);\n\n" +
            "// On EXCEPTION_DEBUG_EVENT (STATUS_BREAKPOINT):\n" +
            "// 1. Restore original byte\n" +
            "WriteProcessMemory(hProcess, addr, &saved, 1, nullptr);\n" +
            "FlushInstructionCache(hProcess, addr, 1);\n" +
            "// 2. Decrement RIP by 1 (CPU advanced past 0xCC)\n" +
            "CONTEXT ctx = { CONTEXT_CONTROL };\n" +
            "GetThreadContext(hThread, &ctx);\n" +
            "ctx.Rip--;\n" +
            "SetThreadContext(hThread, &ctx);\n" +
            "// 3. Re-write 0xCC on resume if breakpoint still active"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SINGLE STEP")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The Trap Flag (TF) is bit 8 of EFLAGS (RFLAGS on x64). When TF is set, the CPU " +
            "raises a single-step exception (STATUS_SINGLE_STEP) after executing each instruction. " +
            "The debugger sets TF via SetThreadContext, continues the debuggee, receives the " +
            "STATUS_SINGLE_STEP exception, and clears TF. This is how \"step into\" works at " +
            "the instruction level."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "CONTEXT ctx = {};\n" +
            "ctx.ContextFlags = CONTEXT_CONTROL;\n" +
            "GetThreadContext(hThread, &ctx);\n" +
            "ctx.EFlags |= 0x100;  // set TF (bit 8)\n" +
            "SetThreadContext(hThread, &ctx);\n" +
            "// ContinueDebugEvent -> next event is STATUS_SINGLE_STEP"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("STEP OVER / STEP OUT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Step over a CALL instruction: read the instruction at current RIP, determine its " +
            "length (a near direct CALL is 5 bytes), compute the address of the next instruction " +
            "(RIP + 5), write 0xCC there as a temporary breakpoint, and continue. Remove it on " +
            "hit.\n\n" +
            "Step out: read the 8-byte return address at [RSP] using ReadProcessMemory, place " +
            "a temporary 0xCC breakpoint at that address, and continue."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DATA BREAKPOINTS (HARDWARE)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The CPU provides four debug address registers (DR0–DR3) plus DR6 (status) and " +
            "DR7 (control). DR7 specifies for each register: enable bit, condition " +
            "(00=execute, 01=write, 11=read/write), and size (00=1 byte, 01=2 bytes, " +
            "11=4 bytes, 10=8 bytes). Set via SetThreadContext on the thread's CONTEXT.Dr* " +
            "fields. Up to 4 simultaneous hardware breakpoints. No code patching required " +
            "— works on read-only memory and self-modifying code."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "CONTEXT ctx = {};\n" +
            "ctx.ContextFlags = CONTEXT_DEBUG_REGISTERS;\n" +
            "GetThreadContext(hThread, &ctx);\n\n" +
            "ctx.Dr0 = (DWORD64)watchAddress;  // address to watch\n" +
            "// DR7: enable DR0 local (bit 0), condition=write (01<<16),\n" +
            "//      size=4 bytes (11<<18)\n" +
            "ctx.Dr7 = 0x1          // L0 enable\n" +
            "        | (0x1 << 16)  // condition: write\n" +
            "        | (0x3 << 18); // size: 4 bytes\n\n" +
            "SetThreadContext(hThread, &ctx);"
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
