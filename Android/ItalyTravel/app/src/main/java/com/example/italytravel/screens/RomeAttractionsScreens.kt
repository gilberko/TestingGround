package com.example.italytravel.screens

import androidx.compose.runtime.Composable

/** Rome -> Attractions: a select screen with one button per attraction. */
@Composable
fun RomeAttractionsScreen(onBack: () -> Unit, onOpen: (String) -> Unit) {
    ScreenScaffold(title = "Rome - Attractions", onBack = onBack, showBackground = true) {
        MenuButtons(
            items = romeAttractions.map { attraction -> attraction.title to { onOpen(attraction.id) } },
            header = {
                OverlayText(
                    "Tips: book the big sights (Colosseum, Vatican Museums, Galleria Borghese) on their official websites - resellers charge much more. " +
                        "Churches need covered shoulders and knees. State museums are free on the first Sunday of the month (no booking, long queues)."
                )
            }
        )
    }
}

/** One attraction's information screen. */
@Composable
fun RomeAttractionDetailScreen(id: String, onBack: () -> Unit) {
    AttractionDetailScreen(romeAttractions, id, onBack)
}

/** Generic information screen for one entry of a data list (attractions, day trips). */
@Composable
fun AttractionDetailScreen(list: List<Attraction>, id: String, onBack: () -> Unit) {
    val attraction = list.firstOrNull { it.id == id }
    InfoScreen(title = "Rome - ${attraction?.title ?: "Attraction"}", onBack = onBack) {
        if (attraction == null) {
            BodyText("Not found.")
            return@InfoScreen
        }
        attraction.intro?.let { BodyText(it) }
        attraction.places.forEach { place ->
            PlaceEntry(place.name ?: attraction.title, *place.facts.toTypedArray())
        }
    }
}
