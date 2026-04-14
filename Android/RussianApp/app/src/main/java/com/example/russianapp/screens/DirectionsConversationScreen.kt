package com.example.russianapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class DialogueLine(val speaker: String, val ru: String, val en: String)
private data class ConversationSection(val heading: String?, val lines: List<DialogueLine>)

private val conversationSections = listOf(
    ConversationSection(
        heading = "На автобусе — By Bus",
        lines = listOf(
            DialogueLine("A", "Извините, вы не могли бы помочь мне? Как добраться до стадиона?", "Excuse me, could you help me? How do I get to the stadium?"),
            DialogueLine("B", "Конечно! Вы можете поехать на автобусе.", "Of course! You can take the bus."),
            DialogueLine("A", "А как мне найти автобусную остановку?", "And how do I find the bus stop?"),
            DialogueLine("B", "Идите прямо по этой улице до угла, потом повернёте налево. Остановка будет справа.", "Go straight down this street to the corner, then turn left. The stop will be on the right."),
            DialogueLine("A", "Хорошо, понятно. А какой маршрут мне нужен?", "Good, I understand. What route do I need?"),
            DialogueLine("B", "Садитесь на автобус номер двадцать три. Езжайте пять остановок.", "Take bus number 23. Ride five stops."),
            DialogueLine("A", "И где мне выходить?", "And where do I get off?"),
            DialogueLine("B", "Выходите на остановке «Спортивная». Оттуда стадион будет виден — идти минут пять пешком.", "Get off at the «Sportivnaya» stop. From there the stadium will be visible — about a five-minute walk.")
        )
    ),
    ConversationSection(
        heading = "На метро — By Metro",
        lines = listOf(
            DialogueLine("B", "Есть ещё вариант — метро.", "There's another option — the metro."),
            DialogueLine("A", "О, хорошо! Как мне добраться до станции метро?", "Oh, good! How do I get to the metro station?"),
            DialogueLine("B", "Идите прямо до конца улицы, потом повернёте направо. Станция «Центральная» будет слева.", "Go straight to the end of the street, then turn right. The «Tsentralnaya» station will be on the left."),
            DialogueLine("A", "Понял. Какую линию брать?", "Got it. Which line to take?"),
            DialogueLine("B", "Садитесь на красную линию в направлении «Южная».", "Take the red line in the direction of «Yuzhnaya»."),
            DialogueLine("A", "Сколько остановок?", "How many stops?"),
            DialogueLine("B", "Три остановки. Выходите на «Стадионной».", "Three stops. Get off at «Stadionnaya»."),
            DialogueLine("A", "И как оттуда до стадиона?", "And how do I get to the stadium from there?"),
            DialogueLine("B", "Выйдите из метро и идите прямо. Стадион будет прямо перед вами — минуты три пешком.", "Exit the metro and go straight. The stadium will be right in front of you — about three minutes on foot.")
        )
    ),
    ConversationSection(
        heading = "На такси — By Taxi",
        lines = listOf(
            DialogueLine("B", "И последний вариант — такси. Это самый простой способ.", "And the last option — taxi. That's the easiest way."),
            DialogueLine("A", "Это дорого?", "Is it expensive?"),
            DialogueLine("B", "Не очень. Вызовите такси через приложение, скажите водителю: «На стадион, пожалуйста». Он довезёт вас прямо до входа.", "Not really. Call a taxi through the app, tell the driver: «To the stadium, please». He'll take you right to the entrance."),
            DialogueLine("A", "Спасибо большое! Вы очень помогли!", "Thank you very much! You've been very helpful!"),
            DialogueLine("B", "Пожалуйста! Удачи!", "You're welcome! Good luck!")
        )
    )
)

@Composable
private fun DialogueCard(line: DialogueLine) {
    val isSpeakerA = line.speaker == "A"
    val containerColor = if (isSpeakerA)
        MaterialTheme.colorScheme.secondaryContainer
    else
        MaterialTheme.colorScheme.surfaceVariant

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = line.speaker,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = if (isSpeakerA)
                    MaterialTheme.colorScheme.onSecondaryContainer
                else
                    MaterialTheme.colorScheme.onSurfaceVariant
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
fun DirectionsConversationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Asking for Directions") },
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
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp)
        ) {
            item {
                Text(
                    text = "Getting to the Stadium",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            conversationSections.forEach { section ->
                if (section.heading != null) {
                    item {
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
                        ) {
                            HorizontalDivider(modifier = Modifier.weight(1f))
                            Text(
                                text = section.heading,
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            HorizontalDivider(modifier = Modifier.weight(1f))
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }

                items(section.lines) { line ->
                    Spacer(modifier = Modifier.height(6.dp))
                    DialogueCard(line = line)
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}
