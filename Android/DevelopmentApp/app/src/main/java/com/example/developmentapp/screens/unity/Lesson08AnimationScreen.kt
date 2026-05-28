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
fun Lesson08AnimationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Unity — Lesson 8 — Animation",
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
                        "• Animator\n\n" +
                        "The Animator component on a GameObject links it to an Animator Controller asset. " +
                        "The controller is a state machine — it defines which animation clips exist, how " +
                        "they transition between each other, and under what conditions. Open the Animator " +
                        "window (Window → Animation → Animator) to view and edit the state machine visually."
                    )
                    BodyText(
                        "• Animation clips\n\n" +
                        "An Animation Clip (.anim file) records how one or more properties of a GameObject " +
                        "change over time — sprite frames, position, scale, color, etc. Create clips by " +
                        "selecting the object, opening the Animation window (Window → Animation → Animation), " +
                        "and pressing Record. For 2D characters, swap the SpriteRenderer's sprite property " +
                        "on each keyframe to create sprite sheet animation."
                    )
                    BodyText(
                        "• Blend trees\n\n" +
                        "A Blend Tree blends multiple animation clips together based on a float parameter. " +
                        "Use a 1D Blend Tree for locomotion: set the parameter to the player's speed and " +
                        "blend smoothly from idle (speed 0) through walk (speed 0.5) to run (speed 1). " +
                        "This avoids jarring cut transitions and feels natural."
                    )
                    BodyText(
                        "• State transitions\n\n" +
                        "Transitions in the Animator Controller define the conditions for moving from one " +
                        "animation state to another. Right-click a state and choose Make Transition, then " +
                        "click the destination state. In the Inspector set conditions (e.g., parameter " +
                        "\"isJumping\" = true) and adjust the transition duration to control blending time."
                    )
                    CodeBlock(
                        "// Drive animator parameters from script\n" +
                        "Animator anim;\n" +
                        "void Start() { anim = GetComponent<Animator>(); }\n\n" +
                        "void Update() {\n" +
                        "    float speed = Mathf.Abs(rb.linearVelocity.x);\n" +
                        "    anim.SetFloat(\"Speed\", speed);\n" +
                        "    anim.SetBool(\"IsGrounded\", IsGrounded());\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Practice") {
                    BodyText(
                        "• Idle/run/jump animations\n\n" +
                        "Import a character sprite sheet. Slice it in the Sprite Editor (2D→Sprite Editor). " +
                        "Create three Animation Clips: Idle (looping), Run (looping), Jump (play once). " +
                        "Set up the Animator Controller with transitions driven by Speed (float) and " +
                        "IsGrounded (bool) parameters. Connect to the character controller script."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
