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
fun NdisLightweightFilterScreen(navController: NavController) {
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
            text = "NDIS LIGHTWEIGHT FILTER",
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

        SectionHeader("WHAT IS AN NDIS LIGHTWEIGHT FILTER?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("An NDIS Lightweight Filter (LWF) is a kernel-mode driver that inserts itself into the NDIS (Network Driver Interface Specification) stack between the miniport driver (the NIC driver) and the protocol driver (TCP/IP). It intercepts all inbound and outbound packets on one or more network adapters. It is simpler than the older NDIS Intermediate Driver (IMD) model — it attaches to an existing adapter as a module without creating virtual devices.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "[ Protocol Driver: TCPIP   ]\n" +
            "          ↕\n" +
            "[ NDIS Lightweight Filter ]   ← sits here\n" +
            "          ↕\n" +
            "[ Miniport Driver: NIC     ]"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("AVAILABLE SINCE: WINDOWS VISTA / NDIS 6.0")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("LWFs were introduced with NDIS 6.0 in Windows Vista (2007). Before Vista, intercepting packets at the NDIS level required an NDIS Intermediate Driver (IMD) — a complex driver that had to present itself as both a virtual miniport (to the protocol above) and a protocol (to the miniport below), requiring two separate binding operations. LWFs replaced IMDs for filtering scenarios: they attach as a lightweight module to a real adapter without creating virtual devices.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Multiple LWFs can attach to the same adapter simultaneously. Order is determined by altitude — a numeric value defined in the INF file, the same concept as filesystem minifilter altitude. Higher altitude = closer to the protocol (TCP/IP) side; lower altitude = closer to the NIC.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("STRUCTURE AND COMPONENTS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("An NDIS LWF driver consists of the following key pieces:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "NDIS_FILTER_DRIVER_CHARACTERISTICS\n" +
            "  Registration struct filled in DriverEntry.\n" +
            "  Holds all callback function pointers.\n\n" +
            "NDIS_FILTER_ATTRIBUTES\n" +
            "  Per-adapter binding struct.\n" +
            "  Passed to NdisFSetAttributes() in AttachHandler.\n\n" +
            "NET_BUFFER_LIST (NBL)\n" +
            "  Packet container. One NBL can hold multiple\n" +
            "  NET_BUFFERs (e.g. a GSO batch). NBLs are passed\n" +
            "  to send/receive callbacks.\n\n" +
            "NET_BUFFER\n" +
            "  One packet. Contains an MDL chain pointing to\n" +
            "  the actual data bytes.\n\n" +
            "INF file  (required for installation)\n" +
            "  Specifies FilterClass, FilterMediaTypes,\n" +
            "  altitude value, and driver service name.\n" +
            "  FilterMediaTypes: ethernet, wan, ppip, native_wifi, ..."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("REGISTRATION API")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("In DriverEntry, fill out NDIS_FILTER_DRIVER_CHARACTERISTICS and call NdisFRegisterFilterDriver. On unload, call NdisFDeregisterFilterDriver.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// DriverEntry\n" +
            "NDIS_FILTER_DRIVER_CHARACTERISTICS chars = {0};\n" +
            "chars.Header.Type     =\n" +
            "    NDIS_OBJECT_TYPE_FILTER_DRIVER_CHARACTERISTICS;\n" +
            "chars.Header.Revision =\n" +
            "    NDIS_FILTER_CHARACTERISTICS_REVISION_3;\n" +
            "chars.Header.Size     =\n" +
            "    NDIS_SIZEOF_FILTER_DRIVER_CHARACTERISTICS_REVISION_3;\n" +
            "chars.MajorNdisVersion = NDIS_FILTER_MAJOR_VERSION;  // 6\n" +
            "chars.MinorNdisVersion = NDIS_FILTER_MINOR_VERSION;  // 20 for Win10+\n" +
            "chars.FriendlyName    = FRIENDLY_NAME;\n" +
            "chars.UniqueName      = UNIQUE_NAME;  // GUID string, matches INF\n" +
            "chars.ServiceName     = SERVICE_NAME; // registry service name\n\n" +
            "chars.AttachHandler                = FilterAttach;\n" +
            "chars.DetachHandler                = FilterDetach;\n" +
            "chars.RestartHandler               = FilterRestart;\n" +
            "chars.PauseHandler                 = FilterPause;\n" +
            "chars.SendNetBufferListsHandler    = FilterSendNetBufferLists;\n" +
            "chars.ReceiveNetBufferListsHandler = FilterReceiveNetBufferLists;\n" +
            "// optional: OID, status, net PnP handlers\n\n" +
            "NdisFRegisterFilterDriver(\n" +
            "    driverObject,\n" +
            "    (NDIS_HANDLE)driverContext,\n" +
            "    &chars,\n" +
            "    &gFilterDriverHandle);\n\n" +
            "// DriverUnload\n" +
            "NdisFDeregisterFilterDriver(gFilterDriverHandle);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ATTACH AND DETACH")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("NDIS calls FilterAttach when the filter should bind to an adapter (on driver load or adapter arrival). The filter allocates a per-adapter context and registers it via NdisFSetAttributes. FilterDetach is called on adapter removal or driver unload.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "NDIS_STATUS FilterAttach(\n" +
            "    NDIS_HANDLE ndisFilterHandle,\n" +
            "    NDIS_HANDLE filterDriverContext,\n" +
            "    PNDIS_FILTER_ATTACH_PARAMETERS attachParameters)\n" +
            "{\n" +
            "    MY_FILTER_CTX *ctx = ExAllocatePool2(\n" +
            "        POOL_FLAG_NON_PAGED, sizeof(*ctx), 'FltT');\n" +
            "    ctx->FilterHandle = ndisFilterHandle;\n\n" +
            "    NDIS_FILTER_ATTRIBUTES attrs = {0};\n" +
            "    attrs.Header.Type     =\n" +
            "        NDIS_OBJECT_TYPE_FILTER_ATTRIBUTES;\n" +
            "    attrs.Header.Revision =\n" +
            "        NDIS_FILTER_ATTRIBUTES_REVISION_1;\n" +
            "    attrs.Header.Size     = sizeof(NDIS_FILTER_ATTRIBUTES);\n" +
            "    attrs.Flags           = 0;\n\n" +
            "    return NdisFSetAttributes(ndisFilterHandle, ctx, &attrs);\n" +
            "}\n\n" +
            "void FilterDetach(NDIS_HANDLE filterModuleContext) {\n" +
            "    ExFreePoolWithTag(filterModuleContext, 'FltT');\n" +
            "}"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("FilterPause and FilterRestart are also mandatory. They bracket the active packet-processing window. Handlers must not send or receive packets while the filter is paused.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SEND AND RECEIVE HANDLERS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The send handler intercepts outbound packets (host → wire), the receive handler intercepts inbound packets (wire → host). Forward the NBL to pass it through; complete it locally to block.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Outbound: host → wire\n" +
            "void FilterSendNetBufferLists(\n" +
            "    NDIS_HANDLE filterModuleContext,\n" +
            "    PNET_BUFFER_LIST netBufferLists,\n" +
            "    NDIS_PORT_NUMBER portNumber,\n" +
            "    ULONG sendFlags)\n" +
            "{\n" +
            "    MY_FILTER_CTX *ctx = filterModuleContext;\n" +
            "    // inspect NBLs here, then pass through:\n" +
            "    NdisFSendNetBufferLists(\n" +
            "        ctx->FilterHandle,\n" +
            "        netBufferLists, portNumber, sendFlags);\n" +
            "}\n\n" +
            "// Inbound: wire → host\n" +
            "void FilterReceiveNetBufferLists(\n" +
            "    NDIS_HANDLE filterModuleContext,\n" +
            "    PNET_BUFFER_LIST netBufferLists,\n" +
            "    NDIS_PORT_NUMBER portNumber,\n" +
            "    ULONG numberOfNetBufferLists,\n" +
            "    ULONG receiveFlags)\n" +
            "{\n" +
            "    MY_FILTER_CTX *ctx = filterModuleContext;\n" +
            "    NdisFIndicateReceiveNetBufferLists(\n" +
            "        ctx->FilterHandle, netBufferLists,\n" +
            "        portNumber, numberOfNetBufferLists, receiveFlags);\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("MONITOR / BLOCK / MODIFY / INJECT")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "MONITOR\n" +
            "  Yes. Inspect every packet in send and receive handlers.\n" +
            "  Read NBL data; forwarding behavior is unchanged.\n\n" +
            "BLOCK\n" +
            "  Yes. Do not forward the NBL.\n" +
            "  Send path: call NdisFSendNetBufferListsComplete()\n" +
            "             with NDIS_STATUS_FAILURE on the NBL.\n" +
            "  Recv path: call NdisReturnNetBufferLists() instead\n" +
            "             of NdisFIndicateReceiveNetBufferLists.\n\n" +
            "MODIFY\n" +
            "  Yes. Edit the MDL chain data before forwarding.\n" +
            "  Common for NAT, VPN encapsulation, header rewriting.\n" +
            "  Edits must stay within the allocated buffer bounds.\n\n" +
            "INJECT\n" +
            "  Yes. Build new NBLs and send them:\n" +
            "  Outbound: NdisFSendNetBufferLists()\n" +
            "  Inbound:  NdisFIndicateReceiveNetBufferLists()\n" +
            "  Allocate with NdisFAllocateNetBufferListPool()."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("FIREWALL AND VPN")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Firewall: Yes, an NDIS LWF can act as a stateless packet filter — drop or allow packets by IP range, port, or protocol. WFP is generally preferred for stateful or process-aware firewalling because it has ALE layers with socket context. An NDIS LWF firewall must parse raw Ethernet/IP/TCP headers itself.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("VPN: Yes. A typical VPN driver creates a virtual network adapter (via an NDIS miniport), then attaches an LWF to the physical adapter. The LWF intercepts outbound packets, encrypts and encapsulates them, and injects them as tunnel packets. On the inbound side it decapsulates and decrypts, then injects via the virtual adapter so TCP/IP sees a clean packet.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("COMMON USE CASES")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Network monitoring / packet capture\n" +
            "  Wireshark on Windows uses NPcap, which is an LWF.\n" +
            "  Sees all raw packets on the adapter.\n\n" +
            "Firewall\n" +
            "  Stateless filtering by IP range, port, protocol.\n\n" +
            "VPN / Tunneling\n" +
            "  Encrypt/encapsulate outbound, decrypt/decapsulate\n" +
            "  inbound, inject via virtual adapter.\n\n" +
            "QoS (Quality of Service)\n" +
            "  Inspect/mark DSCP fields, throttle flows per adapter.\n\n" +
            "Network diagnostics\n" +
            "  Inject crafted packets for testing, fault injection,\n" +
            "  protocol fuzzing."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("CAN IT IDENTIFY THE SENDING PROCESS?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("No. NDIS LWF operates below the transport layer — it sees raw Ethernet frames. There is no process context at this level. NDIS has no concept of sockets, connections, or applications. A packet is just bytes with source/destination MAC addresses and (once parsed) IP headers. There is no kernel-supported way to know which process produced or will consume a given NET_BUFFER_LIST.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("COMBINING WITH WFP FOR PROCESS IDENTITY")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("WFP's ALE (Application Layer Enforcement) layers intercept at the socket level — before a packet enters the NDIS stack — where the kernel does know the process:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "WFP FWPS_LAYER_ALE_FLOW_ESTABLISHED_V4 exposes:\n" +
            "  FWP_CONDITION_ALE_APP_ID    path to the process binary\n" +
            "  FWPS_CONDITION_ALE_USER_ID  SID of the owning user\n" +
            "  processId                   numeric PID\n" +
            "  local IP + local port  ┐\n" +
            "  remote IP + remote port┘  the socket 4-tuple"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Standard enterprise security pattern combining both:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "1. WFP callout at ALE_FLOW_ESTABLISHED_V4:\n" +
            "     On each new TCP connection, record:\n" +
            "       { srcIP, srcPort, dstIP, dstPort } → { path, PID }\n" +
            "     Store in a spin-lock-protected hash table.\n\n" +
            "2. NDIS LWF in send/receive handlers:\n" +
            "     Parse IP + TCP/UDP header from NBL → 4-tuple.\n" +
            "     Look up the hash table by 4-tuple.\n" +
            "     Result: the process that owns this flow.\n\n" +
            "3. Act based on process identity:\n" +
            "     Allow / block / log / tag as needed."
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("For TCP this lookup is reliable — the 4-tuple is stable for the connection lifetime. For UDP it is less precise since there is no established connection state.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("This is the industry-standard approach used by virtually all endpoint security products: Windows Defender, CrowdStrike Falcon, Zscaler, Palo Alto Cortex, and most modern EDR/DLP/NGFW solutions combine WFP ALE (process identity) with NDIS or WFP stream-layer callouts (per-packet enforcement).")

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
