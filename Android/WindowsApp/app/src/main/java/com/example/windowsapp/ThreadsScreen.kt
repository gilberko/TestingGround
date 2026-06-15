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
fun ThreadsScreen(navController: NavController) {
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
            text = "THREADS",
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

        SectionHeader("SYSTEM THREADS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Kernel drivers can create dedicated threads using PsCreateSystemThread — " +
            "the kernel equivalent of user-mode CreateThread.\n\n" +
            "System threads run in the context of the System process (PID 4). They have " +
            "no user-mode address space. They start at PASSIVE_LEVEL, so they can use the " +
            "full range of kernel APIs including those that sleep (KeWaitForSingleObject, " +
            "ZwReadFile, etc.).\n\n" +
            "Use system threads for long-running or blocking work that cannot be done in " +
            "dispatch routines or DPCs (which run at DISPATCH_LEVEL and cannot sleep)."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("PsCreateSystemThread")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "NTSTATUS PsCreateSystemThread(\n" +
            "    PHANDLE ThreadHandle,         // out: handle to new thread\n" +
            "    ULONG   DesiredAccess,         // THREAD_ALL_ACCESS\n" +
            "    POBJECT_ATTRIBUTES ObjectAttr, // NULL for defaults\n" +
            "    HANDLE  ProcessHandle,         // NULL = System process\n" +
            "    PCLIENT_ID ClientId,           // NULL if not needed\n" +
            "    PKSTART_ROUTINE StartRoutine,  // your thread function\n" +
            "    PVOID   StartContext);          // passed to StartRoutine"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "On success, *ThreadHandle is a kernel handle (valid only in the System process). " +
            "You must either close it with ZwClose or convert it to an object pointer with " +
            "ObReferenceObjectByHandle — never let it leak."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("THREAD ROUTINE")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "VOID MyThreadRoutine(PVOID StartContext) {\n" +
            "    MY_CONTEXT* ctx = (MY_CONTEXT*)StartContext;\n\n" +
            "    while (!ctx->ShouldStop) {\n" +
            "        // do work at PASSIVE_LEVEL\n" +
            "        // can call ZwXxx, KeWaitForSingleObject, etc.\n" +
            "    }\n\n" +
            "    PsTerminateSystemThread(STATUS_SUCCESS);\n" +
            "    // alternatively: just return — Windows will terminate it\n" +
            "}"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "PsTerminateSystemThread must be called from within the thread itself — " +
            "you cannot terminate another thread from outside. To stop a thread, " +
            "set a shared flag and signal an event that the thread is waiting on."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WAITING FOR A THREAD (JOIN EQUIVALENT)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Thread objects are dispatcher objects — they become signaled when the thread " +
            "exits. You cannot wait directly on a handle from kernel mode. " +
            "Instead, convert the handle to an object pointer, then wait on the object."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "HANDLE hThread;\n" +
            "PsCreateSystemThread(&hThread, THREAD_ALL_ACCESS, NULL,\n" +
            "                     NULL, NULL, MyThreadRoutine, ctx);\n\n" +
            "// Convert handle to object pointer:\n" +
            "PVOID threadObj;\n" +
            "ObReferenceObjectByHandle(hThread, THREAD_ALL_ACCESS, NULL,\n" +
            "                          KernelMode, &threadObj, NULL);\n\n" +
            "// Close the handle — we have the object reference:\n" +
            "ZwClose(hThread);\n\n" +
            "// Signal the thread to stop (your mechanism):\n" +
            "ctx->ShouldStop = TRUE;\n" +
            "KeSetEvent(&ctx->StopEvent, 0, FALSE);\n\n" +
            "// Wait for the thread to exit (PASSIVE_LEVEL required):\n" +
            "KeWaitForSingleObject(threadObj, Executive,\n" +
            "                      KernelMode, FALSE, NULL);\n\n" +
            "// Release the reference:\n" +
            "ObDereferenceObject(threadObj);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DETACHING (FIRE AND FORGET)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "If you do not need to wait for the thread to finish, close the handle " +
            "immediately after creation. The thread continues running; when it exits, " +
            "the Object Manager frees the thread object once its reference count reaches 0."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "HANDLE hThread;\n" +
            "PsCreateSystemThread(&hThread, THREAD_ALL_ACCESS, NULL,\n" +
            "                     NULL, NULL, MyThreadRoutine, ctx);\n" +
            "ZwClose(hThread);  // detach: thread runs independently"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Warning: if the driver unloads while the thread is still running, the thread " +
            "will execute freed code and crash the system. Always ensure the thread has " +
            "exited before DriverUnload returns."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WORK ITEMS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Work items are a lighter alternative to dedicated threads. The kernel maintains " +
            "a pool of worker threads (ExpWorkerThread). You queue a callback to run on one " +
            "of those threads instead of creating your own.\n\n" +
            "Think of work items exactly like a thread pool: IoQueueWorkItem is equivalent " +
            "to submitting a task to a thread pool. The callback runs at PASSIVE_LEVEL in " +
            "the context of the system worker thread."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WORK ITEM API")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// 1. Allocate (once, e.g., in AddDevice):\n" +
            "PIO_WORKITEM workItem = IoAllocateWorkItem(deviceObject);\n\n" +
            "// 2. Queue from any IRQL up to DISPATCH_LEVEL:\n" +
            "IoQueueWorkItem(\n" +
            "    workItem,\n" +
            "    MyWorkCallback,      // called at PASSIVE_LEVEL\n" +
            "    DelayedWorkQueue,    // queue type (see below)\n" +
            "    context);            // passed to callback\n\n" +
            "// 3. The callback:\n" +
            "VOID MyWorkCallback(\n" +
            "    PDEVICE_OBJECT DevObj,\n" +
            "    PVOID Context)\n" +
            "{\n" +
            "    // Runs at PASSIVE_LEVEL on a system worker thread.\n" +
            "    // Do your work here.\n\n" +
            "    PIO_WORKITEM wi = (PIO_WORKITEM)Context;\n" +
            "    IoFreeWorkItem(wi);  // always free inside the callback\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("QUEUE TYPES")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "DelayedWorkQueue      — normal priority; use for most background work.\n" +
            "CriticalWorkQueue     — higher priority; use for time-sensitive work.\n" +
            "HyperCriticalWorkQueue — highest priority; reserved for OS use; avoid.\n\n" +
            "The kernel has a fixed number of worker threads per queue. If all threads " +
            "are busy, new work items queue up and wait."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("LIMITATIONS OF WORK ITEMS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Work items run at PASSIVE_LEVEL — you cannot perform DISPATCH_LEVEL-only " +
            "operations from them directly (though you can queue a DPC).\n\n" +
            "Do not block indefinitely in a work item callback. Blocking one worker thread " +
            "can starve the entire pool. For long-blocking operations, create a dedicated " +
            "system thread instead.\n\n" +
            "No guaranteed ordering — two work items queued in order may run concurrently " +
            "on different worker threads.\n\n" +
            "Always free the work item inside the callback with IoFreeWorkItem. " +
            "Never free it before the callback runs (race condition), and never " +
            "access the work item after freeing it.\n\n" +
            "The work item holds a reference to the device object. Do not delete " +
            "the device object while a work item referencing it is still queued."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
