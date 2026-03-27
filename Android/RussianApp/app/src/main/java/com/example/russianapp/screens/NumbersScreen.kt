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

private data class RussianNumber(
    val numeral: Int,
    val cardinal: String,
    val ordinalM: String,
    val ordinalF: String
)

private val numbers = listOf(
    RussianNumber(0,    "ноль",           "нулевой",        "нулевая"),
    RussianNumber(1,    "один / одна",    "первый",         "первая"),
    RussianNumber(2,    "два / две",      "второй",         "вторая"),
    RussianNumber(3,    "три",            "третий",         "третья"),
    RussianNumber(4,    "четыре",         "четвёртый",      "четвёртая"),
    RussianNumber(5,    "пять",           "пятый",          "пятая"),
    RussianNumber(6,    "шесть",          "шестой",         "шестая"),
    RussianNumber(7,    "семь",           "седьмой",        "седьмая"),
    RussianNumber(8,    "восемь",         "восьмой",        "восьмая"),
    RussianNumber(9,    "девять",         "девятый",        "девятая"),
    RussianNumber(10,   "десять",         "десятый",        "десятая"),
    RussianNumber(11,   "одиннадцать",    "одиннадцатый",   "одиннадцатая"),
    RussianNumber(12,   "двенадцать",     "двенадцатый",    "двенадцатая"),
    RussianNumber(13,   "тринадцать",     "тринадцатый",    "тринадцатая"),
    RussianNumber(14,   "четырнадцать",   "четырнадцатый",  "четырнадцатая"),
    RussianNumber(15,   "пятнадцать",     "пятнадцатый",    "пятнадцатая"),
    RussianNumber(16,   "шестнадцать",    "шестнадцатый",   "шестнадцатая"),
    RussianNumber(17,   "семнадцать",     "семнадцатый",    "семнадцатая"),
    RussianNumber(18,   "восемнадцать",   "восемнадцатый",  "восемнадцатая"),
    RussianNumber(19,   "девятнадцать",   "девятнадцатый",  "девятнадцатая"),
    RussianNumber(20,   "двадцать",       "двадцатый",      "двадцатая"),
    RussianNumber(30,   "тридцать",       "тридцатый",      "тридцатая"),
    RussianNumber(40,   "сорок",          "сороковой",      "сороковая"),
    RussianNumber(50,   "пятьдесят",      "пятидесятый",    "пятидесятая"),
    RussianNumber(60,   "шестьдесят",     "шестидесятый",   "шестидесятая"),
    RussianNumber(70,   "семьдесят",      "семидесятый",    "семидесятая"),
    RussianNumber(80,   "восемьдесят",    "восьмидесятый",  "восьмидесятая"),
    RussianNumber(90,   "девяносто",      "девяностый",     "девяностая"),
    RussianNumber(100,  "сто",            "сотый",          "сотая"),
    RussianNumber(1000, "тысяча",         "тысячный",       "тысячная")
)

// ── Composables ───────────────────────────────────────────────────────────────

@Composable
private fun SectionHeader(title: String) {
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
private fun CardinalOrdinalIntroCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Cardinals vs Ordinals",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Cardinal numbers (один, два, три…) answer the question \"how many?\" — they are used for counting and quantity.",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Ordinal numbers (первый, второй, третий…) answer \"which in order?\" — used for ranking and ordering. They function like adjectives and agree with the noun in gender, number, and case.",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "• первый этаж (the first floor — masc.)\n• первая страница (the first page — fem.)",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun GenderAgreementCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Gender Agreement",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Some cardinal numbers change form based on the gender of the noun they modify:",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))

            listOf(
                Triple("1", "один (m) / одна (f) / одно (n)", "один стол, одна книга, одно окно"),
                Triple("2", "два (m/n) / две (f)",             "два стола, две книги, два окна"),
                Triple("3–4", "no gender change",              "три стола, три книги"),
                Triple("5+",  "no gender change",              "пять столов, пять книг")
            ).forEach { (num, rule, example) ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp)
                ) {
                    Text(num,    style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(0.8f))
                    Text(rule,   style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(2.5f))
                    Text(example, style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant, fontStyle = FontStyle.Italic,
                        modifier = Modifier.weight(2.5f))
                }
            }
        }
    }
}

@Composable
private fun NounCaseAfterNumbersCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Noun Case After Numbers",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "The noun following a cardinal number changes its case depending on the number:",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Table header
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                Text("Count", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f))
                Text("Noun case", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1.5f))
                Text("год (year)", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
                Text("книга (book)", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1.5f))
            }
            HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))

            listOf(
                listOf("1",     "Nom. singular",  "один год",      "одна книга"),
                listOf("2–4",   "Gen. singular",  "два года",      "две книги"),
                listOf("5–20",  "Gen. plural",    "пять лет",      "пять книг"),
                listOf("21",    "Nom. singular",  "двадцать один год", "двадцать одна книга"),
                listOf("22–24", "Gen. singular",  "двадцать два года", "двадцать две книги"),
                listOf("25–30", "Gen. plural",    "двадцать пять лет", "двадцать пять книг")
            ).forEachIndexed { i, (count, rule, ex1, ex2) ->
                val bg = if (i % 2 == 0)
                    MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                else
                    MaterialTheme.colorScheme.surface
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp),
                    colors = CardDefaults.cardColors(containerColor = bg),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp, vertical = 3.dp)) {
                        Text(count, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f))
                        Text(rule,  style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.5f))
                        Text(ex1,   style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.weight(1.5f))
                        Text(ex2,   style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.weight(1.5f))
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "More examples:",
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            listOf(
                "человек (person): один человек / два человека / пять человек",
                "рубль (ruble): один рубль / два рубля / пять рублей",
                "минута (minute): одна минута / две минуты / пять минут"
            ).forEach { example ->
                Text(
                    text = "• $example",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(vertical = 1.dp)
                )
            }
        }
    }
}

@Composable
private fun NumberTableHeader() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp)
    ) {
        Text("#",           style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(0.6f))
        Text("Cardinal",    style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(2f))
        Text("Ordinal (M)", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(2f))
        Text("Ordinal (F)", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.tertiary, modifier = Modifier.weight(2f))
    }
}

@Composable
private fun NumberRow(number: RussianNumber, isEven: Boolean) {
    val bg = if (isEven)
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
    else
        MaterialTheme.colorScheme.surface
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp),
        colors = CardDefaults.cardColors(containerColor = bg),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 5.dp)) {
            Text(number.numeral.toString(),
                style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(0.6f))
            Text(number.cardinal,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(2f))
            Text(number.ordinalM,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.weight(2f))
            Text(number.ordinalF,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.weight(2f))
        }
    }
}

// ── Screen ────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumbersScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Numbers") },
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
            item { SectionHeader("Cardinals & Ordinals") }
            item { CardinalOrdinalIntroCard() }

            item { SectionHeader("Gender Agreement") }
            item { GenderAgreementCard() }

            item { SectionHeader("Noun Case After Numbers") }
            item { NounCaseAfterNumbersCard() }

            item { SectionHeader("Number Table") }
            item { NumberTableHeader() }
            items(numbers.size) { index ->
                NumberRow(numbers[index], isEven = index % 2 == 0)
            }
        }
    }
}
