package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Tomás (student) = isFirst true (secondaryContainer)
// Professora and Colega = isFirst false (surfaceVariant)
private val schoolLines = listOf(
    DialogueLine(
        speaker = "Professora",
        pt = "Bom dia a todos! Hoje é dia de 'Mostra e Conta'. O Tomás vai partilhar com a turma o seu passatempo favorito. Tomás, podes vir à frente?",
        en = "Good morning everyone! Today is Show and Tell day. Tomás is going to share his favourite hobby with the class. Tomás, can you come to the front?"
    ),
    DialogueLine(
        speaker = "Tomás",
        pt = "Bom dia! O meu passatempo favorito é a observação de pássaros. Trago aqui os meus binóculos e o meu guia de campo.",
        en = "Good morning! My favourite hobby is bird watching. I brought my binoculars and my field guide."
    ),
    DialogueLine(
        speaker = "Professora",
        pt = "Que interessante! O que é um guia de campo?",
        en = "How interesting! What is a field guide?"
    ),
    DialogueLine(
        speaker = "Tomás",
        pt = "Um guia de campo é um livro com fotografias e descrições de muitos pássaros diferentes. Serve para identificar os pássaros que observamos. Quando vejo um pássaro desconhecido, consulto o guia para saber o nome e aprender mais sobre ele.",
        en = "A field guide is a book with photographs and descriptions of many different birds. It's used to identify the birds we observe. When I see an unknown bird, I check the guide to find out its name and learn more about it."
    ),
    DialogueLine(
        speaker = "Colega",
        pt = "Qual é o teu pássaro favorito?",
        en = "What is your favourite bird?"
    ),
    DialogueLine(
        speaker = "Tomás",
        pt = "O meu pássaro favorito é o guarda-rios. Em inglês chama-se 'kingfisher'. É muito bonito — tem penas azuis e laranja brilhantes e vive perto de rios e lagos. Costumo observá-lo no rio Tejo, aqui perto de Lisboa.",
        en = "My favourite bird is the kingfisher — guarda-rios in Portuguese. It's very beautiful — it has brilliant blue and orange feathers and lives near rivers and lakes. I usually watch it on the River Tagus, near Lisbon."
    ),
    DialogueLine(
        speaker = "Professora",
        pt = "Que lindo! E quando costumas ir observar pássaros?",
        en = "How beautiful! And when do you usually go bird watching?"
    ),
    DialogueLine(
        speaker = "Tomás",
        pt = "Normalmente vou de manhã cedo, antes das aulas ao fim de semana. Os pássaros são mais ativos ao amanhecer — cantam mais e é mais fácil de os ver. Às vezes o meu pai vem comigo.",
        en = "I usually go early in the morning, before school on weekends. Birds are more active at dawn — they sing more and it's easier to spot them. Sometimes my father comes with me."
    ),
    DialogueLine(
        speaker = "Colega",
        pt = "Já viste pássaros raros alguma vez?",
        en = "Have you ever seen any rare birds?"
    ),
    DialogueLine(
        speaker = "Tomás",
        pt = "Sim! No mês passado vi uma cegonha branca num campo perto de Santarém. As cegonhas são muito grandes e fazem ninhos enormes em cima de chaminés e postes. Foi incrível!",
        en = "Yes! Last month I saw a white stork in a field near Santarém. Storks are very large and make enormous nests on top of chimneys and poles. It was incredible!"
    ),
    DialogueLine(
        speaker = "Professora",
        pt = "Muito bem, Tomás! Aprendemos todos muito hoje. Obrigado por partilhares este passatempo connosco. Quem sabe, talvez alguns de vós fiquem com vontade de experimentar!",
        en = "Well done, Tomás! We all learned a lot today. Thank you for sharing this hobby with us. Who knows, maybe some of you will feel like giving it a try!"
    ),
    DialogueLine(
        speaker = "Tomás",
        pt = "Recomendo a toda a gente! É muito relaxante e aprende-se muito sobre a natureza.",
        en = "I recommend it to everyone! It's very relaxing and you learn a lot about nature."
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchoolConversationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("School — Show And Tell") },
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
                    text = "A student presents his hobby — bird watching",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            schoolLines.forEach { line ->
                item {
                    DialogueCard(line = line, isFirst = line.speaker == "Tomás")
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}
