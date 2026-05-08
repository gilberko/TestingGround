package com.example.linuxapp.screens.kernel

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExportingKernelModuleApiScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Exporting Kernel Module API",
                        color = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
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
                .padding(innerPadding)
                .padding(horizontal = 12.dp)
        ) {
            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SectionCard(title = "EXPORT_SYMBOL vs EXPORT_SYMBOL_GPL") {
                    BodyText("A kernel module can make its functions and variables visible to other modules by placing EXPORT_SYMBOL or EXPORT_SYMBOL_GPL immediately after the definition:")
                    CodeBlock("""
/* In exporter.c */
int my_add(int a, int b)
{
    return a + b;
}
EXPORT_SYMBOL(my_add);          /* any module may use */

int secret_func(void)
{
    return 42;
}
EXPORT_SYMBOL_GPL(secret_func); /* GPL modules only  */""".trimIndent())
                    BodyText("EXPORT_SYMBOL — the symbol is placed in the module's __ksymtab section and becomes visible in the kernel's global symbol table. Any loaded module can use it regardless of license.")
                    BodyText("EXPORT_SYMBOL_GPL — same placement, but the kernel enforces that the importing module has MODULE_LICENSE(\"GPL\") (or a GPL-compatible variant). An attempt to insmod a non-GPL module that uses a _GPL symbol fails with \"loading out-of-tree module taints kernel\" and an explicit symbol error. This is the mechanism that enforces the GPL boundary for kernel APIs.")
                    BodyText("Where to place the macro: directly after the function or variable definition in the same .c file. It must be in the same compilation unit as the definition — not in a header.")
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SectionCard(title = "How the Importer Declares It") {
                    BodyText("The importing module does NOT #include the exporter's .c file and does NOT link against the exporter at compile time. Instead, you simply declare the symbol with extern (or put the declaration in a shared header):")
                    CodeBlock("""
/* Option A — inline extern declaration */
extern int my_add(int a, int b);

/* Option B — shared header (preferred) */
/* exporter_api.h */
#ifndef EXPORTER_API_H
#define EXPORTER_API_H
int my_add(int a, int b);
#endif

/* In importer.c */
#include "exporter_api.h"

static int __init importer_init(void)
{
    int result = my_add(3, 4);
    pr_info("importer: my_add(3,4) = %d\n", result);
    return 0;
}""".trimIndent())
                    BodyText("That is all. No special linker flags, no import libraries. The extern declaration tells the C compiler \"this symbol exists somewhere\" so it does not complain. The kernel resolves the actual address at insmod time by looking it up in the running kernel's symbol table.")
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Compilation & Linking") {
                    BodyText("Each module is compiled and linked independently into its own .ko file. There is no link-time dependency between the exporter.ko and importer.ko:")
                    CodeBlock("""
# Makefile — both modules are independent
obj-m += exporter.o
obj-m += importer.o""".trimIndent())
                    BodyText("At build time, importer.ko contains an unresolved symbol reference to my_add. This shows up in:")
                    CodeBlock("""
nm importer.ko | grep my_add
#  U my_add        ← 'U' = undefined (unresolved)

modinfo importer.ko
# depends:        (empty — no compile-time dep declared)""".trimIndent())
                    BodyText("At runtime (insmod), the kernel walks /proc/kallsyms (the live symbol table) and resolves U symbols against it. If my_add is found there, the reference is patched and the module loads. The live table includes both built-in kernel symbols and symbols exported by any currently loaded module.")
                    CodeBlock("""
cat /proc/kallsyms | grep my_add
# ffffffffc0123456 T my_add  [exporter]""".trimIndent())
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SectionCard(title = "insmod Without the Exporter Loaded") {
                    BodyText("If you insmod the importer when the exporter module is not loaded, every unresolved symbol produces an error and the insmod fails:")
                    CodeBlock("""
$ insmod importer.ko
insmod: ERROR: could not insert module importer.ko:
  Unknown symbol in module

# Kernel log (dmesg):
importer: Unknown symbol my_add (err -2)""".trimIndent())
                    BodyText("The return code is -ENOENT. The importer module is NOT partially loaded — it is fully rejected. You must load the exporter first:")
                    CodeBlock("""
$ insmod exporter.ko   # load exporter first
$ insmod importer.ko   # now succeeds""".trimIndent())
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SectionCard(title = "MODULE_SOFTDEP") {
                    BodyText("MODULE_SOFTDEP declares a load-order hint. It is respected by modprobe (the high-level loader) but ignored by raw insmod.")
                    CodeBlock("""
/* In importer.c */
MODULE_SOFTDEP("pre: exporter");""".trimIndent())
                    BodyText("\"pre: exporter\" means: load the module named 'exporter' before loading this one. modprobe reads this annotation and automatically loads the dependency first:")
                    CodeBlock("""
$ modprobe importer   # modprobe reads MODULE_SOFTDEP,
                      # loads exporter first, then importer""".trimIndent())
                    BodyText("Where it is declared: MODULE_SOFTDEP is a macro in <linux/module.h>. It expands to a string placed in the .modinfo ELF section of the .ko. modprobe parses this section with modinfo before loading.")
                    BodyText("What if the exporter does not exist at all? modprobe will try to load 'exporter', fail to find it, and depending on the configuration either skip it (soft dep) or refuse to load. A missing symbol at insmod time still causes a hard failure regardless of MODULE_SOFTDEP.")
                    BodyText("MODULE_SOFTDEP only affects ordering — it does NOT make the dependency optional. It is purely a hint to the loader, not a kernel enforcement mechanism.")
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Dynamic: symbol_get / symbol_put") {
                    BodyText("For truly optional dependencies — where the importer should work whether or not the exporter is loaded — the kernel provides symbol_get() and symbol_put(). These allow runtime lookup without any compile-time or load-time dependency.")
                    CodeBlock("""
#include <linux/module.h>

/* Declare the function pointer type */
typedef int (*my_add_fn_t)(int, int);

static int __init importer_init(void)
{
    my_add_fn_t fn;

    /* Look up the symbol in the running kernel.
       Returns the address or NULL if not exported/loaded.
       Also increments the exporter module's refcount. */
    fn = (my_add_fn_t) symbol_get(my_add);

    if (fn) {
        pr_info("importer: my_add(3,4) = %d\n", fn(3, 4));

        /* Decrement the exporter's refcount.
           The exporter can now be rmmod'd. */
        symbol_put(my_add);
    } else {
        pr_info("importer: exporter not loaded, skipping\n");
    }

    return 0;
}

static void __exit importer_exit(void) { }

module_init(importer_init);
module_exit(importer_exit);
MODULE_LICENSE("GPL");""".trimIndent())
                    BodyText("symbol_get(sym_name) — takes the symbol name as a macro argument (not a string), returns a void * (cast to the correct function pointer type), or NULL if the symbol is not currently exported by any loaded module. It also calls try_module_get() on the owning module, so that module cannot be unloaded while the pointer is held.")
                    BodyText("symbol_put(sym_name) — decrements the owning module's refcount. Must be called once for every successful symbol_get(). After symbol_put() the pointer must not be used.")
                    BodyText("Key difference from EXPORT_SYMBOL direct use: symbol_get/put imposes no load-time dependency. The importer.ko loads and runs even if the exporter is absent — it just skips the optional functionality. This is used for optional hardware support, plugin-style architectures, and inter-subsystem optional hooks.")
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
