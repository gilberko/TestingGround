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
fun AlternativeModuleCommScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Alt. Module-to-Module Comm.",
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
                SectionCard(title = "The Approach: Open a Device File From Kernel Code") {
                    BodyText("One kernel module can communicate with another by opening the second module's device file from within kernel context — exactly the same path that userspace uses. The caller gets a struct file *, then calls file operations (ioctl, read, write) through it, and closes it when done.")
                    BodyText("This works because the same VFS machinery that handles userspace open/ioctl/close is also callable from kernel context. The key functions are:")
                    CodeBlock("""
/* Open a device file from kernel context.
   Returns struct file * or ERR_PTR on error. */
struct file *filp_open(const char *filename,
                       int flags, umode_t mode);

/* Send an ioctl through the struct file.
   Runs in process context (not interrupt). */
long vfs_ioctl(struct file *file,
               unsigned int cmd, unsigned long arg);

/* Close the file and release resources. */
int filp_close(struct file *filp, fl_owner_t id);""".trimIndent())
                    BodyText("For read/write operations use kernel_read() and kernel_write() — the same functions used for filp_open-based file I/O elsewhere in the kernel.")
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Full Example") {
                    BodyText("Target module — a simple char device that responds to an ioctl:")
                    CodeBlock("""
/* target.c — the device being called */
#include <linux/module.h>
#include <linux/fs.h>
#include <linux/cdev.h>
#include <linux/uaccess.h>

#define TARGET_IOCTL_GREET  _IO('T', 1)

static int target_open(struct inode *i, struct file *f) { return 0; }
static int target_release(struct inode *i, struct file *f) { return 0; }

static long target_ioctl(struct file *f,
                         unsigned int cmd, unsigned long arg)
{
    if (cmd == TARGET_IOCTL_GREET) {
        pr_info("target: greeted from another module!\n");
        return 0;
    }
    return -EINVAL;
}

static struct file_operations target_fops = {
    .owner          = THIS_MODULE,
    .open           = target_open,
    .release        = target_release,
    .unlocked_ioctl = target_ioctl,
};

/* standard cdev setup — major/minor registration omitted */""".trimIndent())
                    BodyText("Caller module — opens the device, calls the ioctl, closes it:")
                    CodeBlock("""
/* caller.c — the module that calls the device */
#include <linux/module.h>
#include <linux/fs.h>

#define TARGET_IOCTL_GREET  _IO('T', 1)

static int __init caller_init(void)
{
    struct file *f;
    long ret;

    f = filp_open("/dev/target", O_RDWR, 0);
    if (IS_ERR(f)) {
        pr_err("caller: cannot open /dev/target: %ld\n",
               PTR_ERR(f));
        return PTR_ERR(f);
    }

    ret = vfs_ioctl(f, TARGET_IOCTL_GREET, 0);
    if (ret)
        pr_err("caller: ioctl failed: %ld\n", ret);
    else
        pr_info("caller: ioctl succeeded\n");

    filp_close(f, NULL);
    return 0;
}

static void __exit caller_exit(void) { }

module_init(caller_init);
module_exit(caller_exit);
MODULE_LICENSE("GPL");""".trimIndent())
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Problems With This Approach") {
                    BodyText("1. No permission enforcement — filp_open() from kernel context bypasses the normal DAC (file permission) checks. The kernel code runs with full privilege; no uid/gid check applies. If the device file has mode 0600, a userspace process cannot open it, but kernel code can.")
                    BodyText("2. Wrong architectural layer — a kernel module calling another module through a userspace-facing API is backwards. The right way is to use EXPORT_SYMBOL for a direct function call, a notifier chain, a shared data structure, or Netlink. The /dev interface exists for userspace, not for kernel-to-kernel calls.")
                    BodyText("3. Requires /dev node to exist — the device file must be present in the filesystem at the time of the call. In early boot or initramfs environments it may not be. EXPORT_SYMBOL-based calls have no such requirement.")
                    BodyText("4. ioctl argument passing is awkward from kernel — vfs_ioctl passes arg as an unsigned long. For ioctls that take a pointer (e.g. _IOWR), the called code may run copy_from_user(), which will fail because the \"user\" address is actually a kernel address. You would need to use kernel_write/kernel_read instead, or the callee must be aware it is being called from kernel and use memcpy.")
                    BodyText("Despite the problems, this pattern is occasionally used when a module must interact with a driver whose source it cannot modify and which has no exported API — treating it as a true black box accessible only through its device interface.")
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Reference Counting: Module Refcount") {
                    BodyText("YES — filp_open() automatically increments the refcount of the module that owns the device's file_operations. This happens through try_module_get() inside the VFS open path.")
                    BodyText("Practical consequences:")
                    BodyText("• While the struct file is open (between filp_open and filp_close), the target module's use count is non-zero.")
                    BodyText("• rmmod on the target module will return -EBUSY. The module cannot be unloaded.")
                    BodyText("• filp_close() calls module_put() which decrements the refcount. After filp_close(), if no other files are open and no other references exist, rmmod succeeds.")
                    CodeBlock("""
/* Check refcount from outside */
$ cat /sys/module/target/refcnt
1    ← non-zero while filp is open

$ rmmod target
rmmod: ERROR: Module target is in use   ← blocked

$ # After filp_close():
$ rmmod target   ← succeeds""".trimIndent())
                    BodyText("This refcount mechanism is the same one that protects against userspace processes holding a device open. The kernel does not distinguish between a userspace open and a kernel filp_open — both increment the module refcount.")
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SectionCard(title = "What If the Device Removes Itself While Being Called?") {
                    BodyText("The key question is: can the target device disappear — via cdev_del(), device_del(), or similar — while the caller is executing an ioctl inside it?")
                    BodyText("cdev_del() — unregisters the char device major/minor from the kernel's cdev map. After cdev_del(), no NEW opens can succeed. But existing open struct files are unaffected. The struct file captured a pointer to fops at open time; that pointer is stable as long as the module is loaded. Because the module refcount is > 0 (the filp_open holds it), the module cannot be unloaded, so fops remains valid.")
                    BodyText("In other words: cdev_del() is non-destructive for existing open files. It is safe to call from within the target module even while another module has a filp open. The open file will continue to work until filp_close() is called. Only then does the refcount drop to zero, allowing rmmod to proceed.")
                    CodeBlock("""
/* Timeline showing safety */

[caller]  filp_open("/dev/target")
             → try_module_get(target_module)   refcnt = 1
             → fops pointer captured in file->f_op

[target]  cdev_del(&target_cdev)
             → new opens fail (ENXIO)
             → existing file->f_op pointer is unchanged
             → module refcnt still = 1 (rmmod blocked)

[caller]  vfs_ioctl(f, TARGET_IOCTL_GREET, 0)
             → calls file->f_op->unlocked_ioctl   ← safe!

[caller]  filp_close(f, NULL)
             → module_put(target_module)  refcnt = 0

[target]  rmmod target   ← now succeeds""".trimIndent())
                    BodyText("If instead the target module tried to force-exit while the ioctl is actively executing (e.g. module_exit running in parallel on another CPU), the refcount > 0 prevents rmmod from completing — module_exit is not called until the refcount reaches zero. The ioctl always finishes before the module can be removed.")
                    BodyText("Conclusion: the module refcount fully protects against both races — the module cannot be unloaded while a struct file is open to it, and cdev_del() only prevents new opens without disturbing existing ones.")
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
