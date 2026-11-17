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


private val beachWords = listOf(
    DictWord("a praia", "beach"),
    DictWord("o mar", "the sea"),
    DictWord("o oceano", "the ocean"),
    DictWord("a areia", "sand"),
    DictWord("o sol", "the sun"),
    DictWord("a piscina", "the pool"),
    DictWord("nadar", "to swim"),
    DictWord("mergulhar", "to dive"),
    DictWord("fazer snorkel", "to snorkel"),
    DictWord("fazer surf", "to surf"),
    DictWord("a prancha de surf", "surfboard"),
    DictWord("o nadador-salvador / a nadadora-salvadora", "lifeguard", "EP term")
)

private val accommodationWords = listOf(
    DictWord("o hotel", "hotel"),
    DictWord("a pensão", "guesthouse", "EP term; also: a hospedaria"),
    DictWord("o/a hóspede", "guest"),
    DictWord("o pequeno-almoço", "breakfast", "EP term — BP: o café da manhã"),
    DictWord("o bufete", "buffet", "also written: o buffet")
)

private val gettingAroundWords = listOf(
    DictWord("o táxi", "taxi"),
    DictWord("o barco", "boat"),
    DictWord("um passeio de barco", "a boat ride")
)

private val clothingWords = listOf(
    DictWord("o fato de banho", "bathing suit / swimsuit", "EP term — covers both"),
    DictWord("o biquíni", "bikini"),
    DictWord("o protetor solar", "sunscreen"),
    DictWord("a t-shirt", "t-shirt"),
    DictWord("as sandálias", "sandals")
)

private val activitiesWords = listOf(
    DictWord("as férias", "vacation / holiday", "always plural in PT"),
    DictWord("fazer turismo", "sightseeing", "lit. to do tourism"),
    DictWord("o/a turista", "tourist"),
    DictWord("a atração turística", "tourist attraction"),
    DictWord("o parque aquático", "water park"),
    DictWord("o parque de diversões", "amusement park"),
    DictWord("os tobogãs aquáticos", "water slides"),
    DictWord("a montanha-russa", "roller coaster"),
    DictWord("esquiar", "to ski"),
    DictWord("as pistas", "the slopes"),
    DictWord("os bilhetes", "tickets")
)

private val descriptionsWords = listOf(
    DictWord("divertido/a", "fun / enjoyable"),
    DictWord("cheio/a / lotado/a", "crowded")
)

@Composable
fun VacationScreen(onBack: () -> Unit) {
    DictScreenScaffold(title = "Vacation", onBack = onBack) {
        item { DictCategoryCard("At the Beach", beachWords) }
        item { DictCategoryCard("Accommodation", accommodationWords) }
        item { DictCategoryCard("Getting Around", gettingAroundWords) }
        item { DictCategoryCard("Clothing & Gear", clothingWords) }
        item { DictCategoryCard("Activities & Attractions", activitiesWords) }
        item { DictCategoryCard("Feelings & Descriptions", descriptionsWords) }
    }
}
