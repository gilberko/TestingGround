package com.example.linuxapp.screens.permissions

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.linuxapp.screens.kernel.BodyText
import com.example.linuxapp.screens.kernel.CodeBlock
import com.example.linuxapp.screens.kernel.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProcessStartScreen(onBack: () -> Unit) {
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding).padding(horizontal = 12.dp)
        ) {

            // ── 1. Overview ──────────────────────────────────────────────────────────
            item {
                SectionCard(title = "Overview — The Full Journey") {
                    BodyText("When you type ./hello and press Enter, the kernel goes through roughly 11 distinct stages before the first line of your code runs. This screen walks each stage in order.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Stage 1  Shell resolves the name to a filesystem path.")
                    BodyText("Stage 2  Kernel checks the x-bit — is this process allowed to run the file?")
                    BodyText("Stage 3  setuid/setgid — does the file change the caller's effective identity?")
                    BodyText("Stage 4  Shell calls fork() to create a child process.")
                    BodyText("Stage 5  Child calls execve() — the point of no return.")
                    BodyText("Stage 6  Kernel parses the ELF file header and program headers.")
                    BodyText("Stage 7  Kernel maps ELF segments into the new address space.")
                    BodyText("Stage 8  If PT_INTERP is present, the dynamic linker (ld.so) takes control.")
                    BodyText("Stage 9  ld.so loads all required shared libraries (.so files).")
                    BodyText("Stage 10 ld.so resolves symbols and patches PLT/GOT relocations.")
                    BodyText("Stage 11 C runtime (_start) calls __libc_start_main which calls main().")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Example used throughout: a small C program compiled as ./hello that calls printf(\"Hello\\n\").")
                }
                Spacer(Modifier.height(8.dp))
            }

            // ── 2. Shell: Finding the Executable ────────────────────────────────────
            item {
                SectionCard(title = "Stage 1 — Shell: Finding the Executable") {
                    BodyText("When you type hello (no slash), bash looks through each directory in PATH left to right, checking whether a file named hello exists and has the execute bit set. The first match wins.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock("""
echo ${'$'}PATH
# /usr/local/sbin:/usr/local/bin:/usr/bin:/bin

type -a hello      # shows every match on PATH
which hello        # prints first match only
                    """.trimIndent())
                    Spacer(Modifier.height(6.dp))
                    BodyText("A leading ./ or / skips PATH entirely — the shell passes the exact string to execve().")
                    Spacer(Modifier.height(6.dp))
                    BodyText("If no match is found, bash prints 'command not found' — the kernel never even sees the request. If the path is found but the file is not executable, execve() returns EACCES and bash prints 'Permission denied'.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("execvp() and execv() — the C library wrappers used by shells — do this PATH scan internally. The raw kernel syscall execve() accepts only an absolute or relative path and returns ENOENT if the file does not exist.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock("""
# Watch execve calls in strace
strace -e execve bash -c './hello'
# execve("./hello", ["./hello"], 0x... /* env */) = 0
                    """.trimIndent())
                }
                Spacer(Modifier.height(8.dp))
            }

            // ── 3. Permission Check ──────────────────────────────────────────────────
            item {
                SectionCard(title = "Stage 2 — Permission Check: Can We Run It?") {
                    BodyText("Before loading anything, the kernel checks whether the calling process is allowed to execute the file. This happens inside do_open_execat() → inode_permission() → generic_permission().")
                    Spacer(Modifier.height(6.dp))
                    BodyText("The kernel compares the process's credentials (real UID, effective UID, supplementary groups) against the file's inode (st_uid, st_gid, st_mode):")
                    Spacer(Modifier.height(6.dp))
                    BodyText("• If euid == file owner  → check owner x-bit (bit 6, S_IXUSR)")
                    BodyText("• Else if egid or any suppl. group == file group  → check group x-bit (bit 3, S_IXGRP)")
                    BodyText("• Else  → check other x-bit (bit 0, S_IXOTH)")
                    BodyText("• CAP_DAC_OVERRIDE in CapEff bypasses the owner/group/other check entirely.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock("""
ls -l hello
# -rwxr-xr-x 1 gil gil 16488 May  2 10:00 hello
#  ^^^ owner=rwx, group=r-x, other=r-x

stat hello
# File: hello
# Access: (0755/-rwxr-xr-x) Uid: (1000/gil) Gid: (1000/gil)
                    """.trimIndent())
                    Spacer(Modifier.height(6.dp))
                    BodyText("If the check fails, execve() returns -EACCES. The process image is never touched.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("LSM hooks (SELinux, AppArmor) also run here via security_bprm_check(). They can deny execution based on policy labels independent of DAC bits.")
                }
                Spacer(Modifier.height(8.dp))
            }

            // ── 4. setuid / setgid ───────────────────────────────────────────────────
            item {
                SectionCard(title = "Stage 3 — The setuid / setgid Bit") {
                    BodyText("Two special permission bits can change the effective identity of the new process at exec time, not just at file-open time.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("S_ISUID (bit 11, 04000) — setuid bit:")
                    BodyText("When set, the kernel sets euid = file's st_uid for the new process, regardless of who called execve(). The real UID stays as the caller's UID.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock("""
ls -l /usr/bin/passwd
# -rwsr-xr-x 1 root root 68208 ... /usr/bin/passwd
#    ^
#    's' in owner-execute slot = setuid bit + x-bit both set
#    'S' would mean setuid bit set but x-bit NOT set (suspicious)

ls -l /usr/bin/sudo
# -rwsr-xr-x 1 root root 232416 ... /usr/bin/sudo
                    """.trimIndent())
                    Spacer(Modifier.height(6.dp))
                    BodyText("S_ISGID (bit 10, 02000) — setgid bit: sets egid = file's st_gid.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("The kernel stores three IDs in task_struct.cred: real (who you are), effective (what you can do), and saved-set (what you can drop back to). On a setuid exec, saved-set = new euid, so the process can later drop to ruid and re-escalate if needed (e.g. sshd dropping to user and re-escalating).")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Setting the bit:")
                    CodeBlock("""
chmod u+s myprog        # set setuid
chmod 4755 myprog       # same thing via octal
# Now ls -l shows 'rws' or 'rwS' in owner field
                    """.trimIndent())
                    Spacer(Modifier.height(6.dp))
                    BodyText("Security note: LD_PRELOAD and LD_LIBRARY_PATH are silently ignored by ld.so when euid != ruid, to prevent privilege escalation via injected shared libraries.")
                }
                Spacer(Modifier.height(8.dp))
            }

            // ── 5. fork() + execve() ─────────────────────────────────────────────────
            item {
                SectionCard(title = "Stage 4–5 — fork() and execve()") {
                    BodyText("The shell uses the classic fork-exec pattern to launch every external command.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("fork() (syscall nr 57 on x86-64) clones the calling process:")
                    BodyText("• Parent receives the child's PID (> 0)")
                    BodyText("• Child receives 0")
                    BodyText("• Address space is copy-on-write — no physical pages are copied yet")
                    BodyText("• File descriptors, signal masks, working directory are inherited")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock("""
// Minimal fork+exec in C
pid_t pid = fork();
if (pid == 0) {
    // child
    char *argv[] = { "./hello", NULL };
    char *envp[] = { "HOME=/home/gil", NULL };
    execve("./hello", argv, envp);
    perror("execve");   // only reached on error
    _exit(1);
} else {
    // parent (shell)
    int status;
    waitpid(pid, &status, 0);
}
                    """.trimIndent())
                    Spacer(Modifier.height(6.dp))
                    BodyText("execve() (syscall nr 59) is the point of no return. On success it never returns to the caller — the calling thread's address space is completely replaced. Kernel path:")
                    Spacer(Modifier.height(6.dp))
                    BodyText("sys_execve → do_execve → do_execveat_common")
                    Spacer(Modifier.height(6.dp))
                    BodyText("do_execveat_common allocates a linux_binprm (bprm) struct that carries:")
                    BodyText("• filename, file* to the opened executable")
                    BodyText("• argc, argv[], envp[] (copied from user space)")
                    BodyText("• cred (new credentials being prepared)")
                    BodyText("• buf[BINPRM_BUF_SIZE] — first 256 bytes of file for magic-number detection")
                    Spacer(Modifier.height(6.dp))
                    BodyText("search_binary_handler() walks the list of registered binary formats (binfmt_elf, binfmt_script, binfmt_misc) checking the magic bytes. For ELF it calls load_elf_binary().")
                }
                Spacer(Modifier.height(8.dp))
            }

            // ── 6. ELF Format ────────────────────────────────────────────────────────
            item {
                SectionCard(title = "Stage 6 — The ELF File Format") {
                    BodyText("ELF (Executable and Linkable Format) is the standard binary format on Linux. Every executable, shared library, and relocatable object file is an ELF.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("ELF Header (64 bytes at offset 0):")
                    BodyText("• e_ident[4] — magic bytes: 0x7f 'E' 'L' 'F'")
                    BodyText("• e_type — ET_EXEC (position-dependent), ET_DYN (PIE exe or .so), ET_REL (.o file)")
                    BodyText("• e_entry — virtual address of the first instruction (usually _start)")
                    BodyText("• e_phoff / e_phnum — offset and count of program headers")
                    BodyText("• e_shoff / e_shnum — offset and count of section headers")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Program headers (kernel's view — needed at runtime):")
                    BodyText("• PT_LOAD  — segment to map into memory; has vaddr, filesz, memsz, flags (R/W/X)")
                    BodyText("• PT_INTERP  — path of the dynamic linker (e.g. /lib64/ld-linux-x86-64.so.2)")
                    BodyText("• PT_DYNAMIC  — location of the .dynamic section (DT_* entries)")
                    BodyText("• PT_PHDR  — the program header table itself")
                    BodyText("• PT_GNU_STACK  — stack permissions (W^X enforcement)")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Key sections (linker's view — may be stripped in production):")
                    BodyText("• .text — executable code (R+X)")
                    BodyText("• .rodata — read-only data, string literals (R)")
                    BodyText("• .data — initialised globals (R+W)")
                    BodyText("• .bss — uninitialised globals (R+W, zero-filled; no space in file)")
                    BodyText("• .plt — Procedure Linkage Table stubs for external calls")
                    BodyText("• .got / .got.plt — Global Offset Table (pointer array patched at runtime)")
                    BodyText("• .dynamic — DT_NEEDED, DT_RPATH, DT_SYMTAB, DT_STRTAB, etc.")
                    BodyText("• .dynsym / .dynstr — dynamic symbol table and its string pool")
                    BodyText("• .rela.plt / .rela.dyn — relocation entries")
                    Spacer(Modifier.height(6.dp))
                    BodyText("memsz > filesz in a PT_LOAD entry means the extra bytes (the .bss region) are zero-filled by the kernel — they take no space on disk.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock("""
readelf -l hello          # show program headers
# INTERP  0x... /lib64/ld-linux-x86-64.so.2
# LOAD    offset=0x0    vaddr=0x400000  memsz=0x... flags=R E
# LOAD    offset=0x... vaddr=0x601000  memsz=0x... flags=RW
# DYNAMIC offset=0x...

readelf -S hello          # show section headers
# [14] .text   PROGBITS  0x401060 ... AX
# [16] .rodata PROGBITS  0x402000 ... A
# [24] .got.plt PROGBITS 0x404000 ... WA
                    """.trimIndent())
                }
                Spacer(Modifier.height(8.dp))
            }

            // ── 7. Kernel: Mapping the ELF ───────────────────────────────────────────
            item {
                SectionCard(title = "Stage 7 — Kernel Maps the ELF into Memory") {
                    BodyText("load_elf_binary() in fs/binfmt_elf.c does the actual work of building the new address space.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Step 1 — flush_old_exec(): tears down the old address space (all VMAs unmapped, file descriptors with O_CLOEXEC closed, signal handlers reset to default).")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Step 2 — map PT_LOAD segments: for each PT_LOAD entry, elf_map() calls do_mmap() with the segment's vaddr, filesz, and permission flags. The flags mapping is:")
                    BodyText("• PF_R → PROT_READ, PF_W → PROT_WRITE, PF_X → PROT_EXEC")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Step 3 — ASLR: if e_type == ET_DYN (PIE executable), the kernel adds a random load_bias to every vaddr. ET_EXEC binaries have fixed virtual addresses and are not randomised.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Step 4 — zero .bss: the kernel zero-fills the gap between filesz and memsz in the last writable PT_LOAD (this is the .bss region).")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Step 5 — set up stack: the kernel allocates a stack VMA at the top of the address space and pushes the initial stack frame:")
                    BodyText("   [argc] [argv ptrs] [NULL] [envp ptrs] [NULL] [auxv pairs] [strings]")
                    Spacer(Modifier.height(6.dp))
                    BodyText("auxv (auxiliary vector) entries the kernel writes:")
                    BodyText("• AT_PHDR / AT_PHENT / AT_PHNUM — program header location and count")
                    BodyText("• AT_ENTRY — original application entry point (for ld.so to jump to later)")
                    BodyText("• AT_BASE — base address where ld.so was mapped")
                    BodyText("• AT_RANDOM — 16 random bytes (used by glibc for stack canaries)")
                    BodyText("• AT_PAGESZ — 4096")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Step 6 — heap: mm->start_brk = mm->brk is set just past .bss. No heap pages are allocated yet; the first malloc() will call brk() or mmap().")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock("""
# /proc/PID/maps after execve, before main:
# 00400000-00401000 r--p  /home/gil/hello  (ELF header)
# 00401000-00402000 r-xp  /home/gil/hello  (.text)
# 00402000-00403000 r--p  /home/gil/hello  (.rodata)
# 00403000-00404000 r--p  /home/gil/hello  (.dynamic etc)
# 00404000-00405000 rw-p  /home/gil/hello  (.got.plt, .data, .bss)
# 7fff...           rw-p  [stack]
# 7f... ld-linux    r-xp  /lib64/ld-linux-x86-64.so.2
                    """.trimIndent())
                }
                Spacer(Modifier.height(8.dp))
            }

            // ── 8. Dynamic Linker ────────────────────────────────────────────────────
            item {
                SectionCard(title = "Stage 8 — The Dynamic Linker (ld-linux.so)") {
                    BodyText("If the ELF has a PT_INTERP segment, the kernel maps the file it names (the dynamic linker) and sets the instruction pointer to ld.so's entry point instead of the application's entry point.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock("""
readelf -l hello | grep INTERP
#       [Requesting program interpreter: /lib64/ld-linux-x86-64.so.2]

file hello
# hello: ELF 64-bit LSB pie executable, dynamically linked,
#        interpreter /lib64/ld-linux-x86-64.so.2
                    """.trimIndent())
                    Spacer(Modifier.height(6.dp))
                    BodyText("ld.so is itself an ET_DYN shared library. The kernel maps its PT_LOAD segments and records its base in AT_BASE in the auxv.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("ld.so's first job is self-relocation: it bootstraps itself (its own GOT is not yet valid) using position-independent code and fixes up its own relocations before calling any libc functions.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("ld.so then reads the auxv to find:")
                    BodyText("• AT_PHDR — where the app's program headers are in memory")
                    BodyText("• AT_ENTRY — the app's own entry point (_start), to jump to after setup")
                    BodyText("• AT_BASE — ld.so's own load base")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Statically linked binary (compiled with -static): no PT_INTERP, no ld.so involved. The kernel jumps directly to e_entry (_start in the binary). All libc code is baked in.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock("""
gcc -static -o hello_static hello.c
readelf -l hello_static | grep INTERP
# (no output — no interpreter)
file hello_static
# hello_static: ELF 64-bit LSB executable, statically linked
                    """.trimIndent())
                }
                Spacer(Modifier.height(8.dp))
            }

            // ── 9. Loading Shared Libraries ──────────────────────────────────────────
            item {
                SectionCard(title = "Stage 9 — Loading Shared Libraries") {
                    BodyText("ld.so locates and maps every shared library the application depends on, then recursively their dependencies (DT_NEEDED is a DAG, not just a flat list).")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Step 1 — read .dynamic: ld.so walks the DT_NEEDED entries in the app's .dynamic section. Each entry is a string like \"libc.so.6\" or \"libpthread.so.0\".")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Step 2 — search order for each library name:")
                    BodyText("  1. DT_RPATH in the binary's .dynamic (baked in at link time, deprecated)")
                    BodyText("  2. LD_LIBRARY_PATH environment variable (ignored for setuid binaries)")
                    BodyText("  3. DT_RUNPATH in the binary's .dynamic (link-time, respects LD_LIBRARY_PATH)")
                    BodyText("  4. /etc/ld.so.cache (built by ldconfig from /etc/ld.so.conf)")
                    BodyText("  5. /lib, /lib64, /usr/lib, /usr/lib64 (compiled-in defaults)")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Step 3 — map the .so: open the file → read ELF header → for each PT_LOAD call mmap() into a suitable address range (always ET_DYN, so ASLR picks the base). Increment reference count.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Step 4 — recurse: process the newly loaded library's own DT_NEEDED entries. Libraries already mapped (by inode) are skipped.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock("""
ldd hello
# linux-vdso.so.1 => (0x00007ffd...)      # kernel-injected vDSO
# libc.so.6 => /lib/x86_64-linux-gnu/libc.so.6 (0x7f...)
# /lib64/ld-linux-x86-64.so.2 (0x7f...)

LD_DEBUG=libs,files ./hello 2>&1 | head -30
# find library=libc.so.6 [0]; searching
#  search cache=/etc/ld.so.cache
#   trying file=/lib/x86_64-linux-gnu/libc.so.6
#  calling init: /lib/x86_64-linux-gnu/libc.so.6
                    """.trimIndent())
                    Spacer(Modifier.height(6.dp))
                    BodyText("vDSO (virtual Dynamic Shared Object): a small shared library the kernel injects into every process automatically. It provides fast user-space implementations of frequently called syscalls like clock_gettime() so they avoid the full ring-3→ring-0 transition.")
                }
                Spacer(Modifier.height(8.dp))
            }

            // ── 10. Symbol Resolution / PLT/GOT ─────────────────────────────────────
            item {
                SectionCard(title = "Stage 10 — Symbol Resolution and PLT/GOT Relocations") {
                    BodyText("Once all libraries are mapped, ld.so resolves undefined symbols and patches the GOT so that calls to external functions reach the right addresses.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Symbol lookup: ld.so walks the 'scope chain' — the ordered list of loaded objects — and finds the first definition of each symbol in their .dynsym tables. The default scope is: app → its DT_NEEDED libs → their DT_NEEDED libs (breadth-first).")
                    Spacer(Modifier.height(6.dp))
                    BodyText("PLT/GOT lazy binding (default):")
                    BodyText("• Each external function (e.g. printf) gets a PLT stub and a GOT slot.")
                    BodyText("• Initially, the GOT slot points back into the PLT stub itself.")
                    BodyText("• First call: PLT stub pushes a relocation index, jumps to ld.so's resolver.")
                    BodyText("• Resolver finds printf in libc's .dynsym, writes its address into the GOT slot.")
                    BodyText("• All future calls: PLT stub loads the GOT slot → jumps directly to printf. O(1) overhead.")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock("""
# PLT stub for printf (simplified x86-64 disassembly):
objdump -d -j .plt hello
# 0000000000401030 <printf@plt>:
#   401030: ff 25 e2 2f 00 00  jmpq *0x2fe2(%rip)  # GOT slot
#   401036: 68 00 00 00 00     push  ${'$'}0x0         # reloc index
#   40103b: e9 e0 ff ff ff     jmpq  401020         # ld.so resolver

readelf -r hello
# Relocation section '.rela.plt':
# Offset       Info    Type            Symbol
# 00404018  0000000100000007 R_X86_64_JUMP_SLOT  printf@GLIBC_2.2.5
                    """.trimIndent())
                    Spacer(Modifier.height(6.dp))
                    BodyText("Relocation record fields:")
                    BodyText("• r_offset — address of the GOT slot to patch")
                    BodyText("• r_info — encodes symbol index + relocation type (R_X86_64_JUMP_SLOT)")
                    BodyText("• r_addend — constant addend (usually 0 for JUMP_SLOT)")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Eager binding: LD_BIND_NOW=1 or link flag -Wl,-z,now forces ld.so to resolve all symbols at startup before transferring control. Slower startup, but no lazy-resolution jitter at runtime. Security hardening (full RELRO + BIND_NOW) also marks the GOT read-only after relocation.")
                    Spacer(Modifier.height(6.dp))
                    BodyText(".rela.dyn holds GLOB_DAT and RELATIVE relocations for non-function external references (global variables, data pointers). These are always resolved eagerly.")
                }
                Spacer(Modifier.height(8.dp))
            }

            // ── 11. C Runtime: _start → main() ──────────────────────────────────────
            item {
                SectionCard(title = "Stage 11 — C Runtime: _start → main()") {
                    BodyText("After ld.so finishes loading and relocating, it jumps to the application's entry point (AT_ENTRY from auxv). For a C program this is _start, not main.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("_start comes from crt1.o, compiled into glibc and linked into every C executable. It is the true ELF entry point (e_entry). Responsibilities:")
                    Spacer(Modifier.height(6.dp))
                    BodyText("• Align RSP to a 16-byte boundary (x86-64 ABI requires this before any CALL)")
                    BodyText("• Zero RBP (marks the outermost stack frame for debuggers and stack unwinders)")
                    BodyText("• Read argc from the top of the stack (kernel placed it there)")
                    BodyText("• Compute argv = RSP + 8, envp = RSP + 8 + (argc+1)*8")
                    BodyText("• Call __libc_start_main(main, argc, argv, init, fini, rtld_fini, stack_end)")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock("""
# _start (simplified x86-64 from glibc crt1.o):
_start:
    xor  %rbp, %rbp          # mark outermost frame
    mov  (%rsp), %edi         # argc
    lea  8(%rsp), %rsi        # argv
    lea  16(%rsp,%rdi,8), %rdx  # envp (past argv NULL)
    call __libc_start_main
    hlt                       # never reached

# __libc_start_main prototype:
int __libc_start_main(
    int  (*main)(int, char**, char**),
    int  argc,
    char **argv,
    void (*init)(void),       # points to __libc_csu_init
    void (*fini)(void),       # points to __libc_csu_fini
    void (*rtld_fini)(void),  # ld.so cleanup callback
    void *stack_end
);
                    """.trimIndent())
                    Spacer(Modifier.height(6.dp))
                    BodyText("__libc_start_main responsibilities (in order):")
                    Spacer(Modifier.height(6.dp))
                    BodyText("1. Register rtld_fini with atexit() so ld.so runs DT_FINI destructors at exit.")
                    BodyText("2. Set up TLS (Thread-Local Storage) for the main thread (calls __pthread_initialize_minimal).")
                    BodyText("3. Store argc/argv/envp in libc globals (__libc_argc, __libc_argv, environ).")
                    BodyText("4. Call security_init() — sets up stack canary from AT_RANDOM bytes.")
                    BodyText("5. Call the init function — walks the .init_array section calling each function pointer. This runs global C++ constructors and any __attribute__((constructor)) functions.")
                    BodyText("6. Call main(argc, argv, envp).")
                    BodyText("7. Pass main's return value to exit().")
                    Spacer(Modifier.height(6.dp))
                    BodyText("exit() in glibc:")
                    BodyText("• Calls all functions registered with atexit() and on_exit() in LIFO order")
                    BodyText("• Flushes and closes all stdio FILE streams (fflush / fclose)")
                    BodyText("• Calls .fini_array destructors (reverse order of .init_array)")
                    BodyText("• Calls the _exit() syscall (nr 60 on x86-64) — kernel tears down the process")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock("""
// __attribute__((constructor)) runs before main:
__attribute__((constructor))
static void my_init(void) {
    printf("I run before main!\n");
}

// __attribute__((destructor)) runs after main returns:
__attribute__((destructor))
static void my_fini(void) {
    printf("I run after main!\n");
}

int main(void) {
    printf("Hello from main\n");
    return 0;
}
// Output:
// I run before main!
// Hello from main
// I run after main!
                    """.trimIndent())
                    Spacer(Modifier.height(6.dp))
                    BodyText("musl libc follows the same structure but with a leaner implementation. The entry point is also _start, it also calls __libc_start_main, and constructor/destructor arrays work identically. The difference is musl has no lazy-init, no NSS plugins, and a much smaller code size.")
                }
                Spacer(Modifier.height(8.dp))
            }

            // ── 12. Full Example Walkthrough ─────────────────────────────────────────
            item {
                SectionCard(title = "Full Example — ./hello Under strace") {
                    BodyText("Compiling and tracing a minimal C program that calls printf(\"Hello\\n\"):")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock("""
gcc -o hello hello.c          # dynamic, PIE by default on modern gcc
strace -e trace=execve,openat,mmap,brk,write ./hello
                    """.trimIndent())
                    Spacer(Modifier.height(6.dp))
                    BodyText("Annotated strace output (abbreviated):")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock("""
# Stage 4-5: shell's execve syscall
execve("./hello", ["./hello"], 0x... /* 50 vars */) = 0

# Stage 8: kernel mapped ld.so; ld.so now runs
# Stage 9: ld.so opens libc
openat(AT_FDCWD, "/etc/ld.so.cache", O_RDONLY|O_CLOEXEC) = 3
openat(AT_FDCWD, "/lib/x86_64-linux-gnu/libc.so.6",
       O_RDONLY|O_CLOEXEC)                             = 3

# Stage 9: ld.so maps libc's PT_LOAD segments
mmap(NULL, 1900544, PROT_READ, MAP_PRIVATE|MAP_DENYWRITE, 3, 0) = 0x7f...
mmap(0x7f...+0x28000, 1363968, PROT_READ|PROT_EXEC,
     MAP_PRIVATE|MAP_FIXED|MAP_DENYWRITE, 3, 0x28000) = 0x7f...
mmap(...  PROT_READ|PROT_WRITE ...) = 0x7f...   # .data .bss

# Stage 10: ld.so resolves all relocations (lazy default)
# (no strace output — happens in user space, no syscalls for lazy)

# Stage 11: glibc __libc_start_main setup
brk(NULL)    = 0x...         # get current heap end
brk(0x...)   = 0x...         # first malloc sets up heap arena

# main() runs, calls printf which calls write():
write(1, "Hello\n", 6)  = 6

# exit():
exit_group(0)            = ?
+++ exited with 0 +++
                    """.trimIndent())
                    Spacer(Modifier.height(6.dp))
                    BodyText("Key observations:")
                    BodyText("• execve() = 0 means the kernel accepted the binary — the old address space is already gone at this point.")
                    BodyText("• All the openat/mmap calls before write() are ld.so loading libc — main() has not run yet.")
                    BodyText("• printf() itself does not syscall on every call — glibc buffers stdout and flushes on '\\n' (line-buffered) or on exit.")
                    BodyText("• exit_group() (not exit()) is used because glibc uses it to terminate all threads atomically.")
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}
