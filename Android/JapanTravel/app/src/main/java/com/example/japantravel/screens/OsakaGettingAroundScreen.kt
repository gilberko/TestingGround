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
fun OsakaGettingAroundScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Getting Around", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Osaka Metro")
            BodyText(
                "Osaka Metro is the backbone of getting around the city, and the Midosuji Line " +
                    "(shown in red on maps) is the one line to know first - it's a direct " +
                    "north-south spine connecting **Umeda**, **Namba**, **Shinsaibashi**, and **Tennoji** with no " +
                    "transfers needed between any of them. The Tanimachi Line is the other " +
                    "commonly used line, reaching **Osaka Castle** and **Tennoji** from a different angle. " +
                    "IC cards (see General Information) work throughout."
            )

            SectionHeader("Buses")
            BodyText(
                "Local buses fill in the gaps the subway doesn't reach, particularly for spots a " +
                    "bit off the main lines - useful but rarely necessary for the core sightseeing " +
                    "areas, since the Midosuji Line already covers most of them directly."
            )

            SectionHeader("Taxis")
            BodyText(
                "Taxis are reliable and safe but noticeably pricier than the subway - most useful " +
                    "late at night once trains stop running, or when carrying heavy luggage. See " +
                    "General Information - Public Transportation for general taxi etiquette."
            )

            SectionHeader("Best way to get around, and main stations")
            BodyText(
                "For most visitors, the Midosuji Line subway is simply the best way to move " +
                    "between **Osaka**'s main areas - fast, frequent, and requiring no transfers " +
                    "between the stops below."
            )
            BodyText(
                "**Umeda** / Osaka Station: the main hub and arrival point for most visitors, with JR, " +
                    "Hankyu, Hanshin, and subway lines all converging here."
            )
            BodyText(
                "Namba Station: the gateway to **Dotonbori** and the **Namba** shopping/nightlife area."
            )
            BodyText(
                "Shinsaibashi Station: one stop from **Namba** on the Midosuji Line, for the " +
                    "**Shinsaibashi-suji** covered shopping arcade."
            )
            BodyText(
                "Tennoji Station: for **Shitennoji Temple** and **Tennoji Zoo**, and also the connection " +
                    "point to **Kansai** Airport via the Haruka express or the Nankai Line."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
