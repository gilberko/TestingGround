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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectorsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Já, Ainda & Connectors") },
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
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { JaAindaCard() }
            item { ConjunctionsCard() }
            item { AddingContrastingCard() }
            item { TimeConditionCard() }
            item { MannerEmphasisCard() }
            item { TalCard() }
        }
    }
}

// ── Section 1: Já & Ainda ─────────────────────────────────────────────────────

@Composable
private fun JaAindaCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Já & Ainda — Already, Still, Yet", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            ConnectorEntry(
                term = "já",
                meaning = "already / now / yet (in questions)",
                description = "Já shifts meaning based on sentence type. In questions it means \"yet\" or \"already\". In affirmatives it means \"already\". Followed by an exclamation it means \"right now / coming!\".",
                examples = listOf(
                    "Já comeste?" to "Have you eaten yet?",
                    "Já comi." to "I already ate.",
                    "Já vou!" to "I'm coming now!"
                ),
                note = "With não: \"já não\" = no longer / not anymore. E.g. \"Já não fumo.\" = I no longer smoke."
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            ConnectorEntry(
                term = "ainda",
                meaning = "still / yet / even",
                description = "Ainda on its own means \"still\" (ongoing). Combined with não it means \"not yet\". It can also intensify meaning (\"even more\").",
                examples = listOf(
                    "Ainda estou aqui." to "I'm still here.",
                    "Ainda não acabei." to "I haven't finished yet.",
                    "Ainda melhor!" to "Even better!"
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            ConnectorEntry(
                term = "ainda não",
                meaning = "not yet",
                description = "The standard way to say \"not yet\". Ainda não always comes before the verb.",
                examples = listOf(
                    "Ainda não chegou." to "He hasn't arrived yet.",
                    "Ainda não sei." to "I don't know yet."
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            ConnectorEntry(
                term = "já não",
                meaning = "no longer / not anymore",
                description = "Já não expresses that something which was true in the past is no longer true now.",
                examples = listOf(
                    "Já não fumo." to "I no longer smoke.",
                    "Ela já não trabalha aqui." to "She no longer works here."
                )
            )
        }
    }
}

// ── Section 2: Conjunctions ───────────────────────────────────────────────────

@Composable
private fun ConjunctionsCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Conjunctions — But, Although, If, Because, While", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            ConnectorEntry(
                term = "mas",
                meaning = "but",
                description = "The most common conjunction for contrast or contradiction. Goes between the two clauses.",
                examples = listOf(
                    "Quero ir, mas estou cansado." to "I want to go, but I'm tired.",
                    "É caro, mas vale a pena." to "It's expensive, but it's worth it."
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            ConnectorEntry(
                term = "embora",
                meaning = "although / even though",
                description = "Concessive conjunction — introduces a fact that doesn't prevent the main clause from being true. Always triggers the subjunctive mood.",
                examples = listOf(
                    "Embora esteja cansado, vou sair." to "Although I'm tired, I'll go out.",
                    "Embora chovesse, fomos à praia." to "Although it was raining, we went to the beach."
                ),
                note = "Embora always takes the subjunctive (esteja, chovesse, etc.). Never use with the indicative."
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            ConnectorEntry(
                term = "apesar de",
                meaning = "despite / in spite of",
                description = "Easier alternative to embora — takes an infinitive or a noun, no subjunctive needed.",
                examples = listOf(
                    "Apesar de estar cansado, fui." to "Despite being tired, I went.",
                    "Apesar da chuva, saímos." to "Despite the rain, we went out."
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            ConnectorEntry(
                term = "se",
                meaning = "if",
                description = "Introduces a condition. For real/possible conditions use present indicative; for hypothetical use imperfect subjunctive.",
                examples = listOf(
                    "Se tiveres tempo, liga-me." to "If you have time, call me.",
                    "Se eu fosse rico, viajava muito." to "If I were rich, I'd travel a lot."
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            ConnectorEntry(
                term = "porque",
                meaning = "because",
                description = "Gives the reason for the main clause. Always followed by indicative.",
                examples = listOf(
                    "Fiquei em casa porque estava doente." to "I stayed home because I was sick.",
                    "Gosto de Lisboa porque é bonita." to "I like Lisbon because it's beautiful."
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            ConnectorEntry(
                term = "por causa de",
                meaning = "because of / due to",
                description = "Takes a noun or pronoun — it's the \"because of\" that precedes a thing rather than a clause. Contrast with porque, which introduces a full verb clause.",
                examples = listOf(
                    "Fiquei em casa por causa da chuva." to "I stayed home because of the rain.",
                    "Chegou tarde por causa do trânsito." to "She arrived late because of the traffic.",
                    "Foi por causa de ti." to "It was because of you."
                ),
                note = "porque + clause (\"porque estava doente\") vs por causa de + noun (\"por causa da doença\")."
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            ConnectorEntry(
                term = "enquanto",
                meaning = "while (simultaneous) / whereas (contrast)",
                description = "Enquanto covers two English meanings: simultaneous actions (\"while doing X\") and contrast between two situations (\"whereas\").",
                examples = listOf(
                    "Comi enquanto via televisão." to "I ate while watching TV.",
                    "Ela é extrovertida, enquanto ele é tímido." to "She's extroverted, whereas he's shy."
                )
            )
        }
    }
}

// ── Section 3: Adding & Contrasting ──────────────────────────────────────────

@Composable
private fun AddingContrastingCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Adding & Contrasting — Moreover, However, Therefore", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            ConnectorEntry(
                term = "além disso / para além disso",
                meaning = "moreover / in addition / furthermore",
                description = "Used to add a further point that reinforces what was just said. Para além disso is slightly more emphatic.",
                examples = listOf(
                    "É barato. Além disso, é muito bom." to "It's cheap. Moreover, it's very good.",
                    "Para além disso, fala três línguas." to "Furthermore, she speaks three languages."
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            ConnectorEntry(
                term = "no entanto / contudo / porém / todavia",
                meaning = "however / nevertheless",
                description = "All four express contrast with what came before. No entanto and contudo are common in both speech and writing. Porém and todavia feel more literary.",
                examples = listOf(
                    "Estudei muito. No entanto, reprovei." to "I studied a lot. However, I failed.",
                    "É difícil; contudo, é possível." to "It's difficult; nevertheless, it's possible."
                ),
                note = "All are interchangeable — pick no entanto for everyday use."
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            ConnectorEntry(
                term = "portanto / por isso",
                meaning = "therefore / so / that's why",
                description = "Express a logical consequence. Por isso (\"for that reason\") is more colloquial; portanto is also common in speech.",
                examples = listOf(
                    "Estava a chover, por isso fiquei em casa." to "It was raining, so I stayed home.",
                    "Não estudei, portanto reprovei." to "I didn't study, therefore I failed."
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            ConnectorEntry(
                term = "por um lado… por outro (lado)",
                meaning = "on one hand… on the other hand",
                description = "Used to present two contrasting aspects of the same topic. The second lado is often dropped in speech.",
                examples = listOf(
                    "Por um lado, é caro. Por outro, é de qualidade." to "On one hand, it's expensive. On the other, it's quality.",
                    "Por um lado quero ir, por outro estou cansado." to "On one hand I want to go, on the other I'm tired."
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            ConnectorEntry(
                term = "tanto… como",
                meaning = "both… and",
                description = "Connects two nouns or phrases as equally true or applicable.",
                examples = listOf(
                    "Tanto o João como a Maria vieram." to "Both João and Maria came.",
                    "Falo tanto inglês como português." to "I speak both English and Portuguese."
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            ConnectorEntry(
                term = "ou… ou  /  nem… nem",
                meaning = "either… or  /  neither… nor",
                description = "Ou…ou presents two alternatives. Nem…nem negates both options. With nem…nem the verb is still positive (the negation is in nem).",
                examples = listOf(
                    "Ou comes, ou sais." to "Either you eat, or you leave.",
                    "Nem come nem dorme." to "He neither eats nor sleeps."
                )
            )
        }
    }
}

// ── Section 4: Time & Condition ───────────────────────────────────────────────

@Composable
private fun TimeConditionCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Time & Condition — When, During, Then, Unless", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            ConnectorEntry(
                term = "quando",
                meaning = "when",
                description = "Introduces a time clause. Unlike English, when quando refers to a future event it takes the present subjunctive, not the future.",
                examples = listOf(
                    "Quando chegar, liga-me." to "When you arrive, call me.",
                    "Quando era criança, brincava muito." to "When I was a child, I played a lot."
                ),
                note = "Future events: quando + present subjunctive (\"quando chegar\" not \"quando chegará\")."
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            ConnectorEntry(
                term = "durante",
                meaning = "during / for (a duration)",
                description = "Followed by a noun or noun phrase. Covers both \"during\" (within an event) and \"for\" (a length of time).",
                examples = listOf(
                    "Durante o jantar, falámos." to "During dinner, we talked.",
                    "Estudei durante três horas." to "I studied for three hours."
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            ConnectorEntry(
                term = "depois / então",
                meaning = "then / afterwards / so then",
                description = "Depois = \"afterwards / after that\" (time sequence). Então = \"so then / in that case\" (also used as a filler meaning \"so, well\").",
                examples = listOf(
                    "Jantei e depois fui dormir." to "I had dinner and then went to sleep.",
                    "Então, o que é que achas?" to "So, what do you think?"
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            ConnectorEntry(
                term = "uma vez que / já que / visto que",
                meaning = "since / given that / seeing as",
                description = "Causal connectors that present a known fact as the reason for something. More formal than porque; common in writing and formal speech.",
                examples = listOf(
                    "Já que estás aqui, fica para jantar." to "Since you're here, stay for dinner.",
                    "Visto que não há bilhetes, ficamos em casa." to "Seeing as there are no tickets, we'll stay home."
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            ConnectorEntry(
                term = "a menos que / a não ser que",
                meaning = "unless",
                description = "Both mean \"unless\" and are interchangeable. They always take the subjunctive in the clause that follows.",
                examples = listOf(
                    "Vou sair, a menos que chova." to "I'll go out, unless it rains.",
                    "Fica, a não ser que precises de ir." to "Stay, unless you need to go."
                ),
                note = "Both take the subjunctive: \"a menos que chova\" (not \"chove\")."
            )
        }
    }
}

// ── Section 5: Manner & Emphasis ─────────────────────────────────────────────

@Composable
private fun MannerEmphasisCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Manner & Emphasis — Assim, Mesmo", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            ConnectorEntry(
                term = "assim",
                meaning = "like this / this way / so / thus",
                description = "Assim describes manner (\"do it like this\") or draws a conclusion (\"so / therefore\"). Combined with mesmo it means \"even so\". Combined with que it means \"as soon as\".",
                examples = listOf(
                    "Faz assim." to "Do it like this.",
                    "Assim não dá." to "It won't work this way.",
                    "Não é bem assim." to "It's not quite like that.",
                    "Assim que chegar, avisa-me." to "As soon as you arrive, let me know."
                ),
                note = "assim mesmo = exactly like that / just so. mesmo assim = even so (see mesmo below)."
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            ConnectorEntry(
                term = "mesmo / mesma",
                meaning = "same (adj.) / really / even / myself (reflexive)",
                description = "As an adjective, mesmo/mesma agrees with the noun gender and means \"the same\". As an adverb it intensifies (\"really / truly\"). In mesmo assim it means \"even so\". In mesmo que + subjunctive it means \"even if\".",
                examples = listOf(
                    "É a mesma coisa." to "It's the same thing.",
                    "O mesmo, por favor." to "The same (thing/order), please.",
                    "Isso mesmo!" to "Exactly! / That's it!",
                    "Mesmo assim, fui." to "Even so, I went.",
                    "Mesmo que chova, vou sair." to "Even if it rains, I'll go out.",
                    "Fiz isso eu mesmo." to "I did that myself. (male speaker)",
                    "Fiz isso eu mesma." to "I did that myself. (female speaker)"
                ),
                note = "mesmo que always takes the subjunctive. As a determiner: o mesmo livro = the same book (m.), a mesma ideia = the same idea (f.)."
            )
        }
    }
}

// ── Section 6: Tal ────────────────────────────────────────────────────────────

@Composable
private fun TalCard() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Such & How About — Tal", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            ConnectorEntry(
                term = "tal",
                meaning = "such / so",
                description = "Used as an adjective or pronoun to refer to something of that kind, or to something already mentioned. Also appears in fixed expressions like como tal (as such).",
                examples = listOf(
                    "Nunca vi tal coisa." to "I never saw such a thing.",
                    "Como tal, não posso ajudar." to "As such, I cannot help."
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            ConnectorEntry(
                term = "que tal?",
                meaning = "how about? / what do you think?",
                description = "Used to make suggestions or ask for someone's opinion. Can be followed by a noun, an infinitive, or the personal infinitive construction (e.g. irmos = us going).",
                examples = listOf(
                    "Que tal um café?" to "How about a coffee?",
                    "Que tal irmos ao cinema?" to "How about we go to the cinema?",
                    "Que tal o jantar?" to "How was the dinner?"
                ),
                note = "\"Que tal irmos\" uses the EP personal infinitive — irmos (we go). Very natural in everyday speech."
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            ConnectorEntry(
                term = "tal como",
                meaning = "just as / such as / like",
                description = "Introduces a comparison or example — meaning \"in the same way as\" or \"such as\". Goes at the start of a clause or in the middle of a sentence.",
                examples = listOf(
                    "Fiz tal como me pediste." to "I did it just as you asked me.",
                    "Tal como eu disse, precisamos de ajuda." to "Just as I said, we need help."
                )
            )
        }
    }
}

// ── Shared entry composable ───────────────────────────────────────────────────

@Composable
private fun ConnectorEntry(
    term: String,
    meaning: String,
    description: String,
    examples: List<Pair<String, String>>,
    note: String? = null
) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(term, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
        Text(meaning, style = MaterialTheme.typography.bodyMedium, fontStyle = FontStyle.Italic, color = MaterialTheme.colorScheme.primary)
        Text(description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.height(2.dp))
        examples.forEach { (pt, en) ->
            Text(
                "\u2022 $pt",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic
            )
            Text(
                "  $en",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        if (note != null) {
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                "Note: $note",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}
