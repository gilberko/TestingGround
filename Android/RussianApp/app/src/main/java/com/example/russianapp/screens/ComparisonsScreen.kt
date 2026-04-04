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
fun ComparisonsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Comparisons") },
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

            // ── Regular Comparative Formation ────────────────────────────────
            item { CompSectionHeader("Сравнительная степень — Regular Comparative Formation") }
            item {
                CompCard(title = "Forming the comparative: drop -ый/-ий/-ой, add -ее") {
                    Text(
                        text = "Most adjectives form the comparative by dropping the long-form ending " +
                                "(-ый, -ий, -ой) and adding -ее (or the colloquial variant -ей). " +
                                "The comparative form is invariant — it does not change for gender or number.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "Colloquial speech often uses -ей instead of -ее: быстрей = быстрее.",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            item {
                CompCard(title = "Regular comparative forms") {
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                        Text("Adjective", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.3f))
                        Text("Meaning", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
                        Text("Comparative", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.weight(1.3f))
                        Text("Meaning", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
                    }
                    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                    listOf(
                        listOf("вкусный",    "tasty",        "вкуснее",    "tastier"),
                        listOf("умный",      "smart",        "умнее",      "smarter"),
                        listOf("красивый",   "beautiful",    "красивее",   "more beautiful"),
                        listOf("интересный", "interesting",  "интереснее", "more interesting"),
                        listOf("холодный",   "cold",         "холоднее",   "colder"),
                        listOf("быстрый",    "fast",         "быстрее",    "faster"),
                        listOf("медленный",  "slow",         "медленнее",  "slower"),
                        listOf("горячий",    "hot",          "горячее",    "hotter")
                    ).forEachIndexed { i, row ->
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .background(
                                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                    else MaterialTheme.colorScheme.surface
                                )
                                .padding(vertical = 4.dp)
                        ) {
                            Text(row[0], style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.weight(1.3f))
                            Text(row[1], style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.weight(1.2f))
                            Text(row[2], style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1.3f))
                            Text(row[3], style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.weight(1.2f))
                        }
                    }
                }
            }

            // ── "Than" — чем vs Genitive ─────────────────────────────────────
            item { CompSectionHeader("\"Than\" — чем vs. Genitive Case") }
            item {
                CompCard(title = "Two ways to express \"than\" in Russian") {
                    Text(
                        text = "Russian has two constructions for \"than\":\n\n" +
                                "1. чем + Nominative — formal and unambiguous, preferred in writing.\n" +
                                "2. Genitive case alone (no чем) — colloquial, very common in speech.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.height(8.dp))
                    CompExampleRow("Этот суп вкуснее, чем тот.", "This soup is tastier than that one. (чем)")
                    CompExampleRow("Она умнее его.", "She is smarter than him. (genitive)")
                    CompExampleRow("Москва больше, чем Берлин.", "Moscow is bigger than Berlin. (чем)")
                    CompExampleRow("Москва больше Берлина.", "Moscow is bigger than Berlin. (genitive)")
                    CompExampleRow("Этот фильм интереснее, чем тот.", "This film is more interesting than that one.")
                    CompExampleRow("Он старше меня.", "He is older than me.")
                    CompExampleRow("Эта задача сложнее, чем я думал.", "This task is harder than I thought.")
                }
            }

            // ── "Less Than" — менее ──────────────────────────────────────────
            item { CompSectionHeader("\"Less Than\" — менее") }
            item {
                CompCard(title = "менее + adjective (formal/written register)") {
                    Text(
                        text = "менее is the analytical word for \"less\" placed before the adjective. " +
                                "It is formal and more common in written language. " +
                                "In everyday speech, Russians often rephrase to avoid менее entirely.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "Tip: colloquial alternatives use не такой (not as...) or restructure the sentence.",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.height(8.dp))
                    CompExampleRow("Это менее вкусно, чем то.", "This is less tasty than that.")
                    CompExampleRow("Этот маршрут менее удобный, чем тот.", "This route is less convenient than that one.")
                }
            }

            // ── Superlative — самый ──────────────────────────────────────────
            item { CompSectionHeader("Превосходная степень — Superlative with самый") }
            item {
                CompCard(title = "самый + adjective — agrees in gender and number") {
                    Text(
                        text = "самый is the most common way to form the superlative (\"the most...\"). " +
                                "Unlike the comparative, самый is a full adjective and agrees with the noun " +
                                "it describes in gender and number.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                        Text("Gender", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                        Text("Form", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.weight(1f))
                        Text("Example", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(2f))
                    }
                    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                    listOf(
                        Triple("M",  "самый", "самый вкусный суп"),
                        Triple("F",  "самая", "самая умная девушка"),
                        Triple("N",  "самое", "самое красивое место"),
                        Triple("Pl", "самые", "самые быстрые машины")
                    ).forEachIndexed { i, (gender, form, example) ->
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .background(
                                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                    else MaterialTheme.colorScheme.surface
                                )
                                .padding(vertical = 4.dp)
                        ) {
                            Text(gender, style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                            Text(form, style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.weight(1f))
                            Text(example, style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.weight(2f))
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                    CompExampleRow("Это самый вкусный суп!", "This is the tastiest soup!")
                    CompExampleRow("Она самая умная в классе.", "She is the smartest in the class.")
                    CompExampleRow("Это самое красивое место в городе.", "This is the most beautiful place in the city.")
                    CompExampleRow("Они самые быстрые в команде.", "They are the fastest in the team.")
                }
            }

            // ── "The Least" — наименее ───────────────────────────────────────
            item { CompSectionHeader("\"The Least\" — наименее") }
            item {
                CompCard(title = "наименее + adjective (formal/written only)") {
                    Text(
                        text = "наименее means \"the least\" and precedes the adjective unchanged. " +
                                "It is rare in everyday conversation. Colloquially, Russians prefer " +
                                "не самый (\"not the most\") as a softer equivalent.",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "Colloquial equivalent: не самый интересный фильм (not the most interesting film).",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.height(8.dp))
                    CompExampleRow("Это наименее интересный фильм.", "This is the least interesting film.")
                    CompExampleRow("Это наименее удобный вариант.", "This is the least convenient option.")
                }
            }

            // ── Irregular Comparatives ───────────────────────────────────────
            item { CompSectionHeader("Irregular Comparatives — Неправильные формы") }
            item {
                CompCard(title = "Common irregular forms (must be memorised)") {
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                        Text("Adjective", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.3f))
                        Text("Meaning", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
                        Text("Comparative", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.weight(1.3f))
                        Text("Meaning", style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
                    }
                    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                    listOf(
                        listOf("хороший",   "good",         "лучше",           "better"),
                        listOf("плохой",    "bad",          "хуже",            "worse"),
                        listOf("большой",   "big",          "больше",          "bigger / more"),
                        listOf("маленький", "small",        "меньше",          "smaller / less"),
                        listOf("высокий",   "tall / high",  "выше",            "taller / higher"),
                        listOf("низкий",    "low / short",  "ниже",            "lower / shorter"),
                        listOf("старый",    "old",          "старше",          "older"),
                        listOf("молодой",   "young",        "моложе / младше", "younger"),
                        listOf("дорогой",   "expensive",    "дороже",          "more expensive"),
                        listOf("дешёвый",   "cheap",        "дешевле",         "cheaper"),
                        listOf("далёкий",   "far",          "дальше",          "farther"),
                        listOf("близкий",   "near / close", "ближе",           "closer / nearer"),
                        listOf("лёгкий",    "easy / light", "легче",           "easier / lighter"),
                        listOf("тяжёлый",   "heavy / hard", "тяжелее",         "heavier / harder")
                    ).forEachIndexed { i, row ->
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .background(
                                    if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                    else MaterialTheme.colorScheme.surface
                                )
                                .padding(vertical = 4.dp)
                        ) {
                            Text(row[0], style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.weight(1.3f))
                            Text(row[1], style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.weight(1.2f))
                            Text(row[2], style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1.3f))
                            Text(row[3], style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.weight(1.2f))
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                    CompExampleRow("Он готовит лучше, чем я.", "He cooks better than me.")
                    CompExampleRow("Эта машина хуже, чем та.", "This car is worse than that one.")
                    CompExampleRow("Сегодня холоднее, чем вчера.", "Today is colder than yesterday.")
                    CompExampleRow("Он старше меня на три года.", "He is three years older than me.")
                    CompExampleRow("Эта сумка дороже, чем та.", "This bag is more expensive than that one.")
                    CompExampleRow("Это здание выше.", "This building is taller.")
                    CompExampleRow("Эта книга легче для понимания.", "This book is easier to understand.")
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun CompSectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun CompCard(title: String, content: @Composable ColumnScope.() -> Unit) {
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
private fun CompExampleRow(russian: String, english: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text(russian, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
        Text(english, style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic,
            color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
    Spacer(modifier = Modifier.height(2.dp))
}
