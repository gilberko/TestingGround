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

private val WalScreenGray = Color(0xFF2D2D2D)
private val WalHeadingColor = Color(0xFFDDDDDD)

private val walSections = listOf(
    "The Two Games" to "Game A: flip a coin — heads you get \$100, tails you get \$30. Game B: I give you \$100, then we flip — heads you keep the \$100, tails you pay back \$70. Both games have the same expected value (\$65) and the same two possible outcomes (\$100 or \$30 in hand). The math is identical. But they feel completely different.",

    "Why They Feel Different — Framing" to "In Game A your reference point is zero — both outcomes are gains, so you feel excitement about how much you will win. In Game B your reference point is \$100 (you already have it) — paying back \$70 is coded by the brain as a loss, not as \"ending up with \$30.\" The framing sets the reference point, and the reference point changes everything.\n\nThis is the Framing Effect, first described systematically by Tversky and Kahneman (1981, Science) in their famous Asian Disease Problem, where \"400 people will die\" and \"200 people will be saved\" describe the same outcome yet produce opposite preferences. Same facts, different frame, different feeling.",

    "Prospect Theory — Kahneman & Tversky, 1979" to "In their 1979 paper (Econometrica), Daniel Kahneman and Amos Tversky proposed Prospect Theory to explain why people's choices deviate from pure expected-value math. The core insight: people evaluate outcomes relative to a reference point (usually the current state), not in absolute terms. A gain of \$30 feels very different depending on whether your reference point is zero or \$100.\n\nKahneman received the Nobel Prize in Economics in 2002 for this work. It is one of the most cited papers in all of economics and psychology, and it remains the foundation of behavioural economics.",

    "Loss Aversion" to "The value function in Prospect Theory is S-shaped and asymmetric: losses feel roughly twice as painful as equivalent gains feel good. Losing \$70 registers as more significant than gaining \$70, even though the dollar amounts are equal. This is loss aversion.\n\nIt explains why Game B feels threatening — the possibility of \"losing\" \$70 relative to the \$100 reference carries disproportionate emotional weight, even though the worst outcome is simply ending up with \$30 — which is still \$30 more than you started with.",

    "What Happens In The Brain" to "Imaging studies show the brain processes gains and losses through different circuits. The ventral striatum activates for anticipated gains, driving the reward signal. The amygdala — the brain's threat-detection centre — shows greater activation for losses than for equivalent gains, triggering a mild fear and stress response.\n\nA key paper (Tom et al., 2007, Science) directly measured neural activity during loss-aversion decisions and found the striatum ramping down for potential losses more steeply than it ramped up for gains — mirroring the asymmetric S-shaped value function at the neural level. The ventromedial prefrontal cortex (vmPFC) integrates these emotional signals and encodes value; it too shows asymmetric responses to gain versus loss frames, explaining why the feeling isn't purely rational even when you consciously know the math.",

    "Do Other People Perceive It The Same Way?" to "Yes — this is one of the most robustly replicated findings in behavioural science. Loss aversion appears across cultures, ages, and education levels. The same framing manipulations produce consistent preference reversals in populations around the world (Wang, 1996; Harinck et al., 2007).\n\nResearchers have even found similar patterns in capuchin monkeys (Chen et al., 2006), suggesting the bias has deep evolutionary roots: losing an existing resource was historically more dangerous than failing to gain a new one, so the brain learned to weight losses more heavily. This is not a quirk of human culture — it appears to be a feature of how value is computed in mammalian nervous systems.",

    "Why the Reference Point Matters" to "The reference point is not fixed — it is set by context, language, and expectation. Telling you \"I'm giving you \$100\" anchors your reference at \$100 before the coin is even flipped. Telling you \"you might win \$100 or \$30\" anchors your reference at zero. Same coin, same outcomes, opposite emotional tone.\n\nThis is why framing is so powerful in negotiation, marketing, medicine (\"90% survival rate\" vs \"10% mortality rate\"), and politics. Whoever sets the reference point shapes how the options feel — before any reasoning begins.",

    "Defending Against It" to "Awareness helps but does not fully override the bias — loss aversion operates partly below deliberate control. Useful strategies: reframe the choice explicitly before deciding (convert Game B mentally to \"I will end up with \$30 or \$100\"); focus on absolute outcomes rather than changes from a reference point; and when comparing options, ask yourself whether the reference point was given to you by someone else or chosen by you.\n\nRecognising that your discomfort in Game B is a framing artefact — not a signal about the actual outcomes — is the first step to making the choice on its actual merits."
)

@Composable
fun WinningAndLosingScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WalScreenGray)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Winning And Losing",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 28.dp)
        )
        walSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = WalHeadingColor,
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
