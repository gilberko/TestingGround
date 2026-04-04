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

private data class VacEntry(val pt: String, val en: String, val note: String = "")

private val beachWords = listOf(
    VacEntry("a praia", "beach"),
    VacEntry("o mar", "the sea"),
    VacEntry("o oceano", "the ocean"),
    VacEntry("a areia", "sand"),
    VacEntry("o sol", "the sun"),
    VacEntry("a piscina", "the pool"),
    VacEntry("nadar", "to swim"),
    VacEntry("mergulhar", "to dive"),
    VacEntry("fazer snorkel", "to snorkel"),
    VacEntry("fazer surf", "to surf"),
    VacEntry("a prancha de surf", "surfboard"),
    VacEntry("o nadador-salvador / a nadadora-salvadora", "lifeguard", "EP term")
)

private val accommodationWords = listOf(
    VacEntry("o hotel", "hotel"),
    VacEntry("a pensão", "guesthouse", "EP term; also: a hospedaria"),
    VacEntry("o/a hóspede", "guest"),
    VacEntry("o pequeno-almoço", "breakfast", "EP term — BP: o café da manhã"),
    VacEntry("o bufete", "buffet", "also written: o buffet")
)

private val gettingAroundWords = listOf(
    VacEntry("o táxi", "taxi"),
    VacEntry("o barco", "boat"),
    VacEntry("um passeio de barco", "a boat ride")
)

private val clothingWords = listOf(
    VacEntry("o fato de banho", "bathing suit / swimsuit", "EP term — covers both"),
    VacEntry("o biquíni", "bikini"),
    VacEntry("o protetor solar", "sunscreen"),
    VacEntry("a t-shirt", "t-shirt"),
    VacEntry("as sandálias", "sandals")
)

private val activitiesWords = listOf(
    VacEntry("as férias", "vacation / holiday", "always plural in PT"),
    VacEntry("fazer turismo", "sightseeing", "lit. to do tourism"),
    VacEntry("o/a turista", "tourist"),
    VacEntry("a atração turística", "tourist attraction"),
    VacEntry("o parque aquático", "water park"),
    VacEntry("o parque de diversões", "amusement park"),
    VacEntry("os tobogãs aquáticos", "water slides"),
    VacEntry("a montanha-russa", "roller coaster"),
    VacEntry("esquiar", "to ski"),
    VacEntry("as pistas", "the slopes"),
    VacEntry("os bilhetes", "tickets")
)

private val descriptionsWords = listOf(
    VacEntry("divertido/a", "fun / enjoyable"),
    VacEntry("cheio/a / lotado/a", "crowded")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VacationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Vacation") },
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
            item { VacWordCard("At the Beach", beachWords) }
            item { VacWordCard("Accommodation", accommodationWords) }
            item { VacWordCard("Getting Around", gettingAroundWords) }
            item { VacWordCard("Clothing & Gear", clothingWords) }
            item { VacWordCard("Activities & Attractions", activitiesWords) }
            item { VacWordCard("Feelings & Descriptions", descriptionsWords) }
        }
    }
}

@Composable
private fun VacWordCard(title: String, entries: List<VacEntry>) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Portuguese",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1.1f),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    "English",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(0.9f),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            entries.forEach { entry ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1.1f)) {
                        Text(entry.pt, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
                        if (entry.note.isNotEmpty()) {
                            Text(
                                entry.note,
                                style = MaterialTheme.typography.labelSmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }
                    }
                    Text(
                        entry.en,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(0.9f),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
