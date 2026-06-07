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

private val AboutRulesBg = Color(0xFF2D2D2D)
private val AboutRulesHeading = Color(0xFFDDDDDD)

private val aboutRulesSections = listOf(
    "Rules As Limitation" to
        "Rules exist to coordinate society and protect people. But they inherently constrain what we can do, and we feel that tension constantly. We comply because of social pressure, consequences, or genuine agreement — but some part of us resents the limit. The tension doesn't go away; it's just managed.",

    "Why We Root for Rule-Breakers" to
        "In movies we cheer for characters who break the rules. Not because we want people hurt, but because they do something we stop ourselves from doing. The rebel, the anti-hero, the one who bends the system — they act out the suppressed desire for freedom and autonomy that we carry but don't express. We enjoy it precisely because we would not allow ourselves to do it.",

    "Mirror Neurons and Vicarious Experience" to
        "Mirror neurons fire both when we perform an action and when we observe someone else perform it. They may be part of why we feel \"inside\" a character — we don't just watch, we partially experience. When a movie character breaks rules, our mirror neurons may make us feel as if we're doing it too, with none of the real-world consequences. Fiction may be so satisfying partly because it gives us a safe channel for experiences we can't or won't have.",

    "The Tension Is Permanent" to
        "We don't just tolerate rule-breaking in fiction — we need it. It reveals that we want freedom even when we choose structure. The tension between compliance and desire is ongoing, not resolved. Rules contain behavior; they don't eliminate the impulse. That gap between what we do and what part of us wants to do is always there, and it never fully closes."
)

@Composable
fun AboutRulesScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AboutRulesBg)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "About Rules",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        aboutRulesSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = AboutRulesHeading,
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
