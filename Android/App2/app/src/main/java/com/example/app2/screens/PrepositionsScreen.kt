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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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

private data class PrepEntryFull(
    val preposition: String,
    val usage: String,
    val contractions: String,
    val examples: List<Pair<String, String>>
)

private data class ContrastivePair(
    val scenario: String,
    val aSentence: String,
    val aTranslation: String,
    val paraSentence: String,
    val paraTranslation: String
)

private val prepEntriesFull = listOf(
    PrepEntryFull(
        preposition = "a",
        usage = "Direction, time, indirect object marker.",
        contractions = "a + o = ao, a + a = à",
        examples = listOf(
            "Vou ao mercado." to "I'm going to the market.",
            "Chego às três horas." to "I arrive at three o'clock."
        )
    ),
    PrepEntryFull(
        preposition = "de",
        usage = "Origin, possession, material.",
        contractions = "de + o = do, de + a = da",
        examples = listOf(
            "Sou de Portugal." to "I am from Portugal.",
            "O carro do João é vermelho." to "João's car is red."
        )
    ),
    PrepEntryFull(
        preposition = "em",
        usage = "Location, time periods.",
        contractions = "em + o = no, em + a = na",
        examples = listOf(
            "Moro no Porto." to "I live in Porto.",
            "Nasceu em janeiro." to "He was born in January."
        )
    ),
    PrepEntryFull(
        preposition = "para",
        usage = "Long-term destination, purpose, recipient.",
        contractions = "(none)",
        examples = listOf(
            "Este livro é para ti." to "This book is for you.",
            "Viajamos para o Brasil." to "We are travelling to Brazil."
        )
    ),
    PrepEntryFull(
        preposition = "por",
        usage = "Reason, movement through, exchange, passive agent.",
        contractions = "por + o = pelo, por + a = pela",
        examples = listOf(
            "Passei pelo centro." to "I passed through the centre.",
            "O jantar foi feito pela minha mãe." to "Dinner was made by my mother."
        )
    ),
    PrepEntryFull(
        preposition = "com",
        usage = "Accompaniment, instrument.",
        contractions = "Special: comigo, contigo, consigo",
        examples = listOf(
            "Vim com a minha irmã." to "I came with my sister.",
            "Vens comigo?" to "Are you coming with me?"
        )
    ),
    PrepEntryFull(
        preposition = "sem",
        usage = "Without.",
        contractions = "(none)",
        examples = listOf(
            "Saí sem dinheiro." to "I left without money.",
            "Café sem açúcar, por favor." to "Coffee without sugar, please."
        )
    ),
    PrepEntryFull(
        preposition = "sobre",
        usage = "About (topic), on top of.",
        contractions = "(none)",
        examples = listOf(
            "Escreveu sobre história." to "He wrote about history.",
            "O livro está sobre a mesa." to "The book is on the table."
        )
    ),
    PrepEntryFull(
        preposition = "entre",
        usage = "Between, among.",
        contractions = "(none)",
        examples = listOf(
            "O café fica entre o banco e a farmácia." to "The café is between the bank and the pharmacy.",
            "Isto é entre nós." to "This is between us."
        )
    ),
    PrepEntryFull(
        preposition = "até",
        usage = "Endpoint in time or space.",
        contractions = "até + ao = até ao (written separately)",
        examples = listOf(
            "Trabalha até às seis." to "He works until six.",
            "Fui até ao rio." to "I went as far as the river."
        )
    ),
    PrepEntryFull(
        preposition = "desde",
        usage = "Starting point in time; pairs with até.",
        contractions = "(none)",
        examples = listOf(
            "Moro aqui desde 2015." to "I have lived here since 2015.",
            "Desde criança que gosto de música." to "I have liked music since childhood."
        )
    )
)

private val paraVsAExamples = listOf(
    ContrastivePair(
        scenario = "Trip to a city",
        aSentence = "Fui a Lisboa.",
        aTranslation = "I went to Lisbon (short trip, coming back).",
        paraSentence = "Fui para Lisboa.",
        paraTranslation = "I went to Lisbon (to stay / I moved there)."
    ),
    ContrastivePair(
        scenario = "Going to a place",
        aSentence = "Vou ao supermercado.",
        aTranslation = "I'm going to the supermarket (and returning).",
        paraSentence = "Vou para o supermercado.",
        paraTranslation = "I'm heading to the supermarket (no return implied)."
    ),
    ContrastivePair(
        scenario = "Going home",
        aSentence = "Vou a casa.",
        aTranslation = "I'm going home briefly.",
        paraSentence = "Vou para casa.",
        paraTranslation = "I'm going home for the day / settling in."
    ),
    ContrastivePair(
        scenario = "Giving vs. sending",
        aSentence = "Dei o livro ao João.",
        aTranslation = "I gave the book to João (indirect object — handed directly).",
        paraSentence = "Mandei o livro para o João.",
        paraTranslation = "I sent the book to João (recipient of a sent item)."
    ),
    ContrastivePair(
        scenario = "Time vs. purpose",
        aSentence = "Chego às oito.",
        aTranslation = "I arrive at eight (time expression).",
        paraSentence = "Saio para o trabalho às oito.",
        paraTranslation = "I leave for work at eight (purpose / destination)."
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrepositionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Prepositions") },
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

            prepEntriesFull.forEachIndexed { index, entry ->
                item(key = entry.preposition) {
                    PrepCardFull(entry)
                }
                if (index == 3) {
                    item(key = "comparison_para_a") {
                        ParaVsACard()
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun PrepCardFull(entry: PrepEntryFull) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = entry.preposition,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = entry.usage,
                style = MaterialTheme.typography.bodyMedium,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            if (entry.contractions != "(none)") {
                Text(
                    text = "Contractions: ${entry.contractions}",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
            }
            entry.examples.forEach { (pt, en) ->
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

@Composable
private fun ParaVsACard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "para vs. a",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Both can express direction, but they differ in permanence, purpose, and usage.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Use a for:",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    listOf(
                        "Short trip (returning)",
                        "Time: às três",
                        "Indirect object",
                        "Nearby destination"
                    ).forEach { bullet ->
                        Text(
                            text = "• $bullet",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(bottom = 2.dp)
                        )
                    }
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Use para for:",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    listOf(
                        "Long stay / no return",
                        "Purpose / goal",
                        "Recipient (sent item)",
                        "Permanent relocation"
                    ).forEach { bullet ->
                        Text(
                            text = "• $bullet",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(bottom = 2.dp)
                        )
                    }
                }
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Text(
                text = "Contrastive Examples",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            paraVsAExamples.forEach { pair ->
                ContrastivePairRow(pair)
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}

@Composable
private fun ContrastivePairRow(pair: ContrastivePair) {
    Column {
        Text(
            text = pair.scenario,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = "a: ${pair.aSentence}",
            style = MaterialTheme.typography.bodySmall,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = pair.aTranslation,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(start = 16.dp, bottom = 2.dp)
        )
        Text(
            text = "para: ${pair.paraSentence}",
            style = MaterialTheme.typography.bodySmall,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = pair.paraTranslation,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}
