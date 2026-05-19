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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

private val biases = listOf(
    "Anchoring",
    "Confirmation",
    "Availability Heuristic",
    "Dunning-Kruger",
    "Sunk Cost Fallacy",
    "Framing Effect",
    "Bandwagon Effect",
    "Hindsight Bias",
    "Status Quo Bias",
    "Negativity Bias",
    "Optimism Bias",
    "Attribution Bias",
    "Misattribution - A few effects / biases",
    "Mere Exposure Effect",
    "Riddles and Quizzes - Information Gap / Curiosity Gap",
    "Don't Tell Me What To Do - Reactance / Psychological Reactance",
    "Mirror Neurons",
    "Authority Bias"
)

private val biasContentKeys = mapOf(
    "Anchoring" to "anchoring",
    "Misattribution - A few effects / biases" to "misattribution",
    "Mere Exposure Effect" to "mere_exposure",
    "Riddles and Quizzes - Information Gap / Curiosity Gap" to "information_gap",
    "Don't Tell Me What To Do - Reactance / Psychological Reactance" to "reactance",
    "Mirror Neurons" to "mirror_neurons",
    "Authority Bias" to "authority_bias",
    "Sunk Cost Fallacy" to "sunk_cost_fallacy",
    "Dunning-Kruger" to "dunning_kruger",
    "Negativity Bias" to "negativity_bias",
    "Optimism Bias" to "optimism_bias",
    "Hindsight Bias" to "hindsight_bias",
    "Bandwagon Effect" to "bandwagon_effect"
)

@Composable
fun BiasesSelectionScreen(navController: NavController) {
    HubBackground {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Biases and Effects",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        biases.chunked(2).forEach { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                row.forEach { bias ->
                    Button(
                        onClick = {
                            val key = biasContentKeys[bias]
                            if (key != null) navController.navigate("biases_section/$key")
                            else navController.navigate("topic/$bias")
                        },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue),
                        modifier = Modifier.weight(1f).height(56.dp)
                    ) {
                        Text(
                            text = bias,
                            color = Color.White,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                if (row.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { navController.popBackStack() },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(56.dp)
        ) {
            Text(text = "Back", color = Color.White, fontSize = 18.sp)
        }
    }
    }
}
