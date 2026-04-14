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

private data class AdvTenseEntry(
    val name: String,
    val usage: String,
    val example: String,
    val exampleTranslation: String,
    val extraNote: String = "",
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

private val advancedTenses = listOf(
    AdvTenseEntry(
        name = "Mais-que-Perfeito",
        usage = "Pluperfect — completed before another past action; formal/literary.",
        example = "Quando cheguei, ela já partira.",
        exampleTranslation = "When I arrived, she had already left.",
        formationRule = "Take the eles form of the Pretérito Perfeito (falaram, comeram, partiram). Remove the final -m to get the base (falara, comera, partira). All verb types then share the same endings. Note: nós and vós carry a written accent.",
        suffixBase = "3rd pl. preterite − m (falara / comera / partira), then add:",
        arSuf = listOf("—", "+s", "—", "+mos*", "+reis*", "+m"),
        erSuf = listOf("—", "+s", "—", "+mos*", "+reis*", "+m"),
        irSuf = listOf("—", "+s", "—", "+mos*", "+reis*", "+m"),
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
        formationRule = "Keep the full infinitive intact (falar, comer, partir) and append the ending directly to it. All three verb types use exactly the same endings.",
        suffixBase = "Full infinitive (falar / comer / partir) +",
        arSuf = listOf("-ia", "-ias", "-ia", "-íamos", "-íeis", "-iam"),
        erSuf = listOf("-ia", "-ias", "-ia", "-íamos", "-íeis", "-iam"),
        irSuf = listOf("-ia", "-ias", "-ia", "-íamos", "-íeis", "-iam"),
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
        formationRule = "Take the present indicative eu form (falo, como, parto) and drop the -o to get the subjunctive stem (fal-, com-, part-). Then add the suffix. Key rule: -ar verbs take -e endings, while -er/-ir verbs take -a endings — the opposite of their infinitive class.",
        suffixBase = "Present indicative eu − o  (fal- / com- / part-)",
        arSuf = listOf("-e", "-es", "-e", "-emos", "-eis", "-em"),
        erSuf = listOf("-a", "-as", "-a", "-amos", "-ais", "-am"),
        irSuf = listOf("-a", "-as", "-a", "-amos", "-ais", "-am"),
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
        formationRule = "Take the eles form of the Pretérito Perfeito (falaram, comeram, partiram). Remove -ram to get the base (fala-, come-, parti-). All verb types share the same endings. Note: nós and vós carry a written accent.",
        suffixBase = "3rd pl. preterite − ram  (fala- / come- / parti-)",
        arSuf = listOf("-sse", "-sses", "-sse", "-ssemos*", "-sseis*", "-ssem"),
        erSuf = listOf("-sse", "-sses", "-sse", "-ssemos*", "-sseis*", "-ssem"),
        irSuf = listOf("-sse", "-sses", "-sse", "-ssemos*", "-sseis*", "-ssem"),
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
        formationRule = "Take the eles form of the Pretérito Perfeito (falaram, comeram, partiram). Remove -ram to get the base (fala-, come-, parti-). All verb types share the same endings. The eu and ele/ela forms are identical to the regular infinitive.",
        suffixBase = "3rd pl. preterite − ram  (fala- / come- / parti-)",
        arSuf = listOf("-r", "-res", "-r", "-rmos", "-rdes", "-rem"),
        erSuf = listOf("-r", "-res", "-r", "-rmos", "-rdes", "-rem"),
        irSuf = listOf("-r", "-res", "-r", "-rmos", "-rdes", "-rem"),
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
        formationRule = "The imperative borrows from other tenses. tu = present indicative ele/ela form. ele/você, nós, eles/vocês = conjuntivo presente forms. vós = stem + special ending (-ai for -ar / -ei for -er / -i for -ir).",
        suffixBase = "Derived from other tenses (see rule above)",
        arSuf = listOf("—", "pres. ind. ele", "conj. pres.", "conj. pres.", "stem + -ai", "conj. pres."),
        erSuf = listOf("—", "pres. ind. ele", "conj. pres.", "conj. pres.", "stem + -ei", "conj. pres."),
        irSuf = listOf("—", "pres. ind. ele", "conj. pres.", "conj. pres.", "stem + -i", "conj. pres."),
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
        formationRule = "There is no eu form. Use não + the conjuntivo presente form for every person. The negative imperative is entirely built on the present subjunctive.",
        suffixBase = "não + Conjuntivo Presente form (all verb types)",
        arSuf = listOf("—", "não + conj.", "não + conj.", "não + conj.", "não + conj.", "não + conj."),
        erSuf = listOf("—", "não + conj.", "não + conj.", "não + conj.", "não + conj.", "não + conj."),
        irSuf = listOf("—", "não + conj.", "não + conj.", "não + conj.", "não + conj.", "não + conj."),
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
        formationRule = "Remove -ar / -er / -ir from the infinitive to get the stem. Add the gerund ending. The gerund has only one form — it does not change by subject. In European Portuguese, use estar + a + infinitive for continuous actions instead.",
        suffixBase = "Stem (infinitive − -ar / -er / -ir) +",
        arSuf = listOf("-ando", "-ando", "-ando", "-ando", "-ando", "-ando"),
        erSuf = listOf("-endo", "-endo", "-endo", "-endo", "-endo", "-endo"),
        irSuf = listOf("-indo", "-indo", "-indo", "-indo", "-indo", "-indo"),
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
        formationRule = "Start with the regular infinitive. The eu and ele/ela forms are the unchanged infinitive. For all other subjects, append the ending to the full infinitive. All verb types share the same endings.",
        suffixBase = "Full infinitive (falar / comer / partir) +",
        arSuf = listOf("(unchanged)", "+es", "(unchanged)", "+mos", "+des", "+em"),
        erSuf = listOf("(unchanged)", "+es", "(unchanged)", "+mos", "+des", "+em"),
        irSuf = listOf("(unchanged)", "+es", "(unchanged)", "+mos", "+des", "+em"),
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
            if (entry.extraNote.isNotEmpty()) {
                Text(
                    text = entry.extraNote,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

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
            AdvSuffixTable(entry.arSuf, entry.erSuf, entry.irSuf, allSame)

            // Example conjugation table
            HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
            Text(
                text = "Example — falar / comer / partir:",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
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
private fun AdvSuffixTable(
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
        AdvConjugationRow("Subject", "-ar", "-er", "-ir", isHeader = true)
        subjects.forEachIndexed { i, subj ->
            AdvConjugationRow(
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
