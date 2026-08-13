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
fun OkutamaMitakeDayTripScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Okutama / Mount Mitake", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Getting there")
            BodyText(
                "Take the JR Chuo Line from **Shinjuku** to **Ome**, then transfer to the Ome Line to " +
                    "Mitake Station, then a short bus ride and a cable car up to Mitakesan Station " +
                    "- about 90 minutes total from **Shinjuku**."
            )

            SectionHeader("What to see")
            BodyText(
                "**Mount Mitake**, in **Okutama**, is a quieter, more remote alternative to **Mount Takao**. " +
                    "At the summit is **Musashi Mitake Shrine**, said to be over 2,000 years old, " +
                    "reached by a 20-30 minute walk from the cable car's upper station through " +
                    "**Mitake** village - traditional lodges, a 1,000-year-old sacred tree, and " +
                    "mountain scenery along the way. The fuller loop with side trails to " +
                    "waterfalls and lookout points runs about 6-8km and takes 3-4 hours - more of " +
                    "a real hike than **Takao**, and noticeably less crowded."
            )

            SectionHeader("When to head back")
            BodyText(
                "The Ome Line and the Mitake cable car both run less frequently than central-" +
                    "**Tokyo** lines, especially in the late afternoon - check the cable car's last " +
                    "descent time before committing to the longer waterfall loop, and budget for " +
                    "an early sunset if you're visiting in winter, since the return trip has " +
                    "several connections."
            )

            SectionHeader("How long is the train")
            BodyText(
                "About 90 minutes each way from **Shinjuku**, including the Chuo Line, the Ome Line " +
                    "transfer, and the cable car."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
