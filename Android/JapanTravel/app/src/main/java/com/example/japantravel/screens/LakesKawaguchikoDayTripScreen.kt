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
fun LakesKawaguchikoDayTripScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Lakes Region and Kawaguchiko", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Getting there")
            BodyText(
                "The JR Chuo Line limited express \"Fuji Excursion\" runs direct from **Shinjuku** to " +
                    "**Kawaguchiko** with no transfer. Alternatively, a direct highway bus from " +
                    "Shinjuku Station's bus terminal is cheaper and more frequent, though slower."
            )

            SectionHeader("What to see")
            BodyText(
                "**Lake Kawaguchiko** is one of the **Fuji Five Lakes**, at the base of **Mount Fuji** - one " +
                    "of the most popular short trips out of **Tokyo**. The **Mt. Fuji Panoramic Ropeway** " +
                    "(**Kachi Kachi Yama Ropeway**) climbs a nearby peak for a sweeping view over the " +
                    "lake with **Fuji** behind it; **Oishi Park** on the north shore combines a classic " +
                    "**Fuji** view with seasonal flower fields; lake cruises and shoreline cycling are " +
                    "also popular. **Kawaguchiko** is especially famous for shots where **Fuji** is " +
                    "mirrored in still lake water - most reliable on clear, calm mornings, since " +
                    "the mountain is often shy behind cloud cover later in the day."
            )

            SectionHeader("When to head back")
            BodyText(
                "Front-load lake-view activities for the morning if a clear **Fuji** photo matters to " +
                    "you. The Fuji Excursion limited express has only a handful of direct return " +
                    "departures per day, so it's worth reserving the return leg in advance rather " +
                    "than assuming walk-up availability; the highway bus runs more frequently and " +
                    "is a reliable fallback if you miss your train reservation."
            )

            SectionHeader("How long is the train")
            BodyText(
                "About 2 hours each way on the direct Fuji Excursion limited express; the highway " +
                    "bus takes about 1h45m."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
