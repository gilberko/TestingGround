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

private data class TenseTopic(
    val name: String,
    val subtitle: String,
    val whenToUse: List<String>,
    val examples: List<Pair<String, String>>,
    val note: String? = null
)

private val indicativeTenses = listOf(
    TenseTopic(
        name = "Presente do Indicativo",
        subtitle = "Present",
        whenToUse = listOf(
            "Actions happening now",
            "Habits and routines",
            "Universal truths",
            "Near future (informal)"
        ),
        examples = listOf(
            "Eu falo português." to "I speak Portuguese.",
            "O sol nasce a leste." to "The sun rises in the east.",
            "Amanhã vou ao mercado." to "Tomorrow I'm going to the market."
        )
    ),
    TenseTopic(
        name = "Presente Contínuo",
        subtitle = "Present Continuous / Progressive — estar + a + infinitive",
        whenToUse = listOf(
            "Action actively happening right now, at this moment",
            "Use estar + a + infinitive (European Portuguese pattern)",
            "Not used for habits, routines, or general truths — use Presente for those"
        ),
        examples = listOf(
            "Estou a comer." to "I am eating.",
            "Ela está a ouvir música." to "She is listening to music.",
            "Estamos a aprender português." to "We are learning Portuguese."
        ),
        note = "EP uses estar + a + infinitive. Brazilian Portuguese uses the gerund: \"Estou comendo.\" This gerund form is not used in European Portuguese."
    ),
    TenseTopic(
        name = "Pretérito Perfeito",
        subtitle = "Simple Past",
        whenToUse = listOf(
            "Completed past actions (specific time)",
            "Sequence of past events",
            "Actions with defined start/end"
        ),
        examples = listOf(
            "Ontem fui ao cinema." to "Yesterday I went to the cinema.",
            "Ela chegou, sentou e começou a ler." to "She arrived, sat down and started reading.",
            "Já comi." to "I have already eaten."
        )
    ),
    TenseTopic(
        name = "Pretérito Imperfeito",
        subtitle = "Imperfect",
        whenToUse = listOf(
            "Ongoing or habitual past actions",
            "Background descriptions in a narrative",
            "Polite requests (softened tone)"
        ),
        examples = listOf(
            "Quando era criança, jogava futebol." to "When I was a child, I used to play football.",
            "O céu estava azul e os pássaros cantavam." to "The sky was blue and the birds were singing.",
            "Queria um café, por favor." to "I would like a coffee, please."
        )
    ),
    TenseTopic(
        name = "Pretérito Imperfeito Contínuo",
        subtitle = "Past Progressive / Continuous — estava + a + infinitive",
        whenToUse = listOf(
            "Action actively in progress at a specific past moment — caught in the act",
            "More vivid and moment-specific than the plain Imperfeito",
            "Use estava + a + infinitive (European Portuguese pattern)"
        ),
        examples = listOf(
            "Ele estava a falar ao telefone." to "He was (right in the middle of) talking on the phone.",
            "Quando entrei, ela estava a dormir." to "When I came in, she was sleeping.",
            "Estávamos a jantar quando tocou o telefone." to "We were having dinner when the phone rang."
        ),
        note = "Why not just use the Imperfeito? \"Ele falava ao telefone\" also means \"he was talking on the phone\" — but it feels like a background detail or habit. \"Estava a falar\" is more vivid: it emphasises the action was actively in progress at that exact moment. Think of it as the difference between scene-setting and catching someone in the act."
    ),
    TenseTopic(
        name = "Pretérito Mais-que-Perfeito",
        subtitle = "Pluperfect — 'had done'",
        whenToUse = listOf(
            "Action completed before another past action",
            "Compound form (tinha + past participle) is standard in speech",
            "Simple literary form (fizera, dissera) exists but is rare in speech"
        ),
        examples = listOf(
            "Quando cheguei, ela já tinha saído." to "When I arrived, she had already left.",
            "Ele tinha comido antes de eu chegar." to "He had eaten before I arrived.",
            "Já tínhamos visto esse filme." to "We had already seen that film."
        )
    ),
    TenseTopic(
        name = "Futuro Simples",
        subtitle = "Simple Future",
        whenToUse = listOf(
            "Future events (formal/written)",
            "Probability or conjecture about the present",
            "In speech, usually replaced by ir + infinitive"
        ),
        examples = listOf(
            "Amanhã farei o trabalho." to "Tomorrow I will do the work.",
            "Ela virá às três." to "She will come at three.",
            "Vou fazer o trabalho amanhã." to "(Informal alternative)"
        ),
        note = "In spoken EP, 'vou fazer' is far more common than 'farei'."
    ),
    TenseTopic(
        name = "Condicional",
        subtitle = "Conditional — 'would'",
        whenToUse = listOf(
            "Hypothetical situations ('would do')",
            "Polite requests and suggestions",
            "Reported speech ('she said she would')"
        ),
        examples = listOf(
            "Eu gostaria de ajudar." to "I would like to help.",
            "Se tivesse dinheiro, compraria uma casa." to "If I had money, I would buy a house.",
            "Ele disse que viria mais tarde." to "He said he would come later."
        )
    )
)

private val conjuntivoTenses = listOf(
    TenseTopic(
        name = "Conjuntivo Presente",
        subtitle = "Present Subjunctive",
        whenToUse = listOf(
            "After querer que / esperar que / pedir que + verb",
            "After expressions of doubt, emotion, or necessity",
            "After embora (although), para que (so that), desde que"
        ),
        examples = listOf(
            "Espero que venhas." to "I hope you will come.",
            "Quero que ele estude mais." to "I want him to study more.",
            "Embora esteja cansado, vou continuar." to "Although I'm tired, I'll continue.",
            "Vou pedir que ele traga o recibo." to "I will ask that he bring the receipt."
        ),
        note = "Sequence of tenses: the main (trigger) verb's tense decides which subjunctive tense follows in the que-clause. Present or future main verb → Conjuntivo Presente (traga). Past main verb → Conjuntivo Pretérito Imperfeito (trouxesse) — see the card below. So \"I will ask that he bring the receipt\" = \"Vou pedir que ele traga o recibo,\" but \"I asked that he bring the receipt\" = \"Pedi que ele trouxesse o recibo.\" The Conjuntivo Futuro is never used after pedir que — it's reserved for adverbial clauses (quando, se, quem), not for reported requests."
    ),
    TenseTopic(
        name = "Conjuntivo Pretérito Imperfeito",
        subtitle = "Imperfect Subjunctive",
        whenToUse = listOf(
            "Hypothetical/contrary-to-fact conditions with se (if)",
            "After expressions of wish or request in the past",
            "Polite softening ('I wish you would…')"
        ),
        examples = listOf(
            "Se eu tivesse dinheiro, compraria uma casa." to "If I had money, I would buy a house.",
            "Gostaria que viesses connosco." to "I would like you to come with us.",
            "Era necessário que todos soubessem." to "It was necessary that everyone know.",
            "Pedi que ele trouxesse o recibo." to "I asked that he bring the receipt."
        ),
        note = "Used whenever the main (trigger) verb is in a past tense — pretérito perfeito, imperfeito, and condicional all count as \"past\" for this rule. Compare with the Conjuntivo Presente card above: it's the trigger verb's tense, not the meaning, that decides the subjunctive tense."
    ),
    TenseTopic(
        name = "Conjuntivo Futuro",
        subtitle = "Future Subjunctive — unique to Portuguese!",
        whenToUse = listOf(
            "Conditions about the future with quando (when), se (if)",
            "Relative clauses with future meaning",
            "Where English uses the present tense, Portuguese requires this form"
        ),
        examples = listOf(
            "Quando chegares, liga-me." to "When you arrive, call me. (NOT 'quando chegas')",
            "Se fizeres o trabalho, podes sair." to "If you do the work, you may leave.",
            "Quem vier primeiro, receberá o prémio." to "Whoever arrives first will receive the prize.",
            "Quando chegas a casa, ligas-me sempre." to "When you get home, you always call me. (habitual — Indicativo, not Conjuntivo Futuro)"
        ),
        note = "English 'when you arrive' = 'quando chegares' (Conjuntivo Futuro). Using the present indicative here is a common learner mistake. But for habitual, repeated, or general-rule statements (anywhere you could add 'always'/'every time'), Portuguese switches back to the Indicativo — the event is an established pattern, not a single unrealized future occurrence. The same logic applies to generic statements with 'quem': the proverb \"Quem não arrisca, não petisca\" (nothing ventured, nothing gained) uses the Indicativo because it states a general truth about a real class of people. Contrast that with \"Preciso de alguém que saiba conduzir\" (I need someone who can drive), which uses the Conjuntivo Presente because 'alguém' is an unknown, indefinite person — not a general truth."
    )
)

private val imperativoEntries = listOf(
    TenseTopic(
        name = "Imperativo Afirmativo",
        subtitle = "Affirmative Imperative",
        whenToUse = listOf(
            "Direct commands and instructions",
            "tu-form: drop -s from 3rd person present (fala, come, parte)",
            "você/ele/ela use the Conjuntivo Presente form"
        ),
        examples = listOf(
            "Fala mais devagar!" to "Speak more slowly! (tu)",
            "Venha cá, por favor." to "Come here, please. (você)",
            "Comam a sopa!" to "Eat the soup! (eles/vocês)"
        )
    ),
    TenseTopic(
        name = "Imperativo Negativo",
        subtitle = "Negative Imperative",
        whenToUse = listOf(
            "Prohibitions and negative commands",
            "Formed using the Conjuntivo Presente for all persons"
        ),
        examples = listOf(
            "Não fales tão alto!" to "Don't speak so loudly! (tu)",
            "Não venha tarde." to "Don't come late. (você)",
            "Não se preocupem." to "Don't worry. (vocês)"
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TensesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tenses") },
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

            item { SectionHeader("Modo Indicativo — Indicative") }
            indicativeTenses.forEach { entry ->
                item(key = entry.name) { TenseCard(entry) }
            }

            item { SectionHeader("Modo Conjuntivo — Subjunctive") }
            conjuntivoTenses.forEach { entry ->
                item(key = entry.name) { TenseCard(entry) }
            }

            item { SectionHeader("Modo Imperativo — Imperative") }
            imperativoEntries.forEach { entry ->
                item(key = entry.name) { TenseCard(entry) }
            }

            item { SectionHeader("Infinitivo Pessoal — Personal Infinitive") }
            item {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Infinitivo Pessoal",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 2.dp)
                        )
                        Text(
                            text = "Unique to Portuguese — the infinitive inflects for person",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = "When to use:",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        listOf(
                            "After prepositions when subjects of clauses differ",
                            "After para, sem, antes de, depois de, ao",
                            "When the infinitive has its own explicit subject"
                        ).forEach { bullet ->
                            Text(
                                text = "• $bullet",
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(start = 4.dp, bottom = 2.dp)
                            )
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
                        Text(
                            text = "Forms of falar:",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        listOf(
                            "eu" to "falar",
                            "tu" to "falares",
                            "ele/ela" to "falar",
                            "nós" to "falarmos",
                            "vós" to "falardes",
                            "eles/elas" to "falarem"
                        ).forEach { form ->
                            Text(
                                text = "${form.first} — ${form.second}",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                modifier = Modifier.padding(start = 4.dp, bottom = 1.dp)
                            )
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
                        Text(
                            text = "Examples:",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        listOf(
                            "Para falares bem, precisas de praticar." to "To speak well, you need to practise.",
                            "É importante saberes a resposta." to "It is important for you to know the answer.",
                            "Saímos sem eles saberem." to "We left without them knowing.",
                            "Depois de comermos, fomos passear." to "After eating, we went for a walk."
                        ).forEach { ex ->
                            Column {
                                Text(
                                    text = ex.first,
                                    style = MaterialTheme.typography.bodySmall,
                                    fontStyle = FontStyle.Italic
                                )
                                Text(
                                    text = ex.second,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(start = 12.dp, bottom = 3.dp)
                                )
                            }
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
    )
}

@Composable
private fun TenseCard(entry: TenseTopic) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = entry.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Text(
                text = entry.subtitle,
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "When to use:",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            entry.whenToUse.forEach { bullet ->
                Text(
                    text = "• $bullet",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 4.dp, bottom = 2.dp)
                )
            }
            if (entry.note != null) {
                Text(
                    text = "Note: ${entry.note}",
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
            entry.examples.forEach { example ->
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
