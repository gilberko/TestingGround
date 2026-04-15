package com.example.developmentapp.screens.tcpip

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
fun DnsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "DNS",
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

            // ── What is DNS ──────────────────────────────────────────────
            item {
                SectionCard(title = "What is DNS?") {
                    BodyText("DNS — Domain Name System — is the internet's phone book. It translates human-readable hostnames (www.example.com) into IP addresses (93.184.216.34) that computers use to route traffic.")
                    BodyText("DNS is a hierarchical, distributed database. No single server knows all domain names — the responsibility is delegated across a tree of authoritative name servers.")
                    BodyText("DNS primarily uses UDP port 53 for queries (fast, low-overhead). TCP port 53 is used when the response is too large for a single UDP datagram (>512 bytes, or >4096 with EDNS), and for zone transfers between servers.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Resolution Chain ─────────────────────────────────────────
            item {
                SectionCard(title = "Resolution Chain") {
                    BodyText("When you type 'www.example.com' in a browser, here is what happens:")
                    CodeBlock("""
1. Local cache: OS checks its own DNS cache.
   Hit? Use it. Miss? Continue.

2. Recursive resolver (your ISP or 8.8.8.8):
   Your machine asks it to resolve the name.
   It checks its cache. Miss? It does the work for you:

3. Root nameserver (.):
   13 root server clusters worldwide.
   "I don't know example.com, but ask .com TLD servers"

4. TLD nameserver (.com):
   "I don't know www.example.com, but ask ns1.example.com"

5. Authoritative nameserver (ns1.example.com):
   "www.example.com is at 93.184.216.34" ← final answer

6. Recursive resolver returns the answer to you,
   caches it for the TTL duration.
                    """.trimIndent())
                    BodyText("This whole process typically takes 20–200 ms on the first query; subsequent queries are served from cache in < 1 ms.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Record Types ─────────────────────────────────────────────
            item {
                SectionCard(title = "DNS Record Types") {
                    BodyText("DNS stores different types of records for different purposes:")
                    CodeBlock("""
A      maps hostname → IPv4 address
       www.example.com.  300  IN  A  93.184.216.34

AAAA   maps hostname → IPv6 address
       www.example.com.  300  IN  AAAA  2606:2800:220:1::68

CNAME  canonical name alias — points to another hostname
       blog.example.com.  300  IN  CNAME  www.example.com.

MX     mail exchanger — where to deliver email for a domain
       example.com.  3600  IN  MX  10  mail.example.com.

NS     authoritative nameserver for a zone
       example.com.  86400 IN  NS  ns1.example.com.

PTR    reverse DNS — IP → hostname (in-addr.arpa zone)
       34.216.184.93.in-addr.arpa.  IN  PTR  www.example.com.

TXT    arbitrary text — used for SPF, DKIM, domain verification
       example.com.  IN  TXT  "v=spf1 include:_spf.google.com ~all"

SOA    Start of Authority — metadata about the zone
       (serial, refresh, retry, expire, min-TTL)
                    """.trimIndent())
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Query/Response Format ────────────────────────────────────
            item {
                SectionCard(title = "Query & Response Format") {
                    BodyText("A DNS message (query or response) has the same structure:")
                    CodeBlock("""
+---------------------------+
| Transaction ID (16b)      |  matches query to response
+---------------------------+
| Flags (16b)               |  QR, Opcode, AA, TC, RD, RA, RCODE
+---------------------------+
| QDCOUNT  (questions)      |
| ANCOUNT  (answer records) |
| NSCOUNT  (authority RRs)  |
| ARCOUNT  (additional RRs) |
+---------------------------+
| Question section          |  name + type + class
+---------------------------+
| Answer section            |  resource records
+---------------------------+
| Authority section         |  NS records for referrals
+---------------------------+
| Additional section        |  glue records (A for NS names)
+---------------------------+
                    """.trimIndent())
                    BodyText("Key flags: QR=0 query / QR=1 response. RD=1 means 'recursion desired'. RA=1 means the server supports recursion. RCODE=0 success, RCODE=3 NXDOMAIN (name doesn't exist).")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── TTL & Caching ────────────────────────────────────────────
            item {
                SectionCard(title = "TTL & Caching") {
                    BodyText("Every DNS record has a TTL (Time To Live) in seconds. Resolvers cache the answer for that duration and serve it from cache to reduce load on authoritative servers and speed up lookups.")
                    BodyText("Short TTL (e.g. 60s): changes propagate quickly. Useful before a planned IP change or failover. But every client queries the server more often — higher load.")
                    BodyText("Long TTL (e.g. 86400 = 24h): very fast responses from cache. But if you change the IP, clients may use the old address for up to 24 hours. Standard practice before a migration: lower the TTL to 300s a day in advance, make the change, then raise it again.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── DNS Security ─────────────────────────────────────────────
            item {
                SectionCard(title = "DNS Security") {
                    BodyText("Plain DNS has no authentication — a man-in-the-middle can forge responses and redirect traffic. Several mechanisms address this:")
                    BodyText("DNSSEC: the zone owner signs its records with a private key. Resolvers verify the signature using the public key published in a DS record chain up to the root. Proves the answer is authentic and unmodified. Does NOT encrypt — responses are still visible.")
                    BodyText("DNS over TLS (DoT): wraps DNS in TLS on TCP port 853. Encrypts the query/response between client and resolver. Prevents eavesdropping and tampering by the network.")
                    BodyText("DNS over HTTPS (DoH): sends DNS messages as HTTPS requests on port 443. Harder to block than DoT (blends with HTTPS traffic). Used by browsers (Firefox, Chrome) with their own configured resolvers.")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
