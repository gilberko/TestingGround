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
fun MountTakaoDayTripScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Mount Takao", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Getting there")
            BodyText(
                "The Keio Line runs direct from Shinjuku to Takaosanguchi Station, about 50 " +
                    "minutes, with frequent service all day."
            )

            SectionHeader("What to see")
            BodyText(
                "Mount Takao (599m) is the easiest proper hike near Tokyo. Trail 1, the main " +
                    "route to the summit, is broad and mostly paved, and a cable car or chairlift " +
                    "cuts the roughly 90-minute hike about in half - a good option for families or " +
                    "anyone with less stamina. Partway up is the Takao Monkey Park & Wildflower " +
                    "Garden, home to around 90 Japanese macaques viewable safely through glass. " +
                    "Takaosanguchi Station itself has restaurants, souvenir shops, and even an " +
                    "onsen for after the hike."
            )

            SectionHeader("When to head back")
            BodyText(
                "Since the Keio Line runs frequently and needs no reservation, the train timing " +
                    "itself is flexible. The real constraint is the cable car/chairlift's last " +
                    "descent time in the early evening - miss it and you're walking down Trail 1 " +
                    "in the dark, so check the current last-departure time before you head up."
            )

            SectionHeader("How long is the train")
            BodyText(
                "About 50 minutes each way on the direct Keio Line from Shinjuku - one of the " +
                    "shortest rides on this list, making it an easy half-day or relaxed full-day " +
                    "trip."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
