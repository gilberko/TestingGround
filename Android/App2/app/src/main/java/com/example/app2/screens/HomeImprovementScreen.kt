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

private data class HiEntry(val pt: String, val en: String, val note: String = "")
private data class HiCategory(val title: String, val entries: List<HiEntry>)

private val hiCategories = listOf(
    HiCategory(
        "The House & Rooms",
        listOf(
            HiEntry("a casa", "house"),
            HiEntry("o lar / a casa", "home", "lar = home in the emotional sense; in everyday speech \"casa\" covers both (vou para casa = I'm going home)"),
            HiEntry("o apartamento", "apartment / flat"),
            HiEntry("o quarto", "bedroom / room"),
            HiEntry("a divisão", "room (any room of a house)", "used in listings: \"um T2 com quatro divisões\""),
            HiEntry("a sala (de estar)", "living room"),
            HiEntry("a cozinha", "kitchen"),
            HiEntry("a casa de banho", "bathroom"),
            HiEntry("o corredor", "corridor / hallway"),
            HiEntry("as escadas", "stairs"),
            HiEntry("o elevador", "elevator / lift"),
            HiEntry("a porta", "door"),
            HiEntry("a janela", "window"),
            HiEntry("o chão", "floor (the surface you walk on)"),
            HiEntry("o andar", "floor / storey (level of a building)"),
            HiEntry("o rés-do-chão", "ground floor"),
            HiEntry("o segundo andar", "2nd floor", "EP counts from the rés-do-chão (ground floor), so o segundo andar is two levels above the ground — what Americans call the 3rd floor"),
            HiEntry("a varanda", "balcony"),
            HiEntry("o sótão", "attic"),
            HiEntry("a cave", "basement / cellar"),
            HiEntry("o estacionamento", "parking"),
            HiEntry("o lugar de garagem", "parking space (in a building's garage)")
        )
    ),
    HiCategory(
        "Bathroom",
        listOf(
            HiEntry("a sanita", "toilet (the fixture)"),
            HiEntry("a casa de banho / o WC", "toilets / restroom", "public toilets: as casas de banho; signs often say WC"),
            HiEntry("o chuveiro", "shower (the fixture / showerhead)"),
            HiEntry("o duche", "shower (the act)", "tomar um duche = to take a shower"),
            HiEntry("a banheira", "bathtub"),
            HiEntry("o lavatório", "bathroom sink / washbasin"),
            HiEntry("a torneira", "faucet / tap")
        )
    ),
    HiCategory(
        "Kitchen & Appliances",
        listOf(
            HiEntry("o frigorífico", "refrigerator / fridge", "BP: geladeira"),
            HiEntry("o congelador", "freezer"),
            HiEntry("o forno", "oven"),
            HiEntry("o mini-forno / o forno elétrico", "toaster oven"),
            HiEntry("o micro-ondas", "microwave"),
            HiEntry("o lava-loiça", "kitchen sink", "EP spelling loiça; BP pia"),
            HiEntry("a máquina de lavar loiça", "dishwasher"),
            HiEntry("o ar condicionado", "air conditioner"),
            HiEntry("o aquecimento central", "central heating"),
            HiEntry("a ventoinha", "fan (electric, blows air)", "BP: ventilador"),
            HiEntry("a tomada", "power outlet / socket")
        )
    ),
    HiCategory(
        "Furniture",
        listOf(
            HiEntry("a cama", "bed"),
            HiEntry("o sofá", "sofa"),
            HiEntry("a mesa", "table"),
            HiEntry("a cadeira", "chair"),
            HiEntry("o roupeiro / o guarda-roupa", "wardrobe"),
            HiEntry("o armário", "cabinet / cupboard"),
            HiEntry("o armário embutido", "closet (built-in)"),
            HiEntry("a gaveta", "drawer")
        )
    ),
    HiCategory(
        "Floors, Walls & Decor",
        listOf(
            HiEntry("o soalho / o chão de madeira", "wooden floor"),
            HiEntry("os mosaicos / os ladrilhos", "floor tiles", "azulejo is the glazed (often decorative) wall tile"),
            HiEntry("o tapete", "carpet / rug"),
            HiEntry("a alcatifa", "wall-to-wall carpet", "BP: carpete"),
            HiEntry("a tapeçaria", "tapestry"),
            HiEntry("o quadro / a pintura", "a painting (artwork)", "pintura also means the act of painting / the paintwork")
        )
    ),
    HiCategory(
        "Outside",
        listOf(
            HiEntry("o jardim", "garden"),
            HiEntry("o quintal", "yard"),
            HiEntry("o quintal das traseiras", "back yard", "as traseiras = the back of a building"),
            HiEntry("a relva", "grass / lawn", "BP: grama"),
            HiEntry("a flor", "flower"),
            HiEntry("a árvore", "tree"),
            HiEntry("o arbusto", "bush / shrub"),
            HiEntry("a sebe", "hedge"),
            HiEntry("a vedação / a cerca", "fence"),
            HiEntry("a areia", "sand")
        )
    ),
    HiCategory(
        "Problems in the House",
        listOf(
            HiEntry("cano rebentado", "burst pipe"),
            HiEntry("cano entupido", "clogged pipe"),
            HiEntry("tinta a descascar", "peeling paint"),
            HiEntry("bolor / mofo", "mould"),
            HiEntry("fissura / rachadura", "crack"),
            HiEntry("infiltração", "leak (roof or wall)"),
            HiEntry("goteira", "drip / roof leak"),
            HiEntry("humidade", "damp / moisture"),
            HiEntry("azulejos partidos", "broken tiles"),
            HiEntry("curto-circuito", "short circuit"),
            HiEntry("entupimento", "blockage / clog"),
            HiEntry("danos estruturais", "structural damage")
        )
    ),
    HiCategory(
        "Renovation & Tradespeople",
        listOf(
            HiEntry("obras", "building works / renovation"),
            HiEntry("renovar", "to renovate"),
            HiEntry("remodelação", "refurbishment / remodelling"),
            HiEntry("empreiteiro", "contractor"),
            HiEntry("canalizador", "plumber"),
            HiEntry("eletricista", "electrician"),
            HiEntry("pedreiro", "bricklayer / mason"),
            HiEntry("carpinteiro", "carpenter"),
            HiEntry("orçamento", "quote / estimate"),
            HiEntry("licença de obras", "building permit"),
            HiEntry("reparação", "repair"),
            HiEntry("substituição", "replacement")
        )
    ),
    HiCategory(
        "Painting",
        listOf(
            HiEntry("pintar", "to paint"),
            HiEntry("tinta", "paint"),
            HiEntry("rolo", "roller"),
            HiEntry("pincel", "brush"),
            HiEntry("lixa", "sandpaper"),
            HiEntry("lixar", "to sand"),
            HiEntry("escada", "ladder"),
            HiEntry("andaime", "scaffolding"),
            HiEntry("primário", "primer"),
            HiEntry("acabamento", "finish / topcoat"),
            HiEntry("verniz", "varnish"),
            HiEntry("diluente", "thinner / solvent")
        )
    ),
    HiCategory(
        "Materials & Structure",
        listOf(
            HiEntry("azulejo", "tile"),
            HiEntry("ladrilhar", "to tile"),
            HiEntry("gesso", "plaster"),
            HiEntry("tijolo", "brick"),
            HiEntry("cimento", "cement"),
            HiEntry("betão", "concrete"),
            HiEntry("madeira", "wood / timber"),
            HiEntry("piso / pavimento", "floor / flooring"),
            HiEntry("telhado", "roof"),
            HiEntry("parede", "wall"),
            HiEntry("tecto / teto", "ceiling"),
            HiEntry("viga", "beam"),
            HiEntry("pilar", "pillar / column"),
            HiEntry("isolamento", "insulation")
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeImprovementScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home And Home Improvement") },
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
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(hiCategories, key = { it.title }) { category ->
                HiCategoryCard(category)
            }
        }
    }
}

@Composable
private fun HiCategoryCard(category: HiCategory) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = category.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            category.entries.forEach { entry ->
                HiRow(entry)
            }
        }
    }
}

@Composable
private fun HiRow(entry: HiEntry) {
    Column(modifier = Modifier.padding(vertical = 2.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = entry.pt,
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = entry.en,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.weight(1f)
            )
        }
        if (entry.note.isNotEmpty()) {
            Text(
                text = entry.note,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}
