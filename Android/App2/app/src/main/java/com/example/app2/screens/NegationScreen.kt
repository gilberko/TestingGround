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

private data class NegationTopic(
    val name: String,
    val subtitle: String,
    val howToUse: List<String>,
    val examples: List<Pair<String, String>>,
    val note: String? = null
)

private val basicNegation = listOf(
    NegationTopic(
        name = "não",
        subtitle = "not / don't / doesn't",
        howToUse = listOf(
            "Place não directly before the verb",
            "No auxiliary verb needed — unlike English 'do/does/did'",
            "Works for all tenses and persons",
            "Repeated at end of sentence = tag question: não?"
        ),
        examples = listOf(
            "Não falo inglês." to "I don't speak English.",
            "Ela não quer ir." to "She doesn't want to go.",
            "Não tenho fome." to "I'm not hungry.",
            "Ele fala português, não?" to "He speaks Portuguese, doesn't he?"
        )
    )
)

private val doubleNegation = listOf(
    NegationTopic(
        name = "Dupla Negação",
        subtitle = "Double Negation — required in EP",
        howToUse = listOf(
            "When a negative word (ninguém, nada, nunca, nenhum…) comes AFTER the verb, não must still precede the verb",
            "This is grammatically required — unlike English where only one negative is used",
            "When the negative word comes BEFORE the verb, não is dropped"
        ),
        examples = listOf(
            "Não vi ninguém." to "I didn't see anyone. (lit. I didn't see nobody)",
            "Não tenho nada." to "I don't have anything. (lit. I don't have nothing)",
            "Não fui nunca lá." to "I never went there.",
            "Ninguém sabe." to "Nobody knows. (ninguém before verb — no não needed)",
            "Nada aconteceu." to "Nothing happened. (nada before verb — no não needed)"
        ),
        note = "Double negation is standard and correct in Portuguese. It is not informal or incorrect — it is required grammar."
    )
)

private val nemEntries = listOf(
    NegationTopic(
        name = "nem",
        subtitle = "nor / neither / not even",
        howToUse = listOf(
            "Adds a negation: 'not even', 'nor', 'neither'",
            "When nem starts a clause it carries the negation — no separate não needed",
            "Often used for emphasis: nem sequer = not even"
        ),
        examples = listOf(
            "Nem eu!" to "Neither do I! / Not me either!",
            "Nem sequer tentou." to "He didn't even try.",
            "Nem eu sei." to "Not even I know.",
            "Nem quero pensar nisso." to "I don't even want to think about it."
        )
    ),
    NegationTopic(
        name = "nem…nem",
        subtitle = "neither…nor",
        howToUse = listOf(
            "Pairs two negated items",
            "When the nem…nem follows the verb, não still precedes the verb (double negation)",
            "When nem…nem precedes the verb, não is dropped"
        ),
        examples = listOf(
            "Não gosto nem de café nem de chá." to "I like neither coffee nor tea.",
            "Nem fala nem escreve." to "He neither speaks nor writes.",
            "Não é nem bonito nem feio." to "It's neither pretty nor ugly."
        )
    )
)

private val nenhumEntries = listOf(
    NegationTopic(
        name = "nenhum / nenhuma",
        subtitle = "no / none / not any",
        howToUse = listOf(
            "Adjective or pronoun — agrees in gender with the noun",
            "Nenhum (masculine), nenhuma (feminine)",
            "EP strongly prefers the singular form even with plural nouns",
            "When it follows the verb, não precedes the verb (double negation)",
            "When it precedes the verb as subject, não is dropped"
        ),
        examples = listOf(
            "Não tenho nenhum amigo aqui." to "I have no friend here.",
            "Nenhum deles veio." to "None of them came.",
            "Não encontrei nenhuma solução." to "I found no solution.",
            "Não há nenhum problema." to "There is no problem."
        )
    )
)

private val nuncaEntries = listOf(
    NegationTopic(
        name = "nunca",
        subtitle = "never",
        howToUse = listOf(
            "Can precede the verb (no não needed) or follow the verb (double negation with não)",
            "Nunca mais = never again",
            "Nunca se sabe = you never know (fixed expression)"
        ),
        examples = listOf(
            "Nunca fui a Lisboa." to "I've never been to Lisbon.",
            "Não vou nunca lá." to "I'll never go there.",
            "Nunca mais!" to "Never again!",
            "Nunca se sabe." to "You never know."
        )
    ),
    NegationTopic(
        name = "jamais",
        subtitle = "never — emphatic / literary",
        howToUse = listOf(
            "Stronger and more formal than nunca",
            "Common in literary or formal contexts, and for emphasis in speech",
            "Nunca jamais = never ever (doubly emphatic)"
        ),
        examples = listOf(
            "Jamais me esquecerei disto." to "I will never forget this.",
            "Nunca jamais voltarei lá." to "I will never ever go back there.",
            "Jamais pensei que isto aconteceria." to "I never thought this would happen."
        )
    )
)

private val pronounEntries = listOf(
    NegationTopic(
        name = "ninguém",
        subtitle = "nobody / no one / anyone (in negative context)",
        howToUse = listOf(
            "Refers to people",
            "Subject position (before verb) → no não needed",
            "After verb → double negation: não + verb + ninguém",
            "Never takes a plural — always singular"
        ),
        examples = listOf(
            "Ninguém sabe." to "Nobody knows.",
            "Não conheço ninguém aqui." to "I don't know anyone here.",
            "Não há ninguém em casa." to "There's no one at home.",
            "Ninguém me entende." to "Nobody understands me."
        )
    ),
    NegationTopic(
        name = "nada",
        subtitle = "nothing / anything (in negative context)",
        howToUse = listOf(
            "Refers to things",
            "Subject position (before verb) → no não needed",
            "After verb → double negation: não + verb + nada",
            "De nada = you're welcome (fixed expression, lit. 'of nothing')"
        ),
        examples = listOf(
            "Nada aconteceu." to "Nothing happened.",
            "Não vi nada." to "I didn't see anything.",
            "Não tenho nada a dizer." to "I have nothing to say.",
            "De nada." to "You're welcome."
        )
    )
)

private val temporalNegation = listOf(
    NegationTopic(
        name = "ainda não",
        subtitle = "not yet",
        howToUse = listOf(
            "Ainda não + verb = the action has not happened yet",
            "Ainda não is placed before the verb"
        ),
        examples = listOf(
            "Ainda não cheguei." to "I haven't arrived yet.",
            "Ainda não sei." to "I don't know yet.",
            "Ainda não acabou." to "It's not finished yet."
        )
    ),
    NegationTopic(
        name = "já não",
        subtitle = "no longer / not anymore",
        howToUse = listOf(
            "Já não + verb = something that used to be true is no longer true",
            "Já não is placed before the verb"
        ),
        examples = listOf(
            "Já não vivo aqui." to "I no longer live here.",
            "Ela já não trabalha lá." to "She doesn't work there anymore.",
            "Já não me lembro." to "I don't remember anymore."
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NegationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Negation") },
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

            item { NegationSectionHeader("Negação Básica — Basic Negation") }
            basicNegation.forEach { entry ->
                item(key = entry.name) { NegationCard(entry) }
            }

            item { NegationSectionHeader("Dupla Negação — Double Negation") }
            doubleNegation.forEach { entry ->
                item(key = entry.name) { NegationCard(entry) }
            }

            item { NegationSectionHeader("\"nem\" e \"nem…nem\"") }
            nemEntries.forEach { entry ->
                item(key = entry.name) { NegationCard(entry) }
            }

            item { NegationSectionHeader("\"nenhum\" / \"nenhuma\"") }
            nenhumEntries.forEach { entry ->
                item(key = entry.name) { NegationCard(entry) }
            }

            item { NegationSectionHeader("\"nunca\" e \"jamais\" — Never") }
            nuncaEntries.forEach { entry ->
                item(key = entry.name) { NegationCard(entry) }
            }

            item { NegationSectionHeader("\"ninguém\" e \"nada\" — Nobody & Nothing") }
            pronounEntries.forEach { entry ->
                item(key = entry.name) { NegationCard(entry) }
            }

            item { NegationSectionHeader("\"ainda não\" e \"já não\" — Not Yet & No Longer") }
            temporalNegation.forEach { entry ->
                item(key = entry.name) { NegationCard(entry) }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun NegationSectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
    )
}

@Composable
private fun NegationCard(entry: NegationTopic) {
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
                text = "How to use:",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            entry.howToUse.forEach { bullet ->
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
