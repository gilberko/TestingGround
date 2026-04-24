package com.example.russianapp.screens

import androidx.compose.foundation.background
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

private data class PrepEntry(
    val prep: String,
    val case: String,
    val english: String,
    val russian: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrepositionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Prepositions") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            item { PrepSectionHeader("Movement & Origin") }
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("из", "Genitive", "from (inside a place / country)", "Он из Аргентины — He is from Argentina"),
                        PrepEntry("с", "Genitive", "from (activity / open place)", "Он пришёл с работы — He came from work"),
                        PrepEntry("в", "Accusative", "to (enclosed place)", "Он идёт в офис — He is going to the office"),
                        PrepEntry("на", "Accusative", "to (event / performance)", "Он идёт на шоу — He is going to a show")
                    ),
                    note = "Use в for enclosed spaces (office, house, city) and на for open areas and events (concert, show, work, market). " +
                            "Their opposites follow the same split: из (from inside) vs. с (from a surface/activity)."
                )
            }

            item { PrepSectionHeader("From … Until") }
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("от … до", "Genitive (both)", "from … until (distance)", "от Москвы до Петербурга — from Moscow to St. Petersburg"),
                        PrepEntry("от … до", "Genitive (both)", "from … to (clock hours)", "от пяти утра до десяти вечера — from 5 in the morning to 10 in the evening"),
                        PrepEntry("с … по", "Genitive / Accusative", "from … through (days)", "с понедельника по пятницу — from Monday to Friday")
                    ),
                    note = "For clock times and distances use от…до (both Genitive). " +
                            "For days and weeks, с…по is the natural pairing."
                )
            }

            item { PrepSectionHeader("With & Between") }
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("с", "Instrumental", "with", "Борис идёт с семьёй — Boris goes with his family"),
                        PrepEntry("между", "Instrumental", "between (items, people, groups)", "между двумя командами — between two teams\nмежду нами — between us")
                    )
                )
            }

            item { PrepSectionHeader("Against") }
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("против", "Genitive", "against", "против него — against him\nпротив этого решения — against this decision")
                    )
                )
            }

            item { PrepSectionHeader("Time Relationships") }
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("до", "Genitive", "before / until", "Я жду до этого — I'm waiting until this happens\nдо понедельника — until Monday"),
                        PrepEntry("перед", "Instrumental", "right before (immediately preceding)", "перед встречей — right before the meeting"),
                        PrepEntry("после", "Genitive", "after", "после этого — after this\nэто случилось после — it happened after"),
                        PrepEntry("во время", "Genitive", "during", "во время этого — during this\nво время урока — during the lesson"),
                        PrepEntry("пока", "(conjunction)", "while", "пока это происходило — while this was happening")
                    ),
                    note = "Use до for 'until' a point in time (Genitive). Use перед for 'just before' a specific event (Instrumental). " +
                            "Пока is a conjunction, not a preposition — it introduces a clause."
                )
            }

            item { PrepSectionHeader("Position: Above / Below / In Front / Behind") }
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("над", "Instrumental", "above", "лампа над столом — the lamp above the table"),
                        PrepEntry("под", "Instrumental", "below / under", "под столом — below the table"),
                        PrepEntry("перед", "Instrumental", "in front of", "Я стоял перед тобой в очереди — I was in front of you in line"),
                        PrepEntry("за", "Instrumental", "behind", "Я стоял за тобой — I was behind you")
                    ),
                    note = "All four take Instrumental when expressing static position (no movement). " +
                            "When movement toward the position is involved, they take Accusative instead. " +
                            "Example: Поставь коробку под стол — Put the box under the table (movement → Accusative)."
                )
            }

            item { PrepSectionHeader("About") }
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("о / об", "Prepositional", "about", "Я думал о тебе — I was thinking about you\nоб этом — about this")
                    ),
                    note = "Use об (instead of о) before words that start with a vowel sound: об этом, об Анне."
                )
            }

            item { PrepSectionHeader("Cause, Contrast & Result") }
            item {
                PrepTableCard(
                    entries = listOf(
                        PrepEntry("потому что", "(conjunction)", "because", "Я остался дома, потому что был болен — I stayed home because I was sick"),
                        PrepEntry("поэтому", "(adverb)", "therefore / that's why", "Я был болен, поэтому остался дома — I was sick, so I stayed home"),
                        PrepEntry("и поэтому", "(adverb)", "and therefore / and so", "Шёл дождь, и поэтому мы остались дома — It was raining, and so we stayed home"),
                        PrepEntry("хотя", "(conjunction)", "although / even though", "Хотя было холодно, он вышел — Although it was cold, he went out"),
                        PrepEntry("без", "Genitive", "without", "Он пришёл без денег — He came without money"),
                        PrepEntry("из-за", "Genitive", "because of / due to\n(also: from behind)", "Из-за дождя мы остались дома — Because of the rain we stayed home\nОн вышел из-за угла — He came out from behind the corner")
                    ),
                    note = "«Потому что» gives the reason; «поэтому» states the consequence — they are mirror images. " +
                            "Хотя introduces a concessive clause (the result is surprising given the condition). " +
                            "Без and из-за always take Genitive."
                )
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun PrepSectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun PrepTableCard(entries: List<PrepEntry>, note: String? = null) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Column headers
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                Text("Prep", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.7f))
                Text("Case", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.1f))
                Text("English / Russian Example", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(2.2f))
            }
            HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))

            entries.forEachIndexed { i, entry ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                            else MaterialTheme.colorScheme.surface
                        )
                        .padding(vertical = 6.dp)
                ) {
                    Text(
                        text = entry.prep,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(0.7f)
                    )
                    Text(
                        text = entry.case,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.weight(1.1f)
                    )
                    Column(modifier = Modifier.weight(2.2f)) {
                        Text(
                            text = entry.english,
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            text = entry.russian,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            if (note != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = note,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}
