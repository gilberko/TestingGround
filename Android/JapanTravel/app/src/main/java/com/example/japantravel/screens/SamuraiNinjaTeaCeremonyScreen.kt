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
fun SamuraiNinjaTeaCeremonyScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Samurai Ninja Museum + Tea Ceremony", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("What it is")
            BodyText(
                "A hands-on museum where an English-speaking guide walks you through samurai " +
                    "and ninja history, with samurai armor try-on and shuriken (ninja star) " +
                    "throwing included in the basic ticket. Both **Tokyo** branches are run by " +
                    "**Maikoya**, the same company that runs Japanese tea ceremony experiences - " +
                    "so a kimono tea ceremony can be booked as an add-on or combo at the same " +
                    "company, either right after the museum visit or as a separate booking."
            )

            SectionHeader("Locations")
            BodyText(
                "**Asakusa** branch: 1-8-13 Asakusa, Taito-ku - close to **Senso-ji Temple** and **Tokyo** " +
                    "**Skytree**, easy to combine with an **Asakusa** sightseeing day."
            )
            BodyText(
                "**Shinjuku** branch: Oriental Wave Building 4F, 5-17-13 Shinjuku, Shinjuku-ku - " +
                    "about 2 minutes' walk from Shinjuku-sanchome Station, near **Shinjuku Gyoen**."
            )

            SectionHeader("How to get there")
            BodyText(
                "**Asakusa** branch: take the Tokyo Metro Ginza Line, Toei Asakusa Line, or Tobu " +
                    "Skytree Line to Asakusa Station, then walk a few minutes."
            )
            BodyText(
                "**Shinjuku** branch: take the Tokyo Metro Marunouchi, Fukutoshin, or Shinjuku Line " +
                    "to Shinjuku-sanchome Station."
            )

            SectionHeader("Prices")
            BodyText(
                "Basic museum ticket (guided tour, armor try-on, shuriken throwing) is about " +
                    "¥3,000 for adults and ¥2,700 for children. Upgraded experiences - " +
                    "ninja training, tameshigiri sword-cutting, or a tea ceremony - run roughly " +
                    "¥6,000 to ¥12,000+ depending on what's bundled in. A standalone " +
                    "kimono tea ceremony (about 45 minutes, includes kimono rental, green tea, " +
                    "and sweets) runs around ¥8,000-9,500 (roughly $54-63) per adult. A " +
                    "combo ticket bundles the museum's samurai sword experience with a kimono " +
                    "tea ceremony at **Maikoya** later the same day."
            )

            SectionHeader("Ages")
            BodyText(
                "The basic museum visit suits all ages, with a discounted rate for children 12 " +
                    "and under. The sword-cutting (tameshigiri) upgrade requires a minimum age " +
                    "of 6. The tea ceremony has no strict age limit but is a seated, fairly " +
                    "quiet experience best suited to kids old enough to sit through it."
            )

            SectionHeader("How to book tickets")
            BodyText(
                "Book in advance through the official **Maikoya** website (mai-ko.com), which uses " +
                    "timed entry slots - weekends and popular times can sell out. Tickets are " +
                    "also resold through GetYourGuide and Viator. If combining the museum with " +
                    "a tea ceremony, look for the combo ticket rather than booking each " +
                    "separately, since it's timed to link the two experiences on the same day."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
