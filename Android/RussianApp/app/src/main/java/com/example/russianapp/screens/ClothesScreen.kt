package com.example.russianapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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

private data class ClothesEntry(
    val english: String,
    val russian: String,
    val gender: String,    // "m", "f", "n", "n*", "pl"
    val note: String = ""
)

private val clothesWords = listOf(
    ClothesEntry("clothing / clothes",   "одежда",         "f"),
    ClothesEntry("pants / trousers",     "брюки",          "pl",  "plural-only form"),
    ClothesEntry("jeans",                "джинсы",         "pl",  "plural-only form"),
    ClothesEntry("underwear",            "нижнее бельё",   "n",   "lit. \"lower linen\""),
    ClothesEntry("socks",                "носки",          "pl",  "sg: носок (m)"),
    ClothesEntry("hat (winter)",         "шапка",          "f",   "knit or fur hat"),
    ClothesEntry("hat (brimmed)",        "шляпа",          "f",   "hat with a brim"),
    ClothesEntry("jacket (casual)",      "куртка",         "f"),
    ClothesEntry("jacket (suit)",        "пиджак",         "m",   "blazer / suit jacket"),
    ClothesEntry("shirt (button-up)",    "рубашка",        "f"),
    ClothesEntry("t-shirt",              "футболка",       "f"),
    ClothesEntry("sweater",              "свитер",         "m"),
    ClothesEntry("skirt",                "юбка",           "f"),
    ClothesEntry("dress",                "платье",         "n"),
    ClothesEntry("boots",                "сапоги",         "pl",  "sg: сапог (m)"),
    ClothesEntry("shoes",                "туфли",          "pl",  "sg: туфля (f)"),
    ClothesEntry("sneakers",             "кроссовки",      "pl",  "sg: кроссовка (f)"),
    ClothesEntry("swimsuit (women's)",   "купальник",      "m"),
    ClothesEntry("swimming trunks",      "плавки",         "pl",  "plural-only form"),
    ClothesEntry("coat",                 "пальто",         "n*",  "indeclinable — always пальто"),
    ClothesEntry("raincoat",             "дождевик",       "m"),
    ClothesEntry("scarf",                "шарф",           "m"),
    ClothesEntry("gloves",               "перчатки",       "pl",  "sg: перчатка (f)"),
    ClothesEntry("belt",                 "ремень",         "m"),
    ClothesEntry("pocket",               "карман",         "m"),
    ClothesEntry("size",                 "размер",         "m")
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

private fun genderLabel(g: String) = when (g) {
    "m"  -> "masc."
    "m*" -> "masc.*"
    "f"  -> "fem."
    "n"  -> "neut."
    "n*" -> "neut.*"
    "pl" -> "pl."
    else -> g
}

private fun genderColor(g: String, scheme: androidx.compose.material3.ColorScheme) = when (g) {
    "m", "m*" -> scheme.primary
    "f"        -> scheme.secondary
    "n", "n*"  -> scheme.tertiary
    else       -> scheme.outline
}

@Composable
private fun ClothesTableHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp)
    ) {
        Text("English",  style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.8f))
        Text("Russian",  style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.8f))
        Text("Gender",   style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.8f))
    }
}

@Composable
private fun ClothesRow(entry: ClothesEntry, isEven: Boolean) {
    val bg = if (isEven)
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
    else
        MaterialTheme.colorScheme.surface
    val gColor = genderColor(entry.gender, MaterialTheme.colorScheme)
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp),
        colors = CardDefaults.cardColors(containerColor = bg),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(entry.english, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.8f))
                Text(entry.russian, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.8f))
                Text(genderLabel(entry.gender), style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold, color = gColor, modifier = Modifier.weight(0.8f))
            }
            if (entry.note.isNotEmpty()) {
                Text(
                    text = entry.note,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(top = 1.dp)
                )
            }
        }
    }
}

@Composable
private fun GenderLegendCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("Gender legend:", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            Row(modifier = Modifier.fillMaxWidth().padding(top = 6.dp)) {
                Text("masc.", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1f))
                Text("masculine — typically ends in a consonant or -й",
                    style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(3f))
            }
            Row(modifier = Modifier.fillMaxWidth().padding(top = 4.dp)) {
                Text("fem.", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1f))
                Text("feminine — typically ends in -а / -я or -ь",
                    style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(3f))
            }
            Row(modifier = Modifier.fillMaxWidth().padding(top = 4.dp)) {
                Text("neut.", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary, modifier = Modifier.weight(1f))
                Text("neuter — typically ends in -о / -е",
                    style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(3f))
            }
            Row(modifier = Modifier.fillMaxWidth().padding(top = 4.dp)) {
                Text("neut.*", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary, modifier = Modifier.weight(1f))
                Text("indeclinable neuter — never changes form",
                    style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(3f))
            }
            Row(modifier = Modifier.fillMaxWidth().padding(top = 4.dp)) {
                Text("pl.", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.outline, modifier = Modifier.weight(1f))
                Text("plural-only — no singular form exists",
                    style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(3f))
            }
        }
    }
}

// ── Screen ────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClothesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Clothes") },
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
                SectionHeader("Clothes & Accessories")
                GenderLegendCard()
                ClothesTableHeader()
            }
            items(clothesWords.size) { index ->
                ClothesRow(clothesWords[index], isEven = index % 2 == 0)
            }
        }
    }
}
