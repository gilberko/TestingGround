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

// ── Data ──────────────────────────────────────────────────────────────────────

private data class TimeExprEntry(
    val english: String,
    val russian: String,
    val note: String = ""
)

private val speedExpressions = listOf(
    TimeExprEntry("immediately",           "сразу / немедленно",
        "немедленно is more urgent and formal (orders, instructions)"),
    TimeExprEntry("quickly",               "быстро"),
    TimeExprEntry("briefly (in few words)", "кратко",
        "Расскажи кратко. — Tell it briefly."),
    TimeExprEntry("briefly (for a short while)", "ненадолго",
        "Я зашёл ненадолго. — I dropped in briefly."),
    TimeExprEntry("just (it just happened)", "только что",
        "Он только что ушёл. — He just left."),
    TimeExprEntry("soon",                  "скоро"),
    TimeExprEntry("not so soon / not soon", "не так скоро / нескоро",
        "Это будет нескоро. — That won't be any time soon."),
)

private val frequencyExpressions = listOf(
    TimeExprEntry("always",                "всегда"),
    TimeExprEntry("usually",               "обычно"),
    TimeExprEntry("often",                 "часто"),
    TimeExprEntry("occasionally / sometimes", "иногда / время от времени",
        "время от времени = from time to time"),
    TimeExprEntry("seldom / rarely",       "редко / изредка",
        "изредка = now and then, once in a while"),
    TimeExprEntry("never",                 "никогда",
        "needs не on the verb: Я никогда не опаздываю."),
)

private val relativeDays = listOf(
    TimeExprEntry("the day before yesterday", "позавчера"),
    TimeExprEntry("yesterday",             "вчера"),
    TimeExprEntry("today",                 "сегодня"),
    TimeExprEntry("tomorrow",              "завтра"),
    TimeExprEntry("the day after tomorrow", "послезавтра"),
    TimeExprEntry("now (at this moment)",  "сейчас",
        "Я сейчас занят. — I'm busy right now."),
    TimeExprEntry("now (as opposed to before)", "теперь",
        "Теперь я живу в Москве. — Now (these days) I live in Moscow."),
    TimeExprEntry("next week",             "на следующей неделе"),
    TimeExprEntry("last week",             "на прошлой неделе"),
    TimeExprEntry("a few days ago",        "несколько дней назад"),
)

private val partsOfDay = listOf(
    TimeExprEntry("morning / in the morning",     "утро / утром"),
    TimeExprEntry("noon, midday / at noon",       "полдень / в полдень",
        "noon and midday are the same word"),
    TimeExprEntry("afternoon, daytime / in the afternoon", "день / днём",
        "colloquially also после обеда (lit. after lunch)"),
    TimeExprEntry("evening / in the evening",     "вечер / вечером"),
    TimeExprEntry("night / at night",             "ночь / ночью"),
    TimeExprEntry("midnight / at midnight",       "полночь / в полночь"),
)

private val unitsOfTime = listOf(
    TimeExprEntry("a second",  "секунда (f)"),
    TimeExprEntry("a minute",  "минута (f)"),
    TimeExprEntry("an hour",   "час (m)"),
    TimeExprEntry("a day",     "день (m)"),
    TimeExprEntry("a week",    "неделя (f)"),
    TimeExprEntry("a month",   "месяц (m)"),
    TimeExprEntry("a year",    "год (m)"),
    TimeExprEntry("a decade",  "десятилетие (n)"),
    TimeExprEntry("a moment",  "момент (m) / мгновение (n)",
        "Минутку! / Секундочку! — Just a moment!"),
    TimeExprEntry("a long time (duration)", "долго",
        "Я долго ждал. — I waited a long time."),
    TimeExprEntry("a long time ago / for a long time (until now)", "давно",
        "Я давно здесь живу. — I've lived here for a long time."),
)

private val indefiniteTime = listOf(
    TimeExprEntry("someday (in the future)", "когда-нибудь",
        "Когда-нибудь я поеду в Россию. — Someday I'll go to Russia."),
    TimeExprEntry("sometime / once (in the past)", "когда-то",
        "Когда-то здесь был лес. — Once there was a forest here."),
    TimeExprEntry("in the future",         "в будущем"),
    TimeExprEntry("in the past",           "в прошлом"),
    TimeExprEntry("never",                 "никогда",
        "see How Often above"),
)

// ── Composables ───────────────────────────────────────────────────────────────

@Composable
private fun TeSectionHeader(title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 4.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        HorizontalDivider(
            modifier = Modifier.padding(top = 4.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun TeSection(entries: List<TimeExprEntry>) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            entries.forEachIndexed { index, entry ->
                if (index > 0) {
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        thickness = 0.5.dp
                    )
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = entry.english,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(1.8f)
                    )
                    Column(modifier = Modifier.weight(2f)) {
                        Text(
                            text = entry.russian,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        if (entry.note.isNotEmpty()) {
                            Text(
                                text = entry.note,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontStyle = FontStyle.Italic
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TeNoteCard(text: String) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = text, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(2.dp))
        }
    }
}

// ── Screen ────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeExpressionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Time Related Expressions") },
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
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item { TeSectionHeader("Speed & Immediacy") }
            item { TeSection(speedExpressions) }

            item { TeSectionHeader("How Often") }
            item { TeSection(frequencyExpressions) }

            item { TeSectionHeader("Yesterday, Today, Tomorrow") }
            item { TeSection(relativeDays) }

            item { TeSectionHeader("Parts of the Day") }
            item {
                TeNoteCard(
                    "Each part of the day has a noun and an adverb meaning \"in the …\" " +
                            "(the Instrumental form): утро → утром, вечер → вечером."
                )
            }
            item { TeSection(partsOfDay) }

            item { TeSectionHeader("Units of Time") }
            item { TeSection(unitsOfTime) }
            item {
                TeNoteCard(
                    "After numbers, units of time follow the 1 / 2-4 / 5+ rule " +
                            "(один час, два часа, пять часов) — see Date & Time in Learning The Language."
                )
            }

            item { TeSectionHeader("Past, Future & Indefinite Time") }
            item { TeSection(indefiniteTime) }
        }
    }
}
