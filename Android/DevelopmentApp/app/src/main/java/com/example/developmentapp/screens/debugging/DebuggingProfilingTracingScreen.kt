package com.example.developmentapp.screens.debugging

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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.developmentapp.screens.BodyText
import com.example.developmentapp.screens.CodeBlock
import com.example.developmentapp.screens.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DebuggingProfilingTracingScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Debugging, Profiling And Tracing",
                        color      = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontSize   = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector        = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint               = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding).padding(horizontal = 16.dp)) {
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Overview") {
                    BodyText(
                        "There are three distinct approaches to understanding what a program is doing: " +
                        "Tracing (observing without a debugger), Profiling (measuring performance and " +
                        "resource usage), and Debugging (interactive control via a debugger). Choosing " +
                        "the right one depends on the nature of the problem."
                    )
                    BodyText(
                        "Tracing is best for timing-sensitive bugs where attaching a debugger would " +
                        "change the behavior. Profiling is best for finding bottlenecks and memory " +
                        "problems. Interactive debugging is best when you need to inspect state at a " +
                        "specific point in execution."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Tracing (Without a Debugger)") {
                    BodyText(
                        "Tracing means the program observes and reports its own behavior — without any " +
                        "external debugger attached. The most basic form is printing to the screen " +
                        "(printf, fmt.Println, logcat on Android). More structured forms use an API: " +
                        "OutputDebugString() on Windows (visible in debuggers and tools like DebugView), " +
                        "Event Tracing for Windows (ETW), or ftrace/perf_event on Linux."
                    )
                    BodyText(
                        "The key advantage of tracing is minimal timing interference. Attaching a " +
                        "debugger changes the process's scheduling, pauses execution at breakpoints, " +
                        "and alters interrupt frequency. A race condition or timing-sensitive bug can " +
                        "disappear entirely when a debugger is attached — this is sometimes called a " +
                        "Heisenbug (observing it changes it). Tracing lets the program run at full " +
                        "speed so the timing of the original scenario is preserved."
                    )
                    CodeBlock(
                        "// Go — basic trace print\n" +
                        "fmt.Printf(\"[TRACE] handler called: req=%v\\n\", req)\n\n" +
                        "// Windows — visible in DebugView without attaching a debugger\n" +
                        "OutputDebugString(L\"[TRACE] entering DPC handler\\n\");\n\n" +
                        "// Linux — write to ftrace ring buffer (from kernel/driver code)\n" +
                        "trace_printk(\"irq_handler: dev=%d status=0x%x\\n\", dev, status);"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Profiling") {
                    BodyText(
                        "Profiling runs the program and collects measurements to answer: where does " +
                        "time go, and where does memory go? The goal is to find bottlenecks (hot " +
                        "functions, cache misses, lock contention) and memory problems (excessive " +
                        "allocations, leaks, fragmentation)."
                    )
                    BodyText(
                        "Instrumentation profiling: the compiler (or a tool) injects counters and " +
                        "timers around function calls. Precise call counts and exact time per function " +
                        "are available, but the overhead is significant — the profiler itself affects " +
                        "performance. Examples: gprof (GCC), Go pprof with instrumented builds."
                    )
                    BodyText(
                        "Sampling profiling: an OS timer fires at a fixed frequency and captures the " +
                        "current call stack. Statistical — you see where the program spends most of " +
                        "its time, not every individual call. Very low overhead (< 1% typically). " +
                        "Examples: perf on Linux, Intel VTune, Apple Instruments, Go pprof in sampling " +
                        "mode. Sampling can be done without modifying the binary at all."
                    )
                    BodyText(
                        "Memory profiling records allocations over time to find which code paths " +
                        "allocate the most memory, detect leaks (memory that is allocated but never " +
                        "freed), and expose fragmentation. Tools: Valgrind Massif, Go pprof heap " +
                        "profile, Heaptrack."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Debugging With a Debugger") {
                    BodyText(
                        "An interactive debugger lets you pause execution at any point, inspect and " +
                        "modify memory, and step through code one instruction at a time. It can " +
                        "target a single process or an entire machine (kernel debugging)."
                    )
                    BodyText(
                        "Local vs remote: a local debugger runs on the same machine as the target. " +
                        "A remote debugger runs on a separate machine and communicates over a serial " +
                        "port, USB, or network connection. Remote debugging is common for embedded " +
                        "systems, mobile devices, and kernel debugging."
                    )
                    BodyText(
                        "How breakpoints work: the debugger replaces the instruction byte(s) at the " +
                        "target address with 0xCC — the INT 3 instruction (1 byte). It then registers " +
                        "itself with the OS as the debugging process for the target. When the CPU " +
                        "executes 0xCC, it raises interrupt #3. The interrupt handler notifies the OS, " +
                        "which notifies the registered debugger. The debugger restores the original " +
                        "byte, pauses the target, and waits for user input."
                    )
                    BodyText(
                        "From a breakpoint the debugger can: continue (resume until next breakpoint), " +
                        "single-step one instruction (sets the Trap Flag in EFLAGS/RFLAGS so the CPU " +
                        "raises a debug exception after each instruction), step into a function call, " +
                        "step over (run the call and break on return), or step out (run until the " +
                        "current function returns)."
                    )
                    BodyText(
                        "Memory and variables: the debugger can read and write any memory in the " +
                        "target process. With debug symbols (.pdb on Windows, DWARF on Linux) it can " +
                        "map addresses to variable names and types. In Release builds many variables " +
                        "are optimized away — held in registers, inlined, or eliminated entirely — so " +
                        "they may not be available even if they exist in source."
                    )
                    BodyText(
                        "Instruction pointer: the debugger can also modify RIP/EIP directly. This " +
                        "lets you rewind a few instructions to re-execute a section, or skip forward " +
                        "past code you do not want to run — useful for quickly testing a fix without " +
                        "rebuilding."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Kernel Debugging") {
                    BodyText(
                        "Debugging a driver or OS component requires a kernel debugger. On Windows " +
                        "this is WinDbg with KD (Kernel Debugger). On Linux it is KGDB. Because the " +
                        "kernel controls the scheduler and interrupt handlers, a breakpoint in the " +
                        "kernel freezes the entire machine — the OS cannot run the debugger UI on the " +
                        "same machine. Therefore a second machine (the host) runs the debugger while " +
                        "the target machine (or VM) runs the driver under test."
                    )
                    BodyText(
                        "VMs are very commonly used for kernel debugging. The VM acts as the target " +
                        "and the host machine runs WinDbg or KGDB. The hypervisor provides a virtual " +
                        "serial port or network adapter for the debug connection."
                    )
                    BodyText(
                        "When a VM hangs (kernel hang, deadlock, or bugcheck), the hypervisor can " +
                        "take a full memory dump of the VM — a snapshot of all RAM — without the VM " +
                        "cooperating. This dump, loaded into WinDbg with the correct kernel symbols, " +
                        "shows all running processes, all threads and their call stacks, kernel data " +
                        "structures, and lock ownership. A post-mortem analysis can often pinpoint the " +
                        "hanging driver or deadlocked lock without needing a live debug session at all."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Debugging and Analysis Tools") {
                    BodyText(
                        "Specialized tools can instrument or observe a program without requiring a " +
                        "full interactive debugger session. They often catch classes of bugs that " +
                        "regular debugging misses."
                    )
                    BodyText(
                        "Driver Verifier (Windows) — a kernel-mode tool built into Windows. It " +
                        "intercepts calls made by a selected driver and applies aggressive runtime " +
                        "checks: pool integrity, IRQL correctness, DMA buffer overruns, deadlock " +
                        "detection, and more. When a violation is detected it immediately bugchecks " +
                        "the system with a stop code that identifies the driver and the exact rule " +
                        "violated. Enabled via verifier.exe (select the driver by name); takes effect " +
                        "on next boot. Essential for Windows kernel driver development."
                    )
                    BodyText(
                        "Valgrind (Linux/macOS) — runs the target binary inside a synthetic CPU " +
                        "(dynamic binary instrumentation). The Memcheck tool detects heap corruption, " +
                        "use-after-free, reads of uninitialized memory, and memory leaks with full " +
                        "stack traces. Other tools: Callgrind (instruction-level profiling), Massif " +
                        "(heap usage over time), Helgrind (data-race detection). Runs ~10–50x slower " +
                        "because every instruction is instrumented. No recompilation needed."
                    )
                    BodyText(
                        "strace (Linux) — intercepts and logs every system call a process makes, " +
                        "using the ptrace kernel API. Shows the call name, all arguments, and the " +
                        "return value in real time. Useful for understanding what files a program " +
                        "opens, what network connections it makes, and where it fails — without " +
                        "source code. strace -p <pid> attaches to a running process. " +
                        "-e trace=file limits output to file-related calls."
                    )
                    BodyText(
                        "ltrace (Linux) — similar to strace but traces library function calls " +
                        "(libc, libpthread, etc.) instead of syscalls. Useful when the bug is in " +
                        "how a library is being called rather than in a syscall."
                    )
                    BodyText(
                        "perf (Linux) — kernel-integrated sampling profiler using hardware performance " +
                        "counters. Records CPU cycles, cache misses, branch mispredictions, and " +
                        "context switches with near-zero overhead. perf record captures a trace; " +
                        "perf report shows a hierarchical call tree. Can attach to a running process " +
                        "or profile the entire system."
                    )
                    BodyText(
                        "WinDbg / KD (Windows) — the primary debugger for both user-mode and " +
                        "kernel-mode work on Windows. Supports live debugging, post-mortem crash dump " +
                        "analysis (.dmp files from Task Manager or WER), and kernel debugging over " +
                        "serial/USB/network. The !analyze -v command automatically interprets a " +
                        "bugcheck or application crash and identifies the faulting module and call " +
                        "stack — often the first step when analyzing any Windows crash dump."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
