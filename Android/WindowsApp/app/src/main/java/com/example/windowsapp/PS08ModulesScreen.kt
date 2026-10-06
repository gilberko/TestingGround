package com.example.windowsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PS08ModulesScreen(navController: NavController) {
    PsLessonScreen(navController, "08 MODULES") {

        PsSection("WHAT IS A MODULE?")
        PsText("""
            A MODULE is a reusable PACKAGE of PowerShell functionality that can be loaded into a session as one unit. It can contain:
            • functions (written in PowerShell)
            • cmdlets (compiled .NET code in a DLL)
            • variables and aliases
            • classes and enums
            • formatting and type data, help files, and other resources

            Think of a module as PowerShell's equivalent of a Python package, a C# library/NuGet package, or a Linux shared library. Almost every command you have used so far comes from a module: Get-Process lives in Microsoft.PowerShell.Management, Get-NetIPAddress in NetTCPIP, Get-CimInstance in CimCmdlets.
        """)

        PsSection("WHY MODULES?")
        PsText("""
            In lesson 7 you wrote functions — but a function defined in a script disappears when the script ends, and copying function definitions between scripts is the same copy-paste problem functions were meant to solve.

            A module fixes this:
            • Write the functions ONCE in a module file.
            • Any script or session loads them with one line (or automatically).
            • The module decides what is PUBLIC (exported) and what stays PRIVATE helper code.
            • Modules have versions and can be shared via the PowerShell Gallery (PSGallery).
        """)

        PsSection("MODULE TYPES")
        PsCode("""
            Type         File(s)          Contains
            ─────────────────────────────────────────────────────────
            Script       .psm1            PowerShell functions/vars
            Binary       .dll             compiled cmdlets (C#)
            Manifest     .psd1 (+ others) metadata describing the module:
                                          version, author, exports,
                                          required modules, files
            Dynamic      (in memory)      New-Module — rarely used
        """)

        PsSection("FINDING AND USING MODULES")
        PsCode("""
            Get-Module                    # modules LOADED in this session
            Get-Module -ListAvailable     # modules INSTALLED on disk
            Get-Command -Module NetTCPIP  # what a module provides
            Import-Module NetTCPIP        # load explicitly
            Remove-Module NetTCPIP        # unload from the session
            §env:PSModulePath -split ';'  # where PowerShell looks
        """)
        PsOutput("""
            C:\Users\gil\Documents\PowerShell\Modules        (current user)
            C:\Program Files\PowerShell\Modules              (all users)
            c:\program files\powershell\7\Modules            (built-in, PS 7)
            C:\Program Files\WindowsPowerShell\Modules
            C:\WINDOWS\system32\WindowsPowerShell\v1.0\Modules
        """)
        PsText("""
            AUTO-LOADING (since PowerShell 3.0): if a module lives in a folder listed in §env:PSModulePath, you rarely need Import-Module. The first time you call one of its commands, PowerShell loads the module automatically. Import-Module is still needed for modules stored elsewhere (by path), or to load a specific version.
        """)

        PsSection("INSTALLING MODULES FROM THE GALLERY")
        PsWarn("Installing a module downloads and runs third-party code. Only install modules you trust (check the author, download count and source repository).")
        PsCode("""
            Find-Module -Name *Excel*                     # search PSGallery
            Install-Module ImportExcel -Scope CurrentUser # no admin needed
            Get-InstalledModule
            Update-Module ImportExcel
            Uninstall-Module ImportExcel

            # PowerShell 7.4+ also ships PSResourceGet:
            Install-PSResource ImportExcel
        """)

        PsSection("BUILDING A CUSTOM MODULE — STEP 1: THE .psm1")
        PsText("Imagine three helper functions you keep copying between scripts. Put them in one file named SysTools.psm1:")
        PsCode("""
            # SysTools.psm1

            function Get-DiskInfo {
                param([string]§Drive = 'C')
                §d = Get-PSDrive -Name §Drive
                [PSCustomObject]@{
                    Drive  = §Drive
                    FreeGB = [math]::Round(§d.Free / 1GB, 1)
                    UsedGB = [math]::Round(§d.Used / 1GB, 1)
                }
            }

            function Get-Uptime2 {
                §boot = (Get-CimInstance Win32_OperatingSystem).LastBootUpTime
                (Get-Date) - §boot
            }

            function Format-Bytes {          # helper — kept private below
                param([long]§Bytes)
                if (§Bytes -ge 1GB) { '{0:N1} GB' -f (§Bytes / 1GB) }
                elseif (§Bytes -ge 1MB) { '{0:N1} MB' -f (§Bytes / 1MB) }
                else { '{0:N0} KB' -f (§Bytes / 1KB) }
            }

            function Get-BigProcess {
                param([int]§Top = 5)
                Get-Process | Sort-Object WorkingSet64 -Descending |
                    Select-Object -First §Top Name, Id,
                        @{ N = 'Memory'; E = { Format-Bytes §_.WorkingSet64 } }
            }

            Export-ModuleMember -Function Get-DiskInfo, Get-Uptime2, Get-BigProcess
        """)
        PsText("""
            • A .psm1 is just a script with module semantics: its functions live in the MODULE'S scope.
            • Export-ModuleMember lists what callers can see. Format-Bytes is NOT exported, so callers can't call it — but Get-BigProcess, which lives inside the module, can. Without Export-ModuleMember, all functions are exported by default.
            • Get-Uptime2 is named so it doesn't clash with the built-in Get-Uptime cmdlet of PowerShell 7.
        """)

        PsSection("STEP 2: THE FOLDER LAYOUT")
        PsCode("""
            Documents\PowerShell\Modules\
            └── SysTools\                 ← folder name = module name
                ├── SysTools.psm1
                └── SysTools.psd1         ← manifest (optional but recommended)
        """)
        PsText("""
            The folder name must match the module file name. Put it under a path in §env:PSModulePath and the module auto-loads. Use Documents\WindowsPowerShell\Modules for 5.1.
        """)

        PsSection("STEP 3: THE MANIFEST (.psd1)")
        PsCode("""
            New-ModuleManifest -Path .\SysTools\SysTools.psd1 `
                -RootModule 'SysTools.psm1' `
                -ModuleVersion '1.0.0' `
                -Author 'Gil' `
                -Description 'Small system helpers' `
                -FunctionsToExport 'Get-DiskInfo', 'Get-Uptime2', 'Get-BigProcess' `
                -PowerShellVersion '5.1'
        """)
        PsText("""
            The manifest is a hash table of metadata stored in a .psd1 data file. RootModule points to the code. FunctionsToExport lists public functions explicitly — this also makes auto-loading FASTER because PowerShell can see the command names without running the .psm1. Other fields: RequiredModules, CompatiblePSEditions, NestedModules, FileList, PrivateData (tags, project URI for the Gallery).

            The backtick ` at the end of each line is the LINE CONTINUATION character; it must be the very last character on the line (no trailing spaces).
        """)

        PsSection("STEP 4: USE IT")
        PsCode("""
            Import-Module SysTools            # or just call a function: auto-load
            Get-Command -Module SysTools
            Get-DiskInfo
            Get-BigProcess -Top 3
            Format-Bytes 123456               # ERROR: not recognized (private)

            Import-Module .\SysTools -Force   # reload after editing the code
        """)
        PsOutput("""
            CommandType Name            Version Source
            ----------- ----            ------- ------
            Function    Get-BigProcess  1.0.0   SysTools
            Function    Get-DiskInfo    1.0.0   SysTools
            Function    Get-Uptime2     1.0.0   SysTools
        """)

        PsSection("CAVEATS")
        PsText("""
            • Import-Module of an already-loaded module does nothing; use -Force to reload changes during development.
            • Classes defined in a module are NOT exported by Import-Module — callers need  using module SysTools  at the top of their script to see them (lesson 18).
            • 5.1 and 7 have separate user module folders (WindowsPowerShell vs PowerShell). Some Windows-only modules don't load natively in 7; 7 can load them via the Windows PowerShell compatibility layer (Import-Module -UseWindowsPowerShell).
            • Module execution is subject to the execution policy, just like scripts.
        """)

        PsSection("CONNECTIONS")
        PsText("Modules package the functions of lesson 7. Lesson 9 explains the module's own scope (why Format-Bytes stays hidden and how module-level §script: variables persist between calls).")

        PsExercise("""
            Create the SysTools module from this lesson in your user Modules folder. Open a NEW PowerShell window and run Get-DiskInfo without Import-Module. Confirm it auto-loads with Get-Module. Verify Format-Bytes is not callable.
        """)
        PsChallenge("""
            Add a manifest with New-ModuleManifest, bump the version to 1.1.0, and add a new exported function Get-ServiceSummary that returns one object with counts of Running, Stopped and Disabled services. Use Test-ModuleManifest to validate the manifest. Then check  Get-Module SysTools -ListAvailable  shows 1.1.0.
        """)
    }
}
