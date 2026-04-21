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
private fun RuleCard(title: String, body: String, examples: List<Pair<String, String>>) {
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
fun NegationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Negation") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item { SectionHeader("Verb Negation") }
            item {
                RuleCard(
                    "Prefix ne- on the verb",
                    "To negate a verb, simply add the prefix ne- directly before it (no space).",
                    listOf(
                        "Mluvím česky." to "I speak Czech.",
                        "Nemluvím česky." to "I don't speak Czech.",
                        "Rozumím." to "I understand.",
                        "Nerozumím." to "I don't understand.",
                        "Vím." to "I know.",
                        "Nevím." to "I don't know."
                    )
                )
            }
            item { SectionHeader("Double Negation") }
            item {
                RuleCard(
                    "Czech requires double (or multiple) negation",
                    "Unlike English, Czech uses double negation — both the verb and any negative pronouns/adverbs carry the negative marker. This is grammatically correct in Czech.",
                    listOf(
                        "Nevidím nikoho." to "I don't see anyone. (lit. I don't see nobody.)",
                        "Nic nevím." to "I don't know anything. (lit. I know nothing.)",
                        "Nikdy tam nejdu." to "I never go there. (lit. I never don't go there.)",
                        "Nikam nejdu." to "I'm not going anywhere. (lit. I'm going nowhere.)"
                    )
                )
            }
            item { SectionHeader("Negative Pronouns & Adverbs") }
            item {
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        listOf(
                            Triple("nobody",    "někdo → nikdo",   "Nikdo nepřišel. (Nobody came.)"),
                            Triple("nothing",   "něco → nic",      "Nic se nestalo. (Nothing happened.)"),
                            Triple("never",     "někdy → nikdy",   "Nikdy nespím. (I never sleep.)"),
                            Triple("nowhere",   "někam → nikam",   "Nikam nejdu. (I'm not going anywhere.)"),
                            Triple("nowhere (location)", "někde → nikde", "Nikde nejsem. (I'm nowhere.)"),
                            Triple("no one's",  "něčí → ničí",     "Ničí chyba. (Nobody's fault.)")
                        ).forEach { (eng, czPair, example) ->
                            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp)) {
                                Text(eng, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.2f))
                                Text(czPair, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
                                Text(example, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.weight(2f))
                            }
                        }
                    }
                }
            }
            item { SectionHeader("Negation with Genitive") }
            item {
                RuleCard(
                    "Genitive case after negation",
                    "When a negated verb takes a direct object, Czech often shifts the object from accusative to genitive case.",
                    listOf(
                        "Mám čas. → Nemám čas(u)." to "(I have time. → I don't have time.)",
                        "Piju kávu. → Nepiju kávu." to "(I drink coffee. → I don't drink coffee.)",
                        "Vidím ho. → Nevidím ho." to "(I see him. → I don't see him.) — pronouns don't shift"
                    )
                )
            }
        }
    }
}
