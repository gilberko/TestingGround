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
fun UIProgrammingScreen(navController: NavController) {
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
            text = "UI PROGRAMMING",
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

        SectionHeader("WINDOWS AND MESSAGES")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Every UI element — window, button, edit box, list view — is identified by an HWND (handle to window).\n\nWindows communicate through messages. Each message carries: hwnd, UINT message ID, WPARAM wParam, LPARAM lParam. The meaning of wParam/lParam is message-specific.\n\nCommon messages:\n• WM_CREATE — window is being created\n• WM_DESTROY — window is being destroyed\n• WM_PAINT — window needs redrawing\n• WM_COMMAND — button or menu item activated\n• WM_CLOSE — user clicked the X button\n• WM_SIZE — window was resized\n• WM_KEYDOWN — a key was pressed\n• WM_LBUTTONDOWN — left mouse button clicked")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("THE MESSAGE LOOP")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Yes — every Win32 GUI application has a message loop. It is the heartbeat of the application, continuously dequeuing and dispatching events.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "MSG msg;\n" +
            "while (GetMessage(&msg, NULL, 0, 0)) {\n" +
            "    TranslateMessage(&msg); // VK → WM_CHAR\n" +
            "    DispatchMessage(&msg);  // call WndProc\n" +
            "}\n" +
            "return (int)msg.wParam;"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("GetMessage blocks until a message arrives. It returns 0 when WM_QUIT is dequeued — this ends the loop.\n\nPostQuitMessage(0) posts WM_QUIT to break the loop and exit the application.\n\nTranslateMessage converts virtual-key messages (WM_KEYDOWN) into character messages (WM_CHAR) for text input.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("WINDOW PROCEDURE (WNDPROC)")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Each window class has an associated WNDPROC. DispatchMessage calls it for every message routed to windows of that class.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "LRESULT CALLBACK WndProc(\n" +
            "    HWND hwnd, UINT msg,\n" +
            "    WPARAM wParam, LPARAM lParam)\n" +
            "{\n" +
            "    switch (msg) {\n" +
            "    case WM_DESTROY:\n" +
            "        PostQuitMessage(0);\n" +
            "        return 0;\n" +
            "    default:\n" +
            "        return DefWindowProc(\n" +
            "            hwnd, msg, wParam, lParam);\n" +
            "    }\n" +
            "}"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Unhandled messages must be passed to DefWindowProc for default behavior (close animation, resize, system menu, etc.).")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("CREATING A WINDOW")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("1. Fill WNDCLASSEX — icon, cursor, background brush, WndProc pointer, class name\n2. RegisterClassEx(&wc) — register the class with the OS\n3. CreateWindowEx(...) — create a window instance, returns HWND\n4. ShowWindow(hwnd, SW_SHOW) — make it visible\n5. UpdateWindow(hwnd) — force an immediate WM_PAINT")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("BUTTONS AS CHILD WINDOWS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("A button is a child window of the built-in class \"BUTTON\". Create it inside WM_CREATE:")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "CreateWindowEx(\n" +
            "    0, L\"BUTTON\", L\"Label\",\n" +
            "    WS_VISIBLE | WS_CHILD | BS_PUSHBUTTON,\n" +
            "    x, y, width, height,\n" +
            "    hwndParent,\n" +
            "    (HMENU)CONTROL_ID,\n" +
            "    hInstance, NULL);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("When clicked, the button sends WM_COMMAND to its parent window. LOWORD(wParam) is the control ID; HIWORD(wParam) is the notification code (BN_CLICKED = 0).")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("EXAMPLE — TWO BUTTONS")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "#define ID_BTN_HELLO 1\n" +
            "#define ID_BTN_EXIT  2\n\n" +
            "LRESULT CALLBACK WndProc(\n" +
            "    HWND hwnd, UINT msg,\n" +
            "    WPARAM wParam, LPARAM lParam)\n" +
            "{\n" +
            "    switch (msg) {\n" +
            "    case WM_CREATE:\n" +
            "        CreateWindowEx(\n" +
            "            0, L\"BUTTON\", L\"Say Hello\",\n" +
            "            WS_VISIBLE|WS_CHILD|BS_PUSHBUTTON,\n" +
            "            50, 50, 130, 40, hwnd,\n" +
            "            (HMENU)ID_BTN_HELLO,\n" +
            "            NULL, NULL);\n" +
            "        CreateWindowEx(\n" +
            "            0, L\"BUTTON\", L\"Exit\",\n" +
            "            WS_VISIBLE|WS_CHILD|BS_PUSHBUTTON,\n" +
            "            210, 50, 130, 40, hwnd,\n" +
            "            (HMENU)ID_BTN_EXIT,\n" +
            "            NULL, NULL);\n" +
            "        return 0;\n" +
            "    case WM_COMMAND:\n" +
            "        if (LOWORD(wParam) == ID_BTN_HELLO)\n" +
            "            MessageBox(hwnd,\n" +
            "                L\"Hello, World!\",\n" +
            "                L\"Greeting\", MB_OK);\n" +
            "        else if (LOWORD(wParam) == ID_BTN_EXIT)\n" +
            "            PostQuitMessage(0);\n" +
            "        return 0;\n" +
            "    case WM_DESTROY:\n" +
            "        PostQuitMessage(0);\n" +
            "        return 0;\n" +
            "    default:\n" +
            "        return DefWindowProc(\n" +
            "            hwnd, msg, wParam, lParam);\n" +
            "    }\n" +
            "}\n\n" +
            "int WINAPI WinMain(HINSTANCE hInst,\n" +
            "    HINSTANCE, LPSTR, int nCmdShow)\n" +
            "{\n" +
            "    WNDCLASSEX wc = { sizeof(wc),\n" +
            "        CS_HREDRAW|CS_VREDRAW, WndProc,\n" +
            "        0, 0, hInst,\n" +
            "        LoadIcon(NULL, IDI_APPLICATION),\n" +
            "        LoadCursor(NULL, IDC_ARROW),\n" +
            "        (HBRUSH)(COLOR_WINDOW+1),\n" +
            "        NULL, L\"MyWndClass\",\n" +
            "        LoadIcon(NULL, IDI_APPLICATION) };\n" +
            "    RegisterClassEx(&wc);\n\n" +
            "    HWND hwnd = CreateWindowEx(\n" +
            "        0, L\"MyWndClass\",\n" +
            "        L\"Two Buttons Demo\",\n" +
            "        WS_OVERLAPPEDWINDOW,\n" +
            "        CW_USEDEFAULT, CW_USEDEFAULT,\n" +
            "        420, 160,\n" +
            "        NULL, NULL, hInst, NULL);\n" +
            "    ShowWindow(hwnd, nCmdShow);\n" +
            "    UpdateWindow(hwnd);\n\n" +
            "    MSG msg;\n" +
            "    while (GetMessage(&msg, NULL, 0, 0)) {\n" +
            "        TranslateMessage(&msg);\n" +
            "        DispatchMessage(&msg);\n" +
            "    }\n" +
            "    return (int)msg.wParam;\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("CODE WALKTHROUGH")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("WinMain: registers the window class, creates the main window, then enters the message loop.\n\nWM_CREATE: fired once when CreateWindowEx is called. The ideal place to create child controls — the two button windows are created here.\n\nWM_COMMAND: fires when a child control is activated. LOWORD(wParam) gives the control ID so we dispatch to the right handler.\n\nMessageBox: a blocking call that shows a modal dialog. The message loop runs inside it (Windows pumps messages internally), so the UI stays responsive.\n\nPostQuitMessage(0): posts WM_QUIT to the thread's message queue. The next GetMessage returns 0, the while loop exits, and the process terminates.\n\nWM_DESTROY: fired after the window is destroyed (after WM_CLOSE → DestroyWindow). We call PostQuitMessage here so closing the window ends the application.")

        Spacer(modifier = Modifier.height(32.dp))
        HackerButton("BACK") { navController.popBackStack() }
    }
}
