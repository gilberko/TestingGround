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

    "The Candy Jar" to "Wansink, Painter, and Lee (2006), published in the International Journal of Obesity, studied 40 adult secretaries over four weeks using a simple 2×2 design: candy dish on the desk versus two meters away, and a clear bowl versus an opaque bowl.\n\nThe results were consistent: visible candy led to eating roughly 2.2 more pieces per day compared to opaque. Desk proximity led to eating roughly 1.8 more pieces per day compared to the farther bowl. The combination of both — visible and nearby — was most powerful.\n\nCritically, participants consistently underestimated how much they had eaten from the nearby dish and overestimated from the farther one. The visible candy did not trigger conscious craving. It kept \"eating candy\" in active attention continuously — a persistent low-level prime — and the behavior followed without deliberate choice.\n\nNote: the candy jar is an instance of environmental/contextual priming — not the availability heuristic. The visible stimulus keeps a concept active in working memory; behavior follows from that cognitive readiness, not from a judgment about how common or important snacking is. The relationship between priming and the availability heuristic is examined in a section below.",

    "How It Works — Spreading Activation" to "The mind stores knowledge as a vast network of connected concepts. When a concept is activated — by seeing it, hearing it, or thinking about something related — activation spreads outward along associative links to related concepts, temporarily making them more accessible.\n\n\"Bread\" primes \"butter.\" \"Doctor\" primes \"nurse.\" \"Red car\" primes every other red car on the road. This is why, after buying a new car, you suddenly notice that model everywhere — not because there are more of them, but because the concept is active in your network and every matching instance now reaches your attention rather than fading into the background.\n\nPrimed concepts are faster to retrieve, easier to process, and more likely to come to mind unprompted — which means they are more likely to influence the next thought, judgment, or decision.",

    "Brand Nodes and Spreading Activation" to "Every time a brand appears alongside a specific feeling, sensation, or idea — celebration, freedom, speed, warmth — the two nodes are activated together. Over many repetitions this creates a strong associative link. Later, when you feel that sensation independently (you hear a familiar song, smell something nostalgic, feel excited at a sports event) the brand node fires too, even though the brand was never present.\n\nThis is applied spreading activation, and it is the engine behind almost all emotional advertising. Collins and Loftus (1975) formalised the model: activation spreads outward from a source node along associative links, with intensity decaying with distance. The shorter and stronger the link, the more reliably the brand gets recalled.\n\nClassic examples: Coca-Cola has spent a century pairing its logo with Christmas, family meals, and happiness — seeing any of those scenes activates Coke. Red Bull pairs itself with extreme sports and risk-taking — feeling an adrenaline rush primes Red Bull. Apple consistently frames products alongside creativity and self-expression — the feeling of wanting to make something surfaces the brand.\n\nThe implication: what a brand is near is at least as important as what the brand says. Repeated co-presence is the investment; spreading activation is the return.",

    "The Availability Heuristic" to "Tversky and Kahneman introduced the availability heuristic in their 1973 paper \"Availability: A Heuristic for Judging Frequency and Probability,\" published in Cognitive Psychology. The principle: we judge how common, probable, or important something is based on how easily examples of it come to mind. Cognitive accessibility substitutes for statistical analysis. If examples surface quickly and vividly, the event feels frequent or significant. If they are hard to retrieve, the event feels rare or unimportant.\n\nClassic demonstrations: in the weeks following heavily covered plane crashes, people consistently overestimate the frequency of aviation fatalities relative to far more common causes of death such as car accidents or heart disease. After a friend goes through a divorce, personal estimates of divorce rates rise. After a health scare, health-related threats feel more prevalent and pressing. None of these judgments are based on data. They are based on which examples are easiest to recall at the moment the judgment is made.\n\nThe availability heuristic is distinct from priming, though the two are closely related. Priming is a mechanism: exposure to a stimulus makes related concepts more cognitively accessible — faster to retrieve, easier to process, more likely to surface unprompted. The availability heuristic is a decision rule: we use that accessibility as a proxy for frequency or importance. Priming produces availability; the availability heuristic consumes it.",

    "Priming and Availability — How One Triggers the Other" to "The Candy Jar (Wansink et al., 2006) is a priming story, not an availability heuristic story. The visible bowl keeps \"eating candy\" continuously active in working memory through environmental/contextual priming. Participants did not eat more candy because they judged snacking to be frequent or important. They ate more because the concept was never far from mind, and behavior followed from cognitive readiness — not from a deliberate assessment of how common a behavior is.\n\nBut priming and the availability heuristic feed each other in a chain with real consequences.\n\nPriming creates availability. When a stimulus — an advertisement, a news story, an object in the room — primes a concept, that concept becomes cognitively available: it surfaces faster and more readily when relevant. The availability heuristic then takes over: if this comes to mind so easily, it must be important or prevalent. A brand with heavy advertising is not just primed in the moment — it registers as more trustworthy, more established, and more worth buying, because the ease with which it surfaces is mistaken for evidence of quality or popularity. This is why advertising investment pays even when no single impression is consciously attended to: each exposure builds cognitive availability, and availability is read as significance.\n\nAvailability can also prime. A vivid memory that surfaces easily makes associated concepts more cognitively accessible going forward. Someone who readily recalls a health scare will find health-related thoughts primed more readily in future encounters. The available memory acts as a persistent environmental prime.\n\nThe marketing cascade: advertising primes the brand → the brand becomes cognitively available → the availability heuristic reads \"surfaces so easily, must be trustworthy and popular\" → bandwagon effect reinforces this → purchase. Each mechanism uses the output of the previous one as its input.",

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
            text = "Priming, Availability and Spreading Activation",
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
