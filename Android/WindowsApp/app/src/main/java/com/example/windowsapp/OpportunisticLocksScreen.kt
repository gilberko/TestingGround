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
fun OpportunisticLocksScreen(navController: NavController) {
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
            text = "OPPORTUNISTIC LOCKS",
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

        SectionHeader("WHAT ARE OPPORTUNISTIC LOCKS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("An opportunistic lock (oplock) is a lock granted to a file opener that allows it to cache file data locally — reads, writes, or even the file's open state. The lock is \"opportunistic\" because it is not mandatory: the server grants it when conditions allow, and breaks it when another incompatible access arrives.\n\nWhen a client holds an oplock it can:\n• Buffer writes locally (flush to disk/server lazily)\n• Cache read data without re-fetching\n• Defer open/close round-trips to the server\n\nThis dramatically reduces I/O traffic, especially over the network.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHICH FILE SYSTEMS SUPPORT OPLOCKS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("NTFS: YES — full oplock support, even for local (non-network) access. Used internally by the Cache Manager and for local applications.\n\nReFS: YES — similar to NTFS.\n\nSMB / CIFS (network): YES — oplocks are a core part of the SMB protocol. SMB 1.0 supports Level1/Level2/Batch/Filter. SMB 2.0+ introduces Leases (a more granular oplock superset).\n\nNFS: Similar concept via Delegations (RFC 3530 / NFSv4). Semantically analogous but a different protocol mechanism.\n\nFAT / exFAT: NO — no oplock support.\n\nCDFS / UDF: NO — read-only file systems, no locking needed.\n\nNPFS (Named Pipes): NO — pipe semantics are different.\n\nOplock support is a property of the file system driver, not the storage medium.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("PURPOSE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("For local access:\n• The Windows Cache Manager uses oplocks so it knows when to invalidate a file's cache — if no one else has the file open, it can cache aggressively.\n\nFor network access (primary use case):\n• Without oplocks, every read requires a network round-trip; every write must go to the server before the caller returns.\n• With an oplock, the client caches reads locally and batches writes — only flushing when the oplock is broken.\n• Critical for SMB file server performance: Word, Excel, Visual Studio all rely on oplocks for acceptable responsiveness over a LAN.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("OPLOCK TYPES")
        Spacer(modifier = Modifier.height(8.dp))

        BodyText("LEVEL 1 — Exclusive")
        Spacer(modifier = Modifier.height(4.dp))
        BodyText("Granted when no other handles exist on the file.\nClient can cache: reads AND writes (dirty write cache).\nBroken when: any other process opens the file, a byte-range lock is requested, or a write arrives from another client.\nBreak result: downgraded to Level 2 (if new opener is read-only) or fully broken.\nScenario: Microsoft Word opens a .docx. Gets Level 1. Buffers all edits in memory. When another user opens the same file, Word receives a break — it flushes its buffered writes to the server, acknowledges, and the lock downgrades to Level 2.")

        Spacer(modifier = Modifier.height(12.dp))

        BodyText("LEVEL 2 — Shared (Read)")
        Spacer(modifier = Modifier.height(4.dp))
        BodyText("Multiple clients can hold Level 2 simultaneously.\nClient can cache: reads only (no write buffering).\nBroken when: any write to the file by any client.\nBreak result: fully broken; no downgrade possible from Level 2.\nScenario: Three processes load the same DLL from a network share. Each gets a Level 2 oplock and caches the pages locally. If a build system tries to overwrite the DLL, all three Level 2 oplocks are broken.")

        Spacer(modifier = Modifier.height(12.dp))

        BodyText("BATCH — Keep-Open Cache")
        Spacer(modifier = Modifier.height(4.dp))
        BodyText("Like Level 1, but additionally: when the application \"closes\" the file, the client does NOT actually send a close to the server — it keeps the handle open speculatively, in case the app reopens the file shortly.\nBroken when: another client tries to open the file.\nScenario: cmd.exe runs a batch script that opens the same .txt log file 200 times. With a Batch oplock, the first open is a real network open; all subsequent opens/closes are handled locally. Only when another process tries to open the file does the client send the pending close and acknowledge.")

        Spacer(modifier = Modifier.height(12.dp))

        BodyText("FILTER — Scan Before Access")
        Spacer(modifier = Modifier.height(4.dp))
        BodyText("Designed for file system filters (e.g. antivirus) that need to inspect a file before allowing write access, without blocking concurrent read-only openers.\nClient can cache: reads only.\nKey difference from Level 1: new read-only openers are NOT blocked while the filter holds its oplock.\nBroken when: a new opener requests write access, or a flush is issued.\nScenario: An AV engine opens a downloaded file and requests a Filter oplock. It scans the file. Meanwhile, the original downloader (read-only) can still open it. When a process tries to write or execute the file, the filter oplock breaks, giving the scanner a chance to block the operation before acknowledging.")

        Spacer(modifier = Modifier.height(12.dp))

        BodyText("WINDOWS VISTA+ — Request Oplock (Leases)")
        Spacer(modifier = Modifier.height(4.dp))
        BodyText("FSCTL_REQUEST_OPLOCK with a bitmask of cache capabilities:\n• OPLOCK_LEVEL_CACHE_READ (R) — like Level 2\n• OPLOCK_LEVEL_CACHE_WRITE (W) — write buffering\n• OPLOCK_LEVEL_CACHE_HANDLE (H) — defer close\n• RW — like Level 1\n• RH — read + deferred close\n• RWH — like Batch (most powerful)\n\nSMB 2.0+ maps these directly to Lease types, enabling more granular sharing scenarios than the original four oplock types.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("OPLOCK STATES")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "NO OPLOCK\n" +
            "  ↓  (client requests oplock)\n" +
            "GRANTED\n" +
            "  Client caches reads/writes freely\n" +
            "  ↓  (incompatible access arrives)\n" +
            "BREAKING\n" +
            "  Server sends break notification\n" +
            "  Client has limited time to respond\n" +
            "  ↓  (client flushes + acknowledges)\n" +
            "DOWNGRADED  ← Level1→Level2 (if possible)\n" +
            "  or\n" +
            "NO OPLOCK   ← fully broken"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The BREAKING state is the critical window. The incompatible operation is held pending until either:\n• The client acknowledges the break (operation proceeds)\n• A break timeout expires (server forces the break)\n\nIf the holder crashes without acknowledging, the server times out and proceeds.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHEN IS AN OPLOCK BROKEN")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("An oplock is broken when a new access request is incompatible with the current oplock type:\n\n• Another process opens the file (breaks Level 1 and Batch; read-only opener may allow downgrade to Level 2)\n• Any write to the file (always breaks Level 2; breaks Level 1 if from another client)\n• A byte-range lock (LockFile / LockFileEx) is requested on the file\n• The file is truncated or size-extended by another opener\n• The file is renamed or deleted (breaks all types)\n• On SMB: the client's network session is disconnected\n\nOplock keys (Vista+): a token embedded in a create request that lets the same client open the same file multiple times without breaking its own oplock — important for multi-threaded applications that share a file across threads.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHAT HAPPENS DURING A BREAK")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("1. The incompatible I/O request arrives at the server/FS\n2. Server sends an oplock break notification to the holder (OPLOCK_BREAK to the holder's open handle)\n3. Holder receives the notification asynchronously (its pending DeviceIoControl returns)\n4. Holder must:\n   a. Flush dirty write cache to the server/disk\n   b. Discard read cache (for full breaks)\n   c. Close speculatively-kept handles (for Batch breaks)\n   d. Send acknowledgment: FSCTL_OPLOCK_BREAK_ACKNOWLEDGE\n5. The pending I/O request then proceeds\n\nIf the holder does not acknowledge within the timeout, the server proceeds anyway and the holder's cached data may be stale or lost.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WINDOWS API")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("User mode — via DeviceIoControl with FSCTL codes:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Request (legacy)\n" +
            "FSCTL_REQUEST_OPLOCK_LEVEL_1\n" +
            "FSCTL_REQUEST_OPLOCK_LEVEL_2\n" +
            "FSCTL_REQUEST_BATCH_OPLOCK\n" +
            "FSCTL_REQUEST_FILTER_OPLOCK\n\n" +
            "// Request (Vista+, unified)\n" +
            "FSCTL_REQUEST_OPLOCK\n" +
            "  // with REQUEST_OPLOCK_INPUT_BUFFER\n" +
            "  // StructureVersion, Flags,\n" +
            "  // RequestedOplockLevel (bitmask)\n\n" +
            "// Acknowledge break\n" +
            "FSCTL_OPLOCK_BREAK_ACKNOWLEDGE\n" +
            "FSCTL_OPLOCK_BREAK_ACK_NO_2\n" +
            "  // ACK_NO_2: break to none, not Level2"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Kernel mode — file system filter drivers:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "FsRtlInitializeOplock\n" +
            "FsRtlOplockFsctrl      // handle FSCTL\n" +
            "FsRtlCheckOplock       // check on open\n" +
            "FsRtlOplockBreakToNone // force break\n" +
            "FsRtlRequestOplockEx   // Windows 7+\n" +
            "FsRtlOplockIsSharedRequest"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The typical pattern: open the file with FILE_FLAG_OVERLAPPED, call DeviceIoControl with FSCTL_REQUEST_OPLOCK asynchronously, then do normal I/O. When the async DeviceIoControl completes, it means the break has been requested — flush, then call DeviceIoControl again with FSCTL_OPLOCK_BREAK_ACKNOWLEDGE.")

        Spacer(modifier = Modifier.height(32.dp))
        HackerButton("< BACK") { navController.popBackStack() }
    }
}
