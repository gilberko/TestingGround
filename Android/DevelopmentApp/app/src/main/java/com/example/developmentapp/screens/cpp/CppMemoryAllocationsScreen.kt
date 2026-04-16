package com.example.developmentapp.screens.cpp

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
fun CppMemoryAllocationsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "C/C++ — C Memory Allocations",
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
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            item { Spacer(Modifier.height(12.dp)) }

            // ── malloc, calloc, realloc, free ─────────────────────────────────
            item {
                SectionCard(title = "malloc, calloc, realloc, free") {
                    BodyText(
                        "The four C dynamic memory functions are declared in <stdlib.h>. They manage " +
                        "memory on the heap — memory that persists beyond the function call that " +
                        "allocated it, until explicitly freed."
                    )
                    CodeBlock(
                        "#include <stdlib.h>\n\n" +
                        "// malloc — allocate n bytes, uninitialized (contents are garbage)\n" +
                        "void *ptr = malloc(sizeof(int) * 10);   // 40 bytes\n" +
                        "if (!ptr) { /* handle allocation failure */ }\n\n" +
                        "// calloc — allocate n elements of size bytes, zero-initialized\n" +
                        "int *arr = (int *)calloc(10, sizeof(int));  // 40 bytes, all zeros\n\n" +
                        "// realloc — resize an existing allocation\n" +
                        "// Returns new pointer (may be different from old); old ptr is invalid after\n" +
                        "// If it returns NULL, the old pointer is still valid — do NOT overwrite it\n" +
                        "int *bigger = (int *)realloc(arr, sizeof(int) * 20);\n" +
                        "if (bigger) { arr = bigger; }   // update pointer only on success\n\n" +
                        "// free — release memory; passing NULL is a no-op (safe)\n" +
                        "free(arr);\n" +
                        "arr = NULL;   // good practice: prevent use-after-free"
                    )
                    BodyText(
                        "Always cast the return value of malloc/calloc in C++ (void* does not implicitly " +
                        "convert). In C, the cast is optional but some prefer it for clarity. " +
                        "malloc returns NULL on failure — always check before using the pointer."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── The C Runtime Library ─────────────────────────────────────────
            item {
                SectionCard(title = "The C Runtime Library") {
                    BodyText(
                        "malloc and friends live in the C runtime library (CRT). On Linux this is libc " +
                        "(glibc on most distros, musl on Alpine/embedded). On Windows it is the Microsoft " +
                        "CRT (msvcrt.dll or the newer ucrtbase.dll)."
                    )
                    BodyText(
                        "The CRT implements a heap allocator in user space. It manages a pool of memory " +
                        "obtained from the OS in large chunks, then subdivides it into the smaller " +
                        "allocations you request. The allocator tracks which blocks are free (typically " +
                        "using free-lists of various size classes) and merges adjacent free blocks to " +
                        "reduce fragmentation."
                    )
                    BodyText(
                        "The CRT asks the OS for more memory when its pool runs low — but it does this " +
                        "in large increments (pages or runs of pages), not one byte at a time. Calling " +
                        "malloc(8) does not necessarily make a system call; most of the time it simply " +
                        "returns a pointer into the existing pool."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Under the Hood: Linux ─────────────────────────────────────────
            item {
                SectionCard(title = "Under the Hood: Linux") {
                    BodyText(
                        "On Linux, glibc's malloc uses two OS mechanisms depending on allocation size:"
                    )
                    BodyText(
                        "Small allocations (< 128 KB by default): the heap segment is extended using " +
                        "the brk / sbrk system call. brk moves the program break — the boundary between " +
                        "the data segment and unallocated memory. Moving it up expands the heap; moving " +
                        "it down releases memory back to the OS. glibc manages this as a contiguous arena."
                    )
                    BodyText(
                        "Large allocations (>= 128 KB): glibc calls mmap(MAP_ANONYMOUS | MAP_PRIVATE) " +
                        "to request a new block of virtual memory directly from the kernel. When freed, " +
                        "the block is returned with munmap rather than kept in the pool. This avoids " +
                        "fragmentation for large objects."
                    )
                    CodeBlock(
                        "// You can observe this with strace on Linux:\n" +
                        "// strace ./myprogram 2>&1 | grep -E 'brk|mmap|munmap'\n\n" +
                        "// Small malloc — may see: brk(0x...)  (extending the heap)\n" +
                        "// Large malloc — may see: mmap(NULL, 2097152, ...)  (new region)\n" +
                        "// Large free   — may see: munmap(0x..., 2097152)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Under the Hood: Windows ───────────────────────────────────────
            item {
                SectionCard(title = "Under the Hood: Windows") {
                    BodyText(
                        "On Windows, the CRT uses the Win32 Heap API. Each process has a default heap " +
                        "created at startup (returned by GetProcessHeap()). The CRT calls HeapAlloc and " +
                        "HeapFree on this heap for malloc/free. The NT heap manager in turn uses " +
                        "VirtualAlloc to obtain memory from the OS in multiples of the page size (4 KB)."
                    )
                    BodyText(
                        "VirtualAlloc is the lowest-level Windows memory function: it reserves or " +
                        "commits virtual address ranges. VirtualFree returns them. The NT heap manager " +
                        "calls VirtualAlloc in large chunks and sub-allocates from those regions, " +
                        "similar to how glibc uses mmap."
                    )
                    CodeBlock(
                        "// Win32 heap API (bypasses CRT, useful for DLL boundary safety)\n" +
                        "HANDLE heap = GetProcessHeap();\n" +
                        "void *p = HeapAlloc(heap, HEAP_ZERO_MEMORY, 1024);\n" +
                        "HeapFree(heap, 0, p);\n\n" +
                        "// Direct virtual memory (page-granularity)\n" +
                        "void *page = VirtualAlloc(NULL, 4096, MEM_COMMIT | MEM_RESERVE,\n" +
                        "                          PAGE_READWRITE);\n" +
                        "VirtualFree(page, 0, MEM_RELEASE);"
                    )
                    BodyText(
                        "Important: do not mix allocators across DLL boundaries on Windows. If DLL A " +
                        "allocates with malloc from its CRT and DLL B calls free (its own CRT), you get " +
                        "heap corruption if the two DLLs link different CRT versions. Use one shared CRT " +
                        "(/MD flag) or allocate and free within the same module."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Common Pitfalls ───────────────────────────────────────────────
            item {
                SectionCard(title = "Common Pitfalls") {
                    BodyText(
                        "Memory leak: allocating but never freeing. Use tools like Valgrind (Linux) or " +
                        "AddressSanitizer (-fsanitize=address) to detect leaks at runtime."
                    )
                    BodyText(
                        "Double free: calling free twice on the same pointer corrupts the allocator's " +
                        "internal structures and can lead to security vulnerabilities. Set the pointer " +
                        "to NULL after freeing — free(NULL) is a no-op, so a second free becomes safe."
                    )
                    BodyText(
                        "Use after free: reading or writing through a pointer after free has been called. " +
                        "The memory may have been reallocated to another object, causing subtle data " +
                        "corruption or crashes far from the actual bug."
                    )
                    BodyText(
                        "realloc pitfall: realloc may return NULL if it cannot expand the allocation. " +
                        "Never write ptr = realloc(ptr, new_size) — if realloc returns NULL, you lose " +
                        "the original pointer and cannot free the old allocation."
                    )
                    CodeBlock(
                        "// WRONG\n" +
                        "ptr = realloc(ptr, new_size);  // if NULL returned, ptr is lost\n\n" +
                        "// CORRECT\n" +
                        "void *tmp = realloc(ptr, new_size);\n" +
                        "if (tmp) { ptr = tmp; }\n" +
                        "else { /* handle failure; ptr is still valid */ }"
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
