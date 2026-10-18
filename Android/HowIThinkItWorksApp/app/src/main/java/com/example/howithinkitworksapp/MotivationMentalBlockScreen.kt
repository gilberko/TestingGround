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

private val MentalBlockBg = Color(0xFF2D2D2D)
private val MentalBlockHeading = Color(0xFFDDDDDD)

private val mentalBlockSections = listOf(
    "Why People Avoid Doing Something" to
        "People sometimes avoid doing something they know they should do. Usually this is not because they don't understand the reasons — it is because they are ambivalent: part of them wants to do it and part of them doesn't. When someone else pushes (\"you really should...\"), the natural reaction is to argue the other side — the part that doesn't want to. Motivational Interviewing (Miller & Rollnick) calls the urge to push and fix the \"righting reflex\", and notes that it tends to strengthen the very resistance it is trying to overcome. The way past a mental block is not to argue harder, but to let the person argue for change themselves — by asking questions that zoom in and out on the subject.",

    "Where the Method Comes From" to
        "The question sequence is real and well known. It comes from Motivational Interviewing (MI), developed by William Miller and Stephen Rollnick (first book 1991, current edition 2013), where it is called the \"readiness ruler\" or \"importance ruler\". Michael Pantalon, a research scientist at the Yale School of Medicine, packaged it into a short six-question script in his book \"Instant Influence\" (2011): why might you change; how ready are you on a scale of 1 to 10; why didn't you pick a lower number; imagine you've changed — what would the positive outcomes be; why are those outcomes important to you; and what's the next step, if any. A similar scaling question is also a core tool of Solution-Focused Brief Therapy (de Shazer and Berg).",

    "a. \"How Willing Are You to Do It, on a Scale of 0-10?\"" to
        "What it does — zoom out: it turns a vague yes/no (\"I don't want to\") into a gradient. Saying a number is much easier than saying yes, and it admits ambivalence without judgment — it is fine not to be at 10. It also gives a baseline you can return to later. Notice the hidden effect: anyone who picks anything above 0 has just admitted that some part of them is willing. That small admission is what the next question builds on.",

    "b. \"You Chose 3 — Why Didn't You Choose 2?\"" to
        "What it does — zoom in on the person's own reasons: this is the counterintuitive core of the method, and Pantalon calls it the question that catches people off guard. The natural question would be \"why only 3, why not higher?\" — but that invites the person to list the reasons against, strengthening the block. Asking \"why not lower?\" forces them to defend the willingness they do have, which means saying out loud their own reasons for doing it. MI calls these statements \"change talk\", and the opposite — reasons for staying the same — \"sustain talk\".\n\n" +
        "Why saying it matters: Bem's self-perception theory (1967) proposes that we infer our own attitudes partly by observing what we say and do — so hearing myself argue for something makes me believe it more. Amrhein et al. (2003, Journal of Consulting and Clinical Psychology) found that the strength of clients' commitment language during an MI session predicted later behavior change. A meta-analysis by Magill et al. (2018, Journal of Consulting and Clinical Psychology) found the clearest signal on the other side: more sustain talk was associated with worse outcomes, while change talk on its own was a weaker, non-significant predictor when both were tested together. In other words, a question that steers the person away from listing their objections is doing useful work in itself.",

    "c. \"Suppose You Did It — What Reasons Could There Have Been?\"" to
        "What it does — lowers the threat and widens the view: the hypothetical framing removes the pressure of commitment. The person isn't promising anything; they're just imagining. Because nothing is at stake, they can come up with reasons freely, including reasons they wouldn't admit to if they felt they were being cornered. MI calls this \"looking forward\" or hypothetical change questions. Pantalon's version adds two steps here: imagine you've done it — what would the positive outcomes be? And then: why are those outcomes important to you? The second question connects the action to the person's core values, which is where lasting motivation lives.\n\n" +
        "A caution from research: Oettingen and Mayer (2002) found that positive fantasies about the future alone can actually reduce effort — the mind enjoys the imagined success as if it had already happened. Oettingen's \"mental contrasting\" (WOOP: Wish, Outcome, Obstacle, Plan) works better: imagine the outcome, then name the main obstacle and how you would handle it. So it helps to follow the hypothetical with \"what would get in the way?\".",

    "d. \"Now, How Willing Would You Be?\"" to
        "What it does — zoom back out and consolidate: after the person has spent several minutes voicing their own reasons for doing it, you ask the scale question again. Very often the number rises, for two reasons. First, they have just heard themselves make the case — self-perception again. Second, consistency: once we have stated reasons in favor, a low number would contradict what we just said, and people are motivated to keep their words and attitudes consistent (Festinger's cognitive dissonance, 1957; Cialdini's commitment and consistency). If the number doesn't rise, that is information rather than failure — it tells you that the real obstacle hasn't been named yet.",

    "More Questions Worth Asking" to
        "\"What would it take to move from 3 to 4?\" — focuses on one small step instead of the whole mountain, and often reveals the concrete obstacle.\n\n" +
        "\"How confident are you that you could do it, 0-10?\" — the confidence ruler. MI separates importance (do I want to?) from confidence (can I?). Someone can be at 9 on importance and 2 on confidence — and then the block is about self-efficacy (Bandura, 1977), not desire, and needs a different approach: smaller steps, past successes, support.\n\n" +
        "\"Why does that outcome matter to you?\" — ties the reasons to core values, which sustain effort far longer than external reasons (Self-Determination Theory, Deci & Ryan).\n\n" +
        "\"What has worked for you before, even a little?\" — a Solution-Focused question that reminds the person of their own past competence instead of their failures.\n\n" +
        "\"What's the next step — if any?\" — Pantalon's final question. The \"if any\" respects autonomy and keeps reactance away. If there is a step, make it concrete: when, where, how. Gollwitzer's research on implementation intentions (1999) shows that \"when X happens, I will do Y\" plans greatly increase follow-through.",

    "Does It Make Sense? The Evidence" to
        "Yes — the logic is sound, and the components are well supported, with some honest limits. Motivational Interviewing as a whole is supported by many randomized trials, especially for alcohol and substance use, and research links the client's own language during the session to later change. Pantalon's brief version was tested in emergency departments as a \"brief negotiation interview\": D'Onofrio et al. (2012, Annals of Emergency Medicine, 60(2)) reported reduced drinking and fewer drink-driving incidents among hazardous drinkers, though some related trials found more modest differences compared with control groups. What has not been tested is the single \"why not lower?\" question on its own — there is no study isolating it as a standalone technique. So the fair summary is: this is a well-grounded, widely used clinical practice built on solid principles (letting people voice their own reasons, avoiding pressure, connecting to values), rather than a proven magic question."
)

@Composable
fun MotivationMentalBlockScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MentalBlockBg)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Disarming a Mental Block",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        mentalBlockSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MentalBlockHeading,
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
