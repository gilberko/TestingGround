package com.example.windowsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PS07FunctionsScreen(navController: NavController) {
    PsLessonScreen(navController, "07 FUNCTIONS") {

        PsSection("WHY FUNCTIONS?")
        PsText("""
            A FUNCTION is a named, reusable block of code. Instead of copying the same 15 lines into three places in a script — and later fixing a bug in only two of them — you write the logic once, give it a name, and call it.

            Benefits:
            • One place to fix bugs and make improvements.
            • Scripts read like a list of steps: Get-DiskReport; Send-Report.
            • Functions can be tested on their own at the prompt.
            • Functions become COMMANDS — they take parameters, accept pipeline input, and emit objects just like cmdlets, so they work with everything from lessons 3 and 6.
        """)

        PsSection("THE SIMPLEST FUNCTION")
        PsCode("""
            function Get-Greeting {
                'Hello from a function!'
            }

            Get-Greeting
        """)
        PsOutput("Hello from a function!")
        PsText("""
            function NAME { BODY }. Follow the Verb-Noun convention with an approved verb (Get-Verb) so your functions feel like real cmdlets. The function must be DEFINED before it is called — in a script, put functions at the top.
        """)

        PsSection("PARAMETERS")
        PsCode("""
            function Get-Greeting {
                param(
                    [string]§Name = 'World',
                    [int]§Times = 1
                )
                for (§i = 0; §i -lt §Times; §i++) {
                    "Hello, §Name!"
                }
            }

            Get-Greeting                       # Hello, World!
            Get-Greeting -Name Ada             # Hello, Ada!
            Get-Greeting -Name Ada -Times 2    # twice
            Get-Greeting Ada 2                 # positional, same thing
        """)
        PsText("""
            The param( ) block declares parameters, each with an optional type and default value — exactly like a script's param block in lesson 2. Callers use -Name, positional order, or abbreviations (-N Ada).

            IMPORTANT: calling a function is a COMMAND, not a method call. Use spaces, not parentheses and commas:
              Get-Greeting Ada 2        ✓
              Get-Greeting('Ada', 2)    ✗  passes ONE array argument ('Ada',2) to -Name!
        """)
        PsCode("""
            # Switch parameter
            function Get-Temp {
                param([switch]§Recurse)
                Get-ChildItem §env:TEMP -Recurse:§Recurse
            }
            Get-Temp -Recurse
        """)

        PsSection("RETURNING RESULTS")
        PsText("""
            This is the most surprising part of PowerShell functions: a function returns EVERYTHING its statements output, not only what you pass to return. Every value that isn't assigned, piped away, or cast to [void] becomes part of the result.
        """)
        PsCode("""
            function Add-Numbers {
                param([int]§a, [int]§b)
                §a + §b                # this IS the return value
            }
            §sum = Add-Numbers 2 3     # 5

            function Get-Trouble {
                §list = [System.Collections.ArrayList]::new()
                §list.Add('x')         # ArrayList.Add returns the new index (0)!
                return 'done'
            }
            Get-Trouble                # outputs 0 AND 'done' — an array!

            function Get-Fixed {
                §list = [System.Collections.ArrayList]::new()
                [void]§list.Add('x')   # discard (also: §null = ... or | Out-Null)
                return 'done'
            }
        """)
        PsText("""
            return VALUE outputs VALUE and leaves the function immediately; return alone just exits. Because ALL output is returned, avoid stray output: discard method results you don't need with [void], §null = …, or | Out-Null. Write-Host writes to the console only — it is NOT part of the return value (good for status messages, bad for data).
        """)

        PsSection("OBJECTS AS OUTPUT")
        PsCode("""
            function Get-DiskInfo {
                param([string]§Drive = 'C')
                §d = Get-PSDrive -Name §Drive
                [PSCustomObject]@{
                    Drive   = §Drive
                    UsedGB  = [math]::Round(§d.Used / 1GB, 1)
                    FreeGB  = [math]::Round(§d.Free / 1GB, 1)
                    PctFree = [math]::Round(100 * §d.Free / (§d.Used + §d.Free))
                }
            }

            Get-DiskInfo
            Get-DiskInfo D | Format-List
            Get-DiskInfo | Export-Csv disks.csv -NoTypeInformation
        """)
        PsOutput("""
            Drive UsedGB FreeGB PctFree
            ----- ------ ------ -------
            C      312.4  163.9      34
        """)
        PsText("""
            Return OBJECTS, not formatted strings. An object can be sorted, filtered, exported to CSV/JSON or turned into a table by the caller. A string like "C: 163.9 GB free" can only be printed.
        """)

        PsSection("OBJECTS AS INPUT AND PIPELINE INPUT")
        PsCode("""
            function Get-FileAge {
                param(
                    [Parameter(ValueFromPipeline)]
                    [System.IO.FileInfo]§File
                )
                process {
                    [PSCustomObject]@{
                        Name    = §File.Name
                        AgeDays = [int]((Get-Date) - §File.LastWriteTime).TotalDays
                    }
                }
            }

            Get-ChildItem §env:TEMP -File | Get-FileAge | Sort-Object AgeDays -Descending
        """)
        PsText("""
            • [Parameter(ValueFromPipeline)] lets objects arriving through | bind to §File.
            • The process { } block runs ONCE PER PIPELINE OBJECT. Without it, the body would run only once at the end and see only the LAST file.
            • The parameter is typed [System.IO.FileInfo], so the function receives full file objects and can use any property.
        """)

        PsSection("PARAMETER VALIDATION")
        PsCode("""
            function Set-Volume {
                param(
                    [Parameter(Mandatory)]
                    [ValidateRange(0, 100)]
                    [int]§Level,

                    [ValidateSet('Speakers', 'Headphones')]
                    [string]§Device = 'Speakers',

                    [ValidateNotNullOrEmpty()]
                    [string]§Reason = 'manual'
                )
                "Setting §Device to §Level% (§Reason)"
            }

            Set-Volume -Level 50
            Set-Volume -Level 150      # error before the body runs
            Set-Volume                 # Mandatory → PowerShell prompts for Level
        """)
        PsOutput("""
            Setting Speakers to 50% (manual)
            Set-Volume: Cannot validate argument on parameter 'Level'. The 150 argument is greater than the maximum allowed range of 100. …
        """)
        PsText("""
            Validation attributes reject bad input BEFORE your code runs, with clear error messages, so the body doesn't need lots of if checks. Common ones: Mandatory, ValidateRange, ValidateSet (also gives tab completion!), ValidatePattern (regex), ValidateLength, ValidateScript ({ Test-Path §_ }), ValidateNotNullOrEmpty.
        """)

        PsSection("A TASTE OF ADVANCED FUNCTIONS")
        PsCode("""
            function Get-LargeFile {
                [CmdletBinding()]
                param(
                    [string]§Path = '.',
                    [long]§MinSize = 100MB
                )
                Write-Verbose "Scanning §Path for files over §MinSize bytes"
                Get-ChildItem §Path -File -Recurse -ErrorAction SilentlyContinue |
                    Where-Object Length -gt §MinSize
            }

            Get-LargeFile C:\Users -Verbose
        """)
        PsText("""
            Adding [CmdletBinding()] turns a function into an ADVANCED FUNCTION: it gains all common parameters (-Verbose, -ErrorAction, -WhatIf support, …). Write-Verbose messages show only when the caller adds -Verbose. Lesson 24 covers advanced functions fully.
        """)

        PsSection("COMMENT-BASED HELP")
        PsCode("""
            function Get-DiskInfo {
                <#
                .SYNOPSIS
                    Shows used and free space for a drive.
                .PARAMETER Drive
                    Drive letter without colon. Default: C.
                .EXAMPLE
                    Get-DiskInfo -Drive D
                #>
                param([string]§Drive = 'C')
                # ...
            }

            Get-Help Get-DiskInfo -Full
        """)
        PsText("A specially formatted comment inside the function becomes real help that Get-Help displays — your functions document themselves the same way built-in cmdlets do.")

        PsSection("CAVEATS")
        PsText("""
            • Call with spaces, not (a, b) — the most common beginner bug.
            • Everything un-captured is output: method return values, intermediate expressions, even §list.Add().
            • A function defined in a script vanishes when the script ends unless the script is dot-sourced or the function is put in a module (lesson 8).
            • Function names can collide with cmdlets — a function named Get-Process would HIDE the real cmdlet in your session (functions win over cmdlets in command lookup).
        """)

        PsSection("CONNECTIONS")
        PsText("Parameters work the same as the script param() from lesson 2. The process {} block is the function version of ForEach-Object from lesson 6. Lesson 8 packages functions into modules; lesson 9 explains which variables a function can see.")

        PsExercise("""
            Write a function ConvertTo-Fahrenheit with a mandatory [double]§Celsius parameter that returns the converted value (F = C * 9 / 5 + 32). Then make it accept pipeline input so that  0, 37, 100 | ConvertTo-Fahrenheit  works.
        """)
        PsChallenge("""
            Write Get-ServiceReport with parameters -Status (ValidateSet Running/Stopped, default Running) and -StartType (ValidateSet Automatic/Manual/Disabled, optional). Output [PSCustomObject]s with Name, DisplayName, Status and StartType. Add comment-based help with two examples. Verify:  Get-ServiceReport -Status Stopped -StartType Automatic | Export-Csv report.csv
        """)
    }
}
