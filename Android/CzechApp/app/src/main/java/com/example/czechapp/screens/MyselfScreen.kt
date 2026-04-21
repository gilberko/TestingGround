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
private fun TableCard(title: String, rows: List<Triple<String, String, String>>) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = CardDefaults.cardElevation(2.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(6.dp))
            rows.forEach { (label, czech, example) ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                    Text(label,   style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.2f))
                    Text(czech,   style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1f))
                    Text(example, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, fontStyle = FontStyle.Italic, modifier = Modifier.weight(2f))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyselfScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My, Myself, You, Yourself") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item { SectionHeader("Possessive Pronouns") }
            item {
                TableCard("Nominative forms (who owns something)", listOf(
                    Triple("my (m/n)",   "můj / mé/moje",  "můj pes (my dog)"),
                    Triple("my (f)",     "moje / má",      "moje matka (my mother)"),
                    Triple("your (m/n)", "tvůj / tvé/tvoje","tvůj dům (your house)"),
                    Triple("your (f)",   "tvoje / tvá",    "tvoje sestra (your sister)"),
                    Triple("his",        "jeho",           "jeho auto (his car)"),
                    Triple("her",        "její",           "její kniha (her book)"),
                    Triple("our (m/n)",  "náš / naše",     "náš tým (our team)"),
                    Triple("our (f)",    "naše",           "naše škola (our school)"),
                    Triple("your pl. (m/n)", "váš / vaše","váš projekt (your project)"),
                    Triple("their",      "jejich",         "jejich dům (their house)")
                ))
            }
            item { SectionHeader("Reflexive Pronoun: se / si") }
            item {
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("se — reflexive accusative (oneself as direct object)", style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.height(4.dp))
                        listOf(
                            "Myjí se." to "They wash themselves.",
                            "Oblékám se." to "I'm getting dressed. (dressing myself)",
                            "Cítím se dobře." to "I feel well."
                        ).forEach { (cz, en) ->
                            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                                Text("• $cz", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
                                Text(en, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, fontStyle = FontStyle.Italic, modifier = Modifier.weight(1.5f))
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text("si — reflexive dative (for oneself / to oneself)", style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.height(4.dp))
                        listOf(
                            "Koupím si to." to "I'll buy it (for myself).",
                            "Pomysli si." to "Think to yourself.",
                            "Sedni si." to "Sit down (to yourself, i.e. take a seat)."
                        ).forEach { (cz, en) ->
                            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                                Text("• $cz", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
                                Text(en, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, fontStyle = FontStyle.Italic, modifier = Modifier.weight(1.5f))
                            }
                        }
                    }
                }
            }
            item { SectionHeader("Reflexive Possessive: svůj") }
            item {
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Svůj refers back to the subject of the clause (one's own). It replaces můj, tvůj, jeho, její, náš, váš, jejich when the possessor IS the subject.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        listOf(
                            "Miluji svou matku." to "I love my (own) mother.",
                            "Přinesl svou knihu." to "He brought his (own) book.",
                            "Děláme svou práci." to "We do our (own) work.",
                            "Každý dělá svou věc." to "Everyone does their own thing."
                        ).forEach { (cz, en) ->
                            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                                Text("• $cz", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
                                Text(en, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, fontStyle = FontStyle.Italic, modifier = Modifier.weight(1.5f))
                            }
                        }
                    }
                }
            }
        }
    }
}
