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
fun Lesson11World3DBasicsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Unity — Lesson 11 — 3D World Basics",
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
                        "• 3D coordinates\n\n" +
                        "Unity uses a left-handed coordinate system. X points right, Y points up, and Z " +
                        "points forward (into the screen). Positions and rotations are represented as " +
                        "Vector3(x, y, z). When you move an object forward in local space, you move it " +
                        "along its own Z axis — use transform.forward (a unit vector in world space) " +
                        "for movement relative to the object's facing direction."
                    )
                    BodyText(
                        "• Materials\n\n" +
                        "A Material defines how a surface looks when lit — its color, texture, shininess, " +
                        "and transparency. Create a Material in the Project window (right-click → Create " +
                        "→ Material). In URP, the default shader is Lit. Set the Base Map to a texture " +
                        "or color. Drag the material onto a MeshRenderer in the scene or Inspector. " +
                        "Metallic and Smoothness sliders control the physically-based appearance."
                    )
                    BodyText(
                        "• Lighting\n\n" +
                        "Unity supports several light types: Directional (sunlight, infinite distance, " +
                        "casts shadows across the scene), Point (a bulb radiating in all directions with " +
                        "a range), Spot (a cone of light), and Area (emits from a rectangle, baked only). " +
                        "The Directional Light's rotation determines the sun angle. For indoor scenes, " +
                        "use Point lights for lamps. Adjust intensity and color in the Inspector."
                    )
                    BodyText(
                        "• Skyboxes\n\n" +
                        "A Skybox is the background image or color visible when no geometry is behind the " +
                        "camera. In URP, set the skybox in Window → Rendering → Lighting → Environment. " +
                        "Unity includes several Skybox shaders: Procedural (dynamic sun/sky), 6 Sided " +
                        "(six individual textures forming a cube), and Panoramic (a 360° equirectangular " +
                        "image). HDR skyboxes can also contribute to ambient lighting via Environment " +
                        "Lighting settings."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Practice") {
                    BodyText(
                        "• Create a 3D environment\n\n" +
                        "Build a small outdoor scene using a Plane as the ground. Add several Cube and " +
                        "Sphere primitives as props. Create materials for each with different colors and " +
                        "smoothness values. Add a Directional Light and rotate it to cast interesting " +
                        "shadows. Set a Procedural Skybox and adjust the sun size. Add a Point Light " +
                        "inside an archway to create an indoor glow effect."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
