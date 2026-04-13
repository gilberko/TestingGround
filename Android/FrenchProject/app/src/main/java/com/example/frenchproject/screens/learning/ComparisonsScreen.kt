package com.example.frenchproject.screens.learning

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

private data class ComparisonItem(
    val formula: String,
    val meaning: String,
    val example: String,
    val translation: String
)

private data class IrregularItem(
    val base: String,
    val comparative: String,
    val meaning: String,
    val example: String,
    val translation: String,
    val note: String? = null
)

private data class SuperlativeItem(
    val formula: String,
    val meaning: String,
    val example: String,
    val translation: String
)

private val comparisons = listOf(
    ComparisonItem(
        formula = "plus + adj + que",
        meaning = "more … than",
        example = "Elle est plus grande que moi.",
        translation = "She is taller than me."
    ),
    ComparisonItem(
        formula = "moins + adj + que",
        meaning = "less … than",
        example = "Ce film est moins intéressant que l'autre.",
        translation = "This film is less interesting than the other one."
    ),
    ComparisonItem(
        formula = "aussi + adj + que",
        meaning = "as … as",
        example = "Il est aussi fort que son frère.",
        translation = "He is as strong as his brother."
    )
)

private val irregulars = listOf(
    IrregularItem(
        base = "bon(ne)  (good — adjective)",
        comparative = "meilleur(e)",
        meaning = "better",
        example = "Ce vin est meilleur que l'autre.",
        translation = "This wine is better than the other.",
        note = "Never say \"plus bon\" — always use meilleur."
    ),
    IrregularItem(
        base = "bien  (well — adverb)",
        comparative = "mieux",
        meaning = "better (adverb)",
        example = "Je comprends mieux maintenant.",
        translation = "I understand better now."
    ),
    IrregularItem(
        base = "mauvais(e)  (bad — adjective)",
        comparative = "pire  or  plus mauvais(e)",
        meaning = "worse",
        example = "C'est pire que je pensais.",
        translation = "It's worse than I thought."
    )
)

private val superlatives = listOf(
    SuperlativeItem(
        formula = "le / la / les plus + adj",
        meaning = "the most … (the -est)",
        example = "C'est le plus grand bâtiment de la ville.",
        translation = "It's the tallest building in the city."
    ),
    SuperlativeItem(
        formula = "le / la / les moins + adj",
        meaning = "the least …",
        example = "C'est le moins cher.",
        translation = "It's the least expensive."
    ),
    SuperlativeItem(
        formula = "le meilleur / la meilleure / les meilleur(e)s",
        meaning = "the best",
        example = "C'est le meilleur vin de la région.",
        translation = "It's the best wine in the region."
    ),
    SuperlativeItem(
        formula = "le pire / la pire / les pires",
        meaning = "the worst",
        example = "C'est le pire film de l'année.",
        translation = "It's the worst film of the year."
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComparisonsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Comparisons", color = Color.White) },
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
            item {
                Text(
                    text = "French uses specific structures to compare two things or to express the extreme degree of a quality.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // Section 1: Comparing two things
            item {
                CompSectionHeader("Comparing Two Things")
            }
            items(comparisons.size) { i -> ComparisonCard(comparisons[i]) }

            // Section 2: The same
            item {
                Spacer(modifier = Modifier.height(12.dp))
                CompSectionHeader("The Same")
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "la même chose",
                            fontWeight = FontWeight.Bold,
                            color = FrenchBlue,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "the same thing",
                            style = MaterialTheme.typography.bodySmall,
                            color = FrenchNavy
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Note: chose (thing) is feminine → la même chose. The accent on même is required.",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchNavy
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        HorizontalDivider(color = FrenchBlue.copy(alpha = 0.3f))
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "le même / la même / les mêmes + noun",
                            fontWeight = FontWeight.SemiBold,
                            color = FrenchBlue,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        SameExample("On mange dans le même restaurant.", "We eat at the same restaurant.")
                        SameExample("C'est la même idée.", "It's the same idea.")
                        SameExample("Ce sont les mêmes règles.", "These are the same rules.")
                        Spacer(modifier = Modifier.height(10.dp))
                        HorizontalDivider(color = FrenchBlue.copy(alpha = 0.3f))
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "To say two things are identical, you can also use:",
                            style = MaterialTheme.typography.bodySmall,
                            color = FrenchNavy
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "C'est pareil.  —  It's the same. / It's the same thing.",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                        Text(
                            text = "C'est identique.  —  It's identical.",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                    }
                }
            }

            // Section 3: Irregular comparatives
            item {
                Spacer(modifier = Modifier.height(12.dp))
                CompSectionHeader("Irregular Comparatives: Better / Worse")
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(
                            text = "Some adjectives and adverbs have irregular comparative forms instead of using plus or moins.",
                            style = MaterialTheme.typography.bodySmall,
                            color = FrenchNavy
                        )
                    }
                }
            }
            items(irregulars.size) { i -> IrregularCard(irregulars[i]) }

            // Section 4: Superlatives
            item {
                Spacer(modifier = Modifier.height(12.dp))
                CompSectionHeader("Superlatives — The Most / The Best / The Worst")
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(
                            text = "Use le / la / les before plus or moins (or meilleur / pire) to express the extreme degree. Use de to say \"in\" a group or place.",
                            style = MaterialTheme.typography.bodySmall,
                            color = FrenchNavy
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "le plus grand pays d'Europe  —  the biggest country in Europe",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                    }
                }
            }
            items(superlatives.size) { i -> SuperlativeCard(superlatives[i]) }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun SameExample(french: String, english: String) {
    Text(
        text = french,
        style = MaterialTheme.typography.bodySmall,
        fontStyle = FontStyle.Italic,
        color = FrenchBlue
    )
    Text(
        text = english,
        style = MaterialTheme.typography.bodySmall,
        color = FrenchNavy,
        modifier = Modifier.padding(bottom = 4.dp)
    )
}

@Composable
private fun ComparisonCard(item: ComparisonItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)) {
            Text(
                text = item.formula,
                fontWeight = FontWeight.Bold,
                color = FrenchBlue,
                fontSize = 14.sp
            )
            Text(
                text = item.meaning,
                style = MaterialTheme.typography.bodySmall,
                color = FrenchNavy,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = item.example,
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = FrenchBlue
            )
            Text(
                text = item.translation,
                style = MaterialTheme.typography.bodySmall,
                color = FrenchNavy
            )
        }
    }
}

@Composable
private fun IrregularCard(item: IrregularItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)) {
            Text(
                text = item.base,
                style = MaterialTheme.typography.bodySmall,
                color = FrenchNavy
            )
            Text(
                text = "→  ${item.comparative}  (${item.meaning})",
                fontWeight = FontWeight.Bold,
                color = FrenchBlue,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.example,
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = FrenchBlue
            )
            Text(
                text = item.translation,
                style = MaterialTheme.typography.bodySmall,
                color = FrenchNavy
            )
            if (item.note != null) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.note,
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun SuperlativeCard(item: SuperlativeItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)) {
            Text(
                text = item.formula,
                fontWeight = FontWeight.Bold,
                color = FrenchBlue,
                fontSize = 14.sp
            )
            Text(
                text = item.meaning,
                style = MaterialTheme.typography.bodySmall,
                color = FrenchNavy,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = item.example,
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = FrenchBlue
            )
            Text(
                text = item.translation,
                style = MaterialTheme.typography.bodySmall,
                color = FrenchNavy
            )
        }
    }
}

@Composable
private fun CompSectionHeader(title: String) {
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
