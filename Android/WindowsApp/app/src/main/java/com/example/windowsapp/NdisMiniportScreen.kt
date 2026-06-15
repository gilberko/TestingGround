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
fun NdisMiniportScreen(navController: NavController) {
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
            text = "NDIS MINIPORT",
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

        SectionHeader("WHAT IS AN NDIS MINIPORT?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("An NDIS miniport driver is the lowest layer of the NDIS network stack — it represents a network adapter to the OS. Unlike an LWF which hooks into an existing adapter, a miniport IS the adapter. The OS TCP/IP stack and other protocols bind to it through NDIS. A virtual miniport requires no real hardware: Windows sees it as a real NIC and routes traffic through it like any other adapter.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "[ Protocol Driver: TCP/IP  ]\n" +
            "          ↕\n" +
            "[ NDIS Core               ]\n" +
            "          ↕\n" +
            "[ Miniport Driver         ]  ← IS the adapter\n" +
            "   (real NIC or virtual)  "
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("NDIS_MINIPORT_DRIVER DEFINE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("ndis.h is a shared WDK header used by miniport drivers, LWF filter drivers, and protocol drivers. Miniport-specific declarations are gated behind a preprocessor flag. You must define NDIS_MINIPORT_DRIVER before including ndis.h, or the compiler will not see the core miniport APIs and structures.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Must appear BEFORE #include <ndis.h>\n" +
            "#define NDIS_MINIPORT_DRIVER 1\n" +
            "#include <ndis.h>\n\n" +
            "// Without this define, the compiler won't find:\n" +
            "//   NDIS_MINIPORT_DRIVER_CHARACTERISTICS\n" +
            "//   NdisMRegisterMiniportDriver\n" +
            "//   NdisMDeregisterMiniportDriver\n" +
            "//   NdisMSetMiniportAttributes\n" +
            "//   NdisMSendNetBufferListsComplete\n" +
            "//   NdisMIndicateReceiveNetBufferLists\n" +
            "//   ...and all miniport callback typedefs\n\n" +
            "// WHERE TO PUT IT — three options:\n" +
            "// 1. Top of each .c/.cpp file, before any includes\n" +
            "// 2. In pch.h (precompiled header) — before ndis.h\n" +
            "// 3. Project preprocessor definitions (.vcxproj)\n" +
            "//    → C/C++ → Preprocessor → Preprocessor Definitions\n\n" +
            "// LWF filter drivers do NOT need an equivalent flag —\n" +
            "// filter APIs are always visible after #include <ndis.h>"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("MINIPORT VS LWF")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "                    LWF              Virtual Miniport\n" +
            "Represents adapter  No               Yes\n" +
            "Needs existing NIC  Yes              No\n" +
            "Creates virtual NIC No               Yes\n" +
            "Typical uses        Monitor/filter   TUN/TAP, VPN\n" +
            "Stack position      Between layers   Bottom (is adapter)"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("STRUCTURE AND COMPONENTS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The core registration structure is NDIS_MINIPORT_DRIVER_CHARACTERISTICS, filled in DriverEntry and passed to NdisMRegisterMiniportDriver. Key fields and callbacks:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Header.Type     = NDIS_OBJECT_TYPE_MINIPORT_DRIVER_CHARACTERISTICS\n" +
            "Header.Revision = NDIS_MINIPORT_DRIVER_CHARACTERISTICS_REVISION_3\n" +
            "Header.Size     = NDIS_SIZEOF_MINIPORT_DRIVER_CHARACTERISTICS_REVISION_3\n\n" +
            "Required callbacks:\n" +
            "  MiniportInitializeEx        // adapter instantiated\n" +
            "  MiniportHaltEx              // adapter removed\n" +
            "  MiniportSendNetBufferLists  // OS wants to send packets\n" +
            "  MiniportReturnNetBufferLists// OS returns received NBLs\n" +
            "  MiniportOidRequest          // OID queries and sets\n" +
            "  MiniportPause               // pause (required lifecycle)\n" +
            "  MiniportRestart             // restart after pause\n" +
            "  MiniportCheckForHangEx      // return FALSE for virtual\n" +
            "  MiniportResetEx             // return NDIS_STATUS_SUCCESS"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("An INF file is required: NetAdapter component class, AddReg entries for Characteristics, BusType, MediaType, and service name.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("REGISTRATION API")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// DriverEntry\n" +
            "NDIS_STATUS NdisMRegisterMiniportDriver(\n" +
            "    PDRIVER_OBJECT                      DriverObject,\n" +
            "    PUNICODE_STRING                     RegistryPath,\n" +
            "    NDIS_HANDLE                         MiniportDriverContext,\n" +
            "    NDIS_MINIPORT_DRIVER_CHARACTERISTICS *Characteristics,\n" +
            "    NDIS_HANDLE                         *NdisMiniportDriverHandle);\n\n" +
            "// DriverUnload\n" +
            "VOID NdisMDeregisterMiniportDriver(NDIS_HANDLE NdisMiniportDriverHandle);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("INITIALIZE AND HALT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("MiniportInitializeEx is called when Windows instantiates the adapter. You must call NdisMSetMiniportAttributes twice — once for registration attributes and once for general attributes:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Step 1: registration attributes\n" +
            "NDIS_MINIPORT_ADAPTER_REGISTRATION_ATTRIBUTES regAttrs = {0};\n" +
            "regAttrs.Header.Type     = NDIS_OBJECT_TYPE_MINIPORT_ADAPTER_REG_ATTRIBUTES;\n" +
            "regAttrs.Header.Revision = NDIS_MINIPORT_ADAPTER_REG_ATTRIBUTES_REVISION_1;\n" +
            "regAttrs.Header.Size     = sizeof(regAttrs);\n" +
            "regAttrs.MiniportAdapterContext = pContext;\n" +
            "NdisMSetMiniportAttributes(AdapterHandle,\n" +
            "    (PNDIS_MINIPORT_ADAPTER_ATTRIBUTES)&regAttrs);\n\n" +
            "// Step 2: general attributes\n" +
            "NDIS_MINIPORT_ADAPTER_GENERAL_ATTRIBUTES genAttrs = {0};\n" +
            "genAttrs.MediaType            = NdisMedium802_3;  // Ethernet\n" +
            "genAttrs.PhysicalMediumType   = NdisPhysicalMediumUnspecified;\n" +
            "genAttrs.MtuSize              = 1500;\n" +
            "genAttrs.MaxXmitLinkSpeed     = 1000000000ULL;  // 1 Gbps\n" +
            "genAttrs.MaxRcvLinkSpeed      = 1000000000ULL;\n" +
            "genAttrs.MacOptions           = NDIS_MAC_OPTION_NO_LOOPBACK;\n" +
            "genAttrs.SupportedPacketFilters =\n" +
            "    NDIS_PACKET_TYPE_DIRECTED  |\n" +
            "    NDIS_PACKET_TYPE_MULTICAST |\n" +
            "    NDIS_PACKET_TYPE_ALL_MULTICAST |\n" +
            "    NDIS_PACKET_TYPE_BROADCAST;\n" +
            "NdisMSetMiniportAttributes(AdapterHandle,\n" +
            "    (PNDIS_MINIPORT_ADAPTER_ATTRIBUTES)&genAttrs);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("MiniportHaltEx: stop the adapter, free all allocated resources. MiniportCheckForHangEx: always return FALSE for virtual adapters (no hardware to hang).")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SEND HANDLER — OUTBOUND PACKETS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("MiniportSendNetBufferLists is called when the OS wants to send data through this adapter. For a VPN virtual adapter, this is where you intercept the plaintext packets:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "VOID MiniportSendNetBufferLists(\n" +
            "    NDIS_HANDLE      MiniportAdapterContext,\n" +
            "    PNET_BUFFER_LIST NetBufferLists,\n" +
            "    NDIS_PORT_NUMBER PortNumber,\n" +
            "    ULONG            SendFlags)\n" +
            "{\n" +
            "    PNET_BUFFER_LIST nbl = NetBufferLists;\n" +
            "    while (nbl) {\n" +
            "        PNET_BUFFER nb = NET_BUFFER_LIST_FIRST_NB(nbl);\n" +
            "        // nb->CurrentMdl points to packet data\n" +
            "        // NdisQueryMdl / NdisGetMdlVirtualAddress to read\n" +
            "        // Copy data, queue to user-mode via IOCTL or ring\n" +
            "        NET_BUFFER_LIST_STATUS(nbl) = NDIS_STATUS_SUCCESS;\n" +
            "        nbl = NET_BUFFER_LIST_NEXT_NBL(nbl);\n" +
            "    }\n" +
            "    NdisMSendNetBufferListsComplete(\n" +
            "        AdapterHandle, NetBufferLists,\n" +
            "        NDIS_SEND_COMPLETE_FLAGS_DISPATCH_LEVEL);\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("INJECT RECEIVED PACKETS — INBOUND")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Call NdisMIndicateReceiveNetBufferLists to tell the OS that packets have arrived on this adapter. For VPN: the user-mode component decrypts the data and feeds it here so the OS delivers it to the right socket:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Allocate an NBL pool first (typically in MiniportInitializeEx):\n" +
            "NDIS_HANDLE NblPool = NdisAllocateNetBufferListPool(\n" +
            "    AdapterHandle, &poolParams);\n\n" +
            "// When injecting a received frame:\n" +
            "PNET_BUFFER_LIST pNbl = NdisAllocateNetBufferAndNetBufferList(\n" +
            "    NblPool, 0, 0, pMdl, 0, dataLength);\n\n" +
            "NdisMIndicateReceiveNetBufferLists(\n" +
            "    NdisMiniportHandle,\n" +
            "    pNbl,\n" +
            "    NDIS_DEFAULT_PORT_NUMBER,\n" +
            "    1,    // number of NBLs\n" +
            "    NDIS_RECEIVE_FLAGS_DISPATCH_LEVEL);\n\n" +
            "// OS returns NBLs to MiniportReturnNetBufferLists — free there"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHAT ARE OIDs")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("OIDs (Object Identifiers) are 4-byte integer constants defined in ndis.h and ntddndis.h. NDIS uses them as a standardized vocabulary to query state from an adapter or push configuration into it — without knowing anything about the underlying hardware. Two operations exist: query (OS reads current state) and set (OS writes new configuration).")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("OIDs are grouped by prefix:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "OID_GEN_*          // general: link speed, MTU, packet filter,\n" +
            "                   //          vendor description, statistics\n" +
            "OID_802_3_*        // Ethernet: current MAC, permanent MAC,\n" +
            "                   //           multicast list\n" +
            "OID_TCP_TASK_*     // offloads: checksum (TX/RX), LSO,\n" +
            "                   //           RSS configuration\n" +
            "OID_GEN_STATISTICS // counters: bytes/frames sent and received\n\n" +
            "// Examples:\n" +
            "OID_GEN_MAXIMUM_TOTAL_SIZE    // max frame size incl. header\n" +
            "OID_GEN_LINK_SPEED            // speed in units of 100 bps\n" +
            "OID_GEN_CURRENT_PACKET_FILTER // bitmask: directed/broadcast/\n" +
            "                              //          multicast/promiscuous\n" +
            "OID_GEN_VENDOR_DESCRIPTION    // human-readable adapter name\n" +
            "OID_802_3_CURRENT_ADDRESS     // active 6-byte MAC address\n" +
            "OID_802_3_PERMANENT_ADDRESS   // factory-burned MAC address\n" +
            "OID_TCP_TASK_OFFLOAD          // checksum and LSO capabilities"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("OID requests arrive through MiniportOidRequest. The NDIS_OID_REQUEST structure carries: RequestType (NdisRequestQueryInformation / NdisRequestSetInformation / NdisRequestMethod), the OID code, InformationBuffer, InformationBufferLength, and output fields BytesWritten / BytesNeeded. The driver fills BytesWritten for queries and returns NDIS_STATUS_SUCCESS, NDIS_STATUS_BUFFER_TOO_SHORT, or NDIS_STATUS_NOT_SUPPORTED.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("OID HANDLING")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("MiniportOidRequest handles queries and sets from the OS. Mandatory OIDs for a virtual Ethernet adapter:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "OID_GEN_CURRENT_PACKET_FILTER  // set/query packet filter bitmask\n" +
            "OID_GEN_CURRENT_LOOKAHEAD      // lookahead buffer size\n" +
            "OID_GEN_LINK_SPEED             // link speed\n" +
            "OID_802_3_CURRENT_ADDRESS      // current MAC address\n" +
            "OID_802_3_PERMANENT_ADDRESS    // permanent MAC address\n\n" +
            "// Return NDIS_STATUS_NOT_SUPPORTED for all other OIDs"
        )

        Spacer(modifier = Modifier.height(8.dp))
        SectionHeader("OID HANDLING — REAL HARDWARE DEVICE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("For a real NIC, OID queries read actual values from hardware registers or firmware, and OID sets reprogram hardware. The OS trusts these values for flow control, routing, and offload decisions.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "case OID_802_3_CURRENT_ADDRESS:\n" +
            "case OID_802_3_PERMANENT_ADDRESS:\n" +
            "    // MAC burned into EEPROM — read it once at init,\n" +
            "    // store in context, return the stored bytes here\n" +
            "    NdisMoveMemory(\n" +
            "        pReq->DATA.QUERY_INFORMATION.InformationBuffer,\n" +
            "        pCtx->MacAddress, 6);\n" +
            "    pReq->DATA.QUERY_INFORMATION.BytesWritten = 6;\n" +
            "    return NDIS_STATUS_SUCCESS;\n\n" +
            "case OID_GEN_LINK_SPEED:\n" +
            "    // Read auto-negotiated speed from PHY register;\n" +
            "    // unit is 100 bps: 1 Gbps = 10,000,000\n" +
            "    *(ULONG*)pReq->DATA.QUERY_INFORMATION.InformationBuffer =\n" +
            "        ReadPhyRegister(pCtx, PHY_SPEED_REG) * 10000000;\n" +
            "    pReq->DATA.QUERY_INFORMATION.BytesWritten = sizeof(ULONG);\n" +
            "    return NDIS_STATUS_SUCCESS;\n\n" +
            "case OID_GEN_CURRENT_PACKET_FILTER:\n" +
            "    if (pReq->RequestType == NdisRequestSetInformation) {\n" +
            "        ULONG filter = *(ULONG*)pReq->DATA.SET_INFORMATION\n" +
            "                                    .InformationBuffer;\n" +
            "        // Write filter bitmask to NIC receive filter register\n" +
            "        // so hardware only DMA-s matching frames\n" +
            "        WriteNicRegister(pCtx, NIC_RX_FILTER_REG, filter);\n" +
            "    }\n" +
            "    return NDIS_STATUS_SUCCESS;"
        )

        Spacer(modifier = Modifier.height(8.dp))
        SectionHeader("OID HANDLING — VIRTUAL / VPN DEVICE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A virtual adapter has no hardware to read or program. All OID responses come from values stored in the adapter context at initialization. Sets that would configure hardware are simply saved back to the context — there is nothing to write to. OID_GEN_CURRENT_PACKET_FILTER is stored so the driver knows what traffic to inject; OID_802_3_CURRENT_ADDRESS returns a statically generated or configurable MAC.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// MiniportInitializeEx — seed context with fixed defaults\n" +
            "pCtx->MacAddress[0] = 0x02;  // locally administered bit set\n" +
            "pCtx->MacAddress[1] = 0xAB;\n" +
            "pCtx->MacAddress[2] = 0xCD;\n" +
            "pCtx->MacAddress[3] = 0xEF;\n" +
            "pCtx->MacAddress[4] = 0x01;\n" +
            "pCtx->MacAddress[5] = 0x02;\n" +
            "pCtx->LinkSpeedIn100Bps = 10000000ULL;  // report 1 Gbps\n" +
            "pCtx->PacketFilter      = 0;\n\n" +
            "// MiniportOidRequest — virtual dispatch\n" +
            "case OID_802_3_CURRENT_ADDRESS:\n" +
            "case OID_802_3_PERMANENT_ADDRESS:\n" +
            "    NdisMoveMemory(buf, pCtx->MacAddress, 6);\n" +
            "    pReq->DATA.QUERY_INFORMATION.BytesWritten = 6;\n" +
            "    return NDIS_STATUS_SUCCESS;\n\n" +
            "case OID_GEN_LINK_SPEED:\n" +
            "    *(ULONG*)buf = (ULONG)pCtx->LinkSpeedIn100Bps;\n" +
            "    pReq->DATA.QUERY_INFORMATION.BytesWritten = sizeof(ULONG);\n" +
            "    return NDIS_STATUS_SUCCESS;\n\n" +
            "case OID_GEN_CURRENT_PACKET_FILTER:\n" +
            "    if (isSet)\n" +
            "        pCtx->PacketFilter = *(ULONG*)buf; // save; nothing to program\n" +
            "    else\n" +
            "        *(ULONG*)buf = pCtx->PacketFilter;\n" +
            "    return NDIS_STATUS_SUCCESS;"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Real miniport = hardware I/O for every OID. Virtual miniport = context struct I/O for every OID. The NDIS_OID_REQUEST structure, status codes, and MiniportOidRequest signature are identical in both cases — NDIS never knows the difference.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("VPN IMPLEMENTATION — ROUTING SETUP")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A software VPN using a virtual miniport works by routing all traffic through the virtual adapter, with one exception: the VPN server itself must still be reachable via the real NIC (otherwise the VPN tunnel traffic would loop through itself).")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Routing table after VPN connects:\n" +
            "  0.0.0.0/0       via virtual adapter   (all traffic)\n" +
            "  <VPN_SERVER_IP> via real NIC gateway   (exception: tunnel itself)\n" +
            "  10.x.x.x/24    via virtual adapter    (VPN internal subnet)\n\n" +
            "Windows route commands:\n" +
            "  route add 0.0.0.0 mask 0.0.0.0 <virtual_gw> metric 1\n" +
            "  route add <vpn_server_ip> mask 255.255.255.255 <real_gw>"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("VPN IMPLEMENTATION — OUTBOUND PATH")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "App sends data\n" +
            " └─ OS routes via virtual adapter\n" +
            "     └─ MiniportSendNetBufferLists fires\n" +
            "         └─ Driver reads plaintext Ethernet/IP frames\n" +
            "             └─ Passes to user-mode (IOCTL / shared ring)\n" +
            "                 └─ User-mode encrypts (e.g. AES-GCM)\n" +
            "                     └─ Sends encrypted UDP to VPN server\n" +
            "                         via real NIC socket"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("VPN IMPLEMENTATION — INBOUND PATH")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Real NIC receives encrypted UDP from VPN server\n" +
            " └─ User-mode UDP socket receives the datagram\n" +
            "     └─ User-mode decrypts → raw Ethernet/IP frame\n" +
            "         └─ Passes decrypted frame to driver (IOCTL)\n" +
            "             └─ Driver allocates NBL, fills MDL\n" +
            "                 └─ NdisMIndicateReceiveNetBufferLists()\n" +
            "                     └─ OS delivers packet to app socket\n" +
            "                         as if it arrived from the network"
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
