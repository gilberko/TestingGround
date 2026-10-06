package com.example.windowsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PS01AboutScreen(navController: NavController) {
    PsLessonScreen(navController, "01 ABOUT POWERSHELL") {

        PsSection("WHAT IS POWERSHELL?")
        PsText("""
            PowerShell is three things at once:

            1. An interactive command-line SHELL — you type commands and see results, like cmd.exe or Bash.
            2. A full SCRIPTING / PROGRAMMING LANGUAGE — variables, functions, loops, classes, exceptions, modules.
            3. An AUTOMATION FRAMEWORK built on .NET — every value is a .NET object, and any .NET library can be called directly.

            Its defining idea: commands exchange OBJECTS, not text. That single design decision shapes everything else in this course.
        """)

        PsSection("HISTORY")
        PsText("""
            Before PowerShell, Windows administration was split between cmd.exe batch files (very limited), VBScript / JScript via Windows Script Host (powerful but verbose, COM-based), and GUI tools. Unix administrators had shells and text tools; Windows admins mostly had mouse clicks.

            2002 — Jeffrey Snover publishes the "Monad Manifesto", describing a shell whose pipeline carries structured .NET objects instead of text. The project is code-named Monad (MSH).

            2006 — Windows PowerShell 1.0 ships (November 2006) for XP / Server 2003 / Vista.
            2009 — 2.0 ships with Windows 7: remoting (WinRM), modules, advanced functions, background jobs.
            2012 — 3.0 (Windows 8): CIM cmdlets, auto-loading modules, workflows.
            2013 — 4.0: Desired State Configuration (DSC).
            2016 — 5.0 / 5.1: classes and enums, PowerShellGet / PSGallery. 5.1 is the FINAL version of "Windows PowerShell" and is still built into every Windows 10/11 and Server install.

            2016 — PowerShell is open-sourced and goes cross-platform (Windows, Linux, macOS) on .NET Core. PowerShell Core 6.0 GA: January 2018.
            2020 — PowerShell 7.0 (the "Core" label is dropped), built on .NET Core 3.1.
            Since then: 7.2 LTS (.NET 6), 7.4 LTS (.NET 8), 7.6 LTS (.NET 10, March 2026). Even-numbered LTS releases track .NET LTS releases.
        """)

        PsSection("WHEN IS POWERSHELL USED?")
        PsText("""
            • System administration — users, services, processes, registry, event logs, scheduled tasks.
            • Bulk / repeatable work — "do this to 500 machines" instead of clicking 500 times.
            • Cloud & infrastructure — Azure (Az module), Microsoft 365, Exchange Online, AWS Tools, VMware PowerCLI.
            • DevOps — build scripts, CI/CD pipelines (GitHub Actions and Azure Pipelines both have pwsh steps).
            • Diagnostics & security — collecting system information, incident response, auditing configuration.
            • Glue code — calling REST APIs, parsing JSON/CSV/XML, combining tools.
        """)

        PsSection("OBJECTS, NOT TEXT — THE CORE IDEA")
        PsText("""
            In a Unix shell, every command writes a stream of TEXT. The next command must re-parse that text — cut columns, grep lines, hope the format never changes.

            In PowerShell, a command writes .NET OBJECTS into the pipeline. The next command receives real objects with typed properties and methods. Nothing has to be parsed.
        """)
        PsCode("""
            # Bash: find processes using > 100 MB (text parsing)
            ps aux | awk '§6 > 102400 { print §11 }'

            # PowerShell: same idea, using object properties
            Get-Process | Where-Object WorkingSet64 -gt 100MB |
                Select-Object Name, Id, WorkingSet64
        """)
        PsText("""
            The Bash version depends on column 6 being RSS in kilobytes and column 11 being the command — if the output layout changes, the script silently breaks.

            The PowerShell version asks for a property by NAME (WorkingSet64). The value is already a 64-bit integer, so -gt 100MB is a numeric comparison (100MB is a built-in numeric suffix = 104857600). The table you see on screen is only drawn at the very END of the pipeline by the formatting system.
        """)
        PsOutput("""
            Name       Id WorkingSet64
            ----       -- ------------
            chrome   8812    312459264
            Code    12044    254803968
            MsMpEng  4120    188416000
        """)

        PsSection("COMPARING WITH OTHER LANGUAGES")
        PsCode("""
            Aspect          Bash           Python         PowerShell
            ──────────────────────────────────────────────────────────
            Pipeline data   text bytes     (no pipeline)  .NET objects
            Typing          strings        dynamic        dynamic + optional
                                                          [type] annotations
            Runtime         native exes    CPython        .NET
            Windows APIs    poor           via modules    first-class: WMI,
                                                          COM, registry, .NET
            Interactive     excellent      REPL only      excellent
            Naming          short (ls)     library names  Verb-Noun
            Error model     exit codes     exceptions     exceptions + non-
                                                          terminating errors
        """)
        PsText("""
            • vs cmd.exe batch — batch has no real data types, functions or error handling. PowerShell replaces it entirely, though it can still run any .exe.
            • vs Bash — Bash is excellent at gluing Unix text tools together. PowerShell is better when the data is structured (processes, services, JSON, AD users) because no parsing is needed. On Linux, PowerShell can run alongside Bash and call the same tools.
            • vs Python — Python is a general-purpose language with a huge ecosystem. PowerShell is a shell first: one-liners at the prompt are natural, and Windows management (registry, WMI, services, AD, Exchange) is built in.
            • vs VBScript — PowerShell is its spiritual successor. VBScript is deprecated and being removed from Windows as an optional feature.
        """)

        PsSection("WINDOWS POWERSHELL 5.1 vs POWERSHELL 7+")
        PsCode("""
                           Windows PowerShell 5.1   PowerShell 7+
            ─────────────────────────────────────────────────────────
            Executable     powershell.exe           pwsh.exe
            Runtime        .NET Framework 4.x       modern .NET (8/10)
            Platforms      Windows only             Windows/Linux/macOS
            Shipped        built into Windows       separate install
                                                    (winget, MSI, Store)
            Development    maintenance only         active, open source
            Side by side   yes — both can be installed at the same time
            Profile path   Documents\WindowsPowerShell
                                                    Documents\PowerShell
            Default file   mixed (often UTF-16 or   UTF-8 without BOM
            encoding       ANSI)
            WMI cmdlets    Get-WmiObject + CIM      CIM only
        """)
        PsText("""
            New in 7.x (selected): ternary operator  a ? b : c, null-coalescing  ??  and  ??= , pipeline chain operators  &&  and  || , ForEach-Object -Parallel, Get-Error, better error display, faster engine.

            Rule of thumb: write new scripts for PowerShell 7, but remember that 5.1 is the only version guaranteed to exist on a fresh Windows machine. Some older Windows modules only load in 5.1 (7 has a Windows-compatibility layer that proxies many of them).
        """)
        PsCode("""
            # Which version am I running?
            §PSVersionTable.PSVersion
            §PSVersionTable.PSEdition    # 'Desktop' = 5.1, 'Core' = 6/7+
        """)
        PsOutput("""
            Major  Minor  Patch  PreReleaseLabel BuildLabel
            -----  -----  -----  --------------- ----------
            7      6      0

            Core
        """)

        PsSection("CAVEATS")
        PsText("""
            • "PowerShell" on Windows can mean either powershell.exe (5.1) or pwsh.exe (7). Always check §PSVersionTable before assuming a feature exists.
            • Examples in this course target PowerShell 7 on Windows; differences from 5.1 are called out where they matter.
            • PowerShell runs on Linux and macOS, but Windows-specific features (registry, WMI/CIM, COM, services) are only available on Windows.
        """)

        PsSection("CONNECTIONS")
        PsText("""
            This course builds on other parts of the app: lesson 20 (COM) uses what the User Mode COM screen describes, lesson 21 (WMI/CIM) expands the Advanced Topics RPC AND WMI screen, and lesson 13 (Registry) applies the REGISTRY screen to scripting.
        """)

        PsExercise("""
            Open BOTH powershell.exe and pwsh.exe (install PowerShell 7 with: winget install Microsoft.PowerShell). In each, run:
              §PSVersionTable
            Note the PSVersion, PSEdition and the CLR / .NET version. Which differences do you see?
        """)
        PsChallenge("""
            Run  Get-Process | Get-Member  and count how many properties a process object has. Then run  tasklist  (a classic text tool) and list three pieces of information you can get from the object that tasklist does not show.
        """)
    }
}
