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
fun PagingIOScreen(navController: NavController) {
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
            text = "PAGING I/O",
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

        SectionHeader("WHAT IS PAGING I/O?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Paging I/O is disk I/O initiated by the Windows Memory Manager (not by user applications) to satisfy memory demands. When the kernel needs to bring a page of data from disk into RAM, or flush a dirty page back to disk, it issues a special IRP with the IRP_PAGING_IO flag set in IrpFlags.\n\n" +
            "This flag tells the filesystem driver and minifilters: this I/O is coming from the kernel's memory subsystem, not from a user-mode ReadFile/WriteFile call. The handling rules are different — the driver must not acquire certain resources that could cause deadlock, and the semantics around locking and completion are stricter.\n\n" +
            "Paging I/O is fundamental to how Windows works. Virtually every file access ultimately involves paging I/O under the hood, even when user code calls ReadFile."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("THE VIRTUAL MEMORY AND FILE CACHE CONNECTION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Windows uses a unified memory manager and cache model. File data is not cached separately from virtual memory — they share the same mechanism: Section objects.\n\n" +
            "When a file is opened and read, the Cache Manager creates a Section object (a memory-mapped view) over the file. The file's data is mapped into kernel address space as pages in the Virtual Address Descriptor (VAD) tree.\n\n" +
            "Reading file data = accessing those mapped pages. If the page is already in RAM (page frame), it's a direct memory read — no disk I/O. If the page is not in RAM, a page fault occurs, and the Memory Manager must bring it from disk.\n\n" +
            "This design means there is one copy of each file page in RAM, shared between all processes that have mapped it. The filesystem cache and virtual memory are unified, not separate systems."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("PAGE FAULT FLOW — READING")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Here is the full flow when a file-backed page is not in RAM:\n\n" +
            "1. A thread accesses a virtual address that is mapped to file data\n" +
            "2. The CPU finds no valid PTE for that address → hardware page fault\n" +
            "3. The CPU jumps to the kernel page fault handler: KiPageFault → MmAccessFault\n" +
            "4. MmAccessFault inspects the VAD: the address is file-backed (demand-zero pages or a file-mapped Section)\n" +
            "5. MmPageFaultFileIO is called — it allocates a free page frame and constructs an IRP\n" +
            "6. The IRP has IRP_MJ_READ as the major function, with IRP_PAGING_IO | IRP_NOCACHE set in IrpFlags\n" +
            "7. The IRP is sent down the file object's device stack — through minifilters (if any) to the filesystem driver\n" +
            "8. The filesystem driver reads the data from disk into the allocated page frame\n" +
            "9. MmAccessFault updates the PTE to map the virtual address to the new page frame\n" +
            "10. The faulting thread resumes as if nothing happened\n\n" +
            "The thread that caused the page fault is suspended during steps 3-9 (it is in a waiting state). The entire read happens transparently at the hardware fault level, invisible to the user-mode code that triggered it."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("EXAMPLE: FILE READ VIA ReadFile")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Consider: a user-mode program calls ReadFile on a text file it opened.\n\n" +
            "User-mode view:\n" +
            "  ReadFile(hFile, buffer, size, &bytesRead, NULL)\n" +
            "  → The call returns with the data in buffer.\n\n" +
            "What actually happened:\n" +
            "  1. ReadFile → NtReadFile (syscall) → IRP_MJ_READ sent to the filesystem\n" +
            "  2. The filesystem (e.g., NTFS) calls CcCopyRead to read from the cache\n" +
            "  3. CcCopyRead checks if the file's pages are in the cache (mapped pages in RAM)\n" +
            "  4. Cache miss: the pages are not yet in RAM\n" +
            "  5. CcCopyRead signals the Memory Manager to populate the pages\n" +
            "  6. The Memory Manager issues a paging I/O IRP (IRP_PAGING_IO | IRP_NOCACHE) to read from disk\n" +
            "  7. The disk read completes; pages are now in RAM\n" +
            "  8. CcCopyRead copies the data from the cache pages into the user's buffer\n" +
            "  9. NtReadFile returns to user mode; ReadFile returns\n\n" +
            "Who initiated what:\n" +
            "  - The user initiated ReadFile (a normal I/O IRP_MJ_READ)\n" +
            "  - The actual disk transfer was a kernel-initiated paging I/O IRP issued by the Cache Manager\n" +
            "  - User mode never directly issues a paging I/O IRP\n\n" +
            "On the second ReadFile call to the same data, the pages are already in RAM → CcCopyRead is a pure memory copy, no disk I/O at all."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("PAGE FAULT FLOW — WRITING / DIRTY PAGE FLUSH")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "When data is written to a file-backed page:\n\n" +
            "Via WriteFile:\n" +
            "  1. WriteFile → NtWriteFile → IRP_MJ_WRITE to filesystem\n" +
            "  2. NTFS calls CcCopyWrite — copies user data into the cache pages\n" +
            "  3. The cache pages are marked dirty (the PTE dirty bit is set)\n" +
            "  4. WriteFile returns to user mode immediately (lazy write — data is in cache, not yet on disk)\n\n" +
            "Via memory-mapped write (MapViewOfFile + write through pointer):\n" +
            "  1. The write goes directly to the mapped page in cache\n" +
            "  2. Page is marked dirty by the hardware (dirty PTE bit)\n\n" +
            "Dirty page writeback:\n" +
            "  The kernel runs two system threads for this:\n" +
            "  - Modified Page Writer (MiModifiedPageWriter): writes dirty private pages to the paging file\n" +
            "  - Mapped Page Writer (MiMappedPageWriter): writes dirty file-backed pages back to their file\n\n" +
            "  When the Mapped Page Writer decides to flush a dirty file page, it issues an IRP_MJ_WRITE with IRP_PAGING_IO set to the filesystem. The filesystem writes the page data to disk at the file's correct offset.\n\n" +
            "This writeback is asynchronous and kernel-initiated. The user-mode program that originally wrote the data may have returned from WriteFile long before the data actually hits the disk. Calling FlushFileBuffers forces a synchronous flush."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("IS PAGING I/O USER OR KERNEL INITIATED?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Summary:\n\n" +
            "User-mode initiates: ReadFile, WriteFile, MapViewOfFile, or any access to a file-backed mapped region.\n\n" +
            "Kernel initiates (as paging I/O): the actual disk transfer IRPs that populate the cache on a miss, and the dirty-page writebacks that flush modified data to disk.\n\n" +
            "User mode NEVER directly issues a paging I/O IRP. Paging I/O IRPs always originate in the kernel — specifically in the Memory Manager (MmAccessFault, MiModifiedPageWriter, MiMappedPageWriter) or the Cache Manager (CcCopyRead triggering page population).\n\n" +
            "An important consequence: minifilters that want to monitor all disk I/O must handle paging I/O correctly. Blocking or failing a paging I/O IRP can cause a system deadlock or BSOD, because the Memory Manager may be waiting on that I/O to resolve a page fault in a critical kernel path."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("PAGING I/O IN MINIFILTERS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Minifilter pre/post callbacks receive paging I/O IRPs just like normal I/O. The PFLT_CALLBACK_DATA has IrpFlags you can check:\n\n" +
            "Key rules when handling paging I/O:\n" +
            "  - Do NOT acquire the file's FCB main resource (ExAcquireResourceExclusiveLite) — this can deadlock if the page fault happened while that resource was held\n" +
            "  - Use FsRtlEnterFileSystem / FsRtlExitFileSystem to disable APCs (required when acquiring any filesystem resource)\n" +
            "  - Do not allocate paged pool from a paging I/O callback — the allocation itself might trigger a page fault that re-enters your callback\n" +
            "  - Non-paged pool allocations are safe\n\n" +
            "Example check in a PreWrite callback:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "FLT_PREOP_CALLBACK_STATUS\n" +
            "MyPreWrite(\n" +
            "    PFLT_CALLBACK_DATA Data,\n" +
            "    PCFLT_RELATED_OBJECTS FltObjects,\n" +
            "    PVOID *CompletionContext)\n" +
            "{\n" +
            "    // Check if this is paging I/O\n" +
            "    if (Data->Iopb->IrpFlags & IRP_PAGING_IO) {\n" +
            "        // Do not acquire FCB resources here\n" +
            "        // Only safe to do lightweight work\n" +
            "        return FLT_PREOP_SUCCESS_NO_CALLBACK;\n" +
            "    }\n" +
            "\n" +
            "    // Normal (non-paging) write — safe to do full processing\n" +
            "    // ...\n" +
            "    return FLT_PREOP_SUCCESS_WITH_CALLBACK;\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHAT FLAGS DOES THE IRP CARRY?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Three key IRP flags relevant to paging I/O (from wdm.h):\n\n" +
            "IRP_PAGING_IO (0x0200)\n" +
            "  Set on all Memory Manager-initiated I/O. Tells the filesystem and minifilters this is a paging operation. Imposes strict resource-acquisition rules.\n\n" +
            "IRP_NOCACHE (0x0008)\n" +
            "  Bypass the cache — go directly to storage. Combined with IRP_PAGING_IO on page fault reads: the Memory Manager needs the raw data, not a cached copy.\n\n" +
            "IRP_SYNCHRONOUS_PAGING_IO (0x0400)\n" +
            "  The paging I/O must complete synchronously before the issuing thread can proceed (the thread is blocked on this specific I/O completion). Used for synchronous page faults.\n\n" +
            "Common combinations seen in practice:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Page fault read (Memory Manager reading from disk)\n" +
            "IrpFlags = IRP_PAGING_IO | IRP_NOCACHE | IRP_SYNCHRONOUS_PAGING_IO\n" +
            "\n" +
            "// Dirty page writeback (Mapped Page Writer)\n" +
            "IrpFlags = IRP_PAGING_IO | IRP_NOCACHE\n" +
            "\n" +
            "// Normal cached ReadFile (no paging I/O)\n" +
            "IrpFlags = 0  (or IRP_SYNCHRONOUS_API for sync ReadFile)\n" +
            "\n" +
            "// Check in a minifilter or filesystem driver:\n" +
            "BOOLEAN IsPagingIo =\n" +
            "    (Irp->Flags & IRP_PAGING_IO) != 0;\n" +
            "BOOLEAN IsNonCached =\n" +
            "    (Irp->Flags & IRP_NOCACHE) != 0;\n" +
            "// Both set = going directly to disk (no cache involvement)"
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
