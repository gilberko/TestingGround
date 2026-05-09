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

private val NtScreenGray = Color(0xFF2D2D2D)
private val NtHeadingColor = Color(0xFFDDDDDD)

private val sections = listOf(
    "What Are Neurotransmitters" to "Neurotransmitters are chemical messengers that neurons use to communicate across the synapse — the tiny gap between one neuron and the next. They are not the same as hormones, though people often conflate them. Hormones are released into the bloodstream and travel far to reach distant targets. Neurotransmitters are released locally into synaptic clefts and act on the receiving neuron directly across that gap. The distinction matters: neurotransmitters work fast and precisely; hormones are slower and broader.",

    "How They Work" to "When a neuron fires an action potential — an electrical signal traveling down the axon — it reaches the axon terminal, where neurotransmitter molecules are stored in small packages called vesicles. The action potential triggers those vesicles to fuse with the cell membrane and release their contents into the synaptic cleft. The neurotransmitter molecules drift across the gap and bind to receptors on the receiving neuron. Depending on the receptor type, this either excites that neuron (making it more likely to fire) or inhibits it (making it less likely to fire). After binding, the neurotransmitter is either reabsorbed back into the sending neuron (reuptake) or broken down by enzymes in the synapse. This clears the signal and resets the system for the next one.",

    "Common Neurotransmitters" to "Dopamine — involved in reward, anticipation, motivation, and movement. More about wanting than pleasure itself.\n\nSerotonin — contributes to mood regulation, a sense of wellbeing, appetite, and sleep. Often described as the \"background contentment\" chemical.\n\nNorepinephrine (noradrenaline) — drives alertness, arousal, and the stress response. Works closely with adrenaline.\n\nGABA (gamma-aminobutyric acid) — the main inhibitory neurotransmitter. It quiets neural activity. Deficient GABA signalling is associated with anxiety.\n\nGlutamate — the main excitatory neurotransmitter. It drives neural firing throughout the brain and is critical for learning and memory.\n\nAcetylcholine — involved in muscle activation, memory, and attention. Also the primary neurotransmitter of the parasympathetic nervous system.\n\nOxytocin — often called the bonding or trust chemical. Released during physical contact, social connection, and intimacy.\n\nEndorphins — the brain's internal painkillers and mood elevators. Released during exercise, laughter, and pain.\n\nHistamine — plays a role in the sleep-wake cycle and alertness; also part of immune and inflammatory responses.",

    "Where They Are Generated" to "Different neurotransmitters are produced in distinct regions of the brain. Dopamine is primarily synthesized in the substantia nigra (for the motor pathway) and the ventral tegmental area or VTA (for the reward and motivation pathways). Serotonin is produced almost entirely in the raphe nuclei, a cluster of nuclei running along the brainstem midline. Norepinephrine originates mainly in the locus coeruleus, a small region in the pons. Oxytocin is produced in the hypothalamus and released by the pituitary gland. GABA and glutamate are not confined to any single region — they are widely distributed throughout the brain and are the dominant currency of general neural communication. Acetylcholine has multiple sources including the basal forebrain (for memory and attention) and motor neurons.",

    "Their Emotional Effects" to "Dopamine drives the feeling of wanting, anticipation, and motivation — its absence creates apathy and anhedonia. Serotonin provides a background sense of contentment and emotional stability; low serotonin correlates strongly with depression and anxiety. Norepinephrine fuels vigilance and arousal — too little and you feel sluggish; too much and anxiety spikes. GABA is calming — it's what tells the brain \"the threat has passed, quiet down.\" Glutamate is the gas pedal for neural activity — it's involved in fear memory formation and anxiety when overactive. Oxytocin promotes trust, bonding, and a sense of social safety. Endorphins produce euphoria and buffer physical and emotional pain. The interaction of all these systems, not any single one in isolation, produces the complex texture of emotional experience.",

    "Medicines That Increase Them" to "SSRIs (selective serotonin reuptake inhibitors) — drugs like fluoxetine (Prozac) and sertraline (Zoloft) block the reuptake transporter for serotonin, meaning serotonin stays in the synapse longer and has more opportunity to bind to receptors. This gradually increases effective serotonergic signalling.\n\nSNRIs (serotonin-norepinephrine reuptake inhibitors) — drugs like venlafaxine do the same for both serotonin and norepinephrine simultaneously.\n\nMAOIs (monoamine oxidase inhibitors) — older antidepressants that prevent the enzyme MAO from breaking down dopamine, serotonin, and norepinephrine, allowing levels to accumulate.\n\nL-DOPA — a dopamine precursor used in Parkinson's disease treatment. The brain converts it to dopamine, partially compensating for the loss of dopaminergic neurons.\n\nStimulants like Adderall (amphetamine) and Ritalin (methylphenidate) increase dopamine and norepinephrine by triggering release and blocking reuptake.",

    "Medicines That Decrease or Interfere" to "Antipsychotics — most work by blocking dopamine D2 receptors, reducing the signal from whatever dopamine is present. This is effective for psychosis (where dopaminergic systems may be overactive) but can cause movement side effects similar to Parkinson's symptoms, because the motor dopamine pathway is also dampened.\n\nBeta-blockers — block norepinephrine receptors (specifically beta-adrenergic receptors), reducing heart rate and the physical symptoms of anxiety and stress. They don't cross the blood-brain barrier well in typical doses, so their effect is more peripheral than central.\n\nBenzodiazepines — drugs like diazepam (Valium) and alprazolam (Xanax) enhance the effect of GABA at GABA-A receptors. They don't increase GABA itself; they make the GABA that is present more effective, powerfully quieting neural activity. This produces sedation, anxiety relief, and muscle relaxation."
)

@Composable
fun NeurotransmittersScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NtScreenGray)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Neurotransmitters",
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
                color = NtHeadingColor,
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
