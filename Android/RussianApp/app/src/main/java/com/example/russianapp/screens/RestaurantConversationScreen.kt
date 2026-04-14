package com.example.russianapp.screens

import androidx.compose.foundation.layout.Column
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

private data class RestaurantLine(val speaker: String, val ru: String, val en: String)

private val restaurantDialogue = listOf(
    RestaurantLine("W", "Добрый вечер! Добро пожаловать! Вы готовы сделать заказ?", "Good evening! Welcome! Are you ready to order?"),
    RestaurantLine("A", "Да, почти. Расскажите нам про меню.", "Yes, almost. Tell us about the menu."),
    RestaurantLine("W", "Конечно! Сегодня у нас есть говядина, рыба, курица и индейка.", "Of course! Today we have beef, fish, chicken, and turkey."),
    RestaurantLine("B", "Извините, у меня целиакия и непереносимость лактозы. Я не могу есть глютен и молочные продукты. Что мне подойдёт?", "Excuse me, I have celiac disease and lactose intolerance. I can't eat gluten or dairy products. What would be suitable for me?"),
    RestaurantLine("W", "Понимаю. Наша рыба приготовлена с оливковым маслом и травами — без глютена и молочного.", "I understand. Our fish is prepared with olive oil and herbs — no gluten and no dairy."),
    RestaurantLine("B", "Звучит хорошо! А курица?", "That sounds good! And the chicken?"),
    RestaurantLine("W", "Курица маринованная с лимоном и чесноком, подаётся с рисом и овощами. Тоже без глютена и молочного.", "The chicken is marinated with lemon and garlic, served with rice and vegetables. Also without gluten and dairy."),
    RestaurantLine("B", "Отлично. А индейка?", "Excellent. And the turkey?"),
    RestaurantLine("W", "Индейка у нас запечённая, но к сожалению, соус содержит муку. Вам её не рекомендую.", "Our turkey is roasted, but unfortunately the sauce contains flour. I wouldn't recommend it for you."),
    RestaurantLine("B", "Понятно, спасибо. Тогда возьму курицу.", "Understood, thank you. Then I'll have the chicken."),
    RestaurantLine("A", "А что представляет собой говяжье блюдо сегодня?", "And what is the beef dish today?"),
    RestaurantLine("W", "Говяжий стейк с картофельным пюре и грибным соусом. Очень вкусно!", "Beef steak with mashed potatoes and mushroom sauce. Very delicious!"),
    RestaurantLine("A", "Звучит прекрасно! Возьму стейк.", "Sounds wonderful! I'll take the steak."),
    RestaurantLine("W", "Прекрасный выбор! Что будете пить?", "Excellent choice! What will you drink?"),
    RestaurantLine("A", "Мне бокал красного вина, пожалуйста.", "A glass of red wine for me, please."),
    RestaurantLine("B", "А мне минеральную воду без газа.", "And still mineral water for me."),
    RestaurantLine("W", "Отлично! Сейчас принесу.", "Excellent! I'll bring it right away.")
)

@Composable
private fun RestaurantCard(line: RestaurantLine) {
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
        "A" -> "A (friend)"
        "B" -> "B (friend)"
        else -> "W (waiter)"
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
fun RestaurantConversationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("At The Restaurant") },
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
                    text = "Ordering Food",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            items(restaurantDialogue) { line ->
                RestaurantCard(line = line)
                Spacer(modifier = Modifier.height(6.dp))
            }

            item { Spacer(modifier = Modifier.height(10.dp)) }
        }
    }
}
