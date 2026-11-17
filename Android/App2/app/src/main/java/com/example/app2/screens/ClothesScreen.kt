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


private val topsWords = listOf(
    DictWord("a camisa", "shirt"),
    DictWord("a camisola", "sweater / jumper", "EP: \"camisola\" = sweater, not a small shirt"),
    DictWord("a t-shirt", "t-shirt"),
    DictWord("a blusa", "blouse"),
    DictWord("a sweatshirt", "sweatshirt"),
    DictWord("a sweatshirt com capuz", "hoodie")
)

private val bottomsOutfitsWords = listOf(
    DictWord("as calças", "trousers / pants"),
    DictWord("as calças de ganga", "jeans", "EP uses \"ganga\" for denim"),
    DictWord("os calções", "shorts"),
    DictWord("a saia", "skirt"),
    DictWord("o vestido", "dress"),
    DictWord("o fato", "suit", "EP term (BP: \"o terno\")"),
    DictWord("o fato de banho", "swimsuit / swimwear", "EP term (BP: \"roupa de banho\")"),
    DictWord("o pijama", "pyjamas")
)

private val outerwearWords = listOf(
    DictWord("o casaco", "jacket / coat"),
    DictWord("o sobretudo", "overcoat"),
    DictWord("o impermeável", "raincoat")
)

private val accessoriesWords = listOf(
    DictWord("o chapéu", "hat"),
    DictWord("a boina", "beret", "common in EP"),
    DictWord("o cinto", "belt"),
    DictWord("a gravata", "tie"),
    DictWord("o cachecol", "scarf"),
    DictWord("as luvas", "gloves")
)

private val footwearUnderwearWords = listOf(
    DictWord("os sapatos", "shoes"),
    DictWord("as botas", "boots"),
    DictWord("os ténis", "trainers / sneakers", "EP spelling (BP: \"tênis\")"),
    DictWord("as meias", "socks / stockings"),
    DictWord("as cuecas", "underpants"),
    DictWord("o soutien", "bra", "EP term from French (BP: \"sutiã\")")
)

@Composable
fun ClothesScreen(onBack: () -> Unit) {
    DictScreenScaffold(title = "Clothes", onBack = onBack) {
        item { DictCategoryCard("Tops", topsWords) }
        item { DictCategoryCard("Bottoms & Full Outfits", bottomsOutfitsWords) }
        item { DictCategoryCard("Outerwear", outerwearWords) }
        item { DictCategoryCard("Accessories", accessoriesWords) }
        item { DictCategoryCard("Footwear & Underwear", footwearUnderwearWords) }
    }
}
