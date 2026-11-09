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
fun UserModeDebuggingFlowScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Debugging User Mode Programs",
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
                SectionCard(title = "1. The Players: Tracer, Tracee and ptrace") {
                    BodyText("GDB is NOT special: it is an ordinary user-mode process. Everything it does to your program goes through ONE system call: ptrace(2).")
                    BodyText("  - TRACER = GDB.\n  - TRACEE = your program (GDB calls it the 'inferior').\n  - 'gdb ./prog': GDB fork()s, the child calls ptrace(PTRACE_TRACEME) and then execve()s your program - so it is traced from its very first instruction.\n  - 'gdb -p PID': GDB attaches to an existing process with PTRACE_ATTACH / PTRACE_SEIZE (needs permission: same uid + Yama ptrace_scope, or CAP_SYS_PTRACE).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("While the tracee runs, GDB is simply asleep in waitpid(). The kernel wakes GDB whenever the tracee STOPS (breakpoint, single step, watchpoint, signal, exit...). While the tracee is stopped GDB can read/write its memory and registers through ptrace:")
                    CodeBlock(
                        """PTRACE_PEEKTEXT / POKETEXT   read / write a word of memory
PTRACE_GETREGS  / SETREGS    read / write all registers (RIP, RSP...)
PTRACE_PEEKUSER / POKEUSER   read / write the 'user area' (debug regs)
PTRACE_CONT                  resume running
PTRACE_SINGLESTEP            resume for exactly ONE instruction
(GDB also uses /proc/PID/mem for bulk memory reads/writes)"""
                    )
                }
            }
            item {
                SectionCard(title = "2. Setting a Breakpoint: 0xCC Is Written Into the Code") {
                    BodyText("Yes - a normal (software) breakpoint really is a code modification. When you type 'break foo.c:42' GDB:")
                    BodyText("  1. Uses the DWARF debug info (line table) to map foo.c:42 to an instruction address.\n  2. Reads and SAVES the original byte at that address.\n  3. Writes 0xCC (INT3) over the FIRST byte of the instruction.")
                    CodeBlock(
                        """before:   401136:  48 89 e5        mov  %rsp,%rbp
after:    401136:  cc 89 e5        int3 ; (rest is garbage, never reached)
GDB keeps: { addr=0x401136, saved_byte=0x48 }"""
                    )
                    BodyText("But .text is read-only - how can it write? ptrace writes go through the KERNEL (access_process_vm() with FOLL_FORCE), which is allowed to ignore the page's write protection for a tracer. For a private file mapping the kernel does a COPY-ON-WRITE: the tracee gets its own private copy of that page. The executable on disk and other processes running the same binary are NOT affected.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Detail: by default GDB inserts its breakpoints into memory only when the program is RESUMED and removes them again when it stops ('set breakpoint always-inserted off'). That's why 'x/i' on a breakpoint address in GDB shows the original instruction, not int3.")
                }
            }
            item {
                SectionCard(title = "3. 0xCC or 0xCD? (INT3 vs INT 3)") {
                    BodyText("x86 has TWO encodings that reach vector 3:")
                    CodeBlock(
                        """CC       INT3      dedicated 1-byte breakpoint instruction
CD 03    INT 3     generic 'INT imm8' with imm8 = 3 (2 bytes)"""
                    )
                    BodyText("Debuggers always use 0xCC:")
                    BodyText("  - It is ONE byte, so it can replace the first byte of ANY instruction - even a 1-byte one (e.g. 'ret' = C3, 'push %rbp' = 55) - without touching the next instruction. 'CD 03' is two bytes; on a 1-byte instruction it would overwrite the start of the NEXT instruction, which may itself be a jump target.\n  - The CPU treats them slightly differently: Intel documents that INT3 is not quite a normal software interrupt (e.g. in virtual-8086 mode it is not IOPL-sensitive, and VMX reports it as a 'software exception' while 'CD 03' is a 'software interrupt').\n  - After 'CC' the saved RIP is breakpoint_address + 1. After 'CD 03' it would be + 2, and GDB's 'RIP - 1' logic would not recognise it.")
                    BodyText("(On Linux x86-64 user code CAN execute 'CD 03' - the IDT gate for vector 3 is user-accessible - and gets a SIGTRAP too; it's just not what debuggers plant. Other ints like 'CD 80' are a different story: that's the legacy 32-bit syscall gate.)")
                    BodyText("On ARM64 the equivalent is the 'BRK #imm' instruction (fixed 4 bytes, like every ARM64 instruction).")
                }
            }
            item {
                SectionCard(title = "4. Running Into the Breakpoint: Who Is the 'Interrupt Handler'?") {
                    BodyText("When the CPU executes 0xCC it raises exception #BP (vector 3). Strictly speaking this is not a hardware interrupt but a synchronous TRAP-class EXCEPTION - but it travels through the same IDT mechanism described in 'Interrupt Triggering': the CPU switches to the kernel stack, pushes SS/RSP/RFLAGS/CS/RIP and jumps to IDT[3].")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("KEY POINT: the handler is the KERNEL's, never GDB's. GDB has no code in the IDT and no code inside your process. The kernel handler turns the exception into a SIGNAL:")
                    CodeBlock(
                        """IDT[3] -> asm_exc_int3          (arch/x86/kernel/traps.c)
  exc_int3(regs)
    user_mode(regs)?  yes
    do_int3_user(regs)
      do_int3()?        -> uprobes/kprobes may claim it first
      do_trap(X86_TRAP_BP, SIGTRAP, "int3", ...)
        -> SIGTRAP queued to the current task (your program)
  return to user mode -> pending signal is processed"""
                    )
                    BodyText("Now ptrace kicks in. Because the task is TRACED, signal delivery is intercepted: instead of running a handler or dying ('Trace/breakpoint trap'), the tracee enters a 'signal-delivery-stop' and the kernel wakes the tracer:")
                    CodeBlock(
                        """ your program          CPU            kernel                     GDB
 ------------          ---            ------                     ---
 ... runs ...                                                 waitpid() sleeping
 int3 @0x401136 --->  #BP vector 3
                      save RIP=0x401137
                      jump IDT[3] ---> exc_int3()
                                       SIGTRAP -> tracee
                                       tracee is traced:
                                       STOP it, notify tracer -->
                                                               waitpid() returns:
                                                               WIFSTOPPED, SIGTRAP
                                                               PTRACE_GETREGS
                                                               RIP-1 = 0x401136 -> 'mine!'
                                                               RIP = 0x401136
                                                               restore byte 0x48
                                                               show prompt (gdb)"""
                    )
                    BodyText("So the 'handler' in a GDB session is really a three-step chain: CPU exception -> kernel trap handler (makes a SIGTRAP) -> ptrace stop -> GDB, a separate process woken from waitpid(). GDB then fixes RIP back by 1 so the program will re-execute the ORIGINAL instruction at the breakpoint address.")
                }
            }
            item {
                SectionCard(title = "5. Resuming: 'continue' Must Step Over the Breakpoint") {
                    BodyText("Problem: the breakpoint should stay active, but the original instruction at that address must still run. If GDB simply re-inserted 0xCC and continued, the program would trap on the same spot forever. GDB solves it with a STEP-OVER:")
                    CodeBlock(
                        """'continue' at breakpoint 0x401136:
 1. original byte 0x48 is in place, RIP = 0x401136
 2. PTRACE_SINGLESTEP          -> executes ONLY 'mov %rsp,%rbp'
 3. tracee stops again (SIGTRAP from single-step, see below)
 4. write 0xCC back at 0x401136 (+ insert all other breakpoints)
 5. PTRACE_CONT, signal = 0    -> runs freely until next trap"""
                    )
                    BodyText("Signal = 0 in PTRACE_CONT means 'suppress the SIGTRAP' - the program never sees it. (If the stop was caused by a REAL signal, e.g. SIGSEGV, GDB passes it on according to 'handle SIGSEGV pass/nopass'.)")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Multi-threaded twist: while the breakpoint is temporarily removed (step 1-4), ANOTHER thread could run through that address and miss it. In all-stop mode GDB avoids this by keeping the other threads stopped during the step-over. In non-stop mode it uses DISPLACED STEPPING: it copies the original instruction to a scratch area, single-steps the COPY there (fixing up RIP-relative addressing), and the 0xCC at the real address never has to be removed. GDB's default 'set displaced-stepping auto' uses it when non-stop mode is on and the target supports it.")
                }
            }
            item {
                SectionCard(title = "6. Single Step (stepi): The Trap Flag and #DB") {
                    BodyText("PTRACE_SINGLESTEP does NOT modify code. It uses a CPU feature: the TRAP FLAG (TF, bit 8 of RFLAGS).")
                    BodyText("  1. The kernel sets TF in the tracee's saved RFLAGS (user_enable_single_step()) and resumes it.\n  2. The CPU executes exactly ONE instruction.\n  3. Because TF=1, the CPU raises exception #DB (vector 1) AFTER that instruction, and sets DR6.BS ('single step') to say why.\n  4. IDT[1] -> exc_debug_user() in the kernel -> SIGTRAP -> ptrace stop -> GDB's waitpid() returns.\n  5. When the tracee is resumed normally (PTRACE_CONT), the kernel clears TF again.")
                    CodeBlock(
                        """(gdb) stepi
  ptrace(PTRACE_SINGLESTEP, pid, 0, 0)  -> kernel sets RFLAGS.TF
  one instruction runs
  #DB vector 1 -> exc_debug_user -> SIGTRAP -> stop
  waitpid() -> GDB prints new RIP"""
                    )
                    BodyText("ARM64 equivalent: the kernel enables MDSCR_EL1.SS (software step) and the CPU raises a 'Software Step' exception after one instruction.")
                }
            }
            item {
                SectionCard(title = "7. step / next / finish: Built From the Primitives") {
                    BodyText("The kernel only knows 'run' and 'run one instruction'. Source-level commands are GDB logic on top, using the DWARF LINE TABLE (address ranges -> source lines):")
                    BodyText("  - stepi / nexti: exactly one instruction (nexti steps over a 'call' using a temporary breakpoint after it).\n  - step: keep single-stepping until RIP leaves the address range of the current source line. If it enters a function WITH debug info, GDB stops at the first line after that function's prologue. Functions without debug info (e.g. libc) are stepped over.\n  - next: like step, but when a 'call' is entered GDB plants a temporary 'step-resume' breakpoint (0xCC) at the RETURN address and does PTRACE_CONT - so the whole callee runs at full speed instead of being single-stepped.\n  - finish: reads the return address from the current frame, plants a temporary breakpoint there, continues, and prints the return value (from RAX/XMM0) when it hits.\n  - until / advance: temporary breakpoints at the target location.")
                    CodeBlock(
                        """(gdb) next      on line 42:  x = compute(y);
  single-step ... 'call compute' -> now inside compute()
  temp breakpoint at return address in caller
  PTRACE_CONT  -> compute() runs freely
  temp bp hit  -> keep stepping until line 43 begins -> stop"""
                    )
                    BodyText("Optimisation: with gdbserver (remote debugging) GDB can ask the server for RANGE STEPPING ('vCont;r' packet): 'keep stepping while RIP is in [start,end)'. The stepping loop then runs inside gdbserver instead of costing a GDB<->server round-trip per instruction.")
                }
            }
            item {
                SectionCard(title = "8. Memory Breakpoints (Watchpoints): CPU Debug Registers") {
                    BodyText("'watch var' stops when a memory location is WRITTEN ('rwatch' = read, 'awatch' = read or write). Planting 0xCC is useless here - you don't know which instruction will touch the data. So GDB uses HARDWARE debug registers:")
                    CodeBlock(
                        """DR0..DR3   4 linear addresses to watch
DR6        status: which DRn fired, BS = single-step
DR7        control, per DRn:
             enable bit (L0..L3)
             R/W type: 00 = execute   01 = write
                       11 = read/write (10 = I/O, not for user)
             LEN:      1, 2, 4 or 8 bytes (aligned)"""
                    )
                    BodyText("How it flows:")
                    BodyText("  1. GDB writes the address/control values with PTRACE_POKEUSER at offsetof(struct user, u_debugreg[n]).\n  2. The kernel does not let user space write DR7 directly: ptrace_set_debugreg() validates the values and registers a perf 'hw_breakpoint' for the task. The registers are loaded into the CPU whenever that task is scheduled in (and removed when it's scheduled out).\n  3. Your program writes the variable -> the CPU raises #DB (vector 1) AFTER the writing instruction completes (data breakpoints are trap-like).\n  4. Kernel #DB handler -> SIGTRAP -> ptrace stop -> GDB reads DR6 to see WHICH watchpoint fired, compares the old and new values and prints 'Old value = ... New value = ...'.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Limits and fallbacks:")
                    BodyText("  - Only 4 debug registers -> at most 4 aligned 1/2/4/8-byte regions. A bigger struct uses several registers.\n  - x86 has no 'read-only' type, so rwatch is implemented with read/write type and GDB filters.\n  - If no hardware slot is available GDB falls back to a SOFTWARE watchpoint: single-step EVERY instruction and re-read the value after each one. Correct, but hundreds of times slower.\n  - 'hbreak' = hardware EXECUTION breakpoint (R/W = 00): stops on an instruction WITHOUT writing 0xCC - useful for code that is read-only to the debugger (ROM/flash on embedded targets) or not loaded yet.")
                    BodyText("ARM64: DBGBVR/DBGBCR (breakpoint) and DBGWVR/DBGWCR (watchpoint) register pairs; the number of pairs is implementation-defined (commonly 4-6 breakpoints and 2-4 watchpoints).")
                }
            }
            item {
                SectionCard(title = "9. Conditional Breakpoints: Where Is the Condition Checked?") {
                    BodyText("'break foo.c:42 if i == 1000' - the CPU knows nothing about the condition and nothing is injected into your program. The 0xCC traps EVERY time, and GDB evaluates the condition:")
                    CodeBlock(
                        """each time line 42 executes:
  int3 -> #BP -> kernel -> SIGTRAP -> ptrace stop
  GDB wakes from waitpid()
  GDB reads i via PTRACE_PEEKDATA (/proc/PID/mem)
  GDB evaluates  i == 1000   (in GDB's own process)
    false -> silent step-over + PTRACE_CONT   (user sees nothing)
    true  -> stop and show the prompt"""
                    )
                    BodyText("So 'where is the condition checked?' -> for a local program, in the DEBUGGER (GDB), after a full trap round-trip: exception, signal, two context switches, several ptrace calls and a step-over. In a hot loop that runs a million times, a conditional breakpoint can slow the program down by orders of magnitude. Ignore counts ('ignore 1 999') and hit counts work exactly the same way.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Ways to move the check closer to the program:")
                    BodyText("  - Remote targets (gdbserver): 'set breakpoint condition-evaluation' defaults to 'auto' - if the target supports it, GDB compiles the condition to AGENT EXPRESSION bytecode and downloads it with the breakpoint, and gdbserver evaluates it itself, reporting a stop only when it is true ('host' forces evaluation in GDB; conditions that use GDB-only data such as convenience variables always stay on the host). That saves the GDB<->gdbserver round-trip, but gdbserver is still a separate process using ptrace, so the trap and context switches remain.\n  - Fast tracepoints with the in-process agent (libinproctrace.so loaded into your program): the instruction is patched with a JUMP into the agent, and the condition runs INSIDE your process with no trap at all. This is the one GDB case where the condition is evaluated in-process (tracepoints collect data; they don't stop the program).\n  - Hardware watchpoint with a condition ('watch x if x > 5'): the debug registers filter by ADDRESS in hardware, but the value condition is still checked by GDB.\n  - Outside GDB: a uprobe + bpftrace/eBPF predicate - the condition runs in the KERNEL on each hit, with no ptrace stop and no tracer process involved.")
                    CodeBlock(
                        """# condition evaluated in-kernel by eBPF, no debugger stop
bpftrace -e 'uprobe:./prog:compute /arg0 == 1000/ { printf("hit\n"); }'"""
                    )
                }
            }
            item {
                SectionCard(title = "10. Summary") {
                    CodeBlock(
                        """action            mechanism              CPU event     handled by
----------------  ---------------------  ------------  ------------------------
break             write 0xCC (POKETEXT)  #BP  vec 3    kernel -> SIGTRAP ->
                                                       ptrace stop -> GDB
continue          step-over, re-insert,  -             kernel resumes tracee
                  PTRACE_CONT sig=0
stepi             RFLAGS.TF=1            #DB  vec 1    kernel -> SIGTRAP -> GDB
step / next       stepi loop + DWARF     #DB / #BP     GDB logic
                  line table + temp bps
finish            temp bp at ret addr    #BP  vec 3    GDB
watch (hw)        DR0-3 + DR7 write      #DB  vec 1    kernel (DR6) -> GDB
watch (sw)        stepi + compare        #DB  vec 1    GDB (very slow)
hbreak            DR0-3 + DR7 exec       #DB  vec 1    kernel -> GDB
break ... if C    0xCC + eval C in GDB   #BP every hit GDB (or gdbserver)"""
                    )
                }
            }
            item {
                SectionCard(title = "11. See Also") {
                    BodyText("  - Linux User Mode Programming -> Debugging with GDB: the commands themselves (break, watch, bt, x, info registers, core dumps, strace/ltrace).\n  - Interrupt Triggering (this hub): what the CPU does on IDT entry/exit - #BP and #DB use the same path.\n  - Kernel Mode -> kprobes: the kernel uses the SAME int3 trick on kernel code (with optimized kprobes replacing int3 with a jump). uprobes do it on user code - no debugger process involved.")
                }
            }
        }
    }
}
