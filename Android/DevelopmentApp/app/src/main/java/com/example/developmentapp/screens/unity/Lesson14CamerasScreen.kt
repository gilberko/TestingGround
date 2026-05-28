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
fun Lesson14CamerasScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Unity — Lesson 14 — Cameras",
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
                        "• Cinemachine\n\n" +
                        "Cinemachine is Unity's procedural camera system (install via Package Manager). " +
                        "Instead of manually scripting camera behavior, you configure Virtual Cameras " +
                        "that Cinemachine's Brain (attached to the main Camera) blends between. A " +
                        "FreeLook Virtual Camera gives you a third-person orbit rig with three rigs " +
                        "(top, middle, bottom) and smooth damping built-in. Assign the Follow and Look " +
                        "At targets to the player transform."
                    )
                    BodyText(
                        "• Camera transitions\n\n" +
                        "Cinemachine blends between Virtual Cameras based on priority — the highest " +
                        "priority active camera wins. To trigger a cinematic cutscene camera, enable a " +
                        "high-priority Virtual Camera from script, then re-enable the gameplay camera " +
                        "when done. Set the blend style (Cut, EaseIn, EaseOut, Linear) in the Cinemachine " +
                        "Brain component."
                    )
                    CodeBlock(
                        "using Cinemachine;\n\n" +
                        "public CinemachineVirtualCamera cutsceneCam;\n" +
                        "public CinemachineVirtualCamera gameplayCam;\n\n" +
                        "void StartCutscene() {\n" +
                        "    cutsceneCam.Priority = 20;   // higher than gameplay (10)\n" +
                        "}\n\n" +
                        "void EndCutscene() {\n" +
                        "    cutsceneCam.Priority = 0;\n" +
                        "}"
                    )
                    BodyText(
                        "• Camera shake\n\n" +
                        "Cinemachine implements camera shake via Noise. On a Virtual Camera, add a " +
                        "CinemachineBasicMultiChannelPerlin component and assign a Noise Profile (Unity " +
                        "ships with \"6D Shake\" and \"Handheld\"). Set Amplitude Gain and Frequency Gain " +
                        "to 0 normally, then spike them briefly on impact events (explosions, landings) " +
                        "using a coroutine that lerps back to zero."
                    )
                    CodeBlock(
                        "IEnumerator Shake(float intensity, float duration) {\n" +
                        "    noise.m_AmplitudeGain = intensity;\n" +
                        "    yield return new WaitForSeconds(duration);\n" +
                        "    noise.m_AmplitudeGain = 0;\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Practice") {
                    BodyText(
                        "• Third-person camera rig\n\n" +
                        "Install Cinemachine. Create a FreeLook Virtual Camera, assign your character as " +
                        "Follow and Look At targets. Adjust the three orbit rigs (height and radius) in " +
                        "the Inspector. Add a CinemachineBasicMultiChannelPerlin noise component. Trigger " +
                        "a brief shake when the player lands (detect via velocity change in OnCollisionEnter)."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
