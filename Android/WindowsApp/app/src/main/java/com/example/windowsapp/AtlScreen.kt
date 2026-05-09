package com.example.windowsapp

import androidx.compose.foundation.background
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
fun AtlScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "ATL\nACTIVE TEMPLATE LIBRARY",
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

        SectionHeader("WHAT IS ATL")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "ATL stands for Active Template Library. It is a C++ template library for building COM (Component Object Model) components — in-process servers (DLLs), out-of-process servers (EXEs), ActiveX controls, and shell extensions.\n\n" +
            "Released by Microsoft in 1995 as a Visual C++ 4.2 add-on, then bundled with Visual Studio. It ships with Visual Studio to this day.\n\n" +
            "ATL is dramatically lighter than MFC: no heavy runtime DLL dependency, no document-view framework. The templates generate COM boilerplate at compile time, resulting in small, fast binaries suitable for system-level components."
        )
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WHY ATL EXISTS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "MFC had COM support, but it required the MFC runtime DLL and added considerable overhead — unsuitable for lightweight COM servers.\n\n" +
            "ATL uses C++ templates to generate all COM boilerplate (QueryInterface, AddRef, Release, class factory, registration) at compile time, with zero extra runtime dependency. The resulting DLL or EXE can be just a few kilobytes.\n\n" +
            "Common uses: shell extensions (context menu handlers, icon overlays, preview handlers), COM automation servers, ActiveX controls (in-browser or in legacy apps), service moniker objects."
        )
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("COM IMPLEMENTATION PATTERN")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "A COM object in ATL inherits from several ATL templates that wire up IUnknown and the class factory automatically."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
// MyObject.h
class ATL_NO_VTABLE CMyObject :
    public CComObjectRootEx<CComSingleThreadModel>,
    public CComCoClass<CMyObject, &CLSID_MyObject>,
    public IMyInterface
{
public:
    DECLARE_REGISTRY_RESOURCEID(IDR_MYOBJECT) // RGS script for registration

    BEGIN_COM_MAP(CMyObject)
        COM_INTERFACE_ENTRY(IMyInterface)
        COM_INTERFACE_ENTRY(IUnknown)
    END_COM_MAP()

    // IMyInterface
    STDMETHODIMP DoWork(LONG input, LONG *output) override {
        *output = input * 2;
        return S_OK;
    }
};
OBJECT_ENTRY_AUTO(__uuidof(CMyObject), CMyObject)

// CComObjectRootEx  → provides AddRef, Release, QueryInterface
// CComCoClass       → ties the C++ class to a CLSID + class factory
// BEGIN_COM_MAP     → defines which interfaces QI resolves
// OBJECT_ENTRY_AUTO → registers the class with the ATL module
        """.trimIndent())
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("SMART POINTERS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "ATL provides two smart pointer types that manage COM reference counting automatically."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
// CComPtr<T> — owns one reference; AddRef on copy, Release on destroy
CComPtr<IMyInterface> spObj;
HRESULT hr = spObj.CoCreateInstance(CLSID_MyObject);
if (SUCCEEDED(hr)) {
    LONG result;
    spObj->DoWork(21, &result); // result == 42
} // spObj released here automatically

// CComQIPtr<T> — like CComPtr but QueryInterface on assignment
CComPtr<IUnknown> spUnk = ...; // some IUnknown
CComQIPtr<IMyInterface> spMy(spUnk); // QI for IMyInterface
if (spMy) {
    // interface supported
}

// CComBSTR — BSTR wrapper
CComBSTR bstr(L"Hello");

// CComVariant — VARIANT wrapper
CComVariant var(42L);
        """.trimIndent())
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("MODULE AND REGISTRATION")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "An ATL DLL server uses CAtlDllModuleT; an EXE server uses CAtlExeModuleT. These handle DllMain / WinMain, class object registration, and lock counting.\n\n" +
            "Registration is driven by an RGS (Registry Script) resource embedded in the binary. regsvr32 calls DllRegisterServer, which processes the RGS scripts for all OBJECT_ENTRY_AUTO objects.\n\n" +
            "DECLARE_REGISTRY_RESOURCEID(IDR_MYOBJECT) links the class to its RGS resource."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
// dllmain.cpp
class CMyModule : public CAtlDllModuleT<CMyModule> {} _AtlModule;

extern "C" BOOL WINAPI DllMain(
        HINSTANCE hInstance, DWORD dwReason, LPVOID lpReserved) {
    return _AtlModule.DllMain(dwReason, lpReserved);
}
STDAPI DllRegisterServer()   { return _AtlModule.DllRegisterServer(); }
STDAPI DllUnregisterServer() { return _AtlModule.DllUnregisterServer(); }
STDAPI DllGetClassObject(REFCLSID rclsid, REFIID riid, LPVOID* ppv) {
    return _AtlModule.DllGetClassObject(rclsid, riid, ppv); }
STDAPI DllCanUnloadNow()     { return _AtlModule.DllCanUnloadNow(); }
        """.trimIndent())
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("ATL VS MFC")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
                ATL                  MFC
────────────────────────────────────────────────────
Purpose         COM components       GUI applications
Runtime DLL     None                 MFC runtime required
Binary size     Tiny (KB range)      Larger
GUI support     Minimal              Full Doc/View/Controls
COM support     Native, first-class  Available but heavier
Templates       Heavy use            Light use
Typical output  Shell ext / server   Desktop application
        """.trimIndent())
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WTL")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "WTL (Windows Template Library) is an open-source extension to ATL that adds lightweight GUI classes — CFrameWindowImpl, CDialogImpl, CDCHandle, CMenuHandle, and common control wrappers.\n\n" +
            "WTL gives ATL the GUI capabilities of MFC without the MFC runtime dependency. It is used by developers who want a small, fast Win32 GUI without the overhead of MFC.\n\n" +
            "WTL is not developed by Microsoft and does not ship with Visual Studio, but it is freely available and widely used in system utility software."
        )
        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
