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

private val BaeScreenGray = Color(0xFF2D2D2D)
private val BaeHeadingColor = Color(0xFFDDDDDD)

private val sections = listOf(
    "Fear" to "The amygdala is the primary alarm system here — it detects potential threats and fires before conscious awareness even registers what happened. The hypothalamus gets the signal almost simultaneously and triggers the fight-or-flight cascade through the HPA axis — heart rate up, stress hormones released. The prefrontal cortex (PFC) tries to evaluate whether the threat is real and can suppress the amygdala response if it concludes the fear is unwarranted, but this takes time and effort. The hippocampus provides context — it's asking whether this situation resembles past dangerous ones. The locus coeruleus floods the brain with norepinephrine, sharpening attention and preparing the body.\n\nExample: you hear a loud bang. The amygdala fires before you've identified the sound; your heart is already racing by the time your PFC decides it was a car door.",

    "Shame" to "The anterior cingulate cortex (ACC) is heavily involved — shame is a social emotion and the ACC processes social pain in ways that overlap significantly with physical pain. The insula generates the visceral, bodily feeling of shame — the heat, the urge to disappear. The prefrontal cortex contributes self-evaluation: it's comparing your behavior against internalized standards. The default mode network (DMN) is active, running the mental simulation of how others are judging you. The temporal-parietal junction (TPJ) handles social cognition — imagining the perspective of those around you.\n\nExample: you say something embarrassing in front of a group; the insula makes it feel physically awful, and the DMN keeps replaying how it must have looked to everyone else.",

    "Guilt" to "Guilt is distinct from shame in that it focuses on the action rather than the self (guilt = \"I did bad\"; shame = \"I am bad\"). The ventromedial prefrontal cortex (vmPFC) leads here — it handles moral reasoning and the evaluation of one's own behavior. The ACC generates the discomfort signal. The insula contributes bodily unease. The hippocampus keeps bringing the memory back so it can be examined. Unlike shame, guilt tends to motivate repair behavior — the vmPFC is generating \"what could I do to fix this\" scenarios.\n\nExample: you snap at a friend and immediately feel the urge to apologize; that reparative impulse is the vmPFC at work.",

    "Excitement" to "The ventral tegmental area (VTA) and its dopaminergic projections are central — dopamine floods the nucleus accumbens, creating the felt sense of anticipation and wanting. The amygdala contributes positive arousal. The prefrontal cortex engages to plan and elaborate on what's anticipated. The hypothalamus drives physiological activation — similar to fear in its body signature, but interpreted differently because the PFC labels it positive.\n\nExample: you're about to go on a trip you've been planning for months; dopamine is firing on anticipated reward, your heart rate is up, and your attention is narrowed to the upcoming event.",

    "Arousal (Sexual and Non-Sexual)" to "Sexual and non-sexual arousal share significant neural overlap. The hypothalamus plays a key role in sexual arousal specifically — the medial preoptic area coordinates sexual behavior and motivation. The dopaminergic VTA/nucleus accumbens pathway drives the wanting component in both. The amygdala processes emotionally salient stimuli. The insula registers the bodily state. The prefrontal cortex handles context and permission — whether to act or inhibit. Non-sexual arousal (general alertness or activation) draws more heavily on the locus coeruleus and the norepinephrine system.\n\nExample: both involve elevated heart rate and heightened attention, but sexual arousal has a hypothalamic signature that non-sexual arousal does not.",

    "Boredom" to "Boredom involves distinct patterns around the absence of engagement. The default mode network (DMN) becomes highly active — the mind wanders, generates internal simulations, and looks for something meaningful to do. The ACC detects the mismatch between a desired level of engagement and the actual level. The prefrontal cortex is understimulated and starts seeking novel tasks. Dopamine levels are relatively low — the nucleus accumbens is quiet.\n\nExample: sitting through a meeting that requires no real engagement; the DMN takes over, your mind drifts, and you find yourself thinking about unrelated things.",

    "Curious" to "Curiosity activates the dopaminergic system — specifically the VTA/nucleus accumbens pathway — because it involves anticipating a reward (information, understanding). The prefrontal cortex is engaged in generating questions and evaluating hypotheses. The hippocampus is active because curiosity is strongly linked to encoding new information and placing it in context. The ACC monitors for gaps between what is known and what is not, generating the discomfort of not-knowing that motivates exploration.\n\nExample: you read a sentence that ends unexpectedly; the ACC flags the knowledge gap, dopamine fires on anticipated resolution, and your attention narrows to find the answer.",

    "Stress" to "Stress is a systems-level response. The amygdala detects the stressor and signals the hypothalamus. The hypothalamus activates the HPA axis: CRH → pituitary ACTH → adrenal cortisol, plus the sympathetic nervous system driving adrenaline. The prefrontal cortex attempts to regulate the response, but under high or chronic stress, cortisol actually impairs prefrontal function, making regulation harder over time. The locus coeruleus maintains elevated norepinephrine, sustaining vigilance. The hippocampus is particularly vulnerable to chronic stress — prolonged cortisol exposure can damage hippocampal neurons, impairing memory and context processing.\n\nExample: a looming work deadline. The amygdala fires on the threat; cortisol sustains the arousal; the PFC tries to prioritize and plan but is fighting an uphill battle against elevated stress hormones.",

    "Calm" to "Calm is characterized more by what is not firing than what is. The amygdala is quiet. The prefrontal cortex, particularly the ventromedial portion, is active in a regulatory way — generating a sense of safety and control. The ACC shows reduced conflict monitoring. The parasympathetic nervous system (high vagal tone) is dominant, slowing heart rate. The DMN may be gently active — mind-wandering in a pleasant, non-anxious way. Serotonin and GABA are the primary neurochemical contributors to this state.\n\nExample: sitting outside on a warm day with nothing urgent to do; the amygdala is quiet, vagal tone is high, and serotonin is providing a background sense of wellbeing.",

    "Happy" to "Happiness as a sustained state involves the nucleus accumbens and dopaminergic reward circuits, but also the serotonin system broadly — serotonin provides the background \"all is well\" signal rather than the sharp peaks of dopamine. The prefrontal cortex, particularly the left hemisphere, shows elevated activity in positive mood states. The vmPFC generates positive self-evaluation. The hippocampus is involved in accessing positive memories. Endorphins also contribute, particularly when happiness is linked to physical activity or social bonding.\n\nExample: spending time with close friends; serotonin provides the sense of belonging and contentment, while moments of laughter and connection trigger endorphin and dopamine responses.",

    "Sad" to "Sadness involves reduced activity in reward circuits — the nucleus accumbens and VTA are relatively quiet. The subgenual anterior cingulate cortex (sgACC) is often hyperactive in sadness and depression — it seems to sustain the emotional state. The amygdala is reactive to negative stimuli. The prefrontal cortex attempts to regulate but may be impaired in shifting attention away from the source of loss. The DMN tends toward rumination — replaying the loss or what could have been different. The insula generates the bodily heaviness and lethargy associated with grief.\n\nExample: after a significant loss, the sgACC sustains the emotional processing, the DMN replays memories, and the insula creates the physical weight you can feel in your chest.",

    "Anxious" to "Anxiety differs from acute fear in that it involves sustained, anticipatory threat processing rather than a response to an immediate stimulus. The bed nucleus of the stria terminalis (BNST) is critical here — it drives sustained anxiety responses, while the amygdala handles acute fear. Both the PFC and ACC are active, trying to evaluate uncertain threats and monitor for outcomes. The hippocampus contributes contextual memories of past bad outcomes, feeding the threat evaluation. The locus coeruleus maintains elevated norepinephrine, keeping the system primed. The insula generates the bodily sensation of dread. The HPA axis may be chronically mildly activated.\n\nExample: waiting for medical test results. No immediate threat is present, but the BNST sustains the threat-anticipation response, the DMN generates worst-case scenarios, and the insula makes it feel physically uncomfortable."
)

@Composable
fun BrainAreasAndEmotionsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BaeScreenGray)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Brain Areas And Emotions",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 28.dp)
        )
        sections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = BaeHeadingColor,
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
