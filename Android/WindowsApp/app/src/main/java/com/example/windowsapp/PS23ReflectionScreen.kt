package com.example.windowsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PS23ReflectionScreen(navController: NavController) {
    PsLessonScreen(navController, "23 REFLECTION AND RUNTIME TYPE INSPECTION") {

        PsSection("WHAT IS REFLECTION?")
        PsText("""
            REFLECTION is a program's ability to examine — and use — its own types at RUNTIME. In .NET, every compiled assembly carries rich METADATA describing every type, method, property, parameter and attribute in it. The System.Reflection API reads that metadata, so code can ask questions like:
            • "What type is this object, and what does it inherit from?"
            • "Which methods does this type have, with which parameters?"
            • "Which assemblies are loaded, and what types do they contain?"
            • "Call the method whose name is in this string."

            Debuggers, serializers (JSON), test frameworks, dependency injection, IDE IntelliSense — and PowerShell's own Get-Member, tab completion and parameter binding — are all built on reflection.
        """)

        PsSection("KEY CONCEPTS")
        PsText("""
            • ASSEMBLY — a compiled unit (.dll / .exe) containing IL code + metadata. System.Private.CoreLib.dll holds core types; System.Management.Automation.dll is PowerShell itself.
            • TYPE (System.Type) — the description of a class, struct, enum or interface: name, namespace, base type, members.
            • MEMBERS — MethodInfo, PropertyInfo, FieldInfo, ConstructorInfo, EventInfo — each describes one member.
            • ATTRIBUTES — metadata annotations attached to code: [Obsolete], [Serializable], and PowerShell's own [Parameter(Mandatory)] and [CmdletBinding()] are attributes!
            • BINDING FLAGS — filters controlling which members are returned: Public / NonPublic, Instance / Static, DeclaredOnly.
        """)

        PsSection("GETTING A TYPE")
        PsCode("""
            §t = (Get-Date).GetType()        # from an instance
            §t = [datetime]                  # from a type literal
            §t = [type]'System.IO.FileInfo'  # from a string

            §t.FullName              # System.DateTime
            §t.Namespace             # System
            §t.BaseType              # System.ValueType
            §t.IsValueType           # True  (struct)
            §t.IsClass; §t.IsEnum; §t.IsInterface
            §t.Assembly.Location     # the DLL it comes from
            §t.GetInterfaces().Name  # IComparable, IFormattable, …
        """)
        PsCode("""
            # Walk the inheritance chain
            §t = [System.IO.FileInfo]
            while (§t) { §t.FullName; §t = §t.BaseType }
        """)
        PsOutput("""
            System.IO.FileInfo
            System.IO.FileSystemInfo
            System.MarshalByRefObject
            System.Object
        """)

        PsSection("INSPECTING MEMBERS")
        PsCode("""
            [System.IO.File].GetMethods() |
                Select-Object -ExpandProperty Name -Unique | Sort-Object | Select-Object -First 10

            # Method signatures with parameters
            [System.IO.File].GetMethods() | Where-Object Name -eq 'Copy' | ForEach-Object {
                §params = §_.GetParameters() | ForEach-Object { "§(§_.ParameterType.Name) §(§_.Name)" }
                "§(§_.ReturnType.Name) Copy(§(§params -join ', '))"
            }

            [System.Diagnostics.Process].GetProperties() | Select-Object Name, PropertyType, CanWrite -First 8
            [System.Text.StringBuilder].GetConstructors() | ForEach-Object { §_.ToString() }
        """)
        PsOutput("""
            Void Copy(String sourceFileName, String destFileName)
            Void Copy(String sourceFileName, String destFileName, Boolean overwrite)
        """)

        PsSection("BINDING FLAGS: NON-PUBLIC MEMBERS")
        PsCode("""
            §flags = [System.Reflection.BindingFlags]'NonPublic, Instance'
            [System.Collections.Generic.List[int]].GetFields(§flags) | Select-Object Name, FieldType
        """)
        PsOutput("""
            Name     FieldType
            ----     ---------
            _items   System.Int32[]
            _size    System.Int32
            _version System.Int32
        """)
        PsText("""
            With NonPublic you can see a type's PRIVATE implementation — here, List<T> really stores its items in an internal array _items. Reading private members is great for learning and debugging, but DON'T depend on them in production scripts: private names change between .NET versions without notice.
        """)

        PsSection("ASSEMBLIES")
        PsCode("""
            [AppDomain]::CurrentDomain.GetAssemblies().Count
            [AppDomain]::CurrentDomain.GetAssemblies() |
                Select-Object -First 8 @{N='Name';E={§_.GetName().Name}}, @{N='Version';E={§_.GetName().Version}}

            # Which public types does PowerShell's engine assembly contain?
            §sma = [System.Management.Automation.PSObject].Assembly
            §sma.GetExportedTypes().Count
            §sma.GetExportedTypes() | Where-Object Name -like '*Cmdlet*' | Select-Object -First 5 FullName

            # Find every loaded type that has a method named 'Parse'
            [AppDomain]::CurrentDomain.GetAssemblies() | ForEach-Object {
                try { §_.GetExportedTypes() } catch {}
            } | Where-Object { §_.GetMethod('Parse', [type[]]@([string])) } |
                Select-Object -First 10 FullName
        """)
        PsText("The last example DISCOVERS functionality: it searches all loaded types for a static Parse(string) method — [int], [datetime], [ipaddress], [version], [guid]… Some dynamic assemblies throw on GetExportedTypes, hence the try/catch.")

        PsSection("ATTRIBUTES")
        PsCode("""
            # .NET attributes on a type
            [System.Text.StringBuilder].GetCustomAttributes(§true) | ForEach-Object { §_.GetType().Name }

            # PowerShell's own attributes, seen through reflection
            function Test-Attr {
                [CmdletBinding()]
                param([Parameter(Mandatory)][ValidateRange(1,10)][int]§N)
            }
            (Get-Command Test-Attr).Parameters['N'].Attributes | ForEach-Object { §_.GetType().Name }

            # Which cmdlet class implements Get-Process, and its [Cmdlet] attribute?
            §impl = (Get-Command Get-Process).ImplementingType
            §impl.FullName
            §impl.GetCustomAttributes([System.Management.Automation.CmdletAttribute], §false)
        """)
        PsOutput("""
            ParameterAttribute
            ValidateRangeAttribute
            ArgumentTypeConverterAttribute

            Microsoft.PowerShell.Commands.GetProcessCommand

            VerbName NounName DefaultParameterSetName …
            -------- -------- -----------------------
            Get      Process  Name
        """)
        PsText("This reveals how cmdlets work internally: Get-Process is the C# class GetProcessCommand decorated with [Cmdlet(\"Get\", \"Process\")]. PowerShell finds cmdlets by reflecting over assemblies for that attribute.")

        PsSection("DYNAMIC INVOCATION")
        PsCode("""
            # Call a method chosen at runtime by name
            §methodName = 'ToUpper'
            'hello'.§methodName()                    # PowerShell syntax: HELLO

            §m = [string].GetMethod('Replace', [type[]]@([string], [string]))
            §m.Invoke('hello world', @('world', 'reflection'))   # hello reflection

            # Read a property chosen at runtime
            §prop = 'Length'
            'hello'.§prop                            # 5

            # Create an instance of a type named in a string
            §typeName = 'System.Text.StringBuilder'
            §obj = [Activator]::CreateInstance([type]§typeName)
            §obj.GetType().Name
        """)
        PsText("PowerShell supports dynamic member names natively (§obj.§name, §obj.§name()). MethodInfo.Invoke is the raw reflection equivalent and works for overloads that PowerShell's binder picks wrongly.")

        PsSection("REFLECTION vs Get-Member")
        PsCode("""
                             Get-Member                .NET reflection
            ─────────────────────────────────────────────────────────────
            Input            an OBJECT (pipeline)      a TYPE (System.Type)
            Shows            .NET members PLUS ETS     only the real .NET
                             members (ScriptProperty,  members
                             NoteProperty, Alias…)
            Non-public       no                        yes (BindingFlags)
            Parameters       one-line definition       full ParameterInfo:
                                                       types, defaults, attrs
            Attributes       no                        yes
            Assemblies       no                        yes
            Invoke           no (just describes)       yes (Invoke, Activator)
            Ease             designed for humans       designed for programs
        """)
        PsCode("""
            # The difference in practice: BaseName is an ETS member
            Get-Item C:\Windows\notepad.exe | Get-Member -Name BaseName   # found (ScriptProperty)
            [System.IO.FileInfo].GetProperty('BaseName')                   # nothing — not .NET

            # PowerShell's psobject view combines both worlds
            (Get-Item C:\Windows\notepad.exe).psobject.Properties |
                Select-Object Name, MemberType -First 6
        """)
        PsText("""
            Get-Member is a friendly view over reflection PLUS PowerShell's Extended Type System. Use Get-Member for day-to-day exploration; use reflection when you need private members, attributes, parameter details, assembly scanning, or dynamic invocation. Every object's .psobject property exposes PowerShell's adapter view programmatically.
        """)

        PsSection("CAVEATS")
        PsText("""
            • Non-public members are implementation details; they differ between .NET Framework (5.1) and .NET (7) and can change in any update.
            • Reflection is slower than direct calls — fine for exploration, avoid in tight loops.
            • GetExportedTypes() can throw for dynamic or partially loadable assemblies; wrap in try/catch.
            • Overloaded methods: GetMethod(name) throws AmbiguousMatchException — pass parameter types.
        """)

        PsSection("CONNECTIONS")
        PsText("Get-Member (lessons 3, 16) is reflection with ETS on top. Classes you write (lessons 17, 18) are real .NET types you can reflect over. Attributes like [Parameter] and [CmdletBinding()] are central to lesson 24.")

        PsExercise("""
            1. Print the full inheritance chain of the object returned by Get-Service.
            2. List all static properties of [System.Environment] with their current values (GetProperties with Public, Static flags, then .GetValue(§null)).
            3. Compare the property list of  Get-Item C:\Windows  from Get-Member and from GetType().GetProperties(). Which extra members does Get-Member show?
        """)
        PsChallenge("""
            Write Find-DotNetMethod -Name <pattern> that searches all exported types in all loaded assemblies for public static methods matching the pattern and outputs Type, Method and a readable signature. Use it to discover three useful methods you didn't know about (try *Encode*, *Hash*, *Compress*), and call each one from PowerShell.
        """)
    }
}
