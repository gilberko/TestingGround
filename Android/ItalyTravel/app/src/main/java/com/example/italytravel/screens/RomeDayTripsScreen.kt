package com.example.italytravel.screens

import androidx.compose.runtime.Composable

/** Rome -> Nature And Day Trips: one button per day trip. */
@Composable
fun RomeDayTripsScreen(onBack: () -> Unit, onOpen: (String) -> Unit) {
    ScreenScaffold(title = "Rome - Nature And Day Trips", onBack = onBack, showBackground = true) {
        MenuButtons(
            items = romeDayTrips.map { trip -> trip.title to { onOpen(trip.id) } },
            header = {
                OverlayText(
                    "Most day trips are easy by regional train or bus with normal tickets - but trains back can be infrequent, so check the return times before you go. " +
                        "Many museums outside Rome close on Monday."
                )
            }
        )
    }
}

@Composable
fun RomeDayTripDetailScreen(id: String, onBack: () -> Unit) {
    AttractionDetailScreen(romeDayTrips, id, onBack)
}
