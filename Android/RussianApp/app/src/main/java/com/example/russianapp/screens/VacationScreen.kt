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

private data class VacationEntry(
    val english: String,
    val russian: String,
    val gender: String,    // "m", "f", "n", "m*", "pl"
    val note: String = ""
)

private val vacationWords = listOf(
    VacationEntry("vacation (from work)",   "отпуск",               "m"),
    VacationEntry("vacation (school)",      "каникулы",             "pl",  "plural-only form"),
    VacationEntry("rest / relaxation",      "отдых",                "m"),
    VacationEntry("hotel",                  "гостиница",            "f"),
    VacationEntry("hotel (loanword)",       "отель",                "m*",  "indeclinable — always отель"),
    VacationEntry("guesthouse",             "пансион",              "m"),
    VacationEntry("beach",                  "пляж",                 "m"),
    VacationEntry("ski / skis",             "лыжи",                 "pl",  "sg: лыжа (f)"),
    VacationEntry("resort",                 "курорт",               "m"),
    VacationEntry("waterpark",              "аквапарк",             "m"),
    VacationEntry("amusement park",         "парк аттракционов",    "m",   "lit. \"park of attractions\""),
    VacationEntry("roller coaster",         "американские горки",   "pl",  "lit. \"American hills\""),
    VacationEntry("water slides",           "водные горки",         "pl",  "lit. \"water hills\""),
    VacationEntry("ocean",                  "океан",                "m"),
    VacationEntry("sea",                    "море",                 "n"),
    VacationEntry("pool",                   "бассейн",              "m"),
    VacationEntry("train",                  "поезд",                "m"),
    VacationEntry("ticket",                 "билет",                "m"),
    VacationEntry("airport",                "аэропорт",             "m"),
    VacationEntry("airplane",               "самолёт",              "m"),
    VacationEntry("flight",                 "рейс",                 "m"),
    VacationEntry("layover",                "пересадка",            "f"),
    VacationEntry("island",                 "остров",               "m"),
    VacationEntry("passport",               "паспорт",              "m"),
    VacationEntry("suitcase",               "чемодан",              "m"),
    VacationEntry("souvenir",               "сувенир",              "m"),
    VacationEntry("tour / excursion",       "экскурсия",            "f"),
    VacationEntry("sunglasses",             "солнечные очки",       "pl")
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
private fun VacationTableHeader() {
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
private fun VacationRow(entry: VacationEntry, isEven: Boolean) {
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
fun VacationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Vacation") },
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
                SectionHeader("Vacation & Travel")
                GenderLegendCard()
                VacationTableHeader()
            }
            items(vacationWords.size) { index ->
                VacationRow(vacationWords[index], isEven = index % 2 == 0)
            }
        }
    }
}
