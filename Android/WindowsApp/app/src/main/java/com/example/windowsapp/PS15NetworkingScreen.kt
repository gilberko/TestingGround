package com.example.windowsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PS15NetworkingScreen(navController: NavController) {
    PsLessonScreen(navController, "15 NETWORKING") {

        PsSection("NETWORKING FROM A SCRIPT — CONCEPTS")
        PsText("""
            Network troubleshooting follows layers, and PowerShell has a command for each:
            1. Configuration — which adapters and IP addresses do I have? (Get-NetIPConfiguration)
            2. Name resolution — does the name resolve to an IP? (Resolve-DnsName)
            3. Reachability — does the host answer ICMP ping? (Test-Connection)
            4. Port / transport — is the TCP port open? (Test-NetConnection, TcpClient)
            5. Application — does the HTTP(S) service work? (Invoke-WebRequest / Invoke-RestMethod)

            Unlike ping.exe or nslookup.exe, these commands return OBJECTS, so their results can be tested in if statements, collected and exported.
        """)

        PsSection("LOCAL CONFIGURATION")
        PsCode("""
            Get-NetIPConfiguration                           # like ipconfig
            Get-NetIPAddress -AddressFamily IPv4 |
                Select-Object InterfaceAlias, IPAddress, PrefixLength
            Get-NetAdapter | Select-Object Name, Status, LinkSpeed, MacAddress
            Get-NetRoute -DestinationPrefix 0.0.0.0/0        # default gateway
            Get-DnsClientServerAddress -AddressFamily IPv4   # DNS servers
        """)
        PsText("These come from the NetTCPIP, NetAdapter and DnsClient modules — Windows-only, built on CIM classes in root\\StandardCimv2 (lesson 21).")

        PsSection("DNS LOOKUP")
        PsCode("""
            Resolve-DnsName github.com
            Resolve-DnsName github.com -Type AAAA            # IPv6
            Resolve-DnsName microsoft.com -Type MX           # mail servers
            Resolve-DnsName example.com -Server 1.1.1.1      # ask a specific server
            (Resolve-DnsName github.com -Type A).IPAddress

            # Cross-platform .NET alternative (works on Linux/macOS too):
            [System.Net.Dns]::GetHostAddresses('github.com')
        """)
        PsOutput("""
            Name         Type   TTL   Section    IPAddress
            ----         ----   ---   -------    ---------
            github.com   A      60    Answer     140.82.121.4
        """)
        PsText("Resolve-DnsName returns record objects with Name, Type, TTL and IPAddress (or NameExchange for MX, etc.). It throws a non-terminating error if the name doesn't exist — add -ErrorAction Stop inside try (lesson 10).")

        PsSection("CONNECTIVITY: Test-Connection (PING)")
        PsCode("""
            Test-Connection github.com -Count 2              # ICMP echo
            Test-Connection github.com -Count 1 -Quiet       # just True/False
            Test-Connection 8.8.8.8 -Traceroute              # PS 7

            # PS 7: TCP port test without ICMP
            Test-Connection github.com -TcpPort 443          # True/False
        """)
        PsText("""
            5.1 vs 7 DIFFERENCES: Windows PowerShell 5.1's Test-Connection uses WMI (Win32_PingStatus) and the parameter is -ComputerName. PowerShell 7 rewrote it cross-platform: -TargetName (with -ComputerName kept as an alias), new -TcpPort, -Traceroute, -MtuSize and different output objects. -Quiet works in both.

            Many hosts and firewalls block ICMP, so a failed ping does NOT prove the host is down — test the actual port instead.
        """)

        PsSection("PORTS: Test-NetConnection")
        PsCode("""
            Test-NetConnection github.com -Port 443
            Test-NetConnection srv01 -CommonTCPPort RDP      # 3389
            Test-NetConnection github.com -TraceRoute
            (Test-NetConnection db01 -Port 1433 -WarningAction SilentlyContinue).TcpTestSucceeded
        """)
        PsOutput("""
            ComputerName     : github.com
            RemoteAddress    : 140.82.121.4
            RemotePort       : 443
            InterfaceAlias   : Wi-Fi
            SourceAddress    : 192.168.1.23
            TcpTestSucceeded : True
        """)
        PsText("Test-NetConnection (alias tnc) is Windows-only (NetTCPIP module) but works in both 5.1 and 7 on Windows. It's slow on failure (waits for a timeout) and prints warnings, hence -WarningAction SilentlyContinue in scripts.")

        PsSection("RAW TCP WITH .NET")
        PsCode("""
            function Test-TcpPort {
                param([string]§HostName, [int]§Port, [int]§TimeoutMs = 2000)
                §client = [System.Net.Sockets.TcpClient]::new()
                try {
                    §task = §client.ConnectAsync(§HostName, §Port)
                    §task.Wait(§TimeoutMs) -and §client.Connected
                }
                catch { §false }
                finally { §client.Dispose() }
            }

            'github.com:443', 'github.com:22', 'github.com:8080' | ForEach-Object {
                §h, §p = §_ -split ':'
                [PSCustomObject]@{ Target = §_; Open = Test-TcpPort §h ([int]§p) }
            }
        """)
        PsText("""
            System.Net.Sockets.TcpClient is the same Winsock-based API a C# program uses (see User Mode WINSOCK). ConnectAsync + Wait(timeout) gives a fast, controllable timeout — much quicker than Test-NetConnection when scanning several ports. §h, §p = §_ -split ':' is MULTIPLE ASSIGNMENT: the array is unpacked into two variables. Only scan hosts you own or are authorized to test.
        """)
        PsCode("""
            # Who is connected? (like netstat -ano, but objects)
            Get-NetTCPConnection -State Established |
                Select-Object LocalPort, RemoteAddress, RemotePort, OwningProcess,
                    @{ N = 'Process'; E = { (Get-Process -Id §_.OwningProcess).Name } } |
                Sort-Object Process
        """)

        PsSection("HTTP/HTTPS: Invoke-WebRequest")
        PsCode("""
            §r = Invoke-WebRequest -Uri 'https://example.com'
            §r.StatusCode                   # 200
            §r.Headers['Content-Type']
            §r.Content.Length               # body as a string
            §r.Links.href | Select-Object -First 5

            # Download a file
            Invoke-WebRequest -Uri 'https://example.com/file.zip' -OutFile C:\Temp\PSCourse\file.zip

            # POST a form
            Invoke-WebRequest -Uri 'https://httpbin.org/post' -Method Post -Body @{ user = 'ada' }
        """)
        PsText("""
            Invoke-WebRequest (alias iwr) returns a response object: status, headers, raw content, links and images. CAVEAT: in 5.1 it uses Internet Explorer's engine to parse HTML unless you pass -UseBasicParsing (which fails on machines where IE was never launched); PowerShell 6+ always uses basic parsing. Also in 5.1, very old TLS defaults can break HTTPS:  [Net.ServicePointManager]::SecurityProtocol = 'Tls12'.
        """)

        PsSection("REST APIs: Invoke-RestMethod")
        PsCode("""
            # JSON is converted to objects automatically
            §repo = Invoke-RestMethod 'https://api.github.com/repos/PowerShell/PowerShell'
            §repo.full_name
            §repo.stargazers_count
            §repo.license.name

            # Query parameters, headers, JSON body
            §headers = @{ Accept = 'application/json'; 'User-Agent' = 'PSCourse' }
            §body = @{ title = 'Hello'; userId = 1 } | ConvertTo-Json
            §new = Invoke-RestMethod -Uri 'https://jsonplaceholder.typicode.com/posts' `
                       -Method Post -Body §body -ContentType 'application/json' -Headers §headers
            §new.id                          # server-assigned id

            # Authentication with a bearer token kept out of the script
            §token = §env:GITHUB_TOKEN
            Invoke-RestMethod 'https://api.github.com/user' -Headers @{ Authorization = "Bearer §token" }
        """)
        PsOutput("""
            PowerShell/PowerShell
            48000
            MIT License
        """)
        PsText("""
            Invoke-RestMethod (alias irm) parses the response body: JSON → PSCustomObjects, XML → XmlDocument. You use the API's data directly as objects — no manual parsing. Compare with curl + jq in Bash. Never hard-code tokens in scripts; read them from environment variables or SecretManagement (lesson 25).

            PS 7 extras: -StatusCodeVariable, -SkipHttpErrorCheck (don't throw on 4xx/5xx), -FollowRelLink (automatic pagination), -Authentication Bearer -Token (SecureString), -MaximumRetryCount / -RetryIntervalSec.
        """)

        PsSection("CAVEATS")
        PsText("""
            • Test-NetConnection, Resolve-DnsName and Get-Net* are Windows-only; .NET classes ([System.Net.Dns], TcpClient, HttpClient) work everywhere.
            • ICMP blocked ≠ host down.
            • Invoke-WebRequest throws on HTTP 4xx/5xx — catch it (lesson 10) or use -SkipHttpErrorCheck (PS 7).
            • Respect API rate limits; GitHub allows only 60 unauthenticated requests per hour.
        """)

        PsSection("CONNECTIONS")
        PsText("Error handling from lesson 10 wraps every network call. JSON objects from lesson 12 are what Invoke-RestMethod returns. Lesson 22 goes further into .NET classes such as HttpClient and TcpClient.")

        PsExercise("""
            For the hosts 'github.com', 'microsoft.com' and 'no-such-host.invalid', output one object each with: Host, IPv4 (Resolve-DnsName, or §null), Ping (Test-Connection -Quiet), Https (Test-NetConnection -Port 443 TcpTestSucceeded). Handle the DNS failure gracefully.
        """)
        PsChallenge("""
            Use Invoke-RestMethod against https://api.github.com/users/<some user>/repos?per_page=100 to list repositories. Output Name, Language, Stars, LastPush, sort by stars, and group by Language with counts. Add a -Token parameter that, if given, sends an Authorization header. Handle HTTP 404 (unknown user) and 403 (rate limit) with distinct messages.
        """)
    }
}
