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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class VerbEntry(val english: String, val imperfective: String, val perfective: String)

private val verbs = listOf(
    VerbEntry("to be",                  "být",          "—"),
    VerbEntry("to have",                "mít",          "—"),
    VerbEntry("to do / make",           "dělat",        "udělat"),
    VerbEntry("to say",                 "říkat",        "říct"),
    VerbEntry("to speak / talk",        "mluvit",       "promluvit"),
    VerbEntry("to go (on foot)",        "jít / chodit", "zajít / přijít"),
    VerbEntry("to go (by vehicle)",     "jet / jezdit", "zajet / přijet"),
    VerbEntry("to come / arrive",       "přicházet",    "přijít"),
    VerbEntry("to leave / go away",     "odcházet",     "odejít"),
    VerbEntry("to read",                "číst",         "přečíst"),
    VerbEntry("to write",               "psát",         "napsat"),
    VerbEntry("to think",               "myslet / přemýšlet", "pomyslet"),
    VerbEntry("to know (a fact)",       "vědět",        "—"),
    VerbEntry("to know (a person/place)","znát",        "poznat"),
    VerbEntry("to understand",          "rozumět",      "porozumět"),
    VerbEntry("to love / like",         "milovat / mít rád", "—"),
    VerbEntry("to want",                "chtít",        "—"),
    VerbEntry("to be able to / can",    "moci / umět",  "—"),
    VerbEntry("to need",                "potřebovat",   "—"),
    VerbEntry("to live",                "žít / bydlet", "—"),
    VerbEntry("to work",                "pracovat",     "—"),
    VerbEntry("to eat",                 "jíst",         "sníst"),
    VerbEntry("to drink",               "pít",          "vypít"),
    VerbEntry("to sleep",               "spát",         "vyspat se"),
    VerbEntry("to wake up",             "vstávat",      "vstát"),
    VerbEntry("to get dressed",         "oblékat se",   "obléct se"),
    VerbEntry("to cook / prepare",      "vařit",        "uvařit"),
    VerbEntry("to buy",                 "kupovat",      "koupit"),
    VerbEntry("to sell",                "prodávat",     "prodat"),
    VerbEntry("to help",                "pomáhat",      "pomoct"),
    VerbEntry("to ask (a question)",    "ptát se",      "zeptat se"),
    VerbEntry("to answer",              "odpovídat",    "odpovědět"),
    VerbEntry("to look / watch",        "dívat se",     "podívat se"),
    VerbEntry("to see",                 "vidět",        "uvidět"),
    VerbEntry("to listen",              "poslouchat",   "poslechnout"),
    VerbEntry("to hear",                "slyšet",       "uslyšet"),
    VerbEntry("to open",                "otvírat",      "otevřít"),
    VerbEntry("to close",               "zavírat",      "zavřít"),
    VerbEntry("to begin / start",       "začínat",      "začít"),
    VerbEntry("to finish / end",        "končit",       "skončit"),
    VerbEntry("to find",                "hledat → nacházet", "najít"),
    VerbEntry("to lose",                "ztrácet",      "ztratit"),
    VerbEntry("to learn / study",       "učit se",      "naučit se"),
    VerbEntry("to forget",              "zapomínat",    "zapomenout"),
    VerbEntry("to remember",            "pamatovat si", "zapamatovat si"),
    VerbEntry("to wait",                "čekat",        "počkat"),
    VerbEntry("to meet",                "setkávat se",  "setkat se"),
    VerbEntry("to pay",                 "platit",       "zaplatit"),
    VerbEntry("to take",                "brát",         "vzít"),
    VerbEntry("to give",                "dávat",        "dát")
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
fun VerbsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Verbs") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item { SectionHeader("Common Czech Verbs (Imperfective / Perfective)") }
            item {
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                            Text("English",      style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                            Text("Imperfective", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.3f))
                            Text("Perfective",   style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(1.3f))
                        }
                        HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                        verbs.forEachIndexed { i, v ->
                            val bg = if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(0.4f) else MaterialTheme.colorScheme.surface
                            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp), colors = CardDefaults.cardColors(bg), elevation = CardDefaults.cardElevation(0.dp)) {
                                Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp)) {
                                    Text(v.english,      style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.5f))
                                    Text(v.imperfective, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.3f))
                                    Text(
                                        v.perfective,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = if (v.perfective == "—") MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.secondary,
                                        modifier = Modifier.weight(1.3f)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
