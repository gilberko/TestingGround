package com.example.windowsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PS10ErrorHandlingScreen(navController: NavController) {
    PsLessonScreen(navController, "10 ERROR AND EXCEPTION HANDLING") {

        PsSection("THE POWERSHELL ERROR MODEL")
        PsText("""
            Most languages have one kind of error: an exception that stops execution until something catches it. PowerShell has TWO, because a shell command often works on MANY items at once:

            • NON-TERMINATING ERROR — "this one item failed, keep going". Get-ChildItem over 1,000 folders hits one access-denied folder: it reports an error for that folder and continues with the other 999. This is the DEFAULT for most cmdlet errors.

            • TERMINATING ERROR — "stop now". The command (and, unless caught, the script) stops. Caused by throw, by .NET method exceptions, by syntax/parameter-binding errors, or by cmdlets that can't continue at all.

            try/catch ONLY catches TERMINATING errors. This is the #1 error-handling confusion in PowerShell.
        """)

        PsSection("SEEING THE DIFFERENCE")
        PsCode("""
            try {
                Get-Item C:\DoesNotExist
                'This line STILL runs'
            }
            catch {
                'Caught!'        # never reached
            }
        """)
        PsOutput("""
            Get-Item: Cannot find path 'C:\DoesNotExist' because it does not exist.
            This line STILL runs
        """)
        PsText("Get-Item wrote a NON-terminating error (red text) and execution continued. The catch block never ran.")

        PsSection("-ErrorAction: PER-COMMAND CONTROL")
        PsCode("""
            try {
                Get-Item C:\DoesNotExist -ErrorAction Stop
                'Not reached'
            }
            catch {
                "Caught: §(§_.Exception.Message)"
            }
        """)
        PsOutput("Caught: Cannot find path 'C:\\DoesNotExist' because it does not exist.")
        PsText("""
            -ErrorAction (alias -EA) is a common parameter on every cmdlet:
            • Stop — turn non-terminating errors into terminating ones (catchable). Use this inside try.
            • Continue — default: show the error, keep going.
            • SilentlyContinue — hide the error, keep going (still recorded in §Error).
            • Ignore — hide it and don't record it.
            • Inquire — ask the user.
        """)

        PsSection("§ErrorActionPreference: SCRIPT-WIDE DEFAULT")
        PsCode("""
            §ErrorActionPreference = 'Stop'    # top of the script

            try {
                Copy-Item C:\Data\*.csv D:\Backup\   # any error now terminates
                Remove-Item C:\Data\*.csv
            }
            catch {
                Write-Warning "Backup failed: §(§_.Exception.Message)"
                exit 1
            }
        """)
        PsText("""
            §ErrorActionPreference sets the default -ErrorAction for every command in that scope (and child scopes). Many production scripts start with 'Stop' so that ANY unexpected error halts the script instead of carrying on in a broken state — like Bash's set -e. Above, if the copy fails, the Remove-Item never runs, so data is not lost.

            Note: native programs (git.exe, robocopy.exe) don't raise PowerShell errors; check §LASTEXITCODE. In PowerShell 7.4+ you can set §PSNativeCommandUseErrorActionPreference = §true to make a non-zero exit code an error.
        """)

        PsSection("TRY / CATCH / FINALLY")
        PsCode("""
            try {
                # code that might fail
            }
            catch [System.IO.FileNotFoundException] {
                # specific exception type first
            }
            catch [System.UnauthorizedAccessException] {
                # another specific type
            }
            catch {
                # anything else
            }
            finally {
                # ALWAYS runs — success, failure, even Ctrl+C
            }
        """)
        PsText("""
            Inside catch, §_ (or §PSItem) is an ErrorRecord with:
            • §_.Exception — the .NET exception (.Message, .GetType().FullName, .InnerException)
            • §_.CategoryInfo, §_.FullyQualifiedErrorId — PowerShell classification
            • §_.InvocationInfo — script, line number and command where it happened
            • §_.ScriptStackTrace — call stack

            catch blocks are matched top-to-bottom, so put the most specific types first. finally is where you release resources: close files, dispose connections, remove temp files.
        """)

        PsSection("REALISTIC EXAMPLE: FILE ACCESS")
        PsCode("""
            §path = 'C:\Windows\System32\config\SAM'    # locked by the OS
            §stream = §null
            try {
                §stream = [System.IO.File]::OpenRead(§path)
                "Opened §path, length §(§stream.Length)"
            }
            catch [System.UnauthorizedAccessException] {
                "Access denied: run as administrator?"
            }
            catch [System.IO.IOException] {
                "File is in use by another process: §(§_.Exception.Message)"
            }
            finally {
                if (§stream) { §stream.Dispose() }
                'Cleanup done'
            }
        """)
        PsOutput("""
            File is in use by another process: The process cannot access the file 'C:\Windows\System32\config\SAM' because it is being used by another process.
            Cleanup done
        """)
        PsText("""
            .NET methods like [System.IO.File]::OpenRead always throw TERMINATING exceptions, so no -ErrorAction is needed. The SAM registry hive is held open by the kernel: a normal user typically hits the access-denied branch (the file's ACL), while an administrator passes the ACL check and gets the IOException (sharing violation) shown above. The finally block disposes the stream whether or not it opened. Note that in .NET, FileNotFoundException derives from IOException — had the IOException block been first, it would also have caught "not found".
        """)

        PsSection("REALISTIC EXAMPLE: REGISTRY")
        PsCode("""
            function Get-RegValue {
                param([string]§Path, [string]§Name)
                try {
                    Get-ItemPropertyValue -Path §Path -Name §Name -ErrorAction Stop
                }
                catch [System.Management.Automation.ItemNotFoundException] {
                    Write-Warning "Key not found: §Path"
                }
                catch [System.Management.Automation.PSArgumentException] {
                    Write-Warning "Value '§Name' not found under §Path"
                }
            }

            Get-RegValue 'HKLM:\SOFTWARE\Microsoft\Windows NT\CurrentVersion' 'ProductName'
            Get-RegValue 'HKLM:\SOFTWARE\NoSuchKey' 'X'
            Get-RegValue 'HKLM:\SOFTWARE\Microsoft\Windows NT\CurrentVersion' 'NoSuchValue'
        """)
        PsText("""
            Discovering exception types: trigger the error once, then inspect  §Error[0].Exception.GetType().FullName. That gives you the exact type to put in catch [...].
        """)

        PsSection("REALISTIC EXAMPLE: NETWORK REQUESTS")
        PsCode("""
            §urls = 'https://api.github.com', 'https://no-such-host.invalid', 'https://httpbin.org/status/500'

            foreach (§u in §urls) {
                try {
                    §r = Invoke-WebRequest -Uri §u -TimeoutSec 10 -ErrorAction Stop
                    "OK   §u (§(§r.StatusCode))"
                }
                catch [System.Net.Http.HttpRequestException] {
                    # PS 7: DNS failure, connection refused, or HTTP error code
                    §code = §_.Exception.Response.StatusCode
                    if (§code) { "HTTP §u → §([int]§code) §code" }
                    else       { "NET  §u → §(§_.Exception.Message)" }
                }
                catch {
                    "FAIL §u → §(§_.Exception.GetType().Name): §(§_.Exception.Message)"
                }
            }
        """)
        PsText("""
            In PowerShell 7, Invoke-WebRequest throws HttpResponseException / HttpRequestException (System.Net.Http) for HTTP 4xx/5xx and connection problems. In 5.1 it throws System.Net.WebException instead — the catch-all catch block keeps the script working in both. The loop continues to the next URL because each request has its own try.
        """)

        PsSection("THROW: RAISING YOUR OWN ERRORS")
        PsCode("""
            function Set-Port {
                param([int]§Port)
                if (§Port -lt 1 -or §Port -gt 65535) {
                    throw "Port §Port is out of range (1-65535)"
                }
                "Port set to §Port"
            }

            try { Set-Port 70000 }
            catch { "Error: §(§_.Exception.Message)" }

            # Throw a specific .NET exception type:
            throw [System.ArgumentOutOfRangeException]::new('Port', 'Must be 1-65535')

            # Inside catch, re-throw the same error to the caller:
            catch { Write-Warning 'logging…'; throw }
        """)
        PsText("throw always produces a TERMINATING error. A bare throw inside catch re-throws the current error with its original details. In advanced functions, §PSCmdlet.ThrowTerminatingError() and Write-Error are the cmdlet-style alternatives (lesson 24).")

        PsSection("NON-TERMINATING ERRORS YOU WRITE: Write-Error")
        PsCode("""
            foreach (§f in 'a.txt', 'missing.txt', 'c.txt') {
                if (-not (Test-Path §f)) {
                    Write-Error "§f not found"   # report, keep going
                    continue
                }
                "Processing §f"
            }
        """)

        PsSection("§Error AND -ErrorVariable")
        PsCode("""
            §Error[0]                     # most recent error
            §Error.Count
            §Error.Clear()

            Get-ChildItem C:\Windows\System32 -Recurse -ErrorAction SilentlyContinue `
                -ErrorVariable problems | Out-Null
            "§(§problems.Count) folders could not be read"
            §problems | Select-Object -First 3 TargetObject

            Get-Error                     # PS 7: detailed view of the last error
        """)
        PsText("-ErrorVariable collects that one command's errors into a variable without stopping or printing — perfect for a summary at the end.")

        PsSection("CAVEATS")
        PsText("""
            • try/catch catches only TERMINATING errors — add -ErrorAction Stop to cmdlets inside try.
            • SilentlyContinue hides errors; use it deliberately, never as a blanket fix.
            • §? is True/False for the last command, but gets reset by EVERY command — check it immediately.
            • The old  trap { }  statement still works but try/catch is clearer.
            • Don't catch and ignore everything: catch { } with an empty body hides real bugs.
        """)

        PsSection("CONNECTIONS")
        PsText("Lesson 5's -ErrorAction SilentlyContinue examples now make sense. The finally block is how you guarantee cleanup of the resources mentioned in lesson 9. Lessons 12, 13 and 15 use these patterns for files, the registry and networking.")

        PsExercise("""
            Write a script that asks for a file path with Read-Host and prints its first 5 lines with Get-Content -TotalCount 5. Handle: path not found (ItemNotFoundException), access denied (UnauthorizedAccessException), and anything else. Use -ErrorAction Stop and always print "Done." in finally.
        """)
        PsChallenge("""
            Write Test-Websites that takes an array of URLs, tries each with Invoke-WebRequest (timeout 5 s), and outputs one [PSCustomObject] per URL with Url, Success (bool), StatusCode (or §null) and Error (message or §null). One failing URL must not stop the others. Bonus: retry each failed URL up to 3 times with a 2-second delay before marking it failed.
        """)
    }
}
