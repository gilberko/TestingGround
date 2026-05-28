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

private data class IntroLine(val speaker: String, val fr: String, val en: String)

private val introLines = listOf(
    IntroLine("Thomas", "Bonjour ! Je m'appelle Thomas. Et vous ?", "Hello! My name is Thomas. And you?"),
    IntroLine("Claire", "Bonjour ! Moi, c'est Claire. Enchantée !", "Hello! I'm Claire. Nice to meet you!"),
    IntroLine("Thomas", "Enchanté ! D'où venez-vous, Claire ?", "Nice to meet you! Where are you from, Claire?"),
    IntroLine("Claire", "Je suis de Lyon. Et vous, Thomas, vous êtes d'ici ?", "I'm from Lyon. And you, Thomas, are you from here?"),
    IntroLine("Thomas", "Oui, je suis parisien. Est-ce que vous parlez anglais ?", "Yes, I'm Parisian. Do you speak English?"),
    IntroLine("Claire", "Un peu, mais je préfère le français. Vous parlez très bien français, d'ailleurs !", "A little, but I prefer French. You speak French very well, by the way!"),
    IntroLine("Thomas", "Merci ! J'essaie. Vous êtes touriste à Paris ?", "Thank you! I try. Are you a tourist in Paris?"),
    IntroLine("Claire", "Non, je travaille ici. Je suis professeure dans un lycée. Et vous, qu'est-ce que vous faites ?", "No, I work here. I'm a high school teacher. And you, what do you do?"),
    IntroLine("Thomas", "Je travaille dans une banque. Mon bureau est juste là, rue de Rivoli.", "I work in a bank. My office is just there, on Rue de Rivoli."),
    IntroLine("Claire", "Quelle coïncidence ! C'est un très beau quartier.", "What a coincidence! It's a very beautiful neighbourhood."),
    IntroLine("Thomas", "Oui, j'adore. Bon, je dois y aller. Bonne journée, Claire !", "Yes, I love it. Well, I must go. Have a nice day, Claire!"),
    IntroLine("Claire", "À vous aussi, Thomas ! Au revoir !", "You too, Thomas! Goodbye!")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleIntroductionScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Simple Introduction", color = Color.White) },
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
            item { IntroSectionHeader("En français") }
            items(introLines.size) { i ->
                IntroCard(line = introLines[i], showFrench = true)
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { IntroSectionHeader("English Translation") }
            items(introLines.size) { i ->
                IntroCard(line = introLines[i], showFrench = false)
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun IntroSectionHeader(title: String) {
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
private fun IntroCard(line: IntroLine, showFrench: Boolean) {
    val isThomas = line.speaker == "Thomas"
    val containerColor = if (isThomas)
        MaterialTheme.colorScheme.secondaryContainer
    else
        MaterialTheme.colorScheme.tertiaryContainer
    val onContainerColor = if (isThomas)
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
