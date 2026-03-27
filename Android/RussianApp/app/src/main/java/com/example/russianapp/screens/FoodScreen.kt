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

private data class FoodEntry(
    val english: String,
    val russian: String,
    val gender: String,    // "m", "f", "n", "pl", or "m*" for indeclinable
    val note: String = ""
)

private val foodWords = listOf(
    // Meals & occasions
    FoodEntry("breakfast",       "завтрак",        "m"),
    FoodEntry("lunch",           "обед",           "m"),
    FoodEntry("dinner",          "ужин",           "m"),
    // Staples
    FoodEntry("bread",           "хлеб",           "m"),
    FoodEntry("butter",          "масло",          "n"),
    FoodEntry("meat",            "мясо",           "n"),
    FoodEntry("chicken",         "курица",         "f"),
    FoodEntry("fish",            "рыба",           "f"),
    FoodEntry("egg",             "яйцо",           "n"),
    FoodEntry("cheese",          "сыр",            "m"),
    FoodEntry("sausage",         "колбаса",        "f"),
    FoodEntry("rice",            "рис",            "m"),
    FoodEntry("pasta / noodles", "макароны",       "pl",   "plural-only form"),
    // Soup & dishes
    FoodEntry("soup",            "суп",            "m"),
    FoodEntry("borscht",         "борщ",           "m"),
    FoodEntry("pancake",         "блин",           "m"),
    FoodEntry("pizza",           "пицца",          "f"),
    FoodEntry("pasta (Italian)", "паста",          "f"),
    // Dairy
    FoodEntry("milk",            "молоко",         "n"),
    // Vegetables
    FoodEntry("potato",          "картофель",      "m",    "informal: картошка (f)"),
    FoodEntry("tomato",          "помидор",        "m"),
    FoodEntry("cucumber",        "огурец",         "m"),
    FoodEntry("pepper",          "перец",          "m"),
    FoodEntry("onion",           "лук",            "m",    "collective; no plural needed"),
    FoodEntry("garlic",          "чеснок",         "m",    "collective"),
    FoodEntry("parsley",         "петрушка",       "f"),
    FoodEntry("dill",            "укроп",          "m"),
    FoodEntry("mushroom",        "гриб",           "m"),
    // Fruits
    FoodEntry("apple",           "яблоко",         "n"),
    FoodEntry("lemon",           "лимон",          "m"),
    FoodEntry("grapes",          "виноград",       "m",    "collective; singular used"),
    FoodEntry("pineapple",       "ананас",         "m"),
    FoodEntry("banana",          "банан",          "m"),
    FoodEntry("strawberry",      "клубника",       "f",    "collective"),
    // Sweets & desserts
    FoodEntry("cake",            "торт",           "m"),
    FoodEntry("ice cream",       "мороженое",      "n",    "declines as adjective"),
    FoodEntry("chocolate",       "шоколад",        "m"),
    FoodEntry("sugar",           "сахар",          "m"),
    FoodEntry("salt",            "соль",           "f"),
    // Drinks
    FoodEntry("water",           "вода",           "f"),
    FoodEntry("juice",           "сок",            "m"),
    FoodEntry("tea",             "чай",            "m"),
    FoodEntry("coffee",          "кофе",           "m*",   "indeclinable — always кофе"),
    FoodEntry("milk",            "молоко",         "n",    "listed again as drink"),
    FoodEntry("beer",            "пиво",           "n"),
    FoodEntry("wine",            "вино",           "n"),
    FoodEntry("vodka",           "водка",          "f"),
    // Tableware
    FoodEntry("plate",           "тарелка",        "f"),
    FoodEntry("bowl",            "миска",          "f"),
    FoodEntry("cup",             "чашка",          "f"),
    FoodEntry("mug",             "кружка",         "f"),
    FoodEntry("glass",           "стакан",         "m"),
    FoodEntry("knife",           "нож",            "m"),
    FoodEntry("fork",            "вилка",          "f"),
    FoodEntry("spoon",           "ложка",          "f"),
    FoodEntry("teaspoon",        "чайная ложка",   "f")
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
    "pl" -> "pl."
    else -> g
}

private fun genderColor(g: String, scheme: androidx.compose.material3.ColorScheme) = when (g) {
    "m", "m*" -> scheme.primary
    "f"        -> scheme.secondary
    "n"        -> scheme.tertiary
    else       -> scheme.outline
}

@Composable
private fun FoodTableHeader() {
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
private fun FoodRow(entry: FoodEntry, isEven: Boolean) {
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
                Text(entry.english,  style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.8f))
                Text(entry.russian,  style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.8f))
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
                Text("masc.*", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1f))
                Text("indeclinable — treated as masculine but never changes form",
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
fun FoodScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Food & Drink") },
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
                SectionHeader("Food, Drink & Tableware")
                GenderLegendCard()
                FoodTableHeader()
            }
            // deduplicate milk (appears twice in source for demonstration, show unique only)
            val unique = foodWords.distinctBy { it.english + it.russian }
            items(unique.size) { index ->
                FoodRow(unique[index], isEven = index % 2 == 0)
            }
        }
    }
}
