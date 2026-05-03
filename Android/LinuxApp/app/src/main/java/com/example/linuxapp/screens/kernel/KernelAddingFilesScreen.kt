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
fun KernelAddingFilesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Adding Files to the Kernel",
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
                SectionCard(title = "The kbuild System") {
                    BodyText(
                        "The Linux kernel uses a build system called kbuild. Every kernel directory " +
                        "contains a Makefile that tells kbuild exactly which C files to compile and " +
                        "how to compile them. You never call gcc directly — you run make, and kbuild " +
                        "walks the tree reading each Makefile."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The three key obj- prefixes:")
                    CodeBlock(
                        """obj-y    += myfile.o   # always compiled into the kernel image
obj-m    += myfile.o   # compiled as a loadable kernel module
obj-$(CONFIG_MYOPT) += myfile.o  # depends on a Kconfig option:
                                  #   y → built-in, m → module, n → skip"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "The .o extension in the Makefile refers to the compiled object file. " +
                        "kbuild automatically knows to compile myfile.c into myfile.o — you do " +
                        "not list the .c file explicitly."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "Adding a File to an Existing Directory") {
                    BodyText(
                        "This is the simplest case. Find the Makefile already present in the " +
                        "directory and add one line. No other Makefile needs to change — kbuild " +
                        "already descends into this directory as part of the normal build."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Example: adding kernel/myhello.c unconditionally")
                    CodeBlock(
                        """# 1. Create the file:
kernel/myhello.c

# 2. Edit kernel/Makefile — add one line anywhere in the obj-y list:
obj-y += myhello.o

# That's it. Run:
make -j${"$"}(nproc)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Example: adding a file under a CONFIG_ guard")
                    CodeBlock(
                        """# kernel/Makefile:
obj-$(CONFIG_MY_HELLO) += myhello.o

# Now it is compiled only when CONFIG_MY_HELLO=y or =m
# (configured via Kconfig — see the Kconfig section below)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "If you want to place the file in an existing subsystem (e.g., fs/, mm/, " +
                        "drivers/char/), find that directory's Makefile and add the same line. " +
                        "Look at the surrounding entries for the naming convention used."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "Adding a New Subdirectory") {
                    BodyText(
                        "When adding a whole new directory, you need to (a) create a Makefile " +
                        "inside it and (b) tell the parent directory's Makefile to descend into it."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Example: adding drivers/mydriver/")
                    CodeBlock(
                        """# 1. Create the directory and your source files:
drivers/mydriver/mydriver.c
drivers/mydriver/mydriver.h

# 2. Create drivers/mydriver/Makefile:
obj-$(CONFIG_MY_DRIVER) += mydriver.o

# 3. Edit the parent Makefile (drivers/Makefile) — add:
obj-$(CONFIG_MY_DRIVER) += mydriver/
#                                    ^
#   Trailing slash = descend into this subdirectory"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "The trailing slash in obj-y += mydir/ is the kbuild convention that " +
                        "means 'recurse into this subdirectory and read its Makefile'. Without " +
                        "the slash it would look for a file named mydir.c."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Multiple source files in one directory:")
                    CodeBlock(
                        """# drivers/mydriver/Makefile:
obj-$(CONFIG_MY_DRIVER) += mydriver.o
mydriver-objs := core.o io.o irq.o

# kbuild links core.o + io.o + irq.o into mydriver.o
# This is called a composite object (or multi-file object)."""
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "Adding a Kconfig Option") {
                    BodyText(
                        "If you use obj-$(CONFIG_MY_DRIVER) += mydriver.o, you need to define " +
                        "that CONFIG_ symbol in a Kconfig file so users can enable/disable it " +
                        "via make menuconfig."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Step 1 — Create or edit the Kconfig in your directory:")
                    CodeBlock(
                        """# drivers/mydriver/Kconfig:
config MY_DRIVER
    tristate "My example driver"
    default n
    help
      Enable the example driver. Say M to build as a module,
      Y to build in, N to skip entirely.

# tristate = y/m/n choice (use 'bool' for y/n only)
# default n = disabled by default"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Step 2 — Source it from the parent Kconfig:")
                    CodeBlock(
                        """# drivers/Kconfig  (find a logical place to insert):
source "drivers/mydriver/Kconfig""""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Step 3 — Enable the option and build:")
                    CodeBlock(
                        """make menuconfig   # navigate to your option and enable it
# or set directly in .config:
echo "CONFIG_MY_DRIVER=y" >> .config && make oldconfig

make -j${"$"}(nproc)   # build"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Common Kconfig dependency keywords:")
                    CodeBlock(
                        """depends on NET           # only shown if CONFIG_NET=y
select CRYPTO_AES        # automatically enables CRYPTO_AES
default y if X86_64      # default y only on x86-64"""
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "Compiler Flags and Include Paths") {
                    BodyText(
                        "You can set per-file or per-directory compiler flags inside a kbuild Makefile:"
                    )
                    CodeBlock(
                        """# Extra flags for one file only:
CFLAGS_myfile.o := -O0 -g   # disable optimisation (useful for debugging)
CFLAGS_myfile.o += -DDEBUG_MODE=1

# Extra flags for all files in this directory:
ccflags-y := -I$(src)/include   # add an include path
ccflags-y += -DMY_DRIVER_VERSION=2"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "The $(src) variable expands to the directory containing the Makefile. " +
                        "Use it for include paths so out-of-tree builds work correctly."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Header files:")
                    CodeBlock(
                        """# Headers you create in include/linux/ are auto-found by the build.
# For private headers inside your driver directory, use:
ccflags-y += -I$(src)

# Then in your .c file:
#include "mydriver_private.h"   /* finds drivers/mydriver/mydriver_private.h */"""
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "Complete Example from Scratch") {
                    BodyText("Adding a new subsystem at kernel/hello/ that is always built in:")
                    CodeBlock(
                        """# Directory layout:
kernel/hello/
    Kconfig
    Makefile
    hello.c"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """# kernel/hello/Kconfig:
config KERNEL_HELLO
    bool "Kernel hello example"
    default n
    help
      A minimal example of adding code to the kernel."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """# kernel/hello/Makefile:
obj-$(CONFIG_KERNEL_HELLO) += hello.o"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """/* kernel/hello/hello.c */
#include <linux/init.h>
#include <linux/printk.h>

static int __init hello_init(void)
{
    pr_info("hello: kernel module loaded\n");
    return 0;
}

static void __exit hello_exit(void)
{
    pr_info("hello: kernel module unloaded\n");
}

/* Only needed if built-in (obj-y); not needed for modules */
core_initcall(hello_init);"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """# kernel/Makefile — add one line:
obj-$(CONFIG_KERNEL_HELLO) += hello/

# kernel/Kconfig — source the new Kconfig:
source "kernel/hello/Kconfig""""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Note the initcall level: core_initcall runs very early (after the core " +
                        "kernel, before devices). Use device_initcall for drivers, " +
                        "subsys_initcall for subsystems. For built-in code you can also just " +
                        "call your function directly from an existing init path."
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
