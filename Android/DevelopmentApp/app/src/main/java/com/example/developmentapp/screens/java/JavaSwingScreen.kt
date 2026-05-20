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
fun JavaSwingScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Java — Swing",
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
                SectionCard(title = "Swing vs AWT") {
                    BodyText(
                        "Swing (javax.swing) was introduced in Java 1.2 (1998) as the successor to AWT. " +
                        "It builds on top of AWT — it still uses the EDT and java.awt layout managers — " +
                        "but its components are \"lightweight\": they are drawn entirely by Java2D, not " +
                        "by the OS.\n\n" +
                        "Key advantages over AWT:\n" +
                        "• Consistent look across all platforms (no native widget differences)\n" +
                        "• Pluggable Look-and-Feel (LaF) — Metal (default), Nimbus, system native, or custom\n" +
                        "• Rich component set: JTable, JTree, JTabbedPane, JSlider, JSpinner, JComboBox, " +
                        "JSplitPane, JScrollPane, JToolBar, JMenuBar, JDialog, etc.\n" +
                        "• Full control over colours, fonts, borders, icons\n" +
                        "• Built-in double buffering — no flicker\n\n" +
                        "Swing components are prefixed with J (JButton vs Button, JFrame vs Frame)."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Core Components") {
                    BodyText(
                        "Most-used Swing components (all in javax.swing):\n" +
                        "• JFrame — top-level window; use setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) " +
                        "so closing the window exits the app\n" +
                        "• JPanel — general-purpose container (replaces AWT Panel)\n" +
                        "• JButton — push button; supports icons, mnemonics, tooltips\n" +
                        "• JLabel — text or icon label; supports HTML for rich formatting\n" +
                        "• JTextField — single-line text input\n" +
                        "• JTextArea + JScrollPane — multi-line text with scrollbars\n" +
                        "• JCheckBox / JRadioButton + ButtonGroup — toggles\n" +
                        "• JComboBox — drop-down list\n" +
                        "• JList — scrollable list\n" +
                        "• JTable — spreadsheet-style grid (uses TableModel)\n" +
                        "• JMenuBar + JMenu + JMenuItem — menu system\n" +
                        "• JOptionPane — ready-made dialogs (message, confirm, input)"
                    )
                    CodeBlock(
                        "JFrame frame = new JFrame(\"My App\");\n" +
                        "frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n" +
                        "frame.setSize(500, 400);\n\n" +
                        "JPanel panel = new JPanel(new BorderLayout());\n" +
                        "JLabel  label = new JLabel(\"<html><b>Name:</b></html>\");\n" +
                        "JTextField tf = new JTextField(20);\n" +
                        "JButton btn   = new JButton(\"OK\");\n\n" +
                        "panel.add(label, BorderLayout.WEST);\n" +
                        "panel.add(tf,    BorderLayout.CENTER);\n" +
                        "panel.add(btn,   BorderLayout.EAST);\n\n" +
                        "frame.setContentPane(panel);\n" +
                        "frame.setVisible(true);"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Event Loop — Same EDT as AWT") {
                    BodyText(
                        "Swing uses the same Event Dispatch Thread as AWT — there is no separate " +
                        "event loop to start and no blocking function to call. The JVM stays alive " +
                        "as long as a non-daemon thread (the EDT) is running.\n\n" +
                        "The idiomatic way to start a Swing UI is SwingUtilities.invokeLater(). This " +
                        "schedules your window-creation code to run on the EDT. If you construct Swing " +
                        "components on the main thread and the EDT touches them simultaneously, you get " +
                        "race conditions — invokeLater() prevents this.\n\n" +
                        "SwingUtilities.invokeAndWait() is the blocking variant — it waits until the " +
                        "EDT finishes the runnable. Never call it from the EDT itself (deadlock)."
                    )
                    CodeBlock(
                        "public static void main(String[] args) {\n" +
                        "    // Schedule UI creation on the EDT\n" +
                        "    SwingUtilities.invokeLater(() -> {\n" +
                        "        JFrame frame = new JFrame(\"Hello Swing\");\n" +
                        "        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n" +
                        "        frame.add(new JLabel(\"Hello!\"));\n" +
                        "        frame.pack();\n" +
                        "        frame.setVisible(true);\n" +
                        "    });\n" +
                        "    // main() exits; EDT keeps JVM alive\n" +
                        "}\n\n" +
                        "// Update UI from a background thread\n" +
                        "new Thread(() -> {\n" +
                        "    String data = fetchSomething();\n" +
                        "    SwingUtilities.invokeLater(() -> label.setText(data));\n" +
                        "}).start();"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Layout Managers") {
                    BodyText(
                        "Swing inherits AWT's layout managers and adds a few more. The most common:\n" +
                        "• FlowLayout — places components left-to-right, wraps to next line (default for JPanel)\n" +
                        "• BorderLayout — 5 regions: NORTH/SOUTH/EAST/WEST/CENTER (default for JFrame's content pane)\n" +
                        "• GridLayout(rows, cols) — equal-sized grid cells\n" +
                        "• BoxLayout — vertical or horizontal stack with flexible spacing\n" +
                        "• GridBagLayout — most powerful, most verbose; precise control via GridBagConstraints\n\n" +
                        "Control button size with setPreferredSize(new Dimension(width, height)). " +
                        "The layout manager uses preferred size as a hint (FlowLayout respects it; " +
                        "BorderLayout ignores width in NORTH/SOUTH)."
                    )
                    CodeBlock(
                        "// Mix layouts: outer BorderLayout, inner FlowLayout for buttons\n" +
                        "JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));\n\n" +
                        "JButton ok     = new JButton(\"OK\");\n" +
                        "JButton cancel = new JButton(\"Cancel\");\n" +
                        "ok.setPreferredSize(new Dimension(100, 35));\n" +
                        "cancel.setPreferredSize(new Dimension(80, 28));\n\n" +
                        "buttonPanel.add(ok);\n" +
                        "buttonPanel.add(cancel);\n\n" +
                        "frame.add(buttonPanel, BorderLayout.SOUTH);"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Example — Two Buttons of Different Sizes") {
                    BodyText(
                        "A complete Swing program. Button 1 is large (160×50) and shows a message " +
                        "dialog. Button 2 is small (80×30) and exits. SwingUtilities.invokeLater() " +
                        "ensures the UI is created on the EDT."
                    )
                    CodeBlock(
                        "import javax.swing.*;\n" +
                        "import java.awt.*;\n\n" +
                        "public class SwingDemo {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        SwingUtilities.invokeLater(() -> {\n" +
                        "            JFrame frame = new JFrame(\"Swing Demo\");\n" +
                        "            frame.setDefaultCloseOperation(\n" +
                        "                JFrame.EXIT_ON_CLOSE);\n" +
                        "            frame.setLayout(new FlowLayout());\n" +
                        "            frame.setSize(320, 120);\n\n" +
                        "            JButton bigBtn   = new JButton(\"Show Message\");\n" +
                        "            JButton smallBtn = new JButton(\"Exit\");\n\n" +
                        "            // Different sizes\n" +
                        "            bigBtn.setPreferredSize(\n" +
                        "                new Dimension(160, 50));\n" +
                        "            smallBtn.setPreferredSize(\n" +
                        "                new Dimension(80, 30));\n\n" +
                        "            bigBtn.addActionListener(e ->\n" +
                        "                JOptionPane.showMessageDialog(\n" +
                        "                    frame, \"Hello from Swing!\"));\n" +
                        "            smallBtn.addActionListener(\n" +
                        "                e -> System.exit(0));\n\n" +
                        "            frame.add(bigBtn);\n" +
                        "            frame.add(smallBtn);\n" +
                        "            frame.setVisible(true);\n" +
                        "        });\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "When to Use Swing Today") {
                    BodyText(
                        "Swing is fully functional but no longer actively developed. Sun/Oracle " +
                        "introduced JavaFX as the modern desktop UI toolkit (hardware-accelerated, CSS " +
                        "styling, FXML layout, animation, media). JavaFX was bundled with the JDK until " +
                        "Java 10, then separated into the OpenJFX project.\n\n" +
                        "Use Swing when:\n" +
                        "• Maintaining existing Swing desktop applications\n" +
                        "• You need a quick desktop tool with zero extra dependencies (Swing ships in the JDK)\n" +
                        "• The team already knows Swing and the project scope is small\n\n" +
                        "Use JavaFX for new desktop projects. It is included via Maven/Gradle " +
                        "(org.openjfx:javafx-controls) and provides a far richer developer experience."
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
