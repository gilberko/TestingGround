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

private val RhythmBg = Color(0xFF2D2D2D)
private val RhythmHeading = Color(0xFFDDDDDD)

private val rhythmSections = listOf(
    "The Body Knows Before The Brain Does" to
        "Rhythm is uniquely physical. Before we consciously decide to tap our foot, the motor system has already responded. The basal ganglia and cerebellum — structures deeply involved in timing and movement — activate automatically in response to a regular beat. This is neural entrainment: when exposed to a consistent rhythmic stimulus, the brain literally synchronizes its oscillatory activity to match it (Nozaradan et al., 2011, Journal of Neuroscience). This is why rhythm feels so immediate and embodied. It is not first processed conceptually and then felt — it is felt first, through the body. A consistent tempo is an invitation to the nervous system to align with something external.",

    "Why Predictability Feels Good" to
        "A fixed tempo is a promise: something will happen exactly when you expect it. The brain is a prediction machine, and it is deeply motivated to predict correctly. Wolfram Schultz's landmark research on dopamine (1997, Science) showed that dopamine neurons fire not just when a reward arrives, but when the brain correctly predicts that a reward is coming. Successful prediction is itself rewarding — a small neurological \"win.\" A reliable rhythmic pulse offers the brain dozens of these micro-wins per minute. It is engaging precisely because it satisfies the prediction system over and over. This is the first thing rhythm gives us: the pleasure of being right.",

    "The Anchor" to
        "Before rhythm can be interesting, it must be established. The first bars of a piece create an anchor — a reference point for all subsequent rhythmic events. The tempo becomes internalized, and the brain builds a rhythmic model: it knows when the next beat is expected, where the downbeat falls, what the feel of the pattern is. Everything that follows is interpreted in relation to this anchor. This is why the introduction matters in music — it is not decoration, it is calibration. The listener is being taught what to expect. The game cannot begin until the rules are established.",

    "Breaking The Pattern — Tension" to
        "Once an anchor is established, the composer can violate it. Syncopation places emphasis on unexpected beats. A sudden silence where a beat was expected creates a jolt. The brain predicted one thing and received another — a prediction error. Rather than being unpleasant, this violation is where musical interest lives. The amygdala responds to unexpected events, including rhythmic ones, with a mild alerting response. The tension is not distressing but engaging — it is the feeling of a question being asked. The brain's task is now to figure out what just happened and to recalibrate its model.",

    "Resolution — Coming Home" to
        "The resolution of rhythmic tension is the return to the anchor. When the familiar pattern reasserts itself after a disruption, the brain experiences something like vindication: the prediction system was right all along — the original pattern was the structure beneath the variation. This resolution triggers a different kind of reward: not just the satisfaction of predicting correctly, but the satisfaction of having held a model through uncertainty and been proved right. This is why so much music works by a cycle of tension and release: establish, disrupt, resolve. Each cycle is a small emotional journey. The listener is not a passive receiver — they are an active predictor, and the music keeps giving them feedback.",

    "Subdivisions and Intensity" to
        "Rhythm can also be intensified without disruption. Moving from quarter notes to eighth notes to sixteenth notes at the same tempo multiplies the number of rhythmic events per bar — more inputs for the brain to track, more micro-predictions per second. This creates a sense of increased energy and forward movement. When this subdivision accelerates toward a structural point — a chorus, a climax, a final beat — the brain is experiencing progressive loading: more and more cognitive engagement until a release. It is a rhythmic equivalent of tension building. The brain is implicitly being told: we are heading somewhere — and the density of information is the signal."
)

@Composable
fun AboutMusicRhythmScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(RhythmBg)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Rhythm",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        rhythmSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = RhythmHeading,
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
