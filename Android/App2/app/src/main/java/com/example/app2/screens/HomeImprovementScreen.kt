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

private data class HiEntry(val pt: String, val en: String)
private data class HiCategory(val title: String, val entries: List<HiEntry>)

private val hiCategories = listOf(
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
                title = { Text("Home Improvement") },
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
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
}
