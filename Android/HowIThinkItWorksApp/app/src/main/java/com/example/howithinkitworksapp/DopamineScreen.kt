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

private val DaScreenGray = Color(0xFF2D2D2D)
private val DaHeadingColor = Color(0xFFDDDDDD)

private val sections = listOf(
    "What Is Dopamine" to "Dopamine is both a neurotransmitter — a chemical that transmits signals between neurons across a synapse — and a neuromodulator, meaning it doesn't just flip a single switch but alters the general tone and responsiveness of large networks of neurons at once. It is produced primarily in two areas: the substantia nigra, which feeds the motor system, and the ventral tegmental area (VTA), which feeds the reward, motivation, and prefrontal systems. Despite its popular reputation as the \"happiness chemical,\" dopamine is better understood as the anticipation and motivation chemical. It is less about the experience of pleasure and more about the drive to pursue it.",

    "Is It About Happiness" to "This distinction matters more than it might seem. Researchers have separated the \"wanting\" system from the \"liking\" system, and dopamine belongs firmly to wanting. You can have high dopamine activity and be in a state of intense craving without being happy at all — in fact, the pursuit itself can feel urgent, even uncomfortable. The actual experience of pleasure (liking) draws more on the opioid system. Dopamine is what makes you reach for the cookie; the opioid system is what makes the first bite feel good. They interact, but they are not the same thing. This is why you can want something desperately and find it disappointing when you get it.",

    "Dopamine And Learning" to "One of dopamine's most important functions is as a learning signal. In reinforcement learning terms, dopamine neurons fire a prediction error signal: they spike when something good happens unexpectedly (positive prediction error) and they dip below baseline when something good was expected but didn't happen (negative prediction error). When the reward was fully predicted, dopamine doesn't spike at the reward — it spikes earlier, at the cue that reliably preceded the reward. The brain is constantly updating its model of the world based on these surprise signals. This is the neural mechanism behind why unexpected rewards feel exciting and why expected ones feel flat: your dopamine system has already adjusted its expectations.",

    "Dopamine And Habit Formation" to "When a behavior reliably leads to a reward, dopamine reinforces the neural pathway that produced the behavior. Over time, the association between a trigger, an action, and a reward gets stamped in — this is the habit loop. Dopamine makes behaviors \"sticky\" by rewarding them, literally strengthening the synaptic connections involved. Once a habit is formed, dopamine fires at the trigger (the cue), not just the reward, which is part of why habits are so hard to break: the cue alone is now enough to drive the craving, even when you consciously don't want to follow through.",

    "Dopamine And Movement" to "The nigrostriatal dopamine pathway — from the substantia nigra to the striatum — is the motor control pathway. It fine-tunes and initiates voluntary movement. When this pathway is intact, movement is smooth and initiated easily. This pathway has nothing to do with reward or mood; its role is purely motor. But because it shares the same neurotransmitter as the reward system, anything that globally depletes dopamine will affect both.",

    "Dopamine And Parkinson's" to "Parkinson's disease is caused by the progressive death of dopaminergic neurons in the substantia nigra. As this pathway degrades, the motor signal breaks down: voluntary movements become slow to initiate (bradykinesia), muscles develop a resting tremor (the characteristic Parkinson's tremor), and tone increases causing rigidity. The disease progresses as more neurons die. L-DOPA (levodopa) is the primary treatment — it is a dopamine precursor that crosses the blood-brain barrier and is converted to dopamine in the brain, partially restoring the depleted signal. It doesn't stop the degeneration, but it compensates for the loss while neurons remain.",

    "Triggers: Sugar, Drugs, Stimuli" to "Many things trigger dopamine release, and the size of the spike correlates with how unexpected and concentrated the reward is. Sugar triggers a sharp dopamine release — evolutionarily useful when calorie-dense food was rare, less useful now that it's everywhere. Exercise produces a sustained, moderate dopamine elevation. Music, especially when it gives you chills, produces a dopamine spike at the moments of anticipation and emotional peak. Novelty alone triggers dopamine — the brain rewards exploration. Drugs of abuse hijack this system at much higher magnitudes: cocaine blocks dopamine reuptake so it accumulates massively in the synapse; amphetamine both blocks reuptake and reverses the transporter to actively pump dopamine out. These artificial spikes are far larger than anything natural experiences produce, which is part of why they are addictive and why ordinary pleasures feel flat to people in active addiction.",

    "Can Thought Alone Trigger Dopamine" to "Yes. Imagining a reward, recalling a pleasurable memory, or anticipating a future event all produce dopamine release. The brain does not fully distinguish between vividly imagined experiences and real ones at the level of these circuits. This is why visualizing a goal can motivate action, why daydreaming about food can make you hungry, and why anticipating a vacation produces almost as much dopamine as being on one. It's also the neural basis for why planning and looking forward to things matters for mood — you are literally dosing your reward system with low-level dopamine by constructing positive anticipations.",

    "The Stress-Craving Cycle" to "Stress activates the HPA axis and floods the brain with cortisol. One of cortisol's effects is to sensitize the dopamine reward system — making it more reactive to cues associated with quick relief or reward. At the same time, stress depletes the prefrontal cortex's capacity to regulate impulse. The result is a strong pull toward whatever has reliably produced dopamine in the past. For someone whose go-to is a cold sugary drink, the sequence is: stress → craving → dopamine spike from the drink → relief. The brain records this: stress predicts drink predicts relief. The association strengthens with each repetition. Over time, stress itself becomes a conditioned cue that triggers the craving automatically, before any conscious deliberation occurs. This is a well-documented cycle in both addiction research and behavioral psychology.",

    "Dopamine And OCD Rituals" to "In OCD, an intrusive thought or situation triggers intense anxiety. Performing a compulsion (checking, counting, washing) temporarily reduces that distress. The relief is not purely dopamine — it likely involves GABA, serotonin, and the calming of the threat-detection system — but the relief is nonetheless rewarding, and dopamine reinforces the behavior that produced it. This is negative reinforcement in the neurological sense: the behavior is strengthened because it removes something aversive. Over time, the trigger-compulsion-relief loop becomes a habit at the neural level — dopamine fires at the trigger, driving the urge to perform the compulsion, even when the person consciously knows the compulsion is irrational. This is why willpower alone is rarely effective against OCD: the loop is operating below the deliberate control level, exactly as any other habit does."
)

@Composable
fun DopamineScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DaScreenGray)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Dopamine",
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
                color = DaHeadingColor,
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
