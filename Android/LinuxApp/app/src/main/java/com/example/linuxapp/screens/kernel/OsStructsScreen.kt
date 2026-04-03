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
fun OsStructsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "OS Structs",
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
                SectionCard(title = "Processes and Threads in Linux") {
                    BodyText("In most operating systems, processes and threads are distinct concepts. In Linux, they are not — at the kernel level, both are represented by the same structure: struct task_struct.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The difference lies in what they share:")
                    CodeBlock(
                        """Process (fork):
  Created by fork() or clone() without sharing flags.
  Gets its own copy-on-write address space, file
  descriptor table, and signal handlers.
  Each process has a unique PID.

Thread (pthread / clone with sharing):
  Created by clone() with CLONE_VM | CLONE_FILES |
  CLONE_SIGHAND | CLONE_THREAD flags.
  Shares the address space, fd table, and signal
  handlers with the creating task.
  Has its own PID but shares the TGID (Thread Group ID)
  with the main thread."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("From the kernel scheduler's perspective, a thread is just another task_struct that happens to share an mm_struct with siblings. pthread_create() in glibc ultimately calls clone().")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Key IDs:")
                    CodeBlock(
                        """PID  (Process ID)      — unique ID for each task_struct
TGID (Thread Group ID) — PID of the main thread; same for
                         all threads in a process.
                         getpid() returns TGID.
                         gettid() returns PID.
PGID (Process Group ID) — for job control (kill a group)
SID  (Session ID)       — for terminal sessions"""
                    )
                }
            }
            item {
                SectionCard(title = "task_struct") {
                    BodyText("task_struct is the central kernel data structure describing a runnable entity. Defined in <linux/sched.h>, it is several kilobytes in size. Key fields:")
                    CodeBlock(
                        """struct task_struct {
    /* State */
    unsigned int   __state;  /* TASK_RUNNING, TASK_INTERRUPTIBLE,
                                TASK_UNINTERRUPTIBLE, TASK_STOPPED,
                                TASK_ZOMBIE, EXIT_DEAD ... */

    /* Identity */
    pid_t          pid;      /* this task's unique ID */
    pid_t          tgid;     /* thread group ID (= main thread's pid) */
    char           comm[TASK_COMM_LEN]; /* process name (16 chars) */

    /* Relationships */
    struct task_struct *real_parent; /* forking parent */
    struct task_struct *parent;      /* current parent (may differ) */
    struct list_head    children;    /* list of child tasks */
    struct list_head    sibling;     /* linkage in parent's children */

    /* Memory */
    struct mm_struct   *mm;          /* address space (NULL = kthread) */
    struct mm_struct   *active_mm;   /* mm currently in use */

    /* Files & FS */
    struct files_struct *files;      /* open file descriptor table */
    struct fs_struct    *fs;         /* cwd, root directory */

    /* Signals */
    struct signal_struct   *signal;  /* shared signal state */
    struct sighand_struct  *sighand; /* signal handlers */
    sigset_t                blocked; /* blocked signal mask */

    /* Scheduling */
    int            prio;         /* effective (dynamic) priority */
    int            static_prio;  /* nice-based static priority */
    unsigned int   policy;       /* SCHED_NORMAL / SCHED_FIFO / SCHED_RR */
    struct sched_entity se;      /* CFS scheduling entity */

    /* Credentials */
    const struct cred *cred;     /* UID, GID, capabilities */
};"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Accessing the current task from kernel code:")
                    CodeBlock(
                        """#include <linux/sched.h>

/* 'current' is a per-CPU pointer to the running task_struct */
printk(KERN_INFO "PID=%d Name=%s\n",
       current->pid, current->comm);

/* Check if this is a kernel thread (no user address space): */
if (current->mm == NULL)
    pr_info("running in kernel thread context\n");"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Iterating all tasks:")
                    CodeBlock(
                        """#include <linux/sched/signal.h>

struct task_struct *task;
rcu_read_lock();
for_each_process(task) {
    pr_info("PID=%d TGID=%d Name=%s\n",
            task->pid, task->tgid, task->comm);
}
rcu_read_unlock();

/* Iterate threads within a process: */
struct task_struct *thread;
for_each_thread(task, thread) {
    pr_info("  thread PID=%d\n", thread->pid);
}"""
                    )
                }
            }
            item {
                SectionCard(title = "mm_struct — Process Address Space") {
                    BodyText("mm_struct describes the full virtual address space of a process. Each process has its own mm_struct; all threads in a process share the same one.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Key fields:")
                    CodeBlock(
                        """struct mm_struct {
    /* Segment boundaries */
    unsigned long start_code,  end_code;   /* text (.text) */
    unsigned long start_data,  end_data;   /* init data */
    unsigned long start_brk,   brk;        /* heap (brk grows up) */
    unsigned long start_stack;             /* stack top */
    unsigned long mmap_base;               /* base of mmap region */

    /* Page tables */
    pgd_t         *pgd;         /* top-level page directory */

    /* VMA tree (kernel 6.1+: maple tree; older: rb_root) */
    struct maple_tree mm_mt;    /* all VMAs for this process */

    /* Reference counts */
    atomic_t mm_users;  /* tasks using this mm */
    atomic_t mm_count;  /* struct references (includes lazy TLBs) */

    /* Stats */
    unsigned long total_vm;   /* total pages mapped */
    unsigned long locked_vm;  /* mlock-ed pages */
};"""
                    )
                }
            }
            item {
                SectionCard(title = "vm_area_struct — Memory Mappings") {
                    BodyText("Each contiguous region of virtual memory in a process is described by a vm_area_struct (VMA). All VMAs for a process are stored in mm_struct's VMA tree.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Key fields:")
                    CodeBlock(
                        """struct vm_area_struct {
    unsigned long vm_start;  /* start virtual address (inclusive) */
    unsigned long vm_end;    /* end virtual address (exclusive) */

    unsigned long vm_flags;  /* permissions and type:
        VM_READ    — pages are readable
        VM_WRITE   — pages are writable
        VM_EXEC    — pages are executable
        VM_SHARED  — mapping is shared (not private COW)
        VM_GROWSDOWN — stack (grows downward)
        VM_IO      — I/O mapping (e.g. device memory)
        VM_LOCKED  — pages are mlock-ed in RAM */

    struct file *vm_file;    /* backing file, or NULL for anonymous */
    unsigned long vm_pgoff;  /* offset in file (in pages) */

    struct vm_operations_struct *vm_ops; /* fault, open, close */
};"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("How mmap() creates a VMA:")
                    CodeBlock(
                        """/* User space calls: */
void *addr = mmap(NULL, 4096, PROT_READ|PROT_WRITE,
                  MAP_PRIVATE|MAP_ANONYMOUS, -1, 0);

/* The kernel:
   1. Calls do_mmap() → finds a free virtual address range
   2. Allocates a new vm_area_struct
   3. Sets vm_start/vm_end to the chosen range
   4. Sets vm_flags from PROT_* and MAP_* flags
   5. If a file was passed: sets vm_file, vm_pgoff
   6. Inserts the VMA into mm->mm_mt (maple tree)
   7. Returns the start address to user space

   Pages are NOT allocated yet — they are demand-paged
   in on first access (page fault → alloc page → map PTE). */"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Viewing VMAs from user space:")
                    CodeBlock(
                        """cat /proc/<pid>/maps
# Output format:
# start-end  perms  offset  dev  inode  pathname
# 7f8a000000-7f8a001000 rw-p 00000000 00:00 0  [heap]
# 7fff000000-7fff001000 rwxp 00000000 00:00 0  [stack]

cat /proc/<pid>/smaps   # detailed stats per VMA"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Walking VMAs from a kernel module (kernel 6.1+):")
                    CodeBlock(
                        """struct vm_area_struct *vma;
unsigned long addr;

mmap_read_lock(task->mm);
mt_for_each(&task->mm->mm_mt, vma, addr, ULONG_MAX) {
    pr_info("VMA: %lx-%lx flags=%lx\n",
            vma->vm_start, vma->vm_end, vma->vm_flags);
}
mmap_read_unlock(task->mm);"""
                    )
                }
            }
            item {
                SectionCard(title = "sk_buff — Socket Buffer") {
                    BodyText("sk_buff (socket buffer) is the fundamental data structure for network packets in the Linux kernel. Every packet flowing through the network stack — from a NIC driver up to a socket, and back down — is represented as an sk_buff.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The buffer is a contiguous memory region managed by four pointers:")
                    CodeBlock(
                        """  head                              end
   |                                |
   +----+----------+----------+-----+
   |headroom| data (payload)  |tailroom|
   +----+----------+----------+-----+
        |                     |
       data                  tail

head  — start of the allocated buffer
data  — start of the current payload
tail  — end of the current payload
end   — end of the allocated buffer

skb->len = tail - data  (current payload length)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Key metadata fields:")
                    CodeBlock(
                        """struct sk_buff {
    /* Pointers */
    unsigned char   *head, *data;
    sk_buff_data_t   tail, end;   /* offsets on 64-bit */

    /* Length */
    unsigned int     len;         /* payload length     */
    unsigned int     data_len;    /* paged data length  */

    /* Metadata */
    __be16           protocol;    /* ETH_P_IP, ETH_P_IPV6 ... */
    struct net_device *dev;       /* receiving/sending device */
    struct sock      *sk;         /* owning socket, or NULL   */

    /* Checksums, priority, timestamps, ... */
};"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Allocation and payload helpers:")
                    CodeBlock(
                        """/* Allocate a new skb */
struct sk_buff *skb = alloc_skb(size, GFP_KERNEL);
dev_alloc_skb(size);  /* same but with NET_SKB_PAD headroom */

/* Reserve headroom before filling payload
   (call before any put/push) */
skb_reserve(skb, NET_IP_ALIGN);

/* Extend the tail — returns pointer to the new area */
void *p = skb_put(skb, payload_len);
memcpy(p, data, payload_len);

/* Prepend a header — extends toward head */
struct iphdr *iph = skb_push(skb, sizeof(*iph));

/* Consume a header — move data pointer forward */
skb_pull(skb, sizeof(struct ethhdr));

/* Access current payload */
struct iphdr *iph = (struct iphdr *)skb->data;"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Where you encounter sk_buff:")
                    CodeBlock(
                        """/* Net driver TX — called by the stack to send a packet */
netdev_tx_t my_ndo_start_xmit(struct sk_buff *skb,
                               struct net_device *dev)
{
    pr_info("TX: len=%u proto=0x%04x\n",
            skb->len, ntohs(skb->protocol));
    /* hand skb->data / skb->len to hardware DMA ... */
    dev_kfree_skb(skb);
    return NETDEV_TX_OK;
}

/* Netfilter hook — inspect/drop/modify packets */
static unsigned int my_hook(void *priv,
    struct sk_buff *skb,
    const struct nf_hook_state *state)
{
    struct iphdr *iph = ip_hdr(skb); /* skb->data helper */
    pr_info("src=%pI4 len=%u\n", &iph->saddr, skb->len);
    return NF_ACCEPT;
}"""
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
