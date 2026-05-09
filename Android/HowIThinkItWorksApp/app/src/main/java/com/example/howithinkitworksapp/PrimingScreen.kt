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

private val PrimingScreenGray = Color(0xFF2D2D2D)
private val PrimingHeadingColor = Color(0xFFDDDDDD)

private val primingSections = listOf(
    "What Is Priming" to "Exposure to a stimulus activates related concepts in memory, making them more accessible — they come to mind faster, more readily, and with less effort. This shapes how we perceive subsequent information and quietly nudges our decisions without deliberate thought.\n\nIt is not a vague idea. Priming is one of the most rigorously studied phenomena in cognitive psychology, with decades of peer-reviewed research beginning with Meyer and Schvaneveldt's landmark lexical decision study in 1971, published in the Journal of Experimental Psychology.",

    "The Candy Jar" to "Wansink, Painter, and Lee (2006), published in the International Journal of Obesity, studied 40 adult secretaries over four weeks using a simple 2×2 design: candy dish on the desk versus two meters away, and a clear bowl versus an opaque bowl.\n\nThe results were consistent: visible candy led to eating roughly 2.2 more pieces per day compared to opaque. Desk proximity led to eating roughly 1.8 more pieces per day compared to the farther bowl. The combination of both — visible and nearby — was most powerful.\n\nCritically, participants consistently underestimated how much they had eaten from the nearby dish and overestimated from the farther one. The visible candy did not trigger conscious craving. It kept \"eating candy\" in active attention continuously — a persistent low-level prime — and the behavior followed without deliberate choice.",

    "How It Works — Spreading Activation" to "The mind stores knowledge as a vast network of connected concepts. When a concept is activated — by seeing it, hearing it, or thinking about something related — activation spreads outward along associative links to related concepts, temporarily making them more accessible.\n\n\"Bread\" primes \"butter.\" \"Doctor\" primes \"nurse.\" \"Red car\" primes every other red car on the road. This is why, after buying a new car, you suddenly notice that model everywhere — not because there are more of them, but because the concept is active in your network and every matching instance now reaches your attention rather than fading into the background.\n\nPrimed concepts are faster to retrieve, easier to process, and more likely to come to mind unprompted — which means they are more likely to influence the next thought, judgment, or decision.",

    "Types of Priming" to "Semantic: a word or concept primes related words and concepts through meaning. \"Cat\" speeds recognition of \"dog\" because they share category membership.\n\nPerceptual / Repetition: prior exposure to the specific sensory form of a stimulus makes that exact form easier to recognize later — even days later, and even without conscious memory of the prior exposure. The brain has seen it before and pre-loaded the recognition machinery.\n\nAffective: emotionally positive stimuli prime faster responses to other positive stimuli; negative primes negative. The emotional tone spreads through the network.\n\nEnvironmental / Contextual: ongoing cues in the physical environment — a candy jar, a scent, background music, imagery on a website — keep related concepts continuously active without any single triggering event. The effect is sustained as long as the cue remains.",

    "Priming in Everyday Life and Marketing" to "Retailers place premium products at eye level, priming quality before any price comparison is made. The implied standard of the shelf position does work before you read a label.\n\nBackground music shapes behavior. North, Hargreaves, and McKendrick (1999) found that playing French music in a wine shop led customers to buy more French wine, and German music led to more German wine — even though customers afterward denied that the music had influenced them.\n\nScent priming: lavender in a store environment primes relaxation and has been shown to extend browsing time. The feeling of calm is attributed to the store itself.\n\nLanguage primes solutions. \"War on crime\" activates military concepts and primes enforcement-based responses. \"Crime epidemic\" activates public health concepts and primes social intervention. The same statistics, described with different language, lead to different conclusions because the prime shapes which knowledge is accessible when the conclusion is formed.",

    "The Limits" to "Semantic and perceptual priming are among the most robustly replicated findings in cognitive psychology — more than fifty years of consistent laboratory results. These are reliable.\n\nBehavioral priming — the idea that priming a concept changes overt actions — has a more troubled record. Bargh, Chen, and Burrows (1996) famously showed that unscrambling sentences containing elderly-related words made participants walk more slowly out of the lab. A rigorous replication attempt in 2012 failed to reproduce the result using automated timing, suggesting the original effect may have been influenced by experimenter expectations. Behavioral priming results should be treated with significant caution.\n\nPriming effects also decay. Spreading activation is temporary — concepts return to baseline within minutes without reinforcement. The candy jar is effective precisely because the reinforcement is constant: the bowl is always there, always visible, always keeping the concept active."
)

@Composable
fun PrimingScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimingScreenGray)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Priming",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 28.dp)
        )
        primingSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = PrimingHeadingColor,
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
