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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// ── Data ──────────────────────────────────────────────────────────────────────

private data class PlaceEntry(
    val english: String,
    val russian: String,
    val gender: String,   // m, f, n, pl, m* (indeclinable)
    val note: String = ""
)

private val placeWords = listOf(
    // City & geography
    PlaceEntry("city / town",          "город",              "m"),
    PlaceEntry("village",              "деревня",            "f"),
    PlaceEntry("country",              "страна",             "f"),
    PlaceEntry("capital",              "столица",            "f"),
    PlaceEntry("street",               "улица",              "f"),
    PlaceEntry("road",                 "дорога",             "f"),
    PlaceEntry("avenue",               "проспект",           "m"),
    PlaceEntry("lane / alley",         "переулок",           "m"),
    PlaceEntry("square / plaza",       "площадь",            "f"),
    PlaceEntry("main square",          "главная площадь",    "f"),
    PlaceEntry("city center",          "центр города",       "m"),
    PlaceEntry("neighbourhood",        "район",              "m"),
    PlaceEntry("bridge",               "мост",               "m"),
    // Nature
    PlaceEntry("forest",               "лес",                "m"),
    PlaceEntry("park",                 "парк",               "m"),
    PlaceEntry("garden",               "сад",                "m"),
    PlaceEntry("river",                "река",               "f"),
    PlaceEntry("lake",                 "озеро",              "n"),
    PlaceEntry("sea",                  "море",               "n"),
    PlaceEntry("ocean",                "океан",              "m"),
    PlaceEntry("mountain",             "гора",               "f"),
    PlaceEntry("beach",                "пляж",               "m"),
    PlaceEntry("field",                "поле",               "n"),
    PlaceEntry("island",               "остров",             "m"),
    // Transport hubs
    PlaceEntry("airport",              "аэропорт",           "m"),
    PlaceEntry("train station",        "вокзал",             "m"),
    PlaceEntry("bus station",          "автовокзал",         "m"),
    PlaceEntry("metro station",        "станция метро",      "f"),
    PlaceEntry("port / harbour",       "порт",               "m"),
    // Accommodation & services
    PlaceEntry("hotel",                "гостиница",          "f"),
    PlaceEntry("hostel",               "хостел",             "m"),
    PlaceEntry("post office",          "почта",              "f"),
    PlaceEntry("bank",                 "банк",               "m"),
    PlaceEntry("embassy",              "посольство",         "n"),
    PlaceEntry("pharmacy",             "аптека",             "f"),
    PlaceEntry("hospital",             "больница",           "f"),
    PlaceEntry("clinic / doctor",      "поликлиника",        "f"),
    PlaceEntry("police station",       "полицейский участок","m"),
    PlaceEntry("fire station",         "пожарная станция",   "f"),
    // Shopping & food
    PlaceEntry("shop / store",         "магазин",            "m"),
    PlaceEntry("supermarket",          "супермаркет",        "m"),
    PlaceEntry("shopping center",      "торговый центр",     "m"),
    PlaceEntry("market / bazaar",      "рынок",              "m"),
    PlaceEntry("restaurant",           "ресторан",           "m"),
    PlaceEntry("café",                 "кафе",               "m*",  "indeclinable"),
    PlaceEntry("bar",                  "бар",                "m"),
    PlaceEntry("bakery",               "булочная",           "f"),
    // Culture & education
    PlaceEntry("school",               "школа",              "f"),
    PlaceEntry("university",           "университет",        "m"),
    PlaceEntry("library",              "библиотека",         "f"),
    PlaceEntry("museum",               "музей",              "m"),
    PlaceEntry("cinema",               "кинотеатр",          "m"),
    PlaceEntry("theater",              "театр",              "m"),
    PlaceEntry("opera (house)",        "опера",              "f"),
    PlaceEntry("concert hall",         "концертный зал",     "m"),
    PlaceEntry("gallery",              "галерея",            "f"),
    PlaceEntry("church",               "церковь",            "f"),
    PlaceEntry("cathedral",            "собор",              "m"),
    PlaceEntry("mosque",               "мечеть",             "f"),
    // Recreation
    PlaceEntry("stadium",              "стадион",            "m"),
    PlaceEntry("sports hall / gym",    "спортзал",           "m"),
    PlaceEntry("zoo",                  "зоопарк",            "m"),
    PlaceEntry("swimming pool",        "бассейн",            "m"),
    // Other
    PlaceEntry("home / house",         "дом",                "m"),
    PlaceEntry("apartment / flat",     "квартира",           "f"),
    PlaceEntry("office",               "офис",               "m"),
    PlaceEntry("factory",              "завод",              "m"),
    PlaceEntry("border",               "граница",            "f"),
)

// ── UI helpers ────────────────────────────────────────────────────────────────

@Composable
private fun PlacesSectionHeader(title: String) {
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
private fun GenderLegendCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
            Text("m = masculine  ", style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text("f = feminine  ", style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold)
            Text("n = neuter  ", style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.tertiary, fontWeight = FontWeight.Bold)
            Text("m* = indeclinable", style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun PlacesTableHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text("English",  style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.8f))
        Text("Russian",  style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.8f))
        Text("Gender",   style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.7f))
    }
    HorizontalDivider()
}

@Composable
private fun PlaceRow(entry: PlaceEntry, index: Int) {
    val bg = if (index % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
             else MaterialTheme.colorScheme.surface
    val genderColor = when (entry.gender) {
        "f"  -> MaterialTheme.colorScheme.secondary
        "n"  -> MaterialTheme.colorScheme.tertiary
        "pl" -> MaterialTheme.colorScheme.outline
        else -> MaterialTheme.colorScheme.primary
    }
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = bg),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 2.dp, vertical = 2.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(entry.english, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.8f))
                Text(entry.russian, style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1.8f))
                Text(entry.gender,  style = MaterialTheme.typography.bodySmall,
                    color = genderColor, fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(0.7f))
            }
            if (entry.note.isNotEmpty()) {
                Text(entry.note, style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(start = 2.dp, bottom = 2.dp))
            }
        }
    }
}

// ── Screen ────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlacesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Places") },
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
            item { PlacesSectionHeader("Places  (Места)") }
            item { GenderLegendCard() }
            item { Spacer(modifier = Modifier.height(4.dp)) }
            item { PlacesTableHeader() }
            items(placeWords.mapIndexed { i, e -> i to e }) { (i, entry) ->
                PlaceRow(entry, i)
            }
        }
    }
}
