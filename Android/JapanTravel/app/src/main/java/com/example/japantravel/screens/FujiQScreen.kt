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
fun FujiQScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Fuji-Q Highland", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("What it is")
            BodyText(
                "An amusement park in Fujiyoshida, Yamanashi, right next to Kawaguchiko, famous " +
                    "for its record-breaking roller coasters and Mt. Fuji views."
            )

            SectionHeader("Main rides")
            BodyText(
                "The \"Big Four\": Fujiyama, a classic tall, fast coaster; Dodonpa, known for one " +
                    "of the fastest accelerations of any coaster in the world; Takabisha, which " +
                    "had the steepest drop of any coaster in the world when it opened; and " +
                    "Eejanaika, a 4D coaster that rotates seats independently. There's also Thomas " +
                    "Land for younger kids, and a well-known haunted-hospital walk-through horror " +
                    "attraction for thrill-seekers."
            )

            SectionHeader("The app, costs, and ride reservations")
            BodyText(
                "No dedicated app-based ride-reservation system was found for Fuji-Q. Instead, " +
                    "paper Fast Passes (about ¥1,000 each, limited to roughly two per hour block) " +
                    "are bought on-site the same day at booths near the ride entrances, and tend " +
                    "to sell out, so buy them early in the day. Entry options are an unlimited-" +
                    "rides \"Free Pass\" day ticket or individual per-ride tickets."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
