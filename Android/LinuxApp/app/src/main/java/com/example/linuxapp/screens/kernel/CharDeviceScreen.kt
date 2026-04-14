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
fun CharDeviceScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Char Device",
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
                SectionCard(title = "What Is a Character Device?") {
                    BodyText("A character device transfers data as a stream of bytes — one byte at a time, unbuffered. Character devices appear as files in /dev (e.g. /dev/tty0, /dev/null, /dev/random).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Each device is identified by two numbers:")
                    CodeBlock(
                        """Major number — identifies the driver
Minor number — identifies a specific device instance

Example: /dev/tty0 has major=4, minor=0."""
                    )
                }
            }
            item {
                SectionCard(title = "File Operations Struct") {
                    BodyText("The kernel routes file I/O to your driver through a struct file_operations. You fill in function pointers for the operations you support:")
                    CodeBlock(
                        """#include <linux/fs.h>

static struct file_operations mydev_fops = {
    .owner          = THIS_MODULE,
    .open           = mydev_open,
    .release        = mydev_release,
    .read           = mydev_read,
    .write          = mydev_write,
    .unlocked_ioctl = mydev_ioctl,
};"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(".owner — prevents module unload while device is open (always THIS_MODULE).")
                    BodyText(".open — called when a process opens the device file.")
                    BodyText(".release — called when the last fd referencing the file is closed.")
                    BodyText(".read — called on read(2). Copy data from kernel to user space.")
                    BodyText(".write — called on write(2). Copy data from user space to kernel.")
                    BodyText(".unlocked_ioctl — called on ioctl(2). Custom device control commands.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Function signatures:")
                    CodeBlock(
                        """int     mydev_open   (struct inode *, struct file *);
int     mydev_release(struct inode *, struct file *);
ssize_t mydev_read   (struct file *, char __user *,
                      size_t, loff_t *);
ssize_t mydev_write  (struct file *, const char __user *,
                      size_t, loff_t *);
long    mydev_ioctl  (struct file *, unsigned int cmd,
                      unsigned long arg);"""
                    )
                }
            }
            item {
                SectionCard(title = "Registering a Single Major Number (Legacy API)") {
                    BodyText("register_chrdev() is the legacy all-in-one API:")
                    CodeBlock(
                        """#include <linux/fs.h>

int major;

/* In init: pass 0 to get a dynamic major assigned */
major = register_chrdev(0, "mydevice", &mydev_fops);
if (major < 0) {
    pr_err("register_chrdev failed: %d\n", major);
    return major;
}
pr_info("Registered with major %d\n", major);

/* In exit: */
unregister_chrdev(major, "mydevice");"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("What register_chrdev does internally:")
                    CodeBlock(
                        """register_chrdev() calls alloc_chrdev_region() + cdev_alloc()
+ cdev_add() for you internally.

→ You do NOT call cdev_init / cdev_add separately.
→ It allocates all 256 minor numbers for that major.

However, you STILL need class_create() + device_create()
if you want a /dev entry to appear via udev."""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("With register_chrdev, the complete init/exit pattern is:")
                    CodeBlock(
                        """static int __init mydev_init(void)
{
    major = register_chrdev(0, "mydevice", &mydev_fops);
    if (major < 0) return major;

    my_class = class_create("myclass");
    if (IS_ERR(my_class)) {
        unregister_chrdev(major, "mydevice");
        return PTR_ERR(my_class);
    }
    device_create(my_class, NULL, MKDEV(major, 0),
                  NULL, "mydevice");
    return 0;
}

static void __exit mydev_exit(void)
{
    device_destroy(my_class, MKDEV(major, 0));
    class_destroy(my_class);
    unregister_chrdev(major, "mydevice");
}"""
                    )
                }
            }
            item {
                SectionCard(title = "Registering a Range (Modern API)") {
                    BodyText("The modern API separates number allocation from cdev creation, giving you fine-grained control:")
                    CodeBlock(
                        """#include <linux/fs.h>

dev_t dev;  /* major + minor packed into one value */

/* Dynamic allocation (recommended): */
int ret = alloc_chrdev_region(&dev, 0, 1, "mydevice");
/*   &dev        = output: assigned major+minor
     baseminor   = 0 (first minor)
     count       = 1 (how many minors to allocate)
     name        = shows in /proc/devices           */

/* Static allocation (you choose the major): */
dev = MKDEV(240, 0);
int ret = register_chrdev_region(dev, 1, "mydevice");

/* Extract major/minor back: */
int major = MAJOR(dev);
int minor = MINOR(dev);

/* Cleanup: */
unregister_chrdev_region(dev, 1);"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("With alloc_chrdev_region, the kernel does NOT create a cdev for you. You must call cdev_init + cdev_add manually (see next section).")
                }
            }
            item {
                SectionCard(title = "Adding the Char Device (cdev) — Modern API Only") {
                    BodyText("After alloc_chrdev_region, create and register the cdev to link the dev_t to your file_operations:")
                    CodeBlock(
                        """#include <linux/cdev.h>

struct cdev my_cdev;

/* In init, after alloc_chrdev_region: */
cdev_init(&my_cdev, &mydev_fops);
my_cdev.owner = THIS_MODULE;

int err = cdev_add(&my_cdev, dev, 1);
if (err) {
    pr_err("cdev_add failed: %d\n", err);
    unregister_chrdev_region(dev, 1);
    return err;
}
/* From this point the kernel routes fops to your driver */

/* In exit, before unregister_chrdev_region: */
cdev_del(&my_cdev);"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Skip this section entirely if you used register_chrdev — it handles cdev internally.")
                }
            }
            item {
                SectionCard(title = "Creating a /dev Entry") {
                    BodyText("Whether you used register_chrdev or alloc_chrdev_region, a /dev entry is NOT created automatically. Use the device class API to have udev create it:")
                    CodeBlock(
                        """#include <linux/device.h>

struct class  *my_class;
struct device *my_device;

my_class = class_create("myclass");
if (IS_ERR(my_class)) { /* handle error */ }

my_device = device_create(my_class, NULL, dev,
                           NULL, "mydevice");
if (IS_ERR(my_device)) { /* handle error */ }

/* udev sees the uevent and creates /dev/mydevice */

/* Cleanup (reverse order): */
device_destroy(my_class, dev);
class_destroy(my_class);"""
                    )
                }
            }
            item {
                SectionCard(title = "mknod vs udev — How /dev Entries Are Created") {
                    BodyText("There are two ways to create a /dev node for your driver: manually with mknod, or automatically through the udev daemon.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("mknod — manual approach:")
                    CodeBlock(
                        """# Syntax: mknod <path> <type> <major> <minor>
# c = character device, b = block device
sudo mknod /dev/mydevice c 240 0

# Remove it
sudo rm /dev/mydevice"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("mknod creates a special file directly in the filesystem. No daemon is involved — you must know the major/minor numbers yourself. Useful in recovery environments, minimal init systems, or when you want to pre-create the node before the module loads.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("class_create + device_create — automatic via udev:")
                    BodyText("When you call device_create(), the kernel internally calls kobject_uevent(KOBJ_ADD), which broadcasts a netlink message over NETLINK_KOBJECT_UEVENT. This wakes up the udevd daemon running in userspace.")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("How udevd communicates with the kernel:")
                    CodeBlock(
                        """/* udevd listens on a netlink socket: */
socket(AF_NETLINK, SOCK_RAW, NETLINK_KOBJECT_UEVENT)

/* The uevent message carries: */
ACTION=add
DEVPATH=/devices/virtual/myclass/mydevice
SUBSYSTEM=myclass
DEVNAME=mydevice
MAJOR=240
MINOR=0"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("After receiving the uevent, udevd queries sysfs (/sys/DEVPATH/) to read the full device attributes. It then applies your rules from /etc/udev/rules.d/ to decide the final node name, permissions, ownership, and any symlinks to create. Finally it creates the /dev/mydevice node.")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Tip — watch uevents in real time:")
                    CodeBlock("udevadm monitor --kernel")
                }
            }
            item {
                SectionCard(title = "Seeing It in the Filesystem") {
                    CodeBlock(
                        """# c = character device; shows major, minor
ls -l /dev/mydevice
# crw------- 1 root root 240, 0 ...

# All registered char and block devices
cat /proc/devices

# sysfs entries
ls /sys/class/myclass/mydevice/"""
                    )
                }
            }
            item {
                SectionCard(title = "Communicating Through the Filesystem") {
                    CodeBlock(
                        """# Triggers .write in your driver
echo "hello driver" > /dev/mydevice

# Triggers .read in your driver
cat /dev/mydevice"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Inside .read and .write, always use copy_to_user / copy_from_user to cross the kernel/user boundary safely:")
                    CodeBlock(
                        """ssize_t mydev_write(struct file *file,
                    const char __user *buf,
                    size_t count, loff_t *ppos)
{
    char kbuf[256];
    size_t n = min(count, sizeof(kbuf) - 1);
    if (copy_from_user(kbuf, buf, n))
        return -EFAULT;
    kbuf[n] = '\0';
    pr_info("received: %s\n", kbuf);
    return n;
}

ssize_t mydev_read(struct file *file,
                   char __user *buf,
                   size_t count, loff_t *ppos)
{
    const char *msg = "Hello from kernel!\n";
    size_t len = strlen(msg);
    if (*ppos >= len) return 0;  /* EOF */
    if (copy_to_user(buf, msg, len)) return -EFAULT;
    *ppos += len;
    return len;
}"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Never dereference a __user pointer directly in kernel code — it could be an invalid or malicious address.")
                }
            }
            item {
                SectionCard(title = "ioctl Handler") {
                    BodyText("ioctl allows user space to send custom commands to the driver — operations that don't fit the read/write model (e.g. set speed, get status, reset hardware).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Step 1 — define your ioctl commands using the kernel macros:")
                    CodeBlock(
                        """#include <linux/ioctl.h>

/* Pick a unique 8-bit magic number for your driver */
#define MYDEV_IOC_MAGIC  'k'

/* _IO   = no data transfer
   _IOR  = kernel→user (Read from driver)
   _IOW  = user→kernel (Write to driver)
   _IOWR = bidirectional */
#define MYDEV_IOCRESET   _IO  (MYDEV_IOC_MAGIC, 0)
#define MYDEV_IOCGVAL    _IOR (MYDEV_IOC_MAGIC, 1, int)
#define MYDEV_IOCSVAL    _IOW (MYDEV_IOC_MAGIC, 2, int)

/* Put these defines in a shared header used by both
   the driver and the user-space application. */"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Step 2 — implement the handler:")
                    CodeBlock(
                        """static int internal_val = 0;

static long mydev_ioctl(struct file *file,
                        unsigned int cmd,
                        unsigned long arg)
{
    int val;

    /* Verify the magic number belongs to this driver */
    if (_IOC_TYPE(cmd) != MYDEV_IOC_MAGIC)
        return -ENOTTY;

    switch (cmd) {
    case MYDEV_IOCRESET:
        internal_val = 0;
        break;

    case MYDEV_IOCGVAL:
        /* Copy kernel value → user space */
        if (copy_to_user((int __user *)arg,
                         &internal_val, sizeof(int)))
            return -EFAULT;
        break;

    case MYDEV_IOCSVAL:
        /* Copy user value → kernel */
        if (copy_from_user(&val, (int __user *)arg,
                           sizeof(int)))
            return -EFAULT;
        internal_val = val;
        break;

    default:
        return -ENOTTY;  /* unknown command */
    }
    return 0;
}"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Step 3 — call from user space:")
                    CodeBlock(
                        """#include <sys/ioctl.h>

int fd = open("/dev/mydevice", O_RDWR);

/* Reset */
ioctl(fd, MYDEV_IOCRESET);

/* Set value */
int v = 42;
ioctl(fd, MYDEV_IOCSVAL, &v);

/* Get value */
int result;
ioctl(fd, MYDEV_IOCGVAL, &result);
printf("kernel value: %d\n", result);

close(fd);"""
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
