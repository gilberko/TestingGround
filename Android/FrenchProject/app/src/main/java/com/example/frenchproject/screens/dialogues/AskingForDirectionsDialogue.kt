package com.example.frenchproject.screens.dialogues

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frenchproject.ui.theme.FrenchBlue
import com.example.frenchproject.ui.theme.FrenchNavy

private data class DirectionsDLine(val speaker: String, val fr: String, val en: String)

private val directionsLines = listOf(
    DirectionsDLine("Touriste", "Excusez-moi, monsieur. Pourriez-vous m'aider ? Je cherche le stade.", "Excuse me, sir. Could you help me? I'm looking for the stadium."),
    DirectionsDLine("Passant", "Bien sûr ! Vous pouvez y aller à pied ou prendre le métro.", "Of course! You can get there on foot or take the metro."),
    DirectionsDLine("Touriste", "À pied, c'est loin ?", "Is it far on foot?"),
    DirectionsDLine("Passant", "C'est à presque une heure à pied. Si vous voulez quand même, voilà la route : allez tout droit dans cette rue, tournez à gauche au feu rouge, continuez jusqu'à la grande avenue et tournez à droite. Le stade est au bout.", "It's almost an hour on foot. If you still want to, here's the route: go straight along this street, turn left at the traffic light, continue to the main avenue and turn right. The stadium is at the end."),
    DirectionsDLine("Touriste", "Et en métro, c'est plus rapide ?", "And by metro, is it faster?"),
    DirectionsDLine("Passant", "Oui, beaucoup plus rapide. Prenez la ligne 5 direction nord. Descendez à la station « Stade de France ». Le stade est à deux minutes à pied de là.", "Yes, much faster. Take line 5 heading north. Get off at the 'Stade de France' station. The stadium is a two-minute walk from there."),
    DirectionsDLine("Touriste", "Où est-ce que je peux acheter un ticket de métro ?", "Where can I buy a metro ticket?"),
    DirectionsDLine("Passant", "Il y a un guichet dans chaque station, ou vous pouvez acheter un carnet de dix tickets. Vous pouvez aussi utiliser une carte rechargeable comme le Navigo.", "There's a ticket counter in each station, or you can buy a book of ten tickets. You can also use a rechargeable card like the Navigo."),
    DirectionsDLine("Touriste", "Et combien ça coûte ?", "And how much does it cost?"),
    DirectionsDLine("Passant", "Un ticket simple coûte deux euros dix. Ou utilisez l'application « Île-de-France Mobilités » pour payer avec votre téléphone — même prix, mais plus pratique.", "A single ticket costs 2 euros 10. Or use the 'Île-de-France Mobilités' app to pay with your phone — same price, but more convenient."),
    DirectionsDLine("Touriste", "Super, merci mille fois !", "Great, thank you so much!"),
    DirectionsDLine("Passant", "De rien ! Bonne chance et profitez bien du match !", "You're welcome! Good luck and enjoy the match!")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AskingForDirectionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Asking For Directions", color = Color.White) },
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
            item { DirectionsSectionHeader("En français") }
            items(directionsLines.size) { i ->
                DirectionsCard(line = directionsLines[i], showFrench = true)
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { DirectionsSectionHeader("English Translation") }
            items(directionsLines.size) { i ->
                DirectionsCard(line = directionsLines[i], showFrench = false)
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun DirectionsSectionHeader(title: String) {
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
private fun DirectionsCard(line: DirectionsDLine, showFrench: Boolean) {
    val isTourist = line.speaker == "Touriste"
    val containerColor = if (isTourist)
        MaterialTheme.colorScheme.secondaryContainer
    else
        MaterialTheme.colorScheme.tertiaryContainer
    val onContainerColor = if (isTourist)
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
