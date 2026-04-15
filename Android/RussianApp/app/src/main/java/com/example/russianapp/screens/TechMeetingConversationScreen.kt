package com.example.russianapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class TechMeetingLine(val speaker: String, val ru: String, val en: String)

private val techMeetingDialogue = listOf(
    TechMeetingLine("A", "Итак, давайте обсудим проект. Нам нужно определить, кто что делает.", "So, let's discuss the project. We need to determine who does what."),
    TechMeetingLine("B", "Согласен. Моя команда специализируется на бэкенде. Мы возьмём API и базу данных.", "Agreed. My team specializes in the backend. We'll take the API and the database."),
    TechMeetingLine("A", "Отлично. Тогда наша команда займётся фронтендом — интерфейс, авторизация пользователей и мобильное приложение.", "Great. Then our team will handle the frontend — the interface, user authorization, and the mobile app."),
    TechMeetingLine("B", "Хороший план. Когда, по-вашему, нам следует провести интеграцию?", "Good plan. When do you think we should do the integration?"),
    TechMeetingLine("A", "Предлагаю интеграцию через шесть недель. К тому моменту у нас будут готовы основные компоненты.", "I suggest integration in six weeks. By then we'll have the main components ready."),
    TechMeetingLine("B", "Шесть недель — это реально. Нам нужно заранее согласовать формат API, чтобы обе команды работали синхронно.", "Six weeks is realistic. We need to agree on the API format in advance so both teams work in sync."),
    TechMeetingLine("A", "Совершенно верно. Давайте на следующей неделе проведём встречу по документации API.", "Absolutely right. Let's have a meeting about the API documentation next week."),
    TechMeetingLine("B", "Договорились. А как насчёт тестирования? Кто отвечает за юнит-тесты?", "Agreed. And what about testing? Who is responsible for unit tests?"),
    TechMeetingLine("A", "Каждая команда пишет юнит-тесты для своей части. Интеграционные тесты проводим совместно после интеграции.", "Each team writes unit tests for their part. We run integration tests together after the integration."),
    TechMeetingLine("B", "Разумно. Нужно также предусмотреть нагрузочное тестирование перед релизом.", "Reasonable. We also need to plan for load testing before the release."),
    TechMeetingLine("A", "Да, запланируем нагрузочные тесты на неделю перед финальным дедлайном. Кстати, когда у нас дедлайн?", "Yes, let's plan load tests for the week before the final deadline. By the way, when is our deadline?"),
    TechMeetingLine("B", "Релиз запланирован через три месяца. Значит, у нас есть шесть недель на разработку, две недели на интеграцию и ещё четыре на тестирование и исправление ошибок.", "The release is planned in three months. So we have six weeks for development, two weeks for integration, and another four for testing and bug fixes."),
    TechMeetingLine("A", "Звучит разумно. Давайте проводить еженедельные совещания, чтобы отслеживать прогресс.", "Sounds reasonable. Let's have weekly meetings to track progress."),
    TechMeetingLine("B", "Согласен. И если у кого-то возникнут блокирующие проблемы, сразу пишите — не ждите до следующего совещания.", "Agreed. And if anyone has blocking issues, write immediately — don't wait until the next meeting."),
    TechMeetingLine("A", "Отличный подход. Думаю, у нас есть всё для успешного проекта.", "Excellent approach. I think we have everything for a successful project."),
    TechMeetingLine("B", "Я тоже настроен оптимистично. Удачи нашим командам!", "I'm also optimistic. Good luck to our teams!")
)

@Composable
private fun TechMeetingCard(line: TechMeetingLine) {
    val containerColor = when (line.speaker) {
        "A" -> MaterialTheme.colorScheme.secondaryContainer
        else -> MaterialTheme.colorScheme.surfaceVariant
    }
    val labelColor = when (line.speaker) {
        "A" -> MaterialTheme.colorScheme.onSecondaryContainer
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }
    val speakerLabel = when (line.speaker) {
        "A" -> "A (team leader)"
        else -> "B (team leader)"
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = speakerLabel,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = labelColor
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = line.ru,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = line.en,
                style = MaterialTheme.typography.bodyMedium,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TechMeetingConversationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tech Meeting") },
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
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Text(
                    text = "Planning a Software Project",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            items(techMeetingDialogue) { line ->
                TechMeetingCard(line = line)
                Spacer(modifier = Modifier.height(6.dp))
            }

            item { Spacer(modifier = Modifier.height(10.dp)) }
        }
    }
}
