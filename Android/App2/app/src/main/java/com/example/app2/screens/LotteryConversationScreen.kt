package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private val lotterySections = listOf(
    ConversationSection(
        heading = null,
        lines = listOf(
            DialogueLine(
                speaker = "Vasco",
                pt = "Os números do sorteio desta semana são exatamente os que eu costumava preencher. Se os tivesse preenchido desta vez, tinha sido milionário!",
                en = "This week's lottery numbers are exactly the ones I used to fill out. If I had filled them in this time, I'd have been a millionaire!"
            ),
            DialogueLine(
                speaker = "Nuno",
                pt = "Que azar! Oxalá ganhes a lotaria da próxima vez.",
                en = "What bad luck! I wish you win the lottery next time."
            ),
            DialogueLine(
                speaker = "Vasco",
                pt = "Obrigado! Se alguma vez ganhar a lotaria, vou comprar uma casa grande e um carro novo, e vou viajar pelo mundo.",
                en = "Thanks! If I ever win the lottery, I'll buy a big house and a new car, and travel the world."
            ),
            DialogueLine(
                speaker = "Nuno",
                pt = "E os amigos, Vasco? Se eu alguma vez ganhar a lotaria, também vou partilhá-la com os meus amigos.",
                en = "And your friends, Vasco? If I ever win the lottery, I'll share it with my friends too."
            ),
            DialogueLine(
                speaker = "Vasco",
                pt = "Sim, é uma boa ideia... Quem me dera que ganhasses a lotaria!",
                en = "Yes, that's a good idea... I really wish you'd win the lottery!"
            )
        )
    )
)

private data class RemarkEntry(val phrase: String, val explanation: String)

private val lotteryRemarks = listOf(
    RemarkEntry(
        "\"tivesse preenchido\" — Pretérito Mais-que-Perfeito do Conjuntivo",
        "The se-clause describes a hypothetical past that did not happen (he didn't fill out those numbers), so Portuguese requires the compound past subjunctive, not the indicative."
    ),
    RemarkEntry(
        "\"tinha sido\" — Imperfeito do Indicativo used as the result clause",
        "Colloquial European Portuguese frequently substitutes the Imperfeito do Indicativo for the more formal Condicional Composto (\"teria sido milionário\") in the result clause of a past-unreal conditional. Both are correct; \"tinha sido\" is what you'll actually hear in speech."
    ),
    RemarkEntry(
        "\"Oxalá ganhes\" — Presente do Conjuntivo",
        "Oxalá (from Arabic \"law šā' allāh\", if God wills) always triggers the subjunctive; since winning next time is still a real future possibility, the present subjunctive — not the imperfect — is used."
    ),
    RemarkEntry(
        "\"Se alguma vez ganhar\" — Futuro do Conjuntivo",
        "Used because the condition is open/still possible, not contrary-to-fact. This is the mood Portuguese always uses after se when talking about a possible future event, paired with the Futuro do Indicativo or ir + infinitive in the main clause."
    ),
    RemarkEntry(
        "\"vou comprar / vou viajar / vou partilhá-la\" — ir + infinitive (futuro próximo)",
        "The natural spoken-EP counterpart to the Futuro do Indicativo. \"Compraria / viajaria\" (Condicional Simples) would be a common learner mistake here — see the next remark."
    ),
    RemarkEntry(
        "Why not \"compraria\" (Condicional Simples)?",
        "A literal translation of English \"I would buy\" tempts learners toward the conditional, but Portuguese reserves se + Imperfeito do Conjuntivo, ... Condicional for conditions presented as unlikely or hypothetical. Since winning the lottery \"someday\" is treated as a real, standing possibility (not a rejected hypothetical), natural Portuguese uses Se + Futuro do Conjuntivo, ... Futuro/ir + infinitivo instead — a key mismatch point between English \"if...would\" and Portuguese mood choice."
    ),
    RemarkEntry(
        "\"Quem me dera que ganhasses\" — Imperfeito do Conjuntivo",
        "Quem me dera que (\"how I wish that...\") is a fixed wistful-wish construction that always takes the imperfect subjunctive, giving a more emotionally loaded/longing tone than oxalá + present subjunctive."
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LotteryConversationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Winning The Lottery") },
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
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Text(
                    text = "Two friends, Vasco and Nuno, talk about this week's lottery numbers.",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            lotterySections.forEach { section ->
                if (section.heading != null) {
                    item {
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            HorizontalDivider(modifier = Modifier.weight(1f))
                            Text(
                                text = section.heading,
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            HorizontalDivider(modifier = Modifier.weight(1f))
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }

                section.lines.forEach { line ->
                    item {
                        DialogueCard(line = line, isFirst = line.speaker == "Vasco")
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    HorizontalDivider(modifier = Modifier.weight(1f))
                    Text(
                        text = "Remarks — Why Each Mood Was Chosen",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    HorizontalDivider(modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(4.dp))
            }

            lotteryRemarks.forEach { remark ->
                item {
                    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(
                                text = remark.phrase,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = remark.explanation,
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}
