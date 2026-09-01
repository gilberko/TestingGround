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
fun KyotoWhereToSleepScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Kyoto - Where To Sleep", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("For Families")
            BodyText(
                "**Downtown / Kawaramachi** (Central Kyoto) is the easiest base - walkable, " +
                    "close to family-friendly restaurants and shopping, with straightforward " +
                    "transport to most sights. Staying near **Kyoto Station** also works well " +
                    "when easy train and bus access with luggage matters most."
            )

            SectionHeader("For Nightlife and Young Travelers")
            BodyText(
                "**Pontocho**, a narrow lantern-lit alley of bars and restaurants along the " +
                    "Kamo River, is Kyoto's most atmospheric nightlife strip. " +
                    "**Gion / Higashiyama** has a lively, more traditional geisha-district " +
                    "dining and drinking scene. **Downtown / Kawaramachi** is the best " +
                    "all-rounder for shopping, nightlife, and central access."
            )

            SectionHeader("For Business Travelers")
            BodyText(
                "**Shimogyo-ku**, right around **Kyoto Station**: the Shinkansen, JR lines, " +
                    "the Karasuma subway, Kintetsu trains, and airport limousine buses all " +
                    "converge there, with plenty of places to eat without being a loud " +
                    "nightlife zone."
            )

            SectionHeader("Where To Use Caution")
            BodyText(
                "Kyoto has no notably unsafe district for tourists, but some scenic outer " +
                    "neighborhoods are genuinely inconvenient as a home base. " +
                    "**Arashiyama** looks appealing (bamboo grove, monkey park), but hotels " +
                    "there are often far from the station, meaning long commutes into " +
                    "downtown. The northern hills (**Takagamine**, **Nishijin**, and " +
                    "**Kita-ku** generally) are peaceful but rely heavily on buses or taxis, " +
                    "which slows down a short trip."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
