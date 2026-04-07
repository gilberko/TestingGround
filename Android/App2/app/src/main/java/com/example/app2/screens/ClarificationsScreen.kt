package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// ─── Data models ─────────────────────────────────────────────────────────────

private data class ClarExample(val pt: String, val en: String)

private data class ClarTenseEntry(
    val name: String,
    val englishEquivalent: String,
    val whenToUse: String,
    val signal: String,           // "What a sentence in this tense means"
    val triggers: String = "",    // Optional: trigger words / structures
    val examples: List<ClarExample>,
    val note: String = ""
)

// ─── Content ──────────────────────────────────────────────────────────────────

private val acabarDeExamples = listOf(
    ClarExample("Acabo de chegar.", "I have just arrived."),
    ClarExample("Eu acabei de comer.", "I just ate. / I have just finished eating."),
    ClarExample("Ela acabou de ligar.", "She just called."),
    ClarExample("Eles acabavam de jantar quando chegámos.", "They had just had dinner when we arrived."),
    ClarExample("Acabas de dizer uma coisa muito importante.", "You have just said something very important.")
)

private val tenseEntries = listOf(
    ClarTenseEntry(
        name = "Pretérito Perfeito Simples do Indicativo",
        englishEquivalent = "Simple Past",
        whenToUse = "A single, completed action in the past. Something happened and it's done.",
        signal = "\"This event happened at a specific moment in the past and is finished.\"",
        examples = listOf(
            ClarExample("Eu comi o bolo.", "I ate the cake."),
            ClarExample("Ele foi a Lisboa ontem.", "He went to Lisbon yesterday."),
            ClarExample("Ela aprendeu português.", "She learned Portuguese."),
            ClarExample("Nós trabalhámos muito hoje.", "We worked a lot today.")
        )
    ),
    ClarTenseEntry(
        name = "Pretérito Imperfeito do Indicativo",
        englishEquivalent = "Imperfect / \"used to\" / \"was doing\"",
        whenToUse = "Habitual or repeated actions in the past; ongoing states or background descriptions; actions that were in progress when something else happened.",
        signal = "\"This was the ongoing situation, OR this kept happening back then.\"",
        examples = listOf(
            ClarExample("Eu comia sempre aqui.", "I always used to eat here."),
            ClarExample("Quando era criança, jogava futebol.", "When I was a child, I used to play football."),
            ClarExample("Ela lia enquanto ele dormia.", "She was reading while he was sleeping."),
            ClarExample("A casa era pequena mas acolhedora.", "The house was small but cosy.")
        ),
        note = "Often sets the scene or background in a story, while the Perfeito Simples narrates the foreground events."
    ),
    ClarTenseEntry(
        name = "Conjuntivo Presente",
        englishEquivalent = "Present Subjunctive",
        whenToUse = "Subordinate clauses after verbs/expressions of desire, hope, doubt, emotion, necessity, or after conjunctions like embora, para que, antes que, mesmo que, talvez.",
        signal = "\"I'm expressing something uncertain, desired, or emotionally coloured about the present or near future.\"",
        triggers = "querer que, esperar que, é importante que, duvidar que, embora, para que, antes que, talvez, oxalá, mesmo que",
        examples = listOf(
            ClarExample("Espero que ele venha.", "I hope he comes."),
            ClarExample("É necessário que estudes.", "It's necessary that you study."),
            ClarExample("Embora esteja cansado, vou trabalhar.", "Although I'm tired, I'll work."),
            ClarExample("Talvez ela saiba a resposta.", "Maybe she knows the answer."),
            ClarExample("Para que entendas, vou explicar devagar.", "So that you understand, I'll explain slowly.")
        )
    ),
    ClarTenseEntry(
        name = "Conjuntivo Futuro",
        englishEquivalent = "Future Subjunctive (no direct English equivalent)",
        whenToUse = "Temporal or conditional clauses about future or hypothetical events. Used after quando, se, assim que, logo que, enquanto, onde quer que, and similar conjunctions when referring to the future.",
        signal = "\"When/if this future event occurs...\" — the speaker treats the event as yet-to-happen and not guaranteed.",
        triggers = "quando, se, assim que, logo que, enquanto, como (=however), onde quer que, quem quer que, o que quer que",
        examples = listOf(
            ClarExample("Quando chegares, liga-me.", "When you arrive, call me."),
            ClarExample("Se tiveres tempo, vem cá.", "If you have time, come here."),
            ClarExample("Assim que ela sair, avisa-me.", "As soon as she leaves, let me know."),
            ClarExample("Enquanto viveres aqui, tens de cumprir as regras.", "While you live here, you have to follow the rules."),
            ClarExample("Faças o que fizeres, serei honesto.", "Whatever you do, I'll be honest.")
        ),
        note = "This tense is unique to Portuguese (and Spanish). In English, the present simple is used after 'when/if' for future events — in Portuguese you must use the Conjuntivo Futuro."
    ),
    ClarTenseEntry(
        name = "Conjuntivo Imperfeito (Passado)",
        englishEquivalent = "Past Subjunctive",
        whenToUse = "Same triggers as the Conjuntivo Presente, but used when the main verb is in a past tense. Also used in 'se' hypothetical conditions (se + Imperfeito Conjuntivo → Condicional).",
        signal = "\"I was expressing something uncertain or desired about the past, OR I'm imagining a hypothetical scenario.\"",
        triggers = "queria que, era necessário que, embora (+ past), se (hypothetical)",
        examples = listOf(
            ClarExample("Queria que ele viesse.", "I wanted him to come."),
            ClarExample("Era necessário que estudasses.", "It was necessary that you studied."),
            ClarExample("Embora estivesse cansado, trabalhou.", "Although he was tired, he worked."),
            ClarExample("Se tivesse dinheiro, compraria um carro.", "If I had money, I would buy a car."),
            ClarExample("Oxalá soubesse a resposta.", "I wish I knew the answer.")
        )
    ),
    ClarTenseEntry(
        name = "Condicional (Condicional Presente)",
        englishEquivalent = "Conditional — \"would\"",
        whenToUse = "Hypothetical or imagined situations; polite requests and softened statements; the result clause in 'se' hypothetical sentences (se + Imperfeito Conjuntivo → Condicional).",
        signal = "\"This would happen under some condition, or in theory. / I'm being polite.\"",
        examples = listOf(
            ClarExample("Eu comeria mais, mas estou cheio.", "I would eat more, but I'm full."),
            ClarExample("Gostaria de um café, por favor.", "I would like a coffee, please."),
            ClarExample("Se tivesse tempo, viajaria mais.", "If I had time, I would travel more."),
            ClarExample("Poderia ajudar-me?", "Could you help me?"),
            ClarExample("Disseram que viriam mais tarde.", "They said they would come later.")
        ),
        note = "In spoken European Portuguese, the Condicional is often replaced by the Imperfeito do Indicativo (e.g., 'Queria um café' instead of 'Quereria um café')."
    ),
    ClarTenseEntry(
        name = "Mais que Perfeito (Pretérito Mais que Perfeito)",
        englishEquivalent = "Pluperfect — \"had done\"",
        whenToUse = "An action that was completed BEFORE another past action or point in time.",
        signal = "\"This had already happened before something else occurred in the past.\"",
        examples = listOf(
            ClarExample("Quando ele chegou, eu já tinha saído.", "When he arrived, I had already left."),
            ClarExample("Ela tinha estudado toda a noite antes do exame.", "She had studied all night before the exam."),
            ClarExample("Ele disse que nunca tinha comido sushi.", "He said he had never eaten sushi."),
            ClarExample("Já tínhamos jantado quando eles ligaram.", "We had already had dinner when they called.")
        ),
        note = "The simple literary form (e.g., 'eu comera') exists but is rare in speech. The compound form with ter + participle ('tinha comido') is used in everyday European Portuguese."
    )
)

// ─── Composables ─────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClarificationsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Clarifications & Misc") },
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
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item { SectionLabel("A. acabar de + Infinitive") }
            item { AcabarDeCard() }

            item { SectionLabel("B. Tense Guide — When to Use Each Tense") }
            items(tenseEntries) { entry -> ClarTenseCard(entry) }

            item { Spacer(modifier = Modifier.height(8.dp)) }
        }
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 4.dp, bottom = 2.dp)
    )
}

@Composable
private fun AcabarDeCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(
                text = "acabar de + infinitive",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Meaning: \"to have just done something\" — a very recent completed action.",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Used in the present tense to mean \"just now\"; in the imperfect to mean \"had just\" (before another past event).",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            acabarDeExamples.forEach { ex -> ExampleRow(ex) }
        }
    }
}

@Composable
private fun ClarTenseCard(entry: ClarTenseEntry) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(
                text = entry.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "English: ${entry.englishEquivalent}",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = entry.whenToUse,
                style = MaterialTheme.typography.bodyMedium
            )
            OutlinedCard(
                colors = CardDefaults.outlinedCardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = entry.signal,
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(8.dp)
                )
            }
            if (entry.triggers.isNotEmpty()) {
                Text(
                    text = "Common triggers: ${entry.triggers}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontStyle = FontStyle.Italic
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            entry.examples.forEach { ex -> ExampleRow(ex) }
            if (entry.note.isNotEmpty()) {
                Text(
                    text = "Note: ${entry.note}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun ExampleRow(example: ClarExample) {
    Column(modifier = Modifier.padding(bottom = 2.dp)) {
        Text(
            text = example.pt,
            style = MaterialTheme.typography.bodyMedium,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = example.en,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
