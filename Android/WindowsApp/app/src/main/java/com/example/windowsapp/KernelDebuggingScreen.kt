package com.example.windowsapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.windowsapp.ui.theme.HackerGreen

@Composable
fun KernelDebuggingScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "KERNEL DEBUGGING",
            color = HackerGreen,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "─".repeat(28),
            color = HackerGreen,
            fontFamily = FontFamily.Monospace,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        SectionHeader("OVERVIEW")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Two primary approaches: lightweight printf-style debugging with DbgPrint and " +
            "DebugView, and full kernel debugging with WinDbg attached to a target machine."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DBGPRINT AND DEBUGVIEW")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "DbgPrint() is the kernel-mode printf. DebugView (dbgview.exe) from Sysinternals " +
            "captures its output in real time — enable the \"Capture Kernel\" checkbox. No " +
            "second machine and no debugger setup required, making it ideal for early " +
            "development and simple tracing."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "#include <ntddk.h>\n\n" +
            "// Basic usage\n" +
            "DbgPrint(\"MyDriver: value = %d, ptr = %p\\n\", someInt, somePtr);\n\n" +
            "// Component-level filtering with DbgPrintEx (optional)\n" +
            "DbgPrintEx(DPFLTR_IHVDRIVER_ID, DPFLTR_INFO_LEVEL,\n" +
            "           \"MyDriver: initialized\\n\");"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("HOST AND TARGET MACHINES")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Host: the machine running WinDbg (your development workstation).\n" +
            "Target: the machine being debugged — its kernel is halted at breakpoints and " +
            "controlled by the host.\n\n" +
            "The target can be a physical second machine or a VM. It must be configured to " +
            "accept a debugger connection before booting."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("KDNET SETUP (NETWORK)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Modern network-based kernel debugging. Run on the target machine:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "bcdedit /debug on\n" +
            "bcdedit /dbgsettings net hostip:<HOST_IP> port:<PORT>\n" +
            "  ; port must be in range 49152-65535\n" +
            "  ; bcdedit outputs an encryption key — save it\n\n" +
            "; Reboot the target, then on the host:\n" +
            "windbg -k net:port=<PORT>,key=<KEY>\n" +
            "; Or: File > Attach to Kernel > Net tab"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("VIRTUAL SERIAL PORT SETUP (VM)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "For a VM target: add a named pipe virtual serial port in VM settings " +
            "(e.g., \\\\.\\pipe\\com_1, server mode). Then on the target VM:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "bcdedit /debug on\n" +
            "bcdedit /dbgsettings serial debugport:1 baudrate:115200\n\n" +
            "; On the host, connect WinDbg:\n" +
            "windbg -k com:pipe,port=\\\\.\\pipe\\com_1,resets=0"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SYMBOL PATH")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "WinDbg needs symbol files (.pdb) to resolve addresses to function names. " +
            "The standard symbol path is:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "srv*C:\\Symbols*https://msdl.microsoft.com/download/symbols\n\n" +
            "; srv*         = use symbol server protocol\n" +
            "; C:\\Symbols   = local disk cache directory\n" +
            "; URL          = Microsoft public symbol server\n\n" +
            "; Set via WinDbg command:\n" +
            ".sympath srv*C:\\Symbols*https://msdl.microsoft.com/download/symbols\n\n" +
            "; Or environment variable before launching WinDbg:\n" +
            "; _NT_SYMBOL_PATH=srv*C:\\Symbols*https://msdl.microsoft.com/download/symbols\n\n" +
            "; Add your own driver PDB directory by prepending it:\n" +
            ".sympath C:\\path\\to\\pdbs;srv*C:\\Symbols*https://msdl.microsoft.com/download/symbols"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("LOADING YOUR DRIVER SYMBOLS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("After the driver is loaded on the target, force WinDbg to load its symbols:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            ".reload /f mydriver.sys     ; force reload symbols for this module\n\n" +
            "; Or add the PDB directory first:\n" +
            ".sympath+ C:\\path\\to\\pdbs\n" +
            ".reload /f mydriver.sys\n\n" +
            "; Verify symbols loaded correctly:\n" +
            "x MyDriver!*               ; list all symbols in MyDriver"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("BREAKPOINTS BEFORE DRIVER LOADS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "The driver module does not exist in memory until it is loaded, so a normal bp " +
            "command will fail. Use a deferred breakpoint instead:"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "bu MyDriver!DriverEntry   ; deferred bp — resolves when module loads\n" +
            "g                         ; continue; run 'sc start MyDriver' on target\n" +
            "                          ; WinDbg breaks when DriverEntry is hit\n\n" +
            "; Alternative: break on module load event, then set bp manually\n" +
            "sxe ld:mydriver.sys       ; break when mydriver.sys is loaded\n" +
            "g                         ; run 'sc start MyDriver' on target\n" +
            "                          ; WinDbg breaks at load; now set your bp:\n" +
            "bp MyDriver!DriverEntry\n" +
            "g                         ; continue to DriverEntry"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("USEFUL WINDBG COMMANDS")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "!analyze -v                ; analyze crash dump or live bugcheck\n" +
            "lm                         ; list all loaded modules\n" +
            "lm m mydriver*             ; list modules matching pattern\n" +
            "dt _DRIVER_OBJECT <addr>   ; dump structure at address\n" +
            "k                          ; call stack of current thread\n" +
            "bp  MyDriver!MyFunction    ; set breakpoint (resolved address)\n" +
            "bu  MyDriver!MyFunction    ; set deferred breakpoint\n" +
            "bl                         ; list all breakpoints\n" +
            "bc *                       ; clear all breakpoints\n" +
            "g                          ; go / continue execution\n" +
            "p                          ; step over\n" +
            "t                          ; step into\n" +
            "r                          ; show registers\n" +
            "dd <addr>                  ; dump DWORDs at address\n" +
            "!thread                    ; dump current thread info\n" +
            "!process 0 0               ; list all processes"
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
