package com.example.windowsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PS14ProcessesServicesScreen(navController: NavController) {
    PsLessonScreen(navController, "14 PROCESSES, SERVICES AND THE WINDOWS SYSTEM") {

        PsSection("PROCESSES AND SERVICES — CONCEPTS")
        PsText("""
            A PROCESS is a running program: its own virtual address space, one or more threads, open handles and a security token (see the Internal Data Structures PROCESSES AND THREADS screen for the kernel view: EPROCESS / ETHREAD).

            A SERVICE is a program managed by the Service Control Manager (SCM, services.exe). It usually starts at boot, runs without a logged-on user, under an account like LocalSystem or NetworkService, and is controlled with start/stop/pause commands (see User Mode WRITING A SERVICE). Every RUNNING service is also a process — sometimes several services share one svchost.exe process.

            In PowerShell both are .NET objects:
            • Get-Process → System.Diagnostics.Process
            • Get-Service → System.ServiceProcess.ServiceController
        """)

        PsSection("INSPECTING PROCESSES")
        PsCode("""
            Get-Process                              # all
            Get-Process -Name chrome, Code           # by name (no .exe)
            Get-Process -Id 4120
            Get-Process -Name *host*                 # wildcard
            Get-Process -IncludeUserName             # owner (needs admin)
            Get-Process chrome | Measure-Object WorkingSet64 -Sum   # total RAM
        """)
        PsCode("""
            §p = Get-Process -Id §PID          # this PowerShell process
            §p | Get-Member -MemberType Property | Select-Object -First 15

            §p.ProcessName
            §p.Id
            §p.StartTime                       # DateTime
            (Get-Date) - §p.StartTime          # running for…
            §p.Path                            # full path to the .exe
            §p.WorkingSet64 / 1MB              # physical memory in use
            §p.CPU                             # total CPU seconds
            §p.Threads.Count
            §p.Modules | Select-Object -First 5 ModuleName, FileName   # loaded DLLs
            §p.MainModule.FileVersionInfo.ProductVersion
            §p.Parent                          # parent process (PS 7)
        """)
        PsText("""
            Every property comes straight from the .NET Process class, which reads OS information. Threads is a collection of ProcessThread objects, Modules lists the loaded DLLs (compare with the DLLs screen in Advanced Topics). Some properties throw "Access is denied" for processes owned by other users or protected processes (PPL — see Advanced Topics PROTECTED AND PPL).
        """)

        PsSection("PROCESS METHODS")
        PsCode("""
            §p | Get-Member -MemberType Method | Select-Object Name
            §p.Refresh()                 # re-read live values
            §p.WaitForExit(5000)         # wait up to 5 s, returns bool
            §p.Kill()                    # forcibly terminate (careful!)
            §p.CloseMainWindow()         # polite close: sends WM_CLOSE to its window
        """)

        PsSection("STARTING PROCESSES")
        PsWarn("Start-Process launches programs. The -Verb RunAs example triggers a UAC elevation prompt and runs the program with administrator rights.")
        PsCode("""
            Start-Process notepad
            Start-Process notepad -ArgumentList 'C:\Temp\PSCourse\a.txt'
            Start-Process 'https://learn.microsoft.com'        # default browser
            Start-Process C:\Temp\PSCourse\report.pdf          # default app for .pdf

            §proc = Start-Process ping -ArgumentList '-n 3 localhost' `
                        -NoNewWindow -Wait -PassThru
            §proc.ExitCode                                     # 0 = success

            Start-Process powershell -Verb RunAs               # elevated (UAC)
            Start-Process cmd -ArgumentList '/c dir > C:\Temp\PSCourse\d.txt' -WindowStyle Hidden
        """)
        PsText("""
            • -PassThru returns the Process object (otherwise Start-Process outputs nothing).
            • -Wait blocks until the process exits; -NoNewWindow runs console apps in the current window.
            • -Verb RunAs asks for elevation; -Verb Print prints a document.
            • Simply typing  ping -n 3 localhost  also runs a program, but synchronously and with its text output in the pipeline; Start-Process gives control over windows, verbs and credentials.
        """)

        PsSection("STOPPING PROCESSES")
        PsWarn("Stop-Process terminates programs immediately — unsaved work is lost, and killing system processes can crash Windows or force a reboot. Always test with -WhatIf.")
        PsCode("""
            Start-Process notepad
            Get-Process notepad | Stop-Process -WhatIf
            Get-Process notepad | Stop-Process               # by object
            Stop-Process -Name notepad                       # by name
            Stop-Process -Id 12345 -Force                    # by id, no confirm

            # Polite first, forceful if needed
            §n = Get-Process notepad -ErrorAction SilentlyContinue
            if (§n) {
                §null = §n.CloseMainWindow()
                if (-not §n.WaitForExit(3000)) { §n | Stop-Process -Force }
            }
        """)
        PsText("Stop-Process calls TerminateProcess under the hood — the program gets no chance to clean up. CloseMainWindow() asks a GUI app to close normally (it may show \"Save changes?\").")

        PsSection("INSPECTING SERVICES")
        PsCode("""
            Get-Service                                      # all
            Get-Service -Name Spooler
            Get-Service -DisplayName '*Update*'
            Get-Service | Where-Object Status -eq Running | Measure-Object
            Get-Service Spooler -DependentServices           # who needs it
            Get-Service Spooler -RequiredServices            # what it needs

            §s = Get-Service W32Time
            §s | Get-Member
            §s.Status            # Running / Stopped (an enum)
            §s.StartType         # Automatic / Manual / Disabled
            §s.ServiceType       # Win32OwnProcess / Win32ShareProcess …
            §s.CanStop
            §s.BinaryPathName    # PS 7 only: exe + args
        """)
        PsOutput("""
            Status   Name     DisplayName
            ------   ----     -----------
            Running  Spooler  Print Spooler
        """)
        PsText("""
            Get-Service in 5.1 lacks some details (account, path, description). For those, use CIM (lesson 21):
              Get-CimInstance Win32_Service -Filter "Name='Spooler'" | Select-Object Name, StartName, PathName, ProcessId
            ProcessId links the service to its process — then Get-Process -Id gives memory and CPU.
        """)

        PsSection("CONTROLLING SERVICES")
        PsWarn("Starting, stopping or reconfiguring services changes system behavior and needs an ELEVATED PowerShell. Stopping critical services (e.g. RpcSs, Winmgmt, Dhcp) can break networking, management or the whole OS. The examples use the Print Spooler, which is safe to restart on most machines that are not print servers.")
        PsCode("""
            Stop-Service    -Name Spooler -WhatIf
            Stop-Service    -Name Spooler
            Start-Service   -Name Spooler
            Restart-Service -Name Spooler -Force      # -Force also stops dependents
            Suspend-Service / Resume-Service          # only if CanPauseAndContinue

            Set-Service -Name Spooler -StartupType Manual
            Set-Service -Name Spooler -StartupType Automatic

            # Wait for a state with a timeout, using the .NET method:
            §svc = Get-Service Spooler
            §svc.Stop()
            §svc.WaitForStatus('Stopped', [TimeSpan]::FromSeconds(30))
            §svc.Start()
        """)
        PsText("""
            Start-/Stop-Service wait until the service reaches the new state (printing "Waiting for service…" warnings if slow). The .NET methods .Stop()/.Start() return immediately — combine with .WaitForStatus(). New-Service and Remove-Service (PS 6+; sc.exe delete in 5.1) create or delete services.
        """)

        PsSection("THE WIDER WINDOWS SYSTEM")
        PsCode("""
            Get-ComputerInfo -Property OsName, OsVersion, CsTotalPhysicalMemory
            Get-HotFix | Sort-Object InstalledOn -Descending | Select-Object -First 5
            Get-WinEvent -LogName System -MaxEvents 10            # event log
            Get-WinEvent -FilterHashtable @{ LogName='System'; Level=2; StartTime=(Get-Date).AddDays(-1) }
            Get-ScheduledTask | Where-Object State -eq Ready | Select-Object -First 5
            Get-LocalUser
            Restart-Computer -WhatIf; Stop-Computer -WhatIf      # don't remove -WhatIf casually!
        """)
        PsText("Get-WinEvent -FilterHashtable filters in the event log service itself (fast). Level 2 = Error, 3 = Warning, 4 = Information.")

        PsSection("CAVEATS")
        PsText("""
            • Process names omit .exe: Get-Process notepad, not notepad.exe.
            • Many process properties need admin rights for other users' processes; Path/Modules may be §null or throw.
            • Service names (Spooler) ≠ display names (Print Spooler). Cmdlets use -Name by default.
            • Stop-Service on a service with dependents fails unless you add -Force.
            • In PowerShell 7, ps / kill are aliases on Windows only; on Linux they run the native tools.
        """)

        PsSection("CONNECTIONS")
        PsText("Get-Member and object properties come from lessons 3 and 16; -WhatIf/-Force are common parameters (lesson 3); error handling for access-denied comes from lesson 10. Lesson 21's Win32_Process and Win32_Service CIM classes provide even more detail.")

        PsExercise("""
            1. List the 10 processes with the most CPU time, showing Name, Id, CPU (rounded) and memory in MB.
            2. Start Notepad with Start-Process -PassThru, print its Id and StartTime, wait 3 seconds, then close it with CloseMainWindow().
            3. List all Automatic services that are NOT running.
        """)
        PsChallenge("""
            Write Get-ServiceProcessInfo that, for every RUNNING service, combines Win32_Service (Name, StartName, ProcessId) with Get-Process (WorkingSet64, StartTime) and outputs one object per service. Group the results by ProcessId to show which svchost.exe processes host more than one service. Sort by memory and export to CSV.
        """)
    }
}
