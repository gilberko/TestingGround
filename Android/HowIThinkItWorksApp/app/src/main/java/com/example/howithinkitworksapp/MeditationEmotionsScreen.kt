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

private val MeScreenGray = Color(0xFF2D2D2D)
private val MeHeadingColor = Color(0xFFDDDDDD)

private val meSections = listOf(
    "They Will Come" to "During meditation, thoughts, emotions, and physical sensations will arise — this is not a sign that you are doing it wrong. The mind's default is to generate mental activity. Expecting silence is like expecting the ocean to stop moving. The question is not whether thoughts will appear, but what you do when they do.",

    "The Rabbit Hole Problem" to "When a thought appears and you engage with it — follow its logic, respond to it emotionally, start planning around it — you are doing what the brain does by default: elaborating. Neuroscience calls this default mode network (DMN) activity. The DMN is associated with self-referential thought and rumination.\n\nResearch by Judson Brewer (2011, PNAS) showed that experienced meditators had less DMN activity and, crucially, less coupling between the self-referencing and emotional regions of the DMN. When you engage with a stressful thought, you trigger the stress response — cortisol rises, the amygdala fires, the body becomes alert — and your attention narrows further onto the very thing that is stressing you. The thought magnifies.",

    "The Exam Thought — An Example" to "You are meditating. A thought arrives: the exam tomorrow. If you engage with it, your body starts to respond — mild chest tightness, a quickened breath — and your attention locks onto the thought. You think about what might go wrong, which generates more threat-related activation, which pulls more attention, which magnifies the feeling.\n\nThis is the cycle. But notice: the thought itself is not the problem. The loop is.",

    "Observe, Don't Engage" to "The alternative is to notice the thought without following it in. Acknowledge it as a mental event — something that appeared — rather than as a fact demanding a response. This is called cognitive defusion in Acceptance and Commitment Therapy (ACT), developed by Steven Hayes (1999). Instead of thinking \"I am scared about the exam,\" you notice: there is a thought about the exam, and it brings some stress with it. The thought is still there. You have not suppressed it. But you have stepped back from it.\n\nResearch on MBCT (Mindfulness-Based Cognitive Therapy — Segal, Teasdale and Williams, 2002) showed that this kind of decentering — treating thoughts as transient mental events rather than as the self or as truth — significantly reduced relapse in depression. The skill being developed is not the absence of thought but a different relationship with it.",

    "Where Do You Feel It?" to "Once you have noticed a thought and acknowledged it, you can briefly turn curiosity toward the sensation it produces. Not analysing it — just locating it. Is there tightness somewhere? A change in breathing? A feeling in the stomach?\n\nThis is interoception: the awareness of internal body states. The insula, a brain region involved in interoception, becomes active during mindful body awareness. Research by Farb et al. (2013) found that people who were better at noticing body sensations also showed greater emotional resilience. Asking \"where do I feel this?\" momentarily shifts processing from narrative (verbal and conceptual) to experiential — a different mode, and a calmer one.",

    "As If For The First Time" to "The meditation teacher Shunryu Suzuki wrote: \"In the beginner's mind there are many possibilities; in the expert's mind there are few.\" Applied here: approach each thought, feeling, or sensation as if encountering it for the first time. What is this tightness? Where exactly is it? What does it feel like — pressure, warmth, a pulling? Not to answer these questions analytically, but to inhabit the curiosity itself.\n\nThis orientation prevents the brain from running its standard interpretive shortcuts — \"this is stress, this is bad, this is about the exam\" — and slows down the elaboration that leads to rumination. Discovery mode and problem-solving mode are incompatible. You cannot analyse and wonder simultaneously.",

    "Acknowledge and Return" to "Mindfulness is not about laser-focusing on one thing indefinitely — it is about the width and quality of attention. After briefly acknowledging a thought and its felt sense, you return: to the breath, to the body as a whole, to sounds, to the present moment. You are still breathing. You can feel your feet. The thought was one event among many happening simultaneously. Acknowledging it without dwelling gives it less fuel.\n\nThe Shapiro et al. (2006) reperceiving framework describes this as a shift in perspective — from being inside an experience to observing it — which creates space for a more flexible, less reactive response. The thought does not disappear. But it no longer has the whole of your attention.",

    "The Research Summary" to "What you are practising has several names across different traditions and research frameworks: cognitive defusion (ACT), decentering (MBCT), reperceiving (Shapiro, 2006), metacognitive awareness (Wells, 1994). All point at the same capacity: the ability to notice your own mental activity from a slight distance, without being carried by it.\n\nThis does not eliminate emotions or stress — it changes your relationship to them. And that relationship, research suggests, matters more than the content of any individual thought."
)

@Composable
fun MeditationEmotionsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MeScreenGray)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Dealing With Emotions",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 28.dp)
        )
        meSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MeHeadingColor,
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
