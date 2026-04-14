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

// Ana (Team Leader 1, back-end) = isFirst true (secondaryContainer)
// Bruno (Team Leader 2, front-end) = isFirst false (surfaceVariant)
private val workMeetingSections = listOf(
    ConversationSection(
        heading = null,
        lines = listOf(
            DialogueLine(
                speaker = "Ana",
                pt = "Bom dia, Bruno. Obrigada por vires. Temos bastante para discutir sobre o novo projeto.",
                en = "Good morning, Bruno. Thanks for coming. We have quite a bit to discuss about the new project."
            ),
            DialogueLine(
                speaker = "Bruno",
                pt = "Bom dia, Ana. Concordo. Ontem li o documento de requisitos — é ambicioso mas acho que é viável.",
                en = "Good morning, Ana. I agree. Yesterday I read the requirements document — it's ambitious but I think it's feasible."
            )
        )
    ),
    ConversationSection(
        heading = "Divisão de Equipas",
        lines = listOf(
            DialogueLine(
                speaker = "Ana",
                pt = "Propus à direção que a minha equipa fique responsável pela API de back-end. Vamos desenvolver todos os endpoints REST e a lógica de negócio.",
                en = "I proposed to management that my team takes responsibility for the back-end API. We'll develop all the REST endpoints and the business logic."
            ),
            DialogueLine(
                speaker = "Bruno",
                pt = "Faz sentido. A minha equipa trata então do front-end — a interface gráfica e a integração com a API. Vamos usar React para a versão web e React Native para a aplicação móvel.",
                en = "That makes sense. My team will then handle the front-end — the graphical interface and the API integration. We'll use React for the web version and React Native for the mobile app."
            ),
            DialogueLine(
                speaker = "Ana",
                pt = "Ótimo. Assim ficamos com uma separação clara de responsabilidades.",
                en = "Great. This way we have a clear separation of responsibilities."
            )
        )
    ),
    ConversationSection(
        heading = "Integração",
        lines = listOf(
            DialogueLine(
                speaker = "Bruno",
                pt = "Precisamos de acordar o contrato da API o mais depressa possível. Se a minha equipa não souber a estrutura dos endpoints, não conseguimos avançar com o front-end.",
                en = "We need to agree on the API contract as soon as possible. If my team doesn't know the endpoint structure, we can't move forward with the front-end."
            ),
            DialogueLine(
                speaker = "Ana",
                pt = "Tens razão. Proponho que definamos e documentemos todos os endpoints principais até ao final desta semana. Posso ter um esboço pronto até amanhã para revisão.",
                en = "You're right. I propose we define and document all the main endpoints by the end of this week. I can have a draft ready by tomorrow for review."
            ),
            DialogueLine(
                speaker = "Bruno",
                pt = "Perfeito. Assim a minha equipa pode começar a construir os mocks do front-end na próxima semana, sem esperar pela implementação real.",
                en = "Perfect. That way my team can start building front-end mocks next week, without waiting for the real implementation."
            ),
            DialogueLine(
                speaker = "Ana",
                pt = "Bom plano. Usamos Swagger para a documentação da API?",
                en = "Good plan. Shall we use Swagger for the API documentation?"
            ),
            DialogueLine(
                speaker = "Bruno",
                pt = "Sim, Swagger é ideal. Toda a gente conhece e é fácil de gerar clientes automaticamente.",
                en = "Yes, Swagger is ideal. Everyone knows it and it's easy to auto-generate clients."
            )
        )
    ),
    ConversationSection(
        heading = "Prazos e Estimativas",
        lines = listOf(
            DialogueLine(
                speaker = "Ana",
                pt = "Agora os prazos. A direção quer uma versão beta em seis semanas. Achas que é realista?",
                en = "Now the timelines. Management wants a beta version in six weeks. Do you think that's realistic?"
            ),
            DialogueLine(
                speaker = "Bruno",
                pt = "Seis semanas é apertado. O front-end pode estar pronto em seis semanas, mas só se tivermos a API estável nas primeiras três. Se houver atrasos no back-end, a integração vai sofrer.",
                en = "Six weeks is tight. The front-end can be ready in six weeks, but only if we have a stable API in the first three. If there are back-end delays, the integration will suffer."
            ),
            DialogueLine(
                speaker = "Ana",
                pt = "Percebo a preocupação. Sugiro que acrescentemos uma semana de margem no planeamento interno. Dizemos à equipa que o objetivo é cinco semanas, mas reportamos seis à direção.",
                en = "I understand the concern. I suggest we add a week of buffer in our internal planning. We tell the team the goal is five weeks, but we report six to management."
            ),
            DialogueLine(
                speaker = "Bruno",
                pt = "Boa ideia. Assim temos margem para imprevistos sem comprometer o prazo oficial.",
                en = "Good idea. That way we have room for unexpected issues without compromising the official deadline."
            ),
            DialogueLine(
                speaker = "Ana",
                pt = "Exato. Vamos marcar uma reunião de acompanhamento para a semana que vem, para verificar como estão as coisas.",
                en = "Exactly. Let's schedule a follow-up meeting for next week, to check how things are going."
            ),
            DialogueLine(
                speaker = "Bruno",
                pt = "Combinado. Obrigado, Ana. Estou otimista em relação a este projeto.",
                en = "Agreed. Thanks, Ana. I'm optimistic about this project."
            ),
            DialogueLine(
                speaker = "Ana",
                pt = "Eu também. Até para a semana!",
                en = "Me too. See you next week!"
            )
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkMeetingConversationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Work Meeting") },
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
                    text = "Two team leaders plan a software project",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            workMeetingSections.forEach { section ->
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
                        DialogueCard(line = line, isFirst = line.speaker == "Ana")
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}
