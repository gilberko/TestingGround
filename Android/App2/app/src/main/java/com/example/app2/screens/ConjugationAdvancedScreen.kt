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

            item { AdvPpIntroCard() }
            item { AdvPpAgreementCard() }
            item { AdvPpIrregularCard() }
            item { AdvPpDoubleCard() }
            item { AdvPassiveVerbsCard() }
            item { AdvSePassiveCard() }

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

// ─────────────────────────────────────────────────────────────────────────────
// Past participle + passive voice
// ─────────────────────────────────────────────────────────────────────────────

private data class AdvPpAgreeRow(val verb: String, val ms: String, val fs: String, val mp: String, val fp: String, val example: String, val exampleEn: String)

private val advPpAgreeRows = listOf(
    AdvPpAgreeRow("fechar", "fechado", "fechada", "fechados", "fechadas", "As lojas estão fechadas.", "The shops are closed."),
    AdvPpAgreeRow("vender", "vendido", "vendida", "vendidos", "vendidas", "A casa foi vendida.", "The house was sold."),
    AdvPpAgreeRow("partir", "partido", "partida", "partidos", "partidas", "Os copos ficaram partidos.", "The glasses ended up broken."),
    AdvPpAgreeRow("abrir", "aberto", "aberta", "abertos", "abertas", "A janela está aberta.", "The window is open."),
    AdvPpAgreeRow("escrever", "escrito", "escrita", "escritos", "escritas", "Os livros foram escritos por ela.", "The books were written by her."),
    AdvPpAgreeRow("fazer", "feito", "feita", "feitos", "feitas", "As camas já estão feitas.", "The beds are already made.")
)

private val advPpIrregular = listOf(
    "fazer" to "feito", "dizer" to "dito", "escrever" to "escrito", "ver" to "visto",
    "vir" to "vindo", "pôr" to "posto", "abrir" to "aberto", "cobrir" to "coberto",
    "ganhar" to "ganho", "gastar" to "gasto", "pagar" to "pago", "descobrir" to "descoberto"
)

private data class AdvPpDouble(val verb: String, val regular: String, val short: String, val note: String = "")

private val advPpDoubles = listOf(
    AdvPpDouble("aceitar", "aceitado", "aceite", "EP; BP aceito"),
    AdvPpDouble("entregar", "entregado", "entregue"),
    AdvPpDouble("matar", "matado", "morto"),
    AdvPpDouble("prender", "prendido", "preso"),
    AdvPpDouble("acender", "acendido", "aceso"),
    AdvPpDouble("imprimir", "imprimido", "impresso"),
    AdvPpDouble("eleger", "elegido", "eleito"),
    AdvPpDouble("limpar", "limpado", "limpo"),
    AdvPpDouble("salvar", "salvado", "salvo")
)

private data class AdvPassiveVerb(val verb: String, val role: String, val examples: List<Pair<String, String>>, val works: Boolean)

private val advPassiveVerbs = listOf(
    AdvPassiveVerb(
        "ser + participle",
        "The true passive: describes the ACTION being done. The doer (agent) is introduced with por (pelo / pela / pelos / pelas).",
        listOf(
            "A ponte foi construída em 1966." to "The bridge was built in 1966.",
            "O ladrão foi preso pela polícia." to "The thief was arrested by the police.",
            "As cartas serão entregues amanhã." to "The letters will be delivered tomorrow."
        ),
        true
    ),
    AdvPassiveVerb(
        "estar + participle",
        "The RESULTING STATE after an action. Usually no agent.",
        listOf(
            "A porta está fechada." to "The door is closed. (state now)",
            "O jantar já está feito." to "Dinner is already made.",
            "Compare: A porta foi fechada às dez." to "The door was closed at ten. (the action, so ser)"
        ),
        true
    ),
    AdvPassiveVerb(
        "ficar + participle",
        "A CHANGE of state: became / got / ended up. Very common in speech for feelings and results.",
        listOf(
            "Fiquei surpreendido." to "I was (got) surprised.",
            "O carro ficou estragado." to "The car got damaged / ended up broken.",
            "A reunião ficou marcada para segunda." to "The meeting got scheduled for Monday."
        ),
        true
    ),
    AdvPassiveVerb(
        "andar / continuar / vir / ir + participle",
        "Less common, but possible: a continuing or accompanying state.",
        listOf(
            "Ando preocupado." to "I've been worried (lately).",
            "A loja continua fechada." to "The shop is still closed.",
            "Ele veio acompanhado." to "He came accompanied (with someone).",
            "O pacote vai embrulhado." to "The parcel goes wrapped."
        ),
        true
    ),
    AdvPassiveVerb(
        "tornar-se + participle",
        "NOT a passive auxiliary. tornar-se = to become, and takes adjectives; a participle works only when it acts as an adjective.",
        listOf(
            "Ele tornou-se conhecido." to "He became well known. (conhecido used as an adjective)",
            "Tornou-se médico." to "He became a doctor.",
            "✗ A casa tornou-se vendida." to "(wrong; say: A casa foi vendida.)"
        ),
        false
    ),
    AdvPassiveVerb(
        "mudar / mudar-se + participle",
        "NOT a passive auxiliary. mudar = to change, mudar-se = to move (house). They don't combine with a participle to make a passive.",
        listOf(
            "Mudei-me para o Porto." to "I moved to Porto.",
            "O horário foi mudado." to "The schedule was changed. (the passive of mudar uses ser)"
        ),
        false
    )
)

@Composable
private fun AdvPpCardShell(title: String, subtitle: String = "", content: @Composable () -> Unit) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            if (subtitle.isNotEmpty()) {
                Text(
                    subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            content()
        }
    }
}

@Composable
private fun AdvPpLabel(text: String) {
    Text(
        text,
        style = MaterialTheme.typography.labelSmall,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 6.dp)
    )
}

@Composable
private fun AdvPpNote(text: String) {
    Text(
        text,
        style = MaterialTheme.typography.bodySmall,
        fontStyle = FontStyle.Italic,
        color = MaterialTheme.colorScheme.tertiary,
        modifier = Modifier.padding(top = 4.dp)
    )
}

@Composable
private fun AdvPpExample(pt: String, en: String) {
    Text(
        text = buildAnnotatedString {
            withStyle(SpanStyle(fontStyle = FontStyle.Italic)) { append(pt) }
            append("  —  $en")
        },
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun AdvPpIntroCard() {
    AdvPpCardShell("The Past Participle (Particípio Passado)", "The -ed form: closed, sold, written") {
        Text(
            "• It is used (1) with ter to make compound tenses, (2) with ser / estar / ficar to make the passive, and (3) as an adjective.",
            style = MaterialTheme.typography.bodySmall
        )
        AdvPpLabel("How to form it (regular verbs):")
        AdvConjugationRow("", "-ar → -ado", "-er → -ido", "-ir → -ido", isHeader = true)
        AdvConjugationRow("", "falar → falado", "vender → vendido", "partir → partido", isHeader = false)
        AdvConjugationRow("", "fechar → fechado", "comer → comido", "dormir → dormido", isHeader = false)
        AdvConjugationRow("", "comprar → comprado", "beber → bebido", "sair → saído", isHeader = false)
        AdvPpLabel("With ter: the participle NEVER changes")
        AdvPpExample("Tenho falado muito com ela.", "I've been talking to her a lot.")
        AdvPpExample("Ela tinha fechado as janelas.", "She had closed the windows. (fechado, not fechadas)")
        AdvPpLabel("With ser / estar / ficar, or as an adjective: it AGREES")
        AdvPpExample("As janelas estão fechadas.", "The windows are closed. (f. pl.)")
        AdvPpExample("um bolo feito em casa", "a homemade cake")
        AdvPpNote("See also More Tenses → Voz Passiva.")
    }
}

@Composable
private fun AdvPpAgreementCard() {
    AdvPpCardShell("Participle Agreement", "masculine / feminine × singular / plural: it follows the noun it describes") {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            listOf("verb", "m. sg.", "f. sg.", "m. pl.", "f. pl.").forEachIndexed { i, h ->
                Text(
                    h,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(if (i == 0) 0.9f else 1f)
                )
            }
        }
        advPpAgreeRows.forEach { row ->
            Column(modifier = Modifier.padding(vertical = 2.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(row.verb, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.9f))
                    listOf(row.ms, row.fs, row.mp, row.fp).forEach {
                        Text(it, style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic, modifier = Modifier.weight(1f))
                    }
                }
                AdvPpExample(row.example, row.exampleEn)
            }
        }
        AdvPpLabel("More examples:")
        AdvPpExample("O contrato foi assinado.", "The contract was signed. (m. sg.)")
        AdvPpExample("A carta foi enviada ontem.", "The letter was sent yesterday. (f. sg.)")
        AdvPpExample("Os documentos estão traduzidos.", "The documents are translated. (m. pl.)")
        AdvPpExample("As mesas estão reservadas.", "The tables are reserved. (f. pl.)")
    }
}

@Composable
private fun AdvPpIrregularCard() {
    AdvPpCardShell("Irregular Past Participles", "These don't follow -ado / -ido. They still agree: feito / feita / feitos / feitas") {
        advPpIrregular.chunked(2).forEach { pair ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                pair.forEach { (verb, pp) ->
                    Text(
                        text = buildAnnotatedString {
                            append("$verb → ")
                            withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)) { append(pp) }
                        },
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(1f)
                    )
                }
                if (pair.size == 1) Spacer(modifier = Modifier.weight(1f))
            }
        }
        AdvPpNote("In EP, ganho / gasto / pago are used with ter too: Já tinha pago a conta. (I had already paid the bill.)")
    }
}

@Composable
private fun AdvPpDoubleCard() {
    AdvPpCardShell("Verbs With Two Participles", "Regular form → with ter / haver · short form → with ser / estar / ficar") {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            listOf("verb", "with ter", "with ser / estar").forEach {
                Text(it, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            }
        }
        advPpDoubles.forEach { d ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(d.verb, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Text(d.regular, style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic, modifier = Modifier.weight(1f))
                Text(
                    if (d.note.isEmpty()) d.short else "${d.short} (${d.note})",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        AdvPpLabel("Examples:")
        AdvPpExample("Eles tinham aceitado a proposta.", "They had accepted the proposal. (ter)")
        AdvPpExample("A proposta foi aceite.", "The proposal was accepted. (ser)")
        AdvPpExample("A encomenda já está entregue.", "The order has already been delivered. (estar)")
        AdvPpExample("A luz está acesa.", "The light is on. (estar + aceso / acesa)")
        AdvPpNote("In everyday EP speech the short form is also heard with ter (tinha aceite, tinha entregue); both occur. With ser / estar, use the short form.")
    }
}

@Composable
private fun AdvPassiveVerbsCard() {
    AdvPpCardShell("Which Verbs Make the Passive?", "verb + past participle, the participle agreeing with the subject") {
        advPassiveVerbs.forEachIndexed { index, v ->
            if (index > 0) HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            Text(
                text = (if (v.works) "✓ " else "✗ ") + v.verb,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = if (v.works) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            )
            Text(v.role, style = MaterialTheme.typography.bodySmall)
            v.examples.forEach { (pt, en) -> AdvPpExample(pt, en) }
        }
    }
}

@Composable
private fun AdvSePassiveCard() {
    AdvPpCardShell("The se Passive (Passiva Sintética)", "Very common in signs, ads and general statements; no agent") {
        Text(
            "• verb (3rd person) + se. The verb agrees with the thing: singular → vende-se, plural → vendem-se.",
            style = MaterialTheme.typography.bodySmall
        )
        AdvPpExample("Vende-se casa.", "House for sale.")
        AdvPpExample("Vendem-se casas.", "Houses for sale.")
        AdvPpExample("Aluga-se apartamento.", "Apartment for rent.")
        AdvPpExample("Fala-se inglês.", "English spoken (here).")
        AdvPpExample("Aqui não se aceitam cartões.", "Cards aren't accepted here.")
        AdvPpNote("Equivalent with ser: A casa é vendida. But the se form sounds much more natural for general statements.")
    }
}
