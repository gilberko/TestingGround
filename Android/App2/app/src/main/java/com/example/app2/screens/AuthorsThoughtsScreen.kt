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

private val indicativeTopics = listOf(
    ThoughtsTopic(
        name = "The Indicative Zone",
        subtitle = "Facts, rules, and personal guidelines",
        description = listOf(
            "When you state a fact, a rule, or a personal habit presented as reality, use the indicative.",
            "There is no doubt, wish, or hypothetical involved — it just is.",
            "Think: generalities, scientific facts, personal routines, observations."
        ),
        examples = listOf(
            "Quando chove, preciso de levar um guarda-chuva." to "When it rains, I need to take an umbrella.",
            "A água ferve a 100 graus." to "Water boils at 100 degrees.",
            "Quando estou cansado, durmo cedo." to "When I'm tired, I go to bed early."
        )
    )
)

private val presentSubjunctiveTopics = listOf(
    ThoughtsTopic(
        name = "Wishes & Doubts — Present Subjunctive",
        subtitle = "Wanting, hoping, doubting → the wished thing uses conjuntivo",
        description = listOf(
            "When one clause expresses wanting, hoping, wishing, fearing, or doubting, the verb of wanting/doubting stays in the indicative — but what is wanted or doubted lives in its own hypothetical realm: the present subjunctive (conjuntivo presente).",
            "Pattern: [indicative: I want/doubt] + que + [present subjunctive: the thing wanted/doubted].",
            "Think of it this way: the wish hasn't happened yet — it exists only in possibility, so it gets the subjunctive."
        ),
        examples = listOf(
            "Quero que venhas comigo." to "I want you to come with me.  (quero = indicative; venhas = present subjunctive)",
            "Espero que ele chegue a tempo." to "I hope he arrives on time.  (espero = indicative; chegue = present subjunctive)",
            "Duvido que ele saiba a resposta." to "I doubt he knows the answer.  (duvido = indicative; saiba = present subjunctive)",
            "Tenho medo que ela se magoe." to "I'm afraid she'll get hurt.  (tenho medo = indicative; magoe = present subjunctive)"
        ),
        note = "Politeness framing: 'Gostaria de uma chávena de café.' (I would like a cup of coffee.) — this condicional can be read as: if you had asked me what I wanted, I would like… It's subjunctive reasoning in a polite wrapper."
    )
)

private val pastSubjunctiveTopics = listOf(
    ThoughtsTopic(
        name = "The Unlikely Condition — Past Subjunctive + Condicional",
        subtitle = "Very improbable future, or something that didn't happen in the past",
        description = listOf(
            "When a condition is highly unlikely to happen (even if it's technically future), or refers to something that did not happen in the past, use the past subjunctive (conjuntivo imperfeito) for the condition.",
            "The result of such an unlikely scenario uses the condicional.",
            "Watch for 'se' (if) as the signal. The past subjunctive almost always acts as an unlikely or counterfactual condition.",
            "Even a tomorrow-scenario can qualify: if a tornado tomorrow feels wildly improbable, past subjunctive applies."
        ),
        examples = listOf(
            "Se amanhã houvesse um tornado, compraria um barco." to "If tomorrow there were a tornado, I would buy a raft.  (houvesse = past subjunctive; compraria = condicional)",
            "Se eu tivesse estudado mais, teria passado no exame." to "If I had studied more, I would have passed the exam.  (tivesse estudado = past subjunctive; teria passado = condicional)",
            "Se ganhasse a lotaria, compraria uma casa." to "If I won the lottery, I would buy a house.  (ganhasse = past subjunctive; compraria = condicional)",
            "Se ele soubesse a verdade, ficaria chocado." to "If he knew the truth, he would be shocked.  (soubesse = past subjunctive; ficaria = condicional)"
        ),
        note = "Past subjunctive ≠ past tense. It signals unreality or low probability, not necessarily past time."
    )
)

private val futureSubjunctiveTopics = listOf(
    ThoughtsTopic(
        name = "The Possible Future Condition — Future Subjunctive + Future Indicative",
        subtitle = "Realistic conditions that could genuinely happen",
        description = listOf(
            "When a condition is realistic — something that could genuinely occur — use the future subjunctive (conjuntivo futuro) for the condition and the future simple indicative for the result.",
            "The key test: can you say 'when it happens' rather than 'if it were to happen'? If yes, this is your form.",
            "This pairing is very common in everyday Portuguese for expressing real future plans or genuine possibilities."
        ),
        examples = listOf(
            "Se chover amanhã, levarei o guarda-chuva." to "If it rains tomorrow, I will bring an umbrella.  (chover = future subjunctive; levarei = future indicative)",
            "Quando chegares, telefona-me." to "When you arrive, call me.  (chegares = future subjunctive; telefona = imperative)",
            "Se tiveres tempo, vem ter comigo." to "If you have time, come meet me.  (tiveres = future subjunctive; vem = imperative)",
            "Quando acabar o trabalho, vamos jantar fora." to "When work is done, we'll go out for dinner.  (acabar = future subjunctive; vamos = future indicative)"
        ),
        note = "Notice: Portuguese uses future subjunctive where English just uses simple present ('if it rains', 'when you arrive'). In English you say present; in Portuguese you say future subjunctive."
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthorsThoughtsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Author's Personal Thoughts") },
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
                    text = "The fundamental split: indicative is the realm of reality; conjuntivo and condicional are the realm of wishes, doubts, hypotheticals, and politeness.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }

            indicativeTopics.forEach { topic ->
                item(key = topic.name) { ThoughtsCard(topic) }
            }

            item {
                Text(
                    text = "Connecting Two Clauses",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                )
            }

            presentSubjunctiveTopics.forEach { topic ->
                item(key = topic.name) { ThoughtsCard(topic) }
            }

            pastSubjunctiveTopics.forEach { topic ->
                item(key = topic.name) { ThoughtsCard(topic) }
            }

            futureSubjunctiveTopics.forEach { topic ->
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
