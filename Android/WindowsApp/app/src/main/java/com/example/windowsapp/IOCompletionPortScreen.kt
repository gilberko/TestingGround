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
fun IOCompletionPortScreen(navController: NavController) {
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
            text = "IO COMPLETION PORT",
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

        SectionHeader("ASYNC OPERATIONS OVERVIEW")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Synchronous I/O: the calling thread blocks until the operation completes.\n\nAsynchronous I/O: the thread initiates the operation and continues running; the OS notifies it when done.\n\nRequirement: the handle must be opened with FILE_FLAG_OVERLAPPED.\n\nKey struct: OVERLAPPED\n• Offset / OffsetHigh — file position for seeks\n• hEvent — optional event signalled on completion\n• Internal / InternalHigh — OS-filled: status code and byte count")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("READFILEEX AND COMPLETION ROUTINES")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("ReadFileEx issues an async read and registers a completion routine (callback). The routine is queued as a User APC and only fires when the issuing thread enters an alertable wait.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "ReadFileEx(hFile, buf, nBytes,\n" +
            "           &ovr, CompletionRoutine);\n\n" +
            "// Completion routine signature:\n" +
            "VOID CALLBACK CompletionRoutine(\n" +
            "    DWORD dwErrorCode,\n" +
            "    DWORD dwBytesTransferred,\n" +
            "    LPOVERLAPPED lpOvr);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Alertable waits that allow the APC to fire:\n• SleepEx(INFINITE, TRUE)\n• WaitForSingleObjectEx(h, ms, TRUE)\n• WaitForMultipleObjectsEx(..., TRUE, ...)\n\nIf the thread never enters an alertable wait, the routine never fires.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("OVERLAPPED WITH EVENTS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Alternative: set hEvent in the OVERLAPPED struct, call ReadFile (not Ex), then wait on the event.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "OVERLAPPED ovr = {0};\n" +
            "ovr.hEvent = CreateEvent(\n" +
            "    NULL, TRUE, FALSE, NULL);\n\n" +
            "ReadFile(hFile, buf, 4096, NULL, &ovr);\n\n" +
            "// Block until done:\n" +
            "WaitForSingleObject(ovr.hEvent, INFINITE);\n\n" +
            "DWORD bytesRead;\n" +
            "GetOverlappedResult(hFile, &ovr,\n" +
            "                    &bytesRead, FALSE);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("IO COMPLETION PORTS (IOCP)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("An IOCP is a kernel object designed for scalable async I/O. Associate many handles of many types to a single port. A pool of worker threads dequeues completed operations.\n\nCreate a port:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// numThreads=0 → one per logical CPU\n" +
            "HANDLE hPort = CreateIoCompletionPort(\n" +
            "    INVALID_HANDLE_VALUE,\n" +
            "    NULL, 0, 0);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Associate a handle with the port. completionKey is any user-defined value (e.g. pointer to a context struct) that identifies this handle when a completion arrives:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "CreateIoCompletionPort(\n" +
            "    hFile, hPort,\n" +
            "    (ULONG_PTR)pContext, // completionKey\n" +
            "    0);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Issue an async operation (ReadFile, WriteFile, WSARecv, etc.) with an OVERLAPPED. When it completes, the OS posts a packet to the port.\n\nWorker thread dequeues packets:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "DWORD bytes;\n" +
            "ULONG_PTR key;\n" +
            "LPOVERLAPPED lpOvr;\n\n" +
            "while (GetQueuedCompletionStatus(\n" +
            "        hPort, &bytes, &key,\n" +
            "        &lpOvr, INFINITE)) {\n" +
            "    // key  → which context/handle\n" +
            "    // bytes → bytes transferred\n" +
            "    // lpOvr → your OVERLAPPED\n" +
            "    HandleCompletion(key, bytes, lpOvr);\n" +
            "}"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Graceful shutdown — post sentinel packets to wake workers:\n\nPostQueuedCompletionStatus(hPort, 0, SHUTDOWN_KEY, NULL);\n\nThread count: one per CPU core is typical. IOCP ensures at most numThreads run concurrently even when many are blocked waiting.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("HANDLE TYPES COMPATIBLE WITH IOCP")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Compatible (must use FILE_FLAG_OVERLAPPED or WSA_FLAG_OVERLAPPED):\n• Files (FILE_FLAG_OVERLAPPED)\n• Sockets (WSASocket with WSA_FLAG_OVERLAPPED)\n• Named pipes — server-side\n• Serial communication ports\n• Mailslots\n\nNOT compatible:\n• Console handles\n• Process / thread handles\n• Synchronization objects (use waitable timers via CreateThreadpoolTimer instead)")

        Spacer(modifier = Modifier.height(32.dp))
        HackerButton("BACK") { navController.popBackStack() }
    }
}
