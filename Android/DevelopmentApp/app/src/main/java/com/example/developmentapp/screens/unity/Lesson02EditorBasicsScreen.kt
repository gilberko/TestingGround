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
fun Lesson02EditorBasicsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Unity — Lesson 2 — Unity Editor Basics",
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
                        "• Scene view\n\n" +
                        "The Scene view is your 3D/2D editing viewport. You navigate it with the middle " +
                        "mouse button to pan, scroll to zoom, and right-click drag to rotate. Use Q/W/E/R " +
                        "shortcut keys to switch between the Hand, Move, Rotate, and Scale tools. Everything " +
                        "you build lives here before you press Play."
                    )
                    BodyText(
                        "• Game view\n\n" +
                        "The Game view shows what the camera sees — exactly what the player will see at " +
                        "runtime. Press the Play button (▶) to enter Play Mode, which runs your game inside " +
                        "the editor. Changes made during Play Mode are not saved — it is only for testing. " +
                        "Press Play again to stop."
                    )
                    BodyText(
                        "• Hierarchy\n\n" +
                        "The Hierarchy panel lists every GameObject in the current scene. GameObjects can " +
                        "be nested — dragging one object onto another makes it a child. Children inherit " +
                        "the parent's transform, so moving the parent moves all children together. Right-" +
                        "click in the Hierarchy to create new GameObjects (empty objects, 3D shapes, lights, " +
                        "cameras, etc.)."
                    )
                    BodyText(
                        "• Inspector\n\n" +
                        "Select any GameObject in the Hierarchy or Scene view and the Inspector shows all " +
                        "Components attached to it. A Component is a modular piece of behavior or data — " +
                        "Transform, MeshRenderer, Rigidbody, and your own scripts are all Components. You " +
                        "can add, remove, enable, and tweak components directly in the Inspector without " +
                        "opening a script."
                    )
                    BodyText(
                        "• Project window\n\n" +
                        "The Project window is your file browser for all assets: textures, audio clips, " +
                        "scripts, scenes, prefabs, materials, and more. Assets live in the Assets/ folder " +
                        "on disk. Drag an asset from the Project window into the Scene or Hierarchy to use " +
                        "it. Unity also stores auto-generated metadata in a Library/ folder — never manually " +
                        "delete it."
                    )
                    BodyText(
                        "• Console\n\n" +
                        "The Console displays log messages, warnings, and errors from both editor scripts " +
                        "and your running game. Use Debug.Log(\"message\") in your scripts to print " +
                        "information. Errors appear in red and include a stack trace. Click a message to " +
                        "jump to the script line that produced it. Always check the Console after pressing " +
                        "Play."
                    )
                    BodyText(
                        "• Prefabs\n\n" +
                        "A Prefab is a reusable template of a GameObject (with all its components and " +
                        "children). Drag a GameObject from the Hierarchy into the Project window to create " +
                        "a Prefab. You can then instantiate copies of it at runtime with Instantiate(). " +
                        "Editing the Prefab asset updates all instances in all scenes."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Practice") {
                    BodyText("• Build a simple room\n\nCreate a new 3D scene. Use 3D Object → Cube to create floor, ceiling, and four walls. Scale each cube using the Inspector Transform fields. Apply basic materials (create a Material in the Project window, assign a color, drag it onto the object).")
                    BodyText("• Add cubes/sprites\n\nIn the 3D scene add several more cubes of different sizes and positions to furnish the room. In a 2D scene, import a PNG image and set its Texture Type to Sprite, then drag it into the scene.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Mini Project") {
                    BodyText("• Create a basic playable scene\n\nAdd a plane as a floor, place a few cubes on it, add a camera pointing at the scene, and press Play to verify the scene renders correctly. Add a Directional Light and experiment with its rotation to change shadows.")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
