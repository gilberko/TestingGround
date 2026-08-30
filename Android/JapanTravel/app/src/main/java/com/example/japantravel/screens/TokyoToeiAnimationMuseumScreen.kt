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
fun TokyoToeiAnimationMuseumScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "TOEI Animation Museum", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("What it is")
            BodyText(
                "Toei Animation's own museum in **Nerima**, Tokyo, showcasing production art, " +
                    "cels, storyboards, and history from its long-running anime library - " +
                    "including Dragon Ball, Sailor Moon, One Piece, and Digimon."
            )

            SectionHeader("Who it's for")
            BodyText(
                "Anime fans of any age, especially those interested in the production side " +
                    "(art, cels, character design) rather than a rides-and-shows experience. " +
                    "It's a small, quiet museum rather than a theme park - best treated as an " +
                    "add-on stop near Nerima/Ikebukuro rather than a full day out."
            )

            SectionHeader("Costs")
            BodyText("Admission is free.")

            SectionHeader("Hours")
            BodyText(
                "Roughly 10:00am-5:00pm, with last admission around 4:30pm. It's worth " +
                    "checking the official site for regular closed days before visiting."
            )

            SectionHeader("How to buy tickets")
            BodyText(
                "No tickets are needed since entry is free, though popular time slots can " +
                    "still have a short queue."
            )

            SectionHeader("How to get there")
            BodyText(
                "Located in **Nerima** ward, a short walk from **Oizumi-Gakuen Station** on " +
                    "the Seibu Ikebukuro Line - easily reached from central Tokyo or " +
                    "**Ikebukuro**."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
