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
fun Lesson05Sprites2DScenesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Unity — Lesson 5 — Sprites and 2D Scenes",
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
                        "• Sprites\n\n" +
                        "A sprite is a 2D image used as a visual element in a 2D game. Import a PNG into " +
                        "Unity, set its Texture Type to Sprite (2D and UI) in the Inspector, and drag it " +
                        "into the scene. Unity creates a GameObject with a SpriteRenderer component that " +
                        "displays the image. You can tint, flip, or swap sprites at runtime through the " +
                        "SpriteRenderer component in scripts."
                    )
                    BodyText(
                        "• Pixels per unit\n\n" +
                        "Pixels Per Unit (PPU) controls the relationship between a sprite's pixel size and " +
                        "Unity's world units. The default is 100 PPU — a 100×100 pixel sprite appears as " +
                        "1×1 world unit. If your character sprite is 64×64 pixels at 64 PPU it occupies " +
                        "exactly 1×1 unit, making size relationships easier to reason about. Keep PPU " +
                        "consistent across all sprites in a project."
                    )
                    BodyText(
                        "• Sorting layers\n\n" +
                        "In 2D, Unity determines which sprites appear in front of others using Sorting " +
                        "Layers and Order in Layer. Define sorting layers in Project Settings → Tags and " +
                        "Layers (e.g. Background, Midground, Foreground, UI). Assign each SpriteRenderer " +
                        "a layer and an order number within that layer — higher numbers render on top. " +
                        "This controls draw order independently of Z position."
                    )
                    BodyText(
                        "• Tilemaps\n\n" +
                        "The Tilemap system lets you paint 2D levels using a tile palette — a grid of " +
                        "reusable sprites. Create a Grid GameObject, add a Tilemap child, open the Tile " +
                        "Palette window, drag sprites into it to create tiles, and paint your level. " +
                        "Tilemaps use Tilemap Collider 2D to generate collision shapes automatically from " +
                        "the painted tiles, making platformer level creation very efficient."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Practice") {
                    BodyText(
                        "• Build a 2D level\n\n" +
                        "Create a 2D project. Import or find free sprite sheets online. Set up two sorting " +
                        "layers: Background and Foreground. Paint a ground platform and some walls using " +
                        "the Tilemap system. Place a Sprite character on the Foreground layer above the " +
                        "tilemap. Press Play and verify the draw order is correct."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
