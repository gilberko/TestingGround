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


private val wordCategories = listOf(
    DictCategory(
        "House / Home",
        listOf(
            DictWord("casa", "house / home", gender = "(f)"),
            DictWord("apartamento", "apartment / flat", gender = "(m)"),
            DictWord("quarto", "bedroom / room", gender = "(m)"),
            DictWord("sala", "living room", gender = "(f)"),
            DictWord("cozinha", "kitchen", gender = "(f)"),
            DictWord("casa de banho", "bathroom", gender = "(f)"),
            DictWord("jardim", "garden", gender = "(m)"),
            DictWord("garagem", "garage", gender = "(f)"),
            DictWord("janela", "window", gender = "(f)"),
            DictWord("porta", "door", gender = "(f)"),
            DictWord("parede", "wall", gender = "(f)"),
            DictWord("tecto", "ceiling", gender = "(m)"),
            DictWord("chão", "floor / ground", gender = "(m)"),
            DictWord("escadas", "stairs", gender = "(f)"),
            DictWord("cama", "bed", gender = "(f)"),
            DictWord("sofá", "sofa", gender = "(m)"),
            DictWord("mesa", "table", gender = "(f)"),
            DictWord("cadeira", "chair", gender = "(f)"),
            DictWord("armário", "wardrobe / cupboard", gender = "(m)"),
            DictWord("frigorífico", "fridge", gender = "(m)"),
            DictWord("fogão", "cooker / stove", gender = "(m)"),
            DictWord("chave", "key", gender = "(f)"),
            DictWord("lâmpada", "lamp / light bulb", gender = "(f)"),
            DictWord("cortina", "curtain", gender = "(f)"),
            DictWord("tapete", "carpet / rug", gender = "(m)"),
            DictWord("espelho", "mirror", gender = "(m)"),
            DictWord("toalha", "towel", gender = "(f)"),
            DictWord("almofada", "pillow / cushion", gender = "(f)"),
            DictWord("sanita", "toilet", gender = "(f)"),
            DictWord("elevador", "elevator / lift", gender = "(m)"),
            DictWord("congelador", "freezer", gender = "(m)"),
            DictWord("forno", "oven", gender = "(m)"),
            DictWord("micro-ondas", "microwave", gender = "(m)"),
            DictWord("lençóis", "sheets", gender = "(m pl)"),
            DictWord("máquina de lavar (roupa)", "washing machine", gender = "(f)"),
            DictWord("máquina de secar (roupa)", "tumble dryer", gender = "(f)"),
            DictWord("ar condicionado", "air conditioner", gender = "(m)")
        )
    ),
    DictCategory(
        "Home — Actions & Phrases",
        listOf(
            DictWord("Abrir a janela", "Open the window"),
            DictWord("Fechar a janela", "Close the window"),
            DictWord("Abrir a porta", "Open the door"),
            DictWord("Trancar a porta", "Lock the door"),
            DictWord("Lavar a louça", "Do the dishes"),
            DictWord("Limpar o quarto", "Clean the room"),
            DictWord("Mudar / trocar os lençóis", "Change the sheets"),
            DictWord("O apartamento fica no segundo andar.", "The apartment is on the second floor.")
        )
    ),
    DictCategory(
        "Yes, No & Uncertainty",
        listOf(
            DictWord("sim", "yes"),
            DictWord("não", "no"),
            DictWord("claro / com certeza", "sure / of course"),
            DictWord("talvez", "maybe (+ conjuntivo)"),
            DictWord("se calhar", "perhaps / probably (informal EP)"),
            DictWord("não sei", "I don't know"),
            DictWord("não tenho a certeza", "I'm not sure")
        )
    ),
    DictCategory(
        "Questions & Answers",
        listOf(
            DictWord("pergunta", "question", gender = "(f)"),
            DictWord("resposta", "answer", gender = "(f)"),
            DictWord("porquê? / porque é que…?", "why?"),
            DictWord("porque", "because"),
            DictWord("quando", "when"),
            DictWord("até quando?", "until when?"),
            DictWord("desde quando?", "since when?"),
            DictWord("onde", "where"),
            DictWord("para onde / aonde", "to where"),
            DictWord("de onde", "from where"),
            DictWord("como", "how"),
            DictWord("quanto / quanta", "how much"),
            DictWord("quantos / quantas", "how many"),
            DictWord("o que / o quê?", "what"),
            DictWord("O que é isto?", "What is this?")
        )
    ),
    DictCategory(
        "Quantity, Frequency & Negatives",
        listOf(
            DictWord("nem ___ nem ___", "neither ___ nor ___"),
            DictWord("nenhum destes / nenhuma destas", "neither / none of these"),
            DictWord("um destes / uma destas", "one of these"),
            DictWord("cada / todos os, todas as", "every"),
            DictWord("alguns / algumas", "a few / some"),
            DictWord("um pouco / um bocado", "a bit"),
            DictWord("muitos / muitas", "many"),
            DictWord("raramente", "rarely"),
            DictWord("muitas vezes / frequentemente", "often"),
            DictWord("sempre que / todas as vezes", "every time"),
            DictWord("todos os dias", "every day"),
            DictWord("sempre", "always"),
            DictWord("nunca", "never"),
            DictWord("nada", "nothing"),
            DictWord("em lado nenhum", "nowhere"),
            DictWord("ninguém", "no one / nobody")
        )
    ),
    DictCategory(
        "Food & Dining",
        listOf(
            DictWord("comida", "food", gender = "(f)"),
            DictWord("prato", "plate / dish", gender = "(m)"),
            DictWord("faca", "knife", gender = "(f)"),
            DictWord("garfo", "fork", gender = "(m)"),
            DictWord("colher", "spoon", gender = "(f)"),
            DictWord("copo", "glass / cup", gender = "(m)"),
            DictWord("garrafa", "bottle", gender = "(f)"),
            DictWord("água", "water", gender = "(f)"),
            DictWord("vinho", "wine", gender = "(m)"),
            DictWord("cerveja", "beer", gender = "(f)"),
            DictWord("pão", "bread", gender = "(m)"),
            DictWord("manteiga", "butter", gender = "(f)"),
            DictWord("queijo", "cheese", gender = "(m)"),
            DictWord("carne", "meat", gender = "(f)"),
            DictWord("peixe", "fish", gender = "(m)"),
            DictWord("frango", "chicken", gender = "(m)"),
            DictWord("ovo", "egg", gender = "(m)"),
            DictWord("arroz", "rice", gender = "(m)"),
            DictWord("batata", "potato", gender = "(f)"),
            DictWord("salada", "salad", gender = "(f)"),
            DictWord("sopa", "soup", gender = "(f)"),
            DictWord("fruta", "fruit", gender = "(f)"),
            DictWord("legumes", "vegetables", gender = "(m)"),
            DictWord("sal", "salt", gender = "(m)"),
            DictWord("açúcar", "sugar", gender = "(m)"),
            DictWord("azeite", "olive oil", gender = "(m)"),
            DictWord("café", "coffee", gender = "(m)"),
            DictWord("conta", "bill", gender = "(f)")
        )
    ),
    DictCategory(
        "Transport",
        listOf(
            DictWord("carro", "car", gender = "(m)"),
            DictWord("autocarro", "bus", gender = "(m)"),
            DictWord("comboio", "train", gender = "(m)"),
            DictWord("metro", "metro / underground", gender = "(m)"),
            DictWord("avião", "aeroplane", gender = "(m)"),
            DictWord("barco", "boat", gender = "(m)"),
            DictWord("bicicleta", "bicycle", gender = "(f)"),
            DictWord("mota", "motorbike", gender = "(f)"),
            DictWord("táxi", "taxi", gender = "(m)"),
            DictWord("paragem", "bus stop", gender = "(f)"),
            DictWord("estação", "station", gender = "(f)"),
            DictWord("aeroporto", "airport", gender = "(m)"),
            DictWord("bilhete", "ticket", gender = "(m)"),
            DictWord("passagem", "passage / ticket", gender = "(f)"),
            DictWord("bagagem", "luggage", gender = "(f)"),
            DictWord("mala", "suitcase / bag", gender = "(f)"),
            DictWord("mapa", "map", gender = "(m)"),
            DictWord("semáforo", "traffic light", gender = "(m)"),
            DictWord("estrada", "road", gender = "(f)"),
            DictWord("rua", "street", gender = "(f)"),
            DictWord("avenida", "avenue", gender = "(f)"),
            DictWord("ponte", "bridge", gender = "(f)"),
            DictWord("gasolina", "petrol", gender = "(f)"),
            DictWord("parque de estacionamento", "car park", gender = "(m)")
        )
    ),
    DictCategory(
        "Entertainment",
        listOf(
            DictWord("televisão", "television", gender = "(f)"),
            DictWord("filme", "film", gender = "(m)"),
            DictWord("música", "music", gender = "(f)"),
            DictWord("livro", "book", gender = "(m)"),
            DictWord("jornal", "newspaper", gender = "(m)"),
            DictWord("revista", "magazine", gender = "(f)"),
            DictWord("concerto", "concert", gender = "(m)"),
            DictWord("teatro", "theatre", gender = "(m)"),
            DictWord("cinema", "cinema", gender = "(m)"),
            DictWord("jogo", "game", gender = "(m)"),
            DictWord("computador", "computer", gender = "(m)"),
            DictWord("telemóvel", "mobile phone", gender = "(m)"),
            DictWord("internet", "internet", gender = "(f)"),
            DictWord("canção", "song", gender = "(f)"),
            DictWord("rádio", "radio", gender = "(m)"),
            DictWord("dança", "dance", gender = "(f)"),
            DictWord("festa", "party", gender = "(f)"),
            DictWord("férias", "holidays / vacation", gender = "(f)"),
            DictWord("passeio", "walk / outing", gender = "(m)"),
            DictWord("praia", "beach", gender = "(f)"),
            DictWord("piscina", "swimming pool", gender = "(f)"),
            DictWord("desporto", "sport", gender = "(m)"),
            DictWord("futebol", "football", gender = "(m)")
        )
    ),
    DictCategory(
        "Places",
        listOf(
            DictWord("hotel", "hotel", gender = "(m)"),
            DictWord("restaurante", "restaurant", gender = "(m)"),
            DictWord("hospital", "hospital", gender = "(m)"),
            DictWord("banco", "bank", gender = "(m)"),
            DictWord("farmácia", "pharmacy", gender = "(f)"),
            DictWord("supermercado", "supermarket", gender = "(m)"),
            DictWord("mercado", "market", gender = "(m)"),
            DictWord("loja", "shop / store", gender = "(f)"),
            DictWord("escola", "school", gender = "(f)"),
            DictWord("universidade", "university", gender = "(f)"),
            DictWord("biblioteca", "library", gender = "(f)"),
            DictWord("museu", "museum", gender = "(m)"),
            DictWord("igreja", "church", gender = "(f)"),
            DictWord("correios", "post office", gender = "(m)"),
            DictWord("polícia", "police", gender = "(f)"),
            DictWord("câmara municipal", "town hall", gender = "(f)"),
            DictWord("cidade", "city", gender = "(f)"),
            DictWord("vila", "town", gender = "(f)"),
            DictWord("aldeia", "village", gender = "(f)"),
            DictWord("país", "country", gender = "(m)"),
            DictWord("campo", "countryside / field", gender = "(m)"),
            DictWord("parque", "park", gender = "(m)"),
            DictWord("praça", "square / plaza", gender = "(f)")
        )
    ),
    DictCategory(
        "People & Family",
        listOf(
            DictWord("homem", "man", gender = "(m)"),
            DictWord("mulher", "woman", gender = "(f)"),
            DictWord("criança", "child", gender = "(f)"),
            DictWord("bebé", "baby", gender = "(m)"),
            DictWord("rapaz", "boy", gender = "(m)"),
            DictWord("rapariga", "girl", gender = "(f)"),
            DictWord("pai", "father", gender = "(m)"),
            DictWord("mãe", "mother", gender = "(f)"),
            DictWord("filho", "son", gender = "(m)"),
            DictWord("filha", "daughter", gender = "(f)"),
            DictWord("irmão", "brother", gender = "(m)"),
            DictWord("irmã", "sister", gender = "(f)"),
            DictWord("avô", "grandfather", gender = "(m)"),
            DictWord("avó", "grandmother", gender = "(f)"),
            DictWord("tio", "uncle", gender = "(m)"),
            DictWord("tia", "aunt", gender = "(f)"),
            DictWord("primo / prima", "cousin", gender = "(m/f)"),
            DictWord("marido", "husband", gender = "(m)"),
            DictWord("esposa", "wife", gender = "(f)"),
            DictWord("amigo / amiga", "friend", gender = "(m/f)"),
            DictWord("colega", "colleague", gender = "(m/f)"),
            DictWord("vizinho / vizinha", "neighbour", gender = "(m/f)"),
            DictWord("pessoa", "person", gender = "(f)"),
            DictWord("família", "family", gender = "(f)")
        )
    ),
    DictCategory(
        "Body",
        listOf(
            DictWord("cabeça", "head", gender = "(f)"),
            DictWord("rosto / cara", "face", gender = "(m)"),
            DictWord("olho", "eye", gender = "(m)"),
            DictWord("ouvido / orelha", "ear (inner / outer)", gender = "(m)"),
            DictWord("nariz", "nose", gender = "(m)"),
            DictWord("boca", "mouth", gender = "(f)"),
            DictWord("dente", "tooth", gender = "(m)"),
            DictWord("língua", "tongue", gender = "(f)"),
            DictWord("pescoço", "neck", gender = "(m)"),
            DictWord("ombro", "shoulder", gender = "(m)"),
            DictWord("braço", "arm", gender = "(m)"),
            DictWord("mão", "hand", gender = "(f)"),
            DictWord("dedo", "finger", gender = "(m)"),
            DictWord("peito", "chest", gender = "(m)"),
            DictWord("costas", "back", gender = "(f)"),
            DictWord("barriga", "belly / stomach", gender = "(f)"),
            DictWord("perna", "leg", gender = "(f)"),
            DictWord("joelho", "knee", gender = "(m)"),
            DictWord("pé", "foot", gender = "(m)"),
            DictWord("coração", "heart", gender = "(m)"),
            DictWord("pulmão", "lung", gender = "(m)"),
            DictWord("estômago", "stomach", gender = "(m)"),
            DictWord("pele", "skin", gender = "(f)"),
            DictWord("cabelo", "hair", gender = "(m)")
        )
    ),
    DictCategory(
        "Writing & Punctuation",
        listOf(
            DictWord("o texto", "text", gender = "(m)"),
            DictWord("a história", "story", gender = "(f)"),
            DictWord("a piada", "joke", note = "anedota (f) is also common in EP; contar uma piada = to tell a joke", gender = "(f)"),
            DictWord("o parágrafo", "paragraph", gender = "(m)"),
            DictWord("a carta", "letter (mail)", note = "o correio = the mail / post office", gender = "(f)"),
            DictWord("a letra", "letter (of the alphabet)", note = "also handwriting and song lyrics", gender = "(f)"),
            DictWord("a palavra", "word", gender = "(f)"),
            DictWord("a frase", "sentence", gender = "(f)"),
            DictWord("o ponto", "point / dot", gender = "(m)"),
            DictWord("o ponto final", "full stop / period", gender = "(m)"),
            DictWord("a vírgula", "comma", note = "also the decimal point: 2,5 = dois vírgula cinco", gender = "(f)"),
            DictWord("o ponto de exclamação", "exclamation mark", gender = "(m)"),
            DictWord("o ponto de interrogação", "question mark", gender = "(m)"),
            DictWord("os parênteses", "parentheses / brackets", note = "singular: o parêntese (or o parêntesis)", gender = "(m pl)"),
            DictWord("as aspas", "quotation marks", note = "entre aspas = in quotes", gender = "(f pl)"),
            DictWord("os dois pontos", "colon ( : )", gender = "(m pl)"),
            DictWord("o ponto e vírgula", "semicolon ( ; )", gender = "(m)")
        )
    )
)

@Composable
fun CommonWordsScreen(onBack: () -> Unit) {
    DictScreenScaffold(title = "Common Words", onBack = onBack) {
        items(wordCategories) { DictCategoryCard(it) }
    }
}
