package com.example.linuxapp.screens.permissions

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
fun ElfFileFormatScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "ELF File Format",
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
                SectionCard(title = "What is ELF?") {
                    BodyText(
                        "ELF (Executable and Linkable Format) is the standard binary format on Linux and most Unix-like systems. " +
                        "It is used for four types of files:"
                    )
                    CodeBlock(
                        "Executables      — runnable programs (/bin/ls, your app)\n" +
                        "Shared objects   — dynamic libraries (.so files)\n" +
                        "Relocatable objs — compiler output before linking (.o files)\n" +
                        "Core dumps       — memory snapshots for post-mortem debugging"
                    )
                    BodyText(
                        "ELF replaced older formats like a.out (1988) and COFF. " +
                        "It is also used on embedded targets, Android (same format), and as the container for eBPF programs."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            item {
                SectionCard(title = "The ELF Header") {
                    BodyText(
                        "Every ELF file begins with a fixed-size ELF header (64 bytes on 64-bit). " +
                        "Key fields:"
                    )
                    CodeBlock(
                        "e_ident[0..3]  = 0x7f 'E' 'L' 'F'   (magic bytes)\n" +
                        "e_ident[4]     = 2                   (EI_CLASS: 64-bit)\n" +
                        "e_ident[5]     = 1                   (EI_DATA: little-endian)\n" +
                        "e_type         = ET_EXEC / ET_DYN / ET_REL\n" +
                        "e_machine      = EM_X86_64 (62) / EM_AARCH64 (183)\n" +
                        "e_entry        = 0x401050            (entry point VA)\n" +
                        "e_phoff        = 64                  (program header table offset)\n" +
                        "e_shoff        = 14728               (section header table offset)"
                    )
                    BodyText("Read it with:")
                    CodeBlock("readelf -h /bin/ls")
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            item {
                SectionCard(title = "Key Sections") {
                    BodyText(
                        "The section header table describes named regions of the file. " +
                        "Most important sections:"
                    )
                    CodeBlock(
                        ".text      — machine code (executable, read-only)\n" +
                        ".rodata    — read-only constants: string literals,\n" +
                        "             switch tables, vtables, float constants\n" +
                        ".data      — initialized global/static variables\n" +
                        "             (read-write, copied from file into RAM)\n" +
                        ".bss       — uninitialized globals (not stored in file!\n" +
                        "             just a size; kernel zero-fills at load time)\n" +
                        ".symtab    — full symbol table (stripped in release builds)\n" +
                        ".dynsym    — dynamic symbol table (kept; needed at runtime)\n" +
                        ".debug_*   — DWARF debug info (only in debug builds)"
                    )
                    BodyText("List all sections:")
                    CodeBlock("readelf -S /bin/ls")
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            item {
                SectionCard(title = "Executables vs Shared Objects") {
                    BodyText(
                        "The e_type field distinguishes how an ELF is used:"
                    )
                    CodeBlock(
                        "ET_EXEC  — traditional executable\n" +
                        "           linked at a fixed virtual address\n" +
                        "           e_entry is a fixed VA like 0x400000\n\n" +
                        "ET_DYN   — shared object OR PIE executable\n" +
                        "           position-independent; base VA chosen at load\n" +
                        "           modern compilers produce PIE by default"
                    )
                    BodyText(
                        "PIE (Position-Independent Executables) use ET_DYN so ASLR can randomize " +
                        "the load address. The kernel treats them like shared objects and picks a random base."
                    )
                    BodyText(
                        "Shared objects (.so) always use ET_DYN and must be compiled with -fPIC " +
                        "(Position-Independent Code). They use .got and .plt sections to redirect " +
                        "calls to symbols whose addresses are not known until load time."
                    )
                    CodeBlock(
                        "# .plt — Procedure Linkage Table\n" +
                        "#   each external function call goes through a stub here\n" +
                        "#   first call: dynamic linker resolves + patches .got\n" +
                        "#   subsequent calls: jump directly via .got (lazy binding)\n\n" +
                        "# .got — Global Offset Table\n" +
                        "#   holds runtime addresses of external functions/variables"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            item {
                SectionCard(title = "Dynamic Dependencies") {
                    BodyText(
                        "When a program needs functions from shared libraries, the .dynamic section " +
                        "records which .so files are required. The dynamic linker (ld-linux.so) reads " +
                        "these at startup and loads each dependency."
                    )
                    CodeBlock(
                        "$ readelf -d /bin/ls | grep NEEDED\n" +
                        " 0x0000000000000001 (NEEDED) Shared library: [libselinux.so.1]\n" +
                        " 0x0000000000000001 (NEEDED) Shared library: [libc.so.6]"
                    )
                    BodyText(
                        "The dynamic linker (/lib64/ld-linux-x86-64.so.2) is specified in the " +
                        "PT_INTERP program header. The kernel transfers control to the dynamic linker " +
                        "first; it loads all NEEDED libraries, resolves symbols, then jumps to e_entry."
                    )
                    CodeBlock("ldd /bin/ls    # show all transitive dependencies")
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            item {
                SectionCard(title = "Symbol Tables & Undefined Symbols") {
                    BodyText(
                        "Symbols are named entities: functions, global variables, labels. " +
                        "The dynamic symbol table (.dynsym) is the runtime-visible subset:"
                    )
                    CodeBlock(
                        "$ nm -D /bin/ls | head -8\n" +
                        "                 U __cxa_atexit@GLIBC_2.2.5   <- undefined\n" +
                        "                 U malloc@GLIBC_2.2.5         <- undefined\n" +
                        "0000000000004010 T main                        <- defined\n\n" +
                        "Binding:\n" +
                        "  STB_GLOBAL  — normal external symbol\n" +
                        "  STB_WEAK    — can be overridden by another definition\n" +
                        "  STB_LOCAL   — file-local (static in C)"
                    )
                    BodyText(
                        "Undefined symbols (U) are resolved by the dynamic linker at load time by " +
                        "searching all loaded .so files in order. This is why link order matters."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            item {
                SectionCard(title = "Entry Point & C Runtime Startup") {
                    BodyText(
                        "e_entry in the ELF header does not point to main(). It points to _start, " +
                        "a function provided by the C runtime (crt1.o for regular executables, " +
                        "Scrt1.o for PIE). The call chain looks like this:"
                    )
                    CodeBlock(
                        "kernel loads ELF, jumps to e_entry\n" +
                        "  └─ _start  (crt1.o / Scrt1.o)\n" +
                        "       sets up the stack (argc, argv, envp layout)\n" +
                        "       zeros .bss\n" +
                        "       calls __libc_start_main(main, argc, argv, ...)\n" +
                        "         └─ __libc_start_main  (glibc)\n" +
                        "              runs .init_array constructors (C++ globals)\n" +
                        "              calls main(argc, argv, envp)\n" +
                        "              calls exit(main's return value)\n" +
                        "                └─ runs atexit() handlers\n" +
                        "                   runs .fini_array destructors\n" +
                        "                   calls _exit() (raw syscall)"
                    )
                    BodyText(
                        "This means: global C++ object constructors run before main(), and their " +
                        "destructors run after main() returns. If you call exit() directly from a " +
                        "signal handler, destructors still run. _exit() skips them."
                    )
                    CodeBlock(
                        "# verify entry point\n" +
                        "readelf -h ./myapp | grep 'Entry point'\n\n" +
                        "# disassemble _start\n" +
                        "objdump -d ./myapp | grep -A 20 '<_start>'"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            item {
                SectionCard(title = "Useful Tools") {
                    CodeBlock(
                        "readelf -h file        # ELF header\n" +
                        "readelf -S file        # section headers\n" +
                        "readelf -l file        # program (segment) headers\n" +
                        "readelf -d file        # dynamic section (.dynamic)\n" +
                        "readelf --syms file    # symbol tables\n\n" +
                        "objdump -d file        # disassemble .text\n" +
                        "objdump -s -j .rodata file  # hex dump of .rodata\n\n" +
                        "nm file                # symbol table (with types)\n" +
                        "nm -D file             # dynamic symbols only\n\n" +
                        "ldd file               # shared library dependencies\n" +
                        "file file              # quick ELF type identification"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
