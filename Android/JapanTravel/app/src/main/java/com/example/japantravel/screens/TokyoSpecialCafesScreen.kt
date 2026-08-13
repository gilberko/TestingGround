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
fun TokyoSpecialCafesScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Special Cafes", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Cat cafes")
            BodyText(
                "Pay an entrance/time fee to relax in a room full of resident cats you can pet " +
                    "and photograph while having a drink. **MoCHA** is a well-known chain with " +
                    "several **Tokyo** locations, including **Shinjuku**, **Harajuku**, and **Shibuya**."
            )

            SectionHeader("Capybara cafes")
            BodyText(
                "**Capyneko Cafe** in **Kichijoji** lets you pet capybaras alongside resident cats, and " +
                    "**Cafe capyba** in **Asakusa** is another dedicated capybara spot."
            )

            SectionHeader("Owl cafes")
            BodyText(
                "**Akiba Fukurou** in **Akihabara** is one of the largest owl cafes in Japan, home to " +
                    "around 40 owls, and lets you observe and photograph them up close. Owl " +
                    "cafes are a common source of animal-welfare debate among visitors - if this " +
                    "matters to you, look into a specific cafe's handling and rest policies before " +
                    "booking."
            )

            SectionHeader("Puppy cafes")
            BodyText(
                "Several cafes around **Harajuku** and **Shibuya** let you play with puppies for a " +
                    "time-based fee - a good option if you're missing a dog from home. Availability " +
                    "and specific venues change fairly often, so check current listings before " +
                    "visiting."
            )

            SectionHeader("Pokemon Cafe")
            BodyText(
                "Located in Nihombashi Takashimaya S.C. (5 minutes from Tokyo Station's Yaesu " +
                    "North Exit) - Pikachu and friends \"visit\" your table and the menu is full of " +
                    "Pokemon-themed food. It's reservation-only through the official **Pokemon Cafe** " +
                    "website; there's no walk-in seating and no phone/email booking."
            )

            SectionHeader("Others")
            BodyText(
                "**Harry's Zoo Cafe** in **Harajuku** goes beyond a single animal, with capybaras, " +
                    "meerkats, chinchillas, and more under one roof."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
