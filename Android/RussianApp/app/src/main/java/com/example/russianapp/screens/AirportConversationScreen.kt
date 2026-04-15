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

private data class AirportLine(val speaker: String, val ru: String, val en: String)

private val airportDialogue = listOf(
    AirportLine("A", "Простите! Можете мне помочь? Я не могу найти свой выход!", "Excuse me! Can you help me? I can't find my gate!"),
    AirportLine("E", "Конечно! Не волнуйтесь, у вас ещё есть время. Какой у вас номер выхода?", "Of course! Don't worry, you still have time. What is your gate number?"),
    AirportLine("A", "Выход 47Б. Я уже 20 минут хожу кругами!", "Gate 47B. I've been walking in circles for 20 minutes!"),
    AirportLine("E", "Понимаю вас! Выход 47Б находится в терминале Б, а вы сейчас в терминале А. Вам нужно пройти немного дальше.", "I understand you! Gate 47B is in Terminal B, and you're currently in Terminal A. You need to walk a bit further."),
    AirportLine("A", "О нет. Это далеко?", "Oh no. Is it far?"),
    AirportLine("E", "Нет, совсем близко! Идите прямо по этому коридору до конца, затем поверните направо. Увидите указатели «Терминал Б».", "No, quite close! Go straight down this corridor to the end, then turn right. You'll see signs saying 'Terminal B'."),
    AirportLine("A", "Хорошо. А там есть эскалатор или лифт?", "Okay. Is there an escalator or elevator there?"),
    AirportLine("E", "Да, и то и другое. Вам на второй этаж. Выход 47Б будет справа от лифта.", "Yes, both. You need the second floor. Gate 47B will be to the right of the elevator."),
    AirportLine("A", "Спасибо! Сколько у меня времени?", "Thank you! How much time do I have?"),
    AirportLine("E", "Посадка обычно начинается за 40 минут до вылета. Проверьте ваш посадочный талон.", "Boarding usually begins 40 minutes before departure. Check your boarding pass."),
    AirportLine("A", "Вылет через час. Значит, у меня есть немного времени. Кстати, где здесь можно купить что-нибудь поесть?", "The flight is in an hour. So I have a little time. By the way, where can I get something to eat here?"),
    AirportLine("E", "Фуд-корт находится на этом же этаже, прямо перед входом в терминал Б. Там есть несколько кафе и ресторанов.", "The food court is on this same floor, right before the entrance to Terminal B. There are several cafés and restaurants there."),
    AirportLine("A", "Отлично! А есть ли здесь книжный магазин? Я хочу купить книгу на полёт.", "Great! Is there a bookstore here? I want to buy a book for the flight."),
    AirportLine("E", "Да, есть! Книжный магазин «Читай-город» находится рядом с фуд-кортом, слева. Там большой выбор книг и журналов.", "Yes, there is! The 'Chitay-Gorod' bookstore is next to the food court, on the left. They have a large selection of books and magazines."),
    AirportLine("A", "Замечательно. Я успею зайти туда и поесть?", "Wonderful. Will I have time to stop there and eat?"),
    AirportLine("E", "Если поторопитесь — успеете! Но не задерживайтесь больше 30 минут, чтобы успеть на посадку.", "If you hurry — you'll make it! But don't linger more than 30 minutes so you catch the boarding."),
    AirportLine("A", "Понял. Большое спасибо за помощь! Вы меня успокоили.", "Understood. Thank you very much for your help! You calmed me down."),
    AirportLine("E", "Пожалуйста! Приятного полёта!", "You're welcome! Have a pleasant flight!")
)

@Composable
private fun AirportCard(line: AirportLine) {
    val containerColor = when (line.speaker) {
        "A" -> MaterialTheme.colorScheme.secondaryContainer
        else -> MaterialTheme.colorScheme.tertiaryContainer
    }
    val labelColor = when (line.speaker) {
        "A" -> MaterialTheme.colorScheme.onSecondaryContainer
        else -> MaterialTheme.colorScheme.onTertiaryContainer
    }
    val speakerLabel = when (line.speaker) {
        "A" -> "A (traveler)"
        else -> "E (employee)"
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
fun AirportConversationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("At The Airport") },
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
                    text = "Finding Your Gate",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            items(airportDialogue) { line ->
                AirportCard(line = line)
                Spacer(modifier = Modifier.height(6.dp))
            }

            item { Spacer(modifier = Modifier.height(10.dp)) }
        }
    }
}
