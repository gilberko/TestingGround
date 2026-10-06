package com.example.windowsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PS26ProjectScreen(navController: NavController) {
    PsLessonScreen(navController, "26 PRACTICAL POWERSHELL PROJECT") {

        PsSection("THE PROJECT: Get-SystemDiagnostic")
        PsText("""
            To finish the course we build a real tool: a Windows SYSTEM-INFORMATION AND DIAGNOSTIC script that collects:
            • operating system details and uptime
            • CPU and memory
            • disks and free space
            • network configuration
            • top processes by memory and CPU
            • services that should be running but aren't
            • a list of WARNINGS (low disk, high memory, stopped services)
            …handles errors per section so one failure doesn't stop the report, and exports everything to JSON and/or CSV.

            Each part uses concepts from earlier lessons; the CONNECTIONS notes below each section name them.
        """)

        PsSection("DESIGN")
        PsCode("""
            SysDiag.ps1
            ├── param block             → script parameters        (lessons 2, 7, 24)
            ├── Invoke-Section helper   → error isolation          (10, 11)
            ├── Get-OsInfo              → CIM                      (21)
            ├── Get-CpuMemoryInfo       → CIM + math               (21, 4)
            ├── Get-DiskInfo            → CIM + objects            (21, 17)
            ├── Get-NetworkInfo         → NetTCPIP / CIM           (15, 21)
            ├── Get-TopProcess          → object pipeline          (14, 16)
            ├── Get-ServiceProblem      → CIM filter               (14, 21)
            ├── Get-HealthWarning       → conditions + enum        (5, 18)
            └── main: collect → report → export JSON/CSV           (12)
        """)
        PsText("""
            Principles: every collector is a small FUNCTION returning OBJECTS (never formatted text); a wrapper turns any failure into a recorded error instead of a crash; formatting and export happen only at the end. Run the script from an elevated PowerShell 7 for the most complete data; it degrades gracefully when not elevated.
        """)
        PsWarn("The script only READS system information, but it WRITES report files to the folder given by -OutputPath (default: a SysDiag folder in your Documents). Review any script — including this one — before running it.")

        PsSection("PART 1: PARAMETERS AND SETUP")
        PsCode("""
            <#
            .SYNOPSIS
                Collects Windows system information and health warnings.
            .EXAMPLE
                .\SysDiag.ps1 -Format Both -TopProcesses 15
            #>
            #Requires -Version 7.2
            [CmdletBinding()]
            param(
                [ValidateScript({ Test-Path §_ -IsValid })]
                [string]§OutputPath = (Join-Path ([Environment]::GetFolderPath('MyDocuments')) 'SysDiag'),

                [ValidateSet('Json', 'Csv', 'Both')]
                [string]§Format = 'Json',

                [ValidateRange(1, 50)]
                [int]§TopProcesses = 10,

                [ValidateRange(1, 99)]
                [int]§DiskWarnPercent = 15
            )

            Set-StrictMode -Version 1.0
            §ErrorActionPreference = 'Stop'
            §script:Errors = [System.Collections.Generic.List[object]]::new()
        """)
        PsText("""
            Comment-based help makes Get-Help .\SysDiag.ps1 work (lesson 7). #Requires stops on old versions (lesson 25). Validation attributes reject bad input before anything runs (lesson 24). Set-StrictMode -Version 1.0 makes reading a never-assigned variable an error, which catches typos (later strict-mode versions also reject missing properties, which would trip on optional data such as an adapter without a gateway); ErrorActionPreference = 'Stop' makes every cmdlet error catchable (lesson 10). §script:Errors is shared state for all functions in the script (lesson 9), and a generic List avoids slow += (lesson 22).
        """)

        PsSection("PART 2: ERROR ISOLATION WITH A SCRIPT BLOCK")
        PsCode("""
            function Invoke-Section {
                param(
                    [Parameter(Mandatory)][string]§Name,
                    [Parameter(Mandatory)][scriptblock]§Action
                )
                Write-Verbose "Collecting §Name…"
                §sw = [System.Diagnostics.Stopwatch]::StartNew()
                try {
                    & §Action
                }
                catch {
                    §script:Errors.Add([PSCustomObject]@{
                        Section = §Name
                        Error   = §_.Exception.Message
                        Type    = §_.Exception.GetType().Name
                    })
                    Write-Warning "Section '§Name' failed: §(§_.Exception.Message)"
                    §null
                }
                finally {
                    Write-Verbose ("{0} took {1} ms" -f §Name, §sw.ElapsedMilliseconds)
                }
            }
        """)
        PsText("""
            Each section is passed in as a SCRIPT BLOCK (lesson 11) and run with &. Any terminating error is caught, recorded with its type, and the section returns §null — the report continues. The Stopwatch (lesson 22) and finally block (lesson 10) give timing even on failure.
        """)

        PsSection("PART 3: OS, CPU AND MEMORY")
        PsCode("""
            function Get-OsInfo {
                §os = Get-CimInstance Win32_OperatingSystem
                §cs = Get-CimInstance Win32_ComputerSystem
                [PSCustomObject]@{
                    ComputerName = §env:COMPUTERNAME
                    OS           = §os.Caption
                    Version      = §os.Version
                    Build        = §os.BuildNumber
                    Architecture = §os.OSArchitecture
                    Manufacturer = §cs.Manufacturer
                    Model        = §cs.Model
                    LastBoot     = §os.LastBootUpTime
                    UptimeDays   = [math]::Round(((Get-Date) - §os.LastBootUpTime).TotalDays, 1)
                    PowerShell   = §PSVersionTable.PSVersion.ToString()
                }
            }

            function Get-CpuMemoryInfo {
                §cpu = Get-CimInstance Win32_Processor | Select-Object -First 1
                §os  = Get-CimInstance Win32_OperatingSystem
                §totalGB = §os.TotalVisibleMemorySize / 1MB        # KB → GB
                §freeGB  = §os.FreePhysicalMemory / 1MB
                [PSCustomObject]@{
                    CpuName        = §cpu.Name.Trim()
                    Cores          = §cpu.NumberOfCores
                    LogicalCpus    = §cpu.NumberOfLogicalProcessors
                    CpuLoadPercent = §cpu.LoadPercentage
                    MemoryTotalGB  = [math]::Round(§totalGB, 1)
                    MemoryFreeGB   = [math]::Round(§freeGB, 1)
                    MemoryUsedPct  = [math]::Round(100 * (§totalGB - §freeGB) / §totalGB)
                }
            }
        """)
        PsText("CIM classes (lesson 21) return typed values: LastBootUpTime is already a DateTime, so date math works directly. Memory values in Win32_OperatingSystem are in KILOBYTES — dividing by 1MB (1,048,576) converts KB to GB. [math]::Round and calculated values come from lessons 4 and 22.")

        PsSection("PART 4: DISKS AND NETWORK")
        PsCode("""
            function Get-DiskInfo {
                Get-CimInstance Win32_LogicalDisk -Filter 'DriveType = 3' | ForEach-Object {
                    [PSCustomObject]@{
                        Drive   = §_.DeviceID
                        Label   = §_.VolumeName
                        FileSys = §_.FileSystem
                        SizeGB  = [math]::Round(§_.Size / 1GB, 1)
                        FreeGB  = [math]::Round(§_.FreeSpace / 1GB, 1)
                        FreePct = if (§_.Size) { [math]::Round(100 * §_.FreeSpace / §_.Size) } else { 0 }
                    }
                }
            }

            function Get-NetworkInfo {
                Get-NetIPConfiguration | Where-Object { §_.NetAdapter.Status -eq 'Up' } | ForEach-Object {
                    [PSCustomObject]@{
                        Interface = §_.InterfaceAlias
                        IPv4      = (§_.IPv4Address.IPAddress -join ', ')
                        Gateway   = (§_.IPv4DefaultGateway.NextHop -join ', ')
                        DNS       = (§_.DNSServer | Where-Object AddressFamily -eq 2).ServerAddresses -join ', '
                        MAC       = §_.NetAdapter.MacAddress
                        LinkSpeed = §_.NetAdapter.LinkSpeed
                    }
                }
            }
        """)
        PsText("""
            -Filter 'DriveType = 3' filters at the provider (fixed disks only — "filter left", lessons 16, 21). The if expression inside the hash table avoids division by zero (lesson 5). Get-NetIPConfiguration (lesson 15) returns nested objects; -join flattens arrays into strings so they export cleanly to CSV. AddressFamily 2 = IPv4 (23 = IPv6).
        """)

        PsSection("PART 5: PROCESSES AND SERVICES")
        PsCode("""
            function Get-TopProcess {
                param([int]§Top = 10)
                Get-Process |
                    Sort-Object WorkingSet64 -Descending |
                    Select-Object -First §Top |
                    ForEach-Object {
                        [PSCustomObject]@{
                            Name     = §_.ProcessName
                            Id       = §_.Id
                            MemoryMB = [math]::Round(§_.WorkingSet64 / 1MB)
                            CpuSec   = [math]::Round([double]§_.CPU, 1)
                            Threads  = §_.Threads.Count
                            Path     = try { §_.Path } catch { '<access denied>' }
                        }
                    }
            }

            function Get-ServiceProblem {
                Get-CimInstance Win32_Service -Filter "StartMode = 'Auto' AND State <> 'Running'" |
                    Where-Object { §_.Name -notmatch '^(sppsvc|gupdate|edgeupdate|MapsBroker)§' } |
                    Select-Object Name, DisplayName, State, StartName,
                        @{ N = 'ExitCode'; E = { §_.ExitCode } }
            }
        """)
        PsText("""
            Get-TopProcess is the object pipeline in action (lessons 14, 16). CPU can be §null for protected processes, so it's cast to [double] (lesson 4); Path can throw access-denied, handled inline with try/catch as an EXPRESSION (lesson 10). Get-ServiceProblem uses WQL to find Automatic services that aren't running (lessons 14, 21) and a regex (lesson 19) to exclude services that normally stop by design (delayed/triggered updaters).
        """)

        PsSection("PART 6: HEALTH WARNINGS WITH AN ENUM")
        PsCode("""
            enum Severity { Info; Warning; Critical }

            function Get-HealthWarning {
                param(§Report, [int]§DiskWarnPercent)

                foreach (§d in @(§Report.Disks)) {
                    if (§null -eq §d) { continue }
                    if (§d.FreePct -lt 5) {
                        [PSCustomObject]@{ Severity = [Severity]::Critical; Message = "§(§d.Drive) only §(§d.FreePct)% free" }
                    }
                    elseif (§d.FreePct -lt §DiskWarnPercent) {
                        [PSCustomObject]@{ Severity = [Severity]::Warning;  Message = "§(§d.Drive) §(§d.FreePct)% free" }
                    }
                }
                if (§Report.CpuMemory -and §Report.CpuMemory.MemoryUsedPct -ge 90) {
                    [PSCustomObject]@{ Severity = [Severity]::Warning; Message = "Memory §(§Report.CpuMemory.MemoryUsedPct)% used" }
                }
                if (§Report.OS -and §Report.OS.UptimeDays -gt 30) {
                    [PSCustomObject]@{ Severity = [Severity]::Info; Message = "No reboot for §(§Report.OS.UptimeDays) days" }
                }
                foreach (§s in @(§Report.ServiceProblems)) {
                    if (§s) { [PSCustomObject]@{ Severity = [Severity]::Warning; Message = "Service §(§s.Name) is §(§s.State)" } }
                }
            }
        """)
        PsText("""
            An ENUM (lesson 18) makes severities typed and sortable (Critical > Warning > Info). @(…) forces an array even when a section returned a single object or §null (lesson 4); the §null checks keep failed sections (which return §null) from causing new errors. Each warning is an object, so the caller can sort, filter or export them.
        """)

        PsSection("PART 7: MAIN — COLLECT, REPORT, EXPORT")
        PsCode("""
            §report = [ordered]@{
                CollectedAt     = Get-Date
                OS              = Invoke-Section 'OS'        { Get-OsInfo }
                CpuMemory       = Invoke-Section 'CPU/Memory' { Get-CpuMemoryInfo }
                Disks           = Invoke-Section 'Disks'     { Get-DiskInfo }
                Network         = Invoke-Section 'Network'   { Get-NetworkInfo }
                TopProcesses    = Invoke-Section 'Processes' { Get-TopProcess -Top §TopProcesses }
                ServiceProblems = Invoke-Section 'Services'  { Get-ServiceProblem }
            }
            §report.Warnings = @(Get-HealthWarning -Report ([PSCustomObject]§report) -DiskWarnPercent §DiskWarnPercent |
                                 Sort-Object Severity -Descending)
            §report.Errors = §script:Errors

            # ── Console summary ──────────────────────────────
            if (§report.OS) {
                Write-Host ("{0} — {1} (build {2}), up {3} days" -f
                    §report.OS.ComputerName, §report.OS.OS, §report.OS.Build, §report.OS.UptimeDays)
            }
            §report.Disks | Format-Table Drive, SizeGB, FreeGB, FreePct -AutoSize | Out-Host
            foreach (§w in §report.Warnings) {
                §color = switch (§w.Severity) { 'Critical' { 'Red' } 'Warning' { 'Yellow' } default { 'Cyan' } }
                Write-Host "[§(§w.Severity)] §(§w.Message)" -ForegroundColor §color
            }

            # ── Export ───────────────────────────────────────
            §null = New-Item -Path §OutputPath -ItemType Directory -Force
            §stamp = Get-Date -Format 'yyyyMMdd_HHmmss'
            §base  = Join-Path §OutputPath "SysDiag_§(§env:COMPUTERNAME)_§stamp"

            if (§Format -in 'Json', 'Both') {
                [PSCustomObject]§report | ConvertTo-Json -Depth 5 -EnumsAsStrings |
                    Set-Content "§base.json" -Encoding utf8
                Write-Host "JSON: §base.json"
            }
            if (§Format -in 'Csv', 'Both') {
                foreach (§section in 'Disks', 'Network', 'TopProcesses', 'ServiceProblems', 'Warnings') {
                    if (§report[§section]) {
                        §report[§section] | Export-Csv "§base`_§section.csv" -NoTypeInformation -Encoding utf8
                    }
                }
                Write-Host "CSV files: §base`_*.csv"
            }

            if (§script:Errors.Count) {
                Write-Warning "§(§script:Errors.Count) section(s) failed — see Errors in the report."
                exit 1
            }
        """)
        PsText("""
            Walkthrough:
            • The [ordered] hash table (lesson 4) keeps sections in a predictable order in the JSON. Each value comes from Invoke-Section with a script block — the main logic reads like a table of contents.
            • Get-HealthWarning receives the report converted to a PSCustomObject so property access works uniformly.
            • Console output uses Write-Host / Out-Host deliberately: it's for the human and doesn't pollute the script's output stream (lesson 7). switch maps severity to color (lesson 5).
            • New-Item -Force on a FOLDER is safe (creates it if missing, no error if it exists — lesson 12). Join-Path builds paths; the timestamp makes every report unique.
            • ConvertTo-Json -Depth 5 is essential — the default depth of 2 would flatten nested sections (lesson 12). -EnumsAsStrings (PS 6.2+) writes "Warning" instead of the enum's number 1. CSV is flat, so each section becomes its own CSV file. `_ escapes the underscore so PowerShell doesn't read "§base_" as one variable name.
            • exit 1 tells a scheduler or CI system the run was not fully successful (lesson 10).
        """)

        PsSection("RUNNING IT")
        PsCode("""
            .\SysDiag.ps1 -Verbose
            .\SysDiag.ps1 -Format Both -TopProcesses 15 -DiskWarnPercent 20
            Get-Help .\SysDiag.ps1 -Examples

            # Use the JSON later — the data is objects again:
            §r = Get-Content (Get-ChildItem ~\Documents\SysDiag\*.json | Sort-Object LastWriteTime | Select-Object -Last 1) -Raw |
                     ConvertFrom-Json
            §r.Warnings | Format-Table
            §r.TopProcesses | Sort-Object CpuSec -Descending | Select-Object -First 3
        """)
        PsOutput("""
            DESKTOP-GB01 — Microsoft Windows 11 Pro (build 26100), up 3.2 days

            Drive SizeGB FreeGB FreePct
            ----- ------ ------ -------
            C:     476.3   61.2      13
            D:     931.5  512.8      55

            [Warning] C: 13% free
            [Warning] Service wuauserv is Stopped
            JSON: C:\Users\gil\Documents\SysDiag\SysDiag_DESKTOP-GB01_20261006_143012.json
        """)

        PsSection("CAVEATS")
        PsText("""
            • Some data (other users' process paths, certain services) needs elevation; the script records errors instead of failing.
            • Get-NetIPConfiguration is Windows-only; the whole script targets Windows with PowerShell 7.2+.
            • LoadPercentage is a momentary sample; for a real CPU average take several samples (Get-Counter '\Processor(_Total)\% Processor Time' -SampleInterval 1 -MaxSamples 5).
            • Exclusion lists for "normal" stopped services vary per machine — tune the regex.
        """)

        PsSection("CONNECTIONS — THE WHOLE COURSE")
        PsText("""
            You've just combined: scripts & parameters (2), cmdlets & the pipeline (3, 16), variables & types (4), conditions & switch (5), loops (6), functions & advanced functions (7, 24), scope (9), error handling (10), script blocks (11), files, JSON & CSV (12), processes & services (14), networking (15), enums & objects (17, 18), regex (19), WMI/CIM (21) and .NET (22) — PowerShell as a real programming language for Windows automation.
        """)

        PsExercise("""
            Type in (don't copy-paste) the complete script, run it with -Verbose, and open the JSON report. Then deliberately break one section (e.g. change Win32_LogicalDisk to Win32_LogicalDiskX) and confirm the report still completes with an entry in Errors.
        """)
        PsChallenge("""
            Extend the tool:
            1. Turn it into a MODULE (lesson 8) exposing Get-SystemDiagnostic as an advanced function with -ComputerName support using CIM sessions (lesson 21) for remote machines.
            2. Add sections for: last 10 System-log errors (Get-WinEvent -FilterHashtable), installed hotfixes in the last 30 days, and pending-reboot detection (registry keys under HKLM:\SOFTWARE\Microsoft\Windows\CurrentVersion\Component Based Servicing\RebootPending — lesson 13).
            3. Generate an HTML report with ConvertTo-Html and a little CSS, highlighting Critical warnings in red.
            4. Register it as a daily scheduled task (Register-ScheduledTask) that keeps only the last 14 reports.
        """)
    }
}
