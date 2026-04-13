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

private data class PossAdjectiveRow(
    val owner: String,
    val masculine: String,
    val feminine: String,
    val plural: String
)

private data class PossPronounRow(
    val owner: String,
    val mascSing: String,
    val femSing: String,
    val mascPl: String,
    val femPl: String
)

private val adjectiveRows = listOf(
    PossAdjectiveRow("my", "mon", "ma  (→ mon)", "mes"),
    PossAdjectiveRow("your (tu)", "ton", "ta  (→ ton)", "tes"),
    PossAdjectiveRow("his / her / its", "son", "sa  (→ son)", "ses"),
    PossAdjectiveRow("our", "notre", "notre", "nos"),
    PossAdjectiveRow("your (vous)", "votre", "votre", "vos"),
    PossAdjectiveRow("their", "leur", "leur", "leurs")
)

private val pronounRows = listOf(
    PossPronounRow("mine", "le mien", "la mienne", "les miens", "les miennes"),
    PossPronounRow("yours (tu)", "le tien", "la tienne", "les tiens", "les tiennes"),
    PossPronounRow("his / hers / its", "le sien", "la sienne", "les siens", "les siennes"),
    PossPronounRow("ours", "le nôtre", "la nôtre", "les nôtres", "les nôtres"),
    PossPronounRow("yours (vous)", "le vôtre", "la vôtre", "les vôtres", "les vôtres"),
    PossPronounRow("theirs", "le leur", "la leur", "les leurs", "les leurs")
)

private val adjectiveExamples = listOf(
    "C'est mon livre." to "That's my book.",
    "C'est ma voiture." to "That's my car.",
    "Ce sont mes amis." to "These are my friends.",
    "C'est son idée." to "It's his idea. / It's her idea. (context tells you which)",
    "J'ai oublié mon amie." to "I forgot my friend. (mon before vowel, even though amie is feminine)",
    "C'est notre maison." to "It's our house.",
    "Leur chien est gentil." to "Their dog is nice."
)

private val pronounExamples = listOf(
    "C'est ton livre — où est le mien ?" to "That's your book — where is mine?",
    "Sa voiture est rapide, mais la mienne est plus confortable." to "His car is fast, but mine is more comfortable.",
    "Nos enfants jouent avec les leurs." to "Our children play with theirs.",
    "J'ai perdu mon stylo. Tu peux me prêter le tien ?" to "I lost my pen. Can you lend me yours?"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PossessiveScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Possessive", color = Color.White) },
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
            // Section 1: Possessive Adjectives
            item {
                Text(
                    text = "French has two kinds of possessives: adjectives (mon, ma, mes…) that come before a noun, and pronouns (le mien, la mienne…) that replace the noun entirely.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                PossSectionHeader("Possessive Adjectives")
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Agree with the NOUN they describe — not with the owner.",
                            fontWeight = FontWeight.SemiBold,
                            color = FrenchNavy,
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Special rule: before a feminine noun that starts with a vowel or silent h, use the masculine form — mon, ton, son — to avoid an awkward vowel clash.",
                            style = MaterialTheme.typography.bodySmall,
                            color = FrenchNavy
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "mon amie ✓   (not ma amie)   •   ton école ✓   •   son horloge ✓",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                    }
                }
            }

            // Table header
            item {
                Spacer(modifier = Modifier.height(6.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = FrenchBlue),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)) {
                        Text("Owner", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.width(100.dp))
                        Text("Masc.", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.weight(1f))
                        Text("Fem.", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.weight(1f))
                        Text("Plural", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.weight(1f))
                    }
                }
            }
            items(adjectiveRows.size) { i -> PossAdjectiveRowCard(adjectiveRows[i], i % 2 == 0) }

            item {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Examples",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = FrenchNavy,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
            items(adjectiveExamples.size) { i -> PossExampleCard(adjectiveExamples[i]) }

            // Section 2: Possessive Pronouns
            item {
                Spacer(modifier = Modifier.height(16.dp))
                PossSectionHeader("Possessive Pronouns")
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Replace the entire \"possessive adjective + noun\" phrase. They agree in gender and number with the thing being possessed.",
                            style = MaterialTheme.typography.bodySmall,
                            color = FrenchNavy
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "\"C'est mon livre.\"  →  \"C'est le mien.\"",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                    }
                }
            }

            // Pronoun table header
            item {
                Spacer(modifier = Modifier.height(6.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = FrenchBlue),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)) {
                        Text("Owner", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 11.sp, modifier = Modifier.width(80.dp))
                        Text("M. sing.", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 11.sp, modifier = Modifier.weight(1f))
                        Text("F. sing.", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 11.sp, modifier = Modifier.weight(1f))
                        Text("M. pl.", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 11.sp, modifier = Modifier.weight(1f))
                        Text("F. pl.", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 11.sp, modifier = Modifier.weight(1f))
                    }
                }
            }
            items(pronounRows.size) { i -> PossPronounRowCard(pronounRows[i], i % 2 == 0) }

            item {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Examples",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = FrenchNavy,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
            items(pronounExamples.size) { i -> PossExampleCard(pronounExamples[i]) }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun PossAdjectiveRowCard(row: PossAdjectiveRow, isEven: Boolean) {
    val bg = if (isEven) Color.White else Color(0xFFF0F4FF)
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = bg),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp)) {
            Text(row.owner, fontSize = 12.sp, color = FrenchNavy, fontWeight = FontWeight.SemiBold, modifier = Modifier.width(100.dp))
            Text(row.masculine, fontSize = 13.sp, color = FrenchBlue, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            Text(row.feminine, fontSize = 12.sp, color = FrenchBlue, modifier = Modifier.weight(1f))
            Text(row.plural, fontSize = 13.sp, color = FrenchBlue, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun PossPronounRowCard(row: PossPronounRow, isEven: Boolean) {
    val bg = if (isEven) Color.White else Color(0xFFF0F4FF)
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = bg),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp)) {
            Text(row.owner, fontSize = 11.sp, color = FrenchNavy, fontWeight = FontWeight.SemiBold, modifier = Modifier.width(80.dp))
            Text(row.mascSing, fontSize = 11.sp, color = FrenchBlue, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            Text(row.femSing, fontSize = 11.sp, color = FrenchBlue, modifier = Modifier.weight(1f))
            Text(row.mascPl, fontSize = 11.sp, color = FrenchBlue, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            Text(row.femPl, fontSize = 11.sp, color = FrenchBlue, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun PossExampleCard(example: Pair<String, String>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 9.dp)) {
            Text(
                text = example.first,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = FrenchBlue,
                fontSize = 14.sp
            )
            Text(
                text = example.second,
                style = MaterialTheme.typography.bodySmall,
                color = FrenchNavy
            )
        }
    }
}

@Composable
private fun PossSectionHeader(title: String) {
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
