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

private val casualMeetingSections = listOf(
    ConversationSection(
        heading = null,
        lines = listOf(
            DialogueLine(
                speaker = "Miguel",
                pt = "Carlos? Não acredito! Carlos Ferreira?",
                en = "Carlos? I can't believe it! Carlos Ferreira?"
            ),
            DialogueLine(
                speaker = "Carlos",
                pt = "Miguel! Que surpresa! Quanto tempo!",
                en = "Miguel! What a surprise! It's been so long!"
            ),
            DialogueLine(
                speaker = "Miguel",
                pt = "Não te vejo desde a universidade! Como estás?",
                en = "I haven't seen you since university! How are you?"
            ),
            DialogueLine(
                speaker = "Carlos",
                pt = "Estou bem, obrigado. E tu? Continuas em Lisboa?",
                en = "I'm well, thanks. And you? Are you still in Lisbon?"
            ),
            DialogueLine(
                speaker = "Miguel",
                pt = "Sim, cá estou. O que estás a fazer por aqui?",
                en = "Yes, here I am. What are you doing around here?"
            )
        )
    ),
    ConversationSection(
        heading = "Os Anos Que Passaram — The Years That Have Passed",
        lines = listOf(
            DialogueLine(
                speaker = "Carlos",
                pt = "Casaste-te? Tens filhos?",
                en = "Did you get married? Do you have children?"
            ),
            DialogueLine(
                speaker = "Miguel",
                pt = "Sim, casei há seis anos. Tenho duas filhas, uma de quatro e outra de dois.",
                en = "Yes, I got married six years ago. I have two daughters, one of four and one of two."
            ),
            DialogueLine(
                speaker = "Carlos",
                pt = "Que maravilha! E em que é que trabalhas?",
                en = "How wonderful! And what do you do for work?"
            ),
            DialogueLine(
                speaker = "Miguel",
                pt = "Trabalho numa empresa de tecnologia aqui em Lisboa. E tu, o que andas a fazer?",
                en = "I work at a tech company here in Lisbon. And you, what have you been up to?"
            ),
            DialogueLine(
                speaker = "Carlos",
                pt = "Tenho trabalhado em Braga, mas agora estou a pensar mudar-me.",
                en = "I've been working in Braga, but now I'm thinking of moving."
            )
        )
    ),
    ConversationSection(
        heading = "A Mudança Para Lisboa — Moving to Lisbon",
        lines = listOf(
            DialogueLine(
                speaker = "Miguel",
                pt = "Para Lisboa? A sério?",
                en = "To Lisbon? Really?"
            ),
            DialogueLine(
                speaker = "Carlos",
                pt = "Sim! Tenho uma entrevista de emprego hoje à tarde numa empresa em Parque das Nações.",
                en = "Yes! I have a job interview this afternoon at a company in Parque das Nações."
            ),
            DialogueLine(
                speaker = "Miguel",
                pt = "Boa sorte! E o que pensa a tua família?",
                en = "Good luck! And what does your family think?"
            ),
            DialogueLine(
                speaker = "Carlos",
                pt = "A minha mulher apoia a ideia. Temos dois filhos e já estamos a ver apartamentos. Hoje vejo dois com uma agente imobiliária.",
                en = "My wife supports the idea. We have two children and we're already looking at apartments. Today I'm seeing two with a real estate agent."
            ),
            DialogueLine(
                speaker = "Miguel",
                pt = "Em que zona estás a ver?",
                en = "What area are you looking at?"
            ),
            DialogueLine(
                speaker = "Carlos",
                pt = "Estamos a ver em Cascais e também aqui em Lisboa. O que achas de Cascais para famílias?",
                en = "We're looking in Cascais and also here in Lisbon. What do you think of Cascais for families?"
            ),
            DialogueLine(
                speaker = "Miguel",
                pt = "Cascais é muito bom — boas escolas, perto do mar. Mas a viagem para Lisboa pode ser longa se fores ao escritório todos os dias.",
                en = "Cascais is great — good schools, close to the sea. But the commute to Lisbon can be long if you go to the office every day."
            ),
            DialogueLine(
                speaker = "Carlos",
                pt = "Pois, vamos ponderar melhor.",
                en = "Indeed, we'll think it over more carefully."
            )
        )
    ),
    ConversationSection(
        heading = "Até Logo — Goodbye",
        lines = listOf(
            DialogueLine(
                speaker = "Miguel",
                pt = "Olha, tenho de ir. Estou atrasado para uma reunião.",
                en = "Look, I have to go. I'm late for a meeting."
            ),
            DialogueLine(
                speaker = "Carlos",
                pt = "Eu também me tenho de despachar. Mas foi ótimo ver-te.",
                en = "I also have to get going. But it was great seeing you."
            ),
            DialogueLine(
                speaker = "Miguel",
                pt = "Com certeza! Dás-me o teu número? Quando te instalares, temos de sair para jantar.",
                en = "Absolutely! Will you give me your number? When you settle in, we have to go out for dinner."
            ),
            DialogueLine(
                speaker = "Carlos",
                pt = "Ótimo! O meu número é... E o teu?",
                en = "Great! My number is... And yours?"
            ),
            DialogueLine(
                speaker = "Miguel",
                pt = "Manda-me uma mensagem quando souberes da entrevista. Boa sorte!",
                en = "Send me a message when you hear about the interview. Good luck!"
            ),
            DialogueLine(
                speaker = "Carlos",
                pt = "Obrigado! Até logo, Miguel.",
                en = "Thanks! See you, Miguel."
            )
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CasualMeetingConversationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Casual Meeting") },
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
                    text = "Two old university friends run into each other in Lisbon",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            casualMeetingSections.forEach { section ->
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
                        DialogueCard(line = line, isFirst = line.speaker == "Miguel")
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}
