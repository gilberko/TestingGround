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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
private fun SectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(text = title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun CompCard(title: String, body: String, examples: List<Pair<String, String>>) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = CardDefaults.cardElevation(2.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(4.dp))
            Text(body, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(6.dp))
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
fun ComparisonsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Comparisons") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item { SectionHeader("Comparative (-er / more)") }
            item {
                CompCard(
                    "Add -ší / -ejší / -čí to the adjective stem (or use irregular forms)",
                    "The comparative is formed by modifying the adjective stem. Use 'než' (than) to compare two things.",
                    listOf(
                        "větší (bigger) — velký → větší" to "Toto auto je větší než tamto. (This car is bigger than that one.)",
                        "menší (smaller) — malý → menší" to "On je menší než já. (He is smaller than me.)",
                        "rychlejší (faster) — rychlý → rychlejší" to "Vlak je rychlejší než autobus. (The train is faster than the bus.)",
                        "starší (older) — starý → starší" to "Sestra je starší než já. (My sister is older than me.)",
                        "lepší (better) — dobrý → lepší" to "Toto je lepší. (This is better.)",
                        "horší (worse) — špatný → horší" to "Počasí je horší než včera. (The weather is worse than yesterday.)"
                    )
                )
            }
            item { SectionHeader("Superlative (the most / -est)") }
            item {
                CompCard(
                    "Add nej- prefix to the comparative form",
                    "The superlative is formed by adding 'nej-' to the comparative. It means 'the most / the -est'.",
                    listOf(
                        "největší (biggest)" to "To je největší město v zemi. (It's the biggest city in the country.)",
                        "nejmenší (smallest)" to "Má nejmenší dům na ulici. (He has the smallest house on the street.)",
                        "nejrychlejší (fastest)" to "On je nejrychlejší v týmu. (He is the fastest in the team.)",
                        "nejlepší (best)" to "To je nejlepší jídlo! (This is the best food!)",
                        "nejhorší (worst)" to "To byl nejhorší den. (That was the worst day.)"
                    )
                )
            }
            item { SectionHeader("Equality & Inequality") }
            item {
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        listOf(
                            Triple("as ... as", "stejně ... jako", "Je stejně vysoký jako ty. (He is as tall as you.)"),
                            Triple("not as ... as", "není tak ... jako", "Není tak rychlý jako ona. (He's not as fast as she is.)"),
                            Triple("more ... than", "více/víc ... než", "Je to více problematické. (It is more problematic.)"),
                            Triple("less ... than", "méně/míň ... než", "Je méně zkušený. (He is less experienced.)"),
                            Triple("the more ... the more", "čím ... tím", "Čím víc učíš, tím víc umíš. (The more you study, the more you know.)")
                        ).forEach { (en, cz, example) ->
                            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp)) {
                                Text(en,      style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.3f))
                                Text(cz,      style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1.3f))
                                Text(example, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, fontStyle = FontStyle.Italic, modifier = Modifier.weight(2f))
                            }
                        }
                    }
                }
            }
        }
    }
}
