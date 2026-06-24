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
fun KallsymsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Symbols and kallsyms",
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
                SectionCard(title = "What Is /proc/kallsyms") {
                    BodyText(
                        "/proc/kallsyms is a virtual file that exports the kernel's complete symbol " +
                        "table to user space. The name stands for 'kernel all symbols'. It is " +
                        "generated dynamically when read — there is no file on disk."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Each line has three (or four) columns:")
                    CodeBlock(
                        """address          type  name           [module]
ffffffff81234560  T    schedule
ffffffff82a001a0  R    sys_call_table
ffffffff81c00000  D    init_task
ffffffffc0401000  t    my_init_function  [my_module]"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Symbol type letters:")
                    CodeBlock(
                        """T / t   text (code) — T=global, t=local/static
D / d   initialized data — D=global, d=local
B / b   BSS (zero-initialized data)
R / r   read-only data (.rodata)
W / w   weak symbol
A       absolute (fixed address)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "The symbol table content is baked into the kernel image at build time. " +
                        "Module symbols are added dynamically when the module is loaded."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "What It Is Used For") {
                    BodyText("kallsyms has several important use cases:")
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """Kernel debugging
  → Decode addresses in oops/panic call traces to function names
  → addr2line needs vmlinux; kallsyms works on a live system

kprobes
  → Attach a probe to any kernel function by name without
    knowing its address at compile time

eBPF
  → Resolve kernel function pointers; BPF verifier uses BTF +
    kallsyms to verify type-safe kprobe attachments

LKM development
  → Look up unexported symbols by address via kallsyms_lookup_name()
    (see caveats in the later section)

ftrace and perf
  → Both use kallsyms internally to name the functions they trace

Crash analysis
  → /proc/kallsyms from a running system helps map addresses
    from a crash dump to symbols"""
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "How to Access It") {
                    BodyText("From the shell:")
                    CodeBlock(
                        """# View the first few symbols:
cat /proc/kallsyms | head

# Find sys_call_table:
grep sys_call_table /proc/kallsyms

# Find exactly one symbol by name (whole-word match):
grep -w "T schedule" /proc/kallsyms

# Count all symbols (typically 100,000-200,000):
sudo cat /proc/kallsyms | wc -l

# See all symbols from a loaded module:
grep "\[my_module\]" /proc/kallsyms"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("From a C program:")
                    CodeBlock(
                        """FILE *f = fopen("/proc/kallsyms", "r");
char addr[32], type[4], name[256];
while (fscanf(f, "%s %s %s%*[^\n]\n",
              addr, type, name) == 3) {
    /* parse each symbol */
}
fclose(f);"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "From an LKM: before Linux 5.7 you could call kallsyms_lookup_name() " +
                        "directly. After 5.7 it is no longer exported — see the LKM section below."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "Why Addresses Are Zeroed") {
                    BodyText(
                        "The kptr_restrict sysctl controls whether /proc/kallsyms shows real " +
                        "kernel addresses or zeros. It prevents an unprivileged attacker from " +
                        "learning kernel addresses that would help them bypass KASLR."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """# Read the current setting:
cat /proc/sys/kernel/kptr_restrict

# Values:
#   0 — all users see real addresses
#   1 — root + CAP_SYSLOG see real; others see 0000000000000000
#   2 — everyone sees zeros, even root (hardened distros)

# Temporarily allow real addresses (restore afterwards!):
sudo sysctl kernel.kptr_restrict=0"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Default on Ubuntu/Debian: 1. Default on some hardened distros: 2."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Important: kptr_restrict=1 requires the CAP_SYSLOG capability, not just " +
                        "root UID. If sudo drops capabilities before running cat, you may still " +
                        "see zeros even as root. Use 'sudo sysctl kernel.kptr_restrict=0' to " +
                        "globally unlock, or run the reading program with CAP_SYSLOG."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "How kallsyms Is Built") {
                    BodyText(
                        "The symbol table is embedded directly into the kernel image at compile time, " +
                        "not stored as a separate file. The process:"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """Build step                 What happens
----------                 ------------
1. Link vmlinux (pass 1)   Partial kernel binary with all symbols
2. nm vmlinux              List all symbols + addresses
3. scripts/kallsyms        Processes nm output:
                           • Compresses symbol names (run-length
                             encoding on common substrings)
                           • Generates kallsyms_names[],
                             kallsyms_addresses[],
                             kallsyms_num_syms[]
4. Link vmlinux (pass 2)   Final binary includes the compressed
                           symbol arrays as kernel data

At runtime: reading /proc/kallsyms triggers kallsyms_show_value()
which decompresses names and checks kptr_restrict per symbol."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Because the addresses are baked in at link time, they reflect KASLR " +
                        "offsets only at runtime — /proc/kallsyms always shows the actual " +
                        "runtime address (subject to kptr_restrict)."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "Kernel AND Module Symbols") {
                    BodyText(
                        "Yes — /proc/kallsyms contains both kernel symbols and symbols from " +
                        "loaded kernel modules. Module symbols are added dynamically when " +
                        "insmod/modprobe loads the module, and removed when rmmod unloads it."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Module symbol example:")
                    CodeBlock(
                        """ffffffffc0401000 t my_init_function [my_module]
ffffffffc0402000 t my_cleanup_function [my_module]
ffffffffc0403000 r my_config_table [my_module]"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "The [module_name] suffix in the fourth column identifies the owning " +
                        "module. Symbols without this suffix belong to the base kernel."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "EXPORT_SYMBOL and EXPORT_SYMBOL_GPL mark symbols as available for " +
                        "other modules to use (see the dedicated section below). Note that " +
                        "kptr_restrict and EXPORT_SYMBOL are unrelated mechanisms: kptr_restrict " +
                        "only gates what a userspace reader sees when it reads /proc/kallsyms — " +
                        "it never affects the kernel's own in-kernel symbol resolution. That's why " +
                        "insmod/modprobe can always resolve EXPORT_SYMBOL'd dependencies at load " +
                        "time no matter how kptr_restrict is set; the module loader runs entirely " +
                        "in kernel space and never goes through the restricted read path. You can " +
                        "correlate exported symbols with lsmod and /proc/modules."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "EXPORT_SYMBOL — What Gets Exported") {
                    BodyText(
                        "EXPORT_SYMBOL(name) and EXPORT_SYMBOL_GPL(name) are macros placed at " +
                        "file scope, immediately after the function or variable they apply to. " +
                        "They add an entry to a special symbol table (ksymtab) that the module " +
                        "loader consults when resolving symbols other modules reference."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """int my_global = 42;
EXPORT_SYMBOL(my_global);

void my_function(void) { ... }
EXPORT_SYMBOL_GPL(my_function);"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "What CAN be exported: anything with a real linker symbol and external " +
                        "(non-static) linkage — functions, and global data objects: plain global " +
                        "variables, arrays, structs/struct instances, function pointers, even " +
                        "const globals placed in .rodata."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "What CANNOT be exported: static (file-local) functions or variables — " +
                        "they have no external symbol to point at; #define macros and typedefs — " +
                        "purely compile-time constructs, no runtime symbol exists for them at " +
                        "all. The macro itself must also sit at file/global scope, not inside a " +
                        "function body."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "EXPORT_SYMBOL vs EXPORT_SYMBOL_GPL: mechanically identical. The GPL " +
                        "variant additionally requires the importing module to declare a " +
                        "GPL-compatible MODULE_LICENSE — enforced at build time by modpost, not " +
                        "at runtime."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Important: EXPORT_SYMBOL only controls whether OTHER modules can " +
                        "resolve the symbol via the normal load-time linking mechanism. It does " +
                        "NOT control whether the symbol shows up in /proc/kallsyms — see the " +
                        "previous section: insmod adds every symbol from a module, exported or " +
                        "not (that's why lowercase t/d/r module-local symbols show up too). " +
                        "Exporting is about cross-module linkability, not kallsyms visibility."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "Two Modules Exporting the Same Symbol") {
                    BodyText(
                        "What if special_a.ko and special_b.ko each define and " +
                        "EXPORT_SYMBOL() a function called special_func? Only the first one to " +
                        "load wins — the second insmod is rejected outright."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """$ insmod special_a.ko
$ insmod special_b.ko
insmod: ERROR: could not insert module
  special_b.ko: Exec format error

$ dmesg | tail -1
special_b: exports duplicate symbol
  special_func (owned by special_a)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Under the hood, load_module() calls verify_exported_symbols(), which " +
                        "walks every symbol the loading module exports and calls find_symbol() " +
                        "to see if a symbol with that name is already visible — owned by the " +
                        "core kernel or by any currently loaded module. If it finds one, the " +
                        "load fails immediately with -ENOEXEC (\"Exec format error\"), before " +
                        "special_b's module_init() ever runs. special_a keeps the symbol; " +
                        "special_b never gets linked into the kernel at all."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "find_symbol()'s search order — core kernel ksymtab first, then loaded " +
                        "modules from most-recently-loaded to least-recently-loaded (new modules " +
                        "are added to the head of the modules list) — is what would decide a " +
                        "'winner' if a collision were ever allowed to resolve. In practice it " +
                        "never gets that far: the duplicate-export check above fires first, so " +
                        "two modules can never actually coexist while both claiming the same " +
                        "exported name."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Build time vs load time: if both modules are built together as part of " +
                        "the same kernel source tree (sharing Module.symvers), modpost catches " +
                        "the collision even earlier, at link time. Independent out-of-tree " +
                        "modules built separately have no shared Module.symvers, so this is only " +
                        "ever caught when you actually insmod both into the same running kernel."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "kallsyms_lookup_name() in LKMs") {
                    BodyText(
                        "kallsyms_lookup_name(name) returns the address of any kernel symbol by " +
                        "name — including unexported ones. This is useful for accessing internal " +
                        "kernel functions from an LKM."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Before Linux 5.7: the function was EXPORT_SYMBOL'd. LKMs could call it " +
                        "directly with no special setup."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "After Linux 5.7 (commit by Christoph Hellwig): un-exported. Standard " +
                        "workaround — use a kprobe on kallsyms_lookup_name itself to capture " +
                        "its address, then call it via function pointer:"
                    )
                    CodeBlock(
                        """#include <linux/kprobes.h>

typedef unsigned long (*kallsyms_lookup_name_t)(const char *name);
static kallsyms_lookup_name_t my_kallsyms_lookup_name;

static int resolve_kallsyms_lookup_name(void) {
    struct kprobe kp = {
        .symbol_name = "kallsyms_lookup_name"
    };
    int ret = register_kprobe(&kp);
    if (ret < 0) return ret;
    my_kallsyms_lookup_name =
        (kallsyms_lookup_name_t)kp.addr;
    unregister_kprobe(&kp);
    return 0;
}

/* Usage: */
static int __init mymod_init(void) {
    resolve_kallsyms_lookup_name();
    unsigned long addr =
        my_kallsyms_lookup_name("sys_call_table");
    pr_info("sys_call_table at %lx\n", addr);
    return 0;
}"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Alternative: if you only need to probe a function (not get its address " +
                        "as a pointer), set .symbol_name directly on the kprobe and let the " +
                        "kernel resolve it for you — no kptr workaround needed."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "kallsyms_lookup() — Lookup by Address") {
                    BodyText(
                        "kallsyms_lookup() is the mirror image of kallsyms_lookup_name() — " +
                        "given an address, it returns the symbol name plus its size, offset " +
                        "within the symbol, and owning module name (if any):"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """const char *kallsyms_lookup(
    unsigned long addr,
    unsigned long *symbolsize,
    unsigned long *offset,
    char **modname,
    char *namebuf);"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Like kallsyms_lookup_name(), it is NOT exported — an LKM cannot call " +
                        "it directly. Resolving it requires the same kprobe-capture trick shown " +
                        "above (register_kprobe with .symbol_name = \"kallsyms_lookup\", read " +
                        "kp.addr, then unregister_kprobe)."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "In practice you rarely need to do this yourself. The kernel already " +
                        "exports thin wrappers around kallsyms_lookup() for the common case of " +
                        "formatting an address for printing:"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """sprint_symbol(buf, addr)            EXPORT_SYMBOL_GPL
  -> "function_name+0x1a/0x40 [module]"

sprint_symbol_no_offset(buf, addr)  EXPORT_SYMBOL_GPL
  -> "function_name [module]"

sprint_symbol_build_id(buf, addr)   EXPORT_SYMBOL_GPL
  -> like sprint_symbol, plus build ID"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "So: name -> address has no exported path at all (kprobe trick " +
                        "mandatory). Address -> name has an exported, ready-made formatter — " +
                        "only reach for raw kallsyms_lookup() + the kprobe trick if you need " +
                        "the size/offset/modname as separate fields rather than one formatted " +
                        "string."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "symbol_get() and symbol_put()") {
                    BodyText(
                        "symbol_get(name) and symbol_put(name) are a safer alternative to " +
                        "kallsyms_lookup_name() for one specific case: getting a pointer to a " +
                        "symbol exported by a module that may or may not currently be loaded, " +
                        "without creating a hard build-time dependency."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """typeof(&some_gpl_function) fn =
    symbol_get(some_gpl_function);

if (fn) {
    fn(...);
    symbol_put(some_gpl_function);
}"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Under the hood, symbol_get(x) expands to __symbol_get(\"x\"), which " +
                        "calls find_symbol() to locate the symbol and its owning module, then " +
                        "pins that module by calling strong_try_module_get() on it — " +
                        "incrementing its reference count so it cannot be rmmod'd while you " +
                        "hold the reference. symbol_put(x) calls module_put() to release that " +
                        "pin."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Restriction (since Linux 6.5, 2023): symbol_get() only works on " +
                        "EXPORT_SYMBOL_GPL symbols — it explicitly rejects plain EXPORT_SYMBOL " +
                        "ones with a \"failing symbol_get of non-GPLONLY symbol\" warning. This " +
                        "was tightened specifically to stop proprietary modules from " +
                        "re-exporting GPL-only kernel internals through a thin GPL wrapper " +
                        "module. Before 6.5, it worked on any exported symbol."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Yes — symbol_get() always references the owning module. That " +
                        "reference is the entire point of the API: it's the difference between " +
                        "this and a raw kallsyms-style lookup (see next section)."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "Why Unpinned Lookups Are Dangerous") {
                    BodyText(
                        "kallsyms_lookup_name(), kallsyms_lookup(), and reading /proc/kallsyms " +
                        "all hand you a bare address. None of them take a reference on the " +
                        "owning module — there is no refcount increment anywhere in that path."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "That is dangerous if the symbol belongs to a loadable module rather " +
                        "than the core kernel: if you resolve the address once and call through " +
                        "it later, nothing stops that module from being rmmod'd in between. The " +
                        "memory backing that address can be freed and reused, so the call " +
                        "becomes a jump into freed memory — a textbook use-after-free, and a " +
                        "reliable way to crash the kernel."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "symbol_get() exists precisely to close this gap: it locates the " +
                        "symbol AND pins the module in the same step, so the address it hands " +
                        "you is guaranteed valid until you call symbol_put(). The tradeoff is " +
                        "the EXPORT_SYMBOL_GPL-only restriction, and that it only searches " +
                        "symbols currently registered in the kernel/module symbol tables — it " +
                        "cannot find static/internal symbols, only exported ones."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Rule of thumb: if the symbol is EXPORT_SYMBOL_GPL'd and you're calling " +
                        "it more than once or holding the pointer beyond a single immediate " +
                        "use, prefer symbol_get()/symbol_put(). Reach for the " +
                        "kallsyms_lookup_name() + kprobe trick only when there is no other way " +
                        "to get the address (the symbol isn't exported, or you only need a " +
                        "one-shot read of a symbol you know lives in the core kernel image, " +
                        "which can never be unloaded) — and even then, treat it as a " +
                        "debugging/research technique rather than something to ship in a " +
                        "production driver."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "Examples") {
                    BodyText("Common shell one-liners:")
                    CodeBlock(
                        """# 1. Find sys_call_table (shows zeros without root / kptr_restrict=0)
grep sys_call_table /proc/kallsyms
# → ffffffff82a001a0 R sys_call_table

# 2. Find a specific function (global text symbol)
grep -w "T do_sys_openat2" /proc/kallsyms

# 3. Exact match on the name column with awk
awk '${"$"}3 == "sys_call_table"' /proc/kallsyms

# 4. All symbols from a loaded module
grep "\[my_module\]" /proc/kallsyms

# 5. Count total symbols
wc -l /proc/kallsyms
# → 152347 /proc/kallsyms  (typical range: 100k-200k)

# 6. Show only exported (EXPORT_SYMBOL) kernel functions
grep " T " /proc/kallsyms | head -20"""
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
