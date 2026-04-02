package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp

private data class WordEntry(val pt: String, val en: String, val gender: String = "")
private data class WordCategory(val title: String, val entries: List<WordEntry>)

private val wordCategories = listOf(
    WordCategory(
        "House / Home",
        listOf(
            WordEntry("casa", "house / home", "(f)"),
            WordEntry("apartamento", "apartment / flat", "(m)"),
            WordEntry("quarto", "bedroom / room", "(m)"),
            WordEntry("sala", "living room", "(f)"),
            WordEntry("cozinha", "kitchen", "(f)"),
            WordEntry("casa de banho", "bathroom", "(f)"),
            WordEntry("jardim", "garden", "(m)"),
            WordEntry("garagem", "garage", "(f)"),
            WordEntry("janela", "window", "(f)"),
            WordEntry("porta", "door", "(f)"),
            WordEntry("parede", "wall", "(f)"),
            WordEntry("tecto", "ceiling", "(m)"),
            WordEntry("chão", "floor / ground", "(m)"),
            WordEntry("escadas", "stairs", "(f)"),
            WordEntry("cama", "bed", "(f)"),
            WordEntry("sofá", "sofa", "(m)"),
            WordEntry("mesa", "table", "(f)"),
            WordEntry("cadeira", "chair", "(f)"),
            WordEntry("armário", "wardrobe / cupboard", "(m)"),
            WordEntry("frigorífico", "fridge", "(m)"),
            WordEntry("fogão", "cooker / stove", "(m)"),
            WordEntry("chave", "key", "(f)"),
            WordEntry("lâmpada", "lamp / light bulb", "(f)"),
            WordEntry("cortina", "curtain", "(f)"),
            WordEntry("tapete", "carpet / rug", "(m)"),
            WordEntry("espelho", "mirror", "(m)"),
            WordEntry("toalha", "towel", "(f)"),
            WordEntry("almofada", "pillow / cushion", "(f)"),
            WordEntry("sanita", "toilet", "(f)"),
            WordEntry("elevador", "elevator / lift", "(m)"),
            WordEntry("congelador", "freezer", "(m)"),
            WordEntry("forno", "oven", "(m)"),
            WordEntry("micro-ondas", "microwave", "(m)"),
            WordEntry("lençóis", "sheets", "(m pl)"),
            WordEntry("máquina de lavar (roupa)", "washing machine", "(f)"),
            WordEntry("máquina de secar (roupa)", "tumble dryer", "(f)"),
            WordEntry("ar condicionado", "air conditioner", "(m)")
        )
    ),
    WordCategory(
        "Home — Actions & Phrases",
        listOf(
            WordEntry("Abrir a janela", "Open the window"),
            WordEntry("Fechar a janela", "Close the window"),
            WordEntry("Abrir a porta", "Open the door"),
            WordEntry("Trancar a porta", "Lock the door"),
            WordEntry("Lavar a louça", "Do the dishes"),
            WordEntry("Limpar o quarto", "Clean the room"),
            WordEntry("Mudar / trocar os lençóis", "Change the sheets"),
            WordEntry("O apartamento fica no segundo andar.", "The apartment is on the second floor.")
        )
    ),
    WordCategory(
        "Food & Dining",
        listOf(
            WordEntry("comida", "food", "(f)"),
            WordEntry("prato", "plate / dish", "(m)"),
            WordEntry("faca", "knife", "(f)"),
            WordEntry("garfo", "fork", "(m)"),
            WordEntry("colher", "spoon", "(f)"),
            WordEntry("copo", "glass / cup", "(m)"),
            WordEntry("garrafa", "bottle", "(f)"),
            WordEntry("água", "water", "(f)"),
            WordEntry("vinho", "wine", "(m)"),
            WordEntry("cerveja", "beer", "(f)"),
            WordEntry("pão", "bread", "(m)"),
            WordEntry("manteiga", "butter", "(f)"),
            WordEntry("queijo", "cheese", "(m)"),
            WordEntry("carne", "meat", "(f)"),
            WordEntry("peixe", "fish", "(m)"),
            WordEntry("frango", "chicken", "(m)"),
            WordEntry("ovo", "egg", "(m)"),
            WordEntry("arroz", "rice", "(m)"),
            WordEntry("batata", "potato", "(f)"),
            WordEntry("salada", "salad", "(f)"),
            WordEntry("sopa", "soup", "(f)"),
            WordEntry("fruta", "fruit", "(f)"),
            WordEntry("legumes", "vegetables", "(m)"),
            WordEntry("sal", "salt", "(m)"),
            WordEntry("açúcar", "sugar", "(m)"),
            WordEntry("azeite", "olive oil", "(m)"),
            WordEntry("café", "coffee", "(m)"),
            WordEntry("conta", "bill", "(f)")
        )
    ),
    WordCategory(
        "Transport",
        listOf(
            WordEntry("carro", "car", "(m)"),
            WordEntry("autocarro", "bus", "(m)"),
            WordEntry("comboio", "train", "(m)"),
            WordEntry("metro", "metro / underground", "(m)"),
            WordEntry("avião", "aeroplane", "(m)"),
            WordEntry("barco", "boat", "(m)"),
            WordEntry("bicicleta", "bicycle", "(f)"),
            WordEntry("mota", "motorbike", "(f)"),
            WordEntry("táxi", "taxi", "(m)"),
            WordEntry("paragem", "bus stop", "(f)"),
            WordEntry("estação", "station", "(f)"),
            WordEntry("aeroporto", "airport", "(m)"),
            WordEntry("bilhete", "ticket", "(m)"),
            WordEntry("passagem", "passage / ticket", "(f)"),
            WordEntry("bagagem", "luggage", "(f)"),
            WordEntry("mala", "suitcase / bag", "(f)"),
            WordEntry("mapa", "map", "(m)"),
            WordEntry("semáforo", "traffic light", "(m)"),
            WordEntry("estrada", "road", "(f)"),
            WordEntry("rua", "street", "(f)"),
            WordEntry("avenida", "avenue", "(f)"),
            WordEntry("ponte", "bridge", "(f)"),
            WordEntry("gasolina", "petrol", "(f)"),
            WordEntry("parque de estacionamento", "car park", "(m)")
        )
    ),
    WordCategory(
        "Entertainment",
        listOf(
            WordEntry("televisão", "television", "(f)"),
            WordEntry("filme", "film", "(m)"),
            WordEntry("música", "music", "(f)"),
            WordEntry("livro", "book", "(m)"),
            WordEntry("jornal", "newspaper", "(m)"),
            WordEntry("revista", "magazine", "(f)"),
            WordEntry("concerto", "concert", "(m)"),
            WordEntry("teatro", "theatre", "(m)"),
            WordEntry("cinema", "cinema", "(m)"),
            WordEntry("jogo", "game", "(m)"),
            WordEntry("computador", "computer", "(m)"),
            WordEntry("telemóvel", "mobile phone", "(m)"),
            WordEntry("internet", "internet", "(f)"),
            WordEntry("canção", "song", "(f)"),
            WordEntry("rádio", "radio", "(m)"),
            WordEntry("dança", "dance", "(f)"),
            WordEntry("festa", "party", "(f)"),
            WordEntry("férias", "holidays / vacation", "(f)"),
            WordEntry("passeio", "walk / outing", "(m)"),
            WordEntry("praia", "beach", "(f)"),
            WordEntry("piscina", "swimming pool", "(f)"),
            WordEntry("desporto", "sport", "(m)"),
            WordEntry("futebol", "football", "(m)")
        )
    ),
    WordCategory(
        "Places",
        listOf(
            WordEntry("hotel", "hotel", "(m)"),
            WordEntry("restaurante", "restaurant", "(m)"),
            WordEntry("hospital", "hospital", "(m)"),
            WordEntry("banco", "bank", "(m)"),
            WordEntry("farmácia", "pharmacy", "(f)"),
            WordEntry("supermercado", "supermarket", "(m)"),
            WordEntry("mercado", "market", "(m)"),
            WordEntry("loja", "shop / store", "(f)"),
            WordEntry("escola", "school", "(f)"),
            WordEntry("universidade", "university", "(f)"),
            WordEntry("biblioteca", "library", "(f)"),
            WordEntry("museu", "museum", "(m)"),
            WordEntry("igreja", "church", "(f)"),
            WordEntry("correios", "post office", "(m)"),
            WordEntry("polícia", "police", "(f)"),
            WordEntry("câmara municipal", "town hall", "(f)"),
            WordEntry("cidade", "city", "(f)"),
            WordEntry("vila", "town", "(f)"),
            WordEntry("aldeia", "village", "(f)"),
            WordEntry("país", "country", "(m)"),
            WordEntry("campo", "countryside / field", "(m)"),
            WordEntry("parque", "park", "(m)"),
            WordEntry("praça", "square / plaza", "(f)")
        )
    ),
    WordCategory(
        "People & Family",
        listOf(
            WordEntry("homem", "man", "(m)"),
            WordEntry("mulher", "woman", "(f)"),
            WordEntry("criança", "child", "(f)"),
            WordEntry("bebé", "baby", "(m)"),
            WordEntry("rapaz", "boy", "(m)"),
            WordEntry("rapariga", "girl", "(f)"),
            WordEntry("pai", "father", "(m)"),
            WordEntry("mãe", "mother", "(f)"),
            WordEntry("filho", "son", "(m)"),
            WordEntry("filha", "daughter", "(f)"),
            WordEntry("irmão", "brother", "(m)"),
            WordEntry("irmã", "sister", "(f)"),
            WordEntry("avô", "grandfather", "(m)"),
            WordEntry("avó", "grandmother", "(f)"),
            WordEntry("tio", "uncle", "(m)"),
            WordEntry("tia", "aunt", "(f)"),
            WordEntry("primo / prima", "cousin", "(m/f)"),
            WordEntry("marido", "husband", "(m)"),
            WordEntry("esposa", "wife", "(f)"),
            WordEntry("amigo / amiga", "friend", "(m/f)"),
            WordEntry("colega", "colleague", "(m/f)"),
            WordEntry("vizinho / vizinha", "neighbour", "(m/f)"),
            WordEntry("pessoa", "person", "(f)"),
            WordEntry("família", "family", "(f)")
        )
    ),
    WordCategory(
        "Body",
        listOf(
            WordEntry("cabeça", "head", "(f)"),
            WordEntry("rosto / cara", "face", "(m)"),
            WordEntry("olho", "eye", "(m)"),
            WordEntry("ouvido / orelha", "ear (inner / outer)", "(m)"),
            WordEntry("nariz", "nose", "(m)"),
            WordEntry("boca", "mouth", "(f)"),
            WordEntry("dente", "tooth", "(m)"),
            WordEntry("língua", "tongue", "(f)"),
            WordEntry("pescoço", "neck", "(m)"),
            WordEntry("ombro", "shoulder", "(m)"),
            WordEntry("braço", "arm", "(m)"),
            WordEntry("mão", "hand", "(f)"),
            WordEntry("dedo", "finger", "(m)"),
            WordEntry("peito", "chest", "(m)"),
            WordEntry("costas", "back", "(f)"),
            WordEntry("barriga", "belly / stomach", "(f)"),
            WordEntry("perna", "leg", "(f)"),
            WordEntry("joelho", "knee", "(m)"),
            WordEntry("pé", "foot", "(m)"),
            WordEntry("coração", "heart", "(m)"),
            WordEntry("pulmão", "lung", "(m)"),
            WordEntry("estômago", "stomach", "(m)"),
            WordEntry("pele", "skin", "(f)"),
            WordEntry("cabelo", "hair", "(m)")
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonWordsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Common Words") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(wordCategories) { category ->
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = category.title,
                            style = MaterialTheme.typography.titleMedium
                        )
                        category.entries.forEach { entry ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    modifier = Modifier.weight(1f),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = entry.pt,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontStyle = FontStyle.Italic
                                    )
                                    if (entry.gender.isNotEmpty()) {
                                        Text(
                                            text = " ${entry.gender}",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                                Text(
                                    text = entry.en,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
