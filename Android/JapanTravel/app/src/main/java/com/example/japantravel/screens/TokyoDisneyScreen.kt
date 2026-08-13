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
fun TokyoDisneyScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Disneyland & DisneySea", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("What it is")
            BodyText(
                "**Tokyo Disney Resort** (in **Urayasu**, **Chiba**, just outside central **Tokyo**) has two " +
                    "separate parks on one ticket-adjacent site: **Tokyo Disneyland**, the classic " +
                    "fairy-tale Disney park, and **Tokyo DisneySea**, a nautical/adventure-themed park " +
                    "found nowhere else in the world. **DisneySea**'s newest area, **Fantasy Springs** " +
                    "(opened 2024), adds Frozen, Tangled, and Peter Pan lands."
            )

            SectionHeader("Tokyo Disneyland: Lands")
            BodyText(
                "**Tokyo Disneyland** is split into 7 themed lands radiating out from the entrance. " +
                    "**World Bazaar**: the covered shopping-and-dining arcade at the entrance, no " +
                    "rides. **Adventureland**: jungle/tropical theming, main ride is **Jungle Cruise: " +
                    "Wildlife Expeditions**. **Westernland**: old-West theming, main ride is **Big " +
                    "Thunder Mountain**. **Fantasyland**: fairy-tale theming around the castle, main " +
                    "rides are **Pooh's Hunny Hunt** and **Enchanted Tale of Beauty and the Beast**. " +
                    "**Critter Country**: woodland theming, main ride is **Beaver Brothers Explorer " +
                    "Canoes**. **Toontown**: cartoon-village theming aimed at younger kids, no thrill " +
                    "rides. **Tomorrowland**: space/future theming, main ride is **Space Mountain**."
            )

            SectionHeader("Tokyo DisneySea: Ports of Call")
            BodyText(
                "**Tokyo DisneySea** is split into 8 \"ports of call\". **Mediterranean Harbor**: " +
                    "Italian-seaside entrance area, main ride is **Soaring: Fantastic Flight**. " +
                    "**Mysterious Island**: built inside Mount Prometheus, main rides are **Journey to " +
                    "the Center of the Earth** and **20,000 Leagues Under the Sea**. **Mermaid Lagoon**: " +
                    "Little Mermaid theming, mostly aimed at younger kids. **Arabian Coast**: " +
                    "Arabian Nights theming, family-friendly rides. **Lost River Delta**: Central " +
                    "American ruins theming, main ride is **Indiana Jones Adventure**. **Port " +
                    "Discovery**: retro-futuristic port theming. **American Waterfront**: early-1900s " +
                    "New York/Cape Cod theming, main rides are **Toy Story Mania!** and **Tower of " +
                    "Terror**. **Fantasy Springs**: the newest port (opened 2024), split into Frozen, " +
                    "Tangled, and Peter Pan sub-areas."
            )

            SectionHeader("Fireworks and nighttime shows")
            BodyText(
                "Each park runs one nighttime spectacular per night, not a show that repeats " +
                    "through the day - daytime hours instead have separate parades with no " +
                    "fireworks. **Tokyo Disneyland**'s nighttime show is **Sky Full of Colors**, a " +
                    "roughly 5-minute fireworks display over the castle (note: this show is " +
                    "suspended from mid-June through mid-September 2026, so it's worth checking " +
                    "current dates before counting on it). **Tokyo DisneySea**'s nighttime show is " +
                    "**Believe! Sea of Dreams**, a roughly 30-minute show staged on the water in " +
                    "Mediterranean Harbor with pyrotechnics and projection rather than a " +
                    "traditional fireworks display."
            )

            SectionHeader("Main rides")
            BodyText(
                "**Disneyland**: **Pooh's Hunny Hunt**, **Big Thunder Mountain**, **Space Mountain**, **Haunted " +
                    "Mansion**, **Pirates of the Caribbean**, **Beauty and the Beast: A Magical Journey**."
            )
            BodyText(
                "**DisneySea**: **Soaring: Fantastic Flight**, **Toy Story Mania!**, **Journey to the Center of " +
                    "the Earth**, **Indiana Jones Adventure**, **Tower of Terror**, **20,000 Leagues Under the " +
                    "Sea**, and the **Fantasy Springs** rides (Frozen, Rapunzel's/Tangled, Peter Pan)."
            )

            SectionHeader("The app, costs, and ride reservations")
            BodyText(
                "The **Tokyo Disney Resort** App is used to buy park tickets, check hours, and book " +
                    "restaurants. It also runs two queue systems: Disney Premier Access (DPA) lets " +
                    "you pay to reserve a specific entry time for a specific popular ride (roughly " +
                    "¥1,500-2,500 per person per attraction, priced daily in the app), and Standby " +
                    "Pass is a free virtual queue required for some attractions, including the " +
                    "**Fantasy Springs** rides. So yes — the app can reserve a ride time slot, either " +
                    "paid (DPA) or free (Standby Pass) depending on the ride."
            )
            BodyText(
                "A one-day passport costs roughly ¥7,900-10,900 depending on the date (**Tokyo " +
                    "Disney** uses variable date-based pricing), on top of any optional DPA purchases."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
