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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

private data class AdvTenseEntry(
    val name: String,
    val usage: String,
    val example: String,
    val exampleTranslation: String,
    val extraNote: String = "",
    val eu: String,
    val tu: String,
    val ele: String,
    val nos: String,
    val vos: String,
    val eles: String
)

private val advancedTenses = listOf(
    AdvTenseEntry(
        name = "Mais-que-Perfeito",
        usage = "Pluperfect — completed before another past action; formal/literary.",
        example = "Quando cheguei, ela já partira.",
        exampleTranslation = "When I arrived, she had already left.",
        eu = "falara / comera / partira",
        tu = "falaras / comeras / partiras",
        ele = "falara / comera / partira",
        nos = "faláramos / comêramos / partíramos",
        vos = "faláreis / comêreis / partíreis",
        eles = "falaram / comeram / partiram"
    ),
    AdvTenseEntry(
        name = "Condicional",
        usage = "Hypothetical or polite 'would'.",
        example = "Eu comeria mais, mas estou cheio.",
        exampleTranslation = "I would eat more, but I am full.",
        eu = "falaria / comeria / partiria",
        tu = "falarias / comerias / partirias",
        ele = "falaria / comeria / partiria",
        nos = "falaríamos / comeríamos / partiríamos",
        vos = "falaríeis / comeríeis / partiríeis",
        eles = "falariam / comeriam / partiriam"
    ),
    AdvTenseEntry(
        name = "Conjuntivo Presente",
        usage = "Doubt, desire, emotion, uncertainty. Triggered by 'que', 'embora', etc.",
        example = "Espero que tu fales com ela.",
        exampleTranslation = "I hope you speak with her.",
        eu = "fale / coma / parta",
        tu = "fales / comas / partas",
        ele = "fale / coma / parta",
        nos = "falemos / comamos / partamos",
        vos = "faleis / comais / partais",
        eles = "falem / comam / partam"
    ),
    AdvTenseEntry(
        name = "Conjuntivo Imperfeito",
        usage = "Hypothetical/contrary-to-fact 'se' clauses.",
        example = "Se eu falasse melhor, conseguia o emprego.",
        exampleTranslation = "If I spoke better, I'd get the job.",
        eu = "falasse / comesse / partisse",
        tu = "falasses / comesses / partisses",
        ele = "falasse / comesse / partisse",
        nos = "falássemos / comêssemos / partíssemos",
        vos = "falásseis / comêsseis / partísseis",
        eles = "falassem / comessem / partissem"
    ),
    AdvTenseEntry(
        name = "Conjuntivo Futuro",
        usage = "Hypothetical future conditions; common in formal/legal writing.",
        example = "Quando fores a Lisboa, visita o castelo.",
        exampleTranslation = "When you go to Lisbon, visit the castle.",
        eu = "falar / comer / partir",
        tu = "falares / comeres / partires",
        ele = "falar / comer / partir",
        nos = "falarmos / comermos / partirmos",
        vos = "falardes / comerdes / partirdes",
        eles = "falarem / comerem / partirem"
    ),
    AdvTenseEntry(
        name = "Imperativo Afirmativo",
        usage = "Direct positive commands (no 'eu' form).",
        example = "Fala mais devagar, por favor!",
        exampleTranslation = "Speak more slowly, please!",
        eu = "—",
        tu = "fala / come / parte",
        ele = "fale / coma / parta",
        nos = "falemos / comamos / partamos",
        vos = "falai / comei / parti",
        eles = "falem / comam / partam"
    ),
    AdvTenseEntry(
        name = "Imperativo Negativo",
        usage = "Prohibitions; uses Conjuntivo Presente forms.",
        example = "Não fales tão alto!",
        exampleTranslation = "Don't speak so loudly!",
        eu = "—",
        tu = "não fales / não comas / não partas",
        ele = "não fale / não coma / não parta",
        nos = "não falemos / não comamos / não partamos",
        vos = "não faleis / não comais / não partais",
        eles = "não falem / não comam / não partam"
    ),
    AdvTenseEntry(
        name = "Gerúndio",
        usage = "NOT used for continuous tenses in EP (use estar+a+inf). Used for simultaneous narrative actions.",
        example = "Fui embora, chorando de alegria.",
        exampleTranslation = "(literary only)",
        eu = "falando",
        tu = "comendo",
        ele = "partindo",
        nos = "(no conjugation)",
        vos = "—",
        eles = "—"
    ),
    AdvTenseEntry(
        name = "Infinitivo Pessoal",
        usage = "Uniquely Portuguese. Used after prepositions when the infinitive has its own subject.",
        example = "Antes de eles chegarem, preparei o jantar.",
        exampleTranslation = "Before they arrived, I prepared dinner.",
        extraNote = "'É importante estudar.' (impersonal) vs. 'É importante estudarmos.' (we specifically).",
        eu = "falar / comer / partir",
        tu = "falares / comeres / partires",
        ele = "falar / comer / partir",
        nos = "falarmos / comermos / partirmos",
        vos = "falardes / comerdes / partirdes",
        eles = "falarem / comerem / partirem"
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConjugationAdvancedScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Conjugation — Advanced") },
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
            item {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "These tables show regular patterns only. Irregular verbs may deviate significantly.",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            items(advancedTenses) { entry ->
                AdvTenseCard(entry)
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun AdvTenseCard(entry: AdvTenseEntry) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = entry.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = entry.usage,
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(fontStyle = FontStyle.Italic)) { append(entry.example) }
                    append("  —  ${entry.exampleTranslation}")
                },
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            if (entry.extraNote.isNotEmpty()) {
                Text(
                    text = entry.extraNote,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            AdvConjugationRow("Subject", "-ar (falar)", "-er (comer)", "-ir (partir)", isHeader = true)
            listOf(
                Triple("eu", entry.eu, Unit),
                Triple("tu", entry.tu, Unit),
                Triple("ele/ela", entry.ele, Unit),
                Triple("nós", entry.nos, Unit),
                Triple("vós", entry.vos, Unit),
                Triple("eles/elas", entry.eles, Unit)
            ).forEach { (subject, raw, _) ->
                val parts = raw.split(" / ")
                AdvConjugationRow(
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
private fun AdvConjugationRow(subject: String, ar: String, er: String, ir: String, isHeader: Boolean) {
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
