package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SomeNoneAllScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Some, None, All") },
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
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { SnaCard() }
        }
    }
}

@Composable
private fun SnaCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // ── All / Every / Everything ──────────────────────────────────
            Text("All / Every / Everything", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            SnaEntry(
                term = "tudo",
                description = "Everything — invariable, refers to abstract totality",
                examples = listOf(
                    "Tudo está bem." to "Everything is fine.",
                    "Fiz tudo." to "I did everything.",
                    "Tudo ou nada." to "All or nothing."
                )
            )
            SnaEntry(
                term = "todos os / todas as",
                description = "All (the) / every — masculine plural / feminine plural, used with countable nouns",
                examples = listOf(
                    "Todos os dias." to "Every day.",
                    "Todas as pessoas foram." to "All the people went.",
                    "Comi todos os bolos." to "I ate all the cakes."
                )
            )
            SnaEntry(
                term = "todo o / toda a",
                description = "The whole / all of the — singular, refers to the entirety of one thing",
                examples = listOf(
                    "Passei todo o dia em casa." to "I spent the whole day at home.",
                    "Toda a equipa estava lá." to "The whole team was there."
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            // ── Some / A few ──────────────────────────────────────────────
            Text("Some / A Few", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            SnaEntry(
                term = "algum / alguma",
                description = "Some, any, a certain — singular, used before a noun",
                examples = listOf(
                    "Tens algum dinheiro?" to "Do you have any money?",
                    "Há algum problema?" to "Is there any problem?",
                    "Alguma coisa aconteceu." to "Something happened."
                )
            )
            SnaEntry(
                term = "alguns / algumas",
                description = "Some, a few — plural, used before countable nouns",
                examples = listOf(
                    "Tenho alguns amigos em Lisboa." to "I have some friends in Lisbon.",
                    "Comprei algumas maçãs." to "I bought some apples.",
                    "Faltam algumas peças." to "A few pieces are missing."
                )
            )
            SnaEntry(
                term = "algo",
                description = "Something — invariable, slightly more formal or abstract than alguma coisa",
                examples = listOf(
                    "Queres algo?" to "Do you want something?",
                    "Há algo errado." to "There's something wrong.",
                    "Disseste algo importante." to "You said something important."
                ),
                note = "algo and alguma coisa are largely interchangeable. alguma coisa is more colloquial."
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            // ── None / Not any ────────────────────────────────────────────
            Text("None / Not Any", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            SnaEntry(
                term = "nenhum / nenhuma",
                description = "None, no, not any — used with a singular noun. Portuguese uses double negation: não … nenhum.",
                examples = listOf(
                    "Não tenho nenhum problema." to "I have no problem.",
                    "Nenhuma das respostas está certa." to "None of the answers is correct.",
                    "Não há nenhuma razão para isso." to "There is no reason for that."
                ),
                note = "nenhuns / nenhumas (plural) exists but is rare — the singular is strongly preferred."
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            // ── Someone / No-one / Anyone ─────────────────────────────────
            Text("Someone / No-one / Anyone", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            SnaEntry(
                term = "alguém",
                description = "Someone, somebody — also anyone in questions and negatives",
                examples = listOf(
                    "Alguém telefonou." to "Someone called.",
                    "Há alguém em casa?" to "Is anyone home?",
                    "Não vi alguém assim." to "I've never seen anyone like that."
                )
            )
            SnaEntry(
                term = "ninguém",
                description = "No-one, nobody — always used with a negative verb (double negation)",
                examples = listOf(
                    "Não vi ninguém." to "I didn't see anyone. / I saw no-one.",
                    "Ninguém sabe." to "Nobody knows.",
                    "Não há ninguém aqui." to "There's no-one here."
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            // ── Something / Nothing / Anything ───────────────────────────
            Text("Something / Nothing / Anything", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            SnaEntry(
                term = "algo / alguma coisa",
                description = "Something — also anything in questions",
                examples = listOf(
                    "Queres alguma coisa?" to "Do you want something / anything?",
                    "Disseste algo?" to "Did you say something?",
                    "Encontrei alguma coisa estranha." to "I found something strange."
                )
            )
            SnaEntry(
                term = "nada",
                description = "Nothing — also anything with a negative verb (double negation)",
                examples = listOf(
                    "Não há nada." to "There's nothing.",
                    "Não quero nada." to "I don't want anything.",
                    "Nada aconteceu." to "Nothing happened."
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            // ── Somewhere / Nowhere / Anywhere ───────────────────────────
            Text("Somewhere / Nowhere / Anywhere", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            SnaEntry(
                term = "algures / em algum sítio",
                description = "Somewhere",
                examples = listOf(
                    "Deixei as chaves algures." to "I left my keys somewhere.",
                    "Vimos isso em algum sítio." to "We've seen this somewhere."
                )
            )
            SnaEntry(
                term = "em lado nenhum / em nenhum sítio",
                description = "Nowhere — used with negative verb",
                examples = listOf(
                    "Não o encontro em lado nenhum." to "I can't find it anywhere / nowhere.",
                    "Não vou a lado nenhum hoje." to "I'm not going anywhere today."
                )
            )
            SnaEntry(
                term = "em qualquer sítio / em todo o lado",
                description = "Anywhere / everywhere",
                examples = listOf(
                    "Podes encontrá-lo em qualquer sítio." to "You can find it anywhere.",
                    "Há turistas em todo o lado." to "There are tourists everywhere."
                )
            )
        }
    }
}

@Composable
private fun SnaEntry(
    term: String,
    description: String,
    examples: List<Pair<String, String>>,
    note: String? = null
) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(term, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
        Text(description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.height(2.dp))
        examples.forEach { (pt, en) ->
            Text(
                "\u2022 $pt",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic
            )
            Text(
                "  $en",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        if (note != null) {
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                "Note: $note",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}
