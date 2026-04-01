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
fun LoadableKernelModuleScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Loadable Kernel Module",
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
                SectionCard(title = "What Is a Loadable Kernel Module?") {
                    BodyText("A Loadable Kernel Module (LKM) is a piece of object code that can be inserted into the running kernel at runtime — without rebooting or recompiling the kernel.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("LKMs live in kernel space and have full access to kernel internals. They are compiled separately from the kernel tree and produce a .ko (kernel object) file.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Common uses: device drivers, filesystem drivers, network protocols, debugging hooks.")
                }
            }
            item {
                SectionCard(title = "Module Structure") {
                    BodyText("Required headers:")
                    CodeBlock(
                        """#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/init.h>"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Every module needs an init function (called on load) and an exit function (called on unload):")
                    CodeBlock(
                        """static int __init hello_init(void)
{
    printk(KERN_INFO "Hello, kernel!\n");
    return 0;  /* 0 = success, negative = error */
}

static void __exit hello_exit(void)
{
    printk(KERN_INFO "Goodbye, kernel!\n");
}

module_init(hello_init);
module_exit(hello_exit);"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Module metadata macros:")
                    CodeBlock(
                        """MODULE_LICENSE("GPL");
MODULE_AUTHOR("Your Name");
MODULE_DESCRIPTION("A simple hello world LKM");
MODULE_VERSION("1.0");"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("MODULE_LICENSE(\"GPL\") is required to use most exported kernel symbols. Using a non-GPL license taints the kernel and restricts access to GPL-only symbols.")
                }
            }
            item {
                SectionCard(title = "__init, __exit, module_init, module_exit") {
                    BodyText("__init is a section attribute that marks a function as initialization code:")
                    CodeBlock("static int __init hello_init(void) { ... }")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("After the module is loaded and the init function has run, the kernel frees the memory occupied by __init functions. This saves RAM — the code is only needed once at load time.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("__exit similarly marks the cleanup function:")
                    CodeBlock("static void __exit hello_exit(void) { ... }")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("For drivers compiled directly into the kernel (not as modules), __exit code is discarded entirely at build time — a built-in driver can never be unloaded, so there's no point keeping its cleanup code.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("module_init() and module_exit() are macros that register your functions in special ELF sections:")
                    CodeBlock(
                        """module_init(hello_init);
/* Places a pointer to hello_init in the __initcall section.
   On module load, the kernel calls every function in this
   section in order. */

module_exit(hello_exit);
/* Places a pointer to hello_exit in the __exitcall section.
   Called when the module is unloaded via rmmod. */"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Without module_init/module_exit, the kernel would not know which functions to call. They are not optional.")
                }
            }
            item {
                SectionCard(title = "printk in Module Development") {
                    BodyText("printk() is the kernel's equivalent of printf(). It writes to the kernel ring buffer (viewable with dmesg).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Basic usage with log level:")
                    CodeBlock(
                        """printk(KERN_INFO "mymodule: loaded, value=%d\n", val);
printk(KERN_ERR  "mymodule: failed to allocate memory\n");
printk(KERN_WARN "mymodule: deprecated parameter used\n");"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Log levels (lower number = higher priority):")
                    CodeBlock(
                        """KERN_EMERG   "0"  — system is unusable (panic imminent)
KERN_ALERT   "1"  — immediate action required
KERN_CRIT    "2"  — critical hardware/software failure
KERN_ERR     "3"  — error conditions
KERN_WARNING "4"  — warning, something unexpected
KERN_NOTICE  "5"  — normal but significant event
KERN_INFO    "6"  — informational (use this most often)
KERN_DEBUG   "7"  — debug (only shown if debug enabled)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Convenience macros (preferred over raw printk):")
                    CodeBlock(
                        """pr_info("loaded successfully\n");
pr_err("failed: %d\n", ret);
pr_warn("unusual condition\n");
pr_debug("value is %d\n", x);   /* compiled out unless DEBUG set */
pr_notice("important event\n");"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Add a module-name prefix automatically with pr_fmt:")
                    CodeBlock(
                        """/* At the top of your .c file, before including linux/printk.h: */
#define pr_fmt(fmt) KBUILD_MODNAME ": " fmt

/* Now every pr_info/pr_err/etc automatically prefixes with your
   module name. dmesg output: "hello: loaded successfully" */"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Rate-limited printing (avoids spamming the log in loops):")
                    CodeBlock(
                        """pr_info_ratelimited("interrupt fired, count=%d\n", n);
/* Outputs at most once per 5 seconds by default */"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Common mistake: forgetting \\n at the end. Without it the message stays in the line buffer and may not appear until the next newline — or not at all.")
                }
            }
            item {
                SectionCard(title = "Module Parameters") {
                    BodyText("Modules can declare parameters that can be set at load time or changed at runtime via sysfs. Use module_param() to declare them:")
                    CodeBlock(
                        """#include <linux/moduleparam.h>

/* Declare a parameter: module_param(name, type, permissions) */
static int  my_count = 10;
static bool my_enable = true;
static char *my_name = "default";

module_param(my_count,  int,   0644);
module_param(my_enable, bool,  0644);
module_param(my_name,   charp, 0444);  /* charp = char pointer */

/* Describe each parameter (shown by modinfo): */
MODULE_PARM_DESC(my_count,  "Number of items to process");
MODULE_PARM_DESC(my_enable, "Enable the feature (default: true)");
MODULE_PARM_DESC(my_name,   "Device name string");"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Supported types: bool, int, long, short, uint, ulong, ushort, charp (char*), and array variants.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Permissions control visibility and writability in sysfs:")
                    CodeBlock(
                        """0     — not exposed in sysfs at all
0444  — readable by everyone, not writable
0644  — readable by all, writable by root
0600  — readable and writable by root only"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Passing parameters when loading with insmod:")
                    CodeBlock(
                        """sudo insmod mymodule.ko my_count=42 my_name="hello" my_enable=0

# Multiple params space-separated on the same command"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Using modprobe — set params in /etc/modprobe.d/:")
                    CodeBlock(
                        """# /etc/modprobe.d/mymodule.conf
options mymodule my_count=42 my_name=hello"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Exploring parameters via the VFS (sysfs) at runtime:")
                    CodeBlock(
                        """# List all parameters exposed for a loaded module:
ls /sys/module/mymodule/parameters/
# my_count  my_enable  my_name

# Read a parameter's current value:
cat /sys/module/mymodule/parameters/my_count
# 42

# Write a new value (if permission 0644 or 0600):
echo 100 | sudo tee /sys/module/mymodule/parameters/my_count

# Also visible via modinfo (before loading):
modinfo mymodule.ko
# parm: my_count:Number of items to process (int)
# parm: my_enable:Enable the feature (default: true) (bool)"""
                    )
                }
            }
            item {
                SectionCard(title = "Writing a Makefile") {
                    BodyText("The kernel uses its own build system (kbuild). Your Makefile delegates to it:")
                    CodeBlock(
                        """obj-m += hello.o

KDIR := /lib/modules/$(shell uname -r)/build
PWD  := $(shell pwd)

all:
\t$(MAKE) -C $(KDIR) M=$(PWD) modules

clean:
\t$(MAKE) -C $(KDIR) M=$(PWD) clean"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("obj-m tells kbuild to build hello.o as an out-of-tree module.")
                    BodyText("-C \$(KDIR) changes to the kernel source directory which contains the build rules.")
                    BodyText("M=\$(PWD) tells kbuild where your module source lives.")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("For a module from multiple source files:")
                    CodeBlock(
                        """obj-m += mymodule.o
mymodule-objs := main.o utils.o ops.o"""
                    )
                }
            }
            item {
                SectionCard(title = "Compiling") {
                    BodyText("Install kernel headers for your running kernel:")
                    CodeBlock("sudo apt install linux-headers-\$(uname -r)")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Build the module:")
                    CodeBlock("make")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Output files created:")
                    CodeBlock(
                        """hello.ko        ← the module itself
hello.o         ← intermediate object
hello.mod.c     ← generated glue code
hello.mod.o
Module.symvers  ← exported symbol versions
modules.order"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock("make clean")
                }
            }
            item {
                SectionCard(title = "Loading & Unloading") {
                    BodyText("Load directly from a .ko file:")
                    CodeBlock("sudo insmod hello.ko")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Load with parameters:")
                    CodeBlock("sudo insmod hello.ko my_count=5 my_name=\"test\"")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Unload (by name, no .ko extension):")
                    CodeBlock("sudo rmmod hello")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("List all loaded modules:")
                    CodeBlock("lsmod")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Show module metadata (license, author, params, dependencies):")
                    CodeBlock("modinfo hello.ko")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Load via modprobe (resolves dependencies, reads /etc/modprobe.d/):")
                    CodeBlock(
                        """sudo cp hello.ko /lib/modules/\$(uname -r)/extra/
sudo depmod
sudo modprobe hello"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("View kernel messages from printk:")
                    CodeBlock(
                        """dmesg | tail -20
dmesg -w        # follow in real time"""
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
