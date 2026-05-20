package com.example.developmentapp.screens.java

import androidx.compose.foundation.layout.Spacer
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
import com.example.developmentapp.screens.BodyText
import com.example.developmentapp.screens.CodeBlock
import com.example.developmentapp.screens.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JavaAwtScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Java — AWT",
                        color      = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontSize   = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector        = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint               = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding).padding(horizontal = 16.dp)) {
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "What Is AWT") {
                    BodyText(
                        "AWT (Abstract Window Toolkit) is Java's original GUI library, shipped since " +
                        "JDK 1.0 (1995). It wraps the native OS widgets — on Windows you get Windows " +
                        "controls, on macOS you get macOS controls, on Linux you get Motif/GTK widgets. " +
                        "This means AWT programs look like native apps on each platform but can look " +
                        "inconsistent across platforms.\n\n" +
                        "Components are called \"heavyweight\" because each one is backed by a real OS " +
                        "window handle (HWND on Windows, X window on Linux). This gives them the native " +
                        "look but limits customisation and causes z-order issues when mixing with Swing.\n\n" +
                        "AWT was superseded by Swing (Java 1.2) and later JavaFX. It is still present " +
                        "in the JDK and some legacy systems use it."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Core Components") {
                    BodyText(
                        "All AWT classes live in java.awt. Key components:\n" +
                        "• Frame — top-level window with title bar and border\n" +
                        "• Panel — lightweight container for grouping components\n" +
                        "• Button — clickable button\n" +
                        "• TextField — single-line text input\n" +
                        "• TextArea — multi-line text input\n" +
                        "• Label — non-editable text\n" +
                        "• Checkbox — toggle checkbox or radio button (CheckboxGroup)\n" +
                        "• Choice — drop-down menu\n" +
                        "• List — scrollable list of choices\n" +
                        "• Canvas — blank drawing surface\n\n" +
                        "Layout is controlled by LayoutManagers (FlowLayout, BorderLayout, GridLayout, " +
                        "CardLayout, GridBagLayout)."
                    )
                    CodeBlock(
                        "import java.awt.*;\n\n" +
                        "Frame frame = new Frame(\"My Window\");\n" +
                        "frame.setLayout(new FlowLayout());\n" +
                        "frame.setSize(400, 300);\n\n" +
                        "Label  label = new Label(\"Name:\");\n" +
                        "TextField tf = new TextField(20);  // 20 columns wide\n" +
                        "Button btn   = new Button(\"Submit\");\n\n" +
                        "frame.add(label);\n" +
                        "frame.add(tf);\n" +
                        "frame.add(btn);\n" +
                        "frame.setVisible(true);"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Event Handling") {
                    BodyText(
                        "AWT uses the listener/observer pattern. Attach a listener to a component; " +
                        "the listener's method is called when the event fires. The relevant interface for " +
                        "buttons is ActionListener with its single method actionPerformed(ActionEvent e).\n\n" +
                        "Since Java 8, ActionListener is a functional interface, so you can use a lambda " +
                        "instead of an anonymous class. Other listeners: MouseListener, KeyListener, " +
                        "WindowListener (for window-close events), etc."
                    )
                    CodeBlock(
                        "// Lambda (Java 8+)\n" +
                        "btn.addActionListener(e -> System.out.println(\"Clicked!\"));\n\n" +
                        "// Anonymous class (older style)\n" +
                        "btn.addActionListener(new ActionListener() {\n" +
                        "    @Override\n" +
                        "    public void actionPerformed(ActionEvent e) {\n" +
                        "        System.out.println(\"Clicked!\");\n" +
                        "    }\n" +
                        "});\n\n" +
                        "// Window close\n" +
                        "frame.addWindowListener(new WindowAdapter() {\n" +
                        "    @Override\n" +
                        "    public void windowClosing(WindowEvent e) {\n" +
                        "        System.exit(0);\n" +
                        "    }\n" +
                        "});"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The Event Dispatch Thread") {
                    BodyText(
                        "There is NO blocking main() call in AWT. When you call frame.setVisible(true), " +
                        "AWT starts its Event Dispatch Thread (EDT) in the background. The main thread " +
                        "can exit and the JVM stays alive because the EDT is a non-daemon thread.\n\n" +
                        "The EDT runs the event loop — it continuously polls for OS events (mouse clicks, " +
                        "key presses, repaints) and dispatches them to listeners. All UI updates must " +
                        "happen on the EDT to avoid race conditions. Use EventQueue.invokeLater(Runnable) " +
                        "to schedule code on the EDT from another thread.\n\n" +
                        "Never do long blocking work (network calls, file I/O) on the EDT — it freezes " +
                        "the UI. Offload to a background thread, then post the result back via invokeLater."
                    )
                    CodeBlock(
                        "// main() exits after this — EDT keeps JVM alive\n" +
                        "frame.setVisible(true);\n\n" +
                        "// Schedule UI update from a background thread\n" +
                        "new Thread(() -> {\n" +
                        "    String result = fetchDataFromNetwork();  // slow work\n" +
                        "    EventQueue.invokeLater(() -> {\n" +
                        "        label.setText(result);              // back on EDT\n" +
                        "    });\n" +
                        "}).start();"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Problems With AWT") {
                    BodyText(
                        "• Outdated API — designed in 1995, shows its age\n" +
                        "• Native widgets = limited customisation; you cannot change a Button's font " +
                        "or background colour consistently across platforms\n" +
                        "• Inconsistent look — same code looks different on Windows, macOS, and Linux\n" +
                        "• No rich components — no tables, trees, tabbed panes, sliders, etc.\n" +
                        "• Z-order bugs — mixing AWT heavyweight components with Swing lightweight " +
                        "components causes AWT to always render on top regardless of Z-order\n" +
                        "• No double buffering by default — painting can flicker\n\n" +
                        "Swing and JavaFX solve all of these. AWT is only relevant for legacy code."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Example — Two Buttons") {
                    BodyText(
                        "A complete AWT program with two buttons: one shows a message box, one exits. " +
                        "WindowAdapter handles the close button on the title bar. Note that main() " +
                        "returns immediately after setVisible — the EDT keeps the program alive."
                    )
                    CodeBlock(
                        "import java.awt.*;\n" +
                        "import java.awt.event.*;\n" +
                        "import javax.swing.JOptionPane;\n\n" +
                        "public class AwtDemo {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        Frame frame = new Frame(\"AWT Demo\");\n" +
                        "        frame.setLayout(new FlowLayout());\n" +
                        "        frame.setSize(300, 150);\n\n" +
                        "        Button msgBtn  = new Button(\"Show Message\");\n" +
                        "        Button exitBtn = new Button(\"Exit\");\n\n" +
                        "        // Button 1: show a message dialog\n" +
                        "        msgBtn.addActionListener(e ->\n" +
                        "            JOptionPane.showMessageDialog(frame,\n" +
                        "                \"Hello from AWT!\"));\n\n" +
                        "        // Button 2: exit\n" +
                        "        exitBtn.addActionListener(e -> System.exit(0));\n\n" +
                        "        // Handle title-bar close button\n" +
                        "        frame.addWindowListener(new WindowAdapter() {\n" +
                        "            @Override\n" +
                        "            public void windowClosing(WindowEvent e) {\n" +
                        "                System.exit(0);\n" +
                        "            }\n" +
                        "        });\n\n" +
                        "        frame.add(msgBtn);\n" +
                        "        frame.add(exitBtn);\n" +
                        "        frame.setVisible(true);\n" +
                        "        // main() ends here; EDT keeps JVM alive\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
