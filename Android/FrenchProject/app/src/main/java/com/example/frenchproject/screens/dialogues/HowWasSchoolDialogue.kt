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

private data class SchoolLine(val speaker: String, val fr: String, val en: String)

private val schoolLines = listOf(
    SchoolLine("Papa", "Alors, Chloé, comment s'est passée l'école aujourd'hui ?", "So, Chloé, how was school today?"),
    SchoolLine("Chloé", "Super, papa ! D'abord on a eu maths — c'était les additions !", "Great, dad! First we had maths — it was additions!"),
    SchoolLine("Papa", "Les additions ! Et tu as bien compris ?", "Additions! And did you understand it well?"),
    SchoolLine("Chloé", "Oui ! La maîtresse dit que je suis très bonne en maths.", "Yes! The teacher says I'm very good at maths."),
    SchoolLine("Papa", "Je suis trop fier de toi ! Vous avez eu quelles autres matières ?", "I'm so proud of you! What other subjects did you have?"),
    SchoolLine("Chloé", "On a eu français, dessin et gym. J'adore la gym !", "We had French, drawing and PE. I love PE!"),
    SchoolLine("Papa", "Et pendant la récréation, tu as fait quoi ?", "And during break, what did you do?"),
    SchoolLine("Chloé", "J'ai joué à la marelle avec Léa et Emma. Et après on a mangé nos goûters.", "I played hopscotch with Léa and Emma. And then we ate our snacks."),
    SchoolLine("Papa", "Et à la cantine, c'était bon ?", "And at lunch, was it good?"),
    SchoolLine("Chloé", "Oui ! On a eu du poulet avec des frites. C'était trop bon !", "Yes! We had chicken with chips. It was so good!"),
    SchoolLine("Papa", "La chance ! Tu as autre chose à me raconter ?", "Lucky! Is there anything else to tell me?"),
    SchoolLine("Chloé", "Oui ! La maîtresse a dit qu'on va avoir une sortie scolaire la semaine prochaine !", "Yes! The teacher said we're going to have a school trip next week!"),
    SchoolLine("Papa", "Vraiment ? Vous allez où ?", "Really? Where are you going?"),
    SchoolLine("Chloé", "Au musée des sciences ! On va voir des dinosaures et des expériences scientifiques !", "To the science museum! We're going to see dinosaurs and science experiments!"),
    SchoolLine("Papa", "C'est super ça ! Est-ce que tu as besoin d'une autorisation signée ?", "That's great! Do you need a signed permission slip?"),
    SchoolLine("Chloé", "Oui, j'ai le papier dans mon cartable. Tu peux le signer ce soir, s'il te plaît ?", "Yes, I have the paper in my school bag. Can you sign it tonight, please?"),
    SchoolLine("Papa", "Bien sûr ! Et tu es contente d'y aller ?", "Of course! And are you looking forward to it?"),
    SchoolLine("Chloé", "Oh oui, trop contente ! Léa dit que c'est trop bien comme musée. Allez, on mange ?", "Oh yes, so excited! Léa says it's such a great museum. Come on, can we eat?")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HowWasSchoolScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("How Was School Today", color = Color.White) },
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
            item { SchoolSectionHeader("En français") }
            items(schoolLines.size) { i ->
                SchoolCard(line = schoolLines[i], showFrench = true)
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { SchoolSectionHeader("English Translation") }
            items(schoolLines.size) { i ->
                SchoolCard(line = schoolLines[i], showFrench = false)
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun SchoolSectionHeader(title: String) {
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
private fun SchoolCard(line: SchoolLine, showFrench: Boolean) {
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
                modifier = Modifier.width(52.dp)
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
