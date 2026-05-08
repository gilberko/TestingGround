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
fun LinuxDeviceModelScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Linux Device Model",
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
                SectionCard(title = "What Is the Linux Device Model") {
                    BodyText("The Linux Device Model is a unified representation of every device and bus in the system. Its core goals are:")
                    BodyText("• Power management — walk the device tree to suspend/resume in the right order\n• Hotplug — add and remove devices safely at runtime\n• sysfs — expose device topology and attributes to userspace under /sys\n• Reference counting — prevent use-after-free when devices disappear")
                    BodyText("Every representable object (device, bus, driver, class) embeds a struct kobject. kobject handles ref-counting and sysfs node creation. All higher-level structs (struct device, struct bus_type, struct device_driver) wrap a kobject.")
                    BodyText("struct device is the foundational type. Every physical or virtual device in the kernel is a struct device or embeds one:")
                    CodeBlock("""
struct device {
    struct device       *parent;    /* parent in the device tree   */
    struct kobject      kobj;       /* sysfs node + refcount       */
    const char          *init_name;
    struct device_type  *type;
    struct bus_type     *bus;       /* which bus this device is on */
    struct device_driver *driver;   /* bound driver, or NULL       */
    void                *platform_data; /* legacy board-file data  */
    void                *driver_data;   /* driver private, see below */
    /* ... */
};""".trimIndent())
                    BodyText("Most subsystem-specific structs (pci_dev, usb_device, platform_device) embed struct device as a member, so they automatically get sysfs, ref-counting, and power management.")
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SectionCard(title = "struct platform_device") {
                    BodyText("A platform device represents hardware that cannot be discovered automatically — it is memory-mapped at a fixed address, has a hardcoded IRQ, and has no self-describing bus (unlike PCI or USB). Examples: UARTs, GPIO controllers, timers on SoCs.")
                    CodeBlock("""
struct platform_device {
    const char          *name;   /* matched against driver name  */
    int                 id;      /* -1 = single instance         */
    struct device       dev;     /* embedded struct device       */
    u32                 num_resources;
    struct resource     *resource; /* IORESOURCE_MEM / IRQ list  */
    const struct platform_device_id *id_entry; /* matched entry  */
    /* ... */
};""".trimIndent())
                    BodyText("struct resource describes a memory range or IRQ the device owns:")
                    CodeBlock("""
struct resource {
    resource_size_t start;
    resource_size_t end;
    const char      *name;
    unsigned long   flags;  /* IORESOURCE_MEM, IORESOURCE_IRQ, … */
};""".trimIndent())
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SectionCard(title = "struct platform_driver") {
                    BodyText("A platform driver binds to one or more platform devices. You define callbacks and register the driver:")
                    CodeBlock("""
struct platform_driver {
    int  (*probe) (struct platform_device *);
    void (*remove)(struct platform_device *);
    struct device_driver driver; /* embedded, holds name + owner */
    const struct platform_device_id *id_table;
};""".trimIndent())
                    BodyText("driver.of_match_table — array of struct of_device_id terminated by {}. Each entry has a .compatible string that must match a node's compatible property in the Device Tree.\n\nid_table — array of struct platform_device_id terminated by {}. Each entry has a .name string matched against platform_device.name when there is no Device Tree.")
                    BodyText("Typical registration in a module:")
                    CodeBlock("""
static struct platform_driver my_driver = {
    .probe  = my_probe,
    .remove = my_remove,
    .driver = {
        .name           = "my-device",
        .owner          = THIS_MODULE,
        .of_match_table = my_of_match,
    },
};
module_platform_driver(my_driver); /* expands to module_init/exit */""".trimIndent())
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SectionCard(title = "The Matching Process") {
                    BodyText("When platform_driver_register() is called, the kernel iterates all registered platform devices and tries to match each one against the driver. The match order:")
                    BodyText("1. Device Tree (OF) match — if the device was described in a DTS node, the kernel compares the node's compatible string against every entry in driver.of_match_table. First hit wins.")
                    BodyText("2. ACPI match — similar, for ACPI-enumerated devices.")
                    BodyText("3. id_table match — compares platform_device.name against each platform_device_id.name in the driver's id_table.")
                    BodyText("4. Name match — compares platform_device.name directly against driver.driver.name.")
                    BodyText("On a match, the bus calls driver.probe(pdev). If probe returns 0 the device is bound to the driver. If probe returns an error the device remains unbound.")
                    BodyText("The same match runs in reverse when platform_device_register() is called while the driver is already loaded.")
                    CodeBlock("""
/* DTS node */
my_uart: serial@10000000 {
    compatible = "myvendor,my-uart";
    reg = <0x10000000 0x1000>;
    interrupts = <0 42 4>;
};

/* Driver of_match_table */
static const struct of_device_id my_of_match[] = {
    { .compatible = "myvendor,my-uart" },
    { /* sentinel */ }
};
MODULE_DEVICE_TABLE(of, my_of_match);""".trimIndent())
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Registering a Device From Code") {
                    BodyText("Normally platform devices come from the Device Tree or ACPI. For testing or legacy board files you can create one in C:")
                    CodeBlock("""
static struct resource my_resources[] = {
    {
        .start = 0x10000000,
        .end   = 0x10000FFF,
        .flags = IORESOURCE_MEM,
        .name  = "my-device-mem",
    },
    {
        .start = 42,
        .end   = 42,
        .flags = IORESOURCE_IRQ,
        .name  = "my-device-irq",
    },
};

static struct platform_device my_pdev = {
    .name          = "my-device",
    .id            = -1,          /* single instance */
    .num_resources = ARRAY_SIZE(my_resources),
    .resource      = my_resources,
};

/* In module_init: */
platform_device_register(&my_pdev);

/* In module_exit: */
platform_device_unregister(&my_pdev);""".trimIndent())
                    BodyText("platform_device_alloc(name, id) + platform_device_add(pdev) is the dynamic variant — allocates and registers in two steps, useful when you need to set platform_data before registration.")
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Communicating: Driver ↔ Device") {
                    BodyText("Driver → Device (most common direction):\n\nIn .probe(), the driver reads the device's resources and maps them:")
                    CodeBlock("""
static int my_probe(struct platform_device *pdev)
{
    struct resource *mem;
    void __iomem    *base;
    int              irq;

    mem  = platform_get_resource(pdev, IORESOURCE_MEM, 0);
    base = devm_ioremap_resource(&pdev->dev, mem); /* auto-cleanup */
    if (IS_ERR(base)) return PTR_ERR(base);

    irq = platform_get_irq(pdev, 0);
    if (irq < 0) return irq;

    /* store per-device private state */
    struct my_priv *priv = devm_kzalloc(&pdev->dev,
                                         sizeof(*priv), GFP_KERNEL);
    priv->base = base;
    priv->irq  = irq;
    platform_set_drvdata(pdev, priv); /* attach to device */

    devm_request_irq(&pdev->dev, irq, my_irq_handler,
                     0, "my-device", priv);
    return 0;
}""".trimIndent())
                    BodyText("platform_set_drvdata() stores a pointer inside pdev->dev.driver_data. Any other callback (.remove, .suspend, .resume, sysfs show/store) can retrieve it with platform_get_drvdata(pdev).")
                    BodyText("Device → Driver (async):\n\nThe device signals the driver by raising an IRQ. The driver registers an IRQ handler in .probe(); when hardware fires the interrupt, the kernel calls the handler which reads hardware registers and acts accordingly. This is the standard bidirectional communication channel.")
                    BodyText("sysfs attributes can also carry data in both directions: the driver exports read/write attributes; userspace or another kernel component writes a value; the driver's store() callback reacts.")
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Full Example") {
                    BodyText("A minimal platform device + driver that maps memory, handles an IRQ, and exchanges data through driver_data:")
                    CodeBlock("""
/* ── Device definition (board file / test module) ─────────── */
#include <linux/platform_device.h>

static struct resource demo_res[] = {
    DEFINE_RES_MEM(0x10000000, 0x1000),
    DEFINE_RES_IRQ(42),
};
static struct platform_device demo_pdev = {
    .name = "demo-device", .id = -1,
    .num_resources = ARRAY_SIZE(demo_res),
    .resource      = demo_res,
};
/* call platform_device_register(&demo_pdev) in module_init */


/* ── Driver ─────────────────────────────────────────────── */
#include <linux/module.h>
#include <linux/platform_device.h>
#include <linux/interrupt.h>
#include <linux/io.h>

struct demo_priv {
    void __iomem *base;
    int           irq;
    u32           last_value;
};

static irqreturn_t demo_irq(int irq, void *data)
{
    struct demo_priv *p = data;
    p->last_value = readl(p->base + 0x04); /* read status register */
    pr_info("demo: irq fired, value=0x%x\n", p->last_value);
    return IRQ_HANDLED;
}

static int demo_probe(struct platform_device *pdev)
{
    struct demo_priv *p;
    struct resource  *mem;

    p   = devm_kzalloc(&pdev->dev, sizeof(*p), GFP_KERNEL);
    mem = platform_get_resource(pdev, IORESOURCE_MEM, 0);
    p->base = devm_ioremap_resource(&pdev->dev, mem);
    if (IS_ERR(p->base)) return PTR_ERR(p->base);

    p->irq = platform_get_irq(pdev, 0);
    devm_request_irq(&pdev->dev, p->irq, demo_irq,
                     0, "demo-device", p);

    writel(0x1, p->base + 0x00); /* write a config register */
    platform_set_drvdata(pdev, p);
    dev_info(&pdev->dev, "probed, base=%p irq=%d\n", p->base, p->irq);
    return 0;
}

static void demo_remove(struct platform_device *pdev)
{
    /* devm resources freed automatically */
    dev_info(&pdev->dev, "removed\n");
}

static const struct of_device_id demo_of[] = {
    { .compatible = "demo,demo-device" }, {}
};
MODULE_DEVICE_TABLE(of, demo_of);

static struct platform_driver demo_driver = {
    .probe  = demo_probe,
    .remove = demo_remove,
    .driver = { .name = "demo-device", .of_match_table = demo_of },
};
module_platform_driver(demo_driver);
MODULE_LICENSE("GPL");""".trimIndent())
                    BodyText("Data flow summary:\n• Driver → Device: writel() / writew() / writeb() to ioremap'd base address\n• Device → Driver: IRQ fires → handler reads hardware register → stores in priv\n• Both directions are possible; the IRQ is the device's primary way to notify the driver")
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
