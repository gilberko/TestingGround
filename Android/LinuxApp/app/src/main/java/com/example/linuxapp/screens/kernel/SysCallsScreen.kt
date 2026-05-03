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
fun SysCallsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Sys Calls",
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
                SectionCard(title = "What Is a System Call") {
                    BodyText(
                        "A system call (syscall) is the ABI contract between user-space code and the " +
                        "kernel. It is the only legitimate way for a user-mode program to request a " +
                        "kernel service — such as opening a file, allocating memory, or creating a " +
                        "process."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "The C standard library wraps almost every syscall in a thin function with the " +
                        "same name. When you call open() or write() in C, you are usually calling the " +
                        "libc wrapper, not issuing the syscall directly."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Common syscalls on x86-64 Linux (approximately 350 total):")
                    CodeBlock(
                        """read    write   open    close   stat
fork    execve  exit    wait4   kill
mmap    munmap  brk     ioctl   socket
connect bind    listen  accept  sendto"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "POSIX standardizes the user-visible interface for many of these. The kernel " +
                        "implements them in architecture-specific entry points."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "Ring 0 and Ring 3") {
                    BodyText(
                        "x86 processors define four privilege levels, called rings, numbered 0 to 3. " +
                        "The Current Privilege Level (CPL) is stored in bits 0-1 of the CS register."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Linux (like Windows) uses only two of these rings:"
                    )
                    CodeBlock(
                        """Ring 0 — kernel mode
  Full hardware access. Can execute privileged instructions,
  access any memory, configure the MMU, manage interrupts.

Ring 3 — user mode
  Restricted. Cannot directly access hardware, kernel memory,
  or execute privileged instructions. Must ask the kernel
  via system calls."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Rings 1 and 2 exist in the x86 specification but are unused by both Linux and " +
                        "Windows — the model is effectively binary: kernel vs user."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "This is the same ring 0/ring 3 model as Windows. Windows system services " +
                        "(the SSDT — System Service Descriptor Table) play the same role as Linux's " +
                        "sys_call_table: a table of kernel functions indexed by a number that user " +
                        "code places in a register before triggering the transition."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "The syscall Instruction (64-bit)") {
                    BodyText(
                        "On x86-64, the fast syscall instruction is used. Before executing it, user " +
                        "code loads the syscall number and arguments into specific registers:"
                    )
                    CodeBlock(
                        """Register  Role
--------  ----
RAX       syscall number
RDI       1st argument
RSI       2nd argument
RDX       3rd argument
R10       4th argument  ← NOTE: R10, not RCX (unlike function calls)
R8        5th argument
R9        6th argument
RAX       return value (after the call)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("What the CPU does when syscall executes:")
                    CodeBlock(
                        """1. RCX   ← RIP    (save user instruction pointer for sysret)
2. R11   ← RFLAGS (save user flags for sysret)
3. RFLAGS bits cleared per IA32_FMASK MSR (clears IF, disabling interrupts)
4. CS    ← kernel code selector  → CPL switches to 0
5. RIP   ← IA32_LSTAR MSR (address of kernel entry: entry_SYSCALL_64)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("What the kernel entry code (entry_SYSCALL_64) does:")
                    CodeBlock(
                        """1. swapgs — load per-CPU kernel data pointer from GS_BASE MSR
2. Switch RSP to the kernel stack for this CPU/task
3. Save all user registers to struct pt_regs on the kernel stack
4. Call do_syscall_64(regs, RAX)
5.   → sys_call_table[RAX](args...) dispatched here
6.   → return value placed in regs->ax
7. sysret: restores RIP ← RCX, RFLAGS ← R11, CPL → 3"""
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "The Old Way: int 0x80 (32-bit)") {
                    BodyText(
                        "Before x86-64, 32-bit Linux used the int 0x80 software interrupt to enter " +
                        "the kernel. This is a trap-gate approach — slower than the dedicated " +
                        "syscall instruction because interrupt handling has more overhead."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("32-bit calling convention (int 0x80):")
                    CodeBlock(
                        """EAX = syscall number
EBX = 1st argument
ECX = 2nd argument
EDX = 3rd argument
ESI = 4th argument
EDI = 5th argument
EBP = 6th argument
EAX = return value"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "int 0x80 is still supported on 64-bit Linux for 32-bit binaries (IA-32 " +
                        "compatibility mode). It routes through a separate 32-bit syscall table."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Intel also introduced sysenter/sysexit as a faster 32-bit alternative to " +
                        "int 0x80. Linux uses it via the VDSO (Virtual Dynamic Shared Object), " +
                        "a small kernel-mapped page that picks the fastest available mechanism " +
                        "at runtime."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "How to Call a Syscall") {
                    BodyText("There are three ways, from most to least practical:")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("1. libc wrapper (recommended)")
                    CodeBlock(
                        """#include <unistd.h>

ssize_t n = write(fd, buf, len);
int fd    = open("/etc/passwd", O_RDONLY);"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "2. Generic syscall() — bypasses the wrapper, calls the kernel directly " +
                        "by number. Useful when the wrapper does not exist yet for a new syscall."
                    )
                    CodeBlock(
                        """#include <sys/syscall.h>
#include <unistd.h>

long ret = syscall(SYS_write, fd, buf, len);
/* SYS_write expands to the numeric syscall number (1 on x86-64) */"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("3. Raw inline assembly — direct hardware-level call:")
                    CodeBlock(
                        """long result;
asm volatile (
    "syscall"
    : "=a" (result)       /* output: rax → result */
    : "0"  (1),           /* rax = __NR_write (1) */
      "D"  (fd),          /* rdi = file descriptor */
      "S"  (buf),         /* rsi = buffer pointer */
      "d"  (len)          /* rdx = byte count */
    : "rcx", "r11", "memory"  /* clobbers */
);"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "The clobbers list includes rcx and r11 because the syscall instruction " +
                        "overwrites them with the saved RIP and RFLAGS."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "The System Call Table") {
                    BodyText(
                        "The kernel dispatches syscalls through sys_call_table[], a read-only array " +
                        "of function pointers indexed by syscall number. On x86-64 it is defined " +
                        "in arch/x86/entry/syscall_64.c and auto-generated from the master list " +
                        "in arch/x86/entry/syscalls/syscall_64.tbl."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Syscall numbers (x86-64):")
                    CodeBlock(
                        """__NR_read    = 0
__NR_write   = 1
__NR_open    = 2
__NR_close   = 3
__NR_stat    = 4
__NR_fork    = 57
__NR_execve  = 59
__NR_mmap    = 9

/* List all numbers on your system: */
cat /usr/include/asm/unistd_64.h | grep "#define __NR_""""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Syscall implementations in the kernel are defined with SYSCALL_DEFINE macros, " +
                        "where the number suffix is the argument count:"
                    )
                    CodeBlock(
                        """SYSCALL_DEFINE3(write,
    unsigned int, fd,
    const char __user *, buf,
    size_t, count)
{
    struct fd f = fdget_pos(fd);
    /* ... kernel write implementation ... */
}"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "The __user annotation marks pointers to user-space memory. Kernel code " +
                        "must use copy_from_user() / copy_to_user() to access them safely."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "sys_call_table in kallsyms") {
                    BodyText(
                        "sys_call_table is a kernel symbol and is visible in /proc/kallsyms. " +
                        "It appears as a type 'R' (read-only data) symbol:"
                    )
                    CodeBlock(
                        """grep sys_call_table /proc/kallsyms
# → ffffffff82a001a0 R sys_call_table
#   (address may show as 0000000000000000 without root)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Whether the real address is shown depends on kptr_restrict " +
                        "(/proc/sys/kernel/kptr_restrict). Root or a process with CAP_SYSLOG " +
                        "is typically required to see non-zero addresses. See the kallsyms screen " +
                        "for full details."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Historically, kernel rootkits and syscall-hooking LKMs modified " +
                        "sys_call_table to intercept system calls. Modern kernels prevent this:"
                    )
                    CodeBlock(
                        """• __ro_after_init marks sys_call_table read-only after boot
• CR0.WP (Write Protect bit) prevents ring-0 writes to read-only pages
• CONFIG_STRICT_KERNEL_RWX enforces W^X for all kernel memory

Attempting to write to sys_call_table on a modern kernel will
trigger a page fault and kernel oops (or panic)."""
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "SYSCALL_DEFINE Macros") {
                    BodyText(
                        "The SYSCALL_DEFINE family of macros is used to implement syscall handlers. " +
                        "There are seven variants, one per argument count (0 through 6):"
                    )
                    CodeBlock(
                        """SYSCALL_DEFINE0(getpid)
SYSCALL_DEFINE1(close,  unsigned int, fd)
SYSCALL_DEFINE2(kill,   pid_t, pid,  int, sig)
SYSCALL_DEFINE3(write,  unsigned int, fd,
                        const char __user *, buf,
                        size_t, count)
SYSCALL_DEFINE4(openat, int, dfd, const char __user *, filename,
                        int, flags, umode_t, mode)
SYSCALL_DEFINE5(...)
SYSCALL_DEFINE6(mmap,   unsigned long, addr, unsigned long, len,
                        unsigned long, prot, unsigned long, flags,
                        unsigned long, fd,   unsigned long, offset)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "The macro arguments alternate: type, name, type, name, ... " +
                        "This is how the macro can generate both the function signature and " +
                        "the argument names for tracing."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "On modern x86-64 (since Linux 4.17), SYSCALL_DEFINE generates three " +
                        "layered functions — for example, SYSCALL_DEFINE3(write, ...) produces:"
                    )
                    CodeBlock(
                        """/* 1. Arch entry: receives pt_regs, extracts raw register values */
long __x64_sys_write(const struct pt_regs *regs)
{
    return __se_sys_write(regs->di, regs->si, regs->dx);
}

/* 2. Type-safe shim: casts raw longs to correct argument types */
long __se_sys_write(long fd, long buf, long count)
{
    return __do_sys_write((unsigned int)fd,
                          (const char __user *)buf,
                          (size_t)count);
}

/* 3. Actual implementation (static — not directly exported) */
static long __do_sys_write(unsigned int fd,
                            const char __user *buf,
                            size_t count) { ... }"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "sys_call_table[__NR_write] points to __x64_sys_write. The layering " +
                        "ensures correct type casting even when the architecture passes raw " +
                        "register values, and it also lets BPF/ftrace hook cleanly into the " +
                        "named entry points."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "asmlinkage and Calling Convention") {
                    BodyText(
                        "asmlinkage is a macro defined in include/linux/linkage.h. Its meaning " +
                        "differs by architecture."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("On x86-32:")
                    CodeBlock(
                        """#define asmlinkage  __attribute__((regparm(0)))

regparm(0) means: pass ALL arguments on the stack, not in registers.

Why needed: the x86-32 kernel is built with -mregparm=3, which tells GCC
to pass the first three arguments in EAX, EDX, ECX. But the syscall entry
assembly (entry.S) saves the user registers into a pt_regs struct on the
kernel stack, then calls the handler. The handler's args are therefore on
the stack — not in the CPU registers GCC would normally expect.

Without asmlinkage, GCC would generate code that reads args from EAX/EDX/
ECX and get garbage. With it, GCC reads from the stack instead — matching
what the assembly already put there."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("On x86-64:")
                    CodeBlock(
                        """#define asmlinkage  __visible
/* (__visible = __attribute__((externally_visible))) */

On x86-64, asmlinkage is essentially a no-op for calling convention.
The ABI mismatch is solved differently: the SYSCALL_DEFINE macro generates
__x64_sys_foo(pt_regs*) which extracts register values explicitly, then
calls __do_sys_foo with proper C types. There is no ambiguity.

__visible prevents LTO (Link Time Optimization) from inlining or hiding
the function, because it is called from assembly (entry_SYSCALL_64) and
the linker must keep the symbol alive and findable."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "In summary: asmlinkage originally solved a real ABI problem on x86-32. " +
                        "On x86-64, it is kept for historical uniformity and LTO safety, but " +
                        "the actual calling-convention bridging is handled by the generated " +
                        "__x64_sys / __se_sys intermediate functions."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "Adding a New Syscall — Walkthrough (x86-64)") {
                    BodyText(
                        "Adding a real syscall to the Linux kernel requires touching several files. " +
                        "Here is a complete walkthrough for a syscall named sys_hello."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Step 1 — Pick the next available syscall number")
                    CodeBlock(
                        """# See the last few entries in the table:
grep "^[0-9]" arch/x86/entry/syscalls/syscall_64.tbl \
    | sort -n | tail -5

# Example output:
# 449  common  futex_waitv     sys_futex_waitv
# 450  common  set_mempolicy_home_node  sys_set_mempolicy_home_node
# 451  common  cachestat       sys_cachestat
# ...
# → use the next free number (e.g. 452)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Step 2 — Edit arch/x86/entry/syscalls/syscall_64.tbl")
                    CodeBlock(
                        """# Format:  <number>  <abi>  <name>  <entry_point>
# abi values: common (64-bit + x32), 64 (64-bit only), x32 (x32 ABI only)

452  common  hello  sys_hello

# The build scripts (scripts/syscalltbl.sh + scripts/syscallhdr.sh)
# auto-generate __NR_hello in the uapi headers — you do NOT manually
# edit include/uapi/asm/unistd_64.h."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Step 3 — Add the prototype to include/linux/syscalls.h")
                    CodeBlock(
                        """/* near the end of the file, before the #endif */
asmlinkage long sys_hello(char __user *buf, size_t len);"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Step 4 — Implement the syscall (e.g., in kernel/sys.c or a new file)")
                    CodeBlock(
                        """#include <linux/syscalls.h>
#include <linux/uaccess.h>

SYSCALL_DEFINE2(hello,
    char __user *, buf,
    size_t,        len)
{
    const char msg[] = "Hello from the kernel!\n";

    if (len < sizeof(msg))
        return -EINVAL;

    if (copy_to_user(buf, msg, sizeof(msg)))
        return -EFAULT;

    return sizeof(msg);   /* return bytes written, like write() */
}"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Step 5 — If you created a new .c file, add it to its directory's Makefile")
                    CodeBlock(
                        """# e.g. kernel/Makefile — add one line:
obj-y += hello.o"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Step 6 — Build and test from user space")
                    CodeBlock(
                        """/* User-space test program */
#include <sys/syscall.h>
#include <unistd.h>
#include <stdio.h>

#define __NR_hello 452   /* or use the generated header after build */

int main(void) {
    char buf[64];
    long ret = syscall(__NR_hello, buf, sizeof(buf));
    if (ret > 0) printf("%s", buf);
    return 0;
}"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Files changed — summary:")
                    CodeBlock(
                        """arch/x86/entry/syscalls/syscall_64.tbl  ← add number + name
include/linux/syscalls.h                ← add prototype
kernel/sys.c  (or new kernel/hello.c)  ← implement SYSCALL_DEFINE
kernel/Makefile  (only if new file)    ← obj-y += hello.o"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Other architectures (ARM64, RISC-V) have their own syscall table files " +
                        "(e.g., arch/arm64/tools/syscall.tbl or the generic " +
                        "include/uapi/asm-generic/unistd.h). The kernel steps are the same; " +
                        "only the table file location differs."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Modifying an existing syscall: only change the implementation function body " +
                        "in its source file. Never change the syscall number or parameter types in " +
                        "the .tbl or syscalls.h — that would break the ABI and all programs that " +
                        "already use that syscall."
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
