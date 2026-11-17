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


private val timeCategories = listOf(
    DictCategory(
        "Today & Now",
        listOf(
            DictWord("hoje", "today"),
            DictWord("agora", "now"),
            DictWord("agora mesmo", "right now"),
            DictWord("hoje de manhã", "this morning"),
            DictWord("hoje à tarde", "this afternoon"),
            DictWord("hoje à noite", "tonight")
        )
    ),
    DictCategory(
        "Past",
        listOf(
            DictWord("ontem", "yesterday"),
            DictWord("anteontem", "the day before yesterday"),
            DictWord("ontem de manhã", "yesterday morning"),
            DictWord("ontem à noite", "last night")
        )
    ),
    DictCategory(
        "Future",
        listOf(
            DictWord("amanhã", "tomorrow"),
            DictWord("depois de amanhã", "the day after tomorrow"),
            DictWord("amanhã de manhã", "tomorrow morning"),
            DictWord("amanhã à noite", "tomorrow night")
        )
    ),
    DictCategory(
        "Parts of the Day",
        listOf(
            DictWord("manhã", "morning"),
            DictWord("meio-dia", "noon"),
            DictWord("tarde", "afternoon / evening"),
            DictWord("noite", "night"),
            DictWord("meia-noite", "midnight"),
            DictWord("de manhã", "in the morning"),
            DictWord("à tarde", "in the afternoon"),
            DictWord("à noite", "at night")
        )
    ),
    DictCategory(
        "Time Periods",
        listOf(
            DictWord("um dia", "a day"),
            DictWord("uma semana", "a week"),
            DictWord("um mês", "a month"),
            DictWord("um ano", "a year"),
            DictWord("uma hora", "an hour"),
            DictWord("um minuto", "a minute"),
            DictWord("esta semana", "this week"),
            DictWord("este mês", "this month"),
            DictWord("este ano", "this year"),
            DictWord("todos os dias", "every day"),
            DictWord("semana passada", "last week"),
            DictWord("próxima semana / semana que vem", "next week")
        )
    ),
    DictCategory(
        "Days of the Week",
        listOf(
            DictWord("domingo", "Sunday", "From Latin Dominica — Lord's day"),
            DictWord("segunda-feira", "Monday", "From Latin feria secunda — second feast/fair day"),
            DictWord("terça-feira", "Tuesday", "From Latin feria tertia — third fair day"),
            DictWord("quarta-feira", "Wednesday", "From Latin feria quarta — fourth fair day"),
            DictWord("quinta-feira", "Thursday", "From Latin feria quinta — fifth fair day"),
            DictWord("sexta-feira", "Friday", "From Latin feria sexta — sixth fair day"),
            DictWord("sábado", "Saturday", "From Hebrew Shabbat (Sabbath)")
        ),
        headerNote = "Portuguese (and Galician) is unique among Romance languages: instead of planet names (French lundi = Moon, mardi = Mars\u2026), weekdays are numbered as feiras (feast/market days). Sunday is domingo (Lord\u2019s day) and the remaining days count up from there."
    ),
    DictCategory(
        "Months of the Year",
        listOf(
            DictWord("Janeiro", "January"),
            DictWord("Fevereiro", "February"),
            DictWord("Março", "March"),
            DictWord("Abril", "April"),
            DictWord("Maio", "May"),
            DictWord("Junho", "June"),
            DictWord("Julho", "July"),
            DictWord("Agosto", "August"),
            DictWord("Setembro", "September"),
            DictWord("Outubro", "October"),
            DictWord("Novembro", "November"),
            DictWord("Dezembro", "December")
        )
    ),
    DictCategory(
        "Ago & In... (Há / Daqui a)",
        listOf(
            DictWord("há alguns minutos / há uns minutos", "a few minutes ago"),
            DictWord("há cinco minutos", "5 minutes ago"),
            DictWord("há um ano", "a year ago"),
            DictWord("daqui a uma hora", "in an hour"),
            DictWord("daqui a meia hora", "in half an hour"),
            DictWord("daqui a quinze minutos", "in 15 minutes"),
            DictWord("daqui a cinco minutos", "in 5 minutes"),
            DictWord("daqui a três dias", "in 3 days")
        ),
        headerNote = "Há (\"ago\", literally \"there is/has been\") looks back in time; daqui a (\"from here to...\") looks forward. European Portuguese uses há — not the Brazilian faz."
    ),
    DictCategory(
        "During, After, Before, While",
        listOf(
            DictWord("durante o jogo", "during the game", "durante + noun"),
            DictWord(
                "Depois de ter consertado o telemóvel, recebi uma chamada.",
                "After I fixed the phone, I got a phone call.",
                "depois de + personal compound infinitive (ter + past participle) marks the fixing as completed before the call"
            ),
            DictWord(
                "Antes de teres ligado, eu estava a fazer os trabalhos de casa.",
                "Before you called, I was doing my homework.",
                "antes de + personal infinitive (teres ligado) — the subject changes (tu → eu), so the infinitive is inflected; contrast with antes que + subjunctive"
            ),
            DictWord(
                "enquanto via o jogo",
                "while watching the game",
                "enquanto introduces a full clause with a conjugated verb, unlike durante which takes a noun"
            )
        ),
        stacked = true
    ),
    DictCategory(
        "Saying the Full Date",
        listOf(
            DictWord("Hoje é sábado, quatro de julho de dois mil e vinte e seis.", "Today is Saturday, the 4th of July, 2026."),
            DictWord("Hoje é dia um / primeiro de janeiro.", "Today is the 1st of January."),
            DictWord("Que dia é hoje? — Hoje é [dia da semana], [dia] de [mês].", "What day is it today? — Today is [weekday], [day] of [month].")
        ),
        headerNote = "Pattern: [dia da semana], [dia cardinal] de [mês] de [ano]. Portuguese uses cardinal numbers for the day of the month — \"quatro de julho\", not \"quarto de julho\". The 1st can be said as either um or the more formal/traditional primeiro (primeiro de janeiro for New Year's Day is very common).",
        stacked = true
    ),
    DictCategory(
        "Telling Time: Hours",
        listOf(
            DictWord("São nove horas da manhã.", "It's 9 o'clock in the morning."),
            DictWord(
                "São quatro horas da tarde. / São dezasseis horas.",
                "It's 4 o'clock in the afternoon. / It's 16:00.",
                "Pick one register, don't mix them: 12h + da tarde (informal) OR bare 24h (formal). \"Dezasseis horas da tarde\" is redundant/unnatural — the 24h number already says afternoon."
            ),
            DictWord(
                "É uma hora da madrugada.",
                "It's 1 o'clock in the morning (the small hours).",
                "Not \"da noite\" — noite covers evening to midnight (~6pm–meia-noite), while madrugada covers midnight to dawn (~meia-noite–6am). 1am falls in madrugada."
            ),
            DictWord("São dez horas da noite.", "It's 10 o'clock at night."),
            DictWord("É meio-dia.", "It's noon.", "Always é, never são — meio-dia is grammatically singular."),
            DictWord("É meia-noite.", "It's midnight.", "Always é, never são — meia-noite is grammatically singular.")
        ),
        headerNote = "É is used only for uma hora (1 o'clock) and for meio-dia/meia-noite; são is used for every other hour (duas horas, três horas...). Portuguese has four day-period words instead of am/pm: da manhã (~6am–noon), da tarde (~noon–6pm), da noite (~6pm–midnight), da madrugada (midnight–~6am). Portugal uses the 24-hour clock often, even semi-formally (schedules, news) — more so than Brazil.",
        stacked = true
    ),
    DictCategory(
        "Telling Time: Minutes",
        listOf(
            DictWord(
                "São quatro e cinco (da tarde). / São dezasseis e cinco.",
                "It's 4:05 (pm). / It's 16:05."
            ),
            DictWord(
                "São quatro e um quarto. / São quatro e quinze.",
                "It's quarter past four. / It's 4:15.",
                "Both um quarto and quinze are used interchangeably for the quarter-hour."
            ),
            DictWord(
                "São quatro e meia. / São dezasseis e trinta.",
                "It's half past four. / It's 4:30."
            ),
            DictWord(
                "São quatro e quarenta e cinco. / É um quarto para as cinco. / São cinco menos um quarto.",
                "It's 4:45. / It's a quarter to five.",
                "Three valid options, not formal-vs-informal: straight addition (always safe); um quarto/quinze para as (South, incl. Lisbon); menos um quarto/quinze (North). Straight addition is what timetables and formal announcements use."
            ),
            DictWord(
                "São quatro e cinquenta e cinco. / São cinco para as cinco. / São cinco menos cinco.",
                "It's 4:55. / It's five to five.",
                "Same three-way choice as above: e cinquenta e cinco (straight), para as (South), menos (North)."
            )
        ),
        headerNote = "Past the hour: e + minutes (e dez, e vinte...). Approaching the next hour, there are two competing regional conventions for \"to\" phrasing — para as in the South, menos in the North — both understood nationwide; straight 24h-style addition sidesteps the split entirely and is standard in formal/written contexts.",
        stacked = true
    )
)

@Composable
fun TimeExpressionsScreen(onBack: () -> Unit) {
    DictScreenScaffold(title = "Time Expressions", onBack = onBack) {
        items(timeCategories) { DictCategoryCard(it) }
    }
}
