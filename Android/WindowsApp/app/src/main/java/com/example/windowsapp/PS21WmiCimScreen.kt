package com.example.windowsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PS21WmiCimScreen(navController: NavController) {
    PsLessonScreen(navController, "21 WMI AND CIM") {

        PsSection("THE PROBLEM WMI SOLVES")
        PsText("""
            How do you ask a computer "what's your BIOS version, how full are your disks, which services are stopped, what's your IP configuration?" Before WMI, every answer came from a different API: registry keys, kernel calls, vendor DLLs, SNMP agents, driver-specific IOCTLs. Every management tool reinvented the wheel, and many details were simply unreachable remotely.

            Enterprise management needed ONE consistent, queryable, remotely accessible model of everything on a machine.
        """)

        PsSection("WHAT IS CIM?")
        PsText("""
            CIM — the COMMON INFORMATION MODEL — is an open, vendor-neutral standard published by the DMTF (Distributed Management Task Force, an industry consortium founded in 1992, originally as the Desktop Management Task Force). CIM defines:

            • A SCHEMA — an object-oriented model of managed resources: computer systems, operating systems, processes, disks, network adapters, software, users… expressed as CLASSES with properties, methods and associations, organized in an inheritance hierarchy (e.g. CIM_ManagedSystemElement → CIM_LogicalElement → CIM_Process).
            • A META-MODEL and a language (MOF — Managed Object Format) to write class definitions.
            • Related standards for transport: WBEM (Web-Based Enterprise Management, the DMTF initiative from 1996 that CIM belongs to) and later WS-Management (WS-Man, SOAP over HTTP/HTTPS).

            CIM is a MODEL, not a product: any vendor can implement it. Linux implementations such as OpenPegasus and OMI exist too.
        """)

        PsSection("WHAT IS WMI?")
        PsText("""
            WMI — WINDOWS MANAGEMENT INSTRUMENTATION — is MICROSOFT'S IMPLEMENTATION of CIM/WBEM for Windows. It was built into Windows 2000 (earlier versions such as NT 4.0 could add it as a separate download) and is part of every Windows version since.

            Architecture:
            • CIM OBJECT MANAGER (CIMOM) — the WMI service (winmgmt, hosted in svchost) that receives queries and routes them.
            • REPOSITORY — a database (%windir%\System32\wbem\Repository) holding class DEFINITIONS and some static data.
            • PROVIDERS — COM DLLs that fetch LIVE data on demand. The Win32 provider reads processes/services from the OS, the registry provider reads keys, storage providers read disks, drivers can expose their own data through WMI (WMI's kernel side, used by drivers via IoWMIRegistrationControl). Many providers run in WmiPrvSE.exe host processes.
            • CONSUMERS — any client: PowerShell, wmic.exe (deprecated), SCCM/Intune, monitoring tools, C++ via IWbemServices (COM, lesson 20).
        """)
        PsCode("""
              PowerShell / tools  (consumers)
                     │  COM / DCOM / WS-Man
                     ▼
              WMI service (CIMOM, winmgmt)
                ├── Repository (class definitions)
                └── Providers ──► OS, registry, drivers, disks, network
        """)

        PsSection("NAMESPACES, CLASSES, INSTANCES")
        PsText("""
            • NAMESPACE — a folder of related classes. root\cimv2 is the default and holds most OS classes (Win32_*). Others: root\StandardCimv2 (modern networking — NetTCPIP cmdlets), root\Microsoft\Windows\Storage (Storage cmdlets), root\SecurityCenter2 (antivirus status on clients), root\wmi (driver data), root\subscription (event subscriptions).
            • CLASS — a type definition, e.g. Win32_Process: properties (Name, ProcessId, CommandLine), methods (Create, Terminate) and qualifiers (metadata like Key, read/write).
            • INSTANCE — one actual object: the Win32_Process instance for notepad.exe with ProcessId 4120.
            • Microsoft extends the standard: Win32_Process DERIVES from the DMTF class CIM_Process, adding Windows-specific properties. So WMI = CIM schema + Microsoft extensions + Windows providers.
        """)
        PsText("""
            What WMI exposes (a few of the thousands of classes):
            • OS: Win32_OperatingSystem (version, boot time, memory), Win32_ComputerSystem, Win32_QuickFixEngineering (hotfixes)
            • Hardware: Win32_Processor, Win32_PhysicalMemory, Win32_BIOS, Win32_BaseBoard, Win32_VideoController
            • Processes and services: Win32_Process (incl. CommandLine and owner!), Win32_Service (start account, path)
            • Storage: Win32_LogicalDisk, Win32_DiskDrive, Win32_Volume, MSFT_Disk
            • Networking: Win32_NetworkAdapterConfiguration, MSFT_NetIPAddress
            • Events: __InstanceCreationEvent (react when a process starts, a USB disk appears…)
        """)

        PsSection("WMI vs CIM — THE RELATIONSHIP")
        PsCode("""
                         CIM                         WMI
            ─────────────────────────────────────────────────────────
            What         open STANDARD (model +      Microsoft's IMPLEMENT-
                         protocols)                  ATION on Windows
            Owner        DMTF                        Microsoft
            Classes      CIM_* base schema           CIM_* + Win32_* + MSFT_*
            Platforms    any (OMI on Linux…)         Windows
            Transport    WS-Man (modern standard)    COM locally, DCOM remotely
                                                     (+ WS-Man via WinRM)
        """)
        PsText("In everyday PowerShell, \"WMI\" and \"CIM\" refer to the SAME data on Windows. The difference that matters is which CMDLETS and which REMOTE PROTOCOL you use.")

        PsSection("WMI CMDLETS vs CIM CMDLETS")
        PsText("""
            POWERSHELL 1.0/2.0 — the WMI cmdlets: Get-WmiObject, Invoke-WmiMethod, Set-WmiInstance, Register-WmiEvent. They used the .NET System.Management classes over COM locally and DCOM remotely.

            POWERSHELL 3.0 (2012) — the CIM cmdlets: Get-CimInstance, Get-CimClass, Invoke-CimMethod, New-CimSession, Register-CimIndicationEvent…, built on the newer Windows MI (Management Infrastructure) API.

            POWERSHELL 6/7 — the WMI cmdlets were REMOVED (System.Management wasn't part of .NET Core when PowerShell went cross-platform). Only CIM cmdlets remain.
        """)
        PsCode("""
                             Get-WmiObject (legacy)   Get-CimInstance (modern)
            ────────────────────────────────────────────────────────────────
            Available in     5.1 only                 3.0+, including 7
            Remote protocol  DCOM (RPC, port 135 +    WS-Man (WinRM: HTTP 5985/
                             dynamic high ports)      HTTPS 5986); DCOM optional
            Firewall         hard: dynamic ports      one well-known port
            Sessions         new connection per call  reusable CimSession
            Returned object  live ManagementObject    CimInstance: a DATA
                             with methods attached    snapshot (no methods)
            Methods          §obj.Terminate()         Invoke-CimMethod
            Dates            WMI string format        real DateTime
                             '20261006142233.5+120'
        """)
        PsText("""
            Why prefer CIM cmdlets for new scripts:
            • They work in PowerShell 7 (WMI cmdlets don't exist there).
            • WS-Man is firewall- and security-friendly (one port, Kerberos/HTTPS, the same WinRM channel PowerShell remoting uses), while DCOM needs RPC endpoint mapper port 135 plus a dynamic port range.
            • CimSessions are reusable and can target many machines at once efficiently.
            • Dates come back as DateTime objects, not strings needing ConvertToDateTime.
            • They're standards-based: they can even query non-Windows CIM servers (OMI).

            When a remote machine doesn't have WinRM enabled (old systems), CIM cmdlets can still use DCOM via a session option — so there's no reason left to use Get-WmiObject.
        """)

        PsSection("Get-CimInstance — QUERYING")
        PsCode("""
            Get-CimInstance -ClassName Win32_OperatingSystem |
                Select-Object Caption, Version, BuildNumber, OSArchitecture, LastBootUpTime

            # Filter on the provider side with -Filter (WQL WHERE syntax)
            Get-CimInstance Win32_Process -Filter "Name = 'notepad.exe'" |
                Select-Object ProcessId, CommandLine, CreationDate

            # Full WQL query
            Get-CimInstance -Query "SELECT Name, State FROM Win32_Service WHERE StartMode = 'Auto' AND State <> 'Running'"

            # Another namespace
            Get-CimInstance -Namespace root/SecurityCenter2 -ClassName AntiVirusProduct
        """)
        PsText("""
            WQL (WMI Query Language) is a SQL-like subset: SELECT … FROM class WHERE …, with = <> < > LIKE ('%' wildcard) AND OR. Filter with -Filter / WQL rather than Where-Object: the provider does the work and, remotely, only matching objects cross the network ("filter left").
        """)

        PsSection("PRACTICAL QUERIES")
        PsCode("""
            # Operating system and memory
            §os = Get-CimInstance Win32_OperatingSystem
            [PSCustomObject]@{
                OS       = §os.Caption
                Build    = §os.BuildNumber
                Uptime   = (Get-Date) - §os.LastBootUpTime
                RamGB    = [math]::Round(§os.TotalVisibleMemorySize / 1MB, 1)   # value is in KB
                FreeGB   = [math]::Round(§os.FreePhysicalMemory / 1MB, 1)
            }

            # Hardware
            Get-CimInstance Win32_ComputerSystem | Select-Object Manufacturer, Model, NumberOfLogicalProcessors
            Get-CimInstance Win32_Processor | Select-Object Name, NumberOfCores, MaxClockSpeed
            Get-CimInstance Win32_BIOS | Select-Object Manufacturer, SMBIOSBIOSVersion, SerialNumber
            Get-CimInstance Win32_PhysicalMemory | Measure-Object Capacity -Sum

            # Processes with owner and command line (Get-Process can't show these in 5.1)
            Get-CimInstance Win32_Process | Select-Object -First 5 Name, ProcessId, ParentProcessId, CommandLine

            # Services with account and binary path
            Get-CimInstance Win32_Service -Filter "State='Running'" | Select-Object Name, StartName, PathName

            # Disks: free space on fixed drives (DriveType 3)
            Get-CimInstance Win32_LogicalDisk -Filter 'DriveType = 3' |
                Select-Object DeviceID, @{N='SizeGB';E={[math]::Round(§_.Size/1GB)}}, @{N='FreeGB';E={[math]::Round(§_.FreeSpace/1GB)}}

            # Network configuration
            Get-CimInstance Win32_NetworkAdapterConfiguration -Filter 'IPEnabled = True' |
                Select-Object Description, IPAddress, DefaultIPGateway, DNSServerSearchOrder, MACAddress
        """)

        PsSection("DISCOVERING CLASSES — DON'T MEMORIZE")
        PsCode("""
            Get-CimClass -ClassName *disk*                    # search by name
            Get-CimClass -ClassName Win32_* -PropertyName ProcessId
            Get-CimClass -ClassName * -MethodName Terminate   # who has this method?
            Get-CimClass -Namespace root/StandardCimv2 -ClassName MSFT_Net*

            §cls = Get-CimClass Win32_Process
            §cls.CimClassProperties | Select-Object Name, CimType
            §cls.CimClassMethods    | Select-Object Name, @{N='Params';E={§_.Parameters.Name -join ', '}}
            §cls.CimSuperClassName  # CIM_Process — the DMTF base class!

            Get-CimInstance -Namespace root -ClassName __Namespace | Select-Object Name   # list namespaces
        """)
        PsOutput("""
            Name       Params
            ----       ------
            Create     CommandLine, CurrentDirectory, ProcessStartupInformation, ProcessId
            Terminate  Reason
            GetOwner   Domain, ReturnValue, User
            GetOwnerSid Sid
            …
        """)
        PsText("Get-CimClass is the WMI equivalent of Get-Command + Get-Member: it lets you discover classes, properties, methods and parameters by searching. You only need to remember that the tool exists.")

        PsSection("Invoke-CimMethod — CALLING METHODS")
        PsCode("""
            # Read-only method: who owns each process?
            Get-CimInstance Win32_Process -Filter "Name='explorer.exe'" |
                Invoke-CimMethod -MethodName GetOwner
        """)
        PsWarn("The next examples START and TERMINATE processes through WMI. Only use them on processes you started yourself.")
        PsCode("""
            # Static method on the CLASS: create a process
            §r = Invoke-CimMethod -ClassName Win32_Process -MethodName Create `
                     -Arguments @{ CommandLine = 'notepad.exe' }
            §r.ReturnValue    # 0 = success
            §r.ProcessId

            # Instance method: terminate it
            Get-CimInstance Win32_Process -Filter "ProcessId = §(§r.ProcessId)" |
                Invoke-CimMethod -MethodName Terminate
        """)
        PsText("WMI methods report success through ReturnValue (0 = success; other codes are documented per class) rather than exceptions — check it explicitly.")

        PsSection("REMOTING: CIM SESSIONS")
        PsCode("""
            # One-off remote query (WS-Man by default)
            Get-CimInstance Win32_OperatingSystem -ComputerName srv01

            # Reusable session(s)
            §s = New-CimSession -ComputerName srv01, srv02 -Credential (Get-Credential)
            Get-CimInstance Win32_LogicalDisk -CimSession §s -Filter 'DriveType=3'
            Get-CimInstance Win32_Service -CimSession §s -Filter "Name='Spooler'"
            Remove-CimSession §s

            # Old machine without WinRM: fall back to DCOM
            §opt = New-CimSessionOption -Protocol Dcom
            §old = New-CimSession -ComputerName legacy01 -SessionOption §opt
            Get-CimInstance Win32_BIOS -CimSession §old
        """)
        PsText("""
            New-CimSession opens a connection once and reuses it for many calls — much faster than reconnecting each time, and results carry a PSComputerName property telling you which machine they came from. Many Windows modules (NetTCPIP, Storage, ScheduledTasks) are built on CIM and accept -CimSession too. Locally (no -ComputerName), CIM cmdlets talk to WMI directly over COM — no WinRM needed.
        """)

        PsSection("CAVEATS")
        PsText("""
            • Get-WmiObject doesn't exist in PowerShell 7 — translate old scripts to Get-CimInstance (+ Invoke-CimMethod for methods).
            • Remote WS-Man requires WinRM enabled (Enable-PSRemoting, on by default on Windows Server) and appropriate permissions.
            • Avoid Win32_Product: querying it triggers a consistency check (and possibly repair) of every MSI-installed program. Use the registry Uninstall keys (lesson 13) instead.
            • Some classes are slow (Win32_QuickFixEngineering) or need admin rights / particular namespaces.
            • Units differ per class: TotalVisibleMemorySize is in KB, Capacity in bytes — read the class docs.
        """)

        PsSection("CONNECTIONS")
        PsText("WMI's client API is COM (lesson 20) and its remote transports are DCOM and WS-Man; see Advanced Topics RPC AND WMI for the internals. CIM results are objects for the pipeline (lesson 16). Lesson 26 builds a full diagnostic tool on these classes.")

        PsExercise("""
            Using only Get-CimClass, find: (1) a class whose name contains "Battery", (2) all classes in root/cimv2 that have a method named StopService, (3) the properties of Win32_VideoController. Then query one interesting property from each.
        """)
        PsChallenge("""
            Write Get-RemoteInventory -ComputerName <names> that creates ONE CimSession for all computers (falling back to DCOM for any that fail with WS-Man), collects OS caption/build, CPU name, total RAM, fixed disks with free %, and the list of stopped Automatic services, and outputs one object per computer. Remove the sessions in a finally block. Test with localhost.
        """)
    }
}
