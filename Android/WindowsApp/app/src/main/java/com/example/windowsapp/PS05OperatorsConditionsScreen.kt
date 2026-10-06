package com.example.windowsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PS05OperatorsConditionsScreen(navController: NavController) {
    PsLessonScreen(navController, "05 OPERATORS, EXPRESSIONS AND CONDITIONS") {

        PsSection("EXPRESSIONS AND OPERATORS")
        PsText("""
            An EXPRESSION is anything that produces a value: 42, §x, §x * 2, (Get-Process).Count, §a -gt §b. OPERATORS combine values into new values.

            PowerShell's comparison operators are WORDS with a dash (-eq, -gt) rather than symbols (==, >). That is because in a shell > and < already mean output / input REDIRECTION — writing  if (§a > 5)  would create a file called "5"!
        """)

        PsSection("ARITHMETIC OPERATORS")
        PsCode("""
            10 + 3     # 13
            10 - 3     # 7
            10 * 3     # 30
            10 / 3     # 3.33333333333333
            10 % 3     # 1      (remainder)
            -5         # unary minus
            §i = 0
            §i++       # 1      (also §i--)
            §i += 10   # 11     (also -=  *=  /=  %=)
            2 -shl 3   # 16     (bit shift; also -shr)
            0xF0 -band 0x3C   # 48 (bitwise AND; also -bor -bxor -bnot)
        """)
        PsText("Arithmetic also works on other types: 'ab' * 3 → 'ababab', @(1,2) + @(3) → 1 2 3, (Get-Date) + (New-TimeSpan -Days 7) → a week from now.")

        PsSection("COMPARISON OPERATORS")
        PsCode("""
            Operator   Meaning                 Example → result
            ────────────────────────────────────────────────────
            -eq        equal                   5 -eq 5        → True
            -ne        not equal               5 -ne 3        → True
            -gt  -ge   greater (or equal)      5 -gt 3        → True
            -lt  -le   less (or equal)         5 -le 5        → True
            -like      wildcard match          'notepad.exe' -like '*.exe'
            -notlike
            -match     regex match (lesson 19) 'abc123' -match '\d+'
            -notmatch
            -contains  collection has item     1,2,3 -contains 2
            -in        item in collection      2 -in 1,2,3
            -notcontains / -notin
            -is        type test               42 -is [int]   → True
            -isnot
        """)
        PsText("""
            CASE: comparisons are CASE-INSENSITIVE by default. 'ABC' -eq 'abc' is True. Prefix with c for case-sensitive (-ceq, -clike, -cmatch) or i to be explicit (-ieq).

            COLLECTIONS ON THE LEFT: when the left side is an array, the operator acts as a FILTER and returns the matching elements:
        """)
        PsCode("""
            1, 5, 10, 20 -gt 7          # 10 20
            'a.txt','b.log' -like '*.log'   # b.log
        """)

        PsSection("LOGICAL (BOOLEAN) OPERATORS")
        PsCode("""
            -and   both true          (§a -gt 0) -and (§a -lt 10)
            -or    either true        (§day -eq 'Sat') -or (§day -eq 'Sun')
            -xor   exactly one true
            -not   negate             -not (Test-Path C:\Temp)
            !      negate (short)     !(Test-Path C:\Temp)
        """)
        PsText("""
            -and and -or SHORT-CIRCUIT: in  §f -and §f.Exists  the right side is not evaluated if §f is §null. Use parentheses around each comparison for readability.
        """)

        PsSection("IF / ELSEIF / ELSE")
        PsCode("""
            §path = 'C:\Temp\report.txt'

            if (Test-Path §path) {
                Write-Output "Found §path"
            }
            elseif (Test-Path 'C:\Temp') {
                Write-Output 'Folder exists, file does not'
            }
            else {
                Write-Output 'No C:\Temp folder at all'
            }
        """)
        PsText("""
            Walkthrough:
            • The condition goes in ( ) and the body in { } — braces are REQUIRED even for one line.
            • Test-Path returns a Boolean, True if the path exists. Any expression works as a condition; it is converted to bool using the truthiness rules of lesson 4.
            • elseif is one word. You can have any number of elseif branches; at most one branch runs.
        """)
        PsCode("""
            # Practical: warn when a process uses too much memory
            §proc = Get-Process -Name chrome -ErrorAction SilentlyContinue |
                    Sort-Object WorkingSet64 -Descending | Select-Object -First 1

            if (-not §proc) {
                'Chrome is not running'
            }
            elseif (§proc.WorkingSet64 -gt 1GB) {
                "Chrome PID §(§proc.Id) uses §([math]::Round(§proc.WorkingSet64/1GB,2)) GB!"
            }
            else {
                'Chrome memory use is fine'
            }
        """)
        PsText("-ErrorAction SilentlyContinue stops Get-Process from printing an error when no chrome process exists; §proc is then §null, which is false, so -not §proc is true.")

        PsSection("SWITCH")
        PsText("When one value is compared against many possibilities, switch is cleaner than a chain of elseif.")
        PsCode("""
            §svc = Get-Service -Name Spooler

            switch (§svc.Status) {
                'Running' { 'Print spooler is up' }
                'Stopped' { 'Print spooler is DOWN' }
                default   { "Spooler is in state §(§svc.Status)" }
            }
        """)
        PsOutput("""
            Print spooler is up
        """)
        PsCode("""
            # switch with -Wildcard, -Regex and script-block conditions
            §file = 'backup_2026.zip'
            switch -Wildcard (§file) {
                '*.zip'  { 'Archive' }
                '*.log'  { 'Log file' }
                'backup*'{ 'Backup — note: ALSO matches, both run!' }
            }

            §n = 15
            switch (§n) {
                { §_ % 15 -eq 0 } { 'FizzBuzz'; break }
                { §_ % 3  -eq 0 } { 'Fizz';     break }
                { §_ % 5  -eq 0 } { 'Buzz';     break }
                default           { §_ }
            }
        """)
        PsText("""
            Key differences from C-style switch:
            • ALL matching branches run (no fall-through needed, but no automatic stop either). Use break to stop after the first match.
            • A condition can be a value, a wildcard (-Wildcard), a regex (-Regex) or a SCRIPT BLOCK in which §_ is the value being tested.
            • If the input is an ARRAY, switch runs once per element — it is also a loop!
            • switch -File C:\log.txt { ... } processes a file line by line.
        """)

        PsSection("USER INPUT")
        PsCode("""
            §answer = Read-Host 'Delete temp files? (y/n)'
            if (§answer -eq 'y') {
                'You said yes'          # (we would delete here — see lesson 12)
            }
            else {
                'Cancelled'
            }

            §age = [int](Read-Host 'Your age')
            if (§age -ge 18) { 'Adult' } else { 'Minor' }

            §pw = Read-Host 'Password' -AsSecureString   # hidden typing
        """)
        PsText("""
            Read-Host always returns a STRING. Casting to [int] makes the -ge comparison numeric — without it, '9' -ge 18 would compare as strings ('9' is greater than '18' alphabetically!). -AsSecureString hides the input and returns a SecureString (lesson 25).
        """)

        PsSection("POWERSHELL 7 EXTRAS")
        PsCode("""
            §status = (Test-Path C:\Temp) ? 'exists' : 'missing'   # ternary
            §name = §env:APP_NAME ?? 'default-app'                 # null-coalescing
            §cache ??= @{}                                         # assign if null
            git --version && 'git works' || 'git missing'          # pipeline chain
            §{proc}?.Id    # null-conditional member access: no error if §proc is null
        """)
        PsText("""
            These operators do NOT exist in Windows PowerShell 5.1 — scripts using them fail to parse there.

            Careful: && and || test whether the previous command SUCCEEDED (§? / exit code 0), NOT whether it returned True. Test-Path C:\Nope && 'yes' prints False AND 'yes', because Test-Path ran successfully. For a null-conditional access, the variable name must be in braces, §{proc}?.Id, because ? is a legal character in variable names.
        """)

        PsSection("CAVEATS")
        PsText("""
            • = is ASSIGNMENT, never comparison. if (§x = 5) assigns 5 and is always true.
            • > is redirection: §a > 5 writes §a to a file named 5.
            • Comparisons convert the RIGHT operand to the LEFT operand's type: 10 -eq '10.0' is True, '10' -eq 10.0 is False ('10' vs '10.0' as strings).
            • Put §null on the left: §null -eq §x.
        """)

        PsSection("CONNECTIONS")
        PsText("Lesson 4's types decide how operators behave (string vs number). Where-Object from lesson 3 uses exactly these comparison operators inside its script block. Lesson 19 expands -match / -replace / -split with regular expressions.")

        PsExercise("""
            Ask the user for a service name with Read-Host. If the service does not exist, print a message. If it is running, print "<name> is running"; otherwise print its status. Use Get-Service with -ErrorAction SilentlyContinue.
        """)
        PsChallenge("""
            Write a switch that classifies every file in §env:TEMP by extension: .log/.txt → 'Text', .zip/.7z/.cab → 'Archive', .exe/.dll/.msi → 'Binary', everything else → 'Other'. Count how many files fall in each class using a hash table of counters (§counts[§class]++). Hint: switch can loop over (Get-ChildItem §env:TEMP -File).Extension.
        """)
    }
}
