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

private val MbScreenGray = Color(0xFF2D2D2D)
private val MbHeadingColor = Color(0xFFDDDDDD)

private val mbSections = listOf(
    "Something We Always Do, Never Notice" to "Breathing is the one constant of conscious life — it has been happening every few seconds since birth, it will continue until death, and for the vast majority of that time it receives zero attention. The phrase \"I didn't even have time to breathe\" captures this precisely: the sentence treats breathing as an afterthought, something that happens in the background of real events. That invisibility is exactly what makes breathing useful as a meditation anchor.",

    "Automatic But Accessible" to "Most vital functions are entirely beyond voluntary control: you cannot decide your heart rate, your digestion, or your hormone levels. Breathing is different. It runs automatically under normal conditions, but it can be taken over by deliberate control at any moment. This dual nature — automatic and accessible — is unique among bodily processes.\n\nThis is why it appears as an anchor in virtually every contemplative tradition and in modern clinical mindfulness. It is always present (you cannot forget to bring it), it is always changing (each breath is slightly different, giving the attention something to follow), and it is neutral (unlike emotions or thoughts, it does not pull you into content or narrative).",

    "What to Focus On" to "The instruction is not simply to \"think about breathing\" but to actually observe the physical experience of it — the specific sensations at each phase. The feeling of air passing through the nostrils: slightly cool on the way in, warmer on the way out. The slight pause at the top of the inhale before the direction reverses. The movement of the chest or abdomen expanding and contracting. The moment of stillness at the end of the exhale before the body initiates the next breath.\n\nEach of these is a real, happening event — not a thought about breathing, but the actual physical phenomenon, attended to in real time. This distinction matters. Thinking \"I am breathing\" is still thought. Feeling the cool air at the nostrils is sensation.",

    "The Anchor Function" to "When a thought arises and begins to pull you away — as they will — the breath serves as a return point. Rather than trying to fight the thought or feel bad about drifting, you simply notice and return attention to wherever in the breath cycle you currently are. You do not restart the breath, you do not begin again from inhale — you just rejoin it wherever it happens to be.\n\nThis is what meditation teachers mean by an anchor: something fixed and ongoing that you can always return to, regardless of how far you have drifted or how many times. Tang, Ma, and colleagues (2009) found that brief mindfulness training focused on breathing reduced mind-wandering and improved self-regulatory performance compared to relaxation controls — effects attributed to improved attentional monitoring.",

    "Breathing and the Nervous System" to "The breath is also a direct access point to the autonomic nervous system (see the Vagus Nerve screen for the mechanism). Slow, extended exhalation activates parasympathetic tone and lowers cortisol and heart rate within minutes. When meditation practice incorporates this — longer exhales, steady rhythm — the physiological effects compound the attentional ones: the body itself becomes less aroused, which makes the mind easier to observe without being pulled in.\n\nThis is why some traditions pair breath-focused meditation with specific ratios (4 seconds in, 6 seconds out, for example) rather than natural breathing. The ratio is not incidental; it is deliberately chosen to exploit respiratory sinus arrhythmia.",

    "Why It Helps Even When Overwhelmed" to "A common report from people who try meditation during emotional difficulty is that focusing on thoughts feels impossible — the emotions are too loud. The breath provides a way in that bypasses thought entirely. You are not being asked to process the emotion, understand it, or reason with it. You are being asked to notice the feeling of air moving through the nostrils.\n\nThis does not make the emotion disappear. But it gives the nervous system a stabilising signal — the slow, steady breath pattern — and gives the attention a concrete task that is simpler than engaging the feeling directly. From that slightly more stable ground, the emotion often becomes more observable and less overwhelming."
)

@Composable
fun MeditationBreathingScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MbScreenGray)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Breathing",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 28.dp)
        )
        mbSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MbHeadingColor,
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
