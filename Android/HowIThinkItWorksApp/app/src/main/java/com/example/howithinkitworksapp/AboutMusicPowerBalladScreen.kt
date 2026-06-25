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

private val PowerBalladBg = Color(0xFF2D2D2D)
private val PowerBalladHeading = Color(0xFFDDDDDD)

private val powerBalladSections = listOf(
    "The Opening Riff" to
        "Many power ballads begin the same way: a single guitar, playing slowly, in a minor key. The tempo is deliberate. The tone is searching. There is no percussion, no bass, no other voice — just the riff, and what it evokes.\n\nAt this moment, the emotional signal is unambiguous: sadness, longing, something lost. The minor key has been consistently associated with these emotions across cultures; research by Kastner and Crowder (1990) found that even young children reliably associate minor chords with sadness. The song meets the listener in a place of vulnerability. It makes no attempt to lift or reassure. It simply sits in the feeling.\n\nThe listener accepts the invitation. They are in.",

    "What Repetition Does" to
        "The riff repeats. Then repeats again. Here something begins to shift, though it is subtle at first.\n\nRobert Zajonc (1968) documented what is now called the Mere Exposure Effect: repeated exposure to a stimulus changes how we feel about it. Familiar stimuli are processed more fluently — the brain recognizes them faster, with less effort — and that ease of processing generates a subtle positive feeling alongside whatever the stimulus originally evoked.\n\nA riff heard once is simply sad. A riff heard four or five times in a row becomes familiarly sad — and familiarity, even with a sad thing, is a different sensation. Recognition arrives with it. A sense of home within that sadness. The feeling that started as something that caught you off guard has now become something you know. The emotional texture changes even though the notes do not.",

    "The Build" to
        "Then the drums enter.\n\nThen the bass. Then more guitars, more instruments, more voices. The volume rises. The sonic density increases.\n\nJaak Panksepp (1995) and subsequent researchers identified crescendos and the sudden entrance of new instruments as among the strongest documented triggers of musical frisson — the physical response of chills, goosebumps, and a sense of emotional significance. The body interprets rising sonic density as something building toward importance. The nervous system responds to increasing volume and texture with heightened arousal: not anxiety, but attention, a sense that something is happening.\n\nThis is not metaphorical. The autonomic nervous system genuinely responds to sonic build-up. Heart rate and respiration change. Goosebumps appear. The body is being moved before the mind has decided how to feel about it.",

    "From Sad to Anthem" to
        "The minor key has not changed. The chord structure may be identical to the opening riff. But the experience is completely different.\n\nWhat was intimate and personal now sounds collective and enormous. A feeling that was private at quiet volume feels shared at high volume. The sadness that belonged to one person now sounds like it belongs to everyone who has ever felt it.\n\nResearch by Taruffi and Koelsch (2014) found that sad music paradoxically produces pleasure, comfort, and nostalgia in most listeners rather than actual sadness. The emotional journey of the power ballad is this paradox in motion: the sadness does not disappear — it gets amplified until it transforms. It becomes catharsis. It becomes a declaration. It becomes the feeling of being held in an emotion so large that it outgrows the loss that produced it.\n\nThe song has turned a private wound into a shared anthem.",

    "Still Loving You — The Scorpions (1984)" to
        "This song is a near-perfect example of the arc.\n\nIt opens with the guitar riff — slow, deliberate, unmistakably minor-key and melancholic. The verse is quiet, intimate, almost spoken. The melody stays close to the ground. The listener is pulled in gently.\n\nAs the song progresses, more enters. The chorus swells. By the final third — the extended guitar solo climbing through its phrases, the full band behind it, the massed sound building toward the peak — the same minor-key material that started in sadness has become something enormous. Listeners commonly report chills during the solo and the final choruses. The sadness has not been replaced. It has been held up and made immense. In becoming immense, it becomes something closer to triumph — not the triumph of winning, but the triumph of enduring, of feeling something fully, of not being small in the face of it.",

    "Why The Formula Works" to
        "Three mechanisms work together to produce this effect.\n\nThe Mere Exposure Effect transforms the emotional texture. Repetition of the riff changes raw sadness into familiar sadness — something that feels less like a wound and more like a known landscape. The edge comes off, replaced by a kind of intimacy with the feeling.\n\nPhysiological arousal completes the shift. The crescendo and the entering instruments trigger a real nervous-system response: frisson, heightened attention, the body's sense that something significant is building. Volume becomes emotion. The body is moved before the mind catches up.\n\nThe bittersweet effect provides the frame. Minor-key music, by its nature, holds more than one thing simultaneously — sadness and something else, loss and something that survives the loss. When amplified to anthem scale, the \"something else\" becomes the foreground. What was once the color of sadness is now the color of shared human experience.\n\nThe result is something most people would struggle to describe: it started sad, it got louder, and now it feels powerful. That is not an accident. It is the arc of the power ballad, and it works because it moves through the listener's own psychology in exactly the right order."
)

@Composable
fun AboutMusicPowerBalladScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PowerBalladBg)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Power Ballads",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        powerBalladSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = PowerBalladHeading,
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
