package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class DialogueLine(
    val speaker: String,
    val pt: String,
    val en: String
)

private data class ConversationSection(
    val heading: String?,
    val lines: List<DialogueLine>
)

private val conversationSections = listOf(
    ConversationSection(
        heading = null,
        lines = listOf(
            DialogueLine(
                speaker = "A",
                pt = "Desculpe, pode dizer-me como chego ao Estádio da Luz?",
                en = "Excuse me, can you tell me how to get to Estádio da Luz?"
            ),
            DialogueLine(
                speaker = "B",
                pt = "Claro! Há várias maneiras de lá chegar. Posso explicar-lhe três opções.",
                en = "Of course! There are several ways to get there. I can explain three options to you."
            )
        )
    ),
    ConversationSection(
        heading = "By Bus",
        lines = listOf(
            DialogueLine(
                speaker = "B",
                pt = "A primeira opção é de autocarro. Ali mesmo ao fundo da rua há uma paragem. Apanhe o autocarro número cinquenta e três em direção a Colégio Militar. Viaja durante cerca de doze paragens até à paragem \"Estádio da Luz.\" Quando sair, o estádio fica a uns cinco minutos a pé, mesmo em frente.",
                en = "The first option is by bus. At the end of the street there's a bus stop. Take bus number 53 in the direction of Colégio Militar. Travel for about twelve stops until the \"Estádio da Luz\" stop. When you get off, the stadium is about five minutes on foot, straight ahead."
            ),
            DialogueLine(
                speaker = "A",
                pt = "Entendido. E há outras opções?",
                en = "Understood. And are there other options?"
            ),
            DialogueLine(
                speaker = "B",
                pt = "Sim, claro!",
                en = "Yes, of course!"
            )
        )
    ),
    ConversationSection(
        heading = "By Metro",
        lines = listOf(
            DialogueLine(
                speaker = "B",
                pt = "A segunda opção é o metro — é a mais rápida. Vá até à estação de metro Marquês de Pombal. É mesmo aqui perto. Apanhe a Linha Azul em direção a Amadora Este e saia na estação Colégio Militar / Luz. Quando sair da estação, siga as placas — o estádio fica mesmo ao lado, a dois minutos a pé.",
                en = "The second option is the metro — it's the fastest. Go to Marquês de Pombal metro station. It's right nearby. Take the Blue Line in the direction of Amadora Este and get off at Colégio Militar / Luz station. When you exit the station, follow the signs — the stadium is right next door, two minutes on foot."
            )
        )
    ),
    ConversationSection(
        heading = "By Taxi",
        lines = listOf(
            DialogueLine(
                speaker = "B",
                pt = "A terceira opção é de táxi. Se não quer andar muito, pode apanhar um táxi mesmo aqui na rua. Diga simplesmente \"Estádio da Luz\" ao motorista — toda a gente conhece. Não é muito longe daqui, por isso não deve custar muito, talvez entre seis a dez euros, dependendo do trânsito. É a opção mais cómoda, especialmente se tiver bagagem.",
                en = "The third option is by taxi. If you don't want to walk much, you can catch a taxi right here on the street. Just say \"Estádio da Luz\" to the driver — everyone knows it. It's not very far from here, so it shouldn't cost much, maybe between six and ten euros, depending on traffic. It's the most comfortable option, especially if you have luggage."
            ),
            DialogueLine(
                speaker = "A",
                pt = "Muito obrigado! Acho que vou de metro — é mais rápido.",
                en = "Thank you very much! I think I'll take the metro — it's faster."
            ),
            DialogueLine(
                speaker = "B",
                pt = "Boa escolha! É rápido e barato. Boa sorte e diverta-se no jogo!",
                en = "Good choice! It's fast and cheap. Good luck and enjoy the game!"
            )
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DirectionsConversationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Asking for Directions") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Text(
                    text = "Getting to Estádio da Luz",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            conversationSections.forEach { section ->
                if (section.heading != null) {
                    item {
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            HorizontalDivider(modifier = Modifier.weight(1f))
                            Text(
                                text = section.heading,
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            HorizontalDivider(modifier = Modifier.weight(1f))
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }

                section.lines.forEach { line ->
                    item {
                        DialogueCard(line = line)
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun DialogueCard(line: DialogueLine) {
    val isSpeakerA = line.speaker == "A"
    val containerColor = if (isSpeakerA)
        MaterialTheme.colorScheme.secondaryContainer
    else
        MaterialTheme.colorScheme.surfaceVariant

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = line.speaker,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = if (isSpeakerA)
                    MaterialTheme.colorScheme.onSecondaryContainer
                else
                    MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = line.pt,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = line.en,
                style = MaterialTheme.typography.bodyMedium,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
