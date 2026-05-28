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
fun Lesson06Physics2DScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Unity — Lesson 6 — 2D Physics",
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
                        "• Rigidbody2D\n\n" +
                        "Adding a Rigidbody2D component to a GameObject places it under control of the " +
                        "Unity 2D physics engine. The engine will then apply gravity, handle collisions, " +
                        "and respond to forces. Set Body Type to Dynamic for moving physics objects, " +
                        "Kinematic for objects you move via script without physics forces (like a moving " +
                        "platform), or Static for immovable geometry."
                    )
                    CodeBlock(
                        "// Get reference in Start\n" +
                        "Rigidbody2D rb;\n" +
                        "void Start() { rb = GetComponent<Rigidbody2D>(); }\n\n" +
                        "// Apply a jump force\n" +
                        "void Jump() { rb.AddForce(Vector2.up * jumpForce, ForceMode2D.Impulse); }"
                    )
                    BodyText(
                        "• Collider2D\n\n" +
                        "Colliders define the physical shape of an object for collision detection. For 2D " +
                        "objects: BoxCollider2D (rectangles), CircleCollider2D (circles), " +
                        "PolygonCollider2D (custom shapes), CapsuleCollider2D (for characters). A " +
                        "Rigidbody2D needs a Collider2D to actually collide with anything. Use the Edit " +
                        "Collider button in the Inspector to resize the collision shape to fit your sprite."
                    )
                    BodyText(
                        "• Triggers\n\n" +
                        "Check the Is Trigger checkbox on a Collider2D to make it a trigger volume — " +
                        "objects pass through it but Unity fires events when they overlap. Use " +
                        "OnTriggerEnter2D to detect when something enters a trigger zone (like a coin " +
                        "pickup or a hazard area). Triggers have no physics response — they only notify."
                    )
                    CodeBlock(
                        "void OnTriggerEnter2D(Collider2D other) {\n" +
                        "    if (other.CompareTag(\"Coin\")) {\n" +
                        "        score++;\n" +
                        "        Destroy(other.gameObject);\n" +
                        "    }\n" +
                        "}"
                    )
                    BodyText(
                        "• Gravity\n\n" +
                        "The Rigidbody2D Gravity Scale field controls how much of the global gravity " +
                        "(set in Edit → Project Settings → Physics 2D) applies to this object. Default " +
                        "scale is 1. Set it to 0 for top-down games where you don't want objects to fall, " +
                        "or increase it to make objects fall faster. Gravity pulls in the -Y direction " +
                        "by default."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Practice") {
                    BodyText(
                        "• Jumping platformer\n\n" +
                        "Set up a 2D scene with a tilemap floor. Add a sprite character with Rigidbody2D " +
                        "(Dynamic) and a CapsuleCollider2D. Write a script that checks if the player is " +
                        "grounded (using Physics2D.OverlapCircle at the feet) and applies an upward " +
                        "AddForce when Space is pressed."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Mini Project") {
                    BodyText(
                        "• Physics playground\n\n" +
                        "Create a scene with ramps, bouncy objects (set Bounciness in a Physics Material 2D), " +
                        "and trigger zones that destroy objects when they enter. Drop different shapes from " +
                        "above and observe how they interact. Add a score counter that increments when objects " +
                        "hit a trigger at the bottom."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
