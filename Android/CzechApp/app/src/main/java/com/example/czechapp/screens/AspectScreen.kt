package com.example.czechapp.screens

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

private data class AspectPair(val imperfective: String, val perfective: String, val english: String)

private val aspectPairs = listOf(
    AspectPair("dělat",      "udělat",      "to do/make"),
    AspectPair("číst",       "přečíst",     "to read"),
    AspectPair("psát",       "napsat",      "to write"),
    AspectPair("jíst",       "sníst",       "to eat"),
    AspectPair("pít",        "vypít",       "to drink"),
    AspectPair("mluvit",     "promluvit",   "to speak"),
    AspectPair("kupovat",    "koupit",       "to buy"),
    AspectPair("dávat",      "dát",         "to give"),
    AspectPair("vidět",      "uvidět",      "to see"),
    AspectPair("slyšet",     "uslyšet",     "to hear"),
    AspectPair("brát",       "vzít",        "to take"),
    AspectPair("začínat",    "začít",       "to start"),
    AspectPair("končit",     "skončit",     "to finish"),
    AspectPair("přicházet",  "přijít",      "to arrive (on foot)"),
    AspectPair("odcházet",   "odejít",      "to leave (on foot)"),
    AspectPair("otvírat",    "otevřít",     "to open"),
    AspectPair("zavírat",    "zavřít",      "to close"),
    AspectPair("pomáhat",    "pomoct",      "to help")
)

@Composable
private fun SectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(text = title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AspectScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Aspect") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item { SectionHeader("What is Aspect?") }
            item {
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Almost every Czech verb comes in two versions — imperfective and perfective — that express different views of the same action.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text("Imperfective (nedokonavý)", style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            "Describes an ongoing, repeated, or habitual action — the process, not the result. Can express present, past, and future tenses.",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text("• Čtu knihu. (I am reading a book — right now.)", style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(top = 2.dp))
                        Spacer(modifier = Modifier.height(10.dp))
                        Text("Perfective (dokonavý)", style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.secondary)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            "Describes a completed action with a definite result. Has NO present tense — its present-style conjugation expresses future meaning.",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text("• Přečetl jsem knihu. (I finished reading the book — it's done.)", style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(top = 2.dp))
                        Spacer(modifier = Modifier.height(10.dp))
                        HorizontalDivider()
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Verbs below are listed as: Imperfective / Perfective. Most perfective verbs are formed by adding a prefix to the imperfective root.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            item { SectionHeader("Common Aspect Pairs") }
            item {
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                            Text("English",      style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                            Text("Imperfective", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.3f))
                            Text("Perfective",   style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1.3f))
                        }
                        HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                        aspectPairs.forEachIndexed { i, pair ->
                            val bg = if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(0.4f) else MaterialTheme.colorScheme.surface
                            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp), colors = CardDefaults.cardColors(bg), elevation = CardDefaults.cardElevation(0.dp)) {
                                Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp)) {
                                    Text(pair.english,      style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.5f))
                                    Text(pair.imperfective, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.3f))
                                    Text(pair.perfective,   style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1.3f))
                                }
                            }
                        }
                    }
                }
            }
            item { SectionHeader("Aspect & Tense Summary") }
            item {
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                            Text("Tense",         style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                            Text("Imperfective",  style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
                            Text("Perfective",    style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1.5f))
                        }
                        HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                        listOf(
                            Triple("Present",  "dělám (I do/am doing)", "— (no present tense)"),
                            Triple("Past",     "dělal jsem (I was doing)", "udělal jsem (I did/completed)"),
                            Triple("Future",   "budu dělat (I will be doing)", "udělám (I will do/complete)")
                        ).forEach { (tense, imp, perf) ->
                            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp)) {
                                Text(tense, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                                Text(imp,   style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
                                Text(perf,  style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1.5f))
                            }
                        }
                    }
                }
            }
        }
    }
}
