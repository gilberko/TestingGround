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
        "The other function of gradual building is emotional. A song that starts at full intensity has nowhere to go. A song that begins spare and adds — more drums, more voices, a key change, a doubled chorus — creates momentum, and momentum creates anticipation. The listener feels the structure pulling forward. When the chorus finally hits with everything, the release is proportional to the tension that was accumulated. This is immersion by construction: the listener has been inside a building process, not just a static sound. It mirrors the same tension-release mechanism that operates in harmony and rhythm, but at the scale of the whole song. The drop in electronic music is the most explicit version of this — the build is the entire point, and the release is understood to be coming, which is exactly why it hits when it does.",

    "The Beginning Should Capture Your Attention" to
        "In every context where music is selected competitively — radio programming, demo tape review, streaming playlist browsing — the opening of a song carries disproportionate weight. A radio programme director deciding whether to add a track to rotation may listen to the first few seconds and stop there. A&R executives reviewing hundreds of demos have described something close to an eight-second rule: if the opening does not signal something worth hearing, the recording is set aside. Streaming data supports this: analyses from platforms like Spotify have shown that skip rates are highest in the first thirty seconds, and songs that fail to establish something memorable in that window suffer disproportionately in algorithmic recommendations.\n\nThe consequence is that songwriters and producers — particularly in pop and rock, where the audience is already listening to a great deal of music — have strong incentives to front-load attention. Sometimes this means opening with the hook directly: no intro, no preamble, just the most memorable part of the song first. Sometimes it means engineering an opening that is simply unusual enough to prevent the skip.\n\nThe interesting cases are the ones where the beginning of a song is not representative of what follows — it is designed purely to pull the listener in, and the rest of the song is a different beast. 'Stairway to Heaven' by Led Zeppelin opens as a delicate, fingerpicked acoustic piece with a recorder melody, nothing like the dense, heavy rock that arrives in its second half. The opening is not a preview of the song; it is a lure. It establishes an atmosphere of something unfolding slowly, with care, and once the listener is committed, the transition into heavier material is earned. 'Smells Like Teen Spirit' by Nirvana does something structurally similar in miniature: the quiet, tentative guitar intro builds for four bars before the full band crashes in, creating a contrast that was jarring in 1991 and has since become a template.\n\n'Money' by Pink Floyd opens with the sound of a cash register and coins clinking before any instrument plays. It is one of the most immediately recognisable song openings in rock precisely because it has nothing to do with conventional musical expectations — it is a sonic image that forces a response. 'Welcome to the Jungle' by Guns N' Roses opens with a sliding guitar figure that functions as an alert signal. 'Baba O'Riley' by The Who opens with a synthesiser arpeggio that, in 1971, was completely alien to the listener's expectations for rock music. In every case, the opening is a commitment device: it demands a response before the listener has decided whether they want to engage.\n\nDaniel Levitin (This Is Your Brain on Music, 2006) describes how the first few seconds of a familiar piece of music can trigger an immediate anticipatory response, with the brain beginning to predict what follows based on a pattern it recognises. For a song being heard for the first time, this mechanism is reversed: the opening sets up predictions that the rest of the song will fulfil or subvert. A strong opening creates strong predictions, and strong predictions create engagement. The brain, having begun to model what the song will do, is invested in finding out whether it was right."
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
