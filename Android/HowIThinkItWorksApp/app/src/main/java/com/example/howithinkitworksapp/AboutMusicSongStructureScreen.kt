package com.example.howithinkitworksapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

private val SongStructureBg = Color(0xFF2D2D2D)
private val SongStructureHeading = Color(0xFFDDDDDD)

private val songStructureSections = listOf(
    "Starting Simple — The Hook That Teaches" to
        "Most songs begin stripped down: a single riff, a lone beat, a bass line. Not because what follows is complicated — but because the ear needs to be taught before it can appreciate. When a song opens with its most distinctive element alone, the listener locks onto it. It becomes familiar in two or three bars. Then the next layer arrives — the snare, or the harmony, or the second guitar — and it lands against a background the listener already knows. This is not accident; it is instruction. By the time the full arrangement arrives, every element has been individually introduced and the listener can hear all of them because they have been learning the song from the beginning. This gradual layering is the difference between a wall of sound that overwhelms and a wall of sound that satisfies: you can only hear complexity you have been taught to hear.",

    "Building Toward Release — Immersion Through Momentum" to
        "The other function of gradual building is emotional. A song that starts at full intensity has nowhere to go. A song that begins spare and adds — more drums, more voices, a key change, a doubled chorus — creates momentum, and momentum creates anticipation. The listener feels the structure pulling forward. When the chorus finally hits with everything, the release is proportional to the tension that was accumulated. This is immersion by construction: the listener has been inside a building process, not just a static sound. It mirrors the same tension-release mechanism that operates in harmony and rhythm, but at the scale of the whole song. The drop in electronic music is the most explicit version of this — the build is the entire point, and the release is understood to be coming, which is exactly why it hits when it does."
)

@Composable
fun AboutMusicSongStructureScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SongStructureBg)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Song Structure",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        songStructureSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = SongStructureHeading,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = body,
                fontSize = 16.sp,
                color = Color.White,
                lineHeight = 24.sp,
                modifier = Modifier.padding(bottom = 28.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { navController.popBackStack() },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(text = "Back", color = Color.White, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}
