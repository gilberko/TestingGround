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
fun Lesson09UISystemScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Unity — Lesson 9 — UI System",
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
                        "• Canvas\n\n" +
                        "The Canvas is the root container for all Unity UI elements. Create one via " +
                        "GameObject → UI → Canvas. The Canvas has three Render Modes: Screen Space Overlay " +
                        "(UI drawn on top of the screen, no camera needed), Screen Space Camera (UI " +
                        "rendered by a specific camera, allows world effects), and World Space (UI exists " +
                        "in 3D space, useful for in-world labels or VR). For most games, Screen Space " +
                        "Overlay is the simplest starting point."
                    )
                    BodyText(
                        "• Buttons\n\n" +
                        "Add a Button from GameObject → UI → Button. The Button component has an OnClick " +
                        "event that you can wire up in the Inspector to call any public method on any " +
                        "component in the scene. Alternatively, subscribe to it in code:"
                    )
                    CodeBlock(
                        "using UnityEngine.UI;\n\n" +
                        "public Button startButton;\n\n" +
                        "void Start() {\n" +
                        "    startButton.onClick.AddListener(StartGame);\n" +
                        "}\n\n" +
                        "void StartGame() {\n" +
                        "    SceneManager.LoadScene(\"GameScene\");\n" +
                        "}"
                    )
                    BodyText(
                        "• Health bars\n\n" +
                        "A health bar is typically an Image with Image Type set to Filled. Set Fill Method " +
                        "to Horizontal and Fill Amount (0.0 to 1.0) to the player's current health as a " +
                        "fraction of max health. Update it from script whenever the player takes damage."
                    )
                    CodeBlock(
                        "public Image healthBar;\n\n" +
                        "void UpdateHealthBar(float current, float max) {\n" +
                        "    healthBar.fillAmount = current / max;\n" +
                        "}"
                    )
                    BodyText(
                        "• Menus\n\n" +
                        "A main menu is a Canvas with Panel backgrounds and Button children. Use " +
                        "SceneManager.LoadScene(\"SceneName\") to transition to the game scene when the " +
                        "player clicks Play, and Application.Quit() to exit. For pause menus, toggle a " +
                        "Panel's SetActive state and set Time.timeScale = 0 to freeze game time."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Practice") {
                    BodyText(
                        "• Main menu scene\n\n" +
                        "Create a new scene called MainMenu. Add a Canvas with a background Panel, a " +
                        "TextMeshPro title, a Play button, and a Quit button. Wire Play to load the game " +
                        "scene and Quit to Application.Quit(). Add the scenes to Build Settings and test " +
                        "the transition."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
