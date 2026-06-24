package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class MoreTenseTopic(
    val name: String,
    val subtitle: String,
    val whenToUse: List<String>,
    val examples: List<Pair<String, String>>,
    val note: String? = null
)

private val moreTenseTopics = listOf(
    MoreTenseTopic(
        name = "Pretérito Perfeito Composto",
        subtitle = "Present Perfect Compound — repeated or ongoing action up to now",
        whenToUse = listOf(
            "Formed with: ter (presente do indicativo) + past participle",
            "Actions or states that started in the past and continue, or repeat, up to the present",
            "NOT used for a single completed action — that's Pretérito Perfeito Simples",
            "Common with time markers like \"últimamente\", \"este ano\", \"sempre\""
        ),
        examples = listOf(
            "Tenho estudado muito últimamente." to "I have been studying a lot lately.",
            "O que é que tens feito?" to "What have you been up to?",
            "Tem sido difícil encontrar trabalho." to "It has been difficult to find work.",
            "Temos viajado bastante este ano." to "We have traveled quite a bit this year."
        ),
        note = "Don't confuse with the simple past: \"Já fiz o trabalho\" (I already did the work — one completed action) vs \"Tenho feito muito trabalho\" (I've been doing a lot of work — repeated/ongoing). This repeated/ongoing-only restriction is one of the biggest differences between European Portuguese and the English/Spanish present perfect."
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreTensesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("More Tenses") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { Spacer(modifier = Modifier.height(4.dp)) }

            moreTenseTopics.forEach { entry ->
                item(key = entry.name) { MoreTenseCard(entry) }
            }

            item { GerundioCard() }
            item { VozPassivaCard() }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun MoreTenseCard(entry: MoreTenseTopic) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = entry.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Text(
                text = entry.subtitle,
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "When to use:",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            entry.whenToUse.forEach { bullet ->
                Text(
                    text = "• $bullet",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 4.dp, bottom = 2.dp)
                )
            }
            if (entry.note != null) {
                Text(
                    text = "Note: ${entry.note}",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 2.dp, bottom = 4.dp)
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
            Text(
                text = "Examples:",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            entry.examples.forEach { example ->
                Column {
                    Text(
                        text = example.first,
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic
                    )
                    Text(
                        text = example.second,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(start = 12.dp, bottom = 3.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun GerundioCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "Gerúndio",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Text(
                text = "Gerund — a verb form, not a tense, but studied alongside the tenses",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Formation:",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            listOf(
                "-ar → -ando" to "falar → falando",
                "-er → -endo" to "comer → comendo",
                "-ir → -indo" to "partir → partindo"
            ).forEach { rule ->
                Text(
                    text = "${rule.first}  (${rule.second})",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 4.dp, bottom = 2.dp)
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
            Text(
                text = "When to use in European Portuguese:",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            listOf(
                "Manner — describes how an action is done",
                "Simultaneity — two actions happening at once",
                "Cause / conclusion",
                "After verbs like continuar, ficar, andar, in some literary or fixed constructions",
                "NOT used for the present continuous in spoken EP — see contrast with Brazilian Portuguese below"
            ).forEach { bullet ->
                Text(
                    text = "• $bullet",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 4.dp, bottom = 2.dp)
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
            Text(
                text = "Examples:",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            listOf(
                "Ela respondeu, sorrindo." to "She answered, smiling. (manner)",
                "Caminhando pela rua, vi um amigo." to "Walking down the street, I saw a friend. (simultaneity)",
                "Sendo assim, vamos continuar." to "Being that the case, let's continue. (cause/conclusion)"
            ).forEach { example ->
                Column {
                    Text(
                        text = example.first,
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic
                    )
                    Text(
                        text = example.second,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(start = 12.dp, bottom = 3.dp)
                    )
                }
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
            Text(
                text = "Brazilian Portuguese vs European Portuguese:",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "In Brazilian Portuguese, the gerund is the everyday way to express the present continuous: \"Estou falando\" (I am speaking). European Portuguese does NOT use the gerund this way — it uses estar + a + infinitive instead. This is one of the most noticeable grammatical differences between the two varieties.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Column {
                Text(
                    text = "Estou a trabalhar. (European Portuguese)",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = "Estou trabalhando. (Brazilian Portuguese)",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
                Text(
                    text = "Both mean: I am working.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
        }
    }
}

@Composable
private fun VozPassivaCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "Voz Passiva",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Text(
                text = "Passive Voice — formed with ser or estar + past participle",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Auxiliary verbs:",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            listOf(
                "ser (any tense) + past participle — the action/process itself; agent introduced with por (\"by\")",
                "estar (any tense) + past participle — the resulting state; normally no agent"
            ).forEach { bullet ->
                Text(
                    text = "• $bullet",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 4.dp, bottom = 2.dp)
                )
            }
            Text(
                text = "Note: Past participles must agree in gender/number with the subject (fechada — fem., escrito — masc.). English distinguishes action vs. state through tense; Portuguese distinguishes it through the choice of auxiliary verb.",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 2.dp, bottom = 4.dp)
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
            Text(
                text = "Examples — ser (the action):",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            listOf(
                "O livro foi escrito por ela." to "The book was written by her.",
                "A porta foi fechada pelo segurança." to "The door was closed by the security guard.",
                "Os documentos são revistos todos os meses." to "The documents are reviewed every month."
            ).forEach { example ->
                Column {
                    Text(
                        text = example.first,
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic
                    )
                    Text(
                        text = example.second,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(start = 12.dp, bottom = 3.dp)
                    )
                }
            }
            Text(
                text = "Examples — estar (the resulting state):",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
            )
            Column {
                Text(
                    text = "A porta está fechada.",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = "The door is closed. (just the current state — compare with \"foi fechada\" above)",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 12.dp, bottom = 3.dp)
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
            Text(
                text = "A third option: ficar + past participle",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "ficar + past participle expresses the resulting state after a change or transformation — more dynamic than estar, since it implies \"became\" rather than just \"is\".",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Column {
                Text(
                    text = "O homem ficou ferido no acidente.",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = "The man was injured in the accident. (ficar — emphasizes the change of state)",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 12.dp, bottom = 3.dp)
                )
                Text(
                    text = "O homem está ferido.",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = "The man is injured. (estar — just states the current condition, no change implied)",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 12.dp, bottom = 3.dp)
                )
            }
        }
    }
}
