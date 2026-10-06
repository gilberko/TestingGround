package com.example.windowsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PS11ScriptBlocksScreen(navController: NavController) {
    PsLessonScreen(navController, "11 SCRIPT BLOCKS AND CLOSURES") {

        PsSection("WHAT IS A SCRIPT BLOCK?")
        PsText("""
            A SCRIPT BLOCK is a piece of PowerShell code wrapped in braces { } that is treated as a VALUE. It isn't run where it is written — it is stored, passed around, and run later, possibly many times.

            You have already used dozens of them:
              Where-Object { §_.Status -eq 'Running' }
              ForEach-Object { §_.Name }
              @{ Name = 'MB'; Expression = { §_.Length / 1MB } }
              if (...) { ... }   — the bodies of if/foreach/function are blocks too

            In other languages the same idea is called an anonymous function, lambda, callback or closure: C#'s x => x * 2, JavaScript's () => {...}, Python's lambda.
        """)

        PsSection("STORING AND RUNNING A SCRIPT BLOCK")
        PsCode("""
            §sayHi = { 'Hello!' }
            §sayHi.GetType().FullName     # System.Management.Automation.ScriptBlock
            §sayHi                        # prints the CODE, doesn't run it
            & §sayHi                      # call operator: runs it → Hello!
            §sayHi.Invoke()               # .NET-style invocation
        """)
        PsOutput("""
            System.Management.Automation.ScriptBlock
             'Hello!'
            Hello!
            Hello!
        """)
        PsText("""
            & (call operator) runs the block in a CHILD scope; . (dot) runs it in the CURRENT scope (lesson 9). .Invoke() also works but always returns a collection; & is the idiomatic choice.
        """)

        PsSection("PARAMETERS AND RETURN VALUES")
        PsCode("""
            §add = { param(§a, §b) §a + §b }
            & §add 3 4                    # 7

            §square = { §args[0] * §args[0] }   # §args = unnamed arguments
            & §square 9                   # 81

            §greet = {
                param([string]§Name = 'World')
                "Hello, §Name"
            }
            & §greet -Name Ada
        """)
        PsText("A script block is an anonymous function: it can have a param() block with types and defaults, and it returns all its output — exactly like the functions of lesson 7. In fact, a function is just a script block with a name.")

        PsSection("PASSING SCRIPT BLOCKS TO COMMANDS (CALLBACKS)")
        PsCode("""
            function Invoke-ForEachFile {
                param(
                    [string]§Path,
                    [scriptblock]§Action
                )
                foreach (§f in Get-ChildItem §Path -File) {
                    & §Action §f              # call the callback with the file
                }
            }

            Invoke-ForEachFile C:\Windows { param(§file) "{0,-25} {1,10:N0}" -f §file.Name, §file.Length }
            Invoke-ForEachFile C:\Windows { param(§file) if (§file.Extension -eq '.exe') { §file.Name } }
        """)
        PsText("""
            Invoke-ForEachFile knows HOW to walk files; the caller decides WHAT to do with each one. Same function, different behavior — that is the callback pattern. Typing the parameter [scriptblock] gives a clear error if someone passes something else.
        """)
        PsCode("""
            # Built-in cmdlets that take script blocks:
            Measure-Command { Get-ChildItem C:\Windows -Recurse -EA 0 }   # timing
            Invoke-Command -ScriptBlock { hostname } -ComputerName srv01   # remoting
            Start-Job -ScriptBlock { Start-Sleep 5; 'done' }              # background
            Register-ObjectEvent §timer Elapsed -Action { 'tick' }        # events
            Sort-Object { §_.Name.Length }                                # custom sort key
        """)

        PsSection("SCRIPT BLOCKS ARE ALSO .NET DELEGATES")
        PsCode("""
            §nums = [System.Collections.Generic.List[int]](5, 3, 9, 1)
            §nums.RemoveAll({ param(§n) §n -gt 4 })    # Predicate<int>
            §nums                                       # 3 1

            (1..10).Where({ §_ % 2 -eq 0 })     # intrinsic .Where() → 2 4 6 8 10
            (1..5).ForEach({ §_ * 10 })         # intrinsic .ForEach() → 10..50
        """)
        PsText("PowerShell converts a script block automatically when a .NET method wants a delegate (Func<>, Action<>, Predicate<>). The .Where() and .ForEach() methods (available on every collection since PS 4) are faster than the pipeline cmdlets for in-memory data.")

        PsSection("CLOSURES — THE PROBLEM")
        PsText("""
            A CLOSURE is a function bundled together with the variables it uses from the place where it was created. To see why it matters, consider a script block that refers to an outer variable:
        """)
        PsCode("""
            function New-Greeter {
                param([string]§Greeting)
                { param(§name) "§Greeting, §name!" }     # returns a script block
            }

            §hello = New-Greeter 'Hello'
            & §hello 'Ada'
        """)
        PsOutput(", Ada!")
        PsText("""
            The greeting is MISSING. Why? A normal script block doesn't remember where it was created. When we finally run §hello, New-Greeter has already returned and its scope (holding §Greeting) has been destroyed (lesson 9). The block looks up §Greeting dynamically, in the scope where it RUNS — and finds nothing.
        """)

        PsSection("CLOSURES — THE FIX: GetNewClosure()")
        PsCode("""
            function New-Greeter {
                param([string]§Greeting)
                { param(§name) "§Greeting, §name!" }.GetNewClosure()
            }

            §hello = New-Greeter 'Hello'
            §hola  = New-Greeter 'Hola'
            & §hello 'Ada'
            & §hola 'Ada'
        """)
        PsOutput("""
            Hello, Ada!
            Hola, Ada!
        """)
        PsText("""
            .GetNewClosure() returns a copy of the script block bound to a new dynamic module containing a SNAPSHOT of the local variables at that moment. Each call to New-Greeter captures its OWN §Greeting, so §hello and §hola remember different values even though New-Greeter's scope is long gone.

            Important: PowerShell captures the VALUE at the time GetNewClosure() is called (a snapshot), not a live link to the variable as C# or JavaScript do.
        """)

        PsSection("CLOSURES — STATE THAT SURVIVES")
        PsCode("""
            function New-Counter {
                §count = 0
                {
                    §script:count++          # 'script' here = the closure's module
                    §script:count
                }.GetNewClosure()
            }

            §c1 = New-Counter
            §c2 = New-Counter
            & §c1; & §c1; & §c1     # 1 2 3
            & §c2                   # 1   — independent state
        """)
        PsText("""
            Because the closure's captured variables live in their own small module, §script:count inside the block refers to THAT module's copy. Each counter keeps private, persistent state — like a tiny object. (Lesson 17 shows classes, the more structured way to bundle state and behavior.)
        """)

        PsSection("CLOSURES IN LOOPS")
        PsCode("""
            §actions = foreach (§i in 1..3) {
                { "Action §i" }.GetNewClosure()
            }
            §actions | ForEach-Object { & §_ }
        """)
        PsOutput("""
            Action 1
            Action 2
            Action 3
        """)
        PsText("Without GetNewClosure(), all three blocks would print the value §i has when they finally run (3, the last value — or nothing if §i is gone). This is the same loop-capture bug known from JavaScript's var.")

        PsSection("§using: — PASSING VALUES TO OTHER RUNSPACES")
        PsCode("""
            §threshold = 100MB
            Get-Process | ForEach-Object -Parallel {
                if (§_.WorkingSet64 -gt §using:threshold) { §_.Name }
            }

            Invoke-Command -ComputerName srv01 -ScriptBlock {
                Get-ChildItem §using:path
            }
        """)
        PsText("Script blocks sent to another thread, job or computer can't see your variables at all. §using:name copies the value into the block at launch — a different capture mechanism for a different situation.")

        PsSection("CREATING SCRIPT BLOCKS FROM STRINGS")
        PsWarn("Creating code from strings that include user input is a CODE INJECTION risk. Never build script blocks from untrusted text.")
        PsCode("""
            §code = 'Get-Date -Format yyyy'
            §sb = [scriptblock]::Create(§code)
            & §sb
        """)

        PsSection("CAVEATS")
        PsText("""
            • Printing a script block variable shows its source; you must & it to run it.
            • §_ inside a script block refers to the CURRENT pipeline object of whichever pipeline is running — inside nested pipelines it changes. Assign §outer = §_ before nesting.
            • GetNewClosure() snapshots variables; later changes to the original variable are not seen.
            • Script blocks can't be serialized with their closures to remote machines — use §using:.
        """)

        PsSection("CONNECTIONS")
        PsText("Every Where-Object/ForEach-Object (lessons 3, 6) and calculated property (lesson 2) used script blocks. Lesson 9's scope rules explain the closure problem. Lesson 24's advanced functions use [ValidateScript({ ... })], another script-block parameter.")

        PsExercise("""
            Create a hash table of named operations:
              §ops = @{ add = { param(§a,§b) §a + §b }; mul = { param(§a,§b) §a * §b } }
            Add 'sub' and 'div'. Then loop over §ops.Keys and print "<op>(12, 4) = <result>" for each.
        """)
        PsChallenge("""
            Write New-RateLimiter -MaxCalls 3 that returns a closure. Each time the closure is invoked with a script block, it runs it — unless it has already run 3 times, in which case it writes a warning instead. Create two independent limiters and prove their counts don't interfere. Then write Retry-Command -Action { ... } -Times 3, which retries a failing script block (use try/catch from lesson 10).
        """)
    }
}
