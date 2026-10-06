package com.example.windowsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PS06LoopsScreen(navController: NavController) {
    PsLessonScreen(navController, "06 LOOPS AND ITERATION") {

        PsSection("WHY LOOPS?")
        PsText("""
            A loop repeats a block of code — once per item in a collection, a fixed number of times, or until a condition changes. PowerShell has the classic language loops (for, foreach, while, do) AND a pipeline-based loop (ForEach-Object). Knowing when to use which is a key PowerShell skill.
        """)

        PsSection("FOR — COUNTED LOOP")
        PsCode("""
            for (§i = 1; §i -le 5; §i++) {
                "Iteration §i"
            }
        """)
        PsText("""
            Three parts separated by ; — INITIALIZE (§i = 1, runs once), CONDITION (§i -le 5, checked before every pass), STEP (§i++, runs after every pass). Use for when you need the index, need to step by more than 1, or walk backwards.
        """)
        PsCode("""
            §files = Get-ChildItem C:\Windows -File
            for (§i = 0; §i -lt §files.Count; §i += 10) {
                "{0,4}: {1}" -f §i, §files[§i].Name    # every 10th file
            }
        """)

        PsSection("FOREACH — LANGUAGE LOOP OVER A COLLECTION")
        PsCode("""
            §colors = 'red', 'green', 'blue'
            foreach (§c in §colors) {
                "Color: §c"
            }

            foreach (§p in Get-Process) {
                if (§p.WorkingSet64 -gt 500MB) {
                    "{0,-20} {1,8:N0} MB" -f §p.Name, (§p.WorkingSet64 / 1MB)
                }
            }
        """)
        PsText("""
            foreach (§item in §collection) runs the body once for every element, assigning it to §item. The collection is evaluated FIRST and held in memory completely, then iterated. This is usually the FASTEST loop in PowerShell. The format string {1,8:N0} means: argument 1, right-aligned in 8 characters, number with thousands separators and 0 decimals.
        """)

        PsSection("WHILE — LOOP WHILE A CONDITION IS TRUE")
        PsCode("""
            §n = 1
            while (§n -lt 1000) {
                §n *= 2
            }
            §n      # 1024

            # Wait for a file to appear (max 30 seconds)
            §deadline = (Get-Date).AddSeconds(30)
            while (-not (Test-Path C:\Temp\ready.flag) -and (Get-Date) -lt §deadline) {
                Start-Sleep -Seconds 1
            }
        """)
        PsText("The condition is checked BEFORE each pass, so the body may run zero times. Always make sure something in the loop eventually makes the condition false — or add a timeout as above.")

        PsSection("DO / WHILE AND DO / UNTIL")
        PsCode("""
            do {
                §answer = Read-Host 'Type yes or no'
            } while (§answer -notin 'yes', 'no')

            do {
                §answer = Read-Host 'Type yes or no'
            } until (§answer -in 'yes', 'no')
        """)
        PsText("A do loop checks its condition AFTER the body, so the body runs at least once — perfect for input validation. do/while repeats while true; do/until repeats until true.")

        PsSection("BREAK AND CONTINUE")
        PsCode("""
            foreach (§n in 1..10) {
                if (§n % 2 -eq 0) { continue }   # skip even numbers
                if (§n -gt 7)     { break }      # leave the loop
                §n
            }
        """)
        PsOutput("""
            1
            3
            5
            7
        """)
        PsCode("""
            # Labeled break: exit an OUTER loop from an inner one
            :outer foreach (§dir in 'C:\Windows', 'C:\Program Files') {
                foreach (§f in Get-ChildItem §dir -File) {
                    if (§f.Name -eq 'notepad.exe') {
                        "Found in §dir"
                        break outer
                    }
                }
            }
        """)
        PsText("continue jumps to the next iteration; break exits the innermost loop (or switch). A :label before a loop lets break/continue target that loop.")

        PsSection("ForEach-Object — THE PIPELINE LOOP")
        PsCode("""
            Get-ChildItem C:\Windows -File | ForEach-Object {
                "{0} — {1:N0} bytes" -f §_.Name, §_.Length
            }

            1..5 | ForEach-Object { §_ * §_ }        # 1 4 9 16 25
            Get-Process | ForEach-Object Name        # shorthand: just a property
            'a','b' | ForEach-Object ToUpper         # shorthand: call a method
        """)
        PsText("""
            ForEach-Object (alias %, and also foreach — confusingly) is a CMDLET. It receives objects one at a time from the pipeline and runs the script block for each; §_ is the current object. It also supports -Begin and -End blocks that run once before/after all objects.
        """)
        PsCode("""
            Get-ChildItem C:\Windows -File | ForEach-Object -Begin { §total = 0 } -Process {
                §total += §_.Length
            } -End {
                "Total: {0:N0} MB" -f (§total / 1MB)
            }
        """)

        PsSection("foreach (STATEMENT) vs ForEach-Object (CMDLET)")
        PsCode("""
                           foreach (§x in §list)     ... | ForEach-Object
            ─────────────────────────────────────────────────────────────
            Kind           language keyword        cmdlet in a pipeline
            Input          whole collection        streamed one at a time
                           loaded into memory first
            Speed          fast                    slower per item
            Memory         holds everything        constant — good for
                                                   huge inputs (big logs)
            Current item   your named variable     §_ / §PSItem
            Output         can be captured with    flows straight on to
                           §r = foreach(...){...}  the next command
            break          leaves the loop         stops the WHOLE
                                                   pipeline (surprise!)
            Parallel       no                      -Parallel (PS 7+)
        """)
        PsText("""
            Rules of thumb:
            • Data already in a variable, need speed or break/continue → foreach statement.
            • Data streaming from a command, very large inputs, or you want to keep piping → ForEach-Object.
            • Inside ForEach-Object, use  return  to skip to the next item (it acts like continue); break and continue there behave unexpectedly because there is no enclosing loop.
        """)

        PsSection("ITERATING COLLECTIONS")
        PsCode("""
            # Hash table: iterate entries, not the table itself
            §ports = @{ HTTP = 80; HTTPS = 443; RDP = 3389 }
            foreach (§entry in §ports.GetEnumerator()) {
                "{0,-6} {1}" -f §entry.Key, §entry.Value
            }

            # Collect results from a loop into a variable
            §big = foreach (§f in Get-ChildItem §env:TEMP -File) {
                if (§f.Length -gt 10MB) { §f.FullName }
            }
            "Found §(§big.Count) large files"

            # Parallel (PowerShell 7+): ping 4 hosts at once
            'google.com','bing.com','github.com','microsoft.com' |
                ForEach-Object -Parallel {
                    [pscustomobject]@{ Host = §_; Up = Test-Connection §_ -Count 1 -Quiet }
                } -ThrottleLimit 4
        """)
        PsText("""
            • Piping or foreach-ing a hash table directly gives you ONE item (the whole table). .GetEnumerator() yields each key/value pair.
            • Assigning a loop to a variable collects everything the loop outputs — far faster than §results += inside the loop.
            • -Parallel runs each script block in its own runspace (thread). Variables from outside are not visible unless referenced with §using:name.
        """)

        PsSection("CAVEATS")
        PsText("""
            • foreach is both a keyword and an alias of ForEach-Object; at the start of a statement it is the keyword, after | it is the cmdlet.
            • Don't modify a collection while iterating it with foreach (e.g. removing items from a List) — .NET throws "Collection was modified".
            • 1..1000000 creates a million-element array up front in 5.1; use a for loop for very large ranges.
        """)

        PsSection("CONNECTIONS")
        PsText("Loops use the conditions from lesson 5 and the arrays/hash tables from lesson 4. The ForEach-Object -Begin/-Process/-End pattern is exactly how advanced functions process pipeline input (lesson 24).")

        PsExercise("""
            1. Use a for loop to print the 7-times table (7 x 1 … 7 x 10).
            2. Use foreach over Get-Service to count how many services are Running vs Stopped.
            3. Repeat 2 with Get-Service | ForEach-Object using -Begin/-Process/-End.
        """)
        PsChallenge("""
            Write a do/until loop that asks the user for a directory path until they type one that exists (Test-Path -PathType Container). Then loop through every file in it and print the 3 largest files and the total size. Measure the time of a foreach version vs a ForEach-Object version with Measure-Command { ... } on C:\Windows\System32. Which is faster and by how much?
        """)
    }
}
