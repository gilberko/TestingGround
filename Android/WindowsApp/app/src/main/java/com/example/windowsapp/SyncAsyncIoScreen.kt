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
fun SyncAsyncIoScreen(navController: NavController) {
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
            text = "SYNC AND ASYNC IO",
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

        SectionHeader("SYNC VS ASYNC OVERVIEW")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Synchronous I/O: the calling thread blocks inside ReadFile / WriteFile until the " +
            "operation completes. Simple but wastes CPU time waiting.\n\n" +
            "Asynchronous I/O: the thread initiates the operation and returns immediately. The OS " +
            "signals completion later via one of three mechanisms (see below).\n\n" +
            "Key requirement: the handle must be opened with FILE_FLAG_OVERLAPPED:\n" +
            "  CreateFile(..., FILE_FLAG_OVERLAPPED, ...);\n\n" +
            "With an overlapped handle, ReadFile / WriteFile return FALSE and " +
            "GetLastError() == ERROR_IO_PENDING when the operation is in flight. On completion " +
            "the OS notifies you.\n\n" +
            "Many Win32 APIs support overlapped I/O:\n" +
            "  ReadFile / WriteFile\n" +
            "  ReadFileScatter / WriteFileGather\n" +
            "  WSARecv / WSASend / WSARecvFrom / WSASendTo\n" +
            "  DeviceIoControl\n" +
            "  TransactNamedPipe / ConnectNamedPipe\n" +
            "  AcceptEx / ConnectEx (Winsock extension functions)"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("THE OVERLAPPED STRUCT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("You allocate an OVERLAPPED and pass its address to every async call:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "typedef struct _OVERLAPPED {\n" +
            "    ULONG_PTR Internal;     // OS: NTSTATUS of the op\n" +
            "    ULONG_PTR InternalHigh; // OS: bytes transferred\n" +
            "    DWORD     Offset;       // File offset (low 32 bits)\n" +
            "    DWORD     OffsetHigh;   // File offset (high 32 bits)\n" +
            "    HANDLE    hEvent;       // Optional event to signal\n" +
            "} OVERLAPPED;"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Offset / OffsetHigh — file position for random-access I/O; set both to 0 for " +
            "named pipes and sockets (which have no seek position).\n\n" +
            "hEvent — create an event yourself; the OS signals it on completion. Leave NULL if " +
            "using IOCP or completion routines.\n\n" +
            "Internal / InternalHigh — filled by the OS; do not touch these yourself.\n\n" +
            "Lifetime: the OVERLAPPED struct AND your I/O buffer must remain valid until the " +
            "operation completes. See the Stack Allocation Pitfall section."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("THREE COMPLETION STRATEGIES")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Once an overlapped operation finishes, you find out via one of three mechanisms:\n\n" +
            "1. Event signaling\n" +
            "   Set OVERLAPPED.hEvent → call WaitForSingleObject or WaitForMultipleObjects " +
            "on the event → call GetOverlappedResult to retrieve bytes transferred and any error.\n" +
            "   Good for: simple code, up to 64 concurrent operations.\n\n" +
            "2. Completion routine (APC)\n" +
            "   Use ReadFileEx / WriteFileEx and supply a callback. The OS queues it as a " +
            "User-mode APC on the issuing thread. The thread must enter an alertable wait to " +
            "receive it.\n" +
            "   Good for: callback-style code on a single thread.\n\n" +
            "3. IO Completion Port (IOCP)\n" +
            "   Associate the handle with an IOCP. A pool of worker threads calls " +
            "GetQueuedCompletionStatus. Completions from any associated handle arrive at the " +
            "same port.\n" +
            "   Good for: high-performance servers, thousands of concurrent connections."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("COMPLETION ROUTINES (READFILEEX)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Issue an async read or write with a callback:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "ReadFileEx(hFile, buffer, nBytes, &ovr, MyCallback);\n" +
            "WriteFileEx(hFile, buffer, nBytes, &ovr, MyCallback);\n\n" +
            "// Callback signature:\n" +
            "VOID CALLBACK MyCallback(\n" +
            "    DWORD dwErrorCode,         // 0 = success\n" +
            "    DWORD dwBytesTransferred,\n" +
            "    LPOVERLAPPED lpOverlapped); // the &ovr you passed"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The callback fires in the SAME THREAD that called ReadFileEx — not in a new thread " +
            "or a thread pool thread.\n\n" +
            "To carry context into the callback, embed OVERLAPPED as the first member of a " +
            "larger struct. Cast lpOverlapped back to your struct type inside the callback:\n" +
            "  struct MyCtx { OVERLAPPED ovr; /* your fields */ };\n" +
            "  MyCtx* ctx = (MyCtx*)lpOverlapped;"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("COMPLETION ROUTINES ARE USER-MODE APCs")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Yes — completion routines submitted via ReadFileEx / WriteFileEx are delivered as " +
            "User-mode APCs (Asynchronous Procedure Calls).\n\n" +
            "The OS queues the APC on the thread that called ReadFileEx. The APC is only " +
            "delivered when that thread enters an alertable wait state. If the thread never " +
            "enters an alertable wait, the callback NEVER fires.\n\n" +
            "Alertable wait functions (pass TRUE for bAlertable):"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "SleepEx(INFINITE, TRUE);\n" +
            "WaitForSingleObjectEx(h, timeout, TRUE);\n" +
            "WaitForMultipleObjectsEx(n, hs, FALSE, timeout, TRUE);\n" +
            "MsgWaitForMultipleObjectsEx(..., MWMO_ALERTABLE);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "When at least one APC is queued, the alertable wait returns WAIT_IO_COMPLETION, " +
            "the pending APCs fire, and you re-enter the wait. Typical loop:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Issue several ReadFileEx calls, then pump APCs:\n" +
            "while (pendingOps > 0) {\n" +
            "    DWORD r = SleepEx(INFINITE, TRUE);\n" +
            "    // r == WAIT_IO_COMPLETION: one or more callbacks fired\n" +
            "    // decrement pendingOps inside the callback\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("STACK ALLOCATION PITFALL")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The OVERLAPPED struct and the I/O buffer passed to ReadFileEx must remain valid " +
            "until the completion callback fires. If you allocate them on the stack and the " +
            "initiating function returns before the I/O finishes, the stack is reused and " +
            "the OS writes into garbage memory."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// WRONG — stack memory gone when StartRead() returns:\n" +
            "void StartRead(HANDLE hFile) {\n" +
            "    OVERLAPPED ovr = {0}; // stack\n" +
            "    char buf[4096];       // stack\n" +
            "    ReadFileEx(hFile, buf, sizeof(buf), &ovr, Callback);\n" +
            "} // stack unwound → ovr & buf invalid → corruption"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Always heap-allocate the context, with OVERLAPPED as the first member:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "struct IoCtx {\n" +
            "    OVERLAPPED ovr; // MUST be first member\n" +
            "    char buf[4096];\n" +
            "};\n\n" +
            "void StartRead(HANDLE hFile) {\n" +
            "    IoCtx* ctx = new IoCtx{};\n" +
            "    ReadFileEx(hFile, ctx->buf, sizeof(ctx->buf),\n" +
            "               &ctx->ovr, Callback);\n" +
            "    // Return safely; ctx lives on the heap\n" +
            "}\n\n" +
            "VOID CALLBACK Callback(\n" +
            "    DWORD err, DWORD bytes, LPOVERLAPPED lpOvr) {\n" +
            "    IoCtx* ctx = (IoCtx*)lpOvr; // safe cast\n" +
            "    // use ctx->buf ...\n" +
            "    delete ctx;\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WAITING ON MULTIPLE HANDLES")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "select() is socket-only on Windows (and POSIX). There is no direct equivalent " +
            "for arbitrary kernel objects, but WaitForMultipleObjects covers most cases:\n\n" +
            "  WaitForMultipleObjects(n, handles, bWaitAll, timeout)\n\n" +
            "Limit: MAXIMUM_WAIT_OBJECTS = 64 handles simultaneously.\n\n" +
            "For overlapped file I/O: put a manual-reset event in each OVERLAPPED.hEvent, " +
            "then wait on all the events at once:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "ovr1.hEvent = CreateEvent(NULL, TRUE, FALSE, NULL);\n" +
            "ovr2.hEvent = CreateEvent(NULL, TRUE, FALSE, NULL);\n\n" +
            "ReadFile(hFile1, buf1, n, NULL, &ovr1);\n" +
            "ReadFile(hFile2, buf2, n, NULL, &ovr2);\n\n" +
            "HANDLE events[] = { ovr1.hEvent, ovr2.hEvent };\n" +
            "DWORD idx = WaitForMultipleObjects(2, events, FALSE, INFINITE);\n" +
            "// idx == WAIT_OBJECT_0   → ovr1 completed\n" +
            "// idx == WAIT_OBJECT_0+1 → ovr2 completed"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "For sockets: WSAPoll() works like POSIX poll(), or use WSAEventSelect with " +
            "WaitForMultipleObjects.\n\n" +
            "For more than 64 handles or high-throughput scenarios: use IOCP (see next section)."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("IOCP: THE SCALABLE CHOICE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "IO Completion Ports are the Windows answer to epoll / kqueue. Associate many " +
            "handles of different types (files, sockets, pipes) with one port. A pool of " +
            "worker threads dequeues completions — no 64-handle limit, no APC or alertable-" +
            "wait requirement.\n\n" +
            "Create a port, then associate handles:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Create the port (0 threads = one per logical CPU)\n" +
            "HANDLE hPort = CreateIoCompletionPort(\n" +
            "    INVALID_HANDLE_VALUE, NULL, 0, 0);\n\n" +
            "// Associate a handle; completionKey identifies it\n" +
            "CreateIoCompletionPort(\n" +
            "    hFile, hPort, (ULONG_PTR)pCtx, 0);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Worker thread loop:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "DWORD bytes;\n" +
            "ULONG_PTR key;   // your completionKey\n" +
            "LPOVERLAPPED lpOvr;\n\n" +
            "while (GetQueuedCompletionStatus(\n" +
            "        hPort, &bytes, &key, &lpOvr, INFINITE)) {\n" +
            "    IoCtx* ctx = (IoCtx*)lpOvr;\n" +
            "    // handle completion ...\n" +
            "}"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Any number of worker threads can call GetQueuedCompletionStatus on the same port. " +
            "The OS ensures at most N run concurrently (set when creating the port), reducing " +
            "context-switching overhead.\n\n" +
            "Used by IIS, SQL Server, and every high-performance Windows server. " +
            "See the IO Completion Port screen for a deeper walkthrough."
        )

        Spacer(modifier = Modifier.height(32.dp))
        HackerButton("BACK") { navController.popBackStack() }
    }
}
