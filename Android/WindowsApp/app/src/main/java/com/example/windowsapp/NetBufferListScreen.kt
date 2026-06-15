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
fun NetBufferListScreen(navController: NavController) {
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
            text = "NET_BUFFER_LIST",
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

        // WHAT IS IT
        SectionHeader("WHAT IS IT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "_NET_BUFFER_LIST (NBL) is the primary packet descriptor in NDIS 6+. It replaced " +
            "the old NDIS 5 NDIS_PACKET structure and is used by protocol, filter, and " +
            "miniport drivers to send and receive network packets.\n\n" +
            "NBLs flow up the receive path (miniport → filter → protocol) and down the " +
            "send path (protocol → filter → miniport). Each layer may inspect, modify, or " +
            "generate NBLs."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // STRUCTURE HIERARCHY
        SectionHeader("STRUCTURE HIERARCHY")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "An NBL contains a linked list of NET_BUFFERs. Each NET_BUFFER represents " +
            "one network packet and contains a chain of MDLs describing the packet's " +
            "physical memory."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "NET_BUFFER_LIST  ──next──>  NET_BUFFER_LIST  ──> ...\n" +
            "  │\n" +
            "  └── NET_BUFFER  ──next──>  NET_BUFFER  ──> ...\n" +
            "        │\n" +
            "        └── MDL  ──next──>  MDL  ──> ...\n" +
            "              (physical memory segments of the packet)"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Multiple NBLs can be chained via NET_BUFFER_LIST_NEXT_NBL(nbl). Drivers " +
            "typically pass the entire chain to the layer above or below, which processes " +
            "each NBL and each NET_BUFFER within it."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // NET_BUFFER FIELDS
        SectionHeader("NET_BUFFER FIELDS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Next              — next NET_BUFFER in this NBL's list\n\n" +
            "MdlChain          — first MDL in the chain describing packet data\n\n" +
            "DataOffset        — byte offset into the MdlChain data where the packet starts\n" +
            "                    (allows prepending headers without copying)\n\n" +
            "DataLength        — total length of the packet data in bytes\n\n" +
            "CurrentMdl        — MDL that contains the current data position\n" +
            "CurrentMdlOffset  — byte offset within CurrentMdl"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // NBL METADATA
        SectionHeader("NBL METADATA")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The NetBufferListInfo[] array (also called OOB data — out-of-band) stores " +
            "per-layer packet annotations. Accessed via the NET_BUFFER_LIST_INFO macro:\n"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Read TCP/IP checksum offload flags\n" +
            "NDIS_TCP_IP_CHECKSUM_NET_BUFFER_LIST_INFO csum;\n" +
            "csum.Value = (ULONG)(ULONG_PTR)NET_BUFFER_LIST_INFO(\n" +
            "    nbl, TcpIpChecksumNetBufferListInfo);\n\n" +
            "// Read VLAN tag\n" +
            "NDIS_NET_BUFFER_LIST_8021Q_INFO dot1q;\n" +
            "dot1q.Value = (ULONG)(ULONG_PTR)NET_BUFFER_LIST_INFO(\n" +
            "    nbl, Ieee8021QNetBufferListInfo);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Common annotations: IP/TCP/UDP checksum offload status, VLAN tag (802.1Q), " +
            "TCP large-send offload (LSO) parameters, receive-side scaling (RSS) hash " +
            "value and type, IPsec offload info."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ALLOCATION
        SectionHeader("ALLOCATION")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// 1. Create a pool (typically in DriverEntry or FilterAttach)\n" +
            "NET_BUFFER_LIST_POOL_PARAMETERS params = {0};\n" +
            "params.Header.Type        = NDIS_OBJECT_TYPE_DEFAULT;\n" +
            "params.Header.Revision    = NET_BUFFER_LIST_POOL_PARAMETERS_REVISION_1;\n" +
            "params.Header.Size        = sizeof(params);\n" +
            "params.fAllocateNetBuffer = TRUE;\n" +
            "params.PoolTag            = 'lBtN';\n" +
            "NDIS_HANDLE pool = NdisAllocateNetBufferListPool(\n" +
            "    ndisHandle, &params);\n\n" +
            "// 2. Allocate an NBL (and a NET_BUFFER) from the pool\n" +
            "PNET_BUFFER_LIST nbl =\n" +
            "    NdisAllocateNetBufferAndNetBufferList(\n" +
            "        pool,\n" +
            "        0,        // ContextSize\n" +
            "        0,        // ContextBackFill\n" +
            "        mdlChain, // your MDL chain\n" +
            "        dataOffset,\n" +
            "        dataLength);\n\n" +
            "// 3. Free when done\n" +
            "NdisFreeNetBufferList(nbl);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ITERATING
        SectionHeader("ITERATING NBL CHAINS")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "for (PNET_BUFFER_LIST nbl = nblChain;\n" +
            "     nbl != NULL;\n" +
            "     nbl = NET_BUFFER_LIST_NEXT_NBL(nbl))\n" +
            "{\n" +
            "    for (PNET_BUFFER nb = NET_BUFFER_LIST_FIRST_NB(nbl);\n" +
            "         nb != NULL;\n" +
            "         nb = NET_BUFFER_NEXT_NB(nb))\n" +
            "    {\n" +
            "        PUCHAR data = MmGetSystemAddressForMdlSafe(\n" +
            "            NET_BUFFER_FIRST_MDL(nb),\n" +
            "            NormalPagePriority);\n" +
            "        ULONG len = NET_BUFFER_DATA_LENGTH(nb);\n" +
            "        // process [data, data+len)\n" +
            "    }\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // OWNERSHIP AND COMPLETION
        SectionHeader("OWNERSHIP AND COMPLETION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "NDIS enforces strict ownership rules. You may only free or return an NBL " +
            "that you own.\n\n" +
            "Send path (protocol → filter → miniport):\n" +
            "  When the miniport is done, it calls NdisMSendNetBufferListsComplete().\n" +
            "  This returns ownership up the stack to the originating protocol driver.\n\n" +
            "Receive path (miniport → filter → protocol):\n" +
            "  After the protocol is done with received NBLs, it calls\n" +
            "  NdisReturnNetBufferLists() to return them to the miniport.\n\n" +
            "Always check NET_BUFFER_LIST_STATUS(nbl) in send-complete callbacks to " +
            "determine whether each NBL was transmitted successfully."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
