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

private val MotivationBg = Color(0xFF2D2D2D)
private val MotivationHeading = Color(0xFFDDDDDD)

private val motivationSections = listOf(
    "The Logical Case Doesn't Land" to
        "We instinctively try to motivate others — or ourselves — by building a case: here are the reasons, here are the benefits, here is the evidence. It's logical. It's reasonable. And it very often doesn't work. The problem is that people already know what's good for them. They know they should exercise, save money, have the difficult conversation. The information was never the obstacle. Daniel Kahneman's distinction between System 1 (fast, emotional, automatic) and System 2 (slow, deliberate, rational) helps explain why: decisions are mostly driven by System 1, and System 2 is recruited mainly to justify and explain afterward. Telling someone the reasons may feel like persuasion, but you are usually talking to the justification layer — not the decision layer.",

    "Where Decisions Actually Come From" to
        "The limbic system — especially the amygdala, the nucleus accumbens, and the anterior cingulate cortex — is deeply involved in processing what we care about, what we fear, and what feels worth doing. The prefrontal cortex (PFC) can plan and evaluate, but it is downstream from these emotional signals. Research using fMRI has shown that when people make value-based decisions, activity in the ventromedial PFC and ventral striatum (a dopamine-rich reward area) precedes and predicts their choice — before conscious reasoning completes (Hare et al., 2009, Science). The story the PFC tells is often a rationalization of a conclusion the emotional brain already reached. This doesn't mean logic is useless — but it means logic works on top of emotional permission, not instead of it.",

    "Make Them Feel It — Not Think It" to
        "What moves people is not understanding but feeling. Emotional resonance is the signal that something matters. Effective motivation connects the action to something the person already cares about deeply — a core value, a long-held aspiration, a fear that hasn't been addressed, a version of themselves they want to become. This is the foundation of Self-Determination Theory (Deci & Ryan, 1985), one of the most robust frameworks in motivation research: people are intrinsically motivated when an action connects to autonomy (it's my choice), competence (I can do this), or relatedness (it connects me to others I care about). Extrinsic reasons — money, approval, obligation — can create movement, but intrinsic meaning creates sustained effort. The key question isn't \"why is this a good idea?\" but \"why would this matter to this person, in terms of what they already value?\"",

    "Find What Makes Them Tick" to
        "Personalizing the emotional connection is everything. One person is motivated by fear of loss — they need to understand what not acting will cost them. Another is driven by identity: this is who I am, or who I want to be, and this action is consistent with that. A third is moved by belonging and connection — they will do something for a relationship or community that they would never do for an abstract benefit. Tapping into fear of irrelevance activates the amygdala's threat response, which can be powerful but also paralyzing if overdone. Connecting to aspiration and identity activates the dopamine system's approach motivation — a forward pull rather than a push. The goal is not to manufacture emotion, but to genuinely understand what is already there and make the connection visible.",

    "The Power of a Small Yes" to
        "Once there is emotional permission, momentum matters. Freedman and Fraser (1966) demonstrated the foot-in-the-door technique: people who agreed to a small request were far more likely to agree to a much larger one later. The explanation lies in consistency — once we act in a certain way, the brain updates its self-image accordingly (\"I am someone who does this\"), and future behavior aligns with that image. Robert Cialdini's research on commitment and consistency (1984) showed the same: small public commitments function as identity anchors. The mechanism is also partly behavioral: acting on something, even once, builds a neural pathway. The action is no longer purely hypothetical — it exists in memory as something you have done.",

    "Intention, Momentum, and Dopamine" to
        "Peter Gollwitzer's work on implementation intentions (1999) shows that specifying when and where you will do something dramatically increases follow-through — not because of willpower, but because the brain forms a situational cue. The dopamine system plays a direct role: dopamine is not only released at reward but also at the anticipation of reward (Schultz, 1997). Each small completed action triggers a mini dopamine hit, which reinforces the behavior and makes the next step slightly easier. This is why action chains work: once someone has said yes to three small things in a row, the fourth yes feels natural — not because they've been tricked, but because their internal reward system is already engaged. The Zeigarnik effect (1927) also contributes: the brain preferentially keeps unfinished tasks in working memory, which means starting something creates its own forward pull.",

    "A Note on Manipulation" to
        "Understanding this is not the same as exploiting it. There is a meaningful difference between connecting someone to their own genuine values and manufacturing false urgency or exploiting insecurity. The most durable motivation — the kind that sustains effort past the initial burst — is built on real alignment between action and identity. Pressure, fear-mongering, or false flattery may create short-term compliance, but they erode trust and often produce reactance (Brehm, 1966): the instinctive push-back when someone feels their autonomy is threatened. The most effective motivation, whether in leadership, parenting, or self-change, looks less like persuasion and more like helping someone rediscover what they already care about."
)

@Composable
fun MotivationScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MotivationBg)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Motivation",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        motivationSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MotivationHeading,
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
