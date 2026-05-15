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

private val LyricsBg = Color(0xFF2D2D2D)
private val LyricsHeading = Color(0xFFDDDDDD)

private val lyricsSections = listOf(
    "Lyrics as Story" to
        "When lyrics tell a story, the music becomes a narrator's voice — not the story itself, but the emotional color that makes the story felt rather than merely understood. Narrative transportation theory (Green & Brock, 2000) shows that when a person is absorbed in a narrative, their capacity for critical evaluation decreases and emotional impact increases. Song lyrics achieve this in compressed form: a three-minute song can carry the emotional weight of a much longer story by selecting a single moment, a single image, or a single emotional truth. \"The Night They Drove Old Dixie Down,\" \"Hotel California,\" \"The River\" — the music does not just accompany the story; it is the atmosphere the story lives in. The listener is not reading; they are inhabiting.",

    "Lyrics as Image — Building a World" to
        "Sometimes lyrics do not tell a story so much as construct a myth or an identity. \"Play That Funky Music\" is played on an instrument that is funky music — and the lyrics insist on this identity, celebrate it, build a world around it. The music is its own proof. Similarly, Jimi Hendrix's \"Purple Haze\" does not describe a specific event; it creates a texture, an altered-state atmosphere, that the music then inhabits and confirms. This is lyrics functioning as world-building: they tell you what kind of experience you are inside before you fully know it. Bruce Springsteen's \"Born to Run\" builds the myth of escape and possibility and then the music enacts it — the urgency, the release. The lyrics prime the emotional register; the music fulfills it.",

    "Lyrics as Emotion Label" to
        "Sometimes there is no story and no world — only the name of a feeling. \"I can't live if living is without you.\" \"I will always love you.\" \"Every time you go away, you take a piece of me with you.\" These lyrics work because the music has already created an emotional state, and the lyrics give it a word. Matthew Lieberman et al. (2007, Psychological Science) showed that affect labeling — putting a name to an emotion — activates the prefrontal cortex and reduces amygdala reactivity, suggesting that naming a feeling helps regulate it. In music, this mechanism is reversed: the music destabilizes, heightens, or opens up the emotional state; the lyrics arrive and label it, making it conscious and real. The listener thinks: yes, that is exactly what this is.",

    "The Rhythm of Words" to
        "Lyrics are not just semantic content — they are percussive material. Syllables land on beats; stressed syllables align with musical accents; phrasing follows the contour of the melody. In rap and hip-hop, the lyrical line is explicitly a percussion instrument: internal rhymes, polyrhythmic stress, syllable density that creates rhythmic complexity independent of the beat. When lyrics flow naturally with the rhythm, there is a double satisfaction: both the melodic and the linguistic phrase resolve together. When lyrics fight the beat — deliberately or clumsily — there is a small friction that can be either expressive (tension as effect) or distracting (a misalignment the ear keeps snagging on). The best lyrical writing is inseparable from its rhythmic setting.",

    "Repetition and the Earworm" to
        "The chorus is repeated because repetition does something specific to the brain. Each encounter with the same phrase activates a slightly stronger memory trace (the spacing effect; Ebbinghaus, 1885). By the third or fourth chorus, the phrase is not just heard — it is known, anticipated, and the listener may begin to complete it internally before it arrives. Williamson et al. (2011) studied involuntary musical imagery — earworms — and found they are almost always associated with highly repetitive phrases with distinctive rhythmic or melodic hooks. The brain latches onto predictable, repeated patterns and re-runs them as a form of continued processing. A chorus that you cannot get out of your head is a piece of very effective neural encoding. The earworm is not an accident — it is the mechanism working as intended.",

    "The Gap Between Lyrics and Music" to
        "One of the most interesting possibilities in song is deliberate contrast between what the lyrics say and what the music feels. \"Every Breath You Take\" by The Police has the warm harmonic language of a love ballad but lyrics describing obsessive surveillance — the contrast creates unease that a purely sinister arrangement would not. Johnny Cash's recording of \"Hurt\" takes a song written in the voice of a young addict and places it in the voice of an old man facing death — the recontextualization makes the lyrics mean something completely different. When the emotional signal of the music diverges from the semantic content of the lyrics, the listener must hold both simultaneously, and the result is a complexity and depth that neither could achieve alone. Irony, grief, ambivalence — these require the gap."
)

@Composable
fun AboutMusicLyricsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LyricsBg)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Lyrics",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        lyricsSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = LyricsHeading,
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
