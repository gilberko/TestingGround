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
fun YokohamaDayTripScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Yokohama", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Getting there")
            BodyText(
                "The JR Tokaido Line or Keihin-Tohoku Line runs direct from Tokyo Station to " +
                    "Yokohama Station, about 30-45 minutes, very frequently. From Yokohama " +
                    "Station, the Minatomirai Line continues on to **Minato Mirai** and the other " +
                    "waterfront sights."
            )

            SectionHeader("What to see")
            BodyText(
                "**Minato Mirai** is **Yokohama**'s waterfront district - **Landmark Tower** and the **Cosmo** " +
                    "**World** Ferris wheel are the visual anchors. **Yokohama Chinatown**, Japan's " +
                    "largest, is packed with Chinese restaurants and street food stalls. The **Cup** " +
                    "**Noodles Museum** lets you design and make your own instant noodle cup (about " +
                    "¥500 for adults, plan 1-2 hours, closed Tuesdays). **Sankeien Garden**, a bit " +
                    "further out, is a quieter optional add-on if you want a break from the " +
                    "waterfront crowds."
            )

            SectionHeader("When to head back")
            BodyText(
                "Trains back to central **Tokyo** run late into the night and very frequently, so " +
                    "return timing here is genuinely flexible - no need to plan around a specific " +
                    "last train the way you would for the more remote trips on this list."
            )

            SectionHeader("How long is the train")
            BodyText(
                "About 30-45 minutes each way - one of the shortest and easiest day trips from " +
                    "**Tokyo**."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
