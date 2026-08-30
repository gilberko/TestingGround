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
fun NaraGettingThereScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Getting There", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("From Osaka")
            BodyText(
                "Kintetsu Nara Line express trains run from **Osaka-Namba** Station to " +
                    "**Kintetsu-Nara** Station in about 35-40 minutes for roughly ¥560-680, " +
                    "and drop you a 5-minute walk from Nara Park and the deer."
            )
            BodyText(
                "JR's Yamatoji Rapid line runs from **Osaka Station** to **JR Nara** Station " +
                    "in about 45-50 minutes for roughly ¥800-820 - a bit slower and further " +
                    "from the park (about a 20-minute walk), but the option to use if you're " +
                    "relying on a Japan Rail Pass."
            )

            SectionHeader("From Kyoto")
            BodyText(
                "Kintetsu limited express trains run directly from **Kyoto Station** to " +
                    "**Kintetsu-Nara** in about 35 minutes for roughly ¥1,280 total (fare plus " +
                    "limited-express charge, with reserved seating). A regular Kintetsu express " +
                    "covers the same route in about 45-50 minutes for roughly ¥760, with no " +
                    "reservation needed."
            )
            BodyText(
                "JR's Miyakoji Rapid line runs from **Kyoto Station** to **JR Nara** Station in " +
                    "about 45 minutes for roughly ¥720, and is covered by the Japan Rail Pass."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
