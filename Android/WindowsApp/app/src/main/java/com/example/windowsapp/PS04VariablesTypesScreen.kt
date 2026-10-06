package com.example.windowsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PS04VariablesTypesScreen(navController: NavController) {
    PsLessonScreen(navController, "04 VARIABLES AND DATA TYPES") {

        PsSection("WHAT IS A VARIABLE?")
        PsText("""
            A variable is a named slot that holds a value. In PowerShell every value is a .NET OBJECT, so a variable always holds an object of some .NET type — a System.String, a System.Int32, a System.Diagnostics.Process…

            Variables start with § and are created simply by assigning to them. There is no declaration keyword (no var, let, dim). Names are case-insensitive: §Name and §name are the same variable.
        """)
        PsCode("""
            §greeting = 'Hello'
            §count    = 42
            §greeting                 # typing a variable prints it
            "§greeting, world (§count)"
        """)
        PsOutput("""
            Hello
            Hello, world (42)
        """)

        PsSection("DYNAMIC TYPING")
        PsText("""
            By default a variable can hold ANY type, and its type can change. PowerShell picks the type from the value you assign.
        """)
        PsCode("""
            §x = 10          ; §x.GetType().Name     # Int32
            §x = 10.5        ; §x.GetType().Name     # Double
            §x = 'ten'       ; §x.GetType().Name     # String
            §x = Get-Date    ; §x.GetType().Name     # DateTime
        """)
        PsOutput("""
            Int32
            Double
            String
            DateTime
        """)
        PsText("""
            Every object has a .GetType() method (inherited from System.Object). .Name gives the short name, .FullName gives e.g. System.Int32.

            PowerShell also CONVERTS types automatically when an operator needs it. The LEFT operand decides:
        """)
        PsCode("""
            '5' + 3      # '53'   — left is string → 3 becomes '3'
            5 + '3'      # 8      — left is int → '3' becomes 3
            '5' * 3      # '555'  — string repetition
        """)

        PsSection("STRINGS")
        PsCode("""
            §name = 'World'
            'Hello §name'          # single quotes: literal → Hello §name
            "Hello §name"          # double quotes: expand → Hello World
            "Total: §(2 + 3)"      # subexpression → Total: 5
            "Tab:`tTab, newline:`n"  # backtick is the escape character
            "Path is C:\Temp"      # backslash is NOT special in PowerShell

            # Here-string: multi-line, quotes need no escaping
            §text = @"
            Name: §name
            Say "hi"
            "@

            §s = 'PowerShell'
            §s.Length              # 10
            §s.ToUpper()           # POWERSHELL
            §s.Substring(0, 5)     # Power
            §s.Contains('Shell')   # True
            §s -replace 'Shell', 'Tools'   # PowerTools
            'a,b,c' -split ','     # array: a  b  c
            'x' * 3                # xxx
            '{0} has {1} chars' -f §s, §s.Length   # format operator
        """)
        PsText("""
            The ESCAPE character is the BACKTICK ` (not backslash): `n newline, `t tab, `" literal quote, `§ literal dollar. This choice keeps Windows paths like C:\Temp working without doubling backslashes. In a here-string the closing "@ must be at the start of a line.
        """)

        PsSection("NUMBERS")
        PsCode("""
            §i = 42              # [int]    System.Int32
            §big = 3000000000    # [long]   — too big for Int32, auto-widened
            §d = 3.14            # [double] System.Double
            §m = 19.99d          # [decimal] — exact base-10, use for money
            §hex = 0xFF          # 255
            §size = 5GB          # 5368709120  (KB, MB, GB, TB, PB suffixes)

            7 / 2                # 3.5  — division produces double if needed
            [int](7 / 2)         # 4    — banker's rounding! (to even)
            [math]::Floor(7 / 2) # 3
            7 % 2                # 1    — remainder
            [math]::Pow(2, 10)   # 1024
            0.1 + 0.2            # 0.3 displayed, really 0.30000000000000004
        """)
        PsText("""
            Note the caveat on [int] conversion: PowerShell (.NET) rounds half to EVEN — [int]2.5 is 2, [int]3.5 is 4. Use [math]::Round(x, [MidpointRounding]::AwayFromZero) or [math]::Floor / Ceiling when you need a specific rule.
        """)

        PsSection("BOOLEANS AND §null")
        PsCode("""
            §ok = §true
            §done = §false
            §nothing = §null

            # Truthiness: these are all FALSE in a condition
            [bool]0; [bool]''; [bool]§null; [bool]@()
            # These are TRUE
            [bool]1; [bool]'false'; [bool]'0'; [bool]@(1)
        """)
        PsText("""
            §true, §false and §null are AUTOMATIC VARIABLES. Watch out: any NON-EMPTY string is true — even 'false' and '0'.

            §null means "no value". A command that outputs nothing gives §null. When comparing, put §null on the LEFT:  if (§null -eq §x)  — because if §x is an array,  §x -eq §null  FILTERS the array instead of returning a single bool.
        """)

        PsSection("ARRAYS")
        PsCode("""
            §colors = 'red', 'green', 'blue'    # comma makes an array
            §nums   = 1..5                      # range: 1 2 3 4 5
            §empty  = @()                       # empty array
            §one    = @('single')               # force array of 1

            §colors[0]          # red
            §colors[-1]         # blue  (negative = from the end)
            §colors[0..1]       # red green (slice)
            §colors.Count       # 3
            §colors += 'black'  # "append" — actually creates a NEW array
            §colors -contains 'green'   # True
            'green' -in §colors         # True
        """)
        PsText("""
            PowerShell arrays are System.Object[] — fixed-size .NET arrays. += copies the whole array every time, which is slow in big loops. For growing collections use a List:
        """)
        PsCode("""
            §list = [System.Collections.Generic.List[string]]::new()
            §list.Add('a'); §list.Add('b')
            §list.Count          # 2
        """)
        PsText("""
            A command that returns ONE object gives you that object, not a 1-element array; returning several gives an array. Wrap in @( ) when you always want an array:  §procs = @(Get-Process -Name notepad).
        """)

        PsSection("HASH TABLES")
        PsCode("""
            §person = @{
                Name = 'Ada'
                Age  = 36
                Langs = 'PowerShell', 'C#'
            }
            §person.Name            # Ada
            §person['Age']          # 36
            §person.City = 'London' # add a key
            §person.Remove('Age')
            §person.ContainsKey('Name')   # True
            §person.Keys            # Name, Langs, City (order not guaranteed)

            §ordered = [ordered]@{ First = 1; Second = 2 }   # keeps insertion order
        """)
        PsText("""
            A hash table (System.Collections.Hashtable) maps KEYS to VALUES. Keys are case-insensitive by default. Use [ordered] when the key order matters, for example when you will export to CSV/JSON.
        """)

        PsSection("OBJECTS")
        PsCode("""
            §file = Get-Item C:\Windows\notepad.exe
            §file.Length            # size in bytes
            §file.LastWriteTime     # a DateTime object
            §file.LastWriteTime.Year
            §file.VersionInfo.FileVersion

            # Your own object (lesson 17 goes deeper):
            §server = [PSCustomObject]@{
                Name = 'web01'
                IP   = '10.0.0.5'
                Up   = §true
            }
            §server.Name
        """)
        PsText("""
            Properties can themselves be objects (LastWriteTime is a DateTime with its own .Year), so you can "dot" your way into any depth. [PSCustomObject] turns a hash table into an object with real properties — it displays as a table and works with Select-Object, Export-Csv and the rest of the pipeline.
        """)

        PsSection("EXPLICIT TYPES")
        PsCode("""
            [int]§port = 8080
            [string]§hostName = 'localhost'
            [bool]§enabled = §true
            [datetime]§when = '2026-10-06'      # string converted to DateTime
            [int[]]§ports = 80, 443
            [System.IO.FileInfo]§f = 'C:\Windows\notepad.exe'

            §port = '9090'     # OK — converted to 9090
            §port = 'abc'      # ERROR: Cannot convert value "abc" to type "System.Int32"
        """)
        PsText("""
            Putting a type in front of the variable on assignment makes it a TYPE-CONSTRAINED variable. Every later assignment is converted to that type, or fails. This catches bugs early. Putting a type in front of a VALUE ([int]'42') is a one-off CAST.

            Common type accelerators (short names): [int] [long] [double] [decimal] [string] [char] [bool] [datetime] [timespan] [array] [hashtable] [regex] [xml] [ipaddress] [guid] [pscustomobject].
        """)

        PsSection("USEFUL AUTOMATIC VARIABLES")
        PsText("""
            §_ / §PSItem — current pipeline object
            §PSVersionTable — version information
            §PID — this process's id
            §HOME — user profile folder
            §PWD — current location
            §env:NAME — environment variables (§env:PATH, §env:COMPUTERNAME)
            §LASTEXITCODE — exit code of the last native program
            §? — True if the last command succeeded
            §Error — list of recent errors (lesson 10)
        """)

        PsSection("CAVEATS")
        PsText("""
            • Variable names are case-insensitive, and many automatic names (§input, §args, §host, §PID, §Error, §_) are reserved. Assigning to §host fails, and §input means something special inside functions.
            • Strict mode helps catch typos:  Set-StrictMode -Version Latest  makes reading an undefined variable an error instead of silently returning §null.
            • ' (single) vs " (double) quotes is the #1 beginner bug — remember single quotes never expand.
        """)

        PsSection("CONNECTIONS")
        PsText("Lesson 2's param([string]§Name) was an explicit type. Lesson 3 showed objects in the pipeline — those objects are the same values you can now store in variables.")

        PsExercise("""
            Create variables for your name (string), age (int), height in meters (double) and a list of three hobbies (array). Print a sentence using all of them inside ONE double-quoted string. Then print §hobbies.Count and the type of each variable with .GetType().FullName.
        """)
        PsChallenge("""
            Build an [ordered] hash table describing your computer with keys: Name (§env:COMPUTERNAME), OS ((Get-CimInstance Win32_OperatingSystem).Caption), Cores ([Environment]::ProcessorCount) and FreeGB on C: (use (Get-PSDrive C).Free, divided by 1GB and rounded to 1 decimal). Convert it to [PSCustomObject] and display it. What changes in the display compared with the hash table?
        """)
    }
}
