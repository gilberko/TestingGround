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
        "One of the most interesting possibilities in song is deliberate contrast between what the lyrics say and what the music feels. \"Every Breath You Take\" by The Police has the warm harmonic language of a love ballad but lyrics describing obsessive surveillance — the contrast creates unease that a purely sinister arrangement would not. Johnny Cash's recording of \"Hurt\" takes a song written in the voice of a young addict and places it in the voice of an old man facing death — the recontextualization makes the lyrics mean something completely different. When the emotional signal of the music diverges from the semantic content of the lyrics, the listener must hold both simultaneously, and the result is a complexity and depth that neither could achieve alone. Irony, grief, ambivalence — these require the gap.",

    "Priming Arousal Through Imagery" to
        "Katy Perry's \"I Kissed A Girl\" saturates the language with sensory, arousal-adjacent cues — kissing, perfume, the taste of cherry chapstick, touching someone who shouldn't be touched. None of these is an explicit statement; they are images that prime the listener's arousal state before any direct proposition is made. Priming (Bargh et al., 1992) works by activating associated mental concepts and bodily states, and lyrical imagery does this efficiently: each sensory detail pulls the listener's attention into an aroused, heightened register. By the time the chorus arrives, the emotional landscape has already been shaped by the accumulation of images. The words do not say what they are doing — they just do it.",

    "Lyrics as Rapport — Being One of Them" to
        "Sam Cooke's \"Wonderful World\" (1960) opens with the admission that he doesn't know algebra, biology, French, geography, history, science, or trigonometry. The effect is immediate rapport. Cooke is not positioning himself above the listener with knowledge or authority — he is placing himself on the same side of the desk, sharing the same gaps, the same irreverence toward subjects he was supposed to have mastered. Anyone who has ever sat in a classroom recognizes this. That recognition is the mechanism: a performer who mirrors the listener's experience creates a feeling of being understood, of not being alone in it. This is mass rapport — the song speaks to many listeners simultaneously, but each one feels it personally. What follows — the declaration that he does know love — lands with the authority that the shared confession has earned.",

    "Lyrics That Prime Movement — Dance Suggestion" to
        "Some lyrics embed movement in their language. Kylie Minogue's \"Spinning Around\" creates a physical image of rotation and lightness; Taylor Swift's \"Shake It Off\" works on two levels simultaneously — it means ignore it, don't let criticism stick, don't worry about it, but it is also a dance instruction: shake your body, move. The double meaning is not accidental. The motor imagery system (Jeannerod, 2001) shows that mentally simulating a movement activates similar neural circuits to physically performing it. A lyric that describes dancing is, in a mild sense, cueing the body to move. In a dance pop context, this is part of the song's design: it is inviting the listener onto the floor through language before the beat alone could do it. The suggestion is not coercive — it is just a nudge in the direction the music is already pulling.",

    "Call and Response — Making the Crowd Part of the Song" to
        "Cab Calloway's \"Minnie the Moocher\" (1931) is built around a simple mechanism: Calloway sings a phrase — \"Hi-de-hi-de-hi-de-ho\" — and the audience repeats it back. Then he extends it: longer, more complex, harder to anticipate. The crowd follows. This is call and response, one of the oldest structures in music, and what it does to an audience is specific: participation dissolves the boundary between performer and crowd. The audience is no longer watching a performance — they are inside it, contributing to it, responsible for part of it. Psychologically, what you help create, you own; what you own, you feel. Calloway understood that the audience singing back was not a novelty trick — it was immersion by design. The song becomes a collective event, and the crowd's experience of it is categorically different from passive listening. This is activation, not entertainment."
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
