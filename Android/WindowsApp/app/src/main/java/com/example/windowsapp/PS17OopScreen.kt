package com.example.windowsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PS17OopScreen(navController: NavController) {
    PsLessonScreen(navController, "17 OBJECT-ORIENTED PROGRAMMING") {

        PsSection("OOP IN ONE PARAGRAPH")
        PsText("""
            Object-oriented programming organizes code around OBJECTS that bundle DATA (properties) with BEHAVIOR (methods). A CLASS is the blueprint; an object is an INSTANCE of it. The key ideas:
            • ENCAPSULATION — keep related data and the code that operates on it together.
            • INHERITANCE — a class can extend another, reusing and specializing it.
            • POLYMORPHISM — code written for a base type works with any derived type.

            You have been USING objects since lesson 1 (every Process, Service and FileInfo is one). This lesson is about CREATING your own, in increasing order of power: hash table → [PSCustomObject] → Add-Member → class.
        """)

        PsSection("STEP 1: [PSCustomObject]")
        PsCode("""
            §server = [PSCustomObject]@{
                Name     = 'web01'
                IP       = '10.0.0.5'
                Role     = 'Web'
                MemoryGB = 16
            }

            §server
            §server.Name
            §server.MemoryGB = 32               # properties are writable
            §server | Get-Member
        """)
        PsOutput("""
            Name  IP       Role MemoryGB
            ----  --       ---- --------
            web01 10.0.0.5 Web        16

               TypeName: System.Management.Automation.PSCustomObject
            Name     MemberType   Definition
            ----     ----------   ----------
            IP       NoteProperty string IP=10.0.0.5
            MemoryGB NoteProperty int MemoryGB=32
            Name     NoteProperty string Name=web01
            Role     NoteProperty string Role=Web
        """)
        PsText("""
            [PSCustomObject]@{...} is the quickest way to make a real object: properties keep the order you wrote them, it displays as a table, and it works with Sort/Where/Group/Export-Csv/ConvertTo-Json. Its properties are NoteProperties — untyped values. This is perfect for RETURNING DATA from functions (lesson 7) and is what most PowerShell code uses.
        """)
        PsCode("""
            # A collection of objects = a "table" you can query
            §servers = @(
                [PSCustomObject]@{ Name = 'web01'; Role = 'Web'; MemoryGB = 16 }
                [PSCustomObject]@{ Name = 'web02'; Role = 'Web'; MemoryGB = 8 }
                [PSCustomObject]@{ Name = 'db01';  Role = 'DB';  MemoryGB = 64 }
            )
            §servers | Where-Object Role -eq 'Web' | Measure-Object MemoryGB -Sum
        """)

        PsSection("STEP 2: ADDING BEHAVIOR WITH Add-Member")
        PsCode("""
            §server | Add-Member -MemberType ScriptMethod -Name Describe -Value {
                "§(§this.Name) (§(§this.Role)) at §(§this.IP)"
            }
            §server | Add-Member -MemberType ScriptProperty -Name MemoryMB -Value {
                §this.MemoryGB * 1024
            }
            §server.Describe()
            §server.MemoryMB
        """)
        PsOutput("""
            web01 (Web) at 10.0.0.5
            32768
        """)
        PsText("""
            Add-Member attaches ETS members to an existing object: ScriptMethod (a method written as a script block), ScriptProperty (a computed property), NoteProperty, AliasProperty. Inside, §this means "the object the member is called on". This works but is ad hoc: each object must be decorated individually, and nothing enforces types. Classes solve that.
        """)

        PsSection("STEP 3: CLASSES")
        PsCode("""
            class Server {
                # Properties (typed)
                [string]§Name
                [string]§IP
                [int]§MemoryGB
                hidden [datetime]§Created

                # Constructor
                Server([string]§name, [string]§ip, [int]§memoryGB) {
                    §this.Name     = §name
                    §this.IP       = §ip
                    §this.MemoryGB = §memoryGB
                    §this.Created  = Get-Date
                }

                # Methods
                [string] Describe() {
                    return "§(§this.Name) at §(§this.IP) with §(§this.MemoryGB) GB"
                }

                [bool] Ping() {
                    return Test-Connection §this.IP -Count 1 -Quiet
                }
            }

            §s = [Server]::new('web01', '10.0.0.5', 16)
            §s.Describe()
            §s.MemoryGB = 'lots'     # ERROR: cannot convert "lots" to System.Int32
            §s -is [Server]          # True
        """)
        PsText("""
            Available since PowerShell 5.0. A class defines:
            • Typed properties — assignments are converted or rejected, like the constrained variables of lesson 4. hidden hides a member from Get-Member and default display (it is still accessible).
            • Constructors — a method with the CLASS NAME and no return type, run by [Server]::new(...) (or New-Object Server -ArgumentList ...).
            • Methods — must declare a return type ([string], [bool], [void]) and must use return explicitly. Unlike functions, stray output inside a method is DISCARDED — only return produces a value.
            • §this — the current instance; it's required to access properties inside methods.
        """)

        PsSection("INHERITANCE")
        PsCode("""
            class WebServer : Server {
                [int]§Port = 443

                WebServer([string]§name, [string]§ip, [int]§mem, [int]§port) : base(§name, §ip, §mem) {
                    §this.Port = §port
                }

                # Override the base method
                [string] Describe() {
                    return ([Server]§this).Describe() + " serving HTTPS on §(§this.Port)"
                }
            }

            §w = [WebServer]::new('web02', '10.0.0.6', 8, 8443)
            §w.Describe()
            §w -is [Server]          # True — a WebServer IS a Server

            # Polymorphism: one loop, different behavior per type
            [Server[]]§all = [Server]::new('db01', '10.0.0.9', 64), §w
            §all | ForEach-Object { §_.Describe() }
        """)
        PsOutput("""
            web02 at 10.0.0.6 with 8 GB serving HTTPS on 8443
            True
            db01 at 10.0.0.9 with 64 GB
            web02 at 10.0.0.6 with 8 GB serving HTTPS on 8443
        """)
        PsText("""
            • class Child : Parent  inherits all properties and methods.
            • : base(args) calls the parent constructor.
            • Defining a method with the same signature OVERRIDES it. To call the parent's version, cast §this to the base type: ([Server]§this).Describe().
            • A class can also implement .NET INTERFACES: class Version2 : System.IComparable { [int] CompareTo([object]§o) { ... } }.
        """)

        PsSection("STATIC MEMBERS")
        PsCode("""
            class Counter {
                static [int]§Instances = 0
                [int]§Id

                Counter() {
                    [Counter]::Instances++
                    §this.Id = [Counter]::Instances
                }

                static [Counter] Create() { return [Counter]::new() }
            }

            §a = [Counter]::new()
            §b = [Counter]::Create()
            [Counter]::Instances      # 2
            §b.Id                     # 2
        """)
        PsText("STATIC members belong to the CLASS, not to an instance — shared by all objects and accessed with [ClassName]::Member, exactly like .NET's [Math]::PI or [DateTime]::Now (lesson 22).")

        PsSection("COMPARING WITH C#")
        PsCode("""
            Feature                C#                 PowerShell class
            ──────────────────────────────────────────────────────────
            Properties / fields    yes                yes (all public)
            private/protected      yes                no — only 'hidden'
            Constructors           yes, overloads     yes, overloads
            Inheritance            single + ifaces    single + ifaces
            Abstract / sealed      yes                no (convention only)
            Get/set accessors      yes                no (use methods or
                                                      Update-TypeData)
            Generics (define)      yes                no (can USE them)
            Static members         yes                yes
            Enums                  yes                yes (lesson 18)
            Events, delegates      yes                limited
            Compilation            compile time       parsed when script
                                                      runs/loads
        """)
        PsText("""
            PowerShell classes compile to real .NET types in memory, so they interoperate with .NET (they can be passed to .NET methods, implement interfaces, be used in generic collections). They are deliberately simpler than C#: everything is public, and there are no access modifiers besides hidden. For complex OO designs, you can even write C# and load it with Add-Type (lesson 22).
        """)

        PsSection("CAVEATS")
        PsText("""
            • A class can't be redefined in the same session once used in some cases — start a new session while developing if changes don't take effect.
            • Types used in a class (e.g. a class from a module) must be available when the script is PARSED: use  using module X  / using namespace at the top.
            • Classes are not exported by Import-Module; use  using module MyModule  (lesson 8).
            • Methods discard non-returned output and need explicit return — opposite to functions.
        """)

        PsSection("CONNECTIONS")
        PsText("Objects are what the pipeline (lesson 16) carries; PSCustomObject was introduced in lesson 4. Closures with state (lesson 11) are a lightweight alternative to classes. Lesson 18 focuses on class and enum syntax and when to choose a class over a PSCustomObject.")

        PsExercise("""
            Create a [PSCustomObject] for three books (Title, Author, Year, Pages). Add a ScriptProperty Age (current year minus Year) to each with Add-Member. Then sort by Age and show only Title and Age.
        """)
        PsChallenge("""
            Build a class hierarchy: base class Shape with an abstract-by-convention method [double] Area() that throws "Not implemented", and classes Circle (Radius), Rectangle (Width, Height) and Square : Rectangle (one Side). Add a static [Shape[]] Parse([string]§csv) that creates shapes from lines like "circle,2" or "rect,3,4". Print each shape's type and area, sorted by area.
        """)
    }
}
