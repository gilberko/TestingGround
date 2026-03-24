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

private data class TimeEntry(val pt: String, val en: String)
private data class TimeCategory(val title: String, val entries: List<TimeEntry>)

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
            TimeEntry("todos os dias", "every day")
        )
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
                        category.entries.forEach { entry ->
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
                    }
                }
            }
        }
    }
}
