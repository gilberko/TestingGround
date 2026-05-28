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

private data class RestaurantLine(val speaker: String, val fr: String, val en: String)

private val restaurantLines = listOf(
    RestaurantLine("Marc", "Sophie ! Ça fait si longtemps ! Comment vas-tu ?", "Sophie! It's been so long! How are you?"),
    RestaurantLine("Sophie", "Marc ! Je vais très bien, merci ! Et toi ?", "Marc! I'm very well, thank you! And you?"),
    RestaurantLine("Marc", "Super ! J'ai réservé une table pour deux. Allons-y.", "Great! I reserved a table for two. Let's go."),
    RestaurantLine("Serveur", "Bonsoir, messieurs-dames. Voici le menu. Vous avez des questions ?", "Good evening. Here is the menu. Do you have any questions?"),
    RestaurantLine("Sophie", "Oui. Y a-t-il des plats sans gluten ? J'ai la maladie cœliaque.", "Yes. Are there any gluten-free dishes? I have celiac disease."),
    RestaurantLine("Serveur", "Bien sûr. Je vous recommande le poulet rôti aux herbes et la salade niçoise — tous les deux sont naturellement sans gluten.", "Of course. I recommend the herb-roasted chicken and the niçoise salad — both are naturally gluten-free."),
    RestaurantLine("Marc", "Et moi, je préfère éviter le lactose autant que possible.", "And I prefer to avoid lactose as much as possible."),
    RestaurantLine("Serveur", "Pas de problème. Le bœuf bourguignon et le saumon grillé aux légumes ne contiennent pas de produits laitiers.", "No problem. The bœuf bourguignon and the grilled salmon with vegetables contain no dairy products."),
    RestaurantLine("Sophie", "Parfait ! Pour moi, le poulet rôti, s'il vous plaît.", "Perfect! For me, the herb-roasted chicken, please."),
    RestaurantLine("Marc", "Et pour moi, le saumon grillé. Merci.", "And for me, the grilled salmon. Thank you."),
    RestaurantLine("Serveur", "Très bien. Et comme boisson ?", "Very good. And to drink?"),
    RestaurantLine("Sophie", "Une carafe d'eau, s'il vous plaît. Et une bouteille de vin rouge ?", "A carafe of water, please. And a bottle of red wine?"),
    RestaurantLine("Marc", "Oui, pourquoi pas ! Un verre de vin entre vieux amis...", "Yes, why not! A glass of wine between old friends..."),
    RestaurantLine("Serveur", "Excellent choix. Je reviens dans un moment. Bon appétit !", "Excellent choice. I'll be back in a moment. Enjoy your meal!")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AtTheRestaurantScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("At The Restaurant", color = Color.White) },
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
            item { RestaurantSectionHeader("En français") }
            items(restaurantLines.size) { i ->
                RestaurantCard(line = restaurantLines[i], showFrench = true)
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { RestaurantSectionHeader("English Translation") }
            items(restaurantLines.size) { i ->
                RestaurantCard(line = restaurantLines[i], showFrench = false)
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun RestaurantSectionHeader(title: String) {
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
private fun RestaurantCard(line: RestaurantLine, showFrench: Boolean) {
    val containerColor = when (line.speaker) {
        "Marc" -> MaterialTheme.colorScheme.secondaryContainer
        "Sophie" -> MaterialTheme.colorScheme.surfaceVariant
        else -> MaterialTheme.colorScheme.tertiaryContainer
    }
    val onContainerColor = when (line.speaker) {
        "Marc" -> MaterialTheme.colorScheme.onSecondaryContainer
        "Sophie" -> MaterialTheme.colorScheme.onSurfaceVariant
        else -> MaterialTheme.colorScheme.onTertiaryContainer
    }

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
                modifier = Modifier.width(60.dp)
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
