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
fun MfcScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "MFC\nMICROSOFT FOUNDATION CLASSES",
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

        SectionHeader("WHAT IS MFC")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "MFC stands for Microsoft Foundation Classes. It is a C++ class library that wraps the Win32 API, making Windows GUI programming object-oriented and reducing boilerplate.\n\n" +
            "Introduced in 1992 with Visual C++ 1.0. It ships with Visual Studio to this day (all editions).\n\n" +
            "Instead of writing a raw WinMain loop, registering WNDCLASSEX, calling CreateWindowEx, and implementing a WndProc switch statement, MFC provides class hierarchies, message maps, and application frameworks that handle this machinery automatically."
        )
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DOCUMENT-VIEW ARCHITECTURE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "MFC's central design pattern separates data from presentation:\n\n" +
            "CDocument — holds application data and handles serialization (Save/Load via the Serialize() method and CArchive).\n\n" +
            "CView / CScrollView — renders the document and handles user input. Multiple views can display the same document.\n\n" +
            "CFrameWnd / CMDIFrameWnd — the top-level frame window (SDI or MDI).\n\n" +
            "CDocTemplate — ties a document class, view class, and frame class together. The framework instantiates them as a matched set when a file is opened or a new document is created.\n\n" +
            "This pattern was influential and predates MVC frameworks in web development by a decade."
        )
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("MESSAGE MAPS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "Instead of a switch statement in WndProc, MFC uses message maps — a macro-based dispatch table that binds Windows messages to C++ member functions."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
// In the .cpp file
BEGIN_MESSAGE_MAP(CMyView, CView)
    ON_WM_PAINT()                           // WM_PAINT → OnPaint()
    ON_WM_LBUTTONDOWN()                     // WM_LBUTTONDOWN → OnLButtonDown()
    ON_COMMAND(ID_FILE_SAVE, &CMyView::OnFileSave)
    ON_BN_CLICKED(IDC_BUTTON1, &CMyView::OnButton1Clicked)
    ON_MESSAGE(WM_MY_CUSTOM, &CMyView::OnMyCustom)
END_MESSAGE_MAP()

void CMyView::OnPaint() {
    CPaintDC dc(this); // PAINTSTRUCT wrapper
    dc.TextOut(10, 10, _T("Hello from MFC"));
}

void CMyView::OnFileSave() {
    GetDocument()->DoFileSave();
}
        """.trimIndent())
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("KEY CLASSES")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "CWnd — base class for all window objects. Every button, edit, list box, and dialog inherits from it.\n\n" +
            "CDialog / CDialogEx — modal and modeless dialogs. Override OnInitDialog() for setup.\n\n" +
            "CDC — device context wrapper. Methods include DrawText, BitBlt, LineTo, Ellipse, SelectObject.\n\n" +
            "CString — a managed string class that replaces TCHAR[] arrays. Supports formatting, searching, and automatic resource management.\n\n" +
            "CList<T, ARG_T>, CMap<KEY, ARG_KEY, VALUE, ARG_VALUE>, CArray<T, ARG_T> — simple MFC collection templates.\n\n" +
            "CFile / CArchive — file I/O and binary serialization.\n\n" +
            "CToolBar, CStatusBar, CTreeCtrl, CListCtrl, CTabCtrl — common controls wrappers."
        )
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("DIALOG DATA EXCHANGE (DDX / DDV)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "DDX (Dialog Data eXchange) binds dialog controls to member variables. DDV (Dialog Data Validation) enforces constraints.\n\n" +
            "UpdateData(TRUE) reads controls into member variables. UpdateData(FALSE) writes member variables back into controls."
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock("""
class CMyDialog : public CDialog {
public:
    CString m_strName;
    int     m_nAge;

protected:
    void DoDataExchange(CDataExchange* pDX) override {
        CDialog::DoDataExchange(pDX);
        DDX_Text(pDX, IDC_EDIT_NAME, m_strName);
        DDV_MaxChars(pDX, m_strName, 50);   // validate: max 50 chars
        DDX_Text(pDX, IDC_EDIT_AGE,  m_nAge);
        DDV_MinMaxInt(pDX, m_nAge, 0, 150); // validate: 0..150
    }

    void OnOK() override {
        UpdateData(TRUE); // read controls → member vars
        // use m_strName, m_nAge
        CDialog::OnOK();
    }

    BOOL OnInitDialog() override {
        CDialog::OnInitDialog();
        m_strName = _T("Default");
        UpdateData(FALSE); // write member vars → controls
        return TRUE;
    }
};
        """.trimIndent())
        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("MFC TODAY")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(
            "MFC is legacy technology. New Windows desktop projects typically use the Win32 API directly, WinUI 3, Qt, or wxWidgets.\n\n" +
            "However, MFC is still heavily used in enterprise environments where large existing codebases (CAD tools, ERP clients, industrial software) were built on it and continue to be maintained.\n\n" +
            "Microsoft still ships MFC with Visual Studio Community, Professional, and Enterprise, and it receives occasional maintenance updates. It is not being removed."
        )
        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
