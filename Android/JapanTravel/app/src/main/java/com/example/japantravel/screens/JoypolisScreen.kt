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
fun JoypolisScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Joypolis", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("What it is")
            BodyText(
                "Tokyo Joypolis is SEGA's indoor arcade and VR entertainment park in Odaiba, " +
                    "spread across three floors with around 21 attractions - a good rainy-day or " +
                    "evening option since it's fully indoors."
            )

            SectionHeader("Main rides")
            BodyText(
                "Gekion Live Coaster (a roller coaster synced to music), Wild River (a VR rafting " +
                    "ride), Halfpipe Tokyo, VR horror houses, and various laser-tag/shooting and " +
                    "arcade attractions."
            )

            SectionHeader("The app, costs, and ride reservations")
            BodyText(
                "There is no dedicated app for booking individual ride time slots at Joypolis. " +
                    "Tickets are bought on-site or pre-booked online (official site or platforms " +
                    "like Klook). A Passport ticket (admission + unlimited rides) costs around " +
                    "¥4,200 for adults / ¥3,600 for children; admission-only is about ¥1,200 / " +
                    "¥900 with individual rides paid separately (roughly ¥700-1,000 each)."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
