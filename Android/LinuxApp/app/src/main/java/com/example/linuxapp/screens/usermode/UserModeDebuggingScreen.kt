package com.example.linuxapp.screens.usermode

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
import com.example.linuxapp.screens.kernel.BodyText
import com.example.linuxapp.screens.kernel.CodeBlock
import com.example.linuxapp.screens.kernel.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserModeDebuggingScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Debugging with GDB",
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
                SectionCard(title = "What is GDB") {
                    BodyText("GDB (GNU Debugger) lets you run a program under controlled conditions: pause at any point, inspect memory and variables, step through code line by line, and diagnose crashes. It works on C, C++, Rust, and other languages that emit DWARF debug info.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Always compile with -g to include debug symbols. Optimisation can make stepping confusing, so use -O0 during debugging:")
                    CodeBlock(
                        """gcc -g -O0 -o myapp main.c

# Start gdb
gdb ./myapp

# Or attach to a running process
gdb -p <pid>

# Load a core dump
gdb ./myapp core"""
                    )
                }
            }
            item {
                SectionCard(title = "Running the Program") {
                    BodyText("Inside gdb, use these commands to start and control execution:")
                    CodeBlock(
                        """(gdb) run                   # start the program
(gdb) run arg1 arg2         # start with arguments
(gdb) run < input.txt       # redirect stdin

(gdb) kill                  # stop the running program
(gdb) quit                  # exit gdb"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("If the program crashes, gdb stops at the fault and shows the line. Type bt to see the call stack.")
                }
            }
            item {
                SectionCard(title = "Breakpoints") {
                    BodyText("A breakpoint pauses execution before a line or function is entered.")
                    CodeBlock(
                        """(gdb) break main            # break at function entry
(gdb) break myfile.c:42    # break at file:line
(gdb) break *0x4005e0      # break at address

# Shorthand
(gdb) b main

# List all breakpoints
(gdb) info breakpoints

# Disable / enable without deleting
(gdb) disable 1
(gdb) enable 1

# Delete a breakpoint by number
(gdb) delete 1
(gdb) delete                # delete all breakpoints

# Conditional breakpoint — pause only when expr is true
(gdb) break myfile.c:55 if count == 10
(gdb) condition 2 x > 100   # add condition to existing bp #2"""
                    )
                }
            }
            item {
                SectionCard(title = "Stepping Through Code") {
                    BodyText("Four commands control how you move through code after a pause:")
                    CodeBlock(
                        """(gdb) continue   # (c) resume until next breakpoint/signal

(gdb) next       # (n) execute current line, step OVER function calls

(gdb) step       # (s) execute current line, step INTO function calls

(gdb) finish     # run until the current function returns,
                 # then pause (step OUT)

(gdb) until 70   # run until line 70 (useful to skip loops)

# Pressing Enter repeats the last command — handy for
# stepping repeatedly."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Use next to stay at the same level; use step to dive into a called function; use finish to get back out of it.")
                }
            }
            item {
                SectionCard(title = "Inspecting Variables and Parameters") {
                    BodyText("When paused, you can read and modify any in-scope variable:")
                    CodeBlock(
                        """# Print a variable
(gdb) print x
(gdb) p *ptr            # dereference a pointer
(gdb) p arr[3]          # array element
(gdb) p/x val           # print in hex

# Print all local variables
(gdb) info locals

# Print function parameters of the current frame
(gdb) info args

# Automatically print an expression after every step
(gdb) display count
(gdb) display *node
(gdb) info display      # list auto-display expressions
(gdb) undisplay 1       # remove auto-display #1

# Modify a variable
(gdb) set variable x = 42

# Show the call stack
(gdb) backtrace         # (bt) full call stack
(gdb) bt 5              # top 5 frames only

# Switch to a different stack frame
(gdb) frame 2
(gdb) info args         # now shows frame 2's parameters"""
                    )
                }
            }
            item {
                SectionCard(title = "Watchpoints") {
                    BodyText("A watchpoint pauses the program whenever a variable is read or written — useful for finding who is corrupting a value.")
                    CodeBlock(
                        """# Pause when 'x' is written
(gdb) watch x

# Pause when 'x' is read
(gdb) rwatch x

# Pause when 'x' is either read or written
(gdb) awatch x

# Watch a memory address (e.g. a struct field via pointer)
(gdb) watch *((int *)0x6020a0)

# List watchpoints (they share numbering with breakpoints)
(gdb) info watchpoints

# Delete watchpoint
(gdb) delete 3"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Hardware watchpoints (on x86: up to 4) are fast. Software watchpoints work anywhere but slow the program significantly.")
                }
            }
            item {
                SectionCard(title = "Finding Memory Leaks with Valgrind") {
                    BodyText("Valgrind's Memcheck tool instruments every memory operation at runtime, detecting leaks, use-after-free, and reads of uninitialised memory. It runs your program as-is — no recompile needed — but compile with -g and -O0 for readable output.")
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """gcc -g -O0 -o myapp main.c

# Basic run — prints a leak summary at exit
valgrind ./myapp

# Full leak details — shows where each block was allocated
valgrind --leak-check=full ./myapp

# Also track where uninitialised memory came from
valgrind --leak-check=full --track-origins=yes ./myapp"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Example program with a deliberate leak:")
                    CodeBlock(
                        """// leak.c
#include <stdlib.h>
int main() {
    int *p = malloc(40);   // allocated but never freed
    p[0] = 1;
    return 0;              // leak happens here
}"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Valgrind output for the above:")
                    CodeBlock(
                        """==12345== HEAP SUMMARY:
==12345==   in use at exit: 40 bytes in 1 blocks
==12345==   total heap usage: 1 allocs, 0 frees, 40 bytes allocated
==12345==
==12345== 40 bytes in 1 blocks are definitely lost in loss record 1 of 1
==12345==    at 0x4C2FB0F: malloc (in .../vgpreload_memcheck.so)
==12345==    by 0x10865B: main (leak.c:4)
==12345==
==12345== LEAK SUMMARY:
==12345==    definitely lost: 40 bytes in 1 blocks
==12345==    indirectly lost: 0 bytes in 0 blocks
==12345==      possibly lost: 0 bytes in 0 blocks
==12345==    still reachable: 0 bytes in 0 blocks"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Leak categories:")
                    CodeBlock(
                        """definitely lost   — no pointer to this block exists; it's a real leak
indirectly lost   — pointed to only by a definitely-lost block
possibly lost     — pointer exists but not to the block's start
still reachable   — pointer exists at exit (often global state, not a bug)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Use a suppressions file to silence known false positives from system libraries:")
                    CodeBlock(
                        """valgrind --suppressions=my.supp --leak-check=full ./myapp

# Generate a suppressions template from current output:
valgrind --gen-suppressions=all ./myapp"""
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
