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
fun Lesson10AudioScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Unity — Lesson 10 — Audio",
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
                        "• Audio sources\n\n" +
                        "An AudioSource component plays audio on a GameObject. Import an audio file (MP3, " +
                        "OGG, WAV) into the Project window and assign it to the AudioClip field of an " +
                        "AudioSource. Check Play On Awake to play when the scene loads, or call " +
                        "audioSource.Play() from a script. For 3D spatial audio, enable 3D Sound Settings " +
                        "so the volume fades with distance."
                    )
                    CodeBlock(
                        "AudioSource audioSource;\n" +
                        "public AudioClip jumpSound;\n\n" +
                        "void Start() { audioSource = GetComponent<AudioSource>(); }\n\n" +
                        "void Jump() {\n" +
                        "    audioSource.PlayOneShot(jumpSound);  // plays without interrupting\n" +
                        "}"
                    )
                    BodyText(
                        "• Sound effects\n\n" +
                        "Sound effects are short audio clips triggered by game events — jumping, landing, " +
                        "collecting items, getting hit. Use PlayOneShot() for effects that should overlap " +
                        "(multiple gunshots) and Play() when the effect should restart or be exclusive. " +
                        "Keep sound effect clips compressed (OGG/Vorbis) and short to minimize memory use."
                    )
                    BodyText(
                        "• Music\n\n" +
                        "Background music is typically a long compressed audio clip set to Loop on an " +
                        "AudioSource that persists across scenes. Call DontDestroyOnLoad(gameObject) on " +
                        "the music manager to prevent it from being destroyed when loading a new scene. " +
                        "Use a singleton pattern so only one music manager exists at a time."
                    )
                    BodyText(
                        "• Mixer basics\n\n" +
                        "The Audio Mixer (Window → Audio → Audio Mixer) is a signal routing graph with " +
                        "groups, effects, and volume controls. Create groups for Music, SFX, and Voice. " +
                        "Assign AudioSources to groups by setting their Output field. Use exposed " +
                        "parameters to let UI sliders control group volume at runtime."
                    )
                    CodeBlock(
                        "using UnityEngine.Audio;\n\n" +
                        "public AudioMixer mixer;\n\n" +
                        "public void SetMusicVolume(float volume) {\n" +
                        "    // Mixer volumes are in decibels — convert from linear 0-1\n" +
                        "    mixer.SetFloat(\"MusicVolume\", Mathf.Log10(volume) * 20);\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Practice") {
                    BodyText(
                        "• Add music and effects\n\n" +
                        "Import a background music track and at least two sound effects (jump, collect). " +
                        "Set up an Audio Mixer with Music and SFX groups. Attach music to a persistent " +
                        "manager object. Trigger sound effects from the player controller script. Add " +
                        "volume sliders to the settings menu connected to the mixer's exposed parameters."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
