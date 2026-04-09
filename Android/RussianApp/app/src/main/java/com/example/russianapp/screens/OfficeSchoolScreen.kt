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

private data class OfficeSchoolEntry(
    val english: String,
    val russian: String,
    val type: String,   // "m"|"f"|"n"|"pl"|"verb"
    val note: String = ""
)

private val officeSchoolSections: List<Pair<String, List<OfficeSchoolEntry>>> = listOf(
    "Stationery & Supplies" to listOf(
        OfficeSchoolEntry("pen",            "ручка",        "f"),
        OfficeSchoolEntry("pencil",         "карандаш",     "m"),
        OfficeSchoolEntry("sharpener",      "точилка",      "f"),
        OfficeSchoolEntry("eraser",         "ластик",       "m"),
        OfficeSchoolEntry("notebook",       "тетрадь",      "f"),
        OfficeSchoolEntry("book",           "книга",        "f"),
        OfficeSchoolEntry("textbook",       "учебник",      "m"),
        OfficeSchoolEntry("stapler",        "степлер",      "m"),
        OfficeSchoolEntry("staple",         "скоба",        "f"),
        OfficeSchoolEntry("chalk",          "мел",          "m"),
        OfficeSchoolEntry("printer",        "принтер",      "m")
    ),
    "People & Places" to listOf(
        OfficeSchoolEntry("teacher",        "учитель",      "m",    "fem.: учительница"),
        OfficeSchoolEntry("principal",      "директор",     "m",    "used for both genders"),
        OfficeSchoolEntry("desk",           "парта",        "f",    "school desk; office desk: стол (m)"),
        OfficeSchoolEntry("blackboard",     "доска",        "f"),
        OfficeSchoolEntry("class",          "класс",        "m",    "also: урок (lesson)"),
        OfficeSchoolEntry("lunch",          "обед",         "m")
    ),
    "Tasks & Assessments" to listOf(
        OfficeSchoolEntry("homework",       "домашнее задание", "n"),
        OfficeSchoolEntry("task",           "задание",      "n"),
        OfficeSchoolEntry("exam",           "экзамен",      "m"),
        OfficeSchoolEntry("grades",         "оценки",       "pl"),
        OfficeSchoolEntry("school report",  "табель",       "m"),
        OfficeSchoolEntry("report",         "отчёт",        "m"),
        OfficeSchoolEntry("presentation",   "презентация",  "f"),
        OfficeSchoolEntry("meeting",        "совещание",    "n")
    ),
    "Actions" to listOf(
        OfficeSchoolEntry("to draw",        "рисовать",     "verb"),
        OfficeSchoolEntry("to write",       "писать",       "verb"),
        OfficeSchoolEntry("to describe",    "описывать",    "verb"),
        OfficeSchoolEntry("to learn",       "учить",        "verb",  "to memorise / learn sth"),
        OfficeSchoolEntry("to study",       "учиться",      "verb",  "to study (intransitive)"),
        OfficeSchoolEntry("to practice",    "практиковать", "verb"),
        OfficeSchoolEntry("to prepare",     "подготовиться","verb"),
        OfficeSchoolEntry("to print",       "распечатать",  "verb")
    )
)

// ── Composables ───────────────────────────────────────────────────────────────

@Composable
private fun OfficeSchoolSectionHeader(title: String) {
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

private fun officeTypeLabel(t: String) = when (t) {
    "m"    -> "masc."
    "f"    -> "fem."
    "n"    -> "neut."
    "pl"   -> "pl."
    "verb" -> "verb"
    else   -> t
}

private fun officeTypeColor(t: String, scheme: androidx.compose.material3.ColorScheme) = when (t) {
    "m"    -> scheme.primary
    "f"    -> scheme.secondary
    "n"    -> scheme.tertiary
    "verb" -> scheme.tertiary
    else   -> scheme.outline
}

@Composable
private fun OfficeSchoolTableHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp)
    ) {
        Text("English",  style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.8f))
        Text("Russian",  style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.8f))
        Text("Type",     style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.8f))
    }
}

@Composable
private fun OfficeSchoolRow(entry: OfficeSchoolEntry, isEven: Boolean) {
    val bg = if (isEven)
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
    else
        MaterialTheme.colorScheme.surface
    val tColor = officeTypeColor(entry.type, MaterialTheme.colorScheme)
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp),
        colors = CardDefaults.cardColors(containerColor = bg),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(entry.english, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.8f))
                Text(entry.russian, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.8f))
                Text(officeTypeLabel(entry.type), style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold, color = tColor, modifier = Modifier.weight(0.8f))
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
private fun OfficeSchoolLegendCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("Type legend:", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            Row(modifier = Modifier.fillMaxWidth().padding(top = 6.dp)) {
                Text("masc.", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1f))
                Text("masculine noun", style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(3f))
            }
            Row(modifier = Modifier.fillMaxWidth().padding(top = 4.dp)) {
                Text("fem.", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1f))
                Text("feminine noun", style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(3f))
            }
            Row(modifier = Modifier.fillMaxWidth().padding(top = 4.dp)) {
                Text("neut.", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary, modifier = Modifier.weight(1f))
                Text("neuter noun", style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(3f))
            }
            Row(modifier = Modifier.fillMaxWidth().padding(top = 4.dp)) {
                Text("pl.", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.outline, modifier = Modifier.weight(1f))
                Text("plural-only noun", style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(3f))
            }
            Row(modifier = Modifier.fillMaxWidth().padding(top = 4.dp)) {
                Text("verb", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary, modifier = Modifier.weight(1f))
                Text("verb (imperfective aspect)", style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(3f))
            }
        }
    }
}

// ── Screen ────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OfficeSchoolScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Office & School") },
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
                OfficeSchoolLegendCard()
            }
            officeSchoolSections.forEach { (sectionTitle, entries) ->
                item(key = "header_$sectionTitle") {
                    OfficeSchoolSectionHeader(sectionTitle)
                    OfficeSchoolTableHeader()
                }
                items(entries.size, key = { "$sectionTitle-$it" }) { index ->
                    OfficeSchoolRow(entries[index], isEven = index % 2 == 0)
                }
            }
        }
    }
}
