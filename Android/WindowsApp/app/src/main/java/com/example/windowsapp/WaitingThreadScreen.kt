package com.example.windowsapp

import androidx.compose.foundation.background
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
fun WaitingThreadScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "WAITING THREAD\nBEHIND THE SCENES",
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

        SectionHeader("WHAT HAPPENS WHEN A THREAD WAITS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "When a thread calls WaitForSingleObject or WaitForMultipleObjects, the call transitions to the kernel via NtWaitForSingleObject / NtWaitForMultipleObjects, which call KeWaitForSingleObject / KeWaitForMultipleObjects.\n\n" +
            "The thread's state changes from Running to Waiting. It is removed from the CPU's run queue and the scheduler will not select it again until the condition is met.\n\n" +
            "For each dispatcher object being waited on, the kernel sets up a KWAIT_BLOCK that links the thread into that object's list of waiters."
        )
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("THE KWAIT_BLOCK STRUCTURE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "KWAIT_BLOCK is the data structure that records one thread's interest in one dispatcher object."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
typedef struct _KWAIT_BLOCK {
    LIST_ENTRY        WaitListEntry;   // into dispatcher object's WaitListHead
    struct _KTHREAD  *Thread;          // back-pointer to the waiting thread
    PVOID             Object;          // the dispatcher object
    struct _KWAIT_BLOCK *NextWaitBlock;// ring linking the thread's own blocks
    USHORT            WaitKey;         // index of this object in wait array
    USHORT            WaitType;        // WaitAny or WaitAll
} KWAIT_BLOCK;
        """.trimIndent())
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Wait blocks are NOT dynamically allocated for the common case. KTHREAD embeds an array WaitBlock[4] — the first three slots are for the waited objects and the fourth is reserved for the optional timeout timer. Waiting on more than 3 objects causes additional wait blocks to be allocated from NonPagedPool.\n\n" +
            "The thread's wait blocks are linked together in a ring via NextWaitBlock so the kernel can quickly walk all of a thread's waits."
        )
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WAIT BLOCKS AND DISPATCHER OBJECTS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Every kernel dispatcher object (KEVENT, KMUTEX, KSEMAPHORE, thread object, process object, timer, etc.) has a WaitListHead — a LIST_ENTRY that is the head of a doubly-linked list of KWAIT_BLOCKs.\n\n" +
            "When a thread begins waiting, each KWAIT_BLOCK.WaitListEntry is inserted into the corresponding object's WaitListHead.\n\n" +
            "A thread waiting on 5 objects simultaneously has 5 wait blocks, each sitting in a different object's waiter list."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
Thread T waits on objects A, B, C, D, E:

  T.WaitBlock[0] ──WaitListEntry──> A.WaitListHead
  T.WaitBlock[1] ──WaitListEntry──> B.WaitListHead
  T.WaitBlock[2] ──WaitListEntry──> C.WaitListHead
  T.WaitBlock[3] (reserved for timeout)
  extra blocks from NonPagedPool for D and E
        """.trimIndent())
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHO HANDLES THE WAIT — THE KERNEL DISPATCHER")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "It is the kernel dispatcher — not the object itself, and not a separate scheduler thread.\n\n" +
            "When an object is signaled (e.g. KeSetEvent, ReleaseMutex, thread exits), the signaling code calls KiWaitTest on that object. KiWaitTest walks the object's WaitListHead, inspects each KWAIT_BLOCK, and decides which threads to wake.\n\n" +
            "This happens atomically under the dispatcher lock (raised to SYNCH_LEVEL or HIGH_LEVEL), so no thread can be double-woken and no wait block can be inserted or removed concurrently."
        )
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WAITANY — FIRST OF N")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "When waiting WaitAny, the thread wakes as soon as any one of the objects is signaled.\n\n" +
            "When object C signals and KiWaitTest finds thread T in C's waiter list:\n" +
            "1. The kernel marks T as Ready.\n" +
            "2. The kernel walks T's own wait block ring (NextWaitBlock links) and removes each remaining wait block from A, B, D, E's WaitListHead.\n" +
            "3. T is placed on the run queue. The return value from WaitForMultipleObjects is the index of C (WAIT_OBJECT_0 + index).\n\n" +
            "A thread cannot be woken twice: the dispatcher lock prevents any other object from signaling T concurrently during this cleanup."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
// WaitAny — thread wakes on the first signal
HANDLE objects[5] = { hA, hB, hC, hD, hE };
DWORD result = WaitForMultipleObjects(5, objects,
                                      FALSE, // WaitAny
                                      INFINITE);
// result == WAIT_OBJECT_0 + 2 means object C (index 2) fired

// Behind the scenes when C signals:
//   KiWaitTest(C) finds T
//   → removes T's blocks from A,B,D,E WaitListHead
//   → marks T Ready
//   → T resumes with index 2
        """.trimIndent())
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WAITALL — ALL OF N")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "When waiting WaitAll, the thread does not wake until every object in the array has been signaled.\n\n" +
            "As each object signals, KiWaitTest checks: are ALL objects in this thread's wait satisfied? If not, the wait block stays linked in the signaled object's WaitListHead — the thread remains Waiting.\n\n" +
            "Only when the last object signals does the kernel see all conditions met: it removes ALL wait blocks from ALL objects and marks the thread Ready.\n\n" +
            "So yes — already-signaled objects keep the wait block linked in their WaitListHead until the last object fires. The thread's wait blocks remain spread across all object lists until that final signal."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
// WaitAll — thread wakes only when all 3 are signaled
HANDLE objects[3] = { hEventA, hMutexB, hSemC };
WaitForMultipleObjects(3, objects,
                       TRUE, // WaitAll
                       INFINITE);

// Behind the scenes:
//   EventA signals → KiWaitTest: not all satisfied
//                    → T's block stays in EventA.WaitListHead
//   SemC signals   → KiWaitTest: not all satisfied
//                    → T's block stays in SemC.WaitListHead
//   MutexB signals → KiWaitTest: ALL satisfied
//                    → remove T's blocks from EventA, SemC, MutexB
//                    → mark T Ready
        """.trimIndent())
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("PRACTICAL NOTES")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Maximum objects: WaitForMultipleObjects supports at most MAXIMUM_WAIT_OBJECTS (64) handles.\n\n" +
            "Kernel-mode: KeWaitForMultipleObjects takes an array of PVOID (pointers to dispatcher objects, not HANDLEs).\n\n" +
            "Dispatcher lock: all wait list manipulation happens under a spinlock at SYNCH_LEVEL. The window is very short.\n\n" +
            "WaitAll deadlock: if thread T1 waits for {MutexA, MutexB} and thread T2 waits for {MutexB, MutexA}, each acquires one and waits for the other — a classic deadlock. WaitAll does not protect against this.\n\n" +
            "Timeout: the 4th slot in KTHREAD.WaitBlock[] is used for the timeout object (a kernel timer). When the timeout fires, the timer signals and the thread wakes with WAIT_TIMEOUT."
        )
        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
