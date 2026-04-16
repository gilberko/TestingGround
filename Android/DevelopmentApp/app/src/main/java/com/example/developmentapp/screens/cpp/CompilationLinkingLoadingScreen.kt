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
fun CompilationLinkingLoadingScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "C/C++ — Compilation, Linking & Loading",
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

            // ── Compilation Units and Object Files ────────────────────────────
            item {
                SectionCard(title = "Compilation Units and Object Files") {
                    BodyText(
                        "Each .c or .cpp source file is a translation unit — the compiler processes it " +
                        "independently, with no knowledge of other source files. The compiler's output for " +
                        "each translation unit is an object file (.o on Linux/macOS, .obj on Windows)."
                    )
                    BodyText(
                        "An object file contains:\n" +
                        "  • Machine code for the functions defined in that source file\n" +
                        "  • A symbol table listing defined symbols (functions, global variables) and " +
                        "    undefined symbols (references to things declared but not defined here)\n" +
                        "  • Relocation entries — placeholders where addresses need to be filled in later"
                    )
                    CodeBlock(
                        "# Compile each source file separately (no linking yet)\n" +
                        "gcc -c main.c   -o main.o\n" +
                        "gcc -c utils.c  -o utils.o\n\n" +
                        "# Inspect symbols in an object file (Linux)\n" +
                        "nm main.o\n" +
                        "# U printf      <- undefined: referenced here, defined elsewhere\n" +
                        "# T main        <- defined (T = text/code section)\n" +
                        "# U add_numbers <- undefined: defined in utils.o"
                    )
                    BodyText(
                        "The preprocessor runs first (expanding #includes and macros), then the compiler " +
                        "translates the preprocessed source to assembly, and the assembler converts that " +
                        "to machine code in the object file. The -c flag tells GCC/Clang to stop after " +
                        "producing the object file, without invoking the linker."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── The Linker ────────────────────────────────────────────────────
            item {
                SectionCard(title = "The Linker") {
                    BodyText(
                        "The linker (ld on Linux, link.exe on Windows) combines object files and resolves " +
                        "symbols. For every undefined symbol in any object file, it finds the matching " +
                        "definition in another object file or library. If a symbol is still unresolved " +
                        "after processing all inputs, the linker reports an error."
                    )
                    CodeBlock(
                        "# Link object files into an executable\n" +
                        "gcc main.o utils.o -o myprogram\n\n" +
                        "# Link error example:\n" +
                        "# undefined reference to 'add_numbers'\n" +
                        "# (utils.o was not included on the command line)"
                    )
                    BodyText(
                        "The linker also:\n" +
                        "  • Assigns final virtual addresses to each section (code, data, BSS)\n" +
                        "  • Patches relocation entries with the resolved addresses\n" +
                        "  • Combines sections from all object files (all .text sections become one .text)\n" +
                        "  • Writes the final executable or library format (ELF on Linux, PE on Windows)"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Static vs Dynamic Linking ─────────────────────────────────────
            item {
                SectionCard(title = "Static vs Dynamic Linking") {
                    BodyText(
                        "Static linking: the linker copies library code directly into the executable at " +
                        "build time. The result is a self-contained binary with no external dependencies. " +
                        "Advantage: simpler deployment. Disadvantage: larger binary; a security fix in the " +
                        "library requires relinking and redeploying every program that uses it."
                    )
                    BodyText(
                        "Dynamic linking: the linker records a reference to the shared library but does " +
                        "not copy code. The actual library code is loaded at runtime from a separate file " +
                        "(.so on Linux, .dll on Windows). All programs sharing the same library use one " +
                        "copy in memory. A library update is picked up by all programs automatically " +
                        "(without relinking)."
                    )
                    CodeBlock(
                        "# Static link — include libmath.a directly in the binary\n" +
                        "gcc main.o -static -lmath -o myprogram_static\n\n" +
                        "# Dynamic link (default) — record dependency on libmath.so\n" +
                        "gcc main.o -lmath -o myprogram_dynamic"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Windows: PE Files and DLLs ────────────────────────────────────
            item {
                SectionCard(title = "Windows: PE Files and DLLs") {
                    BodyText(
                        "On Windows, both executables (.exe) and dynamic libraries (.dll) share the same " +
                        "file format: PE (Portable Executable). A PE is just a container for sections " +
                        "(.text for code, .data for initialized data, .rdata for read-only data, etc.) " +
                        "plus metadata headers."
                    )
                    BodyText(
                        "Key PE data structures for linking:\n\n" +
                        "Import Directory Table — lists every DLL this PE depends on. For each DLL, " +
                        "it lists the functions (by name or ordinal) that are imported. This is what " +
                        "the Windows loader reads to know which DLLs to load.\n\n" +
                        "Import Address Table (IAT) — an array of pointers, one per imported function. " +
                        "Initially filled with placeholder values; the loader overwrites each slot with " +
                        "the actual function address after loading the DLL.\n\n" +
                        "Export Table (in DLLs) — maps function names and ordinals to their addresses " +
                        "inside the DLL. This is what other PEs look up when resolving imports."
                    )
                    CodeBlock(
                        "// A DLL's exported function (MSVC)\n" +
                        "__declspec(dllexport) int add(int a, int b) { return a + b; }\n\n" +
                        "// Importing it in another binary\n" +
                        "__declspec(dllimport) int add(int a, int b);\n\n" +
                        "// Inspect PE imports with dumpbin (Visual Studio tools)\n" +
                        "dumpbin /imports myprogram.exe\n" +
                        "// Inspect exports of a DLL\n" +
                        "dumpbin /exports mylib.dll"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Windows Loading ───────────────────────────────────────────────
            item {
                SectionCard(title = "Windows: Loading") {
                    BodyText(
                        "When Windows launches a PE, the loader (ntdll.dll's LdrLoadDll and related " +
                        "functions) performs these steps:"
                    )
                    BodyText(
                        "  1. Map the PE — the kernel maps the PE file's sections into virtual memory " +
                        "at the preferred base address (or relocates if that address is taken).\n\n" +
                        "  2. Walk the Import Directory — for each DLL listed as a dependency, call " +
                        "LdrLoadDll recursively. This triggers the same process for that DLL's own " +
                        "imports, building a full dependency tree.\n\n" +
                        "  3. Patch the IAT — for each imported function, look it up in the DLL's Export " +
                        "Table and write the actual virtual address into the corresponding IAT slot. " +
                        "All calls through the IAT now jump to the correct function.\n\n" +
                        "  4. Run DllMain (for DLLs) — each loaded DLL's DllMain is called with " +
                        "DLL_PROCESS_ATTACH to allow initialization.\n\n" +
                        "  5. Transfer control — execution begins at the PE's entry point."
                    )
                    BodyText(
                        "Runtime dynamic loading: LoadLibrary(\"mylib.dll\") loads a DLL on demand and " +
                        "returns a handle. GetProcAddress(handle, \"FuncName\") retrieves a function " +
                        "pointer by name, bypassing the static import mechanism entirely. This is how " +
                        "plugin systems work."
                    )
                    CodeBlock(
                        "// Runtime loading (Windows)\n" +
                        "HMODULE lib = LoadLibrary(L\"mylib.dll\");\n" +
                        "typedef int (*AddFn)(int, int);\n" +
                        "AddFn add = (AddFn)GetProcAddress(lib, \"add\");\n" +
                        "int result = add(3, 4);   // 7\n" +
                        "FreeLibrary(lib);"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Linux: ELF Files and Shared Objects ───────────────────────────
            item {
                SectionCard(title = "Linux: ELF Files and Shared Objects") {
                    BodyText(
                        "On Linux, both executables and shared libraries use the ELF (Executable and " +
                        "Linkable Format). Shared libraries have the .so extension (Shared Object) and " +
                        "are the Linux equivalent of Windows DLLs."
                    )
                    BodyText(
                        "Key ELF structures for dynamic linking:\n\n" +
                        ".dynamic section — a table of tags describing the file's dynamic linking needs: " +
                        "DT_NEEDED entries list required shared libraries (by soname, e.g. libc.so.6); " +
                        "DT_SYMTAB points to the symbol table; DT_STRTAB to the string table.\n\n" +
                        ".dynsym — dynamic symbol table listing imported and exported symbols.\n\n" +
                        ".plt (Procedure Linkage Table) — a stub for each imported function. The first " +
                        "call to a PLT stub triggers the dynamic linker to resolve the symbol (lazy " +
                        "binding). Subsequent calls go directly to the resolved address cached in the GOT.\n\n" +
                        ".got.plt (Global Offset Table) — holds the resolved function addresses after " +
                        "lazy binding. Initially each entry points back into the PLT resolver."
                    )
                    CodeBlock(
                        "# Inspect shared library dependencies\n" +
                        "ldd myprogram\n" +
                        "#   linux-vdso.so.1\n" +
                        "#   libc.so.6 => /lib/x86_64-linux-gnu/libc.so.6\n\n" +
                        "# Inspect dynamic symbols\n" +
                        "readelf -d myprogram       # .dynamic section\n" +
                        "readelf --syms myprogram   # symbol table\n" +
                        "objdump -d -j .plt myprogram  # PLT stubs"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Linux Loading ─────────────────────────────────────────────────
            item {
                SectionCard(title = "Linux: Loading") {
                    BodyText(
                        "When the Linux kernel executes an ELF, it reads the PT_INTERP program header " +
                        "to find the dynamic linker path (typically /lib64/ld-linux-x86-64.so.2). The " +
                        "kernel maps both the ELF and the dynamic linker into memory, then transfers " +
                        "control to the dynamic linker."
                    )
                    BodyText(
                        "The dynamic linker (ld.so) then:\n\n" +
                        "  1. Reads DT_NEEDED entries from the .dynamic section and loads each required " +
                        ".so file (recursively resolving their own dependencies). Library search order: " +
                        "RPATH/RUNPATH embedded in the binary, LD_LIBRARY_PATH environment variable, " +
                        "/etc/ld.so.cache, then standard paths (/lib, /usr/lib).\n\n" +
                        "  2. Symbol resolution — for each undefined symbol in the executable or loaded " +
                        ".so files, finds the definition in another loaded library. By default this uses " +
                        "lazy binding via the PLT/GOT mechanism: the GOT entry is patched on first call.\n\n" +
                        "  3. Applies relocations — patches addresses in the loaded segments.\n\n" +
                        "  4. Runs initialization functions — calls .init_array constructors for each " +
                        "library, then the executable's own constructors.\n\n" +
                        "  5. Transfers control to the executable's entry point (_start, which calls main)."
                    )
                    BodyText(
                        "Runtime dynamic loading (equivalent to Windows LoadLibrary):\n" +
                        "dlopen(\"libfoo.so\", RTLD_LAZY) loads a .so at runtime; " +
                        "dlsym(handle, \"func_name\") retrieves a symbol by name."
                    )
                    CodeBlock(
                        "// Runtime loading (Linux)\n" +
                        "#include <dlfcn.h>\n" +
                        "void *lib = dlopen(\"libfoo.so\", RTLD_LAZY);\n" +
                        "typedef int (*AddFn)(int, int);\n" +
                        "AddFn add = (AddFn)dlsym(lib, \"add\");\n" +
                        "int result = add(3, 4);   // 7\n" +
                        "dlclose(lib);"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Key Differences ───────────────────────────────────────────────
            item {
                SectionCard(title = "Windows vs Linux: Key Differences") {
                    BodyText(
                        "Format: Both use a single format for executables and shared libraries — PE on " +
                        "Windows (.exe/.dll), ELF on Linux (executable/.so)."
                    )
                    BodyText(
                        "Import resolution:\n" +
                        "  • Windows: IAT is patched eagerly at load time (all imports resolved before " +
                        "the entry point runs).\n" +
                        "  • Linux: PLT/GOT uses lazy binding by default (symbols resolved on first call), " +
                        "reducing startup time. Set LD_BIND_NOW=1 or link with -Wl,-z,now to force eager."
                    )
                    BodyText(
                        "DLL/soname identity:\n" +
                        "  • Windows PE import entries reference DLL by filename (e.g. KERNEL32.DLL).\n" +
                        "  • ELF DT_NEEDED entries reference the soname (e.g. libc.so.6), which is the " +
                        "logical name embedded in the .so, separate from its filename on disk."
                    )
                    BodyText(
                        "Runtime loading:\n" +
                        "  • Windows: LoadLibrary / GetProcAddress / FreeLibrary\n" +
                        "  • Linux:   dlopen / dlsym / dlclose  (from libdl, link with -ldl)"
                    )
                    BodyText(
                        "Symbol visibility:\n" +
                        "  • Windows: symbols are private by default; use __declspec(dllexport) to export.\n" +
                        "  • Linux: symbols in .so are public by default; use -fvisibility=hidden and " +
                        "__attribute__((visibility(\"default\"))) to control what is exported."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
