package com.example.frenchproject.screens.dictionary

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

private data class TrafficWord(val french: String, val english: String)
private data class TrafficSection(val title: String, val words: List<TrafficWord>)

private val trafficSections = listOf(
    TrafficSection(
        title = "Directions",
        words = listOf(
            TrafficWord("le nord", "north"),
            TrafficWord("le sud", "south"),
            TrafficWord("l'est (m)", "east"),
            TrafficWord("l'ouest (m)", "west"),
            TrafficWord("en haut", "up"),
            TrafficWord("en bas", "down"),
            TrafficWord("à gauche", "left"),
            TrafficWord("à droite", "right"),
            TrafficWord("tourner à gauche", "turn left"),
            TrafficWord("tourner à droite", "turn right"),
            TrafficWord("faire demi-tour", "turn around / turn back"),
            TrafficWord("continuer tout droit", "continue straight"),
            TrafficWord("en dessous", "below"),
            TrafficWord("au-dessus", "above"),
            TrafficWord("monter", "to go up"),
            TrafficWord("descendre", "to go down"),
            TrafficWord("prendre la première sortie", "take the first exit"),
            TrafficWord("prendre la deuxième sortie", "take the second exit")
        )
    ),
    TrafficSection(
        title = "Traffic & Road Signs",
        words = listOf(
            TrafficWord("les embouteillages (m pl) / un bouchon", "traffic jam"),
            TrafficWord("le feu (tricolore)", "traffic light"),
            TrafficWord("la limite de vitesse", "speed limit"),
            TrafficWord("attention à la limite de vitesse", "beware the speed limit"),
            TrafficWord("les panneaux de signalisation (m pl)", "road signs"),
            TrafficWord("le panneau stop", "stop sign"),
            TrafficWord("le panneau de vitesse maximale", "maximum speed sign"),
            TrafficWord("un barrage routier", "road block"),
            TrafficWord("la route est bloquée", "the road is blocked"),
            TrafficWord("la voie", "lane"),
            TrafficWord("la voie de droite", "right lane"),
            TrafficWord("la voie de gauche", "left lane")
        )
    ),
    TrafficSection(
        title = "Geography & Places",
        words = listOf(
            TrafficWord("la place", "square")
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrafficDirectionsGeographyScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Traffic, Directions & Geography", color = Color.White) },
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
                    text = "Vocabulary for giving directions, talking about traffic, and describing geography. Abbreviations: (m) = masculine, (f) = feminine, (pl) = plural.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            trafficSections.forEach { section ->
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
                items(section.words) { word -> TrafficWordCard(word) }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun TrafficWordCard(word: TrafficWord) {
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
