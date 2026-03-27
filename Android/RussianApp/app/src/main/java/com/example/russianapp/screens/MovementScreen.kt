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

private data class MovementWord(
    val english: String,
    val russian: String,
    val notes: String = ""
)

private val directions = listOf(
    MovementWord("forward",          "вперёд"),
    MovementWord("backward",         "назад"),
    MovementWord("left",             "влево / налево",  "влево = motion; налево = direction"),
    MovementWord("right",            "вправо / направо","вправо = motion; направо = direction"),
    MovementWord("up",               "вверх / наверх",  "вверх = motion upward; наверх = to the top"),
    MovementWord("down",             "вниз",            "movement downward")
)

private val cardinalDirections = listOf(
    MovementWord("north",  "север",  "на севере (in the north); на север (to the north)"),
    MovementWord("south",  "юг",     "на юге (in the south); на юг (to the south)"),
    MovementWord("east",   "восток", "на востоке (in the east); на восток (to the east)"),
    MovementWord("west",   "запад",  "на западе (in the west); на запад (to the west)")
)

private val distanceWords = listOf(
    MovementWord("far",          "далеко"),
    MovementWord("near / close", "близко / рядом",  "рядом = right next to; близко = nearby"),
    MovementWord("far away",     "далеко отсюда",   "away from here")
)

private val locationWords = listOf(
    MovementWord("here",       "здесь / тут",   "здесь = here (location); тут = here (informal)"),
    MovementWord("there",      "там"),
    MovementWord("everywhere", "везде"),
    MovementWord("somewhere",  "где-то"),
    MovementWord("nowhere",    "нигде")
)

private val motionPrepositions = listOf(
    MovementWord("straight ahead", "прямо"),
    MovementWord("around",         "вокруг",          "takes genitive: вокруг дома (around the house)"),
    MovementWord("through",        "через",            "takes accusative: через парк (through the park)"),
    MovementWord("past",           "мимо",             "takes genitive: мимо магазина (past the shop)"),
    MovementWord("toward",         "к / навстречу",    "к + dative: к дому (toward the house)"),
    MovementWord("away from",      "от",               "takes genitive: от дома (away from the house)")
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
private fun VNaExplanationCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "When to use В vs НА",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "В (in / into)",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Used with enclosed spaces, countries, and cities. Think of going inside something.",
                style = MaterialTheme.typography.bodySmall
            )
            listOf(
                "идти в магазин — go to the store (into the store)",
                "в Россию — to Russia",
                "в школу — to school",
                "в театр — to the theater"
            ).forEach {
                Text("• $it", style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic, color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 1.dp))
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "НА (on / onto)",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = "Used with open spaces, events, certain places, and all cardinal directions.",
                style = MaterialTheme.typography.bodySmall
            )
            listOf(
                "идти на работу — go to work",
                "на концерт — to a concert",
                "на почту — to the post office",
                "на стадион — to the stadium",
                "на север / юг / восток / запад — to the north / south / east / west"
            ).forEach {
                Text("• $it", style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic, color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 1.dp))
            }

            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Motion vs Location",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            listOf(
                Pair("Motion (accusative):", "идти на север — going north"),
                Pair("Location (prepositional):", "жить на севере — living in the north")
            ).forEach { (label, example) ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                    Text(label,   style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1.8f))
                    Text(example, style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontStyle = FontStyle.Italic, modifier = Modifier.weight(2f))
                }
            }
        }
    }
}

@Composable
private fun WordSection(words: List<MovementWord>) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            words.forEachIndexed { index, word ->
                if (index > 0) Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = word.english,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1.4f)
                    )
                    Column(modifier = Modifier.weight(2f)) {
                        Text(
                            text = word.russian,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        if (word.notes.isNotEmpty()) {
                            Text(
                                text = word.notes,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontStyle = FontStyle.Italic
                            )
                        }
                    }
                }
                if (index < words.lastIndex) {
                    HorizontalDivider(modifier = Modifier.padding(top = 8.dp), thickness = 0.5.dp)
                }
            }
        }
    }
}

// ── Screen ────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovementScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Movement") },
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
            item { SectionHeader("Prepositions: В vs НА") }
            item { VNaExplanationCard() }

            item { SectionHeader("Directions") }
            item { WordSection(directions) }

            item { SectionHeader("Cardinal Directions") }
            item { WordSection(cardinalDirections) }

            item { SectionHeader("Distance") }
            item { WordSection(distanceWords) }

            item { SectionHeader("Location Words") }
            item { WordSection(locationWords) }

            item { SectionHeader("Motion Prepositions") }
            item { WordSection(motionPrepositions) }
        }
    }
}
