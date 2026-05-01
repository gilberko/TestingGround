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
fun PythonUsefulPackagesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Python — Useful Packages",
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
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("File I/O — open() and pathlib") {
                    BodyText("open() is a built-in. pathlib.Path (stdlib) is the modern object-oriented way to work with paths and files.")
                    CodeBlock("""
# open() built-in
with open("data.txt", "r") as f:
    content = f.read()        # entire file as string
    lines   = f.readlines()   # list of lines

with open("out.txt", "w") as f:
    f.write("hello\n")

# pathlib — path math + reading/writing
from pathlib import Path

p = Path("data.txt")
text  = p.read_text()              # read whole file
p.write_text("new content")
p.exists()                         # True / False
p.suffix                           # ".txt"
p.stem                             # "data"

files = list(Path(".").glob("*.py"))   # find files
here  = Path(__file__).parent          # dir of this script
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("JSON — json (stdlib)") {
                    BodyText("Converts between Python objects (dicts, lists, strings, numbers) and JSON strings or files.")
                    CodeBlock("""
import json

# String ↔ object
obj  = json.loads('{"x": 1, "y": [2, 3]}')   # str  → dict
text = json.dumps(obj, indent=2)               # dict → str

# File ↔ object
with open("data.json") as f:
    data = json.load(f)           # file → dict

with open("out.json", "w") as f:
    json.dump(data, f, indent=2)  # dict → file
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("TOML — tomllib (stdlib, Python 3.11+)") {
                    BodyText("tomllib is read-only and part of the stdlib since Python 3.11. For older Python or for writing TOML, use third-party tomli (read) and tomli-w (write).")
                    CodeBlock("""
# Read — stdlib (Python 3.11+)
import tomllib
with open("config.toml", "rb") as f:   # must be binary mode
    cfg = tomllib.load(f)

val = tomllib.loads('[server]\nport = 8080')
# → {'server': {'port': 8080}}

# Write — pip install tomli-w
import tomli_w
data = {"server": {"port": 8080}}
with open("config.toml", "wb") as f:
    tomli_w.dump(data, f)
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Base64 — base64 (stdlib)") {
                    BodyText("Encodes bytes to Base64 text and decodes back. The urlsafe variants use - and _ instead of + and /, making them safe for URLs and filenames.")
                    CodeBlock("""
import base64

encoded = base64.b64encode(b"hello world")
# b'aGVsbG8gd29ybGQ='

decoded = base64.b64decode(encoded)
# b'hello world'

# Encode to string (not bytes)
text = base64.b64encode(b"data").decode()

# URL-safe variant
url_enc = base64.urlsafe_b64encode(b"\xfb\xff")
url_dec = base64.urlsafe_b64decode(url_enc)
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("HTTP — requests (third-party)") {
                    BodyText("The de facto standard for HTTP. pip install requests. For async HTTP use httpx instead.")
                    CodeBlock("""
import requests

# GET with query params
r = requests.get("https://api.example.com/data",
                 params={"q": "python"},
                 timeout=10)
r.status_code      # 200
r.text             # raw response text
r.json()           # parse JSON body → dict

# POST JSON body
r = requests.post(
    "https://api.example.com/items",
    json={"name": "foo"},
    headers={"Authorization": "Bearer TOKEN"}
)

# Session reuses TCP connection + default headers
s = requests.Session()
s.headers.update({"Authorization": "Bearer TOKEN"})
s.get("https://api.example.com/me")
s.post("https://api.example.com/items", json={})
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("HTML Parsing — BeautifulSoup (third-party)") {
                    BodyText("beautifulsoup4 parses HTML and XML and lets you search and navigate the tree. pip install beautifulsoup4. Pair with requests to fetch then parse pages.")
                    CodeBlock("""
from bs4 import BeautifulSoup
import requests

html = requests.get("https://example.com").text
soup = BeautifulSoup(html, "html.parser")

soup.find("h1").text              # first <h1> text
soup.find("p", class_="note")    # <p class="note">
soup.find_all("a")                # list of all <a> tags
soup.select("div > p.note")       # CSS selector

link = soup.find("a")
link["href"]                      # get attribute value
link.get_text()                   # text content

# Find by id
soup.find(id="main-content")
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("OS & Processes — os, subprocess, psutil") {
                    BodyText("os and subprocess are stdlib. psutil (pip install psutil) adds process enumeration, CPU/memory stats, and network interface listings.")
                    CodeBlock("""
import os, subprocess

os.getcwd()                          # current directory
os.environ["HOME"]                   # environment variable
os.getpid()                          # this process's PID
os.listdir(".")                      # directory contents

# Run a command and capture output
result = subprocess.run(
    ["ls", "-la"],
    capture_output=True, text=True
)
print(result.stdout)
print(result.returncode)

# psutil — process enumeration
import psutil

for proc in psutil.process_iter(["pid", "name", "cpu_percent"]):
    print(proc.info)

p = psutil.Process(os.getpid())
p.memory_info().rss    # RAM used in bytes
p.cpu_percent()
p.name()
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("IOCTLs & Device Drivers") {
                    BodyText("On Linux/Unix, fcntl.ioctl() (stdlib) sends an ioctl to an open file descriptor. On Windows, use ctypes to call DeviceIoControl from kernel32.")
                    CodeBlock("""
# Linux — fcntl.ioctl (Unix only)
import fcntl, struct

fd = open("/dev/some_device", "rb")
request_code = 0x8004_1234
buf    = struct.pack("I", 0)
result = fcntl.ioctl(fd, request_code, buf)
value  = struct.unpack("I", result)[0]
fd.close()

# Windows — ctypes DeviceIoControl
import ctypes, ctypes.wintypes as wt

kernel32 = ctypes.WinDLL("kernel32", use_last_error=True)
GENERIC_READ  = 0x80000000
OPEN_EXISTING = 3

handle = kernel32.CreateFileW(
    r"\\.\MyDevice", GENERIC_READ,
    0, None, OPEN_EXISTING, 0, None
)
out = (ctypes.c_char * 64)()
kernel32.DeviceIoControl(
    handle, 0x220004,
    None, 0,
    out, ctypes.sizeof(out),
    None, None
)
kernel32.CloseHandle(handle)
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Time & Sleep — time, datetime (stdlib)") {
                    BodyText("time covers sleeping and measuring elapsed wall-clock or CPU time. datetime handles calendar dates, times, and arithmetic.")
                    CodeBlock("""
import time
from datetime import datetime, timedelta

time.sleep(1.5)                   # pause for 1.5 seconds
t0 = time.monotonic()             # always-increasing clock
elapsed = time.monotonic() - t0   # measure duration
ts = time.time()                  # Unix timestamp (float)
time.perf_counter()               # highest-resolution timer

now = datetime.now()
print(now.strftime("%Y-%m-%d %H:%M:%S"))

tomorrow  = now + timedelta(days=1)
next_hour = now + timedelta(hours=1)
diff      = tomorrow - now        # timedelta
diff.total_seconds()              # 86400.0
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Networking — socket (stdlib)") {
                    BodyText("The socket module provides TCP, UDP, DNS lookup, and hostname utilities. For network interface enumeration use psutil.net_if_addrs().")
                    CodeBlock("""
import socket, psutil

# TCP client
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(("example.com", 80))
s.sendall(b"GET / HTTP/1.0\r\nHost: example.com\r\n\r\n")
data = s.recv(4096)
s.close()

# UDP send
u = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
u.sendto(b"ping", ("192.168.1.1", 9))
u.close()

# DNS lookup
ip    = socket.gethostbyname("example.com")
infos = socket.getaddrinfo("example.com", 443)
# each entry: (family, type, proto, canonname, sockaddr)

# Local hostname
socket.gethostname()
socket.getfqdn()

# Network interfaces (psutil)
for iface, addrs in psutil.net_if_addrs().items():
    for a in addrs:
        print(iface, a.address, a.netmask)
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Compression — zipfile, gzip, tarfile (stdlib)") {
                    BodyText("Python's stdlib covers ZIP, gzip, bzip2, LZMA/XZ, and tar archives. For 7-Zip (.7z) use the third-party py7zr (pip install py7zr).")
                    CodeBlock("""
import zipfile, gzip, tarfile

# ZIP
with zipfile.ZipFile("archive.zip", "r") as z:
    z.extractall("output/")        # extract all
    z.namelist()                   # list contents

with zipfile.ZipFile("out.zip", "w", zipfile.ZIP_DEFLATED) as z:
    z.write("file.txt")            # add a file

# gzip (single-file compression)
with gzip.open("data.gz", "wb") as f:
    f.write(b"hello world")

with gzip.open("data.gz", "rb") as f:
    content = f.read()

# tar / tar.gz / tar.bz2 / tar.xz
with tarfile.open("archive.tar.gz", "r:gz") as t:
    t.extractall("output/")

with tarfile.open("out.tar.gz", "w:gz") as t:
    t.add("mydir/")

# 7-Zip (pip install py7zr)
import py7zr
with py7zr.SevenZipFile("archive.7z", "r") as z:
    z.extractall("output/")
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
