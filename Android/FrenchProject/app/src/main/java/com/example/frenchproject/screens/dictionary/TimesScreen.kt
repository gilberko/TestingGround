package com.example.frenchproject.screens.dictionary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frenchproject.ui.theme.FrenchBlue
import com.example.frenchproject.ui.theme.FrenchNavy

private data class TimeEntry(val french: String, val english: String, val note: String = "")

private val daysOfWeek = listOf(
    TimeEntry("lundi", "Monday"),
    TimeEntry("mardi", "Tuesday"),
    TimeEntry("mercredi", "Wednesday"),
    TimeEntry("jeudi", "Thursday"),
    TimeEntry("vendredi", "Friday"),
    TimeEntry("samedi", "Saturday"),
    TimeEntry("dimanche", "Sunday")
)

private val months = listOf(
    TimeEntry("janvier", "January"),
    TimeEntry("février", "February"),
    TimeEntry("mars", "March"),
    TimeEntry("avril", "April"),
    TimeEntry("mai", "May"),
    TimeEntry("juin", "June"),
    TimeEntry("juillet", "July"),
    TimeEntry("août", "August"),
    TimeEntry("septembre", "September"),
    TimeEntry("octobre", "October"),
    TimeEntry("novembre", "November"),
    TimeEntry("décembre", "December")
)

private val partsOfDay = listOf(
    TimeEntry("le matin", "the morning", "Je travaille le matin. — I work in the morning."),
    TimeEntry("midi", "noon / midday", "On mange à midi. — We eat at noon."),
    TimeEntry("l'après-midi", "the afternoon", "Elle fait du sport l'après-midi. — She exercises in the afternoon."),
    TimeEntry("le soir", "the evening", "On regarde la télé le soir. — We watch TV in the evening."),
    TimeEntry("la nuit", "the night", "Il fait froid la nuit. — It's cold at night.")
)

private val tellingTime = listOf(
    TimeEntry("Il est dix heures du matin.", "It is 10:00 a.m."),
    TimeEntry("Il est dix heures du soir.", "It is 10:00 p.m."),
    TimeEntry("Il est dix heures six.", "It is 10:06."),
    TimeEntry("Il est dix heures et quart.", "It is 10:15. (quarter past ten)"),
    TimeEntry("Il est dix heures et demie.", "It is 10:30. (half past ten)"),
    TimeEntry("Il est onze heures moins le quart.", "It is 10:45. (quarter to eleven)"),
    TimeEntry("Il est onze heures moins cinq.", "It is 10:55. (five to eleven)"),
    TimeEntry("Il est midi.", "It is noon."),
    TimeEntry("Il est minuit.", "It is midnight.")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Time & Dates", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = FrenchBlue)
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
                    text = "Days, months, and parts of the day are all lowercase in French. The week starts on Monday (lundi), not Sunday.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // Days of the week
            item { TimeSectionHeader("Days of the Week") }
            items(daysOfWeek.size) { i -> TimeEntryCard(daysOfWeek[i]) }
            item {
                TimeRuleCard(
                    title = "Using days",
                    body = "No article for habitual days:\n" +
                        "Le lundi, je travaille. — On Mondays, I work.\n\n" +
                        "No article for a specific upcoming day:\n" +
                        "Je pars lundi. — I'm leaving on Monday.\n\n" +
                        "ce + day = this coming [day]:\n" +
                        "ce vendredi — this Friday"
                )
            }

            // Months
            item {
                Spacer(modifier = Modifier.height(8.dp))
                TimeSectionHeader("Months")
            }
            items(months.size) { i -> TimeEntryCard(months[i]) }
            item {
                TimeRuleCard(
                    title = "Using months",
                    body = "No article before a month in general use:\n" +
                        "en janvier — in January\n" +
                        "au mois de mars — in the month of March\n\n" +
                        "Months are always lowercase in French."
                )
            }

            // Parts of the day
            item {
                Spacer(modifier = Modifier.height(8.dp))
                TimeSectionHeader("Parts of the Day")
            }
            items(partsOfDay.size) { i -> TimeEntryCard(partsOfDay[i]) }

            // Asking the time
            item {
                Spacer(modifier = Modifier.height(8.dp))
                TimeSectionHeader("Asking the Time")
            }
            item {
                TimeRuleCard(
                    title = "How to ask",
                    body = "Formal: Quelle heure est-il ?\n" +
                        "Informal: Il est quelle heure ?\n\n" +
                        "Both mean \"What time is it?\" — either is correct in everyday speech."
                )
            }

            // Telling the time
            item {
                Spacer(modifier = Modifier.height(8.dp))
                TimeSectionHeader("Telling the Time")
            }
            items(tellingTime.size) { i -> TimeEntryCard(tellingTime[i]) }
            item {
                TimeRuleCard(
                    title = "Key expressions",
                    body = "et quart — quarter past (+ 15 min)\n" +
                        "et demie — half past (+ 30 min) — demie because heure is feminine\n" +
                        "moins le quart — quarter to (- 15 min)\n" +
                        "moins cinq — five to (- 5 min)\n\n" +
                        "Exception: midi et demi / minuit et demi (no e on demi)\n" +
                        "because midi and minuit are masculine nouns.\n\n" +
                        "du matin = a.m.  |  du soir = p.m. (after noon)\n" +
                        "de l'après-midi = p.m. (afternoon, up to ~6pm)"
                )
            }

            // Asking the date
            item {
                Spacer(modifier = Modifier.height(8.dp))
                TimeSectionHeader("Asking the Date")
            }
            item {
                TimeRuleCard(
                    title = "How to ask",
                    body = "Quelle est la date aujourd'hui ? — What is today's date?\n" +
                        "On est le combien ? — What's the date? (informal)"
                )
            }

            // Telling the date
            item {
                Spacer(modifier = Modifier.height(8.dp))
                TimeSectionHeader("Telling the Date")
            }
            item {
                TimeRuleCard(
                    title = "How to answer",
                    body = "On est le [number] [month].\n" +
                        "On est le 14 avril. — It's April 14th.\n\n" +
                        "Nous sommes le [day] [number] [month].\n" +
                        "Nous sommes le lundi 14 avril. — It's Monday, April 14th.\n\n" +
                        "Use cardinal numbers (14, 21, 25…) for all dates\n" +
                        "EXCEPT the 1st — always use le premier:\n" +
                        "On est le premier mai. — It's May 1st.\n" +
                        "(Never say le un — it is always le premier.)"
                )
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun TimeSectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold,
        color = FrenchBlue,
        modifier = Modifier.padding(bottom = 4.dp)
    )
    HorizontalDivider(color = FrenchBlue.copy(alpha = 0.35f))
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
private fun TimeRuleCard(title: String, body: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = FrenchBlue,
                fontSize = 13.sp,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Text(
                text = body,
                style = MaterialTheme.typography.bodySmall,
                color = FrenchNavy,
                lineHeight = 19.sp
            )
        }
    }
}

@Composable
private fun TimeEntryCard(entry: TimeEntry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 9.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = entry.french,
                    fontWeight = FontWeight.Bold,
                    color = FrenchBlue,
                    fontSize = 14.sp,
                    modifier = Modifier.width(200.dp)
                )
                Text(
                    text = entry.english,
                    style = MaterialTheme.typography.bodyMedium,
                    color = FrenchNavy
                )
            }
            if (entry.note.isNotEmpty()) {
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = entry.note,
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = FrenchBlue,
                    lineHeight = 18.sp
                )
            }
        }
    }
}
