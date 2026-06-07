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

private val AboutThinkingBg = Color(0xFF2D2D2D)
private val AboutThinkingHeading = Color(0xFFDDDDDD)

private val aboutThinkingSections = listOf(
    "Two Types of Thinking" to
        "Associative thinking is when the mind wanders freely — a word, a sound, a sight triggers a chain of connections with no destination. Because there's no goal, the mind reaches unexpected places and that's where creativity lives. Focused thinking is the opposite: we lock onto a problem and narrow our mental lens. We suppress distractions and unrelated associations. This is useful but it trades creative range for depth.",

    "Why Focused Thinking Limits Creativity" to
        "When solving a specific problem we actively block out what seems irrelevant. The brain treats \"unrelated\" as noise. The narrowing that makes focused thinking powerful is the same thing that makes it uncreative — we won't wander to the region where the unusual solution lives. The one exception: evaluating a candidate solution. When we test an idea we can be very creative — about why it won't work, where it breaks, what edge cases exist. Judgment is creative in a focused direction.",

    "An Iterative Method That Brings Both Together" to
        "Start with the most absurd idea imaginable as your solution — or pick a random object nearby and declare it the answer. Then focus: why would this work? Why won't it? What would have to change for it to work? Make one adjustment and repeat. This tricks the brain. The absurd premise forces associative leaps; evaluating it channels focused analysis. Iteration moves you from the ridiculous toward something real, and each cycle can surface unexpected insights that pure focused thinking would never reach.",

    "Associative Thinking and the \"Background\" Mind" to
        "Creative breakthroughs often arrive when we're not trying — in the shower, on a walk, half asleep. The associative mind works in the background even when we're focused on something else. Deliberately switching modes — focused work followed by unstructured rest or distraction — may be more productive than staying in either mode too long. The focused session loads the problem; the associative session finds the unexpected path."
)

@Composable
fun AboutThinkingScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AboutThinkingBg)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "About Thinking",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        aboutThinkingSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = AboutThinkingHeading,
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
