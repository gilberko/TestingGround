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
fun WritingADllScreen(navController: NavController) {
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
            text = "WRITING A DLL",
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

        // DLLMAIN
        SectionHeader("DLLMAIN")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "DllMain is the optional entry point for a DLL. When present it is called by " +
            "the Windows loader at specific moments in the DLL's lifetime. If you omit it, " +
            "the CRT provides a default one."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "BOOL WINAPI DllMain(\n" +
            "    HINSTANCE hInstDLL,   // handle to this DLL module\n" +
            "    DWORD     fdwReason,  // reason for the call\n" +
            "    LPVOID    lpvReserved // static/dynamic load indicator\n" +
            ") {\n" +
            "    switch (fdwReason) {\n" +
            "        case DLL_PROCESS_ATTACH: ...\n" +
            "        case DLL_PROCESS_DETACH: ...\n" +
            "        case DLL_THREAD_ATTACH:  ...\n" +
            "        case DLL_THREAD_DETACH:  ...\n" +
            "    }\n" +
            "    return TRUE;  // FALSE from PROCESS_ATTACH fails the load\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // WHEN DLLMAIN IS CALLED
        SectionHeader("WHEN DLLMAIN IS CALLED")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "DLL_PROCESS_ATTACH\n" +
            "  The DLL is being mapped into the process for the first time.\n" +
            "  lpvReserved != NULL → statically linked (loaded at process start)\n" +
            "  lpvReserved == NULL → dynamically loaded via LoadLibrary\n" +
            "  Return FALSE to abort; LoadLibrary returns NULL; static load aborts process.\n\n" +
            "DLL_PROCESS_DETACH\n" +
            "  The DLL is being unmapped.\n" +
            "  lpvReserved != NULL → process is terminating (do minimal cleanup;\n" +
            "                        other threads may be dead already)\n" +
            "  lpvReserved == NULL → FreeLibrary was called; safe to release resources.\n\n" +
            "DLL_THREAD_ATTACH\n" +
            "  A new thread was created in the process.\n" +
            "  NOT called for the thread that loaded the DLL.\n" +
            "  Disable with DisableThreadLibraryCalls(hInstDLL) if you have no per-thread\n" +
            "  state — avoids unnecessary overhead for thread-heavy processes.\n\n" +
            "DLL_THREAD_DETACH\n" +
            "  A thread exited cleanly via ExitThread or return from its start function.\n" +
            "  NOT called if the thread was killed via TerminateThread."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // THE LOADER LOCK
        SectionHeader("THE LOADER LOCK")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The loader lock (ntdll!LdrpLoaderLock) is a critical section in ntdll.dll " +
            "that the Windows loader holds whenever it is:\n" +
            "  • Loading or unloading a DLL (LoadLibrary / FreeLibrary)\n" +
            "  • Calling DllMain callbacks\n" +
            "  • Initializing or cleaning up TLS\n\n" +
            "DllMain is called while the loader lock is held by your thread.\n\n" +
            "The lock is technically a re-entrant critical section, so your own thread can " +
            "acquire it again — which is why simple LoadLibrary calls from PROCESS_ATTACH " +
            "sometimes appear to work. But this hides a dangerous multi-threaded deadlock:\n"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Thread A: LoadLibrary(\"mydll.dll\")\n" +
            "  → acquires loader lock\n" +
            "  → calls DllMain(DLL_PROCESS_ATTACH)\n" +
            "     → DllMain creates Thread C and waits for it\n\n" +
            "Thread B: concurrently called LoadLibrary(\"other.dll\")\n" +
            "  → blocked waiting for loader lock\n" +
            "  → Thread B holds Mutex X\n\n" +
            "Thread C starts, tries to acquire Mutex X\n" +
            "  → blocked waiting for Thread B\n\n" +
            "Deadlock: A waits for C, C waits for B (Mutex X), B waits for A (loader lock)"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "This is why Microsoft explicitly forbids calling LoadLibrary from DllMain, " +
            "even though the lock is technically re-entrant."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // WHAT NOT TO DO IN DLLMAIN
        SectionHeader("WHAT NOT TO DO IN DLLMAIN")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "FORBIDDEN — can cause deadlocks or undefined behavior:\n\n" +
            "  LoadLibrary / FreeLibrary       — loader lock + ordering issues\n" +
            "  CoInitialize / CoUninitialize   — COM initialization uses the loader\n" +
            "  CreateThread + WaitForSingleObject — see deadlock example above\n" +
            "  SendMessage / PostMessage        — USER32 can wait on message pump;\n" +
            "                                    message pump may need loader lock\n" +
            "  Functions in uninitialized DLLs — they may not have run PROCESS_ATTACH yet\n" +
            "  Heap operations in DLL_PROCESS_DETACH on process termination — heap may be\n" +
            "                                    corrupt; other threads already dead\n\n" +
            "SAFE — low-level, no loader interaction:\n\n" +
            "  HeapAlloc / HeapFree\n" +
            "  TlsAlloc / TlsSetValue / TlsFree\n" +
            "  Functions exported from ntdll.dll (RtlXxx, NtXxx)\n" +
            "  InitializeCriticalSection / DeleteCriticalSection\n" +
            "  Setting/reading global variables\n" +
            "  DisableThreadLibraryCalls"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // EXPORTING FUNCTIONS
        SectionHeader("EXPORTING FUNCTIONS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Method 1 — __declspec(dllexport) on the declaration. The linker auto-generates " +
            "the export table. Simplest approach; name decoration applies."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// mylib.h\n" +
            "__declspec(dllexport) int  Add(int a, int b);\n" +
            "__declspec(dllexport) void Reset();"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Method 2 — a module definition (.def) file. Gives full control over export " +
            "names, ordinals, and aliases. No name mangling. Preferred for stable ABIs."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "; mylib.def\n" +
            "LIBRARY mylib\n" +
            "EXPORTS\n" +
            "    Add      @1\n" +
            "    Reset    @2\n" +
            "    InternalHelper  PRIVATE  ; not exported to callers"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "To import in a calling module, use __declspec(dllimport) or link against " +
            "the .lib import library generated by the linker alongside the .dll."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // CRT MISMATCH
        SectionHeader("CRT MISMATCH AND HEAP OWNERSHIP")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The C Runtime (CRT) manages its own heap. When you call malloc/free, you are " +
            "allocating from and returning memory to the heap owned by that specific CRT " +
            "instance.\n\n" +
            "If your DLL is compiled against MSVCR140.dll (VS 2015+) and the caller is " +
            "compiled against MSVCR120.dll (VS 2013), they have separate heaps. Passing " +
            "a pointer allocated in the DLL to the caller and letting the caller call " +
            "free() on it will attempt to free memory from the wrong heap — this causes " +
            "heap corruption and typically a crash.\n\n" +
            "This is exactly the problem you described, and the description is correct."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// DLL (MSVCR140.dll heap)\n" +
            "char* GetBuffer() {\n" +
            "    return (char*)malloc(256);  // allocated from DLL's heap\n" +
            "}\n\n" +
            "// Caller EXE (MSVCR120.dll heap)\n" +
            "char* buf = GetBuffer();\n" +
            "free(buf);  // WRONG heap — corruption / crash"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // SOLUTIONS
        SectionHeader("SOLUTIONS TO CRT MISMATCH")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Option 1 — /MD (dynamic CRT, recommended):\n" +
            "Compile all modules (DLL and EXE) with /MD pointing to the SAME CRT DLL " +
            "(e.g., both use MSVCR140.dll). They share one CRT instance and one heap. " +
            "malloc in the DLL and free in the EXE are both calls into the same CRT DLL " +
            "— no mismatch. This is the standard recommendation.\n\n" +
            "Option 2 — never free across the boundary:\n" +
            "Provide a paired FreeBuffer() export in the DLL. The caller never calls " +
            "free() directly; it calls your function which frees from the correct heap."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// DLL exports both allocator and deallocator\n" +
            "__declspec(dllexport) char* GetBuffer()    { return (char*)malloc(256); }\n" +
            "__declspec(dllexport) void  FreeBuffer(char* p) { free(p); }\n\n" +
            "// Caller\n" +
            "char* buf = GetBuffer();\n" +
            "FreeBuffer(buf);  // correct — frees on DLL's heap"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Option 3 — use a CRT-independent allocator:\n" +
            "  CoTaskMemAlloc / CoTaskMemFree  — COM task allocator (single system-wide heap)\n" +
            "  HeapAlloc / HeapFree with a shared HANDLE — explicit Win32 heap\n" +
            "  VirtualAlloc / VirtualFree      — page-granularity, always safe to cross\n\n" +
            "Avoid /MT (static CRT) when crossing module boundaries — each module gets its " +
            "own private CRT copy and heap; mismatches are guaranteed."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
