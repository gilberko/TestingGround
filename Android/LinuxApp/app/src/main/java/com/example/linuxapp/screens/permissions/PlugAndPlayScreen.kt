package com.example.linuxapp.screens.permissions

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
fun PlugAndPlayScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Plug And Play",
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
                .padding(horizontal = 16.dp)
        ) {
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "What Is Plug and Play?") {
                    BodyText("Plug and Play (PnP) is the ability to connect a device and have it automatically recognized, configured, and made usable — without rebooting or manual driver installation.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("In Linux, PnP is built from three cooperating layers:")
                    CodeBlock(
                        """1. Bus enumeration protocol
   — USB, PCI, Bluetooth each have their own protocol
     for discovering attached devices at runtime

2. Kernel device model
   — struct device + kobject + driver matching engine
   — calls probe() when a driver matches a device
   — calls disconnect() / remove() on unplug

3. udev (userspace device manager)
   — receives kernel uevents over NETLINK_KOBJECT_UEVENT
   — creates /dev nodes, loads firmware, runs scripts""")
                    Spacer(Modifier.height(4.dp))
                    BodyText("USB is the most visible PnP bus on a Linux desktop. The rest of this screen focuses on USB.")
                }
            }

            item {
                SectionCard(title = "USB Device Plug-In Flow") {
                    BodyText("When you plug in a USB device, the following sequence happens:")
                    CodeBlock(
                        """1. Hardware: USB hub detects D+ line pull-up
   (device powered on and connected to host port)
   → Hub controller raises an interrupt

2. Hub driver: reads hub status register, identifies
   which port changed, resets the port

3. USB core: assigns a bus address to the device,
   then reads the device descriptor:
     idVendor  — manufacturer ID  (e.g. 0x046D Logitech)
     idProduct — product ID       (e.g. 0xC52B wireless rcvr)
     bcdDevice — device version
     bDeviceClass — device class (HID, storage, etc.)

4. USB core: reads configuration, interface,
   and endpoint descriptors
   → knows power requirements, endpoints, protocols

5. Driver matching: kernel searches loaded modules for
   a driver whose usb_device_id table matches
   (idVendor, idProduct) or (bDeviceClass, bDeviceSubClass)
   → if module not loaded: udev loads it via modprobe

6. Driver probe(): driver is called, initializes device,
   allocates URBs, sets up /dev or input subsystem entry

7. device_add() → kobject_uevent(KOBJ_ADD)
   → netlink message sent to udevd

8. udevd: receives uevent, queries /sys/bus/usb/devices/
   applies rules, creates /dev node if needed""")
                }
            }

            item {
                SectionCard(title = "The usb_device_id Table") {
                    BodyText("Every USB driver exports a table declaring which devices it handles. The kernel uses this table for driver matching, and modprobe uses it for automatic module loading.")
                    CodeBlock(
                        """#include <linux/usb.h>

/* Table of devices this driver supports */
static const struct usb_device_id my_usb_table[] = {
    /* Match by vendor + product ID */
    { USB_DEVICE(0x046D, 0xC52B) },

    /* Match any device of a given class */
    { USB_INTERFACE_INFO(USB_CLASS_HID, 0, 0) },

    { } /* terminator */
};

/* Exports the table to modprobe and udev */
MODULE_DEVICE_TABLE(usb, my_usb_table);

static struct usb_driver my_driver = {
    .name       = "my_usb_driver",
    .id_table   = my_usb_table,
    .probe      = my_probe,      /* called on plug-in */
    .disconnect = my_disconnect, /* called on unplug  */
};"""
                    )
                    Spacer(Modifier.height(4.dp))
                    BodyText("MODULE_DEVICE_TABLE generates a section in the .ko binary that tools like depmod parse to build the modules.alias file. When udevd gets a uevent with PRODUCT=046d/c52b/..., it looks up modules.alias and calls modprobe with the matching module name.")
                }
            }

            item {
                SectionCard(title = "USB Device Unplug Flow") {
                    BodyText("When the USB cable is pulled or the device is powered off:")
                    CodeBlock(
                        """1. Hardware: hub detects loss of D+ voltage
   → Hub controller raises an interrupt

2. Hub driver: reads port status, sees device gone,
   marks struct usb_device as disconnected

3. USB core: cancels all in-flight URBs automatically
   (see next section), marks endpoint queues dead

4. Driver disconnect() callback is called:
   — driver must stop all I/O it started
   — free all private buffers and state
   — unregister any input/network/block devices
   — after disconnect() returns, the driver must not
     touch the device again

5. device_remove() → kobject_uevent(KOBJ_REMOVE)
   → netlink message sent to udevd

6. udevd: receives REMOVE uevent,
   removes /dev nodes for that device,
   runs any remove-action rules

7. struct usb_device freed, all sysfs entries removed"""
                    )
                }
            }

            item {
                SectionCard(title = "URBs — USB Request Blocks") {
                    BodyText("A URB (USB Request Block) is the kernel object that represents a single asynchronous USB I/O operation — analogous to a bio in the block layer.")
                    CodeBlock(
                        """struct urb *urb = usb_alloc_urb(0, GFP_KERNEL);

/* Fill the URB (bulk transfer example) */
usb_fill_bulk_urb(urb, dev, pipe,
                  buffer, length,
                  my_completion_fn, context);

/* Submit — returns immediately, callback fires later */
usb_submit_urb(urb, GFP_KERNEL);

/* Cancel a pending URB (waits for completion handler) */
usb_kill_urb(urb);    /* blocks until handler done */
usb_poison_urb(urb);  /* also prevents resubmission */

usb_free_urb(urb);"""
                    )
                    Spacer(Modifier.height(4.dp))
                    BodyText("On disconnect, the driver must cancel every URB it submitted before it returns from disconnect(). usb_kill_urb() guarantees the completion callback has finished before returning — safe for cleanup. usb_poison_urb() additionally makes any future usb_submit_urb() on that URB fail, useful when a completion handler resubmits in a loop.")
                }
            }

            item {
                SectionCard(title = "PnP for Other Bus Types") {
                    BodyText("The same device model principles apply across all hot-plug buses in Linux:")
                    CodeBlock(
                        """PCI / PCIe
  — Config space auto-enumeration on boot + hotplug slots
  — pci_device_id table + MODULE_DEVICE_TABLE(pci, ...)
  — probe() / remove() callbacks, same kobject_uevent path

Bluetooth
  — HCI layer discovers devices during inquiry/scan
  - btusb driver handles the controller via USB PnP
  — L2CAP / profiles handle pairing and service discovery

I2C / SPI (embedded)
  — No enumeration protocol — devices listed in Device Tree
    or ACPI, probed at boot (not truly hot-plug)
  — Exceptions: I2C-HID and some SMBus devices support
    a limited form of enumeration

Thunderbolt / USB4
  — Security levels control authorization before probing
  — Otherwise follows the same USB enumeration path"""
                    )
                    Spacer(Modifier.height(4.dp))
                    BodyText("In all cases the flow is the same: bus detects → enumerate → driver matching via id table → probe() → kobject_uevent → udev → /dev node.")
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
