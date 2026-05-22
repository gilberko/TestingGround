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

private val BoredomScreenGray = Color(0xFF2D2D2D)
private val BoredomHeadingColor = Color(0xFFDDDDDD)

private val boredomSections = listOf(
    "What Boredom Actually Is" to "Not simply an absence of fun. Eastwood, Frischen, Fenske, and Smilek (2012, Perspectives on Psychological Science) defined boredom as \"the aversive experience of wanting, but being unable, to engage in satisfying activity.\" The word wanting is critical: boredom involves genuine desire for stimulation alongside inability to connect to it. The discomfort is measurable — heart rate changes, negative affect, distorted time perception.\n\nBoredom is not relaxation and not contentment. It is an unpleasant motivational state that pushes toward action — while simultaneously presenting a low-arousal, low-motivation baseline that makes action paradoxically difficult to initiate.",

    "The Paradox — Wanting Excitement But Saying No" to "The pattern of rejecting suggestion after suggestion while clearly not wanting to be bored is real and has a coherent explanation.\n\nBoredom involves what researchers call a failure of attention-goal coupling: the brain cannot connect potential activities to a felt sense of reward or purpose. Fahlman, Mercer-Lynn, Flora, and Eastwood (2013) linked boredom specifically to this decoupling. The bored person's mental simulation of proposed activities comes out flat — they cannot generate the anticipatory sense of enjoyment that would make beginning feel worthwhile.\n\nThere is also an effort-threshold problem. Beginning any new activity requires a small initiation investment. Under normal arousal, this threshold is easily cleared. Boredom lowers arousal and motivation simultaneously, raising the effective threshold. Things that ordinarily seem easy to start feel like too much work. The rejections are genuine — the brain is accurately reporting that from its current state, none of the options feel like they will clear the effort bar. What's distorted is the low-gain prediction machinery generating that report, not the honest experience of disinterest.",

    "The Effort Equation — Why Nothing Sounds Good" to "Treadway, Buckholtz, Schwartzman, Lambert, and Zald (2012, Neuron) showed that dopamine levels directly predict willingness to exert effort for rewards. Low dopamine signaling — associated with boredom, low arousal, and dysphoria — reduces how much effort a person will invest to secure a reward of a given size.\n\nThe result: an activity that would normally seem worth starting — a new film, a walk, a creative project — doesn't reach the brain's internal effort-to-reward threshold. The expected reward, as the brain models it from a bored state, feels insufficient to justify even modest initiation costs. The person says no not because the activity is genuinely poor but because their reward-anticipation system is running at low gain. The felt sense of disinterest is real; what's distorted is the prediction system generating it.",

    "Why Scrolling Works — And Why Habits Win" to "Social media scrolling, channel flipping, and other habitual low-effort behaviors solve the boredom problem through a specific combination: near-zero initiation cost, familiar format, and a guaranteed floor of stimulation.\n\nThe mechanism involves variable-ratio reinforcement, from Skinner's operant conditioning research. Variable-ratio schedules — reward after unpredictable numbers of responses — produce the highest and most persistent response rates of any reinforcement schedule. Social media applies this directly: most content is unremarkable, but something interesting appears at unpredictable intervals. The unpredictability is precisely what makes it compelling.\n\nBut the decisive factor for boredom specifically is not the unpredictability — it's the near-zero risk. The bored person knows the format. They know how to do it. They know it won't require deep engagement or risk the disappointment of a high-investment activity that fails to satisfy. The decision to scroll costs nothing and carries no downside. Teng, Qian, Li, and Chai (2021) found that state boredom is a strong predictor of problematic smartphone use, specifically through the low cognitive load that smartphone activity requires — supporting the idea that boredom drives toward low-effort familiar formats rather than toward potentially rewarding but uncertain alternatives.",

    "What Would Actually Help" to "The activities most likely to genuinely relieve boredom — exercise, creative work, social engagement, learning — all have higher initiation costs and no guaranteed immediate reward. They are exactly what the bored brain under-invests in.\n\nCsikszentmihalyi's research on flow states (1990) found that genuine engagement emerges when a task's challenge level is slightly above current skill — requiring real effort but within reach. Boredom tends to occur precisely when skill is available but no challenge matches it. Transitioning from boredom to flow requires an initial effort investment that the bored state is specifically poor at generating.\n\nExternal kickstart bypasses this. Someone initiating an activity for you — making a plan rather than suggesting options, putting something on rather than asking what you want to watch, starting the project and pulling you in — removes the decision and initiation cost from the bored person. This is why boredom is more easily solved by others than by the bored person themselves. Presenting a menu of options that must be evaluated and chosen from is the worst format: it asks the bored brain to generate enthusiasm for something it can't currently feel enthusiasm for, one item at a time."
)

@Composable
fun BoredomScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BoredomScreenGray)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Boredom",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 28.dp)
        )
        boredomSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = BoredomHeadingColor,
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
