package com.example.russianapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuantifiersScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Many, Few, A Lot, All, Whole") },
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

            // ── Many / A Lot ──────────────────────────────────────────────────
            item { QuantSectionHeader("Many & A Lot — Много") }
            item {
                QuantCard(title = "много — many, much, a lot") {
                    Text(
                        text = "много takes the GENITIVE CASE of the following noun. " +
                                "It is used with both countable nouns (many) and uncountable nouns (much/a lot of).",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.height(8.dp))
                    QuantExampleRow("много людей", "many people")
                    QuantExampleRow("много воды", "a lot of water")
                    QuantExampleRow("много работы", "a lot of work")
                    QuantExampleRow("Здесь много интересного.", "There is a lot of interesting things here.")
                    QuantExampleRow("Я видел много мест.", "I have seen many places.")
                }
            }
            item {
                QuantCard(title = "столько — so many, so much") {
                    Text(
                        text = "столько also takes genitive. It emphasises an impressive or surprising quantity.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.height(8.dp))
                    QuantExampleRow("Столько людей!", "So many people!")
                    QuantExampleRow("столько всего", "so much of everything")
                    QuantExampleRow("Откуда столько денег?", "Where did so much money come from?")
                }
            }

            // ── Few / A Few ───────────────────────────────────────────────────
            item { QuantSectionHeader("Few, A Few & A Little — Мало / Несколько") }
            item {
                QuantCard(title = "мало — little, few (not enough)") {
                    Text(
                        text = "мало takes genitive and implies there is less than desired or needed — a negative connotation.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.height(8.dp))
                    QuantExampleRow("мало времени", "little time (not enough)")
                    QuantExampleRow("мало денег", "not much money")
                    QuantExampleRow("Мне мало этого.", "This is not enough for me.")
                }
            }
            item {
                QuantCard(title = "несколько — several, a few") {
                    Text(
                        text = "несколько takes the GENITIVE PLURAL. It means a small specific-ish number — " +
                                "more than one or two, less than many.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.height(8.dp))
                    QuantExampleRow("несколько книг", "several books")
                    QuantExampleRow("несколько дней", "a few days")
                    QuantExampleRow("несколько минут", "a few minutes")
                    QuantExampleRow("Подожди несколько секунд.", "Wait a few seconds.")
                }
            }
            item {
                QuantCard(title = "немного — a little, a bit (neutral)") {
                    Text(
                        text = "немного takes genitive and is NEUTRAL — just a small amount, no negative connotation. " +
                                "It can also modify adjectives and adverbs.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.height(8.dp))
                    QuantExampleRow("немного молока", "a little milk")
                    QuantExampleRow("немного странно", "a bit strange")
                    QuantExampleRow("Говори немного медленнее.", "Speak a little more slowly.")
                    Spacer(Modifier.height(6.dp))
                    // contrast card
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text("мало vs немного:",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer)
                            Spacer(Modifier.height(4.dp))
                            Text("Мало времени.", style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
                            Text("Not enough time. (insufficient — negative)",
                                style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onPrimaryContainer)
                            Spacer(Modifier.height(6.dp))
                            Text("Немного времени.", style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
                            Text("A little time. (a small amount — neutral)",
                                style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onPrimaryContainer)
                        }
                    }
                }
            }

            // ── Each / Every ──────────────────────────────────────────────────
            item { QuantSectionHeader("Each & Every — Каждый / Любой") }
            item {
                QuantCard(title = "каждый — each, every (one by one)") {
                    Text(
                        text = "каждый agrees with the noun in gender, number and case — like an adjective. " +
                                "It refers to each individual member of a group.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                        Text("Form", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
                        Text("Example", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(2.8f))
                    }
                    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                    listOf(
                        "каждый (m)"  to "каждый день — every day",
                        "каждая (f)"  to "каждая неделя — every week",
                        "каждое (n)"  to "каждое утро — every morning",
                        "каждые (pl)" to "каждые два часа — every two hours"
                    ).forEachIndexed { i, (form, ex) ->
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .background(
                                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                    else MaterialTheme.colorScheme.surface
                                )
                                .padding(vertical = 4.dp)
                        ) {
                            Text(form, style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.weight(1.2f))
                            Text(ex, style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.weight(2.8f))
                        }
                    }
                    Spacer(Modifier.height(6.dp))
                    QuantExampleRow("Каждый человек важен.", "Every person matters.")
                    QuantExampleRow("Я хожу туда каждую пятницу.", "I go there every Friday.")
                }
            }
            item {
                QuantCard(title = "любой — any (it doesn't matter which)") {
                    Text(
                        text = "любой also agrees as an adjective. It means \"any one — the choice doesn't matter\", " +
                                "implying freedom of choice.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.height(8.dp))
                    QuantExampleRow("в любое время", "at any time")
                    QuantExampleRow("любой цвет подойдёт", "any color will do")
                    QuantExampleRow("Позвони в любой день.", "Call on any day.")
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "каждый = each individual one; любой = whichever one, doesn't matter which.",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // ── All / Whole ───────────────────────────────────────────────────
            item { QuantSectionHeader("All & Whole — Весь / Целый") }
            item {
                QuantCard(title = "весь, вся, всё, все — all, the whole") {
                    Text(
                        text = "весь agrees with the noun in gender, number and case like an adjective. " +
                                "It means the entirety of something.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                        Text("Form", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                        Text("Example", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(3f))
                    }
                    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                    listOf(
                        "весь (m)"  to "весь день — the whole day",
                        "вся (f)"   to "вся семья — the whole family",
                        "всё (n)"   to "всё молоко — all the milk",
                        "все (pl)"  to "все люди — all people"
                    ).forEachIndexed { i, (form, ex) ->
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .background(
                                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                    else MaterialTheme.colorScheme.surface
                                )
                                .padding(vertical = 4.dp)
                        ) {
                            Text(form, style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.weight(1f))
                            Text(ex, style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.weight(3f))
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    Text("всё / все as pronouns:",
                        style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(4.dp))
                    QuantExampleRow("Я знаю всё.", "I know everything.")
                    QuantExampleRow("Все пришли.", "Everyone came.")
                    QuantExampleRow("Всё в порядке.", "Everything is fine.")
                    Spacer(Modifier.height(6.dp))
                    Text("Related words:",
                        style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(4.dp))
                    QuantExampleRow("всегда", "always")
                    QuantExampleRow("везде", "everywhere")
                    QuantExampleRow("всё равно", "it doesn't matter / all the same")
                }
            }
            item {
                QuantCard(title = "целый — whole, entire (emphasising completeness)") {
                    Text(
                        text = "целый also agrees as an adjective. Like весь, it means whole/entire, " +
                                "but it often adds emphasis on unexpectedness or impressive completeness.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.height(8.dp))
                    QuantExampleRow("целый день", "a whole day (he spent the entire day…)")
                    QuantExampleRow("целая история", "a whole story / quite a story")
                    QuantExampleRow("Прошёл целый год.", "A whole year has passed.")
                    QuantExampleRow("целый город", "an entire city")
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "весь vs целый: весь = all of it (the total amount); " +
                                "целый = the whole thing (stressing completeness or surprising scale).",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // ── Quick reference ───────────────────────────────────────────────
            item { QuantSectionHeader("Quick Reference") }
            item {
                QuantCard(title = "Summary table") {
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                        Text("Word", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.4f))
                        Text("Meaning", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.4f))
                        Text("Grammar", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.8f))
                    }
                    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                    listOf(
                        Triple("много",           "many / a lot",        "+ Genitive"),
                        Triple("столько",         "so many / so much",   "+ Genitive"),
                        Triple("мало",            "few / little",        "+ Genitive"),
                        Triple("несколько",       "several / a few",     "+ Gen plural"),
                        Triple("немного",         "a little / a bit",    "+ Genitive"),
                        Triple("каждый",          "each / every",        "agrees as adj"),
                        Triple("любой",           "any (your choice)",   "agrees as adj"),
                        Triple("весь / вся…",     "all / the whole",     "agrees as adj"),
                        Triple("целый",           "whole / entire",      "agrees as adj")
                    ).forEachIndexed { i, (word, meaning, grammar) ->
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .background(
                                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                    else MaterialTheme.colorScheme.surface
                                )
                                .padding(vertical = 5.dp)
                        ) {
                            Text(word, style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.weight(1.4f))
                            Text(meaning, style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.weight(1.4f))
                            Text(grammar, style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.weight(1.8f))
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun QuantSectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun QuantCard(title: String, content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }
    }
}

@Composable
private fun QuantExampleRow(russian: String, english: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text(russian, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
        Text(english, style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
            color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
    Spacer(modifier = Modifier.height(2.dp))
}
