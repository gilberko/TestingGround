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
private fun PhraseCard(title: String, rows: List<Pair<String, String>>) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = CardDefaults.cardElevation(2.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(6.dp))
            rows.forEach { (cz, en) ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                    Text(cz, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
                    Text(en, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, fontStyle = FontStyle.Italic, modifier = Modifier.weight(1.5f))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiscScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Misc") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item { SectionHeader("Common Greetings & Farewells") }
            item {
                PhraseCard("Greetings", listOf(
                    "Dobrý den." to "Good day. (formal)",
                    "Dobrý ráno." to "Good morning.",
                    "Dobrý večer." to "Good evening.",
                    "Ahoj / Čau." to "Hi / Bye. (informal)",
                    "Nashledanou." to "Goodbye. (formal)",
                    "Na shledanou." to "Goodbye.",
                    "Dobrou noc." to "Good night."
                ))
            }
            item { SectionHeader("Polite Phrases") }
            item {
                PhraseCard("Courtesy expressions", listOf(
                    "Prosím." to "Please / Here you are / You're welcome.",
                    "Děkuji / Díky." to "Thank you / Thanks.",
                    "Promiňte." to "Excuse me / I'm sorry. (formal)",
                    "Promiň." to "Sorry. (informal)",
                    "Není zač." to "Don't mention it.",
                    "Dobrou chuť!" to "Enjoy your meal!",
                    "Na zdraví!" to "Cheers! (toast)",
                    "Hodně štěstí!" to "Good luck!",
                    "Vše nejlepší!" to "All the best!",
                    "Svolení?" to "May I? / Permission?"
                ))
            }
            item { SectionHeader("Questions") }
            item {
                PhraseCard("Question words", listOf(
                    "Kdo?" to "Who?",
                    "Co?" to "What?",
                    "Kde?" to "Where? (location)",
                    "Kam?" to "Where? (direction/to where)",
                    "Kdy?" to "When?",
                    "Proč?" to "Why?",
                    "Jak?" to "How?",
                    "Kolik?" to "How many / How much?",
                    "Který / Která / Které?" to "Which?",
                    "Čí?" to "Whose?"
                ))
            }
            item { SectionHeader("Conjunctions & Connectors") }
            item {
                PhraseCard("Linking words", listOf(
                    "a" to "and",
                    "ale" to "but",
                    "nebo" to "or",
                    "protože" to "because",
                    "že" to "that (conjunction)",
                    "když" to "when / if",
                    "pokud" to "if / as long as",
                    "i když" to "even though / even if",
                    "proto" to "therefore",
                    "takže" to "so / therefore",
                    "nicméně" to "nevertheless",
                    "přesto" to "despite / still",
                    "zatímco" to "while / whereas"
                ))
            }
            item { SectionHeader("Essential Survival Phrases") }
            item {
                PhraseCard("Key phrases", listOf(
                    "Nerozumím." to "I don't understand.",
                    "Nemluvím dobře česky." to "I don't speak Czech well.",
                    "Mluvíte anglicky?" to "Do you speak English?",
                    "Jak se řekne ... česky?" to "How do you say ... in Czech?",
                    "Zopakujte to, prosím." to "Please repeat that.",
                    "Mluvte pomaleji, prosím." to "Please speak more slowly.",
                    "Kde je ...?" to "Where is ...?",
                    "Kolik to stojí?" to "How much does it cost?",
                    "Potřebuji pomoc." to "I need help.",
                    "Zavolejte záchranku!" to "Call an ambulance!"
                ))
            }
        }
    }
}
