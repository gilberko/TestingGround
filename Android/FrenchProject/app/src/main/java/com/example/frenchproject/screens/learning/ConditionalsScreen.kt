package com.example.frenchproject.screens.learning

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frenchproject.ui.theme.FrenchBlue
import com.example.frenchproject.ui.theme.FrenchNavy

private data class ConditionalExample(val french: String, val english: String)
private data class ConditionalSection(
    val title: String,
    val formation: String,
    val examples: List<ConditionalExample>
)

private val conditionalSections = listOf(
    ConditionalSection(
        title = "Type 1 — Real Conditions",
        formation = "si + présent indicatif → futur simple\n\nUsed for real, likely situations: something that will happen if a condition is met.",
        examples = listOf(
            ConditionalExample("Si il pleut, je resterai à la maison.", "If it rains, I will stay home."),
            ConditionalExample("Si tu étudies, tu réussiras.", "If you study, you will succeed."),
            ConditionalExample("Si nous partons tôt, nous arriverons à l'heure.", "If we leave early, we will arrive on time.")
        )
    ),
    ConditionalSection(
        title = "Type 2 — Hypothetical Present",
        formation = "si + imparfait → conditionnel présent\n\nUsed for unlikely or imaginary present/future situations: things that are not currently true.",
        examples = listOf(
            ConditionalExample("Si j'étais riche, j'achèterais une maison.", "If I were rich, I would buy a house."),
            ConditionalExample("Si elle avait le temps, elle voyagerait plus.", "If she had the time, she would travel more."),
            ConditionalExample("Si nous habitions à Paris, nous irions souvent au musée.", "If we lived in Paris, we would often go to the museum.")
        )
    ),
    ConditionalSection(
        title = "Type 3 — Counterfactual Past",
        formation = "si + plus-que-parfait → conditionnel passé\n\nUsed for things that did not happen in the past and their imagined consequences.",
        examples = listOf(
            ConditionalExample("Si j'avais étudié, j'aurais réussi.", "If I had studied, I would have passed."),
            ConditionalExample("Si tu avais appelé, je serais venu.", "If you had called, I would have come."),
            ConditionalExample("Si elle n'avait pas manqué le train, elle serait arrivée à l'heure.", "If she hadn't missed the train, she would have arrived on time.")
        )
    ),
    ConditionalSection(
        title = "Polite Requests",
        formation = "conditionnel présent (no si clause)\n\nThe conditionnel présent is used on its own to soften requests and sound more courteous.",
        examples = listOf(
            ConditionalExample("Je voudrais un café, s'il vous plaît.", "I would like a coffee, please."),
            ConditionalExample("Pourriez-vous m'aider ?", "Could you help me?"),
            ConditionalExample("Auriez-vous un stylo à me prêter ?", "Would you have a pen to lend me?")
        )
    ),
    ConditionalSection(
        title = "Wishes & Regrets",
        formation = "si seulement + imparfait → present wish\nsi seulement + plus-que-parfait → past regret\n\nExpresses longing for something that is not or was not the case.",
        examples = listOf(
            ConditionalExample("Si seulement j'avais plus d'argent !", "If only I had more money!"),
            ConditionalExample("Si seulement elle était là.", "If only she were here."),
            ConditionalExample("Si seulement j'avais su !", "If only I had known!")
        )
    ),
    ConditionalSection(
        title = "Unless / Even If / Provided That",
        formation = "à moins que + subjonctif = unless\nmême si + indicatif = even if\npourvu que + subjonctif = provided that / as long as",
        examples = listOf(
            ConditionalExample("Je viendrai à moins qu'il ne pleuve.", "I will come unless it rains."),
            ConditionalExample("Même si tu insistes, je ne changerai pas d'avis.", "Even if you insist, I won't change my mind."),
            ConditionalExample("Pourvu qu'il fasse beau, on ira à la plage.", "As long as the weather is nice, we'll go to the beach.")
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConditionalsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Conditionals", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = FrenchBlue)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item {
                Text(
                    text = "French conditionals express hypothetical situations, polite requests, wishes, and regrets. The three core types differ in the tense combinations used with si (if).",
                    style = MaterialTheme.typography.bodyMedium,
                    color = FrenchNavy,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            conditionalSections.forEach { section ->
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    ConditionalSectionHeader(section.title)
                    ConditionalRuleCard(section.formation)
                }
                items(section.examples.size) { i ->
                    ConditionalExampleCard(section.examples[i])
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun ConditionalSectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = FrenchBlue,
        modifier = Modifier.padding(bottom = 4.dp)
    )
    HorizontalDivider(color = FrenchBlue.copy(alpha = 0.4f))
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
private fun ConditionalRuleCard(formation: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Text(
            text = formation,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
            color = FrenchNavy,
            lineHeight = 19.sp,
            modifier = Modifier.padding(12.dp)
        )
    }
}

@Composable
private fun ConditionalExampleCard(example: ConditionalExample) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp)
        ) {
            Text(
                text = example.french,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = FrenchBlue,
                style = MaterialTheme.typography.bodySmall,
                lineHeight = 18.sp
            )
            Text(
                text = example.english,
                style = MaterialTheme.typography.bodySmall,
                color = FrenchNavy,
                lineHeight = 18.sp
            )
        }
    }
}
