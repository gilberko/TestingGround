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
fun ErrorInjectionScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Error Injection",
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
                SectionCard(title = "What Is Error Injection?") {
                    BodyText("Error injection is the ability to force a kernel function to return an error without modifying any code. It is used to test error-handling paths — for example, verifying that a driver correctly handles ENOMEM when memory allocation fails.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The kernel supports this through the ALLOW_ERROR_INJECTION() macro. A function marked with this macro is added to an allowlist that tells error injection tools: \"you may force this function to return a specific error.\"")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("This is separate from the CONFIG_FAULT_INJECTION subsystem (which injects errors into generic subsystems like slab or page allocation) — ALLOW_ERROR_INJECTION is per-function and works with eBPF and the fail_function debugfs interface.")
                }
            }
            item {
                SectionCard(title = "ALLOW_ERROR_INJECTION Macro") {
                    BodyText("Header: #include <linux/error-injection.h>")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Syntax — placed immediately after the function definition:")
                    CodeBlock("""
                        |ALLOW_ERROR_INJECTION(function_name, type);
                    """.trimMargin())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The type parameter declares what kind of return value can be injected:")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("• ERRNO — any negative errno (e.g. -ENOMEM, -EIO, -EPERM)")
                    BodyText("• ERRNO_NULL — negative errno or NULL pointer")
                    BodyText("• NULL — NULL pointer only")
                    BodyText("• TRUE — non-zero boolean")
                    BodyText("• ZERO — zero value")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The type is a contract between the function and the injector. The injector must honour the type — you cannot inject NULL into an ERRNO function. Internally, the macro creates a section entry (__error_injection_whitelist) that the kernel and tools read at runtime.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Example:")
                    CodeBlock("""
                        |int my_alloc(size_t size)
                        |{
                        |    void *p = kmalloc(size, GFP_KERNEL);
                        |    if (!p)
                        |        return -ENOMEM;
                        |    /* ... */
                        |    return 0;
                        |}
                        |ALLOW_ERROR_INJECTION(my_alloc, ERRNO);
                    """.trimMargin())
                }
            }
            item {
                SectionCard(title = "Defining an Injectable Function in Your Module") {
                    BodyText("A loadable kernel module can mark its own functions with ALLOW_ERROR_INJECTION. After insmod, the function appears in the runtime list. Here is a minimal example:")
                    CodeBlock("""
                        |// my_module.c
                        |#include <linux/module.h>
                        |#include <linux/error-injection.h>
                        |
                        |int my_module_process(int cmd)
                        |{
                        |    pr_info("my_module_process: cmd=%d\n", cmd);
                        |    return 0;
                        |}
                        |ALLOW_ERROR_INJECTION(my_module_process, ERRNO);
                        |EXPORT_SYMBOL(my_module_process);
                        |
                        |static int __init my_mod_init(void) { return 0; }
                        |static void __exit my_mod_exit(void) {}
                        |module_init(my_mod_init);
                        |module_exit(my_mod_exit);
                        |MODULE_LICENSE("GPL");
                    """.trimMargin())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("After insmod my_module.ko, run the runtime list command (next section) — my_module_process will appear.")
                }
            }
            item {
                SectionCard(title = "Viewing Supported Functions at Runtime") {
                    BodyText("The kernel exports the full error injection allowlist through debugfs:")
                    CodeBlock("""
                        |cat /sys/kernel/debug/error_injection/list
                        |
                        |# Output format:  function_name  type
                        |# Examples:
                        |# __alloc_pages_nodemask  ERRNO
                        |# filp_open               ERRNO_NULL
                        |# my_module_process       ERRNO
                    """.trimMargin())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Module functions appear in the list after insmod and disappear after rmmod. Requires CONFIG_FUNCTION_ERROR_INJECTION=y and debugfs mounted.")
                }
            }
            item {
                SectionCard(title = "What Error Can Be Injected?") {
                    BodyText("The injected error is constrained by the type declared in ALLOW_ERROR_INJECTION. For ERRNO, the injector may supply any negative errno — there is no further restriction on which errno values are valid. The type acts as a format contract, not an allowlist of specific error codes.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Examples of valid injections for each type:")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("• ERRNO → -ENOMEM, -EIO, -EPERM, -EINVAL, etc.")
                    BodyText("• NULL → (void *)0 / NULL pointer cast to the return type")
                    BodyText("• TRUE → any non-zero value (usually 1)")
                    BodyText("• ZERO → 0")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Injecting a mismatched type (e.g. passing -12 to a NULL-only function) is rejected by the kernel or eBPF verifier.")
                }
            }
            item {
                SectionCard(title = "Injecting via eBPF (bpf_override_return)") {
                    BodyText("From an eBPF kprobe program, use bpf_override_return() to force the probed function to return a specific value immediately. The function body does not execute — the return happens at the probe point.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Requirements: CONFIG_BPF_KPROBE_OVERRIDE=y and the target function must have ALLOW_ERROR_INJECTION(). The eBPF verifier checks this at program load time and rejects programs that attempt bpf_override_return on non-allowlisted functions.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Example — inject -ENOMEM into my_module_process:")
                    CodeBlock("""
                        |// inject_error.bpf.c
                        |#include <linux/bpf.h>
                        |#include <bpf/bpf_helpers.h>
                        |
                        |SEC("kprobe/my_module_process")
                        |int inject_error(struct pt_regs *ctx)
                        |{
                        |    bpf_override_return(ctx, -12); // -ENOMEM
                        |    return 0;
                        |}
                        |char LICENSE[] SEC("license") = "GPL";
                    """.trimMargin())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("For deeper coverage of bpf_override_return, including its limitations and comparison with fmod_ret, see the Security Possibilities and Modify Return Value screens under eBPF.")
                }
            }
            item {
                SectionCard(title = "Injecting via fail_function (User Mode / Debugfs)") {
                    BodyText("The fail_function subsystem (CONFIG_FAULT_INJECTION + CONFIG_FAULT_INJECTION_DEBUGFS) lets you inject errors from user space using only shell commands — no kernel module or eBPF program required.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The function must be in the ALLOW_ERROR_INJECTION list. Interface at /sys/kernel/debug/fail_function/:")
                    CodeBlock("""
                        |# Enable injection for a specific function
                        |echo "my_module_process" > \
                        |    /sys/kernel/debug/fail_function/inject
                        |
                        |# Set the return value to inject (-ENOMEM = -12)
                        |echo -12 > \
                        |    /sys/kernel/debug/fail_function/retval
                        |
                        |# Optional: set probability (100 = always, 1 = rarely)
                        |echo 100 > \
                        |    /sys/kernel/debug/fail_function/probability
                        |
                        |# Optional: inject only N times, then stop
                        |echo 5 > /sys/kernel/debug/fail_function/times
                        |
                        |# Disable injection
                        |echo "" > /sys/kernel/debug/fail_function/inject
                    """.trimMargin())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("This is the simplest way to test error handling paths without writing any code. Works for both kernel built-in functions and module functions (after insmod).")
                }
            }
            item {
                SectionCard(title = "Injecting from a Kernel Module") {
                    BodyText("From a kernel module, you can use a kprobe pre_handler with override_function_with_return() and regs_set_return_value() to force a target function to return immediately with a custom value.")
                    CodeBlock("""
                        |#include <linux/kprobes.h>
                        |
                        |static int pre(struct kprobe *p,
                        |               struct pt_regs *regs)
                        |{
                        |    regs_set_return_value(regs,
                        |                          (unsigned long)-EIO);
                        |    override_function_with_return(regs);
                        |    return 0;
                        |}
                        |
                        |static struct kprobe kp = {
                        |    .symbol_name = "my_module_process",
                        |    .pre_handler = pre,
                        |};
                        |
                        |static int __init myinj_init(void)
                        |{
                        |    return register_kprobe(&kp);
                        |}
                        |static void __exit myinj_exit(void)
                        |{
                        |    unregister_kprobe(&kp);
                        |}
                        |module_init(myinj_init);
                        |module_exit(myinj_exit);
                        |MODULE_LICENSE("GPL");
                    """.trimMargin())
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Note: unlike eBPF's bpf_override_return, the kernel-module approach using override_function_with_return() does NOT require the target to have ALLOW_ERROR_INJECTION — that check is only enforced by the eBPF verifier. From a raw kprobe module, you can call override_function_with_return() on any non-blacklisted function. Use this power carefully.")
                }
            }
            item {
                SectionCard(title = "Is There Overhead?") {
                    BodyText("Overhead exists only when a probe is actively attached. There is zero overhead when no injection is configured.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("• kprobe mechanism (basic): ~100–300 ns per call. Each invocation triggers an int3 exception, enters the probe handler, then single-steps the original instruction. The eBPF JIT path reduces this compared to an interpreted handler.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("• fail_function: adds a small per-call overhead for the probability/times check inside the kernel's fault injection framework — negligible compared to kprobe overhead.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("• Optimized kprobes (CONFIG_OPTPROBES): replaces int3 with a JMP to a trampoline when possible, reducing overhead significantly.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("For production performance testing, always remove the injection after debugging.")
                }
            }
            item {
                SectionCard(title = "Used by Test Frameworks") {
                    BodyText("Error injection is used extensively in the Linux kernel's own test infrastructure:")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("• fault injection framework (CONFIG_FAULT_INJECTION): subsystem-level injection for slab (failslab), page allocation (fail_page_alloc), I/O (fail_io_timeout, fail_make_request). These are higher-level than ALLOW_ERROR_INJECTION but use the same underlying mechanism.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("• KUnit tests: kernel unit tests can combine fault injection with kunit assertions to verify OOM handling paths, I/O error recovery, and allocation failure fallback code.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("• Chaos engineering: inject ENOMEM into kmalloc, EIO into block writes, or EPERM into VFS calls to simulate hostile conditions and find bugs in error paths that are rarely executed in normal use.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Example workflow — test that your module handles ENOMEM correctly:")
                    CodeBlock("""
                        |# 1. Load the module
                        |insmod my_module.ko
                        |
                        |# 2. Enable injection
                        |echo "my_module_process" > \
                        |    /sys/kernel/debug/fail_function/inject
                        |echo -12 > \
                        |    /sys/kernel/debug/fail_function/retval
                        |
                        |# 3. Trigger the code path — verify dmesg shows
                        |#    correct error handling
                        |
                        |# 4. Disable injection when done
                        |echo "" > /sys/kernel/debug/fail_function/inject
                    """.trimMargin())
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
