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

private val GdmScreenGray = Color(0xFF2D2D2D)
private val GdmHeadingColor = Color(0xFFDDDDDD)

private val sections = listOf(
    "What Is This" to "Daniel Kahneman identified two distinct modes of thinking in his book \"Thinking, Fast and Slow.\" I think of System 1 as the fast brain — the part that reacts instantly — and System 2 as the slow brain — the part that can actually reason. They don't take turns; they run simultaneously, but the fast brain almost always wins the race.",

    "The Fast Brain" to "The fast brain is constantly running in the background, scanning everything — events, sensations, emotions, social cues — and generating instant reactions before the slow brain has even registered what happened. I think of it like an OS interrupt handler: when something triggers it, it fires immediately, bypassing the normal queue. It doesn't deliberate. It doesn't weigh options. It reacts.",

    "The Slow Brain" to "The slow brain can actually think. It can reason through a problem, consider alternatives, and plan. The catch is that it almost always receives its inputs after the fast brain has already acted. By the time the slow brain is engaged, a feeling has already fired, a word has already been said, or a choice has already been made. It's working with a fait accompli.",

    "The Illusion of Reason" to "Here's the uncomfortable part. When the slow brain examines what the fast brain just did, it generates an explanation. It tells a story — \"I did that because...\" — that sounds like reasoning. But that story may have had zero causal influence on the actual decision. The fast brain already acted. The slow brain is writing the press release after the fact. This is what researchers call post-hoc rationalization, and I find it one of the most humbling ideas in psychology: the feeling of having reasons for what we do is often just a story we tell ourselves afterward.",

    "Influencing Fast Through Habit" to "The slow brain can't override the fast brain in the moment — but it can train it over time. When we deliberately practice something repeatedly, we are essentially filing a request with the fast brain: \"please take this over.\" Eventually, the fast brain adopts the behavior as a habit — something it runs automatically, without deliberation. This is why habits are so powerful and so hard to break: they are now operating at the interrupt-handler level, not the reasoning level.",

    "Influencing Fast Through Reframing" to "The slow brain can tell a story that attaches a new emotion to a recurring event. If every time I think about going to the gym I consciously reframe it as \"this is how I take care of myself\" rather than \"this is hard,\" I'm trying to install a new emotional tag on that trigger. The next time the gym thought arrives, the fast brain may fire a slightly different emotional response — not because I reasoned my way there in the moment, but because I did the work earlier to rewire the association.",

    "Influencing Fast Through Reminiscing" to "Deliberately revisiting past experiences — really sitting with them and re-feeling them — can strengthen or reshape the emotional tags attached to those memories. If I recall a moment of genuine success and let myself feel the pride of it vividly, I'm reinforcing the emotional signature of that memory. Over time, this can shift what the fast brain fires when similar situations arise, because the memory it's drawing on has been emotionally enriched.",

    "Influencing Fast Through Memory Plasticity" to "Memories aren't recorded and played back like video files. They're reconstructed every time we recall them, and the reconstruction is influenced by our current state, expectations, and imagination. This means that if I repeatedly imagine an event slightly differently, or introduce doubt about what exactly happened, the stored memory can gradually shift. New emotional anchors can be created. Old ones can be weakened. This is both fascinating and a little unsettling — it means the boundary between memory and imagination is softer than we'd like to think."
)

@Composable
fun GeneralDecisionMakingScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GdmScreenGray)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "General Decision Making",
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
                color = GdmHeadingColor,
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
