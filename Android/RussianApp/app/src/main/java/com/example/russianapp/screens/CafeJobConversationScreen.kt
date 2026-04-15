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

private data class CafeJobLine(val speaker: String, val ru: String, val en: String)

private val cafeJobDialogue = listOf(
    CafeJobLine("M", "Добрый день! Вы записались на собеседование на должность бариста?", "Good afternoon! Did you sign up for the barista interview?"),
    CafeJobLine("A", "Да, здравствуйте. Меня зовут Антон. Я очень заинтересован в этой должности.", "Yes, hello. My name is Anton. I'm very interested in this position."),
    CafeJobLine("M", "Приятно познакомиться, Антон. Я Марина, управляющая кафе. Расскажите — вы работали бариста раньше?", "Nice to meet you, Anton. I'm Marina, the café manager. Tell me — have you worked as a barista before?"),
    CafeJobLine("A", "Да, я работал в кофейне около двух лет. У меня есть опыт с эспрессо-машинами.", "Yes, I worked in a coffee shop for about two years. I have experience with espresso machines."),
    CafeJobLine("M", "Хорошо. Давайте проверим ваши знания. Как вы готовите эспрессо макиато?", "Good. Let's test your knowledge. How do you prepare an espresso macchiato?"),
    CafeJobLine("A", "Сначала варю двойной эспрессо — примерно 60 миллилитров. Затем взбиваю небольшое количество молока до состояния микропены. Выкладываю ложку пены поверх эспрессо.", "First I brew a double espresso — about 60 milliliters. Then I steam a small amount of milk to a microfoam texture. I place a spoonful of foam on top of the espresso."),
    CafeJobLine("M", "Верно. А чем макиато отличается от капучино?", "Correct. And how does a macchiato differ from a cappuccino?"),
    CafeJobLine("A", "Макиато — это эспрессо с небольшим количеством пены. Капучино — это равные части эспрессо, горячего молока и молочной пены, примерно по одной трети каждого.", "A macchiato is espresso with a small amount of foam. A cappuccino is equal parts espresso, hot milk, and milk foam — roughly one third each."),
    CafeJobLine("M", "Отлично объяснено. Как вы готовите капучино?", "Excellently explained. How do you prepare a cappuccino?"),
    CafeJobLine("A", "Варю эспрессо в чашку объёмом 180–200 миллилитров. Взбиваю молоко до температуры 65 градусов — до состояния бархатистой пены. Наливаю молоко в эспрессо, удерживая пену ложкой, затем выкладываю пену сверху.", "I brew espresso into a 180–200ml cup. I steam the milk to 65 degrees — to a velvety foam texture. I pour the milk into the espresso, holding back the foam with a spoon, then place the foam on top."),
    CafeJobLine("M", "Прекрасно! Чувствуется опыт. Теперь давайте обсудим условия работы. Вы ищете полную или частичную занятость?", "Excellent! Experience shows. Now let's discuss the terms of employment. Are you looking for full-time or part-time work?"),
    CafeJobLine("A", "Я хотел бы работать полный рабочий день, если возможно.", "I'd like to work full-time, if possible."),
    CafeJobLine("M", "У нас есть полная ставка — пять дней в неделю, смены по восемь часов. Иногда нужно выходить в выходные.", "We have a full-time position — five days a week, eight-hour shifts. Sometimes you'd need to work weekends."),
    CafeJobLine("A", "Это мне подходит. А какова зарплата?", "That works for me. And what is the salary?"),
    CafeJobLine("M", "Мы предлагаем 55 000 рублей в месяц плюс чаевые. Чаевые обычно составляют от 10 до 15 тысяч рублей.", "We offer 55,000 rubles per month plus tips. Tips usually amount to 10 to 15 thousand rubles."),
    CafeJobLine("A", "Хорошо. Есть ли другие льготы?", "Good. Are there any other benefits?"),
    CafeJobLine("M", "Да. Мы оплачиваем бесплатный кофе и обед во время смены. Также после испытательного срока предоставляем оплачиваемый отпуск — 28 дней в год.", "Yes. We provide free coffee and lunch during the shift. Also, after the probationary period we provide paid vacation — 28 days per year."),
    CafeJobLine("A", "Отлично! А каков испытательный срок?", "Great! And what is the probationary period?"),
    CafeJobLine("M", "Три месяца. В этот период зарплата такая же. Если всё хорошо, оформляем постоянный договор.", "Three months. During this period the salary is the same. If everything goes well, we draw up a permanent contract."),
    CafeJobLine("A", "Звучит очень привлекательно. Когда можно приступить к работе?", "Sounds very attractive. When can I start work?"),
    CafeJobLine("M", "Если вы нам подходите, хотели бы начать со следующей недели? У нас как раз открылась вакансия.", "If you're a good fit, would you like to start next week? We just had a vacancy open up."),
    CafeJobLine("A", "С удовольствием! Спасибо за возможность.", "I'd love to! Thank you for the opportunity.")
)

@Composable
private fun CafeJobCard(line: CafeJobLine) {
    val containerColor = when (line.speaker) {
        "A" -> MaterialTheme.colorScheme.secondaryContainer
        else -> MaterialTheme.colorScheme.tertiaryContainer
    }
    val labelColor = when (line.speaker) {
        "A" -> MaterialTheme.colorScheme.onSecondaryContainer
        else -> MaterialTheme.colorScheme.onTertiaryContainer
    }
    val speakerLabel = when (line.speaker) {
        "A" -> "A (applicant)"
        else -> "M (manager)"
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
fun CafeJobConversationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Applying For A Job") },
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
                    text = "Barista Position at a Café",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            items(cafeJobDialogue) { line ->
                CafeJobCard(line = line)
                Spacer(modifier = Modifier.height(6.dp))
            }

            item { Spacer(modifier = Modifier.height(10.dp)) }
        }
    }
}
