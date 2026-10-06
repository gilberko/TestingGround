package com.example.windowsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PS12FilesScreen(navController: NavController) {
    PsLessonScreen(navController, "12 WORKING WITH FILES AND DIRECTORIES") {

        PsSection("CONCEPTS: ITEMS, PATHS AND PROVIDERS")
        PsText("""
            PowerShell treats the file system as one PROVIDER among several (registry, environment, certificates…). Files and folders are ITEMS, which is why the cmdlets are named *-Item, *-ChildItem and *-Content rather than *-File.

            Get-ChildItem returns real .NET objects: System.IO.FileInfo for files and System.IO.DirectoryInfo for folders — with Length, LastWriteTime, Attributes, Extension, FullName and methods like .CopyTo() or .Delete().

            PATHS can be absolute (C:\Data\a.txt), relative to the current location (.\a.txt, ..\b.txt), or use provider drives (Env:, HKCU:). The current location is §PWD (Set-Location / cd changes it).
        """)

        PsSection("WORKING WITH PATHS")
        PsCode("""
            Join-Path C:\Data 'reports\2026.csv'     # C:\Data\reports\2026.csv
            Split-Path C:\Data\reports\a.csv -Leaf   # a.csv
            Split-Path C:\Data\reports\a.csv -Parent # C:\Data\reports
            Resolve-Path .\notes.txt                 # full path of a relative one
            Test-Path C:\Data                        # True / False
            Test-Path C:\Data -PathType Container    # is it a folder?
            [System.IO.Path]::GetExtension('a.tar.gz')     # .gz
            [System.IO.Path]::GetFileNameWithoutExtension('report.csv')   # report
            [System.IO.Path]::GetTempFileName()      # creates a unique temp file
            §PSScriptRoot                            # folder of the running script
        """)
        PsText("""
            Always build paths with Join-Path instead of string concatenation — it handles the separators for you. §PSScriptRoot is invaluable in scripts: it lets the script find files that sit next to it, no matter what the current directory is.

            Wildcards: * ? [a-z] work in -Path. If a real file name contains [ or ], use -LiteralPath to switch wildcards off.
        """)

        PsSection("ENUMERATING DIRECTORIES")
        PsCode("""
            Get-ChildItem C:\Data                         # files + folders
            Get-ChildItem C:\Data -File                   # files only
            Get-ChildItem C:\Data -Directory              # folders only
            Get-ChildItem C:\Data -Recurse -Filter *.log  # whole tree
            Get-ChildItem C:\Data -Recurse -Depth 1       # limit depth
            Get-ChildItem C:\Data -Hidden                 # hidden items
            Get-ChildItem C:\Data -Include *.csv, *.json -Recurse

            # Folder size
            (Get-ChildItem C:\Data -Recurse -File | Measure-Object Length -Sum).Sum / 1MB
        """)
        PsText("-Filter is passed to the file system API and is much faster than -Include, which filters inside PowerShell afterwards. Measure-Object -Sum adds up a property over all objects.")

        PsSection("READING TEXT FILES")
        PsCode("""
            Get-Content C:\Data\notes.txt                 # array of lines
            Get-Content C:\Data\notes.txt -TotalCount 10  # first 10 lines (head)
            Get-Content C:\Data\app.log -Tail 20          # last 20 (tail)
            Get-Content C:\Data\app.log -Tail 0 -Wait     # follow, like tail -f
            Get-Content C:\Data\notes.txt -Raw            # ONE string, whole file
            (Get-Content C:\Data\notes.txt).Count         # number of lines

            # Search inside files (like grep)
            Select-String -Path C:\Data\*.log -Pattern 'ERROR'
        """)
        PsText("""
            By default Get-Content emits one string PER LINE into the pipeline — great for streaming big files line by line. -Raw returns the whole file as a single string (needed for multi-line regex or ConvertFrom-Json). Select-String returns MatchInfo objects with .Path, .LineNumber and .Line.
        """)

        PsSection("WRITING TEXT FILES")
        PsWarn("The following commands CREATE or OVERWRITE files. Set-Content and Out-File replace existing content without asking. Use a test folder such as C:\\Temp\\PSCourse.")
        PsCode("""
            New-Item C:\Temp\PSCourse -ItemType Directory -Force | Out-Null

            Set-Content  C:\Temp\PSCourse\a.txt 'first line'      # overwrite
            Add-Content  C:\Temp\PSCourse\a.txt 'second line'     # append
            'one','two','three' | Set-Content C:\Temp\PSCourse\b.txt
            Get-Process | Out-File C:\Temp\PSCourse\procs.txt     # as on screen
            'log entry' >> C:\Temp\PSCourse\a.txt                 # append redirect

            Set-Content C:\Temp\PSCourse\u.txt 'Grüße' -Encoding utf8
            Set-Content C:\Temp\PSCourse\x.txt 'x' -NoClobber     # fail if exists (PS 7)
        """)
        PsText("""
            • Set-Content writes the STRING form of objects; Out-File (and >) writes the formatted TABLE view as seen on screen. For data, prefer Export-Csv / ConvertTo-Json.
            • ENCODING caveat: Windows PowerShell 5.1 defaults differ by cmdlet (Out-File / > write UTF-16LE, Set-Content writes ANSI). PowerShell 7 defaults to UTF-8 without BOM everywhere. Specify -Encoding when files must be read by other tools.
        """)

        PsSection("COPY, MOVE, RENAME, DELETE")
        PsWarn("Move-Item, Rename-Item and especially Remove-Item change the file system. Remove-Item does NOT use the Recycle Bin — deleted files are gone. Try every command with -WhatIf first.")
        PsCode("""
            Copy-Item C:\Temp\PSCourse\a.txt C:\Temp\PSCourse\a_copy.txt
            Copy-Item C:\Temp\PSCourse C:\Temp\PSCourse_backup -Recurse
            Move-Item C:\Temp\PSCourse\b.txt C:\Temp\PSCourse\archive\b.txt
            Rename-Item C:\Temp\PSCourse\a_copy.txt a_old.txt

            Remove-Item C:\Temp\PSCourse\a_old.txt -WhatIf     # preview
            Remove-Item C:\Temp\PSCourse\a_old.txt
            Remove-Item C:\Temp\PSCourse_backup -Recurse -Confirm

            # Delete .tmp files older than 7 days
            Get-ChildItem C:\Temp\PSCourse -Filter *.tmp -File |
                Where-Object LastWriteTime -lt (Get-Date).AddDays(-7) |
                Remove-Item -WhatIf
        """)
        PsText("""
            The last pipeline is a typical cleanup task: find → filter by age → delete. Keep -WhatIf until the output lists exactly the files you expect, then remove it. Move-Item fails if the destination folder doesn't exist — create it first with New-Item -ItemType Directory.
        """)

        PsSection("CSV FILES")
        PsCode("""
            Get-Process | Select-Object Name, Id, CPU, WorkingSet64 |
                Export-Csv C:\Temp\PSCourse\procs.csv -NoTypeInformation

            §rows = Import-Csv C:\Temp\PSCourse\procs.csv
            §rows[0].Name
            §rows | Where-Object { [long]§_.WorkingSet64 -gt 200MB } | Select-Object Name

            Import-Csv data.csv -Delimiter ';'          # European-style CSV
            §objs | ConvertTo-Csv -NoTypeInformation    # CSV as strings, no file
        """)
        PsText("""
            Export-Csv turns each object into a row and each property into a column; Import-Csv turns rows back into objects. CAVEAT: everything comes back as STRINGS — cast ([long], [datetime]) before numeric comparisons. -NoTypeInformation removes the "#TYPE" header line that 5.1 adds (7 omits it by default).
        """)

        PsSection("JSON FILES")
        PsCode("""
            §config = [ordered]@{
                Server  = 'db01'
                Port    = 5432
                Enabled = §true
                Tags    = @('prod', 'eu')
            }
            §config | ConvertTo-Json | Set-Content C:\Temp\PSCourse\config.json

            §c = Get-Content C:\Temp\PSCourse\config.json -Raw | ConvertFrom-Json
            §c.Server        # db01
            §c.Port + 1      # 5433 — numbers stay numbers in JSON
            §c.Tags[1]       # eu
        """)
        PsOutput("""
            {
              "Server": "db01",
              "Port": 5432,
              "Enabled": true,
              "Tags": [
                "prod",
                "eu"
              ]
            }
        """)
        PsText("""
            CAVEAT: ConvertTo-Json only serializes 2 levels deep by default; deeper objects become strings like "System.Collections.Hashtable". Use -Depth 10 for nested data. In 7, ConvertFrom-Json -AsHashtable returns hash tables instead of PSCustomObjects.
        """)

        PsSection("BINARY FILES")
        PsCode("""
            # PowerShell 7
            §bytes = Get-Content C:\Windows\notepad.exe -AsByteStream -TotalCount 2
            # Windows PowerShell 5.1
            §bytes = Get-Content C:\Windows\notepad.exe -Encoding Byte -TotalCount 2

            '{0:X2} {1:X2}' -f §bytes[0], §bytes[1]       # 4D 5A = "MZ"

            # .NET is simpler and faster for whole files:
            §all = [System.IO.File]::ReadAllBytes('C:\Windows\notepad.exe')
            §all.Length
            [System.IO.File]::WriteAllBytes('C:\Temp\PSCourse\copy.bin', §all)
        """)
        PsText("""
            Every Windows executable starts with the bytes 4D 5A ("MZ") — the DOS header described in the app's PE FILE STRUCTURE screen. ReadAllBytes loads the whole file into a byte[] — fine for small files; for large files use a FileStream and read in chunks.
        """)

        PsSection(".NET FILE APIs")
        PsCode("""
            [System.IO.File]::ReadAllText('C:\Temp\PSCourse\a.txt')
            [System.IO.File]::ReadAllLines('C:\Temp\PSCourse\a.txt')
            [System.IO.File]::WriteAllText('C:\Temp\PSCourse\n.txt', "hello`n")
            [System.IO.Directory]::GetFiles('C:\Windows', '*.exe')
            [System.IO.Directory]::EnumerateFiles('C:\Windows\System32', '*.dll')

            # Streaming a huge file line by line, fast:
            §reader = [System.IO.StreamReader]::new('C:\Temp\big.log')
            try {
                while (§null -ne (§line = §reader.ReadLine())) {
                    if (§line -like '*ERROR*') { §line }
                }
            }
            finally { §reader.Dispose() }
        """)
        PsText("""
            .NET methods are often 10x faster than the cmdlets for large data because they skip the pipeline. CAVEAT: .NET uses the PROCESS working directory, which is not the same as PowerShell's §PWD — always pass FULL paths to .NET methods (use (Resolve-Path x).Path or Convert-Path).
        """)

        PsSection("CAVEATS")
        PsText("""
            • Remove-Item is permanent — no Recycle Bin.
            • Long paths (> 260 chars) work in PowerShell 7; 5.1 may need the \\?\ prefix.
            • Import-Csv returns strings; ConvertTo-Json defaults to -Depth 2.
            • Files locked by other processes throw IOException — handle with try/catch (lesson 10).
        """)

        PsSection("CONNECTIONS")
        PsText("Uses the pipeline (3), loops (6), error handling (10) and .NET types (previewed here, fully covered in lesson 22). Lesson 13 shows that the registry uses the very same *-Item cmdlets.")

        PsExercise("""
            In C:\Temp\PSCourse create 10 files named file1.txt … file10.txt, each containing its own number (use a for loop and Set-Content). Then list them sorted by name, read file5.txt, and copy all files containing an even number into a subfolder "even".
        """)
        PsChallenge("""
            Write a script that scans a folder recursively and writes a JSON report: total file count, total size in MB, the 10 largest files (FullName, SizeMB), and a count per extension (use Group-Object Extension). Then write a second script that reads the JSON and prints a human-readable summary. Make sure the JSON is complete (-Depth).
        """)
    }
}
