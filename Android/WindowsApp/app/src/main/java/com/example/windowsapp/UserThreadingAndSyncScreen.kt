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
fun UserThreadingAndSyncScreen(navController: NavController) {
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
            text = "THREADING AND SYNCHRONIZATION",
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

        SectionHeader("CREATING A THREAD")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Use CreateThread to launch a new thread in the current process. The thread routine must match LPTHREAD_START_ROUTINE:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "DWORD WINAPI MyThreadProc(LPVOID param) {\n" +
            "    // param is whatever you passed as lpParameter\n" +
            "    return 0;\n" +
            "}\n\n" +
            "HANDLE hThread = CreateThread(\n" +
            "    NULL,          // default security\n" +
            "    0,             // default stack size\n" +
            "    MyThreadProc,  // thread function\n" +
            "    myParam,       // parameter\n" +
            "    0,             // run immediately (CREATE_SUSPENDED to pause)\n" +
            "    &threadId);    // receives thread ID"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Joining (waiting for thread to finish):")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Wait for one thread:\n" +
            "WaitForSingleObject(hThread, INFINITE);\n\n" +
            "// Wait for multiple threads:\n" +
            "HANDLE threads[] = { hT1, hT2, hT3 };\n" +
            "WaitForMultipleObjects(3, threads,\n" +
            "    TRUE,     // TRUE = wait for all; FALSE = wait for any\n" +
            "    INFINITE);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("CloseHandle(hThread) releases the handle but does NOT terminate the thread — the thread keeps running until its function returns. Avoid TerminateThread; it skips C++ destructors and TLS cleanup.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("EVENT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("An event is a kernel object with two states: signaled and non-signaled. Threads wait on it; it is signaled to wake them.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "HANDLE hEvent = CreateEvent(\n" +
            "    NULL,   // default security\n" +
            "    FALSE,  // FALSE = auto-reset; TRUE = manual-reset\n" +
            "    FALSE,  // initial state: non-signaled\n" +
            "    NULL);  // unnamed\n\n" +
            "// Signal (wake up one waiter if auto-reset):\n" +
            "SetEvent(hEvent);\n\n" +
            "// Reset manually (only needed for manual-reset events):\n" +
            "ResetEvent(hEvent);\n\n" +
            "// Wait:\n" +
            "WaitForSingleObject(hEvent, INFINITE);\n\n" +
            "CloseHandle(hEvent);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Auto-reset event: after SetEvent releases one waiting thread, the event automatically returns to non-signaled. Useful as a 'one thread proceeds' gate.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Manual-reset event: stays signaled after SetEvent until ResetEvent is called. All waiting threads are released at once. Useful for broadcasting 'work is ready' to multiple threads.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("MUTEX")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A mutex (mutually exclusive) is a kernel object that allows only one thread to own it at a time. It is used to protect shared data.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "HANDLE hMutex = CreateMutex(\n" +
            "    NULL,   // default security\n" +
            "    FALSE,  // FALSE = not initially owned\n" +
            "    NULL);  // unnamed (use a name for cross-process)\n\n" +
            "// Acquire (blocks if mutex is owned by another thread):\n" +
            "WaitForSingleObject(hMutex, INFINITE);\n\n" +
            "// --- protected section ---\n\n" +
            "// Release:\n" +
            "ReleaseMutex(hMutex);\n\n" +
            "CloseHandle(hMutex);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Abandoned mutex: if a thread exits without calling ReleaseMutex, the mutex is abandoned. The next WaitForSingleObject succeeds with WAIT_ABANDONED (0x00000080), signaling that the protected state may be corrupt.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Named mutexes (e.g., L\"Global\\\\MyMutex\") work across processes, making them useful for single-instance application enforcement.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SEMAPHORE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A semaphore maintains a count. WaitForSingleObject decrements the count (blocks if count is 0); ReleaseSemaphore increments it. Use a semaphore to limit concurrent access to N resources.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "HANDLE hSem = CreateSemaphore(\n" +
            "    NULL,   // default security\n" +
            "    3,      // initial count (3 slots available)\n" +
            "    3,      // maximum count\n" +
            "    NULL);  // unnamed\n\n" +
            "// Acquire one slot (blocks if count == 0):\n" +
            "WaitForSingleObject(hSem, INFINITE);\n\n" +
            "// --- use the resource ---\n\n" +
            "// Release one slot (increments count by 1):\n" +
            "ReleaseSemaphore(\n" +
            "    hSem,\n" +
            "    1,      // release count\n" +
            "    NULL);  // previous count (optional output)\n\n" +
            "CloseHandle(hSem);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("CRITICAL_SECTION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("CRITICAL_SECTION is a user-mode-only synchronization object. It does not create a kernel object — when uncontested, it uses an interlocked operation without a kernel transition, making it significantly faster than a Mutex.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "CRITICAL_SECTION cs;\n\n" +
            "// Initialize (once, before use):\n" +
            "InitializeCriticalSection(&cs);\n" +
            "// Or with a spin count (spins before blocking kernel):\n" +
            "InitializeCriticalSectionAndSpinCount(&cs, 4000);\n\n" +
            "// Enter (blocks if owned by another thread):\n" +
            "EnterCriticalSection(&cs);\n\n" +
            "// --- protected section ---\n\n" +
            "// Leave:\n" +
            "LeaveCriticalSection(&cs);\n\n" +
            "// Non-blocking try:\n" +
            "if (TryEnterCriticalSection(&cs)) {\n" +
            "    // got it\n" +
            "    LeaveCriticalSection(&cs);\n" +
            "}\n\n" +
            "// Cleanup:\n" +
            "DeleteCriticalSection(&cs);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("CRITICAL_SECTION is recursive: the same thread can call EnterCriticalSection multiple times without deadlocking, but must call LeaveCriticalSection the same number of times to release it.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Limitation: process-local only. Cannot be used across processes (use a named Mutex for that).")

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
