package com.example.linuxapp.screens.usermode

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.linuxapp.screens.kernel.BodyText
import com.example.linuxapp.screens.kernel.CodeBlock
import com.example.linuxapp.screens.kernel.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserModeGraphicalScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Graphical Interface",
                        color = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            item {
                SectionCard(title = "Display Protocols: X11 and Wayland") {
                    BodyText("Before picking a UI toolkit, understand the display protocol — the layer that connects your app to the screen.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("X11 (X Window System):")
                    BodyText("• The classic protocol, in use since 1987")
                    BodyText("• Still very common; most software supports it")
                    BodyText("• A separate X server process handles all rendering and input")
                    BodyText("• Apps talk to Xorg via the X11 protocol (Xlib or XCB library)")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Wayland:")
                    BodyText("• Modern replacement for X11, default on GNOME and KDE Plasma since ~2021")
                    BodyText("• The compositor IS the display server — no separate Xorg process")
                    BodyText("• Better security (apps can't snoop on each other), smoother rendering")
                    BodyText("• XWayland provides backwards compatibility for legacy X11 apps")
                    Spacer(Modifier.height(6.dp))
                    BodyText("You rarely interact with X11 or Wayland directly — UI toolkits (GTK, Qt) abstract this and handle both protocols automatically.")
                }
            }
            item {
                SectionCard(title = "GTK — GNOME's Native Toolkit") {
                    BodyText("GTK (GIMP Toolkit) is the primary toolkit for GNOME applications. Current version: GTK4.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Core characteristics:")
                    BodyText("• Written in C; object-oriented via GObject type system")
                    BodyText("• Companion libraries: GLib (data structures, main loop), GIO (async I/O, D-Bus), GObject")
                    BodyText("• Native look on GNOME; works fine on KDE and other desktops")
                    BodyText("• Supports both X11 and Wayland backends")
                    BodyText("• UI can be defined in XML (.ui files) using Glade or Blueprint")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Language bindings:")
                    BodyText("• C — native, direct API")
                    BodyText("• Python — PyGObject (gi.repository.Gtk)")
                    BodyText("• Rust — gtk-rs crate")
                    BodyText("• C++ — gtkmm")
                    BodyText("• JavaScript — GJS (used by GNOME Shell extensions)")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock("""# Minimal GTK4 app in Python (PyGObject):
import gi
gi.require_version('Gtk', '4.0')
from gi.repository import Gtk

def on_activate(app):
    win = Gtk.ApplicationWindow(application=app, title="Hello")
    btn = Gtk.Button(label="Click me")
    btn.connect("clicked", lambda b: print("Hello!"))
    win.set_child(btn)
    win.present()

app = Gtk.Application(application_id='com.example.hello')
app.connect('activate', on_activate)
app.run()""")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock("""# Install GTK4 dev libraries:
sudo apt install libgtk-4-dev python3-gi  # Debian/Ubuntu
sudo dnf install gtk4-devel python3-gobject  # Fedora""")
                }
            }
            item {
                SectionCard(title = "Qt — KDE's Native Toolkit") {
                    BodyText("Qt is the primary toolkit for KDE applications. Current version: Qt6. It is a full application framework, not just a UI library.")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Core characteristics:")
                    BodyText("• Written in C++; uses the Meta-Object Compiler (moc) for signals/slots")
                    BodyText("• Modules: QtWidgets (classic desktop UI), QtQuick/QML (declarative modern UI), QtNetwork, QtConcurrent, and many more")
                    BodyText("• Native look on KDE; works fine on GNOME and other desktops")
                    BodyText("• Supports X11, Wayland, Windows, macOS, Android, iOS")
                    BodyText("• KDE Frameworks (KF6) extends Qt with KDE-specific modules: KConfig, KIO, KWidgetsAddons, Kirigami (responsive UI framework)")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Language bindings:")
                    BodyText("• C++ — native")
                    BodyText("• Python — PyQt6 (GPL/commercial) or PySide6 (LGPL, official from Qt)")
                    BodyText("• Rust — qmetaobject-rs, cxx-qt")
                    BodyText("• QML — Qt's own declarative language (similar to JavaScript + JSON)")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock("""# Minimal Qt6 app in Python (PySide6):
from PySide6.QtWidgets import QApplication, QPushButton
import sys

app = QApplication(sys.argv)
btn = QPushButton("Click me")
btn.clicked.connect(lambda: print("Hello!"))
btn.show()
sys.exit(app.exec())""")
                    Spacer(Modifier.height(6.dp))
                    CodeBlock("""# Install Qt6 dev libraries:
sudo apt install qt6-base-dev python3-pyside6  # Debian/Ubuntu
sudo dnf install qt6-qtbase-devel python3-pyside6  # Fedora

# Or install via pip:
pip install PySide6""")
                }
            }
            item {
                SectionCard(title = "Cross-Platform and Other Toolkits") {
                    BodyText("wxWidgets:")
                    BodyText("• C++ toolkit that wraps the native widgets of each platform (GTK on Linux, Win32 on Windows, Cocoa on macOS)")
                    BodyText("• True native look-and-feel on every platform")
                    BodyText("• Python binding: wxPython")
                    Spacer(Modifier.height(6.dp))
                    BodyText("FLTK (Fast Light Toolkit):")
                    BodyText("• Very lightweight C++ toolkit")
                    BodyText("• Draws its own widgets (not native); minimal dependencies")
                    BodyText("• Good for embedded or resource-constrained environments")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Dear ImGui:")
                    BodyText("• Immediate mode GUI library (C++)")
                    BodyText("• No retained widget state; redraws everything every frame")
                    BodyText("• Ideal for developer tools, game UIs, debug overlays — not typical desktop apps")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Electron:")
                    BodyText("• Bundles Chromium + Node.js into a desktop app")
                    BodyText("• Write UI in HTML, CSS, JavaScript")
                    BodyText("• Works on Linux, Windows, macOS")
                    BodyText("• Very heavy (Chromium bundle ~150 MB); high RAM usage")
                    BodyText("• Used by VS Code, Slack, Discord, Spotify")
                }
            }
            item {
                SectionCard(title = "High-Level / Scripting Options") {
                    BodyText("PyGObject (GTK + Python):")
                    BodyText("• Use GTK4 from Python via gi.repository — fast to prototype")
                    BodyText("• Suitable for real apps (GNOME apps like Rhythmbox use it)")
                    CodeBlock("""pip install pygobject   # or use system package""")
                    Spacer(Modifier.height(6.dp))
                    BodyText("PySide6 / PyQt6 (Qt + Python):")
                    BodyText("• Full Qt6 bindings; QtWidgets for classic UI, QML for modern UI")
                    BodyText("• PySide6 is LGPL (free for commercial use); PyQt6 is GPL/commercial")
                    CodeBlock("""pip install PySide6""")
                    Spacer(Modifier.height(6.dp))
                    BodyText("Rust UI options:")
                    BodyText("• egui — immediate mode, pure Rust, runs on native + WASM")
                    BodyText("• iced — Elm-inspired reactive UI, pure Rust")
                    BodyText("• Relm4 — GTK4 wrapper for Rust following the Elm pattern")
                    CodeBlock("""# Cargo.toml
[dependencies]
eframe = "0.27"   # egui + native backend""")
                }
            }
            item {
                SectionCard(title = "Choosing a Toolkit") {
                    BodyText("Quick reference:")
                    Spacer(Modifier.height(4.dp))
                    CodeBlock("""Use case                        → Recommended toolkit
──────────────────────────────────────────────────────
GNOME-native look, C/C++        → GTK4
GNOME-native look, Python       → PyGObject (GTK4)
KDE-native look, C++            → Qt6 + KDE Frameworks
KDE-native look, Python         → PySide6
Cross-platform C++ desktop      → Qt6 (Widgets or QML)
Cross-platform Python desktop   → PySide6 or PyGObject
Rapid Python prototyping        → PySide6 (easiest API)
Developer tools / game UI       → Dear ImGui
Web-developer background        → Electron
Lightweight / embedded          → FLTK
Rust application                → iced or egui""")
                    Spacer(Modifier.height(6.dp))
                    BodyText("GTK apps run fine on KDE and Qt apps run fine on GNOME — the desktop environment does not restrict which toolkit you use. The choice affects the visual style and which platform feels most \"at home\".")
                }
            }
            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}
