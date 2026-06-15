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
fun SynchronizationScreen(navController: NavController) {
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
            text = "SYNCHRONIZATION",
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

        SectionHeader("WHY SYNCHRONIZATION?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Modern systems are multicore and interrupt-driven. Without synchronization, " +
            "two CPUs can modify the same data simultaneously (torn write), an interrupt " +
            "handler can preempt a thread mid-operation (lost update), and DPCs can interleave " +
            "with thread code that touches the same structures.\n\n" +
            "The Windows kernel provides a range of synchronization primitives. Each is " +
            "designed for a specific IRQL range. Using the wrong primitive at the wrong " +
            "IRQL causes either a bugcheck or a deadlock."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("INTERRUPT SPIN LOCK (DIRQL)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Each KINTERRUPT object (created by IoConnectInterrupt) has an associated spin lock. " +
            "The kernel acquires this spin lock automatically before calling your ISR and " +
            "releases it when the ISR returns. This prevents your ISR from being called " +
            "concurrently on two CPUs for the same interrupt.\n\n" +
            "If code outside the ISR (e.g., a DPC or dispatch routine) needs to access the " +
            "same shared data that the ISR touches, it must also acquire the interrupt spin lock " +
            "explicitly. KeAcquireInterruptSpinLock raises the caller to the device's DIRQL, " +
            "which blocks the ISR from running on this CPU while the lock is held.\n\n" +
            "Not reentrant. Hold time must be extremely short — nanoseconds, not microseconds."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "KIRQL oldIrql;\n" +
            "KeAcquireInterruptSpinLock(gInterruptObject, &oldIrql);\n" +
            "// access shared device data\n" +
            "KeReleaseInterruptSpinLock(gInterruptObject, oldIrql);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("HOW SPIN LOCKS WORK")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "A spin lock is a single aligned machine word in memory, initially zero. " +
            "KeAcquireSpinLock uses an atomic compare-exchange instruction to set it to 1. " +
            "If the lock is already 1 (owned by another CPU), the acquiring CPU loops in a " +
            "tight busy-wait — it spins — reading the lock until the owning CPU clears it.\n\n" +
            "There is no scheduler involvement, no sleep, no context switch. This is exactly " +
            "why spin locks can be used at DISPATCH_LEVEL and DIRQL where sleeping is " +
            "absolutely forbidden: at those IRQLs, the scheduler does not preempt and " +
            "blocking APIs would deadlock."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SPIN LOCK (KSPIN_LOCK) — DISPATCH_LEVEL")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The basic kernel spin lock. KeAcquireSpinLock raises the calling CPU to " +
            "DISPATCH_LEVEL (disabling preemption and DPCs on that CPU) and then acquires " +
            "the lock. KeReleaseSpinLock releases the lock and restores the old IRQL.\n\n" +
            "Not reentrant — acquiring a spin lock you already hold on the same CPU deadlocks."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "KSPIN_LOCK lock;\n" +
            "KeInitializeSpinLock(&lock);\n\n" +
            "// Acquire:\n" +
            "KIRQL oldIrql;\n" +
            "KeAcquireSpinLock(&lock, &oldIrql);\n" +
            "// critical section (running at DISPATCH_LEVEL)\n" +
            "KeReleaseSpinLock(&lock, oldIrql);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("QUEUED SPIN LOCK — DISPATCH_LEVEL")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "A more scalable variant. Each CPU that wants the lock spins on its own " +
            "per-CPU queue node (KLOCK_QUEUE_HANDLE on the stack) rather than on the " +
            "shared lock word. This reduces cache-line bouncing on multicore systems and " +
            "provides FIFO ordering.\n\n" +
            "Same IRQL rules as KSPIN_LOCK. Not reentrant."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "KSPIN_LOCK lock;\n" +
            "KeInitializeSpinLock(&lock);\n\n" +
            "KLOCK_QUEUE_HANDLE lockHandle;\n" +
            "KeAcquireInStackQueuedSpinLock(&lock, &lockHandle);\n" +
            "// critical section\n" +
            "KeReleaseInStackQueuedSpinLock(&lockHandle);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("FAST MUTEX (FAST_MUTEX) — PASSIVE_LEVEL")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "A non-spinning mutex optimized for the common uncontended case. When acquired, " +
            "it raises the thread to APC_LEVEL, which blocks APC delivery while the mutex " +
            "is held (preventing re-entrancy via APCs).\n\n" +
            "Faster than KMUTEX for uncontended cases; puts the thread to sleep (waits) " +
            "on contention rather than spinning.\n\n" +
            "Not reentrant. Usable only at PASSIVE_LEVEL."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "FAST_MUTEX fastMutex;\n" +
            "ExInitializeFastMutex(&fastMutex);\n\n" +
            "ExAcquireFastMutex(&fastMutex);  // raises to APC_LEVEL\n" +
            "// critical section\n" +
            "ExReleaseFastMutex(&fastMutex);  // drops back to PASSIVE_LEVEL"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("MUTEX (KMUTEX) — PASSIVE_LEVEL")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The kernel mutex. A waiting thread sleeps (is descheduled) until the mutex " +
            "becomes available — the scheduler finds another thread to run.\n\n" +
            "IS reentrant by the same thread: if the owning thread calls KeWaitForSingleObject " +
            "on a mutex it already owns, it succeeds immediately (the kernel tracks an " +
            "ownership count). Each successful wait must be matched with one KeReleaseMutex.\n\n" +
            "Usable only at PASSIVE_LEVEL (sleeping requires it)."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "KMUTEX mutex;\n" +
            "KeInitializeMutex(&mutex, 0);  // 0 = not owned initially\n\n" +
            "// Acquire (sleeps if contended):\n" +
            "KeWaitForSingleObject(&mutex, Executive, KernelMode, FALSE, NULL);\n" +
            "// critical section\n" +
            "KeReleaseMutex(&mutex, FALSE);  // FALSE = no other wait follows immediately"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SEMAPHORE (KSEMAPHORE) — PASSIVE_LEVEL")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "A counting semaphore with an integer count. KeReleaseSemaphore increments the " +
            "count (can be called at any IRQL up to DISPATCH_LEVEL). " +
            "KeWaitForSingleObject decrements it; if count is 0, the thread sleeps " +
            "(PASSIVE_LEVEL required for waiting).\n\n" +
            "Not reentrant — it is a count, not an ownership concept.\n\n" +
            "Typical use: producer signals (releases), consumer waits."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "KSEMAPHORE sem;\n" +
            "KeInitializeSemaphore(&sem, 0, MAXLONG);  // initial=0, max=MAXLONG\n\n" +
            "// Signal (from any IRQL <= DISPATCH_LEVEL):\n" +
            "KeReleaseSemaphore(&sem, IO_NO_INCREMENT, 1, FALSE);\n\n" +
            "// Wait (PASSIVE_LEVEL only — may sleep):\n" +
            "KeWaitForSingleObject(&sem, Executive, KernelMode, FALSE, NULL);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("EVENT (KEVENT) — PASSIVE_LEVEL TO WAIT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "An event has two states: signaled and not-signaled. There are two types:\n\n" +
            "  SynchronizationEvent (auto-reset) — when signaled, wakes exactly one " +
            "waiting thread, then automatically resets to not-signaled.\n\n" +
            "  NotificationEvent (manual-reset) — when signaled, wakes all waiting " +
            "threads and stays signaled until KeClearEvent or KeResetEvent is called.\n\n" +
            "KeSetEvent can be called at any IRQL up to DISPATCH_LEVEL. " +
            "KeWaitForSingleObject on an event requires PASSIVE_LEVEL (it may sleep)."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "KEVENT event;\n" +
            "KeInitializeEvent(&event, NotificationEvent, FALSE); // starts not-signaled\n\n" +
            "// Signal (e.g., from a DPC at DISPATCH_LEVEL):\n" +
            "KeSetEvent(&event, IO_NO_INCREMENT, FALSE);\n\n" +
            "// Wait (PASSIVE_LEVEL — may sleep):\n" +
            "KeWaitForSingleObject(&event, Executive, KernelMode, FALSE, NULL);\n\n" +
            "// Reset for NotificationEvent:\n" +
            "KeClearEvent(&event);  // sets to not-signaled"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ERESOURCE — PASSIVE_LEVEL")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "ERESOURCE is a read/write lock. Multiple threads can hold it shared (read) " +
            "simultaneously; only one thread at a time can hold it exclusive (write).\n\n" +
            "IS reentrant: the owning thread can re-acquire the resource shared or exclusive " +
            "without deadlocking.\n\n" +
            "Must be used with KeEnterCriticalRegion / KeLeaveCriticalRegion to prevent " +
            "APC delivery while the resource is held (APCs could try to acquire the same " +
            "resource and deadlock).\n\n" +
            "PASSIVE_LEVEL only — sleeping is required on contention."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "ERESOURCE res;\n" +
            "ExInitializeResourceLite(&res);\n\n" +
            "// Shared (read) acquire:\n" +
            "KeEnterCriticalRegion();\n" +
            "ExAcquireResourceSharedLite(&res, TRUE);  // TRUE = wait\n" +
            "// read shared data\n" +
            "ExReleaseResourceLite(&res);\n" +
            "KeLeaveCriticalRegion();\n\n" +
            "// Exclusive (write) acquire:\n" +
            "KeEnterCriticalRegion();\n" +
            "ExAcquireResourceExclusiveLite(&res, TRUE);\n" +
            "// modify shared data\n" +
            "ExReleaseResourceLite(&res);\n" +
            "KeLeaveCriticalRegion();\n\n" +
            "ExDeleteResourceLite(&res);  // cleanup"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("PUSH LOCK (EX_PUSH_LOCK) — PASSIVE_LEVEL")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "A lightweight read/write lock. Cheaper than ERESOURCE — fits in a single " +
            "pointer-sized field, no additional kernel resources allocated.\n\n" +
            "Not reentrant. PASSIVE_LEVEL only.\n\n" +
            "Use when contention is expected to be low and the overhead of ERESOURCE " +
            "is not justified."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "EX_PUSH_LOCK pushLock;\n" +
            "ExInitializePushLock(&pushLock);\n\n" +
            "// Shared acquire:\n" +
            "ExAcquirePushLockShared(&pushLock);\n" +
            "// read\n" +
            "ExReleasePushLock(&pushLock);\n\n" +
            "// Exclusive acquire:\n" +
            "ExAcquirePushLockExclusive(&pushLock);\n" +
            "// write\n" +
            "ExReleasePushLock(&pushLock);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("RCU (READ-COPY-UPDATE)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("RCU is a synchronization pattern from the Linux kernel that allows readers to run lock-free while a writer atomically replaces a data structure. The writer makes a copy, modifies the copy, publishes the new pointer atomically, then waits for all concurrent readers to finish before freeing the old version.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Windows does NOT expose an RCU API in the DDI (Driver Development Interface). There is no documented rcu_read_lock / call_rcu equivalent for driver developers.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The closest Windows DDI mechanisms for read-mostly patterns:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "1. EX_PUSH_LOCK (shared/exclusive) -- already covered above.\n" +
            "   Readers acquire shared (non-blocking if no writer);\n" +
            "   writer acquires exclusive. Not lock-free, but very\n" +
            "   lightweight for read-dominated workloads.\n\n" +
            "2. InterlockedExchangePointer -- atomically swap a pointer.\n" +
            "   Combined with EX_PUSH_LOCK or reference counting this\n" +
            "   can approximate the 'publish new version' step of RCU.\n\n" +
            "3. EX_RUNDOWN_REF -- prevents object teardown while\n" +
            "   references are outstanding. Readers call\n" +
            "   ExAcquireRundownProtection; writer calls\n" +
            "   ExWaitForRundownProtectionRelease to wait for all\n" +
            "   readers to finish before freeing.\n" +
            "   This matches the RCU 'grace period' concept."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Rundown protection pattern:\n" +
            "EX_RUNDOWN_REF rundown;\n" +
            "ExInitializeRundownProtection(&rundown);\n\n" +
            "// Reader side:\n" +
            "if (ExAcquireRundownProtection(&rundown)) {\n" +
            "    // use shared object\n" +
            "    ExReleaseRundownProtection(&rundown);\n" +
            "}\n\n" +
            "// Writer/cleanup side:\n" +
            "ExWaitForRundownProtectionRelease(&rundown);\n" +
            "// all readers have exited -- safe to free\n" +
            "// (reinitialize with ExReInitializeRundownProtection\n" +
            "//  if the object will be reused)"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Windows does use RCU-like mechanisms internally (e.g., in the kernel's own lock-free data structures and in Hyper-V), but these are not exported through the public DDI. For driver code, EX_PUSH_LOCK + EX_RUNDOWN_REF is the practical combination.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SUMMARY TABLE")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Primitive         IRQL           Reentrant  Sleeps  Notes\n" +
            "────────────────────────────────────────────────────────────────\n" +
            "Interrupt SpinLk  <= DIRQL       No         No      Raises to DIRQL; ISR sync\n" +
            "KSPIN_LOCK        <= DISPATCH   No         No      Raises to DISPATCH\n" +
            "Queued SpinLk     <= DISPATCH   No         No      Better multicore\n" +
            "FAST_MUTEX        PASSIVE       No         Yes     Raises to APC\n" +
            "KMUTEX            PASSIVE       Yes        Yes     Kernel mutex\n" +
            "KSEMAPHORE        wait:PASSIVE  No         Yes     Counting\n" +
            "KEVENT            wait:PASSIVE  N/A        Yes     Set at <=DISPATCH\n" +
            "ERESOURCE         PASSIVE       Yes        Yes     R/W lock\n" +
            "EX_PUSH_LOCK      PASSIVE       No         Yes     Lightweight R/W\n" +
            "EX_RUNDOWN_REF    PASSIVE       No         Yes     Grace-period (RCU-like)"
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
