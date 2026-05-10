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

private val CortisolScreenGray = Color(0xFF2D2D2D)
private val CortisolHeadingColor = Color(0xFFDDDDDD)

private val cortisolSections = listOf(
    "What Is Cortisol" to "Cortisol is a steroid hormone — not a neurotransmitter. Neurotransmitters are chemicals that cross synapses between neurons; hormones are released into the bloodstream and travel to target organs throughout the body. Cortisol is produced by the adrenal glands — two small glands that sit on top of the kidneys — specifically in the outer layer called the adrenal cortex. It belongs to a family called glucocorticoids, meaning it affects glucose (sugar) metabolism. It also follows a circadian rhythm: levels peak roughly 30 minutes after waking (the cortisol awakening response) and fall throughout the day.",

    "How It's Triggered — The HPA Axis" to "The main pathway is the hypothalamic-pituitary-adrenal (HPA) axis. When the brain perceives a stressor, the hypothalamus releases corticotropin-releasing hormone (CRH), which signals the pituitary gland to release adrenocorticotropic hormone (ACTH), which travels in the bloodstream to the adrenal cortex, which then secretes cortisol.\n\n\"Secreted\" is the correct biological term — hormones are secreted by glands into the blood; neurotransmitters are released at synapses. The whole cascade takes only a few minutes. Perceived psychological threats — an argument, a looming deadline, a frightening thought — trigger it just as physical stressors do.",

    "What It Does to the Body" to "Cortisol's purpose is to mobilise energy and sharpen alertness for dealing with a threat. It raises blood glucose (by prompting the liver to produce more glucose and reducing insulin sensitivity), increases heart rate and blood pressure, temporarily suppresses digestion, and heightens sensory alertness. It also suppresses the immune system in the short term — useful when fighting a predator, costly when chronic. The net effect is a body primed for immediate action: more fuel in the blood, more arousal in the nervous system, fewer non-essential processes running.",

    "The Vigilance Cycle" to "Elevated cortisol sensitises the amygdala — the brain's threat-detection centre — making it more reactive to potential dangers. A more reactive amygdala interprets ambiguous signals as threatening, which the brain registers as new stress, which triggers more cortisol.\n\nThis is the cycle: cortisol → heightened self-monitoring and threat detection → more perceived threats → more cortisol. It is not purely about being \"self-aware\" but about the brain's threat-scanning system running on high. McEwen (1998) called the cost of this sustained arousal allostatic load — the wear and tear of repeated or chronic stress activation.",

    "Chronic Cortisol and Its Effects" to "Designed for short bursts, cortisol becomes damaging when chronically elevated. It impairs the hippocampus (the brain's memory-formation structure), which is why people under prolonged stress have difficulty forming new memories and often feel mentally foggy. It disrupts sleep (cortisol and the sleep hormone melatonin are on opposite cycles), promotes fat storage particularly around the abdomen, and keeps the immune system suppressed — making chronic stress a genuine health risk.\n\nSapolsky's Why Zebras Don't Get Ulcers (1994) documents this extensively: the same physiological system that saves a zebra from a lion destroys a person who worries constantly.",

    "Breaking the Cycle" to "The primary lever is activating the parasympathetic nervous system, which is cortisol's physiological counterweight. Slow, extended exhalation breathing (see the Vagus Nerve screen) directly lowers cortisol and heart rate within minutes. Aerobic exercise burns off the glucose cortisol mobilised and provides a clean endpoint to the stress response. Quality sleep allows the HPA axis to reset — cortisol rises sharply when sleep is disrupted.\n\nSocial contact and laughter reduce cortisol. Heinrichs et al. (2003) showed that social support buffers the HPA response to stress. Cognitive reframing — changing the perceived severity of a stressor rather than the stressor itself — can interrupt the trigger at the hypothalamus level."
)

@Composable
fun CortisolScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CortisolScreenGray)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Cortisol",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 28.dp)
        )
        cortisolSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = CortisolHeadingColor,
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
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(text = "Back", color = Color.White, fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}
