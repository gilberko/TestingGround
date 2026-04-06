package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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

private data class ClothesEntry(val pt: String, val en: String, val note: String = "")

private val topsWords = listOf(
    ClothesEntry("a camisa", "shirt"),
    ClothesEntry("a camisola", "sweater / jumper", "EP: \"camisola\" = sweater, not a small shirt"),
    ClothesEntry("a t-shirt", "t-shirt"),
    ClothesEntry("a blusa", "blouse"),
    ClothesEntry("a sweatshirt", "sweatshirt"),
    ClothesEntry("a sweatshirt com capuz", "hoodie")
)

private val bottomsOutfitsWords = listOf(
    ClothesEntry("as calças", "trousers / pants"),
    ClothesEntry("as calças de ganga", "jeans", "EP uses \"ganga\" for denim"),
    ClothesEntry("os calções", "shorts"),
    ClothesEntry("a saia", "skirt"),
    ClothesEntry("o vestido", "dress"),
    ClothesEntry("o fato", "suit", "EP term (BP: \"o terno\")"),
    ClothesEntry("o fato de banho", "swimsuit / swimwear", "EP term (BP: \"roupa de banho\")"),
    ClothesEntry("o pijama", "pyjamas")
)

private val outerwearWords = listOf(
    ClothesEntry("o casaco", "jacket / coat"),
    ClothesEntry("o sobretudo", "overcoat"),
    ClothesEntry("o impermeável", "raincoat")
)

private val accessoriesWords = listOf(
    ClothesEntry("o chapéu", "hat"),
    ClothesEntry("a boina", "beret", "common in EP"),
    ClothesEntry("o cinto", "belt"),
    ClothesEntry("a gravata", "tie"),
    ClothesEntry("o cachecol", "scarf"),
    ClothesEntry("as luvas", "gloves")
)

private val footwearUnderwearWords = listOf(
    ClothesEntry("os sapatos", "shoes"),
    ClothesEntry("as botas", "boots"),
    ClothesEntry("os ténis", "trainers / sneakers", "EP spelling (BP: \"tênis\")"),
    ClothesEntry("as meias", "socks / stockings"),
    ClothesEntry("as cuecas", "underpants"),
    ClothesEntry("o soutien", "bra", "EP term from French (BP: \"sutiã\")")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClothesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Clothes") },
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
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { ClothesCard("Tops", topsWords) }
            item { ClothesCard("Bottoms & Full Outfits", bottomsOutfitsWords) }
            item { ClothesCard("Outerwear", outerwearWords) }
            item { ClothesCard("Accessories", accessoriesWords) }
            item { ClothesCard("Footwear & Underwear", footwearUnderwearWords) }
        }
    }
}

@Composable
private fun ClothesCard(title: String, entries: List<ClothesEntry>) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
            entries.forEach { entry ->
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
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
        }
    }
}
