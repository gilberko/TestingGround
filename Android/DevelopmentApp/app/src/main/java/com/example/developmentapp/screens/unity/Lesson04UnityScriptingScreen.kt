package com.example.developmentapp.screens.unity

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
fun Lesson04UnityScriptingScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Unity — Lesson 4 — Unity Scripting",
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
                SectionCard(title = "Topics") {
                    BodyText(
                        "• MonoBehaviour\n\n" +
                        "MonoBehaviour is the base class for all Unity scripts. When you create a new " +
                        "C# script in Unity it automatically inherits from MonoBehaviour. This gives your " +
                        "class access to all Unity lifecycle callbacks, component references (transform, " +
                        "gameObject), and helper methods like Invoke and StartCoroutine. Attach a " +
                        "MonoBehaviour script to a GameObject by dragging it from the Project window onto " +
                        "the object."
                    )
                    BodyText(
                        "• Start()\n\n" +
                        "Start() is called once, on the first frame the script is enabled, before any " +
                        "Update() call. Use it to initialize variables, find references to other components, " +
                        "or spawn objects at game start. It runs after Awake() (which runs even if the " +
                        "script is disabled)."
                    )
                    CodeBlock(
                        "void Start() {\n" +
                        "    rb = GetComponent<Rigidbody2D>();\n" +
                        "    score = 0;\n" +
                        "    Debug.Log(\"Game started\");\n" +
                        "}"
                    )
                    BodyText(
                        "• Update()\n\n" +
                        "Update() is called once per frame — typically 30 to 120 times per second depending " +
                        "on hardware and settings. Use it for input reading, non-physics movement, and " +
                        "game logic that must run every frame. Because the frame rate varies, always " +
                        "multiply movement values by Time.deltaTime to keep speed consistent."
                    )
                    CodeBlock(
                        "void Update() {\n" +
                        "    float h = Input.GetAxis(\"Horizontal\");\n" +
                        "    transform.Translate(h * speed * Time.deltaTime, 0, 0);\n" +
                        "}"
                    )
                    BodyText(
                        "• Time.deltaTime\n\n" +
                        "Time.deltaTime is the time in seconds that elapsed since the last frame. On a " +
                        "60 fps machine it is roughly 0.0167 seconds; on a 30 fps machine it is 0.033. " +
                        "By multiplying your movement or animation values by Time.deltaTime you ensure the " +
                        "game runs at the same effective speed regardless of frame rate — a character set " +
                        "to move 5 units per second will travel 5 units per second on any machine."
                    )
                    BodyText(
                        "• Input system\n\n" +
                        "Unity has two input systems. The legacy Input class (Input.GetKey, Input.GetAxis, " +
                        "Input.GetMouseButton) is simple and works out of the box. The newer Input System " +
                        "package is event-driven and supports rebinding, gamepad, touch, and multiple " +
                        "players more cleanly. For beginners, start with the legacy Input class."
                    )
                    CodeBlock(
                        "// Legacy Input examples\n" +
                        "if (Input.GetKeyDown(KeyCode.Space))  jump();\n" +
                        "float x = Input.GetAxis(\"Horizontal\");  // -1 to 1\n" +
                        "if (Input.GetMouseButtonDown(0))  shoot();  // left click"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Practice") {
                    BodyText("• Keyboard movement\n\nCreate a cube, attach a script, and in Update() read Input.GetAxis(\"Horizontal\") and Input.GetAxis(\"Vertical\") to move the object with the arrow keys or WASD. Multiply by speed * Time.deltaTime.")
                    BodyText("• Mouse input\n\nIn Update(), detect Input.GetMouseButtonDown(0) and print the mouse position (Input.mousePosition) to the Console when the left button is clicked.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Mini Project") {
                    BodyText("• Controllable player object\n\nCreate a 2D scene with a sprite. Attach a script that moves it left/right with the arrow keys and up/down, keeping movement frame-rate independent. Add a speed public float so you can tune it in the Inspector without recompiling.")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
