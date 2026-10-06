package com.example.windowsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PS02FirstScriptScreen(navController: NavController) {
    PsLessonScreen(navController, "02 YOUR FIRST POWERSHELL SCRIPT") {

        PsSection("FROM PROMPT TO SCRIPT")
        PsText("""
            Anything you can type at the PowerShell prompt can be saved into a file and run again. That file is a SCRIPT. There is no separate "script language" — a script is just interactive commands written down, plus the programming constructs (variables, if, loops, functions) you will learn in the next lessons.

            PowerShell scripts use the .ps1 extension (the "1" is historical — it was kept for every version). Modules use .psm1 and module manifests use .psd1 (lesson 8).
        """)

        PsSection("THE COMPLETE EXAMPLE")
        PsText("Save this as  Hello.ps1  in a folder such as C:\\Scripts:")
        PsCode("""
            <#
                Hello.ps1
                Greets the user and shows a short system summary.
            #>
            param(
                [string]§Name = §env:USERNAME
            )

            # Greet the user
            Write-Output "Hello, §Name!"

            # When is it?
            §now = Get-Date
            Write-Output "Today is §(§now.DayOfWeek), §(§now.ToString('yyyy-MM-dd'))."

            # How many processes are running?
            §count = (Get-Process).Count
            Write-Output "There are §count processes running."

            # The 3 processes using the most memory
            Get-Process |
                Sort-Object WorkingSet64 -Descending |
                Select-Object -First 3 Name, Id,
                    @{ Name = 'MemoryMB'; Expression = { [math]::Round(§_.WorkingSet64 / 1MB) } }
        """)

        PsSection("RUNNING THE SCRIPT")
        PsCode("""
            PS C:\Scripts> .\Hello.ps1
            PS C:\Scripts> .\Hello.ps1 -Name Gil
            PS C:\> & 'C:\My Scripts\Hello.ps1'   # path with spaces
            PS C:\> pwsh -File C:\Scripts\Hello.ps1   # from cmd.exe / Task Scheduler
        """)
        PsText("""
            • You must give a PATH — .\Hello.ps1, not just Hello.ps1. PowerShell deliberately does not run scripts from the current directory by name, so a malicious file called "dir.ps1" in a downloads folder cannot hijack a command you type.
            • & is the CALL OPERATOR. It runs a command whose name is in a string — needed when the path contains spaces and is quoted.
            • pwsh -File  (or powershell.exe -File) starts a new PowerShell process that runs the script and exits.
        """)
        PsOutput("""
            Hello, Gil!
            Today is Tuesday, 2026-10-06.
            There are 243 processes running.

            Name       Id MemoryMB
            ----       -- --------
            chrome   8812      298
            Code    12044      243
            MsMpEng  4120      180
        """)

        PsSection("\"RUNNING SCRIPTS IS DISABLED ON THIS SYSTEM\"")
        PsText("""
            On a fresh Windows client, Windows PowerShell 5.1's execution policy is Restricted, so your first .ps1 fails with this error. The usual fix for your own user account is:
        """)
        PsWarn("This changes a PowerShell setting for your user account. RemoteSigned allows local scripts to run but still blocks unsigned scripts downloaded from the internet.")
        PsCode("""
            Set-ExecutionPolicy -Scope CurrentUser -ExecutionPolicy RemoteSigned
            Get-ExecutionPolicy -List     # show the policy at every scope
        """)
        PsText("Execution policy is a safety catch, NOT a security boundary — lesson 25 explains why.")

        PsSection("LINE-BY-LINE WALKTHROUGH")
        PsText("""
            <# ... #>
            A BLOCK COMMENT. Everything between <# and #> is ignored. Placed at the top of a script it is also where comment-based help goes (.SYNOPSIS, .EXAMPLE …), which Get-Help can read.

            param( [string]§Name = §env:USERNAME )
            Declares the script's PARAMETERS. -Name becomes a named argument of the script, exactly like a cmdlet parameter. [string] is an optional type, and = §env:USERNAME is the default value read from the USERNAME environment variable. param() must be the first statement in the script (after comments).

            # Greet the user
            A LINE COMMENT. # to end-of-line is ignored.

            Write-Output "Hello, §Name!"
            A COMMAND with one argument. Strings in DOUBLE quotes expand variables, so §Name is replaced by its value. 'Single quotes' never expand. Write-Output sends the string down the pipeline — at the end of the script it is displayed.

            §now = Get-Date
            An ASSIGNMENT STATEMENT. Get-Date returns a System.DateTime OBJECT (not text), stored in the variable §now.

            "Today is §(§now.DayOfWeek) ..."
            §( ) is a SUBEXPRESSION inside a string. Plain "§now.DayOfWeek" would expand only §now and then print the literal text ".DayOfWeek". §(...) evaluates the whole expression first. .ToString('yyyy-MM-dd') CALLS A METHOD on the DateTime object.

            §count = (Get-Process).Count
            Parentheses run the command first; .Count is the number of objects returned.

            Get-Process | Sort-Object ... | Select-Object ...
            A PIPELINE. The | operator passes each process OBJECT from one command to the next:
              Get-Process  → emits Process objects
              Sort-Object WorkingSet64 -Descending  → sorts them by the WorkingSet64 property (bytes of RAM)
              Select-Object -First 3 Name, Id, @{...}  → keeps the first 3 and only the listed properties.
            A line ending in | continues on the next line automatically.

            @{ Name = 'MemoryMB'; Expression = { ... } }
            A CALCULATED PROPERTY — a hash table that defines a new column. The Expression is a SCRIPT BLOCK (lesson 11) run for each object; §_ means "the current object in the pipeline". 1MB is a numeric literal suffix (1,048,576), and [math]::Round calls a static .NET method (lesson 22).

            The last pipeline has no Write-Output, yet its result is still shown. Anything a statement produces that is not captured into a variable goes to the OUTPUT STREAM automatically.
        """)

        PsSection("STATEMENTS, COMMANDS AND PARAMETERS")
        PsText("""
            • A STATEMENT is one unit of work — usually one line. Several statements can share a line separated by ;
            • A COMMAND is something PowerShell can run: a cmdlet (Get-Process), a function, a script (.\Hello.ps1), an alias (ls), or a native program (ipconfig.exe).
            • PARAMETERS start with a dash: -Name Gil, -First 3, -Descending (a SWITCH parameter: present = true, absent = false). Parameter names can be shortened as long as they stay unambiguous: -Desc works for -Descending.
            • POSITIONAL arguments have no name: Sort-Object WorkingSet64 is really Sort-Object -Property WorkingSet64.
        """)
        PsCode("""
            Get-ChildItem -Path C:\Windows -Filter *.exe   # named
            Get-ChildItem C:\Windows *.exe                 # positional
            §a = 1; §b = 2; §a + §b                         # 3 statements
        """)

        PsSection("CAVEATS")
        PsText("""
            • Save scripts as UTF-8. Windows PowerShell 5.1 reads a BOM-less file as the system ANSI code page, so non-ASCII characters (é, ü, emoji) break. UTF-8 WITH BOM works in both 5.1 and 7; VS Code's PowerShell extension handles this.
            • Double-clicking a .ps1 in Explorer opens it in Notepad by default — this is deliberate, so scripts are not run by accident.
            • Variables created inside a script disappear when it finishes (lesson 9). To keep them in your session, "dot-source" it:  . .\Hello.ps1
        """)

        PsSection("CONNECTIONS")
        PsText("This lesson used the object pipeline introduced in lesson 1. Lesson 3 looks at cmdlets and the pipeline in depth; lesson 4 covers the variables and types you just saw.")

        PsExercise("""
            Create Hello.ps1 from the example and run it twice: once with no arguments and once with -Name followed by your own name. Then change the script to show the TOP 5 processes instead of 3.
        """)
        PsChallenge("""
            Add a second parameter, [int]§Top = 3, and use it in Select-Object -First §Top. Then add a line that shows how long the computer has been running:
              (Get-Date) - (Get-CimInstance Win32_OperatingSystem).LastBootUpTime
            Format the result as "Up for X days, Y hours" using the .Days and .Hours properties of the TimeSpan object.
        """)
    }
}
