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
fun UsjScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Universal Studios Japan", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("What it is")
            BodyText(
                "An officially licensed Universal theme park in **Osaka**, open since 2001. Official " +
                    "website: usj.co.jp"
            )

            SectionHeader("Main areas and rides")
            BodyText(
                "**USJ** is split into 10 areas: **Hollywood**, **New York**, **San Francisco**, **Jurassic Park**, " +
                    "**Amity Village**, **Waterworld**, **Universal Wonderland**, **Minion Park**, the **Wizarding " +
                    "World of Harry Potter**, and **Super Nintendo World**."
            )
            BodyText(
                "**Hollywood**: **Hollywood Dream: The Ride** and **Space Fantasy: The Ride** (both " +
                    "roller coasters). **Jurassic Park**: **The Flying Dinosaur** (coaster) and **Jurassic " +
                    "Park The Ride** (water ride). **Amity Village**: the **JAWS** boat ride. **Minion Park**: " +
                    "**Despicable Me Minion Mayhem**. **Wizarding World of Harry Potter**: **Harry Potter " +
                    "and the Forbidden Journey** and **Flight of the Hippogriff**. **Universal Wonderland**: " +
                    "gentle family rides themed to Sesame Street, Hello Kitty, and Snoopy. **Super " +
                    "Nintendo World**: **Mario Kart: Koopa's Challenge**, **Yoshi's Adventure**, and (since " +
                    "the **Donkey Kong Country** expansion in December 2024) **Donkey Kong Country: " +
                    "Minecart Madness**."
            )

            SectionHeader("Super Nintendo World entry")
            BodyText(
                "On busy days, **Super Nintendo World** requires a free \"Area Timed Entry Ticket\" " +
                    "to get in. These are claimed through the official **USJ** app once you're inside " +
                    "the park - and they can run out within the first hour, so use the app as " +
                    "early as possible after entry."
            )
            BodyText(
                "To avoid that scramble entirely, buy an Express Pass in advance that specifically " +
                    "includes a **Super Nintendo World** Area Timed Entry ticket (not all Express " +
                    "Passes do - check the package details). Express Passes go on sale 60 days " +
                    "before the visit date, and the ones covering **Super Nintendo World** sell out " +
                    "quickly, so booking early matters. A qualifying Express Pass both guarantees " +
                    "your entry window into **Super Nintendo World** and gives priority access to the " +
                    "rides included in that pass, skipping the standby line."
            )

            SectionHeader("The app")
            BodyText(
                "The official **USJ** app shows ride wait times and show times, is used to claim the " +
                    "free **Super Nintendo World** Area Timed Entry Ticket, and is used to buy/manage " +
                    "Express Passes. It functions as an area-entry reservation tool rather than a " +
                    "way to book a time slot for any individual ride - the exception is that an " +
                    "Express Pass (a separate paid purchase) bundles priority access to specific " +
                    "rides."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
