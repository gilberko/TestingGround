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
fun IPCScreen(navController: NavController) {
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
            text = "IPC",
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

        SectionHeader("INTER-PROCESS COMMUNICATION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Each Windows process has its own virtual address space — one process cannot directly read or write another's memory. IPC mechanisms provide structured ways for processes to exchange data or coordinate.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("NAMED PIPES")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Named pipes are implemented by npfs.sys — the Named Pipe File System, a kernel driver. They appear in the Object Manager namespace as \\Device\\NamedPipe\\<name>, accessible from Win32 as \\\\.\\pipe\\<name>.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Server side:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "HANDLE hPipe = CreateNamedPipe(\n" +
            "    L\"\\\\\\\\.\\\\pipe\\\\MyPipe\",\n" +
            "    PIPE_ACCESS_DUPLEX,        // bidirectional\n" +
            "    PIPE_TYPE_BYTE |           // byte stream mode\n" +
            "    PIPE_READMODE_BYTE |\n" +
            "    PIPE_WAIT,\n" +
            "    PIPE_UNLIMITED_INSTANCES,  // max instances\n" +
            "    4096, 4096,                // out/in buffer sizes\n" +
            "    0,                         // default timeout\n" +
            "    NULL);                     // default security\n\n" +
            "ConnectNamedPipe(hPipe, NULL); // blocks until client connects\n\n" +
            "// Communicate:\n" +
            "ReadFile(hPipe, buf, sizeof(buf), &bytesRead, NULL);\n" +
            "WriteFile(hPipe, response, len, &bytesWritten, NULL);\n\n" +
            "DisconnectNamedPipe(hPipe);\n" +
            "CloseHandle(hPipe);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Client side:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// If pipe is busy, WaitNamedPipe before retrying\n" +
            "WaitNamedPipe(L\"\\\\\\\\.\\\\pipe\\\\MyPipe\", NMPWAIT_WAIT_FOREVER);\n\n" +
            "HANDLE hPipe = CreateFile(\n" +
            "    L\"\\\\\\\\.\\\\pipe\\\\MyPipe\",\n" +
            "    GENERIC_READ | GENERIC_WRITE,\n" +
            "    0, NULL, OPEN_EXISTING, 0, NULL);\n\n" +
            "WriteFile(hPipe, request, len, &bytesWritten, NULL);\n" +
            "ReadFile(hPipe, buf, sizeof(buf), &bytesRead, NULL);\n" +
            "CloseHandle(hPipe);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Named pipes work across machines over SMB if you use \\\\<server>\\pipe\\<name>. They also support impersonation: the server can call ImpersonateNamedPipeClient to act as the client's security context.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SHARED MEMORY")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Shared memory uses file mappings backed by the page file (INVALID_HANDLE_VALUE as the file handle). Both processes map the same named section into their address spaces and can read/write the same physical pages.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Creator / server side:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "HANDLE hMapping = CreateFileMapping(\n" +
            "    INVALID_HANDLE_VALUE,  // backed by page file\n" +
            "    NULL,                  // default security\n" +
            "    PAGE_READWRITE,\n" +
            "    0,                     // size high DWORD\n" +
            "    4096,                  // size low DWORD (4KB)\n" +
            "    L\"Local\\\\MySharedMem\");\n\n" +
            "void* pData = MapViewOfFile(\n" +
            "    hMapping,\n" +
            "    FILE_MAP_ALL_ACCESS,\n" +
            "    0, 0,   // offset\n" +
            "    4096);  // bytes to map (0 = whole mapping)\n\n" +
            "// Write data\n" +
            "strcpy((char*)pData, \"hello\");\n\n" +
            "// Cleanup\n" +
            "UnmapViewOfFile(pData);\n" +
            "CloseHandle(hMapping);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Client side:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "HANDLE hMapping = OpenFileMapping(\n" +
            "    FILE_MAP_ALL_ACCESS,\n" +
            "    FALSE,\n" +
            "    L\"Local\\\\MySharedMem\");\n\n" +
            "void* pData = MapViewOfFile(\n" +
            "    hMapping, FILE_MAP_ALL_ACCESS, 0, 0, 4096);\n\n" +
            "// Read data\n" +
            "printf(\"%s\", (char*)pData);\n\n" +
            "UnmapViewOfFile(pData);\n" +
            "CloseHandle(hMapping);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Shared memory has no built-in synchronization. Use a named mutex or event to coordinate access — otherwise two processes writing simultaneously will corrupt the data.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("LPC AND ALPC")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("LPC (Local Procedure Call) was the original Windows inter-process messaging mechanism. ALPC (Advanced Local Procedure Call) replaced it in Windows Vista with improved security, performance, and message size limits.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("ALPC is used internally by Windows for many critical components: the Win32 subsystem (csrss.exe), the Security subsystem (lsass.exe), and RPC uses ALPC for all local calls (ncalrpc transport).")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The ALPC API is NT native — it lives in ntdll.dll but is not exposed through official Win32 headers. Key functions:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "NtAlpcCreatePort()       // server: create an ALPC port\n" +
            "NtAlpcConnectPort()      // client: connect to a named port\n" +
            "NtAlpcSendWaitReceivePort() // main send/receive loop\n" +
            "NtAlpcAcceptConnectPort()   // server: accept a connection\n" +
            "NtAlpcDisconnectPort()      // disconnect\n" +
            "NtAlpcClosePort()           // destroy"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Because ALPC is undocumented, using it directly requires importing function pointers from ntdll at runtime and defining structures from reverse engineering or ReactOS source. For production IPC, use Named Pipes, Shared Memory, or RPC (which internally uses ALPC) instead.")

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
