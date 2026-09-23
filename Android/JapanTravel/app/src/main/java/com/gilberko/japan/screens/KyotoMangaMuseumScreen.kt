package com.gilberko.japan.screens

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
fun KyotoMangaMuseumScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Kyoto International Manga Museum", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("What it is")
            BodyText(
                "The **Kyoto International Manga Museum** is a combined museum and library " +
                    "housed in a converted former elementary school building near **Karasuma " +
                    "Oike**, holding one of the largest manga collections in Japan alongside " +
                    "exhibits on the history and craft of manga."
            )

            SectionHeader("Ages and audience")
            BodyText(
                "It's genuinely all-ages: there's a dedicated children's picture-book and manga " +
                    "corner for younger kids, an English-translated manga section for " +
                    "non-Japanese readers, and reading rooms and reference material substantial " +
                    "enough to hold the attention of serious manga fans and researchers."
            )

            SectionHeader("What it features")
            BodyText(
                "The signature sight is the **\"Wall of Manga\"** - around 50,000 volumes shelved " +
                    "along corridor-length bookcases, almost all of which visitors are free to " +
                    "pull down and read on-site (in the reading rooms or out on the lawn). Beyond " +
                    "the wall, there's an exhibition hall covering manga history and drawing " +
                    "technique, a research library with a much larger archived collection, and on " +
                    "some days live manga-artist demonstrations. A grassy courtyard outside is a " +
                    "popular spot to sit and read on a nice day."
            )

            SectionHeader("Tickets")
            BodyText(
                "Adult admission is roughly ¥800-¥1,200 with reduced rates for students and " +
                    "children, though pricing has changed in recent years, so it's worth checking " +
                    "current prices before visiting. The museum is open 10am-6pm (last entry " +
                    "5:30pm) and closed on Wednesdays - if a Wednesday falls on a public holiday, " +
                    "it closes the following Thursday instead."
            )

            SectionHeader("How to get there")
            BodyText(
                "It's about a 2-minute walk from **Karasuma Oike Station**, where the Karasuma " +
                    "and Tozai subway lines meet - roughly 5 minutes and ¥220 from **Kyoto " +
                    "Station** by subway."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
