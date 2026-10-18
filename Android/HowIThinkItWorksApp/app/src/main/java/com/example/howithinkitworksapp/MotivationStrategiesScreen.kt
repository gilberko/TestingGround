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

private val StrategiesBg = Color(0xFF2D2D2D)
private val StrategiesHeading = Color(0xFFDDDDDD)

private val strategiesSections = listOf(
    "Levers That Move People" to
        "Below are eight levers that reliably push or pull people toward action. All of them are recognized in psychological research — some with strong evidence, some with more modest or mixed evidence, which is noted where relevant. Several of them can easily be used to manipulate rather than to help, so each section also notes where the lever tends to backfire. Some of these levers create a lasting inner pull; others are a push from outside that works fast but fades or breeds resistance.",

    "a. \"I Believe in Who You Can Be\"" to
        "The idea: you don't pretend the person is already where they want to be. You acknowledge the gap — they are not there yet, and they know it. You validate that awareness (\"you see it clearly, and that matters\"), and then you add that you believe in their ability to become who they want to be. This works because it is honest: it doesn't deny the problem, so it doesn't trigger the defensive \"you're just saying that\" reaction, and it places the person on the side of their own ideal rather than their current state.\n\n" +
        "The research: the clearest demonstration is the \"wise feedback\" study by Yeager et al. (2014, Journal of Experimental Psychology: General, 143(2)). Seventh-graders received critical comments on an essay, with a note attached. The key note read: \"I'm giving you these comments because I have very high standards and I know that you can reach them.\" Students who got this note were substantially more likely to revise their essays (roughly twice as likely in one study) and produced better revisions. Importantly, it worked better than praise alone or high standards alone — the combination of honest standards plus belief in the person was what mattered.\n\n" +
        "Supporting ideas: Higgins' self-discrepancy theory (1987) describes the tension between the actual self and the ideal self — the acknowledged gap is itself a motivator. Bandura's self-efficacy theory (1977) lists \"verbal persuasion\" — someone credible telling you that you can — as one of the four sources of belief in your own ability. Motivational Interviewing (Miller & Rollnick) calls this \"developing discrepancy\": helping a person see the gap between where they are and what they value, without lecturing. The older Pygmalion effect (Rosenthal & Jacobson, 1968), where teachers' expectations raised students' performance, points the same way, though the size of that original effect has been debated in later replications.",

    "b. Fear" to
        "The idea: make people fear something — the consequence of not acting, or the danger the action protects against. Fear narrows attention and creates urgency.\n\n" +
        "The research: fear is one of the most studied persuasion tools. Rogers' Protection Motivation Theory (1975) says people act when they perceive a threat as severe, believe they are vulnerable to it, and believe the recommended action will work and that they can do it. Witte's Extended Parallel Process Model (1992) adds that when fear is high but the person sees no effective way out, they switch from controlling the danger to controlling the fear — through denial, avoidance, or dismissing the message. The largest meta-analysis (Tannenbaum et al., 2015, Psychological Bulletin, 127 articles, ~27,000 participants) found that fear appeals do change attitudes, intentions, and behaviors — the effect is modest but consistent — and that they are notably stronger when the message includes an efficacy statement: a clear, doable action. That meta-analysis did not find conditions where fear appeals reliably backfired, though the denial response predicted by Witte is still widely discussed. Kahneman and Tversky's loss aversion (1979) explains part of fear's power: losses loom roughly twice as large as equivalent gains.\n\n" +
        "In practice: fear alone gets attention; fear plus \"and here is exactly what you can do about it\" gets action. Used constantly, fear wears people out and erodes trust in the source.",

    "c. Disgust / \"The Opposite of Me\"" to
        "The idea: you present an alternative and frame it as something the person despises, finds ridiculous, or would never want to be associated with. They then move toward the other option — not because it was argued for, but because the first option threatens how they see themselves.\n\n" +
        "The research: Berger and Heath (2008, Journal of Personality and Social Psychology, 95(3), \"Who Drives Divergence?\") showed that people abandon tastes to avoid signaling an identity they don't want. In a field study, undergraduates stopped wearing a particular wristband once students from the neighboring \"geeky\" academically focused dorm started wearing it. White and Dahl (2006) found the same with \"dissociative reference groups\": men were less likely to choose a steak labeled a \"ladies' cut\". The famous \"truth\" anti-smoking campaign worked partly this way — instead of preaching health, it framed smoking as being manipulated by tobacco companies, something rebellious teenagers would hate to be; it was associated with a measurable drop in youth smoking (Farrelly et al., 2005, American Journal of Public Health).\n\n" +
        "A related lever is the decoy effect (Huber, Payne & Puto, 1982): adding a clearly worse option next to your target option makes the target look more attractive by contrast. The mechanism underneath is identity signaling — every choice says something about who I am, and I will steer away from choices that say the wrong thing.",

    "d. The Rewarding Puzzle — \"I Can Win This\"" to
        "The idea: if someone encounters a puzzle or a game that is rewarding — interesting, pleasant, or stimulating all the senses like a slot machine — and they believe they can solve, advance, or win, they are pulled in. Several separate mechanisms meet here.\n\n" +
        "Early success, but not too easy: if someone solves the first clues of a crossword quickly, they conclude \"I can do this\" — a mastery experience, the strongest source of self-efficacy in Bandura's theory. But it must not be too easy. Atkinson's achievement motivation research (1957) found that people are most motivated by tasks of moderate difficulty: a sure thing gives no sense of achievement, and an impossible task gives no hope. The person wants the feeling of achievement still waiting at the end. Csikszentmihalyi's flow theory (1990) describes the same sweet spot — challenge matched to skill — and one of flow's signatures is losing track of time.\n\n" +
        "The slot machine: everyone knows a slot machine is hard to beat — then you win quickly. Could I be lucky? Langer (1975) described the \"illusion of control\" — people treat chance outcomes as if skill or luck were involved. Slot machines pay on a variable-ratio schedule, the reinforcement pattern that Ferster and Skinner (1957) found produces the most persistent behavior. An early big win is often reported in the histories of problem gamblers (described by Custer in clinical work in the 1980s). Clark et al. (2009, Neuron) showed that near-misses — almost winning — were experienced as unpleasant yet increased the desire to keep playing, and activated the same striatum and insula reward circuitry as real wins. Schüll's \"Addiction by Design\" (2012) documents how machines are engineered to produce \"the zone\", where time disappears.\n\n" +
        "On the verge of a breakthrough: the goal-gradient effect (Hull, 1932; Kivetz, Urminsky & Zheng, 2006) shows that effort speeds up as a goal gets closer — coffee-card customers bought coffee faster as they approached the free one. The Zeigarnik effect (1927) keeps unfinished tasks active in the mind, and Loewenstein's information-gap theory of curiosity (1994) says a small, closable gap in knowledge is especially compelling. When I feel I am only a little away from finishing, my perception says it will take only a short time — so I stay focused, keep going, and lose track of how long it has actually been.",

    "e. \"I Don't Think You Can Do It\"" to
        "The idea: you challenge someone who knows they can do something by telling them you don't think they can. They then set out to prove you wrong.\n\n" +
        "The research: this sits on Brehm's psychological reactance theory (1966) — when people feel their freedom or self-image is being restricted, they are motivated to reassert it. Swann's self-verification theory adds that people act to confirm their existing self-views when those views are challenged: if I see myself as capable, a doubt about me is something I want to correct. MacDonald, Nail and Harper (2011, Social Influence, \"Do people use reverse psychology?\") found that many people report deliberately using this kind of \"strategic self-anticonformity\", and that it depends on concealing the tactic and correctly predicting the other person's contrary reaction.\n\n" +
        "Caveat: the evidence here is more modest and context-dependent than for the other levers. It works mainly when the person already believes in their ability. With someone who is unsure of themselves, doubt can simply confirm their fear: Babad, Inbar and Rosenthal (1982) described a \"Golem effect\", the reverse of Pygmalion, where low expectations lowered performance, and Bandura notes that verbal persuasion works in both directions. So it is a lever for the confident, not for the struggling.",

    "f. Addiction" to
        "The idea: addiction, whether chemical or behavioral, drives a person to repeat an action.\n\n" +
        "The research: Robinson and Berridge's incentive-sensitization theory (1993) shows that addiction changes the dopamine system so that \"wanting\" grows even when \"liking\" does not — the person craves the thing more and more while enjoying it the same or less. Koob and Le Moal (2001) describe the other half: over time the person acts not to feel good but to stop feeling bad (withdrawal, anxiety, emptiness) — negative reinforcement. Behavioral addictions are real: DSM-5 (2013) recognizes gambling disorder as an addiction, with the same reward circuitry involved.\n\n" +
        "A milder, everyday version is the habit. Wood and Neal (2007) show that habits are triggered by context cues rather than goals, and Lally et al. (2010) found that a new daily habit took a median of about 66 days to become automatic. Habits are the healthy way to use this lever: once the action is automatic, motivation is no longer needed every time.",

    "g. My Group Does It" to
        "The idea: if people in my group — or the group I see myself as part of, or wish to be part of — do something, I am likely to do it too.\n\n" +
        "The research: Asch's conformity experiments (1951) showed people giving an obviously wrong answer to match the group. Cialdini (1984) called this social proof: when unsure, we look at what people like us do. Tajfel and Turner's social identity theory (1979) explains why the group matters — part of our self-image is the groups we belong to, so acting like them confirms who we are. In the hotel towel study (Goldstein, Cialdini & Griskevicius, 2008), the message \"most guests who stayed in this room reused their towels\" outperformed the standard environmental message — the more specific and similar the group, the stronger the effect. Schultz et al. (2007) found that telling households how their energy use compared to neighbors changed their consumption. Escalas and Bettman (2003) showed that aspirational groups — the group I want to be part of — shape choices too. Christakis and Fowler (2007) argued that behaviors like weight gain spread through social networks, though their methods have been debated.",

    "h. The Power of a Story" to
        "The idea: a story can tap into a person's belief system. It builds an equivalence between a task and the core values I hold — and so my own conclusion is that I need to do it. Nobody told me to; I deduced it.\n\n" +
        "The research: Green and Brock (2000) showed that people \"transported\" into a story change their beliefs to match it, partly because transportation reduces counter-arguing — you are living the story, not debating it. March and Olsen describe a \"logic of appropriateness\": much of our behavior is driven not by calculating outcomes but by asking \"what does a person like me do in a situation like this?\" — exactly the deduction a good story sets up. Oyserman's identity-based motivation (2007) shows that people act when an action feels congruent with their identity and give up when it feels like \"not for people like me\". Kaplan et al. (2017, Cerebral Cortex, 27(2)) scanned American, Chinese and Iranian participants reading stories in their own languages; stories involving values the reader held as sacred (\"protected values\") activated the brain's default mode network more strongly — a network tied to self-reflection and meaning-making. Claims that stories work by releasing oxytocin (Paul Zak) are popular but have faced replication concerns.\n\n" +
        "Why it is so powerful: the conclusion feels self-generated, so it isn't experienced as pressure, and it is tied to identity, so acting against it would feel like betraying myself.",

    "Combining the Levers" to
        "Some of these levers create a lasting inner pull: belief in who you can be (a), the well-designed challenge (d), the group I identify with (g), and the story tied to my values (h). These connect the action to competence, belonging, and identity — the same needs that Self-Determination Theory (Deci & Ryan) identifies as the foundation of intrinsic motivation. Others are pushes from outside: fear (b), disgust (c), being doubted (e), and addiction (f). They are fast and powerful, but they can fade, wear people down, or trigger reactance (Brehm, 1966) when people sense they are being steered. The most durable motivation usually starts with a push and is carried by a pull."
)

@Composable
fun MotivationStrategiesScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(StrategiesBg)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Strategies",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        strategiesSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = StrategiesHeading,
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
