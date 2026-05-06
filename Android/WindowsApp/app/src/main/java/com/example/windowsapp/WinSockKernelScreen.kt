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
fun WinSockKernelScreen(navController: NavController) {
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
            text = "WINSOCK KERNEL (WSK)",
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

        SectionHeader("WHAT IS WSK?")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Winsock Kernel (WSK) is the kernel-mode socket programming interface for Windows. Available since Windows Vista, it allows kernel drivers to communicate over TCP/IP, UDP, and other protocols directly from kernel mode — without needing to go to user mode.\n\n" +
            "WSK is implemented in netio.sys. It sits above the transport layer (TCP/UDP) and the NDIS stack. Drivers include wsk.h and link against netio.lib.\n\n" +
            "Why use WSK instead of going through user mode?\n" +
            "  - Lower latency: no user/kernel transitions for each operation\n" +
            "  - Works in contexts where user mode is unavailable (early system services, DPC-adjacent work queued to threads)\n" +
            "  - VPN and tunnel drivers need to inject/receive packets without involving a user-mode daemon\n" +
            "  - Some security software uses WSK for C2-channel communication that bypasses user-mode API hooks\n\n" +
            "Contrast with user-mode Winsock:\n" +
            "  User Winsock → ws2_32.dll → IOCTL to afd.sys (Ancillary Function Driver) → TCP/IP stack. WSK bypasses all of that and talks directly to the TCP/IP transport."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WSK REGISTRATION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Before creating any sockets, a driver must register with the WSK subsystem and capture the provider NPI (Network Programming Interface). This is done in DriverEntry."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// 1. Define the client dispatch table (can be all NULL if no callbacks needed)\n" +
            "const WSK_CLIENT_DISPATCH WskClientDispatch = {\n" +
            "    MAKE_WSK_VERSION(1, 0),  // requested version\n" +
            "    0,                        // reserved\n" +
            "    NULL                      // WskClientEvent (optional)\n" +
            "};\n" +
            "\n" +
            "// 2. Fill in the client NPI\n" +
            "WSK_CLIENT_NPI WskClientNpi;\n" +
            "WskClientNpi.ClientContext = NULL;          // context passed to callbacks\n" +
            "WskClientNpi.Dispatch      = &WskClientDispatch;\n" +
            "\n" +
            "// 3. Register — output is a WSK_REGISTRATION handle\n" +
            "WSK_REGISTRATION WskRegistration;\n" +
            "NTSTATUS status = WskRegister(&WskClientNpi, &WskRegistration);\n" +
            "\n" +
            "// 4. Capture the provider NPI (blocks until WSK is ready)\n" +
            "WSK_PROVIDER_NPI WskProviderNpi;\n" +
            "status = WskCaptureProviderNPI(\n" +
            "    &WskRegistration,\n" +
            "    WSK_INFINITE_WAIT,\n" +
            "    &WskProviderNpi);\n" +
            "\n" +
            "// --- Use sockets via WskProviderNpi.Dispatch->WskSocket etc. ---\n" +
            "\n" +
            "// DriverUnload cleanup:\n" +
            "WskReleaseProviderNPI(&WskRegistration);\n" +
            "WskDeregister(&WskRegistration);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("CREATING A SOCKET")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "All WSK operations are IRP-based and asynchronous. The common pattern is to allocate an IRP, set a completion routine that signals a KEVENT, call the WSK function, and then wait on the event.\n\n" +
            "Socket type flags:\n" +
            "  WSK_FLAG_CONNECTION_SOCKET  — TCP stream socket\n" +
            "  WSK_FLAG_DATAGRAM_SOCKET    — UDP datagram socket\n" +
            "  WSK_FLAG_LISTEN_SOCKET      — server accept-only socket"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Helper: allocate IRP, wait for completion, return status\n" +
            "NTSTATUS WskCallAndWait(PIRP *pIrp, PKEVENT pEvent) {\n" +
            "    *pIrp = IoAllocateIrp(1, FALSE);\n" +
            "    if (!*pIrp) return STATUS_INSUFFICIENT_RESOURCES;\n" +
            "    KeInitializeEvent(pEvent, NotificationEvent, FALSE);\n" +
            "    IoSetCompletionRoutine(*pIrp, WskCompletionRoutine,\n" +
            "                          pEvent, TRUE, TRUE, TRUE);\n" +
            "    return STATUS_SUCCESS;\n" +
            "}\n" +
            "\n" +
            "// WskCompletionRoutine signals the event:\n" +
            "NTSTATUS WskCompletionRoutine(PDEVICE_OBJECT Dev,\n" +
            "                              PIRP Irp, PVOID Context) {\n" +
            "    KeSetEvent((PKEVENT)Context, IO_NO_INCREMENT, FALSE);\n" +
            "    return STATUS_MORE_PROCESSING_REQUIRED;\n" +
            "}\n" +
            "\n" +
            "// Create a TCP socket:\n" +
            "PIRP irp;\n" +
            "KEVENT event;\n" +
            "WskCallAndWait(&irp, &event);\n" +
            "\n" +
            "PWSK_SOCKET socket;\n" +
            "WskProviderNpi.Dispatch->WskSocket(\n" +
            "    WskProviderNpi.Client,\n" +
            "    AF_INET,               // address family\n" +
            "    SOCK_STREAM,           // socket type\n" +
            "    IPPROTO_TCP,           // protocol\n" +
            "    WSK_FLAG_CONNECTION_SOCKET,\n" +
            "    NULL,                  // socket context\n" +
            "    NULL,                  // dispatch (callbacks)\n" +
            "    NULL, NULL, NULL,      // security, owner, SD\n" +
            "    irp);\n" +
            "\n" +
            "KeWaitForSingleObject(&event, Executive, KernelMode,\n" +
            "                      FALSE, NULL);\n" +
            "socket = (PWSK_SOCKET)irp->IoStatus.Information;\n" +
            "IoFreeIrp(irp);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("IRP-BASED ASYNC MODEL")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Every WSK operation (WskSocket, WskConnect, WskSend, WskReceive, etc.) takes an IRP as its last parameter. The operation is always asynchronous — the function may return STATUS_PENDING before the operation completes.\n\n" +
            "The standard synchronous-wait pattern:\n" +
            "  1. Allocate an IRP with IoAllocateIrp(stackSize=1, chargeQuota=FALSE)\n" +
            "  2. Initialize a KEVENT (NotificationEvent, not signaled)\n" +
            "  3. Set a completion routine on the IRP that calls KeSetEvent\n" +
            "  4. Call the WSK function — it may complete inline (STATUS_SUCCESS) or asynchronously (STATUS_PENDING)\n" +
            "  5. KeWaitForSingleObject on the event\n" +
            "  6. Read the result from irp->IoStatus.Status and irp->IoStatus.Information\n" +
            "  7. IoFreeIrp when done\n\n" +
            "For higher throughput, you can reuse IRPs with IoReuseIrp instead of allocating a new one for each operation.\n\n" +
            "Why IRP-based? Because kernel-mode code must not block threads for extended periods without giving the scheduler a chance to run. Using IRPs with completion callbacks keeps threads available while I/O is in flight."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("TCP CLIENT EXAMPLE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Full flow for a TCP client connecting to a remote server and exchanging data:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// After WskCaptureProviderNPI — have WskProviderNpi ready\n" +
            "// and socket was created as WSK_FLAG_CONNECTION_SOCKET\n" +
            "\n" +
            "PWSK_PROVIDER_CONNECTION_DISPATCH dispatch =\n" +
            "    (PWSK_PROVIDER_CONNECTION_DISPATCH)socket->Dispatch;\n" +
            "\n" +
            "// --- CONNECT ---\n" +
            "SOCKADDR_IN remoteAddr = {0};\n" +
            "remoteAddr.sin_family      = AF_INET;\n" +
            "remoteAddr.sin_port        = RtlUshortByteSwap(443); // big-endian\n" +
            "remoteAddr.sin_addr.s_addr = RtlUlongByteSwap(0xC0A80101); // 192.168.1.1\n" +
            "\n" +
            "// (Optional) Bind to local port 0 (any) first:\n" +
            "// dispatch->WskBind(socket, (PSOCKADDR)&localAddr, 0, irp);\n" +
            "\n" +
            "dispatch->WskConnect(socket, (PSOCKADDR)&remoteAddr, 0, irp);\n" +
            "KeWaitForSingleObject(&event, Executive, KernelMode, FALSE, NULL);\n" +
            "// irp->IoStatus.Status == STATUS_SUCCESS if connected\n" +
            "\n" +
            "// --- SEND ---\n" +
            "CHAR sendBuf[] = \"GET / HTTP/1.0\\r\\n\\r\\n\";\n" +
            "MDL *mdl = IoAllocateMdl(sendBuf, sizeof(sendBuf), FALSE, FALSE, NULL);\n" +
            "MmBuildMdlForNonPagedPool(mdl);\n" +
            "\n" +
            "WSK_BUF wskBuf;\n" +
            "wskBuf.Mdl    = mdl;\n" +
            "wskBuf.Offset = 0;\n" +
            "wskBuf.Length = sizeof(sendBuf) - 1;\n" +
            "\n" +
            "IoReuseIrp(irp, STATUS_UNSUCCESSFUL);\n" +
            "IoSetCompletionRoutine(irp, WskCompletionRoutine, &event, ...);\n" +
            "KeResetEvent(&event);\n" +
            "\n" +
            "dispatch->WskSend(socket, &wskBuf, 0, irp);\n" +
            "KeWaitForSingleObject(&event, Executive, KernelMode, FALSE, NULL);\n" +
            "IoFreeMdl(mdl);\n" +
            "\n" +
            "// --- RECEIVE ---\n" +
            "CHAR recvBuf[4096];\n" +
            "MDL *recvMdl = IoAllocateMdl(recvBuf, sizeof(recvBuf), FALSE, FALSE, NULL);\n" +
            "MmBuildMdlForNonPagedPool(recvMdl);\n" +
            "\n" +
            "WSK_BUF recvWskBuf = { recvMdl, 0, sizeof(recvBuf) };\n" +
            "\n" +
            "// ... set up IRP again ...\n" +
            "dispatch->WskReceive(socket, &recvWskBuf, 0, irp);\n" +
            "KeWaitForSingleObject(&event, Executive, KernelMode, FALSE, NULL);\n" +
            "ULONG bytesReceived = (ULONG)irp->IoStatus.Information;\n" +
            "IoFreeMdl(recvMdl);\n" +
            "\n" +
            "// --- CLOSE ---\n" +
            "dispatch->WskCloseSocket(socket, irp);\n" +
            "KeWaitForSingleObject(&event, Executive, KernelMode, FALSE, NULL);\n" +
            "IoFreeIrp(irp);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("LISTENING SERVER SOCKET")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "A listen socket accepts incoming TCP connections. Use WSK_FLAG_LISTEN_SOCKET."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// Create listen socket\n" +
            "// WskSocket(..., WSK_FLAG_LISTEN_SOCKET, ...)\n" +
            "\n" +
            "PWSK_PROVIDER_LISTEN_DISPATCH listenDispatch =\n" +
            "    (PWSK_PROVIDER_LISTEN_DISPATCH)listenSocket->Dispatch;\n" +
            "\n" +
            "// Bind to local address/port\n" +
            "SOCKADDR_IN localAddr = {0};\n" +
            "localAddr.sin_family = AF_INET;\n" +
            "localAddr.sin_port   = RtlUshortByteSwap(8080);\n" +
            "localAddr.sin_addr.s_addr = INADDR_ANY;\n" +
            "\n" +
            "listenDispatch->WskBind(listenSocket,\n" +
            "    (PSOCKADDR)&localAddr, 0, irp);\n" +
            "KeWaitForSingleObject(&event, ...);\n" +
            "\n" +
            "// Accept loop (each accept gives a new connected socket)\n" +
            "while (running) {\n" +
            "    PWSK_SOCKET clientSocket = NULL;\n" +
            "    SOCKADDR_IN remoteAddr = {0};\n" +
            "\n" +
            "    // Reset IRP\n" +
            "    IoReuseIrp(irp, STATUS_UNSUCCESSFUL);\n" +
            "    IoSetCompletionRoutine(irp, WskCompletionRoutine, &event, ...);\n" +
            "    KeResetEvent(&event);\n" +
            "\n" +
            "    listenDispatch->WskAccept(\n" +
            "        listenSocket,\n" +
            "        0,              // flags\n" +
            "        NULL,           // accept socket context\n" +
            "        NULL,           // accept socket dispatch\n" +
            "        NULL,           // local addr buffer\n" +
            "        (PSOCKADDR)&remoteAddr,\n" +
            "        irp);\n" +
            "    KeWaitForSingleObject(&event, ...);\n" +
            "\n" +
            "    clientSocket = (PWSK_SOCKET)irp->IoStatus.Information;\n" +
            "    // Handle clientSocket in a worker thread...\n" +
            "    // Then close it:\n" +
            "    PWSK_PROVIDER_CONNECTION_DISPATCH cd =\n" +
            "        (PWSK_PROVIDER_CONNECTION_DISPATCH)clientSocket->Dispatch;\n" +
            "    cd->WskCloseSocket(clientSocket, irp);\n" +
            "    KeWaitForSingleObject(&event, ...);\n" +
            "}\n" +
            "\n" +
            "// Close listen socket\n" +
            "listenDispatch->WskCloseSocket(listenSocket, irp);\n" +
            "KeWaitForSingleObject(&event, ...);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("UDP DATAGRAM SOCKET")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "For UDP, use WSK_FLAG_DATAGRAM_SOCKET. There is no connect step — each send specifies the destination."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// WskSocket(..., SOCK_DGRAM, IPPROTO_UDP,\n" +
            "//           WSK_FLAG_DATAGRAM_SOCKET, ...)\n" +
            "\n" +
            "PWSK_PROVIDER_DATAGRAM_DISPATCH dgDispatch =\n" +
            "    (PWSK_PROVIDER_DATAGRAM_DISPATCH)dgSocket->Dispatch;\n" +
            "\n" +
            "// Bind to local port (required before receive; optional for send-only)\n" +
            "SOCKADDR_IN localAddr = { AF_INET, RtlUshortByteSwap(9000), {INADDR_ANY} };\n" +
            "dgDispatch->WskBind(dgSocket, (PSOCKADDR)&localAddr, 0, irp);\n" +
            "KeWaitForSingleObject(&event, ...);\n" +
            "\n" +
            "// Send to a specific remote address\n" +
            "SOCKADDR_IN remoteAddr = { AF_INET, RtlUshortByteSwap(5353), {0xEFFFFFFA} }; // 239.255.255.250\n" +
            "WSK_BUF buf = { mdl, 0, dataLen };\n" +
            "\n" +
            "dgDispatch->WskSendTo(\n" +
            "    dgSocket, &buf, 0,\n" +
            "    (PSOCKADDR)&remoteAddr,\n" +
            "    0, NULL, irp);\n" +
            "KeWaitForSingleObject(&event, ...);\n" +
            "\n" +
            "// Receive (stores sender address in remoteAddr)\n" +
            "dgDispatch->WskReceiveFrom(\n" +
            "    dgSocket, &recvBuf, 0,\n" +
            "    (PSOCKADDR)&remoteAddr,\n" +
            "    NULL, NULL, NULL, irp);\n" +
            "KeWaitForSingleObject(&event, ...);\n" +
            "\n" +
            "// Close\n" +
            "dgDispatch->WskCloseSocket(dgSocket, irp);\n" +
            "KeWaitForSingleObject(&event, ...);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("CLEANUP AND UNLOAD")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "WSK cleanup must be fully complete before DriverUnload returns. If any socket or IRP is still outstanding when the driver unloads, a BSOD follows."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "VOID DriverUnload(PDRIVER_OBJECT DriverObject) {\n" +
            "    // 1. Close all open sockets first (each with event-wait)\n" +
            "    if (g_socket) {\n" +
            "        PIRP irp = IoAllocateIrp(1, FALSE);\n" +
            "        KEVENT event;\n" +
            "        KeInitializeEvent(&event, NotificationEvent, FALSE);\n" +
            "        IoSetCompletionRoutine(irp, WskCompletionRoutine,\n" +
            "                              &event, TRUE, TRUE, TRUE);\n" +
            "\n" +
            "        PWSK_PROVIDER_BASIC_DISPATCH basic =\n" +
            "            (PWSK_PROVIDER_BASIC_DISPATCH)g_socket->Dispatch;\n" +
            "        basic->WskCloseSocket(g_socket, irp);\n" +
            "        KeWaitForSingleObject(&event, Executive,\n" +
            "                             KernelMode, FALSE, NULL);\n" +
            "        IoFreeIrp(irp);\n" +
            "        g_socket = NULL;\n" +
            "    }\n" +
            "\n" +
            "    // 2. Release the provider NPI\n" +
            "    WskReleaseProviderNPI(&g_WskRegistration);\n" +
            "\n" +
            "    // 3. Deregister from WSK\n" +
            "    WskDeregister(&g_WskRegistration);\n" +
            "\n" +
            "    // WskDeregister blocks until all outstanding IRPs complete\n" +
            "    // Safe to return after this\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("COMMON USE CASES")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "VPN and tunnel drivers:\n" +
            "  A VPN driver can use WSK to open a UDP socket and send encapsulated packets over the tunnel without needing a user-mode daemon for the inner socket. This avoids the overhead of user/kernel round-trips for each tunneled packet.\n\n" +
            "Network diagnostics and telemetry:\n" +
            "  Security drivers that need to report findings to a collector can use WSK to open a TCP connection and stream events directly, even before user-mode processes are available.\n\n" +
            "Kernel-mode C2 (EDR evasion context):\n" +
            "  Malicious kernel-mode rootkits have used WSK to establish command-and-control channels directly from the kernel, bypassing user-mode API hooks placed by security software. This is a known EDR evasion technique — reputable EDR products therefore monitor WSK usage via WFP callouts or NDIS LWF.\n\n" +
            "WSK vs WFP injection:\n" +
            "  WSK creates new network connections from kernel code.\n" +
            "  WFP injection modifies or injects data into existing connections.\n" +
            "  They are complementary: a VPN driver might use WFP to intercept outgoing packets and WSK to send them encapsulated over the tunnel."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
