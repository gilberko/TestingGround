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
fun KernelDataStructuresScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "KERNEL DATA STRUCTURES",
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

        SectionHeader("DOUBLY LINKED LIST (LIST_ENTRY)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "LIST_ENTRY is the workhorse of the Windows kernel. It is embedded directly inside the container struct — not allocated separately. The list head is also a LIST_ENTRY, forming a circular doubly-linked ring.\n\n" +
            "Access the container from a list pointer using CONTAINING_RECORD(ptr, TYPE, field).\n\n" +
            "APIs: InitializeListHead, InsertHeadList, InsertTailList, RemoveHeadList, RemoveTailList, RemoveEntryList, IsListEmpty.\n\n" +
            "Not thread-safe. Pair with KeAcquireSpinLock or IO_REMOVE_LOCK for concurrent access."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
typedef struct _MY_ITEM {
    LIST_ENTRY ListEntry;
    ULONG      Data;
} MY_ITEM;

LIST_ENTRY g_Head;
InitializeListHead(&g_Head);

// Add
MY_ITEM *item = ExAllocatePool2(POOL_FLAG_NON_PAGED,
                                sizeof(MY_ITEM), 'ITEM');
item->Data = 42;
InsertTailList(&g_Head, &item->ListEntry);

// Enumerate (Flink = forward, Blink = backward)
for (PLIST_ENTRY e = g_Head.Flink; e != &g_Head; e = e->Flink) {
    MY_ITEM *cur = CONTAINING_RECORD(e, MY_ITEM, ListEntry);
    DbgPrint("Data: %lu\n", cur->Data);
}

// Remove individual entry
RemoveEntryList(&item->ListEntry);
ExFreePoolWithTag(item, 'ITEM');
        """.trimIndent())
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SINGLY LINKED LIST")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Two variants:\n\n" +
            "SINGLE_LIST_ENTRY — raw LIFO stack. PushEntryList / PopEntryList are NOT thread-safe; the caller must hold a lock. Suitable only when a lock already protects the structure.\n\n" +
            "SLIST_HEADER — preferred for multi-threaded use. Lock-free interlocked operations work correctly at any IRQL, including DISPATCH_LEVEL. Items must be MEMORY_ALLOCATION_ALIGNMENT-aligned (use DECLSPEC_ALIGN or rely on ExAllocatePool2 which already aligns allocations)."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
typedef struct DECLSPEC_ALIGN(MEMORY_ALLOCATION_ALIGNMENT) _MY_NODE {
    SLIST_ENTRY SListEntry;
    ULONG       Value;
} MY_NODE;

SLIST_HEADER g_Stack;
ExInitializeSListHead(&g_Stack);

// Push — safe from any IRQL, any CPU
MY_NODE *node = ExAllocatePool2(POOL_FLAG_NON_PAGED,
                                sizeof(MY_NODE), 'SLST');
node->Value = 7;
InterlockedPushEntrySList(&g_Stack, &node->SListEntry);

// Pop
PSLIST_ENTRY e = InterlockedPopEntrySList(&g_Stack);
if (e) {
    MY_NODE *got = CONTAINING_RECORD(e, MY_NODE, SListEntry);
    DbgPrint("Got: %lu\n", got->Value);
    ExFreePoolWithTag(got, 'SLST');
}

USHORT depth = ExQueryDepthSList(&g_Stack);
        """.trimIndent())
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("HASH TABLE (RTL_HASH_TABLE)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Available since Windows 8. A chained hash table where each bucket is a doubly-linked list.\n\n" +
            "The caller embeds RTL_HASH_TABLE_ENTRY in the container struct and supplies a hash value on each operation. The table does NOT compute the hash — the caller does.\n\n" +
            "Because multiple keys can share the same hash bucket, RtlLookupEntryHashTable returns the first matching entry; the caller must walk further and verify the key matches.\n\n" +
            "Not thread-safe. Protect with a lock when accessed from multiple threads or IRQL > PASSIVE_LEVEL."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
typedef struct _MY_RECORD {
    RTL_HASH_TABLE_ENTRY HashEntry;
    ULONG                Key;
    ULONG                Value;
} MY_RECORD;

RTL_HASH_TABLE g_Table;
RtlInitHashTable(&g_Table, 64, 0); // 64 buckets

// Insert
MY_RECORD *rec = ExAllocatePool2(POOL_FLAG_NON_PAGED,
                                 sizeof(MY_RECORD), 'HTBL');
rec->Key = 99; rec->Value = 123;
ULONG hash = rec->Key; // real code uses a proper hash fn
RtlInsertEntryHashTable(&g_Table, &rec->HashEntry, hash, NULL);

// Lookup — walk chain and check key
RTL_HASH_TABLE_ENUMERATOR en;
PRTL_HASH_TABLE_ENTRY entry =
    RtlLookupEntryHashTable(&g_Table, hash, &en);
while (entry) {
    MY_RECORD *r = CONTAINING_RECORD(entry, MY_RECORD, HashEntry);
    if (r->Key == 99) { /* found */ break; }
    entry = RtlGetNextEntryHashTable(&g_Table, &en);
}
RtlEndEnumerationHashTable(&g_Table, &en);

// Remove
RtlRemoveEntryHashTable(&g_Table, &rec->HashEntry, NULL);
RtlDeleteHashTable(&g_Table);
        """.trimIndent())
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SPLAY TREE (RTL_GENERIC_TABLE)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "A self-adjusting BST — recently accessed nodes rotate to the root. Amortized O(log n) with excellent cache behaviour for hot keys.\n\n" +
            "The tree manages node memory through user-supplied AllocateRoutine and FreeRoutine callbacks. CompareRoutine defines the sort order. RtlInsertElementGenericTable copies the caller's buffer into the tree node and returns a pointer to the stored copy.\n\n" +
            "Not thread-safe. Protect with a lock."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
RTL_GENERIC_COMPARE_RESULTS NTAPI CmpFn(
        PRTL_GENERIC_TABLE T, PVOID A, PVOID B) {
    ULONG a = *(PULONG)A, b = *(PULONG)B;
    if (a < b) return GenericLessThan;
    if (a > b) return GenericGreaterThan;
    return GenericEqual;
}
PVOID NTAPI AllocFn(PRTL_GENERIC_TABLE T, CLONG Sz) {
    return ExAllocatePool2(POOL_FLAG_NON_PAGED, Sz, 'SPLT');
}
VOID NTAPI FreeFn(PRTL_GENERIC_TABLE T, PVOID Buf) {
    ExFreePoolWithTag(Buf, 'SPLT');
}

RTL_GENERIC_TABLE g_Tree;
RtlInitializeGenericTable(&g_Tree, CmpFn, AllocFn, FreeFn, NULL);

ULONG val = 42; BOOLEAN isNew;
PULONG stored = (PULONG)RtlInsertElementGenericTable(
    &g_Tree, &val, sizeof(val), &isNew);

PULONG found = (PULONG)RtlLookupElementGenericTable(&g_Tree, &val);
RtlDeleteElementGenericTable(&g_Tree, &val);
ULONG cnt = RtlNumberGenericTableElements(&g_Tree);

// In-order walk (restart=TRUE resets to first element)
for (PVOID el = RtlEnumerateGenericTable(&g_Tree, TRUE);
     el != NULL;
     el = RtlEnumerateGenericTable(&g_Tree, FALSE)) {
    DbgPrint("%lu\n", *(PULONG)el);
}
        """.trimIndent())
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("AVL TREE (RTL_AVL_TABLE)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "A height-balanced BST — strict O(log n) guarantee for insert, delete, and lookup. Better worst-case than splay trees when all keys are accessed uniformly.\n\n" +
            "Same callback pattern as RTL_GENERIC_TABLE; all API names have an 'Avl' suffix. CompareRoutine returns GenericLessThan, GenericGreaterThan, or GenericEqual.\n\n" +
            "Not thread-safe. Protect with a lock."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
RTL_AVL_TABLE g_Avl;
RtlInitializeGenericTableAvl(&g_Avl, CmpFn, AllocFn, FreeFn, NULL);

ULONG val = 100; BOOLEAN isNew;
RtlInsertElementGenericTableAvl(&g_Avl, &val, sizeof(val), &isNew);

PULONG found = (PULONG)RtlLookupElementGenericTableAvl(&g_Avl, &val);
RtlDeleteElementGenericTableAvl(&g_Avl, &val);
ULONG cnt = RtlNumberGenericTableElementsAvl(&g_Avl);

// In-order walk
for (PVOID el = RtlEnumerateGenericTableAvl(&g_Avl, TRUE);
     el != NULL;
     el = RtlEnumerateGenericTableAvl(&g_Avl, FALSE)) {
    DbgPrint("%lu\n", *(PULONG)el);
}
        """.trimIndent())
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("CHOOSING THE RIGHT STRUCTURE")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
Structure            Ordered  Thread-Safe  Best For
─────────────────────────────────────────────────────────
LIST_ENTRY           No       No (*)       FIFO queues, all OS versions
SINGLE_LIST_ENTRY    No       No (*)       Simple LIFO, lock required
SLIST_HEADER         No       YES (LF)     Lock-free stack at any IRQL
RTL_HASH_TABLE       No       No (*)       Keyed lookup (Windows 8+)
RTL_GENERIC_TABLE    Sorted   No (*)       Sorted data, hot-key workloads
RTL_AVL_TABLE        Sorted   No (*)       Sorted data, uniform access

(*) Protect with KeAcquireSpinLock /
    KeAcquireInStackQueuedSpinLock when shared across
    threads or at IRQL > PASSIVE_LEVEL.

SLIST_HEADER is the only lock-free option — safe to push/pop
from DPCs and ISRs without additional synchronization.
        """.trimIndent())
        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
