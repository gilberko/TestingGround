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
                        "kallsyms",
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
                        "other modules to use. These exported symbols appear in /proc/kallsyms " +
                        "regardless of kptr_restrict type (because they must be findable by " +
                        "the module loader). You can correlate with lsmod and /proc/modules."
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
