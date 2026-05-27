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

private val ViolenceBg = Color(0xFF2D2D2D)
private val ViolenceHeading = Color(0xFFDDDDDD)

private val violenceSections = listOf(
    "The Need to Have an Effect" to
        "The brain is wired to seek causal impact — to act and see the world respond. Robert White (1959, Psychological Review) called this effectance motivation: an intrinsic drive, independent of hunger or fear, to interact with the environment and produce effects. It is not a personality trait; it is a feature of how the brain is organized.\n\nThe neuroscience fills in the mechanism. Wolfram Schultz's work (1997) on dopamine neurons showed that dopamine fires not only when a reward arrives but when an outcome matches a prediction. The brain that predicts \"I'll fix the door and it will be fixed\" gets a small chemical reward when it turns out to be right. Action-with-result feels good — not because the result was pleasurable in an obvious sense, but because the brain's predictive system was confirmed. Babies as young as a few months old show persistent, preferential engagement with toys they can control over toys that merely move. The drive to do things, to leave marks, to make things happen, is not incidental — it is foundational.",

    "Brakes and the Tension They Create" to
        "The prefrontal cortex (PFC) is the brain's primary inhibitory regulator. It suppresses impulses arising from the amygdala and the broader limbic system, modulating emotional reactions against anticipated consequences and internalized rules. This is not a flaw in design — it is what allows coordinated social life.\n\nBut the content the PFC works with is learned. Social learning theory (Bandura, 1977) established that inhibitions are acquired through observation and consequence: the fear is not of the action itself but of how the world will respond — the punishment, the loss of standing, the guilt that follows. When a drive is strong and the brakes are also strong, the result is tension. This tension is physiologically real: elevated cortisol, restless cognition, a sense of being simultaneously pulled forward and held back.\n\nExample: a person furious at a colleague, imaging the confrontation, replaying the offense — the drive intensifies. The PFC fires back: consider the consequences, recall the norms, anticipate the shame. The drive and the brake escalate together. The tension becomes its own experience — pressurized and uncomfortable — and relief from it becomes a goal.",

    "Anger as the Accelerant" to
        "Anger evolved as an action-mobilization state — not an accident but a functional design. John Dollard, Neal Miller, and colleagues (1939) proposed the frustration-aggression hypothesis: frustration, defined as the blocking of a goal, generates aggressive impulse. Leonard Berkowitz refined this in 1989 (Psychological Bulletin), arguing that frustration produces anger, and anger produces what he called \"action readiness\" — a physiological state aimed outward at removing the obstacle.\n\nThe critical addition is that anger degrades the brakes. Amy Arnsten's research (2009) on stress and the PFC showed that strong emotional arousal releases norepinephrine and dopamine at levels that impair PFC regulatory function — the very system that holds impulse in check. The result: the brakes weaken at precisely the moment the drive intensifies. It is a convergence, not a coincidence.\n\nExample: road rage. A driver is cut off — a goal is blocked (reaching a destination, maintaining safe space). Frustration triggers anger. Anger activates action readiness and begins degrading PFC oversight. The urge to respond aggressively rises as the capacity to evaluate consequences falls. A person who, five minutes earlier, would have called violent road behavior \"insane\" is now the person performing it.",

    "The Forbidden Fruit Effect" to
        "When people are told they cannot or must not do something, wanting it increases. Jack Brehm formalized this as reactance theory (1966, A Theory of Psychological Reactance): when a perceived freedom is threatened, a motivational state arises to restore it. The most direct restoration is doing the forbidden thing.\n\nViolence is not merely prohibited — it is the most prohibited category of action in nearly every social system simultaneously: law, morality, religion, and cultural norms converge to declare it off-limits. This means the psychological magnet of the forbidden applies at maximum force. When anger and drive build and the accumulated weight of social prohibition is the primary brake — \"you must not do this,\" \"this is what a bad person does\" — the very force of that prohibition can add pull. Transgression becomes a statement of autonomy as much as an act of aggression.\n\nResearch supports this. Studies of adolescent aggression find rates disproportionately high during the developmental period when authority is most constraining and reactance is most acute (Gardner & Steinberg, 2005, Developmental Psychology). The taboo, meant to deter, functions partly as an advertisement. Doing the most forbidden thing is doing the most complete version of the thing the self wanted to do. It is the grand prize precisely because it is the greatest violation of what the norms demanded.",

    "Stories That Unlock the Cage" to
        "Albert Bandura (1999, Personality and Social Psychology Review) described moral disengagement: the cognitive mechanisms by which people neutralize moral prohibitions they otherwise accept. The mechanisms include moral justification (\"I'm teaching them a lesson they earned\"), diffusion of responsibility (\"I was provoked beyond reason\"), dehumanization (\"they forfeited normal consideration\"), and euphemistic labeling (\"it's discipline, not violence\"). None of these are experienced as excuses — they are experienced as accurate descriptions of the situation.\n\nRevenge is among the most culturally pervasive of these narrative licenses. Kevin Carlsmith and colleagues (2002, Journal of Personality and Social Psychology) found that people have strong retributive intuitions — that wrongdoers deserve to suffer in proportion to the harm they caused. The revenge narrative does not feel like a rationalization; it feels like a moral truth. It converts violence from transgression into correction, from the worst thing into the right thing.\n\nRole misidentification creates similar permission without obvious revenge framing. The parent who believes he is \"disciplining,\" the manager who believes she is \"establishing authority,\" the man who believes he is \"defending honor\" — each has a narrative in which the violence is classified as role-consistent duty rather than impulse. Stanley Milgram's obedience experiments (1963) demonstrated this with unusual clarity: a role frame (\"you are the experimenter, this is a legitimate scientific procedure\") was sufficient to lead ordinary people to administer what they believed were dangerous electric shocks to a stranger. The role did not just permit the behavior — it redefined it.",

    "Violence as a Power Statement" to
        "Richard Nisbett and Dov Cohen (1996, Culture of Honor) studied American men from regions historically dependent on cattle herding, where portable wealth could be stolen and law enforcement was sparse. These men showed significantly more physiological arousal and aggressive behavior in response to insults than men from farming regions — cultures where a reputation for willingness to use disproportionate violence had historically been a survival mechanism. The violence was not irrational; it was communicative. It said: I am a person who responds. I am not someone to take from.\n\nThis framework reveals what violence can provide that is not available through other means: the feeling of being a force. Roy Baumeister (1997, Evil: Inside Human Violence and Cruelty) identified \"threatened egotism\" — high but unstable self-regard combined with perceived insult or disrespect — as one of the strongest empirical predictors of violence. The violence does not come from low self-esteem but from a self-image that feels under attack and responds with escalation. The person does not feel like a monster; they feel like someone who finally acted.\n\nFor someone who feels powerless, invisible, ignored, or disrespected across time, the experience of being feared — even briefly — can function as a temporary resolution to a chronic psychological threat. To be taken seriously through fear is still to be taken seriously. The security is not real. But in the moment, it can feel more available than any other path.",

    "Watching Creates Wanting" to
        "Witnessing violence — in person, in film, in news, in memory — produces physiological arousal. This arousal is not inert. Dolf Zillmann's excitation transfer theory (1971) demonstrated that residual arousal from one stimulus can transfer to amplify the emotional response to a later, unrelated stimulus. A person whose heart rate has risen watching something violent carries that arousal forward — it intensifies the next emotional state, whatever it is. If anger is nearby, it becomes more intense. If the pull toward action was present, it becomes stronger.\n\nCraig Anderson and Brad Bushman's meta-analysis of media violence research (2002, Psychological Science in the Public Interest), covering hundreds of studies, found consistent evidence that exposure to violent media increases aggressive thoughts, arousal, and behavior in the short term. The mechanism is partly priming: encountering violent content activates a mental schema in which violence is more cognitively available — easier to generate as a response option, faster to retrieve as a reference frame. It does not force action. But when the moment of decision arrives, violent responses are more easily produced.\n\nThis helps explain not just individual exposure effects but the appeal of violent entertainment generally. It provides vicarious experience of the action the drive wants but the brakes prevent. It is a partial release — not the real thing, but something. For some, that is enough. For others, it is appetite.",

    "The Neuroscience of Aggression" to
        "The neural architecture of aggression is ancient. The key structures — the amygdala (threat detection and emotional tagging), the hypothalamus (initiating the fight response), and the periaqueductal gray, or PAG (executing defensive and offensive aggressive behavior) — predate the cortex by hundreds of millions of years. They are not features of human psychology specifically; they are features of vertebrate survival machinery.\n\nTestosterone does not directly cause violence, but it increases sensitivity to perceived disrespect and lowers the threshold for dominance responses (Archer, 2006, Neuroscience and Biobehavioral Reviews). Cortisol and testosterone interact: low cortisol combined with high testosterone is associated with dominance-seeking and reduced fear of consequences, a profile found at elevated rates in chronically aggressive individuals. The MAOA gene, when present in a low-activity variant, is associated with impulsive aggression — but only in individuals with a history of childhood maltreatment (Caspi et al., 2002, Science). Biology interacts with experience; it does not replace it.\n\nIn animal models, stimulation of specific hypothalamic and amygdala circuits produces aggressive behavior that animals will work to obtain — they press levers to trigger it. The behavior has the signature of reward-seeking. The human parallel is imperfect but not absent: the resolution of built-up frustration through explosive action, combined with a sense of power and the temporary silencing of the tension, may draw on the same dopamine pathways that make other completed drives feel satisfying. The biology does not make violence inevitable. But it makes it comprehensible — not as the failure of humanity but as the expression of circuitry that was once, in a different world, very useful.",

    "When the Act Recalibrates the Belief" to
        "The imagined taboo is partly a prediction: doing this thing will feel catastrophic, others will react with horror, the guilt will be unbearable. These predictions are the substance of the inhibition. When the act actually happens, reality arrives and replaces the imagination — and reality is often a different number than the prediction.\n\nLeon Festinger's cognitive dissonance theory (1957, A Theory of Cognitive Dissonance) describes one mechanism: when behavior conflicts with a prior belief, the belief is what adjusts. The person who has crossed a line does not stand there thinking \"I just became someone terrible.\" The mind searches for a story in which the act was understandable, justified, or not as serious as the prior belief had classified it. Daryl Bem's self-perception theory (1967, Psychological Review) adds a complementary account: we infer our own attitudes from our behavior. Having done something, we revise what kind of person we are and what that kind of person considers acceptable.\n\nThe more precise mechanism is inhibitory learning — the process by which fear associations are updated when predicted consequences fail to materialize. Edna Foa and Michael Kozak (1986, Psychological Bulletin) showed that when a feared outcome is predicted and then does not arrive at the expected intensity, the brain's stored threat value for that outcome is revised downward. The taboo had been held at imaginary intensity — an intensity the imagination had no mechanism to correct, because it was never tested. The first act is the test. When the predicted collapse of the world doesn't arrive on schedule, the brain files an updated estimate: the taboo was overcalibrated. The next threshold is lower — not because the person has become more reckless, but because the prediction system has been updated with data.\n\nExample: a person who has never struck anyone imagines it as a catastrophic and clearly bounded act — a line that, once crossed, cannot be uncrossed. The first time, if the anticipated social devastation doesn't materialize, if the other person doesn't immediately call the police, if the guilt doesn't arrive at the imagined pitch — the taboo deflates. The imagination had inflated it. Reality corrects it downward, and the correction is stored.",

    "The Group Avalanche" to
        "In a group, this recalibration happens collectively and almost instantaneously. Ralph Turner and Lewis Killian's emergent norm theory (1987) describes how, in high-arousal or ambiguous group situations, the first clear action by any member generates information about what is acceptable. The group observes the action, observes that no catastrophe follows — no one stops it, no authority intervenes, no member breaks away in horror — and adjusts. That adjustment happens in every member simultaneously, and the adjusted norm becomes the new baseline.\n\nThis is compounded by pluralistic ignorance. Each member may privately feel that the escalation is going further than it should — but they look around and see no one protesting, no one flinching, no one leaving. The conclusion is not \"we are all spiraling.\" The conclusion is \"I must be the one who is overreacting. Everyone else seems fine with this.\" Hubert O'Gorman (1975) and Deborah Prentice and Dale Miller (1993, Journal of Personality and Social Psychology) documented this: individuals consistently misread the group's apparent comfort as evidence that the group's actual comfort is higher than their own, and adjust toward the perceived norm.\n\nDeindividuation compounds it further. Philip Zimbardo (1969; expanded in The Lucifer Effect, 2007) showed that in group contexts, individual self-monitoring — the internal process that regulates behavior when a person is identifiable and accountable — partially dissolves. The person stops asking \"what would I normally do?\" and starts reading the room. And the room, itself subject to the same dynamic, is reading them back. Diffusion of responsibility (Latané and Darley, 1968) closes the loop: no single person carries the full moral weight. The more participants, the less any individual feels accountable. \"I'm not the one who started it. I'm not the worst one here. Everyone is doing it.\"\n\nThe result is an accelerating loop. One member escalates slightly. The group's non-panicked reaction is registered by everyone as permission. The next escalation is a small move from the last — not a large move from the original starting point — so it doesn't feel extreme. The group adjusts again. Each step is local; the total distance traveled is invisible because no one is measuring from the beginning. The micro-environment of the group becomes the entire reference frame for what is normal, and it is calibrating against itself — a closed system, entirely decoupled from the norms of the world outside it.\n\nJames Waller (2002, Becoming Evil) documented this across perpetrators of mass atrocities: incremental commitment, group social proof, deindividuation, and diffusion of responsibility reliably convert ordinary people into participants in extreme violence without requiring exceptional cruelty at any individual step. Abu Ghraib escalated from small humiliations that went unchecked, each tolerated step resetting the reference point. In Rwanda's 1994 genocide, communities in which neighbors had coexisted for decades participated in mass killing within days, enabled by broadcast norm statements and the real-time social proof that everyone around them was doing it. The avalanche did not require each person to be a monster. It required the absence of a brake at any step, and a group calibrating against itself."
)

@Composable
fun ViolenceScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ViolenceBg)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Violence",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        violenceSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = ViolenceHeading,
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
