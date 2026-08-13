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
fun WeatherScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Weather", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Month by month")
            BodyText(
                "Spring (March-May): generally mild, with April a particularly comfortable " +
                    "window and cherry blossoms as the big draw."
            )
            BodyText(
                "Rainy season - \"tsuyu\" (June-early July): high humidity and frequent rain " +
                    "across most of the country."
            )
            BodyText(
                "Summer (July-August): hot and humid, with typhoon risk running roughly August " +
                    "through October - check forecasts if a typhoon is tracking toward Japan " +
                    "during your trip, since trains (including the Shinkansen) can suspend service."
            )
            BodyText(
                "Autumn (September-November): October and November are usually comfortable and " +
                    "mild, with good autumn foliage later in November."
            )
            BodyText(
                "Winter (December-February): cold, especially January - inland cities like **Kyoto** " +
                    "get noticeably colder than coastal **Tokyo**, and occasional snow is possible in " +
                    "both."
            )

            SectionHeader("Recommended weather apps")
            BodyText(
                "tenki.jp and the Yahoo! Weather Japan app both give detailed local forecasts. " +
                    "For official forecasts and typhoon warnings in English, the Japan " +
                    "Meteorological Agency (JMA, jma.go.jp) is the authoritative source."
            )

            SectionHeader("Tips")
            BodyText(
                "Always carry a compact umbrella, or don't bother packing one at all - the clear " +
                    "plastic umbrellas sold cheaply at any convenience store are sturdy and easy " +
                    "to pick up on the spot when sudden rain hits, which is common, especially " +
                    "during tsuyu."
            )
            BodyText(
                "Dress in layers during spring and autumn - mornings and evenings can be " +
                    "noticeably cooler than the middle of the day."
            )
            BodyText(
                "Summer calls for breathable, moisture-wicking clothing and sun protection (hat, " +
                    "sunscreen, a folding fan or handheld fan) - the heat and humidity are " +
                    "intense, and recent summers have seen record-breaking heat."
            )
            BodyText(
                "Winter calls for a proper warm coat plus removable layers underneath - indoor " +
                    "heating tends to run warm, so you'll want to shed layers once you're inside " +
                    "shops, trains, and restaurants."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
