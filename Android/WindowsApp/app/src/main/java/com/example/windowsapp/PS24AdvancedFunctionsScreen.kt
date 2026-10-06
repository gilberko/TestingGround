package com.example.windowsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PS24AdvancedFunctionsScreen(navController: NavController) {
    PsLessonScreen(navController, "24 ADVANCED FUNCTIONS AND PIPELINE PROGRAMMING") {

        PsSection("GOAL: FUNCTIONS THAT BEHAVE LIKE CMDLETS")
        PsText("""
            A built-in cmdlet like Get-Process gives you: named and positional parameters, validation, tab completion, pipeline input, streaming output, -Verbose/-ErrorAction/-WhatIf support, and help. An ADVANCED FUNCTION is a function written in PowerShell that gets ALL of that — users can't tell it apart from a compiled cmdlet.

            Building your tools this way is what makes them COMPOSE: they slot into pipelines with Get-ChildItem, Where-Object, Export-Csv and each other.
        """)

        PsSection("[CmdletBinding()] AND COMMON PARAMETERS")
        PsCode("""
            function Get-Something {
                [CmdletBinding()]
                param()
                Write-Verbose 'Verbose detail'
                Write-Debug   'Debug detail'
                Write-Warning 'A warning'
                'output'
            }

            Get-Something -Verbose
            Get-Something -WarningAction SilentlyContinue
            Get-Something -OutVariable result
        """)
        PsText("""
            [CmdletBinding()] — placed right before param() — makes the function "advanced". It gains the COMMON PARAMETERS: -Verbose, -Debug, -ErrorAction, -ErrorVariable, -WarningAction, -InformationAction, -OutVariable, -OutBuffer, -PipelineVariable. It also gains §PSCmdlet (an object giving access to cmdlet features) and rejects unknown parameters instead of silently putting them in §args.

            Write-Verbose / Write-Debug / Write-Warning / Write-Information write to SEPARATE STREAMS, not the output stream — so diagnostic messages never pollute the data your function returns.
        """)

        PsSection("THE [Parameter()] ATTRIBUTE")
        PsCode("""
            function Get-UserReport {
                [CmdletBinding(DefaultParameterSetName = 'ByName')]
                param(
                    [Parameter(Mandatory, Position = 0, ParameterSetName = 'ByName',
                               ValueFromPipeline, ValueFromPipelineByPropertyName,
                               HelpMessage = 'One or more user names')]
                    [Alias('User', 'SamAccountName')]
                    [ValidateNotNullOrEmpty()]
                    [string[]]§Name,

                    [Parameter(Mandatory, ParameterSetName = 'All')]
                    [switch]§All,

                    [ValidateRange(1, 365)]
                    [int]§Days = 30
                )
                # ...
            }
        """)
        PsText("""
            Parameter attribute arguments:
            • Mandatory — prompt if missing.
            • Position — allow positional use.
            • ValueFromPipeline — bind WHOLE pipeline objects of a compatible type.
            • ValueFromPipelineByPropertyName — bind from a property of the incoming object with the SAME name (or an [Alias]).
            • ParameterSetName — define mutually exclusive ways to call the function (like Get-Process -Name vs -Id). §PSCmdlet.ParameterSetName tells you which was used.
            • HelpMessage — text shown at the mandatory prompt when the user types !?
            [Alias()] adds alternative parameter names, which also enables by-property-name binding from objects that use those names.
        """)

        PsSection("BEGIN, PROCESS, END")
        PsCode("""
            function Measure-Text {
                [CmdletBinding()]
                param(
                    [Parameter(Mandatory, ValueFromPipeline)]
                    [string]§Line
                )
                begin {
                    Write-Verbose 'Starting'
                    §lines = 0; §words = 0
                }
                process {
                    §lines++
                    §words += (§Line -split '\s+' | Where-Object { §_ }).Count
                }
                end {
                    [PSCustomObject]@{ Lines = §lines; Words = §words }
                }
            }

            Get-Content C:\Windows\System32\drivers\etc\hosts | Measure-Text -Verbose
            Measure-Text -Line 'one two three'
        """)
        PsText("""
            • begin — runs ONCE before any pipeline input. Initialize counters, open connections.
            • process — runs ONCE PER INPUT OBJECT. The pipeline-bound parameter (§Line) holds the current object.
            • end — runs ONCE after all input. Summaries, cleanup, final output.
            • (PS 7.3+) clean — runs always, even after errors or Ctrl+C: guaranteed cleanup.

            STREAMING: objects emitted in process flow to the next command immediately, so a long pipeline starts producing results before all input is read — exactly like built-in cmdlets.

            When called WITHOUT the pipeline (Measure-Text -Line 'a b'), process runs once with the parameter value. If the parameter is an ARRAY ([string[]]) and passed directly, process runs once with the whole array — so loop inside process:  foreach (§n in §Name) { ... }  to handle both cases.
        """)

        PsSection("BY-PROPERTY-NAME BINDING IN ACTION")
        PsCode("""
            function Get-FileHashShort {
                [CmdletBinding()]
                param(
                    [Parameter(Mandatory, ValueFromPipelineByPropertyName)]
                    [Alias('FullName')]
                    [string]§Path
                )
                process {
                    §h = Get-FileHash -LiteralPath §Path -Algorithm SHA256
                    [PSCustomObject]@{
                        Name  = Split-Path §Path -Leaf
                        Short = §h.Hash.Substring(0, 12)
                    }
                }
            }

            Get-ChildItem C:\Windows\*.exe | Get-FileHashShort
            [PSCustomObject]@{ Path = 'C:\Windows\notepad.exe' } | Get-FileHashShort
            Import-Csv files.csv | Get-FileHashShort          # CSV with a Path column
        """)
        PsText("FileInfo objects have a FullName property; the [Alias('FullName')] makes it bind to §Path automatically. Any object with Path or FullName works — files, CSV rows, custom objects. That's the composability goal.")

        PsSection("VALIDATION AND ARGUMENT COMPLETION")
        PsCode("""
            param(
                [ValidateSet('Low', 'Medium', 'High')]        [string]§Priority,
                [ValidatePattern('^[A-Z]{3}-\d{4}§')]          [string]§TicketId,
                [ValidateScript({ Test-Path §_ -PathType Container }, ErrorMessage = 'Folder {0} not found')]
                                                              [string]§Folder,
                [ValidateCount(1, 5)]                         [string[]]§Tags,
                [ValidateLength(3, 20)]                       [string]§Owner,
                [ArgumentCompleter({ param(§cmd, §param, §word)
                    Get-Service -Name "§word*" | ForEach-Object Name })]
                                                              [string]§Service
            )
        """)
        PsText("ErrorMessage on ValidateScript/ValidatePattern is PS 6+. [ArgumentCompleter] provides dynamic tab completion — here, live service names. Validation runs before begin, so your code only ever sees valid input.")

        PsSection("-WhatIf AND -Confirm: SupportsShouldProcess")
        PsCode("""
            function Remove-OldLog {
                [CmdletBinding(SupportsShouldProcess, ConfirmImpact = 'Medium')]
                param(
                    [Parameter(Mandatory)][string]§Path,
                    [int]§Days = 30
                )
                Get-ChildItem §Path -Filter *.log -File |
                    Where-Object LastWriteTime -lt (Get-Date).AddDays(-§Days) |
                    ForEach-Object {
                        if (§PSCmdlet.ShouldProcess(§_.FullName, 'Delete log file')) {
                            Remove-Item -LiteralPath §_.FullName
                        }
                    }
            }

            Remove-OldLog C:\Temp\PSCourse -WhatIf       # lists, deletes nothing
            Remove-OldLog C:\Temp\PSCourse -Confirm      # asks per file
        """)
        PsWarn("Without -WhatIf, Remove-OldLog really deletes files. Every function that changes system state should implement SupportsShouldProcess so callers can preview it.")
        PsText("§PSCmdlet.ShouldProcess(target, action) returns False under -WhatIf (printing \"What if: Performing the operation…\") and asks the user under -Confirm. ConfirmImpact High makes PowerShell ask automatically (by default §ConfirmPreference is High).")

        PsSection("STRUCTURED OUTPUT")
        PsCode("""
            function Get-DiskUsage {
                [CmdletBinding()]
                [OutputType('PSCourse.DiskUsage')]
                param([Parameter(ValueFromPipeline)][string[]]§ComputerName = §env:COMPUTERNAME)
                process {
                    foreach (§c in §ComputerName) {
                        Get-CimInstance Win32_LogicalDisk -ComputerName §c -Filter 'DriveType=3' |
                            ForEach-Object {
                                [PSCustomObject]@{
                                    PSTypeName = 'PSCourse.DiskUsage'
                                    Computer   = §c
                                    Drive      = §_.DeviceID
                                    SizeGB     = [math]::Round(§_.Size / 1GB, 1)
                                    FreeGB     = [math]::Round(§_.FreeSpace / 1GB, 1)
                                    FreePct    = [math]::Round(100 * §_.FreeSpace / §_.Size)
                                }
                            }
                    }
                }
            }
        """)
        PsText("""
            Output rules for composable commands:
            • Emit OBJECTS, one per item, as soon as they're ready (inside process) — never formatted text, never Format-Table.
            • Keep a CONSISTENT shape — the same properties every time.
            • Give them a type name (PSTypeName) and declare [OutputType()] — enables tab completion of properties downstream and custom formatting via .ps1xml.
            • Use Write-Verbose/Write-Warning for messages, Write-Error for per-item failures, throw / §PSCmdlet.ThrowTerminatingError() for fatal ones.
        """)

        PsSection("COMPOSITION")
        PsCode("""
            'srv01', 'srv02' |
                Get-DiskUsage |
                Where-Object FreePct -lt 15 |
                Sort-Object FreePct |
                Export-Csv C:\Temp\PSCourse\lowdisk.csv -NoTypeInformation
        """)
        PsText("Your function sits in the middle of a pipeline like any built-in cmdlet: names in, objects out, filtered and exported with standard commands. That is the payoff of everything in this lesson.")

        PsSection("CAVEATS")
        PsText("""
            • Without a process block, pipeline input binds only the LAST object.
            • Loop over array parameters inside process to support both pipeline and direct input.
            • Don't call ShouldProcess in a function that doesn't change anything; and pass -WhatIf through to inner cmdlets automatically — §WhatIfPreference propagates.
            • Write-Host bypasses the output stream; avoid it for data.
            • PS 7.3+'s clean block doesn't exist in 5.1.
        """)

        PsSection("CONNECTIONS")
        PsText("This completes lesson 7 (functions). begin/process/end mirror ForEach-Object's -Begin/-Process/-End (lesson 6). Pipeline binding was introduced in lesson 3, attributes inspected via reflection in lesson 23, and error streams in lesson 10.")

        PsExercise("""
            Write ConvertTo-Title as an advanced function that accepts strings from the pipeline and outputs them in Title Case (use (Get-Culture).TextInfo.ToTitleCase). Add -Verbose messages in begin/end that report how many strings were processed.
        """)
        PsChallenge("""
            Build Get-StaleFile with: parameter sets 'ByAge' (-Days) and 'ByDate' (-Before [datetime]); a -Path parameter that accepts pipeline input by property name (alias FullName) and validates the folder exists; output objects with PSTypeName 'PSCourse.StaleFile'; and a companion Remove-StaleFile with SupportsShouldProcess that accepts Get-StaleFile's output from the pipeline. Verify:  Get-ChildItem C:\Temp -Directory | Get-StaleFile -Days 90 | Remove-StaleFile -WhatIf
        """)
    }
}
