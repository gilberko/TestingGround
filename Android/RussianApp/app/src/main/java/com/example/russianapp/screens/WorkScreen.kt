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

private data class WorkEntry(
    val english: String,
    val russian: String,
    val gender: String,
    val note: String = ""
)

private data class JobEntry(
    val english: String,
    val masculine: String,
    val feminine: String,
    val note: String = ""
)

private val workWords = listOf(
    // General
    WorkEntry("work / job",           "работа",           "f"),
    WorkEntry("labour / toil",        "труд",             "m"),
    WorkEntry("profession",           "профессия",        "f"),
    WorkEntry("career",               "карьера",          "f"),
    WorkEntry("salary / wage",        "зарплата",         "f"),
    WorkEntry("bonus",                "премия",           "f"),
    WorkEntry("vacation / leave",     "отпуск",           "m"),
    WorkEntry("sick leave",           "больничный",       "m"),
    WorkEntry("experience",           "опыт",             "m"),
    WorkEntry("skill",                "навык",            "m"),
    WorkEntry("qualification",        "квалификация",     "f"),
    WorkEntry("resume / CV",          "резюме",           "n*",  "indeclinable"),
    WorkEntry("interview",            "собеседование",    "n"),
    WorkEntry("contract",             "контракт",         "m"),
    WorkEntry("agreement",            "договор",          "m"),
    // Workplace
    WorkEntry("office",               "офис",             "m"),
    WorkEntry("company / firm",       "компания",         "f"),
    WorkEntry("firm",                 "фирма",            "f"),
    WorkEntry("organisation",         "организация",      "f"),
    WorkEntry("department",           "отдел",            "m"),
    WorkEntry("branch",               "филиал",           "m"),
    WorkEntry("headquarters",         "главный офис",     "m"),
    WorkEntry("desk",                 "стол",             "m"),
    WorkEntry("computer",             "компьютер",        "m"),
    WorkEntry("laptop",               "ноутбук",          "m"),
    WorkEntry("printer",              "принтер",          "m"),
    WorkEntry("phone",                "телефон",          "m"),
    WorkEntry("email",                "электронная почта","f"),
    WorkEntry("meeting / conference", "совещание",        "n"),
    WorkEntry("meeting (personal)",   "встреча",          "f"),
    WorkEntry("presentation",         "презентация",      "f"),
    WorkEntry("report",               "отчёт",            "m"),
    WorkEntry("project",              "проект",           "m"),
    WorkEntry("task / assignment",    "задание",          "n"),
    WorkEntry("deadline",             "дедлайн",          "m"),
    WorkEntry("negotiations",         "переговоры",       "pl"),
    WorkEntry("client",               "клиент",           "m"),
    WorkEntry("partner",              "партнёр",          "m"),
    WorkEntry("business trip",        "командировка",     "f"),
    WorkEntry("lunch break",          "обеденный перерыв","m"),
    // People at work
    WorkEntry("colleague (m)",        "коллега",          "m",   "same word used for f"),
    WorkEntry("colleague (f)",        "коллега",          "f",   "same word as m"),
    WorkEntry("boss / manager",       "начальник",        "m"),
    WorkEntry("boss / manager (f)",   "начальница",       "f"),
    WorkEntry("employee (m)",         "сотрудник",        "m"),
    WorkEntry("employee (f)",         "сотрудница",       "f"),
    WorkEntry("intern",               "стажёр",           "m"),
    WorkEntry("secretary (m)",        "секретарь",        "m"),
    WorkEntry("secretary (f)",        "секретарша",       "f"),
)

private val jobWords = listOf(
    JobEntry("manager",            "менеджер",       "менеджер",       "same form; clarify by context"),
    JobEntry("director",           "директор",       "директор",       "same form"),
    JobEntry("doctor",             "врач",           "врач",           "same form; врачиха – informal"),
    JobEntry("dentist",            "стоматолог",     "стоматолог",     "same form"),
    JobEntry("nurse",              "медбрат",        "медсестра"),
    JobEntry("pharmacist",         "фармацевт",      "фармацевт",      "same form"),
    JobEntry("programmer",         "программист",    "программистка"),
    JobEntry("engineer",           "инженер",        "инженер",        "same form"),
    JobEntry("architect",          "архитектор",     "архитектор",     "same form"),
    JobEntry("teacher",            "учитель",        "учительница"),
    JobEntry("professor",          "профессор",      "профессор",      "same form"),
    JobEntry("student",            "студент",        "студентка"),
    JobEntry("pupil / schoolchild","школьник",       "школьница"),
    JobEntry("actor",              "актёр",          "актриса"),
    JobEntry("musician",           "музыкант",       "музыкантка"),
    JobEntry("artist / painter",   "художник",       "художница"),
    JobEntry("writer / author",    "писатель",       "писательница"),
    JobEntry("journalist",         "журналист",      "журналистка"),
    JobEntry("translator",         "переводчик",     "переводчица"),
    JobEntry("lawyer",             "юрист",          "юрист",          "same form; адвокат also common"),
    JobEntry("judge",              "судья",          "судья",          "same form"),
    JobEntry("accountant",         "бухгалтер",      "бухгалтер",      "same form"),
    JobEntry("economist",          "экономист",      "экономист",      "same form"),
    JobEntry("entrepreneur",       "предприниматель","предпринимательница"),
    JobEntry("chemist / scientist","химик",          "химик",          "same form"),
    JobEntry("biologist",          "биолог",         "биолог",         "same form"),
    JobEntry("physicist",          "физик",          "физик",          "same form"),
    JobEntry("cook / chef",        "повар",          "повариха"),
    JobEntry("waiter",             "официант",       "официантка"),
    JobEntry("driver",             "водитель",       "водительница"),
    JobEntry("pilot",              "пилот",          "пилот",          "same form"),
    JobEntry("police officer",     "полицейский",    "полицейская"),
    JobEntry("soldier",            "солдат",         "солдат",         "same; женщина-солдат for emphasis"),
    JobEntry("firefighter",        "пожарный",       "пожарная"),
    JobEntry("politician",         "политик",        "политик",        "same form"),
    JobEntry("president",          "президент",      "президент",      "same form"),
    JobEntry("minister",           "министр",        "министр",        "same form"),
    JobEntry("athlete / sportsman","спортсмен",      "спортсменка"),
    JobEntry("astronaut",          "космонавт",      "космонавт",      "same form"),
    JobEntry("farmer",             "фермер",         "фермер",         "same form"),
    JobEntry("builder",            "строитель",      "строительница"),
)

// ── UI helpers ────────────────────────────────────────────────────────────────

@Composable
private fun WorkSectionHeader(title: String) {
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
private fun WorkGenderLegend() {
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
            Text("pl / n* = special", style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun WorkTableHeader() {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text("English",  style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.8f))
        Text("Russian",  style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.8f))
        Text("Gen.",     style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.7f))
    }
    HorizontalDivider()
}

@Composable
private fun WorkRow(entry: WorkEntry, index: Int) {
    val bg = if (index % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
             else MaterialTheme.colorScheme.surface
    val genderColor = when (entry.gender) {
        "f"   -> MaterialTheme.colorScheme.secondary
        "n", "n*" -> MaterialTheme.colorScheme.tertiary
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

@Composable
private fun JobTableHeader() {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text("English",    style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
        Text("Masculine",  style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.4f))
        Text("Feminine",   style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1.4f))
    }
    HorizontalDivider()
}

@Composable
private fun JobRow(entry: JobEntry, index: Int) {
    val bg = if (index % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
             else MaterialTheme.colorScheme.surface
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = bg),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 2.dp, vertical = 2.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(entry.english,   style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.5f))
                Text(entry.masculine, style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1.4f))
                Text(entry.feminine,  style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1.4f))
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
fun WorkScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Work & Jobs") },
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
            item { WorkGenderLegend() }

            item { WorkSectionHeader("Work & Office  (Работа и офис)") }
            item { WorkTableHeader() }
            itemsIndexed(workWords) { i, entry -> WorkRow(entry, i) }

            item { Spacer(modifier = Modifier.height(8.dp)) }
            item { WorkSectionHeader("Jobs  (Профессии) — Masculine & Feminine") }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Text(
                        text = "Many Russian job titles have distinct masculine and feminine forms. " +
                                "When the same form is listed for both, it is used regardless of gender — " +
                                "the grammatical gender of adjectives/verbs still changes.",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
            item { JobTableHeader() }
            itemsIndexed(jobWords) { i, entry -> JobRow(entry, i) }
        }
    }
}
