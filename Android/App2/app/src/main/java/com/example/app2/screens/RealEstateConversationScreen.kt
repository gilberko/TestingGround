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

private val realEstateSections = listOf(
    ConversationSection(
        heading = null,
        lines = listOf(
            DialogueLine(
                speaker = "Sofia",
                pt = "Bem-vindos! Sou a Sofia, da Imobiliária Central. É um prazer conhecê-los.",
                en = "Welcome! I'm Sofia from Imobiliária Central. It's a pleasure to meet you."
            ),
            DialogueLine(
                speaker = "Ricardo",
                pt = "Olá, Sofia. Eu sou o Ricardo e esta é a minha mulher, Ana.",
                en = "Hello, Sofia. I'm Ricardo and this is my wife, Ana."
            ),
            DialogueLine(
                speaker = "Sofia",
                pt = "Entrem, por favor. O apartamento tem cento e vinte metros quadrados, fica no quinto andar e tem uma vista linda sobre a cidade.",
                en = "Please come in. The apartment is one hundred and twenty square metres, on the fifth floor, and has a beautiful view of the city."
            ),
            DialogueLine(
                speaker = "Ana",
                pt = "Que bonito! A luz é incrível.",
                en = "How beautiful! The light is incredible."
            )
        )
    ),
    ConversationSection(
        heading = "O Apartamento — The Apartment",
        lines = listOf(
            DialogueLine(
                speaker = "Sofia",
                pt = "Sim, o apartamento tem exposição solar todo o dia. Temos três quartos, duas casas de banho e uma sala ampla com acesso à varanda.",
                en = "Yes, the apartment has sunlight all day. We have three bedrooms, two bathrooms and a spacious living room with access to the balcony."
            ),
            DialogueLine(
                speaker = "Ricardo",
                pt = "Três quartos... Nós temos três filhos. Achas que chega, Ana?",
                en = "Three bedrooms... We have three children. Do you think it's enough, Ana?"
            ),
            DialogueLine(
                speaker = "Ana",
                pt = "Dois dos miúdos podem partilhar, mas é um bocado justo.",
                en = "Two of the kids can share, but it's a bit tight."
            ),
            DialogueLine(
                speaker = "Sofia",
                pt = "O quarto principal tem catorze metros quadrados. Dito isto, o apartamento acima deste tem quatro quartos — posso mostrar-lho também se quiserem.",
                en = "The master bedroom is fourteen square metres. That said, the apartment above this one has four bedrooms — I can show it to you as well if you'd like."
            ),
            DialogueLine(
                speaker = "Ricardo",
                pt = "Vamos ver primeiro este e depois decidimos.",
                en = "Let's look at this one first and then we'll decide."
            )
        )
    ),
    ConversationSection(
        heading = "A Zona e as Escolas — The Area and Schools",
        lines = listOf(
            DialogueLine(
                speaker = "Ana",
                pt = "Sofia, o que nos pode dizer sobre a zona?",
                en = "Sofia, what can you tell us about the area?"
            ),
            DialogueLine(
                speaker = "Sofia",
                pt = "É uma zona muito tranquila e segura. Há supermercados, cafés e farmácias a poucos minutos a pé.",
                en = "It's a very quiet and safe area. There are supermarkets, cafés and pharmacies a few minutes' walk away."
            ),
            DialogueLine(
                speaker = "Ricardo",
                pt = "E em termos de escolas? Temos três filhos em idade escolar.",
                en = "And in terms of schools? We have three school-age children."
            ),
            DialogueLine(
                speaker = "Sofia",
                pt = "Há uma escola primária a duzentos metros daqui e um colégio muito bem avaliado a menos de um quilómetro. Também há um jardim de infância a dois minutos a pé.",
                en = "There's a primary school two hundred metres from here and a highly-rated secondary school less than one kilometre away. There's also a nursery school two minutes' walk away."
            ),
            DialogueLine(
                speaker = "Ana",
                pt = "Isso é muito conveniente.",
                en = "That's very convenient."
            )
        )
    ),
    ConversationSection(
        heading = "Transportes e Estacionamento — Transport and Parking",
        lines = listOf(
            DialogueLine(
                speaker = "Ricardo",
                pt = "Temos dois carros, mas preferíamos usar os transportes públicos. O trânsito em Lisboa é difícil.",
                en = "We have two cars, but we'd prefer to use public transport. The traffic in Lisbon is difficult."
            ),
            DialogueLine(
                speaker = "Sofia",
                pt = "Compreendo perfeitamente. Há uma paragem de autocarro mesmo à porta e a estação de metro mais próxima fica a dez minutos a pé, com ligações diretas ao centro.",
                en = "I completely understand. There's a bus stop right at the door and the nearest metro station is ten minutes' walk, with direct connections to the centre."
            ),
            DialogueLine(
                speaker = "Ana",
                pt = "E se precisarmos do carro?",
                en = "And if we need the car?"
            ),
            DialogueLine(
                speaker = "Sofia",
                pt = "O apartamento inclui dois lugares de estacionamento numa garagem subterrânea, com acesso por elevador diretamente ao piso do apartamento.",
                en = "The apartment includes two parking spaces in an underground garage, with lift access directly to the apartment floor."
            ),
            DialogueLine(
                speaker = "Ricardo",
                pt = "Isso é uma vantagem. O preço inclui os lugares de estacionamento?",
                en = "That's an advantage. Does the price include the parking spaces?"
            ),
            DialogueLine(
                speaker = "Sofia",
                pt = "Sim, está tudo incluído no preço pedido.",
                en = "Yes, everything is included in the asking price."
            ),
            DialogueLine(
                speaker = "Ana",
                pt = "Ricardo, a zona parece boa. A minha dúvida continua a ser se três quartos chegam.",
                en = "Ricardo, the area seems good. My doubt is still whether three bedrooms are enough."
            ),
            DialogueLine(
                speaker = "Ricardo",
                pt = "Sofia, talvez valha a pena ver o de quatro quartos.",
                en = "Sofia, maybe it's worth seeing the four-bedroom one."
            ),
            DialogueLine(
                speaker = "Sofia",
                pt = "Claro! Venham comigo ao andar de cima.",
                en = "Of course! Come with me to the floor above."
            )
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RealEstateConversationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Real Estate") },
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
                    text = "Viewing an apartment for sale in Lisbon",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            realEstateSections.forEach { section ->
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
                        DialogueCard(line = line, isFirst = line.speaker == "Sofia")
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}
