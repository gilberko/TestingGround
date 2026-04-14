package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// Pedro (traveller) = isFirst true (secondaryContainer)
// Funcionário (airport worker) = isFirst false (surfaceVariant)
private val airportSections = listOf(
    ConversationSection(
        heading = null,
        lines = listOf(
            DialogueLine(
                speaker = "Pedro",
                pt = "Com licença! Desculpe incomodar, mas estou um pouco perdido. Tenho de apanhar o voo às quinze e trinta e não consigo encontrar o portão vinte e dois.",
                en = "Excuse me! Sorry to bother you, but I'm a bit lost. I have to catch the flight at 15:30 and I can't find gate twenty-two."
            ),
            DialogueLine(
                speaker = "Funcionário",
                pt = "Não se preocupe, ainda tem tempo. Posso ajudá-lo a chegar lá sem problema.",
                en = "Don't worry, you still have time. I can help you get there no problem."
            )
        )
    ),
    ConversationSection(
        heading = "À Procura do Portão",
        lines = listOf(
            DialogueLine(
                speaker = "Funcionário",
                pt = "Estamos aqui na zona de chegadas. Para chegar ao portão vinte e dois, precisa de subir ao primeiro andar pela escada rolante que está ali mesmo ao fundo do corredor. Quando chegar ao primeiro andar, vire à esquerda e siga as placas que indicam \"Portões 20 a 30\". O portão vinte e dois fica mesmo no meio dessa zona, não tem perda.",
                en = "We're here in the arrivals area. To get to gate twenty-two, you need to go up to the first floor on the escalator at the end of the corridor. When you reach the first floor, turn left and follow the signs for \"Gates 20 to 30\". Gate twenty-two is right in the middle of that area, you can't miss it."
            ),
            DialogueLine(
                speaker = "Pedro",
                pt = "Muito obrigado! Ainda tenho cerca de quarenta e cinco minutos. Há algum sítio onde possa comer qualquer coisa antes de embarcar? Não tive tempo de almoçar.",
                en = "Thank you very much! I still have about forty-five minutes. Is there somewhere I can grab something to eat before boarding? I didn't have time for lunch."
            ),
            DialogueLine(
                speaker = "Funcionário",
                pt = "Tem sorte! Há uma zona de restauração mesmo no caminho para o seu portão.",
                en = "You're in luck! There's a food court right on the way to your gate."
            )
        )
    ),
    ConversationSection(
        heading = "A Zona de Restauração",
        lines = listOf(
            DialogueLine(
                speaker = "Funcionário",
                pt = "Quando subir ao primeiro andar e virar à esquerda, vai ver logo uma zona de restauração à direita. Tem várias opções — há sandes e snacks numa pastelaria, uma loja de conveniência com fruta e iogurtes, um café que serve pratos quentes, e uma loja de sushi se quiser algo diferente. Os preços são um pouco mais altos do que lá fora, mas é normal num aeroporto.",
                en = "When you go up to the first floor and turn left, you'll immediately see a food court on the right. There are several options — there are sandwiches and snacks at a pastry shop, a convenience store with fruit and yoghurts, a café that serves hot dishes, and a sushi place if you want something different. Prices are a bit higher than outside, but that's normal in an airport."
            ),
            DialogueLine(
                speaker = "Pedro",
                pt = "Perfeito! Acho que vou a uma sandes e um café para me animar. Tem alguma recomendação?",
                en = "Perfect! I think I'll go for a sandwich and a coffee to perk me up. Do you have any recommendations?"
            ),
            DialogueLine(
                speaker = "Funcionário",
                pt = "O café 'Sabores de Portugal' tem uns pastéis de nata muito bons e o café é excelente. Fica mesmo à entrada da zona de restauração.",
                en = "The 'Sabores de Portugal' café has very good pastéis de nata and the coffee is excellent. It's right at the entrance to the food court."
            ),
            DialogueLine(
                speaker = "Pedro",
                pt = "Ótimo! Muito obrigado pela ajuda. Tem sido muito amável.",
                en = "Great! Thank you so much for the help. You've been very kind."
            ),
            DialogueLine(
                speaker = "Funcionário",
                pt = "De nada! Boa viagem e não se esqueça — portão vinte e dois, primeiro andar, à esquerda!",
                en = "You're welcome! Have a good trip and don't forget — gate twenty-two, first floor, on the left!"
            )
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AirportConversationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("At The Airport") },
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
                    text = "A traveller asks for help finding his gate and somewhere to eat",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            airportSections.forEach { section ->
                if (section.heading != null) {
                    item {
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
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
                        DialogueCard(line = line, isFirst = line.speaker == "Pedro")
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}
