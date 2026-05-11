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
fun UserModeSharedObjectsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Shared Objects",
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
                SectionCard(title = "Static vs Dynamic Linking") {
                    BodyText("Linux supports both linking models, exactly like Windows (.lib/.dll → .a/.so):")
                    CodeBlock(
                        """Static library  (.a)   ←→  Windows .lib (static)
Shared object   (.so)  ←→  Windows .dll

Static linking:
  The linker copies all needed code from the .a file
  directly into your executable at link time.
  Result: larger binary, no runtime dependency,
  symbols are private to the binary.

Dynamic linking:
  The linker records which .so file is needed and
  which symbols to import. At runtime, ld.so (the
  dynamic linker) loads the .so into the process and
  resolves symbols.
  Result: smaller binary, shared code in RAM across
  processes, but the .so must be present at runtime."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Use static when: shipping a self-contained binary (containers, embedded). Use shared when: multiple programs share the same library, or you need runtime plugin loading.")
                }
            }
            item {
                SectionCard(title = "Building a Shared Object") {
                    BodyText("Two steps: compile with -fPIC (Position-Independent Code), then link with -shared.")
                    CodeBlock(
                        """/* mathlib.h */
int add(int a, int b);
int mul(int a, int b);

/* mathlib.c */
int add(int a, int b) { return a + b; }
int mul(int a, int b) { return a * b; }

# Step 1 — compile to PIC object file
gcc -fPIC -c mathlib.c -o mathlib.o

# Step 2 — link into a shared object
gcc -shared -o libmath.so mathlib.o

# With soname (recommended for versioning):
gcc -shared -Wl,-soname,libmath.so.1 \
    -o libmath.so.1.0 mathlib.o
ln -s libmath.so.1.0 libmath.so.1
ln -s libmath.so.1   libmath.so"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("-fPIC generates code that uses relative addressing so the .so can be loaded at any virtual address. Without it the linker will reject -shared on most architectures.")
                }
            }
            item {
                SectionCard(title = "Using a Shared Object") {
                    BodyText("Link your program against the .so with -l and -L, then make sure the loader can find it at runtime.")
                    CodeBlock(
                        """/* main.c */
#include "mathlib.h"
#include <stdio.h>
int main() {
    printf("%d\n", add(3, 4));
    return 0;
}

# Compile + link (libmath.so lives in current dir)
gcc main.c -L. -lmath -o main

# Tell the dynamic linker where to find libmath.so
export LD_LIBRARY_PATH=.:${'$'}LD_LIBRARY_PATH
./main     # → 7

# System-wide: copy to /usr/local/lib and run ldconfig
sudo cp libmath.so /usr/local/lib/
sudo ldconfig          # rebuilds /etc/ld.so.cache

# Inspect what a binary needs at runtime
ldd ./main
# linux-vdso.so.1
# libmath.so => /usr/local/lib/libmath.so
# libc.so.6  => /lib/x86_64-linux-gnu/libc.so.6

# Embed the search path in the binary (rpath):
gcc main.c -L. -lmath -Wl,-rpath,'${'$'}ORIGIN' -o main"""
                    )
                }
            }
            item {
                SectionCard(title = "Static Linking") {
                    BodyText("To build a static library (.a) and link it in:")
                    CodeBlock(
                        """# Create the archive
ar rcs libmath.a mathlib.o

# Link statically — copies mathlib code into the binary
gcc main.c -L. -lmath -static -o main_static

# Or mix: link libmath statically, rest dynamically
gcc main.c -L. -Wl,-Bstatic -lmath \
              -Wl,-Bdynamic -o main_mixed

# Check: no shared library dependencies
ldd main_static
#   not a dynamic executable"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Fully static binaries are portable but larger. musl libc is popular for static builds (smaller than glibc static).")
                }
            }
            item {
                SectionCard(title = "Runtime Loading with dlopen") {
                    BodyText("Load a .so at runtime without linking against it — the classic plugin pattern. Equivalent to Windows LoadLibrary/GetProcAddress.")
                    CodeBlock(
                        """#include <dlfcn.h>

int main() {
    /* Open the shared object */
    void *handle = dlopen("./libmath.so", RTLD_LAZY);
    if (!handle) {
        fprintf(stderr, "%s\n", dlerror());
        return 1;
    }

    /* Clear any previous error */
    dlerror();

    /* Look up the symbol */
    int (*add_fn)(int, int) = dlsym(handle, "add");
    const char *err = dlerror();
    if (err) { fprintf(stderr, "%s\n", err); return 1; }

    printf("3 + 4 = %d\n", add_fn(3, 4));

    dlclose(handle);
    return 0;
}

# Link against libdl
gcc main.c -ldl -o main_plugin

# RTLD flags:
# RTLD_LAZY   — resolve symbols on first use
# RTLD_NOW    — resolve all symbols immediately (safer)
# RTLD_GLOBAL — symbols visible to later dlopen() calls"""
                    )
                }
            }
            item {
                SectionCard(title = "Symbol Visibility") {
                    BodyText("By default every global function in a .so is exported (visible to callers). For large libraries, hide internal symbols to reduce the public API surface and improve load time.")
                    CodeBlock(
                        """/* Mark a function as part of the public API */
__attribute__((visibility("default")))
int add(int a, int b) { return a + b; }

/* Hide an internal helper */
__attribute__((visibility("hidden")))
static int helper(int x) { return x * 2; }

# Hide everything by default, then explicitly export:
gcc -fPIC -fvisibility=hidden -c mathlib.c
gcc -shared -o libmath.so mathlib.o

# Inspect exported symbols
nm -D libmath.so | grep ' T '   # T = text (code) symbol
objdump -T libmath.so"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Hiding symbols also prevents accidental symbol collisions when two .so files define the same internal name.")
                }
            }
            item {
                SectionCard(title = "Shared Object Constructor and Destructor") {
                    BodyText("Linux's equivalent of Windows DllMain is __attribute__((constructor)) and __attribute__((destructor)). Functions marked this way run automatically when the .so is loaded or unloaded.")
                    CodeBlock(
                        """DllMain reason         Linux equivalent
DLL_PROCESS_ATTACH  →  __attribute__((constructor))
DLL_PROCESS_DETACH  →  __attribute__((destructor))
DLL_THREAD_ATTACH   →  (no equivalent)
DLL_THREAD_DETACH   →  (no equivalent)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The constructor runs at program startup if the .so was linked in, or when dlopen() loads it. The destructor runs at exit, or when dlclose() drops the last reference. No parameters; return type must be void.")
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """/* mylib.c */
#include <stdio.h>

__attribute__((constructor))
static void my_init(void) {
    printf("mylib loaded\n");
    /* malloc, printf, open files -- all safe here */
}

__attribute__((destructor))
static void my_fini(void) {
    printf("mylib unloaded\n");
}

void do_work(void) { printf("doing work\n"); }"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """/* loader.c -- test with dlopen */
#include <dlfcn.h>
#include <stdio.h>
int main() {
    printf("before dlopen\n");
    void *h = dlopen("./libmy.so", RTLD_LAZY);
    printf("after dlopen\n");
    dlclose(h);
    printf("after dlclose\n");
    return 0;
}

gcc -fPIC -shared -o libmy.so mylib.c
gcc loader.c -ldl -o loader
./loader
# before dlopen
# mylib loaded
# after dlopen
# mylib unloaded
# after dlclose"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Thread attach/detach notifications do not exist in Linux. For per-thread cleanup, use pthread_key_create() with a destructor function — it fires when any thread exits and has a non-NULL value for that key.")
                }
            }
            item {
                SectionCard(title = "Constructor Priority and Ordering") {
                    BodyText("Pass an optional integer priority to control the order: __attribute__((constructor(N))). Lower number runs first during load. Destructors run in reverse order (LIFO). User range: 101–65535 (0–100 reserved for glibc/compiler).")
                    CodeBlock(
                        """__attribute__((constructor(200)))
static void init_b(void) { printf("init B (200)\n"); }

__attribute__((constructor(101)))
static void init_a(void) { printf("init A (101)\n"); }

/* Load output:
   init A (101)   <- lower number first
   init B (200)
*/

__attribute__((destructor(200)))
static void fini_b(void) { printf("fini B (200)\n"); }

__attribute__((destructor(101)))
static void fini_a(void) { printf("fini A (101)\n"); }

/* Unload output:
   fini B (200)   <- higher number destructor first (LIFO)
   fini A (101)
*/"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("__attribute__((constructor)) with no number runs after all numbered constructors. Across multiple .so files, load order determines constructor order — dependencies are always initialised before dependents.")
                }
            }
            item {
                SectionCard(title = "ELF Storage: .init_array and .fini_array") {
                    BodyText("Constructor function pointers are stored in the .init_array ELF section; destructor pointers in .fini_array. The dynamic linker (ld.so) iterates .init_array forward on load and .fini_array in reverse on unload.")
                    CodeBlock(
                        """# Show all sections -- look for .init_array / .fini_array
readelf -S libmy.so | grep -E 'init|fini'

# Dump raw pointer values stored in .init_array
objdump -s -j .init_array libmy.so

# Disassemble to see the actual constructor function
objdump -d libmy.so

# Confirm symbol is exported (T = text/code)
nm -D libmy.so | grep -E 'my_init|my_fini'"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("There are also legacy .init and .fini sections (a single _init()/_fini() function each) inherited from crti.o/crtn.o. They still appear in modern .so files but GCC-generated constructors go directly into .init_array, not into _init.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Loader lock comparison vs Windows:")
                    CodeBlock(
                        """                  Windows DllMain          Linux constructor
Lock held         Loader Lock              _dl_load_lock (glibc)
                  (CRITICAL_SECTION)       (recursive pthread_mutex)
Can call malloc?  DANGEROUS                YES -- safe
Can call printf?  DANGEROUS                YES -- safe
Recursive         Deadlocks                Allowed (recursive mutex)
  dlopen inside
Thread notify     DLL_THREAD_ATTACH/       None -- use
                    DETACH                   pthread_key_create"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Linux constructors are far less restricted than DllMain. The main deadlock risk is a lock-ordering inversion: thread A holds a user lock and calls dlopen; thread B is inside a constructor trying to acquire the same user lock. Keep constructors simple to avoid this.")
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
