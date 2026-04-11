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

private data class DemAdjRow(
    val form: String,
    val usedWhen: String,
    val example: String
)

private val demAdjRows = listOf(
    DemAdjRow("ce", "masc. singular before a consonant", "ce livre (this/that book)"),
    DemAdjRow("cet", "masc. singular before a vowel or h", "cet homme, cet arbre"),
    DemAdjRow("cette", "feminine singular", "cette femme (this/that woman)"),
    DemAdjRow("ces", "plural — both genders", "ces livres, ces femmes")
)

private data class DemPronRow(
    val form: String,
    val gender: String,
    val withCi: String,
    val withLa: String
)

private val demPronRows = listOf(
    DemPronRow("celui", "masc. singular", "celui-ci (this one)", "celui-là (that one)"),
    DemPronRow("celle", "fem. singular", "celle-ci (this one)", "celle-là (that one)"),
    DemPronRow("ceux", "masc. plural", "ceux-ci (these)", "ceux-là (those)"),
    DemPronRow("celles", "fem. plural", "celles-ci (these)", "celles-là (those)")
)

private data class PlaceRow(val french: String, val english: String, val note: String? = null)

private val placeRows = listOf(
    PlaceRow("ici", "here", "Specific / formal. Points to a precise location."),
    PlaceRow("là", "there", "Also used informally for \"here\" — e.g. tu es là ? (are you there/here?)"),
    PlaceRow("là-bas", "over there", "Further away; emphasises distance.")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemonstrativesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("This and That, Here and There", color = Color.White) },
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
                    text = "French uses different words to say \"this\", \"that\", \"these\", and \"those\" depending on whether you are modifying a noun (demonstrative adjectives) or replacing it entirely (demonstrative pronouns). The suffixes -ci and -là then add a sense of nearness or distance.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // Section 1 — Demonstrative Adjectives
            item {
                Spacer(modifier = Modifier.height(8.dp))
                DemSectionHeader("Demonstrative Adjectives — ce, cet, cette, ces")
                Text(
                    text = "These come directly before a noun. French does not distinguish \"this\" from \"that\" by form alone — context makes it clear, or you add -ci / -là to the noun.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
            }
            items(demAdjRows.size) { i -> DemAdjCard(demAdjRows[i]) }

            // -ci / -là note on adjectives
            item {
                Spacer(modifier = Modifier.height(6.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Adding -ci and -là to distinguish near from far",
                            fontWeight = FontWeight.SemiBold,
                            color = FrenchNavy
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Attach -ci (near) or -là (far) to the noun with a hyphen:",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "ce livre-ci → this book (near me)\nce livre-là → that book (over there)",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "cette table-ci → this table\ncette table-là → that table",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                    }
                }
            }

            // Section 2 — Demonstrative Pronouns
            item {
                Spacer(modifier = Modifier.height(12.dp))
                DemSectionHeader("Demonstrative Pronouns — celui, celle, ceux, celles")
                Text(
                    text = "These stand alone — they replace a noun already mentioned. They must always be followed by -ci, -là, de, or a relative clause.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
            }
            items(demPronRows.size) { i -> DemPronCard(demPronRows[i]) }

            // Example card for pronouns
            item {
                Spacer(modifier = Modifier.height(6.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Examples in sentences",
                            fontWeight = FontWeight.SemiBold,
                            color = FrenchNavy
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Je préfère celui-ci. → I prefer this one. (masc.)",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                        Text(
                            text = "Celle-là est belle. → That one is beautiful. (fem.)",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                        Text(
                            text = "Ceux-ci sont moins chers. → These are cheaper. (masc. pl.)",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                        Text(
                            text = "Quelles chaussures ? Celles-là. → Which shoes? Those ones. (fem. pl.)",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                    }
                }
            }

            // Section 3 — ça / cela / ceci
            item {
                Spacer(modifier = Modifier.height(12.dp))
                DemSectionHeader("ça, cela, and ceci")
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EDF8)),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "These refer to ideas, situations, or things in a general way — not linked to a specific gender or noun.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        HorizontalDivider(color = FrenchBlue.copy(alpha = 0.3f))
                        Spacer(modifier = Modifier.height(10.dp))
                        ImpersonalRow("ça", "that / it (informal)", "Extremely common in speech. The everyday contraction of cela.")
                        Spacer(modifier = Modifier.height(8.dp))
                        ImpersonalRow("cela", "that (formal)", "Used in writing or careful speech. Ça is its informal equivalent.")
                        Spacer(modifier = Modifier.height(8.dp))
                        ImpersonalRow("ceci", "this (formal)", "Rarely heard in everyday conversation; mainly written French.")
                        Spacer(modifier = Modifier.height(10.dp))
                        HorizontalDivider(color = FrenchBlue.copy(alpha = 0.3f))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Ça m'est égal. → I don't mind / It's all the same to me.",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                        Text(
                            text = "Cela ne me concerne pas. → That is none of my business.",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                        Text(
                            text = "Lisez ceci attentivement. → Read this carefully.",
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic,
                            color = FrenchBlue
                        )
                    }
                }
            }

            // Section 4 — Here and There
            item {
                Spacer(modifier = Modifier.height(12.dp))
                DemSectionHeader("Here and There — ici and là")
                Text(
                    text = "The suffixes -ci and -là on demonstrative pronouns come directly from these two adverbs.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
            }
            items(placeRows.size) { i -> PlaceCard(placeRows[i]) }

            // Etymology note
            item {
                Spacer(modifier = Modifier.height(6.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Why -ci and -là?",
                            fontWeight = FontWeight.SemiBold,
                            color = FrenchNavy
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "The suffix -ci comes from ici (here) and signals proximity.\nThe suffix -là comes from là (there) and signals distance.\n\nSo celui-ci literally means \"that-one-here\" and celui-là means \"that-one-there\". This is why they translate as \"this one\" and \"that one\" respectively.",
                            style = MaterialTheme.typography.bodyMedium,
                            lineHeight = 22.sp
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun DemSectionHeader(title: String) {
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
private fun DemAdjCard(row: DemAdjRow) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Text(
                text = row.form,
                fontWeight = FontWeight.Bold,
                color = FrenchBlue,
                fontSize = 18.sp,
                modifier = Modifier.width(60.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(text = row.usedWhen, style = MaterialTheme.typography.bodyMedium, color = FrenchNavy)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = row.example,
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = FrenchBlue
                )
            }
        }
    }
}

@Composable
private fun DemPronCard(row: DemPronRow) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Row {
                Text(
                    text = row.form,
                    fontWeight = FontWeight.Bold,
                    color = FrenchBlue,
                    fontSize = 16.sp,
                    modifier = Modifier.width(80.dp)
                )
                Text(text = row.gender, style = MaterialTheme.typography.bodyMedium, color = FrenchNavy)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${row.withCi}  •  ${row.withLa}",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = FrenchBlue
            )
        }
    }
}

@Composable
private fun ImpersonalRow(french: String, english: String, note: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = french,
            fontWeight = FontWeight.Bold,
            color = FrenchBlue,
            fontSize = 15.sp,
            modifier = Modifier.width(55.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(text = english, style = MaterialTheme.typography.bodyMedium, color = FrenchNavy)
            Text(
                text = note,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun PlaceCard(row: PlaceRow) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Row {
                Text(
                    text = row.french,
                    fontWeight = FontWeight.Bold,
                    color = FrenchBlue,
                    fontSize = 16.sp,
                    modifier = Modifier.width(80.dp)
                )
                Text(text = row.english, style = MaterialTheme.typography.bodyMedium, color = FrenchNavy)
            }
            if (row.note != null) {
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = row.note,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
