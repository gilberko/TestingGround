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

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
