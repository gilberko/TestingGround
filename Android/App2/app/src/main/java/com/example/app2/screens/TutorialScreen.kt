package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
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

private data class TenseEntry(
    val name: String,
    val eu: String,
    val tu: String,
    val ele: String,
    val nos: String,
    val vos: String,
    val eles: String
)

private data class PrepEntry(
    val name: String,
    val usage: String
)

private val tenseEntries = listOf(
    TenseEntry(
        name = "Presente",
        eu = "falo / como / parto",
        tu = "falas / comes / partes",
        ele = "fala / come / parte",
        nos = "falamos / comemos / partimos",
        vos = "falais / comeis / partis",
        eles = "falam / comem / partem"
    ),
    TenseEntry(
        name = "Pret. Perfeito",
        eu = "falei / comi / parti",
        tu = "falaste / comeste / partiste",
        ele = "falou / comeu / partiu",
        nos = "falámos / comemos / partimos",
        vos = "falastes / comestes / partistes",
        eles = "falaram / comeram / partiram"
    ),
    TenseEntry(
        name = "Pret. Imperfeito",
        eu = "falava / comia / partia",
        tu = "falavas / comias / partias",
        ele = "falava / comia / partia",
        nos = "falávamos / comíamos / partíamos",
        vos = "faláveis / comíeis / partíeis",
        eles = "falavam / comiam / partiam"
    ),
    TenseEntry(
        name = "Mais-que-Perf.",
        eu = "falara / comera / partira",
        tu = "falaras / comeras / partiras",
        ele = "falara / comera / partira",
        nos = "faláramos / comêramos / partíramos",
        vos = "faláreis / comêreis / partíreis",
        eles = "falaram / comeram / partiram"
    ),
    TenseEntry(
        name = "Futuro",
        eu = "falarei / comerei / partirei",
        tu = "falarás / comerás / partirás",
        ele = "falará / comerá / partirá",
        nos = "falaremos / comeremos / partiremos",
        vos = "falareis / comereis / partireis",
        eles = "falarão / comerão / partirão"
    ),
    TenseEntry(
        name = "Condicional",
        eu = "falaria / comeria / partiria",
        tu = "falarias / comerias / partirias",
        ele = "falaria / comeria / partiria",
        nos = "falaríamos / comeríamos / partiríamos",
        vos = "falaríeis / comeríeis / partiríeis",
        eles = "falariam / comeriam / partiriam"
    ),
    TenseEntry(
        name = "Conj. Presente",
        eu = "fale / coma / parta",
        tu = "fales / comas / partas",
        ele = "fale / coma / parta",
        nos = "falemos / comamos / partamos",
        vos = "faleis / comais / partais",
        eles = "falem / comam / partam"
    ),
    TenseEntry(
        name = "Conj. Imperfeito",
        eu = "falasse / comesse / partisse",
        tu = "falasses / comesses / partisses",
        ele = "falasse / comesse / partisse",
        nos = "falássemos / comêssemos / partíssemos",
        vos = "falásseis / comêsseis / partísseis",
        eles = "falassem / comessem / partissem"
    ),
    TenseEntry(
        name = "Conj. Futuro",
        eu = "falar / comer / partir",
        tu = "falares / comeres / partires",
        ele = "falar / comer / partir",
        nos = "falarmos / comermos / partirmos",
        vos = "falardes / comerdes / partirdes",
        eles = "falarem / comerem / partirem"
    ),
    TenseEntry(
        name = "Imp. Afirmativo",
        eu = "—",
        tu = "fala / come / parte",
        ele = "fale / coma / parta",
        nos = "falemos / comamos / partamos",
        vos = "falai / comei / parti",
        eles = "falem / comam / partam"
    ),
    TenseEntry(
        name = "Imp. Negativo",
        eu = "—",
        tu = "não fales / não comas / não partas",
        ele = "não fale / não coma / não parta",
        nos = "não falemos / não comamos / não partamos",
        vos = "não faleis / não comais / não partais",
        eles = "não falem / não comam / não partam"
    ),
    TenseEntry(
        name = "Gerúndio",
        eu = "falando",
        tu = "comendo",
        ele = "partindo",
        nos = "(no conjugation)",
        vos = "—",
        eles = "—"
    )
)

private val prepEntries = listOf(
    PrepEntry("a", "Direction/movement to a place; time (\"às 3h\"); indirect object marker; \"ir a + city\""),
    PrepEntry("de", "Origin (\"sou de Portugal\"); possession; material; verbs: gostar de, precisar de, lembrar-se de"),
    PrepEntry("em", "Location (in/at/on); time periods (\"em janeiro\"); result state; contracts na/no/numa/num"),
    PrepEntry("para", "Permanent destination; purpose/goal; recipient; opinion (\"para mim\")"),
    PrepEntry("por", "Reason/motive; movement through a place; duration; exchange/price; passive voice agent"),
    PrepEntry("com", "Accompaniment; instrument; verbs: casar com, contar com, sonhar com, concordar com"),
    PrepEntry("sem", "Negation of accompaniment (\"sem dinheiro\"); used before nouns and infinitives"),
    PrepEntry("sobre", "Topic (\"falar sobre\"); physical position on top of / above"),
    PrepEntry("entre", "Between two things or people; among a group"),
    PrepEntry("até", "End point in time (\"até amanhã\") or space (\"até ao rio\"); inclusive endpoint"),
    PrepEntry("desde", "Starting point in time (\"desde 2018\"); often paired with até")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tutorial") },
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
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Section A: Regular Conjugations
            item {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Regular Verb Conjugations",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "These tables show regular patterns only. Irregular verbs may deviate significantly.",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            items(tenseEntries) { entry ->
                TenseCard(entry)
            }

            item {
                Text(
                    text = "Passive voice uses ser conjugated + past participle (-ado for -ar, -ido for -er/-ir).",
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }

            // Section B: Prepositions
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Prepositions",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
            }

            items(prepEntries) { entry ->
                PrepCard(entry)
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun TenseCard(entry: TenseEntry) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = entry.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            // Header row
            ConjugationRow(
                subject = "Subject",
                ar = "-ar (falar)",
                er = "-er (comer)",
                ir = "-ir (partir)",
                isHeader = true
            )
            // Split each conjugation cell by " / " to get ar/er/ir forms
            val euParts = entry.eu.split(" / ")
            val tuParts = entry.tu.split(" / ")
            val eleParts = entry.ele.split(" / ")
            val nosParts = entry.nos.split(" / ")
            val vosParts = entry.vos.split(" / ")
            val elesParts = entry.eles.split(" / ")

            val allRows = listOf(
                Triple("eu", euParts, entry.eu),
                Triple("tu", tuParts, entry.tu),
                Triple("ele/ela", eleParts, entry.ele),
                Triple("nós", nosParts, entry.nos),
                Triple("vós", vosParts, entry.vos),
                Triple("eles/elas", elesParts, entry.eles)
            )

            allRows.forEach { (subject, parts, raw) ->
                ConjugationRow(
                    subject = subject,
                    ar = parts.getOrElse(0) { raw },
                    er = parts.getOrElse(1) { "—" },
                    ir = parts.getOrElse(2) { "—" },
                    isHeader = false
                )
            }
        }
    }
}

@Composable
private fun ConjugationRow(subject: String, ar: String, er: String, ir: String, isHeader: Boolean) {
    val style = if (isHeader) MaterialTheme.typography.labelSmall else MaterialTheme.typography.bodySmall
    val fontWeight = if (isHeader) FontWeight.Bold else FontWeight.Normal
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = subject, style = style, fontWeight = fontWeight, modifier = Modifier.weight(0.7f))
        Text(text = ar, style = style, fontWeight = fontWeight, modifier = Modifier.weight(1f))
        Text(text = er, style = style, fontWeight = fontWeight, modifier = Modifier.weight(1f))
        Text(text = ir, style = style, fontWeight = fontWeight, modifier = Modifier.weight(1f))
    }
}

@Composable
private fun PrepCard(entry: PrepEntry) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = entry.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = entry.usage,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
