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

private data class WeekdayMonthEntry(
    val english: String,
    val russian: String,
    val gender: String = "",   // m / f / n / pl
    val usage: String = "",    // "on …" / "in …" form
    val note: String = ""
)

private val weekdays = listOf(
    WeekdayMonthEntry("Monday",    "понедельник", "m", "в понедельник", "abbr. пн"),
    WeekdayMonthEntry("Tuesday",   "вторник",     "m", "во вторник",    "abbr. вт — во (not в) before вт-"),
    WeekdayMonthEntry("Wednesday", "среда",       "f", "в среду",       "abbr. ср"),
    WeekdayMonthEntry("Thursday",  "четверг",     "m", "в четверг",     "abbr. чт"),
    WeekdayMonthEntry("Friday",    "пятница",     "f", "в пятницу",     "abbr. пт"),
    WeekdayMonthEntry("Saturday",  "суббота",     "f", "в субботу",     "abbr. сб"),
    WeekdayMonthEntry("Sunday",    "воскресенье", "n", "в воскресенье", "abbr. вс"),
)

private val months = listOf(
    WeekdayMonthEntry("January",   "январь",   "m", "в январе"),
    WeekdayMonthEntry("February",  "февраль",  "m", "в феврале"),
    WeekdayMonthEntry("March",     "март",     "m", "в марте"),
    WeekdayMonthEntry("April",     "апрель",   "m", "в апреле"),
    WeekdayMonthEntry("May",       "май",      "m", "в мае"),
    WeekdayMonthEntry("June",      "июнь",     "m", "в июне"),
    WeekdayMonthEntry("July",      "июль",     "m", "в июле"),
    WeekdayMonthEntry("August",    "август",   "m", "в августе"),
    WeekdayMonthEntry("September", "сентябрь", "m", "в сентябре"),
    WeekdayMonthEntry("October",   "октябрь",  "m", "в октябре"),
    WeekdayMonthEntry("November",  "ноябрь",   "m", "в ноябре"),
    WeekdayMonthEntry("December",  "декабрь",  "m", "в декабре"),
)

private val calendarWords = listOf(
    WeekdayMonthEntry("week",               "неделя",       "f"),
    WeekdayMonthEntry("month",              "месяц",        "m"),
    WeekdayMonthEntry("day of the week",    "день недели",  "m"),
    WeekdayMonthEntry("weekday / workday",  "будний день",  "m", "по будним дням", "on weekdays"),
    WeekdayMonthEntry("weekend",            "выходные",     "pl", "на выходных", "on the weekend"),
    WeekdayMonthEntry("day off",            "выходной (день)", "m"),
    WeekdayMonthEntry("calendar",           "календарь",    "m"),
)

// ── Composables ───────────────────────────────────────────────────────────────

@Composable
private fun WmSectionHeader(title: String) {
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
private fun WmTextCard(lines: List<String>, examples: List<String> = emptyList()) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            lines.forEachIndexed { i, line ->
                if (i > 0) Spacer(modifier = Modifier.height(6.dp))
                Text(text = line, style = MaterialTheme.typography.bodySmall)
            }
            examples.forEach {
                Text(
                    "• $it",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }
    }
}

@Composable
private fun WmTable(entries: List<WeekdayMonthEntry>, usageHeader: String) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text("English", style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.4f))
                Text("Russian", style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold, modifier = Modifier.weight(2f))
                Text(usageHeader, style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold, modifier = Modifier.weight(2f))
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 5.dp))
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
                        modifier = Modifier.weight(1.4f)
                    )
                    Column(modifier = Modifier.weight(2f)) {
                        Text(
                            text = entry.russian,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        if (entry.gender.isNotEmpty()) {
                            Text(
                                text = entry.gender,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontStyle = FontStyle.Italic
                            )
                        }
                    }
                    Column(modifier = Modifier.weight(2f)) {
                        Text(
                            text = entry.usage,
                            style = MaterialTheme.typography.bodySmall
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

// ── Screen ────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeekdaysMonthsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Weekdays and Months") },
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
            item { WmSectionHeader("About Days and Months") }
            item {
                WmTextCard(
                    lines = listOf(
                        "In Russian, the names of days and months are written in lowercase " +
                                "(unless they start a sentence): понедельник, январь.",
                        "The week starts on Monday."
                    )
                )
            }

            item { WmSectionHeader("Days of the Week") }
            item { WmTable(weekdays, "On … (в + acc.)") }
            item {
                WmTextCard(
                    lines = listOf(
                        "\"On Monday\" uses в + Accusative. Feminine days change -а → -у " +
                                "(среда → в среду); masculine and neuter days don't change.",
                        "\"On Mondays\" (every Monday, habitual) uses по + Dative plural:"
                    ),
                    examples = listOf(
                        "по понедельникам — on Mondays",
                        "по средам — on Wednesdays",
                        "по субботам — on Saturdays",
                        "Я работаю по пятницам. — I work on Fridays."
                    )
                )
            }

            item { WmSectionHeader("Months") }
            item { WmTable(months, "In … (в + prep.)") }
            item {
                WmTextCard(
                    lines = listOf(
                        "All months are masculine. \"In January\" uses в + Prepositional (в январе).",
                        "In dates, the month goes into the Genitive — see Date & Time in Learning The Language:"
                    ),
                    examples = listOf(
                        "пятое мая — the fifth of May",
                        "Мой день рождения в мае. — My birthday is in May."
                    )
                )
            }

            item { WmSectionHeader("Related Words") }
            item { WmTable(calendarWords, "Usage") }
        }
    }
}
