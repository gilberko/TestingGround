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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


private val hiCategories = listOf(
    DictCategory(
        "The House & Rooms",
        listOf(
            DictWord("a casa", "house"),
            DictWord("o lar / a casa", "home", "lar = home in the emotional sense; in everyday speech \"casa\" covers both (vou para casa = I'm going home)"),
            DictWord("o apartamento", "apartment / flat"),
            DictWord("o quarto", "bedroom / room"),
            DictWord("a divisão", "room (any room of a house)", "used in listings: \"um T2 com quatro divisões\""),
            DictWord("a sala (de estar)", "living room"),
            DictWord("a cozinha", "kitchen"),
            DictWord("a casa de banho", "bathroom"),
            DictWord("o corredor", "corridor / hallway"),
            DictWord("as escadas", "stairs"),
            DictWord("o elevador", "elevator / lift"),
            DictWord("a porta", "door"),
            DictWord("a janela", "window"),
            DictWord("o chão", "floor (the surface you walk on)"),
            DictWord("o andar", "floor / storey (level of a building)"),
            DictWord("o rés-do-chão", "ground floor"),
            DictWord("o segundo andar", "2nd floor", "EP counts from the rés-do-chão (ground floor), so o segundo andar is two levels above the ground — what Americans call the 3rd floor"),
            DictWord("a varanda", "balcony"),
            DictWord("o sótão", "attic"),
            DictWord("a cave", "basement / cellar"),
            DictWord("a adega", "wine cellar", "also a winery; a cave is the general basement / cellar"),
            DictWord("o estacionamento", "parking"),
            DictWord("o lugar de garagem", "parking space (in a building's garage)")
        )
    ),
    DictCategory(
        "Bathroom",
        listOf(
            DictWord("a sanita", "toilet (the fixture)"),
            DictWord("a casa de banho / o WC", "toilets / restroom", "public toilets: as casas de banho; signs often say WC"),
            DictWord("o chuveiro", "shower (the fixture / showerhead)"),
            DictWord("o duche", "shower (the act)", "tomar um duche = to take a shower"),
            DictWord("a banheira", "bathtub"),
            DictWord("o lavatório", "bathroom sink / washbasin"),
            DictWord("a torneira", "faucet / tap")
        )
    ),
    DictCategory(
        "Kitchen & Appliances",
        listOf(
            DictWord("o frigorífico", "refrigerator / fridge", "BP: geladeira"),
            DictWord("o congelador", "freezer"),
            DictWord("o forno", "oven"),
            DictWord("o mini-forno / o forno elétrico", "toaster oven"),
            DictWord("o micro-ondas", "microwave"),
            DictWord("o lava-loiça", "kitchen sink", "EP spelling loiça; BP pia"),
            DictWord("a máquina de lavar loiça", "dishwasher"),
            DictWord("o ar condicionado", "air conditioner"),
            DictWord("o aquecimento central", "central heating"),
            DictWord("a ventoinha", "fan (electric, blows air)", "BP: ventilador"),
            DictWord("a tomada", "power outlet / socket")
        )
    ),
    DictCategory(
        "Furniture",
        listOf(
            DictWord("a cama", "bed"),
            DictWord("o sofá", "sofa"),
            DictWord("a mesa", "table"),
            DictWord("a cadeira", "chair"),
            DictWord("o roupeiro / o guarda-roupa", "wardrobe"),
            DictWord("o armário", "cabinet / cupboard"),
            DictWord("o armário embutido", "closet (built-in)"),
            DictWord("a gaveta", "drawer")
        )
    ),
    DictCategory(
        "Floors, Walls & Decor",
        listOf(
            DictWord("o soalho / o chão de madeira", "wooden floor"),
            DictWord("os mosaicos / os ladrilhos", "floor tiles", "azulejo is the glazed (often decorative) wall tile"),
            DictWord("o tapete", "carpet / rug"),
            DictWord("a alcatifa", "wall-to-wall carpet", "BP: carpete"),
            DictWord("a tapeçaria", "tapestry"),
            DictWord("as cortinas", "curtains"),
            DictWord("os cortinados", "drapes / curtains (the finished curtain panels)", "EP: the usual shop word; cortinas and cortinados are largely interchangeable"),
            DictWord("os estores", "blinds / roller shutters", "EP; BP: persianas"),
            DictWord("o quadro / a pintura", "a painting (artwork)", "pintura also means the act of painting / the paintwork")
        )
    ),
    DictCategory(
        "Outside",
        listOf(
            DictWord("o jardim", "garden"),
            DictWord("o quintal", "yard"),
            DictWord("o quintal das traseiras", "back yard", "as traseiras = the back of a building"),
            DictWord("a relva", "grass / lawn", "BP: grama"),
            DictWord("a flor", "flower"),
            DictWord("a árvore", "tree"),
            DictWord("o arbusto", "bush / shrub"),
            DictWord("a sebe", "hedge"),
            DictWord("a vedação / a cerca", "fence"),
            DictWord("a areia", "sand")
        )
    ),
    DictCategory(
        "Problems in the House",
        listOf(
            DictWord("cano rebentado", "burst pipe"),
            DictWord("cano entupido", "clogged pipe"),
            DictWord("tinta a descascar", "peeling paint"),
            DictWord("bolor / mofo", "mould"),
            DictWord("fissura / rachadura", "crack"),
            DictWord("infiltração", "leak (roof or wall)"),
            DictWord("goteira", "drip / roof leak"),
            DictWord("humidade", "damp / moisture"),
            DictWord("azulejos partidos", "broken tiles"),
            DictWord("curto-circuito", "short circuit"),
            DictWord("entupimento", "blockage / clog"),
            DictWord("danos estruturais", "structural damage")
        )
    ),
    DictCategory(
        "Renovation & Tradespeople",
        listOf(
            DictWord("obras", "building works / renovation", "estar em obras = to be under renovation"),
            DictWord("a renovação", "renovation"),
            DictWord("renovar", "to renovate"),
            DictWord("remodelação", "refurbishment / remodelling"),
            DictWord("empreiteiro", "contractor"),
            DictWord("canalizador", "plumber"),
            DictWord("eletricista", "electrician"),
            DictWord("pedreiro", "bricklayer / mason"),
            DictWord("carpinteiro", "carpenter"),
            DictWord("orçamento", "quote / estimate"),
            DictWord("licença de obras", "building permit"),
            DictWord("reparação", "repair"),
            DictWord("substituição", "replacement")
        )
    ),
    DictCategory(
        "Painting",
        listOf(
            DictWord("pintar", "to paint"),
            DictWord("tinta", "paint"),
            DictWord("rolo", "roller"),
            DictWord("pincel", "brush"),
            DictWord("lixa", "sandpaper"),
            DictWord("lixar", "to sand"),
            DictWord("escada", "ladder"),
            DictWord("andaime", "scaffolding"),
            DictWord("primário", "primer"),
            DictWord("acabamento", "finish / topcoat"),
            DictWord("verniz", "varnish"),
            DictWord("diluente", "thinner / solvent")
        )
    ),
    DictCategory(
        "Materials & Structure",
        listOf(
            DictWord("azulejo", "tile"),
            DictWord("ladrilhar", "to tile"),
            DictWord("gesso", "plaster"),
            DictWord("tijolo", "brick"),
            DictWord("cimento", "cement"),
            DictWord("betão", "concrete"),
            DictWord("madeira", "wood / timber"),
            DictWord("piso / pavimento", "floor / flooring"),
            DictWord("telhado", "roof"),
            DictWord("parede", "wall"),
            DictWord("tecto / teto", "ceiling"),
            DictWord("viga", "beam"),
            DictWord("pilar", "pillar / column"),
            DictWord("isolamento", "insulation")
        )
    ),
    DictCategory(
        "Real Estate, Renting & Buying",
        listOf(
            DictWord("o imobiliário / o setor imobiliário", "real estate (the field / market)"),
            DictWord("o imóvel / os imóveis", "property / real estate (a building, flat or land)", "the standard word in listings and contracts"),
            DictWord("a propriedade", "property (something owned; also land / an estate)"),
            DictWord("a região / a zona", "region / area", "zona is the everyday word for a neighbourhood area: \"uma zona calma\""),
            DictWord("o bairro", "neighbourhood"),
            DictWord("o agente imobiliário / a agente imobiliária", "real estate agent / realtor"),
            DictWord("o consultor imobiliário", "real estate consultant", "the title many Portuguese agencies give their agents"),
            DictWord("a imobiliária / a agência imobiliária", "real estate agency"),
            DictWord("o advogado / a advogada", "lawyer"),
            DictWord("o notário", "notary", "the escritura (deed of sale) is signed before a notary or other authorised official"),
            DictWord("a escritura", "deed of sale"),
            DictWord("o contrato", "contract"),
            DictWord("o contrato de arrendamento", "rental agreement / lease contract"),
            DictWord("o arrendamento", "lease / renting", "arrendar = to let or rent (long-term)"),
            DictWord("a renda", "rent (the monthly payment)", "EP; BP: o aluguel. \"Quanto é a renda?\""),
            DictWord("arrendar / alugar", "to rent / to let", "alugar is also used for cars and short stays"),
            DictWord("o senhorio / a senhoria", "landlord / landlady"),
            DictWord("o inquilino / a inquilina", "tenant"),
            DictWord("a caução", "security deposit"),
            DictWord("comprar casa", "to buy a home"),
            DictWord("o crédito à habitação / o crédito habitação", "mortgage (home loan)", "the everyday EP term; both spellings are common"),
            DictWord("a hipoteca", "mortgage (the legal charge on the property)"),
            DictWord("o empréstimo", "loan"),
            DictWord("o banco", "bank"),
            DictWord("as comodidades", "amenities"),
            DictWord("a vista", "the view", "vista mar = sea view, vista rio = river view"),
            DictWord("Tem vista?", "Is there a view? / Does it have a view?"),
            DictWord("Tem vista para o mar?", "Does it have a sea view?"),
            DictWord("T0 / T1 / T2 / T3", "studio / 1-bed / 2-bed / 3-bed flat", "EP listing code: T + number of bedrooms")
        )
    )
)

@Composable
fun HomeImprovementScreen(onBack: () -> Unit) {
    DictScreenScaffold(title = "Home And Home Improvement", onBack = onBack) {
        items(hiCategories) { DictCategoryCard(it) }
    }
}
