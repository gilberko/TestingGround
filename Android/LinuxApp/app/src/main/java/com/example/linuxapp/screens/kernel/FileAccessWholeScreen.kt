package com.example.linuxapp.screens.kernel

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FileAccessWholeScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "File Access: The Whole Picture",
                        color = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontSize = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item {
                SectionCard(title = "Starting Point: write() in User Mode") {
                    BodyText("Everything starts with a simple call in your application:")
                    CodeBlock("""ssize_t written = write(fd, buffer, length);""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("write() is a thin libc wrapper around the write syscall. Internally it executes the syscall instruction (on x86-64), which causes the CPU to:")
                    CodeBlock("""1. Save the current user-mode register state
2. Switch privilege level: Ring 3 → Ring 0 (kernel mode)
3. Jump to the kernel's syscall entry point
4. Dispatch to sys_write() based on the syscall number""")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("The user-space buffer is still in user virtual memory — the kernel can read it but must be careful not to fault (it uses copy_from_user() for safe access).")
                }
            }
            item {
                SectionCard(title = "The VFS Layer") {
                    BodyText("sys_write() in the kernel does not talk to any specific filesystem. Instead it goes through the Virtual File System (VFS) — an abstraction layer that provides a uniform interface over ext4, btrfs, tmpfs, NFS, and any other filesystem.")
                    CodeBlock("""sys_write()
  → ksys_write()
    → vfs_write()
      → file->f_op->write_iter()   // filesystem's implementation""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Key VFS data structures:")
                    CodeBlock("""struct file    — open file instance (one per open() call)
struct inode   — the file itself (metadata, permissions, size)
struct dentry  — directory entry (name → inode mapping)
struct address_space — page cache for this inode's data""")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("VFS checks permissions, updates access timestamps, and routes the call to the right filesystem based on the mount point.")
                }
            }
            item {
                SectionCard(title = "The Page Cache") {
                    BodyText("Before anything reaches the disk, the kernel writes data into the page cache — a region of RAM that holds copies of file data. write() typically completes very quickly because it only needs to copy data into cached pages and mark them dirty.")
                    CodeBlock("""User buffer → page cache (dirty pages marked)
write() returns to user space immediately
Kernel will flush dirty pages to disk later (pdflush/writeback)""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("fsync() or O_SYNC force dirty pages to disk synchronously — write() does not return until the data is on stable storage.")
                }
            }
            item {
                SectionCard(title = "The File System Layer") {
                    BodyText("When dirty pages are flushed (by the writeback daemon or fsync()), the filesystem (e.g. ext4) takes over. It must:")
                    CodeBlock("""1. Translate the file offset → logical block number
   (using the inode's block map or extent tree)
2. Determine which physical block on the device to write
3. If journaling: write to the journal first (ext4 default)
4. Construct one or more bio (block I/O) structures
   describing the data and destination block(s)""")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("struct bio is the kernel's fundamental unit of block I/O. It describes a set of pages to transfer and their destination on a block device.")
                }
            }
            item {
                SectionCard(title = "The Block Layer") {
                    BodyText("The bio is handed to the block layer via submit_bio(). The block layer sits between filesystems and device drivers. It handles:")
                    CodeBlock("""submit_bio()
  → block layer receives the bio
  → I/O scheduler (mq-deadline, kyber, none, BFQ)
     merges and reorders requests for efficiency
  → blk_mq (multi-queue block layer)
     places request in the driver's hardware queue""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Plugging: the block layer can hold (plug) requests briefly to batch multiple bios together before sending to the driver — reduces per-request overhead.")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("The request is now in the driver's queue. The CPU is free to continue executing other processes. The calling process is asleep in a wait queue if it used blocking I/O.")
                }
            }
            item {
                SectionCard(title = "The Device Driver") {
                    BodyText("The device driver (e.g. NVMe, SATA AHCI, virtio-blk) pulls requests from the blk_mq queue and programs the hardware to perform the transfer:")
                    CodeBlock("""1. Driver picks request from its hardware queue
2. Sets up a DMA descriptor:
     - Source: physical address of the page cache page
     - Destination: LBA (Logical Block Address) on the device
     - Length: number of sectors
3. Writes the command to the device's command register
   (memory-mapped I/O or I/O port)
4. Returns — CPU does NOT wait""")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("The hardware controller now handles the actual data transfer autonomously via DMA. The driver does not poll — it returns control to the block layer and eventually to the scheduler.")
                }
            }
            item {
                SectionCard(title = "The Hardware & DMA") {
                    BodyText("DMA (Direct Memory Access) allows the disk controller to read data directly from RAM and write it to the storage media without CPU involvement:")
                    CodeBlock("""CPU programs DMA descriptor → writes NVMe/SATA command
Hardware controller reads DMA descriptor
Controller reads data from RAM (via PCIe bus)
Controller programs flash cells / spins magnetic disk
Data is physically written to stable storage
Controller raises an interrupt line (IRQ)""")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("During all of this the CPU is completely free — it runs other processes, handles other interrupts, does other work. The calling process is still asleep, waiting.")
                }
            }
            item {
                SectionCard(title = "The Interrupt Handler") {
                    BodyText("When the hardware is done, it raises an interrupt (IRQ). The CPU stops whatever it was doing and runs the interrupt handler registered by the driver:")
                    CodeBlock("""Top half (ISR — runs immediately, very fast):
  - Acknowledge interrupt to the hardware
  - Read status register (success / error)
  - Schedule bottom half via tasklet or workqueue
  - Return (CPU resumes interrupted task)

Bottom half (runs soon after, more work allowed):
  - Mark the request as complete
  - Call blk_mq_complete_request()
  - Update I/O accounting statistics""")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("The split into top/bottom halves keeps interrupt latency low. The top half must be extremely fast — it runs with interrupts disabled on that CPU.")
                }
            }
            item {
                SectionCard(title = "Completing the Request — Waking the Process") {
                    BodyText("After blk_mq_complete_request(), the completion propagates back up the stack:")
                    CodeBlock("""blk_mq_complete_request()
  → bio completion callback
    → filesystem's end_io handler
      → mark pages clean (no longer dirty)
        → wake up process in the wait queue""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The process that called write() with O_SYNC or fsync() was sleeping in a wait_queue. The kernel calls wake_up() on it, the scheduler marks it as runnable, and at the next scheduling opportunity it resumes execution.")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("write() returns the number of bytes written. The whole journey — user space → VFS → FS → block layer → driver → DMA → interrupt → wake — is invisible to the application.")
                }
            }
            item {
                SectionCard(title = "Blocking vs Async Paths") {
                    BodyText("Blocking (default): the calling thread sleeps from the moment it needs to wait for I/O until the driver signals completion. Simple to program, but one thread = one outstanding operation.")
                    CodeBlock("""// Blocking write with fsync — thread sleeps until disk confirms
write(fd, buf, n);
fsync(fd);            // process sleeps here until drive IRQ""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Async (O_NONBLOCK + io_uring / AIO): no sleeping. The application submits operations to the kernel's submission queue and continues. The kernel processes them independently and posts results to the completion queue.")
                    CodeBlock("""// io_uring async write — no blocking at all
io_uring_prep_write(sqe, fd, buf, n, offset);
io_uring_submit(&ring);
// ... do other work ...
io_uring_wait_cqe(&ring, &cqe);  // collect result later""")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("In both cases, the journey through VFS → FS → block layer → driver → DMA → interrupt is identical. The only difference is whether the calling thread waits or continues running while it happens.")
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
