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

private data class GarageLine(val speaker: String, val fr: String, val en: String)

private val garageLines = listOf(
    GarageLine("Client", "Bonjour, j'aimerais faire réviser ma voiture. C'est une hybride.", "Hello, I'd like to have my car checked. It's a hybrid."),
    GarageLine("Mécanicien", "Bien sûr. Autre chose à signaler, ou seulement la révision générale ?", "Of course. Anything else to report, or just the general check-up?"),
    GarageLine("Client", "Oui, en fait. La climatisation ne souffle du froid que d'un seul côté. De l'autre côté, l'air est à température ambiante.", "Yes, actually. The AC only blows cold air on one side. On the other side, the air is at room temperature."),
    GarageLine("Mécanicien", "D'accord, on va vérifier ça avec l'électricien. Laissez-moi la voiture une heure.", "Okay, we'll check that with the electrician. Leave the car with me for an hour."),
    GarageLine("Mécanicien", "Voilà, j'ai regardé avec l'électricien. Il manque du gaz réfrigérant dans le circuit de climatisation, c'est pour ça qu'un côté ne refroidit plus correctement.", "There we go, I checked it with the electrician. There's not enough refrigerant gas in the AC circuit, that's why one side isn't cooling properly anymore."),
    GarageLine("Client", "D'accord. Vous pouvez recharger la climatisation ?", "Okay. Can you refill the AC?"),
    GarageLine("Mécanicien", "Oui, sans problème. On a aussi remarqué qu'une ampoule est grillée à l'arrière, il faudra la remplacer.", "Yes, no problem. We also noticed a lightbulb is burnt out at the back, it will need to be replaced."),
    GarageLine("Client", "Pas de souci, faites le nécessaire.", "No problem, do what's needed."),
    GarageLine("Mécanicien", "Et une dernière chose : le pneu avant droit est bien usé. Je vous recommande de changer les deux pneus avant ensemble, pas seulement celui-là.", "And one last thing: the front right tire is quite worn. I'd recommend changing both front tires together, not just that one."),
    GarageLine("Client", "Pourquoi les deux et pas juste le pneu abîmé ?", "Why both and not just the damaged one?"),
    GarageLine("Mécanicien", "Pour garder une usure homogène et une bonne adhérence des deux côtés. C'est plus sûr, surtout pour le freinage.", "To keep even wear and good grip on both sides. It's safer, especially for braking."),
    GarageLine("Client", "D'accord, faites tout : le gaz de la clim, l'ampoule, et les deux pneus avant.", "Alright, do it all: the AC gas, the lightbulb, and the two front tires."),
    GarageLine("Mécanicien", "Parfait, ce sera prêt cet après-midi.", "Perfect, it will be ready this afternoon.")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AtTheGarageScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("At The Garage", color = Color.White) },
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
            item { GarageSectionHeader("En français") }
            items(garageLines.size) { i ->
                GarageCard(line = garageLines[i], showFrench = true)
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { GarageSectionHeader("English Translation") }
            items(garageLines.size) { i ->
                GarageCard(line = garageLines[i], showFrench = false)
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun GarageSectionHeader(title: String) {
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
private fun GarageCard(line: GarageLine, showFrench: Boolean) {
    val isClient = line.speaker == "Client"
    val containerColor = if (isClient)
        MaterialTheme.colorScheme.secondaryContainer
    else
        MaterialTheme.colorScheme.tertiaryContainer
    val onContainerColor = if (isClient)
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
                modifier = Modifier.width(78.dp)
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
