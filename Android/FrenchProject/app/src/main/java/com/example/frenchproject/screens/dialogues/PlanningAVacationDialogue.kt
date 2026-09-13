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

private data class VacationLine(val speaker: String, val fr: String, val en: String)

private val vacationLines = listOf(
    VacationLine("Papa", "On devrait commencer à planifier notre voyage au Japon. On part combien de temps au total ?", "We should start planning our trip to Japan. How long are we going for in total?"),
    VacationLine("Maman", "J'ai pensé à un itinéraire. On commence par quatre jours à Osaka, avec Universal Studios comme attraction principale.", "I thought of an itinerary. We start with four days in Osaka, with Universal Studios as the main attraction."),
    VacationLine("Papa", "Bonne idée, les enfants vont adorer. Et après Osaka ?", "Good idea, the kids will love it. And after Osaka?"),
    VacationLine("Maman", "Ensuite, quatre jours à Kyoto. On pourrait aussi faire une excursion d'une journée à Nara pendant qu'on est dans la région.", "Then, four days in Kyoto. We could also do a day trip to Nara while we're in the area."),
    VacationLine("Papa", "Oui, pour voir les cerfs. Et je voulais absolument passer une journée au parc Ghibli.", "Yes, to see the deer. And I really wanted to spend a day at Ghibli Park."),
    VacationLine("Maman", "D'accord, on ajoute une journée pour le parc Ghibli. Et une journée à Hakone aussi, pour voir le mont Fuji.", "Okay, let's add a day for Ghibli Park. And a day in Hakone too, to see Mount Fuji."),
    VacationLine("Papa", "Et on termine par Tokyo ?", "And we finish in Tokyo?"),
    VacationLine("Maman", "Oui, neuf jours à Tokyo pour finir le voyage, il y a tellement de choses à voir là-bas.", "Yes, nine days in Tokyo to end the trip, there's so much to see there."),
    VacationLine("Papa", "Parfait. Il faut qu'on achète les billets d'avion bientôt, les prix augmentent vite.", "Perfect. We need to buy the plane tickets soon, prices go up fast."),
    VacationLine("Maman", "Je m'en occupe cette semaine. Il faudra aussi réserver les hôtels pour chaque ville.", "I'll take care of that this week. We'll also need to book hotels for each city."),
    VacationLine("Papa", "Et les billets de train, non ? Pour aller d'Osaka à Kyoto, puis jusqu'à Tokyo.", "And the train tickets, right? To go from Osaka to Kyoto, and then to Tokyo."),
    VacationLine("Maman", "Oui, on réservera les billets de train à l'avance. Par contre, pour Hakone et le parc Ghibli, je pense qu'on devrait louer une voiture.", "Yes, we'll book the train tickets in advance. But for Hakone and Ghibli Park, I think we should rent a car."),
    VacationLine("Papa", "Bonne idée, ces deux endroits sont moins pratiques en transports en commun.", "Good idea, those two places are less convenient by public transport."),
    VacationLine("Maman", "Exactement. On garde le train pour le reste du voyage et la voiture juste pour ces deux jours-là.", "Exactly. We'll keep the train for the rest of the trip and the car just for those two days.")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanningAVacationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Planning A Vacation", color = Color.White) },
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
            item { VacationSectionHeader("En français") }
            items(vacationLines.size) { i ->
                VacationCard(line = vacationLines[i], showFrench = true)
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { VacationSectionHeader("English Translation") }
            items(vacationLines.size) { i ->
                VacationCard(line = vacationLines[i], showFrench = false)
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun VacationSectionHeader(title: String) {
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
private fun VacationCard(line: VacationLine, showFrench: Boolean) {
    val isPapa = line.speaker == "Papa"
    val containerColor = if (isPapa)
        MaterialTheme.colorScheme.secondaryContainer
    else
        MaterialTheme.colorScheme.tertiaryContainer
    val onContainerColor = if (isPapa)
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
                modifier = Modifier.width(68.dp)
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
