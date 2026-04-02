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
import androidx.compose.foundation.lazy.itemsIndexed
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

private data class HouseEntry(
    val english: String,
    val russian: String,
    val gender: String,
    val note: String = ""
)

private val rooms = listOf(
    HouseEntry("house / home",              "дом",                      "m"),
    HouseEntry("apartment / flat",          "квартира",                 "f"),
    HouseEntry("room",                      "комната",                  "f"),
    HouseEntry("kitchen",                   "кухня",                    "f"),
    HouseEntry("bedroom",                   "спальня",                  "f"),
    HouseEntry("living room",               "гостиная",                 "f",  "adj used as noun"),
    HouseEntry("bathroom",                  "ванная",                   "f",  "adj used as noun; ванная комната"),
    HouseEntry("toilet",                    "туалет",                   "m"),
)

private val homeItems = listOf(
    HouseEntry("table",                     "стол",                     "m"),
    HouseEntry("chair",                     "стул",                     "m"),
    HouseEntry("sofa",                      "диван",                    "m"),
    HouseEntry("bed",                       "кровать",                  "f"),
    HouseEntry("curtains",                  "шторы",                    "pl", "sg: штора (f)"),
    HouseEntry("window",                    "окно",                     "n",  "pl: окна"),
    HouseEntry("lightbulb",                 "лампочка",                 "f"),
    HouseEntry("bathtub",                   "ванна",                    "f"),
    HouseEntry("shower",                    "душ",                      "m"),
    HouseEntry("pillow",                    "подушка",                  "f"),
    HouseEntry("blanket",                   "одеяло",                   "n"),
    HouseEntry("bedding / bed linen",       "постельное бельё",         "n"),
    HouseEntry("sheets",                    "простыни",                 "pl", "sg: простыня (f)"),
)

private val appliances = listOf(
    HouseEntry("television / TV",           "телевизор",                "m"),
    HouseEntry("air conditioner",           "кондиционер",              "m"),
    HouseEntry("washing machine",           "стиральная машина",        "f"),
    HouseEntry("dryer",                     "сушильная машина",         "f"),
    HouseEntry("oven",                      "духовка",                  "f"),
    HouseEntry("microwave",                 "микроволновка",            "f",  "also: микроволновая печь"),
    HouseEntry("freezer",                   "морозильник",              "m"),
    HouseEntry("refrigerator / fridge",     "холодильник",              "m"),
)

private val actions = listOf(
    HouseEntry("to clean / tidy up",        "убирать",                  "verb", "pf: убрать"),
    HouseEntry("to tidy",                   "прибираться",              "verb", "pf: прибраться; reflexive"),
    HouseEntry("to arrange / place things", "расставлять",              "verb", "pf: расставить"),
    HouseEntry("to paint (walls)",          "красить",                  "verb", "pf: покрасить"),
    HouseEntry("to take a shower",          "принимать душ",            "verb", "pf: принять душ"),
    HouseEntry("to change the sheets",      "менять бельё",             "verb", "pf: поменять бельё"),
    HouseEntry("to open the window(s)",     "открывать окно",           "verb", "pf: открыть окно"),
    HouseEntry("to close the window",       "закрывать окно",           "verb", "pf: закрыть окно"),
    HouseEntry("to turn on the lights",     "включать свет",            "verb", "pf: включить свет"),
    HouseEntry("to turn off the lights",    "выключать свет",           "verb", "pf: выключить свет"),
    HouseEntry("to replace a lightbulb",    "менять лампочку",          "verb", "pf: поменять лампочку"),
)

// ── UI helpers ────────────────────────────────────────────────────────────────

@Composable
private fun HouseSectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(text = title, style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp),
            color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun HouseGenderLegend() {
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
            Text("pl = plural", style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun HouseTableHeader() {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text("English", style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.8f))
        Text("Russian", style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.8f))
        Text("Gen.", style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.7f))
    }
    HorizontalDivider()
}

@Composable
private fun HouseActionTableHeader() {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text("English", style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.8f))
        Text("Russian (impf)", style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.8f))
        Text("Pf.", style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.7f))
    }
    HorizontalDivider()
}

@Composable
private fun HouseRow(entry: HouseEntry, index: Int) {
    val bg = if (index % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
             else MaterialTheme.colorScheme.surface
    val genderColor = when (entry.gender) {
        "f"   -> MaterialTheme.colorScheme.secondary
        "n"   -> MaterialTheme.colorScheme.tertiary
        "pl"  -> MaterialTheme.colorScheme.outline
        else  -> MaterialTheme.colorScheme.primary
    }
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = bg),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 2.dp, vertical = 2.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(entry.english, style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1.8f))
                Text(entry.russian, style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1.8f))
                Text(entry.gender, style = MaterialTheme.typography.bodySmall,
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

@Composable
private fun HouseActionRow(entry: HouseEntry, index: Int) {
    val bg = if (index % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
             else MaterialTheme.colorScheme.surface
    // Extract the pf form from note ("pf: xxx" → "xxx", stripping extra info after ";")
    val pfForm = entry.note.removePrefix("pf: ").substringBefore(";").trim()
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = bg),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 2.dp, vertical = 2.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(entry.english, style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1.8f))
                Text(entry.russian, style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1.8f))
                Text(pfForm, style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.weight(0.7f))
            }
            // Show any extra note after ";"
            val extra = entry.note.substringAfter(";", "").trim()
            if (extra.isNotEmpty()) {
                Text(extra, style = MaterialTheme.typography.bodySmall,
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
fun HouseScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("House & Home") },
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
            item { HouseGenderLegend() }

            item { HouseSectionHeader("Rooms & Spaces  (Комнаты и помещения)") }
            item { HouseTableHeader() }
            itemsIndexed(rooms) { i, entry -> HouseRow(entry, i) }

            item { Spacer(modifier = Modifier.height(8.dp)) }
            item { HouseSectionHeader("Furniture & Home Items  (Мебель и предметы быта)") }
            item { HouseTableHeader() }
            itemsIndexed(homeItems) { i, entry -> HouseRow(entry, i) }

            item { Spacer(modifier = Modifier.height(8.dp)) }
            item { HouseSectionHeader("Appliances  (Бытовая техника)") }
            item { HouseTableHeader() }
            itemsIndexed(appliances) { i, entry -> HouseRow(entry, i) }

            item { Spacer(modifier = Modifier.height(8.dp)) }
            item { HouseSectionHeader("Actions  (Действия)") }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Text(
                        text = "The Russian (impf) column shows the imperfective form. " +
                                "The Pf. column shows the perfective. Use imperfective for ongoing/habitual actions, " +
                                "perfective for completed single events.",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
            item { HouseActionTableHeader() }
            itemsIndexed(actions) { i, entry -> HouseActionRow(entry, i) }
        }
    }
}
