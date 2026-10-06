package com.example.windowsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PS03CmdletsPipelineScreen(navController: NavController) {
    PsLessonScreen(navController, "03 COMMANDS, CMDLETS AND THE PIPELINE") {

        PsSection("WHAT IS A CMDLET?")
        PsText("""
            A CMDLET ("command-let") is a small, single-purpose command compiled into a .NET class (usually C#) and loaded by PowerShell. Cmdlets are not separate .exe files: they run inside the PowerShell process, receive objects, and emit objects.

            Kinds of commands PowerShell can run:
            • Cmdlet — compiled .NET class (Get-Process, Get-ChildItem)
            • Function — written in PowerShell (lesson 7); advanced functions behave exactly like cmdlets (lesson 24)
            • Alias — a short alternative name (ls → Get-ChildItem)
            • Script — a .ps1 file
            • Application — any native executable (ping.exe, git.exe). These output TEXT, which PowerShell turns into a stream of strings.
        """)

        PsSection("VERB-NOUN NAMING")
        PsText("""
            Every cmdlet is named Verb-Noun. The verb comes from an APPROVED list (Get-Verb shows it), the noun is singular and describes the thing acted on:

              Get-Process     Stop-Process     Start-Process
              Get-Service     Start-Service    Restart-Service
              Get-ChildItem   Copy-Item        Remove-Item
              Get-Content     Set-Content      Add-Content

            Because the vocabulary is consistent, you can GUESS commands: if Get-Service exists, Stop-Service probably does too. Commands are case-insensitive: get-process works.
        """)
        PsCode("""
            Get-Verb | Where-Object Group -eq 'Lifecycle'
        """)
        PsOutput("""
            Verb      AliasPrefix Group     Description
            ----      ----------- -----     -----------
            Approve   ap          Lifecycle Confirms or agrees to the status of…
            Assert    as          Lifecycle Affirms the state of a resource
            Build     bd          Lifecycle Creates an artifact…
            Complete  cmp         Lifecycle Concludes an operation
            Confirm   cn          Lifecycle Acknowledges, verifies, or validates…
            …
        """)

        PsSection("THREE EVERYDAY CMDLETS")
        PsCode("""
            Get-Process                          # all processes
            Get-Process -Name notepad            # by name
            Get-Process -Id §PID                 # this PowerShell (§PID = own id)

            Get-Service                          # all services
            Get-Service -Name Spooler, W32Time   # several at once
            Get-Service | Where-Object Status -eq 'Running'

            Get-ChildItem C:\Windows             # directory listing
            Get-ChildItem C:\Windows -Filter *.log
            Get-ChildItem §env:TEMP -Recurse -File
        """)
        PsText("""
            Get-ChildItem works on any PowerShell PROVIDER drive, not only the file system: Get-ChildItem HKCU:\Software lists registry keys, Get-ChildItem Env: lists environment variables, Get-ChildItem Cert:\CurrentUser\My lists certificates (lesson 13 explains providers).
        """)

        PsSection("PARAMETERS")
        PsText("""
            • Named: -Name notepad
            • Positional: Get-Process notepad (Name is position 0)
            • Switch: -Recurse, -File (no value; present means true)
            • Multiple values: -Name Spooler, W32Time (a comma makes an array)
            • Common parameters — every cmdlet supports -Verbose, -Debug, -ErrorAction, -WarningAction, -OutVariable, -WhatIf / -Confirm (on cmdlets that change things)
        """)
        PsCode("""
            # -WhatIf: show what WOULD happen, change nothing
            Get-ChildItem §env:TEMP -Filter *.tmp | Remove-Item -WhatIf
        """)
        PsOutput("""
            What if: Performing the operation "Remove File" on target "C:\Users\gil\AppData\Local\Temp\a1b2.tmp".
            What if: Performing the operation "Remove File" on target "C:\Users\gil\AppData\Local\Temp\c3d4.tmp".
        """)
        PsText("-WhatIf is your best friend when learning: try it before ANY command that deletes or changes things.")

        PsSection("ALIASES")
        PsCode("""
            Get-Alias ls, dir, gci, ps, cd, cat, ?, %
            Get-Alias -Definition Get-ChildItem
        """)
        PsOutput("""
            CommandType Name                 Version Source
            ----------- ----                 ------- ------
            Alias       ls -> Get-ChildItem
            Alias       dir -> Get-ChildItem
            Alias       gci -> Get-ChildItem
            Alias       ps -> Get-Process
            Alias       cd -> Set-Location
            Alias       cat -> Get-Content
            Alias       ? -> Where-Object
            Alias       % -> ForEach-Object
        """)
        PsText("""
            Aliases exist so Unix and cmd.exe users can type familiar names. They are great at the prompt but should be AVOIDED in scripts: full names are self-documenting, and on Linux ls / ps / cat are NOT aliases in PowerShell 7 — they run the native Linux tools, which output text.
        """)

        PsSection("DISCOVERING COMMANDS: Get-Command")
        PsCode("""
            Get-Command -Noun Service            # everything acting on services
            Get-Command -Verb Get -Noun *Net*    # wildcards
            Get-Command *firewall*               # anything matching
            Get-Command -Module Microsoft.PowerShell.Management
            Get-Command ping                     # where is ping? (native exe)
        """)
        PsOutput("""
            CommandType Name             Version Source
            ----------- ----             ------- ------
            Cmdlet      Get-Service      7.0.0.0 Microsoft.PowerShell.Management
            Cmdlet      New-Service      7.0.0.0 Microsoft.PowerShell.Management
            Cmdlet      Restart-Service  7.0.0.0 Microsoft.PowerShell.Management
            Cmdlet      Start-Service    7.0.0.0 Microsoft.PowerShell.Management
            Cmdlet      Stop-Service     7.0.0.0 Microsoft.PowerShell.Management
            …
        """)

        PsSection("LEARNING A COMMAND: Get-Help")
        PsCode("""
            Update-Help -UICulture en-US        # download help files (run once, as admin in 5.1)
            Get-Help Get-Process                # summary + syntax
            Get-Help Get-Process -Examples      # just the examples
            Get-Help Get-Process -Parameter Name
            Get-Help Get-Process -Online        # open the web page
            Get-Help about_Pipelines            # conceptual "about_" topics
            Get-Process -?                      # same as Get-Help
        """)
        PsText("""
            The SYNTAX block reads like this:
              Get-Process [[-Name] <String[]>] [-Module] ...
            [ ] = optional, [-Name] in brackets = the name itself can be omitted (positional), <String[]> = accepts an array of strings.
        """)

        PsSection("THE PIPELINE PASSES OBJECTS")
        PsText("""
            The | operator connects commands. The left command's OUTPUT OBJECTS become the right command's INPUT, one object at a time, while the left command is still running (streaming).
        """)
        PsCode("""
            Get-Service | Get-Member
        """)
        PsOutput("""
               TypeName: System.ServiceProcess.ServiceController

            Name               MemberType Definition
            ----               ---------- ----------
            Start              Method     void Start(), void Start(string[] args)
            Stop               Method     void Stop()
            WaitForStatus      Method     void WaitForStatus(…)
            DisplayName        Property   string DisplayName {get;set;}
            Name               AliasProperty Name = ServiceName
            StartType          Property   ServiceStartMode StartType {get;}
            Status             Property   ServiceControllerStatus Status {get;}
            …
        """)
        PsText("""
            Get-Member proves the point: what flows through the pipe is a System.ServiceProcess.ServiceController .NET object with properties (Status, StartType) and methods (Start(), Stop()). Not a line of text.
        """)
        PsCode("""
            # Filter on a property, sort on another, pick columns:
            Get-Service |
                Where-Object { §_.Status -eq 'Stopped' -and §_.StartType -eq 'Automatic' } |
                Sort-Object DisplayName |
                Select-Object Name, DisplayName, Status
        """)
        PsText("""
            • Where-Object keeps only objects for which the script block is true. §_ (also §PSItem) is the CURRENT pipeline object.
            • Status is an ENUM, not a string, but PowerShell converts 'Stopped' automatically for the comparison.
            • Nothing is parsed. If Microsoft changed the on-screen table layout, this pipeline would still work.
        """)
        PsCode("""
            # Pipeline binding: Stop-Process accepts Process objects from the pipe
            Get-Process -Name notepad | Stop-Process -WhatIf

            # A string can bind too — "ByPropertyName" / "ByValue" binding
            'Spooler', 'W32Time' | Get-Service
        """)
        PsText("""
            PARAMETER BINDING: when an object arrives, PowerShell looks for a parameter that accepts it "ByValue" (the whole object has the right type — Process → Stop-Process -InputObject) or "ByPropertyName" (the object has a property with the same name as the parameter). Get-Help Stop-Process -Parameter Name shows "Accept pipeline input? True (ByPropertyName)".
        """)

        PsSection("FORMATTING HAPPENS LAST")
        PsText("""
            The table you see is drawn by Out-Default at the end of the pipeline. Format-Table and Format-List produce FORMATTING objects, not data — so always put them LAST.
        """)
        PsCode("""
            Get-Process | Format-Table Name, Id -AutoSize    # OK, last
            Get-Process | Format-List *                       # every property
            Get-Process | Format-Table | Sort-Object Name     # WRONG — sorts format objects
        """)

        PsSection("CAVEATS")
        PsText("""
            • Native commands (ipconfig, netstat, git) output STRINGS. You can still pipe them (ipconfig | Select-String IPv4) but you are back to text processing.
            • Get-Service in 5.1 and 7 differ slightly: 7 adds properties like BinaryPathName, UserName, Description and StartupType on Windows.
            • Help is not installed by default; run Update-Help once (it needs internet).
        """)

        PsSection("CONNECTIONS")
        PsText("Lesson 2 used Sort-Object and Select-Object in a script — now you know how the objects flow. Lesson 16 goes deeper into Get-Member, Select-Object, Where-Object, Sort-Object and Group-Object.")

        PsExercise("""
            1. Use Get-Command to find every cmdlet whose noun contains "Item".
            2. Use Get-Help -Examples on one of them and run an example.
            3. Pipe Get-ChildItem C:\Windows into Get-Member. What is the TypeName of a file? Of a folder?
        """)
        PsChallenge("""
            Write ONE pipeline that lists the 5 largest .log files under C:\Windows (search recursively, ignore access-denied errors with -ErrorAction SilentlyContinue), showing FullName and size in MB rounded to 1 decimal. Hint: Get-ChildItem → Sort-Object Length → Select-Object with a calculated property, like the one in lesson 2.
        """)
    }
}
