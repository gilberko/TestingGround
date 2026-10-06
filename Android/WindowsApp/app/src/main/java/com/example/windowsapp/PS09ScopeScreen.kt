package com.example.windowsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PS09ScopeScreen(navController: NavController) {
    PsLessonScreen(navController, "09 VARIABLE SCOPE AND LIFETIME") {

        PsSection("WHAT IS SCOPE?")
        PsText("""
            A SCOPE is a container that holds variables, functions and aliases. Scope answers two questions:
            • VISIBILITY — from where can a variable be read or changed?
            • LIFETIME — when is it created, and when is it destroyed?

            PowerShell creates a new CHILD scope every time you run a script or call a function (or a script block). When that script/function finishes, its scope — and every variable created in it — is destroyed.
        """)

        PsSection("THE SCOPE TREE")
        PsCode("""
            Global scope        ← your interactive session (the prompt)
            ├── Script scope    ← created when .\Backup.ps1 runs
            │   └── Function    ← created on each call to Get-Data
            │       └── Script block / nested function call
            └── Module scope    ← each loaded module has its own,
                                  attached to the global scope
        """)
        PsText("""
            Named scopes you can refer to:
            • Global — the session itself. Lives until the PowerShell window closes.
            • Script — the nearest script file (or module) being run. Lives until the script ends.
            • Local — whatever scope you are in RIGHT NOW (an alias for the current scope).
            • Private — not a place, but a VISIBILITY option: a private variable can't be seen by child scopes.
        """)

        PsSection("RULE 1: CHILD SCOPES CAN READ PARENT VARIABLES")
        PsCode("""
            §color = 'blue'

            function Show-Color {
                "Inside: §color"      # reads the parent's variable
            }

            Show-Color
        """)
        PsOutput("Inside: blue")
        PsText("When code reads a variable that doesn't exist in the current scope, PowerShell looks in the parent, then its parent, up to global. This is DYNAMIC scoping — based on who CALLED you at run time, not where the code was written.")

        PsSection("RULE 2: ASSIGNING CREATES A LOCAL COPY")
        PsCode("""
            §count = 10

            function Add-One {
                §count = §count + 1    # reads parent 10, creates LOCAL §count = 11
                "Inside: §count"
            }

            Add-One
            "Outside: §count"
        """)
        PsOutput("""
            Inside: 11
            Outside: 10
        """)
        PsText("""
            This is the classic surprise. Assigning to a variable inside a function does NOT change the caller's variable — it creates a NEW variable in the function's scope that "shadows" the outer one. When Add-One returns, its §count is destroyed and the outer §count is still 10.

            This is a FEATURE: functions can't accidentally clobber the caller's variables. The right way to give data back is to RETURN it (lesson 7):  §count = Add-One §count.
        """)

        PsSection("SCOPE MODIFIERS: §global:  §script:  §local:")
        PsCode("""
            §global:LogPath = 'C:\Temp\app.log'   # visible everywhere, survives
                                                  # after the script ends

            §script:Counter = 0                   # shared by all functions
            function Step {                       # in this script file
                §script:Counter++
                "Step §script:Counter"
            }
            Step; Step; Step

            function Test-Local {
                §local:x = 5                      # same as plain §x = 5
            }
        """)
        PsOutput("""
            Step 1
            Step 2
            Step 3
        """)
        PsText("""
            • §script:Counter++ changes the SCRIPT-level variable, so the count persists between calls to Step. This is the cleanest way to keep shared state in a script or module.
            • §global: should be rare — global variables survive after your script ends, clutter the user's session and can collide with other scripts. Use it only when you deliberately want to leave something behind.
            • §local: is the default and mostly used for clarity.
            • Get-Variable / Set-Variable accept -Scope with a name or a NUMBER: 0 = current, 1 = parent, 2 = grandparent.
        """)

        PsSection("PRIVATE VARIABLES")
        PsCode("""
            §private:secret = 'p@ss'

            function Peek {
                "Secret is: [§secret]"
            }
            Peek
        """)
        PsOutput("Secret is: []")
        PsText("A private variable is visible ONLY in the scope that created it. Child scopes don't see it, even when reading — the lookup skips it and finds nothing.")

        PsSection("SCRIPTS: RUN vs DOT-SOURCE")
        PsCode("""
            # Settings.ps1 contains:  §server = 'db01'; function Connect-Db { ... }

            .\Settings.ps1        # runs in a NEW script scope
            §server               # → nothing: the variable died with the scope

            . .\Settings.ps1      # DOT-SOURCE: runs in the CURRENT scope
            §server               # → db01
            Connect-Db            # function is available too
        """)
        PsText("""
            The dot-and-space prefix  .  runs a script in the CALLER'S scope, so its variables and functions remain afterwards. This is how profile scripts and "library" scripts used to be loaded before modules. It also works for functions: . MyFunction runs it in the current scope.
        """)

        PsSection("MODULES HAVE THEIR OWN SCOPE")
        PsCode("""
            # Counter.psm1
            §script:calls = 0           # module-level state

            function Invoke-Counted {
                §script:calls++
                "Called §script:calls times"
            }
            Export-ModuleMember -Function Invoke-Counted
        """)
        PsCode("""
            Import-Module .\Counter.psm1
            Invoke-Counted        # Called 1 times
            Invoke-Counted        # Called 2 times
            §calls                # nothing — module state is hidden
        """)
        PsText("""
            Inside a module, §script: refers to the MODULE'S scope. That scope lives as long as the module is loaded, so it can hold state between calls (connection objects, caches, counters) while staying invisible to the user's session. Remove-Module destroys it.

            Module functions also do NOT see the caller's variables — a module's parent is the global scope, not the script that called it. This keeps modules predictable.
        """)

        PsSection("LIFETIME SUMMARY")
        PsCode("""
            Created in…          Lives until…
            ───────────────────────────────────────────────
            Prompt (global)      PowerShell window closes
            Script               script finishes (unless dot-sourced)
            Function / block     function returns
            Module (§script:)    module removed / session ends
            Remove-Variable x    immediately (explicit delete)
        """)
        PsText("""
            Destroying a variable only removes the NAME. The .NET object it pointed to is freed later by the .NET garbage collector, once nothing references it. Objects holding OS resources (files, connections) should be closed explicitly with .Close() / .Dispose(), ideally in a finally block (lesson 10).
        """)

        PsSection("SCRIPT BLOCKS AND SCOPE")
        PsCode("""
            §x = 1
            & { §x = 2 }      # & runs the block in a CHILD scope
            §x                # 1

            . { §x = 2 }      # . runs it in the CURRENT scope
            §x                # 2
        """)
        PsText("The same call-vs-dot-source rule applies to script blocks — important for lesson 11 (closures).")

        PsSection("CAVEATS")
        PsText("""
            • Changing a variable's CONTENTS isn't the same as assigning it. If the parent has  §list = [System.Collections.Generic.List[int]]::new(), a child calling  §list.Add(1)  modifies the SAME object — no new variable is created. Only assignment (=) creates a local copy.
            • Set-StrictMode -Version Latest makes reading an unset variable an error, which catches many scope bugs.
            • ForEach-Object -Parallel and Start-Job run in SEPARATE runspaces — no parent scope at all. Use §using:var to pass values in.
        """)

        PsSection("CONNECTIONS")
        PsText("Lesson 7's functions each get a child scope; lesson 8's Export-ModuleMember and module scope explain why private helpers stay hidden. Lesson 11 shows how GetNewClosure() captures variables so a script block keeps them after the scope dies.")

        PsExercise("""
            Predict the output, then run it:
              §n = 1
              function A { §n = 2; B }
              function B { "B sees §n" }
              A
              "Top sees §n"
            Explain why B sees 2 (dynamic scope) but the top level sees 1.
        """)
        PsChallenge("""
            Write a script TaskLog.ps1 with a §script:Log list and functions Add-LogEntry (adds a timestamped string) and Show-Log. Call Add-LogEntry three times and Show-Log once inside the script. Then (a) run it normally and check whether §Log exists afterwards, (b) dot-source it and check again. Finally, convert it into a module that keeps the log between calls but hides the variable.
        """)
    }
}
