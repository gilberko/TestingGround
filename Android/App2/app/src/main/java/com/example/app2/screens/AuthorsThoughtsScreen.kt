package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class ThoughtsTopic(
    val name: String,
    val subtitle: String,
    val description: List<String>,
    val examples: List<Pair<String, String>>,
    val note: String? = null
)

private val realityTopics = listOf(
    ThoughtsTopic(
        name = "The Indicative Zone",
        subtitle = "Facts, rules, habits — and even the planned future",
        description = listOf(
            "Indicativo is Portuguese's \"reality\" mood — used whenever you're stating something as fact, whether that fact is about the present, the past, or something already scheduled for later.",
            "This includes future plans and schedules: even though the future hasn't happened yet, if you're presenting it as a plan rather than a wish or a doubt, it stays in the indicativo.",
            "Contrast this with conjuntivo, which is reserved for a different kind of \"unknown\": wishes, doubts, hypotheticals, and conditions whose outcome genuinely isn't guaranteed."
        ),
        examples = listOf(
            "Amanhã de manhã vou à loja comprar tinta." to "Tomorrow morning I'm going to the store to buy paint. (a plan — indicativo, even though it's about tomorrow)",
            "Quando chove, preciso de levar um guarda-chuva." to "When it rains, I need to take an umbrella. (general truth/habit)",
            "A água ferve a 100 graus." to "Water boils at 100 degrees. (fact)",
            "O comboio parte às 9 horas." to "The train leaves at 9 o'clock. (schedule)"
        )
    ),
    ThoughtsTopic(
        name = "Future Indicative — Still Unknown, Still Indicativo",
        subtitle = "Plans and schedules aren't wishes, so they don't trigger conjuntivo",
        description = listOf(
            "It's tempting to think \"the future is unknown, so it must need conjuntivo\" — but that isn't the test European Portuguese grammar actually uses.",
            "The real test: are you stating this as your plan or expectation (indicativo), or are you wishing for it, doubting it, or treating it as a genuinely open condition (conjuntivo)?",
            "A simple statement of intent — even one that could technically fail to happen — stays in the future/present indicativo as long as you aren't framing it as a wish or an if/when-uncertain condition."
        ),
        examples = listOf(
            "Vou visitar os meus pais no fim de semana." to "I'm going to visit my parents this weekend. (plan — indicativo)",
            "Vamos jantar fora sexta-feira." to "We're going out for dinner on Friday. (plan — indicativo)",
            "Ela vai começar um novo emprego em outubro." to "She's going to start a new job in October. (plan — indicativo)"
        ),
        note = "Compare with \"Espero que ela comece um novo emprego em outubro\" (I hope she starts a new job in October) — the moment you add a wish (espero que), that same event switches to conjuntivo presente."
    )
)

private val presentSubjunctiveTopics = listOf(
    ThoughtsTopic(
        name = "Wishes, Hopes & Doubts — Now or in the Near Future",
        subtitle = "querer / esperar / duvidar / ser possível que + conjuntivo presente",
        description = listOf(
            "Whenever the main clause expresses wanting, hoping, doubting, fearing, or possibility, the thing being wanted or doubted goes into the conjuntivo presente — as long as it's happening now or in the near future.",
            "Pattern: [indicativo: I want/hope/doubt] + que + [conjuntivo presente: the wished-for or doubted thing].",
            "This isn't about \"now\" in an absolute sense — it's about the timeline relative to the main verb: as long as the main verb is present or future, the dependent wish stays in the present subjunctive."
        ),
        examples = listOf(
            "Quero que venhas à festa." to "I want you to come to the party. (venhas = conjuntivo presente; the coming can happen now or soon)",
            "Espero que ele chegue a tempo." to "I hope he arrives on time.",
            "Duvido que ele saiba a resposta." to "I doubt he knows the answer.",
            "É possível que chova amanhã." to "It's possible that it'll rain tomorrow.",
            "Tenho medo que ela se magoe." to "I'm afraid she'll get hurt."
        ),
        note = "Watch what happens if the main verb shifts to the past: \"Queria que viesses à festa\" (I wanted you to come to the party) — the whole sentence moves into the past, so \"venhas\" becomes \"viesses\", the imperfeito do conjuntivo. That's the sequence-of-tenses rule covered next."
    )
)

private val pastSubjunctiveTopics = listOf(
    ThoughtsTopic(
        name = "Wishes & Doubts About the Past",
        subtitle = "Sequence of tenses: a past main verb pulls the wish into the past subjunctive too",
        description = listOf(
            "When the \"wanting/doubting\" verb itself is in a past tense (queria, duvidava, era possível, esperava), the thing wanted or doubted shifts from conjuntivo presente to conjuntivo imperfeito to match.",
            "This is grammatical agreement between clauses — it doesn't necessarily mean the wished-for event failed to happen, just that the wanting happened in the past."
        ),
        examples = listOf(
            "Queria que viesses à festa." to "I wanted you to come to the party. (queria = past; viesses = conjuntivo imperfeito)",
            "Esperava que ele chegasse a tempo." to "I was hoping he'd arrive on time.",
            "Duvidava que ele soubesse a resposta." to "I doubted he knew the answer.",
            "Era possível que chovesse nesse dia." to "It was possible that it would rain that day."
        ),
        note = "Compare to the present-tense versions in the previous card — same verbs, same logic, just shifted one step into the past because the main verb moved there first."
    ),
    ThoughtsTopic(
        name = "The Unreal \"What If\"",
        subtitle = "se + imperfeito do conjuntivo → condicional — a make-believe world",
        description = listOf(
            "This is the classic hypothetical/counterfactual conditional: describing a world that isn't real (or isn't currently true), and what would follow if it were.",
            "Pattern: Se + [imperfeito do conjuntivo: the unreal condition], + [condicional: the imagined result].",
            "It's the Portuguese equivalent of English \"If I were...\" constructions — you're not talking about something likely, you're imagining."
        ),
        examples = listOf(
            "Se eu fosse rico, não trabalharia." to "If I were rich, I wouldn't work. (fosse = imperfeito do conjuntivo; trabalharia = condicional)",
            "Se eu tivesse mais tempo, viajaria mais." to "If I had more time, I'd travel more.",
            "Se ganhasse a lotaria, compraria uma casa." to "If I won the lottery, I'd buy a house.",
            "Se ele soubesse a verdade, ficaria chocado." to "If he knew the truth, he'd be shocked."
        ),
        note = "This pairing (imperfeito do conjuntivo + condicional) is the single most common if/then pattern for hypotheticals in Portuguese."
    ),
    ThoughtsTopic(
        name = "Emphasizing an Unlikely Future",
        subtitle = "Same imperfeito do conjuntivo — but the \"unreality\" is about probability, not time",
        description = listOf(
            "Imperfeito do conjuntivo isn't restricted to the past — it also covers future events you're framing as improbable or purely speculative, even if that future is as close as tomorrow.",
            "The signal is your own framing: if you'd say \"that's very unlikely\" rather than \"that could genuinely happen,\" reach for imperfeito do conjuntivo + condicional instead of futuro do conjuntivo."
        ),
        examples = listOf(
            "Se amanhã houvesse um tornado, ficaríamos em casa." to "If there were a tornado tomorrow, we'd stay home. (technically about tomorrow, but framed as very improbable)",
            "Se ele fosse embora agora, perderia o comboio." to "If he left right now, he'd miss the train.",
            "Se eu fosse passear, ias comigo?" to "If I went for a walk, would you come with me? (a speculative invitation, not a real plan)"
        ),
        note = "Conjuntivo imperfeito ≠ \"past tense.\" Its real job is marking unreality or low probability — that's why the same form covers a past wish (\"queria que viesses\") and an improbable future (\"se houvesse um tornado\")."
    )
)

private val futureSubjunctiveTopics = listOf(
    ThoughtsTopic(
        name = "Real, Open Conditions — quando / se / assim que",
        subtitle = "Conditions that could genuinely happen, even if the timing isn't certain",
        description = listOf(
            "Futuro do conjuntivo appears in clauses introduced by se (if), quando (when), assim que (as soon as), and enquanto (as long as/while) — whenever the condition is realistic, even if exactly when it happens isn't certain.",
            "The key test: could you rephrase it with \"once/when this happens\" rather than \"if this were to happen\"? If yes, this is your form.",
            "The result clause is usually present or future indicativo (or the imperative) — you're treating the outcome as a real consequence, not an imagined one."
        ),
        examples = listOf(
            "Se levares o carro, tens de pôr gasolina." to "If you take the car, you have to put gas in it.",
            "Quando tiver fome, faço o almoço." to "Once I'm hungry, I'll make lunch.",
            "Enquanto for estudante, tenho desconto." to "As long as I'm a student, I get a discount.",
            "Quando chegares, telefona-me." to "When you arrive, call me.",
            "Assim que acabar o trabalho, vamos jantar fora." to "As soon as work is done, we'll go out for dinner."
        ),
        note = "Contrast directly with the previous group: futuro do conjuntivo says \"this will genuinely happen, I just don't control exactly when\"; imperfeito do conjuntivo says \"this probably won't happen at all.\" Same se/quando trigger word, different verb form, very different meaning."
    )
)

private val conditionalTopics = listOf(
    ThoughtsTopic(
        name = "Politeness",
        subtitle = "Softening requests and statements",
        description = listOf(
            "Condicional softens a request or statement, making it feel less direct — the same logic as English \"could/would\" instead of \"can/will\".",
            "In everyday spoken European Portuguese, the imperfeito do indicativo often substitutes for the more formal condicional in polite requests without losing the polite tone."
        ),
        examples = listOf(
            "Gostaria de uma chávena de café." to "I would like a cup of coffee.",
            "Poderia ajudar-me, por favor?" to "Could you help me, please? (formal condicional)",
            "Podia ajudar-me, por favor?" to "Could you help me, please? (imperfeito do indicativo — very common in speech, equally polite)",
            "Quereria saber mais sobre isso." to "I would like to know more about that. (more formal/literary than gostaria)"
        )
    ),
    ThoughtsTopic(
        name = "Hypothetical Outcomes in If/Then Sentences",
        subtitle = "When a stated option becomes a hypothetical choice",
        description = listOf(
            "A plainly stated fact or an available option stays in the indicativo, even inside an if/then sentence — the \"if\" here works like \"whenever,\" a real/open condition, not a true hypothetical.",
            "Condicional (poderia) enters when you want to frame something as one hypothetical choice among others — an option you're imagining rather than one you're straightforwardly stating.",
            "This is more a matter of framing and register than a rigid either-or rule: speakers can and do keep the plain indicativo (posso) even when a choice is implied. Reach for condicional specifically when you want to underline \"this is just one possibility, not necessarily what I'd do.\""
        ),
        examples = listOf(
            "Se o carro tiver um problema, tenho seguro." to "If the car has a problem, I have insurance. (a stated fact — indicativo throughout, real/open condition)",
            "Se o carro tiver um problema, posso ligar ao seguro." to "If the car has a problem, I can call the insurance. (a plainly available option — indicativo)",
            "Se o carro tiver um problema, poderia ligar ao seguro." to "If the car has a problem, I could call the insurance. (same real condition; poderia softens the result into one option among others)",
            "Se o carro tivesse um problema, poderia tentar consertá-lo eu mesmo." to "If the car had a problem, I could try to fix it myself. (the condition itself now shifts to imperfeito do conjuntivo — a fully hypothetical framing, not just a hedge)"
        ),
        note = "The last two examples look similar but differ in how hypothetical they are: the third keeps a real, open condition (tiver) and only softens the result; the fourth commits to the fully make-believe framing (tivesse) from \"The Unreal What If\" card above."
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthorsThoughtsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Author's Understanding Of Conjunctive/Conditional") },
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
            item { Spacer(modifier = Modifier.height(4.dp)) }

            item {
                Text(
                    text = "Conjuntivo & Condicional — When to Use What",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )
            }

            item {
                Text(
                    text = "The fundamental split: indicativo is the realm of reality — including plans and schedules; conjuntivo and condicional are the realm of wishes, doubts, hypotheticals, and politeness. The trick is telling apart \"future but planned\" (indicativo) from \"future but genuinely uncertain\" (conjuntivo).",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }

            item {
                Text(
                    text = "Reality vs. Possibility",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                )
            }

            realityTopics.forEach { topic ->
                item(key = topic.name) { ThoughtsCard(topic) }
            }

            item {
                Text(
                    text = "Conjuntivo Presente",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                )
            }

            presentSubjunctiveTopics.forEach { topic ->
                item(key = topic.name) { ThoughtsCard(topic) }
            }

            item {
                Text(
                    text = "Conjuntivo Imperfeito — Past Conjuntivo",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                )
            }

            pastSubjunctiveTopics.forEach { topic ->
                item(key = topic.name) { ThoughtsCard(topic) }
            }

            item {
                Text(
                    text = "Conjuntivo Futuro",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                )
            }

            futureSubjunctiveTopics.forEach { topic ->
                item(key = topic.name) { ThoughtsCard(topic) }
            }

            item {
                Text(
                    text = "Condicional",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                )
            }

            conditionalTopics.forEach { topic ->
                item(key = topic.name) { ThoughtsCard(topic) }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun ThoughtsCard(topic: ThoughtsTopic) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = topic.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Text(
                text = topic.subtitle,
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            topic.description.forEach { line ->
                Text(
                    text = "• $line",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 4.dp, bottom = 3.dp)
                )
            }
            if (topic.note != null) {
                Text(
                    text = "Note: ${topic.note}",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 2.dp, bottom = 4.dp)
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
            Text(
                text = "Examples:",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            topic.examples.forEach { example ->
                Column {
                    Text(
                        text = example.first,
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic
                    )
                    Text(
                        text = example.second,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(start = 12.dp, bottom = 3.dp)
                    )
                }
            }
        }
    }
}
