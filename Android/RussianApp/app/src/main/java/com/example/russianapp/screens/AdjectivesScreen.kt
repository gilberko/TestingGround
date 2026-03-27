package com.example.russianapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// ── Data ──────────────────────────────────────────────────────────────────────

private data class RussianAdjective(
    val english: String,
    val masculine: String,
    val feminine: String,
    val neuter: String,
    val plural: String
)

private val adjectives = listOf(
    RussianAdjective("big / large",        "большой",       "большая",       "большое",       "большие"),
    RussianAdjective("small / little",     "маленький",     "маленькая",     "маленькое",     "маленькие"),
    RussianAdjective("new",                "новый",         "новая",         "новое",         "новые"),
    RussianAdjective("old (things)",       "старый",        "старая",        "старое",        "старые"),
    RussianAdjective("young",              "молодой",       "молодая",       "молодое",       "молодые"),
    RussianAdjective("good",               "хороший",       "хорошая",       "хорошее",       "хорошие"),
    RussianAdjective("bad",                "плохой",        "плохая",        "плохое",        "плохие"),
    RussianAdjective("beautiful",          "красивый",      "красивая",      "красивое",      "красивые"),
    RussianAdjective("ugly",               "некрасивый",    "некрасивая",    "некрасивое",    "некрасивые"),
    RussianAdjective("fast / quick",       "быстрый",       "быстрая",       "быстрое",       "быстрые"),
    RussianAdjective("slow",               "медленный",     "медленная",     "медленное",     "медленные"),
    RussianAdjective("tall / high",        "высокий",       "высокая",       "высокое",       "высокие"),
    RussianAdjective("short / low",        "низкий",        "низкая",        "низкое",        "низкие"),
    RussianAdjective("long",               "длинный",       "длинная",       "длинное",       "длинные"),
    RussianAdjective("short (length)",     "короткий",      "короткая",      "короткое",      "короткие"),
    RussianAdjective("warm",               "тёплый",        "тёплая",        "тёплое",        "тёплые"),
    RussianAdjective("cold",               "холодный",      "холодная",      "холодное",      "холодные"),
    RussianAdjective("hot",                "горячий",       "горячая",       "горячее",       "горячие"),
    RussianAdjective("tasty / delicious",  "вкусный",       "вкусная",       "вкусное",       "вкусные"),
    RussianAdjective("difficult / hard",   "трудный",       "трудная",       "трудное",       "трудные"),
    RussianAdjective("easy",               "лёгкий",        "лёгкая",        "лёгкое",        "лёгкие"),
    RussianAdjective("expensive",          "дорогой",       "дорогая",       "дорогое",       "дорогие"),
    RussianAdjective("cheap",             "дешёвый",       "дешёвая",       "дешёвое",       "дешёвые"),
    RussianAdjective("interesting",        "интересный",    "интересная",    "интересное",    "интересные"),
    RussianAdjective("boring",             "скучный",       "скучная",       "скучное",       "скучные"),
    RussianAdjective("happy",              "счастливый",    "счастливая",    "счастливое",    "счастливые"),
    RussianAdjective("sad",                "грустный",      "грустная",      "грустное",      "грустные"),
    RussianAdjective("healthy",            "здоровый",      "здоровая",      "здоровое",      "здоровые"),
    RussianAdjective("sick / ill",         "больной",       "больная",       "больное",       "больные"),
    RussianAdjective("clean",              "чистый",        "чистая",        "чистое",        "чистые"),
    RussianAdjective("dirty",              "грязный",       "грязная",       "грязное",       "грязные"),
    RussianAdjective("smart / clever",     "умный",         "умная",         "умное",         "умные"),
    RussianAdjective("stupid",             "глупый",        "глупая",        "глупое",        "глупые"),
    RussianAdjective("strong",             "сильный",       "сильная",       "сильное",       "сильные"),
    RussianAdjective("weak",               "слабый",        "слабая",        "слабое",        "слабые"),
    RussianAdjective("rich",               "богатый",       "богатая",       "богатое",       "богатые"),
    RussianAdjective("poor",               "бедный",        "бедная",        "бедное",        "бедные"),
    RussianAdjective("loud",               "громкий",       "громкая",       "громкое",       "громкие"),
    RussianAdjective("quiet",              "тихий",         "тихая",         "тихое",         "тихие"),
    RussianAdjective("kind / nice",        "добрый",        "добрая",        "доброе",        "добрые"),
    RussianAdjective("angry / mean",       "злой",          "злая",          "злое",          "злые"),
    RussianAdjective("tired",              "усталый",       "усталая",       "усталое",       "усталые"),
    RussianAdjective("free / available",   "свободный",     "свободная",     "свободное",     "свободные"),
    RussianAdjective("busy",               "занятой",       "занятая",       "занятое",       "занятые"),
    RussianAdjective("important",          "важный",        "важная",        "важное",        "важные"),
    RussianAdjective("correct / right",    "правильный",    "правильная",    "правильное",    "правильные"),
    RussianAdjective("wrong",              "неправильный",  "неправильная",  "неправильное",  "неправильные"),
    RussianAdjective("dark",               "тёмный",        "тёмная",        "тёмное",        "тёмные"),
    RussianAdjective("light / bright",     "светлый",       "светлая",       "светлое",       "светлые"),
    RussianAdjective("open",               "открытый",      "открытая",      "открытое",      "открытые"),
    RussianAdjective("closed",             "закрытый",      "закрытая",      "закрытое",      "закрытые"),
    RussianAdjective("full",               "полный",        "полная",        "полное",        "полные"),
    RussianAdjective("empty",              "пустой",        "пустая",        "пустое",        "пустые"),
    RussianAdjective("wide",               "широкий",       "широкая",       "широкое",       "широкие"),
    RussianAdjective("narrow",             "узкий",         "узкая",         "узкое",         "узкие"),
    RussianAdjective("deep",               "глубокий",      "глубокая",      "глубокое",      "глубокие"),
    RussianAdjective("heavy",              "тяжёлый",       "тяжёлая",       "тяжёлое",       "тяжёлые"),
    RussianAdjective("light (weight)",     "лёгкий",        "лёгкая",        "лёгкое",        "лёгкие"),
    RussianAdjective("soft",               "мягкий",        "мягкая",        "мягкое",        "мягкие"),
    RussianAdjective("hard / firm",        "твёрдый",       "твёрдая",       "твёрдое",       "твёрдые"),
    RussianAdjective("simple / plain",     "простой",       "простая",       "простое",       "простые"),
    RussianAdjective("different",          "другой",        "другая",        "другое",        "другие")
)

// ── Composables ───────────────────────────────────────────────────────────────

@Composable
private fun SectionHeader(title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 4.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        HorizontalDivider(
            modifier = Modifier.padding(top = 4.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun AdjectiveTableHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp)
    ) {
        Text("English",   style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.8f))
        Text("Masc.",     style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary,   modifier = Modifier.weight(1.4f))
        Text("Fem.",      style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1.4f))
        Text("Neut.",     style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.tertiary,  modifier = Modifier.weight(1.2f))
        Text("Plural",    style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.outline,   modifier = Modifier.weight(1.4f))
    }
}

@Composable
private fun AdjectiveRow(adj: RussianAdjective, isEven: Boolean) {
    val bg = if (isEven)
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
    else
        MaterialTheme.colorScheme.surface
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp),
        colors = CardDefaults.cardColors(containerColor = bg),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 5.dp)) {
            Text(adj.english,    style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.8f))
            Text(adj.masculine,  style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary,   modifier = Modifier.weight(1.4f))
            Text(adj.feminine,   style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1.4f))
            Text(adj.neuter,     style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.tertiary,  modifier = Modifier.weight(1.2f))
            Text(adj.plural,     style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.outline,   modifier = Modifier.weight(1.4f))
        }
    }
}

// ── Screen ────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdjectivesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adjectives") },
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
                .padding(innerPadding)
                .padding(horizontal = 8.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item {
                SectionHeader("60 Common Adjectives")
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Adjectives in Russian agree with the noun they describe in gender, number, and case. " +
                            "The nominative (dictionary) forms are shown here: Masculine / Feminine / Neuter / Plural.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                AdjectiveTableHeader()
            }
            items(adjectives.size) { index ->
                AdjectiveRow(adjectives[index], isEven = index % 2 == 0)
            }
        }
    }
}
