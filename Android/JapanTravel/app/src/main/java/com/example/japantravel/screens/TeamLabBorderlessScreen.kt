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
fun TeamLabBorderlessScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "teamLab Borderless", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("What it is")
            BodyText(
                "The **MORI Building DIGITAL ART MUSEUM**: teamLab's flagship \"borderless\" digital " +
                    "art museum, where the artworks move between rooms and react to visitors. It " +
                    "originally opened in **Odaiba** in 2018 and closed in 2022; it reopened in " +
                    "February 2024 at **Azabudai Hills** (**Minato Ward**, about a 15-minute walk from " +
                    "**Roppongi** or Azabu-juban Station) - larger and with new works, but the same " +
                    "museum, not a separate \"part 2\"."
            )

            SectionHeader("Tickets and tips")
            BodyText(
                "Entry is timed and should be booked online in advance, especially for weekends. " +
                    "Expect to spend two or more hours wandering between rooms - there's no fixed " +
                    "route."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
