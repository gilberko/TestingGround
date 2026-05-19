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

private val DoPeopleBg = Color(0xFF2D2D2D)
private val DoPeopleHeading = Color(0xFFDDDDDD)

private val doPeopleListenSections = listOf(
    "Confirmation Bias — We Hear What We Want to Hear" to
        "Humans don't process information neutrally. We actively seek out, favor, remember, and give disproportionate weight to information that confirms what we already believe — and we do this without noticing. The research name for this is confirmation bias, and it is one of the most robustly replicated findings in cognitive psychology.\n\nPeter Wason's selection task (1960) was among the first systematic demonstrations. He showed that people almost universally look for confirming evidence when testing a rule, and fail to look for the disconfirming evidence that would actually tell them whether the rule was correct. Raymond Nickerson's 1998 review in Review of General Psychology surveyed decades of research and concluded that confirmation bias is the most pervasive and potentially damaging of all the known biases, operating in science, law, medicine, politics, and everyday reasoning.\n\nIn a 1979 study by Lord, Ross, and Lepper, people with opposing views on capital punishment were shown the same two research studies — one supporting deterrence, one opposing it. Rather than updating their beliefs in the direction of the evidence, participants on both sides ended up more extreme than when they started. They scrutinized the study that disagreed with them, found methodological problems with it (real or imagined), and accepted the supporting study uncritically. The same evidence made both sides more convinced they were right.",

    "Disconfirmation — Why Opposing Evidence Gets Dismissed" to
        "When you encounter information that contradicts your belief, two things happen — and neither of them is objective analysis.\n\nFirst, you engage in what Kunda (1990) called motivated reasoning: the goal of the reasoning process is not to reach the truth, but to reach the conclusion you already want. You examine the evidence selectively, hold it to a different standard, and generate arguments against it with more energy than you would for supporting evidence. The process feels like thinking; it is mostly rationalizing.\n\nSecond, the disconfirmation is easier than you'd expect to dismiss: \"That study is flawed,\" \"That source is biased,\" \"That's an exceptional case.\" Because most evidence is imperfect and most sources are imperfect, the brain can nearly always find a reason to discount something it doesn't want to believe. Edwards and Smith (1996) confirmed this experimentally — participants rated the methodology of studies as weaker when the conclusion contradicted their prior beliefs, and stronger when it supported them, regardless of actual methodological quality.\n\nThe result is that exposure to opposing evidence often has no effect, or even a backfire effect — it triggers defensiveness rather than updating.",

    "Cognitive Dissonance — When Reality Challenges a Belief" to
        "The psychological term for the discomfort experienced when two things you believe are in conflict — or when evidence contradicts something you hold to be true — is cognitive dissonance. Leon Festinger introduced the concept in his 1957 book A Theory of Cognitive Dissonance.\n\nFestinger's core insight was that this discomfort is a motivational state, not just an uncomfortable feeling — it drives behavior aimed at reducing the inconsistency. And crucially, the reduction is not usually achieved by updating the belief to match reality. It is achieved by one of three cheaper routes: changing the smaller cognition, adding new supporting cognitions that make the conflict seem less severe, or simply reinterpreting the contradictory information so that it no longer contradicts.\n\nAbandoning a significant belief is expensive. It means admitting a mistake, reconsidering everything built on that belief, and potentially disrupting your identity, social relationships, and sense of self. Constructing a narrative that makes the conflict go away is far cheaper — so the brain does that instead. The belief survives. The uncomfortable evidence gets explained away.",

    "The Doomsday Cult — Festinger's Famous Case" to
        "The most striking demonstration of cognitive dissonance in action came from an event Festinger himself studied in real time. In 1954, a woman named Dorothy Martin — writing under the name \"Marian Keech\" in Festinger's account — led a group of followers who believed, based on messages she claimed to receive from a higher civilization, that a great flood would destroy the world on December 21, 1954. Followers quit their jobs, left their families, and gave away their possessions in preparation.\n\nWhen midnight passed without incident, the prediction had failed completely and publicly. The rational response would have been to question the belief. What happened instead was documented in Festinger, Riecken, and Schachter's 1956 book When Prophecy Fails: within hours, Martin received a new message — God had been so moved by the group's faith that He had decided to spare the Earth. The failure of the prophecy was reframed as the group's greatest victory.\n\nMore striking still: the members, who had previously kept to themselves and avoided publicity, immediately began proselytizing — phoning newspapers, seeking converts, trying to grow the movement. A belief that had been privately held and unverifiable became something they now needed to spread. Festinger's interpretation: when external reality provides no support, believers seek social validation instead. The more people who share the belief, the more real it feels. The prophecy failed; the commitment deepened.",

    "Why the Brain Does This — The Path of Least Resistance" to
        "The brain is not poorly designed — it is efficiently designed for an environment where changing a belief had significant costs. When you change a belief, you change everything downstream of it: the decisions you made based on it, the people you aligned with because of it, the identity you built around it.\n\nIn most cases, the new evidence is uncertain, the social cost of changing is concrete, and the psychological cost of admitting you were wrong is immediate. The calculation that the brain runs — implicitly, automatically, without your awareness — almost always favors the existing belief. The evidence gets explained away, the source gets discredited, or a new piece of reasoning arrives that makes the contradiction disappear.\n\nThis is not unique to cults or fringe beliefs. It operates in investing (\"the stock will come back\"), medicine (\"I feel fine, I don't need to get checked\"), politics, relationships, and everyday self-perception. The strength of the bias is roughly proportional to how much is invested in the belief — financially, emotionally, or socially. The more you have built on a belief, the more your brain will work to protect it from contradicting evidence. And it will feel, from the inside, exactly like reasoning."
)

@Composable
fun DoPeopleListenScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DoPeopleBg)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Do People Listen?",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        doPeopleListenSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = DoPeopleHeading,
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
