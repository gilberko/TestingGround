package com.example.developmentapp.screens.python

import androidx.compose.foundation.layout.Spacer
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
import com.example.developmentapp.screens.BodyText
import com.example.developmentapp.screens.CodeBlock
import com.example.developmentapp.screens.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PythonNetworkingScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Python — Networking",
                        color      = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontSize   = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector        = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint               = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            item { Spacer(Modifier.height(12.dp)) }

            // ── UDP Server ─────────────────────────────────────────────────
            item {
                SectionCard(title = "UDP Server") {
                    BodyText("UDP is connectionless — no handshake, no persistent connection. The server binds to a port and waits for datagrams. Each recvfrom() returns a packet and the sender's address.")
                    CodeBlock(
                        """
import socket

HOST = "0.0.0.0"   # listen on all interfaces
PORT = 9000

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.bind((HOST, PORT))
print(f"UDP server listening on {PORT}")

while True:
    data, addr = sock.recvfrom(4096)  # buffer size in bytes
    print(f"From {addr}: {data.decode()}")
    sock.sendto(b"ACK: " + data, addr)
                        """.trimIndent()
                    )
                    BodyText("socket.AF_INET = IPv4, socket.SOCK_DGRAM = UDP. recvfrom() blocks until a datagram arrives and returns (bytes, (ip, port)). sendto() sends a datagram to the given address.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── UDP Client ─────────────────────────────────────────────────
            item {
                SectionCard(title = "UDP Client") {
                    BodyText("The UDP client sends datagrams directly to the server's address — no connection setup needed. sendto() does not require a prior connect().")
                    CodeBlock(
                        """
import socket

SERVER = ("127.0.0.1", 9000)

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

message = "Hello, UDP server!"
sock.sendto(message.encode(), SERVER)

response, _ = sock.recvfrom(4096)
print(f"Server replied: {response.decode()}")

sock.close()
                        """.trimIndent()
                    )
                    BodyText("UDP has no delivery guarantee — packets can be lost, duplicated, or arrive out of order. Use TCP when reliability is required.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── TCP Server ─────────────────────────────────────────────────
            item {
                SectionCard(title = "TCP Server") {
                    BodyText("TCP is connection-oriented. The server calls listen() to accept incoming connections, then accept() to get a connected socket for each client. Each connected socket is a separate two-way stream.")
                    CodeBlock(
                        """
import socket

HOST = "0.0.0.0"
PORT = 9001

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
server.bind((HOST, PORT))
server.listen(5)  # max 5 queued connections
print(f"TCP server listening on {PORT}")

while True:
    conn, addr = server.accept()  # blocks until a client connects
    print(f"Connected: {addr}")
    try:
        while True:
            data = conn.recv(4096)
            if not data:
                break  # client closed the connection
            print(f"Received: {data.decode()}")
            conn.sendall(b"ECHO: " + data)
    finally:
        conn.close()
        print(f"Disconnected: {addr}")
                        """.trimIndent()
                    )
                    BodyText("SO_REUSEADDR lets the server restart quickly without waiting for the port's TIME_WAIT state to expire. conn.sendall() loops internally until all data is sent (unlike send() which may send less).")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── TCP Client ─────────────────────────────────────────────────
            item {
                SectionCard(title = "TCP Client") {
                    BodyText("The TCP client calls connect() to perform the three-way handshake with the server. After that, send/recv work on the established stream.")
                    CodeBlock(
                        """
import socket

SERVER = ("127.0.0.1", 9001)

client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.connect(SERVER)  # TCP three-way handshake

message = "Hello, TCP server!"
client.sendall(message.encode())

response = client.recv(4096)
print(f"Server replied: {response.decode()}")

client.close()
                        """.trimIndent()
                    )
                    BodyText("recv() may return less than the requested size — in a real protocol you loop until you've received the expected number of bytes (framed by a length prefix or delimiter).")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── HTTP Requests ──────────────────────────────────────────────
            item {
                SectionCard(title = "HTTP Requests — requests Library") {
                    BodyText("The requests library is the standard for HTTP in Python. Install with: pip install requests")
                    BodyText("GET request:")
                    CodeBlock(
                        """
import requests

r = requests.get(
    "https://api.github.com/users/torvalds",
    headers={"Accept": "application/json"},
    timeout=10
)
print(r.status_code)   # 200
print(r.headers["Content-Type"])
data = r.json()        # parse JSON response body
print(data["name"])    # Linus Torvalds
                        """.trimIndent()
                    )
                    BodyText("POST request with JSON body:")
                    CodeBlock(
                        """
import requests

payload = {"username": "alice", "score": 42}

r = requests.post(
    "https://httpbin.org/post",
    json=payload,        # sets Content-Type: application/json automatically
    timeout=10
)
print(r.status_code)   # 200
result = r.json()
print(result["json"])  # echoed back by httpbin
                        """.trimIndent()
                    )
                    BodyText("Useful response attributes: r.status_code (int), r.text (str), r.json() (dict/list), r.content (bytes), r.headers (dict), r.url (final URL after redirects).")
                    BodyText("Always set timeout= to avoid hanging forever on slow servers. Raises requests.exceptions.Timeout if exceeded.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Other HTTP Libraries ───────────────────────────────────────
            item {
                SectionCard(title = "Other HTTP Libraries") {
                    BodyText("httpx — modern drop-in alternative to requests. Supports both synchronous and async usage. Recommended for new projects.")
                    CodeBlock(
                        """
# pip install httpx
import httpx
import asyncio

# Sync (same API as requests):
r = httpx.get("https://httpbin.org/get", timeout=10)
print(r.json())

# Async:
async def fetch():
    async with httpx.AsyncClient() as client:
        r = await client.get("https://httpbin.org/get")
        return r.json()

asyncio.run(fetch())
                        """.trimIndent()
                    )
                    BodyText("urllib.request — standard library, no install needed. Verbose but always available.")
                    CodeBlock(
                        """
from urllib.request import urlopen
import json

with urlopen("https://api.github.com/users/torvalds") as resp:
    data = json.loads(resp.read())
    print(data["name"])
                        """.trimIndent()
                    )
                    BodyText("aiohttp — async HTTP client/server framework. Popular for high-concurrency applications (download many URLs in parallel).")
                    CodeBlock(
                        """
# pip install aiohttp
import aiohttp, asyncio

async def fetch_all(urls):
    async with aiohttp.ClientSession() as session:
        tasks = [session.get(u) for u in urls]
        responses = await asyncio.gather(*tasks)
        return [await r.json() for r in responses]
                        """.trimIndent()
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
