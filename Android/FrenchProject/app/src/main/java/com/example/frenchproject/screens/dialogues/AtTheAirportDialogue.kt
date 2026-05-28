package com.example.frenchproject.screens.dialogues

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frenchproject.ui.theme.FrenchBlue

private data class AirportLine(val speaker: String, val fr: String, val en: String)

private val airportLines = listOf(
    AirportLine("Voyageur", "Excusez-moi, je cherche la porte B14. Pouvez-vous m'indiquer le chemin ?", "Excuse me, I'm looking for gate B14. Can you show me the way?"),
    AirportLine("Agent", "Bien sûr. Vous êtes dans le terminal B. Prenez l'escalator là-bas jusqu'au premier étage, puis suivez les panneaux « Portes B10 à B20 ».", "Of course. You're in terminal B. Take the escalator over there to the first floor, then follow the signs 'Gates B10 to B20'."),
    AirportLine("Voyageur", "C'est loin ?", "Is it far?"),
    AirportLine("Agent", "À environ cinq minutes à pied. Vous ne pouvez pas rater les panneaux.", "About five minutes on foot. You can't miss the signs."),
    AirportLine("Voyageur", "Merci. Est-ce qu'il y a quelque chose à manger dans cette zone ?", "Thank you. Is there anything to eat in this area?"),
    AirportLine("Agent", "Oui. Il y a un café juste avant les portes B10. Un peu plus loin dans le couloir, vous trouverez aussi une boulangerie et un restaurant.", "Yes. There's a café just before gates B10. A little further down the corridor, you'll also find a bakery and a restaurant."),
    AirportLine("Voyageur", "Mon vol part dans deux heures. J'ai le temps ?", "My flight leaves in two hours. Do I have time?"),
    AirportLine("Agent", "Largement ! Bonne continuation et bon voyage !", "Plenty! Enjoy and have a great flight!"),
    AirportLine("Voyageur", "Merci beaucoup !", "Thank you very much!")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AtTheAirportScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("At The Airport", color = Color.White) },
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
                .padding(horizontal = 12.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item { AirportSectionHeader("En français") }
            items(airportLines.size) { i ->
                AirportCard(line = airportLines[i], showFrench = true)
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { AirportSectionHeader("English Translation") }
            items(airportLines.size) { i ->
                AirportCard(line = airportLines[i], showFrench = false)
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun AirportSectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = FrenchBlue,
        modifier = Modifier.padding(vertical = 8.dp)
    )
    HorizontalDivider(color = FrenchBlue.copy(alpha = 0.4f))
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
private fun AirportCard(line: AirportLine, showFrench: Boolean) {
    val isVoyageur = line.speaker == "Voyageur"
    val containerColor = if (isVoyageur)
        MaterialTheme.colorScheme.secondaryContainer
    else
        MaterialTheme.colorScheme.tertiaryContainer
    val onContainerColor = if (isVoyageur)
        MaterialTheme.colorScheme.onSecondaryContainer
    else
        MaterialTheme.colorScheme.onTertiaryContainer

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = line.speaker,
                fontWeight = FontWeight.Bold,
                color = onContainerColor,
                fontSize = 12.sp,
                modifier = Modifier.width(72.dp)
            )
            Text(
                text = if (showFrench) line.fr else line.en,
                style = MaterialTheme.typography.bodySmall,
                color = onContainerColor,
                lineHeight = 18.sp,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
