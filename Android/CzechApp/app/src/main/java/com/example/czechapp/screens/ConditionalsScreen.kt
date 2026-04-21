package com.example.czechapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp

@Composable
private fun SectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(text = title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun ConditionalCard(title: String, formation: String, examples: List<Pair<String, String>>) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = CardDefaults.cardElevation(2.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(4.dp))
            Text(formation, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            examples.forEach { (cz, en) ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                    Text("• $cz", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
                    Text(en, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, fontStyle = FontStyle.Italic, modifier = Modifier.weight(1.5f))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConditionalsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Conditionals") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item { SectionHeader("Present Conditional") }
            item {
                ConditionalCard(
                    "Formation: l-participle + by/bys/by/bychom/byste/by",
                    "The conditional is formed by combining the l-participle with the conditional auxiliary. \"Kdybych\" is the common conjunction meaning \"if I\".",
                    listOf(
                        "Šel bych domů." to "I would go home.",
                        "Co bys chtěl?" to "What would you like?",
                        "On by přišel." to "He would come.",
                        "Koupili bychom to." to "We would buy it.",
                        "Šli byste s námi?" to "Would you go with us?"
                    )
                )
            }
            item {
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Conditional auxiliary forms", style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.height(6.dp))
                        listOf(
                            "já"        to "bych",
                            "ty"        to "bys",
                            "on/ona"    to "by",
                            "my"        to "bychom",
                            "vy"        to "byste",
                            "oni/ony"   to "by"
                        ).forEach { (pronoun, form) ->
                            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                                Text(pronoun, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.weight(1f))
                                Text(form, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(2f))
                            }
                        }
                    }
                }
            }
            item { SectionHeader("Conditional with 'if' (kdybych/když)") }
            item {
                ConditionalCard(
                    "Real conditions (když — if/when)",
                    "When the condition is possible/real, use když + indicative mood.",
                    listOf(
                        "Když budu mít čas, přijdu." to "If I have time, I'll come.",
                        "Když prší, zůstanu doma." to "When it rains, I stay home."
                    )
                )
            }
            item {
                ConditionalCard(
                    "Hypothetical conditions (kdyby — if [hypothetically])",
                    "When the condition is hypothetical or contrary to fact, use kdyby + conditional.",
                    listOf(
                        "Kdybych měl čas, šel bych." to "If I had time, I would go.",
                        "Kdybys byl bohatý, co bys koupil?" to "If you were rich, what would you buy?",
                        "Kdyby pršelo, zůstali bychom doma." to "If it rained, we would stay home."
                    )
                )
            }
            item { SectionHeader("Past Conditional") }
            item {
                ConditionalCard(
                    "Formation: byl/byla/bylo bych + l-participle",
                    "To express what would have happened (contrary to past fact), combine the past tense of být with the conditional auxiliary.",
                    listOf(
                        "Byl bych přišel, kdybych věděl." to "I would have come if I had known.",
                        "Nebylo by se to stalo." to "It would not have happened."
                    )
                )
            }
        }
    }
}
