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
fun ComScreen(navController: NavController) {
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
            text = "COM",
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

        SectionHeader("WHAT IS COM")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Component Object Model: Microsoft's binary-compatible interface standard. A COM " +
            "object exposes functionality through interfaces — abstract base classes with a " +
            "pure virtual table (vtable). Any language that can call through a vtable can use " +
            "COM: C, C++, Delphi, Visual Basic, .NET (via COM interop). COM objects can live " +
            "in the same process (in-process server, a DLL), a separate process (out-of-process " +
            "server, an EXE), or on a remote machine (DCOM). COM underpins: DirectX, Windows " +
            "Shell extensions, WMI, ActiveX, and much of the Windows API."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("IUNKNOWN")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Every COM interface must derive from IUnknown — no exceptions. IUnknown provides " +
            "three methods that every COM object must implement."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "struct IUnknown {\n" +
            "    virtual HRESULT QueryInterface(REFIID riid,\n" +
            "                                   void** ppvObject) = 0;\n" +
            "    virtual ULONG   AddRef()  = 0;\n" +
            "    virtual ULONG   Release() = 0;\n" +
            "};"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("REFERENCE COUNTING")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "COM uses manual reference counting to manage object lifetimes. Rules: a pointer " +
            "returned to you (from CoCreateInstance, QueryInterface, etc.) is already AddRef'd. " +
            "Call Release() when done. If you store the pointer in a member variable, call " +
            "AddRef(). When the refcount hits 0 the object deletes itself. Smart pointers " +
            "automate this: CComPtr<T> (ATL), Microsoft::WRL::ComPtr<T> (WRL), " +
            "_com_ptr_t<T> from #import."
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("QUERYINTERFACE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Used to navigate between interfaces on the same object. You pass an IID (a " +
            "128-bit GUID uniquely identifying an interface) and a void** output. Returns " +
            "S_OK and a new AddRef'd pointer if the object supports the interface, or " +
            "E_NOINTERFACE if not. An object can expose any set of interfaces; callers " +
            "discover capabilities at runtime via QI."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "IShellLink* pLink = nullptr;\n" +
            "CoCreateInstance(CLSID_ShellLink, nullptr, CLSCTX_INPROC_SERVER,\n" +
            "                 IID_IShellLink, (void**)&pLink);\n\n" +
            "// Get IPersistFile from the same object\n" +
            "IPersistFile* pPF = nullptr;\n" +
            "HRESULT hr = pLink->QueryInterface(IID_IPersistFile,\n" +
            "                                   (void**)&pPF);\n" +
            "if (SUCCEEDED(hr)) {\n" +
            "    pPF->Save(L\"C:\\\\link.lnk\", TRUE);\n" +
            "    pPF->Release();\n" +
            "}\n" +
            "pLink->Release();"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("IDISPATCH")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "A late-binding interface for scripting languages (VBScript, PowerShell, Python " +
            "via win32com) that lets them call COM objects without knowing the interface at " +
            "compile time. GetIDsOfNames() converts a method name string into a numeric DISPID. " +
            "Invoke() calls the method via its DISPID, passing arguments as VARIANT values in " +
            "a DISPPARAMS struct. Type libraries (.tlb) describe available methods to tools " +
            "like OLE/COM Object Viewer."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "DISPID dispid;\n" +
            "OLECHAR* name = L\"Open\";\n" +
            "pDisp->GetIDsOfNames(IID_NULL, &name, 1,\n" +
            "                     LOCALE_USER_DEFAULT, &dispid);\n\n" +
            "VARIANT arg;\n" +
            "VariantInit(&arg);\n" +
            "arg.vt = VT_BSTR;\n" +
            "arg.bstrVal = SysAllocString(L\"C:\\\\file.txt\");\n\n" +
            "DISPPARAMS params = { &arg, nullptr, 1, 0 };\n" +
            "VARIANT result;\n" +
            "VariantInit(&result);\n" +
            "pDisp->Invoke(dispid, IID_NULL, LOCALE_USER_DEFAULT,\n" +
            "              DISPATCH_METHOD, &params, &result,\n" +
            "              nullptr, nullptr);\n" +
            "VariantClear(&arg);\n" +
            "VariantClear(&result);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("APARTMENT MODEL")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "COM's threading model. Every thread using COM must call CoInitialize or " +
            "CoInitializeEx at startup and CoUninitialize on exit.\n\n" +
            "Single-Threaded Apartment (STA): CoInitialize(nullptr). Each STA contains " +
            "exactly one thread. Cross-apartment calls are marshaled via a hidden window " +
            "message — the STA thread must pump messages to receive them.\n\n" +
            "Multi-Threaded Apartment (MTA): CoInitializeEx(nullptr, COINIT_MULTITHREADED). " +
            "All MTA threads share one apartment. COM does not serialize calls — the object " +
            "must handle its own thread safety."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// UI thread — STA\n" +
            "CoInitialize(nullptr);\n\n" +
            "// Worker thread — MTA\n" +
            "CoInitializeEx(nullptr, COINIT_MULTITHREADED);"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DCOM")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Distributed COM: COM extended across a network using RPC under the hood. The " +
            "server registers its AppID in the registry (HKCR\\AppID\\{...}). The client uses " +
            "CoCreateInstanceEx with a COSERVERINFO struct specifying the remote machine name. " +
            "Authentication via NTLM or Kerberos, configured by CoInitializeSecurity. " +
            "DCOMCNFG (dcomcnfg.exe) is the built-in tool to manage permissions and identity."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "COSERVERINFO si = {};\n" +
            "si.pwszName = L\"RemoteServer\";  // machine name\n\n" +
            "IMyInterface* pRemote = nullptr;\n" +
            "MULTI_QI qi = { &IID_IMyInterface, nullptr, S_OK };\n" +
            "CoCreateInstanceEx(CLSID_MyObject, nullptr,\n" +
            "                   CLSCTX_REMOTE_SERVER,\n" +
            "                   &si, 1, &qi);\n" +
            "pRemote = (IMyInterface*)qi.pItf;"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("MARSHALING")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Microsoft spelling: one 'l' (\"marshaling\"). Both forms appear in docs but " +
            "\"marshaling\" is more common in Windows API documentation.\n\n" +
            "Marshaling packages an interface pointer so it can cross apartment, process, or " +
            "machine boundaries. Standard marshaling uses a proxy DLL on the caller side and " +
            "a stub DLL on the object side. The proxy serializes the call (method index + " +
            "parameters) and sends it via LRPC (same machine, different process) or RPC " +
            "(different machine). The stub deserializes and invokes the real object.\n\n" +
            "The Free-Threaded Marshaler (FTM) implements IMarshal to skip the proxy/stub and " +
            "pass the raw pointer directly — safe only when both caller and object are in the MTA."
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
