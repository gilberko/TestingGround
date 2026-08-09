package com.example.japantravel.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UsefulAppsScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Useful Apps", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Klook")
            BodyText(
                "A travel-booking app covering attractions, tours, activities, and Japan Rail " +
                    "tickets and passes. Good for bundling sightseeing tickets and transport in " +
                    "one place, often with skip-the-line entry."
            )

            SectionHeader("Ticket Pia (ぴあ)")
            BodyText(
                "Japan's major entertainment-ticket platform, for concerts, sports, theater, and " +
                    "other live events. Similar services worth knowing about are e+ (eplus) and " +
                    "Lawson Ticket, which sell overlapping but not identical event listings."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
