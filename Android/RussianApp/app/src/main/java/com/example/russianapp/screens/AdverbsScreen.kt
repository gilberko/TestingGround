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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// ── Data ──────────────────────────────────────────────────────────────────────

private data class AdverbEntry(
    val english: String,
    val adverb: String,
    val adjective: String = ""   // related adjective form (if applicable)
)

// Manner / quality adverbs (including predicative adjectives used as adverbs)
private val mannerAdverbs = listOf(
    AdverbEntry("well / it's good",          "хорошо",       "хороший"),
    AdverbEntry("badly / it's bad",          "плохо",        "плохой"),
    AdverbEntry("quickly / fast",            "быстро",       "быстрый"),
    AdverbEntry("slowly",                    "медленно",     "медленный"),
    AdverbEntry("easily / it's easy",        "легко",        "лёгкий"),
    AdverbEntry("with difficulty / hard",    "трудно",       "трудный"),
    AdverbEntry("loudly / loud",             "громко",       "громкий"),
    AdverbEntry("quietly / quiet",           "тихо",         "тихий"),
    AdverbEntry("beautifully / beautiful",   "красиво",      "красивый"),
    AdverbEntry("strongly / strongly",       "сильно",       "сильный"),
    AdverbEntry("together",                  "вместе"),
    AdverbEntry("alone",                     "одного / одной (m/f)"),
)

// Predicative adverbs describing conditions/states
private val predicativeAdverbs = listOf(
    AdverbEntry("warm / it's warm",          "тепло",        "тёплый"),
    AdverbEntry("cold / it's cold",          "холодно",      "холодный"),
    AdverbEntry("hot / it's hot",            "жарко",        "жаркий"),
    AdverbEntry("tasty / it's tasty",        "вкусно",       "вкусный"),
    AdverbEntry("expensive / it's expensive","дорого",       "дорогой"),
    AdverbEntry("cheap / it's cheap",        "дёшево",       "дешёвый"),
    AdverbEntry("interesting",               "интересно",    "интересный"),
    AdverbEntry("boring",                    "скучно",       "скучный"),
    AdverbEntry("tall / high up",            "высоко",       "высокий"),
    AdverbEntry("short / low",               "низко",        "низкий"),
    AdverbEntry("far / it's far",            "далеко",       "далёкий"),
    AdverbEntry("near / it's near",          "близко",       "близкий"),
)

// Time adverbs
private val timeAdverbs = listOf(
    AdverbEntry("now",               "сейчас"),
    AdverbEntry("then / later",      "потом"),
    AdverbEntry("soon",              "скоро"),
    AdverbEntry("already",           "уже"),
    AdverbEntry("still / yet",       "ещё"),
    AdverbEntry("always",            "всегда"),
    AdverbEntry("never",             "никогда"),
    AdverbEntry("often",             "часто"),
    AdverbEntry("sometimes",         "иногда"),
    AdverbEntry("rarely",            "редко"),
    AdverbEntry("recently",          "недавно"),
    AdverbEntry("long ago",          "давно"),
    AdverbEntry("finally",           "наконец"),
    AdverbEntry("immediately",       "сразу"),
    AdverbEntry("again",             "снова / опять"),
    AdverbEntry("at first",          "сначала"),
)

// Degree / quantity adverbs
private val degreeAdverbs = listOf(
    AdverbEntry("very",              "очень"),
    AdverbEntry("a lot / much",      "много"),
    AdverbEntry("a little / few",    "мало / немного"),
    AdverbEntry("enough",            "достаточно"),
    AdverbEntry("too much / too",    "слишком"),
    AdverbEntry("almost",            "почти"),
    AdverbEntry("exactly",           "точно"),
    AdverbEntry("approximately",     "примерно"),
)

// Certainty / affirmation adverbs
private val certaintyAdverbs = listOf(
    AdverbEntry("of course",         "конечно"),
    AdverbEntry("certainly",         "обязательно"),
    AdverbEntry("probably",          "наверное"),
    AdverbEntry("perhaps / maybe",   "может быть"),
    AdverbEntry("suddenly",          "вдруг"),
    AdverbEntry("unfortunately",     "к сожалению"),
    AdverbEntry("fortunately",       "к счастью"),
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
private fun AdverbSection(entries: List<AdverbEntry>) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            entries.forEachIndexed { index, entry ->
                if (index > 0) {
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        thickness = 0.5.dp
                    )
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = entry.english,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(1.8f)
                    )
                    Column(modifier = Modifier.weight(2f)) {
                        Text(
                            text = entry.adverb,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        if (entry.adjective.isNotEmpty()) {
                            Text(
                                text = "adj: ${entry.adjective}",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontStyle = FontStyle.Italic
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AdverbIntroCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Adverbs in Russian are invariable — they do not change for gender, number, or case. " +
                        "Many adverbs are formed from adjectives by replacing the ending with -о or -е.",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Many of these words also serve as predicative adverbs (impersonal sentences):",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            listOf(
                "Здесь тепло. — It's warm here.",
                "Это трудно. — This is difficult.",
                "Мне хорошо. — I feel good. (lit. To me it's good.)"
            ).forEach {
                Text("• $it", style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 1.dp))
            }
        }
    }
}

// ── Screen ────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdverbsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adverbs") },
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
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item { SectionHeader("About Adverbs") }
            item { AdverbIntroCard() }

            item { SectionHeader("Manner & Quality") }
            item { AdverbSection(mannerAdverbs) }

            item { SectionHeader("Predicative / State") }
            item { AdverbSection(predicativeAdverbs) }

            item { SectionHeader("Time") }
            item { AdverbSection(timeAdverbs) }

            item { SectionHeader("Degree & Quantity") }
            item { AdverbSection(degreeAdverbs) }

            item { SectionHeader("Certainty & Affirmation") }
            item { AdverbSection(certaintyAdverbs) }
        }
    }
}
