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
fun WinSockScreen(navController: NavController) {
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
            text = "WINSOCK",
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

        SectionHeader("INITIALIZATION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Every WinSock application must call WSAStartup before using any socket functions, and WSACleanup when done. Link with Ws2_32.lib.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "WSADATA wsaData;\n" +
            "int result = WSAStartup(MAKEWORD(2, 2), &wsaData);\n" +
            "if (result != 0) { /* handle error */ }\n\n" +
            "// ... use sockets ...\n\n" +
            "WSACleanup();"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("MAKEWORD(2,2) requests Winsock version 2.2, the current standard. WSAGetLastError() returns the last WinSock error code (analogous to GetLastError).")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("TCP CLIENT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("TCP provides a reliable, ordered byte stream. As a client, you create a socket, connect to the server, then send and receive data.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "SOCKET sock = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);\n\n" +
            "sockaddr_in addr = {0};\n" +
            "addr.sin_family = AF_INET;\n" +
            "addr.sin_port   = htons(80);  // host-to-network byte order\n" +
            "inet_pton(AF_INET, \"93.184.216.34\", &addr.sin_addr);\n\n" +
            "connect(sock, (sockaddr*)&addr, sizeof(addr));\n\n" +
            "const char* req = \"GET / HTTP/1.0\\r\\n\\r\\n\";\n" +
            "send(sock, req, (int)strlen(req), 0);\n\n" +
            "char buf[4096];\n" +
            "int n;\n" +
            "while ((n = recv(sock, buf, sizeof(buf)-1, 0)) > 0) {\n" +
            "    buf[n] = '\\0';\n" +
            "    printf(\"%s\", buf);\n" +
            "}\n\n" +
            "closesocket(sock);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("send() may not send all bytes in one call on large buffers — loop until all bytes are sent. recv() returns 0 when the server closes the connection gracefully.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("UDP CLIENT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("UDP is connectionless — no handshake. You send datagrams directly to an address. Each sendto/recvfrom call is one independent datagram.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "SOCKET sock = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP);\n\n" +
            "sockaddr_in server = {0};\n" +
            "server.sin_family = AF_INET;\n" +
            "server.sin_port   = htons(9999);\n" +
            "inet_pton(AF_INET, \"127.0.0.1\", &server.sin_addr);\n\n" +
            "const char* msg = \"hello\";\n" +
            "sendto(sock, msg, (int)strlen(msg), 0,\n" +
            "       (sockaddr*)&server, sizeof(server));\n\n" +
            "char buf[1024];\n" +
            "sockaddr_in from = {0};\n" +
            "int fromLen = sizeof(from);\n" +
            "int n = recvfrom(sock, buf, sizeof(buf)-1, 0,\n" +
            "                 (sockaddr*)&from, &fromLen);\n\n" +
            "closesocket(sock);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("You can optionally call connect() on a UDP socket to set a default destination — after that you can use send/recv instead of sendto/recvfrom. This does not create a connection; it just sets the default target address.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("TCP SERVER")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A TCP server creates a listening socket, accepts incoming connections one by one (each returning a new socket), then communicates on each client socket independently.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "SOCKET listenSock = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);\n\n" +
            "sockaddr_in addr = {0};\n" +
            "addr.sin_family      = AF_INET;\n" +
            "addr.sin_port        = htons(8080);\n" +
            "addr.sin_addr.s_addr = INADDR_ANY; // bind to all interfaces\n\n" +
            "bind(listenSock, (sockaddr*)&addr, sizeof(addr));\n" +
            "listen(listenSock, SOMAXCONN); // SOMAXCONN = OS max backlog\n\n" +
            "// Accept loop:\n" +
            "while (true) {\n" +
            "    sockaddr_in clientAddr = {0};\n" +
            "    int clientLen = sizeof(clientAddr);\n" +
            "    SOCKET clientSock = accept(listenSock,\n" +
            "        (sockaddr*)&clientAddr, &clientLen);\n\n" +
            "    // Handle client (spawn thread or handle inline):\n" +
            "    char buf[1024];\n" +
            "    int n = recv(clientSock, buf, sizeof(buf), 0);\n" +
            "    send(clientSock, \"OK\", 2, 0);\n" +
            "    closesocket(clientSock);\n" +
            "}\n\n" +
            "closesocket(listenSock);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("For a production server, handle each client socket in a separate thread or use I/O completion ports (IOCP) for scalability.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("UDP SERVER")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A UDP server binds to a port and loops on recvfrom. There is no listen/accept — each datagram arrives independently with the sender's address in the from parameter.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "SOCKET sock = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP);\n\n" +
            "sockaddr_in addr = {0};\n" +
            "addr.sin_family      = AF_INET;\n" +
            "addr.sin_port        = htons(9999);\n" +
            "addr.sin_addr.s_addr = INADDR_ANY;\n\n" +
            "bind(sock, (sockaddr*)&addr, sizeof(addr));\n\n" +
            "// Receive loop:\n" +
            "while (true) {\n" +
            "    char buf[1024];\n" +
            "    sockaddr_in from = {0};\n" +
            "    int fromLen = sizeof(from);\n" +
            "    int n = recvfrom(sock, buf, sizeof(buf)-1, 0,\n" +
            "                     (sockaddr*)&from, &fromLen);\n" +
            "    buf[n] = '\\0';\n\n" +
            "    // Echo back to sender:\n" +
            "    sendto(sock, buf, n, 0,\n" +
            "           (sockaddr*)&from, fromLen);\n" +
            "}\n\n" +
            "closesocket(sock);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("UDP datagrams can be lost, reordered, or duplicated — the application is responsible for reliability if needed. Each datagram is limited to ~65,507 bytes (IPv4 UDP payload max).")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("RAW SOCKETS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Yes — Windows supports raw sockets via SOCK_RAW. A raw socket bypasses the TCP/UDP transport layer and gives you direct access to the IP layer. You receive raw IP packets, and you can send packets with a custom-built IP header.\n\nNormal sockets: App → TCP/UDP → IP → network\nRaw socket:      App → IP → network  (no TCP/UDP overhead)")

        Spacer(modifier = Modifier.height(8.dp))

        SectionHeader("CREATING A RAW SOCKET")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The third argument to socket() is the protocol number instead of 0:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Raw ICMP socket (e.g. for building ping)\n" +
            "SOCKET s = socket(AF_INET, SOCK_RAW,\n" +
            "                  IPPROTO_ICMP);\n\n" +
            "// Raw IP socket — receive all protocols\n" +
            "SOCKET s = socket(AF_INET, SOCK_RAW,\n" +
            "                  IPPROTO_IP);\n\n" +
            "// Craft your own IP header:\n" +
            "BOOL hdrIncl = TRUE;\n" +
            "setsockopt(s, IPPROTO_IP, IP_HDRINCL,\n" +
            "           (char*)&hdrIncl, sizeof(hdrIncl));"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Without IP_HDRINCL: the OS prepends a valid IP header automatically. You supply only the payload (e.g. ICMP bytes).\n\nWith IP_HDRINCL: you build and include the entire IP header yourself. This lets you set any field — protocol, TTL, flags — but the source address restrictions below still apply.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WINDOWS RESTRICTIONS (VISTA+)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Raw sockets require administrator privileges (elevated process). Without elevation, socket(AF_INET, SOCK_RAW, ...) returns WSAEACCES.\n\nAdditional send-side restrictions enforced by the OS:\n\n• TCP data cannot be sent — a raw socket with IPPROTO_TCP can receive TCP packets, but any send() call is silently dropped by the stack.\n\n• Source IP address must be a valid local interface address — spoofing a different source IP is not allowed; the packet is dropped.\n\n• Broadcast/multicast from raw sockets is restricted.\n\nThese restrictions were introduced in XP SP2 to prevent raw socket abuse for SYN floods and IP spoofing attacks.\n\nWhat IS allowed:\n• Sending raw ICMP (build your own ping or traceroute)\n• Sending raw UDP with full control over payload\n• Receiving any incoming IP packet at the raw level")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("RECEIVE ALL IP PACKETS (SIO_RCVALL)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("To receive all IP packets arriving on an interface (a limited form of packet capture):")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "SOCKET s = socket(AF_INET, SOCK_RAW,\n" +
            "                  IPPROTO_IP);\n\n" +
            "// Bind to a specific local interface\n" +
            "sockaddr_in local = {0};\n" +
            "local.sin_family = AF_INET;\n" +
            "inet_pton(AF_INET, \"192.168.1.5\",\n" +
            "          &local.sin_addr);\n" +
            "bind(s, (sockaddr*)&local, sizeof(local));\n\n" +
            "// Enable promiscuous-mode reception\n" +
            "DWORD rcvAll = RCVALL_ON;\n" +
            "DWORD bytesRet;\n" +
            "WSAIoctl(s, SIO_RCVALL,\n" +
            "         &rcvAll, sizeof(rcvAll),\n" +
            "         NULL, 0, &bytesRet,\n" +
            "         NULL, NULL);\n\n" +
            "// Now recv() delivers raw IP packets\n" +
            "char buf[65536];\n" +
            "int n = recv(s, buf, sizeof(buf), 0);\n" +
            "// buf starts with the IP header"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("SIO_RCVALL requires administrator privileges. It delivers all inbound IP packets on the bound interface — you receive the full packet starting from the IP header.\n\nFor production-grade packet capture, use WinPcap or Npcap (user mode libraries built on NDIS) rather than SIO_RCVALL — they are more reliable, support multiple concurrent capture sessions, and work on both inbound and outbound traffic.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("EXAMPLE — ICMP ECHO (PING)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("ICMP Echo Request is protocol 1. The payload is an ICMP header followed by arbitrary data:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "typedef struct {\n" +
            "    BYTE  type;       // 8 = echo request\n" +
            "    BYTE  code;       // 0\n" +
            "    WORD  checksum;\n" +
            "    WORD  id;\n" +
            "    WORD  sequence;\n" +
            "    // followed by data payload\n" +
            "} ICMP_HDR;\n\n" +
            "SOCKET s = socket(AF_INET, SOCK_RAW,\n" +
            "                  IPPROTO_ICMP);\n\n" +
            "char packet[sizeof(ICMP_HDR) + 32];\n" +
            "ICMP_HDR* icmp = (ICMP_HDR*)packet;\n" +
            "icmp->type     = 8;   // echo request\n" +
            "icmp->code     = 0;\n" +
            "icmp->id       = (WORD)GetCurrentProcessId();\n" +
            "icmp->sequence = 1;\n" +
            "icmp->checksum = 0;\n" +
            "// fill data bytes, then compute checksum\n" +
            "icmp->checksum = IpChecksum(packet,\n" +
            "                            sizeof(packet));\n\n" +
            "sockaddr_in dest = {0};\n" +
            "dest.sin_family = AF_INET;\n" +
            "inet_pton(AF_INET, \"8.8.8.8\", &dest.sin_addr);\n\n" +
            "sendto(s, packet, sizeof(packet), 0,\n" +
            "       (sockaddr*)&dest, sizeof(dest));\n\n" +
            "// Receive the ICMP echo reply:\n" +
            "char reply[65536];\n" +
            "sockaddr_in from = {0};\n" +
            "int fromLen = sizeof(from);\n" +
            "// reply buffer includes the IP header\n" +
            "recvfrom(s, reply, sizeof(reply), 0,\n" +
            "         (sockaddr*)&from, &fromLen);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The reply buffer starts with a 20-byte IP header, followed by the ICMP echo reply (type 0). You must skip the IP header to reach the ICMP data.\n\nNote: Windows also provides IcmpSendEcho / IcmpSendEcho2 (from IpHlpApi.lib) as a higher-level alternative for ICMP ping that does not require a raw socket or admin rights.")

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
