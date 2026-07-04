package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp

private data class TimeEntry(val pt: String, val en: String, val note: String = "")
private data class TimeCategory(val title: String, val entries: List<TimeEntry>, val headerNote: String = "", val stacked: Boolean = false)

private val timeCategories = listOf(
    TimeCategory(
        "Today & Now",
        listOf(
            TimeEntry("hoje", "today"),
            TimeEntry("agora", "now"),
            TimeEntry("agora mesmo", "right now"),
            TimeEntry("hoje de manhã", "this morning"),
            TimeEntry("hoje à tarde", "this afternoon"),
            TimeEntry("hoje à noite", "tonight")
        )
    ),
    TimeCategory(
        "Past",
        listOf(
            TimeEntry("ontem", "yesterday"),
            TimeEntry("anteontem", "the day before yesterday"),
            TimeEntry("ontem de manhã", "yesterday morning"),
            TimeEntry("ontem à noite", "last night")
        )
    ),
    TimeCategory(
        "Future",
        listOf(
            TimeEntry("amanhã", "tomorrow"),
            TimeEntry("depois de amanhã", "the day after tomorrow"),
            TimeEntry("amanhã de manhã", "tomorrow morning"),
            TimeEntry("amanhã à noite", "tomorrow night")
        )
    ),
    TimeCategory(
        "Parts of the Day",
        listOf(
            TimeEntry("manhã", "morning"),
            TimeEntry("meio-dia", "noon"),
            TimeEntry("tarde", "afternoon / evening"),
            TimeEntry("noite", "night"),
            TimeEntry("meia-noite", "midnight"),
            TimeEntry("de manhã", "in the morning"),
            TimeEntry("à tarde", "in the afternoon"),
            TimeEntry("à noite", "at night")
        )
    ),
    TimeCategory(
        "Time Periods",
        listOf(
            TimeEntry("um dia", "a day"),
            TimeEntry("uma semana", "a week"),
            TimeEntry("um mês", "a month"),
            TimeEntry("um ano", "a year"),
            TimeEntry("uma hora", "an hour"),
            TimeEntry("um minuto", "a minute"),
            TimeEntry("esta semana", "this week"),
            TimeEntry("este mês", "this month"),
            TimeEntry("este ano", "this year"),
            TimeEntry("todos os dias", "every day"),
            TimeEntry("semana passada", "last week"),
            TimeEntry("próxima semana / semana que vem", "next week")
        )
    ),
    TimeCategory(
        "Days of the Week",
        listOf(
            TimeEntry("domingo", "Sunday", "From Latin Dominica — Lord's day"),
            TimeEntry("segunda-feira", "Monday", "From Latin feria secunda — second feast/fair day"),
            TimeEntry("terça-feira", "Tuesday", "From Latin feria tertia — third fair day"),
            TimeEntry("quarta-feira", "Wednesday", "From Latin feria quarta — fourth fair day"),
            TimeEntry("quinta-feira", "Thursday", "From Latin feria quinta — fifth fair day"),
            TimeEntry("sexta-feira", "Friday", "From Latin feria sexta — sixth fair day"),
            TimeEntry("sábado", "Saturday", "From Hebrew Shabbat (Sabbath)")
        ),
        headerNote = "Portuguese (and Galician) is unique among Romance languages: instead of planet names (French lundi = Moon, mardi = Mars\u2026), weekdays are numbered as feiras (feast/market days). Sunday is domingo (Lord\u2019s day) and the remaining days count up from there."
    ),
    TimeCategory(
        "Months of the Year",
        listOf(
            TimeEntry("Janeiro", "January"),
            TimeEntry("Fevereiro", "February"),
            TimeEntry("Março", "March"),
            TimeEntry("Abril", "April"),
            TimeEntry("Maio", "May"),
            TimeEntry("Junho", "June"),
            TimeEntry("Julho", "July"),
            TimeEntry("Agosto", "August"),
            TimeEntry("Setembro", "September"),
            TimeEntry("Outubro", "October"),
            TimeEntry("Novembro", "November"),
            TimeEntry("Dezembro", "December")
        )
    ),
    TimeCategory(
        "Ago & In... (Há / Daqui a)",
        listOf(
            TimeEntry("há alguns minutos / há uns minutos", "a few minutes ago"),
            TimeEntry("há cinco minutos", "5 minutes ago"),
            TimeEntry("há um ano", "a year ago"),
            TimeEntry("daqui a uma hora", "in an hour"),
            TimeEntry("daqui a meia hora", "in half an hour"),
            TimeEntry("daqui a quinze minutos", "in 15 minutes"),
            TimeEntry("daqui a cinco minutos", "in 5 minutes"),
            TimeEntry("daqui a três dias", "in 3 days")
        ),
        headerNote = "Há (\"ago\", literally \"there is/has been\") looks back in time; daqui a (\"from here to...\") looks forward. European Portuguese uses há — not the Brazilian faz."
    ),
    TimeCategory(
        "During, After, Before, While",
        listOf(
            TimeEntry("durante o jogo", "during the game", "durante + noun"),
            TimeEntry(
                "Depois de ter consertado o telemóvel, recebi uma chamada.",
                "After I fixed the phone, I got a phone call.",
                "depois de + personal compound infinitive (ter + past participle) marks the fixing as completed before the call"
            ),
            TimeEntry(
                "Antes de teres ligado, eu estava a fazer os trabalhos de casa.",
                "Before you called, I was doing my homework.",
                "antes de + personal infinitive (teres ligado) — the subject changes (tu → eu), so the infinitive is inflected; contrast with antes que + subjunctive"
            ),
            TimeEntry(
                "enquanto via o jogo",
                "while watching the game",
                "enquanto introduces a full clause with a conjugated verb, unlike durante which takes a noun"
            )
        ),
        stacked = true
    ),
    TimeCategory(
        "Saying the Full Date",
        listOf(
            TimeEntry("Hoje é sábado, quatro de julho de dois mil e vinte e seis.", "Today is Saturday, the 4th of July, 2026."),
            TimeEntry("Hoje é dia um / primeiro de janeiro.", "Today is the 1st of January."),
            TimeEntry("Que dia é hoje? — Hoje é [dia da semana], [dia] de [mês].", "What day is it today? — Today is [weekday], [day] of [month].")
        ),
        headerNote = "Pattern: [dia da semana], [dia cardinal] de [mês] de [ano]. Portuguese uses cardinal numbers for the day of the month — \"quatro de julho\", not \"quarto de julho\". The 1st can be said as either um or the more formal/traditional primeiro (primeiro de janeiro for New Year's Day is very common).",
        stacked = true
    ),
    TimeCategory(
        "Telling Time: Hours",
        listOf(
            TimeEntry("São nove horas da manhã.", "It's 9 o'clock in the morning."),
            TimeEntry(
                "São quatro horas da tarde. / São dezasseis horas.",
                "It's 4 o'clock in the afternoon. / It's 16:00.",
                "Pick one register, don't mix them: 12h + da tarde (informal) OR bare 24h (formal). \"Dezasseis horas da tarde\" is redundant/unnatural — the 24h number already says afternoon."
            ),
            TimeEntry(
                "É uma hora da madrugada.",
                "It's 1 o'clock in the morning (the small hours).",
                "Not \"da noite\" — noite covers evening to midnight (~6pm–meia-noite), while madrugada covers midnight to dawn (~meia-noite–6am). 1am falls in madrugada."
            ),
            TimeEntry("São dez horas da noite.", "It's 10 o'clock at night."),
            TimeEntry("É meio-dia.", "It's noon.", "Always é, never são — meio-dia is grammatically singular."),
            TimeEntry("É meia-noite.", "It's midnight.", "Always é, never são — meia-noite is grammatically singular.")
        ),
        headerNote = "É is used only for uma hora (1 o'clock) and for meio-dia/meia-noite; são is used for every other hour (duas horas, três horas...). Portuguese has four day-period words instead of am/pm: da manhã (~6am–noon), da tarde (~noon–6pm), da noite (~6pm–midnight), da madrugada (midnight–~6am). Portugal uses the 24-hour clock often, even semi-formally (schedules, news) — more so than Brazil.",
        stacked = true
    ),
    TimeCategory(
        "Telling Time: Minutes",
        listOf(
            TimeEntry(
                "São quatro e cinco (da tarde). / São dezasseis e cinco.",
                "It's 4:05 (pm). / It's 16:05."
            ),
            TimeEntry(
                "São quatro e um quarto. / São quatro e quinze.",
                "It's quarter past four. / It's 4:15.",
                "Both um quarto and quinze are used interchangeably for the quarter-hour."
            ),
            TimeEntry(
                "São quatro e meia. / São dezasseis e trinta.",
                "It's half past four. / It's 4:30."
            ),
            TimeEntry(
                "São quatro e quarenta e cinco. / É um quarto para as cinco. / São cinco menos um quarto.",
                "It's 4:45. / It's a quarter to five.",
                "Three valid options, not formal-vs-informal: straight addition (always safe); um quarto/quinze para as (South, incl. Lisbon); menos um quarto/quinze (North). Straight addition is what timetables and formal announcements use."
            ),
            TimeEntry(
                "São quatro e cinquenta e cinco. / São cinco para as cinco. / São cinco menos cinco.",
                "It's 4:55. / It's five to five.",
                "Same three-way choice as above: e cinquenta e cinco (straight), para as (South), menos (North)."
            )
        ),
        headerNote = "Past the hour: e + minutes (e dez, e vinte...). Approaching the next hour, there are two competing regional conventions for \"to\" phrasing — para as in the South, menos in the North — both understood nationwide; straight 24h-style addition sidesteps the split entirely and is standard in formal/written contexts.",
        stacked = true
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeExpressionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Time Expressions") },
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
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(timeCategories) { category ->
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = category.title,
                            style = MaterialTheme.typography.titleMedium
                        )
                        if (category.headerNote.isNotEmpty()) {
                            Text(
                                text = category.headerNote,
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }
                        category.entries.forEach { entry ->
                            Column(modifier = Modifier.fillMaxWidth()) {
                                if (category.stacked) {
                                    Text(
                                        text = entry.pt,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontStyle = FontStyle.Italic,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Text(
                                        text = entry.en,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                } else {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = entry.pt,
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontStyle = FontStyle.Italic,
                                            modifier = Modifier.weight(1f)
                                        )
                                        Text(
                                            text = entry.en,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                                if (entry.note.isNotEmpty()) {
                                    Text(
                                        text = entry.note,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
