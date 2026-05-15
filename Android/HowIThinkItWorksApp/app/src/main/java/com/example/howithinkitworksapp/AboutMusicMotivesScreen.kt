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

private val MotivesBg = Color(0xFF2D2D2D)
private val MotivesHeading = Color(0xFFDDDDDD)

private val motivesSections = listOf(
    "What Is A Motive" to
        "A motive (or motif) is a short musical cell — usually two to five notes, a distinctive rhythm, or a particular interval — that functions as a recognizable signature. It is small enough to appear frequently and be remembered, but distinctive enough to be identified across transformations. The four-note opening of Beethoven's Fifth Symphony, the two-note descending semitone of the Jaws theme, John Williams's Imperial March — these are motives. They are not themes in the full sense, but building blocks that can be manipulated, developed, and transformed. A motive is something the listener can learn.",

    "Teaching The Brain A Pattern" to
        "The first time a motive appears, the brain registers it as a new input. The second time, it begins to predict: something like this might come again. By the third or fourth appearance, the brain has built a model — it anticipates the motive before it fully arrives, and when it does, there is a small reward signal. This is the same prediction-reward mechanism described by Schultz (1997): correct prediction releases dopamine. The motive trains the listener's brain to expect it, and then keeps rewarding that expectation. The musical relationship between composer and listener is, in this sense, a collaboration: the composer teaches the pattern; the listener learns it and takes pleasure in recognizing it.",

    "The Dressing-Up Game" to
        "The real power of motives emerges when they are transformed. The same motive can be played in a different key (transposition), upside down (inversion), backwards (retrograde), faster or slower (diminution/augmentation), or with different instrumentation. It is the same recognizable shape in a different costume. David Huron, in his influential work \"Sweet Anticipation\" (2006), describes the pleasure of musical recognition as a version of the \"aha\" moment — the brain identifies the underlying pattern despite the surface transformation. It is a puzzle the brain is equipped to solve, and solving it is rewarding. The game is: here is someone you know. Now find them in disguise.",

    "Development and Variation" to
        "The classical tradition built entire movements around motivic development. Beethoven and Brahms would take a short cell and subject it to continuous transformation — harmonically, rhythmically, contrapuntally — across the entire span of a movement. The listener's engagement comes from tracking the motive through its changes: where has it gone, what has it become, is this still the same idea? In jazz, the standard is played and then improvised over — the original melody is the motive, and the improvisation is its transformation. In pop music, the hook usually returns in its original form across the verse/chorus structure, giving the listener the satisfaction of the return.",

    "Dopamine, Chills, and Pattern Completion" to
        "Anne Blood and Robert Zatorre (2001, PNAS) demonstrated using PET imaging that musical chills — the shivers associated with emotionally intense musical moments — correlated with activation in the nucleus accumbens, ventral tegmental area, and other dopaminergic reward structures: the same regions involved in food, sex, and other primary rewards. The moments that most reliably trigger chills are often structural: the return of a theme after absence, a harmonic resolution after extended tension, a motive arriving unexpectedly in a new register. Pattern completion — the brain's recognition that something it was waiting for has arrived — appears to be a deep source of musical pleasure, not a superficial one.",

    "In Modern Music" to
        "Motivic thinking is not confined to concert halls. The guitar riff in rock music is a motive — repeated, altered, brought back after the solo. In hip-hop, the sample or loop is often motivic: a short fragment that repeats, and over which new material is layered, creating a relationship between fixed anchor and evolving foreground. Film scores (Williams, Morricone, Zimmer) use leitmotifs to link music to characters, objects, or emotional states — so that when the motive returns, it carries accumulated emotional weight from previous appearances. Advertising uses audio logos (Netflix's \"ta-dum,\" Intel's chime) as single-exposure motives designed to trigger brand recognition instantly. The underlying mechanism is the same: a short distinctive pattern that the brain learns to recognize and predict."
)

@Composable
fun AboutMusicMotivesScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MotivesBg)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Motives",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        motivesSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MotivesHeading,
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
