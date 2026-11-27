package com.example.italytravel.screens

import androidx.compose.runtime.Composable

/** Milan -> Attractions: a select screen with one button per attraction. */
@Composable
fun MilanAttractionsScreen(onBack: () -> Unit, onOpen: (String) -> Unit) {
    ScreenScaffold(title = "Milan - Attractions", onBack = onBack, showBackground = true) {
        MenuButtons(
            items = milanAttractions.map { attraction -> attraction.title to { onOpen(attraction.id) } },
            header = {
                OverlayText(
                    "Tips: book the Duomo terraces on the official website (duomomilano.it) - resellers charge more. " +
                        "The Duomo, Galleria, Castello Sforzesco and Parco Sempione are all within a 15-20 min walk of each other (Metro M1 Duomo / Cairoli)."
                )
            }
        )
    }
}

/** One Milan attraction's information screen. */
@Composable
fun MilanAttractionDetailScreen(id: String, onBack: () -> Unit) {
    AttractionDetailScreen(milanAttractions, id, onBack, city = "Milan")
}
