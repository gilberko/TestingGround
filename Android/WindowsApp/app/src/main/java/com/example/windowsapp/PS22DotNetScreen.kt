package com.example.windowsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PS22DotNetScreen(navController: NavController) {
    PsLessonScreen(navController, "22 USING .NET FROM POWERSHELL") {

        PsSection("POWERSHELL IS A .NET LANGUAGE")
        PsText("""
            PowerShell runs on .NET (.NET Framework 4.x for 5.1, modern .NET 8/10 for 7.x). Every object you've seen IS a .NET object, and you can use the ENTIRE .NET class library directly: thousands of types for files, networking, cryptography, compression, XML, threading, math, globalization…

            So if no cmdlet does what you need, it's very likely that a .NET class does. Any C# example on Microsoft Learn can be translated to PowerShell almost line by line. This is what turns PowerShell from "a shell with commands" into a general-purpose programming environment.
        """)

        PsSection("TYPE SYNTAX")
        PsCode("""
            [System.IO.File]           # a type literal: square brackets + full name
            [System.DateTime]
            [datetime]                 # type accelerator (short alias)
            [System.Collections.Generic.List[string]]   # generic type
            [System.IO.File].FullName  # the type itself is an object (System.Type)

            using namespace System.Text            # top of script: shorter names
            [StringBuilder]::new()
        """)
        PsText("""
            [TypeName] refers to a .NET TYPE. "System." can be omitted ([IO.File] works). Accelerators like [int], [string], [regex], [xml], [ipaddress], [pscustomobject] are built-in short names — list them all with:
              [psobject].Assembly.GetType('System.Management.Automation.TypeAccelerators')::Get
        """)

        PsSection("STATIC MEMBERS: [Type]::Member")
        PsCode("""
            [System.Math]::Sqrt(144)                 # 12
            [Math]::PI                               # 3.14159265358979
            [DateTime]::Now
            [DateTime]::IsLeapYear(2028)             # True
            [Environment]::MachineName
            [Environment]::OSVersion
            [Environment]::ProcessorCount
            [Environment]::GetFolderPath('MyDocuments')
            [System.IO.Path]::Combine('C:\Data', 'a.txt')
            [Guid]::NewGuid()
            [int]::MaxValue                          # 2147483647
            [int]::TryParse('42', [ref]§n)           # True, §n = 42
        """)
        PsText("""
            :: accesses STATIC members — they belong to the type, no object needed. [ref] passes a variable BY REFERENCE for .NET "out" parameters, as in TryParse. To see what a type offers:  [Math] | Get-Member -Static.
        """)

        PsSection("CREATING OBJECTS")
        PsCode("""
            §sb = [System.Text.StringBuilder]::new()          # PS 5+ preferred
            §sb = New-Object System.Text.StringBuilder        # older cmdlet form
            §sw = [System.Diagnostics.Stopwatch]::StartNew()  # static factory method
            §uri = [Uri]'https://learn.microsoft.com/powershell?view=7'   # cast from string

            [void]§sb.Append('Hello').Append(', ').Append('.NET')
            §sb.ToString()             # Hello, .NET
            §sb.Length                 # 11

            §uri.Host                  # learn.microsoft.com
            §uri.Query                 # ?view=7
            §sw.Elapsed.TotalMilliseconds

            [System.Text.StringBuilder]::new          # shows constructor overloads
        """)
        PsText("""
            ::new(args) calls a CONSTRUCTOR. Typing  [Type]::new  without parentheses lists the available constructor overloads — a quick way to see what arguments are accepted. Casting a string to a type ([Uri]'…', [ipaddress]'10.0.0.1', [version]'1.2.3') uses the type's parse logic.
        """)

        PsSection("PROPERTIES, METHODS AND ENUMS")
        PsCode("""
            §d = [DateTime]::new(2026, 10, 6, 14, 30, 0)
            §d.DayOfWeek                     # Tuesday — an enum value
            §d.AddDays(30).ToString('yyyy-MM-dd')
            (§d - [DateTime]::Today).TotalHours
            [TimeSpan]::FromMinutes(90)      # 01:30:00

            # Enums
            [System.DayOfWeek]::Friday
            [Enum]::GetNames([System.ConsoleColor])
            [System.IO.FileAttributes]'Hidden, ReadOnly'      # flags from a string
            (Get-Item C:\Windows).Attributes -band [IO.FileAttributes]::Directory
            Write-Host 'Warning!' -ForegroundColor ([ConsoleColor]::Yellow)
        """)

        PsSection("FILES WITH .NET")
        PsCode("""
            [System.IO.File]::Exists('C:\Windows\notepad.exe')
            [System.IO.File]::ReadAllLines('C:\Windows\System32\drivers\etc\hosts')
            [System.IO.File]::GetLastWriteTime('C:\Windows\notepad.exe')
            [System.IO.Directory]::GetDirectories('C:\Users')
            [System.IO.DriveInfo]::GetDrives() | Select-Object Name, DriveType, TotalFreeSpace

            # Compression: zip a folder
            Add-Type -AssemblyName System.IO.Compression.FileSystem   # needed in 5.1
            [System.IO.Compression.ZipFile]::CreateFromDirectory('C:\Temp\PSCourse', 'C:\Temp\PSCourse.zip')
        """)
        PsText("Some assemblies aren't loaded by default in 5.1; Add-Type -AssemblyName loads them. Remember: .NET uses the process working directory, so always pass FULL paths (lesson 12).")

        PsSection("DATES AND CULTURES")
        PsCode("""
            [DateTime]::ParseExact('06/10/2026', 'dd/MM/yyyy', §null)
            [DateTime]::UtcNow
            [DateTimeOffset]::Now.ToUnixTimeSeconds()
            [TimeZoneInfo]::FindSystemTimeZoneById('Tokyo Standard Time')
            [TimeZoneInfo]::ConvertTimeBySystemTimeZoneId([DateTime]::UtcNow, 'Tokyo Standard Time')
            [CultureInfo]::GetCultureInfo('de-DE').DateTimeFormat.DayNames
            (1234567.891).ToString('N2', [CultureInfo]'fr-FR')        # 1 234 567,89
        """)

        PsSection("NETWORKING WITH .NET")
        PsCode("""
            [System.Net.Dns]::GetHostEntry('github.com').AddressList
            [ipaddress]::Parse('192.168.1.10').GetAddressBytes()
            [System.Net.NetworkInformation.NetworkInterface]::GetAllNetworkInterfaces() |
                Select-Object Name, OperationalStatus, Speed

            §ping = [System.Net.NetworkInformation.Ping]::new()
            §ping.Send('8.8.8.8', 1000).Status       # Success / TimedOut

            # HttpClient (PS 7 / .NET Core)
            §http = [System.Net.Http.HttpClient]::new()
            try {
                §json = §http.GetStringAsync('https://api.github.com/zen').GetAwaiter().GetResult()
            } finally { §http.Dispose() }
        """)
        PsText("Async .NET methods return Task objects; .GetAwaiter().GetResult() waits for the result synchronously (PowerShell has no await keyword). GitHub may require a User-Agent header; add it via §http.DefaultRequestHeaders.")

        PsSection("COLLECTIONS")
        PsCode("""
            §list = [System.Collections.Generic.List[int]]::new()
            1..5 | ForEach-Object { §list.Add(§_) }
            §list.Contains(3); §list.Remove(3); §list.Count

            §dict = [System.Collections.Generic.Dictionary[string,int]]::new()
            §dict['apples'] = 5
            §dict.TryGetValue('apples', [ref]§count)

            §set = [System.Collections.Generic.HashSet[string]]::new([StringComparer]::OrdinalIgnoreCase)
            §set.Add('A'); §set.Add('a')     # True, False — duplicate ignored

            §queue = [System.Collections.Generic.Queue[string]]::new()
            §queue.Enqueue('job1'); §queue.Dequeue()

            §stack = [System.Collections.Generic.Stack[int]]::new()
            §sorted = [System.Collections.Generic.SortedDictionary[string,int]]::new()
        """)
        PsText("""
            Generic collections are TYPED (adding the wrong type converts or fails) and much faster than PowerShell arrays with +=. HashSet gives O(1) duplicate checking; Dictionary gives typed key/value lookup. Remember to discard the return value of .Add() on lists that return something (lesson 7).
        """)

        PsSection("CRYPTOGRAPHY")
        PsCode("""
            # Hash a string (SHA-256)
            §bytes = [System.Text.Encoding]::UTF8.GetBytes('hello')
            §hash  = [System.Security.Cryptography.SHA256]::Create().ComputeHash(§bytes)
            [System.BitConverter]::ToString(§hash) -replace '-', ''
            # PS 7 (.NET 5+): [Convert]::ToHexString([System.Security.Cryptography.SHA256]::HashData(§bytes))

            # Hash a file — cmdlet wraps the same .NET classes
            Get-FileHash C:\Windows\notepad.exe -Algorithm SHA256

            # Cryptographically secure random bytes / password
            §rnd = [byte[]]::new(16)
            [System.Security.Cryptography.RandomNumberGenerator]::Fill(§rnd)       # PS 7
            [Convert]::ToBase64String(§rnd)

            # Base64
            [Convert]::ToBase64String([Text.Encoding]::UTF8.GetBytes('PowerShell'))
            [Text.Encoding]::UTF8.GetString([Convert]::FromBase64String('UG93ZXJTaGVsbA=='))
        """)
        PsOutput("""
            2CF24DBA5FB0A30E26E83B2AC5B9E29E1B161E5C1FA7425E73043362938B9824
        """)
        PsText("""
            Use System.Security.Cryptography for anything security-related — never Get-Random (a non-cryptographic generator) for passwords or keys. Base64 is an ENCODING, not encryption.
        """)

        PsSection("Add-Type: LOAD OR COMPILE CODE")
        PsCode("""
            Add-Type -AssemblyName System.Windows.Forms
            [System.Windows.Forms.Clipboard]::GetText()

            Add-Type -Path C:\Libs\Newtonsoft.Json.dll      # any .NET DLL

            # Compile C# on the fly, including Win32 API calls (P/Invoke)
            Add-Type -TypeDefinition @'
            using System;
            using System.Runtime.InteropServices;
            public static class Native {
                [DllImport("kernel32.dll")]
                public static extern uint GetCurrentProcessId();
                [DllImport("user32.dll", CharSet = CharSet.Unicode)]
                public static extern int MessageBoxW(IntPtr h, string text, string caption, uint type);
            }
            '@
            [Native]::GetCurrentProcessId()    # same as §PID
        """)
        PsText("""
            Add-Type is the bridge to EVERYTHING: load assemblies by name or path, or compile C# source (in a single-quoted here-string so § isn't expanded). With [DllImport] you can call any Win32 API — the same functions described throughout this app's User Mode screens. A type compiled with Add-Type can't be changed or unloaded in the same session.
        """)

        PsSection("CAVEATS")
        PsText("""
            • 5.1 (.NET Framework) and 7 (.NET) have different APIs: HashData, ToHexString, RandomNumberGenerator.Fill, many Span APIs are 7-only; some Framework-only classes (System.Web, WCF) are missing in 7.
            • Pass full paths to .NET methods.
            • Methods are called with (a, b) — commas and parentheses, unlike cmdlets.
            • Dispose objects that hold resources (streams, HttpClient, crypto objects) in finally.
        """)

        PsSection("CONNECTIONS")
        PsText("You've used .NET since lesson 2 ([math]::Round). Lesson 12 used System.IO, lesson 15 TcpClient, lesson 17/18 classes compile to .NET types. Lesson 23 inspects .NET types and assemblies with reflection.")

        PsExercise("""
            1. Use [System.IO.DriveInfo]::GetDrives() to print each ready drive's name and free space percentage.
            2. Use a Stopwatch to time  Get-ChildItem C:\Windows\System32  and print the milliseconds.
            3. Create a HashSet of the extensions in C:\Windows\System32 and print how many unique extensions exist.
        """)
        PsChallenge("""
            Write New-StrongPassword -Length 20 that uses RandomNumberGenerator to pick characters from upper, lower, digits and symbols, guaranteeing at least one of each. Then write Protect-Text / Unprotect-Text that encrypt a string with AES ([System.Security.Cryptography.Aes]::Create()), returning Base64 of IV + ciphertext, and decrypt it again. Dispose every crypto object.
        """)
    }
}
