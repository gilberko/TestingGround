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
fun NintendoMuseumScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Nintendo Museum", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("What it is")
            BodyText(
                "Nintendo's official museum, opened in October 2024 inside a former Nintendo " +
                    "factory building. It's a retrospective of the company's history from " +
                    "playing cards through consoles, handhelds, and games, with large-scale " +
                    "replicas of controllers and hardware you can interact with, plus hands-on " +
                    "play areas."
            )

            SectionHeader("Location")
            BodyText(
                "Uji, Kyoto Prefecture - not Tokyo. (If you were looking for this under Tokyo, " +
                    "this is the correct place: it's about 20-30 minutes from Kyoto Station via " +
                    "the JR Nara Line plus a short walk/bus.)"
            )

            SectionHeader("Ages")
            BodyText(
                "All ages - designed as a family attraction, with content spanning classic " +
                    "hardware nostalgia for adults and playable exhibits for kids."
            )

            SectionHeader("How to get there")
            BodyText(
                "From Kyoto Station, take the JR Nara Line to Uji or Rokujizo Station, then a " +
                    "short walk or local bus to the museum."
            )

            SectionHeader("How to buy tickets")
            BodyText(
                "Entry is lottery-only through Nintendo's official ticket site " +
                    "(museum-tickets.nintendo.com) - there's no walk-in option, no same-day " +
                    "availability, and no third-party booking. Apply roughly three months ahead " +
                    "of your intended visit; results are announced on the 1st of the month before " +
                    "your visit, with a payment deadline of 23:59 JST on the 7th of that month. " +
                    "Any unclaimed tickets go on general sale about two weeks later. A free " +
                    "Nintendo Account is required to apply, and only credit cards are accepted for " +
                    "payment - set this up well before the application date."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
