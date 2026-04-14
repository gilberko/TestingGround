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
fun DeviceTreesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Device Trees & ACPI",
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
                SectionCard(title = "What Is a Device Tree?") {
                    BodyText("A Device Tree is a data structure that describes hardware to the operating system. Instead of the kernel hard-coding knowledge of every board's memory layout, IRQ lines, clocks, and buses, the bootloader passes a binary Device Tree Blob (DTB) to the kernel at boot time. The kernel reads it to discover what hardware is present.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Workflow:")
                    CodeBlock(
                        """Board description written in DTS
(Device Tree Source — human readable text)
        ↓  compiled by dtc (Device Tree Compiler)
DTB (Device Tree Blob — binary)
        ↓  loaded by bootloader (U-Boot / UEFI)
Kernel reads DTB at boot → probes drivers"""
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("DTS files use a simple hierarchical syntax of nodes and properties. A node represents a device or bus; properties are key-value pairs describing its resources.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("DTSI files (.dtsi) are include files — SoC vendors provide a base dtsi that board vendors include and override for their specific hardware.")
                }
            }

            item {
                SectionCard(title = "A Simple Device Tree Example") {
                    BodyText("A minimal DTS showing a CPU, memory, and a USB host controller:")
                    CodeBlock(
                        """/dts-v1/;

/ {
    #address-cells = <1>;  /* 1 cell = 32-bit address */
    #size-cells    = <1>;  /* 1 cell = 32-bit size    */

    cpus {
        cpu@0 {
            compatible = "arm,cortex-a9";
            reg = <0>;  /* CPU index */
        };
    };

    memory@80000000 {
        device_type = "memory";
        reg = <0x80000000 0x40000000>; /* 1 GB at 2 GB */
    };

    soc {
        compatible = "simple-bus";
        #address-cells = <1>;
        #size-cells    = <1>;
        ranges;  /* pass addresses straight through */

        usb@12110000 {
            compatible = "samsung,exynos4210-ehci";
            reg       = <0x12110000 0x100>; /* MMIO base + size */
            interrupts = <0 71 0>;           /* IRQ line */
            clocks    = <&clock 305>;        /* clock gate */
            status    = "okay";
        };
    };
};"""
                    )
                    Spacer(Modifier.height(4.dp))
                    BodyText("The compatible property is how the kernel matches a driver to a node — the kernel looks for a driver that registered the same compatible string.")
                }
            }

            item {
                SectionCard(title = "What Is ACPI?") {
                    BodyText("ACPI (Advanced Configuration and Power Interface) is a firmware standard used on x86/x64 systems. The BIOS/UEFI firmware stores ACPI tables in memory. The kernel finds and parses these tables to discover hardware and manage power.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Key characteristics:")
                    CodeBlock(
                        """• Tables include AML bytecode (ACPI Machine Language)
  — the kernel runs an AML interpreter to execute
    device methods at runtime
• Richer than DT: supports power management methods,
  thermal zones, embedded controller access, Notify()
  events for dynamic hardware changes
• DSDT (Differentiated System Description Table):
  main table describing devices
• SSDT: supplemental tables (can be loaded at runtime)
• /sys/firmware/acpi/tables/ — view tables on a running system""")
                    Spacer(Modifier.height(8.dp))
                    BodyText("ACPI supports runtime device enable/disable and power state transitions through AML methods (_STA, _PS0, _PS3, etc.), making it more dynamic than a static Device Tree.")
                }
            }

            item {
                SectionCard(title = "DT vs ACPI — Where Each Is Used") {
                    CodeBlock(
                        """Platform              | Firmware  | Hardware discovery
----------------------+-----------+-------------------
ARM / ARM64 embedded  | U-Boot    | Device Tree (DTB)
RISC-V boards         | U-Boot    | Device Tree (DTB)
Android phones / SBCs | U-Boot    | Device Tree (DTB)
x86 / x64 PCs         | BIOS/UEFI | ACPI
x86 servers           | UEFI      | ACPI
Modern ARM64 servers  | UEFI      | ACPI (or DT)
  (Ampere, AWS Graviton, etc.)"""
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Modern ARM64 servers like AWS Graviton or Ampere Altra can use ACPI because they run UEFI firmware — the same standard as x86 servers. The Linux ARM64 kernel supports both: it prefers Device Tree if a DTB is present, otherwise falls back to ACPI.")
                }
            }

            item {
                SectionCard(title = "Embedded Devices and Device Trees") {
                    BodyText("Device Trees were created specifically for embedded systems where there is no self-describing bus. On a typical PC the BIOS/PCI bus can enumerate devices automatically — on an embedded board the hardware is wired directly to the SoC with no enumeration protocol.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Without a Device Tree the kernel would need board-specific C code for every board variant. With DT, one kernel binary can boot any board — the bootloader just loads the right DTB.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Typical embedded resources described in DT:")
                    CodeBlock(
                        """• Memory-mapped peripherals (UART, SPI, I2C, GPIO)
• Interrupt controllers and IRQ mappings
• Clock sources and clock gates (clocks = <&clock N>)
• Pin multiplexing (pinctrl) — which pins go to which function
• Power regulators (voltage domains)
• DMA channels
• USB host / device controllers
• Display controllers and panel timings""")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Examples: Raspberry Pi (BCM2711 DT), BeagleBone (AM335x DT), i.MX6 boards, Qualcomm phone SoCs. On these platforms the Device Tree is the only way the kernel knows the hardware layout.")
                }
            }

            item {
                SectionCard(title = "Device Trees and USB — Static Controller, Dynamic Devices") {
                    BodyText("Device Trees are static — the file is compiled at build time and describes fixed hardware. But that does not mean USB hot-plug stops working. The two concerns are independent.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("The DT describes the USB host controller hardware:")
                    CodeBlock(
                        """usb@12110000 {
    compatible = "samsung,exynos4210-ehci";
    reg        = <0x12110000 0x100>; /* MMIO registers */
    interrupts = <0 71 0>;           /* IRQ */
    clocks     = <&clock 305>;       /* power gate */
};"""
                    )
                    Spacer(Modifier.height(4.dp))
                    BodyText("The kernel reads this node and binds the EHCI host controller driver. After that, the USB subsystem (not the Device Tree) takes complete ownership of device discovery:")
                    CodeBlock(
                        """DT defines the controller hardware
        ↓
Kernel binds USB host controller driver
(e.g. ehci-exynos, xhci-plat)
        ↓  (DT's job is done here)
USB host controller driver is running
        ↓  user plugs in a USB device
Hub driver detects D+ line pull-up
        ↓
USB core enumerates the device
(reads idVendor, idProduct, descriptors)
        ↓
Kernel matches driver → probe() called
        ↓
/dev node appears"""
                    )
                    Spacer(Modifier.height(4.dp))
                    BodyText("Everything after the controller driver is loaded is fully dynamic — the Device Tree is not consulted at all. USB keyboards, flash drives, and webcams are discovered, driver-matched, and exposed to userspace at runtime, exactly as on any x86 machine.")
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
