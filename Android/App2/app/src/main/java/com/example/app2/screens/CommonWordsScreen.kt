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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp

private data class WordEntry(val pt: String, val en: String)
private data class WordCategory(val title: String, val entries: List<WordEntry>)

private val wordCategories = listOf(
    WordCategory(
        "House / Home",
        listOf(
            WordEntry("casa", "house / home"),
            WordEntry("apartamento", "apartment / flat"),
            WordEntry("quarto", "bedroom / room"),
            WordEntry("sala", "living room"),
            WordEntry("cozinha", "kitchen"),
            WordEntry("casa de banho", "bathroom"),
            WordEntry("jardim", "garden"),
            WordEntry("garagem", "garage"),
            WordEntry("janela", "window"),
            WordEntry("porta", "door"),
            WordEntry("parede", "wall"),
            WordEntry("tecto", "ceiling"),
            WordEntry("chão", "floor / ground"),
            WordEntry("escadas", "stairs"),
            WordEntry("cama", "bed"),
            WordEntry("sofá", "sofa"),
            WordEntry("mesa", "table"),
            WordEntry("cadeira", "chair"),
            WordEntry("armário", "wardrobe / cupboard"),
            WordEntry("frigorífico", "fridge"),
            WordEntry("fogão", "cooker / stove"),
            WordEntry("chave", "key"),
            WordEntry("lâmpada", "lamp / light bulb"),
            WordEntry("cortina", "curtain"),
            WordEntry("tapete", "carpet / rug"),
            WordEntry("espelho", "mirror"),
            WordEntry("toalha", "towel"),
            WordEntry("almofada", "pillow / cushion")
        )
    ),
    WordCategory(
        "Food & Dining",
        listOf(
            WordEntry("comida", "food"),
            WordEntry("prato", "plate / dish"),
            WordEntry("faca", "knife"),
            WordEntry("garfo", "fork"),
            WordEntry("colher", "spoon"),
            WordEntry("copo", "glass / cup"),
            WordEntry("garrafa", "bottle"),
            WordEntry("água", "water"),
            WordEntry("vinho", "wine"),
            WordEntry("cerveja", "beer"),
            WordEntry("pão", "bread"),
            WordEntry("manteiga", "butter"),
            WordEntry("queijo", "cheese"),
            WordEntry("carne", "meat"),
            WordEntry("peixe", "fish"),
            WordEntry("frango", "chicken"),
            WordEntry("ovo", "egg"),
            WordEntry("arroz", "rice"),
            WordEntry("batata", "potato"),
            WordEntry("salada", "salad"),
            WordEntry("sopa", "soup"),
            WordEntry("fruta", "fruit"),
            WordEntry("legumes", "vegetables"),
            WordEntry("sal", "salt"),
            WordEntry("açúcar", "sugar"),
            WordEntry("azeite", "olive oil"),
            WordEntry("café", "coffee"),
            WordEntry("conta", "bill")
        )
    ),
    WordCategory(
        "Transport",
        listOf(
            WordEntry("carro", "car"),
            WordEntry("autocarro", "bus"),
            WordEntry("comboio", "train"),
            WordEntry("metro", "metro / underground"),
            WordEntry("avião", "aeroplane"),
            WordEntry("barco", "boat"),
            WordEntry("bicicleta", "bicycle"),
            WordEntry("mota", "motorbike"),
            WordEntry("táxi", "taxi"),
            WordEntry("paragem", "bus stop"),
            WordEntry("estação", "station"),
            WordEntry("aeroporto", "airport"),
            WordEntry("bilhete", "ticket"),
            WordEntry("passagem", "passage / ticket"),
            WordEntry("bagagem", "luggage"),
            WordEntry("mala", "suitcase / bag"),
            WordEntry("mapa", "map"),
            WordEntry("semáforo", "traffic light"),
            WordEntry("estrada", "road"),
            WordEntry("rua", "street"),
            WordEntry("avenida", "avenue"),
            WordEntry("ponte", "bridge"),
            WordEntry("gasolina", "petrol"),
            WordEntry("parque de estacionamento", "car park")
        )
    ),
    WordCategory(
        "Entertainment",
        listOf(
            WordEntry("televisão", "television"),
            WordEntry("filme", "film"),
            WordEntry("música", "music"),
            WordEntry("livro", "book"),
            WordEntry("jornal", "newspaper"),
            WordEntry("revista", "magazine"),
            WordEntry("concerto", "concert"),
            WordEntry("teatro", "theatre"),
            WordEntry("cinema", "cinema"),
            WordEntry("jogo", "game"),
            WordEntry("computador", "computer"),
            WordEntry("telemóvel", "mobile phone"),
            WordEntry("internet", "internet"),
            WordEntry("canção", "song"),
            WordEntry("rádio", "radio"),
            WordEntry("dança", "dance"),
            WordEntry("festa", "party"),
            WordEntry("férias", "holidays / vacation"),
            WordEntry("passeio", "walk / outing"),
            WordEntry("praia", "beach"),
            WordEntry("piscina", "swimming pool"),
            WordEntry("desporto", "sport"),
            WordEntry("futebol", "football")
        )
    ),
    WordCategory(
        "Places",
        listOf(
            WordEntry("hotel", "hotel"),
            WordEntry("restaurante", "restaurant"),
            WordEntry("hospital", "hospital"),
            WordEntry("banco", "bank"),
            WordEntry("farmácia", "pharmacy"),
            WordEntry("supermercado", "supermarket"),
            WordEntry("mercado", "market"),
            WordEntry("loja", "shop / store"),
            WordEntry("escola", "school"),
            WordEntry("universidade", "university"),
            WordEntry("biblioteca", "library"),
            WordEntry("museu", "museum"),
            WordEntry("igreja", "church"),
            WordEntry("correios", "post office"),
            WordEntry("polícia", "police"),
            WordEntry("câmara municipal", "town hall"),
            WordEntry("cidade", "city"),
            WordEntry("vila", "town"),
            WordEntry("aldeia", "village"),
            WordEntry("país", "country"),
            WordEntry("campo", "countryside / field"),
            WordEntry("parque", "park"),
            WordEntry("praça", "square / plaza")
        )
    ),
    WordCategory(
        "People & Family",
        listOf(
            WordEntry("homem", "man"),
            WordEntry("mulher", "woman"),
            WordEntry("criança", "child"),
            WordEntry("bebé", "baby"),
            WordEntry("rapaz", "boy"),
            WordEntry("rapariga", "girl"),
            WordEntry("pai", "father"),
            WordEntry("mãe", "mother"),
            WordEntry("filho", "son"),
            WordEntry("filha", "daughter"),
            WordEntry("irmão", "brother"),
            WordEntry("irmã", "sister"),
            WordEntry("avô", "grandfather"),
            WordEntry("avó", "grandmother"),
            WordEntry("tio", "uncle"),
            WordEntry("tia", "aunt"),
            WordEntry("primo / prima", "cousin"),
            WordEntry("marido", "husband"),
            WordEntry("esposa", "wife"),
            WordEntry("amigo / amiga", "friend"),
            WordEntry("colega", "colleague"),
            WordEntry("vizinho / vizinha", "neighbour"),
            WordEntry("pessoa", "person"),
            WordEntry("família", "family")
        )
    ),
    WordCategory(
        "Body",
        listOf(
            WordEntry("cabeça", "head"),
            WordEntry("rosto / cara", "face"),
            WordEntry("olho", "eye"),
            WordEntry("ouvido / orelha", "ear (inner / outer)"),
            WordEntry("nariz", "nose"),
            WordEntry("boca", "mouth"),
            WordEntry("dente", "tooth"),
            WordEntry("língua", "tongue"),
            WordEntry("pescoço", "neck"),
            WordEntry("ombro", "shoulder"),
            WordEntry("braço", "arm"),
            WordEntry("mão", "hand"),
            WordEntry("dedo", "finger"),
            WordEntry("peito", "chest"),
            WordEntry("costas", "back"),
            WordEntry("barriga", "belly / stomach"),
            WordEntry("perna", "leg"),
            WordEntry("joelho", "knee"),
            WordEntry("pé", "foot"),
            WordEntry("coração", "heart"),
            WordEntry("pulmão", "lung"),
            WordEntry("estômago", "stomach"),
            WordEntry("pele", "skin"),
            WordEntry("cabelo", "hair")
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
                                Text(
                                    text = entry.pt,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontStyle = FontStyle.Italic,
                                    modifier = Modifier.weight(1f)
                                )
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
