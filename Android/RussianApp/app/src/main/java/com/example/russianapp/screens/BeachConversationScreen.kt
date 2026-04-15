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

private data class BeachLine(val speaker: String, val ru: String, val en: String)

private val beachDialogue = listOf(
    BeachLine("A", "Извините, вы здесь дежурный спасатель?", "Excuse me, are you the lifeguard on duty here?"),
    BeachLine("L", "Да, я дежурный спасатель. Чем могу помочь?", "Yes, I'm the on-duty lifeguard. How can I help?"),
    BeachLine("B", "Мы хотим купаться. Какова обстановка сегодня?", "We want to swim. What are the conditions today?"),
    BeachLine("L", "Сегодня условия умеренные. Есть несколько важных моментов.", "Today conditions are moderate. There are a few important points."),
    BeachLine("A", "Есть ли медузы в воде?", "Are there jellyfish in the water?"),
    BeachLine("L", "Да, немного медуз замечено сегодня утром, но не очень много. Будьте осторожны.", "Yes, a few jellyfish were spotted this morning, but not too many. Be careful."),
    BeachLine("B", "А как насчёт ветра? Он сильный?", "What about the wind? Is it strong?"),
    BeachLine("L", "Ветер умеренный, около 15 километров в час. Не слишком сильный, но волны немного выше обычного.", "The wind is moderate, about 15 kilometers per hour. Not too strong, but the waves are a bit higher than usual."),
    BeachLine("A", "Волны большие? Нам купаться безопасно?", "Are the waves big? Is it safe for us to swim?"),
    BeachLine("L", "Волны около метра высотой. Для взрослых безопасно, но детям рекомендую купаться только у берега.", "The waves are about a meter high. Safe for adults, but I recommend children only swim near the shore."),
    BeachLine("B", "Флаг жёлтый. Что это означает?", "The flag is yellow. What does that mean?"),
    BeachLine("L", "Жёлтый флаг означает умеренную опасность — плавайте осторожно. Красный — купаться запрещено. Зелёный — всё безопасно.", "A yellow flag means moderate danger — swim with caution. Red means swimming is prohibited. Green means everything is safe."),
    BeachLine("A", "Есть ли опасные течения сегодня?", "Are there any dangerous currents today?"),
    BeachLine("L", "Сегодня слабые течения. Главное — не заплывать за буйки.", "The currents are weak today. The main thing is not to swim past the buoys."),
    BeachLine("B", "Понятно. Есть ли что-то ещё, о чём нам следует знать?", "Understood. Is there anything else we should know?"),
    BeachLine("L", "Наносите солнцезащитный крем — ультрафиолет сегодня очень сильный. И если почувствуете что-то необычное в воде, сразу выходите.", "Apply sunscreen — the UV index is very high today. And if you feel anything unusual in the water, get out immediately."),
    BeachLine("A", "Спасибо за информацию!", "Thank you for the information!"),
    BeachLine("L", "Пожалуйста! Приятного отдыха и купайтесь осторожно!", "You're welcome! Enjoy your time and swim safely!")
)

@Composable
private fun BeachCard(line: BeachLine) {
    val containerColor = when (line.speaker) {
        "A" -> MaterialTheme.colorScheme.secondaryContainer
        "B" -> MaterialTheme.colorScheme.surfaceVariant
        else -> MaterialTheme.colorScheme.tertiaryContainer
    }
    val labelColor = when (line.speaker) {
        "A" -> MaterialTheme.colorScheme.onSecondaryContainer
        "B" -> MaterialTheme.colorScheme.onSurfaceVariant
        else -> MaterialTheme.colorScheme.onTertiaryContainer
    }
    val speakerLabel = when (line.speaker) {
        "A" -> "A (family)"
        "B" -> "B (family)"
        else -> "L (lifeguard)"
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
fun BeachConversationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("At The Beach") },
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
                    text = "Talking to the Lifeguard",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            items(beachDialogue) { line ->
                BeachCard(line = line)
                Spacer(modifier = Modifier.height(6.dp))
            }

            item { Spacer(modifier = Modifier.height(10.dp)) }
        }
    }
}
