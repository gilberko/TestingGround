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

private val AvoidanceBg = Color(0xFF2D2D2D)
private val AvoidanceHeading = Color(0xFFDDDDDD)

private val avoidanceSections = listOf(
    "The Short-Term Logic of Avoidance" to
        "Avoidance feels rational. There is something I am afraid of — a confrontation, a performance, a difficult decision, a physical experience — and by not doing it, I don't have to feel the fear. In the short term this works: the anxiety decreases. The relief is real and immediate. But this relief is exactly the problem. When the brain learns that avoidance makes fear go away, it reinforces avoidance as the strategy. The cognitive behavioral model of anxiety (Clark, 1999) identifies this cycle as the core maintenance mechanism: avoidance prevents the fear from being tested, and untested fears are preserved at their imagined intensity. The behavior that was supposed to protect against suffering ends up generating it, over and over, every time the avoided situation approaches.",

    "Don't Think of a White Bear" to
        "Daniel Wegner's ironic process theory (1987, Psychological Review) demonstrated something striking: when people are told not to think of a white bear, they think of it more than people who were never given the instruction. The attempt at suppression creates a monitoring process — the brain keeps checking whether the forbidden thought has appeared — and this monitoring paradoxically keeps the thought active. Avoidance works the same way with fears. To avoid something successfully, you must keep track of it. The fear you are trying not to confront stays in mental view, because vigilance requires knowing where the threat is. The attention you are trying to remove from the fear is exactly the attention the avoidance strategy demands.",

    "The Thinking Brain Is a Side Actor" to
        "When we contemplate avoiding something, we feel like we are making a careful rational assessment. We run mental simulations: what would it be like? How bad could it get? We analyze the risks and worst-case scenarios in detail. This feels like prudent planning. But the executive brain — the prefrontal cortex — is largely responding to signals from deeper structures that have already issued a threat evaluation. The amygdala detects a potential threat, activates a fear response, and the cortex's job is primarily to elaborate, contextualize, and justify that response. Antonio Damasio's somatic marker hypothesis (1994) describes how emotional signals from the body guide cognition, often before deliberate reasoning begins. The \"analysis\" of why to avoid something is frequently the cortex constructing reasons for a decision the emotional brain has already made.",

    "Imagination Amplifies" to
        "The feared scenario, contemplated in avoidance, is not real. But the brain does not fully distinguish vivid imagination from direct experience when it comes to emotional responses. The amygdala responds to imagined threats — to mental simulations of failure, embarrassment, or pain — in ways that have real physiological consequences: elevated cortisol, increased heart rate, heightened vigilance. Research on stress reactivity shows that anticipatory anxiety — the stress before a challenging event — can be as physiologically costly as the event itself (Kirschbaum et al., 1993). When we ruminate on why something would be bad, we are not preparing — we are experiencing the worst-case scenario repeatedly, in full emotional resolution, without any of the real-world information that comes from actually doing the thing.",

    "The Self-Fulfilling Prophecy" to
        "When fear is the dominant mental state going into a performance or a challenge, it does not stay in the mind. The body responds: muscle tension increases, working memory is partially occupied by threat-monitoring, attention narrows to potential failure cues. Claude Steele and Joshua Aronson's research on stereotype threat (1995, Journal of Personality and Social Psychology) showed that reminding people of a negative expectation — even subtly — reliably degrades performance on cognitively demanding tasks. The nocebo effect (the dark mirror of placebo) demonstrates the same principle medically: the expectation of harm creates real physiological harm (Benedetti et al., 2007). When we spend time rehearsing failure through avoidance-driven rumination, we are not passively predicting the outcome — we are partly creating it.",

    "What Actually Works" to
        "The research consistently points in one direction: gradual approach, not avoidance. Exposure therapy — the empirically validated gold standard for anxiety (Foa & McLean, 2016, Annual Review of Clinical Psychology) — works by systematically doing the avoided thing, in manageable doses, until the brain learns that the predicted catastrophe does not occur. Each approach is a prediction test: the fear says this will be terrible, and the actual experience provides data. The amygdala's fear conditioning can be updated, but only by lived experience — not by analysis, reassurance, or careful planning. Acceptance and Commitment Therapy (Hayes, 1999) frames this as: the goal is not to eliminate the fear, but to act in the presence of it. The fear may not disappear immediately. But the power attributed to it — amplified by avoidance — begins to diminish the moment you stop organizing your life around it."
)

@Composable
fun AvoidanceScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AvoidanceBg)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Avoidance",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        avoidanceSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = AvoidanceHeading,
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
