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

// Empregado (waiter) = isFirst false (surfaceVariant)
// Miguel and Carlos (friends) = isFirst true (secondaryContainer)
private val restaurantSections = listOf(
    ConversationSection(
        heading = null,
        lines = listOf(
            DialogueLine(
                speaker = "Empregado",
                pt = "Boa tarde! Sejam bem-vindos. Tenho uma mesa para dois logo ali. Por favor, sigam-me.",
                en = "Good afternoon! Welcome. I have a table for two right over there. Please, follow me."
            ),
            DialogueLine(
                speaker = "Miguel",
                pt = "Obrigado! Pode trazer-nos a ementa, por favor?",
                en = "Thank you! Could you bring us the menu, please?"
            ),
            DialogueLine(
                speaker = "Empregado",
                pt = "Claro, já trago. Posso trazer-lhes água enquanto esperam?",
                en = "Of course, I'll bring it right away. Can I bring you some water while you wait?"
            ),
            DialogueLine(
                speaker = "Carlos",
                pt = "Sim, por favor. Com gás, se fizer favor.",
                en = "Yes, please. Sparkling, if you don't mind."
            )
        )
    ),
    ConversationSection(
        heading = "A Ementa",
        lines = listOf(
            DialogueLine(
                speaker = "Empregado",
                pt = "Aqui têm a ementa. Hoje o nosso prato do dia é frango assado com legumes. Temos também peixe grelhado, carne de vaca e peru recheado.",
                en = "Here is the menu. Today our daily special is roast chicken with vegetables. We also have grilled fish, beef, and stuffed turkey."
            ),
            DialogueLine(
                speaker = "Miguel",
                pt = "Tenho uma questão — sou intolerante à lactose e tenho doença celíaca. Não posso comer glúten nem produtos com leite. Pode dizer-me quais os pratos que são seguros para mim?",
                en = "I have a question — I'm lactose intolerant and have coeliac disease. I can't eat gluten or any dairy products. Can you tell me which dishes are safe for me?"
            ),
            DialogueLine(
                speaker = "Empregado",
                pt = "Claro, peço desculpa pelo inconveniente. O peixe grelhado é preparado apenas com azeite e ervas aromáticas — sem manteiga, sem glúten. O frango assado também é seguro, desde que não queira o molho, que contém farinha. O peru recheado infelizmente não é adequado — o recheio tem pão.",
                en = "Of course, I apologise for the inconvenience. The grilled fish is prepared with just olive oil and herbs — no butter, no gluten. The roast chicken is also safe, as long as you don't want the sauce, which contains flour. The stuffed turkey unfortunately isn't suitable — the stuffing has bread."
            ),
            DialogueLine(
                speaker = "Miguel",
                pt = "Perfeito. Fico com o peixe grelhado, sem molho. Obrigado por explicar!",
                en = "Perfect. I'll have the grilled fish, no sauce. Thank you for explaining!"
            )
        )
    ),
    ConversationSection(
        heading = "A Encomenda",
        lines = listOf(
            DialogueLine(
                speaker = "Empregado",
                pt = "Óptima escolha! E para o senhor?",
                en = "Excellent choice! And for you, sir?"
            ),
            DialogueLine(
                speaker = "Carlos",
                pt = "Para mim, o peru recheado, por favor.",
                en = "For me, the stuffed turkey, please."
            ),
            DialogueLine(
                speaker = "Empregado",
                pt = "Muito bem. Alguma sobremesa ou ficamos só com o prato principal por enquanto?",
                en = "Very well. Any dessert or shall we just start with the main course for now?"
            ),
            DialogueLine(
                speaker = "Carlos",
                pt = "Por enquanto ficamos com o prato principal. Obrigado.",
                en = "For now just the main course. Thank you."
            ),
            DialogueLine(
                speaker = "Empregado",
                pt = "Perfeito. Trago já. Bom apetite antecipado!",
                en = "Perfect. I'll bring it right away. Enjoy your meal!"
            )
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantConversationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("At The Restaurant") },
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
                    text = "Two friends at a restaurant — one has dietary restrictions",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            restaurantSections.forEach { section ->
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
                        DialogueCard(line = line, isFirst = line.speaker != "Empregado")
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}
