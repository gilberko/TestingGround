package com.example.windowsapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.windowsapp.ui.theme.HackerGreen

@Composable
fun NetAdapterCxScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "NET ADAPTER CX",
            color = HackerGreen,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "─".repeat(28),
            color = HackerGreen,
            fontFamily = FontFamily.Monospace,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        SectionHeader("WHAT IS NETADAPTERCX")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("NetAdapterCx (Network Adapter WDF Class Extension) is a KMDF extension for writing NDIS miniport drivers. Instead of filling in NDIS_MINIPORT_DRIVER_CHARACTERISTICS and calling NdisMRegisterMiniportDriver, the driver uses KMDF objects and Net-prefixed APIs — NetAdapterCx handles the NDIS registration internally. The driver stays fully in the KMDF object model: WDFDEVICE, WDFDRIVER, PnP and power callbacks all work exactly as in any other KMDF driver.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The result is dramatically less boilerplate. A raw NDIS miniport needs ~1000 lines of hand-written infrastructure; a NetAdapterCx driver that does the same job can be under 300.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHEN WAS IT ADDED")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Introduced in Windows 10 version 1703 (Creators Update, April 2017) with WDK 10.0.15063. It targets hardware NIC drivers — PCIe Ethernet, USB networking, and similar. It is the recommended approach for new hardware bring-up on Windows 10 and later. Raw NDIS miniport remains the approach for virtual adapters (TUN/TAP, VPN): NetAdapterCx is designed around physical hardware abstractions.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("KMDF FOUNDATION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A NetAdapterCx driver is a KMDF driver with extra Net* callbacks. DriverEntry calls WdfDriverCreate (not NdisMRegisterMiniportDriver). EvtDriverDeviceAdd creates a WDFDEVICE, then calls NetAdapterCreate to get a NETADAPTER handle, configures capabilities, and calls NetAdapterStart. Everything else — PnP, power, device removal — is handled by the KMDF + NetAdapterCx runtime.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// DriverEntry — just like any KMDF driver\n" +
            "WDF_DRIVER_CONFIG_INIT(&config, EvtDriverDeviceAdd);\n" +
            "WdfDriverCreate(DriverObject, RegistryPath,\n" +
            "                WDF_NO_OBJECT_ATTRIBUTES, &config, WDF_NO_HANDLE);\n\n" +
            "// EvtDriverDeviceAdd\n" +
            "WdfDeviceCreate(&deviceInit, WDF_NO_OBJECT_ATTRIBUTES, &device);\n\n" +
            "NET_ADAPTER_CONFIG adapterConfig;\n" +
            "NET_ADAPTER_CONFIG_INIT(&adapterConfig,\n" +
            "    EvtAdapterCreateTxQueue,\n" +
            "    EvtAdapterCreateRxQueue);\n\n" +
            "NETADAPTER adapter;\n" +
            "NetAdapterCreate(device, WDF_NO_OBJECT_ATTRIBUTES,\n" +
            "                 &adapterConfig, &adapter);\n\n" +
            "// Set link layer capabilities, data path attributes...\n" +
            "NetAdapterSetLinkLayerCapabilities(adapter, &llCaps);\n" +
            "NetAdapterSetDataPathCapabilities(adapter, &txCaps, &rxCaps);\n\n" +
            "// Bring the adapter online — NDIS binding happens here\n" +
            "NetAdapterStart(adapter);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("KEY DIFFERENCES FROM RAW NDIS MINIPORT")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "                     Raw NDIS Miniport        NetAdapterCx\n" +
            "Registration         NdisMRegisterMiniport    WdfDriverCreate\n" +
            "Device object        NdisMSetMiniportAttrs    WDFDEVICE (KMDF)\n" +
            "Packet I/O model     NET_BUFFER_LIST chains   NET_RING buffers\n" +
            "Queue creation       Manual NBL handling      NetTxQueueCreate\n" +
            "                                              NetRxQueueCreate\n" +
            "Power management     MiniportPause/Restart    EvtDeviceD0Entry/Exit\n" +
            "PnP lifecycle        MiniportInitialize/Halt  EvtDevicePrepareHardware\n" +
            "NDIS version         NDIS 6.x (WDM-level)     KMDF 1.23+ (WDF)\n" +
            "Virtual adapters     Yes                      No (hardware only)"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("RING BUFFER PACKET MODEL")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Raw NDIS miniport uses NET_BUFFER_LIST chains: each send or receive is a linked list of NBL objects, each allocated individually. NetAdapterCx instead uses NET_RING — a fixed-size circular buffer of NET_PACKET and NET_FRAGMENT descriptors shared between the driver and the OS. There is no per-packet allocation on the fast path. The driver's EvtPacketQueueAdvance callback walks the ring, hands packets to or from hardware, and advances the ring index.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "VOID EvtTxQueueAdvance(NETPACKETQUEUE txQueue)\n" +
            "{\n" +
            "    NET_RING_COLLECTION const *rings =\n" +
            "        NetTxQueueGetRingCollection(txQueue);\n" +
            "    NET_RING *packetRing = rings->Rings[NET_RING_TYPE_PACKET];\n" +
            "    NET_RING *fragRing   = rings->Rings[NET_RING_TYPE_FRAGMENT];\n\n" +
            "    // Walk packets the OS wants to send\n" +
            "    UINT32 idx = packetRing->NextIndex;\n" +
            "    while (idx != packetRing->EndIndex) {\n" +
            "        NET_PACKET *packet = NetRingGetPacketAtIndex(packetRing, idx);\n" +
            "        // program hardware DMA descriptor for each fragment...\n" +
            "        idx = NetRingIncrementIndex(packetRing, idx);\n" +
            "    }\n" +
            "    packetRing->NextIndex = idx;\n\n" +
            "    // Retire completed packets (hardware done)\n" +
            "    packetRing->BeginIndex = completedUpTo;\n" +
            "}"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The EvtPacketQueueSetNotificationEnabled callback enables or disables interrupt-driven notification. When hardware signals completion (interrupt → DPC), the driver calls NetTxQueueNotifyMoreCompletedPacketsAvailable or NetRxQueueNotifyMoreReceivedPacketsAvailable to wake the queue.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("TX AND RX QUEUE CREATION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The adapter config supplies two callbacks: EvtAdapterCreateTxQueue and EvtAdapterCreateRxQueue. NDIS calls them when binding. The driver fills in a NET_PACKET_QUEUE_CONFIG and calls NetTxQueueCreate / NetRxQueueCreate.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "NTSTATUS EvtAdapterCreateTxQueue(\n" +
            "    NETADAPTER adapter, NETTXQUEUE_INIT *init)\n" +
            "{\n" +
            "    NET_PACKET_QUEUE_CONFIG txConfig;\n" +
            "    NET_PACKET_QUEUE_CONFIG_INIT(\n" +
            "        &txConfig,\n" +
            "        EvtTxQueueAdvance,\n" +
            "        EvtTxQueueSetNotificationEnabled,\n" +
            "        EvtTxQueueCancel);\n\n" +
            "    NETPACKETQUEUE txQueue;\n" +
            "    return NetTxQueueCreate(init,\n" +
            "                            WDF_NO_OBJECT_ATTRIBUTES,\n" +
            "                            &txConfig, &txQueue);\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("BUILDING")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Use the \"Kernel Mode Driver (KMDF)\" project template in Visual Studio + WDK. Add NetAdapterCx.lib to the linker inputs and NetAdapterCx.h to includes. The INF file must declare the NetCx class and reference the NetAdapterCx co-installer. Deployment is the same as any KMDF driver: pnputil /add-driver with the INF, or devcon install for development. Requires Windows 10 1703 or later on the target machine.")

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
