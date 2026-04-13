package com.example.frenchproject.screens.dictionary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.frenchproject.ui.theme.FrenchBlue
import com.example.frenchproject.ui.theme.FrenchNavy

private data class NatureWord(val french: String, val english: String)
private data class NatureSection(val title: String, val words: List<NatureWord>)

private val natureSections = listOf(
    NatureSection(
        title = "Plants & Trees",
        words = listOf(
            NatureWord("la feuille", "leaf"),
            NatureWord("l'arbre (m)", "tree"),
            NatureWord("la fleur", "flower"),
            NatureWord("l'herbe (f)", "grass"),
            NatureWord("le buisson", "bush"),
            NatureWord("la vigne", "vine"),
            NatureWord("le vignoble", "vineyard"),
            NatureWord("la pomme", "apple"),
            NatureWord("le fruit", "fruit"),
            NatureWord("la paille", "straw"),
            NatureWord("le coton", "cotton"),
            NatureWord("le nénuphar", "water lily"),
            NatureWord("les algues (f pl)", "seaweed"),
            NatureWord("l'orchidée (f)", "orchid"),
            NatureWord("l'herbe à puce (f)", "poison ivy"),
            NatureWord("la menthe", "mint"),
            NatureWord("le chêne", "oak"),
            NatureWord("l'orme (m)", "elm")
        )
    ),
    NatureSection(
        title = "Landscape & Terrain",
        words = listOf(
            NatureWord("la montagne", "mountain"),
            NatureWord("la colline", "hill"),
            NatureWord("la dune", "dune"),
            NatureWord("la forêt", "forest"),
            NatureWord("le champ", "field"),
            NatureWord("le lac", "lake"),
            NatureWord("l'étang (m)", "pond"),
            NatureWord("la plage", "beach"),
            NatureWord("le sable", "sand"),
            NatureWord("la terre", "dirt / soil / earth"),
            NatureWord("la boue", "mud"),
            NatureWord("la pierre", "stone"),
            NatureWord("le rocher", "rock")
        )
    ),
    NatureSection(
        title = "Water & Sky",
        words = listOf(
            NatureWord("l'océan (m)", "ocean"),
            NatureWord("la mer", "sea"),
            NatureWord("le vent", "wind"),
            NatureWord("l'arc-en-ciel (m)", "rainbow"),
            NatureWord("la neige", "snow"),
            NatureWord("la pluie", "rain"),
            NatureWord("le soleil", "sun"),
            NatureWord("le nuage", "cloud")
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NatureScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nature", color = Color.White) },
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
                    text = "French nouns have grammatical gender. The article (le, la, l', les) tells you the gender. Abbreviations: (m) = masculine, (f) = feminine, (pl) = plural.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            natureSections.forEach { section ->
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = section.title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = FrenchBlue,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    HorizontalDivider(color = FrenchBlue.copy(alpha = 0.4f))
                    Spacer(modifier = Modifier.height(4.dp))
                }
                items(section.words) { word -> NatureWordCard(word) }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun NatureWordCard(word: NatureWord) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 9.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = word.french,
                fontWeight = FontWeight.Bold,
                color = FrenchBlue,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = word.english,
                style = MaterialTheme.typography.bodyMedium,
                color = FrenchNavy,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
