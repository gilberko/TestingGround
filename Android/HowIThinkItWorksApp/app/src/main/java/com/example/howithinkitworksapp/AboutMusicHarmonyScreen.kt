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

private val HarmonyBg = Color(0xFF2D2D2D)
private val HarmonyHeading = Color(0xFFDDDDDD)

private val harmonySections = listOf(
    "The Anchor — Root and Tonic" to
        "A chord is a collection of notes played simultaneously. The root note is its foundation — the pitch the other notes are stacked on top of and measured against. In a key, the tonic chord (built on the first note of the scale) functions as home: the place of rest, resolution, and stability. All other chords in a piece are heard in relation to this anchor. Moving away from the tonic creates a sense of departure; returning to it creates a sense of arrival. The listener internalizes the tonic very quickly — often within the first few bars — and from that point, every harmony is perceived not in isolation but as a distance from, or a tension against, that established home. Harmony is fundamentally a system of relationships.",

    "Major vs Minor — The Emotional Palette" to
        "The difference between a major and minor chord is a single note: the third. In a major chord, the third is raised (a major third above the root); in a minor chord, it is lowered by a semitone. This small difference has a large emotional consequence. Research by Kastner and Crowder (1990) found that even young children reliably associate major chords with happiness and minor chords with sadness, suggesting the association has developmental roots. Cross-cultural studies (Fritz et al., 2009) found some universality: isolated tribespeople in Cameroon with no exposure to Western music also rated major as \"happy\" and minor as \"sad.\" Part of this may be acoustic — major intervals share more overtones and sound more \"fused\"; minor intervals create more acoustic roughness. Part may be cultural reinforcement over millennia of music. Likely both.",

    "Tension and Resolution — The Dominant Pull" to
        "The dominant seventh chord (built on the fifth degree of the scale, with an added minor seventh) is harmony's engine of tension. It contains a tritone — the interval of an augmented fourth or diminished fifth — which is acoustically unstable: its two notes want to move in opposite directions to reach a consonant landing point. In medieval music theory this interval was called \"diabolus in musica\" (the devil in music) for its unsettled quality. When the dominant seventh resolves to the tonic, the tension releases and the listener experiences relief. Koelsch et al. (2006, Nature Neuroscience) showed that the brain responds to unexpected or dissonant harmonies with activity in the amygdala — the same structure involved in threat detection and emotional salience. Harmony activates the emotional brain, not just the auditory one.",

    "Suspended Chords — The Wait" to
        "A suspended chord (sus2 or sus4) replaces the third with a second or a fourth. Without the third, there is no major or minor — no happy or sad. The chord floats in emotional ambiguity. Sus chords feel unresolved, expectant, held in suspense — which is exactly what the name means. They are frequently used at the end of a phrase or at a moment of climax precisely because they intensify the desire for resolution. When the third finally arrives — whether in a major or minor landing — the relief is amplified by having been withheld. The emotional color of the resolution is richer for the suspension that preceded it. The brain's prediction system has been kept waiting, and the satisfaction of arrival is proportionally greater.",

    "How Frequencies Affect the Nervous System" to
        "Below the level of musical context, frequencies interact directly with the nervous system. Consonant intervals (octave 1:2, fifth 2:3, fourth 3:4) have simple integer ratios and produce low acoustic roughness — the sensory quality of beating or wavering between frequencies. Dissonant intervals have more complex ratios, produce higher roughness, and are perceived as tense or uncomfortable. Physiologically, listening to music with sustained dissonance has been associated with elevated cortisol (Thoma et al., 2013) — the brain's stress hormone — while consonant resolutions correlate with decreases in cortisol and heart rate. This is not purely symbolic: the body responds to harmonic tension as a mild stressor, and resolution as relief. Music can modulate the autonomic nervous system directly through the frequencies it delivers.",

    "The Harmonic Journey" to
        "A chord progression is an emotional narrative. I-IV-V-I (tonic-subdominant-dominant-tonic) is so stable and satisfying that it underlies thousands of songs across genres — the tension of IV and V, the resolution back to I, is almost universally pleasing. The ii-V-I progression of jazz adds chromatic richness and a stronger pull toward resolution. Borrowing chords from the parallel minor key (e.g., a minor iv in a major key) adds unexpected darkness — a shadow in a bright room. Modulation — shifting the entire tonal center to a new key — is the harmonic equivalent of a scene change in a film: the same emotional laws apply, but from a new reference point, which can feel like renewed energy or a completely different emotional register. The harmonic language of a piece is how it navigates between stability and tension, familiarity and surprise."
)

@Composable
fun AboutMusicHarmonyScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(HarmonyBg)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Harmony",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        harmonySections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = HarmonyHeading,
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
