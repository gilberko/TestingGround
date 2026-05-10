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

private val VagusScreenGray = Color(0xFF2D2D2D)
private val VagusHeadingColor = Color(0xFFDDDDDD)

private val vagusSections = listOf(
    "What Is the Vagus Nerve" to "The vagus nerve is the tenth cranial nerve (CN X) and the longest, most widely distributed nerve in the body. It originates in the brainstem (the medulla oblongata) and travels down through the neck, chest, and abdomen, branching to the heart, lungs, oesophagus, stomach, liver, kidneys, and most of the gut. \"Vagus\" is Latin for wandering — an accurate description of a nerve that reaches almost everywhere.\n\nIt is the primary nerve of the parasympathetic nervous system, and approximately 80% of its fibres carry signals from the body to the brain rather than the other way around.",

    "The Parasympathetic Nervous System" to "The autonomic (automatic) nervous system has two main branches: the sympathetic system (fight or flight) and the parasympathetic system (rest and digest). The sympathetic system mobilises the body for action — faster heart rate, dilated pupils, blood to muscles. The parasympathetic system does the opposite — slower heart rate, deeper digestion, lower blood pressure, reduced arousal.\n\nThe vagus nerve is the main highway of the parasympathetic system. When it is active, the body signals safety. When it is underactive (low vagal tone), the system defaults toward low-grade alertness and reactivity. Parasympathetic is the correct term for this system.",

    "How It Regulates Heart Rate" to "The vagus nerve directly innervates the sinoatrial node — the heart's natural pacemaker. When the vagus fires, it releases acetylcholine, which slows the node and lowers heart rate. This is called vagal braking.\n\nThe strength of this regulation is measured by heart rate variability (HRV) — the natural variation in time between heartbeats. Higher HRV indicates stronger vagal tone (the vagus is actively modulating the heart). Lower HRV indicates the heart is running closer to its default pace with less vagal control, and is associated with anxiety, chronic stress, and cardiovascular risk.",

    "The Mind-Body Loop — Polyvagal Theory" to "Stephen Porges (1994, 2011) proposed Polyvagal Theory: the vagus nerve does not just regulate the heart, it also sends continuous status reports to the brainstem about the state of the body. If heart rate is fast and irregular, the brain interprets this as evidence of threat — even if no threat is consciously identified. If heart rate is slow and regular, the brain interprets this as safety.\n\nThis means you can send safety signals to the brain by changing the body's state — the communication goes both ways. Slowing and steadying the heart through breathing is not a trick; it is genuine physiological information that the brain uses to update its threat assessment.",

    "Breathing and the Vagus Nerve" to "A well-documented phenomenon called respiratory sinus arrhythmia (RSA) explains the inhale-exhale asymmetry: heart rate naturally speeds up slightly during inhalation and slows down during exhalation. This happens because inhaling briefly inhibits vagal output to the heart, while exhaling restores it.\n\nAn extended exhale therefore means a longer period of vagal braking — a longer period of the heart slowing down. The brain registers this as a shift toward safety. Deliberately extending the exhale exploits RSA to intentionally increase vagal tone. This is not vague relaxation advice — it is a specific physiological mechanism with a well-described pathway.",

    "Breathing Techniques" to "The 4-6 pattern (4 seconds inhale, 6 seconds exhale) is a widely recommended starting point. It reliably extends the exhale beyond the inhale without being demanding. The 4-8 pattern (4 in, 8 out) is used for stronger effect. Box breathing (4-4-4-4: inhale, hold, exhale, hold) is used in military and clinical settings.\n\nThe physiological sigh — a double inhale through the nose followed by a long exhale through the mouth — is the fastest single-breath reset. Yackle et al. (2017, Science) identified the specific brainstem neurons responsible. All of these techniques work through the same RSA mechanism: extended exhale → vagal activation → slower heart rate → brainstem safety signal.",

    "Other Ways to Activate the Vagus Nerve" to "Cold water on the face or wrists triggers the dive reflex, which activates the vagus and drops heart rate within seconds. Humming, chanting, and singing stimulate the vagus through the larynx, which it also innervates.\n\nSocial engagement and eye contact activate what Porges calls the social engagement system, which is also vagally mediated — explaining why talking to a calm, trusted person can rapidly reduce physiological arousal. Slow, rhythmic eating activates vagal pathways in the gut. Regular aerobic exercise improves resting HRV over weeks, meaning exercise trains the vagus nerve as well as the cardiovascular system."
)

@Composable
fun VagusNerveScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(VagusScreenGray)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "The Vagus Nerve",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 28.dp)
        )
        vagusSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = VagusHeadingColor,
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
