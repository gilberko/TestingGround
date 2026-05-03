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
fun ProcessesAndThreadsScreen(navController: NavController) {
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
            text = "PROCESSES AND THREADS",
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

        // OVERVIEW
        SectionHeader("OVERVIEW")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The Windows kernel represents processes and threads with two layered structures each:\n\n" +
            "  _EPROCESS — the Executive-level process object (full representation)\n" +
            "    └── Pcb (_KPROCESS) — the scheduler/dispatcher level (embedded as first field)\n\n" +
            "  _ETHREAD  — the Executive-level thread object (full representation)\n" +
            "    └── Tcb (_KTHREAD) — the scheduler/dispatcher level (embedded as first field)\n\n" +
            "KPROCESS/KTHREAD contain only what the scheduler and dispatcher need. EPROCESS/ETHREAD " +
            "extend them with I/O, security, handle tracking, and everything else."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ─── THREAD SECTION ───────────────────────────────────────
        SectionHeader("_ETHREAD")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "_ETHREAD is the Executive thread object — the kernel's full representation of a thread. " +
            "It is an Executive Object (managed by the Object Manager) and lives in non-paged pool.\n\n" +
            "Key fields:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "struct _ETHREAD {\n" +
            "    KTHREAD          Tcb;                // MUST be first — scheduler state\n" +
            "    CLIENT_ID        Cid;                // {UniqueProcess, UniqueThread}\n" +
            "    PEPROCESS        ThreadsProcess;     // owning EPROCESS\n" +
            "    PVOID            StartAddress;       // kernel thread start routine\n" +
            "    PVOID            Win32StartAddress;  // Win32 user-mode start address\n" +
            "    LIST_ENTRY       IrpList;            // IRPs currently issued by this thread\n" +
            "    EX_PUSH_LOCK     ThreadLock;         // protects thread fields\n" +
            "    // ... security, timer, impersonation, etc.\n" +
            "};"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // KTHREAD
        SectionHeader("KTHREAD  (Tcb — the scheduler kernel)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "KTHREAD is the scheduler's view of a thread. Because it is the first field of " +
            "ETHREAD, a pointer to ETHREAD is also a valid pointer to KTHREAD (and vice versa " +
            "with a simple cast). Key fields:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "struct _KTHREAD {\n" +
            "    DISPATCHER_HEADER Header;       // wait block — thread is a waitable object\n" +
            "    volatile KTHREAD_STATE State;   // Running/Ready/Waiting/Terminated/...\n" +
            "    KWAIT_REASON  WaitReason;       // why the thread is waiting\n" +
            "    CHAR          Priority;         // current dynamic priority (0–31)\n" +
            "    CHAR          BasePriority;     // base priority\n" +
            "    KAPC_STATE    ApcState;         // kernel and user-mode APC queues\n" +
            "    KAPC_STATE    SavedApcState;    // APCs saved during process attach\n" +
            "    PVOID         KernelStack;      // current kernel stack pointer\n" +
            "    PVOID         StackBase;        // kernel stack base\n" +
            "    PKTRAP_FRAME  TrapFrame;        // saved registers (when not running)\n" +
            "    PKSERVICE_TABLE_DESCRIPTOR ServiceTable; // SSDT pointer for this thread\n" +
            "    // ... scheduling, affinity, quantum, locks ...\n" +
            "};"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // THREAD STATE
        SectionHeader("THREAD STATE  (KTHREAD.State)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The KTHREAD_STATE enum stored in Tcb.State tracks the thread's scheduler state:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Initialized    — created but not yet started\n" +
            "Ready          — runnable, waiting for a free CPU\n" +
            "Running        — currently executing on a processor\n" +
            "Standby        — chosen to run next on a specific processor (pre-emption pending)\n" +
            "Terminated     — thread has exited; object may still be referenced\n" +
            "Waiting        — blocked waiting for an object (event, mutex, timer...)\n" +
            "Transition     — Waiting, but the kernel stack has been paged out\n" +
            "DeferredReady  — about to be placed in Ready queue (transitional)"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "When State == Waiting, the WaitReason field explains why: Executive (waiting " +
            "on a kernel object), FreePage, PageIn, PoolAllocation, DelayExecution (Sleep), " +
            "Suspended, UserRequest, WrQueue (thread pool), WrLpcReceive, and others. " +
            "Tools like WinDbg show this as the thread's wait reason in !thread output."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // APC LIST
        SectionHeader("APC LIST  (KTHREAD.ApcState)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "An APC (Asynchronous Procedure Call) is a function queued to execute in the " +
            "context of a specific thread — a way to get code to run inside a thread without " +
            "that thread polling for work.\n\n" +
            "The APC queues are stored in KTHREAD.ApcState, of type KAPC_STATE:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "struct _KAPC_STATE {\n" +
            "    LIST_ENTRY  ApcListHead[2]; // [0] = KernelMode APCs\n" +
            "                               // [1] = UserMode  APCs\n" +
            "    PKPROCESS   Process;\n" +
            "    BOOLEAN     KernelApcInProgress;\n" +
            "    BOOLEAN     KernelApcPending;\n" +
            "    BOOLEAN     UserApcPending;\n" +
            "};"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "There are three kinds of APC:\n\n" +
            "  Special Kernel APC — delivered at APC_LEVEL (IRQL 1) as soon as the thread " +
            "drops to PASSIVE_LEVEL. Cannot be blocked by the thread. Used for I/O completion " +
            "work (e.g. unlocking MDL pages, completing user-mode I/O).\n\n" +
            "  Normal Kernel APC — delivered at PASSIVE_LEVEL. Can be blocked if the thread " +
            "calls KeEnterCriticalRegion. Used internally by the kernel.\n\n" +
            "  User APC — delivered in user mode only when the thread is in an alertable wait " +
            "(WaitForSingleObjectEx with bAlertable=TRUE, SleepEx, etc.). Used by " +
            "ReadFileEx / WriteFileEx completion callbacks, QueueUserAPC, and WOW64 " +
            "mode-switch stubs.\n\n" +
            "Key uses of the APC mechanism:\n" +
            "  • Thread termination — a special kernel APC is injected that never returns\n" +
            "  • I/O completion delivery — the I/O manager queues a special APC to the\n" +
            "    issuing thread to finalize the IRP and copy status back to user mode\n" +
            "  • QueueUserAPC — lets one thread request work from another thread\n" +
            "  • KTHREAD.SavedApcState holds APCs that were deferred while the thread\n" +
            "    was temporarily attached to a different process address space"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // TEB
        SectionHeader("TEB  (Thread Environment Block)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The TEB is the user-mode counterpart to ETHREAD/KTHREAD. It lives in the " +
            "process's user-mode address space (not in kernel memory) and holds per-thread " +
            "state that user-mode code needs fast access to without making a syscall.\n\n" +
            "On x64, the GS segment register points to the TEB (gs:[0] = TEB address). " +
            "On x86, FS points to it (fs:[0x18] = TEB address).\n\n" +
            "Key TEB fields:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "struct _TEB {\n" +
            "    NT_TIB          NtTib;             // ExceptionList, StackBase, StackLimit\n" +
            "    PVOID           EnvironmentPointer;\n" +
            "    CLIENT_ID       ClientId;           // {ProcessId, ThreadId}\n" +
            "    PVOID           ActiveRpcHandle;\n" +
            "    PVOID           ThreadLocalStoragePointer; // TLS array (TlsGetValue)\n" +
            "    PPEB            ProcessEnvironmentBlock;   // pointer to the PEB\n" +
            "    ULONG           LastErrorValue;     // GetLastError() reads this\n" +
            "    ULONG           LastStatusValue;    // last NTSTATUS from a syscall\n" +
            "    // ... locale, GDI, fiber data, WOW64 info ...\n" +
            "};"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "GetLastError() / SetLastError() are simply reading/writing TEB.LastErrorValue — " +
            "no syscall involved, which is why they are effectively free. TLS (Thread Local " +
            "Storage) data accessed via TlsGetValue/TlsSetValue is an array hanging off " +
            "TEB.ThreadLocalStoragePointer."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // IRP LIST
        SectionHeader("IRP LIST  (ETHREAD.IrpList)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Every IRP issued synchronously or asynchronously by a thread is linked into " +
            "ETHREAD.IrpList — a doubly-linked list protected by a lock.\n\n" +
            "Purpose: when a thread exits, the I/O Manager walks this list and cancels all " +
            "outstanding IRPs on behalf of the dying thread. This ensures no IRP remains " +
            "pending after its issuing thread is gone.\n\n" +
            "For drivers: Irp->Tail.Overlay.Thread points back to the ETHREAD that issued " +
            "the IRP. IoCompleteRequest uses this to deliver the I/O completion APC to the " +
            "correct thread."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ─── PROCESS SECTION ──────────────────────────────────────
        SectionHeader("_EPROCESS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "_EPROCESS is the Executive process object — the kernel's full representation of " +
            "a process. It is also an Executive Object (Object Manager, reference counted). " +
            "Key fields:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "struct _EPROCESS {\n" +
            "    KPROCESS         Pcb;                  // MUST be first — scheduler state\n" +
            "    EX_PUSH_LOCK     ProcessLock;\n" +
            "    HANDLE           UniqueProcessId;       // the PID\n" +
            "    LIST_ENTRY       ActiveProcessLinks;    // global active process list\n" +
            "    EX_FAST_REF      Token;                 // security access token\n" +
            "    RTL_AVL_TREE     VadRoot;               // Virtual Address Descriptor tree\n" +
            "    PHANDLE_TABLE    ObjectTable;            // open handle table\n" +
            "    CHAR             ImageFileName[15];     // short image name (e.g. \"notepad.exe\")\n" +
            "    LIST_ENTRY       ThreadListHead;        // all ETHREADs of this process\n" +
            "    PPEB             Peb;                   // user-mode Process Environment Block\n" +
            "    EX_PUSH_LOCK     AddressCreationLock;\n" +
            "    PVOID            SectionObject;         // mapped image section\n" +
            "    PVOID            Wow64Process;          // WOW64 state (NULL if native 64-bit)\n" +
            "    // ... quota, debug port, exception port, job ...\n" +
            "};"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // KPROCESS
        SectionHeader("KPROCESS  (Pcb — the scheduler kernel)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "KPROCESS is the scheduler's view of a process. Like KTHREAD/ETHREAD, a pointer " +
            "to EPROCESS is a valid KPROCESS pointer. Key fields:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "struct _KPROCESS {\n" +
            "    DISPATCHER_HEADER Header;       // waitable — WaitForSingleObject on a process\n" +
            "    LIST_ENTRY   ProfileListHead;\n" +
            "    ULONG_PTR    DirectoryTableBase; // CR3 — physical addr of page table root\n" +
            "    LIST_ENTRY   ThreadListHead;     // KTHREADs belonging to this process\n" +
            "    ULONG        ProcessFlags;\n" +
            "    CHAR         BasePriority;       // base priority for threads\n" +
            "    CHAR         QuantumReset;       // quantum slice for new threads\n" +
            "    KAFFINITY    Affinity;           // processor affinity mask\n" +
            "};"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ALLOCATED MEMORY
        SectionHeader("ALLOCATED MEMORY — WHERE IT LIVES")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Memory allocation information is tracked at two levels:\n\n" +
            "1. Virtual layout — EPROCESS.VadRoot (Virtual Address Descriptor tree):\n\n" +
            "   The VAD tree is an AVL (self-balancing) binary tree where each node " +
            "describes one committed virtual address range in the process: its start/end VA, " +
            "type (private committed, mapped file, image section), and protection attributes " +
            "(PAGE_READWRITE, PAGE_EXECUTE_READ, etc.).\n\n" +
            "   VirtualAlloc, MapViewOfSection, and loading a DLL all insert nodes into the " +
            "VAD tree. VirtualFree removes them. The Memory Manager queries the VAD tree " +
            "when handling page faults to determine what should be mapped into a given range.\n\n" +
            "2. Physical layout — KPROCESS.DirectoryTableBase (CR3):\n\n" +
            "   CR3 is the physical address of the top-level page table (PML4 on x64). The " +
            "CPU loads this value on every context switch to this process. The hardware page " +
            "table hierarchy (PML4 → PDPT → PD → PT) maps virtual addresses to physical " +
            "frame numbers — recording exactly which physical pages are currently backing " +
            "each virtual address in the process.\n\n" +
            "   The working set — the set of currently resident (non-paged) pages — is " +
            "tracked in EPROCESS.WorkingSetList. Pages not in the working set are on disk " +
            "or in the standby/modified lists."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // OPEN HANDLES
        SectionHeader("OPEN HANDLES — WHERE THEY LIVE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Every process has a private handle table stored in EPROCESS.ObjectTable " +
            "(type PHANDLE_TABLE). The handle table maps integer handle values to kernel " +
            "object pointers plus their granted access mask.\n\n" +
            "When a user-mode call like CreateFile or OpenProcess succeeds, the kernel:\n" +
            "  1. Increments the reference count on the kernel object (ObReferenceObject)\n" +
            "  2. Inserts a new entry in the calling process's handle table\n" +
            "  3. Returns the handle value (a table index) to user mode\n\n" +
            "When CloseHandle is called:\n" +
            "  1. The entry is removed from the handle table\n" +
            "  2. ObDereferenceObject is called — if the ref count hits zero, the object\n" +
            "     is deleted\n\n" +
            "Inherited handles: at process creation, the parent's handle table entries " +
            "marked as inheritable are duplicated into the child's table.\n\n" +
            "Global handles: the kernel's own handle table (PsInitialSystemProcess->ObjectTable) " +
            "is used for handles that are process-independent, such as kernel event objects."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
