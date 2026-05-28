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
import com.example.developmentapp.screens.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lesson01WhatIsUnityScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Unity — Lesson 1 — What is Unity?",
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
                        "• Game engines\n\n" +
                        "A game engine is a software framework that provides the core systems needed to " +
                        "build a game: rendering, physics, audio, input handling, scripting, and asset " +
                        "management. Instead of building everything from scratch, developers use an engine " +
                        "to focus on game logic and content. Popular engines include Unity, Unreal Engine, " +
                        "Godot, and GameMaker."
                    )
                    BodyText(
                        "• Unity Hub\n\n" +
                        "Unity Hub is a desktop application that manages all your Unity installations and " +
                        "projects. It lets you install multiple versions of the Unity Editor side by side, " +
                        "create new projects with templates, open existing projects, and install additional " +
                        "modules such as Android or iOS build support. Always start by installing Unity Hub " +
                        "before installing the Editor."
                    )
                    BodyText(
                        "• Installing Unity\n\n" +
                        "Download Unity Hub from unity.com. After installing Hub, go to the Installs tab " +
                        "and add an Editor version. Choose an LTS release for stability. During installation " +
                        "you can select add-on modules — for Android development, check Android Build Support " +
                        "(which includes Android SDK and NDK tools)."
                    )
                    BodyText(
                        "• LTS vs Beta versions\n\n" +
                        "Unity releases two kinds of versions: LTS (Long-Term Support) and TECH streams. " +
                        "LTS versions receive bug-fix patches for two years after release with no new " +
                        "features added, making them stable choices for shipping products. TECH stream " +
                        "versions arrive more frequently and include the latest features, but may have more " +
                        "bugs. For serious projects, always use the latest LTS."
                    )
                    BodyText(
                        "• Creating projects\n\n" +
                        "In Unity Hub, click New Project. Choose a template — Universal 2D, Universal 3D, " +
                        "or Universal RP are common starting points. Give your project a name and choose a " +
                        "location on disk. Unity generates the folder structure, pulls in required packages, " +
                        "and opens the Editor with a default scene."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Practice") {
                    BodyText("• Create a 2D project\n\nIn Unity Hub click New Project, select the 2D template, name it \"My2DGame\", and click Create. Explore the Scene view and notice the default orthographic camera.")
                    BodyText("• Create a 3D project\n\nRepeat the process with the 3D (URP) template. Notice the perspective camera and the directional light in the Hierarchy.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Homework") {
                    BodyText("• Explore the editor interface\n\nOpen both projects and spend time rearranging panels, docking windows, and resizing views. Familiarize yourself with where the Scene, Game, Hierarchy, Inspector, Project, and Console panels live before the next lesson.")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
