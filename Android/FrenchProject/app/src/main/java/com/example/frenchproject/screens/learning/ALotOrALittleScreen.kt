package com.example.frenchproject.screens.learning

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frenchproject.ui.theme.FrenchBlue
import com.example.frenchproject.ui.theme.FrenchNavy

private data class QuantEntry(
    val expression: String,
    val meaning: String,
    val examples: List<Pair<String, String>>,
    val note: String? = null
)

private val aLotEntries = listOf(
    QuantEntry(
        expression = "beaucoup de",
        meaning = "a lot of / many / much",
        examples = listOf(
            "beaucoup de fleurs" to "many flowers",
            "beaucoup de gens" to "many people",
            "beaucoup d'eau" to "a lot of water",
            "Il mange beaucoup." to "He eats a lot. (no de when no noun follows)"
        ),
        note = "Use beaucoup de + noun (no article). The noun is PLURAL for countable things, SINGULAR for non-countable."
    )
)

private val aLittleEntries = listOf(
    QuantEntry(
        expression = "un peu de",
        meaning = "a little (bit of)",
        examples = listOf(
            "un peu d'eau" to "a little water",
            "un peu de temps" to "a little time",
            "un peu de sel" to "a little salt"
        ),
        note = "Used with non-countable nouns. Friendly and positive in tone."
    ),
    QuantEntry(
        expression = "peu de",
        meaning = "few / little (not much)",
        examples = listOf(
            "peu de gens" to "few people",
            "peu de temps" to "little time",
            "peu d'argent" to "little money"
        ),
        note = "More negative in tone than un peu de — implies a disappointing or insufficient amount."
    ),
    QuantEntry(
        expression = "quelques",
        meaning = "a few / some",
        examples = listOf(
            "quelques personnes" to "a few people",
            "quelques voitures" to "a few cars",
            "quelques minutes" to "a few minutes"
        ),
        note = "Only used with COUNTABLE plural nouns. Always causes the noun to be plural."
    )
)

private val someEntries = listOf(
    QuantEntry(
        expression = "du / de la / de l' / des",
        meaning = "some (partitive/indefinite article)",
        examples = listOf(
            "des gens" to "some people",
            "du pain" to "some bread",
            "de la musique" to "some music",
            "de l'eau" to "some water"
        ),
        note = "Use du (m.), de la (f.), de l' (before vowel/h), des (plural). This is the default 'some' built into French articles."
    ),
    QuantEntry(
        expression = "quelques",
        meaning = "a few / some (small number)",
        examples = listOf(
            "quelques amis" to "some friends (a few)",
            "quelques idées" to "some ideas"
        ),
        note = "More specific than des — implies a small, limited number."
    ),
    QuantEntry(
        expression = "certains / certaines",
        meaning = "some / certain (specific subset)",
        examples = listOf(
            "Certaines personnes pensent que..." to "Some people think that...",
            "Certains jours sont difficiles." to "Some days are difficult."
        ),
        note = "Certains (m. pl.) / certaines (f. pl.). Implies 'a specific subset' rather than just any small amount."
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ALotOrALittleScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("A Lot Or A Little", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = FrenchBlue)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            // Intro
            item {
                Text(
                    text = "French quantity expressions use de/d' (not the article) before the noun. Whether the noun is countable or non-countable determines if it is plural or singular.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // Section 1: A Lot
            item { AlasSectionHeader("A Lot — beaucoup de") }
            items(aLotEntries.size) { i -> AlasQuantCard(aLotEntries[i]) }

            // Section 2: A Little / A Few
            item {
                Spacer(modifier = Modifier.height(12.dp))
                AlasSectionHeader("A Little / A Few")
            }
            items(aLittleEntries.size) { i -> AlasQuantCard(aLittleEntries[i]) }

            // Section 3: Some
            item {
                Spacer(modifier = Modifier.height(12.dp))
                AlasSectionHeader("Some")
            }
            items(someEntries.size) { i -> AlasQuantCard(someEntries[i]) }

            // Section 4: Something / Someone / Somewhere / Sometimes
            item {
                Spacer(modifier = Modifier.height(12.dp))
                AlasSectionHeader("Something / Someone / Somewhere / Sometimes")
            }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        AlasEntryBlock("quelque chose", "something",
                            "J'ai quelque chose à dire.  →  I have something to say.\nIl mange quelque chose.  →  He's eating something.")
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(color = FrenchBlue.copy(alpha = 0.2f))
                        Spacer(modifier = Modifier.height(8.dp))
                        AlasEntryBlock("quelqu'un", "someone / somebody",
                            "Quelqu'un a appelé.  →  Someone called.\nJe cherche quelqu'un.  →  I'm looking for someone.")
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(color = FrenchBlue.copy(alpha = 0.2f))
                        Spacer(modifier = Modifier.height(8.dp))
                        AlasEntryBlock("quelque part", "somewhere",
                            "Je l'ai vu quelque part.  →  I saw it somewhere.\nElle habite quelque part en France.  →  She lives somewhere in France.")
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(color = FrenchBlue.copy(alpha = 0.2f))
                        Spacer(modifier = Modifier.height(8.dp))
                        AlasEntryBlock("parfois  /  quelquefois", "sometimes",
                            "Il vient parfois le soir.  →  He sometimes comes in the evening.\nQuelquefois je me trompe.  →  Sometimes I make mistakes.")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "parfois and quelquefois are interchangeable. parfois is slightly more common in modern speech.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Section 5: Some Other Time
            item {
                Spacer(modifier = Modifier.height(12.dp))
                AlasSectionHeader("Some Other Time")
            }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        AlasEntryBlock("une autre fois", "some other time / another time",
                            "On peut faire ça une autre fois.  →  We can do that some other time.\nUne autre fois, peut-être.  →  Another time, perhaps.")
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(color = FrenchBlue.copy(alpha = 0.2f))
                        Spacer(modifier = Modifier.height(8.dp))
                        AlasEntryBlock("à un autre moment", "at another time / some other time",
                            "Parlons-en à un autre moment.  →  Let's talk about it some other time.")
                    }
                }
            }

            // Section 6: Not Too Many / Not A Lot
            item {
                Spacer(modifier = Modifier.height(12.dp))
                AlasSectionHeader("Not Too Many / Not A Lot")
            }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        AlasEntryBlock("pas trop de", "not too many / not too much",
                            "pas trop de sel  →  not too much salt\npas trop de voitures  →  not too many cars\nIl ne faut pas trop de stress.  →  There shouldn't be too much stress.")
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(color = FrenchBlue.copy(alpha = 0.2f))
                        Spacer(modifier = Modifier.height(8.dp))
                        AlasEntryBlock("pas beaucoup de", "not a lot of / not many",
                            "pas beaucoup d'eau  →  not a lot of water\npas beaucoup de gens  →  not many people\nIl n'y a pas beaucoup de temps.  →  There isn't a lot of time.")
                    }
                }
            }

            // Section 7: Noun Form Rule
            item {
                Spacer(modifier = Modifier.height(12.dp))
                AlasSectionHeader("Noun Form Rule")
            }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "After beaucoup de, peu de, trop de, assez de, pas beaucoup de, pas trop de:",
                            fontWeight = FontWeight.SemiBold,
                            color = FrenchNavy,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        AlasRuleRow("Countable nouns", "PLURAL",
                            "beaucoup de fleurs, quelques voitures, peu de gens")
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(color = FrenchBlue.copy(alpha = 0.25f))
                        Spacer(modifier = Modifier.height(8.dp))
                        AlasRuleRow("Non-countable nouns", "SINGULAR",
                            "beaucoup d'eau, un peu de sel, pas trop de bruit")
                        Spacer(modifier = Modifier.height(10.dp))
                        HorizontalDivider(color = FrenchBlue.copy(alpha = 0.25f))
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "In both cases, the article is dropped — use de/d' directly before the noun, NOT du/de la/des.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 18.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "✗  beaucoup des fleurs\n✓  beaucoup de fleurs\n\n✗  beaucoup de l'eau\n✓  beaucoup d'eau",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue,
                            lineHeight = 20.sp
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun AlasSectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = FrenchBlue,
        modifier = Modifier.padding(bottom = 4.dp)
    )
    HorizontalDivider(color = FrenchBlue.copy(alpha = 0.4f))
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
private fun AlasEntryBlock(expression: String, meaning: String, example: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = expression,
            fontWeight = FontWeight.Bold,
            color = FrenchBlue,
            fontSize = 14.sp,
            modifier = Modifier.width(175.dp)
        )
        Text(text = meaning, style = MaterialTheme.typography.bodyMedium, color = FrenchNavy)
    }
    Spacer(modifier = Modifier.height(3.dp))
    Text(
        text = example,
        style = MaterialTheme.typography.bodySmall,
        fontStyle = FontStyle.Italic,
        color = FrenchBlue,
        lineHeight = 20.sp
    )
}

@Composable
private fun AlasRuleRow(type: String, form: String, examples: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = type,
            fontWeight = FontWeight.SemiBold,
            color = FrenchNavy,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.width(150.dp)
        )
        Text(
            text = "→ $form",
            fontWeight = FontWeight.Bold,
            color = FrenchBlue,
            style = MaterialTheme.typography.bodyMedium
        )
    }
    Spacer(modifier = Modifier.height(3.dp))
    Text(
        text = examples,
        style = MaterialTheme.typography.bodySmall,
        fontStyle = FontStyle.Italic,
        color = FrenchBlue
    )
}

@Composable
private fun AlasQuantCard(entry: QuantEntry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = entry.expression,
                    fontWeight = FontWeight.Bold,
                    color = FrenchBlue,
                    fontSize = 15.sp,
                    modifier = Modifier.width(150.dp)
                )
                Text(
                    text = entry.meaning,
                    style = MaterialTheme.typography.bodyMedium,
                    color = FrenchNavy,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            entry.examples.forEach { (french, english) ->
                Text(
                    text = "$french  →  $english",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = FrenchBlue,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
            }
            if (entry.note != null) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = entry.note,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 18.sp
                )
            }
        }
    }
}
