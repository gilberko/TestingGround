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
fun CliticPronounsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Object Pronouns (Clitics)") },
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
                            text = "What are clitic pronouns?",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Text(
                            text = "Object pronouns (clitics) replace a noun that was mentioned earlier. There are two types:",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Text(
                            text = "• Direct object — answers \"what?\" or \"whom?\"",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 2.dp)
                        )
                        Text(
                            text = "• Indirect object — answers \"to/for whom?\"",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = "Eu li o livro.  →  Eu li-o.",
                            style = MaterialTheme.typography.bodyMedium,
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            text = "I read the book.  →  I read it.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = "Eu disse isso ao João.  →  Eu disse-lhe isso.",
                            style = MaterialTheme.typography.bodyMedium,
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            text = "I told that to João.  →  I told him that.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Direct object pronouns
            item {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Direct Object Pronouns",
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
                                modifier = Modifier.weight(0.5f)
                            )
                            Text(
                                text = "Meaning",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.weight(1f)
                            )
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                        listOf(
                            Triple("eu", "me", "me"),
                            Triple("tu", "te", "you"),
                            Triple("ele / ela / você", "o / a", "him / her / it"),
                            Triple("nós", "nos", "us"),
                            Triple("vós", "vos", "you (pl.)"),
                            Triple("eles / elas", "os / as", "them")
                        ).forEach { (subject, pronoun, meaning) ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 2.dp)
                            ) {
                                Text(
                                    text = subject,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontStyle = FontStyle.Italic,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = pronoun,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.weight(0.5f)
                                )
                                Text(
                                    text = meaning,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }

            // Indirect object pronouns
            item {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Indirect Object Pronouns",
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
                                modifier = Modifier.weight(0.5f)
                            )
                            Text(
                                text = "Meaning",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.weight(1f)
                            )
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                        listOf(
                            Triple("eu", "me", "to/for me"),
                            Triple("tu", "te", "to/for you"),
                            Triple("ele / ela / você", "lhe", "to/for him/her"),
                            Triple("nós", "nos", "to/for us"),
                            Triple("vós", "vos", "to/for you (pl.)"),
                            Triple("eles / elas", "lhes", "to/for them")
                        ).forEach { (subject, pronoun, meaning) ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 2.dp)
                            ) {
                                Text(
                                    text = subject,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontStyle = FontStyle.Italic,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = pronoun,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.weight(0.5f)
                                )
                                Text(
                                    text = meaning,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
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
                            text = "In affirmative sentences, the pronoun follows the verb and is joined to it with a hyphen.",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        listOf(
                            "Ela ajudou-me." to "She helped me.",
                            "Vejo-os todos os dias." to "I see them every day.",
                            "Disse-lhe a verdade." to "I told him/her the truth.",
                            "Ele comprou-a ontem." to "He bought it yesterday."
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
                            text = "Certain words trigger proclisis — the pronoun moves before the verb with no hyphen.",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        listOf(
                            "After negation (não, nunca, jamais):" to listOf(
                                "Não o vi." to "I didn't see him.",
                                "Nunca lhe disse isso." to "I never told him/her that."
                            ),
                            "After question / relative words (quem, que, quando, onde…):" to listOf(
                                "Quem te ensinou?" to "Who taught you?",
                                "O que lhe disseste?" to "What did you say to him/her?"
                            ),
                            "After subordinating conjunctions (que, quando, se, porque, embora…):" to listOf(
                                "Espero que o faças." to "I hope you do it.",
                                "Disse-me que te viu." to "He told me he saw you."
                            ),
                            "After certain adverbs (já, ainda, sempre, também, talvez…):" to listOf(
                                "Ela já me ligou." to "She has already called me.",
                                "Sempre te ajudo." to "I always help you."
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

            // Infinitive form
            item {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Pronoun + Infinitive Form",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Text(
                            text = "When a direct object pronoun (o, a, os, as) attaches to an infinitive, the -r is dropped and an -l is inserted before the pronoun. An accent is often added to preserve the stress.",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = "Rule:  infinitive − r  +  l  +  pronoun",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Infinitive + pronoun",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.weight(1.2f)
                            )
                            Text(
                                text = "Result",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.weight(0.8f)
                            )
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                        listOf(
                            "ver + o" to "vê-lo",
                            "dar + a" to "dá-la",
                            "fazer + os" to "fazê-los",
                            "pôr + as" to "pô-las",
                            "comprar + o" to "comprá-lo",
                            "comer + a" to "comê-la",
                            "partir + os" to "parti-los"
                        ).forEach { (source, result) ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 2.dp)
                            ) {
                                Text(
                                    text = source,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontStyle = FontStyle.Italic,
                                    modifier = Modifier.weight(1.2f)
                                )
                                Text(
                                    text = result,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.weight(0.8f)
                                )
                            }
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
                        Text(
                            text = "Note: me, te, lhe, nos, vos, lhes do not trigger this change — they attach directly after the -r drops only for o/a/os/as.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        listOf(
                            "Quero vê-lo amanhã." to "I want to see him tomorrow.",
                            "Vou comprá-la no mercado." to "I'm going to buy it at the market.",
                            "Ela conseguiu fazê-los." to "She managed to do them."
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
                    }
                }
            }

            // Auxiliary + infinitive
            item {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Auxiliary + Infinitive",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Text(
                            text = "When a clitic accompanies an auxiliary verb (poder, querer, ir, conseguir…) + infinitive, two positions are valid in European Portuguese:",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = "1. Clitic on the infinitive (preferred in writing):",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "Posso vê-lo.  /  Quero comprá-la.",
                            style = MaterialTheme.typography.bodyMedium,
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            text = "I can see him.  /  I want to buy it.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = "2. Clitic on the auxiliary (common in speech):",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "Posso-o ver.  /  Quero-a comprar.",
                            style = MaterialTheme.typography.bodyMedium,
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            text = "I can see him.  /  I want to buy it.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = "3. With negation → proclisis is forced:",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "Não o posso ver.  /  Não a quero comprar.",
                            style = MaterialTheme.typography.bodyMedium,
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            text = "I cannot see him.  /  I don't want to buy it.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Combined pronouns
            item {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Combined Pronouns (lho / lha…)",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Text(
                            text = "When an indirect pronoun (lhe / lhes) and a direct pronoun (o, a, os, as) are used together, they merge into a single form. This is found mainly in formal writing.",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        listOf(
                            "lhe + o" to "lho",
                            "lhe + a" to "lha",
                            "lhe + os" to "lhos",
                            "lhe + as" to "lhas",
                            "lhes + o" to "lho",
                            "lhes + a" to "lha"
                        ).forEach { (source, result) ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 2.dp)
                            ) {
                                Text(
                                    text = source,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontStyle = FontStyle.Italic,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "→  $result",
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
                        Text(
                            text = "Dei o livro ao João.  →  Dei-lho.",
                            style = MaterialTheme.typography.bodyMedium,
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            text = "I gave the book to João.  →  I gave it to him.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = "In everyday European Portuguese speech, it is more natural to say \"Dei o livro ao João\" or use a full noun phrase rather than combined clitics.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontStyle = FontStyle.Italic
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}
