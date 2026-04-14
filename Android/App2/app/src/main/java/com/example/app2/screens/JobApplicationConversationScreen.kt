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

// Ricardo (applicant) = isFirst true (secondaryContainer)
// Gerente (manager) = isFirst false (surfaceVariant)
private val jobApplicationSections = listOf(
    ConversationSection(
        heading = null,
        lines = listOf(
            DialogueLine(
                speaker = "Ricardo",
                pt = "Bom dia. Chamo-me Ricardo Silva. Vim candidatar-me ao lugar de barista que vi anunciado na montra.",
                en = "Good morning. My name is Ricardo Silva. I came to apply for the barista position I saw advertised in the window."
            ),
            DialogueLine(
                speaker = "Gerente",
                pt = "Bom dia, Ricardo. Sou a Dra. Santos, a gerente. Entre, por favor. Pode sentar-se.",
                en = "Good morning, Ricardo. I'm Dr. Santos, the manager. Come in, please. Have a seat."
            ),
            DialogueLine(
                speaker = "Ricardo",
                pt = "Muito obrigado.",
                en = "Thank you very much."
            ),
            DialogueLine(
                speaker = "Gerente",
                pt = "Tem experiência como barista ou a trabalhar num café?",
                en = "Do you have experience as a barista or working in a café?"
            ),
            DialogueLine(
                speaker = "Ricardo",
                pt = "Sim, trabalhei dois anos num café no Chiado. Sei preparar os cafés mais comuns e tenho boas referências.",
                en = "Yes, I worked for two years in a café in Chiado. I know how to prepare the most common coffees and have good references."
            )
        )
    ),
    ConversationSection(
        heading = "Competências",
        lines = listOf(
            DialogueLine(
                speaker = "Gerente",
                pt = "Muito bem. Diga-me, como se prepara um espresso macchiato?",
                en = "Very good. Tell me, how do you prepare an espresso macchiato?"
            ),
            DialogueLine(
                speaker = "Ricardo",
                pt = "Um espresso macchiato é uma dose simples ou dupla de espresso, com apenas uma pequena colher de leite vaporizado ou espuma de leite por cima. A palavra 'macchiato' significa 'manchado' — é um espresso 'manchado' com um pouco de leite.",
                en = "An espresso macchiato is a single or double shot of espresso, with just a small spoonful of steamed milk or milk foam on top. The word 'macchiato' means 'stained' — it's an espresso 'stained' with a little milk."
            ),
            DialogueLine(
                speaker = "Gerente",
                pt = "Correto. E um cappuccino?",
                en = "Correct. And a cappuccino?"
            ),
            DialogueLine(
                speaker = "Ricardo",
                pt = "Um cappuccino tem três partes iguais: uma dose de espresso, leite vaporizado e espuma de leite. Normalmente é servido numa chávena maior, com um terço de cada componente. Pode polvilhar-se com cacau em pó por cima, se o cliente quiser.",
                en = "A cappuccino has three equal parts: a shot of espresso, steamed milk, and milk foam. It's normally served in a larger cup, with one third of each component. You can dust it with cocoa powder on top if the customer wishes."
            ),
            DialogueLine(
                speaker = "Gerente",
                pt = "Excelente! Vejo que sabe bem o que faz.",
                en = "Excellent! I can see you really know what you're doing."
            )
        )
    ),
    ConversationSection(
        heading = "Horário e Condições",
        lines = listOf(
            DialogueLine(
                speaker = "Gerente",
                pt = "Agora vamos falar das condições. O horário seria de segunda a sexta, das oito da manhã às quatro da tarde. É trabalho a tempo inteiro. Estaria disponível?",
                en = "Now let's talk about the terms. The schedule would be Monday to Friday, from eight in the morning to four in the afternoon. It's full-time work. Would you be available?"
            ),
            DialogueLine(
                speaker = "Ricardo",
                pt = "Sim, estou disponível. E qual seria o ordenado?",
                en = "Yes, I'm available. And what would the salary be?"
            ),
            DialogueLine(
                speaker = "Gerente",
                pt = "O ordenado base é de novecentos euros mensais, mais subsídio de refeição de seis euros por dia. Temos também seguro de saúde após três meses de contrato.",
                en = "The base salary is nine hundred euros per month, plus a meal allowance of six euros per day. We also have health insurance after three months on contract."
            ),
            DialogueLine(
                speaker = "Ricardo",
                pt = "Parece-me muito razoável. Quando poderia começar a trabalhar, caso seja selecionado?",
                en = "That sounds very reasonable. When could I start, if I'm selected?"
            ),
            DialogueLine(
                speaker = "Gerente",
                pt = "Precisamos de alguém a partir da próxima semana. Entraremos em contacto em dois dias. Muito obrigado pela sua candidatura, Ricardo.",
                en = "We need someone starting next week. We'll be in touch within two days. Thank you very much for your application, Ricardo."
            ),
            DialogueLine(
                speaker = "Ricardo",
                pt = "Obrigado pela oportunidade. Até breve!",
                en = "Thank you for the opportunity. See you soon!"
            )
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobApplicationConversationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Applying For A Job") },
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
                    text = "A young man applies for a barista position at a café",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            jobApplicationSections.forEach { section ->
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
                        DialogueCard(line = line, isFirst = line.speaker == "Ricardo")
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}
