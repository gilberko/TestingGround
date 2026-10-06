package com.example.windowsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PS19RegexScreen(navController: NavController) {
    PsLessonScreen(navController, "19 REGULAR EXPRESSIONS") {

        PsSection("WHAT ARE REGULAR EXPRESSIONS?")
        PsText("""
            A REGULAR EXPRESSION (regex) is a mini-language for describing TEXT PATTERNS: "three digits, a dash, four digits", "a line starting with ERROR", "anything that looks like an IP address". A regex engine searches text for matches of the pattern.

            PowerShell uses the .NET regex engine (System.Text.RegularExpressions) — the same syntax as C#, and very close to Perl/Python. Even though PowerShell is object-based, text never goes away: logs, native command output, config files and user input are all strings, and regex is how you dissect them.

            Wildcards (-like 'a*.txt') are much simpler: * any characters, ? one character. Regex is far more powerful.
        """)

        PsSection("BUILDING BLOCKS")
        PsCode("""
            LITERALS        abc      matches "abc"
            ANY CHARACTER   .        any one char (except newline)
            ESCAPE          \.       a literal dot  (also \\ \( \[ \§ …)

            CHARACTER CLASSES
            [aeiou]   one vowel           [^0-9]   anything BUT a digit
            [a-z]     range               [A-Za-z0-9_]
            \d  digit   \D  non-digit
            \w  word char [A-Za-z0-9_]   \W  non-word
            \s  whitespace (space, tab, newline)   \S  non-space

            QUANTIFIERS (how many of the previous item)
            *      0 or more        +      1 or more        ?   0 or 1
            {3}    exactly 3        {2,5}  2 to 5           {2,} 2 or more
            *? +?  LAZY versions: as few as possible

            ANCHORS (positions, not characters)
            ^   start of string (or line with (?m))
            §   end of string   (or line with (?m))
            \b  word boundary

            GROUPS
            (abc)        capture group 1, 2, …
            (?<name>…)   named capture group
            (?:…)        group without capturing
            a|b          alternation: a OR b
        """)
        PsText("""
            IMPORTANT for PowerShell: write regex patterns in SINGLE quotes. In double quotes, a regex ending anchor followed by text (or §1 in a replacement) would be treated as a PowerShell variable.
        """)

        PsSection("-match: TEST AND CAPTURE")
        PsCode("""
            'Error code: 404' -match '\d+'          # True
            §Matches[0]                              # 404

            'John Smith, age 42' -match '(\w+) (\w+), age (\d+)'
            §Matches[1]     # John
            §Matches[2]     # Smith
            §Matches[3]     # 42

            'user=ada;role=admin' -match 'user=(?<user>\w+);role=(?<role>\w+)'
            §Matches.user   # ada
            §Matches.role   # admin

            'HELLO' -match 'hello'     # True  — case-INSENSITIVE by default
            'HELLO' -cmatch 'hello'    # False — case-sensitive
        """)
        PsText("""
            -match returns True/False and, on success with a SINGLE string on the left, fills the automatic hash table §Matches: [0] is the whole match, [1], [2]… the capture groups, and named groups by name. Named groups make complex patterns self-documenting.

            With an ARRAY on the left, -match FILTERS: it returns the matching elements and does NOT set §Matches.
        """)
        PsCode("""
            'apple','banana','cherry','avocado' -match '^a'    # apple avocado
        """)

        PsSection("-replace: SEARCH AND REPLACE")
        PsCode("""
            'Hello World' -replace 'World', 'PowerShell'     # Hello PowerShell
            '2026-10-06' -replace '(\d+)-(\d+)-(\d+)', '§3/§2/§1'   # 06/10/2026
            'a1b22c333' -replace '\d', ''                    # abc
            '   trim   me  ' -replace '^\s+|\s+§', ''        # 'trim   me'
            'too    many   spaces' -replace '\s+', ' '
            'John Smith' -replace '(?<first>\w+) (?<last>\w+)', '§{last}, §{first}'

            # Script block replacement (PS 7): compute the new text
            'price: 10, 20, 30' -replace '\d+', { [int]§_.Value * 2 }   # price: 20, 40, 60
        """)
        PsText("""
            In the REPLACEMENT string, §1 / §{name} insert captured groups — this is why both the pattern AND the replacement must be in single quotes. -replace replaces ALL matches. -creplace is the case-sensitive version. In PowerShell 7 the replacement can be a script block that receives each Match object as §_.
        """)

        PsSection("-split: SPLIT BY PATTERN")
        PsCode("""
            'a,b;c d' -split '[,; ]'          # a b c d
            'one1two22three' -split '\d+'     # one two three
            'key = value' -split '\s*=\s*'    # key  value
            'a.b.c' -split '.', 0, 'SimpleMatch'   # literal dot split
            'a,b,c,d' -split ',', 2           # a   b,c,d   (max 2 parts)
        """)
        PsText("-split takes a REGEX, so . means ANY character: 'a.b' -split '.' returns empty strings. Escape it ('\\.') or use the SimpleMatch option. The string method 'a.b'.Split('.') is always literal.")

        PsSection("Select-String: GREP FOR POWERSHELL")
        PsCode("""
            Select-String -Path C:\Windows\Logs\CBS\CBS.log -Pattern 'error' |
                Select-Object -First 5 LineNumber, Line

            Get-Content app.log | Select-String '(?<code>E\d{4})' |
                ForEach-Object { §_.Matches[0].Groups['code'].Value }
        """)

        PsSection("PRACTICAL: PARSING LOGS")
        PsCode("""
            # Sample lines:
            # 2026-10-06 14:03:11 ERROR [Auth] Login failed for user ada from 10.0.0.7
            # 2026-10-06 14:03:15 INFO  [Web]  GET /index.html 200

            §pattern = '^(?<date>\S+) (?<time>\S+) (?<level>\w+)\s+\[(?<src>\w+)\]\s+(?<msg>.*)§'

            §entries = Get-Content C:\Temp\PSCourse\app.log | ForEach-Object {
                if (§_ -match §pattern) {
                    [PSCustomObject]@{
                        Time    = [datetime]"§(§Matches.date) §(§Matches.time)"
                        Level   = §Matches.level
                        Source  = §Matches.src
                        Message = §Matches.msg
                    }
                }
            }

            §entries | Where-Object Level -eq 'ERROR' | Group-Object Source
        """)
        PsText("""
            This is the key technique: regex turns unstructured TEXT into OBJECTS, and from then on you use the object pipeline (lesson 16). Walkthrough of the pattern: ^ start; (?<date>\S+) a run of non-spaces named date; a space; time; (?<level>\w+) the level word; \s+ one or more spaces; \[ and \] literal brackets around the source; (?<msg>.*) the rest; § end.
        """)
        PsCode("""
            # Extract all IPv4 addresses and count them
            §ipRegex = '\b(?:\d{1,3}\.){3}\d{1,3}\b'
            Select-String -Path C:\Temp\PSCourse\app.log -Pattern §ipRegex -AllMatches |
                ForEach-Object { §_.Matches.Value } |
                Group-Object | Sort-Object Count -Descending
        """)
        PsText("-AllMatches finds every match per line, not just the first. Note this IPv4 pattern also accepts 999.999.999.999 — regex validates SHAPE; use [ipaddress]::TryParse for real validation.")

        PsSection("PRACTICAL: VALIDATING INPUT")
        PsCode("""
            function New-User {
                param(
                    [ValidatePattern('^[a-z][a-z0-9_]{2,15}§')]
                    [string]§UserName,

                    [ValidatePattern('^[^@\s]+@[^@\s]+\.[a-z]{2,}§')]
                    [string]§Email
                )
                "Creating §UserName <§Email>"
            }
            New-User -UserName ada_l -Email ada@example.com
            New-User -UserName 1bad -Email x          # validation error
        """)
        PsText("""
            Anchors ^ and § are ESSENTIAL in validation: without them, '[a-z]{3}' matches "abc" inside "123abc!!!" and the bad input passes. Use [regex]::Escape(§text) when user text must be matched literally inside a pattern.
        """)

        PsSection("THE [regex] CLASS")
        PsCode("""
            [regex]::Matches('a1 b22 c333', '\d+') | ForEach-Object Value   # 1 22 333
            [regex]::Replace('hello', '^.', { §args[0].Value.ToUpper() }) # Hello
            [regex]::Escape('C:\Temp\file (1).txt')     # C:\\Temp\\file\ \(1\)\.txt
            §re = [regex]::new('\d{4}', 'IgnoreCase, Compiled')
            §re.IsMatch('year 2026')                    # True

            # Inline options in the pattern:
            "Line1`nERROR here" -match '(?m)^ERROR'      # multiline: ^ per line
            'ABC' -match '(?-i)abc'                      # turn case-insensitivity off
        """)
        PsText("Use [regex]::Matches to get ALL matches (the -match operator only finds the first). Compiled regex objects are faster when the same pattern runs on millions of lines.")

        PsSection("CAVEATS")
        PsText("""
            • Always single-quote patterns and replacements.
            • -match / -replace / -split are case-INSENSITIVE by default (use -cmatch etc.).
            • -split and -replace use regex: escape . \ ( ) [ ] { } * + ? ^ § | with \ or [regex]::Escape.
            • Greedy .* grabs as much as possible: '<b>x</b><b>y</b>' -match '<b>(.*)</b>' captures "x</b><b>y". Use lazy .*? to stop at the first </b>.
            • Don't parse HTML/JSON/XML with regex — use ConvertFrom-Json, [xml] or a real parser.
        """)

        PsSection("CONNECTIONS")
        PsText("-match and -replace were introduced with the other operators in lesson 5, and switch -Regex in lesson 5 too. Regex is how text from native commands, files (lesson 12) and logs becomes objects for the pipeline (lesson 16). ValidatePattern comes from lesson 7.")

        PsExercise("""
            1. From the string 'Order #12345 shipped on 2026-10-06 to ZIP 90210' extract the order number, date and ZIP code using ONE -match with named groups.
            2. Convert 'camelCaseVariableName' to 'camel_case_variable_name' with -replace.
            3. Split 'apples, pears;bananas  kiwis' into fruits whatever the separator.
        """)
        PsChallenge("""
            Parse the output of the native command  ipconfig /all  with regex: for each adapter, extract the adapter name, Physical Address (MAC), IPv4 Address and DHCP Enabled, and produce [PSCustomObject]s. Then compare your results with Get-NetIPConfiguration (lesson 15). This shows why object-native commands are preferable when they exist.
        """)
    }
}
