package com.example.windowsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PS16ObjectPipelineScreen(navController: NavController) {
    PsLessonScreen(navController, "16 OBJECTS AND THE OBJECT PIPELINE") {

        PsSection("THE OBJECT MODEL")
        PsText("""
            Every value in PowerShell is an OBJECT, and every object has:
            • a TYPE — the .NET class it was made from (System.IO.FileInfo, System.String, System.Int32…). The type decides which members exist.
            • PROPERTIES — named data: a file's Length, LastWriteTime, Name.
            • METHODS — actions: a file's CopyTo(), Delete(), a string's ToUpper().

            PowerShell adds an extra layer on top of .NET called the EXTENDED TYPE SYSTEM (ETS). It wraps every object in a PSObject and can attach extra members: ALIAS properties (a process's Name → ProcessName), SCRIPT properties computed by code, and NOTE properties (plain values — what PSCustomObject is made of). This is why Get-Member shows member types beyond Property and Method.
        """)

        PsSection("Get-Member — ASK AN OBJECT WHAT IT IS")
        PsCode("""
            Get-Item C:\Windows\notepad.exe | Get-Member
        """)
        PsOutput("""
               TypeName: System.IO.FileInfo

            Name              MemberType     Definition
            ----              ----------     ----------
            CopyTo            Method         System.IO.FileInfo CopyTo(string destFileName)…
            Delete            Method         void Delete()
            Open              Method         System.IO.FileStream Open(System.IO.FileMode mode)…
            Length            Property       long Length {get;}
            LastWriteTime     Property       datetime LastWriteTime {get;set;}
            Extension         Property       string Extension {get;}
            BaseName          ScriptProperty System.Object BaseName {get=…}
            VersionInfo       ScriptProperty System.Object VersionInfo {get=…}
            PSPath            NoteProperty   string PSPath=Microsoft.PowerShell.Core\FileSystem::C:\…
            …
        """)
        PsText("""
            Reading Get-Member:
            • TypeName — the .NET type. Search docs for it or use it in -is / [type] casts.
            • {get;} means read-only, {get;set;} means you can assign to it.
            • ScriptProperty — added by PowerShell's type data (BaseName = name without extension).
            • NoteProperty — PSPath, PSParentPath, PSDrive: added by the PROVIDER so cmdlets know where the item came from.

            Useful forms:  Get-Member -MemberType Property,  Get-Member -Name *Time*,  Get-Member -Static (static members of the type),  and  Get-Member -Force (hidden members).
        """)
        PsCode("""
            # When a pipe carries mixed objects, Get-Member lists each type once
            Get-ChildItem C:\Windows | Get-Member | Select-Object -ExpandProperty TypeName -Unique
        """)
        PsOutput("""
            System.IO.DirectoryInfo
            System.IO.FileInfo
        """)

        PsSection("Select-Object — CHOOSE OR SHAPE PROPERTIES")
        PsCode("""
            Get-Process | Select-Object Name, Id, CPU               # pick columns
            Get-Process | Select-Object -First 5                    # first N objects
            Get-Process | Select-Object -Last 3 -Skip 1
            Get-Process | Select-Object -Property * -First 1        # every property
            Get-Process | Select-Object -ExpandProperty Name        # plain strings
            Get-Process | Select-Object Name -Unique

            # Calculated properties
            Get-ChildItem C:\Windows -File | Select-Object Name,
                @{ Name = 'SizeKB'; Expression = { [math]::Round(§_.Length / 1KB, 1) } },
                @{ N = 'Age'; E = { ((Get-Date) - §_.LastWriteTime).Days } }
        """)
        PsText("""
            Select-Object with property names creates NEW objects (PSCustomObject) containing only those properties — the methods are gone. -ExpandProperty is different: it outputs the property's VALUE itself (strings, not objects with one Name property). Calculated properties (@{Name=…; Expression={…}}, short N/E) add new columns computed per object.
        """)

        PsSection("Where-Object — FILTER")
        PsCode("""
            Get-Service | Where-Object Status -eq 'Running'             # simple syntax
            Get-Service | Where-Object { §_.Status -eq 'Running' -and §_.Name -like 'W*' }
            Get-ChildItem C:\Windows -File | Where-Object Length -gt 1MB
            Get-Process | Where-Object { §_.StartTime -gt (Get-Date).AddHours(-1) }

            # Filter left: let the source command filter when it can
            Get-ChildItem C:\Windows -Filter *.log                      # fast
            Get-ChildItem C:\Windows | Where-Object Extension -eq '.log' # slow
        """)
        PsText("""
            The SIMPLE syntax (Where-Object Prop -op Value) handles one comparison. The SCRIPT-BLOCK syntax allows any logic. PERFORMANCE RULE: "filter left, format right" — use a command's own -Filter/-Name parameters before piping to Where-Object, because filtering at the source avoids creating thousands of objects only to throw them away.
        """)

        PsSection("ForEach-Object — TRANSFORM OR ACT")
        PsCode("""
            Get-ChildItem C:\Windows -File | ForEach-Object { §_.Name.ToUpper() }
            Get-Process | ForEach-Object { "{0} uses {1:N0} MB" -f §_.Name, (§_.WorkingSet64/1MB) }
            1..3 | ForEach-Object { [PSCustomObject]@{ N = §_; Square = §_ * §_ } }
        """)
        PsText("ForEach-Object runs a block per object and outputs whatever the block produces — a transformation (map) step. Lesson 6 compared it with the foreach statement.")

        PsSection("Sort-Object — ORDER")
        PsCode("""
            Get-Process | Sort-Object WorkingSet64 -Descending | Select-Object -First 5
            Get-Service | Sort-Object Status, DisplayName           # multi-key
            Get-ChildItem | Sort-Object { §_.Name.Length }           # computed key
            Get-Process | Sort-Object @{ E = 'CPU'; Descending = §true }, @{ E = 'Name' }
            'b','a','c','a' | Sort-Object -Unique                    # a b c
        """)
        PsText("Sort-Object compares the property's REAL type: numbers sort numerically, dates chronologically. With text tools you'd need sort -n or date parsing. Note that Sort-Object must collect ALL input before emitting anything — it blocks streaming.")

        PsSection("Group-Object — GROUP AND COUNT")
        PsCode("""
            Get-Service | Group-Object Status
            Get-ChildItem C:\Windows -File | Group-Object Extension |
                Sort-Object Count -Descending | Select-Object -First 5 Count, Name

            §byStatus = Get-Service | Group-Object Status -AsHashTable -AsString
            §byStatus['Running'].Count
        """)
        PsOutput("""
            Count Name                      Group
            ----- ----                      -----
              121 Running                   {AudioEndpointBuilder, Audiosrv, …}
              168 Stopped                   {AJRouter, ALG, AppIDSvc, …}
        """)
        PsText("Each group object has Name (the shared value), Count and Group (the original objects, still fully usable). -AsHashTable gives fast lookup by the group key.")

        PsSection("Measure-Object — AGGREGATE")
        PsCode("""
            Get-ChildItem C:\Windows -File | Measure-Object Length -Sum -Average -Maximum
            Get-Content C:\Temp\PSCourse\a.txt | Measure-Object -Line -Word -Character
        """)

        PsSection("WHY OBJECT PIPELINES ARE FUNDAMENTALLY DIFFERENT")
        PsText("Task: the 3 largest files in a folder, modified in the last 30 days, with size in MB.")
        PsCode("""
            # Bash (text)
            find . -maxdepth 1 -type f -mtime -30 -printf '%s %p\n' |
                sort -rn | head -3 |
                awk '{ printf "%.1f MB %s\n", §1/1048576, §2 }'

            # PowerShell (objects)
            Get-ChildItem -File |
                Where-Object LastWriteTime -gt (Get-Date).AddDays(-30) |
                Sort-Object Length -Descending |
                Select-Object -First 3 Name, @{ N = 'MB'; E = { [math]::Round(§_.Length / 1MB, 1) } }
        """)
        PsText("""
            Differences that matter:
            • NO PARSING: in Bash, each stage must agree on a text format (field 1 = size, field 2 = path). A file name with a SPACE breaks awk's §2. PowerShell passes the FileInfo object; Name is always the whole name.
            • TYPES PRESERVED: Length is an Int64 and LastWriteTime a DateTime all the way through, so sorting and date comparison are correct without -n flags or date parsing.
            • SELF-DESCRIBING: Get-Member tells you what's available; with text you guess from the output layout, which can vary by locale or version.
            • LATE FORMATTING: the display is decided only at the end, so the same pipeline can end in Format-Table, Export-Csv, ConvertTo-Json or a variable — with no change to the earlier steps.
            • TRADE-OFF: objects carry more memory and CPU overhead than bytes; for gigantic logs, streaming text tools (or .NET StreamReader, lesson 12) can be faster.
        """)

        PsSection("CAVEATS")
        PsText("""
            • Format-* outputs formatting objects — always last in a pipeline.
            • Select-Object strips methods; keep the original object if you still need them.
            • Collection UNROLLING: a pipeline enumerates arrays, sending elements one by one. To send an array as one object use the unary comma:  ,§array | Get-Member.
            • Sort-Object and Group-Object collect all input first — no streaming.
        """)

        PsSection("CONNECTIONS")
        PsText("This deepens lesson 3's introduction to the pipeline and lesson 2's calculated property. Lesson 17 creates your own objects so they flow through these same cmdlets, and lesson 23 shows how Get-Member relates to .NET reflection.")

        PsExercise("""
            Using one pipeline each:
            1. Group the files in C:\Windows\System32 by extension and show the 10 most common extensions with their total size in MB.
            2. Show the 5 oldest running processes with Name, Id and how long they have been running (StartTime may throw for protected processes — use -ErrorAction or a try in a calculated property).
        """)
        PsChallenge("""
            Run  Get-Process | Get-Member  and pick 3 properties you have never used. Then build a "process report" pipeline that outputs Name, Id, Threads (count), Handles, MemoryMB and Company (from .Company), grouped by Company and sorted by total memory per company. Export to both CSV and JSON, then re-import both and compare which one preserved number types.
        """)
    }
}
