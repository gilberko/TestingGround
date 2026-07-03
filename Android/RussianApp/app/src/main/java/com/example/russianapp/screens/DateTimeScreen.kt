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

// ── Helpers ───────────────────────────────────────────────────────────────────

@Composable
private fun DtSectionHeader(title: String) {
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
private fun ExampleRow(english: String, russian: String, highlight: Boolean = false) {
    val bg = if (highlight) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
             else MaterialTheme.colorScheme.surface
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp),
        colors = CardDefaults.cardColors(containerColor = bg),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp)) {
            Text(english, style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1.6f))
            Text(russian, style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(2f))
        }
    }
}

// ── Time cards ────────────────────────────────────────────────────────────────

@Composable
private fun TimeIntroCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "To ask the time: Который час? (What time is it?) or Сколько времени?",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Russian time expressions use ordinal numbers and genitive case in complex ways. " +
                        "The patterns differ depending on whether you are talking about full hours, " +
                        "minutes past the hour (up to 30), or minutes before the next hour.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ExactHourCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Exact hours — Ровно … час/часа/часов",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "The word час (hour/o'clock) changes form based on the number preceding it:",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(6.dp))
            listOf(
                "1 o'clock" to "Час  /  Один час",
                "2, 3, 4 o'clock" to "Два/Три/Четыре часа  (Gen. sg.)",
                "5–20, 0 o'clock" to "Пять … двадцать часов  (Gen. pl.)",
                "21 o'clock" to "Двадцать один час",
                "22–24 o'clock" to "Двадцать два/три/четыре часа"
            ).forEachIndexed { i, (en, ru) -> ExampleRow(en, ru, highlight = i % 2 == 1) }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Examples: Сейчас три часа. (It is 3 o'clock.) — Сейчас полдень. (It is noon.) — Сейчас полночь. (It is midnight.)",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
private fun TimeDurationsCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Durations — half an hour & an hour and a half",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "These expressions describe lengths of time, not clock positions.",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Half an hour
            Text(text = "Half an hour — полчаса", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "полчаса is the contracted form of половина часа (half of an hour). " +
                        "It behaves as a genitive singular noun phrase.",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            ExampleRow("Wait half an hour",         "Подождите полчаса",        highlight = false)
            ExampleRow("In half an hour",           "Через полчаса",            highlight = true)
            ExampleRow("Half an hour ago",          "Полчаса назад",            highlight = false)
            ExampleRow("It takes half an hour",     "Это занимает полчаса",     highlight = true)

            Spacer(modifier = Modifier.height(12.dp))

            // An hour and a half
            Text(text = "An hour and a half — полтора часа", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "полтора (masc./neut.) / полторы (fem.) + Genitive singular. " +
                        "час is masculine, so полтора часа.",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            ExampleRow("An hour and a half",           "Полтора часа",              highlight = false)
            ExampleRow("In an hour and a half",        "Через полтора часа",         highlight = true)
            ExampleRow("An hour and a half ago",       "Полтора часа назад",         highlight = false)
            ExampleRow("Two and a half hours",         "Два с половиной часа",       highlight = true)
            ExampleRow("Three and a half hours",       "Три с половиной часа",       highlight = false)

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "For 'N and a half hours' beyond 1.5, use: [cardinal] с половиной часа/часов.\n" +
                        "2–4 → с половиной часа  |  5+ → с половиной часов",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
private fun MinutesAfterCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Minutes past (1–29 minutes after the hour)",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Formula: [minutes] минута/минуты/минут [ordinal of next hour in Genitive]\n" +
                        "The hour is expressed as an ordinal adjective in the genitive: первого (of the 1st), второго (of the 2nd), etc.",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                Text("English", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.6f))
                Text("Russian", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(2f))
            }
            HorizontalDivider(modifier = Modifier.padding(bottom = 2.dp))
            ExampleRow("1:05  — five past one",     "Пять минут второго",        highlight = false)
            ExampleRow("2:10  — ten past two",      "Десять минут третьего",     highlight = true)
            ExampleRow("3:20  — twenty past three", "Двадцать минут четвёртого", highlight = false)
            ExampleRow("4:01  — one past four",     "Одна минута пятого",        highlight = true)
            ExampleRow("16:03 — three past four (PM)", "Три минуты семнадцатого", highlight = false)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Note: минута uses the same number agreement rules as час:\n" +
                        "  1 → минута,  2–4 → минуты,  5+ → минут.\n\n" +
                        "Compare the 16:03 / 16:05 pair:\n" +
                        "  16:03 = Три минуты семнадцатого   (3 → 2–4 rule → минуты)\n" +
                        "  16:05 = Пять минут семнадцатого   (5 → 5+ rule → минут)",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
private fun HalfQuarterCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Half past, Quarter past, Quarter to",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Half past
            Text(text = "Half past — Половина", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "половина + ordinal of the NEXT hour in Genitive",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            ExampleRow("1:30  half past one",   "Половина второго",    highlight = false)
            ExampleRow("5:30  half past five",  "Половина шестого",    highlight = true)
            ExampleRow("12:30 half past twelve","Половина первого",     highlight = false)

            Spacer(modifier = Modifier.height(12.dp))

            // Quarter past
            Text(text = "Quarter past — Четверть", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "четверть + ordinal of the NEXT hour in Genitive",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            ExampleRow("1:15  quarter past one",    "Четверть второго",  highlight = false)
            ExampleRow("6:15  quarter past six",    "Четверть седьмого", highlight = true)

            Spacer(modifier = Modifier.height(12.dp))

            // Quarter to
            Text(text = "Quarter to — Без четверти", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "без четверти + the NEXT hour in Nominative (cardinal number)",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            ExampleRow("1:45  quarter to two",    "Без четверти два",   highlight = false)
            ExampleRow("6:45  quarter to seven",  "Без четверти семь",  highlight = true)
        }
    }
}

@Composable
private fun MinutesToCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Minutes before the hour (31–59 minutes past)",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Formula: без + [minutes remaining] in Genitive + [next hour] in Nominative\n" +
                        "без means 'without/minus' — you are stating how many minutes are missing from the next hour.",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                Text("English", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.6f))
                Text("Russian", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(2f))
            }
            HorizontalDivider(modifier = Modifier.padding(bottom = 2.dp))
            ExampleRow("1:50  ten to two",       "Без десяти два",       highlight = false)
            ExampleRow("2:40  twenty to three",  "Без двадцати три",     highlight = true)
            ExampleRow("5:55  five to six",      "Без пяти шесть",       highlight = false)
            ExampleRow("3:35  twenty-five to four", "Без двадцати пяти четыре", highlight = true)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "The minutes in без constructions use Genitive: пять → без пяти, десять → без десяти, двадцать → без двадцати.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

// ── Date cards ────────────────────────────────────────────────────────────────

@Composable
private fun DateIntroCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "To ask the date: Какое сегодня число? (What is today's date?) or Какого числа…? (On what date…?)",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Stating the date — Nominative neuter ordinal + month in Genitive:",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            ExampleRow("Today is 1 January",  "Сегодня первое января",   highlight = false)
            ExampleRow("Today is 15 March",   "Сегодня пятнадцатое марта", highlight = true)
            ExampleRow("Today is 28 March",   "Сегодня двадцать восьмое марта", highlight = false)
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Answering 'on what date?' — Genitive neuter ordinal + month in Genitive:",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            ExampleRow("On 1 January",  "Первого января",   highlight = false)
            ExampleRow("On 15 March",   "Пятнадцатого марта", highlight = true)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Rule: When the date is a subject (Сегодня…) use the Nominative ordinal ending -ое/-ее. " +
                        "When used as an adverbial (on what date) use the Genitive ending -ого/-его.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
private fun MonthsCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Months — Nominative & Genitive",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                Text("Month",       style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                Text("Nominative",  style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                Text("Genitive",    style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
            }
            HorizontalDivider(modifier = Modifier.padding(bottom = 2.dp))
            listOf(
                Triple("January",   "январь",    "января"),
                Triple("February",  "февраль",   "февраля"),
                Triple("March",     "март",      "марта"),
                Triple("April",     "апрель",    "апреля"),
                Triple("May",       "май",       "мая"),
                Triple("June",      "июнь",      "июня"),
                Triple("July",      "июль",      "июля"),
                Triple("August",    "август",    "августа"),
                Triple("September", "сентябрь",  "сентября"),
                Triple("October",   "октябрь",   "октября"),
                Triple("November",  "ноябрь",    "ноября"),
                Triple("December",  "декабрь",   "декабря")
            ).forEachIndexed { i, (en, nom, gen) ->
                val bg = if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                         else MaterialTheme.colorScheme.surface
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp),
                    colors = CardDefaults.cardColors(containerColor = bg),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp, vertical = 3.dp)) {
                        Text(en,  style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.5f))
                        Text(nom, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary,   fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                        Text(gen, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                    }
                }
            }
        }
    }
}

@Composable
private fun YearCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Saying the year",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "For the year as a subject — Nominative ordinal: две тысячи двадцать шестой год.\n" +
                        "For 'in the year' — Prepositional with в: в две тысячи двадцать шестом году.",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            ExampleRow("The year is 2026",     "Две тысячи двадцать шестой год",    highlight = false)
            ExampleRow("In 2026",              "В две тысячи двадцать шестом году", highlight = true)
            ExampleRow("Born in 1990",         "Родился в тысяча девятьсот девяностом году", highlight = false)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Only the last word (год/году) and the last ordinal number change their case ending. " +
                        "All preceding numbers (тысяча, сотни) use cardinal forms.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
private fun FullDateExampleCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Complete date examples",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            ExampleRow("Today is 28 March 2026",
                "Сегодня двадцать восьмое марта две тысячи двадцать шестого года",
                highlight = false)
            ExampleRow("On 1 May 2025",
                "Первого мая две тысячи двадцать пятого года",
                highlight = true)
            ExampleRow("On 9 May (Victory Day)",
                "Девятого мая",
                highlight = false)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "When the year follows a date in Genitive, the year also takes Genitive: " +
                        "…марта две тысячи двадцать шестого года.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

// ── Time period cards (ago / in the future) ────────────────────────────────────

@Composable
private fun TimePeriodAgreementCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Counting time periods: 1 / 2–4 / 5+",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Russian nouns change their ending depending on the number counting them: " +
                        "1 → nominative singular, 2–4 → genitive singular, 5 and above → genitive plural. " +
                        "This applies to every noun, including all the time-period words below.",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                Text("Noun", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.3f))
                Text("1", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.3f))
                Text("2–4", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                Text("5+", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
            }
            HorizontalDivider(modifier = Modifier.padding(bottom = 2.dp))
            listOf(
                listOf("день (day)",          "день",        "дня",         "дней"),
                listOf("неделя (week)",       "неделя",      "недели",      "недель"),
                listOf("месяц (month)",       "месяц",       "месяца",      "месяцев"),
                listOf("год (year)",          "год",         "года",        "лет"),
                listOf("десятилетие (decade)","десятилетие", "десятилетия", "десятилетий"),
                listOf("век (century)",       "век",         "века",        "веков")
            ).forEachIndexed { i, row ->
                val bg = if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                         else MaterialTheme.colorScheme.surface
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp),
                    colors = CardDefaults.cardColors(containerColor = bg),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp, vertical = 3.dp)) {
                        Text(row[0], style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.3f))
                        Text(row[1], style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.3f))
                        Text(row[2], style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                        Text(row[3], style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "год is irregular: its 5+ form лет isn't built from год at all — it's borrowed from an old form of " +
                        "лето (summer/year). This is the classic \"один год, два года, пять лет\" pattern every learner trips on.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "десятилетие (decade) is often replaced in casual speech by десять лет (ten years).",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
private fun TimeAgoCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "'Ago' — [time period] + назад",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Put the quantity phrase before назад (\"ago\"). Masculine and neuter nouns look the same as their " +
                        "counting form; неделя (fem.) is the one exception — its accusative form неделю is used instead of неделя.",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Day — день", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            ExampleRow("A day ago",                  "День назад",                highlight = false)
            ExampleRow("Two/Three/Four days ago",    "Два/Три/Четыре дня назад",  highlight = true)
            ExampleRow("Five/Ten days ago",          "Пять/Десять дней назад",    highlight = false)

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Week — неделя", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            ExampleRow("A week ago",                 "Неделю назад",              highlight = true)
            ExampleRow("Two/Three/Four weeks ago",   "Две/Три/Четыре недели назад", highlight = false)
            ExampleRow("Five/Ten weeks ago",         "Пять/Десять недель назад",  highlight = true)

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Month — месяц", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            ExampleRow("A month ago",                "Месяц назад",               highlight = false)
            ExampleRow("Two/Three/Four months ago",  "Два/Три/Четыре месяца назад", highlight = true)
            ExampleRow("Five/Ten months ago",        "Пять/Десять месяцев назад", highlight = false)

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Year — год", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            ExampleRow("A year ago",                 "Год назад",                 highlight = false)
            ExampleRow("Two/Three/Four years ago",   "Два/Три/Четыре года назад", highlight = true)
            ExampleRow("Five/Ten years ago",         "Пять/Десять лет назад",     highlight = true)

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Decade — десятилетие", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            ExampleRow("A decade ago",               "Десятилетие назад",         highlight = false)
            ExampleRow("Two/Three/Four decades ago", "Два/Три/Четыре десятилетия назад", highlight = true)
            ExampleRow("Five decades ago",           "Пять десятилетий назад",    highlight = false)

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Century — век", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            ExampleRow("A century ago",              "Век назад",                 highlight = false)
            ExampleRow("Two/Three/Four centuries ago", "Два/Три/Четыре века назад", highlight = true)
            ExampleRow("Five centuries ago",         "Пять веков назад",          highlight = false)

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "тому назад is an older/more literary equivalent with identical meaning and case use: " +
                        "год тому назад = год назад.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
private fun TimeInFutureCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "'In X time' — через + [time period]",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "через (\"in / after\") takes the exact same quantity phrase used for назад, just placed before it " +
                        "instead of after. через неделю ↔ неделю назад — same неделю, different position.",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Day — день", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            ExampleRow("In a day",                   "Через день",                highlight = false)
            ExampleRow("In two/three/four days",     "Через два/три/четыре дня",  highlight = true)
            ExampleRow("In five/ten days",           "Через пять/десять дней",    highlight = false)

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Week — неделя", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            ExampleRow("In a week",                  "Через неделю",              highlight = true)
            ExampleRow("In two/three/four weeks",    "Через две/три/четыре недели", highlight = false)
            ExampleRow("In five/ten weeks",          "Через пять/десять недель",  highlight = true)

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Month — месяц", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            ExampleRow("In a month",                 "Через месяц",               highlight = false)
            ExampleRow("In two/three/four months",   "Через два/три/четыре месяца", highlight = true)
            ExampleRow("In five/ten months",         "Через пять/десять месяцев", highlight = false)

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Year — год", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            ExampleRow("In a year",                  "Через год",                 highlight = false)
            ExampleRow("In two/three/four years",    "Через два/три/четыре года", highlight = true)
            ExampleRow("In five/ten years",          "Через пять/десять лет",     highlight = true)

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Decade — десятилетие", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            ExampleRow("In a decade",                "Через десятилетие",         highlight = false)
            ExampleRow("In two/three/four decades",  "Через два/три/четыре десятилетия", highlight = true)
            ExampleRow("In five decades",            "Через пять десятилетий",    highlight = false)

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Century — век", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            ExampleRow("In a century",               "Через век",                 highlight = false)
            ExampleRow("In two/three/four centuries", "Через два/три/четыре века", highlight = true)
            ExampleRow("In five centuries",          "Через пять веков",          highlight = false)

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "через and назад use the same case form of the quantity phrase — only the word and its position " +
                        "(before vs. after) differ.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

// ── Screen ────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimeScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Date & Time") },
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
            item { DtSectionHeader("Telling the Time  (Который час?)") }
            item { TimeIntroCard() }
            item { ExactHourCard() }
            item { TimeDurationsCard() }
            item { MinutesAfterCard() }
            item { HalfQuarterCard() }
            item { MinutesToCard() }

            item { DtSectionHeader("Telling the Date  (Какое число?)") }
            item { DateIntroCard() }
            item { MonthsCard() }
            item { YearCard() }
            item { FullDateExampleCard() }

            item { DtSectionHeader("Time Periods — Ago & In the Future") }
            item { TimePeriodAgreementCard() }
            item { TimeAgoCard() }
            item { TimeInFutureCard() }
        }
    }
}
