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

private val MbsScreenGray = Color(0xFF2D2D2D)
private val MbsHeadingColor = Color(0xFFDDDDDD)

private val mbsSections = listOf(
    "What It Is" to "Body scanning is a structured meditation technique in which you move deliberate attention methodically through each region of the body, noticing whatever sensations are present there without trying to change them. It is typically done lying down, though it can be practised seated. A complete scan might take 20–45 minutes, though shorter versions (5–10 minutes) are used in clinical settings.\n\nThe technique was formalised and researched by Jon Kabat-Zinn as part of Mindfulness-Based Stress Reduction (MBSR). In his original programme at the University of Massachusetts Medical School (1979), the body scan was practised daily as the primary formal meditation, particularly in the first weeks.",

    "How It Works" to "The standard progression begins at the feet — specifically the toes of the left foot — and moves upward through the body: foot, ankle, lower leg, knee, thigh, then switching to the right side, then moving up through the pelvis, abdomen, lower back, chest, upper back, shoulders, arms (usually both together), hands, neck, face, and scalp. At each location, you simply notice whatever is there: warmth, pressure, tingling, tension, numbness, nothing in particular.\n\nThere is no correct sensation. The instruction is observation, not achievement. If you notice nothing in a particular region, that is the observation — nothing is there. If you notice tension, you notice tension, without necessarily needing to release it.",

    "The Anchor Function" to "Like breathing, body scanning gives the attention a specific, structured task. Because the task moves through the body in sequence, it has a built-in progression that pulls attention forward — from region to region — which can make it easier to sustain than breath-only meditation for some people. The structure itself does a portion of the work.\n\nWhen the mind drifts — which it will — the return is to whichever body region you were last attending to, or simply back to wherever you are now. The scan provides a place to return to, not just a direction to look in.",

    "Interoception — The Science Behind It" to "Body scanning develops what researchers call interoception: the sense of the body's internal state. This is distinct from proprioception (knowing where your limbs are in space) and from sensing the external environment. Interoception includes signals like heartbeat, hunger, temperature, and the subtle physical correlates of emotional states.\n\nKerr et al. (2013) proposed that mindfulness practice, including body scanning, works in part by improving the resolution and accuracy of interoceptive processing — you get better at detecting what your body is actually doing. Farb et al. (2013) found that body scan practice increased activity in the insula (a key interoceptive region) and reduced rumination. People become more accurately attuned to their physical state, which appears to make emotional regulation easier.",

    "Emotions Have a Body" to "One practical reason body scanning is valued in therapeutic contexts is that emotions are experienced physically before they are labelled mentally. Anxiety shows up as tightness in the chest, a clenched jaw, a hollow feeling in the stomach — often before the word \"anxious\" ever forms. By the time you consciously label a feeling, the body has been holding it for some time.\n\nBody scanning trains the ability to notice these physical signals earlier and with more precision. Rather than being surprised by a strong emotion that has been building unnoticed, regular practitioners often report catching the body's early signals — the tightening, the shift in breath, the restlessness — and having more time and space to respond. Kabat-Zinn used body scanning extensively in chronic pain management programmes, finding that patients who learned to observe pain sensations with non-judgmental attention reported reduced suffering even when the pain intensity remained unchanged.",

    "Together, Not As Cure" to "Neither breathing nor body scanning makes difficult thoughts or emotions disappear. Both are ways of giving the attention something real and present to anchor to, so that being swept away becomes less automatic. The goal is not emptiness but a different relationship with the content of the mind — one where you have slightly more choice about whether to follow a thought or simply notice it and let it pass.\n\nResearch on MBSR programmes (Hofmann et al., 2010, meta-analysis across 39 studies) found significant effects on anxiety, depression, and stress in clinical populations. These effects were not achieved through relaxation alone: the attentional training — learning to observe without reactivity — appears to be the active ingredient."
)

@Composable
fun MeditationBodyScanningScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MbsScreenGray)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Body Scanning",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 28.dp)
        )
        mbsSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MbsHeadingColor,
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
