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
import androidx.compose.foundation.lazy.items
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

private data class CzechCase(
    val number: Int,
    val englishName: String,
    val czechName: String,
    val question: String,
    val usage: String,
    val example: String
)

private val czechCases = listOf(
    CzechCase(1, "Nominative", "Nominativ", "Kdo? Co? (Who? What?)",
        "Subject of the sentence — who or what performs the action.",
        "Pes spí. (The dog is sleeping.)"),
    CzechCase(2, "Genitive", "Genitiv", "Koho? Čeho? (Of whom? Of what?)",
        "Possession, absence, negation, and after certain prepositions (bez, do, od, z, u, podle, kolem).",
        "Nemám psa. (I don't have a dog.) / okno domu (window of the house)"),
    CzechCase(3, "Dative", "Dativ", "Komu? Čemu? (To whom? To what?)",
        "Indirect object — to whom or for whom the action is done. After: k, ke, kvůli, díky.",
        "Dám knížku příteli. (I'll give the book to a friend.)"),
    CzechCase(4, "Accusative", "Akuzativ", "Koho? Co? (Whom? What?)",
        "Direct object — the entity directly receiving the action. After: na, pro, přes, skrz, v/ve (with time), za.",
        "Čtu knihu. (I am reading a book.)"),
    CzechCase(5, "Vocative", "Vokativ", "— (used when addressing someone)",
        "Addressing a person or thing directly. Not used after prepositions.",
        "Petře, pojď sem! (Petr, come here!)"),
    CzechCase(6, "Locative", "Lokativ", "O kom? O čem? (About whom? About what?)",
        "Location or topic; ALWAYS used with a preposition (o, v/ve, na, při, po).",
        "Mluvím o příteli. (I am talking about a friend.) / jsem v Praze (I am in Prague)"),
    CzechCase(7, "Instrumental", "Instrumentál", "Kým? Čím? (With whom? With what?)",
        "Means/instrument, accompaniment, or identity after být (to be). After: s/se, před, za, nad, pod, mezi.",
        "Píšu tužkou. (I write with a pencil.) / Jsem učitelem. (I am a teacher.)")
)

private data class PronounRow(val label: String, val form: String)

private fun pronounsForCase(caseNumber: Int): List<PronounRow> = when (caseNumber) {
    1 -> listOf(
        PronounRow("já (I)",        "já"),
        PronounRow("ty (you sg.)",  "ty"),
        PronounRow("on (he)",       "on"),
        PronounRow("ona (she)",     "ona"),
        PronounRow("ono (it)",      "ono"),
        PronounRow("my (we)",       "my"),
        PronounRow("vy (you pl.)",  "vy"),
        PronounRow("oni/ony (they)","oni/ony")
    )
    2 -> listOf(
        PronounRow("já",    "mě / mne"),
        PronounRow("ty",    "tě / tebe"),
        PronounRow("on/ono","ho / něho / jeho"),
        PronounRow("ona",   "jí / ní"),
        PronounRow("my",    "nás"),
        PronounRow("vy",    "vás"),
        PronounRow("oni/ony","jich / nich")
    )
    3 -> listOf(
        PronounRow("já",    "mi / mně"),
        PronounRow("ty",    "ti / tobě"),
        PronounRow("on/ono","mu / jemu / němu"),
        PronounRow("ona",   "jí / ní"),
        PronounRow("my",    "nám"),
        PronounRow("vy",    "vám"),
        PronounRow("oni/ony","jim / nim")
    )
    4 -> listOf(
        PronounRow("já",    "mě / mne"),
        PronounRow("ty",    "tě / tebe"),
        PronounRow("on/ono","ho / jej / něho / jeho"),
        PronounRow("ona",   "ji / ni"),
        PronounRow("my",    "nás"),
        PronounRow("vy",    "vás"),
        PronounRow("oni/ony","je / ně")
    )
    5 -> emptyList()
    6 -> listOf(
        PronounRow("já",    "mně"),
        PronounRow("ty",    "tobě"),
        PronounRow("on/ono","něm"),
        PronounRow("ona",   "ní"),
        PronounRow("my",    "nás"),
        PronounRow("vy",    "vás"),
        PronounRow("oni/ony","nich")
    )
    7 -> listOf(
        PronounRow("já",    "mnou"),
        PronounRow("ty",    "tebou"),
        PronounRow("on/ono","jím / ním"),
        PronounRow("ona",   "jí / ní"),
        PronounRow("my",    "námi"),
        PronounRow("vy",    "vámi"),
        PronounRow("oni/ony","jimi / nimi")
    )
    else -> emptyList()
}

@Composable
private fun CaseCard(case: CzechCase) {
    val pronouns = pronounsForCase(case.number)
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "${case.number}. ${case.englishName} — ${case.czechName}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = case.question,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = case.usage, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = case.example,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Italic
            )
            if (pronouns.isNotEmpty()) {
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Personal Pronouns",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                    Text("Pronoun", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                    Text("${case.englishName} form", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(2f))
                }
                HorizontalDivider(modifier = Modifier.padding(bottom = 2.dp))
                pronouns.forEachIndexed { i, row ->
                    val bg = if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                             else MaterialTheme.colorScheme.surface
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp),
                        colors = CardDefaults.cardColors(containerColor = bg),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                    ) {
                        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp, vertical = 3.dp)) {
                            Text(row.label, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.5f))
                            Text(
                                row.form,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(2f)
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GrammarCasesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Grammar: Cases") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item {
                Text(
                    text = "Czech has 7 grammatical cases. Each changes noun and adjective endings depending on the word's role in the sentence.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            items(czechCases) { CaseCard(it) }
        }
    }
}
