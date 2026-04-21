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
import androidx.compose.ui.unit.dp

private data class ConjRow(val pronoun: String, val form: String)

private val delatRows = listOf(
    ConjRow("já",       "dělám"),
    ConjRow("ty",       "děláš"),
    ConjRow("on/ona",   "dělá"),
    ConjRow("my",       "děláme"),
    ConjRow("vy",       "děláte"),
    ConjRow("oni/ony",  "dělají")
)

private val pracovat = listOf(
    ConjRow("já",       "pracuji / pracuju"),
    ConjRow("ty",       "pracuješ"),
    ConjRow("on/ona",   "pracuje"),
    ConjRow("my",       "pracujeme"),
    ConjRow("vy",       "pracujete"),
    ConjRow("oni/ony",  "pracují")
)

private val prositRows = listOf(
    ConjRow("já",       "prosím"),
    ConjRow("ty",       "prosíš"),
    ConjRow("on/ona",   "prosí"),
    ConjRow("my",       "prosíme"),
    ConjRow("vy",       "prosíte"),
    ConjRow("oni/ony",  "prosí")
)

private val umitRows = listOf(
    ConjRow("já",       "umím"),
    ConjRow("ty",       "umíš"),
    ConjRow("on/ona",   "umí"),
    ConjRow("my",       "umíme"),
    ConjRow("vy",       "umíte"),
    ConjRow("oni/ony",  "umí")
)

private val bytRows = listOf(
    ConjRow("já",       "jsem"),
    ConjRow("ty",       "jsi / jseš"),
    ConjRow("on/ona",   "je"),
    ConjRow("my",       "jsme"),
    ConjRow("vy",       "jste"),
    ConjRow("oni/ony",  "jsou")
)

private val mitRows = listOf(
    ConjRow("já",       "mám"),
    ConjRow("ty",       "máš"),
    ConjRow("on/ona",   "má"),
    ConjRow("my",       "máme"),
    ConjRow("vy",       "máte"),
    ConjRow("oni/ony",  "mají")
)

@Composable
private fun SectionHeader(title: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 4.dp)) {
        Text(text = title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun ConjTable(title: String, rows: List<ConjRow>) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            rows.forEach { row ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                    Text(row.pronoun, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f), color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(row.form, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(2f))
                }
            }
        }
    }
}

@Composable
private fun PastTenseCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Czech past tense uses the l-participle + the present tense of být (except 3rd person). The participle agrees in gender and number.",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "dělat (to do) — past tense", style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(6.dp))
            listOf(
                Triple("Masculine sg",  "dělal",  "já/ty/on jsem/jsi/Ø dělal"),
                Triple("Feminine sg",   "dělala", "já/ty/ona jsem/jsi/Ø dělala"),
                Triple("Neuter sg",     "dělalo", "ono Ø dělalo"),
                Triple("Masc. anim. pl","dělali", "my/vy/oni jsme/jste/Ø dělali"),
                Triple("Other pl",      "dělaly", "my/vy/ony jsme/jste/Ø dělaly")
            ).forEach { (gender, form, example) ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                    Text(gender, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.8f))
                    Text(form, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1f))
                    Text(example, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.weight(2.5f))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Note: in 3rd person (on/ona/ono/oni/ony), být is omitted — no auxiliary verb is used.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
private fun FutureCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Imperfective future: conjugate být (to be) + imperfective infinitive.", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            listOf(
                Pair("já budu",       "já budu dělat"),
                Pair("ty budeš",      "ty budeš dělat"),
                Pair("on/ona bude",   "on/ona bude dělat"),
                Pair("my budeme",     "my budeme dělat"),
                Pair("vy budete",     "vy budete dělat"),
                Pair("oni/ony budou", "oni/ony budou dělat")
            ).forEach { (byt, example) ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                    Text(byt, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.5f), color = MaterialTheme.colorScheme.primary)
                    Text(example, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(2f))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Perfective future: conjugate the perfective verb like a present-tense verb.",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "• udělám (I will do/make) / uděláš / udělá / uděláme / uděláte / udělají",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ImperativeCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Imperatives are formed from the present-tense stem. The ty (singular informal) form is the base.",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            listOf(
                Triple("ty form", "bare stem (+ -i/-ej if needed)", "dělej! (do it!)"),
                Triple("vy form", "ty form + -te", "dělejte! (do it! — formal/plural)"),
                Triple("my form", "ty form + -me", "dělejme! (let's do it!)")
            ).forEach { (label, rule, ex) ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                    Text(label, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1f))
                    Text(rule,  style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(2f))
                    Text(ex,    style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.weight(1.5f))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(6.dp))
            Text("More examples:", style = MaterialTheme.typography.labelMedium)
            listOf(
                "mluvit → mluv / mluvte  (speak!)",
                "číst → čti / čtěte  (read!)",
                "psát → piš / pište  (write!)"
            ).forEach {
                Text("• $it", style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(vertical = 1.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerbConjugationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Verb Conjugation") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item { SectionHeader("Present Tense") }
            item {
                Text(
                    text = "Czech verbs belong to conjugation classes based on their infinitive ending. The main patterns are shown below.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            item { ConjTable("Class 1 (-at verbs): dělat (to do/make)", delatRows) }
            item { ConjTable("Class 2 (-ovat verbs): pracovat (to work)", pracovat) }
            item { ConjTable("Class 3 (-it/-et/-ít verbs): prosit (to ask/beg)", prositRows) }
            item { ConjTable("Class 3 variant (-ít verbs): umět (to know how to)", umitRows) }
            item { SectionHeader("Irregular Verbs") }
            item { ConjTable("být (to be)", bytRows) }
            item { ConjTable("mít (to have)", mitRows) }
            item { SectionHeader("Past Tense") }
            item { PastTenseCard() }
            item { SectionHeader("Future Tense") }
            item { FutureCard() }
            item { SectionHeader("Imperative (Commands)") }
            item { ImperativeCard() }
        }
    }
}
