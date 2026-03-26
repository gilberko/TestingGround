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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class ReflexivePronounRow(val subject: String, val pronoun: String)

private val reflexivePronounTable = listOf(
    ReflexivePronounRow("eu", "me"),
    ReflexivePronounRow("tu", "te"),
    ReflexivePronounRow("ele / ela / você", "se"),
    ReflexivePronounRow("nós", "nos"),
    ReflexivePronounRow("vós", "vos"),
    ReflexivePronounRow("eles / elas", "se")
)

private data class ReflexiveVerbEntry(val infinitive: String, val meaning: String)

private val commonReflexiveVerbs = listOf(
    ReflexiveVerbEntry("levantar-se", "to get up"),
    ReflexiveVerbEntry("deitar-se", "to lie down / go to bed"),
    ReflexiveVerbEntry("lavar-se", "to wash (oneself)"),
    ReflexiveVerbEntry("vestir-se", "to get dressed"),
    ReflexiveVerbEntry("sentar-se", "to sit down"),
    ReflexiveVerbEntry("chamar-se", "to be called / named"),
    ReflexiveVerbEntry("sentir-se", "to feel (a certain way)"),
    ReflexiveVerbEntry("lembrar-se (de)", "to remember"),
    ReflexiveVerbEntry("esquecer-se (de)", "to forget"),
    ReflexiveVerbEntry("preocupar-se (com)", "to worry (about)"),
    ReflexiveVerbEntry("encontrar-se", "to meet up / be located"),
    ReflexiveVerbEntry("habituar-se (a)", "to get used to")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReflexiveVerbsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reflexive Verbs") },
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

            // Intro
            item {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "What are reflexive verbs?",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Text(
                            text = "A reflexive verb indicates that the subject performs an action on itself. The verb is accompanied by a reflexive pronoun that agrees with the subject.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "lavar → to wash (something)    lavar-se → to wash oneself",
                            style = MaterialTheme.typography.bodyMedium,
                            fontStyle = FontStyle.Italic
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Ela lavou o carro.  →  She washed the car.",
                            style = MaterialTheme.typography.bodyMedium,
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            text = "Ela lavou-se.  →  She washed herself.",
                            style = MaterialTheme.typography.bodyMedium,
                            fontStyle = FontStyle.Italic
                        )
                    }
                }
            }

            // Pronoun table
            item {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Reflexive Pronouns",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Subject",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = "Pronoun",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.weight(1f)
                            )
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                        reflexivePronounTable.forEach { row ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 2.dp)
                            ) {
                                Text(
                                    text = row.subject,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontStyle = FontStyle.Italic,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = row.pronoun,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }

            // Enclisis
            item {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Default Placement: After the Verb (Enclisis)",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Text(
                            text = "In straightforward affirmative sentences, the pronoun is attached to the verb with a hyphen.",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        listOf(
                            "Ele levantou-se cedo." to "He got up early.",
                            "Lavo-me todos os dias." to "I wash myself every day.",
                            "Ela chama-se Ana." to "Her name is Ana.",
                            "Sentámo-nos à mesa." to "We sat down at the table."
                        ).forEach { (pt, en) ->
                            Text(
                                text = pt,
                                style = MaterialTheme.typography.bodyMedium,
                                fontStyle = FontStyle.Italic
                            )
                            Text(
                                text = en,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                        Text(
                            text = "With an infinitive, the pronoun also follows the verb:",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = "Vou lavar-me agora.",
                            style = MaterialTheme.typography.bodyMedium,
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            text = "I'm going to wash myself now.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Proclisis
            item {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Before the Verb (Proclisis)",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Text(
                            text = "Certain words pull the pronoun to the position before the verb. The pronoun is written separately — no hyphen.",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        listOf(
                            "After negation (não, nunca, jamais):" to listOf(
                                "Não me levantei cedo." to "I didn't get up early.",
                                "Nunca se esquece de nada." to "She never forgets anything."
                            ),
                            "After question / relative words (quando, quem, onde, que…):" to listOf(
                                "Quando se levantou?" to "When did he get up?",
                                "Quem se lembrou?" to "Who remembered?"
                            ),
                            "After subordinating conjunctions (que, porque, se, embora…):" to listOf(
                                "Espero que se sinta melhor." to "I hope you feel better.",
                                "Fiz isso porque me preocupei." to "I did that because I was worried."
                            ),
                            "After certain adverbs (já, ainda, sempre, também, talvez…):" to listOf(
                                "Ela já se foi." to "She has already gone.",
                                "Sempre me lembro disso." to "I always remember that."
                            )
                        ).forEach { (trigger, examples) ->
                            Text(
                                text = trigger,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(top = 4.dp, bottom = 2.dp)
                            )
                            examples.forEach { (pt, en) ->
                                Text(
                                    text = pt,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontStyle = FontStyle.Italic
                                )
                                Text(
                                    text = en,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(bottom = 2.dp)
                                )
                            }
                        }
                    }
                }
            }

            // Auxiliary verbs
            item {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "With Auxiliary Verbs",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Text(
                            text = "When a reflexive verb follows an auxiliary (poder, querer, ir, conseguir…), two positions are possible in European Portuguese:",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = "1. Pronoun on the infinitive (preferred in writing):",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "Ele pode lavar-se.",
                            style = MaterialTheme.typography.bodyMedium,
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            text = "He can wash himself.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Text(
                            text = "2. Pronoun on the auxiliary (common in speech):",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "Ele pode-se lavar.",
                            style = MaterialTheme.typography.bodyMedium,
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            text = "He can wash himself.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Text(
                            text = "3. With negation → proclisis is forced (pronoun before auxiliary):",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "Ele não se pode lavar.",
                            style = MaterialTheme.typography.bodyMedium,
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            text = "He cannot wash himself.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Common verbs list
            item {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Common Reflexive Verbs",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        commonReflexiveVerbs.forEach { entry ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 2.dp)
                            ) {
                                Text(
                                    text = entry.infinitive,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontStyle = FontStyle.Italic,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = entry.meaning,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.weight(1f)
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
