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
fun HotelsOnsensRyokansScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "About Hotels, Onsens and Ryokans", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Onsens & Ryokans")
            BodyText(
                "An onsen is a natural hot spring bath, found as standalone bathhouses or as part " +
                    "of a hotel/ryokan. Bathers wash and rinse thoroughly before entering the " +
                    "water, then bathe nude (swimsuits are generally not worn). Baths are often " +
                    "separated by gender, and some onsens restrict guests with visible tattoos."
            )
            BodyText(
                "A ryokan is a traditional Japanese inn, with tatami-mat rooms, futon bedding laid " +
                    "out on the floor, and often a multi-course kaiseki dinner and breakfast " +
                    "included. Many ryokans have their own onsen baths for guests."
            )

            SectionHeader("Family Room Bed Configurations")
            BodyText(
                "Is it common to book a room for a family of 4 and get fewer beds than expected? " +
                    "Yes - this is a real and fairly common pattern, not a booking mistake. A room " +
                    "listed as \"sleeps 4\" often doesn't mean 4 separate Western-style beds - it " +
                    "can mean 2 beds plus 2 futons, bunk beds plus a sofa bed, or a traditional " +
                    "tatami room where futons are laid out on the floor for multiple guests, with " +
                    "young children sometimes expected to share a bed or futon with a parent."
            )
            BodyText(
                "Why it happens: a mix of genuinely smaller Japanese hotel room sizes, cultural " +
                    "norms around family sleeping arrangements (young children sharing with a " +
                    "parent rather than needing their own bed), and booking-site categorization - " +
                    "\"sleeps 4\" or \"quad room\" often describes total sleeping capacity through a " +
                    "mix of bedding types, not a guaranteed count of separate beds."
            )
            BodyText(
                "It's a known, frequently mentioned pain point for Western families expecting one " +
                    "bed per person, so it's worth planning around rather than assuming."
            )
            BodyText(
                "Practical tips: look for family-oriented hotel brands known for genuine " +
                    "multi-bed rooms (**MIMARU**, **MONday**, **FAV**, and **Minn** are commonly " +
                    "mentioned examples), read the exact bedding breakdown in the room description " +
                    "rather than just the \"sleeps X\" number, check the hotel's child-age policy " +
                    "for shared bedding, and consider booking two twin/double rooms instead of one " +
                    "quad room if a separate bed per person matters to you. A ryokan's tatami-and-" +
                    "futon setup is a legitimate, comfortable option in its own right - just set " +
                    "expectations that it's floor futons rather than beds."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
