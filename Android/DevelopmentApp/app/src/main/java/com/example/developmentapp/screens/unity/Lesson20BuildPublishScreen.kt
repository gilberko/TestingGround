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
fun Lesson20BuildPublishScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Unity — Lesson 20 — Build & Publish",
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
                        "• PC builds\n\n" +
                        "To build for Windows/Mac/Linux, open File → Build Settings, select PC, Mac & " +
                        "Linux Standalone as the platform, and click Switch Platform. Make sure all your " +
                        "scenes are added in the Scenes In Build list (the first scene is the startup " +
                        "scene). Click Build to produce an executable. On Windows this creates an .exe " +
                        "plus a _Data folder — distribute both together. Use IL2CPP scripting backend for " +
                        "better performance and obfuscation instead of Mono."
                    )
                    BodyText(
                        "• Android builds\n\n" +
                        "Switch the platform to Android in Build Settings. Unity requires Android SDK, " +
                        "NDK, and JDK — install them via Unity Hub's Add Modules. In Player Settings " +
                        "set your Company Name, Product Name, Package Name (e.g. com.studio.mygame), " +
                        "Minimum API Level (typically API 24+), and Target API Level. Build produces an " +
                        ".apk (direct install) or .aab (Android App Bundle, required for Google Play). " +
                        "Sign your build with a keystore for Play Store distribution."
                    )
                    BodyText(
                        "• Build settings\n\n" +
                        "Key settings in Edit → Project Settings → Player: Product Name and Version " +
                        "(shown to users), Scripting Backend (Mono for fast iteration, IL2CPP for " +
                        "release), API Compatibility Level (.NET Standard 2.1 recommended), and " +
                        "Optimization level. In Build Settings, Development Build adds the profiler " +
                        "and detailed error messages — disable it for final releases. Enable Strip " +
                        "Engine Code to reduce build size."
                    )
                    BodyText(
                        "• Input configuration\n\n" +
                        "Before building for multiple platforms, verify your input works on all target " +
                        "devices. If using the legacy Input Manager, check Edit → Project Settings → " +
                        "Input Manager for axis and button mappings. For Android, touch input maps to " +
                        "mouse events by default, but for proper multi-touch and gamepad support use " +
                        "the new Input System package with platform-specific action bindings."
                    )
                    CodeBlock(
                        "// Check platform at runtime\n" +
                        "#if UNITY_ANDROID\n" +
                        "    // Android-specific code\n" +
                        "    if (Input.touchCount > 0) { ... }\n" +
                        "#elif UNITY_STANDALONE\n" +
                        "    // PC-specific code\n" +
                        "#endif"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Practice") {
                    BodyText(
                        "• Export a game\n\n" +
                        "Take any project from this course. Add all scenes to the Build Settings list. " +
                        "Set a proper Company Name and Product Name. Build for PC and run the standalone " +
                        "executable — verify it works outside the editor. Then switch to Android, connect " +
                        "an Android device (enable Developer Options + USB Debugging), click Build And " +
                        "Run to deploy the APK directly to the device."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
