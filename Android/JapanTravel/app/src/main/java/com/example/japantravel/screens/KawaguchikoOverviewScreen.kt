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
fun KawaguchikoOverviewScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Overview", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("The lake and the area")
            BodyText(
                "**Lake Kawaguchiko** is one of the **Fuji Five Lakes**, at the base of **Mount Fuji**, and " +
                    "one of the most popular short trips out of **Tokyo**. Ways to spend time around " +
                    "the lake: the **Mt. Fuji Panoramic Ropeway** (also called **Kachi Kachi Yama " +
                    "Ropeway**), which climbs a nearby peak for a sweeping view over the lake with " +
                    "**Fuji** behind it; lake cruises; cycling the shoreline path; and **Kawaguchiko " +
                    "Music Forest**, a music-box and orchestrion museum with garden grounds. **Oishi " +
                    "Park**, on the lake's north shore, combines a classic **Fuji** view with seasonal " +
                    "flower fields (lavender in summer, kochia and cosmos in autumn)."
            )

            SectionHeader("The famous Mount Fuji view")
            BodyText(
                "**Kawaguchiko** is one of the best-known and most photographed vantage points for " +
                    "**Mount Fuji**, especially the shots where the mountain is mirrored in the still " +
                    "lake water - most reliable on clear, calm mornings. **Fuji** is often shy behind " +
                    "cloud cover later in the day, so plan lake-view activities for the morning if " +
                    "a clear photo matters to you."
            )

            SectionHeader("Getting there from Tokyo")
            BodyText(
                "The simplest option is a direct highway bus from Shinjuku Station's bus terminal " +
                    "(about 1h45m). By train, the JR Chuo Line limited express \"Fuji Excursion\" " +
                    "runs direct from **Shinjuku** to **Kawaguchiko** with no transfer; alternatively, " +
                    "take a local/rapid JR train to **Otsuki** and transfer to the Fujikyu Railway line " +
                    "into **Kawaguchiko**."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
