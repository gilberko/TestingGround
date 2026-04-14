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
import androidx.compose.material3.HorizontalDivider
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

private data class TenseEntry(
    val name: String,
    val usage: String,
    val example: String,
    val exampleTranslation: String,
    val formationRule: String,
    val suffixBase: String,
    val arSuf: List<String>,
    val erSuf: List<String>,
    val irSuf: List<String>,
    val eu: String,
    val tu: String,
    val ele: String,
    val nos: String,
    val vos: String,
    val eles: String
)

private val basicTenses = listOf(
    TenseEntry(
        name = "Presente do Indicativo",
        usage = "Expresses current actions, habitual facts, universal truths.",
        example = "Eu falo português todos os dias.",
        exampleTranslation = "I speak Portuguese every day.",
        formationRule = "Remove -ar / -er / -ir from the infinitive to get the stem. Then add the suffix for the subject. Note: -er and -ir differ for nós and vós.",
        suffixBase = "Stem (infinitive − -ar / -er / -ir)",
        arSuf = listOf("-o", "-as", "-a", "-amos", "-ais", "-am"),
        erSuf = listOf("-o", "-es", "-e", "-emos", "-eis", "-em"),
        irSuf = listOf("-o", "-es", "-e", "-imos", "-is", "-em"),
        eu = "falo / como / parto",
        tu = "falas / comes / partes",
        ele = "fala / come / parte",
        nos = "falamos / comemos / partimos",
        vos = "falais / comeis / partis",
        eles = "falam / comem / partem"
    ),
    TenseEntry(
        name = "Pretérito Perfeito",
        usage = "Completed past action with a defined endpoint.",
        example = "Ela comeu o jantar às oito.",
        exampleTranslation = "She ate dinner at eight.",
        formationRule = "Remove -ar / -er / -ir from the infinitive to get the stem. Then add the suffix for the subject.",
        suffixBase = "Stem (infinitive − -ar / -er / -ir)",
        arSuf = listOf("-ei", "-aste", "-ou", "-ámos", "-astes", "-aram"),
        erSuf = listOf("-i", "-este", "-eu", "-emos", "-estes", "-eram"),
        irSuf = listOf("-i", "-iste", "-iu", "-imos", "-istes", "-iram"),
        eu = "falei / comi / parti",
        tu = "falaste / comeste / partiste",
        ele = "falou / comeu / partiu",
        nos = "falámos / comemos / partimos",
        vos = "falastes / comestes / partistes",
        eles = "falaram / comeram / partiram"
    ),
    TenseEntry(
        name = "Pretérito Imperfeito",
        usage = "Ongoing/habitual past state or 'used to' actions.",
        example = "Quando era criança, eu falava muito.",
        exampleTranslation = "When I was a child, I used to talk a lot.",
        formationRule = "Remove -ar / -er / -ir from the infinitive to get the stem. Then add the suffix. -er and -ir verbs share the same endings.",
        suffixBase = "Stem (infinitive − -ar / -er / -ir)",
        arSuf = listOf("-ava", "-avas", "-ava", "-ávamos", "-áveis", "-avam"),
        erSuf = listOf("-ia", "-ias", "-ia", "-íamos", "-íeis", "-iam"),
        irSuf = listOf("-ia", "-ias", "-ia", "-íamos", "-íeis", "-iam"),
        eu = "falava / comia / partia",
        tu = "falavas / comias / partias",
        ele = "falava / comia / partia",
        nos = "falávamos / comíamos / partíamos",
        vos = "faláveis / comíeis / partíeis",
        eles = "falavam / comiam / partiam"
    ),
    TenseEntry(
        name = "Futuro do Presente",
        usage = "Future actions and predictions.",
        example = "Amanhã eles partirão cedo.",
        exampleTranslation = "Tomorrow they will leave early.",
        formationRule = "Keep the full infinitive intact (falar, comer, partir) and append the ending directly to it. All three verb types use exactly the same endings.",
        suffixBase = "Full infinitive (falar / comer / partir) +",
        arSuf = listOf("-ei", "-ás", "-á", "-emos", "-eis", "-ão"),
        erSuf = listOf("-ei", "-ás", "-á", "-emos", "-eis", "-ão"),
        irSuf = listOf("-ei", "-ás", "-á", "-emos", "-eis", "-ão"),
        eu = "falarei / comerei / partirei",
        tu = "falarás / comerás / partirás",
        ele = "falará / comerá / partirá",
        nos = "falaremos / comeremos / partiremos",
        vos = "falareis / comereis / partireis",
        eles = "falarão / comerão / partirão"
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConjugationBasicScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Conjugation — Basic") },
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

            items(basicTenses) { entry ->
                BasicTenseCard(entry)
            }

            item {
                NoteCard(
                    title = "Present Progressive",
                    lines = listOf(
                        NoteContent.Bold("European Portuguese uses estar + a + infinitive (NOT the gerund)."),
                        NoteContent.Example("Estou a falar.", "I am speaking."),
                        NoteContent.Plain("Brazilian Portuguese uses 'estou falando' — avoid this in EP.")
                    )
                )
            }

            item {
                NoteCard(
                    title = "Near Future (ir + infinitive)",
                    lines = listOf(
                        NoteContent.Bold("Very common alternative to the Futuro: ir (conjugated) + infinitive"),
                        NoteContent.Example("Vou falar com ela amanhã.", "I am going to speak with her tomorrow.")
                    )
                )
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

private sealed class NoteContent {
    data class Plain(val text: String) : NoteContent()
    data class Bold(val text: String) : NoteContent()
    data class Example(val pt: String, val en: String) : NoteContent()
}

@Composable
private fun NoteCard(title: String, lines: List<NoteContent>) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            lines.forEach { content ->
                when (content) {
                    is NoteContent.Plain -> Text(
                        text = content.text,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    is NoteContent.Bold -> Text(
                        text = content.text,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    is NoteContent.Example -> Column(modifier = Modifier.padding(bottom = 4.dp)) {
                        Text(
                            text = content.pt,
                            style = MaterialTheme.typography.bodyMedium,
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            text = content.en,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BasicTenseCard(entry: TenseEntry) {
    val allSame = entry.arSuf == entry.erSuf && entry.erSuf == entry.irSuf
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Tense name
            Text(
                text = entry.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            // Usage
            Text(
                text = entry.usage,
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            // Example
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(fontStyle = FontStyle.Italic)) { append(entry.example) }
                    append("  —  ${entry.exampleTranslation}")
                },
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Formation explanation
            HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
            Text(
                text = "How to form:",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = entry.formationRule,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Base: ${entry.suffixBase}",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            BasicSuffixTable(entry.arSuf, entry.erSuf, entry.irSuf, allSame)

            // Example conjugation table
            HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
            Text(
                text = "Example — falar / comer / partir:",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            BasicConjugationRow("Subject", "-ar (falar)", "-er (comer)", "-ir (partir)", isHeader = true)
            listOf(
                Triple("eu", entry.eu, entry.eu),
                Triple("tu", entry.tu, entry.tu),
                Triple("ele/ela", entry.ele, entry.ele),
                Triple("nós", entry.nos, entry.nos),
                Triple("vós", entry.vos, entry.vos),
                Triple("eles/elas", entry.eles, entry.eles)
            ).forEach { (subject, raw, _) ->
                val parts = raw.split(" / ")
                BasicConjugationRow(
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
private fun BasicSuffixTable(
    arSuf: List<String>,
    erSuf: List<String>,
    irSuf: List<String>,
    allSame: Boolean
) {
    val subjects = listOf("eu", "tu", "ele/ela", "nós", "vós", "eles/elas")
    if (allSame) {
        // 2-column: Subject | Suffix
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text("Subject", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.8f))
            Text("Suffix", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
        }
        subjects.forEachIndexed { i, subj ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(subj, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(0.8f))
                Text(arSuf.getOrElse(i) { "—" }, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1f))
            }
        }
    } else {
        // 4-column: Subject | -ar | -er | -ir
        BasicConjugationRow("Subject", "-ar", "-er", "-ir", isHeader = true)
        subjects.forEachIndexed { i, subj ->
            BasicConjugationRow(
                subject = subj,
                ar = arSuf.getOrElse(i) { "—" },
                er = erSuf.getOrElse(i) { "—" },
                ir = irSuf.getOrElse(i) { "—" },
                isHeader = false
            )
        }
    }
}

@Composable
private fun BasicConjugationRow(subject: String, ar: String, er: String, ir: String, isHeader: Boolean) {
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
