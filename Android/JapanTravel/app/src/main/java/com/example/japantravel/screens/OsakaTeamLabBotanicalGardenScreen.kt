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
fun OsakaTeamLabBotanicalGardenScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "teamLab Botanical Garden Osaka", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("What it is / concept")
            BodyText(
                "teamLab's permanent nighttime open-air museum, built inside a real botanical " +
                    "garden of about 1,200 plant species. After sunset, digital light and art " +
                    "installations respond to the surrounding flowers, lake, trees, and even rain, " +
                    "turning the living garden itself into part of the artwork."
            )

            SectionHeader("Where")
            BodyText(
                "Inside Nagai Park in southern Osaka. Take the Osaka Metro Midosuji Line to Nagai " +
                    "Station (about 25 minutes from Namba), then it's roughly a 10-minute walk " +
                    "from Exit 3."
            )

            SectionHeader("Is it for the whole family?")
            BodyText(
                "It's an evening/night outdoor walk - doors open around 7:30pm, which can be late " +
                    "for very young children, but the garden paths are flat and stroller-friendly, " +
                    "and it's a calmer, more relaxed experience than teamLab's indoor Tokyo venues."
            )

            SectionHeader("Prices")
            BodyText(
                "Tickets are ¥1,800 for adults (high-school age and up) and ¥500 for junior-high " +
                    "age and younger, with a surcharge added if bought on-site rather than online."
            )

            SectionHeader("Where to book")
            BodyText(
                "Tickets are sold for a specific entrance timeslot and should be bought online in " +
                    "advance, via the official teamLab website (teamlab.art) or resellers such as " +
                    "Klook or GetYourGuide."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
