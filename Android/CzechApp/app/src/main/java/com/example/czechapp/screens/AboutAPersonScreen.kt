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
private fun ExampleCard(title: String, rows: List<Pair<String, String>>) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = CardDefaults.cardElevation(2.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(6.dp))
            rows.forEach { (cz, en) ->
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
fun AboutAPersonScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("About A Person & Actions") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item { SectionHeader("Describing a Person") }
            item {
                ExampleCard("Physical appearance", listOf(
                    "Je vysoký/vysoká." to "He/She is tall.",
                    "Je malý/malá." to "He/She is short.",
                    "Má hnědé vlasy." to "He/She has brown hair.",
                    "Má modré oči." to "He/She has blue eyes.",
                    "Je mladý/mladá." to "He/She is young.",
                    "Je starý/stará." to "He/She is old."
                ))
            }
            item {
                ExampleCard("Personality & character", listOf(
                    "Je hodný/hodná." to "He/She is kind/nice.",
                    "Je chytrý/chytrá." to "He/She is smart.",
                    "Je laskavý/laskavá." to "He/She is caring/gentle.",
                    "Je vtipný/vtipná." to "He/She is funny.",
                    "Je pracovitý/pracovitá." to "He/She is hardworking.",
                    "Je spolehlivý/spolehlivá." to "He/She is reliable."
                ))
            }
            item { SectionHeader("Talking About Actions") }
            item {
                ExampleCard("What someone is doing", listOf(
                    "Co děláš?" to "What are you doing?",
                    "Pracuji." to "I am working.",
                    "Studuji češtinu." to "I am studying Czech.",
                    "Odpočívám." to "I am resting.",
                    "Pomáhám příteli." to "I am helping a friend."
                ))
            }
            item {
                ExampleCard("Saying what someone likes / dislikes", listOf(
                    "Mám rád/ráda hudbu." to "I like music. (m/f)",
                    "Nemám rád/ráda sport." to "I don't like sport. (m/f)",
                    "Líbí se mi to." to "I like it. (lit. It pleases me.)",
                    "Nelíbí se mi to." to "I don't like it.",
                    "Baví mě čtení." to "I enjoy reading. (lit. Reading entertains me.)"
                ))
            }
            item { SectionHeader("Relative Clauses — Who/Which/That") }
            item {
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "The Czech relative pronoun který/která/které (who, which, that) agrees with the noun it refers to in gender and number, but takes the case required by its role in the clause.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        listOf(
                            "muž, který mluví česky" to "the man who speaks Czech",
                            "žena, která zpívá" to "the woman who is singing",
                            "město, které znám" to "the city that I know",
                            "přátelé, se kterými chodím" to "friends with whom I go out",
                            "dům, ve kterém bydlím" to "the house in which I live"
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
