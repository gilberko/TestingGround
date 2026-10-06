package com.example.windowsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PS18ClassesEnumsScreen(navController: NavController) {
    PsLessonScreen(navController, "18 CLASSES AND ENUMS") {

        PsSection("FOCUS OF THIS LESSON")
        PsText("""
            Lesson 17 introduced OOP concepts. This lesson is a focused tour of the SYNTAX: how to declare classes and enums, how instantiation and constructors really work, how methods behave differently from functions, and — most practically — when a class is worth it versus a simple [PSCustomObject].
        """)

        PsSection("CLASS ANATOMY")
        PsCode("""
            class Person {
                # ── properties ──────────────────────
                [string]§FirstName
                [string]§LastName
                [int]§Age = 0                    # default value
                static [int]§Count = 0

                # ── constructors ────────────────────
                Person() {                       # parameterless
                    [Person]::Count++
                }
                Person([string]§first, [string]§last) {
                    §this.FirstName = §first
                    §this.LastName  = §last
                    [Person]::Count++
                }

                # ── methods ─────────────────────────
                [string] FullName() {
                    return "§(§this.FirstName) §(§this.LastName)"
                }
                [void] Birthday() {
                    §this.Age++
                }
                [string] ToString() {            # override System.Object.ToString
                    return §this.FullName()
                }
            }
        """)

        PsSection("INSTANTIATION — FOUR WAYS")
        PsCode("""
            §p1 = [Person]::new()                         # parameterless ctor
            §p2 = [Person]::new('Ada', 'Lovelace')        # 2-arg ctor
            §p3 = New-Object Person -ArgumentList 'Alan', 'Turing'
            §p4 = [Person]@{ FirstName = 'Grace'; LastName = 'Hopper'; Age = 85 }

            §p2.FullName()      # Ada Lovelace
            "Hello §p2"         # uses ToString() → Hello Ada Lovelace
            [Person]::Count     # 4
        """)
        PsText("""
            • ::new(args) picks the constructor whose parameter count/types match — this is OVERLOADING.
            • New-Object is the older cmdlet form; slower but equivalent.
            • [Person]@{...} — CAST A HASH TABLE: PowerShell calls the PARAMETERLESS constructor, then sets each key as a property. Handy for many properties; requires a parameterless constructor.
            • If you define NO constructor, PowerShell supplies a default parameterless one. As soon as you define ANY constructor, the default disappears — define Person() yourself if you still want it.
        """)

        PsSection("SHARED CONSTRUCTOR LOGIC")
        PsCode("""
            class Connection {
                [string]§Server
                [int]§Port
                [int]§TimeoutSec

                Connection([string]§server) { §this.Init(§server, 443, 30) }
                Connection([string]§server, [int]§port) { §this.Init(§server, §port, 30) }

                hidden [void] Init([string]§s, [int]§p, [int]§t) {
                    if (§p -lt 1 -or §p -gt 65535) { throw "Invalid port §p" }
                    §this.Server = §s; §this.Port = §p; §this.TimeoutSec = §t
                }
            }
            [Connection]::new('db01', 5432)
        """)
        PsText("PowerShell constructors can't call each other directly (no C#-style : this(...)), so shared setup goes into a hidden helper method. Throwing from a constructor aborts creation — the variable stays unassigned.")

        PsSection("HOW METHODS OPERATE")
        PsCode("""
            class Demo {
                [int] Bad() {
                    'this text is DISCARDED'      # not returned!
                    Write-Output 'also discarded'
                    return 42
                }
                [int[]] Many() { return 1, 2, 3 }
                [void] Log([string]§msg) {
                    Write-Host "LOG: §msg"         # host output still shows
                }
                static [double] Area([double]§r) { return [math]::PI * §r * §r }

                # Overloads by parameter count/type
                [string] Fmt([int]§n)    { return "int §n" }
                [string] Fmt([string]§s) { return "string §s" }
            }

            [Demo]::new().Bad()         # 42 only
            [Demo]::Area(2)             # 12.566…
            [Demo]::new().Fmt(5)        # int 5
            [Demo]::new().Fmt('x')      # string x
        """)
        PsText("""
            Methods follow .NET rules, not PowerShell function rules:
            • The declared return type is enforced and the value is converted to it.
            • ONLY return produces output — everything else in the output stream is thrown away (Write-Host/Write-Verbose/Write-Warning still display).
            • Variables used inside must be parameters, §this members, or explicitly scoped (§script:x) — the parser REJECTS a method that reads a variable it never assigned.
            • Methods are called with parentheses and commas: §obj.Fmt(5) — the opposite of function calls.
        """)

        PsSection("ENUMS")
        PsCode("""
            enum LogLevel {
                Debug                 # 0
                Info                  # 1
                Warning               # 2
                Error = 10            # explicit value
                Critical              # 11
            }

            [LogLevel]::Warning
            [LogLevel]'Error'                      # string → enum
            [int][LogLevel]::Critical              # 11
            [LogLevel]::Info -lt [LogLevel]::Error # True — compares values
            [enum]::GetNames([LogLevel])           # all names

            function Write-Log {
                param([string]§Message, [LogLevel]§Level = 'Info')
                if (§Level -ge [LogLevel]::Warning) {
                    Write-Warning "[§Level] §Message"
                } else {
                    "[§Level] §Message"
                }
            }
            Write-Log 'Disk almost full' -Level Warning
            Write-Log 'Oops' -Level Fatal          # ERROR: not a valid LogLevel
        """)
        PsText("""
            An ENUM is a named set of integer constants. Typing a parameter as an enum gives free VALIDATION (invalid names are rejected, listing the valid ones) and TAB COMPLETION — like ValidateSet but reusable as a type. Enums compare by numeric value, so severities can be ordered.
        """)
        PsCode("""
            # Flags enums: combinable bit values
            [Flags()] enum Permission {
                None    = 0
                Read    = 1
                Write   = 2
                Execute = 4
            }
            §p = [Permission]'Read, Write'
            §p                              # Read, Write
            §p.HasFlag([Permission]::Write) # True
            [int]§p                         # 3
        """)
        PsText("[Flags()] makes an enum a BIT FIELD — the same pattern as .NET's [System.IO.FileAttributes] or Win32 access masks (compare the ACCESS_MASK values on the DACL AND SDDL screen).")

        PsSection("WHEN TO DEFINE A CLASS vs USE PSCustomObject")
        PsCode("""
            Use [PSCustomObject] when…        Use a class when…
            ───────────────────────────────────────────────────────────
            returning data from a function    you need typed, validated
                                              properties
            shape is simple / one-off         many objects share the
                                              same shape AND behavior
            output goes to CSV/JSON/screen    methods belong with the data
            you want minimal code             you need inheritance or a
                                              .NET interface
            script must run on PS 3/4         you want [Type] parameters
                                              (param([Server]§s))
                                              state + invariants must be
                                              protected (constructor checks)
        """)
        PsText("""
            In practice: most scripts return PSCustomObjects. Reach for a class when you catch yourself writing helper functions that all take the same "kind" of object, or validating the same properties in several places — that's data and behavior asking to live together.

            A middle ground: [PSCustomObject] with a PSTypeName gives your objects a custom type name (useful for formatting files and for [PSTypeName('My.Server')] parameter checks) without a full class:
              [PSCustomObject]@{ PSTypeName = 'My.Server'; Name = 'web01' }
        """)

        PsSection("CLASSES IN SCRIPTS AND MODULES")
        PsCode("""
            # MyTypes.psm1 defines: class Server { ... }  enum Env { Dev; Prod }

            using module .\MyTypes.psm1      # MUST be the first statement

            §s = [Server]::new()
            [Env]::Prod
        """)
        PsText("""
            Classes and enums are resolved when the script is PARSED, before it runs. That's why Import-Module (a runtime command) doesn't make module classes visible to your script — the parser needs  using module  at the top. using namespace System.Text  similarly lets you write [StringBuilder] instead of [System.Text.StringBuilder].
        """)

        PsSection("CAVEATS")
        PsText("""
            • Redefining a class in the same session can leave old instances with the old type; restart the session when class changes seem ignored.
            • All members are public; hidden only hides from display.
            • Parameterless constructor is lost once you define any other constructor.
            • Classes and enums require PowerShell 5.0 or later.
        """)

        PsSection("CONNECTIONS")
        PsText("Builds directly on lesson 17. Enums give the validation of lesson 7's ValidateSet as a reusable type. Lesson 22 shows .NET's own enums ([System.IO.FileAttributes], [ConsoleColor]) and lesson 23 inspects your classes with reflection.")

        PsExercise("""
            Define enum Priority { Low; Medium; High; Urgent } and class TodoItem with Title, Priority, Done (bool, default false), a constructor (title, priority) and a method Complete(). Create 4 items, complete one, and list the open items sorted by Priority descending.
        """)
        PsChallenge("""
            Create a class BankAccount with a hidden [decimal]§balance, methods Deposit([decimal]), Withdraw([decimal]) that throws on insufficient funds, and [decimal] GetBalance(). Add a static [int] counter for account numbers assigned in the constructor. Derive SavingsAccount that adds an InterestRate and an AddInterest() method. Write a script that simulates a year of monthly deposits and interest, catching an overdraft attempt with try/catch.
        """)
    }
}
