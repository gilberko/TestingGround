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
fun MdlScreen(navController: NavController) {
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
            text = "MDL",
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

        // WHAT IS IT
        SectionHeader("WHAT IS IT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "_MDL (Memory Descriptor List) describes a range of virtual memory in terms of " +
            "its underlying physical pages. It allows kernel code to safely access a buffer " +
            "regardless of which process is currently scheduled or what virtual address space " +
            "is active."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // WHY NEEDED
        SectionHeader("WHY IS IT NEEDED")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "User-mode buffers exist in process virtual address space, which is unique per " +
            "process and only valid while that process is running.\n\n" +
            "At DISPATCH_LEVEL — the IRQL of DPCs, completion routines, and ISRs — the " +
            "processor may be running in the context of any arbitrary process. The original " +
            "user's VA space is inaccessible.\n\n" +
            "An MDL solves this by:\n" +
            "  1. Recording the physical page frame numbers (PFNs) behind the buffer\n" +
            "  2. Locking those pages in RAM (preventing the pager from evicting them)\n" +
            "  3. Allowing a new mapping in system address space (always accessible) to " +
            "be created over the same physical pages"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // USAGE FLOW
        SectionHeader("USAGE FLOW")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// 1. Allocate the MDL for a virtual address range\n" +
            "PMDL mdl = IoAllocateMdl(\n" +
            "    userBuffer,    // virtual address\n" +
            "    length,        // byte count\n" +
            "    FALSE, FALSE,  // SecondaryBuffer, ChargeQuota\n" +
            "    NULL           // Irp (attach to IRP, or NULL)\n" +
            ");\n\n" +
            "// 2. Lock the physical pages (verify access rights)\n" +
            "__try {\n" +
            "    MmProbeAndLockPages(mdl, UserMode, IoReadAccess);\n" +
            "} __except(EXCEPTION_EXECUTE_HANDLER) { ... }\n\n" +
            "// 3. Map into system (kernel) address space\n" +
            "void* sysAddr = MmGetSystemAddressForMdlSafe(\n" +
            "    mdl, NormalPagePriority);\n\n" +
            "// 4. Access the buffer via sysAddr safely at any IRQL\n\n" +
            "// 5. Cleanup\n" +
            "MmUnlockPages(mdl);\n" +
            "IoFreeMdl(mdl);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "MmProbeAndLockPages verifies that the caller has the requested access rights " +
            "to the virtual range and locks the underlying physical pages. After this call, " +
            "the pages will not be paged out.\n\n" +
            "MmGetSystemAddressForMdlSafe creates a kernel virtual mapping over the locked " +
            "pages and returns a pointer that is valid from any process context, until " +
            "MmUnlockPages is called."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // IN DIRECT I/O
        SectionHeader("IN DIRECT I/O")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "When a DEVICE_OBJECT has the DO_DIRECT_IO flag set in its Flags field, the " +
            "I/O Manager automatically creates and locks an MDL for the user's buffer on " +
            "every read or write IRP, and stores the MDL in Irp->MdlAddress.\n\n" +
            "The driver just calls:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "PVOID buf = MmGetSystemAddressForMdlSafe(\n" +
            "    Irp->MdlAddress, NormalPagePriority);\n" +
            "ULONG len = IoGetCurrentIrpStackLocation(Irp)\n" +
            "                ->Parameters.Read.Length;"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The I/O Manager handles MmProbeAndLockPages and cleanup. This is the recommended " +
            "approach for most device drivers."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // MDL STRUCTURE
        SectionHeader("MDL STRUCTURE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The MDL header fields:\n\n" +
            "  Next          — pointer to the next MDL (for chaining multiple MDLs)\n" +
            "  Size          — total size of the MDL structure, including the PFN array\n" +
            "  MdlFlags      — MDL_PAGES_LOCKED, MDL_MAPPED_TO_SYSTEM_VA,\n" +
            "                  MDL_SOURCE_IS_NONPAGED_POOL, MDL_PARTIAL, etc.\n" +
            "  StartVa       — page-aligned start address of the described VA range\n" +
            "  ByteOffset    — byte offset of the buffer's start within the first page\n" +
            "  ByteCount     — total byte count of the buffer\n\n" +
            "Immediately following the MDL header in memory is a variable-length array of " +
            "PFN_NUMBER (physical frame numbers) — one entry per physical page covered by " +
            "the buffer. The MmGetMdlPfnArray(mdl) macro returns a pointer to this array."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
