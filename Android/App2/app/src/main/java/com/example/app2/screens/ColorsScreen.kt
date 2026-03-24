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

private data class ColorEntry(val pt: String, val en: String, val note: String = "")
private data class ColorCategory(val title: String, val entries: List<ColorEntry>)

private val colorCategories = listOf(
    ColorCategory(
        "Basic Colors",
        listOf(
            ColorEntry("vermelho / vermelha", "red", "gender-variable"),
            ColorEntry("azul", "blue", "invariable"),
            ColorEntry("verde", "green", "invariable"),
            ColorEntry("amarelo / amarela", "yellow", "gender-variable"),
            ColorEntry("laranja", "orange", "invariable"),
            ColorEntry("roxo / roxa", "purple", "gender-variable"),
            ColorEntry("cor-de-rosa", "pink", "invariable"),
            ColorEntry("castanho / castanha", "brown", "gender-variable"),
            ColorEntry("preto / preta", "black", "gender-variable"),
            ColorEntry("branco / branca", "white", "gender-variable"),
            ColorEntry("cinzento / cinzenta", "grey", "gender-variable"),
            ColorEntry("cinza", "grey (alt.)", "invariable, informal")
        )
    ),
    ColorCategory(
        "Shades & Modifiers",
        listOf(
            ColorEntry("claro / clara", "light (e.g. azul claro)", "gender-variable"),
            ColorEntry("escuro / escura", "dark (e.g. verde escuro)", "gender-variable"),
            ColorEntry("vivo / viva", "bright", "gender-variable"),
            ColorEntry("pálido / pálida", "pale", "gender-variable")
        )
    ),
    ColorCategory(
        "More Colors",
        listOf(
            ColorEntry("bege", "beige", "invariable"),
            ColorEntry("bordeaux", "burgundy / wine", "invariable"),
            ColorEntry("dourado / dourada", "golden", "gender-variable"),
            ColorEntry("prateado / prateada", "silver", "gender-variable"),
            ColorEntry("creme", "cream", "invariable"),
            ColorEntry("turquesa", "turquoise", "invariable"),
            ColorEntry("lilás", "lilac", "invariable"),
            ColorEntry("índigo", "indigo", "invariable")
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Colors") },
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
            items(colorCategories) { category ->
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = category.title,
                            style = MaterialTheme.typography.titleMedium
                        )
                        category.entries.forEach { entry ->
                            Column(modifier = Modifier.fillMaxWidth()) {
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
                                if (entry.note.isNotEmpty()) {
                                    Text(
                                        text = entry.note,
                                        style = MaterialTheme.typography.bodySmall,
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
}
