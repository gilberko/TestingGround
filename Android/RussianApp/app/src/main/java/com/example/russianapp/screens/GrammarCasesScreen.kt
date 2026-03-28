package com.example.russianapp.screens

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

private data class RussianCase(
    val englishName: String,
    val cyrillicName: String,
    val questionRu: String,
    val questionEn: String,
    val usage: String,
    val example: String
)

private val russianCases = listOf(
    RussianCase(
        englishName  = "Nominative",
        cyrillicName = "Именительный падеж",
        questionRu   = "Кто? Что?",
        questionEn   = "Who? What?",
        usage        = "Subject of the sentence — who or what is performing the action.",
        example      = "Кот спит. (The cat is sleeping.)"
    ),
    RussianCase(
        englishName  = "Genitive",
        cyrillicName = "Родительный падеж",
        questionRu   = "Кого? Чего?",
        questionEn   = "Of whom? Of what?",
        usage        = "Possession, absence, or after numbers (2–4 → genitive singular; 5+ → genitive plural).",
        example      = "У меня нет кота. (I don't have a cat.)"
    ),
    RussianCase(
        englishName  = "Dative",
        cyrillicName = "Дательный падеж",
        questionRu   = "Кому? Чему?",
        questionEn   = "To whom? To what?",
        usage        = "Indirect object — to whom or for whom the action is done.",
        example      = "Я даю книгу другу. (I give the book to a friend.)"
    ),
    RussianCase(
        englishName  = "Accusative",
        cyrillicName = "Винительный падеж",
        questionRu   = "Кого? Что?",
        questionEn   = "Whom? What?",
        usage        = "Direct object — the entity directly receiving the action.",
        example      = "Я читаю книгу. (I am reading a book.)"
    ),
    RussianCase(
        englishName  = "Instrumental",
        cyrillicName = "Творительный падеж",
        questionRu   = "Кем? Чем?",
        questionEn   = "With whom? With what?",
        usage        = "Means, instrument, or accompaniment — by means of, with, or alongside.",
        example      = "Я пишу ручкой. (I write with a pen.)"
    ),
    RussianCase(
        englishName  = "Prepositional",
        cyrillicName = "Предложный падеж",
        questionRu   = "О ком? О чём?",
        questionEn   = "About whom? About what?",
        usage        = "Always used after specific prepositions: в (in), на (on), о (about).",
        example      = "Я думаю о доме. (I am thinking about home.)"
    )
)

// ── Personal pronoun forms per case ───────────────────────────────────────────

private data class PronounRow(
    val nominativeLabel: String,
    val caseForm: String
)

/** Returns the personal pronoun forms for the given case (identified by English name). */
private fun pronounsForCase(englishName: String): List<PronounRow> = when (englishName) {
    "Nominative" -> listOf(
        PronounRow("я  (I)",           "я"),
        PronounRow("ты  (you sg.)",    "ты"),
        PronounRow("он  (he)",         "он"),
        PronounRow("она  (she)",       "она"),
        PronounRow("оно  (it)",        "оно"),
        PronounRow("мы  (we)",         "мы"),
        PronounRow("вы  (you pl.)",    "вы"),
        PronounRow("они  (they)",      "они")
    )
    "Genitive" -> listOf(
        PronounRow("я",       "меня"),
        PronounRow("ты",      "тебя"),
        PronounRow("он/оно",  "его  (него*)"),
        PronounRow("она",     "её  (неё*)"),
        PronounRow("мы",      "нас"),
        PronounRow("вы",      "вас"),
        PronounRow("они",     "их  (них*)")
    )
    "Dative" -> listOf(
        PronounRow("я",       "мне"),
        PronounRow("ты",      "тебе"),
        PronounRow("он/оно",  "ему  (нему*)"),
        PronounRow("она",     "ей  (ней*)"),
        PronounRow("мы",      "нам"),
        PronounRow("вы",      "вам"),
        PronounRow("они",     "им  (ним*)")
    )
    "Accusative" -> listOf(
        PronounRow("я",       "меня"),
        PronounRow("ты",      "тебя"),
        PronounRow("он/оно",  "его  (него*)"),
        PronounRow("она",     "её  (неё*)"),
        PronounRow("мы",      "нас"),
        PronounRow("вы",      "вас"),
        PronounRow("они",     "их  (них*)")
    )
    "Instrumental" -> listOf(
        PronounRow("я",       "мной"),
        PronounRow("ты",      "тобой"),
        PronounRow("он/оно",  "им  (ним*)"),
        PronounRow("она",     "ей  (ней*)"),
        PronounRow("мы",      "нами"),
        PronounRow("вы",      "вами"),
        PronounRow("они",     "ими  (ними*)")
    )
    "Prepositional" -> listOf(
        PronounRow("я",       "мне"),
        PronounRow("ты",      "тебе"),
        PronounRow("он/оно",  "нём"),
        PronounRow("она",     "ней"),
        PronounRow("мы",      "нас"),
        PronounRow("вы",      "вас"),
        PronounRow("они",     "них")
    )
    else -> emptyList()
}

@Composable
private fun CaseCard(russianCase: RussianCase) {
    val pronouns = pronounsForCase(russianCase.englishName)
    val hasPrepositionNote = russianCase.englishName != "Nominative" && russianCase.englishName != "Prepositional"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "${russianCase.englishName} — ${russianCase.cyrillicName}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${russianCase.questionRu}  /  ${russianCase.questionEn}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = russianCase.usage,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = russianCase.example,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Italic
            )

            // ── Personal pronoun table ─────────────────────────────────────
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
                Text("Pronoun", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.4f))
                Text("${russianCase.englishName} form", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(2f))
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
                        Text(row.nominativeLabel, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.4f))
                        Text(row.caseForm, style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(2f))
                    }
                }
            }
            if (hasPrepositionNote) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "* After a preposition, 3rd-person pronouns take the prefix н-: его→него, её→неё, им→ним, etc.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontStyle = FontStyle.Italic
                )
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
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item {
                Text(
                    text = "Russian has 6 grammatical cases. Each changes noun endings depending on the word's role in the sentence. Each case also transforms personal pronouns — the table below each case shows all pronoun forms.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            items(russianCases) { case ->
                CaseCard(case)
            }
        }
    }
}
