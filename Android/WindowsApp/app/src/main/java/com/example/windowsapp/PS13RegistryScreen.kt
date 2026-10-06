package com.example.windowsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PS13RegistryScreen(navController: NavController) {
    PsLessonScreen(navController, "13 THE REGISTRY") {

        PsSection("THE REGISTRY IN ONE MINUTE")
        PsText("""
            The Windows Registry is a hierarchical database of configuration: KEYS (like folders) contain VALUES (name + type + data) and sub-keys. The app's Advanced Topics REGISTRY screen covers its internals (hives, control sets). Here we focus on scripting it.

            Two concepts to keep separate:
            • KEY — a container, e.g. HKCU\Software\Microsoft\Notepad
            • VALUE — a named piece of data INSIDE a key, e.g. fWrap = 1 (REG_DWORD)
        """)

        PsSection("POWERSHELL PROVIDERS")
        PsText("""
            A PROVIDER is an adapter that makes a data store look like a FILE SYSTEM DRIVE. The same cmdlets (Get-ChildItem, Get-Item, New-Item, Remove-Item, Set-Location) then work on all of them.
        """)
        PsCode("""
            Get-PSProvider
            Get-PSDrive -PSProvider Registry
        """)
        PsOutput("""
            Name         Capabilities                     Drives
            ----         ------------                     ------
            Registry     ShouldProcess                    {HKLM, HKCU}
            Alias        ShouldProcess                    {Alias}
            Environment  ShouldProcess                    {Env}
            FileSystem   Filter, ShouldProcess, Credentials {C, D, Temp}
            Function     ShouldProcess                    {Function}
            Variable     ShouldProcess                    {Variable}
            Certificate  ShouldProcess                    {Cert}
        """)
        PsText("""
            Only two registry drives exist by default: HKLM: (HKEY_LOCAL_MACHINE) and HKCU: (HKEY_CURRENT_USER). Other roots are reachable through the provider path syntax:
              Get-ChildItem Registry::HKEY_USERS
              Get-ChildItem Registry::HKEY_CLASSES_ROOT
            or by creating a drive:  New-PSDrive -Name HKCR -PSProvider Registry -Root HKEY_CLASSES_ROOT
        """)

        PsSection("NAVIGATING AND READING KEYS")
        PsCode("""
            Set-Location HKLM:\SOFTWARE\Microsoft\Windows\CurrentVersion
            Get-ChildItem                       # SUB-KEYS (not values!)
            cd HKCU:\Software; dir              # aliases work too
            Set-Location C:\                    # back to the file system
        """)
        PsText("""
            The key point that confuses everyone: Get-ChildItem on a registry key lists its SUB-KEYS. Registry VALUES are not child items — they are PROPERTIES of the key item. So you read values with Get-ItemProperty.
        """)

        PsSection("READING VALUES")
        PsCode("""
            §key = 'HKLM:\SOFTWARE\Microsoft\Windows NT\CurrentVersion'

            Get-ItemProperty -Path §key                       # all values
            Get-ItemProperty -Path §key -Name ProductName     # one value (object)
            Get-ItemPropertyValue -Path §key -Name ProductName, DisplayVersion, CurrentBuild
            (Get-ItemProperty §key).EditionID                 # dotted access
            (Get-Item §key).GetValueKind('CurrentBuild')      # data type
        """)
        PsOutput("""
            Windows 10 Pro
            24H2
            26100
            Professional
            String
        """)
        PsText("""
            Get-ItemPropertyValue returns just the data. Get-Item returns a Microsoft.Win32.RegistryKey object, so you can call .NET methods such as .GetValueNames(), .GetValue() and .GetValueKind().

            CAVEAT: ProductName says "Windows 10" even on Windows 11 — Microsoft never updated that value. Use CurrentBuild (22000+ = Windows 11) or (Get-CimInstance Win32_OperatingSystem).Caption.
        """)
        PsCode("""
            # Practical: list installed programs (64-bit + 32-bit + per-user)
            §paths = 'HKLM:\SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\*',
                     'HKLM:\SOFTWARE\WOW6432Node\Microsoft\Windows\CurrentVersion\Uninstall\*',
                     'HKCU:\Software\Microsoft\Windows\CurrentVersion\Uninstall\*'
            Get-ItemProperty §paths -ErrorAction SilentlyContinue |
                Where-Object DisplayName |
                Select-Object DisplayName, DisplayVersion, Publisher |
                Sort-Object DisplayName
        """)
        PsText("The * wildcard expands to every sub-key, and Get-ItemProperty reads all their values at once. WOW6432Node is where 32-bit programs' settings are redirected on 64-bit Windows.")

        PsSection("CREATING KEYS AND VALUES")
        PsWarn("The following examples MODIFY THE REGISTRY. They only touch a demo key under HKCU:\\Software\\PSCourseDemo (your own user hive, no admin needed). Never experiment under HKLM:\\SYSTEM or other system keys — a wrong value there can stop Windows from booting. Consider exporting a backup first: reg export HKCU\\Software backup.reg")
        PsCode("""
            §demo = 'HKCU:\Software\PSCourseDemo'

            if (-not (Test-Path §demo)) {
                New-Item -Path §demo | Out-Null             # create key
            }
            New-Item -Path "§demo\Sub1" | Out-Null          # sub-key

            New-ItemProperty -Path §demo -Name Greeting -Value 'Hello' -PropertyType String
            New-ItemProperty -Path §demo -Name Counter  -Value 1       -PropertyType DWord
            New-ItemProperty -Path §demo -Name Paths    -Value 'C:\A','C:\B' -PropertyType MultiString
            New-ItemProperty -Path §demo -Name Data     -Value ([byte[]](1,2,3)) -PropertyType Binary
            New-ItemProperty -Path §demo -Name Home     -Value '%USERPROFILE%\x' -PropertyType ExpandString

            Get-ItemProperty §demo
        """)
        PsText("""
            -PropertyType maps to the registry types: String (REG_SZ), ExpandString (REG_EXPAND_SZ — expands %VARS% on read), DWord (REG_DWORD, 32-bit), QWord (REG_QWORD, 64-bit), MultiString (REG_MULTI_SZ, array of strings), Binary (REG_BINARY).

            Why the Test-Path guard? In the registry provider, New-Item -Force on a key that ALREADY exists can delete and recreate it, wiping its values and sub-keys (unlike -Force on a file-system folder). Guarding with Test-Path avoids that trap.
        """)

        PsSection("CHANGING VALUES")
        PsWarn("Modifies registry values (demo key only).")
        PsCode("""
            Set-ItemProperty -Path §demo -Name Greeting -Value 'Hi there'
            Set-ItemProperty -Path §demo -Name Counter  -Value 42
            Rename-ItemProperty -Path §demo -Name Counter -NewName Count

            # Set-ItemProperty creates the value if it doesn't exist (as String
            # unless the key already has a type) — use -Type to be explicit:
            Set-ItemProperty -Path §demo -Name Enabled -Value 1 -Type DWord
        """)

        PsSection("DELETING VALUES AND KEYS")
        PsWarn("Deletion is permanent — there is no undo or Recycle Bin for the registry. Double-check the path; use -WhatIf first.")
        PsCode("""
            Remove-ItemProperty -Path §demo -Name Data          # delete a VALUE
            Remove-Item -Path "§demo\Sub1"                      # delete a KEY
            Remove-Item -Path §demo -Recurse -WhatIf            # preview
            Remove-Item -Path §demo -Recurse                    # delete key + all sub-keys
        """)
        PsText("Remove-ItemProperty deletes a value; Remove-Item deletes a key. -Recurse is required when the key has sub-keys.")

        PsSection("SAFE PATTERN: TEST, BACKUP, CHANGE, VERIFY")
        PsCode("""
            function Set-RegistryValueSafe {
                [CmdletBinding(SupportsShouldProcess)]
                param(
                    [Parameter(Mandatory)][string]§Path,
                    [Parameter(Mandatory)][string]§Name,
                    [Parameter(Mandatory)]§Value,
                    [Microsoft.Win32.RegistryValueKind]§Type = 'String'
                )
                if (-not (Test-Path §Path)) {
                    if (§PSCmdlet.ShouldProcess(§Path, 'Create key')) {
                        New-Item -Path §Path | Out-Null
                    }
                }
                §old = Get-ItemProperty -Path §Path -Name §Name -ErrorAction SilentlyContinue
                if (§old) { Write-Verbose "Old value: §(§old.§Name)" }

                if (§PSCmdlet.ShouldProcess("§Path\§Name", "Set to '§Value'")) {
                    Set-ItemProperty -Path §Path -Name §Name -Value §Value -Type §Type
                    Get-ItemPropertyValue -Path §Path -Name §Name     # verify
                }
            }

            Set-RegistryValueSafe HKCU:\Software\PSCourseDemo Mode 'test' -WhatIf -Verbose
        """)
        PsText("SupportsShouldProcess gives the function -WhatIf and -Confirm for free (lesson 24). The old value is logged so it can be restored, and the new value is read back to verify.")

        PsSection("REMOTE AND OFFLINE REGISTRY")
        PsCode("""
            # Another computer (needs PowerShell remoting):
            Invoke-Command -ComputerName srv01 {
                Get-ItemPropertyValue 'HKLM:\SOFTWARE\Microsoft\Windows NT\CurrentVersion' CurrentBuild
            }

            # .NET API, works over the Remote Registry service:
            §base = [Microsoft.Win32.RegistryKey]::OpenRemoteBaseKey('LocalMachine', 'srv01')
            §base.OpenSubKey('SOFTWARE\Microsoft\Windows NT\CurrentVersion').GetValue('CurrentBuild')
        """)

        PsSection("CAVEATS")
        PsText("""
            • Writing to HKLM: needs an elevated (Run as administrator) PowerShell; HKCU: doesn't.
            • A 32-bit PowerShell sees HKLM:\SOFTWARE redirected to WOW6432Node — use 64-bit PowerShell for system work.
            • Keys used by security software and services may be protected; expect UnauthorizedAccessException (catch it, lesson 10).
            • Read the BEWARE THE REGISTRY screen in Advanced Topics: keys like Run, IFEO and Winlogon are classic persistence points — changing them affects every logon.
        """)

        PsSection("CONNECTIONS")
        PsText("The registry uses the same *-Item cmdlets as files (lesson 12) thanks to providers. Error handling (lesson 10) catches missing keys and values. Lesson 24 covers SupportsShouldProcess in depth.")

        PsExercise("""
            Read and print: Windows build number (CurrentBuild), the registered owner (RegisteredOwner), and the install date (InstallDate — a Unix timestamp; convert with [DateTimeOffset]::FromUnixTimeSeconds(§v).LocalDateTime) from HKLM:\SOFTWARE\Microsoft\Windows NT\CurrentVersion.
        """)
        PsChallenge("""
            Write a script that saves the contents of HKCU:\Software\PSCourseDemo (all values with their name, kind and data) to a JSON file, deletes the key, then RESTORES it from the JSON with the correct value types. Verify the restored key matches the original. Use GetValueNames() / GetValueKind() on the RegistryKey object.
        """)
    }
}
