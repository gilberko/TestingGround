package com.example.windowsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PS20ComScreen(navController: NavController) {
    PsLessonScreen(navController, "20 COM AUTOMATION") {

        PsSection("WHAT IS COM?")
        PsText("""
            COM — the COMPONENT OBJECT MODEL — is Microsoft's binary standard, introduced in 1993, for software components to talk to each other regardless of the language they were written in or the process they live in. A component written in C++ can be used from Visual Basic, C#, JavaScript, Python or PowerShell, without recompiling and without sharing source code.

            The User Mode COM screen in this app covers the C++ side (IUnknown, reference counting, apartments, DCOM). This lesson focuses on what a SCRIPT sees.
        """)

        PsSection("WHY MICROSOFT CREATED IT")
        PsText("""
            In the late 1980s / early 1990s Windows applications needed to embed and control each other: put an Excel chart inside a Word document, drive Word from a macro, let any program use a common spell checker. Every language had its own object layout and calling conventions, so objects couldn't be shared at the binary level.

            COM's answer grew out of OLE (Object Linking and Embedding): define a LANGUAGE-NEUTRAL BINARY CONTRACT — a table of function pointers (a vtable) with agreed calling conventions — so any language that can call through a pointer table can use any component. DCOM (1996) extended it across the network.
        """)

        PsSection("CORE CONCEPTS")
        PsText("""
            • INTERFACE — a named, versioned set of methods identified by a GUID (an IID). Clients only ever talk to interfaces, never to the implementation. Interfaces are IMMUTABLE once published: new features mean new interfaces (IShellItem, IShellItem2…).
            • IUnknown — the root interface every COM object implements: QueryInterface (ask "do you support interface X?"), AddRef and Release (reference counting — the object frees itself when the count drops to 0).
            • COCLASS — a concrete component implementing one or more interfaces, identified by a CLSID (a GUID).
            • ProgID — a human-friendly name mapped to a CLSID in the registry, such as "WScript.Shell" or "Excel.Application". Registered under HKEY_CLASSES_ROOT.
            • SERVER — where the component lives: in-process (a DLL loaded into your process) or out-of-process (a separate EXE, like Excel, with calls marshaled across processes).
            • IDispatch — the "automation" interface. Instead of compiled vtable calls, a client asks for a method by NAME at runtime (GetIDsOfNames) and calls it with VARIANT arguments (Invoke). This LATE BINDING is what makes COM usable from scripting languages — and from PowerShell.
        """)

        PsSection("WHY COM IS STILL EVERYWHERE")
        PsText("""
            Although .NET and WinRT came later, COM remains the foundation of large parts of Windows:
            • The Windows Shell (Explorer, shortcuts, file dialogs, Shell.Application)
            • Microsoft Office automation (Excel, Word, Outlook object models)
            • DirectX, Media Foundation, Windows Imaging Component
            • WMI's own client API (IWbemServices — lesson 21)
            • Task Scheduler, Windows Update Agent, Windows Firewall (HNetCfg.FwPolicy2), BITS, Volume Shadow Copy
            • WinRT itself is built on COM concepts (IInspectable derives from IUnknown)

            Many older applications expose ONLY a COM automation interface, so COM is often the only scriptable way to control them.
        """)

        PsSection("CREATING COM OBJECTS IN POWERSHELL")
        PsCode("""
            §shell = New-Object -ComObject WScript.Shell
            §shell.GetType().FullName           # System.__ComObject
            §shell | Get-Member
        """)
        PsOutput("""
               TypeName: System.__ComObject#{41904400-be18-11d3-a28b-00104bd35090}

            Name                     MemberType Definition
            ----                     ---------- ----------
            CreateShortcut           Method     IDispatch CreateShortcut (string)
            Exec                     Method     IWshExec Exec (string)
            ExpandEnvironmentStrings Method     string ExpandEnvironmentStrings (string)
            Popup                    Method     int Popup (string, Variant, Variant, Variant)
            RegRead                  Method     Variant RegRead (string)
            Run                      Method     int Run (string, Variant, Variant)
            SpecialFolders           Property   IWshCollection SpecialFolders () {get}
            …
        """)
        PsText("""
            New-Object -ComObject <ProgID> looks up the ProgID in the registry, creates the object through COM (CoCreateInstance) and wraps it in a .NET Runtime Callable Wrapper (RCW). The GUID in the TypeName is the interface ID. PowerShell uses IDispatch and the component's TYPE LIBRARY to discover members, so Get-Member works just like for .NET objects — this is how you explore a COM object without documentation.
        """)

        PsSection("SAFE READ-ONLY EXAMPLES")
        PsCode("""
            §shell = New-Object -ComObject WScript.Shell
            §shell.ExpandEnvironmentStrings('%WINDIR%\System32')   # C:\WINDOWS\System32
            §shell.SpecialFolders.Item('Desktop')                    # desktop path
            §shell.RegRead('HKLM\SOFTWARE\Microsoft\Windows NT\CurrentVersion\CurrentBuild')

            §app = New-Object -ComObject Shell.Application
            §app.Namespace('C:\Windows').Items() | Select-Object -First 5 Name, Size, Type
            §app.Windows() | Select-Object LocationName, LocationURL    # open Explorer windows

            # Explorer "details" columns — metadata .NET doesn't expose easily
            §folder = §app.Namespace('C:\Windows')
            §item = §folder.ParseName('notepad.exe')
            0..5 | ForEach-Object { '{0,-15} {1}' -f §folder.GetDetailsOf(§null, §_), §folder.GetDetailsOf(§item, §_) }
        """)
        PsText("""
            Shell.Application exposes the Explorer shell. GetDetailsOf reads the same columns you see in Explorer's Details view (Name, Size, Item type, Date modified…), including media and document metadata that ordinary file APIs don't provide.
        """)

        PsSection("EXAMPLES THAT CHANGE THINGS")
        PsWarn("The following examples CREATE a shortcut file on your desktop and show a dialog. Delete the shortcut afterwards. Office automation launches the application in the background.")
        PsCode("""
            # Create a desktop shortcut (.lnk)
            §shell = New-Object -ComObject WScript.Shell
            §lnkPath = Join-Path §shell.SpecialFolders.Item('Desktop') 'Notepad.lnk'
            §lnk = §shell.CreateShortcut(§lnkPath)
            §lnk.TargetPath = 'C:\Windows\notepad.exe'
            §lnk.Description = 'Created by PowerShell'
            §lnk.Save()

            # Popup with timeout: returns -1 on timeout, 1 for OK
            §shell.Popup('Hello from COM!', 5, 'PSCourse', 0x40)
        """)
        PsCode("""
            # Excel automation (only if Office is installed)
            §excel = New-Object -ComObject Excel.Application
            try {
                §excel.Visible = §false
                §wb = §excel.Workbooks.Add()
                §ws = §wb.Worksheets.Item(1)
                §ws.Cells.Item(1, 1) = 'Process'
                §ws.Cells.Item(1, 2) = 'MemoryMB'
                §row = 2
                Get-Process | Sort-Object WorkingSet64 -Descending | Select-Object -First 10 | ForEach-Object {
                    §ws.Cells.Item(§row, 1) = §_.Name
                    §ws.Cells.Item(§row, 2) = [math]::Round(§_.WorkingSet64 / 1MB)
                    §row++
                }
                §wb.SaveAs('C:\Temp\PSCourse\procs.xlsx')
                §wb.Close()
            }
            finally {
                §excel.Quit()
                [void][System.Runtime.InteropServices.Marshal]::ReleaseComObject(§excel)
                [GC]::Collect(); [GC]::WaitForPendingFinalizers()
            }
        """)
        PsText("""
            This is the classic COM automation pattern: the Excel object model (Application → Workbooks → Worksheet → Cells) is the same one VBA macros use. The finally block matters: an out-of-process COM server like Excel keeps running (an invisible EXCEL.EXE in Task Manager) until every reference is released. Quit() asks it to exit; ReleaseComObject decrements the RCW's reference count; the GC calls free any remaining wrappers.
        """)

        PsSection("DISCOVERING COM CLASSES")
        PsCode("""
            # List registered ProgIDs (slow — reads HKCR)
            Get-ChildItem Registry::HKEY_CLASSES_ROOT -ErrorAction SilentlyContinue |
                Where-Object { §_.PSChildName -match '^\w+\.\w+(\.\d+)?§' -and
                               (Test-Path "Registry::§(§_.Name)\CLSID") } |
                Select-Object -First 20 PSChildName

            # Inspect members of any COM object
            §fw = New-Object -ComObject HNetCfg.FwPolicy2     # Windows Firewall
            §fw | Get-Member -MemberType Property
            §fw.FirewallEnabled(1)    # 1 = domain, 2 = private, 4 = public profile
        """)
        PsText("A ProgID key with a CLSID sub-key is a creatable COM class. Get-Member on the object then tells you what it can do.")

        PsSection("CAVEATS")
        PsText("""
            • COM is Windows-only; PowerShell 7 on Linux/macOS has no -ComObject.
            • Bitness: a 32-bit-only COM DLL can't be loaded into 64-bit PowerShell (and vice versa) — use the matching PowerShell or an out-of-process server.
            • Indexed properties often need .Item(): §ws.Cells.Item(1,1), not §ws.Cells[1,1].
            • Release out-of-process servers (Excel, Word, Outlook) or orphan processes pile up.
            • Office automation is not supported by Microsoft for unattended server-side use.
            • Prefer native cmdlets or .NET APIs when they exist (e.g. Get-NetFirewallProfile instead of HNetCfg.FwPolicy2); COM shines for apps and APIs that have nothing else.
        """)

        PsSection("CONNECTIONS")
        PsText("Get-Member (lesson 16) works on COM objects through IDispatch type information. try/finally (lesson 10) guarantees cleanup. HKEY_CLASSES_ROOT is reached through the registry provider (lesson 13). Lesson 21's WMI is itself built on COM/DCOM.")

        PsExercise("""
            Using Shell.Application, list all files in your Downloads folder with Name, Size and "Date modified" taken from GetDetailsOf. (Find the correct column indexes by printing GetDetailsOf(§null, 0..30) first.)
        """)
        PsChallenge("""
            Use the Windows Update Agent COM API (Microsoft.Update.Session) to search for installed updates READ-ONLY: §s = New-Object -ComObject Microsoft.Update.Session; §searcher = §s.CreateUpdateSearcher(); §searcher.QueryHistory(0, 20). Output Title, Date and ResultCode for the last 20 updates as objects, and explore the searcher with Get-Member. Do NOT call any Install/Download methods.
        """)
    }
}
