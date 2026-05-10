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

private val MwiaScreenGray = Color(0xFF2D2D2D)
private val MwiaHeadingColor = Color(0xFFDDDDDD)

private val mwiaSections = listOf(
    "Not Doing Nothing" to "Meditation is commonly imagined as sitting still with an empty mind — not thinking, not feeling, just existing in a kind of pleasant blankness. This is a misconception. Attempting to stop thoughts entirely is neither the goal nor what happens in practice. Thoughts continue to arise. Feelings continue to surface. The distinction is in the relationship you maintain with them.\n\nThe practice is better described as aware presence: you notice what is moving through your mind without becoming it. You are the observer, not the participant. This is closer to watching traffic from a pavement than to stopping the cars.",

    "Watching Without Being Carried" to "Normally when a thought arises, it leads somewhere. An anxious thought triggers another, then another, and within seconds you are deep in a mental narrative — planning, worrying, replaying — without ever consciously choosing to go there. The same happens with emotions: a feeling of irritation can unfold into a full internal argument before you notice you are having one.\n\nThe skill meditation builds is the ability to notice this happening — the moment of catching yourself — and to return to a neutral point of observation rather than following the chain further. Jon Kabat-Zinn, who founded Mindfulness-Based Stress Reduction (MBSR) at the University of Massachusetts in 1979, defined mindfulness as \"paying attention in a particular way: on purpose, in the present moment, and non-judgmentally.\" Each of those qualifiers matters.",

    "Awareness, Not Suppression" to "A common misreading is that the goal is to ignore thoughts or push feelings away. This tends to backfire: suppression research (Wegner et al., 1987) shows that trying not to think about something typically makes it more intrusive, not less. The famous instruction \"don't think of a white bear\" reliably produces white bears.\n\nMeditation asks for something different: let the thought or feeling be present, notice it clearly, and simply not follow it. The metaphor often used is clouds passing through an open sky. The sky does not engage with the clouds or try to disperse them — it simply remains, and the clouds move through on their own. You are the sky.",

    "Why It's Difficult" to "The difficulty is not laziness or lack of concentration. It is habit. The mind has spent years — possibly decades — treating every thought as something to engage with and every feeling as something to act on. The default mode network (DMN), a set of brain regions active during mind-wandering and self-referential thought, is essentially the neural substrate of the distracted mind. It is highly practiced.\n\nBrewer et al. (2011) found that experienced meditators showed significantly reduced activity in the DMN compared to beginners, particularly in regions associated with self-referential processing and mind-wandering. The change takes time. The difficulty you experience at the beginning is not a sign you're doing it wrong — it's evidence of the very habit the practice is designed to change.",

    "The Returning Is the Practice" to "A persistent misconception is that a good meditation session is one without distraction — where the mind stays put. Experienced practitioners describe it differently. The moment of noticing that you have been carried away and gently returning to the present is not a failure. It is the practice. Each return is the equivalent of a repetition in physical training.\n\nKabat-Zinn refers to this as \"beginning again.\" The instruction is not to sit and succeed at staying focused, but to sit and notice when you have drifted, and return — without self-criticism — as many times as it takes. The frequency of distraction is not the measure. The quality of the return is.",

    "What It's Not" to "Meditation is not sleep (though the boundary can blur in early practice). In sleep, awareness withdraws. In meditation, awareness is the point — it is active, even when the body is still and the mind is quiet. It is also not a religious requirement: secular mindfulness programmes, used in hospitals, schools, and corporate settings, are based on the same attentional principles without any doctrinal framework.\n\nIt is also not a quick fix. Structural brain changes associated with regular practice — increased grey matter density in the hippocampus and insula, reduced amygdala reactivity — have been documented in studies of experienced meditators (Hölzel et al., 2011), but these reflect sustained practice over months and years, not a single session.",

    "The Buffer — Carrying the Habit Into Life" to "One of the most practically valuable effects of regular meditation is not what happens during the session but what transfers to the rest of life. Through repeated practice of noticing a thought or emotion and choosing not to immediately follow it, the mind begins to develop what researchers call response flexibility: a gap between stimulus and response where deliberate choice becomes possible.\n\nViktor Frankl, the psychiatrist and Holocaust survivor, put it plainly: \"Between stimulus and response there is a space. In that space is our power to choose our response.\" Meditation practice is essentially the exercise of widening that space.\n\nIn everyday terms: without this habit, a frustrating event triggers immediate frustration and immediate reaction. With it, the same event triggers the same feeling, but there is a recognisable moment before the reaction — the feeling is noticed, observed, and a response is chosen rather than discharged automatically. This is not suppression; the feeling is fully present. The difference is that you are relating to it rather than being it.\n\nShapiro et al. (2006) proposed that the core mechanism of mindfulness is reperceiving: a fundamental shift in perspective in which you step back from the immediate content of your experience and observe it from a slight distance. This reduces what they call automaticity — the tendency for thoughts and feelings to trigger behaviours without any intervening awareness.\n\nIn clinical settings, this mechanism underlies Mindfulness-Based Cognitive Therapy (MBCT), which has been shown in multiple randomised trials to significantly reduce relapse rates in recurrent depression (Teasdale et al., 2000; Segal et al., 2002). The reason is not that the practice eliminates negative thoughts but that it changes the patient's relationship to them: thoughts are experienced as mental events rather than facts or commands, and the habit of automatic escalation is interrupted."
)

@Composable
fun MeditationWhatsItAboutScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MwiaScreenGray)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "What Is Meditation",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 28.dp)
        )
        mwiaSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MwiaHeadingColor,
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
